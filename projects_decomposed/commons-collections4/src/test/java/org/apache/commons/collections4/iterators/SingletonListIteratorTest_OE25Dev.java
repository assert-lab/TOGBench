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

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.ResettableListIterator;

/**
 * Tests the SingletonListIterator.
 *
 */
public class SingletonListIteratorTest_OE25Dev<E> extends AbstractListIteratorTest<E> {

    private static final Object testValue = "foo";

    public SingletonListIteratorTest_OE25Dev(final String testName) {
        super(testName);
    }

    /**
     * Returns a SingletonListIterator from which
     * the element has already been removed.
     */
    @Override
    public SingletonListIterator<E> makeEmptyIterator() {
        final SingletonListIterator<E> iter = makeObject();
        iter.next();
        iter.remove();
        iter.reset();
        return iter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SingletonListIterator<E> makeObject() {
        return new SingletonListIterator<>((E) testValue);
    }

    @Override
    public boolean supportsAdd() {
        return false;
    }

    @Override
    public boolean supportsRemove() {
        return true;
    }

    @Override
    public boolean supportsEmptyIterator() {
        return true;
    }

    public void testIterator_1_oe() {
        final ListIterator<E> iter = makeObject();
        assertTrue( "Iterator should have next item", iter.hasNext() );
    }

    public void testIterator_2_oe() {
        final ListIterator<E> iter = makeObject();
        assertTrue( "Iterator should have no previous item", !iter.hasPrevious() );
    }

    public void testIterator_3_oe() {
        final ListIterator<E> iter = makeObject();
        assertEquals( "Iteration next index", 0, iter.nextIndex() );
    }

    public void testIterator_4_oe() {
        final ListIterator<E> iter = makeObject();
        assertEquals( "Iteration previous index", -1, iter.previousIndex() );
    }

    public void testIterator_5_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();
        assertEquals( "Iteration value is correct", testValue, iterValue );
    }

    public void testIterator_6_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();

        assertTrue( "Iterator should have no next item", !iter.hasNext() );
    }

    public void testIterator_7_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();

        assertTrue( "Iterator should have previous item", iter.hasPrevious() );
    }

    public void testIterator_8_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();

        assertEquals( "Iteration next index", 1, iter.nextIndex() );
    }

    public void testIterator_9_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();

        assertEquals( "Iteration previous index", 0, iter.previousIndex() );
    }

    public void testIterator_10_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();
        assertEquals( "Iteration value is correct", testValue, iterValue );
    }

    public void testIterator_11_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();

        assertTrue( "Iterator should have next item", iter.hasNext() );
    }

    public void testIterator_12_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();

        assertTrue( "Iterator should have no previous item", !iter.hasPrevious() );
    }

    public void testIterator_13_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();

        assertEquals( "Iteration next index", 0, iter.nextIndex() );
    }

    public void testIterator_14_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();

        assertEquals( "Iteration previous index", -1, iter.previousIndex() );
    }

    public void testIterator_15_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();


        iterValue = iter.next();
        assertEquals( "Iteration value is correct", testValue, iterValue );
    }

    public void testIterator_16_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();


        iterValue = iter.next();

        assertTrue( "Iterator should have no next item", !iter.hasNext() );
    }

    public void testIterator_17_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();


        iterValue = iter.next();

        assertTrue( "Iterator should have previous item", iter.hasPrevious() );
    }

    public void testIterator_18_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();


        iterValue = iter.next();

        assertEquals( "Iteration next index", 1, iter.nextIndex() );
    }

    public void testIterator_19_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();


        iterValue = iter.next();

        assertEquals( "Iteration previous index", 0, iter.previousIndex() );
    }

    public void testIterator_20_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();


        iterValue = iter.next();


        try {
            iter.next();
        } catch (final Exception e) {
          assertTrue("NoSuchElementException must be thrown",e.getClass().equals(new NoSuchElementException().getClass()));
    }
    }

    public void testIterator_21_oe() {
        final ListIterator<E> iter = makeObject();

        Object iterValue = iter.next();


        iterValue = iter.previous();


        iterValue = iter.next();


        try {
            iter.next();
        } catch (final Exception e) {
        }
        iter.previous();
        try {
            iter.previous();
        } catch (final Exception e) {
          assertTrue("NoSuchElementException must be thrown",e.getClass().equals(new NoSuchElementException().getClass()));
    }
    }

    public void testReset_1_oe() {
        final ResettableListIterator<E> it = makeObject();

        assertEquals(true, it.hasNext());
    }

    public void testReset_2_oe() {
        final ResettableListIterator<E> it = makeObject();

        assertEquals(false, it.hasPrevious());
    }

    public void testReset_3_oe() {
        final ResettableListIterator<E> it = makeObject();

        assertEquals(testValue, it.next());
    }

    public void testReset_6_oe() {
        final ResettableListIterator<E> it = makeObject();


        it.reset();

        assertEquals(true, it.hasNext());
    }

    public void testReset_7_oe() {
        final ResettableListIterator<E> it = makeObject();


        it.reset();

        assertEquals(false, it.hasPrevious());
    }

    public void testReset_8_oe() {
        final ResettableListIterator<E> it = makeObject();


        it.reset();

        assertEquals(testValue, it.next());
    }

    public void testReset_11_oe() {
        final ResettableListIterator<E> it = makeObject();


        it.reset();


        it.reset();
        it.reset();

        assertEquals(true, it.hasNext());
    }

}

