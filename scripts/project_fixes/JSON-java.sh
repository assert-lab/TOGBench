#!/usr/bin/env bash
set -euo pipefail
echo $PWD

ROOT="$PWD/projects_decomposed/JSON-java"
DATASET_DIR="$ROOT/dataset"
META="$DATASET_DIR/meta.csv"
INPUTS="$DATASET_DIR/inputs.csv"

cd "$ROOT"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset_final")
meta_path = dataset / "meta_final.csv"
inputs_path = dataset / "inputs_final.csv"

fail_list = [
"JSONObjectTest.testSingletonBean_7_oe",
"JSONObjectTest.testSingletonBean_8_oe",
"JSONObjectTest.testSingletonEnumBean_7_oe",
"JSONObjectTest.testSingletonEnumBean_8_oe",
"JSONObjectTest.testSingletonBean_8_oe",
"JSONObjectTest.testSingletonEnumBean_8_oe",
]

pairs = set()
for full in fail_list:
    cls, name = full.split(".", 1)
    base_cls = cls.replace("_OE25Dev", "")
    pairs.add((base_cls, name))

bad_ids = set()
meta_rows = []

with meta_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    fieldnames = r.fieldnames
    for row in r:
        key = (row["test_class"], row["test_name"])
        if key in pairs:
            bad_ids.add(row["id"])
        else:
            meta_rows.append(row)

with meta_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=fieldnames)
    w.writeheader()
    w.writerows(meta_rows)

inputs_rows = []
with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    in_fields = r.fieldnames
    for row in r:
        if row["id"] in bad_ids:
            continue
        inputs_rows.append(row)

with inputs_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=in_fields)
    w.writeheader()
    w.writerows(inputs_rows)
PY
