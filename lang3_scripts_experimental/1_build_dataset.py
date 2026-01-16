import os
from extract_project import extract_project

PROJECTS = [
    p for p in os.listdir("projects_decomposed/commons-lang3-3.12.0-src")
    if os.path.isdir(os.path.join("projects_decomposed/commons-lang3-3.12.0-src", p))
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
