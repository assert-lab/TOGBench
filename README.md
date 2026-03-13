# TOGBench: A Multi-Variant Dataset and Benchmark Suite for Test Oracle Generation

<p align="center">
    <a href="#getting-started">Quick Start</a> •
    <a href="#dataset">Dataset</a> •
    <a href="#pipeline">Pipeline</a> •
    <a href="#scoring">Scoring</a> •
    <a href="#citation">Citation</a>
</p>

TOGBench is a benchmark suite for test oracle generation (TOG) built around **OE25dev**, a multi-variant dataset mined from developer-written unit tests across 25 real-world Java systems spanning 56 modules. It provides an end-to-end evaluation toolchain covering oracle injection, compilation, execution-driven validation, and mutation-based adequacy assessment.

The dataset currently includes **81,176 single-oracle instances** (75,088 assertion and 6,088 exception), **3,424 mixed assertion+exception tests**, **17,020 multi-assertion-oracle tests**, and **5,747 custom-oracle instances**.

---

## Motivation

Most existing TOG benchmarks rely on automatically generated tests or apply simplifying assumptions — restricting to single assertions, capping input length, or avoiding developer-written tests entirely. These choices under-represent the fixture-heavy setup code, helper abstractions, project-specific conventions, mixed oracle styles, and long contexts found in real test suites.

TOGBench addresses this gap by providing developer-written oracle instances with realistic test context and a standardized harness for execution- and mutation-driven evaluation.

---

## Repository Structure

```
.
├── dataset_mixed/                  # Per-project dataset for mixed tests
│   └── <project>/
├── dataset_single/                 # Per-project dataset for single tests
│   └── <project>/
├── dataset_custom/                 # Per-project dataset for custom tests
│   └── <project>/
├── dataset_multiple/               # Per-project dataset for multiple tests
│   └── <project>/
├── projects_decomposed/            # Rebuilt projects from OE25dev datasets
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

## Getting Started

Install dependencies:

```bash
pip install -r requirements.txt
```

The code has been tested with Python 3.9, Java 8 (JDK), and Maven 3.x.

---

## Dataset

OE25dev is available in per-variant CSV files under `dataset_<type>/<project>/`. Each instance contains the following fields:

| Field | Description |
|---|---|
| `id` | Unique instance identifier |
| `test_prefix` | Developer-written test prefix with `<place_holder>` at the oracle position |
| `mut` | Primary method under test |
| `doc` | Javadoc for the method under test (when available) |
| `meta` | Reconstruction metadata (project, module, file paths, oracle kind, build framework) |
| `oracle` | Reference oracle block (ground truth label) |

The four dataset variants are:

- **Single** — one oracle block per instance, isolated from the original test while preserving context (81,176 instances)
- **Multiple** — original test methods containing at least two assertion oracle blocks, released without decomposition (17,020 tests)
- **Mixed** — original test methods containing at least one assertion oracle and at least one exception oracle (3,424 tests)
- **Custom** — invocations of project-defined assertion wrappers recorded as individual instances (5,747 instances)

---

## Pipeline

### 1. Full Dataset Rebuild and Maven Run

Run the complete pipeline end-to-end:

```bash
./scripts/run_project_dataset.sh
```

This executes the following steps in order:

**Step 1 — Remove previous rebuild files**
```bash
find projects_decomposed -type f -name "*_OE25Dev*.java" -delete
```

**Step 2 — Rebuild test files from dataset**
```bash
python3 scripts/rebuild_dataset_tests.py
```

Interactively rebuilds test files from the selected dataset variant into `projects_decomposed/`.

**Step 3 — Run Maven across all projects**
```bash
./scripts/clean_run_mvn.sh > clean_loop_mvn.log
```

Runs each project module and saves individual Maven logs under `logs/`.

**Step 4 — Collect statistics**
```bash
python3 scripts/data_csv/test_count.py
```

Counts and summarises test results from the Maven logs per project.

---

### 2. Evaluating a TOG Tool (Assertion Injection Workflow)

Use this workflow to evaluate a tool's generated assertions against the dataset.

**Step 1 — Prepare the input CSV**

Place the CSV file containing the tool's assertion output in the same directory as the relevant dataset variant.

**Step 2 — Strip existing assertions**
```bash
python3 scripts/remove_assertion.py
```

Removes oracle blocks from the input CSV to produce `inputs_no_assert.csv`.

**Step 3 — Inject tool predictions**
```bash
python3 scripts/inject_assertion.py \
  --inputs dataset_<type>/<project>/inputs_no_assert.csv \
  --preds  dataset_<type>/<project>/generated_oracles.csv \
  --out    dataset_<type>/<project>/inputs_llm.csv
```

Merges the tool's predicted oracles into the input file, producing `inputs_llm.csv`.

**Step 4 — Rebuild test files**
```bash
python3 scripts/rebuild_dataset_tests.py
```

Regenerates `.java` test files using the injected assertions.

**Step 5 — Run tests**
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

To count commented-out assertions for a specific project (e.g. `joda-time`):

```bash
grep -R --include="*OE25Dev.java" "// incorrect assertion" projects_decomposed/joda-time | wc -l
```

---

## Scoring

Evaluation metrics are computed directly from Maven execution logs and PIT mutation reports. The harness reports:

- **Compilation Rate** — proportion of reintegrated test classes that compile successfully
- **Execution Pass Rate** — proportion of compiled tests that pass
- **False Positive Rate** — proportion of passing tests that pass on incorrect program versions
- **Mutation Score** — oracle adequacy measured via PIT mutation testing on green suites

All raw results and pass rates are written to `evaluation/evaluation_results/` after running the evaluation scripts.

---

## Logs

- `clean_loop_mvn.log` — aggregate Maven output from the full pipeline run
- `logs/<project>/` — per-project Maven logs produced by `clean_run_mvn.sh`
- `projects_original/` — original unmodified projects with baseline PIT mutation and Maven logs

---

## Notes

- All generated test files follow the naming convention `*_OE25Dev.java`.
- The `projects_original/` directory serves as the baseline reference and should not be modified.
- Dataset variants (`single`, `multiple`, `mixed`, `custom`) are independent and can each be used as input to `rebuild_dataset_tests.py`.
- Projects are pinned to specific released versions that build and test deterministically under JDK 8 and support PIT without modifying production code.
