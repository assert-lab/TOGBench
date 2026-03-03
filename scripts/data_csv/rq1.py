# python3 scripts/data_csv/rq1.py

#!/usr/bin/env python3
import argparse
import csv
import re
from pathlib import Path

JUNIT3 = {
    "assertEquals","assertFalse","assertNotNull","assertNull",
    "assertSame","assertTrue","fail"
}

JUNIT4 = {
    "assertArrayEquals","assertEquals","assertFalse","assertNotNull",
    "assertNotSame","assertNull","assertSame","assertThat",
    "assertTrue","fail"
}

JUNIT5 = {
    "assertAll","assertArrayEquals","assertDoesNotThrow","assertEquals",
    "assertFalse","assertIterableEquals","assertLinesMatch",
    "assertNotEquals","assertNotNull","assertNotSame","assertNull",
    "assertSame","assertThrows","assertTimeout",
    "assertTimeoutPreemptively","assertTrue","fail"
}

STANDARD = sorted(JUNIT3 | JUNIT4 | JUNIT5)

CALL_RE = re.compile(r"\b([A-Za-z_]\w*)\s*\(")

def pick_text_column(fieldnames):
    cands = ["test_prefix","method_definition","prefix","input","inputs","test","test_code","code","content"]
    m = {c.lower(): c for c in fieldnames}
    for c in cands:
        if c in m:
            return m[c]
    return fieldnames[1] if len(fieldnames) > 1 else fieldnames[0]

def read_rows(path: Path):
    with path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        rows = list(r)
        return r.fieldnames or [], rows

def yesno(b):
    return "Yes" if b else "No"

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--root", default="projects_decomposed")
    ap.add_argument("--dataset_dir", default="dataset")
    ap.add_argument("--inputs_name", default="inputs_final.csv")
    ap.add_argument("--out_csv", default="scripts/data_csv/assertion_counts.csv")
    args = ap.parse_args()

    root = Path(args.root)
    ds = args.dataset_dir
    inputs_name = args.inputs_name

    counts = {k: 0 for k in STANDARD}

    for proj_dir in sorted([p for p in root.iterdir() if p.is_dir()]):
        csv_path = proj_dir / ds / inputs_name
        if not csv_path.exists():
            continue

        fields, rows = read_rows(csv_path)
        if not fields:
            continue

        text_col = pick_text_column(fields)

        for r in rows:
            txt = r.get(text_col, "") or ""
            for m in CALL_RE.finditer(txt):
                name = m.group(1)
                if name in counts:
                    counts[name] += 1

    out_csv = Path(args.out_csv)
    with out_csv.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=["assertion","count","JUnit3","JUnit4","JUnit5"])
        w.writeheader()
        for a in STANDARD:
            w.writerow({
                "assertion": a,
                "count": counts[a],
                "JUnit3": yesno(a in JUNIT3),
                "JUnit4": yesno(a in JUNIT4),
                "JUnit5": yesno(a in JUNIT5),
            })

    print(str(out_csv))

if __name__ == "__main__":
    main()