#!/usr/bin/env python3
import csv
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SCRIPTS_DIR = ROOT / "scripts"
PROJECTS_ROOT = ROOT / "projects_decomposed" / "projects"

FAIL_LINES_CSV = SCRIPTS_DIR / "test_fail_lines.csv"

FAIL_TEST_RE = re.compile(r"^([A-Za-z0-9_$.]+)\.([A-Za-z0-9_]+)")

def base_class_name(c):
    if c.endswith("_OE25Dev"):
        return c[:-len("_OE25Dev")]
    return c

failed_by_folder = {}

with FAIL_LINES_CSV.open(encoding="utf-8", newline="") as f:
    r = csv.DictReader(f)
    for row in r:
        folder = row["folder"]
        line = row["line"].strip()
        first = line.split()[0] if line else ""
        m = FAIL_TEST_RE.match(first)
        if not m:
            continue
        clazz = m.group(1)
        method = m.group(2)
        failed_by_folder.setdefault(folder, set()).add((clazz, method))

print("folders with failed tests:", len(failed_by_folder))

total_meta = 0
total_inputs = 0

for folder, failed_set in failed_by_folder.items():
    dataset_dir = PROJECTS_ROOT / folder / "dataset"
    meta_path = dataset_dir / "meta.csv"
    inputs_path = dataset_dir / "inputs.csv"

    if not meta_path.is_file() or not inputs_path.is_file():
        continue

    with meta_path.open(encoding="utf-8", newline="") as f:
        r = csv.DictReader(f)
        meta_fields = list(r.fieldnames or [])
        if "base_test_class" not in meta_fields:
            meta_fields.append("base_test_class")
        meta_out = []
        for row in r:
            tc = row.get("test_class", "")
            tm = row.get("test_method", "")
            row["base_test_class"] = base_class_name(tc)
            if (tc, tm) not in failed_set:
                meta_out.append(row)

    meta_final_path = dataset_dir / "meta_final.csv"
    with meta_final_path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=meta_fields)
        w.writeheader()
        w.writerows(meta_out)

    with inputs_path.open(encoding="utf-8", newline="") as f:
        r = csv.DictReader(f)
        inputs_fields = list(r.fieldnames or [])
        if "base_test_class" not in inputs_fields:
            inputs_fields.append("base_test_class")
        inputs_out = []
        for row in r:
            tc = row.get("test_class", "")
            tm = row.get("test_method", "")
            row["base_test_class"] = base_class_name(tc)
            if (tc, tm) not in failed_set:
                inputs_out.append(row)

    inputs_final_path = dataset_dir / "inputs_final.csv"
    with inputs_final_path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=inputs_fields)
        w.writeheader()
        w.writerows(inputs_out)

    total_meta += len(meta_out)
    total_inputs += len(inputs_out)
    print(folder, "meta_final:", len(meta_out), "inputs_final:", len(inputs_out))

print("total_meta:", total_meta)
print("total_inputs:", total_inputs)
