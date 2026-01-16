find projects_decomposed -type f -name "*_OE25Dev.java" -delete

./scripts/build_treesitter.sh

# ./scripts/download_projects.sh

python3 scripts/1_build_dataset.py

python3 scripts/dataset_post_process.py

python3 scripts/2_filter_compilable_tests.py

python3 scripts/3_rebuild_tests.py

# module load maven

./scripts/clean_loop.sh

./scripts/clean_loop.sh

# ./scripts/final_mvn_run.sh

# python3 scripts/filter_dataset.py

# ./scripts/list_oe25dev_files.sh