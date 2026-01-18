import csv
import os
from pathlib import Path

from tree_sitter import Language, Parser

# Path to your compiled Tree-sitter library
# (same as in extract_project.py)
JAVA = Language("build/my-languages.so", "java")
parser = Parser()
parser.set_language(JAVA)


def wrap_in_dummy_class(test_prefix: str) -> str:
    """
    Wrap the test_prefix inside a dummy Java class so Tree-sitter
    can parse it as a full compilation unit.
    We do NOT touch the internal structure of test_prefix.
    """
    return "class Dummy {\n" + test_prefix + "\n}\n"


def is_syntax_ok(test_prefix: str) -> bool:
    """
    Use Tree-sitter to check if the wrapped test code has any parse errors.
    This catches:
      - unbalanced braces
      - missing semicolons
      - malformed try/catch
      - stray @Test / } / etc.
    """
    code = wrap_in_dummy_class(test_prefix)
    tree = parser.parse(code.encode("utf-8"))
    return not tree.root_node.has_error


def process_project_dataset(dataset_dir: Path):
    inputs_path = dataset_dir / "inputs.csv"
    meta_path = dataset_dir / "meta.csv"

    if not inputs_path.exists() or not meta_path.exists():
        return

    print(f"[syntax check] {dataset_dir.parent.name}")

    # Load inputs
    with inputs_path.open("r", encoding="utf-8") as f:
        inputs_reader = csv.DictReader(f)
        inputs_rows = list(inputs_reader)

    # Load meta into dict by id
    with meta_path.open("r", encoding="utf-8") as f:
        meta_reader = csv.DictReader(f)
        meta_rows = list(meta_reader)
    meta_by_id = {row["id"]: row for row in meta_rows}

    # Prepare collectors
    kept_inputs = []
    kept_meta = []

    failed_inputs = []
    failed_meta = []

    for row in inputs_rows:
        tid = row["id"]
        prefix = row.get("test_prefix", "")

        ok = is_syntax_ok(prefix)

        meta_row = meta_by_id.get(tid)
        if meta_row is None:
            # If somehow meta missing, treat as failed row to be safe
            ok = False

        if ok:
            kept_inputs.append(row)
            if meta_row is not None:
                kept_meta.append(meta_row)
        else:
            failed_inputs.append(row)
            if meta_row is not None:
                failed_meta.append(meta_row)

    print(f"  kept = {len(kept_inputs)}, failed = {len(failed_inputs)}")

    # Write back filtered main CSVs (only syntactically valid)
    if kept_inputs:
        with inputs_path.open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=kept_inputs[0].keys())
            writer.writeheader()
            writer.writerows(kept_inputs)
    else:
        # If nothing valid, still keep a header-only file
        with inputs_path.open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=inputs_rows[0].keys())
            writer.writeheader()

    if kept_meta:
        with meta_path.open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=kept_meta[0].keys())
            writer.writeheader()
            writer.writerows(kept_meta)
    else:
        with meta_path.open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=meta_rows[0].keys())
            writer.writeheader()

    # Write failed rows into separate files
    if failed_inputs:
        with (dataset_dir / "inputs_failed.csv").open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=failed_inputs[0].keys())
            writer.writeheader()
            writer.writerows(failed_inputs)

    if failed_meta:
        with (dataset_dir / "meta_failed.csv").open("w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=failed_meta[0].keys())
            writer.writeheader()
            writer.writerows(failed_meta)


def main():
    root = Path(__file__).resolve().parent.parent
    projects_dir = root / "projects_decomposed/joda-time"

    for project_dir in projects_dir.iterdir():
        dataset_dir = project_dir / "dataset"
        if dataset_dir.exists():
            process_project_dataset(dataset_dir)


if __name__ == "__main__":
    main()