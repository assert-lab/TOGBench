# #!/usr/bin/env bash
# set -euo pipefail

# ROOT_DIR="$(cd "$(dirname "$0")/../.." && pwd)"
# PROJECT_ROOT="$ROOT_DIR/projects_decomposed/commons-collections4"
# DATASET_DIR="$PROJECT_ROOT/dataset"
# INPUTS_CSV="$DATASET_DIR/inputs.csv"
# META_CSV="$DATASET_DIR/meta.csv"

# if [ ! -f "$INPUTS_CSV" ] || [ ! -f "$META_CSV" ]; then
#   echo "inputs.csv or meta.csv not found under $DATASET_DIR"
#   exit 1
# fi

# python3 - "$META_CSV" "$INPUTS_CSV" << 'PY'
# import csv, sys
# from pathlib import Path

# meta_path = Path(sys.argv[1])
# inputs_path = Path(sys.argv[2])

# # classes whose _OE25Dev versions were deleted
# classes = [
#     "AbstractMapTest",
#     "AbstractBidiMapTest",
#     "AbstractMultiSetTest",
#     "DefaultMapEntryTest",
#     "Flat3MapTest",
#     "ListOrderedMapTest",
#     "UnmodifiableMapEntryTest",
#     "AbstractBagTest",
#     "AbstractListTest",
#     "AbstractMultiValuedMapTest",
#     "AbstractOrderedBidiMapTest",
#     "AbstractOrderedMapTest",
#     "ComparatorChainTest",
#     "LinkedMapTest",
#     "ListOrderedMap2Test",
# ]

# # backup originals once more
# meta_backup = meta_path.with_suffix(meta_path.suffix + ".bak_classes")
# inputs_backup = inputs_path.with_suffix(inputs_path.suffix + ".bak_classes")
# meta_backup.write_text(meta_path.read_text(encoding="utf-8", errors="ignore"), encoding="utf-8")
# inputs_backup.write_text(inputs_path.read_text(encoding="utf-8", errors="ignore"), encoding="utf-8")

# # load meta and collect ids to remove
# with meta_path.open(newline="", encoding="utf-8") as f:
#     meta_rows = list(csv.reader(f))

# if not meta_rows:
#     sys.exit(0)

# meta_header = meta_rows[0]
# bad_ids = set()

# for row in meta_rows[1:]:
#     if len(row) < 3:
#         continue
#     test_class = row[2]
#     if test_class in classes:
#         bad_ids.add(row[0])

# # rewrite meta.csv without those ids
# with meta_path.open("w", newline="", encoding="utf-8") as f:
#     w = csv.writer(f)
#     w.writerow(meta_header)
#     for row in meta_rows[1:]:
#         if not row:
#             continue
#         if row[0] in bad_ids:
#             continue
#         w.writerow(row)

# # load inputs.csv as real CSV (handles multiline cells)
# with inputs_path.open(newline="", encoding="utf-8") as f:
#     input_rows = list(csv.reader(f))

# if not input_rows:
#     sys.exit(0)

# inputs_header = input_rows[0]

# # rewrite inputs.csv without those ids
# with inputs_path.open("w", newline="", encoding="utf-8") as f:
#     w = csv.writer(f)
#     w.writerow(inputs_header)
#     for row in input_rows[1:]:
#         if not row:
#             continue
#         if row[0] in bad_ids:
#             continue
#         w.writerow(row)
# PY

# echo "done"


# ROOT_DIR="$(cd "$(dirname "$0")/../.." && pwd)"
# PROJECT_ROOT="$ROOT_DIR/projects_decomposed/commons-collections4"
# DATASET_DIR="$PROJECT_ROOT/dataset"
# INPUTS_CSV="$DATASET_DIR/inputs.csv"
# META_CSV="$DATASET_DIR/meta.csv"

# if [ ! -f "$INPUTS_CSV" ] || [ ! -f "$META_CSV" ]; then
#   echo "inputs.csv or meta.csv not found under $DATASET_DIR"
#   exit 1
# fi

# if [ $# -ge 1 ]; then
#   LOG_FILE="$1"
# else
#   LOG_DIR="$ROOT_DIR/logs/commons-collections4"
#   if [ ! -d "$LOG_DIR" ]; then
#     echo "Log dir not found: $LOG_DIR"
#     exit 1
#   fi
#   LOG_FILE="$(ls -1t "$LOG_DIR"/*.log 2>/dev/null | head -n 1 || true)"
#   if [ -z "$LOG_FILE" ]; then
#     echo "No .log files found in $LOG_DIR"
#     exit 1
#   fi
# fi

# echo "ROOT_DIR    = $ROOT_DIR"
# echo "PROJECT     = $PROJECT_ROOT"
# echo "DATASET_DIR = $DATASET_DIR"
# echo "INPUTS      = $INPUTS_CSV"
# echo "META        = $META_CSV"
# echo "LOG         = $LOG_FILE"
# echo

# python3 - "$LOG_FILE" "$META_CSV" "$INPUTS_CSV" << 'PY'
# import csv, re, sys
# from pathlib import Path

# log_path, meta_path, inputs_path = sys.argv[1:4]

# log_text = Path(log_path).read_text(encoding="utf-8", errors="ignore")
# # strip ANSI color codes
# log_text = re.sub(r'\x1b\[[0-9;]*m', '', log_text)

# failing = set()
# for line in log_text.splitlines():
#     m = re.match(r'\[ERROR\]\s+([A-Za-z0-9_]+)_OE25Dev\.([A-Za-z0-9_]+_oe)\b', line)
#     if m:
#         cls, name = m.groups()
#         failing.add((cls, name))

# if not failing:
#     sys.exit(0)

# meta_p = Path(meta_path)
# inputs_p = Path(inputs_path)

# meta_backup = meta_p.with_suffix(meta_p.suffix + ".bak2")
# inputs_backup = inputs_p.with_suffix(inputs_p.suffix + ".bak2")
# meta_backup.write_text(meta_p.read_text(encoding="utf-8", errors="ignore"), encoding="utf-8")
# inputs_backup.write_text(inputs_p.read_text(encoding="utf-8", errors="ignore"), encoding="utf-8")

# # load meta.csv and collect bad ids
# with meta_p.open(newline="", encoding="utf-8") as f:
#     meta_rows = list(csv.reader(f))

# if not meta_rows:
#     sys.exit(0)

# meta_header = meta_rows[0]
# bad_ids = set()

# for row in meta_rows[1:]:
#     if len(row) < 4:
#         continue
#     cls, name = row[2], row[3]
#     if (cls, name) in failing:
#         bad_ids.add(row[0])

# # rewrite meta.csv without bad ids
# with meta_p.open("w", newline="", encoding="utf-8") as f:
#     w = csv.writer(f)
#     w.writerow(meta_header)
#     for row in meta_rows[1:]:
#         if row and row[0] not in bad_ids:
#             w.writerow(row)

# # load inputs.csv as proper CSV (handles multi-line cells)
# with inputs_p.open(newline="", encoding="utf-8") as f:
#     inputs_rows = list(csv.reader(f))

# if not inputs_rows:
#     sys.exit(0)

# inputs_header = inputs_rows[0]

# # rewrite inputs.csv without bad ids
# with inputs_p.open("w", newline="", encoding="utf-8") as f:
#     w = csv.writer(f)
#     w.writerow(inputs_header)
#     for row in inputs_rows[1:]:
#         if row and row[0] not in bad_ids:
#             w.writerow(row)
# PY

# echo "done"



cd "$PWD/projects_decomposed/commons-collections4"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [
".",
]

patterns = []
for full in fail_list:
    cls, name = full.split(".", 1)
    if name.endswith("*"):
        name = name[:-1]  # strip '*'
    patterns.append((cls, name))

bad_ids = set()
meta_rows = []
removed_meta = 0

with meta_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    fieldnames = r.fieldnames
    for row in r:
        test_class = row["test_class"]
        test_name = row["test_name"]
        matched = False
        for cls_pattern, name_prefix in patterns:
            if test_class == cls_pattern and test_name.startswith(name_prefix):
                matched = True
                break
        if matched:
            bad_ids.add(row["id"])
            removed_meta += 1
        else:
            meta_rows.append(row)

print("removed from meta:", removed_meta)
print("bad_ids:", len(bad_ids))

with meta_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=fieldnames)
    w.writeheader()
    w.writerows(meta_rows)

inputs_rows = []
removed_inputs = 0

with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    in_fields = r.fieldnames
    for row in r:
        if row["id"] in bad_ids:
            removed_inputs += 1
            continue
        inputs_rows.append(row)

print("removed from inputs:", removed_inputs)

with inputs_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=in_fields)
    w.writeheader()
    w.writerows(inputs_rows)
PY
