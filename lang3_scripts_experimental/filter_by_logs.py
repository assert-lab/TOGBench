import os
import re
import csv

LOG_ROOT = "logs"
PROJECTS_DIR = "projects_decomposed/commons-lang3-3.12.0-src"

running_re = re.compile(r"\[INFO\]\s+Running\s+([^\s]+)")
in_re = re.compile(r"- in\s+([^\s]+)\s*$")
error_class_re = re.compile(r"\(([^()]+)\)")

def collect_project_classes():
    project_classes = {}
    for dirpath, dirnames, filenames in os.walk(LOG_ROOT):
        rel = os.path.relpath(dirpath, LOG_ROOT)
        if rel == ".":
            continue
        base = rel.split(os.sep)[0]
        project = base.split("__")[0]
        cls_set = project_classes.setdefault(project, set())
        for fn in filenames:
            if not fn.endswith(".log"):
                continue
            log_path = os.path.join(dirpath, fn)
            try:
                with open(log_path, "r", errors="ignore") as f:
                    for line in f:
                        if "_OE25Dev" not in line:
                            continue
                        if "[INFO]" in line:
                            m = running_re.search(line)
                            if m:
                                cls = m.group(1)
                                if cls.endswith("_OE25Dev"):
                                    cls_set.add(cls)
                            m2 = in_re.search(line)
                            if m2:
                                cls = m2.group(1)
                                if cls.endswith("_OE25Dev"):
                                    cls_set.add(cls)
                        if "[ERROR]" in line:
                            m3 = error_class_re.search(line)
                            if m3:
                                cls = m3.group(1)
                                if cls.endswith("_OE25Dev"):
                                    cls_set.add(cls)
            except FileNotFoundError:
                continue
    return project_classes

def filter_project(project, test_paths):
    dataset_dir = os.path.join(PROJECTS_DIR, project, "dataset")
    meta_path = os.path.join(dataset_dir, "meta.csv")
    inputs_path = os.path.join(dataset_dir, "inputs.csv")
    if not os.path.exists(meta_path):
        print(f"Skipping {project}: no meta.csv")
        return
    if not os.path.exists(inputs_path):
        print(f"Skipping {project}: no inputs.csv")
        return
    with open(meta_path, newline="") as f:
        reader = csv.reader(f)
        meta_rows = list(reader)
    if not meta_rows:
        print(f"Skipping {project}: empty meta.csv")
        return
    meta_header = meta_rows[0]
    try:
        meta_test_idx = meta_header.index("test_file_path")
        meta_id_idx = meta_header.index("id")
    except ValueError:
        print(f"Skipping {project}: required columns missing in meta.csv")
        return
    kept_meta = [meta_header]
    kept_ids = set()
    for row in meta_rows[1:]:
        if meta_test_idx < len(row) and row[meta_test_idx] in test_paths:
            kept_meta.append(row)
            if meta_id_idx < len(row):
                kept_ids.add(row[meta_id_idx])
    with open(meta_path, "w", newline="") as f:
        writer = csv.writer(f)
        writer.writerows(kept_meta)
    with open(inputs_path, newline="") as f:
        reader = csv.reader(f)
        inputs_rows = list(reader)
    if not inputs_rows:
        print(f"Skipping {project}: empty inputs.csv")
        return
    inputs_header = inputs_rows[0]
    try:
        inputs_id_idx = inputs_header.index("id")
    except ValueError:
        print(f"Skipping {project}: 'id' column missing in inputs.csv")
        return
    kept_inputs = [inputs_header]
    for row in inputs_rows[1:]:
        if inputs_id_idx < len(row) and row[inputs_id_idx] in kept_ids:
            kept_inputs.append(row)
    with open(inputs_path, "w", newline="") as f:
        writer = csv.writer(f)
        writer.writerows(kept_inputs)
    print(f"Project {project}: kept {len(kept_meta)-1} tests")

def main():
    project_classes = collect_project_classes()
    if not project_classes:
        print("No projects found in logs")
        return
    for project, classes in project_classes.items():
        if not classes:
            print(f"Project {project}: no OE25Dev classes found in logs")
            continue
        test_paths = set()
        for cls in classes:
            path = "src/test/java/" + cls.replace(".", "/") + ".java"
            test_paths.add(path)
        filter_project(project, test_paths)

if __name__ == "__main__":
    main()
