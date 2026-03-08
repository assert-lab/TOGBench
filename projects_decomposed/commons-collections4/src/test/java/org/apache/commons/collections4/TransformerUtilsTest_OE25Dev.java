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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionTransformer;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.NOPTransformer;
import org.apache.commons.collections4.functors.StringValueTransformer;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.Test;

/**
 * Tests the TransformerUtils class.
 *
 * @since 3.0
 */
public class TransformerUtilsTest_OE25Dev {

    private static final Object cObject = new Object();
    private static final Object cString = "Hello";
    private static final Object cInteger = Integer.valueOf(6);

    // exceptionTransformer
    //------------------------------------------------------------------

    // nullTransformer
    //------------------------------------------------------------------

    // nopTransformer
    //------------------------------------------------------------------

    // constantTransformer
    //------------------------------------------------------------------

    // cloneTransformer
    //------------------------------------------------------------------

    // mapTransformer
    //------------------------------------------------------------------

    // commandTransformer
    //------------------------------------------------------------------

    // predicateTransformer
    //------------------------------------------------------------------

    // factoryTransformer
    //------------------------------------------------------------------

    // chainedTransformer
    //------------------------------------------------------------------

    // ifTransformer
    //------------------------------------------------------------------

    // switchTransformer
    //------------------------------------------------------------------

    // switchMapTransformer
    //------------------------------------------------------------------

    // invokerTransformer
    //------------------------------------------------------------------

    // invokerTransformer2
    //------------------------------------------------------------------

    // stringValueTransformer
    //------------------------------------------------------------------

    // instantiateFactory
    //------------------------------------------------------------------

    @Test
    public void testInstantiateTransformerNull() {
        try {
            TransformerUtils.instantiateTransformer(null, new Object[] { "str" });
            fail();
        } catch (final IllegalArgumentException ex) {}
        try {
            TransformerUtils.instantiateTransformer(new Class[] {}, new Object[] { "str" });
            fail();
        } catch (final IllegalArgumentException ex) {}

        Transformer<Class<?>, Object> trans = TransformerUtils.instantiateTransformer(new Class[] { Long.class }, new Object[] { null });
        try {
            trans.transform(String.class);
            fail();
        } catch (final FunctorException ex) {}

        trans = TransformerUtils.instantiateTransformer();
        assertEquals("", trans.transform(String.class));

        trans = TransformerUtils.instantiateTransformer(new Class[] { Long.TYPE }, new Object[] { new Long(1000L) });
        assertEquals(new Date(1000L), trans.transform(Date.class));
    }

    // misc tests
    //------------------------------------------------------------------

    /**
     * Test that all Transformer singletons hold singleton pattern in
     * serialization/deserialization process.
     */

    @Test
    public void testExceptionTransformer_1_oe() {
        Object a = TransformerUtils.exceptionTransformer();
        assertNotNull(a);
    }

    @Test
    public void testExceptionTransformer_2_oe() {
        Object a = TransformerUtils.exceptionTransformer();
        assertSame(TransformerUtils.exceptionTransformer(), a);
    }

    @Test
    public void testExceptionTransformer_3_oe() {
        try {
            TransformerUtils.exceptionTransformer().transform(null);
        } catch (final FunctorException ex) {
            try {
                TransformerUtils.exceptionTransformer().transform(cString);
            } catch (final FunctorException ex2) {
                return;
            }
        }
        fail();
    }

    @Test
    public void testNullTransformer_1_oe() {
        Object a = TransformerUtils.nullTransformer();
        assertNotNull(a);
    }

    @Test
    public void testNullTransformer_2_oe() {
        Object a = TransformerUtils.nullTransformer();
        assertSame(TransformerUtils.nullTransformer(), a);
    }

    @Test
    public void testNullTransformer_3_oe() {
        Object a = TransformerUtils.nullTransformer().transform(null);
        assertEquals(null, a);
    }

    @Test
    public void testNullTransformer_4_oe() {
        Object a = TransformerUtils.nullTransformer().transform(cObject);
        assertEquals(null, a);
    }

    @Test
    public void testNullTransformer_5_oe() {
        Object a = TransformerUtils.nullTransformer().transform(cString);
        assertEquals(null, a);
    }

    @Test
    public void testNullTransformer_6_oe() {
        Object a = TransformerUtils.nullTransformer().transform(cInteger);
        assertEquals(null, a);
    }

    @Test
    public void testNopTransformer_1_oe() {
        Object a = TransformerUtils.nullTransformer();
        assertNotNull(a);
    }

    @Test
    public void testNopTransformer_2_oe() {
        Object a = TransformerUtils.nullTransformer();
        assertSame(TransformerUtils.nullTransformer(), a);
    }

    @Test
    public void testNopTransformer_3_oe() {
        Object a = TransformerUtils.nopTransformer().transform(null);
        assertEquals(null, a);
    }

    @Test
    public void testNopTransformer_4_oe() {
        Object a = TransformerUtils.nopTransformer().transform(cObject);
        assertEquals(cObject, a);
    }

    @Test
    public void testNopTransformer_5_oe() {
        Object a = TransformerUtils.nopTransformer().transform(cString);
        assertEquals(cString, a);
    }

    @Test
    public void testNopTransformer_6_oe() {
        Object a = TransformerUtils.nopTransformer().transform(cInteger);
        assertEquals(cInteger, a);
    }

    @Test
    public void testConstantTransformer_1_oe() {
        Object a = TransformerUtils.constantTransformer(cObject).transform(null);
        assertEquals(cObject, a);
    }

    @Test
    public void testConstantTransformer_2_oe() {
        Object a = TransformerUtils.constantTransformer(cObject).transform(cObject);
        assertEquals(cObject, a);
    }

    @Test
    public void testConstantTransformer_3_oe() {
        Object a = TransformerUtils.constantTransformer(cObject).transform(cString);
        assertEquals(cObject, a);
    }

    @Test
    public void testConstantTransformer_4_oe() {
        Object a = TransformerUtils.constantTransformer(cObject).transform(cInteger);
        assertEquals(cObject, a);
    }

    @Test
    public void testConstantTransformer_5_oe() {
        Object a = TransformerUtils.constantTransformer(null);
        assertSame(ConstantTransformer.NULL_INSTANCE, a);
    }

    @Test
    public void testCloneTransformer_1_oe() {
        Object a = TransformerUtils.cloneTransformer().transform(null);
        assertEquals(null, a);
    }

    @Test
    public void testCloneTransformer_2_oe() {
        Object a = TransformerUtils.cloneTransformer().transform(cString);
        assertEquals(cString, a);
    }

    @Test
    public void testCloneTransformer_3_oe() {
        Object a = TransformerUtils.cloneTransformer().transform(cInteger);
        assertEquals(cInteger, a);
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testMapTransformer_1_oe() {
        final Map<Object, Integer> map = new HashMap<>();
        map.put(null, 0);
        map.put(cObject, 1);
        map.put(cString, 2);
        assertEquals(Integer.valueOf(0), TransformerUtils.mapTransformer(map).transform(null));
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testMapTransformer_2_oe() {
        final Map<Object, Integer> map = new HashMap<>();
        map.put(null, 0);
        map.put(cObject, 1);
        map.put(cString, 2);
        assertEquals(Integer.valueOf(1), TransformerUtils.mapTransformer(map).transform(cObject));
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testMapTransformer_3_oe() {
        final Map<Object, Integer> map = new HashMap<>();
        map.put(null, 0);
        map.put(cObject, 1);
        map.put(cString, 2);
        assertEquals(Integer.valueOf(2), TransformerUtils.mapTransformer(map).transform(cString));
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testMapTransformer_4_oe() {
        final Map<Object, Integer> map = new HashMap<>();
        map.put(null, 0);
        map.put(cObject, 1);
        map.put(cString, 2);
        assertEquals(null, TransformerUtils.mapTransformer(map).transform(cInteger));
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testMapTransformer_5_oe() {
        final Map<Object, Integer> map = new HashMap<>();
        map.put(null, 0);
        map.put(cObject, 1);
        map.put(cString, 2);
        assertSame(ConstantTransformer.NULL_INSTANCE, TransformerUtils.mapTransformer(null));
    }

    @Test
    public void testExecutorTransformer_1_oe() {
        Object a = TransformerUtils.asTransformer(ClosureUtils.nopClosure()).transform(null);
        assertEquals(null, a);
    }

    @Test
    public void testExecutorTransformer_2_oe() {
        Object a = TransformerUtils.asTransformer(ClosureUtils.nopClosure()).transform(cObject);
        assertEquals(cObject, a);
    }

    @Test
    public void testExecutorTransformer_3_oe() {
        Object a = TransformerUtils.asTransformer(ClosureUtils.nopClosure()).transform(cString);
        assertEquals(cString, a);
    }

    @Test
    public void testExecutorTransformer_4_oe() {
        Object a = TransformerUtils.asTransformer(ClosureUtils.nopClosure()).transform(cInteger);
        assertEquals(cInteger, a);
    }

    @Test
    public void testExecutorTransformer_5_oe() {
        try {
            TransformerUtils.asTransformer((Closure<Object>) null);
        } catch (final NullPointerException ex) {
            return;
        }
        fail();
    }

    @Test
    public void testPredicateTransformer_1_oe() {
        Object a = TransformerUtils.asTransformer(TruePredicate.truePredicate()).transform(null);
        assertEquals(Boolean.TRUE, a);
    }

    @Test
    public void testPredicateTransformer_2_oe() {
        Object a = TransformerUtils.asTransformer(TruePredicate.truePredicate()).transform(cObject);
        assertEquals(Boolean.TRUE, a);
    }

    @Test
    public void testPredicateTransformer_3_oe() {
        Object a = TransformerUtils.asTransformer(TruePredicate.truePredicate()).transform(cString);
        assertEquals(Boolean.TRUE, a);
    }

    @Test
    public void testPredicateTransformer_4_oe() {
        Object a = TransformerUtils.asTransformer(TruePredicate.truePredicate()).transform(cInteger);
        assertEquals(Boolean.TRUE, a);
    }

    @Test
    public void testPredicateTransformer_5_oe() {
        try {
            TransformerUtils.asTransformer((Predicate<Object>) null);
        } catch (final IllegalArgumentException ex) {
            return;
        }
        fail();
    }

    @Test
    public void testFactoryTransformer_1_oe() {
        Object a = TransformerUtils.asTransformer(FactoryUtils.nullFactory()).transform(null);
        assertEquals(null, a);
    }

    @Test
    public void testFactoryTransformer_2_oe() {
        Object a = TransformerUtils.asTransformer(FactoryUtils.nullFactory()).transform(cObject);
        assertEquals(null, a);
    }

    @Test
    public void testFactoryTransformer_3_oe() {
        Object a = TransformerUtils.asTransformer(FactoryUtils.nullFactory()).transform(cString);
        assertEquals(null, a);
    }

    @Test
    public void testFactoryTransformer_4_oe() {
        Object a = TransformerUtils.asTransformer(FactoryUtils.nullFactory()).transform(cInteger);
        assertEquals(null, a);
    }

    @Test
    public void testFactoryTransformer_5_oe() {
        try {
            TransformerUtils.asTransformer((Factory<Object>) null);
        } catch (final NullPointerException ex) {
            return;
        }
        fail();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedTransformer_1_oe() {
        final Transformer<Object, Object> a = TransformerUtils.<Object, Object>constantTransformer("A");
        final Transformer<Object, Object> b = TransformerUtils.constantTransformer((Object) "B");

        assertEquals("A", TransformerUtils.chainedTransformer(b, a).transform(null));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedTransformer_2_oe() {
        final Transformer<Object, Object> a = TransformerUtils.<Object, Object>constantTransformer("A");
        final Transformer<Object, Object> b = TransformerUtils.constantTransformer((Object) "B");

        assertEquals("B", TransformerUtils.chainedTransformer(a, b).transform(null));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedTransformer_3_oe() {
        final Transformer<Object, Object> a = TransformerUtils.<Object, Object>constantTransformer("A");
        final Transformer<Object, Object> b = TransformerUtils.constantTransformer((Object) "B");

        assertEquals("A", TransformerUtils.chainedTransformer(new Transformer[] { b, a }).transform(null));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedTransformer_4_oe() {
        final Transformer<Object, Object> a = TransformerUtils.<Object, Object>constantTransformer("A");
        final Transformer<Object, Object> b = TransformerUtils.constantTransformer((Object) "B");

        Collection<Transformer<Object, Object>> coll = new ArrayList<>();
        coll.add(b);
        coll.add(a);
        assertEquals("A", TransformerUtils.chainedTransformer(coll).transform(null));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedTransformer_5_oe() {
        final Transformer<Object, Object> a = TransformerUtils.<Object, Object>constantTransformer("A");
        final Transformer<Object, Object> b = TransformerUtils.constantTransformer((Object) "B");

        Collection<Transformer<Object, Object>> coll = new ArrayList<>();
        coll.add(b);
        coll.add(a);

        assertSame(NOPTransformer.INSTANCE, TransformerUtils.chainedTransformer(new Transformer[0]));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testChainedTransformer_6_oe() {
        final Transformer<Object, Object> a = TransformerUtils.<Object, Object>constantTransformer("A");
        final Transformer<Object, Object> b = TransformerUtils.constantTransformer((Object) "B");

        Collection<Transformer<Object, Object>> coll = new ArrayList<>();
        coll.add(b);
        coll.add(a);

        assertSame(NOPTransformer.INSTANCE, TransformerUtils.chainedTransformer(Collections.<Transformer<Object, Object>>emptyList()));
    }

    @Test
    public void testIfTransformer_1_oe() {
        final Transformer<Object, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<Object, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<Object, String> c = TransformerUtils.constantTransformer("C");

        assertEquals("A", TransformerUtils.ifTransformer(TruePredicate.truePredicate(), a, b).transform(null));
    }

    @Test
    public void testIfTransformer_2_oe() {
        final Transformer<Object, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<Object, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<Object, String> c = TransformerUtils.constantTransformer("C");

        assertEquals("B", TransformerUtils.ifTransformer(FalsePredicate.falsePredicate(), a, b).transform(null));
    }

    @Test
    public void testIfTransformer_3_oe() {
        final Transformer<Object, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<Object, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<Object, String> c = TransformerUtils.constantTransformer("C");


        final Predicate<Integer> lessThanFivePredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer value) {
                return value < 5;
            }
        };
        assertEquals("A", TransformerUtils.<Integer, String>ifTransformer(lessThanFivePredicate, a, b).transform(1));
    }

    @Test
    public void testIfTransformer_4_oe() {
        final Transformer<Object, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<Object, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<Object, String> c = TransformerUtils.constantTransformer("C");


        final Predicate<Integer> lessThanFivePredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer value) {
                return value < 5;
            }
        };
        assertEquals("B", TransformerUtils.<Integer, String>ifTransformer(lessThanFivePredicate, a, b).transform(5));
    }

    @Test
    public void testIfTransformer_5_oe() {
        final Transformer<Object, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<Object, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<Object, String> c = TransformerUtils.constantTransformer("C");


        final Predicate<Integer> lessThanFivePredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer value) {
                return value < 5;
            }
        };

        final Predicate<String> equalsAPredicate = EqualPredicate.equalPredicate("A");
        assertEquals("C", TransformerUtils.<String>ifTransformer(equalsAPredicate, c).transform("A"));
    }

    @Test
    public void testIfTransformer_6_oe() {
        final Transformer<Object, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<Object, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<Object, String> c = TransformerUtils.constantTransformer("C");


        final Predicate<Integer> lessThanFivePredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer value) {
                return value < 5;
            }
        };

        final Predicate<String> equalsAPredicate = EqualPredicate.equalPredicate("A");
        assertEquals("B", TransformerUtils.<String>ifTransformer(equalsAPredicate, c).transform("B"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_1_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");

        assertEquals("A", TransformerUtils.switchTransformer(TruePredicate.truePredicate(), a, b).transform(null));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_2_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");

        assertEquals("B", TransformerUtils.switchTransformer(FalsePredicate.falsePredicate(), a, b).transform(null));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_3_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");


        assertEquals(null, TransformerUtils.<Object, String>switchTransformer( new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") }, new Transformer[] { a, b }).transform("WELL"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_4_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");


        assertEquals("A", TransformerUtils.switchTransformer( new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") }, new Transformer[] { a, b }).transform("HELLO"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_5_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");


        assertEquals("B", TransformerUtils.switchTransformer( new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") }, new Transformer[] { a, b }).transform("THERE"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_6_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");



        assertEquals("C", TransformerUtils.switchTransformer( new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") }, new Transformer[] { a, b }, c).transform("WELL"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_7_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");




        Map<Predicate<String>, Transformer<String, String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        assertEquals(null, TransformerUtils.switchTransformer(map).transform("WELL"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_8_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");




        Map<Predicate<String>, Transformer<String, String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        assertEquals("A", TransformerUtils.switchTransformer(map).transform("HELLO"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_9_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");




        Map<Predicate<String>, Transformer<String, String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        assertEquals("B", TransformerUtils.switchTransformer(map).transform("THERE"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_10_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");




        Map<Predicate<String>, Transformer<String, String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);
        assertEquals("C", TransformerUtils.switchTransformer(map).transform("WELL"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_11_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");




        Map<Predicate<String>, Transformer<String, String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);

        assertEquals(ConstantTransformer.NULL_INSTANCE, TransformerUtils.switchTransformer(new Predicate[0], new Transformer[0]));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_12_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");




        Map<Predicate<String>, Transformer<String, String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);

        assertEquals(ConstantTransformer.NULL_INSTANCE, TransformerUtils.switchTransformer(new HashMap<Predicate<Object>, Transformer<Object, Object>>()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSwitchTransformer_13_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");




        Map<Predicate<String>, Transformer<String, String>> map = new HashMap<>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        map.put(null, c);

        map = new HashMap<>();
        map.put(null, null);
        assertEquals(ConstantTransformer.NULL_INSTANCE, TransformerUtils.switchTransformer(map));
    }

    @Test
    public void testSwitchMapTransformer_1_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");

        Map<String, Transformer<String, String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        assertEquals(null, TransformerUtils.switchMapTransformer(map).transform("WELL"));
    }

    @Test
    public void testSwitchMapTransformer_2_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");

        Map<String, Transformer<String, String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        assertEquals("A", TransformerUtils.switchMapTransformer(map).transform("HELLO"));
    }

    @Test
    public void testSwitchMapTransformer_3_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");

        Map<String, Transformer<String, String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        assertEquals("B", TransformerUtils.switchMapTransformer(map).transform("THERE"));
    }

    @Test
    public void testSwitchMapTransformer_4_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");

        Map<String, Transformer<String, String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        map.put(null, c);
        assertEquals("C", TransformerUtils.switchMapTransformer(map).transform("WELL"));
    }

    @Test
    public void testSwitchMapTransformer_5_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");

        Map<String, Transformer<String, String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        map.put(null, c);

        assertSame(ConstantTransformer.NULL_INSTANCE, TransformerUtils.switchMapTransformer(new HashMap<Object, Transformer<Object, Object>>()));
    }

    @Test
    public void testSwitchMapTransformer_6_oe() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");

        Map<String, Transformer<String, String>> map = new HashMap<>();
        map.put("HELLO", a);
        map.put("THERE", b);
        map.put(null, c);

        map = new HashMap<>();
        map.put(null, null);
        assertSame(ConstantTransformer.NULL_INSTANCE, TransformerUtils.switchMapTransformer(map));
    }

    @Test
    public void testInvokerTransformer_1_oe() {
        final List<Object> list = new ArrayList<>();
        assertEquals(Integer.valueOf(0), TransformerUtils.invokerTransformer("size").transform(list));
    }

    @Test
    public void testInvokerTransformer_2_oe() {
        final List<Object> list = new ArrayList<>();
        list.add(new Object());
        assertEquals(Integer.valueOf(1), TransformerUtils.invokerTransformer("size").transform(list));
    }

    @Test
    public void testInvokerTransformer_3_oe() {
        final List<Object> list = new ArrayList<>();
        list.add(new Object());
        assertEquals(null, TransformerUtils.invokerTransformer("size").transform(null));
    }

    @Test
    public void testInvokerTransformer2_1_oe() {
        final List<Object> list = new ArrayList<>();
        assertEquals(Boolean.FALSE, TransformerUtils.invokerTransformer("contains", new Class[] { Object.class }, new Object[] { cString }).transform(list));
    }

    @Test
    public void testInvokerTransformer2_2_oe() {
        final List<Object> list = new ArrayList<>();
        list.add(cString);
        assertEquals(Boolean.TRUE, TransformerUtils.invokerTransformer("contains", new Class[] { Object.class }, new Object[] { cString }).transform(list));
    }

    @Test
    public void testInvokerTransformer2_3_oe() {
        final List<Object> list = new ArrayList<>();
        list.add(cString);
        assertEquals(null, TransformerUtils.invokerTransformer("contains", new Class[] { Object.class }, new Object[] { cString }).transform(null));
    }

    @Test
    public void testStringValueTransformer_1_oe() {
        Object a = TransformerUtils.stringValueTransformer().transform(null);
        assertNotNull("StringValueTransformer should NEVER return a null value.", a);
    }

    @Test
    public void testStringValueTransformer_2_oe() {
        Object a = TransformerUtils.stringValueTransformer().transform(null);
        assertEquals("StringValueTransformer should return \"null\" when given a null argument.", "null", a);
    }

    @Test
    public void testStringValueTransformer_3_oe() {
        Object a = TransformerUtils.stringValueTransformer().transform(Integer.valueOf(6));
        assertEquals("StringValueTransformer should return toString value", "6", a);
    }

@Test
    public void testSingletonPatternInSerialization_1_oe() {
        final Object[] singletones = new Object[] {
                ExceptionTransformer.INSTANCE,
                NOPTransformer.INSTANCE,
                StringValueTransformer.stringValueTransformer(),
        };

        for (final Object original : singletones) {
            TestUtils.assertSameAfterSerialization("Singleton pattern broken for " + original.getClass(), original);
    }
    }

@Test
    public void testIfTransformer_oe_101_oe() {
        try {
            TransformerUtils.ifTransformer(null, null);
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testIfTransformer_oe_102_oe() {
        try {
            TransformerUtils.ifTransformer(TruePredicate.truePredicate(), null);
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testIfTransformer_oe_103_oe() {
        try {
            TransformerUtils.ifTransformer(null, ConstantTransformer.constantTransformer("A"));
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testIfTransformer_oe_104_oe() {
        try {
            TransformerUtils.ifTransformer(null, null, null);
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testSwitchMapTransformer_oe_101_oe() {
        try {
            TransformerUtils.switchMapTransformer(null);
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testInvokerTransformer_oe_101_oe() {
        try {
            TransformerUtils.invokerTransformer(null);
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testInvokerTransformer_oe_102_oe() {
        try {
            TransformerUtils.invokerTransformer("noSuchMethod").transform(new Object());
            fail();
        } catch (final FunctorException ex) {}
    }

@Test
    public void testInvokerTransformer2_oe_101_oe() {
        try {
            TransformerUtils.invokerTransformer(null, null, null);
            fail();
        } catch (final NullPointerException ex) {}
    }

@Test
    public void testInvokerTransformer2_oe_102_oe() {
        try {
            TransformerUtils.invokerTransformer("noSuchMethod", new Class[] { Object.class },
                    new Object[] { cString }).transform(new Object());
            fail();
        } catch (final FunctorException ex) {}
    }

@Test
    public void testInvokerTransformer2_oe_103_oe() {
        try {
            TransformerUtils.invokerTransformer("badArgs", null, new Object[] { cString });
            fail();
        } catch (final IllegalArgumentException ex) {}
    }

@Test
    public void testInvokerTransformer2_oe_104_oe() {
        try {
            TransformerUtils.invokerTransformer("badArgs", new Class[] { Object.class }, null);
            fail();
        } catch (final IllegalArgumentException ex) {}
    }

@Test
    public void testInvokerTransformer2_oe_105_oe() {
        try {
            TransformerUtils.invokerTransformer("badArgs", new Class[] {}, new Object[] { cString });
            fail();
        } catch (final IllegalArgumentException ex) {}
    }

@Test
    public void testInstantiateTransformerNull_oe_101_oe() {
        try {
            TransformerUtils.instantiateTransformer(null, new Object[] { "str" });
            fail();
        } catch (final IllegalArgumentException ex) {}
    }

@Test
    public void testInstantiateTransformerNull_oe_102_oe() {
        try {
            TransformerUtils.instantiateTransformer(new Class[] {}, new Object[] { "str" });
            fail();
        } catch (final IllegalArgumentException ex) {}
    }

}
