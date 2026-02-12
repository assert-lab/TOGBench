#!/usr/bin/env python3
import csv
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed"

ASSERT_PREFIXES = ("assert", "Assert.", "Assertions.", "Assume.", "MatcherAssert.")
SKIP_SIMPLE = {
    "if","for","while","switch","catch","return","throw","new","super","this","try","do","synchronized",
    "equals","hashCode","toString","getClass"
}

VAR_DECL_RE = re.compile(
    r'(^|\n)\s*(?:final\s+)?([A-Za-z_][A-Za-z0-9_$.<>]*)\s+([A-Za-z_][A-Za-z0-9_]*)\s*(?:=|;)',
    re.MULTILINE
)

CALL_SCAN_RE = re.compile(r'([A-Za-z_][A-Za-z0-9_$.]*)\s*\(')

def strip_generics(t: str) -> str:
    t = re.sub(r'<.*?>', '', t or '')
    return t.strip()

def is_assert_like(full: str, simple: str) -> bool:
    if not simple:
        return True
    if simple.startswith("assert"):
        return True
    for p in ASSERT_PREFIXES:
        if full.startswith(p):
            return True
    return False

def build_var_type_map(code: str):
    m = {}
    for mm in VAR_DECL_RE.finditer(code or ""):
        t = strip_generics(mm.group(2))
        v = mm.group(3)
        if not t:
            continue
        m[v] = t
    return m

def extract_calls_reverse(code: str):
    out = []
    lines = (code or "").splitlines()
    for line in reversed(lines):
        s = line.strip()
        if not s:
            continue
        if s.startswith("//") or s.startswith("/*") or s.startswith("*"):
            continue
        for m in reversed(list(CALL_SCAN_RE.finditer(s))):
            full = m.group(1)
            simple = full.split(".")[-1]
            if simple in SKIP_SIMPLE:
                continue
            if is_assert_like(full, simple):
                continue
            recv = None
            if "." in full:
                recv = full.rsplit(".", 1)[0].split(".")[-1]
            out.append((simple, recv))
    return out

def load_methods_csv(proj: Path):
    p = proj / "methods.csv"
    if not p.exists():
        return None
    rows = []
    with p.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        for row in r:
            cls = (row.get("classname") or "").strip()
            sig = (row.get("method") or "").strip()
            doc = row.get("docstring") or ""
            mdef = row.get("method_definition") or ""
            mname = ""
            mm = re.search(r'\b([A-Za-z_][A-Za-z0-9_]*)\s*\(', sig)
            if mm:
                mname = mm.group(1)
            rows.append({
                "classname": cls,
                "method": sig,
                "method_name": mname,
                "docstring": doc,
                "method_definition": mdef,
            })
    return rows

def build_method_index(method_rows):
    by_name = {}
    by_name_and_simple_class = {}
    for m in method_rows:
        n = m["method_name"]
        if n:
            by_name.setdefault(n, []).append(m)
            simple_cls = m["classname"].split(".")[-1] if m["classname"] else ""
            if simple_cls:
                by_name_and_simple_class.setdefault((n, simple_cls), []).append(m)
    return by_name, by_name_and_simple_class

def detect_input_prefix_col(header):
    lower = [h.strip().lower() for h in header]
    if "test_prefix" in lower:
        return header[lower.index("test_prefix")]
    if "prefix" in lower:
        return header[lower.index("prefix")]
    if "test_code" in lower:
        return header[lower.index("test_code")]
    if "test" in lower:
        return header[lower.index("test")]
    return None

def pick_best(candidates, recv_type_simple):
    if not candidates:
        return None
    if recv_type_simple:
        for c in candidates:
            if c["classname"].split(".")[-1] == recv_type_simple:
                return c
    return candidates[0]

def map_row(test_code: str, by_name, by_name_and_simple_class):
    var_types = build_var_type_map(test_code)
    calls = extract_calls_reverse(test_code)

    for call_name, recv in calls:
        recv_type = None
        recv_type_simple = None
        if recv:
            t = var_types.get(recv)
            if t:
                recv_type = t
                recv_type_simple = t.split(".")[-1]
            elif recv[:1].isupper():
                recv_type_simple = recv

        if recv_type_simple:
            cand = by_name_and_simple_class.get((call_name, recv_type_simple))
            m = pick_best(cand, recv_type_simple)
            if m:
                return m

        cand = by_name.get(call_name)
        m = pick_best(cand, recv_type_simple)
        if m:
            return m

    return None

def update_inputs(proj: Path):
    inputs_path = proj / "dataset" / "inputs.csv"
    if not inputs_path.exists():
        return (0, 0)

    method_rows = load_methods_csv(proj)
    if not method_rows:
        return (0, 0)

    by_name, by_name_and_simple_class = build_method_index(method_rows)

    with inputs_path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        reader = csv.DictReader(f)
        header = reader.fieldnames or []
        code_col = detect_input_prefix_col(header)
        rows = list(reader)

    if not rows or not code_col:
        return (len(rows), 0)

    out_cols = list(rows[0].keys())
    for c in ("mut_class", "method_under_test", "mut_docstring", "mut_method_definition"):
        if c not in out_cols:
            out_cols.append(c)

    changed = 0
    for r in rows:
        code = (r.get(code_col) or "")
        m = map_row(code, by_name, by_name_and_simple_class)

        if not m:
            if any(r.get(k, "") for k in ("mut_class","method_under_test","mut_docstring","mut_method_definition")):
                r["mut_class"] = ""
                r["method_under_test"] = ""
                r["mut_docstring"] = ""
                r["mut_method_definition"] = ""
                changed += 1
            continue

        new_class = m["classname"]
        new_method = m["method"]
        new_doc = m["docstring"]
        new_def = m["method_definition"]

        if (r.get("mut_class","") != new_class or
            r.get("method_under_test","") != new_method or
            r.get("mut_docstring","") != new_doc or
            r.get("mut_method_definition","") != new_def):
            r["mut_class"] = new_class
            r["method_under_test"] = new_method
            r["mut_docstring"] = new_doc
            r["mut_method_definition"] = new_def
            changed += 1

    if changed:
        bak = inputs_path.with_suffix(".csv.bak")
        if not bak.exists():
            bak.write_text(inputs_path.read_text(encoding="utf-8", errors="ignore"), encoding="utf-8", errors="ignore")
        with inputs_path.open("w", encoding="utf-8", newline="") as f:
            w = csv.DictWriter(f, fieldnames=out_cols)
            w.writeheader()
            for r in rows:
                w.writerow({k: r.get(k, "") for k in out_cols})

    return (len(rows), changed)

def main():
    if not PROJECTS_DIR.exists():
        print("projects_decomposed not found")
        return

    projects = [p for p in PROJECTS_DIR.iterdir() if p.is_dir()]
    total_projects = 0
    total_rows = 0
    total_updated = 0

    for proj in sorted(projects):
        nrows, nupd = update_inputs(proj)
        if nrows == 0 and nupd == 0:
            continue
        total_projects += 1
        total_rows += nrows
        total_updated += nupd
        print(f"{proj.name} rows={nrows} updated={nupd}")

    print(f"projects={total_projects} total_rows={total_rows} updated_rows={total_updated}")

if __name__ == "__main__":
    main()
