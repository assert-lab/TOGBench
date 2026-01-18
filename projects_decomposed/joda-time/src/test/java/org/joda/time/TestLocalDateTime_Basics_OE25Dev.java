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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for LocalDate.
 *
 * @author Stephen Colebourne
 */
public class TestLocalDateTime_Basics_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final DateTimeZone NEW_YORK = DateTimeZone.forID("America/New_York");
    private static final GJChronology GJ_UTC = GJChronology.getInstanceUTC();
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
    private static final Chronology COPTIC_LONDON = CopticChronology.getInstance(LONDON);
    private static final Chronology COPTIC_TOKYO = CopticChronology.getInstance(TOKYO);
    private static final Chronology COPTIC_UTC = CopticChronology.getInstanceUTC();
    private static final Chronology ISO_LONDON = ISOChronology.getInstance(LONDON);
    private static final Chronology ISO_NEW_YORK = ISOChronology.getInstance(NEW_YORK);
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final Chronology GREGORIAN_UTC = GregorianChronology.getInstanceUTC();
    private static final Chronology BUDDHIST_LONDON = BuddhistChronology.getInstance(LONDON);
    private static final Chronology BUDDHIST_TOKYO = BuddhistChronology.getInstance(TOKYO);

//    private long TEST_TIME1 =
//        (31L + 28L + 31L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
//        + 12L * DateTimeConstants.MILLIS_PER_HOUR
//        + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
//
//    private long TEST_TIME2 =
//        (365L + 31L + 28L + 31L + 30L + 7L -1L) * DateTimeConstants.MILLIS_PER_DAY
//        + 14L * DateTimeConstants.MILLIS_PER_HOUR
//        + 28L * DateTimeConstants.MILLIS_PER_MINUTE;

    private int MILLIS_OF_DAY_UTC =
        (int) (10L * DateTimeConstants.MILLIS_PER_HOUR
        + 20L * DateTimeConstants.MILLIS_PER_MINUTE
        + 30L * DateTimeConstants.MILLIS_PER_SECOND
        + 40L);

    private long TEST_TIME_NOW_UTC =
        (31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY + MILLIS_OF_DAY_UTC;

    private DateTimeZone zone = null;

    private Locale systemDefaultLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestLocalDateTime_Basics_OE25Dev.class);
    }

    public TestLocalDateTime_Basics_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW_UTC);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(LONDON);
        systemDefaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        zone = null;
        Locale.setDefault(systemDefaultLocale);
        systemDefaultLocale = null;
    }

    //-----------------------------------------------------------------------
    
    class MockInstant extends MockPartial {
        @Override
        public Chronology getChronology() {
            return COPTIC_UTC;
        }
        @Override
        public DateTimeField[] getFields() {
            return new DateTimeField[] {
                COPTIC_UTC.year(),
                COPTIC_UTC.monthOfYear(),
                COPTIC_UTC.dayOfMonth(),
                COPTIC_UTC.millisOfDay(),
            };
        }
        @Override
        public int[] getValues() {
            return new int[] {1970, 6, 9, MILLIS_OF_DAY_UTC};
        }
    }

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testWithDate() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withDate(2006, 2, 1);
        
        check(test, 2004, 6, 9, 10, 20, 30, 40);
        check(result, 2006, 2, 1, 10, 20, 30, 40);
    }

    //-----------------------------------------------------------------------
    public void testWithTime() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withTime(9, 8, 7, 6);
        
        check(test, 2004, 6, 9, 10, 20, 30, 40);
        check(result, 2004, 6, 9, 9, 8, 7, 6);
    }

    //-----------------------------------------------------------------------
    public void testWithField_DateTimeFieldType_int_1() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(new LocalDateTime(2004, 6, 9, 10, 20, 30, 40), test);
        assertEquals(new LocalDateTime(2006, 6, 9, 10, 20, 30, 40), result);
    }

    public void testWithField_DateTimeFieldType_int_2() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        try {
            test.withField(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithField_DateTimeFieldType_int_3() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withField(DateTimeFieldType.year(), 2004);
        assertEquals(new LocalDateTime(2004, 6, 9, 10, 20, 30, 40), test);
        assertEquals(test, result);
    }

    //-----------------------------------------------------------------------
    public void testWithFieldAdded_DurationFieldType_int_1() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(new LocalDateTime(2004, 6, 9, 10, 20, 30, 40), test);
        assertEquals(new LocalDateTime(2010, 6, 9, 10, 20, 30, 40), result);
    }

    public void testWithFieldAdded_DurationFieldType_int_2() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        try {
            test.withFieldAdded(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_DurationFieldType_int_3() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        try {
            test.withFieldAdded(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_DurationFieldType_int_4() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertEquals(test, result);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testWithers() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        check(test.withYear(2000), 2000, 6, 9, 10, 20, 30, 40);
        check(test.withMonthOfYear(2), 1970, 2, 9, 10, 20, 30, 40);
        check(test.withDayOfMonth(2), 1970, 6, 2, 10, 20, 30, 40);
        check(test.withDayOfYear(6), 1970, 1, 6, 10, 20, 30, 40);
        check(test.withDayOfWeek(6), 1970, 6, 13, 10, 20, 30, 40);
        check(test.withWeekOfWeekyear(6), 1970, 2, 3, 10, 20, 30, 40);
        check(test.withWeekyear(1971), 1971, 6, 15, 10, 20, 30, 40);
        check(test.withYearOfCentury(60), 1960, 6, 9, 10, 20, 30, 40);
        check(test.withCenturyOfEra(21), 2070, 6, 9, 10, 20, 30, 40);
        check(test.withYearOfEra(1066), 1066, 6, 9, 10, 20, 30, 40);
        check(test.withEra(DateTimeConstants.BC), -1970, 6, 9, 10, 20, 30, 40);
        check(test.withHourOfDay(6), 1970, 6, 9, 6, 20, 30, 40);
        check(test.withMinuteOfHour(6), 1970, 6, 9, 10, 6, 30, 40);
        check(test.withSecondOfMinute(6), 1970, 6, 9, 10, 20, 6, 40);
        check(test.withMillisOfSecond(6), 1970, 6, 9, 10, 20, 30, 6);
        check(test.withMillisOfDay(61234), 1970, 6, 9, 0, 1, 1, 234);
        try {
            test.withMonthOfYear(0);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.withMonthOfYear(13);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testToDateTime_Zone_dstGap() {
        LocalDateTime base = new LocalDateTime(2014, 3, 30, 1, 30, 0, 0, ISO_LONDON);
        try {
            base.toDateTime(LONDON);
            fail();
        } catch (IllegalInstantException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testToDate_springDST() {
        LocalDateTime base = new LocalDateTime(2007, 4, 2, 0, 20, 0, 0);
        
        SimpleTimeZone testZone = new SimpleTimeZone(3600000, "NoMidnight",
                Calendar.APRIL, 2, 0, 0, Calendar.OCTOBER, 2, 0, 3600000);
        TimeZone currentZone = TimeZone.getDefault();
        try {
            TimeZone.setDefault(testZone);
            Date test = base.toDate();
            check(base, 2007, 4, 2, 0, 20, 0, 0);
            assertEquals("Mon Apr 02 01:00:00 GMT+02:00 2007", test.toString());
        } finally {
            TimeZone.setDefault(currentZone);
        }
    }

    public void testToDate_springDST_2Hour40Savings() {
        LocalDateTime base = new LocalDateTime(2007, 4, 2, 0, 20, 0, 0);
        
        SimpleTimeZone testZone = new SimpleTimeZone(3600000, "NoMidnight",
                Calendar.APRIL, 2, 0, 0, Calendar.OCTOBER, 2, 0, 3600000, (3600000 / 6) * 16);
        TimeZone currentZone = TimeZone.getDefault();
        try {
            TimeZone.setDefault(testZone);
            Date test = base.toDate();
            check(base, 2007, 4, 2, 0, 20, 0, 0);
            assertEquals("Mon Apr 02 02:40:00 GMT+03:40 2007", test.toString());
        } finally {
            TimeZone.setDefault(currentZone);
        }
    }

    public void testToDate_autumnDST() {
        LocalDateTime base = new LocalDateTime(2007, 10, 2, 0, 20, 30, 0);
        
        SimpleTimeZone testZone = new SimpleTimeZone(3600000, "NoMidnight",
                Calendar.APRIL, 2, 0, 0, Calendar.OCTOBER, 2, 0, 3600000);
        TimeZone currentZone = TimeZone.getDefault();
        try {
            TimeZone.setDefault(testZone);
            Date test = base.toDate();
            check(base, 2007, 10, 2, 0, 20, 30, 0);
            assertEquals("Tue Oct 02 00:20:30 GMT+02:00 2007", test.toString());
        } finally {
            TimeZone.setDefault(currentZone);
        }
    }

    //-----------------------------------------------------------------------

    public void testToDate_springDST_Zone() {
        LocalDateTime base = new LocalDateTime(2007, 4, 2, 0, 20, 0, 0);
        
        SimpleTimeZone testZone = new SimpleTimeZone(3600000, "NoMidnight",
                Calendar.APRIL, 2, 0, 0, Calendar.OCTOBER, 2, 0, 3600000);
        TimeZone currentZone = TimeZone.getDefault();
        try {
            TimeZone.setDefault(testZone);
            Date test = base.toDate(TimeZone.getDefault());
            check(base, 2007, 4, 2, 0, 20, 0, 0);
            assertEquals("Mon Apr 02 01:00:00 GMT+02:00 2007", test.toString());
        } finally {
            TimeZone.setDefault(currentZone);
        }
    }

    public void testToDate_springDST_2Hour40Savings_Zone() {
        LocalDateTime base = new LocalDateTime(2007, 4, 2, 0, 20, 0, 0);
        
        SimpleTimeZone testZone = new SimpleTimeZone(3600000, "NoMidnight",
                Calendar.APRIL, 2, 0, 0, Calendar.OCTOBER, 2, 0, 3600000, (3600000 / 6) * 16);
        TimeZone currentZone = TimeZone.getDefault();
        try {
            TimeZone.setDefault(testZone);
            Date test = base.toDate(TimeZone.getDefault());
            check(base, 2007, 4, 2, 0, 20, 0, 0);
            assertEquals("Mon Apr 02 02:40:00 GMT+03:40 2007", test.toString());
        } finally {
            TimeZone.setDefault(currentZone);
        }
    }

    public void testToDate_autumnDST_Zone() {
        LocalDateTime base = new LocalDateTime(2007, 10, 2, 0, 20, 30, 0);
        
        SimpleTimeZone testZone = new SimpleTimeZone(3600000, "NoMidnight",
                Calendar.APRIL, 2, 0, 0, Calendar.OCTOBER, 2, 0, 3600000);
        TimeZone currentZone = TimeZone.getDefault();
        try {
            TimeZone.setDefault(testZone);
            Date test = base.toDate(TimeZone.getDefault());
            check(base, 2007, 10, 2, 0, 20, 30, 0);
            assertEquals("Tue Oct 02 00:20:30 GMT+02:00 2007", test.toString());
        } finally {
            TimeZone.setDefault(currentZone);
        }
    }
    
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    private void check(LocalDateTime test, int year, int month, int day, int hour, int min, int sec, int mil) {
        assertEquals(year, test.getYear());
        assertEquals(month, test.getMonthOfYear());
        assertEquals(day, test.getDayOfMonth());
        assertEquals(hour, test.getHourOfDay());
        assertEquals(min, test.getMinuteOfHour());
        assertEquals(sec, test.getSecondOfMinute());
        assertEquals(mil, test.getMillisOfSecond());
    }

    public void testGet_DateTimeFieldType_1_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        assertEquals(1970, test.get(DateTimeFieldType.year()));
    }

    public void testGet_DateTimeFieldType_2_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        assertEquals(6, test.get(DateTimeFieldType.monthOfYear()));
    }

    public void testGet_DateTimeFieldType_3_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.get(DateTimeFieldType.dayOfMonth()));
    }

    public void testGet_DateTimeFieldType_4_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.get(DateTimeFieldType.dayOfWeek()));
    }

    public void testGet_DateTimeFieldType_5_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(160, test.get(DateTimeFieldType.dayOfYear()));
    }

    public void testGet_DateTimeFieldType_6_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(24, test.get(DateTimeFieldType.weekOfWeekyear()));
    }

    public void testGet_DateTimeFieldType_7_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1970, test.get(DateTimeFieldType.weekyear()));
    }

    public void testGet_DateTimeFieldType_8_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, test.get(DateTimeFieldType.hourOfDay()));
    }

    public void testGet_DateTimeFieldType_9_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(20, test.get(DateTimeFieldType.minuteOfHour()));
    }

    public void testGet_DateTimeFieldType_10_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(30, test.get(DateTimeFieldType.secondOfMinute()));
    }

    public void testGet_DateTimeFieldType_11_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(40, test.get(DateTimeFieldType.millisOfSecond()));
    }

    public void testGet_DateTimeFieldType_12_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(MILLIS_OF_DAY_UTC / 60000 , test.get(DateTimeFieldType.minuteOfDay()));
    }

    public void testGet_DateTimeFieldType_13_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(MILLIS_OF_DAY_UTC / 1000 , test.get(DateTimeFieldType.secondOfDay()));
    }

    public void testGet_DateTimeFieldType_14_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(MILLIS_OF_DAY_UTC , test.get(DateTimeFieldType.millisOfDay()));
    }

    public void testGet_DateTimeFieldType_15_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, test.get(DateTimeFieldType.hourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_16_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.AM, test.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testGet_DateTimeFieldType_17_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        assertEquals(0, test.get(DateTimeFieldType.hourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_18_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        assertEquals(12, test.get(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_19_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        // removed other assertion
        assertEquals(12, test.get(DateTimeFieldType.clockhourOfDay()));
    }

    public void testGet_DateTimeFieldType_20_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.PM, test.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testGet_DateTimeFieldType_21_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 14, 30);
        assertEquals(2, test.get(DateTimeFieldType.hourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_22_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 14, 30);
        // removed other assertion
        assertEquals(2, test.get(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_23_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 14, 30);
        // removed other assertion
        // removed other assertion
        assertEquals(14, test.get(DateTimeFieldType.clockhourOfDay()));
    }

    public void testGet_DateTimeFieldType_24_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 14, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.PM, test.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testGet_DateTimeFieldType_25_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 14, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 0, 30);
        assertEquals(0, test.get(DateTimeFieldType.hourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_26_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 14, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 0, 30);
        // removed other assertion
        assertEquals(12, test.get(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_27_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 14, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 0, 30);
        // removed other assertion
        // removed other assertion
        assertEquals(24, test.get(DateTimeFieldType.clockhourOfDay()));
    }

    public void testGet_DateTimeFieldType_28_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDateTime(1970, 6, 9, 12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 14, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalDateTime(1970, 6, 9, 0, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.AM, test.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testSize_1_oe() {
        LocalDateTime test = new LocalDateTime();
        assertEquals(4, test.size());
    }

    public void testGetFieldType_int_1_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        assertSame(DateTimeFieldType.year(), test.getFieldType(0));
    }

    public void testGetFieldType_int_2_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        // removed other assertion
        assertSame(DateTimeFieldType.monthOfYear(), test.getFieldType(1));
    }

    public void testGetFieldType_int_3_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.dayOfMonth(), test.getFieldType(2));
    }

    public void testGetFieldType_int_4_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.millisOfDay(), test.getFieldType(3));
    }

    public void testGetFieldTypes_1_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        assertSame(DateTimeFieldType.year(), fields[0]);
    }

    public void testGetFieldTypes_2_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        assertSame(DateTimeFieldType.monthOfYear(), fields[1]);
    }

    public void testGetFieldTypes_3_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.dayOfMonth(), fields[2]);
    }

    public void testGetFieldTypes_4_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.millisOfDay(), fields[3]);
    }

    public void testGetFieldTypes_5_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFieldTypes(), test.getFieldTypes());
    }

    public void testGetField_int_1_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        assertSame(COPTIC_UTC.year(), test.getField(0));
    }

    public void testGetField_int_2_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        // removed other assertion
        assertSame(COPTIC_UTC.monthOfYear(), test.getField(1));
    }

    public void testGetField_int_3_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.dayOfMonth(), test.getField(2));
    }

    public void testGetField_int_4_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.millisOfDay(), test.getField(3));
    }

    public void testGetFields_1_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        assertSame(COPTIC_UTC.year(), fields[0]);
    }

    public void testGetFields_2_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        assertSame(COPTIC_UTC.monthOfYear(), fields[1]);
    }

    public void testGetFields_3_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.dayOfMonth(), fields[2]);
    }

    public void testGetFields_4_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.millisOfDay(), fields[3]);
    }

    public void testGetFields_5_oe() {
        LocalDateTime test = new LocalDateTime(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFields(), test.getFields());
    }

    public void testGetValue_int_1_oe() {
        LocalDateTime test = new LocalDateTime(ISO_UTC);
        assertEquals(1970, test.getValue(0));
    }

    public void testGetValue_int_2_oe() {
        LocalDateTime test = new LocalDateTime(ISO_UTC);
        // removed other assertion
        assertEquals(6, test.getValue(1));
    }

    public void testGetValue_int_3_oe() {
        LocalDateTime test = new LocalDateTime(ISO_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getValue(2));
    }

    public void testGetValue_int_4_oe() {
        LocalDateTime test = new LocalDateTime(ISO_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(MILLIS_OF_DAY_UTC, test.getValue(3));
    }

    public void testGetValues_1_oe() {
        LocalDateTime test = new LocalDateTime(ISO_UTC);
        int[] values = test.getValues();
        assertEquals(1970, values[0]);
    }

    public void testGetValues_2_oe() {
        LocalDateTime test = new LocalDateTime(ISO_UTC);
        int[] values = test.getValues();
        // removed other assertion
        assertEquals(6, values[1]);
    }

    public void testGetValues_3_oe() {
        LocalDateTime test = new LocalDateTime(ISO_UTC);
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        assertEquals(9, values[2]);
    }

    public void testGetValues_4_oe() {
        LocalDateTime test = new LocalDateTime(ISO_UTC);
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(MILLIS_OF_DAY_UTC, values[3]);
    }

    public void testGetValues_5_oe() {
        LocalDateTime test = new LocalDateTime(ISO_UTC);
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getValues(), test.getValues());
    }

    public void testIsSupported_DateTimeFieldType_1_oe() {
        LocalDateTime test = new LocalDateTime();
        assertEquals(true, test.isSupported(DateTimeFieldType.year()));
    }

    public void testIsSupported_DateTimeFieldType_2_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.monthOfYear()));
    }

    public void testIsSupported_DateTimeFieldType_3_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.dayOfMonth()));
    }

    public void testIsSupported_DateTimeFieldType_4_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.dayOfWeek()));
    }

    public void testIsSupported_DateTimeFieldType_5_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.dayOfYear()));
    }

    public void testIsSupported_DateTimeFieldType_6_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.weekOfWeekyear()));
    }

    public void testIsSupported_DateTimeFieldType_7_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.weekyear()));
    }

    public void testIsSupported_DateTimeFieldType_8_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.yearOfCentury()));
    }

    public void testIsSupported_DateTimeFieldType_9_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.yearOfEra()));
    }

    public void testIsSupported_DateTimeFieldType_10_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.centuryOfEra()));
    }

    public void testIsSupported_DateTimeFieldType_11_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.weekyearOfCentury()));
    }

    public void testIsSupported_DateTimeFieldType_12_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.era()));
    }

    public void testIsSupported_DateTimeFieldType_13_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(true, test.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_14_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.minuteOfHour()));
    }

    public void testIsSupported_DateTimeFieldType_15_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.secondOfMinute()));
    }

    public void testIsSupported_DateTimeFieldType_16_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.millisOfSecond()));
    }

    public void testIsSupported_DateTimeFieldType_17_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.minuteOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_18_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.secondOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_19_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.millisOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_20_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.hourOfHalfday()));
    }

    public void testIsSupported_DateTimeFieldType_21_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.halfdayOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_22_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testIsSupported_DateTimeFieldType_23_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.clockhourOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_24_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isSupported((DateTimeFieldType) null));
    }

    public void testIsSupported_DurationFieldType_1_oe() {
        LocalDateTime test = new LocalDateTime();
        assertEquals(false, test.isSupported(DurationFieldType.eras()));
    }

    public void testIsSupported_DurationFieldType_2_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        assertEquals(true, test.isSupported(DurationFieldType.centuries()));
    }

    public void testIsSupported_DurationFieldType_3_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DurationFieldType.years()));
    }

    public void testIsSupported_DurationFieldType_4_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DurationFieldType.months()));
    }

    public void testIsSupported_DurationFieldType_5_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DurationFieldType.weekyears()));
    }

    public void testIsSupported_DurationFieldType_6_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DurationFieldType.weeks()));
    }

    public void testIsSupported_DurationFieldType_7_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DurationFieldType.days()));
    }

    public void testIsSupported_DurationFieldType_8_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(true, test.isSupported(DurationFieldType.hours()));
    }

    public void testIsSupported_DurationFieldType_9_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test.isSupported(DurationFieldType.minutes()));
    }

    public void testIsSupported_DurationFieldType_10_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DurationFieldType.seconds()));
    }

    public void testIsSupported_DurationFieldType_11_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DurationFieldType.millis()));
    }

    public void testIsSupported_DurationFieldType_12_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DurationFieldType.halfdays()));
    }

    public void testIsSupported_DurationFieldType_13_oe() {
        LocalDateTime test = new LocalDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isSupported((DurationFieldType) null));
    }

    public void testEqualsHashCode_1_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        assertEquals(false, test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        assertEquals(false, test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test2.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test1.equals("Hello"));
    }

    public void testEqualsHashCode_15_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test1.equals(new MockInstant()));
    }

    public void testEqualsHashCode_16_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        Partial partial = new Partial(
                new DateTimeFieldType[] {
                        DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(),
                        DateTimeFieldType.dayOfMonth(), DateTimeFieldType.millisOfDay()},
                new int[] {1970, 6, 9, MILLIS_OF_DAY_UTC}, COPTIC_PARIS);
        assertEquals(true, test1.equals(partial));
    }

    public void testEqualsHashCode_17_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        Partial partial = new Partial(
                new DateTimeFieldType[] {
                        DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(),
                        DateTimeFieldType.dayOfMonth(), DateTimeFieldType.millisOfDay()},
                new int[] {1970, 6, 9, MILLIS_OF_DAY_UTC}, COPTIC_PARIS);
        // removed other assertion
        assertEquals(true, test1.hashCode() == partial.hashCode());
    }

    public void testEqualsHashCode_18_oe() {
        LocalDateTime test1 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        LocalDateTime test2 = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(1971, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        Partial partial = new Partial(
                new DateTimeFieldType[] {
                        DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(),
                        DateTimeFieldType.dayOfMonth(), DateTimeFieldType.millisOfDay()},
                new int[] {1970, 6, 9, MILLIS_OF_DAY_UTC}, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.equals(MockPartial.EMPTY_INSTANCE));
    }

    public void testCompareTo_1_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        assertEquals(0, test1.compareTo(test1a));
    }

    public void testCompareTo_2_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_3_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test1.compareTo(test1));
    }

    public void testCompareTo_4_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test1a.compareTo(test1a));
    }

    public void testCompareTo_5_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        assertEquals(-1, test1.compareTo(test2));
    }

    public void testCompareTo_6_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        assertEquals(+1, test2.compareTo(test1));
    }

    public void testCompareTo_7_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        assertEquals(-1, test1.compareTo(test3));
    }

    public void testCompareTo_8_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        // removed other assertion
        assertEquals(+1, test3.compareTo(test1));
    }

    public void testCompareTo_9_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test3.compareTo(test2));
    }

    public void testCompareTo_10_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
            DateTimeFieldType.millisOfDay(),
        };
        int[] values = new int[] {2005, 6, 2, MILLIS_OF_DAY_UTC};
        Partial p = new Partial(types, values);
        assertEquals(0, test1.compareTo(p));
    }

    public void testIsEqual_LocalDateTime_1_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_LocalDateTime_2_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_LocalDateTime_3_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.isEqual(test1));
    }

    public void testIsEqual_LocalDateTime_4_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1a.isEqual(test1a));
    }

    public void testIsEqual_LocalDateTime_5_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        assertEquals(false, test1.isEqual(test2));
    }

    public void testIsEqual_LocalDateTime_6_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        assertEquals(false, test2.isEqual(test1));
    }

    public void testIsEqual_LocalDateTime_7_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        assertEquals(false, test1.isEqual(test3));
    }

    public void testIsEqual_LocalDateTime_8_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        // removed other assertion
        assertEquals(false, test3.isEqual(test1));
    }

    public void testIsEqual_LocalDateTime_9_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test3.isEqual(test2));
    }

    public void testIsBefore_LocalDateTime_1_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        assertEquals(false, test1.isBefore(test1a));
    }

    public void testIsBefore_LocalDateTime_2_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        assertEquals(false, test1a.isBefore(test1));
    }

    public void testIsBefore_LocalDateTime_3_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isBefore(test1));
    }

    public void testIsBefore_LocalDateTime_4_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1a.isBefore(test1a));
    }

    public void testIsBefore_LocalDateTime_5_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        assertEquals(true, test1.isBefore(test2));
    }

    public void testIsBefore_LocalDateTime_6_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        assertEquals(false, test2.isBefore(test1));
    }

    public void testIsBefore_LocalDateTime_7_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        assertEquals(true, test1.isBefore(test3));
    }

    public void testIsBefore_LocalDateTime_8_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        // removed other assertion
        assertEquals(false, test3.isBefore(test1));
    }

    public void testIsBefore_LocalDateTime_9_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.isBefore(test2));
    }

    public void testIsAfter_LocalDateTime_1_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        assertEquals(false, test1.isAfter(test1a));
    }

    public void testIsAfter_LocalDateTime_2_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        assertEquals(false, test1a.isAfter(test1));
    }

    public void testIsAfter_LocalDateTime_3_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isAfter(test1));
    }

    public void testIsAfter_LocalDateTime_4_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1a.isAfter(test1a));
    }

    public void testIsAfter_LocalDateTime_5_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        assertEquals(false, test1.isAfter(test2));
    }

    public void testIsAfter_LocalDateTime_6_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        assertEquals(true, test2.isAfter(test1));
    }

    public void testIsAfter_LocalDateTime_7_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        assertEquals(false, test1.isAfter(test3));
    }

    public void testIsAfter_LocalDateTime_8_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        // removed other assertion
        assertEquals(true, test3.isAfter(test1));
    }

    public void testIsAfter_LocalDateTime_9_oe() {
        LocalDateTime test1 = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        LocalDateTime test1a = new LocalDateTime(2005, 6, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test2 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        
        LocalDateTime test3 = new LocalDateTime(2005, 7, 2, 10, 20, 30, 40, GREGORIAN_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.isAfter(test2));
    }

    public void testWithField_DateTimeFieldType_int_1_1_oe() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(new LocalDateTime(2004, 6, 9, 10, 20, 30, 40), test);
    }

    public void testWithField_DateTimeFieldType_int_1_2_oe() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withField(DateTimeFieldType.year(), 2006);
        
        // removed other assertion
        assertEquals(new LocalDateTime(2006, 6, 9, 10, 20, 30, 40), result);
    }

    public void testWithField_DateTimeFieldType_int_3_1_oe() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withField(DateTimeFieldType.year(), 2004);
        assertEquals(new LocalDateTime(2004, 6, 9, 10, 20, 30, 40), test);
    }

    public void testWithField_DateTimeFieldType_int_3_2_oe() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withField(DateTimeFieldType.year(), 2004);
        // removed other assertion
        assertSame(test, result);
    }

    public void testWithFieldAdded_DurationFieldType_int_1_1_oe() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(new LocalDateTime(2004, 6, 9, 10, 20, 30, 40), test);
    }

    public void testWithFieldAdded_DurationFieldType_int_1_2_oe() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        // removed other assertion
        assertEquals(new LocalDateTime(2010, 6, 9, 10, 20, 30, 40), result);
    }

    public void testWithFieldAdded_DurationFieldType_int_4_1_oe() {
        LocalDateTime test = new LocalDateTime(2004, 6, 9, 10, 20, 30, 40);
        LocalDateTime result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertSame(test, result);
    }

    public void testPlus_RP_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plus(new Period(1, 2, 3, 4, 29, 6, 7, 8));
        LocalDateTime expected = new LocalDateTime(2003, 7, 29, 15, 26, 37, 48, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testPlus_RP_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plus(new Period(1, 2, 3, 4, 29, 6, 7, 8));
        LocalDateTime expected = new LocalDateTime(2003, 7, 29, 15, 26, 37, 48, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plus((ReadablePeriod) null);
        assertSame(test, result);
    }

    public void testPlusYears_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusYears(1);
        LocalDateTime expected = new LocalDateTime(2003, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testPlusYears_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusYears(1);
        LocalDateTime expected = new LocalDateTime(2003, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusYears(0);
        assertSame(test, result);
    }

    public void testPlusMonths_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusMonths(1);
        LocalDateTime expected = new LocalDateTime(2002, 6, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testPlusMonths_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusMonths(1);
        LocalDateTime expected = new LocalDateTime(2002, 6, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusMonths(0);
        assertSame(test, result);
    }

    public void testPlusWeeks_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusWeeks(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 10, 10, 20, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testPlusWeeks_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusWeeks(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 10, 10, 20, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusWeeks(0);
        assertSame(test, result);
    }

    public void testPlusDays_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusDays(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 4, 10, 20, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testPlusDays_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusDays(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 4, 10, 20, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusDays(0);
        assertSame(test, result);
    }

    public void testPlusHours_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusHours(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 11, 20, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testPlusHours_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusHours(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 11, 20, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusHours(0);
        assertSame(test, result);
    }

    public void testPlusMinutes_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusMinutes(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 21, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testPlusMinutes_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusMinutes(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 21, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusMinutes(0);
        assertSame(test, result);
    }

    public void testPlusSeconds_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusSeconds(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 20, 31, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testPlusSeconds_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusSeconds(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 20, 31, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusSeconds(0);
        assertSame(test, result);
    }

    public void testPlusMillis_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusMillis(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 20, 30, 41, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testPlusMillis_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.plusMillis(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 20, 30, 41, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusMillis(0);
        assertSame(test, result);
    }

    public void testMinus_RP_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        
        LocalDateTime expected = new LocalDateTime(2001, 3, 26, 9, 19, 29, 39, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testMinus_RP_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        
        LocalDateTime expected = new LocalDateTime(2001, 3, 26, 9, 19, 29, 39, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minus((ReadablePeriod) null);
        assertSame(test, result);
    }

    public void testMinusYears_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusYears(1);
        LocalDateTime expected = new LocalDateTime(2001, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testMinusYears_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusYears(1);
        LocalDateTime expected = new LocalDateTime(2001, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusYears(0);
        assertSame(test, result);
    }

    public void testMinusMonths_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusMonths(1);
        LocalDateTime expected = new LocalDateTime(2002, 4, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testMinusMonths_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusMonths(1);
        LocalDateTime expected = new LocalDateTime(2002, 4, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusMonths(0);
        assertSame(test, result);
    }

    public void testMinusWeeks_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusWeeks(1);
        LocalDateTime expected = new LocalDateTime(2002, 4, 26, 10, 20, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testMinusWeeks_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusWeeks(1);
        LocalDateTime expected = new LocalDateTime(2002, 4, 26, 10, 20, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusWeeks(0);
        assertSame(test, result);
    }

    public void testMinusDays_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusDays(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 2, 10, 20, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testMinusDays_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusDays(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 2, 10, 20, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusDays(0);
        assertSame(test, result);
    }

    public void testMinusHours_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusHours(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 9, 20, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testMinusHours_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusHours(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 9, 20, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusHours(0);
        assertSame(test, result);
    }

    public void testMinusMinutes_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusMinutes(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 19, 30, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testMinusMinutes_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusMinutes(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 19, 30, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusMinutes(0);
        assertSame(test, result);
    }

    public void testMinusSeconds_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusSeconds(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 20, 29, 40, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testMinusSeconds_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusSeconds(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 20, 29, 40, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusSeconds(0);
        assertSame(test, result);
    }

    public void testMinusMillis_int_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusMillis(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 20, 30, 39, BUDDHIST_LONDON);
        assertEquals(expected, result);
    }

    public void testMinusMillis_int_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 5, 3, 10, 20, 30, 40, BUDDHIST_LONDON);
        LocalDateTime result = test.minusMillis(1);
        LocalDateTime expected = new LocalDateTime(2002, 5, 3, 10, 20, 30, 39, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusMillis(0);
        assertSame(test, result);
    }

    public void testGetters_1_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        assertEquals(1970, test.getYear());
    }

    public void testGetters_2_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testGetters_3_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testGetters_4_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(160, test.getDayOfYear());
    }

    public void testGetters_5_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getDayOfWeek());
    }

    public void testGetters_6_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(24, test.getWeekOfWeekyear());
    }

    public void testGetters_7_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1970, test.getWeekyear());
    }

    public void testGetters_8_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(70, test.getYearOfCentury());
    }

    public void testGetters_9_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(20, test.getCenturyOfEra());
    }

    public void testGetters_10_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1970, test.getYearOfEra());
    }

    public void testGetters_11_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.AD, test.getEra());
    }

    public void testGetters_12_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, test.getHourOfDay());
    }

    public void testGetters_13_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testGetters_14_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(30, test.getSecondOfMinute());
    }

    public void testGetters_15_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(40, test.getMillisOfSecond());
    }

    public void testGetters_16_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(MILLIS_OF_DAY_UTC, test.getMillisOfDay());
    }

    public void testToDateTime_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 6, 9, 6, 7, 8, 9, COPTIC_PARIS); // PARIS irrelevant
        
        DateTime test = base.toDateTime();
        check(base, 2005, 6, 9, 6, 7, 8, 9);
        DateTime expected = new DateTime(2005, 6, 9, 6, 7, 8, 9, COPTIC_LONDON);
        assertEquals(expected, test);
    }

    public void testToDateTime_Zone_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 6, 9, 6, 7, 8, 9, COPTIC_PARIS); // PARIS irrelevant
        
        DateTime test = base.toDateTime(TOKYO);
        check(base, 2005, 6, 9, 6, 7, 8, 9);
        DateTime expected = new DateTime(2005, 6, 9, 6, 7, 8, 9, COPTIC_TOKYO);
        assertEquals(expected, test);
    }

    public void testToDateTime_nullZone_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 6, 9, 6, 7, 8, 9, COPTIC_PARIS); // PARIS irrelevant
        
        DateTime test = base.toDateTime((DateTimeZone) null);
        check(base, 2005, 6, 9, 6, 7, 8, 9);
        DateTime expected = new DateTime(2005, 6, 9, 6, 7, 8, 9, COPTIC_LONDON);
        assertEquals(expected, test);
    }

    public void testToDateTime_Zone_dstOverlap_1_oe() {
        LocalDateTime base = new LocalDateTime(2014, 10, 26, 1, 30, 0, 0, ISO_LONDON);
        DateTime test = base.toDateTime(LONDON);
        DateTime expected = new DateTime(2014, 10, 26, 1, 30, ISO_LONDON).withEarlierOffsetAtOverlap();
        assertEquals(expected, test);
    }

    public void testToDateTime_Zone_dstOverlap_NewYork_1_oe() {
        LocalDateTime base = new LocalDateTime(2007, 11, 4, 1, 30, 0, 0, ISO_NEW_YORK);
        DateTime test = base.toDateTime(NEW_YORK);
        DateTime expected = new DateTime(2007, 11, 4, 1, 30, ISO_NEW_YORK).withEarlierOffsetAtOverlap();
        assertEquals(expected, test);
    }

    public void testToLocalDate_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 6, 9, 6, 7, 8, 9, COPTIC_PARIS); // PARIS irrelevant
        LocalDate expected = new LocalDate(2005, 6, 9, COPTIC_LONDON);
        assertEquals(expected,base.toLocalDate());
    }

    public void testToLocalTime_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 6, 9, 6, 7, 8, 9, COPTIC_PARIS); // PARIS irrelevant
        LocalTime expected = new LocalTime(6, 7, 8, 9, COPTIC_LONDON);
        assertEquals(expected,base.toLocalTime());
    }

    public void testToDateTime_RI_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        DateTime dt = new DateTime(2002, 1, 3, 4, 5, 6, 7, BUDDHIST_TOKYO);
        
        DateTime test = base.toDateTime(dt);
        check(base, 2005, 6, 9, 10, 20, 30, 40);
        DateTime expected = new DateTime(2005, 6, 9, 10, 20, 30, 40, BUDDHIST_TOKYO);
        assertEquals(expected, test);
    }

    public void testToDateTime_nullRI_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        
        DateTime test = base.toDateTime((ReadableInstant) null);
        check(base, 2005, 6, 9, 10, 20, 30, 40);
        DateTime expected = new DateTime(2005, 6, 9, 10, 20, 30, 40, ISO_LONDON);
        assertEquals(expected, test);
    }

    public void testToDate_summer_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 7, 9, 10, 20, 30, 40, COPTIC_PARIS);
        
        Date test = base.toDate();
        check(base, 2005, 7, 9, 10, 20, 30, 40);
        
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.clear();
        gcal.set(Calendar.YEAR, 2005);
        gcal.set(Calendar.MONTH, Calendar.JULY);
        gcal.set(Calendar.DAY_OF_MONTH, 9);
        gcal.set(Calendar.HOUR_OF_DAY, 10);
        gcal.set(Calendar.MINUTE, 20);
        gcal.set(Calendar.SECOND, 30);
        gcal.set(Calendar.MILLISECOND, 40);
        assertEquals(gcal.getTime(), test);
    }

    public void testToDate_winter_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 1, 9, 10, 20, 30, 40, COPTIC_PARIS);
        
        Date test = base.toDate();
        check(base, 2005, 1, 9, 10, 20, 30, 40);
        
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.clear();
        gcal.set(Calendar.YEAR, 2005);
        gcal.set(Calendar.MONTH, Calendar.JANUARY);
        gcal.set(Calendar.DAY_OF_MONTH, 9);
        gcal.set(Calendar.HOUR_OF_DAY, 10);
        gcal.set(Calendar.MINUTE, 20);
        gcal.set(Calendar.SECOND, 30);
        gcal.set(Calendar.MILLISECOND, 40);
        assertEquals(gcal.getTime(), test);
    }

    public void testToDate_summer_Zone_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 7, 9, 10, 20, 30, 40, COPTIC_PARIS);
        
        Date test = base.toDate(TimeZone.getDefault());
        check(base, 2005, 7, 9, 10, 20, 30, 40);
        
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.clear();
        gcal.set(Calendar.YEAR, 2005);
        gcal.set(Calendar.MONTH, Calendar.JULY);
        gcal.set(Calendar.DAY_OF_MONTH, 9);
        gcal.set(Calendar.HOUR_OF_DAY, 10);
        gcal.set(Calendar.MINUTE, 20);
        gcal.set(Calendar.SECOND, 30);
        gcal.set(Calendar.MILLISECOND, 40);
        assertEquals(gcal.getTime(), test);
    }

    public void testToDate_winter_Zone_1_oe() {
        LocalDateTime base = new LocalDateTime(2005, 1, 9, 10, 20, 30, 40, COPTIC_PARIS);
        
        Date test = base.toDate(TimeZone.getDefault());
        check(base, 2005, 1, 9, 10, 20, 30, 40);
        
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.clear();
        gcal.set(Calendar.YEAR, 2005);
        gcal.set(Calendar.MONTH, Calendar.JANUARY);
        gcal.set(Calendar.DAY_OF_MONTH, 9);
        gcal.set(Calendar.HOUR_OF_DAY, 10);
        gcal.set(Calendar.MINUTE, 20);
        gcal.set(Calendar.SECOND, 30);
        gcal.set(Calendar.MILLISECOND, 40);
        assertEquals(gcal.getTime(), test);
    }

    public void testProperty_1_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        assertEquals(test.year(), test.property(DateTimeFieldType.year()));
    }

    public void testProperty_2_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        assertEquals(test.monthOfYear(), test.property(DateTimeFieldType.monthOfYear()));
    }

    public void testProperty_3_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(test.dayOfMonth(), test.property(DateTimeFieldType.dayOfMonth()));
    }

    public void testProperty_4_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.dayOfWeek(), test.property(DateTimeFieldType.dayOfWeek()));
    }

    public void testProperty_5_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.dayOfYear(), test.property(DateTimeFieldType.dayOfYear()));
    }

    public void testProperty_6_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.weekOfWeekyear(), test.property(DateTimeFieldType.weekOfWeekyear()));
    }

    public void testProperty_7_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.weekyear(), test.property(DateTimeFieldType.weekyear()));
    }

    public void testProperty_8_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.yearOfCentury(), test.property(DateTimeFieldType.yearOfCentury()));
    }

    public void testProperty_9_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.yearOfEra(), test.property(DateTimeFieldType.yearOfEra()));
    }

    public void testProperty_10_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.centuryOfEra(), test.property(DateTimeFieldType.centuryOfEra()));
    }

    public void testProperty_11_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.era(), test.property(DateTimeFieldType.era()));
    }

    public void testProperty_12_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.hourOfDay(), test.property(DateTimeFieldType.hourOfDay()));
    }

    public void testProperty_13_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.minuteOfHour(), test.property(DateTimeFieldType.minuteOfHour()));
    }

    public void testProperty_14_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.secondOfMinute(), test.property(DateTimeFieldType.secondOfMinute()));
    }

    public void testProperty_15_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.millisOfSecond(), test.property(DateTimeFieldType.millisOfSecond()));
    }

    public void testProperty_16_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.millisOfDay(), test.property(DateTimeFieldType.millisOfDay()));
    }

    public void testProperty_18_oe() {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        try {
            test.property(null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        assertEquals(test, test.property(DateTimeFieldType.minuteOfDay()).getLocalDateTime());
    }

    public void testSerialization_1_oe() throws Exception {
        LocalDateTime test = new LocalDateTime(1972, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalDateTime result = (LocalDateTime) ois.readObject();
        ois.close();
        
        assertEquals(test, result);
    }

    public void testSerialization_2_oe() throws Exception {
        LocalDateTime test = new LocalDateTime(1972, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalDateTime result = (LocalDateTime) ois.readObject();
        ois.close();
        
        // removed other assertion
        assertTrue(Arrays.equals(test.getValues(), result.getValues()));
    }

    public void testSerialization_3_oe() throws Exception {
        LocalDateTime test = new LocalDateTime(1972, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalDateTime result = (LocalDateTime) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(test.getFields(), result.getFields()));
    }

    public void testSerialization_4_oe() throws Exception {
        LocalDateTime test = new LocalDateTime(1972, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalDateTime result = (LocalDateTime) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology(), result.getChronology());
    }

    public void testSerialization_5_oe() throws Exception {
        LocalDateTime test = new LocalDateTime(1972, 6, 9, 10, 20, 30, 40, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalDateTime result = (LocalDateTime) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(result.isSupported(DateTimeFieldType.dayOfMonth()));  // check deserialization;
    }

    public void testToString_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 6, 9, 10, 20, 30, 40);
        assertEquals("2002-06-09T10:20:30.040", test.toString());
    }

    public void testToString_String_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 6, 9, 10, 20, 30, 40);
        assertEquals("2002 10", test.toString("yyyy HH"));
    }

    public void testToString_String_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        assertEquals("2002-06-09T10:20:30.040", test.toString((String) null));
    }

    public void testToString_String_Locale_1_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        assertEquals("Tue 9/6", test.toString("EEE d/M", Locale.ENGLISH));
    }

    public void testToString_String_Locale_2_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        assertEquals("mar. 9/6", test.toString("EEE d/M", Locale.FRENCH));
    }

    public void testToString_String_Locale_3_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals("1970-06-09T10:20:30.040", test.toString(null, Locale.ENGLISH));
    }

    public void testToString_String_Locale_4_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Tue 9/6", test.toString("EEE d/M", null));
    }

    public void testToString_String_Locale_5_oe() {
        LocalDateTime test = new LocalDateTime(1970, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1970-06-09T10:20:30.040", test.toString(null, null));
    }

    public void testToString_DTFormatter_1_oe() {
        LocalDateTime test = new LocalDateTime(2002, 6, 9, 10, 20, 30, 40);
        assertEquals("2002 10", test.toString(DateTimeFormat.forPattern("yyyy HH")));
    }

    public void testToString_DTFormatter_2_oe() {
        LocalDateTime test = new LocalDateTime(2002, 6, 9, 10, 20, 30, 40);
        // removed other assertion
        assertEquals("2002-06-09T10:20:30.040", test.toString((DateTimeFormatter) null));
    }

}
