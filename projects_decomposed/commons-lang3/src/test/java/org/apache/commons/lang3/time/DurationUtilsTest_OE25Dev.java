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

package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests {@link DurationUtils}.
 */
public class DurationUtilsTest_OE25Dev {

    @Test
    public void testGetNanosOfMilli_1_oe() {
        assertEquals(0, DurationUtils.getNanosOfMiili(Duration.ZERO));
    }

    @Test
    public void testGetNanosOfMilli_2_oe() {
        // removed other assertion
        assertEquals(1, DurationUtils.getNanosOfMiili(Duration.ofNanos(1)));
    }

    @Test
    public void testGetNanosOfMilli_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(10, DurationUtils.getNanosOfMiili(Duration.ofNanos(10)));
    }

    @Test
    public void testGetNanosOfMilli_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(100, DurationUtils.getNanosOfMiili(Duration.ofNanos(100)));
    }

    @Test
    public void testGetNanosOfMilli_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1_000, DurationUtils.getNanosOfMiili(Duration.ofNanos(1_000)));
    }

    @Test
    public void testGetNanosOfMilli_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10_000, DurationUtils.getNanosOfMiili(Duration.ofNanos(10_000)));
    }

    @Test
    public void testGetNanosOfMilli_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(100_000, DurationUtils.getNanosOfMiili(Duration.ofNanos(100_000)));
    }

    @Test
    public void testGetNanosOfMilli_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, DurationUtils.getNanosOfMiili(Duration.ofNanos(1_000_000)));
    }

    @Test
    public void testGetNanosOfMilli_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, DurationUtils.getNanosOfMiili(Duration.ofNanos(1_000_001)));
    }

    @Test
    public void testIsPositive_1_oe() {
        assertFalse(DurationUtils.isPositive(Duration.ZERO));
    }

    @Test
    public void testIsPositive_2_oe() {
        // removed other assertion
        assertFalse(DurationUtils.isPositive(Duration.ofMillis(-1)));
    }

    @Test
    public void testIsPositive_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(DurationUtils.isPositive(Duration.ofMillis(1)));
    }

    @Test
    public void testLongToIntRangeFit_1_oe() {
        assertEquals(0, DurationUtils.LONG_TO_INT_RANGE.fit(0L));
    }

    @Test
    public void testLongToIntRangeFit_2_oe() {
        // removed other assertion
        //
        assertEquals(Integer.MIN_VALUE, DurationUtils.LONG_TO_INT_RANGE.fit(NumberUtils.LONG_INT_MIN_VALUE));
    }

    @Test
    public void testLongToIntRangeFit_3_oe() {
        // removed other assertion
        //
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, DurationUtils.LONG_TO_INT_RANGE.fit(NumberUtils.LONG_INT_MIN_VALUE - 1));
    }

    @Test
    public void testLongToIntRangeFit_4_oe() {
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, DurationUtils.LONG_TO_INT_RANGE.fit(NumberUtils.LONG_INT_MIN_VALUE - 2));
    }

    @Test
    public void testLongToIntRangeFit_5_oe() {
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, DurationUtils.LONG_TO_INT_RANGE.fit(NumberUtils.LONG_INT_MAX_VALUE));
    }

    @Test
    public void testLongToIntRangeFit_6_oe() {
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, DurationUtils.LONG_TO_INT_RANGE.fit(NumberUtils.LONG_INT_MAX_VALUE + 1));
    }

    @Test
    public void testLongToIntRangeFit_7_oe() {
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, DurationUtils.LONG_TO_INT_RANGE.fit(NumberUtils.LONG_INT_MAX_VALUE + 2));
    }

    @Test
    public void testLongToIntRangeFit_8_oe() {
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //
        assertEquals(Integer.MIN_VALUE, DurationUtils.LONG_TO_INT_RANGE.fit(Long.MIN_VALUE));
    }

    @Test
    public void testLongToIntRangeFit_9_oe() {
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, DurationUtils.LONG_TO_INT_RANGE.fit(Long.MAX_VALUE));
    }

    @Test
    public void testLongToIntRangeFit_10_oe() {
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        //
        assertEquals(Short.MIN_VALUE, DurationUtils.LONG_TO_INT_RANGE.fit((long) Short.MIN_VALUE));
    }

    @Test
    public void testLongToIntRangeFit_11_oe() {
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        assertEquals(Short.MAX_VALUE, DurationUtils.LONG_TO_INT_RANGE.fit((long) Short.MAX_VALUE));
    }

    @Test
    public void testToDuration_1_oe() {
        assertEquals(Duration.ofDays(1), DurationUtils.toDuration(1, TimeUnit.DAYS));
    }

    @Test
    public void testToDuration_2_oe() {
        // removed other assertion
        assertEquals(Duration.ofHours(1), DurationUtils.toDuration(1, TimeUnit.HOURS));
    }

    @Test
    public void testToDuration_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Duration.ofMillis(1), DurationUtils.toDuration(1_000, TimeUnit.MICROSECONDS));
    }

    @Test
    public void testToDuration_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Duration.ofMillis(1), DurationUtils.toDuration(1, TimeUnit.MILLISECONDS));
    }

    @Test
    public void testToDuration_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Duration.ofMinutes(1), DurationUtils.toDuration(1, TimeUnit.MINUTES));
    }

    @Test
    public void testToDuration_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Duration.ofNanos(1), DurationUtils.toDuration(1, TimeUnit.NANOSECONDS));
    }

    @Test
    public void testToDuration_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Duration.ofSeconds(1), DurationUtils.toDuration(1, TimeUnit.SECONDS));
    }

    @Test
    public void testToDuration_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, DurationUtils.toDuration(1, TimeUnit.MILLISECONDS).toMillis());
    }

    @Test
    public void testToDuration_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, DurationUtils.toDuration(-1, TimeUnit.MILLISECONDS).toMillis());
    }

    @Test
    public void testToDuration_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, DurationUtils.toDuration(0, TimeUnit.SECONDS).toMillis());
    }

    @Test
    public void testToMillisInt_1_oe() {
        assertEquals(0, DurationUtils.toMillisInt(Duration.ZERO));
    }

    @Test
    public void testToMillisInt_2_oe() {
        // removed other assertion
        assertEquals(1, DurationUtils.toMillisInt(Duration.ofMillis(1)));
    }

    @Test
    public void testToMillisInt_3_oe() {
        // removed other assertion
        // removed other assertion
        //
        assertEquals(Integer.MIN_VALUE, DurationUtils.toMillisInt(Duration.ofMillis(Integer.MIN_VALUE)));
    }

    @Test
    public void testToMillisInt_4_oe() {
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, DurationUtils.toMillisInt(Duration.ofMillis(Integer.MAX_VALUE)));
    }

    @Test
    public void testToMillisInt_5_oe() {
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, DurationUtils.toMillisInt(Duration.ofMillis(NumberUtils.LONG_INT_MAX_VALUE + 1)));
    }

    @Test
    public void testToMillisInt_6_oe() {
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, DurationUtils.toMillisInt(Duration.ofMillis(NumberUtils.LONG_INT_MAX_VALUE + 2)));
    }

    @Test
    public void testToMillisInt_7_oe() {
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, DurationUtils.toMillisInt(Duration.ofMillis(NumberUtils.LONG_INT_MIN_VALUE - 1)));
    }

    @Test
    public void testToMillisInt_8_oe() {
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, DurationUtils.toMillisInt(Duration.ofMillis(NumberUtils.LONG_INT_MIN_VALUE - 2)));
    }

    @Test
    public void testToMillisInt_9_oe() {
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //
        assertEquals(Integer.MIN_VALUE, DurationUtils.toMillisInt(Duration.ofNanos(Long.MIN_VALUE)));
    }

    @Test
    public void testToMillisInt_10_oe() {
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, DurationUtils.toMillisInt(Duration.ofNanos(Long.MAX_VALUE)));
    }

    @Test
    public void testZeroIfNull_1_oe() {
        assertEquals(Duration.ZERO, DurationUtils.zeroIfNull(null));
    }

    @Test
    public void testZeroIfNull_2_oe() {
        // removed other assertion
        assertEquals(Duration.ofDays(1), DurationUtils.zeroIfNull(Duration.ofDays(1)));
    }

}
