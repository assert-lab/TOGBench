# # # import csv
# # # from pathlib import Path

# # # ROOT = Path("projects_decomposed")

# # # total = 0

# # # for project in ROOT.iterdir():

# # #     dataset_dir = project / "dataset"
# # #     meta_path = project / "dataset_with_fail" / "meta_updated.csv"
# # #     inputs_path = dataset_dir / "inputs.csv"

# # #     if not meta_path.exists() or not inputs_path.exists():
# # #         continue

# # #     ids = set()

# # #     with open(meta_path, newline="") as f:
# # #         reader = csv.DictReader(f)
# # #         for row in reader:
# # #             if (row.get("oracle_type") or "").strip() == "IF_THROWN_ASSERT":
# # #                 ids.add(row["id"].strip())

# # #     if not ids:
# # #         print(project.name, "0")
# # #         continue

# # #     with open(inputs_path, newline="") as f:
# # #         reader = csv.DictReader(f)
# # #         rows = list(reader)
# # #         fields = reader.fieldnames

# # #     selected = [r for r in rows if (r.get("id") or "").strip() in ids]

# # #     out_dir = project / "dataset_check"
# # #     out_dir.mkdir(exist_ok=True)

# # #     out_file = out_dir / "inputs_IF_THROWN_ASSERT.csv"

# # #     with open(out_file, "w", newline="") as f:
# # #         writer = csv.DictWriter(f, fieldnames=fields)
# # #         writer.writeheader()
# # #         writer.writerows(selected)

# # #     print(project.name, len(selected))

# # #     total += len(selected)

# # # print("TOTAL:", total)

# # import csv
# # from pathlib import Path

# # ROOT = Path("projects_decomposed")

# # total_replaced = 0

# # for project in sorted([p for p in ROOT.iterdir() if p.is_dir()]):

# #     dataset_inputs = project / "dataset" / "inputs.csv"
# #     updated_inputs = project / "dataset_check" / "inputs_IF_THROWN_ASSERT_with_fail.csv"

# #     if not dataset_inputs.exists() or not updated_inputs.exists():
# #         continue

# #     with open(dataset_inputs, newline="") as f:
# #         reader = csv.DictReader(f)
# #         original_rows = list(reader)
# #         fields = reader.fieldnames

# #     with open(updated_inputs, newline="") as f:
# #         reader = csv.DictReader(f)
# #         updated_rows = {row["id"]: row for row in reader}

# #     replaced = 0

# #     for i, row in enumerate(original_rows):
# #         rid = (row.get("id") or "").strip()
# #         if rid in updated_rows:
# #             original_rows[i] = updated_rows[rid]
# #             replaced += 1

# #     out_file = project / "dataset_check" / "inputs.csv"

# #     with open(out_file, "w", newline="") as f:
# #         writer = csv.DictWriter(f, fieldnames=fields)
# #         writer.writeheader()
# #         writer.writerows(original_rows)

# #     print(project.name, "rows replaced:", replaced)

# #     total_replaced += replaced

# # print("TOTAL rows replaced:", total_replaced)
# import shutil
# from pathlib import Path

# ROOT = Path("projects_decomposed")

# replaced = 0

# for project in sorted([p for p in ROOT.iterdir() if p.is_dir()]):

#     src = project / "dataset_check" / "inputs.csv"
#     dst = project / "dataset" / "inputs.csv"

#     if not src.exists():
#         continue

#     if not dst.exists():
#         print(project.name, "missing dataset/inputs.csv")
#         continue

#     shutil.copy2(src, dst)

#     print(project.name, "inputs.csv replaced")
#     replaced += 1

# print("TOTAL replaced:", replaced)

import csv
from pathlib import Path

ROOT = Path("projects_decomposed")

total_replaced = 0

for project in sorted([p for p in ROOT.iterdir() if p.is_dir()]):

    base_inputs = project / "dataset" / "inputs.csv"
    multi_inputs = project / "dataset" / "inputs_multiline.csv"
    out_file = project / "dataset_check" / "inputs.csv"

    if not base_inputs.exists() or not multi_inputs.exists():
        continue

    rows_base = {}
    with open(base_inputs, newline="") as f:
        reader = csv.DictReader(f)
        fieldnames = reader.fieldnames
        for r in reader:
            rows_base[r["id"]] = r

    with open(multi_inputs, newline="") as f:
        reader = csv.DictReader(f)
        for r in reader:
            bid = r["id"]
            if bid in rows_base:
                rows_base[bid] = r
                total_replaced += 1

    out_file.parent.mkdir(parents=True, exist_ok=True)

    with open(out_file, "w", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        for r in rows_base.values():
            writer.writerow(r)

    print(project.name, "written")

print("TOTAL rows replaced:", total_replaced)