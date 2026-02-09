cd "$PWD/projects_decomposed/commons-rng"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"JumpableProvidersParametricTest.testJumpCopyMatchesPreJumpState_1_oe_1_oe",
"JumpableProvidersParametricTest.testLongJumpCopyMatchesPreJumpState_1_oe_1_oe",
"JumpableProvidersParametricTest.testJumpResetsDefaultState_1_oe_1_oe",
"JumpableProvidersParametricTest.testLongJumpResetsDefaultState_1_oe_1_oe",
"NumberFactoryTest.testDoubleGenerationMethods_1_oe_1_oe",
"NumberFactoryTest.testDoubleGenerationMethods_1_oe_2_oe",
"NumberFactoryTest.testDoubleGenerationMethods_2_oe_1_oe",
"NumberFactoryTest.testDoubleGenerationMethods_2_oe_2_oe",
"NumberFactoryTest.testDoubleGenerationMethods_3_oe_1_oe",
"NumberFactoryTest.testDoubleGenerationMethods_3_oe_2_oe",
"NumberFactoryTest.testMakeDoubleFromLong_1_oe_1_oe",
"NumberFactoryTest.testMakeDoubleFromLong_1_oe_2_oe",
"NumberFactoryTest.testMakeDoubleFromIntInt_1_oe_2_oe",
"NumberFactoryTest.testMakeDoubleFromIntInt_1_oe_1_oe",
"ProvidersCommonParametricTest.testUniformNextBytesFullBuffer_1_oe",
"IntProviderTest.testNextBoolean_2_oe",
"LongProviderTest.testNextBoolean_2_oe",
"LongProviderTest.testNextInt_2_oe",
"LongProviderTest.testNextInt_4_oe",
"NumberFactoryTest.testIntToByteArraySignificanceOrder_1_oe",
"NumberFactoryTest.testLongToByteArraySignificanceOrder_1_oe",
"CoordinatesTest.testRequireFiniteWithMessageThrows_3_oe",
"CoordinatesTest.testRequireLengthWithMessageThrows_3_oe",
"CoordinatesTest.testRequireLengthWithMessageThrows_4_oe",
"CoordinatesTest.testRequireLengthWithMessageThrows_5_oe",
"CompositeSamplersTest.testSharedStateObjectSamplerSamplesWithCustomDiscreteSamplerFactory_2_oe",
"CompositeSamplersTest.testSharedStateObjectSamplerSamplesWithCustomSharedStateDiscreteSamplerFactory_2_oe",
"AhrensDieterExponentialSamplerTest.testSamplerWithZeroFromRandomGenerator_2_oe",
"CombinationSamplerTest.testUniformWithKmoreThanHalfN_1_oe_2_oe",

"BoxMullerLogNormalSamplerTest.*",


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

sed -i 's/\bfinal[[:space:]]\+/ /g' dataset/inputs.csv

sed -i 's/\bchiSquareTest0\s*(/chiSquareTest(/g' dataset/inputs.csv

sed -i 's/\bbitCount0\s*(/bitCount(/g' dataset/inputs.csv