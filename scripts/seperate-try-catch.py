import csv
from pathlib import Path

root = Path("projects_decomposed")

total_projects = 0
total_try_rows = 0

for project_dir in root.iterdir():
    if not project_dir.is_dir():
        continue

    project_dir = project_dir / "dataset"
    inputs_path = project_dir / "inputs.csv"
    if not inputs_path.exists():
        continue

    try_rows = []

    with open(inputs_path, newline='', encoding='utf-8') as f:
        reader = csv.DictReader(f)
        fieldnames = reader.fieldnames

        for row in reader:
            prefix = row.get("test_prefix", "")
            if "try" in prefix and "catch" in prefix:
                try_rows.append(row)

    if try_rows:
        output_path = project_dir / "inputs_try.csv"
        with open(output_path, "w", newline='', encoding='utf-8') as f:
            writer = csv.DictWriter(f, fieldnames=fieldnames)
            writer.writeheader()
            writer.writerows(try_rows)

        print(project_dir.name, "try rows:", len(try_rows))
        total_try_rows += len(try_rows)

    total_projects += 1

print("TOTAL projects checked:", total_projects)
print("TOTAL try-catch rows:", total_try_rows)