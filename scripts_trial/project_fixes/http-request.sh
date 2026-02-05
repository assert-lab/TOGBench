#!/usr/bin/env bash
set -euo pipefail

# --- Always run from OE25-DEV repo root (no matter where you launch from) ---
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/../.." && pwd)"   # projects_fixes -> scripts_trial -> OE25-DEV
cd "$REPO_ROOT"

python3 - <<'PY'
import csv
import re
from pathlib import Path

# IMPORTANT: only fix THIS project
PROJECT = "http-request"
DATASET_DIR = Path("projects_decomposed") / PROJECT / "dataset"

METHOD_NAME_RE = re.compile(r"\bpublic\s+void\s+([A-Za-z0-9_]+)\s*\(")

DELETE_METHODS = {
    "malformedStringUrlCause_1_oe",

    "getUrlEncodedWithSpace_2_oe",
    "getUrlEncodedWithUnicode_2_oe",
    "deleteEmpty_4_oe",
    "deleteUrlEmpty_4_oe",
    "optionsEmpty_4_oe",
    "optionsUrlEmpty_4_oe",
    "headEmpty_4_oe",
    "headUrlEmpty_4_oe",
    "putEmpty_4_oe",
    "putUrlEmpty_4_oe",
    "traceEmpty_4_oe",
    "traceUrlEmpty_4_oe",
    "postForm_3_oe",
    "basicAuthentication_2_oe",
    "basicAuthentication_3_oe",
    "basicProxyAuthentication_4_oe",
    "basicProxyAuthentication_5_oe",
    "sendWithWriter_2_oe",
    "requestContentType_2_oe",
    "requestContentTypeNullCharset_2_oe",
    "requestContentTypeEmptyCharset_2_oe",
    "headers_2_oe",
    "headers_3_oe",
    "numberHeader_2_oe",
    "numberHeader_3_oe",
    "userAgentHeader_2_oe",
    "acceptHeader_2_oe",
    "acceptJson_2_oe",
    "ifNoneMatchHeader_2_oe",
    "acceptCharsetHeader_2_oe",
    "acceptEncodingHeader_2_oe",
    "ifModifiedSinceHeader_2_oe",
    "refererHeader_2_oe",

    "postMultipart_3_oe",
    "postMultipart_4_oe",
    "postMultipart_5_oe",
    "postMultipart_6_oe",
    "postMultipart_7_oe",
    "postMultipartWithContentType_2_oe",
    "postMultipartWithContentType_3_oe",

    "receiveAppendable_3_oe",
    "receiveWriter_3_oe",
    "receivePrintStream_3_oe",
    "receiveFile_3_oe",

    "postWithMappedQueryParams_2_oe",
    "postWithMappedQueryParams_3_oe",
    "postWithMappedQueryParams_4_oe",
    "postWithVaragsQueryParams_2_oe",
    "postWithVaragsQueryParams_3_oe",
    "postWithVaragsQueryParams_4_oe",
    "postWithEscapedMappedQueryParams_2_oe",
    "postWithEscapedMappedQueryParams_3_oe",
    "postWithEscapedMappedQueryParams_4_oe",

    "getWithMappedQueryParams_2_oe",
    "getWithMappedQueryParams_3_oe",
    "getWithMappedQueryParams_4_oe",
    "getWithVarargsQueryParams_2_oe",
    "getWithVarargsQueryParams_3_oe",
    "getWithVarargsQueryParams_4_oe",
    "getWithEscapedMappedQueryParams_2_oe",
    "getWithEscapedMappedQueryParams_3_oe",
    "getWithEscapedMappedQueryParams_4_oe",
    "getWithEscapedVarargsQueryParams_2_oe",
    "getWithEscapedVarargsQueryParams_3_oe",
    "getWithEscapedVarargsQueryParams_4_oe",

    "deleteWithMappedQueryParams_2_oe",
    "deleteWithMappedQueryParams_3_oe",
    "deleteWithMappedQueryParams_4_oe",
    "deleteWithVarargsQueryParams_2_oe",
    "deleteWithVarargsQueryParams_3_oe",
    "deleteWithVarargsQueryParams_4_oe",
    "deleteWithEscapedMappedQueryParams_2_oe",
    "deleteWithEscapedVarargsQueryParams_2_oe",

    "putWithMappedQueryParams_2_oe",
    "putWithMappedQueryParams_3_oe",
    "putWithMappedQueryParams_4_oe",
    "putWithEscapedMappedQueryParams_2_oe",
    "putWithEscapedMappedQueryParams_3_oe",
    "putWithEscapedVarargsQueryParams_2_oe",
    "putWithEscapedVarargsQueryParams_3_oe",
    "putWithEscapedVarargsQueryParams_4_oe",

    "headWithVaragsQueryParams_2_oe",
    "headWithVaragsQueryParams_3_oe",
    "headWithVaragsQueryParams_4_oe",
    "headWithEscapedMappedQueryParams_2_oe",
    "headWithEscapedVarargsQueryParams_2_oe",
    "headWithEscapedVarargsQueryParams_3_oe",
    "headWithEscapedVarargsQueryParams_4_oe",

    "sendReceiveWithoutCode_2_oe",
    "sendDateHeaderWithoutCode_2_oe",
    "sendIntHeaderWithoutCode_2_oe",

    "uploadProgressSend_3_oe",
    "uploadProgressSendInputStream_3_oe",
    "uploadProgressSendByteArray_3_oe",
    "uploadProgressSendReader_3_oe",
}

SEMICOLON_FIX_METHODS = {
    "receiveAppendable_1_oe",
    "postMultipart_1_oe",
    "receiveWriter_1_oe",
    "receiveFile_1_oe",
    "receivePrintStream_1_oe",
    "sendErrorReadStream_1_oe",
    "sendErrorReadStream_3_oe",
    "sendErrorCloseStream_1_oe",
    "getToOutputBody_1_oe",
    "getToOutputBodyWithCharset_1_oe",
    "uploadProgressSend_1_oe",
    "uploadProgressSend_2_oe",
    "uploadProgressSendInputStream_1_oe",
    "uploadProgressSendInputStream_2_oe",
    "uploadProgressSendReader_2_oe",
    "uploadProgressSendByteArray_1_oe",
    "uploadProgressSendByteArray_2_oe",
    "uploadProgressSendReader_1_oe",
}

REPL_SEND_ERROR_READ_STREAM_2 = """  @Test
  public void sendErrorReadStream_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        try {
          response.getWriter().print("content");
        } catch (IOException e) {
          // removed other assertion
        }
      }
    };
    final IOException readCause = new IOException();
    final IOException closeCause = new IOException();
    InputStream stream = new InputStream() {

      public int read() throws IOException {
        throw readCause;
      }

      public void close() throws IOException {
        throw closeCause;
      }
    };
    try {
      post(url).send(stream);
      fail("Exception not thrown");
    } catch (HttpRequestException e) {
      assertEquals(readCause, e.getCause());
    }
  }
"""

REPL_SEND_ERROR_CLOSE_STREAM_2 = """  @Test
  public void sendErrorCloseStream_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        try {
          response.getWriter().print("content");
        } catch (IOException e) {
          // removed other assertion
        }
      }
    };
    final IOException closeCause = new IOException();
    InputStream stream = new InputStream() {

      public int read() throws IOException {
        return -1;
      }

      public void close() throws IOException {
        throw closeCause;
      }
    };
    try {
      post(url).ignoreCloseExceptions(false).send(stream);
      fail("Exception not thrown");
    } catch (HttpRequestException e) {
      assertEquals(closeCause, e.getCause());
    }
  }
"""

def get_method_name(test_prefix: str) -> str:
    m = METHOD_NAME_RE.search(test_prefix or "")
    return m.group(1) if m else ""

def add_semicolon_second_last_nonempty_line(method_text: str) -> str:
    lines = (method_text or "").splitlines()
    idxs = [i for i, ln in enumerate(lines) if ln.strip() != ""]
    if len(idxs) < 2:
        return method_text
    second_last = idxs[-2]
    s = lines[second_last].rstrip()
    # typical corruption: line is "}" but should be "};"
    if s == "}":
        indent = lines[second_last][:len(lines[second_last]) - len(lines[second_last].lstrip())]
        lines[second_last] = indent + "};"
    return "\n".join(lines)

def read_csv(path: Path):
    with path.open("r", newline="", encoding="utf-8") as f:
        r = csv.DictReader(f)
        return (r.fieldnames or []), list(r)

def write_csv(path: Path, fieldnames, rows):
    with path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        w.writeheader()
        if rows:
            w.writerows(rows)

def append_csv(path: Path, fieldnames, rows):
    if not rows:
        return
    exists = path.exists()
    mode = "a" if exists else "w"
    with path.open(mode, newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=fieldnames, lineterminator="\n")
        if not exists:
            w.writeheader()
        w.writerows(rows)

def process_dataset(dataset_dir: Path):
    inputs_path = dataset_dir / "inputs.csv"
    meta_path = dataset_dir / "meta.csv"
    if not inputs_path.exists() or not meta_path.exists():
        raise SystemExit(f"Expected: {inputs_path} and {meta_path}")

    in_fields, in_rows = read_csv(inputs_path)
    meta_fields, meta_rows = read_csv(meta_path)

    if "id" not in in_fields or "test_prefix" not in in_fields:
        raise SystemExit("inputs.csv must contain columns: id, test_prefix")
    if "id" not in meta_fields:
        raise SystemExit("meta.csv must contain column: id")

    meta_by_id = {r.get("id", ""): r for r in meta_rows if r.get("id")}

    kept_inputs, kept_meta = [], []
    failed_inputs, failed_meta = [], []

    replaced = 0
    semicolon_fixed = 0
    removed = 0

    for row in in_rows:
        tid = (row.get("id") or "").strip()
        prefix = row.get("test_prefix") or ""
        mname = get_method_name(prefix)

        if mname in DELETE_METHODS:
            removed += 1
            failed_inputs.append(row)
            mr = meta_by_id.get(tid)
            if mr is not None:
                failed_meta.append(mr)
            continue

        if mname == "sendErrorReadStream_2_oe":
            row["test_prefix"] = REPL_SEND_ERROR_READ_STREAM_2
            replaced += 1
        elif mname == "sendErrorCloseStream_2_oe":
            row["test_prefix"] = REPL_SEND_ERROR_CLOSE_STREAM_2
            replaced += 1
        elif mname in SEMICOLON_FIX_METHODS:
            newp = add_semicolon_second_last_nonempty_line(prefix)
            if newp != prefix:
                row["test_prefix"] = newp
                semicolon_fixed += 1

        kept_inputs.append(row)
        mr = meta_by_id.get(tid)
        if mr is not None:
            kept_meta.append(mr)
        else:
            # shouldn't happen, but keep alignment safe
            failed_inputs.append(row)

    kept_ids = {r.get("id", "") for r in kept_inputs if r.get("id")}
    kept_meta = [r for r in kept_meta if r.get("id", "") in kept_ids]

    # anything not kept in meta goes to failed_meta (so meta aligns with inputs_failed too)
    removed_meta = [r for r in meta_rows if r.get("id", "") and r.get("id", "") not in kept_ids]
    failed_meta.extend(removed_meta)

    write_csv(inputs_path, in_fields, kept_inputs)
    write_csv(meta_path, meta_fields, kept_meta)

    append_csv(dataset_dir / "inputs_failed.csv", in_fields, failed_inputs)
    append_csv(dataset_dir / "meta_failed.csv", meta_fields, failed_meta)

    return removed, replaced, semicolon_fixed, len(kept_inputs), len(kept_meta), len(failed_inputs), len(failed_meta)

def main():
    res = process_dataset(DATASET_DIR)
    removed, replaced, semi, ki, km, fi, fm = res
    print(f"{PROJECT}: removed={removed} replaced={replaced} semicolon_fixed={semi} inputs_kept={ki} meta_kept={km} inputs_failed_added={fi} meta_failed_added={fm}")

if __name__ == "__main__":
    main()
PY
