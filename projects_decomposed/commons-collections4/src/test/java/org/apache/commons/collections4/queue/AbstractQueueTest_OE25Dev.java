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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import org.apache.commons.collections4.collection.AbstractCollectionTest;

/**
 * Abstract test class for {@link java.util.Queue} methods and contracts.
 * <p>
 * To use, simply extend this class, and implement
 * the {@link #makeObject} method.
 * <p>
 * If your {@link Queue} fails one of these tests by design,
 * you may still use this base set of cases.  Simply override the
 * test case (method) your {@link Queue} fails or override one of the
 * protected methods from AbstractCollectionTest.
 *
 * @since 4.0
 */
public abstract class AbstractQueueTest_OE25Dev<E> extends AbstractCollectionTest<E> {

    /**
     * JUnit constructor.
     *
     * @param testName  the test class name
     */
    public AbstractQueueTest_OE25Dev(final String testName) {
        super(testName);
    }

    //-----------------------------------------------------------------------
    /**
     *  Returns true if the collections produced by
     *  {@link #makeObject()} and {@link #makeFullCollection()}
     *  support the <code>set operation.<p>
     *  Default implementation returns true.  Override if your collection
     *  class does not support set.
     */
    public boolean isSetSupported() {
        return true;
    }

    //-----------------------------------------------------------------------
    /**
     *  Verifies that the test queue implementation matches the confirmed queue
     *  implementation.
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
     * Returns an empty {@link ArrayList}.
     */
    @Override
    public Collection<E> makeConfirmedCollection() {
        final ArrayList<E> list = new ArrayList<>();
        return list;
    }

    /**
     * Returns a full {@link ArrayList}.
     */
    @Override
    public Collection<E> makeConfirmedFullCollection() {
        final ArrayList<E> list = new ArrayList<>();
        list.addAll(Arrays.asList(getFullElements()));
        return list;
    }

    /**
     * Returns {@link #makeObject()}.
     *
     * @return an empty queue to be used for testing
     */
    @Override
    public abstract Queue<E> makeObject();

    /**
     * {@inheritDoc}
     */
    @Override
    public Queue<E> makeFullCollection() {
        // only works if queue supports optional "addAll(Collection)"
        final Queue<E> queue = makeObject();
        queue.addAll(Arrays.asList(getFullElements()));
        return queue;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the {@link #collection} field cast to a {@link Queue}.
     *
     * @return the collection field as a Queue
     */
    @Override
    public Queue<E> getCollection() {
        return (Queue<E>) super.getCollection();
    }

    //-----------------------------------------------------------------------
    /**
     *  Tests {@link Queue#offer(Object)}.
     */

    /**
     *  Tests {@link Queue#element()}.
     */

    /**
     *  Tests {@link Queue#peek()}.
     */

    /**
     *  Tests {@link Queue#remove()}.
     */

    /**
     *  Tests {@link Queue#poll()}.
     */

    //-----------------------------------------------------------------------

    /**
     * Compare the current serialized form of the Queue
     * against the canonical version in SVN.
     */

    /**
     * Compare the current serialized form of the Queue
     * against the canonical version in SVN.
     */

    public void testQueueOffer_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final E[] elements = getFullElements();
        for (final E element : elements) {
            resetEmpty();
            final boolean r = getCollection().offer(element);
            getConfirmed().add(element);
            verify();
            assertTrue("Empty queue changed after add", r);
    }
    }

    public void testQueueOffer_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final E[] elements = getFullElements();
        for (final E element : elements) {
            resetEmpty();
            final boolean r = getCollection().offer(element);
            getConfirmed().add(element);
            verify();
            // removed other assertion
            assertEquals("Queue size is 1 after first add", 1, getCollection().size());
    }
    }

    public void testQueueOffer_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final E[] elements = getFullElements();
        for (final E element : elements) {
            resetEmpty();
            final boolean r = getCollection().offer(element);
            getConfirmed().add(element);
            verify();
            // removed other assertion
            // removed other assertion
        }

        resetEmpty();
        int size = 0;
        for (final E element : elements) {
            final boolean r = getCollection().offer(element);
            getConfirmed().add(element);
            verify();
            if (r) {
                size++;
            }
            assertEquals("Queue size should grow after add", size, getCollection().size());
    }
    }

    public void testQueueOffer_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final E[] elements = getFullElements();
        for (final E element : elements) {
            resetEmpty();
            final boolean r = getCollection().offer(element);
            getConfirmed().add(element);
            verify();
            // removed other assertion
            // removed other assertion
        }

        resetEmpty();
        int size = 0;
        for (final E element : elements) {
            final boolean r = getCollection().offer(element);
            getConfirmed().add(element);
            verify();
            if (r) {
                size++;
            }
            // removed other assertion
            assertTrue("Queue should contain added element", getCollection().contains(element));
    }
    }

    public void testQueueElement_2_oe() {
        resetEmpty();

        try {
            getCollection().element();
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        resetFull();

        assertTrue(getConfirmed().contains(getCollection().element()));
    }

    public void testQueueElement_3_oe() {
        resetEmpty();

        try {
            getCollection().element();
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        resetFull();

        // removed other assertion

        if (!isRemoveSupported()) {
            return;
        }

        final int max = getFullElements().length;
        for (int i = 0; i < max; i++) {
            final E element = getCollection().element();

            if (!isNullSupported()) {
                assertNotNull(element);
    }
    }
    }

    public void testQueueElement_4_oe() {
        resetEmpty();

        try {
            getCollection().element();
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        resetFull();

        // removed other assertion

        if (!isRemoveSupported()) {
            return;
        }

        final int max = getFullElements().length;
        for (int i = 0; i < max; i++) {
            final E element = getCollection().element();

            if (!isNullSupported()) {
                // removed other assertion
            }

            assertTrue(getConfirmed().contains(element));
    }
    }

    public void testQueuePeek_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        resetEmpty();

        E element = getCollection().peek();
        assertNull(element);
    }

    public void testQueuePeek_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        resetEmpty();

        E element = getCollection().peek();
        // removed other assertion

        resetFull();

        final int max = getFullElements().length;
        for (int i = 0; i < max; i++) {
            element = getCollection().peek();

            if (!isNullSupported()) {
                assertNotNull(element);
    }
    }
    }

    public void testQueuePeek_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        resetEmpty();

        E element = getCollection().peek();
        // removed other assertion

        resetFull();

        final int max = getFullElements().length;
        for (int i = 0; i < max; i++) {
            element = getCollection().peek();

            if (!isNullSupported()) {
                // removed other assertion
            }

            assertTrue(getConfirmed().contains(element));
    }
    }

    public void testQueuePeek_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        resetEmpty();

        E element = getCollection().peek();
        // removed other assertion

        resetFull();

        final int max = getFullElements().length;
        for (int i = 0; i < max; i++) {
            element = getCollection().peek();

            if (!isNullSupported()) {
                // removed other assertion
            }

            // removed other assertion

            getCollection().remove(element);
            getConfirmed().remove(element);

            verify();
        }

        element = getCollection().peek();
        assertNull(element);
    }

    public void testQueueRemove_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        resetEmpty();

        try {
            getCollection().remove();
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        resetFull();

        final int max = getFullElements().length;
        for (int i = 0; i < max; i++) {
            final E element = getCollection().remove();
            final boolean success = getConfirmed().remove(element);
            assertTrue("remove should return correct element", success);
    }
    }

    public void testQueuePoll_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        resetEmpty();

        E element = getCollection().poll();
        assertNull(element);
    }

    public void testQueuePoll_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        resetEmpty();

        E element = getCollection().poll();
        // removed other assertion

        resetFull();

        final int max = getFullElements().length;
        for (int i = 0; i < max; i++) {
            element = getCollection().poll();
            final boolean success = getConfirmed().remove(element);
            assertTrue("poll should return correct element", success);
    }
    }

    public void testQueuePoll_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        resetEmpty();

        E element = getCollection().poll();
        // removed other assertion

        resetFull();

        final int max = getFullElements().length;
        for (int i = 0; i < max; i++) {
            element = getCollection().poll();
            final boolean success = getConfirmed().remove(element);
            // removed other assertion
            verify();
        }

        element = getCollection().poll();
        assertNull(element);
    }

    public void testEmptyQueueSerialization_1_oe() throws IOException, ClassNotFoundException {
        final Queue<E> queue = makeObject();
        if (!(queue instanceof Serializable && isTestSerialization())) {
            return;
        }

        final byte[] objekt = writeExternalFormToBytes((Serializable) queue);
        final Queue<E> queue2 = (Queue<E>) readExternalFormFromBytes(objekt);

        assertEquals("Both queues are empty", 0, queue.size());
    }

    public void testEmptyQueueSerialization_2_oe() throws IOException, ClassNotFoundException {
        final Queue<E> queue = makeObject();
        if (!(queue instanceof Serializable && isTestSerialization())) {
            return;
        }

        final byte[] objekt = writeExternalFormToBytes((Serializable) queue);
        final Queue<E> queue2 = (Queue<E>) readExternalFormFromBytes(objekt);

        // removed other assertion
        assertEquals("Both queues are empty", 0, queue2.size());
    }

    public void testFullQueueSerialization_1_oe() throws IOException, ClassNotFoundException {
        final Queue<E> queue = makeFullCollection();
        final int size = getFullElements().length;
        if (!(queue instanceof Serializable && isTestSerialization())) {
            return;
        }

        final byte[] objekt = writeExternalFormToBytes((Serializable) queue);
        final Queue<E> queue2 = (Queue<E>) readExternalFormFromBytes(objekt);

        assertEquals("Both queues are same size", size, queue.size());
    }

    public void testFullQueueSerialization_2_oe() throws IOException, ClassNotFoundException {
        final Queue<E> queue = makeFullCollection();
        final int size = getFullElements().length;
        if (!(queue instanceof Serializable && isTestSerialization())) {
            return;
        }

        final byte[] objekt = writeExternalFormToBytes((Serializable) queue);
        final Queue<E> queue2 = (Queue<E>) readExternalFormFromBytes(objekt);

        // removed other assertion
        assertEquals("Both queues are same size", size, queue2.size());
    }

    public void testEmptyQueueCompatibility_1_oe() throws IOException, ClassNotFoundException {
        /**
         * Create canonical objects with this code
        Queue queue = makeEmptyQueue();
        if (!(queue instanceof Serializable)) return;

        writeExternalFormToDisk((Serializable) queue, getCanonicalEmptyCollectionName(queue));
        */

        // test to make sure the canonical form has been preserved
        final Queue<E> queue = makeObject();
        if (queue instanceof Serializable && !skipSerializedCanonicalTests()
                && isTestSerialization()) {
            final Queue<E> queue2 = (Queue<E>) readExternalFormFromDisk(getCanonicalEmptyCollectionName(queue));
            assertEquals("Queue is empty", 0, queue2.size());
    }
    }

    public void testFullQueueCompatibility_1_oe() throws IOException, ClassNotFoundException {
        /**
         * Create canonical objects with this code
        Queue queue = makeFullQueue();
        if (!(queue instanceof Serializable)) return;

        writeExternalFormToDisk((Serializable) queue, getCanonicalFullCollectionName(queue));
        */

        // test to make sure the canonical form has been preserved
        final Queue<E> queue = makeFullCollection();
        if(queue instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final Queue<E> queue2 = (Queue<E>) readExternalFormFromDisk(getCanonicalFullCollectionName(queue));
            assertEquals("Queues are not the right size", queue.size(), queue2.size());
    }
    }

}
