# TOGBench

A benchmark pipeline for rebuilding, injecting, and evaluating test oracle assertions across decomposed Java projects using the OE25Dev dataset and Maven.

---

## Repository Structure

```
.
├── dataset_mixed/                  # Per-project dataset for mixed tests
│   └── <project>/
│       ├── inputs_no_assert.csv
│       ├── inputs_llm.csv
│       └── togll.csv
├── dataset_single/                 # Per-project dataset for single tests
│   └── <project>/
├── dataset_custom/                 # Per-project dataset for custom tests
│   └── <project>/
├── dataset_multiple/               # Per-project dataset for multiple tests
│   └── <project>/
├── projects_decomposed/            # Rebuilt projects from OE25Dev datasets
│   ├── async-http-client/
│   ├── bcel/
│   ├── commons-beanutils/
│   ├── commons-collections4/
│   ├── commons-configuration2/
│   ├── commons-dbutils/
│   ├── commons-geometry/
│   ├── commons-imaging/
│   ├── commons-jcs3/
│   ├── commons-jexl3/
│   ├── commons-lang3/
│   ├── commons-net/
│   ├── commons-numbers/
│   ├── commons-pool2/
│   ├── commons-rng/
│   ├── commons-validator/
│   ├── commons-vfs/
│   ├── commons-weaver/
│   ├── http-request/
│   ├── joda-time/
│   ├── JSON-java/
│   ├── jsoup/
│   ├── mvn_log.txt
│   ├── scribejava/
│   ├── spark/
│   └── springside4/
├── projects_original/              # Original projects with PIT and Maven logs
├── logs/                           # Per-project Maven logs
└── scripts/                        # All pipeline scripts
    ├── run_project_dataset.sh
    ├── rebuild_dataset_tests.py
    ├── clean_run_mvn.sh
    ├── remove_assertion.py
    ├── inject_assertion.py
    ├── comment-incompatible_assertions.py
    └── data_csv/
        └── test_count.py
```

---

## Full Pipeline

### 1. Dataset Rebuild & Maven Run

Run the full pipeline end-to-end using:

```bash
./scripts/run_project_dataset.sh
```

This script executes the following steps in order:

**Step 1 — Clean previous rebuild files**
```bash
find projects_decomposed -type f -name "*_OE25Dev*.java" -delete
```
Removes any previously generated test files to ensure a clean rebuild.

**Step 2 — Rebuild tests from dataset**
```bash
python3 scripts/rebuild_dataset_tests.py
```
Interactively rebuilds test files from the chosen dataset into `projects_decomposed/`.

**Step 3 — Run Maven across all projects**
```bash
./scripts/clean_run_mvn.sh > clean_loop_mvn.log
```
Runs each project module, saving individual Maven logs under `logs/`.

**Step 4 — Collect statistics**
```bash
python3 scripts/data_csv/test_count.py
```
Counts and summarises test results from the Maven logs per project.

---

### 2. Testing a Tool (Assertion Injection Workflow)

Use this workflow to evaluate a tool's generated assertions against the dataset.

**Step 1 — Prepare input CSV**

Save the CSV file with assertion output in the same directory as the dataset. The file should contain assertion statements alongside test inputs.

**Step 2 — Strip existing assertions**
```bash
python3 scripts/remove_assertion.py
```
Removes assertions from the input CSV, producing a clean `inputs_no_assert.csv`.

**Step 3 — Inject tool predictions**
```bash
python3 scripts/inject_assertion.py \
  --inputs dataset_<type>/<project>/inputs_no_assert.csv \
  --preds  dataset_<type>/<project>/togll.csv \
  --out    dataset_<type>/<project>/inputs_llm.csv
```
Merges tool-predicted assertions into the input file, producing `inputs_llm.csv`.

**Step 4 — Rebuild test files**
```bash
python3 scripts/rebuild_dataset_tests.py
```
Regenerates the `.java` test files using the injected assertions.

**Step 5 — Run tests per project**
```bash
./scripts/run_tests.sh
```
Executes tests for the target project and saves logs under `logs/`.

---

### 3. Handling Compilation Errors

If compilation errors persist after injection, comment out incompatible assertions:

```bash
python3 scripts/comment-incompatible_assertions.py
```

To count how many assertions were commented out for a given project (e.g. `joda-time`):

```bash
grep -R --include="*OE25Dev.java" "// incorrect assertion" projects_decomposed/joda-time | wc -l
```

---

## Logs

- `clean_loop_mvn.log` — Aggregate Maven output from the full pipeline run
- `logs/<project>/` — Per-project Maven logs produced by `clean_run_mvn.sh`
- `projects_original/` — Original unmodified projects with their baseline PIT mutation and Maven logs

---

## Notes

- All generated test files follow the naming convention `*_OE25Dev.java`.
- The `projects_original/` directory should not be modified; it serves as the baseline reference.
- Dataset variants (`mixed`, `single`, `custom`, `multiple`) are independent and can each be used as input to `rebuild_dataset_tests.py`.
