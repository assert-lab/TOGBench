# python3 scripts/data_csv/count_sloc.py
#!/usr/bin/env python3
import csv
from pathlib import Path

ROOT = Path("projects_decomposed")
OUT = Path("scripts/data_csv/single_sloc_summary.csv")

def sloc(text: str) -> int:
    return sum(1 for l in (text or "").splitlines() if l.strip())

def analyze_project(project_dir: Path):
    csv_path = project_dir / "dataset" / "inputs_final.csv"
    if not csv_path.exists():
        return None

    vals = []
    with csv_path.open("r", encoding="utf-8", newline="") as f:
        r = csv.DictReader(f)
        for row in r:
            vals.append(sloc(row.get("test_prefix", "") or ""))

    if not vals:
        return None

    return {
        "project": project_dir.name,
        "count": len(vals),
        "min_sloc": min(vals),
        "max_sloc": max(vals),
        "avg_sloc": round(sum(vals) / len(vals), 2),
    }

def main():
    rows = []
    all_vals = []
    total_count = 0

    for p in sorted(ROOT.iterdir()):
        if not p.is_dir():
            continue
        stats = analyze_project(p)
        if not stats:
            continue
        rows.append(stats)
        total_count += stats["count"]

        csv_path = p / "dataset_multiple" / "inputs_multiple.csv"
        with csv_path.open("r", encoding="utf-8", newline="") as f:
            r = csv.DictReader(f)
            for row in r:
                all_vals.append(sloc(row.get("test_prefix", "") or ""))

    if all_vals:
        rows.append({
            "project": "TOTAL",
            "count": total_count,
            "min_sloc": min(all_vals),
            "max_sloc": max(all_vals),
            "avg_sloc": round(sum(all_vals) / len(all_vals), 2),
        })

    with OUT.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=["project", "count", "min_sloc", "max_sloc", "avg_sloc"])
        w.writeheader()
        for r in rows:
            w.writerow(r)

    print(str(OUT))

if __name__ == "__main__":
    main()