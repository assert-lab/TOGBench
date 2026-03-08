import pandas as pd
import re
import glob
import os
import sys

def remove_empty_try_catch(code):
    pattern = r'\btry\s*\{\s*\}\s*catch\s*\([^)]+\)\s*\{\s*\}'
    return re.sub(pattern, '', str(code), flags=re.DOTALL)

root = sys.argv[1] if len(sys.argv) > 1 else "projects_decomposed"

for project_dir in sorted(glob.glob(os.path.join(root, "*"))):
    inp_path = os.path.join(project_dir, "dataset", "inputs.csv")
    out_path = os.path.join(project_dir, "dataset", "inputs_no_empty_blocks.csv")

    if not os.path.exists(inp_path):
        print(f"Skipping {os.path.basename(project_dir)}")
        continue

    df = pd.read_csv(inp_path)
    before = df['test_prefix'].str.contains(r'\btry\s*\{\s*\}\s*catch', regex=True, na=False).sum()
    df['test_prefix'] = df['test_prefix'].apply(remove_empty_try_catch)
    df.to_csv(out_path, index=False)
    print(f"{os.path.basename(project_dir)}: {before} empty try-catch removed -> {out_path}")