#!/bin/bash

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

timeout 2h mvn pitest:mutationCoverage 2>&1 | tee -a pit.log
exit_code=$?

if [ $exit_code -eq 124 ]; then
    echo "[TIMEOUT] PIT" | tee -a pit.log
fi

end_ts=$(date "+%Y-%m-%d %H:%M:%S")
echo "ended at: $end_ts" | tee -a pit.log
