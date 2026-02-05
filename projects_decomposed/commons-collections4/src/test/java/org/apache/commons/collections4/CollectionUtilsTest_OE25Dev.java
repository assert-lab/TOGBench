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

import static org.apache.commons.collections4.functors.EqualPredicate.equalPredicate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.collection.PredicatedCollection;
import org.apache.commons.collections4.collection.SynchronizedCollection;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.functors.DefaultEquator;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for CollectionUtils.
 *
 */
@SuppressWarnings("boxing")
public class CollectionUtilsTest_OE25Dev extends MockTestCase {

    /**
     * Collection of {@link Integer}s
     */
    private List<Integer> collectionA = null;

    /**
     * Collection of {@link Long}s
     */
    private List<Long> collectionB = null;

    /**
     * Collection of {@link Integer}s that are equivalent to the Longs in
     * collectionB.
     */
    private Collection<Integer> collectionC = null;

    /**
     * Sorted Collection of {@link Integer}s
     */
    private Collection<Integer> collectionD = null;

    /**
     * Sorted Collection of {@link Integer}s
     */
    private Collection<Integer> collectionE = null;

    /**
     * Collection of {@link Integer}s, bound as {@link Number}s
     */
    private Collection<Number> collectionA2 = null;

    /**
     * Collection of {@link Long}s, bound as {@link Number}s
     */
    private Collection<Number> collectionB2 = null;

    /**
     * Collection of {@link Integer}s (cast as {@link Number}s) that are
     * equivalent to the Longs in collectionB.
     */
    private Collection<Number> collectionC2 = null;

    private Iterable<Integer> iterableA = null;

    private Iterable<Long> iterableB = null;

    private Iterable<Integer> iterableC = null;

    private Iterable<Number> iterableA2 = null;

    private Iterable<Number> iterableB2 = null;

    private final Collection<Integer> emptyCollection = new ArrayList<>(1);

    @Before
    public void setUp() {
        collectionA = new ArrayList<>();
        collectionA.add(1);
        collectionA.add(2);
        collectionA.add(2);
        collectionA.add(3);
        collectionA.add(3);
        collectionA.add(3);
        collectionA.add(4);
        collectionA.add(4);
        collectionA.add(4);
        collectionA.add(4);
        collectionB = new LinkedList<>();
        collectionB.add(5L);
        collectionB.add(4L);
        collectionB.add(4L);
        collectionB.add(3L);
        collectionB.add(3L);
        collectionB.add(3L);
        collectionB.add(2L);
        collectionB.add(2L);
        collectionB.add(2L);
        collectionB.add(2L);

        collectionC = new ArrayList<>();
        for (final Long l : collectionB) {
            collectionC.add(l.intValue());
        }

        iterableA = collectionA;
        iterableB = collectionB;
        iterableC = collectionC;
        collectionA2 = new ArrayList<>(collectionA);
        collectionB2 = new LinkedList<>(collectionB);
        collectionC2 = new LinkedList<>(collectionC);
        iterableA2 = collectionA2;
        iterableB2 = collectionB2;

        collectionD = new ArrayList<>();
        collectionD.add(1);
        collectionD.add(3);
        collectionD.add(3);
        collectionD.add(3);
        collectionD.add(5);
        collectionD.add(7);
        collectionD.add(7);
        collectionD.add(10);

        collectionE = new ArrayList<>();
        collectionE.add(2);
        collectionE.add(4);
        collectionE.add(4);
        collectionE.add(5);
        collectionE.add(6);
        collectionE.add(6);
        collectionE.add(9);
    }

    @Test(expected=NullPointerException.class)
    public void testIsEqualCollectionNullEquator() {
        CollectionUtils.isEqualCollection(collectionA, collectionA, null);
    }

    @Test(expected = FunctorException.class)
    @Deprecated
    public void forAllDoFailure() {
        final Closure<String> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<String> col = new ArrayList<>();
        col.add("x");
        CollectionUtils.forAllDo(col, testClosure);
    }

    /**
     * Tests that {@link List}s are handled correctly - e.g. using
     * {@link List#get(int)}.
     */

    @Test(expected=IllegalArgumentException.class)
    public void getFromObject() throws Exception {
        // Invalid object
        final Object obj = new Object();
        CollectionUtils.get(obj, 0);
    }

    // -----------------------------------------------------------------------

    @Test(expected=IllegalArgumentException.class)
    public void testSize_Other() {
        CollectionUtils.size("not a list");
    }

    // -----------------------------------------------------------------------

    @Test
    public void testSizeIsEmpty_Other() {
        try {
            CollectionUtils.sizeIsEmpty("not a list");
            fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
        }
    }

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------
    private static Predicate<Number> EQUALS_TWO = new Predicate<Number>() {
        @Override
        public boolean evaluate(final Number input) {
            return input.intValue() == 2;
        }
    };

//Up to here

    private void assertCollectResult(final Collection<Number> collection) {
        assertTrue(collectionA.contains(1) && !collectionA.contains(2L));
        assertTrue(collection.contains(2L) && !collection.contains(1));
    }

    Transformer<Object, Integer> TRANSFORM_TO_INTEGER = new Transformer<Object, Integer>() {
        @Override
        public Integer transform(final Object input) {
            return Integer.valueOf(((Long)input).intValue());
        }
    };

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------
    //Up to here

    // -----------------------------------------------------------------------

    /**
     * This test ensures that {@link Iterable}s are supported by {@link CollectionUtils}.
     * Specifically, it uses mocks to ensure that if the passed in
     * {@link Iterable} is a {@link Collection} then
     * {@link Collection#addAll(Collection)} is called instead of iterating.
     */

    @Test(expected=IndexOutOfBoundsException.class)
    public void getNegative() {
        CollectionUtils.get((Object)collectionA, -3);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void getPositiveOutOfBounds() {
        CollectionUtils.get((Object)collectionA.iterator(), 30);
    }

    @Test(expected=IllegalArgumentException.class)
    public void get1() {
        CollectionUtils.get((Object)null, 0);
    }

    /**
     * Records the next object returned for a mock iterator
     */
    private <T> void next(final Iterator<T> iterator, final T t) {
        expect(iterator.hasNext()).andReturn(true);
        expect(iterator.next()).andReturn(t);
    }

    @Test(expected=NullPointerException.class)
    public void collateException1() {
        CollectionUtils.collate(collectionA, null);
    }

    @Test(expected=NullPointerException.class)
    public void collateException2() {
        CollectionUtils.collate(collectionA, collectionC, null);
    }

    @Test(expected=NullPointerException.class)
    public void testPermutationsWithNullCollection() {
        CollectionUtils.permutations(null);
    }

@Test
    public void getCardinalityMap_1_oe() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        assertEquals(1, (int) freqA.get(1));
    }

@Test
    public void getCardinalityMap_2_oe() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        // removed other assertion
        assertEquals(2, (int) freqA.get(2));
    }

@Test
    public void getCardinalityMap_3_oe() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        // removed other assertion
        // removed other assertion
        assertEquals(3, (int) freqA.get(3));
    }

@Test
    public void getCardinalityMap_4_oe() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, (int) freqA.get(4));
    }

@Test
    public void getCardinalityMap_5_oe() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(freqA.get(5));
    }

@Test
    public void getCardinalityMap_6_oe() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Map<Long, Integer> freqB = CollectionUtils.getCardinalityMap(iterableB);
        assertNull(freqB.get(1L));
    }

@Test
    public void getCardinalityMap_7_oe() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Map<Long, Integer> freqB = CollectionUtils.getCardinalityMap(iterableB);
        // removed other assertion
        assertEquals(4, (int) freqB.get(2L));
    }

@Test
    public void getCardinalityMap_8_oe() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Map<Long, Integer> freqB = CollectionUtils.getCardinalityMap(iterableB);
        // removed other assertion
        // removed other assertion
        assertEquals(3, (int) freqB.get(3L));
    }

@Test
    public void getCardinalityMap_9_oe() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Map<Long, Integer> freqB = CollectionUtils.getCardinalityMap(iterableB);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, (int) freqB.get(4L));
    }

@Test
    public void getCardinalityMap_10_oe() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Map<Long, Integer> freqB = CollectionUtils.getCardinalityMap(iterableB);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, (int) freqB.get(5L));
    }

@Test
    @Deprecated
    public void cardinality_1_oe() {
        assertEquals(1, CollectionUtils.cardinality(1, iterableA));
    }

@Test
    @Deprecated
    public void cardinality_2_oe() {
        // removed other assertion
        assertEquals(2, CollectionUtils.cardinality(2, iterableA));
    }

@Test
    @Deprecated
    public void cardinality_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(3, CollectionUtils.cardinality(3, iterableA));
    }

@Test
    @Deprecated
    public void cardinality_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, CollectionUtils.cardinality(4, iterableA));
    }

@Test
    @Deprecated
    public void cardinality_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, CollectionUtils.cardinality(5, iterableA));
    }

@Test
    @Deprecated
    public void cardinality_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, CollectionUtils.cardinality(1L, iterableB));
    }

@Test
    @Deprecated
    public void cardinality_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(4, CollectionUtils.cardinality(2L, iterableB));
    }

@Test
    @Deprecated
    public void cardinality_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(3, CollectionUtils.cardinality(3L, iterableB));
    }

@Test
    @Deprecated
    public void cardinality_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, CollectionUtils.cardinality(4L, iterableB));
    }

@Test
    @Deprecated
    public void cardinality_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, CollectionUtils.cardinality(5L, iterableB));
    }

@Test
    @Deprecated
    public void cardinality_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        assertEquals(0, CollectionUtils.cardinality(2L, iterableA2));
    }

@Test
    @Deprecated
    public void cardinality_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        assertEquals(0, CollectionUtils.cardinality(2, iterableB2));
    }

@Test
    @Deprecated
    public void cardinality_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        assertEquals(1, CollectionUtils.cardinality("A", set));
    }

@Test
    @Deprecated
    public void cardinality_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        assertEquals(0, CollectionUtils.cardinality("B", set));
    }

@Test
    @Deprecated
    public void cardinality_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        // removed other assertion
        assertEquals(1, CollectionUtils.cardinality("C", set));
    }

@Test
    @Deprecated
    public void cardinality_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, CollectionUtils.cardinality("D", set));
    }

@Test
    @Deprecated
    public void cardinality_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, CollectionUtils.cardinality("E", set));
    }

@Test
    @Deprecated
    public void cardinality_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Bag<String> bag = new HashBag<>();
        bag.add("A", 3);
        bag.add("C");
        bag.add("E");
        bag.add("E");
        assertEquals(3, CollectionUtils.cardinality("A", bag));
    }

@Test
    @Deprecated
    public void cardinality_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Bag<String> bag = new HashBag<>();
        bag.add("A", 3);
        bag.add("C");
        bag.add("E");
        bag.add("E");
        // removed other assertion
        assertEquals(0, CollectionUtils.cardinality("B", bag));
    }

@Test
    @Deprecated
    public void cardinality_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Bag<String> bag = new HashBag<>();
        bag.add("A", 3);
        bag.add("C");
        bag.add("E");
        bag.add("E");
        // removed other assertion
        // removed other assertion
        assertEquals(1, CollectionUtils.cardinality("C", bag));
    }

@Test
    @Deprecated
    public void cardinality_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Bag<String> bag = new HashBag<>();
        bag.add("A", 3);
        bag.add("C");
        bag.add("E");
        bag.add("E");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, CollectionUtils.cardinality("D", bag));
    }

@Test
    @Deprecated
    public void cardinality_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Bag<String> bag = new HashBag<>();
        bag.add("A", 3);
        bag.add("C");
        bag.add("E");
        bag.add("E");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, CollectionUtils.cardinality("E", bag));
    }

@Test
    @Deprecated
    public void cardinalityOfNull_1_oe() {
        final List<String> list = new ArrayList<>();
        assertEquals(0, CollectionUtils.cardinality(null, list));
    }

@Test
    @Deprecated
    public void cardinalityOfNull_2_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertNull(freq.get(null));
    }
    }

@Test
    @Deprecated
    public void cardinalityOfNull_3_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        assertEquals(0, CollectionUtils.cardinality(null, list));
    }

@Test
    @Deprecated
    public void cardinalityOfNull_4_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertNull(freq.get(null));
    }
    }

@Test
    @Deprecated
    public void cardinalityOfNull_5_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        assertEquals(1, CollectionUtils.cardinality(null, list));
    }

@Test
    @Deprecated
    public void cardinalityOfNull_6_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertEquals(Integer.valueOf(1), freq.get(null));
    }
    }

@Test
    @Deprecated
    public void cardinalityOfNull_7_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        assertEquals(1, CollectionUtils.cardinality(null, list));
    }

@Test
    @Deprecated
    public void cardinalityOfNull_8_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertEquals(Integer.valueOf(1), freq.get(null));
    }
    }

@Test
    @Deprecated
    public void cardinalityOfNull_9_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        assertEquals(2, CollectionUtils.cardinality(null, list));
    }

@Test
    @Deprecated
    public void cardinalityOfNull_10_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertEquals(Integer.valueOf(2), freq.get(null));
    }
    }

@Test
    @Deprecated
    public void cardinalityOfNull_11_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        assertEquals(2, CollectionUtils.cardinality(null, list));
    }

@Test
    @Deprecated
    public void cardinalityOfNull_12_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertEquals(Integer.valueOf(2), freq.get(null));
    }
    }

@Test
    @Deprecated
    public void cardinalityOfNull_13_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        assertEquals(3, CollectionUtils.cardinality(null, list));
    }

@Test
    @Deprecated
    public void cardinalityOfNull_14_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("A");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add("B");
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            // removed other assertion
        }
        list.add(null);
        // removed other assertion
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertEquals(Integer.valueOf(3), freq.get(null));
    }
    }

@Test
    public void containsAll_1_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        assertTrue("containsAll({1},{1,3}) should return false.", !CollectionUtils.containsAll(one, odds));
    }

@Test
    public void containsAll_2_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        assertTrue("containsAll({1,3},{1}) should return true.", CollectionUtils.containsAll(odds, one));
    }

@Test
    public void containsAll_3_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        assertTrue("containsAll({3},{1,3}) should return false.", !CollectionUtils.containsAll(three, odds));
    }

@Test
    public void containsAll_4_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAll({1,3},{3}) should return true.", CollectionUtils.containsAll(odds, three));
    }

@Test
    public void containsAll_5_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAll({2},{2}) should return true.", CollectionUtils.containsAll(two, two));
    }

@Test
    public void containsAll_6_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAll({1,3},{1,3}) should return true.", CollectionUtils.containsAll(odds, odds));
    }

@Test
    public void containsAll_7_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("containsAll({2},{1,3}) should return false.", !CollectionUtils.containsAll(two, odds));
    }

@Test
    public void containsAll_8_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("containsAll({1,3},{2}) should return false.", !CollectionUtils.containsAll(odds, two));
    }

@Test
    public void containsAll_9_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue("containsAll({1},{3}) should return false.", !CollectionUtils.containsAll(one, three));
    }

@Test
    public void containsAll_10_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAll({3},{1}) should return false.", !CollectionUtils.containsAll(three, one));
    }

@Test
    public void containsAll_11_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAll({1,3},{}) should return true.", CollectionUtils.containsAll(odds, empty));
    }

@Test
    public void containsAll_12_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAll({},{1,3}) should return false.", !CollectionUtils.containsAll(empty, odds));
    }

@Test
    public void containsAll_13_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAll({},{}) should return true.", CollectionUtils.containsAll(empty, empty));
    }

@Test
    public void containsAll_14_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("containsAll({1,3},{1,3,1}) should return true.", CollectionUtils.containsAll(odds, multiples));
    }

@Test
    public void containsAll_15_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("containsAll({1,3,1},{1,3,1}) should return true.", CollectionUtils.containsAll(odds, odds));
    }

@Test
    public void containsAnyInCollection_1_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        assertTrue("containsAny({1},{1,3}) should return true.", CollectionUtils.containsAny(one, odds));
    }

@Test
    public void containsAnyInCollection_2_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        assertTrue("containsAny({1,3},{1}) should return true.", CollectionUtils.containsAny(odds, one));
    }

@Test
    public void containsAnyInCollection_3_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({3},{1,3}) should return true.", CollectionUtils.containsAny(three, odds));
    }

@Test
    public void containsAnyInCollection_4_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({1,3},{3}) should return true.", CollectionUtils.containsAny(odds, three));
    }

@Test
    public void containsAnyInCollection_5_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({2},{2}) should return true.", CollectionUtils.containsAny(two, two));
    }

@Test
    public void containsAnyInCollection_6_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({1,3},{1,3}) should return true.", CollectionUtils.containsAny(odds, odds));
    }

@Test
    public void containsAnyInCollection_7_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("containsAny({2},{1,3}) should return false.", !CollectionUtils.containsAny(two, odds));
    }

@Test
    public void containsAnyInCollection_8_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("containsAny({1,3},{2}) should return false.", !CollectionUtils.containsAny(odds, two));
    }

@Test
    public void containsAnyInCollection_9_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({1},{3}) should return false.", !CollectionUtils.containsAny(one, three));
    }

@Test
    public void containsAnyInCollection_10_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({3},{1}) should return false.", !CollectionUtils.containsAny(three, one));
    }

@Test
    public void containsAnyInCollection_11_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({1,3},{}) should return false.", !CollectionUtils.containsAny(odds, empty));
    }

@Test
    public void containsAnyInCollection_12_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({},{1,3}) should return false.", !CollectionUtils.containsAny(empty, odds));
    }

@Test
    public void containsAnyInCollection_13_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({},{}) should return false.", !CollectionUtils.containsAny(empty, empty));
    }

@Test
    public void containsAnyInArray_1_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        assertTrue("containsAny({1},{1,3}) should return true.", CollectionUtils.containsAny(one, oddsArr));
    }

@Test
    public void containsAnyInArray_2_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        assertTrue("containsAny({1,3},{1}) should return true.", CollectionUtils.containsAny(odds, oneArr));
    }

@Test
    public void containsAnyInArray_3_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({3},{1,3}) should return true.", CollectionUtils.containsAny(three, oddsArr));
    }

@Test
    public void containsAnyInArray_4_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({1,3},{3}) should return true.", CollectionUtils.containsAny(odds, threeArr));
    }

@Test
    public void containsAnyInArray_5_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({2},{2}) should return true.", CollectionUtils.containsAny(two, twoArr));
    }

@Test
    public void containsAnyInArray_6_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({1,3},{1,3}) should return true.", CollectionUtils.containsAny(odds, oddsArr));
    }

@Test
    public void containsAnyInArray_7_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("containsAny({2},{1,3}) should return false.", !CollectionUtils.containsAny(two, oddsArr));
    }

@Test
    public void containsAnyInArray_8_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("containsAny({1,3},{2}) should return false.", !CollectionUtils.containsAny(odds, twoArr));
    }

@Test
    public void containsAnyInArray_9_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({1},{3}) should return false.", !CollectionUtils.containsAny(one, threeArr));
    }

@Test
    public void containsAnyInArray_10_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({3},{1}) should return false.", !CollectionUtils.containsAny(three, oneArr));
    }

@Test
    public void containsAnyInArray_11_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({1,3},{}) should return false.", !CollectionUtils.containsAny(odds, emptyArr));
    }

@Test
    public void containsAnyInArray_12_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({},{1,3}) should return false.", !CollectionUtils.containsAny(empty, oddsArr));
    }

@Test
    public void containsAnyInArray_13_oe() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("containsAny({},{}) should return false.", !CollectionUtils.containsAny(empty, emptyArr));
    }

@Test
    public void union_1_oe() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        assertEquals(Integer.valueOf(1), freq.get(1));
    }

@Test
    public void union_2_oe() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        assertEquals(Integer.valueOf(4), freq.get(2));
    }

@Test
    public void union_3_oe() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(3), freq.get(3));
    }

@Test
    public void union_4_oe() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(4), freq.get(4));
    }

@Test
    public void union_5_oe() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1), freq.get(5));
    }

@Test
    public void union_6_oe() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.union(collectionC2, iterableA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        assertEquals(Integer.valueOf(1), freq2.get(1));
    }

@Test
    public void union_7_oe() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.union(collectionC2, iterableA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        assertEquals(Integer.valueOf(4), freq2.get(2));
    }

@Test
    public void union_8_oe() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.union(collectionC2, iterableA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(3), freq2.get(3));
    }

@Test
    public void union_9_oe() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.union(collectionC2, iterableA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(4), freq2.get(4));
    }

@Test
    public void union_10_oe() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.union(collectionC2, iterableA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1), freq2.get(5));
    }

@Test
    public void intersection_1_oe() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        assertNull(freq.get(1));
    }

@Test
    public void intersection_2_oe() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq.get(2));
    }

@Test
    public void intersection_3_oe() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(3), freq.get(3));
    }

@Test
    public void intersection_4_oe() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq.get(4));
    }

@Test
    public void intersection_5_oe() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(freq.get(5));
    }

@Test
    public void intersection_6_oe() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.intersection(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        assertNull(freq2.get(1));
    }

@Test
    public void intersection_7_oe() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.intersection(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq2.get(2));
    }

@Test
    public void intersection_8_oe() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.intersection(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(3), freq2.get(3));
    }

@Test
    public void intersection_9_oe() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.intersection(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq2.get(4));
    }

@Test
    public void intersection_10_oe() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.intersection(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(freq2.get(5));
    }

@Test
    public void disjunction_1_oe() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        assertEquals(Integer.valueOf(1), freq.get(1));
    }

@Test
    public void disjunction_2_oe() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq.get(2));
    }

@Test
    public void disjunction_3_oe() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        assertNull(freq.get(3));
    }

@Test
    public void disjunction_4_oe() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq.get(4));
    }

@Test
    public void disjunction_5_oe() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1), freq.get(5));
    }

@Test
    public void disjunction_6_oe() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.disjunction(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        assertEquals(Integer.valueOf(1), freq2.get(1));
    }

@Test
    public void disjunction_7_oe() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.disjunction(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq2.get(2));
    }

@Test
    public void disjunction_8_oe() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.disjunction(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        assertNull(freq2.get(3));
    }

@Test
    public void disjunction_9_oe() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.disjunction(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq2.get(4));
    }

@Test
    public void disjunction_10_oe() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.disjunction(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1), freq2.get(5));
    }

@Test
    public void testDisjunctionAsUnionMinusIntersection_1_oe() {
        final Collection<Number> dis = CollectionUtils.<Number>disjunction(collectionA, collectionC);
        final Collection<Number> un = CollectionUtils.<Number>union(collectionA, collectionC);
        final Collection<Number> inter = CollectionUtils.<Number>intersection(collectionA, collectionC);
        assertTrue(CollectionUtils.isEqualCollection(dis, CollectionUtils.subtract(un, inter)));
    }

@Test
    public void testDisjunctionAsSymmetricDifference_1_oe() {
        final Collection<Number> dis = CollectionUtils.<Number>disjunction(collectionA, collectionC);
        final Collection<Number> amb = CollectionUtils.<Number>subtract(collectionA, collectionC);
        final Collection<Number> bma = CollectionUtils.<Number>subtract(collectionC, collectionA);
        assertTrue(CollectionUtils.isEqualCollection(dis, CollectionUtils.union(amb, bma)));
    }

@Test
    public void testSubtract_1_oe() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        assertEquals(Integer.valueOf(1), freq.get(1));
    }

@Test
    public void testSubtract_2_oe() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        assertNull(freq.get(2));
    }

@Test
    public void testSubtract_3_oe() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        assertNull(freq.get(3));
    }

@Test
    public void testSubtract_4_oe() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq.get(4));
    }

@Test
    public void testSubtract_5_oe() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(freq.get(5));
    }

@Test
    public void testSubtract_6_oe() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.subtract(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        assertEquals(Integer.valueOf(1), freq2.get(5));
    }

@Test
    public void testSubtract_7_oe() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.subtract(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        assertNull(freq2.get(4));
    }

@Test
    public void testSubtract_8_oe() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.subtract(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        assertNull(freq2.get(3));
    }

@Test
    public void testSubtract_9_oe() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.subtract(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq2.get(2));
    }

@Test
    public void testSubtract_10_oe() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Collection<Number> col2 = CollectionUtils.subtract(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(freq2.get(1));
    }

@Test
    public void testSubtractWithPredicate_1_oe() {
        // greater than 3
        final Predicate<Number> predicate = new Predicate<Number>() {
            @Override
            public boolean evaluate(final Number n) {
                return n.longValue() > 3L;
            }
        };

        final Collection<Number> col = CollectionUtils.subtract(iterableA, collectionC, predicate);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col);
        assertEquals(Integer.valueOf(1), freq2.get(1));
    }

@Test
    public void testSubtractWithPredicate_2_oe() {
        // greater than 3
        final Predicate<Number> predicate = new Predicate<Number>() {
            @Override
            public boolean evaluate(final Number n) {
                return n.longValue() > 3L;
            }
        };

        final Collection<Number> col = CollectionUtils.subtract(iterableA, collectionC, predicate);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq2.get(2));
    }

@Test
    public void testSubtractWithPredicate_3_oe() {
        // greater than 3
        final Predicate<Number> predicate = new Predicate<Number>() {
            @Override
            public boolean evaluate(final Number n) {
                return n.longValue() > 3L;
            }
        };

        final Collection<Number> col = CollectionUtils.subtract(iterableA, collectionC, predicate);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(3), freq2.get(3));
    }

@Test
    public void testSubtractWithPredicate_4_oe() {
        // greater than 3
        final Predicate<Number> predicate = new Predicate<Number>() {
            @Override
            public boolean evaluate(final Number n) {
                return n.longValue() > 3L;
            }
        };

        final Collection<Number> col = CollectionUtils.subtract(iterableA, collectionC, predicate);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), freq2.get(4));
    }

@Test
    public void testSubtractWithPredicate_5_oe() {
        // greater than 3
        final Predicate<Number> predicate = new Predicate<Number>() {
            @Override
            public boolean evaluate(final Number n) {
                return n.longValue() > 3L;
            }
        };

        final Collection<Number> col = CollectionUtils.subtract(iterableA, collectionC, predicate);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(freq2.get(5));
    }

@Test
    public void testIsSubCollectionOfSelf_1_oe() {
        assertTrue(CollectionUtils.isSubCollection(collectionA, collectionA));
    }

@Test
    public void testIsSubCollectionOfSelf_2_oe() {
        // removed other assertion
        assertTrue(CollectionUtils.isSubCollection(collectionB, collectionB));
    }

@Test
    public void testIsSubCollection_1_oe() {
        assertTrue(!CollectionUtils.isSubCollection(collectionA, collectionC));
    }

@Test
    public void testIsSubCollection_2_oe() {
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionC, collectionA));
    }

@Test
    public void testIsSubCollection2_1_oe() {
        final Collection<Integer> c = new ArrayList<>();
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_2_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_3_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_4_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_5_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_6_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_7_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_8_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_9_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_10_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_11_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_12_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_13_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_14_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_15_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(4);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_16_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_17_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_18_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_19_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_20_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        assertTrue(!CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_21_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_22_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        assertTrue(CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsSubCollection2_23_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(5);
        assertTrue(!CollectionUtils.isSubCollection(c, collectionA));
    }

@Test
    public void testIsSubCollection2_24_oe() {
        final Collection<Integer> c = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        c.add(1);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(2);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(3);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(4);
        // removed other assertion
        // removed other assertion
        c.add(5);
        // removed other assertion
        assertTrue(CollectionUtils.isSubCollection(collectionA, c));
    }

@Test
    public void testIsEqualCollectionToSelf_1_oe() {
        assertTrue(CollectionUtils.isEqualCollection(collectionA, collectionA));
    }

@Test
    public void testIsEqualCollectionToSelf_2_oe() {
        // removed other assertion
        assertTrue(CollectionUtils.isEqualCollection(collectionB, collectionB));
    }

@Test
    public void testIsEqualCollection_1_oe() {
        assertTrue(!CollectionUtils.isEqualCollection(collectionA, collectionC));
    }

@Test
    public void testIsEqualCollection_2_oe() {
        // removed other assertion
        assertTrue(!CollectionUtils.isEqualCollection(collectionC, collectionA));
    }

@Test
    public void testIsEqualCollectionReturnsFalse_1_oe() {
        final List<Integer> b = new ArrayList<>(collectionA);
        // remove an extra '2', and add a 5.  This will increase the size of the cardinality
        b.remove(1);
        b.add(5);
        assertFalse(CollectionUtils.isEqualCollection(collectionA, b));
    }

@Test
    public void testIsEqualCollectionReturnsFalse_2_oe() {
        final List<Integer> b = new ArrayList<>(collectionA);
        // remove an extra '2', and add a 5.  This will increase the size of the cardinality
        b.remove(1);
        b.add(5);
        // removed other assertion
        assertFalse(CollectionUtils.isEqualCollection(b, collectionA));
    }

@Test
    public void testIsEqualCollection2_1_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        assertTrue(CollectionUtils.isEqualCollection(a, b));
    }

@Test
    public void testIsEqualCollection2_2_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        assertTrue(CollectionUtils.isEqualCollection(b, a));
    }

@Test
    public void testIsEqualCollection2_3_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        assertTrue(!CollectionUtils.isEqualCollection(a, b));
    }

@Test
    public void testIsEqualCollection2_4_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        assertTrue(!CollectionUtils.isEqualCollection(b, a));
    }

@Test
    public void testIsEqualCollection2_5_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        assertTrue(CollectionUtils.isEqualCollection(a, b));
    }

@Test
    public void testIsEqualCollection2_6_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        // removed other assertion
        assertTrue(CollectionUtils.isEqualCollection(b, a));
    }

@Test
    public void testIsEqualCollection2_7_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        a.add("2");
        assertTrue(!CollectionUtils.isEqualCollection(a, b));
    }

@Test
    public void testIsEqualCollection2_8_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        a.add("2");
        // removed other assertion
        assertTrue(!CollectionUtils.isEqualCollection(b, a));
    }

@Test
    public void testIsEqualCollection2_9_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        a.add("2");
        // removed other assertion
        // removed other assertion
        b.add("2");
        assertTrue(CollectionUtils.isEqualCollection(a, b));
    }

@Test
    public void testIsEqualCollection2_10_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        a.add("2");
        // removed other assertion
        // removed other assertion
        b.add("2");
        // removed other assertion
        assertTrue(CollectionUtils.isEqualCollection(b, a));
    }

@Test
    public void testIsEqualCollection2_11_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        a.add("2");
        // removed other assertion
        // removed other assertion
        b.add("2");
        // removed other assertion
        // removed other assertion
        a.add("1");
        assertTrue(!CollectionUtils.isEqualCollection(a, b));
    }

@Test
    public void testIsEqualCollection2_12_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        a.add("2");
        // removed other assertion
        // removed other assertion
        b.add("2");
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        assertTrue(!CollectionUtils.isEqualCollection(b, a));
    }

@Test
    public void testIsEqualCollection2_13_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        a.add("2");
        // removed other assertion
        // removed other assertion
        b.add("2");
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        assertTrue(CollectionUtils.isEqualCollection(a, b));
    }

@Test
    public void testIsEqualCollection2_14_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        a.add("2");
        // removed other assertion
        // removed other assertion
        b.add("2");
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        b.add("1");
        // removed other assertion
        assertTrue(CollectionUtils.isEqualCollection(b, a));
    }

@Test
    public void testIsEqualCollectionEquator_1_oe() {
        final Collection<Integer> collB = CollectionUtils.collect(collectionB, TRANSFORM_TO_INTEGER);

        // odd / even equator
        final Equator<Integer> e = new Equator<Integer>() {
            @Override
            public boolean equate(final Integer o1, final Integer o2) {
                if (o1.intValue() % 2 == 0 ^ o2.intValue() % 2 == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public int hash(final Integer o) {
                return o.intValue() % 2 == 0 ? Integer.valueOf(0).hashCode() : Integer.valueOf(1).hashCode();
            }
        };

        assertTrue(CollectionUtils.isEqualCollection(collectionA, collectionA, e));
    }

@Test
    public void testIsEqualCollectionEquator_2_oe() {
        final Collection<Integer> collB = CollectionUtils.collect(collectionB, TRANSFORM_TO_INTEGER);

        // odd / even equator
        final Equator<Integer> e = new Equator<Integer>() {
            @Override
            public boolean equate(final Integer o1, final Integer o2) {
                if (o1.intValue() % 2 == 0 ^ o2.intValue() % 2 == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public int hash(final Integer o) {
                return o.intValue() % 2 == 0 ? Integer.valueOf(0).hashCode() : Integer.valueOf(1).hashCode();
            }
        };

        // removed other assertion
        assertTrue(CollectionUtils.isEqualCollection(collectionA, collB, e));
    }

@Test
    public void testIsEqualCollectionEquator_3_oe() {
        final Collection<Integer> collB = CollectionUtils.collect(collectionB, TRANSFORM_TO_INTEGER);

        // odd / even equator
        final Equator<Integer> e = new Equator<Integer>() {
            @Override
            public boolean equate(final Integer o1, final Integer o2) {
                if (o1.intValue() % 2 == 0 ^ o2.intValue() % 2 == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public int hash(final Integer o) {
                return o.intValue() % 2 == 0 ? Integer.valueOf(0).hashCode() : Integer.valueOf(1).hashCode();
            }
        };

        // removed other assertion
        // removed other assertion
        assertTrue(CollectionUtils.isEqualCollection(collB, collectionA, e));
    }

@Test
    public void testIsEqualCollectionEquator_4_oe() {
        final Collection<Integer> collB = CollectionUtils.collect(collectionB, TRANSFORM_TO_INTEGER);

        // odd / even equator
        final Equator<Integer> e = new Equator<Integer>() {
            @Override
            public boolean equate(final Integer o1, final Integer o2) {
                if (o1.intValue() % 2 == 0 ^ o2.intValue() % 2 == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public int hash(final Integer o) {
                return o.intValue() % 2 == 0 ? Integer.valueOf(0).hashCode() : Integer.valueOf(1).hashCode();
            }
        };

        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Equator<Number> defaultEquator = DefaultEquator.defaultEquator();
        assertFalse(CollectionUtils.isEqualCollection(collectionA, collectionB, defaultEquator));
    }

@Test
    public void testIsEqualCollectionEquator_5_oe() {
        final Collection<Integer> collB = CollectionUtils.collect(collectionB, TRANSFORM_TO_INTEGER);

        // odd / even equator
        final Equator<Integer> e = new Equator<Integer>() {
            @Override
            public boolean equate(final Integer o1, final Integer o2) {
                if (o1.intValue() % 2 == 0 ^ o2.intValue() % 2 == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public int hash(final Integer o) {
                return o.intValue() % 2 == 0 ? Integer.valueOf(0).hashCode() : Integer.valueOf(1).hashCode();
            }
        };

        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Equator<Number> defaultEquator = DefaultEquator.defaultEquator();
        // removed other assertion
        assertFalse(CollectionUtils.isEqualCollection(collectionA, collB, defaultEquator));
    }

@Test
    public void testIsProperSubCollection_1_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        assertTrue(!CollectionUtils.isProperSubCollection(a, b));
    }

@Test
    public void testIsProperSubCollection_2_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        b.add("1");
        assertTrue(CollectionUtils.isProperSubCollection(a, b));
    }

@Test
    public void testIsProperSubCollection_3_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        b.add("1");
        // removed other assertion
        assertTrue(!CollectionUtils.isProperSubCollection(b, a));
    }

@Test
    public void testIsProperSubCollection_4_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        assertTrue(!CollectionUtils.isProperSubCollection(b, b));
    }

@Test
    public void testIsProperSubCollection_5_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!CollectionUtils.isProperSubCollection(a, a));
    }

@Test
    public void testIsProperSubCollection_6_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        a.add("1");
        a.add("2");
        b.add("2");
        assertTrue(!CollectionUtils.isProperSubCollection(b, a));
    }

@Test
    public void testIsProperSubCollection_7_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        a.add("1");
        a.add("2");
        b.add("2");
        // removed other assertion
        assertTrue(!CollectionUtils.isProperSubCollection(a, b));
    }

@Test
    public void testIsProperSubCollection_8_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        a.add("1");
        a.add("2");
        b.add("2");
        // removed other assertion
        // removed other assertion
        a.add("1");
        assertTrue(CollectionUtils.isProperSubCollection(b, a));
    }

@Test
    public void testIsProperSubCollection_9_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        a.add("1");
        a.add("2");
        b.add("2");
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        assertTrue(CollectionUtils.isProperSubCollection(CollectionUtils.intersection(collectionA, collectionC), collectionA));
    }

@Test
    public void testIsProperSubCollection_10_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        a.add("1");
        a.add("2");
        b.add("2");
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        assertTrue(CollectionUtils.isProperSubCollection(CollectionUtils.subtract(a, b), a));
    }

@Test
    public void testIsProperSubCollection_11_oe() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        // removed other assertion
        b.add("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        a.add("1");
        a.add("2");
        b.add("2");
        // removed other assertion
        // removed other assertion
        a.add("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!CollectionUtils.isProperSubCollection(a, CollectionUtils.subtract(a, b)));
    }

@Test
    @Deprecated
    public void find_1_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = CollectionUtils.find(collectionA, testPredicate);
        assertTrue(test.equals(4));
    }

@Test
    @Deprecated
    public void find_2_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = CollectionUtils.find(collectionA, testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        test = CollectionUtils.find(collectionA, testPredicate);
        assertTrue(test == null);
    }

@Test
    @Deprecated
    public void find_3_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = CollectionUtils.find(collectionA, testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        test = CollectionUtils.find(collectionA, testPredicate);
        // removed other assertion
        assertNull(CollectionUtils.find(null,testPredicate));
    }

@Test
    @Deprecated
    public void find_4_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = CollectionUtils.find(collectionA, testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        test = CollectionUtils.find(collectionA, testPredicate);
        // removed other assertion
        // removed other assertion
        assertNull(CollectionUtils.find(collectionA, null));
    }

@Test
    @Deprecated
    public void forAllDoCollection_1_oe() {
        final Closure<Collection<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<Collection<Integer>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionC);
        Closure<Collection<Integer>> resultClosure = CollectionUtils.forAllDo(col, testClosure);
        assertSame(testClosure, resultClosure);
    }

@Test
    @Deprecated
    public void forAllDoCollection_2_oe() {
        final Closure<Collection<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<Collection<Integer>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionC);
        Closure<Collection<Integer>> resultClosure = CollectionUtils.forAllDo(col, testClosure);
        // removed other assertion
        assertTrue(collectionA.isEmpty() && collectionC.isEmpty());
    }

@Test
    @Deprecated
    public void forAllDoCollection_3_oe() {
        final Closure<Collection<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<Collection<Integer>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionC);
        Closure<Collection<Integer>> resultClosure = CollectionUtils.forAllDo(col, testClosure);
        // removed other assertion
        // removed other assertion
        // fix for various java 1.6 versions: keep the cast
        resultClosure = CollectionUtils.forAllDo(col, (Closure<Collection<Integer>>) null);
        assertNull(resultClosure);
    }

@Test
    @Deprecated
    public void forAllDoCollection_4_oe() {
        final Closure<Collection<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<Collection<Integer>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionC);
        Closure<Collection<Integer>> resultClosure = CollectionUtils.forAllDo(col, testClosure);
        // removed other assertion
        // removed other assertion
        // fix for various java 1.6 versions: keep the cast
        resultClosure = CollectionUtils.forAllDo(col, (Closure<Collection<Integer>>) null);
        // removed other assertion
        assertTrue(collectionA.isEmpty() && collectionC.isEmpty());
    }

@Test
    @Deprecated
    public void forAllDoIterator_1_oe() {
        final Closure<Collection<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<Collection<Integer>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionC);
        Closure<Collection<Integer>> resultClosure = CollectionUtils.forAllDo(col.iterator(), testClosure);
        assertSame(testClosure, resultClosure);
    }

@Test
    @Deprecated
    public void forAllDoIterator_2_oe() {
        final Closure<Collection<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<Collection<Integer>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionC);
        Closure<Collection<Integer>> resultClosure = CollectionUtils.forAllDo(col.iterator(), testClosure);
        // removed other assertion
        assertTrue(collectionA.isEmpty() && collectionC.isEmpty());
    }

@Test
    @Deprecated
    public void forAllDoIterator_3_oe() {
        final Closure<Collection<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<Collection<Integer>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionC);
        Closure<Collection<Integer>> resultClosure = CollectionUtils.forAllDo(col.iterator(), testClosure);
        // removed other assertion
        // removed other assertion
        // fix for various java 1.6 versions: keep the cast
        resultClosure = CollectionUtils.forAllDo(col.iterator(), (Closure<Collection<Integer>>) null);
        assertNull(resultClosure);
    }

@Test
    @Deprecated
    public void forAllDoIterator_4_oe() {
        final Closure<Collection<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<Collection<Integer>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionC);
        Closure<Collection<Integer>> resultClosure = CollectionUtils.forAllDo(col.iterator(), testClosure);
        // removed other assertion
        // removed other assertion
        // fix for various java 1.6 versions: keep the cast
        resultClosure = CollectionUtils.forAllDo(col.iterator(), (Closure<Collection<Integer>>) null);
        // removed other assertion
        assertTrue(collectionA.isEmpty() && collectionC.isEmpty());
    }

@Test
    @Deprecated
    public void forAllButLastDoCollection_1_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        assertSame(lastElement, collectionB);
    }

@Test
    @Deprecated
    public void forAllButLastDoCollection_2_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        assertTrue(collectionA.isEmpty() && !collectionB.isEmpty());
    }

@Test
    @Deprecated
    public void forAllButLastDoCollection_3_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        col.add(collectionB);
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        assertSame(lastElement, collectionB);
    }

@Test
    @Deprecated
    public void forAllButLastDoCollection_4_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        col.add(collectionB);
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        assertTrue(!collectionB.isEmpty() );
    }

@Test
    @Deprecated
    public void forAllButLastDoCollection_5_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        col.add(collectionB);
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        assertNull(lastElement);
    }

@Test
    @Deprecated
    public void forAllButLastDoCollection_6_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        col.add(collectionB);
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion

        final Collection<String> strings = Arrays.asList("a", "b", "c");
        final StringBuffer result = new StringBuffer();
        result.append(CollectionUtils.forAllButLastDo(strings, new Closure<String>() {
            @Override
            public void execute(final String input) {
                result.append(input+";");
            }
        }));
        assertEquals("a;b;c", result.toString());
    }

@Test
    @Deprecated
    public void forAllButLastDoCollection_7_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        col.add(collectionB);
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion

        final Collection<String> strings = Arrays.asList("a", "b", "c");
        final StringBuffer result = new StringBuffer();
        result.append(CollectionUtils.forAllButLastDo(strings, new Closure<String>() {
            @Override
            public void execute(final String input) {
                result.append(input+";");
            }
        }));
        // removed other assertion

        final Collection<String> oneString = Arrays.asList("a");
        final StringBuffer resultOne = new StringBuffer();
        resultOne.append(CollectionUtils.forAllButLastDo(oneString, new Closure<String>() {
            @Override
            public void execute(final String input) {
                resultOne.append(input+";");
            }
        }));
        assertEquals("a", resultOne.toString());
    }

@Test
    @Deprecated
    public void forAllButLastDoCollection_8_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        col.add(collectionB);
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion

        final Collection<String> strings = Arrays.asList("a", "b", "c");
        final StringBuffer result = new StringBuffer();
        result.append(CollectionUtils.forAllButLastDo(strings, new Closure<String>() {
            @Override
            public void execute(final String input) {
                result.append(input+";");
            }
        }));
        // removed other assertion

        final Collection<String> oneString = Arrays.asList("a");
        final StringBuffer resultOne = new StringBuffer();
        resultOne.append(CollectionUtils.forAllButLastDo(oneString, new Closure<String>() {
            @Override
            public void execute(final String input) {
                resultOne.append(input+";");
            }
        }));
        // removed other assertion
        assertNull(CollectionUtils.forAllButLastDo(strings, (Closure<String>) null)); // do not remove cast;
    }

@Test
    @Deprecated
    public void forAllButLastDoCollection_9_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        col.add(collectionB);
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion
        // removed other assertion

        col.clear();
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        // removed other assertion

        final Collection<String> strings = Arrays.asList("a", "b", "c");
        final StringBuffer result = new StringBuffer();
        result.append(CollectionUtils.forAllButLastDo(strings, new Closure<String>() {
            @Override
            public void execute(final String input) {
                result.append(input+";");
            }
        }));
        // removed other assertion

        final Collection<String> oneString = Arrays.asList("a");
        final StringBuffer resultOne = new StringBuffer();
        resultOne.append(CollectionUtils.forAllButLastDo(oneString, new Closure<String>() {
            @Override
            public void execute(final String input) {
                resultOne.append(input+";");
            }
        }));
        // removed other assertion
        // removed other assertion
        assertNull(CollectionUtils.forAllButLastDo((Collection<String>) null, (Closure<String>) null)); // do not remove cast;
    }

@Test
    @Deprecated
    public void forAllButLastDoIterator_1_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        final List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col.iterator(), testClosure);
        assertSame(lastElement, collectionB);
    }

@Test
    @Deprecated
    public void forAllButLastDoIterator_2_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        final List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col.iterator(), testClosure);
        // removed other assertion
        assertTrue(collectionA.isEmpty() && !collectionB.isEmpty());
    }

@Test
    @Deprecated
    public void forAllButLastDoIterator_3_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        final List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col.iterator(), testClosure);
        // removed other assertion
        // removed other assertion

        assertNull(CollectionUtils.forAllButLastDo(col.iterator(), (Closure<List<? extends Number>>) null));
    }

@Test
    @Deprecated
    public void forAllButLastDoIterator_4_oe() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        final List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col.iterator(), testClosure);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertNull(CollectionUtils.forAllButLastDo((Iterator<String>) null, (Closure<String>) null)); // do not remove cast;
    }

@Test
    public void getFromMap_1_oe() {
        // Unordered map, entries exist
        final Map<String, String> expected = new HashMap<>();
        expected.put("zeroKey", "zero");
        expected.put("oneKey", "one");

        final Map<String, String> found = new HashMap<>();
        Map.Entry<String, String> entry = CollectionUtils.get(expected, 0);
        found.put(entry.getKey(), entry.getValue());
        entry = CollectionUtils.get(expected, 1);
        found.put(entry.getKey(), entry.getValue());
        assertEquals(expected, found);
    }

@Test
    public void getFromMap_4_oe() {
        // Unordered map, entries exist
        final Map<String, String> expected = new HashMap<>();
        expected.put("zeroKey", "zero");
        expected.put("oneKey", "one");

        final Map<String, String> found = new HashMap<>();
        Map.Entry<String, String> entry = CollectionUtils.get(expected, 0);
        found.put(entry.getKey(), entry.getValue());
        entry = CollectionUtils.get(expected, 1);
        found.put(entry.getKey(), entry.getValue());
        // removed other assertion

        // Map index out of range
        try {
            CollectionUtils.get(expected, 2);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
        try {
            CollectionUtils.get(expected, -2);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }

        // Sorted map, entries exist, should respect order
        final SortedMap<String, String> map = new TreeMap<>();
        map.put("zeroKey", "zero");
        map.put("oneKey", "one");
        Map.Entry<String, String> test = CollectionUtils.get(map, 1);
        assertEquals("zeroKey", test.getKey());
    }

@Test
    public void getFromMap_5_oe() {
        // Unordered map, entries exist
        final Map<String, String> expected = new HashMap<>();
        expected.put("zeroKey", "zero");
        expected.put("oneKey", "one");

        final Map<String, String> found = new HashMap<>();
        Map.Entry<String, String> entry = CollectionUtils.get(expected, 0);
        found.put(entry.getKey(), entry.getValue());
        entry = CollectionUtils.get(expected, 1);
        found.put(entry.getKey(), entry.getValue());
        // removed other assertion

        // Map index out of range
        try {
            CollectionUtils.get(expected, 2);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
        try {
            CollectionUtils.get(expected, -2);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }

        // Sorted map, entries exist, should respect order
        final SortedMap<String, String> map = new TreeMap<>();
        map.put("zeroKey", "zero");
        map.put("oneKey", "one");
        Map.Entry<String, String> test = CollectionUtils.get(map, 1);
        // removed other assertion
        assertEquals("zero", test.getValue());
    }

@Test
    public void getFromMap_6_oe() {
        // Unordered map, entries exist
        final Map<String, String> expected = new HashMap<>();
        expected.put("zeroKey", "zero");
        expected.put("oneKey", "one");

        final Map<String, String> found = new HashMap<>();
        Map.Entry<String, String> entry = CollectionUtils.get(expected, 0);
        found.put(entry.getKey(), entry.getValue());
        entry = CollectionUtils.get(expected, 1);
        found.put(entry.getKey(), entry.getValue());
        // removed other assertion

        // Map index out of range
        try {
            CollectionUtils.get(expected, 2);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
        try {
            CollectionUtils.get(expected, -2);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }

        // Sorted map, entries exist, should respect order
        final SortedMap<String, String> map = new TreeMap<>();
        map.put("zeroKey", "zero");
        map.put("oneKey", "one");
        Map.Entry<String, String> test = CollectionUtils.get(map, 1);
        // removed other assertion
        // removed other assertion
        test = CollectionUtils.get(map, 0);
        assertEquals("oneKey", test.getKey());
    }

@Test
    public void getFromMap_7_oe() {
        // Unordered map, entries exist
        final Map<String, String> expected = new HashMap<>();
        expected.put("zeroKey", "zero");
        expected.put("oneKey", "one");

        final Map<String, String> found = new HashMap<>();
        Map.Entry<String, String> entry = CollectionUtils.get(expected, 0);
        found.put(entry.getKey(), entry.getValue());
        entry = CollectionUtils.get(expected, 1);
        found.put(entry.getKey(), entry.getValue());
        // removed other assertion

        // Map index out of range
        try {
            CollectionUtils.get(expected, 2);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
        try {
            CollectionUtils.get(expected, -2);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }

        // Sorted map, entries exist, should respect order
        final SortedMap<String, String> map = new TreeMap<>();
        map.put("zeroKey", "zero");
        map.put("oneKey", "one");
        Map.Entry<String, String> test = CollectionUtils.get(map, 1);
        // removed other assertion
        // removed other assertion
        test = CollectionUtils.get(map, 0);
        // removed other assertion
        assertEquals("one", test.getValue());
    }

@Test(expected=IndexOutOfBoundsException.class)
    public void getFromList_1_oe() throws Exception {
        // List, entry exists
        final List<String> list = createMock(List.class);
        expect(list.get(0)).andReturn("zero");
        expect(list.get(1)).andReturn("one");
        replay();
        final String string = CollectionUtils.get(list, 0);
        assertEquals("zero", string);
    }

@Test(expected=IndexOutOfBoundsException.class)
    public void getFromList_2_oe() throws Exception {
        // List, entry exists
        final List<String> list = createMock(List.class);
        expect(list.get(0)).andReturn("zero");
        expect(list.get(1)).andReturn("one");
        replay();
        final String string = CollectionUtils.get(list, 0);
        // removed other assertion
        assertEquals("one", CollectionUtils.get(list, 1));
    }

@Test
    @Deprecated
    public void getFromIterator_1_oe() throws Exception {
        // Iterator, entry exists
        Iterator<Integer> iterator = iterableA.iterator();
        assertEquals(1, (int) CollectionUtils.get(iterator, 0));
    }

@Test
    @Deprecated
    public void getFromIterator_2_oe() throws Exception {
        // Iterator, entry exists
        Iterator<Integer> iterator = iterableA.iterator();
        // removed other assertion
        iterator = iterableA.iterator();
        assertEquals(2, (int) CollectionUtils.get(iterator, 1));
    }

@Test
    @Deprecated
    public void getFromIterator_4_oe() throws Exception {
        // Iterator, entry exists
        Iterator<Integer> iterator = iterableA.iterator();
        // removed other assertion
        iterator = iterableA.iterator();
        // removed other assertion

        // Iterator, non-existent entry
        try {
            CollectionUtils.get(iterator, 10);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
        assertTrue(!iterator.hasNext());
    }

@Test
    @Deprecated
    public void getFromEnumeration_1_oe() throws Exception {
        // Enumeration, entry exists
        final Vector<String> vector = new Vector<>();
        vector.addElement("zero");
        vector.addElement("one");
        Enumeration<String> en = vector.elements();
        assertEquals("zero", CollectionUtils.get(en, 0));
    }

@Test
    @Deprecated
    public void getFromEnumeration_2_oe() throws Exception {
        // Enumeration, entry exists
        final Vector<String> vector = new Vector<>();
        vector.addElement("zero");
        vector.addElement("one");
        Enumeration<String> en = vector.elements();
        // removed other assertion
        en = vector.elements();
        assertEquals("one", CollectionUtils.get(en, 1));
    }

@Test
    @Deprecated
    public void getFromEnumeration_4_oe() throws Exception {
        // Enumeration, entry exists
        final Vector<String> vector = new Vector<>();
        vector.addElement("zero");
        vector.addElement("one");
        Enumeration<String> en = vector.elements();
        // removed other assertion
        en = vector.elements();
        // removed other assertion

        // Enumerator, non-existent entry
        try {
            CollectionUtils.get(en, 3);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
        assertTrue(!en.hasMoreElements());
    }

@Test(expected = IndexOutOfBoundsException.class)
    @Deprecated
    public void getFromIterable_1_oe() throws Exception {
        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        assertEquals("element", CollectionUtils.get(bag, 0));
    }

@Test(expected = IndexOutOfBoundsException.class)
    public void getFromObjectArray_1_oe() throws Exception {
        // Object array, entry exists
        final Object[] objArray = new Object[2];
        objArray[0] = "zero";
        objArray[1] = "one";
        assertEquals("zero", CollectionUtils.get(objArray, 0));
    }

@Test(expected = IndexOutOfBoundsException.class)
    public void getFromObjectArray_2_oe() throws Exception {
        // Object array, entry exists
        final Object[] objArray = new Object[2];
        objArray[0] = "zero";
        objArray[1] = "one";
        // removed other assertion
        assertEquals("one", CollectionUtils.get(objArray, 1));
    }

@Test(expected = IndexOutOfBoundsException.class)
    public void getFromPrimitiveArray_1_oe() throws Exception {
        // Primitive array, entry exists
        final int[] array = new int[2];
        array[0] = 10;
        array[1] = 20;
        assertEquals(10, CollectionUtils.get(array, 0));
    }

@Test(expected = IndexOutOfBoundsException.class)
    public void getFromPrimitiveArray_2_oe() throws Exception {
        // Primitive array, entry exists
        final int[] array = new int[2];
        array[0] = 10;
        array[1] = 20;
        // removed other assertion
        assertEquals(20, CollectionUtils.get(array, 1));
    }

@Test
    public void testSize_List_1_oe() {
        List<String> list = null;
        assertEquals(0, CollectionUtils.size(list));
    }

@Test
    public void testSize_List_2_oe() {
        List<String> list = null;
        // removed other assertion
        list = new ArrayList<>();
        assertEquals(0, CollectionUtils.size(list));
    }

@Test
    public void testSize_List_3_oe() {
        List<String> list = null;
        // removed other assertion
        list = new ArrayList<>();
        // removed other assertion
        list.add("a");
        assertEquals(1, CollectionUtils.size(list));
    }

@Test
    public void testSize_List_4_oe() {
        List<String> list = null;
        // removed other assertion
        list = new ArrayList<>();
        // removed other assertion
        list.add("a");
        // removed other assertion
        list.add("b");
        assertEquals(2, CollectionUtils.size(list));
    }

@Test
    public void testSize_Map_1_oe() {
        final Map<String, String> map = new HashMap<>();
        assertEquals(0, CollectionUtils.size(map));
    }

@Test
    public void testSize_Map_2_oe() {
        final Map<String, String> map = new HashMap<>();
        // removed other assertion
        map.put("1", "a");
        assertEquals(1, CollectionUtils.size(map));
    }

@Test
    public void testSize_Map_3_oe() {
        final Map<String, String> map = new HashMap<>();
        // removed other assertion
        map.put("1", "a");
        // removed other assertion
        map.put("2", "b");
        assertEquals(2, CollectionUtils.size(map));
    }

@Test
    public void testSize_Array_1_oe() {
        final Object[] objectArray = new Object[0];
        assertEquals(0, CollectionUtils.size(objectArray));
    }

@Test
    public void testSize_Array_2_oe() {
        final Object[] objectArray = new Object[0];
        // removed other assertion

        final String[] stringArray = new String[3];
        assertEquals(3, CollectionUtils.size(stringArray));
    }

@Test
    public void testSize_Array_3_oe() {
        final Object[] objectArray = new Object[0];
        // removed other assertion

        final String[] stringArray = new String[3];
        // removed other assertion
        stringArray[0] = "a";
        stringArray[1] = "b";
        stringArray[2] = "c";
        assertEquals(3, CollectionUtils.size(stringArray));
    }

@Test
    public void testSize_PrimitiveArray_1_oe() {
        final int[] intArray = new int[0];
        assertEquals(0, CollectionUtils.size(intArray));
    }

@Test
    public void testSize_PrimitiveArray_2_oe() {
        final int[] intArray = new int[0];
        // removed other assertion

        final double[] doubleArray = new double[3];
        assertEquals(3, CollectionUtils.size(doubleArray));
    }

@Test
    public void testSize_PrimitiveArray_3_oe() {
        final int[] intArray = new int[0];
        // removed other assertion

        final double[] doubleArray = new double[3];
        // removed other assertion
        doubleArray[0] = 0.0d;
        doubleArray[1] = 1.0d;
        doubleArray[2] = 2.5d;
        assertEquals(3, CollectionUtils.size(doubleArray));
    }

@Test
    public void testSize_Enumeration_1_oe() {
        final Vector<String> list = new Vector<>();
        assertEquals(0, CollectionUtils.size(list.elements()));
    }

@Test
    public void testSize_Enumeration_2_oe() {
        final Vector<String> list = new Vector<>();
        // removed other assertion
        list.add("a");
        assertEquals(1, CollectionUtils.size(list.elements()));
    }

@Test
    public void testSize_Enumeration_3_oe() {
        final Vector<String> list = new Vector<>();
        // removed other assertion
        list.add("a");
        // removed other assertion
        list.add("b");
        assertEquals(2, CollectionUtils.size(list.elements()));
    }

@Test
    public void testSize_Iterator_1_oe() {
        final List<String> list = new ArrayList<>();
        assertEquals(0, CollectionUtils.size(list.iterator()));
    }

@Test
    public void testSize_Iterator_2_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("a");
        assertEquals(1, CollectionUtils.size(list.iterator()));
    }

@Test
    public void testSize_Iterator_3_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("a");
        // removed other assertion
        list.add("b");
        assertEquals(2, CollectionUtils.size(list.iterator()));
    }

@Test
    public void testSizeIsEmpty_Null_1_oe() {
        assertEquals(true, CollectionUtils.sizeIsEmpty(null));
    }

@Test
    public void testSizeIsEmpty_List_1_oe() {
        final List<String> list = new ArrayList<>();
        assertEquals(true, CollectionUtils.sizeIsEmpty(list));
    }

@Test
    public void testSizeIsEmpty_List_2_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("a");
        assertEquals(false, CollectionUtils.sizeIsEmpty(list));
    }

@Test
    public void testSizeIsEmpty_Map_1_oe() {
        final Map<String, String> map = new HashMap<>();
        assertEquals(true, CollectionUtils.sizeIsEmpty(map));
    }

@Test
    public void testSizeIsEmpty_Map_2_oe() {
        final Map<String, String> map = new HashMap<>();
        // removed other assertion
        map.put("1", "a");
        assertEquals(false, CollectionUtils.sizeIsEmpty(map));
    }

@Test
    public void testSizeIsEmpty_Array_1_oe() {
        final Object[] objectArray = new Object[0];
        assertEquals(true, CollectionUtils.sizeIsEmpty(objectArray));
    }

@Test
    public void testSizeIsEmpty_Array_2_oe() {
        final Object[] objectArray = new Object[0];
        // removed other assertion

        final String[] stringArray = new String[3];
        assertEquals(false, CollectionUtils.sizeIsEmpty(stringArray));
    }

@Test
    public void testSizeIsEmpty_Array_3_oe() {
        final Object[] objectArray = new Object[0];
        // removed other assertion

        final String[] stringArray = new String[3];
        // removed other assertion
        stringArray[0] = "a";
        stringArray[1] = "b";
        stringArray[2] = "c";
        assertEquals(false, CollectionUtils.sizeIsEmpty(stringArray));
    }

@Test
    public void testSizeIsEmpty_PrimitiveArray_1_oe() {
        final int[] intArray = new int[0];
        assertEquals(true, CollectionUtils.sizeIsEmpty(intArray));
    }

@Test
    public void testSizeIsEmpty_PrimitiveArray_2_oe() {
        final int[] intArray = new int[0];
        // removed other assertion

        final double[] doubleArray = new double[3];
        assertEquals(false, CollectionUtils.sizeIsEmpty(doubleArray));
    }

@Test
    public void testSizeIsEmpty_PrimitiveArray_3_oe() {
        final int[] intArray = new int[0];
        // removed other assertion

        final double[] doubleArray = new double[3];
        // removed other assertion
        doubleArray[0] = 0.0d;
        doubleArray[1] = 1.0d;
        doubleArray[2] = 2.5d;
        assertEquals(false, CollectionUtils.sizeIsEmpty(doubleArray));
    }

@Test
    public void testSizeIsEmpty_Enumeration_1_oe() {
        final Vector<String> list = new Vector<>();
        assertEquals(true, CollectionUtils.sizeIsEmpty(list.elements()));
    }

@Test
    public void testSizeIsEmpty_Enumeration_2_oe() {
        final Vector<String> list = new Vector<>();
        // removed other assertion
        list.add("a");
        assertEquals(false, CollectionUtils.sizeIsEmpty(list.elements()));
    }

@Test
    public void testSizeIsEmpty_Enumeration_3_oe() {
        final Vector<String> list = new Vector<>();
        // removed other assertion
        list.add("a");
        // removed other assertion
        final Enumeration<String> en = list.elements();
        en.nextElement();
        assertEquals(true, CollectionUtils.sizeIsEmpty(en));
    }

@Test
    public void testSizeIsEmpty_Iterator_1_oe() {
        final List<String> list = new ArrayList<>();
        assertEquals(true, CollectionUtils.sizeIsEmpty(list.iterator()));
    }

@Test
    public void testSizeIsEmpty_Iterator_2_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("a");
        assertEquals(false, CollectionUtils.sizeIsEmpty(list.iterator()));
    }

@Test
    public void testSizeIsEmpty_Iterator_3_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("a");
        // removed other assertion
        final Iterator<String> it = list.iterator();
        it.next();
        assertEquals(true, CollectionUtils.sizeIsEmpty(it));
    }

@Test
    public void testIsEmptyWithEmptyCollection_1_oe() {
        final Collection<Object> coll = new ArrayList<>();
        assertEquals(true, CollectionUtils.isEmpty(coll));
    }

@Test
    public void testIsEmptyWithNonEmptyCollection_1_oe() {
        final Collection<String> coll = new ArrayList<>();
        coll.add("item");
        assertEquals(false, CollectionUtils.isEmpty(coll));
    }

@Test
    public void testIsEmptyWithNull_1_oe() {
        final Collection<?> coll = null;
        assertEquals(true, CollectionUtils.isEmpty(coll));
    }

@Test
    public void testIsNotEmptyWithEmptyCollection_1_oe() {
        final Collection<Object> coll = new ArrayList<>();
        assertEquals(false, CollectionUtils.isNotEmpty(coll));
    }

@Test
    public void testIsNotEmptyWithNonEmptyCollection_1_oe() {
        final Collection<String> coll = new ArrayList<>();
        coll.add("item");
        assertEquals(true, CollectionUtils.isNotEmpty(coll));
    }

@Test
    public void testIsNotEmptyWithNull_1_oe() {
        final Collection<?> coll = null;
        assertEquals(false, CollectionUtils.isNotEmpty(coll));
    }

@Test
    public void filter_1_oe() {
        final List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        final Iterable<Integer> iterable = ints;
        assertTrue(CollectionUtils.filter(iterable, EQUALS_TWO));
    }

@Test
    public void filter_2_oe() {
        final List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        final Iterable<Integer> iterable = ints;
        // removed other assertion
        assertEquals(1, ints.size());
    }

@Test
    public void filter_3_oe() {
        final List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        final Iterable<Integer> iterable = ints;
        // removed other assertion
        // removed other assertion
        assertEquals(2, (int) ints.get(0));
    }

@Test
    public void filterNullParameters_1_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        assertFalse(CollectionUtils.filter(longs, null));
    }

@Test
    public void filterNullParameters_2_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        // removed other assertion
        assertEquals(4, longs.size());
    }

@Test
    public void filterNullParameters_3_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        // removed other assertion
        // removed other assertion
        assertFalse(CollectionUtils.filter(null, EQUALS_TWO));
    }

@Test
    public void filterNullParameters_4_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, longs.size());
    }

@Test
    public void filterNullParameters_5_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CollectionUtils.filter(null, null));
    }

@Test
    public void filterNullParameters_6_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, longs.size());
    }

@Test
    public void filterInverse_1_oe() {
        final List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        final Iterable<Integer> iterable = ints;
        assertTrue(CollectionUtils.filterInverse(iterable, EQUALS_TWO));
    }

@Test
    public void filterInverse_2_oe() {
        final List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        final Iterable<Integer> iterable = ints;
        // removed other assertion
        assertEquals(3, ints.size());
    }

@Test
    public void filterInverse_3_oe() {
        final List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        final Iterable<Integer> iterable = ints;
        // removed other assertion
        // removed other assertion
        assertEquals(1, (int) ints.get(0));
    }

@Test
    public void filterInverse_4_oe() {
        final List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        final Iterable<Integer> iterable = ints;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, (int) ints.get(1));
    }

@Test
    public void filterInverse_5_oe() {
        final List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        final Iterable<Integer> iterable = ints;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, (int) ints.get(2));
    }

@Test
    public void filterInverseNullParameters_1_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        assertFalse(CollectionUtils.filterInverse(longs, null));
    }

@Test
    public void filterInverseNullParameters_2_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        // removed other assertion
        assertEquals(4, longs.size());
    }

@Test
    public void filterInverseNullParameters_3_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        // removed other assertion
        // removed other assertion
        assertFalse(CollectionUtils.filterInverse(null, EQUALS_TWO));
    }

@Test
    public void filterInverseNullParameters_4_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, longs.size());
    }

@Test
    public void filterInverseNullParameters_5_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CollectionUtils.filterInverse(null, null));
    }

@Test
    public void filterInverseNullParameters_6_oe() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, longs.size());
    }

@Test
    @Deprecated
    public void countMatches_1_oe() {
        assertEquals(4, CollectionUtils.countMatches(iterableB, EQUALS_TWO));
    }

@Test
    @Deprecated
    public void countMatches_2_oe() {
        // removed other assertion
        assertEquals(0, CollectionUtils.countMatches(iterableA, null));
    }

@Test
    @Deprecated
    public void countMatches_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, CollectionUtils.countMatches(null, EQUALS_TWO));
    }

@Test
    @Deprecated
    public void countMatches_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, CollectionUtils.countMatches(null, null));
    }

@Test
    @Deprecated
    public void exists_1_oe() {
        final List<Integer> list = new ArrayList<>();
        assertFalse(CollectionUtils.exists(null, null));
    }

@Test
    @Deprecated
    public void exists_2_oe() {
        final List<Integer> list = new ArrayList<>();
        // removed other assertion
        assertFalse(CollectionUtils.exists(list, null));
    }

@Test
    @Deprecated
    public void exists_3_oe() {
        final List<Integer> list = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        assertFalse(CollectionUtils.exists(null, EQUALS_TWO));
    }

@Test
    @Deprecated
    public void exists_4_oe() {
        final List<Integer> list = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CollectionUtils.exists(list, EQUALS_TWO));
    }

@Test
    @Deprecated
    public void exists_5_oe() {
        final List<Integer> list = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add(1);
        list.add(3);
        list.add(4);
        assertFalse(CollectionUtils.exists(list, EQUALS_TWO));
    }

@Test
    @Deprecated
    public void exists_6_oe() {
        final List<Integer> list = new ArrayList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add(1);
        list.add(3);
        list.add(4);
        // removed other assertion

        list.add(2);
        assertEquals(true, CollectionUtils.exists(list, EQUALS_TWO));
    }

@Test
    public void select_1_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // Ensure that the collection is the input type or a super type
        final Collection<Integer> output1 = CollectionUtils.select(list, EQUALS_TWO);
        final Collection<Number> output2 = CollectionUtils.<Number>select(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.select(list, EQUALS_TWO, new HashSet<Number>());
        assertTrue(CollectionUtils.isEqualCollection(output1, output3));
    }

@Test
    public void select_2_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // Ensure that the collection is the input type or a super type
        final Collection<Integer> output1 = CollectionUtils.select(list, EQUALS_TWO);
        final Collection<Number> output2 = CollectionUtils.<Number>select(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.select(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        assertEquals(4, list.size());
    }

@Test
    public void select_3_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // Ensure that the collection is the input type or a super type
        final Collection<Integer> output1 = CollectionUtils.select(list, EQUALS_TWO);
        final Collection<Number> output2 = CollectionUtils.<Number>select(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.select(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        assertEquals(1, output1.size());
    }

@Test
    public void select_4_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // Ensure that the collection is the input type or a super type
        final Collection<Integer> output1 = CollectionUtils.select(list, EQUALS_TWO);
        final Collection<Number> output2 = CollectionUtils.<Number>select(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.select(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, output2.iterator().next());
    }

@Test
    public void selectWithOutputCollections_1_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);

        final List<Integer> output = new ArrayList<>();
        final List<Integer> rejected = new ArrayList<>();

        CollectionUtils.select(input, EQUALS_TWO, output, rejected);

        // output contains 2
        assertEquals(1, output.size());
    }

@Test
    public void selectWithOutputCollections_2_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);

        final List<Integer> output = new ArrayList<>();
        final List<Integer> rejected = new ArrayList<>();

        CollectionUtils.select(input, EQUALS_TWO, output, rejected);

        // output contains 2
        // removed other assertion
        assertEquals(2, CollectionUtils.extractSingleton(output).intValue());
    }

@Test
    public void selectWithOutputCollections_3_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);

        final List<Integer> output = new ArrayList<>();
        final List<Integer> rejected = new ArrayList<>();

        CollectionUtils.select(input, EQUALS_TWO, output, rejected);

        // output contains 2
        // removed other assertion
        // removed other assertion

        // rejected contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        Assert.assertArrayEquals(expected, rejected.toArray());
    }

@Test
    public void selectWithOutputCollections_4_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);

        final List<Integer> output = new ArrayList<>();
        final List<Integer> rejected = new ArrayList<>();

        CollectionUtils.select(input, EQUALS_TWO, output, rejected);

        // output contains 2
        // removed other assertion
        // removed other assertion

        // rejected contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        // removed other assertion

        output.clear();
        rejected.clear();
        CollectionUtils.select((List<Integer>) null, EQUALS_TWO, output, rejected);
        assertTrue(output.isEmpty());
    }

@Test
    public void selectWithOutputCollections_5_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);

        final List<Integer> output = new ArrayList<>();
        final List<Integer> rejected = new ArrayList<>();

        CollectionUtils.select(input, EQUALS_TWO, output, rejected);

        // output contains 2
        // removed other assertion
        // removed other assertion

        // rejected contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        // removed other assertion

        output.clear();
        rejected.clear();
        CollectionUtils.select((List<Integer>) null, EQUALS_TWO, output, rejected);
        // removed other assertion
        assertTrue(rejected.isEmpty());
    }

@Test
    public void selectRejected_1_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final Collection<Long> output1 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final Collection<? extends Number> output2 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        assertTrue(CollectionUtils.isEqualCollection(output1, output2));
    }

@Test
    public void selectRejected_2_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final Collection<Long> output1 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final Collection<? extends Number> output2 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        assertTrue(CollectionUtils.isEqualCollection(output1, output3));
    }

@Test
    public void selectRejected_3_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final Collection<Long> output1 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final Collection<? extends Number> output2 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        assertEquals(4, list.size());
    }

@Test
    public void selectRejected_4_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final Collection<Long> output1 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final Collection<? extends Number> output2 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, output1.size());
    }

@Test
    public void selectRejected_5_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final Collection<Long> output1 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final Collection<? extends Number> output2 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(output1.contains(1L));
    }

@Test
    public void selectRejected_6_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final Collection<Long> output1 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final Collection<? extends Number> output2 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(output1.contains(3L));
    }

@Test
    public void selectRejected_7_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final Collection<Long> output1 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final Collection<? extends Number> output2 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(output1.contains(4L));
    }

@Test
    public void collect_1_oe() {
        final Transformer<Number, Long> transformer = TransformerUtils.constantTransformer(2L);
        Collection<Number> collection = CollectionUtils.<Integer, Number>collect(iterableA, transformer);
        assertTrue(collection.size() == collectionA.size());
    }

@Test
    public void collect_3_oe() {
        final Transformer<Number, Long> transformer = TransformerUtils.constantTransformer(2L);
        Collection<Number> collection = CollectionUtils.<Integer, Number>collect(iterableA, transformer);
        // removed other assertion
        // removed other assertion

        ArrayList<Number> list;
        list = CollectionUtils.collect(collectionA, transformer, new ArrayList<Number>());
        assertTrue(list.size() == collectionA.size());
    }

@Test
    public void collect_5_oe() {
        final Transformer<Number, Long> transformer = TransformerUtils.constantTransformer(2L);
        Collection<Number> collection = CollectionUtils.<Integer, Number>collect(iterableA, transformer);
        // removed other assertion
        // removed other assertion

        ArrayList<Number> list;
        list = CollectionUtils.collect(collectionA, transformer, new ArrayList<Number>());
        // removed other assertion
        // removed other assertion

        Iterator<Integer> iterator = null;
        list = CollectionUtils.collect(iterator, transformer, new ArrayList<Number>());

        iterator = iterableA.iterator();
        list = CollectionUtils.collect(iterator, transformer, list);
        assertTrue(collection.size() == collectionA.size());
    }

@Test
    public void collect_7_oe() {
        final Transformer<Number, Long> transformer = TransformerUtils.constantTransformer(2L);
        Collection<Number> collection = CollectionUtils.<Integer, Number>collect(iterableA, transformer);
        // removed other assertion
        // removed other assertion

        ArrayList<Number> list;
        list = CollectionUtils.collect(collectionA, transformer, new ArrayList<Number>());
        // removed other assertion
        // removed other assertion

        Iterator<Integer> iterator = null;
        list = CollectionUtils.collect(iterator, transformer, new ArrayList<Number>());

        iterator = iterableA.iterator();
        list = CollectionUtils.collect(iterator, transformer, list);
        // removed other assertion
        // removed other assertion

        iterator = collectionA.iterator();
        collection = CollectionUtils.<Integer, Number>collect(iterator, transformer);
        assertTrue(collection.size() == collectionA.size());
    }

@Test
    public void collect_8_oe() {
        final Transformer<Number, Long> transformer = TransformerUtils.constantTransformer(2L);
        Collection<Number> collection = CollectionUtils.<Integer, Number>collect(iterableA, transformer);
        // removed other assertion
        // removed other assertion

        ArrayList<Number> list;
        list = CollectionUtils.collect(collectionA, transformer, new ArrayList<Number>());
        // removed other assertion
        // removed other assertion

        Iterator<Integer> iterator = null;
        list = CollectionUtils.collect(iterator, transformer, new ArrayList<Number>());

        iterator = iterableA.iterator();
        list = CollectionUtils.collect(iterator, transformer, list);
        // removed other assertion
        // removed other assertion

        iterator = collectionA.iterator();
        collection = CollectionUtils.<Integer, Number>collect(iterator, transformer);
        // removed other assertion
        assertTrue(collection.contains(2L) && !collection.contains(1));
    }

@Test
    public void collect_9_oe() {
        final Transformer<Number, Long> transformer = TransformerUtils.constantTransformer(2L);
        Collection<Number> collection = CollectionUtils.<Integer, Number>collect(iterableA, transformer);
        // removed other assertion
        // removed other assertion

        ArrayList<Number> list;
        list = CollectionUtils.collect(collectionA, transformer, new ArrayList<Number>());
        // removed other assertion
        // removed other assertion

        Iterator<Integer> iterator = null;
        list = CollectionUtils.collect(iterator, transformer, new ArrayList<Number>());

        iterator = iterableA.iterator();
        list = CollectionUtils.collect(iterator, transformer, list);
        // removed other assertion
        // removed other assertion

        iterator = collectionA.iterator();
        collection = CollectionUtils.<Integer, Number>collect(iterator, transformer);
        // removed other assertion
        // removed other assertion
        collection = CollectionUtils.collect((Iterator<Integer>) null, (Transformer<Integer, Number>) null);
        assertTrue(collection.size() == 0);
    }

@Test
    public void collect_10_oe() {
        final Transformer<Number, Long> transformer = TransformerUtils.constantTransformer(2L);
        Collection<Number> collection = CollectionUtils.<Integer, Number>collect(iterableA, transformer);
        // removed other assertion
        // removed other assertion

        ArrayList<Number> list;
        list = CollectionUtils.collect(collectionA, transformer, new ArrayList<Number>());
        // removed other assertion
        // removed other assertion

        Iterator<Integer> iterator = null;
        list = CollectionUtils.collect(iterator, transformer, new ArrayList<Number>());

        iterator = iterableA.iterator();
        list = CollectionUtils.collect(iterator, transformer, list);
        // removed other assertion
        // removed other assertion

        iterator = collectionA.iterator();
        collection = CollectionUtils.<Integer, Number>collect(iterator, transformer);
        // removed other assertion
        // removed other assertion
        collection = CollectionUtils.collect((Iterator<Integer>) null, (Transformer<Integer, Number>) null);
        // removed other assertion

        final int size = collectionA.size();
        collectionB = CollectionUtils.collect((Collection<Integer>) null, transformer, collectionB);
        assertTrue(collectionA.size() == size && collectionA.contains(1));
    }

@Test
    public void collect_11_oe() {
        final Transformer<Number, Long> transformer = TransformerUtils.constantTransformer(2L);
        Collection<Number> collection = CollectionUtils.<Integer, Number>collect(iterableA, transformer);
        // removed other assertion
        // removed other assertion

        ArrayList<Number> list;
        list = CollectionUtils.collect(collectionA, transformer, new ArrayList<Number>());
        // removed other assertion
        // removed other assertion

        Iterator<Integer> iterator = null;
        list = CollectionUtils.collect(iterator, transformer, new ArrayList<Number>());

        iterator = iterableA.iterator();
        list = CollectionUtils.collect(iterator, transformer, list);
        // removed other assertion
        // removed other assertion

        iterator = collectionA.iterator();
        collection = CollectionUtils.<Integer, Number>collect(iterator, transformer);
        // removed other assertion
        // removed other assertion
        collection = CollectionUtils.collect((Iterator<Integer>) null, (Transformer<Integer, Number>) null);
        // removed other assertion

        final int size = collectionA.size();
        collectionB = CollectionUtils.collect((Collection<Integer>) null, transformer, collectionB);
        // removed other assertion
        CollectionUtils.collect(collectionB, null, collectionA);
        assertTrue(collectionA.size() == size && collectionA.contains(1));
    }

@Test
    public void transform1_1_oe() {
        List<Number> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(list, TRANSFORM_TO_INTEGER);
        assertEquals(3, list.size());
    }

@Test
    public void transform1_2_oe() {
        List<Number> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(list, TRANSFORM_TO_INTEGER);
        // removed other assertion
        assertEquals(1, list.get(0));
    }

@Test
    public void transform1_3_oe() {
        List<Number> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(list, TRANSFORM_TO_INTEGER);
        // removed other assertion
        // removed other assertion
        assertEquals(2, list.get(1));
    }

@Test
    public void transform1_4_oe() {
        List<Number> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(list, TRANSFORM_TO_INTEGER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, list.get(2));
    }

@Test
    public void transform1_5_oe() {
        List<Number> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(list, TRANSFORM_TO_INTEGER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(null, TRANSFORM_TO_INTEGER);
        assertEquals(3, list.size());
    }

@Test
    public void transform1_6_oe() {
        List<Number> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(list, TRANSFORM_TO_INTEGER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(null, TRANSFORM_TO_INTEGER);
        // removed other assertion
        CollectionUtils.transform(list, null);
        assertEquals(3, list.size());
    }

@Test
    public void transform1_7_oe() {
        List<Number> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(list, TRANSFORM_TO_INTEGER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(null, TRANSFORM_TO_INTEGER);
        // removed other assertion
        CollectionUtils.transform(list, null);
        // removed other assertion
        CollectionUtils.transform(null, null);
        assertEquals(3, list.size());
    }

@Test
    public void transform2_1_oe() {
        final Set<Number> set = new HashSet<>();
        set.add(1L);
        set.add(2L);
        set.add(3L);
        CollectionUtils.transform(set, new Transformer<Object, Integer>() {
            @Override
            public Integer transform(final Object input) {
                return 4;
            }
        });
        assertEquals(1, set.size());
    }

@Test
    public void transform2_2_oe() {
        final Set<Number> set = new HashSet<>();
        set.add(1L);
        set.add(2L);
        set.add(3L);
        CollectionUtils.transform(set, new Transformer<Object, Integer>() {
            @Override
            public Integer transform(final Object input) {
                return 4;
            }
        });
        // removed other assertion
        assertEquals(4, set.iterator().next());
    }

@Test
    public void addIgnoreNull_1_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        assertFalse(CollectionUtils.addIgnoreNull(set, null));
    }

@Test
    public void addIgnoreNull_2_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        // removed other assertion
        assertEquals(3, set.size());
    }

@Test
    public void addIgnoreNull_3_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        // removed other assertion
        // removed other assertion
        assertFalse(CollectionUtils.addIgnoreNull(set, "1"));
    }

@Test
    public void addIgnoreNull_4_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, set.size());
    }

@Test
    public void addIgnoreNull_5_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, CollectionUtils.addIgnoreNull(set, "4"));
    }

@Test
    public void addIgnoreNull_6_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, set.size());
    }

@Test
    public void addIgnoreNull_7_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, set.contains("4"));
    }

@Test
    public void predicatedCollection_1_oe() {
        final Predicate<Object> predicate = PredicateUtils.instanceofPredicate(Integer.class);
        final Collection<Number> collection = CollectionUtils.predicatedCollection(new ArrayList<Number>(), predicate);
        assertTrue("returned object should be a PredicatedCollection", collection instanceof PredicatedCollection);
    }

@Test
    public void isFull_2_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        try {
            CollectionUtils.isFull(null);
            // removed other assertion
        } catch (final NullPointerException ex) {
        }
        assertFalse(CollectionUtils.isFull(set));
    }

@Test
    public void isFull_3_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        try {
            CollectionUtils.isFull(null);
            // removed other assertion
        } catch (final NullPointerException ex) {
        }
        // removed other assertion

        final CircularFifoQueue<String> buf = new CircularFifoQueue<>(set);
        assertEquals(false, CollectionUtils.isFull(buf));
    }

@Test
    public void isFull_4_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        try {
            CollectionUtils.isFull(null);
            // removed other assertion
        } catch (final NullPointerException ex) {
        }
        // removed other assertion

        final CircularFifoQueue<String> buf = new CircularFifoQueue<>(set);
        // removed other assertion
        buf.remove("2");
        assertFalse(CollectionUtils.isFull(buf));
    }

@Test
    public void isFull_5_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        try {
            CollectionUtils.isFull(null);
            // removed other assertion
        } catch (final NullPointerException ex) {
        }
        // removed other assertion

        final CircularFifoQueue<String> buf = new CircularFifoQueue<>(set);
        // removed other assertion
        buf.remove("2");
        // removed other assertion
        buf.add("2");
        assertEquals(false, CollectionUtils.isFull(buf));
    }

@Test
    public void isEmpty_1_oe() {
        assertFalse(CollectionUtils.isNotEmpty(null));
    }

@Test
    public void isEmpty_2_oe() {
        // removed other assertion
        assertTrue(CollectionUtils.isNotEmpty(collectionA));
    }

@Test
    public void maxSize_2_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        try {
            CollectionUtils.maxSize(null);
            // removed other assertion
        } catch (final NullPointerException ex) {
        }
        assertEquals(-1, CollectionUtils.maxSize(set));
    }

@Test
    public void maxSize_3_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        try {
            CollectionUtils.maxSize(null);
            // removed other assertion
        } catch (final NullPointerException ex) {
        }
        // removed other assertion

        final Queue<String> buf = new CircularFifoQueue<>(set);
        assertEquals(3, CollectionUtils.maxSize(buf));
    }

@Test
    public void maxSize_4_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        try {
            CollectionUtils.maxSize(null);
            // removed other assertion
        } catch (final NullPointerException ex) {
        }
        // removed other assertion

        final Queue<String> buf = new CircularFifoQueue<>(set);
        // removed other assertion
        buf.remove("2");
        assertEquals(3, CollectionUtils.maxSize(buf));
    }

@Test
    public void maxSize_5_oe() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        try {
            CollectionUtils.maxSize(null);
            // removed other assertion
        } catch (final NullPointerException ex) {
        }
        // removed other assertion

        final Queue<String> buf = new CircularFifoQueue<>(set);
        // removed other assertion
        buf.remove("2");
        // removed other assertion
        buf.add("2");
        assertEquals(3, CollectionUtils.maxSize(buf));
    }

@Test
    public void intersectionUsesMethodEquals_1_oe() {
        // Let elta and eltb be objects...
        final Integer elta = new Integer(17); // Cannot use valueOf here
        final Integer eltb = new Integer(17);

        // ...which are equal...
        assertEquals(elta, eltb);
    }

@Test
    public void intersectionUsesMethodEquals_2_oe() {
        // Let elta and eltb be objects...
        final Integer elta = new Integer(17); // Cannot use valueOf here
        final Integer eltb = new Integer(17);

        // ...which are equal...
        // removed other assertion
        assertEquals(eltb, elta);
    }

@Test
    public void intersectionUsesMethodEquals_3_oe() {
        // Let elta and eltb be objects...
        final Integer elta = new Integer(17); // Cannot use valueOf here
        final Integer eltb = new Integer(17);

        // ...which are equal...
        // removed other assertion
        // removed other assertion

        // ...but not the same (==).
        assertTrue(elta != eltb);
    }

@Test
    public void intersectionUsesMethodEquals_4_oe() {
        // Let elta and eltb be objects...
        final Integer elta = new Integer(17); // Cannot use valueOf here
        final Integer eltb = new Integer(17);

        // ...which are equal...
        // removed other assertion
        // removed other assertion

        // ...but not the same (==).
        // removed other assertion

        // Let cola and colb be collections...
        final Collection<Number> cola = new ArrayList<>();
        final Collection<Integer> colb = new ArrayList<>();

        // ...which contain elta and eltb,
        // respectively.
        cola.add(elta);
        colb.add(eltb);

        // Then the intersection of the two
        // should contain one element.
        final Collection<Number> intersection = CollectionUtils.intersection(cola, colb);
        assertEquals(1, intersection.size());
    }

@Test
    public void intersectionUsesMethodEquals_5_oe() {
        // Let elta and eltb be objects...
        final Integer elta = new Integer(17); // Cannot use valueOf here
        final Integer eltb = new Integer(17);

        // ...which are equal...
        // removed other assertion
        // removed other assertion

        // ...but not the same (==).
        // removed other assertion

        // Let cola and colb be collections...
        final Collection<Number> cola = new ArrayList<>();
        final Collection<Integer> colb = new ArrayList<>();

        // ...which contain elta and eltb,
        // respectively.
        cola.add(elta);
        colb.add(eltb);

        // Then the intersection of the two
        // should contain one element.
        final Collection<Number> intersection = CollectionUtils.intersection(cola, colb);
        // removed other assertion

        // In practice, this element will be the same (==) as elta
        // or eltb, although this isn't strictly part of the
        // contract.
        final Object eltc = intersection.iterator().next();
        assertTrue(eltc == elta && eltc != eltb || eltc != elta && eltc == eltb);
    }

@Test
    public void intersectionUsesMethodEquals_6_oe() {
        // Let elta and eltb be objects...
        final Integer elta = new Integer(17); // Cannot use valueOf here
        final Integer eltb = new Integer(17);

        // ...which are equal...
        // removed other assertion
        // removed other assertion

        // ...but not the same (==).
        // removed other assertion

        // Let cola and colb be collections...
        final Collection<Number> cola = new ArrayList<>();
        final Collection<Integer> colb = new ArrayList<>();

        // ...which contain elta and eltb,
        // respectively.
        cola.add(elta);
        colb.add(eltb);

        // Then the intersection of the two
        // should contain one element.
        final Collection<Number> intersection = CollectionUtils.intersection(cola, colb);
        // removed other assertion

        // In practice, this element will be the same (==) as elta
        // or eltb, although this isn't strictly part of the
        // contract.
        final Object eltc = intersection.iterator().next();
        // removed other assertion

        // In any event, this element remains equal,
        // to both elta and eltb.
        assertEquals(elta, eltc);
    }

@Test
    public void intersectionUsesMethodEquals_7_oe() {
        // Let elta and eltb be objects...
        final Integer elta = new Integer(17); // Cannot use valueOf here
        final Integer eltb = new Integer(17);

        // ...which are equal...
        // removed other assertion
        // removed other assertion

        // ...but not the same (==).
        // removed other assertion

        // Let cola and colb be collections...
        final Collection<Number> cola = new ArrayList<>();
        final Collection<Integer> colb = new ArrayList<>();

        // ...which contain elta and eltb,
        // respectively.
        cola.add(elta);
        colb.add(eltb);

        // Then the intersection of the two
        // should contain one element.
        final Collection<Number> intersection = CollectionUtils.intersection(cola, colb);
        // removed other assertion

        // In practice, this element will be the same (==) as elta
        // or eltb, although this isn't strictly part of the
        // contract.
        final Object eltc = intersection.iterator().next();
        // removed other assertion

        // In any event, this element remains equal,
        // to both elta and eltb.
        // removed other assertion
        assertEquals(eltc, elta);
    }

@Test
    public void intersectionUsesMethodEquals_8_oe() {
        // Let elta and eltb be objects...
        final Integer elta = new Integer(17); // Cannot use valueOf here
        final Integer eltb = new Integer(17);

        // ...which are equal...
        // removed other assertion
        // removed other assertion

        // ...but not the same (==).
        // removed other assertion

        // Let cola and colb be collections...
        final Collection<Number> cola = new ArrayList<>();
        final Collection<Integer> colb = new ArrayList<>();

        // ...which contain elta and eltb,
        // respectively.
        cola.add(elta);
        colb.add(eltb);

        // Then the intersection of the two
        // should contain one element.
        final Collection<Number> intersection = CollectionUtils.intersection(cola, colb);
        // removed other assertion

        // In practice, this element will be the same (==) as elta
        // or eltb, although this isn't strictly part of the
        // contract.
        final Object eltc = intersection.iterator().next();
        // removed other assertion

        // In any event, this element remains equal,
        // to both elta and eltb.
        // removed other assertion
        // removed other assertion
        assertEquals(eltb, eltc);
    }

@Test
    public void intersectionUsesMethodEquals_9_oe() {
        // Let elta and eltb be objects...
        final Integer elta = new Integer(17); // Cannot use valueOf here
        final Integer eltb = new Integer(17);

        // ...which are equal...
        // removed other assertion
        // removed other assertion

        // ...but not the same (==).
        // removed other assertion

        // Let cola and colb be collections...
        final Collection<Number> cola = new ArrayList<>();
        final Collection<Integer> colb = new ArrayList<>();

        // ...which contain elta and eltb,
        // respectively.
        cola.add(elta);
        colb.add(eltb);

        // Then the intersection of the two
        // should contain one element.
        final Collection<Number> intersection = CollectionUtils.intersection(cola, colb);
        // removed other assertion

        // In practice, this element will be the same (==) as elta
        // or eltb, although this isn't strictly part of the
        // contract.
        final Object eltc = intersection.iterator().next();
        // removed other assertion

        // In any event, this element remains equal,
        // to both elta and eltb.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(eltc, eltb);
    }

@Test
    public void testRetainAll_1_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        assertEquals(2, result.size());
    }

@Test
    public void testRetainAll_2_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        assertEquals(true, result.contains("A"));
    }

@Test
    public void testRetainAll_3_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        // removed other assertion
        assertFalse(result.contains("B"));
    }

@Test
    public void testRetainAll_4_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, result.contains("C"));
    }

@Test
    public void testRetainAll_5_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, base.size());
    }

@Test
    public void testRetainAll_6_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, base.contains("A"));
    }

@Test
    public void testRetainAll_7_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, base.contains("B"));
    }

@Test
    public void testRetainAll_8_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, base.contains("C"));
    }

@Test
    public void testRetainAll_9_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, sub.size());
    }

@Test
    public void testRetainAll_10_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.contains("A"));
    }

@Test
    public void testRetainAll_11_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.contains("C"));
    }

@Test
    public void testRetainAll_12_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.contains("X"));
    }

@Test
    public void testRemoveAll_1_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        assertEquals(1, result.size());
    }

@Test
    public void testRemoveAll_2_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        assertFalse(result.contains("A"));
    }

@Test
    public void testRemoveAll_3_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        // removed other assertion
        assertEquals(true, result.contains("B"));
    }

@Test
    public void testRemoveAll_4_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(result.contains("C"));
    }

@Test
    public void testRemoveAll_5_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, base.size());
    }

@Test
    public void testRemoveAll_6_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, base.contains("A"));
    }

@Test
    public void testRemoveAll_7_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, base.contains("B"));
    }

@Test
    public void testRemoveAll_8_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, base.contains("C"));
    }

@Test
    public void testRemoveAll_9_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, sub.size());
    }

@Test
    public void testRemoveAll_10_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.contains("A"));
    }

@Test
    public void testRemoveAll_11_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.contains("C"));
    }

@Test
    public void testRemoveAll_12_oe() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.contains("X"));
    }

@Test
    public void testTransformedCollection_1_oe() {
        final Transformer<Object, Object> transformer = TransformerUtils.nopTransformer();
        final Collection<Object> collection = CollectionUtils.transformingCollection(new ArrayList<>(), transformer);
        assertTrue("returned object should be a TransformedCollection", collection instanceof TransformedCollection);
    }

@Test
    public void testTransformedCollection_2_1_oe() {
        final List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        final Collection<Object> result = CollectionUtils.transformingCollection(list, TRANSFORM_TO_INTEGER);
        assertEquals(true, result.contains("1")); // untransformed;
    }

@Test
    public void testTransformedCollection_2_2_oe() {
        final List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        final Collection<Object> result = CollectionUtils.transformingCollection(list, TRANSFORM_TO_INTEGER);
        // removed other assertion
        assertEquals(true, result.contains("2")); // untransformed;
    }

@Test
    public void testTransformedCollection_2_3_oe() {
        final List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        final Collection<Object> result = CollectionUtils.transformingCollection(list, TRANSFORM_TO_INTEGER);
        // removed other assertion
        // removed other assertion
        assertEquals(true, result.contains("3")); // untransformed;
    }

@Test
    @Deprecated
    public void testSynchronizedCollection_1_oe() {
        final Collection<Object> col = CollectionUtils.synchronizedCollection(new ArrayList<>());
        assertTrue("Returned object should be a SynchronizedCollection.", col instanceof SynchronizedCollection);
    }

@Test
    @Deprecated
    public void testUnmodifiableCollection_1_oe() {
        final Collection<Object> col = CollectionUtils.unmodifiableCollection(new ArrayList<>());
        assertTrue("Returned object should be a UnmodifiableCollection.", col instanceof UnmodifiableCollection);
    }

@Test
    public void emptyCollection_1_oe() throws Exception {
        final Collection<Number> coll = CollectionUtils.emptyCollection();
        assertEquals(CollectionUtils.EMPTY_COLLECTION, coll);
    }

@Test
    public void emptyIfNull_1_oe() {
        assertTrue(CollectionUtils.emptyIfNull(null).isEmpty());
    }

@Test
    public void emptyIfNull_2_oe() {
        // removed other assertion
        final Collection<Object> collection = new ArrayList<>();
        assertSame(collection, CollectionUtils.emptyIfNull(collection));
    }

@Test
    public void addAllForIterable_1_oe() {
        final Collection<Integer> inputCollection = createMock(Collection.class);
        final Iterable<Integer> inputIterable = inputCollection;
        final Iterable<Long> iterable = createMock(Iterable.class);
        final Iterator<Long> iterator = createMock(Iterator.class);
        final Collection<Number> c = createMock(Collection.class);

        expect(iterable.iterator()).andReturn(iterator);
        next(iterator, 1L);
        next(iterator, 2L);
        next(iterator, 3L);
        expect(iterator.hasNext()).andReturn(false);
        expect(c.add(1L)).andReturn(true);
        expect(c.add(2L)).andReturn(true);
        expect(c.add(3L)).andReturn(true);
        // Check that the collection is added using
        // Collection.addAll(Collection)
        expect(c.addAll(inputCollection)).andReturn(true);

        // Ensure the method returns false if nothing is added
        expect(iterable.iterator()).andReturn(iterator);
        next(iterator, 1L);
        expect(iterator.hasNext()).andReturn(false);
        expect(c.add(1L)).andReturn(false);
        expect(c.addAll(inputCollection)).andReturn(false);

        replay();
        assertTrue(CollectionUtils.addAll(c, iterable));
    }

@Test
    public void addAllForIterable_2_oe() {
        final Collection<Integer> inputCollection = createMock(Collection.class);
        final Iterable<Integer> inputIterable = inputCollection;
        final Iterable<Long> iterable = createMock(Iterable.class);
        final Iterator<Long> iterator = createMock(Iterator.class);
        final Collection<Number> c = createMock(Collection.class);

        expect(iterable.iterator()).andReturn(iterator);
        next(iterator, 1L);
        next(iterator, 2L);
        next(iterator, 3L);
        expect(iterator.hasNext()).andReturn(false);
        expect(c.add(1L)).andReturn(true);
        expect(c.add(2L)).andReturn(true);
        expect(c.add(3L)).andReturn(true);
        // Check that the collection is added using
        // Collection.addAll(Collection)
        expect(c.addAll(inputCollection)).andReturn(true);

        // Ensure the method returns false if nothing is added
        expect(iterable.iterator()).andReturn(iterator);
        next(iterator, 1L);
        expect(iterator.hasNext()).andReturn(false);
        expect(c.add(1L)).andReturn(false);
        expect(c.addAll(inputCollection)).andReturn(false);

        replay();
        // removed other assertion
        assertTrue(CollectionUtils.addAll(c, inputIterable));
    }

@Test
    public void addAllForIterable_3_oe() {
        final Collection<Integer> inputCollection = createMock(Collection.class);
        final Iterable<Integer> inputIterable = inputCollection;
        final Iterable<Long> iterable = createMock(Iterable.class);
        final Iterator<Long> iterator = createMock(Iterator.class);
        final Collection<Number> c = createMock(Collection.class);

        expect(iterable.iterator()).andReturn(iterator);
        next(iterator, 1L);
        next(iterator, 2L);
        next(iterator, 3L);
        expect(iterator.hasNext()).andReturn(false);
        expect(c.add(1L)).andReturn(true);
        expect(c.add(2L)).andReturn(true);
        expect(c.add(3L)).andReturn(true);
        // Check that the collection is added using
        // Collection.addAll(Collection)
        expect(c.addAll(inputCollection)).andReturn(true);

        // Ensure the method returns false if nothing is added
        expect(iterable.iterator()).andReturn(iterator);
        next(iterator, 1L);
        expect(iterator.hasNext()).andReturn(false);
        expect(c.add(1L)).andReturn(false);
        expect(c.addAll(inputCollection)).andReturn(false);

        replay();
        // removed other assertion
        // removed other assertion

        assertFalse(CollectionUtils.addAll(c, iterable));
    }

@Test
    public void addAllForIterable_4_oe() {
        final Collection<Integer> inputCollection = createMock(Collection.class);
        final Iterable<Integer> inputIterable = inputCollection;
        final Iterable<Long> iterable = createMock(Iterable.class);
        final Iterator<Long> iterator = createMock(Iterator.class);
        final Collection<Number> c = createMock(Collection.class);

        expect(iterable.iterator()).andReturn(iterator);
        next(iterator, 1L);
        next(iterator, 2L);
        next(iterator, 3L);
        expect(iterator.hasNext()).andReturn(false);
        expect(c.add(1L)).andReturn(true);
        expect(c.add(2L)).andReturn(true);
        expect(c.add(3L)).andReturn(true);
        // Check that the collection is added using
        // Collection.addAll(Collection)
        expect(c.addAll(inputCollection)).andReturn(true);

        // Ensure the method returns false if nothing is added
        expect(iterable.iterator()).andReturn(iterator);
        next(iterator, 1L);
        expect(iterator.hasNext()).andReturn(false);
        expect(c.add(1L)).andReturn(false);
        expect(c.addAll(inputCollection)).andReturn(false);

        replay();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(CollectionUtils.addAll(c, inputIterable));
    }

@Test
    public void addAllForEnumeration_1_oe() {
        final Hashtable<Integer, Integer> h = new Hashtable<>();
        h.put(5, 5);
        final Enumeration<? extends Integer> enumeration = h.keys();
        CollectionUtils.addAll(collectionA, enumeration);
        assertTrue(collectionA.contains(5));
    }

@Test
    public void addAllForElements_1_oe() {
        CollectionUtils.addAll(collectionA, new Integer[]{5});
        assertTrue(collectionA.contains(5));
    }

@Test
    public void get_1_oe() {
        assertEquals(2, CollectionUtils.get((Object)collectionA, 2));
    }

@Test
    public void get_2_oe() {
        // removed other assertion
        assertEquals(2, CollectionUtils.get((Object)collectionA.iterator(), 2));
    }

@Test
    public void get_3_oe() {
        // removed other assertion
        // removed other assertion
        final Map<Integer, Integer> map = CollectionUtils.getCardinalityMap(collectionA);
        assertEquals(map.entrySet().iterator().next(), CollectionUtils.get((Object)map, 0));
    }

@Test
    public void getIterator_1_oe() {
        final Iterator<Integer> it = collectionA.iterator();
        assertEquals(Integer.valueOf(2), CollectionUtils.get((Object) it, 2));
    }

@Test
    public void getIterator_2_oe() {
        final Iterator<Integer> it = collectionA.iterator();
        // removed other assertion
        assertTrue(it.hasNext());
    }

@Test
    public void getIterator_3_oe() {
        final Iterator<Integer> it = collectionA.iterator();
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(4), CollectionUtils.get((Object) it, 6));
    }

@Test
    public void getIterator_4_oe() {
        final Iterator<Integer> it = collectionA.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(it.hasNext());
    }

@Test
    public void getEnumeration_1_oe() {
        final Vector<Integer> vectorA = new Vector<>(collectionA);
        final Enumeration<Integer> e = vectorA.elements();
        assertEquals(Integer.valueOf(2), CollectionUtils.get(e, 2));
    }

@Test
    public void getEnumeration_2_oe() {
        final Vector<Integer> vectorA = new Vector<>(collectionA);
        final Enumeration<Integer> e = vectorA.elements();
        // removed other assertion
        assertTrue(e.hasMoreElements());
    }

@Test
    public void getEnumeration_3_oe() {
        final Vector<Integer> vectorA = new Vector<>(collectionA);
        final Enumeration<Integer> e = vectorA.elements();
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(4), CollectionUtils.get(e, 6));
    }

@Test
    public void getEnumeration_4_oe() {
        final Vector<Integer> vectorA = new Vector<>(collectionA);
        final Enumeration<Integer> e = vectorA.elements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(e.hasMoreElements());
    }

@Test
    public void reverse_1_oe() {
        CollectionUtils.reverseArray(new Object[] {});
        final Integer[] a = collectionA.toArray(new Integer[collectionA.size()]);
        CollectionUtils.reverseArray(a);
        // assume our implementation is correct if it returns the same order as the Java function
        Collections.reverse(collectionA);
        assertEquals(collectionA, Arrays.asList(a));
    }

@Test
    public void extractSingleton_3_oe() {
        ArrayList<String> coll = null;
        try {
            CollectionUtils.extractSingleton(coll);
            // removed other assertion
        } catch (final NullPointerException e) {
        }
        coll = new ArrayList<>();
        try {
            CollectionUtils.extractSingleton(coll);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
        }
        coll.add("foo");
        assertEquals("foo", CollectionUtils.extractSingleton(coll));
    }

@Test
    public void testCollate_1_oe() {
        List<Integer> result = CollectionUtils.collate(emptyCollection, emptyCollection);
        assertEquals("Merge empty with empty", 0, result.size());
    }

@Test
    public void testCollate_2_oe() {
        List<Integer> result = CollectionUtils.collate(emptyCollection, emptyCollection);
        // removed other assertion

        result = CollectionUtils.collate(collectionA, emptyCollection);
        assertEquals("Merge empty with non-empty", collectionA, result);
    }

@Test
    public void testCollate_3_oe() {
        List<Integer> result = CollectionUtils.collate(emptyCollection, emptyCollection);
        // removed other assertion

        result = CollectionUtils.collate(collectionA, emptyCollection);
        // removed other assertion

        List<Integer> result1 = CollectionUtils.collate(collectionD, collectionE);
        List<Integer> result2 = CollectionUtils.collate(collectionE, collectionD);
        assertEquals("Merge two lists 1", result1, result2);
    }

@Test
    public void testCollate_4_oe() {
        List<Integer> result = CollectionUtils.collate(emptyCollection, emptyCollection);
        // removed other assertion

        result = CollectionUtils.collate(collectionA, emptyCollection);
        // removed other assertion

        List<Integer> result1 = CollectionUtils.collate(collectionD, collectionE);
        List<Integer> result2 = CollectionUtils.collate(collectionE, collectionD);
        // removed other assertion

        final List<Integer> combinedList = new ArrayList<>();
        combinedList.addAll(collectionD);
        combinedList.addAll(collectionE);
        Collections.sort(combinedList);

        assertEquals("Merge two lists 2", combinedList, result2);
    }

@Test
    public void testCollate_5_oe() {
        List<Integer> result = CollectionUtils.collate(emptyCollection, emptyCollection);
        // removed other assertion

        result = CollectionUtils.collate(collectionA, emptyCollection);
        // removed other assertion

        List<Integer> result1 = CollectionUtils.collate(collectionD, collectionE);
        List<Integer> result2 = CollectionUtils.collate(collectionE, collectionD);
        // removed other assertion

        final List<Integer> combinedList = new ArrayList<>();
        combinedList.addAll(collectionD);
        combinedList.addAll(collectionE);
        Collections.sort(combinedList);

        // removed other assertion

        final Comparator<Integer> reverseComparator =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        result = CollectionUtils.collate(emptyCollection, emptyCollection, reverseComparator);
        assertEquals("Comparator Merge empty with empty", 0, result.size());
    }

@Test
    public void testCollate_6_oe() {
        List<Integer> result = CollectionUtils.collate(emptyCollection, emptyCollection);
        // removed other assertion

        result = CollectionUtils.collate(collectionA, emptyCollection);
        // removed other assertion

        List<Integer> result1 = CollectionUtils.collate(collectionD, collectionE);
        List<Integer> result2 = CollectionUtils.collate(collectionE, collectionD);
        // removed other assertion

        final List<Integer> combinedList = new ArrayList<>();
        combinedList.addAll(collectionD);
        combinedList.addAll(collectionE);
        Collections.sort(combinedList);

        // removed other assertion

        final Comparator<Integer> reverseComparator =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        result = CollectionUtils.collate(emptyCollection, emptyCollection, reverseComparator);
        // removed other assertion

        Collections.reverse((List<Integer>) collectionD);
        Collections.reverse((List<Integer>) collectionE);
        Collections.reverse(combinedList);

        result1 = CollectionUtils.collate(collectionD, collectionE, reverseComparator);
        result2 = CollectionUtils.collate(collectionE, collectionD, reverseComparator);
        assertEquals("Comparator Merge two lists 1", result1, result2);
    }

@Test
    public void testCollate_7_oe() {
        List<Integer> result = CollectionUtils.collate(emptyCollection, emptyCollection);
        // removed other assertion

        result = CollectionUtils.collate(collectionA, emptyCollection);
        // removed other assertion

        List<Integer> result1 = CollectionUtils.collate(collectionD, collectionE);
        List<Integer> result2 = CollectionUtils.collate(collectionE, collectionD);
        // removed other assertion

        final List<Integer> combinedList = new ArrayList<>();
        combinedList.addAll(collectionD);
        combinedList.addAll(collectionE);
        Collections.sort(combinedList);

        // removed other assertion

        final Comparator<Integer> reverseComparator =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        result = CollectionUtils.collate(emptyCollection, emptyCollection, reverseComparator);
        // removed other assertion

        Collections.reverse((List<Integer>) collectionD);
        Collections.reverse((List<Integer>) collectionE);
        Collections.reverse(combinedList);

        result1 = CollectionUtils.collate(collectionD, collectionE, reverseComparator);
        result2 = CollectionUtils.collate(collectionE, collectionD, reverseComparator);
        // removed other assertion
        assertEquals("Comparator Merge two lists 2", combinedList, result2);
    }

@Test
    public void testCollateIgnoreDuplicates_1_oe() {
        final List<Integer> result1 = CollectionUtils.collate(collectionD, collectionE, false);
        final List<Integer> result2 = CollectionUtils.collate(collectionE, collectionD, false);
        assertEquals("Merge two lists 1 - ignore duplicates", result1, result2);
    }

@Test
    public void testCollateIgnoreDuplicates_2_oe() {
        final List<Integer> result1 = CollectionUtils.collate(collectionD, collectionE, false);
        final List<Integer> result2 = CollectionUtils.collate(collectionE, collectionD, false);
        // removed other assertion

        final Set<Integer> combinedSet = new HashSet<>();
        combinedSet.addAll(collectionD);
        combinedSet.addAll(collectionE);
        final List<Integer> combinedList = new ArrayList<>(combinedSet);
        Collections.sort(combinedList);

        assertEquals("Merge two lists 2 - ignore duplicates", combinedList, result2);
    }

@Test
    public void testPermutations_1_oe() {
        final List<Integer> sample = collectionA.subList(0, 5);
        final Collection<List<Integer>> permutations = CollectionUtils.permutations(sample);

        // result size = n!
        final int collSize = sample.size();
        int factorial = 1;
        for (int i = 1; i <= collSize; i++) {
            factorial *= i;
        }
        assertEquals(factorial, permutations.size());
    }

@Test
    @Deprecated
    public void testMatchesAll_1_oe() {
        assertFalse(CollectionUtils.matchesAll(null, null));
    }

@Test
    @Deprecated
    public void testMatchesAll_2_oe() {
        // removed other assertion
        assertFalse(CollectionUtils.matchesAll(collectionA, null));
    }

@Test
    @Deprecated
    public void testMatchesAll_3_oe() {
        // removed other assertion
        // removed other assertion

        final Predicate<Integer> lessThanFive = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object < 5;
            }
        };
        assertTrue(CollectionUtils.matchesAll(collectionA, lessThanFive));
    }

@Test
    @Deprecated
    public void testMatchesAll_4_oe() {
        // removed other assertion
        // removed other assertion

        final Predicate<Integer> lessThanFive = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object < 5;
            }
        };
        // removed other assertion

        final Predicate<Integer> lessThanFour = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object < 4;
            }
        };
        assertFalse(CollectionUtils.matchesAll(collectionA, lessThanFour));
    }

@Test
    @Deprecated
    public void testMatchesAll_5_oe() {
        // removed other assertion
        // removed other assertion

        final Predicate<Integer> lessThanFive = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object < 5;
            }
        };
        // removed other assertion

        final Predicate<Integer> lessThanFour = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object < 4;
            }
        };
        // removed other assertion

        assertTrue(CollectionUtils.matchesAll(null, lessThanFour));
    }

@Test
    @Deprecated
    public void testMatchesAll_6_oe() {
        // removed other assertion
        // removed other assertion

        final Predicate<Integer> lessThanFive = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object < 5;
            }
        };
        // removed other assertion

        final Predicate<Integer> lessThanFour = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object < 4;
            }
        };
        // removed other assertion

        // removed other assertion
        assertTrue(CollectionUtils.matchesAll(emptyCollection, lessThanFour));
    }

@Test
    public void testRemoveAllWithEquator_1_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        assertEquals(2, result.size());
    }

@Test
    public void testRemoveAllWithEquator_2_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        assertTrue(result.contains("AC"));
    }

@Test
    public void testRemoveAllWithEquator_3_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        // removed other assertion
        assertTrue(result.contains("BB"));
    }

@Test
    public void testRemoveAllWithEquator_4_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(result.contains("CA"));
    }

@Test
    public void testRemoveAllWithEquator_5_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, base.size());
    }

@Test
    public void testRemoveAllWithEquator_6_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, base.contains("AC"));
    }

@Test
    public void testRemoveAllWithEquator_7_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, base.contains("BB"));
    }

@Test
    public void testRemoveAllWithEquator_8_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, base.contains("CA"));
    }

@Test
    public void testRemoveAllWithEquator_9_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, remove.size());
    }

@Test
    public void testRemoveAllWithEquator_10_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, remove.contains("AA"));
    }

@Test
    public void testRemoveAllWithEquator_11_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, remove.contains("CX"));
    }

@Test
    public void testRemoveAllWithEquator_12_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, remove.contains("XZ"));
    }

@Test
    public void testRetainAllWithEquator_1_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        assertEquals(1, result.size());
    }

@Test
    public void testRetainAllWithEquator_2_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        assertTrue(result.contains("CA"));
    }

@Test
    public void testRetainAllWithEquator_3_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        // removed other assertion
        assertFalse(result.contains("BB"));
    }

@Test
    public void testRetainAllWithEquator_4_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(result.contains("AC"));
    }

@Test
    public void testRetainAllWithEquator_5_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(3, base.size());
    }

@Test
    public void testRetainAllWithEquator_6_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(base.contains("AC"));
    }

@Test
    public void testRetainAllWithEquator_7_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(base.contains("BB"));
    }

@Test
    public void testRetainAllWithEquator_8_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(base.contains("CA"));
    }

@Test
    public void testRetainAllWithEquator_9_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(3, retain.size());
    }

@Test
    public void testRetainAllWithEquator_10_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(retain.contains("AA"));
    }

@Test
    public void testRetainAllWithEquator_11_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(retain.contains("CX"));
    }

@Test
    public void testRetainAllWithEquator_12_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(retain.contains("XZ"));
    }

}
