# python3 scripts/one_line_csv.py
import csv
import re
from pathlib import Path

root = Path("projects_decomposed")

ASSERT_PREFIXES = ("assert", "Assert.", "Assertions.", "fail", "Assume.", "assume")

string_literal = re.compile(r'"([^"\\]|\\.)*"')

def strip_method_wrapper(s: str) -> str:
    lines = s.splitlines()
    core = []
    for line in lines:
        t = line.strip()
        if not t:
            continue
        if t.startswith("//"):
            continue
        if t.startswith("@"):
            continue
        if t == "}" or t == "{":
            continue
        if t.startswith("public ") or t.startswith("private ") or t.startswith("protected "):
            continue
        core.append(line)
    return "\n".join(core)

def count_statements(code: str) -> int:
    if not code.strip():
        return 0
    code = string_literal.sub("", code)
    return code.count(";")

def first_statement_starts_with_assert(code: str) -> bool:
    lines = [l.strip() for l in code.splitlines() if l.strip()]
    if not lines:
        return False
    first = lines[0]
    return first.startswith(ASSERT_PREFIXES)

total_projects = 0
total_rows = 0

for project in sorted(root.iterdir()):
    if not project.is_dir():
        continue

    dataset_dir = project / "dataset"
    inputs_path = dataset_dir / "inputs_final.csv"
    if not inputs_path.exists():
        continue

    matched = []

    with open(inputs_path, newline="", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        fieldnames = reader.fieldnames

        for row in reader:
            tp = row.get("test_prefix", "") or ""
            body = strip_method_wrapper(tp)

            if count_statements(body) == 1 and first_statement_starts_with_assert(body):
                matched.append(row)

    if matched:
        out_path = project / "dataset_check" / "inputs_one_assert_stmt.csv"
        with open(out_path, "w", newline="", encoding="utf-8") as f:
            writer = csv.DictWriter(f, fieldnames=fieldnames)
            writer.writeheader()
            writer.writerows(matched)

        print(project.name, "one-assert-stmt rows:", len(matched))
        total_rows += len(matched)
    else:
        print(project.name, "one-assert-stmt rows: 0")

    total_projects += 1

print("TOTAL projects checked:", total_projects)
print("TOTAL one-assert-stmt rows:", total_rows)

# # # # # ========== extract one line assertion expected value ========

# # # # # import csv
# # # # # import re
# # # # # from pathlib import Path

# # # # # root = Path("projects_decomposed")

# # # # # EXCLUDE = {"assertThrows", "assertDoesNotThrow"}

# # # # # ASSERT_NAMES = {
# # # # #     "assertTrue", "assertFalse",
# # # # #     "assertEquals", "assertNotEquals",
# # # # #     "assertSame", "assertNotSame",
# # # # #     "assertNull", "assertNotNull",
# # # # #     "assertArrayEquals", "assertIterableEquals", "assertLinesMatch"
# # # # # }

# # # # # STR_RE = re.compile(r'^"([^"\\]|\\.)*"$')
# # # # # CHAR_RE = re.compile(r"^'([^'\\]|\\.)'$")
# # # # # BOOL_LIT_RE = re.compile(r"^(true|false)$")
# # # # # LONG_LIT_RE = re.compile(r"^[+-]?\d+[lL]$")
# # # # # INT_LIT_RE = re.compile(r"^[+-]?\d+$")
# # # # # FLOAT_LIT_RE = re.compile(r"^[+-]?(?:\d+\.\d*|\d*\.\d+|\d+)(?:[eE][+-]?\d+)?[fF]$")
# # # # # DOUBLE_LIT_RE = re.compile(r"^[+-]?(?:\d+\.\d*|\d*\.\d+|\d+)(?:[eE][+-]?\d+)?(?:[dD])?$")

# # # # # IDENT_CALL_RE = re.compile(r"\b(?:Assert\.|Assertions\.)?(assert[A-Za-z0-9_]+)\s*\(")

# # # # # def infer_type_from_expr(expr: str, default_type: str = "Object") -> str:
# # # # #     e = expr.strip()
# # # # #     if BOOL_LIT_RE.match(e):
# # # # #         return "boolean"
# # # # #     if LONG_LIT_RE.match(e):
# # # # #         return "long"
# # # # #     if INT_LIT_RE.match(e):
# # # # #         return "int"
# # # # #     if FLOAT_LIT_RE.match(e):
# # # # #         return "float"
# # # # #     if CHAR_RE.match(e):
# # # # #         return "char"
# # # # #     if STR_RE.match(e):
# # # # #         return "String"
# # # # #     if DOUBLE_LIT_RE.match(e) and ("." in e or "e" in e.lower() or e.lower().endswith("d")):
# # # # #         return "double"
# # # # #     return default_type

# # # # # def split_args(arg_str: str):
# # # # #     args = []
# # # # #     cur = []
# # # # #     par = br = sq = ang = 0
# # # # #     in_str = False
# # # # #     in_chr = False
# # # # #     esc = False

# # # # #     for ch in arg_str:
# # # # #         if esc:
# # # # #             cur.append(ch)
# # # # #             esc = False
# # # # #             continue

# # # # #         if ch == "\\" and (in_str or in_chr):
# # # # #             cur.append(ch)
# # # # #             esc = True
# # # # #             continue

# # # # #         if in_str:
# # # # #             cur.append(ch)
# # # # #             if ch == '"':
# # # # #                 in_str = False
# # # # #             continue

# # # # #         if in_chr:
# # # # #             cur.append(ch)
# # # # #             if ch == "'":
# # # # #                 in_chr = False
# # # # #             continue

# # # # #         if ch == '"':
# # # # #             cur.append(ch)
# # # # #             in_str = True
# # # # #             continue

# # # # #         if ch == "'":
# # # # #             cur.append(ch)
# # # # #             in_chr = True
# # # # #             continue

# # # # #         if ch == "(":
# # # # #             par += 1
# # # # #             cur.append(ch)
# # # # #             continue
# # # # #         if ch == ")":
# # # # #             par -= 1
# # # # #             cur.append(ch)
# # # # #             continue
# # # # #         if ch == "{":
# # # # #             br += 1
# # # # #             cur.append(ch)
# # # # #             continue
# # # # #         if ch == "}":
# # # # #             br -= 1
# # # # #             cur.append(ch)
# # # # #             continue
# # # # #         if ch == "[":
# # # # #             sq += 1
# # # # #             cur.append(ch)
# # # # #             continue
# # # # #         if ch == "]":
# # # # #             sq -= 1
# # # # #             cur.append(ch)
# # # # #             continue
# # # # #         if ch == "<":
# # # # #             ang += 1
# # # # #             cur.append(ch)
# # # # #             continue
# # # # #         if ch == ">":
# # # # #             if ang > 0:
# # # # #                 ang -= 1
# # # # #             cur.append(ch)
# # # # #             continue

# # # # #         if ch == "," and par == 0 and br == 0 and sq == 0 and ang == 0:
# # # # #             args.append("".join(cur).strip())
# # # # #             cur = []
# # # # #             continue

# # # # #         cur.append(ch)

# # # # #     tail = "".join(cur).strip()
# # # # #     if tail:
# # # # #         args.append(tail)
# # # # #     return args

# # # # # def find_matching_paren(s: str, lpar_idx: int):
# # # # #     n = len(s)
# # # # #     depth = 0
# # # # #     in_str = False
# # # # #     in_chr = False
# # # # #     esc = False
# # # # #     i = lpar_idx
# # # # #     while i < n:
# # # # #         ch = s[i]
# # # # #         if esc:
# # # # #             esc = False
# # # # #             i += 1
# # # # #             continue
# # # # #         if ch == "\\" and (in_str or in_chr):
# # # # #             esc = True
# # # # #             i += 1
# # # # #             continue
# # # # #         if in_str:
# # # # #             if ch == '"':
# # # # #                 in_str = False
# # # # #             i += 1
# # # # #             continue
# # # # #         if in_chr:
# # # # #             if ch == "'":
# # # # #                 in_chr = False
# # # # #             i += 1
# # # # #             continue
# # # # #         if ch == '"':
# # # # #             in_str = True
# # # # #             i += 1
# # # # #             continue
# # # # #         if ch == "'":
# # # # #             in_chr = True
# # # # #             i += 1
# # # # #             continue

# # # # #         if ch == "(":
# # # # #             depth += 1
# # # # #         elif ch == ")":
# # # # #             depth -= 1
# # # # #             if depth == 0:
# # # # #                 return i
# # # # #         i += 1
# # # # #     return None

# # # # # def is_string_literal(expr: str) -> bool:
# # # # #     return STR_RE.match(expr.strip()) is not None

# # # # # def var_name(idx: int) -> str:
# # # # #     return chr(ord("a") + idx)

# # # # # def choose_extract_index(assert_name: str, args: list[str]):
# # # # #     name = assert_name.split(".")[-1]
# # # # #     if name in ("assertTrue", "assertFalse"):
# # # # #         if len(args) >= 2 and is_string_literal(args[0]):
# # # # #             return 1, "boolean"
# # # # #         return 0, "boolean"

# # # # #     if name in ("assertNull", "assertNotNull"):
# # # # #         if len(args) >= 2 and is_string_literal(args[0]):
# # # # #             return 1, None
# # # # #         return 0, None

# # # # #     if name in ("assertSame", "assertNotSame"):
# # # # #         if len(args) >= 3 and is_string_literal(args[0]):
# # # # #             return 1, "Object"
# # # # #         return 0, "Object"

# # # # #     if name in ("assertEquals", "assertNotEquals", "assertArrayEquals", "assertIterableEquals", "assertLinesMatch"):
# # # # #         if len(args) >= 3 and is_string_literal(args[0]):
# # # # #             return 1, None
# # # # #         return 0, None

# # # # #     return None, None

# # # # # def rewrite_one_assert(call_full: str, assert_name_full: str, args: list[str], var_idx: int):
# # # # #     name = assert_name_full.split(".")[-1]
# # # # #     if name in EXCLUDE:
# # # # #         return None

# # # # #     extract_i, forced_type = choose_extract_index(assert_name_full, args)
# # # # #     if extract_i is None or extract_i >= len(args):
# # # # #         return None

# # # # #     v = var_name(var_idx)
# # # # #     extracted = args[extract_i].strip()

# # # # #     if forced_type == "boolean":
# # # # #         decl_type = "boolean"
# # # # #     elif forced_type is not None:
# # # # #         decl_type = forced_type
# # # # #     else:
# # # # #         decl_type = infer_type_from_expr(extracted, default_type="Object")

# # # # #     new_args = args[:]
# # # # #     new_args[extract_i] = v

# # # # #     decl = f"{decl_type} {v} = {extracted};"
# # # # #     new_call = assert_name_full + "(" + ", ".join(new_args) + ")"

# # # # #     return decl, new_call

# # # # # def transform_test_prefix(tp: str):
# # # # #     s = tp
# # # # #     calls = []
# # # # #     for m in IDENT_CALL_RE.finditer(s):
# # # # #         name = m.group(1)
# # # # #         if name in EXCLUDE:
# # # # #             continue
# # # # #         if name not in ASSERT_NAMES:
# # # # #             continue
# # # # #         lpar = s.find("(", m.end() - 1)
# # # # #         if lpar == -1:
# # # # #             continue
# # # # #         rpar = find_matching_paren(s, lpar)
# # # # #         if rpar is None:
# # # # #             continue
# # # # #         full_start = m.start()
# # # # #         full_end = rpar + 1
# # # # #         args_text = s[lpar + 1:rpar]
# # # # #         args = split_args(args_text)
# # # # #         prefix = s[full_start:m.start(1)]
# # # # #         name_full = prefix + name
# # # # #         calls.append((full_start, full_end, name_full, args, lpar, rpar))

# # # # #     if not calls:
# # # # #         return tp

# # # # #     out = s
# # # # #     var_idx = 0

# # # # #     for full_start, full_end, name_full, args, lpar, rpar in reversed(calls):
# # # # #         call_text = out[full_start:full_end]
# # # # #         res = rewrite_one_assert(call_text, name_full, args, var_idx)
# # # # #         if res is None:
# # # # #             continue
# # # # #         decl, new_call = res
# # # # #         var_idx += 1

# # # # #         line_start = out.rfind("\n", 0, full_start) + 1
# # # # #         indent = ""
# # # # #         j = line_start
# # # # #         while j < len(out) and out[j] in (" ", "\t"):
# # # # #             indent += out[j]
# # # # #             j += 1

# # # # #         new_block = indent + decl + "\n" + out[line_start:full_start] + new_call + out[full_end:full_end]
# # # # #         out = out[:line_start] + new_block + out[full_end:]

# # # # #     return out

# # # # # total_projects = 0
# # # # # total_rows = 0

# # # # # for project_dir in root.iterdir():
# # # # #     if not project_dir.is_dir():
# # # # #         continue

# # # # #     project_dir = project_dir / "dataset"
# # # # #     inputs_path = project_dir / "inputs_one_assert_stmt.csv"
# # # # #     if not inputs_path.exists():
# # # # #         continue

# # # # #     out_path = project_dir / "inputs_multiline.csv"

# # # # #     with open(inputs_path, newline="", encoding="utf-8") as f:
# # # # #         reader = csv.DictReader(f)
# # # # #         fieldnames = reader.fieldnames
# # # # #         rows = []
# # # # #         for row in reader:
# # # # #             tp = row.get("test_prefix", "") or ""
# # # # #             row["test_prefix"] = transform_test_prefix(tp)
# # # # #             rows.append(row)

# # # # #     with open(out_path, "w", newline="", encoding="utf-8") as f:
# # # # #         writer = csv.DictWriter(f, fieldnames=fieldnames)
# # # # #         writer.writeheader()
# # # # #         writer.writerows(rows)

# # # # #     print(project_dir.name, "written:", out_path.name, "rows:", len(rows))
# # # # #     total_projects += 1
# # # # #     total_rows += len(rows)

# # # # # print("TOTAL projects:", total_projects)
# # # # # print("TOTAL rows:", total_rows)



# # # # import csv
# # # # from pathlib import Path

# # # # ROOT = Path("projects_decomposed")

# # # # SKIP_ASSERTS = {"assertThrows", "assertDoesNotThrow"}

# # # # def is_string_literal(x):
# # # #     s = x.strip()
# # # #     return len(s) >= 2 and s[0] == '"' and s[-1] == '"'

# # # # def infer_type(expr):
# # # #     e = expr.strip()

# # # #     if e in ("true", "false"):
# # # #         return "boolean"

# # # #     if is_string_literal(e):
# # # #         return "String"

# # # #     t = e.lstrip("+-")
# # # #     if t.isdigit():
# # # #         return "int"

# # # #     if t.endswith(("l", "L")) and t[:-1].isdigit():
# # # #         return "long"

# # # #     low = e.lower()
# # # #     if any(ch in low for ch in [".", "e"]):
# # # #         if low.endswith("f"):
# # # #             return "float"
# # # #         return "double"

# # # #     return "Object"

# # # # def split_args(s):
# # # #     args = []
# # # #     cur = []

# # # #     par = br = sq = ang = 0
# # # #     in_str = False
# # # #     in_chr = False
# # # #     esc = False

# # # #     for ch in s:
# # # #         if esc:
# # # #             cur.append(ch)
# # # #             esc = False
# # # #             continue

# # # #         if (in_str or in_chr) and ch == "\\":
# # # #             cur.append(ch)
# # # #             esc = True
# # # #             continue

# # # #         if in_str:
# # # #             cur.append(ch)
# # # #             if ch == '"':
# # # #                 in_str = False
# # # #             continue

# # # #         if in_chr:
# # # #             cur.append(ch)
# # # #             if ch == "'":
# # # #                 in_chr = False
# # # #             continue

# # # #         if ch == '"':
# # # #             cur.append(ch)
# # # #             in_str = True
# # # #             continue

# # # #         if ch == "'":
# # # #             cur.append(ch)
# # # #             in_chr = True
# # # #             continue

# # # #         if ch == "(":
# # # #             par += 1
# # # #             cur.append(ch)
# # # #             continue
# # # #         if ch == ")":
# # # #             par -= 1
# # # #             cur.append(ch)
# # # #             continue

# # # #         if ch == "{":
# # # #             br += 1
# # # #             cur.append(ch)
# # # #             continue
# # # #         if ch == "}":
# # # #             br -= 1
# # # #             cur.append(ch)
# # # #             continue

# # # #         if ch == "[":
# # # #             sq += 1
# # # #             cur.append(ch)
# # # #             continue
# # # #         if ch == "]":
# # # #             sq -= 1
# # # #             cur.append(ch)
# # # #             continue

# # # #         if ch == "<":
# # # #             ang += 1
# # # #             cur.append(ch)
# # # #             continue
# # # #         if ch == ">":
# # # #             if ang > 0:
# # # #                 ang -= 1
# # # #             cur.append(ch)
# # # #             continue

# # # #         if ch == "," and par == 0 and br == 0 and sq == 0 and ang == 0:
# # # #             args.append("".join(cur).strip())
# # # #             cur = []
# # # #             continue

# # # #         cur.append(ch)

# # # #     tail = "".join(cur).strip()
# # # #     if tail:
# # # #         args.append(tail)
# # # #     return args

# # # # def find_assert_call(text):
# # # #     candidates = []

# # # #     i = text.find("Assert.assert")
# # # #     if i != -1:
# # # #         candidates.append(i)

# # # #     i = text.find("Assertions.assert")
# # # #     if i != -1:
# # # #         candidates.append(i)

# # # #     i = text.find("assert")
# # # #     if i != -1:
# # # #         candidates.append(i)

# # # #     if not candidates:
# # # #         return None

# # # #     start = min(candidates)

# # # #     j = start
# # # #     while j < len(text) and (text[j].isalnum() or text[j] in "._"):
# # # #         j += 1

# # # #     full_name = text[start:j].strip()
# # # #     name = full_name.split(".")[-1]

# # # #     lpar = text.find("(", j)
# # # #     if lpar == -1:
# # # #         return None

# # # #     depth = 0
# # # #     in_str = False
# # # #     in_chr = False
# # # #     esc = False
# # # #     rpar = None

# # # #     k = lpar
# # # #     while k < len(text):
# # # #         ch = text[k]

# # # #         if esc:
# # # #             esc = False
# # # #             k += 1
# # # #             continue

# # # #         if (in_str or in_chr) and ch == "\\":
# # # #             esc = True
# # # #             k += 1
# # # #             continue

# # # #         if in_str:
# # # #             if ch == '"':
# # # #                 in_str = False
# # # #             k += 1
# # # #             continue

# # # #         if in_chr:
# # # #             if ch == "'":
# # # #                 in_chr = False
# # # #             k += 1
# # # #             continue

# # # #         if ch == '"':
# # # #             in_str = True
# # # #             k += 1
# # # #             continue

# # # #         if ch == "'":
# # # #             in_chr = True
# # # #             k += 1
# # # #             continue

# # # #         if ch == "(":
# # # #             depth += 1
# # # #         elif ch == ")":
# # # #             depth -= 1
# # # #             if depth == 0:
# # # #                 rpar = k
# # # #                 break

# # # #         k += 1

# # # #     if rpar is None:
# # # #         return None

# # # #     args_str = text[lpar + 1:rpar]
# # # #     end = rpar + 1
# # # #     return start, end, full_name, name, args_str

# # # # def get_indent(text, idx):
# # # #     line_start = text.rfind("\n", 0, idx) + 1
# # # #     indent = ""
# # # #     p = line_start
# # # #     while p < len(text) and text[p] in (" ", "\t"):
# # # #         indent += text[p]
# # # #         p += 1
# # # #     return indent, line_start

# # # # def transform(prefix):
# # # #     found = find_assert_call(prefix)
# # # #     if not found:
# # # #         return prefix, False

# # # #     start, end, full_name, name, args_str = found

# # # #     if not name.startswith("assert"):
# # # #         return prefix, False

# # # #     if name in SKIP_ASSERTS:
# # # #         return prefix, True

# # # #     args = split_args(args_str)
# # # #     if not args:
# # # #         return prefix, False

# # # #     var = "a"
# # # #     indent, line_start = get_indent(prefix, start)

# # # #     if name in ("assertTrue", "assertFalse"):
# # # #         if len(args) >= 2 and is_string_literal(args[0]):
# # # #             cond_idx = 1
# # # #             cond_expr = args[cond_idx]
# # # #             decl = f"{indent}boolean {var} = {cond_expr.strip()};"
# # # #             args[cond_idx] = var
# # # #         else:
# # # #             cond_idx = 0
# # # #             cond_expr = args[cond_idx]
# # # #             decl = f"{indent}boolean {var} = {cond_expr.strip()};"
# # # #             args[cond_idx] = var

# # # #         new_call = full_name + "(" + ", ".join(args) + ")"
# # # #         new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_call + prefix[end:]
# # # #         return new_prefix, True

# # # #     extract_idx = 0
# # # #     if len(args) >= 2 and is_string_literal(args[0]):
# # # #         extract_idx = 1

# # # #     expr = args[extract_idx].strip()
# # # #     t = infer_type(expr)
# # # #     decl = f"{indent}{t} {var} = {expr};"
# # # #     args[extract_idx] = var

# # # #     new_call = full_name + "(" + ", ".join(args) + ")"
# # # #     new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_call + prefix[end:]
# # # #     return new_prefix, True

# # # # def main():
# # # #     for proj in ROOT.iterdir():
# # # #         if not proj.is_dir():
# # # #             continue
        
# # # #         pname = proj
# # # #         proj = proj / "dataset"
# # # #         inp = proj / "inputs_one_assert_stmt.csv"
# # # #         if not inp.exists():
# # # #             continue

        
# # # #         out_good = proj / "inputs_multiline.csv"
# # # #         out_skip = proj / "inputs_skipped.csv"

# # # #         good = []
# # # #         skipped = []

# # # #         with open(inp, newline="", encoding="utf-8") as f:
# # # #             r = csv.DictReader(f)
# # # #             fields = r.fieldnames

# # # #             for row in r:
# # # #                 tp = row.get("test_prefix", "") or ""
# # # #                 new, ok = transform(tp)

# # # #                 if ok:
# # # #                     row["test_prefix"] = new
# # # #                     good.append(row)
# # # #                 else:
# # # #                     skipped.append(row)

# # # #         with open(out_good, "w", newline="", encoding="utf-8") as f:
# # # #             w = csv.DictWriter(f, fieldnames=fields)
# # # #             w.writeheader()
# # # #             w.writerows(good)

# # # #         with open(out_skip, "w", newline="", encoding="utf-8") as f:
# # # #             w = csv.DictWriter(f, fieldnames=fields)
# # # #             w.writeheader()
# # # #             w.writerows(skipped)

# # # #         print(pname, "converted_or_kept:", len(good), "unparsed_skipped:", len(skipped))

# # # # if __name__ == "__main__":
# # # #     main()

# # # import csv
# # # from pathlib import Path

# # # ROOT = Path("projects_decomposed")

# # # SKIP_ASSERTS = {"assertThrows", "assertDoesNotThrow"}

# # # def is_string_literal(x):
# # #     s = x.strip()
# # #     return len(s) >= 2 and s[0] == '"' and s[-1] == '"'

# # # def split_args(s):
# # #     args = []
# # #     cur = []

# # #     par = br = sq = ang = 0
# # #     in_str = False
# # #     in_chr = False
# # #     esc = False

# # #     for ch in s:
# # #         if esc:
# # #             cur.append(ch)
# # #             esc = False
# # #             continue

# # #         if (in_str or in_chr) and ch == "\\":
# # #             cur.append(ch)
# # #             esc = True
# # #             continue

# # #         if in_str:
# # #             cur.append(ch)
# # #             if ch == '"':
# # #                 in_str = False
# # #             continue

# # #         if in_chr:
# # #             cur.append(ch)
# # #             if ch == "'":
# # #                 in_chr = False
# # #             continue

# # #         if ch == '"':
# # #             cur.append(ch)
# # #             in_str = True
# # #             continue

# # #         if ch == "'":
# # #             cur.append(ch)
# # #             in_chr = True
# # #             continue

# # #         if ch == "(":
# # #             par += 1
# # #             cur.append(ch)
# # #             continue
# # #         if ch == ")":
# # #             par -= 1
# # #             cur.append(ch)
# # #             continue

# # #         if ch == "{":
# # #             br += 1
# # #             cur.append(ch)
# # #             continue
# # #         if ch == "}":
# # #             br -= 1
# # #             cur.append(ch)
# # #             continue

# # #         if ch == "[":
# # #             sq += 1
# # #             cur.append(ch)
# # #             continue
# # #         if ch == "]":
# # #             sq -= 1
# # #             cur.append(ch)
# # #             continue

# # #         if ch == "<":
# # #             ang += 1
# # #             cur.append(ch)
# # #             continue
# # #         if ch == ">":
# # #             if ang > 0:
# # #                 ang -= 1
# # #             cur.append(ch)
# # #             continue

# # #         if ch == "," and par == 0 and br == 0 and sq == 0 and ang == 0:
# # #             args.append("".join(cur).strip())
# # #             cur = []
# # #             continue

# # #         cur.append(ch)

# # #     tail = "".join(cur).strip()
# # #     if tail:
# # #         args.append(tail)
# # #     return args

# # # def find_assert_call(text):
# # #     candidates = []

# # #     i = text.find("Assert.assert")
# # #     if i != -1:
# # #         candidates.append(i)

# # #     i = text.find("Assertions.assert")
# # #     if i != -1:
# # #         candidates.append(i)

# # #     i = text.find("assert")
# # #     if i != -1:
# # #         candidates.append(i)

# # #     if not candidates:
# # #         return None

# # #     start = min(candidates)

# # #     j = start
# # #     while j < len(text) and (text[j].isalnum() or text[j] in "._"):
# # #         j += 1

# # #     full_name = text[start:j].strip()
# # #     name = full_name.split(".")[-1]

# # #     lpar = text.find("(", j)
# # #     if lpar == -1:
# # #         return None

# # #     depth = 0
# # #     in_str = False
# # #     in_chr = False
# # #     esc = False
# # #     rpar = None

# # #     k = lpar
# # #     while k < len(text):
# # #         ch = text[k]

# # #         if esc:
# # #             esc = False
# # #             k += 1
# # #             continue

# # #         if (in_str or in_chr) and ch == "\\":
# # #             esc = True
# # #             k += 1
# # #             continue

# # #         if in_str:
# # #             if ch == '"':
# # #                 in_str = False
# # #             k += 1
# # #             continue

# # #         if in_chr:
# # #             if ch == "'":
# # #                 in_chr = False
# # #             k += 1
# # #             continue

# # #         if ch == '"':
# # #             in_str = True
# # #             k += 1
# # #             continue

# # #         if ch == "'":
# # #             in_chr = True
# # #             k += 1
# # #             continue

# # #         if ch == "(":
# # #             depth += 1
# # #         elif ch == ")":
# # #             depth -= 1
# # #             if depth == 0:
# # #                 rpar = k
# # #                 break

# # #         k += 1

# # #     if rpar is None:
# # #         return None

# # #     args_str = text[lpar + 1:rpar]
# # #     end = rpar + 1
# # #     return start, end, full_name, name, args_str

# # # def get_indent(text, idx):
# # #     line_start = text.rfind("\n", 0, idx) + 1
# # #     indent = ""
# # #     p = line_start
# # #     while p < len(text) and text[p] in (" ", "\t"):
# # #         indent += text[p]
# # #         p += 1
# # #     return indent, line_start

# # # def transform(prefix):
# # #     found = find_assert_call(prefix)
# # #     if not found:
# # #         return prefix, False

# # #     start, end, full_name, name, args_str = found

# # #     if not name.startswith("assert"):
# # #         return prefix, False

# # #     if name in SKIP_ASSERTS:
# # #         return prefix, True

# # #     args = split_args(args_str)
# # #     if not args:
# # #         return prefix, False

# # #     var = "a"
# # #     indent, line_start = get_indent(prefix, start)

# # #     if name in ("assertTrue", "assertFalse"):
# # #         if len(args) >= 2 and is_string_literal(args[0]):
# # #             cond_idx = 1
# # #         else:
# # #             cond_idx = 0

# # #         cond_expr = args[cond_idx].strip()
# # #         decl = f"{indent}boolean {var} = {cond_expr};"
# # #         args[cond_idx] = var

# # #         new_call = full_name + "(" + ", ".join(args) + ")"
# # #         new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_call + prefix[end:]
# # #         return new_prefix, True

# # #     extract_idx = 1 if len(args) >= 2 and is_string_literal(args[0]) else 0

# # #     expr = args[extract_idx].strip()
# # #     decl = f"{indent}Object {var} = {expr};"
# # #     args[extract_idx] = var

# # #     new_call = full_name + "(" + ", ".join(args) + ")"
# # #     new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_call + prefix[end:]
# # #     return new_prefix, True

# # # def main():
# # #     for proj in ROOT.iterdir():
# # #         if not proj.is_dir():
# # #             continue

# # #         pname = proj
# # #         proj = proj / "dataset"

# # #         inp = proj / "inputs_one_assert_stmt.csv"
# # #         if not inp.exists():
# # #             continue

# # #         out_good = proj / "inputs_multiline.csv"
# # #         out_skip = proj / "inputs_skipped.csv"

# # #         good = []
# # #         skipped = []

# # #         with open(inp, newline="", encoding="utf-8") as f:
# # #             r = csv.DictReader(f)
# # #             fields = r.fieldnames

# # #             for row in r:
# # #                 tp = row.get("test_prefix", "") or ""
# # #                 new, ok = transform(tp)

# # #                 if ok:
# # #                     row["test_prefix"] = new
# # #                     good.append(row)
# # #                 else:
# # #                     skipped.append(row)

# # #         with open(out_good, "w", newline="", encoding="utf-8") as f:
# # #             w = csv.DictWriter(f, fieldnames=fields)
# # #             w.writeheader()
# # #             w.writerows(good)

# # #         with open(out_skip, "w", newline="", encoding="utf-8") as f:
# # #             w = csv.DictWriter(f, fieldnames=fields)
# # #             w.writeheader()
# # #             w.writerows(skipped)

# # #         print(pname, "converted_or_kept:", len(good), "unparsed_skipped:", len(skipped))

# # # if __name__ == "__main__":
# # #     main()

# # import csv
# # from pathlib import Path

# # ROOT = Path("projects_decomposed")

# # SKIP_ASSERTS = {"assertThrows", "assertDoesNotThrow"}

# # def is_string_literal(x):
# #     s = x.strip()
# #     return len(s) >= 2 and s[0] == '"' and s[-1] == '"'

# # def split_args(s):
# #     args = []
# #     cur = []

# #     par = br = sq = ang = 0
# #     in_str = False
# #     in_chr = False
# #     esc = False

# #     i = 0
# #     n = len(s)

# #     while i < n:
# #         ch = s[i]
# #         nxt = s[i + 1] if i + 1 < n else ""
# #         nxt2 = s[i + 2] if i + 2 < n else ""

# #         if esc:
# #             cur.append(ch)
# #             esc = False
# #             i += 1
# #             continue

# #         if (in_str or in_chr) and ch == "\\":
# #             cur.append(ch)
# #             esc = True
# #             i += 1
# #             continue

# #         if in_str:
# #             cur.append(ch)
# #             if ch == '"':
# #                 in_str = False
# #             i += 1
# #             continue

# #         if in_chr:
# #             cur.append(ch)
# #             if ch == "'":
# #                 in_chr = False
# #             i += 1
# #             continue

# #         if ch == '"':
# #             cur.append(ch)
# #             in_str = True
# #             i += 1
# #             continue

# #         if ch == "'":
# #             cur.append(ch)
# #             in_chr = True
# #             i += 1
# #             continue

# #         if ch == "(":
# #             par += 1
# #             cur.append(ch)
# #             i += 1
# #             continue
# #         if ch == ")":
# #             par -= 1
# #             cur.append(ch)
# #             i += 1
# #             continue

# #         if ch == "{":
# #             br += 1
# #             cur.append(ch)
# #             i += 1
# #             continue
# #         if ch == "}":
# #             br -= 1
# #             cur.append(ch)
# #             i += 1
# #             continue

# #         if ch == "[":
# #             sq += 1
# #             cur.append(ch)
# #             i += 1
# #             continue
# #         if ch == "]":
# #             sq -= 1
# #             cur.append(ch)
# #             i += 1
# #             continue

# #         if ch == "<":
# #             if nxt == "<":
# #                 cur.append("<<")
# #                 i += 2
# #                 continue
# #             ang += 1
# #             cur.append("<")
# #             i += 1
# #             continue

# #         if ch == ">":
# #             if nxt == ">" and nxt2 == ">":
# #                 if ang > 0:
# #                     cur.append(">>>")
# #                     ang = max(0, ang - 3)
# #                     i += 3
# #                     continue
# #                 cur.append(">>>")
# #                 i += 3
# #                 continue

# #             if nxt == ">":
# #                 if ang > 0:
# #                     cur.append(">>")
# #                     ang = max(0, ang - 2)
# #                     i += 2
# #                     continue
# #                 cur.append(">>")
# #                 i += 2
# #                 continue

# #             if ang > 0:
# #                 ang -= 1
# #             cur.append(">")
# #             i += 1
# #             continue

# #         if ch == "," and par == 0 and br == 0 and sq == 0 and ang == 0:
# #             args.append("".join(cur).strip())
# #             cur = []
# #             i += 1
# #             continue

# #         cur.append(ch)
# #         i += 1

# #     tail = "".join(cur).strip()
# #     if tail:
# #         args.append(tail)

# #     return args

# # def find_first_call(text, prefixes):
# #     starts = []
# #     for p in prefixes:
# #         i = text.find(p)
# #         if i != -1:
# #             starts.append(i)
# #     if not starts:
# #         return None
# #     return min(starts)

# # def get_indent(text, idx):
# #     line_start = text.rfind("\n", 0, idx) + 1
# #     indent = ""
# #     p = line_start
# #     while p < len(text) and text[p] in (" ", "\t"):
# #         indent += text[p]
# #         p += 1
# #     return indent, line_start

# # def find_matching_rpar(text, lpar):
# #     depth = 0
# #     in_str = False
# #     in_chr = False
# #     esc = False

# #     k = lpar
# #     while k < len(text):
# #         ch = text[k]

# #         if esc:
# #             esc = False
# #             k += 1
# #             continue

# #         if (in_str or in_chr) and ch == "\\":
# #             esc = True
# #             k += 1
# #             continue

# #         if in_str:
# #             if ch == '"':
# #                 in_str = False
# #             k += 1
# #             continue

# #         if in_chr:
# #             if ch == "'":
# #                 in_chr = False
# #             k += 1
# #             continue

# #         if ch == '"':
# #             in_str = True
# #             k += 1
# #             continue

# #         if ch == "'":
# #             in_chr = True
# #             k += 1
# #             continue

# #         if ch == "(":
# #             depth += 1
# #         elif ch == ")":
# #             depth -= 1
# #             if depth == 0:
# #                 return k

# #         k += 1

# #     return None

# # def transform_assertj_assertThat(prefix):
# #     start = prefix.find("assertThat(")
# #     if start == -1:
# #         return prefix, False

# #     lpar = prefix.find("(", start)
# #     if lpar == -1:
# #         return prefix, False

# #     rpar = find_matching_rpar(prefix, lpar)
# #     if rpar is None:
# #         return prefix, False

# #     inside = prefix[lpar + 1:rpar].strip()
# #     chain = prefix[rpar + 1:]  

# #     if not chain.strip().startswith("."):
# #         return prefix, False

# #     var = "a"
# #     indent, line_start = get_indent(prefix, start)

# #     chain_has_bool = (".isTrue()" in chain) or (".isFalse()" in chain)

# #     chain_has_size = ".hasSize(" in chain
# #     chain_contains_exactly = ".containsExactly(" in chain
# #     chain_contains = ".contains(" in chain

# #     chain_is_equal = ".isEqualTo(" in chain

# #     inside_looks_string = ".toString(" in inside or "toString(" in inside

# #     if chain_has_bool:
# #         decl = f"{indent}boolean {var} = {inside};"
# #         new_assert = "assertThat(" + var + ")"
# #         new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_assert + prefix[rpar+1:]
# #         return new_prefix, True

# #     if inside_looks_string and (chain_contains or chain_is_equal):
# #         decl = f"{indent}String {var} = {inside};"
# #         new_assert = "assertThat(" + var + ")"
# #         new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_assert + prefix[rpar+1:]
# #         return new_prefix, True

# #     if chain_has_size or chain_contains_exactly or chain_contains:
# #         decl = f"{indent}java.util.List {var} = java.util.Arrays.asList({inside});"
# #         new_assert = "assertThat(" + var + ")"
# #         new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_assert + prefix[rpar+1:]
# #         return new_prefix, True

# #     decl = f"{indent}Object {var} = {inside};"
# #     new_assert = "assertThat(" + var + ")"
# #     new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_assert + prefix[rpar+1:]
# #     return new_prefix, True

# # def find_junit_assert_call(text):
# #     start = find_first_call(text, ["Assert.assert", "Assertions.assert", "assert"])
# #     if start is None:
# #         return None

# #     j = start
# #     while j < len(text) and (text[j].isalnum() or text[j] in "._"):
# #         j += 1

# #     full_name = text[start:j].strip()
# #     name = full_name.split(".")[-1]

# #     lpar = text.find("(", j)
# #     if lpar == -1:
# #         return None

# #     rpar = find_matching_rpar(text, lpar)
# #     if rpar is None:
# #         return None

# #     args_str = text[lpar + 1:rpar]
# #     end = rpar + 1
# #     return start, end, full_name, name, args_str


# # def infer_ref_array_type(expr):
# #     e = expr.strip()
# #     if not e.startswith("new "):
# #         return None

# #     after_new = e[4:].lstrip()

# #     if after_new.startswith(("byte", "short", "int", "long", "char", "boolean", "float", "double")):
# #         return None

# #     if "[" not in after_new:
# #         return None

# #     t = after_new.split("[", 1)[0].strip()
# #     if not t:
# #         return None

# #     return t + "[]"

# # def infer_type(expr):
# #     e = expr.strip()

# #     if e in ("true", "false"):
# #         return "boolean"

# #     if is_string_literal(e):
# #         return "String"

# #     s = e.lstrip("+-")

# #     if s.endswith(("l", "L")) and s[:-1].isdigit():
# #         return "long"

# #     if s.isdigit():
# #         return "int"

# #     if s.endswith(("f", "F")):
# #         t = s[:-1]
# #         if t.replace(".", "", 1).isdigit() or ("e" in t.lower() and any(c.isdigit() for c in t)):
# #             return "float"

# #     if s.replace(".", "", 1).isdigit():
# #         return "double"

# #     if "<<" in e and "L" in e:
# #         return "long"

# #     return "Object"

# # PRIM_BASE = {"byte","short","int","long","char","boolean","float","double"}

# # def infer_new_array_decl_type(expr):
# #     e = expr.strip()
# #     if not e.startswith("new "):
# #         return None

# #     after = e[4:].lstrip()

# #     j = 0
# #     while j < len(after) and after[j] not in "[{":
# #         j += 1
# #     base = after[:j].strip()
# #     if not base:
# #         return None

# #     k = j
# #     dims = 0
# #     while k + 1 < len(after) and after[k] == "[" and after[k + 1] == "]":
# #         dims += 1
# #         k += 2

# #     if dims == 0:
# #         while k < len(after) and after[k] == "[":
# #             dims += 1
# #             k += 1
# #             while k < len(after) and after[k] != "]":
# #                 k += 1
# #             if k < len(after) and after[k] == "]":
# #                 k += 1
# #             else:
# #                 break

# #     if dims == 0:
# #         return None

# #     return base + ("[]" * dims)

# # def infer_type_for_assert_array_equals(expr):
# #     t = infer_new_array_decl_type(expr)
# #     if t is not None:
# #         return t

# #     e = expr.strip()
# #     if e.endswith("[]"):
# #         return e

# #     return "Object[]"

# # def transform_junit(prefix):
# #     found = find_junit_assert_call(prefix)
# #     if not found:
# #         return prefix, False

# #     start, end, full_name, name, args_str = found

# #     if not name.startswith("assert"):
# #         return prefix, False

# #     if name in SKIP_ASSERTS:
# #         return prefix, True

# #     args = split_args(args_str)
# #     if not args:
# #         return prefix, False

# #     var = "a"
# #     indent, line_start = get_indent(prefix, start)

# #     if name in ("assertTrue", "assertFalse"):
# #         cond_idx = 1 if (len(args) >= 2 and is_string_literal(args[0])) else 0
# #         cond_expr = args[cond_idx].strip()
# #         decl = f"{indent}boolean {var} = {cond_expr};"
# #         args[cond_idx] = var
# #         new_call = full_name + "(" + ", ".join(args) + ")"
# #         new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_call + prefix[end:]
# #         return new_prefix, True

# #     if name == "assertArrayEquals" or name.endswith(".assertArrayEquals"):
# #         extract_idx = 0
# #         expr = args[extract_idx].strip()
# #         t = infer_type_for_assert_array_equals(expr)
# #     else:
# #         extract_idx = 1 if (len(args) >= 2 and is_string_literal(args[0])) else 0
# #         expr = args[extract_idx].strip()
# #         t = infer_type(expr)

# #     decl = f"{indent}{t} {var} = {expr};"
# #     args[extract_idx] = var

# #     new_call = full_name + "(" + ", ".join(args) + ")"
# #     new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_call + prefix[end:]
# #     return new_prefix, True

# # def transform(prefix):
# #     new1, ok1 = transform_assertj_assertThat(prefix)
# #     if ok1:
# #         return new1, True

# #     new2, ok2 = transform_junit(prefix)
# #     return new2, ok2

# # def main():
# #     for proj in ROOT.iterdir():
# #         if not proj.is_dir():
# #             continue

# #         pname = proj
# #         ds = proj / "dataset"
# #         inp = ds / "inputs_one_assert_stmt.csv"
# #         if not inp.exists():
# #             continue

# #         out_good = ds / "inputs_multiline.csv"
# #         out_skip = ds / "inputs_skipped.csv"

# #         good = []
# #         skipped = []

# #         with open(inp, newline="", encoding="utf-8") as f:
# #             r = csv.DictReader(f)
# #             fields = r.fieldnames

# #             for row in r:
# #                 tp = row.get("test_prefix", "") or ""
# #                 new, ok = transform(tp)
# #                 if ok:
# #                     row["test_prefix"] = new
# #                     good.append(row)
# #                 else:
# #                     skipped.append(row)

# #         with open(out_good, "w", newline="", encoding="utf-8") as f:
# #             w = csv.DictWriter(f, fieldnames=fields)
# #             w.writeheader()
# #             w.writerows(good)

# #         with open(out_skip, "w", newline="", encoding="utf-8") as f:
# #             w = csv.DictWriter(f, fieldnames=fields)
# #             w.writeheader()
# #             w.writerows(skipped)

# #         print(pname, "converted_or_kept:", len(good), "unparsed_skipped:", len(skipped))

# # if __name__ == "__main__":
# #     main()

# import csv
# from pathlib import Path

# ROOT = Path("projects_decomposed")


# SKIP_ASSERTS = {"assertThrows", "assertDoesNotThrow"}

# def is_string_literal(x):
#     s = x.strip()
#     return len(s) >= 2 and s[0] == '"' and s[-1] == '"'

# def split_args(s):
#     args = []
#     cur = []

#     par = br = sq = ang = 0
#     in_str = False
#     in_chr = False
#     esc = False

#     i = 0
#     n = len(s)

#     while i < n:
#         ch = s[i]
#         nxt = s[i + 1] if i + 1 < n else ""
#         nxt2 = s[i + 2] if i + 2 < n else ""

#         if esc:
#             cur.append(ch)
#             esc = False
#             i += 1
#             continue

#         if (in_str or in_chr) and ch == "\\":
#             cur.append(ch)
#             esc = True
#             i += 1
#             continue

#         if in_str:
#             cur.append(ch)
#             if ch == '"':
#                 in_str = False
#             i += 1
#             continue

#         if in_chr:
#             cur.append(ch)
#             if ch == "'":
#                 in_chr = False
#             i += 1
#             continue

#         if ch == '"':
#             cur.append(ch)
#             in_str = True
#             i += 1
#             continue

#         if ch == "'":
#             cur.append(ch)
#             in_chr = True
#             i += 1
#             continue

#         if ch == "(":
#             par += 1
#             cur.append(ch)
#             i += 1
#             continue
#         if ch == ")":
#             par -= 1
#             cur.append(ch)
#             i += 1
#             continue

#         if ch == "{":
#             br += 1
#             cur.append(ch)
#             i += 1
#             continue
#         if ch == "}":
#             br -= 1
#             cur.append(ch)
#             i += 1
#             continue

#         if ch == "[":
#             sq += 1
#             cur.append(ch)
#             i += 1
#             continue
#         if ch == "]":
#             sq -= 1
#             cur.append(ch)
#             i += 1
#             continue

#         if ch == "<":
#             if nxt == "<":
#                 cur.append("<<")
#                 i += 2
#                 continue
#             ang += 1
#             cur.append("<")
#             i += 1
#             continue

#         if ch == ">":
#             if nxt == ">" and nxt2 == ">":
#                 if ang > 0:
#                     cur.append(">>>")
#                     ang = max(0, ang - 3)
#                     i += 3
#                     continue
#                 cur.append(">>>")
#                 i += 3
#                 continue

#             if nxt == ">":
#                 if ang > 0:
#                     cur.append(">>")
#                     ang = max(0, ang - 2)
#                     i += 2
#                     continue
#                 cur.append(">>")
#                 i += 2
#                 continue

#             if ang > 0:
#                 ang -= 1
#             cur.append(">")
#             i += 1
#             continue

#         if ch == "," and par == 0 and br == 0 and sq == 0 and ang == 0:
#             args.append("".join(cur).strip())
#             cur = []
#             i += 1
#             continue

#         cur.append(ch)
#         i += 1

#     tail = "".join(cur).strip()
#     if tail:
#         args.append(tail)

#     return args

# def find_first_call(text, prefixes):
#     starts = []
#     for p in prefixes:
#         i = text.find(p)
#         if i != -1:
#             starts.append(i)
#     if not starts:
#         return None
#     return min(starts)

# def get_indent(text, idx):
#     line_start = text.rfind("\n", 0, idx) + 1
#     indent = ""
#     p = line_start
#     while p < len(text) and text[p] in (" ", "\t"):
#         indent += text[p]
#         p += 1
#     return indent, line_start

# def find_matching_rpar(text, lpar):
#     depth = 0
#     in_str = False
#     in_chr = False
#     esc = False

#     k = lpar
#     while k < len(text):
#         ch = text[k]

#         if esc:
#             esc = False
#             k += 1
#             continue

#         if (in_str or in_chr) and ch == "\\":
#             esc = True
#             k += 1
#             continue

#         if in_str:
#             if ch == '"':
#                 in_str = False
#             k += 1
#             continue

#         if in_chr:
#             if ch == "'":
#                 in_chr = False
#             k += 1
#             continue

#         if ch == '"':
#             in_str = True
#             k += 1
#             continue

#         if ch == "'":
#             in_chr = True
#             k += 1
#             continue

#         if ch == "(":
#             depth += 1
#         elif ch == ")":
#             depth -= 1
#             if depth == 0:
#                 return k

#         k += 1

#     return None

# def transform_assertj_assertThat(prefix):
#     start = prefix.find("assertThat(")
#     if start == -1:
#         return prefix, False

#     lpar = prefix.find("(", start)
#     if lpar == -1:
#         return prefix, False

#     rpar = find_matching_rpar(prefix, lpar)
#     if rpar is None:
#         return prefix, False

#     inside = prefix[lpar + 1:rpar].strip()
#     chain = prefix[rpar + 1:]

#     if not chain.strip().startswith("."):
#         return prefix, False

#     var = "a"
#     indent, line_start = get_indent(prefix, start)

#     chain_has_bool = (".isTrue()" in chain) or (".isFalse()" in chain)

#     chain_has_size = ".hasSize(" in chain
#     chain_contains_exactly = ".containsExactly(" in chain
#     chain_contains = ".contains(" in chain

#     chain_is_equal = ".isEqualTo(" in chain

#     inside_looks_string = ".toString(" in inside or "toString(" in inside

#     if chain_has_bool:
#         decl = f"{indent}boolean {var} = {inside};"
#         new_assert = "assertThat(" + var + ")"
#         new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_assert + prefix[rpar+1:]
#         return new_prefix, True

#     if inside_looks_string and (chain_contains or chain_is_equal):
#         decl = f"{indent}String {var} = {inside};"
#         new_assert = "assertThat(" + var + ")"
#         new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_assert + prefix[rpar+1:]
#         return new_prefix, True

#     if chain_has_size or chain_contains_exactly or chain_contains:
#         decl = f"{indent}java.util.List {var} = java.util.Arrays.asList({inside});"
#         new_assert = "assertThat(" + var + ")"
#         new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_assert + prefix[rpar+1:]
#         return new_prefix, True

#     decl = f"{indent}Object {var} = {inside};"
#     new_assert = "assertThat(" + var + ")"
#     new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_assert + prefix[rpar+1:]
#     return new_prefix, True

# def find_junit_assert_call(text):
#     start = find_first_call(text, ["Assert.assert", "Assertions.assert", "assert"])
#     if start is None:
#         return None

#     j = start
#     while j < len(text) and (text[j].isalnum() or text[j] in "._"):
#         j += 1

#     full_name = text[start:j].strip()
#     name = full_name.split(".")[-1]

#     lpar = text.find("(", j)
#     if lpar == -1:
#         return None

#     rpar = find_matching_rpar(text, lpar)
#     if rpar is None:
#         return None

#     args_str = text[lpar + 1:rpar]
#     end = rpar + 1
#     return start, end, full_name, name, args_str


# def infer_ref_array_type(expr):
#     e = expr.strip()
#     if not e.startswith("new "):
#         return None

#     after_new = e[4:].lstrip()

#     if after_new.startswith(("byte", "short", "int", "long", "char", "boolean", "float", "double")):
#         return None

#     if "[" not in after_new:
#         return None

#     t = after_new.split("[", 1)[0].strip()
#     if not t:
#         return None

#     return t + "[]"

# def infer_type(expr):
#     e = expr.strip()

#     if e in ("true", "false"):
#         return "boolean"

#     if is_string_literal(e):
#         return "String"

#     s = e.lstrip("+-")

#     if s.endswith(("l", "L")) and s[:-1].isdigit():
#         return "long"

#     if s.isdigit():
#         return "int"

#     if s.endswith(("f", "F")):
#         t = s[:-1]
#         if t.replace(".", "", 1).isdigit() or ("e" in t.lower() and any(c.isdigit() for c in t)):
#             return "float"

#     if s.replace(".", "", 1).isdigit():
#         return "double"

#     if "<<" in e and "L" in e:
#         return "long"

#     return "Object"

# PRIM_BASE = {"byte","short","int","long","char","boolean","float","double"}

# def infer_new_array_decl_type(expr):
#     e = expr.strip()
#     if not e.startswith("new "):
#         return None

#     after = e[4:].lstrip()

#     j = 0
#     while j < len(after) and after[j] not in "[{":
#         j += 1
#     base = after[:j].strip()
#     if not base:
#         return None

#     k = j
#     dims = 0
#     while k + 1 < len(after) and after[k] == "[" and after[k + 1] == "]":
#         dims += 1
#         k += 2

#     if dims == 0:
#         while k < len(after) and after[k] == "[":
#             dims += 1
#             k += 1
#             while k < len(after) and after[k] != "]":
#                 k += 1
#             if k < len(after) and after[k] == "]":
#                 k += 1
#             else:
#                 break

#     if dims == 0:
#         return None

#     return base + ("[]" * dims)

# def infer_type_for_assert_array_equals(expr):
#     t = infer_new_array_decl_type(expr)
#     if t is not None:
#         return t

#     e = expr.strip()
#     if e.endswith("[]"):
#         return e

#     return "Object[]"

# def transform_junit(prefix):
#     found = find_junit_assert_call(prefix)
#     if not found:
#         return prefix, False

#     start, end, full_name, name, args_str = found

#     if not name.startswith("assert"):
#         return prefix, False

#     if name in SKIP_ASSERTS:
#         return prefix, True

#     args = split_args(args_str)
#     if not args:
#         return prefix, False

#     var = "a"
#     indent, line_start = get_indent(prefix, start)

#     if name in ("assertTrue", "assertFalse"):
#         cond_idx = 1 if (len(args) >= 2 and is_string_literal(args[0])) else 0
#         cond_expr = args[cond_idx].strip()
#         decl = f"{indent}boolean {var} = {cond_expr};"
#         args[cond_idx] = var
#         new_call = full_name + "(" + ", ".join(args) + ")"
#         new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_call + prefix[end:]
#         return new_prefix, True

#     # Extract the ACTUAL value (last arg) into `a`, keep expected literal in assert.
#     # For (msg?, expected, actual): actual is always args[-1].
#     if name == "assertArrayEquals" or name.endswith(".assertArrayEquals"):
#         extract_idx = len(args) - 1
#         expr = args[extract_idx].strip()
#         t = infer_type_for_assert_array_equals(expr)
#     else:
#         extract_idx = len(args) - 1
#         expr = args[extract_idx].strip()
#         t = infer_type(expr)

#     decl = f"{indent}{t} {var} = {expr};"
#     args[extract_idx] = var

#     new_call = full_name + "(" + ", ".join(args) + ")"
#     new_prefix = prefix[:line_start] + decl + "\n" + prefix[line_start:start] + new_call + prefix[end:]
#     return new_prefix, True

# def transform(prefix):
#     new1, ok1 = transform_assertj_assertThat(prefix)
#     if ok1:
#         return new1, True

#     new2, ok2 = transform_junit(prefix)
#     return new2, ok2


# # ── Oracle extraction ─────────────────────────────────────────────────────────

# def _extract_assertj_oracle(prefix):
#     """
#     Handle assertThat(actual).chainMethod(...) style assertions.
#     The *actual* value under test is the expression inside assertThat(...).
#     Returns that expression string, or None if not an assertThat call.
#     """
#     start = prefix.find("assertThat(")
#     if start == -1:
#         return None

#     lpar = prefix.find("(", start)
#     if lpar == -1:
#         return None
#     rpar = find_matching_rpar(prefix, lpar)
#     if rpar is None:
#         return None

#     # The actual is what is inside assertThat(...)
#     actual = prefix[lpar + 1:rpar].strip()

#     # Only return if there is a chained assertion (so it really is assertThat)
#     chain = prefix[rpar + 1:].strip()
#     if not chain.startswith("."):
#         return None

#     return actual


# def _extract_junit_oracle(prefix):
#     """
#     Extract the value assigned to variable 'a' in the transformed test,
#     which mirrors the extract_idx logic in transform_junit():

#     - assertTrue/assertFalse/assertNull/assertNotNull:
#         'a' = the condition/expression (first meaningful arg)
#         e.g. assertTrue(MUT())          -> MUT()
#              assertNotNull(msg, MUT())  -> MUT()

#     - assertEquals/assertSame/assertNotEquals/etc:
#         'a' = the expected/first meaningful arg
#         e.g. assertEquals(0, MUT())        -> 0
#              assertEquals(msg, 0, MUT())   -> 0
#              assertEquals(fullList, MUT()) -> fullList
#     """
#     found = find_junit_assert_call(prefix)
#     if not found:
#         return None

#     _start, _end, _full_name, name, args_str = found

#     if not name.startswith("assert"):
#         return None

#     if name in SKIP_ASSERTS:
#         return None

#     args = split_args(args_str)
#     if not args:
#         return None

#     # `a` is always assigned args[-1] (the actual/MUT call).
#     # assertTrue(MUT())          -> a = MUT()
#     # assertTrue(msg, MUT())     -> a = MUT()
#     # assertEquals(0, MUT())     -> a = MUT()
#     # assertEquals(msg, 0, MUT())-> a = MUT()
#     return args[-1].strip()


# def extract_oracle(prefix):
#     """
#     Extract the actual value under test from the assertion in a test prefix.
#     Works for both JUnit-style and AssertJ-style assertions.

#     For JUnit:  always returns the LAST argument (the actual expression).
#     For AssertJ: returns the expression inside assertThat(...).

#     Returns "" (empty string) if extraction failed.
#     """
#     if not prefix:
#         return ""

#     # AssertJ first
#     oracle = _extract_assertj_oracle(prefix)
#     if oracle is not None:
#         return oracle

#     # JUnit / plain assert
#     oracle = _extract_junit_oracle(prefix)
#     if oracle is not None:
#         return oracle

#     return ""

# # ── Main ──────────────────────────────────────────────────────────────────────

# def main():
#     # for proj in ROOT.iterdir():
#     #     if not proj.is_dir():
#     #         continue
#     proj = Path("projects_decomposed/commons-collections4")

#     pname = proj
#     ds = proj / "dataset"
#     inp = ds / "inputs_one_assert_stmt.csv"
#     # if not inp.exists():
#         # continue

#     out_good = ds / "inputs_multiline.csv"
#     out_skip = ds / "inputs_skipped.csv"

#     good = []
#     skipped = []

#     with open(inp, newline="", encoding="utf-8") as f:
#         r = csv.DictReader(f)
#         fields = r.fieldnames

#     # Add oracle column if not already present
#     new_fields = list(fields)
#     if "oracle" not in new_fields:
#         new_fields.append("oracle")

#     with open(inp, newline="", encoding="utf-8") as f:
#         r = csv.DictReader(f)

#         for row in r:
#             tp = row.get("test_prefix", "") or ""

#             # Extract oracle BEFORE transformation (assertions still intact)
#             row["oracle"] = extract_oracle(tp)

#             new, ok = transform(tp)
#             if ok:
#                 row["test_prefix"] = new
#                 good.append(row)
#             else:
#                 skipped.append(row)

#     with open(out_good, "w", newline="", encoding="utf-8") as f:
#         w = csv.DictWriter(f, fieldnames=new_fields)
#         w.writeheader()
#         w.writerows(good)

#     with open(out_skip, "w", newline="", encoding="utf-8") as f:
#         w = csv.DictWriter(f, fieldnames=new_fields)
#         w.writeheader()
#         w.writerows(skipped)

#     print(pname, "converted_or_kept:", len(good), "unparsed_skipped:", len(skipped))

# if __name__ == "__main__":
#     main()