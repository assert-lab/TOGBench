# python3 scripts/make_left_decomposed.py bcel
#!/usr/bin/env python3
import os
import csv
import re
import sys
from typing import List, Tuple

_STRING_RE = re.compile(r'"(?:\\.|[^"\\])*"')
_CHAR_RE = re.compile(r"'(?:\\.|[^'\\])'")

STANDARD_JUNIT_ASSERT_NAMES = {
    "assertEquals",
    "assertNotEquals",
    "assertTrue",
    "assertFalse",
    "assertNull",
    "assertNotNull",
    "assertSame",
    "assertNotSame",
    "assertArrayEquals",
    "assertIterableEquals",
    "assertLinesMatch",
    "assertAll",
    "assertThat",
    "assertThrows",
    "assertDoesNotThrow",
    "assertTimeout",
    "assertTimeoutPreemptively",
    "assertEqualsTypeNotNull",
    "fail",
}

JAVA_ASSERT_STMT = re.compile(r"^\s*assert\b")

_ASSERT_CALL_NAME_RE = re.compile(
    r"(?:\bAssertions\s*\.\s*|"
    r"\borg\.junit[^\s;]*\.\s*Assertions\s*\.\s*|"
    r"\borg\.junit[^\s;]*\.\s*Assert\s*\.\s*)?"
    r"(?P<name>[A-Za-z_][A-Za-z0-9_]*)\s*\("
)

_JSOUP_METHOD_DECL = re.compile(
    r"^\s*(?:@\w+(?:\([^)]*\))?\s+)*"
    r"(?:(?:public|protected|private)\s+)?"
    r"(?:static\s+)?"
    r"(?:final\s+)?"
    r"[\w<>\[\], ?]+\s+"
    r"(?P<name>[A-Za-z_][A-Za-z0-9_]*)\s*\("
)

JUNIT3_TEST_HEADER = re.compile(r"\s*public\s+void\s+test[A-Za-z0-9_]*\s*\(")

JUNIT_TEST_ANNOTATIONS = (
    "@Test",
    "@org.junit.Test",
    "@org.junit.jupiter.api.Test",
    "@ParameterizedTest",
    "@org.junit.jupiter.params.ParameterizedTest",
    "@RepeatedTest",
    "@TestFactory",
    "@TestTemplate",
)

def _strip_line_comment(s: str) -> str:
    idx = s.find("//")
    return s if idx < 0 else s[:idx]

def sanitize_for_counting(line: str, in_block_comment: bool) -> Tuple[str, bool]:
    s = line
    out_parts = []
    i = 0
    while i < len(s):
        if in_block_comment:
            end = s.find("*/", i)
            if end == -1:
                return ("", True)
            i = end + 2
            in_block_comment = False
        else:
            start = s.find("/*", i)
            if start == -1:
                out_parts.append(s[i:])
                break
            out_parts.append(s[i:start])
            i = start + 2
            in_block_comment = True
    s2 = "".join(out_parts)
    s2 = _STRING_RE.sub('""', s2)
    s2 = _CHAR_RE.sub("''", s2)
    s2 = _strip_line_comment(s2)
    return (s2, in_block_comment)

def classify_assert_line(line: str, *, in_block_comment: bool = False) -> Tuple[str | None, str | None]:
    sanitized, _ = sanitize_for_counting(line, in_block_comment)
    s = sanitized.strip()
    if not s:
        return (None, None)
    if JAVA_ASSERT_STMT.match(s):
        return ("java_assert", "assert")
    if "assert" not in s and "fail(" not in s:
        return (None, None)
    m = _ASSERT_CALL_NAME_RE.search(s)
    if not m:
        return (None, None)
    name = m.group("name")
    if name == "fail":
        return ("standard", name)
    if name.startswith("assert"):
        if name in STANDARD_JUNIT_ASSERT_NAMES:
            return ("standard", name)
        return ("custom", name)
    return (None, None)

def is_assert_line(line: str, *, in_block_comment: bool = False) -> bool:
    kind, _ = classify_assert_line(line, in_block_comment=in_block_comment)
    return kind is not None

def get_method_header(lines: List[str]) -> str:
    in_block = False
    for ln in lines:
        sanitized, in_block = sanitize_for_counting(ln, in_block)
        s = sanitized.strip()
        if not s:
            continue
        if s.startswith("@"):
            continue
        if "class " in s or "interface " in s:
            continue
        if "(" in s:
            return ln
    return ""

def get_method_header_jsoup(lines: List[str]) -> str:
    in_block = False
    for ln in lines:
        sanitized, in_block = sanitize_for_counting(ln, in_block)
        s = sanitized.strip()
        if not s:
            continue
        if "class " in s or "interface " in s:
            continue
        if _JSOUP_METHOD_DECL.match(sanitized):
            return ln
    return ""

def get_method_name_from_header(header: str) -> str:
    if not header:
        return "testMethod"
    before_paren = header.split("(", 1)[0]
    tokens = re.findall(r"[A-Za-z_][A-Za-z0-9_]*", before_paren)
    return tokens[-1] if tokens else "testMethod"

def extract_oracle_block(lines: List[str], start_idx: int) -> Tuple[List[str], int]:
    block: List[str] = []
    paren_balance = 0
    i = start_idx
    in_block = False
    while i < len(lines):
        ln = lines[i]
        block.append(ln)
        sanitized, in_block = sanitize_for_counting(ln, in_block)
        paren_balance += sanitized.count("(") - sanitized.count(")")
        if ";" in sanitized and paren_balance <= 0:
            break
        i += 1
    return block, i

def rebalance_braces(lines: List[str]) -> List[str]:
    in_block = False
    opens = closes = 0
    for ln in lines:
        sanitized, in_block = sanitize_for_counting(ln, in_block)
        opens += sanitized.count("{")
        closes += sanitized.count("}")
    diff = opens - closes
    if diff <= 0:
        return lines
    indent = ""
    for ln in lines:
        if ln.strip() and not ln.strip().startswith("@"):
            indent = ln[: len(ln) - len(ln.lstrip())]
            break
    for _ in range(diff):
        lines.append(indent + "}\n")
    return lines

def collapse_oracle_blocks(method_lines: List[str]) -> Tuple[List[str], List[int], List[Tuple[str, str]]]:
    out: List[str] = []
    oracle_idxs: List[int] = []
    oracle_kinds: List[Tuple[str, str]] = []
    i = 0
    in_block = False
    while i < len(method_lines):
        ln = method_lines[i]
        kind, name = classify_assert_line(ln, in_block_comment=in_block)
        if kind is not None:
            block, end_idx = extract_oracle_block(method_lines, i)
            indent = ln[: len(ln) - len(ln.lstrip())]
            joined = " ".join(x.strip() for x in block if x.strip())
            if not joined.endswith(";"):
                joined = joined + ";"
            out.append(indent + joined + "\n")
            oracle_idxs.append(len(out) - 1)
            oracle_kinds.append((kind, name if name else ""))
            for k in range(i, end_idx + 1):
                _, in_block = sanitize_for_counting(method_lines[k], in_block)
            i = end_idx + 1
            continue
        out.append(ln)
        _, in_block = sanitize_for_counting(ln, in_block)
        i += 1
    return out, oracle_idxs, oracle_kinds

def build_single_oracle_test(
    method_lines: List[str],
    oracle_start_idx: int,
    new_method_name: str,
    *,
    is_jsoup: bool = False,
) -> str:
    lines = method_lines[:]
    header = get_method_header_jsoup(lines) if is_jsoup else get_method_header(lines)
    if header:
        header_idx = lines.index(header)
        orig_name = get_method_name_from_header(header)
        lines[header_idx] = header.replace(orig_name, new_method_name, 1)
    oracle_indices: List[int] = []
    in_block = False
    for i, ln in enumerate(lines):
        if is_assert_line(ln, in_block_comment=in_block):
            oracle_indices.append(i)
        _, in_block = sanitize_for_counting(ln, in_block)
    out: List[str] = []
    i = 0
    while i < len(lines):
        ln = lines[i]
        if i < oracle_start_idx:
            if i in oracle_indices:
                indent = ln[: len(ln) - len(ln.lstrip())]
                out.append(indent + "// removed other assertion\n")
                i += 1
                continue
            out.append(ln)
            i += 1
            continue
        if i == oracle_start_idx:
            out.append(ln)
            i += 1
            continue
        i += 1
    out = rebalance_braces(out)
    while out and not out[0].strip():
        out.pop(0)
    if out and not out[-1].endswith("\n"):
        out[-1] += "\n"
    return "".join(out)

def get_project_prefix(project: str) -> str:
    p = project.lower()
    if p.startswith("commons-"):
        suffix = p.split("commons-")[1]
        return suffix[:4]
    return p.split("-")[0]

def find_test_files(project_path: str):
    for root, _, files in os.walk(project_path):
        if os.sep + "src" + os.sep + "test" + os.sep + "java" + os.sep in root:
            for f in files:
                if f.endswith(".java"):
                    yield os.path.join(root, f)

def _line_has_test_annotation(sanitized_line: str) -> bool:
    s = sanitized_line.strip()
    if not s.startswith("@"):
        return False
    return any(ann in s for ann in JUNIT_TEST_ANNOTATIONS)

def extract_test_methods(file_content: str) -> List[List[str]]:
    lines = file_content.splitlines(keepends=True)
    methods = []
    i = 0
    n = len(lines)
    in_block = False
    while i < n:
        line = lines[i]
        sanitized, in_block = sanitize_for_counting(line, in_block)
        if _line_has_test_annotation(sanitized):
            buf = [line]
            i += 1
            local_in_block = in_block
            sanitized_body, local_in_block = sanitize_for_counting(line, local_in_block)
            brace_balance = sanitized_body.count("{") - sanitized_body.count("}")
            saw_open = sanitized_body.count("{") > 0
            while i < n:
                ln = lines[i]
                sanitized_ln, local_in_block = sanitize_for_counting(ln, local_in_block)
                if (_line_has_test_annotation(sanitized_ln) or JUNIT3_TEST_HEADER.match(sanitized_ln)):
                    if saw_open and brace_balance <= 0:
                        break
                buf.append(ln)
                opens = sanitized_ln.count("{")
                closes = sanitized_ln.count("}")
                brace_balance += opens - closes
                if opens > 0:
                    saw_open = True
                if saw_open and brace_balance == 0:
                    i += 1
                    break
                i += 1
            methods.append(buf)
            in_block = local_in_block
            continue
        if JUNIT3_TEST_HEADER.match(sanitized):
            buf = [line]
            i += 1
            local_in_block = in_block
            sanitized_header, local_in_block = sanitize_for_counting(line, local_in_block)
            brace_balance = sanitized_header.count("{") - sanitized_header.count("}")
            saw_open = sanitized_header.count("{") > 0
            while i < n:
                ln = lines[i]
                sanitized_ln, local_in_block = sanitize_for_counting(ln, local_in_block)
                if (_line_has_test_annotation(sanitized_ln) or JUNIT3_TEST_HEADER.match(sanitized_ln)):
                    if saw_open and brace_balance <= 0:
                        break
                buf.append(ln)
                opens = sanitized_ln.count("{")
                closes = sanitized_ln.count("}")
                brace_balance += opens - closes
                if opens > 0:
                    saw_open = True
                if saw_open and brace_balance == 0:
                    i += 1
                    break
                i += 1
            methods.append(buf)
            in_block = local_in_block
            continue
        i += 1
    return methods

def simple_classname(fq: str) -> str:
    s = (fq or "").strip().strip(".")
    if not s:
        return ""
    return s.split(".")[-1].strip()

def extract_method_name_from_sig(sig: str) -> str:
    s = (sig or "").strip()
    if "(" not in s:
        return ""
    pre = s.split("(", 1)[0].strip()
    if not pre:
        return ""
    return pre.split()[-1].strip()

def normalize_after_first_oe(name: str) -> str:
    s = (name or "").strip()
    if not s:
        return ""
    if "_oe" in s:
        return s.split("_oe", 1)[0] + "_oe"
    return s

def build_method_lines(annotations: str, method_def: str) -> List[str]:
    ann = (annotations or "").replace("\r\n", "\n").replace("\r", "\n").strip()
    mdef = (method_def or "").replace("\r\n", "\n").replace("\r", "\n").strip()
    if not mdef:
        return []
    if mdef.lstrip().startswith("@"):
        txt = mdef
    else:
        txt = (ann + "\n" + mdef).strip() if ann else mdef
    if not txt.endswith("\n"):
        txt += "\n"
    return txt.splitlines(keepends=True)

def build_testfile_map(project_path: str, *, is_jsoup: bool) -> dict:
    out = {}
    for test_file in find_test_files(project_path):
        rel_path = os.path.relpath(test_file, project_path)
        test_class = os.path.basename(test_file).replace(".java", "")
        try:
            with open(test_file, "r", encoding="utf-8", errors="ignore") as f:
                code = f.read()
        except Exception:
            continue
        methods = extract_test_methods(code)
        for method_lines in methods:
            header = get_method_header_jsoup(method_lines) if is_jsoup else get_method_header(method_lines)
            base_name = get_method_name_from_header(header)
            key = f"{test_class}::{base_name}"
            out[key] = rel_path
    return out

def main():
    if len(sys.argv) < 2:
        print("usage: python3 make_left_decomposed.py <project> [projects_root]")
        return

    project = sys.argv[1]
    projects_root = sys.argv[2] if len(sys.argv) >= 3 else "projects_decomposed"
    project_path = os.path.join(projects_root, project)
    if not os.path.isdir(project_path):
        print("missing project dir:", project_path)
        return

    methods_left_path = os.path.join(project_path, "methods_left.csv")
    if not os.path.isfile(methods_left_path):
        print("missing:", methods_left_path)
        return

    out_dir = os.path.join(project_path, "dataset_left")
    os.makedirs(out_dir, exist_ok=True)

    inputs_left_path = os.path.join(out_dir, "inputs_left.csv")
    meta_left_path = os.path.join(out_dir, "meta_left.csv")

    prefix = get_project_prefix(project)
    is_jsoup = project.lower() == "jsoup"

    testfile_map = build_testfile_map(project_path, is_jsoup=is_jsoup)

    rows_inputs = []
    rows_meta = []

    global_test_counter = 1

    meta_fields = [
        "id",
        "project",
        "test_class",
        "test_name",
        "test_file_path",
        "focal_file_path",
        "focal_class",
        "focal_package",
        "oracle_type",
        "junit_version",
        "assert_kind",
        "assert_name",
    ]

    with open(methods_left_path, "r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        need = {"classname", "method", "docstring", "annotations", "method_definition"}
        if not r.fieldnames or not need.issubset(set(r.fieldnames)):
            print("methods_left.csv missing required columns")
            return

        for row in r:
            cls_fq = (row.get("classname") or "").strip()
            test_class = simple_classname(cls_fq)
            sig = (row.get("method") or "").strip()
            method_name = extract_method_name_from_sig(sig)
            if not test_class or not method_name:
                continue

            key = f"{test_class}::{method_name}"
            rel_path = testfile_map.get(key, "")

            method_lines = build_method_lines(row.get("annotations", ""), row.get("method_definition", ""))
            if not method_lines:
                continue

            try:
                collapsed_lines, oracle_idxs, oracle_kinds = collapse_oracle_blocks(method_lines)
            except IndexError:
                continue

            if not oracle_idxs:
                continue

            header = get_method_header_jsoup(collapsed_lines) if is_jsoup else get_method_header(collapsed_lines)
            base_name = get_method_name_from_header(header)

            oracle_counter = 1
            for j, idx in enumerate(oracle_idxs):
                kind, name = oracle_kinds[j]
                new_method_name = normalize_after_first_oe(f"{base_name}_{oracle_counter}_oe")
                test_id = f"{prefix}_left_{global_test_counter}_{oracle_counter}"

                full_test = build_single_oracle_test(
                    collapsed_lines,
                    idx,
                    new_method_name,
                    is_jsoup=is_jsoup,
                )

                rows_inputs.append({
                    "id": test_id,
                    "test_prefix": full_test,
                    "test_name": f"{test_class}::{new_method_name}",
                })

                rows_meta.append({
                    "id": test_id,
                    "project": project,
                    "test_class": test_class,
                    "test_name": new_method_name,
                    "test_file_path": rel_path,
                    "focal_file_path": "",
                    "focal_class": "",
                    "focal_package": "",
                    "oracle_type": "assert",
                    "junit_version": "unknown",
                    "assert_kind": kind,
                    "assert_name": name,
                })

                oracle_counter += 1

            global_test_counter += 1

    with open(inputs_left_path, "w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=["id", "test_prefix", "test_name"], lineterminator="\n")
        w.writeheader()
        w.writerows(rows_inputs)

    with open(meta_left_path, "w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=meta_fields, lineterminator="\n")
        w.writeheader()
        w.writerows(rows_meta)

    print("wrote", inputs_left_path, "rows=", len(rows_inputs))
    print("wrote", meta_left_path, "rows=", len(rows_meta))

if __name__ == "__main__":
    main()
