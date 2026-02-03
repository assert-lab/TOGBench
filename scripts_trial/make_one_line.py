import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
BASE = ROOT / "projects_decomposed"

ASSERT_NAMES = [
    "assertArrayEquals",
    "assertEquals",
    "assertFalse",
    "assertNotEquals",
    "assertNotNull",
    "assertNotSame",
    "assertNull",
    "assertSame",
    "assertThat",
    "assertTrue",
    "assertIterableEquals",
    "assertLinesMatch",
    "assertTimeout",
    "assertTimeoutPreemptively",
    "assertDoesNotThrow",
    "assertAll",
    "fail",
]

START_RE = re.compile(
    r'^\s*(?:Assert\.|Assertions\.|Assume\.)?(?:' +
    "|".join(map(re.escape, ASSERT_NAMES)) +
    r')\s*\('
)

def squeeze(stmt: str) -> str:
    stmt = re.sub(r'\s+', ' ', stmt)
    stmt = re.sub(r'\s*([,;()])\s*', r'\1', stmt)
    return stmt.strip()

def is_comment_line(line: str) -> bool:
    s = line.lstrip()
    return s.startswith("//")

def should_start(line: str) -> bool:
    s = line.lstrip()
    if s.startswith("//"):
        return False
    if "assertThrows" in s:
        return False
    return START_RE.match(line) is not None

def collapse_assert(lines, i):
    indent = re.match(r'^\s*', lines[i]).group(0)
    buf = []
    paren = 0
    seen_open = False

    j = i
    while j < len(lines):
        line = lines[j].rstrip("\n")

        # drop pure comment lines inside assertion
        if is_comment_line(line):
            j += 1
            continue
            
        if "{" in line or "}" in line:
            return None

        for c in line:
            if c == "(":
                paren += 1
                seen_open = True
            elif c == ")":
                paren -= 1

        buf.append(line.strip())

        if seen_open and paren == 0 and line.rstrip().endswith(";"):
            stmt = " ".join(buf)
            return i, j, indent + squeeze(stmt) + "\n"

        j += 1

    return None

def process_file(p: Path) -> bool:
    original = p.read_text(encoding="utf-8", errors="ignore")
    lines = original.splitlines(True)

    out = []
    changed = False
    i = 0

    while i < len(lines):
        line = lines[i]
        if should_start(line) and not line.rstrip().endswith(";"):
            res = collapse_assert(lines, i)
            if res:
                s, e, repl = res
                out.append(repl)
                i = e + 1
                changed = True
                continue
        out.append(line)
        i += 1

    if changed:
        bak = p.with_suffix(p.suffix + ".bak")
        if not bak.exists():
            bak.write_text(original, encoding="utf-8", errors="ignore")
        p.write_text("".join(out), encoding="utf-8", errors="ignore")

    return changed

def main():
    files = list(BASE.rglob("*.java"))
    touched = 0
    for f in files:
        if process_file(f):
            touched += 1
    print(f"java_files_scanned={len(files)} java_files_modified={touched}")

if __name__ == "__main__":
    main()
