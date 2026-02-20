import csv
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed"

oe_pattern = re.compile(r"_oe_")

output_path = ROOT / "scripts" / "data_csv" / "meta_counts_all_projects.csv"

rows_out = []

grand_total = 0
grand_custom = 0

for proj in sorted(PROJECTS_DIR.glob("*")):
    if not proj.is_dir():
        continue

    meta_path = proj / "dataset_final" / "meta.csv"
    if not meta_path.exists():
        continue

    total = 0
    custom_to_standard = 0

    with meta_path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        reader = csv.DictReader(f)
        for row in reader:
            total += 1
            test_name = (row.get("test_name") or "").strip()
            if oe_pattern.search(test_name):
                custom_to_standard += 1

    standard_decomposed = total - custom_to_standard

    rows_out.append({
        "project": proj.name,
        "total_rows": total,
        "custom_to_standard": custom_to_standard,
        "standard_decomposed": standard_decomposed
    })

    grand_total += total
    grand_custom += custom_to_standard

# Add TOTAL row
rows_out.append({
    "project": "TOTAL",
    "total_rows": grand_total,
    "custom_to_standard": grand_custom,
    "standard_decomposed": grand_total - grand_custom
})

with output_path.open("w", encoding="utf-8", newline="") as f:
    writer = csv.DictWriter(
        f,
        fieldnames=["project", "total_rows", "custom_to_standard", "standard_decomposed"],
        lineterminator="\n"
    )
    writer.writeheader()
    writer.writerows(rows_out)

print(f"Wrote: {output_path}")
