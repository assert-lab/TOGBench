# scripts/extract_project.py
import os
import csv
import re
import argparse
from typing import List

from utils import (
    get_method_header,
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


def _line_has_test_annotation(line: str, in_block_comment: bool) -> bool:
    sanitized, _ = sanitize_for_counting(line, in_block_comment)
    s = sanitized.strip()
    if not s.startswith("@"):
        return False
    return any(ann in s for ann in JUNIT_TEST_ANNOTATIONS)


def extract_test_methods(file_content: str) -> List[List[str]]:
    lines = file_content.splitlines(keepends=True)
    methods: List[List[str]] = []
    i = 0
    n = len(lines)
    in_block = False

    while i < n:
        line = lines[i]
        sanitized, in_block = sanitize_for_counting(line, in_block)

        if _line_has_test_annotation(line, in_block_comment=False):
            buf: List[str] = [line]
            i += 1
            brace_balance = 0
            saw_open = False
            inner_in_block = in_block

            while i < n:
                ln = lines[i]
                buf.append(ln)
                s2, inner_in_block = sanitize_for_counting(ln, inner_in_block)
                opens = s2.count("{")
                closes = s2.count("}")
                brace_balance += opens - closes
                if opens > 0:
                    saw_open = True
                if saw_open and brace_balance == 0:
                    i += 1
                    break
                i += 1

            methods.append(buf)
            continue

        if JUNIT3_TEST_HEADER.match(sanitized):
            buf = [line]
            i += 1
            brace_balance = sanitized.count("{") - sanitized.count("}")
            saw_open = sanitized.count("{") > 0
            inner_in_block = in_block

            while i < n:
                ln = lines[i]
                buf.append(ln)
                s2, inner_in_block = sanitize_for_counting(ln, inner_in_block)
                opens = s2.count("{")
                closes = s2.count("}")
                brace_balance += opens - closes
                if opens > 0:
                    saw_open = True
                if saw_open and brace_balance == 0:
                    i += 1
                    break
                i += 1

            methods.append(buf)
            continue

        i += 1

    return methods


def extract_project(project: str, projects_root: str = "projects_decomposed/joda-time"):
    project_path = os.path.join(projects_root, project)
    dataset_path = os.path.join(project_path, "dataset")
    os.makedirs(dataset_path, exist_ok=True)

    prefix = get_project_prefix(project)
    global_test_counter = 1

    rows_inputs = []
    rows_meta = []

    for test_file in find_test_files(project_path):
        rel_path = os.path.relpath(test_file, project_path)
        test_class = os.path.basename(test_file).replace(".java", "")

        with open(test_file, "r", encoding="utf-8", errors="ignore") as f:
            code = f.read()

        methods = extract_test_methods(code)

        for method_lines in methods:
            try:
                collapsed_lines, oracle_idxs = collapse_oracle_blocks(method_lines)
            except IndexError:
                # Don't let one weird test kill the whole build
                print(f"[WARN] collapse_oracle_blocks IndexError in project={project}, "
                    f"file={rel_path}")
                continue

            if not oracle_idxs:
                continue


            header = get_method_header(collapsed_lines)
            base_name = get_method_name_from_header(header)

            oracle_counter = 1
            for idx in oracle_idxs:
                new_method_name = f"{base_name}_{oracle_counter}_oe"
                test_id = f"{prefix}_{global_test_counter}_{oracle_counter}"

                full_test = build_single_oracle_test(collapsed_lines, idx, new_method_name)

                rows_inputs.append(
                    {
                        "id": test_id,
                        "test_prefix": full_test,
                        "test_name": f"{test_class}::{new_method_name}",
                    }
                )

                rows_meta.append(
                    {
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
                    }
                )

                oracle_counter += 1

            global_test_counter += 1

    inputs_path = os.path.join(dataset_path, "inputs.csv")
    meta_path = os.path.join(dataset_path, "meta.csv")

    with open(inputs_path, "w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=["id", "test_prefix", "test_name"])
        w.writeheader()
        w.writerows(rows_inputs)

    with open(meta_path, "w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(
            f,
            fieldnames=[
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
            ],
        )
        w.writeheader()
        w.writerows(rows_meta)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("project")
    ap.add_argument("--projects_root", default="projects_decomposed/joda-time")
    args = ap.parse_args()
    extract_project(args.project, projects_root=args.projects_root)


if __name__ == "__main__":
    main()
