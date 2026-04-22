<h1 align="center">TOGBench: A Developer-Written Multi-Variant Dataset and Benchmark Suite for Test Oracle Generation</h1>

<p align="center">
    <a href="#overview">Overview</a> •
    <a href="#features">Features</a> •
    <a href="#quick-start">Quick Start</a> •
    <a href="#outputs">Outputs</a> •
    <a href="#scripts">Scripts</a> •
    <a href="#projects">Projects</a>
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

---

## Projects

| Project (version) | Domain | SLOC | Test LOC | Modules | Test Framework |
|---|---|---:|---:|---:|---|
| async-http-client (2.12.3) | Asynchronous HTTP library | 16,299 | 21,746 | 8 | TestNG |
| bcel (6.5.0) | Bytecode Engineering Library | 35,571 | 6,647 | 1 | JUnit 4.13 |
| commons-beanutils (1.9.4) | Reflection and Introspection API | 11,725 | 38,626 | 1 | JUnit 4.12 |
| commons-collections4 (4.4) | Utilities for Java Collections Framework | 6,697 | 51,768 | 1 | JUnit 4.12 |
| commons-configuration2 (2.8.0) | Utilities for reading configuration files | 4,553 | 52,017 | 1 | JUnit 4.13.2 |
| commons-dbutils (1.7) | Java utility for JDBC development | 3,079 | 6,106 | 1 | JUnit 4.12 |
| commons-geometry (1.0) | Geometric types and utilities | 14,168 | 66,558 | 5 | JUnit 5.8.0-M1 |
| commons-imaging (1.0-alpha3) | Java image library | 32,275 | 18,381 | 1 | JUnit 5.8.2 |
| commons-jcs3 (3.1) | Caching system | 14,328 | 26,738 | 4 | JUnit 4.13 |
| commons-jexl3 (3.2.1) | Scripting utilities for Java application | 2,295 | 22,258 | 1 | JUnit 4.13.2 |
| commons-lang3 (3.12.0) | Java helper utilities | 5,321 | 72,696 | 1 | JUnit 5.7.1 |
| commons-net (3.8.0) | Network utilities / protocol implementations | 19,407 | 11,965 | 1 | JUnit 4.13.1 |
| commons-numbers (1.0) | Java utility for number types | 5,502 | 18,218 | 11 | JUnit 5.4.2 |
| commons-pool2 (2.11.1) | Object Pooling Library | 1,197 | 12,968 | 1 | JUnit 5.8.0-M1 |
| commons-rng (1.4) | Pseudo-random generators | 9,654 | 24,841 | 5 | JUnit 5.7.2 |
| commons-validator (1.7) | Client and server side data validation | 7,829 | 15,537 | 1 | JUnit 4.13 |
| commons-vfs (2.9.0) | Virtual File System library | 7,751 | 21,739 | 2 | JUnit 4.13.2 |
| commons-weaver (2.0) | Utility to enhance compiled Java classes | 4,527 | 2,000 | 2 | JUnit 4.12 |
| http-request (6.0) | Library for making HTTP requests | 1,395 | 3,889 | 1 | JUnit 4.1 |
| joda-time (2.11.2) | Date and time library | 32,312 | 73,997 | 1 | JUnit 3.8.2 |
| JSON-java (20220924) | JSON library for Java | 4,220 | 12,069 | 1 | JUnit 4.12 |
| jsoup (1.15.3) | Java library for HTML | 13,905 | 15,025 | 1 | JUnit 5.9.0 |
| scribejava (8.3.1) | OAuth library | 2,173 | 2,367 | 1 | JUnit 4.13.2 |
| spark (2.9.3) | Framework for creating web applications | 6,124 | 7,302 | 1 | JUnit 4.12 |
| springside4 (5.0.0-SNAPSHOT) | JavaEE application reference architecture | 9,336 | 6,155 | 2 | JUnit 4.12 |
| **Total** | | **271,643** | **611,613** | **56** | — |
