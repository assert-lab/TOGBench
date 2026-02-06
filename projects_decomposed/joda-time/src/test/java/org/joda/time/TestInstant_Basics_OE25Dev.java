/*
 *  Copyright 2001-2009 Stephen Colebourne
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
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.base.AbstractInstant;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;

/**
 * This class is a Junit unit test for Instant.
 *
 * @author Stephen Colebourne
 */
public class TestInstant_Basics_OE25Dev extends TestCase {
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
        return new TestSuite(TestInstant_Basics_OE25Dev_OE25Dev.class);
    }

    public TestInstant_Basics_OE25Dev(String name) {
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
    
    class MockInstant extends AbstractInstant {
        @Override
        public String toString() {
            return null;
        }
        public long getMillis() {
            return TEST_TIME1;
        }
        public Chronology getChronology() {
            return ISOChronology.getInstanceUTC();
        }
    }
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------    
    
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

    public void testGet_DateTimeFieldType_1_oe() {
        Instant test = new Instant();  // 2002-06-09
        assertEquals(1,test.get(DateTimeFieldType.era()));
    }

    public void testGet_DateTimeFieldType_2_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        assertEquals(20,test.get(DateTimeFieldType.centuryOfEra()));
    }

    public void testGet_DateTimeFieldType_3_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.get(DateTimeFieldType.yearOfCentury()));
    }

    public void testGet_DateTimeFieldType_4_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.get(DateTimeFieldType.yearOfEra()));
    }

    public void testGet_DateTimeFieldType_5_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.get(DateTimeFieldType.year()));
    }

    public void testGet_DateTimeFieldType_6_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.get(DateTimeFieldType.monthOfYear()));
    }

    public void testGet_DateTimeFieldType_7_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.get(DateTimeFieldType.dayOfMonth()));
    }

    public void testGet_DateTimeFieldType_8_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.get(DateTimeFieldType.weekyear()));
    }

    public void testGet_DateTimeFieldType_9_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(23,test.get(DateTimeFieldType.weekOfWeekyear()));
    }

    public void testGet_DateTimeFieldType_10_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7,test.get(DateTimeFieldType.dayOfWeek()));
    }

    public void testGet_DateTimeFieldType_11_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(160,test.get(DateTimeFieldType.dayOfYear()));
    }

    public void testGet_DateTimeFieldType_12_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testGet_DateTimeFieldType_13_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(DateTimeFieldType.hourOfHalfday()));// UTC zone assertEquals(24,test.get(DateTimeFieldType.clockhourOfDay()));// UTC zone assertEquals(12,test.get(DateTimeFieldType.clockhourOfHalfday()));// UTC zone assertEquals(0,test.get(DateTimeFieldType.hourOfDay()));// UTC zone assertEquals(0,test.get(DateTimeFieldType.minuteOfHour()));
    }

    public void testGet_DateTimeFieldType_14_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(DateTimeFieldType.minuteOfDay()));
    }

    public void testGet_DateTimeFieldType_15_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(DateTimeFieldType.secondOfMinute()));
    }

    public void testGet_DateTimeFieldType_16_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(DateTimeFieldType.secondOfDay()));
    }

    public void testGet_DateTimeFieldType_17_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(DateTimeFieldType.millisOfSecond()));
    }

    public void testGet_DateTimeFieldType_18_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(DateTimeFieldType.millisOfDay()));
    }

    public void testGet_DateTimeField_1_oe() {
        Instant test = new Instant();  // 2002-06-09
        assertEquals(1,test.get(ISOChronology.getInstance().era()));
    }

    public void testGet_DateTimeField_2_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        assertEquals(20,test.get(ISOChronology.getInstance().centuryOfEra()));
    }

    public void testGet_DateTimeField_3_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.get(ISOChronology.getInstance().yearOfCentury()));
    }

    public void testGet_DateTimeField_4_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.get(ISOChronology.getInstance().yearOfEra()));
    }

    public void testGet_DateTimeField_5_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.get(ISOChronology.getInstance().year()));
    }

    public void testGet_DateTimeField_6_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.get(ISOChronology.getInstance().monthOfYear()));
    }

    public void testGet_DateTimeField_7_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.get(ISOChronology.getInstance().dayOfMonth()));
    }

    public void testGet_DateTimeField_8_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.get(ISOChronology.getInstance().weekyear()));
    }

    public void testGet_DateTimeField_9_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(23,test.get(ISOChronology.getInstance().weekOfWeekyear()));
    }

    public void testGet_DateTimeField_10_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7,test.get(ISOChronology.getInstance().dayOfWeek()));
    }

    public void testGet_DateTimeField_11_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(160,test.get(ISOChronology.getInstance().dayOfYear()));
    }

    public void testGet_DateTimeField_12_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(ISOChronology.getInstance().halfdayOfDay()));
    }

    public void testGet_DateTimeField_13_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,test.get(ISOChronology.getInstance().hourOfHalfday()));
    }

    public void testGet_DateTimeField_14_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,test.get(ISOChronology.getInstance().clockhourOfDay()));
    }

    public void testGet_DateTimeField_15_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,test.get(ISOChronology.getInstance().clockhourOfHalfday()));
    }

    public void testGet_DateTimeField_16_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,test.get(ISOChronology.getInstance().hourOfDay()));
    }

    public void testGet_DateTimeField_17_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(ISOChronology.getInstance().minuteOfHour()));
    }

    public void testGet_DateTimeField_18_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60,test.get(ISOChronology.getInstance().minuteOfDay()));
    }

    public void testGet_DateTimeField_19_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(ISOChronology.getInstance().secondOfMinute()));
    }

    public void testGet_DateTimeField_20_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60 * 60,test.get(ISOChronology.getInstance().secondOfDay()));
    }

    public void testGet_DateTimeField_21_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test.get(ISOChronology.getInstance().millisOfSecond()));
    }

    public void testGet_DateTimeField_22_oe() {
        Instant test = new Instant();  // 2002-06-09
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60 * 60 * 1000,test.get(ISOChronology.getInstance().millisOfDay()));
    }

    public void testGetMethods_1_oe() {
        Instant test = new Instant();
        
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void testGetMethods_2_oe() {
        Instant test = new Instant();
        
        // removed other assertion
        assertEquals(DateTimeZone.UTC,test.getZone());
    }

    public void testGetMethods_3_oe() {
        Instant test = new Instant();
        
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testEqualsHashCode_1_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        assertEquals(true,test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        assertEquals(true,test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test3 = new Instant(TEST_TIME2);
        assertEquals(false,test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test3 = new Instant(TEST_TIME2);
        // removed other assertion
        assertEquals(false,test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test3 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test3 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test3 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.hashCode()== test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test3 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test2.hashCode()== test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test3 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test1.equals("Hello"));
    }

    public void testEqualsHashCode_15_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test3 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,test1.equals(new MockInstant()));
    }

    public void testEqualsHashCode_16_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test2 = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test3 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.equals(new DateTime(TEST_TIME1)));
    }

    public void testCompareTo_1_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        assertEquals(0,test1.compareTo(test1a));
    }

    public void testCompareTo_2_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        assertEquals(0,test1a.compareTo(test1));
    }

    public void testCompareTo_3_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(0,test1.compareTo(test1));
    }

    public void testCompareTo_4_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test1a.compareTo(test1a));
    }

    public void testCompareTo_5_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        assertEquals(-1,test1.compareTo(test2));
    }

    public void testCompareTo_6_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        assertEquals(+1,test2.compareTo(test1));
    }

    public void testCompareTo_7_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(-1,test1.compareTo(test3));
    }

    public void testCompareTo_8_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(+1,test3.compareTo(test1));
    }

    public void testCompareTo_9_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(0,test3.compareTo(test2));
    }

    public void testCompareTo_10_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(+1,test2.compareTo(new MockInstant()));
    }

    public void testCompareTo_11_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(0,test1.compareTo(new MockInstant()));
    }

    public void testIsEqual_long_1_oe() {
        assertEquals(false,new Instant(TEST_TIME1).isEqual(TEST_TIME2));
    }

    public void testIsEqual_long_2_oe() {
        // removed other assertion
        assertEquals(true,new Instant(TEST_TIME1).isEqual(TEST_TIME1));
    }

    public void testIsEqual_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME2).isEqual(TEST_TIME1));
    }

    public void testIsEqualNow_1_oe() {
        assertEquals(false,new Instant(TEST_TIME_NOW - 1).isEqualNow());
    }

    public void testIsEqualNow_2_oe() {
        // removed other assertion
        assertEquals(true,new Instant(TEST_TIME_NOW).isEqualNow());
    }

    public void testIsEqualNow_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME_NOW + 1).isEqualNow());
    }

    public void testIsEqual_RI_1_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        assertEquals(true,test1.isEqual(test1a));
    }

    public void testIsEqual_RI_2_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        assertEquals(true,test1a.isEqual(test1));
    }

    public void testIsEqual_RI_3_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.isEqual(test1));
    }

    public void testIsEqual_RI_4_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1a.isEqual(test1a));
    }

    public void testIsEqual_RI_5_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        assertEquals(false,test1.isEqual(test2));
    }

    public void testIsEqual_RI_6_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        assertEquals(false,test2.isEqual(test1));
    }

    public void testIsEqual_RI_7_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false,test1.isEqual(test3));
    }

    public void testIsEqual_RI_8_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(false,test3.isEqual(test1));
    }

    public void testIsEqual_RI_9_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(true,test3.isEqual(test2));
    }

    public void testIsEqual_RI_10_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test2.isEqual(new MockInstant()));
    }

    public void testIsEqual_RI_11_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,test1.isEqual(new MockInstant()));
    }

    public void testIsEqual_RI_12_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,new Instant(TEST_TIME_NOW + 1).isEqual(null));
    }

    public void testIsEqual_RI_13_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,new Instant(TEST_TIME_NOW).isEqual(null));
    }

    public void testIsEqual_RI_14_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME_NOW - 1).isEqual(null));
    }

    public void testIsBefore_long_1_oe() {
        assertEquals(true,new Instant(TEST_TIME1).isBefore(TEST_TIME2));
    }

    public void testIsBefore_long_2_oe() {
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME1).isBefore(TEST_TIME1));
    }

    public void testIsBefore_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME2).isBefore(TEST_TIME1));
    }

    public void testIsBeforeNow_1_oe() {
        assertEquals(true,new Instant(TEST_TIME_NOW - 1).isBeforeNow());
    }

    public void testIsBeforeNow_2_oe() {
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME_NOW).isBeforeNow());
    }

    public void testIsBeforeNow_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME_NOW + 1).isBeforeNow());
    }

    public void testIsBefore_RI_1_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        assertEquals(false,test1.isBefore(test1a));
    }

    public void testIsBefore_RI_2_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        assertEquals(false,test1a.isBefore(test1));
    }

    public void testIsBefore_RI_3_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.isBefore(test1));
    }

    public void testIsBefore_RI_4_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1a.isBefore(test1a));
    }

    public void testIsBefore_RI_5_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        assertEquals(true,test1.isBefore(test2));
    }

    public void testIsBefore_RI_6_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        assertEquals(false,test2.isBefore(test1));
    }

    public void testIsBefore_RI_7_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(true,test1.isBefore(test3));
    }

    public void testIsBefore_RI_8_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(false,test3.isBefore(test1));
    }

    public void testIsBefore_RI_9_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.isBefore(test2));
    }

    public void testIsBefore_RI_10_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test2.isBefore(new MockInstant()));
    }

    public void testIsBefore_RI_11_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false,test1.isBefore(new MockInstant()));
    }

    public void testIsBefore_RI_12_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,new Instant(TEST_TIME_NOW + 1).isBefore(null));
    }

    public void testIsBefore_RI_13_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME_NOW).isBefore(null));
    }

    public void testIsBefore_RI_14_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true,new Instant(TEST_TIME_NOW - 1).isBefore(null));
    }

    public void testIsAfter_long_1_oe() {
        assertEquals(false,new Instant(TEST_TIME1).isAfter(TEST_TIME2));
    }

    public void testIsAfter_long_2_oe() {
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME1).isAfter(TEST_TIME1));
    }

    public void testIsAfter_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true,new Instant(TEST_TIME2).isAfter(TEST_TIME1));
    }

    public void testIsAfterNow_1_oe() {
        assertEquals(false,new Instant(TEST_TIME_NOW - 1).isAfterNow());
    }

    public void testIsAfterNow_2_oe() {
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME_NOW).isAfterNow());
    }

    public void testIsAfterNow_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true,new Instant(TEST_TIME_NOW + 1).isAfterNow());
    }

    public void testIsAfter_RI_1_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        assertEquals(false,test1.isAfter(test1a));
    }

    public void testIsAfter_RI_2_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        assertEquals(false,test1a.isAfter(test1));
    }

    public void testIsAfter_RI_3_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.isAfter(test1));
    }

    public void testIsAfter_RI_4_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1a.isAfter(test1a));
    }

    public void testIsAfter_RI_5_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        assertEquals(false,test1.isAfter(test2));
    }

    public void testIsAfter_RI_6_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        assertEquals(true,test2.isAfter(test1));
    }

    public void testIsAfter_RI_7_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false,test1.isAfter(test3));
    }

    public void testIsAfter_RI_8_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(true,test3.isAfter(test1));
    }

    public void testIsAfter_RI_9_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.isAfter(test2));
    }

    public void testIsAfter_RI_10_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(true,test2.isAfter(new MockInstant()));
    }

    public void testIsAfter_RI_11_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false,test1.isAfter(new MockInstant()));
    }

    public void testIsAfter_RI_12_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(true,new Instant(TEST_TIME_NOW + 1).isAfter(null));
    }

    public void testIsAfter_RI_13_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME_NOW).isAfter(null));
    }

    public void testIsAfter_RI_14_oe() {
        Instant test1 = new Instant(TEST_TIME1);
        Instant test1a = new Instant(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Instant test2 = new Instant(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false,new Instant(TEST_TIME_NOW - 1).isAfter(null));
    }

    public void testSerialization_1_oe() throws Exception {
        Instant test = new Instant(TEST_TIME_NOW);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Instant result = (Instant) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

    public void testToString_1_oe() {
        Instant test = new Instant(TEST_TIME_NOW);
        assertEquals("2002-06-09T00:00:00.000Z",test.toString());
    }

    public void testToInstant_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.toInstant();
        assertSame(test,result);
    }

    public void testToDateTime_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime();
        assertEquals(TEST_TIME1,result.getMillis());
    }

    public void testToDateTime_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime();
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToDateTimeISO_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        assertSame(DateTime.class,result.getClass());
    }

    public void testToDateTimeISO_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        assertSame(ISOChronology.class,result.getChronology().getClass());
    }

    public void testToDateTimeISO_3_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTimeISO_4_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToDateTime_DateTimeZone_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime(LONDON);
        // removed other assertion
        assertEquals(ISOChronology.getInstance(LONDON),result.getChronology());
    }

    public void testToDateTime_DateTimeZone_3_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toDateTime(PARIS);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_DateTimeZone_4_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toDateTime(PARIS);
        // removed other assertion
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToDateTime_DateTimeZone_5_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_Chronology_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime(ISOChronology.getInstance());
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_Chronology_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime(ISOChronology.getInstance());
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToDateTime_Chronology_3_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toDateTime(GregorianChronology.getInstance(PARIS));
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_Chronology_4_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(GregorianChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToDateTime_Chronology_5_oe() {
        Instant test = new Instant(TEST_TIME1);
        DateTime result = test.toDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toDateTime((Chronology) null);
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime();
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime();
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTimeISO_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertSame(MutableDateTime.class,result.getClass());
    }

    public void testToMutableDateTimeISO_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTimeISO();
        // removed other assertion
        assertSame(ISOChronology.class,result.getChronology().getClass());
    }

    public void testToMutableDateTimeISO_3_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTimeISO();
        // removed other assertion
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTimeISO_4_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone_3_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_4_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone_5_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_6_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_Chronology_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_Chronology_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_Chronology_3_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_Chronology_4_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(GregorianChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToMutableDateTime_Chronology_5_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_Chronology_6_oe() {
        Instant test = new Instant(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion

        test = new Instant(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToDate_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        Date result = test.toDate();
        assertEquals(test.getMillis(),result.getTime());
    }

    public void testWithMillis_long_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withMillis(TEST_TIME2);
        assertEquals(TEST_TIME2,result.getMillis());
    }

    public void testWithMillis_long_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withMillis(TEST_TIME2);
        // removed other assertion
        assertEquals(test.getChronology(),result.getChronology());
    }

    public void testWithMillis_long_3_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withMillis(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        test = new Instant(TEST_TIME1);
        result = test.withMillis(TEST_TIME1);
        assertSame(test,result);
    }

    public void testWithDurationAdded_long_int_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withDurationAdded(123456789L, 1);
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        assertEquals(expected,result);
    }

    public void testWithDurationAdded_long_int_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withDurationAdded(123456789L, 1);
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, 0);
        assertSame(test,result);
    }

    public void testWithDurationAdded_long_int_3_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withDurationAdded(123456789L, 1);
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, 0);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, 2);
        expected = new Instant(TEST_TIME1 + (2L * 123456789L));
        assertEquals(expected,result);
    }

    public void testWithDurationAdded_long_int_4_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withDurationAdded(123456789L, 1);
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, 0);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, 2);
        expected = new Instant(TEST_TIME1 + (2L * 123456789L));
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, -3);
        expected = new Instant(TEST_TIME1 - (3L * 123456789L));
        assertEquals(expected,result);
    }

    public void testWithDurationAdded_RD_int_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withDurationAdded(new Duration(123456789L), 1);
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        assertEquals(expected,result);
    }

    public void testWithDurationAdded_RD_int_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withDurationAdded(new Duration(123456789L), 1);
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        // removed other assertion
        
        result = test.withDurationAdded(null, 1);
        assertSame(test,result);
    }

    public void testWithDurationAdded_RD_int_3_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withDurationAdded(new Duration(123456789L), 1);
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        // removed other assertion
        
        result = test.withDurationAdded(null, 1);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), 0);
        assertSame(test,result);
    }

    public void testWithDurationAdded_RD_int_4_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withDurationAdded(new Duration(123456789L), 1);
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        // removed other assertion
        
        result = test.withDurationAdded(null, 1);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), 0);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), 2);
        expected = new Instant(TEST_TIME1 + (2L * 123456789L));
        assertEquals(expected,result);
    }

    public void testWithDurationAdded_RD_int_5_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.withDurationAdded(new Duration(123456789L), 1);
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        // removed other assertion
        
        result = test.withDurationAdded(null, 1);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), 0);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), 2);
        expected = new Instant(TEST_TIME1 + (2L * 123456789L));
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), -3);
        expected = new Instant(TEST_TIME1 - (3L * 123456789L));
        assertEquals(expected,result);
    }

    public void testPlus_long_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.plus(123456789L);
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        assertEquals(expected,result);
    }

    public void testPlus_RD_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.plus(new Duration(123456789L));
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        assertEquals(expected,result);
    }

    public void testPlus_RD_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.plus(new Duration(123456789L));
        Instant expected = new Instant(TEST_TIME1 + 123456789L);
        // removed other assertion
        
        result = test.plus((ReadableDuration) null);
        assertSame(test,result);
    }

    public void testMinus_long_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.minus(123456789L);
        Instant expected = new Instant(TEST_TIME1 - 123456789L);
        assertEquals(expected,result);
    }

    public void testMinus_RD_1_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.minus(new Duration(123456789L));
        Instant expected = new Instant(TEST_TIME1 - 123456789L);
        assertEquals(expected,result);
    }

    public void testMinus_RD_2_oe() {
        Instant test = new Instant(TEST_TIME1);
        Instant result = test.minus(new Duration(123456789L));
        Instant expected = new Instant(TEST_TIME1 - 123456789L);
        // removed other assertion
        
        result = test.minus((ReadableDuration) null);
        assertSame(test,result);
    }

    public void testImmutable_1_oe() {
        assertTrue(Modifier.isFinal(Instant.class.getModifiers()));
    }

}
