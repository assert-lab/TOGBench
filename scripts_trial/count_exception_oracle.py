#!/usr/bin/env python3
import csv
import re
from pathlib import Path

ASSERT_THROWS_RE = re.compile(r"\bassertThrows\s*\(")

FAIL_UNQUAL_SPACE_RE = re.compile(r"\sfail\(")
FAIL_ASSERT_RE = re.compile(r"\bAssert\.fail\(")
FAIL_ASSERTIONS_RE = re.compile(r"\bAssertions\.fail\(")

def count_in_file(inputs_csv: Path):
    assertthrows = 0
    fail = 0
    total_rows = 0

    with inputs_csv.open("r", encoding="utf-8", newline="") as f:
        r = csv.DictReader(f)
        for row in r:
            total_rows += 1
            code = row.get("test_prefix") or ""

            assertthrows += len(ASSERT_THROWS_RE.findall(code))
            fail += (
                len(FAIL_UNQUAL_SPACE_RE.findall(code)) +
                len(FAIL_ASSERT_RE.findall(code)) +
                len(FAIL_ASSERTIONS_RE.findall(code))
            )

    return total_rows, assertthrows, fail

def main():
    root = Path(__file__).resolve().parent.parent
    projects_dir = root / "projects_decomposed"
    if not projects_dir.exists():
        raise SystemExit("projects_decomposed not found")

    out_path = root / "assertthrows_fail_counts.csv"

    rows = []
    grand_rows = 0
    grand_assertthrows = 0
    grand_fail = 0

    for project_dir in sorted(projects_dir.iterdir()):
        dataset_dir = project_dir / "dataset"
        inputs_csv = dataset_dir / "inputs.csv"
        if not inputs_csv.exists():
            continue

        total_rows, assertthrows, fail = count_in_file(inputs_csv)
        combined = assertthrows + fail

        grand_rows += total_rows
        grand_assertthrows += assertthrows
        grand_fail += fail

        rows.append({
            "project": project_dir.name,
            "total_rows": total_rows,
            "assertThrows_count": assertthrows,
            "fail_count": fail,
            "assertThrows_plus_fail": combined,
        })

    rows.append({
        "project": "TOTAL",
        "total_rows": grand_rows,
        "assertThrows_count": grand_assertthrows,
        "fail_count": grand_fail,
        "assertThrows_plus_fail": grand_assertthrows + grand_fail,
    })

    with out_path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=["project","total_rows","assertThrows_count","fail_count","assertThrows_plus_fail"], lineterminator="\n")
        w.writeheader()
        for r in rows:
            w.writerow(r)

    print(out_path)

if __name__ == "__main__":
    main()
