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

import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * This class is a Junit unit test for TimeOfDay.
 *
 * @author Stephen Colebourne
 */
@SuppressWarnings("deprecation")
public class TestTimeOfDay_Constructors_OE25Dev extends TestCase {

    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final ISOChronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final int OFFSET = 1;
    
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
        return new TestSuite(TestTimeOfDay_Constructors_OE25Dev.class);
    }

    public TestTimeOfDay_Constructors_OE25Dev(String name) {
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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testFactory_FromDateFields_null() throws Exception {
        try {
            TimeOfDay.fromDateFields(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    /**
     * Test factory (long)
     */

    /**
     * Test factory (long, Chronology)
     */

    /**
     * Test factory (long, Chronology=null)
     */

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
    /**
     * Test constructor (Object)
     */

    /**
     * Test constructor (Object)
     */

    /**
     * Test constructor (Object=null)
     */

    /**
     * Test constructor (Object)
     */

    public void testConstructor_ObjectStringEx1() throws Throwable {
        try {
            new TimeOfDay("1970-04-06");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx2() throws Throwable {
        try {
            new TimeOfDay("1970-04-06T+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx3() throws Throwable {
        try {
            new TimeOfDay("1970-04-06T10:20:30.040");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testConstructor_ObjectStringEx4() throws Throwable {
        try {
            new TimeOfDay("1970-04-06T10:20:30.040+14:00");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor (Object, Chronology)
     */

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
     * Test constructor (int, int, int, Chronology)
     */

    /**
     * Test constructor (int, int, int, Chronology=null)
     */

    /**
     * Test constructor (int, int, int)
     */

    /**
     * Test constructor (int, int, int, Chronology)
     */

    /**
     * Test constructor (int, int, int, Chronology=null)
     */

    /**
     * Test constructor (int, int, int, int)
     */

    /**
     * Test constructor (int, int, int, int, Chronology)
     */

    /**
     * Test constructor (int, int, int, int, Chronology=null)
     */

    public void testConstantMidnight_1_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.MIDNIGHT;
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstantMidnight_2_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.MIDNIGHT;
        assertEquals(0,test.getHourOfDay());
    }

    public void testConstantMidnight_3_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.MIDNIGHT;
        assertEquals(0,test.getMinuteOfHour());
    }

    public void testConstantMidnight_4_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.MIDNIGHT;
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstantMidnight_5_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.MIDNIGHT;
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testFactory_FromCalendarFields_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1970, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        TimeOfDay expected = new TimeOfDay(4, 5, 6, 7);
        assertEquals(expected,TimeOfDay.fromCalendarFields(cal));
    }

    public void testFactory_FromDateFields_after1970_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1970, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        TimeOfDay expected = new TimeOfDay(4, 5, 6, 7);
        assertEquals(expected,TimeOfDay.fromDateFields(cal.getTime()));
    }

    public void testFactory_FromDateFields_before1970_1_oe() throws Exception {
        GregorianCalendar cal = new GregorianCalendar(1969, 1, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 7);
        TimeOfDay expected = new TimeOfDay(4, 5, 6, 7);
        assertEquals(expected,TimeOfDay.fromDateFields(cal.getTime()));
    }

    public void testFactoryMillisOfDay_long1_1_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testFactoryMillisOfDay_long1_2_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1);
        assertEquals(1,test.getHourOfDay());
    }

    public void testFactoryMillisOfDay_long1_3_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1);
        assertEquals(2,test.getMinuteOfHour());
    }

    public void testFactoryMillisOfDay_long1_4_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1);
        assertEquals(3,test.getSecondOfMinute());
    }

    public void testFactoryMillisOfDay_long1_5_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1);
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testFactoryMillisOfDay_long1_Chronology_1_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1, JulianChronology.getInstance());
        assertEquals(JulianChronology.getInstanceUTC(),test.getChronology());
    }

    public void testFactoryMillisOfDay_long1_Chronology_2_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1, JulianChronology.getInstance());
        assertEquals(1,test.getHourOfDay());
    }

    public void testFactoryMillisOfDay_long1_Chronology_3_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1, JulianChronology.getInstance());
        assertEquals(2,test.getMinuteOfHour());
    }

    public void testFactoryMillisOfDay_long1_Chronology_4_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1, JulianChronology.getInstance());
        assertEquals(3,test.getSecondOfMinute());
    }

    public void testFactoryMillisOfDay_long1_Chronology_5_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1, JulianChronology.getInstance());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testFactoryMillisOfDay_long_nullChronology_1_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1, null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testFactoryMillisOfDay_long_nullChronology_2_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1, null);
        assertEquals(1,test.getHourOfDay());
    }

    public void testFactoryMillisOfDay_long_nullChronology_3_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1, null);
        assertEquals(2,test.getMinuteOfHour());
    }

    public void testFactoryMillisOfDay_long_nullChronology_4_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1, null);
        assertEquals(3,test.getSecondOfMinute());
    }

    public void testFactoryMillisOfDay_long_nullChronology_5_oe() throws Throwable {
        TimeOfDay test = TimeOfDay.fromMillisOfDay(TEST_TIME1, null);
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay();
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay();
        assertEquals(10 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay();
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay();
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay();
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_DateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay(LONDON);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_DateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay(LONDON);
        assertEquals(23,test.getHourOfDay());
    }

    public void testConstructor_DateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay(LONDON);
        assertEquals(59,test.getMinuteOfHour());
    }

    public void testConstructor_DateTimeZone_4_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay(LONDON);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_DateTimeZone_5_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay(LONDON);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_DateTimeZone_6_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay(LONDON);
        
        test = new TimeOfDay(PARIS);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_DateTimeZone_7_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay(LONDON);
        
        test = new TimeOfDay(PARIS);
        assertEquals(0,test.getHourOfDay());
    }

    public void testConstructor_DateTimeZone_8_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay(LONDON);
        
        test = new TimeOfDay(PARIS);
        assertEquals(59,test.getMinuteOfHour());
    }

    public void testConstructor_DateTimeZone_9_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay(LONDON);
        
        test = new TimeOfDay(PARIS);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_DateTimeZone_10_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay(LONDON);
        
        test = new TimeOfDay(PARIS);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_nullDateTimeZone_1_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay((DateTimeZone) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_nullDateTimeZone_2_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay((DateTimeZone) null);
        assertEquals(23,test.getHourOfDay());
    }

    public void testConstructor_nullDateTimeZone_3_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay((DateTimeZone) null);
        assertEquals(59,test.getMinuteOfHour());
    }

    public void testConstructor_nullDateTimeZone_4_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay((DateTimeZone) null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullDateTimeZone_5_oe() throws Throwable {
        DateTime dt = new DateTime(2005, 6, 8, 23, 59, 30, 40, LONDON);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        TimeOfDay test = new TimeOfDay((DateTimeZone) null);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_Chronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(JulianChronology.getInstance());
        assertEquals(JulianChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_Chronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(JulianChronology.getInstance());
        assertEquals(10 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_Chronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(JulianChronology.getInstance());
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_Chronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(JulianChronology.getInstance());
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_Chronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(JulianChronology.getInstance());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_nullChronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Chronology) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_nullChronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Chronology) null);
        assertEquals(10 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_nullChronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Chronology) null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullChronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Chronology) null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullChronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Chronology) null);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_long1_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_long1_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1);
        assertEquals(1 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_long1_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1);
        assertEquals(2,test.getMinuteOfHour());
    }

    public void testConstructor_long1_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1);
        assertEquals(3,test.getSecondOfMinute());
    }

    public void testConstructor_long1_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1);
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_long2_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME2);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_long2_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME2);
        assertEquals(5 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_long2_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME2);
        assertEquals(6,test.getMinuteOfHour());
    }

    public void testConstructor_long2_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME2);
        assertEquals(7,test.getSecondOfMinute());
    }

    public void testConstructor_long2_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME2);
        assertEquals(8,test.getMillisOfSecond());
    }

    public void testConstructor_long1_Chronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1, JulianChronology.getInstance());
        assertEquals(JulianChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_long1_Chronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1, JulianChronology.getInstance());
        assertEquals(1 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_long1_Chronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1, JulianChronology.getInstance());
        assertEquals(2,test.getMinuteOfHour());
    }

    public void testConstructor_long1_Chronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1, JulianChronology.getInstance());
        assertEquals(3,test.getSecondOfMinute());
    }

    public void testConstructor_long1_Chronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1, JulianChronology.getInstance());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_long2_Chronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME2, JulianChronology.getInstance());
        assertEquals(JulianChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_long2_Chronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME2, JulianChronology.getInstance());
        assertEquals(5 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_long2_Chronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME2, JulianChronology.getInstance());
        assertEquals(6,test.getMinuteOfHour());
    }

    public void testConstructor_long2_Chronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME2, JulianChronology.getInstance());
        assertEquals(7,test.getSecondOfMinute());
    }

    public void testConstructor_long2_Chronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME2, JulianChronology.getInstance());
        assertEquals(8,test.getMillisOfSecond());
    }

    public void testConstructor_long_nullChronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1, null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_long_nullChronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1, null);
        assertEquals(1 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_long_nullChronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1, null);
        assertEquals(2,test.getMinuteOfHour());
    }

    public void testConstructor_long_nullChronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1, null);
        assertEquals(3,test.getSecondOfMinute());
    }

    public void testConstructor_long_nullChronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(TEST_TIME1, null);
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_Object1_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_Object1_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date);
        assertEquals(1 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_Object1_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date);
        assertEquals(2,test.getMinuteOfHour());
    }

    public void testConstructor_Object1_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date);
        assertEquals(3,test.getSecondOfMinute());
    }

    public void testConstructor_Object1_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date);
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_Object2_1_oe() throws Throwable {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(TEST_TIME1));
        TimeOfDay test = new TimeOfDay(cal);
        assertEquals(GJChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_Object2_2_oe() throws Throwable {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(TEST_TIME1));
        TimeOfDay test = new TimeOfDay(cal);
        assertEquals(1 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_Object2_3_oe() throws Throwable {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(TEST_TIME1));
        TimeOfDay test = new TimeOfDay(cal);
        assertEquals(2,test.getMinuteOfHour());
    }

    public void testConstructor_Object2_4_oe() throws Throwable {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(TEST_TIME1));
        TimeOfDay test = new TimeOfDay(cal);
        assertEquals(3,test.getSecondOfMinute());
    }

    public void testConstructor_Object2_5_oe() throws Throwable {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(TEST_TIME1));
        TimeOfDay test = new TimeOfDay(cal);
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_nullObject_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null);
        assertEquals(10 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_nullObject_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_todObject_1_oe() throws Throwable {
        TimeOfDay base = new TimeOfDay(10, 20, 30, 40, CopticChronology.getInstance(PARIS));
        TimeOfDay test = new TimeOfDay(base);
        assertEquals(CopticChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_todObject_2_oe() throws Throwable {
        TimeOfDay base = new TimeOfDay(10, 20, 30, 40, CopticChronology.getInstance(PARIS));
        TimeOfDay test = new TimeOfDay(base);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_todObject_3_oe() throws Throwable {
        TimeOfDay base = new TimeOfDay(10, 20, 30, 40, CopticChronology.getInstance(PARIS));
        TimeOfDay test = new TimeOfDay(base);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_todObject_4_oe() throws Throwable {
        TimeOfDay base = new TimeOfDay(10, 20, 30, 40, CopticChronology.getInstance(PARIS));
        TimeOfDay test = new TimeOfDay(base);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_todObject_5_oe() throws Throwable {
        TimeOfDay base = new TimeOfDay(10, 20, 30, 40, CopticChronology.getInstance(PARIS));
        TimeOfDay test = new TimeOfDay(base);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString1_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20:30.040");
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_ObjectString1_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20:30.040");
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_ObjectString1_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20:30.040");
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString1_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20:30.040");
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString1_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20:30.040");
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString2_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20:30.040+04:00");
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_ObjectString2_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20:30.040+04:00");
        assertEquals(10 + OFFSET - 4,test.getHourOfDay());
    }

    public void testConstructor_ObjectString2_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20:30.040+04:00");
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString2_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20:30.040+04:00");
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString2_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20:30.040+04:00");
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString3_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20:30.040");
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_ObjectString3_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20:30.040");
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_ObjectString3_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20:30.040");
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString3_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20:30.040");
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString3_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20:30.040");
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString4_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20:30.040+04:00");
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_ObjectString4_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20:30.040+04:00");
        assertEquals(10 + OFFSET - 4,test.getHourOfDay());
    }

    public void testConstructor_ObjectString4_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20:30.040+04:00");
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString4_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20:30.040+04:00");
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString4_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20:30.040+04:00");
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString5_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20");
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_ObjectString5_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20");
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_ObjectString5_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20");
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString5_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20");
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString5_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10:20");
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_ObjectString6_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10");
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_ObjectString6_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10");
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_ObjectString6_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10");
        assertEquals(0,test.getMinuteOfHour());
    }

    public void testConstructor_ObjectString6_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10");
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_ObjectString6_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("10");
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_Object_Chronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date, JulianChronology.getInstance());
        assertEquals(JulianChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_Object_Chronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date, JulianChronology.getInstance());
        assertEquals(1 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_Object_Chronology_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date, JulianChronology.getInstance());
        assertEquals(2,test.getMinuteOfHour());
    }

    public void testConstructor_Object_Chronology_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date, JulianChronology.getInstance());
        assertEquals(3,test.getSecondOfMinute());
    }

    public void testConstructor_Object_Chronology_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date, JulianChronology.getInstance());
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor2_Object_Chronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20");
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor2_Object_Chronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20");
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor2_Object_Chronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20");
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor2_Object_Chronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay("T10:20");
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_Chronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null, JulianChronology.getInstance());
        assertEquals(JulianChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_nullObject_Chronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null, JulianChronology.getInstance());
        assertEquals(10 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_nullObject_Chronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null, JulianChronology.getInstance());
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_Chronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null, JulianChronology.getInstance());
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_Chronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null, JulianChronology.getInstance());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_Object_nullChronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date, null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_Object_nullChronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date, null);
        assertEquals(1 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_Object_nullChronology_3_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date, null);
        assertEquals(2,test.getMinuteOfHour());
    }

    public void testConstructor_Object_nullChronology_4_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date, null);
        assertEquals(3,test.getSecondOfMinute());
    }

    public void testConstructor_Object_nullChronology_5_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        TimeOfDay test = new TimeOfDay(date, null);
        assertEquals(4,test.getMillisOfSecond());
    }

    public void testConstructor_nullObject_nullChronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null, null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_nullObject_nullChronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null, null);
        assertEquals(10 + OFFSET,test.getHourOfDay());
    }

    public void testConstructor_nullObject_nullChronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null, null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_nullObject_nullChronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null, null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_nullObject_nullChronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay((Object) null, null);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_int_int_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_Chronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, JulianChronology.getInstance());
        assertEquals(JulianChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_int_int_Chronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, JulianChronology.getInstance());
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_Chronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, JulianChronology.getInstance());
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_Chronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, JulianChronology.getInstance());
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_Chronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, JulianChronology.getInstance());
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_nullChronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_int_int_nullChronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, null);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_nullChronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_nullChronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, null);
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_nullChronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, null);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_int_int_int_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_int_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_Chronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, JulianChronology.getInstance());
        assertEquals(JulianChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_int_int_int_Chronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, JulianChronology.getInstance());
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_int_Chronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, JulianChronology.getInstance());
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_Chronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, JulianChronology.getInstance());
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_Chronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, JulianChronology.getInstance());
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_nullChronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_int_int_int_nullChronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, null);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_int_nullChronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_nullChronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_nullChronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, null);
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_int_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_int_int_int_int_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_int_int_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_int_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_int_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_int_Chronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40, JulianChronology.getInstance());
        assertEquals(JulianChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_int_int_int_int_Chronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40, JulianChronology.getInstance());
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_int_int_Chronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40, JulianChronology.getInstance());
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_int_Chronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40, JulianChronology.getInstance());
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_int_Chronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40, JulianChronology.getInstance());
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testConstructor_int_int_int_int_nullChronology_1_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40, null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_int_int_int_int_nullChronology_2_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40, null);
        assertEquals(10,test.getHourOfDay());
    }

    public void testConstructor_int_int_int_int_nullChronology_3_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40, null);
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testConstructor_int_int_int_int_nullChronology_4_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40, null);
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testConstructor_int_int_int_int_nullChronology_5_oe() throws Throwable {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40, null);
        assertEquals(40,test.getMillisOfSecond());
    }

}
