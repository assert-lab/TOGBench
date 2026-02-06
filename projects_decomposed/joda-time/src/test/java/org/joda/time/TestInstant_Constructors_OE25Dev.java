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
package org.joda.time;

import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.MockZeroNullIntegerConverter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for Instant.
 *
 * @author Stephen Colebourne
 */
public class TestInstant_Constructors_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    
    // 1970-06-09
    private long TEST_TIME_NOW =
            (31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
    // 1970-04-05
    private long TEST_TIME1 =
        (31L + 28L + 31L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
        + 12L * DateTimeConstants.MILLIS_PER_HOUR
        + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    // 1971-05-06
    private long TEST_TIME2 =
        (365L + 31L + 28L + 31L + 30L + 7L -1L) * DateTimeConstants.MILLIS_PER_DAY
        + 14L * DateTimeConstants.MILLIS_PER_HOUR
        + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    private DateTimeZone zone = null;
    private Locale locale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestInstant_Constructors_OE25Dev.class);
    }

    public TestInstant_Constructors_OE25Dev(String name) {
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
    /**
     * Test EPOCH
     */

    /**
     * Test now()
     */

    //-----------------------------------------------------------------------
    /**
     * Test ofEpochMilli() and ofEpochSecond()
     */
    
    public void test_ofEpochSecond_overflow() throws Throwable {
        try {
            Instant.ofEpochSecond(Long.MAX_VALUE);
            fail();
        } catch (ArithmeticException ex) {}
    }
    
    public void test_ofEpochSecond_underflow() throws Throwable {
        try {
            Instant.ofEpochSecond(Long.MIN_VALUE);
            fail();
        } catch (ArithmeticException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    /**
     * Test constructor ()
     */

    //-----------------------------------------------------------------------
    /**
     * Test constructor (long)
     */

    /**
     * Test constructor (long)
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
            new Instant(new Object());
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
            Instant test = new Instant(new Integer(0));
            assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
            assertEquals(0L,test.getMillis());
        } finally {
            ConverterManager.getInstance().removeInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
        }
    }

    public void test_epoch_1_oe() throws Throwable {
        Instant test = Instant.EPOCH;
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void test_epoch_2_oe() throws Throwable {
        Instant test = Instant.EPOCH;
        // removed other assertion
        assertEquals(0L,test.getMillis());
    }

    public void test_now_1_oe() throws Throwable {
        Instant test = Instant.now();
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void test_now_2_oe() throws Throwable {
        Instant test = Instant.now();
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void test_ofEpochMilli_1_oe() throws Throwable {
        Instant test = Instant.ofEpochMilli(TEST_TIME1);
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void test_ofEpochMilli_2_oe() throws Throwable {
        Instant test = Instant.ofEpochMilli(TEST_TIME1);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void test_ofEpochSecond_1_oe() throws Throwable {
        Instant test = Instant.ofEpochSecond(TEST_TIME1 / 1000);
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void test_ofEpochSecond_2_oe() throws Throwable {
        Instant test = Instant.ofEpochSecond(TEST_TIME1 / 1000);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void test_ofEpochSecond_zero_1_oe() throws Throwable {
        Instant test = Instant.ofEpochSecond(0);
        assertEquals(0,test.getMillis());
    }

    public void testParse_noFormatter_1_oe() throws Throwable {
        assertEquals(new DateTime(2010,6,30,0,20,ISOChronology.getInstance(LONDON)).toInstant(),Instant.parse("2010-06-30T01:20+02:00"));
    }

    public void testParse_noFormatter_2_oe() throws Throwable {
        // removed other assertion
        assertEquals(new DateTime(2010,1,2,14,50,ISOChronology.getInstance(LONDON)).toInstant(),Instant.parse("2010-002T14:50"));
    }

    public void testParse_formatter_1_oe() throws Throwable {
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy--dd MM HH").withChronology(ISOChronology.getInstance(PARIS));
        assertEquals(new DateTime(2010,6,30,13,0,ISOChronology.getInstance(PARIS)).toInstant(),Instant.parse("2010--30 06 13",f));
    }

    public void testConstructor_1_oe() throws Throwable {
        Instant test = new Instant();
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_2_oe() throws Throwable {
        Instant test = new Instant();
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testConstructor_long1_1_oe() throws Throwable {
        Instant test = new Instant(TEST_TIME1);
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_long1_2_oe() throws Throwable {
        Instant test = new Instant(TEST_TIME1);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_long2_1_oe() throws Throwable {
        Instant test = new Instant(TEST_TIME2);
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_long2_2_oe() throws Throwable {
        Instant test = new Instant(TEST_TIME2);
        // removed other assertion
        assertEquals(TEST_TIME2,test.getMillis());
    }

    public void testConstructor_Object_1_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        Instant test = new Instant(date);
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_Object_2_oe() throws Throwable {
        Date date = new Date(TEST_TIME1);
        Instant test = new Instant(date);
        // removed other assertion
        assertEquals(TEST_TIME1,test.getMillis());
    }

    public void testConstructor_nullObject_1_oe() throws Throwable {
        Instant test = new Instant((Object) null);
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void testConstructor_nullObject_2_oe() throws Throwable {
        Instant test = new Instant((Object) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

}
