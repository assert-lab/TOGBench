cd "$PWD/projects_decomposed/commons-jexl3"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"DoWhileTest.testSimpleWhileFalse_2_oe",


"ArithmeticOperatorTest.testInterval_3_oe",
"ArithmeticOperatorTest.testInterval_4_oe",
"ArithmeticOperatorTest.testInterval_7_oe",
"ArithmeticOperatorTest.testInterval_8_oe",
"ClassCreatorTest.testMany_3_oe",
"ClassCreatorTest.testMany_4_oe",
"ClassCreatorTest.testMany_6_oe",
"DoWhileTest.testWhileEmptyStmtBody_1_oe",
"Issues200Test.test286_1_oe",
"Issues300Test.testIssue304_2_oe",
"JexlTest.testAssignment_2_oe",
"JexlTest.testAssignment_4_oe",
"LexicalTest.testScopeFrame_2_oe",
"LexicalTest.testScopeFrame_3_oe",
"PublicFieldsTest.testSetInnerDouble_2_oe",
"PublicFieldsTest.testSetInt_2_oe",
"PublicFieldsTest.testSetString_2_oe",
"SideEffectTest.testSideEffectAntishArray_10_oe",
"SideEffectTest.testSideEffectAntishArray_12_oe",
"SideEffectTest.testSideEffectAntishArray_14_oe",
"SideEffectTest.testSideEffectAntishArray_16_oe",
"SideEffectTest.testSideEffectAntishArray_2_oe",
"SideEffectTest.testSideEffectAntishArray_4_oe",
"SideEffectTest.testSideEffectAntishArray_6_oe",
"SideEffectTest.testSideEffectAntishArray_8_oe",
"SideEffectTest.testSideEffectArray_10_oe",
"SideEffectTest.testSideEffectArray_12_oe",
"SideEffectTest.testSideEffectArray_14_oe",
"SideEffectTest.testSideEffectArray_16_oe",
"SideEffectTest.testSideEffectArray_2_oe",
"SideEffectTest.testSideEffectArray_4_oe",
"SideEffectTest.testSideEffectArray_6_oe",
"SideEffectTest.testSideEffectArray_8_oe",
"SideEffectTest.testSideEffectBeanContainer_10_oe",
"SideEffectTest.testSideEffectBeanContainer_12_oe",
"SideEffectTest.testSideEffectBeanContainer_14_oe",
"SideEffectTest.testSideEffectBeanContainer_16_oe",
"SideEffectTest.testSideEffectBeanContainer_18_oe",
"SideEffectTest.testSideEffectBeanContainer_2_oe",
"SideEffectTest.testSideEffectBeanContainer_4_oe",
"SideEffectTest.testSideEffectBeanContainer_6_oe",
"SideEffectTest.testSideEffectBeanContainer_8_oe",
"SideEffectTest.testSideEffectBean_10_oe",
"SideEffectTest.testSideEffectBean_12_oe",
"SideEffectTest.testSideEffectBean_14_oe",
"SideEffectTest.testSideEffectBean_16_oe",
"SideEffectTest.testSideEffectBean_2_oe",
"SideEffectTest.testSideEffectBean_4_oe",
"SideEffectTest.testSideEffectBean_6_oe",
"SideEffectTest.testSideEffectBean_8_oe",
"SideEffectTest.testSideEffectDotArray_10_oe",
"SideEffectTest.testSideEffectDotArray_12_oe",
"SideEffectTest.testSideEffectDotArray_14_oe",
"SideEffectTest.testSideEffectDotArray_16_oe",
"SideEffectTest.testSideEffectDotArray_2_oe",
"SideEffectTest.testSideEffectDotArray_4_oe",
"SideEffectTest.testSideEffectDotArray_6_oe",
"SideEffectTest.testSideEffectDotArray_8_oe",
"SideEffectTest.testSideEffectVarDots_10_oe",
"SideEffectTest.testSideEffectVarDots_12_oe",
"SideEffectTest.testSideEffectVarDots_14_oe",
"SideEffectTest.testSideEffectVarDots_16_oe",
"SideEffectTest.testSideEffectVarDots_2_oe",
"SideEffectTest.testSideEffectVarDots_4_oe",
"SideEffectTest.testSideEffectVarDots_6_oe",
"SideEffectTest.testSideEffectVarDots_8_oe",
"SideEffectTest.testSideEffectVar_10_oe",
"SideEffectTest.testSideEffectVar_12_oe",
"SideEffectTest.testSideEffectVar_14_oe",
"SideEffectTest.testSideEffectVar_16_oe",
"SideEffectTest.testSideEffectVar_2_oe",
"SideEffectTest.testSideEffectVar_4_oe",
"SideEffectTest.testSideEffectVar_6_oe",
"SideEffectTest.testSideEffectVar_8_oe",
"RangeTest.testRanges_27_oe",
"RangeTest.testRanges_30_oe",
"MiscIntrospectionTest.testArrayIterator_7_oe",
"JexlScriptEngineTest.testScripting_7_oe",
"JexlScriptEngineTest.testScripting_8_oe",
"ClassCreatorTest.testFunctorOne_4_oe",
"ClassCreatorTest.testFunctorOne_5_oe",
"ClassCreatorTest.testFunctorOne_6_oe",
"ClassCreatorTest.testFunctorOne_7_oe",
"ClassCreatorTest.testFunctorOne_8_oe",
"ClassCreatorTest.testFunctorOne_9_oe",
"ClassCreatorTest.testMany_5_oe",
"ContextNamespaceTest.testNamespacePragma_1_oe",
"DoWhileTest.testSimpleWhileFalse_1_oe",
"Issues200Test.test279_3_oe",
"Issues200Test.test279_4_oe",


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
