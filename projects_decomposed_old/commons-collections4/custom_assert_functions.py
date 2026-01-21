import os
import re

TEST_DIR = "src/test/java"

TARGETS = {
    "assertEqualsAfterSerialization",
    "assertMessageContains",
    "assertNoPrint",
    "assertParse",
    "assertPrint",
    "assertSameAfterSerialization",
    "assertSerialization",
}

# Match method declarations: private/protected/public <...> name(...)
decl_pattern = re.compile(
    r'\b(?:public|protected|private)\s+[\w<>\[\]]+\s+(assert\w+)\s*\('
)

def extract_method_block(path, start_lineno):
    block = []
    brace_count = 0
    recording = False

    with open(path, "r", encoding="utf-8", errors="ignore") as f:
        lines = f.readlines()

    for idx in range(start_lineno - 1, len(lines)):
        line = lines[idx]

        # Start recording once we encounter the method declaration line
        if not recording:
            if idx == start_lineno - 1:
                recording = True
                block.append(line.rstrip('\n'))

                # Count braces on the first line
                brace_count += line.count('{') - line.count('}')
        else:
            block.append(line.rstrip('\n'))
            brace_count += line.count('{') - line.count('}')
            if brace_count == 0:
                break

    return block


for root, _, files in os.walk(TEST_DIR):
    for file in files:
        if file.endswith(".java"):
            path = os.path.join(root, file)

            with open(path, "r", encoding="utf-8", errors="ignore") as f:
                for lineno, line in enumerate(f, start=1):
                    match = decl_pattern.search(line)
                    if match:
                        name = match.group(1)
                        if name in TARGETS:
                            print("=" * 80)
                            print(f"{name}  |  {path}:{lineno}")
                            print("=" * 80)

                            block = extract_method_block(path, lineno)
                            for bline in block:
                                print(bline)
                            print()
