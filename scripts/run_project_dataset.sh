# ./scripts/run_project_dataset.sh

find projects_decomposed -type f -name "*_OE25Dev*.java" -delete

python3 scripts/rebuild_dataset_tests.py

./scripts/clean_run_mvn.sh > clean_loop_mvn.log

python3 scripts/data_csv/test_count.py
