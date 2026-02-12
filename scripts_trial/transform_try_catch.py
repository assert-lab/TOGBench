# # python3 scripts_trial/transform_try_catch.py --project commons-pool2
# # !/usr/bin/env python3
# import argparse
# import csv
# from pathlib import Path
# from typing import Optional, Tuple, List

# from tree_sitter import Language, Parser


# ID_COL = "id"
# CODE_COL = "test_prefix"
# TS_LIB = "build/my-languages.so"


# JAVA = Language(TS_LIB, "java")
# parser = Parser()
# parser.set_language(JAVA)


# def node_text(src: bytes, node) -> str:
#     return src[node.start_byte:node.end_byte].decode("utf-8")


# def find_all(node, type_name: str):
#     if node.type == type_name:
#         yield node
#     for ch in node.children:
#         yield from find_all(ch, type_name)


# def first_child_of_type(node, type_name: str):
#     for ch in node.children:
#         if ch.type == type_name:
#             return ch
#     return None


# def has_assertthrows(code: str) -> bool:
#     return "assertThrows" in str(code)


# def parse_assertthrows_call(src: bytes, mi_node) -> Optional[Tuple[str, str, Optional[str]]]:
#     arg_list = first_child_of_type(mi_node, "argument_list")
#     if not arg_list:
#         return None

#     args = [ch for ch in arg_list.children if ch.type not in ["(", ")", ","]]
#     if len(args) < 2:
#         return None

#     exc_arg = args[0]
#     lam_arg = args[1]
#     msg_arg = args[2] if len(args) >= 3 else None

#     exc_text = node_text(src, exc_arg).strip()
#     exc_type = exc_text.replace(".class", "").strip()

#     if lam_arg.type != "lambda_expression":
#         return None

#     body_node = None
#     for ch in reversed(lam_arg.children):
#         if ch.is_named:
#             body_node = ch
#             break
#     if body_node is None:
#         return None

#     body_text = node_text(src, body_node).strip()
#     msg_text = node_text(src, msg_arg).strip() if msg_arg is not None else None

#     return exc_type, body_text, msg_text


# def build_trycatch(exc_type: str, lam_body: str, msg_text: Optional[str], indent: str) -> str:
#     statements: List[str] = []

#     if lam_body.startswith("{") and lam_body.endswith("}"):
#         inner = lam_body[1:-1].strip("\n")
#         if inner.strip():
#             for line in inner.splitlines():
#                 statements.append(line.rstrip())
#     else:
#         stmt = lam_body
#         if not stmt.endswith(";"):
#             stmt += ";"
#         statements.append(stmt)

#     fail_msg = exc_type
#     if msg_text:
#         m = msg_text.strip()
#         if (m.startswith('"') and m.endswith('"')) or (m.startswith('""') and m.endswith('""')):
#             m = m.strip('"')
#         fail_msg = f"{exc_type}: {m}"

#     fail_msg_java = fail_msg.replace("\\", "\\\\").replace('"', '\\"')

#     out = []
#     out.append(f"{indent}try {{")
#     for s in statements:
#         out.append(f"{indent}    {s}")
#     out.append(f'{indent}    fail("{fail_msg_java}");')
#     out.append(f"{indent}}} catch ({exc_type} e) {{")
#     out.append(f"{indent}}}")
#     return "\n".join(out)


# def transform_code(code: str) -> Tuple[str, bool]:
#     if not has_assertthrows(code):
#         return code, False

#     src = code.encode("utf-8")
#     tree = parser.parse(src)
#     root = tree.root_node

#     edits = []

#     for mi in find_all(root, "method_invocation"):
#         mi_text = node_text(src, mi)
#         if "assertThrows" not in mi_text:
#             continue

#         stmt = mi
#         while stmt is not None and stmt.type != "expression_statement":
#             stmt = stmt.parent
#         if stmt is None:
#             continue

#         parsed = parse_assertthrows_call(src, mi)
#         if not parsed:
#             continue

#         exc_type, lam_body, msg_text = parsed

#         stmt_text = node_text(src, stmt)
#         leading = ""
#         for ch in stmt_text:
#             if ch in [" ", "\t"]:
#                 leading += ch
#             else:
#                 break

#         replacement = build_trycatch(exc_type, lam_body, msg_text, leading)
#         edits.append((stmt.start_byte, stmt.end_byte, replacement))

#     if not edits:
#         return code, False

#     edits.sort(key=lambda x: x[0], reverse=True)
#     new_src = src
#     for start, end, rep in edits:
#         new_src = new_src[:start] + rep.encode("utf-8") + new_src[end:]

#     return new_src.decode("utf-8"), True


# def write_csv(path: Path, fieldnames: List[str], data: List[dict]):
#     with path.open("w", newline="", encoding="utf-8") as f:
#         w = csv.DictWriter(f, fieldnames=fieldnames)
#         w.writeheader()
#         w.writerows(data)


# def main():
#     ap = argparse.ArgumentParser()
#     ap.add_argument("--project", required=True, help="project folder name under projects_decomposed (e.g., commons-pool2)")
#     ap.add_argument("--repo", default=".", help="OE25-DEV repo root (default: current directory)")
#     args = ap.parse_args()

#     repo = Path(args.repo).resolve()
#     dataset_dir = repo / "projects_decomposed" / args.project / "dataset"

#     inputs_csv = dataset_dir / "inputs.csv"
#     if not inputs_csv.exists():
#         raise SystemExit(f"missing_inputs_csv={inputs_csv}")

#     out_assertthrows_original = dataset_dir / "inputs_assertthrows_original.csv"
#     out_trycatch_only = dataset_dir / "inputs_trycatch_only.csv"
#     out_full_trycatch = dataset_dir / "inputs_trycatch_full.csv"

#     with inputs_csv.open(newline="", encoding="utf-8") as f:
#         reader = csv.DictReader(f)
#         fieldnames = reader.fieldnames
#         if not fieldnames:
#             raise SystemExit("missing_header=1")
#         if ID_COL not in fieldnames:
#             raise SystemExit(f"missing_id_col={ID_COL}")
#         if CODE_COL not in fieldnames:
#             raise SystemExit(f"missing_code_col={CODE_COL}")

#         rows = list(reader)

#     assert_rows_original = [r for r in rows if has_assertthrows(r[CODE_COL])]

#     trycatch_rows = []
#     full_rows = []
#     changed_count = 0

#     for r in rows:
#         code = r[CODE_COL]
#         new_code, changed = transform_code(code)

#         if has_assertthrows(code):
#             r_try = dict(r)
#             r_try[CODE_COL] = new_code
#             trycatch_rows.append(r_try)

#         r_full = dict(r)
#         if changed:
#             r_full[CODE_COL] = new_code
#             changed_count += 1
#         full_rows.append(r_full)

#     write_csv(out_assertthrows_original, fieldnames, assert_rows_original)
#     write_csv(out_trycatch_only, fieldnames, trycatch_rows)
#     write_csv(out_full_trycatch, fieldnames, full_rows)

#     print(f"total_rows={len(rows)}")
#     print(f"assertthrows_rows={len(assert_rows_original)}")
#     print(f"changed_rows_in_full={changed_count}")
#     print(f"wrote={out_assertthrows_original}")
#     print(f"wrote={out_trycatch_only}")
#     print(f"wrote={out_full_trycatch}")


# if __name__ == "__main__":
#     main()


# python3 scripts_trial/transform_try_catch.py --project commons-pool2
#!/usr/bin/env python3
import argparse
import csv
from pathlib import Path
from typing import Optional, Tuple, List

from tree_sitter import Language, Parser


ID_COL = "id"
CODE_COL = "test_prefix"
TS_LIB = "build/my-languages.so"


JAVA = Language(TS_LIB, "java")
parser = Parser()
parser.set_language(JAVA)


def node_text(src: bytes, node) -> str:
    return src[node.start_byte:node.end_byte].decode("utf-8")


def find_all(node, type_name: str):
    if node.type == type_name:
        yield node
    for ch in node.children:
        yield from find_all(ch, type_name)


def first_child_of_type(node, type_name: str):
    for ch in node.children:
        if ch.type == type_name:
            return ch
    return None


def has_assertthrows(code: str) -> bool:
    return "assertThrows" in str(code)


def method_needs_throws_exception(code: str) -> bool:
    s = str(code)
    if "assertThrows" not in s and "fail(" not in s:
        return False
    if "throws Exception" in s:
        return False
    return "public void" in s


def add_throws_exception_to_method_signature(code: str) -> Tuple[str, bool]:
    if not method_needs_throws_exception(code):
        return code, False

    src = code.encode("utf-8")
    tree = parser.parse(src)
    root = tree.root_node

    target = None
    for m in find_all(root, "method_declaration"):
        target = m
        break
    if target is None:
        return code, False

    has_throws = False
    for ch in target.children:
        if ch.type == "throws":
            has_throws = True
            break
    if has_throws:
        return code, False

    fp = first_child_of_type(target, "formal_parameters")
    if fp is None:
        return code, False

    insert_at = fp.end_byte
    new_src = src[:insert_at] + b" throws Exception" + src[insert_at:]
    return new_src.decode("utf-8"), True


def parse_assertthrows_call(src: bytes, mi_node) -> Optional[Tuple[str, str, Optional[str]]]:
    arg_list = first_child_of_type(mi_node, "argument_list")
    if not arg_list:
        return None

    args = [ch for ch in arg_list.children if ch.type not in ["(", ")", ","]]
    if len(args) < 2:
        return None

    exc_arg = args[0]
    lam_arg = args[1]
    msg_arg = args[2] if len(args) >= 3 else None

    exc_text = node_text(src, exc_arg).strip()
    exc_type = exc_text.replace(".class", "").strip()

    if lam_arg.type != "lambda_expression":
        return None

    body_node = None
    for ch in reversed(lam_arg.children):
        if ch.is_named:
            body_node = ch
            break
    if body_node is None:
        return None

    body_text = node_text(src, body_node).strip()
    msg_text = node_text(src, msg_arg).strip() if msg_arg is not None else None

    return exc_type, body_text, msg_text


def build_trycatch(exc_type: str, lam_body: str, msg_text: Optional[str], indent: str) -> str:
    statements: List[str] = []

    if lam_body.startswith("{") and lam_body.endswith("}"):
        inner = lam_body[1:-1].strip("\n")
        if inner.strip():
            for line in inner.splitlines():
                statements.append(line.rstrip())
    else:
        stmt = lam_body
        if not stmt.endswith(";"):
            stmt += ";"
        statements.append(stmt)

    fail_msg = exc_type
    if msg_text:
        m = msg_text.strip()
        if (m.startswith('"') and m.endswith('"')) or (m.startswith('""') and m.endswith('""')):
            m = m.strip('"')
        fail_msg = f"{exc_type}: {m}"

    fail_msg_java = fail_msg.replace("\\", "\\\\").replace('"', '\\"')

    out = []
    out.append(f"{indent}try {{")
    for s in statements:
        out.append(f"{indent}    {s}")
    out.append(f'{indent}    org.junit.jupiter.api.Assertions.fail("{fail_msg_java}");')
    out.append(f"{indent}}} catch ({exc_type} e) {{")
    out.append(f"{indent}}}")
    return "\n".join(out)


def transform_code(code: str) -> Tuple[str, bool]:
    if not has_assertthrows(code):
        return code, False

    src = code.encode("utf-8")
    tree = parser.parse(src)
    root = tree.root_node

    edits = []

    for mi in find_all(root, "method_invocation"):
        mi_text = node_text(src, mi)
        if "assertThrows" not in mi_text:
            continue

        stmt = mi
        while stmt is not None and stmt.type != "expression_statement":
            stmt = stmt.parent
        if stmt is None:
            continue

        parsed = parse_assertthrows_call(src, mi)
        if not parsed:
            continue

        exc_type, lam_body, msg_text = parsed

        stmt_text = node_text(src, stmt)
        leading = ""
        for ch in stmt_text:
            if ch in [" ", "\t"]:
                leading += ch
            else:
                break

        replacement = build_trycatch(exc_type, lam_body, msg_text, leading)
        edits.append((stmt.start_byte, stmt.end_byte, replacement))

    if not edits:
        return code, False

    edits.sort(key=lambda x: x[0], reverse=True)
    new_src = src
    for start, end, rep in edits:
        new_src = new_src[:start] + rep.encode("utf-8") + new_src[end:]

    new_code = new_src.decode("utf-8")
    new_code, sig_changed = add_throws_exception_to_method_signature(new_code)
    return new_code, True or sig_changed


def write_csv(path: Path, fieldnames: List[str], data: List[dict]):
    with path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames)
        w.writeheader()
        w.writerows(data)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--project", required=True, help="project folder name under projects_decomposed (e.g., commons-pool2)")
    ap.add_argument("--repo", default=".", help="OE25-DEV repo root (default: current directory)")
    args = ap.parse_args()

    repo = Path(args.repo).resolve()
    dataset_dir = repo / "projects_decomposed" / args.project / "dataset"

    inputs_csv = dataset_dir / "inputs.csv"
    if not inputs_csv.exists():
        raise SystemExit(f"missing_inputs_csv={inputs_csv}")

    out_assertthrows_original = dataset_dir / "inputs_assertthrows_original.csv"
    out_trycatch_only = dataset_dir / "inputs_trycatch_only.csv"
    out_full_trycatch = dataset_dir / "inputs_trycatch_full.csv"

    with inputs_csv.open(newline="", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        fieldnames = reader.fieldnames
        if not fieldnames:
            raise SystemExit("missing_header=1")
        if ID_COL not in fieldnames:
            raise SystemExit(f"missing_id_col={ID_COL}")
        if CODE_COL not in fieldnames:
            raise SystemExit(f"missing_code_col={CODE_COL}")

        rows = list(reader)

    assert_rows_original = [r for r in rows if has_assertthrows(r[CODE_COL])]

    trycatch_rows = []
    full_rows = []
    changed_count = 0
    sig_changed_count = 0

    for r in rows:
        code = r[CODE_COL]
        new_code, changed = transform_code(code)

        if has_assertthrows(code):
            r_try = dict(r)
            r_try[CODE_COL] = new_code
            trycatch_rows.append(r_try)

        r_full = dict(r)
        if changed:
            r_full[CODE_COL] = new_code
            changed_count += 1
            if "throws Exception" in new_code and "throws Exception" not in code:
                sig_changed_count += 1
        full_rows.append(r_full)

    write_csv(out_assertthrows_original, fieldnames, assert_rows_original)
    write_csv(out_trycatch_only, fieldnames, trycatch_rows)
    write_csv(out_full_trycatch, fieldnames, full_rows)

    print(f"total_rows={len(rows)}")
    print(f"assertthrows_rows={len(assert_rows_original)}")
    print(f"changed_rows_in_full={changed_count}")
    print(f"method_sigs_added_throws_exception={sig_changed_count}")
    print(f"wrote={out_assertthrows_original}")
    print(f"wrote={out_trycatch_only}")
    print(f"wrote={out_full_trycatch}")


if __name__ == "__main__":
    main()
