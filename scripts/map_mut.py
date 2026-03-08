# python3 scripts/map_mut.py

#!/usr/bin/env python3
import csv
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed"

# ---------------------------------------------------------------------------
# Constants
# ---------------------------------------------------------------------------

ASSERT_PREFIXES = (
    # ── JUnit 4 (org.junit.Assert) ──────────────────────────────────────────
    "Assert.",
    "assertEquals", "assertNotEquals",
    "assertTrue", "assertFalse",
    "assertNull", "assertNotNull",
    "assertSame", "assertNotSame",
    "assertArrayEquals",
    "assertThat",
    "fail",
    # ── JUnit 5 (org.junit.jupiter.api.Assertions) ──────────────────────────
    "Assertions.",
    "assertAll",
    "assertDoesNotThrow",
    "assertInstanceOf",
    "assertIterableEquals",
    "assertLinesMatch",
    "assertThrows",
    "assertThrowsExactly",
    "assertTimeout",
    "assertTimeoutPreemptively",
    # ── JUnit 5 Assumptions ──────────────────────────────────────────────────
    "Assumptions.",
    "assumeTrue", "assumeFalse",
    "assumingThat",
    # ── JUnit 4 Assume ───────────────────────────────────────────────────────
    "Assume.",
    # ── AssertJ ──────────────────────────────────────────────────────────────
    "assertThatCode",
    "assertThatException",
    "assertThatExceptionOfType",
    "assertThatIllegalArgumentException",
    "assertThatIllegalStateException",
    "assertThatIOException",
    "assertThatNullPointerException",
    "assertThatObject",
    "assertThatNoException",
    "assertThatThrownBy",
    "assertWith",
    "catchThrowable",
    "catchThrowableOfType",
    "SoftAssertions.",
    "BDDAssertions.",
    "BDDSoftAssertions.",
    "then",
    "thenCode",
    "thenThrownBy",
    "thenExceptionOfType",
    "thenNoException",
    # ── TestNG ────────────────────────────────────────────────────────────────
    "assertEqualsNoOrder",
    "assertEqualsDeep",
    "assertNotEqualsDeep",
    "assertListEquals",
    "assertSetEquals",
    "assertUnorderedEquals",
)

SKIP_SIMPLE = {
    "if", "for", "while", "switch", "catch", "return", "throw", "new",
    "super", "this", "try", "do", "synchronized", "equals", "hashCode",
    "toString", "getClass", "makeEmptyPool", "makeKey", "getNthObject",
    "setUp", "tearDown",
}

VAR_DECL_RE = re.compile(
    r'(^|\n)\s*(?:final\s+)?([A-Za-z_][A-Za-z0-9_$.<>\[\]]*)\s+([A-Za-z_][A-Za-z0-9_]*)\s*(?:=|;)',
    re.MULTILINE,
)
CALL_SCAN_RE  = re.compile(r'([A-Za-z_][A-Za-z0-9_$.]*)\s*\(')
INNER_CALL_RE = re.compile(
    r'(?:assert\w*|Assert\.\w+|assertEquals|assertThat)\s*\(\s*'
    r'(?:[^,)]+,\s*)?'
    r'([A-Za-z_][A-Za-z0-9_.]*)\s*\('
)

# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def strip_generics(t: str) -> str:
    return re.sub(r'<.*?>', '', t or '').strip()


def is_assert_like(full: str, simple: str) -> bool:
    if not simple:
        return True
    for p in ASSERT_PREFIXES:
        if full == p or full.startswith(p):
            return True
    return False


def build_var_type_map(code: str) -> dict:
    result = {}
    for mm in VAR_DECL_RE.finditer(code or ""):
        t = strip_generics(mm.group(2))
        v = mm.group(3)
        if t and v:
            result[v] = t
    return result


def extract_calls_reverse(code: str) -> list:
    out   = []
    lines = (code or "").splitlines()
    for line in reversed(lines):
        s = line.strip()
        if not s or s.startswith("//") or s.startswith("/*") or s.startswith("*"):
            continue
        inner = INNER_CALL_RE.search(s)
        if inner:
            full   = inner.group(1)
            simple = full.split(".")[-1]
            recv   = full.rsplit(".", 1)[0].split(".")[-1] if "." in full else None
            if simple and simple not in SKIP_SIMPLE:
                out.append((simple, recv))
            continue
        for m in reversed(list(CALL_SCAN_RE.finditer(s))):
            full   = m.group(1)
            simple = full.split(".")[-1]
            if simple in SKIP_SIMPLE or is_assert_like(full, simple):
                continue
            recv = full.rsplit(".", 1)[0].split(".")[-1] if "." in full else None
            out.append((simple, recv))
    return out


def focal_class_hint(test_name: str) -> str:
    if not test_name:
        return ""
    cls_part = test_name.split("::")[0].strip()
    if cls_part.lower().startswith("test"):
        return cls_part[4:]
    if cls_part.lower().endswith("test"):
        return cls_part[:-4]
    return cls_part

# ---------------------------------------------------------------------------
# Load / index methods.csv
# ---------------------------------------------------------------------------

def load_methods_csv(proj: Path):
    p = proj / "methods.csv"
    if not p.exists():
        return None
    rows = []
    with p.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        for row in csv.DictReader(f):
            sig   = (row.get("method") or "").strip()
            mname = ""
            mm = re.search(r'\b([A-Za-z_][A-Za-z0-9_]*)\s*\(', sig)
            if mm:
                mname = mm.group(1)
            rows.append({
                "classname":         (row.get("classname") or "").strip(),
                "method":            sig,
                "method_name":       mname,
                "docstring":         (row.get("docstring") or "").strip(),
                "method_definition": (row.get("method_definition") or "").strip(),
                "focal_file_path":   (row.get("focal_file_path") or "").strip(),
                "focal_package":     (row.get("focal_package") or "").strip(),
                "focal_class":       (row.get("focal_class") or "").strip(),
            })
    return rows


def build_method_index(method_rows):
    by_name                  = {}
    by_name_and_simple_class = {}
    for m in method_rows:
        n = m["method_name"]
        if not n:
            continue
        by_name.setdefault(n, []).append(m)
        for cls_field in ("classname", "focal_class"):
            simple = m[cls_field].split(".")[-1] if m[cls_field] else ""
            if simple:
                by_name_and_simple_class.setdefault((n, simple), []).append(m)
    return by_name, by_name_and_simple_class


def pick_best(candidates, recv_type_simple):
    if not candidates:
        return None
    if recv_type_simple:
        for c in candidates:
            if c["classname"].split(".")[-1] == recv_type_simple:
                return c
            if c["focal_class"].split(".")[-1] == recv_type_simple:
                return c
    return candidates[0]


def map_row(test_code: str, test_name: str, by_name, by_name_and_simple_class):
    hint      = focal_class_hint(test_name)
    var_types = build_var_type_map(test_code)
    calls     = extract_calls_reverse(test_code)

    for call_name, recv in calls:
        if hint:
            cand = by_name_and_simple_class.get((call_name, hint))
            if cand:
                return cand[0]

        recv_type_simple = None
        if recv:
            t = var_types.get(recv)
            if t:
                recv_type_simple = strip_generics(t).split(".")[-1]
            elif recv[:1].isupper():
                recv_type_simple = recv

        if recv_type_simple:
            cand = by_name_and_simple_class.get((call_name, recv_type_simple))
            m = pick_best(cand, recv_type_simple)
            if m:
                return m

        cand = by_name.get(call_name, [])
        if hint:
            for c in cand:
                if hint in c["classname"] or hint in c["focal_class"]:
                    return c
        if cand:
            return cand[0]

    return None

# ---------------------------------------------------------------------------
# CSV helpers
# ---------------------------------------------------------------------------

def detect_col(header, *candidates):
    lower = [h.strip().lower() for h in header]
    for cand in candidates:
        if cand in lower:
            return header[lower.index(cand)]
    return None


def read_csv(path: Path):
    with path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        reader = csv.DictReader(f)
        return list(reader.fieldnames or []), list(reader)


def write_csv(path: Path, fieldnames: list, rows: list):
    bak = path.with_suffix(".csv.bak")
    if not bak.exists():
        bak.write_text(
            path.read_text(encoding="utf-8", errors="ignore"),
            encoding="utf-8",
            errors="ignore",
        )
    with path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames)
        w.writeheader()
        for r in rows:
            w.writerow({k: r.get(k, "") for k in fieldnames})

# ---------------------------------------------------------------------------
# Column sets
# ---------------------------------------------------------------------------

# inputs.csv  ← full method body + docstring only
INPUTS_FOCAL_COLS = ["focal_method", "docstring"]

# meta.csv    ← file/package/class location info
META_FOCAL_COLS = ["focal_file_path", "focal_class", "focal_package"]

# ---------------------------------------------------------------------------
# Per-project update
# ---------------------------------------------------------------------------

def update_project(proj: Path):
    dataset_dir = proj / "dataset"
    inputs_path = dataset_dir / "inputs_final.csv"
    meta_path   = dataset_dir / "meta_final.csv"

    if not inputs_path.exists():
        return (0, 0, 0)

    method_rows = load_methods_csv(proj)
    if not method_rows:
        return (0, 0, 0)

    by_name, by_name_and_simple_class = build_method_index(method_rows)

    # --- read inputs ---
    inp_header, inp_rows = read_csv(inputs_path)
    code_col = detect_col(inp_header, "test_prefix", "prefix", "test_code", "test")
    name_col = detect_col(inp_header, "test_name", "name", "test_method")
    id_col   = detect_col(inp_header, "id")

    if not inp_rows or not code_col:
        return (len(inp_rows), 0, 0)

    # --- read meta (optional) ---
    has_meta = meta_path.exists()
    if has_meta:
        meta_header, meta_rows = read_csv(meta_path)
        meta_id_col = detect_col(meta_header, "id")
        meta_idx    = (
            {r.get(meta_id_col, ""): i for i, r in enumerate(meta_rows)}
            if meta_id_col else {}
        )
    else:
        meta_header = meta_rows = []
        meta_id_col = None
        meta_idx    = {}

    # ensure output columns exist
    inp_out_cols = list(inp_rows[0].keys())
    for c in INPUTS_FOCAL_COLS:
        if c not in inp_out_cols:
            inp_out_cols.append(c)

    meta_out_cols = []
    if has_meta and meta_rows:
        meta_out_cols = list(meta_rows[0].keys())
        for c in META_FOCAL_COLS:
            if c not in meta_out_cols:
                meta_out_cols.append(c)

    inp_changed  = 0
    meta_changed = 0

    for r in inp_rows:
        code      = r.get(code_col) or ""
        test_name = r.get(name_col, "") if name_col else ""
        row_id    = r.get(id_col,   "") if id_col   else ""

        m = map_row(code, test_name, by_name, by_name_and_simple_class)

        # ── inputs.csv: focal_method (full body) + docstring ─────────────────
        if not m:
            if any(r.get(k, "") for k in INPUTS_FOCAL_COLS):
                for k in INPUTS_FOCAL_COLS:
                    r[k] = ""
                inp_changed += 1
        else:
            new_inp = {
                "focal_method": m["method_definition"],  # full method body
                "docstring":    m["docstring"],
            }
            if any(r.get(k, "") != v for k, v in new_inp.items()):
                r.update(new_inp)
                inp_changed += 1

        # ── meta.csv: focal_file_path, focal_class, focal_package ────────────
        if has_meta and meta_id_col and row_id in meta_idx:
            mr = meta_rows[meta_idx[row_id]]
            if not m:
                if any(mr.get(k, "") for k in META_FOCAL_COLS):
                    for k in META_FOCAL_COLS:
                        mr[k] = ""
                    meta_changed += 1
            else:
                new_meta = {
                    "focal_file_path": m["focal_file_path"],
                    "focal_class":     m["focal_class"],
                    "focal_package":   m["focal_package"],
                }
                if any(mr.get(k, "") != v for k, v in new_meta.items()):
                    mr.update(new_meta)
                    meta_changed += 1

    if inp_changed:
        write_csv(inputs_path, inp_out_cols, inp_rows)
    if has_meta and meta_changed:
        write_csv(meta_path, meta_out_cols, meta_rows)

    return (len(inp_rows), inp_changed, meta_changed)

# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main():
    if not PROJECTS_DIR.exists():
        print("projects_decomposed not found")
        return

    projects       = sorted(p for p in PROJECTS_DIR.iterdir() if p.is_dir())
    total_projects = 0
    total_rows     = 0
    total_inp      = 0
    total_meta     = 0

    for proj in projects:
        nrows, ninp, nmeta = update_project(proj)
        if nrows == 0:
            continue
        total_projects += 1
        total_rows     += nrows
        total_inp      += ninp
        total_meta     += nmeta
        print(f"{proj.name}  rows={nrows}  inputs_updated={ninp}  meta_updated={nmeta}")

    print(
        f"\nprojects={total_projects}  total_rows={total_rows}"
        f"  inputs_updated={total_inp}  meta_updated={total_meta}"
    )


if __name__ == "__main__":
    main()