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

    //-----------------------------------------------------------------------

public void testEmptyListIteratorIsIndeedEmpty_1_oe() {
        final ListIterator<E> it = makeEmptyIterator();

        assertEquals(false, it.hasNext());
    }

public void testEmptyListIteratorIsIndeedEmpty_2_oe() {
        final ListIterator<E> it = makeEmptyIterator();

        // removed other assertion
        assertEquals(-1,it.nextIndex());// reversed index assertEquals(false,it.hasPrevious());
    }

public void testEmptyListIteratorIsIndeedEmpty_3_oe() {
        final ListIterator<E> it = makeEmptyIterator();

        // removed other assertion
        // removed other assertion
        assertEquals(0, it.previousIndex());  // reversed index;
    }

public void testWalkForwardAndBack_1_oe() {
        final ArrayList<E> list = new ArrayList<>();
        final ListIterator<E> it = makeObject();
        while (it.hasNext()) {
            list.add(it.next());
        }

        // check state at end
        assertEquals(false, it.hasNext());
    }

public void testWalkForwardAndBack_2_oe() {
        final ArrayList<E> list = new ArrayList<>();
        final ListIterator<E> it = makeObject();
        while (it.hasNext()) {
            list.add(it.next());
        }

        // check state at end
        // removed other assertion
        assertEquals(true, it.hasPrevious());
    }

public void testWalkForwardAndBack_3_oe() {
        final ArrayList<E> list = new ArrayList<>();
        final ListIterator<E> it = makeObject();
        while (it.hasNext()) {
            list.add(it.next());
        }

        // check state at end
        // removed other assertion
        // removed other assertion

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
    }

public void testWalkForwardAndBack_4_oe() {
        final ArrayList<E> list = new ArrayList<>();
        final ListIterator<E> it = makeObject();
        while (it.hasNext()) {
            list.add(it.next());
        }

        // check state at end
        // removed other assertion
        // removed other assertion

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
            // removed other assertion
        }

        // check state at start
        assertEquals(true, it.hasNext());
    }

public void testWalkForwardAndBack_5_oe() {
        final ArrayList<E> list = new ArrayList<>();
        final ListIterator<E> it = makeObject();
        while (it.hasNext()) {
            list.add(it.next());
        }

        // check state at end
        // removed other assertion
        // removed other assertion

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
            // removed other assertion
        }

        // check state at start
        // removed other assertion
        assertEquals(false, it.hasPrevious());
    }

public void testReverse_1_oe() {
        final ListIterator<E> it = makeObject();
        assertEquals(true, it.hasNext());
    }

public void testReverse_2_oe() {
        final ListIterator<E> it = makeObject();
        // removed other assertion
        assertEquals(3, it.nextIndex());
    }

public void testReverse_3_oe() {
        final ListIterator<E> it = makeObject();
        // removed other assertion
        // removed other assertion
        assertEquals(false, it.hasPrevious());
    }

public void testReverse_4_oe() {
        final ListIterator<E> it = makeObject();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, it.previousIndex());
    }

public void testReverse_5_oe() {
        final ListIterator<E> it = makeObject();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Four", it.next());
    }

public void testReverse_6_oe() {
        final ListIterator<E> it = makeObject();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, it.nextIndex());
    }

public void testReverse_7_oe() {
        final ListIterator<E> it = makeObject();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, it.hasNext());
    }

public void testReverse_8_oe() {
        final ListIterator<E> it = makeObject();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, it.previousIndex());
    }

public void testReverse_9_oe() {
        final ListIterator<E> it = makeObject();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, it.hasPrevious());
    }

public void testReverse_10_oe() {
        final ListIterator<E> it = makeObject();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Three", it.next());
    }

public void testReverse_11_oe() {
        final ListIterator<E> it = makeObject();
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
        assertEquals(true, it.hasNext());
    }

public void testReverse_12_oe() {
        final ListIterator<E> it = makeObject();
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
        assertEquals(1, it.nextIndex());
    }

public void testReverse_13_oe() {
        final ListIterator<E> it = makeObject();
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
        assertEquals(true, it.hasPrevious());
    }

public void testReverse_14_oe() {
        final ListIterator<E> it = makeObject();
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
        assertEquals(2, it.previousIndex());
    }

public void testReverse_15_oe() {
        final ListIterator<E> it = makeObject();
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
        assertEquals("Two", it.next());
    }

public void testReverse_16_oe() {
        final ListIterator<E> it = makeObject();
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
        // removed other assertion
        assertEquals(true, it.hasNext());
    }

public void testReverse_17_oe() {
        final ListIterator<E> it = makeObject();
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
        // removed other assertion
        // removed other assertion
        assertEquals(0, it.nextIndex());
    }

public void testReverse_18_oe() {
        final ListIterator<E> it = makeObject();
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, it.hasPrevious());
    }

public void testReverse_19_oe() {
        final ListIterator<E> it = makeObject();
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, it.previousIndex());
    }

public void testReverse_20_oe() {
        final ListIterator<E> it = makeObject();
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("One", it.next());
    }

public void testReverse_21_oe() {
        final ListIterator<E> it = makeObject();
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, it.hasNext());
    }

public void testReverse_22_oe() {
        final ListIterator<E> it = makeObject();
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, it.nextIndex());
    }

public void testReverse_23_oe() {
        final ListIterator<E> it = makeObject();
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, it.hasPrevious());
    }

public void testReverse_24_oe() {
        final ListIterator<E> it = makeObject();
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, it.previousIndex());
    }

public void testReverse_25_oe() {
        final ListIterator<E> it = makeObject();
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
        assertEquals("One", it.previous());
    }

public void testReverse_26_oe() {
        final ListIterator<E> it = makeObject();
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
        assertEquals("Two", it.previous());
    }

public void testReverse_27_oe() {
        final ListIterator<E> it = makeObject();
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
        assertEquals("Three", it.previous());
    }

public void testReverse_28_oe() {
        final ListIterator<E> it = makeObject();
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
        assertEquals("Four", it.previous());
    }

public void testReset_1_oe() {
        final ResettableListIterator<E> it = makeObject();
        assertEquals("Four", it.next());
    }

public void testReset_2_oe() {
        final ResettableListIterator<E> it = makeObject();
        // removed other assertion
        it.reset();
        assertEquals("Four", it.next());
    }

public void testReset_3_oe() {
        final ResettableListIterator<E> it = makeObject();
        // removed other assertion
        it.reset();
        // removed other assertion
        it.next();
        it.next();
        it.reset();
        assertEquals("Four", it.next());
    }

}
