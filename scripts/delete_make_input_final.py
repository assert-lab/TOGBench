# python3 scripts/delete_make_input_final.py
#!/usr/bin/env python3
import csv
from pathlib import Path

ROOT = Path(".").resolve()
PROJECTS_DIR = ROOT / "projects_decomposed"

def read_csv(path: Path):
    if not path.exists():
        return [], []
    with path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        rows = list(r)
        return (r.fieldnames or [], rows)

def write_csv(path: Path, fieldnames, rows):
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        w.writeheader()
        for row in rows:
            out = {}
            for k in fieldnames:
                out[k] = row.get(k, "")
            w.writerow(out)

def merge_unique_by_id(primary_fields, primary_rows, secondary_fields, secondary_rows):
    out_fields = []
    for k in (primary_fields or []):
        if k not in out_fields:
            out_fields.append(k)
    for k in (secondary_fields or []):
        if k not in out_fields:
            out_fields.append(k)
    if "id" not in out_fields:
        out_fields = ["id"] + out_fields

    seen = set()
    out_rows = []

    def add(rows):
        nonlocal out_rows, seen
        for r in rows:
            tid = (r.get("id") or "").strip().strip('"')
            if not tid:
                continue
            if tid in seen:
                continue
            seen.add(tid)
            rr = dict(r)
            rr["id"] = tid
            out_rows.append(rr)

    add(primary_rows)
    add(secondary_rows)

    return out_fields, out_rows

def process_project(proj_dir: Path):
    dataset_dir = proj_dir / "dataset"
    final_dir = proj_dir / "dataset_final"

    inputs_success = final_dir / "inputs_success.csv"
    meta_success = final_dir / "meta_success.csv"

    inputs_passed = dataset_dir / "inputs_passed.csv"
    meta_passed = dataset_dir / "meta_passed.csv"

    a_fields, a_rows = read_csv(inputs_success)
    b_fields, b_rows = read_csv(inputs_passed)

    if a_rows or b_rows:
        out_fields, out_rows = merge_unique_by_id(a_fields, a_rows, b_fields, b_rows)
        out_path = final_dir / "inputs_final.csv"
        write_csv(out_path, out_fields, out_rows)
        print(f"[ok] {proj_dir.name}: inputs_final={len(out_rows)}")

    ma_fields, ma_rows = read_csv(meta_success)
    mb_fields, mb_rows = read_csv(meta_passed)

    if ma_rows or mb_rows:
        out_fields, out_rows = merge_unique_by_id(ma_fields, ma_rows, mb_fields, mb_rows)
        out_path = final_dir / "meta_final.csv"
        write_csv(out_path, out_fields, out_rows)
        print(f"[ok] {proj_dir.name}: meta_final={len(out_rows)}")

def main():
    if not PROJECTS_DIR.exists():
        raise SystemExit(f"missing folder: {PROJECTS_DIR}")

    target = "spark"

    proj_dir = PROJECTS_DIR / target
    if not proj_dir.exists() or not proj_dir.is_dir():
        raise SystemExit(f"project not found: {proj_dir}")

    process_project(proj_dir)

if __name__ == "__main__":
    main()
