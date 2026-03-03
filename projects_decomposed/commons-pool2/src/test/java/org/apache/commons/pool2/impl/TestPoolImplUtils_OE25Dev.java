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

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.junit.jupiter.api.Test;

public class TestPoolImplUtils_OE25Dev {

    @SuppressWarnings("unused")
    private abstract static class FactoryAB<A, B> extends BasePooledObjectFactory<B> {
        // empty by design
    }

    private abstract static class FactoryBA<A, B> extends FactoryAB<B, A> {
        // empty by design
    }

    private abstract static class FactoryC<C> extends FactoryBA<C, String> {
        // empty by design
    }

    @SuppressWarnings("unused")
    private abstract static class FactoryDE<D, E> extends FactoryC<D> {
        // empty by design
    }

    private abstract static class FactoryF<F> extends FactoryDE<Long, F> {
        // empty by design
    }

    private static class NotSimpleFactory extends FactoryF<Integer> {
        @Override
        public Long create() throws Exception {
            return null;
        }

        @Override
        public PooledObject<Long> wrap(final Long obj) {
            return null;
        }
    }

    private static class SimpleFactory extends BasePooledObjectFactory<String> {
        @Override
        public String create() throws Exception {
            return null;
        }

        @Override
        public PooledObject<String> wrap(final String obj) {
            return null;
        }
    }

    private static final Instant INSTANT_1 = Instant.ofEpochMilli(1);

    private static final Instant INSTANT_0 = Instant.ofEpochMilli(0);

    @Test
    public void testFactoryTypeNotSimple_1_oe() {
        final Class<?> result = PoolImplUtils.getFactoryType(NotSimpleFactory.class);
        assertEquals(Long.class, result);
    }

    @Test
    public void testFactoryTypeSimple_1_oe() {
        final Class<?> result = PoolImplUtils.getFactoryType(SimpleFactory.class);
        assertEquals(String.class, result);
    }

    @Test
    public void testMaxInstants_1_oe() {
        assertEquals(INSTANT_1, PoolImplUtils.max(INSTANT_0, INSTANT_1));
    }

    @Test
    public void testMaxInstants_2_oe() {
        assertEquals(INSTANT_1, PoolImplUtils.max(INSTANT_1, INSTANT_0));
    }

    @Test
    public void testMaxInstants_3_oe() {
        assertEquals(INSTANT_1, PoolImplUtils.max(INSTANT_1, INSTANT_1));
    }

    @Test
    public void testMaxInstants_4_oe() {
        assertEquals(INSTANT_0, PoolImplUtils.max(INSTANT_0, INSTANT_0));
    }

    @Test
    public void testMinInstants_1_oe() {
        assertEquals(INSTANT_0, PoolImplUtils.min(INSTANT_0, INSTANT_1));
    }

    @Test
    public void testMinInstants_2_oe() {
        assertEquals(INSTANT_0, PoolImplUtils.min(INSTANT_1, INSTANT_0));
    }

    @Test
    public void testMinInstants_3_oe() {
        assertEquals(INSTANT_1, PoolImplUtils.min(INSTANT_1, INSTANT_1));
    }

    @Test
    public void testMinInstants_4_oe() {
        assertEquals(INSTANT_0, PoolImplUtils.min(INSTANT_0, INSTANT_0));
    }

    @Test
    public void testToChronoUnit_1_oe() {
        assertEquals(ChronoUnit.NANOS, PoolImplUtils.toChronoUnit(TimeUnit.NANOSECONDS));
    }

    @Test
    public void testToChronoUnit_2_oe() {
        assertEquals(ChronoUnit.MICROS, PoolImplUtils.toChronoUnit(TimeUnit.MICROSECONDS));
    }

    @Test
    public void testToChronoUnit_3_oe() {
        assertEquals(ChronoUnit.MILLIS, PoolImplUtils.toChronoUnit(TimeUnit.MILLISECONDS));
    }

    @Test
    public void testToChronoUnit_4_oe() {
        assertEquals(ChronoUnit.SECONDS, PoolImplUtils.toChronoUnit(TimeUnit.SECONDS));
    }

    @Test
    public void testToChronoUnit_5_oe() {
        assertEquals(ChronoUnit.MINUTES, PoolImplUtils.toChronoUnit(TimeUnit.MINUTES));
    }

    @Test
    public void testToChronoUnit_6_oe() {
        assertEquals(ChronoUnit.HOURS, PoolImplUtils.toChronoUnit(TimeUnit.HOURS));
    }

    @Test
    public void testToChronoUnit_7_oe() {
        assertEquals(ChronoUnit.DAYS, PoolImplUtils.toChronoUnit(TimeUnit.DAYS));
    }

    @Test
    public void testToDuration_1_oe() {
        assertEquals(Duration.ZERO, PoolImplUtils.toDuration(0, TimeUnit.MILLISECONDS));
    }

    @Test
    public void testToDuration_2_oe() {
        assertEquals(Duration.ofMillis(1), PoolImplUtils.toDuration(1, TimeUnit.MILLISECONDS));
    }

    @Test
    public void testToDuration_3_oe() {
        for (final TimeUnit tu : TimeUnit.values()) {
            assertEquals(Duration.ZERO, PoolImplUtils.toDuration(0, tu));
    }
    }

}
