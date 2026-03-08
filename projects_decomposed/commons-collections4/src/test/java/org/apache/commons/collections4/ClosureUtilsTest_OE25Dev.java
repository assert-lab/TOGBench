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
package org.apache.commons.collections4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionClosure;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.NOPClosure;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.Test;

/**
 * Tests the ClosureUtils class.
 *
 * @since 3.0
 */
public class ClosureUtilsTest_OE25Dev {

    private static final Object cString = "Hello";

    static class MockClosure<T> implements Closure<T> {
        int count = 0;

        @Override
        public void execute(final T object) {
            count++;
        }

        public void reset() {
            count = 0;
        }
    }

    static class MockTransformer<T> implements Transformer<T, T> {
        int count = 0;

        @Override
        public T transform(final T object) {
            count++;
            return object;
        }
    }

    // exceptionClosure
    //------------------------------------------------------------------

    @Test
    public void testExceptionClosure() {
        assertNotNull(ClosureUtils.exceptionClosure());
        assertSame(ClosureUtils.exceptionClosure(), ClosureUtils.exceptionClosure());
        try {
            ClosureUtils.exceptionClosure().execute(null);
        } catch (final FunctorException ex) {
            try {
                ClosureUtils.exceptionClosure().execute(cString);
            } catch (final FunctorException ex2) {
                return;
            }
        }
        fail();
    }

    // nopClosure
    //------------------------------------------------------------------

    @Test
    public void testNopClosure() {
        final StringBuilder buf = new StringBuilder("Hello");
        ClosureUtils.nopClosure().execute(null);
        assertEquals("Hello", buf.toString());
        ClosureUtils.nopClosure().execute("Hello");
        assertEquals("Hello", buf.toString());
    }

    // invokeClosure
    //------------------------------------------------------------------

    @Test
    public void testInvokeClosure() {
        StringBuffer buf = new StringBuffer("Hello"); // Only StringBuffer has setLength() method
        ClosureUtils.invokerClosure("reverse").execute(buf);
        assertEquals("olleH", buf.toString());
        buf = new StringBuffer("Hello");
        ClosureUtils.invokerClosure("setLength", new Class[] {Integer.TYPE}, new Object[] {Integer.valueOf(2)}).execute(buf);
        assertEquals("He", buf.toString());
    }

    // forClosure
    //------------------------------------------------------------------

    @Test
    public void testForClosure() {
        final MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.forClosure(5, cmd).execute(null);
        assertEquals(5, cmd.count);
        assertSame(NOPClosure.INSTANCE, ClosureUtils.forClosure(0, new MockClosure<>()));
        assertSame(NOPClosure.INSTANCE, ClosureUtils.forClosure(-1, new MockClosure<>()));
        assertSame(NOPClosure.INSTANCE, ClosureUtils.forClosure(1, null));
        assertSame(NOPClosure.INSTANCE, ClosureUtils.forClosure(3, null));
        assertSame(cmd, ClosureUtils.forClosure(1, cmd));
    }

    // whileClosure
    //------------------------------------------------------------------

    @Test
    public void testWhileClosure() {
        MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.whileClosure(FalsePredicate.falsePredicate(), cmd).execute(null);
        assertEquals(0, cmd.count);

        cmd = new MockClosure<>();
        ClosureUtils.whileClosure(PredicateUtils.uniquePredicate(), cmd).execute(null);
        assertEquals(1, cmd.count);

        try {
            ClosureUtils.whileClosure(null, ClosureUtils.nopClosure());
            fail();
        } catch (final NullPointerException ex) {}
        try {
            ClosureUtils.whileClosure(FalsePredicate.falsePredicate(), null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            ClosureUtils.whileClosure(null, null);
            fail();
        } catch (final NullPointerException ex) {}
    }

    // doWhileClosure
    //------------------------------------------------------------------

    @Test
    public void testDoWhileClosure() {
        MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.doWhileClosure(cmd, FalsePredicate.falsePredicate()).execute(null);
        assertEquals(1, cmd.count);

        cmd = new MockClosure<>();
        ClosureUtils.doWhileClosure(cmd, PredicateUtils.uniquePredicate()).execute(null);
        assertEquals(2, cmd.count);

        try {
            ClosureUtils.doWhileClosure(null, null);
            fail();
        } catch (final NullPointerException ex) {}
    }

    // chainedClosure
    //------------------------------------------------------------------

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedClosure() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = new MockClosure<>();
        ClosureUtils.chainedClosure(a, b).execute(null);
        assertEquals(1, a.count);
        assertEquals(1, b.count);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.<Object>chainedClosure(new Closure[] {a, b, a}).execute(null);
        assertEquals(2, a.count);
        assertEquals(1, b.count);

        a = new MockClosure<>();
        b = new MockClosure<>();
        Collection<Closure<Object>> coll = new ArrayList<>();
        coll.add(b);
        coll.add(a);
        coll.add(b);
        ClosureUtils.<Object>chainedClosure(coll).execute(null);
        assertEquals(1, a.count);
        assertEquals(2, b.count);

        assertSame(NOPClosure.INSTANCE, ClosureUtils.<Object>chainedClosure(new Closure[0]));
        assertSame(NOPClosure.INSTANCE, ClosureUtils.<Object>chainedClosure(Collections.<Closure<Object>>emptyList()));

        try {
            ClosureUtils.chainedClosure(null, null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            ClosureUtils.<Object>chainedClosure((Closure[]) null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            ClosureUtils.<Object>chainedClosure((Collection<Closure<Object>>) null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            ClosureUtils.<Object>chainedClosure(new Closure[] {null, null});
            fail();
        } catch (final NullPointerException ex) {}
        try {
            coll = new ArrayList<>();
            coll.add(null);
            coll.add(null);
            ClosureUtils.chainedClosure(coll);
            fail();
        } catch (final NullPointerException ex) {}
    }

    // ifClosure
    //------------------------------------------------------------------

    @Test
    public void testIfClosure() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = null;
        ClosureUtils.ifClosure(TruePredicate.truePredicate(), a).execute(null);
        assertEquals(1, a.count);

        a = new MockClosure<>();
        ClosureUtils.ifClosure(FalsePredicate.<Object>falsePredicate(), a).execute(null);
        assertEquals(0, a.count);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.ifClosure(TruePredicate.<Object>truePredicate(), a, b).execute(null);
        assertEquals(1, a.count);
        assertEquals(0, b.count);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.ifClosure(FalsePredicate.<Object>falsePredicate(), a, b).execute(null);
        assertEquals(0, a.count);
        assertEquals(1, b.count);
    }

    // switchClosure
    //------------------------------------------------------------------

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");
        assertEquals(0, a.count);
        assertEquals(0, b.count);

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");
        assertEquals(1, a.count);
        assertEquals(0, b.count);

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");
        assertEquals(0, a.count);
        assertEquals(0, b.count);
        assertEquals(1, c.count);

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);
        assertEquals(0, a.count);
        assertEquals(0, b.count);

        a.reset();
        b.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.switchClosure(map).execute("THERE");
        assertEquals(0, a.count);
        assertEquals(1, b.count);

        a.reset();
        b.reset();
        c.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);
        ClosureUtils.switchClosure(map).execute("WELL");
        assertEquals(0, a.count);
        assertEquals(0, b.count);
        assertEquals(1, c.count);

        assertEquals(NOPClosure.INSTANCE, ClosureUtils.<String>switchClosure(new Predicate[0], new Closure[0]));
        assertEquals(NOPClosure.INSTANCE, ClosureUtils.<String>switchClosure(new HashMap<Predicate<String>, Closure<String>>()));
        map.clear();
        map.put(null, null);
        assertEquals(NOPClosure.INSTANCE, ClosureUtils.switchClosure(map));

        try {
            ClosureUtils.switchClosure(null, null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            ClosureUtils.<String>switchClosure((Predicate<String>[]) null, (Closure<String>[]) null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            ClosureUtils.<String>switchClosure((Map<Predicate<String>, Closure<String>>) null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            ClosureUtils.<String>switchClosure(new Predicate[2], new Closure[2]);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            ClosureUtils.<String>switchClosure(
                    new Predicate[] { TruePredicate.<String>truePredicate() },
                    new Closure[] { a, b });
            fail();
        } catch (final IllegalArgumentException ex) {}
    }

    // switchMapClosure
    //------------------------------------------------------------------

    @Test
    public void testSwitchMapClosure() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        final Map<String, Closure<String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute(null);
        assertEquals(0, a.count);
        assertEquals(0, b.count);

        a.reset();
        b.reset();
        map.clear();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute("THERE");
        assertEquals(0, a.count);
        assertEquals(1, b.count);

        a.reset();
        b.reset();
        map.clear();
        final MockClosure<String> c = new MockClosure<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        map.put(null, c);
        ClosureUtils.switchMapClosure(map).execute("WELL");
        assertEquals(0, a.count);
        assertEquals(0, b.count);
        assertEquals(1, c.count);

        assertEquals(NOPClosure.INSTANCE, ClosureUtils.switchMapClosure(new HashMap<String, Closure<String>>()));

        try {
            ClosureUtils.switchMapClosure(null);
            fail();
        } catch (final NullPointerException ex) {}
    }

    // asClosure
    //------------------------------------------------------------------

    @Test
    public void testTransformerClosure() {
        final MockTransformer<Object> mock = new MockTransformer<>();
        final Closure<Object> closure = ClosureUtils.asClosure(mock);
        closure.execute(null);
        assertEquals(1, mock.count);
        closure.execute(null);
        assertEquals(2, mock.count);

        assertEquals(ClosureUtils.nopClosure(), ClosureUtils.asClosure(null));
    }

    // misc tests
    //------------------------------------------------------------------

    /**
     * Test that all Closure singletons hold singleton pattern in
     * serialization/deserialization process.
     */
    @Test
    public void testSingletonPatternInSerialization() {
        final Object[] singletones = new Object[] {
                ExceptionClosure.INSTANCE,
                NOPClosure.INSTANCE,
        };

        for (final Object original : singletones) {
            TestUtils.assertSameAfterSerialization(
                    "Singletone patern broken for " + original.getClass(),
                    original
            );
        }
    }

    @Test
    public void testExceptionClosure_1_oe() {
        assertNotNull(ClosureUtils.exceptionClosure());
    }

    @Test
    public void testExceptionClosure_2_oe() {
        assertSame(ClosureUtils.exceptionClosure(), ClosureUtils.exceptionClosure());
    }

    @Test
    public void testExceptionClosure_3_oe() {
        try {
            ClosureUtils.exceptionClosure().execute(null);
        } catch (final FunctorException ex) {
            try {
                ClosureUtils.exceptionClosure().execute(cString);
            } catch (final FunctorException ex2) {
                return;
            }
        }
        fail();
    }

    @Test
    public void testNopClosure_1_oe() {
        final StringBuilder buf = new StringBuilder("Hello");
        ClosureUtils.nopClosure().execute(null);
        assertEquals("Hello", buf.toString());
    }

    @Test
    public void testNopClosure_2_oe() {
        final StringBuilder buf = new StringBuilder("Hello");
        ClosureUtils.nopClosure().execute(null);
        ClosureUtils.nopClosure().execute("Hello");
        assertEquals("Hello", buf.toString());
    }

    @Test
    public void testInvokeClosure_1_oe() {
        StringBuffer buf = new StringBuffer("Hello"); // Only StringBuffer has setLength() method
        ClosureUtils.invokerClosure("reverse").execute(buf);
        assertEquals("olleH", buf.toString());
    }

    @Test
    public void testInvokeClosure_2_oe() {
        StringBuffer buf = new StringBuffer("Hello"); // Only StringBuffer has setLength() method
        ClosureUtils.invokerClosure("reverse").execute(buf);
        buf = new StringBuffer("Hello");
        ClosureUtils.invokerClosure("setLength", new Class[] {Integer.TYPE}, new Object[] {Integer.valueOf(2)}).execute(buf);
        assertEquals("He", buf.toString());
    }

    @Test
    public void testForClosure_1_oe() {
        final MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.forClosure(5, cmd).execute(null);
        assertEquals(5, cmd.count);
    }

    @Test
    public void testForClosure_2_oe() {
        final MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.forClosure(5, cmd).execute(null);
        assertSame(NOPClosure.INSTANCE, ClosureUtils.forClosure(0, new MockClosure<>()));
    }

    @Test
    public void testForClosure_3_oe() {
        final MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.forClosure(5, cmd).execute(null);
        assertSame(NOPClosure.INSTANCE, ClosureUtils.forClosure(-1, new MockClosure<>()));
    }

    @Test
    public void testForClosure_4_oe() {
        final MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.forClosure(5, cmd).execute(null);
        assertSame(NOPClosure.INSTANCE, ClosureUtils.forClosure(1, null));
    }

    @Test
    public void testForClosure_5_oe() {
        final MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.forClosure(5, cmd).execute(null);
        assertSame(NOPClosure.INSTANCE, ClosureUtils.forClosure(3, null));
    }

    @Test
    public void testForClosure_6_oe() {
        final MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.forClosure(5, cmd).execute(null);
        assertSame(cmd, ClosureUtils.forClosure(1, cmd));
    }

    @Test
    public void testWhileClosure_1_oe() {
        MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.whileClosure(FalsePredicate.falsePredicate(), cmd).execute(null);
        assertEquals(0, cmd.count);
    }

    @Test
    public void testWhileClosure_2_oe() {
        MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.whileClosure(FalsePredicate.falsePredicate(), cmd).execute(null);

        cmd = new MockClosure<>();
        ClosureUtils.whileClosure(PredicateUtils.uniquePredicate(), cmd).execute(null);
        assertEquals(1, cmd.count);
    }

    @Test
    public void testDoWhileClosure_1_oe() {
        MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.doWhileClosure(cmd, FalsePredicate.falsePredicate()).execute(null);
        assertEquals(1, cmd.count);
    }

    @Test
    public void testDoWhileClosure_2_oe() {
        MockClosure<Object> cmd = new MockClosure<>();
        ClosureUtils.doWhileClosure(cmd, FalsePredicate.falsePredicate()).execute(null);

        cmd = new MockClosure<>();
        ClosureUtils.doWhileClosure(cmd, PredicateUtils.uniquePredicate()).execute(null);
        assertEquals(2, cmd.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedClosure_1_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = new MockClosure<>();
        ClosureUtils.chainedClosure(a, b).execute(null);
        assertEquals(1, a.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedClosure_2_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = new MockClosure<>();
        ClosureUtils.chainedClosure(a, b).execute(null);
        assertEquals(1, b.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedClosure_3_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = new MockClosure<>();
        ClosureUtils.chainedClosure(a, b).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.<Object>chainedClosure(new Closure[] {a, b, a}).execute(null);
        assertEquals(2, a.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedClosure_4_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = new MockClosure<>();
        ClosureUtils.chainedClosure(a, b).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.<Object>chainedClosure(new Closure[] {a, b, a}).execute(null);
        assertEquals(1, b.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedClosure_5_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = new MockClosure<>();
        ClosureUtils.chainedClosure(a, b).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.<Object>chainedClosure(new Closure[] {a, b, a}).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        Collection<Closure<Object>> coll = new ArrayList<>();
        coll.add(b);
        coll.add(a);
        coll.add(b);
        ClosureUtils.<Object>chainedClosure(coll).execute(null);
        assertEquals(1, a.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedClosure_6_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = new MockClosure<>();
        ClosureUtils.chainedClosure(a, b).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.<Object>chainedClosure(new Closure[] {a, b, a}).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        Collection<Closure<Object>> coll = new ArrayList<>();
        coll.add(b);
        coll.add(a);
        coll.add(b);
        ClosureUtils.<Object>chainedClosure(coll).execute(null);
        assertEquals(2, b.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedClosure_7_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = new MockClosure<>();
        ClosureUtils.chainedClosure(a, b).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.<Object>chainedClosure(new Closure[] {a, b, a}).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        Collection<Closure<Object>> coll = new ArrayList<>();
        coll.add(b);
        coll.add(a);
        coll.add(b);
        ClosureUtils.<Object>chainedClosure(coll).execute(null);

        assertSame(NOPClosure.INSTANCE, ClosureUtils.<Object>chainedClosure(new Closure[0]));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedClosure_8_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = new MockClosure<>();
        ClosureUtils.chainedClosure(a, b).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.<Object>chainedClosure(new Closure[] {a, b, a}).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        Collection<Closure<Object>> coll = new ArrayList<>();
        coll.add(b);
        coll.add(a);
        coll.add(b);
        ClosureUtils.<Object>chainedClosure(coll).execute(null);

        assertSame(NOPClosure.INSTANCE, ClosureUtils.<Object>chainedClosure(Collections.<Closure<Object>>emptyList()));
    }

    @Test
    public void testIfClosure_1_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = null;
        ClosureUtils.ifClosure(TruePredicate.truePredicate(), a).execute(null);
        assertEquals(1, a.count);
    }

    @Test
    public void testIfClosure_2_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = null;
        ClosureUtils.ifClosure(TruePredicate.truePredicate(), a).execute(null);

        a = new MockClosure<>();
        ClosureUtils.ifClosure(FalsePredicate.<Object>falsePredicate(), a).execute(null);
        assertEquals(0, a.count);
    }

    @Test
    public void testIfClosure_3_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = null;
        ClosureUtils.ifClosure(TruePredicate.truePredicate(), a).execute(null);

        a = new MockClosure<>();
        ClosureUtils.ifClosure(FalsePredicate.<Object>falsePredicate(), a).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.ifClosure(TruePredicate.<Object>truePredicate(), a, b).execute(null);
        assertEquals(1, a.count);
    }

    @Test
    public void testIfClosure_4_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = null;
        ClosureUtils.ifClosure(TruePredicate.truePredicate(), a).execute(null);

        a = new MockClosure<>();
        ClosureUtils.ifClosure(FalsePredicate.<Object>falsePredicate(), a).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.ifClosure(TruePredicate.<Object>truePredicate(), a, b).execute(null);
        assertEquals(0, b.count);
    }

    @Test
    public void testIfClosure_5_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = null;
        ClosureUtils.ifClosure(TruePredicate.truePredicate(), a).execute(null);

        a = new MockClosure<>();
        ClosureUtils.ifClosure(FalsePredicate.<Object>falsePredicate(), a).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.ifClosure(TruePredicate.<Object>truePredicate(), a, b).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.ifClosure(FalsePredicate.<Object>falsePredicate(), a, b).execute(null);
        assertEquals(0, a.count);
    }

    @Test
    public void testIfClosure_6_oe() {
        MockClosure<Object> a = new MockClosure<>();
        MockClosure<Object> b = null;
        ClosureUtils.ifClosure(TruePredicate.truePredicate(), a).execute(null);

        a = new MockClosure<>();
        ClosureUtils.ifClosure(FalsePredicate.<Object>falsePredicate(), a).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.ifClosure(TruePredicate.<Object>truePredicate(), a, b).execute(null);

        a = new MockClosure<>();
        b = new MockClosure<>();
        ClosureUtils.ifClosure(FalsePredicate.<Object>falsePredicate(), a, b).execute(null);
        assertEquals(1, b.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_1_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");
        assertEquals(0, a.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_2_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");
        assertEquals(0, b.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_3_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");
        assertEquals(1, a.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_4_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");
        assertEquals(0, b.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_5_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");
        assertEquals(0, a.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_6_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");
        assertEquals(0, b.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_7_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");
        assertEquals(1, c.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_8_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);
        assertEquals(0, a.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_9_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);
        assertEquals(0, b.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_10_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.switchClosure(map).execute("THERE");
        assertEquals(0, a.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_11_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.switchClosure(map).execute("THERE");
        assertEquals(1, b.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_12_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.switchClosure(map).execute("THERE");

        a.reset();
        b.reset();
        c.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);
        ClosureUtils.switchClosure(map).execute("WELL");
        assertEquals(0, a.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_13_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.switchClosure(map).execute("THERE");

        a.reset();
        b.reset();
        c.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);
        ClosureUtils.switchClosure(map).execute("WELL");
        assertEquals(0, b.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_14_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.switchClosure(map).execute("THERE");

        a.reset();
        b.reset();
        c.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);
        ClosureUtils.switchClosure(map).execute("WELL");
        assertEquals(1, c.count);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_15_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.switchClosure(map).execute("THERE");

        a.reset();
        b.reset();
        c.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);
        ClosureUtils.switchClosure(map).execute("WELL");

        assertEquals(NOPClosure.INSTANCE, ClosureUtils.<String>switchClosure(new Predicate[0], new Closure[0]));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_16_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.switchClosure(map).execute("THERE");

        a.reset();
        b.reset();
        c.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);
        ClosureUtils.switchClosure(map).execute("WELL");

        assertEquals(NOPClosure.INSTANCE, ClosureUtils.<String>switchClosure(new HashMap<Predicate<String>, Closure<String>>()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchClosure_17_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("WELL");

        a.reset();
        b.reset();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }).execute("HELLO");

        a.reset();
        b.reset();
        final MockClosure<String> c = new MockClosure<>();
        ClosureUtils.<String>switchClosure(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Closure[] { a, b }, c).execute("WELL");

        a.reset();
        b.reset();
        final Map<Predicate<String>, Closure<String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.<String>switchClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        ClosureUtils.switchClosure(map).execute("THERE");

        a.reset();
        b.reset();
        c.reset();
        map.clear();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);
        ClosureUtils.switchClosure(map).execute("WELL");

        map.clear();
        map.put(null, null);
        assertEquals(NOPClosure.INSTANCE, ClosureUtils.switchClosure(map));
    }

    @Test
    public void testSwitchMapClosure_1_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        final Map<String, Closure<String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute(null);
        assertEquals(0, a.count);
    }

    @Test
    public void testSwitchMapClosure_2_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        final Map<String, Closure<String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute(null);
        assertEquals(0, b.count);
    }

    @Test
    public void testSwitchMapClosure_3_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        final Map<String, Closure<String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute("THERE");
        assertEquals(0, a.count);
    }

    @Test
    public void testSwitchMapClosure_4_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        final Map<String, Closure<String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute("THERE");
        assertEquals(1, b.count);
    }

    @Test
    public void testSwitchMapClosure_5_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        final Map<String, Closure<String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute("THERE");

        a.reset();
        b.reset();
        map.clear();
        final MockClosure<String> c = new MockClosure<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        map.put(null, c);
        ClosureUtils.switchMapClosure(map).execute("WELL");
        assertEquals(0, a.count);
    }

    @Test
    public void testSwitchMapClosure_6_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        final Map<String, Closure<String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute("THERE");

        a.reset();
        b.reset();
        map.clear();
        final MockClosure<String> c = new MockClosure<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        map.put(null, c);
        ClosureUtils.switchMapClosure(map).execute("WELL");
        assertEquals(0, b.count);
    }

    @Test
    public void testSwitchMapClosure_7_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        final Map<String, Closure<String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute("THERE");

        a.reset();
        b.reset();
        map.clear();
        final MockClosure<String> c = new MockClosure<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        map.put(null, c);
        ClosureUtils.switchMapClosure(map).execute("WELL");
        assertEquals(1, c.count);
    }

    @Test
    public void testSwitchMapClosure_8_oe() {
        final MockClosure<String> a = new MockClosure<>();
        final MockClosure<String> b = new MockClosure<>();
        final Map<String, Closure<String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute(null);

        a.reset();
        b.reset();
        map.clear();
        map.put("HELLO", a);
        map.put("THERE", b);
        ClosureUtils.switchMapClosure(map).execute("THERE");

        a.reset();
        b.reset();
        map.clear();
        final MockClosure<String> c = new MockClosure<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        map.put(null, c);
        ClosureUtils.switchMapClosure(map).execute("WELL");

        assertEquals(NOPClosure.INSTANCE, ClosureUtils.switchMapClosure(new HashMap<String, Closure<String>>()));
    }

    @Test
    public void testTransformerClosure_1_oe() {
        final MockTransformer<Object> mock = new MockTransformer<>();
        final Closure<Object> closure = ClosureUtils.asClosure(mock);
        closure.execute(null);
        assertEquals(1, mock.count);
    }

    @Test
    public void testTransformerClosure_2_oe() {
        final MockTransformer<Object> mock = new MockTransformer<>();
        final Closure<Object> closure = ClosureUtils.asClosure(mock);
        closure.execute(null);
        closure.execute(null);
        assertEquals(2, mock.count);
    }

    @Test
    public void testTransformerClosure_3_oe() {
        final MockTransformer<Object> mock = new MockTransformer<>();
        final Closure<Object> closure = ClosureUtils.asClosure(mock);
        closure.execute(null);
        closure.execute(null);

        assertEquals(ClosureUtils.nopClosure(), ClosureUtils.asClosure(null));
    }

@Test
    public void testSingletonPatternInSerialization_1_oe() {
        final Object[] singletones = new Object[] {
                ExceptionClosure.INSTANCE,
                NOPClosure.INSTANCE,
        };

        for (final Object original : singletones) {
            TestUtils.assertSameAfterSerialization( "Singletone patern broken for " + original.getClass(), original );
    }
    }

@Test
    public void testWhileClosure_oe_101_oe() {
        try {
            ClosureUtils.whileClosure(null, ClosureUtils.nopClosure());
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testWhileClosure_oe_102_oe() {
        try {
            ClosureUtils.whileClosure(FalsePredicate.falsePredicate(), null);
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testWhileClosure_oe_103_oe() {
        try {
            ClosureUtils.whileClosure(null, null);
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testDoWhileClosure_oe_101_oe() {
        try {
            ClosureUtils.doWhileClosure(null, null);
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testSwitchMapClosure_oe_101_oe() {
        try {
            ClosureUtils.switchMapClosure(null);
            fail();
        } catch (final NullPointerException ex) {}
    }

}
