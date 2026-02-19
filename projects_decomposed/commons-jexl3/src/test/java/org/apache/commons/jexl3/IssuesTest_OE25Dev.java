/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.jexl3;

import org.apache.commons.jexl3.internal.Engine;
import org.apache.commons.jexl3.internal.introspection.Uberspect;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.apache.commons.beanutils.LazyDynaMap;

/**
 * Test cases for reported issue between JEXL-1 and JEXL-100.
 */
@SuppressWarnings({"boxing", "UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class IssuesTest_OE25Dev extends JexlTestCase {
    public IssuesTest_OE25Dev() {
        super("IssuesTest_OE25Dev", null);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        // ensure jul logging is only error to avoid warning in silent mode
        java.util.logging.Logger.getLogger(JexlEngine.class.getName()).setLevel(java.util.logging.Level.SEVERE);
    }

    // JEXL-49: blocks not parsed (fixed)

    // JEXL-48: bad assignment detection
    public static class Another {
        public String name = "whatever";
        private final Boolean foo = Boolean.TRUE;

        public Boolean foo() {
            return foo;
        }

        public int goo() {
            return 100;
        }
    }

    public static class Foo {
        private final Another inner;

        Foo() {
            inner = new Another();
        }

        public Another getInner() {
            return inner;
        }
    }

    // JEXL-47: C style comments (single & multi line) (fixed in Parser.jjt)
    // JEXL-44: comments dont allow double quotes (fixed in Parser.jjt)

    // JEXL-42: NullPointerException evaluating an expression
    // fixed in JexlArithmetic by allowing add operator to deal with string, null

    // JEXL-40: failed to discover all methods (non public class implements public method)
    // fixed in ClassMap by taking newer version of populateCache from Velocity
    public static abstract class Base {
        public abstract boolean foo();
    }

    static class Derived extends Base {
        @Override
        public boolean foo() {
            return true;
        }
    }

    // JEXL-52: can be implemented by deriving Interpreter.{g,s}etAttribute; later

    // JEXL-10/JEXL-11: variable checking, null operand is error
    @Test
    public void test11() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        options.setStrict(true);

        ctxt.set("a", null);

        final String[] exprs = {
            //"10 + null",
            //"a - 10",
            //"b * 10",
            "a % b"//,
        //"1000 / a"
        };
        for (final String s : exprs) {
            try {
                final JexlExpression expr = jexl.createExpression(s);
                /* Object value = */
                expr.evaluate(ctxt);
                Assert.fail(s + " : should have failed due to null argument");
            } catch (final JexlException xjexl) {
                // expected
            }
        }
    }

    // JEXL-62

    // JEXL-87

    // JEXL-90

    // JEXL-44

    public static class fn98 {
        public String replace(final String str, final String target, final String replacement) {
            return str.replace(target, replacement);
        }
    }

    @Test
    public void test49_1_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new MapContext(vars);
        final String stmt = "a = 'b'; c = 'd';";
        final JexlScript expr = jexl.createScript(stmt);
        /* Object value = */ expr.execute(ctxt);
        Assert.assertTrue("JEXL-49 is not fixed", vars.get("a").equals("b") && vars.get("c").equals("d"));
    }

    @Test
    public void test48_2_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        // ensure errors will throw
        options.setStrict(true);
        options.setSilent(false);
        try {
            final String jexlExp = "(foo.getInner().foo() eq true) and (foo.getInner().goo() = (foo.getInner().goo()+1-1))";
            final JexlExpression e = jexl.createExpression(jexlExp);
            jc.set("foo", new Foo());
            /* Object o = */ e.evaluate(jc);
            // removed other assertion
        } catch (final JexlException.Assignment xparse) {
            final String dbg = xparse.toString();
        } catch (final JexlException xjexl) {
            Assert.fail("Should have thrown a parse exception");
    }
    }

    @Test
    public void test47_1_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);

        JexlExpression expr = jexl.createExpression("true//false\n");
        Object value = expr.evaluate(ctxt);
        Assert.assertTrue("should be true", (Boolean) value);
    }

    @Test
    public void test47_2_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);

        JexlExpression expr = jexl.createExpression("true//false\n");
        Object value = expr.evaluate(ctxt);
        // removed other assertion

        expr = jexl.createExpression("/*true*/false");
        value = expr.evaluate(ctxt);
        Assert.assertFalse("should be false", (Boolean) value);
    }

    @Test
    public void test47_3_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);

        JexlExpression expr = jexl.createExpression("true//false\n");
        Object value = expr.evaluate(ctxt);
        // removed other assertion

        expr = jexl.createExpression("/*true*/false");
        value = expr.evaluate(ctxt);
        // removed other assertion

        expr = jexl.createExpression("/*\"true\"*/false");
        value = expr.evaluate(ctxt);
        Assert.assertFalse("should be false", (Boolean) value);
    }

    @Test
    public void test42_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JxltEngine uel = jexl.createJxltEngine();
        // ensure errors will throw
        //jexl.setSilent(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        options.set(jexl);
        options.setStrict(false);
        options.setStrictArithmetic(false);
        ctxt.set("ax", "ok");

        final JxltEngine.Expression expr = uel.createExpression("${ax+(bx)}");
        final Object value = expr.evaluate(ctxt);
        Assert.assertEquals("should be ok", "ok", value);
    }

    @Test
    public void test40_1_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        options.set(jexl);
        // ensure errors will throw
        options.setSilent(false);

        ctxt.set("derived", new Derived());

        final JexlExpression expr = jexl.createExpression("derived.foo()");
        final Object value = expr.evaluate(ctxt);
        Assert.assertTrue("should be true", (Boolean) value);
    }

    @Test
    public void test52base_1_oe() throws Exception {
        final Engine jexl = (Engine) createEngine(false);
        final Uberspect uber = (Uberspect) jexl.getUberspect();
        // most likely, call will be in an Interpreter, getUberspect
        String[] names = uber.getMethodNames(Another.class);
        Assert.assertTrue("should find methods", names.length > 0);
    }

    @Test
    public void test52base_2_oe() throws Exception {
        final Engine jexl = (Engine) createEngine(false);
        final Uberspect uber = (Uberspect) jexl.getUberspect();
        // most likely, call will be in an Interpreter, getUberspect
        String[] names = uber.getMethodNames(Another.class);
        // removed other assertion
        int found = 0;
        for (final String name : names) {
            if ("foo".equals(name) || "goo".equals(name)) {
                found += 1;
            }
        }
        Assert.assertEquals("should have foo & goo", 2, found);
    }

    @Test
    public void test52base_3_oe() throws Exception {
        final Engine jexl = (Engine) createEngine(false);
        final Uberspect uber = (Uberspect) jexl.getUberspect();
        // most likely, call will be in an Interpreter, getUberspect
        String[] names = uber.getMethodNames(Another.class);
        // removed other assertion
        int found = 0;
        for (final String name : names) {
            if ("foo".equals(name) || "goo".equals(name)) {
                found += 1;
            }
        }
        // removed other assertion

        names = uber.getFieldNames(Another.class);
        Assert.assertTrue("should find fields", names.length > 0);
    }

    @Test
    public void test52base_4_oe() throws Exception {
        final Engine jexl = (Engine) createEngine(false);
        final Uberspect uber = (Uberspect) jexl.getUberspect();
        // most likely, call will be in an Interpreter, getUberspect
        String[] names = uber.getMethodNames(Another.class);
        // removed other assertion
        int found = 0;
        for (final String name : names) {
            if ("foo".equals(name) || "goo".equals(name)) {
                found += 1;
            }
        }
        // removed other assertion

        names = uber.getFieldNames(Another.class);
        // removed other assertion
        found = 0;
        for (final String name : names) {
            if ("name".equals(name)) {
                found += 1;
            }
        }
        Assert.assertEquals("should have name", 1, found);
    }

    @Test
    public void test62_1_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final MapContext vars = new MapContext();
        final JexlEvalContext ctxt = new JexlEvalContext(vars);
        final JexlOptions options = ctxt.getEngineOptions();
        options.setStrict(true);
        options.setSilent(true);// to avoid throwing JexlException on null method call

        JexlScript jscript;

        jscript = jexl.createScript("dummy.hashCode()");
        Assert.assertNull(jscript.getSourceText(),jscript.execute(ctxt));// OK;
    }

    @Test
    public void test62_2_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final MapContext vars = new MapContext();
        final JexlEvalContext ctxt = new JexlEvalContext(vars);
        final JexlOptions options = ctxt.getEngineOptions();
        options.setStrict(true);
        options.setSilent(true);// to avoid throwing JexlException on null method call

        JexlScript jscript;

        jscript = jexl.createScript("dummy.hashCode()");
        // removed other assertion
        ctxt.set("dummy","abcd");
        Assert.assertEquals(jscript.getSourceText(),Integer.valueOf("abcd".hashCode()),jscript.execute(ctxt));// OK;
    }

    @Test
    public void test62_3_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final MapContext vars = new MapContext();
        final JexlEvalContext ctxt = new JexlEvalContext(vars);
        final JexlOptions options = ctxt.getEngineOptions();
        options.setStrict(true);
        options.setSilent(true);// to avoid throwing JexlException on null method call

        JexlScript jscript;

        jscript = jexl.createScript("dummy.hashCode()");
        // removed other assertion
        ctxt.set("dummy","abcd");
        // removed other assertion
        jscript = jexl.createScript("dummy.hashCode");
        Assert.assertNull(jscript.getSourceText(),jscript.execute(ctxt));// OK;
    }

    @Test
    public void test62_4_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final MapContext vars = new MapContext();
        final JexlEvalContext ctxt = new JexlEvalContext(vars);
        final JexlOptions options = ctxt.getEngineOptions();
        options.setStrict(true);
        options.setSilent(true);// to avoid throwing JexlException on null method call

        JexlScript jscript;

        jscript = jexl.createScript("dummy.hashCode()");
        // removed other assertion
        ctxt.set("dummy","abcd");
        // removed other assertion
        jscript = jexl.createScript("dummy.hashCode");
        // removed other assertion
        JexlExpression jexpr;
        vars.clear();
        jexpr = jexl.createExpression("dummy.hashCode()");
        Assert.assertNull(jexpr.toString(),jexpr.evaluate(ctxt));// OK;
    }

    @Test
    public void test62_5_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final MapContext vars = new MapContext();
        final JexlEvalContext ctxt = new JexlEvalContext(vars);
        final JexlOptions options = ctxt.getEngineOptions();
        options.setStrict(true);
        options.setSilent(true);// to avoid throwing JexlException on null method call

        JexlScript jscript;

        jscript = jexl.createScript("dummy.hashCode()");
        // removed other assertion
        ctxt.set("dummy","abcd");
        // removed other assertion
        jscript = jexl.createScript("dummy.hashCode");
        // removed other assertion
        JexlExpression jexpr;
        vars.clear();
        jexpr = jexl.createExpression("dummy.hashCode()");
        // removed other assertion
        ctxt.set("dummy","abcd");
        Assert.assertEquals(jexpr.toString(),Integer.valueOf("abcd".hashCode()),jexpr.evaluate(ctxt));// OK;
    }

    @Test
    public void test62_6_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final MapContext vars = new MapContext();
        final JexlEvalContext ctxt = new JexlEvalContext(vars);
        final JexlOptions options = ctxt.getEngineOptions();
        options.setStrict(true);
        options.setSilent(true);// to avoid throwing JexlException on null method call

        JexlScript jscript;

        jscript = jexl.createScript("dummy.hashCode()");
        // removed other assertion
        ctxt.set("dummy","abcd");
        // removed other assertion
        jscript = jexl.createScript("dummy.hashCode");
        // removed other assertion
        JexlExpression jexpr;
        vars.clear();
        jexpr = jexl.createExpression("dummy.hashCode()");
        // removed other assertion
        ctxt.set("dummy","abcd");
        // removed other assertion
        jexpr = jexl.createExpression("dummy.hashCode");
        Assert.assertNull(jexpr.toString(), jexpr.evaluate(ctxt)); // OK;
    }

    @Test
    public void test87_1_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        final JexlExpression divide = jexl.createExpression("l / r");
        final JexlExpression modulo = jexl.createExpression("l % r");

        ctxt.set("l", java.math.BigInteger.valueOf(7));
        ctxt.set("r", java.math.BigInteger.valueOf(2));
        Assert.assertEquals(java.math.BigInteger.valueOf(3), divide.evaluate(ctxt));
    }

    @Test
    public void test87_2_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        final JexlExpression divide = jexl.createExpression("l / r");
        final JexlExpression modulo = jexl.createExpression("l % r");

        ctxt.set("l", java.math.BigInteger.valueOf(7));
        ctxt.set("r", java.math.BigInteger.valueOf(2));
        // removed other assertion
        Assert.assertTrue(jexl.getArithmetic().equals(1, modulo.evaluate(ctxt)));
    }

    @Test
    public void test87_3_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        final JexlExpression divide = jexl.createExpression("l / r");
        final JexlExpression modulo = jexl.createExpression("l % r");

        ctxt.set("l", java.math.BigInteger.valueOf(7));
        ctxt.set("r", java.math.BigInteger.valueOf(2));
        // removed other assertion
        // removed other assertion

        ctxt.set("l", java.math.BigDecimal.valueOf(7));
        ctxt.set("r", java.math.BigDecimal.valueOf(2));
        Assert.assertEquals(java.math.BigDecimal.valueOf(3.5), divide.evaluate(ctxt));
    }

    @Test
    public void test87_4_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        final JexlExpression divide = jexl.createExpression("l / r");
        final JexlExpression modulo = jexl.createExpression("l % r");

        ctxt.set("l", java.math.BigInteger.valueOf(7));
        ctxt.set("r", java.math.BigInteger.valueOf(2));
        // removed other assertion
        // removed other assertion

        ctxt.set("l", java.math.BigDecimal.valueOf(7));
        ctxt.set("r", java.math.BigDecimal.valueOf(2));
        // removed other assertion
        Assert.assertTrue(jexl.getArithmetic().equals(1, modulo.evaluate(ctxt)));
    }

    @Test
    public void test90_2_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        // ';' is necessary between expressions
        final String[] fexprs = {
            "a=3 b=4",
            "",
            "while (x) 1 if (y) 2 3"
        };
        for (final String fexpr : fexprs) {
            try {
                jexl.createScript(fexpr);
                // removed other assertion
            } catch (final JexlException xany) {
                // expected to fail in createExpression
            }
        }
        // ';' is necessary between expressions and only expressions
        final String[] exprs = {
            "if (x) {1} if (y) {2}",
            "if (x) 1 if (y) 2",
            "while (x) 1 if (y) 2 else 3",
            "for(z : [3, 4, 5]) { z } y ? 2 : 1",
            "for(z : [3, 4, 5]) { z } if (y) 2 else 1"
        };
        ctxt.set("x", Boolean.FALSE);
        ctxt.set("y", Boolean.TRUE);
        for (final String expr : exprs) {
            final JexlScript s = jexl.createScript(expr);
            Assert.assertEquals(Integer.valueOf(2), s.execute(ctxt));
    }
    }

    @Test
    public void test44_1_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        JexlScript script;
        script = jexl.createScript("'hello world!'//commented");
        Assert.assertEquals("hello world!", script.execute(ctxt));
    }

    @Test
    public void test44_2_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        JexlScript script;
        script = jexl.createScript("'hello world!'//commented");
        // removed other assertion
        script = jexl.createScript("'hello world!';//commented\n'bye...'");
        Assert.assertEquals("bye...", script.execute(ctxt));
    }

    @Test
    public void test44_3_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        JexlScript script;
        script = jexl.createScript("'hello world!'//commented");
        // removed other assertion
        script = jexl.createScript("'hello world!';//commented\n'bye...'");
        // removed other assertion
        script = jexl.createScript("'hello world!'## commented");
        Assert.assertEquals("hello world!", script.execute(ctxt));
    }

    @Test
    public void test44_4_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        JexlScript script;
        script = jexl.createScript("'hello world!'//commented");
        // removed other assertion
        script = jexl.createScript("'hello world!';//commented\n'bye...'");
        // removed other assertion
        script = jexl.createScript("'hello world!'## commented");
        // removed other assertion
        script = jexl.createScript("'hello world!';## commented\n'bye...'");
        Assert.assertEquals("bye...", script.execute(ctxt));
    }

    @Test
    public void test97_1_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        for (char v = 'a'; v <= 'z'; ++v) {
            ctxt.set(Character.toString(v), 10);
        }
        final String input
                = "(((((((((((((((((((((((((z+y)/x)*w)-v)*u)/t)-s)*r)/q)+p)-o)*n)-m)+l)*k)+j)/i)+h)*g)+f)/e)+d)-c)/b)+a)";

        JexlExpression script;
        // Make sure everything is loaded...
        final long start = System.nanoTime();
        script = jexl.createExpression(input);
        final Object value = script.evaluate(ctxt);
        Assert.assertEquals(Integer.valueOf(11), value);
    }

    @Test
    public void test97_2_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        for (char v = 'a'; v <= 'z'; ++v) {
            ctxt.set(Character.toString(v), 10);
        }
        final String input
                = "(((((((((((((((((((((((((z+y)/x)*w)-v)*u)/t)-s)*r)/q)+p)-o)*n)-m)+l)*k)+j)/i)+h)*g)+f)/e)+d)-c)/b)+a)";

        JexlExpression script;
        // Make sure everything is loaded...
        final long start = System.nanoTime();
        script = jexl.createExpression(input);
        final Object value = script.evaluate(ctxt);
        // removed other assertion
        final long end = System.nanoTime();
        final double millisec = (end - start) / 1e6;
        final double limit = 200.0; // Allow plenty of slack
        Assert.assertTrue("Expected parse to take less than " + limit + "ms, actual " + millisec, millisec < limit);
    }

    @Test
    public void test98_1_oe() throws Exception {
        final String[] exprs = {
            "fn:replace('DOMAIN\\somename', '\\\\', '\\\\\\\\')",
            "fn:replace(\"DOMAIN\\somename\", \"\\\\\", \"\\\\\\\\\")",
            "fn:replace('DOMAIN\\somename', '\\u005c', '\\u005c\\u005c')"
        };
        final Map<String, Object> funcs = new HashMap<String, Object>();
        funcs.put("fn", new fn98());
        final JexlEngine jexl = new JexlBuilder().namespaces(funcs).create();
        for (final String expr : exprs) {
            final Object value = jexl.createExpression(expr).evaluate(null);
            Assert.assertEquals(expr, "DOMAIN\\\\somename", value);
    }
    }

}
