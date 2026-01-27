#!/usr/bin/env python3
import os
import re
from collections import defaultdict

ROOT = "src"

ASSERT_RE = re.compile(r'\b(assert[A-Za-z0-9_]*)\s*\(')

assert_info = defaultdict(lambda: {"count": 0, "examples": []})

for dirpath, dirnames, filenames in os.walk(ROOT):
    for filename in filenames:
        if not filename.endswith(".java"):
            continue
        fpath = os.path.join(dirpath, filename)
        try:
            with open(fpath, encoding="utf-8", errors="ignore") as f:
                for line in f:
                    if "assert" not in line:
                        continue
                    for m in ASSERT_RE.finditer(line):
                        name = m.group(1)
                        info = assert_info[name]
                        info["count"] += 1
                        # if len(info["examples"]) < 3:
                        #     info["examples"].append(line.rstrip("\n"))
        except OSError:
            continue

for name in sorted(assert_info.keys()):
    info = assert_info[name]
    print(f"{name} {info['count']}")
    for ex in info["examples"]:
        print("   " + ex)
    print()

print(len(assert_info))
