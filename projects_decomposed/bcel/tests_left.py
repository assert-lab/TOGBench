#!/usr/bin/env python3
import csv
from pathlib import Path

def extract_method_name(sig: str) -> str:
    s = (sig or "").strip()
    if "(" not in s:
        return ""
    pre = s.split("(", 1)[0].strip()
    if not pre:
        return ""
    return pre.split()[-1].strip()

def simple_classname(fq: str) -> str:
    s = (fq or "").strip().strip(".")
    if not s:
        return ""
    return s.split(".")[-1].strip()

def normalize_test_name(t: str) -> str:
    s = (t or "").strip()
    if not s:
        return ""
    if "_oe" in s:
        return s.split("_oe", 1)[0] + "_oe"
    return s

def main():
    all_tests_path = Path("all_test_methods.csv")
    inputs_final_path = Path("dataset") / "inputs.csv"
    out_path = Path("methods_left.csv")

    if not all_tests_path.exists():
        print("missing all_test_methods.csv")
        return
    if not inputs_final_path.exists():
        print("missing dataset/inputs.csv")
        return

    keep_keys = set()
    with inputs_final_path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        if not r.fieldnames or "test_name" not in r.fieldnames:
            print("inputs.csv missing test_name column")
            return
        for row in r:
            t = normalize_test_name(row.get("test_name", ""))
            if t:
                keep_keys.add(t)

    left_rows = []
    with all_tests_path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        if not r.fieldnames:
            print("all_test_methods.csv has no header")
            return
        required = ["classname", "method", "docstring", "annotations", "method_definition"]
        for c in required:
            if c not in r.fieldnames:
                print("all_test_methods.csv missing column:", c)
                return

        for row in r:
            cls = simple_classname(row.get("classname", ""))
            mname = extract_method_name(row.get("method", ""))
            if not cls or not mname:
                continue
            key = f"{cls}::{mname}"
            key_norm = normalize_test_name(key)
            if key_norm not in keep_keys:
                left_rows.append(row)

    with out_path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(
            f,
            fieldnames=["classname", "method", "docstring", "annotations", "method_definition"],
            lineterminator="\n",
        )
        w.writeheader()
        for row in left_rows:
            w.writerow({k: row.get(k, "") for k in w.fieldnames})

    print("methods_left =", out_path.as_posix())
    print("left_count =", len(left_rows))
    print("inputs_count =", len(keep_keys))

if __name__ == "__main__":
    main()
