
#!/usr/bin/env python3
# scripts/extract_project.py
import os
import csv
import re
import argparse
from typing import List

from utils import (
    get_method_header,
    get_method_header_jsoup,
    get_method_name_from_header,
    build_single_oracle_test,
    sanitize_for_counting,
    collapse_oracle_blocks,
)

JUNIT3_TEST_HEADER = re.compile(r"\s*public\s+void\s+test[A-Za-z0-9_]*\s*\(")

JUNIT_TEST_ANNOTATIONS = (
    "@Test",
    "@org.junit.Test",
    "@org.junit.jupiter.api.Test",
    "@ParameterizedTest",
    "@org.junit.jupiter.params.ParameterizedTest",
    "@RepeatedTest",
    "@TestFactory",
    "@TestTemplate",
)


def get_project_prefix(project: str) -> str:
    p = project.lower()
    if p.startswith("commons-"):
        suffix = p.split("commons-")[1]
        return suffix[:4]
    return p.split("-")[0]


def find_test_files(project_path: str):
    for root, _, files in os.walk(project_path):
        if os.sep + "src" + os.sep + "test" + os.sep + "java" + os.sep in root:
            for f in files:
                if f.endswith(".java"):
                    yield os.path.join(root, f)


def _line_has_test_annotation(sanitized_line: str) -> bool:
    s = sanitized_line.strip()
    if not s.startswith("@"):
        return False
    return any(ann in s for ann in JUNIT_TEST_ANNOTATIONS)


def extract_test_methods(file_content: str) -> List[List[str]]:
    lines = file_content.splitlines(keepends=True)
    methods = []
    i = 0
    n = len(lines)
    in_block = False

    while i < n:
        line = lines[i]
        sanitized, in_block = sanitize_for_counting(line, in_block)

        if _line_has_test_annotation(sanitized):
            buf = [line]
            i += 1
            local_in_block = in_block
            sanitized_body, local_in_block = sanitize_for_counting(line, local_in_block)
            brace_balance = sanitized_body.count("{") - sanitized_body.count("}")
            saw_open = sanitized_body.count("{") > 0
            while i < n:
                ln = lines[i]
                sanitized_ln, local_in_block = sanitize_for_counting(ln, local_in_block)
                if (_line_has_test_annotation(sanitized_ln) or JUNIT3_TEST_HEADER.match(sanitized_ln)):
                    if saw_open and brace_balance <= 0:
                        break
                buf.append(ln)
                opens = sanitized_ln.count("{")
                closes = sanitized_ln.count("}")
                brace_balance += opens - closes
                if opens > 0:
                    saw_open = True
                if saw_open and brace_balance == 0:
                    i += 1
                    break
                i += 1
            methods.append(buf)
            in_block = local_in_block
            continue

        if JUNIT3_TEST_HEADER.match(sanitized):
            buf = [line]
            i += 1
            local_in_block = in_block
            sanitized_header, local_in_block = sanitize_for_counting(line, local_in_block)
            brace_balance = sanitized_header.count("{") - sanitized_header.count("}")
            saw_open = sanitized_header.count("{") > 0
            while i < n:
                ln = lines[i]
                sanitized_ln, local_in_block = sanitize_for_counting(ln, local_in_block)
                if (_line_has_test_annotation(sanitized_ln) or JUNIT3_TEST_HEADER.match(sanitized_ln)):
                    if saw_open and brace_balance <= 0:
                        break
                buf.append(ln)
                opens = sanitized_ln.count("{")
                closes = sanitized_ln.count("}")
                brace_balance += opens - closes
                if opens > 0:
                    saw_open = True
                if saw_open and brace_balance == 0:
                    i += 1
                    break
                i += 1
            methods.append(buf)
            in_block = local_in_block
            continue

        i += 1

    return methods


def extract_project(project: str, projects_root: str = "projects_decomposed"):
    project_path = os.path.join(projects_root, project)
    if not os.path.isdir(project_path):
        print(f"[SKIP] {project} is not a directory")
        return

    dataset_path = os.path.join(project_path, "dataset_custom")
    os.makedirs(dataset_path, exist_ok=True)

    prefix = get_project_prefix(project)
    global_test_counter = 1

    rows_inputs = []
    rows_meta = []
    rows_inputs_custom = []
    rows_meta_custom = []

    is_jsoup = project.lower() == "jsoup"

    for test_file in find_test_files(project_path):
        rel_path = os.path.relpath(test_file, project_path)
        test_class = os.path.basename(test_file).replace(".java", "")

        with open(test_file, "r", encoding="utf-8", errors="ignore") as f:
            code = f.read()

        methods = extract_test_methods(code)

        for method_lines in methods:
            try:
                collapsed_lines, oracle_idxs, oracle_kinds = collapse_oracle_blocks(method_lines)
            except IndexError:
                print(f"[WARN] collapse_oracle_blocks IndexError in project={project}, file={rel_path}")
                continue

            if not oracle_idxs:
                continue

            header = get_method_header_jsoup(collapsed_lines) if is_jsoup else get_method_header(collapsed_lines)
            base_name = get_method_name_from_header(header)

            oracle_counter = 1
            for j, idx in enumerate(oracle_idxs):
                kind, name = oracle_kinds[j]
                new_method_name = f"{base_name}_{oracle_counter}_oe"
                test_id = f"{prefix}_{global_test_counter}_{oracle_counter}"

                full_test = build_single_oracle_test(
                    collapsed_lines,
                    idx,
                    new_method_name,
                    is_jsoup=is_jsoup,
                )

                inputs_row = {
                    "id": test_id,
                    "test_prefix": full_test,
                    "test_name": f"{test_class}::{new_method_name}",
                }

                meta_row = {
                    "id": test_id,
                    "project": project,
                    "test_class": test_class,
                    "test_name": new_method_name,
                    "test_file_path": rel_path,
                    "focal_file_path": "",
                    "focal_class": "",
                    "focal_package": "",
                    "oracle_type": "assert",
                    "junit_version": "unknown",
                    "assert_kind": kind,
                    "assert_name": name,
                }

                if kind == "custom":
                    rows_inputs_custom.append(inputs_row)
                    rows_meta_custom.append(meta_row)
                else:
                    rows_inputs.append(inputs_row)
                    rows_meta.append(meta_row)

                oracle_counter += 1

            global_test_counter += 1

    # inputs_path = os.path.join(dataset_path, "inputs.csv")
    # meta_path = os.path.join(dataset_path, "meta.csv")
    inputs_custom_path = os.path.join(dataset_path, "inputs_custom.csv")
    meta_custom_path = os.path.join(dataset_path, "meta_custom.csv")

    # with open(inputs_path, "w", newline="", encoding="utf-8") as f:
    #     w = csv.DictWriter(f, fieldnames=["id", "test_prefix", "test_name"])
    #     w.writeheader()
    #     w.writerows(rows_inputs)

    with open(inputs_custom_path, "w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=["id", "test_prefix", "test_name"])
        w.writeheader()
        w.writerows(rows_inputs_custom)

    meta_fields = [
        "id",
        "project",
        "test_class",
        "test_name",
        "test_file_path",
        "focal_file_path",
        "focal_class",
        "focal_package",
        "oracle_type",
        "junit_version",
        "assert_kind",
        "assert_name",
    ]

    # with open(meta_path, "w", newline="", encoding="utf-8") as f:
    #     w = csv.DictWriter(f, fieldnames=meta_fields)
    #     w.writeheader()
    #     w.writerows(rows_meta)

    with open(meta_custom_path, "w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=meta_fields)
        w.writeheader()
        w.writerows(rows_meta_custom)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("project")
    ap.add_argument("--projects_root", default="")
    args = ap.parse_args()
    extract_project(args.project, projects_root=args.projects_root)


if __name__ == "__main__":
    main()
