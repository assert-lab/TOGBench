#!/bin/bash

cd "$(dirname "$0")/../projects_decomposed/commons-lang3-3.12.0-src" || { echo "Directory ../projects not found"; exit 1; }

find . -type f -name "pit.sh" | while read -r script; do
    dir=$(dirname "$script")
    echo "=== Cleaning and running pit.sh in $dir ==="

    (
        cd "$dir" || exit 1


        find . -type d -name "target" -exec rm -rf {} +
        find . -type d -name "pit-reports" -exec rm -rf {} +
        find . -type d -name "mutation-report" -exec rm -rf {} +

        find . -type f -name "*.log" -delete
        find . -type f -name "*.tmp" -delete
        find . -type f -name "junit*.tmp" -delete
        find . -type f -name "hs_err_pid*.log" -delete
        find . -type f -name "mutations.xml" -delete


        bash pit.sh
    )
done
