import csv
import re
from pathlib import Path
from typing import List, Tuple, Dict, Optional

INPUT_INPUTS = "projects_decomposed/commons-beanutils/dataset_check/inputs_try_fail_catch_multi.csv"
INPUT_META   = "projects_decomposed/commons-beanutils/dataset_multiple/meta_multiple.csv"
OUT_INPUTS   = "projects_decomposed/commons-beanutils/dataset_MUST_THROW/inputs_renamed.csv"
OUT_META     = "projects_decomposed/commons-beanutils/dataset_MUST_THROW/meta_renamed.csv"

Path(OUT_INPUTS).parent.mkdir(parents=True, exist_ok=True)
Path(OUT_META).parent.mkdir(parents=True, exist_ok=True)

# ── comment/string sanitiser (from doc-3) ────────────────────────────────────

_STRING_RE = re.compile(r'"(?:\\.|[^"\\])*"')
_CHAR_RE   = re.compile(r"'(?:\\.|[^'\\])'")

def _strip_line_comment(s: str) -> str:
    idx = s.find("//")
    return s if idx < 0 else s[:idx]

def sanitize(line: str, in_block: bool) -> Tuple[str, bool]:
    s, out = line, []
    i = 0
    while i < len(s):
        if in_block:
            end = s.find("*/", i)
            if end == -1:
                return ("", True)
            i = end + 2
            in_block = False
        else:
            start = s.find("/*", i)
            if start == -1:
                out.append(s[i:]); break
            out.append(s[i:start])
            i = start + 2
            in_block = True
    s2 = "".join(out)
    s2 = _STRING_RE.sub('""', s2)
    s2 = _CHAR_RE.sub("''", s2)
    return (_strip_line_comment(s2), in_block)

# ── method header helpers ─────────────────────────────────────────────────────

def get_method_header(lines: List[str]) -> str:
    in_block = False
    for ln in lines:
        san, in_block = sanitize(ln, in_block)
        s = san.strip()
        if not s or s.startswith("@") or "class " in s or "interface " in s:
            continue
        if "(" in s:
            return ln
    return ""

def get_method_name(header: str) -> str:
    if not header:
        return "testMethod"
    tokens = re.findall(r"[A-Za-z_][A-Za-z0-9_]*", header.split("(", 1)[0])
    return tokens[-1] if tokens else "testMethod"

# ── brace rebalancer ──────────────────────────────────────────────────────────

def brace_depth(lines: List[str]) -> int:
    in_block = False
    d = 0
    for ln in lines:
        san, in_block = sanitize(ln, in_block)
        d += san.count('{') - san.count('}')
    return d

def rebalance_braces(lines: List[str]) -> List[str]:
    in_block = False
    opens = closes = 0
    for ln in lines:
        san, in_block = sanitize(ln, in_block)
        opens  += san.count("{")
        closes += san.count("}")
    diff = opens - closes
    if diff <= 0:
        return lines
    indent = ""
    for ln in lines:
        if ln.strip() and not ln.strip().startswith("@"):
            indent = ln[: len(ln) - len(ln.lstrip())]
            break
    for _ in range(diff):
        lines.append(indent + "}\n")
    return lines

# ── try-block extraction ──────────────────────────────────────────────────────

def extract_try_blocks(lines: List[str]) -> List[Tuple[int, int]]:
    """Return (start, end) index pairs for every top-level try block."""
    spans = []
    i = 0
    in_block = False
    while i < len(lines):
        san, _ = sanitize(lines[i], in_block)
        if re.search(r'\btry\b', san):
            depth = 0
            start = i
            j, ib2 = i, in_block
            while j < len(lines):
                s2, ib2 = sanitize(lines[j], ib2)
                depth += s2.count("{") - s2.count("}")
                j += 1
                if depth <= 0 and j > start + 1:
                    break
            spans.append((start, j - 1))
            i = j
            in_block = ib2
        else:
            _, in_block = sanitize(lines[i], in_block)
            i += 1
    return spans

def try_has_fail(lines: List[str], start: int, end: int) -> bool:
    """True if fail() appears in the try body (before catch)."""
    in_try_body = False
    in_block = False
    for i in range(start, end + 1):
        san, in_block = sanitize(lines[i], in_block)
        if re.search(r'\btry\b', san) and '{' in san:
            in_try_body = True
            continue
        if in_try_body and re.search(r'\bcatch\b', san):
            break
        if in_try_body and 'fail(' in san:
            return True
    return False

def fail_in_catch_body(lines: List[str], start: int, end: int) -> bool:
    """True if fail() is directly in the FIRST catch body (not nested)."""
    seen_catch = False
    catch_lvl  = None
    depth = 0
    in_block = False
    for i in range(start, end + 1):
        san, in_block = sanitize(lines[i], in_block)
        has_catch = bool(re.search(r'\bcatch\b', san))
        closes = san.count('}');  opens = san.count('{')
        if has_catch:
            lvl = depth - closes
            if not seen_catch:
                seen_catch = True; catch_lvl = lvl
                depth += opens - closes
            elif lvl <= catch_lvl:
                break
            else:
                depth += opens - closes
        else:
            if seen_catch and depth == catch_lvl + 1 and 'fail(' in san:
                return True
            depth += opens - closes
            if seen_catch and depth <= catch_lvl:
                break
    return False

def has_catch_clause(lines: List[str], start: int, end: int) -> bool:
    in_block = False
    for i in range(start, end + 1):
        san, in_block = sanitize(lines[i], in_block)
        if re.search(r'\bcatch\b', san):
            return True
    return False

# ── keep only first catch ─────────────────────────────────────────────────────

def keep_first_catch_only(block_lines: List[str]) -> List[str]:
    result = []
    seen_catch = False; catch_lvl = None; depth = 0; in_block = False
    for line in block_lines:
        san, in_block = sanitize(line, in_block)
        has_catch = bool(re.search(r'\bcatch\b', san))
        closes = san.count('}');  opens = san.count('{')
        if has_catch:
            lvl = depth - closes
            if not seen_catch:
                seen_catch = True; catch_lvl = lvl
                result.append(line); depth += opens - closes
            elif lvl <= catch_lvl:
                result.append('        }\n'); break
            else:
                result.append(line); depth += opens - closes
        else:
            result.append(line); depth += opens - closes
    return result

# ── is a line purely a setup/noise line to drop? ─────────────────────────────

_CTRL_FLOW = re.compile(r'\b(if|else|for|while|do|switch|try|catch|finally)\b')

def clean_setup_lines(setup_lines: List[str]) -> List[str]:
    """
    Filter setup (pre-try) lines:
      DROP:  blank, comment-only, assert*, ellipsis, annotations, method signature
             anonymous { } blocks (standalone { with NO preceding control-flow keyword)
             and their matching closing }
      KEEP:  everything else, including { } that belong to if/for/while/else structures
    """
    # First pass: decide per-line, tracking anonymous-block depth
    anon_depth = 0           # depth of dropped anonymous { blocks
    prev_real = ""           # last non-blank non-comment sanitized line kept
    out = []
    in_block = False
    for ln in setup_lines:
        san, in_block = sanitize(ln, in_block)
        s = san.strip()

        # Always drop these regardless
        if not s:
            continue
        if s.startswith('//') or s.startswith('/*') or s.startswith('*') or s.endswith('*/'):
            continue
        if re.match(r'assert\w*\s*\(', s):
            continue
        if s == '...':
            continue
        if s.startswith('@'):
            continue
        if re.match(r'(?:public|protected|private)\s+(?:static\s+)?void\s+\w+', s):
            continue

        # Standalone `{` — anonymous if not preceded by control-flow
        if s == '{':
            ctrl = re.search(r'\b(if|else|for|while|do|switch|try|catch|finally)\b', prev_real)
            if not ctrl:
                anon_depth += 1   # anonymous block open — track and drop
                continue
            # else: belongs to a control-flow structure on the previous line → keep
            out.append(ln)
            prev_real = s
            continue

        # Standalone `}` — close of anonymous block?
        if s == '}':
            if anon_depth > 0:
                anon_depth -= 1   # matching anonymous close — drop
                continue
            # else: closes a real structure — keep
            out.append(ln)
            prev_real = s
            continue

        out.append(ln)
        prev_real = s

    return out

# ── build one test from one try block ────────────────────────────────────────

def build_single_throw_test(
    method_lines: List[str],
    try_start: int,
    try_end: int,
    new_method_name: str,
    setup_open: int,   # kept for API compat; recalculated internally
) -> str:
    """
    Assemble:  signature  +  cleaned setup  +  try-block (first catch only)

    Setup = lines from line-1 up to the FIRST try in the whole method.
    This avoids pulling in other try-blocks as "setup" for a later target block.
    The for-loop / if-guard that DIRECTLY precedes all tries is correctly included.
    """
    # ── 1. signature ─────────────────────────────────────────────────────────
    header   = get_method_header(method_lines)
    orig     = get_method_name(header)
    sig_line = method_lines[0].replace(orig, new_method_name, 1) if method_lines else ""

    # ── 2. setup: line 1 … first-try-line (exclusive) ────────────────────────
    first_try_idx = None
    in_block = False
    for i, ln in enumerate(method_lines):
        san, in_block = sanitize(ln, in_block)
        if i == 0:
            continue
        if re.search(r'\btry\b', san):
            first_try_idx = i
            break

    setup_raw   = method_lines[1:first_try_idx] if first_try_idx else []
    setup_clean = clean_setup_lines(setup_raw)

    # recalculate how many braces the cleaned setup leaves open
    s_open = brace_depth(setup_clean)

    # ── 3. target try block (first catch only) ────────────────────────────────
    try_block = keep_first_catch_only(method_lines[try_start:try_end + 1])

    # ── 4. assemble ───────────────────────────────────────────────────────────
    out: List[str] = [sig_line] + setup_clean + try_block

    # close any for-loop / if wrappers left open by setup
    for _ in range(s_open):
        out.append('        }\n')

    out = rebalance_braces(out)

    while out and not out[0].strip():
        out.pop(0)
    if out and not out[-1].endswith('\n'):
        out[-1] += '\n'

    return "".join(out)

def get_method_lines(code: str) -> List[str]:
    return [l + '\n' if not l.endswith('\n') else l for l in code.splitlines()]


# ── preprocessing: strip assertions, comments, blanks, unused vars ────────────

_ASSERT_NAMES = re.compile(
    r'\b(assertEquals|assertNotEquals|assertTrue|assertFalse|assertNull|'
    r'assertNotNull|assertSame|assertNotSame|assertArrayEquals|assertIterableEquals|'
    r'assertLinesMatch|assertAll|assertThat|assertThrows|assertDoesNotThrow|'
    r'assertTimeout|assertTimeoutPreemptively|assertNotSame)\s*\('
)

# Java local variable declaration: optional modifiers, type, name, optional init
# Handles both:  Type name = ...;   and   Type name[] = ...;  (C-style arrays)
_VAR_DECL_RE = re.compile(
    r'^\s*(?:final\s+)?'
    r'(?:[A-Za-z_$][A-Za-z0-9_$<>\[\],\s?]*?)\s+'   # type (rough)
    r'([A-Za-z_$][A-Za-z0-9_$]*)'                    # var name group(1)
    r'\s*(?:\[\s*\])?\s*'                             # optional [] after name (C-style)
    r'(?:=.*)?;'                                      # optional init + semicolon
)

def _is_assert_or_comment(line: str, in_block: bool) -> Tuple[bool, bool]:
    """Return (should_drop, new_in_block)."""
    san, new_block = sanitize(line, in_block)
    s = san.strip()
    if not s:
        return (True, new_block)   # blank / pure comment line
    if not s and new_block != in_block:
        return (True, new_block)
    # original line entirely a block comment continuation
    raw = line.strip()
    if raw.startswith('//') or raw.startswith('/*') or raw.startswith('*'):
        return (True, new_block)
    # assert statement (Java keyword)
    if re.match(r'^\s*assert\b', san):
        return (True, new_block)
    # JUnit assert call
    if _ASSERT_NAMES.search(san):
        return (True, new_block)
    return (False, new_block)


def _collect_used_names(lines: List[str]) -> set:
    """Collect every identifier token that appears in non-declaration lines."""
    used = set()
    in_block = False
    for ln in lines:
        san, in_block = sanitize(ln, in_block)
        s = san.strip()
        if not s:
            continue
        # tokenize identifiers
        for tok in re.findall(r'[A-Za-z_$][A-Za-z0-9_$]*', san):
            used.add(tok)
    return used


def _is_unused_var_decl(line: str, used_names: set) -> bool:
    """
    Return True if the line is a local variable declaration whose name
    never appears anywhere else in the method.
    Conservative: only drops if name appears exactly once (the declaration itself).
    """
    san, _ = sanitize(line, False)
    s = san.strip()
    if not s or not s.endswith(';'):
        return False
    m = _VAR_DECL_RE.match(san)
    if not m:
        return False
    # skip field-access / method-call lines mistakenly matched
    if '(' in san.split(m.group(1))[0].split('=')[0] if '=' in san else '(' in san.split(m.group(1))[0]:
        return False
    var_name = m.group(1)
    # Java keywords / types that look like identifiers
    if var_name in ('return', 'throw', 'new', 'this', 'super', 'true', 'false', 'null'):
        return False
    # count occurrences in full used set — if only 1, it's only the declaration
    return used_names.count(var_name) <= 1 if isinstance(used_names, list) else False


def _drop_dead_assignments(lines: List[str]) -> List[str]:
    """
    Remove pure assignment lines  `varName = expr;`  whose written value
    is never subsequently READ before being overwritten or reaching end-of-scope.

    Algorithm (single forward pass per variable):
      For each line that is a pure assignment (not a declaration), scan forward.
      If the next occurrence of varName is ALSO a pure assignment (overwrite),
      or varName never appears again, the current assignment is dead → drop it.

    A 'read' means varName appears in a line that is NOT itself a pure assignment
    to that same variable.

    Runs iteratively until no more removals (cascading dead code).
    """
    _JAVA_KEYWORDS = {
        'return','throw','new','this','super','true','false','null',
        'if','else','for','while','do','switch','case','break','continue',
        'try','catch','finally','class','interface','extends','implements',
        'public','private','protected','static','final','void','int','long',
        'double','float','boolean','char','byte','short','abstract','native',
    }

    # Regex: pure assignment  (no type keyword before var = expr)
    # Matches:  `  varName = ...;`  or  `  varName = ...\n` spanning 1 line
    ASSIGN_RE = re.compile(r'^\s*([A-Za-z_$][A-Za-z0-9_$]*)\s*=(?!=)')

    def is_pure_assign(san_stripped: str, var: str) -> bool:
        """True if the sanitized line is purely `var = expr;` (not a decl, not ==)."""
        m = ASSIGN_RE.match(san_stripped)
        return bool(m and m.group(1) == var)

    changed = True
    current = lines[:]
    while changed:
        changed = False
        # build sanitized cache
        san_cache: List[str] = []
        in_block = False
        for ln in current:
            san, in_block = sanitize(ln, in_block)
            san_cache.append(san.strip())

        drop_indices = set()

        for i, (ln, sc) in enumerate(zip(current, san_cache)):
            if i in drop_indices:
                continue
            m = ASSIGN_RE.match(sc)
            if not m:
                continue
            var = m.group(1)
            if var in _JAVA_KEYWORDS:
                continue
            # Make sure this isn't a declaration (type keyword before var)
            # by checking if the sanitized line starts with var= (after stripping)
            # i.e. no type token precedes it
            before_eq = sc.split('=')[0].strip()
            tokens_before = re.findall(r'[A-Za-z_$][A-Za-z0-9_$]*', before_eq)
            if len(tokens_before) != 1:
                # more than one token before = means it's a declaration like `Type var =`
                continue

            # Scan forward: is var read before next pure-assignment to var?
            var_is_read = False
            for j in range(i + 1, len(current)):
                if j in drop_indices:
                    continue
                sc_j = san_cache[j]
                if var not in sc_j:
                    continue
                # var appears in this line — is it a read or another overwrite?
                if is_pure_assign(sc_j, var):
                    # another overwrite — current assignment at i is dead
                    break
                else:
                    # var is read here
                    var_is_read = True
                    break

            if not var_is_read:
                # If RHS contains a method call, keep it as a bare statement
                # (the call may have side effects like throwing exceptions)
                rhs = sc.split('=', 1)[1].strip() if '=' in sc else ''
                if '(' in rhs:
                    # strip   varName =   prefix, keep   rhs_statement
                    indent = ln[: len(ln) - len(ln.lstrip())]
                    # preserve original spacing from the actual line, not sanitized
                    raw_rhs = ln.split('=', 1)[1].lstrip() if '=' in ln else ln.lstrip()
                    current[i] = indent + raw_rhs if raw_rhs.strip() else None
                    if current[i] is None:
                        drop_indices.add(i)
                    changed = True
                else:
                    drop_indices.add(i)
                    changed = True

        current = [ln for i, ln in enumerate(current) if i not in drop_indices and ln is not None]

    return current


def preprocess_method(lines: List[str]) -> List[str]:
    """
    Applied to ALL lines of a method BEFORE decomposition:
      1. Remove blank lines, comment-only lines
      2. Remove assertion lines  (JUnit assert*, Java assert stmt)
      3. Remove dead assignments  (var = expr where value is never read)
      4. Remove unused variable declarations  (iterates until stable)
    Preserves: fail(), try/catch structure, setup code, method signature.
    """
    from collections import Counter

    # ── pass 1: drop blanks, comments, assertions ────────────────────────────
    out: List[str] = []
    in_block = False
    for ln in lines:
        drop, in_block = _is_assert_or_comment(ln, in_block)
        if not drop:
            out.append(ln)

    # ── pass 2: dead assignment elimination (iterative) ───────────────────────
    out = _drop_dead_assignments(out)

    # ── pass 3: unused variable declarations (iterative) ──────────────────────
    _SKIP_VARS = {
        'return','throw','new','this','super','true','false','null',
        'int','long','double','float','boolean','char','byte','short',
    }
    changed = True
    while changed:
        changed = False
        all_tokens: List[str] = []
        in_block = False
        for ln in out:
            san, in_block = sanitize(ln, in_block)
            all_tokens.extend(re.findall(r'[A-Za-z_$][A-Za-z0-9_$]*', san))
        token_counts = Counter(all_tokens)

        next_out: List[str] = []
        in_block = False
        for ln in out:
            san, in_block = sanitize(ln, in_block)
            s = san.strip()
            if s and s.endswith(';'):
                m = _VAR_DECL_RE.match(san)
                if m:
                    var_name = m.group(1)
                    if var_name not in _SKIP_VARS:
                        if token_counts[var_name] <= 1:
                            changed = True
                            continue   # drop unused declaration
            next_out.append(ln)
        out = next_out

    return out

def setup_open_count(method_lines: List[str]) -> int:
    """Unclosed brace depth in setup lines (after the method opening brace)."""
    in_block = False
    depth = 0
    for ln in method_lines:
        san, in_block = sanitize(ln, in_block)
        if re.search(r'\btry\b', san):
            break
        depth += san.count('{') - san.count('}')
    return max(0, depth - 1)   # subtract 1 for the method's own {

# ── CSV helpers ───────────────────────────────────────────────────────────────

def read_csv(path):
    with open(path, newline='', encoding='utf8', errors='ignore') as f:
        rows = list(csv.DictReader(f))
    with open(path, newline='', encoding='utf8') as f:
        fields = csv.DictReader(f).fieldnames
    return rows, fields

def write_csv(path, rows, fieldnames):
    with open(path, 'w', newline='', encoding='utf8') as f:
        w = csv.DictWriter(f, fieldnames=fieldnames)
        w.writeheader()
        w.writerows(rows)

# ── main ──────────────────────────────────────────────────────────────────────

input_rows, input_fields = read_csv(INPUT_INPUTS)

meta_by_id: Dict[str, dict] = {}
meta_fields = []
try:
    meta_rows_list, meta_fields = read_csv(INPUT_META)
    meta_by_id = {r['id']: r for r in meta_rows_list if r.get('id')}
except FileNotFoundError:
    print(f"WARNING: meta not found: {INPUT_META}")

out_inputs: List[dict] = []
out_meta:   List[dict] = []
skipped_fail_catch = skipped_no_catch = skipped_no_call = generated = 0

for row in input_rows:
    original_id = row['id']
    code        = row['test_prefix']
    class_name  = row['test_name'].split('::')[0]
    base_method = row['test_name'].split('::')[1]
    base_id     = '_'.join(original_id.split('_')[:2])

    method_lines = get_method_lines(code)
    method_lines = preprocess_method(method_lines)   # strip assertions, comments, blanks, unused vars
    s_open       = setup_open_count(method_lines)
    spans        = extract_try_blocks(method_lines)

    if not spans:
        new_method = f'{base_method}_101_oe'
        new_id     = f'{base_id}_101'
        new_code   = build_single_throw_test(method_lines, -1, -1, new_method, 0)
        r = row.copy()
        r.update({'id': new_id, 'test_prefix': new_code,
                  'test_name': f'{class_name}::{new_method}'})
        out_inputs.append(r)
        if original_id in meta_by_id:
            m = meta_by_id[original_id].copy()
            m.update({'id': new_id, 'test_name': new_method})
            out_meta.append(m)
        generated += 1
        continue

    counter = 101
    for (ts, te) in spans:
        if fail_in_catch_body(method_lines, ts, te):
            skipped_fail_catch += 1; counter += 1; continue
        if not has_catch_clause(method_lines, ts, te):
            skipped_no_catch += 1; counter += 1; continue
        if not try_has_fail(method_lines, ts, te):
            skipped_no_call += 1; counter += 1; continue

        new_method = f'{base_method}_{counter}_oe'
        new_id     = f'{base_id}_{counter}'
        new_code   = build_single_throw_test(method_lines, ts, te, new_method, s_open)

        r = row.copy()
        r.update({'id': new_id, 'test_prefix': new_code,
                  'test_name': f'{class_name}::{new_method}'})
        out_inputs.append(r)

        if original_id in meta_by_id:
            m = meta_by_id[original_id].copy()
            m.update({'id': new_id, 'test_name': new_method})
            out_meta.append(m)

        generated += 1
        counter += 1

write_csv(OUT_INPUTS, out_inputs, input_fields)
if meta_fields and out_meta:
    write_csv(OUT_META, out_meta, meta_fields)

unbalanced = [r['id'] for r in out_inputs
              if r['test_prefix'].count('{') != r['test_prefix'].count('}')]

print(f"Input rows                     : {len(input_rows)}")
print(f"Output rows (inputs)           : {generated}")
print(f"Output rows (meta)             : {len(out_meta)}")
print(f"Skipped (fail in catch)        : {skipped_fail_catch}")
print(f"Skipped (no catch clause)      : {skipped_no_catch}")
print(f"Skipped (try body only fail()) : {skipped_no_call}")
print(f"Unbalanced braces              : {len(unbalanced)}")
for uid in unbalanced[:10]:
    print(f"  !! {uid}")