# ./scripts/main.sh

find projects_decomposed -type f -name "*_OE25Dev*.java" -delete

# find projects_decomposed -type d -name "dataset" -exec rm -rf {} +

# ./scripts/build_treesitter.sh

# python3 scripts/1_build_dataset.py

# ========== CUSTOM ASSERTION STARTS =============

# map custom assertion method
# for p in projects_decomposed/*; do
#   [ -d "$p" ] || continue
#   echo "=== $(basename "$p") ==="
#   python3 scripts/custom_assert_define.py --project_root "$p"
# done


# inline custom assert
# python3 scripts/inline_custom.py

# python3 scripts/custom_inline_decompose.py

# seperate still existing custom assertions - dont if handles in dataset preprocessing/filtering scripts

# ./scripts/concat_custom_to_standard.sh

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


# python3 scripts/dataset_post_process.py

# python3 scripts/2_filter_compilable_tests.py

# python3 scripts/try_catch_filter.py


# ==== try-catch conversion start ====

# set -euo pipefail

# for p in projects_decomposed/*; do
#   [ -d "$p" ] || continue
#   proj="$(basename "$p")"

#   if [ ! -f "$p/dataset/inputs.csv" ]; then
#     continue
#   fi

#   echo "=== $proj ==="
#   python3 scripts/transform_try_catch.py --project "$proj"
# done

# ==== try-catch conversion ends ====

# merge all successful tests
# for p in projects_decomposed/*; do
#   f1="$p/dataset_final/inputs_final.csv"
#   f2="$p/dataset_left/inputs_passed.csv"
#   out="$p/dataset_final/inputs.csv"

#   if [ -f "$f1" ] && [ -f "$f2" ]; then
#     awk 'FNR==1 && NR!=1 {next} {print}' "$f1" "$f2" > "$out"
#     echo "[inputs merged] $(basename "$p")"
#   fi

#   m1="$p/dataset_final/meta_final.csv"
#   m2="$p/dataset_left/meta_passed.csv"
#   mout="$p/dataset_final/meta.csv"

#   if [ -f "$m1" ] && [ -f "$m2" ]; then
#     awk 'FNR==1 && NR!=1 {next} {print}' "$m1" "$m2" > "$mout"
#     echo "[meta merged] $(basename "$p")"
#   fi
# done


# ls -1 projects_decomposed | xargs -n 1 -P 4 -I{} python3 scripts/test_failed_tests.py --project "{}"

# ./scripts/project_fixes/async-http-client.sh
# ./scripts/project_fixes/bcel.sh
./scripts/project_fixes/commons-collections4.sh
# ./scripts/project_fixes/commons-configuration2.sh
# ./scripts/project_fixes/commons-dbutils.sh
./scripts/project_fixes/commons-geometry.sh
# ./scripts/project_fixes/commons-imaging.sh
./scripts/project_fixes/commons-jcs3.sh
./scripts/project_fixes/commons-jexl3.sh
./scripts/project_fixes/commons-lang3.sh
./scripts/project_fixes/commons-rng.sh
# ./scripts/project_fixes/commons-numbers.sh
./scripts/project_fixes/commons-pool2.sh
./scripts/project_fixes/joda-time.sh
./scripts/project_fixes/jsoup.sh
./scripts/project_fixes/JSON-java.sh
./scripts/project_fixes/commons-net.sh
# ./scripts/project_fixes/http-request.sh
./scripts/project_fixes/commons-beanutils.sh
# ./scripts/project_fixes/commons-validator.sh
./scripts/project_fixes/commons-vfs.sh
./scripts/project_fixes/commons-weaver.sh
./scripts/project_fixes/scribejava.sh
./scripts/project_fixes/spark.sh
# ./scripts/project_fixes/springside4.sh

python3 scripts/dedup_dataset_final.py
# first run to keep all logs - error and running
# python3 scripts/3_rebuild_tests.py
python3 scripts/3_rebuild_decomposed.py

# run each projects' fix.sh before running mvn test
# ./scripts/project_fixes.sh

./scripts/clean_loop.sh

# cd projects_decomposed/commons-lang3
# mvn clean test -Dtest="*_OE25Dev#*_oe" --color=never 2>&1 | tee mvn.log

# ======== map muts ========
python3 scripts/collect_methods.py
python3 scripts/map_mut.py



# python3 scripts/filter_running_tests.py

# # second run with all passed tests
# find projects_decomposed -type f -name "*_OE25Dev.java" -delete
# python3 scripts/3_rebuild_decomposed.py


# ./scripts/clean_loop.sh projects_decomposed/commons-lang3
# ./scripts/clean_loop.sh projects_decomposed/commons-jcs3/commons-jcs-core
python3 scripts/count_custom.py
python3 scripts/test_count.py

# python3 scripts/filter_by_logs.py

# # ./scripts/final_mvn_run.sh


# find . -type f -name "*.bak" -delete
 