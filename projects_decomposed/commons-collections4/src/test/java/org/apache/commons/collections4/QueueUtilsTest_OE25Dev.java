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

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.queue.PredicatedQueue;
import org.apache.commons.collections4.queue.SynchronizedQueue;
import org.apache.commons.collections4.queue.TransformedQueue;
import org.apache.commons.collections4.queue.UnmodifiableQueue;
import org.junit.Test;

/**
 * Tests for QueueUtils factory methods.
 *
 */
public class QueueUtilsTest_OE25Dev {

    protected Predicate<Object> truePredicate = TruePredicate.truePredicate();
    protected Transformer<Object, Object> nopTransformer = TransformerUtils.nopTransformer();

    // ----------------------------------------------------------------------

    @Test
    public void testSynchronizedQueue_1_oe() {
        final Queue<Object> queue = QueueUtils.synchronizedQueue(new LinkedList<>());
        assertTrue("Returned object should be a SynchronizedQueue.", queue instanceof SynchronizedQueue);
    }

    @Test
    public void testUnmodifiableQueue_1_oe() {
        final Queue<Object> queue = QueueUtils.unmodifiableQueue(new LinkedList<>());
        assertTrue("Returned object should be an UnmodifiableQueue.", queue instanceof UnmodifiableQueue);
    }

    @Test
    public void testUnmodifiableQueue_3_oe() {
        final Queue<Object> queue = QueueUtils.unmodifiableQueue(new LinkedList<>());
        try {
            QueueUtils.unmodifiableQueue(null);
        } catch (final NullPointerException ex) {
        }

        assertSame("UnmodifiableQueue shall not be decorated", queue, QueueUtils.unmodifiableQueue(queue));
    }

    @Test
    public void testPredicatedQueue_1_oe() {
        final Queue<Object> queue = QueueUtils.predicatedQueue(new LinkedList<>(), truePredicate);
        assertTrue("Returned object should be a PredicatedQueue.", queue instanceof PredicatedQueue);
    }

    @Test
    public void testTransformedQueue_1_oe() {
        final Queue<Object> queue = QueueUtils.transformingQueue(new LinkedList<>(), nopTransformer);
        assertTrue("Returned object should be an TransformedQueue.", queue instanceof TransformedQueue);
    }

    @Test
    public void testEmptyQueue_1_oe() {
        final Queue<Object> queue = QueueUtils.emptyQueue();
        assertTrue("Returned object should be an UnmodifiableQueue.", queue instanceof UnmodifiableQueue);
    }

    @Test
    public void testEmptyQueue_2_oe() {
        final Queue<Object> queue = QueueUtils.emptyQueue();
        assertTrue("Returned queue is not empty.", queue.isEmpty());
    }

}
