#!/usr/bin/env python3
import csv
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed"

def get_java_language():
    try:
        from tree_sitter_languages import get_language
        return get_language("java")
    except Exception:
        return None

def node_text(src_bytes, node):
    return src_bytes[node.start_byte:node.end_byte].decode("utf-8", errors="ignore")

def first_named_child(node, t):
    for c in node.named_children:
        if c.type == t:
            return c
    return None

def package_name(tree, src_bytes):
    root = tree.root_node
    for c in root.named_children:
        if c.type == "package_declaration":
            name = first_named_child(c, "scoped_identifier") or first_named_child(c, "identifier")
            if name:
                return node_text(src_bytes, name).strip()
    return ""

def method_annotations(method_node, src_bytes):
    annotations = []
    for child in method_node.named_children:
        if child.type == "modifiers":
            for mod in child.named_children:
                if mod.type == "annotation":
                    annotations.append(node_text(src_bytes, mod).strip())
    return "\n".join(annotations)


def enclosing_type_chain(node, src_bytes):
    names = []
    cur = node
    while cur is not None:
        if cur.type in ("class_declaration", "interface_declaration", "enum_declaration", "annotation_type_declaration", "record_declaration"):
            ident = first_named_child(cur, "identifier")
            if ident:
                names.append(node_text(src_bytes, ident).strip())
        cur = cur.parent
    names.reverse()
    return names

def preceding_javadoc(node, src_bytes):
    prev = node.prev_named_sibling
    if prev and prev.type == "block_comment":
        txt = node_text(src_bytes, prev).strip()
        if txt.startswith("/**"):
            return txt
    return ""

def method_signature(method_node, src_bytes):
    rt = first_named_child(method_node, "type")
    if rt is None:
        rt = first_named_child(method_node, "void_type")
    name = first_named_child(method_node, "identifier")
    params = first_named_child(method_node, "formal_parameters")
    rt_s = node_text(src_bytes, rt).strip() if rt else ""
    n_s = node_text(src_bytes, name).strip() if name else ""
    p_s = node_text(src_bytes, params).strip() if params else "()"
    if rt_s:
        return f"{rt_s} {n_s}{p_s}".strip()
    return f"{n_s}{p_s}".strip()

def walk_methods(node, out):
    if node.type == "method_declaration":
        out.append(node)
    for c in node.named_children:
        walk_methods(c, out)

def process_project(proj: Path, java_lang):
    from tree_sitter import Parser
    parser = Parser()
    parser.set_language(java_lang)

    rows = []
    src_roots = list(proj.rglob("src/test/java"))
    for src_root in src_roots:
        for f in src_root.rglob("*.java"):
            try:
                src_bytes = f.read_bytes()
            except Exception:
                continue
            tree = parser.parse(src_bytes)
            pkg = package_name(tree, src_bytes)

            methods = []
            walk_methods(tree.root_node, methods)

            for m in methods:
                chain = enclosing_type_chain(m, src_bytes)
                if not chain:
                    continue
                cls = ".".join(chain)
                fq = f"{pkg}.{cls}".strip(".") if pkg else cls
                sig = method_signature(m, src_bytes)
                jd = preceding_javadoc(m, src_bytes)
                ann = method_annotations(m, src_bytes)
                mdef = node_text(src_bytes, m).strip()
                rows.append({
                    "classname": fq,
                    "method": sig,
                    "docstring": jd,
                    "annotations": ann,
                    "method_definition": mdef
                })

    out_path = proj / "all_test_methods.csv"
    with out_path.open("w", encoding="utf-8", newline="") as wf:
        w = csv.DictWriter(wf, fieldnames=["classname", "method", "docstring", "annotations", "method_definition"])
        w.writeheader()
        for r in rows:
            w.writerow(r)

    return len(rows)

def main():
    if not PROJECTS_DIR.exists():
        print("projects_decomposed not found")
        return

    java_lang = get_java_language()
    if java_lang is None:
        print("tree_sitter_languages not available")
        return

    total_projects = 0
    total_methods = 0

    for proj in sorted([p for p in PROJECTS_DIR.iterdir() if p.is_dir()]):
        n = process_project(proj, java_lang)
        print(f"{proj.name} methods={n}")
        total_projects += 1
        total_methods += n

    print(f"projects={total_projects} total_methods={total_methods}")

if __name__ == "__main__":
    main()
