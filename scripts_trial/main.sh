find projects_decomposed -type f -name "*_OE25Dev.java" -delete

find projects_decomposed -type d -name "dataset" -exec rm -rf {} +

# ./scripts_trial/build_treesitter.sh

# ./scripts_trial/expand_arch_type_asserts_lang3.py

python3 scripts_trial/1_build_dataset.py

python3 scripts_trial/dataset_post_process.py

python3 scripts_trial/2_filter_compilable_tests.py

# run project_fixes scripts_trial for each projects
./scripts_trial/project_fixes/joda-time.sh
./scripts_trial/project_fixes/commons-collections4.sh
./scripts_trial/project_fixes/JSON-java.sh

python3 scripts_trial/3_rebuild_tests.py

# run each projects' fix.sh before running mvn test
./scripts_trial/project_fixes.sh

cd projects_decomposed/commons-beanutils
mvn clean --color never test -Dtest="*OE25Dev#*_oe"

# module load maven

# module load java/17

# ./scripts_trial/clean_loop.sh > compile_error_test_files.log


# ./scripts_trial/clean_loop.sh

# python3 scripts_trial/test_count.py

# python3 scripts_trial/filter_by_logs.py

# find projects_decomposed -type f -name "*_OE25Dev.java" -delete

# python3 scripts_trial/3_rebuild_tests.py

# ./scripts_trial/clean_loop.sh

# # failes/error removal script

# # ./scripts_trial/clean_loop.sh

# # ./scripts_trial/final_mvn_run.sh

# # python3 scripts_trial/filter_dataset.py
