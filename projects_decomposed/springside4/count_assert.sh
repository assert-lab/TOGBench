#!/usr/bin/env bash

ROOT="$(pwd)"
PY="show_unique_assert.py"

find . -type f -name "pit.sh" | while read f; do
  d="$(dirname "$f")"
  cd "$d"
  echo "$d"
  python3 "$ROOT/$PY"
  cd "$ROOT"
done
