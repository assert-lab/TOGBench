
# ===== remove non-assertrows from inputs and meta.csv =====

# python3 scripts/check_actuallyhasoracle.py
#!/usr/bin/env python3
import csv
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed"

ORACLE_PATTERNS = [
    re.compile(r"\bassert\w*\s*\(", re.M),
    re.compile(r"\bfail\s*\(", re.M),
    re.compile(r"\bthrow\s+new\s+\w", re.M),
]

def read_csv(path: Path):
    with path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        return (r.fieldnames or []), list(r)

def write_csv(path: Path, fieldnames, rows):
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        w.writeheader()
        for row in rows:
            out = {k: row.get(k, "") for k in fieldnames}
            w.writerow(out)

def append_csv(path: Path, fieldnames, rows):
    if not rows:
        return
    if not path.exists():
        write_csv(path, fieldnames, rows)
        return
    ex_fields, ex_rows = read_csv(path)
    out_fields = list(ex_fields or [])
    for k in fieldnames:
        if k not in out_fields:
            out_fields.append(k)
    combined = list(ex_rows or [])
    seen = set()
    for r in combined:
        rid = (r.get("id", "") or "").strip()
        if rid:
            seen.add(rid)
    for r in rows:
        rid = (r.get("id", "") or "").strip()
        if not rid:
            continue
        if rid in seen:
            continue
        seen.add(rid)
        rr = dict(r)
        for k in out_fields:
            rr.setdefault(k, "")
        combined.append(rr)
    write_csv(path, out_fields, combined)

def strip_comments_java(s: str) -> str:
    out = []
    i = 0
    n = len(s)
    in_str = False
    in_chr = False
    in_line = False
    in_block = False
    esc = False
    while i < n:
        c = s[i]
        nxt = s[i + 1] if i + 1 < n else ""
        if in_line:
            if c == "\n":
                in_line = False
                out.append(c)
            i += 1
            continue
        if in_block:
            if c == "*" and nxt == "/":
                in_block = False
                i += 2
            else:
                i += 1
            continue
        if in_str:
            out.append(c)
            if esc:
                esc = False
            elif c == "\\":
                esc = True
            elif c == '"':
                in_str = False
            i += 1
            continue
        if in_chr:
            out.append(c)
            if esc:
                esc = False
            elif c == "\\":
                esc = True
            elif c == "'":
                in_chr = False
            i += 1
            continue

        if c == "/" and nxt == "/":
            in_line = True
            i += 2
            continue
        if c == "/" and nxt == "*":
            in_block = True
            i += 2
            continue
        if c == '"':
            in_str = True
            out.append(c)
            i += 1
            continue
        if c == "'":
            in_chr = True
            out.append(c)
            i += 1
            continue

        out.append(c)
        i += 1
    return "".join(out)

def has_uncommented_oracle(test_prefix: str) -> bool:
    if not test_prefix:
        return False
    s = strip_comments_java(test_prefix)
    last_pos = -1
    for pat in ORACLE_PATTERNS:
        for m in pat.finditer(s):
            last_pos = max(last_pos, m.start())
    return last_pos >= 0

def main():
    total_projects = 0
    total_removed = 0
    total_kept = 0

    for proj_dir in sorted(PROJECTS_DIR.iterdir()):
        if not proj_dir.is_dir():
            continue

        inputs_final = proj_dir / "dataset" / "inputs_final.csv"
        meta_final = proj_dir / "dataset" / "meta_final.csv"

        if not inputs_final.exists() or not meta_final.exists():
            continue

        in_fields, in_rows = read_csv(inputs_final)
        meta_fields, meta_rows = read_csv(meta_final)

        if not in_fields or not meta_fields:
            continue

        if not in_rows:
            print(f"{proj_dir.name}: inputs_final empty")
            continue

        if not meta_rows:
            print(f"{proj_dir.name}: meta_final empty")
            continue

        meta_by_id = {}
        for r in meta_rows:
            rid = (r.get("id", "") or "").strip()
            if rid and rid not in meta_by_id:
                meta_by_id[rid] = r

        keep_inputs = []
        drop_inputs = []
        keep_meta = []
        drop_meta = []

        drop_ids = set()

        for r in in_rows:
            rid = (r.get("id", "") or "").strip()
            tp = r.get("test_prefix", "") or ""
            ok = has_uncommented_oracle(tp)
            if ok:
                keep_inputs.append(r)
            else:
                drop_inputs.append(r)
                if rid:
                    drop_ids.add(rid)

        for r in meta_rows:
            rid = (r.get("id", "") or "").strip()
            if rid and rid in drop_ids:
                drop_meta.append(r)
            else:
                keep_meta.append(r)

        out_inputs = proj_dir / "dataset" / "inputs_final.csv"
        out_meta = proj_dir / "dataset" / "meta_final.csv"

        write_csv(out_inputs, in_fields, keep_inputs)
        write_csv(out_meta, meta_fields, keep_meta)

        non_assert_inputs = proj_dir / "dataset_check" / "non-assert-inputs.csv"
        non_assert_meta = proj_dir / "dataset_check" / "non-assert-meta.csv"

        append_csv(non_assert_inputs, in_fields, drop_inputs)
        append_csv(non_assert_meta, meta_fields, drop_meta)

        removed = len(drop_inputs)
        kept = len(keep_inputs)

        total_projects += 1
        total_removed += removed
        total_kept += kept

        print(f"{proj_dir.name}: kept={kept} removed={removed} wrote_nonassert={removed}")

    print(f"\nDONE projects={total_projects} total_kept={total_kept} total_removed={total_removed}")

if __name__ == "__main__":
    main()