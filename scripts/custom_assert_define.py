#scripts/custom_assert_define.py
#!/usr/bin/env python3
import os
import re
import csv
import argparse
from pathlib import Path
from typing import Optional, Tuple, Dict, List


def read_text(path: Path) -> str:
    return path.read_text(encoding="utf-8", errors="ignore")


def find_call_name_in_test(test_prefix: str) -> Optional[str]:
    candidates = []
    for m in re.finditer(r"\bassert[A-Za-z0-9_]*\s*\(", test_prefix):
        candidates.append((m.start(), m.group(0)))
    if not candidates:
        return None
    _, tok = candidates[-1]
    return tok.split("(", 1)[0].strip()


def extract_method_from_java(code: str, method_name: str) -> Optional[str]:
    pat = re.compile(rf"\b{re.escape(method_name)}\s*\(")
    for m in pat.finditer(code):
        call_idx = m.start()

        start = code.rfind("\n", 0, call_idx)
        if start == -1:
            start = 0
        else:
            start += 1

        scan_start = max(0, start - 2000)
        segment = code[scan_start:call_idx]
        lines = segment.splitlines()

        best_line_idx = None
        for i in range(len(lines) - 1, -1, -1):
            ln = lines[i].strip()
            if not ln:
                continue
            if ln.startswith("@"):
                continue
            if "class " in ln or "interface " in ln or "enum " in ln:
                continue
            best_line_idx = i
            break

        if best_line_idx is None:
            decl_start = start
        else:
            prefix = "\n".join(lines[:best_line_idx])
            decl_start = scan_start + (len(prefix) + (1 if prefix else 0))

        brace_open = code.find("{", call_idx)
        semi = code.find(";", call_idx)
        if semi != -1 and (brace_open == -1 or semi < brace_open):
            continue
        if brace_open == -1:
            continue

        i = brace_open
        depth = 0
        in_str = False
        in_char = False
        in_line = False
        in_block = False
        esc = False
        while i < len(code):
            ch = code[i]
            nxt = code[i + 1] if i + 1 < len(code) else ""

            if in_line:
                if ch == "\n":
                    in_line = False
                i += 1
                continue

            if in_block:
                if ch == "*" and nxt == "/":
                    in_block = False
                    i += 2
                    continue
                i += 1
                continue

            if in_str:
                if esc:
                    esc = False
                elif ch == "\\":
                    esc = True
                elif ch == '"':
                    in_str = False
                i += 1
                continue

            if in_char:
                if esc:
                    esc = False
                elif ch == "\\":
                    esc = True
                elif ch == "'":
                    in_char = False
                i += 1
                continue

            if ch == "/" and nxt == "/":
                in_line = True
                i += 2
                continue
            if ch == "/" and nxt == "*":
                in_block = True
                i += 2
                continue
            if ch == '"':
                in_str = True
                i += 1
                continue
            if ch == "'":
                in_char = True
                i += 1
                continue

            if ch == "{":
                depth += 1
            elif ch == "}":
                depth -= 1
                if depth == 0:
                    return code[decl_start : i + 1].rstrip() + "\n"
            i += 1

    return None


def iter_java_files(project_root: Path, subdir: str) -> List[Path]:
    base = project_root / subdir
    if not base.exists():
        return []
    out = []
    for root, _, files in os.walk(base):
        for f in files:
            if f.endswith(".java"):
                out.append(Path(root) / f)
    return out


def find_method_in_project(project_root: Path, method_name: str, test_file_path: Optional[str]) -> Tuple[Optional[str], Optional[str]]:
    if test_file_path:
        p = project_root / test_file_path
        if p.exists() and p.is_file():
            code = read_text(p)
            m = extract_method_from_java(code, method_name)
            if m:
                return m, str(p.relative_to(project_root))

    for p in iter_java_files(project_root, "src/test/java"):
        try:
            code = read_text(p)
        except Exception:
            continue
        m = extract_method_from_java(code, method_name)
        if m:
            return m, str(p.relative_to(project_root))

    for p in iter_java_files(project_root, "src/main/java"):
        try:
            code = read_text(p)
        except Exception:
            continue
        m = extract_method_from_java(code, method_name)
        if m:
            return m, str(p.relative_to(project_root))

    for root, _, files in os.walk(project_root):
        for f in files:
            if f.endswith(".java"):
                p = Path(root) / f
                try:
                    code = read_text(p)
                except Exception:
                    continue
                m = extract_method_from_java(code, method_name)
                if m:
                    return m, str(p.relative_to(project_root))

    return None, None


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--project_root", required=True)
    ap.add_argument("--dataset_dir", default="dataset")
    ap.add_argument("--inputs_custom", default="inputs_custom.csv")
    ap.add_argument("--meta_custom", default="meta_custom.csv")
    ap.add_argument("--out_inputs", default="inputs_custom_enriched.csv")
    ap.add_argument("--out_meta", default="meta_custom_enriched.csv")
    args = ap.parse_args()

    project_root = Path(args.project_root).resolve()
    dataset_dir = (project_root / args.dataset_dir).resolve()

    inputs_path = dataset_dir / args.inputs_custom
    meta_path = dataset_dir / args.meta_custom
    out_inputs_path = dataset_dir / args.out_inputs
    out_meta_path = dataset_dir / args.out_meta

    if not inputs_path.exists():
        raise FileNotFoundError(str(inputs_path))
    if not meta_path.exists():
        raise FileNotFoundError(str(meta_path))

    meta_by_id: Dict[str, Dict[str, str]] = {}
    with meta_path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        for row in r:
            meta_by_id[row["id"]] = row

    inputs_rows = []
    missing_method = 0
    missing_call = 0

    with inputs_path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        for row in r:
            rid = row["id"]
            test_prefix = row.get("test_prefix", "")
            meta = meta_by_id.get(rid, {})

            method_name = meta.get("assert_name", "") or find_call_name_in_test(test_prefix)
            if not method_name:
                missing_call += 1
                row["custom_assert_name"] = ""
                row["custom_assert_method"] = ""
                row["custom_assert_file"] = ""
                inputs_rows.append(row)
                continue

            test_file_path = meta.get("test_file_path", "") or None
            method_code, found_in = find_method_in_project(project_root, method_name, test_file_path)

            row["custom_assert_name"] = method_name
            row["custom_assert_method"] = method_code or ""
            row["custom_assert_file"] = found_in or ""

            if not method_code:
                missing_method += 1

            inputs_rows.append(row)

    out_fieldnames_inputs = []
    if inputs_rows:
        base_fields = list(inputs_rows[0].keys())
        for k in ["custom_assert_name", "custom_assert_method", "custom_assert_file"]:
            if k not in base_fields:
                base_fields.append(k)
        out_fieldnames_inputs = base_fields

    with out_inputs_path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=out_fieldnames_inputs)
        w.writeheader()
        w.writerows(inputs_rows)

    meta_rows = []
    with meta_path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        for row in r:
            rid = row["id"]
            row["custom_assert_name"] = row.get("assert_name", "")
            meta_rows.append(row)

    out_fieldnames_meta = []
    if meta_rows:
        base_fields = list(meta_rows[0].keys())
        if "custom_assert_name" not in base_fields:
            base_fields.append("custom_assert_name")
        out_fieldnames_meta = base_fields

    with out_meta_path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=out_fieldnames_meta)
        w.writeheader()
        w.writerows(meta_rows)

    if missing_call:
        print(f"[WARN] rows with no custom assert call found: {missing_call}")
    if missing_method:
        print(f"[WARN] rows where custom assert method not found: {missing_method}")


if __name__ == "__main__":
    main()
