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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.base.BasePeriod;

/**
 * This class is a Junit unit test for MutableDuration.
 *
 * @author Stephen Colebourne
 */
public class TestMutablePeriod_Basics_OE25Dev extends TestCase {
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
    
    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMutablePeriod_Basics_OE25Dev_OE25Dev.class);
    }

    public TestMutablePeriod_Basics_OE25Dev(String name) {
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
    
    class MockMutablePeriod extends BasePeriod {
        public MockMutablePeriod(long value) {
            super(value, null, null);
        }
    }

    //-----------------------------------------------------------------------

//    //-----------------------------------------------------------------------
//    public void testAddTo1() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, 1);
//        expected = ISOChronology.getInstance().months().add(expected, 2);
//        expected = ISOChronology.getInstance().weeks().add(expected, 3);
//        expected = ISOChronology.getInstance().days().add(expected, 4);
//        expected = ISOChronology.getInstance().hours().add(expected, 5);
//        expected = ISOChronology.getInstance().minutes().add(expected, 6);
//        expected = ISOChronology.getInstance().seconds().add(expected, 7);
//        expected = ISOChronology.getInstance().millis().add(expected, 8);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
//        long added = test.addTo(TEST_TIME_NOW, 1);
//        assertEquals(expected,added);
//    }
//    
//    public void testAddTo2() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, -2);
//        expected = ISOChronology.getInstance().months().add(expected, -4);
//        expected = ISOChronology.getInstance().weeks().add(expected, -6);
//        expected = ISOChronology.getInstance().days().add(expected, -8);
//        expected = ISOChronology.getInstance().hours().add(expected, -10);
//        expected = ISOChronology.getInstance().minutes().add(expected, -12);
//        expected = ISOChronology.getInstance().seconds().add(expected, -14);
//        expected = ISOChronology.getInstance().millis().add(expected, -16);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
//        long added = test.addTo(TEST_TIME_NOW, -2);
//        assertEquals(expected,added);
//    }
//    
//    public void testAddTo3() {
//        long expected = TEST_TIME_NOW;
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
//        long added = test.addTo(TEST_TIME_NOW, 0);
//        assertEquals(expected,added);
//    }
//    
//    public void testAddTo4() {
//        long expected = TEST_TIME_NOW + 100L;
//        MutablePeriod test = new MutablePeriod(100L);
//        long added = test.addTo(TEST_TIME_NOW, 1);
//        assertEquals(expected,added);
//    }
//    
//    //-----------------------------------------------------------------------
//    public void testAddToWithChronology1() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, 1);
//        expected = ISOChronology.getInstance().months().add(expected, 2);
//        expected = ISOChronology.getInstance().weeks().add(expected, 3);
//        expected = ISOChronology.getInstance().days().add(expected, 4);
//        expected = ISOChronology.getInstance().hours().add(expected, 5);
//        expected = ISOChronology.getInstance().minutes().add(expected, 6);
//        expected = ISOChronology.getInstance().seconds().add(expected, 7);
//        expected = ISOChronology.getInstance().millis().add(expected, 8);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
//        long added = test.addTo(TEST_TIME_NOW, 1, ISOChronology.getInstance());
//        assertEquals(expected,added);
//    }
//    
//    public void testAddToWithChronology2() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstanceUTC().years().add(expected, -2);
//        expected = ISOChronology.getInstanceUTC().months().add(expected, -4);
//        expected = ISOChronology.getInstanceUTC().weeks().add(expected, -6);
//        expected = ISOChronology.getInstanceUTC().days().add(expected, -8);
//        expected = ISOChronology.getInstanceUTC().hours().add(expected, -10);
//        expected = ISOChronology.getInstanceUTC().minutes().add(expected, -12);
//        expected = ISOChronology.getInstanceUTC().seconds().add(expected, -14);
//        expected = ISOChronology.getInstanceUTC().millis().add(expected, -16);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        long added = test.addTo(TEST_TIME_NOW, -2, ISOChronology.getInstanceUTC());  // chrono specified so use it
//        assertEquals(expected,added);
//    }
//    
//    public void testAddToWithChronology3() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, -2);
//        expected = ISOChronology.getInstance().months().add(expected, -4);
//        expected = ISOChronology.getInstance().weeks().add(expected, -6);
//        expected = ISOChronology.getInstance().days().add(expected, -8);
//        expected = ISOChronology.getInstance().hours().add(expected, -10);
//        expected = ISOChronology.getInstance().minutes().add(expected, -12);
//        expected = ISOChronology.getInstance().seconds().add(expected, -14);
//        expected = ISOChronology.getInstance().millis().add(expected, -16);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        long added = test.addTo(TEST_TIME_NOW, -2, null);  // no chrono so use default
//        assertEquals(expected,added);
//    }
//    
//    //-----------------------------------------------------------------------
//    public void testAddToRI1() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, 1);
//        expected = ISOChronology.getInstance().months().add(expected, 2);
//        expected = ISOChronology.getInstance().weeks().add(expected, 3);
//        expected = ISOChronology.getInstance().days().add(expected, 4);
//        expected = ISOChronology.getInstance().hours().add(expected, 5);
//        expected = ISOChronology.getInstance().minutes().add(expected, 6);
//        expected = ISOChronology.getInstance().seconds().add(expected, 7);
//        expected = ISOChronology.getInstance().millis().add(expected, 8);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
//        DateTime added = test.addTo(new Instant(), 1);  // Instant has no time zone, use default
//        assertEquals(expected,added.getMillis());
//        assertEquals(ISOChronology.getInstance(),added.getChronology());
//    }
//    
//    public void testAddToRI2() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, -2);
//        expected = ISOChronology.getInstance().months().add(expected, -4);
//        expected = ISOChronology.getInstance().weeks().add(expected, -6);
//        expected = ISOChronology.getInstance().days().add(expected, -8);
//        expected = ISOChronology.getInstance().hours().add(expected, -10);
//        expected = ISOChronology.getInstance().minutes().add(expected, -12);
//        expected = ISOChronology.getInstance().seconds().add(expected, -14);
//        expected = ISOChronology.getInstance().millis().add(expected, -16);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        DateTime added = test.addTo(new Instant(), -2);  // Instant has no time zone, use default
//        assertEquals(expected,added.getMillis());
//        assertEquals(ISOChronology.getInstance(),added.getChronology());
//    }
//    
//    public void testAddToRI3() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstanceUTC().years().add(expected, -2);
//        expected = ISOChronology.getInstanceUTC().months().add(expected, -4);
//        expected = ISOChronology.getInstanceUTC().weeks().add(expected, -6);
//        expected = ISOChronology.getInstanceUTC().days().add(expected, -8);
//        expected = ISOChronology.getInstanceUTC().hours().add(expected, -10);
//        expected = ISOChronology.getInstanceUTC().minutes().add(expected, -12);
//        expected = ISOChronology.getInstanceUTC().seconds().add(expected, -14);
//        expected = ISOChronology.getInstanceUTC().millis().add(expected, -16);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        DateTime added = test.addTo(new DateTime(ISOChronology.getInstanceUTC()), -2);  // DateTime has UTC time zone
//        assertEquals(expected,added.getMillis());
//        assertEquals(ISOChronology.getInstanceUTC(),added.getChronology());
//    }
//    
//    public void testAddToRI4() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance(PARIS).years().add(expected, -2);
//        expected = ISOChronology.getInstance(PARIS).months().add(expected, -4);
//        expected = ISOChronology.getInstance(PARIS).weeks().add(expected, -6);
//        expected = ISOChronology.getInstance(PARIS).days().add(expected, -8);
//        expected = ISOChronology.getInstance(PARIS).hours().add(expected, -10);
//        expected = ISOChronology.getInstance(PARIS).minutes().add(expected, -12);
//        expected = ISOChronology.getInstance(PARIS).seconds().add(expected, -14);
//        expected = ISOChronology.getInstance(PARIS).millis().add(expected, -16);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        DateTime added = test.addTo(new DateTime(PARIS), -2);  // DateTime has PARIS time zone
//        assertEquals(expected,added.getMillis());
//        assertEquals(ISOChronology.getInstance(PARIS),added.getChronology());
//    }
//    
//    public void testAddToRI5() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, -2);
//        expected = ISOChronology.getInstance().months().add(expected, -4);
//        expected = ISOChronology.getInstance().weeks().add(expected, -6);
//        expected = ISOChronology.getInstance().days().add(expected, -8);
//        expected = ISOChronology.getInstance().hours().add(expected, -10);
//        expected = ISOChronology.getInstance().minutes().add(expected, -12);
//        expected = ISOChronology.getInstance().seconds().add(expected, -14);
//        expected = ISOChronology.getInstance().millis().add(expected, -16);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        DateTime added = test.addTo(null, -2);  // null has no time zone, use default
//        assertEquals(expected,added.getMillis());
//        assertEquals(ISOChronology.getInstance(),added.getChronology());
//    }
//    
//    //-----------------------------------------------------------------------
//    public void testAddIntoRWI1() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, 1);
//        expected = ISOChronology.getInstance().months().add(expected, 2);
//        expected = ISOChronology.getInstance().weeks().add(expected, 3);
//        expected = ISOChronology.getInstance().days().add(expected, 4);
//        expected = ISOChronology.getInstance().hours().add(expected, 5);
//        expected = ISOChronology.getInstance().minutes().add(expected, 6);
//        expected = ISOChronology.getInstance().seconds().add(expected, 7);
//        expected = ISOChronology.getInstance().millis().add(expected, 8);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
//        MutableDateTime mdt = new MutableDateTime();
//        test.addInto(mdt, 1);
//        assertEquals(expected,mdt.getMillis());
//    }
//    
//    public void testAddIntoRWI2() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, -2);
//        expected = ISOChronology.getInstance().months().add(expected, -4);
//        expected = ISOChronology.getInstance().weeks().add(expected, -6);
//        expected = ISOChronology.getInstance().days().add(expected, -8);
//        expected = ISOChronology.getInstance().hours().add(expected, -10);
//        expected = ISOChronology.getInstance().minutes().add(expected, -12);
//        expected = ISOChronology.getInstance().seconds().add(expected, -14);
//        expected = ISOChronology.getInstance().millis().add(expected, -16);
//        
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        MutableDateTime mdt = new MutableDateTime();
//        test.addInto(mdt, -2);  // MutableDateTime has a chronology, use it
//        assertEquals(expected,mdt.getMillis());
//    }
//    
//    public void testAddIntoRWI3() {
//        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
//        try {
//            test.addInto(null, 1);
//            fail();
//        } catch (IllegalArgumentException ex) {}
//    }
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
//    public void testToDurationMillisFrom() {
//        MutablePeriod test = new MutablePeriod(123L);
//        assertEquals(123L,test.toDurationMillisFrom(0L,null));
//    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    
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

    public void testGetPeriodType_1_oe() {
        MutablePeriod test = new MutablePeriod();
        assertEquals(PeriodType.standard(),test.getPeriodType());
    }

    public void testGetMethods_1_oe() {
        MutablePeriod test = new MutablePeriod();
        assertEquals(0,test.getYears());
    }

    public void testGetMethods_2_oe() {
        MutablePeriod test = new MutablePeriod();
        // removed other assertion
        assertEquals(0,test.getMonths());
    }

    public void testGetMethods_3_oe() {
        MutablePeriod test = new MutablePeriod();
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.getDays());
    }

    public void testGetMethods_4_oe() {
        MutablePeriod test = new MutablePeriod();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.getWeeks());
    }

    public void testGetMethods_5_oe() {
        MutablePeriod test = new MutablePeriod();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.getDays());
    }

    public void testGetMethods_6_oe() {
        MutablePeriod test = new MutablePeriod();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.getHours());
    }

    public void testGetMethods_7_oe() {
        MutablePeriod test = new MutablePeriod();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.getMinutes());
    }

    public void testGetMethods_8_oe() {
        MutablePeriod test = new MutablePeriod();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.getSeconds());
    }

    public void testGetMethods_9_oe() {
        MutablePeriod test = new MutablePeriod();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.getMillis());
    }

    public void testEqualsHashCode_1_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        assertEquals(true,test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        assertEquals(true,test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutablePeriod test3 = new MutablePeriod(321L);
        assertEquals(false,test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutablePeriod test3 = new MutablePeriod(321L);
        // removed other assertion
        assertEquals(false,test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutablePeriod test3 = new MutablePeriod(321L);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutablePeriod test3 = new MutablePeriod(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutablePeriod test3 = new MutablePeriod(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.hashCode()== test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutablePeriod test3 = new MutablePeriod(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test2.hashCode()== test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutablePeriod test3 = new MutablePeriod(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test1.equals("Hello"));
    }

    public void testEqualsHashCode_15_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutablePeriod test3 = new MutablePeriod(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,test1.equals(new MockMutablePeriod(123L)));
    }

    public void testEqualsHashCode_16_oe() {
        MutablePeriod test1 = new MutablePeriod(123L);
        MutablePeriod test2 = new MutablePeriod(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutablePeriod test3 = new MutablePeriod(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.equals(new Period(123L,PeriodType.dayTime())));
    }

    public void testSerialization_1_oe() throws Exception {
        MutablePeriod test = new MutablePeriod(123L);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        MutablePeriod result = (MutablePeriod) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

    public void testToString_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals("P1Y2M3W4DT5H6M7.008S",test.toString());
    }

    public void testToString_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        // removed other assertion
        
        test = new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("PT0S",test.toString());
    }

    public void testToString_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        // removed other assertion
        
        test = new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 0);
        // removed other assertion
        
        test = new MutablePeriod(12345L);
        assertEquals("PT12.345S",test.toString());
    }

    public void testToPeriod_1_oe() {
        MutablePeriod test = new MutablePeriod(123L);
        Period result = test.toPeriod();
        assertEquals(test,result);
    }

    public void testToMutablePeriod_1_oe() {
        MutablePeriod test = new MutablePeriod(123L);
        MutablePeriod result = test.toMutablePeriod();
        assertEquals(test,result);
    }

    public void testToDurationFrom_1_oe() {
        MutablePeriod test = new MutablePeriod(123L);
        assertEquals(new Duration(123L),test.toDurationFrom(new Instant(0L)));
    }

    public void testCopy_1_oe() {
        MutablePeriod test = new MutablePeriod(123L);
        MutablePeriod copy = test.copy();
        assertEquals(test.getPeriodType(),copy.getPeriodType());
    }

    public void testCopy_2_oe() {
        MutablePeriod test = new MutablePeriod(123L);
        MutablePeriod copy = test.copy();
        // removed other assertion
        assertEquals(test,copy);
    }

    public void testClone_1_oe() {
        MutablePeriod test = new MutablePeriod(123L);
        MutablePeriod copy = (MutablePeriod) test.clone();
        assertEquals(test.getPeriodType(),copy.getPeriodType());
    }

    public void testClone_2_oe() {
        MutablePeriod test = new MutablePeriod(123L);
        MutablePeriod copy = (MutablePeriod) test.clone();
        // removed other assertion
        assertEquals(test,copy);
    }

}
