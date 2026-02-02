#!/usr/bin/env bash

ROOT="$(pwd)"
PY="count_assert_slc.py"

find . -type f -name "pit.sh" | while read f; do
  d="$(dirname "$f")"
  cd "$d"
  echo "$d"
  python3 "$ROOT/$PY"
  cd "$ROOT"
done
