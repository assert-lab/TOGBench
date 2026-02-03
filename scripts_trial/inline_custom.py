#!/usr/bin/env python3
import os
import csv
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed"

def split_args(s: str):
    out = []
    cur = []
    depth = 0
    in_str = None
    esc = False
    for ch in s:
        if in_str:
            cur.append(ch)
            if esc:
                esc = False
            elif ch == "\\":
                esc = True
            elif ch == in_str:
                in_str = None
            continue
        if ch in ("'", '"'):
            in_str = ch
            cur.append(ch)
            continue
        if ch == "(":
            depth += 1
            cur.append(ch)
            continue
        if ch == ")":
            depth = max(0, depth - 1)
            cur.append(ch)
            continue
        if ch == "," and depth == 0:
            out.append("".join(cur).strip())
            cur = []
            continue
        cur.append(ch)
    tail = "".join(cur).strip()
    if tail:
        out.append(tail)
    return out

def extract_signature_and_body(method_src: str):
    if not method_src:
        return None
    s = method_src.strip()
    lb = s.find("{")
    rb = s.rfind("}")
    if lb == -1 or rb == -1 or rb <= lb:
        return None
    header = s[:lb].strip()
    body = s[lb + 1:rb].strip()
    m = re.search(r"\((.*)\)", header, flags=re.S)
    if not m:
        return None
    params_str = m.group(1).strip()
    params = []
    if params_str:
        parts = split_args(params_str)
        for p in parts:
            p = p.strip()
            if not p:
                continue
            p = re.sub(r"\s+", " ", p)
            p = re.sub(r"\bfinal\b", "", p)
            p = re.sub(r"@[\w.]+\s*", "", p)
            p = re.sub(r"\s+", " ", p).strip()
            toks = p.split(" ")
            if len(toks) < 2:
                continue
            name = toks[-1].strip()
            typ = " ".join(toks[:-1]).strip()
            if not name or not typ:
                continue
            params.append((typ, name))
    return (params, body)

def find_call_span(code: str, func: str):
    if not func:
        return None
    pat = re.compile(r"\b" + re.escape(func) + r"\s*\(")
    m = pat.search(code)
    if not m:
        return None
    start = m.start()
    i = m.end() - 1
    depth = 0
    in_str = None
    esc = False
    while i < len(code):
        ch = code[i]
        if in_str:
            if esc:
                esc = False
            elif ch == "\\":
                esc = True
            elif ch == in_str:
                in_str = None
            i += 1
            continue
        if ch in ("'", '"'):
            in_str = ch
            i += 1
            continue
        if ch == "(":
            depth += 1
        elif ch == ")":
            depth -= 1
            if depth == 0:
                i += 1
                break
        i += 1
    if depth != 0:
        return None
    j = i
    while j < len(code) and code[j].isspace():
        j += 1
    if j >= len(code) or code[j] != ";":
        return None
    end = j + 1
    args_src = code[m.end(): i - 1]
    return (start, end, args_src)

def collect_taken_names(code: str):
    taken = set()
    for nm in re.findall(r"\b([A-Za-z_][A-Za-z0-9_]*)\b\s*(?==)", code):
        taken.add(nm)
    for nm in re.findall(r"\bfor\s*\(\s*(?:final\s+)?[A-Za-z_][A-Za-z0-9_<>\[\].]*\s+([A-Za-z_][A-Za-z0-9_]*)\b", code):
        taken.add(nm)
    for nm in re.findall(r"\bcatch\s*\(\s*(?:final\s+)?[A-Za-z_][A-Za-z0-9_<>\[\].]*\s+([A-Za-z_][A-Za-z0-9_]*)\b", code):
        taken.add(nm)
    for nm in re.findall(r"\b(?:final\s+)?[A-Za-z_][A-Za-z0-9_<>\[\].]*\s+([A-Za-z_][A-Za-z0-9_]*)\s*(?:=|;|,|\))", code):
        taken.add(nm)
    return taken

def unique_name(base: str, taken: set):
    if base not in taken:
        taken.add(base)
        return base
    k = 1
    while True:
        cand = f"{base}{k}"
        if cand not in taken:
            taken.add(cand)
            return cand
        k += 1

def replace_identifiers(text: str, mapping: dict):
    if not mapping:
        return text
    keys = sorted(mapping.keys(), key=len, reverse=True)
    pat = re.compile(r"\b(" + "|".join(re.escape(k) for k in keys) + r")\b")
    return pat.sub(lambda m: mapping.get(m.group(1), m.group(1)), text)

def get_indent_at(code: str, pos: int):
    line_start = code.rfind("\n", 0, pos)
    if line_start == -1:
        line_start = 0
    else:
        line_start += 1
    j = line_start
    while j < len(code) and code[j] in (" ", "\t"):
        j += 1
    return code[line_start:j]

def inline_one(test_src: str, func: str, helper_src: str):
    sig = extract_signature_and_body(helper_src)
    if not sig:
        return ""
    params, body = sig
    span = find_call_span(test_src, func)
    if not span:
        return ""
    start, end, args_src = span
    args = split_args(args_src.strip())
    if len(args) != len(params):
        return ""
    taken = collect_taken_names(test_src)
    rename = {}
    decls = []
    for (typ, pname), arg in zip(params, args):
        new_name = unique_name(pname, taken)
        rename[pname] = new_name
        decls.append(f"final {typ} {new_name} = {arg.strip()};")
    body2 = replace_identifiers(body, rename).strip()
    indent = get_indent_at(test_src, start)
    block_lines = []
    for d in decls:
        block_lines.append(indent + d)
    if body2:
        for ln in body2.splitlines():
            block_lines.append(indent + ln.rstrip())
    block = "\n".join(block_lines)
    return test_src[:start] + block + test_src[end:]

def process_csv(path_in: Path):
    path_out = path_in.with_name("inputs_custom_enriched_inlined.csv")
    with path_in.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        reader = csv.DictReader(f)
        if not reader.fieldnames:
            return False
        fieldnames = list(reader.fieldnames)
        if "inlined" not in fieldnames:
            fieldnames.append("inlined")
        rows = list(reader)

    changed = 0
    for row in rows:
        test_src = row.get("test_prefix", "") or ""
        func = row.get("custom_assert_name", "") or ""
        helper_src = row.get("custom_assert_method", "") or ""
        row["inlined"] = inline_one(test_src, func, helper_src)
        if row["inlined"]:
            changed += 1

    with path_out.open("w", encoding="utf-8", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        for r in rows:
            writer.writerow(r)

    print(f"{path_in.parent.parent.name}: {path_in.name} -> {path_out.name} | inlined {changed}/{len(rows)}")
    return True

def main():
    any_found = False
    for proj_dir in sorted(PROJECTS_DIR.glob("*")):
        if not proj_dir.is_dir():
            continue
        in_path = proj_dir / "dataset" / "inputs_custom_enriched.csv"
        if in_path.exists():
            any_found = True
            process_csv(in_path)
    if not any_found:
        print("no inputs_custom_enriched.csv found")

if __name__ == "__main__":
    main()
