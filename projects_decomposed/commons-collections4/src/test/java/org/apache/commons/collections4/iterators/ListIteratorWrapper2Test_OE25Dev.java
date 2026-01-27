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
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.ResettableListIterator;

/**
 * Tests the ListIteratorWrapper to insure that it behaves as expected when wrapping a ListIterator.
 *
 */
public class ListIteratorWrapper2Test_OE25Dev<E> extends AbstractIteratorTest<E> {

    protected String[] testArray = {
        "One", "Two", "Three", "Four", "Five", "Six"
    };

    protected List<E> list1 = null;

    public ListIteratorWrapper2Test_OE25Dev(final String testName) {
        super(testName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setUp() {
        list1 = new ArrayList<>();
        list1.add((E) "One");
        list1.add((E) "Two");
        list1.add((E) "Three");
        list1.add((E) "Four");
        list1.add((E) "Five");
        list1.add((E) "Six");
    }

    @Override
    public ResettableListIterator<E> makeEmptyIterator() {
        final ArrayList<E> list = new ArrayList<>();
        return new ListIteratorWrapper<>(list.listIterator());
    }

    @Override
    public ResettableListIterator<E> makeObject() {
        return new ListIteratorWrapper<>(list1.listIterator());
    }

    public void testIterator_1_oe() {
        final ListIterator<E> iter = makeObject();
        for (final String testValue : testArray) {
            final Object iterValue = iter.next();

            assertEquals("Iteration value is correct", testValue, iterValue);
    }
    }

    public void testIterator_2_oe() {
        final ListIterator<E> iter = makeObject();
        for (final String testValue : testArray) {
            final Object iterValue = iter.next();

            // removed other assertion
        }

        assertTrue("Iterator should now be empty", !iter.hasNext());
    }

    public void testIterator_3_oe() {
        final ListIterator<E> iter = makeObject();
        for (final String testValue : testArray) {
            final Object iterValue = iter.next();

            // removed other assertion
        }

        // removed other assertion

        try {
            iter.next();
        } catch (final Exception e) {
            assertTrue("NoSuchElementException must be thrown", e.getClass().equals(new NoSuchElementException().getClass()));
    }
    }

    public void testIterator_4_oe() {
        final ListIterator<E> iter = makeObject();
        for (final String testValue : testArray) {
            final Object iterValue = iter.next();

            // removed other assertion
        }

        // removed other assertion

        try {
            iter.next();
        } catch (final Exception e) {
            // removed other assertion
        }

        // now, read it backwards
        for (int i = testArray.length - 1; i > -1; --i) {
            final Object testValue = testArray[i];
            final E iterValue = iter.previous();

            assertEquals( "Iteration value is correct", testValue, iterValue );
    }
    }

    public void testIterator_5_oe() {
        final ListIterator<E> iter = makeObject();
        for (final String testValue : testArray) {
            final Object iterValue = iter.next();

            // removed other assertion
        }

        // removed other assertion

        try {
            iter.next();
        } catch (final Exception e) {
            // removed other assertion
        }

        // now, read it backwards
        for (int i = testArray.length - 1; i > -1; --i) {
            final Object testValue = testArray[i];
            final E iterValue = iter.previous();

            // removed other assertion
        }

        try {
            iter.previous();
        } catch (final Exception e) {
            assertTrue("NoSuchElementException must be thrown", e.getClass().equals(new NoSuchElementException().getClass()));
    }
    }

    public void testIterator_6_oe() {
        final ListIterator<E> iter = makeObject();
        for (final String testValue : testArray) {
            final Object iterValue = iter.next();

            // removed other assertion
        }

        // removed other assertion

        try {
            iter.next();
        } catch (final Exception e) {
            // removed other assertion
        }

        // now, read it backwards
        for (int i = testArray.length - 1; i > -1; --i) {
            final Object testValue = testArray[i];
            final E iterValue = iter.previous();

            // removed other assertion
        }

        try {
            iter.previous();
        } catch (final Exception e) {
            // removed other assertion
        }

        // now, read it forwards again
        for (final String testValue : testArray) {
            final Object iterValue = iter.next();

            assertEquals("Iteration value is correct", testValue, iterValue);
    }
    }

    public void testRemove_1_oe() {
        final ListIterator<E> iter = makeObject();

        //initial state:
        assertEquals(-1, iter.previousIndex());
    }

    public void testRemove_2_oe() {
        final ListIterator<E> iter = makeObject();

        //initial state:
        // removed other assertion
        assertEquals(0, iter.nextIndex());
    }

    public void testRemove_4_oe() {
        final ListIterator<E> iter = makeObject();

        //initial state:
        // removed other assertion
        // removed other assertion

        try {
            iter.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
        }

        //no change from invalid op:
        assertEquals(-1, iter.previousIndex());
    }

    public void testRemove_5_oe() {
        final ListIterator<E> iter = makeObject();

        //initial state:
        // removed other assertion
        // removed other assertion

        try {
            iter.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
        }

        //no change from invalid op:
        // removed other assertion
        assertEquals(0, iter.nextIndex());
    }

    public void testRemove_6_oe() {
        final ListIterator<E> iter = makeObject();

        //initial state:
        // removed other assertion
        // removed other assertion

        try {
            iter.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
        }

        //no change from invalid op:
        // removed other assertion
        // removed other assertion

        //establish size:
        int sz = list1.size();

        //verify initial next() call:
        assertEquals(list1.get(0), iter.next());
    }

    public void testReset_1_oe() {
        final ResettableListIterator<E> iter = makeObject();
        final E first = iter.next();
        final E second = iter.next();

        iter.reset();

        // after reset, there shouldn't be any previous elements
        assertFalse("No previous elements after reset()", iter.hasPrevious());
    }

    public void testReset_2_oe() {
        final ResettableListIterator<E> iter = makeObject();
        final E first = iter.next();
        final E second = iter.next();

        iter.reset();

        // after reset, there shouldn't be any previous elements
        // removed other assertion

        // after reset, the results should be the same as before
        assertEquals("First element should be the same", first, iter.next());
    }

}
