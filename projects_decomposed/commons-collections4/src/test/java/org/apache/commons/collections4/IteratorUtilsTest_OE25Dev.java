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

import static org.apache.commons.collections4.functors.EqualPredicate.equalPredicate;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyListIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.iterators.EnumerationIterator;
import org.apache.commons.collections4.iterators.NodeListIterator;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Tests for IteratorUtils.
 */
public class IteratorUtilsTest_OE25Dev {

    /**
     * Collection of {@link Integer}s
     */
    private List<Integer> collectionA = null;

    /**
     * Collection of even {@link Integer}s
     */
    private List<Integer> collectionEven = null;

    /**
     * Collection of odd {@link Integer}s
     */
    private List<Integer> collectionOdd = null;

    private final Collection<Integer> emptyCollection = new ArrayList<>(1);

    private Iterable<Integer> iterableA = null;

    /**
     * Creates a NodeList containing the specified nodes.
     */
    private NodeList createNodeList(final Node[] nodes) {
        return new NodeList() {
            @Override
            public int getLength() {
                return nodes.length;
            }
            @Override
            public Node item(final int index) {
                return nodes[index];
            }
        };
    }

    /**
     * creates an array of four Node instances, mocked by EasyMock.
     */
    private Node[] createNodes() {
        final Node node1 = createMock(Node.class);
        final Node node2 = createMock(Node.class);
        final Node node3 = createMock(Node.class);
        final Node node4 = createMock(Node.class);
        replay(node1);
        replay(node2);
        replay(node3);
        replay(node4);

        return new Node[]{node1, node2, node3, node4};
}

    /**
     * Gets an immutable Iterator operating on the elements ["a", "b", "c", "d"].
     */
    private Iterator<String> getImmutableIterator() {
        final List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        return IteratorUtils.unmodifiableIterator(list.iterator());
    }

    /**
     * Gets an immutable ListIterator operating on the elements ["a", "b", "c", "d"].
     */
    private ListIterator<String> getImmutableListIterator() {
        final List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        return IteratorUtils.unmodifiableListIterator(list.listIterator());
    }

    @Before
    public void setUp() {
        collectionA = new ArrayList<>();
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

        collectionEven = Arrays.asList(2, 4, 6, 8, 10, 12);
        collectionOdd = Arrays.asList(1, 3, 5, 7, 9, 11);
    }

    @Test
    public void testAsIterableNull() {
        try {
            IteratorUtils.asIterable(null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            // success
        }
    }


    @Test
    public void testAsMultipleIterableNull() {
        try {
            IteratorUtils.asMultipleUseIterable(null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            // success
        }
    }

    /**
     * Tests methods collatedIterator(...)
     */

    //-----------------------------------------------------------------------
    /**
     * Test empty iterator
     */

    //-----------------------------------------------------------------------
    /**
     * Test empty list iterator
     */

    //-----------------------------------------------------------------------
    /**
     * Test empty map iterator
     */

    //-----------------------------------------------------------------------
    /**
     * Test empty map iterator
     */

    //-----------------------------------------------------------------------
    /**
     * Test empty map iterator
     */

    // -----------------------------------------------------------------------

    /**
     * Tests method nodeListIterator(Node)
     */

    /**
     * Tests method nodeListIterator(NodeList)
     */

    @Test
    public void testToListIteratorNull() {
        try {
            IteratorUtils.toListIterator(null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            // success
        }
    }

    /**
     * Test remove() for an immutable Iterator.
     */
    @Test
    public void testUnmodifiableIteratorImmutability() {
        final Iterator<String> iterator = getImmutableIterator();

        try {
            iterator.remove();
            // We shouldn't get to here.
            fail("remove() should throw an UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            // This is correct; ignore the exception.
        }

        iterator.next();

        try {
            iterator.remove();
            // We shouldn't get to here.
            fail("remove() should throw an UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            // This is correct; ignore the exception.
        }

    }

    //-----------------------------------------------------------------------
    /**
     * Test next() and hasNext() for an immutable Iterator.
     */

    /**
     * Test remove() for an immutable ListIterator.
     */
    @Test
    public void testUnmodifiableListIteratorImmutability() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        try {
            listIterator.remove();
            // We shouldn't get to here.
            fail("remove() should throw an UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            // This is correct; ignore the exception.
        }

        try {
            listIterator.set("a");
            // We shouldn't get to here.
            fail("set(Object) should throw an UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            // This is correct; ignore the exception.
        }

        try {
            listIterator.add("a");
            // We shouldn't get to here.
            fail("add(Object) should throw an UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            // This is correct; ignore the exception.
        }

        listIterator.next();

        try {
            listIterator.remove();
            // We shouldn't get to here.
            fail("remove() should throw an UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            // This is correct; ignore the exception.
        }

        try {
            listIterator.set("a");
            // We shouldn't get to here.
            fail("set(Object) should throw an UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            // This is correct; ignore the exception.
        }

        try {
            listIterator.add("a");
            // We shouldn't get to here.
            fail("add(Object) should throw an UnsupportedOperationException");
        } catch (final UnsupportedOperationException e) {
            // This is correct; ignore the exception.
        }
    }

    /**
     * Test next(), hasNext(), previous() and hasPrevious() for an immutable
     * ListIterator.
     */

    @Test
    public void testArrayIterator_1_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        assertTrue(iterator.next().equals("a"));
    }

    @Test
    public void testArrayIterator_2_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        assertTrue(iterator.next().equals("b"));
    }

    @Test
    public void testArrayIterator_3_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        assertTrue(iterator.next().equals("a"));
    }

    @Test
    public void testArrayIterator_6_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 1);
        assertTrue(iterator.next().equals("b"));
    }

    @Test
    public void testArrayIterator_8_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 1);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 3);
        assertTrue(!iterator.hasNext());
    }

    @Test
    public void testArrayIterator_10_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 1);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 3);
        // removed other assertion
        iterator.reset();

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 2, 3);
        assertTrue(iterator.next().equals("c"));
    }

    @Test
    public void testArrayIterator_14_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 1);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 3);
        // removed other assertion
        iterator.reset();

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayIterator(intArray);
        assertTrue(iterator.next().equals(Integer.valueOf(0)));
    }

    @Test
    public void testArrayIterator_15_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 1);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 3);
        // removed other assertion
        iterator.reset();

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayIterator(intArray);
        // removed other assertion
        assertTrue(iterator.next().equals(Integer.valueOf(1)));
    }

    @Test
    public void testArrayIterator_16_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 1);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 3);
        // removed other assertion
        iterator.reset();

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayIterator(intArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        assertTrue(iterator.next().equals(Integer.valueOf(0)));
    }

    @Test
    public void testArrayIterator_17_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 1);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 3);
        // removed other assertion
        iterator.reset();

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayIterator(intArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        iterator = IteratorUtils.arrayIterator(intArray, 1);
        assertTrue(iterator.next().equals(Integer.valueOf(1)));
    }

    @Test
    public void testArrayIterator_19_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 1);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 3);
        // removed other assertion
        iterator.reset();

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayIterator(intArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        iterator = IteratorUtils.arrayIterator(intArray, 1);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(intArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(intArray, 3);
        assertTrue(!iterator.hasNext());
    }

    @Test
    public void testArrayIterator_21_oe() {
        final Object[] objArray = {"a", "b", "c"};
        ResettableIterator<Object> iterator = IteratorUtils.arrayIterator(objArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 1);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 3);
        // removed other assertion
        iterator.reset();

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayIterator(intArray);
        // removed other assertion
        // removed other assertion
        iterator.reset();
        // removed other assertion

        iterator = IteratorUtils.arrayIterator(intArray, 1);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayIterator(intArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(intArray, 3);
        // removed other assertion
        iterator.reset();

        try {
            iterator = IteratorUtils.arrayIterator(intArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayIterator(intArray, 2, 3);
        assertTrue(iterator.next().equals(Integer.valueOf(2)));
    }

    @Test
    public void testArrayListIterator_1_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
        assertTrue(!iterator.hasPrevious());
    }

    @Test
    public void testArrayListIterator_2_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
        // removed other assertion
        assertTrue(iterator.previousIndex() == -1);
    }

    @Test
    public void testArrayListIterator_3_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.nextIndex() == 0);
    }

    @Test
    public void testArrayListIterator_4_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals("a"));
    }

    @Test
    public void testArrayListIterator_5_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.previous().equals("a"));
    }

    @Test
    public void testArrayListIterator_6_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals("a"));
    }

    @Test
    public void testArrayListIterator_7_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.previousIndex() == 0);
    }

    @Test
    public void testArrayListIterator_8_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.nextIndex() == 1);
    }

    @Test
    public void testArrayListIterator_9_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals("b"));
    }

    @Test
    public void testArrayListIterator_10_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals("c"));
    }

    @Test
    public void testArrayListIterator_11_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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
        assertTrue(iterator.next().equals("d"));
    }

    @Test
    public void testArrayListIterator_12_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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
        assertTrue(iterator.nextIndex()== 4);// size of list assertTrue(iterator.previousIndex()== 3);
    }

    @Test
    public void testArrayListIterator_15_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        assertTrue(iterator.previousIndex() == -1);
    }

    @Test
    public void testArrayListIterator_16_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        assertTrue(!iterator.hasPrevious());
    }

    @Test
    public void testArrayListIterator_17_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.nextIndex() == 0);
    }

    @Test
    public void testArrayListIterator_18_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals("b"));
    }

    @Test
    public void testArrayListIterator_19_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.previousIndex() == 0);
    }

    @Test
    public void testArrayListIterator_21_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testArrayListIterator_24_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        assertTrue(iterator.next().equals("c"));
    }

    @Test
    public void testArrayListIterator_28_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
        assertTrue(iterator.previousIndex() == -1);
    }

    @Test
    public void testArrayListIterator_29_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
        // removed other assertion
        assertTrue(!iterator.hasPrevious());
    }

    @Test
    public void testArrayListIterator_30_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.nextIndex() == 0);
    }

    @Test
    public void testArrayListIterator_31_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals(Integer.valueOf(0)));
    }

    @Test
    public void testArrayListIterator_32_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.previousIndex() == 0);
    }

    @Test
    public void testArrayListIterator_33_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.nextIndex() == 1);
    }

    @Test
    public void testArrayListIterator_34_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals(Integer.valueOf(1)));
    }

    @Test
    public void testArrayListIterator_35_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.previousIndex() == 1);
    }

    @Test
    public void testArrayListIterator_36_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.nextIndex() == 2);
    }

    @Test
    public void testArrayListIterator_37_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.previous().equals(Integer.valueOf(1)));
    }

    @Test
    public void testArrayListIterator_38_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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
        assertTrue(iterator.next().equals(Integer.valueOf(1)));
    }

    @Test
    public void testArrayListIterator_39_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
        assertTrue(iterator.previousIndex() == -1);
    }

    @Test
    public void testArrayListIterator_40_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
        // removed other assertion
        assertTrue(!iterator.hasPrevious());
    }

    @Test
    public void testArrayListIterator_41_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.nextIndex() == 0);
    }

    @Test
    public void testArrayListIterator_42_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals(Integer.valueOf(1)));
    }

    @Test
    public void testArrayListIterator_43_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.previous().equals(Integer.valueOf(1)));
    }

    @Test
    public void testArrayListIterator_44_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals(Integer.valueOf(1)));
    }

    @Test
    public void testArrayListIterator_45_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.previousIndex() == 0);
    }

    @Test
    public void testArrayListIterator_46_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.nextIndex() == 1);
    }

    @Test
    public void testArrayListIterator_47_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals(Integer.valueOf(2)));
    }

    @Test
    public void testArrayListIterator_48_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.previousIndex() == 1);
    }

    @Test
    public void testArrayListIterator_49_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
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
        assertTrue(iterator.nextIndex() == 2);
    }

    @Test
    public void testArrayListIterator_50_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
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
        assertTrue(iterator.previous().equals(Integer.valueOf(2)));
    }

    @Test
    public void testArrayListIterator_51_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
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
        assertTrue(iterator.previousIndex() == 0);
    }

    @Test
    public void testArrayListIterator_52_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
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
        assertTrue(iterator.nextIndex() == 1);
    }

    @Test
    public void testArrayListIterator_54_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
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

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 3);
        assertTrue(!iterator.hasNext());
    }

    @Test
    public void testArrayListIterator_56_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
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

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 2, 3);
        assertTrue(!iterator.hasPrevious());
    }

    @Test
    public void testArrayListIterator_57_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
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

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 2, 3);
        // removed other assertion
        assertTrue(iterator.previousIndex() == -1);
    }

    @Test
    public void testArrayListIterator_58_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
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

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 2, 3);
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.next().equals(Integer.valueOf(2)));
    }

    @Test
    public void testArrayListIterator_59_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
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

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 2, 3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(iterator.hasPrevious());
    }

    @Test
    public void testArrayListIterator_60_oe() {
        final Object[] objArray = {"a", "b", "c", "d"};
        ResettableListIterator<Object> iterator = IteratorUtils.arrayListIterator(objArray);
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

        try {
            iterator = IteratorUtils.arrayListIterator(Integer.valueOf(0));
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
                // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator((Object[]) null);
            // removed other assertion
        } catch (final NullPointerException ex) {
                // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 3);
        // removed other assertion
        try {
            iterator.previous();
            // removed other assertion
        } catch (final NoSuchElementException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(objArray, 2, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, -1, 1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        try {
            iterator = IteratorUtils.arrayListIterator(objArray, 2, 1);
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            // expected
        }

        final int[] intArray = {0, 1, 2};
        iterator = IteratorUtils.arrayListIterator(intArray);
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

        iterator = IteratorUtils.arrayListIterator(intArray, 1);
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

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 3);
        // removed other assertion

        try {
            iterator = IteratorUtils.arrayListIterator(intArray, 4);
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {
            // expected
        }

        iterator = IteratorUtils.arrayListIterator(intArray, 2, 3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!iterator.hasNext());
    }

    @Test
    public void testAsIterable_1_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(0));
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        final Iterator<Integer> iterator = list.iterator();

        final Iterable<Integer> iterable = IteratorUtils.asIterable(iterator);
        int expected = 0;
        for(final Integer actual : iterable) {
            assertEquals(expected, actual.intValue());
    }
    }

    @Test
    public void testAsIterable_2_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(0));
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        final Iterator<Integer> iterator = list.iterator();

        final Iterable<Integer> iterable = IteratorUtils.asIterable(iterator);
        int expected = 0;
        for(final Integer actual : iterable) {
            // removed other assertion
            ++expected;
        }
        // insure iteration occurred
        assertTrue(expected > 0);
    }

    @Test
    public void testAsIterable_3_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(0));
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        final Iterator<Integer> iterator = list.iterator();

        final Iterable<Integer> iterable = IteratorUtils.asIterable(iterator);
        int expected = 0;
        for(final Integer actual : iterable) {
            // removed other assertion
            ++expected;
        }
        // insure iteration occurred
        // removed other assertion

        // single use iterator
        assertFalse("should not be able to iterate twice", IteratorUtils.asIterable(iterator).iterator().hasNext());
    }

    @Test
    public void testAsMultipleIterable_1_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(0));
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        final Iterator<Integer> iterator = list.iterator();

        final Iterable<Integer> iterable = IteratorUtils.asMultipleUseIterable(iterator);
        int expected = 0;
        for(final Integer actual : iterable) {
            assertEquals(expected, actual.intValue());
    }
    }

    @Test
    public void testAsMultipleIterable_2_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(0));
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        final Iterator<Integer> iterator = list.iterator();

        final Iterable<Integer> iterable = IteratorUtils.asMultipleUseIterable(iterator);
        int expected = 0;
        for(final Integer actual : iterable) {
            // removed other assertion
            ++expected;
        }
        // insure iteration occurred
        assertTrue(expected > 0);
    }

    @Test
    public void testAsMultipleIterable_3_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(0));
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        final Iterator<Integer> iterator = list.iterator();

        final Iterable<Integer> iterable = IteratorUtils.asMultipleUseIterable(iterator);
        int expected = 0;
        for(final Integer actual : iterable) {
            // removed other assertion
            ++expected;
        }
        // insure iteration occurred
        // removed other assertion

        // multiple use iterator
        expected = 0;
        for(final Integer actual : iterable) {
            assertEquals(expected, actual.intValue());
    }
    }

    @Test
    public void testAsMultipleIterable_4_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(0));
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        final Iterator<Integer> iterator = list.iterator();

        final Iterable<Integer> iterable = IteratorUtils.asMultipleUseIterable(iterator);
        int expected = 0;
        for(final Integer actual : iterable) {
            // removed other assertion
            ++expected;
        }
        // insure iteration occurred
        // removed other assertion

        // multiple use iterator
        expected = 0;
        for(final Integer actual : iterable) {
            // removed other assertion
            ++expected;
        }
        // insure iteration occurred
        assertTrue(expected > 0);
    }

    @Test
    public void testCollatedIterator_3_oe() {
        try {
            IteratorUtils.collatedIterator(null, collectionOdd.iterator(), null);
            // removed other assertion
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            IteratorUtils.collatedIterator(null, null, collectionEven.iterator());
            // removed other assertion
        } catch (final NullPointerException npe) {
            // expected
        }

        // natural ordering
        Iterator<Integer> it =
                IteratorUtils.collatedIterator(null, collectionOdd.iterator(), collectionEven.iterator());

        List<Integer> result = IteratorUtils.toList(it);
        assertEquals(12, result.size());
    }

    @Test
    public void testCollatedIterator_4_oe() {
        try {
            IteratorUtils.collatedIterator(null, collectionOdd.iterator(), null);
            // removed other assertion
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            IteratorUtils.collatedIterator(null, null, collectionEven.iterator());
            // removed other assertion
        } catch (final NullPointerException npe) {
            // expected
        }

        // natural ordering
        Iterator<Integer> it =
                IteratorUtils.collatedIterator(null, collectionOdd.iterator(), collectionEven.iterator());

        List<Integer> result = IteratorUtils.toList(it);
        // removed other assertion

        final List<Integer> combinedList = new ArrayList<>();
        combinedList.addAll(collectionOdd);
        combinedList.addAll(collectionEven);
        Collections.sort(combinedList);

        assertEquals(combinedList, result);
    }

    @Test
    public void testCollatedIterator_5_oe() {
        try {
            IteratorUtils.collatedIterator(null, collectionOdd.iterator(), null);
            // removed other assertion
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            IteratorUtils.collatedIterator(null, null, collectionEven.iterator());
            // removed other assertion
        } catch (final NullPointerException npe) {
            // expected
        }

        // natural ordering
        Iterator<Integer> it =
                IteratorUtils.collatedIterator(null, collectionOdd.iterator(), collectionEven.iterator());

        List<Integer> result = IteratorUtils.toList(it);
        // removed other assertion

        final List<Integer> combinedList = new ArrayList<>();
        combinedList.addAll(collectionOdd);
        combinedList.addAll(collectionEven);
        Collections.sort(combinedList);

        // removed other assertion

        it = IteratorUtils.collatedIterator(null, collectionOdd.iterator(), emptyCollection.iterator());
        result = IteratorUtils.toList(it);
        assertEquals(collectionOdd, result);
    }

    @Test
    public void testCollatedIterator_6_oe() {
        try {
            IteratorUtils.collatedIterator(null, collectionOdd.iterator(), null);
            // removed other assertion
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            IteratorUtils.collatedIterator(null, null, collectionEven.iterator());
            // removed other assertion
        } catch (final NullPointerException npe) {
            // expected
        }

        // natural ordering
        Iterator<Integer> it =
                IteratorUtils.collatedIterator(null, collectionOdd.iterator(), collectionEven.iterator());

        List<Integer> result = IteratorUtils.toList(it);
        // removed other assertion

        final List<Integer> combinedList = new ArrayList<>();
        combinedList.addAll(collectionOdd);
        combinedList.addAll(collectionEven);
        Collections.sort(combinedList);

        // removed other assertion

        it = IteratorUtils.collatedIterator(null, collectionOdd.iterator(), emptyCollection.iterator());
        result = IteratorUtils.toList(it);
        // removed other assertion

        final Comparator<Integer> reverseComparator =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        Collections.reverse(collectionOdd);
        Collections.reverse(collectionEven);
        Collections.reverse(combinedList);

        it = IteratorUtils.collatedIterator(reverseComparator,
                                            collectionOdd.iterator(),
                                            collectionEven.iterator());
        result = IteratorUtils.toList(it);
        assertEquals(combinedList, result);
    }

    @Test
    public void testEmptyIterator_1_oe() {
        assertSame(EmptyIterator.INSTANCE, IteratorUtils.EMPTY_ITERATOR);
    }

    @Test
    public void testEmptyIterator_2_oe() {
        // removed other assertion
        assertSame(EmptyIterator.RESETTABLE_INSTANCE, IteratorUtils.EMPTY_ITERATOR);
    }

    @Test
    public void testEmptyIterator_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_ITERATOR instanceof Iterator);
    }

    @Test
    public void testEmptyIterator_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_ITERATOR instanceof ResettableIterator);
    }

    @Test
    public void testEmptyIterator_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ITERATOR instanceof OrderedIterator);
    }

    @Test
    public void testEmptyIterator_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ITERATOR instanceof ListIterator);
    }

    @Test
    public void testEmptyIterator_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ITERATOR instanceof MapIterator);
    }

    @Test
    public void testEmptyIterator_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ITERATOR.hasNext());
    }

    @Test
    public void testEmptyIterator_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        IteratorUtils.EMPTY_ITERATOR.reset();
        assertSame(IteratorUtils.EMPTY_ITERATOR, IteratorUtils.EMPTY_ITERATOR);
    }

    @Test
    public void testEmptyIterator_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        IteratorUtils.EMPTY_ITERATOR.reset();
        // removed other assertion
        assertSame(IteratorUtils.EMPTY_ITERATOR, IteratorUtils.emptyIterator());
    }

    @Test
    public void testEmptyListIterator_1_oe() {
        assertSame(EmptyListIterator.INSTANCE, IteratorUtils.EMPTY_LIST_ITERATOR);
    }

    @Test
    public void testEmptyListIterator_2_oe() {
        // removed other assertion
        assertSame(EmptyListIterator.RESETTABLE_INSTANCE, IteratorUtils.EMPTY_LIST_ITERATOR);
    }

    @Test
    public void testEmptyListIterator_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_LIST_ITERATOR instanceof Iterator);
    }

    @Test
    public void testEmptyListIterator_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_LIST_ITERATOR instanceof ListIterator);
    }

    @Test
    public void testEmptyListIterator_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_LIST_ITERATOR instanceof ResettableIterator);
    }

    @Test
    public void testEmptyListIterator_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_LIST_ITERATOR instanceof ResettableListIterator);
    }

    @Test
    public void testEmptyListIterator_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_LIST_ITERATOR instanceof MapIterator);
    }

    @Test
    public void testEmptyListIterator_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_LIST_ITERATOR.hasNext());
    }

    @Test
    public void testEmptyListIterator_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, IteratorUtils.EMPTY_LIST_ITERATOR.nextIndex());
    }

    @Test
    public void testEmptyListIterator_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, IteratorUtils.EMPTY_LIST_ITERATOR.previousIndex());
    }

    @Test
    public void testEmptyListIterator_11_oe() {
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
        IteratorUtils.EMPTY_LIST_ITERATOR.reset();
        assertSame(IteratorUtils.EMPTY_LIST_ITERATOR, IteratorUtils.EMPTY_LIST_ITERATOR);
    }

    @Test
    public void testEmptyListIterator_12_oe() {
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
        IteratorUtils.EMPTY_LIST_ITERATOR.reset();
        // removed other assertion
        assertSame(IteratorUtils.EMPTY_LIST_ITERATOR, IteratorUtils.emptyListIterator());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyMapIterator_1_oe() {
        assertSame(EmptyMapIterator.INSTANCE, IteratorUtils.EMPTY_MAP_ITERATOR);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyMapIterator_2_oe() {
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_MAP_ITERATOR instanceof Iterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyMapIterator_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_MAP_ITERATOR instanceof MapIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyMapIterator_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_MAP_ITERATOR instanceof ResettableIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyMapIterator_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_MAP_ITERATOR instanceof ListIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyMapIterator_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_MAP_ITERATOR instanceof OrderedIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyMapIterator_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_MAP_ITERATOR instanceof OrderedMapIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyMapIterator_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_MAP_ITERATOR.hasNext());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyMapIterator_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        ((ResettableIterator<Object>) IteratorUtils.EMPTY_MAP_ITERATOR).reset();
        assertSame(IteratorUtils.EMPTY_MAP_ITERATOR, IteratorUtils.EMPTY_MAP_ITERATOR);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyMapIterator_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        ((ResettableIterator<Object>) IteratorUtils.EMPTY_MAP_ITERATOR).reset();
        // removed other assertion
        assertSame(IteratorUtils.EMPTY_MAP_ITERATOR, IteratorUtils.emptyMapIterator());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedIterator_1_oe() {
        assertSame(EmptyOrderedIterator.INSTANCE, IteratorUtils.EMPTY_ORDERED_ITERATOR);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedIterator_2_oe() {
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_ORDERED_ITERATOR instanceof Iterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedIterator_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_ORDERED_ITERATOR instanceof OrderedIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedIterator_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_ORDERED_ITERATOR instanceof ResettableIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedIterator_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ORDERED_ITERATOR instanceof ListIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedIterator_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ORDERED_ITERATOR instanceof MapIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedIterator_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ORDERED_ITERATOR.hasNext());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedIterator_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ORDERED_ITERATOR.hasPrevious());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedIterator_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        ((ResettableIterator<Object>) IteratorUtils.EMPTY_ORDERED_ITERATOR).reset();
        assertSame(IteratorUtils.EMPTY_ORDERED_ITERATOR, IteratorUtils.EMPTY_ORDERED_ITERATOR);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedIterator_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        ((ResettableIterator<Object>) IteratorUtils.EMPTY_ORDERED_ITERATOR).reset();
        // removed other assertion
        assertSame(IteratorUtils.EMPTY_ORDERED_ITERATOR, IteratorUtils.emptyOrderedIterator());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedMapIterator_1_oe() {
        assertSame(EmptyOrderedMapIterator.INSTANCE, IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedMapIterator_2_oe() {
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR instanceof Iterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedMapIterator_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR instanceof MapIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedMapIterator_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR instanceof OrderedMapIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedMapIterator_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR instanceof ResettableIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedMapIterator_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR instanceof ListIterator);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedMapIterator_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR.hasNext());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedMapIterator_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR.hasPrevious());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedMapIterator_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        ((ResettableIterator<Object>) IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR).reset();
        assertSame(IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR, IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyOrderedMapIterator_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        ((ResettableIterator<Object>) IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR).reset();
        // removed other assertion
        assertSame(IteratorUtils.EMPTY_ORDERED_MAP_ITERATOR, IteratorUtils.emptyOrderedMapIterator());
    }

    @Test
    public void testFind_1_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = IteratorUtils.find(iterableA.iterator(), testPredicate);
        assertTrue(test.equals(4));
    }

    @Test
    public void testFind_2_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = IteratorUtils.find(iterableA.iterator(), testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        test = IteratorUtils.find(iterableA.iterator(), testPredicate);
        assertTrue(test == null);
    }

    @Test
    public void testFind_3_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = IteratorUtils.find(iterableA.iterator(), testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        test = IteratorUtils.find(iterableA.iterator(), testPredicate);
        // removed other assertion
        assertNull(IteratorUtils.find(null,testPredicate));
    }

    @Test
    public void testFirstFromIterator_1_oe() throws Exception {
        // Iterator, entry exists
        final Iterator<Integer> iterator = iterableA.iterator();
        assertEquals(1, (int) IteratorUtils.first(iterator));
    }

    @Test
    public void testForEach_1_oe() {
        final List<Integer> listA = new ArrayList<>();
        listA.add(1);

        final List<Integer> listB = new ArrayList<>();
        listB.add(2);

        final Closure<List<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<Integer>> col = new ArrayList<>();
        col.add(listA);
        col.add(listB);
        IteratorUtils.forEach(col.iterator(), testClosure);
        assertTrue(listA.isEmpty() && listB.isEmpty());
    }

    @Test
    public void testForEachButLast_1_oe() {
        final List<Integer> listA = new ArrayList<>();
        listA.add(1);

        final List<Integer> listB = new ArrayList<>();
        listB.add(2);

        final Closure<List<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<Integer>> col = new ArrayList<>();
        col.add(listA);
        col.add(listB);
        List<Integer> last = IteratorUtils.forEachButLast(col.iterator(), testClosure);
        assertTrue(listA.isEmpty() && !listB.isEmpty());
    }

    @Test
    public void testForEachButLast_2_oe() {
        final List<Integer> listA = new ArrayList<>();
        listA.add(1);

        final List<Integer> listB = new ArrayList<>();
        listB.add(2);

        final Closure<List<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<Integer>> col = new ArrayList<>();
        col.add(listA);
        col.add(listB);
        List<Integer> last = IteratorUtils.forEachButLast(col.iterator(), testClosure);
        // removed other assertion
        assertSame(listB, last);
    }

    @Test
    public void testForEachButLast_4_oe() {
        final List<Integer> listA = new ArrayList<>();
        listA.add(1);

        final List<Integer> listB = new ArrayList<>();
        listB.add(2);

        final Closure<List<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<Integer>> col = new ArrayList<>();
        col.add(listA);
        col.add(listB);
        List<Integer> last = IteratorUtils.forEachButLast(col.iterator(), testClosure);
        // removed other assertion
        // removed other assertion

        try {
            IteratorUtils.forEachButLast(col.iterator(), null);
            // removed other assertion
        } catch (final NullPointerException npe) {
            // expected
        }

        IteratorUtils.forEachButLast(null, testClosure);

        // null should be OK
        col.add(null);
        col.add(null);
        last = IteratorUtils.forEachButLast(col.iterator(), testClosure);
        assertNull(last);
    }

    @Test
    public void testGetAtIndexFromIterator_1_oe() throws Exception {
        // Iterator, entry exists
        Iterator<Integer> iterator = iterableA.iterator();
        assertEquals(1, (int) IteratorUtils.get(iterator, 0));
    }

    @Test
    public void testGetAtIndexFromIterator_2_oe() throws Exception {
        // Iterator, entry exists
        Iterator<Integer> iterator = iterableA.iterator();
        // removed other assertion
        iterator = iterableA.iterator();
        assertEquals(2, (int) IteratorUtils.get(iterator, 1));
    }

    @Test
    public void testGetAtIndexFromIterator_4_oe() throws Exception {
        // Iterator, entry exists
        Iterator<Integer> iterator = iterableA.iterator();
        // removed other assertion
        iterator = iterableA.iterator();
        // removed other assertion

        // Iterator, non-existent entry
        try {
            IteratorUtils.get(iterator, 10);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }
        assertTrue(!iterator.hasNext());
    }

    @Test
    public void testGetIterator_1_oe() {
    	final Object[] objArray = {"a", "b", "c"};
        final Map<String, String> inMap = new HashMap<>();
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);

        assertTrue("returns empty iterator when null passed", IteratorUtils.getIterator(null) instanceof EmptyIterator);
    }

    @Test
    public void testGetIterator_2_oe() {
    	final Object[] objArray = {"a", "b", "c"};
        final Map<String, String> inMap = new HashMap<>();
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);

        // removed other assertion
        assertTrue("returns Iterator when Iterator directly ", IteratorUtils.getIterator(iterableA.iterator()) instanceof Iterator);
    }

    @Test
    public void testGetIterator_3_oe() {
    	final Object[] objArray = {"a", "b", "c"};
        final Map<String, String> inMap = new HashMap<>();
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);

        // removed other assertion
        // removed other assertion
        assertTrue("returns Iterator when iterable passed", IteratorUtils.getIterator(iterableA) instanceof Iterator);
    }

    @Test
    public void testGetIterator_4_oe() {
    	final Object[] objArray = {"a", "b", "c"};
        final Map<String, String> inMap = new HashMap<>();
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("returns ObjectArrayIterator when Object array passed", IteratorUtils.getIterator(objArray) instanceof ObjectArrayIterator);
    }

    @Test
    public void testGetIterator_5_oe() {
    	final Object[] objArray = {"a", "b", "c"};
        final Map<String, String> inMap = new HashMap<>();
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("returns Iterator when Map passed", IteratorUtils.getIterator(inMap) instanceof Iterator);
    }

    @Test
    public void testGetIterator_6_oe() {
    	final Object[] objArray = {"a", "b", "c"};
        final Map<String, String> inMap = new HashMap<>();
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("returns NodeListIterator when nodeList passed", IteratorUtils.getIterator(nodeList) instanceof NodeListIterator);
    }

    @Test
    public void testGetIterator_7_oe() {
    	final Object[] objArray = {"a", "b", "c"};
        final Map<String, String> inMap = new HashMap<>();
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("returns EnumerationIterator when Enumeration passed", IteratorUtils.getIterator(new Vector().elements()) instanceof EnumerationIterator);
    }

    @Test
    public void testIndexOf_1_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        int index = IteratorUtils.indexOf(iterableA.iterator(), testPredicate);
        assertEquals(6, index);
    }

    @Test
    public void testIndexOf_2_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        int index = IteratorUtils.indexOf(iterableA.iterator(), testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        index = IteratorUtils.indexOf(iterableA.iterator(), testPredicate);
        assertEquals(-1, index);
    }

    @Test
    public void testIndexOf_3_oe() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        int index = IteratorUtils.indexOf(iterableA.iterator(), testPredicate);
        // removed other assertion
        testPredicate = equalPredicate((Number) 45);
        index = IteratorUtils.indexOf(iterableA.iterator(), testPredicate);
        // removed other assertion
        assertEquals(-1, IteratorUtils.indexOf(null, testPredicate));
    }

    @Test
    public void testNodeIterator_1_oe() {
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);
        final Node parentNode = createMock(Node.class);
        expect(parentNode.getChildNodes()).andStubReturn(nodeList);
        replay(parentNode);

        final Iterator<Node> iterator = IteratorUtils.nodeListIterator(parentNode);
        int expectedNodeIndex = 0;
        for (final Node actual : IteratorUtils.asIterable(iterator)) {
            assertEquals(nodes[expectedNodeIndex], actual);
    }
    }

    @Test
    public void testNodeIterator_2_oe() {
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);
        final Node parentNode = createMock(Node.class);
        expect(parentNode.getChildNodes()).andStubReturn(nodeList);
        replay(parentNode);

        final Iterator<Node> iterator = IteratorUtils.nodeListIterator(parentNode);
        int expectedNodeIndex = 0;
        for (final Node actual : IteratorUtils.asIterable(iterator)) {
            // removed other assertion
            ++expectedNodeIndex;
        }

        // insure iteration occurred
        assertTrue(expectedNodeIndex > 0);
    }

    @Test
    public void testNodeIterator_3_oe() {
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);
        final Node parentNode = createMock(Node.class);
        expect(parentNode.getChildNodes()).andStubReturn(nodeList);
        replay(parentNode);

        final Iterator<Node> iterator = IteratorUtils.nodeListIterator(parentNode);
        int expectedNodeIndex = 0;
        for (final Node actual : IteratorUtils.asIterable(iterator)) {
            // removed other assertion
            ++expectedNodeIndex;
        }

        // insure iteration occurred
        // removed other assertion

        // single use iterator
        assertFalse("should not be able to iterate twice", IteratorUtils.asIterable(iterator).iterator().hasNext());
    }

    @Test
    public void testNodeListIterator_1_oe() {
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);

        final Iterator<Node> iterator = IteratorUtils.nodeListIterator(nodeList);
        int expectedNodeIndex = 0;
        for (final Node actual : IteratorUtils.asIterable(iterator)) {
            assertEquals(nodes[expectedNodeIndex], actual);
    }
    }

    @Test
    public void testNodeListIterator_2_oe() {
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);

        final Iterator<Node> iterator = IteratorUtils.nodeListIterator(nodeList);
        int expectedNodeIndex = 0;
        for (final Node actual : IteratorUtils.asIterable(iterator)) {
            // removed other assertion
            ++expectedNodeIndex;
        }

        // insure iteration occurred
        assertTrue(expectedNodeIndex > 0);
    }

    @Test
    public void testNodeListIterator_3_oe() {
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);

        final Iterator<Node> iterator = IteratorUtils.nodeListIterator(nodeList);
        int expectedNodeIndex = 0;
        for (final Node actual : IteratorUtils.asIterable(iterator)) {
            // removed other assertion
            ++expectedNodeIndex;
        }

        // insure iteration occurred
        // removed other assertion

        // single use iterator
        assertFalse("should not be able to iterate twice", IteratorUtils.asIterable(iterator).iterator().hasNext());
    }

    @Test
    public void testToArray_1_oe() {
        final List<Object> list = new ArrayList<>();
        list.add(Integer.valueOf(1));
        list.add("Two");
        list.add(null);
        final Object[] result = IteratorUtils.toArray(list.iterator());
        assertEquals(list, Arrays.asList(result));
    }

    @Test
    public void testToArray2_1_oe() {
        final List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add(null);
        final String[] result = IteratorUtils.toArray(list.iterator(), String.class);
        assertEquals(list, Arrays.asList(result));
    }

    @Test
    public void testToList_1_oe() {
        final List<Object> list = new ArrayList<>();
        list.add(Integer.valueOf(1));
        list.add("Two");
        list.add(null);
        final List<Object> result = IteratorUtils.toList(list.iterator());
        assertEquals(list, result);
    }

    @Test
    public void testToListIterator_1_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(0));
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        final Iterator<Integer> iterator = list.iterator();

        final ListIterator<Integer> liItr = IteratorUtils.toListIterator(iterator);
        int expected = 0;
        while(liItr.hasNext()){
        	assertEquals(expected, liItr.next().intValue());
    }
    }

    @Test
    public void testUnmodifiableIteratorIteration_1_oe() {
        final Iterator<String> iterator = getImmutableIterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    public void testUnmodifiableIteratorIteration_2_oe() {
        final Iterator<String> iterator = getImmutableIterator();

        // removed other assertion

        assertEquals("a", iterator.next());
    }

    @Test
    public void testUnmodifiableIteratorIteration_3_oe() {
        final Iterator<String> iterator = getImmutableIterator();

        // removed other assertion

        // removed other assertion

        assertTrue(iterator.hasNext());
    }

    @Test
    public void testUnmodifiableIteratorIteration_4_oe() {
        final Iterator<String> iterator = getImmutableIterator();

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals("b", iterator.next());
    }

    @Test
    public void testUnmodifiableIteratorIteration_5_oe() {
        final Iterator<String> iterator = getImmutableIterator();

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(iterator.hasNext());
    }

    @Test
    public void testUnmodifiableIteratorIteration_6_oe() {
        final Iterator<String> iterator = getImmutableIterator();

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals("c", iterator.next());
    }

    @Test
    public void testUnmodifiableIteratorIteration_7_oe() {
        final Iterator<String> iterator = getImmutableIterator();

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(iterator.hasNext());
    }

    @Test
    public void testUnmodifiableIteratorIteration_8_oe() {
        final Iterator<String> iterator = getImmutableIterator();

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals("d", iterator.next());
    }

    @Test
    public void testUnmodifiableIteratorIteration_9_oe() {
        final Iterator<String> iterator = getImmutableIterator();

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(!iterator.hasNext());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_1_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        assertTrue(!listIterator.hasPrevious());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_2_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        // removed other assertion
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_3_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        // removed other assertion
        // removed other assertion

        assertEquals("a", listIterator.next());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_4_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_5_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_6_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("b", listIterator.next());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_7_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_8_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_9_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("c", listIterator.next());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_10_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_11_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_12_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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

        assertEquals("d", listIterator.next());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_13_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_14_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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
        assertTrue(!listIterator.hasNext());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_15_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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

        assertEquals("d", listIterator.previous());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_16_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_17_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_18_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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

        assertEquals("c", listIterator.previous());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_19_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_20_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_21_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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

        assertEquals("b", listIterator.previous());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_22_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_23_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_24_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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

        assertEquals("a", listIterator.previous());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_25_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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

        assertTrue(!listIterator.hasPrevious());
    }

    @Test
    public void testUnmodifiableListIteratorIteration_26_oe() {
        final ListIterator<String> listIterator = getImmutableListIterator();

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
        assertTrue(listIterator.hasNext());
    }

}
