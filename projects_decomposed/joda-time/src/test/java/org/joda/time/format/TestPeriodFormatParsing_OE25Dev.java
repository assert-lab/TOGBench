/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time.format;

import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 * This class is a Junit unit test for PeriodFormat.
 *
 * @author Stephen Colebourne
 */
public class TestPeriodFormatParsing_OE25Dev extends TestCase {

    private static final Period PERIOD = new Period(1, 2, 3, 4, 5, 6, 7, 8);
    private static final Period EMPTY_PERIOD = new Period(0, 0, 0, 0, 0, 0, 0, 0);
    private static final Period YEAR_DAY_PERIOD = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
    private static final Period EMPTY_YEAR_DAY_PERIOD = new Period(0, 0, 0, 0, 0, 0, 0, 0, PeriodType.yearDayTime());
    private static final Period TIME_PERIOD = new Period(0, 0, 0, 0, 5, 6, 7, 8);
    private static final Period DATE_PERIOD = new Period(1, 2, 3, 4, 0, 0, 0, 0);

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");

    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    // 2002-06-09
    private long TEST_TIME_NOW = (y2002days + 31L + 28L + 31L + 30L + 31L + 9L - 1L) * DateTimeConstants.MILLIS_PER_DAY;

    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestPeriodFormatParsing_OE25Dev.class);
    }

    public TestPeriodFormatParsing_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        originalDateTimeZone = DateTimeZone.getDefault();
        originalTimeZone = TimeZone.getDefault();
        originalLocale = Locale.getDefault();
        DateTimeZone.setDefault(LONDON);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        Locale.setDefault(Locale.UK);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(originalDateTimeZone);
        TimeZone.setDefault(originalTimeZone);
        Locale.setDefault(originalLocale);
        originalDateTimeZone = null;
        originalTimeZone = null;
        originalLocale = null;
    }

    //-----------------------------------------------------------------------

    public void testParseStandard1_1_oe() {
        PeriodFormatter parser = PeriodFormat.getDefault();
        Period p = parser.parsePeriod("6 years, 3 months and 2 days");
        assertEquals(new Period(6,3,0,2,0,0,0,0),p);
    }

    public void testParseNegativeMillis1_1_oe() {
        Period period = new Period(0, 0, 0, -1);
        String formatted = period.toString();
        assertEquals("PT-0.001S",formatted);
    }

    public void testParseNegativeMillis1_2_oe() {
        Period period = new Period(0, 0, 0, -1);
        String formatted = period.toString();
        Period parsed = Period.parse(formatted);
        assertEquals(period,parsed);
    }

    public void testParseNegativeMillis2_1_oe() {
        Period period = new Period(0, 0, 0, -999);
        String formatted = period.toString();
        assertEquals("PT-0.999S",formatted);
    }

    public void testParseNegativeMillis2_2_oe() {
        Period period = new Period(0, 0, 0, -999);
        String formatted = period.toString();
        Period parsed = Period.parse(formatted);
        assertEquals(period,parsed);
    }

    public void testParseCustom1_1_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);
        assertEquals("47:55",formatter.print(p));
    }

    public void testParseCustom1_2_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);
        assertEquals(p,formatter.parsePeriod("47:55"));
    }

    public void testParseCustom1_3_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);
        assertEquals(p,formatter.parsePeriod("047:055"));
    }

    public void testParseCustom1_4_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);
        assertEquals("7:05",formatter.print(p));
    }

    public void testParseCustom1_5_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);
        assertEquals(p,formatter.parsePeriod("7:05"));
    }

    public void testParseCustom1_6_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);
        assertEquals(p,formatter.parsePeriod("7:5"));
    }

    public void testParseCustom1_7_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);
        assertEquals(p,formatter.parsePeriod("07:05"));
    }

    public void testParseCustom1_8_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);

        p = new Period(0, 5, 0, 0);
        assertEquals("0:05",formatter.print(p));
    }

    public void testParseCustom1_9_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);

        p = new Period(0, 5, 0, 0);
        assertEquals(p,formatter.parsePeriod("0:05"));
    }

    public void testParseCustom1_10_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);

        p = new Period(0, 5, 0, 0);
        assertEquals(p,formatter.parsePeriod("0:5"));
    }

    public void testParseCustom1_11_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);

        p = new Period(0, 5, 0, 0);
        assertEquals(p,formatter.parsePeriod("00:005"));
    }

    public void testParseCustom1_12_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);

        p = new Period(0, 5, 0, 0);
        assertEquals(p,formatter.parsePeriod("0:005"));
    }

    public void testParseCustom1_13_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);

        p = new Period(0, 5, 0, 0);

        p = new Period(0, 0, 0, 0);
        assertEquals("0:00",formatter.print(p));
    }

    public void testParseCustom1_14_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);

        p = new Period(0, 5, 0, 0);

        p = new Period(0, 0, 0, 0);
        assertEquals(p,formatter.parsePeriod("0:00"));
    }

    public void testParseCustom1_15_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);

        p = new Period(0, 5, 0, 0);

        p = new Period(0, 0, 0, 0);
        assertEquals(p,formatter.parsePeriod("0:0"));
    }

    public void testParseCustom1_16_oe() {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSuffix(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();

        Period p;

        p = new Period(47, 55, 0, 0);

        p = new Period(7, 5, 0, 0);

        p = new Period(0, 5, 0, 0);

        p = new Period(0, 0, 0, 0);
        assertEquals(p,formatter.parsePeriod("00:00"));
    }

}
