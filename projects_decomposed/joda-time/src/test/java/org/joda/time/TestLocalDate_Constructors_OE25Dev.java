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
 * This class is a Junit unit test for LocalDate.
 *
 * @author Stephen Colebourne
 */
public class TestLocalDate_Constructors_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final Chronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();
    private static final Chronology GREGORIAN_UTC = GregorianChronology.getInstanceUTC();
    private static final Chronology GREGORIAN_PARIS = GregorianChronology.getInstance(PARIS);
    
    private long TEST_TIME_NOW =
            (31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
    private long TEST_TIME1 =
        (31L + 28L + 31L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
        + 12L * DateTimeConstants.MILLIS_PER_HOUR
        + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
    private long TEST_TIME1_ROUNDED =
        (31L + 28L + 31L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY;
    private long TEST_TIME2 =
        (365L + 31L + 28L + 31L + 30L + 7L -1L) * DateTimeConstants.MILLIS_PER_DAY
        + 14L * DateTimeConstants.MILLIS_PER_HOUR
        + 28L * DateTimeConstants.MILLIS_PER_MINUTE;

    private DateTimeZone zone = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestLocalDate_Constructors_OE25Dev.class);
    }

    public TestLocalDate_Constructors_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(LONDON);
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
            LocalDate.fromCalendarFields((Calendar) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testFactory_fromDateFields_null() throws Exception {
        try {
            LocalDate.fromDateFields((Date) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testConstructor_ObjectStringEx1() throws Throwable {
        try {
            new LocalDate("1970-04-06T+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx2() throws Throwable {
        try {
            new LocalDate("1970-04-06T10:20:30.040");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx3() throws Throwable {
        try {
            new LocalDate("1970-04-06T10:20:30.040+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx4() throws Throwable {
        try {
            new LocalDate("T10:20:30.040");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx5() throws Throwable {
        try {
            new LocalDate("T10:20:30.040+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx6() throws Throwable {
        try {
            new LocalDate("10:20:30.040");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx7() throws Throwable {
        try {
            new LocalDate("10:20:30.040+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectLocalTime() throws Throwable {
        LocalTime time = new LocalTime(10, 20, 30, 40, BUDDHIST_UTC);
        try {
            new LocalDate(time);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testParse_noFormatter_1_oe() throws Throwable {
        assertEquals(new LocalDate(2010, 6, 30), LocalDate.parse("2010-06-30"));
    }

    public void testParse_noFormatter_2_oe() throws Throwable {
        // removed other assertion
        assertEquals(new LocalDate(2010, 1, 2), LocalDate.parse("2010-002"));
    }

    public void testParse_formatter_1_oe() throws Throwable {
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy--dd MM").withChronology(ISOChronology.getInstance(PARIS));
        assertEquals(new LocalDate(2010, 6, 30), LocalDate.parse("2010--30 06", f));
    }

    public void testFactory_fromCalendarFields_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1970, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDate expected = new LocalDate(1970, 2, 3);
        assertEquals(expected, LocalDate.fromCalendarFields(cal));
    }

    public void testFactory_fromCalendarFields_beforeYearZero1_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1, 1, 3, 4, 5, 6);
        cal.set(Calendar.ERA, GregorianCalendar.BC);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDate expected = new LocalDate(0, 2, 3);
        assertEquals(expected, LocalDate.fromCalendarFields(cal));
    }

    public void testFactory_fromCalendarFields_beforeYearZero3_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(3, 1, 3, 4, 5, 6);
        cal.set(Calendar.ERA, GregorianCalendar.BC);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDate expected = new LocalDate(-2, 2, 3);
        assertEquals(expected, LocalDate.fromCalendarFields(cal));
    }

    public void testFactory_fromDateFields_after1970_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1970, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDate expected = new LocalDate(1970, 2, 3);
        assertEquals(expected, LocalDate.fromDateFields(cal.getTime()));
    }

    public void testFactory_fromDateFields_before1970_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1969, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDate expected = new LocalDate(1969, 2, 3);
        assertEquals(expected, LocalDate.fromDateFields(cal.getTime()));
    }

    public void testFactory_fromDateFields_beforeYearZero1_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1, 1, 3, 4, 5, 6);
        cal.set(Calendar.ERA, GregorianCalendar.BC);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDate expected = new LocalDate(0, 2, 3);
        assertEquals(expected, LocalDate.fromDateFields(cal.getTime()));
    }

    public void testFactory_fromDateFields_beforeYearZero3_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(3, 1, 3, 4, 5, 6);
        cal.set(Calendar.ERA, GregorianCalendar.BC);
        cal.set(Calendar.MILLISECOND, 7);
        LocalDate expected = new LocalDate(-2, 2, 3);
        assertEquals(expected, LocalDate.fromDateFields(cal.getTime()));
    }

    public void testConstructor_1_oe() throws Throwable {
        LocalDate test = new LocalDate();
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_2_oe() throws Throwable {
        LocalDate test = new LocalDate();
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_3_oe() throws Throwable {
        LocalDate test = new LocalDate();
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_4_oe() throws Throwable {
        LocalDate test = new LocalDate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_5_oe() throws Throwable {
        LocalDate test = new LocalDate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, LocalDate.now());
    }

    public void testConstructor_DateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate(LONDON);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_DateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate(LONDON);
        // removed other assertion
        assertEquals(2005, test.getYear());
    }

    public void testConstructor_DateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate(LONDON);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_DateTimeZone_4_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getDayOfMonth());
    }

    public void testConstructor_DateTimeZone_5_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, LocalDate.now(LONDON));
    }

    public void testConstructor_DateTimeZone_6_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDate(PARIS);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_DateTimeZone_7_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDate(PARIS);
        // removed other assertion
        assertEquals(2005, test.getYear());
    }

    public void testConstructor_DateTimeZone_8_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDate(PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_DateTimeZone_9_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDate(PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_DateTimeZone_10_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new LocalDate(PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, LocalDate.now(PARIS));
    }

    public void testConstructor_nullDateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate((DateTimeZone) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_nullDateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate((DateTimeZone) null);
        // removed other assertion
        assertEquals(2005, test.getYear());
    }

    public void testConstructor_nullDateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate((DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullDateTimeZone_4_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        LocalDate test = new LocalDate((DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.getDayOfMonth());
    }

    public void testConstructor_Chronology_1_oe() throws Throwable {
        LocalDate test = new LocalDate(GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_Chronology_2_oe() throws Throwable {
        LocalDate test = new LocalDate(GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_Chronology_3_oe() throws Throwable {
        LocalDate test = new LocalDate(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_Chronology_4_oe() throws Throwable {
        LocalDate test = new LocalDate(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_Chronology_5_oe() throws Throwable {
        LocalDate test = new LocalDate(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, LocalDate.now(GREGORIAN_PARIS));
    }

    public void testConstructor_nullChronology_1_oe() throws Throwable {
        LocalDate test = new LocalDate((Chronology) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_nullChronology_2_oe() throws Throwable {
        LocalDate test = new LocalDate((Chronology) null);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_nullChronology_3_oe() throws Throwable {
        LocalDate test = new LocalDate((Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullChronology_4_oe() throws Throwable {
        LocalDate test = new LocalDate((Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_long1_1_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long1_2_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_long1_3_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_long1_4_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_long2_1_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long2_2_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2);
        // removed other assertion
        assertEquals(1971, test.getYear());
    }

    public void testConstructor_long2_3_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getMonthOfYear());
    }

    public void testConstructor_long2_4_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getDayOfMonth());
    }

    public void testConstructor_long1_DateTimeZone_1_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, PARIS);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long1_DateTimeZone_2_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, PARIS);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_long1_DateTimeZone_3_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_long1_DateTimeZone_4_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_long1_DateTimeZone_5_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME1_ROUNDED, test.getLocalMillis());
    }

    public void testConstructor_long2_DateTimeZone_1_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2, PARIS);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long2_DateTimeZone_2_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2, PARIS);
        // removed other assertion
        assertEquals(1971, test.getYear());
    }

    public void testConstructor_long2_DateTimeZone_3_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2, PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getMonthOfYear());
    }

    public void testConstructor_long2_DateTimeZone_4_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2, PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getDayOfMonth());
    }

    public void testConstructor_long3_DateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2006, 6, 9, 0, 0, 0, 0, PARIS);
        DateTime dtUTC = new DateTime(2006, 6, 9, 0, 0, 0, 0, DateTimeZone.UTC);
        
        LocalDate test = new LocalDate(dt.getMillis(), PARIS);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long3_DateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2006, 6, 9, 0, 0, 0, 0, PARIS);
        DateTime dtUTC = new DateTime(2006, 6, 9, 0, 0, 0, 0, DateTimeZone.UTC);
        
        LocalDate test = new LocalDate(dt.getMillis(), PARIS);
        // removed other assertion
        assertEquals(2006, test.getYear());
    }

    public void testConstructor_long3_DateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2006, 6, 9, 0, 0, 0, 0, PARIS);
        DateTime dtUTC = new DateTime(2006, 6, 9, 0, 0, 0, 0, DateTimeZone.UTC);
        
        LocalDate test = new LocalDate(dt.getMillis(), PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_long3_DateTimeZone_4_oe() throws Throwable {
        DateTime dt = new DateTime(2006, 6, 9, 0, 0, 0, 0, PARIS);
        DateTime dtUTC = new DateTime(2006, 6, 9, 0, 0, 0, 0, DateTimeZone.UTC);
        
        LocalDate test = new LocalDate(dt.getMillis(), PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_long3_DateTimeZone_5_oe() throws Throwable {
        DateTime dt = new DateTime(2006, 6, 9, 0, 0, 0, 0, PARIS);
        DateTime dtUTC = new DateTime(2006, 6, 9, 0, 0, 0, 0, DateTimeZone.UTC);
        
        LocalDate test = new LocalDate(dt.getMillis(), PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(dtUTC.getMillis(), test.getLocalMillis());
    }

    public void testConstructor_long4_DateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2006, 6, 9, 23, 59, 59, 999, PARIS);
        DateTime dtUTC = new DateTime(2006, 6, 9, 0, 0, 0, 0, DateTimeZone.UTC);
        
        LocalDate test = new LocalDate(dt.getMillis(), PARIS);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long4_DateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2006, 6, 9, 23, 59, 59, 999, PARIS);
        DateTime dtUTC = new DateTime(2006, 6, 9, 0, 0, 0, 0, DateTimeZone.UTC);
        
        LocalDate test = new LocalDate(dt.getMillis(), PARIS);
        // removed other assertion
        assertEquals(2006, test.getYear());
    }

    public void testConstructor_long4_DateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2006, 6, 9, 23, 59, 59, 999, PARIS);
        DateTime dtUTC = new DateTime(2006, 6, 9, 0, 0, 0, 0, DateTimeZone.UTC);
        
        LocalDate test = new LocalDate(dt.getMillis(), PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_long4_DateTimeZone_4_oe() throws Throwable {
        DateTime dt = new DateTime(2006, 6, 9, 23, 59, 59, 999, PARIS);
        DateTime dtUTC = new DateTime(2006, 6, 9, 0, 0, 0, 0, DateTimeZone.UTC);
        
        LocalDate test = new LocalDate(dt.getMillis(), PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_long4_DateTimeZone_5_oe() throws Throwable {
        DateTime dt = new DateTime(2006, 6, 9, 23, 59, 59, 999, PARIS);
        DateTime dtUTC = new DateTime(2006, 6, 9, 0, 0, 0, 0, DateTimeZone.UTC);
        
        LocalDate test = new LocalDate(dt.getMillis(), PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(dtUTC.getMillis(), test.getLocalMillis());
    }

    public void testConstructor_long_nullDateTimeZone_1_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, (DateTimeZone) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long_nullDateTimeZone_2_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, (DateTimeZone) null);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_long_nullDateTimeZone_3_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, (DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_long_nullDateTimeZone_4_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, (DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_long1_Chronology_1_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_long1_Chronology_2_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_long1_Chronology_3_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_long1_Chronology_4_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_long2_Chronology_1_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_long2_Chronology_2_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(1971, test.getYear());
    }

    public void testConstructor_long2_Chronology_3_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getMonthOfYear());
    }

    public void testConstructor_long2_Chronology_4_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getDayOfMonth());
    }

    public void testConstructor_long_nullChronology_1_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, (Chronology) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long_nullChronology_2_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, (Chronology) null);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_long_nullChronology_3_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, (Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_long_nullChronology_4_oe() throws Throwable {
        LocalDate test = new LocalDate(TEST_TIME1, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_Object1_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_Object1_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_Object1_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_Object1_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_nullObject_1_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_nullObject_2_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_nullObject_3_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullObject_4_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_ObjectString1_1_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-04-06");
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_ObjectString1_2_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-04-06");
        // removed other assertion
        assertEquals(1972, test.getYear());
    }

    public void testConstructor_ObjectString1_3_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-04-06");
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_ObjectString1_4_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-04-06");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_ObjectString2_1_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-037");
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_ObjectString2_2_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-037");
        // removed other assertion
        assertEquals(1972, test.getYear());
    }

    public void testConstructor_ObjectString2_3_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-037");
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getMonthOfYear());
    }

    public void testConstructor_ObjectString2_4_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-037");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_ObjectString3_1_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-02");
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_ObjectString3_2_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-02");
        // removed other assertion
        assertEquals(1972, test.getYear());
    }

    public void testConstructor_ObjectString3_3_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-02");
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getMonthOfYear());
    }

    public void testConstructor_ObjectString3_4_oe() throws Throwable {
        LocalDate test = new LocalDate("1972-02");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDayOfMonth());
    }

    public void testConstructor_ObjectLocalDate_1_oe() throws Throwable {
        LocalDate date = new LocalDate(1970, 4, 6, BUDDHIST_UTC);
        LocalDate test = new LocalDate(date);
        assertEquals(BUDDHIST_UTC, test.getChronology());
    }

    public void testConstructor_ObjectLocalDate_2_oe() throws Throwable {
        LocalDate date = new LocalDate(1970, 4, 6, BUDDHIST_UTC);
        LocalDate test = new LocalDate(date);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_ObjectLocalDate_3_oe() throws Throwable {
        LocalDate date = new LocalDate(1970, 4, 6, BUDDHIST_UTC);
        LocalDate test = new LocalDate(date);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_ObjectLocalDate_4_oe() throws Throwable {
        LocalDate date = new LocalDate(1970, 4, 6, BUDDHIST_UTC);
        LocalDate test = new LocalDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_ObjectLocalDateTime_1_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDate test = new LocalDate(dt);
        assertEquals(BUDDHIST_UTC, test.getChronology());
    }

    public void testConstructor_ObjectLocalDateTime_2_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDate test = new LocalDate(dt);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_ObjectLocalDateTime_3_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDate test = new LocalDate(dt);
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.getMonthOfYear());
    }

    public void testConstructor_ObjectLocalDateTime_4_oe() throws Throwable {
        LocalDateTime dt = new LocalDateTime(1970, 5, 6, 10, 20, 30, 40, BUDDHIST_UTC);
        LocalDate test = new LocalDate(dt);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_ObjectYearMonthDay_1_oe() throws Throwable {
        YearMonthDay date = new YearMonthDay(1970, 4, 6, BUDDHIST_UTC);
        LocalDate test = new LocalDate(date);
        assertEquals(BUDDHIST_UTC, test.getChronology());
    }

    public void testConstructor_ObjectYearMonthDay_2_oe() throws Throwable {
        YearMonthDay date = new YearMonthDay(1970, 4, 6, BUDDHIST_UTC);
        LocalDate test = new LocalDate(date);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_ObjectYearMonthDay_3_oe() throws Throwable {
        YearMonthDay date = new YearMonthDay(1970, 4, 6, BUDDHIST_UTC);
        LocalDate test = new LocalDate(date);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_ObjectYearMonthDay_4_oe() throws Throwable {
        YearMonthDay date = new YearMonthDay(1970, 4, 6, BUDDHIST_UTC);
        LocalDate test = new LocalDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_Object_DateTimeZone_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, PARIS);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_Object_DateTimeZone_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, PARIS);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_Object_DateTimeZone_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_Object_DateTimeZone_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_nullObject_DateTimeZone_1_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, PARIS);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_nullObject_DateTimeZone_2_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, PARIS);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_nullObject_DateTimeZone_3_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullObject_DateTimeZone_4_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_Object_nullDateTimeZone_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, (DateTimeZone) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_Object_nullDateTimeZone_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, (DateTimeZone) null);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_Object_nullDateTimeZone_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, (DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_Object_nullDateTimeZone_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, (DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_nullObject_nullDateTimeZone_1_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, (DateTimeZone) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_nullObject_nullDateTimeZone_2_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, (DateTimeZone) null);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_nullObject_nullDateTimeZone_3_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, (DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullObject_nullDateTimeZone_4_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, (DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_Object_Chronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_Object_Chronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_Object_Chronology_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_Object_Chronology_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_Object_Chronology_crossChronology_1_oe() throws Throwable {
        LocalDate input = new LocalDate(1970, 4, 6, ISO_UTC);
        LocalDate test = new LocalDate(input, BUDDHIST_UTC);
        assertEquals(BUDDHIST_UTC, test.getChronology());
    }

    public void testConstructor_Object_Chronology_crossChronology_2_oe() throws Throwable {
        LocalDate input = new LocalDate(1970, 4, 6, ISO_UTC);
        LocalDate test = new LocalDate(input, BUDDHIST_UTC);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_Object_Chronology_crossChronology_3_oe() throws Throwable {
        LocalDate input = new LocalDate(1970, 4, 6, ISO_UTC);
        LocalDate test = new LocalDate(input, BUDDHIST_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_Object_Chronology_crossChronology_4_oe() throws Throwable {
        LocalDate input = new LocalDate(1970, 4, 6, ISO_UTC);
        LocalDate test = new LocalDate(input, BUDDHIST_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_nullObject_Chronology_1_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_nullObject_Chronology_2_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_nullObject_Chronology_3_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullObject_Chronology_4_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_Object_nullChronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, (Chronology) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_Object_nullChronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, (Chronology) null);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_Object_nullChronology_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, (Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_Object_nullChronology_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        LocalDate test = new LocalDate(date, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_nullObject_nullChronology_1_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, (Chronology) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_nullObject_nullChronology_2_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, (Chronology) null);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_nullObject_nullChronology_3_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, (Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullObject_nullChronology_4_oe() throws Throwable {
        LocalDate test = new LocalDate((Object) null, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_1_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_int_int_int_2_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_int_int_int_3_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_4_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_6_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            new LocalDate(Integer.MIN_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertEquals("Value -2147483648 for year must be in the range [-292275055,292278994]", ex.getMessage());
    }
    }

    public void testConstructor_int_int_int_8_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            new LocalDate(Integer.MIN_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(Integer.MAX_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertEquals("Value 2147483647 for year must be in the range [-292275055,292278994]", ex.getMessage());
    }
    }

    public void testConstructor_int_int_int_10_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            new LocalDate(Integer.MIN_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(Integer.MAX_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 0, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertEquals("Value 0 for monthOfYear must be in the range [1,12]", ex.getMessage());
    }
    }

    public void testConstructor_int_int_int_12_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            new LocalDate(Integer.MIN_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(Integer.MAX_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 0, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 13, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertEquals("Value 13 for monthOfYear must be in the range [1,12]", ex.getMessage());
    }
    }

    public void testConstructor_int_int_int_14_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            new LocalDate(Integer.MIN_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(Integer.MAX_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 0, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 13, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 6, 0);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertEquals("Value 0 for dayOfMonth must be in the range [1,30]: year: 1970 month: 6", ex.getMessage());
    }
    }

    public void testConstructor_int_int_int_16_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            new LocalDate(Integer.MIN_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(Integer.MAX_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 0, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 13, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 6, 0);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 6, 31);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertEquals("Value 31 for dayOfMonth must be in the range [1,30]: year: 1970 month: 6", ex.getMessage());
    }
    }

    public void testConstructor_int_int_int_18_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            new LocalDate(Integer.MIN_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(Integer.MAX_VALUE, 6, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 0, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 13, 9);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 6, 0);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new LocalDate(1970, 6, 31);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        new LocalDate(1970, 7, 31);
        try {
            new LocalDate(1970, 7, 32);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertEquals("Value 32 for dayOfMonth must be in the range [1,31]: year: 1970 month: 7", ex.getMessage());
    }
    }

    public void testConstructor_int_int_int_Chronology_1_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_int_int_int_Chronology_2_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_int_int_int_Chronology_3_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_Chronology_4_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_nullChronology_1_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9, null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_int_int_int_nullChronology_2_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9, null);
        // removed other assertion
        assertEquals(1970, test.getYear());
    }

    public void testConstructor_int_int_int_nullChronology_3_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9, null);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_nullChronology_4_oe() throws Throwable {
        LocalDate test = new LocalDate(1970, 6, 9, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

}
