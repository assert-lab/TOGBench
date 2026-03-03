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

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.jexl3.junit.Asserter;
import java.util.Arrays;
import java.util.Date;
import org.apache.commons.jexl3.introspection.JexlMethod;
import org.apache.commons.jexl3.introspection.JexlPropertyGet;
import org.apache.commons.jexl3.introspection.JexlPropertySet;
import org.apache.commons.jexl3.introspection.JexlUberspect;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for calling methods on objects
 *
 * @since 2.0
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class MethodTest_OE25Dev extends JexlTestCase {
    private Asserter asserter;
    private static final String METHOD_STRING = "Method string";

    public MethodTest_OE25Dev() {
        super("MethodTest_OE25Dev");
    }

    public static class VarArgs {
        public String callInts() {
            final int result = -5000;
            return "Varargs:" + result;
        }

        public String callInts(final Integer... args) {
            int result = 0;
            if (args != null) {
                for (final Integer arg : args) {
                    result += arg != null ? arg : -100;
                }
            } else {
                result = -1000;
            }
            return "Varargs:" + result;
        }

        public String callMixed(final Integer fixed, final Integer... args) {
            int result = fixed;
            if (args != null) {
                for (final Integer arg : args) {
                    result += arg != null ? arg : -100;
                }
            } else {
                result -= 1000;
            }
            return "Mixed:" + result;
        }

        public String callMixed(final String mixed, final Integer... args) {
            int result = 0;
            if (args != null) {
                for (final Integer arg : args) {
                    result += arg != null ? arg : -100;
                }
            } else {
                result = -1000;
            }
            return mixed + ":" + result;
        }

        public String concat(final String... strs) {
            if (strs.length <= 0) {
                return "";
            }
            final StringBuilder strb = new StringBuilder(strs[0]);
            for (int s = 1; s < strs.length; ++s) {
                strb.append(", ");
                strb.append(strs[s]);
            }
            return strb.toString();
        }
    }

    public static class EnhancedContext extends JexlEvalContext {
        int factor = 6;
        final Map<String, Object> funcs;

        EnhancedContext(final Map<String, Object> funcs) {
            this.funcs = funcs;
        }

        @Override
        public Object resolveNamespace(final String name) {
            return funcs.get(name);
        }
    }

    public static class ContextualFunctor {
        private final EnhancedContext context;

        public ContextualFunctor(final EnhancedContext theContext) {
            context = theContext;
        }

        public int ratio(final int n) {
            context.factor -= 1;
            return n / context.factor;
        }
    }

    @Before
    @Override
    public void setUp() {
        asserter = new Asserter(JEXL);
    }

    public static class Functor {
        private boolean overKill = false;
        private String under = null;

        void setKill(final boolean ok) {
            overKill = ok;
        }

        public int ten() {
            return 10;
        }

        public int plus10(final int num) {
            return num + 10;
        }

        public static int TWENTY() {
            return 20;
        }

        public static int PLUS20(final int num) {
            return num + 20;
        }

        public static Class<?> NPEIfNull(final Object x) {
            return x.getClass();
        }

        public Object over(final String f, final int i) {
            if (overKill) {
                throw new UnsupportedOperationException("kill " + f + " + " + i);
            }
            return f + " + " + i;
        }

        public Object over(final String f, final Date g) {
            return f + " + " + g;
        }

        public Object over(final String f, final String g) {
            return f + " + " + g;
        }

        public void setUnder(final String str) {
            if (overKill) {
                throw new UnsupportedOperationException("kill " + str);
            }
            under = str;
        }

        public String getUnder() {
            if (overKill) {
                throw new UnsupportedOperationException("kill " + under);
            }
            return under;
        }
    }

    public static class FunctorOver extends Functor {

        public Object over(final Object f, final Object g) {
            return f + " + " + g;
        }
    }

    /**
     * test a simple method expression
     */

    /**
     * test some String method calls
     */

    /**
     * Ensures static methods on objects can be called.
     */

    public static class MyMath {
        public double cos(final double x) {
            return Math.cos(x);
        }
    }

    public static class Edge {
        private Edge() {
        }

        public int exec(final int arg) {
            return 1;
        }

        public int exec(final int[] arg) {
            return 20;
        }

        public int exec(final String arg) {
            return 2;
        }

        public int exec(final String... arg) {
            return 200;
        }

        public int exec(final Object args) {
            return 3;
        }

        public int exec(final Object... args) {
            return 4;
        }

        public int exec(final Boolean x, final int arg) {
            return 1;
        }

        public int exec(final Boolean x, final int[] arg) {
            return 20;
        }

        public int exec(final Boolean x, final String arg) {
            return 2;
        }

        public int exec(final Boolean x, final Object args) {
            return 3;
        }

        public int exec(final Boolean x, final Object... args) {
            return 4;
        }

        public Class<?>[] execute(final Object... args) {
            final Class<?>[] clazz = new Class<?>[args.length];
            for (int a = 0; a < args.length; ++a) {
                clazz[a] = args[a] != null ? args[a].getClass() : Void.class;
            }
            return clazz;
        }
    }

    private boolean eqExecute(final Object lhs, final Object rhs) {
        if (lhs instanceof Class<?>[] && rhs instanceof Class<?>[]) {
            final Class<?>[] lhsa = (Class<?>[]) lhs;
            final Class<?>[] rhsa = (Class<?>[]) rhs;
            return Arrays.deepEquals(lhsa, rhsa);
        }
        return false;
    }

    public static class ScriptContext extends MapContext implements JexlContext.NamespaceResolver {
        Map<String, Object> nsScript;

        ScriptContext(final Map<String, Object> ns) {
            nsScript = ns;
        }

        @Override
        public Object resolveNamespace(final String name) {
            if (name == null) {
                return this;
            }
            if ("script".equals(name)) {
                return nsScript;
            }
            if ("functor".equals(name)) {
                return (NamespaceFunctor) context -> {
                    final Map<String, Object> values = new HashMap<String, Object>();
                    if ("gin".equals(context.get("base"))) {
                        values.put("drink", "gin fizz");
                    } else {
                        values.put("drink", "champaign");
                    }
                    return values;
                };
            }
            return null;
        }
    }

    public static class ZArithmetic extends JexlArithmetic {
        public ZArithmetic(final boolean astrict) {
            super(astrict);
        }

        public int zzzz(final int z) {
            return 38 + z;
        }
    }

    public static class ZSpace {
        public int zzz(final int z) {
            return 39 + z;
        }
    }

    public static class ZContext extends MapContext {
        public ZContext(final Map<String,Object> map) {
            super(map);
        }

        public int zz(final int z) {
            return 40 + z;
        }

        public int z(final int z) {
            return 181 + z;
        }
    }


    @Test
    public void testCallMixedVarArgMethod_1_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        Assert.assertEquals("Mixed:1", test.callMixed(Integer.valueOf(1)));
    }

    @Test
    public void testCallJexlVarArgMethod_1_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        Assert.assertEquals("jexl:0", test.callMixed("jexl"));
    }

    @Test
    public void testInvoke_1_oe() throws Exception {
        Functor func = new Functor();
        Assert.assertEquals(Integer.valueOf(10), JEXL.invokeMethod(func, "ten"));
    }

    @Test
    public void testInvoke_2_oe() throws Exception {
        Functor func = new Functor();
        Assert.assertEquals(Integer.valueOf(42), JEXL.invokeMethod(func, "PLUS20", Integer.valueOf(22)));
    }

    @Test
    public void testInvoke_8_oe() throws Exception {
        Functor func = new Functor();
        try {
            JEXL.invokeMethod(func, "nonExistentMethod");
        } catch (final Exception xj0) {
        }
        try {
            JEXL.invokeMethod(func, "NPEIfNull", (Object[]) null);
        } catch (final Exception xj0) {
        }

        Object result;
        try {
            result = JEXL.invokeMethod(func, "over", "foo", 42);
        } catch (final Exception xj0) {
            result = xj0;
        }

        try {
            result = JEXL.invokeMethod(func, "over", null, null);
        } catch (final Exception xj0) {
            result = xj0;
        }

        func = new FunctorOver();
        try {
            result = JEXL.invokeMethod(func, "over", null, null);
        } catch (final Exception xj0) {
            Assert.fail("method should not have thrown!");
    }
    }

    @Test
    public void testAmbiguousInvoke_1_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        result = JEXL.invokeMethod(func, "over", "foo", 42);
        Assert.assertEquals("foo + 42", result);
    }

    @Test
    public void testAmbiguousInvoke_3_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        result = JEXL.invokeMethod(func, "over", "foo", 42);
        try {
            JEXL.invokeMethod(func, "over", "not null", null);
        } catch (final JexlException.Method xinvoke) {
            Assert.assertEquals("over(String, Object)", xinvoke.getMethodSignature());
    }
    }

    @Test
    public void testAmbiguousInvoke_5_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        result = JEXL.invokeMethod(func, "over", "foo", 42);
        try {
            JEXL.invokeMethod(func, "over", "not null", null);
        } catch (final JexlException.Method xinvoke) {
        }

        try {
            final String[] arg2 = new String[]{"more", "than", "one"};
            JEXL.invokeMethod(func, "over", "not null", arg2);
        } catch (final JexlException.Method xinvoke) {
            Assert.assertEquals("over(String, String[])", xinvoke.getMethodSignature());
    }
    }

    @Test
    public void testTryFailed_1_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        Assert.assertNotNull(method);
    }

    @Test
    public void testTryFailed_2_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        Assert.assertEquals("foo + 42", result);
    }

    @Test
    public void testTryFailed_4_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.tryInvoke("over", func, "foo", 42);
        } catch (final JexlException.TryFailed xfail) {
            Assert.assertEquals(UnsupportedOperationException.class, xfail.getCause().getClass());
    }
    }

    @Test
    public void testTryFailed_5_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.tryInvoke("over", func, "foo", 42);
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        final JexlPropertySet setter = uber.getPropertySet(func, "under", "42");
        result = setter.tryInvoke(func, "under", "42");
        Assert.assertFalse(setter.tryFailed(result));
    }

    @Test
    public void testTryFailed_6_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.tryInvoke("over", func, "foo", 42);
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        final JexlPropertySet setter = uber.getPropertySet(func, "under", "42");
        result = setter.tryInvoke(func, "under", "42");
        Assert.assertEquals("42", result);
    }

    @Test
    public void testTryFailed_7_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.tryInvoke("over", func, "foo", 42);
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        final JexlPropertySet setter = uber.getPropertySet(func, "under", "42");
        result = setter.tryInvoke(func, "under", "42");

        final JexlPropertyGet getter = uber.getPropertyGet(func, "under");
        result = getter.tryInvoke(func, "under");
        Assert.assertFalse(getter.tryFailed(result));
    }

    @Test
    public void testTryFailed_8_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.tryInvoke("over", func, "foo", 42);
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        final JexlPropertySet setter = uber.getPropertySet(func, "under", "42");
        result = setter.tryInvoke(func, "under", "42");

        final JexlPropertyGet getter = uber.getPropertyGet(func, "under");
        result = getter.tryInvoke(func, "under");
        Assert.assertEquals("42", result);
    }

    @Test
    public void testTryFailed_10_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.tryInvoke("over", func, "foo", 42);
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        final JexlPropertySet setter = uber.getPropertySet(func, "under", "42");
        result = setter.tryInvoke(func, "under", "42");

        final JexlPropertyGet getter = uber.getPropertyGet(func, "under");
        result = getter.tryInvoke(func, "under");

        func.setKill(true);
        try {
            /*result = */setter.tryInvoke(func, "under", "42");
        } catch (final JexlException.TryFailed xfail) {
            Assert.assertEquals(UnsupportedOperationException.class, xfail.getCause().getClass());
    }
    }

    @Test
    public void testTryFailed_11_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.tryInvoke("over", func, "foo", 42);
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        final JexlPropertySet setter = uber.getPropertySet(func, "under", "42");
        result = setter.tryInvoke(func, "under", "42");

        final JexlPropertyGet getter = uber.getPropertyGet(func, "under");
        result = getter.tryInvoke(func, "under");

        func.setKill(true);
        try {
            /*result = */setter.tryInvoke(func, "under", "42");
        } catch (final JexlException.TryFailed xfail) {
        }
        func.setKill(false);
        result = setter.tryInvoke(func, "under", "-42");
        Assert.assertEquals("-42", result);
    }

    @Test
    public void testTryFailed_13_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.tryInvoke("over", func, "foo", 42);
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        final JexlPropertySet setter = uber.getPropertySet(func, "under", "42");
        result = setter.tryInvoke(func, "under", "42");

        final JexlPropertyGet getter = uber.getPropertyGet(func, "under");
        result = getter.tryInvoke(func, "under");

        func.setKill(true);
        try {
            /*result = */setter.tryInvoke(func, "under", "42");
        } catch (final JexlException.TryFailed xfail) {
        }
        func.setKill(false);
        result = setter.tryInvoke(func, "under", "-42");

        func.setKill(true);
        try {
            /*result = */getter.tryInvoke(func, "under");
        } catch (final JexlException.TryFailed xfail) {
            Assert.assertEquals(UnsupportedOperationException.class, xfail.getCause().getClass());
    }
    }

    @Test
    public void testTryFailed_14_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.tryInvoke("over", func, "foo", 42);
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        final JexlPropertySet setter = uber.getPropertySet(func, "under", "42");
        result = setter.tryInvoke(func, "under", "42");

        final JexlPropertyGet getter = uber.getPropertyGet(func, "under");
        result = getter.tryInvoke(func, "under");

        func.setKill(true);
        try {
            /*result = */setter.tryInvoke(func, "under", "42");
        } catch (final JexlException.TryFailed xfail) {
        }
        func.setKill(false);
        result = setter.tryInvoke(func, "under", "-42");

        func.setKill(true);
        try {
            /*result = */getter.tryInvoke(func, "under");
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        result = getter.tryInvoke(func, "under");
        Assert.assertFalse(getter.tryFailed(result));
    }

    @Test
    public void testTryFailed_15_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlMethod method = uber.getMethod(func, "over", "foo", 42);
        result = method.tryInvoke("over", func, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.tryInvoke("over", func, "foo", 42);
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        final JexlPropertySet setter = uber.getPropertySet(func, "under", "42");
        result = setter.tryInvoke(func, "under", "42");

        final JexlPropertyGet getter = uber.getPropertyGet(func, "under");
        result = getter.tryInvoke(func, "under");

        func.setKill(true);
        try {
            /*result = */setter.tryInvoke(func, "under", "42");
        } catch (final JexlException.TryFailed xfail) {
        }
        func.setKill(false);
        result = setter.tryInvoke(func, "under", "-42");

        func.setKill(true);
        try {
            /*result = */getter.tryInvoke(func, "under");
        } catch (final JexlException.TryFailed xfail) {
        }

        func.setKill(false);
        result = getter.tryInvoke(func, "under");
        Assert.assertEquals("-42", result);
    }

    @Test
    public void testTryFailedScript_1_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlScript method = JEXL.createScript("(x, y)->{ func.over(x, y) }");
        Assert.assertNotNull(method);
    }

    @Test
    public void testTryFailedScript_2_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlScript method = JEXL.createScript("(x, y)->{ func.over(x, y) }");
        result = method.execute(ctxt, "foo", 42);
        Assert.assertEquals("foo + 42", result);
    }

    @Test
    public void testTryFailedScript_4_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlScript method = JEXL.createScript("(x, y)->{ func.over(x, y) }");
        result = method.execute(ctxt, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.execute(ctxt, "foo", 42);
        } catch (final JexlException xfail) {
            Assert.assertEquals(UnsupportedOperationException.class, xfail.getCause().getClass());
    }
    }

    @Test
    public void testTryFailedScript_5_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlScript method = JEXL.createScript("(x, y)->{ func.over(x, y) }");
        result = method.execute(ctxt, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.execute(ctxt, "foo", 42);
        } catch (final JexlException xfail) {
        }

        func.setKill(false);
        final JexlScript setter = JEXL.createScript("(x)->{ func.under = x }");
        result = setter.execute(ctxt, "42");
        Assert.assertEquals("42", result);
    }

    @Test
    public void testTryFailedScript_6_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlScript method = JEXL.createScript("(x, y)->{ func.over(x, y) }");
        result = method.execute(ctxt, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.execute(ctxt, "foo", 42);
        } catch (final JexlException xfail) {
        }

        func.setKill(false);
        final JexlScript setter = JEXL.createScript("(x)->{ func.under = x }");
        result = setter.execute(ctxt, "42");

        final JexlScript getter = JEXL.createScript("func.under");
        Assert.assertEquals("42", result);
    }

    @Test
    public void testTryFailedScript_8_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlScript method = JEXL.createScript("(x, y)->{ func.over(x, y) }");
        result = method.execute(ctxt, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.execute(ctxt, "foo", 42);
        } catch (final JexlException xfail) {
        }

        func.setKill(false);
        final JexlScript setter = JEXL.createScript("(x)->{ func.under = x }");
        result = setter.execute(ctxt, "42");

        final JexlScript getter = JEXL.createScript("func.under");

        func.setKill(true);
        try {
            /*result = */setter.execute(ctxt, "42");
        } catch (final JexlException xfail) {
            Assert.assertEquals(UnsupportedOperationException.class, xfail.getCause().getClass());
    }
    }

    @Test
    public void testTryFailedScript_9_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlScript method = JEXL.createScript("(x, y)->{ func.over(x, y) }");
        result = method.execute(ctxt, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.execute(ctxt, "foo", 42);
        } catch (final JexlException xfail) {
        }

        func.setKill(false);
        final JexlScript setter = JEXL.createScript("(x)->{ func.under = x }");
        result = setter.execute(ctxt, "42");

        final JexlScript getter = JEXL.createScript("func.under");

        func.setKill(true);
        try {
            /*result = */setter.execute(ctxt, "42");
        } catch (final JexlException xfail) {
        }
        func.setKill(false);
        result = setter.execute(ctxt, "-42");
        Assert.assertEquals("-42", result);
    }

    @Test
    public void testTryFailedScript_11_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlScript method = JEXL.createScript("(x, y)->{ func.over(x, y) }");
        result = method.execute(ctxt, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.execute(ctxt, "foo", 42);
        } catch (final JexlException xfail) {
        }

        func.setKill(false);
        final JexlScript setter = JEXL.createScript("(x)->{ func.under = x }");
        result = setter.execute(ctxt, "42");

        final JexlScript getter = JEXL.createScript("func.under");

        func.setKill(true);
        try {
            /*result = */setter.execute(ctxt, "42");
        } catch (final JexlException xfail) {
        }
        func.setKill(false);
        result = setter.execute(ctxt, "-42");

        func.setKill(true);
        try {
            /*result = */getter.execute(ctxt);
        } catch (final JexlException xfail) {
            Assert.assertEquals(UnsupportedOperationException.class, xfail.getCause().getClass());
    }
    }

    @Test
    public void testTryFailedScript_12_oe() throws Exception {
        final Functor func = new Functor();
        final JexlContext ctxt = new MapContext();
        ctxt.set("func", func);
        Object result;
        final JexlUberspect uber = JEXL.getUberspect();
        final JexlScript method = JEXL.createScript("(x, y)->{ func.over(x, y) }");
        result = method.execute(ctxt, "foo", 42);
        func.setKill(true);
        try {
            /*result = */method.execute(ctxt, "foo", 42);
        } catch (final JexlException xfail) {
        }

        func.setKill(false);
        final JexlScript setter = JEXL.createScript("(x)->{ func.under = x }");
        result = setter.execute(ctxt, "42");

        final JexlScript getter = JEXL.createScript("func.under");

        func.setKill(true);
        try {
            /*result = */setter.execute(ctxt, "42");
        } catch (final JexlException xfail) {
        }
        func.setKill(false);
        result = setter.execute(ctxt, "-42");

        func.setKill(true);
        try {
            /*result = */getter.execute(ctxt);
        } catch (final JexlException xfail) {
        }

        func.setKill(false);
        result = getter.execute(ctxt);
        Assert.assertEquals("-42", result);
    }

    @Test
    public void testTopLevelCall_1_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        funcs.put(null, new Functor());
        funcs.put("math", new MyMath());
        funcs.put("cx", ContextualFunctor.class);

        final EnhancedContext jc = new EnhancedContext(funcs);

        JexlExpression e = JEXL.createExpression("ten()");
        Object o = e.evaluate(jc);
        Assert.assertEquals("Result is not 10", new Integer(10), o);
    }

    @Test
    public void testTopLevelCall_2_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        funcs.put(null, new Functor());
        funcs.put("math", new MyMath());
        funcs.put("cx", ContextualFunctor.class);

        final EnhancedContext jc = new EnhancedContext(funcs);

        JexlExpression e = JEXL.createExpression("ten()");
        Object o = e.evaluate(jc);

        e = JEXL.createExpression("plus10(10)");
        o = e.evaluate(jc);
        Assert.assertEquals("Result is not 20", new Integer(20), o);
    }

    @Test
    public void testTopLevelCall_3_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        funcs.put(null, new Functor());
        funcs.put("math", new MyMath());
        funcs.put("cx", ContextualFunctor.class);

        final EnhancedContext jc = new EnhancedContext(funcs);

        JexlExpression e = JEXL.createExpression("ten()");
        Object o = e.evaluate(jc);

        e = JEXL.createExpression("plus10(10)");
        o = e.evaluate(jc);

        e = JEXL.createExpression("plus10(ten())");
        o = e.evaluate(jc);
        Assert.assertEquals("Result is not 20", new Integer(20), o);
    }

    @Test
    public void testTopLevelCall_4_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        funcs.put(null, new Functor());
        funcs.put("math", new MyMath());
        funcs.put("cx", ContextualFunctor.class);

        final EnhancedContext jc = new EnhancedContext(funcs);

        JexlExpression e = JEXL.createExpression("ten()");
        Object o = e.evaluate(jc);

        e = JEXL.createExpression("plus10(10)");
        o = e.evaluate(jc);

        e = JEXL.createExpression("plus10(ten())");
        o = e.evaluate(jc);

        jc.set("pi", new Double(Math.PI));
        e = JEXL.createExpression("math:cos(pi)");
        o = e.evaluate(jc);
        Assert.assertEquals(Double.valueOf(-1), o);
    }

    @Test
    public void testTopLevelCall_5_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        funcs.put(null, new Functor());
        funcs.put("math", new MyMath());
        funcs.put("cx", ContextualFunctor.class);

        final EnhancedContext jc = new EnhancedContext(funcs);

        JexlExpression e = JEXL.createExpression("ten()");
        Object o = e.evaluate(jc);

        e = JEXL.createExpression("plus10(10)");
        o = e.evaluate(jc);

        e = JEXL.createExpression("plus10(ten())");
        o = e.evaluate(jc);

        jc.set("pi", new Double(Math.PI));
        e = JEXL.createExpression("math:cos(pi)");
        o = e.evaluate(jc);

        e = JEXL.createExpression("cx:ratio(10) + cx:ratio(20)");
        o = e.evaluate(jc);
        Assert.assertEquals(Integer.valueOf(7), o);
    }

    @Test
    public void testNamespaceCall_1_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        funcs.put("func", new Functor());
        funcs.put("FUNC", Functor.class);

        JexlExpression e = JEXL.createExpression("func:ten()");
        final JexlEvalContext jc = new EnhancedContext(funcs);

        Object o = e.evaluate(jc);
        Assert.assertEquals("Result is not 10", new Integer(10), o);
    }

    @Test
    public void testNamespaceCall_2_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        funcs.put("func", new Functor());
        funcs.put("FUNC", Functor.class);

        JexlExpression e = JEXL.createExpression("func:ten()");
        final JexlEvalContext jc = new EnhancedContext(funcs);

        Object o = e.evaluate(jc);

        e = JEXL.createExpression("func:plus10(10)");
        o = e.evaluate(jc);
        Assert.assertEquals("Result is not 20", new Integer(20), o);
    }

    @Test
    public void testNamespaceCall_3_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        funcs.put("func", new Functor());
        funcs.put("FUNC", Functor.class);

        JexlExpression e = JEXL.createExpression("func:ten()");
        final JexlEvalContext jc = new EnhancedContext(funcs);

        Object o = e.evaluate(jc);

        e = JEXL.createExpression("func:plus10(10)");
        o = e.evaluate(jc);

        e = JEXL.createExpression("func:plus10(func:ten())");
        o = e.evaluate(jc);
        Assert.assertEquals("Result is not 20", new Integer(20), o);
    }

    @Test
    public void testNamespaceCall_4_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        funcs.put("func", new Functor());
        funcs.put("FUNC", Functor.class);

        JexlExpression e = JEXL.createExpression("func:ten()");
        final JexlEvalContext jc = new EnhancedContext(funcs);

        Object o = e.evaluate(jc);

        e = JEXL.createExpression("func:plus10(10)");
        o = e.evaluate(jc);

        e = JEXL.createExpression("func:plus10(func:ten())");
        o = e.evaluate(jc);

        e = JEXL.createExpression("FUNC:PLUS20(10)");
        o = e.evaluate(jc);
        Assert.assertEquals("Result is not 30", new Integer(30), o);
    }

    @Test
    public void testNamespaceCall_5_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        funcs.put("func", new Functor());
        funcs.put("FUNC", Functor.class);

        JexlExpression e = JEXL.createExpression("func:ten()");
        final JexlEvalContext jc = new EnhancedContext(funcs);

        Object o = e.evaluate(jc);

        e = JEXL.createExpression("func:plus10(10)");
        o = e.evaluate(jc);

        e = JEXL.createExpression("func:plus10(func:ten())");
        o = e.evaluate(jc);

        e = JEXL.createExpression("FUNC:PLUS20(10)");
        o = e.evaluate(jc);

        e = JEXL.createExpression("FUNC:PLUS20(FUNC:TWENTY())");
        o = e.evaluate(jc);
        Assert.assertEquals("Result is not 40", new Integer(40), o);
    }

    @Test
    public void testNamespaceCallEdge_14_oe() throws Exception {
        final java.util.Map<String, Object> funcs = new java.util.HashMap<String, Object>();
        final Edge func = new Edge();
        funcs.put("func", func);

        Object o;
        Object c;
        JexlExpression e;
        final JexlEvalContext jc = new EnhancedContext(funcs);
        try {
            for (int i = 0; i < 2; ++i) {
                e = JEXL.createExpression("func:exec([1, 2])");
                o = e.evaluate(jc);

                e = JEXL.createExpression("func:exec(1, 2)");
                o = e.evaluate(jc);

                e = JEXL.createExpression("func:exec([10.0, 20.0])");
                o = e.evaluate(jc);

                e = JEXL.createExpression("func:exec('1', 2)");
                o = e.evaluate(jc);

                e = JEXL.createExpression("func:exec(['1', '2'])");
                o = e.evaluate(jc);
                e = JEXL.createExpression("func:exec('1', '2')");
                o = e.evaluate(jc);

                e = JEXL.createExpression("func:exec(true, [1, 2])");
                o = e.evaluate(jc);

                e = JEXL.createExpression("func:exec(true, 1, 2)");
                o = e.evaluate(jc);

                e = JEXL.createExpression("func:exec(true, ['1', '2'])");
                o = e.evaluate(jc);

                e = JEXL.createExpression("func:exec(true, '1', '2')");
                o = e.evaluate(jc);

                e = JEXL.createExpression("func:execute(true, '1', '2')");
                o = e.evaluate(jc);
                c = func.execute(Boolean.TRUE, "1", "2");

                e = JEXL.createExpression("func:execute([true])");
                o = e.evaluate(jc);
                c = func.execute(new boolean[]{true});
            }
        } catch (final JexlException xjexl) {
            Assert.fail(xjexl.toString());
    }
    }

    @Test
    public void testScriptCall_1_oe() throws Exception {
        JexlContext context = new MapContext();
        final JexlScript plus = JEXL.createScript("a + b", new String[]{"a", "b"});
        context.set("plus", plus);
        JexlScript forty2 = JEXL.createScript("plus(4, 2) * plus(4, 3)");
        Object o = forty2.execute(context);
        Assert.assertEquals("Result is not 42", new Integer(42), o);
    }

    @Test
    public void testScriptCall_2_oe() throws Exception {
        JexlContext context = new MapContext();
        final JexlScript plus = JEXL.createScript("a + b", new String[]{"a", "b"});
        context.set("plus", plus);
        JexlScript forty2 = JEXL.createScript("plus(4, 2) * plus(4, 3)");
        Object o = forty2.execute(context);

        final Map<String, Object> foo = new HashMap<String, Object>();
        foo.put("plus", plus);
        context.set("foo", foo);
        forty2 = JEXL.createScript("foo.plus(4, 2) * foo.plus(4, 3)");
        o = forty2.execute(context);
        Assert.assertEquals("Result is not 42", new Integer(42), o);
    }

    @Test
    public void testScriptCall_3_oe() throws Exception {
        JexlContext context = new MapContext();
        final JexlScript plus = JEXL.createScript("a + b", new String[]{"a", "b"});
        context.set("plus", plus);
        JexlScript forty2 = JEXL.createScript("plus(4, 2) * plus(4, 3)");
        Object o = forty2.execute(context);

        final Map<String, Object> foo = new HashMap<String, Object>();
        foo.put("plus", plus);
        context.set("foo", foo);
        forty2 = JEXL.createScript("foo.plus(4, 2) * foo.plus(4, 3)");
        o = forty2.execute(context);

        context = new ScriptContext(foo);
        forty2 = JEXL.createScript("script:plus(4, 2) * script:plus(4, 3)");
        o = forty2.execute(context);
        Assert.assertEquals("Result is not 42", new Integer(42), o);
    }

    @Test
    public void testScriptCall_4_oe() throws Exception {
        JexlContext context = new MapContext();
        final JexlScript plus = JEXL.createScript("a + b", new String[]{"a", "b"});
        context.set("plus", plus);
        JexlScript forty2 = JEXL.createScript("plus(4, 2) * plus(4, 3)");
        Object o = forty2.execute(context);

        final Map<String, Object> foo = new HashMap<String, Object>();
        foo.put("plus", plus);
        context.set("foo", foo);
        forty2 = JEXL.createScript("foo.plus(4, 2) * foo.plus(4, 3)");
        o = forty2.execute(context);

        context = new ScriptContext(foo);
        forty2 = JEXL.createScript("script:plus(4, 2) * script:plus(4, 3)");
        o = forty2.execute(context);

        final JexlArithmetic ja = JEXL.getArithmetic();
        final JexlMethod mplus = new JexlMethod() {
            @Override
            public Object invoke(final Object obj, final Object ... params) throws Exception {
                if (obj instanceof Map<?, ?>) {
                    return ja.add(params[0], params[1]);
                }
                throw new Exception("not a script context");
            }

            @Override
            public Object tryInvoke(final String name, final Object obj, final Object ... params) {
                try {
                    if ("plus".equals(name)) {
                        return invoke(obj, params);
                    }
                } catch (final Exception xany) {
                }
                return this;
            }

            @Override
            public boolean tryFailed(final Object rval) {
                return rval == this;
            }

            @Override
            public boolean isCacheable() {
                return true;
            }

            @Override
            public Class<?> getReturnType() {
                return Object.class;
            }
        };

        foo.put("PLUS", mplus);
        forty2 = JEXL.createScript("script:PLUS(4, 2) * script:PLUS(4, 3)");
        o = forty2.execute(context);
        Assert.assertEquals("Result is not 42", new Integer(42), o);
    }

    @Test
    public void testScriptCall_5_oe() throws Exception {
        JexlContext context = new MapContext();
        final JexlScript plus = JEXL.createScript("a + b", new String[]{"a", "b"});
        context.set("plus", plus);
        JexlScript forty2 = JEXL.createScript("plus(4, 2) * plus(4, 3)");
        Object o = forty2.execute(context);

        final Map<String, Object> foo = new HashMap<String, Object>();
        foo.put("plus", plus);
        context.set("foo", foo);
        forty2 = JEXL.createScript("foo.plus(4, 2) * foo.plus(4, 3)");
        o = forty2.execute(context);

        context = new ScriptContext(foo);
        forty2 = JEXL.createScript("script:plus(4, 2) * script:plus(4, 3)");
        o = forty2.execute(context);

        final JexlArithmetic ja = JEXL.getArithmetic();
        final JexlMethod mplus = new JexlMethod() {
            @Override
            public Object invoke(final Object obj, final Object ... params) throws Exception {
                if (obj instanceof Map<?, ?>) {
                    return ja.add(params[0], params[1]);
                }
                throw new Exception("not a script context");
            }

            @Override
            public Object tryInvoke(final String name, final Object obj, final Object ... params) {
                try {
                    if ("plus".equals(name)) {
                        return invoke(obj, params);
                    }
                } catch (final Exception xany) {
                }
                return this;
            }

            @Override
            public boolean tryFailed(final Object rval) {
                return rval == this;
            }

            @Override
            public boolean isCacheable() {
                return true;
            }

            @Override
            public Class<?> getReturnType() {
                return Object.class;
            }
        };

        foo.put("PLUS", mplus);
        forty2 = JEXL.createScript("script:PLUS(4, 2) * script:PLUS(4, 3)");
        o = forty2.execute(context);

        context.set("foo.bar", foo);
        forty2 = JEXL.createScript("foo.'bar'.PLUS(4, 2) * foo.bar.PLUS(4, 3)");
        o = forty2.execute(context);
        Assert.assertEquals("Result is not 42", new Integer(42), o);
    }

    @Test
    public void testFizzCall_1_oe() throws Exception {
        final ScriptContext context = new ScriptContext(new HashMap<String, Object>());

        JexlScript bar = JEXL.createScript("functor:get('drink')");
        Object o;
        o = bar.execute(context);
        Assert.assertEquals("Wrong choice", "champaign", o);
    }

    @Test
    public void testFizzCall_2_oe() throws Exception {
        final ScriptContext context = new ScriptContext(new HashMap<String, Object>());

        JexlScript bar = JEXL.createScript("functor:get('drink')");
        Object o;
        o = bar.execute(context);
        context.set("base", "gin");
        o = bar.execute(context);
        Assert.assertEquals("Wrong choice", "gin fizz", o);
    }

    @Test
    public void testFizzCall_3_oe() throws Exception {
        final ScriptContext context = new ScriptContext(new HashMap<String, Object>());

        JexlScript bar = JEXL.createScript("functor:get('drink')");
        Object o;
        o = bar.execute(context);
        context.set("base", "gin");
        o = bar.execute(context);

        context.set("base", "wine");
        bar = JEXL.createScript("var glass = functor:get('drink'); base = 'gin'; functor:get('drink')");
        o = bar.execute(context);
        Assert.assertEquals("Wrong choice", "champaign", o);
    }

    @Test
    public void testVariousFunctionLocation_1_oe() throws Exception {
        final Map<String, Object> vars = new HashMap<String, Object>();
        final Map<String,Object> funcs = new HashMap<String,Object>();
        funcs.put(null, new ZSpace());
        final JexlEngine jexl = new JexlBuilder().namespaces(funcs).arithmetic(new ZArithmetic(true)).create();

        final JexlContext zjc = new ZContext(vars); // that implements a z(int x) function
        final String z41 = "z(41)";
        final JexlScript callz41 = jexl.createScript(z41);
        Object onovar = callz41.execute(zjc);
        Assert.assertEquals(222, onovar);
    }

    @Test
    public void testVariousFunctionLocation_2_oe() throws Exception {
        final Map<String, Object> vars = new HashMap<String, Object>();
        final Map<String,Object> funcs = new HashMap<String,Object>();
        funcs.put(null, new ZSpace());
        final JexlEngine jexl = new JexlBuilder().namespaces(funcs).arithmetic(new ZArithmetic(true)).create();

        final JexlContext zjc = new ZContext(vars); // that implements a z(int x) function
        final String z41 = "z(41)";
        final JexlScript callz41 = jexl.createScript(z41);
        Object onovar = callz41.execute(zjc);

        final JexlScript z241 = jexl.createScript("(x)->{ return x + 241}");
        vars.put("z", z241);
        final Object oglobal = callz41.execute(zjc);
        Assert.assertEquals(282, oglobal);
    }

    @Test
    public void testVariousFunctionLocation_3_oe() throws Exception {
        final Map<String, Object> vars = new HashMap<String, Object>();
        final Map<String,Object> funcs = new HashMap<String,Object>();
        funcs.put(null, new ZSpace());
        final JexlEngine jexl = new JexlBuilder().namespaces(funcs).arithmetic(new ZArithmetic(true)).create();

        final JexlContext zjc = new ZContext(vars); // that implements a z(int x) function
        final String z41 = "z(41)";
        final JexlScript callz41 = jexl.createScript(z41);
        Object onovar = callz41.execute(zjc);

        final JexlScript z241 = jexl.createScript("(x)->{ return x + 241}");
        vars.put("z", z241);
        final Object oglobal = callz41.execute(zjc);
        vars.remove("z");
        onovar = callz41.execute(zjc);
        Assert.assertEquals(222, onovar);
    }

    @Test
    public void testVariousFunctionLocation_4_oe() throws Exception {
        final Map<String, Object> vars = new HashMap<String, Object>();
        final Map<String,Object> funcs = new HashMap<String,Object>();
        funcs.put(null, new ZSpace());
        final JexlEngine jexl = new JexlBuilder().namespaces(funcs).arithmetic(new ZArithmetic(true)).create();

        final JexlContext zjc = new ZContext(vars); // that implements a z(int x) function
        final String z41 = "z(41)";
        final JexlScript callz41 = jexl.createScript(z41);
        Object onovar = callz41.execute(zjc);

        final JexlScript z241 = jexl.createScript("(x)->{ return x + 241}");
        vars.put("z", z241);
        final Object oglobal = callz41.execute(zjc);
        vars.remove("z");
        onovar = callz41.execute(zjc);

        final String slocal = "var z = (x)->{ return x + 141}; z(1)";
        final JexlScript jlocal = jexl.createScript(slocal);
        final Object olocal = jlocal.execute(zjc);
        Assert.assertEquals(142, olocal);
    }

    @Test
    public void testVariousFunctionLocation_5_oe() throws Exception {
        final Map<String, Object> vars = new HashMap<String, Object>();
        final Map<String,Object> funcs = new HashMap<String,Object>();
        funcs.put(null, new ZSpace());
        final JexlEngine jexl = new JexlBuilder().namespaces(funcs).arithmetic(new ZArithmetic(true)).create();

        final JexlContext zjc = new ZContext(vars); // that implements a z(int x) function
        final String z41 = "z(41)";
        final JexlScript callz41 = jexl.createScript(z41);
        Object onovar = callz41.execute(zjc);

        final JexlScript z241 = jexl.createScript("(x)->{ return x + 241}");
        vars.put("z", z241);
        final Object oglobal = callz41.execute(zjc);
        vars.remove("z");
        onovar = callz41.execute(zjc);

        final String slocal = "var z = (x)->{ return x + 141}; z(1)";
        final JexlScript jlocal = jexl.createScript(slocal);
        final Object olocal = jlocal.execute(zjc);

        Assert.assertEquals(42, jexl.createScript("zz(2)").execute(zjc));
    }

    @Test
    public void testVariousFunctionLocation_6_oe() throws Exception {
        final Map<String, Object> vars = new HashMap<String, Object>();
        final Map<String,Object> funcs = new HashMap<String,Object>();
        funcs.put(null, new ZSpace());
        final JexlEngine jexl = new JexlBuilder().namespaces(funcs).arithmetic(new ZArithmetic(true)).create();

        final JexlContext zjc = new ZContext(vars); // that implements a z(int x) function
        final String z41 = "z(41)";
        final JexlScript callz41 = jexl.createScript(z41);
        Object onovar = callz41.execute(zjc);

        final JexlScript z241 = jexl.createScript("(x)->{ return x + 241}");
        vars.put("z", z241);
        final Object oglobal = callz41.execute(zjc);
        vars.remove("z");
        onovar = callz41.execute(zjc);

        final String slocal = "var z = (x)->{ return x + 141}; z(1)";
        final JexlScript jlocal = jexl.createScript(slocal);
        final Object olocal = jlocal.execute(zjc);

        Assert.assertEquals(42, jexl.createScript("zzz(3)").execute(zjc));
    }

    @Test
    public void testVariousFunctionLocation_7_oe() throws Exception {
        final Map<String, Object> vars = new HashMap<String, Object>();
        final Map<String,Object> funcs = new HashMap<String,Object>();
        funcs.put(null, new ZSpace());
        final JexlEngine jexl = new JexlBuilder().namespaces(funcs).arithmetic(new ZArithmetic(true)).create();

        final JexlContext zjc = new ZContext(vars); // that implements a z(int x) function
        final String z41 = "z(41)";
        final JexlScript callz41 = jexl.createScript(z41);
        Object onovar = callz41.execute(zjc);

        final JexlScript z241 = jexl.createScript("(x)->{ return x + 241}");
        vars.put("z", z241);
        final Object oglobal = callz41.execute(zjc);
        vars.remove("z");
        onovar = callz41.execute(zjc);

        final String slocal = "var z = (x)->{ return x + 141}; z(1)";
        final JexlScript jlocal = jexl.createScript(slocal);
        final Object olocal = jlocal.execute(zjc);

        Assert.assertEquals(42, jexl.createScript("zzzz(4)").execute(zjc));
    }

@Test
    public void testCallVarArgMethod_1_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callInts()", test.callInts());
    }

@Test
    public void testCallVarArgMethod_2_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callInts(1)", test.callInts(1));
    }

@Test
    public void testCallVarArgMethod_3_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callInts(1,2,3,4,5)", test.callInts(1, 2, 3, 4, 5));
    }

@Test
    public void testCallVarArgMethod_4_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.concat(['1', '2', '3'])", test.concat(new String[]{"1", "2", "3"}));
    }

@Test
    public void testCallVarArgMethod_5_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.concat('1', '2', '3')", test.concat("1", "2", "3"));
    }

@Test
    public void testCallMixedVarArgMethod_2_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callMixed(1)", test.callMixed(1));
    }

@Test
    public void testCallMixedVarArgMethod_4_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callMixed(1, null)", "Mixed:-999");
    }

@Test
    public void testCallMixedVarArgMethod_5_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callMixed(1,2)", test.callMixed(1, 2));
    }

@Test
    public void testCallMixedVarArgMethod_6_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callMixed(1,2,3,4,5)", test.callMixed(1, 2, 3, 4, 5));
    }

@Test
    public void testCallJexlVarArgMethod_2_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callMixed('jexl')", "jexl:0");
    }

@Test
    public void testCallJexlVarArgMethod_4_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callMixed('jexl', null)", "jexl:-1000");
    }

@Test
    public void testCallJexlVarArgMethod_5_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callMixed('jexl', 2)", test.callMixed("jexl", 2));
    }

@Test
    public void testCallJexlVarArgMethod_6_oe() throws Exception {
        final VarArgs test = new VarArgs();
        asserter.setVariable("test", test);
        asserter.assertExpression("test.callMixed('jexl',2,3,4,5)", test.callMixed("jexl", 2, 3, 4, 5));
    }

@Test
    public void testMethod_1_oe() throws Exception {
        asserter.setVariable("foo", new Foo());
        asserter.assertExpression("foo.bar()", METHOD_STRING);
    }

@Test
    public void testMulti_1_oe() throws Exception {
        asserter.setVariable("foo", new Foo());
        asserter.assertExpression("foo.innerFoo.bar()", METHOD_STRING);
    }

@Test
    public void testStringMethods_1_oe() throws Exception {
        asserter.setVariable("foo", "abcdef");
        asserter.assertExpression("foo.substring(3)", "def");
    }

@Test
    public void testStringMethods_2_oe() throws Exception {
        asserter.setVariable("foo", "abcdef");
        asserter.assertExpression("foo.substring(0,(size(foo)-3))", "abc");
    }

@Test
    public void testStringMethods_3_oe() throws Exception {
        asserter.setVariable("foo", "abcdef");
        asserter.assertExpression("foo.substring(0,size(foo)-3)", "abc");
    }

@Test
    public void testStringMethods_4_oe() throws Exception {
        asserter.setVariable("foo", "abcdef");
        asserter.assertExpression("foo.substring(0,foo.length()-3)", "abc");
    }

@Test
    public void testStringMethods_5_oe() throws Exception {
        asserter.setVariable("foo", "abcdef");
        asserter.assertExpression("foo.substring(0, 1+1)", "ab");
    }

@Test
    public void testStaticMethodInvocation_1_oe() throws Exception {
        asserter.setVariable("aBool", Boolean.FALSE);
        asserter.assertExpression("aBool.valueOf('true')", Boolean.TRUE);
    }

@Test
    public void testStaticMethodInvocationOnClasses_1_oe() throws Exception {
        asserter.setVariable("Boolean", Boolean.class);
        asserter.assertExpression("Boolean.valueOf('true')", Boolean.TRUE);
    }

}
