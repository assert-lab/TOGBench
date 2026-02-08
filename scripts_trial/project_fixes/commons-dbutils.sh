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




# cd "$PWD/projects_decomposed/commons-dbutils"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"BasicRowProcessorTest.testToArray_10_oe",
"BasicRowProcessorTest.testToArray_11_oe",
"BasicRowProcessorTest.testToArray_2_oe",
"BasicRowProcessorTest.testToArray_3_oe",
"BasicRowProcessorTest.testToArray_4_oe",
"BasicRowProcessorTest.testToArray_5_oe",
"BasicRowProcessorTest.testToArray_6_oe",
"BasicRowProcessorTest.testToArray_7_oe",
"BasicRowProcessorTest.testToArray_8_oe",
"BasicRowProcessorTest.testToArray_9_oe",
"BasicRowProcessorTest.testToBean_10_oe",
"BasicRowProcessorTest.testToBean_11_oe",
"BasicRowProcessorTest.testToBean_12_oe",
"BasicRowProcessorTest.testToBean_13_oe",
"BasicRowProcessorTest.testToBean_14_oe",
"BasicRowProcessorTest.testToBean_15_oe",
"BasicRowProcessorTest.testToBean_16_oe",
"BasicRowProcessorTest.testToBean_17_oe",
"BasicRowProcessorTest.testToBean_18_oe",
"BasicRowProcessorTest.testToBean_2_oe",
"BasicRowProcessorTest.testToBean_3_oe",
"BasicRowProcessorTest.testToBean_4_oe",
"BasicRowProcessorTest.testToBean_5_oe",
"BasicRowProcessorTest.testToBean_6_oe",
"BasicRowProcessorTest.testToBean_7_oe",
"BasicRowProcessorTest.testToBean_8_oe",
"BasicRowProcessorTest.testToBean_9_oe",
"BasicRowProcessorTest.testToMapOrdering_10_oe",
"BasicRowProcessorTest.testToMapOrdering_11_oe",
"BasicRowProcessorTest.testToMapOrdering_12_oe",
"BasicRowProcessorTest.testToMapOrdering_2_oe",
"BasicRowProcessorTest.testToMapOrdering_3_oe",
"BasicRowProcessorTest.testToMapOrdering_4_oe",
"BasicRowProcessorTest.testToMapOrdering_5_oe",
"BasicRowProcessorTest.testToMapOrdering_6_oe",
"BasicRowProcessorTest.testToMapOrdering_7_oe",
"BasicRowProcessorTest.testToMapOrdering_8_oe",
"BasicRowProcessorTest.testToMapOrdering_9_oe",
"BasicRowProcessorTest.testToMap_10_oe",
"BasicRowProcessorTest.testToMap_2_oe",
"BasicRowProcessorTest.testToMap_3_oe",
"BasicRowProcessorTest.testToMap_4_oe",
"BasicRowProcessorTest.testToMap_5_oe",
"BasicRowProcessorTest.testToMap_6_oe",
"BasicRowProcessorTest.testToMap_7_oe",
"BasicRowProcessorTest.testToMap_8_oe",
"BasicRowProcessorTest.testToMap_9_oe",
"BeanProcessorTest.testProcessWithPopulateBean_2_oe",
"BeanProcessorTest.testProcessWithPopulateBean_3_oe",
"BeanProcessorTest.testProcessWithPopulateBean_4_oe",
"BeanProcessorTest.testProcessWithPopulateBean_5_oe",
"BeanProcessorTest.testProcessWithPopulateBean_6_oe",
"BeanProcessorTest.testProcessWithPopulateBean_7_oe",
"BeanProcessorTest.testProcessWithToBean_2_oe",
"BeanProcessorTest.testProcessWithToBean_3_oe",
"BeanProcessorTest.testProcessWithToBean_4_oe",
"BeanProcessorTest.testProcessWithToBean_5_oe",
"BeanProcessorTest.testProcessWithToBean_6_oe",
"BeanProcessorTest.testProcessWithToBean_7_oe"

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
