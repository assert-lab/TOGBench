cd "$PWD/projects_decomposed/commons-pool2"
echo $PWD

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"TestGenericObjectPool.testMaxIdleZeroUnderLoad_1_oe",
"TestGenericObjectPool.testMaxTotalUnderLoad_1_oe",
"TestGenericObjectPool.testExceptionOnActivateDuringBorrow_5_oe",
"TestGenericObjectPool.testExceptionInValidationDuringEviction_3_oe",
"TestGenericObjectPool.testFIFO_2_oe",
"TestGenericObjectPool.testFIFO_3_oe",
"TestGenericObjectPool.testFIFO_4_oe",
"TestGenericObjectPool.testFIFO_5_oe",
"TestGenericObjectPool.testFIFO_6_oe",
"TestGenericObjectPool.testLIFO_2_oe",
"TestGenericObjectPool.testLIFO_3_oe",
"TestGenericObjectPool.testLIFO_4_oe",
"TestGenericObjectPool.testLIFO_6_oe",
"TestGenericObjectPool.testAbandonedPool_1_oe",
"TestGenericObjectPool.testDefaultConfiguration_1_serial_19_oe_1_oe",
"TestGenericObjectPool.testSetConfig_1_serial_13_oe_1_oe",
"TestGenericObjectPool.testBorrowTimings_1_oe",
"TestGenericObjectPool.testBorrowTimings_2_oe",
"TestGenericObjectPool.testBorrowTimings_3_oe",
"TestGenericObjectPool.testBorrowTimings_4_oe",
"TestGenericObjectPool.testBorrowTimings_5_oe",
"TestGenericObjectPool.testBorrowTimings_6_oe",
"TestGenericObjectPool.testBorrowTimings_7_oe",
"TestGenericObjectPool.testBorrowTimings_8_oe",
"TestGenericObjectPool.testBorrowTimings_9_oe",
"TestGenericObjectPool.testSetConfig_1_serial_1_oe_1_oe",
"TestGenericObjectPool.testExceptionOnDestroyDuringBorrow_1_oe",
"TestGenericObjectPool.testExceptionOnDestroyDuringBorrow_2_oe",
"TestGenericObjectPool.testExceptionOnDestroyDuringBorrow_3_oe",
"TestGenericObjectPool.testWhenExhaustedFail_1_oe",
"TestGenericObjectPool.testWhenExhaustedFail_2_oe",
"TestGenericObjectPool.testWhenExhaustedFail_3_oe",
"TestGenericObjectPool.testDefaultConfiguration_1_serial_8_oe_1_oe",
"TestGenericObjectPool.testSetConfig_1_serial_8_oe_1_oe",
"TestGenericObjectPool.testSetConfig_2_serial_10_oe_1_oe",
"TestGenericObjectPool.testMaxIdle_1_oe",
"TestGenericObjectPool.testMaxIdle_2_oe",
"TestGenericObjectPool.testMaxIdle_3_oe",
"TestGenericObjectPool.testMaxIdle_4_oe",
"TestGenericObjectPool.testBrokenFactoryShouldNotBlockPool_1_oe",
"TestGenericObjectPool.testBrokenFactoryShouldNotBlockPool_2_oe",
"TestGenericObjectPool.testBrokenFactoryShouldNotBlockPool_3_oe",
"TestGenericObjectPool.testBrokenFactoryShouldNotBlockPool_4_oe",
"TestGenericObjectPool.testSetConfig_2_serial_5_oe_1_oe",
"TestGenericObjectPool.testDefaultConfiguration_1_serial_14_oe_1_oe",
"TestGenericObjectPool.testMaxTotalZero_1_oe",
"TestGenericObjectPool.testSetConfig_2_serial_17_oe_1_oe",
"TestGenericObjectPool.testLIFO_1_oe",
"TestGenericObjectPool.testLIFO_5_oe",
"TestGenericObjectPool.testMaxIdleZero_1_oe",
"TestGenericObjectPool.testMaxIdleZero_2_oe",
"TestGenericObjectPool.testMaxIdleZero_3_oe",
"TestGenericObjectPool.testMaxIdleZero_4_oe",
"TestGenericObjectPool.testDefaultConfiguration_1_serial_3_oe_1_oe",
"TestGenericObjectPool.testSetConfig_1_serial_15_oe_1_oe",
"TestGenericObjectPool.testSetConfig_1_serial_3_oe_1_oe",
"TestGenericObjectPool.testNegativeMaxTotal_1_oe",
"TestGenericObjectPool.testEviction_10_oe",
"TestGenericObjectPool.testEviction_11_oe",
"TestGenericObjectPool.testEviction_12_oe",
"TestGenericObjectPool.testMinIdle_1_oe",
"TestGenericObjectPool.testMinIdle_2_oe",
"TestGenericObjectPool.testMinIdle_3_oe",
"TestGenericObjectPool.testMinIdle_4_oe",
"TestGenericObjectPool.testSwallowedExceptionListener_1_oe",
"TestGenericObjectPool.testSwallowedExceptionListener_2_oe",
"TestGenericObjectPool.testAppendStats_1_oe",
"TestGenericObjectPool.testAppendStats_2_oe",
"TestGenericObjectPool.testInlined_1_oe",
"TestGenericObjectPool.testInlined_2_oe",
"TestGenericObjectPool.testInlined_3_oe",
"TestGenericObjectPool.testInlined_4_oe",
"TestGenericObjectPool.testInlined_5_oe",
"TestGenericObjectPool.testInlined_6_oe",
"TestGenericObjectPool.testInlined_7_oe",
"TestGenericObjectPool.testInlined_8_oe",
"TestGenericObjectPool.testInlined_9_oe",
"TestGenericObjectPool.testSetConfig_2_serial_12_oe_1_oe",
"TestGenericObjectPool.testSetConfig_1_oe_1_oe",
"TestGenericObjectPool.testSetConfig_1_oe_2_oe",
"TestGenericObjectPool.testSetConfig_1_oe_3_oe",
"TestGenericObjectPool.testSetConfig_1_oe_4_oe",
"TestGenericObjectPool.testSetConfig_1_oe_5_oe",
"TestGenericObjectPool.testSetConfig_1_oe_6_oe",
"TestGenericObjectPool.testSetConfig_1_oe_7_oe",
"TestGenericObjectPool.testSetConfig_1_oe_8_oe",
"TestGenericObjectPool.testSetConfig_1_oe_9_oe",
"TestGenericObjectPool.testSettersAndGetters_1_oe",
"TestGenericObjectPool.testSettersAndGetters_2_oe",
"TestGenericObjectPool.testSettersAndGetters_3_oe",
"TestGenericObjectPool.testSettersAndGetters_4_oe",
"TestGenericObjectPool.testSettersAndGetters_5_oe",
"TestGenericObjectPool.testSettersAndGetters_6_oe",
"TestGenericObjectPool.testSettersAndGetters_7_oe",
"TestGenericObjectPool.testSettersAndGetters_8_oe",
"TestGenericObjectPool.testSettersAndGetters_9_oe",
"TestGenericObjectPool.testSetConfig_2_serial_7_oe_1_oe",

"TestSoftRefOutOfMemory.*",

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
