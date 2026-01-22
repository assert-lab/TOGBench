#!/usr/bin/env python3
import re
import csv
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
LOGS_DIR = ROOT / "logs"
OUT_DIR = ROOT / "scripts_trial"
OUT_DIR.mkdir(parents=True, exist_ok=True)

COUNTS_CSV = OUT_DIR / "test_counts.csv"
CLASSES_CSV = OUT_DIR / "test_classes.csv"
FAIL_LINES_CSV = OUT_DIR / "test_fail_lines.csv"

SUMMARY_RE = re.compile(r"Tests run:\s*(\d+),\s*Failures:\s*(\d+),\s*Errors:\s*(\d+),\s*Skipped:\s*(\d+)")
CLASS_RE = re.compile(r"Tests run:\s*(\d+),\s*Failures:\s*(\d+),\s*Errors:\s*(\d+),\s*Skipped:\s*(\d+).* - in\s+(\S+)")
RESULTS_LINE_RE = re.compile(r"^\[INFO\]\s+Results:")
FAILURES_HEADER_RE = re.compile(r"^\[ERROR\]\s+Failures:")
ERRORS_HEADER_RE = re.compile(r"^\[ERROR\]\s+Errors:")
SECTION_END_RE = re.compile(r"^\[INFO\]\s+-{20,}")

counts_rows = []
classes_rows = []
fail_lines_rows = []

for log_path in LOGS_DIR.rglob("1.log"):
    rel_folder = log_path.parent.relative_to(LOGS_DIR).as_posix()

    tests_run = failures = errors = skipped = None
    class_stats = {}
    results_errors = []
    in_results_block = False
    results_section = None

    try:
        with log_path.open(encoding="utf-8", errors="ignore") as f:
            for raw_line in f:
                line = raw_line.rstrip("\n")

                m_class = CLASS_RE.search(line)
                if m_class:
                    tr = int(m_class.group(1))
                    fl = int(m_class.group(2))
                    er = int(m_class.group(3))
                    sk = int(m_class.group(4))
                    clazz = m_class.group(5)
                    class_stats[clazz] = (tr, fl, er, sk)

                m_sum = SUMMARY_RE.search(line)
                if m_sum:
                    tests_run = int(m_sum.group(1))
                    failures = int(m_sum.group(2))
                    errors = int(m_sum.group(3))
                    skipped = int(m_sum.group(4))

                if RESULTS_LINE_RE.search(line):
                    in_results_block = True
                    results_section = None
                    continue

                if in_results_block:
                    if SECTION_END_RE.search(line):
                        in_results_block = False
                        results_section = None
                        continue

                    if line.startswith("[INFO]"):
                        continue

                    if FAILURES_HEADER_RE.search(line):
                        results_section = "failures"
                        continue

                    if ERRORS_HEADER_RE.search(line):
                        results_section = "errors"
                        continue

                    if line.startswith("[ERROR]") and results_section in {"failures", "errors"}:
                        msg = line[len("[ERROR]"):].strip()
                        if msg:
                            results_errors.append(msg)

    except FileNotFoundError:
        continue

    if tests_run is None:
        tests_run = failures = errors = skipped = 0

    success = tests_run - failures - errors - skipped
    if success < 0:
        success = 0

    counts_rows.append({
        "folder": rel_folder,
        "tests_run": tests_run,
        "success": success,
        "failures": failures,
        "errors": errors,
        "skipped": skipped,
    })

    for clazz, (tr, fl, er, sk) in class_stats.items():
        s = tr - fl - er - sk
        if s < 0:
            s = 0
        classes_rows.append({
            "folder": rel_folder,
            "test_class": clazz,
            "tests_run": tr,
            "success": s,
            "failures": fl,
            "errors": er,
            "skipped": sk,
        })

    for msg in results_errors:
        fail_lines_rows.append({
            "folder": rel_folder,
            "line": msg,
        })

counts_rows.sort(key=lambda r: r["folder"])
classes_rows.sort(key=lambda r: (r["folder"], r["test_class"]))
fail_lines_rows.sort(key=lambda r: (r["folder"], r["line"]))

total_tests_run = sum(r["tests_run"] for r in counts_rows)
total_success = sum(r["success"] for r in counts_rows)
total_failures = sum(r["failures"] for r in counts_rows)
total_errors = sum(r["errors"] for r in counts_rows)
total_skipped = sum(r["skipped"] for r in counts_rows)

counts_rows.append({
    "folder": "TOTAL",
    "tests_run": total_tests_run,
    "success": total_success,
    "failures": total_failures,
    "errors": total_errors,
    "skipped": total_skipped,
})

with COUNTS_CSV.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(
        f,
        fieldnames=["folder", "tests_run", "success", "failures", "errors", "skipped"],
    )
    w.writeheader()
    w.writerows(counts_rows)

with CLASSES_CSV.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(
        f,
        fieldnames=["folder", "test_class", "tests_run", "success", "failures", "errors", "skipped"],
    )
    w.writeheader()
    w.writerows(classes_rows)

with FAIL_LINES_CSV.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(
        f,
        fieldnames=["folder", "line"],
    )
    w.writeheader()
    w.writerows(fail_lines_rows)

print(f"wrote {len(counts_rows)} rows to {COUNTS_CSV}")
print(f"wrote {len(classes_rows)} rows to {CLASSES_CSV}")
print(f"wrote {len(fail_lines_rows)} rows to {FAIL_LINES_CSV}")
