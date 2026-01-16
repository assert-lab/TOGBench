#!/usr/bin/env python3
import os
import re
import csv
from pathlib import Path

BASE = Path(__file__).resolve().parent.parent
LOG_PATH = BASE / "scripts" / "deleted_test_files.log"
PROJECTS_DIR = BASE / "projects_decomposed"

deleted_names = set()
pattern = re.compile(r"deleting\s+(\S+_OE25Dev\.java)")

with LOG_PATH.open("r", encoding="utf-8", errors="ignore") as f:
    for line in f:
        m = pattern.search(line)
        if not m:
            continue
        name = m.group(1)
        orig = re.sub(r"_OE25Dev\.java$", ".java", name)
        deleted_names.add(orig)

print(f"Loaded {len(deleted_names)} deleted test files from log")

for project_dir in PROJECTS_DIR.iterdir():
    if not project_dir.is_dir():
        continue
    dataset_dir = project_dir / "dataset"
    meta_path = dataset_dir / "meta.csv"
    inputs_path = dataset_dir / "inputs.csv"
    if not meta_path.exists() or not inputs_path.exists():
        continue

    with meta_path.open(newline="", encoding="utf-8") as f:
        meta_rows = list(csv.reader(f))
    if not meta_rows:
        continue

    meta_header = meta_rows[0]
    if "test_file_path" not in meta_header or "id" not in meta_header:
        print(f"Skipping {project_dir.name}: required columns missing in meta.csv")
        continue

    idx_test = meta_header.index("test_file_path")
    idx_id = meta_header.index("id")

    kept_meta = [meta_header]
    failed_meta = [meta_header]
    failed_ids = set()

    for row in meta_rows[1:]:
        if idx_test >= len(row):
            kept_meta.append(row)
            continue
        path = row[idx_test]
        base = os.path.basename(path)
        if base in deleted_names:
            failed_meta.append(row)
            if idx_id < len(row):
                failed_ids.add(row[idx_id])
        else:
            kept_meta.append(row)

    with inputs_path.open(newline="", encoding="utf-8") as f:
        inputs_rows = list(csv.reader(f))
    if not inputs_rows:
        continue

    inputs_header = inputs_rows[0]
    if "id" not in inputs_header:
        print(f"Skipping {project_dir.name}: 'id' column missing in inputs.csv")
        continue

    idx_in_id = inputs_header.index("id")

    kept_inputs = [inputs_header]
    failed_inputs = [inputs_header]

    for row in inputs_rows[1:]:
        if idx_in_id >= len(row):
            kept_inputs.append(row)
            continue
        rid = row[idx_in_id]
        if rid in failed_ids:
            failed_inputs.append(row)
        else:
            kept_inputs.append(row)

    with meta_path.open("w", newline="", encoding="utf-8") as f:
        csv.writer(f).writerows(kept_meta)
    with inputs_path.open("w", newline="", encoding="utf-8") as f:
        csv.writer(f).writerows(kept_inputs)

    meta_failed_path = dataset_dir / "meta_failed.csv"
    inputs_failed_path = dataset_dir / "inputs_failed.csv"

    with meta_failed_path.open("w", newline="", encoding="utf-8") as f:
        csv.writer(f).writerows(failed_meta)
    with inputs_failed_path.open("w", newline="", encoding="utf-8") as f:
        csv.writer(f).writerows(failed_inputs)

    print(
        f"Project {project_dir.name}: moved {len(failed_meta)-1} tests to *_failed.csv, kept {len(kept_meta)-1}"
    )
