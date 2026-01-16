import csv
import re
import sys
from pathlib import Path

# Matches fully empty control blocks:  if (...) { }
EMPTY_BLOCK_PATTERN = re.compile(
    r'(if|else|for|while|do)[^{]*\{\s*\}',
    re.MULTILINE
)

def remove_empty_blocks(text: str) -> str:
    prev = None
    while prev != text:
        prev = text
        text = re.sub(EMPTY_BLOCK_PATTERN, "", text)
    return text

def rebalance_braces(text: str) -> str:
    """
    Ensures number of '{' equals number of '}'.
    If missing '}', append them at the end.
    """
    opens = text.count("{")
    closes = text.count("}")
    diff = opens - closes

    if diff > 0:
        # Use indentation of last non-empty line
        indent = ""
        for line in reversed(text.splitlines()):
            if line.strip():
                indent = line[:len(line) - len(line.lstrip())]
                break
        for _ in range(diff):
            text += "\n" + indent + "}"

    return text

def fix_prefix(prefix: str) -> str:
    prefix = remove_empty_blocks(prefix)
    prefix = rebalance_braces(prefix)
    return prefix

def fix_csv(csv_path: Path):
    rows = []
    with csv_path.open("r", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            if "test_prefix" in row:
                row["test_prefix"] = fix_prefix(row["test_prefix"])
            rows.append(row)

    with csv_path.open("w", encoding="utf-8", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=rows[0].keys())
        writer.writeheader()
        writer.writerows(rows)

def main():
    root = Path(__file__).resolve().parent.parent
    for project in (root / "projects_decomposed/commons-lang3-3.12.0-src").iterdir():
        csv_file = project / "dataset" / "inputs.csv"
        if csv_file.exists():
            print(f"Fixing {csv_file}")
            fix_csv(csv_file)

if __name__ == "__main__":
    main()
