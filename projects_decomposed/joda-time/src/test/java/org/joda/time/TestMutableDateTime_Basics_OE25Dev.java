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
    public void testTest() {
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW).toString());
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1).toString());
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2).toString());
    }

    //-----------------------------------------------------------------------
    public void testGet_DateTimeField() {
        MutableDateTime test = new MutableDateTime();
        assertEquals(1,test.get(ISOChronology.getInstance().era()));
        assertEquals(20,test.get(ISOChronology.getInstance().centuryOfEra()));
        assertEquals(2,test.get(ISOChronology.getInstance().yearOfCentury()));
        assertEquals(2002,test.get(ISOChronology.getInstance().yearOfEra()));
        assertEquals(2002,test.get(ISOChronology.getInstance().year()));
        assertEquals(6,test.get(ISOChronology.getInstance().monthOfYear()));
        assertEquals(9,test.get(ISOChronology.getInstance().dayOfMonth()));
        assertEquals(2002,test.get(ISOChronology.getInstance().weekyear()));
        assertEquals(23,test.get(ISOChronology.getInstance().weekOfWeekyear()));
        assertEquals(7,test.get(ISOChronology.getInstance().dayOfWeek()));
        assertEquals(160,test.get(ISOChronology.getInstance().dayOfYear()));
        assertEquals(0,test.get(ISOChronology.getInstance().halfdayOfDay()));
        assertEquals(1,test.get(ISOChronology.getInstance().hourOfHalfday()));
        assertEquals(1,test.get(ISOChronology.getInstance().clockhourOfDay()));
        assertEquals(1,test.get(ISOChronology.getInstance().clockhourOfHalfday()));
        assertEquals(1,test.get(ISOChronology.getInstance().hourOfDay()));
        assertEquals(0,test.get(ISOChronology.getInstance().minuteOfHour()));
        assertEquals(60,test.get(ISOChronology.getInstance().minuteOfDay()));
        assertEquals(0,test.get(ISOChronology.getInstance().secondOfMinute()));
        assertEquals(60 * 60,test.get(ISOChronology.getInstance().secondOfDay()));
        assertEquals(0,test.get(ISOChronology.getInstance().millisOfSecond()));
        assertEquals(60 * 60 * 1000,test.get(ISOChronology.getInstance().millisOfDay()));
        try {
            test.get((DateTimeField) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testGet_DateTimeFieldType() {
        MutableDateTime test = new MutableDateTime();
        assertEquals(1,test.get(DateTimeFieldType.era()));
        assertEquals(20,test.get(DateTimeFieldType.centuryOfEra()));
        assertEquals(2,test.get(DateTimeFieldType.yearOfCentury()));
        assertEquals(2002,test.get(DateTimeFieldType.yearOfEra()));
        assertEquals(2002,test.get(DateTimeFieldType.year()));
        assertEquals(6,test.get(DateTimeFieldType.monthOfYear()));
        assertEquals(9,test.get(DateTimeFieldType.dayOfMonth()));
        assertEquals(2002,test.get(DateTimeFieldType.weekyear()));
        assertEquals(23,test.get(DateTimeFieldType.weekOfWeekyear()));
        assertEquals(7,test.get(DateTimeFieldType.dayOfWeek()));
        assertEquals(160,test.get(DateTimeFieldType.dayOfYear()));
        assertEquals(0,test.get(DateTimeFieldType.halfdayOfDay()));
        assertEquals(1,test.get(DateTimeFieldType.hourOfHalfday()));
        assertEquals(1,test.get(DateTimeFieldType.clockhourOfDay()));
        assertEquals(1,test.get(DateTimeFieldType.clockhourOfHalfday()));
        assertEquals(1,test.get(DateTimeFieldType.hourOfDay()));
        assertEquals(0,test.get(DateTimeFieldType.minuteOfHour()));
        assertEquals(60,test.get(DateTimeFieldType.minuteOfDay()));
        assertEquals(0,test.get(DateTimeFieldType.secondOfMinute()));
        assertEquals(60 * 60,test.get(DateTimeFieldType.secondOfDay()));
        assertEquals(0,test.get(DateTimeFieldType.millisOfSecond()));
        assertEquals(60 * 60 * 1000,test.get(DateTimeFieldType.millisOfDay()));
        try {
            test.get((DateTimeFieldType) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testGetMethods() {
        MutableDateTime test = new MutableDateTime();
        
        assertEquals(ISOChronology.getInstance(),test.getChronology());
        assertEquals(LONDON,test.getZone());
        assertEquals(TEST_TIME_NOW,test.getMillis());
        
        assertEquals(1,test.getEra());
        assertEquals(20,test.getCenturyOfEra());
        assertEquals(2,test.getYearOfCentury());
        assertEquals(2002,test.getYearOfEra());
        assertEquals(2002,test.getYear());
        assertEquals(6,test.getMonthOfYear());
        assertEquals(9,test.getDayOfMonth());
        assertEquals(2002,test.getWeekyear());
        assertEquals(23,test.getWeekOfWeekyear());
        assertEquals(7,test.getDayOfWeek());
        assertEquals(160,test.getDayOfYear());
        assertEquals(1,test.getHourOfDay());
        assertEquals(0,test.getMinuteOfHour());
        assertEquals(60,test.getMinuteOfDay());
        assertEquals(0,test.getSecondOfMinute());
        assertEquals(60 * 60,test.getSecondOfDay());
        assertEquals(0,test.getMillisOfSecond());
        assertEquals(60 * 60 * 1000,test.getMillisOfDay());
    }

    public void testEqualsHashCode() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        assertEquals(true,test1.equals(test2));
        assertEquals(true,test2.equals(test1));
        assertEquals(true,test1.equals(test1));
        assertEquals(true,test2.equals(test2));
        assertEquals(true,test1.hashCode()== test2.hashCode());
        assertEquals(true,test1.hashCode()== test1.hashCode());
        assertEquals(true,test2.hashCode()== test2.hashCode());
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        assertEquals(false,test1.equals(test3));
        assertEquals(false,test2.equals(test3));
        assertEquals(false,test3.equals(test1));
        assertEquals(false,test3.equals(test2));
        assertEquals(false,test1.hashCode()== test3.hashCode());
        assertEquals(false,test2.hashCode()== test3.hashCode());
        
        DateTime test4 = new DateTime(TEST_TIME2);
        assertEquals(true,test4.equals(test3));
        assertEquals(true,test3.equals(test4));
        assertEquals(false,test4.equals(test1));
        assertEquals(false,test1.equals(test4));
        assertEquals(true,test3.hashCode()== test4.hashCode());
        assertEquals(false,test1.hashCode()== test4.hashCode());
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        assertEquals(true,test5.equals(test3));
        assertEquals(true,test5.equals(test4));
        assertEquals(true,test3.equals(test5));
        assertEquals(true,test4.equals(test5));
        assertEquals(true,test3.hashCode()== test5.hashCode());
        assertEquals(true,test4.hashCode()== test5.hashCode());
        
        assertEquals(false,test1.equals("Hello"));
        assertEquals(true,test1.equals(new MockInstant()));
        assertEquals(false,test1.equals(new MutableDateTime(TEST_TIME1,GregorianChronology.getInstance())));
        assertEquals(true,new MutableDateTime(TEST_TIME1,new MockEqualsChronology()).equals(new MutableDateTime(TEST_TIME1,new MockEqualsChronology())));
        assertEquals(false,new MutableDateTime(TEST_TIME1,new MockEqualsChronology()).equals(new MutableDateTime(TEST_TIME1,ISOChronology.getInstance())));
    }
    
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

    public void testCompareTo() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(0,test1.compareTo(test1a));
        assertEquals(0,test1a.compareTo(test1));
        assertEquals(0,test1.compareTo(test1));
        assertEquals(0,test1a.compareTo(test1a));
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(-1,test1.compareTo(test2));
        assertEquals(+1,test2.compareTo(test1));
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(-1,test1.compareTo(test3));
        assertEquals(+1,test3.compareTo(test1));
        assertEquals(0,test3.compareTo(test2));
        
        assertEquals(+1,test2.compareTo(new MockInstant()));
        assertEquals(0,test1.compareTo(new MockInstant()));
        
        try {
            test1.compareTo(null);
            fail();
        } catch (NullPointerException ex) {}
//        try {
//            test1.compareTo(new Date());
//            fail();
//        } catch (ClassCastException ex) {}
    }
    
    public void testIsEqual() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(true,test1.isEqual(test1a));
        assertEquals(true,test1a.isEqual(test1));
        assertEquals(true,test1.isEqual(test1));
        assertEquals(true,test1a.isEqual(test1a));
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(false,test1.isEqual(test2));
        assertEquals(false,test2.isEqual(test1));
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false,test1.isEqual(test3));
        assertEquals(false,test3.isEqual(test1));
        assertEquals(true,test3.isEqual(test2));
        
        assertEquals(false,test2.isEqual(new MockInstant()));
        assertEquals(true,test1.isEqual(new MockInstant()));
        
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW + 1).isEqual(null));
        assertEquals(true,new MutableDateTime(TEST_TIME_NOW).isEqual(null));
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW - 1).isEqual(null));
    }
    
    public void testIsBefore() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(false,test1.isBefore(test1a));
        assertEquals(false,test1a.isBefore(test1));
        assertEquals(false,test1.isBefore(test1));
        assertEquals(false,test1a.isBefore(test1a));
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(true,test1.isBefore(test2));
        assertEquals(false,test2.isBefore(test1));
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(true,test1.isBefore(test3));
        assertEquals(false,test3.isBefore(test1));
        assertEquals(false,test3.isBefore(test2));
        
        assertEquals(false,test2.isBefore(new MockInstant()));
        assertEquals(false,test1.isBefore(new MockInstant()));
        
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW + 1).isBefore(null));
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW).isBefore(null));
        assertEquals(true,new MutableDateTime(TEST_TIME_NOW - 1).isBefore(null));
    }
    
    public void testIsAfter() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(false,test1.isAfter(test1a));
        assertEquals(false,test1a.isAfter(test1));
        assertEquals(false,test1.isAfter(test1));
        assertEquals(false,test1a.isAfter(test1a));
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(false,test1.isAfter(test2));
        assertEquals(true,test2.isAfter(test1));
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false,test1.isAfter(test3));
        assertEquals(true,test3.isAfter(test1));
        assertEquals(false,test3.isAfter(test2));
        
        assertEquals(true,test2.isAfter(new MockInstant()));
        assertEquals(false,test1.isAfter(new MockInstant()));
        
        assertEquals(true,new MutableDateTime(TEST_TIME_NOW + 1).isAfter(null));
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW).isAfter(null));
        assertEquals(false,new MutableDateTime(TEST_TIME_NOW - 1).isAfter(null));
    }
    
    //-----------------------------------------------------------------------
    public void testSerialization() throws Exception {
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

    //-----------------------------------------------------------------------
    public void testToString() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("2002-06-09T01:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(TEST_TIME_NOW, PARIS);
        assertEquals("2002-06-09T02:00:00.000+02:00",test.toString());
    }

    public void testToString_String() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("2002 01",test.toString("yyyy HH"));
        assertEquals("2002-06-09T01:00:00.000+01:00",test.toString((String)null));
    }

    public void testToString_String_String() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("Sun 9/6",test.toString("EEE d/M",Locale.ENGLISH));
        assertEquals("dim. 9/6",test.toString("EEE d/M",Locale.FRENCH));
        assertEquals("2002-06-09T01:00:00.000+01:00",test.toString(null,Locale.ENGLISH));
        assertEquals("Sun 9/6",test.toString("EEE d/M",null));
        assertEquals("2002-06-09T01:00:00.000+01:00",test.toString(null,null));
    }

    public void testToString_DTFormatter() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("2002 01",test.toString(DateTimeFormat.forPattern("yyyy HH")));
        assertEquals("2002-06-09T01:00:00.000+01:00",test.toString((DateTimeFormatter)null));
    }

    //-----------------------------------------------------------------------
    public void testToInstant() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Instant result = test.toInstant();
        assertEquals(TEST_TIME1,result.getMillis());
    }

    public void testToDateTime() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTime();
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToDateTimeISO() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTimeISO();
        assertSame(DateTime.class,result.getClass());
        assertSame(ISOChronology.class,result.getChronology().getClass());
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToDateTime_DateTimeZone() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(test.getChronology(),result.getChronology());
        assertEquals(LONDON,result.getZone());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(PARIS,result.getZone());

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(GregorianChronology.getInstance(LONDON),result.getChronology());

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(LONDON,result.getZone());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(LONDON,result.getZone());
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToDateTime_Chronology() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(),result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(GregorianChronology.getInstance(PARIS),result.getChronology());

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(),result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        assertTrue(test != result);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
    }

    public void testToMutableDateTimeISO() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertSame(MutableDateTime.class,result.getClass());
        assertSame(ISOChronology.class,result.getChronology().getClass());
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());
        assertNotSame(test,result);
    }

    public void testToMutableDateTime_DateTimeZone() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertTrue(test != result);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(LONDON),result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertTrue(test != result);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(PARIS),result.getChronology());

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertTrue(test != result);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(),result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertTrue(test != result);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToMutableDateTime_Chronology() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertTrue(test != result);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(),result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertTrue(test != result);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(GregorianChronology.getInstance(PARIS),result.getChronology());

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        assertTrue(test != result);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(),result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        assertTrue(test != result);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISOChronology.getInstance(),result.getChronology());
    }

    public void testToDate() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Date result = test.toDate();
        assertEquals(test.getMillis(),result.getTime());
    }

    public void testToCalendar_Locale() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        assertEquals(test.getMillis(),result.getTime().getTime());
        assertEquals(TimeZone.getTimeZone("Europe/London"),result.getTimeZone());

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);
        assertEquals(test.getMillis(),result.getTime().getTime());
        assertEquals(TimeZone.getTimeZone("Europe/Paris"),result.getTimeZone());

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(Locale.UK);
        assertEquals(test.getMillis(),result.getTime().getTime());
        assertEquals(TimeZone.getTimeZone("Europe/Paris"),result.getTimeZone());
    }

    public void testToGregorianCalendar() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();
        assertEquals(test.getMillis(),result.getTime().getTime());
        assertEquals(TimeZone.getTimeZone("Europe/London"),result.getTimeZone());

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toGregorianCalendar();
        assertEquals(test.getMillis(),result.getTime().getTime());
        assertEquals(TimeZone.getTimeZone("Europe/Paris"),result.getTimeZone());
    }

    public void testClone() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = (MutableDateTime) test.clone();
        assertEquals(true,test.equals(result));
        assertEquals(true,test != result);
    }

    public void testCopy() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.copy();
        assertEquals(true,test.equals(result));
        assertEquals(true,test != result);
    }

    public void testRounding1() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        assertEquals("2002-06-09T05:00:00.000+01:00",test.toString());
        assertEquals(MutableDateTime.ROUND_FLOOR,test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
    }

    public void testRounding2() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals("2002-06-09T06:00:00.000+01:00",test.toString());
        assertEquals(MutableDateTime.ROUND_CEILING,test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
    }

    public void testRounding3() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        assertEquals("2002-06-09T05:00:00.000+01:00",test.toString());
        assertEquals(MutableDateTime.ROUND_HALF_CEILING,test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        assertEquals("2002-06-09T06:00:00.000+01:00",test.toString());
    }

    public void testRounding4() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        assertEquals("2002-06-09T05:00:00.000+01:00",test.toString());
        assertEquals(MutableDateTime.ROUND_HALF_FLOOR,test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        assertEquals("2002-06-09T05:00:00.000+01:00",test.toString());
    }

    public void testRounding5() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals("2002-06-09T05:00:00.000+01:00",test.toString());
        assertEquals(MutableDateTime.ROUND_HALF_EVEN,test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals("2002-06-09T06:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2002, 6, 9, 4, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals("2002-06-09T04:00:00.000+01:00",test.toString());
    }

    public void testRounding6() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals("2002-06-09T05:06:07.008+01:00",test.toString());
        assertEquals(MutableDateTime.ROUND_NONE,test.getRoundingMode());
        assertEquals(null,test.getRoundingField());
    }

    public void testRounding7() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        try {
            test.setRounding(ISOChronology.getInstance().hourOfDay(), -1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testRounding8() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        assertEquals(MutableDateTime.ROUND_NONE,test.getRoundingMode());
        assertEquals(null,test.getRoundingField());
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals(MutableDateTime.ROUND_CEILING,test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals(MutableDateTime.ROUND_NONE,test.getRoundingMode());
        assertEquals(null,test.getRoundingField());
        
        test.setRounding(null, -1);
        assertEquals(MutableDateTime.ROUND_NONE,test.getRoundingMode());
        assertEquals(null,test.getRoundingField());
        
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        assertEquals(MutableDateTime.ROUND_FLOOR,test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(),test.getRoundingField());
        
        test.setRounding(null);
        assertEquals(MutableDateTime.ROUND_NONE,test.getRoundingMode());
        assertEquals(null,test.getRoundingField());
    }

    //-----------------------------------------------------------------------
    public void testProperty() {
        MutableDateTime test = new MutableDateTime();
        assertEquals(test.year(),test.property(DateTimeFieldType.year()));
        assertEquals(test.dayOfWeek(),test.property(DateTimeFieldType.dayOfWeek()));
        assertEquals(test.secondOfMinute(),test.property(DateTimeFieldType.secondOfMinute()));
        assertEquals(test.millisOfSecond(),test.property(DateTimeFieldType.millisOfSecond()));
        DateTimeFieldType bad = new DateTimeFieldType("bad") {
            private static final long serialVersionUID = 1L;
            @Override
            public DurationFieldType getDurationType() {
                return DurationFieldType.weeks();
            }
            @Override
            public DurationFieldType getRangeDurationType() {
                return null;
            }
            @Override
            public DateTimeField getField(Chronology chronology) {
                return UnsupportedDateTimeField.getInstance(this, UnsupportedDurationField.getInstance(getDurationType()));
            }
        };
        try {
            test.property(bad);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.property(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testTest_1_oe() {
        Object a = new Instant(TEST_TIME_NOW).toString();
        assertEquals("2013-07-01T00:59:59.999999999+0100:+0100", a);
    }

    public void testTest_2_oe() {
        Object a = new Instant(TEST_TIME1).toString();
        assertEquals("2013-06-12T08:08:08.000Z", a);
    }

    public void testTest_3_oe() {
        Object a = new Instant(TEST_TIME2).toString();
        assertEquals("1970-01-01T01:00:00Z", a);
    }

    public void testGet_DateTimeField_1_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_2_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_3_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_4_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_5_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_6_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_7_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_8_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_9_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_10_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_11_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_12_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_13_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_14_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_15_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_16_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_17_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_18_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_19_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_20_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_21_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_22_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_1_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_2_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_3_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_4_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_5_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_6_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_7_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_8_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_9_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_10_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_11_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_12_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_13_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_14_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_15_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_16_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_17_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_18_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_19_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_20_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_21_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_22_oe() {
        MutableDateTime test = new MutableDateTime();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGetMethods_1_oe() {
        MutableDateTime test = new MutableDateTime();
        
// incorrect assertion         assertNotNull(MutableDateTime.getInstance());
    }

    public void testGetMethods_4_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(1, test.getEra());
    }

    public void testGetMethods_5_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(1, test.getCenturyOfEra());
    }

    public void testGetMethods_6_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(1, test.getYearOfCentury());
    }

    public void testGetMethods_7_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(1, test.getYearOfEra());
    }

    public void testGetMethods_9_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(6, test.getMonthOfYear());
    }

    public void testGetMethods_10_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(1, test.getDayOfMonth());
    }

    public void testGetMethods_11_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(2013, test.getWeekyear());
    }

    public void testGetMethods_12_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(1, test.getWeekOfWeekyear());
    }

    public void testGetMethods_13_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(2, test.getDayOfWeek());
    }

    public void testGetMethods_14_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(1, test.getDayOfYear());
    }

    public void testGetMethods_15_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(0, test.getHourOfDay());
    }

    public void testGetMethods_16_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testGetMethods_17_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(0, test.getMinuteOfDay());
    }

    public void testGetMethods_18_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testGetMethods_19_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(0, test.getSecondOfDay());
    }

    public void testGetMethods_20_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testGetMethods_21_oe() {
        MutableDateTime test = new MutableDateTime();
        
        
        assertEquals(0, test.getMillisOfDay());
    }

    public void testEqualsHashCode_1_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        assertEquals(1372706400000L, test2.getMillis());
    }

    public void testEqualsHashCode_3_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        assertEquals(1372706400000L, test1.getMillis());
    }

    public void testEqualsHashCode_4_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        assertEquals(0, test1.getMillisOfSecond());
    }

    public void testEqualsHashCode_5_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        assertEquals(1372706400000L, test1.getMillis());
    }

    public void testEqualsHashCode_6_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        assertEquals(1372706400000L, test1.getMillis());
    }

    public void testEqualsHashCode_7_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        assertEquals(0, test1.getMillisOfSecond());
    }

    public void testEqualsHashCode_8_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        assertEquals(1372706400000L, test2.getMillis());
    }

    public void testEqualsHashCode_9_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        assertEquals(1372706400000L, test2.getMillis());
    }

    public void testEqualsHashCode_10_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        assertEquals(1372706400000L, test2.getMillis());
    }

    public void testEqualsHashCode_11_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        assertEquals(1372706400000L, test2.getMillis());
    }

    public void testEqualsHashCode_12_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        assertEquals(1372706400000L, test2.getMillis());
    }

    public void testEqualsHashCode_13_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        assertEquals(1372706400000L, test2.getMillis());
    }

    public void testEqualsHashCode_14_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        
        DateTime test4 = new DateTime(TEST_TIME2);
        assertEquals(0, test1.getMillisOfSecond());
    }

    public void testEqualsHashCode_15_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        
        DateTime test4 = new DateTime(TEST_TIME2);
        assertEquals(0, test1.getMillisOfSecond());
    }

    public void testEqualsHashCode_16_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        
        DateTime test4 = new DateTime(TEST_TIME2);
        assertEquals(0, test1.getMillisOfSecond());
    }

    public void testEqualsHashCode_17_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        
        DateTime test4 = new DateTime(TEST_TIME2);
        assertEquals(1372706400000L, test3.getMillis());
    }

    public void testEqualsHashCode_18_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        
        DateTime test4 = new DateTime(TEST_TIME2);
        assertEquals(0, test1.getMillisOfSecond());
    }

    public void testEqualsHashCode_19_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        
        DateTime test4 = new DateTime(TEST_TIME2);
        assertEquals(0, test1.getMillisOfSecond());
    }

    public void testEqualsHashCode_21_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        
        DateTime test4 = new DateTime(TEST_TIME2);
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        assertEquals(0, test5.getMillisOfSecond());
    }

    public void testCompareTo_4_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_6_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_7_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_8_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_9_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_10_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_11_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testIsEqual_7_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_10_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_11_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_12_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_14_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsBefore_1_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(false, test1.isBefore(TEST_TIME1));
    }

    public void testIsBefore_5_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(false, test1.isBefore(test1a.getMillis()));
    }

    public void testIsBefore_6_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(false, test1.isBefore(TEST_TIME1));
    }

    public void testIsBefore_7_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false, test1.isBefore(test1a.getMillis()));
    }

    public void testIsBefore_8_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false, test1.isBefore(test1a.getMillis()));
    }

    public void testIsBefore_9_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false, test1.isBefore(test1a.getMillis()));
    }

    public void testIsBefore_10_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        assertEquals(false, test1.isBefore(test1a.getMillis()));
    }

    public void testIsBefore_11_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        assertEquals(false, test1.isBefore(test1a.getMillis()));
    }

    public void testIsBefore_12_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        
        assertEquals(false, test1.isBefore(test1a.getMillis()));
    }

    public void testIsBefore_13_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        
        assertEquals(false, test1.isBefore(test1a.getMillis()));
    }

    public void testIsBefore_14_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        
        assertEquals(false, test1.isBefore(test1a.getMillis()));
    }

    public void testIsAfter_1_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
// incorrect assertion         assertEquals(false, test1.isAfter());
    }

    public void testIsAfter_2_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
// incorrect assertion         assertEquals(false, test1.isAfter());
    }

    public void testIsAfter_4_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
// incorrect assertion         assertEquals(false, test1.isAfter());
    }

    public void testIsAfter_5_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(false, test1.isAfter(TEST_TIME1));
    }

    public void testIsAfter_6_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(false, test1.isAfter(TEST_TIME1));
    }

    public void testIsAfter_7_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false, test1.isAfter(TEST_TIME1));
    }

    public void testIsAfter_8_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false, test1.isAfter(TEST_TIME1));
    }

    public void testIsAfter_9_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false, test1.isAfter(TEST_TIME1));
    }

    public void testIsAfter_10_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        assertEquals(false, test1.isAfter(TEST_TIME1));
    }

    public void testIsAfter_11_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        assertEquals(false, test1.isAfter(TEST_TIME1));
    }

    public void testIsAfter_12_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        
        assertEquals(false, test1.isAfter(TEST_TIME1));
    }

    public void testIsAfter_13_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        
        assertEquals(false, test1.isAfter(TEST_TIME1));
    }

    public void testIsAfter_14_oe() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        
        
        assertEquals(false, test1.isAfter(TEST_TIME1));
    }

    public void testToString_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertNotNull(test);
    }

    public void testToString_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        
        test = new MutableDateTime(TEST_TIME_NOW, PARIS);
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testToString_String_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertNotNull(test);
    }

    public void testToString_String_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertNotNull(test);
    }

    public void testToString_String_String_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertNotNull(test);
    }

    public void testToString_String_String_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertNotNull(test);
    }

    public void testToString_String_String_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertNotNull(test);
    }

    public void testToString_String_String_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertNotNull(test);
    }

    public void testToString_String_String_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertNotNull(test);
    }

    public void testToString_DTFormatter_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertNotNull(test);
    }

    public void testToString_DTFormatter_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertNotNull(test);
    }

    public void testToInstant_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Instant result = test.toInstant();
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTime();
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToDateTime_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTime();
        assertNotNull(result);
    }

    public void testToDateTimeISO_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTimeISO();
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTimeISO_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTimeISO();
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTimeISO_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTimeISO();
        assertNotNull(result);
    }

    public void testToDateTime_DateTimeZone_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(ISOChronology.getInstanceUTC(), result.getChronology());
    }

    public void testToDateTime_DateTimeZone_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_DateTimeZone_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertNotSame(LONDON, PARIS);
    }

    public void testToDateTime_DateTimeZone_7_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        assertNotNull(result);
    }

    public void testToDateTime_DateTimeZone_8_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_DateTimeZone_10_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(TEST_TIME1, result.getMillis());
    }

    public void testToDateTime_Chronology_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_Chronology_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertNotNull(result);
    }

    public void testToDateTime_Chronology_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertEquals(TEST_TIME1, result.getMillis());
    }

    public void testToDateTime_Chronology_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertNotNull(result);
    }

    public void testToDateTime_Chronology_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(TEST_TIME1, result.getMillis());
    }

    public void testToDateTime_Chronology_6_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        assertNotNull(result);
    }

    public void testToDateTime_Chronology_7_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(TEST_TIME1, result.getMillis());
    }

    public void testToMutableDateTime_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        assertNotNull(result);
    }

    public void testToMutableDateTime_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToMutableDateTime_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        assertNotNull(result);
    }

    public void testToMutableDateTimeISO_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertEquals(ISOChronology.getInstanceUTC(), result.getChronology());
    }

    public void testToMutableDateTimeISO_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTimeISO_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertNotNull(result);
    }

    public void testToMutableDateTime_DateTimeZone_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals("2013-06-06_00:59:59.999+01:00:Europe/London", result.toString());
    }

    public void testToMutableDateTime_DateTimeZone_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_6_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertNotNull(result);
    }

    public void testToMutableDateTime_DateTimeZone_8_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_11_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_Chronology_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertNotNull(result);
    }

    public void testToMutableDateTime_Chronology_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_Chronology_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertNotNull(result);
    }

    public void testToMutableDateTime_Chronology_4_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertNotNull(result);
    }

    public void testToMutableDateTime_Chronology_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_Chronology_6_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertNotNull(result);
    }

    public void testToMutableDateTime_Chronology_8_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(TEST_TIME1, result.getMillis());
    }

    public void testToMutableDateTime_Chronology_9_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        assertNotNull(result);
    }

    public void testToMutableDateTime_Chronology_11_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDate_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Date result = test.toDate();
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToCalendar_Locale_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToCalendar_Locale_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToCalendar_Locale_5_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(Locale.UK);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToGregorianCalendar_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToGregorianCalendar_3_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toGregorianCalendar();
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testClone_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = (MutableDateTime) test.clone();
        assertNotSame(test, result);
    }

    public void testClone_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = (MutableDateTime) test.clone();
        assertNotSame(test, result);
    }

    public void testCopy_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.copy();
        assertNotNull(result);
    }

    public void testCopy_2_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.copy();
        assertNotNull(result);
    }

    public void testRounding1_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        assertEquals("2002-06-09T05:00:07.000000080-+0100:+0100", test.toString());
    }

    public void testRounding1_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        assertEquals(9223372036854775807L, test.getRoundingMode());
    }

    public void testRounding1_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        assertEquals("2002-06-09T05:00:00.000000000", test.toString());
    }

    public void testRounding2_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals("2002-06-09T05:00:07.000000080-+0100:+0100", test.toString());
    }

    public void testRounding2_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals(MutableDateTime.ROUND_CEILING, test.getRoundingMode());
    }

    public void testRounding2_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals(2002, test.getYear());
    }

    public void testRounding3_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        assertEquals("2002-06-09T05:00:07.000000080-+0100:+0100", test.toString());
    }

    public void testRounding3_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        assertEquals(MutableDateTime.ROUND_HALF_CEILING, test.getRoundingMode());
    }

    public void testRounding3_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
// incorrect assertion         assertEquals(false, test.isLeapSecondMode());
    }

    public void testRounding3_4_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        assertEquals(5, test.hourOfDay().get());
    }

    public void testRounding4_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        assertEquals("2002-06-09T05:00:07.000000080-+0100:+0100", test.toString());
    }

    public void testRounding4_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        assertEquals(MutableDateTime.ROUND_HALF_FLOOR, test.getRoundingMode());
    }

    public void testRounding4_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
// incorrect assertion         assertEquals(false, test.isLeapSecondMode());
    }

    public void testRounding4_4_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        assertEquals(5, test.hourOfDay().get());
    }

    public void testRounding5_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals("2002-06-09T05:00:07.000000080-+0100:+0100", test.toString());
    }

    public void testRounding5_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals(MutableDateTime.ROUND_HALF_EVEN, test.getRoundingMode());
    }

    public void testRounding5_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
// incorrect assertion         assertEquals(false, test.isLeapSecondMode());
    }

    public void testRounding5_4_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals(5, test.hourOfDay().get());
    }

    public void testRounding5_5_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        
        test = new MutableDateTime(2002, 6, 9, 4, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals(4, test.getHourOfDay());
    }

    public void testRounding6_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals("2002-06-09T05:00:07.000000080-+0100:+0100", test.toString());
    }

    public void testRounding6_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals(MutableDateTime.ROUND_NONE, test.getRoundingMode());
    }

    public void testRounding6_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals(DateTimeFieldType.hourOfDay(), test.getRoundingField());
    }

    public void testRounding8_1_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        assertEquals(0, test.getRoundingMode());
    }

    public void testRounding8_2_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        assertNull(test.getRoundingField());
    }

    public void testRounding8_3_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals(MutableDateTime.ROUND_CEILING, test.getRoundingMode());
    }

    public void testRounding8_4_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals(2002, test.getYear());
    }

    public void testRounding8_5_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals(MutableDateTime.ROUND_NONE, test.getRoundingMode());
    }

    public void testRounding8_6_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals(ISOChronology.getInstance().hourOfDay(), test.getRoundingField());
    }

    public void testRounding8_7_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        
        test.setRounding(null, -1);
        assertEquals(MutableDateTime.ROUND_NONE, test.getRoundingMode());
    }

    public void testRounding8_8_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        
        test.setRounding(null, -1);
        assertNull(test.getRoundingField());
    }

    public void testRounding8_9_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        
        test.setRounding(null, -1);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        assertEquals(MutableDateTime.ROUND_NONE, test.getRoundingMode());
    }

    public void testRounding8_11_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        
        test.setRounding(null, -1);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        
        test.setRounding(null);
        assertEquals(MutableDateTime.ROUND_NONE, test.getRoundingMode());
    }

    public void testRounding8_12_oe() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        
        test.setRounding(null, -1);
        
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        
        test.setRounding(null);
        assertEquals(ISOChronology.getInstance().hourOfDay(), test.getRoundingField());
    }

    public void testProperty_1_oe() {
        MutableDateTime test = new MutableDateTime();
        assertNotNull(test.year());
    }

    public void testProperty_2_oe() {
        MutableDateTime test = new MutableDateTime();
        assertEquals(1, test.dayOfWeek().get());
    }

    public void testProperty_3_oe() {
        MutableDateTime test = new MutableDateTime();
        assertEquals("secondOfMinute", test.secondOfMinute().getName());
    }

    public void testToDateTime_DateTimeZone_1_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_DateTimeZone_6_oe() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(1372706400000L, result.getMillis());
    }

}
