#!/usr/bin/env bash

out="pit_summary.csv"
echo -e "project/module\ttests_run\tLine Coverage\ttests examined\tMutation score\tGenerated Mutants\tMutants Killed\tTest Strength" > "$out"

collect() {
    local proj="$1"
    local log="$2"

    tests_run=$(grep -oP 'Ran\s+\K[0-9]+(?=\s+tests)' "$log" | tail -1)
    line_cov=$(grep -oP 'Line Coverage.*?: \K[0-9]+/[0-9]+ \([0-9]+%' "$log" | tail -1)
    tests_examined=$(grep -oP '>>\s*\K[0-9]+(?=\s+tests examined)' "$log" | tail -1)
    mutation_score=$(grep -oP 'Killed\s+[0-9]+\s+\(\K[0-9]+(?=%\))' "$log" | tail -1)
    generated=$(grep -oP 'Generated\s+\K[0-9]+' "$log" | tail -1)
    killed=$(grep -oP 'mutations Killed\s+\K[0-9]+' "$log" | tail -1)
    strength=$(grep -oP 'Test strength\s+\K[0-9]+' "$log" | tail -1)

    # Fill blanks with empty fields
    [ -z "$tests_run" ] && tests_run=""
    [ -z "$line_cov" ] && line_cov=""
    [ -z "$tests_examined" ] && tests_examined=""
    [ -z "$mutation_score" ] && mutation_score=""
    [ -z "$generated" ] && generated=""
    [ -z "$killed" ] && killed=""
    [ -z "$strength" ] && strength=""

    echo -e "${proj}\t${tests_run}\t${line_cov}\t${tests_examined}\t${mutation_score}\t${generated}\t${killed}\t${strength}" >> "$out"
}

export -f collect

# Walk all folders depth 1 and 2 to support modules
find . -name "pit.log" | while read -r log; do
    dir=$(dirname "$log")
    proj=${dir#./}     # strip leading "./"
    collect "$proj" "$log"
done
