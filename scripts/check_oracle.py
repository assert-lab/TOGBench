# python3 scripts/check_oracle.py

import os
import csv
import re

ROOT = "projects_decomposed"

ASSERT_RE = re.compile(r'\b(assert\w*|fail)\s*\(', re.IGNORECASE)

def process_file(path):
    with open(path, newline='', encoding='utf-8') as f:
        reader = csv.DictReader(f)
        rows = list(reader)
        fieldnames = reader.fieldnames

    if not rows:
        print(path, "empty file, skipping")
        return

    keep = []
    removed = []

    for r in rows:
        prefix = r.get("test_prefix", "")
        if ASSERT_RE.search(prefix):
            keep.append(r)
        else:
            removed.append(r)

    base = os.path.dirname(path)

    with open(path, "w", newline='', encoding='utf-8') as f:
        w = csv.DictWriter(f, fieldnames=fieldnames)
        w.writeheader()
        w.writerows(keep)

    out_removed = os.path.join(base, "inputs_no_assert.csv")

    with open(out_removed, "w", newline='', encoding='utf-8') as f:
        w = csv.DictWriter(f, fieldnames=fieldnames)
        w.writeheader()
        w.writerows(removed)

    print(path, "kept", len(keep), "removed", len(removed))


for project in os.listdir(ROOT):
    proj = os.path.join(ROOT, project)

    for dataset in ["dataset_MUST_THROW"]:
        inp = os.path.join(proj, dataset, f"inputs_passed.csv")

        if os.path.exists(inp):
            process_file(inp)