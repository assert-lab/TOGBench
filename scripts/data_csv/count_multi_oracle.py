# python3 scripts/data_csv/count_multi_oracle.py
#!/usr/bin/env python3
import csv
import re
from pathlib import Path

ROOT = Path("projects_decomposed")
OUT = Path("scripts/data_csv/multiple_assertion_summary.csv")

ASSERT_RE = re.compile(
    r'\bassert[A-Z_]\w*\s*\(|\bassertThat\s*\(|\bassume[A-Z_]\w*\s*\(|\bAssumptions\.\w+\s*\(|\bAssert\.\w+\s*\(|\bAssertions\.\w+\s*\('
)
FAIL_OR_THROWS_RE = re.compile(r'\bfail\s*\(|\bassertThrows\s*\(')

def analyze_project(project_dir: Path):
    csv_path = project_dir / "dataset_multiple/inputs_multiple.csv"
    if not csv_path.exists():
        return None

    total = 0
    only_assertion = 0
    mixed = 0

    with csv_path.open("r", encoding="utf-8", newline="") as f:
        reader = csv.DictReader(f)
        for row in reader:
            total += 1
            method_def = row.get("test_prefix") or ""

            has_assert = bool(ASSERT_RE.search(method_def))
            has_fail_or_throws = bool(FAIL_OR_THROWS_RE.search(method_def))

            if has_fail_or_throws:
                mixed += 1
            elif has_assert:
                only_assertion += 1

    return total, only_assertion, mixed


def main():
    results = []

    grand_total = 0
    grand_only_assertion = 0
    grand_mixed = 0

    for project in ROOT.iterdir():
        if not project.is_dir():
            continue

        stats = analyze_project(project)
        if stats is None:
            continue

        total, only_assertion, mixed = stats

        results.append({
            "project": project.name,
            "#total": total,
            "#only_assertion": only_assertion,
            "#mixed": mixed
        })

        grand_total += total
        grand_only_assertion += only_assertion
        grand_mixed += mixed

    results.append({
        "project": "TOTAL",
        "#total": grand_total,
        "#only_assertion": grand_only_assertion,
        "#mixed": grand_mixed
    })

    with OUT.open("w", encoding="utf-8", newline="") as f:
        writer = csv.DictWriter(
            f,
            fieldnames=["project", "#total", "#only_assertion", "#mixed"]
        )
        writer.writeheader()
        for row in results:
            writer.writerow(row)

    print(f"Saved summary to {OUT}")


if __name__ == "__main__":
    main()