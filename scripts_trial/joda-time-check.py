# import csv
# import re
# from pathlib import Path

# PROJECT_DIR = Path("projects_decomposed/joda-time/dataset")
# IN_PATH = PROJECT_DIR / "inputs_filtered.csv"

# OUT_NO_ASSERT = PROJECT_DIR / "rows_no_assertion_in_prefix.csv"
# OUT_ONLY_ASSERT = PROJECT_DIR / "rows_only_assertions_in_prefix.csv"

# ASSERT_RE = re.compile(
#     r"^\s*(assert[A-Za-z0-9_]*\s*\(|Assert\.\s*assert[A-Za-z0-9_]*\s*\(|fail\s*\(|Assert\.\s*fail\s*\()"
# )

# LINE_COMMENT_RE = re.compile(r"^\s*//")
# BLOCK_START_RE = re.compile(r"^\s*/\*")
# BLOCK_END_RE = re.compile(r".*\*/\s*$")

# def is_assert_line(line: str) -> bool:
#     s = line.strip()
#     if not s:
#         return False
#     return bool(ASSERT_RE.match(s))

# def strip_method_wrapper(prefix: str):
#     text = str(prefix)
#     first = text.find("{")
#     last = text.rfind("}")
#     if first == -1 or last == -1 or last <= first:
#         return text.splitlines()
#     body = text[first + 1:last]
#     return body.splitlines()

# def remove_comment_lines(lines):
#     out = []
#     in_block = False
#     for ln in lines:
#         s = ln.rstrip()

#         if in_block:
#             if BLOCK_END_RE.match(s):
#                 in_block = False
#             continue

#         if BLOCK_START_RE.match(s):
#             if not BLOCK_END_RE.match(s):
#                 in_block = True
#             continue

#         if LINE_COMMENT_RE.match(s):
#             continue

#         out.append(s)
#     return out

# def body_lines(prefix: str):
#     lines = strip_method_wrapper(prefix)
#     lines = remove_comment_lines(lines)
#     return [ln for ln in lines if ln.strip()]

# def prefix_has_any_assert(prefix: str) -> bool:
#     for ln in body_lines(prefix):
#         if is_assert_line(ln):
#             return True
#     return False

# def prefix_only_assertions(prefix: str) -> bool:
#     lines = body_lines(prefix)
#     saw_assert = False
#     for ln in lines:
#         if is_assert_line(ln):
#             saw_assert = True
#         else:
#             return False
#     return saw_assert

# def main():
#     if not IN_PATH.exists():
#         raise FileNotFoundError(f"inputs.csv not found: {IN_PATH}")

#     with IN_PATH.open("r", encoding="utf-8", newline="") as f:
#         reader = csv.DictReader(f)
#         rows = list(reader)
#         fieldnames = reader.fieldnames or []

#     if not fieldnames:
#         raise RuntimeError("inputs.csv has no header")
#     if "test_prefix" not in fieldnames:
#         raise RuntimeError("inputs.csv does not have a 'test_prefix' column")

#     no_assert_rows = []
#     only_assert_rows = []

#     for row in rows:
#         prefix = row.get("test_prefix", "") or ""
#         if not str(prefix).strip():
#             continue

#         if not prefix_has_any_assert(prefix):
#             no_assert_rows.append(row)

#         if prefix_only_assertions(prefix):
#             only_assert_rows.append(row)

#     with OUT_NO_ASSERT.open("w", encoding="utf-8", newline="") as f:
#         w = csv.DictWriter(f, fieldnames=fieldnames)
#         w.writeheader()
#         w.writerows(no_assert_rows)

#     with OUT_ONLY_ASSERT.open("w", encoding="utf-8", newline="") as f:
#         w = csv.DictWriter(f, fieldnames=fieldnames)
#         w.writeheader()
#         w.writerows(only_assert_rows)

#     print(f"Total rows: {len(rows)}")
#     print(f"No assertion in prefix body: {len(no_assert_rows)} -> {OUT_NO_ASSERT}")
#     print(f"Only assertions in prefix body: {len(only_assert_rows)} -> {OUT_ONLY_ASSERT}")

# if __name__ == "__main__":
#     main()


# ======= SEPERATE CSV ======


# import csv
# from pathlib import Path

# DATASET_DIR = Path("projects_decomposed/joda-time/dataset")

# INPUTS_PATH = DATASET_DIR / "inputs.csv"
# META_PATH = DATASET_DIR / "meta.csv"

# ONLY_ASSERT_PATH = DATASET_DIR / "rows_only_assertions_in_prefix.csv"

# OUT_INPUTS = DATASET_DIR / "inputs_filtered.csv"
# OUT_META = DATASET_DIR / "meta_filtered.csv"

# def load_ids(path: Path):
#     ids = set()
#     with path.open("r", encoding="utf-8", newline="") as f:
#         r = csv.DictReader(f)
#         if "id" not in (r.fieldnames or []):
#             raise RuntimeError(f"{path} missing 'id' column")
#         for row in r:
#             ids.add((row.get("id") or "").strip())
#     ids.discard("")
#     return ids

# def filter_csv(in_path: Path, out_path: Path, remove_ids: set):
#     with in_path.open("r", encoding="utf-8", newline="") as f:
#         r = csv.DictReader(f)
#         fieldnames = r.fieldnames or []
#         if "id" not in fieldnames:
#             raise RuntimeError(f"{in_path} missing 'id' column")

#         kept = 0
#         removed = 0

#         with out_path.open("w", encoding="utf-8", newline="") as wf:
#             w = csv.DictWriter(wf, fieldnames=fieldnames)
#             w.writeheader()

#             for row in r:
#                 rid = (row.get("id") or "").strip()
#                 if rid in remove_ids:
#                     removed += 1
#                     continue
#                 w.writerow(row)
#                 kept += 1

#     return kept, removed

# def main():
#     if not ONLY_ASSERT_PATH.exists():
#         raise FileNotFoundError(f"Missing: {ONLY_ASSERT_PATH}")

#     remove_ids = load_ids(ONLY_ASSERT_PATH)

#     print(f"IDs to remove: {len(remove_ids)}")

#     kept_i, removed_i = filter_csv(INPUTS_PATH, OUT_INPUTS, remove_ids)
#     kept_m, removed_m = filter_csv(META_PATH, OUT_META, remove_ids)

#     print(f"inputs.csv  kept={kept_i} removed={removed_i} -> {OUT_INPUTS}")
#     print(f"meta.csv    kept={kept_m} removed={removed_m} -> {OUT_META}")

# if __name__ == "__main__":
#     main()


# ===== clean comments =====

import csv
import re
from pathlib import Path

DATASET_DIR = Path("projects_decomposed/joda-time/dataset")

IN_PATH = DATASET_DIR / "inputs_filtered.csv"
OUT_PATH = DATASET_DIR / "inputs_filtered_no_comments.csv"

LINE_COMMENT_RE = re.compile(r"^\s*//")
BLOCK_START_RE = re.compile(r"^\s*/\*")
BLOCK_END_RE = re.compile(r".*\*/\s*$")

COLUMNS_TO_CLEAN = ["test_prefix", "test_case", "focal_method", "docstring"]

def remove_comment_lines(text: str) -> str:
    if text is None:
        return ""

    lines = str(text).splitlines()
    out = []
    in_block = False

    for ln in lines:
        s = ln.rstrip()

        if in_block:
            if BLOCK_END_RE.match(s):
                in_block = False
            continue

        if BLOCK_START_RE.match(s):
            if not BLOCK_END_RE.match(s):
                in_block = True
            continue

        if LINE_COMMENT_RE.match(s):
            continue

        out.append(s)

    return "\n".join(out)

def main():
    if not IN_PATH.exists():
        raise FileNotFoundError(f"Missing: {IN_PATH}")

    with IN_PATH.open("r", encoding="utf-8", newline="") as f:
        r = csv.DictReader(f)
        fieldnames = r.fieldnames or []
        rows = list(r)

    if not fieldnames:
        raise RuntimeError("inputs_filtered.csv has no header")

    clean_cols = [c for c in COLUMNS_TO_CLEAN if c in fieldnames]
    if not clean_cols:
        raise RuntimeError("No known columns found to clean in this CSV")

    for row in rows:
        for col in clean_cols:
            row[col] = remove_comment_lines(row.get(col, "") or "")

    with OUT_PATH.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames)
        w.writeheader()
        w.writerows(rows)

    print(f"Saved: {OUT_PATH}")
    print(f"Rows: {len(rows)}")
    print(f"Cleaned columns: {', '.join(clean_cols)}")

if __name__ == "__main__":
    main()
