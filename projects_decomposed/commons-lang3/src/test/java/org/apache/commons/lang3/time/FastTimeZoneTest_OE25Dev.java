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

import java.util.TimeZone;

import org.junit.jupiter.api.Test;

/**
 * Tests for FastTimeZone
 */
public class FastTimeZoneTest_OE25Dev {

    private static final int HOURS_23 = 23 * 60 * 60 * 1000;
    private static final int HOURS_2 = 2 * 60 * 60 * 1000;
    private static final int MINUTES_59 = 59 * 60 * 1000;
    private static final int MINUTES_5 = 5 * 60 * 1000;

    @Test
    public void testBareGmt_1_oe() {
        assertEquals(FastTimeZone.getGmtTimeZone(), FastTimeZone.getTimeZone("GMT"));
    }

    @Test
    public void testGetGmtTimeZone_1_oe() {
        assertEquals(0, FastTimeZone.getGmtTimeZone().getRawOffset());
    }

    @Test
    public void testGmtPrefix_1_oe() {
        assertEquals(HOURS_23, FastTimeZone.getGmtTimeZone("GMT+23:00").getRawOffset());
    }

    @Test
    public void testGmtPrefix_2_oe() {
        assertEquals(-HOURS_23, FastTimeZone.getGmtTimeZone("GMT-23:00").getRawOffset());
    }

    @Test
    public void testHoursColonMinutes_1_oe() {
        assertEquals(HOURS_23, FastTimeZone.getGmtTimeZone("23:00").getRawOffset());
    }

    @Test
    public void testHoursColonMinutes_2_oe() {
        assertEquals(HOURS_2, FastTimeZone.getGmtTimeZone("2:00").getRawOffset());
    }

    @Test
    public void testHoursColonMinutes_3_oe() {
        assertEquals(MINUTES_59, FastTimeZone.getGmtTimeZone("00:59").getRawOffset());
    }

    @Test
    public void testHoursColonMinutes_4_oe() {
        assertEquals(MINUTES_5, FastTimeZone.getGmtTimeZone("00:5").getRawOffset());
    }

    @Test
    public void testHoursColonMinutes_5_oe() {
        assertEquals(HOURS_23+MINUTES_59, FastTimeZone.getGmtTimeZone("23:59").getRawOffset());
    }

    @Test
    public void testHoursColonMinutes_6_oe() {
        assertEquals(HOURS_2+MINUTES_5, FastTimeZone.getGmtTimeZone("2:5").getRawOffset());
    }

    @Test
    public void testHoursMinutes_1_oe() {
        assertEquals(HOURS_23, FastTimeZone.getGmtTimeZone("2300").getRawOffset());
    }

    @Test
    public void testHoursMinutes_2_oe() {
        assertEquals(HOURS_2, FastTimeZone.getGmtTimeZone("0200").getRawOffset());
    }

    @Test
    public void testHoursMinutes_3_oe() {
        assertEquals(MINUTES_59, FastTimeZone.getGmtTimeZone("0059").getRawOffset());
    }

    @Test
    public void testHoursMinutes_4_oe() {
        assertEquals(MINUTES_5, FastTimeZone.getGmtTimeZone("0005").getRawOffset());
    }

    @Test
    public void testHoursMinutes_5_oe() {
        assertEquals(HOURS_23+MINUTES_59, FastTimeZone.getGmtTimeZone("2359").getRawOffset());
    }

    @Test
    public void testHoursMinutes_6_oe() {
        assertEquals(HOURS_2+MINUTES_5, FastTimeZone.getGmtTimeZone("0205").getRawOffset());
    }

    @Test
    public void testOlson_1_oe() {
        assertEquals(TimeZone.getTimeZone("America/New_York"), FastTimeZone.getTimeZone("America/New_York"));
    }

    @Test
    public void testSign_1_oe() {
        assertEquals(HOURS_23, FastTimeZone.getGmtTimeZone("+23:00").getRawOffset());
    }

    @Test
    public void testSign_2_oe() {
        assertEquals(HOURS_2, FastTimeZone.getGmtTimeZone("+2:00").getRawOffset());
    }

    @Test
    public void testSign_3_oe() {
        assertEquals(-HOURS_23, FastTimeZone.getGmtTimeZone("-23:00").getRawOffset());
    }

    @Test
    public void testSign_4_oe() {
        assertEquals(-HOURS_2, FastTimeZone.getGmtTimeZone("-2:00").getRawOffset());
    }

    @Test
    public void testUTC_1_oe() {
        assertEquals(FastTimeZone.getGmtTimeZone(), FastTimeZone.getTimeZone("UTC"));
    }

    @Test
    public void testZ_1_oe() {
        assertEquals(FastTimeZone.getGmtTimeZone(), FastTimeZone.getTimeZone("Z"));
    }

    @Test
    public void testZeroOffsetsReturnSingleton_1_oe() {
        assertEquals(FastTimeZone.getGmtTimeZone(), FastTimeZone.getTimeZone("+0"));
    }

    @Test
    public void testZeroOffsetsReturnSingleton_2_oe() {
        assertEquals(FastTimeZone.getGmtTimeZone(), FastTimeZone.getTimeZone("-0"));
    }

}
