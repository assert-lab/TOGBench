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

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.ResettableIterator;

/**
 * Tests the SingletonIterator to ensure that the next() method will actually
 * perform the iteration rather than the hasNext() method.
 *
 */
public class SingletonIteratorTest_OE25Dev<E> extends AbstractIteratorTest<E> {

    private static final Object testValue = "foo";

    public SingletonIteratorTest_OE25Dev(final String testName) {
        super(testName);
    }

    /**
     * Returns a SingletonIterator from which
     * the element has already been removed.
     */
    @Override
    public SingletonIterator<E> makeEmptyIterator() {
        final SingletonIterator<E> iter = makeObject();
        iter.next();
        iter.remove();
        iter.reset();
        return iter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SingletonIterator<E> makeObject() {
        return new SingletonIterator<>((E) testValue);
    }

    @Override
    public boolean supportsRemove() {
        return true;
    }

    @Override
    public boolean supportsEmptyIterator() {
        return true;
    }

    public void testIterator() {
        final Iterator<E> iter = makeObject();
        assertTrue("Iterator has a first item", iter.hasNext());

        final E iterValue = iter.next();
        assertEquals("Iteration value is correct", testValue, iterValue);

        assertTrue("Iterator should now be empty", !iter.hasNext());

        try {
            iter.next();
        } catch (final Exception e) {
            assertTrue("NoSuchElementException must be thrown",e.getClass().equals(new NoSuchElementException().getClass()));
        }
    }

    @SuppressWarnings("unchecked")
    public void testSingletonIteratorRemove() {
        final ResettableIterator<E> iter = new SingletonIterator<>((E) "xyzzy");
        assertTrue(iter.hasNext());
        assertEquals("xyzzy",iter.next());
        iter.remove();
        iter.reset();
        assertTrue(! iter.hasNext());
    }

    public void testReset() {
        final ResettableIterator<E> it = makeObject();

        assertEquals(true, it.hasNext());
        assertEquals(testValue, it.next());
        assertEquals(false, it.hasNext());

        it.reset();

        assertEquals(true, it.hasNext());
        assertEquals(testValue, it.next());
        assertEquals(false, it.hasNext());

        it.reset();
        it.reset();

        assertEquals(true, it.hasNext());
    }

    public void testIterator_1_oe() {
        final Iterator<E> iter = makeObject();
        assertTrue("Iterator has a first item", iter.hasNext());
    }

    public void testIterator_2_oe() {
        final Iterator<E> iter = makeObject();

        final E iterValue = iter.next();
        assertEquals("Iteration value is correct", testValue, iterValue);
    }

    public void testIterator_3_oe() {
        final Iterator<E> iter = makeObject();

        final E iterValue = iter.next();

        assertTrue("Iterator should now be empty", !iter.hasNext());
    }

    public void testIterator_4_oe() {
        final Iterator<E> iter = makeObject();

        final E iterValue = iter.next();


        try {
            iter.next();
        } catch (final Exception e) {
            assertTrue("NoSuchElementException must be thrown",e.getClass().equals(new NoSuchElementException().getClass()));
    }
    }

    public void testSingletonIteratorRemove_1_oe() {
        final ResettableIterator<E> iter = new SingletonIterator<>((E) "xyzzy");
        assertTrue(iter.hasNext());
    }

    public void testSingletonIteratorRemove_2_oe() {
        final ResettableIterator<E> iter = new SingletonIterator<>((E) "xyzzy");
        assertEquals("xyzzy",iter.next());
    }

    public void testReset_1_oe() {
        final ResettableIterator<E> it = makeObject();

        assertEquals(true, it.hasNext());
    }

    public void testReset_2_oe() {
        final ResettableIterator<E> it = makeObject();

        assertEquals(testValue, it.next());
    }

    public void testReset_4_oe() {
        final ResettableIterator<E> it = makeObject();


        it.reset();

        assertEquals(true, it.hasNext());
    }

    public void testReset_5_oe() {
        final ResettableIterator<E> it = makeObject();


        it.reset();

        assertEquals(testValue, it.next());
    }

    public void testReset_7_oe() {
        final ResettableIterator<E> it = makeObject();


        it.reset();


        it.reset();
        it.reset();

        assertEquals(true, it.hasNext());
    }

}
