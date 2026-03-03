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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.pool2.PooledObject;
import org.junit.jupiter.api.Test;

/**
 * Tests {@link DefaultPooledObject}.
 */
public class TestDefaultPooledObject_OE25Dev {

    /**
     * JIRA: POOL-279
     *
     * @throws Exception May occur in some failure modes
     */

    @Test
    public void testInitialStateActiveDuration_1_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertFalse(dpo.getActiveDuration().isNegative());
    }

    @Test
    public void testInitialStateActiveDuration_2_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertFalse(dpo.getActiveDuration().isZero());
    }

    @Test
    public void testInitialStateActiveDuration_3_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertThat(1L,lessThanOrEqualTo(2L));// sanity check assertThat(Duration.ZERO,lessThanOrEqualTo(Duration.ZERO.plusNanos(1)));// sanity check assertThat(dpo.getActiveDuration(),lessThanOrEqualTo(dpo.getIdleDuration()));
    }

    @Test
    public void testInitialStateActiveDuration_4_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertThat(dpo.getActiveDuration().toMillis(), lessThanOrEqualTo(dpo.getActiveTimeMillis()));
    }

    @Test
    public void testInitialStateActiveDuration_5_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertThat(dpo.getActiveDuration(), lessThanOrEqualTo(dpo.getActiveTime()));
    }

    @Test
    public void testInitialStateActiveDuration_6_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertThat(dpo.getActiveDuration(), lessThanOrEqualTo(dpo.getIdleTime()));
    }

    @Test
    public void testInitialStateActiveDuration_7_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertThat(dpo.getActiveDuration().toMillis(), lessThanOrEqualTo(dpo.getIdleTimeMillis()));
    }

    @Test
    public void testInitialStateIdleDuration_1_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertFalse(dpo.getIdleDuration().isNegative());
    }

    @Test
    public void testInitialStateIdleDuration_2_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertFalse(dpo.getIdleDuration().isZero());
    }

    @Test
    public void testInitialStateIdleDuration_3_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertThat(dpo.getIdleDuration(), lessThanOrEqualTo(dpo.getActiveDuration()));
    }

    @Test
    public void testInitialStateIdleDuration_4_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertThat(dpo.getIdleDuration(), lessThanOrEqualTo(dpo.getActiveTime()));
    }

    @Test
    public void testInitialStateIdleDuration_5_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        Thread.sleep(200);
        assertThat(dpo.getIdleDuration().toMillis(), lessThanOrEqualTo(dpo.getActiveTimeMillis()));
    }

    @Test
    public void testInitialStateCreateInstant_1_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());


        assertEquals(dpo.getCreateInstant(), dpo.getLastBorrowInstant());
    }

    @Test
    public void testInitialStateCreateInstant_2_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());


        assertEquals(dpo.getCreateInstant(), dpo.getLastReturnInstant());
    }

    @Test
    public void testInitialStateCreateInstant_3_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());


        assertEquals(dpo.getCreateInstant(), dpo.getLastUsedInstant());
    }

    @Test
    public void testInitialStateCreateInstant_4_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());



        assertEquals(dpo.getCreateInstant().toEpochMilli(), dpo.getCreateTime());
    }

    @Test
    public void testInitialStateCreateInstant_5_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());




        assertEquals(dpo.getCreateTime(), dpo.getLastBorrowTime());
    }

    @Test
    public void testInitialStateCreateInstant_6_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());




        assertEquals(dpo.getCreateTime(), dpo.getLastReturnTime());
    }

    @Test
    public void testInitialStateCreateInstant_7_oe() throws InterruptedException {
        final PooledObject<Object> dpo = new DefaultPooledObject<>(new Object());




        assertEquals(dpo.getCreateTime(), dpo.getLastUsedTime());
    }

    @Test
    public void testGetIdleTimeMillis_1_oe() throws Exception {
        final DefaultPooledObject<Object> dpo = new DefaultPooledObject<>(new Object());
        final AtomicBoolean negativeIdleTimeReturned = new AtomicBoolean(false);
        final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3);
        final Runnable allocateAndDeallocateTask = () -> {
            for (int i1 = 0; i1 < 10000; i1++) {
                if (dpo.getIdleDuration().isNegative() || dpo.getIdleTime().isNegative()) {
                    negativeIdleTimeReturned.set(true);
                    break;
                }
                if (dpo.getIdleDuration().isNegative() || dpo.getIdleTime().isNegative()) {
                    negativeIdleTimeReturned.set(true);
                    break;
                }
            }
            dpo.allocate();
            for (int i2 = 0; i2 < 10000; i2++) {
                if (dpo.getIdleDuration().isNegative() || dpo.getIdleTime().isNegative()) {
                    negativeIdleTimeReturned.set(true);
                    break;
                }
            }
            dpo.deallocate();
        };
        final Runnable getIdleTimeTask = () -> {
            for (int i = 0; i < 10000; i++) {
                if (dpo.getIdleDuration().isNegative() || dpo.getIdleTime().isNegative()) {
                    negativeIdleTimeReturned.set(true);
                    break;
                }
            }
        };
        final double probabilityOfAllocationTask = 0.7;
        final List<Future<?>> futures = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            final Runnable randomTask = Math.random() < probabilityOfAllocationTask ? allocateAndDeallocateTask : getIdleTimeTask;
            futures.add(executor.submit(randomTask));
        }
        for (final Future<?> future : futures) {
            future.get();
        }
        assertFalse(negativeIdleTimeReturned.get(), "DefaultPooledObject.getIdleTimeMillis() returned a negative value");
    }

}