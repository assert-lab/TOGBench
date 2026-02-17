# python3 scripts/1_build_dataset.py

import os
from extract_project import extract_project

PROJECTS = [
    p for p in os.listdir("projects_decomposed")
    if os.path.isdir(os.path.join("projects_decomposed", p))
]

def main():
    print()
    for p in PROJECTS:
        print(f"=== Building: {p} ===")
        extract_project(p)
        print()
    print("=== DONE ===")

if __name__ == "__main__":
    main()
