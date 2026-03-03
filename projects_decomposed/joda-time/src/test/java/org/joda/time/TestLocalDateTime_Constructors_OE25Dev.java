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
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for LocalDateTime.
 *
 * @author Stephen Colebourne
 */
public class TestLocalDateTime_Constructors_OE25Dev extends TestCase {

    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone MOSCOW = DateTimeZone.forID("Europe/Moscow");
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final Chronology GREGORIAN_UTC = GregorianChronology.getInstanceUTC();
    private static final Chronology GREGORIAN_PARIS = GregorianChronology.getInstance(PARIS);
    private static final Chronology GREGORIAN_MOSCOW = GregorianChronology.getInstance(MOSCOW);
    private static final Chronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();
    private static final int OFFSET_PARIS = PARIS.getOffset(0L) / DateTimeConstants.MILLIS_PER_HOUR;
    private static final int OFFSET_MOSCOW = MOSCOW.getOffset(0L) / DateTimeConstants.MILLIS_PER_HOUR;
    
    private long MILLIS_OF_DAY =
        10L * DateTimeConstants.MILLIS_PER_HOUR
        + 20L * DateTimeConstants.MILLIS_PER_MINUTE
        + 30L * DateTimeConstants.MILLIS_PER_SECOND
        + 40L;
    private long TEST_TIME_NOW =
        (31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY
        + MILLIS_OF_DAY;

    private long TEST_TIME1 =
        (31L + 28L + 31L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
        + 12L * DateTimeConstants.MILLIS_PER_HOUR
        + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
    private long TEST_TIME2 =
        (365L + 31L + 28L + 31L + 30L + 7L -1L) * DateTimeConstants.MILLIS_PER_DAY
        + 14L * DateTimeConstants.MILLIS_PER_HOUR
        + 28L * DateTimeConstants.MILLIS_PER_MINUTE;

    private DateTimeZone zone = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestLocalDateTime_Constructors_OE25Dev.class);
    }

    public TestLocalDateTime_Constructors_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(MOSCOW);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        zone = null;
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testFactory_fromCalendarFields_null() throws Exception {
        try {
            LocalDateTime.fromCalendarFields((Calendar) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testFactory_fromDateFields_null() throws Exception {
        try {
            LocalDateTime.fromDateFields((Date) null);
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

    public void testConstructor_ObjectStringEx1() throws Throwable {
        try {
            new LocalDateTime("1970-04-06T+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx2() throws Throwable {
        try {
            new LocalDateTime("1970-04-06T10:20:30.040+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx3() throws Throwable {
        try {
            new LocalDateTime("T10:20:30.040");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx4() throws Throwable {
        try {
            new LocalDateTime("T10:20:30.040+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx5() throws Throwable {
        try {
            new LocalDateTime("10:20:30.040");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx6() throws Throwable {
        try {
            new LocalDateTime("10:20:30.040+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectLocalDate() throws Throwable {
        LocalDate date = new LocalDate(1970, 5, 6);
        try {
            new LocalDateTime(date);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectLocalTime() throws Throwable {
        LocalTime time = new LocalTime(10, 20, 30, 40);
        try {
            new LocalDateTime(time);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testParse_noFormatter_1_oe() throws Throwable {
        assertEquals(new LocalDateTime(2010,6,30,1,20),LocalDateTime.parse("2010-06-30T01:20"));
    }

    public void testParse_noFormatter_2_oe() throws Throwable {
        assertEquals(new LocalDateTime(2010,1,2,14,50,30,432),LocalDateTime.parse("2010-002T14:50:30.432"));
    }

    public void testParse_formatter_1_oe() throws Throwable {
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy--dd MM HH").withChronology(ISOChronology.getInstance(PARIS));
        assertEquals(new LocalDateTime(2010,6,30,13,0),LocalDateTime.parse("2010--30 06 13",f));
    }

    public void testFactory_fromCalendarFields_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1970, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDateTime expected = new LocalDateTime(1970, 2, 3, 4, 5, 6, 7);
        assertEquals(expected,LocalDateTime.fromCalendarFields(cal));
    }

    public void testFactory_fromCalendarFields_beforeYearZero1_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1, 1, 3, 4, 5, 6);
        cal.set(Calendar.ERA, GregorianCalendar.BC);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDateTime expected = new LocalDateTime(0, 2, 3, 4, 5, 6, 7);
        assertEquals(expected,LocalDateTime.fromCalendarFields(cal));
    }

    public void testFactory_fromCalendarFields_beforeYearZero3_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(3, 1, 3, 4, 5, 6);
        cal.set(Calendar.ERA, GregorianCalendar.BC);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDateTime expected = new LocalDateTime(-2, 2, 3, 4, 5, 6, 7);
        assertEquals(expected,LocalDateTime.fromCalendarFields(cal));
    }

    public void testFactory_fromDateFields_after1970_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1970, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDateTime expected = new LocalDateTime(1970, 2, 3, 4, 5 ,6, 7);
        assertEquals(expected,LocalDateTime.fromDateFields(cal.getTime()));
    }

    public void testFactory_fromDateFields_before1970_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1969, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDateTime expected = new LocalDateTime(1969, 2, 3, 4, 5 ,6, 7);
        assertEquals(expected,LocalDateTime.fromDateFields(cal.getTime()));
    }

    public void testFactory_fromDateFields_beforeYearZero1_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1, 1, 3, 4, 5, 6);
        cal.set(Calendar.ERA, GregorianCalendar.BC);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDateTime expected = new LocalDateTime(0, 2, 3, 4, 5, 6, 7);
        assertEquals(expected,LocalDateTime.fromDateFields(cal.getTime()));
    }

    public void testFactory_fromDateFields_beforeYearZero3_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(3, 1, 3, 4, 5, 6);
        cal.set(Calendar.ERA, GregorianCalendar.BC);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDateTime expected = new LocalDateTime(-2, 2, 3, 4, 5, 6, 7);
        assertEquals(expected,LocalDateTime.fromDateFields(cal.getTime()));
    }

    public void testConstructor_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime();
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime();
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime();
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime();
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime();
        assertEquals(10 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime();
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime();
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime();
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_9_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime();
        assertEquals(test,LocalDateTime.now());
    }

    public void testConstructor_DateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_DateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        assertEquals(2005,test.getYear());
    }

    public void testConstructor_DateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_DateTimeZone_4_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        assertEquals(8,test.getDayOfMonth());
    }

    public void testConstructor_DateTimeZone_5_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        assertEquals(23,test.getHourOfDay());
    }

    public void testConstructor_DateTimeZone_6_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        assertEquals(59,test.getMinuteOfHour());
    }

    public void testConstructor_DateTimeZone_7_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_DateTimeZone_8_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_DateTimeZone_9_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        assertEquals(test,LocalDateTime.now(LONDON));
    }

    public void testConstructor_DateTimeZone_10_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        
        test = new LocalDateTime(PARIS);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_DateTimeZone_11_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        
        test = new LocalDateTime(PARIS);
        assertEquals(2005,test.getYear());
    }

    public void testConstructor_DateTimeZone_12_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        
        test = new LocalDateTime(PARIS);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_DateTimeZone_13_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        
        test = new LocalDateTime(PARIS);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_DateTimeZone_14_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        
        test = new LocalDateTime(PARIS);
        assertEquals(0,test.getHourOfDay());
    }

    public void testConstructor_DateTimeZone_15_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        
        test = new LocalDateTime(PARIS);
        assertEquals(59,test.getMinuteOfHour());
    }

    public void testConstructor_DateTimeZone_16_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        
        test = new LocalDateTime(PARIS);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_DateTimeZone_17_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        
        test = new LocalDateTime(PARIS);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_DateTimeZone_18_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        LocalDateTime test = new LocalDateTime(LONDON);
        
        test = new LocalDateTime(PARIS);
        assertEquals(test,LocalDateTime.now(PARIS));
    }

    public void testConstructor_nullDateTimeZone_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((DateTimeZone) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_nullDateTimeZone_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((DateTimeZone) null);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_nullDateTimeZone_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((DateTimeZone) null);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_nullDateTimeZone_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((DateTimeZone) null);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_nullDateTimeZone_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((DateTimeZone) null);
        assertEquals(10 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_nullDateTimeZone_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((DateTimeZone) null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullDateTimeZone_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((DateTimeZone) null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullDateTimeZone_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((DateTimeZone) null);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_Chronology_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_Chronology_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(GREGORIAN_PARIS);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_Chronology_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(GREGORIAN_PARIS);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_Chronology_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(GREGORIAN_PARIS);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_Chronology_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(GREGORIAN_PARIS);
        assertEquals(10 + OFFSET_PARIS,test.getHourOfDay());
    }

    public void testConstructor_Chronology_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(GREGORIAN_PARIS);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_Chronology_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(GREGORIAN_PARIS);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_Chronology_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(GREGORIAN_PARIS);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_Chronology_9_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(GREGORIAN_PARIS);
        assertEquals(test,LocalDateTime.now(GREGORIAN_PARIS));
    }

    public void testConstructor_nullChronology_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Chronology) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_nullChronology_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Chronology) null);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_nullChronology_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Chronology) null);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_nullChronology_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Chronology) null);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_nullChronology_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Chronology) null);
        assertEquals(10 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_nullChronology_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Chronology) null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullChronology_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Chronology) null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullChronology_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Chronology) null);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_long1_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_long1_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_long1_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_long1_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_long1_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1);
        assertEquals(12 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_long1_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_long1_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_long1_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_long2_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_long2_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2);
        assertEquals(1971,test.getYear());
    }

    public void testConstructor_long2_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2);
        assertEquals(5,test.getMonthOfYear());
    }

    public void testConstructor_long2_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2);
        assertEquals(7,test.getDayOfMonth());
    }

    public void testConstructor_long2_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2);
        assertEquals(14 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_long2_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2);
        assertEquals(28,test.getMinuteOfHour());
    }

    public void testConstructor_long2_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_long2_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_long1_DateTimeZone_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, PARIS);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_long1_DateTimeZone_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, PARIS);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_long1_DateTimeZone_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, PARIS);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_long1_DateTimeZone_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, PARIS);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_long1_DateTimeZone_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, PARIS);
        assertEquals(12 + OFFSET_PARIS,test.getHourOfDay());
    }

    public void testConstructor_long1_DateTimeZone_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, PARIS);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_long1_DateTimeZone_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, PARIS);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_long1_DateTimeZone_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, PARIS);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_long2_DateTimeZone_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, PARIS);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_long2_DateTimeZone_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, PARIS);
        assertEquals(1971,test.getYear());
    }

    public void testConstructor_long2_DateTimeZone_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, PARIS);
        assertEquals(5,test.getMonthOfYear());
    }

    public void testConstructor_long2_DateTimeZone_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, PARIS);
        assertEquals(7,test.getDayOfMonth());
    }

    public void testConstructor_long2_DateTimeZone_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, PARIS);
        assertEquals(14 + OFFSET_PARIS,test.getHourOfDay());
    }

    public void testConstructor_long2_DateTimeZone_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, PARIS);
        assertEquals(28,test.getMinuteOfHour());
    }

    public void testConstructor_long2_DateTimeZone_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, PARIS);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_long2_DateTimeZone_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, PARIS);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_long_nullDateTimeZone_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_long_nullDateTimeZone_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_long_nullDateTimeZone_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_long_nullDateTimeZone_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_long_nullDateTimeZone_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(12 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_long_nullDateTimeZone_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_long_nullDateTimeZone_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_long_nullDateTimeZone_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_long1_Chronology_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_long1_Chronology_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, GREGORIAN_PARIS);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_long1_Chronology_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, GREGORIAN_PARIS);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_long1_Chronology_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, GREGORIAN_PARIS);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_long1_Chronology_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, GREGORIAN_PARIS);
        assertEquals(12 + OFFSET_PARIS,test.getHourOfDay());
    }

    public void testConstructor_long1_Chronology_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, GREGORIAN_PARIS);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_long1_Chronology_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, GREGORIAN_PARIS);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_long1_Chronology_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, GREGORIAN_PARIS);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_long2_Chronology_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_long2_Chronology_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(1971,test.getYear());
    }

    public void testConstructor_long2_Chronology_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(5,test.getMonthOfYear());
    }

    public void testConstructor_long2_Chronology_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(7,test.getDayOfMonth());
    }

    public void testConstructor_long2_Chronology_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(14 + OFFSET_PARIS,test.getHourOfDay());
    }

    public void testConstructor_long2_Chronology_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(28,test.getMinuteOfHour());
    }

    public void testConstructor_long2_Chronology_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_long2_Chronology_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_long_nullChronology_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (Chronology) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_long_nullChronology_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (Chronology) null);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_long_nullChronology_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (Chronology) null);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_long_nullChronology_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (Chronology) null);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_long_nullChronology_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (Chronology) null);
        assertEquals(12 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_long_nullChronology_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (Chronology) null);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_long_nullChronology_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (Chronology) null);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_long_nullChronology_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(TEST_TIME1, (Chronology) null);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_Object1_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_Object1_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_Object1_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_Object1_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_Object1_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date);
        assertEquals(12 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_Object1_6_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_Object1_7_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_Object1_8_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_nullObject_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_nullObject_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_nullObject_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_nullObject_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null);
        assertEquals(10 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_nullObject_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString1_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06");
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_ObjectString1_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06");
        assertEquals(1972,test.getYear());
    }

    public void testConstructor_ObjectString1_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06");
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_ObjectString1_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06");
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_ObjectString1_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06");
        assertEquals(0,test.getHourOfDay());
    }

    public void testConstructor_ObjectString1_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06");
        assertEquals(0,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString1_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06");
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString1_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06");
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString2_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-037");
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_ObjectString2_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-037");
        assertEquals(1972,test.getYear());
    }

    public void testConstructor_ObjectString2_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-037");
        assertEquals(2,test.getMonthOfYear());
    }

    public void testConstructor_ObjectString2_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-037");
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_ObjectString2_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-037");
        assertEquals(0,test.getHourOfDay());
    }

    public void testConstructor_ObjectString2_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-037");
        assertEquals(0,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString2_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-037");
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString2_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-037");
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString3_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20:30.040");
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_ObjectString3_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20:30.040");
        assertEquals(1972,test.getYear());
    }

    public void testConstructor_ObjectString3_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20:30.040");
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_ObjectString3_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20:30.040");
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_ObjectString3_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20:30.040");
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_ObjectString3_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20:30.040");
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString3_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20:30.040");
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString3_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20:30.040");
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString4_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20");
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_ObjectString4_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20");
        assertEquals(1972,test.getYear());
    }

    public void testConstructor_ObjectString4_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20");
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_ObjectString4_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20");
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_ObjectString4_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20");
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_ObjectString4_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20");
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString4_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20");
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString4_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1972-04-06T10:20");
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectLocalDateTime_1_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDateTime test = new LocalDateTime(dt);
        assertEquals(BUDDHIST_UTC,test.getChronology());
    }

    public void testConstructor_ObjectLocalDateTime_2_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDateTime test = new LocalDateTime(dt);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_ObjectLocalDateTime_3_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDateTime test = new LocalDateTime(dt);
        assertEquals(5,test.getMonthOfYear());
    }

    public void testConstructor_ObjectLocalDateTime_4_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDateTime test = new LocalDateTime(dt);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_ObjectLocalDateTime_5_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDateTime test = new LocalDateTime(dt);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_ObjectLocalDateTime_6_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDateTime test = new LocalDateTime(dt);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectLocalDateTime_7_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDateTime test = new LocalDateTime(dt);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectLocalDateTime_8_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDateTime test = new LocalDateTime(dt);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_Object_DateTimeZone_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, PARIS);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_Object_DateTimeZone_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, PARIS);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_Object_DateTimeZone_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, PARIS);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_Object_DateTimeZone_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, PARIS);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_Object_DateTimeZone_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, PARIS);
        assertEquals(12 + OFFSET_PARIS,test.getHourOfDay());
    }

    public void testConstructor_Object_DateTimeZone_6_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, PARIS);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_Object_DateTimeZone_7_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, PARIS);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_Object_DateTimeZone_8_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, PARIS);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_Object_DateTimeZoneMoscow_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", MOSCOW);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_Object_DateTimeZoneMoscow_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", MOSCOW);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_Object_DateTimeZoneMoscow_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", MOSCOW);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_Object_DateTimeZoneMoscow_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", MOSCOW);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_Object_DateTimeZoneMoscow_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", MOSCOW);
        assertEquals(12,test.getHourOfDay());
    }

    public void testConstructor_Object_DateTimeZoneMoscow_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", MOSCOW);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_Object_DateTimeZoneMoscow_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", MOSCOW);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_Object_DateTimeZoneMoscow_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", MOSCOW);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_Object_DateTimeZoneMoscowBadDateTime_1_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", MOSCOW);  // doesnt exist
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_Object_DateTimeZoneMoscowBadDateTime_2_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", MOSCOW);  // doesnt exist
        assertEquals(1981,test.getYear());
    }

    public void testConstructor_Object_DateTimeZoneMoscowBadDateTime_3_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", MOSCOW);  // doesnt exist
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_Object_DateTimeZoneMoscowBadDateTime_4_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", MOSCOW);  // doesnt exist
        assertEquals(1,test.getDayOfMonth());
    }

    public void testConstructor_Object_DateTimeZoneMoscowBadDateTime_5_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", MOSCOW);  // doesnt exist
        assertEquals(0,test.getHourOfDay());
    }

    public void testConstructor_Object_DateTimeZoneMoscowBadDateTime_6_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", MOSCOW);  // doesnt exist
        assertEquals(30,test.getMinuteOfHour());
    }

    public void testConstructor_Object_DateTimeZoneMoscowBadDateTime_7_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", MOSCOW);  // doesnt exist
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_Object_DateTimeZoneMoscowBadDateTime_8_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", MOSCOW);  // doesnt exist
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_DateTimeZone_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, PARIS);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_nullObject_DateTimeZone_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, PARIS);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_nullObject_DateTimeZone_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, PARIS);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_nullObject_DateTimeZone_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, PARIS);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_nullObject_DateTimeZone_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, PARIS);
        assertEquals(10 + OFFSET_PARIS,test.getHourOfDay());
    }

    public void testConstructor_nullObject_DateTimeZone_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, PARIS);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_DateTimeZone_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, PARIS);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_DateTimeZone_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, PARIS);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_Object_nullDateTimeZone_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (DateTimeZone) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_Object_nullDateTimeZone_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (DateTimeZone) null);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_Object_nullDateTimeZone_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (DateTimeZone) null);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_Object_nullDateTimeZone_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (DateTimeZone) null);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_Object_nullDateTimeZone_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (DateTimeZone) null);
        assertEquals(12 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_Object_nullDateTimeZone_6_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (DateTimeZone) null);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_Object_nullDateTimeZone_7_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (DateTimeZone) null);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_Object_nullDateTimeZone_8_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (DateTimeZone) null);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_nullDateTimeZone_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (DateTimeZone) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_nullObject_nullDateTimeZone_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (DateTimeZone) null);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_nullObject_nullDateTimeZone_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (DateTimeZone) null);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_nullObject_nullDateTimeZone_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (DateTimeZone) null);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_nullObject_nullDateTimeZone_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (DateTimeZone) null);
        assertEquals(10 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_nullObject_nullDateTimeZone_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (DateTimeZone) null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_nullDateTimeZone_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (DateTimeZone) null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_nullDateTimeZone_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (DateTimeZone) null);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_Object_Chronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_Object_Chronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, GREGORIAN_PARIS);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_Object_Chronology_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, GREGORIAN_PARIS);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_Object_Chronology_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, GREGORIAN_PARIS);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_Object_Chronology_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, GREGORIAN_PARIS);
        assertEquals(12 + OFFSET_PARIS,test.getHourOfDay());
    }

    public void testConstructor_Object_Chronology_6_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, GREGORIAN_PARIS);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_Object_Chronology_7_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, GREGORIAN_PARIS);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_Object_Chronology_8_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, GREGORIAN_PARIS);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_Object_Chronology_crossChronology_1_oe() throws Throwable {
        LocalDateTime input = new LocalDateTime(1970, 4, 6, 12, 30, 0, 0, ISO_UTC);
        LocalDateTime test = new LocalDateTime(input, BUDDHIST_UTC);
        assertEquals(BUDDHIST_UTC,test.getChronology());
    }

    public void testConstructor_Object_Chronology_crossChronology_2_oe() throws Throwable {
        LocalDateTime input = new LocalDateTime(1970, 4, 6, 12, 30, 0, 0, ISO_UTC);
        LocalDateTime test = new LocalDateTime(input, BUDDHIST_UTC);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_Object_Chronology_crossChronology_3_oe() throws Throwable {
        LocalDateTime input = new LocalDateTime(1970, 4, 6, 12, 30, 0, 0, ISO_UTC);
        LocalDateTime test = new LocalDateTime(input, BUDDHIST_UTC);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_Object_Chronology_crossChronology_4_oe() throws Throwable {
        LocalDateTime input = new LocalDateTime(1970, 4, 6, 12, 30, 0, 0, ISO_UTC);
        LocalDateTime test = new LocalDateTime(input, BUDDHIST_UTC);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_Object_Chronology_crossChronology_5_oe() throws Throwable {
        LocalDateTime input = new LocalDateTime(1970, 4, 6, 12, 30, 0, 0, ISO_UTC);
        LocalDateTime test = new LocalDateTime(input, BUDDHIST_UTC);
        assertEquals(12,test.getHourOfDay());
    }

    public void testConstructor_Object_Chronology_crossChronology_6_oe() throws Throwable {
        LocalDateTime input = new LocalDateTime(1970, 4, 6, 12, 30, 0, 0, ISO_UTC);
        LocalDateTime test = new LocalDateTime(input, BUDDHIST_UTC);
        assertEquals(30,test.getMinuteOfHour());
    }

    public void testConstructor_Object_Chronology_crossChronology_7_oe() throws Throwable {
        LocalDateTime input = new LocalDateTime(1970, 4, 6, 12, 30, 0, 0, ISO_UTC);
        LocalDateTime test = new LocalDateTime(input, BUDDHIST_UTC);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_Object_Chronology_crossChronology_8_oe() throws Throwable {
        LocalDateTime input = new LocalDateTime(1970, 4, 6, 12, 30, 0, 0, ISO_UTC);
        LocalDateTime test = new LocalDateTime(input, BUDDHIST_UTC);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_Object_ChronologyMoscow_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", GREGORIAN_MOSCOW);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_Object_ChronologyMoscow_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", GREGORIAN_MOSCOW);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_Object_ChronologyMoscow_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", GREGORIAN_MOSCOW);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_Object_ChronologyMoscow_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", GREGORIAN_MOSCOW);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_Object_ChronologyMoscow_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", GREGORIAN_MOSCOW);
        assertEquals(12,test.getHourOfDay());
    }

    public void testConstructor_Object_ChronologyMoscow_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", GREGORIAN_MOSCOW);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_Object_ChronologyMoscow_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", GREGORIAN_MOSCOW);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_Object_ChronologyMoscow_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime("1970-04-06T12:24:00", GREGORIAN_MOSCOW);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_Object_ChronologyMoscowBadDateTime_1_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", GREGORIAN_MOSCOW);  // doesnt exist
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_Object_ChronologyMoscowBadDateTime_2_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", GREGORIAN_MOSCOW);  // doesnt exist
        assertEquals(1981,test.getYear());
    }

    public void testConstructor_Object_ChronologyMoscowBadDateTime_3_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", GREGORIAN_MOSCOW);  // doesnt exist
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_Object_ChronologyMoscowBadDateTime_4_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", GREGORIAN_MOSCOW);  // doesnt exist
        assertEquals(1,test.getDayOfMonth());
    }

    public void testConstructor_Object_ChronologyMoscowBadDateTime_5_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", GREGORIAN_MOSCOW);  // doesnt exist
        assertEquals(0,test.getHourOfDay());
    }

    public void testConstructor_Object_ChronologyMoscowBadDateTime_6_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", GREGORIAN_MOSCOW);  // doesnt exist
        assertEquals(30,test.getMinuteOfHour());
    }

    public void testConstructor_Object_ChronologyMoscowBadDateTime_7_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", GREGORIAN_MOSCOW);  // doesnt exist
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_Object_ChronologyMoscowBadDateTime_8_oe() throws Throwable {
        
        LocalDateTime test = new LocalDateTime("1981-04-01T00:30:00", GREGORIAN_MOSCOW);  // doesnt exist
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_Chronology_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_nullObject_Chronology_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, GREGORIAN_PARIS);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_nullObject_Chronology_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, GREGORIAN_PARIS);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_nullObject_Chronology_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, GREGORIAN_PARIS);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_nullObject_Chronology_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, GREGORIAN_PARIS);
        assertEquals(10 + OFFSET_PARIS,test.getHourOfDay());
    }

    public void testConstructor_nullObject_Chronology_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, GREGORIAN_PARIS);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_Chronology_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, GREGORIAN_PARIS);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_Chronology_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, GREGORIAN_PARIS);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_Object_nullChronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (Chronology) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_Object_nullChronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (Chronology) null);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_Object_nullChronology_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (Chronology) null);
        assertEquals(4,test.getMonthOfYear());
    }

    public void testConstructor_Object_nullChronology_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (Chronology) null);
        assertEquals(6,test.getDayOfMonth());
    }

    public void testConstructor_Object_nullChronology_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (Chronology) null);
        assertEquals(12 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_Object_nullChronology_6_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (Chronology) null);
        assertEquals(24,test.getMinuteOfHour());
    }

    public void testConstructor_Object_nullChronology_7_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (Chronology) null);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_Object_nullChronology_8_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDateTime test = new LocalDateTime(date, (Chronology) null);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_nullChronology_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (Chronology) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_nullObject_nullChronology_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (Chronology) null);
        assertEquals(1970,test.getYear());
    }

    public void testConstructor_nullObject_nullChronology_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (Chronology) null);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_nullObject_nullChronology_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (Chronology) null);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_nullObject_nullChronology_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (Chronology) null);
        assertEquals(10 + OFFSET_MOSCOW,test.getHourOfDay());
    }

    public void testConstructor_nullObject_nullChronology_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (Chronology) null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_nullChronology_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (Chronology) null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_nullChronology_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime((Object) null, (Chronology) null);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_int_int_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_int_int_int_int_int_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20);
        assertEquals(2005,test.getYear());
    }

    public void testConstructor_int_int_int_int_int_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_int_int_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_int_int_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_int_int_int_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_int_int_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_int_int_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_int_int_int_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_int_int_int_int_int_int_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30);
        assertEquals(2005,test.getYear());
    }

    public void testConstructor_int_int_int_int_int_int_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_int_int_int_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_int_int_int_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_int_int_int_int_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_int_int_int_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_int_int_int_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_int_int_int_int_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_int_int_int_int_int_int_int_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40);
        assertEquals(2005,test.getYear());
    }

    public void testConstructor_int_int_int_int_int_int_int_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_int_int_int_int_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_int_int_int_int_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_int_int_int_int_int_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_int_int_int_int_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_int_int_int_int_8_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_Chronology_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_int_int_int_Chronology_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GREGORIAN_PARIS);
        assertEquals(2005,test.getYear());
    }

    public void testConstructor_int_int_int_Chronology_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GREGORIAN_PARIS);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_Chronology_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GREGORIAN_PARIS);
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_Chronology_5_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GREGORIAN_PARIS);
        assertEquals(10,test.getHourOfDay());// PARIS has no effect assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_Chronology_6_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GREGORIAN_PARIS);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_Chronology_7_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, GREGORIAN_PARIS);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_nullChronology_1_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_int_int_int_nullChronology_2_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, null);
        assertEquals(2005,test.getYear());
    }

    public void testConstructor_int_int_int_nullChronology_3_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, null);
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_nullChronology_4_oe() throws Throwable {
        LocalDateTime test = new LocalDateTime(2005, 6, 9, 10, 20, 30, 40, null);
        assertEquals(9,test.getDayOfMonth());
    }

}
