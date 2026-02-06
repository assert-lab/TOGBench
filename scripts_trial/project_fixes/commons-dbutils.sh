cd "$PWD/projects_decomposed/commons-dbutils"

python3 - <<'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

bad_ids = set()
kept_meta = []
removed_meta = 0

with meta_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    meta_fields = r.fieldnames
    for row in r:
        name = (row.get("test_name") or "")
        if name.startswith("testApplyType"):
            bad_ids.add(row["id"])
            removed_meta += 1
        else:
            kept_meta.append(row)

print("removed from meta:", removed_meta)
print("bad_ids:", len(bad_ids))

with meta_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=meta_fields)
    w.writeheader()
    w.writerows(kept_meta)

kept_inputs = []
removed_inputs = 0

with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    in_fields = r.fieldnames
    for row in r:
        if row["id"] in bad_ids:
            removed_inputs += 1
            continue
        kept_inputs.append(row)

print("removed from inputs:", removed_inputs)

with inputs_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=in_fields)
    w.writeheader()
    w.writerows(kept_inputs)
PY
