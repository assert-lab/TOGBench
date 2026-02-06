cd "$PWD/projects_decomposed/commons-lang3"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"ComparableUtilsTest.betweenExclusive_returns_false_1_oe",
"ComparableUtilsTest.between_returns_true_1_oe",
"ComparableUtilsTest.greaterThanOrEqualTo_returns_true_1_oe",
"ComparableUtilsTest.lessThan_returns_false_1_oe",
"ComparableUtilsTest.betweenExclusive_returns_true_1_oe",
"ComparableUtilsTest.between_returns_false_1_oe",
"ComparableUtilsTest.equalTo_returns_false_1_oe",
"ComparableUtilsTest.greaterThan_returns_false_1_oe",
"ComparableUtilsTest.lessThanOrEqualTo_returns_true_1_oe",

"FailableFunctionsTest.*",

"SerializationUtilsTest.testSerializeIOException_2_oe",
"SerializationUtilsTest.testDeserializeStreamClassNotFound_2_oe",

"EqualsBuilderTest.testReflectionHierarchyEquals*",
"CharRangeTest.testContainsNullArg_2_oe",
"StreamsTest.testSimpleStreamMapFailing_2_oe",

"MultiBackgroundInitializerTest.testInitializeRuntimeEx_2_oe",
"ConcurrentUtilsTest.testExtractCauseError_2_oe",
"ConcurrentUtilsTest.testExtractCauseUncheckedError_2_oe",
"ConcurrentUtilsTest.testExtractCauseUncheckedUncheckedException_2_oe",
"ConcurrentUtilsTest.testHandleCauseError_2_oe",
"ConcurrentUtilsTest.testHandleCauseUncheckedException_2_oe",
"ConcurrentUtilsTest.testHandleCauseUncheckedChecked_2_oe",
"ConcurrentUtilsTest.testHandleCauseChecked_2_oe",
"ConcurrentUtilsTest.testHandleCauseUncheckedError_2_oe",
"ConcurrentUtilsTest.testHandleCauseUncheckedUncheckedException_2_oe",
"ConcurrentUtilsTest.testInitializeUncheckedEx_2_oe",
"ConcurrentUtilsTest.testCreateIfAbsentUncheckedException_2_oe",
"BackgroundInitializerTest.testGetRuntimeException_2_oe",
"BackgroundInitializerTest.testGetCheckedException_2_oe",
"ArrayUtilsTest.testIndirectEmptyArrayCreation_1_oe",

"ObjectUtilsTest.testCloneOfUncloneable_2_oe",
"ObjectUtilsTest.testPossibleCloneOfUncloneable_2_oe",
"ExceptionUtilsTest.testCatchTechniques*",
"ExceptionUtilsTest.testThrow_2_oe",
"ExceptionUtilsTest.testWrapAndUnwrapCheckedException_2_oe",
"ExceptionUtilsTest.testWrapAndUnwrapError_2_oe",
"ExceptionUtilsTest.testWrapAndUnwrapRuntimeException_2_oe",
"ExceptionUtilsTest.testWrapAndUnwrapThrowable_2_oe",
"EventUtilsTest.testAddEventListenerWithNoAddMethod_2_oe",
"EventUtilsTest.testAddEventListenerWithPrivateAddMethod_2_oe",

"StreamsTest.testSimpleStreamMapFailing_2_oe",

"ArrayUtilsAddTest.*",
"FunctionsTest.*"
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
