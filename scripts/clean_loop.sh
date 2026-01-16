
# #!/usr/bin/env bash
# set -euo pipefail

# # modules
# if [ -f /etc/profile.d/modules.sh ]; then
#   source /etc/profile.d/modules.sh
# fi
# export MODULEPATH

# ROOT="$(cd "$(dirname "$0")/.." && pwd)"
# PROJECTS_DIR="$ROOT/projects_decomposed"
# LOG_ROOT="$ROOT/logs"

# WORKERS=24
# MAX_ITERS=10
# TEST_PATTERN="*_OE25Dev*"

# JAVA11_HOME="$HOME/local/java/jdk-11.0.29+7"

# export ROOT PROJECTS_DIR LOG_ROOT WORKERS MAX_ITERS TEST_PATTERN JAVA11_HOME

# mkdir -p "$LOG_ROOT"

# detect_java_from_pom() {
#   local pom="$1"
#   local v=""
#   v="$(grep -E '<maven\.compiler\.release>|<maven\.compiler\.target>|<maven\.compiler\.source>|<java\.version>' "$pom" \
#       | sed -E 's/.*>([^<]+)<.*/\1/' | head -n1 || true)"

#   case "$v" in
#     "" ) echo 21 ;;
#     1.[5-8]|5|6|7|8 ) echo 8 ;;
#     9|10|11|12|13|14|15|16|17 ) echo "$v" ;;   # keep 11 explicitly
#     18|19|20|21 ) echo 21 ;;
#     * ) echo 17 ;;
#   esac
# }

# load_java() {
#   local v="$1"
#   module unload java >/dev/null 2>&1 || true

#   case "$v" in
#     1.8|8) module load java/8 ;;
#     11)
#       if [ -d "$JAVA11_HOME" ]; then
#         export JAVA_HOME="$JAVA11_HOME"
#         export PATH="$JAVA_HOME/bin:$PATH"
#       else
#         module load java/17
#       fi
#       ;;
#     17) module load java/17 ;;
#     21) module load java/21 ;;
#     *)  module load java/17 ;;
#   esac

#   java -version 2>&1 | head -n1
# }

# tests_ran() { grep -Eq "Tests run: [1-9]" "$1"; }
# compile_failed() { grep -q "COMPILATION ERROR" "$1"; }

# bad_files_from_log() {
#   # supports *_OE25Dev.java and *_OE25Dev_*.java (ONE, hashes, etc.)
#   grep -E '^\[ERROR\].*(_OE25Dev[^ ]*\.java):\[[0-9]+' "$1" \
#     | sed -E 's#.*/([^/:]+\.java):.*#\1#' \
#     | sort -u || true
# }

# delete_in_tree() {
#   local root="$1"
#   local files="$2"
#   local n=0
#   while read -r f; do
#     [ -z "$f" ] && continue
#     find "$root" -type f -name "$f" -delete 2>/dev/null || true
#     n=$((n+1))
#   done <<< "$files"
#   echo "$n"
# }

# run_mvn_single() {
#   local proj_root="$1"
#   local log="$2"
#   ( cd "$proj_root" && mvn test \
#       -Dtest="$TEST_PATTERN" \
#       -DskipITs=true \
#       -DfailIfNoTests=false \
#       -Dsurefire.failIfNoSpecifiedTests=false \
#       -Dmaven.test.failure.ignore=true \
#       -Denforcer.skip=true \
#       -Drat.skip=true \
#       -Dcheckstyle.skip=true \
#       > "$log" 2>&1 || true )
# }

# run_mvn_module() {
#   local proj_root="$1"
#   local module="$2"
#   local log="$3"
#   ( cd "$proj_root" && mvn -pl "$module" -am test \
#       -Dtest="$TEST_PATTERN" \
#       -DskipITs=true \
#       -DfailIfNoTests=false \
#       -Dsurefire.failIfNoSpecifiedTests=false \
#       -Dmaven.test.failure.ignore=true \
#       -Denforcer.skip=true \
#       -Drat.skip=true \
#       -Dcheckstyle.skip=true \
#       > "$log" 2>&1 || true )
# }

# handle_single_project() {
#   local proj="$1"
#   local name
#   name="$(basename "$proj")"
#   local logdir="$LOG_ROOT/$name"
#   mkdir -p "$logdir"

#   local java_ver java_line
#   java_ver="$(detect_java_from_pom "$proj/pom.xml")"
#   java_line="$(load_java "$java_ver")"
#   echo "[JAVA] $name $java_line"

#   for ((i=1;i<=MAX_ITERS;i++)); do
#     local log="$logdir/build_$i.log"
#     rm -rf "$proj/target" >/dev/null 2>&1 || true
#     run_mvn_single "$proj" "$log"

#     if tests_ran "$log"; then
#       echo "[OK]  $name tests-ran"
#       return 0
#     fi

#     if ! compile_failed "$log"; then
#       echo "[STOP] $name non-compile-failure"
#       return 0
#     fi

#     local files cnt
#     files="$(bad_files_from_log "$log")"
#     [ -z "$files" ] && { echo "[STOP] $name compile-no-file"; return 0; }

#     cnt="$(delete_in_tree "$proj" "$files")"
#     echo "[DEL] $name $cnt"
#   done

#   echo "[DONE] $name"
# }

# detect_modules() {
#   local pom="$1"
#   awk '
#     /<modules>/ {f=1; next}
#     /<\/modules>/ {f=0}
#     f && /<module>/ {
#       line=$0
#       gsub(/.*<module>[[:space:]]*/,"",line)
#       gsub(/[[:space:]]*<\/module>.*/,"",line)
#       if (length(line) > 0) print line
#     }' "$pom"
# }

# fallback_modules() {
#   local proj="$1"
#   for d in "$proj"/*; do
#     [ -d "$d" ] && [ -f "$d/pom.xml" ] && basename "$d"
#   done
# }

# handle_multi_project() {
#   local proj="$1"
#   local name
#   name="$(basename "$proj")"
#   local logdir="$LOG_ROOT/$name"
#   mkdir -p "$logdir"

#   local java_ver java_line
#   java_ver="$(detect_java_from_pom "$proj/pom.xml")"
#   java_line="$(load_java "$java_ver")"
#   echo "[JAVA] $name $java_line"

#   local modules
#   modules="$(detect_modules "$proj/pom.xml")"
#   [ -z "$modules" ] && modules="$(fallback_modules "$proj")"
#   [ -z "$modules" ] && { echo "[STOP] $name no-modules"; return 0; }

#   for m in $modules; do
#     echo "[MOD] $name $m"
#     mkdir -p "$logdir/$m"

#     for ((i=1;i<=MAX_ITERS;i++)); do
#       local log="$logdir/$m/build_$i.log"
#       run_mvn_module "$proj" "$m" "$log"

#       if tests_ran "$log"; then
#         echo "[OK]  $name $m tests-ran"
#         break
#       fi

#       if ! compile_failed "$log"; then
#         echo "[STOP] $name $m non-compile-failure"
#         break
#       fi

#       local files cnt
#       files="$(bad_files_from_log "$log")"
#       [ -z "$files" ] && { echo "[STOP] $name $m compile-no-file"; break; }

#       # delete only inside that module's test tree if it exists, else whole project
#       if [ -d "$proj/$m/src/test/java" ]; then
#         cnt="$(delete_in_tree "$proj/$m/src/test/java" "$files")"
#       else
#         cnt="$(delete_in_tree "$proj" "$files")"
#       fi
#       echo "[DEL] $name $m $cnt"
#     done
#   done

#   echo "[DONE] $name"
# }

# process_project() {
#   local proj="$1"
#   local name
#   name="$(basename "$proj")"
#   echo "[START] $name"

#   if [ ! -f "$proj/pom.xml" ]; then
#     echo "[STOP] $name no-pom"
#     return 0
#   fi

#   if grep -q "<modules>" "$proj/pom.xml"; then
#     handle_multi_project "$proj"
#   else
#     handle_single_project "$proj"
#   fi
# }

# export -f detect_java_from_pom load_java tests_ran compile_failed bad_files_from_log delete_in_tree
# export -f run_mvn_single run_mvn_module detect_modules fallback_modules
# export -f handle_single_project handle_multi_project process_project

# find "$PROJECTS_DIR" -maxdepth 1 -mindepth 1 -type d -print \
# | sort \
# | xargs -n 1 -P "$WORKERS" -I {} bash -lc '
#   set -euo pipefail
#   if [ -f /etc/profile.d/modules.sh ]; then source /etc/profile.d/modules.sh; fi
#   export MODULEPATH ROOT PROJECTS_DIR LOG_ROOT WORKERS MAX_ITERS TEST_PATTERN JAVA11_HOME
#   process_project "{}"
# '

# echo "[ALL DONE]"


#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
PROJECTS_DIR="$ROOT/projects_decomposed"
LOG_ROOT="$ROOT/logs"

rm -rf "$LOG_ROOT" 2>/dev/null || true
mkdir -p "$LOG_ROOT"

WORKERS=12
MAX_ITERS=10
TEST_PATTERN="*_OE25Dev#*_oe"

load_java() {
  module unload java >/dev/null 2>&1 || true
  module load java/17 >/dev/null 2>&1 || true
}

tests_ran() { grep -Eq "Tests run: [1-9]" "$1"; }
compile_failed() { grep -q "COMPILATION ERROR" "$1"; }

bad_files() {
  grep -E '^\[ERROR\].*(_OE25Dev[^ ]*\.java):\[[0-9]+' "$1" \
  | sed -E 's#.*/([^/:]+\.java):.*#\1#' | sort -u || true
}

delete_files() {
  local root="$1" ; local files="$2"
  while read -r f; do
    [ -z "$f" ] && continue
    find "$root" -type f -name "$f" -delete 2>/dev/null || true
  done <<< "$files"
}

run_mvn() {
  ( cd "$1" && mvn test \
    -Dtest="$TEST_PATTERN" \
    > "$2" 2>&1 || true )
}

run_dir() {
  local pit="$1"
  local dir; dir="$(dirname "$pit")"
  local name; name="$(realpath --relative-to="$PROJECTS_DIR" "$dir" 2>/dev/null || echo "$dir")"
  local logdir="$LOG_ROOT/${name//\//__}"
  mkdir -p "$logdir"

  for ((i=1;i<=MAX_ITERS;i++)); do
    local log="$logdir/$i.log"
    rm -rf "$dir/target" 2>/dev/null || true
    run_mvn "$dir" "$log"
    tests_ran "$log" && break
    compile_failed "$log" || break
    local files; files="$(bad_files "$log")"
    [ -z "$files" ] && break
    if [ -d "$dir/src/test/java" ]; then
      delete_files "$dir/src/test/java" "$files"
    else
      delete_files "$dir" "$files"
    fi
  done
}

export ROOT PROJECTS_DIR LOG_ROOT WORKERS MAX_ITERS TEST_PATTERN
export -f tests_ran compile_failed bad_files delete_files run_mvn run_dir load_java

if [ -f /etc/profile.d/modules.sh ]; then source /etc/profile.d/modules.sh; fi
load_java >/dev/null 2>&1

find "$PROJECTS_DIR" -type f -name "pit.sh" -print0 \
| xargs -0 -n 1 -P "$WORKERS" bash -lc 'run_dir "$1"' bash

echo "done"
