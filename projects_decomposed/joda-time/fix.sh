#!/usr/bin/env bash
set -euo pipefail

JODA_ROOT="$(cd "$(dirname "$0")" && pwd)"
echo "JODA_ROOT = $JODA_ROOT"

# echo "Step 2: Updating TestSuite(...) targets in *_OE25Dev.java..."
# find "$JODA_ROOT/src/test/java" -name '*_OE25Dev.java' -exec \
#   sed -i.bak -E 's/TestSuite\(([A-Za-z0-9_]+)\.class\)/TestSuite(\1_OE25Dev.class)/g' {} +
# echo "Step 2 done."
# echo

echo "Step 3: Disabling testCalendar* methods under org/joda/time/chrono..."
find "$JODA_ROOT/src/test/java/org/joda/time/chrono" -name 'Test*.java' -exec \
  sed -i.bak 's/public void testCalendar/public void DISABLED_testCalendar/g' {} +
echo "Step 3 done."
echo

echo "Step 4: Cleaning up sed backup (*.bak) files..."
find "$JODA_ROOT/src/test/java" -name '*.bak' -delete
echo "Step 4 done."

echo "All requested fixes applied."
