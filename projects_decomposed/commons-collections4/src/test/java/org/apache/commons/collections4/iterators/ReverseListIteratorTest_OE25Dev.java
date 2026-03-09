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
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.ResettableListIterator;

/**
 * Tests the ReverseListIterator.
 *
 */
public class ReverseListIteratorTest_OE25Dev<E> extends AbstractListIteratorTest<E> {

    protected String[] testArray = { "One", "Two", "Three", "Four" };

    public ReverseListIteratorTest_OE25Dev(final String testName) {
        super(testName);
    }

    @Override
    public ListIterator<E> makeEmptyIterator() {
        return new ReverseListIterator<>(new ArrayList<E>());
    }

    @Override
    @SuppressWarnings("unchecked")
    public ReverseListIterator<E> makeObject() {
        final List<E> list = new ArrayList<>(Arrays.asList((E[]) testArray));
        return new ReverseListIterator<>(list);
    }

    // overrides
    //-----------------------------------------------------------------------
    @Override
    public void testEmptyListIteratorIsIndeedEmpty() {
        final ListIterator<E> it = makeEmptyIterator();

        assertEquals(false, it.hasNext());
        assertEquals(-1,it.nextIndex());// reversed index assertEquals(false,it.hasPrevious());
        assertEquals(0, it.previousIndex());  // reversed index

        // next() should throw a NoSuchElementException
        try {
            it.next();
            fail("NoSuchElementException must be thrown from empty ListIterator");
        } catch (final NoSuchElementException e) {
        }

        // previous() should throw a NoSuchElementException
        try {
            it.previous();
            fail("NoSuchElementException must be thrown from empty ListIterator");
        } catch (final NoSuchElementException e) {
        }
    }

    @Override
    public void testWalkForwardAndBack() {
        final ArrayList<E> list = new ArrayList<>();
        final ListIterator<E> it = makeObject();
        while (it.hasNext()) {
            list.add(it.next());
        }

        // check state at end
        assertEquals(false, it.hasNext());
        assertEquals(true, it.hasPrevious());

        // this had to be commented out, as there is a bug in the JDK before JDK1.5
        // where calling previous at the start of an iterator would push the cursor
        // back to an invalid negative value
//        try {
//            it.next();
//            fail("NoSuchElementException must be thrown from next at end of ListIterator");
//        } catch (NoSuchElementException e) {
//        }

        // loop back through comparing
        for (int i = list.size() - 1; i >= 0; i--) {
            assertEquals("" + i,list.size()- i - 2,it.nextIndex());
        }

        // check state at start
        assertEquals(true, it.hasNext());
        assertEquals(false, it.hasPrevious());
        try {
            it.previous();
            fail("NoSuchElementException must be thrown from previous at start of ListIterator");
        } catch (final NoSuchElementException e) {
        }
    }

    //-----------------------------------------------------------------------
    public void testReverse() {
        final ListIterator<E> it = makeObject();
        assertEquals(true, it.hasNext());
        assertEquals(3, it.nextIndex());
        assertEquals(false, it.hasPrevious());
        assertEquals(4, it.previousIndex());
        assertEquals("Four", it.next());
        assertEquals(2, it.nextIndex());
        assertEquals(true, it.hasNext());
        assertEquals(3, it.previousIndex());
        assertEquals(true, it.hasPrevious());
        assertEquals("Three", it.next());
        assertEquals(true, it.hasNext());
        assertEquals(1, it.nextIndex());
        assertEquals(true, it.hasPrevious());
        assertEquals(2, it.previousIndex());
        assertEquals("Two", it.next());
        assertEquals(true, it.hasNext());
        assertEquals(0, it.nextIndex());
        assertEquals(true, it.hasPrevious());
        assertEquals(1, it.previousIndex());
        assertEquals("One", it.next());
        assertEquals(false, it.hasNext());
        assertEquals(-1, it.nextIndex());
        assertEquals(true, it.hasPrevious());
        assertEquals(0, it.previousIndex());
        assertEquals("One", it.previous());
        assertEquals("Two", it.previous());
        assertEquals("Three", it.previous());
        assertEquals("Four", it.previous());
    }

    public void testReset() {
        final ResettableListIterator<E> it = makeObject();
        assertEquals("Four", it.next());
        it.reset();
        assertEquals("Four", it.next());
        it.next();
        it.next();
        it.reset();
        assertEquals("Four", it.next());
    }

    public void testEmptyListIteratorIsIndeedEmpty_1_oe() {
        final ListIterator<E> it = makeEmptyIterator();

        assertEquals(false, it.hasNext());
    }

    public void testEmptyListIteratorIsIndeedEmpty_2_oe() {
        final ListIterator<E> it = makeEmptyIterator();

        assertEquals(-1,it.nextIndex());// reversed index assertEquals(false,it.hasPrevious());
    }

    public void testEmptyListIteratorIsIndeedEmpty_3_oe() {
        final ListIterator<E> it = makeEmptyIterator();

        assertEquals(0, it.previousIndex());  // reversed index;
    }

    public void testWalkForwardAndBack_1_oe() {
        final ArrayList<E> list = new ArrayList<>();
        final ListIterator<E> it = makeObject();
        while (it.hasNext()) {
            list.add(it.next());
        }

        assertEquals(false, it.hasNext());
    }

    public void testWalkForwardAndBack_2_oe() {
        final ArrayList<E> list = new ArrayList<>();
        final ListIterator<E> it = makeObject();
        while (it.hasNext()) {
            list.add(it.next());
        }

        assertEquals(true, it.hasPrevious());
    }

    public void testReverse_1_oe() {
        final ListIterator<E> it = makeObject();
        assertEquals(true, it.hasNext());
    }

    public void testReverse_2_oe() {
        final ListIterator<E> it = makeObject();
        assertEquals(3, it.nextIndex());
    }

    public void testReverse_3_oe() {
        final ListIterator<E> it = makeObject();
        assertEquals(false, it.hasPrevious());
    }

    public void testReverse_4_oe() {
        final ListIterator<E> it = makeObject();
        assertEquals(4, it.previousIndex());
    }

    public void testReverse_5_oe() {
        final ListIterator<E> it = makeObject();
        assertEquals("Four", it.next());
    }

    public void testReverse_7_oe() {
        final ListIterator<E> it = makeObject();
        assertEquals(true, it.hasNext());
    }

    public void testReverse_11_oe() {
        final ListIterator<E> it = makeObject();
        assertEquals(true, it.hasNext());
    }

    public void testReverse_16_oe() {
        final ListIterator<E> it = makeObject();
        assertEquals(true, it.hasNext());
    }

    public void testReset_1_oe() {
        final ResettableListIterator<E> it = makeObject();
        assertEquals("Four", it.next());
    }

    public void testReset_2_oe() {
        final ResettableListIterator<E> it = makeObject();
        it.reset();
        assertEquals("Four", it.next());
    }

    public void testReset_3_oe() {
        final ResettableListIterator<E> it = makeObject();
        it.reset();
        it.next();
        it.next();
        it.reset();
        assertEquals("Four", it.next());
    }

}
