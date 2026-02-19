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

import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.MockZeroNullIntegerConverter;
import org.joda.time.format.DateTimeFormat;

/**
 * This class is a Junit unit test for DateMidnight.
 *
 * @author Stephen Colebourne
 */
@SuppressWarnings("deprecation")
public class TestDateMidnight_Constructors_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    
    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    long y2003days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365 + 365;
    
    // 2002-06-09
    private long TEST_TIME_NOW_UTC =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
    private long TEST_TIME_NOW_LONDON =
            TEST_TIME_NOW_UTC - DateTimeConstants.MILLIS_PER_HOUR;
    private long TEST_TIME_NOW_PARIS =
            TEST_TIME_NOW_UTC - 2*DateTimeConstants.MILLIS_PER_HOUR;
    
    // 2002-04-05
    private long TEST_TIME1_UTC =
            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 12L * DateTimeConstants.MILLIS_PER_HOUR
            + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
    private long TEST_TIME1_LONDON =
            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
            - DateTimeConstants.MILLIS_PER_HOUR;
    private long TEST_TIME1_PARIS =
            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
            - 2*DateTimeConstants.MILLIS_PER_HOUR;
    
    // 2003-05-06
    private long TEST_TIME2_UTC =
            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 14L * DateTimeConstants.MILLIS_PER_HOUR
            + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
    private long TEST_TIME2_LONDON =
            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
             - DateTimeConstants.MILLIS_PER_HOUR;
    private long TEST_TIME2_PARIS =
            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
             - 2*DateTimeConstants.MILLIS_PER_HOUR;
    
    private DateTimeZone zone = null;
    private Locale locale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateMidnight_Constructors_OE25Dev.class);
    }

    public TestDateMidnight_Constructors_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW_UTC);
        zone = DateTimeZone.getDefault();
        locale = Locale.getDefault();
        DateTimeZone.setDefault(LONDON);
        Locale.setDefault(Locale.UK);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        Locale.setDefault(locale);
        zone = null;
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    /**
     * Test now ()
     */

    /**
     * Test now (DateTimeZone)
     */

    /**
     * Test now (DateTimeZone=null)
     */
    public void test_now_nullDateTimeZone() throws Throwable {
        try {
            DateMidnight.now((DateTimeZone) null);
            fail();
        } catch (NullPointerException ex) {}
    }

    /**
     * Test now (Chronology)
     */

    /**
     * Test now (Chronology=null)
     */
    public void test_now_nullChronology() throws Throwable {
        try {
            DateMidnight.now((Chronology) null);
            fail();
        } catch (NullPointerException ex) {}
    }

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
     * Test constructor (long, DateTimeZone)
     */

    /**
     * Test constructor (long, DateTimeZone)
     */

    /**
     * Test constructor (long, DateTimeZone=null)
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
    public void testConstructor_invalidObject() throws Throwable {
        try {
            new DateMidnight(new Object());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test constructor (Object=null)
     */

    /**
     * Test constructor (Object=null)
     */
    public void testConstructor_badconverterObject() throws Throwable {
        try {
            ConverterManager.getInstance().addInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
            DateMidnight test = new DateMidnight(new Integer(0));
            assertEquals(ISOChronology.getInstance(),test.getChronology());
            assertEquals(0L - DateTimeConstants.MILLIS_PER_HOUR,test.getMillis());
        } finally {
            ConverterManager.getInstance().removeInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
        }
    }

    /**
     * Test constructor (Object, DateTimeZone)
     */

    /**
     * Test constructor (Object, DateTimeZone)
     */
    public void testConstructor_invalidObject_DateTimeZone() throws Throwable {
        try {
            new DateMidnight(new Object(), PARIS);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test constructor (Object=null, DateTimeZone)
     */

    /**
     * Test constructor (Object, DateTimeZone=null)
     */

    /**
     * Test constructor (Object=null, DateTimeZone=null)
     */

    /**
     * Test constructor (Object, DateTimeZone)
     */
    public void testConstructor_badconverterObject_DateTimeZone() throws Throwable {
        try {
            ConverterManager.getInstance().addInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
            DateMidnight test = new DateMidnight(new Integer(0), GregorianChronology.getInstance());
            assertEquals(ISOChronology.getInstance(),test.getChronology());
            assertEquals(0L - DateTimeConstants.MILLIS_PER_HOUR,test.getMillis());
        } finally {
            ConverterManager.getInstance().removeInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
        }
    }

    /**
     * Test constructor (Object, Chronology)
     */

    /**
     * Test constructor (Object, Chronology)
     */
    public void testConstructor_invalidObject_Chronology() throws Throwable {
        try {
            new DateMidnight(new Object(), GregorianChronology.getInstance());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test constructor (Object=null, Chronology)
     */

    /**
     * Test constructor (Object, Chronology=null)
     */

    /**
     * Test constructor (Object=null, Chronology=null)
     */

    /**
     * Test constructor (Object, Chronology)
     */
    public void testConstructor_badconverterObject_Chronology() throws Throwable {
        try {
            ConverterManager.getInstance().addInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
            DateMidnight test = new DateMidnight(new Integer(0), GregorianChronology.getInstance());
            assertEquals(ISOChronology.getInstance(),test.getChronology());
            assertEquals(0L - DateTimeConstants.MILLIS_PER_HOUR,test.getMillis());
        } finally {
            ConverterManager.getInstance().removeInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor (int, int, int)
     */

    /**
     * Test constructor (int, int, int, DateTimeZone)
     */

    /**
     * Test constructor (int, int, int, DateTimeZone=null)
     */

    /**
     * Test constructor (int, int, int, Chronology)
     */

    /**
     * Test constructor (int, int, int, Chronology=null)
     */

    public void testTest_1_oe() {
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW_UTC).toString());
    }

    public void testTest_2_oe() {
        // removed other assertion
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1_UTC).toString());
    }

    public void testTest_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2_UTC).toString());
    }

    public void test_now_1_oe() throws Throwable {
        DateMidnight test = DateMidnight.now();
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void test_now_2_oe() throws Throwable {
        DateMidnight test = DateMidnight.now();
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void test_now_3_oe() throws Throwable {
        DateMidnight test = DateMidnight.now();
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.getYear());
    }

    public void test_now_4_oe() throws Throwable {
        DateMidnight test = DateMidnight.now();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.getMonthOfYear());
    }

    public void test_now_5_oe() throws Throwable {
        DateMidnight test = DateMidnight.now();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getDayOfMonth());
    }

    public void test_now_DateTimeZone_1_oe() throws Throwable {
        DateMidnight test = DateMidnight.now(PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void test_now_DateTimeZone_2_oe() throws Throwable {
        DateMidnight test = DateMidnight.now(PARIS);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_PARIS,test.getMillis());
    }

    public void test_now_Chronology_1_oe() throws Throwable {
        DateMidnight test = DateMidnight.now(GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void test_now_Chronology_2_oe() throws Throwable {
        DateMidnight test = DateMidnight.now(GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testParse_noFormatter_1_oe() throws Throwable {
        assertEquals(new DateMidnight(2010,6,30,ISOChronology.getInstance(LONDON)),DateMidnight.parse("2010-06-30"));
    }

    public void testParse_noFormatter_2_oe() throws Throwable {
        // removed other assertion
        assertEquals(new DateMidnight(2010,1,2,ISOChronology.getInstance(LONDON)),DateMidnight.parse("2010-002"));
    }

    public void testParse_formatter_1_oe() throws Throwable {
        assertEquals(new DateMidnight(2010,6,30,ISOChronology.getInstance(LONDON)),DateMidnight.parse("2010--30 06",DateTimeFormat.forPattern("yyyy--dd MM")));
    }

    public void testConstructor_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight();
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight();
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_3_oe() throws Throwable {
        DateMidnight test = new DateMidnight();
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.getYear());
    }

    public void testConstructor_4_oe() throws Throwable {
        DateMidnight test = new DateMidnight();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_5_oe() throws Throwable {
        DateMidnight test = new DateMidnight();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_DateTimeZone_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_DateTimeZone_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(PARIS);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_PARIS,test.getMillis());
    }

    public void testConstructor_nullDateTimeZone_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight((DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullDateTimeZone_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight((DateTimeZone) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_Chronology_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_Chronology_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_nullChronology_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Chronology) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullChronology_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Chronology) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_long1_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long1_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        // removed other assertion
        assertEquals(TEST_TIME1_LONDON,test.getMillis());
    }

    public void testConstructor_long2_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long2_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME2_UTC);
        // removed other assertion
        assertEquals(TEST_TIME2_LONDON,test.getMillis());
    }

    public void testConstructor_long1_DateTimeZone_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_long1_DateTimeZone_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        // removed other assertion
        assertEquals(TEST_TIME1_PARIS,test.getMillis());
    }

    public void testConstructor_long2_DateTimeZone_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME2_UTC, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_long2_DateTimeZone_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME2_UTC, PARIS);
        // removed other assertion
        assertEquals(TEST_TIME2_PARIS,test.getMillis());
    }

    public void testConstructor_long_nullDateTimeZone_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, (DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long_nullDateTimeZone_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, (DateTimeZone) null);
        // removed other assertion
        assertEquals(TEST_TIME1_LONDON,test.getMillis());
    }

    public void testConstructor_long1_Chronology_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long1_Chronology_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME1_LONDON,test.getMillis());
    }

    public void testConstructor_long2_Chronology_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME2_UTC, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long2_Chronology_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME2_UTC, GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME2_LONDON,test.getMillis());
    }

    public void testConstructor_long_nullChronology_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, (Chronology) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long_nullChronology_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, (Chronology) null);
        // removed other assertion
        assertEquals(TEST_TIME1_LONDON,test.getMillis());
    }

    public void testConstructor_Object_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_Object_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date);
        // removed other assertion
        assertEquals(TEST_TIME1_LONDON,test.getMillis());
    }

    public void testConstructor_nullObject_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Object) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullObject_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Object) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_Object_DateTimeZone_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_Object_DateTimeZone_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, PARIS);
        // removed other assertion
        assertEquals(TEST_TIME1_PARIS,test.getMillis());
    }

    public void testConstructor_nullObject_DateTimeZone_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Object) null, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_nullObject_DateTimeZone_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Object) null, PARIS);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_PARIS,test.getMillis());
    }

    public void testConstructor_Object_nullDateTimeZone_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, (DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_Object_nullDateTimeZone_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, (DateTimeZone) null);
        // removed other assertion
        assertEquals(TEST_TIME1_LONDON,test.getMillis());
    }

    public void testConstructor_nullObject_nullDateTimeZone_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Object) null, (DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullObject_nullDateTimeZone_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Object) null, (DateTimeZone) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_Object_Chronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_Object_Chronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME1_LONDON,test.getMillis());
    }

    public void testConstructor_nullObject_Chronology_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Object) null, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullObject_Chronology_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Object) null, GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_Object_nullChronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, (Chronology) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_Object_nullChronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, (Chronology) null);
        // removed other assertion
        assertEquals(TEST_TIME1_LONDON,test.getMillis());
    }

    public void testConstructor_nullObject_nullChronology_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Object) null, (Chronology) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullObject_nullChronology_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight((Object) null, (Chronology) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_int_int_int_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_int_int_int_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9);
        // removed other assertion
        assertEquals(LONDON,test.getZone());
    }

    public void testConstructor_int_int_int_3_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9);
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_int_int_int_4_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.getYear());
    }

    public void testConstructor_int_int_int_5_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_6_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_DateTimeZone_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_int_int_int_DateTimeZone_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, PARIS);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_PARIS,test.getMillis());
    }

    public void testConstructor_int_int_int_DateTimeZone_3_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.getYear());
    }

    public void testConstructor_int_int_int_DateTimeZone_4_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_DateTimeZone_5_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_nullDateTimeZone_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_int_int_int_nullDateTimeZone_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (DateTimeZone) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_int_int_int_nullDateTimeZone_3_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.getYear());
    }

    public void testConstructor_int_int_int_nullDateTimeZone_4_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_nullDateTimeZone_5_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_Chronology_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_int_int_int_Chronology_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_int_int_int_Chronology_3_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, GregorianChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.getYear());
    }

    public void testConstructor_int_int_int_Chronology_4_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, GregorianChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_Chronology_5_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, GregorianChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getDayOfMonth());
    }

    public void testConstructor_int_int_int_nullChronology_1_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (Chronology) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_int_int_int_nullChronology_2_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (Chronology) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
    }

    public void testConstructor_int_int_int_nullChronology_3_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.getYear());
    }

    public void testConstructor_int_int_int_nullChronology_4_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.getMonthOfYear());
    }

    public void testConstructor_int_int_int_nullChronology_5_oe() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getDayOfMonth());
    }

}
