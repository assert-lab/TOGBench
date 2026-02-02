#!/usr/bin/env python3
import subprocess
from pathlib import Path

PROJECTS_ROOT = Path("projects_decomposed")
SCRIPT_PATH = Path("scripts_trial/custom_assert_define.py")
DATASET_DIR = "dataset"

def main():
    if not PROJECTS_ROOT.is_dir():
        raise SystemExit("projects_decomposed not found")

    if not SCRIPT_PATH.is_file():
        raise SystemExit("custom_assert_define.py not found")

    ran = 0
    skipped = 0
    failed = 0

    for project_dir in sorted(PROJECTS_ROOT.iterdir()):
        if not project_dir.is_dir():
            continue

        dataset_dir = project_dir / DATASET_DIR
        if not (dataset_dir / "inputs_custom.csv").is_file():
            skipped += 1
            continue
        if not (dataset_dir / "meta_custom.csv").is_file():
            skipped += 1
            continue

        result = subprocess.run(
            ["python3", str(SCRIPT_PATH), "--project_root", str(project_dir)]
        )

        if result.returncode == 0:
            ran += 1
        else:
            failed += 1
            print(f"FAILED {project_dir.name}")

    print(f"processed={ran} skipped={skipped} failed={failed}")

if __name__ == "__main__":
    main()
