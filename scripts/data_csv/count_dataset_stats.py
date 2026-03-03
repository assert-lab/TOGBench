# python3 scripts/data_csv/count_dataset_stats.py
#!/usr/bin/env python3
import argparse
import csv
import re
from pathlib import Path
from statistics import median

STANDARD_ASSERTS = {
    "assertEquals","assertNotEquals","assertTrue","assertFalse","assertNull","assertNotNull",
    "assertSame","assertNotSame","assertArrayEquals","assertThat","fail",
    "assertThrows","assertDoesNotThrow","assertAll","assertTimeout","assertTimeoutPreemptively",
    "assumeTrue","assumeFalse","assumeNoException","assumeNotNull"
}

def read_csv_rows(path: Path):
    with path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        rows = list(r)
        return (r.fieldnames or []), rows

def pick_text_column(fieldnames):
    cands = ["method_definition","test_prefix","prefix","input","inputs","test","test_code","code","content"]
    m = {c.lower(): c for c in fieldnames}
    for c in cands:
        if c in m:
            return m[c]
    return fieldnames[1] if len(fieldnames) > 1 else fieldnames[0]

def loc_count(s: str):
    return sum(1 for ln in s.splitlines() if ln.strip())

def java_tokens(s: str):
    return re.findall(r"[A-Za-z_]\w*|\d+|==|!=|<=|>=|&&|\|\||[{}()[\].,;:+\-*/%<>=!&|^~?]", s)

def percentile(sorted_vals, p):
    if not sorted_vals:
        return None
    if p <= 0:
        return float(sorted_vals[0])
    if p >= 100:
        return float(sorted_vals[-1])
    k = (len(sorted_vals) - 1) * (p / 100.0)
    f = int(k)
    c = min(f + 1, len(sorted_vals) - 1)
    if f == c:
        return float(sorted_vals[f])
    return sorted_vals[f] * (c - k) + sorted_vals[c] * (k - f)

def fmt_num(x):
    if x is None:
        return "NA"
    if isinstance(x, float):
        if abs(x - round(x)) < 1e-9:
            return str(int(round(x)))
        return f"{x:.2f}"
    return str(x)

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--root", default="projects_decomposed")
    ap.add_argument("--dataset_dir", default="dataset")
    args = ap.parse_args()

    root = Path(args.root)
    ds = args.dataset_dir

    projects_seen = 0
    total_instances_rows = 0

    inputs_final_locs = []
    inputs_final_toks = []

    inputs_multiple_locs = []
    inputs_multiple_toks = []

    inputs_custom_locs = []
    inputs_custom_toks = []

    for proj_dir in sorted([p for p in root.iterdir() if p.is_dir()]):
        dataset_dir = proj_dir / ds

        inputs_final = dataset_dir / "inputs_final.csv"
        if inputs_final.exists():
            fields, rows = read_csv_rows(inputs_final)
            text_col = pick_text_column(fields)
            for r in rows:
                txt = r.get(text_col, "") or ""
                inputs_final_locs.append(loc_count(txt))
                inputs_final_toks.append(len(java_tokens(txt)))
            total_instances_rows += len(rows)
            projects_seen += 1

        inputs_multiple = dataset_dir / "inputs_multiple.csv"
        if inputs_multiple.exists():
            fields, rows = read_csv_rows(inputs_multiple)
            text_col = pick_text_column(fields)
            for r in rows:
                txt = r.get(text_col, "") or ""
                inputs_multiple_locs.append(loc_count(txt))
                inputs_multiple_toks.append(len(java_tokens(txt)))

        inputs_custom = dataset_dir / "inputs_custom.csv"
        if inputs_custom.exists():
            fields, rows = read_csv_rows(inputs_custom)
            text_col = pick_text_column(fields)
            for r in rows:
                txt = r.get(text_col, "") or ""
                inputs_custom_locs.append(loc_count(txt))
                inputs_custom_toks.append(len(java_tokens(txt)))

    final_loc = sorted(inputs_final_locs)
    final_tok = sorted(inputs_final_toks)
    mult_loc = sorted(inputs_multiple_locs)
    mult_tok = sorted(inputs_multiple_toks)
    cust_loc = sorted(inputs_custom_locs)
    cust_tok = sorted(inputs_custom_toks)

    final_loc_med = median(final_loc) if final_loc else None
    final_loc_p90 = percentile(final_loc, 90) if final_loc else None
    final_tok_med = median(final_tok) if final_tok else None

    mult_loc_med = median(mult_loc) if mult_loc else None
    mult_loc_p90 = percentile(mult_loc, 90) if mult_loc else None
    mult_tok_med = median(mult_tok) if mult_tok else None

    cust_loc_med = median(cust_loc) if cust_loc else None
    cust_loc_p90 = percentile(cust_loc, 90) if cust_loc else None
    cust_tok_med = median(cust_tok) if cust_tok else None

    print("# Projects\t" + str(projects_seen))
    print("# Instances (rows)\t" + str(total_instances_rows))

    print("Median prefix length (LOC)\t" +
          "\t".join([fmt_num(final_loc_med), fmt_num(mult_loc_med), fmt_num(cust_loc_med)]))

    print("90th percentile prefix length (LOC)\t" +
          "\t".join([fmt_num(final_loc_p90), fmt_num(mult_loc_p90), fmt_num(cust_loc_p90)]))

    print("Median prefix length (tokens)\t" +
          "\t".join([fmt_num(final_tok_med), fmt_num(mult_tok_med), fmt_num(cust_tok_med)]))

if __name__ == "__main__":
    main()