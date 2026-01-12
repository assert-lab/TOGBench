#!/usr/bin/env bash

ROOT_DIR="$(pwd)"

run_pit() {
    dir="$1"
    cd "$dir" || return
    echo "=== $dir ==="
    rm -rf target

    # ----- MVN TEST -----
    start_ts=$(date "+%Y-%m-%d %H:%M:%S")
    echo "began at: $start_ts" > mvn.log

    timeout 30m mvn clean test -Drat.skip=true -Dtest.dir=src -Dtarget.dir=target 2>&1 | tee -a mvn.log
    exit_code=$?

    if [ $exit_code -eq 124 ]; then
        echo "[TIMEOUT] mvn test" | tee -a mvn.log
    fi

    end_ts=$(date "+%Y-%m-%d %H:%M:%S")
    echo "ended at: $end_ts" | tee -a mvn.log

    # ----- PIT TEST -----
    start_ts=$(date "+%Y-%m-%d %H:%M:%S")
    echo "began at: $start_ts" > pit.log

    timeout 2h mvn -Drat.skip=true -Dtest.dir=src -Dtarget.dir=target pitest:mutationCoverage 2>&1 | tee -a pit.log
    exit_code=$?

    if [ $exit_code -eq 124 ]; then
        echo "[TIMEOUT] PIT" | tee -a pit.log
    fi

    end_ts=$(date "+%Y-%m-%d %H:%M:%S")
    echo "ended at: $end_ts" | tee -a pit.log

    cd "$ROOT_DIR" || exit 1
}

targets=()

if [ -n "$1" ]; then
    targets=("$1")
else
    while IFS= read -r pom; do
        dir="$(dirname "$pom")"
        if [[ "$(basename "$dir")" == "async-http-client" ]]; then
            continue
        fi
        targets+=("$dir")
    done < <(grep -RIlE "pitest-maven" --include="pom.xml" .)

fi

for dir in "${targets[@]}"; do
    run_pit "$dir"
done


# find . -type f -name "pit.sh" -print0 | while IFS= read -r -d '' pit_file; do
#     dir="$(dirname "$pit_file")"
#     echo "=== Running PIT in $dir ==="
#     (
#         cd "$dir" && \
#         chmod +x pit.sh && \
#         ./pit.sh
#     )
# done
