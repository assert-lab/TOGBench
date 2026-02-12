# # python3 scripts_trial/test_count_detailed.py --projects_dir projects_decomposed --out scripts_trial/test_counts_details.csv


# #!/usr/bin/env python3
# import csv
# import argparse
# from pathlib import Path

# def pick_test_name_column(fieldnames):
#     if not fieldnames:
#         return None
#     candidates = ["test_name", "test", "name", "method", "testMethod", "full_test_name"]
#     for c in candidates:
#         if c in fieldnames:
#             return c
#     return None

# def count_rows_and_oe(csv_path: Path):
#     total = 0
#     oe = 0
#     with csv_path.open(newline="", encoding="utf-8", errors="replace") as f:
#         reader = csv.DictReader(f)
#         name_col = pick_test_name_column(reader.fieldnames)

#         if name_col is None:
#             for _ in reader:
#                 total += 1
#             print(f"[WARN] No test-name column found in {csv_path} (columns={reader.fieldnames}); _oe_ count set to 0")
#             return total, 0

#         for row in reader:
#             total += 1
#             name = (row.get(name_col) or "").strip()
#             if "_oe_" in name:
#                 oe += 1

#     return total, oe

# def main():
#     ap = argparse.ArgumentParser()
#     ap.add_argument("--projects_dir", required=True,
#                     help="Root folder containing projects (e.g., projects_decomposed)")
#     ap.add_argument("--out", default="oe25dev_test_counts.csv",
#                     help="Output CSV path")
#     args = ap.parse_args()

#     projects_dir = Path(args.projects_dir)
#     out_path = Path(args.out)

#     rows = []

#     sum_total = 0
#     sum_custom = 0
#     sum_decomposed_std = 0
#     sum_meta_total = 0
#     sum_meta_oe = 0

#     for proj in sorted([p for p in projects_dir.iterdir() if p.is_dir()]):
#         dataset_dir = proj / "dataset"
#         inputs_csv = dataset_dir / "inputs.csv"
#         meta_csv = dataset_dir / "meta.csv"

#         if not inputs_csv.exists():
#             continue

#         total_decomposed, custom_to_standard = count_rows_and_oe(inputs_csv)
#         decomposed_standard = total_decomposed - custom_to_standard

#         # Optional meta sanity counts
#         meta_total = ""
#         meta_oe_count = ""
#         if meta_csv.exists():
#             try:
#                 mt, mo = count_rows_and_oe(meta_csv)
#                 meta_total = mt
#                 meta_oe_count = mo
#             except Exception as e:
#                 print(f"[WARN] Failed reading {meta_csv}: {e}")

#         sum_total += total_decomposed
#         sum_custom += custom_to_standard
#         sum_decomposed_std += decomposed_standard

#         if meta_total != "":
#             sum_meta_total += meta_total
#         if meta_oe_count != "":
#             sum_meta_oe += meta_oe_count

#         rows.append({
#             "project": proj.name,
#             "total_decomposed": total_decomposed,
#             "custom_to_standard": custom_to_standard,
#             "decomposed_standard": decomposed_standard,
#             "meta_total": meta_total,
#             "meta_oe_count": meta_oe_count,
#         })

#     # Final TOTAL row
#     rows.append({
#         "project": "TOTAL",
#         "total_decomposed": sum_total,
#         "custom_to_standard": sum_custom,
#         "decomposed_standard": sum_decomposed_std,
#         "meta_total": sum_meta_total,
#         "meta_oe_count": sum_meta_oe,
#     })

#     out_path.parent.mkdir(parents=True, exist_ok=True)
#     with out_path.open("w", newline="", encoding="utf-8") as f:
#         w = csv.DictWriter(f, fieldnames=[
#             "project",
#             "total_decomposed",
#             "custom_to_standard",
#             "decomposed_standard",
#             "meta_total",
#             "meta_oe_count",
#         ])
#         w.writeheader()
#         w.writerows(rows)

#     print(f"Wrote: {out_path}  (projects={len(rows)-1}, plus TOTAL row)")

# if __name__ == "__main__":
#     main()


#!/usr/bin/env python3
import csv
import argparse
from pathlib import Path
from collections import defaultdict

def pick_col(fieldnames, candidates):
    if not fieldnames:
        return None
    for c in candidates:
        if c in fieldnames:
            return c
    return None

def simple_class_name(fqcn: str) -> str:
    s = (fqcn or "").strip()
    if not s:
        return ""
    return s.split(".")[-1]

def build_java_index(project_root: Path):
    idx = defaultdict(list)
    for p in project_root.rglob("*.java"):
        if "dataset" in p.parts:
            continue
        idx[p.name].append(p)
    return idx

def find_module_for_file(project_root: Path, java_file: Path) -> str:
    cur = java_file.parent
    while True:
        if (cur / "pom.xml").exists():
            rel = cur.relative_to(project_root)
            return "." if str(rel) == "." else str(rel).replace("\\", "/")
        if cur == project_root:
            break
        cur = cur.parent
    return "UNKNOWN"

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--projects_dir", required=True,
                    help="Root folder containing projects (e.g., projects_decomposed)")
    ap.add_argument("--out", default="oe25dev_module_counts.csv",
                    help="Output CSV path")
    args = ap.parse_args()

    projects_dir = Path(args.projects_dir)
    out_path = Path(args.out)

    out_rows = []

    grand_total = 0
    grand_custom = 0
    grand_standard = 0

    for proj in sorted([p for p in projects_dir.iterdir() if p.is_dir()]):
        dataset_dir = proj / "dataset"
        inputs_csv = dataset_dir / "inputs.csv"
        meta_csv = dataset_dir / "meta.csv"
        if not inputs_csv.exists() or not meta_csv.exists():
            continue

        # --- Read inputs.csv: id -> test_name
        with inputs_csv.open(newline="", encoding="utf-8", errors="replace") as f:
            r = csv.DictReader(f)
            id_col = pick_col(r.fieldnames, ["id", "ID"])
            tn_col = pick_col(r.fieldnames, ["test_name", "test", "name", "method", "testMethod", "full_test_name"])
            if id_col is None or tn_col is None:
                raise RuntimeError(f"{inputs_csv}: needs columns id + test_name (found {r.fieldnames})")
            id_to_testname = {row[id_col]: (row.get(tn_col) or "").strip() for row in r}

        # --- Read meta.csv: id -> test_class
        with meta_csv.open(newline="", encoding="utf-8", errors="replace") as f:
            r = csv.DictReader(f)
            mid_col = pick_col(r.fieldnames, ["id", "ID"])
            tc_col = pick_col(r.fieldnames, ["test_class", "class", "classname", "testClass", "test_class_name"])
            if mid_col is None or tc_col is None:
                raise RuntimeError(f"{meta_csv}: needs columns id + test_class (found {r.fieldnames})")
            id_to_testclass = {row[mid_col]: (row.get(tc_col) or "").strip() for row in r}

        # Build Java index once per project
        java_idx = build_java_index(proj)

        # Cache class->module
        class_to_module = {}

        def class_module(test_class: str) -> str:
            if test_class in class_to_module:
                return class_to_module[test_class]

            scn = simple_class_name(test_class)

            candidates = [f"{scn}.java"]
            if scn and not scn.endswith("_OE25Dev"):
                candidates.append(f"{scn}_OE25Dev.java")

            found = None
            for fn in candidates:
                hits = java_idx.get(fn, [])
                if hits:
                    found = hits[0]
                    break

            mod = "UNKNOWN" if found is None else find_module_for_file(proj, found)
            class_to_module[test_class] = mod
            return mod

        # Module-wise counters
        mod_total = defaultdict(int)
        mod_custom = defaultdict(int)

        # Authoritative ids = inputs.csv
        for tid, test_name in id_to_testname.items():
            tc = id_to_testclass.get(tid, "")
            mod = class_module(tc) if tc else "UNKNOWN"
            mod_total[mod] += 1
            if "_oe_" in (test_name or ""):
                mod_custom[mod] += 1

        # Emit module rows
        modules = sorted(mod_total.keys(), key=lambda m: (-mod_total[m], m))

        proj_sum_total = 0
        proj_sum_custom = 0

        for m in modules:
            total = mod_total[m]
            custom = mod_custom.get(m, 0)
            standard = total - custom

            proj_sum_total += total
            proj_sum_custom += custom

            out_rows.append({
                "project": proj.name,
                "module": m,
                "total_decomposed": total,
                "custom_to_standard": custom,
                "decomposed_standard": standard,
            })

        # Per-project TOTAL
        proj_standard = proj_sum_total - proj_sum_custom
        out_rows.append({
            "project": proj.name,
            "module": "TOTAL",
            "total_decomposed": proj_sum_total,
            "custom_to_standard": proj_sum_custom,
            "decomposed_standard": proj_standard,
        })

        # Add to grand totals
        grand_total += proj_sum_total
        grand_custom += proj_sum_custom
        grand_standard += proj_standard

    # Final GRAND TOTAL row (very last row)
    out_rows.append({
        "project": "GRAND_TOTAL",
        "module": "GRAND_TOTAL",
        "total_decomposed": grand_total,
        "custom_to_standard": grand_custom,
        "decomposed_standard": grand_standard,
    })

    out_path.parent.mkdir(parents=True, exist_ok=True)
    with out_path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=[
            "project",
            "module",
            "total_decomposed",
            "custom_to_standard",
            "decomposed_standard",
        ])
        w.writeheader()
        w.writerows(out_rows)

    print(f"[OK] Wrote: {out_path}")

if __name__ == "__main__":
    main()
