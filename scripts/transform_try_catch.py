
#!/usr/bin/env python3
import argparse
import csv
import re
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


def wrap_in_dummy_class(method_src: str) -> str:
    return "class Dummy {\n" + str(method_src) + "\n}\n"


def unwrap_dummy_class(wrapped_src: str) -> str:
    prefix = "class Dummy {\n"
    suffix = "\n}\n"
    if wrapped_src.startswith(prefix) and wrapped_src.endswith(suffix):
        return wrapped_src[len(prefix):-len(suffix)]
    return wrapped_src


def method_needs_throws_exception(code: str) -> bool:
    s = str(code)
    if "public void" not in s:
        return False
    if "throws " in s:
        return False
    if "assertThrows" not in s and "fail(" not in s:
        return False
    return True


def add_throws_exception_to_method_signature(method_src: str) -> Tuple[str, bool]:
    if not method_needs_throws_exception(method_src):
        return method_src, False

    wrapped = wrap_in_dummy_class(method_src)
    src = wrapped.encode("utf-8")
    tree = parser.parse(src)
    root = tree.root_node

    target = None
    for m in find_all(root, "method_declaration"):
        target = m
        break
    if target is None:
        return method_src, False

    for ch in target.children:
        if ch.type == "throws":
            return method_src, False

    fp = first_child_of_type(target, "formal_parameters")
    if fp is None:
        return method_src, False

    insert_at = fp.end_byte
    new_src = src[:insert_at] + b" throws Exception" + src[insert_at:]
    new_wrapped = new_src.decode("utf-8")
    return unwrap_dummy_class(new_wrapped), True


def callable_as_body(src: bytes, callable_arg) -> Optional[str]:
    if callable_arg is None:
        return None

    if callable_arg.type == "lambda_expression":
        body_node = None
        for ch in reversed(callable_arg.children):
            if ch.is_named:
                body_node = ch
                break
        if body_node is None:
            return None
        return node_text(src, body_node).strip()

    if callable_arg.type == "method_reference":
        return node_text(src, callable_arg).strip()

    if callable_arg.type in {
        "identifier",
        "field_access",
        "scoped_identifier",
        "this",
        "super",
        "parenthesized_expression",
        "method_invocation",
        "object_creation_expression",
    }:
        expr = node_text(src, callable_arg).strip()
        if not expr:
            return None
        return f"{expr}.execute()"

    expr = node_text(src, callable_arg).strip()
    return expr if expr else None


def parse_assertthrows_call(src: bytes, mi_node) -> Optional[Tuple[str, str, Optional[str]]]:
    arg_list = first_child_of_type(mi_node, "argument_list")
    if not arg_list:
        return None

    args = [ch for ch in arg_list.children if ch.type not in ["(", ")", ","]]
    if len(args) < 2:
        return None

    exc_arg = args[0]
    callable_arg = args[1]
    msg_arg = args[2] if len(args) >= 3 else None

    exc_text = node_text(src, exc_arg).strip()
    exc_type = exc_text.replace(".class", "").strip()
    msg_text = node_text(src, msg_arg).strip() if msg_arg is not None else None

    body_text = callable_as_body(src, callable_arg)
    if body_text is None:
        return None

    return exc_type, body_text, msg_text


def strip_throw_line(line: str) -> str:
    return re.sub(r'^(\s*)throw\s+', r'\1', line)


def strip_throw_expr(expr: str) -> str:
    return re.sub(r'^\s*throw\s+', '', expr)


def build_trycatch(exc_type: str, body: str, msg_text: Optional[str], indent: str) -> str:
    statements: List[str] = []
    s = (body or "").strip()

    if "::" in s and not s.startswith("{"):
        left, right = s.split("::", 1)
        statements.append(f"{left.strip()}.{right.strip()}();")

    elif s.startswith("{") and s.endswith("}"):
        inner = s[1:-1].strip("\n")
        if inner.strip():
            for line in inner.splitlines():
                ln = line.rstrip()
                if not ln.strip():
                    continue
                ln = strip_throw_line(ln)
                stripped = ln.strip()
                if stripped and not stripped.endswith(";") and not stripped.endswith("{") and not stripped.endswith("}"):
                    ln += ";"
                statements.append(ln)

    else:
        stmt = strip_throw_expr(s)
        if stmt and not stmt.endswith(";"):
            stmt += ";"
        statements.append(stmt)

    fail_msg = exc_type
    if msg_text:
        m = msg_text.strip()
        if (m.startswith('"') and m.endswith('"')) and len(m) >= 2:
            m = m[1:-1]
        fail_msg = f"{exc_type}: {m}"

    fail_msg_java = fail_msg.replace("\\", "\\\\").replace('"', '\\"')

    out = []
    out.append(f"{indent}try {{")
    for st in statements:
        if st.strip():
            out.append(f"{indent}    {st}")
    out.append(f'{indent}    fail("{fail_msg_java}");')
    out.append(f"{indent}}} catch ({exc_type} e) {{")
    out.append(f"{indent}}}")
    return "\n".join(out)


def find_replacement_statement_node(mi):
    stmt = mi
    while stmt is not None and stmt.type not in ("expression_statement", "local_variable_declaration"):
        stmt = stmt.parent
    return stmt


def transform_code(method_src: str) -> Tuple[str, bool]:
    if not has_assertthrows(method_src):
        return method_src, False

    wrapped = wrap_in_dummy_class(method_src)
    src = wrapped.encode("utf-8")
    tree = parser.parse(src)
    root = tree.root_node

    edits = []

    for mi in find_all(root, "method_invocation"):
        mi_text = node_text(src, mi)
        if "assertThrows" not in mi_text:
            continue

        stmt = find_replacement_statement_node(mi)
        if stmt is None:
            continue

        parsed = parse_assertthrows_call(src, mi)
        if not parsed:
            continue

        exc_type, body_text, msg_text = parsed

        stmt_text = node_text(src, stmt)
        leading = ""
        for ch in stmt_text:
            if ch in [" ", "\t"]:
                leading += ch
            else:
                break

        replacement = build_trycatch(exc_type, body_text, msg_text, leading)
        edits.append((stmt.start_byte, stmt.end_byte, replacement))

    if not edits:
        return method_src, False

    edits.sort(key=lambda x: x[0], reverse=True)
    new_src = src
    for start, end, rep in edits:
        new_src = new_src[:start] + rep.encode("utf-8") + new_src[end:]

    new_method = unwrap_dummy_class(new_src.decode("utf-8"))
    new_method, sig_changed = add_throws_exception_to_method_signature(new_method)
    return new_method, True or sig_changed


def write_csv(path: Path, fieldnames: List[str], data: List[dict]):
    with path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames)
        w.writeheader()
        w.writerows(data)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--project", required=True)
    ap.add_argument("--repo", default=".")
    args = ap.parse_args()

    repo = Path(args.repo).resolve()
    dataset_dir = repo / "projects_decomposed" / args.project / "dataset"
    inputs_csv = dataset_dir / "inputs.csv"

    if not inputs_csv.exists():
        raise SystemExit(f"missing_inputs_csv={inputs_csv}")

    backup_inputs = dataset_dir / "inputs.csv.bak_before_trycatch"
    if not backup_inputs.exists():
        backup_inputs.write_text(
            inputs_csv.read_text(encoding="utf-8", errors="replace"),
            encoding="utf-8",
            errors="replace",
        )

    out_assertthrows_original = dataset_dir / "inputs_assertthrows_original.csv"
    out_trycatch_only = dataset_dir / "inputs_trycatch_only.csv"
    out_full_trycatch = dataset_dir / "inputs.csv"

    with inputs_csv.open(newline="", encoding="utf-8", errors="replace") as f:
        reader = csv.DictReader(f)
        fieldnames = reader.fieldnames
        if not fieldnames:
            raise SystemExit("missing_header=1")
        if ID_COL not in fieldnames:
            raise SystemExit(f"missing_id_col={ID_COL}")
        if CODE_COL not in fieldnames:
            raise SystemExit(f"missing_code_col={CODE_COL}")
        rows = list(reader)

    assert_rows_original = [r for r in rows if has_assertthrows(r.get(CODE_COL, ""))]

    trycatch_rows = []
    full_rows = []
    changed_count = 0
    sig_changed_count = 0

    for r in rows:
        code = r.get(CODE_COL, "")
        new_code, changed = transform_code(code)

        if has_assertthrows(code):
            rr = dict(r)
            rr[CODE_COL] = new_code
            trycatch_rows.append(rr)

        rf = dict(r)
        if changed:
            rf[CODE_COL] = new_code
            changed_count += 1
            if "throws Exception" in new_code and "throws Exception" not in code:
                sig_changed_count += 1
        full_rows.append(rf)

    write_csv(out_assertthrows_original, fieldnames, assert_rows_original)
    write_csv(out_trycatch_only, fieldnames, trycatch_rows)
    write_csv(out_full_trycatch, fieldnames, full_rows)

    print(f"total_rows={len(rows)}")
    print(f"assertthrows_rows={len(assert_rows_original)}")
    print(f"changed_rows_in_full={changed_count}")
    print(f"method_sigs_added_throws_exception={sig_changed_count}")
    print(f"backup_original_inputs={backup_inputs}")
    print(f"wrote={out_assertthrows_original}")
    print(f"wrote={out_trycatch_only}")
    print(f"wrote={out_full_trycatch}")


if __name__ == "__main__":
    main()
