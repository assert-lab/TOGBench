/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * A unit test to test the basic functions of {@link BoundedIterator}.
 *
 */
public class BoundedIteratorTest_OE25Dev<E> extends AbstractIteratorTest<E> {

    /** Test array of size 7 */
    private final String[] testArray = {
        "a", "b", "c", "d", "e", "f", "g"
    };

    private List<E> testList;

    public BoundedIteratorTest_OE25Dev(final String testName) {
        super(testName);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setUp()
        throws Exception {
        super.setUp();
        testList = Arrays.asList((E[]) testArray);
    }

    @Override
    public Iterator<E> makeEmptyIterator() {
        return new BoundedIterator<>(Collections.<E>emptyList().iterator(), 0, 10);
    }

    @Override
    public Iterator<E> makeObject() {
        return new BoundedIterator<>(new ArrayList<>(testList).iterator(), 1, testList.size() - 1);
    }

    // ---------------- Tests ---------------------

    /**
     * Test a decorated iterator bounded such that the first element returned is
     * at an index greater its first element, and the last element returned is
     * at an index less than its last element.
     */

    /**
     * Test a decorated iterator bounded such that the <code>offset</code> is
     * zero and the <code>max</code> is its size, in that the BoundedIterator
     * should return all the same elements as its decorated iterator.
     */

    /**
     * Test a decorated iterator bounded to a <code>max</code> of 0. The
     * BoundedIterator should behave as if there are no more elements to return,
     * since it is technically an empty iterator.
     */

    /**
     * Test the case if a negative <code>offset</code> is passed to the
     * constructor. {@link IllegalArgumentException} is expected.
     */
    @Test
    public void testNegativeOffset() {
        try {
            new BoundedIterator<>(testList.iterator(), -1, 4);
            fail("Expected IllegalArgumentException.");
        } catch (final IllegalArgumentException iae) { /* Success case */
        }
    }

    /**
     * Test the case if a negative <code>max</code> is passed to the
     * constructor. {@link IllegalArgumentException} is expected.
     */
    @Test
    public void testNegativeMax() {
        try {
            new BoundedIterator<>(testList.iterator(), 3, -1);
            fail("Expected IllegalArgumentException.");
        } catch (final IllegalArgumentException iae) { /* Success case */
        }
    }

    /**
     * Test the case if the <code>offset</code> passed to the constructor is
     * greater than the decorated iterator's size. The BoundedIterator should
     * behave as if there are no more elements to return.
     */

    /**
     * Test the case if the <code>max</code> passed to the constructor is
     * greater than the size of the decorated iterator. The last element
     * returned should be the same as the last element of the decorated
     * iterator.
     */

    /**
     * Test the <code>remove()</code> method being called without
     * <code>next()</code> being called first.
     */
    @Test
    public void testRemoveWithoutCallingNext() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        try {
            iter.remove();
            fail("Expected IllegalStateException.");
        } catch (final IllegalStateException ise) { /* Success case */
        }
    }

    /**
     * Test the <code>remove()</code> method being called twice without calling
     * <code>next()</code> in between.
     */

    /**
     * Test removing the first element. Verify that the element is removed from
     * the underlying collection.
     */

    /**
     * Test removing an element in the middle of the iterator. Verify that the
     * element is removed from the underlying collection.
     */

    /**
     * Test removing the last element. Verify that the element is removed from
     * the underlying collection.
     */

    /**
     * Test the case if the decorated iterator does not support the
     * <code>remove()</code> method and throws an {@link UnsupportedOperationException}.
     */

    @Test
    public void testBounded_1_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 2, 4);

        assertTrue(iter.hasNext());
    }

    @Test
    public void testBounded_2_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 2, 4);

        // removed other assertion
        assertEquals("c", iter.next());
    }

    @Test
    public void testBounded_3_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 2, 4);

        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testBounded_4_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 2, 4);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("d", iter.next());
    }

    @Test
    public void testBounded_5_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 2, 4);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testBounded_6_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 2, 4);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("e", iter.next());
    }

    @Test
    public void testBounded_7_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 2, 4);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testBounded_8_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 2, 4);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f", iter.next());
    }

    @Test
    public void testBounded_9_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 2, 4);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(iter.hasNext());
    }

    @Test
    public void testSameAsDecorated_1_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

        assertTrue(iter.hasNext());
    }

    @Test
    public void testSameAsDecorated_2_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

        // removed other assertion
        assertEquals("a", iter.next());
    }

    @Test
    public void testSameAsDecorated_3_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testSameAsDecorated_4_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("b", iter.next());
    }

    @Test
    public void testSameAsDecorated_5_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testSameAsDecorated_6_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", iter.next());
    }

    @Test
    public void testSameAsDecorated_7_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testSameAsDecorated_8_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("d", iter.next());
    }

    @Test
    public void testSameAsDecorated_9_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testSameAsDecorated_10_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("e", iter.next());
    }

    @Test
    public void testSameAsDecorated_11_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

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
        assertTrue(iter.hasNext());
    }

    @Test
    public void testSameAsDecorated_12_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

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
        assertEquals("f", iter.next());
    }

    @Test
    public void testSameAsDecorated_13_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

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
        assertTrue(iter.hasNext());
    }

    @Test
    public void testSameAsDecorated_14_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

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
        assertEquals("g", iter.next());
    }

    @Test
    public void testSameAsDecorated_15_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 0,
                                                  testList.size());

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

        assertFalse(iter.hasNext());
    }

    @Test
    public void testEmptyBounded_1_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 3, 0);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testOffsetGreaterThanSize_1_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 10, 4);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testMaxGreaterThanSize_1_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        assertTrue(iter.hasNext());
    }

    @Test
    public void testMaxGreaterThanSize_2_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // removed other assertion
        assertEquals("b", iter.next());
    }

    @Test
    public void testMaxGreaterThanSize_3_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testMaxGreaterThanSize_4_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", iter.next());
    }

    @Test
    public void testMaxGreaterThanSize_5_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testMaxGreaterThanSize_6_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("d", iter.next());
    }

    @Test
    public void testMaxGreaterThanSize_7_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testMaxGreaterThanSize_8_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("e", iter.next());
    }

    @Test
    public void testMaxGreaterThanSize_9_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testMaxGreaterThanSize_10_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f", iter.next());
    }

    @Test
    public void testMaxGreaterThanSize_11_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

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
        assertTrue(iter.hasNext());
    }

    @Test
    public void testMaxGreaterThanSize_12_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

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
        assertEquals("g", iter.next());
    }

    @Test
    public void testMaxGreaterThanSize_13_oe() {
        final Iterator<E> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

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

        assertFalse(iter.hasNext());
    }

    @Test
    public void testRemoveCalledTwice_1_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveCalledTwice_2_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        assertEquals("b", iter.next());
    }

    @Test
    public void testRemoveFirst_1_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveFirst_2_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        assertEquals("b", iter.next());
    }

    @Test
    public void testRemoveFirst_3_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion

        iter.remove();
        assertFalse(testListCopy.contains("b"));
    }

    @Test
    public void testRemoveFirst_4_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveFirst_5_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        assertEquals("c", iter.next());
    }

    @Test
    public void testRemoveFirst_6_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveFirst_7_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("d", iter.next());
    }

    @Test
    public void testRemoveFirst_8_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveFirst_9_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("e", iter.next());
    }

    @Test
    public void testRemoveFirst_10_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveFirst_11_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f", iter.next());
    }

    @Test
    public void testRemoveFirst_12_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(iter.hasNext());
    }

    @Test
    public void testRemoveMiddle_1_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveMiddle_2_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        assertEquals("b", iter.next());
    }

    @Test
    public void testRemoveMiddle_3_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveMiddle_4_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", iter.next());
    }

    @Test
    public void testRemoveMiddle_5_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveMiddle_6_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("d", iter.next());
    }

    @Test
    public void testRemoveMiddle_7_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        iter.remove();
        assertFalse(testListCopy.contains("d"));
    }

    @Test
    public void testRemoveMiddle_8_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveMiddle_9_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        assertEquals("e", iter.next());
    }

    @Test
    public void testRemoveMiddle_10_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveMiddle_11_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f", iter.next());
    }

    @Test
    public void testRemoveMiddle_12_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        iter.remove();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(iter.hasNext());
    }

    @Test
    public void testRemoveLast_1_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveLast_2_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        assertEquals("b", iter.next());
    }

    @Test
    public void testRemoveLast_3_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveLast_4_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", iter.next());
    }

    @Test
    public void testRemoveLast_5_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveLast_6_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("d", iter.next());
    }

    @Test
    public void testRemoveLast_7_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveLast_8_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("e", iter.next());
    }

    @Test
    public void testRemoveLast_9_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveLast_10_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f", iter.next());
    }

    @Test
    public void testRemoveLast_11_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

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

        assertFalse(iter.hasNext());
    }

    @Test
    public void testRemoveLast_13_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

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
        try {
            iter.next();
            // removed other assertion
        } catch (final NoSuchElementException nsee) { /* Success case */
        }

        iter.remove();
        assertFalse(testListCopy.contains("f"));
    }

    @Test
    public void testRemoveLast_14_oe() {
        final List<E> testListCopy = new ArrayList<>(testList);
        final Iterator<E> iter = new BoundedIterator<>(testListCopy.iterator(), 1, 5);

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
        try {
            iter.next();
            // removed other assertion
        } catch (final NoSuchElementException nsee) { /* Success case */
        }

        iter.remove();
        // removed other assertion

        assertFalse(iter.hasNext());
    }

    @Test
    public void testRemoveUnsupported_1_oe() {
        final Iterator<E> mockIterator = new AbstractIteratorDecorator<E>(testList.iterator()) {
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        final Iterator<E> iter = new BoundedIterator<>(mockIterator, 1, 5);
        assertTrue(iter.hasNext());
    }

    @Test
    public void testRemoveUnsupported_2_oe() {
        final Iterator<E> mockIterator = new AbstractIteratorDecorator<E>(testList.iterator()) {
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        final Iterator<E> iter = new BoundedIterator<>(mockIterator, 1, 5);
        // removed other assertion
        assertEquals("b", iter.next());
    }

}
