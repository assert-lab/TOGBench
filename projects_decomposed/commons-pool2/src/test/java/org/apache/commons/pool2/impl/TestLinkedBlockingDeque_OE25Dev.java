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
package org.apache.commons.pool2.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Tests for {@link LinkedBlockingDeque}.
 */
public class TestLinkedBlockingDeque_OE25Dev {

    private static final Duration TIMEOUT_50_MILLIS = Duration.ofMillis(50);
    private static final Integer ONE = Integer.valueOf(1);
    private static final Integer TWO = Integer.valueOf(2);
    private static final Integer THREE = Integer.valueOf(3);

    LinkedBlockingDeque<Integer> deque;

    @BeforeEach
    public void setUp() {
        deque = new LinkedBlockingDeque<>(2);
    }

    /*
     * https://issues.apache.org/jira/browse/POOL-281
     *
     * Should complete almost instantly when the issue is fixed.
     */
    @Test
    @Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
    public void testPossibleBug() {

        deque = new LinkedBlockingDeque<>();
        for (int i = 0; i < 3; i++) {
            deque.add(Integer.valueOf(i));
        }

        // This particular sequence of method calls() (there may be others)
        // creates an internal state that triggers an infinite loop in the
        // iterator.
        final Iterator<Integer> iter = deque.iterator();
        iter.next();

        deque.remove(Integer.valueOf(1));
        deque.remove(Integer.valueOf(0));
        deque.remove(Integer.valueOf(2));

        iter.next();
    }

    @Test
    public void test_oe() throws InterruptedException {
        try {
    deque.putLast(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testAdd_1_oe() {
        assertTrue(deque.add(ONE));
    }

    @Test
    public void testAdd_2_oe() {
        // removed other assertion
        assertTrue(deque.add(TWO));
    }

    @Test
    public void testAdd_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    deque.add(THREE);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testAdd_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    deque.add(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testAddFirst_1_oe() {
        deque.addFirst(ONE);
        deque.addFirst(TWO);
        assertEquals(2, deque.size());
    }

    @Test
    public void testAddFirst_2_oe() throws Exception {
        deque.addFirst(ONE);
        deque.addFirst(TWO);
        // removed other assertion
        try {
    deque.add(THREE);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testAddFirst_3_oe() {
        deque.addFirst(ONE);
        deque.addFirst(TWO);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), deque.pop());
    }

    @Test
    public void testAddLast_1_oe() {
        deque.addLast(ONE);
        deque.addLast(TWO);
        assertEquals(2, deque.size());
    }

    @Test
    public void testAddLast_2_oe() throws Exception {
        deque.addLast(ONE);
        deque.addLast(TWO);
        // removed other assertion
        try {
    deque.add(THREE);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testAddLast_3_oe() {
        deque.addLast(ONE);
        deque.addLast(TWO);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1), deque.pop());
    }

    @Test
    public void testClear_1_oe() {
        deque.add(ONE);
        deque.add(TWO);
        deque.clear();
        deque.add(ONE);
        assertEquals(1, deque.size());
    }

    @Test
    public void testConstructors_1_oe() {
        LinkedBlockingDeque<Integer> deque = new LinkedBlockingDeque<>();
        assertEquals(Integer.MAX_VALUE, deque.remainingCapacity());
    }

    @Test
    public void testConstructors_2_oe() {
        LinkedBlockingDeque<Integer> deque = new LinkedBlockingDeque<>();
        // removed other assertion

        deque = new LinkedBlockingDeque<>(2);
        assertEquals(2, deque.remainingCapacity());
    }

    @Test
    public void testConstructors_3_oe() {
        LinkedBlockingDeque<Integer> deque = new LinkedBlockingDeque<>();
        // removed other assertion

        deque = new LinkedBlockingDeque<>(2);
        // removed other assertion

        deque = new LinkedBlockingDeque<>(Arrays.asList(ONE, TWO));
        assertEquals(2, deque.size());
    }

    @Test
    public void testConstructors_4_oe() throws Exception {
        LinkedBlockingDeque<Integer> deque = new LinkedBlockingDeque<>();
        // removed other assertion

        deque = new LinkedBlockingDeque<>(2);
        // removed other assertion

        deque = new LinkedBlockingDeque<>(Arrays.asList(ONE, TWO));
        // removed other assertion

        try {
    new LinkedBlockingDeque<>(Arrays.asList(ONE, null));
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testContains_1_oe() {
        deque.add(ONE);
        assertTrue(deque.contains(ONE));
    }

    @Test
    public void testContains_2_oe() {
        deque.add(ONE);
        // removed other assertion
        assertFalse(deque.contains(TWO));
    }

    @Test
    public void testContains_3_oe() {
        deque.add(ONE);
        // removed other assertion
        // removed other assertion
        assertFalse(deque.contains(null));
    }

    @Test
    public void testContains_4_oe() {
        deque.add(ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        deque.add(TWO);
        assertTrue(deque.contains(TWO));
    }

    @Test
    public void testContains_5_oe() {
        deque.add(ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        deque.add(TWO);
        // removed other assertion
        assertFalse(deque.contains(THREE));
    }

    @Test
    public void testDescendingIterator_1_oe() throws Exception {
        try {
    deque.descendingIterator().next();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testDescendingIterator_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        final Iterator<Integer> iter = deque.descendingIterator();
        assertEquals(Integer.valueOf(2), iter.next());
    }

    @Test
    public void testDescendingIterator_3_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        final Iterator<Integer> iter = deque.descendingIterator();
        // removed other assertion
        iter.remove();
        assertEquals(Integer.valueOf(1), iter.next());
    }

    @Test
    public void testDrainTo_1_oe() {
        Collection<Integer> c = new ArrayList<>();
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(2, deque.drainTo(c));
    }

    @Test
    public void testDrainTo_2_oe() {
        Collection<Integer> c = new ArrayList<>();
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        assertEquals(2, c.size());
    }

    @Test
    public void testDrainTo_3_oe() {
        Collection<Integer> c = new ArrayList<>();
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        // removed other assertion

        c = new ArrayList<>();
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(1, deque.drainTo(c, 1));
    }

    @Test
    public void testDrainTo_4_oe() {
        Collection<Integer> c = new ArrayList<>();
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        // removed other assertion

        c = new ArrayList<>();
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        assertEquals(1, deque.size());
    }

    @Test
    public void testDrainTo_5_oe() {
        Collection<Integer> c = new ArrayList<>();
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        // removed other assertion

        c = new ArrayList<>();
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        // removed other assertion
        assertEquals(1, c.size());
    }

    @Test
    public void testDrainTo_6_oe() {
        Collection<Integer> c = new ArrayList<>();
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        // removed other assertion

        c = new ArrayList<>();
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1), c.iterator().next());
    }

    @Test
    public void testElement_1_oe() throws Exception {
        try {
    deque.element();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testElement_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(Integer.valueOf(1), deque.element());
    }

    @Test
    public void testGetFirst_1_oe() throws Exception {
        try {
    deque.getFirst();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testGetFirst_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(Integer.valueOf(1), deque.getFirst());
    }

    @Test
    public void testGetLast_1_oe() throws Exception {
        try {
    deque.getLast();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testGetLast_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(Integer.valueOf(2), deque.getLast());
    }

    @Test
    public void testIterator_1_oe() throws Exception {
        try {
    deque.iterator().next();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testIterator_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        final Iterator<Integer> iter = deque.iterator();
        assertEquals(Integer.valueOf(1), iter.next());
    }

    @Test
    public void testIterator_3_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        final Iterator<Integer> iter = deque.iterator();
        // removed other assertion
        iter.remove();
        assertEquals(Integer.valueOf(2), iter.next());
    }

    @Test
    public void testOffer_1_oe() {
        assertTrue(deque.offer(ONE));
    }

    @Test
    public void testOffer_2_oe() {
        // removed other assertion
        assertTrue(deque.offer(TWO));
    }

    @Test
    public void testOffer_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(deque.offer(THREE));
    }

    @Test
    public void testOffer_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    deque.offer(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOfferFirst_1_oe() {
        deque.offerFirst(ONE);
        deque.offerFirst(TWO);
        assertEquals(2, deque.size());
    }

    @Test
    public void testOfferFirst_2_oe() throws Exception {
        deque.offerFirst(ONE);
        deque.offerFirst(TWO);
        // removed other assertion
        try {
    deque.offerFirst(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOfferFirst_3_oe() {
        deque.offerFirst(ONE);
        deque.offerFirst(TWO);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), deque.pop());
    }

    @Test
    public void testOfferFirstWithTimeout_1_oe() throws InterruptedException {
        try {
    deque.offerFirst(null, TIMEOUT_50_MILLIS);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOfferFirstWithTimeout_2_oe() throws InterruptedException {
        // removed other assertion
        assertTrue(deque.offerFirst(ONE, TIMEOUT_50_MILLIS));
    }

    @Test
    public void testOfferFirstWithTimeout_3_oe() throws InterruptedException {
        // removed other assertion
        // removed other assertion
        assertTrue(deque.offerFirst(TWO, TIMEOUT_50_MILLIS));
    }

    @Test
    public void testOfferFirstWithTimeout_4_oe() throws InterruptedException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(deque.offerFirst(THREE, TIMEOUT_50_MILLIS));
    }

    @Test
    public void testOfferLast_1_oe() {
        deque.offerLast(ONE);
        deque.offerLast(TWO);
        assertEquals(2, deque.size());
    }

    @Test
    public void testOfferLast_2_oe() throws Exception {
        deque.offerLast(ONE);
        deque.offerLast(TWO);
        // removed other assertion
        try {
    deque.offerLast(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOfferLast_3_oe() {
        deque.offerLast(ONE);
        deque.offerLast(TWO);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1), deque.pop());
    }

    @Test
    public void testOfferLastWithTimeout_1_oe() throws InterruptedException {
        try {
    deque.offerLast(null, TIMEOUT_50_MILLIS);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOfferLastWithTimeout_2_oe() throws InterruptedException {
        // removed other assertion
        assertTrue(deque.offerLast(ONE, TIMEOUT_50_MILLIS));
    }

    @Test
    public void testOfferLastWithTimeout_3_oe() throws InterruptedException {
        // removed other assertion
        // removed other assertion
        assertTrue(deque.offerLast(TWO, TIMEOUT_50_MILLIS));
    }

    @Test
    public void testOfferLastWithTimeout_4_oe() throws InterruptedException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(deque.offerLast(THREE, TIMEOUT_50_MILLIS));
    }

    @Test
    public void testOfferWithTimeout_1_oe() throws InterruptedException {
        assertTrue(deque.offer(ONE, TIMEOUT_50_MILLIS));
    }

    @Test
    public void testOfferWithTimeout_2_oe() throws InterruptedException {
        // removed other assertion
        assertTrue(deque.offer(TWO, TIMEOUT_50_MILLIS));
    }

    @Test
    public void testOfferWithTimeout_3_oe() throws InterruptedException {
        // removed other assertion
        // removed other assertion
        assertFalse(deque.offer(THREE, TIMEOUT_50_MILLIS));
    }

    @Test
    public void testOfferWithTimeout_4_oe() throws InterruptedException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    deque.offer(null, TIMEOUT_50_MILLIS);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPeek_1_oe() {
        assertNull(deque.peek());
    }

    @Test
    public void testPeek_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(Integer.valueOf(1), deque.peek());
    }

    @Test
    public void testPeekFirst_1_oe() {
        assertNull(deque.peekFirst());
    }

    @Test
    public void testPeekFirst_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(Integer.valueOf(1), deque.peekFirst());
    }

    @Test
    public void testPeekLast_1_oe() {
        assertNull(deque.peekLast());
    }

    @Test
    public void testPeekLast_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(Integer.valueOf(2), deque.peekLast());
    }

    @Test
    public void testPollFirst_1_oe() {
        assertNull(deque.pollFirst());
    }

    @Test
    public void testPollFirst_2_oe() {
        // removed other assertion
        assertTrue(deque.offerFirst(ONE));
    }

    @Test
    public void testPollFirst_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(deque.offerFirst(TWO));
    }

    @Test
    public void testPollFirst_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), deque.pollFirst());
    }

    @Test
    public void testPollFirstWithTimeout_1_oe() throws InterruptedException {
        assertNull(deque.pollFirst());
    }

    @Test
    public void testPollFirstWithTimeout_2_oe() throws InterruptedException {
        // removed other assertion
        assertNull(deque.pollFirst(TIMEOUT_50_MILLIS));
    }

    @Test
    public void testPollLast_1_oe() {
        assertNull(deque.pollLast());
    }

    @Test
    public void testPollLast_2_oe() {
        // removed other assertion
        assertTrue(deque.offerFirst(ONE));
    }

    @Test
    public void testPollLast_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(deque.offerFirst(TWO));
    }

    @Test
    public void testPollLast_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1), deque.pollLast());
    }

    @Test
    public void testPollLastWithTimeout_1_oe() throws InterruptedException {
        assertNull(deque.pollLast());
    }

    @Test
    public void testPollLastWithTimeout_2_oe() throws InterruptedException {
        // removed other assertion
        assertNull(deque.pollLast(TIMEOUT_50_MILLIS));
    }

    @Test
    public void testPollWithTimeout_1_oe() throws InterruptedException {
        assertNull(deque.poll(TIMEOUT_50_MILLIS));
    }

    @Test
    public void testPollWithTimeout_2_oe() throws InterruptedException {
        // removed other assertion
        assertNull(deque.poll(TIMEOUT_50_MILLIS));
    }

    @Test
    public void testPop_1_oe() throws Exception {
        try {
    deque.pop();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testPop_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(Integer.valueOf(1), deque.pop());
    }

    @Test
    public void testPop_3_oe() throws Exception {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        try {
     deque.pop(); deque.pop();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testPush_1_oe() {
        deque.push(ONE);
        deque.push(TWO);
        assertEquals(2, deque.size());
    }

    @Test
    public void testPush_2_oe() throws Exception {
        deque.push(ONE);
        deque.push(TWO);
        // removed other assertion
        try {
    deque.push(THREE);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testPush_3_oe() {
        deque.push(ONE);
        deque.push(TWO);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), deque.pop());
    }

    @Test
    public void testPut_1_oe() throws InterruptedException {
        try {
    deque.put(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPutFirst_1_oe() throws InterruptedException {
        try {
    deque.putFirst(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPutFirst_2_oe() throws InterruptedException {
        // removed other assertion
        deque.putFirst(ONE);
        deque.putFirst(TWO);
        assertEquals(2, deque.size());
    }

    @Test
    public void testPutFirst_3_oe() throws InterruptedException {
        // removed other assertion
        deque.putFirst(ONE);
        deque.putFirst(TWO);
        // removed other assertion
        assertEquals(Integer.valueOf(2), deque.pop());
    }

    @Test
    public void testPutLast_1_oe() throws InterruptedException {
        try {
    deque.putLast(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPutLast_2_oe() throws InterruptedException {
        // removed other assertion
        deque.putLast(ONE);
        deque.putLast(TWO);
        assertEquals(2, deque.size());
    }

    @Test
    public void testPutLast_3_oe() throws InterruptedException {
        // removed other assertion
        deque.putLast(ONE);
        deque.putLast(TWO);
        // removed other assertion
        assertEquals(Integer.valueOf(1), deque.pop());
    }

    @Test
    public void testRemove_1_oe() throws Exception {
        try {
    deque.remove();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testRemove_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(Integer.valueOf(1), deque.remove());
    }

    @Test
    public void testRemoveFirst_1_oe() throws Exception {
        try {
    deque.removeFirst();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testRemoveFirst_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(Integer.valueOf(1), deque.removeFirst());
    }

    @Test
    public void testRemoveFirst_3_oe() throws Exception {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        try {
     deque.removeFirst(); deque.removeFirst();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testRemoveLast_1_oe() throws Exception {
        try {
    deque.removeLast();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testRemoveLast_2_oe() {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        assertEquals(Integer.valueOf(2), deque.removeLast());
    }

    @Test
    public void testRemoveLast_3_oe() throws Exception {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        try {
     deque.removeLast(); deque.removeLast();
    fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testRemoveLastOccurrence_1_oe() {
        assertFalse(deque.removeLastOccurrence(null));
    }

    @Test
    public void testRemoveLastOccurrence_2_oe() {
        // removed other assertion
        assertFalse(deque.removeLastOccurrence(ONE));
    }

    @Test
    public void testRemoveLastOccurrence_3_oe() {
        // removed other assertion
        // removed other assertion
        deque.add(ONE);
        deque.add(ONE);
        assertTrue(deque.removeLastOccurrence(ONE));
    }

    @Test
    public void testRemoveLastOccurrence_4_oe() {
        // removed other assertion
        // removed other assertion
        deque.add(ONE);
        deque.add(ONE);
        // removed other assertion
        assertEquals(1, deque.size());
    }

    @Test
    public void testTake_1_oe() throws InterruptedException {
        assertTrue(deque.offerFirst(ONE));
    }

    @Test
    public void testTake_2_oe() throws InterruptedException {
        // removed other assertion
        assertTrue(deque.offerFirst(TWO));
    }

    @Test
    public void testTake_3_oe() throws InterruptedException {
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), deque.take());
    }

    @Test
    public void testTakeFirst_1_oe() throws InterruptedException {
        assertTrue(deque.offerFirst(ONE));
    }

    @Test
    public void testTakeFirst_2_oe() throws InterruptedException {
        // removed other assertion
        assertTrue(deque.offerFirst(TWO));
    }

    @Test
    public void testTakeFirst_3_oe() throws InterruptedException {
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), deque.takeFirst());
    }

    @Test
    public void testTakeLast_1_oe() throws InterruptedException {
        assertTrue(deque.offerFirst(ONE));
    }

    @Test
    public void testTakeLast_2_oe() throws InterruptedException {
        // removed other assertion
        assertTrue(deque.offerFirst(TWO));
    }

    @Test
    public void testTakeLast_3_oe() throws InterruptedException {
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1), deque.takeLast());
    }

    @Test
    public void testToArray_1_oe() {
        deque.add(ONE);
        deque.add(TWO);
        Object[] arr = deque.toArray();
        assertEquals(Integer.valueOf(1), arr[0]);
    }

    @Test
    public void testToArray_2_oe() {
        deque.add(ONE);
        deque.add(TWO);
        Object[] arr = deque.toArray();
        // removed other assertion
        assertEquals(Integer.valueOf(2), arr[1]);
    }

    @Test
    public void testToArray_3_oe() {
        deque.add(ONE);
        deque.add(TWO);
        Object[] arr = deque.toArray();
        // removed other assertion
        // removed other assertion

        arr = deque.toArray(new Integer[0]);
        assertEquals(Integer.valueOf(1), arr[0]);
    }

    @Test
    public void testToArray_4_oe() {
        deque.add(ONE);
        deque.add(TWO);
        Object[] arr = deque.toArray();
        // removed other assertion
        // removed other assertion

        arr = deque.toArray(new Integer[0]);
        // removed other assertion
        assertEquals(Integer.valueOf(2), arr[1]);
    }

    @Test
    public void testToArray_5_oe() {
        deque.add(ONE);
        deque.add(TWO);
        Object[] arr = deque.toArray();
        // removed other assertion
        // removed other assertion

        arr = deque.toArray(new Integer[0]);
        // removed other assertion
        // removed other assertion

        arr = deque.toArray(new Integer[0]);
        assertEquals(Integer.valueOf(1), arr[0]);
    }

    @Test
    public void testToArray_6_oe() {
        deque.add(ONE);
        deque.add(TWO);
        Object[] arr = deque.toArray();
        // removed other assertion
        // removed other assertion

        arr = deque.toArray(new Integer[0]);
        // removed other assertion
        // removed other assertion

        arr = deque.toArray(new Integer[0]);
        // removed other assertion
        assertEquals(Integer.valueOf(2), arr[1]);
    }

}
