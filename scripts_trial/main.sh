# ./scripts_trial/main.sh

find projects_decomposed -type f -name "*_OE25Dev.java" -delete

find projects_decomposed -type d -name "dataset" -exec rm -rf {} +

# ./scripts_trial/build_treesitter.sh

python3 scripts_trial/1_build_dataset.py

# ========== CUSTOM ASSERTION STARTS =============

# map custom assertion method
for p in projects_decomposed/*; do
  [ -d "$p" ] || continue
  echo "=== $(basename "$p") ==="
  python3 scripts_trial/custom_assert_define.py --project_root "$p"
done


# inline custom assert
python3 scripts_trial/inline_custom.py

python3 scripts_trial/custom_inline_decompose.py

# seperate still existing custom assertions - dont if handlesin dataset preprocessing/filtering scripts

./scripts_trial/concat_custom_to_standard.sh

# ========== CUSTOM ASSERTION HANDLING ENDS =============

python3 scripts_trial/dataset_post_process.py

python3 scripts_trial/2_filter_compilable_tests.py # also do same for custom assertion inlined decomposition - just rename inputs.csv and meta.csv

python3 scripts_trial/try_catch_filter.py


./scripts_trial/project_fixes/async-http-client.sh
./scripts_trial/project_fixes/bcel.sh
./scripts_trial/project_fixes/commons-collections4.sh
./scripts_trial/project_fixes/commons-configuration2.sh
./scripts_trial/project_fixes/commons-dbutils.sh
./scripts_trial/project_fixes/commons-geometry.sh
./scripts_trial/project_fixes/commons-imaging.sh
./scripts_trial/project_fixes/commons-jcs3.sh
./scripts_trial/project_fixes/commons-jexl3.sh
./scripts_trial/project_fixes/commons-lang3.sh
./scripts_trial/project_fixes/commons-rng.sh
./scripts_trial/project_fixes/commons-numbers.sh
./scripts_trial/project_fixes/commons-pool2.sh

./scripts_trial/project_fixes/joda-time.sh
./scripts_trial/project_fixes/jsoup.sh

./scripts_trial/project_fixes/JSON-java.sh
./scripts_trial/project_fixes/commons-net.sh
./scripts_trial/project_fixes/http-request.sh
./scripts_trial/project_fixes/commons-beanutils.sh
./scripts_trial/project_fixes/commons-validator.sh


# first run to keep all logs - error and running
python3 scripts_trial/3_rebuild_tests.py

# run each projects' fix.sh before running mvn test
./scripts_trial/project_fixes.sh

# cd projects_decomposed/commons-jcs3/commons-jcs-core
# cd projects_decomposed/commons-numbers/commons-numbers-fraction
# mvn clean test -Dtest="*_OE25Dev#*_oe" --color=never 2>&1 | tee mvn.log

#map muts
python3 scripts_trial/collect_methods.py
python3 scripts_trial/map_mut.py

./scripts_trial/clean_loop.sh

# python3 scripts_trial/filter_running_tests.py

# # second run with all passed tests
# find projects_decomposed -type f -name "*_OE25Dev.java" -delete
# python3 scripts_trial/3_rebuild_tests.py


# ./scripts_trial/clean_loop.sh projects_decomposed/commons-lang3
# ./scripts_trial/clean_loop.sh projects_decomposed/commons-jcs3/commons-jcs-core

python3 scripts_trial/test_count.py

# python3 scripts_trial/filter_by_logs.py

# # failes/error removal script

# # ./scripts_trial/clean_loop.sh

# # ./scripts_trial/final_mvn_run.sh

# # python3 scripts_trial/filter_dataset.py

find . -type f -name "*.bak" -delete
 