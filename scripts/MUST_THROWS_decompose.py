#python3 scripts/MUST_THROWS_decompose.py
import csv
import re
from pathlib import Path
import glob

projects = glob.glob("projects_decomposed/*")

try_re = re.compile(r'\btry\b')
catch_re = re.compile(r'\bcatch\b')
fail_re = re.compile(r'\bfail\s*\(')
assert_re = re.compile(r'\bassert\w*\s*\(')

def split_lines(s):
    return [l + "\n" for l in s.splitlines()]

def extract_method_header(lines):
    """Returns all lines from start up to and including the method opening brace."""
    depth = 0
    for i, line in enumerate(lines):
        depth += line.count("{") - line.count("}")
        if depth > 0:  # we just opened the method body
            return lines[:i+1]
    return lines[:1]  # fallback

def extract_blocks(lines):
    blocks = []
    i = 0
    while i < len(lines):
        if try_re.search(lines[i]):
            start = i
            depth = 0
            j = i
            while j < len(lines):
                depth += lines[j].count("{") - lines[j].count("}")
                if depth == 0 and j > i:
                    break
                j += 1
            blocks.append((start, j))
            i = j + 1
        else:
            i += 1
    return blocks

for proj in projects:

    inp = f"{proj}/dataset_check/inputs_try_fail_catch_multi.csv"
    meta = f"{proj}/dataset_multiple/meta_multiple.csv"

    if not Path(inp).exists():
        continue

    out_inp = f"{proj}/dataset_MUST_THROW/inputs_renamed.csv"
    out_meta = f"{proj}/dataset_MUST_THROW/meta_renamed.csv"

    Path(out_inp).parent.mkdir(parents=True, exist_ok=True)

    with open(inp, newline="", encoding="utf8") as f:
        rows = list(csv.DictReader(f))
        if not rows:
            print(proj, "empty inputs file")
            continue
        fields = rows[0].keys()

    meta_rows = {}
    meta_fields = []
    if Path(meta).exists():
        with open(meta, newline="", encoding="utf8") as f:
            r = csv.DictReader(f)
            meta_fields = r.fieldnames
            for m in r:
                meta_rows[m["id"]] = m

    out_rows = []
    out_meta_rows = []

    for row in rows:
        code = row["test_prefix"]
        lines = split_lines(code)
        blocks = extract_blocks(lines)
        method_header = extract_method_header(lines)
        method_close = "    }\n"  # closing brace of the method

        counter = 101
        base_id = "_".join(row["id"].split("_")[:2])
        class_name, method = row["test_name"].split("::")

        for s, e in blocks:
            block = lines[s:e+1]

            if not any(fail_re.search(x) for x in block):
                continue

            new_block = []
            fail_seen = False

            for l in block:
                if assert_re.search(l):
                    continue
                if fail_re.search(l):
                    if fail_seen:
                        continue
                    fail_seen = True
                new_block.append(l)
                if catch_re.search(l) and "{" in l:
                    continue

            new_method = f"{method}_{counter}_oe"
            new_id = f"{base_id}_{counter}"

            # rename method in header
            renamed_header = []
            for l in method_header:
                renamed_header.append(re.sub(re.escape(method) + r'\b', new_method, l))

            new_code = "".join(renamed_header + new_block + [method_close])

            r = row.copy()
            r["id"] = new_id
            r["test_prefix"] = new_code
            r["test_name"] = f"{class_name}::{new_method}"
            out_rows.append(r)


            if row["id"] in meta_rows:
                m = meta_rows[row["id"]].copy()
                m["id"] = new_id
                m["test_name"] = new_method
                out_meta_rows.append(m)

            counter += 1

    with open(out_inp, "w", newline="", encoding="utf8") as f:
        w = csv.DictWriter(f, fieldnames=fields)
        w.writeheader()
        w.writerows(out_rows)

    if meta_fields:
        with open(out_meta, "w", newline="", encoding="utf8") as f:
            w = csv.DictWriter(f, fieldnames=meta_fields)
            w.writeheader()
            w.writerows(out_meta_rows)

    print(proj, "inputs", len(out_rows), "meta", len(out_meta_rows))