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
import org.junit.Assert;
import org.junit.Test;

/**
 * Checks various exception handling cases.
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class ExceptionTest_OE25Dev extends JexlTestCase {
    /** create a named test */
    public ExceptionTest_OE25Dev() {
        super("ExceptionTest_OE25Dev");
    }

    public static class ThrowNPE {
        boolean doThrow = false;
        public String npe() {
            throw new NullPointerException("ThrowNPE");
        }

        public void setFail(final boolean f) {
            doThrow = f;
            if (f) {
                throw new NullPointerException("ThrowNPE/set");
            }
        }

        public boolean getFail() {
            if (doThrow) {
                throw new NullPointerException("ThrowNPE/get");
            }
            return doThrow;
        }
    }

    // Unknown vars and properties versus null operands
    // JEXL-73

    // null local vars and strict arithmetic effects

    // Unknown vars and properties versus null operands

    @Test
    public void test206() throws Exception {
        String src = "null.1 = 2; return 42";
        doTest206(src, false, false);
        doTest206(src, false, true);
        doTest206(src, true, false);
        doTest206(src, true, true);
        src = "x = null.1; return 42";
        doTest206(src, false, false);
        doTest206(src, false, true);
        doTest206(src, true, false);
        doTest206(src, true, true);
        src = "x = y.1; return 42";
        doTest206(src, false, false);
        doTest206(src, false, true);
        doTest206(src, true, false);
        doTest206(src, true, true);
    }
    private void doTest206(final String src, final boolean strict, final boolean silent) throws Exception {
        final CaptureLog l = new CaptureLog();
        final JexlContext jc = new MapContext();
        final JexlEngine jexl = new JexlBuilder().logger(l).strict(strict).silent(silent).create();
        JexlScript e;
        Object r = -1;
        e = jexl.createScript(src);
        try {
            r = e.execute(jc);
            if (strict && !silent) {
                Assert.fail("should have thrown an exception");
            }
        } catch(final JexlException xjexl) {
            if (!strict || silent) {
                Assert.fail(src + ": should not have thrown an exception");
            }
        }
        if (strict) {
            if (silent && l.count("warn") == 0) {
                Assert.fail(src + ": should have generated a warning");
            }
        } else {
            if (l.count("debug") == 0) {
                Assert.fail(src + ": should have generated a debug");
            }
            Assert.assertEquals(42, r);
        }
    }

    @Test
    public void testWrappedEx_2_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final JexlExpression e = jexl.createExpression("npe()");
        final JexlContext jc = new ObjectContext<ThrowNPE>(jexl, new ThrowNPE());
        try {
            e.evaluate(jc);
            // removed other assertion
        } catch (final JexlException xany) {
            final Throwable xth = xany.getCause();
            Assert.assertEquals(NullPointerException.class, xth.getClass());
    }
    }

    @Test
    public void testWrappedExmore_2_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final ThrowNPE npe = new ThrowNPE();
        try {
            final Object r = jexl.getProperty(npe, "foo");
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            Assert.assertNull(xth);
    }
    }

    @Test
    public void testWrappedExmore_4_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final ThrowNPE npe = new ThrowNPE();
        try {
            final Object r = jexl.getProperty(npe, "foo");
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }
        try {
            jexl.setProperty(npe, "foo", 42);
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            Assert.assertNull(xth);
    }
    }

    @Test
    public void testWrappedExmore_5_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final ThrowNPE npe = new ThrowNPE();
        try {
            final Object r = jexl.getProperty(npe, "foo");
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }
        try {
            jexl.setProperty(npe, "foo", 42);
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }

        final boolean b = (Boolean) jexl.getProperty(npe, "fail");
        Assert.assertFalse(b);
    }

    @Test
    public void testWrappedExmore_7_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final ThrowNPE npe = new ThrowNPE();
        try {
            final Object r = jexl.getProperty(npe, "foo");
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }
        try {
            jexl.setProperty(npe, "foo", 42);
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }

        final boolean b = (Boolean) jexl.getProperty(npe, "fail");
        // removed other assertion
        try {
            jexl.setProperty(npe, "fail", false);
            jexl.setProperty(npe, "fail", true);
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            Assert.assertEquals(NullPointerException.class, xth.getClass());
    }
    }

    @Test
    public void testWrappedExmore_9_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final ThrowNPE npe = new ThrowNPE();
        try {
            final Object r = jexl.getProperty(npe, "foo");
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }
        try {
            jexl.setProperty(npe, "foo", 42);
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }

        final boolean b = (Boolean) jexl.getProperty(npe, "fail");
        // removed other assertion
        try {
            jexl.setProperty(npe, "fail", false);
            jexl.setProperty(npe, "fail", true);
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }
        try {
            jexl.getProperty(npe, "fail");
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            Assert.assertEquals(NullPointerException.class, xth.getClass());
    }
    }

    @Test
    public void testWrappedExmore_11_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final ThrowNPE npe = new ThrowNPE();
        try {
            final Object r = jexl.getProperty(npe, "foo");
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }
        try {
            jexl.setProperty(npe, "foo", 42);
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }

        final boolean b = (Boolean) jexl.getProperty(npe, "fail");
        // removed other assertion
        try {
            jexl.setProperty(npe, "fail", false);
            jexl.setProperty(npe, "fail", true);
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }
        try {
            jexl.getProperty(npe, "fail");
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }

        try {
            jexl.invokeMethod(npe, "foo", 42);
            // removed other assertion
        } catch (final JexlException.Method xany) {
            final Throwable xth = xany.getCause();
            Assert.assertNull(xth);
    }
    }

    @Test
    public void testWrappedExmore_13_oe() throws Exception {
        final JexlEngine jexl = new Engine();
        final ThrowNPE npe = new ThrowNPE();
        try {
            final Object r = jexl.getProperty(npe, "foo");
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }
        try {
            jexl.setProperty(npe, "foo", 42);
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }

        final boolean b = (Boolean) jexl.getProperty(npe, "fail");
        // removed other assertion
        try {
            jexl.setProperty(npe, "fail", false);
            jexl.setProperty(npe, "fail", true);
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }
        try {
            jexl.getProperty(npe, "fail");
            // removed other assertion
        } catch (final JexlException.Property xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }

        try {
            jexl.invokeMethod(npe, "foo", 42);
            // removed other assertion
        } catch (final JexlException.Method xany) {
            final Throwable xth = xany.getCause();
            // removed other assertion
        }
        try {
            jexl.invokeMethod(npe, "npe");
            // removed other assertion
        } catch (final JexlException.Method xany) {
            final Throwable xth = xany.getCause();
            Assert.assertEquals(NullPointerException.class, xth.getClass());
    }
    }

    @Test
    public void testEx_2_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlExpression e = jexl.createExpression("c.e * 6");
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        // make unknown vars throw
        options.setStrict(true);
        // empty cotext
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xjexl) {
            final String msg = xjexl.getMessage();
            Assert.assertTrue(msg.indexOf("variable 'c.e'") > 0);
    }
    }

    @Test
    public void testEx_4_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlExpression e = jexl.createExpression("c.e * 6");
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        // make unknown vars throw
        options.setStrict(true);
        // empty cotext
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xjexl) {
            final String msg = xjexl.getMessage();
            // removed other assertion
        }

        // disallow null operands
        options.setStrictArithmetic(true);
        ctxt.set("c.e", null);
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xjexl) {
            final String msg = xjexl.getMessage();
            Assert.assertTrue(msg.indexOf("variable 'c.e'") > 0);
    }
    }

    @Test
    public void testEx_5_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlExpression e = jexl.createExpression("c.e * 6");
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        // make unknown vars throw
        options.setStrict(true);
        // empty cotext
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xjexl) {
            final String msg = xjexl.getMessage();
            // removed other assertion
        }

        // disallow null operands
        options.setStrictArithmetic(true);
        ctxt.set("c.e", null);
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xjexl) {
            final String msg = xjexl.getMessage();
            // removed other assertion
        }

        // allow null operands
        options.setStrictArithmetic(false);
        try {
            /* Object o = */ e.evaluate(ctxt);

        } catch (final JexlException xjexl) {
            Assert.fail("c.e in expr should not throw");
    }
    }

    @Test
    public void testEx_7_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlExpression e = jexl.createExpression("c.e * 6");
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        // make unknown vars throw
        options.setStrict(true);
        // empty cotext
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xjexl) {
            final String msg = xjexl.getMessage();
            // removed other assertion
        }

        // disallow null operands
        options.setStrictArithmetic(true);
        ctxt.set("c.e", null);
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xjexl) {
            final String msg = xjexl.getMessage();
            // removed other assertion
        }

        // allow null operands
        options.setStrictArithmetic(false);
        try {
            /* Object o = */ e.evaluate(ctxt);

        } catch (final JexlException xjexl) {
            // removed other assertion
        }

        // ensure c.e is not a defined property
        ctxt.set("c", "{ 'a' : 3, 'b' : 5}");
        ctxt.set("e", Integer.valueOf(2));
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException.Property xjexl) {
            final String msg = xjexl.getMessage();
            Assert.assertTrue(msg.indexOf("property 'e") > 0);
    }
    }

    @Test
    public void testExVar_2_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlScript e = jexl.createScript("(x)->{ x * 6 }");
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        // make unknown vars throw
        options.setStrict(true);
        options.setStrictArithmetic(true);
        // empty cotext
        try {
            /* Object o = */ e.execute(ctxt);
            // removed other assertion
        } catch (final JexlException xjexl) {
            final String msg = xjexl.getMessage();
            Assert.assertTrue(msg.indexOf("null") > 0);
    }
    }

    @Test
    public void testExVar_3_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlScript e = jexl.createScript("(x)->{ x * 6 }");
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        // make unknown vars throw
        options.setStrict(true);
        options.setStrictArithmetic(true);
        // empty cotext
        try {
            /* Object o = */ e.execute(ctxt);
            // removed other assertion
        } catch (final JexlException xjexl) {
            final String msg = xjexl.getMessage();
            // removed other assertion
        }

        // allow null operands
        options.setStrictArithmetic(false);
        try {
            final Object o = e.execute(ctxt, (Object) null);
        } catch (final JexlException.Variable xjexl) {
            Assert.fail("arithmetic allows null operands, should not throw");
    }
    }

    @Test
    public void testExMethod_2_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlExpression e = jexl.createExpression("c.e.foo()");
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        // make unknown vars throw
        options.setStrict(true);
        // empty cotext
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xjexl) {
            final String msg = xjexl.getMessage();
            Assert.assertTrue(msg.indexOf("variable 'c.e'") > 0);
    }
    }

    @Test
    public void testExMethod_4_oe() throws Exception {
        final JexlEngine jexl = createEngine(false);
        final JexlExpression e = jexl.createExpression("c.e.foo()");
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        // ensure errors will throw
        options.setSilent(false);
        // make unknown vars throw
        options.setStrict(true);
        // empty cotext
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xjexl) {
            final String msg = xjexl.getMessage();
            // removed other assertion
        }

        // disallow null operands
        options.setStrictArithmetic(true);
        ctxt.set("c.e", null);
        try {
            /* Object o = */ e.evaluate(ctxt);
            // removed other assertion
        } catch (final JexlException xjexl) {
            final String msg = xjexl.getMessage();
            Assert.assertTrue(msg.indexOf("variable 'c.e'") > 0);
    }
    }

}
