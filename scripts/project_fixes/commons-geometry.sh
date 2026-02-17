cd "$PWD/projects_decomposed/commons-geometry"
echo $PWD

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"AffineTransformMatrix3DTest.testToString_1_oe",
"SimpleTriangleMeshTest.testFaces_iterator_3_oe",
"SimpleTriangleMeshTest.testFaces_iterator_4_oe",
"AffineTransformMatrix2DTest.testToString_1_oe",
"GeometryIOUtilsTest.testTryApplyCloseable_functionThrows_inputCloseThrows_2_oe",
"GeometryIOUtilsTest.testTryApplyCloseable_functionThrows_inputCloseThrows_3_oe",
"SimpleTextParserTest.testGetCurrentTokenAsDouble_includedNumberFormatExceptionOnFailure_2_oe",
"SimpleTextParserTest.testGetCurrentTokenAsInt_includedNumberFormatExceptionOnFailure_2_oe",
"SimpleTextParserTest.testNextAlphanumeric_5_oe_3_oe",
"SimpleTextParserTest.testNextAlphanumeric_6_oe_1_oe",
"SimpleTextParserTest.testNextAlphanumeric_6_oe_3_oe",
"SimpleTextParserTest.testNextAlphanumeric_7_oe_1_oe",
"SimpleTextParserTest.testNextAlphanumeric_8_oe_1_oe",
"SimpleTextParserTest.testNextAlphanumeric_8_oe_2_oe",
"SimpleTextParserTest.testNextAlphanumeric_9_oe_1_oe",
"SimpleTextParserTest.testNextAlphanumeric_9_oe_2_oe",
"SimpleTextParserTest.testNextAlphanumeric_9_oe_3_oe",

"SimpleTextParserTest.testNextLine_2_oe_1_oe",
"SimpleTextParserTest.testNextLine_2_oe_2_oe",
"SimpleTextParserTest.testNextLine_3_oe_1_oe",
"SimpleTextParserTest.testNextLine_3_oe_2_oe",
"SimpleTextParserTest.testNextLine_4_oe_2_oe",
"SimpleTextParserTest.testNextLine_4_oe_3_oe",
"SimpleTextParserTest.testNextLine_5_oe_1_oe",
"SimpleTextParserTest.testNextLine_5_oe_2_oe",
"SimpleTextParserTest.testNextLine_5_oe_3_oe",
"SimpleTextParserTest.testNextLine_6_oe_1_oe",
"SimpleTextParserTest.testNextLine_6_oe_2_oe",

"SimpleTextParserTest.testNextWithLineContinuation_lenArg_3_oe_1_oe",
"SimpleTextParserTest.testNextWithLineContinuation_lenArg_3_oe_3_oe",
"SimpleTextParserTest.testNextWithLineContinuation_lenArg_4_oe_1_oe",
"SimpleTextParserTest.testNextWithLineContinuation_lenArg_4_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_lenArg_5_oe_1_oe",
"SimpleTextParserTest.testNextWithLineContinuation_lenArg_5_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_lenArg_5_oe_3_oe",
"SimpleTextParserTest.testNextWithLineContinuation_lenArg_6_oe_1_oe",
"SimpleTextParserTest.testNextWithLineContinuation_lenArg_6_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_lenArg_6_oe_3_oe",

"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_10_oe_1_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_10_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_11_oe_1_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_11_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_11_oe_3_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_2_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_3_oe_1_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_3_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_3_oe_3_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_4_oe_1_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_4_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_4_oe_3_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_5_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_5_oe_3_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_6_oe_1_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_6_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_6_oe_3_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_7_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_7_oe_3_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_8_oe_1_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_8_oe_2_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_8_oe_3_oe",
"SimpleTextParserTest.testNextWithLineContinuation_predicateArg_9_oe_2_oe",

"SimpleTextParserTest.testNext_lenArg_3_oe_1_oe",
"SimpleTextParserTest.testNext_lenArg_3_oe_3_oe",
"SimpleTextParserTest.testNext_lenArg_4_oe_1_oe",
"SimpleTextParserTest.testNext_lenArg_4_oe_2_oe",
"SimpleTextParserTest.testNext_lenArg_4_oe_3_oe",
"SimpleTextParserTest.testNext_lenArg_5_oe_1_oe",
"SimpleTextParserTest.testNext_lenArg_5_oe_2_oe",
"SimpleTextParserTest.testNext_lenArg_5_oe_3_oe",
"SimpleTextParserTest.testNext_lenArg_6_oe_1_oe",
"SimpleTextParserTest.testNext_lenArg_6_oe_2_oe",
"SimpleTextParserTest.testNext_lenArg_6_oe_3_oe",

"SimpleTextParserTest.testNext_predicateArg_10_oe_1_oe",
"SimpleTextParserTest.testNext_predicateArg_10_oe_2_oe",
"SimpleTextParserTest.testNext_predicateArg_11_oe_1_oe",
"SimpleTextParserTest.testNext_predicateArg_11_oe_2_oe",
"SimpleTextParserTest.testNext_predicateArg_11_oe_3_oe",
"SimpleTextParserTest.testNext_predicateArg_3_oe_1_oe",
"SimpleTextParserTest.testNext_predicateArg_3_oe_3_oe",
"SimpleTextParserTest.testNext_predicateArg_4_oe_1_oe",
"SimpleTextParserTest.testNext_predicateArg_4_oe_3_oe",
"SimpleTextParserTest.testNext_predicateArg_5_oe_2_oe",
"SimpleTextParserTest.testNext_predicateArg_5_oe_3_oe",
"SimpleTextParserTest.testNext_predicateArg_6_oe_1_oe",
"SimpleTextParserTest.testNext_predicateArg_6_oe_2_oe",
"SimpleTextParserTest.testNext_predicateArg_6_oe_3_oe",
"SimpleTextParserTest.testNext_predicateArg_7_oe_2_oe",
"SimpleTextParserTest.testNext_predicateArg_7_oe_3_oe",
"SimpleTextParserTest.testNext_predicateArg_8_oe_1_oe",
"SimpleTextParserTest.testNext_predicateArg_8_oe_2_oe",
"SimpleTextParserTest.testNext_predicateArg_8_oe_3_oe",
"SimpleTextParserTest.testNext_predicateArg_9_oe_2_oe",

"SimpleTextParserTest.testUnexpectedToken_1_oe",
"SimpleTextParserTest.testUnexpectedToken_2_oe",
"SimpleTextParserTest.testUnexpectedToken_3_oe",
"SimpleTextParserTest.testUnexpectedToken_4_oe",
"SimpleTextParserTest.testUnexpectedToken_5_oe",
"SimpleTextParserTest.testUnexpectedToken_causeArg_1_oe",
"SimpleTextParserTest.testUnexpectedToken_ioError_1_oe",
"SimpleTextParserTest.testUnexpectedToken_ioError_2_oe",
"SimpleTextParserTest.testUnexpectedToken_ioError_3_oe",

"CharReadBufferTest.testReadPeek_string_5_oe",
"CharReadBufferTest.testReadPeek_string_6_oe",
"CharReadBufferTest.testReadPeek_string_7_oe",
"CharReadBufferTest.testReadPeek_string_8_oe",
"CharReadBufferTest.testSkip_10_oe",
"CharReadBufferTest.testSkip_11_oe",
"CharReadBufferTest.testSkip_12_oe",
"CharReadBufferTest.testSkip_4_oe",
"CharReadBufferTest.testSkip_6_oe",
"CharReadBufferTest.testSkip_8_oe",
"CharReadBufferTest.testSkip_9_oe",

"GeometryIOUtilsTest.testTryApplyCloseable_functionThrows_2_oe",
"GeometryIOUtilsTest.testTryApplyCloseable_functionThrows_inputCloseThrows_4_oe",

"SimpleTextParserTest.testBasicTokenMethods_11_oe_1_oe",
"SimpleTextParserTest.testBasicTokenMethods_11_oe_2_oe",
"SimpleTextParserTest.testBasicTokenMethods_12_oe",
"SimpleTextParserTest.testBasicTokenMethods_13_oe_1_oe",
"SimpleTextParserTest.testBasicTokenMethods_13_oe_2_oe",
"SimpleTextParserTest.testBasicTokenMethods_13_oe_3_oe",
"SimpleTextParserTest.testBasicTokenMethods_14_oe",
"SimpleTextParserTest.testBasicTokenMethods_15_oe_1_oe",
"SimpleTextParserTest.testBasicTokenMethods_15_oe_2_oe",
"SimpleTextParserTest.testBasicTokenMethods_15_oe_3_oe",
"SimpleTextParserTest.testBasicTokenMethods_4_oe",
"SimpleTextParserTest.testBasicTokenMethods_5_oe_1_oe",
"SimpleTextParserTest.testBasicTokenMethods_5_oe_3_oe",
"SimpleTextParserTest.testBasicTokenMethods_6_oe",
"SimpleTextParserTest.testBasicTokenMethods_7_oe_1_oe",
"SimpleTextParserTest.testBasicTokenMethods_7_oe_3_oe",
"SimpleTextParserTest.testBasicTokenMethods_8_oe",
"SimpleTextParserTest.testBasicTokenMethods_9_oe_2_oe",

"SimpleTextParserTest.testCharacterPosition_10_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_11_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_11_oe_2_oe",
"SimpleTextParserTest.testCharacterPosition_12_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_13_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_14_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_15_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_15_oe_2_oe",
"SimpleTextParserTest.testCharacterPosition_16_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_17_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_17_oe_2_oe",
"SimpleTextParserTest.testCharacterPosition_18_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_19_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_20_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_21_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_21_oe_2_oe",
"SimpleTextParserTest.testCharacterPosition_22_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_3_oe_2_oe",
"SimpleTextParserTest.testCharacterPosition_4_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_5_oe_2_oe",
"SimpleTextParserTest.testCharacterPosition_6_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_7_oe_2_oe",
"SimpleTextParserTest.testCharacterPosition_8_oe_1_oe",
"SimpleTextParserTest.testCharacterPosition_9_oe_1_oe",

"SimpleTextParserTest.testCharacterSequence_2_oe_1_oe",
"SimpleTextParserTest.testCharacterSequence_2_oe_2_oe",
"SimpleTextParserTest.testCharacterSequence_2_oe_5_oe",
"SimpleTextParserTest.testCharacterSequence_2_oe_6_oe",
"SimpleTextParserTest.testCharacterSequence_2_oe_7_oe",
"SimpleTextParserTest.testCharacterSequence_2_oe_8_oe",

"SimpleTextParserTest.testDiscardLineWhitespace_10_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_11_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_11_oe_2_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_12_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_13_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_13_oe_2_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_14_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_15_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_15_oe_2_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_16_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_3_oe_2_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_4_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_5_oe_2_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_6_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_7_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_8_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_9_oe_1_oe",
"SimpleTextParserTest.testDiscardLineWhitespace_9_oe_2_oe",

"SimpleTextParserTest.testDiscardNewLineSequence_10_oe_1_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_11_oe_1_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_11_oe_2_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_12_oe_1_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_3_oe_1_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_4_oe_1_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_5_oe_1_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_6_oe_1_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_7_oe_1_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_7_oe_2_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_8_oe_1_oe",
"SimpleTextParserTest.testDiscardNewLineSequence_9_oe_1_oe",

"SimpleTextParserTest.testDiscardWhitespace_3_oe_1_oe",
"SimpleTextParserTest.testDiscardWhitespace_3_oe_2_oe",
"SimpleTextParserTest.testDiscardWhitespace_4_oe_1_oe",
"SimpleTextParserTest.testDiscardWhitespace_5_oe_1_oe",
"SimpleTextParserTest.testDiscardWhitespace_5_oe_2_oe",
"SimpleTextParserTest.testDiscardWhitespace_6_oe_1_oe",
"SimpleTextParserTest.testDiscardWhitespace_7_oe_1_oe",
"SimpleTextParserTest.testDiscardWhitespace_7_oe_2_oe",
"SimpleTextParserTest.testDiscardWhitespace_8_oe_1_oe",

"SimpleTextParserTest.testHasMoreCharactersOnLine_10_oe_1_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_12_oe_1_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_13_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_14_oe_1_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_16_oe_1_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_17_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_18_oe_1_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_19_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_20_oe_1_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_21_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_22_oe_1_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_4_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_7_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_8_oe_1_oe",
"SimpleTextParserTest.testHasMoreCharactersOnLine_9_oe",
"SimpleTextParserTest.testHasMoreCharacters_4_oe",

"SimpleTextParserTest.testNextAlphanumeric_2_oe_1_oe",
"SimpleTextParserTest.testNextAlphanumeric_3_oe_1_oe",
"SimpleTextParserTest.testNextAlphanumeric_3_oe_3_oe",
"SimpleTextParserTest.testNextAlphanumeric_4_oe_1_oe",
"SimpleTextParserTest.testNextAlphanumeric_5_oe_1_oe",
"AbstractBoundaryReadHandler3DTest.testFacetIterator_4_oe",
"AbstractBoundaryReadHandler3DTest.testFacetIterator_5_oe",
"AbstractBoundaryReadHandler3DTest.testFacetIterator_6_oe",
"PolygonObjParserTest.testNextKeyword_1_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_2_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_3_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_4_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_5_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_6_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_7_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_8_oe_1_oe",
"PolygonObjParserTest.testNextKeyword_polygonKeywordsOnly_valid_10_oe_1_oe",
"PolygonObjParserTest.testNextKeyword_polygonKeywordsOnly_valid_1_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_polygonKeywordsOnly_valid_2_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_polygonKeywordsOnly_valid_3_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_polygonKeywordsOnly_valid_4_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_polygonKeywordsOnly_valid_5_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_polygonKeywordsOnly_valid_6_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_polygonKeywordsOnly_valid_7_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_polygonKeywordsOnly_valid_8_oe_2_oe",
"PolygonObjParserTest.testNextKeyword_polygonKeywordsOnly_valid_9_oe_2_oe",
"PolygonObjParserTest.testParse_10_oe",
"PolygonObjParserTest.testParse_11_oe_2_oe",
"PolygonObjParserTest.testParse_13_oe_2_oe",
"PolygonObjParserTest.testParse_15_oe_2_oe",
"PolygonObjParserTest.testParse_17_oe_2_oe",
"PolygonObjParserTest.testParse_19_oe_2_oe",
"PolygonObjParserTest.testParse_1_oe_2_oe",
"PolygonObjParserTest.testParse_21_oe_2_oe",
"PolygonObjParserTest.testParse_23_oe_2_oe",
"PolygonObjParserTest.testParse_25_oe_2_oe",
"PolygonObjParserTest.testParse_27_oe_2_oe",
"PolygonObjParserTest.testParse_29_oe_2_oe",
"PolygonObjParserTest.testParse_2_oe",
"PolygonObjParserTest.testParse_31_oe",
"PolygonObjParserTest.testParse_32_oe",
"PolygonObjParserTest.testParse_33_oe",
"PolygonObjParserTest.testParse_3_oe_2_oe",
"PolygonObjParserTest.testParse_4_oe",
"PolygonObjParserTest.testParse_5_oe_2_oe",
"PolygonObjParserTest.testParse_6_oe",
"PolygonObjParserTest.testParse_7_oe_2_oe",
"PolygonObjParserTest.testParse_8_oe",
"PolygonObjParserTest.testParse_9_oe_2_oe",
"PolygonObjParserTest.testReadDataLine_2_oe",
"PolygonObjParserTest.testReadDataLine_3_oe",
"PolygonObjParserTest.testReadDataLine_4_oe",
"PolygonObjParserTest.testReadDoubles_2_oe",
"StlBoundaryReadHandler3DTest.testRead_closesInputOnReaderCreationFailure_2_oe",
"TextBoundaryWriteHandler3DTest.testWriteBoundarySource_2_oe",
"TextBoundaryWriteHandler3DTest.testWriteFacets_2_oe",
"TextBoundaryWriteHandler3DTest.testWriteFacets_usesOutputCharset_2_oe",
"TextFacetDefinitionWriterTest.testWriteBoundarySource_1_oe",
"TextFacetDefinitionWriterTest.testWriteComment_1_oe",
"TextFacetDefinitionWriterTest.testWriteFacetDefinition_1_oe",
"TextFacetDefinitionWriterTest.testWritePlaneConvexSubset_1_oe",
"TextFacetDefinitionWriterTest.testWritePlaneConvexSubset_convertsToTriangles_1_oe",
"TextFacetDefinitionWriterTest.testWriteVertices_1_oe",
"PolygonObjParserTest.testParse_20_oe",
"PolygonObjParserTest.testParse_22_oe",
"PolygonObjParserTest.testParse_24_oe",

"TextStlFacetDefinitionReaderTest.testParseErrors_1_oe_*",

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
