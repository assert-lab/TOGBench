#!/usr/bin/env python3
import csv
import os
import re
import shutil

BASE = os.path.join(os.getcwd(), "dataset")
INPUTS = os.path.join(BASE, "inputs.csv")
META = os.path.join(BASE, "meta.csv")

def strip_java_noise(s: str) -> str:
    out = []
    i = 0
    n = len(s)
    in_sl_comment = False
    in_ml_comment = False
    in_str = False
    in_chr = False
    esc = False
    while i < n:
        c = s[i]
        nxt = s[i + 1] if i + 1 < n else ""

        if in_sl_comment:
            if c == "\n":
                in_sl_comment = False
                out.append("\n")
            i += 1
            continue

        if in_ml_comment:
            if c == "*" and nxt == "/":
                in_ml_comment = False
                i += 2
            else:
                i += 1
            continue

        if in_str:
            if esc:
                esc = False
            elif c == "\\":
                esc = True
            elif c == '"':
                in_str = False
            i += 1
            continue

        if in_chr:
            if esc:
                esc = False
            elif c == "\\":
                esc = True
            elif c == "'":
                in_chr = False
            i += 1
            continue

        if c == "/" and nxt == "/":
            in_sl_comment = True
            i += 2
            continue
        if c == "/" and nxt == "*":
            in_ml_comment = True
            i += 2
            continue
        if c == '"':
            in_str = True
            i += 1
            continue
        if c == "'":
            in_chr = True
            i += 1
            continue

        out.append(c)
        i += 1
    return "".join(out)

TOK_TRY = re.compile(r"\btry\b")
TOK_CATCH = re.compile(r"\bcatch\b")
TOK_FINALLY = re.compile(r"\bfinally\b")
TOK_HANDLER = re.compile(r"\bhandler\s*=\s*new\s+RequestHandler\s*\(\)\s*\{", re.DOTALL)

def looks_trycatch_broken(code: str) -> bool:
    s = strip_java_noise(code)

    brace = 0
    paren = 0
    for ch in s:
        if ch == "{":
            brace += 1
        elif ch == "}":
            brace -= 1
            if brace < 0:
                return True
        elif ch == "(":
            paren += 1
        elif ch == ")":
            paren -= 1
            if paren < 0:
                return True
    if brace != 0 or paren != 0:
        return True

    for m in TOK_TRY.finditer(s):
        after = s[m.end():]
        if not (TOK_CATCH.search(after) or TOK_FINALLY.search(after)):
            return True

    if TOK_HANDLER.search(s):
        if "handler = new RequestHandler" in s and "};" not in s:
            return True

    return False

def read_csv(path: str):
    with open(path, "r", newline="", encoding="utf-8") as f:
        r = csv.DictReader(f)
        rows = list(r)
        return r.fieldnames, rows

def write_csv(path: str, fieldnames, rows):
    with open(path, "w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        w.writeheader()
        for row in rows:
            w.writerow(row)

def main():
    if not os.path.exists(INPUTS) or not os.path.exists(META):
        raise SystemExit("Expected dataset/inputs.csv and dataset/meta.csv under current folder")

    shutil.copy2(INPUTS, INPUTS + ".bak")
    shutil.copy2(META, META + ".bak")

    in_fields, in_rows = read_csv(INPUTS)
    if "id" not in in_fields or "test_prefix" not in in_fields:
        raise SystemExit("inputs.csv must contain columns: id, test_prefix")

    bad_ids = set()
    for row in in_rows:
        tid = (row.get("id") or "").strip()
        code = row.get("test_prefix") or ""
        if tid and looks_trycatch_broken(code):
            bad_ids.add(tid)

    kept_inputs = [r for r in in_rows if (r.get("id") or "").strip() not in bad_ids]

    meta_fields, meta_rows = read_csv(META)
    if "id" not in meta_fields:
        raise SystemExit("meta.csv must contain column: id")

    kept_meta = [r for r in meta_rows if (r.get("id") or "").strip() not in bad_ids]

    write_csv(INPUTS, in_fields, kept_inputs)
    write_csv(META, meta_fields, kept_meta)

    print(f"removed_ids={len(bad_ids)}")
    print(f"inputs_kept={len(kept_inputs)} meta_kept={len(kept_meta)}")
    print("backups: dataset/inputs.csv.bak dataset/meta.csv.bak")

main()
