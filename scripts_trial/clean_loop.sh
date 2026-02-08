#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
PROJECTS_DIR="$ROOT/projects_decomposed"
LOG_ROOT="$ROOT/logs"

rm -rf "$LOG_ROOT" 2>/dev/null || true
mkdir -p "$LOG_ROOT"

WORKERS=16
MAX_ITERS=10
TEST_PATTERN="*_OE25Dev#*_oe"

tests_ran() { grep -Eq "Tests run: [1-9]" "$1"; }
compile_failed() { grep -q "COMPILATION ERROR" "$1"; }

bad_files() {
  grep -E '^\[ERROR\].*(_OE25Dev[^ ]*\.java):\[[0-9]+' "$1" \
  | sed -E 's#.*/([^/:]+\.java):.*#\1#' | sort -u || true
}

delete_files() {
  local root="$1"
  local files="$2"
  while read -r f; do
    [ -z "$f" ] && continue
    echo "  deleting $f"
    find "$root" -type f -name "$f" -delete 2>/dev/null || true
  done <<< "$files"
}

run_mvn() {
  local dir="$1"
  local log="$2"
  set +e
  timeout 10m bash -c "cd \"$dir\" && mvn clean --color never test -Dtest=\"$TEST_PATTERN\" > \"$log\""
  local ec=$?
  set -e
  if [ $ec -eq 124 ]; then
    echo "TIMEOUT" >> "$log"
    echo "  TIMEOUT"
  fi
}

run_dir() {
  local pit="$1"
  local dir
  dir="$(dirname "$pit")"
  local name
  name="$(realpath --relative-to="$PROJECTS_DIR" "$dir" 2>/dev/null || echo "$dir")"
  local logdir="$LOG_ROOT/${name//\//__}"
  mkdir -p "$logdir"

  echo "START $name"

  for ((i=1;i<=MAX_ITERS;i++)); do
    echo " ITER $i"
    local log="$logdir/$i.log"
    rm -rf "$dir/target" 2>/dev/null || true
    run_mvn "$dir" "$log"

    if tests_ran "$log"; then
      echo " DONE $name (tests ran)"
      break
    fi

    if ! compile_failed "$log"; then
      echo " STOP $name (no compile error, no tests)"
      break
    fi

    local files
    files="$(bad_files "$log")"

    if [ -z "$files" ]; then
      echo " STOP $name (no bad files)"
      break
    fi

    if [ -d "$dir/src/test/java" ]; then
      delete_files "$dir/src/test/java" "$files"
    else
      delete_files "$dir" "$files"
    fi
  done
}

export ROOT PROJECTS_DIR LOG_ROOT WORKERS MAX_ITERS TEST_PATTERN
export -f tests_ran compile_failed bad_files delete_files run_mvn run_dir

TARGET_PATH="${1:-}"

if [ -n "$TARGET_PATH" ]; then
  if [ -d "$TARGET_PATH" ]; then
    PIT="$TARGET_PATH/pit.sh"
  else
    PIT="$TARGET_PATH"
  fi

  if [ ! -f "$PIT" ]; then
    echo "No pit.sh found at: $PIT"
    exit 1
  fi

  bash -lc "run_dir \"$PIT\""
else
  find "$PROJECTS_DIR" -type f -name "pit.sh" -print0 \
  | xargs -0 -n 1 -P "$WORKERS" bash -lc 'run_dir "$1"' bash
fi

echo "ALL DONE"
