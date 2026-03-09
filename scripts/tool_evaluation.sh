python3 scripts/remove_assertion.py --root projects_decomposed/joda-time

python3 scripts/inject_assertion.py \
  --inputs projects_decomposed/joda-time/dataset/inputs_no_assert.csv \
  --preds  projects_decomposed/joda-time/dataset/doc2oracll.csv \
  --out    projects_decomposed/joda-time/dataset/inputs_llm.csv

find projects_decomposed -type f -name "*_OE25Dev*.java" -delete

python3 scripts/3_rebuild_decomposed.py

mvn test -Dtest="*_OE25Dev#*_oe"

./scripts/run_tests.sh

python3 scripts/comment-incompatible_assertions.py

grep -R --include="*OE25Dev.java" "// incorrect assertion" projects_decomposed/joda-time | wc -l
