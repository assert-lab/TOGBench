# # ./scripts/run_tests.sh

# #!/bin/bash
# set -e

# folder="projects_decomposed/joda-time"

# echo "running $folder"
# cd "$folder"
# mvn -B clean test \
#   -Dtest="*_OE25Dev#*_oe" \
#   -Drat.skip=true \
#   -Dstyle.color=never \
#   -DforkedProcessTimeoutInSeconds=10 \
#   2>&1 | tee mvn_log.txt

#!/bin/bash

folder="projects_decomposed/joda-time"

echo "running $folder"

while true
do
    cd "$folder"

    mvn -B clean test \
      -Dtest="*_OE25Dev#*_oe" \
      -Drat.skip=true \
      -Dstyle.color=never \
      -DforkedProcessTimeoutInSeconds=10 \
      2>&1 | tee mvn_log.txt

    if grep -q "COMPILATION ERROR" mvn_log.txt; then
        echo "compile error found, fixing assertions"
        cd ../..
        python3 scripts/comment-incompatible_assertions.py
    else
        echo "no compilation errors"
        break
    fi
done