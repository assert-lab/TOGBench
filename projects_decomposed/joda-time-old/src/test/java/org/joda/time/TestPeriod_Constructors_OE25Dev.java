/*
 *  Copyright 2001-2013 Stephen Colebourne
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
package org.joda.time;

import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.ISOChronology;

/**
 * This class is a JUnit test for Duration.
 *
 * @author Stephen Colebourne
 */
public class TestPeriod_Constructors_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    
    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    long y2003days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365 + 365;
    
    // 2002-06-09
    private long TEST_TIME_NOW =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestPeriod_Constructors.class);
    }

    public TestPeriod_Constructors_OE25Dev(String name) {
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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testConstructor_long_fixedZone() throws Throwable {
        DateTimeZone zone = DateTimeZone.getDefault();
        try {
            DateTimeZone.setDefault(DateTimeZone.forOffsetHours(2));
            long length =
                (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
                5L * DateTimeConstants.MILLIS_PER_HOUR +
                6L * DateTimeConstants.MILLIS_PER_MINUTE +
                7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
            Period test = new Period(length);
            assertEquals(PeriodType.standard(), test.getPeriodType());
            // only time fields are precise in AllType
            assertEquals(0, test.getYears());  // (4 + (3 * 7) + (2 * 30) + 365) == 450
            assertEquals(0, test.getMonths());
            assertEquals(0, test.getWeeks());
            assertEquals(0, test.getDays());
            assertEquals((450 * 24) + 5, test.getHours());
            assertEquals(6, test.getMinutes());
            assertEquals(7, test.getSeconds());
            assertEquals(8, test.getMillis());
        } finally {
            DateTimeZone.setDefault(zone);
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    /**
     * Test constructor (4ints)
     */

    //-----------------------------------------------------------------------
    /**
     * Test constructor (8ints)
     */

    //-----------------------------------------------------------------------
    /**
     * Test constructor (8ints)
     */

    public void testConstructor_8int__PeriodType3() throws Throwable {
        try {
            new Period(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.dayTime());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @SuppressWarnings("deprecation")
    public void testConstructor_RP_RP3() throws Throwable {
        YearMonthDay dt1 = null;
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 17);
        try {
            new Period(dt1, dt2);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    @SuppressWarnings("deprecation")
    public void testConstructor_RP_RP4() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2005, 7, 17);
        YearMonthDay dt2 = null;
        try {
            new Period(dt1, dt2);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    @SuppressWarnings("deprecation")
    public void testConstructor_RP_RP5() throws Throwable {
        YearMonthDay dt1 = null;
        YearMonthDay dt2 = null;
        try {
            new Period(dt1, dt2);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    @SuppressWarnings("deprecation")
    public void testConstructor_RP_RP6() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2005, 7, 17);
        TimeOfDay dt2 = new TimeOfDay(10, 20, 30, 40);
        try {
            new Period(dt1, dt2);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_RP_RP7() throws Throwable {
        Partial dt1 = new Partial().with(DateTimeFieldType.year(), 2005).with(DateTimeFieldType.monthOfYear(), 12);
        Partial dt2 = new Partial().with(DateTimeFieldType.year(), 2005).with(DateTimeFieldType.hourOfDay(), 14);
        try {
            new Period(dt1, dt2);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_RP_RP8() throws Throwable {
        Partial dt1 = new Partial().with(DateTimeFieldType.year(), 2005).with(DateTimeFieldType.hourOfDay(), 12);
        Partial dt2 = new Partial().with(DateTimeFieldType.year(), 2005).with(DateTimeFieldType.hourOfDay(), 14);
        try {
            new Period(dt1, dt2);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    @SuppressWarnings("deprecation")
    public void testConstructor_RP_RP_PeriodType3() throws Throwable {
        YearMonthDay dt1 = null;
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 17);
        try {
            new Period(dt1, dt2, PeriodType.standard());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    @SuppressWarnings("deprecation")
    public void testConstructor_RP_RP_PeriodType4() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2005, 7, 17);
        YearMonthDay dt2 = null;
        try {
            new Period(dt1, dt2);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    @SuppressWarnings("deprecation")
    public void testConstructor_RP_RP_PeriodType5() throws Throwable {
        YearMonthDay dt1 = null;
        YearMonthDay dt2 = null;
        try {
            new Period(dt1, dt2, PeriodType.standard());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    @SuppressWarnings("deprecation")
    public void testConstructor_RP_RP_PeriodType6() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2005, 7, 17);
        TimeOfDay dt2 = new TimeOfDay(10, 20, 30, 40);
        try {
            new Period(dt1, dt2, PeriodType.standard());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_RP_RP_PeriodType7() throws Throwable {
        Partial dt1 = new Partial().with(DateTimeFieldType.year(), 2005).with(DateTimeFieldType.monthOfYear(), 12);
        Partial dt2 = new Partial().with(DateTimeFieldType.year(), 2005).with(DateTimeFieldType.hourOfDay(), 14);
        try {
            new Period(dt1, dt2, PeriodType.standard());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_RP_RP_PeriodType8() throws Throwable {
        Partial dt1 = new Partial().with(DateTimeFieldType.year(), 2005).with(DateTimeFieldType.hourOfDay(), 12);
        Partial dt2 = new Partial().with(DateTimeFieldType.year(), 2005).with(DateTimeFieldType.hourOfDay(), 14);
        try {
            new Period(dt1, dt2, PeriodType.standard());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    /**
     * Test constructor (Object)
     */

    //-----------------------------------------------------------------------
    /**
     * Test constructor (Object)
     */

    //-----------------------------------------------------------------------

    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @SuppressWarnings("deprecation")
    public void testFactoryFieldDifference2() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 4, 9);
        try {
            Period.fieldDifference(ymd, (ReadablePartial) null);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            Period.fieldDifference((ReadablePartial) null, ymd);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    @SuppressWarnings("deprecation")
    public void testFactoryFieldDifference3() throws Throwable {
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        TimeOfDay endTime = new TimeOfDay(12, 30, 40, 0);
        try {
            Period.fieldDifference(start, endTime);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    @SuppressWarnings("deprecation")
    public void testFactoryFieldDifference4() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfWeek(),
        };
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        Partial end = new Partial(types, new int[] {1, 2, 3});
        try {
            Period.fieldDifference(start, end);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testFactoryFieldDifference5() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfMonth(),
            DateTimeFieldType.dayOfWeek(),
        };
        Partial start = new Partial(types, new int[] {1, 2, 3});
        Partial end = new Partial(types, new int[] {1, 2, 3});
        try {
            Period.fieldDifference(start, end);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstants_1_oe() throws Throwable {
        Period test = Period.ZERO;
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstants_2_oe() throws Throwable {
        Period test = Period.ZERO;
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstants_3_oe() throws Throwable {
        Period test = Period.ZERO;
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstants_4_oe() throws Throwable {
        Period test = Period.ZERO;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstants_5_oe() throws Throwable {
        Period test = Period.ZERO;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstants_6_oe() throws Throwable {
        Period test = Period.ZERO;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstants_7_oe() throws Throwable {
        Period test = Period.ZERO;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstants_8_oe() throws Throwable {
        Period test = Period.ZERO;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstants_9_oe() throws Throwable {
        Period test = Period.ZERO;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testParse_noFormatter_1_oe() throws Throwable {
        assertEquals(new Period(1, 2, 3, 4, 5, 6, 7, 890), Period.parse("P1Y2M3W4DT5H6M7.890S"));
    }

    public void testConstructor1_1_oe() throws Throwable {
        Period test = new Period();
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor1_2_oe() throws Throwable {
        Period test = new Period();
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor1_3_oe() throws Throwable {
        Period test = new Period();
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor1_4_oe() throws Throwable {
        Period test = new Period();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor1_5_oe() throws Throwable {
        Period test = new Period();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor1_6_oe() throws Throwable {
        Period test = new Period();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor1_7_oe() throws Throwable {
        Period test = new Period();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor1_8_oe() throws Throwable {
        Period test = new Period();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor1_9_oe() throws Throwable {
        Period test = new Period();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_long1_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long1_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long1_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long1_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long1_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long1_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((4 * 24) + 5, test.getHours());
    }

    public void testConstructor_long1_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long1_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long1_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long2_1_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long2_2_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long2_3_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long2_4_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long2_5_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long2_6_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getHours());
    }

    public void testConstructor_long2_7_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long2_8_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long2_9_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long3_1_oe() throws Throwable {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Period test = new Period(length);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long3_2_oe() throws Throwable {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Period test = new Period(length);
        // removed other assertion
        // only time fields are precise in AllType
        assertEquals(0, test.getYears());  // (4 + (3 * 7) + (2 * 30) + 365) == 450;
    }

    public void testConstructor_long3_3_oe() throws Throwable {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Period test = new Period(length);
        // removed other assertion
        // only time fields are precise in AllType
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long3_4_oe() throws Throwable {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Period test = new Period(length);
        // removed other assertion
        // only time fields are precise in AllType
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long3_5_oe() throws Throwable {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Period test = new Period(length);
        // removed other assertion
        // only time fields are precise in AllType
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long3_6_oe() throws Throwable {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Period test = new Period(length);
        // removed other assertion
        // only time fields are precise in AllType
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((450 * 24) + 5, test.getHours());
    }

    public void testConstructor_long3_7_oe() throws Throwable {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Period test = new Period(length);
        // removed other assertion
        // only time fields are precise in AllType
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long3_8_oe() throws Throwable {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Period test = new Period(length);
        // removed other assertion
        // only time fields are precise in AllType
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long3_9_oe() throws Throwable {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Period test = new Period(length);
        // removed other assertion
        // only time fields are precise in AllType
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long_PeriodType1_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_PeriodType1_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_PeriodType1_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_PeriodType1_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_PeriodType1_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long_PeriodType1_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((4 * 24) + 5, test.getHours());
    }

    public void testConstructor_long_PeriodType1_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long_PeriodType1_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long_PeriodType1_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long_PeriodType2_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.millis());
        assertEquals(PeriodType.millis(), test.getPeriodType());
    }

    public void testConstructor_long_PeriodType2_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.millis());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_PeriodType2_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.millis());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_PeriodType2_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.millis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_PeriodType2_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.millis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long_PeriodType2_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.millis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_long_PeriodType2_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.millis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_long_PeriodType2_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.millis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_long_PeriodType2_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.millis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(length, test.getMillis());
    }

    public void testConstructor_long_PeriodType3_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.dayTime());
        assertEquals(PeriodType.dayTime(), test.getPeriodType());
    }

    public void testConstructor_long_PeriodType3_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.dayTime());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_PeriodType3_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_PeriodType3_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_PeriodType3_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long_PeriodType3_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((4 * 24) + 5, test.getHours());
    }

    public void testConstructor_long_PeriodType3_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long_PeriodType3_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long_PeriodType3_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long_PeriodType4_1_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard().withMillisRemoved());
        assertEquals(PeriodType.standard().withMillisRemoved(), test.getPeriodType());
    }

    public void testConstructor_long_PeriodType4_2_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_PeriodType4_3_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_PeriodType4_4_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_PeriodType4_5_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long_PeriodType4_6_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getHours());
    }

    public void testConstructor_long_PeriodType4_7_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long_PeriodType4_8_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long_PeriodType4_9_oe() throws Throwable {
        long length =
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_long_Chronology1_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstance());
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_Chronology1_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstance());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_Chronology1_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_Chronology1_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_Chronology1_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long_Chronology1_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((4 * 24) + 5, test.getHours());
    }

    public void testConstructor_long_Chronology1_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long_Chronology1_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long_Chronology1_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long_Chronology2_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstanceUTC());
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_Chronology2_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_Chronology2_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_Chronology2_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_Chronology2_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getDays());
    }

    public void testConstructor_long_Chronology2_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getHours());
    }

    public void testConstructor_long_Chronology2_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long_Chronology2_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long_Chronology2_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long_Chronology3_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (Chronology) null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_Chronology3_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (Chronology) null);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_Chronology3_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_Chronology3_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_Chronology3_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long_Chronology3_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((4 * 24) + 5, test.getHours());
    }

    public void testConstructor_long_Chronology3_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long_Chronology3_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long_Chronology3_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long_PeriodType_Chronology1_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.time().withMillisRemoved(), ISOChronology.getInstance());
        assertEquals(PeriodType.time().withMillisRemoved(), test.getPeriodType());
    }

    public void testConstructor_long_PeriodType_Chronology1_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.time().withMillisRemoved(), ISOChronology.getInstance());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_PeriodType_Chronology1_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.time().withMillisRemoved(), ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_PeriodType_Chronology1_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.time().withMillisRemoved(), ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_PeriodType_Chronology1_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.time().withMillisRemoved(), ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long_PeriodType_Chronology1_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.time().withMillisRemoved(), ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((4 * 24) + 5, test.getHours());
    }

    public void testConstructor_long_PeriodType_Chronology1_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.time().withMillisRemoved(), ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long_PeriodType_Chronology1_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.time().withMillisRemoved(), ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long_PeriodType_Chronology1_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.time().withMillisRemoved(), ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_long_PeriodType_Chronology2_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), ISOChronology.getInstanceUTC());
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_PeriodType_Chronology2_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), ISOChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_PeriodType_Chronology2_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_PeriodType_Chronology2_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_PeriodType_Chronology2_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getDays());
    }

    public void testConstructor_long_PeriodType_Chronology2_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getHours());
    }

    public void testConstructor_long_PeriodType_Chronology2_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long_PeriodType_Chronology2_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long_PeriodType_Chronology2_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), ISOChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long_PeriodType_Chronology3_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), (Chronology) null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_PeriodType_Chronology3_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), (Chronology) null);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_PeriodType_Chronology3_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_PeriodType_Chronology3_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_PeriodType_Chronology3_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long_PeriodType_Chronology3_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((4 * 24) + 5, test.getHours());
    }

    public void testConstructor_long_PeriodType_Chronology3_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long_PeriodType_Chronology3_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long_PeriodType_Chronology3_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, PeriodType.standard(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long_PeriodType_Chronology4_1_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null, (Chronology) null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_PeriodType_Chronology4_2_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null, (Chronology) null);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_PeriodType_Chronology4_3_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null, (Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_PeriodType_Chronology4_4_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_PeriodType_Chronology4_5_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long_PeriodType_Chronology4_6_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((4 * 24) + 5, test.getHours());
    }

    public void testConstructor_long_PeriodType_Chronology4_7_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_long_PeriodType_Chronology4_8_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_long_PeriodType_Chronology4_9_oe() throws Throwable {
        long length = 4 * DateTimeConstants.MILLIS_PER_DAY +
                5 * DateTimeConstants.MILLIS_PER_HOUR +
                6 * DateTimeConstants.MILLIS_PER_MINUTE +
                7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
        Period test = new Period(length, (PeriodType) null, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_4int1_1_oe() throws Throwable {
        Period test = new Period(5, 6, 7, 8);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_4int1_2_oe() throws Throwable {
        Period test = new Period(5, 6, 7, 8);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_4int1_3_oe() throws Throwable {
        Period test = new Period(5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_4int1_4_oe() throws Throwable {
        Period test = new Period(5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_4int1_5_oe() throws Throwable {
        Period test = new Period(5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_4int1_6_oe() throws Throwable {
        Period test = new Period(5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getHours());
    }

    public void testConstructor_4int1_7_oe() throws Throwable {
        Period test = new Period(5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_4int1_8_oe() throws Throwable {
        Period test = new Period(5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_4int1_9_oe() throws Throwable {
        Period test = new Period(5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_8int1_1_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_8int1_2_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_8int1_3_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getMonths());
    }

    public void testConstructor_8int1_4_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.getWeeks());
    }

    public void testConstructor_8int1_5_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getDays());
    }

    public void testConstructor_8int1_6_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getHours());
    }

    public void testConstructor_8int1_7_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_8int1_8_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_8int1_9_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_8int__PeriodType1_1_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_8int__PeriodType1_2_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, null);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_8int__PeriodType1_3_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, null);
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getMonths());
    }

    public void testConstructor_8int__PeriodType1_4_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.getWeeks());
    }

    public void testConstructor_8int__PeriodType1_5_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getDays());
    }

    public void testConstructor_8int__PeriodType1_6_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getHours());
    }

    public void testConstructor_8int__PeriodType1_7_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_8int__PeriodType1_8_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_8int__PeriodType1_9_oe() throws Throwable {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_8int__PeriodType2_1_oe() throws Throwable {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.dayTime());
        assertEquals(PeriodType.dayTime(), test.getPeriodType());
    }

    public void testConstructor_8int__PeriodType2_2_oe() throws Throwable {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.dayTime());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_8int__PeriodType2_3_oe() throws Throwable {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_8int__PeriodType2_4_oe() throws Throwable {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_8int__PeriodType2_5_oe() throws Throwable {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_8int__PeriodType2_6_oe() throws Throwable {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getHours());
    }

    public void testConstructor_8int__PeriodType2_7_oe() throws Throwable {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testConstructor_8int__PeriodType2_8_oe() throws Throwable {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testConstructor_8int__PeriodType2_9_oe() throws Throwable {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long_long1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_long1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_long_long1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_long_long1_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_long1_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_long_long1_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_long_long1_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_long_long1_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_long_long1_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_long_long2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_long2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_long_long2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_long_long2_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getWeeks());
    }

    public void testConstructor_long_long2_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_long_long2_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_long_long2_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_long_long2_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_long_long2_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_long_long_PeriodType1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_long_PeriodType1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_long_long_PeriodType1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_long_long_PeriodType1_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_long_PeriodType1_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_long_long_PeriodType1_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_long_long_PeriodType1_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_long_long_PeriodType1_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_long_long_PeriodType1_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_long_long_PeriodType2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.dayTime());
        assertEquals(PeriodType.dayTime(), test.getPeriodType());
    }

    public void testConstructor_long_long_PeriodType2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.dayTime());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_long_PeriodType2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_long_PeriodType2_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_long_PeriodType2_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(31, test.getDays());
    }

    public void testConstructor_long_long_PeriodType2_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_long_long_PeriodType2_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_long_long_PeriodType2_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_long_long_PeriodType2_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_long_long_PeriodType3_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.standard().withMillisRemoved());
        assertEquals(PeriodType.standard().withMillisRemoved(), test.getPeriodType());
    }

    public void testConstructor_long_long_PeriodType3_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.standard().withMillisRemoved());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_long_long_PeriodType3_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_long_long_PeriodType3_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_long_PeriodType3_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_long_long_PeriodType3_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_long_long_PeriodType3_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_long_long_PeriodType3_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_long_long_PeriodType3_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testToPeriod_PeriodType3_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 6, 9, 12, 14, 16, 18);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.yearWeekDayTime());
        
        assertEquals(PeriodType.yearWeekDayTime(), test.getPeriodType());
    }

    public void testToPeriod_PeriodType3_2_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 6, 9, 12, 14, 16, 18);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.yearWeekDayTime());
        
        // removed other assertion
        assertEquals(1, test.getYears());  // tests using years and not weekyears;
    }

    public void testToPeriod_PeriodType3_3_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 6, 9, 12, 14, 16, 18);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.yearWeekDayTime());
        
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testToPeriod_PeriodType3_4_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 6, 9, 12, 14, 16, 18);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.yearWeekDayTime());
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testToPeriod_PeriodType3_5_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 6, 9, 12, 14, 16, 18);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.yearWeekDayTime());
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testToPeriod_PeriodType3_6_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 6, 9, 12, 14, 16, 18);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.yearWeekDayTime());
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getHours());
    }

    public void testToPeriod_PeriodType3_7_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 6, 9, 12, 14, 16, 18);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.yearWeekDayTime());
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testToPeriod_PeriodType3_8_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 6, 9, 12, 14, 16, 18);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.yearWeekDayTime());
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getSeconds());
    }

    public void testToPeriod_PeriodType3_9_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 6, 9, 12, 14, 16, 18);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), PeriodType.yearWeekDayTime());
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getMillis());
    }

    public void testConstructor_long_long_Chronology1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), CopticChronology.getInstance());
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_long_Chronology1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), CopticChronology.getInstance());
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_long_long_Chronology1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_long_long_Chronology1_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_long_Chronology1_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_long_long_Chronology1_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_long_long_Chronology1_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_long_long_Chronology1_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_long_long_Chronology1_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_long_long_Chronology2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_long_Chronology2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_long_long_Chronology2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_long_long_Chronology2_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_long_Chronology2_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_long_long_Chronology2_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_long_long_Chronology2_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_long_long_Chronology2_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_long_long_Chronology2_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_long_long_PeriodType_Chronology1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, CopticChronology.getInstance());
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_long_PeriodType_Chronology1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, CopticChronology.getInstance());
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_long_long_PeriodType_Chronology1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_long_long_PeriodType_Chronology1_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_long_PeriodType_Chronology1_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_long_long_PeriodType_Chronology1_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_long_long_PeriodType_Chronology1_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_long_long_PeriodType_Chronology1_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_long_long_PeriodType_Chronology1_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, CopticChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, CopticChronology.getInstance());
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_long_long_PeriodType_Chronology2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_long_long_PeriodType_Chronology2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, null);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_long_long_PeriodType_Chronology2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, null);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_long_long_PeriodType_Chronology2_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_long_long_PeriodType_Chronology2_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_long_long_PeriodType_Chronology2_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_long_long_PeriodType_Chronology2_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_long_long_PeriodType_Chronology2_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_long_long_PeriodType_Chronology2_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1.getMillis(), dt2.getMillis(), (PeriodType) null, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RI_RI1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RI1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_RI_RI1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_RI_RI1_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RI1_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RI_RI1_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_RI_RI1_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RI_RI1_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RI_RI1_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RI_RI2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RI2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_RI_RI2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_RI_RI2_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getWeeks());
    }

    public void testConstructor_RI_RI2_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RI_RI2_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_RI_RI2_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RI_RI2_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RI_RI2_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RI_RI3_1_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RI3_2_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(3, test.getYears());
    }

    public void testConstructor_RI_RI3_3_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_RI_RI3_4_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getWeeks());
    }

    public void testConstructor_RI_RI3_5_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RI_RI3_6_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RI_RI3_7_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RI_RI3_8_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RI_RI3_9_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RI_RI4_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RI4_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(-3, test.getYears());
    }

    public void testConstructor_RI_RI4_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(-1, test.getMonths());
    }

    public void testConstructor_RI_RI4_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, test.getWeeks());
    }

    public void testConstructor_RI_RI4_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, test.getDays());
    }

    public void testConstructor_RI_RI4_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RI_RI4_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, test.getMinutes());
    }

    public void testConstructor_RI_RI4_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, test.getSeconds());
    }

    public void testConstructor_RI_RI4_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, test.getMillis());
    }

    public void testConstructor_RI_RI5_1_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RI5_2_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RI_RI5_3_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RI_RI5_4_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RI5_5_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_RI_RI5_6_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RI_RI5_7_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RI_RI5_8_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RI_RI5_9_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RI_RI6_1_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withLaterOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RI6_2_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withLaterOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RI_RI6_3_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withLaterOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RI_RI6_4_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withLaterOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RI6_5_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withLaterOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_RI_RI6_6_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withLaterOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RI_RI6_7_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withLaterOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(15, test.getMinutes());
    }

    public void testConstructor_RI_RI6_8_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withLaterOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RI_RI6_9_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withLaterOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RI_RI7_1_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withEarlierOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RI7_2_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withEarlierOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RI_RI7_3_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withEarlierOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RI_RI7_4_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withEarlierOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RI7_5_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withEarlierOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_RI_RI7_6_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withEarlierOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_RI_RI7_7_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withEarlierOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(15, test.getMinutes());
    }

    public void testConstructor_RI_RI7_8_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withEarlierOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RI_RI7_9_oe() throws Throwable {
        DateTimeZone zone = PARIS;
        DateTime dt1 = new DateTime(2013, 10, 27, 2, 0, 0, zone).withEarlierOffsetAtOverlap();
        DateTime dt2 = new DateTime(2013, 10, 27, 2, 15, 0, zone).withLaterOffsetAtOverlap();
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RI_RI_PeriodType1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RI_PeriodType1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, null);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_RI_RI_PeriodType1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, null);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_RI_RI_PeriodType1_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RI_PeriodType1_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RI_RI_PeriodType1_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_RI_RI_PeriodType1_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RI_RI_PeriodType1_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RI_RI_PeriodType1_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RI_RI_PeriodType2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.dayTime());
        assertEquals(PeriodType.dayTime(), test.getPeriodType());
    }

    public void testConstructor_RI_RI_PeriodType2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.dayTime());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RI_RI_PeriodType2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RI_RI_PeriodType2_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RI_PeriodType2_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(31, test.getDays());
    }

    public void testConstructor_RI_RI_PeriodType2_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_RI_RI_PeriodType2_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RI_RI_PeriodType2_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RI_RI_PeriodType2_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 7, 10, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.dayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RI_RI_PeriodType3_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard().withMillisRemoved());
        assertEquals(PeriodType.standard().withMillisRemoved(), test.getPeriodType());
    }

    public void testConstructor_RI_RI_PeriodType3_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RI_RI_PeriodType3_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RI_RI_PeriodType3_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RI_PeriodType3_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_RI_RI_PeriodType3_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_RI_RI_PeriodType3_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RI_RI_PeriodType3_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RI_RI_PeriodType3_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2004, 6, 9, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard().withMillisRemoved());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RI_RI_PeriodType4_1_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RI_PeriodType4_2_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        assertEquals(3, test.getYears());
    }

    public void testConstructor_RI_RI_PeriodType4_3_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_RI_RI_PeriodType4_4_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getWeeks());
    }

    public void testConstructor_RI_RI_PeriodType4_5_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RI_RI_PeriodType4_6_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RI_RI_PeriodType4_7_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RI_RI_PeriodType4_8_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RI_RI_PeriodType4_9_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = new DateTime(2005, 7, 17, 1, 1, 1, 1);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RI_RI_PeriodType5_1_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2, PeriodType.standard());
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RI_PeriodType5_2_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RI_RI_PeriodType5_3_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RI_RI_PeriodType5_4_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RI_PeriodType5_5_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_RI_RI_PeriodType5_6_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RI_RI_PeriodType5_7_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RI_RI_PeriodType5_8_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RI_RI_PeriodType5_9_oe() throws Throwable {
        DateTime dt1 = null;  // 2002-06-09T01:00+01:00
        DateTime dt2 = null;  // 2002-06-09T01:00+01:00
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RP_RP1_1_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RP_RP1_2_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_RP_RP1_3_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_RP_RP1_4_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RP_RP1_5_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RP_RP1_6_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RP_RP1_7_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RP_RP1_8_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RP_RP1_9_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RP_RP2_1_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RP_RP2_2_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RP_RP2_3_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(11, test.getMonths());
    }

    public void testConstructor_RP_RP2_4_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getWeeks());
    }

    public void testConstructor_RP_RP2_5_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RP_RP2_6_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RP_RP2_7_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RP_RP2_8_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RP_RP2_9_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RP_RP2Local_1_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RP_RP2Local_2_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RP_RP2Local_3_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(11, test.getMonths());
    }

    public void testConstructor_RP_RP2Local_4_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getWeeks());
    }

    public void testConstructor_RP_RP2Local_5_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RP_RP2Local_6_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RP_RP2Local_7_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RP_RP2Local_8_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RP_RP2Local_9_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RP_RP_PeriodType1_1_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RP_RP_PeriodType1_2_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_RP_RP_PeriodType1_3_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_RP_RP_PeriodType1_4_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RP_RP_PeriodType1_5_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RP_RP_PeriodType1_6_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RP_RP_PeriodType1_7_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RP_RP_PeriodType1_8_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RP_RP_PeriodType1_9_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 7, 10);
        Period test = new Period(dt1, dt2, PeriodType.standard());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RP_RP_PeriodType2_1_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        assertEquals(PeriodType.yearMonthDay(), test.getPeriodType());
    }

    public void testConstructor_RP_RP_PeriodType2_2_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RP_RP_PeriodType2_3_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        assertEquals(11, test.getMonths());
    }

    public void testConstructor_RP_RP_PeriodType2_4_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RP_RP_PeriodType2_5_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getDays());
    }

    public void testConstructor_RP_RP_PeriodType2_6_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RP_RP_PeriodType2_7_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RP_RP_PeriodType2_8_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RP_RP_PeriodType2_9_oe() throws Throwable {
        YearMonthDay dt1 = new YearMonthDay(2004, 6, 9);
        YearMonthDay dt2 = new YearMonthDay(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RP_RP_PeriodType2Local_1_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        assertEquals(PeriodType.yearMonthDay(), test.getPeriodType());
    }

    public void testConstructor_RP_RP_PeriodType2Local_2_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RP_RP_PeriodType2Local_3_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        assertEquals(11, test.getMonths());
    }

    public void testConstructor_RP_RP_PeriodType2Local_4_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RP_RP_PeriodType2Local_5_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getDays());
    }

    public void testConstructor_RP_RP_PeriodType2Local_6_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RP_RP_PeriodType2Local_7_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RP_RP_PeriodType2Local_8_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RP_RP_PeriodType2Local_9_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 6, 9);
        LocalDate dt2 = new LocalDate(2005, 5, 17);
        Period test = new Period(dt1, dt2, PeriodType.yearMonthDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RI_RD1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RD1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_RI_RD1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_RI_RD1_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RD1_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RI_RD1_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_RI_RD1_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RI_RD1_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RI_RD1_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RI_RD2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RD2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RI_RD2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RI_RD2_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RD2_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_RI_RD2_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RI_RD2_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RI_RD2_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RI_RD2_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RI_RD_PeriodType1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur, PeriodType.yearDayTime());
        assertEquals(PeriodType.yearDayTime(), test.getPeriodType());
    }

    public void testConstructor_RI_RD_PeriodType1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur, PeriodType.yearDayTime());
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_RI_RD_PeriodType1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RI_RD_PeriodType1_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RD_PeriodType1_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(31, test.getDays());
    }

    public void testConstructor_RI_RD_PeriodType1_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_RI_RD_PeriodType1_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RI_RD_PeriodType1_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RI_RD_PeriodType1_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dt1, dur, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RI_RD_PeriodType2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur, (PeriodType) null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RI_RD_PeriodType2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur, (PeriodType) null);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RI_RD_PeriodType2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RI_RD_PeriodType2_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RI_RD_PeriodType2_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_RI_RD_PeriodType2_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RI_RD_PeriodType2_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RI_RD_PeriodType2_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RI_RD_PeriodType2_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dt1, dur, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RD_RI1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RD_RI1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_RD_RI1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_RD_RI1_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RD_RI1_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_RD_RI1_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_RD_RI1_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RD_RI1_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RD_RI1_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RD_RI2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RD_RI2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RD_RI2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RD_RI2_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RD_RI2_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_RD_RI2_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RD_RI2_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RD_RI2_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RD_RI2_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_RD_RI_PeriodType1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2, PeriodType.yearDayTime());
        assertEquals(PeriodType.yearDayTime(), test.getPeriodType());
    }

    public void testConstructor_RD_RI_PeriodType1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2, PeriodType.yearDayTime());
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_RD_RI_PeriodType1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RD_RI_PeriodType1_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RD_RI_PeriodType1_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(31, test.getDays());
    }

    public void testConstructor_RD_RI_PeriodType1_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_RD_RI_PeriodType1_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_RD_RI_PeriodType1_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_RD_RI_PeriodType1_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Duration dur = new Interval(dt1, dt2).toDuration();
        Period test = new Period(dur, dt2, PeriodType.yearDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_RD_RI_PeriodType2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1, (PeriodType) null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_RD_RI_PeriodType2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1, (PeriodType) null);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_RD_RI_PeriodType2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_RD_RI_PeriodType2_4_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_RD_RI_PeriodType2_5_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_RD_RI_PeriodType2_6_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_RD_RI_PeriodType2_7_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_RD_RI_PeriodType2_8_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_RD_RI_PeriodType2_9_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Duration dur = null;
        Period test = new Period(dur, dt1, (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_Object1_1_oe() throws Throwable {
        Period test = new Period("P1Y2M3D");
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_Object1_2_oe() throws Throwable {
        Period test = new Period("P1Y2M3D");
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_Object1_3_oe() throws Throwable {
        Period test = new Period("P1Y2M3D");
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getMonths());
    }

    public void testConstructor_Object1_4_oe() throws Throwable {
        Period test = new Period("P1Y2M3D");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_Object1_5_oe() throws Throwable {
        Period test = new Period("P1Y2M3D");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.getDays());
    }

    public void testConstructor_Object1_6_oe() throws Throwable {
        Period test = new Period("P1Y2M3D");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_Object1_7_oe() throws Throwable {
        Period test = new Period("P1Y2M3D");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_Object1_8_oe() throws Throwable {
        Period test = new Period("P1Y2M3D");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_Object1_9_oe() throws Throwable {
        Period test = new Period("P1Y2M3D");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_Object2_1_oe() throws Throwable {
        Period test = new Period((Object) null);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_Object2_2_oe() throws Throwable {
        Period test = new Period((Object) null);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_Object2_3_oe() throws Throwable {
        Period test = new Period((Object) null);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_Object2_4_oe() throws Throwable {
        Period test = new Period((Object) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_Object2_5_oe() throws Throwable {
        Period test = new Period((Object) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_Object2_6_oe() throws Throwable {
        Period test = new Period((Object) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_Object2_7_oe() throws Throwable {
        Period test = new Period((Object) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_Object2_8_oe() throws Throwable {
        Period test = new Period((Object) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_Object2_9_oe() throws Throwable {
        Period test = new Period((Object) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_Object3_1_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()));
        assertEquals(PeriodType.dayTime(), test.getPeriodType());
    }

    public void testConstructor_Object3_2_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()));
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_Object3_3_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()));
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_Object3_4_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_Object3_5_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_Object3_6_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_Object3_7_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getMinutes());
    }

    public void testConstructor_Object3_8_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.getSeconds());
    }

    public void testConstructor_Object3_9_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMillis());
    }

    public void testConstructor_Object4_1_oe() throws Throwable {
        Period base = new Period(1, 1, 0, 1, 1, 1, 1, 1, PeriodType.standard());
        Period test = new Period(base);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_Object4_2_oe() throws Throwable {
        Period base = new Period(1, 1, 0, 1, 1, 1, 1, 1, PeriodType.standard());
        Period test = new Period(base);
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_Object4_3_oe() throws Throwable {
        Period base = new Period(1, 1, 0, 1, 1, 1, 1, 1, PeriodType.standard());
        Period test = new Period(base);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMonths());
    }

    public void testConstructor_Object4_4_oe() throws Throwable {
        Period base = new Period(1, 1, 0, 1, 1, 1, 1, 1, PeriodType.standard());
        Period test = new Period(base);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_Object4_5_oe() throws Throwable {
        Period base = new Period(1, 1, 0, 1, 1, 1, 1, 1, PeriodType.standard());
        Period test = new Period(base);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDays());
    }

    public void testConstructor_Object4_6_oe() throws Throwable {
        Period base = new Period(1, 1, 0, 1, 1, 1, 1, 1, PeriodType.standard());
        Period test = new Period(base);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_Object4_7_oe() throws Throwable {
        Period base = new Period(1, 1, 0, 1, 1, 1, 1, 1, PeriodType.standard());
        Period test = new Period(base);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMinutes());
    }

    public void testConstructor_Object4_8_oe() throws Throwable {
        Period base = new Period(1, 1, 0, 1, 1, 1, 1, 1, PeriodType.standard());
        Period test = new Period(base);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getSeconds());
    }

    public void testConstructor_Object4_9_oe() throws Throwable {
        Period base = new Period(1, 1, 0, 1, 1, 1, 1, 1, PeriodType.standard());
        Period test = new Period(base);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getMillis());
    }

    public void testConstructor_Object_PeriodType1_1_oe() throws Throwable {
        Period test = new Period("P1Y2M3D", PeriodType.yearMonthDayTime());
        assertEquals(PeriodType.yearMonthDayTime(), test.getPeriodType());
    }

    public void testConstructor_Object_PeriodType1_2_oe() throws Throwable {
        Period test = new Period("P1Y2M3D", PeriodType.yearMonthDayTime());
        // removed other assertion
        assertEquals(1, test.getYears());
    }

    public void testConstructor_Object_PeriodType1_3_oe() throws Throwable {
        Period test = new Period("P1Y2M3D", PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getMonths());
    }

    public void testConstructor_Object_PeriodType1_4_oe() throws Throwable {
        Period test = new Period("P1Y2M3D", PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_Object_PeriodType1_5_oe() throws Throwable {
        Period test = new Period("P1Y2M3D", PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.getDays());
    }

    public void testConstructor_Object_PeriodType1_6_oe() throws Throwable {
        Period test = new Period("P1Y2M3D", PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_Object_PeriodType1_7_oe() throws Throwable {
        Period test = new Period("P1Y2M3D", PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_Object_PeriodType1_8_oe() throws Throwable {
        Period test = new Period("P1Y2M3D", PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_Object_PeriodType1_9_oe() throws Throwable {
        Period test = new Period("P1Y2M3D", PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_Object_PeriodType2_1_oe() throws Throwable {
        Period test = new Period((Object) null, PeriodType.yearMonthDayTime());
        assertEquals(PeriodType.yearMonthDayTime(), test.getPeriodType());
    }

    public void testConstructor_Object_PeriodType2_2_oe() throws Throwable {
        Period test = new Period((Object) null, PeriodType.yearMonthDayTime());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_Object_PeriodType2_3_oe() throws Throwable {
        Period test = new Period((Object) null, PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_Object_PeriodType2_4_oe() throws Throwable {
        Period test = new Period((Object) null, PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_Object_PeriodType2_5_oe() throws Throwable {
        Period test = new Period((Object) null, PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_Object_PeriodType2_6_oe() throws Throwable {
        Period test = new Period((Object) null, PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testConstructor_Object_PeriodType2_7_oe() throws Throwable {
        Period test = new Period((Object) null, PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testConstructor_Object_PeriodType2_8_oe() throws Throwable {
        Period test = new Period((Object) null, PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testConstructor_Object_PeriodType2_9_oe() throws Throwable {
        Period test = new Period((Object) null, PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testConstructor_Object_PeriodType3_1_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), PeriodType.yearMonthDayTime());
        assertEquals(PeriodType.yearMonthDayTime(), test.getPeriodType());
    }

    public void testConstructor_Object_PeriodType3_2_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), PeriodType.yearMonthDayTime());
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_Object_PeriodType3_3_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_Object_PeriodType3_4_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_Object_PeriodType3_5_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_Object_PeriodType3_6_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_Object_PeriodType3_7_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getMinutes());
    }

    public void testConstructor_Object_PeriodType3_8_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.getSeconds());
    }

    public void testConstructor_Object_PeriodType3_9_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), PeriodType.yearMonthDayTime());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMillis());
    }

    public void testConstructor_Object_PeriodType4_1_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), (PeriodType) null);
        assertEquals(PeriodType.dayTime(), test.getPeriodType());
    }

    public void testConstructor_Object_PeriodType4_2_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), (PeriodType) null);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testConstructor_Object_PeriodType4_3_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testConstructor_Object_PeriodType4_4_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testConstructor_Object_PeriodType4_5_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testConstructor_Object_PeriodType4_6_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHours());
    }

    public void testConstructor_Object_PeriodType4_7_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getMinutes());
    }

    public void testConstructor_Object_PeriodType4_8_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.getSeconds());
    }

    public void testConstructor_Object_PeriodType4_9_oe() throws Throwable {
        Period test = new Period(new Period(0, 0, 0, 0, 1, 2, 3, 4, PeriodType.dayTime()), (PeriodType) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMillis());
    }

    public void testFactoryYears_1_oe() throws Throwable {
        Period test = Period.years(6);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testFactoryYears_2_oe() throws Throwable {
        Period test = Period.years(6);
        // removed other assertion
        assertEquals(6, test.getYears());
    }

    public void testFactoryYears_3_oe() throws Throwable {
        Period test = Period.years(6);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testFactoryYears_4_oe() throws Throwable {
        Period test = Period.years(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testFactoryYears_5_oe() throws Throwable {
        Period test = Period.years(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testFactoryYears_6_oe() throws Throwable {
        Period test = Period.years(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testFactoryYears_7_oe() throws Throwable {
        Period test = Period.years(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testFactoryYears_8_oe() throws Throwable {
        Period test = Period.years(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testFactoryYears_9_oe() throws Throwable {
        Period test = Period.years(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testFactoryMonths_1_oe() throws Throwable {
        Period test = Period.months(6);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testFactoryMonths_2_oe() throws Throwable {
        Period test = Period.months(6);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testFactoryMonths_3_oe() throws Throwable {
        Period test = Period.months(6);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonths());
    }

    public void testFactoryMonths_4_oe() throws Throwable {
        Period test = Period.months(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testFactoryMonths_5_oe() throws Throwable {
        Period test = Period.months(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testFactoryMonths_6_oe() throws Throwable {
        Period test = Period.months(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testFactoryMonths_7_oe() throws Throwable {
        Period test = Period.months(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testFactoryMonths_8_oe() throws Throwable {
        Period test = Period.months(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testFactoryMonths_9_oe() throws Throwable {
        Period test = Period.months(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testFactoryWeeks_1_oe() throws Throwable {
        Period test = Period.weeks(6);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testFactoryWeeks_2_oe() throws Throwable {
        Period test = Period.weeks(6);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testFactoryWeeks_3_oe() throws Throwable {
        Period test = Period.weeks(6);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testFactoryWeeks_4_oe() throws Throwable {
        Period test = Period.weeks(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getWeeks());
    }

    public void testFactoryWeeks_5_oe() throws Throwable {
        Period test = Period.weeks(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testFactoryWeeks_6_oe() throws Throwable {
        Period test = Period.weeks(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testFactoryWeeks_7_oe() throws Throwable {
        Period test = Period.weeks(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testFactoryWeeks_8_oe() throws Throwable {
        Period test = Period.weeks(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testFactoryWeeks_9_oe() throws Throwable {
        Period test = Period.weeks(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testFactoryDays_1_oe() throws Throwable {
        Period test = Period.days(6);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testFactoryDays_2_oe() throws Throwable {
        Period test = Period.days(6);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testFactoryDays_3_oe() throws Throwable {
        Period test = Period.days(6);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testFactoryDays_4_oe() throws Throwable {
        Period test = Period.days(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testFactoryDays_5_oe() throws Throwable {
        Period test = Period.days(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDays());
    }

    public void testFactoryDays_6_oe() throws Throwable {
        Period test = Period.days(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testFactoryDays_7_oe() throws Throwable {
        Period test = Period.days(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testFactoryDays_8_oe() throws Throwable {
        Period test = Period.days(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testFactoryDays_9_oe() throws Throwable {
        Period test = Period.days(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testFactoryHours_1_oe() throws Throwable {
        Period test = Period.hours(6);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testFactoryHours_2_oe() throws Throwable {
        Period test = Period.hours(6);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testFactoryHours_3_oe() throws Throwable {
        Period test = Period.hours(6);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testFactoryHours_4_oe() throws Throwable {
        Period test = Period.hours(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testFactoryHours_5_oe() throws Throwable {
        Period test = Period.hours(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testFactoryHours_6_oe() throws Throwable {
        Period test = Period.hours(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getHours());
    }

    public void testFactoryHours_7_oe() throws Throwable {
        Period test = Period.hours(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testFactoryHours_8_oe() throws Throwable {
        Period test = Period.hours(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testFactoryHours_9_oe() throws Throwable {
        Period test = Period.hours(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testFactoryMinutes_1_oe() throws Throwable {
        Period test = Period.minutes(6);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testFactoryMinutes_2_oe() throws Throwable {
        Period test = Period.minutes(6);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testFactoryMinutes_3_oe() throws Throwable {
        Period test = Period.minutes(6);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testFactoryMinutes_4_oe() throws Throwable {
        Period test = Period.minutes(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testFactoryMinutes_5_oe() throws Throwable {
        Period test = Period.minutes(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testFactoryMinutes_6_oe() throws Throwable {
        Period test = Period.minutes(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testFactoryMinutes_7_oe() throws Throwable {
        Period test = Period.minutes(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMinutes());
    }

    public void testFactoryMinutes_8_oe() throws Throwable {
        Period test = Period.minutes(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testFactoryMinutes_9_oe() throws Throwable {
        Period test = Period.minutes(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testFactorySeconds_1_oe() throws Throwable {
        Period test = Period.seconds(6);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testFactorySeconds_2_oe() throws Throwable {
        Period test = Period.seconds(6);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testFactorySeconds_3_oe() throws Throwable {
        Period test = Period.seconds(6);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testFactorySeconds_4_oe() throws Throwable {
        Period test = Period.seconds(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testFactorySeconds_5_oe() throws Throwable {
        Period test = Period.seconds(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testFactorySeconds_6_oe() throws Throwable {
        Period test = Period.seconds(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testFactorySeconds_7_oe() throws Throwable {
        Period test = Period.seconds(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testFactorySeconds_8_oe() throws Throwable {
        Period test = Period.seconds(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getSeconds());
    }

    public void testFactorySeconds_9_oe() throws Throwable {
        Period test = Period.seconds(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

    public void testFactoryMillis_1_oe() throws Throwable {
        Period test = Period.millis(6);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testFactoryMillis_2_oe() throws Throwable {
        Period test = Period.millis(6);
        // removed other assertion
        assertEquals(0, test.getYears());
    }

    public void testFactoryMillis_3_oe() throws Throwable {
        Period test = Period.millis(6);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMonths());
    }

    public void testFactoryMillis_4_oe() throws Throwable {
        Period test = Period.millis(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testFactoryMillis_5_oe() throws Throwable {
        Period test = Period.millis(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getDays());
    }

    public void testFactoryMillis_6_oe() throws Throwable {
        Period test = Period.millis(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testFactoryMillis_7_oe() throws Throwable {
        Period test = Period.millis(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testFactoryMillis_8_oe() throws Throwable {
        Period test = Period.millis(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testFactoryMillis_9_oe() throws Throwable {
        Period test = Period.millis(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMillis());
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_standardYear_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2011, 1, 1, 0, 0);
        DateTime dt2 = new DateTime(2011, 2, 28, 0, 0);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_standardYear_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2011, 1, 1, 0, 0);
        DateTime dt2 = new DateTime(2011, 2, 28, 0, 0);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 1, 3, 6, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_leapYear_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2012, 1, 1, 0, 0);
        DateTime dt2 = new DateTime(2012, 2, 29, 0, 0);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_leapYear_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2012, 1, 1, 0, 0);
        DateTime dt2 = new DateTime(2012, 2, 29, 0, 0);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 1, 4, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_exactMonths_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 12, 28, 0, 0);
        DateTime dt2 = new DateTime(2005, 2, 28, 0, 0);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_exactMonths_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 12, 28, 0, 0);
        DateTime dt2 = new DateTime(2005, 2, 28, 0, 0);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 2, 0, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_endOfMonth1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 12, 29, 0, 0);
        DateTime dt2 = new DateTime(2005, 2, 28, 0, 0);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_endOfMonth1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 12, 29, 0, 0);
        DateTime dt2 = new DateTime(2005, 2, 28, 0, 0);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 2, 0, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_endOfMonth2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 12, 30, 0, 0);
        DateTime dt2 = new DateTime(2005, 2, 28, 0, 0);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_endOfMonth2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 12, 30, 0, 0);
        DateTime dt2 = new DateTime(2005, 2, 28, 0, 0);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 2, 0, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_endOfMonth3_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 12, 31, 0, 0);
        DateTime dt2 = new DateTime(2005, 2, 28, 0, 0);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_RI_RI_toFeb_endOfMonth3_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 12, 31, 0, 0);
        DateTime dt2 = new DateTime(2005, 2, 28, 0, 0);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 2, 0, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_RI_RI_toMar_endOfMonth1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2013, 1, 31, 0, 0);
        DateTime dt2 = new DateTime(2013, 3, 30, 0, 0);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_RI_RI_toMar_endOfMonth1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2013, 1, 31, 0, 0);
        DateTime dt2 = new DateTime(2013, 3, 30, 0, 0);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 1, 4, 2, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_RI_RI_toMar_endOfMonth2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2013, 1, 31, 0, 0);
        DateTime dt2 = new DateTime(2013, 3, 31, 0, 0);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_RI_RI_toMar_endOfMonth2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2013, 1, 31, 0, 0);
        DateTime dt2 = new DateTime(2013, 3, 31, 0, 0);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 2, 0, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_standardYear_1_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2011, 1, 1);
        LocalDate dt2 = new LocalDate(2011, 2, 28);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_standardYear_2_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2011, 1, 1);
        LocalDate dt2 = new LocalDate(2011, 2, 28);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 1, 3, 6, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_leapYear_1_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2012, 1, 1);
        LocalDate dt2 = new LocalDate(2012, 2, 29);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_leapYear_2_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2012, 1, 1);
        LocalDate dt2 = new LocalDate(2012, 2, 29);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 1, 4, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_exactMonths_1_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 12, 28);
        LocalDate dt2 = new LocalDate(2005, 2, 28);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_exactMonths_2_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 12, 28);
        LocalDate dt2 = new LocalDate(2005, 2, 28);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 2, 0, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_endOfMonth1_1_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 12, 29);
        LocalDate dt2 = new LocalDate(2005, 2, 28);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_endOfMonth1_2_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 12, 29);
        LocalDate dt2 = new LocalDate(2005, 2, 28);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 2, 0, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_endOfMonth2_1_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 12, 30);
        LocalDate dt2 = new LocalDate(2005, 2, 28);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_endOfMonth2_2_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 12, 30);
        LocalDate dt2 = new LocalDate(2005, 2, 28);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 2, 0, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_endOfMonth3_1_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 12, 31);
        LocalDate dt2 = new LocalDate(2005, 2, 28);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_LD_LD_toFeb_endOfMonth3_2_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2004, 12, 31);
        LocalDate dt2 = new LocalDate(2005, 2, 28);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 2, 0, 0, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_LD_LD_toMar_endOfMonth1_1_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2013, 1, 31);
        LocalDate dt2 = new LocalDate(2013, 3, 30);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_LD_LD_toMar_endOfMonth1_2_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2013, 1, 31);
        LocalDate dt2 = new LocalDate(2013, 3, 30);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 1, 4, 2, 0, 0, 0, 0), test);
    }

    public void testConstructor_trickyDifferences_LD_LD_toMar_endOfMonth2_1_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2013, 1, 31);
        LocalDate dt2 = new LocalDate(2013, 3, 31);
        Period test = new Period(dt1, dt2);
        assertEquals(PeriodType.standard(), test.getPeriodType());
    }

    public void testConstructor_trickyDifferences_LD_LD_toMar_endOfMonth2_2_oe() throws Throwable {
        LocalDate dt1 = new LocalDate(2013, 1, 31);
        LocalDate dt2 = new LocalDate(2013, 3, 31);
        Period test = new Period(dt1, dt2);
        // removed other assertion
        assertEquals(new Period(0, 2, 0, 0, 0, 0, 0, 0), test);
    }

    public void testFactoryFieldDifference1_1_oe() throws Throwable {
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        Partial end = new Partial(types, new int[] {2004, 6, 7});
        Period test = Period.fieldDifference(start, end);
        assertEquals(PeriodType.yearMonthDay(), test.getPeriodType());
    }

    public void testFactoryFieldDifference1_2_oe() throws Throwable {
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        Partial end = new Partial(types, new int[] {2004, 6, 7});
        Period test = Period.fieldDifference(start, end);
        // removed other assertion
        assertEquals(-1, test.getYears());
    }

    public void testFactoryFieldDifference1_3_oe() throws Throwable {
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        Partial end = new Partial(types, new int[] {2004, 6, 7});
        Period test = Period.fieldDifference(start, end);
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getMonths());
    }

    public void testFactoryFieldDifference1_4_oe() throws Throwable {
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        Partial end = new Partial(types, new int[] {2004, 6, 7});
        Period test = Period.fieldDifference(start, end);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getWeeks());
    }

    public void testFactoryFieldDifference1_5_oe() throws Throwable {
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        Partial end = new Partial(types, new int[] {2004, 6, 7});
        Period test = Period.fieldDifference(start, end);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-2, test.getDays());
    }

    public void testFactoryFieldDifference1_6_oe() throws Throwable {
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        Partial end = new Partial(types, new int[] {2004, 6, 7});
        Period test = Period.fieldDifference(start, end);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getHours());
    }

    public void testFactoryFieldDifference1_7_oe() throws Throwable {
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        Partial end = new Partial(types, new int[] {2004, 6, 7});
        Period test = Period.fieldDifference(start, end);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinutes());
    }

    public void testFactoryFieldDifference1_8_oe() throws Throwable {
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        Partial end = new Partial(types, new int[] {2004, 6, 7});
        Period test = Period.fieldDifference(start, end);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSeconds());
    }

    public void testFactoryFieldDifference1_9_oe() throws Throwable {
        YearMonthDay start = new YearMonthDay(2005, 4, 9);
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        Partial end = new Partial(types, new int[] {2004, 6, 7});
        Period test = Period.fieldDifference(start, end);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillis());
    }

}
