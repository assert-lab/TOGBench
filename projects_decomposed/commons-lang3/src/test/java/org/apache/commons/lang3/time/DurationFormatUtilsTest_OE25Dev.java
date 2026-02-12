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
    @Test
    public void testBugzilla38401() {
        assertEqualDuration( "0000/00/30 16:00:00 000", new int[] { 2006, 0, 26, 18, 47, 34 },
                             new int[] { 2006, 1, 26, 10, 47, 34 }, "yyyy/MM/dd HH:mm:ss SSS");
    }

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
    @Test
    public void testEdgeDurations() {
        // This test case must use a time zone without DST
        TimeZone.setDefault(FastTimeZone.getGmtTimeZone());
        assertEqualDuration( "01", new int[] { 2006, 0, 15, 0, 0, 0 },
                             new int[] { 2006, 2, 10, 0, 0, 0 }, "MM");
        assertEqualDuration( "12", new int[] { 2005, 0, 15, 0, 0, 0 },
                             new int[] { 2006, 0, 15, 0, 0, 0 }, "MM");
        assertEqualDuration( "12", new int[] { 2005, 0, 15, 0, 0, 0 },
                             new int[] { 2006, 0, 16, 0, 0, 0 }, "MM");
        assertEqualDuration( "11", new int[] { 2005, 0, 15, 0, 0, 0 },
                             new int[] { 2006, 0, 14, 0, 0, 0 }, "MM");

        assertEqualDuration( "01 26", new int[] { 2006, 0, 15, 0, 0, 0 },
                             new int[] { 2006, 2, 10, 0, 0, 0 }, "MM dd");
        assertEqualDuration( "54", new int[] { 2006, 0, 15, 0, 0, 0 },
                             new int[] { 2006, 2, 10, 0, 0, 0 }, "dd");

        assertEqualDuration( "09 12", new int[] { 2006, 1, 20, 0, 0, 0 },
                             new int[] { 2006, 11, 4, 0, 0, 0 }, "MM dd");
        assertEqualDuration( "287", new int[] { 2006, 1, 20, 0, 0, 0 },
                             new int[] { 2006, 11, 4, 0, 0, 0 }, "dd");

        assertEqualDuration( "11 30", new int[] { 2006, 0, 2, 0, 0, 0 },
                             new int[] { 2007, 0, 1, 0, 0, 0 }, "MM dd");
        assertEqualDuration( "364", new int[] { 2006, 0, 2, 0, 0, 0 },
                             new int[] { 2007, 0, 1, 0, 0, 0 }, "dd");

        assertEqualDuration( "12 00", new int[] { 2006, 0, 1, 0, 0, 0 },
                             new int[] { 2007, 0, 1, 0, 0, 0 }, "MM dd");
        assertEqualDuration( "365", new int[] { 2006, 0, 1, 0, 0, 0 },
                             new int[] { 2007, 0, 1, 0, 0, 0 }, "dd");

        assertEqualDuration( "31", new int[] { 2006, 0, 1, 0, 0, 0 },
                new int[] { 2006, 1, 1, 0, 0, 0 }, "dd");

        assertEqualDuration( "92", new int[] { 2005, 9, 1, 0, 0, 0 },
                new int[] { 2006, 0, 1, 0, 0, 0 }, "dd");
        assertEqualDuration( "77", new int[] { 2005, 9, 16, 0, 0, 0 },
                new int[] { 2006, 0, 1, 0, 0, 0 }, "dd");

        // test month larger in start than end
        assertEqualDuration( "136", new int[] { 2005, 9, 16, 0, 0, 0 },
                new int[] { 2006, 2, 1, 0, 0, 0 }, "dd");
        // test when start in leap year
        assertEqualDuration( "136", new int[] { 2004, 9, 16, 0, 0, 0 },
                new int[] { 2005, 2, 1, 0, 0, 0 }, "dd");
        // test when end in leap year
        assertEqualDuration( "137", new int[] { 2003, 9, 16, 0, 0, 0 },
                new int[] { 2004, 2, 1, 0, 0, 0 }, "dd");
        // test when end in leap year but less than end of feb
        assertEqualDuration( "135", new int[] { 2003, 9, 16, 0, 0, 0 },
                new int[] { 2004, 1, 28, 0, 0, 0 }, "dd");

        assertEqualDuration( "364", new int[] { 2007, 0, 2, 0, 0, 0 },
                new int[] { 2008, 0, 1, 0, 0, 0 }, "dd");
        assertEqualDuration( "729", new int[] { 2006, 0, 2, 0, 0, 0 },
                new int[] { 2008, 0, 1, 0, 0, 0 }, "dd");

        assertEqualDuration( "365", new int[] { 2007, 2, 2, 0, 0, 0 },
                new int[] { 2008, 2, 1, 0, 0, 0 }, "dd");
        assertEqualDuration( "333", new int[] { 2007, 1, 2, 0, 0, 0 },
                new int[] { 2008, 0, 1, 0, 0, 0 }, "dd");

        assertEqualDuration( "28", new int[] { 2008, 1, 2, 0, 0, 0 },
                new int[] { 2008, 2, 1, 0, 0, 0 }, "dd");
        assertEqualDuration( "393", new int[] { 2007, 1, 2, 0, 0, 0 },
                new int[] { 2008, 2, 1, 0, 0, 0 }, "dd");

        assertEqualDuration( "369", new int[] { 2004, 0, 29, 0, 0, 0 },
                new int[] { 2005, 1, 1, 0, 0, 0 }, "dd");

        assertEqualDuration( "338", new int[] { 2004, 1, 29, 0, 0, 0 },
                new int[] { 2005, 1, 1, 0, 0, 0 }, "dd");

        assertEqualDuration( "28", new int[] { 2004, 2, 8, 0, 0, 0 },
                new int[] { 2004, 3, 5, 0, 0, 0 }, "dd");

        assertEqualDuration( "48", new int[] { 1992, 1, 29, 0, 0, 0 },
                new int[] { 1996, 1, 29, 0, 0, 0 }, "M");


        // this seems odd - and will fail if I throw it in as a brute force
        // below as it expects the answer to be 12. It's a tricky edge case
        assertEqualDuration( "11", new int[] { 1996, 1, 29, 0, 0, 0 },
                new int[] { 1997, 1, 28, 0, 0, 0 }, "M");
        // again - this seems odd
        assertEqualDuration( "11 28", new int[] { 1996, 1, 29, 0, 0, 0 },
                new int[] { 1997, 1, 28, 0, 0, 0 }, "M d");

    }

    /**
     * Tests that "1 &lt;unit&gt;s" gets converted to "1 &lt;unit&gt;" but that "11 &lt;unit&gt;s" is left alone.
     */

    // -----------------------------------------------------------------------

    // https://issues.apache.org/jira/browse/LANG-281
    @Test
    public void testJiraLang281() {
        assertEqualDuration( "09", new int[] { 2005, 11, 31, 0, 0, 0 },
                             new int[] { 2006, 9, 6, 0, 0, 0 }, "MM");
    }

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
    public void testLowDurations() {
        for (int hr=0; hr < 24; hr++) {
            for (int min=0; min < 60; min++) {
                for (int sec=0; sec < 60; sec++) {
                    assertEqualDuration( hr + ":" + min + ":" + sec,
                                         new int[] { 2000, 0, 1, 0, 0, 0, 0 },
                                         new int[] { 2000, 0, 1, hr, min, sec },
                                         "H:m:s"
                                       );
                }
            }
        }
    }

    @Test
    public void testFormatNegativeDuration_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatDuration(-5000, "S", true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testFormatNegativeDurationHMS_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatDurationHMS(-5000);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testFormatNegativeDurationISO_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatDurationISO(-5000);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testFormatNegativeDurationWords_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatDurationWords(-5000, true, true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testFormatPeriodeStartGreaterEnd_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatPeriod(5000, 2500, "yy/MM");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testFormatPeriodISOStartGreaterEnd_1_oe() throws Exception {
        try {
    DurationFormatUtils.formatPeriodISO(5000, 2000);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testLANG981_1_oe() throws Exception { // unmatched quote char in lexx
        try {
    DurationFormatUtils.lexx("'yMdHms''S");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
