import csv
import os
from pathlib import Path

root = Path("projects_decomposed")

total_fail = 0

for project in root.iterdir():

    dataset = project / "dataset"
    inputs_path = dataset / "inputs.csv"
    meta_path = dataset / "meta.csv"

    if not inputs_path.exists() or not meta_path.exists():
        continue

    out_dir = project / "dataset_with_fail"
    out_dir.mkdir(exist_ok=True)

    inputs_out = out_dir / "inputs_with_fail.csv"
    meta_out = out_dir / "meta_with_fail.csv"

    fail_ids = set()

    with open(inputs_path, newline="") as f:
        reader = csv.DictReader(f)
        rows = list(reader)
        input_fields = reader.fieldnames

    fail_rows = []
    for r in rows:
        prefix = (r.get("test_prefix") or "")
        if "fail(" in prefix:
            fail_ids.add(r["id"])
            fail_rows.append(r)

    with open(inputs_out, "w", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=input_fields)
        writer.writeheader()
        writer.writerows(fail_rows)

    with open(meta_path, newline="") as f:
        reader = csv.DictReader(f)
        meta_rows = list(reader)
        meta_fields = reader.fieldnames

    meta_keep = [r for r in meta_rows if r.get("id") in fail_ids]

    with open(meta_out, "w", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=meta_fields)
        writer.writeheader()
        writer.writerows(meta_keep)

    print(project.name, "fail cases:", len(fail_rows))

    total_fail += len(fail_rows)

print("TOTAL fail cases:", total_fail)