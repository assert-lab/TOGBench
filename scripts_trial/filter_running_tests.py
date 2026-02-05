#!/usr/bin/env python3
import csv
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed"
SCRIPTS_DIR = ROOT / "scripts_trial"
TEST_CLASSES_CSV = SCRIPTS_DIR / "test_classes.csv"

def base_project(folder: str) -> str:
    """Extract base project name from folder (handles multi-module with __)"""
    folder = (folder or "").strip()
    return folder.split("__", 1)[0] if "__" in folder else folder

def norm_class(s: str) -> str:
    """Normalize class name: strip _OE25Dev, package prefix, and inner class markers"""
    s = (s or "").strip()
    
    if s.endswith("_OE25Dev"):
        s = s[:-len("_OE25Dev")]
    
    if "." in s:
        s = s.rsplit(".", 1)[-1]
    
    if "$" in s:
        s = s.split("$", 1)[0]
    
    return s.strip()

def clean_row(row: dict) -> dict:
    """Clean CSV row: remove None keys and strip whitespace"""
    if None in row:
        row.pop(None, None)
    out = {}
    for k, v in row.items():
        if k is None:
            continue
        kk = k.strip() if isinstance(k, str) else k
        vv = v.strip() if isinstance(v, str) else v
        out[kk] = vv
    return out

def read_csv(path: Path):
    """Read CSV and return (fieldnames, rows)"""
    if not path.exists():
        return [], []
    with path.open("r", newline="", encoding="utf-8", errors="ignore") as f:
        r = csv.DictReader(f)
        rows = [clean_row(dict(x)) for x in r]
        fields = [c.strip() for c in (r.fieldnames or [])]
        return fields, rows

def write_csv(path: Path, fieldnames, rows):
    """Write CSV (overwrites existing file)"""
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, extrasaction="ignore")
        w.writeheader()
        w.writerows(rows)

def append_csv(path: Path, fieldnames, rows):
    """Append rows to CSV (creates if missing)"""
    if not rows:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    if not path.exists():
        write_csv(path, fieldnames, rows)
        return
    with path.open("a", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, extrasaction="ignore")
        w.writerows(rows)

def pick_id_key(fields):
    """Pick the ID field from CSV headers"""
    return "id" if "id" in fields else (fields[0] if fields else "id")

def load_allowed_classes_by_proj():
    """
    Load test_classes.csv and build a dict:
    {base_project: set(normalized_class_names)}
    
    Also tracks if 'TestSuite' was seen for any module.
    """
    _, rows = read_csv(TEST_CLASSES_CSV)
    keep = {}
    
    for r in rows:
        folder = (r.get("folder") or "").strip()
        tc = (r.get("test_class") or "").strip()
        
        if not folder or not tc:
            continue
        
        bp = base_project(folder)
        
        normalized = norm_class(tc)
        
        keep.setdefault(bp, set()).add(normalized)
    
    return keep

allowed_by_proj = load_allowed_classes_by_proj()

print(f"Loaded {len(allowed_by_proj)} base projects from test_classes.csv")
print()

processed = 0
skipped = 0

for proj, allowed in sorted(allowed_by_proj.items()):
    dataset_dir = PROJECTS_DIR / proj / "dataset"
    inputs_path = dataset_dir / "inputs.csv"
    meta_path = dataset_dir / "meta.csv"
    inputs_failed_path = dataset_dir / "inputs_failed.csv"
    meta_failed_path = dataset_dir / "meta_failed.csv"

    in_fields, in_rows = read_csv(inputs_path)
    meta_fields, meta_rows = read_csv(meta_path)

    if not in_rows or not meta_rows:
        print(f"{proj}: SKIP - missing dataset/inputs.csv or dataset/meta.csv")
        skipped += 1
        continue

    in_id_key = pick_id_key(in_fields)
    meta_id_key = pick_id_key(meta_fields)

    if "TestSuite" in allowed:
        print(f"{proj}: TestSuite found - keeping ALL {len(meta_rows)} tests")
        processed += 1
        continue

    keep_ids = set()
    unmatched_classes = set()
    
    for r in meta_rows:
        rid = (r.get(meta_id_key) or "").strip()
        if not rid:
            continue
        
        meta_class = (r.get("test_class") or "").strip()
        normalized_meta = norm_class(meta_class)
        
        if normalized_meta in allowed:
            keep_ids.add(rid)
        else:
            unmatched_classes.add(normalized_meta)

    if not keep_ids:
        print(f"{proj}: SKIP - keep_ids=0 (no meta.test_class matched)")
        print(f"  Allowed classes: {sorted(allowed)[:5]}{'...' if len(allowed) > 5 else ''}")
        print(f"  Unmatched in meta: {sorted(unmatched_classes)[:5]}{'...' if len(unmatched_classes) > 5 else ''}")
        skipped += 1
        continue

    kept_meta, failed_meta = [], []
    for r in meta_rows:
        rid = (r.get(meta_id_key) or "").strip()
        if rid in keep_ids:
            kept_meta.append(r)
        else:
            failed_meta.append(r)

    kept_inputs, failed_inputs = [], []
    for r in in_rows:
        rid = (r.get(in_id_key) or "").strip()
        if rid in keep_ids:
            kept_inputs.append(r)
        else:
            failed_inputs.append(r)

    write_csv(meta_path, meta_fields, kept_meta)
    write_csv(inputs_path, in_fields, kept_inputs)
    
    append_csv(meta_failed_path, meta_fields, failed_meta)
    append_csv(inputs_failed_path, in_fields, failed_inputs)

    print(f"{proj}: kept ids={len(keep_ids)} | kept inputs={len(kept_inputs)} meta={len(kept_meta)} | moved inputs={len(failed_inputs)} meta={len(failed_meta)}")
    processed += 1

print()
print(f"DONE: processed={processed}, skipped={skipped}")