#!/usr/bin/env bash
set -euo pipefail

# --- CONFIG ---
# Point to the joda-time project under projects_decomposed
ROOT_DIR="$(cd "$(dirname "$0")/../.." && pwd)"
JODA_ROOT="$ROOT_DIR/projects_decomposed/joda-time"
DATASET_DIR="$JODA_ROOT/dataset"
INPUTS_CSV="$DATASET_DIR/inputs.csv"
META_CSV="$DATASET_DIR/meta.csv"

echo "ROOT_DIR    = $ROOT_DIR"
echo "JODA_ROOT   = $JODA_ROOT"
echo "DATASET_DIR = $DATASET_DIR"
echo "INPUTS      = $INPUTS_CSV"
echo "META        = $META_CSV"
echo

###############################################################################
# 1) Remove failing tests from inputs.csv and meta.csv
###############################################################################

bad_methods=(
  "test_add_long_int_2_oe"
  "test_add_long_long_2_oe"
  "test_getDifferenceAsLong_long_long_2_oe"
  "test_getDifference_long_long_2_oe"

  "testParseInto_chrono_2_oe"
  "testParseInto_chrono_4_oe"
  "testParseInto_chrono_6_oe"
  "testParseInto_chrono_8_oe"

  "testParseInto_monthDay_feb29_2_oe"
  "testParseInto_monthDay_feb29_OfYear_2_oe"
  "testParseInto_monthDay_feb29_newYork_2_oe"
  "testParseInto_monthDay_feb29_newYork_endOfYear_2_oe"
  "testParseInto_monthDay_feb29_newYork_startOfYear_2_oe"
  "testParseInto_monthDay_feb29_startOfYear_2_oe"
  "testParseInto_monthDay_feb29_tokyo_2_oe"
  "testParseInto_monthDay_feb29_tokyo_endOfYear_2_oe"
  "testParseInto_monthDay_feb29_tokyo_startOfYear_2_oe"
  "testParseInto_monthDay_withDefaultYear_feb29_2_oe"
  "testParseInto_monthDay_withDefaultYear_feb29_newYork_2_oe"
  "testParseInto_monthDay_withDefaultYear_feb29_newYork_endOfYear_2_oe"

  "testParseInto_monthOnly_2_oe"
  "testParseInto_monthOnly_baseEndYear_2_oe"
  "testParseInto_monthOnly_baseStartYear_2_oe"
  "testParseInto_monthOnly_parseEndYear_2_oe"
  "testParseInto_monthOnly_parseStartYear_2_oe"

  "testParseInto_offsetParsed_2_oe"
  "testParseInto_offsetParsed_4_oe"
  "testParseInto_offsetParsed_6_oe"
  "testParseInto_offsetParsed_8_oe"

  "testParseInto_simple_2_oe"
  "testParseInto_simple_precedence_2_oe"

  "testParseInto_zone2_2_oe"
  "testParseInto_zone2_4_oe"
  "testParseInto_zone2_6_oe"

  "testParseInto_zone3_2_oe"
  "testParseInto_zone3_4_oe"
  "testParseInto_zone3_6_oe"

  "testParseInto_zone_2_oe"
  "testParseInto_zone_4_oe"
  "testParseInto_zone_6_oe"

  "testSaveRestoreState_5_oe"
  "testSaveRestoreState_8_oe"
  "testSaveRestoreState_avoidSideEffects_4_oe"
  "testSaveRestoreState_offset_5_oe"
  "testSaveRestoreState_sameStates_10_oe"
  "testSaveRestoreState_sameStates_12_oe"
  "testSaveRestoreState_sameStates_5_oe"
  "testSaveRestoreState_sameStates_8_oe"
  "testSaveRestoreState_text_5_oe"
  "testSaveRestoreState_twoStates_11_oe"
  "testSaveRestoreState_twoStates_13_oe"
  "testSaveRestoreState_twoStates_7_oe"
  "testSaveRestoreState_twoStates_9_oe"
  "testSaveRestoreState_zone_5_oe"

  "testCompileOnBrokenTimeZoneFile_2_2_oe"
  "testCompileOnBrokenTimeZoneFile_2_oe"

  "testWithZoneRetainFields_DateTimeZone_7_oe"
  "testWithZoneRetainFields_DateTimeZone_8_oe"
  "testToDateTime_DateTimeZone_1_oe"
  "testToDateTime_DateTimeZone_6_oe"
  "testWithZoneRetainFields_DateTimeZone_3_oe"
  "testWithZoneRetainFields_DateTimeZone_4_oe"
)

echo "Step 1: Removing failing tests from CSVs..."

cp "$INPUTS_CSV" "${INPUTS_CSV}.bak"
cp "$META_CSV"   "${META_CSV}.bak"

for m in "${bad_methods[@]}"; do
  echo "  - removing rows containing: $m"
  tmp="${INPUTS_CSV}.tmp"
  grep -v "$m" "$INPUTS_CSV" > "$tmp" || true
  mv "$tmp" "$INPUTS_CSV"

  tmp="${META_CSV}.tmp"
  grep -v "$m" "$META_CSV" > "$tmp" || true
  mv "$tmp" "$META_CSV"
done

echo "  - removing any rows containing: testCalendar (all variants)"
for f in "$INPUTS_CSV" "$META_CSV"; do
  tmp="$f.tmp"
  grep -v "testCalendar" "$f" > "$tmp" || true
  mv "$tmp" "$f"
done

echo "Step 1 done."
echo
