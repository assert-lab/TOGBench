# ./scripts/run_tests.sh

#!/bin/bash
set -e

folder="projects_decomposed/JSON-java"

echo "running $folder"
cd "$folder"
mvn -B clean test \
  -Dtest="*_OE25Dev#*_oe" \
  -Drat.skip=true \
  -Dstyle.color=never \
  -DforkedProcessTimeoutInSeconds=10 \
  2>&1 | tee mvn_log.txt