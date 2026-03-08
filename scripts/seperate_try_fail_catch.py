# python3 scripts/seperate_try_fail_catch.py

import csv
import re
import glob
import os


pattern = re.compile(
    r"try\s*\{[^{}]*fail\s*\([^)]*\)[^{}]*\}\s*catch\s*\(",
    re.MULTILINE
)

files = glob.glob("projects_decomposed/*/dataset_multiple/inputs_multiple.csv")

for input_file in files:

    output_file = input_file.replace(
        "dataset_multiple/inputs_multiple.csv",
        "dataset_check/inputs_try_fail_catch_multi.csv"
    )

    os.makedirs(os.path.dirname(output_file), exist_ok=True)

    rows = []

    with open(input_file, newline='', encoding="utf-8") as f:
        reader = csv.DictReader(f)
        fieldnames = reader.fieldnames

        for row in reader:
            prefix = row.get("test_prefix", "")
            if pattern.search(prefix):
                rows.append(row)

    with open(output_file, "w", newline='', encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)

    print(input_file, "matched_rows:", len(rows))