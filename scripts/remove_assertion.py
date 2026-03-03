import argparse
import re
from pathlib import Path
import pandas as pd

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
    meta_path = dataset_dir / "meta.csv"

    inputs = pd.read_csv(inputs_path)
    if "id" not in inputs.columns or "test_prefix" not in inputs.columns:
        raise ValueError(f"Missing required columns in {inputs_path}")

    if meta_path.exists():
        meta = pd.read_csv(meta_path)
        if "id" not in meta.columns:
            raise ValueError(f"meta.csv missing 'id' column: {meta_path}")
    else:
        meta = None

    is_except = inputs["test_prefix"].astype(str).str.contains(FAIL_RE)
    inputs_except = inputs[is_except].copy()
    inputs_norm = inputs[~is_except].copy()

    changed_count = 0
    new_prefixes = []
    for p in inputs_norm["test_prefix"].tolist():
        new_p, changed = replace_last_assertion_block(p)
        if changed:
            changed_count += 1
        new_prefixes.append(new_p)
    inputs_norm["test_prefix"] = new_prefixes

    out_no_assert = dataset_dir / "inputs_no_assert.csv"
    out_except_inputs = dataset_dir / "inputs_except.csv"
    inputs_norm.to_csv(out_no_assert, index=False)
    inputs_except.to_csv(out_except_inputs, index=False)

    meta_except_rows = 0
    if meta is not None:
        except_ids = set(inputs_except["id"].astype(str).tolist())
        meta_id_str = meta["id"].astype(str)
        meta_except = meta[meta_id_str.isin(except_ids)].copy()
        out_except_meta = dataset_dir / "meta_except.csv"
        meta_except.to_csv(out_except_meta, index=False)
        meta_except_rows = len(meta_except)

    return {
        "dataset_dir": str(dataset_dir),
        "inputs_rows": len(inputs),
        "except_rows": int(is_except.sum()),
        "no_assert_rows": int((~is_except).sum()),
        "assert_replaced_rows": changed_count,
        "meta_except_rows": meta_except_rows
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
        "except_rows": 0,
        "no_assert_rows": 0,
        "assert_replaced_rows": 0,
        "meta_except_rows": 0
    }

    for d in dataset_dirs:
        stats = process_dataset_dir(d)
        total["datasets"] += 1
        total["inputs_rows"] += stats["inputs_rows"]
        total["except_rows"] += stats["except_rows"]
        total["no_assert_rows"] += stats["no_assert_rows"]
        total["assert_replaced_rows"] += stats["assert_replaced_rows"]
        total["meta_except_rows"] += stats["meta_except_rows"]

        print("processed", stats["dataset_dir"])
        print(" inputs", stats["inputs_rows"])
        print(" except", stats["except_rows"])
        print(" no_assert", stats["no_assert_rows"])
        print(" replaced", stats["assert_replaced_rows"])
        if stats["meta_except_rows"]:
            print(" meta_except", stats["meta_except_rows"])

    print("TOTAL_DATASETS", total["datasets"])
    print("TOTAL_INPUTS", total["inputs_rows"])
    print("TOTAL_EXCEPT", total["except_rows"])
    print("TOTAL_NO_ASSERT", total["no_assert_rows"])
    print("TOTAL_REPLACED", total["assert_replaced_rows"])
    print("TOTAL_META_EXCEPT", total["meta_except_rows"])

if __name__ == "__main__":
    main()