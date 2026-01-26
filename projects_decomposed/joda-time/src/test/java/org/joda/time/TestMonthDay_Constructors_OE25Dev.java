/*
 *  Copyright 2001-2010 Stephen Colebourne
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

import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for MonthDay. Based on {@link TestYearMonth_Constuctors} 
 */
public class TestMonthDay_Constructors_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final Chronology GREGORIAN_UTC = GregorianChronology.getInstanceUTC();
    private static final Chronology GREGORIAN_PARIS = GregorianChronology.getInstance(PARIS);
    
    private long TEST_TIME_NOW =
            (31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
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
        return new TestSuite(TestMonthDay_Constructors_OE25Dev_OE25Dev.class);
    }

    public TestMonthDay_Constructors_OE25Dev(String name) {
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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    /**
     * Test constructor ()
     */

    /**
     * Test constructor (DateTimeZone)
     */

    /**
     * Test constructor (DateTimeZone=null)
     */

    /**
     * Test constructor (Chronology)
     */

    /**
     * Test constructor (Chronology=null)
     */

    //-----------------------------------------------------------------------
    /**
     * Test constructor (long)
     */

    /**
     * Test constructor (long)
     */

    /**
     * Test constructor (long, Chronology)
     */

    /**
     * Test constructor (long, Chronology)
     */

    /**
     * Test constructor (long, Chronology=null)
     */

    //-----------------------------------------------------------------------

    public void testConstructor_ObjectStringEx1() throws Throwable {
        try {
            new MonthDay("T10:20:30.040");
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    public void testConstructor_ObjectStringEx2() throws Throwable {
        try {
            new MonthDay("T10:20:30.040+14:00");
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    public void testConstructor_ObjectStringEx3() throws Throwable {
        try {
            new MonthDay("10:20:30.040");
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    public void testConstructor_ObjectStringEx4() throws Throwable {
        try {
            new MonthDay("10:20:30.040+14:00");
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor (Object, Chronology)
     */

    /**
     * Test constructor (Object=null, Chronology)
     */

    /**
     * Test constructor (Object, Chronology=null)
     */

    /**
     * Test constructor (Object=null, Chronology=null)
     */

    //-----------------------------------------------------------------------
    /**
     * Test constructor (int, int)
     */

    /**
     * Test constructor (int, int, Chronology)
     */

    /**
     * Test constructor (int, int, Chronology=null)
     */

    public void testParse_noFormatter_1_oe() throws Throwable {
        assertEquals(new MonthDay(6, 30), MonthDay.parse("--06-30"));
    }

    public void testParse_noFormatter_2_oe() throws Throwable {
        // removed other assertion
        assertEquals(new MonthDay(2, 29), MonthDay.parse("--02-29"));
    }

    public void testParse_noFormatter_3_oe() throws Throwable {
        // removed other assertion
        // removed other assertion
        assertEquals(new MonthDay(6, 30), MonthDay.parse("2010-06-30"));
    }

    public void testParse_noFormatter_4_oe() throws Throwable {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new MonthDay(1, 2), MonthDay.parse("2010-002"));
    }

    public void testParse_formatter_1_oe() throws Throwable {
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy--dd MM").withChronology(ISOChronology.getInstance(PARIS));
        assertEquals(new MonthDay(6, 30), MonthDay.parse("2010--30 06", f));
    }

    public void testFactory_FromCalendarFields_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1970, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        MonthDay expected = new MonthDay(2, 3);
        assertEquals(expected, MonthDay.fromCalendarFields(cal));
    }

    public void testFactory_FromDateFields_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1970, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        MonthDay expected = new MonthDay(2, 3);
        assertEquals(expected, MonthDay.fromDateFields(cal.getTime()));
    }

    public void testConstructor_1_oe() throws Throwable {
        MonthDay test = new MonthDay();
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_2_oe() throws Throwable {
        MonthDay test = new MonthDay();
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_3_oe() throws Throwable {
        MonthDay test = new MonthDay();
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_4_oe() throws Throwable {
        MonthDay test = new MonthDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, MonthDay.now());
    }

    public void testConstructor_DateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay(LONDON);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_DateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay(LONDON);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_DateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay(LONDON);
        // removed other assertion
        // removed other assertion
        assertEquals(30, test.getDayOfMonth());
    }

    public void testConstructor_DateTimeZone_4_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, MonthDay.now(LONDON));
    }

    public void testConstructor_DateTimeZone_5_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new MonthDay(PARIS);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_DateTimeZone_6_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new MonthDay(PARIS);
        // removed other assertion
        assertEquals(7, test.getMonthOfYear());
    }

    public void testConstructor_DateTimeZone_7_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new MonthDay(PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDayOfMonth());
    }

    public void testConstructor_DateTimeZone_8_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new MonthDay(PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, MonthDay.now(PARIS));
    }

    public void testConstructor_nullDateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay((DateTimeZone) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_nullDateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay((DateTimeZone) null);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullDateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 30, 23, 59, 0, 0, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        // 23:59 in London is 00:59 the following day in Paris
        
        MonthDay test = new MonthDay((DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        assertEquals(30, test.getDayOfMonth());
    }

    public void testConstructor_Chronology_1_oe() throws Throwable {
        MonthDay test = new MonthDay(GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_Chronology_2_oe() throws Throwable {
        MonthDay test = new MonthDay(GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_Chronology_3_oe() throws Throwable {
        MonthDay test = new MonthDay(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_Chronology_4_oe() throws Throwable {
        MonthDay test = new MonthDay(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, MonthDay.now(GREGORIAN_PARIS));
    }

    public void testConstructor_nullChronology_1_oe() throws Throwable {
        MonthDay test = new MonthDay((Chronology) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_nullChronology_2_oe() throws Throwable {
        MonthDay test = new MonthDay((Chronology) null);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullChronology_3_oe() throws Throwable {
        MonthDay test = new MonthDay((Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_long1_1_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME1);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long1_2_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME1);
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_long1_3_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_long2_1_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME2);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long2_2_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME2);
        // removed other assertion
        assertEquals(5, test.getMonthOfYear());
    }

    public void testConstructor_long2_3_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getDayOfMonth());
    }

    public void testConstructor_long1_Chronology_1_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME1, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_long1_Chronology_2_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME1, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_long1_Chronology_3_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME1, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_long2_Chronology_1_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_long2_Chronology_2_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(5, test.getMonthOfYear());
    }

    public void testConstructor_long2_Chronology_3_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getDayOfMonth());
    }

    public void testConstructor_long_nullChronology_1_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME1, null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_long_nullChronology_2_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME1, null);
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_long_nullChronology_3_oe() throws Throwable {
        MonthDay test = new MonthDay(TEST_TIME1, null);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_Object_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MonthDay test = new MonthDay(date);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_Object_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MonthDay test = new MonthDay(date);
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_Object_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MonthDay test = new MonthDay(date);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_nullObject_1_oe() throws Throwable {
        MonthDay test = new MonthDay((Object) null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_nullObject_2_oe() throws Throwable {
        MonthDay test = new MonthDay((Object) null);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullObject_3_oe() throws Throwable {
        MonthDay test = new MonthDay((Object) null);
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_ObjectString1_1_oe() throws Throwable {
        MonthDay test = new MonthDay("1972-12");
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_ObjectString1_2_oe() throws Throwable {
        MonthDay test = new MonthDay("1972-12");
        // removed other assertion
        assertEquals(12, test.getMonthOfYear());
    }

    public void testConstructor_ObjectString1_3_oe() throws Throwable {
        MonthDay test = new MonthDay("1972-12");
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDayOfMonth());
    }

    public void testConstructor_ObjectString5_1_oe() throws Throwable {
        MonthDay test = new MonthDay("10");
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_ObjectString5_2_oe() throws Throwable {
        MonthDay test = new MonthDay("10");
        // removed other assertion
        assertEquals(1, test.getMonthOfYear());
    }

    public void testConstructor_ObjectString5_3_oe() throws Throwable {
        MonthDay test = new MonthDay("10");
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getDayOfMonth());
    }

    public void testConstructor_Object_Chronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MonthDay test = new MonthDay(date, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_Object_Chronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MonthDay test = new MonthDay(date, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_Object_Chronology_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MonthDay test = new MonthDay(date, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_nullObject_Chronology_1_oe() throws Throwable {
        MonthDay test = new MonthDay((Object) null, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_nullObject_Chronology_2_oe() throws Throwable {
        MonthDay test = new MonthDay((Object) null, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullObject_Chronology_3_oe() throws Throwable {
        MonthDay test = new MonthDay((Object) null, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_Object_nullChronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MonthDay test = new MonthDay(date, null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_Object_nullChronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MonthDay test = new MonthDay(date, null);
        // removed other assertion
        assertEquals(4, test.getMonthOfYear());
    }

    public void testConstructor_Object_nullChronology_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MonthDay test = new MonthDay(date, null);
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getDayOfMonth());
    }

    public void testConstructor_nullObject_nullChronology_1_oe() throws Throwable {
        MonthDay test = new MonthDay((Object) null, null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_nullObject_nullChronology_2_oe() throws Throwable {
        MonthDay test = new MonthDay((Object) null, null);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_nullObject_nullChronology_3_oe() throws Throwable {
        MonthDay test = new MonthDay((Object) null, null);
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testConstructor_int_int_1_oe() throws Throwable {
        MonthDay test = new MonthDay(6, 30);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_int_int_2_oe() throws Throwable {
        MonthDay test = new MonthDay(6, 30);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_int_int_3_oe() throws Throwable {
        MonthDay test = new MonthDay(6, 30);
        // removed other assertion
        // removed other assertion
        assertEquals(30, test.getDayOfMonth());
    }

    public void testConstructor_int_int_Chronology_1_oe() throws Throwable {
        MonthDay test = new MonthDay(6, 30, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC, test.getChronology());
    }

    public void testConstructor_int_int_Chronology_2_oe() throws Throwable {
        MonthDay test = new MonthDay(6, 30, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_int_int_Chronology_3_oe() throws Throwable {
        MonthDay test = new MonthDay(6, 30, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(30, test.getDayOfMonth());
    }

    public void testConstructor_int_int_nullChronology_1_oe() throws Throwable {
        MonthDay test = new MonthDay(6, 30, null);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testConstructor_int_int_nullChronology_2_oe() throws Throwable {
        MonthDay test = new MonthDay(6, 30, null);
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testConstructor_int_int_nullChronology_3_oe() throws Throwable {
        MonthDay test = new MonthDay(6, 30, null);
        // removed other assertion
        // removed other assertion
        assertEquals(30, test.getDayOfMonth());
    }

}
