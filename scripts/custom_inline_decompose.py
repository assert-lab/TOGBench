#!/usr/bin/env python3
import os
import csv
import re
from pathlib import Path
from typing import List, Tuple, Dict, Optional

PROJECTS_ROOT = Path("projects_decomposed")

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
    "fail",
}

JAVA_ASSERT_STMT = re.compile(r"^\s*assert\b")

_ASSERT_CALL_NAME_RE = re.compile(
    r"(?:\bAssertions\s*\.\s*|"
    r"\borg\.junit[^\s;]*\.\s*Assertions\s*\.\s*|"
    r"\borg\.junit[^\s;]*\.\s*Assert\s*\.\s*)?"
    r"(?P<name>[A-Za-z_][A-Za-z0-9_]*)\s*\("
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


def classify_assert_line(line: str, *, in_block_comment: bool = False) -> Tuple[Optional[str], Optional[str]]:
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


def build_single_oracle_test(method_lines: List[str], oracle_start_idx: int, new_method_name: str) -> str:
    lines = method_lines[:]

    header = get_method_header(lines)
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


def read_csv(path: Path) -> List[Dict[str, str]]:
    with path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        return list(csv.DictReader(f))


def write_csv(path: Path, rows: List[Dict[str, str]], fieldnames: List[str]) -> None:
    with path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames)
        w.writeheader()
        w.writerows(rows)


def first_existing(dataset_dir: Path, candidates: List[str]) -> Optional[Path]:
    for name in candidates:
        p = dataset_dir / name
        if p.exists():
            return p
    return None


def process_project(project_root: Path) -> Tuple[int, int, int]:
    dataset_dir = project_root / "dataset"
    if not dataset_dir.is_dir():
        return (0, 0, 0)

    inputs_src = first_existing(dataset_dir, ["inputs_custom_enriched_inlined.csv", "inputs_custom.csv"])
    meta_src = dataset_dir / "meta_custom.csv"
    if not inputs_src or not meta_src.exists():
        return (0, 0, 0)

    inputs_rows = read_csv(inputs_src)
    meta_rows = read_csv(meta_src)
    meta_by_id = {r.get("id", ""): r for r in meta_rows if r.get("id")}

    out_inputs: List[Dict[str, str]] = []
    out_meta: List[Dict[str, str]] = []

    skipped_empty = 0
    missing_meta = 0
    made = 0

    for row in inputs_rows:
        rid = row.get("id", "")
        inlined = row.get("inlined", "") or ""
        if not inlined.strip():
            skipped_empty += 1
            continue

        meta = meta_by_id.get(rid)
        if not meta:
            missing_meta += 1
            continue

        test_name_full = row.get("test_name", "") or ""
        test_class = meta.get("test_class", "")
        if not test_class and "::" in test_name_full:
            test_class = test_name_full.split("::", 1)[0]

        method_lines = inlined.splitlines(keepends=True)
        collapsed_lines, oracle_idxs, oracle_kinds = collapse_oracle_blocks(method_lines)
        if not oracle_idxs:
            continue

        header = get_method_header(collapsed_lines)
        orig_method = get_method_name_from_header(header)

        serial = 1
        for j, idx in enumerate(oracle_idxs):
            kind, assert_name = oracle_kinds[j]
            if kind == "custom":
                kind = "standard"

            new_method = f"{orig_method}_{serial}_oe"
            new_id = f"{rid}_{serial}"

            full_test = build_single_oracle_test(collapsed_lines, idx, new_method)

            out_inputs.append(
                {
                    "id": new_id,
                    "test_prefix": full_test,
                    "test_name": f"{test_class}::{new_method}" if test_class else new_method,
                }
            )

            m = dict(meta)
            m["id"] = new_id
            m["test_name"] = new_method
            m["assert_kind"] = kind
            m["assert_name"] = assert_name
            out_meta.append(m)

            made += 1
            serial += 1

    out_inputs_path = dataset_dir / "inputs_custom_to_standard.csv"
    out_meta_path = dataset_dir / "meta_custom_to_standard.csv"

    if out_inputs:
        write_csv(out_inputs_path, out_inputs, ["id", "test_prefix", "test_name"])
    else:
        write_csv(out_inputs_path, [], ["id", "test_prefix", "test_name"])

    if out_meta:
        meta_fields = list(meta_rows[0].keys()) if meta_rows else []
        for k in ["id", "test_name", "assert_kind", "assert_name"]:
            if k not in meta_fields:
                meta_fields.append(k)
        write_csv(out_meta_path, out_meta, meta_fields)
    else:
        if meta_rows:
            write_csv(out_meta_path, [], list(meta_rows[0].keys()))
        else:
            write_csv(out_meta_path, [], ["id"])

    return (made, skipped_empty, missing_meta)


def main():
    if not PROJECTS_ROOT.is_dir():
        print("projects_decomposed not found")
        return

    total_made = 0
    total_skipped = 0
    total_missing_meta = 0
    projects_done = 0

    for p in sorted(PROJECTS_ROOT.iterdir()):
        if not p.is_dir():
            continue
        made, skipped, missing_meta = process_project(p)
        if made or skipped or missing_meta:
            projects_done += 1
            total_made += made
            total_skipped += skipped
            total_missing_meta += missing_meta
            print(f"{p.name}: made={made} skipped_empty_inlined={skipped} missing_meta={missing_meta}")

    print(f"done: projects={projects_done} made={total_made} skipped_empty_inlined={total_skipped} missing_meta={total_missing_meta}")


if __name__ == "__main__":
    main()
