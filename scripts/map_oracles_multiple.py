import os
import re
import pandas as pd

PROJECTS_DIR = "projects_decomposed"
TYPES = ["multiple", "mixed"]
COLS_TO_DROP = {"oracle_type", "junit_version", "assert_kind", "assert_name"}

ASSERT_STANDARD = re.compile(
    r'\bassert(?:Equals|NotEquals|True|False|Null|NotNull|Same|NotSame'
    r'|ArrayEquals|That|Matches|Iterable|LinesMatch|InstanceOf|All|Any'
    r'|Timeout|ThatCode)\s*\(',
    re.IGNORECASE
)
ASSERT_EXCEPTION = re.compile(
    r'\b(?:assertThrows|assertDoesNotThrow|assertThrowsExactly|fail)\s*\(',
    re.IGNORECASE
)


def classify_test(test_prefix: str) -> str:
    has_standard = bool(ASSERT_STANDARD.search(test_prefix))
    has_exception = bool(ASSERT_EXCEPTION.search(test_prefix))
    if has_standard and has_exception:
        return "mixed"
    return "multiple"


def process_project(project_path: str, project_name: str):
    for dtype in TYPES:
        meta_path = os.path.join(project_path, f"meta_{dtype}.csv")
        inputs_path = os.path.join(project_path, f"inputs_{dtype}.csv")

        if not os.path.exists(meta_path):
            continue

        meta_df = pd.read_csv(meta_path)

        if "dataset_type" in meta_df.columns:
            print(f"[SKIP] {project_name}/{dtype}: dataset_type already exists")
            continue

        if not os.path.exists(inputs_path):
            print(f"[WARN] {project_name}/{dtype}: inputs file missing, skipping")
            continue

        inputs_df = pd.read_csv(inputs_path)

        drop_cols = COLS_TO_DROP & set(meta_df.columns)
        if drop_cols:
            meta_df.drop(columns=list(drop_cols), inplace=True)

        if "id" not in inputs_df.columns or "test_prefix" not in inputs_df.columns:
            print(f"[WARN] {project_name}/{dtype}: inputs missing id or test_prefix, skipping")
            continue

        id_to_prefix = inputs_df.set_index("id")["test_prefix"].to_dict()

        meta_df["dataset_type"] = meta_df["id"].map(
            lambda row_id: classify_test(id_to_prefix[row_id])
            if row_id in id_to_prefix else None
        )

        meta_df.to_csv(meta_path, index=False)
        print(f"[OK]   {project_name}/{dtype}: updated {len(meta_df)} rows")


def main():
    if not os.path.isdir(PROJECTS_DIR):
        print(f"Directory not found: {PROJECTS_DIR}")
        return

    projects = sorted(os.listdir(PROJECTS_DIR))
    for project_name in projects:
        project_path = os.path.join(PROJECTS_DIR, project_name)
        if not os.path.isdir(project_path):
            continue
        process_project(project_path, project_name)


if __name__ == "__main__":
    main()