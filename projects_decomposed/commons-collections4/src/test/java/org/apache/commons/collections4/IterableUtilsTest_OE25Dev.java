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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.bag.HashBag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for IterableUtils.
 *
 * @since 4.1
 */
public class IterableUtilsTest_OE25Dev {

    /**
     * Iterable of {@link Integer}s
     */
    private Iterable<Integer> iterableA = null;

    /**
     * Iterable of {@link Long}s
     */
    private Iterable<Long> iterableB = null;

    /**
     * An empty Iterable.
     */
    private Iterable<Integer> emptyIterable = null;

    @Before
    public void setUp() {
        final Collection<Integer> collectionA = new ArrayList<>();
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
        iterableA = collectionA;

        final Collection<Long> collectionB = new LinkedList<>();
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
        iterableB = collectionB;

        emptyIterable = Collections.emptyList();
    }

    private static Predicate<Number> EQUALS_TWO = new Predicate<Number>() {
        @Override
        public boolean evaluate(final Number input) {
            return input.intValue() == 2;
        }
    };

    private static Predicate<Number> EVEN = new Predicate<Number>() {
        @Override
        public boolean evaluate(final Number input) {
            return input.intValue() % 2 == 0;
        }
    };

    // -----------------------------------------------------------------------

    @Test(expected = FunctorException.class)
    public void forEachFailure() {
        final Closure<String> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<String> col = new ArrayList<>();
        col.add("x");
        IterableUtils.forEach(col, testClosure);
    }

    public void getFromIterable() throws Exception {
        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        assertEquals("element", IterableUtils.get(bag, 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFromIterableIndexOutOfBoundsException() throws Exception {
        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        // Collection, non-existent entry
        IterableUtils.get(bag, 1);
    }

    public void firstFromIterable() throws Exception {
        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        assertEquals("element", IterableUtils.first(bag));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void firstFromIterableIndexOutOfBoundsException() throws Exception {
        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        // Collection, non-existent entry
        IterableUtils.first(bag);
    }

    @Test
    public void forEach_1_oe() {
        final List<Integer> listA = new ArrayList<>();
        listA.add(1);

        final List<Integer> listB = new ArrayList<>();
        listB.add(2);

        final Closure<List<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<Integer>> col = new ArrayList<>();
        col.add(listA);
        col.add(listB);
        IterableUtils.forEach(col, testClosure);
        assertTrue(listA.isEmpty() && listB.isEmpty());
    }

    @Test
    public void forEachButLast_1_oe() {
        final List<Integer> listA = new ArrayList<>();
        listA.add(1);

        final List<Integer> listB = new ArrayList<>();
        listB.add(2);

        final Closure<List<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<Integer>> col = new ArrayList<>();
        col.add(listA);
        col.add(listB);
        List<Integer> last = IterableUtils.forEachButLast(col, testClosure);
        assertTrue(listA.isEmpty() && !listB.isEmpty());
    }

    @Test
    public void forEachButLast_2_oe() {
        final List<Integer> listA = new ArrayList<>();
        listA.add(1);

        final List<Integer> listB = new ArrayList<>();
        listB.add(2);

        final Closure<List<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<Integer>> col = new ArrayList<>();
        col.add(listA);
        col.add(listB);
        List<Integer> last = IterableUtils.forEachButLast(col, testClosure);
        // removed other assertion
        assertSame(listB, last);
    }

    @Test
    public void forEachButLast_4_oe() {
        final List<Integer> listA = new ArrayList<>();
        listA.add(1);

        final List<Integer> listB = new ArrayList<>();
        listB.add(2);

        final Closure<List<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<Integer>> col = new ArrayList<>();
        col.add(listA);
        col.add(listB);
        List<Integer> last = IterableUtils.forEachButLast(col, testClosure);
        // removed other assertion
        // removed other assertion

        try {
            IterableUtils.forEachButLast(col, null);
            // removed other assertion
        } catch (final NullPointerException npe) {
            // expected
        }

        IterableUtils.forEachButLast(null, testClosure);

        // null should be OK
        col.add(null);
        col.add(null);
        last = IterableUtils.forEachButLast(col, testClosure);
        assertNull(last);
    }

    @Test
    public void containsWithEquator_1_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final Equator<String> secondLetterEquator = new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }

        };

        assertFalse(base.contains("CC"));
    }

    @Test
    public void containsWithEquator_2_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final Equator<String> secondLetterEquator = new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }

        };

        // removed other assertion
        assertTrue(IterableUtils.contains(base, "AC", secondLetterEquator));
    }

    @Test
    public void containsWithEquator_3_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final Equator<String> secondLetterEquator = new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }

        };

        // removed other assertion
        // removed other assertion
        assertTrue(IterableUtils.contains(base, "CC", secondLetterEquator));
    }

    @Test
    public void containsWithEquator_4_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final Equator<String> secondLetterEquator = new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }

        };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(IterableUtils.contains(base, "CX", secondLetterEquator));
    }

    @Test
    public void containsWithEquator_5_oe() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final Equator<String> secondLetterEquator = new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }

        };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(IterableUtils.contains(null, null, secondLetterEquator));
    }

    @Test
    public void frequency_1_oe() {
        // null iterable test
        assertEquals(0, IterableUtils.frequency(null, 1));
    }

    @Test
    public void frequency_2_oe() {
        // null iterable test
        // removed other assertion

        assertEquals(1, IterableUtils.frequency(iterableA, 1));
    }

    @Test
    public void frequency_3_oe() {
        // null iterable test
        // removed other assertion

        // removed other assertion
        assertEquals(2, IterableUtils.frequency(iterableA, 2));
    }

    @Test
    public void frequency_4_oe() {
        // null iterable test
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(3, IterableUtils.frequency(iterableA, 3));
    }

    @Test
    public void frequency_5_oe() {
        // null iterable test
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, IterableUtils.frequency(iterableA, 4));
    }

    @Test
    public void frequency_6_oe() {
        // null iterable test
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, IterableUtils.frequency(iterableA, 5));
    }

    @Test
    public void frequency_7_oe() {
        // null iterable test
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, IterableUtils.frequency(iterableB, 1L));
    }

    @Test
    public void frequency_8_oe() {
        // null iterable test
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(4, IterableUtils.frequency(iterableB, 2L));
    }

    @Test
    public void frequency_9_oe() {
        // null iterable test
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(3, IterableUtils.frequency(iterableB, 3L));
    }

    @Test
    public void frequency_10_oe() {
        // null iterable test
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, IterableUtils.frequency(iterableB, 4L));
    }

    @Test
    public void frequency_11_oe() {
        // null iterable test
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
        assertEquals(1, IterableUtils.frequency(iterableB, 5L));
    }

    @Test
    public void frequency_12_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
        assertEquals(0, IterableUtils.frequency(iterableIntAsNumber, 2L));
    }

    @Test
    public void frequency_13_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
        // removed other assertion
        assertEquals(0, IterableUtils.frequency(iterableLongAsNumber, 2));
    }

    @Test
    public void frequency_14_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        assertEquals(1, IterableUtils.frequency(set, "A"));
    }

    @Test
    public void frequency_15_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        assertEquals(0, IterableUtils.frequency(set, "B"));
    }

    @Test
    public void frequency_16_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
        // removed other assertion
        // removed other assertion

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        // removed other assertion
        // removed other assertion
        assertEquals(1, IterableUtils.frequency(set, "C"));
    }

    @Test
    public void frequency_17_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
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
        assertEquals(0, IterableUtils.frequency(set, "D"));
    }

    @Test
    public void frequency_18_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
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
        assertEquals(1, IterableUtils.frequency(set, "E"));
    }

    @Test
    public void frequency_19_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
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
        assertEquals(3, IterableUtils.frequency(bag, "A"));
    }

    @Test
    public void frequency_20_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
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
        assertEquals(0, IterableUtils.frequency(bag, "B"));
    }

    @Test
    public void frequency_21_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
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
        assertEquals(1, IterableUtils.frequency(bag, "C"));
    }

    @Test
    public void frequency_22_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
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
        assertEquals(0, IterableUtils.frequency(bag, "D"));
    }

    @Test
    public void frequency_23_oe() {
        // null iterable test
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

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        final Iterable<Number> iterableIntAsNumber = Arrays.<Number>asList(1, 2, 3, 4, 5);
        final Iterable<Number> iterableLongAsNumber = Arrays.<Number>asList(1L, 2L, 3L, 4L, 5L);
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
        assertEquals(2, IterableUtils.frequency(bag, "E"));
    }

    @Test
    public void frequencyOfNull_1_oe() {
        final List<String> list = new ArrayList<>();
        assertEquals(0, IterableUtils.frequency(list, null));
    }

    @Test
    public void frequencyOfNull_2_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("A");
        assertEquals(0, IterableUtils.frequency(list, null));
    }

    @Test
    public void frequencyOfNull_3_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("A");
        // removed other assertion
        list.add(null);
        assertEquals(1, IterableUtils.frequency(list, null));
    }

    @Test
    public void frequencyOfNull_4_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("A");
        // removed other assertion
        list.add(null);
        // removed other assertion
        list.add("B");
        assertEquals(1, IterableUtils.frequency(list, null));
    }

    @Test
    public void frequencyOfNull_5_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("A");
        // removed other assertion
        list.add(null);
        // removed other assertion
        list.add("B");
        // removed other assertion
        list.add(null);
        assertEquals(2, IterableUtils.frequency(list, null));
    }

    @Test
    public void frequencyOfNull_6_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("A");
        // removed other assertion
        list.add(null);
        // removed other assertion
        list.add("B");
        // removed other assertion
        list.add(null);
        // removed other assertion
        list.add("B");
        assertEquals(2, IterableUtils.frequency(list, null));
    }

    @Test
    public void frequencyOfNull_7_oe() {
        final List<String> list = new ArrayList<>();
        // removed other assertion
        list.add("A");
        // removed other assertion
        list.add(null);
        // removed other assertion
        list.add("B");
        // removed other assertion
        list.add(null);
        // removed other assertion
        list.add("B");
        // removed other assertion
        list.add(null);
        assertEquals(3, IterableUtils.frequency(list, null));
    }

    @Test
    public void find_1_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = IterableUtils.find(iterableA, testPredicate);
        assertTrue(test.equals(4));
    }

    @Test
    public void find_2_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = IterableUtils.find(iterableA, testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        test = IterableUtils.find(iterableA, testPredicate);
        assertTrue(test == null);
    }

    @Test
    public void find_3_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = IterableUtils.find(iterableA, testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        test = IterableUtils.find(iterableA, testPredicate);
        // removed other assertion
        assertNull(IterableUtils.find(null,testPredicate));
    }

    @Test
    public void indexOf_1_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        int index = IterableUtils.indexOf(iterableA, testPredicate);
        assertEquals(6, index);
    }

    @Test
    public void indexOf_2_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        int index = IterableUtils.indexOf(iterableA, testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        index = IterableUtils.indexOf(iterableA, testPredicate);
        assertEquals(-1, index);
    }

    @Test
    public void indexOf_3_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        int index = IterableUtils.indexOf(iterableA, testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        index = IterableUtils.indexOf(iterableA, testPredicate);
        // removed other assertion
        assertEquals(-1, IterableUtils.indexOf(null, testPredicate));
    }

    @Test
    public void countMatches_1_oe() {
        assertEquals(4, IterableUtils.countMatches(iterableB, EQUALS_TWO));
    }

    @Test
    public void countMatches_2_oe() {
        // removed other assertion
        assertEquals(0, IterableUtils.countMatches(null, EQUALS_TWO));
    }

    @Test
    public void matchesAny_5_oe() {
        final List<Integer> list = new ArrayList<>();

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        assertFalse(IterableUtils.matchesAny(null, EQUALS_TWO));
    }

    @Test
    public void matchesAny_6_oe() {
        final List<Integer> list = new ArrayList<>();

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        // removed other assertion
        assertFalse(IterableUtils.matchesAny(list, EQUALS_TWO));
    }

    @Test
    public void matchesAny_7_oe() {
        final List<Integer> list = new ArrayList<>();

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        // removed other assertion
        // removed other assertion
        list.add(1);
        list.add(3);
        list.add(4);
        assertFalse(IterableUtils.matchesAny(list, EQUALS_TWO));
    }

    @Test
    public void matchesAny_8_oe() {
        final List<Integer> list = new ArrayList<>();

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        // removed other assertion
        // removed other assertion
        list.add(1);
        list.add(3);
        list.add(4);
        // removed other assertion

        list.add(2);
        assertEquals(true, IterableUtils.matchesAny(list, EQUALS_TWO));
    }

    @Test
    public void matchesAll_5_oe() {
        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        final Predicate<Integer> lessThanFive = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object < 5;
            }
        };
        assertTrue(IterableUtils.matchesAll(iterableA, lessThanFive));
    }

    @Test
    public void matchesAll_6_oe() {
        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

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
        assertFalse(IterableUtils.matchesAll(iterableA, lessThanFour));
    }

    @Test
    public void matchesAll_7_oe() {
        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

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

        assertTrue(IterableUtils.matchesAll(null, lessThanFour));
    }

    @Test
    public void matchesAll_8_oe() {
        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

        try {
            // removed other assertion
            // removed other assertion
        } catch (final NullPointerException ex) {
            // expected
        }

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
        assertTrue(IterableUtils.matchesAll(emptyIterable, lessThanFour));
    }

    @Test
    public void partition_1_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO);
        assertEquals(2, partitions.size());
    }

    @Test
    public void partition_2_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO);
        // removed other assertion

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        assertEquals(1, partition.size());
    }

    @Test
    public void partition_3_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO);
        // removed other assertion

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        assertEquals(2, CollectionUtils.extractSingleton(partition).intValue());
    }

    @Test
    public void partition_4_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO);
        // removed other assertion

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        // removed other assertion

        // second partition contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        partition = partitions.get(1);
        Assert.assertArrayEquals(expected, partition.toArray());
    }

    @Test
    public void partition_5_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO);
        // removed other assertion

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        // removed other assertion

        // second partition contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        partition = partitions.get(1);
        // removed other assertion

        partitions = IterableUtils.partition((List<Integer>) null, EQUALS_TWO);
        assertEquals(2, partitions.size());
    }

    @Test
    public void partition_6_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO);
        // removed other assertion

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        // removed other assertion

        // second partition contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        partition = partitions.get(1);
        // removed other assertion

        partitions = IterableUtils.partition((List<Integer>) null, EQUALS_TWO);
        // removed other assertion
        assertTrue(partitions.get(0).isEmpty());
    }

    @Test
    public void partition_7_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO);
        // removed other assertion

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        // removed other assertion

        // second partition contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        partition = partitions.get(1);
        // removed other assertion

        partitions = IterableUtils.partition((List<Integer>) null, EQUALS_TWO);
        // removed other assertion
        // removed other assertion
        assertTrue(partitions.get(1).isEmpty());
    }

    @Test
    public void partition_8_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO);
        // removed other assertion

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        // removed other assertion

        // second partition contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        partition = partitions.get(1);
        // removed other assertion

        partitions = IterableUtils.partition((List<Integer>) null, EQUALS_TWO);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        partitions = IterableUtils.partition(input);
        assertEquals(1, partitions.size());
    }

    @Test
    public void partition_9_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO);
        // removed other assertion

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        // removed other assertion

        // second partition contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        partition = partitions.get(1);
        // removed other assertion

        partitions = IterableUtils.partition((List<Integer>) null, EQUALS_TWO);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        partitions = IterableUtils.partition(input);
        // removed other assertion
        assertEquals(input, partitions.get(0));
    }

    @Test
    public void partitionMultiplePredicates_1_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        final List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO, EVEN);

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        assertEquals(1, partition.size());
    }

    @Test
    public void partitionMultiplePredicates_2_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        final List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO, EVEN);

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        assertEquals(2, partition.iterator().next().intValue());
    }

    @Test
    public void partitionMultiplePredicates_3_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        final List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO, EVEN);

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        // removed other assertion

        // second partition contains 4
        partition = partitions.get(1);
        assertEquals(1, partition.size());
    }

    @Test
    public void partitionMultiplePredicates_4_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        final List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO, EVEN);

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        // removed other assertion

        // second partition contains 4
        partition = partitions.get(1);
        // removed other assertion
        assertEquals(4, partition.iterator().next().intValue());
    }

    @Test
    public void partitionMultiplePredicates_5_oe() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        final List<List<Integer>> partitions = IterableUtils.partition(input, EQUALS_TWO, EVEN);

        // first partition contains 2
        Collection<Integer> partition = partitions.get(0);
        // removed other assertion
        // removed other assertion

        // second partition contains 4
        partition = partitions.get(1);
        // removed other assertion
        // removed other assertion

        // third partition contains 1 and 3
        final Integer[] expected = {1, 3};
        partition = partitions.get(2);
        Assert.assertArrayEquals(expected, partition.toArray());
    }

    @Test
    public void testToString_1_oe() {
        String result = IterableUtils.toString(iterableA);
        assertEquals("[1, 2, 2, 3, 3, 3, 4, 4, 4, 4]", result);
    }

    @Test
    public void testToString_2_oe() {
        String result = IterableUtils.toString(iterableA);
        // removed other assertion

        result = IterableUtils.toString(new ArrayList<Integer>());
        assertEquals("[]", result);
    }

    @Test
    public void testToString_3_oe() {
        String result = IterableUtils.toString(iterableA);
        // removed other assertion

        result = IterableUtils.toString(new ArrayList<Integer>());
        // removed other assertion

        result = IterableUtils.toString(null);
        assertEquals("[]", result);
    }

    @Test
    public void testToString_4_oe() {
        String result = IterableUtils.toString(iterableA);
        // removed other assertion

        result = IterableUtils.toString(new ArrayList<Integer>());
        // removed other assertion

        result = IterableUtils.toString(null);
        // removed other assertion

        result = IterableUtils.toString(iterableA, new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        });
        assertEquals("[2, 4, 4, 6, 6, 6, 8, 8, 8, 8]", result);
    }

    @Test
    public void testToString_6_oe() {
        String result = IterableUtils.toString(iterableA);
        // removed other assertion

        result = IterableUtils.toString(new ArrayList<Integer>());
        // removed other assertion

        result = IterableUtils.toString(null);
        // removed other assertion

        result = IterableUtils.toString(iterableA, new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        });
        // removed other assertion

        result = IterableUtils.toString(new ArrayList<Integer>(), new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                // removed other assertion
                return "";
            }
        });
        assertEquals("[]", result);
    }

    @Test
    public void testToString_8_oe() {
        String result = IterableUtils.toString(iterableA);
        // removed other assertion

        result = IterableUtils.toString(new ArrayList<Integer>());
        // removed other assertion

        result = IterableUtils.toString(null);
        // removed other assertion

        result = IterableUtils.toString(iterableA, new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        });
        // removed other assertion

        result = IterableUtils.toString(new ArrayList<Integer>(), new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                // removed other assertion
                return "";
            }
        });
        // removed other assertion

        result = IterableUtils.toString(null, new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                // removed other assertion
                return "";
            }
        });
        assertEquals("[]", result);
    }

    @Test
    public void testToStringDelimiter_1_oe() {

        final Transformer<Integer, String> transformer = new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        };

        String result = IterableUtils.toString(iterableA, transformer, "", "", "");
        assertEquals("2446668888", result);
    }

    @Test
    public void testToStringDelimiter_2_oe() {

        final Transformer<Integer, String> transformer = new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        };

        String result = IterableUtils.toString(iterableA, transformer, "", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "", "");
        assertEquals("2,4,4,6,6,6,8,8,8,8", result);
    }

    @Test
    public void testToStringDelimiter_3_oe() {

        final Transformer<Integer, String> transformer = new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        };

        String result = IterableUtils.toString(iterableA, transformer, "", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, "", "[", "]");
        assertEquals("[2446668888]", result);
    }

    @Test
    public void testToStringDelimiter_4_oe() {

        final Transformer<Integer, String> transformer = new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        };

        String result = IterableUtils.toString(iterableA, transformer, "", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, "", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[", "]");
        assertEquals("[2,4,4,6,6,6,8,8,8,8]", result);
    }

    @Test
    public void testToStringDelimiter_5_oe() {

        final Transformer<Integer, String> transformer = new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        };

        String result = IterableUtils.toString(iterableA, transformer, "", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, "", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[[", "]]");
        assertEquals("[[2,4,4,6,6,6,8,8,8,8]]", result);
    }

    @Test
    public void testToStringDelimiter_6_oe() {

        final Transformer<Integer, String> transformer = new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        };

        String result = IterableUtils.toString(iterableA, transformer, "", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, "", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[[", "]]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",,", "[", "]");
        assertEquals("[2,,4,,4,,6,,6,,6,,8,,8,,8,,8]", result);
    }

    @Test
    public void testToStringDelimiter_7_oe() {

        final Transformer<Integer, String> transformer = new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        };

        String result = IterableUtils.toString(iterableA, transformer, "", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, "", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[[", "]]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",,", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",,", "((", "))");
        assertEquals("((2,,4,,4,,6,,6,,6,,8,,8,,8,,8))", result);
    }

    @Test
    public void testToStringDelimiter_8_oe() {

        final Transformer<Integer, String> transformer = new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        };

        String result = IterableUtils.toString(iterableA, transformer, "", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, "", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[[", "]]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",,", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",,", "((", "))");
        // removed other assertion

        result = IterableUtils.toString(new ArrayList<Integer>(), transformer, "", "(", ")");
        assertEquals("()", result);
    }

    @Test
    public void testToStringDelimiter_9_oe() {

        final Transformer<Integer, String> transformer = new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                return new Integer(input * 2).toString();
            }
        };

        String result = IterableUtils.toString(iterableA, transformer, "", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "", "");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, "", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",", "[[", "]]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",,", "[", "]");
        // removed other assertion

        result = IterableUtils.toString(iterableA, transformer, ",,", "((", "))");
        // removed other assertion

        result = IterableUtils.toString(new ArrayList<Integer>(), transformer, "", "(", ")");
        // removed other assertion

        result = IterableUtils.toString(new ArrayList<Integer>(), transformer, "", "", "");
        assertEquals("", result);
    }

    @Test
    public void testToStringWithNullArguments_2_oe() {
        final String result = IterableUtils.toString(null, new Transformer<Integer, String>() {
            @Override
            public String transform(final Integer input) {
                // removed other assertion
                return "";
            }
        }, "", "(", ")");
        assertEquals("()", result);
    }

}
