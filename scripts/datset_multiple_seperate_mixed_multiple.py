# python3 scripts/datset_multiple_seperate_mixed_multiple.py
import os
import pandas as pd

PROJECTS_DIR = "projects_decomposed"
TYPES = ["multiple", "mixed"]


def main():
    for project_name in sorted(os.listdir(PROJECTS_DIR)):
        project_path = os.path.join(PROJECTS_DIR, project_name)
        if not os.path.isdir(project_path):
            continue

        meta_path = os.path.join(project_path, "dataset_multiple", "meta_multiple.csv")
        inputs_path = os.path.join(project_path, "dataset_multiple", "inputs_multiple.csv")

        if not os.path.exists(meta_path) or not os.path.exists(inputs_path):
            print(f"[WARN] {project_name}: missing dataset files, skipping")
            continue

        meta_df = pd.read_csv(meta_path)
        inputs_df = pd.read_csv(inputs_path)

        for dtype in TYPES:
            meta_split = meta_df[meta_df["dataset_type"] == dtype]
            if meta_split.empty:
                continue

            ids = set(meta_split["id"])
            inputs_split = inputs_df[inputs_df["id"].isin(ids)]

            out_dir = os.path.join(project_path, f"dataset_{dtype}")
            os.makedirs(out_dir, exist_ok=True)

            meta_split.to_csv(os.path.join(out_dir, f"meta_{dtype}.csv"), index=False)
            inputs_split.to_csv(os.path.join(out_dir, f"inputs_{dtype}.csv"), index=False)

        print(f"[OK] {project_name}")


if __name__ == "__main__":
    main()