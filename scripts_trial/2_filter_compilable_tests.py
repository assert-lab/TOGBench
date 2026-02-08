# import csv
# import os
# from pathlib import Path

# from tree_sitter import Language, Parser

# # Path to your compiled Tree-sitter library
# # (same as in extract_project.py)
# JAVA = Language("build/my-languages.so", "java")
# parser = Parser()
# parser.set_language(JAVA)


# def wrap_in_dummy_class(test_prefix: str) -> str:
#     """
#     Wrap the test_prefix inside a dummy Java class so Tree-sitter
#     can parse it as a full compilation unit.
#     We do NOT touch the internal structure of test_prefix.
#     """
#     return "class Dummy {\n" + test_prefix + "\n}\n"


# def is_syntax_ok(test_prefix: str) -> bool:
#     """
#     Use Tree-sitter to check if the wrapped test code has any parse errors.
#     This catches:
#       - unbalanced braces
#       - missing semicolons
#       - malformed try/catch
#       - stray @Test / } / etc.
#     """
#     code = wrap_in_dummy_class(test_prefix)
#     tree = parser.parse(code.encode("utf-8"))
#     return not tree.root_node.has_error


# def process_project_dataset(dataset_dir: Path):
#     inputs_path = dataset_dir / "inputs.csv"
#     meta_path = dataset_dir / "meta.csv"

#     if not inputs_path.exists() or not meta_path.exists():
#         return

#     print(f"[syntax check] {dataset_dir.parent.name}")

#     # Load inputs
#     with inputs_path.open("r", encoding="utf-8") as f:
#         inputs_reader = csv.DictReader(f)
#         inputs_rows = list(inputs_reader)

#     # Load meta into dict by id
#     with meta_path.open("r", encoding="utf-8") as f:
#         meta_reader = csv.DictReader(f)
#         meta_rows = list(meta_reader)
#     meta_by_id = {row["id"]: row for row in meta_rows}

#     # Prepare collectors
#     kept_inputs = []
#     kept_meta = []

#     failed_inputs = []
#     failed_meta = []

#     for row in inputs_rows:
#         tid = row["id"]
#         prefix = row.get("test_prefix", "")

#         ok = is_syntax_ok(prefix)

#         meta_row = meta_by_id.get(tid)
#         if meta_row is None:
#             # If somehow meta missing, treat as failed row to be safe
#             ok = False

#         if ok:
#             kept_inputs.append(row)
#             if meta_row is not None:
#                 kept_meta.append(meta_row)
#         else:
#             failed_inputs.append(row)
#             if meta_row is not None:
#                 failed_meta.append(meta_row)

#     print(f"  kept = {len(kept_inputs)}, failed = {len(failed_inputs)}")

#     # Write back filtered main CSVs (only syntactically valid)
#     if kept_inputs:
#         with inputs_path.open("w", encoding="utf-8", newline="") as f:
#             writer = csv.DictWriter(f, fieldnames=kept_inputs[0].keys())
#             writer.writeheader()
#             writer.writerows(kept_inputs)
#     else:
#         # If nothing valid, still keep a header-only file
#         with inputs_path.open("w", encoding="utf-8", newline="") as f:
#             writer = csv.DictWriter(f, fieldnames=inputs_rows[0].keys())
#             writer.writeheader()

#     if kept_meta:
#         with meta_path.open("w", encoding="utf-8", newline="") as f:
#             writer = csv.DictWriter(f, fieldnames=kept_meta[0].keys())
#             writer.writeheader()
#             writer.writerows(kept_meta)
#     else:
#         with meta_path.open("w", encoding="utf-8", newline="") as f:
#             writer = csv.DictWriter(f, fieldnames=meta_rows[0].keys())
#             writer.writeheader()

#     # Write failed rows into separate files
#     if failed_inputs:
#         with (dataset_dir / "inputs_failed.csv").open("w", encoding="utf-8", newline="") as f:
#             writer = csv.DictWriter(f, fieldnames=failed_inputs[0].keys())
#             writer.writeheader()
#             writer.writerows(failed_inputs)

#     if failed_meta:
#         with (dataset_dir / "meta_failed.csv").open("w", encoding="utf-8", newline="") as f:
#             writer = csv.DictWriter(f, fieldnames=failed_meta[0].keys())
#             writer.writeheader()
#             writer.writerows(failed_meta)


# def main():
#     root = Path(__file__).resolve().parent.parent
#     projects_dir = root / "projects_decomposed"

#     for project_dir in projects_dir.iterdir():
#         dataset_dir = project_dir / "dataset"
#         if dataset_dir.exists():
#             process_project_dataset(dataset_dir)


# if __name__ == "__main__":
#     main()

#!/usr/bin/env python3
import csv
import re
from pathlib import Path
from tree_sitter import Language, Parser
import shutil


JAVA = Language("build/my-languages.so", "java")
parser = Parser()
parser.set_language(JAVA)

def wrap_in_dummy_class(test_prefix: str) -> str:
    return "class Dummy {\n" + (test_prefix or "") + "\n}\n"

def is_syntax_ok(test_prefix: str) -> bool:
    code = wrap_in_dummy_class(test_prefix)
    tree = parser.parse(code.encode("utf-8", errors="ignore"))
    return not tree.root_node.has_error

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

UNQUAL_FAIL_CALL = re.compile(r"(?<![\w\.])fail\s*\(")
QUAL_FAIL_CALL = re.compile(r"(?:\bAssert\.fail\s*\(|\bAssertions\.fail\s*\(|\borg\.junit\.Assert\.fail\s*\()")
NEW_ANON = re.compile(r"\bnew\s+[A-Za-z_][A-Za-z0-9_<>\.\[\]]*\s*\([^;{}]*\)\s*\{", re.DOTALL)

def find_matching_brace(s: str, open_idx: int) -> int:
    depth = 0
    for i in range(open_idx, len(s)):
        if s[i] == "{":
            depth += 1
        elif s[i] == "}":
            depth -= 1
            if depth == 0:
                return i
    return -1

def has_unqualified_fail_inside_anonymous_class(test_prefix: str) -> bool:
    s = strip_java_noise(test_prefix or "")
    if QUAL_FAIL_CALL.search(s):
        return False
    for m in NEW_ANON.finditer(s):
        brace_open = s.find("{", m.end() - 1)
        if brace_open == -1:
            continue
        brace_close = find_matching_brace(s, brace_open)
        if brace_close == -1:
            continue
        body = s[brace_open:brace_close + 1]
        if UNQUAL_FAIL_CALL.search(body):
            return True
    return False

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

def process_project_dataset(dataset_dir: Path):
    inputs_path = dataset_dir / "inputs.csv"
    meta_path = dataset_dir / "meta.csv"
    if not inputs_path.exists() or not meta_path.exists():
        return

    project_name = dataset_dir.parent.name
    print(f"[filter] {project_name}")

    with inputs_path.open("r", encoding="utf-8") as f:
        inputs_reader = csv.DictReader(f)
        inputs_rows = list(inputs_reader)

    with meta_path.open("r", encoding="utf-8") as f:
        meta_reader = csv.DictReader(f)
        meta_rows = list(meta_reader)
    meta_by_id = {row.get("id", ""): row for row in meta_rows if row.get("id")}

    kept_inputs = []
    kept_meta = []
    failed_inputs = []
    failed_meta = []

    syntax_failed = 0
    anon_fail_failed = 0
    meta_missing_failed = 0
    trycatch_failed = 0

    for row in inputs_rows:
        tid = row.get("id", "")
        prefix = row.get("test_prefix", "")

        ok = is_syntax_ok(prefix)
        if not ok:
            syntax_failed += 1

        if ok:
            if has_unqualified_fail_inside_anonymous_class(prefix):
                ok = False
                anon_fail_failed += 1

        if ok:
            if looks_trycatch_broken(prefix):
                ok = False
                trycatch_failed += 1

        meta_row = meta_by_id.get(tid)
        if meta_row is None:
            ok = False
            meta_missing_failed += 1

        if ok:
            kept_inputs.append(row)
            kept_meta.append(meta_row)
        else:
            failed_inputs.append(row)
            if meta_row is not None:
                failed_meta.append(meta_row)

    print(f"  kept={len(kept_inputs)} failed={len(failed_inputs)} syntax_failed={syntax_failed} anon_fail_failed={anon_fail_failed} trycatch_failed={trycatch_failed} meta_missing={meta_missing_failed}")

    if kept_inputs:
        with inputs_path.open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=kept_inputs[0].keys())
            writer.writeheader()
            writer.writerows(kept_inputs)
    else:
        with inputs_path.open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=inputs_rows[0].keys())
            writer.writeheader()

    if kept_meta:
        with meta_path.open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=kept_meta[0].keys())
            writer.writeheader()
            writer.writerows(kept_meta)
    else:
        with meta_path.open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=meta_rows[0].keys())
            writer.writeheader()

    if failed_inputs:
        with (dataset_dir / "inputs_failed.csv").open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=failed_inputs[0].keys())
            writer.writeheader()
            writer.writerows(failed_inputs)

    if failed_meta:
        with (dataset_dir / "meta_failed.csv").open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=failed_meta[0].keys())
            writer.writeheader()
            writer.writerows(failed_meta)



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

def read_csv(path: Path):
    with path.open("r", newline="", encoding="utf-8") as f:
        r = csv.DictReader(f)
        rows = list(r)
        return r.fieldnames, rows

def write_csv(path: Path, fieldnames, rows):
    with path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        w.writeheader()
        for row in rows:
            w.writerow(row)

def process_dataset_dir(dataset_dir: Path):
    inputs_path = dataset_dir / "inputs.csv"
    meta_path = dataset_dir / "meta.csv"

    if not inputs_path.exists() or not meta_path.exists():
        return

    shutil.copy2(inputs_path, str(inputs_path) + ".bak")
    shutil.copy2(meta_path, str(meta_path) + ".bak")

    in_fields, in_rows = read_csv(inputs_path)
    if "id" not in in_fields or "test_prefix" not in in_fields:
        return

    bad_ids = set()
    for row in in_rows:
        tid = (row.get("id") or "").strip()
        code = row.get("test_prefix") or ""
        if tid and looks_trycatch_broken(code):
            bad_ids.add(tid)

    kept_inputs = [r for r in in_rows if (r.get("id") or "").strip() not in bad_ids]

    meta_fields, meta_rows = read_csv(meta_path)
    if "id" not in meta_fields:
        return

    kept_meta = [r for r in meta_rows if (r.get("id") or "").strip() not in bad_ids]

    write_csv(inputs_path, in_fields, kept_inputs)
    write_csv(meta_path, meta_fields, kept_meta)

    project = dataset_dir.parent.name
    print(f"{project}: removed={len(bad_ids)} kept_inputs={len(kept_inputs)} kept_meta={len(kept_meta)}")


def main():
    root = Path(__file__).resolve().parent.parent
    projects_dir = root / "projects_decomposed"
    if not projects_dir.exists():
        raise SystemExit("projects_decomposed not found")

    count = 0
    for project_dir in sorted(projects_dir.iterdir()):
        dataset_dir = project_dir / "dataset"
        if dataset_dir.exists():
            process_project_dataset(dataset_dir)
            count += 1
    print(f"[done] projects={count}")

    root = Path(__file__).resolve().parent.parent
    projects_dir = root / "projects_decomposed"

    count = 0
    for project_dir in sorted(projects_dir.iterdir()):
        dataset_dir = project_dir / "dataset"
        if dataset_dir.exists():
            process_dataset_dir(dataset_dir)
            count += 1

    print(f"done projects={count}")

if __name__ == "__main__":
    main()
