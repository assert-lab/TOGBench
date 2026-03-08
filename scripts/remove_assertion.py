import argparse
import re
from pathlib import Path
import pandas as pd

outputs_toga = Path("/home/tasfia/Desktop/oe25_for_toga/toga_output_OE25Dev/JSON-java/oracle_preds.csv").expanduser()

FAIL_RE = re.compile(r"\bfail\s*\(", re.MULTILINE)
ASSERT_LINE_START_RE = re.compile(r"\b(?:Assert\.)?assert\w*\s*\(")

def replace_last_assertion_block(prefix: str) -> tuple[str, bool]:
    if prefix is None:
        return prefix, False
    s = str(prefix)
    lines = s.splitlines(True)
    if not lines:
        return s, False

    end_idx = None
    start_idx = None

    for i in range(len(lines) - 1, -1, -1):
        if ';' not in lines[i]:
            continue
        j = i
        found = False
        while j >= 0:
            if ASSERT_LINE_START_RE.search(lines[j]):
                found = True
                break
            if '{' in lines[j] or '}' in lines[j]:
                break
            j -= 1
        if found:
            end_idx = i
            start_idx = j
            break

    if start_idx is None:
        return s, False

    indent_match = re.match(r"^([ \t]*)", lines[start_idx])
    indent = indent_match.group(1) if indent_match else ""

    hole_line = indent + "<ASSERTION_HOLE>;\n"

    new_lines = lines[:start_idx] + [hole_line] + lines[end_idx + 1:]
    return "".join(new_lines), True

def process_dataset_dir(dataset_dir: Path) -> dict:
    inputs_path = dataset_dir / "inputs.csv"
    outputs_path = outputs_toga

    inputs = pd.read_csv(inputs_path)

    if "id" not in inputs.columns or "test_prefix" not in inputs.columns:
        raise ValueError(f"Missing required columns in {inputs_path}")

    if not outputs_path.exists():
        raise FileNotFoundError(outputs_path)

    # outputs = pd.read_csv(outputs_path)

    # if "id" not in outputs.columns:
    #     raise ValueError(f"outputs.csv missing id column: {outputs_path}")

    # ids = set(outputs["id"].astype(str))

    outputs = pd.read_csv(outputs_path)

    if "bug_num" not in outputs.columns or "assert_pred" not in outputs.columns:
        raise ValueError(f"oracle_preds.csv must contain bug_num and assert_pred")

    outputs = outputs[outputs["assert_pred"].notna()]
    ids = set(outputs["bug_num"].astype(str))

    ids = set(outputs["bug_num"].astype(str))

    inputs["id"] = inputs["id"].astype(str)

    inputs["id"] = inputs["id"].astype(str).str.strip()
    outputs["bug_num"] = outputs["bug_num"].astype(str).str.strip()

    inputs_assert = inputs[inputs["id"].isin(ids)].copy()

        

    changed_count = 0
    new_prefixes = []

    for p in inputs_assert["test_prefix"].tolist():
        new_p, changed = replace_last_assertion_block(p)
        if changed:
            changed_count += 1
        new_prefixes.append(new_p)

    inputs_assert["test_prefix"] = new_prefixes

    out_assert = dataset_dir / "inputs_no_assert.csv"
    inputs_assert.to_csv(out_assert, index=False)

    return {
        "dataset_dir": str(dataset_dir),
        "inputs_rows": len(inputs),
        "assert_rows": len(inputs_assert),
        "assert_replaced_rows": changed_count
    }

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--root", required=True)
    args = ap.parse_args()

    root = Path(args.root)
    if not root.exists():
        raise FileNotFoundError(str(root))

    dataset_dirs = sorted({p.parent for p in root.rglob("dataset/inputs.csv")})
    if not dataset_dirs:
        raise FileNotFoundError("No dataset/inputs.csv found under root")

    total = {
        "datasets": 0,
        "inputs_rows": 0,
        "assert_rows": 0,
        "assert_replaced_rows": 0
    }

    for d in dataset_dirs:
        stats = process_dataset_dir(d)
        total["datasets"] += 1
        total["inputs_rows"] += stats["inputs_rows"]
        total["assert_rows"] += stats["assert_rows"]
        total["assert_replaced_rows"] += stats["assert_replaced_rows"]

        print("processed", stats["dataset_dir"])
        print(" inputs", stats["inputs_rows"])
        print(" assert_rows", stats["assert_rows"])
        print(" replaced", stats["assert_replaced_rows"])

    print("TOTAL_DATASETS", total["datasets"])
    print("TOTAL_INPUTS", total["inputs_rows"])
    print("TOTAL_ASSERT_ROWS", total["assert_rows"])
    print("TOTAL_REPLACED", total["assert_replaced_rows"])

if __name__ == "__main__":
    main()