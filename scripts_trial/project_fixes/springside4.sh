cd "$PWD/projects_decomposed/springside4"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"CollectionUtilTest.test_16_oe",
"CollectionUtilTest.test_18_oe",
"ListUtilTest.general_12_oe",
"ReflectionUtilTest.getAndSetFieldValue_1_oe",
"ReflectionUtilTest.getAndSetFieldValue_2_oe",
"ReflectionUtilTest.getAndSetFieldValue_3_oe",
"ReflectionUtilTest.getAndSetFieldValue_4_oe",
"ReflectionUtilTest.invokeGetterAndSetter_1_oe",

"ReflectionUtilTest.invokeMethod_1_oe",
"ReflectionUtilTest.invokeMethod_2_oe",
"ReflectionUtilTest.invokeMethod_3_oe",
"ReflectionUtilTest.invokeMethod_4_oe",

"ReflectionUtilTest.invokeGetterAndSetter_2_oe",


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
