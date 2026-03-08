import re
import csv
from pathlib import Path

ASSERTION_PATTERN = re.compile(
    r'\b(assertEquals|assertNotEquals|assertTrue|assertFalse|'
    r'assertNotNull|assertNull|assertThat|assertThrows|assertSame|'
    r'assertArrayEquals|fail|verify|assertAll)\s*\('
)
METHOD_SIG_PATTERN = re.compile(
    r'^\s*(public|protected|private)\s+.*\(.*\).*\{'
)
KEYWORD_BLOCK = re.compile(
    r'^\s*(if|else\s*if|else|for|while|try|catch|finally|switch)\b'
)
CATCH_FINALLY_LINE = re.compile(r'^}\s*(catch|finally)\b')
METHOD_CALL_IN_RHS = re.compile(r'\b(?!new\b)\w+\s*\.\s*\w+\s*\(|\b(?!new\b)[a-z]\w*\s*\(')
LOCAL_CLASS_PATTERN = re.compile(r'^\s*(?:(?:final|abstract|static|private|public|protected)\s+)*class\s+(\w+)\b')
SYSOUT_PATTERN = re.compile(
    r'^\s*System\s*\.(out|err)\.(print|println|printf)\s*\('
)
VAR_DECL_PATTERN = re.compile(
    r'^\s*(?:final\s+)?(?:[\w\[\]<>,\s]+)\s+(\w+)\s*='
)
ANON_CLASS_NEW_RE = re.compile(
    r'\bnew\s+[A-Za-z_][A-Za-z0-9_$.<>,\s]*\(.*\)\s*\{\s*$'
)


def is_anon_class_start(line):
    return ANON_CLASS_NEW_RE.search(line.strip()) is not None


def get_block_ranges(lines):
    stack, blocks = [], []
    for i, line in enumerate(lines):
        t = line.strip()
        for _ in range(t.count('{')):
            stack.append(i)
        for _ in range(t.count('}')):
            if stack:
                blocks.append((stack.pop(), i))
    return blocks


def all_enclosing_blocks(blocks, target_line):
    enc = [(s, e) for (s, e) in blocks if s <= target_line <= e]
    enc.sort(key=lambda x: x[1] - x[0], reverse=True)
    return enc


def has_orphan_override(lines):
    for i, line in enumerate(lines):
        if line.strip().startswith("@Override"):
            j = i - 1
            while j >= 0 and not (lines[j] or "").strip():
                j -= 1
            if j < 0:
                return True
            prev = (lines[j] or "").strip()
            if "new " not in prev and "{" not in prev:
                return True
    return False


def strip_comments(lines):
    result = []
    in_block = False
    for line in lines:
        if in_block:
            if '*/' in line:
                line = line.split('*/', 1)[1]
                in_block = False
            else:
                continue
        newline = ""
        i = 0
        in_string = False
        while i < len(line):
            ch = line[i]
            if ch == '"' and (i == 0 or line[i - 1] != '\\'):
                in_string = not in_string
                newline += ch
                i += 1
                continue
            if not in_string:
                if line.startswith("/*", i):
                    # Handle inline block comment /* ... */ on same line
                    end_idx = line.find("*/", i + 2)
                    if end_idx != -1:
                        i = end_idx + 2  # skip past */
                        continue
                    else:
                        in_block = True
                        i += 2
                        continue
                if line.startswith("//", i):
                    break
            newline += ch
            i += 1
        if newline.strip():
            result.append(newline)
    return result


def drop_logging(lines):
    """Drop System.out/err calls (including multiline) and empty logging blocks."""
    result = []
    skip_depth = 0
    for line in lines:
        if skip_depth > 0:
            skip_depth += line.count('(') - line.count(')')
            if skip_depth <= 0:
                skip_depth = 0
            continue
        if SYSOUT_PATTERN.match(line):
            depth = line.count('(') - line.count(')')
            if depth > 0:
                skip_depth = depth
            continue
        # Handle inline sysout: "if (cond) { System.out.println(...); }"
        # Drop line if the only real content (outside keyword+braces wrapper) is a sysout call
        sysout_inline = re.search(
            r'System\s*\.\s*(out|err)\s*\.\s*(print|println|printf)\s*\(', line
        )
        if sysout_inline:
            before = line[:sysout_inline.start()].strip()
            # strip away keyword block openers: "if (...) {" or "else {"
            before_clean = re.sub(r"\b(if|else|for|while)\b[^{]*\{", "", before).strip()
            if not before_clean:
                continue  # entire line was just a keyword wrapper + sysout
        result.append(line)
    lines = result

    # drop empty keyword blocks left after logging removal
    # but NEVER drop catch/finally — they must stay paired with their try
    changed = True
    ls = list(lines)
    while changed:
        changed = False
        blocks = get_block_ranges(ls)
        for (s, e) in sorted(blocks, key=lambda x: x[1] - x[0]):
            if s == e:
                continue
            interior = [ls[i].strip() for i in range(s + 1, e)
                        if ls[i].strip() and ls[i].strip() not in ('{', '}')]
            t_kw = ls[s].strip()
            # also check prev line if block opener is bare {
            if t_kw == '{':
                for k in range(s - 1, -1, -1):
                    pk = (ls[k] or '').strip()
                    if pk:
                        t_kw = pk
                        break
            if not interior and KEYWORD_BLOCK.match(t_kw):
                if t_kw.startswith('catch') or t_kw.startswith('finally'):
                    continue  # never drop catch/finally in drop_logging
                # For try blocks: check if the closer or the next line starts a catch/finally
                # with non-empty content — if so, don't drop (the catch must stay)
                if t_kw.startswith('try'):
                    closer_line = (ls[e] or '').strip()
                    has_catch = 'catch' in closer_line or 'finally' in closer_line
                    if not has_catch and e + 1 < len(ls):
                        next_line = (ls[e + 1] or '').strip()
                        has_catch = next_line.startswith('catch') or next_line.startswith('finally') or                                     ('catch' in next_line and next_line.startswith('}'))
                    if has_catch:
                        # Check if any catch/finally has non-empty content
                        j = e if ('catch' in (ls[e] or '') or 'finally' in (ls[e] or '')) else e + 1
                        while j < len(ls):
                            tj = (ls[j] or '').strip()
                            if tj.startswith('catch') or tj.startswith('finally') or                                ('catch' in tj and tj.startswith('}')):
                                catch_part = tj[tj.index('}')+1:] if tj.startswith('}') else tj
                                depth = _count_depth(catch_part)
                                k = j
                                if depth == 0:
                                    nk = k + 1
                                    while nk < len(ls) and not (ls[nk] or '').strip():
                                        nk += 1
                                    if nk < len(ls) and _count_depth((ls[nk] or '').strip()) > 0:
                                        depth = _count_depth(ls[nk])
                                        k = nk
                                while depth != 0 and k < len(ls) - 1:
                                    k += 1
                                    if ls[k]:
                                        depth += _count_depth(ls[k])
                                # Check interior of this catch block
                                catch_interior = [ls[i].strip() for i in range(j + 1, k)
                                                  if (ls[i] or '').strip() and (ls[i] or '').strip() not in ('{', '}')]
                                if catch_interior:
                                    break  # non-empty catch — don't drop the try
                                j = k + 1
                            elif not (ls[j] or '').strip():
                                j += 1
                            else:
                                break
                        else:
                            pass  # all catches empty, fall through to drop
                        if catch_interior if (j < len(ls) and ('catch' in (ls[j-1] or '') if j > 0 else False)) else False:
                            continue  # has non-empty catch, keep
                        # Simpler: just re-check
                        j2 = e if ('catch' in (ls[e] or '') or 'finally' in (ls[e] or '')) else e + 1
                        skip = False
                        while j2 < len(ls):
                            tj2 = (ls[j2] or '').strip()
                            if tj2.startswith('catch') or tj2.startswith('finally') or                                ('catch' in tj2 and tj2.startswith('}')):
                                cp = tj2[tj2.index('}')+1:] if tj2.startswith('}') else tj2
                                d = _count_depth(cp)
                                kk = j2
                                if d == 0:
                                    nk2 = kk + 1
                                    while nk2 < len(ls) and not (ls[nk2] or '').strip():
                                        nk2 += 1
                                    if nk2 < len(ls) and _count_depth((ls[nk2] or '').strip()) > 0:
                                        d = _count_depth(ls[nk2])
                                        kk = nk2
                                while d != 0 and kk < len(ls) - 1:
                                    kk += 1
                                    if ls[kk]:
                                        d += _count_depth(ls[kk])
                                ci = [ls[i].strip() for i in range(j2+1, kk)
                                      if (ls[i] or '').strip() and (ls[i] or '').strip() not in ('{','}')]
                                if ci:
                                    skip = True
                                    break
                                j2 = kk + 1
                            elif not (ls[j2] or '').strip():
                                j2 += 1
                            else:
                                break
                        if skip:
                            continue  # non-empty catch/finally — keep this try block
                for i in range(s, e + 1):
                    ls[i] = None
                changed = True
                break
        ls = [l for l in ls if l is not None]
    return ls


def remove_empty_blocks(lines):
    changed = True
    ls = list(lines)
    while changed:
        changed = False
        # drop single-line empty "try { } catch (e) { }" patterns
        EMPTY_TRY_CATCH_LINE = re.compile(r'^\s*try\s*\{\s*\}\s*(?:catch\s*\([^)]*\)\s*\{\s*\}\s*|finally\s*\{\s*\}\s*)+$')
        # drop single-line empty keyword blocks: "for (...) { }", "while (...) { }", etc.
        EMPTY_KEYWORD_LINE = re.compile(r'^\s*(?:for|while|if|else\s+if)\s*\([^)]*\)\s*\{\s*\}\s*$|^\s*else\s*\{\s*\}\s*$')
        for i in range(len(ls)):
            if ls[i] and (EMPTY_TRY_CATCH_LINE.match(ls[i] or '') or EMPTY_KEYWORD_LINE.match(ls[i] or '')):
                ls[i] = None
                changed = True

        # unwrap/drop single-line { } blocks
        for i in range(len(ls)):
            t = (ls[i] or '').strip()
            if t in ('{ }', '{}'):
                ls[i] = None
                changed = True
            elif t.startswith('{') and t.endswith('}') and len(t) > 2:
                inner = t[1:-1].strip()
                # Only unwrap if inner is a statement (ends with ;) not a data value
                if inner and inner.endswith(';'):
                    ls[i] = ls[i].replace(t, inner)
                    changed = True
        ls = [l for l in ls if l is not None]

        blocks = get_block_ranges(ls)
        for (s, e) in sorted(blocks, key=lambda x: x[1] - x[0]):
            if s == e:
                continue  # single-line block, handled above
            interior = [ls[i].strip() for i in range(s + 1, e)]
            real = [t for t in interior if t and t not in ('{', '}') and not CATCH_FINALLY_LINE.match(t)]
            if real:
                continue
            t_start = ls[s].strip()

            # bare anonymous block { }
            if t_start == '{':
                prev = None
                for k in range(s - 1, -1, -1):
                    if (ls[k] or "").strip():
                        prev = (ls[k] or "").strip()
                        break
                if prev and is_anon_class_start(prev):
                    continue
                # If the closer line has 'catch'/'finally', this is a try body —
                # fall through to the is_try_block path instead of bare-block removal
                closer_t = (ls[e] or '').strip()
                if 'catch' in closer_t or 'finally' in closer_t:
                    pass  # fall through to is_try_block handling below
                else:
                    ls[s] = ls[e] = None
                    changed = True
                    break

            # Determine the "effective" keyword for this block
            # (handles keyword on its own line with { on the next line)
            prev_nonempty = None
            for k in range(s - 1, -1, -1):
                pk = (ls[k] or "").strip()
                if pk:
                    prev_nonempty = pk
                    break

            effective_start = t_start
            if t_start == '{' and prev_nonempty:
                effective_start = prev_nonempty

            # Never drop standalone catch/finally blocks — only as part of try/catch unit
            if effective_start.startswith('catch') or effective_start.startswith('finally'):
                continue

            # try/catch — only drop if ALL parts are empty
            is_try_block = (
                effective_start.startswith('try')
                or (effective_start.startswith(')') and 'try' in effective_start)
            )
            if is_try_block:
                # The try-closer line may also contain 'catch' (e.g. "} catch (Ex e)")
                # or the catch may start on the next line
                closer = (ls[e] or '').strip()
                j = e if ('catch' in closer or 'finally' in closer) else e + 1
                catches = []
                while j < len(ls):
                    tj = (ls[j] or '').strip()
                    if tj.startswith('catch') or tj.startswith('finally') or                        ('catch' in tj and tj.startswith('}')):
                        # When tj starts with '}', that } closes the try body.
                        # Measure depth only for the catch/finally part after the }.
                        catch_part = tj[tj.index('}')+1:] if tj.startswith('}') else tj
                        depth = _count_depth(catch_part)
                        k = j
                        # depth==0 means catch body opens on a subsequent line
                        if depth == 0:
                            nk = k + 1
                            while nk < len(ls) and not (ls[nk] or '').strip():
                                nk += 1
                            if nk < len(ls) and _count_depth((ls[nk] or '').strip()) > 0:
                                depth = _count_depth(ls[nk])
                                k = nk
                        while depth != 0 and k < len(ls) - 1:
                            k += 1
                            if ls[k]:
                                depth += _count_depth(ls[k])
                        catches.append((j, k))
                        j = k + 1
                    elif not tj:
                        j += 1
                    else:
                        break
                all_empty = all(
                    not any(
                        (ls[i] or '').strip() and (ls[i] or '').strip() not in ('{', '}')
                        for i in range(cs + 1, ce)
                    )
                    for (cs, ce) in catches
                )
                if all_empty:
                    for i in range(s, e + 1):
                        ls[i] = None
                    for cs, ce in catches:
                        for i in range(cs, ce + 1):
                            ls[i] = None
                    changed = True
                    break
                continue

            # keyword block with empty body
            if KEYWORD_BLOCK.match(ls[s]):
                t_kw = ls[s].strip()
                if t_kw.startswith('catch') or t_kw.startswith('finally'):
                    continue  # only dropped as part of try/catch unit
                for i in range(s, e + 1):
                    ls[i] = None
                changed = True
                break

            # keyword was on previous line, { on this line — check effective_start
            if effective_start.startswith('if') or effective_start.startswith('for') or                effective_start.startswith('while') or effective_start.startswith('switch') or                effective_start.startswith('else'):
                # find the keyword line and drop from there
                keyword_line = s
                for k in range(s - 1, -1, -1):
                    pk = (ls[k] or "").strip()
                    if pk and KEYWORD_BLOCK.match(pk):
                        keyword_line = k
                        break
                for i in range(keyword_line, e + 1):
                    ls[i] = None
                changed = True
                break

        ls = [l for l in ls if l is not None]
    return ls


def unwrap_bare_blocks(lines):
    changed = True
    ls = list(lines)
    while changed:
        changed = False
        blocks = get_block_ranges(ls)
        for (s, e) in sorted(blocks, key=lambda x: x[1] - x[0]):
            if (ls[s] or "").strip() != '{' or (ls[e] or "").strip() != '}':
                continue
            prev = None
            for k in range(s - 1, -1, -1):
                if (ls[k] or "").strip():
                    prev = (ls[k] or "").strip()
                    break
            if prev and is_anon_class_start(prev):
                continue
            # Never unwrap if previous line is a keyword (try/if/for/while/catch/finally)
            # Also covers "} catch (...)" and "} finally" patterns
            if prev and (KEYWORD_BLOCK.match(prev) or 'catch' in prev or 'finally' in prev):
                continue
            ls[s] = ls[e] = None
            changed = True
            break
        ls = [l for l in ls if l is not None]
    return ls


# Tokens that indicate a line continues onto the next
_CONTINUATION_ENDINGS = ('+', '-', '*', '/', '%', '&&', '||', '?', ':', ',', '.')


def _count_depth(line):
    """Count net open paren/bracket/brace depth, ignoring chars inside strings."""
    depth = 0
    in_string = False
    string_char = None
    i = 0
    while i < len(line):
        ch = line[i]
        if in_string:
            if ch == '\\':
                i += 2
                continue
            if ch == string_char:
                in_string = False
        else:
            if ch in ('"', "'"):
                in_string = True
                string_char = ch
            elif ch in ('(', '[', '{'):
                depth += 1
            elif ch in (')', ']', '}'):
                depth -= 1
        i += 1
    return depth


def _line_continues(line):
    """True if line ends with an operator indicating expression continues on next line."""
    stripped = line.rstrip()
    if '//' in stripped:
        stripped = stripped[:stripped.index('//')].rstrip()
    for tok in _CONTINUATION_ENDINGS:
        if stripped.endswith(tok):
            return True
    return False


_MODIFIER_KEYWORDS = {'final', 'static', 'volatile', 'transient', 'synchronized', 'abstract', 'native'}
_TYPE_CHARS = re.compile(r'^[\w<>\[\],\s]+$')

def _is_dangling_type_fragment(t):
    """True if line looks like a dangling type/modifier with no statement content."""
    if not t:
        return False
    # Must not contain =, ;, (, ) — those indicate a real statement
    if any(ch in t for ch in ('=', ';', '(', ')')):
        return False
    # Must look like type tokens: words, <>, [], commas, spaces
    return bool(_TYPE_CHARS.match(t))


def preceding_modifier_lines(ls, i):
    """Return indices of annotation/lone-modifier/dangling-type lines immediately before line i."""
    result = []
    k = i - 1
    while k >= 0:
        t = (ls[k] or '').strip()
        if t.startswith('@'):
            result.append(k)
            k -= 1
        elif t in _MODIFIER_KEYWORDS or all(w in _MODIFIER_KEYWORDS for w in t.split()):
            result.append(k)
            k -= 1
        elif _is_dangling_type_fragment(t):
            result.append(k)
            k -= 1
        else:
            break
    return result


def continuation_lines(ls, i):
    """Return indices of continuation lines after line i (multiline declaration/call)."""
    result = []
    depth = _count_depth(ls[i])
    j = i + 1
    prev = i
    while j < len(ls) and (depth > 0 or _line_continues(ls[prev])):
        result.append(j)
        depth += _count_depth(ls[j])
        prev = j
        j += 1
    # Also grab lines starting with . (chained calls)
    while j < len(ls) and ls[j].strip().startswith('.'):
        result.append(j)
        prev = j
        j += 1
    return result


def _is_data_oriented_type(var_decl_line):
    """Returns True if the declared type is array or collection (result only useful as data)."""
    m = VAR_DECL_PATTERN.match(var_decl_line)
    if not m:
        return False
    var_end = var_decl_line.rfind(m.group(1))
    type_str = re.sub(r'\b(final|static|volatile)\b', '', var_decl_line[:var_end]).strip()
    if '[]' in type_str:
        return True
    if re.search(r'\b(List|Map|Set|Collection|ArrayList|HashMap|HashSet|LinkedList|Queue|Deque|Stack)\s*<', type_str):
        return True
    return False


def drop_unused_vars(lines):
    """Drop local var declarations whose name never appears elsewhere. Never drop anon class openers."""
    changed = True
    ls = list(lines)
    while changed:
        changed = False
        for i, line in enumerate(ls):
            m = VAR_DECL_PATTERN.match(line)
            if not m:
                continue
            # never drop lines that open a block (anon class, lambda)
            stripped = line.rstrip()
            if stripped.endswith('{') or stripped.endswith('->') or stripped.endswith('-> {'):
                continue
            var_name = m.group(1)
            # check usage excluding this line AND its continuations
            cont = continuation_lines(ls, i)
            drop_indices = {i} | set(cont)
            other = "\n".join(ls[j] for j in range(len(ls)) if j not in drop_indices)
            if not re.search(r'\b' + re.escape(var_name) + r'\b', other):
                pre = preceding_modifier_lines(ls, i)
                all_drop = drop_indices | set(pre)
                # If RHS contains a real method call, keep as bare statement
                # (unless the declared type is data-oriented: array/collection)
                eq_idx = ls[i].find('=') if ls[i] else -1
                if eq_idx != -1 and not cont and not _is_data_oriented_type(ls[i]):
                    rhs = ls[i][eq_idx+1:].strip()
                    if METHOD_CALL_IN_RHS.search(rhs):
                        # Strip leading cast expression: (Type) expr -> expr
                        cast_m = re.match(r'\(\w[\w<>, \[\]]*\)\s*', rhs)
                        if cast_m:
                            rhs = rhs[cast_m.end():]
                        # If rhs doesn't start with the method call (e.g. string concat prefix),
                        # extract from where the method call actually begins
                        if not METHOD_CALL_IN_RHS.match(rhs):
                            mc = METHOD_CALL_IN_RHS.search(rhs)
                            if mc:
                                rhs = rhs[mc.start():]
                        indent = ' ' * (len(ls[i]) - len(ls[i].lstrip()))
                        ls[i] = indent + rhs
                        ls = [l for j, l in enumerate(ls) if j not in (all_drop - {i})]
                        changed = True
                        break
                ls = [l for j, l in enumerate(ls) if j not in all_drop]
                changed = True
                break
    return ls


def safe_cleanup(kept_body):
    cleaned = remove_empty_blocks(kept_body)
    cleaned = unwrap_bare_blocks(cleaned)
    cleaned = drop_unused_vars(cleaned)
    if has_orphan_override(cleaned):
        cleaned = drop_unused_vars(list(kept_body))
    return cleaned


def split_inline_blocks(lines):
    """Split lines containing multiple ; or { } onto separate lines for cleaner parsing."""
    result = []
    for line in lines:
        stripped = line.strip()
        # If line has multiple statements separated by ; outside of strings, split them
        # For now just handle the specific case of multiple bare blocks on one line
        # e.g. "{ } { }" -> "{ }" and "{ }"
        result.append(line)
    return result


def prune_test(raw: str) -> str:
    lines = raw.split("\n")

    # 1. Find signature + annotations
    sig_line_idx = -1
    for i, line in enumerate(lines):
        if METHOD_SIG_PATTERN.match(line):
            sig_line_idx = i
            break
    if sig_line_idx == -1:
        return raw

    header_start = sig_line_idx
    for i in range(sig_line_idx - 1, -1, -1):
        if lines[i].strip().startswith('@'):
            header_start = i
        else:
            break

    body_start = sig_line_idx + 1
    body_end_idx = len(lines) - 1
    for i in range(len(lines) - 1, sig_line_idx, -1):
        if lines[i].strip() == '}':
            body_end_idx = i
            break

    # 2. Clean: strip comments, drop logging
    body_lines = strip_comments(lines[body_start:body_end_idx])
    body_lines = drop_logging(body_lines)

    # 3. Find assertion lines + all enclosing block structure
    blocks = get_block_ranges(body_lines)
    keep = set()

    for i, line in enumerate(body_lines):
        if not ASSERTION_PATTERN.search(line):
            continue
        keep.add(i)
        for (s, e) in all_enclosing_blocks(blocks, i):
            keep.add(s)
            keep.add(e)
        anc = all_enclosing_blocks(blocks, i)
        if anc:
            innermost = min(anc, key=lambda x: x[1] - x[0])
            for j in range(innermost[0], innermost[1] + 1):
                keep.add(j)

    # 3b. Force-drop entire bare { } blocks that contain no assertion
    #     (prevents duplicate var declarations after unwrapping scoped blocks)
    blocks_with_assertions = set()
    for i, line in enumerate(body_lines):
        if ASSERTION_PATTERN.search(line):
            for (s, e) in all_enclosing_blocks(blocks, i):
                blocks_with_assertions.add((s, e))

    force_drop = set()
    for (s, e) in blocks:
        if (s, e) in blocks_with_assertions:
            continue
        opener = body_lines[s].strip()
        if opener == '{':  # bare anonymous block
            prev = None
            for k in range(s - 1, -1, -1):
                if (body_lines[k] or '').strip():
                    prev = (body_lines[k] or '').strip()
                    break
            if prev and (is_anon_class_start(prev) or KEYWORD_BLOCK.match(prev)):
                continue
            # Force-drop every line in this block (step 4 will not re-add them)
            for j in range(s, e + 1):
                force_drop.add(j)
                keep.discard(j)

    # 3c. Drop unused local class definitions
    for i, line in enumerate(body_lines):
        if i in keep or i in force_drop:
            continue
        lcm = LOCAL_CLASS_PATTERN.match(line or '')
        if lcm:
            class_name = lcm.group(1)
            # Find the block this class opens
            class_block = next(((s, e) for (s, e) in blocks if s == i), None)
            if class_block is None:
                continue
            # Check if class_name is referenced outside the class block
            outer_lines = [body_lines[j] for j in range(len(body_lines))
                           if j < class_block[0] or j > class_block[1]]
            if not any(re.search(r'\b' + re.escape(class_name) + r'\b', l or '')
                       for l in outer_lines):
                # Unused local class — force-drop the entire block
                for j in range(class_block[0], class_block[1] + 1):
                    force_drop.add(j)
                    keep.discard(j)

    # 4. Keep everything EXCEPT unused local var declarations
    # First, mark which lines are continuations of a dropped declaration
    dropped_continuations = set()
    for i, line in enumerate(body_lines):
        if i in keep:
            continue
        if i in force_drop:
            continue  # already force-dropped in step 3b
        t = line.strip()
        if not t:
            continue
        m = VAR_DECL_PATTERN.match(line)
        if m:
            stripped = line.rstrip()
            if stripped.endswith('{') or stripped.endswith('->') or stripped.endswith('-> {'):
                keep.add(i)
                continue
            var_name = m.group(1)
            cont = continuation_lines(body_lines, i)
            drop_indices = {i} | set(cont)
            other = "\n".join(body_lines[j] for j in range(len(body_lines)) if j not in drop_indices)
            if not re.search(r'\b' + re.escape(var_name) + r'\b', other):
                dropped_continuations |= set(cont)
                # Also mark preceding annotation/modifier lines for dropping
                pre = preceding_modifier_lines(body_lines, i)
                force_drop.update(pre)
                for p in pre:
                    keep.discard(p)
                # If the RHS contains a real method call, keep it as a bare statement
                # (unless the declared type is data-oriented: array/collection)
                eq_idx = line.find('=')
                if eq_idx != -1:
                    rhs = line[eq_idx+1:].strip()
                    if not cont and METHOD_CALL_IN_RHS.search(rhs) and not _is_data_oriented_type(line):
                        # Strip leading cast expression: (Type) expr -> expr
                        cast_m = re.match(r'\(\w[\w<>, \[\]]*\)\s*', rhs)
                        if cast_m:
                            rhs = rhs[cast_m.end():]
                        # If rhs doesn't start with the method call (e.g. string concat prefix),
                        # extract from where the method call actually begins
                        if not METHOD_CALL_IN_RHS.match(rhs):
                            mc = METHOD_CALL_IN_RHS.search(rhs)
                            if mc:
                                rhs = rhs[mc.start():]
                        # Replace the var decl line with just the method call
                        indent = ' ' * (len(line) - len(line.lstrip()))
                        body_lines[i] = indent + rhs
                        keep.add(i)
                        continue  # keep as bare call, not as var decl
                continue  # unused — drop it and its continuations
        keep.add(i)

    # Remove any continuation lines that were part of a dropped declaration
    keep -= dropped_continuations

    # 5. Build + clean
    kept_body = [body_lines[i] for i in sorted(keep)]
    cleaned = safe_cleanup(kept_body)

    out = []
    for i in range(header_start, sig_line_idx + 1):
        out.append(lines[i])
    out.extend(cleaned)
    out.append(lines[body_end_idx])
    return "\n".join(out)


# ── paste your raw test here ──────────────────────────────────────────────────
RAW_TEST = """
@Test
    public void testBaseBorrow_1_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return;
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        assertEquals(getNthObject(keya,0),pool.borrowObject(keya),"1");
    }
"""
# ─────────────────────────────────────────────────────────────────────────────

if __name__ == "__main__":
    print("=== PRUNED TEST ===\n")
    print(prune_test(RAW_TEST))
# ─────────────────────────────────────────────────────────────────────────────

# def main():
#     root = Path(".")
#     projects_dir = root / "projects_decomposed"

#     for proj in sorted(projects_dir.iterdir()):
#         if not proj.is_dir():
#             continue

#         inp = proj / "dataset" / "inputs.csv"
#         if not inp.exists():
#             continue

#         out = proj / "dataset" / "inputs_pruned.csv"
#         print("processing", proj.name)

#         with inp.open(newline="", encoding="utf-8") as f_in, out.open("w", newline="", encoding="utf-8") as f_out:
#             reader = csv.DictReader(f_in)
#             fieldnames = list(reader.fieldnames or [])
#             writer = csv.DictWriter(f_out, fieldnames=fieldnames)
#             writer.writeheader()

#             total = 0
#             changed = 0

#             for row in reader:
#                 total += 1
#                 raw = row.get("test_prefix", "") or ""
#                 pruned = prune_test(raw)

#                 if pruned == raw:
#                     pass
#                 else:
#                     changed += 1


#                 row["test_prefix"] = pruned
#                 writer.writerow(row)

#         print("written", out, "rows", total, "changed", changed)

# if __name__ == "__main__":
#     main()