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
    public void testAdd_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    deque.add(THREE);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testAddFirst_2_oe() throws Exception {
        deque.addFirst(ONE);
        deque.addFirst(TWO);
        // removed other assertion
        try {
    deque.add(THREE);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testAddLast_2_oe() throws Exception {
        deque.addLast(ONE);
        deque.addLast(TWO);
        // removed other assertion
        try {
    deque.add(THREE);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testDescendingIterator_1_oe() throws Exception {
        try {
    deque.descendingIterator().next();
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testElement_1_oe() throws Exception {
        try {
    deque.element();
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testGetFirst_1_oe() throws Exception {
        try {
    deque.getFirst();
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testGetLast_1_oe() throws Exception {
        try {
    deque.getLast();
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testIterator_1_oe() throws Exception {
        try {
    deque.iterator().next();
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testOffer_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    deque.offer(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOfferFirst_2_oe() throws Exception {
        deque.offerFirst(ONE);
        deque.offerFirst(TWO);
        // removed other assertion
        try {
    deque.offerFirst(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOfferFirstWithTimeout_1_oe() throws InterruptedException {
        try {
    deque.offerFirst(null, TIMEOUT_50_MILLIS);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOfferLast_2_oe() throws Exception {
        deque.offerLast(ONE);
        deque.offerLast(TWO);
        // removed other assertion
        try {
    deque.offerLast(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOfferLastWithTimeout_1_oe() throws InterruptedException {
        try {
    deque.offerLast(null, TIMEOUT_50_MILLIS);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOfferWithTimeout_4_oe() throws InterruptedException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    deque.offer(null, TIMEOUT_50_MILLIS);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPop_1_oe() throws Exception {
        try {
    deque.pop();
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testPop_3_oe() throws Exception {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        try {
     deque.pop(); deque.pop();
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testPush_2_oe() throws Exception {
        deque.push(ONE);
        deque.push(TWO);
        // removed other assertion
        try {
    deque.push(THREE);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testPut_1_oe() throws InterruptedException {
        try {
    deque.put(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPutFirst_1_oe() throws InterruptedException {
        try {
    deque.putFirst(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPutLast_1_oe() throws InterruptedException {
        try {
    deque.putLast(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testRemove_1_oe() {
        assertThrows(NoSuchElementException.class, deque::remove);
    }

    @Test
    public void testRemoveFirst_1_oe() {
        assertThrows(NoSuchElementException.class, deque::removeFirst);
    }

    @Test
    public void testRemoveFirst_3_oe() throws Exception {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        try {
     deque.removeFirst(); deque.removeFirst();
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testRemoveLast_1_oe() {
        assertThrows(NoSuchElementException.class, deque::removeLast);
    }

    @Test
    public void testRemoveLast_3_oe() throws Exception {
        // removed other assertion
        deque.add(ONE);
        deque.add(TWO);
        // removed other assertion
        try {
     deque.removeLast(); deque.removeLast();
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

}
