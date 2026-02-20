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
package org.apache.commons.collections4.list;

import java.util.Arrays;

/**
 * Test case for {@link AbstractLinkedList}.
 *
 */
public abstract class AbstractLinkedListTest_OE25Dev<E> extends AbstractListTest<E> {

    public AbstractLinkedListTest_OE25Dev(final String testName) {
        super(testName);
    }

    //-----------------------------------------------------------------------

    protected void checkNodes() {
        final AbstractLinkedList<E> list = getCollection();
        for (int i = 0; i < list.size; i++) {
            assertEquals(list.getNode(i, false).next, list.getNode(i + 1, true));
            if (i < list.size - 1) {
                assertEquals(list.getNode(i + 1,false).previous,list.getNode(i,false));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractLinkedList<E> getCollection() {
        return (AbstractLinkedList<E>) super.getCollection();
    }

    public void testRemoveFirst_1_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isRemoveSupported()) {
            try {
                list.removeFirst();
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        assertEquals("value1", list.removeFirst());
    }

    public void testRemoveFirst_2_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isRemoveSupported()) {
            try {
                list.removeFirst();
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        // removed other assertion
        checkNodes();
        list.addLast((E) "value3");
        checkNodes();
        assertEquals("value2", list.removeFirst());
    }

    public void testRemoveFirst_3_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isRemoveSupported()) {
            try {
                list.removeFirst();
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        // removed other assertion
        checkNodes();
        list.addLast((E) "value3");
        checkNodes();
        // removed other assertion
        assertEquals("value3", list.removeFirst());
    }

    public void testRemoveFirst_4_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isRemoveSupported()) {
            try {
                list.removeFirst();
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        // removed other assertion
        checkNodes();
        list.addLast((E) "value3");
        checkNodes();
        // removed other assertion
        // removed other assertion
        checkNodes();
        list.addLast((E) "value4");
        checkNodes();
        assertEquals("value4", list.removeFirst());
    }

    public void testRemoveLast_1_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isRemoveSupported()) {
            try {
                list.removeLast();
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        assertEquals("value2", list.removeLast());
    }

    public void testRemoveLast_2_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isRemoveSupported()) {
            try {
                list.removeLast();
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        // removed other assertion
        list.addFirst((E) "value3");
        checkNodes();
        assertEquals("value1", list.removeLast());
    }

    public void testRemoveLast_3_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isRemoveSupported()) {
            try {
                list.removeLast();
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        // removed other assertion
        list.addFirst((E) "value3");
        checkNodes();
        // removed other assertion
        assertEquals("value3", list.removeLast());
    }

    public void testRemoveLast_4_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isRemoveSupported()) {
            try {
                list.removeLast();
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        // removed other assertion
        list.addFirst((E) "value3");
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addFirst((E) "value4");
        checkNodes();
        assertEquals("value4", list.removeFirst());
    }

    public void testAddNodeAfter_1_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        assertEquals("value1", list.getFirst());
    }

    public void testAddNodeAfter_2_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        // removed other assertion
        assertEquals("value2", list.getLast());
    }

    public void testAddNodeAfter_3_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        // removed other assertion
        // removed other assertion
        list.removeFirst();
        checkNodes();
        list.addNodeAfter(list.getNode(0, false), (E) "value3");
        checkNodes();
        assertEquals("value2", list.getFirst());
    }

    public void testAddNodeAfter_4_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        // removed other assertion
        // removed other assertion
        list.removeFirst();
        checkNodes();
        list.addNodeAfter(list.getNode(0, false), (E) "value3");
        checkNodes();
        // removed other assertion
        assertEquals("value3", list.getLast());
    }

    public void testAddNodeAfter_5_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        // removed other assertion
        // removed other assertion
        list.removeFirst();
        checkNodes();
        list.addNodeAfter(list.getNode(0, false), (E) "value3");
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(0, false), (E) "value4");
        checkNodes();
        assertEquals("value2", list.getFirst());
    }

    public void testAddNodeAfter_6_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        // removed other assertion
        // removed other assertion
        list.removeFirst();
        checkNodes();
        list.addNodeAfter(list.getNode(0, false), (E) "value3");
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(0, false), (E) "value4");
        checkNodes();
        // removed other assertion
        assertEquals("value3", list.getLast());
    }

    public void testAddNodeAfter_7_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        // removed other assertion
        // removed other assertion
        list.removeFirst();
        checkNodes();
        list.addNodeAfter(list.getNode(0, false), (E) "value3");
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(0, false), (E) "value4");
        checkNodes();
        // removed other assertion
        // removed other assertion
        assertEquals("value4", list.get(1));
    }

    public void testAddNodeAfter_8_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        // removed other assertion
        // removed other assertion
        list.removeFirst();
        checkNodes();
        list.addNodeAfter(list.getNode(0, false), (E) "value3");
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(0, false), (E) "value4");
        checkNodes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(2, false), (E) "value5");
        checkNodes();
        assertEquals("value2", list.getFirst());
    }

    public void testAddNodeAfter_9_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        // removed other assertion
        // removed other assertion
        list.removeFirst();
        checkNodes();
        list.addNodeAfter(list.getNode(0, false), (E) "value3");
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(0, false), (E) "value4");
        checkNodes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(2, false), (E) "value5");
        checkNodes();
        // removed other assertion
        assertEquals("value4", list.get(1));
    }

    public void testAddNodeAfter_10_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        // removed other assertion
        // removed other assertion
        list.removeFirst();
        checkNodes();
        list.addNodeAfter(list.getNode(0, false), (E) "value3");
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(0, false), (E) "value4");
        checkNodes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(2, false), (E) "value5");
        checkNodes();
        // removed other assertion
        // removed other assertion
        assertEquals("value3", list.get(2));
    }

    public void testAddNodeAfter_11_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {}
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        // removed other assertion
        // removed other assertion
        list.removeFirst();
        checkNodes();
        list.addNodeAfter(list.getNode(0, false), (E) "value3");
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(0, false), (E) "value4");
        checkNodes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.addNodeAfter(list.getNode(2, false), (E) "value5");
        checkNodes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("value5", list.getLast());
    }

    public void testRemoveNode_1_oe() {
        resetEmpty();
        if (!isAddSupported() || !isRemoveSupported()) {
            return;
        }
        final AbstractLinkedList<E> list = getCollection();

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        list.removeNode(list.getNode(0, false));
        checkNodes();
        assertEquals("value2", list.getFirst());
    }

    public void testRemoveNode_2_oe() {
        resetEmpty();
        if (!isAddSupported() || !isRemoveSupported()) {
            return;
        }
        final AbstractLinkedList<E> list = getCollection();

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        list.removeNode(list.getNode(0, false));
        checkNodes();
        // removed other assertion
        assertEquals("value2", list.getLast());
    }

    public void testRemoveNode_3_oe() {
        resetEmpty();
        if (!isAddSupported() || !isRemoveSupported()) {
            return;
        }
        final AbstractLinkedList<E> list = getCollection();

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        list.removeNode(list.getNode(0, false));
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addFirst((E) "value1");
        list.addFirst((E) "value0");
        checkNodes();
        list.removeNode(list.getNode(1, false));
        assertEquals("value0", list.getFirst());
    }

    public void testRemoveNode_4_oe() {
        resetEmpty();
        if (!isAddSupported() || !isRemoveSupported()) {
            return;
        }
        final AbstractLinkedList<E> list = getCollection();

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        list.removeNode(list.getNode(0, false));
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addFirst((E) "value1");
        list.addFirst((E) "value0");
        checkNodes();
        list.removeNode(list.getNode(1, false));
        // removed other assertion
        assertEquals("value2", list.getLast());
    }

    public void testRemoveNode_5_oe() {
        resetEmpty();
        if (!isAddSupported() || !isRemoveSupported()) {
            return;
        }
        final AbstractLinkedList<E> list = getCollection();

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        list.removeNode(list.getNode(0, false));
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addFirst((E) "value1");
        list.addFirst((E) "value0");
        checkNodes();
        list.removeNode(list.getNode(1, false));
        // removed other assertion
        // removed other assertion
        checkNodes();
        list.removeNode(list.getNode(1, false));
        assertEquals("value0", list.getFirst());
    }

    public void testRemoveNode_6_oe() {
        resetEmpty();
        if (!isAddSupported() || !isRemoveSupported()) {
            return;
        }
        final AbstractLinkedList<E> list = getCollection();

        list.addAll(Arrays.asList((E[]) new String[] { "value1", "value2" }));
        list.removeNode(list.getNode(0, false));
        checkNodes();
        // removed other assertion
        // removed other assertion
        list.addFirst((E) "value1");
        list.addFirst((E) "value0");
        checkNodes();
        list.removeNode(list.getNode(1, false));
        // removed other assertion
        // removed other assertion
        checkNodes();
        list.removeNode(list.getNode(1, false));
        // removed other assertion
        assertEquals("value0", list.getLast());
    }

    public void testGetNode_1_oe() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        // get marker
        assertEquals(list.getNode(0, true).previous, list.getNode(0, true).next);
    }

}
