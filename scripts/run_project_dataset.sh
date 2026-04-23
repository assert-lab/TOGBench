# ./scripts/run_project_dataset.sh

if [ ! -d projects_decomposed ]; then
    echo "Copying projects_original -> projects_decomposed"
    cp -r projects_original projects_decomposed
fi

find projects_decomposed -type f -name "*_OE25Dev*.java" -delete

python3 scripts/rebuild_dataset_tests.py

./scripts/clean_run_mvn.sh > clean_loop_mvn.log

python3 scripts/data_csv/test_count.py
