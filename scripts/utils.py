
#!/usr/bin/env python3
# scripts/utils.py
import re
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
