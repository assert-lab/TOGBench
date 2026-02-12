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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.DefaultLocale;
import org.junitpioneer.jupiter.DefaultTimeZone;

/**
 * TestCase for DateFormatUtils.
 */
@SuppressWarnings("deprecation") // tests lots of deprecated items
public class DateFormatUtilsTest_OE25Dev {
    private void assertFormats(final String expectedValue, final String pattern, final TimeZone timeZone, final Calendar cal) {
        assertEquals(expectedValue, DateFormatUtils.format(cal.getTime(), pattern, timeZone));
        assertEquals(expectedValue, DateFormatUtils.format(cal.getTime().getTime(), pattern, timeZone));
        assertEquals(expectedValue, DateFormatUtils.format(cal, pattern, timeZone));
    }

    private Calendar createFebruaryTestDate(final TimeZone timeZone) {
        final Calendar cal = Calendar.getInstance(timeZone);
        cal.set(2002, Calendar.FEBRUARY, 23, 9, 11, 12);
        return cal;
    }

    private Calendar createJuneTestDate(final TimeZone timeZone) {
        final Calendar cal = Calendar.getInstance(timeZone);
        cal.set(2003, Calendar.JUNE, 8, 10, 11, 12);
        return cal;
    }

    //-----------------------------------------------------------------------

    @Test
    public void testDateISO() {
        testGmtMinus3("2002-02-23", DateFormatUtils.ISO_DATE_FORMAT.getPattern());
        testGmtMinus3("2002-02-23-03:00", DateFormatUtils.ISO_DATE_TIME_ZONE_FORMAT.getPattern());
        testUTC("2002-02-23Z", DateFormatUtils.ISO_DATE_TIME_ZONE_FORMAT.getPattern());
    }

    @Test
    public void testDateTimeISO() {
        testGmtMinus3("2002-02-23T09:11:12", DateFormatUtils.ISO_DATETIME_FORMAT.getPattern());
        testGmtMinus3("2002-02-23T09:11:12-03:00", DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern());
        testUTC("2002-02-23T09:11:12Z", DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern());
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    private void testGmtMinus3(final String expectedValue, final String pattern) {
        final TimeZone timeZone = TimeZone.getTimeZone("GMT-3");
        assertFormats(expectedValue, pattern, timeZone, createFebruaryTestDate(timeZone));
    }

    @Test
    public void testLANG1000() throws Exception {
        final String date = "2013-11-18T12:48:05Z";
        DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.parse(date);
    }

    /**
     * According to LANG-916 (https://issues.apache.org/jira/browse/LANG-916),
     * the format method did contain a bug: it did not use the TimeZone data.
     *
     * This method test that the bug is fixed.
     */

    @DefaultLocale(language = "en")
    @Test
    public void testSMTP() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT-3");
        Calendar june = createJuneTestDate(timeZone);

        assertFormats("Sun, 08 Jun 2003 10:11:12 -0300", DateFormatUtils.SMTP_DATETIME_FORMAT.getPattern(),
                timeZone, june);

        timeZone = FastTimeZone.getGmtTimeZone();
        june = createJuneTestDate(timeZone);
        assertFormats("Sun, 08 Jun 2003 10:11:12 +0000", DateFormatUtils.SMTP_DATETIME_FORMAT.getPattern(),
                timeZone, june);
    }

    @Test
    public void testTimeISO() {
        testGmtMinus3("T09:11:12", DateFormatUtils.ISO_TIME_FORMAT.getPattern());
        testGmtMinus3("T09:11:12-03:00", DateFormatUtils.ISO_TIME_TIME_ZONE_FORMAT.getPattern());
        testUTC("T09:11:12Z", DateFormatUtils.ISO_TIME_TIME_ZONE_FORMAT.getPattern());
    }

    @Test
    public void testTimeNoTISO() {
        testGmtMinus3("09:11:12", DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern());
        testGmtMinus3("09:11:12-03:00", DateFormatUtils.ISO_TIME_NO_T_TIME_ZONE_FORMAT.getPattern());
        testUTC("09:11:12Z", DateFormatUtils.ISO_TIME_NO_T_TIME_ZONE_FORMAT.getPattern());
    }

    private void testUTC(final String expectedValue, final String pattern) {
        final TimeZone timeZone = FastTimeZone.getGmtTimeZone();
        assertFormats(expectedValue, pattern, timeZone, createFebruaryTestDate(timeZone));
    }


}
