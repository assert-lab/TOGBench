cd "$PWD/projects_decomposed/commons-lang3"
echo $PWD

find . -type f -name "*.csv" -print0 \
| xargs -0 sed -i.bak 's/iterator0(/iterator(/g'

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset_final")
meta_path = dataset / "meta_final.csv"
inputs_path = dataset / "inputs_final.csv"

fail_list = [
"DateFormatUtilsTest.testSMTP_1_oe_1_oe",
"DateFormatUtilsTest.testSMTP_1_oe_2_oe",
"DateFormatUtilsTest.testSMTP_1_oe_3_oe",
"DateFormatUtilsTest.testSMTP_2_oe_1_oe",
"DateFormatUtilsTest.testSMTP_2_oe_2_oe",
"DateFormatUtilsTest.testSMTP_2_oe_3_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateInstance_2_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateInstance_4_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateInstance_5_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateTimeInstance_2_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateTimeInstance_4_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateTimeInstance_5_oe",
"FastDateFormatTest.test_getInstance_String_Locale_1_oe",

"ValidateTest.*",
]

patterns = []
for full in fail_list:
    cls, name = full.split(".", 1)
    if name.endswith("*"):
        name = name[:-1]  # strip '*'
    patterns.append((cls, name))

bad_ids = set()
meta_rows = []
failed_meta_rows = []
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
            failed_meta_rows.append(row)
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
failed_inputs_rows = []
removed_inputs = 0

with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    in_fields = r.fieldnames
    for row in r:
        if row["id"] in bad_ids:
            failed_inputs_rows.append(row)
            removed_inputs += 1
            continue
        inputs_rows.append(row)

print("removed from inputs:", removed_inputs)

with inputs_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=in_fields)
    w.writeheader()
    w.writerows(inputs_rows)

def merge_dedup_by_id(path, base_fieldnames, new_rows):
    if not new_rows and not path.exists():
        return

    merged = {}
    all_fields = list(base_fieldnames or [])

    def add_fields_from_row(row):
        nonlocal all_fields
        for k in row.keys():
            if k not in all_fields:
                all_fields.append(k)

    if path.exists():
        with path.open(newline="", encoding="utf-8") as f:
            r = csv.DictReader(f)
            if r.fieldnames:
                for k in r.fieldnames:
                    if k not in all_fields:
                        all_fields.append(k)
            for row in r:
                rid = (row.get("id") or "").strip()
                if rid:
                    add_fields_from_row(row)
                    merged[rid] = row

    for row in new_rows:
        rid = (row.get("id") or "").strip()
        if rid:
            add_fields_from_row(row)
            merged[rid] = row

    with path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=all_fields, lineterminator="\n", extrasaction="ignore")
        w.writeheader()
        for rid in sorted(merged.keys()):
            rr = dict(merged[rid])
            for k in all_fields:
                rr.setdefault(k, "")
            w.writerow(rr)


meta_failed_path = dataset / "meta_mvn_failed.csv"
merge_dedup_by_id(meta_failed_path, fieldnames, failed_meta_rows)

inputs_failed_path = dataset / "inputs_mvn_failed.csv"
merge_dedup_by_id(inputs_failed_path, in_fields, failed_inputs_rows)


PY


# python3 - <<'PY'
# import csv
# import shutil
# from pathlib import Path

# dataset = Path("dataset")
# meta_path = dataset / "meta.csv"
# inputs_path = dataset / "inputs.csv"

# meta_rows = []
# id2meta = {}

# with meta_path.open(newline="", encoding="utf-8") as f:
#     r = csv.DictReader(f)
#     meta_fields = r.fieldnames
#     for row in r:
#         meta_rows.append(row)
#         id2meta[row["id"]] = row

# bad_ids = set()

# with inputs_path.open(newline="", encoding="utf-8") as f:
#     r = csv.DictReader(f)
#     inputs_fields = r.fieldnames
#     for row in r:
#         if "return0" in (row.get("test_prefix") or ""):
#             bad_ids.add(row["id"])

# print("bad_ids", len(bad_ids))
# for bid in sorted(bad_ids):
#     m = id2meta.get(bid, {})
#     print(bid, m.get("test_class", "?"), m.get("test_name", "?"), sep=",")

# if not bad_ids:
#     raise SystemExit(0)

# shutil.copy2(meta_path, meta_path.with_suffix(".csv.bak"))
# shutil.copy2(inputs_path, inputs_path.with_suffix(".csv.bak"))

# new_meta = [row for row in meta_rows if row["id"] not in bad_ids]
# with meta_path.open("w", newline="", encoding="utf-8") as f:
#     w = csv.DictWriter(f, fieldnames=meta_fields)
#     w.writeheader()
#     w.writerows(new_meta)

# new_inputs = []
# with inputs_path.open(newline="", encoding="utf-8") as f:
#     r = csv.DictReader(f)
#     for row in r:
#         if row["id"] not in bad_ids:
#             new_inputs.append(row)

# with inputs_path.open("w", newline="", encoding="utf-8") as f:
#     w = csv.DictWriter(f, fieldnames=inputs_fields)
#     w.writeheader()
#     w.writerows(new_inputs)

# print("removed_meta", len(meta_rows) - len(new_meta))
# print("removed_inputs", removed_inputs := len(bad_ids))
# PY

