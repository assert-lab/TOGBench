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


import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.jexl3.internal.TemplateDebugger;
import org.apache.commons.jexl3.introspection.JexlSandbox;
import org.apache.commons.logging.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for reported issue between JEXL-200 and JEXL-299.
 */
@SuppressWarnings({"boxing", "UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class Issues200Test_OE25Dev extends JexlTestCase {
    public Issues200Test_OE25Dev() {
        super("Issues200Test_OE25Dev", null);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        // ensure jul logging is only error to avoid warning in silent mode
        java.util.logging.Logger.getLogger(JexlEngine.class.getName()).setLevel(java.util.logging.Level.SEVERE);
    }

    public static class Eval {
        private JexlEngine jexl;

        public JexlScript fn(final String src) {
            return jexl.createScript(src);
        }

        void setJexl(final JexlEngine je) {
            jexl = je;
        }
    }

    public static class T210 {
        public void npe() {
            throw new NullPointerException("NPE210");
        }
    }


    public static class JexlArithmetic224 extends JexlArithmetic {
        public JexlArithmetic224(final boolean astrict) {
            super(astrict);
        }

        protected Object nth(final Collection<?> c, int i) {
            if (c instanceof List) {
                // tell engine to use default
                return JexlEngine.TRY_FAILED;
            }
            for (final Object o : c) {
                if (i-- == 0) {
                    return o;
                }
            }
            return null;
        }

        public Object propertyGet(final Collection<?> c, final Number n) {
            return nth(c, n.intValue());
        }

        public Object arrayGet(final Collection<?> c, final Number n) {
            return nth(c, n.intValue());
        }

        public Object call(final Collection<?> c, final Number n) {
            if (c instanceof List) {
                return ((List) c).get(n.intValue());
            }
            return nth(c, n.intValue());
        }
    }

    public static class Context225 extends MapContext {
        public String bar(){
            return "bar";
        }
    }

    private static void handle(final ExecutorService pool, final JexlScript script, final Map<String, Object> payload) {
       pool.submit(() -> script.execute(new MapContext(payload)));
    }

    @Test
    public void test241() throws Exception {
        ExecutorService pool;
        final JexlScript script = new JexlBuilder().create().createScript("`${item}`");

        pool = Executors.newFixedThreadPool(4);

        final Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("item", "A");
        final Map<String, Object> m2 = new HashMap<String, Object>();
        m2.put("item", "B");

        handle(pool, script, m1);
        script.execute(new MapContext(m2));
        pool.shutdown();
    }


    @Test
    public void test243a() throws Exception {
        final JexlEngine jexl = new JexlBuilder().cache(32).create();
        final JexlScript script = jexl.createScript("while(true);");
        try {
            final JexlExpression expr = jexl.createExpression("while(true);");
            Assert.fail("should have failed!, expr do not allow 'while' statement");
        } catch (final JexlException xparse) {
            // ok
        }
    }

    public static class Foo245 {
        private Object bar = null;

        void setBar(final Object bar) {
            this.bar = bar;
        }

        public Object getBar() {
            return bar;
        }
    }


    /**
     * An iterator that implements Closeable (at least implements a close method).
     */
    public static class Iterator266 implements /*Closeable,*/ Iterator<Object> {
        private Iterator<Object> iterator;

        Iterator266(final Iterator<Object> ator) {
            iterator = ator;
        }

        @Override
        protected void finalize() throws Throwable {
            close();
            super.finalize();
        }

        //@Override
        public void close() {
            if (iterator != null) {
                Arithmetic266.closeIterator(this);
                iterator = null;
            }
        }

        @Override
        public boolean hasNext() {
            if (iterator == null) {
                return false;
            }
            final boolean n = iterator.hasNext();
            if (!n) {
                close();
            }
            return n;
        }

        @Override
        public Object next() {
            if (iterator == null) {
                throw new NoSuchElementException();
            }
            return iterator.next();
        }

        @Override
        public void remove() {
            if (iterator != null) {
                iterator.remove();
            }
        }
    }
    public static class Arithmetic266 extends JexlArithmetic {
        static final ThreadLocal<Deque<Iterator266>> TLS_FOREACH = new ThreadLocal<Deque<Iterator266>>() {
            @Override
            public Deque<Iterator266> initialValue() {
                return new LinkedList<Iterator266>();
            }
        };
        public Arithmetic266(final boolean strict) {
            super(strict);
        }

        static void closeIterator(final Iterator266 i266) {
            final Deque<Iterator266> queue = TLS_FOREACH.get();
            if (queue != null) {
                queue.remove(i266);
            }
        }

        public Iterator<?> forEach(final Iterable<?> collection) {
            final Iterator266 it266 = new Iterator266((Iterator<Object>) collection.iterator());
            final Deque<Iterator266> queue = TLS_FOREACH.get();
            queue.addFirst(it266);
            return it266;
        }

        public Iterator<?> forEach(final Map<?,?> collection) {
            return forEach(collection.values());
        }

        public void remove() {
            final Deque<Iterator266> queue = TLS_FOREACH.get();
            final Iterator266 i266 = queue.getFirst();
            if (i266 != null) {
                i266.remove();
                throw new JexlException.Continue(null);
            }
            throw new NoSuchElementException();
        }
    }

    public static class Context279 extends MapContext {
        public String identity(final String x) {
            return x;
        }
        public Number identity(final Number x) {
            return x;
        }
        public String[] spread(final String str) {
            if (str == null) {
                return null;
            }
             final String[] a = new String[str.length()];
             for(int i = 0; i < str.length(); ++i) {
                 a[i] = "" + str.charAt(i);
             }
             return a;
        }
    }

    @Test
    public void test286() {
        final String s286 = "var x = 0; for(x : 1..2){}; return x";
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        Assert.assertEquals(2, jexl.createScript(s286).execute(null));
    }

    public static class Cls298 {
        int sz = 42;

        public int size() {
            return sz;
        }

        public int size(final int x) {
            return sz + x;
        }

        public boolean isEmpty() {
            return sz <= 0;
        }
    }

    @Test
    public void test200_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> funcs = new HashMap<String, Object>();
        final Eval eval = new Eval();
        funcs.put(null, eval);
        final JexlEngine jexl = new JexlBuilder().namespaces(funcs).create();
        eval.setJexl(jexl);
        final String src = "var f = fn(\'(x)->{x + 42}\'); f(y)";
        final JexlScript s200 = jexl.createScript(src, "y");
        Assert.assertEquals(142, s200.execute(jc, 100));
    }

    @Test
    public void test200_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> funcs = new HashMap<String, Object>();
        final Eval eval = new Eval();
        funcs.put(null, eval);
        final JexlEngine jexl = new JexlBuilder().namespaces(funcs).create();
        eval.setJexl(jexl);
        final String src = "var f = fn(\'(x)->{x + 42}\'); f(y)";
        final JexlScript s200 = jexl.createScript(src, "y");
        // removed other assertion
        Assert.assertEquals(52, s200.execute(jc, 10));
    }

    @Test
    public void test200b_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlScript e = jexl.createScript("var x = 0; var f = (y)->{ x = y; }; f(42); x");
        final Object r = e.execute(jc);
        Assert.assertEquals(0, r);
    }

    @Test
    public void test209a_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlScript e = jexl.createScript("var x = new('java.util.HashMap'); x.a = ()->{return 1}; x['a']()");
        final Object r = e.execute(jc);
        Assert.assertEquals(1, r);
    }

    @Test
    public void test209b_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlScript e = jexl.createScript("var x = new('java.util.HashMap'); x['a'] = ()->{return 1}; x.a()");
        final Object r = e.execute(jc);
        Assert.assertEquals(1, r);
    }

    @Test
    public void test210_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("v210", new T210());
        final JexlEngine jexl = new JexlBuilder().strict(false).silent(false).create();
        final JexlScript e = jexl.createScript("v210.npe()");
        try {
            e.execute(jc);
            // removed other assertion
        } catch(final JexlException xjexl) {
            final Throwable th = xjexl.getCause();
            Assert.assertEquals("NPE210", th.getMessage());
    }
    }

    @Test
    public void test217_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new int[]{0, 1, 2, 42});
        JexlEngine jexl;
        JexlScript e;
        Object r;
        jexl = new JexlBuilder().strict(false).silent(false).create();
        e = jexl.createScript("foo[3]");
        r = e.execute(jc);
        Assert.assertEquals(42, r);
    }

    @Test
    public void test217_2_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new int[]{0, 1, 2, 42});
        JexlEngine jexl;
        JexlScript e;
        Object r;
        jexl = new JexlBuilder().strict(false).silent(false).create();
        e = jexl.createScript("foo[3]");
        r = e.execute(jc);
        // removed other assertion

        // cache and fail?
        jc.set("foo", new int[]{0, 1});
        options.setStrict(true);
        Assert.assertTrue(options.isStrict());
    }

    @Test
    public void test217_4_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new int[]{0, 1, 2, 42});
        JexlEngine jexl;
        JexlScript e;
        Object r;
        jexl = new JexlBuilder().strict(false).silent(false).create();
        e = jexl.createScript("foo[3]");
        r = e.execute(jc);
        // removed other assertion

        // cache and fail?
        jc.set("foo", new int[]{0, 1});
        options.setStrict(true);
        // removed other assertion
        try {
            r = e.execute(jc);
            // removed other assertion
        } catch(final JexlException xjexl) {
            final Throwable th = xjexl.getCause();
            Assert.assertEquals(ArrayIndexOutOfBoundsException.class, th.getClass());
    }
    }

    @Test
    public void test217_5_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new int[]{0, 1, 2, 42});
        JexlEngine jexl;
        JexlScript e;
        Object r;
        jexl = new JexlBuilder().strict(false).silent(false).create();
        e = jexl.createScript("foo[3]");
        r = e.execute(jc);
        // removed other assertion

        // cache and fail?
        jc.set("foo", new int[]{0, 1});
        options.setStrict(true);
        // removed other assertion
        try {
            r = e.execute(jc);
            // removed other assertion
        } catch(final JexlException xjexl) {
            final Throwable th = xjexl.getCause();
            // removed other assertion
        }
        //
        options.setStrict(false);
        r = e.execute(jc);
        Assert.assertNull("oob adverted", r);
    }

    @Test
    public void test221_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("one", 1);
        jc.set("map", map);
        final JexlEngine jexl = new JexlBuilder().cache(256).create();
        final JexlScript e = jexl.createScript("(x)->{ map[x] }");
        Object r;
        r = e.execute(jc, (Object) null);
        Assert.assertNull(r);
    }

    @Test
    public void test221_2_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("one", 1);
        jc.set("map", map);
        final JexlEngine jexl = new JexlBuilder().cache(256).create();
        final JexlScript e = jexl.createScript("(x)->{ map[x] }");
        Object r;
        r = e.execute(jc, (Object) null);
        // removed other assertion
        r = e.execute(jc, (Object) null);
        Assert.assertNull(r);
    }

    @Test
    public void test221_3_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("one", 1);
        jc.set("map", map);
        final JexlEngine jexl = new JexlBuilder().cache(256).create();
        final JexlScript e = jexl.createScript("(x)->{ map[x] }");
        Object r;
        r = e.execute(jc, (Object) null);
        // removed other assertion
        r = e.execute(jc, (Object) null);
        // removed other assertion
        r = e.execute(jc, "one");
        Assert.assertEquals(1, r);
    }

    @Test
    public void test224_1_oe() throws Exception {
        final List<String> a0 = Arrays.asList("one", "two");
        final Set<String> a1 = new TreeSet<String>(a0);
        final JexlContext jc = new MapContext();
        final JexlEngine jexl = new JexlBuilder().arithmetic(new JexlArithmetic224(true)).create();
        Object r;
        JexlScript e = jexl.createScript("(map, x)->{ map[x] }");
        r = e.execute(jc, a0, 1);
        Assert.assertEquals("two", r);
    }

    @Test
    public void test224_2_oe() throws Exception {
        final List<String> a0 = Arrays.asList("one", "two");
        final Set<String> a1 = new TreeSet<String>(a0);
        final JexlContext jc = new MapContext();
        final JexlEngine jexl = new JexlBuilder().arithmetic(new JexlArithmetic224(true)).create();
        Object r;
        JexlScript e = jexl.createScript("(map, x)->{ map[x] }");
        r = e.execute(jc, a0, 1);
        // removed other assertion
        r = e.execute(jc, a1, 1);
        Assert.assertEquals("two", r);
    }

    @Test
    public void test224_3_oe() throws Exception {
        final List<String> a0 = Arrays.asList("one", "two");
        final Set<String> a1 = new TreeSet<String>(a0);
        final JexlContext jc = new MapContext();
        final JexlEngine jexl = new JexlBuilder().arithmetic(new JexlArithmetic224(true)).create();
        Object r;
        JexlScript e = jexl.createScript("(map, x)->{ map[x] }");
        r = e.execute(jc, a0, 1);
        // removed other assertion
        r = e.execute(jc, a1, 1);
        // removed other assertion
        e = jexl.createScript("(map)->{ map.1 }");
        r = e.execute(jc, a0);
        Assert.assertEquals("two", r);
    }

    @Test
    public void test224_4_oe() throws Exception {
        final List<String> a0 = Arrays.asList("one", "two");
        final Set<String> a1 = new TreeSet<String>(a0);
        final JexlContext jc = new MapContext();
        final JexlEngine jexl = new JexlBuilder().arithmetic(new JexlArithmetic224(true)).create();
        Object r;
        JexlScript e = jexl.createScript("(map, x)->{ map[x] }");
        r = e.execute(jc, a0, 1);
        // removed other assertion
        r = e.execute(jc, a1, 1);
        // removed other assertion
        e = jexl.createScript("(map)->{ map.1 }");
        r = e.execute(jc, a0);
        // removed other assertion
        r = e.execute(jc, a1);
        Assert.assertEquals("two", r);
    }

    @Test
    public void test224_5_oe() throws Exception {
        final List<String> a0 = Arrays.asList("one", "two");
        final Set<String> a1 = new TreeSet<String>(a0);
        final JexlContext jc = new MapContext();
        final JexlEngine jexl = new JexlBuilder().arithmetic(new JexlArithmetic224(true)).create();
        Object r;
        JexlScript e = jexl.createScript("(map, x)->{ map[x] }");
        r = e.execute(jc, a0, 1);
        // removed other assertion
        r = e.execute(jc, a1, 1);
        // removed other assertion
        e = jexl.createScript("(map)->{ map.1 }");
        r = e.execute(jc, a0);
        // removed other assertion
        r = e.execute(jc, a1);
        // removed other assertion
        e = jexl.createScript("(map, x)->{ map(x) }");
        r = e.execute(jc, a0, 1);
        Assert.assertEquals("two", r);
    }

    @Test
    public void test224_6_oe() throws Exception {
        final List<String> a0 = Arrays.asList("one", "two");
        final Set<String> a1 = new TreeSet<String>(a0);
        final JexlContext jc = new MapContext();
        final JexlEngine jexl = new JexlBuilder().arithmetic(new JexlArithmetic224(true)).create();
        Object r;
        JexlScript e = jexl.createScript("(map, x)->{ map[x] }");
        r = e.execute(jc, a0, 1);
        // removed other assertion
        r = e.execute(jc, a1, 1);
        // removed other assertion
        e = jexl.createScript("(map)->{ map.1 }");
        r = e.execute(jc, a0);
        // removed other assertion
        r = e.execute(jc, a1);
        // removed other assertion
        e = jexl.createScript("(map, x)->{ map(x) }");
        r = e.execute(jc, a0, 1);
        // removed other assertion
        r = e.execute(jc, a1, 1);
        Assert.assertEquals("two", r);
    }

    @Test
    public void test225_1_oe() throws Exception {
        final Context225 df = new Context225();
        final JexlEngine jexl = new JexlBuilder().create();

        final JexlExpression expression = jexl.createExpression("bar()");
        Assert.assertEquals("bar", expression.evaluate(df));
    }

    @Test
    public void test225_2_oe() throws Exception {
        final Context225 df = new Context225();
        final JexlEngine jexl = new JexlBuilder().create();

        final JexlExpression expression = jexl.createExpression("bar()");
        // removed other assertion
        final ObjectContext<Object> context = new ObjectContext<Object>(jexl, df);
        Assert.assertEquals("bar", expression.evaluate(context));
    }

    @Test
    public void test242_1_oe() throws Exception {
        final Double a = -40.05d;
        final Double b = -8.01d;
        final Double c = a + b;
        final JexlContext context = new MapContext();
        context.set("a", a);
        context.set("b", b);
        final JexlEngine JEXL_ENGINE = new JexlBuilder().strict(true).silent(true).create();
        final JexlExpression jsp = JEXL_ENGINE.createExpression("a + b");
        final Double e = (Double) jsp.evaluate(context);
        Assert.assertEquals(Double.doubleToLongBits(e)+ " != " + Double.doubleToLongBits(c),c,e,0.0);
    }

    @Test
    public void test242_2_oe() throws Exception {
        final Double a = -40.05d;
        final Double b = -8.01d;
        final Double c = a + b;
        final JexlContext context = new MapContext();
        context.set("a", a);
        context.set("b", b);
        final JexlEngine JEXL_ENGINE = new JexlBuilder().strict(true).silent(true).create();
        final JexlExpression jsp = JEXL_ENGINE.createExpression("a + b");
        final Double e = (Double) jsp.evaluate(context);
        // removed other assertion
        Assert.assertEquals(Double.doubleToLongBits(e) + " != " + Double.doubleToLongBits(c), a + b, e, 0.0);
    }

    @Test
    public void test245_1_oe() throws Exception {
        final MapContext ctx = new MapContext();
        final Foo245 foo245 = new Foo245();
        ctx.set("foo", foo245);

        final JexlEngine engine = new JexlBuilder().strict(true).safe(false).silent(false).create();
        final JexlExpression foobar = engine.createExpression("foo.bar");
        final JexlExpression foobaz = engine.createExpression("foo.baz");
        final JexlExpression foobarbaz = engine.createExpression("foo.bar.baz");
        // add ambiguity with null & not-null
        final Object[] args = { null, 245 };
        for(final Object arg : args ){
            foo245.setBar(arg);
            // ok
            Assert.assertEquals(foo245.getBar(), foobar.evaluate(ctx));
    }
    }

    @Test
    public void test245_3_oe() throws Exception {
        final MapContext ctx = new MapContext();
        final Foo245 foo245 = new Foo245();
        ctx.set("foo", foo245);

        final JexlEngine engine = new JexlBuilder().strict(true).safe(false).silent(false).create();
        final JexlExpression foobar = engine.createExpression("foo.bar");
        final JexlExpression foobaz = engine.createExpression("foo.baz");
        final JexlExpression foobarbaz = engine.createExpression("foo.bar.baz");
        // add ambiguity with null & not-null
        final Object[] args = { null, 245 };
        for(final Object arg : args ){
            foo245.setBar(arg);
            // ok
            // removed other assertion
            // fail level 1
            try {
                foobaz.evaluate(ctx);
                // removed other assertion
            } catch(final JexlException xp) {
                Assert.assertTrue(xp instanceof JexlException.Property);
    }
    }
    }

    @Test
    public void test245_5_oe() throws Exception {
        final MapContext ctx = new MapContext();
        final Foo245 foo245 = new Foo245();
        ctx.set("foo", foo245);

        final JexlEngine engine = new JexlBuilder().strict(true).safe(false).silent(false).create();
        final JexlExpression foobar = engine.createExpression("foo.bar");
        final JexlExpression foobaz = engine.createExpression("foo.baz");
        final JexlExpression foobarbaz = engine.createExpression("foo.bar.baz");
        // add ambiguity with null & not-null
        final Object[] args = { null, 245 };
        for(final Object arg : args ){
            foo245.setBar(arg);
            // ok
            // removed other assertion
            // fail level 1
            try {
                foobaz.evaluate(ctx);
                // removed other assertion
            } catch(final JexlException xp) {
                // removed other assertion
            }
            // fail level 2
            try {
                foobarbaz.evaluate(ctx);
                // removed other assertion
            } catch(final JexlException xp) {
                Assert.assertTrue(xp instanceof JexlException.Property);
    }
    }
    }

    @Test
    public void test256_2_oe() throws Exception {
        final MapContext ctx = new MapContext() {
            @Override public void set(final String name, final Object value) {
                if ("java".equals(name)) {
                    throw new JexlException(null, "can not set " + name);
                }
                super.set(name, value);
            }
            @Override public Object get(final String name) {
                if ("java".equals(name)) {
                    return null;
                }
                return super.get(name);
            }
            @Override public boolean has(final String name) {
                if ("java".equals(name)) {
                    return false;
                }
                return super.has(name);
            }
        };
        ctx.set("java.version", 10);
        final JexlEngine engine = new JexlBuilder().strict(true).silent(false).create();
        JexlScript script;
        script = engine.createScript("java = 3");
        try {
             script.execute(ctx);
             // removed other assertion
        } catch(final JexlException xjexl) {
            // expected
        }
        script = engine.createScript("java.version");
        Assert.assertEquals(10, script.execute(ctx));
    }

    @Test
    public void test230_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().cache(4).create();
        final JexlContext ctxt = new MapContext();
        final int[] foo = {42};
        ctxt.set("fo o", foo);
        Object value;
        for (int l = 0; l < 2; ++l) {
            value = jexl.createExpression("fo\\ o[0]").evaluate(ctxt);
            Assert.assertEquals(42, value);
    }
    }

    @Test
    public void test230_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().cache(4).create();
        final JexlContext ctxt = new MapContext();
        final int[] foo = {42};
        ctxt.set("fo o", foo);
        Object value;
        for (int l = 0; l < 2; ++l) {
            value = jexl.createExpression("fo\\ o[0]").evaluate(ctxt);
            // removed other assertion
            value = jexl.createExpression("fo\\ o[0] = 43").evaluate(ctxt);
            Assert.assertEquals(43, value);
    }
    }

    @Test
    public void test230_3_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().cache(4).create();
        final JexlContext ctxt = new MapContext();
        final int[] foo = {42};
        ctxt.set("fo o", foo);
        Object value;
        for (int l = 0; l < 2; ++l) {
            value = jexl.createExpression("fo\\ o[0]").evaluate(ctxt);
            // removed other assertion
            value = jexl.createExpression("fo\\ o[0] = 43").evaluate(ctxt);
            // removed other assertion
            value = jexl.createExpression("fo\\ o.0").evaluate(ctxt);
            Assert.assertEquals(43, value);
    }
    }

    @Test
    public void test230_4_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().cache(4).create();
        final JexlContext ctxt = new MapContext();
        final int[] foo = {42};
        ctxt.set("fo o", foo);
        Object value;
        for (int l = 0; l < 2; ++l) {
            value = jexl.createExpression("fo\\ o[0]").evaluate(ctxt);
            // removed other assertion
            value = jexl.createExpression("fo\\ o[0] = 43").evaluate(ctxt);
            // removed other assertion
            value = jexl.createExpression("fo\\ o.0").evaluate(ctxt);
            // removed other assertion
            value = jexl.createExpression("fo\\ o.0 = 42").evaluate(ctxt);
            Assert.assertEquals(42, value);
    }
    }

    @Test
    public void test265_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().cache(4).create();
        final JexlContext ctxt = new MapContext();
        ctxt.set("x", 42);
        Object result;
        JexlScript script;
        try {
            script = jexl.createScript("(true) ? x : abs(1)");
        } catch (final JexlException.Parsing xparse) {
            // ambiguous, parsing fails
        }
        script = jexl.createScript("(true) ? (x) : abs(2)");
        result = script.execute(ctxt);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test265_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().cache(4).create();
        final JexlContext ctxt = new MapContext();
        ctxt.set("x", 42);
        Object result;
        JexlScript script;
        try {
            script = jexl.createScript("(true) ? x : abs(1)");
        } catch (final JexlException.Parsing xparse) {
            // ambiguous, parsing fails
        }
        script = jexl.createScript("(true) ? (x) : abs(2)");
        result = script.execute(ctxt);
        // removed other assertion
        script = jexl.createScript("(true) ? x : (abs(3))");
        result = script.execute(ctxt);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test265_3_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().cache(4).create();
        final JexlContext ctxt = new MapContext();
        ctxt.set("x", 42);
        Object result;
        JexlScript script;
        try {
            script = jexl.createScript("(true) ? x : abs(1)");
        } catch (final JexlException.Parsing xparse) {
            // ambiguous, parsing fails
        }
        script = jexl.createScript("(true) ? (x) : abs(2)");
        result = script.execute(ctxt);
        // removed other assertion
        script = jexl.createScript("(true) ? x : (abs(3))");
        result = script.execute(ctxt);
        // removed other assertion
        script = jexl.createScript("(!true) ? abs(4) : x");
        result = script.execute(ctxt);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test266_1_oe() throws Exception {
        Object result;
        JexlScript script;
        final JexlEngine jexl = new JexlBuilder().arithmetic(new Arithmetic266(true)).create();
        final JexlContext ctxt = new MapContext();

        final List<Integer> li = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5 ,6));
        ctxt.set("list", li);
        script = jexl.createScript("for (var item : list) { if (item <= 3) remove(); } return size(list)");
        result = script.execute(ctxt);
        Assert.assertEquals(3, result);
    }

    @Test
    public void test266_2_oe() throws Exception {
        Object result;
        JexlScript script;
        final JexlEngine jexl = new JexlBuilder().arithmetic(new Arithmetic266(true)).create();
        final JexlContext ctxt = new MapContext();

        final List<Integer> li = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5 ,6));
        ctxt.set("list", li);
        script = jexl.createScript("for (var item : list) { if (item <= 3) remove(); } return size(list)");
        result = script.execute(ctxt);
        // removed other assertion
        Assert.assertEquals(3, li.size());
    }

    @Test
    public void test266_3_oe() throws Exception {
        Object result;
        JexlScript script;
        final JexlEngine jexl = new JexlBuilder().arithmetic(new Arithmetic266(true)).create();
        final JexlContext ctxt = new MapContext();

        final List<Integer> li = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5 ,6));
        ctxt.set("list", li);
        script = jexl.createScript("for (var item : list) { if (item <= 3) remove(); } return size(list)");
        result = script.execute(ctxt);
        // removed other assertion
        // removed other assertion

        final Map<String, Integer> msi = new HashMap<String, Integer>();
        msi.put("a", 1);
        msi.put("b", 2);
        msi.put("c", 3);
        msi.put("d", 4);
        msi.put("e", 5);
        msi.put("f", 6);
        ctxt.set("map", msi);
        script = jexl.createScript("for (var item : map) { if (item <= 2) remove(); } return size(map)");
        result = script.execute(ctxt);
        Assert.assertEquals(4, result);
    }

    @Test
    public void test266_4_oe() throws Exception {
        Object result;
        JexlScript script;
        final JexlEngine jexl = new JexlBuilder().arithmetic(new Arithmetic266(true)).create();
        final JexlContext ctxt = new MapContext();

        final List<Integer> li = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5 ,6));
        ctxt.set("list", li);
        script = jexl.createScript("for (var item : list) { if (item <= 3) remove(); } return size(list)");
        result = script.execute(ctxt);
        // removed other assertion
        // removed other assertion

        final Map<String, Integer> msi = new HashMap<String, Integer>();
        msi.put("a", 1);
        msi.put("b", 2);
        msi.put("c", 3);
        msi.put("d", 4);
        msi.put("e", 5);
        msi.put("f", 6);
        ctxt.set("map", msi);
        script = jexl.createScript("for (var item : map) { if (item <= 2) remove(); } return size(map)");
        result = script.execute(ctxt);
        // removed other assertion
        Assert.assertEquals(4, msi.size());
    }

    @Test
    public void test267_1_oe() throws Exception {
        Object result;
        JexlScript script;
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlContext ctxt = new MapContext();
        // API declared params
        script = jexl.createScript("x + y", "x", "y");
        result = script.execute(ctxt, 20, 22);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test267_2_oe() throws Exception {
        Object result;
        JexlScript script;
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlContext ctxt = new MapContext();
        // API declared params
        script = jexl.createScript("x + y", "x", "y");
        result = script.execute(ctxt, 20, 22);
        // removed other assertion
        // script declared params
        script = jexl.createScript("(x, y)->{ x + y}");
        result = script.execute(ctxt, 22, 20);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test267_3_oe() throws Exception {
        Object result;
        JexlScript script;
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlContext ctxt = new MapContext();
        // API declared params
        script = jexl.createScript("x + y", "x", "y");
        result = script.execute(ctxt, 20, 22);
        // removed other assertion
        // script declared params
        script = jexl.createScript("(x, y)->{ x + y}");
        result = script.execute(ctxt, 22, 20);
        // removed other assertion
        // explicitly returning the lambda
        script = jexl.createScript("return (x, y)->{ x + y}");
        result = script.execute(ctxt);
        Assert.assertTrue(result instanceof JexlScript);
    }

    @Test
    public void test274_1_oe() throws Exception {
        JexlEngine jexl = new JexlBuilder().strict(true).safe(true).stackOverflow(5).create();
        final JexlContext ctxt = new MapContext();
        JexlScript script= jexl.createScript("var f = (x)->{ x > 1? x * f(x - 1) : x }; f(a)", "a");
        Object result = script.execute(ctxt, 3);
        Assert.assertEquals(6, result);
    }

    @Test
    public void test274_3_oe() throws Exception {
        JexlEngine jexl = new JexlBuilder().strict(true).safe(true).stackOverflow(5).create();
        final JexlContext ctxt = new MapContext();
        JexlScript script= jexl.createScript("var f = (x)->{ x > 1? x * f(x - 1) : x }; f(a)", "a");
        Object result = script.execute(ctxt, 3);
        // removed other assertion
        try {
            result = script.execute(ctxt, 32);
            // removed other assertion
        } catch(final JexlException.StackOverflow xstack) {
            // expected
            final String sxs = xstack.toString();
            Assert.assertTrue(sxs.contains("jexl"));
    }
    }

    @Test
    public void test274_5_oe() throws Exception {
        JexlEngine jexl = new JexlBuilder().strict(true).safe(true).stackOverflow(5).create();
        final JexlContext ctxt = new MapContext();
        JexlScript script= jexl.createScript("var f = (x)->{ x > 1? x * f(x - 1) : x }; f(a)", "a");
        Object result = script.execute(ctxt, 3);
        // removed other assertion
        try {
            result = script.execute(ctxt, 32);
            // removed other assertion
        } catch(final JexlException.StackOverflow xstack) {
            // expected
            final String sxs = xstack.toString();
            // removed other assertion
        }
        jexl = new JexlBuilder().strict(true).create();
        script= jexl.createScript("var f = (x)->{ x * f(x - 1) }; f(a)", "a");
        try {
            result = script.execute(ctxt, 32);
            // removed other assertion
        } catch(final JexlException.StackOverflow xstack) {
            // expected
            final String sxs = xstack.toString();
            Assert.assertTrue(sxs.contains("jvm"));
    }
    }

    @Test
    public void test275a_2_oe() throws Exception {
        final JexlContext ctxt = new MapContext();
        ctxt.set("out", System.out);
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(true).create();

        final JexlScript e = jexl.createScript("out.println(xyz)");
        try {
            final Object o = e.execute(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xvar) {
            Assert.assertEquals("xyz", xvar.getVariable());
    }
    }

    @Test
    public void test275b_2_oe() throws Exception {
        final JexlContext ctxt = new MapContext();
        //ctxt.set("out", System.out);
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(true).create();
        final JexlScript e = jexl.createScript("var xyz = xyz");
        try {
            final Object o = e.execute(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xvar) {
            Assert.fail("should not have thrown");
    }
    }

    @Test
    public void test275c_2_oe() throws Exception {
        final JexlContext ctxt = new MapContext();
        //ctxt.set("out", System.out);
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(true).silent(true).create();
        JexlScript e;
        Object r;
        e = jexl.createScript("(s, v)->{  var x = y ; 42; }");
        // wont make an error
        try {
            r = e.execute(ctxt, false, true);
            // removed other assertion
        } catch (final JexlException.Variable xjexl) {
            Assert.fail("should not have thrown");
    }
    }

    @Test
    public void test275d_2_oe() throws Exception {
        final JexlContext ctxt = new MapContext();
        ctxt.set("out", System.out);
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(true).create();

        final JexlScript e = jexl.createScript("{ var xyz = 42 } out.println(xyz)");
        try {
            final Object o = e.execute(ctxt);
            // removed other assertion
        } catch (final JexlException.Variable xvar) {
            Assert.fail("should not have thrown" + xvar);
    }
    }

    @Test
    public void test278_2_oe() throws Exception {
        final String[] srcs = new String[]{
            "return union x143('arg',5,6) ",
            "return union y143('arg',5,6)   ;",
            "return union\n z143('arg',5,6)   ;",
            "var f =()->{ return union 143 } foo[0]"
        };
        final Object[] ctls = new Object[]{
            "42","42","42", 42
        };
        final JexlEngine jexl = new JexlBuilder().cache(4).create();
        final JexlContext ctxt = new MapContext();
        final int[] foo = {42};
        ctxt.set("foo", foo);
        ctxt.set("union", "42");
        Object value;
        JexlScript jc;
        for(int i = 0; i < srcs.length; ++i) {
            String src = srcs[i];
            try {
                jc = jexl.createScript(src);
                // removed other assertion
            } catch(final JexlException.Ambiguous xa) {
                final String str = xa.toString();
                Assert.assertTrue(str.contains("143"));
    }
    }
    }

    @Test
    public void test278_3_oe() throws Exception {
        final String[] srcs = new String[]{
            "return union x143('arg',5,6) ",
            "return union y143('arg',5,6)   ;",
            "return union\n z143('arg',5,6)   ;",
            "var f =()->{ return union 143 } foo[0]"
        };
        final Object[] ctls = new Object[]{
            "42","42","42", 42
        };
        final JexlEngine jexl = new JexlBuilder().cache(4).create();
        final JexlContext ctxt = new MapContext();
        final int[] foo = {42};
        ctxt.set("foo", foo);
        ctxt.set("union", "42");
        Object value;
        JexlScript jc;
        for(int i = 0; i < srcs.length; ++i) {
            String src = srcs[i];
            try {
                jc = jexl.createScript(src);
                // removed other assertion
            } catch(final JexlException.Ambiguous xa) {
                final String str = xa.toString();
                // removed other assertion
                src = xa.tryCleanSource(src);
            }
            jc = jexl.createScript(src);
            value = jc.execute(ctxt);
            Assert.assertEquals(src, ctls[i], value);
    }
    }

    @Test
    public void test279_5_oe() throws Exception {
        final Log logger = null;//LogFactory.getLog(Issues200Test.class);
        Object result;
        JexlScript script;
        final JexlContext ctxt = new Context279();
        final String[] srcs = new String[]{
            "var z = null; identity(z[0]);",
             "var z = null; z.0;",
             "var z = null; z.foo();",
            "z['y']['z']",
            "z.y.any()",
             "identity(z.any())",
             "z[0]",
             "z.0",
             "z.foo()",
             "z.y[0]",
             "z.y[0].foo()",
             "z.y.0",
             "z.y.foo()",
             "var z = { 'y' : [42] }; z.y[1]",
             "var z = { 'y' : [42] }; z.y.1",
             "var z = { 'y' : [-42] }; z.y[1].foo()",
             "var z = { 'y' : [42] }; z.y.1.foo()",
             "var z = { 'y' : [null, null] }; z.y[1].foo()",
             "var z = { 'y' : [null, null] }; z.y.1.foo()"
        };
        for (int i = 0; i < 2; ++i) {
            for (final boolean strict : new boolean[]{true, false}) {
                final JexlEngine jexl = new JexlBuilder().safe(false).strict(strict).create();
                for (final String src : srcs) {
                    script = jexl.createScript(src);
                    try {
                        result = script.execute(ctxt);
                        if (strict) {
                            if (logger != null) {
                                logger.warn(ctxt.has("z") + ": " + src + ": no fail, " + result);
                            }
                            // removed other assertion
                        }
                        // not reachable
                        // removed other assertion
                    } catch (final JexlException.Variable xvar) {
                        if (logger != null) {
                            logger.warn(ctxt.has("z") + ": " + src + ": fail, " + xvar);
                        }
                        if (!strict) {
                            // removed other assertion
                        } else {
                            // removed other assertion
                        }
                    } catch (final JexlException.Property xprop) {
                        if (logger != null) {
                            logger.warn(ctxt.has("z") + ": " + src + ": fail, " + xprop);
                        }
                        if (!strict) {
                            Assert.fail(src + ", should not have thrown " + xprop);
    }
    }
    }
    }
    }
    }

    @Test
    public void test279_6_oe() throws Exception {
        final Log logger = null;//LogFactory.getLog(Issues200Test.class);
        Object result;
        JexlScript script;
        final JexlContext ctxt = new Context279();
        final String[] srcs = new String[]{
            "var z = null; identity(z[0]);",
             "var z = null; z.0;",
             "var z = null; z.foo();",
            "z['y']['z']",
            "z.y.any()",
             "identity(z.any())",
             "z[0]",
             "z.0",
             "z.foo()",
             "z.y[0]",
             "z.y[0].foo()",
             "z.y.0",
             "z.y.foo()",
             "var z = { 'y' : [42] }; z.y[1]",
             "var z = { 'y' : [42] }; z.y.1",
             "var z = { 'y' : [-42] }; z.y[1].foo()",
             "var z = { 'y' : [42] }; z.y.1.foo()",
             "var z = { 'y' : [null, null] }; z.y[1].foo()",
             "var z = { 'y' : [null, null] }; z.y.1.foo()"
        };
        for (int i = 0; i < 2; ++i) {
            for (final boolean strict : new boolean[]{true, false}) {
                final JexlEngine jexl = new JexlBuilder().safe(false).strict(strict).create();
                for (final String src : srcs) {
                    script = jexl.createScript(src);
                    try {
                        result = script.execute(ctxt);
                        if (strict) {
                            if (logger != null) {
                                logger.warn(ctxt.has("z") + ": " + src + ": no fail, " + result);
                            }
                            // removed other assertion
                        }
                        // not reachable
                        // removed other assertion
                    } catch (final JexlException.Variable xvar) {
                        if (logger != null) {
                            logger.warn(ctxt.has("z") + ": " + src + ": fail, " + xvar);
                        }
                        if (!strict) {
                            // removed other assertion
                        } else {
                            // removed other assertion
                        }
                    } catch (final JexlException.Property xprop) {
                        if (logger != null) {
                            logger.warn(ctxt.has("z") + ": " + src + ": fail, " + xprop);
                        }
                        if (!strict) {
                            // removed other assertion
                        } else {
                            Assert.assertTrue(src + ": " + xprop.toString(), xprop.toString().contains("1"));
    }
    }
    }
    }
    }
    }

    @Test
    public void test279b_1_oe() throws Exception {
        Object result;
        JexlScript script;
        final JexlContext ctxt = new Context279();
        ctxt.set("ctxt", ctxt);
        final String src = "(x)->{ spread(x)[0].toString() }";
        final JexlEngine jexl = new JexlBuilder().safe(true).strict(true).create();
        script = jexl.createScript(src);
        result = script.execute(ctxt, "abc");
        Assert.assertEquals("a", result);
    }

    @Test
    public void test279b_2_oe() throws Exception {
        Object result;
        JexlScript script;
        final JexlContext ctxt = new Context279();
        ctxt.set("ctxt", ctxt);
        final String src = "(x)->{ spread(x)[0].toString() }";
        final JexlEngine jexl = new JexlBuilder().safe(true).strict(true).create();
        script = jexl.createScript(src);
        result = script.execute(ctxt, "abc");
        // removed other assertion
        result = null;
        try {
            result = script.execute(ctxt, (Object) null);
        } catch(final JexlException xany) {
            Assert.assertNotNull(xany.getMessage());
    }
    }

    @Test
    public void test279b_3_oe() throws Exception {
        Object result;
        JexlScript script;
        final JexlContext ctxt = new Context279();
        ctxt.set("ctxt", ctxt);
        final String src = "(x)->{ spread(x)[0].toString() }";
        final JexlEngine jexl = new JexlBuilder().safe(true).strict(true).create();
        script = jexl.createScript(src);
        result = script.execute(ctxt, "abc");
        // removed other assertion
        result = null;
        try {
            result = script.execute(ctxt, (Object) null);
        } catch(final JexlException xany) {
            // removed other assertion
        }
        Assert.assertNull(result);
    }

    @Test
    public void test285_1_oe() throws Exception {
        final List<String> out = new ArrayList<String>(6);
        final JexlContext ctxt = new MapContext();
        ctxt.set("$out", out);
        final String src = "for(var b: ['g','h','i']) {\n"
                + "  var c = b;\n"
                + "  $out.add(c);\n"
                + "}\n"
                + " \n"
                + "for(var dc: ['j','k','l']) {\n"
                + "  $out.add(dc);\n"
                + "}"
                + " \n"
                + "$out.size()";

        final JexlFeatures features = new JexlFeatures();
        features.lexical(true);
        final JexlEngine jexl = new JexlBuilder()
                //.features(features)
                .safe(false).strict(true).lexical(true).create();
        final JexlScript script = jexl.createScript(src);
        final Object result = script.execute(ctxt, (Object) null);
        Assert.assertEquals(6, result);
    }

    @Test
    public void test285_2_oe() throws Exception {
        final List<String> out = new ArrayList<String>(6);
        final JexlContext ctxt = new MapContext();
        ctxt.set("$out", out);
        final String src = "for(var b: ['g','h','i']) {\n"
                + "  var c = b;\n"
                + "  $out.add(c);\n"
                + "}\n"
                + " \n"
                + "for(var dc: ['j','k','l']) {\n"
                + "  $out.add(dc);\n"
                + "}"
                + " \n"
                + "$out.size()";

        final JexlFeatures features = new JexlFeatures();
        features.lexical(true);
        final JexlEngine jexl = new JexlBuilder()
                //.features(features)
                .safe(false).strict(true).lexical(true).create();
        final JexlScript script = jexl.createScript(src);
        final Object result = script.execute(ctxt, (Object) null);
        // removed other assertion
        final List<String> ctl = Arrays.asList("g", "h", "i", "j", "k", "l");
        Assert.assertEquals(ctl, out);
    }

    @Test
    public void test285a_1_oe() throws Exception {
        final List<String> out = new ArrayList<String>(6);
        final JexlContext ctxt = new MapContext();
        ctxt.set("$out", out);
        final String src =
                  "for(var b: ['g','h','i']) { $out.add(b); }\n"
                + "for(b: ['j','k','l']) { $out.add(b);}\n"
                + "$out.size()";

        final JexlEngine jexl = new JexlBuilder().safe(false).strict(true).lexical(false).create();
        final JexlScript script = jexl.createScript(src);
        final Object result = script.execute(ctxt, (Object) null);
        Assert.assertEquals(6, result);
    }

    @Test
    public void test285a_2_oe() throws Exception {
        final List<String> out = new ArrayList<String>(6);
        final JexlContext ctxt = new MapContext();
        ctxt.set("$out", out);
        final String src =
                  "for(var b: ['g','h','i']) { $out.add(b); }\n"
                + "for(b: ['j','k','l']) { $out.add(b);}\n"
                + "$out.size()";

        final JexlEngine jexl = new JexlBuilder().safe(false).strict(true).lexical(false).create();
        final JexlScript script = jexl.createScript(src);
        final Object result = script.execute(ctxt, (Object) null);
        // removed other assertion
        final List<String> ctl = Arrays.asList("g", "h", "i", "j", "k", "l");
        Assert.assertEquals(ctl, out);
    }

    @Test
    public void test285b_1_oe() throws Exception {
        final List<String> out = new ArrayList<String>(6);
        final JexlContext ctxt = new MapContext();
        ctxt.set("$out", out);
        final String src =
                  "for(b: ['g','h','i']) { $out.add(b); }\n"
                + "for(var b: ['j','k','l']) { $out.add(b);}\n"
                + "$out.size()";

        final JexlEngine jexl = new JexlBuilder().safe(false).strict(true).create();
        final JexlScript script = jexl.createScript(src);
        final Object result = script.execute(ctxt, (Object) null);
        Assert.assertEquals(6, result);
    }

    @Test
    public void test285b_2_oe() throws Exception {
        final List<String> out = new ArrayList<String>(6);
        final JexlContext ctxt = new MapContext();
        ctxt.set("$out", out);
        final String src =
                  "for(b: ['g','h','i']) { $out.add(b); }\n"
                + "for(var b: ['j','k','l']) { $out.add(b);}\n"
                + "$out.size()";

        final JexlEngine jexl = new JexlBuilder().safe(false).strict(true).create();
        final JexlScript script = jexl.createScript(src);
        final Object result = script.execute(ctxt, (Object) null);
        // removed other assertion
        final List<String> ctl = Arrays.asList("g", "h", "i", "j", "k", "l");
        Assert.assertEquals(ctl, out);
    }

    @Test
    public void test287_1_oe() {
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        String src;
        JexlScript script;
        Object result;
        // declared, not defined
        src = "x = 1; if (false) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        Assert.assertEquals(1, result);
    }

    @Test
    public void test287_2_oe() {
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        String src;
        JexlScript script;
        Object result;
        // declared, not defined
        src = "x = 1; if (false) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // declared and defined
        src = "x = 1; if (true) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        Assert.assertEquals(2, result);
    }

    @Test
    public void test287_3_oe() {
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        String src;
        JexlScript script;
        Object result;
        // declared, not defined
        src = "x = 1; if (false) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // declared and defined
        src = "x = 1; if (true) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        src = "x = 1; var x = x + 41; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test287_4_oe() {
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        String src;
        JexlScript script;
        Object result;
        // declared, not defined
        src = "x = 1; if (false) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // declared and defined
        src = "x = 1; if (true) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        src = "x = 1; var x = x + 41; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        options.setLexical(false);
        src = "(x)->{ if (x==1) { var y = 2; } else if (x==2) { var y = 3; }; y }";
        script = jexl.createScript(src);
        result = script.execute(ctxt, 1);
        Assert.assertEquals(2, result);
    }

    @Test
    public void test287_5_oe() {
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        String src;
        JexlScript script;
        Object result;
        // declared, not defined
        src = "x = 1; if (false) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // declared and defined
        src = "x = 1; if (true) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        src = "x = 1; var x = x + 41; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        options.setLexical(false);
        src = "(x)->{ if (x==1) { var y = 2; } else if (x==2) { var y = 3; }; y }";
        script = jexl.createScript(src);
        result = script.execute(ctxt, 1);
        // removed other assertion
        result = script.execute(ctxt, 2);
        Assert.assertEquals(3, result);
    }

    @Test
    public void test287_7_oe() {
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        String src;
        JexlScript script;
        Object result;
        // declared, not defined
        src = "x = 1; if (false) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // declared and defined
        src = "x = 1; if (true) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        src = "x = 1; var x = x + 41; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        options.setLexical(false);
        src = "(x)->{ if (x==1) { var y = 2; } else if (x==2) { var y = 3; }; y }";
        script = jexl.createScript(src);
        result = script.execute(ctxt, 1);
        // removed other assertion
        result = script.execute(ctxt, 2);
        // removed other assertion
        options.setStrict(true);
        try {
            result = script.execute(ctxt, 0);
            // removed other assertion
        } catch (final JexlException.Variable xvar) {
            Assert.assertTrue(xvar.getMessage().contains("y"));
    }
    }

    @Test
    public void test287_8_oe() {
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        String src;
        JexlScript script;
        Object result;
        // declared, not defined
        src = "x = 1; if (false) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // declared and defined
        src = "x = 1; if (true) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        src = "x = 1; var x = x + 41; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        options.setLexical(false);
        src = "(x)->{ if (x==1) { var y = 2; } else if (x==2) { var y = 3; }; y }";
        script = jexl.createScript(src);
        result = script.execute(ctxt, 1);
        // removed other assertion
        result = script.execute(ctxt, 2);
        // removed other assertion
        options.setStrict(true);
        try {
            result = script.execute(ctxt, 0);
            // removed other assertion
        } catch (final JexlException.Variable xvar) {
            // removed other assertion
        }
        options.setStrict(false);
        try {
            result = script.execute(ctxt, 0);
        } catch (final JexlException xvar) {
            Assert.fail("should not have failed!");
    }
    }

    @Test
    public void test287_9_oe() {
        final JexlEvalContext ctxt = new JexlEvalContext();
        final JexlOptions options = ctxt.getEngineOptions();
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        String src;
        JexlScript script;
        Object result;
        // declared, not defined
        src = "x = 1; if (false) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // declared and defined
        src = "x = 1; if (true) var x = 2; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        src = "x = 1; var x = x + 41; x";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        // removed other assertion
        // definition using shadowed global
        options.setLexical(false);
        src = "(x)->{ if (x==1) { var y = 2; } else if (x==2) { var y = 3; }; y }";
        script = jexl.createScript(src);
        result = script.execute(ctxt, 1);
        // removed other assertion
        result = script.execute(ctxt, 2);
        // removed other assertion
        options.setStrict(true);
        try {
            result = script.execute(ctxt, 0);
            // removed other assertion
        } catch (final JexlException.Variable xvar) {
            // removed other assertion
        }
        options.setStrict(false);
        try {
            result = script.execute(ctxt, 0);
        } catch (final JexlException xvar) {
            // removed other assertion
        }
        Assert.assertNull(result);
    }

    @Test
    public void test289_1_oe() {
        final JexlContext ctxt = new MapContext();
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        String src;
        JexlScript script;
        Object result;
        src = "var x = function(a) { var b; return b}; x(1,2)";
        script = jexl.createScript(src);
        result = script.execute(ctxt);
        Assert.assertNull(result);
    }

    @Test
    public void test290a_3_oe() throws Exception {
        Object result;
        JexlScript script;
        final String[] srcs = new String[]{
            "(x)->{ x.nothing().toString() }",
            "(x)->{ x.toString().nothing() }",
            "(x)->{ x.nothing().nothing() }",
        };
        for (final boolean safe : new boolean[]{true, false}) {
            final JexlEngine jexl = new JexlBuilder().safe(safe).strict(true).create();
            for (final String src : srcs) {
                script = jexl.createScript(src);
                try {
                    result = script.execute(null, "abc");
                    if (!safe) {
                        // removed other assertion
                    } else {
                        // removed other assertion
                    }
                } catch (final JexlException.Method xmethod) {
                    if (safe) {
                        Assert.fail(src + ", should not have thrown " + xmethod);
    }
    }
    }
    }
    }

    @Test
    public void test290a_4_oe() throws Exception {
        Object result;
        JexlScript script;
        final String[] srcs = new String[]{
            "(x)->{ x.nothing().toString() }",
            "(x)->{ x.toString().nothing() }",
            "(x)->{ x.nothing().nothing() }",
        };
        for (final boolean safe : new boolean[]{true, false}) {
            final JexlEngine jexl = new JexlBuilder().safe(safe).strict(true).create();
            for (final String src : srcs) {
                script = jexl.createScript(src);
                try {
                    result = script.execute(null, "abc");
                    if (!safe) {
                        // removed other assertion
                    } else {
                        // removed other assertion
                    }
                } catch (final JexlException.Method xmethod) {
                    if (safe) {
                        // removed other assertion
                    } else {
                        Assert.assertTrue(src + ": " + xmethod.toString(), xmethod.toString().contains("nothing"));
    }
    }
    }
    }
    }

    @Test
    public void test290b_1_oe() throws Exception {
        Object result;
        JexlScript script;
        final String[] srcs = new String[]{
            "(x)->{ x?.nothing()?.toString() }",
            "(x)->{ x.toString()?.nothing() }",
            "(x)->{ x?.nothing().nothing() }",};
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        for (final String src : srcs) {
            script = jexl.createScript(src);
            result = script.execute(null, "abc");
            Assert.assertNull(result);
    }
    }

    @Test
    public void test291_1_oe() throws Exception {
        final String str = "{1:'one'}[1]";
        final JexlContext ctxt = new MapContext();
        final JexlEngine jexl = new JexlBuilder().create();
        JexlExpression e = jexl.createExpression(str);
        Object value = e.evaluate(ctxt);
        Assert.assertEquals("one", value);
    }

    @Test
    public void test291_2_oe() throws Exception {
        final String str = "{1:'one'}[1]";
        final JexlContext ctxt = new MapContext();
        final JexlEngine jexl = new JexlBuilder().create();
        JexlExpression e = jexl.createExpression(str);
        Object value = e.evaluate(ctxt);
        // removed other assertion

        final JexlEngine sandboxedJexlEngine = new JexlBuilder().
                sandbox(new JexlSandbox(true)). // add a whitebox sandbox
                create();
         e = sandboxedJexlEngine.createExpression(str);
        value = e.evaluate(ctxt);
        Assert.assertEquals("one", value);
    }

    @Test
    public void testTemplate6565a_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JxltEngine jexlt = jexl.createJxltEngine();
        final String source =
            "$$ var res = '';\n" +
            "$$ var meta = session.data['METADATA'];\n" +
            "$$ if (meta) {\n" +
            "$$   var entry = meta['ID'];\n" +
            "$$   if (entry) {\n" +
            "$$     var value = session.data[entry];\n" +
            "$$     res = value?: '';\n" +
            "$$   }\n" +
            "$$ }\n" +
            "${res}\n";
        final JxltEngine.Template script = jexlt.createTemplate("$$", new StringReader(source));
        Assert.assertNotNull(script);
    }

    @Test
    public void testTemplate6565a_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JxltEngine jexlt = jexl.createJxltEngine();
        final String source =
            "$$ var res = '';\n" +
            "$$ var meta = session.data['METADATA'];\n" +
            "$$ if (meta) {\n" +
            "$$   var entry = meta['ID'];\n" +
            "$$   if (entry) {\n" +
            "$$     var value = session.data[entry];\n" +
            "$$     res = value?: '';\n" +
            "$$   }\n" +
            "$$ }\n" +
            "${res}\n";
        final JxltEngine.Template script = jexlt.createTemplate("$$", new StringReader(source));
        // removed other assertion
        final TemplateDebugger dbg = new TemplateDebugger();
        final String refactored = dbg.debug(script) ? dbg.toString() : "";
        Assert.assertNotNull(refactored);
    }

    @Test
    public void testTemplate6565a_3_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JxltEngine jexlt = jexl.createJxltEngine();
        final String source =
            "$$ var res = '';\n" +
            "$$ var meta = session.data['METADATA'];\n" +
            "$$ if (meta) {\n" +
            "$$   var entry = meta['ID'];\n" +
            "$$   if (entry) {\n" +
            "$$     var value = session.data[entry];\n" +
            "$$     res = value?: '';\n" +
            "$$   }\n" +
            "$$ }\n" +
            "${res}\n";
        final JxltEngine.Template script = jexlt.createTemplate("$$", new StringReader(source));
        // removed other assertion
        final TemplateDebugger dbg = new TemplateDebugger();
        final String refactored = dbg.debug(script) ? dbg.toString() : "";
        // removed other assertion
        Assert.assertEquals(source, refactored);
    }

    @Test
    public void testTemplate6565b_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JxltEngine jexlt = jexl.createJxltEngine();
        final String source =
            "$$ var res = '';\n" +
            "$$ var meta = session.data['METADATA'];\n" +
            "$$ if (meta) {\n" +
            "$$   var entry = meta['ID'];\n" +
            "$$   if (entry) {\n" +
            "$$     var value = session.data[entry];\n" +
            "$$     res = value?: '';\n" +
            "${res}\n" +
            "$$   }\n" +
            "$$ }\n";
        final JxltEngine.Template script = jexlt.createTemplate("$$", new StringReader(source));
        Assert.assertNotNull(script);
    }

    @Test
    public void testTemplate6565b_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JxltEngine jexlt = jexl.createJxltEngine();
        final String source =
            "$$ var res = '';\n" +
            "$$ var meta = session.data['METADATA'];\n" +
            "$$ if (meta) {\n" +
            "$$   var entry = meta['ID'];\n" +
            "$$   if (entry) {\n" +
            "$$     var value = session.data[entry];\n" +
            "$$     res = value?: '';\n" +
            "${res}\n" +
            "$$   }\n" +
            "$$ }\n";
        final JxltEngine.Template script = jexlt.createTemplate("$$", new StringReader(source));
        // removed other assertion
        final TemplateDebugger dbg = new TemplateDebugger();
        final String refactored = dbg.debug(script) ? dbg.toString() : "";
        Assert.assertNotNull(refactored);
    }

    @Test
    public void testTemplate6565b_3_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JxltEngine jexlt = jexl.createJxltEngine();
        final String source =
            "$$ var res = '';\n" +
            "$$ var meta = session.data['METADATA'];\n" +
            "$$ if (meta) {\n" +
            "$$   var entry = meta['ID'];\n" +
            "$$   if (entry) {\n" +
            "$$     var value = session.data[entry];\n" +
            "$$     res = value?: '';\n" +
            "${res}\n" +
            "$$   }\n" +
            "$$ }\n";
        final JxltEngine.Template script = jexlt.createTemplate("$$", new StringReader(source));
        // removed other assertion
        final TemplateDebugger dbg = new TemplateDebugger();
        final String refactored = dbg.debug(script) ? dbg.toString() : "";
        // removed other assertion
        Assert.assertEquals(source, refactored);
    }

    @Test
    public void test298_1_oe() throws Exception {
        final Cls298 c298 = new Cls298();
        final JexlContext ctxt = new MapContext();
        final JexlEngine jexl = new JexlBuilder().create();

        String str = "c.size()";
        JexlScript e = jexl.createScript(str, "c");
        Object value = e.execute(ctxt, c298);
        Assert.assertEquals(str, 42, value);
    }

    @Test
    public void test298_2_oe() throws Exception {
        final Cls298 c298 = new Cls298();
        final JexlContext ctxt = new MapContext();
        final JexlEngine jexl = new JexlBuilder().create();

        String str = "c.size()";
        JexlScript e = jexl.createScript(str, "c");
        Object value = e.execute(ctxt, c298);
        // removed other assertion

        str = "size c";
        e = jexl.createScript(str, "c");
        value = e.execute(ctxt, c298);
        Assert.assertEquals(str, 42, value);
    }

    @Test
    public void test298_3_oe() throws Exception {
        final Cls298 c298 = new Cls298();
        final JexlContext ctxt = new MapContext();
        final JexlEngine jexl = new JexlBuilder().create();

        String str = "c.size()";
        JexlScript e = jexl.createScript(str, "c");
        Object value = e.execute(ctxt, c298);
        // removed other assertion

        str = "size c";
        e = jexl.createScript(str, "c");
        value = e.execute(ctxt, c298);
        // removed other assertion

        str = "c.size(127)";
        e = jexl.createScript(str, "c");
        value = e.execute(ctxt, c298);
        Assert.assertEquals(str, 169, value);
    }

}
