# python3 scripts/fix_compile_tests/empty_trycatch_remove.py

import os, csv, re, argparse

EMPTY_LINE_RE = re.compile(r'^\s*(//\s*removed other assertion\s*)?$', re.MULTILINE)

TRY_CATCH_RE = re.compile(
    r'''
    \btry\s*\{
        (?P<try_body>[^{}]*?)
    \}\s*
    (?P<catches>(?:catch\s*\([^)]*\)\s*\{(?P<catch_body>[^{}]*?)\}\s*)+)
    ''',
    re.DOTALL | re.VERBOSE
)

CATCH_BLOCK_RE = re.compile(r'catch\s*\([^)]*\)\s*\{(?P<body>[^{}]*?)\}', re.DOTALL)

def _is_effectively_empty(body: str) -> bool:
    if body is None:
        return True
    for line in body.splitlines():
        if not EMPTY_LINE_RE.match(line):
            return False
    return True

def remove_empty_try_catch_blocks(s: str) -> str:
    if not s:
        return s
    prev = None
    cur = s
    while prev != cur:
        prev = cur

        def repl(m: re.Match) -> str:
            try_body = m.group("try_body")
            catches = m.group("catches")

            if not _is_effectively_empty(try_body):
                return m.group(0)

            catch_bodies = [cm.group("body") for cm in CATCH_BLOCK_RE.finditer(catches)]
            if not catch_bodies:
                return m.group(0)

            if any(not _is_effectively_empty(b) for b in catch_bodies):
                return m.group(0)

            return ""

        cur = TRY_CATCH_RE.sub(repl, cur)

    cur = re.sub(r'\n{3,}', '\n\n', cur)
    return cur

def read_csv_rows(path: str):
    with open(path, "r", encoding="utf-8", newline="") as f:
        reader = csv.DictReader(f)
        rows = list(reader)
        return reader.fieldnames, rows

def write_csv_rows(path: str, fieldnames, rows):
    with open(path, "w", encoding="utf-8", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames, quoting=csv.QUOTE_MINIMAL)
        writer.writeheader()
        writer.writerows(rows)

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--root", default=None)
    args = ap.parse_args()

    script_dir = os.path.dirname(os.path.abspath(__file__))
    root = args.root
    if root is None:
        root = os.path.abspath(os.path.join(script_dir, "..", ".."))

    projects_root = os.path.join(root, "projects_decomposed")

    print("ROOT:", root)
    print("PROJECTS_ROOT:", projects_root)

    if not os.path.isdir(projects_root):
        raise SystemExit(f"missing projects_decomposed at: {projects_root}")

    total_changed = 0
    total_rows = 0

    for proj in sorted(os.listdir(projects_root)):
        proj_dir = os.path.join(projects_root, proj)
        if not os.path.isdir(proj_dir):
            continue

        all_dir = os.path.join(proj_dir, "dataset")
        inputs_path = os.path.join(all_dir, "inputs_failed.csv")
        meta_path = os.path.join(all_dir, "meta_failed.csv")
        if not (os.path.isfile(inputs_path) and os.path.isfile(meta_path)):
            continue

        in_fields, inputs_rows = read_csv_rows(inputs_path)
        meta_fields, meta_rows = read_csv_rows(meta_path)

        if "id" not in in_fields or "test_prefix" not in in_fields:
            print(f"{proj}: skip (inputs missing id/test_prefix)")
            continue
        if "id" not in meta_fields:
            print(f"{proj}: skip (meta missing id)")
            continue

        meta_by_id = {r["id"]: r for r in meta_rows}

        changed = 0
        out_inputs = []
        out_meta = []
        changed_ids = set()

        for r in inputs_rows:
            rid = r.get("id", "")
            tp = r.get("test_prefix", "")
            fixed = remove_empty_try_catch_blocks(tp)
            if fixed != tp:
                rr = dict(r)
                rr["test_prefix"] = fixed
                out_inputs.append(rr)
                changed_ids.add(rid)
                changed += 1

        for rid in changed_ids:
            mr = meta_by_id.get(rid)
            if mr is not None:
                out_meta.append(mr)


        out_inputs_path = os.path.join(all_dir, "inputs_compile_fixed.csv")
        out_meta_path = os.path.join(all_dir, "meta_compile_fixed.csv")

        write_csv_rows(out_inputs_path, in_fields, out_inputs)
        write_csv_rows(out_meta_path, meta_fields, out_meta)

        total_changed += changed
        total_rows += len(inputs_rows)
        print(f"{proj}: rows={len(inputs_rows)} changed={changed}")

    print(f"DONE. total_rows={total_rows} total_changed={total_changed}")

if __name__ == "__main__":
    main()
