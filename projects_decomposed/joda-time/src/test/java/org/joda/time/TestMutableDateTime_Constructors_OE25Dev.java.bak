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

import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.MockZeroNullIntegerConverter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for MutableDateTime.
 *
 * @author Stephen Colebourne
 */
public class TestMutableDateTime_Constructors_OE25Dev extends TestCase {
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
    private long TEST_TIME_NOW =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
    // 2002-04-05
    private long TEST_TIME1 =
            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 12L * DateTimeConstants.MILLIS_PER_HOUR
            + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    // 2003-05-06
    private long TEST_TIME2 =
            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 14L * DateTimeConstants.MILLIS_PER_HOUR
            + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    private DateTimeZone zone = null;
    private Locale locale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMutableDateTime_Constructors_OE25Dev.class);
    }

    public TestMutableDateTime_Constructors_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        locale = Locale.getDefault();
        DateTimeZone.setDefault(LONDON);
        java.util.TimeZone.setDefault(LONDON.toTimeZone());
        Locale.setDefault(Locale.UK);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        java.util.TimeZone.setDefault(zone.toTimeZone());
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
            MutableDateTime.now((DateTimeZone) null);
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
            MutableDateTime.now((Chronology) null);
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
            new MutableDateTime(new Object());
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
            MutableDateTime test = new MutableDateTime(new Integer(0));
            assertEquals(ISOChronology.getInstance(),test.getChronology());
            assertEquals(0L,test.getMillis());
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
            new MutableDateTime(new Object(), PARIS);
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
            MutableDateTime test = new MutableDateTime(new Integer(0), GregorianChronology.getInstance());
            assertEquals(ISOChronology.getInstance(),test.getChronology());
            assertEquals(0L,test.getMillis());
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
            new MutableDateTime(new Object(), GregorianChronology.getInstance());
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
            MutableDateTime test = new MutableDateTime(new Integer(0), GregorianChronology.getInstance());
            assertEquals(ISOChronology.getInstance(),test.getChronology());
            assertEquals(0L,test.getMillis());
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
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW).toString());
    }

    public void testTest_2_oe() {
        // removed other assertion
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1).toString());
    }

    public void testTest_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2).toString());
    }

    public void test_now_1_oe() throws Throwable {
        MutableDateTime test = MutableDateTime.now();
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void test_now_2_oe() throws Throwable {
        MutableDateTime test = MutableDateTime.now();
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void test_now_DateTimeZone_1_oe() throws Throwable {
        MutableDateTime test = MutableDateTime.now(PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void test_now_DateTimeZone_2_oe() throws Throwable {
        MutableDateTime test = MutableDateTime.now(PARIS);
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void test_now_Chronology_1_oe() throws Throwable {
        MutableDateTime test = MutableDateTime.now(GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void test_now_Chronology_2_oe() throws Throwable {
        MutableDateTime test = MutableDateTime.now(GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testParse_noFormatter_1_oe() throws Throwable {
        assertEquals(new MutableDateTime(2010,6,30,1,20,0,0,ISOChronology.getInstance(DateTimeZone.forOffsetHours(2))),MutableDateTime.parse("2010-06-30T01:20+02:00"));
    }

    public void testParse_noFormatter_2_oe() throws Throwable {
        // removed other assertion
        assertEquals(new MutableDateTime(2010,1,2,14,50,0,0,ISOChronology.getInstance(LONDON)),MutableDateTime.parse("2010-002T14:50"));
    }

    public void testParse_formatter_1_oe() throws Throwable {
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy--dd MM HH").withChronology(ISOChronology.getInstance(PARIS));
        assertEquals(new MutableDateTime(2010,6,30,13,0,0,0,ISOChronology.getInstance(PARIS)),MutableDateTime.parse("2010--30 06 13",f));
    }

    public void testConstructor_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime();
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_DateTimeZone_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_DateTimeZone_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(PARIS);
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_nullDateTimeZone_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullDateTimeZone_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((DateTimeZone) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_Chronology_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_Chronology_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_nullChronology_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Chronology) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullChronology_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Chronology) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_long1_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long1_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_long2_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME2);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long2_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(TEST_TIME2,test.getMillis());
    }

    public void testConstructor_long1_DateTimeZone_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_long1_DateTimeZone_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_long2_DateTimeZone_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME2, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_long2_DateTimeZone_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME2, PARIS);
        // removed other assertion
        assertEquals(TEST_TIME2,test.getMillis());
    }

    public void testConstructor_long_nullDateTimeZone_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, (DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long_nullDateTimeZone_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, (DateTimeZone) null);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_long1_Chronology_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long1_Chronology_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_long2_Chronology_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long2_Chronology_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME2,test.getMillis());
    }

    public void testConstructor_long_nullChronology_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, (Chronology) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_long_nullChronology_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, (Chronology) null);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_Object_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MutableDateTime test = new MutableDateTime(date);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_Object_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MutableDateTime test = new MutableDateTime(date);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_nullObject_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Object) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullObject_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Object) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_Object_DateTimeZone_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MutableDateTime test = new MutableDateTime(date, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_Object_DateTimeZone_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MutableDateTime test = new MutableDateTime(date, PARIS);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_nullObject_DateTimeZone_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Object) null, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_nullObject_DateTimeZone_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Object) null, PARIS);
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_Object_nullDateTimeZone_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MutableDateTime test = new MutableDateTime(date, (DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_Object_nullDateTimeZone_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MutableDateTime test = new MutableDateTime(date, (DateTimeZone) null);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_nullObject_nullDateTimeZone_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Object) null, (DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullObject_nullDateTimeZone_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Object) null, (DateTimeZone) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_Object_Chronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MutableDateTime test = new MutableDateTime(date, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_Object_Chronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MutableDateTime test = new MutableDateTime(date, GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_nullObject_Chronology_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Object) null, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullObject_Chronology_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Object) null, GregorianChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_Object_nullChronology_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MutableDateTime test = new MutableDateTime(date, (Chronology) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_Object_nullChronology_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        MutableDateTime test = new MutableDateTime(date, (Chronology) null);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_nullObject_nullChronology_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Object) null, (Chronology) null);
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_nullObject_nullChronology_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime((Object) null, (Chronology) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_int_int_int_int_int_int_int_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 1, 0, 0, 0);  // +01:00
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_int_int_int_int_int_int_int_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 1, 0, 0, 0);  // +01:00
        // removed other assertion
        assertEquals(LONDON,test.getZone());
    }

    public void testConstructor_int_int_int_int_int_int_int_3_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 1, 0, 0, 0);  // +01:00
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_int_int_int_int_int_int_int_DateTimeZone_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 2, 0, 0, 0, PARIS);  // +02:00
        assertEquals(ISOChronology.getInstance(PARIS),test.getChronology());
    }

    public void testConstructor_int_int_int_int_int_int_int_DateTimeZone_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 2, 0, 0, 0, PARIS);  // +02:00
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_int_int_int_int_int_int_int_nullDateTimeZone_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 1, 0, 0, 0, (DateTimeZone) null);  // +01:00
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_int_int_int_int_int_int_int_nullDateTimeZone_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 1, 0, 0, 0, (DateTimeZone) null);  // +01:00
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_int_int_int_int_int_int_int_Chronology_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 1, 0, 0, 0, GregorianChronology.getInstance());  // +01:00
        assertEquals(GregorianChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_int_int_int_int_int_int_int_Chronology_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 1, 0, 0, 0, GregorianChronology.getInstance());  // +01:00
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_int_int_int_int_int_int_int_nullChronology_1_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 1, 0, 0, 0, (Chronology) null);  // +01:00
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testConstructor_int_int_int_int_int_int_int_nullChronology_2_oe() throws Throwable {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 1, 0, 0, 0, (Chronology) null);  // +01:00
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

}
