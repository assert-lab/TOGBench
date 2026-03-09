import shutil
from pathlib import Path

src_root = Path("projects_decomposed")
dst_root = Path("dataset_multiple")

dst_root.mkdir(exist_ok=True)

for project in src_root.iterdir():
    src = project / "dataset_multiple"
    if not src.exists():
        continue

    dst_proj = dst_root / project.name
    dst_proj.mkdir(parents=True, exist_ok=True)

    inputs = src / "inputs_multiple.csv"
    meta = src / "meta_multiple.csv"

    if inputs.exists():
        shutil.copy2(inputs, dst_proj / "inputs.csv")

    if meta.exists():
        shutil.copy2(meta, dst_proj / "meta.csv")

    print("copied:", project.name)

print("done")