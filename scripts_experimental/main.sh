find projects_decomposed/joda-time -type f -name "*_OE25Dev.java" -delete

./scripts_experimental/build_treesitter.sh

./scripts_experimental/expand_arch_type_asserts_lang3.py

python3 scripts_experimental/1_build_dataset.py

python3 scripts_experimental/dataset_post_process.py

python3 scripts_experimental/2_filter_compilable_tests.py

python3 scripts_experimental/3_rebuild_tests.py

module load maven

module load java/17

./scripts_experimental/clean_loop.sh

./scripts_experimental/clean_loop.sh

python3 ./scripts_experimental/filter_by_logs.py

find projects_decomposed/joda-time -type f -name "*_OE25Dev.java" -delete

python3 scripts_experimental/3_rebuild_tests.py

./scripts_experimental/clean_loop.sh

# failes/error removal script

./scripts_experimental/clean_loop.sh

# ./scripts_experimental/final_mvn_run.sh

# python3 scripts_experimental/filter_dataset.py

# ./scripts_experimental/list_oe25dev_files.sh