#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="projects_decomposed/joda-time"
OUT_FILE="scripts/OE25dev_files.txt"

# Clear old output
> "$OUT_FILE"

# Traverse each project
for PROJECT in "$ROOT_DIR"/*; do
  [ -d "$PROJECT" ] || continue

  find "$PROJECT" -type f -name "*_OE25Dev.java" >> "$OUT_FILE"
done

# Remove duplicates (just in case)
sort -u "$OUT_FILE" -o "$OUT_FILE"

echo "Done. Found $(wc -l < "$OUT_FILE") _OE25Dev.java files."
echo "Saved to $OUT_FILE"
