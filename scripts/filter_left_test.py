#!/usr/bin/env python3
import csv
import hashlib
import re
import sys
from pathlib import Path
import os

WS_RE = re.compile(r"\s+")

def norm_after_first_oe(s: str) -> str:
    t = (s or "").strip()
    if not t:
        return ""
    if "_oe" in t:
        return t.split("_oe", 1)[0] + "_oe"
    return t

def norm_body(s: str) -> str:
    t = (s or "").replace("\r\n", "\n").replace("\r", "\n").strip()
    t = WS_RE.sub("", t)
    return t

def body_hash(s: str) -> str:
    return hashlib.sha1(norm_body(s).encode("utf-8", errors="ignore")).hexdigest()

def load_existing(dataset_inputs: Path):
    names = set()
    hashes = set()
    with dataset_inputs.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        for row in r:
            tn = norm_after_first_oe(row.get("test_name", ""))
            if tn:
                names.add(tn)
            tp = row.get("test_prefix", "")
            if tp:
                hashes.add(body_hash(tp))
    return names, hashes

def filter_inputs(left_inputs: Path, out_inputs: Path, existing_names: set, existing_hashes: set):
    kept = []
    dropped = 0
    with left_inputs.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        for row in r:
            tn = norm_after_first_oe(row.get("test_name", ""))
            tp = row.get("test_prefix", "")
            h = body_hash(tp) if tp else ""
            if (tn and tn in existing_names) or (h and h in existing_hashes):
                dropped += 1
                continue
            kept.append(row)

    with out_inputs.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=["id", "test_prefix", "test_name"], lineterminator="\n")
        w.writeheader()
        for row in kept:
            w.writerow({
                "id": row.get("id", ""),
                "test_prefix": row.get("test_prefix", ""),
                "test_name": row.get("test_name", ""),
            })
    return len(kept), dropped, {row.get("id","") for row in kept}

def index_java_files(project_path: Path):
    by_class = {}
    for p in project_path.rglob("*.java"):
        rel = p.relative_to(project_path).as_posix()
        cls = p.name[:-5]
        by_class.setdefault(cls, []).append(rel)
    for k in by_class:
        by_class[k].sort(key=lambda x: (("src/test/java/" not in x), len(x), x))
    return by_class

def filter_meta(left_meta: Path, out_meta: Path, keep_ids: set):
    kept = []
    with left_meta.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        fields = r.fieldnames or []
        for row in r:
            if row.get("id", "") in keep_ids:
                kept.append(row)
    with out_meta.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fields, lineterminator="\n")
        w.writeheader()
        w.writerows(kept)
    return len(kept)

def main():
    if len(sys.argv) < 2:
        print("usage: python3 filter_left_vs_dataset.py <project>")
        return

    project = sys.argv[1]
    proj = Path("projects_decomposed") / project
    dataset_inputs = proj / "dataset" / "inputs.csv"
    left_inputs = proj / "dataset_left" / "inputs_left.csv"
    left_meta = proj / "dataset_left" / "meta_left.csv"

    if not dataset_inputs.exists():
        print("missing", dataset_inputs.as_posix())
        return
    if not left_inputs.exists():
        print("missing", left_inputs.as_posix())
        return

    existing_names, existing_hashes = load_existing(dataset_inputs)

    out_inputs = proj / "dataset_left" / "inputs_left_filtered.csv"
    kept_n, dropped_n, keep_ids = filter_inputs(left_inputs, out_inputs, existing_names, existing_hashes)
    print("kept =", kept_n)
    print("dropped =", dropped_n)
    print("wrote", out_inputs.as_posix())

    if left_meta.exists():
        out_meta = proj / "dataset_left" / "meta_left_filtered.csv"
        meta_n = filter_meta(left_meta, out_meta, keep_ids)
        print("meta_kept =", meta_n)
        print("wrote", out_meta.as_posix())

# fix path

    if len(sys.argv) < 2:
        print("usage: python3 fill_test_file_path.py <project> [projects_root]")
        return

    project = sys.argv[1]
    projects_root = Path(sys.argv[2]) if len(sys.argv) >= 3 else Path("projects_decomposed")
    project_path = projects_root / project

    meta_in = project_path / "dataset_left" / "meta_left.csv"
    if not meta_in.exists():
        print("missing", meta_in.as_posix())
        return

    meta_out = project_path / "dataset_left" / "meta_left_filtered.csv"

    idx = index_java_files(project_path)

    rows = []
    filled = 0
    missing = 0

    with meta_in.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        fields = r.fieldnames or []
        if "test_file_path" not in fields or "test_class" not in fields:
            print("meta_left.csv missing test_file_path or test_class")
            return
        for row in r:
            cur = (row.get("test_file_path") or "").strip()
            if cur:
                rows.append(row)
                continue
            cls = (row.get("test_class") or "").strip()
            cand = idx.get(cls, [])
            if cand:
                row["test_file_path"] = cand[0]
                filled += 1
            else:
                missing += 1
            rows.append(row)

    with meta_out.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fields, lineterminator="\n")
        w.writeheader()
        w.writerows(rows)

    print("wrote", meta_out.as_posix())
    print("filled =", filled)
    print("still_missing =", missing)

if __name__ == "__main__":
    main()


