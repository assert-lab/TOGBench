cd "$PWD/projects_decomposed/async-http-client"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"PerRequestTimeoutTest.testRequestTimeout_2_oe",
"PerRequestTimeoutTest.testRequestTimeout_3_oe",
"PerRequestTimeoutTest.testReadTimeout_2_oe",
"PerRequestTimeoutTest.testReadTimeout_3_oe",
"PerRequestTimeoutTest.testGlobalDefaultPerRequestInfiniteTimeout_2_oe",
"PerRequestTimeoutTest.testGlobalRequestTimeout_2_oe",
"PerRequestTimeoutTest.testGlobalRequestTimeout_3_oe",
"PerRequestTimeoutTest.testGlobalIdleTimeout_3_oe",

"OAuthSignatureCalculatorTest.testGetWithRequestBuilderAndQuery_3_oe",
"OAuthSignatureCalculatorTest.testGetWithRequestBuilderAndQuery_4_oe",
"OAuthSignatureCalculatorTest.testGetWithRequestBuilderAndQuery_5_oe",
"OAuthSignatureCalculatorTest.testGetWithRequestBuilder_3_oe",
"OAuthSignatureCalculatorTest.testGetWithRequestBuilder_4_oe",
"OAuthSignatureCalculatorTest.testPostCalculateSignature_2_oe",
"SpnegoEngineTest.testGetCompleteServicePrincipalName_2_oe",
"TextMessageTest.onFailureTest_1_oe",

"AbstractAsyncHttpClientFactoryTest.*"
"PerRequestRelative302Test.*",
"Relative302Test.*",
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
