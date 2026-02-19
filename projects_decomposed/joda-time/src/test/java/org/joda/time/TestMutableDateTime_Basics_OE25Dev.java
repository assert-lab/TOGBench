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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.base.AbstractInstant;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.UnsupportedDateTimeField;
import org.joda.time.field.UnsupportedDurationField;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a JUnit test for MutableDateTime.
 *
 * @author Stephen Colebourne
 */
public class TestMutableDateTime_Basics_OE25Dev extends TestCase {
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
        return new TestSuite(TestMutableDateTime_Basics_OE25Dev.class);
    }

    public TestMutableDateTime_Basics_OE25Dev(String name) {
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
            return ISOChronology.getInstance();
        }
    }

    class MockEqualsChronology extends BaseChronology {
        private static final long serialVersionUID = 1L;
        @Override
        public boolean equals(Object obj) {
            return obj instanceof MockEqualsChronology;
        }
        @Override
        public DateTimeZone getZone() {
            return null;
        }
        @Override
        public Chronology withUTC() {
            return this;
        }
        @Override
        public Chronology withZone(DateTimeZone zone) {
            return this;
        }
        @Override
        public String toString() {
            return "";
        }
    }
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testRounding7() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        try {
            test.setRounding(ISOChronology.getInstance().hourOfDay(), -1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

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

    public void testGet_DateTimeField_1_oe() {
        MutableDateTime test = new MutableDateTime();
        assertEquals(1,test.get(ISOChronology.getInstance().era()));
    }

    public void testGet_DateTimeField_2_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        assertEquals(20,test.get(ISOChronology.getInstance().centuryOfEra()));
    }

    public void testGet_DateTimeField_3_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.get(ISOChronology.getInstance().yearOfCentury()));
    }

    public void testGet_DateTimeField_4_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.get(ISOChronology.getInstance().yearOfEra()));
    }

    public void testGet_DateTimeField_5_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.get(ISOChronology.getInstance().year()));
    }

    public void testGet_DateTimeField_6_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.get(ISOChronology.getInstance().monthOfYear()));
    }

    public void testGet_DateTimeField_7_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.get(ISOChronology.getInstance().dayOfMonth()));
    }

    public void testGet_DateTimeField_8_oe() {
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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

    public void testGet_DateTimeFieldType_1_oe() {
        MutableDateTime test = new MutableDateTime();
        assertEquals(1,test.get(DateTimeFieldType.era()));
    }

    public void testGet_DateTimeFieldType_2_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        assertEquals(20,test.get(DateTimeFieldType.centuryOfEra()));
    }

    public void testGet_DateTimeFieldType_3_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.get(DateTimeFieldType.yearOfCentury()));
    }

    public void testGet_DateTimeFieldType_4_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.get(DateTimeFieldType.yearOfEra()));
    }

    public void testGet_DateTimeFieldType_5_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.get(DateTimeFieldType.year()));
    }

    public void testGet_DateTimeFieldType_6_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.get(DateTimeFieldType.monthOfYear()));
    }

    public void testGet_DateTimeFieldType_7_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.get(DateTimeFieldType.dayOfMonth()));
    }

    public void testGet_DateTimeFieldType_8_oe() {
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        MutableDateTime test = new MutableDateTime();
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
        assertEquals(1,test.get(DateTimeFieldType.hourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_14_oe() {
        MutableDateTime test = new MutableDateTime();
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
        assertEquals(1,test.get(DateTimeFieldType.clockhourOfDay()));
    }

    public void testGet_DateTimeFieldType_15_oe() {
        MutableDateTime test = new MutableDateTime();
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
        assertEquals(1,test.get(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_16_oe() {
        MutableDateTime test = new MutableDateTime();
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
        assertEquals(1,test.get(DateTimeFieldType.hourOfDay()));
    }

    public void testGet_DateTimeFieldType_17_oe() {
        MutableDateTime test = new MutableDateTime();
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
        assertEquals(0,test.get(DateTimeFieldType.minuteOfHour()));
    }

    public void testGet_DateTimeFieldType_18_oe() {
        MutableDateTime test = new MutableDateTime();
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
        assertEquals(60,test.get(DateTimeFieldType.minuteOfDay()));
    }

    public void testGet_DateTimeFieldType_19_oe() {
        MutableDateTime test = new MutableDateTime();
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
        assertEquals(0,test.get(DateTimeFieldType.secondOfMinute()));
    }

    public void testGet_DateTimeFieldType_20_oe() {
        MutableDateTime test = new MutableDateTime();
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
        assertEquals(60 * 60,test.get(DateTimeFieldType.secondOfDay()));
    }

    public void testGet_DateTimeFieldType_21_oe() {
        MutableDateTime test = new MutableDateTime();
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
        assertEquals(0,test.get(DateTimeFieldType.millisOfSecond()));
    }

    public void testGet_DateTimeFieldType_22_oe() {
        MutableDateTime test = new MutableDateTime();
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
        assertEquals(60 * 60 * 1000,test.get(DateTimeFieldType.millisOfDay()));
    }

    public void testGetMethods_1_oe() {
        MutableDateTime test = new MutableDateTime();
        
        assertEquals(ISOChronology.getInstance(),test.getChronology());
    }

    public void testGetMethods_2_oe() {
        MutableDateTime test = new MutableDateTime();
        
        // removed other assertion
        assertEquals(LONDON,test.getZone());
    }

    public void testGetMethods_3_oe() {
        MutableDateTime test = new MutableDateTime();
        
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillis());
    }

    public void testGetMethods_4_oe() {
        MutableDateTime test = new MutableDateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,test.getEra());
    }

    public void testGetMethods_5_oe() {
        MutableDateTime test = new MutableDateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(20,test.getCenturyOfEra());
    }

    public void testGetMethods_6_oe() {
        MutableDateTime test = new MutableDateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.getYearOfCentury());
    }

    public void testGetMethods_7_oe() {
        MutableDateTime test = new MutableDateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.getYearOfEra());
    }

    public void testGetMethods_8_oe() {
        MutableDateTime test = new MutableDateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002,test.getYear());
    }

    public void testGetMethods_9_oe() {
        MutableDateTime test = new MutableDateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.getMonthOfYear());
    }

    public void testGetMethods_10_oe() {
        MutableDateTime test = new MutableDateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getDayOfMonth());
    }

    public void testGetMethods_11_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(2002,test.getWeekyear());
    }

    public void testGetMethods_12_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(23,test.getWeekOfWeekyear());
    }

    public void testGetMethods_13_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(7,test.getDayOfWeek());
    }

    public void testGetMethods_14_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(160,test.getDayOfYear());
    }

    public void testGetMethods_15_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(1,test.getHourOfDay());
    }

    public void testGetMethods_16_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(0,test.getMinuteOfHour());
    }

    public void testGetMethods_17_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(60,test.getMinuteOfDay());
    }

    public void testGetMethods_18_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(0,test.getSecondOfMinute());
    }

    public void testGetMethods_19_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(60 * 60,test.getSecondOfDay());
    }

    public void testGetMethods_20_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(0,test.getMillisOfSecond());
    }

    public void testGetMethods_21_oe() {
        MutableDateTime test = new MutableDateTime();
        
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
        assertEquals(60 * 60 * 1000,test.getMillisOfDay());
    }

    public void testEqualsHashCode_1_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        assertEquals(true,test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(true,test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        assertEquals(false,test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(false,test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.hashCode()== test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test2.hashCode()== test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        assertEquals(true,test4.equals(test3));
    }

    public void testEqualsHashCode_15_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(true,test3.equals(test4));
    }

    public void testEqualsHashCode_16_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test4.equals(test1));
    }

    public void testEqualsHashCode_17_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.equals(test4));
    }

    public void testEqualsHashCode_18_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test3.hashCode()== test4.hashCode());
    }

    public void testEqualsHashCode_19_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.hashCode()== test4.hashCode());
    }

    public void testEqualsHashCode_20_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        assertEquals(true,test5.equals(test3));
    }

    public void testEqualsHashCode_21_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        // removed other assertion
        assertEquals(true,test5.equals(test4));
    }

    public void testEqualsHashCode_22_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        // removed other assertion
        // removed other assertion
        assertEquals(true,test3.equals(test5));
    }

    public void testEqualsHashCode_23_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test4.equals(test5));
    }

    public void testEqualsHashCode_24_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test3.hashCode()== test5.hashCode());
    }

    public void testEqualsHashCode_25_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test4.hashCode()== test5.hashCode());
    }

    public void testEqualsHashCode_26_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test1.equals("Hello"));
    }

    public void testEqualsHashCode_27_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,test1.equals(new MockInstant()));
    }

    public void testEqualsHashCode_28_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.equals(new MutableDateTime(TEST_TIME1,GregorianChronology.getInstance())));
    }

    public void testEqualsHashCode_29_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,new MutableDateTime(TEST_TIME1,new MockEqualsChronology()).equals(new MutableDateTime(TEST_TIME1,new MockEqualsChronology())));
    }

    public void testEqualsHashCode_30_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test4 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
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
        assertEquals(false,new MutableDateTime(TEST_TIME1,new MockEqualsChronology()).equals(new MutableDateTime(TEST_TIME1,ISOChronology.getInstance())));
    }

    public void testCompareTo_1_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(0,test1.compareTo(test1a));
    }

    public void testCompareTo_2_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(0,test1a.compareTo(test1));
    }

    public void testCompareTo_3_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(0,test1.compareTo(test1));
    }

    public void testCompareTo_4_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test1a.compareTo(test1a));
    }

    public void testCompareTo_5_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(-1,test1.compareTo(test2));
    }

    public void testCompareTo_6_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(+1,test2.compareTo(test1));
    }

    public void testCompareTo_7_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(-1,test1.compareTo(test3));
    }

    public void testCompareTo_8_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(+1,test3.compareTo(test1));
    }

    public void testCompareTo_9_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(0,test3.compareTo(test2));
    }

    public void testCompareTo_10_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(+1,test2.compareTo(new MockInstant()));
    }

    public void testCompareTo_11_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(0,test1.compareTo(new MockInstant()));
    }

    public void testIsEqual_1_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(true,test1.isEqual(test1a));
    }

    public void testIsEqual_2_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(true,test1a.isEqual(test1));
    }

    public void testIsEqual_3_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.isEqual(test1));
    }

    public void testIsEqual_4_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1a.isEqual(test1a));
    }

    public void testIsEqual_5_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(false,test1.isEqual(test2));
    }

    public void testIsEqual_6_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(false,test2.isEqual(test1));
    }

    public void testIsEqual_7_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false,test1.isEqual(test3));
    }

    public void testIsEqual_8_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(false,test3.isEqual(test1));
    }

    public void testIsEqual_9_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(true,test3.isEqual(test2));
    }

    public void testIsEqual_10_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test2.isEqual(new MockInstant()));
    }

    public void testIsEqual_11_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,test1.isEqual(new MockInstant()));
    }

    public void testIsEqual_12_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW + 1).isEqual(null));
    }

    public void testIsEqual_13_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,new MutableDateTime(TEST_TIME_NOW).isEqual(null));
    }

    public void testIsEqual_14_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW - 1).isEqual(null));
    }

    public void testIsBefore_1_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(false,test1.isBefore(test1a));
    }

    public void testIsBefore_2_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(false,test1a.isBefore(test1));
    }

    public void testIsBefore_3_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.isBefore(test1));
    }

    public void testIsBefore_4_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1a.isBefore(test1a));
    }

    public void testIsBefore_5_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(true,test1.isBefore(test2));
    }

    public void testIsBefore_6_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(false,test2.isBefore(test1));
    }

    public void testIsBefore_7_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(true,test1.isBefore(test3));
    }

    public void testIsBefore_8_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(false,test3.isBefore(test1));
    }

    public void testIsBefore_9_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.isBefore(test2));
    }

    public void testIsBefore_10_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test2.isBefore(new MockInstant()));
    }

    public void testIsBefore_11_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false,test1.isBefore(new MockInstant()));
    }

    public void testIsBefore_12_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW + 1).isBefore(null));
    }

    public void testIsBefore_13_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW).isBefore(null));
    }

    public void testIsBefore_14_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true,new MutableDateTime(TEST_TIME_NOW - 1).isBefore(null));
    }

    public void testIsAfter_1_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(false,test1.isAfter(test1a));
    }

    public void testIsAfter_2_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(false,test1a.isAfter(test1));
    }

    public void testIsAfter_3_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.isAfter(test1));
    }

    public void testIsAfter_4_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1a.isAfter(test1a));
    }

    public void testIsAfter_5_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(false,test1.isAfter(test2));
    }

    public void testIsAfter_6_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(true,test2.isAfter(test1));
    }

    public void testIsAfter_7_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false,test1.isAfter(test3));
    }

    public void testIsAfter_8_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(true,test3.isAfter(test1));
    }

    public void testIsAfter_9_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.isAfter(test2));
    }

    public void testIsAfter_10_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(true,test2.isAfter(new MockInstant()));
    }

    public void testIsAfter_11_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false,test1.isAfter(new MockInstant()));
    }

    public void testIsAfter_12_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(true,new MutableDateTime(TEST_TIME_NOW + 1).isAfter(null));
    }

    public void testIsAfter_13_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW).isAfter(null));
    }

    public void testIsAfter_14_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW - 1).isAfter(null));
    }

    public void testSerialization_1_oe() throws Exception {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        MutableDateTime result = (MutableDateTime) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

    public void testToString_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("2002-06-09T01:00:00.000+01:00",test.toString());
    }

    public void testToString_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        // removed other assertion
        
        test = new MutableDateTime(TEST_TIME_NOW, PARIS);
        assertEquals("2002-06-09T02:00:00.000+02:00",test.toString());
    }

    public void testToString_String_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("2002 01",test.toString("yyyy HH"));
    }

    public void testToString_String_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        // removed other assertion
        assertEquals("2002-06-09T01:00:00.000+01:00",test.toString((String)null));
    }

    public void testToString_String_String_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("Sun 9/6",test.toString("EEE d/M",Locale.ENGLISH));
    }

    public void testToString_String_String_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        // removed other assertion
        assertEquals("dim. 9/6",test.toString("EEE d/M",Locale.FRENCH));
    }

    public void testToString_String_String_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        // removed other assertion
        // removed other assertion
        assertEquals("2002-06-09T01:00:00.000+01:00",test.toString(null,Locale.ENGLISH));
    }

    public void testToString_String_String_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Sun 9/6",test.toString("EEE d/M",null));
    }

    public void testToString_String_String_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2002-06-09T01:00:00.000+01:00",test.toString(null,null));
    }

    public void testToString_DTFormatter_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("2002 01",test.toString(DateTimeFormat.forPattern("yyyy HH")));
    }

    public void testToString_DTFormatter_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        // removed other assertion
        assertEquals("2002-06-09T01:00:00.000+01:00",test.toString((DateTimeFormatter)null));
    }

    public void testToInstant_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Instant result = test.toInstant();
        assertEquals(TEST_TIME1,result.getMillis());
    }

    public void testToDateTime_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTime();
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTime();
        // removed other assertion
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToDateTimeISO_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTimeISO();
        assertSame(DateTime.class,result.getClass());
    }

    public void testToDateTimeISO_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        assertSame(ISOChronology.class,result.getChronology().getClass());
    }

    public void testToDateTimeISO_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTimeISO_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToDateTime_DateTimeZone_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        assertEquals(test.getChronology(),result.getChronology());
    }

    public void testToDateTime_DateTimeZone_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        assertEquals(LONDON,result.getZone());
    }

    public void testToDateTime_DateTimeZone_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_DateTimeZone_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        assertEquals(PARIS,result.getZone());
    }

    public void testToDateTime_DateTimeZone_7_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        assertEquals(GregorianChronology.getInstance(LONDON),result.getChronology());
    }

    public void testToDateTime_DateTimeZone_8_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_DateTimeZone_9_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        assertEquals(LONDON,result.getZone());
    }

    public void testToDateTime_DateTimeZone_10_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_DateTimeZone_11_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        assertEquals(LONDON,result.getZone());
    }

    public void testToDateTime_DateTimeZone_12_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToDateTime_Chronology_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_Chronology_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToDateTime_Chronology_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_Chronology_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(GregorianChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToDateTime_Chronology_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_Chronology_6_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToDateTime_Chronology_7_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_Chronology_8_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        assertTrue(test != result);
    }

    public void testToMutableDateTime_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToMutableDateTimeISO_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertSame(MutableDateTime.class,result.getClass());
    }

    public void testToMutableDateTimeISO_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        // removed other assertion
        assertSame(ISOChronology.class,result.getChronology().getClass());
    }

    public void testToMutableDateTimeISO_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        // removed other assertion
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTimeISO_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToMutableDateTimeISO_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test,result);
    }

    public void testToMutableDateTime_DateTimeZone_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertTrue(test != result);
    }

    public void testToMutableDateTime_DateTimeZone_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(LONDON),result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertTrue(test != result);
    }

    public void testToMutableDateTime_DateTimeZone_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_6_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone_7_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertTrue(test != result);
    }

    public void testToMutableDateTime_DateTimeZone_8_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_9_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone_10_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertTrue(test != result);
    }

    public void testToMutableDateTime_DateTimeZone_11_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_12_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_Chronology_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertTrue(test != result);
    }

    public void testToMutableDateTime_Chronology_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_Chronology_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_Chronology_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertTrue(test != result);
    }

    public void testToMutableDateTime_Chronology_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_Chronology_6_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(GregorianChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToMutableDateTime_Chronology_7_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        assertTrue(test != result);
    }

    public void testToMutableDateTime_Chronology_8_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_Chronology_9_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_Chronology_10_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        assertTrue(test != result);
    }

    public void testToMutableDateTime_Chronology_11_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToMutableDateTime_Chronology_12_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToDate_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Date result = test.toDate();
        assertEquals(test.getMillis(),result.getTime());
    }

    public void testToCalendar_Locale_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        assertEquals(test.getMillis(),result.getTime().getTime());
    }

    public void testToCalendar_Locale_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Europe/London"),result.getTimeZone());
    }

    public void testToCalendar_Locale_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);
        assertEquals(test.getMillis(),result.getTime().getTime());
    }

    public void testToCalendar_Locale_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);
        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Europe/Paris"),result.getTimeZone());
    }

    public void testToCalendar_Locale_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(Locale.UK);
        assertEquals(test.getMillis(),result.getTime().getTime());
    }

    public void testToCalendar_Locale_6_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(Locale.UK);
        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Europe/Paris"),result.getTimeZone());
    }

    public void testToGregorianCalendar_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();
        assertEquals(test.getMillis(),result.getTime().getTime());
    }

    public void testToGregorianCalendar_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();
        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Europe/London"),result.getTimeZone());
    }

    public void testToGregorianCalendar_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toGregorianCalendar();
        assertEquals(test.getMillis(),result.getTime().getTime());
    }

    public void testToGregorianCalendar_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toGregorianCalendar();
        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Europe/Paris"),result.getTimeZone());
    }

    public void testClone_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = (MutableDateTime) test.clone();
        assertEquals(true,test.equals(result));
    }

    public void testClone_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = (MutableDateTime) test.clone();
        // removed other assertion
        assertEquals(true,test != result);
    }

    public void testCopy_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.copy();
        assertEquals(true,test.equals(result));
    }

    public void testCopy_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.copy();
        // removed other assertion
        assertEquals(true,test != result);
    }

    public void testRounding1_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        assertEquals("2002-06-09T05:00:00.000+01:00",test.toString());
    }

    public void testRounding1_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        // removed other assertion
        assertEquals(MutableDateTime.ROUND_FLOOR,test.getRoundingMode());
    }

    public void testRounding1_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
    }

    public void testRounding2_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals("2002-06-09T06:00:00.000+01:00",test.toString());
    }

    public void testRounding2_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        assertEquals(MutableDateTime.ROUND_CEILING,test.getRoundingMode());
    }

    public void testRounding2_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
    }

    public void testRounding3_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        assertEquals("2002-06-09T05:00:00.000+01:00",test.toString());
    }

    public void testRounding3_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        // removed other assertion
        assertEquals(MutableDateTime.ROUND_HALF_CEILING,test.getRoundingMode());
    }

    public void testRounding3_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
    }

    public void testRounding3_4_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        assertEquals("2002-06-09T06:00:00.000+01:00",test.toString());
    }

    public void testRounding4_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        assertEquals("2002-06-09T05:00:00.000+01:00",test.toString());
    }

    public void testRounding4_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        // removed other assertion
        assertEquals(MutableDateTime.ROUND_HALF_FLOOR,test.getRoundingMode());
    }

    public void testRounding4_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
    }

    public void testRounding4_4_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        assertEquals("2002-06-09T05:00:00.000+01:00",test.toString());
    }

    public void testRounding5_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals("2002-06-09T05:00:00.000+01:00",test.toString());
    }

    public void testRounding5_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        // removed other assertion
        assertEquals(MutableDateTime.ROUND_HALF_EVEN,test.getRoundingMode());
    }

    public void testRounding5_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
    }

    public void testRounding5_4_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals("2002-06-09T06:00:00.000+01:00",test.toString());
    }

    public void testRounding5_5_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        // removed other assertion
        
        test = new MutableDateTime(2002, 6, 9, 4, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals("2002-06-09T04:00:00.000+01:00",test.toString());
    }

    public void testRounding6_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals("2002-06-09T05:06:07.008+01:00",test.toString());
    }

    public void testRounding6_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        // removed other assertion
        assertEquals(MutableDateTime.ROUND_NONE,test.getRoundingMode());
    }

    public void testRounding6_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        // removed other assertion
        // removed other assertion
        assertEquals(null,test.getRoundingField());
    }

    public void testRounding8_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        assertEquals(MutableDateTime.ROUND_NONE,test.getRoundingMode());
    }

    public void testRounding8_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        assertEquals(null,test.getRoundingField());
    }

    public void testRounding8_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals(MutableDateTime.ROUND_CEILING,test.getRoundingMode());
    }

    public void testRounding8_4_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
    }

    public void testRounding8_5_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals(MutableDateTime.ROUND_NONE,test.getRoundingMode());
    }

    public void testRounding8_6_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        // removed other assertion
        assertEquals(null,test.getRoundingField());
    }

    public void testRounding8_7_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(null, -1);
        assertEquals(MutableDateTime.ROUND_NONE,test.getRoundingMode());
    }

    public void testRounding8_8_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(null, -1);
        // removed other assertion
        assertEquals(null,test.getRoundingField());
    }

    public void testRounding8_9_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(null, -1);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        assertEquals(MutableDateTime.ROUND_FLOOR,test.getRoundingMode());
    }

    public void testRounding8_10_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(null, -1);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        // removed other assertion
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
    }

    public void testRounding8_11_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(null, -1);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        // removed other assertion
        // removed other assertion
        
        test.setRounding(null);
        assertEquals(MutableDateTime.ROUND_NONE,test.getRoundingMode());
    }

    public void testRounding8_12_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(null, -1);
        // removed other assertion
        // removed other assertion
        
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        // removed other assertion
        // removed other assertion
        
        test.setRounding(null);
        // removed other assertion
        assertEquals(null,test.getRoundingField());
    }

    public void testProperty_1_oe() {
        MutableDateTime test = new MutableDateTime();
        assertEquals(test.year(),test.property(DateTimeFieldType.year()));
    }

    public void testProperty_2_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        assertEquals(test.dayOfWeek(),test.property(DateTimeFieldType.dayOfWeek()));
    }

    public void testProperty_3_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        assertEquals(test.secondOfMinute(),test.property(DateTimeFieldType.secondOfMinute()));
    }

    public void testProperty_4_oe() {
        MutableDateTime test = new MutableDateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.millisOfSecond(),test.property(DateTimeFieldType.millisOfSecond()));
    }

    public void testToDateTime_DateTimeZone_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(test.getMillis(),result.getMillis());
    }

    public void testToDateTime_DateTimeZone_6_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
    }

}
