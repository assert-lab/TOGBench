
# ls -1 projects_decomposed | xargs -n 1 -P 4 -I{} python3 scripts/test_failed_tests.py --project "{}"

#!/usr/bin/env python3
import csv
import re
import subprocess
from pathlib import Path
from typing import List, Tuple, Optional
import argparse

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed"


def read_csv(path: Path) -> Tuple[List[str], List[dict]]:
    if not path.exists():
        return [], []
    with path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        rows = list(r)
        return (r.fieldnames or [], rows)


def write_csv(path: Path, fieldnames: List[str], rows: List[dict]):
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        w.writeheader()
        for row in rows:
            rr = dict(row)
            for k in fieldnames:
                rr.setdefault(k, "")
            w.writerow(rr)


def append_rows(path: Path, fieldnames: List[str], new_rows: List[dict]):
    if not new_rows:
        return

    existing_fields, existing_rows = read_csv(path)

    # Merge fieldnames
    out_fields = list(existing_fields or [])
    for k in fieldnames:
        if k not in out_fields:
            out_fields.append(k)

    # Combine existing + new
    combined = []
    seen_ids = set()

    for row in (existing_rows or []) + new_rows:
        rid = row.get("id", "")
        if not rid:
            continue
        if rid in seen_ids:
            continue
        seen_ids.add(rid)

        rr = dict(row)
        for k in out_fields:
            rr.setdefault(k, "")
        combined.append(rr)

    write_csv(path, out_fields, combined)



def canonical_test_name(name: str) -> str:
    name = re.sub(r"_serial_oe.*$", "", name)
    name = re.sub(r"_oe.*$", "", name)
    name = re.sub(r"_\d+$", "", name)
    return name


def rename_class_and_ctors(source: str, old_class: str, new_class: str) -> str:
    pattern_class = re.compile(r"(class\s+)" + re.escape(old_class) + r"(\b)")
    source = pattern_class.sub(r"\1" + new_class + r"\2", source, count=1)
    pattern_ctor = re.compile(r"\b" + re.escape(old_class) + r"\s*\(")
    source = pattern_ctor.sub(new_class + "(", source)
    pattern_other = re.compile(r"\b" + re.escape(old_class) + r"\b")
    source = pattern_other.sub(new_class, source)
    return source


def count_braces_ignoring_literals(text: str) -> int:
    in_string = False
    in_char = False
    escape_next = False
    brace_count = 0
    i = 0
    while i < len(text):
        ch = text[i]
        if escape_next:
            escape_next = False
            i += 1
            continue
        if ch == "\\" and (in_string or in_char):
            escape_next = True
            i += 1
            continue
        if i + 1 < len(text) and text[i:i+2] == "//" and not in_string and not in_char:
            break
        if ch == '"' and not in_char:
            in_string = not in_string
        elif ch == "'" and not in_string:
            in_char = not in_char
        elif not in_string and not in_char:
            if ch == "{":
                brace_count += 1
            elif ch == "}":
                brace_count -= 1
        i += 1
    return brace_count


def remove_matching_test_methods(source: str, base_names: set) -> str:
    if not base_names:
        return source

    lines = source.splitlines(keepends=True)
    to_delete = set()
    i = 0

    while i < len(lines):
        m = re.search(r"\bvoid\s+([A-Za-z0-9_]+)\s*\(", lines[i])
        if m:
            name = m.group(1)
            cname = canonical_test_name(name)
            if cname in base_names:
                start = i
                while start > 0 and lines[start - 1].lstrip().startswith("@"):
                    start -= 1
                while start > 0 and lines[start - 1].strip() == "":
                    start -= 1

                brace_depth = 0
                found_open = False
                j = i
                while j < len(lines):
                    brace_depth += count_braces_ignoring_literals(lines[j])
                    if brace_depth > 0:
                        found_open = True
                    if found_open and brace_depth == 0:
                        j += 1
                        break
                    j += 1

                for k in range(start, j):
                    to_delete.add(k)
                i = j
                continue
        i += 1

    return "".join(ln for idx, ln in enumerate(lines) if idx not in to_delete)


def find_class_closing_brace(lines: List[str], class_name: str) -> int:
    class_decl_idx = None
    pat = re.compile(r"\bclass\s+" + re.escape(class_name) + r"\b")
    for idx, line in enumerate(lines):
        if pat.search(line):
            class_decl_idx = idx
            break

    if class_decl_idx is None:
        for idx in range(len(lines) - 1, -1, -1):
            if "}" in lines[idx]:
                return idx
        return len(lines) - 1

    depth = 0
    seen_first = False
    close_idx = len(lines) - 1
    for idx in range(class_decl_idx, len(lines)):
        depth += count_braces_ignoring_literals(lines[idx])
        if depth > 0:
            seen_first = True
        if seen_first and depth == 0:
            close_idx = idx
            break
    return close_idx


def build_one_test_block(test_prefix: str) -> str:
    s = test_prefix or ""
    if not s.endswith("\n"):
        s += "\n"
    return "\n" + s + "\n"


def method_name_from_prefix(prefix: str) -> str:
    m = re.search(r"\bvoid\s+([A-Za-z_][A-Za-z0-9_]*)\s*\(", prefix or "")
    return m.group(1) if m else ""


def get_package_name(java_src: str) -> str:
    m = re.search(r"^\s*package\s+([A-Za-z_][A-Za-z0-9_.]*)\s*;", java_src, flags=re.M)
    return m.group(1) if m else ""


def find_module_root(file_path: Path) -> Optional[Path]:
    cur = file_path.parent
    while True:
        if (cur / "pom.xml").exists():
            return cur
        if cur.parent == cur:
            return None
        cur = cur.parent


def run_mvn(module_dir: Path, fqcn: str, method_name: str, log_path: Path) -> bool:
    cmd = [
        "mvn",
        "-q",
        "-DskipTests=false",
        f"-Dtest={fqcn}#{method_name}",
        "test",
        "--color=never",
    ]
    p = subprocess.run(
        cmd,
        cwd=str(module_dir),
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
        text=True,
    )
    out = p.stdout or ""
    log_path.write_text(out, encoding="utf-8", errors="ignore")
    if "No tests were executed" in out:
        return False
    return p.returncode == 0


def rebuild_one_row(project_dir: Path, meta_row: dict, inputs_row: dict) -> Tuple[Path, Path, str, str]:
    test_file_path = meta_row.get("test_file_path", "")
    test_class = meta_row.get("test_class", "")
    if not test_file_path or not test_class:
        raise RuntimeError("missing_test_file_path_or_test_class")

    orig_path = project_dir / test_file_path
    if not orig_path.exists():
        raise FileNotFoundError(str(orig_path))

    injected = inputs_row.get("test_prefix", "") or ""

    injected_method = method_name_from_prefix(injected)
    if not injected_method:
        tn = inputs_row.get("test_name", "") or meta_row.get("test_name", "")
        if "::" in tn:
            tn = tn.split("::", 1)[1]
        injected_method = tn.strip()
    if not injected_method:
        raise RuntimeError("cannot_find_method_name_in_test_prefix")

    tid = (inputs_row.get("id", "") or "").strip()
    new_class = f"{test_class}_OE25Dev_{tid}" if tid else f"{test_class}_OE25Dev"
    if not new_class[-1:].isalnum():
        new_class += "_X"
    new_path = orig_path.parent / f"{new_class}.java"

    src = orig_path.read_text(encoding="utf-8", errors="ignore")
    src = rename_class_and_ctors(src, test_class, new_class)
    src = remove_matching_test_methods(src, {canonical_test_name(injected_method)})

    lines = src.splitlines(keepends=True)
    close_idx = find_class_closing_brace(lines, new_class)

    new_lines = []
    new_lines.extend(lines[:close_idx])
    new_lines.append(build_one_test_block(injected))
    new_lines.append(lines[close_idx])
    new_lines.extend(lines[close_idx + 1:])

    new_src = "".join(new_lines)
    new_path.write_text(new_src, encoding="utf-8", errors="ignore")

    pkg = get_package_name(new_src)
    fqcn = f"{pkg}.{new_class}" if pkg else new_class

    module_root = find_module_root(new_path)
    if module_root is None:
        module_root = project_dir

    return new_path, module_root, fqcn, injected_method


def delete_old_logs(dataset_dir: Path):
    for p in dataset_dir.glob("mvn_try_*.log"):
        try:
            p.unlink()
        except Exception:
            pass


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--project", required=True)
    args = ap.parse_args()

    proj_dir = PROJECTS_DIR / args.project
    if not proj_dir.is_dir():
        raise SystemExit(f"project not found: {args.project}")

    dataset_dir = proj_dir / "dataset_left"
    inputs_failed = dataset_dir / "inputs_left_filtered.csv"
    meta_failed = dataset_dir / "meta_left_filtered.csv"
    if not inputs_failed.exists() or not meta_failed.exists():
        print(f"[skip] {args.project} missing inputs_left_filtered/meta_left_filtered")
        return

    delete_old_logs(dataset_dir)

    in_fields, in_rows = read_csv(inputs_failed)
    meta_fields, meta_rows = read_csv(meta_failed)
    if not in_rows or not meta_rows or not in_fields or not meta_fields:
        print(f"[skip] {args.project} empty failed files")
        return

    meta_by_id = {r.get("id", ""): r for r in meta_rows if r.get("id")}

    still_failed_inputs = []
    still_failed_meta = []
    passed_inputs = []
    passed_meta = []

    print(f"\n=== {args.project} ===", flush=True)
    print(f"failed_rows={len(in_rows)}", flush=True)

    for idx, row in enumerate(in_rows, 1):
        tid = row.get("id", "")
        meta = meta_by_id.get(tid)
        if not tid or not meta:
            still_failed_inputs.append(row)
            continue

        new_path = None
        mvn_log = dataset_dir / f"mvn_try_{tid}.log"

        try:
            new_path, module_root, fqcn, method_name = rebuild_one_row(proj_dir, meta, row)
            ok = run_mvn(module_root, fqcn, method_name, mvn_log)
        except Exception as e:
            mvn_log.write_text(str(e) + "\n", encoding="utf-8", errors="ignore")
            ok = False
        finally:
            if new_path is not None:
                try:
                    new_path.unlink()
                except Exception:
                    pass

        if ok:
            passed_inputs.append(row)
            passed_meta.append(meta)
            try:
                mvn_log.unlink()
            except Exception:
                pass
        else:
            still_failed_inputs.append(row)
            still_failed_meta.append(meta)

        if idx % 10 == 0 or idx == len(in_rows):
            print(
                f"progress={idx}/{len(in_rows)} passed_now={len(passed_inputs)} still_failed={len(still_failed_inputs)}",
                flush=True,
            )

    write_csv(dataset_dir / "inputs_passed.csv", in_fields, passed_inputs)
    write_csv(dataset_dir / "meta_passed.csv", meta_fields, passed_meta)


    write_csv(inputs_failed, in_fields, still_failed_inputs)
    write_csv(meta_failed, meta_fields, still_failed_meta)

    print(f"passed_now={len(passed_inputs)} still_failed={len(still_failed_inputs)}", flush=True)
    print("\nDONE", flush=True)



if __name__ == "__main__":
    main()
