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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for LocalTime.
 *
 * @author Stephen Colebourne
 */
public class TestLocalTime_Constructors_OE25Dev extends TestCase {

    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final DateTimeZone NEW_YORK = DateTimeZone.forID("America/New_York");
    private static final ISOChronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final JulianChronology JULIAN_LONDON = JulianChronology.getInstance(LONDON);
    private static final JulianChronology JULIAN_PARIS = JulianChronology.getInstance(PARIS);
    private static final JulianChronology JULIAN_UTC = JulianChronology.getInstanceUTC();
    private static final Chronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();
    private static final int OFFSET_LONDON = LONDON.getOffset(0L) / DateTimeConstants.MILLIS_PER_HOUR;
    private static final int OFFSET_PARIS = PARIS.getOffset(0L) / DateTimeConstants.MILLIS_PER_HOUR;

    private long TEST_TIME_NOW =
            10L * DateTimeConstants.MILLIS_PER_HOUR
            + 20L * DateTimeConstants.MILLIS_PER_MINUTE
            + 30L * DateTimeConstants.MILLIS_PER_SECOND
            + 40L;

    private long TEST_TIME1 =
        1L * DateTimeConstants.MILLIS_PER_HOUR
        + 2L * DateTimeConstants.MILLIS_PER_MINUTE
        + 3L * DateTimeConstants.MILLIS_PER_SECOND
        + 4L;

    private long TEST_TIME2 =
        1L * DateTimeConstants.MILLIS_PER_DAY
        + 5L * DateTimeConstants.MILLIS_PER_HOUR
        + 6L * DateTimeConstants.MILLIS_PER_MINUTE
        + 7L * DateTimeConstants.MILLIS_PER_SECOND
        + 8L;

    private DateTimeZone zone = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestLocalTime_Constructors_OE25Dev.class);
    }

    public TestLocalTime_Constructors_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(LONDON);
        java.util.TimeZone.setDefault(LONDON.toTimeZone());
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        java.util.TimeZone.setDefault(zone.toTimeZone());
        zone = null;
    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor ()
     */
    public void testConstantMidnight() throws Throwable {
        LocalTime test = LocalTime.MIDNIGHT;
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(0,test.getHourOfDay());
        assertEquals(0,test.getMinuteOfHour());
        assertEquals(0,test.getSecondOfMinute());
        assertEquals(0,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testParse_noFormatter() throws Throwable {
        assertEquals(new LocalTime(1,20),LocalTime.parse("01:20"));
        assertEquals(new LocalTime(14,50,30,432),LocalTime.parse("14:50:30.432"));
    }

    public void testParse_formatter() throws Throwable {
        DateTimeFormatter f = DateTimeFormat.forPattern("HH mm").withChronology(ISOChronology.getInstance(PARIS));
        assertEquals(new LocalTime(13,30),LocalTime.parse("13 30",f));
    }

    //-----------------------------------------------------------------------
    public void testFactory_FromCalendarFields_Calendar() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1970, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        LocalTime expected = new LocalTime(4, 5, 6, 7);
        assertEquals(expected,LocalTime.fromCalendarFields(cal));
        try {
            LocalTime.fromCalendarFields((Calendar) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testFactory_FromDateFields_after1970() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1970, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        LocalTime expected = new LocalTime(4, 5, 6, 7);
        assertEquals(expected,LocalTime.fromDateFields(cal.getTime()));
    }

    public void testFactory_FromDateFields_before1970() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1969, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        LocalTime expected = new LocalTime(4, 5, 6, 7);
        assertEquals(expected,LocalTime.fromDateFields(cal.getTime()));
    }

    public void testFactory_FromDateFields_null() throws Exception {
        try {
            LocalTime.fromDateFields((Date) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testFactoryMillisOfDay_long() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testFactoryMillisOfDay_long_Chronology() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, JULIAN_LONDON);
        assertEquals(JULIAN_UTC,test.getChronology());
        assertEquals(1,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testFactoryMillisOfDay_long_nullChronology() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testConstructor() throws Throwable {
        LocalTime test = new LocalTime();
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
        assertEquals(test,LocalTime.now());
    }

    //-----------------------------------------------------------------------
    public void testConstructor_DateTimeZone() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalTime test = new LocalTime(LONDON);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(23,test.getHourOfDay());
        assertEquals(59,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
        assertEquals(test,LocalTime.now(LONDON));
        
        test = new LocalTime(PARIS);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(0,test.getHourOfDay());
        assertEquals(59,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
        assertEquals(test,LocalTime.now(PARIS));
    }

    public void testConstructor_nullDateTimeZone() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalTime test = new LocalTime((DateTimeZone) null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(23,test.getHourOfDay());
        assertEquals(59,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testConstructor_Chronology() throws Throwable {
        LocalTime test = new LocalTime(JULIAN_LONDON);
        assertEquals(JULIAN_UTC,test.getChronology());
        assertEquals(10 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
        assertEquals(test,LocalTime.now(JULIAN_LONDON));
    }

    public void testConstructor_nullChronology() throws Throwable {
        LocalTime test = new LocalTime((Chronology) null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testConstructor_long1() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_long2() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(5 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(6,test.getMinuteOfHour());
        assertEquals(7,test.getSecondOfMinute());
        assertEquals(8,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testConstructor_long_DateTimeZone() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, PARIS);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1 + OFFSET_PARIS,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_long_DateTimeZone_2() throws Throwable {
        DateTime dt = new DateTime(2007, 6, 9, 1, 2, 3, 4, PARIS);
        DateTime dtUTC = new DateTime(1970, 1, 1, 1, 2, 3, 4, DateTimeZone.UTC);
        
        LocalTime test = new LocalTime(dt.getMillis(), PARIS);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
        assertEquals(dtUTC.getMillis(),test.getLocalMillis());
    }

    public void testConstructor_long_nullDateTimeZone() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testConstructor_long1_Chronology() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, JULIAN_PARIS);
        assertEquals(JULIAN_UTC,test.getChronology());
        assertEquals(1 + OFFSET_PARIS,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_long2_Chronology() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2, JULIAN_LONDON);
        assertEquals(JULIAN_UTC,test.getChronology());
        assertEquals(5 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(6,test.getMinuteOfHour());
        assertEquals(7,test.getSecondOfMinute());
        assertEquals(8,test.getMillisOfSecond());
    }

    public void testConstructor_long_nullChronology() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (Chronology) null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testConstructor_Object1() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_Object2() throws Throwable {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(TEST_TIME1));
        LocalTime test = new LocalTime(cal);
        assertEquals(GJChronology.getInstanceUTC(),test.getChronology());
        assertEquals(1 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject() throws Throwable {
        LocalTime test = new LocalTime((Object) null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString1() throws Throwable {
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString1Tokyo() throws Throwable {
        DateTimeZone.setDefault(TOKYO);
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString1NewYork() throws Throwable {
        DateTimeZone.setDefault(NEW_YORK);
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString2() throws Throwable {
        LocalTime test = new LocalTime("T10:20:30.040");
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString3() throws Throwable {
        LocalTime test = new LocalTime("10:20");
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(0,test.getSecondOfMinute());
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString4() throws Throwable {
        LocalTime test = new LocalTime("10");
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(0,test.getMinuteOfHour());
        assertEquals(0,test.getSecondOfMinute());
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectStringEx1() throws Throwable {
        try {
            new LocalTime("1970-04-06");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx2() throws Throwable {
        try {
            new LocalTime("1970-04-06T+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx3() throws Throwable {
        try {
            new LocalTime("1970-04-06T10:20:30.040");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx4() throws Throwable {
        try {
            new LocalTime("1970-04-06T10:20:30.040+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx5() throws Throwable {
        try {
            new LocalTime("T10:20:30.040+04:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx6() throws Throwable {
        try {
            new LocalTime("10:20:30.040+04:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectLocalTime() throws Throwable {
        LocalTime time = new LocalTime(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals(BUDDHIST_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectLocalDate() throws Throwable {
        LocalDate date = new LocalDate(1970, 4, 6, BUDDHIST_UTC);
        try {
            new LocalTime(date);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectLocalDateTime() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(dt);
        assertEquals(BUDDHIST_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    @SuppressWarnings("deprecation")
    public void testConstructor_ObjectTimeOfDay() throws Throwable {
        TimeOfDay time = new TimeOfDay(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals(BUDDHIST_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testConstructor_Object1_DateTimeZone() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, PARIS);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1 + OFFSET_PARIS,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString_DateTimeZoneLondon() throws Throwable {
        LocalTime test = new LocalTime("04:20", LONDON);
        assertEquals(4,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString_DateTimeZoneTokyo() throws Throwable {
        LocalTime test = new LocalTime("04:20", TOKYO);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(4,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString_DateTimeZoneNewYork() throws Throwable {
        LocalTime test = new LocalTime("04:20", NEW_YORK);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(4,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_DateTimeZone() throws Throwable {
        LocalTime test = new LocalTime((Object) null, PARIS);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10 + OFFSET_PARIS,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_Object_nullDateTimeZone() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (DateTimeZone) null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_nullDateTimeZone() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (DateTimeZone) null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testConstructor_Object1_Chronology() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, JULIAN_LONDON);
        assertEquals(JULIAN_UTC,test.getChronology());
        assertEquals(1 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_Object2_Chronology() throws Throwable {
        LocalTime test = new LocalTime("T10:20");
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(0,test.getSecondOfMinute());
        assertEquals(0,test.getMillisOfSecond());
        
        try {
            new LocalTime("T1020");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_nullObject_Chronology() throws Throwable {
        LocalTime test = new LocalTime((Object) null, JULIAN_LONDON);
        assertEquals(JULIAN_UTC,test.getChronology());
        assertEquals(10 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_Object_nullChronology() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (Chronology) null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(1 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(2,test.getMinuteOfHour());
        assertEquals(3,test.getSecondOfMinute());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_nullChronology() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (Chronology) null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10 + OFFSET_LONDON,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    //-----------------------------------------------------------------------
    public void testConstructor_int_int() throws Throwable {
        LocalTime test = new LocalTime(10, 20);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(0,test.getSecondOfMinute());
        assertEquals(0,test.getMillisOfSecond());
        try {
            new LocalTime(-1, 20);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(24, 20);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, -1);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 60);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_int_int_int() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(0,test.getMillisOfSecond());
        try {
            new LocalTime(-1, 20, 30);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(24, 20, 30);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, -1, 30);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 60, 30);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 20, -1);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 20, 60);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_int_int_int_int() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
        try {
            new LocalTime(-1, 20, 30, 40);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(24, 20, 30, 40);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, -1, 30, 40);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 60, 30, 40);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 20, -1, 40);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 20, 60, 40);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 20, 30, -1);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 20, 30, 1000);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_int_int_int_int_Chronology() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, JULIAN_LONDON);
        assertEquals(JULIAN_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
        try {
            new LocalTime(-1, 20, 30, 40, JULIAN_LONDON);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(24, 20, 30, 40, JULIAN_LONDON);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, -1, 30, 40, JULIAN_LONDON);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 60, 30, 40, JULIAN_LONDON);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 20, -1, 40, JULIAN_LONDON);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 20, 60, 40, JULIAN_LONDON);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 20, 30, -1, JULIAN_LONDON);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new LocalTime(10, 20, 30, 1000, JULIAN_LONDON);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_int_int_int_int_nullChronology() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, null);
        assertEquals(ISO_UTC,test.getChronology());
        assertEquals(10,test.getHourOfDay());
        assertEquals(20,test.getMinuteOfHour());
        assertEquals(30,test.getSecondOfMinute());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstantMidnight_2_oe() throws Throwable {
        LocalTime test = LocalTime.MIDNIGHT;
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstantMidnight_3_oe() throws Throwable {
        LocalTime test = LocalTime.MIDNIGHT;
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstantMidnight_4_oe() throws Throwable {
        LocalTime test = LocalTime.MIDNIGHT;
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstantMidnight_5_oe() throws Throwable {
        LocalTime test = LocalTime.MIDNIGHT;
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testParse_noFormatter_1_oe() throws Throwable {
        Object a = new LocalTime(1,20);
        assertNotNull(LocalTime.parse("23:59:59.999999999"));
    }

    public void testParse_noFormatter_2_oe() throws Throwable {
        Object a = new LocalTime(14,50,30,432);
        assertNotNull(a);
    }

    public void testParse_formatter_1_oe() throws Throwable {
        DateTimeFormatter f = DateTimeFormat.forPattern("HH mm").withChronology(ISOChronology.getInstance(PARIS));
        assertNotNull(f);
    }

    public void testFactoryMillisOfDay_long_1_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getDisplayName());
    }

    public void testFactoryMillisOfDay_long_2_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1);
        assertEquals(1, test.getHourOfDay());
    }

    public void testFactoryMillisOfDay_long_3_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1);
        assertEquals(30, test.getMinuteOfHour());
    }

    public void testFactoryMillisOfDay_long_4_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1);
        assertEquals(2, test.getSecondOfMinute());
    }

    public void testFactoryMillisOfDay_long_5_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testFactoryMillisOfDay_long_Chronology_1_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, JULIAN_LONDON);
        assertEquals(JULIAN_LONDON, test.getChronology());
    }

    public void testFactoryMillisOfDay_long_Chronology_2_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, JULIAN_LONDON);
        assertEquals(0, test.getHourOfDay());
    }

    public void testFactoryMillisOfDay_long_Chronology_3_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, JULIAN_LONDON);
        assertEquals(30, test.getMinuteOfHour());
    }

    public void testFactoryMillisOfDay_long_Chronology_4_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, JULIAN_LONDON);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testFactoryMillisOfDay_long_Chronology_5_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, JULIAN_LONDON);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testFactoryMillisOfDay_long_nullChronology_1_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, null);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getDisplayName());
    }

    public void testFactoryMillisOfDay_long_nullChronology_2_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, null);
        assertEquals(1, test.getHourOfDay());
    }

    public void testFactoryMillisOfDay_long_nullChronology_3_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, null);
        assertEquals(30, test.getMinuteOfHour());
    }

    public void testFactoryMillisOfDay_long_nullChronology_4_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, null);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testFactoryMillisOfDay_long_nullChronology_5_oe() throws Throwable {
        LocalTime test = LocalTime.fromMillisOfDay(TEST_TIME1, null);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_2_oe() throws Throwable {
        LocalTime test = new LocalTime();
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_3_oe() throws Throwable {
        LocalTime test = new LocalTime();
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_4_oe() throws Throwable {
        LocalTime test = new LocalTime();
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_5_oe() throws Throwable {
        LocalTime test = new LocalTime();
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_6_oe() throws Throwable {
        LocalTime test = new LocalTime();
        assertNotNull(test);
    }

    public void testConstructor_DateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getType());
    }

    public void testConstructor_DateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_DateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        assertEquals(59, test.getMinuteOfHour());
    }

    public void testConstructor_DateTimeZone_4_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_DateTimeZone_5_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_DateTimeZone_6_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        assertNotNull(LocalTime.now());
    }

    public void testConstructor_DateTimeZone_7_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        
        test = new LocalTime(PARIS);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getType());
    }

    public void testConstructor_DateTimeZone_8_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        
        test = new LocalTime(PARIS);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_DateTimeZone_9_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        
        test = new LocalTime(PARIS);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_DateTimeZone_10_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        
        test = new LocalTime(PARIS);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_DateTimeZone_11_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        
        test = new LocalTime(PARIS);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_DateTimeZone_12_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime(LONDON);
        
        test = new LocalTime(PARIS);
        assertNotNull(test);
    }

    public void testConstructor_nullDateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime((DateTimeZone) null);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getType());
    }

    public void testConstructor_nullDateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime((DateTimeZone) null);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_nullDateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime((DateTimeZone) null);
        assertEquals(59, test.getMinuteOfHour());
    }

    public void testConstructor_nullDateTimeZone_4_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime((DateTimeZone) null);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_nullDateTimeZone_5_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalTime test = new LocalTime((DateTimeZone) null);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_Chronology_1_oe() throws Throwable {
        LocalTime test = new LocalTime(JULIAN_LONDON);
        assertEquals("java.time.chrono.JapaneseChronology", test.getChronology().toString());
    }

    public void testConstructor_Chronology_2_oe() throws Throwable {
        LocalTime test = new LocalTime(JULIAN_LONDON);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_Chronology_3_oe() throws Throwable {
        LocalTime test = new LocalTime(JULIAN_LONDON);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_Chronology_4_oe() throws Throwable {
        LocalTime test = new LocalTime(JULIAN_LONDON);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_Chronology_5_oe() throws Throwable {
        LocalTime test = new LocalTime(JULIAN_LONDON);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_Chronology_6_oe() throws Throwable {
        LocalTime test = new LocalTime(JULIAN_LONDON);
// incorrect assertion         assertNotNull(now());
    }

    public void testConstructor_nullChronology_1_oe() throws Throwable {
        LocalTime test = new LocalTime((Chronology) null);
        assertNull(test.getChronology());
    }

    public void testConstructor_nullChronology_2_oe() throws Throwable {
        LocalTime test = new LocalTime((Chronology) null);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_nullChronology_3_oe() throws Throwable {
        LocalTime test = new LocalTime((Chronology) null);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_nullChronology_4_oe() throws Throwable {
        LocalTime test = new LocalTime((Chronology) null);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_nullChronology_5_oe() throws Throwable {
        LocalTime test = new LocalTime((Chronology) null);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_long1_2_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1);
        assertEquals(1, test.getHourOfDay());
    }

    public void testConstructor_long1_3_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1);
        assertEquals(30, test.getMinuteOfHour());
    }

    public void testConstructor_long1_4_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_long1_5_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_long2_1_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2);
        assertEquals("java.time.chrono.JapaneseChronology", test.getChronology().toString());
    }

    public void testConstructor_long2_2_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_long2_3_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2);
        assertEquals(30, test.getMinuteOfHour());
    }

    public void testConstructor_long2_4_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_long2_5_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_long_DateTimeZone_1_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, PARIS);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getDisplayName());
    }

    public void testConstructor_long_DateTimeZone_2_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, PARIS);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_long_DateTimeZone_3_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, PARIS);
        assertEquals(30, test.getMinuteOfHour());
    }

    public void testConstructor_long_DateTimeZone_4_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, PARIS);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_long_DateTimeZone_5_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, PARIS);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_long_DateTimeZone_2_1_oe() throws Throwable {
        DateTime dt = new DateTime(2007, 6, 9, 1, 2, 3, 4, PARIS);
        DateTime dtUTC = new DateTime(1970, 1, 1, 1, 2, 3, 4, DateTimeZone.UTC);
        
        LocalTime test = new LocalTime(dt.getMillis(), PARIS);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getType());
    }

    public void testConstructor_long_DateTimeZone_2_2_oe() throws Throwable {
        DateTime dt = new DateTime(2007, 6, 9, 1, 2, 3, 4, PARIS);
        DateTime dtUTC = new DateTime(1970, 1, 1, 1, 2, 3, 4, DateTimeZone.UTC);
        
        LocalTime test = new LocalTime(dt.getMillis(), PARIS);
        assertEquals(1, test.getHourOfDay());
    }

    public void testConstructor_long_DateTimeZone_2_3_oe() throws Throwable {
        DateTime dt = new DateTime(2007, 6, 9, 1, 2, 3, 4, PARIS);
        DateTime dtUTC = new DateTime(1970, 1, 1, 1, 2, 3, 4, DateTimeZone.UTC);
        
        LocalTime test = new LocalTime(dt.getMillis(), PARIS);
        assertEquals(1, test.getMinuteOfHour());
    }

    public void testConstructor_long_DateTimeZone_2_4_oe() throws Throwable {
        DateTime dt = new DateTime(2007, 6, 9, 1, 2, 3, 4, PARIS);
        DateTime dtUTC = new DateTime(1970, 1, 1, 1, 2, 3, 4, DateTimeZone.UTC);
        
        LocalTime test = new LocalTime(dt.getMillis(), PARIS);
        assertEquals(2, test.getSecondOfMinute());
    }

    public void testConstructor_long_DateTimeZone_2_5_oe() throws Throwable {
        DateTime dt = new DateTime(2007, 6, 9, 1, 2, 3, 4, PARIS);
        DateTime dtUTC = new DateTime(1970, 1, 1, 1, 2, 3, 4, DateTimeZone.UTC);
        
        LocalTime test = new LocalTime(dt.getMillis(), PARIS);
        assertEquals(3, test.getMillisOfSecond());
    }

    public void testConstructor_long_DateTimeZone_2_6_oe() throws Throwable {
        DateTime dt = new DateTime(2007, 6, 9, 1, 2, 3, 4, PARIS);
        DateTime dtUTC = new DateTime(1970, 1, 1, 1, 2, 3, 4, DateTimeZone.UTC);
        
        LocalTime test = new LocalTime(dt.getMillis(), PARIS);
        assertEquals(1279654400000L, dtUTC.getMillis());
    }

    public void testConstructor_long_nullDateTimeZone_1_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (DateTimeZone) null);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getDisplayName());
    }

    public void testConstructor_long_nullDateTimeZone_2_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(1, test.getHourOfDay());
    }

    public void testConstructor_long_nullDateTimeZone_3_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(30, test.getMinuteOfHour());
    }

    public void testConstructor_long_nullDateTimeZone_4_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_long_nullDateTimeZone_5_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_long1_Chronology_1_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, JULIAN_PARIS);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getDisplayName());
    }

    public void testConstructor_long1_Chronology_2_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, JULIAN_PARIS);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_long1_Chronology_3_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, JULIAN_PARIS);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_long1_Chronology_4_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, JULIAN_PARIS);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_long1_Chronology_5_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, JULIAN_PARIS);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_long2_Chronology_1_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2, JULIAN_LONDON);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getDisplayName());
    }

    public void testConstructor_long2_Chronology_2_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2, JULIAN_LONDON);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_long2_Chronology_3_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2, JULIAN_LONDON);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_long2_Chronology_4_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2, JULIAN_LONDON);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_long2_Chronology_5_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME2, JULIAN_LONDON);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_long_nullChronology_1_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (Chronology) null);
        assertNull(test.getChronology());
    }

    public void testConstructor_long_nullChronology_2_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (Chronology) null);
        assertEquals(1, test.getHourOfDay());
    }

    public void testConstructor_long_nullChronology_3_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (Chronology) null);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_long_nullChronology_4_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (Chronology) null);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_long_nullChronology_5_oe() throws Throwable {
        LocalTime test = new LocalTime(TEST_TIME1, (Chronology) null);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_Object1_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_Object1_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_Object1_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_Object1_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_Object2_2_oe() throws Throwable {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(TEST_TIME1));
        LocalTime test = new LocalTime(cal);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_Object2_3_oe() throws Throwable {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(TEST_TIME1));
        LocalTime test = new LocalTime(cal);
        assertEquals(32, test.getMinuteOfHour());
    }

    public void testConstructor_Object2_4_oe() throws Throwable {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(TEST_TIME1));
        LocalTime test = new LocalTime(cal);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_Object2_5_oe() throws Throwable {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(TEST_TIME1));
        LocalTime test = new LocalTime(cal);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_2_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_nullObject_3_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_4_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_5_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString1_2_oe() throws Throwable {
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_ObjectString1_3_oe() throws Throwable {
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString1_4_oe() throws Throwable {
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(20, test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString1_5_oe() throws Throwable {
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(30, test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString1Tokyo_2_oe() throws Throwable {
        DateTimeZone.setDefault(TOKYO);
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_ObjectString1Tokyo_3_oe() throws Throwable {
        DateTimeZone.setDefault(TOKYO);
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString1Tokyo_4_oe() throws Throwable {
        DateTimeZone.setDefault(TOKYO);
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(30, test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString1Tokyo_5_oe() throws Throwable {
        DateTimeZone.setDefault(TOKYO);
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(30, test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString1NewYork_2_oe() throws Throwable {
        DateTimeZone.setDefault(NEW_YORK);
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_ObjectString1NewYork_3_oe() throws Throwable {
        DateTimeZone.setDefault(NEW_YORK);
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString1NewYork_4_oe() throws Throwable {
        DateTimeZone.setDefault(NEW_YORK);
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(30, test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString1NewYork_5_oe() throws Throwable {
        DateTimeZone.setDefault(NEW_YORK);
        LocalTime test = new LocalTime("10:20:30.040");
        assertEquals(30, test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString2_1_oe() throws Throwable {
        LocalTime test = new LocalTime("T10:20:30.040");
        assertEquals("java.util.GregorianChronology", test.getChronology().toString());
    }

    public void testConstructor_ObjectString2_2_oe() throws Throwable {
        LocalTime test = new LocalTime("T10:20:30.040");
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_ObjectString2_3_oe() throws Throwable {
        LocalTime test = new LocalTime("T10:20:30.040");
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString2_4_oe() throws Throwable {
        LocalTime test = new LocalTime("T10:20:30.040");
        assertEquals(30, test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString2_5_oe() throws Throwable {
        LocalTime test = new LocalTime("T10:20:30.040");
        assertEquals(30, test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString3_1_oe() throws Throwable {
        LocalTime test = new LocalTime("10:20");
        assertEquals("java.util.GregorianChronology", test.getChronology().toString());
    }

    public void testConstructor_ObjectString3_2_oe() throws Throwable {
        LocalTime test = new LocalTime("10:20");
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_ObjectString3_3_oe() throws Throwable {
        LocalTime test = new LocalTime("10:20");
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString3_4_oe() throws Throwable {
        LocalTime test = new LocalTime("10:20");
        assertEquals(20, test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString3_5_oe() throws Throwable {
        LocalTime test = new LocalTime("10:20");
        assertEquals(20, test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString4_1_oe() throws Throwable {
        LocalTime test = new LocalTime("10");
        assertEquals("java.util.GregorianChronology", test.getChronology().toString());
    }

    public void testConstructor_ObjectString4_2_oe() throws Throwable {
        LocalTime test = new LocalTime("10");
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_ObjectString4_3_oe() throws Throwable {
        LocalTime test = new LocalTime("10");
        assertEquals(10, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString4_4_oe() throws Throwable {
        LocalTime test = new LocalTime("10");
        assertEquals(10, test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString4_5_oe() throws Throwable {
        LocalTime test = new LocalTime("10");
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_ObjectLocalTime_1_oe() throws Throwable {
        LocalTime time = new LocalTime(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals("java.time.chrono.BuddhistChronology", test.getChronology().toString());
    }

    public void testConstructor_ObjectLocalTime_2_oe() throws Throwable {
        LocalTime time = new LocalTime(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals(10, time.getHourOfDay());
    }

    public void testConstructor_ObjectLocalTime_3_oe() throws Throwable {
        LocalTime time = new LocalTime(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectLocalTime_4_oe() throws Throwable {
        LocalTime time = new LocalTime(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals(30, time.getSecondOfMinute());
    }

    public void testConstructor_ObjectLocalTime_5_oe() throws Throwable {
        LocalTime time = new LocalTime(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals(40, time.getMillisOfSecond());
    }

    public void testConstructor_ObjectLocalDateTime_1_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(dt);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_ObjectLocalDateTime_2_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(dt);
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_ObjectLocalDateTime_3_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(dt);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectLocalDateTime_4_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(dt);
        assertEquals(30, test.getSecondOfMinute());
    }

    public void testConstructor_ObjectLocalDateTime_5_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(dt);
        assertEquals(30, test.getMillisOfSecond());
    }

    public void testConstructor_ObjectTimeOfDay_1_oe() throws Throwable {
        TimeOfDay time = new TimeOfDay(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals("java.time.chrono.BuddhistChronology", test.getChronology().toString());
    }

    public void testConstructor_ObjectTimeOfDay_2_oe() throws Throwable {
        TimeOfDay time = new TimeOfDay(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_ObjectTimeOfDay_3_oe() throws Throwable {
        TimeOfDay time = new TimeOfDay(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectTimeOfDay_4_oe() throws Throwable {
        TimeOfDay time = new TimeOfDay(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals(30, test.getSecondOfMinute());
    }

    public void testConstructor_ObjectTimeOfDay_5_oe() throws Throwable {
        TimeOfDay time = new TimeOfDay(10, 20, 30, 40, BUDDHIST_UTC);
        LocalTime test = new LocalTime(time);
        assertEquals(40, test.getMillisOfSecond());
    }

    public void testConstructor_Object1_DateTimeZone_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, PARIS);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getType());
    }

    public void testConstructor_Object1_DateTimeZone_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, PARIS);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_Object1_DateTimeZone_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, PARIS);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_Object1_DateTimeZone_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, PARIS);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_Object1_DateTimeZone_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, PARIS);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString_DateTimeZoneLondon_1_oe() throws Throwable {
        LocalTime test = new LocalTime("04:20", LONDON);
        assertEquals(4, test.getHourOfDay());
    }

    public void testConstructor_ObjectString_DateTimeZoneLondon_2_oe() throws Throwable {
        LocalTime test = new LocalTime("04:20", LONDON);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString_DateTimeZoneTokyo_1_oe() throws Throwable {
        LocalTime test = new LocalTime("04:20", TOKYO);
        assertEquals("java.time.chrono.JapaneseChronology", test.getChronology().toString());
    }

    public void testConstructor_ObjectString_DateTimeZoneTokyo_2_oe() throws Throwable {
        LocalTime test = new LocalTime("04:20", TOKYO);
        assertEquals(4, test.getHourOfDay());
    }

    public void testConstructor_ObjectString_DateTimeZoneTokyo_3_oe() throws Throwable {
        LocalTime test = new LocalTime("04:20", TOKYO);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString_DateTimeZoneNewYork_1_oe() throws Throwable {
        LocalTime test = new LocalTime("04:20", NEW_YORK);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getDisplayName());
    }

    public void testConstructor_ObjectString_DateTimeZoneNewYork_2_oe() throws Throwable {
        LocalTime test = new LocalTime("04:20", NEW_YORK);
        assertEquals(4, test.getHourOfDay());
    }

    public void testConstructor_ObjectString_DateTimeZoneNewYork_3_oe() throws Throwable {
        LocalTime test = new LocalTime("04:20", NEW_YORK);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_DateTimeZone_2_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, PARIS);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_nullObject_DateTimeZone_3_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, PARIS);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_DateTimeZone_4_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, PARIS);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_DateTimeZone_5_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, PARIS);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_Object_nullDateTimeZone_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (DateTimeZone) null);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getType());
    }

    public void testConstructor_Object_nullDateTimeZone_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (DateTimeZone) null);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_Object_nullDateTimeZone_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (DateTimeZone) null);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_Object_nullDateTimeZone_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (DateTimeZone) null);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_Object_nullDateTimeZone_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (DateTimeZone) null);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_nullDateTimeZone_2_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (DateTimeZone) null);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_nullObject_nullDateTimeZone_3_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (DateTimeZone) null);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_nullDateTimeZone_4_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (DateTimeZone) null);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_nullDateTimeZone_5_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (DateTimeZone) null);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_Object1_Chronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, JULIAN_LONDON);
        assertEquals(JULIAN_LONDON, test.getChronology());
    }

    public void testConstructor_Object1_Chronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, JULIAN_LONDON);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_Object1_Chronology_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, JULIAN_LONDON);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_Object1_Chronology_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, JULIAN_LONDON);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_Object1_Chronology_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, JULIAN_LONDON);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_Object2_Chronology_1_oe() throws Throwable {
        LocalTime test = new LocalTime("T10:20");
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_Object2_Chronology_2_oe() throws Throwable {
        LocalTime test = new LocalTime("T10:20");
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_Object2_Chronology_3_oe() throws Throwable {
        LocalTime test = new LocalTime("T10:20");
        assertEquals(20, test.getSecondOfMinute());
    }

    public void testConstructor_Object2_Chronology_4_oe() throws Throwable {
        LocalTime test = new LocalTime("T10:20");
        assertEquals(20, test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_Chronology_1_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, JULIAN_LONDON);
        assertEquals(JULIAN_LONDON, test.getChronology());
    }

    public void testConstructor_nullObject_Chronology_2_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, JULIAN_LONDON);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_nullObject_Chronology_3_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, JULIAN_LONDON);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_Chronology_4_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, JULIAN_LONDON);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_Chronology_5_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, JULIAN_LONDON);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_Object_nullChronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (Chronology) null);
        assertNull(test.getChronology());
    }

    public void testConstructor_Object_nullChronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (Chronology) null);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_Object_nullChronology_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (Chronology) null);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_Object_nullChronology_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (Chronology) null);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_Object_nullChronology_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalTime test = new LocalTime(date, (Chronology) null);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_nullChronology_1_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (Chronology) null);
        assertNull(test.getChronology());
    }

    public void testConstructor_nullObject_nullChronology_2_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (Chronology) null);
        assertEquals(0, test.getHourOfDay());
    }

    public void testConstructor_nullObject_nullChronology_3_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (Chronology) null);
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_nullChronology_4_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (Chronology) null);
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_nullChronology_5_oe() throws Throwable {
        LocalTime test = new LocalTime((Object) null, (Chronology) null);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testConstructor_int_int_1_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getDisplayName());
    }

    public void testConstructor_int_int_2_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20);
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_int_int_3_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_int_int_4_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20);
        assertEquals(20, test.getSecondOfMinute());
    }

    public void testConstructor_int_int_5_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20);
        assertEquals(20, test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_1_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getDisplayName());
    }

    public void testConstructor_int_int_int_2_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30);
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_int_int_int_3_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_4_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30);
        assertEquals(30, test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_5_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30);
        assertEquals(30, test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_int_1_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40);
// incorrect assertion         assertEquals("LocalTime", test.getChronology().getType());
    }

    public void testConstructor_int_int_int_int_2_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_int_int_int_int_3_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_int_4_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(20, test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_int_5_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(40, test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_int_Chronology_1_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, JULIAN_LONDON);
        assertEquals(JULIAN_LONDON, test.getChronology());
    }

    public void testConstructor_int_int_int_int_Chronology_2_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, JULIAN_LONDON);
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_int_int_int_int_Chronology_3_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, JULIAN_LONDON);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_int_Chronology_4_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, JULIAN_LONDON);
        assertEquals(30, test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_int_Chronology_5_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, JULIAN_LONDON);
        assertEquals(40, test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_int_nullChronology_1_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, null);
        assertEquals("LocalTime", test.getChronology().toString());
    }

    public void testConstructor_int_int_int_int_nullChronology_2_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, null);
        assertEquals(10, test.getHourOfDay());
    }

    public void testConstructor_int_int_int_int_nullChronology_3_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, null);
        assertEquals(20, test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_int_nullChronology_4_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, null);
        assertEquals(30, test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_int_nullChronology_5_oe() throws Throwable {
        LocalTime test = new LocalTime(10, 20, 30, 40, null);
        assertEquals(30, test.getMillisOfSecond());
    }

}
