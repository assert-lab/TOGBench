# TOGBench

<p align="center">
    <a href="#overview">Overview</a> •
    <a href="#features">Features</a> •
    <a href="#quick-start">Quick Start</a> •
    <a href="#outputs">Outputs</a> •
    <a href="#scripts">Scripts</a>
</p>

TOGBench is a benchmark suite for automated test oracle generation (TOG) built around **OE25dev**, a multi-variant dataset mined from developer-written unit tests across 25 real-world Java projects spanning 56 modules.

Unlike prior benchmarks that rely on automatically generated tests or restrict evaluation to single assertions, TOGBench preserves realistic test context, six oracle categories, and supports end-to-end executable evaluation through oracle injection, compilation, execution-driven validation, and mutation-based adequacy assessment.

---

## Overview

- **Projects:** 25
- **Modules:** 56
- **Oracle types:** 6
- **Language:** Java 8

---

## Features

### OE25dev_single

77,299 single-oracle instances derived from developer-written tests. Each instance contains one masked oracle block with the surrounding test prefix preserved.

| Oracle category | Instances |
|---|---|
| `ASSERTION_ONLY` | 73,023 |
| `MUST_THROW` | 2,899 |
| `MUST_NOT_THROW` | 694 |
| `IF_THROWN_ASSERT` | 257 |
| `MUST_THROW_WITH_PROPERTIES` | 319 |
| `FAIL_ONLY` | 107 |

### OE25dev_multiple

15,272 original test methods containing at least two standard oracle blocks of the same family, released without decomposition.

### OE25dev_mixed

1,576 original test methods containing at least one assertion oracle and at least one exception oracle, released without decomposition.

### OE25dev_custom

5,747 instances of project-defined assertion wrappers and helper methods recorded as individual oracle instances.

---

## Quick Start

**Requirements:** Python 3.9+, Java 8 JDK, Maven 3.x

1. Strip ground-truth oracles:
   ```bash
   python scripts/remove_assertion.py
   ```
2. Inject your TOG tool predictions:
   ```bash
   python scripts/inject_assertion.py
   ```
3. Rebuild test files:
   ```bash
   python scripts/rebuild_dataset_tests.py
   ```
4. Run Maven and collect results:
   ```bash
   ./scripts/clean_run_mvn.sh
   ```

Or run the full pipeline at once:
```bash
./scripts/run_project_dataset.sh
```

---

## Outputs

All results are written to `evaluation/evaluation_results/`.

- **Compilation Rate** — proportion of reintegrated test classes that compile successfully
- **Execution Pass Rate** — proportion of compiled tests that pass
- **False Positive Rate** — proportion of passing tests that pass on incorrect program versions
- **Mutation Score** — oracle adequacy measured via PIT mutation testing on green suites

---

## Scripts

| Script | Purpose |
|---|---|
| `run_project_dataset.sh` | Full pipeline entry point |
| `rebuild_dataset_tests.py` | Reconstructs `.java` test files into `projects_decomposed/` |
| `inject_assertion.py` | Merges predictions into input CSV and produces `inputs_llm.csv` |
| `remove_assertion.py` | Strips ground-truth oracles and produces `inputs_no_assert.csv` |
| `clean_run_mvn.sh` | Runs Maven across all modules and saves logs to `logs/` |
| `comment-incompatible_assertions.py` | Comments out assertions causing compilation errors |
| `data_csv/test_count.py` | Aggregates per-project test pass/fail counts |
