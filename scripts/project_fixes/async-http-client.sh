cd "$PWD/projects_decomposed/async-http-client"

echo $PWD

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

"AbstractAsyncHttpClientFactoryTest.*",
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
