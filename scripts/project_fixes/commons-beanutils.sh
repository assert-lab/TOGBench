cd "$PWD/projects_decomposed/commons-beanutils"
echo $PWD

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [
    "LocaleBeanificationTestCase.testMemoryTestMethodology_2_oe",
    "LocaleBeanificationTestCase.testMemoryTestMethodology_1_oe",
    "BasicDynaBeanTestCase.testSerialization_3_oe",
    "LazyDynaMapTestCase.testMappedPropertyUtils_4_oe",
    "LazyDynaMapTestCase.testMappedPropertyUtils_5_oe",
    "LazyDynaMapTestCase.testIndexedPropertyUtils_4_oe",
    "LazyDynaMapTestCase.testIndexedPropertyUtils_5_oe",

    "BeanificationTestCase.testMemoryTestMethodology_1_oe",
    "BeanificationTestCase.testMemoryTestMethodology_2_oe",
    "DynaBeanUtilsTestCase.testPopulateArrayProperties_10_oe",

    "DynaBeanUtilsTestCase.testGetArrayProperty_3_oe",
    "DynaBeanUtilsTestCase.testGetArrayProperty_4_oe",
    "DynaBeanUtilsTestCase.testGetIndexedProperty1_3_oe",
    "DynaBeanUtilsTestCase.testGetIndexedProperty1_4_oe",
    "DynaBeanUtilsTestCase.testGetIndexedProperty2_3_oe",
    "DynaBeanUtilsTestCase.testGetIndexedProperty2_4_oe",
    "DynaBeanUtilsTestCase.testGetNestedProperty_2_oe",
    "DynaBeanUtilsTestCase.testGetGeneralProperty_3_oe",
    "DynaBeanUtilsTestCase.testPopulateScalar_10_oe",
    "DynaBeanUtilsTestCase.testPopulateNested_9_oe",
    "DynaBeanUtilsTestCase.testPopulateMapped_5_oe",
    "DynaBeanUtilsTestCase.testGetNestedProperty_3_oe",
    "DynaBeanUtilsTestCase.testGetGeneralProperty_2_oe",
    "DynaBeanUtilsTestCase.testGetSimpleProperty_2_oe",
    "DynaBeanUtilsTestCase.testGetSimpleProperty_3_oe",
    "DynaBeanUtilsTestCase.testPopulateArrayElements_11_oe",
    "Jira349TestCase.testIssue_BEANUTILS_349_PropertyUtils_copyProperties_1_oe",
    "Jira369TestCase.testBeanUtilsGetProperty_aRatedCd_3_oe",

    "LazyDynaBeanTestCase.testMappedPropertyUtils_4_oe",
    "LazyDynaBeanTestCase.testMappedPropertyUtils_5_oe",    

    "LazyDynaBeanTestCase.testIndexedPropertyUtils_4_oe",
    "LazyDynaBeanTestCase.testIndexedPropertyUtils_5_oe",
    "BeanUtilsTestCase.testGetArrayProperty_7_oe",
    "BeanUtilsTestCase.testGetArrayProperty_8_oe",
    "BeanUtilsTestCase.testGetIndexedProperty1_3_oe",
    "BeanUtilsTestCase.testGetIndexedProperty1_4_oe",
    "BeanUtilsTestCase.testGetIndexedProperty2_3_oe",
    "BeanUtilsTestCase.testGetIndexedProperty2_4_oe",
    "BeanUtilsTestCase.testGetNestedProperty_2_oe",
    "BeanUtilsTestCase.testGetNestedProperty_3_oe",
    "BeanUtilsTestCase.testGetGeneralProperty_2_oe",
    "BeanUtilsTestCase.testGetGeneralProperty_3_oe",
    "BeanUtilsTestCase.testPopulateNested_10_oe",
    "BeanUtilsTestCase.testPopulateMapped_5_oe",
    "BeanUtilsTestCase.testPopulateArrayElements_11_oe",
    "BeanUtilsTestCase.testGetSimpleProperty_3_oe",
    "BeanUtilsTestCase.testGetSimpleProperty_2_oe",
    "BeanUtilsTestCase.testPopulateArrayProperties_10_oe",
    "BeanUtilsTestCase.testPopulateScalar_13_oe",

    "Jira368TestCase.testBeanUtilsSetProperty_NullBean_1_oe",
    "BeanIntrospectionDataTestCase.testGetWriteMethodUndefined_2_oe",
"BeanificationTestCase.testMemoryLeak2_4_oe",
"BeanificationTestCase.testMemoryLeak_5_oe",
"DynaBeanMapDecoratorTestCase.testPut_3_oe",
"DynaBeanMapDecoratorTestCase.testPut_4_oe",
"DynaBeanMapDecoratorTestCase.testValues_2_oe",
"LazyDynaListTestCase.testToArrayDynaBeans_2_oe",
"LazyDynaListTestCase.testToArrayMapType_2_oe",
"LazyDynaListTestCase.testToArrayOtherType_2_oe",
"MethodUtilsTestCase.testClearCache_2_oe",
"MethodUtilsTestCase.testSetCacheMethods_2_oe",
"LocaleBeanificationTestCase.testMemoryLeak2_4_oe",
"LocaleBeanificationTestCase.testMemoryLeak_4_oe",


    "DynaPropertyUtilsTestCase.*",
    "PropertyUtilsTestCase.*",

    # "MacOsPeterFTPEntryParserTest.testParseFieldsOnFile*",
    

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
