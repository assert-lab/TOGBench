# ./scripts/main.sh

find projects_decomposed -type f -name "*_OE25Dev*.java" -delete

find projects_decomposed -type d -name "dataset" -exec rm -rf {} +

# ./scripts/build_treesitter.sh

python3 scripts/1_build_dataset.py

# ========== CUSTOM ASSERTION STARTS =============

# map custom assertion method
for p in projects_decomposed/*; do
  [ -d "$p" ] || continue
  echo "=== $(basename "$p") ==="
  python3 scripts/custom_assert_define.py --project_root "$p"
done


# inline custom assert
python3 scripts/inline_custom.py

python3 scripts/custom_inline_decompose.py

# seperate still existing custom assertions - dont if handles in dataset preprocessing/filtering scripts

./scripts/concat_custom_to_standard.sh

# ========== CUSTOM ASSERTION HANDLING ENDS =============

# python3 - << 'PY' > all_decomposed.txt
# from pathlib import Path

# root = Path(".")
# total = 0
# files = 0

# for p in sorted(root.glob("projects_decomposed/*/dataset/meta.csv")):
#     n = -1
#     with p.open("rb") as f:
#         for n, _ in enumerate(f):
#             pass
#     rows = max(n, 0)
#     print(f"{p.parent.parent.name},{rows}")
#     total += rows
#     files += 1

# print("----")
# print(f"projects_with_csv={files}")
# print(f"total_rows={total}")
# PY


python3 scripts/dataset_post_process.py

# python3 scripts/2_filter_compilable_tests.py

# python3 scripts/try_catch_filter.py


# ==== try-catch conversion start ====

set -euo pipefail

for p in projects_decomposed/*; do
  [ -d "$p" ] || continue
  proj="$(basename "$p")"

  if [ ! -f "$p/dataset/inputs.csv" ]; then
    continue
  fi

  echo "=== $proj ==="
  python3 scripts/transform_try_catch.py --project "$proj"
done

# ==== try-catch conversion ends ====


# ls -1 projects_decomposed | xargs -n 1 -P 4 -I{} python3 scripts/test_failed_tests.py --project "{}"

# ./scripts/project_fixes/async-http-client.sh
# ./scripts/project_fixes/bcel.sh
./scripts/project_fixes/commons-collections4.sh
# ./scripts/project_fixes/commons-configuration2.sh
# ./scripts/project_fixes/commons-dbutils.sh
# ./scripts/project_fixes/commons-geometry.sh
# ./scripts/project_fixes/commons-imaging.sh
# ./scripts/project_fixes/commons-jcs3.sh
./scripts/project_fixes/commons-jexl3.sh
# ./scripts/project_fixes/commons-lang3.sh
# ./scripts/project_fixes/commons-rng.sh
# ./scripts/project_fixes/commons-numbers.sh
./scripts/project_fixes/commons-pool2.sh
./scripts/project_fixes/joda-time.sh
# ./scripts/project_fixes/jsoup.sh
./scripts/project_fixes/JSON-java.sh
./scripts/project_fixes/commons-net.sh
# ./scripts/project_fixes/http-request.sh
./scripts/project_fixes/commons-beanutils.sh
# ./scripts/project_fixes/commons-validator.sh
./scripts/project_fixes/spark.sh
# ./scripts/project_fixes/springside4.sh


# first run to keep all logs - error and running
# python3 scripts/3_rebuild_tests.py
# python3 scripts/3_rebuild_decomposed.py

# run each projects' fix.sh before running mvn test
# ./scripts/project_fixes.sh
# ./scripts/clean_loop.sh

# cd projects_decomposed/commons-lang3
# mvn clean test -Dtest="*_OE25Dev#*_oe" --color=never 2>&1 | tee mvn.log

# ======== map muts ========
# python3 scripts/collect_methods.py
# python3 scripts/map_mut.py



# python3 scripts/filter_running_tests.py

# # second run with all passed tests
# find projects_decomposed -type f -name "*_OE25Dev.java" -delete
# python3 scripts/3_rebuild_decomposed.py


# ./scripts/clean_loop.sh projects_decomposed/commons-lang3
# ./scripts/clean_loop.sh projects_decomposed/commons-jcs3/commons-jcs-core

# python3 scripts/test_count.py

# python3 scripts/filter_by_logs.py

# # ./scripts/final_mvn_run.sh


# find . -type f -name "*.bak" -delete
 
python3 - <<'PY'
#!/usr/bin/env python3
import csv
from pathlib import Path

ROOT = Path("projects_decomposed")

def read_bad_ids(ids_path: Path) -> set[str]:
    bad = set()
    with ids_path.open("r", encoding="utf-8", newline="") as f:
        r = csv.reader(f)
        header = next(r, None)
        for row in r:
            if not row:
                continue
            bad.add(row[0].strip().strip('"'))
    return bad

def filter_csv(in_path: Path, out_path: Path, bad: set[str]) -> tuple[int, int, int]:
    total = 0
    kept = 0
    removed = 0

    with in_path.open("r", encoding="utf-8", newline="") as fin, \
         out_path.open("w", encoding="utf-8", newline="") as fout:

        r = csv.reader(fin)
        w = csv.writer(fout)

        header = next(r, None)
        if header is None:
            return (0, 0, 0)
        w.writerow(header)

        for row in r:
            if not row:
                continue
            total += 1
            rid = row[0].strip().strip('"')
            if rid in bad:
                removed += 1
                continue
            w.writerow(row)
            kept += 1

    return (total, kept, removed)

def main():
    for p in sorted(ROOT.glob("*")):
        if not p.is_dir():
            continue

        ids = p / "dataset_checked_ids" / "ids_checked.csv"
        inputs = p / "dataset" / "inputs.csv"
        meta = p / "dataset" / "meta.csv"

        if not (ids.is_file() and inputs.is_file() and meta.is_file()):
            continue

        bad = read_bad_ids(ids)

        inputs_left = p / "dataset" / "inputs_left.csv"
        meta_left = p / "dataset" / "meta_left.csv"

        in_total, in_kept, in_removed = filter_csv(inputs, inputs_left, bad)
        m_total, m_kept, m_removed = filter_csv(meta, meta_left, bad)

        print(f"Project: {p.name}")
        print(f"  inputs: ids={len(bad)} total={in_total} kept={in_kept} removed={in_removed}")
        print(f"  meta:   ids={len(bad)} total={m_total} kept={m_kept} removed={m_removed}")

if __name__ == "__main__":
    main()

PY