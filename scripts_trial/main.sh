find projects_decomposed -type f -name "*_OE25Dev.java" -delete

find projects_decomposed -type d -name "dataset" -exec rm -rf {} +

# ./scripts_trial/build_treesitter.sh

python3 scripts_trial/1_build_dataset.py

# ======== CUSTOM ASSERTION STARTS =============

# map custom assertion method
for p in projects_decomposed/*; do
  [ -d "$p" ] || continue
  echo "=== $(basename "$p") ==="
  python3 scripts_trial/custom_assert_define.py --project_root "$p"
done


# inline custom assert
python3 scripts_trial/inline_custom.py

# seperate still existing custom assertions - dont if handlesin dataset preprocessing/filtering scripts

./scripts_trial/concat_custom_to_standard.sh

# ======== CUSTOM ASSERTION HANDLING ENDS =============

python3 scripts_trial/dataset_post_process.py

python3 scripts_trial/2_filter_compilable_tests.py # also do same for custom assertion inlined decomposition - just rename inputs.csv and meta.csv

python3 scripts_trial/try_catch_filter.py



./scripts_trial/project_fixes/joda-time.sh
./scripts_trial/project_fixes/commons-collections4.sh
./scripts_trial/project_fixes/JSON-java.sh
./scripts_trial/project_fixes/commons-net.sh
./scripts_trial/project_fixes/commons-configuration2.sh
./scripts_trial/project_fixes/http-request.sh
./scripts_trial/project_fixes/commons-lang3.sh
./scripts_trial/project_fixes/commons-jexl3.sh
./scripts_trial/project_fixes/commons-beanutils.sh
./scripts_trial/project_fixes/commons-dbutils.sh

# first run to keep all logs - error and running
python3 scripts_trial/3_rebuild_tests.py

# run each projects' fix.sh before running mvn test
./scripts_trial/project_fixes.sh

# ./scripts_trial/clean_loop.sh > compile_error_test_files.log

# python3 scripts_trial/filter_running_tests.py

# # second run with all passed tests
# find projects_decomposed -type f -name "*_OE25Dev.java" -delete
# python3 scripts_trial/3_rebuild_tests.py


# ./scripts_trial/clean_loop.sh

# python3 scripts_trial/test_count.py

# python3 scripts_trial/filter_by_logs.py

# # failes/error removal script

# # ./scripts_trial/clean_loop.sh

# # ./scripts_trial/final_mvn_run.sh

# # python3 scripts_trial/filter_dataset.py
