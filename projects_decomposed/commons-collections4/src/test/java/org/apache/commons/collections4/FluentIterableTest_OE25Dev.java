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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for FluentIterable.
 *
 * @since 4.1
 */
public class FluentIterableTest_OE25Dev {

    /**
     * Iterable of {@link Integer}s
     */
    private Iterable<Integer> iterableA = null;

    /**
     * Iterable of {@link Long}s
     */
    private Iterable<Long> iterableB = null;

    /**
     * Collection of even {@link Integer}s
     */
    private Iterable<Integer> iterableEven = null;

    /**
     * Collection of odd {@link Integer}s
     */
    private Iterable<Integer> iterableOdd = null;

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

        iterableEven = Arrays.asList(2, 4, 6, 8, 10, 12);
        iterableOdd = Arrays.asList(1, 3, 5, 7, 9, 11);

        emptyIterable = Collections.emptyList();
    }

    private static Predicate<Number> EVEN = new Predicate<Number>() {
        @Override
        public boolean evaluate(final Number input) {
            return input.intValue() % 2 == 0;
        }
    };

    // -----------------------------------------------------------------------

    @Test
    public void factoryMethodOf_1_oe() {
        FluentIterable<Integer> iterable = FluentIterable.of(1, 2, 3, 4, 5);
        List<Integer> result = iterable.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void factoryMethodOf_2_oe() {
        FluentIterable<Integer> iterable = FluentIterable.of(1, 2, 3, 4, 5);
        List<Integer> result = iterable.toList();

        iterable = FluentIterable.of(1);
        assertEquals(1, iterable.size());
    }

    @Test
    public void factoryMethodOf_3_oe() {
        FluentIterable<Integer> iterable = FluentIterable.of(1, 2, 3, 4, 5);
        List<Integer> result = iterable.toList();

        iterable = FluentIterable.of(1);
        assertFalse(iterable.isEmpty());
    }

    @Test
    public void factoryMethodOf_4_oe() {
        FluentIterable<Integer> iterable = FluentIterable.of(1, 2, 3, 4, 5);
        List<Integer> result = iterable.toList();

        iterable = FluentIterable.of(1);
        assertEquals(Arrays.asList(1), iterable.toList());
    }

    @Test
    public void factoryMethodOf_5_oe() {
        FluentIterable<Integer> iterable = FluentIterable.of(1, 2, 3, 4, 5);
        List<Integer> result = iterable.toList();

        iterable = FluentIterable.of(1);

        result = FluentIterable.of(new Integer[0]).toList();
        assertTrue(result.isEmpty());
    }

    @Test
    public void appendElements_1_oe() {
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(10, 20, 30);
        assertEquals(IterableUtils.size(iterableA) + 3, IterableUtils.size(it));
    }

    @Test
    public void appendElements_2_oe() {
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(10, 20, 30);
        assertTrue(IterableUtils.contains(it, 1));
    }

    @Test
    public void appendElements_3_oe() {
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(10, 20, 30);
        assertTrue(IterableUtils.contains(it, 10));
    }

    @Test
    public void appendElements_4_oe() {
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(10, 20, 30);
        assertTrue(IterableUtils.contains(it, 20));
    }

    @Test
    public void appendElements_5_oe() {
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(10, 20, 30);
        assertTrue(IterableUtils.contains(it, 30));
    }

    @Test
    public void appendElements_6_oe() {
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(10, 20, 30);
        assertFalse(IterableUtils.contains(it, 40));
    }

    @Test
    public void appendElements_7_oe() {
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(10, 20, 30);

        final FluentIterable<Integer> empty = FluentIterable.of(emptyIterable).append();
        assertTrue(IterableUtils.isEmpty(empty));
    }

    @Test
    public void appendIterable_1_oe() {
        final List<Integer> listB = Arrays.asList(10, 20, 30);
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(listB);
        assertEquals(IterableUtils.size(iterableA) + listB.size(), IterableUtils.size(it));
    }

    @Test
    public void appendIterable_2_oe() {
        final List<Integer> listB = Arrays.asList(10, 20, 30);
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(listB);
        assertTrue(IterableUtils.contains(it, 1));
    }

    @Test
    public void appendIterable_3_oe() {
        final List<Integer> listB = Arrays.asList(10, 20, 30);
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(listB);
        assertTrue(IterableUtils.contains(it, 10));
    }

    @Test
    public void appendIterable_4_oe() {
        final List<Integer> listB = Arrays.asList(10, 20, 30);
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(listB);
        assertTrue(IterableUtils.contains(it, 20));
    }

    @Test
    public void appendIterable_5_oe() {
        final List<Integer> listB = Arrays.asList(10, 20, 30);
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(listB);
        assertTrue(IterableUtils.contains(it, 30));
    }

    @Test
    public void appendIterable_6_oe() {
        final List<Integer> listB = Arrays.asList(10, 20, 30);
        final FluentIterable<Integer> it = FluentIterable.of(iterableA).append(listB);
        assertFalse(IterableUtils.contains(it, 40));
    }

    @Test
    public void collate_1_oe() {
        final List<Integer> result = FluentIterable.of(iterableOdd).collate(iterableEven).toList();
        final List<Integer> combinedList = new ArrayList<>();
        CollectionUtils.addAll(combinedList, iterableOdd);
        CollectionUtils.addAll(combinedList, iterableEven);
        Collections.sort(combinedList);
        assertEquals(combinedList, result);
    }

    @Test
    public void collateWithComparator_1_oe() {
        List<Integer> result =
                FluentIterable
                    .of(iterableOdd)
                    .collate(iterableEven, ComparatorUtils.<Integer>naturalComparator())
                    .toList();

        final List<Integer> combinedList = new ArrayList<>();
        CollectionUtils.addAll(combinedList, iterableOdd);
        CollectionUtils.addAll(combinedList, iterableEven);
        Collections.sort(combinedList);
        assertEquals(combinedList, result);
    }

    @Test
    public void collateWithComparator_2_oe() {
        List<Integer> result =
                FluentIterable
                    .of(iterableOdd)
                    .collate(iterableEven, ComparatorUtils.<Integer>naturalComparator())
                    .toList();

        final List<Integer> combinedList = new ArrayList<>();
        CollectionUtils.addAll(combinedList, iterableOdd);
        CollectionUtils.addAll(combinedList, iterableEven);
        Collections.sort(combinedList);

        result = FluentIterable.of(iterableOdd).collate(iterableEven, null).toList();
        assertEquals(combinedList, result);
    }

    @Test
    public void filter_1_oe() {
        final Predicate<Integer> smallerThan3 = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object.intValue() < 3;
            }
        };
        List<Integer> result = FluentIterable.of(iterableA).filter(smallerThan3).toList();
        assertEquals(3, result.size());
    }

    @Test
    public void filter_2_oe() {
        final Predicate<Integer> smallerThan3 = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object.intValue() < 3;
            }
        };
        List<Integer> result = FluentIterable.of(iterableA).filter(smallerThan3).toList();
        assertEquals(Arrays.asList(1, 2, 2), result);
    }

    @Test
    public void filter_3_oe() {
        final Predicate<Integer> smallerThan3 = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer object) {
                return object.intValue() < 3;
            }
        };
        List<Integer> result = FluentIterable.of(iterableA).filter(smallerThan3).toList();

        result = FluentIterable.of(emptyIterable).filter(smallerThan3).toList();
        assertEquals(0, result.size());
    }

    @Test
    public void forEach_1_oe() {
        final AtomicInteger sum = new AtomicInteger(0);
        final Closure<Integer> closure = new Closure<Integer>() {
            @Override
            public void execute(final Integer input) {
                sum.addAndGet(input);
            }
        };

        FluentIterable.of(iterableA).forEach(closure);
        int expectedSum = 0;
        for (final Integer i : iterableA) {
            expectedSum += i;
        }
        assertEquals(expectedSum, sum.get());
    }

    @Test
    public void limit_1_oe() {
        List<Integer> result = FluentIterable.of(iterableA).limit(3).toList();
        assertEquals(3, result.size());
    }

    @Test
    public void limit_2_oe() {
        List<Integer> result = FluentIterable.of(iterableA).limit(3).toList();
        assertEquals(Arrays.asList(1, 2, 2), result);
    }

    @Test
    public void limit_3_oe() {
        List<Integer> result = FluentIterable.of(iterableA).limit(3).toList();

        result = FluentIterable.of(iterableA).limit(100).toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void limit_4_oe() {
        List<Integer> result = FluentIterable.of(iterableA).limit(3).toList();

        result = FluentIterable.of(iterableA).limit(100).toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);
        assertEquals(expected, result);
    }

    @Test
    public void limit_5_oe() {
        List<Integer> result = FluentIterable.of(iterableA).limit(3).toList();

        result = FluentIterable.of(iterableA).limit(100).toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);

        result = FluentIterable.of(iterableA).limit(0).toList();
        assertEquals(0, result.size());
    }

    @Test
    public void limit_6_oe() {
        List<Integer> result = FluentIterable.of(iterableA).limit(3).toList();

        result = FluentIterable.of(iterableA).limit(100).toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);

        result = FluentIterable.of(iterableA).limit(0).toList();

        result = FluentIterable.of(emptyIterable).limit(3).toList();
        assertEquals(0, result.size());
    }

    @Test
    public void reverse_1_oe() {
        List<Integer> result = FluentIterable.of(iterableA).reverse().toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);
        Collections.reverse(expected);
        assertEquals(expected, result);
    }

    @Test
    public void reverse_2_oe() {
        List<Integer> result = FluentIterable.of(iterableA).reverse().toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);
        Collections.reverse(expected);

        result = FluentIterable.of(emptyIterable).reverse().toList();
        assertEquals(0, result.size());
    }

    @Test
    public void skip_1_oe() {
        List<Integer> result = FluentIterable.of(iterableA).skip(4).toList();
        assertEquals(6, result.size());
    }

    @Test
    public void skip_2_oe() {
        List<Integer> result = FluentIterable.of(iterableA).skip(4).toList();
        assertEquals(Arrays.asList(3, 3, 4, 4, 4, 4), result);
    }

    @Test
    public void skip_3_oe() {
        List<Integer> result = FluentIterable.of(iterableA).skip(4).toList();

        result = FluentIterable.of(iterableA).skip(100).toList();
        assertEquals(0, result.size());
    }

    @Test
    public void skip_4_oe() {
        List<Integer> result = FluentIterable.of(iterableA).skip(4).toList();

        result = FluentIterable.of(iterableA).skip(100).toList();

        result = FluentIterable.of(iterableA).skip(0).toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void skip_5_oe() {
        List<Integer> result = FluentIterable.of(iterableA).skip(4).toList();

        result = FluentIterable.of(iterableA).skip(100).toList();

        result = FluentIterable.of(iterableA).skip(0).toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);
        assertEquals(expected, result);
    }

    @Test
    public void skip_6_oe() {
        List<Integer> result = FluentIterable.of(iterableA).skip(4).toList();

        result = FluentIterable.of(iterableA).skip(100).toList();

        result = FluentIterable.of(iterableA).skip(0).toList();
        final List<Integer> expected = IterableUtils.toList(iterableA);

        result = FluentIterable.of(emptyIterable).skip(3).toList();
        assertEquals(0, result.size());
    }

    @Test
    public void transform_1_oe() {
        final Transformer<Integer, Integer> squared = new Transformer<Integer, Integer>() {
            @Override
            public Integer transform(final Integer object) {
                return object * object;
            }
        };
        List<Integer> result = FluentIterable.of(iterableA).transform(squared).toList();
        assertEquals(10, result.size());
    }

    @Test
    public void transform_2_oe() {
        final Transformer<Integer, Integer> squared = new Transformer<Integer, Integer>() {
            @Override
            public Integer transform(final Integer object) {
                return object * object;
            }
        };
        List<Integer> result = FluentIterable.of(iterableA).transform(squared).toList();
        assertEquals(Arrays.asList(1, 4, 4, 9, 9, 9, 16, 16, 16, 16), result);
    }

    @Test
    public void transform_3_oe() {
        final Transformer<Integer, Integer> squared = new Transformer<Integer, Integer>() {
            @Override
            public Integer transform(final Integer object) {
                return object * object;
            }
        };
        List<Integer> result = FluentIterable.of(iterableA).transform(squared).toList();

        result = FluentIterable.of(emptyIterable).transform(squared).toList();
        assertEquals(0, result.size());
    }

    @Test
    public void unique_1_oe() {
        List<Integer> result = FluentIterable.of(iterableA).unique().toList();
        assertEquals(4, result.size());
    }

    @Test
    public void unique_2_oe() {
        List<Integer> result = FluentIterable.of(iterableA).unique().toList();
        assertEquals(Arrays.asList(1, 2, 3, 4), result);
    }

    @Test
    public void unique_3_oe() {
        List<Integer> result = FluentIterable.of(iterableA).unique().toList();

        result = FluentIterable.of(emptyIterable).unique().toList();
        assertEquals(0, result.size());
    }

    @Test
    public void unmodifiable_1_oe() {
        final FluentIterable<Integer> iterable1 = FluentIterable.of(iterableA).unmodifiable();
        final Iterator<Integer> it = iterable1.iterator();
        assertEquals(1, it.next().intValue());
    }

    @Test
    public void unmodifiable_3_oe() {
        final FluentIterable<Integer> iterable1 = FluentIterable.of(iterableA).unmodifiable();
        final Iterator<Integer> it = iterable1.iterator();
        try {
            it.remove();
        } catch (final UnsupportedOperationException ise) {
        }

        final FluentIterable<Integer> iterable2 = iterable1.unmodifiable();
        assertSame(iterable1, iterable2);
    }

    @Test
    public void zip_1_oe() {
        List<Integer> result = FluentIterable.of(iterableOdd).zip(iterableEven).toList();
        List<Integer> combinedList = new ArrayList<>();
        CollectionUtils.addAll(combinedList, iterableOdd);
        CollectionUtils.addAll(combinedList, iterableEven);
        Collections.sort(combinedList);
        assertEquals(combinedList, result);
    }

    @Test
    public void zip_3_oe() {
        List<Integer> result = FluentIterable.of(iterableOdd).zip(iterableEven).toList();
        List<Integer> combinedList = new ArrayList<>();
        CollectionUtils.addAll(combinedList, iterableOdd);
        CollectionUtils.addAll(combinedList, iterableEven);
        Collections.sort(combinedList);

        try {
            FluentIterable.of(iterableOdd).zip((Iterable<Integer>) null).toList();
        } catch (final NullPointerException npe) {
        }

        result = FluentIterable
                    .of(Arrays.asList(1, 4, 7))
                    .zip(Arrays.asList(2, 5, 8), Arrays.asList(3, 6, 9))
                    .toList();
        combinedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(combinedList, result);
    }

    @Test
    public void asEnumeration_1_oe() {
        Enumeration<Long> enumeration = FluentIterable.of(iterableB).asEnumeration();
        final List<Long> result = EnumerationUtils.toList(enumeration);
        assertEquals(iterableB, result);
    }

    @Test
    public void asEnumeration_2_oe() {
        Enumeration<Long> enumeration = FluentIterable.of(iterableB).asEnumeration();
        final List<Long> result = EnumerationUtils.toList(enumeration);

        enumeration = FluentIterable.<Long>empty().asEnumeration();
        assertFalse(enumeration.hasMoreElements());
    }

    @Test
    public void allMatch_1_oe() {
        assertTrue(FluentIterable.of(iterableEven).allMatch(EVEN));
    }

    @Test
    public void allMatch_2_oe() {
        assertFalse(FluentIterable.of(iterableOdd).allMatch(EVEN));
    }

    @Test
    public void allMatch_3_oe() {
        assertFalse(FluentIterable.of(iterableA).allMatch(EVEN));
    }

    @Test
    public void anyMatch_1_oe() {
        assertTrue(FluentIterable.of(iterableEven).anyMatch(EVEN));
    }

    @Test
    public void anyMatch_2_oe() {
        assertFalse(FluentIterable.of(iterableOdd).anyMatch(EVEN));
    }

    @Test
    public void anyMatch_3_oe() {
        assertTrue(FluentIterable.of(iterableA).anyMatch(EVEN));
    }

    @Test
    public void isEmpty_1_oe() {
        assertTrue(FluentIterable.of(emptyIterable).isEmpty());
    }

    @Test
    public void isEmpty_2_oe() {
        assertFalse(FluentIterable.of(iterableOdd).isEmpty());
    }

    @Test
    public void size_2_oe() {
        try {
            FluentIterable.of((Iterable<?>) null).size();
        } catch (final NullPointerException npe) {
        }
        assertEquals(0, FluentIterable.of(emptyIterable).size());
    }

    @Test
    public void size_3_oe() {
        try {
            FluentIterable.of((Iterable<?>) null).size();
        } catch (final NullPointerException npe) {
        }
        assertEquals(IterableUtils.toList(iterableOdd).size(), FluentIterable.of(iterableOdd).size());
    }

    @Test
    public void eval_1_oe() {
        final List<Integer> listNumbers = new ArrayList<>();
        listNumbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        final FluentIterable<Integer> iterable = FluentIterable.of(listNumbers).filter(EVEN);
        final FluentIterable<Integer> materialized = iterable.eval();

        listNumbers.addAll(Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        assertEquals(5, materialized.size());
    }

    @Test
    public void eval_2_oe() {
        final List<Integer> listNumbers = new ArrayList<>();
        listNumbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        final FluentIterable<Integer> iterable = FluentIterable.of(listNumbers).filter(EVEN);
        final FluentIterable<Integer> materialized = iterable.eval();

        listNumbers.addAll(Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        assertEquals(10, iterable.size());
    }

    @Test
    public void eval_3_oe() {
        final List<Integer> listNumbers = new ArrayList<>();
        listNumbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        final FluentIterable<Integer> iterable = FluentIterable.of(listNumbers).filter(EVEN);
        final FluentIterable<Integer> materialized = iterable.eval();

        listNumbers.addAll(Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20));

        assertEquals(Arrays.asList(2, 4, 6, 8, 10), materialized.toList());
    }

    @Test
    public void eval_4_oe() {
        final List<Integer> listNumbers = new ArrayList<>();
        listNumbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        final FluentIterable<Integer> iterable = FluentIterable.of(listNumbers).filter(EVEN);
        final FluentIterable<Integer> materialized = iterable.eval();

        listNumbers.addAll(Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20));

        assertEquals(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20), iterable.toList());
    }

    @Test
    public void contains_1_oe() {
        assertTrue(FluentIterable.of(iterableEven).contains(2));
    }

    @Test
    public void contains_2_oe() {
        assertFalse(FluentIterable.of(iterableEven).contains(1));
    }

    @Test
    public void contains_3_oe() {
        assertFalse(FluentIterable.of(iterableEven).contains(null));
    }

    @Test
    public void contains_4_oe() {
        assertTrue(FluentIterable.of(iterableEven).append((Integer) null).contains(null));
    }

    @Test
    public void copyInto_1_oe() {
        List<Integer> result = new ArrayList<>();
        FluentIterable.of(iterableA).copyInto(result);

        List<Integer> expected = IterableUtils.toList(iterableA);
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void copyInto_2_oe() {
        List<Integer> result = new ArrayList<>();
        FluentIterable.of(iterableA).copyInto(result);

        List<Integer> expected = IterableUtils.toList(iterableA);
        assertEquals(expected, result);
    }

    @Test
    public void copyInto_3_oe() {
        List<Integer> result = new ArrayList<>();
        FluentIterable.of(iterableA).copyInto(result);

        List<Integer> expected = IterableUtils.toList(iterableA);

        result = new ArrayList<>();
        result.add(10);
        result.add(9);
        result.add(8);
        FluentIterable.of(iterableA).copyInto(result);

        expected = new ArrayList<>();
        expected.addAll(Arrays.asList(10, 9, 8));
        expected.addAll(IterableUtils.toList(iterableA));
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void copyInto_4_oe() {
        List<Integer> result = new ArrayList<>();
        FluentIterable.of(iterableA).copyInto(result);

        List<Integer> expected = IterableUtils.toList(iterableA);

        result = new ArrayList<>();
        result.add(10);
        result.add(9);
        result.add(8);
        FluentIterable.of(iterableA).copyInto(result);

        expected = new ArrayList<>();
        expected.addAll(Arrays.asList(10, 9, 8));
        expected.addAll(IterableUtils.toList(iterableA));
        assertEquals(expected, result);
    }

    @Test
    public void iterator_1_oe() {
        Iterator<Integer> iterator = FluentIterable.of(iterableA).iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void iterator_2_oe() {
        Iterator<Integer> iterator = FluentIterable.of(iterableA).iterator();

        iterator = FluentIterable.<Integer>empty().iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void get_1_oe() {
        assertEquals(2, FluentIterable.of(iterableEven).get(0).intValue());
    }

    @Test
    public void toArray_1_oe() {
        final Long[] arr = new Long[] {1L, 2L, 3L, 4L, 5L};
        final Long[] result = FluentIterable.of(arr).toArray(Long.class);
        assertNotNull(result);
    }

    @Test
    public void toArray_2_oe() {
        final Long[] arr = new Long[] {1L, 2L, 3L, 4L, 5L};
        final Long[] result = FluentIterable.of(arr).toArray(Long.class);
        assertArrayEquals(arr, result);
    }

    @Test
    public void testToString_1_oe() {
        String result = FluentIterable.of(iterableA).toString();
        assertEquals(iterableA.toString(), result);
    }

    @Test
    public void testToString_2_oe() {
        String result = FluentIterable.of(iterableA).toString();

        result = FluentIterable.empty().toString();
        assertEquals("[]", result);
    }

}
