cd "$PWD/projects_decomposed/commons-jcs3"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"CacheTest.getPut_1_oe",
"CacheTest.getPut_2_oe",
"CacheTest.getPut_3_oe",
"CacheTest.getPut_4_oe",
"CacheTest.listeners_1_oe",
"CacheTest.listeners_2_oe",
"CacheTest.listeners_3_oe",
"CacheTest.listeners_4_oe",
"CacheTest.listeners_5_oe",
"CacheTest.listeners_6_oe",
"CacheTest.loader_1_oe",
"ExpiryListenerTest.listener_1_oe",
"ExpiryListenerTest.listener_2_oe",
"ExpiryListenerTest.listener_3_oe",
"ImmediateExpiryTest.immediate_2_oe",
"NotSerializableTest.run_1_oe",
"NotSerializableTest.run_2_oe",
"NotSerializableTest.run_3_oe",
"NotSerializableTest.run_4_oe",
"CacheLoaderAdapterTest.checkLoadAll_3_oe",
"CacheLoaderAdapterTest.checkLoadAll_6_oe",
"OpenJPAJCacheDataCacheTest.query_1_oe",
"OpenJPAJCacheDataCacheTest.query_2_oe",
"OpenJPAJCacheDataCacheTest.query_3_oe",
"OpenJPAJCacheDataCacheTest.query_4_oe",
"OpenJPAJCacheDataCacheTest.query_5_oe",
"OpenJPAJCacheDataCacheTest.query_6_oe",


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
