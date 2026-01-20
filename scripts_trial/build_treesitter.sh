#!/bin/bash
set -e

python3 -m pip uninstall -y tree-sitter tree-sitter-languages || true

python3 -m pip install --user --break-system-packages \
  "tree-sitter==0.20.4" "tree-sitter-languages==1.10.2"

python3 << 'EOF'
from tree_sitter_languages import get_parser
parser = get_parser("java")
print("Tree-sitter Java parser is ready.")
EOF
