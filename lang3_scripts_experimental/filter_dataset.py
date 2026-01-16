#!/usr/bin/env python3
import csv
import re
from pathlib import Path

# ---------------- Paths ----------------

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed/commons-lang3-3.12.0-src"
LOGS_ROOT = ROOT / "final_build_logs"

# ---------------- Robust Surefire regexes ----------------

# Matches:
# [INFO] Running org.foo.Bar_OE25Dev
RUN_CLASS_RE = re.compile(
    r"Running\s+([A-Za-z0-9_.]+_OE25Dev[A-Za-z0-9_]*)"
)

# Matches ANY failed OE test case, regardless of format:
# JSONTokenerTest_OE25Dev.testSomething_oe
FAIL_TEST_RE = re.compile(
    r"([A-Za-z0-9_]+_OE25Dev[A-Za-z0-9_]*)\.([A-Za-z0-9_]+_oe)\b"
)

# ---------------- CSV helpers ----------------

def load_csv(path: Path):
    with path.open(newline="", encoding="utf8") as f:
        return list(csv.DictReader(f))

def write_csv(path: Path, rows, fieldnames):
    if not rows:
        return
    with path.open("w", newline="", encoding="utf8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames)
        w.writeheader()
        w.writerows(rows)

# ---------------- Log parsing ----------------

def parse_project_logs(project_name: str):
    """
    Returns:
      ran_classes: {"ClassName_OE25Dev", ...}
      failed_tests: {"ClassName_OE25Dev.method_oe", ...}
    """
    ran_classes = set()
    failed_tests = set()

    log_dir = LOGS_ROOT / project_name
    if not log_dir.exists():
        return ran_classes, failed_tests

    logs = []
    if (log_dir / "build.log").exists():
        logs.append(log_dir / "build.log")
    logs.extend(sorted(log_dir.glob("*.log")))

    for log in logs:
        try:
            for line in log.read_text(errors="ignore").splitlines():

                # ---- Detect executed classes ----
                m = RUN_CLASS_RE.search(line)
                if m:
                    ran_classes.add(m.group(1).split(".")[-1])

                # ---- Detect failed OE test cases ----
                if "[ERROR]" in line:
                    for m in FAIL_TEST_RE.finditer(line):
                        cls, method = m.groups()
                        failed_tests.add(f"{cls}.{method}")

        except Exception:
            continue

    return ran_classes, failed_tests

# ---------------- Project processing ----------------

def process_project(project_dir: Path):
    dataset = project_dir / "dataset"
    inputs_csv = dataset / "inputs.csv"
    meta_csv = dataset / "meta.csv"

    if not inputs_csv.exists() or not meta_csv.exists():
        return

    project_name = project_dir.name
    ran_classes, failed_tests = parse_project_logs(project_name)

    inputs = load_csv(inputs_csv)
    meta = load_csv(meta_csv)

    passed_ids = set()
    failed_ids = set()
    not_run_ids = set()

    for row in meta:
        tid = row["id"]

        # Reconstruct class + method
        test_class = row["test_class"] + "_OE25Dev"
        test_method = row["test_name"]
        full_test = f"{test_class}.{test_method}"

        # ---- Class never executed ----
        if test_class not in ran_classes:
            not_run_ids.add(tid)
            continue

        # ---- Failed test case ----
        if full_test in failed_tests:
            failed_ids.add(tid)
            continue

        passed_ids.add(tid)

    inputs_final = [r for r in inputs if r["id"] in passed_ids]
    meta_final = [r for r in meta if r["id"] in passed_ids]

    write_csv(dataset / "inputs_final.csv", inputs_final, inputs[0].keys())
    write_csv(dataset / "meta_final.csv", meta_final, meta[0].keys())

    print(f"[PROJECT] {project_name}")
    print(f"  Ran classes : {len(ran_classes)}")
    print(f"  Failed tests: {len(failed_tests)}")
    print(f"  PASSED ids  : {len(passed_ids)}")
    print(f"  FAILED ids  : {len(failed_ids)}")
    print(f"  NOT-RUN ids : {len(not_run_ids)}")

# ---------------- Main ----------------

def main():
    print("=== Filtering OE25Dev using Maven Surefire logs ===")
    for project in sorted(PROJECTS_DIR.iterdir()):
        if project.is_dir():
            process_project(project)
    print("=== DONE ===")

if __name__ == "__main__":
    main()
