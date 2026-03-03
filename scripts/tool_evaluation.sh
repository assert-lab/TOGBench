grep -RIl --include='*_OE25Dev.java' -F '// incorrect assertion' projects_decomposed/JSON-java |
while read f; do
    awk '
    /public void/ {
        if (match($0, /public void[ \t]+[A-Za-z0-9_]+/)) {
            method = substr($0, RSTART, RLENGTH)
            sub(/public void[ \t]+/, "", method)
        }
    }
    /\/\/ incorrect assertion/ {
        if (method != "") print method
    }
    ' "$f"
done | sort -u | wc -l

python3 - << 'EOF'
import csv
with open("projects_decomposed/JSON-java/dataset/inputs_filled.csv", newline="") as f:
    r = csv.reader(f)
    next(r, None)
    print(sum(1 for _ in r))
EOF

