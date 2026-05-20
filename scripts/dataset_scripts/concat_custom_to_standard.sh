#!/usr/bin/env bash
set -euo pipefail

root="$(pwd)"
projects_dir="${root}/projects_decomposed"

python3 - "$projects_dir" <<'PY'
import csv
import sys
from pathlib import Path

projects_dir = Path(sys.argv[1])

def read_csv(path: Path):
    with path.open("r", newline="", encoding="utf-8") as f:
        r = csv.DictReader(f)
        if r.fieldnames is None:
            return [], []
        return r.fieldnames, list(r)

def write_csv(path: Path, fieldnames, rows):
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        w.writeheader()
        for row in rows:
            w.writerow({k: row.get(k, "") for k in fieldnames})

def align_rows(src_fields, rows, dst_fields):
    src_set = set(src_fields)
    dst_set = set(dst_fields)
    missing_in_src = dst_set - src_set
    if missing_in_src:
        return None, sorted(missing_in_src)
    out = []
    for r in rows:
        out.append({k: r.get(k, "") for k in dst_fields})
    return out, []

def process_dataset(dataset_dir: Path):
    inputs = dataset_dir / "inputs.csv"
    meta = dataset_dir / "meta.csv"
    inputs_add = dataset_dir / "inputs_custom_to_standard.csv"
    meta_add = dataset_dir / "meta_custom_to_standard.csv"

    if not inputs.exists() or not meta.exists():
        return

    if not inputs_add.exists() and not meta_add.exists():
        return

    in_fields, in_rows = read_csv(inputs)
    meta_fields, meta_rows = read_csv(meta)

    appended_inputs = 0
    appended_meta = 0

    if inputs_add.exists():
        add_fields, add_rows = read_csv(inputs_add)
        aligned, missing = align_rows(add_fields, add_rows, in_fields)
        if aligned is None:
            print(f"{dataset_dir.parent.name}: inputs_custom_to_standard columns missing: {','.join(missing)}")
        else:
            in_rows.extend(aligned)
            appended_inputs = len(aligned)

    if meta_add.exists():
        add_fields, add_rows = read_csv(meta_add)
        aligned, missing = align_rows(add_fields, add_rows, meta_fields)
        if aligned is None:
            print(f"{dataset_dir.parent.name}: meta_custom_to_standard columns missing: {','.join(missing)}")
        else:
            meta_rows.extend(aligned)
            appended_meta = len(aligned)

    if appended_inputs:
        write_csv(inputs, in_fields, in_rows)
    if appended_meta:
        write_csv(meta, meta_fields, meta_rows)

    print(f"{dataset_dir.parent.name}: appended_inputs={appended_inputs} appended_meta={appended_meta}")

count = 0
for project_dir in sorted(projects_dir.iterdir()):
    dataset_dir = project_dir / "dataset"
    if dataset_dir.exists():
        process_dataset(dataset_dir)
        count += 1

print(f"done projects={count}")
PY
