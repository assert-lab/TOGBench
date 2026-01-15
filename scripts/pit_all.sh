#!/bin/bash

# go to ../projects relative to where this script is invoked
cd ../projects || { echo "Directory ../projects not found"; exit 1; }

# find all pit.sh files and run them
find . -type f -name "pit.sh" | while read -r script; do
    dir=$(dirname "$script")
    echo "=== Running pit.sh in $dir ==="
    (
        cd "$dir" || exit
        bash pit.sh
    )
done
