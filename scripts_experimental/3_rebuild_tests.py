#!/usr/bin/env python3
import os
import csv
import re
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
PROJECTS_DIR = os.path.join(ROOT, "projects_decomposed/joda-time")


# ------------------------- helpers ------------------------- #

def canonical_test_name(name: str) -> str:

    # Remove OE-specific suffixes first
    name = re.sub(r'_serial_oe.*$', '', name)
    name = re.sub(r'_oe.*$', '', name)
    # Remove trailing _number
    name = re.sub(r'_\d+$', '', name)
    return name


def load_inputs_and_meta(project_dir):
    dataset_dir = os.path.join(project_dir, "dataset")
    inputs_path = os.path.join(dataset_dir, "inputs.csv")
    meta_path = os.path.join(dataset_dir, "meta.csv")

    if not (os.path.exists(inputs_path) and os.path.exists(meta_path)):
        return None, None

    # id -> test_prefix
    id_to_prefix = {}
    with open(inputs_path, newline="", encoding="utf8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            tid = row["id"]
            id_to_prefix[tid] = row["test_prefix"]

    # group rows by (test_file_path, test_class)
    file_map = defaultdict(lambda: {"rows": [], "base_names": set()})
    with open(meta_path, newline="", encoding="utf8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            tid = row["id"]
            test_file_path = row["test_file_path"]
            test_class = row["test_class"]
            full_name = row["test_name"]  # e.g. PooledObjectTest::testGetObject_oe_1
            if "::" in full_name:
                method_name = full_name.split("::", 1)[1]
            else:
                method_name = full_name

            base = canonical_test_name(method_name)
            key = (test_file_path, test_class)
            file_map[key]["rows"].append({
                "id": tid,
                "method_name": method_name,
                "base_name": base,
                "row": row,
            })
            file_map[key]["base_names"].add(base)

    return id_to_prefix, file_map


def rename_class_and_ctors(source, old_class, new_class):
    """
    - Rename the class declaration: class OldClass -> class OldClass_OE25Dev
    - Rename all constructor usages 'OldClass(' -> 'OldClass_OE25Dev('
      (this will also update constructor definitions and 'new OldClass(...)'
       inside the same file).
    """
    # Rename class declaration (first occurrence only)
    pattern_class = re.compile(r"(class\s+)" + re.escape(old_class) + r"(\b)")
    source, n = pattern_class.subn(r"\1" + new_class + r"\2", source, count=1)
    if n == 0:
        print(f"  [WARN] Could not find class declaration for {old_class}")

    # Rename constructors + internal uses where OldClass is followed by '('
    pattern_ctor = re.compile(r"\b" + re.escape(old_class) + r"\s*\(")
    source = pattern_ctor.sub(new_class + "(", source)

    return source


def remove_matching_test_methods(source, base_names):
    """
    Remove test methods whose name matches any base_name (with any suffix).
    Works for:
      - JUnit 3 style: public void testXxx()
      - JUnit 4/5 style: @Test ... void testXxx()
    Only removes full method blocks (including @Test annotations above).
    """
    if not base_names:
        return source

    lines = source.splitlines(keepends=True)
    to_delete = set()
    i = 0
    while i < len(lines):
        line = lines[i]

        m = re.search(r'\bvoid\s+([A-Za-z0-9_]+)\s*\(', line)
        if m:
            name = m.group(1)
            cname = canonical_test_name(name)
            # match by base name (exact)
            if cname in base_names:
                # Find start of method (include annotations and blank lines above)
                start = i
                # include @... annotation lines
                while start > 0 and lines[start - 1].lstrip().startswith("@"):
                    start -= 1
                # optional: include preceding blank lines
                while start > 0 and lines[start - 1].strip() == "":
                    start -= 1

                # Find end of method by brace balance
                brace_depth = 0
                found_open = False
                j = i
                while j < len(lines):
                    for ch in lines[j]:
                        if ch == "{":
                            brace_depth += 1
                            found_open = True
                        elif ch == "}":
                            brace_depth -= 1
                    if found_open and brace_depth <= 0:
                        j += 1
                        break
                    j += 1

                for k in range(start, j):
                    to_delete.add(k)
                i = j
                continue

        i += 1

    new_lines = [ln for idx, ln in enumerate(lines) if idx not in to_delete]
    return "".join(new_lines)


def find_class_closing_brace(lines, class_name):
    """
    Find the index of the closing '}' of the top-level class `class_name`.
    Returns an index of the line containing that '}' (best-effort).
    """
    class_decl_idx = None
    pattern = re.compile(r"\bclass\s+" + re.escape(class_name) + r"\b")
    for idx, line in enumerate(lines):
        if pattern.search(line):
            class_decl_idx = idx
            break

    if class_decl_idx is None:
        # fallback: last brace
        for idx in range(len(lines) - 1, -1, -1):
            if "}" in lines[idx]:
                return idx
        return len(lines) - 1

    depth = 0
    seen_first_brace = False
    close_idx = len(lines) - 1
    for idx in range(class_decl_idx, len(lines)):
        for ch in lines[idx]:
            if ch == "{":
                depth += 1
                seen_first_brace = True
            elif ch == "}":
                depth -= 1
        if seen_first_brace and depth == 0:
            close_idx = idx
            break

    return close_idx


def build_new_tests_block(rows_for_file, id_to_prefix):
    """
    Build a list of lines to insert into the class: all OE25Dev test methods
    from inputs.csv (test_prefix).
    """
    block_lines = []
    first = True
    for info in rows_for_file:
        tid = info["id"]
        prefix = id_to_prefix.get(tid, "")
        if not prefix:
            continue
        # Ensure it ends with newline, but do NOT merge lines
        if not prefix.endswith("\n"):
            prefix = prefix + "\n"
        if not first:
            block_lines.append("\n")
        first = False
        block_lines.append(prefix)
    block_lines.append("\n")
    return block_lines


def process_test_file(project_dir, test_file_path, test_class, entry, id_to_prefix):
    """
    For a single (project, test_file), create <test_class>_OE25Dev.java
    with:
      - class renamed to TestClass_OE25Dev
      - all matching original test methods removed
      - OE25Dev test methods injected before class closing brace
    """
    orig_path = os.path.join(project_dir, test_file_path)
    if not os.path.exists(orig_path):
        print(f"  [WARN] Original test file not found: {orig_path}")
        return

    new_class = test_class + "_OE25Dev"
    new_path = orig_path.replace(test_class + ".java", new_class + ".java")

    if os.path.exists(new_path):
        print(f"  [SKIP] {new_path} already exists")
        return

    with open(orig_path, encoding="utf8") as f:
        src = f.read()

    base_names = entry["base_names"]
    rows_for_file = entry["rows"]

    # 1) rename class and constructors
    src = rename_class_and_ctors(src, test_class, new_class)

    # 2) remove original test methods matching our base names
    src = remove_matching_test_methods(src, base_names)

    # 3) insert OE25Dev methods near end of class
    lines = src.splitlines(keepends=True)
    close_idx = find_class_closing_brace(lines, new_class)
    new_tests_block = build_new_tests_block(rows_for_file, id_to_prefix)

    new_lines = []
    new_lines.extend(lines[:close_idx])
    # ensure a blank line before injected tests
    if not lines[close_idx - 1].strip() == "":
        new_lines.append("\n")
    new_lines.extend(new_tests_block)
    new_lines.append(lines[close_idx])
    new_lines.extend(lines[close_idx + 1:])

    new_src = "".join(new_lines)

    os.makedirs(os.path.dirname(new_path), exist_ok=True)
    with open(new_path, "w", encoding="utf8") as f:
        f.write(new_src)

    print(f"  [+] Wrote {new_path}")


# ------------------------- main ------------------------- #

def main():
    print("=== Injecting OE25Dev tests into cloned test classes ===")
    for project in sorted(os.listdir(PROJECTS_DIR)):
        project_dir = os.path.join(PROJECTS_DIR, project)
        if not os.path.isdir(project_dir):
            continue

        dataset_dir = os.path.join(project_dir, "dataset")
        if not os.path.exists(dataset_dir):
            continue

        print(f"\n--- Project: {project} ---")
        id_to_prefix, file_map = load_inputs_and_meta(project_dir)
        if id_to_prefix is None:
            print("  [WARN] No inputs/meta here, skipping.")
            continue

        for (test_file_path, test_class), entry in file_map.items():
            print(f"  Processing {test_file_path} ({test_class})")
            process_test_file(project_dir, test_file_path, test_class, entry, id_to_prefix)

    print("\n=== DONE (inject_oe25dev_tests) ===")


if __name__ == "__main__":
    main()
