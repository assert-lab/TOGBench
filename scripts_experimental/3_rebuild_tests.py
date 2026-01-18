#!/usr/bin/env python3
import os
import csv
import re
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
PROJECTS_DIR = os.path.join(ROOT, "projects_decomposed/joda-time")


def canonical_test_name(name: str) -> str:
    name = re.sub(r'_serial_oe.*$', '', name)
    name = re.sub(r'_oe.*$', '', name)
    name = re.sub(r'_\d+$', '', name)
    return name


def load_inputs_and_meta(project_dir):
    dataset_dir = os.path.join(project_dir, "dataset")
    inputs_path = os.path.join(dataset_dir, "inputs.csv")
    meta_path = os.path.join(dataset_dir, "meta.csv")

    if not (os.path.exists(inputs_path) and os.path.exists(meta_path)):
        return None, None

    id_to_prefix = {}
    with open(inputs_path, newline="", encoding="utf8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            tid = row["id"]
            id_to_prefix[tid] = row["test_prefix"]

    file_map = defaultdict(lambda: {"rows": [], "base_names": set()})
    with open(meta_path, newline="", encoding="utf8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            tid = row["id"]
            test_file_path = row["test_file_path"]
            test_class = row["test_class"]
            full_name = row["test_name"]
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
    pattern_class = re.compile(r"(class\s+)" + re.escape(old_class) + r"(\b)")
    source, n = pattern_class.subn(r"\1" + new_class + r"\2", source, count=1)
    if n == 0:
        print(f"  [WARN] Could not find class declaration for {old_class}")

    pattern_ctor = re.compile(r"\b" + re.escape(old_class) + r"\s*\(")
    source = pattern_ctor.sub(new_class + "(", source)

    return source


def count_braces_ignoring_literals(text):
    in_string = False
    in_char = False
    escape_next = False
    brace_count = 0
    
    i = 0
    while i < len(text):
        ch = text[i]
        
        if escape_next:
            escape_next = False
            i += 1
            continue
        
        if ch == '\\' and (in_string or in_char):
            escape_next = True
            i += 1
            continue
        
        if i + 1 < len(text) and text[i:i+2] == '//' and not in_string and not in_char:
            break
        
        if ch == '"' and not in_char:
            in_string = not in_string
        elif ch == "'" and not in_string:
            in_char = not in_char
        elif not in_string and not in_char:
            if ch == "{":
                brace_count += 1
            elif ch == "}":
                brace_count -= 1
        
        i += 1
    
    return brace_count


def remove_matching_test_methods(source, base_names):
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
            
            if cname in base_names:
                start = i
                while start > 0 and lines[start - 1].lstrip().startswith("@"):
                    start -= 1
                while start > 0 and lines[start - 1].strip() == "":
                    start -= 1

                brace_depth = 0
                found_open = False
                j = i
                
                while j < len(lines):
                    line_brace_delta = count_braces_ignoring_literals(lines[j])
                    brace_depth += line_brace_delta
                    
                    if brace_depth > 0:
                        found_open = True
                    
                    if found_open and brace_depth == 0:
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
    class_decl_idx = None
    pattern = re.compile(r"\bclass\s+" + re.escape(class_name) + r"\b")
    for idx, line in enumerate(lines):
        if pattern.search(line):
            class_decl_idx = idx
            break

    if class_decl_idx is None:
        for idx in range(len(lines) - 1, -1, -1):
            if "}" in lines[idx]:
                return idx
        return len(lines) - 1

    depth = 0
    seen_first_brace = False
    close_idx = len(lines) - 1
    
    for idx in range(class_decl_idx, len(lines)):
        line_delta = count_braces_ignoring_literals(lines[idx])
        depth += line_delta
        
        if depth > 0:
            seen_first_brace = True
        
        if seen_first_brace and depth == 0:
            close_idx = idx
            break

    return close_idx


def build_new_tests_block(rows_for_file, id_to_prefix):
    block_lines = []
    first = True
    for info in rows_for_file:
        tid = info["id"]
        prefix = id_to_prefix.get(tid, "")
        if not prefix:
            continue
        if not prefix.endswith("\n"):
            prefix = prefix + "\n"
        if not first:
            block_lines.append("\n")
        first = False
        block_lines.append(prefix)
    block_lines.append("\n")
    return block_lines


def process_test_file(project_dir, test_file_path, test_class, entry, id_to_prefix):
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

    src = rename_class_and_ctors(src, test_class, new_class)
    src = remove_matching_test_methods(src, base_names)

    lines = src.splitlines(keepends=True)
    close_idx = find_class_closing_brace(lines, new_class)
    new_tests_block = build_new_tests_block(rows_for_file, id_to_prefix)

    new_lines = []
    new_lines.extend(lines[:close_idx])
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

    print("\n=== DONE ===")


if __name__ == "__main__":
    main()