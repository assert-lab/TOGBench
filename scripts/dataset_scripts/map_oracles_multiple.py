# python3 scripts/map_oracles_multiple.py
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


def classify(test_prefix: str) -> str:
    has_standard = bool(ASSERT_STANDARD.search(test_prefix))
    has_exception = bool(ASSERT_EXCEPTION.search(test_prefix))
    return "mixed" if (has_standard and has_exception) else "multiple"


def process_project(project_path: str, project_name: str):
    for dtype in ["multiple"]:
        dataset_path = os.path.join(project_path, f"dataset_{dtype}")
        meta_path = os.path.join(dataset_path, f"meta_passed.csv")
        inputs_path = os.path.join(dataset_path, f"inputs_passed.csv")

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

        id_to_prefix = inputs_df.set_index("id")["test_prefix"].to_dict()
        meta_df["dataset_type"] = meta_df["id"].map(
            lambda i: classify(id_to_prefix[i]) if i in id_to_prefix else None
        )

        meta_df.to_csv(meta_path, index=False)
        print(f"[OK]   {project_name}/{dtype}: updated {len(meta_df)} rows")


def main():
    project_name = "joda-time"
    project_path = os.path.join(PROJECTS_DIR, project_name)

    if not os.path.isdir(project_path):
        print(f"Project not found: {project_name}")
        return

    process_project(project_path, project_name)


if __name__ == "__main__":
    main()