cd "$PWD/projects_decomposed/jsoup"
echo $PWD

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"ConnectTest.exceptOnUnsupportedProtocol_1_oe",
"ConnectTest.throwsExceptionOn404*"
"ParseTest.testBaidu_6_oe",
"StringUtilTest.padding_5_oe",
"AttributesTest.testIteratorSkipsInternal_4_oe",
"AttributesTest.testIteratorSkipsInternal_5_oe",
"AttributesTest.testIterator_2_oe",
"AttributesTest.testIterator_3_oe",
"DocumentTest.framesetSupportsBodyMethod_9_oe",
"DocumentTest.testHtmlAndXmlSyntax_1_oe",
"DocumentTest.testHtmlAndXmlSyntax_2_oe",
"ElementTest.testBasicFormats_1_oe",
"ElementTest.testClone_8_oe",
"ElementTest.testIndentLevel_2_oe",
"ElementTest.testIndentLevel_4_oe",
"ElementTest.testIndentLevel_6_oe",
"EntitiesTest.caseSensitive_1_oe",
"CharacterReaderTest.advance_2_oe",
"CharacterReaderTest.bufferUp_1_oe",
"CharacterReaderTest.canTrackNewlines_23_oe",
"CharacterReaderTest.canTrackNewlines_24_oe",
"CharacterReaderTest.canTrackNewlines_25_oe",
"CharacterReaderTest.canTrackNewlines_26_oe",
"CharacterReaderTest.canTrackNewlines_28_oe",
"CharacterReaderTest.canTrackNewlines_29_oe",
"CharacterReaderTest.canTrackNewlines_30_oe",
"CharacterReaderTest.consumeLetterSequence_2_oe",
"CharacterReaderTest.consumeLetterSequence_3_oe",
"CharacterReaderTest.consumeLetterSequence_4_oe",
"CharacterReaderTest.consumeLetterThenDigitSequence_2_oe",
"CharacterReaderTest.consumeLetterThenDigitSequence_3_oe",
"CharacterReaderTest.consumeLetterThenDigitSequence_4_oe",
"CharacterReaderTest.consumeToAny_2_oe",
"CharacterReaderTest.consumeToAny_3_oe",
"CharacterReaderTest.consumeToAny_4_oe",
"CharacterReaderTest.consumeToAny_5_oe",
"CharacterReaderTest.consumeToAny_6_oe",
"CharacterReaderTest.consumeToAny_7_oe",
"CharacterReaderTest.consumeToChar_2_oe",
"CharacterReaderTest.consumeToChar_3_oe",
"CharacterReaderTest.consumeToChar_4_oe",
"CharacterReaderTest.consumeToChar_5_oe",
"CharacterReaderTest.consumeToNonexistentEndWhenAtAnd_2_oe",
"CharacterReaderTest.consumeToNonexistentEndWhenAtAnd_3_oe",
"CharacterReaderTest.consumeToString_2_oe",
"CharacterReaderTest.consumeToString_3_oe",
"CharacterReaderTest.consumeToString_4_oe",
"CharacterReaderTest.consumeToString_5_oe",
"CharacterReaderTest.consume_10_oe",
"CharacterReaderTest.consume_11_oe",
"CharacterReaderTest.consume_12_oe",
"CharacterReaderTest.consume_4_oe",
"CharacterReaderTest.consume_5_oe",
"CharacterReaderTest.consume_6_oe",
"CharacterReaderTest.consume_7_oe",
"CharacterReaderTest.consume_8_oe",
"CharacterReaderTest.consume_9_oe",
"CharacterReaderTest.containsIgnoreCaseBuffer_13_oe",
"CharacterReaderTest.containsIgnoreCaseBuffer_14_oe",
"CharacterReaderTest.empty_2_oe",
"CharacterReaderTest.mark_3_oe",
"CharacterReaderTest.mark_4_oe",
"CharacterReaderTest.mark_8_oe",
"CharacterReaderTest.matchesAny_3_oe",
"CharacterReaderTest.matchesAny_4_oe",
"CharacterReaderTest.matchesIgnoreCase_10_oe",
"CharacterReaderTest.matchesIgnoreCase_11_oe",
"CharacterReaderTest.matchesIgnoreCase_13_oe",
"CharacterReaderTest.matches_11_oe",
"CharacterReaderTest.matches_6_oe",
"CharacterReaderTest.matches_7_oe",
"CharacterReaderTest.matches_9_oe",
"CharacterReaderTest.nextIndexOfString_4_oe",
"CharacterReaderTest.nextIndexOfString_5_oe",
"CharacterReaderTest.nextIndexOfString_6_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_10_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_11_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_12_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_13_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_14_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_15_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_16_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_3_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_5_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_6_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_7_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_8_oe",
"CharacterReaderTest.notEmptyAtBufferSplitPoint_9_oe",
"CharacterReaderTest.unconsume_2_oe",
"HtmlParserTest.handlesQuotesInCommentsInScripts_1_oe",
"HtmlParserTest.handlesXmlDeclAndCommentsBeforeDoctype_1_oe",
"HtmlParserTest.preservesTabs_4_oe",
"HtmlTreeBuilderStateTest.nestedAnchorElements01_1_oe",
"HtmlTreeBuilderStateTest.nestedAnchorElements02_1_oe",
"TokeniserTest.cp1252EntitiesProduceError_2_oe",
"XmlTreeBuilderTest.testSupplyParserToConnection_3_oe",
"ElementIT.testFastReparentExistingContent_4_oe",
"ElementIT.testFastReparentExistingContent_5_oe",
"ElementIT.testFastReparentExistingContent_6_oe",
"ElementIT.testFastReparentExistingContent_7_oe",
"ElementIT.testFastReparentExistingContent_8_oe",
"ElementIT.testFastReparentExistingContent_9_oe",
"ElementIT.testFastReparent_3_oe",
"ElementIT.testFastReparent_4_oe",
"ElementIT.testFastReparent_5_oe",
"ElementIT.testFastReparent_6_oe",
"CharacterReaderTest.unconsume_3_oe",
"CharacterReaderTest.unconsume_4_oe",
"CharacterReaderTest.unconsume_5_oe",
"CharacterReaderTest.unconsume_6_oe",
"CharacterReaderTest.unconsume_7_oe",
"CharacterReaderTest.unconsume_8_oe",
"CharacterReaderTest.unconsume_9_oe",
"CharacterReaderTest.unconsume_10_oe",
"CharacterReaderTest.unconsume_11_oe",
"CharacterReaderTest.unconsume_12_oe",
"CharacterReaderTest.unconsume_13_oe",
"CharacterReaderTest.unconsume_14_oe",
"CharacterReaderTest.unconsume_15_oe",
"CharacterReaderTest.unconsume_16_oe",
"CharacterReaderTest.unconsume_17_oe",
"CharacterReaderTest.unconsume_18_oe",
"ParseTest.testBaidu_6_oe",
"ConnectTest.throwsExceptionOn404*",


]

patterns = []
for full in fail_list:
    cls, name = full.split(".", 1)
    if name.endswith("*"):
        name = name[:-1]  # strip '*'
    patterns.append((cls, name))

bad_ids = set()
meta_rows = []
failed_meta_rows = []
removed_meta = 0

with meta_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    fieldnames = r.fieldnames
    for row in r:
        test_class = row["test_class"]
        test_name = row["test_name"]
        matched = False
        for cls_pattern, name_prefix in patterns:
            if test_class == cls_pattern and test_name.startswith(name_prefix):
                matched = True
                break
        if matched:
            bad_ids.add(row["id"])
            failed_meta_rows.append(row)
            removed_meta += 1
        else:
            meta_rows.append(row)

print("removed from meta:", removed_meta)
print("bad_ids:", len(bad_ids))

with meta_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=fieldnames)
    w.writeheader()
    w.writerows(meta_rows)




inputs_rows = []
failed_inputs_rows = []
removed_inputs = 0

with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    in_fields = r.fieldnames
    for row in r:
        if row["id"] in bad_ids:
            failed_inputs_rows.append(row)
            removed_inputs += 1
            continue
        inputs_rows.append(row)

print("removed from inputs:", removed_inputs)

with inputs_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=in_fields)
    w.writeheader()
    w.writerows(inputs_rows)

def merge_dedup_by_id(path, base_fieldnames, new_rows):
    if not new_rows and not path.exists():
        return

    merged = {}
    all_fields = list(base_fieldnames or [])

    def add_fields_from_row(row):
        nonlocal all_fields
        for k in row.keys():
            if k not in all_fields:
                all_fields.append(k)

    if path.exists():
        with path.open(newline="", encoding="utf-8") as f:
            r = csv.DictReader(f)
            if r.fieldnames:
                for k in r.fieldnames:
                    if k not in all_fields:
                        all_fields.append(k)
            for row in r:
                rid = (row.get("id") or "").strip()
                if rid:
                    add_fields_from_row(row)
                    merged[rid] = row

    for row in new_rows:
        rid = (row.get("id") or "").strip()
        if rid:
            add_fields_from_row(row)
            merged[rid] = row

    with path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=all_fields, lineterminator="\n", extrasaction="ignore")
        w.writeheader()
        for rid in sorted(merged.keys()):
            rr = dict(merged[rid])
            for k in all_fields:
                rr.setdefault(k, "")
            w.writerow(rr)


meta_failed_path = dataset / "meta_mvn_failed.csv"
merge_dedup_by_id(meta_failed_path, fieldnames, failed_meta_rows)

inputs_failed_path = dataset / "inputs_mvn_failed.csv"
merge_dedup_by_id(inputs_failed_path, in_fields, failed_inputs_rows)


PY
