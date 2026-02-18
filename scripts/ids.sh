#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
PD="$ROOT/projects_decomposed"

out_name="ids_checked.csv"

for proj in "$PD"/*; do
  [ -d "$proj" ] || continue
  ds="$proj/dataset"

  meta="$ds/meta.csv"
  failed="$ds/meta_failed.csv"

  [ -f "$meta" ] || continue
  [ -f "$failed" ] || continue

  out="$ds/$out_name"

  tmp1="$(mktemp)"
  tmp2="$(mktemp)"

  awk -F',' 'NR==1{for(i=1;i<=NF;i++) if($i=="id") c=i; next} c{print $c}' "$meta"   | sed 's/\r$//' > "$tmp1"
  awk -F',' 'NR==1{for(i=1;i<=NF;i++) if($i=="id") c=i; next} c{print $c}' "$failed" | sed 's/\r$//' > "$tmp2"

  {
    echo "id"
    cat "$tmp1" "$tmp2" | sort -u
  } > "$out"

  rm -f "$tmp1" "$tmp2"

  echo "Wrote $out ($(($(wc -l < "$out") - 1)) ids)"
done
