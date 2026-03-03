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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * TestCase for DurationFormatUtils.
 */
public class DurationFormatUtilsTest_OE25Dev {

    private static final int FOUR_YEARS = 365 * 3 + 366;

    private void assertEqualDuration(final String expected, final int[] start, final int[] end, final String format) {
        assertEqualDuration(null, expected, start, end, format);
    }

    private void assertEqualDuration(final String message, final String expected, final int[] start, final int[] end, final String format) {
        final Calendar cal1 = Calendar.getInstance();
        cal1.set(start[0], start[1], start[2], start[3], start[4], start[5]);
        cal1.set(Calendar.MILLISECOND, 0);
        final Calendar cal2 = Calendar.getInstance();
        cal2.set(end[0], end[1], end[2], end[3], end[4], end[5]);
        cal2.set(Calendar.MILLISECOND, 0);
        final long milli1 = cal1.getTime().getTime();
        final long milli2 = cal2.getTime().getTime();
        final String result = DurationFormatUtils.formatPeriod(milli1, milli2, format);
        if (message == null) {
            assertEquals(expected, result);
        } else {
            assertEquals(expected, result, message);
        }
    }

    private void bruteForce(final int year, final int month, final int day, final String format, final int calendarType) {
        final String msg = year + "-" + month + "-" + day + " to ";
        final Calendar c = Calendar.getInstance();
        c.set(year, month, day, 0, 0, 0);
        final int[] array1 = new int[] { year, month, day, 0, 0, 0 };
        final int[] array2 = new int[] { year, month, day, 0, 0, 0 };
        for (int i=0; i < FOUR_YEARS; i++) {
            array2[0] = c.get(Calendar.YEAR);
            array2[1] = c.get(Calendar.MONTH);
            array2[2] = c.get(Calendar.DAY_OF_MONTH);
            final String tmpMsg = msg + array2[0] + "-" + array2[1] + "-" + array2[2] + " at ";
            assertEqualDuration( tmpMsg + i, Integer.toString(i), array1, array2, format );
            c.add(calendarType, 1);
        }
    }

    // https://issues.apache.org/bugzilla/show_bug.cgi?id=38401

    // -----------------------------------------------------------------------

    @Test
    public void testDurationsByBruteForce() {
        bruteForce(2006, 0, 1, "d", Calendar.DAY_OF_MONTH);
        bruteForce(2006, 0, 2, "d", Calendar.DAY_OF_MONTH);
        bruteForce(2007, 1, 2, "d", Calendar.DAY_OF_MONTH);
        bruteForce(2004, 1, 29, "d", Calendar.DAY_OF_MONTH);
        bruteForce(1996, 1, 29, "d", Calendar.DAY_OF_MONTH);

        bruteForce(1969, 1, 28, "M", Calendar.MONTH);  // tests for 48 years
        //bruteForce(1996, 1, 29, "M", Calendar.MONTH);  // this will fail
    }

    // Attempting to test edge cases in DurationFormatUtils.formatPeriod

    /**
     * Tests that "1 &lt;unit&gt;s" gets converted to "1 &lt;unit&gt;" but that "11 &lt;unit&gt;s" is left alone.
     */

    // -----------------------------------------------------------------------

    // https://issues.apache.org/jira/browse/LANG-281

    // Takes a minute to run, so generally turned off
//    public void testBrutally() {
//        Calendar c = Calendar.getInstance();
//        c.set(2004, 0, 1, 0, 0, 0);
//        for (int i=0; i < FOUR_YEARS; i++) {
//            bruteForce(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), "d", Calendar.DAY_OF_MONTH );
//            c.add(Calendar.DAY_OF_MONTH, 1);
//        }
//    }
    // Testing the under a day range in DurationFormatUtils.formatPeriod

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new DurationFormatUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        final Constructor<?>[] cons = DurationFormatUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        final Constructor<?>[] cons = DurationFormatUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        final Constructor<?>[] cons = DurationFormatUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(DurationFormatUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        final Constructor<?>[] cons = DurationFormatUtils.class.getDeclaredConstructors();
        assertFalse(Modifier.isFinal(DurationFormatUtils.class.getModifiers()));
    }

    @Test
    public void testFormatDuration_1_oe() {
        long duration = 0;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "y"));
    }

    @Test
    public void testFormatDuration_2_oe() {
        long duration = 0;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "M"));
    }

    @Test
    public void testFormatDuration_3_oe() {
        long duration = 0;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "d"));
    }

    @Test
    public void testFormatDuration_4_oe() {
        long duration = 0;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "H"));
    }

    @Test
    public void testFormatDuration_5_oe() {
        long duration = 0;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "m"));
    }

    @Test
    public void testFormatDuration_6_oe() {
        long duration = 0;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "s"));
    }

    @Test
    public void testFormatDuration_7_oe() {
        long duration = 0;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "S"));
    }

    @Test
    public void testFormatDuration_8_oe() {
        long duration = 0;
        assertEquals("0000", DurationFormatUtils.formatDuration(duration, "SSSS"));
    }

    @Test
    public void testFormatDuration_9_oe() {
        long duration = 0;
        assertEquals("0000", DurationFormatUtils.formatDuration(duration, "yyyy"));
    }

    @Test
    public void testFormatDuration_10_oe() {
        long duration = 0;
        assertEquals("0000", DurationFormatUtils.formatDuration(duration, "yyMM"));
    }

    @Test
    public void testFormatDuration_11_oe() {
        long duration = 0;

        duration = 60 * 1000;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "y"));
    }

    @Test
    public void testFormatDuration_12_oe() {
        long duration = 0;

        duration = 60 * 1000;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "M"));
    }

    @Test
    public void testFormatDuration_13_oe() {
        long duration = 0;

        duration = 60 * 1000;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "d"));
    }

    @Test
    public void testFormatDuration_14_oe() {
        long duration = 0;

        duration = 60 * 1000;
        assertEquals("0", DurationFormatUtils.formatDuration(duration, "H"));
    }

    @Test
    public void testFormatDuration_15_oe() {
        long duration = 0;

        duration = 60 * 1000;
        assertEquals("1", DurationFormatUtils.formatDuration(duration, "m"));
    }

    @Test
    public void testFormatDuration_16_oe() {
        long duration = 0;

        duration = 60 * 1000;
        assertEquals("60", DurationFormatUtils.formatDuration(duration, "s"));
    }

    @Test
    public void testFormatDuration_17_oe() {
        long duration = 0;

        duration = 60 * 1000;
        assertEquals("60000", DurationFormatUtils.formatDuration(duration, "S"));
    }

    @Test
    public void testFormatDuration_18_oe() {
        long duration = 0;

        duration = 60 * 1000;
        assertEquals("01:00", DurationFormatUtils.formatDuration(duration, "mm:ss"));
    }

    @Test
    public void testFormatDuration_19_oe() {
        long duration = 0;

        duration = 60 * 1000;

        final Calendar base = Calendar.getInstance();
        base.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        base.set(Calendar.MILLISECOND, 0);

        final Calendar cal = Calendar.getInstance();
        cal.set(2003, Calendar.FEBRUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        duration = cal.getTime().getTime() - base.getTime().getTime(); // duration from 2000-01-01 to cal
        final int days = 366 + 365 + 365 + 31;
        assertEquals("0 0 " + days, DurationFormatUtils.formatDuration(duration, "y M d"));
    }

    @Test
    public void testFormatDurationHMS_1_oe() {
        long time = 0;
        assertEquals("00:00:00.000", DurationFormatUtils.formatDurationHMS(time));
    }

    @Test
    public void testFormatDurationHMS_2_oe() {
        long time = 0;

        time = 1;
        assertEquals("00:00:00.001", DurationFormatUtils.formatDurationHMS(time));
    }

    @Test
    public void testFormatDurationHMS_3_oe() {
        long time = 0;

        time = 1;

        time = 15;
        assertEquals("00:00:00.015", DurationFormatUtils.formatDurationHMS(time));
    }

    @Test
    public void testFormatDurationHMS_4_oe() {
        long time = 0;

        time = 1;

        time = 15;

        time = 165;
        assertEquals("00:00:00.165", DurationFormatUtils.formatDurationHMS(time));
    }

    @Test
    public void testFormatDurationHMS_5_oe() {
        long time = 0;

        time = 1;

        time = 15;

        time = 165;

        time = 1675;
        assertEquals("00:00:01.675", DurationFormatUtils.formatDurationHMS(time));
    }

    @Test
    public void testFormatDurationHMS_6_oe() {
        long time = 0;

        time = 1;

        time = 15;

        time = 165;

        time = 1675;

        time = 13465;
        assertEquals("00:00:13.465", DurationFormatUtils.formatDurationHMS(time));
    }

    @Test
    public void testFormatDurationHMS_7_oe() {
        long time = 0;

        time = 1;

        time = 15;

        time = 165;

        time = 1675;

        time = 13465;

        time = 72789;
        assertEquals("00:01:12.789", DurationFormatUtils.formatDurationHMS(time));
    }

    @Test
    public void testFormatDurationHMS_8_oe() {
        long time = 0;

        time = 1;

        time = 15;

        time = 165;

        time = 1675;

        time = 13465;

        time = 72789;

        time = 12789 + 32 * 60000;
        assertEquals("00:32:12.789", DurationFormatUtils.formatDurationHMS(time));
    }

    @Test
    public void testFormatDurationHMS_9_oe() {
        long time = 0;

        time = 1;

        time = 15;

        time = 165;

        time = 1675;

        time = 13465;

        time = 72789;

        time = 12789 + 32 * 60000;

        time = 12789 + 62 * 60000;
        assertEquals("01:02:12.789", DurationFormatUtils.formatDurationHMS(time));
    }

    @Test
    public void testFormatDurationISO_1_oe() {
        assertEquals("P0Y0M0DT0H0M0.000S", DurationFormatUtils.formatDurationISO(0L));
    }

    @Test
    public void testFormatDurationISO_2_oe() {
        assertEquals("P0Y0M0DT0H0M0.001S", DurationFormatUtils.formatDurationISO(1L));
    }

    @Test
    public void testFormatDurationISO_3_oe() {
        assertEquals("P0Y0M0DT0H0M0.010S", DurationFormatUtils.formatDurationISO(10L));
    }

    @Test
    public void testFormatDurationISO_4_oe() {
        assertEquals("P0Y0M0DT0H0M0.100S", DurationFormatUtils.formatDurationISO(100L));
    }

    @Test
    public void testFormatDurationISO_5_oe() {
        assertEquals("P0Y0M0DT0H1M15.321S", DurationFormatUtils.formatDurationISO(75321L));
    }

    @Test
    public void testFormatDurationPluralWords_1_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        assertEquals("0 days 0 hours 0 minutes 1 second", text);
    }

    @Test
    public void testFormatDurationPluralWords_2_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        assertEquals("0 days 0 hours 0 minutes 2 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_3_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);
        assertEquals("0 days 0 hours 0 minutes 11 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_4_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        assertEquals("0 days 0 hours 1 minute 0 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_5_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        assertEquals("0 days 0 hours 2 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_6_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 11, false, false);
        assertEquals("0 days 0 hours 11 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_7_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute + oneSecond, false, false);
        assertEquals("0 days 0 hours 1 minute 1 second", text);
    }

    @Test
    public void testFormatDurationPluralWords_8_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneHour, false, false);
        assertEquals("0 days 1 hour 0 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_9_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneHour, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 2, false, false);
        assertEquals("0 days 2 hours 0 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_10_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneHour, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 11, false, false);
        assertEquals("0 days 11 hours 0 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_11_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneHour, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour + oneMinute + oneSecond, false, false);
        assertEquals("0 days 1 hour 1 minute 1 second", text);
    }

    @Test
    public void testFormatDurationPluralWords_12_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneHour, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour + oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneDay, false, false);
        assertEquals("1 day 0 hours 0 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_13_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneHour, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour + oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneDay, false, false);
        text = DurationFormatUtils.formatDurationWords(oneDay * 2, false, false);
        assertEquals("2 days 0 hours 0 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_14_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneHour, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour + oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneDay, false, false);
        text = DurationFormatUtils.formatDurationWords(oneDay * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneDay * 11, false, false);
        assertEquals("11 days 0 hours 0 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationPluralWords_15_oe() {
        final long oneSecond = 1000;
        final long oneMinute = oneSecond * 60;
        final long oneHour = oneMinute * 60;
        final long oneDay = oneHour * 24;
        String text;

        text = DurationFormatUtils.formatDurationWords(oneSecond, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneSecond * 11, false, false);

        text = DurationFormatUtils.formatDurationWords(oneMinute, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneHour, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneHour + oneMinute + oneSecond, false, false);

        text = DurationFormatUtils.formatDurationWords(oneDay, false, false);
        text = DurationFormatUtils.formatDurationWords(oneDay * 2, false, false);
        text = DurationFormatUtils.formatDurationWords(oneDay * 11, false, false);
        text = DurationFormatUtils.formatDurationWords(oneDay + oneHour + oneMinute + oneSecond, false, false);
        assertEquals("1 day 1 hour 1 minute 1 second", text);
    }

    @Test
    public void testFormatDurationWords_1_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        assertEquals("50 seconds", text);
    }

    @Test
    public void testFormatDurationWords_2_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        assertEquals("1 minute 5 seconds", text);
    }

    @Test
    public void testFormatDurationWords_3_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        assertEquals("2 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationWords_4_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        assertEquals("2 minutes 1 second", text);
    }

    @Test
    public void testFormatDurationWords_5_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        assertEquals("1 hour 12 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationWords_6_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);
        assertEquals("1 day 0 hours 0 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationWords_7_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        assertEquals("50 seconds", text);
    }

    @Test
    public void testFormatDurationWords_8_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        assertEquals("1 minute 5 seconds", text);
    }

    @Test
    public void testFormatDurationWords_9_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        assertEquals("2 minutes", text);
    }

    @Test
    public void testFormatDurationWords_10_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        assertEquals("2 minutes 1 second", text);
    }

    @Test
    public void testFormatDurationWords_11_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        assertEquals("1 hour 12 minutes", text);
    }

    @Test
    public void testFormatDurationWords_12_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);
        assertEquals("1 day", text);
    }

    @Test
    public void testFormatDurationWords_13_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        assertEquals("0 days 0 hours 0 minutes 50 seconds", text);
    }

    @Test
    public void testFormatDurationWords_14_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        assertEquals("0 days 0 hours 1 minute 5 seconds", text);
    }

    @Test
    public void testFormatDurationWords_15_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        assertEquals("0 days 0 hours 2 minutes", text);
    }

    @Test
    public void testFormatDurationWords_16_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        assertEquals("0 days 0 hours 2 minutes 1 second", text);
    }

    @Test
    public void testFormatDurationWords_17_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, true);
        assertEquals("0 days 1 hour 12 minutes", text);
    }

    @Test
    public void testFormatDurationWords_18_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, false, true);
        assertEquals("1 day", text);
    }

    @Test
    public void testFormatDurationWords_19_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, false, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, false);
        assertEquals("0 days 0 hours 0 minutes 50 seconds", text);
    }

    @Test
    public void testFormatDurationWords_20_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, false, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, false);
        assertEquals("0 days 0 hours 1 minute 5 seconds", text);
    }

    @Test
    public void testFormatDurationWords_21_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, false, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, false);
        assertEquals("0 days 0 hours 2 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationWords_22_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, false, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, false);
        assertEquals("0 days 0 hours 2 minutes 1 second", text);
    }

    @Test
    public void testFormatDurationWords_23_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, false, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, false);
        assertEquals("0 days 1 hour 12 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationWords_24_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, false, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000 + 72 * 60 * 1000, false, false);
        assertEquals("1 day 1 hour 12 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationWords_25_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, false, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000 + 72 * 60 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(2 * 24 * 60 * 60 * 1000 + 72 * 60 * 1000, false, false);
        assertEquals("2 days 1 hour 12 minutes 0 seconds", text);
    }

    @Test
    public void testFormatDurationWords_26_oe() {
        String text;

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, false);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, true, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, true, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, true);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000, false, true);

        text = DurationFormatUtils.formatDurationWords(50 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(65 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(120 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(121 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(72 * 60 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(24 * 60 * 60 * 1000 + 72 * 60 * 1000, false, false);
        text = DurationFormatUtils.formatDurationWords(2 * 24 * 60 * 60 * 1000 + 72 * 60 * 1000, false, false);
        for (int i = 2; i < 31; i++) {
            text = DurationFormatUtils.formatDurationWords(i * 24 * 60 * 60 * 1000L, false, false);
            assertEquals(i + " days 0 hours 0 minutes 0 seconds", text);
    }
    }

    @Test
    public void testFormatNegativeDuration_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatDuration(-5000, "S", true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testFormatNegativeDurationHMS_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatDurationHMS(-5000);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testFormatNegativeDurationISO_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatDurationISO(-5000);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testFormatNegativeDurationWords_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatDurationWords(-5000, true, true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testFormatPeriod_1_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();

        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time1970, "y"));
    }

    @Test
    public void testFormatPeriod_2_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();

        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time1970, "M"));
    }

    @Test
    public void testFormatPeriod_3_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();

        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time1970, "d"));
    }

    @Test
    public void testFormatPeriod_4_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();

        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time1970, "H"));
    }

    @Test
    public void testFormatPeriod_5_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();

        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time1970, "m"));
    }

    @Test
    public void testFormatPeriod_6_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();

        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time1970, "s"));
    }

    @Test
    public void testFormatPeriod_7_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();

        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time1970, "S"));
    }

    @Test
    public void testFormatPeriod_8_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();

        assertEquals("0000", DurationFormatUtils.formatPeriod(time1970, time1970, "SSSS"));
    }

    @Test
    public void testFormatPeriod_9_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();

        assertEquals("0000", DurationFormatUtils.formatPeriod(time1970, time1970, "yyyy"));
    }

    @Test
    public void testFormatPeriod_10_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();

        assertEquals("0000", DurationFormatUtils.formatPeriod(time1970, time1970, "yyMM"));
    }

    @Test
    public void testFormatPeriod_11_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;
        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time, "y"));
    }

    @Test
    public void testFormatPeriod_12_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;
        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time, "M"));
    }

    @Test
    public void testFormatPeriod_13_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;
        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time, "d"));
    }

    @Test
    public void testFormatPeriod_14_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;
        assertEquals("0", DurationFormatUtils.formatPeriod(time1970, time, "H"));
    }

    @Test
    public void testFormatPeriod_15_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;
        assertEquals("1", DurationFormatUtils.formatPeriod(time1970, time, "m"));
    }

    @Test
    public void testFormatPeriod_16_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;
        assertEquals("60", DurationFormatUtils.formatPeriod(time1970, time, "s"));
    }

    @Test
    public void testFormatPeriod_17_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;
        assertEquals("60000", DurationFormatUtils.formatPeriod(time1970, time, "S"));
    }

    @Test
    public void testFormatPeriod_18_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;
        assertEquals("01:00", DurationFormatUtils.formatPeriod(time1970, time, "mm:ss"));
    }

    @Test
    public void testFormatPeriod_19_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("36", DurationFormatUtils.formatPeriod(time1970, time, "yM"));
    }

    @Test
    public void testFormatPeriod_20_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("3 years 6 months", DurationFormatUtils.formatPeriod(time1970, time, "y' years 'M' months'"));
    }

    @Test
    public void testFormatPeriod_21_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("03/06", DurationFormatUtils.formatPeriod(time1970, time, "yy/MM"));
    }

    @Test
    public void testFormatPeriod_22_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1973, Calendar.NOVEMBER, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("310", DurationFormatUtils.formatPeriod(time1970, time, "yM"));
    }

    @Test
    public void testFormatPeriod_23_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1973, Calendar.NOVEMBER, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("3 years 10 months", DurationFormatUtils.formatPeriod(time1970, time, "y' years 'M' months'"));
    }

    @Test
    public void testFormatPeriod_24_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1973, Calendar.NOVEMBER, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("03/10", DurationFormatUtils.formatPeriod(time1970, time, "yy/MM"));
    }

    @Test
    public void testFormatPeriod_25_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1973, Calendar.NOVEMBER, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1974, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("40", DurationFormatUtils.formatPeriod(time1970, time, "yM"));
    }

    @Test
    public void testFormatPeriod_26_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1973, Calendar.NOVEMBER, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1974, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("4 years 0 months", DurationFormatUtils.formatPeriod(time1970, time, "y' years 'M' months'"));
    }

    @Test
    public void testFormatPeriod_27_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1973, Calendar.NOVEMBER, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1974, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("04/00", DurationFormatUtils.formatPeriod(time1970, time, "yy/MM"));
    }

    @Test
    public void testFormatPeriod_28_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1973, Calendar.NOVEMBER, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1974, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("48", DurationFormatUtils.formatPeriod(time1970, time, "M"));
    }

    @Test
    public void testFormatPeriod_29_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1973, Calendar.NOVEMBER, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1974, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("48", DurationFormatUtils.formatPeriod(time1970, time, "MM"));
    }

    @Test
    public void testFormatPeriod_30_oe() {
        final Calendar cal1970 = Calendar.getInstance();
        cal1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        cal1970.set(Calendar.MILLISECOND, 0);
        final long time1970 = cal1970.getTime().getTime();


        long time = time1970 + 60 * 1000;

        final Calendar cal = Calendar.getInstance();
        cal.set(1973, Calendar.JULY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1973, Calendar.NOVEMBER, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();

        cal.set(1974, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime().getTime();
        assertEquals("048", DurationFormatUtils.formatPeriod(time1970, time, "MMM"));
    }

    @Test
    public void testFormatPeriodeStartGreaterEnd_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatPeriod(5000, 2500, "yy/MM");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testFormatPeriodISO_1_oe() {
        final TimeZone timeZone = TimeZone.getTimeZone("GMT-3");
        final Calendar base = Calendar.getInstance(timeZone);
        base.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        base.set(Calendar.MILLISECOND, 0);

        final Calendar cal = Calendar.getInstance(timeZone);
        cal.set(2002, Calendar.FEBRUARY, 23, 9, 11, 12);
        cal.set(Calendar.MILLISECOND, 1);
        String text;
        text = DateFormatUtils.format(cal, DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern(), timeZone);
        assertEquals("2002-02-23T09:11:12-03:00", text);
    }

    @Test
    public void testFormatPeriodISO_2_oe() {
        final TimeZone timeZone = TimeZone.getTimeZone("GMT-3");
        final Calendar base = Calendar.getInstance(timeZone);
        base.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        base.set(Calendar.MILLISECOND, 0);

        final Calendar cal = Calendar.getInstance(timeZone);
        cal.set(2002, Calendar.FEBRUARY, 23, 9, 11, 12);
        cal.set(Calendar.MILLISECOND, 1);
        String text;
        text = DateFormatUtils.format(cal, DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern(), timeZone);
        text = DurationFormatUtils.formatPeriod(base.getTime().getTime(), cal.getTime().getTime(),
                DurationFormatUtils.ISO_EXTENDED_FORMAT_PATTERN, false, timeZone);
        assertEquals("P32Y1M22DT9H11M12.001S", text);
    }

    @Test
    public void testFormatPeriodISO_3_oe() {
        final TimeZone timeZone = TimeZone.getTimeZone("GMT-3");
        final Calendar base = Calendar.getInstance(timeZone);
        base.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        base.set(Calendar.MILLISECOND, 0);

        final Calendar cal = Calendar.getInstance(timeZone);
        cal.set(2002, Calendar.FEBRUARY, 23, 9, 11, 12);
        cal.set(Calendar.MILLISECOND, 1);
        String text;
        text = DateFormatUtils.format(cal, DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern(), timeZone);
        text = DurationFormatUtils.formatPeriod(base.getTime().getTime(), cal.getTime().getTime(),
                DurationFormatUtils.ISO_EXTENDED_FORMAT_PATTERN, false, timeZone);
        cal.set(1971, Calendar.FEBRUARY, 3, 10, 30, 0);
        cal.set(Calendar.MILLISECOND, 0);
        text = DurationFormatUtils.formatPeriod(base.getTime().getTime(), cal.getTime().getTime(),
                DurationFormatUtils.ISO_EXTENDED_FORMAT_PATTERN, false, timeZone);
        assertEquals("P1Y1M2DT10H30M0.000S", text);
    }

    @Test
    public void testFormatPeriodISOMethod_1_oe() {
        assertEquals("P0Y0M0DT0H0M0.000S", DurationFormatUtils.formatPeriodISO(0L, 0L));
    }

    @Test
    public void testFormatPeriodISOMethod_2_oe() {
        assertEquals("P0Y0M0DT0H0M1.000S", DurationFormatUtils.formatPeriodISO(0L, 1000L));
    }

    @Test
    public void testFormatPeriodISOMethod_3_oe() {
        assertEquals("P0Y0M0DT0H1M1.000S", DurationFormatUtils.formatPeriodISO(0L, 61000L));
    }

    @Test
    public void testFormatPeriodISOStartGreaterEnd_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatPeriodISO(5000, 2000);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testLANG815_1_oe() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2012, Calendar.JULY, 30, 0, 0, 0);
        final long startMillis = calendar.getTimeInMillis();

        calendar.set(2012, Calendar.SEPTEMBER, 8);
        final long endMillis = calendar.getTimeInMillis();

        assertEquals("1 9", DurationFormatUtils.formatPeriod(startMillis, endMillis, "M d"));
    }

    @Test
    public void testLANG981_1_oe() throws Exception { // unmatched quote char in lexx
        try {
    DurationFormatUtils.lexx("'yMdHms''S");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testLANG982_1_oe() { // More than 3 millisecond digits following a second
        assertEquals("61.999", DurationFormatUtils.formatDuration(61999, "s.S"));
    }

    @Test
    public void testLANG982_2_oe() { // More than 3 millisecond digits following a second
        assertEquals("1 1999", DurationFormatUtils.formatDuration(61999, "m S"));
    }

    @Test
    public void testLANG982_3_oe() { // More than 3 millisecond digits following a second
        assertEquals("61.999", DurationFormatUtils.formatDuration(61999, "s.SSS"));
    }

    @Test
    public void testLANG982_4_oe() { // More than 3 millisecond digits following a second
        assertEquals("1 1999", DurationFormatUtils.formatDuration(61999, "m SSS"));
    }

    @Test
    public void testLANG982_5_oe() { // More than 3 millisecond digits following a second
        assertEquals("61.0999", DurationFormatUtils.formatDuration(61999, "s.SSSS"));
    }

    @Test
    public void testLANG982_6_oe() { // More than 3 millisecond digits following a second
        assertEquals("1 1999", DurationFormatUtils.formatDuration(61999, "m SSSS"));
    }

    @Test
    public void testLANG982_7_oe() { // More than 3 millisecond digits following a second
        assertEquals("61.00999", DurationFormatUtils.formatDuration(61999, "s.SSSSS"));
    }

    @Test
    public void testLANG982_8_oe() { // More than 3 millisecond digits following a second
        assertEquals("1 01999", DurationFormatUtils.formatDuration(61999, "m SSSSS"));
    }

    @Test
    public void testLANG984_1_oe() { // Long durations
        assertEquals("0", DurationFormatUtils.formatDuration(0, "S"));
    }

    @Test
    public void testLANG984_2_oe() { // Long durations
        assertEquals(Integer.toString(Integer.MAX_VALUE), DurationFormatUtils.formatDuration(Integer.MAX_VALUE, "S"));
    }

    @Test
    public void testLANG984_3_oe() { // Long durations
        long maxIntPlus=Integer.MAX_VALUE;
        maxIntPlus++;
        assertEquals(Long.toString(maxIntPlus), DurationFormatUtils.formatDuration(maxIntPlus, "S"));
    }

    @Test
    public void testLANG984_4_oe() { // Long durations
        long maxIntPlus=Integer.MAX_VALUE;
        maxIntPlus++;
        assertEquals(Long.toString(Long.MAX_VALUE), DurationFormatUtils.formatDuration(Long.MAX_VALUE, "S"));
    }

    @Test
    public void testLexx_1_oe() {
        assertArrayEquals(new DurationFormatUtils.Token[]{ new DurationFormatUtils.Token(DurationFormatUtils.y, 1), new DurationFormatUtils.Token(DurationFormatUtils.M, 1), new DurationFormatUtils.Token(DurationFormatUtils.d, 1), new DurationFormatUtils.Token(DurationFormatUtils.H, 1), new DurationFormatUtils.Token(DurationFormatUtils.m, 1), new DurationFormatUtils.Token(DurationFormatUtils.s, 1), new DurationFormatUtils.Token(DurationFormatUtils.S, 1)}, DurationFormatUtils.lexx("yMdHmsS"));
    }

    @Test
    public void testLexx_2_oe() {

        assertArrayEquals(new DurationFormatUtils.Token[]{ new DurationFormatUtils.Token(DurationFormatUtils.H, 2), new DurationFormatUtils.Token(new StringBuilder(":"), 1), new DurationFormatUtils.Token(DurationFormatUtils.m, 2), new DurationFormatUtils.Token(new StringBuilder(":"), 1), new DurationFormatUtils.Token(DurationFormatUtils.s, 2), new DurationFormatUtils.Token(new StringBuilder("."), 1), new DurationFormatUtils.Token(DurationFormatUtils.S, 3)}, DurationFormatUtils.lexx("HH:mm:ss.SSS"));
    }

    @Test
    public void testLexx_3_oe() {


        assertArrayEquals(new DurationFormatUtils.Token[]{ new DurationFormatUtils.Token(new StringBuilder("P"), 1), new DurationFormatUtils.Token(DurationFormatUtils.y, 4), new DurationFormatUtils.Token(new StringBuilder("Y"), 1), new DurationFormatUtils.Token(DurationFormatUtils.M, 1), new DurationFormatUtils.Token(new StringBuilder("M"), 1), new DurationFormatUtils.Token(DurationFormatUtils.d, 1), new DurationFormatUtils.Token(new StringBuilder("DT"), 1), new DurationFormatUtils.Token(DurationFormatUtils.H, 1), new DurationFormatUtils.Token(new StringBuilder("H"), 1), new DurationFormatUtils.Token(DurationFormatUtils.m, 1), new DurationFormatUtils.Token(new StringBuilder("M"), 1), new DurationFormatUtils.Token(DurationFormatUtils.s, 1), new DurationFormatUtils.Token(new StringBuilder("."), 1), new DurationFormatUtils.Token(DurationFormatUtils.S, 3), new DurationFormatUtils.Token(new StringBuilder("S"), 1)}, DurationFormatUtils .lexx(DurationFormatUtils.ISO_EXTENDED_FORMAT_PATTERN));
    }

    @Test
    public void testLexx_4_oe() {



        final DurationFormatUtils.Token token = new DurationFormatUtils.Token(DurationFormatUtils.y, 4);
        assertNotEquals(token, new Object(), "Token equal to non-Token class. ");
    }

    @Test
    public void testLexx_5_oe() {



        final DurationFormatUtils.Token token = new DurationFormatUtils.Token(DurationFormatUtils.y, 4);
        assertNotEquals(token, new DurationFormatUtils.Token(new Object()), "Token equal to Token with wrong value class. ");
    }

    @Test
    public void testLexx_6_oe() {



        final DurationFormatUtils.Token token = new DurationFormatUtils.Token(DurationFormatUtils.y, 4);
        assertNotEquals(token, new DurationFormatUtils.Token(DurationFormatUtils.y, 1), "Token equal to Token with different count. ");
    }

    @Test
    public void testLexx_7_oe() {



        final DurationFormatUtils.Token token = new DurationFormatUtils.Token(DurationFormatUtils.y, 4);
        final DurationFormatUtils.Token numToken = new DurationFormatUtils.Token(Integer.valueOf(1), 4);
        assertEquals(numToken, numToken, "Token with Number value not equal to itself. ");
    }

@Test
    public void testBugzilla38401_1_oe() {
        assertEqualDuration( "0000/00/30 16:00:00 000", new int[] { 2006, 0, 26, 18, 47, 34 }, new int[] { 2006, 1, 26, 10, 47, 34 }, "yyyy/MM/dd HH:mm:ss SSS");
    }

@Test
    public void testEdgeDurations_1_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());
        assertEqualDuration( "01", new int[] { 2006, 0, 15, 0, 0, 0 }, new int[] { 2006, 2, 10, 0, 0, 0 }, "MM");
    }

@Test
    public void testEdgeDurations_2_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());
        assertEqualDuration( "12", new int[] { 2005, 0, 15, 0, 0, 0 }, new int[] { 2006, 0, 15, 0, 0, 0 }, "MM");
    }

@Test
    public void testEdgeDurations_3_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());
        assertEqualDuration( "12", new int[] { 2005, 0, 15, 0, 0, 0 }, new int[] { 2006, 0, 16, 0, 0, 0 }, "MM");
    }

@Test
    public void testEdgeDurations_4_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());
        assertEqualDuration( "11", new int[] { 2005, 0, 15, 0, 0, 0 }, new int[] { 2006, 0, 14, 0, 0, 0 }, "MM");
    }

@Test
    public void testEdgeDurations_5_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());

        assertEqualDuration( "01 26", new int[] { 2006, 0, 15, 0, 0, 0 }, new int[] { 2006, 2, 10, 0, 0, 0 }, "MM dd");
    }

@Test
    public void testEdgeDurations_6_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());

        assertEqualDuration( "54", new int[] { 2006, 0, 15, 0, 0, 0 }, new int[] { 2006, 2, 10, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_7_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());


        assertEqualDuration( "09 12", new int[] { 2006, 1, 20, 0, 0, 0 }, new int[] { 2006, 11, 4, 0, 0, 0 }, "MM dd");
    }

@Test
    public void testEdgeDurations_8_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());


        assertEqualDuration( "287", new int[] { 2006, 1, 20, 0, 0, 0 }, new int[] { 2006, 11, 4, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_9_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());



        assertEqualDuration( "11 30", new int[] { 2006, 0, 2, 0, 0, 0 }, new int[] { 2007, 0, 1, 0, 0, 0 }, "MM dd");
    }

@Test
    public void testEdgeDurations_10_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());



        assertEqualDuration( "364", new int[] { 2006, 0, 2, 0, 0, 0 }, new int[] { 2007, 0, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_11_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());




        assertEqualDuration( "12 00", new int[] { 2006, 0, 1, 0, 0, 0 }, new int[] { 2007, 0, 1, 0, 0, 0 }, "MM dd");
    }

@Test
    public void testEdgeDurations_12_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());




        assertEqualDuration( "365", new int[] { 2006, 0, 1, 0, 0, 0 }, new int[] { 2007, 0, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_13_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());





        assertEqualDuration( "31", new int[] { 2006, 0, 1, 0, 0, 0 }, new int[] { 2006, 1, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_14_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());






        assertEqualDuration( "92", new int[] { 2005, 9, 1, 0, 0, 0 }, new int[] { 2006, 0, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_15_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());






        assertEqualDuration( "77", new int[] { 2005, 9, 16, 0, 0, 0 }, new int[] { 2006, 0, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_16_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());







        assertEqualDuration( "136", new int[] { 2005, 9, 16, 0, 0, 0 }, new int[] { 2006, 2, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_17_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());







        assertEqualDuration( "136", new int[] { 2004, 9, 16, 0, 0, 0 }, new int[] { 2005, 2, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_18_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());







        assertEqualDuration( "137", new int[] { 2003, 9, 16, 0, 0, 0 }, new int[] { 2004, 2, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_19_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());







        assertEqualDuration( "135", new int[] { 2003, 9, 16, 0, 0, 0 }, new int[] { 2004, 1, 28, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_20_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());








        assertEqualDuration( "364", new int[] { 2007, 0, 2, 0, 0, 0 }, new int[] { 2008, 0, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_21_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());








        assertEqualDuration( "729", new int[] { 2006, 0, 2, 0, 0, 0 }, new int[] { 2008, 0, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_22_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());









        assertEqualDuration( "365", new int[] { 2007, 2, 2, 0, 0, 0 }, new int[] { 2008, 2, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_23_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());









        assertEqualDuration( "333", new int[] { 2007, 1, 2, 0, 0, 0 }, new int[] { 2008, 0, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_24_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());










        assertEqualDuration( "28", new int[] { 2008, 1, 2, 0, 0, 0 }, new int[] { 2008, 2, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_25_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());










        assertEqualDuration( "393", new int[] { 2007, 1, 2, 0, 0, 0 }, new int[] { 2008, 2, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_26_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());











        assertEqualDuration( "369", new int[] { 2004, 0, 29, 0, 0, 0 }, new int[] { 2005, 1, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_27_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());












        assertEqualDuration( "338", new int[] { 2004, 1, 29, 0, 0, 0 }, new int[] { 2005, 1, 1, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_28_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());













        assertEqualDuration( "28", new int[] { 2004, 2, 8, 0, 0, 0 }, new int[] { 2004, 3, 5, 0, 0, 0 }, "dd");
    }

@Test
    public void testEdgeDurations_29_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());














        assertEqualDuration( "48", new int[] { 1992, 1, 29, 0, 0, 0 }, new int[] { 1996, 1, 29, 0, 0, 0 }, "M");
    }

@Test
    public void testEdgeDurations_30_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());
















        assertEqualDuration( "11", new int[] { 1996, 1, 29, 0, 0, 0 }, new int[] { 1997, 1, 28, 0, 0, 0 }, "M");
    }

@Test
    public void testEdgeDurations_31_oe() {
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());
















        assertEqualDuration( "11 28", new int[] { 1996, 1, 29, 0, 0, 0 }, new int[] { 1997, 1, 28, 0, 0, 0 }, "M d");
    }

@Test
    public void testJiraLang281_1_oe() {
        assertEqualDuration( "09", new int[] { 2005, 11, 31, 0, 0, 0 }, new int[] { 2006, 9, 6, 0, 0, 0 }, "MM");
    }

@Test
    public void testLowDurations_1_oe() {
        for (int hr=0; hr < 24; hr++) {
            for (int min=0; min < 60; min++) {
                for (int sec=0; sec < 60; sec++) {
                    assertEqualDuration( hr + ":" + min + ":" + sec, new int[] { 2000, 0, 1, 0, 0, 0, 0 }, new int[] { 2000, 0, 1, hr, min, sec }, "H:m:s" );
    }
    }
    }
    }

}
