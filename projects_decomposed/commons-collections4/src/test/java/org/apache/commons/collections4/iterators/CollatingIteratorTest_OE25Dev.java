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
package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.comparators.ComparableComparator;

/**
 * Unit test suite for {@link CollatingIterator}.
 *
 */
@SuppressWarnings("boxing")
public class CollatingIteratorTest_OE25Dev extends AbstractIteratorTest<Integer> {

    //------------------------------------------------------------ Conventional

    public CollatingIteratorTest_OE25Dev(final String testName) {
        super(testName);
    }

    //--------------------------------------------------------------- Lifecycle

    private Comparator<Integer> comparator = null;
    private ArrayList<Integer> evens = null;
    private ArrayList<Integer> odds = null;
    private ArrayList<Integer> fib = null;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        comparator = new ComparableComparator<>();
        evens = new ArrayList<>();
        odds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (0 == i % 2) {
                evens.add(i);
            } else {
                odds.add(i);
            }
        }
        fib = new ArrayList<>();
        fib.add(1);
        fib.add(1);
        fib.add(2);
        fib.add(3);
        fib.add(5);
        fib.add(8);
        fib.add(13);
        fib.add(21);
    }

    //---------------------------------------------------- TestIterator Methods

    @Override
    public CollatingIterator<Integer> makeEmptyIterator() {
        return new CollatingIterator<>(comparator);
    }

    @Override
    public CollatingIterator<Integer> makeObject() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());
        iter.addIterator(fib.iterator());
        return iter;
    }

    //------------------------------------------------------------------- Tests

    public void testGetSetComparator_1_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>();
        assertNull(iter.getComparator());
    }

    public void testGetSetComparator_2_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>();
        // removed other assertion
        iter.setComparator(comparator);
        assertSame(comparator, iter.getComparator());
    }

    public void testGetSetComparator_3_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>();
        // removed other assertion
        iter.setComparator(comparator);
        // removed other assertion
        iter.setComparator(null);
        assertNull(iter.getComparator());
    }

    public void testIterateEven_1_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        for (int i = 0; i < evens.size(); i++) {
            assertTrue(iter.hasNext());
    }
    }

    public void testIterateEven_2_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        for (int i = 0; i < evens.size(); i++) {
            // removed other assertion
            assertEquals(evens.get(i), iter.next());
    }
    }

    public void testIterateEvenOdd_1_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, evens.iterator(), odds.iterator());
        for (int i = 0; i < 20; i++) {
            assertTrue(iter.hasNext());
    }
    }

    public void testIterateEvenOdd_2_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, evens.iterator(), odds.iterator());
        for (int i = 0; i < 20; i++) {
            // removed other assertion
            assertEquals(Integer.valueOf(i), iter.next());
    }
    }

    public void testIterateOddEven_1_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, odds.iterator(), evens.iterator());
        for (int i = 0; i < 20; i++) {
            assertTrue(iter.hasNext());
    }
    }

    public void testIterateOddEven_2_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator, odds.iterator(), evens.iterator());
        for (int i = 0; i < 20; i++) {
            // removed other assertion
            assertEquals(Integer.valueOf(i),iter.next());
    }
    }

    public void testIterateEvenEven_1_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(evens.iterator());
        for (int i = 0; i < evens.size(); i++) {
            assertTrue(iter.hasNext());
    }
    }

    public void testIterateEvenEven_4_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(evens.iterator());
        for (int i = 0; i < evens.size(); i++) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertTrue(iter.hasNext());
    }
    }

    public void testIterateFibEvenOdd_1_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(fib.iterator());
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());

        assertEquals(Integer.valueOf(0),iter.next());// even 0 assertEquals(1,iter.getIteratorIndex());
    }

    public void testRemoveFromSingle_1_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        int expectedSize = evens.size();
        while (iter.hasNext()) {
            final Object o = iter.next();
            final Integer val = (Integer) o;
            if (val.intValue() % 4 == 0) {
                expectedSize--;
                iter.remove();
            }
        }
        assertEquals(expectedSize,evens.size());
    }

    public void testRemoveFromDouble_1_oe() {
        final CollatingIterator<Integer> iter = new CollatingIterator<>(comparator);
        iter.addIterator(evens.iterator());
        iter.addIterator(odds.iterator());
        int expectedSize = evens.size() + odds.size();
        while (iter.hasNext()) {
            final Object o = iter.next();
            final Integer val = (Integer) o;
            if (val.intValue() % 4 == 0 || val.intValue() % 3 == 0) {
                expectedSize--;
                iter.remove();
            }
        }
        assertEquals(expectedSize, evens.size() + odds.size());
    }

    public void testNullComparator_1_oe() {
       final List<Integer> l1 = Arrays.asList(1, 3, 5);
       final List<Integer> l2 = Arrays.asList(2, 4, 6);

       final CollatingIterator<Integer> collatingIterator1 = new CollatingIterator<>(null, l1.iterator(), l2.iterator());
       try {
           collatingIterator1.next();
       } catch (final NullPointerException e) {
           assertTrue(e.getMessage().startsWith("You must invoke setComparator"));
    }
    }

    public void testNullComparator_2_oe() {
       final List<Integer> l1 = Arrays.asList(1, 3, 5);
       final List<Integer> l2 = Arrays.asList(2, 4, 6);

       final CollatingIterator<Integer> collatingIterator1 = new CollatingIterator<>(null, l1.iterator(), l2.iterator());
       try {
           collatingIterator1.next();
       } catch (final NullPointerException e) {
           // removed other assertion
       }

       int i = 0;
       final CollatingIterator<Integer> collatingIterator2 = new CollatingIterator<>(null, l1.iterator(), l2.iterator());
       collatingIterator2.setComparator(new ComparableComparator<Integer>());
       for ( ; collatingIterator2.hasNext(); i++ ) {
          final Integer n = collatingIterator2.next();
          assertEquals("wrong order", (int)n, i + 1);
    }
    }

    public void testNullComparator_3_oe() {
       final List<Integer> l1 = Arrays.asList(1, 3, 5);
       final List<Integer> l2 = Arrays.asList(2, 4, 6);

       final CollatingIterator<Integer> collatingIterator1 = new CollatingIterator<>(null, l1.iterator(), l2.iterator());
       try {
           collatingIterator1.next();
       } catch (final NullPointerException e) {
           // removed other assertion
       }

       int i = 0;
       final CollatingIterator<Integer> collatingIterator2 = new CollatingIterator<>(null, l1.iterator(), l2.iterator());
       collatingIterator2.setComparator(new ComparableComparator<Integer>());
       for ( ; collatingIterator2.hasNext(); i++ ) {
          final Integer n = collatingIterator2.next();
          // removed other assertion
       }
       assertEquals("wrong size", i, l1.size() + l2.size());
    }

}

