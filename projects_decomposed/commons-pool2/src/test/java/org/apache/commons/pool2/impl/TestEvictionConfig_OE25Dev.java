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

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link EvictionConfig}.
 */
public class TestEvictionConfig_OE25Dev {

    @Test
    public void testConstructor1s_1_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ofMillis(1), Duration.ofMillis(1), 1);

        assertEquals(1, config.getIdleEvictDuration().toMillis());
    }

    @Test
    public void testConstructor1s_2_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ofMillis(1), Duration.ofMillis(1), 1);

        // removed other assertion
        assertEquals(1, config.getIdleEvictTime());
    }

    @Test
    public void testConstructor1s_3_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ofMillis(1), Duration.ofMillis(1), 1);

        // removed other assertion
        // removed other assertion
        assertEquals(1, config.getIdleEvictTimeDuration().toMillis());
    }

    @Test
    public void testConstructor1s_4_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ofMillis(1), Duration.ofMillis(1), 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, config.getIdleSoftEvictDuration().toMillis());
    }

    @Test
    public void testConstructor1s_5_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ofMillis(1), Duration.ofMillis(1), 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, config.getIdleSoftEvictTime());
    }

    @Test
    public void testConstructor1s_6_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ofMillis(1), Duration.ofMillis(1), 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, config.getIdleSoftEvictTimeDuration().toMillis());
    }

    @Test
    public void testConstructor1s_7_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ofMillis(1), Duration.ofMillis(1), 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, config.getMinIdle());
    }

    @Test
    public void testConstructorZerosDurations_1_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ZERO, Duration.ZERO, 0);

        assertEquals(Long.MAX_VALUE, config.getIdleEvictDuration().toMillis());
    }

    @Test
    public void testConstructorZerosDurations_2_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ZERO, Duration.ZERO, 0);

        // removed other assertion
        assertEquals(Long.MAX_VALUE, config.getIdleEvictTime());
    }

    @Test
    public void testConstructorZerosDurations_3_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ZERO, Duration.ZERO, 0);

        // removed other assertion
        // removed other assertion
        assertEquals(Long.MAX_VALUE, config.getIdleEvictTimeDuration().toMillis());
    }

    @Test
    public void testConstructorZerosDurations_4_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ZERO, Duration.ZERO, 0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.MAX_VALUE, config.getIdleSoftEvictDuration().toMillis());
    }

    @Test
    public void testConstructorZerosDurations_5_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ZERO, Duration.ZERO, 0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.MAX_VALUE, config.getIdleSoftEvictTime());
    }

    @Test
    public void testConstructorZerosDurations_6_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ZERO, Duration.ZERO, 0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.MAX_VALUE, config.getIdleSoftEvictTimeDuration().toMillis());
    }

    @Test
    public void testConstructorZerosDurations_7_oe() {
        final EvictionConfig config = new EvictionConfig(Duration.ZERO, Duration.ZERO, 0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, config.getMinIdle());
    }

    @Test
    public void testConstructorZerosMillis_1_oe() {
        @SuppressWarnings("deprecation")
        final EvictionConfig config = new EvictionConfig(0, 0, 0);

        assertEquals(Long.MAX_VALUE, config.getIdleEvictDuration().toMillis());
    }

    @Test
    public void testConstructorZerosMillis_2_oe() {
        @SuppressWarnings("deprecation")
        final EvictionConfig config = new EvictionConfig(0, 0, 0);

        // removed other assertion
        assertEquals(Long.MAX_VALUE, config.getIdleEvictTime());
    }

    @Test
    public void testConstructorZerosMillis_3_oe() {
        @SuppressWarnings("deprecation")
        final EvictionConfig config = new EvictionConfig(0, 0, 0);

        // removed other assertion
        // removed other assertion
        assertEquals(Long.MAX_VALUE, config.getIdleEvictTimeDuration().toMillis());
    }

    @Test
    public void testConstructorZerosMillis_4_oe() {
        @SuppressWarnings("deprecation")
        final EvictionConfig config = new EvictionConfig(0, 0, 0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.MAX_VALUE, config.getIdleSoftEvictDuration().toMillis());
    }

    @Test
    public void testConstructorZerosMillis_5_oe() {
        @SuppressWarnings("deprecation")
        final EvictionConfig config = new EvictionConfig(0, 0, 0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.MAX_VALUE, config.getIdleSoftEvictTime());
    }

    @Test
    public void testConstructorZerosMillis_6_oe() {
        @SuppressWarnings("deprecation")
        final EvictionConfig config = new EvictionConfig(0, 0, 0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.MAX_VALUE, config.getIdleSoftEvictTimeDuration().toMillis());
    }

    @Test
    public void testConstructorZerosMillis_7_oe() {
        @SuppressWarnings("deprecation")
        final EvictionConfig config = new EvictionConfig(0, 0, 0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, config.getMinIdle());
    }

}
