#!/usr/bin/env python3
import re
import csv
from pathlib import Path
from typing import Optional, Dict, Tuple, List

ROOT = Path(__file__).resolve().parents[1]
LOGS_DIR = ROOT / "logs"
OUT_DIR = ROOT / "scripts"
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

NUMERIC_LOG_RE = re.compile(r"^(\d+)\.log$")


def pick_latest_log(log_dir: Path) -> Optional[Path]:
    """
    Pick the latest numeric log in a directory: 1.log, 2.log, ..., 12.log
    Returns None if no numeric *.log exists in that directory.
    """
    best_n = None
    best_path = None
    for p in log_dir.glob("*.log"):
        m = NUMERIC_LOG_RE.match(p.name)
        if not m:
            continue
        n = int(m.group(1))
        if best_n is None or n > best_n:
            best_n = n
            best_path = p
    return best_path


counts_rows: List[Dict[str, object]] = []
classes_rows: List[Dict[str, object]] = []
fail_lines_rows: List[Dict[str, object]] = []


# Iterate each directory under logs/ and parse ONLY its latest numeric log.
# This supports logs/<proj>/1.log,2.log,... and logs/<module>/<n>.log, etc.
if not LOGS_DIR.exists():
    raise SystemExit(f"logs dir not found: {LOGS_DIR}")

log_dirs = sorted([p for p in LOGS_DIR.rglob("*") if p.is_dir()])

for log_dir in log_dirs:
    log_path = pick_latest_log(log_dir)
    if log_path is None:
        continue

    rel_folder = log_dir.relative_to(LOGS_DIR).as_posix()

    tests_run = failures = errors = skipped = None
    class_stats: Dict[str, Tuple[int, int, int, int]] = {}
    results_errors: List[str] = []
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

# Sort + totals
counts_rows.sort(key=lambda r: str(r["folder"]))
classes_rows.sort(key=lambda r: (str(r["folder"]), str(r["test_class"])))
fail_lines_rows.sort(key=lambda r: (str(r["folder"]), str(r["line"])))

total_tests_run = sum(int(r["tests_run"]) for r in counts_rows)
total_success = sum(int(r["success"]) for r in counts_rows)
total_failures = sum(int(r["failures"]) for r in counts_rows)
total_errors = sum(int(r["errors"]) for r in counts_rows)
total_skipped = sum(int(r["skipped"]) for r in counts_rows)

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
