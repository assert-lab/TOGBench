#!/usr/bin/env bash
set -euo pipefail

ROOT="$PWD/projects_decomposed/JSON-java"
DATASET_DIR="$ROOT/dataset"
META="$DATASET_DIR/meta.csv"
INPUTS="$DATASET_DIR/inputs.csv"

cd "$ROOT"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [
  "JSONArrayTest_OE25Dev.iteratorTest_2_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_3_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_4_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_5_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_6_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_8_oe",
  "JSONArrayTest_OE25Dev.jsonArrayClearMethodTest_1_oe",
  "JSONArrayTest_OE25Dev.toList_30_oe",
  "JSONObjectTest_OE25Dev.jsonObjectClearMethodTest_1_oe",
  "JSONObjectTest_OE25Dev.testSingletonBean_7_oe",
  "JSONObjectTest_OE25Dev.testSingletonBean_8_oe",
  "JSONObjectTest_OE25Dev.testSingletonEnumBean_7_oe",
  "JSONObjectTest_OE25Dev.testSingletonEnumBean_8_oe",
  "JSONObjectTest_OE25Dev.toMap_28_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_3_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_6_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_7_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_8_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_10_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_11_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_12_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_13_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_14_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_7_oe",
  "JSONArrayTest_OE25Dev.iteratorTest_9_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_10_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_11_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_1_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_2_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_3_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_4_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_5_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_6_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_7_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_8_oe",
  "JSONObjectTest_OE25Dev.jsonObjectByBean1_9_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_10_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_11_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_12_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_13_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_14_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_15_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_16_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_17_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_18_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_19_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_20_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_21_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_22_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_23_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_24_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_25_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_26_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_27_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_28_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_29_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_30_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_31_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_32_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_33_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_34_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_35_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_36_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_37_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_38_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_39_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_40_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_41_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_42_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_43_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_44_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_45_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_46_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_47_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_48_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_49_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_50_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_51_oe",
  "JSONTokenerTest_OE25Dev.testNextBackComboWithNewLines_9_oe",
  "JSONObjectTest_OE25Dev.issue654StackOverflowInputWellFormed_1_oe",
  "JSONObjectTest_OE25Dev.issue654StackOverflowInputWellFormed_2_oe",
  "CDLTest.badEscapedQuote_2_oe",
"CDLTest.nullInName_2_oe",
"JSONArrayTest.failedGetArrayValues_10_oe",
"JSONArrayTest.failedGetArrayValues_12_oe",
"JSONArrayTest.failedGetArrayValues_14_oe",
"JSONArrayTest.failedGetArrayValues_16_oe",
"JSONArrayTest.failedGetArrayValues_2_oe",
"JSONArrayTest.failedGetArrayValues_6_oe",
"JSONArrayTest.failedGetArrayValues_8_oe",
"JSONMLTest.emptyTagException_2_oe",
"JSONObjectTest.jsonObjectNonAndWrongValues_12_oe",
"JSONObjectTest.jsonObjectNonAndWrongValues_16_oe",
"JSONObjectTest.jsonObjectNonAndWrongValues_20_oe",
"JSONObjectTest.jsonObjectNonAndWrongValues_24_oe",
"JSONObjectTest.jsonObjectNonAndWrongValues_28_oe",
"JSONObjectTest.jsonObjectNonAndWrongValues_32_oe",
"JSONObjectTest.jsonObjectNonAndWrongValues_4_oe",
"JSONObjectTest.jsonObjectNonAndWrongValues_8_oe",

]

pairs = set()
for full in fail_list:
    cls, name = full.split(".", 1)
    base_cls = cls.replace("_OE25Dev", "")
    pairs.add((base_cls, name))

bad_ids = set()
meta_rows = []

with meta_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    fieldnames = r.fieldnames
    for row in r:
        key = (row["test_class"], row["test_name"])
        if key in pairs:
            bad_ids.add(row["id"])
        else:
            meta_rows.append(row)

with meta_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=fieldnames)
    w.writeheader()
    w.writerows(meta_rows)

inputs_rows = []
with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    in_fields = r.fieldnames
    for row in r:
        if row["id"] in bad_ids:
            continue
        inputs_rows.append(row)

with inputs_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=in_fields)
    w.writeheader()
    w.writerows(inputs_rows)
PY
