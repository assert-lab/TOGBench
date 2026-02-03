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
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.DefaultLocale;
import org.junitpioneer.jupiter.DefaultTimeZone;

/**
 * Unit tests {@link org.apache.commons.lang3.time.FastDateFormat}.
 *
 * @since 2.0
 */
public class FastDateFormatTest_OE25Dev {
    private static final int NTHREADS = 10;

    private static final int NROUNDS = 10000;

    private AtomicLongArray measureTime(final Format printer, final Format parser) throws InterruptedException {
        final ExecutorService pool = Executors.newFixedThreadPool(NTHREADS);
        final AtomicInteger failures = new AtomicInteger(0);
        final AtomicLongArray totalElapsed = new AtomicLongArray(2);
        try {
            for (int i = 0; i < NTHREADS; ++i) {
                pool.submit(() -> {
                    for (int j = 0; j < NROUNDS; ++j) {
                        try {
                            final Date date = new Date();

                            final long t0Millis = System.currentTimeMillis();
                            final String formattedDate = printer.format(date);
                            totalElapsed.addAndGet(0, System.currentTimeMillis() - t0Millis);

                            final long t1Millis = System.currentTimeMillis();
                            final Object pd = parser.parseObject(formattedDate);
                            totalElapsed.addAndGet(1, System.currentTimeMillis() - t1Millis);

                            if (!date.equals(pd)) {
                                failures.incrementAndGet();
                            }
                        } catch (final Exception e) {
                            failures.incrementAndGet();
                            e.printStackTrace();
                        }
                    }
                });
            }
        } finally {
            pool.shutdown();
            // depending on the performance of the machine used to run the parsing,
            // the tests can run for a while. It should however complete within
            // 30 seconds. Might need increase on very slow machines.
            if (!pool.awaitTermination(30, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                fail("did not complete tasks");
            }
        }
        assertEquals(0, failures.get());
        return totalElapsed;
    }

    /*
     * Only the cache methods need to be tested here.
     * The print methods are tested by {@link FastDateFormat_PrinterTest}
     * and the parse methods are tested by {@link FastDateFormat_ParserTest}
     */

    @Test
    public void testLANG_1152() {
        final TimeZone utc = FastTimeZone.getGmtTimeZone();
        final Date date = new Date(Long.MAX_VALUE);

        String dateAsString = FastDateFormat.getInstance("yyyy-MM-dd", utc, Locale.US).format(date);
        assertEquals("292278994-08-17", dateAsString);

        dateAsString = FastDateFormat.getInstance("dd/MM/yyyy", utc, Locale.US).format(date);
        assertEquals("17/08/292278994", dateAsString);
    }
    @Test
    public void testLANG_1267() {
        FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    }

    /**
     * According to LANG-954 (https://issues.apache.org/jira/browse/LANG-954) this is broken in Android 2.1.
     */
    @Test
    public void testLANG_954() {
        final String pattern = "yyyy-MM-dd'T'";
        FastDateFormat.getInstance(pattern);
    }

    @Test
    public void testParseSync() throws InterruptedException {
        final String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        final SimpleDateFormat inner = new SimpleDateFormat(pattern);
        final Format sdf= new Format() {
            private static final long serialVersionUID = 1L;

            @Override
            public StringBuffer format(final Object obj,
                    final StringBuffer toAppendTo,
                    final FieldPosition fieldPosition) {
                synchronized(this) {
                    return inner.format(obj, toAppendTo, fieldPosition);
                }
            }

            @Override
            public Object parseObject(final String source, final ParsePosition pos) {
                synchronized(this) {
                    return inner.parseObject(source, pos);
                }
            }
        };
        final AtomicLongArray sdfTime= measureTime(sdf, sdf);

        final Format fdf = FastDateFormat.getInstance(pattern);
        final AtomicLongArray fdfTime= measureTime(fdf, fdf);

        //System.out.println(">>FastDateFormatTest_OE25Dev: FastDatePrinter:"+fdfTime.get(0)+"  SimpleDateFormat:"+sdfTime.get(0));
        //System.out.println(">>FastDateFormatTest_OE25Dev: FastDateParser:"+fdfTime.get(1)+"  SimpleDateFormat:"+sdfTime.get(1));
    }

    @Test
    public void test_changeDefault_Locale_DateInstance_1_oe() {
        final FastDateFormat format1 = FastDateFormat.getDateInstance(FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateInstance(FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateInstance(FastDateFormat.FULL);

        assertSame(Locale.GERMANY, format1.getLocale());
    }

    @Test
    public void test_changeDefault_Locale_DateInstance_2_oe() {
        final FastDateFormat format1 = FastDateFormat.getDateInstance(FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateInstance(FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateInstance(FastDateFormat.FULL);

        // removed other assertion
        assertEquals(Locale.US, format2.getLocale());
    }

    @Test
    public void test_changeDefault_Locale_DateInstance_3_oe() {
        final FastDateFormat format1 = FastDateFormat.getDateInstance(FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateInstance(FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateInstance(FastDateFormat.FULL);

        // removed other assertion
        // removed other assertion
        assertSame(Locale.GERMANY, format3.getLocale());
    }

    @Test
    public void test_changeDefault_Locale_DateInstance_4_oe() {
        final FastDateFormat format1 = FastDateFormat.getDateInstance(FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateInstance(FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateInstance(FastDateFormat.FULL);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(format1, format2);
    }

    @Test
    public void test_changeDefault_Locale_DateInstance_5_oe() {
        final FastDateFormat format1 = FastDateFormat.getDateInstance(FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateInstance(FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateInstance(FastDateFormat.FULL);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(format2, format3);
    }

    @Test
    public void test_changeDefault_Locale_DateTimeInstance_1_oe() {
        final FastDateFormat format1 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);

        assertSame(Locale.GERMANY, format1.getLocale());
    }

    @Test
    public void test_changeDefault_Locale_DateTimeInstance_2_oe() {
        final FastDateFormat format1 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);

        // removed other assertion
        assertEquals(Locale.US, format2.getLocale());
    }

    @Test
    public void test_changeDefault_Locale_DateTimeInstance_3_oe() {
        final FastDateFormat format1 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);

        // removed other assertion
        // removed other assertion
        assertSame(Locale.GERMANY, format3.getLocale());
    }

    @Test
    public void test_changeDefault_Locale_DateTimeInstance_4_oe() {
        final FastDateFormat format1 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(format1, format2);
    }

    @Test
    public void test_changeDefault_Locale_DateTimeInstance_5_oe() {
        final FastDateFormat format1 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateTimeInstance(FastDateFormat.FULL, FastDateFormat.FULL);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(format2, format3);
    }

    @Test
    public void test_getInstance_1_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance();
        final FastDateFormat format2 = FastDateFormat.getInstance();
        assertSame(format1, format2);
    }

    @Test
    public void test_getInstance_String_1_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format2 = FastDateFormat.getInstance("MM-DD-yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM-DD-yyyy");

        assertNotSame(format1, format2);
    }

    @Test
    public void test_getInstance_String_2_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format2 = FastDateFormat.getInstance("MM-DD-yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM-DD-yyyy");

        // removed other assertion
        assertSame(format2, format3);
    }

    @Test
    public void test_getInstance_String_3_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format2 = FastDateFormat.getInstance("MM-DD-yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM-DD-yyyy");

        // removed other assertion
        // removed other assertion
        assertEquals("MM/DD/yyyy", format1.getPattern());
    }

    @Test
    public void test_getInstance_String_4_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format2 = FastDateFormat.getInstance("MM-DD-yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM-DD-yyyy");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TimeZone.getDefault(), format1.getTimeZone());
    }

    @Test
    public void test_getInstance_String_5_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format2 = FastDateFormat.getInstance("MM-DD-yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM-DD-yyyy");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TimeZone.getDefault(), format2.getTimeZone());
    }

    @Test
    public void test_getInstance_String_Locale_1_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);

        assertNotSame(format1, format2);
    }

    @Test
    public void test_getInstance_String_Locale_2_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);

        // removed other assertion
        assertSame(format1, format3);
    }

    @Test
    public void test_getInstance_String_Locale_3_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);

        // removed other assertion
        // removed other assertion
        assertEquals(Locale.GERMANY, format1.getLocale());
    }

    @Test
    public void test_getInstance_String_TimeZone_1_oe() {

        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"));
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format4 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format5 = FastDateFormat.getInstance("MM-DD-yyyy", TimeZone.getDefault());
        final FastDateFormat format6 = FastDateFormat.getInstance("MM-DD-yyyy");

        assertNotSame(format1, format2);
    }

    @Test
    public void test_getInstance_String_TimeZone_2_oe() {

        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"));
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format4 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format5 = FastDateFormat.getInstance("MM-DD-yyyy", TimeZone.getDefault());
        final FastDateFormat format6 = FastDateFormat.getInstance("MM-DD-yyyy");

        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Atlantic/Reykjavik"), format1.getTimeZone());
    }

    @Test
    public void test_getInstance_String_TimeZone_3_oe() {

        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"));
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format4 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format5 = FastDateFormat.getInstance("MM-DD-yyyy", TimeZone.getDefault());
        final FastDateFormat format6 = FastDateFormat.getInstance("MM-DD-yyyy");

        // removed other assertion
        // removed other assertion
        assertEquals(TimeZone.getDefault(), format2.getTimeZone());
    }

    @Test
    public void test_getInstance_String_TimeZone_4_oe() {

        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"));
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format4 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format5 = FastDateFormat.getInstance("MM-DD-yyyy", TimeZone.getDefault());
        final FastDateFormat format6 = FastDateFormat.getInstance("MM-DD-yyyy");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(format3, format4);
    }

    @Test
    public void test_getInstance_String_TimeZone_5_oe() {

        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"));
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format4 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format5 = FastDateFormat.getInstance("MM-DD-yyyy", TimeZone.getDefault());
        final FastDateFormat format6 = FastDateFormat.getInstance("MM-DD-yyyy");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(format3, format5);
    }

    @Test
    public void test_getInstance_String_TimeZone_6_oe() {

        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"));
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format4 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format5 = FastDateFormat.getInstance("MM-DD-yyyy", TimeZone.getDefault());
        final FastDateFormat format6 = FastDateFormat.getInstance("MM-DD-yyyy");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(format4, format6);
    }

    @Test
    public void test_getInstance_String_TimeZone_Locale_1_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"), Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getDefault(), Locale.GERMANY);

        assertNotSame(format1, format2);
    }

    @Test
    public void test_getInstance_String_TimeZone_Locale_2_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"), Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getDefault(), Locale.GERMANY);

        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Atlantic/Reykjavik"), format1.getTimeZone());
    }

    @Test
    public void test_getInstance_String_TimeZone_Locale_3_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"), Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getDefault(), Locale.GERMANY);

        // removed other assertion
        // removed other assertion
        assertEquals(TimeZone.getDefault(), format2.getTimeZone());
    }

    @Test
    public void test_getInstance_String_TimeZone_Locale_4_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"), Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getDefault(), Locale.GERMANY);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TimeZone.getDefault(), format3.getTimeZone());
    }

    @Test
    public void test_getInstance_String_TimeZone_Locale_5_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"), Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getDefault(), Locale.GERMANY);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Locale.GERMANY, format1.getLocale());
    }

    @Test
    public void test_getInstance_String_TimeZone_Locale_6_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"), Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getDefault(), Locale.GERMANY);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Locale.GERMANY, format2.getLocale());
    }

    @Test
    public void test_getInstance_String_TimeZone_Locale_7_oe() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"), Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getDefault(), Locale.GERMANY);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Locale.GERMANY, format3.getLocale());
    }

    @Test
    public void testCheckDefaults_1_oe() {
        final FastDateFormat format = FastDateFormat.getInstance();
        final FastDateFormat medium = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT);
        assertEquals(medium, format);
    }

    @Test
    public void testCheckDefaults_2_oe() {
        final FastDateFormat format = FastDateFormat.getInstance();
        final FastDateFormat medium = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT);
        // removed other assertion

        final SimpleDateFormat sdf = new SimpleDateFormat();
        assertEquals(sdf.toPattern(), format.getPattern());
    }

    @Test
    public void testCheckDefaults_3_oe() {
        final FastDateFormat format = FastDateFormat.getInstance();
        final FastDateFormat medium = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT);
        // removed other assertion

        final SimpleDateFormat sdf = new SimpleDateFormat();
        // removed other assertion

        assertEquals(Locale.getDefault(), format.getLocale());
    }

    @Test
    public void testCheckDefaults_4_oe() {
        final FastDateFormat format = FastDateFormat.getInstance();
        final FastDateFormat medium = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT);
        // removed other assertion

        final SimpleDateFormat sdf = new SimpleDateFormat();
        // removed other assertion

        // removed other assertion
        assertEquals(TimeZone.getDefault(), format.getTimeZone());
    }

    @Test
    public void testCheckDifferingStyles_1_oe() {
        final FastDateFormat shortShort = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat shortLong = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.LONG, Locale.US);
        final FastDateFormat longShort = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat longLong = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.LONG, Locale.US);

        assertNotEquals(shortShort, shortLong);
    }

    @Test
    public void testCheckDifferingStyles_2_oe() {
        final FastDateFormat shortShort = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat shortLong = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.LONG, Locale.US);
        final FastDateFormat longShort = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat longLong = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.LONG, Locale.US);

        // removed other assertion
        assertNotEquals(shortShort, longShort);
    }

    @Test
    public void testCheckDifferingStyles_3_oe() {
        final FastDateFormat shortShort = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat shortLong = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.LONG, Locale.US);
        final FastDateFormat longShort = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat longLong = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.LONG, Locale.US);

        // removed other assertion
        // removed other assertion
        assertNotEquals(shortShort, longLong);
    }

    @Test
    public void testCheckDifferingStyles_4_oe() {
        final FastDateFormat shortShort = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat shortLong = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.LONG, Locale.US);
        final FastDateFormat longShort = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat longLong = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.LONG, Locale.US);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(shortLong, longShort);
    }

    @Test
    public void testCheckDifferingStyles_5_oe() {
        final FastDateFormat shortShort = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat shortLong = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.LONG, Locale.US);
        final FastDateFormat longShort = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat longLong = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.LONG, Locale.US);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(shortLong, longLong);
    }

    @Test
    public void testCheckDifferingStyles_6_oe() {
        final FastDateFormat shortShort = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat shortLong = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.LONG, Locale.US);
        final FastDateFormat longShort = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat longLong = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.LONG, Locale.US);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(longShort, longLong);
    }

    @Test
    public void testDateDefaults_1_oe() {
        assertEquals(FastDateFormat.getDateInstance(FastDateFormat.LONG,Locale.CANADA),FastDateFormat.getDateInstance(FastDateFormat.LONG,TimeZone.getDefault(),Locale.CANADA));
    }

    @Test
    public void testDateDefaults_2_oe() {
        // removed other assertion

        assertEquals(FastDateFormat.getDateInstance(FastDateFormat.LONG,TimeZone.getTimeZone("America/New_York")),FastDateFormat.getDateInstance(FastDateFormat.LONG,TimeZone.getTimeZone("America/New_York"),Locale.getDefault()));
    }

    @Test
    public void testDateDefaults_3_oe() {
        // removed other assertion

        // removed other assertion

        assertEquals(FastDateFormat.getDateInstance(FastDateFormat.LONG),FastDateFormat.getDateInstance(FastDateFormat.LONG,TimeZone.getDefault(),Locale.getDefault()));
    }

    @Test
    public void testLANG_1152_1_oe() {
        final TimeZone utc = FastTimeZone.getGmtTimeZone();
        final Date date = new Date(Long.MAX_VALUE);

        String dateAsString = FastDateFormat.getInstance("yyyy-MM-dd", utc, Locale.US).format(date);
        assertEquals("292278994-08-17", dateAsString);
    }

    @Test
    public void testLANG_1152_2_oe() {
        final TimeZone utc = FastTimeZone.getGmtTimeZone();
        final Date date = new Date(Long.MAX_VALUE);

        String dateAsString = FastDateFormat.getInstance("yyyy-MM-dd", utc, Locale.US).format(date);
        // removed other assertion

        dateAsString = FastDateFormat.getInstance("dd/MM/yyyy", utc, Locale.US).format(date);
        assertEquals("17/08/292278994", dateAsString);
    }

    @Test
    public void testTimeDateDefaults_1_oe() {
        assertEquals(FastDateFormat.getDateTimeInstance(FastDateFormat.LONG,FastDateFormat.MEDIUM,Locale.CANADA),FastDateFormat.getDateTimeInstance(FastDateFormat.LONG,FastDateFormat.MEDIUM,TimeZone.getDefault(),Locale.CANADA));
    }

    @Test
    public void testTimeDateDefaults_2_oe() {
        // removed other assertion

        assertEquals(FastDateFormat.getDateTimeInstance(FastDateFormat.LONG,FastDateFormat.MEDIUM,TimeZone.getTimeZone("America/New_York")),FastDateFormat.getDateTimeInstance(FastDateFormat.LONG,FastDateFormat.MEDIUM,TimeZone.getTimeZone("America/New_York"),Locale.getDefault()));
    }

    @Test
    public void testTimeDateDefaults_3_oe() {
        // removed other assertion

        // removed other assertion

        assertEquals(FastDateFormat.getDateTimeInstance(FastDateFormat.LONG,FastDateFormat.MEDIUM),FastDateFormat.getDateTimeInstance(FastDateFormat.LONG,FastDateFormat.MEDIUM,TimeZone.getDefault(),Locale.getDefault()));
    }

    @Test
    public void testTimeDefaults_1_oe() {
        assertEquals(FastDateFormat.getTimeInstance(FastDateFormat.LONG,Locale.CANADA),FastDateFormat.getTimeInstance(FastDateFormat.LONG,TimeZone.getDefault(),Locale.CANADA));
    }

    @Test
    public void testTimeDefaults_2_oe() {
        // removed other assertion

        assertEquals(FastDateFormat.getTimeInstance(FastDateFormat.LONG,TimeZone.getTimeZone("America/New_York")),FastDateFormat.getTimeInstance(FastDateFormat.LONG,TimeZone.getTimeZone("America/New_York"),Locale.getDefault()));
    }

    @Test
    public void testTimeDefaults_3_oe() {
        // removed other assertion

        // removed other assertion

        assertEquals(FastDateFormat.getTimeInstance(FastDateFormat.LONG),FastDateFormat.getTimeInstance(FastDateFormat.LONG,TimeZone.getDefault(),Locale.getDefault()));
    }

}
