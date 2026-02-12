cd "$PWD/projects_decomposed/commons-numbers"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"EpsilonDoubleEquivalenceTest.testInvalidEpsilonValues_3_oe",
"EpsilonDoubleEquivalenceTest.testInvalidEpsilonValues_5_oe",
"EpsilonDoubleEquivalenceTest.testInvalidEpsilonValues_7_oe",
"CommonsLangPortedFractionTest.testPow_36_oe_1_oe",
"CommonsLangPortedFractionTest.testPow_37_oe_1_oe",
"CommonsLangPortedFractionTest.testPow_38_oe_1_oe",
"BigFractionTest.testConstructor_1_oe_3_oe",
"BigFractionTest.testConstructor_2_oe_3_oe",
"BigFractionTest.testConstructor_3_oe_3_oe",
"BigFractionTest.testConstructor_4_oe_3_oe",
"BigFractionTest.testConstructor_5_oe_3_oe",
"BigFractionTest.testDoubleConstructor_1_oe_3_oe",
"BigFractionTest.testDoubleConstructor_2_oe_3_oe",
"BigFractionTest.testDoubleConstructor_3_oe_3_oe",
"BigFractionTest.testDoubleConstructor_4_oe_3_oe",
"BigFractionTest.testDoubleConstructor_5_oe_3_oe",
"BigFractionTest.testDoubleConstructor_6_oe_3_oe",
"BigFractionTest.testDoubleConstructor_7_oe_3_oe",
"BigFractionTest.testDoubleConstructor_10_oe_1_oe"
"BigFractionTest.testDoubleConstructor_10_oe_2_oe",
"BigFractionTest.testDoubleConstructor_10_oe_3_oe",
"BigFractionTest.testDoubleConstructor_14_oe_3_oe",
"BigFractionTest.testDoubleConstructor_15_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithMaxDenominator_1_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithMaxDenominator_2_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithMaxDenominator_3_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithMaxDenominator_4_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithMaxDenominator_5_oe_3_oe",
"BigFractionTest.testDoubleConstructorThrows_7_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithEpsilonLimit_1_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithEpsilonLimit_2_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithEpsilonLimit_2_oe_1_oe",
"BigFractionTest.testDoubleConstructorWithEpsilonLimit_3_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithEpsilonLimit_4_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithEpsilonLimit_5_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithEpsilonLimit_6_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithEpsilonLimit_7_oe_3_oe",
"BigFractionTest.testDoubleValue_1_oe_1_oe",
"BigFractionTest.testDoubleValue_2_oe_1_oe",
"BigFractionTest.testDoubleValue_3_oe_1_oe"
"BigFractionTest.testDoubleValue_4_oe_1_oe",
"BigFractionTest.testDoubleValue_5_oe_1_oe",
"BigFractionTest.testDoubleValue_9_oe_1_oe",
"BigFractionTest.testDoubleValue_9_oe_1_oe",
"BigFractionTest.testDoubleValue_11_oe_1_oe",
"BigFractionTest.testAbs_1_oe_3_oe",
"BigFractionTest.testReciprocal_1_oe_3_oe",
"BigFractionTest.testNegate_1_oe_3_oe",
"BigFractionTest.testAdd_1_oe_3_oe",
"BigFractionTest.testAdd_2_oe_3_oe",
"BigFractionTest.testAdd_3_oe_3_oe",
"BigFractionTest.testAdd_4_oe_3_oe",
"BigFractionTest.testAdd_7_oe_3_oe",
"BigFractionTest.testDoubleConstructorWithEpsilonLimit_2_oe_2_oe",
"BigFractionTest.testAdd_8_oe_3_oe",
"BigFractionTest.testDivide_1_oe_3_oe",
"BigFractionTest.testDivide_2_oe_3_oe",
"BigFractionTest.testDivide_3_oe_3_oe",
"BigFractionTest.testDivide_4_oe_3_oe",
"BigFractionTest.testDivide_11_oe_3_oe",
"BigFractionTest.testDivide_12_oe_3_oe",
"BigFractionTest.testDivide_13_oe_3_oe",
"BigFractionTest.testMultiply_1_oe_3_oe",
"BigFractionTest.testMultiply_2_oe_3_oe",
"BigFractionTest.testMultiply_3_oe_3_oe",
"BigFractionTest.testMultiply_4_oe_3_oe",
"BigFractionTest.testPow_1_oe_3_oe",
"BigFractionTest.testSubtract_1_oe_3_oe",
"BigFractionTest.testSubtract_2_oe_3_oe",
"BigFractionTest.testSubtract_3_oe_3_oe",
"BigFractionTest.testSubtract_4_oe_3_oe",
"BigFractionTest.testDoubleConstructor_10_oe_1_oe",
"BigFractionTest.testDoubleConstructor_10_oe_2_oe",
"BigFractionTest.testDoubleValue_3_oe_1_oe",
"BigFractionTest.testDoubleValue_4_oe_1_oe",
"BigFractionTest.testDoubleValue_10_oe_1_oe",

"FractionTest.testConstructor_1_oe_3_oe",
"FractionTest.testConstructor_2_oe_3_oe",
"FractionTest.testConstructor_3_oe_3_oe",
"FractionTest.testConstructor_4_oe_3_oe",
"FractionTest.testConstructor_5_oe_3_oe",
"FractionTest.testDoubleConstructor_1_oe_3_oe",
"FractionTest.testDoubleConstructor_2_oe_3_oe",
"FractionTest.testDoubleConstructor_3_oe_3_oe",
"FractionTest.testDoubleConstructor_4_oe_3_oe",
"FractionTest.testDoubleConstructor_5_oe_3_oe",
"FractionTest.testDoubleConstructor_6_oe_3_oe",
"FractionTest.testDoubleConstructorWithMaxDenominator_1_oe_3_oe",
"FractionTest.testDoubleConstructorWithMaxDenominator_2_oe_3_oe",
"FractionTest.testDoubleConstructorWithEpsilonLimit_1_oe_3_oe",
"FractionTest.testNegate_2_oe_3_oe",
"FractionTest.testSubtract_1_oe_3_oe",
"FractionTest.testMath1261_2_oe_3_oe",
"FractionTest.testMath1261_1_oe_3_oe",
"FractionTest.testSubtract_2_oe_3_oe",
"FractionTest.testNegate_3_oe_3_oe",
"FractionTest.testDoubleConstructorThrows_7_oe_3_oe",
"FractionTest.testDoubleConstructor_7_oe_3_oe",
"FractionTest.testSubtract_8_oe_3_oe",
"FractionTest.testAdd_7_oe_3_oe",
"FractionTest.testReciprocal_1_oe_3_oe",
"FractionTest.testDoubleConstructorWithEpsilonLimit_3_oe_3_oe",
"FractionTest.testDoubleConstructorWithMaxDenominator_3_oe_3_oe",
"FractionTest.testSubtract_7_oe_3_oe",
"FractionTest.testMultiply_1_oe_3_oe",
"FractionTest.testAdd_8_oe_3_oe",
"FractionTest.testNegate_1_oe_3_oe",
"FractionTest.testDoubleConstructorWithEpsilonLimit_7_oe_3_oe",
"FractionTest.testDoubleConstructorWithEpsilonLimit_2_oe_3_oe",
"FractionTest.testDoubleConstructorWithMaxDenominator_4_oe_3_oe",
"FractionTest.testSubtract_6_oe_3_oe",
"FractionTest.testMultiply_2_oe_3_oe",
"FractionTest.testAdd_6_oe_3_oe",
"FractionTest.testAbs_1_oe_3_oe",
"FractionTest.testDoubleConstructorWithMaxDenominator_5_oe_3_oe",
"FractionTest.testDoubleConstructorWithEpsilonLimit_4_oe_3_oe",
"FractionTest.testDoubleConstructorWithEpsilonLimit_5_oe_3_oe",
"FractionTest.testDoubleConstructorWithEpsilonLimit_6_oe_3_oe",
"FractionTest.testAdd_2_oe_3_oe",
"FractionTest.testPow_1_oe_3_oe",
"FractionTest.testDivide_2_oe_3_oe",
"FractionTest.testDivide_1_oe_3_oe",
"FractionTest.testAdd_1_oe_3_oe",


"BigFractionTest.testDoubleConstructorWithMaxDenominator_2_oe_1_oe",
"BigFractionTest.testDoubleConstructorWithMaxDenominator_3_oe_1_oe",
"BigFractionTest.testDoubleConstructorWithMaxDenominator_4_oe_2_oe",
"BigFractionTest.testDoubleConstructorWithMaxDenominator_5_oe_2_oe",
"BigFractionTest.testDoubleConstructor_14_oe_1_oe",
"BigFractionTest.testDoubleConstructor_14_oe_2_oe",
"BigFractionTest.testDoubleConstructor_15_oe_1_oe",
"BigFractionTest.testDoubleConstructor_15_oe_2_oe",
"BigFractionTest.testDoubleConstructor_2_oe_1_oe",
"BigFractionTest.testDoubleConstructor_2_oe_2_oe",
"BigFractionTest.testDoubleConstructor_3_oe_1_oe",
"BigFractionTest.testDoubleConstructor_3_oe_2_oe",
"BigFractionTest.testDoubleConstructor_4_oe_1_oe",
"BigFractionTest.testDoubleConstructor_4_oe_2_oe",
"BigFractionTest.testDoubleConstructor_5_oe_1_oe",
"BigFractionTest.testDoubleConstructor_5_oe_2_oe",
"BigFractionTest.testDoubleConstructor_6_oe_1_oe",
"BigFractionTest.testDoubleConstructor_6_oe_2_oe",
"BigFractionTest.testDoubleConstructor_7_oe_1_oe",
"BigFractionTest.testDoubleConstructor_7_oe_2_oe",
"BigFractionTest.testParse_1_oe",
"FractionTest.testParse_1_oe",

"BrentSolverTest.testTooManyCalls_4_oe",

"ContinuedFractionTest.testMaxIterationsThrows_2_oe_1_oe",
"ContinuedFractionTest.testNaNThrows_2_oe_1_oe",
"ContinuedFractionTest.testInfThrows_2_oe_1_oe",

"ErfTest.testErfGnu_1_oe",
"ErfcTest.testErfcGnu_1_oe",
"GammaTest.testGammaNegativeDouble_1_oe",

"LogGammaTest.*",
"LogBetaTest.*",

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

# sed -i 's/\bfinal[[:space:]]\+int[[:space:]]\+expectedNumerator0[[:space:]]*=[[:space:]]*1L;/final long expectedNumerator0 = 1L;/g' dataset/inputs.csv
# sed -i 's/\bfinal[[:space:]]\+int[[:space:]]\+expectedDenominator0[[:space:]]*=[[:space:]]*1;/final long expectedDenominator0 = 1L;/g' dataset/inputs.csv

sed -i 's/\bfinal[[:space:]]\+int[[:space:]]\+expectedNumerator0\b/final long expectedNumerator0/g' dataset/inputs.csv
sed -i 's/\bfinal[[:space:]]\+int[[:space:]]\+expectedDenominator0\b/final long expectedDenominator0/g' dataset/inputs.csv


perl -pi.bak -e '
  s/\.(is(?:NaN|Infinite|Finite))0(\s*\()/.$1$2/g;
  s/\.abs0(\s*\()/\.abs$1/g;
  s/\bMath\s*\.\s*abs0(\s*\()/Math.abs$1/g;
' dataset/inputs.csv
