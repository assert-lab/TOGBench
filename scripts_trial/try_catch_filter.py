#!/usr/bin/env python3
import csv
import re
from pathlib import Path

TRY_TOK = re.compile(r"(?<![\w$])try(?![\w$])")
CATCH_TOK = re.compile(r"(?<![\w$])catch(?![\w$])")
FINALLY_TOK = re.compile(r"(?<![\w$])finally(?![\w$])")
TOK_HANDLER = re.compile(r"\bhandler\s*=\s*new\s+RequestHandler\s*\(\)\s*\{", re.DOTALL)

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

def _skip_ws(s: str, i: int) -> int:
    n = len(s)
    while i < n and s[i].isspace():
        i += 1
    return i

def _match_paren(s: str, i: int) -> int:
    if i >= len(s) or s[i] != "(":
        return -1
    depth = 0
    n = len(s)
    j = i
    while j < n:
        ch = s[j]
        if ch == "(":
            depth += 1
        elif ch == ")":
            depth -= 1
            if depth == 0:
                return j
        j += 1
    return -1

def _match_brace(s: str, i: int) -> int:
    if i >= len(s) or s[i] != "{":
        return -1
    depth = 0
    n = len(s)
    j = i
    while j < n:
        ch = s[j]
        if ch == "{":
            depth += 1
        elif ch == "}":
            depth -= 1
            if depth == 0:
                return j
        j += 1
    return -1

def has_unclosed_try_at_same_level(code: str) -> bool:
    s = strip_java_noise(code or "")
    brace_depth = 0
    i = 0
    n = len(s)

    while i < n:
        ch = s[i]
        if ch == "{":
            brace_depth += 1
            i += 1
            continue
        if ch == "}":
            brace_depth -= 1
            i += 1
            continue

        m = TRY_TOK.match(s, i)
        if not m:
            i += 1
            continue

        try_level = brace_depth
        j = m.end()
        j = _skip_ws(s, j)

        if j < n and s[j] == "(":
            pj = _match_paren(s, j)
            if pj == -1:
                return True
            j = pj + 1
            j = _skip_ws(s, j)

        if j >= n or s[j] != "{":
            i = m.end()
            continue

        end_block = _match_brace(s, j)
        if end_block == -1:
            return True

        k = end_block + 1
        k = _skip_ws(s, k)

        if brace_depth != try_level:
            return True

        if k >= n:
            return True

        if not (CATCH_TOK.match(s, k) or FINALLY_TOK.match(s, k)):
            return True

        i = k
    return False

def looks_trycatch_broken(code: str) -> bool:
    s = strip_java_noise(code or "")

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

    if has_unclosed_try_at_same_level(code or ""):
        return True

    if TOK_HANDLER.search(s):
        if "handler = new RequestHandler" in s and "};" not in s:
            return True

    return False

def read_csv(path: Path):
    with path.open("r", newline="", encoding="utf-8") as f:
        r = csv.DictReader(f)
        rows = list(r)
        return r.fieldnames or [], rows

def write_csv(path: Path, fieldnames, rows):
    with path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        w.writeheader()
        w.writerows(rows)

def ensure_keys(row: dict, fieldnames):
    return {k: row.get(k, "") for k in fieldnames}

def append_or_create_failed(path: Path, fieldnames, new_rows):
    if not new_rows:
        return 0
    if path.exists():
        existing_fields, existing_rows = read_csv(path)
        use_fields = existing_fields if existing_fields else fieldnames
        merged = existing_rows + [ensure_keys(r, use_fields) for r in new_rows]
        write_csv(path, use_fields, merged)
        return len(new_rows)
    write_csv(path, fieldnames, [ensure_keys(r, fieldnames) for r in new_rows])
    return len(new_rows)

def process_dataset_dir(dataset_dir: Path):
    inputs_path = dataset_dir / "inputs.csv"
    meta_path = dataset_dir / "meta.csv"
    if not inputs_path.exists() or not meta_path.exists():
        return

    in_fields, in_rows = read_csv(inputs_path)
    meta_fields, meta_rows = read_csv(meta_path)
    if not in_fields or "id" not in in_fields or "test_prefix" not in in_fields:
        return
    if not meta_fields or "id" not in meta_fields:
        return

    meta_by_id = {((r.get("id") or "").strip()): r for r in meta_rows if (r.get("id") or "").strip()}

    bad_ids = set()
    failed_inputs = []
    failed_meta = []

    for row in in_rows:
        tid = (row.get("id") or "").strip()
        code = row.get("test_prefix") or ""
        if tid and looks_trycatch_broken(code):
            bad_ids.add(tid)
            failed_inputs.append(row)
            mr = meta_by_id.get(tid)
            if mr is not None:
                failed_meta.append(mr)

    kept_inputs = [r for r in in_rows if ((r.get("id") or "").strip() not in bad_ids)]
    kept_meta = [r for r in meta_rows if ((r.get("id") or "").strip() not in bad_ids)]

    write_csv(inputs_path, in_fields, kept_inputs)
    write_csv(meta_path, meta_fields, kept_meta)

    appended_in = append_or_create_failed(dataset_dir / "inputs_failed.csv", in_fields, failed_inputs)
    appended_meta = append_or_create_failed(dataset_dir / "meta_failed.csv", meta_fields, failed_meta)

    project = dataset_dir.parent.name
    print(f"{project}: removed={len(bad_ids)} kept_inputs={len(kept_inputs)} kept_meta={len(kept_meta)} appended_failed_inputs={appended_in} appended_failed_meta={appended_meta}")

def main():
    root = Path(__file__).resolve().parent.parent
    projects_dir = root / "projects_decomposed"
    if not projects_dir.exists():
        raise SystemExit("projects_decomposed not found (run from OE25-DEV root)")

    count = 0
    for project_dir in sorted(projects_dir.iterdir()):
        dataset_dir = project_dir / "dataset"
        if dataset_dir.exists():
            process_dataset_dir(dataset_dir)
            count += 1

    print(f"done projects={count}")

if __name__ == "__main__":
    main()
