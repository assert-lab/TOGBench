# python3 scripts/remove_comments.py
import os
import csv
from pathlib import Path

root = Path("projects_decomposed")

total_projects = 0
total_rows = 0

for project_dir in root.iterdir():
    if not project_dir.is_dir():
        continue

    inputs_path = project_dir / "dataset/inputs.csv"
    if not inputs_path.exists():
        continue

    rows = []

    with open(inputs_path, newline='', encoding='utf-8') as f:
        reader = csv.DictReader(f)
        fieldnames = reader.fieldnames

        for row in reader:
            prefix = row.get("test_prefix", "")
            lines = prefix.splitlines()

            cleaned_lines = [
                line for line in lines
                if not line.lstrip().startswith("//")
            ]

            row["test_prefix"] = "\n".join(cleaned_lines)
            rows.append(row)

    with open(inputs_path, "w", newline='', encoding='utf-8') as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)

    print(project_dir.name, "processed:", len(rows))
    total_projects += 1
    total_rows += len(rows)

print("TOTAL projects:", total_projects)
print("TOTAL rows processed:", total_rows)