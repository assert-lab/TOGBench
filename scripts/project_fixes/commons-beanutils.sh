cd "$PWD/projects_decomposed/commons-beanutils"
echo $PWD

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset_final")
meta_path = dataset / "meta_final.csv"
inputs_path = dataset / "inputs_final.csv"

fail_list = [
"BasicDynaBeanTestCase.testSerialization_3_oe",
"MemoryLeakTestCase.testPropertyUtilsBean_descriptorsCache_memoryLeak_6_oe",
"MemoryLeakTestCase.testPropertyUtilsBean_mappedDescriptorsCache_memoryLeak_7_oe",
"MemoryLeakTestCase.testMappedPropertyDescriptor_MappedMethodReference1_10_oe",
"MemoryLeakTestCase.testMappedPropertyDescriptor_MappedMethodReference1_11_oe",
"MemoryLeakTestCase.testMappedPropertyDescriptor_MappedMethodReference1_12_oe",
"MemoryLeakTestCase.testMappedPropertyDescriptor_MappedMethodReference1_13_oe",
"MemoryLeakTestCase.testMappedPropertyDescriptor_MappedMethodReference2_10_oe",
"MemoryLeakTestCase.testMappedPropertyDescriptor_MappedMethodReference2_11_oe",
"MemoryLeakTestCase.testMappedPropertyDescriptor_MappedMethodReference2_12_oe",
"MemoryLeakTestCase.testMappedPropertyDescriptor_MappedMethodReference2_13_oe",
"MemoryLeakTestCase.testMethodUtils_cache_memoryLeak_6_oe",
"MemoryLeakTestCase.testWrapDynaClass_dynaClasses_memoryLeak_6_oe",
"MemoryLeakTestCase.testConvertUtilsBean_converters_memoryLeak_6_oe",
"MemoryLeakTestCase.testLocaleConvertUtilsBean_converters_memoryLeak_6_oe",
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
