# python3 scripts/merge_rows.py

import os
import csv

ROOT = "projects_decomposed"

def read_csv(path):
    if not os.path.exists(path):
        return [], []
    with open(path, newline='', encoding='utf-8') as f:
        r = csv.DictReader(f)
        rows = list(r)
        return r.fieldnames, rows


def write_csv(path, fieldnames, rows):
    with open(path, "w", newline='', encoding='utf-8') as f:
        w = csv.DictWriter(f, fieldnames=fieldnames)
        w.writeheader()
        w.writerows(rows)


for project in os.listdir(ROOT):

    proj = os.path.join(ROOT, project)

    dataset_dir = os.path.join(proj, "dataset")
    must_dir = os.path.join(proj, "dataset_MUST_THROW")

    inputs = os.path.join(dataset_dir, "inputs.csv")
    meta = os.path.join(dataset_dir, "meta.csv")

    inputs_must = os.path.join(must_dir, "inputs_passed.csv")
    meta_must = os.path.join(must_dir, "meta_passed.csv")

    if not os.path.exists(inputs) or not os.path.exists(inputs_must):
        continue

    f_inputs, rows_inputs = read_csv(inputs)
    f_meta, rows_meta = read_csv(meta)

    f_inputs_m, rows_inputs_m = read_csv(inputs_must)
    f_meta_m, rows_meta_m = read_csv(meta_must)

    existing_tests = set(r["test_name"] for r in rows_inputs)

    add_inputs = []
    add_meta = []

    for r in rows_inputs_m:
        if r["test_name"] not in existing_tests:
            add_inputs.append(r)

    existing_ids = set(r["id"] for r in rows_meta)

    for r in rows_meta_m:
        if r["id"] not in existing_ids:
            add_meta.append(r)

    merged_inputs = rows_inputs + add_inputs
    merged_meta = rows_meta + add_meta

    out_inputs = os.path.join(dataset_dir, "inputs_all.csv")
    out_meta = os.path.join(dataset_dir, "meta_all.csv")

    write_csv(out_inputs, f_inputs, merged_inputs)
    write_csv(out_meta, f_meta, merged_meta)

    print(project, "added", len(add_inputs), "rows")