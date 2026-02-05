find projects_decomposed -type f -name "*_OE25Dev.java" -delete

find projects_decomposed -type d -name "dataset" -exec rm -rf {} +

# ./scripts_trial/build_treesitter.sh

python3 scripts_trial/1_build_dataset.py

# ======== CUSTOM ASSERTION STARTS =============

# # map custom assertion method
# for p in projects_decomposed/*; do
#   [ -d "$p" ] || continue
#   echo "=== $(basename "$p") ==="
#   python3 scripts_trial/custom_assert_define.py --project_root "$p"
# done


# # inline custom assert
# python3 scripts_trial/inline_custom.py

# # seperate still existing custom assertions - dont if handlesin dataset preprocessing/filtering scripts

# ./scripts_trial/concat_custom_to_standard.sh

# ======== CUSTOM ASSERTION HANDLING ENDS =============

python3 scripts_trial/dataset_post_process.py

python3 scripts_trial/2_filter_compilable_tests.py. # also do same for custom assertion inlined decomposition - just rename inputs.csv and meta.csv

./scripts_trial/project_fixes.sh

python3 scripts_trial/try_catch_filter.py

# run project_fixes scripts_trial for each projects

./scripts_trial/project_fixes/joda-time.sh
./scripts_trial/project_fixes/commons-collections4.sh
./scripts_trial/project_fixes/JSON-java.sh
./scripts_trial/project_fixes/commons-net.sh
./scripts_trial/project_fixes/commons-configuration2.sh

python3 scripts_trial/3_rebuild_tests.py

# run each projects' fix.sh before running mvn test


# cd projects_decomposed/commons-net

# mvn clean --color never test -Dtest="*OE25Dev#*_oe"

# module load maven

# module load java/17

# ./scripts_trial/clean_loop.sh > compile_error_test_files.log


./scripts_trial/clean_loop.sh
python3 scripts_trial/test_count.py

# python3 scripts_trial/filter_by_logs.py

# find projects_decomposed -type f -name "*_OE25Dev.java" -delete

# python3 scripts_trial/3_rebuild_tests.py

# ./scripts_trial/clean_loop.sh

# # failes/error removal script

# # ./scripts_trial/clean_loop.sh

# # ./scripts_trial/final_mvn_run.sh

# # python3 scripts_trial/filter_dataset.py
