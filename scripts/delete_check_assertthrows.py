# # # python3 scripts/delete_check_assertthrows.py
# # #!/usr/bin/env python3
# # import csv
# # from pathlib import Path

# # ROOT = Path("projects_decomposed")

# # def process_project(project_path: Path):
# #     input_path = project_path / "dataset" / "inputs.csv"
# #     output_path = project_path / "dataset_check" / "assertthrows.csv"

# #     if not input_path.exists():
# #         return

# #     output_path.parent.mkdir(parents=True, exist_ok=True)

# #     total = 0
# #     kept = 0

# #     with input_path.open("r", encoding="utf-8", newline="") as fin, \
# #          output_path.open("w", encoding="utf-8", newline="") as fout:

# #         reader = csv.DictReader(fin)
# #         fieldnames = reader.fieldnames
# #         writer = csv.DictWriter(fout, fieldnames=fieldnames)
# #         writer.writeheader()

# #         for row in reader:
# #             total += 1
# #             test_prefix = row.get("test_prefix", "")
# #             if "assertThrows" in test_prefix:
# #                 writer.writerow(row)
# #                 kept += 1

# #     print(project_path.name, "Total:", total, "assertThrows:", kept)


# # def main():
# #     for project in ROOT.iterdir():
# #         if project.is_dir():
# #             process_project(project)


# # if __name__ == "__main__":
# #     main()


# # ======= transform =========

# #!/usr/bin/env python3
# import csv
# import re
# from pathlib import Path
# from typing import Optional, Tuple, List, Dict, Any

# from tree_sitter import Language, Parser

# ROOT = Path("projects_decomposed")
# TS_LIB = "build/my-languages.so"

# CODE_COL = "test_prefix"
# ID_COL = "id"

# JAVA = Language(TS_LIB, "java")
# parser = Parser()
# parser.set_language(JAVA)

# def node_text(src: bytes, node) -> str:
#     return src[node.start_byte:node.end_byte].decode("utf-8", errors="replace")

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

# def method_invocation_name(src: bytes, mi_node) -> str:
#     name = ""
#     for ch in mi_node.children:
#         if ch.type == "identifier":
#             name = node_text(src, ch)
#     return name or ""

# def has_any_target_substring(code: str) -> bool:
#     s = code or ""
#     return ("assertThrows" in s) or ("assertThrowsWithMessage" in s)

# def wrap_in_dummy(code_src: str) -> str:
#     return "class Dummy {\nvoid dummy() {\n" + (code_src or "") + "\n}\n}\n"

# def unwrap_dummy(wrapped_src: str) -> str:
#     prefix = "class Dummy {\nvoid dummy() {\n"
#     suffix = "\n}\n}\n"
#     if wrapped_src.startswith(prefix) and wrapped_src.endswith(suffix):
#         return wrapped_src[len(prefix):-len(suffix)]
#     return wrapped_src

# def callable_as_body(src: bytes, callable_arg) -> Optional[str]:
#     if callable_arg is None:
#         return None

#     if callable_arg.type == "lambda_expression":
#         body_node = None
#         for ch in reversed(callable_arg.children):
#             if ch.is_named:
#                 body_node = ch
#                 break
#         if body_node is None:
#             return None
#         return node_text(src, body_node).strip()

#     if callable_arg.type == "method_reference":
#         return node_text(src, callable_arg).strip()

#     expr = node_text(src, callable_arg).strip()
#     return expr if expr else None

# def parse_assert_call(src: bytes, mi_node) -> Optional[Tuple[str, str, Optional[str]]]:
#     arg_list = first_child_of_type(mi_node, "argument_list")
#     if not arg_list:
#         return None

#     args = [ch for ch in arg_list.children if ch.type not in ["(", ")", ","]]
#     if len(args) < 2:
#         return None

#     name = method_invocation_name(src, mi_node)

#     exc_arg = None
#     callable_arg = None
#     msg_arg = None

#     if name == "assertThrowsWithMessage":
#         if len(args) < 3:
#             return None
#         callable_arg = args[0]
#         exc_arg = args[1]
#         msg_arg = args[2]
#     else:
#         a0 = node_text(src, args[0]).strip()
#         a1 = node_text(src, args[1]).strip()

#         if a0.endswith(".class"):
#             exc_arg = args[0]
#             callable_arg = args[1]
#             msg_arg = args[2] if len(args) >= 3 else None
#         elif a1.endswith(".class"):
#             callable_arg = args[0]
#             exc_arg = args[1]
#             msg_arg = args[2] if len(args) >= 3 else None
#         else:
#             return None

#     exc_text = node_text(src, exc_arg).strip()
#     exc_type = exc_text.replace(".class", "").strip()

#     body_text = callable_as_body(src, callable_arg)
#     if body_text is None:
#         return None

#     msg_text = node_text(src, msg_arg).strip() if msg_arg is not None else None
#     return exc_type, body_text, msg_text

# def strip_throw_line(line: str) -> str:
#     return re.sub(r'^(\s*)throw\s+', r'\1', line)

# def strip_throw_expr(expr: str) -> str:
#     return re.sub(r'^\s*throw\s+', '', expr)

# def leading_indent(txt: str) -> str:
#     out = ""
#     for ch in txt:
#         if ch in (" ", "\t"):
#             out += ch
#         else:
#             break
#     return out

# def build_trycatch_block(exc_type: str, body: str, msg_text: Optional[str], indent: str, assign_var: Optional[str]) -> str:
#     statements: List[str] = []
#     s = (body or "").strip()

#     if "::" in s and not s.startswith("{"):
#         left, right = s.split("::", 1)
#         statements.append(f"{left.strip()}.{right.strip()}();")
#     elif s.startswith("{") and s.endswith("}"):
#         inner = s[1:-1].strip("\n")
#         if inner.strip():
#             for line in inner.splitlines():
#                 ln = line.rstrip()
#                 if not ln.strip():
#                     continue
#                 ln = strip_throw_line(ln)
#                 stripped = ln.strip()
#                 if stripped and not stripped.endswith(";") and not stripped.endswith("{") and not stripped.endswith("}"):
#                     ln += ";"
#                 statements.append(ln)
#     else:
#         stmt = strip_throw_expr(s)
#         if stmt and not stmt.endswith(";"):
#             stmt += ";"
#         statements.append(stmt)

#     out = []
#     out.append(f"{indent}try {{")
#     for st in statements:
#         if st.strip():
#             out.append(f"{indent}    {st}")

#     if msg_text:
#         out.append(f'{indent}    fail("Expected {exc_type} with message: " + {msg_text});')
#     else:
#         out.append(f'{indent}    fail("Expected {exc_type}");')

#     out.append(f"{indent}}} catch ({exc_type} e) {{")
#     if assign_var:
#         out.append(f"{indent}    {assign_var} = e;")
#     out.append(f"{indent}}}")
#     return "\n".join(out)

# def statement_ancestor(node):
#     cur = node
#     while cur is not None and cur.type not in ("expression_statement", "local_variable_declaration"):
#         cur = cur.parent
#     return cur

# def node_key(n) -> Tuple[int, int, str]:
#     return (n.start_byte, n.end_byte, n.type)

# def replace_range_in_text(original: str, rel_start: int, rel_end: int, replacement: str) -> str:
#     return original[:rel_start] + replacement + original[rel_end:]

# def local_decl_extract(src: bytes, decl_node):
#     named = [ch for ch in decl_node.children if ch.is_named]
#     if not named:
#         return None

#     var_decls = [ch for ch in named if ch.type == "variable_declarator"]
#     if len(var_decls) != 1:
#         return None
#     vd = var_decls[0]

#     var_name = None
#     for ch in vd.children:
#         if ch.type == "identifier":
#             var_name = node_text(src, ch).strip()

#     vdi = first_child_of_type(vd, "variable_initializer")
#     if vdi is None:
#         return None
#     init_named = [ch for ch in vdi.children if ch.is_named]
#     if not init_named:
#         return None
#     init_expr = init_named[0]

#     if not var_name:
#         return None

#     type_node = None
#     for ch in named:
#         if ch.type == "variable_declarator":
#             break
#         type_node = ch
#     if type_node is None:
#         return None

#     decl_type = node_text(src, type_node).strip()
#     if not decl_type:
#         return None

#     return decl_type, var_name, init_expr

# def analyze_row(code_src: str) -> Tuple[Optional[str], Optional[str]]:
#     if not has_any_target_substring(code_src):
#         return None, "no_substring"

#     wrapped = wrap_in_dummy(code_src)
#     src = wrapped.encode("utf-8", errors="replace")
#     tree = parser.parse(src)
#     root = tree.root_node

#     mi_nodes = list(find_all(root, "method_invocation"))
#     if not mi_nodes:
#         return None, "no_method_invocation_nodes"

#     candidates = []
#     for mi in mi_nodes:
#         if "assertThrows" in node_text(src, mi) or "assertThrowsWithMessage" in node_text(src, mi):
#             candidates.append(mi)

#     if not candidates:
#         return None, "substring_but_no_assertthrows_invocation_ast"

#     supported = []
#     unsupported_names = set()
#     for mi in candidates:
#         nm = method_invocation_name(src, mi)
#         if nm in ("assertThrows", "assertThrowsWithMessage"):
#             supported.append(mi)
#         else:
#             if nm:
#                 unsupported_names.add(nm)

#     if not supported:
#         if unsupported_names:
#             return None, "unsupported_method_names:" + "|".join(sorted(list(unsupported_names))[:10])
#         return None, "unsupported_method_names:unknown"

#     parsed_any = False
#     for mi in supported:
#         if parse_assert_call(src, mi):
#             parsed_any = True
#             break
#     if not parsed_any:
#         return None, "supported_name_but_args_unparsed"

#     return wrapped, None

# def transform_wrapped_to_trycatch(wrapped: str) -> Tuple[str, bool, str]:
#     src = wrapped.encode("utf-8", errors="replace")
#     tree = parser.parse(src)
#     root = tree.root_node

#     stmt_to_calls: Dict[Tuple[int, int, str], List[Any]] = {}

#     for mi in find_all(root, "method_invocation"):
#         nm = method_invocation_name(src, mi)
#         if nm not in ("assertThrows", "assertThrowsWithMessage"):
#             continue
#         parsed = parse_assert_call(src, mi)
#         if not parsed:
#             continue
#         stmt = statement_ancestor(mi)
#         if stmt is None:
#             continue
#         stmt_to_calls.setdefault(node_key(stmt), []).append(mi)

#     if not stmt_to_calls:
#         return wrapped, False, "no_transformable_calls_after_parse"

#     edits = []
#     handled = set()

#     for stmt in find_all(root, "expression_statement"):
#         k = node_key(stmt)
#         if k not in stmt_to_calls or k in handled:
#             continue
#         handled.add(k)

#         calls = stmt_to_calls[k]
#         stmt_txt = node_text(src, stmt)
#         ind = leading_indent(stmt_txt)

#         mi = calls[0]
#         parsed = parse_assert_call(src, mi)
#         if not parsed:
#             continue
#         exc_type, body_text, msg_text = parsed

#         if len(calls) == 1 and (mi.start_byte >= stmt.start_byte and mi.end_byte <= stmt.end_byte):
#             rep = build_trycatch_block(exc_type, body_text, msg_text, ind, assign_var=None)
#             edits.append((stmt.start_byte, stmt.end_byte, rep))
#             continue

#         tmp = "__oe_ex"
#         pre = f"{ind}{exc_type} {tmp} = null;\n"
#         tc = build_trycatch_block(exc_type, body_text, msg_text, ind, assign_var=tmp) + "\n"

#         rel_start = mi.start_byte - stmt.start_byte
#         rel_end = mi.end_byte - stmt.start_byte
#         new_stmt = replace_range_in_text(stmt_txt, rel_start, rel_end, tmp)

#         rep = pre + tc + new_stmt
#         edits.append((stmt.start_byte, stmt.end_byte, rep))

#     for decl in find_all(root, "local_variable_declaration"):
#         k = node_key(decl)
#         if k not in stmt_to_calls or k in handled:
#             continue
#         handled.add(k)

#         calls = stmt_to_calls[k]
#         if len(calls) != 1:
#             continue

#         mi = calls[0]
#         parsed = parse_assert_call(src, mi)
#         if not parsed:
#             continue
#         exc_type, body_text, msg_text = parsed

#         extracted = local_decl_extract(src, decl)
#         if not extracted:
#             continue
#         decl_type, var_name, init_expr = extracted

#         if not (mi.start_byte >= init_expr.start_byte and mi.end_byte <= init_expr.end_byte):
#             continue

#         decl_txt = node_text(src, decl)
#         ind = leading_indent(decl_txt)

#         use_type = exc_type if decl_type == "var" else decl_type
#         pre_decl = f"{ind}{use_type} {var_name} = null;\n"
#         tc = build_trycatch_block(exc_type, body_text, msg_text, ind, assign_var=var_name)

#         rep = pre_decl + tc
#         edits.append((decl.start_byte, decl.end_byte, rep))

#     if not edits:
#         return wrapped, False, "no_edit_points_matched"

#     edits.sort(key=lambda x: x[0], reverse=True)
#     new_src = src
#     for start, end, rep in edits:
#         new_src = new_src[:start] + rep.encode("utf-8", errors="replace") + new_src[end:]

#     return new_src.decode("utf-8", errors="replace"), True, ""

# def process_project(p: Path):
#     inputs_csv = p / "dataset" / "inputs.csv"
#     if not inputs_csv.exists():
#         return

#     out_dir = p / "dataset_check"
#     out_dir.mkdir(parents=True, exist_ok=True)
#     out_ok = out_dir / "inputs_trycatch_only_converted.csv"
#     out_bad = out_dir / "inputs_trycatch_not_converted.csv"

#     with inputs_csv.open("r", encoding="utf-8", newline="", errors="replace") as f:
#         r = csv.DictReader(f)
#         fieldnames = r.fieldnames or []
#         if CODE_COL not in fieldnames:
#             print(p.name, "missing_col", CODE_COL)
#             return
#         rows = list(r)

#     total = len(rows)
#     substring_rows = 0
#     converted = 0
#     removed = 0

#     ok_rows = []
#     bad_rows = []

#     for row in rows:
#         code = row.get(CODE_COL, "") or ""
#         rid = row.get(ID_COL, "") or ""

#         if not has_any_target_substring(code):
#             continue

#         substring_rows += 1

#         wrapped, reason = analyze_row(code)
#         if reason is not None:
#             rr = dict(row)
#             rr["reason"] = reason
#             bad_rows.append(rr)
#             removed += 1
#             continue

#         new_wrapped, did, reason2 = transform_wrapped_to_trycatch(wrapped)
#         if not did:
#             rr = dict(row)
#             rr["reason"] = reason2
#             bad_rows.append(rr)
#             removed += 1
#             continue

#         new_code = unwrap_dummy(new_wrapped)
#         rr = dict(row)
#         rr[CODE_COL] = new_code
#         ok_rows.append(rr)
#         converted += 1

#     ok_fieldnames = fieldnames
#     bad_fieldnames = fieldnames + (["reason"] if "reason" not in fieldnames else [])

#     with out_ok.open("w", encoding="utf-8", newline="") as f:
#         w = csv.DictWriter(f, fieldnames=ok_fieldnames, lineterminator="\n")
#         w.writeheader()
#         w.writerows(ok_rows)

#     with out_bad.open("w", encoding="utf-8", newline="") as f:
#         w = csv.DictWriter(f, fieldnames=bad_fieldnames, lineterminator="\n")
#         w.writeheader()
#         w.writerows(bad_rows)

#     print(
#         p.name,
#         "total_rows=", total,
#         "substring_rows=", substring_rows,
#         "converted_rows=", converted,
#         "removed_rows=", removed,
#         "out_ok=", str(out_ok),
#         "out_bad=", str(out_bad)
#     )

# def main():
#     for p in ROOT.iterdir():
#         if p.is_dir():
#             process_project(p)

# if __name__ == "__main__":
#     main()


# ===== replace inputs.csv -> inputs_final.csv =====

#!/usr/bin/env python3
import csv
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
PROJECTS_DIR = ROOT / "projects_decomposed"

def read_csv_rows(path: Path):
    with path.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        r = csv.DictReader(f)
        return (r.fieldnames or []), list(r)

def write_csv_rows(path: Path, fieldnames, rows):
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        w.writeheader()
        for row in rows:
            out = {}
            for k in fieldnames:
                out[k] = row.get(k, "")
            w.writerow(out)

def main():
    total_projects = 0
    total_replaced = 0
    total_matches = 0
    total_missing_in_inputs = 0

    for proj_dir in sorted(PROJECTS_DIR.iterdir()):
        if not proj_dir.is_dir():
            continue

        project = proj_dir.name
        trycatch_path = proj_dir / "dataset_check" / "trycatch_passed.csv"
        inputs_path = proj_dir / "dataset" / "inputs.csv"
        out_path = proj_dir / "dataset" / "inputs_final.csv"

        if not trycatch_path.exists() or not inputs_path.exists():
            continue

        in_fields, in_rows = read_csv_rows(inputs_path)
        tc_fields, tc_rows = read_csv_rows(trycatch_path)

        if not in_fields or not in_rows:
            print(f"{project}: skip (empty inputs.csv)")
            continue
        if not tc_fields or not tc_rows:
            print(f"{project}: skip (empty trycatch_passed.csv)")
            continue

        inputs_by_id = {}
        for i, row in enumerate(in_rows):
            rid = (row.get("id", "") or "").strip()
            if rid and rid not in inputs_by_id:
                inputs_by_id[rid] = i

        tc_by_id = {}
        for row in tc_rows:
            rid = (row.get("id", "") or "").strip()
            if rid and rid not in tc_by_id:
                tc_by_id[rid] = row

        replaced = 0
        matches = 0
        missing_in_inputs = 0

        for rid, tc_row in tc_by_id.items():
            idx = inputs_by_id.get(rid)
            if idx is None:
                missing_in_inputs += 1
                continue
            matches += 1
            old_row = in_rows[idx]
            changed = False
            for k in in_fields:
                if (old_row.get(k, "") or "") != (tc_row.get(k, "") or ""):
                    changed = True
                    break
            in_rows[idx] = tc_row
            if changed:
                replaced += 1

        write_csv_rows(out_path, in_fields, in_rows)

        total_projects += 1
        total_replaced += replaced
        total_matches += matches
        total_missing_in_inputs += missing_in_inputs

        print(
            f"{project}: inputs={len(in_rows)} trycatch={len(tc_by_id)} "
            f"matches={matches} replaced={replaced} missing_in_inputs={missing_in_inputs}"
        )

    print(
        f"\nDONE projects={total_projects} total_matches={total_matches} "
        f"total_replaced={total_replaced} total_missing_in_inputs={total_missing_in_inputs}"
    )

if __name__ == "__main__":
    main()
