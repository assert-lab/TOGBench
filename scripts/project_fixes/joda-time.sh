
#!/usr/bin/env bash
set -euo pipefail
echo $PWD

ROOT_DIR="$(cd "$(dirname "$0")/../.." && pwd)"
PROJ_ROOT="$ROOT_DIR/projects_decomposed/joda-time"
DATASET_DIR="$PROJ_ROOT/dataset"
META_CSV="$DATASET_DIR/meta.csv"
INPUTS_CSV="$DATASET_DIR/inputs.csv"

cd "$PROJ_ROOT"

cp "$META_CSV" "${META_CSV}.bak"
cp "$INPUTS_CSV" "${INPUTS_CSV}.bak"

python3 - <<'PY'
from pathlib import Path
import csv

meta_path = Path("dataset/meta.csv")
inputs_path = Path("dataset/inputs.csv")

bad_methods = {
  "test_add_long_int_2_oe",
  "test_add_long_long_2_oe",
  "test_getDifferenceAsLong_long_long_2_oe",
  "test_getDifference_long_long_2_oe",
  "testParseInto_chrono_2_oe",
  "testParseInto_chrono_4_oe",
  "testParseInto_chrono_6_oe",
  "testParseInto_chrono_8_oe",
  "testParseInto_monthDay_feb29_2_oe",
  "testParseInto_monthDay_feb29_OfYear_2_oe",
  "testParseInto_monthDay_feb29_newYork_2_oe",
  "testParseInto_monthDay_feb29_newYork_endOfYear_2_oe",
  "testParseInto_monthDay_feb29_newYork_startOfYear_2_oe",
  "testParseInto_monthDay_feb29_startOfYear_2_oe",
  "testParseInto_monthDay_feb29_tokyo_2_oe",
  "testParseInto_monthDay_feb29_tokyo_endOfYear_2_oe",
  "testParseInto_monthDay_feb29_tokyo_startOfYear_2_oe",
  "testParseInto_monthDay_withDefaultYear_feb29_2_oe",
  "testParseInto_monthDay_withDefaultYear_feb29_newYork_2_oe",
  "testParseInto_monthDay_withDefaultYear_feb29_newYork_endOfYear_2_oe",
  "testParseInto_monthOnly_2_oe",
  "testParseInto_monthOnly_baseEndYear_2_oe",
  "testParseInto_monthOnly_baseStartYear_2_oe",
  "testParseInto_monthOnly_parseEndYear_2_oe",
  "testParseInto_monthOnly_parseStartYear_2_oe",
  "testParseInto_offsetParsed_2_oe",
  "testParseInto_offsetParsed_4_oe",
  "testParseInto_offsetParsed_6_oe",
  "testParseInto_offsetParsed_8_oe",
  "testParseInto_simple_2_oe",
  "testParseInto_simple_precedence_2_oe",
  "testParseInto_zone2_2_oe",
  "testParseInto_zone2_4_oe",
  "testParseInto_zone2_6_oe",
  "testParseInto_zone3_2_oe",
  "testParseInto_zone3_4_oe",
  "testParseInto_zone3_6_oe",
  "testParseInto_zone_2_oe",
  "testParseInto_zone_4_oe",
  "testParseInto_zone_6_oe",
  "testSaveRestoreState_5_oe",
  "testSaveRestoreState_8_oe",
  "testSaveRestoreState_avoidSideEffects_4_oe",
  "testSaveRestoreState_offset_5_oe",
  "testSaveRestoreState_sameStates_10_oe",
  "testSaveRestoreState_sameStates_12_oe",
  "testSaveRestoreState_sameStates_5_oe",
  "testSaveRestoreState_sameStates_8_oe",
  "testSaveRestoreState_text_5_oe",
  "testSaveRestoreState_twoStates_11_oe",
  "testSaveRestoreState_twoStates_13_oe",
  "testSaveRestoreState_twoStates_7_oe",
  "testSaveRestoreState_twoStates_9_oe",
  "testSaveRestoreState_zone_5_oe",
  "testCompileOnBrokenTimeZoneFile_2_2_oe",
  "testCompileOnBrokenTimeZoneFile_2_oe",
  "testWithZoneRetainFields_DateTimeZone_7_oe",
  "testWithZoneRetainFields_DateTimeZone_8_oe",
  "testToDateTime_DateTimeZone_1_oe",
  "testToDateTime_DateTimeZone_6_oe",
  "testWithZoneRetainFields_DateTimeZone_3_oe",
  "testWithZoneRetainFields_DateTimeZone_4_oe",
}

fail_list = [
"TestChronology.testToString_11_oe",
"TestChronology.testToString_12_oe",
"TestDateMidnight_Properties.testPropertyGetMonthOfYear_14_oe",
"TestDateTimeZone.testGetName_berlin_english_2_oe",
"TestDateTimeZone.testGetName_berlin_german_3_oe",
"TestDateTime_Properties.testPropertyGetMonthOfYear_18_oe",
"TestInterval_Basics.testAbuts_RInterval_null_2_oe",
"TestInterval_Basics.testAbuts_RInterval_null_5_oe",
"TestInterval_Basics.testContainsNow_2_oe",
"TestInterval_Basics.testContains_RI_null_2_oe",
"TestInterval_Basics.testContains_RInterval_null_2_oe",
"TestInterval_Basics.testGap_RInterval_null_6_oe",
"TestInterval_Basics.testOverlap_RInterval_null_3_oe",
"TestInterval_Basics.testOverlaps_RInterval_null_3_oe",
"TestInterval_Basics.test_useCase_ContainsOverlapAbutGap_zeroDuration_15_oe",
"TestInterval_Basics.test_useCase_ContainsOverlapAbutGap_zeroDuration_16_oe",
"TestInterval_Basics.test_useCase_ContainsOverlapAbutGap_zeroDuration_18_oe",
"TestInterval_Basics.test_useCase_ContainsOverlapAbutGap_zeroDuration_19_oe",
"TestInterval_Basics.test_useCase_ContainsOverlapAbutGap_zeroDuration_24_oe",
"TestMonthDay_Properties.testPropertyGetMonthOfYear_10_oe",
"TestMonthDay_Properties.testPropertyGetMonthOfYear_14_oe",
"TestMutableDateTime_Properties.testPropertyGetMonthOfYear_12_oe",
"TestPartial_Basics.testToString4_2_oe",
"TestPartial_Basics.testToString5_2_oe",
"TestPeriod_Basics.testToString_PeriodFormatter_1_oe",
"TestPeriodFormat.test_getDefault_formatStandard_1_oe",
"TestPeriodFormat.test_wordBased_bg_formatStandard_1_oe",
"TestPeriodFormat.test_wordBased_cs_formatStandard_1_oe",
"TestPeriodFormat.test_wordBased_da_formatMultiple_1_oe",
"TestPeriodFormat.test_wordBased_da_formatSinglular_1_oe",
"TestPeriodFormat.test_wordBased_de_formatStandard_1_oe",
"TestPeriodFormat.test_wordBased_default_1_oe",
"TestPeriodFormat.test_wordBased_en_from_de_1_oe",
"TestPeriodFormat.test_wordBased_en_from_nl_1_oe",
"TestPeriodFormat.test_wordBased_en_from_pl_1_oe",
"TestPeriodFormat.test_wordBased_es_formatStandard_1_oe",
"TestPeriodFormat.test_wordBased_fr_formatStandard_1_oe",
"TestPeriodFormat.test_wordBased_fr_from_de_1_oe",
"TestPeriodFormat.test_wordBased_fr_from_nl_1_oe",
"TestPeriodFormat.test_wordBased_nl_formatStandard_1_oe",
"TestPeriodFormat.test_wordBased_pl_formatStandard_1_oe",
"TestPeriodFormat.test_wordBased_pl_from_fr_1_oe",
"TestPeriodFormat.test_wordBased_pt_formatStandard_1_oe",
"TestPeriodFormat.test_wordBased_ru_formatStandard_1_oe",
"TestPeriodFormatterBuilder.testFormatSeparatorComplex_1_oe",
]

patterns=[]
for full in fail_list:
    cls, name = full.split(".", 1)
    if name.endswith("*"):
        name = name[:-1]
    patterns.append((cls, name))

bad_ids=set()

meta_rows=[]
removed_meta=0
with meta_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    meta_fields = r.fieldnames or []
    for row in r:
        if None in row:
            row.pop(None, None)

        tc = (row.get("test_class") or "")
        tn = (row.get("test_name") or "")

        matched = False

        if "testCalendar" in tc or "testCalendar" in tn:
            matched = True
        elif "testChronology" in tc or "testChronology" in tn:
            matched = True
        elif tn in bad_methods:
            matched = True
        else:
            for cls_pat, name_pref in patterns:
                if tc == cls_pat and tn.startswith(name_pref):
                    matched = True
                    break

        if matched:
            bad_ids.add(row.get("id"))
            removed_meta += 1
        else:
            meta_rows.append(row)

with meta_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=meta_fields, extrasaction="ignore")
    w.writeheader()
    w.writerows(meta_rows)

inputs_rows=[]
removed_inputs=0
with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    in_fields = r.fieldnames or []
    for row in r:
        if None in row:
            row.pop(None, None)

        if row.get("id") in bad_ids:
            removed_inputs += 1
            continue

        tn = (row.get("test_name") or "")
        if tn in bad_methods:
            removed_inputs += 1
            continue

        if "testCalendar" in (row.get("test_class") or "") or "testCalendar" in tn:
            removed_inputs += 1
            continue
        if "testChronology" in (row.get("test_class") or "") or "testChronology" in tn:
            removed_inputs += 1
            continue

        inputs_rows.append(row)

with inputs_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=in_fields, extrasaction="ignore")
    w.writeheader()
    w.writerows(inputs_rows)

print("removed_meta", removed_meta)
print("removed_inputs", removed_inputs)
PY
