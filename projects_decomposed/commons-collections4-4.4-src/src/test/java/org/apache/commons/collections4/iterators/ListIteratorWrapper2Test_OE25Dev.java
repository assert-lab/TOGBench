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

    public void testRemove_7_oe() {
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
        // removed other assertion
        assertEquals(0, iter.previousIndex());
    }

    public void testRemove_8_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(1, iter.nextIndex());
    }

    public void testRemove_9_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        assertEquals(--sz, list1.size());
    }

    public void testRemove_10_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
        assertEquals(-1, iter.previousIndex());
    }

    public void testRemove_11_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
        // removed other assertion
        assertEquals(0, iter.nextIndex());
    }

    public void testRemove_13_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

    public void testRemove_14_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

    public void testRemove_15_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        assertEquals(list1.get(0), iter.next());
    }

    public void testRemove_16_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        assertEquals(0, iter.previousIndex());
    }

    public void testRemove_17_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        assertEquals(1, iter.nextIndex());
    }

    public void testRemove_18_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(list1.get(1), iter.next());
    }

    public void testRemove_19_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(1, iter.previousIndex());
    }

    public void testRemove_20_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(2, iter.nextIndex());
    }

    public void testRemove_21_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        assertEquals(list1.get(1), iter.previous());
    }

    public void testRemove_22_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        assertEquals(0, iter.previousIndex());
    }

    public void testRemove_23_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        // removed other assertion
        assertEquals(1, iter.nextIndex());
    }

    public void testRemove_24_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should support remove() after calling previous() once from tip because we haven't changed the underlying iterator's position:
        iter.remove();
        assertEquals(--sz, list1.size());
    }

    public void testRemove_25_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should support remove() after calling previous() once from tip because we haven't changed the underlying iterator's position:
        iter.remove();
        // removed other assertion
        assertEquals(0, iter.previousIndex());
    }

    public void testRemove_26_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should support remove() after calling previous() once from tip because we haven't changed the underlying iterator's position:
        iter.remove();
        // removed other assertion
        // removed other assertion
        assertEquals(1, iter.nextIndex());
    }

    public void testRemove_27_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should support remove() after calling previous() once from tip because we haven't changed the underlying iterator's position:
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //this would dig into cache on a plain Iterator, but forwards directly to wrapped ListIterator:
        assertEquals(list1.get(0), iter.previous());
    }

    public void testRemove_28_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should support remove() after calling previous() once from tip because we haven't changed the underlying iterator's position:
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //this would dig into cache on a plain Iterator, but forwards directly to wrapped ListIterator:
        // removed other assertion
        assertEquals(-1, iter.previousIndex());
    }

    public void testRemove_29_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should support remove() after calling previous() once from tip because we haven't changed the underlying iterator's position:
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //this would dig into cache on a plain Iterator, but forwards directly to wrapped ListIterator:
        // removed other assertion
        // removed other assertion
        assertEquals(0, iter.nextIndex());
    }

    public void testRemove_30_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should support remove() after calling previous() once from tip because we haven't changed the underlying iterator's position:
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //this would dig into cache on a plain Iterator, but forwards directly to wrapped ListIterator:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //here's the proof; remove() still works:
        iter.remove();
        assertEquals(--sz, list1.size());
    }

    public void testRemove_31_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should support remove() after calling previous() once from tip because we haven't changed the underlying iterator's position:
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //this would dig into cache on a plain Iterator, but forwards directly to wrapped ListIterator:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //here's the proof; remove() still works:
        iter.remove();
        // removed other assertion
        assertEquals(-1, iter.previousIndex());
    }

    public void testRemove_32_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //verify remove():
        iter.remove();
        // removed other assertion
        //like we never started iterating:
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

        //two consecutive next() calls:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //call previous():
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should support remove() after calling previous() once from tip because we haven't changed the underlying iterator's position:
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //this would dig into cache on a plain Iterator, but forwards directly to wrapped ListIterator:
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //here's the proof; remove() still works:
        iter.remove();
        // removed other assertion
        // removed other assertion
        assertEquals(0, iter.nextIndex());
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

    public void testReset_3_oe() {
        final ResettableListIterator<E> iter = makeObject();
        final E first = iter.next();
        final E second = iter.next();

        iter.reset();

        // after reset, there shouldn't be any previous elements
        // removed other assertion

        // after reset, the results should be the same as before
        // removed other assertion
        assertEquals("Second elment should be the same", second, iter.next());
    }

    public void testReset_4_oe() {
        final ResettableListIterator<E> iter = makeObject();
        final E first = iter.next();
        final E second = iter.next();

        iter.reset();

        // after reset, there shouldn't be any previous elements
        // removed other assertion

        // after reset, the results should be the same as before
        // removed other assertion
        // removed other assertion

        // after passing the point, where we resetted, continuation should work as expected
        for (int i = 2; i < testArray.length; i++) {
            final Object testValue = testArray[i];
            final E iterValue = iter.next();

            assertEquals("Iteration value is correct", testValue, iterValue);
    }
    }

}
