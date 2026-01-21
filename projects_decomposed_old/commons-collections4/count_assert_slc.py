#!/usr/bin/env python3
import re
from pathlib import Path

# ---- configure path to tests relative to project root ----
ROOT = Path("src/test/java")  # assume you run from project root

# standard JUnit 3.x assertions
ASSERT_RE = re.compile(
    r'\b(assertTrue|assertFalse|assertEquals|assertSame|'
    r'assertNotSame|assertNull|assertNotNull|fail)\s*\('
)

def count_asserts_in_file(path: Path):
    try:
        text = path.read_text(encoding="utf-8", errors="ignore")
    except Exception:
        return 0
    return len(ASSERT_RE.findall(text))

def count_exception_oracles_in_file(path: Path):
    try:
        text = path.read_text(encoding="utf-8", errors="ignore")
    except Exception:
        return 0

    count = 0
    i = 0
    while True:
        idx_try = text.find("try", i)
        if idx_try == -1:
            break

        # make sure it's a standalone 'try' (word boundary)
        if not re.match(r'\btry\b', text[idx_try:idx_try+4]):
            i = idx_try + 3
            continue

        idx_catch = text.find("catch", idx_try)
        if idx_catch == -1:
            break

        segment = text[idx_try:idx_catch]
        if "fail(" in segment:
            count += 1

        i = idx_catch + 5

    return count

def main():
    if not ROOT.exists():
        print(f"ROOT path does not exist: {ROOT.resolve()}")
        return

    total_asserts = 0
    total_exc_oracles = 0
    total_files = 0

    for java_file in ROOT.rglob("*.java"):
        total_files += 1
        a = count_asserts_in_file(java_file)
        e = count_exception_oracles_in_file(java_file)

        if a > 0 or e > 0:
            print(f"{java_file}: asserts={a}, exception_oracles={e}")

        total_asserts += a
        total_exc_oracles += e

    print(f"\nJava files scanned: {total_files}")
    print(f"TOTAL standard JUnit assertions: {total_asserts}")
    print(f"TOTAL exception-oracle blocks (try..fail..catch): {total_exc_oracles}")

if __name__ == "__main__":
    main()
