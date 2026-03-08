python3 scripts/remove_assertion.py --root projects_decomposed/commons-configuration2

python3 scripts/inject_assertion.py \
  --inputs projects_decomposed/commons-configuration2/dataset/inputs_no_assert.csv \
  --preds  projects_decomposed/commons-configuration2/dataset/doc2oracll.csv \
  --out    projects_decomposed/commons-configuration2/dataset/inputs_llm.csv

find projects_decomposed -type f -name "*_OE25Dev*.java" -delete

python3 scripts/3_rebuild_decomposed.py

mvn test -Dtest="*_OE25Dev#*_oe"

./scripts/run_tests.sh

python3 scripts/comment-incompatible_assertions.py

grep -R --include="*OE25Dev.java" "// incorrect assertion" projects_decomposed/commons-configuration2 | wc -l



# ==== copy rows to inputs.csv ======
python3 - <<'PY'
import csv

inputs="dataset/inputs_final.csv"
inputs_try="dataset/inputs_passed_1_many.csv"
meta="dataset/meta_delete.csv"
meta_try="dataset_MUST_THROW/meta_passed.csv"

out_inputs="dataset/inputs_final.csv"
out_meta="dataset/meta_delete.csv"

def replace_add_rows(src,repl,out):
    with open(src) as f:
        r=csv.DictReader(f)
        rows=list(r)
        fields=r.fieldnames

    with open(repl) as f:
        r=csv.DictReader(f)
        rep={x["id"]: {k:x.get(k,"") for k in fields} for x in r}

    existing_ids=set()

    out_rows=[]
    for x in rows:
        existing_ids.add(x["id"])
        if x["id"] in rep:
            out_rows.append(rep[x["id"]])
        else:
            out_rows.append(x)

    for i in rep:
        if i not in existing_ids:
            out_rows.append(rep[i])

    with open(out,"w",newline="") as f:
        w=csv.DictWriter(f,fieldnames=fields)
        w.writeheader()
        w.writerows(out_rows)

    print(out,"final rows:",len(out_rows))

replace_add_rows(inputs,inputs_try,out_inputs)
replace_add_rows(meta,meta_try,out_meta)
PY







# remove
python3 - <<'PY'
import csv

inputs="dataset/inputs_final.csv"
meta="dataset/meta_final.csv"
remove_ids_file="dataset_check/inputs_one_assert_stmt.csv"

out_inputs="dataset/inputs_final.csv"
out_meta="dataset/meta_final.csv"

with open(remove_ids_file) as f:
    r=csv.DictReader(f)
    remove_ids={x["id"] for x in r}

def filter_file(src,out):
    with open(src) as f:
        r=csv.DictReader(f)
        rows=list(r)
        fields=r.fieldnames

    kept=[x for x in rows if x["id"] not in remove_ids]

    with open(out,"w",newline="") as f:
        w=csv.DictWriter(f,fieldnames=fields)
        w.writeheader()
        w.writerows(kept)

    print(src,"removed:",len(rows)-len(kept),"remaining:",len(kept))

filter_file(inputs,out_inputs)
filter_file(meta,out_meta)

PY