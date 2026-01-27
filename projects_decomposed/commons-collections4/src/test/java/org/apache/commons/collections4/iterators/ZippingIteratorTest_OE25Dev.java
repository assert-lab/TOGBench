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

import org.apache.commons.collections4.IteratorUtils;

/**
 * Unit test suite for {@link ZippingIterator}.
 *
 */
@SuppressWarnings("boxing")
public class ZippingIteratorTest_OE25Dev extends AbstractIteratorTest<Integer> {

    //------------------------------------------------------------ Conventional

    public ZippingIteratorTest_OE25Dev(final String testName) {
        super(testName);
    }

    //--------------------------------------------------------------- Lifecycle

    private ArrayList<Integer> evens = null;
    private ArrayList<Integer> odds = null;
    private ArrayList<Integer> fib = null;

    @Override
    public void setUp() throws Exception {
        super.setUp();
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
    @SuppressWarnings("unchecked")
    public ZippingIterator<Integer> makeEmptyIterator() {
        return new ZippingIterator<>(IteratorUtils.<Integer>emptyIterator());
    }

    @Override
    public ZippingIterator<Integer> makeObject() {
        return new ZippingIterator<>(evens.iterator(), odds.iterator(), fib.iterator());
    }

    //------------------------------------------------------------------- Tests

    public void testIterateEven_1_oe() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        for (int i = 0; i < evens.size(); i++) {
            assertTrue(iter.hasNext());
    }
    }

    public void testIterateEven_2_oe() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        for (int i = 0; i < evens.size(); i++) {
            // removed other assertion
            assertEquals(evens.get(i), iter.next());
    }
    }

    public void testIterateEvenOdd_1_oe() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator());
        for (int i = 0; i < 20; i++) {
            assertTrue(iter.hasNext());
    }
    }

    public void testIterateEvenOdd_2_oe() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator());
        for (int i = 0; i < 20; i++) {
            // removed other assertion
            assertEquals(Integer.valueOf(i), iter.next());
    }
    }

    public void testIterateOddEven_1_oe() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(odds.iterator(), evens.iterator());
        for (int i = 0, j = 0; i < 20; i++) {
            assertTrue(iter.hasNext());
    }
    }

    public void testIterateOddEven_4_oe() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(odds.iterator(), evens.iterator());
        for (int i = 0, j = 0; i < 20; i++) {
            // removed other assertion
            final int val = iter.next();
            if (i % 2 == 0) {
                // removed other assertion
            } else {
                // removed other assertion
                j++;
            }
        }
        assertTrue(!iter.hasNext());
    }

    public void testIterateEvenEven_1_oe() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), evens.iterator());
        for (int i = 0; i < evens.size(); i++) {
            assertTrue(iter.hasNext());
    }
    }

    public void testIterateEvenEven_3_oe() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), evens.iterator());
        for (int i = 0; i < evens.size(); i++) {
            // removed other assertion
            // removed other assertion
            assertTrue(iter.hasNext());
    }
    }

    public void testIterateFibEvenOdd_1_oe() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(fib.iterator(), evens.iterator(), odds.iterator());

        assertEquals(Integer.valueOf(1),iter.next());  // fib    1;
    }

    public void testIterateFibEvenOdd_3_oe() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(fib.iterator(), evens.iterator(), odds.iterator());

        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1),iter.next());  // odd    1;
    }

    public void testIterateFibEvenOdd_4_oe() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(fib.iterator(), evens.iterator(), odds.iterator());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1),iter.next());  // fib    1;
    }

    public void testRemoveFromSingle_1_oe() {
        @SuppressWarnings("unchecked")
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator());
        int expectedSize = evens.size();
        while (iter.hasNext()) {
            final Object o = iter.next();
            final Integer val = (Integer) o;
            if (val.intValue() % 4 == 0) {
                expectedSize--;
                iter.remove();
            }
        }
        assertEquals(expectedSize, evens.size());
    }

    public void testRemoveFromDouble_1_oe() {
        final ZippingIterator<Integer> iter = new ZippingIterator<>(evens.iterator(), odds.iterator());
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

}

