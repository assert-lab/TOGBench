# python3 scripts/build_multiple_dataset.py projects_decomposed/bcel/all_test_methods.csv projects_decomposed/bcel projects_decomposed/bcel/dataset_multiple
#!/usr/bin/env python3
import csv
import re
import sys
from pathlib import Path
import re

ORACLE_LINE_RE = re.compile(
    r'(\bassert[A-Z_]\w*\s*\()|(\bassertThat\s*\()|(\bfail\s*\()|(\bassume[A-Z_]\w*\s*\()|(\bAssert\.\w+\s*\()|(\bAssertions\.\w+\s*\()'
)
TRY_RE = re.compile(r'^\s*try\s*\{')
CATCH_RE = re.compile(r'\bcatch\s*\(')
SIG_NAME_RE = re.compile(r'\b([A-Za-z_]\w*)\s*\(')
TEST_ANNOT_RE = re.compile(r'@Test\b')


def method_name_from_def(method_def: str) -> str:
    if not method_def:
        return ""
    for ln in method_def.splitlines():
        s = ln.strip()
        if not s:
            continue
        m = SIG_NAME_RE.search(s)
        if m:
            return m.group(1)
    return ""


def has_trycatch_oracle(method_def: str) -> bool:
    lines = method_def.splitlines(True)
    n = len(lines)
    i = 0
    while i < n:
        if TRY_RE.match(lines[i]):
            depth = 0
            seen_catch = False
            j = i
            while j < n:
                ln = lines[j]
                if CATCH_RE.search(ln):
                    seen_catch = True
                depth += ln.count("{") - ln.count("}")
                if depth == 0 and j > i:
                    block = "".join(lines[i:j+1])
                    if seen_catch and ORACLE_LINE_RE.search(block):
                        return True
                    break
                j += 1
            i = j + 1
        else:
            i += 1
    return False


def has_oracle(method_def: str) -> bool:
    if not method_def:
        return False
    if ORACLE_LINE_RE.search(method_def):
        return True
    return has_trycatch_oracle(method_def)


def class_simple_name(classname: str) -> str:
    return (classname or "").split(".")[-1]


def build_test_index(project_root: Path):
    idx = {}
    for base in project_root.rglob("src/test/java"):
        if not base.is_dir():
            continue
        for f in base.rglob("*.java"):
            rel = f.relative_to(base).as_posix()
            if not rel.endswith(".java"):
                continue
            fqn = rel[:-5].replace("/", ".")
            idx.setdefault(fqn, f)
    return idx


def resolve_test_file(test_index, classname: str):
    cand = (classname or "").strip()
    while cand:
        p = test_index.get(cand)
        if p is not None:
            return p
        if "." not in cand:
            break
        cand = cand.rsplit(".", 1)[0]
    return None


def is_test_method(row, method_def: str) -> bool:
    if TEST_ANNOT_RE.search(method_def or ""):
        return True
    m = (row.get("method") or "").strip()
    if m.startswith("void test") or m.startswith("test"):
        return True
    name = method_name_from_def(method_def)
    if name.startswith("test"):
        return True
    return False


def read_rows(in_csv: Path):
    with in_csv.open("r", encoding="utf-8", newline="") as f:
        r = csv.DictReader(f)
        rows = list(r)
        return r.fieldnames or [], rows


def write_csv(path: Path, fieldnames, rows):
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n", quoting=csv.QUOTE_MINIMAL)
        w.writeheader()
        for row in rows:
            w.writerow(row)


def main():
    if len(sys.argv) < 4:
        print("usage: python3 build_multiple_dataset.py INPUT.csv PROJECT_ROOT OUT_DIR")
        sys.exit(2)

    in_csv = Path(sys.argv[1])
    project_root = Path(sys.argv[2])
    out_dir = Path(sys.argv[3])

    _, rows = read_rows(in_csv)

    test_index = build_test_index(project_root)

    inputs_out = []
    meta_out = []

    total = 0
    in_test_src = 0
    test_like = 0
    kept = 0
    missing_test_file = 0
    no_oracle = 0

    counter = 0
    project = project_root.name

    for row in rows:
        total += 1
        classname = (row.get("classname") or "").strip()
        method_def = row.get("method_definition") or ""

        test_file = resolve_test_file(test_index, classname)
        if test_file is None:
            missing_test_file += 1
            continue
        in_test_src += 1

        if not is_test_method(row, method_def):
            continue
        test_like += 1

        if not has_oracle(method_def):
            no_oracle += 1
            continue

        counter += 1
        rid = f"{project}_{counter}"

        mname = method_name_from_def(method_def) or (row.get("method") or "").strip()
        tclass = class_simple_name(classname)

        # strip any trailing _oe if present
        if mname.endswith("_oe"):
            base_name = mname[:-3]
        else:
            base_name = mname

        test_name = f"{base_name}_oe"

        old_name = mname
        new_name = test_name

        # rename method definition safely
        pattern = re.compile(r'(\b%s\s*\()' % re.escape(old_name))
        method_def = pattern.sub(new_name + "(", method_def, count=1)

        rel_path = test_file.relative_to(project_root)

        inputs_out.append({
            "id": rid,
            "test_prefix": method_def,
            "test_name": f"{tclass}::{test_name}",
        })

        meta_out.append({
            "id": rid,
            "project": project,
            "test_class": tclass,
            "test_name": test_name,
            "test_file_path": str(rel_path),
            "focal_file_path": "",
            "focal_class": "",
            "focal_package": "",
            "oracle_type": "multiple",
            "junit_version": "unknown",
            "assert_kind": "standard",
            "assert_name": "",
        })

        kept += 1

    inputs_fields = ["id", "test_prefix", "test_name"]
    meta_fields = [
        "id", "project", "test_class", "test_name", "test_file_path",
        "focal_file_path", "focal_class", "focal_package",
        "oracle_type", "junit_version", "assert_kind", "assert_name"
    ]

    write_csv(out_dir / "inputs_multiple.csv", inputs_fields, inputs_out)
    write_csv(out_dir / "meta_multiple.csv", meta_fields, meta_out)

    print(f"total_rows={total}")
    print(f"in_test_src={in_test_src}")
    print(f"test_like={test_like}")
    print(f"kept={kept}")
    print(f"missing_test_file={missing_test_file}")
    print(f"no_oracle={no_oracle}")
    print(str(out_dir / "inputs_multiple.csv"))
    print(str(out_dir / "meta_multiple.csv"))


if __name__ == "__main__":
    main()