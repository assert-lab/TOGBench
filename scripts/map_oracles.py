# python3 scripts/map_oracles.py projects_decomposed

import pandas as pd
import re
import glob
import os
import sys

KEEP_COLS = ["id", "project", "test_class", "test_name", "test_file_path",
             "focal_file_path", "focal_class", "focal_package", "oracle_type"]

def strip_lambda_bodies(code):
    result = []
    depth = 0
    in_lambda = False
    lambda_depth = None
    i = 0
    while i < len(code):
        if code[i:i+2] == '->' and not in_lambda:
            j = i + 2
            while j < len(code) and code[j] in ' \t\n':
                j += 1
            if j < len(code) and code[j] == '{':
                in_lambda = True
                lambda_depth = depth + 1
                result.append(code[i:j+1])
                depth += 1
                i = j + 1
                continue
        if code[i] == '{':
            depth += 1
        elif code[i] == '}':
            depth -= 1
            if in_lambda and depth < lambda_depth:
                in_lambda = False
                lambda_depth = None
        if not in_lambda:
            result.append(code[i])
        i += 1
    return ''.join(result)

def find_block_starts(code, keyword_re):
    """Find start positions (just after opening {) for all blocks introduced by keyword_re,
    skipping over any parenthesized resource/condition between keyword and {."""
    positions = []
    for m in re.finditer(keyword_re, code):
        i = m.end()
        while i < len(code) and code[i] in ' \t\n':
            i += 1
        if i < len(code) and code[i] == '(':
            depth = 1
            i += 1
            while i < len(code) and depth > 0:
                if code[i] == '(':
                    depth += 1
                elif code[i] == ')':
                    depth -= 1
                i += 1
            while i < len(code) and code[i] in ' \t\n':
                i += 1
        if i < len(code) and code[i] == '{':
            positions.append(i + 1)
    return positions

def extract_block_at(code, start):
    depth = 1
    i = start
    while i < len(code) and depth > 0:
        if code[i] == '{':
            depth += 1
        elif code[i] == '}':
            depth -= 1
        i += 1
    return code[start:i-1]

def extract_all_blocks(code, keyword_re):
    return [extract_block_at(code, pos) for pos in find_block_starts(code, keyword_re)]

def has_fail_directly(block):
    """True if fail() appears in this block but NOT nested inside a try/catch within it."""
    depth = 0
    i = 0
    while i < len(block):
        if block[i] == '{':
            depth += 1
        elif block[i] == '}':
            depth -= 1
        elif depth == 0 and block[i:].startswith('fail') and re.match(r'fail\s*\(', block[i:]):
            return True
        i += 1
    return False

def classify(prefix):
    top = strip_lambda_bodies(prefix)

    has_fail = bool(re.search(r'\bfail\s*\(', top))
    has_try  = bool(find_block_starts(top, r'\btry\b'))

    try_blocks   = extract_all_blocks(top, r'\btry\b')
    catch_blocks = extract_all_blocks(top, r'}\s*catch\b')

    has_fail_in_try     = any(re.search(r'\bfail\s*\(', b) for b in try_blocks)
    has_fail_in_catch   = any(re.search(r'\bfail\s*\(', b) for b in catch_blocks)
    has_assert_in_catch = any(re.search(r'\bassert\w*\s*\(', b) for b in catch_blocks)

    if has_fail_in_catch:
        return "MUST_NOT_THROW"
    if has_fail_in_try and has_assert_in_catch:
        return "MUST_THROW_WITH_PROPERTIES"
    if has_fail_in_try and len(catch_blocks) > 0:
        return "MUST_THROW"
    if has_assert_in_catch:
        return "IF_THROWN_ASSERT"
    if has_fail:
        return "FAIL_ONLY"
    return "ASSERTION_ONLY"

def process_folder(folder):
    project_dirs = [d for d in glob.glob(os.path.join(folder, "*")) if os.path.isdir(d)]

    for project_dir in sorted(project_dirs):
        inp_path  = os.path.join(project_dir, "dataset", "inputs_final.csv")
        meta_path = os.path.join(project_dir, "dataset", "meta_final.csv")

        if not os.path.exists(inp_path) or not os.path.exists(meta_path):
            print(f"Skipping {os.path.basename(project_dir)}: missing inputs.csv or meta.csv")
            continue

        project_name = os.path.basename(project_dir)
        inputs = pd.read_csv(inp_path)
        meta   = pd.read_csv(meta_path)

        df = pd.merge(inputs, meta, on="id", suffixes=("_in", "_meta"))
        df["oracle_type"] = df["test_prefix"].astype(str).apply(classify)

        for col in KEEP_COLS:
            if col not in df.columns:
                df[col] = ""

        out = os.path.join(project_dir, "dataset", "meta_final.csv")
        df[KEEP_COLS].to_csv(out, index=False)
        print(f"{project_name}: {len(df)} rows | {df['oracle_type'].value_counts().to_dict()}")

root = sys.argv[1] if len(sys.argv) > 1 else "projects_decomposed"
if not os.path.isdir(root):
    print(f"ERROR: folder not found: {root}")
    print("Usage: python3 map_oracles.py /path/to/projects_decomposed")
    sys.exit(1)

process_folder(root)