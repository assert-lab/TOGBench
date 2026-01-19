#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
PROJECTS_DIR="$ROOT_DIR/projects_decomposed"

echo "ROOT_DIR = $ROOT_DIR"
echo "PROJECTS_DIR = $PROJECTS_DIR"
echo

for proj in "$PROJECTS_DIR"/*; do
    [ -d "$proj" ] || continue
    FIX_SCRIPT="$proj/fix.sh"
    if [ -x "$FIX_SCRIPT" ]; then
        echo "Running fix.sh in $(basename "$proj")"
        (cd "$proj" && ./fix.sh)
    fi
done

echo
echo "Done"
