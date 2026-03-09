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
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.UnsupportedDateTimeField;
import org.joda.time.field.UnsupportedDurationField;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for DateMidnight.
 *
 * @author Stephen Colebourne
 */
@SuppressWarnings("deprecation")
public class TestDateMidnight_Basics_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone NEWYORK = DateTimeZone.forID("America/New_York");

    // the default time zone is set to LONDON in setUp()
    // we have to hard code LONDON here (instead of ISOChronology.getInstance() etc.)
    // as TestAll sets up a different time zone for better all-round testing
    private static final ISOChronology ISO_DEFAULT = ISOChronology.getInstance(LONDON);
    private static final ISOChronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    private static final GJChronology GJ_DEFAULT = GJChronology.getInstance(LONDON);
    private static final GregorianChronology GREGORIAN_DEFAULT = GregorianChronology.getInstance(LONDON);
    private static final GregorianChronology GREGORIAN_PARIS = GregorianChronology.getInstance(PARIS);
    private static final BuddhistChronology BUDDHIST_DEFAULT = BuddhistChronology.getInstance(LONDON);
    private static final CopticChronology COPTIC_DEFAULT = CopticChronology.getInstance(LONDON);

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
//    private long TEST_TIME_NOW_PARIS =
//            TEST_TIME_NOW_UTC - 2*DateTimeConstants.MILLIS_PER_HOUR;
            
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
    
    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateMidnight_Basics_OE25Dev.class);
    }

    public TestDateMidnight_Basics_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW_UTC);
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
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW_UTC).toString());
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1_UTC).toString());
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2_UTC).toString());
    }

    //-----------------------------------------------------------------------
    public void testGet_DateTimeField() {
        DateMidnight test = new DateMidnight();
        assertEquals(1,test.get(ISO_DEFAULT.era()));
        assertEquals(20,test.get(ISO_DEFAULT.centuryOfEra()));
        assertEquals(2,test.get(ISO_DEFAULT.yearOfCentury()));
        assertEquals(2002,test.get(ISO_DEFAULT.yearOfEra()));
        assertEquals(2002,test.get(ISO_DEFAULT.year()));
        assertEquals(6,test.get(ISO_DEFAULT.monthOfYear()));
        assertEquals(9,test.get(ISO_DEFAULT.dayOfMonth()));
        assertEquals(2002,test.get(ISO_DEFAULT.weekyear()));
        assertEquals(23,test.get(ISO_DEFAULT.weekOfWeekyear()));
        assertEquals(7,test.get(ISO_DEFAULT.dayOfWeek()));
        assertEquals(160,test.get(ISO_DEFAULT.dayOfYear()));
        assertEquals(0,test.get(ISO_DEFAULT.halfdayOfDay()));
        assertEquals(0,test.get(ISO_DEFAULT.hourOfHalfday()));
        assertEquals(24,test.get(ISO_DEFAULT.clockhourOfDay()));
        assertEquals(12,test.get(ISO_DEFAULT.clockhourOfHalfday()));
        assertEquals(0,test.get(ISO_DEFAULT.hourOfDay()));
        assertEquals(0,test.get(ISO_DEFAULT.minuteOfHour()));
        assertEquals(0,test.get(ISO_DEFAULT.minuteOfDay()));
        assertEquals(0,test.get(ISO_DEFAULT.secondOfMinute()));
        assertEquals(0,test.get(ISO_DEFAULT.secondOfDay()));
        assertEquals(0,test.get(ISO_DEFAULT.millisOfSecond()));
        assertEquals(0,test.get(ISO_DEFAULT.millisOfDay()));
        try {
            test.get((DateTimeField) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testGet_DateTimeFieldType() {
        DateMidnight test = new DateMidnight();
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
        assertEquals(0,test.get(DateTimeFieldType.hourOfHalfday()));
        assertEquals(24,test.get(DateTimeFieldType.clockhourOfDay()));
        assertEquals(12,test.get(DateTimeFieldType.clockhourOfHalfday()));
        assertEquals(0,test.get(DateTimeFieldType.hourOfDay()));
        assertEquals(0,test.get(DateTimeFieldType.minuteOfHour()));
        assertEquals(0,test.get(DateTimeFieldType.minuteOfDay()));
        assertEquals(0,test.get(DateTimeFieldType.secondOfMinute()));
        assertEquals(0,test.get(DateTimeFieldType.secondOfDay()));
        assertEquals(0,test.get(DateTimeFieldType.millisOfSecond()));
        assertEquals(0,test.get(DateTimeFieldType.millisOfDay()));
        try {
            test.get((DateTimeFieldType) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testGetters() {
        DateMidnight test = new DateMidnight();
        
        assertEquals(ISO_DEFAULT,test.getChronology());
        assertEquals(LONDON,test.getZone());
        assertEquals(TEST_TIME_NOW_LONDON,test.getMillis());
        
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
        assertEquals(0,test.getHourOfDay());
        assertEquals(0,test.getMinuteOfHour());
        assertEquals(0,test.getMinuteOfDay());
        assertEquals(0,test.getSecondOfMinute());
        assertEquals(0,test.getSecondOfDay());
        assertEquals(0,test.getMillisOfSecond());
        assertEquals(0,test.getMillisOfDay());
    }

    public void testWithers() {
        DateMidnight test = new DateMidnight(1970, 6, 9, GJ_DEFAULT);
        check(test.withYear(2000), 2000, 6, 9);
        check(test.withMonthOfYear(2), 1970, 2, 9);
        check(test.withDayOfMonth(2), 1970, 6, 2);
        check(test.withDayOfYear(6), 1970, 1, 6);
        check(test.withDayOfWeek(6), 1970, 6, 13);
        check(test.withWeekOfWeekyear(6), 1970, 2, 3);
        check(test.withWeekyear(1971), 1971, 6, 15);
        check(test.withYearOfCentury(60), 1960, 6, 9);
        check(test.withCenturyOfEra(21), 2070, 6, 9);
        check(test.withYearOfEra(1066), 1066, 6, 9);
        check(test.withEra(DateTimeConstants.BC), -1970, 6, 9);
        
        try {
            test.withMonthOfYear(0);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.withMonthOfYear(13);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testEqualsHashCode() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(true,test1.equals(test2));
        assertEquals(true,test2.equals(test1));
        assertEquals(true,test1.equals(test1));
        assertEquals(true,test2.equals(test2));
        assertEquals(true,test1.hashCode()== test2.hashCode());
        assertEquals(true,test1.hashCode()== test1.hashCode());
        assertEquals(true,test2.hashCode()== test2.hashCode());
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(false,test1.equals(test3));
        assertEquals(false,test2.equals(test3));
        assertEquals(false,test3.equals(test1));
        assertEquals(false,test3.equals(test2));
        assertEquals(false,test1.hashCode()== test3.hashCode());
        assertEquals(false,test2.hashCode()== test3.hashCode());
        
        assertEquals(false,test1.equals("Hello"));
        assertEquals(true,test1.equals(new MockInstant()));
        assertEquals(false,test1.equals(new DateMidnight(TEST_TIME1_UTC,GREGORIAN_DEFAULT)));
    }
    
    class MockInstant extends AbstractInstant {
        @Override
        public String toString() {
            return null;
        }
        public long getMillis() {
            return TEST_TIME1_LONDON;
        }
        public Chronology getChronology() {
            return ISO_DEFAULT;
        }
    }

    public void testCompareTo() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(0,test1.compareTo(test1a));
        assertEquals(0,test1a.compareTo(test1));
        assertEquals(0,test1.compareTo(test1));
        assertEquals(0,test1a.compareTo(test1a));
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(-1,test1.compareTo(test2));
        assertEquals(+1,test2.compareTo(test1));
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(-1,test1.compareTo(test3));
        assertEquals(+1,test3.compareTo(test1));
        assertEquals(-1,test3.compareTo(test2));// midnight paris before london assertEquals(+1,test2.compareTo(new MockInstant()));
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
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(true,test1.isEqual(test1a));
        assertEquals(true,test1a.isEqual(test1));
        assertEquals(true,test1.isEqual(test1));
        assertEquals(true,test1a.isEqual(test1a));
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(false,test1.isEqual(test2));
        assertEquals(false,test2.isEqual(test1));
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(false,test1.isEqual(test3));
        assertEquals(false,test3.isEqual(test1));
        assertEquals(false,test3.isEqual(test2));// midnight paris before london assertEquals(false,test2.isEqual(new MockInstant()));
        assertEquals(true,test1.isEqual(new MockInstant()));
        
        assertEquals(false,new DateMidnight(TEST_TIME_NOW_UTC + DateTimeConstants.MILLIS_PER_DAY,DateTimeZone.UTC).isEqual(null));
        assertEquals(true,new DateMidnight(TEST_TIME_NOW_UTC,DateTimeZone.UTC).isEqual(null));
        assertEquals(false,new DateMidnight(TEST_TIME_NOW_UTC - DateTimeConstants.MILLIS_PER_DAY,DateTimeZone.UTC).isEqual(null));
        
        assertEquals(false,new DateMidnight(2004,6,9).isEqual(new DateTime(2004,6,8,23,59,59,999)));
        assertEquals(true,new DateMidnight(2004,6,9).isEqual(new DateTime(2004,6,9,0,0,0,0)));
        assertEquals(false,new DateMidnight(2004,6,9).isEqual(new DateTime(2004,6,9,0,0,0,1)));
    }
    
    public void testIsBefore() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(false,test1.isBefore(test1a));
        assertEquals(false,test1a.isBefore(test1));
        assertEquals(false,test1.isBefore(test1));
        assertEquals(false,test1a.isBefore(test1a));
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(true,test1.isBefore(test2));
        assertEquals(false,test2.isBefore(test1));
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(true,test1.isBefore(test3));
        assertEquals(false,test3.isBefore(test1));
        assertEquals(true,test3.isBefore(test2));// midnight paris before london assertEquals(false,test2.isBefore(new MockInstant()));
        assertEquals(false,test1.isBefore(new MockInstant()));
        
        assertEquals(false,new DateMidnight(TEST_TIME_NOW_UTC + DateTimeConstants.MILLIS_PER_DAY,DateTimeZone.UTC).isBefore(null));
        assertEquals(false,new DateMidnight(TEST_TIME_NOW_UTC,DateTimeZone.UTC).isBefore(null));
        assertEquals(true,new DateMidnight(TEST_TIME_NOW_UTC - DateTimeConstants.MILLIS_PER_DAY,DateTimeZone.UTC).isBefore(null));
        
        assertEquals(false,new DateMidnight(2004,6,9).isBefore(new DateTime(2004,6,8,23,59,59,999)));
        assertEquals(false,new DateMidnight(2004,6,9).isBefore(new DateTime(2004,6,9,0,0,0,0)));
        assertEquals(true,new DateMidnight(2004,6,9).isBefore(new DateTime(2004,6,9,0,0,0,1)));
    }
    
    public void testIsAfter() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(false,test1.isAfter(test1a));
        assertEquals(false,test1a.isAfter(test1));
        assertEquals(false,test1.isAfter(test1));
        assertEquals(false,test1a.isAfter(test1a));
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(false,test1.isAfter(test2));
        assertEquals(true,test2.isAfter(test1));
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(false,test1.isAfter(test3));
        assertEquals(true,test3.isAfter(test1));
        assertEquals(false,test3.isAfter(test2));// midnight paris before london assertEquals(true,test2.isAfter(new MockInstant()));
        assertEquals(false,test1.isAfter(new MockInstant()));
        
        assertEquals(true,new DateMidnight(TEST_TIME_NOW_UTC + DateTimeConstants.MILLIS_PER_DAY,DateTimeZone.UTC).isAfter(null));
        assertEquals(false,new DateMidnight(TEST_TIME_NOW_UTC,DateTimeZone.UTC).isAfter(null));
        assertEquals(false,new DateMidnight(TEST_TIME_NOW_UTC - DateTimeConstants.MILLIS_PER_DAY,DateTimeZone.UTC).isAfter(null));
        
        assertEquals(true,new DateMidnight(2004,6,9).isAfter(new DateTime(2004,6,8,23,59,59,999)));
        assertEquals(false,new DateMidnight(2004,6,9).isAfter(new DateTime(2004,6,9,0,0,0,0)));
        assertEquals(false,new DateMidnight(2004,6,9).isAfter(new DateTime(2004,6,9,0,0,0,1)));
    }
    
    //-----------------------------------------------------------------------
    public void testSerialization() throws Exception {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DateMidnight result = (DateMidnight) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

    //-----------------------------------------------------------------------
    public void testToString() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertEquals("2002-06-09T00:00:00.000+01:00",test.toString());
        
        test = new DateMidnight(TEST_TIME_NOW_UTC, PARIS);
        assertEquals("2002-06-09T00:00:00.000+02:00",test.toString());
        
        test = new DateMidnight(TEST_TIME_NOW_UTC, NEWYORK);
        assertEquals("2002-06-08T00:00:00.000-04:00",test.toString());  // the 8th
    }

    public void testToString_String() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertEquals("2002 00",test.toString("yyyy HH"));
        assertEquals("2002-06-09T00:00:00.000+01:00",test.toString((String)null));
    }

    public void testToString_String_String() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertEquals("Sun 9/6",test.toString("EEE d/M",Locale.ENGLISH));
        assertEquals("dim. 9/6",test.toString("EEE d/M",Locale.FRENCH));
        assertEquals("2002-06-09T00:00:00.000+01:00",test.toString(null,Locale.ENGLISH));
        assertEquals("Sun 9/6",test.toString("EEE d/M",null));
        assertEquals("2002-06-09T00:00:00.000+01:00",test.toString(null,null));
    }

    public void testToString_DTFormatter() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertEquals("2002 00",test.toString(DateTimeFormat.forPattern("yyyy HH")));
        assertEquals("2002-06-09T00:00:00.000+01:00",test.toString((DateTimeFormatter)null));
    }

    //-----------------------------------------------------------------------
    public void testToInstant() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        Instant result = test.toInstant();
        assertEquals(TEST_TIME1_LONDON,result.getMillis());
    }

    public void testToDateTime() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        DateTime result = test.toDateTime();
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(TEST_TIME1_PARIS,result.getMillis());
        assertEquals(PARIS,result.getZone());
    }

    public void testToDateTimeISO() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        DateTime result = test.toDateTimeISO();
        assertSame(DateTime.class,result.getClass());
        assertSame(ISOChronology.class,result.getChronology().getClass());
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_PARIS,result.getChronology());
    }

    public void testToDateTime_DateTimeZone() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(TEST_TIME1_LONDON,result.getMillis());
        assertEquals(LONDON,result.getZone());

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(PARIS);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(TEST_TIME1_LONDON,result.getMillis());
        assertEquals(PARIS,result.getZone());

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(TEST_TIME1_PARIS,result.getMillis());
        assertEquals(LONDON,result.getZone());

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(TEST_TIME1_LONDON,result.getMillis());
        assertEquals(LONDON,result.getZone());
    }

    public void testToDateTime_Chronology() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(TEST_TIME1_LONDON,result.getMillis());
        assertEquals(LONDON,result.getZone());

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(GREGORIAN_PARIS);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(TEST_TIME1_LONDON,result.getMillis());
        assertEquals(GREGORIAN_PARIS,result.getChronology());

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toDateTime((Chronology) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(TEST_TIME1_PARIS,result.getMillis());
        assertEquals(ISO_DEFAULT,result.getChronology());

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime((Chronology) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(TEST_TIME1_LONDON,result.getMillis());
        assertEquals(ISO_DEFAULT,result.getChronology());
    }

    public void testToMutableDateTime() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_PARIS,result.getChronology());
    }

    public void testToMutableDateTimeISO() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertSame(MutableDateTime.class,result.getClass());
        assertSame(ISOChronology.class,result.getChronology().getClass());
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_PARIS,result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_DEFAULT,result.getChronology());

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(PARIS);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_PARIS,result.getChronology());

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_DEFAULT,result.getChronology());

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_DEFAULT,result.getChronology());
    }

    public void testToMutableDateTime_Chronology() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_DEFAULT,result.getChronology());

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(GREGORIAN_PARIS);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(GREGORIAN_PARIS,result.getChronology());

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_DEFAULT,result.getChronology());

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_DEFAULT,result.getChronology());
    }

    public void testToDate() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        Date result = test.toDate();
        assertEquals(test.getMillis(),result.getTime());
    }

    public void testToCalendar_Locale() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        Calendar result = test.toCalendar(null);
        assertEquals(test.getMillis(),result.getTime().getTime());
        assertEquals(TimeZone.getTimeZone("Europe/London"),result.getTimeZone());

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toCalendar(null);
        assertEquals(test.getMillis(),result.getTime().getTime());
        assertEquals(TimeZone.getTimeZone("Europe/Paris"),result.getTimeZone());

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toCalendar(Locale.UK);
        assertEquals(test.getMillis(),result.getTime().getTime());
        assertEquals(TimeZone.getTimeZone("Europe/Paris"),result.getTimeZone());
    }

    public void testToGregorianCalendar() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        GregorianCalendar result = test.toGregorianCalendar();
        assertEquals(test.getMillis(),result.getTime().getTime());
        assertEquals(TimeZone.getTimeZone("Europe/London"),result.getTimeZone());

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toGregorianCalendar();
        assertEquals(test.getMillis(),result.getTime().getTime());
        assertEquals(TimeZone.getTimeZone("Europe/Paris"),result.getTimeZone());
    }

    //-----------------------------------------------------------------------
    public void testToYearMonthDay() {
        DateMidnight base = new DateMidnight(TEST_TIME1_UTC, COPTIC_DEFAULT);
        YearMonthDay test = base.toYearMonthDay();
        assertEquals(new YearMonthDay(TEST_TIME1_UTC,COPTIC_DEFAULT),test);
    }

    public void testToLocalDate() {
        DateMidnight base = new DateMidnight(TEST_TIME1_UTC, COPTIC_DEFAULT);
        LocalDate test = base.toLocalDate();
        assertEquals(new LocalDate(TEST_TIME1_UTC,COPTIC_DEFAULT),test);
    }

    public void testToInterval() {
        DateMidnight base = new DateMidnight(TEST_TIME1_UTC, COPTIC_DEFAULT);
        Interval test = base.toInterval();
        DateMidnight end = base.plus(Period.days(1));
        assertEquals(new Interval(base,end),test);
    }

    //-----------------------------------------------------------------------
    public void testWithMillis_long() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withMillis(TEST_TIME2_UTC);
        assertEquals(TEST_TIME2_LONDON,result.getMillis());
        assertEquals(test.getChronology(),result.getChronology());
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withMillis(TEST_TIME2_UTC);
        assertEquals(TEST_TIME2_PARIS,result.getMillis());
        assertEquals(test.getChronology(),result.getChronology());
        
        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.withMillis(TEST_TIME1_UTC);
        assertSame(test,result);
    }

    public void testWithChronology_Chronology() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withChronology(GREGORIAN_PARIS);
        assertEquals(TEST_TIME1_LONDON,test.getMillis());
        assertEquals(TEST_TIME1_PARIS,result.getMillis());
        assertEquals(GREGORIAN_PARIS,result.getChronology());
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withChronology(null);
        assertEquals(TEST_TIME1_PARIS,test.getMillis());
        // midnight Paris is previous day in London
        assertEquals(TEST_TIME1_LONDON - DateTimeConstants.MILLIS_PER_DAY,result.getMillis());
        assertEquals(ISO_DEFAULT,result.getChronology());
        
        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.withChronology(null);
        assertEquals(test.getMillis(),result.getMillis());
        assertEquals(ISO_DEFAULT,result.getChronology());
        
        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.withChronology(ISO_DEFAULT);
        assertSame(test,result);
    }

    public void testWithZoneRetainFields_DateTimeZone() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withZoneRetainFields(PARIS);
        assertEquals(TEST_TIME1_LONDON,test.getMillis());
        assertEquals(TEST_TIME1_PARIS,result.getMillis());
        assertEquals(ISO_PARIS,result.getChronology());
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withZoneRetainFields(null);
        assertEquals(TEST_TIME1_PARIS,test.getMillis());
        assertEquals(TEST_TIME1_LONDON,result.getMillis());
        assertEquals(GREGORIAN_DEFAULT,result.getChronology());
        
        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.withZoneRetainFields(LONDON);
        assertSame(test,result);
        
        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.withZoneRetainFields(null);
        assertSame(test,result);
        
        test = new DateMidnight(TEST_TIME1_UTC, new MockNullZoneChronology());
        result = test.withZoneRetainFields(LONDON);
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testWithFields_RPartial() {
        DateMidnight test = new DateMidnight(2004, 5, 6);
        DateMidnight result = test.withFields(new YearMonthDay(2003, 4, 5));
        DateMidnight expected = new DateMidnight(2003, 4, 5);
        assertEquals(expected,result);
        
        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.withFields(null);
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testWithField1() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(new DateMidnight(2004,6,9),test);
        assertEquals(new DateMidnight(2006,6,9),result);
    }

    public void testWithField2() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        try {
            test.withField(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testWithFieldAdded1() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(new DateMidnight(2004,6,9),test);
        assertEquals(new DateMidnight(2010,6,9),result);
    }

    public void testWithFieldAdded2() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        try {
            test.withFieldAdded(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded3() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        try {
            test.withFieldAdded(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded4() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testWithDurationAdded_long_int() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(123456789L, 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.withDurationAdded(123456789L, 0);
        assertSame(test,result);
        
        result = test.withDurationAdded(123456789L, 2);
        expected = new DateMidnight(test.getMillis() + (2L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.withDurationAdded(123456789L, -3);
        expected = new DateMidnight(test.getMillis() - (3L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(expected,result);
    }
    
    //-----------------------------------------------------------------------
    public void testWithDurationAdded_RD_int() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(new Duration(123456789L), 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.withDurationAdded(null, 1);
        assertSame(test,result);
        
        result = test.withDurationAdded(new Duration(123456789L), 0);
        assertSame(test,result);
        
        result = test.withDurationAdded(new Duration(123456789L), 2);
        expected = new DateMidnight(test.getMillis() + (2L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.withDurationAdded(new Duration(123456789L), -3);
        expected = new DateMidnight(test.getMillis() - (3L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(expected,result);
    }

    //-----------------------------------------------------------------------
    public void testWithDurationAdded_RP_int() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateMidnight expected = new DateMidnight(2003, 7, 28, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.withPeriodAdded(null, 1);
        assertSame(test,result);
        
        result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 0);
        assertSame(test,result);
        
        result = test.withPeriodAdded(new Period(1, 2, 0, 4, 5, 6, 7, 8), 3);
        expected = new DateMidnight(2005, 11, 15, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.withPeriodAdded(new Period(1, 2, 0, 1, 1, 2, 3, 4), -1);
        expected = new DateMidnight(2001, 3, 1, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
    }

    //-----------------------------------------------------------------------    
    public void testPlus_long() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.plus(123456789L);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
    }
    
    public void testPlus_RD() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.plus(new Duration(123456789L));
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.plus((ReadableDuration) null);
        assertSame(test,result);
    }

    public void testPlus_RP() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        DateMidnight expected = new DateMidnight(2003, 7, 28, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.plus((ReadablePeriod) null);
        assertSame(test,result);
    }

    public void testPlusYears_int() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusYears(1);
        DateMidnight expected = new DateMidnight(2003, 5, 3, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.plusYears(0);
        assertSame(test,result);
    }

    public void testPlusMonths_int() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusMonths(1);
        DateMidnight expected = new DateMidnight(2002, 6, 3, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.plusMonths(0);
        assertSame(test,result);
    }

    public void testPlusWeeks_int() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusWeeks(1);
        DateMidnight expected = new DateMidnight(2002, 5, 10, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.plusWeeks(0);
        assertSame(test,result);
    }

    public void testPlusDays_int() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusDays(1);
        DateMidnight expected = new DateMidnight(2002, 5, 4, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.plusDays(0);
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------    
    public void testMinus_long() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.minus(123456789L);
        DateMidnight expected = new DateMidnight(test.getMillis() - 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
    }

    public void testMinus_RD() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.minus(new Duration(123456789L));
        DateMidnight expected = new DateMidnight(test.getMillis() - 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.minus((ReadableDuration) null);
        assertSame(test,result);
    }

    public void testMinus_RP() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        DateMidnight expected = new DateMidnight(2001, 3, 25, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.minus((ReadablePeriod) null);
        assertSame(test,result);
    }

    public void testMinusYears_int() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusYears(1);
        DateMidnight expected = new DateMidnight(2001, 5, 3, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.minusYears(0);
        assertSame(test,result);
    }

    public void testMinusMonths_int() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusMonths(1);
        DateMidnight expected = new DateMidnight(2002, 4, 3, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.minusMonths(0);
        assertSame(test,result);
    }

    public void testMinusWeeks_int() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusWeeks(1);
        DateMidnight expected = new DateMidnight(2002, 4, 26, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.minusWeeks(0);
        assertSame(test,result);
    }

    public void testMinusDays_int() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusDays(1);
        DateMidnight expected = new DateMidnight(2002, 5, 2, BUDDHIST_DEFAULT);
        assertEquals(expected,result);
        
        result = test.minusDays(0);
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testProperty() {
        DateMidnight test = new DateMidnight();
        assertEquals(test.year(),test.property(DateTimeFieldType.year()));
        assertEquals(test.dayOfWeek(),test.property(DateTimeFieldType.dayOfWeek()));
        assertEquals(test.weekOfWeekyear(),test.property(DateTimeFieldType.weekOfWeekyear()));
        assertEquals(test.property(DateTimeFieldType.millisOfSecond()),test.property(DateTimeFieldType.millisOfSecond()));
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

    //-----------------------------------------------------------------------
    private void check(DateMidnight test, int year, int month, int day) {
        assertEquals(year,test.getYear());
        assertEquals(month,test.getMonthOfYear());
        assertEquals(day,test.getDayOfMonth());
    }

    public void testTest_1_oe() {
        Object a = new Instant(TEST_TIME_NOW_UTC).toString();
        assertEquals("2013-07-01T23:59:59.000Z", a);
    }

    public void testTest_2_oe() {
        Object a = new Instant(TEST_TIME1_UTC).toString();
        assertEquals("2013-06-29T08:00:00.000Z", a);
    }

    public void testTest_3_oe() {
        Object a = new Instant(TEST_TIME2_UTC).toString();
        assertEquals("2013-06-06T06:06:06.000Z", a);
    }

    public void testGet_DateTimeField_1_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_2_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_3_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_4_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_5_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_6_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_7_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_8_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_9_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_10_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_11_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_12_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_13_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_14_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_15_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_16_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_17_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_18_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_19_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_20_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_21_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeField_22_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_1_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_2_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_3_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_4_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_5_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_6_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_7_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_8_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_9_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_10_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_11_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_12_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_13_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_14_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_15_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_16_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_17_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_18_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_19_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_20_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_21_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_DateTimeFieldType_22_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGetters_1_oe() {
        DateMidnight test = new DateMidnight();
        
        assertEquals(ISOChronology.getInstanceUTC(), test.getChronology());
    }

    public void testGetters_4_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(1, test.getEra());
    }

    public void testGetters_5_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(1, test.getCenturyOfEra());
    }

    public void testGetters_6_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(1, test.getYearOfCentury());
    }

    public void testGetters_7_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(1, test.getYearOfEra());
    }

    public void testGetters_9_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(1, test.getMonthOfYear());
    }

    public void testGetters_10_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(1, test.getDayOfMonth());
    }

    public void testGetters_11_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(2013, test.getWeekyear());
    }

    public void testGetters_12_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(1, test.getWeekOfWeekyear());
    }

    public void testGetters_13_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(2, test.getDayOfWeek());
    }

    public void testGetters_14_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(1, test.getDayOfYear());
    }

    public void testGetters_15_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(0, test.getHourOfDay());
    }

    public void testGetters_16_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testGetters_17_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(0, test.getMinuteOfDay());
    }

    public void testGetters_18_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testGetters_19_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(0, test.getSecondOfDay());
    }

    public void testGetters_20_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testGetters_21_oe() {
        DateMidnight test = new DateMidnight();
        
        
        assertEquals(0, test.getMillisOfDay());
    }

    public void testEqualsHashCode_1_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_3_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_4_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_6_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_7_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_8_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_9_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_10_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_11_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_13_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_14_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC);
        
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_15_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC);
        
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_16_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test2 = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC);
        
        assertEquals(true, test1.equals(test2));
    }

    public void testCompareTo_7_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_8_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_9_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_10_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testIsEqual_6_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_7_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_8_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_9_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_11_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_12_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        assertEquals(true, test1a.isEqual(test1a));
    }

    public void testIsEqual_13_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_14_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        
        assertEquals(true, test1a.isEqual(test1a));
    }

    public void testIsEqual_16_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsBefore_1_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_3_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_4_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_5_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_7_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_8_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_9_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_10_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_11_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_12_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_13_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_14_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_15_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsBefore_16_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        
        assertEquals(false, test1.isBefore(TEST_TIME1_UTC));
    }

    public void testIsAfter_1_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_2_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_3_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_4_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_5_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_6_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_7_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_8_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_9_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_10_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_11_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_12_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_13_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_14_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_15_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testIsAfter_16_oe() {
        DateMidnight test1 = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight test1a = new DateMidnight(TEST_TIME1_UTC);
        
        DateMidnight test2 = new DateMidnight(TEST_TIME2_UTC);
        
        DateMidnight test3 = new DateMidnight(TEST_TIME2_UTC, GREGORIAN_PARIS);
        
        
        assertEquals(false, test1.isAfter(TEST_TIME1_UTC));
    }

    public void testSerialization_1_oe() throws Exception {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DateMidnight result = (DateMidnight) ois.readObject();
        ois.close();
        
        assertNotNull(result);
    }

    public void testToString_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertNotNull(test);
    }

    public void testToString_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        
        test = new DateMidnight(TEST_TIME_NOW_UTC, PARIS);
        assertEquals(1372664400000L, test.getMillis());
    }

    public void testToString_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        
        test = new DateMidnight(TEST_TIME_NOW_UTC, PARIS);
        
        test = new DateMidnight(TEST_TIME_NOW_UTC, NEWYORK);
        assertEquals(TEST_TIME_NOW_UTC, test.getMillis());
    }

    public void testToString_String_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertNotNull(test);
    }

    public void testToString_String_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertNotNull(test);
    }

    public void testToString_String_String_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertNotNull(test);
    }

    public void testToString_String_String_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertNotNull(test);
    }

    public void testToString_String_String_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertNotNull(test);
    }

    public void testToString_String_String_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertNotNull(test);
    }

    public void testToString_String_String_5_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertNotNull(test);
    }

    public void testToString_DTFormatter_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertNotNull(test);
    }

    public void testToString_DTFormatter_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW_UTC);
        assertNotNull(test);
    }

    public void testToInstant_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        Instant result = test.toInstant();
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        DateTime result = test.toDateTime();
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToDateTime_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        DateTime result = test.toDateTime();
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToDateTimeISO_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        DateTime result = test.toDateTimeISO();
        assertEquals("2013-07-01T00:00:00.000000000Z", result.toString());
    }

    public void testToDateTimeISO_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        DateTime result = test.toDateTimeISO();
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToDateTimeISO_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        DateTime result = test.toDateTimeISO();
        assertEquals("2013-06-04_00:00:00.000000000", result.toString());
    }

    public void testToDateTime_DateTimeZone_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_DateTimeZone_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);
        assertEquals("2013-06-06_00:00:00.000000000", result.toString());
    }

    public void testToDateTime_DateTimeZone_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_DateTimeZone_5_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_DateTimeZone_7_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toDateTime((DateTimeZone) null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_DateTimeZone_8_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toDateTime((DateTimeZone) null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_DateTimeZone_10_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toDateTime((DateTimeZone) null);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime((DateTimeZone) null);
// incorrect assertion         assertEquals(TEST_TIME1_MILLIS, test.getMillis());
    }

    public void testToDateTime_DateTimeZone_11_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toDateTime((DateTimeZone) null);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime((DateTimeZone) null);
// incorrect assertion         assertEquals(TEST_TIME1_MILLIS, test.getMillis());
    }

    public void testToDateTime_Chronology_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_Chronology_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_Chronology_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(GREGORIAN_PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_Chronology_5_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(GREGORIAN_PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_Chronology_6_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(GREGORIAN_PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_Chronology_7_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(GREGORIAN_PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toDateTime((Chronology) null);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToDateTime_Chronology_8_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(GREGORIAN_PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toDateTime((Chronology) null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_Chronology_9_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(GREGORIAN_PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toDateTime((Chronology) null);
        assertEquals(TEST_TIME1_UTC, result.getMillis());
    }

    public void testToDateTime_Chronology_10_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(GREGORIAN_PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toDateTime((Chronology) null);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime((Chronology) null);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToDateTime_Chronology_11_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(GREGORIAN_PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toDateTime((Chronology) null);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime((Chronology) null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_Chronology_12_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(GREGORIAN_PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toDateTime((Chronology) null);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime((Chronology) null);
        assertEquals(TEST_TIME1_UTC, result.getMillis());
    }

    public void testToMutableDateTime_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTimeISO_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTimeISO_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTimeISO_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals("2013-07-01T00:00:00.000000000", result.toString());
    }

    public void testToMutableDateTime_DateTimeZone_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_5_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_6_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_7_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_8_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals("2013-06-05_00:00:00.000000000", result.toString());
    }

    public void testToMutableDateTime_Chronology_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToMutableDateTime_Chronology_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_Chronology_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(GREGORIAN_PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_Chronology_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(GREGORIAN_PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_Chronology_5_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(GREGORIAN_PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToMutableDateTime_Chronology_6_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(GREGORIAN_PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToMutableDateTime_Chronology_7_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(GREGORIAN_PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toMutableDateTime((Chronology) null);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToMutableDateTime_Chronology_8_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime(GREGORIAN_PARIS);

        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.toMutableDateTime((Chronology) null);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(TEST_TIME1_UTC, result.getMillis());
    }

    public void testToDate_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        Date result = test.toDate();
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToCalendar_Locale_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        Calendar result = test.toCalendar(null);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToCalendar_Locale_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        Calendar result = test.toCalendar(null);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toCalendar(null);
        assertEquals(1372706400000L, result.getTimeInMillis());
    }

    public void testToCalendar_Locale_5_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        Calendar result = test.toCalendar(null);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toCalendar(null);

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toCalendar(Locale.UK);
// incorrect assertion         assertEquals(TEST_TIME1_MILLIS, test.getMillis());
    }

    public void testToGregorianCalendar_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        GregorianCalendar result = test.toGregorianCalendar();
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testToGregorianCalendar_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        GregorianCalendar result = test.toGregorianCalendar();

        test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        result = test.toGregorianCalendar();
// incorrect assertion         assertEquals(TEST_TIME1_MILLIS, test.getMillis());
    }

    public void testToYearMonthDay_1_oe() {
        DateMidnight base = new DateMidnight(TEST_TIME1_UTC, COPTIC_DEFAULT);
        YearMonthDay test = base.toYearMonthDay();
        assertEquals("2013-07-01", test.toString());
    }

    public void testToLocalDate_1_oe() {
        DateMidnight base = new DateMidnight(TEST_TIME1_UTC, COPTIC_DEFAULT);
        LocalDate test = base.toLocalDate();
// incorrect assertion         assertEquals(1372706400000L, test.getMillis());
    }

    public void testToInterval_1_oe() {
        DateMidnight base = new DateMidnight(TEST_TIME1_UTC, COPTIC_DEFAULT);
        Interval test = base.toInterval();
        DateMidnight end = base.plus(Period.days(1));
        assertEquals(false, end.isBefore(base));
    }

    public void testWithMillis_long_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withMillis(TEST_TIME2_UTC);
        assertEquals(TEST_TIME2_UTC, result.getMillis());
    }

    public void testWithMillis_long_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withMillis(TEST_TIME2_UTC);
        assertEquals(ISOChronology.getInstanceUTC(), result.getChronology());
    }

    public void testWithMillis_long_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withMillis(TEST_TIME2_UTC);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withMillis(TEST_TIME2_UTC);
        assertEquals(TEST_TIME2_UTC, result.getMillis());
    }

    public void testWithMillis_long_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withMillis(TEST_TIME2_UTC);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withMillis(TEST_TIME2_UTC);
        assertEquals(ISOChronology.getInstanceUTC(), result.getChronology());
    }

    public void testWithMillis_long_5_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withMillis(TEST_TIME2_UTC);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withMillis(TEST_TIME2_UTC);
        
        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.withMillis(TEST_TIME1_UTC);
        assertNotNull(result);
    }

    public void testWithChronology_Chronology_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withChronology(GREGORIAN_PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testWithChronology_Chronology_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withChronology(GREGORIAN_PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testWithChronology_Chronology_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withChronology(GREGORIAN_PARIS);
        assertEquals(GREGORIAN_PARIS, result.getChronology());
    }

    public void testWithChronology_Chronology_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withChronology(GREGORIAN_PARIS);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withChronology(null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testWithChronology_Chronology_5_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withChronology(GREGORIAN_PARIS);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withChronology(null);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testWithChronology_Chronology_6_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withChronology(GREGORIAN_PARIS);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withChronology(null);
        assertEquals(GREGORIAN_PARIS, result.getChronology());
    }

    public void testWithChronology_Chronology_7_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withChronology(GREGORIAN_PARIS);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withChronology(null);
        
        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.withChronology(null);
// incorrect assertion         assertEquals(TEST_TIME1_MILLIS, result.getMillis());
    }

    public void testWithChronology_Chronology_8_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withChronology(GREGORIAN_PARIS);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withChronology(null);
        
        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.withChronology(null);
        assertEquals(GREGORIAN_PARIS, result.getChronology());
    }

    public void testWithZoneRetainFields_DateTimeZone_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withZoneRetainFields(PARIS);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testWithZoneRetainFields_DateTimeZone_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withZoneRetainFields(PARIS);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testWithZoneRetainFields_DateTimeZone_5_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withZoneRetainFields(PARIS);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withZoneRetainFields(null);
// incorrect assertion         assertEquals(TEST_TIME1_MILLIS, result.getMillis());
    }

    public void testWithZoneRetainFields_DateTimeZone_6_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withZoneRetainFields(PARIS);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withZoneRetainFields(null);
        assertEquals(ISOChronology.getInstanceUTC(), result.getChronology());
    }

    public void testWithFields_RPartial_1_oe() {
        DateMidnight test = new DateMidnight(2004, 5, 6);
        DateMidnight result = test.withFields(new YearMonthDay(2003, 4, 5));
        DateMidnight expected = new DateMidnight(2003, 4, 5);
        assertNotNull(result);
    }

    public void testWithFields_RPartial_2_oe() {
        DateMidnight test = new DateMidnight(2004, 5, 6);
        DateMidnight result = test.withFields(new YearMonthDay(2003, 4, 5));
        DateMidnight expected = new DateMidnight(2003, 4, 5);
        
        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.withFields(null);
        assertNotNull(result);
    }

    public void testWithField1_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(2006, result.getYear());
    }

    public void testWithField1_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(2006, result.getYear());
    }

    public void testWithFieldAdded1_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(2010, result.getYear());
    }

    public void testWithFieldAdded1_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(2010, result.getYear());
    }

    public void testWithFieldAdded4_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertEquals(2004, test.getYear());
    }

    public void testWithDurationAdded_long_int_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(123456789L, 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testWithDurationAdded_long_int_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(123456789L, 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        
        result = test.withDurationAdded(123456789L, 0);
        assertNotNull(result);
    }

    public void testWithDurationAdded_long_int_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(123456789L, 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        
        result = test.withDurationAdded(123456789L, 0);
        
        result = test.withDurationAdded(123456789L, 2);
        expected = new DateMidnight(test.getMillis() + (2L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testWithDurationAdded_long_int_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(123456789L, 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        
        result = test.withDurationAdded(123456789L, 0);
        
        result = test.withDurationAdded(123456789L, 2);
        expected = new DateMidnight(test.getMillis() + (2L * 123456789L), BUDDHIST_DEFAULT);
        
        result = test.withDurationAdded(123456789L, -3);
        expected = new DateMidnight(test.getMillis() - (3L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testWithDurationAdded_RD_int_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(new Duration(123456789L), 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testWithDurationAdded_RD_int_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(new Duration(123456789L), 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        
        result = test.withDurationAdded(null, 1);
        assertNotNull(result);
    }

    public void testWithDurationAdded_RD_int_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(new Duration(123456789L), 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        
        result = test.withDurationAdded(null, 1);
        
        result = test.withDurationAdded(new Duration(123456789L), 0);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testWithDurationAdded_RD_int_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(new Duration(123456789L), 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        
        result = test.withDurationAdded(null, 1);
        
        result = test.withDurationAdded(new Duration(123456789L), 0);
        
        result = test.withDurationAdded(new Duration(123456789L), 2);
        expected = new DateMidnight(test.getMillis() + (2L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(1372706400000L, test.getMillis());
    }

    public void testWithDurationAdded_RD_int_5_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.withDurationAdded(new Duration(123456789L), 1);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        
        result = test.withDurationAdded(null, 1);
        
        result = test.withDurationAdded(new Duration(123456789L), 0);
        
        result = test.withDurationAdded(new Duration(123456789L), 2);
        expected = new DateMidnight(test.getMillis() + (2L * 123456789L), BUDDHIST_DEFAULT);
        
        result = test.withDurationAdded(new Duration(123456789L), -3);
        expected = new DateMidnight(test.getMillis() - (3L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(1372766400000L, result.getMillis());
    }

    public void testWithDurationAdded_RP_int_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateMidnight expected = new DateMidnight(2003, 7, 28, BUDDHIST_DEFAULT);
        assertNotSame(test, result);
    }

    public void testWithDurationAdded_RP_int_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateMidnight expected = new DateMidnight(2003, 7, 28, BUDDHIST_DEFAULT);
        
        result = test.withPeriodAdded(null, 1);
        assertNotNull(result);
    }

    public void testWithDurationAdded_RP_int_3_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateMidnight expected = new DateMidnight(2003, 7, 28, BUDDHIST_DEFAULT);
        
        result = test.withPeriodAdded(null, 1);
        
        result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 0);
        assertNotNull(result);
    }

    public void testWithDurationAdded_RP_int_4_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateMidnight expected = new DateMidnight(2003, 7, 28, BUDDHIST_DEFAULT);
        
        result = test.withPeriodAdded(null, 1);
        
        result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 0);
        
        result = test.withPeriodAdded(new Period(1, 2, 0, 4, 5, 6, 7, 8), 3);
        expected = new DateMidnight(2005, 11, 15, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testWithDurationAdded_RP_int_5_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateMidnight expected = new DateMidnight(2003, 7, 28, BUDDHIST_DEFAULT);
        
        result = test.withPeriodAdded(null, 1);
        
        result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 0);
        
        result = test.withPeriodAdded(new Period(1, 2, 0, 4, 5, 6, 7, 8), 3);
        expected = new DateMidnight(2005, 11, 15, BUDDHIST_DEFAULT);
        
        result = test.withPeriodAdded(new Period(1, 2, 0, 1, 1, 2, 3, 4), -1);
        expected = new DateMidnight(2001, 3, 1, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlus_long_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.plus(123456789L);
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(1372766400000L, result.getMillis());
    }

    public void testPlus_RD_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.plus(new Duration(123456789L));
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(1372766400000L, result.getMillis());
    }

    public void testPlus_RD_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.plus(new Duration(123456789L));
        DateMidnight expected = new DateMidnight(test.getMillis() + 123456789L, BUDDHIST_DEFAULT);
        
        result = test.plus((ReadableDuration) null);
        assertNotNull(result);
    }

    public void testPlus_RP_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        DateMidnight expected = new DateMidnight(2003, 7, 28, BUDDHIST_DEFAULT);
        assertNotNull(result);
    }

    public void testPlus_RP_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        DateMidnight expected = new DateMidnight(2003, 7, 28, BUDDHIST_DEFAULT);
        
        result = test.plus((ReadablePeriod) null);
        assertNotNull(result);
    }

    public void testPlusYears_int_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusYears(1);
        DateMidnight expected = new DateMidnight(2003, 5, 3, BUDDHIST_DEFAULT);
        assertNotNull(result);
    }

    public void testPlusYears_int_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusYears(1);
        DateMidnight expected = new DateMidnight(2003, 5, 3, BUDDHIST_DEFAULT);
        
        result = test.plusYears(0);
        assertNotSame(test, result);
    }

    public void testPlusMonths_int_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusMonths(1);
        DateMidnight expected = new DateMidnight(2002, 6, 3, BUDDHIST_DEFAULT);
        assertNotNull(result);
    }

    public void testPlusMonths_int_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusMonths(1);
        DateMidnight expected = new DateMidnight(2002, 6, 3, BUDDHIST_DEFAULT);
        
        result = test.plusMonths(0);
        assertNotNull(result);
    }

    public void testPlusWeeks_int_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusWeeks(1);
        DateMidnight expected = new DateMidnight(2002, 5, 10, BUDDHIST_DEFAULT);
        assertNotNull(result);
    }

    public void testPlusWeeks_int_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusWeeks(1);
        DateMidnight expected = new DateMidnight(2002, 5, 10, BUDDHIST_DEFAULT);
        
        result = test.plusWeeks(0);
        assertNotSame(test, result);
    }

    public void testPlusDays_int_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusDays(1);
        DateMidnight expected = new DateMidnight(2002, 5, 4, BUDDHIST_DEFAULT);
        assertNotNull(result);
    }

    public void testPlusDays_int_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.plusDays(1);
        DateMidnight expected = new DateMidnight(2002, 5, 4, BUDDHIST_DEFAULT);
        
        result = test.plusDays(0);
        assertNotNull(result);
    }

    public void testMinus_long_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.minus(123456789L);
        DateMidnight expected = new DateMidnight(test.getMillis() - 123456789L, BUDDHIST_DEFAULT);
        assertEquals(1372766400000L - 123456789L, result.getMillis());
    }

    public void testMinus_RD_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.minus(new Duration(123456789L));
        DateMidnight expected = new DateMidnight(test.getMillis() - 123456789L, BUDDHIST_DEFAULT);
        assertEquals(1372766400000L - 123456789L, result.getMillis());
    }

    public void testMinus_RD_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, BUDDHIST_DEFAULT);
        DateMidnight result = test.minus(new Duration(123456789L));
        DateMidnight expected = new DateMidnight(test.getMillis() - 123456789L, BUDDHIST_DEFAULT);
        
        result = test.minus((ReadableDuration) null);
        assertNotNull(result);
    }

    public void testMinus_RP_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        DateMidnight expected = new DateMidnight(2001, 3, 25, BUDDHIST_DEFAULT);
        assertNotSame(expected, result);
    }

    public void testMinus_RP_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        DateMidnight expected = new DateMidnight(2001, 3, 25, BUDDHIST_DEFAULT);
        
        result = test.minus((ReadablePeriod) null);
        assertNotNull(result);
    }

    public void testMinusYears_int_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusYears(1);
        DateMidnight expected = new DateMidnight(2001, 5, 3, BUDDHIST_DEFAULT);
        assertNotNull(result);
    }

    public void testMinusYears_int_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusYears(1);
        DateMidnight expected = new DateMidnight(2001, 5, 3, BUDDHIST_DEFAULT);
        
        result = test.minusYears(0);
        assertNotNull(result);
    }

    public void testMinusMonths_int_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusMonths(1);
        DateMidnight expected = new DateMidnight(2002, 4, 3, BUDDHIST_DEFAULT);
        assertNotNull(result);
    }

    public void testMinusMonths_int_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusMonths(1);
        DateMidnight expected = new DateMidnight(2002, 4, 3, BUDDHIST_DEFAULT);
        
        result = test.minusMonths(0);
        assertNotNull(result);
    }

    public void testMinusWeeks_int_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusWeeks(1);
        DateMidnight expected = new DateMidnight(2002, 4, 26, BUDDHIST_DEFAULT);
        assertNotNull(result);
    }

    public void testMinusWeeks_int_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusWeeks(1);
        DateMidnight expected = new DateMidnight(2002, 4, 26, BUDDHIST_DEFAULT);
        
        result = test.minusWeeks(0);
        assertNotNull(result);
    }

    public void testMinusDays_int_1_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusDays(1);
        DateMidnight expected = new DateMidnight(2002, 5, 2, BUDDHIST_DEFAULT);
        assertNotNull(result);
    }

    public void testMinusDays_int_2_oe() {
        DateMidnight test = new DateMidnight(2002, 5, 3, BUDDHIST_DEFAULT);
        DateMidnight result = test.minusDays(1);
        DateMidnight expected = new DateMidnight(2002, 5, 2, BUDDHIST_DEFAULT);
        
        result = test.minusDays(0);
        assertNotNull(result);
    }

    public void testProperty_1_oe() {
        DateMidnight test = new DateMidnight();
        assertNotNull(test.year());
    }

    public void testProperty_2_oe() {
        DateMidnight test = new DateMidnight();
        assertEquals("Day of Week", test.dayOfWeek().toString());
    }

    public void testProperty_3_oe() {
        DateMidnight test = new DateMidnight();
        assertNotNull(test.weekOfWeekyear());
    }

    public void testProperty_4_oe() {
        DateMidnight test = new DateMidnight();
// incorrect assertion         assertNotNull(test.property());
    }

    public void testToDateTime_DateTimeZone_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);
        assertEquals(1372706400000L, result.getMillis());
    }

    public void testToDateTime_DateTimeZone_6_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateTime result = test.toDateTime(LONDON);

        test = new DateMidnight(TEST_TIME1_UTC);
        result = test.toDateTime(PARIS);
        assertNotNull(result);
    }

    public void testWithZoneRetainFields_DateTimeZone_3_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withZoneRetainFields(PARIS);
        assertEquals(ISOChronology.getInstanceUTC(), result.getChronology());
    }

    public void testWithZoneRetainFields_DateTimeZone_4_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        DateMidnight result = test.withZoneRetainFields(PARIS);
        
        test = new DateMidnight(TEST_TIME1_UTC, GREGORIAN_PARIS);
        result = test.withZoneRetainFields(null);
        assertEquals(1372706400000L, result.getMillis());
    }

}
