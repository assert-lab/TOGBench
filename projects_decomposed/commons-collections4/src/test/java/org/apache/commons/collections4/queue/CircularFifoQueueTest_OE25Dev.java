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
package org.apache.commons.collections4.queue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Test cases for CircularFifoQueue.
 *
 * @since 4.0
 */
public class CircularFifoQueueTest_OE25Dev<E> extends AbstractQueueTest<E> {

    public CircularFifoQueueTest_OE25Dev(final String testName) {
        super(testName);
    }

    //-----------------------------------------------------------------------
    /**
     *  Runs through the regular verifications, but also verifies that
     *  the buffer contains the same elements in the same sequence as the
     *  list.
     */
    @Override
    public void verify() {
        super.verify();
        final Iterator<E> iterator1 = getCollection().iterator();
        for (final E e : getConfirmed()) {
            assertTrue(iterator1.hasNext());
            final Object o1 = iterator1.next();
            final Object o2 = e;
            assertEquals(o1, o2);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Overridden because CircularFifoQueue doesn't allow null elements.
     * @return false
     */
    @Override
    public boolean isNullSupported() {
        return false;
    }

    /**
     * Overridden because CircularFifoQueue isn't fail fast.
     * @return false
     */
    @Override
    public boolean isFailFastSupported() {
        return false;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns an empty ArrayList.
     *
     * @return an empty ArrayList
     */
    @Override
    public Collection<E> makeConfirmedCollection() {
        return new ArrayList<>();
    }

    /**
     * Returns a full ArrayList.
     *
     * @return a full ArrayList
     */
    @Override
    public Collection<E> makeConfirmedFullCollection() {
        final Collection<E> c = makeConfirmedCollection();
        c.addAll(java.util.Arrays.asList(getFullElements()));
        return c;
    }

    /**
     * Returns an empty CircularFifoQueue that won't overflow.
     *
     * @return an empty CircularFifoQueue
     */
    @Override
    public Queue<E> makeObject() {
        return new CircularFifoQueue<>(100);
    }

    //-----------------------------------------------------------------------
    /**
     * Tests that the removal operation actually removes the first element.
     */

    /**
     * Tests that the removal operation actually removes the first element.
     */

    /**
     * Tests that the constructor correctly throws an exception.
     */

    /**
     * Tests that the constructor correctly throws an exception.
     */

    /**
     * Tests that the constructor correctly throws an exception.
     */

    //-----------------------------------------------------------------------

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/CircularFifoQueue.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/CircularFifoQueue.fullCollection.version4.obj");
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CircularFifoQueue<E> getCollection() {
        return (CircularFifoQueue<E>) super.getCollection();
    }

public void testCircularFifoQueueCircular_1_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        assertEquals(true, queue.contains("A"));
    }

public void testCircularFifoQueueCircular_2_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        // removed other assertion
        assertEquals(true, queue.contains("B"));
    }

public void testCircularFifoQueueCircular_3_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        // removed other assertion
        // removed other assertion
        assertEquals(true, queue.contains("C"));
    }

public void testCircularFifoQueueCircular_4_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        queue.add((E) "D");

        assertEquals(false, queue.contains("A"));
    }

public void testCircularFifoQueueCircular_5_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        queue.add((E) "D");

        // removed other assertion
        assertEquals(true, queue.contains("B"));
    }

public void testCircularFifoQueueCircular_6_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        queue.add((E) "D");

        // removed other assertion
        // removed other assertion
        assertEquals(true, queue.contains("C"));
    }

public void testCircularFifoQueueCircular_7_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        queue.add((E) "D");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, queue.contains("D"));
    }

public void testCircularFifoQueueCircular_8_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        queue.add((E) "D");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("B", queue.peek());
    }

public void testCircularFifoQueueCircular_9_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        queue.add((E) "D");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("B", queue.remove());
    }

public void testCircularFifoQueueCircular_10_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        queue.add((E) "D");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("C", queue.remove());
    }

public void testCircularFifoQueueCircular_11_oe() {
        final List<E> list = new ArrayList<>();
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        final Queue<E> queue = new CircularFifoQueue<>(list);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        queue.add((E) "D");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("D", queue.remove());
    }

public void testCircularFifoQueueRemove_1_oe() {
        resetFull();
        final int size = getConfirmed().size();
        for (int i = 0; i < size; i++) {
            final Object o1 = getCollection().remove();
            final Object o2 = ((List<?>) getConfirmed()).remove(0);
            assertEquals("Removed objects should be equal", o1, o2);
    }
    }

public void testConstructorException1_1_oe() {
        try {
            new CircularFifoQueue<E>(0);
        } catch (final IllegalArgumentException ex) {
            return;
        }
        fail();
    }

public void testConstructorException2_1_oe() {
        try {
            new CircularFifoQueue<E>(-20);
        } catch (final IllegalArgumentException ex) {
            return;
        }
        fail();
    }

public void testConstructorException3_1_oe() {
        try {
            new CircularFifoQueue<E>(null);
        } catch (final NullPointerException ex) {
            return;
        }
        fail();
    }

public void testRemoveError1_1_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");

        assertEquals("[1, 2, 3, 4, 5]", fifo.toString());
    }

public void testRemoveError1_2_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");

        // removed other assertion

        fifo.remove("3");
        assertEquals("[1, 2, 4, 5]", fifo.toString());
    }

public void testRemoveError1_3_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");

        // removed other assertion

        fifo.remove("3");
        // removed other assertion

        fifo.remove("4");
        assertEquals("[1, 2, 5]", fifo.toString());
    }

public void testRemoveError2_1_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");
        fifo.add((E) "6");

        assertEquals(5, fifo.size());
    }

public void testRemoveError2_2_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");
        fifo.add((E) "6");

        // removed other assertion
        assertEquals("[2, 3, 4, 5, 6]", fifo.toString());
    }

public void testRemoveError2_3_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");
        fifo.add((E) "6");

        // removed other assertion
        // removed other assertion

        fifo.remove("3");
        assertEquals("[2, 4, 5, 6]", fifo.toString());
    }

public void testRemoveError2_4_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");
        fifo.add((E) "6");

        // removed other assertion
        // removed other assertion

        fifo.remove("3");
        // removed other assertion

        fifo.remove("4");
        assertEquals("[2, 5, 6]", fifo.toString());
    }

public void testRemoveError3_1_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");

        assertEquals("[1, 2, 3, 4, 5]", fifo.toString());
    }

public void testRemoveError3_2_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");

        // removed other assertion

        fifo.remove("3");
        assertEquals("[1, 2, 4, 5]", fifo.toString());
    }

public void testRemoveError3_3_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");

        // removed other assertion

        fifo.remove("3");
        // removed other assertion

        fifo.add((E) "6");
        fifo.add((E) "7");
        assertEquals("[2, 4, 5, 6, 7]", fifo.toString());
    }

public void testRemoveError3_4_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");

        // removed other assertion

        fifo.remove("3");
        // removed other assertion

        fifo.add((E) "6");
        fifo.add((E) "7");
        // removed other assertion

        fifo.remove("4");
        assertEquals("[2, 5, 6, 7]", fifo.toString());
    }

public void testRemoveError4_1_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2

        assertEquals("[3, 4, 5, 6, 7]", fifo.toString());
    }

public void testRemoveError4_2_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2

        // removed other assertion

        fifo.remove("4");  // remove element in middle of array, after start
        assertEquals("[3, 5, 6, 7]", fifo.toString());
    }

public void testRemoveError5_1_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2

        assertEquals("[3, 4, 5, 6, 7]", fifo.toString());
    }

public void testRemoveError5_2_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2

        // removed other assertion

        fifo.remove("5");  // remove element at last pos in array
        assertEquals("[3, 4, 6, 7]", fifo.toString());
    }

public void testRemoveError6_1_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2

        assertEquals("[3, 4, 5, 6, 7]", fifo.toString());
    }

public void testRemoveError6_2_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2

        // removed other assertion

        fifo.remove("6");  // remove element at position zero in array
        assertEquals("[3, 4, 5, 7]", fifo.toString());
    }

public void testRemoveError7_1_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2

        assertEquals("[3, 4, 5, 6, 7]", fifo.toString());
    }

public void testRemoveError7_2_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2

        // removed other assertion

        fifo.remove("7");  // remove element at position one in array
        assertEquals("[3, 4, 5, 6]", fifo.toString());
    }

public void testRemoveError8_1_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2
        fifo.add((E) "8");  // end=3

        assertEquals("[4, 5, 6, 7, 8]", fifo.toString());
    }

public void testRemoveError8_2_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2
        fifo.add((E) "8");  // end=3

        // removed other assertion

        fifo.remove("7");  // remove element at position one in array, need to shift 8
        assertEquals("[4, 5, 6, 8]", fifo.toString());
    }

public void testRemoveError9_1_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2
        fifo.add((E) "8");  // end=3

        assertEquals("[4, 5, 6, 7, 8]", fifo.toString());
    }

public void testRemoveError9_2_oe() throws Exception {
        // based on bug 33071
        final CircularFifoQueue<E> fifo = new CircularFifoQueue<>(5);
        fifo.add((E) "1");
        fifo.add((E) "2");
        fifo.add((E) "3");
        fifo.add((E) "4");
        fifo.add((E) "5");  // end=0
        fifo.add((E) "6");  // end=1
        fifo.add((E) "7");  // end=2
        fifo.add((E) "8");  // end=3

        // removed other assertion

        fifo.remove("8");  // remove element at position two in array
        assertEquals("[4, 5, 6, 7]", fifo.toString());
    }

public void testRepeatedSerialization_1_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        assertEquals(1, b.size());
    }

public void testRepeatedSerialization_2_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        assertEquals(true, b.contains("a"));
    }

public void testRepeatedSerialization_3_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        assertEquals(1, b2.size());
    }

public void testRepeatedSerialization_4_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        assertEquals(true, b2.contains("a"));
    }

public void testRepeatedSerialization_5_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        b2.add((E) "b");
        assertEquals(2, b2.size());
    }

public void testRepeatedSerialization_6_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        b2.add((E) "b");
        // removed other assertion
        assertEquals(true, b2.contains("a"));
    }

public void testRepeatedSerialization_7_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        b2.add((E) "b");
        // removed other assertion
        // removed other assertion
        assertEquals(true, b2.contains("b"));
    }

public void testRepeatedSerialization_8_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        b2.add((E) "b");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b2);

        final CircularFifoQueue<E> b3 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        assertEquals(2, b3.size());
    }

public void testRepeatedSerialization_9_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        b2.add((E) "b");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b2);

        final CircularFifoQueue<E> b3 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        assertEquals(true, b3.contains("a"));
    }

public void testRepeatedSerialization_10_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        b2.add((E) "b");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b2);

        final CircularFifoQueue<E> b3 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        assertEquals(true, b3.contains("b"));
    }

public void testRepeatedSerialization_11_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        b2.add((E) "b");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b2);

        final CircularFifoQueue<E> b3 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        b3.add((E) "c");
        assertEquals(2, b3.size());
    }

public void testRepeatedSerialization_12_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        b2.add((E) "b");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b2);

        final CircularFifoQueue<E> b3 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        b3.add((E) "c");
        // removed other assertion
        assertEquals(true, b3.contains("b"));
    }

public void testRepeatedSerialization_13_oe() throws Exception {
        // bug 31433
        final CircularFifoQueue<E> b = new CircularFifoQueue<>(2);
        b.add((E) "a");
        // removed other assertion
        // removed other assertion

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b);

        final CircularFifoQueue<E> b2 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        b2.add((E) "b");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        bos = new ByteArrayOutputStream();
        new ObjectOutputStream(bos).writeObject(b2);

        final CircularFifoQueue<E> b3 = (CircularFifoQueue<E>) new ObjectInputStream(
            new ByteArrayInputStream(bos.toByteArray())).readObject();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        b3.add((E) "c");
        // removed other assertion
        // removed other assertion
        assertEquals(true, b3.contains("c"));
    }

public void testGetIndex_1_oe() {
        resetFull();

        final CircularFifoQueue<E> queue = getCollection();
        final List<E> confirmed = (List<E>) getConfirmed();
        for (int i = 0; i < confirmed.size(); i++) {
            assertEquals(confirmed.get(i), queue.get(i));
    }
    }

public void testGetIndex_2_oe() {
        resetFull();

        final CircularFifoQueue<E> queue = getCollection();
        final List<E> confirmed = (List<E>) getConfirmed();
        for (int i = 0; i < confirmed.size(); i++) {
            // removed other assertion
        }

        // remove the first two elements and check again
        queue.remove();
        queue.remove();

        for (int i = 0; i < queue.size(); i++) {
            assertEquals(confirmed.get(i + 2), queue.get(i));
    }
    }

}
