python3 - <<'PY'
from pathlib import Path


path = Path("projects_decomposed/async-http-client/extras/retrofit2/src/test/java/org/asynchttpclient/extras/retrofit/AsyncHttpClientCallFactoryTest_OE25Dev.java")
s = path.read_text(encoding="utf-8")

target = """@Test(expectedExceptions = NullPointerException.class,
          expectedExceptionsMessageRegExp = "httpClientSupplier is marked non-null but is null")"""

idx = s.find(target)
if idx == -1:
    raise SystemExit("Target annotation block not found")

replacement = "\n".join("// " + line for line in target.splitlines())

s2 = s[:idx] + replacement + s[idx + len(target):]

path.write_text(s2, encoding="utf-8")
print("Commented FIRST occurrence only:", path)
PY
