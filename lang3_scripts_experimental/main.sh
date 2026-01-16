find projects_decomposed/commons-lang3-3.12.0-src -type f -name "*_OE25Dev.java" -delete

# ./lang3_scripts/build_treesitter.sh

./lang3_scripts/expand_arch_type_asserts_lang3.py

python3 lang3_scripts/1_build_dataset.py

python3 lang3_scripts/dataset_post_process.py

python3 lang3_scripts/2_filter_compilable_tests.py

python3 lang3_scripts/3_rebuild_tests.py

module load maven

module load java/17

./lang3_scripts/clean_loop.sh

./lang3_scripts/clean_loop.sh

python3 ./lang3_scripts/filter_by_logs.py

find projects_decomposed/commons-lang3-3.12.0-src -type f -name "*_OE25Dev.java" -delete

python3 lang3_scripts/3_rebuild_tests.py

./lang3_scripts/clean_loop.sh

# failes/error removal script

./lang3_scripts/clean_loop.sh

# ./lang3_scripts/final_mvn_run.sh

# python3 lang3_scripts/filter_dataset.py

# ./lang3_scripts/list_oe25dev_files.sh