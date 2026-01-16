#!/usr/bin/env python3
import re
from pathlib import Path

root = Path("/sfs/weka/scratch/wxw9rr/OE25-DEV/projects_decomposed/commons-lang3-3.12.0-src")

patterns = [
    (
        re.compile(r'(\s*)assertEqualsArchNotNull\s*\(([^,]+),\s*([^)]+)\);\s*'),
        lambda indent, expected, actual: (
            f"{indent}assertNotNull({expected});\n"
            f"{indent}assertNotNull({actual});\n"
            f"{indent}assertEquals({expected}, {actual}.getArch());"
        ),
    ),
    (
        re.compile(r'(\s*)assertEqualsTypeNotNull\s*\(([^,]+),\s*([^)]+)\);\s*'),
        lambda indent, expected, actual: (
            f"{indent}assertNotNull({expected});\n"
            f"{indent}assertNotNull({actual});\n"
            f"{indent}assertEquals({expected}, {actual}.getType());"
        ),
    ),
    (
        re.compile(r'(\s*)assertNotEqualsArchNotNull\s*\(([^,]+),\s*([^)]+)\);\s*'),
        lambda indent, expected, actual: (
            f"{indent}assertNotNull({expected});\n"
            f"{indent}assertNotNull({actual});\n"
            f"{indent}assertNotEquals({expected}, {actual}.getArch());"
        ),
    ),
    (
        re.compile(r'(\s*)assertNotEqualsTypeNotNull\s*\(([^,]+),\s*([^)]+)\);\s*'),
        lambda indent, expected, actual: (
            f"{indent}assertNotNull({expected});\n"
            f"{indent}assertNotNull({actual});\n"
            f"{indent}assertNotEquals({expected}, {actual}.getType());"
        ),
    ),
]

def transform_text(text):
    changed = False
    for regex, builder in patterns:
        def repl(m):
            nonlocal changed
            changed = True
            indent = m.group(1)
            expected = m.group(2).strip()
            actual = m.group(3).strip()
            return builder(indent, expected, actual)
        text = regex.sub(repl, text)
    return text, changed

print(f"root: {root} exists={root.exists()}")

for jf in root.rglob("*.java"):
    original = jf.read_text(encoding="utf-8")
    new, changed = transform_text(original)
    if changed:
        jf.write_text(new, encoding="utf-8")
        print(f"updated {jf}")
