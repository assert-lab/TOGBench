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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.DefaultLocale;
import org.junitpioneer.jupiter.DefaultTimeZone;

/**
 * Unit tests {@link org.apache.commons.lang3.time.FastDatePrinter}.
 *
 * @since 3.0
 */
public class FastDatePrinterTest_OE25Dev {

    private enum Expected1806 {
        India(INDIA, "+05", "+0530", "+05:30"), Greenwich(GMT, "Z", "Z", "Z"), NewYork(
                NEW_YORK, "-05", "-0500", "-05:00");

        final TimeZone zone;

        final String one;
        final String two;
        final String three;
        Expected1806(final TimeZone zone, final String one, final String two, final String three) {
            this.zone = zone;
            this.one = one;
            this.two = two;
            this.three = three;
        }
    }
    private static final String YYYY_MM_DD = "yyyy/MM/dd";
    private static final TimeZone NEW_YORK = TimeZone.getTimeZone("America/New_York");
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final TimeZone INDIA = TimeZone.getTimeZone("Asia/Calcutta");

    private static final Locale SWEDEN = new Locale("sv", "SE");

    private static Calendar initializeCalendar(final TimeZone tz) {
        final Calendar cal = Calendar.getInstance(tz);
        cal.set(Calendar.YEAR, 2001);
        cal.set(Calendar.MONTH, 1); // not daylight savings
        cal.set(Calendar.DAY_OF_MONTH, 4);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 8);
        cal.set(Calendar.SECOND, 56);
        cal.set(Calendar.MILLISECOND, 235);
        return cal;
    }

    private DatePrinter getDateInstance(final int dateStyle, final Locale locale) {
        return getInstance(FormatCache.getPatternForStyle(Integer.valueOf(dateStyle), null, locale), TimeZone.getDefault(), Locale.getDefault());
    }

    DatePrinter getInstance(final String format) {
        return getInstance(format, TimeZone.getDefault(), Locale.getDefault());
    }

    private DatePrinter getInstance(final String format, final Locale locale) {
        return getInstance(format, TimeZone.getDefault(), locale);
    }

    private DatePrinter getInstance(final String format, final TimeZone timeZone) {
        return getInstance(format, timeZone, Locale.getDefault());
    }

    /**
     * Override this method in derived tests to change the construction of instances
     * @param format the format string to use
     * @param timeZone the time zone to use
     * @param locale the locale to use
     * @return the DatePrinter to use for testing
     */
    protected DatePrinter getInstance(final String format, final TimeZone timeZone, final Locale locale) {
        return new FastDatePrinter(format, timeZone, locale);
    }

    /**
     * According to LANG-916 (https://issues.apache.org/jira/browse/LANG-916),
     * the format method did contain a bug: it did not use the TimeZone data.
     *
     * This method test that the bug is fixed.
     */

    /**
     * Tests that pre-1000AD years get padded with yyyy
     */

    /**
     * Show Bug #39410 is solved
     */

    /**
     * Test case for {@link FastDateParser#FastDateParser(String, TimeZone, Locale)}.
     */

    /**
     * testLowYearPadding showed that the date was buggy
     * This test confirms it, getting 366 back as a date
     */

    @DefaultTimeZone("UTC")
    @Test
    public void testTimeZoneAsZ() {
        final Calendar c = Calendar.getInstance(FastTimeZone.getGmtTimeZone());
        final FastDateFormat noColonFormat = FastDateFormat.getInstance("Z");
        assertEquals("+0000", noColonFormat.format(c));

        final FastDateFormat isoFormat = FastDateFormat.getInstance("ZZ");
        assertEquals("Z", isoFormat.format(c));

        final FastDateFormat colonFormat = FastDateFormat.getInstance("ZZZ");
        assertEquals("+00:00", colonFormat.format(c));
    }

    @Test
    public void test1806Argument_1_oe() throws Exception {
        try {
    getInstance("XXXX");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
