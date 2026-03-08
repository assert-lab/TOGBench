# python3 scripts/inject_assertion.py \
#   --inputs projects_decomposed/commons-configuration2/dataset/inputs_no_assert.csv \
#   --preds  /home/tasfia/Desktop/oe25_for_toga/toga_output_OE25Dev/commons-configuration2/oracle_preds.csv \
#   --out    projects_decomposed/commons-configuration2/dataset/inputs_llm.csv

import argparse
from pathlib import Path
import pandas as pd

HOLE = "<ASSERTION_HOLE>"

def is_assert_stmt(s: str) -> bool:
    if s is None:
        return False
    t = str(s).lstrip()
    return t.startswith("assert") or t.startswith("Assert.")

def normalize_assert(s: str) -> str:
    if s is None:
        return ""
    s = str(s).strip()
    if s == "" or s.lower() == "nan":
        return ""
    if not s.endswith(";"):
        s += ";"
    return s



def fill_file(inputs_path: Path, preds_path: Path, out_path: Path) -> dict:
    inputs = pd.read_csv(inputs_path)
    # preds = pd.read_csv(preds_path)

    preds = pd.read_csv(preds_path)

    if "bug_num" not in preds.columns:
        raise ValueError(f"{preds_path} must contain column: bug_num")

    if "assert_pred" not in preds.columns:
        raise ValueError(f"{preds_path} must contain column: assert_pred")

    preds = preds[preds["assert_pred"].notna()]

    preds = preds.rename(columns={
        "bug_num": "id",
        "assert_pred": "output"
    })

    if "id" not in inputs.columns or "test_prefix" not in inputs.columns:
        raise ValueError(f"{inputs_path} must contain columns: id, test_prefix")
    if "output" not in preds.columns or "output" not in preds.columns:
        raise ValueError(f"{preds_path} must contain columns: id, output")

    inputs["id"] = inputs["id"].astype(str)
    preds["id"] = preds["id"].astype(str)

    id_to_out = {}
    multiline_pred = 0

    for _id, out in zip(preds["id"].tolist(), preds["output"].tolist()):
        if out is None:
            continue

        out = str(out).strip()

        if "\n" in out or "\r" in out:
            multiline_pred += 1
            continue

        out_norm = normalize_assert(out)

        if out_norm != "" and is_assert_stmt(out_norm):
            id_to_out[_id] = out_norm

    total = len(inputs)
    had_hole = 0
    filled = 0
    missing_pred = 0
    no_hole_rows = 0

    new_rows = []

    for _, row in inputs.iterrows():
        _id = str(row["id"])
        prefix = "" if row["test_prefix"] is None else str(row["test_prefix"])

        if HOLE not in prefix:
            no_hole_rows += 1
            new_rows.append(row)
            continue

        had_hole += 1
        out_stmt = id_to_out.get(_id, "")

        if out_stmt == "":
            missing_pred += 1
            continue

        replaced = prefix.replace(HOLE + ";", out_stmt).replace(HOLE, out_stmt)

        row["test_prefix"] = replaced
        new_rows.append(row)
        filled += 1

    inputs = pd.DataFrame(new_rows)

    out_path.parent.mkdir(parents=True, exist_ok=True)
    inputs.to_csv(out_path, index=False)

    return {
        "inputs": str(inputs_path),
        "preds": str(preds_path),
        "out": str(out_path),
        "total_rows": total,
        "rows_with_hole": had_hole,
        "filled_rows": filled,
        "missing_prediction_for_id": missing_pred,
        "rows_without_hole": no_hole_rows,
        "multiline_predictions": multiline_pred
    }

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--inputs", required=True)
    ap.add_argument("--preds", required=True)
    ap.add_argument("--out", required=True)
    args = ap.parse_args()

    stats = fill_file(Path(args.inputs), Path(args.preds), Path(args.out))
    print("inputs", stats["inputs"])
    print("preds", stats["preds"])
    print("out", stats["out"])
    print("total_rows", stats["total_rows"])
    print("rows_with_hole", stats["rows_with_hole"])
    print("filled_rows", stats["filled_rows"])
    print("missing_prediction_for_id", stats["missing_prediction_for_id"])
    print("rows_without_hole", stats["rows_without_hole"])
    print("multiline_predictions", stats["multiline_predictions"])

if __name__ == "__main__":
    main()