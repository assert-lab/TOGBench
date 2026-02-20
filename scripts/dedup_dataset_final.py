import csv
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed"


def read_csv(path: Path):
    with path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        rows = list(r)
        return r.fieldnames or [], rows


def write_csv(path: Path, fieldnames, rows):
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        w.writeheader()
        for row in rows:
            rr = dict(row)
            for k in fieldnames:
                rr.setdefault(k, "")
            w.writerow(rr)


for proj_dir in sorted(PROJECTS_DIR.glob("*")):
    if not proj_dir.is_dir():
        continue

    ds = proj_dir / "dataset_final"
    meta_path = ds / "meta.csv"
    inputs_path = ds / "inputs.csv"
    if not meta_path.exists() or not inputs_path.exists():
        continue

    meta_fields, meta_rows = read_csv(meta_path)
    in_fields, in_rows = read_csv(inputs_path)

    inputs_by_id = {r.get("id", ""): r for r in in_rows if r.get("id", "")}

    seen = set()
    keep_meta = []
    keep_inputs = []

    for m in meta_rows:
        tid = (m.get("id") or "").strip()
        cls = (m.get("test_class") or "").strip()
        tn = (m.get("test_name") or "").strip()
        if not tid or not cls or not tn:
            continue
        key = cls + "::" + tn
        if key in seen:
            continue
        seen.add(key)
        keep_meta.append(m)
        if tid in inputs_by_id:
            keep_inputs.append(inputs_by_id[tid])

    out_dir = proj_dir / "dataset_final"
    write_csv(out_dir / "meta.csv", meta_fields, keep_meta)
    write_csv(out_dir / "inputs.csv", in_fields, keep_inputs)

    print(f"{proj_dir.name}: kept={len(keep_meta)} removed={len(meta_rows)-len(keep_meta)}")
