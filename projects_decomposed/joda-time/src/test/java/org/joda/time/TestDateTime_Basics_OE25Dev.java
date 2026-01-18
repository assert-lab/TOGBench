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
 * This class is a Junit unit test for DateTime.
 *
 * @author Stephen Colebourne
 */
public class TestDateTime_Basics_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");

    // the default time zone is set to LONDON in setUp()
    // we have to hard code LONDON here (instead of ISOChronology.getInstance() etc.)
    // as TestAll sets up a different time zone for better all-round testing
    private static final ISOChronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final ISOChronology ISO_DEFAULT = ISOChronology.getInstance(LONDON);
    private static final ISOChronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    private static final GJChronology GJ_DEFAULT = GJChronology.getInstance(LONDON);
    private static final GregorianChronology GREGORIAN_DEFAULT = GregorianChronology.getInstance(LONDON);
    private static final GregorianChronology GREGORIAN_PARIS = GregorianChronology.getInstance(PARIS);
    private static final BuddhistChronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();
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
        return new TestSuite(TestDateTime_Basics_OE25Dev.class);
    }

    public TestDateTime_Basics_OE25Dev(String name) {
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

    //-----------------------------------------------------------------------

    public void testWithers() {
        DateTime test = new DateTime(1970, 6, 9, 10, 20, 30, 40, GJ_DEFAULT);
        check(test.withYear(2000), 2000, 6, 9, 10, 20, 30, 40);
        check(test.withMonthOfYear(2), 1970, 2, 9, 10, 20, 30, 40);
        check(test.withDayOfMonth(2), 1970, 6, 2, 10, 20, 30, 40);
        check(test.withDayOfYear(6), 1970, 1, 6, 10, 20, 30, 40);
        check(test.withDayOfWeek(6), 1970, 6, 13, 10, 20, 30, 40);
        check(test.withWeekOfWeekyear(6), 1970, 2, 3, 10, 20, 30, 40);
        check(test.withWeekyear(1971), 1971, 6, 15, 10, 20, 30, 40);
        check(test.withYearOfCentury(60), 1960, 6, 9, 10, 20, 30, 40);
        check(test.withCenturyOfEra(21), 2070, 6, 9, 10, 20, 30, 40);
        check(test.withYearOfEra(1066), 1066, 6, 9, 10, 20, 30, 40);
        check(test.withEra(DateTimeConstants.BC), -1970, 6, 9, 10, 20, 30, 40);
        check(test.withHourOfDay(6), 1970, 6, 9, 6, 20, 30, 40);
        check(test.withMinuteOfHour(6), 1970, 6, 9, 10, 6, 30, 40);
        check(test.withSecondOfMinute(6), 1970, 6, 9, 10, 20, 6, 40);
        check(test.withMillisOfSecond(6), 1970, 6, 9, 10, 20, 30, 6);
        check(test.withMillisOfDay(61234), 1970, 6, 9, 0, 1, 1, 234);
        
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
    
    class MockInstant extends AbstractInstant {
        @Override
        public String toString() {
            return null;
        }
        public long getMillis() {
            return TEST_TIME1;
        }
        public Chronology getChronology() {
            return ISO_DEFAULT;
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
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    public void testWithField2() {
        DateTime test = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        try {
            test.withField(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testWithFieldAdded2() {
        DateTime test = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        try {
            test.withFieldAdded(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded3() {
        DateTime test = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        try {
            test.withFieldAdded(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------    

    //-----------------------------------------------------------------------    

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    private void check(DateTime test, int year, int month, int day, int hour, int min, int sec, int mil) {
        assertEquals(year, test.getYear());
        assertEquals(month, test.getMonthOfYear());
        assertEquals(day, test.getDayOfMonth());
        assertEquals(hour, test.getHourOfDay());
        assertEquals(min, test.getMinuteOfHour());
        assertEquals(sec, test.getSecondOfMinute());
        assertEquals(mil, test.getMillisOfSecond());
    }

    public void testTest_1_oe() {
        assertEquals("2002-06-09T00:00:00.000Z", new Instant(TEST_TIME_NOW).toString());
    }

    public void testTest_2_oe() {
        // removed other assertion
        assertEquals("2002-04-05T12:24:00.000Z", new Instant(TEST_TIME1).toString());
    }

    public void testTest_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("2003-05-06T14:28:00.000Z", new Instant(TEST_TIME2).toString());
    }

    public void testGet_DateTimeField_1_oe() {
        DateTime test = new DateTime();
        assertEquals(1, test.get(ISO_DEFAULT.era()));
    }

    public void testGet_DateTimeField_2_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        assertEquals(20, test.get(ISO_DEFAULT.centuryOfEra()));
    }

    public void testGet_DateTimeField_3_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.get(ISO_DEFAULT.yearOfCentury()));
    }

    public void testGet_DateTimeField_4_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002, test.get(ISO_DEFAULT.yearOfEra()));
    }

    public void testGet_DateTimeField_5_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002, test.get(ISO_DEFAULT.year()));
    }

    public void testGet_DateTimeField_6_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.get(ISO_DEFAULT.monthOfYear()));
    }

    public void testGet_DateTimeField_7_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.get(ISO_DEFAULT.dayOfMonth()));
    }

    public void testGet_DateTimeField_8_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002, test.get(ISO_DEFAULT.weekyear()));
    }

    public void testGet_DateTimeField_9_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(23, test.get(ISO_DEFAULT.weekOfWeekyear()));
    }

    public void testGet_DateTimeField_10_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.get(ISO_DEFAULT.dayOfWeek()));
    }

    public void testGet_DateTimeField_11_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(160, test.get(ISO_DEFAULT.dayOfYear()));
    }

    public void testGet_DateTimeField_12_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.get(ISO_DEFAULT.halfdayOfDay()));
    }

    public void testGet_DateTimeField_13_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.get(ISO_DEFAULT.hourOfHalfday()));
    }

    public void testGet_DateTimeField_14_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.get(ISO_DEFAULT.clockhourOfDay()));
    }

    public void testGet_DateTimeField_15_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.get(ISO_DEFAULT.clockhourOfHalfday()));
    }

    public void testGet_DateTimeField_16_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.get(ISO_DEFAULT.hourOfDay()));
    }

    public void testGet_DateTimeField_17_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.get(ISO_DEFAULT.minuteOfHour()));
    }

    public void testGet_DateTimeField_18_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60, test.get(ISO_DEFAULT.minuteOfDay()));
    }

    public void testGet_DateTimeField_19_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.get(ISO_DEFAULT.secondOfMinute()));
    }

    public void testGet_DateTimeField_20_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60 * 60, test.get(ISO_DEFAULT.secondOfDay()));
    }

    public void testGet_DateTimeField_21_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.get(ISO_DEFAULT.millisOfSecond()));
    }

    public void testGet_DateTimeField_22_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60 * 60 * 1000, test.get(ISO_DEFAULT.millisOfDay()));
    }

    public void testGet_DateTimeFieldType_1_oe() {
        DateTime test = new DateTime();
        assertEquals(1, test.get(DateTimeFieldType.era()));
    }

    public void testGet_DateTimeFieldType_2_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        assertEquals(20, test.get(DateTimeFieldType.centuryOfEra()));
    }

    public void testGet_DateTimeFieldType_3_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.get(DateTimeFieldType.yearOfCentury()));
    }

    public void testGet_DateTimeFieldType_4_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002, test.get(DateTimeFieldType.yearOfEra()));
    }

    public void testGet_DateTimeFieldType_5_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002, test.get(DateTimeFieldType.year()));
    }

    public void testGet_DateTimeFieldType_6_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.get(DateTimeFieldType.monthOfYear()));
    }

    public void testGet_DateTimeFieldType_7_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.get(DateTimeFieldType.dayOfMonth()));
    }

    public void testGet_DateTimeFieldType_8_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002, test.get(DateTimeFieldType.weekyear()));
    }

    public void testGet_DateTimeFieldType_9_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(23, test.get(DateTimeFieldType.weekOfWeekyear()));
    }

    public void testGet_DateTimeFieldType_10_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.get(DateTimeFieldType.dayOfWeek()));
    }

    public void testGet_DateTimeFieldType_11_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(160, test.get(DateTimeFieldType.dayOfYear()));
    }

    public void testGet_DateTimeFieldType_12_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testGet_DateTimeFieldType_13_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.get(DateTimeFieldType.hourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_14_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.get(DateTimeFieldType.clockhourOfDay()));
    }

    public void testGet_DateTimeFieldType_15_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.get(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_16_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.get(DateTimeFieldType.hourOfDay()));
    }

    public void testGet_DateTimeFieldType_17_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.get(DateTimeFieldType.minuteOfHour()));
    }

    public void testGet_DateTimeFieldType_18_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60, test.get(DateTimeFieldType.minuteOfDay()));
    }

    public void testGet_DateTimeFieldType_19_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.get(DateTimeFieldType.secondOfMinute()));
    }

    public void testGet_DateTimeFieldType_20_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60 * 60, test.get(DateTimeFieldType.secondOfDay()));
    }

    public void testGet_DateTimeFieldType_21_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.get(DateTimeFieldType.millisOfSecond()));
    }

    public void testGet_DateTimeFieldType_22_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60 * 60 * 1000, test.get(DateTimeFieldType.millisOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_1_oe() {
        DateTime test = new DateTime();
        assertEquals(true, test.isSupported(DateTimeFieldType.era()));
    }

    public void testIsSupported_DateTimeFieldType_2_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.centuryOfEra()));
    }

    public void testIsSupported_DateTimeFieldType_3_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.yearOfCentury()));
    }

    public void testIsSupported_DateTimeFieldType_4_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.yearOfEra()));
    }

    public void testIsSupported_DateTimeFieldType_5_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.year()));
    }

    public void testIsSupported_DateTimeFieldType_6_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.monthOfYear()));
    }

    public void testIsSupported_DateTimeFieldType_7_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.dayOfMonth()));
    }

    public void testIsSupported_DateTimeFieldType_8_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.weekyear()));
    }

    public void testIsSupported_DateTimeFieldType_9_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.weekOfWeekyear()));
    }

    public void testIsSupported_DateTimeFieldType_10_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.dayOfWeek()));
    }

    public void testIsSupported_DateTimeFieldType_11_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.dayOfYear()));
    }

    public void testIsSupported_DateTimeFieldType_12_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.halfdayOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_13_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.hourOfHalfday()));
    }

    public void testIsSupported_DateTimeFieldType_14_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.clockhourOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_15_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testIsSupported_DateTimeFieldType_16_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_17_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.minuteOfHour()));
    }

    public void testIsSupported_DateTimeFieldType_18_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.minuteOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_19_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.secondOfMinute()));
    }

    public void testIsSupported_DateTimeFieldType_20_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.secondOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_21_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.millisOfSecond()));
    }

    public void testIsSupported_DateTimeFieldType_22_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.millisOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_23_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isSupported(null));
    }

    public void testGetters_1_oe() {
        DateTime test = new DateTime();
        
        assertEquals(ISO_DEFAULT, test.getChronology());
    }

    public void testGetters_2_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        assertEquals(LONDON, test.getZone());
    }

    public void testGetters_3_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_NOW, test.getMillis());
    }

    public void testGetters_4_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1, test.getEra());
    }

    public void testGetters_5_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(20, test.getCenturyOfEra());
    }

    public void testGetters_6_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getYearOfCentury());
    }

    public void testGetters_7_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002, test.getYearOfEra());
    }

    public void testGetters_8_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002, test.getYear());
    }

    public void testGetters_9_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.getMonthOfYear());
    }

    public void testGetters_10_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.getDayOfMonth());
    }

    public void testGetters_11_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2002, test.getWeekyear());
    }

    public void testGetters_12_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(23, test.getWeekOfWeekyear());
    }

    public void testGetters_13_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.getDayOfWeek());
    }

    public void testGetters_14_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(160, test.getDayOfYear());
    }

    public void testGetters_15_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getHourOfDay());
    }

    public void testGetters_16_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMinuteOfHour());
    }

    public void testGetters_17_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60, test.getMinuteOfDay());
    }

    public void testGetters_18_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getSecondOfMinute());
    }

    public void testGetters_19_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60 * 60, test.getSecondOfDay());
    }

    public void testGetters_20_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.getMillisOfSecond());
    }

    public void testGetters_21_oe() {
        DateTime test = new DateTime();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60 * 60 * 1000, test.getMillisOfDay());
    }

    public void testEqualsHashCode_1_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        assertEquals(false, test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(false, test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test2.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test1.equals("Hello"));
    }

    public void testEqualsHashCode_15_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test1.equals(new MockInstant()));
    }

    public void testEqualsHashCode_16_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.equals(new DateTime(TEST_TIME1, GREGORIAN_DEFAULT)));
    }

    public void testEqualsHashCode_17_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, new DateTime(TEST_TIME1, new MockEqualsChronology()).equals(new DateTime(TEST_TIME1, new MockEqualsChronology())));
    }

    public void testEqualsHashCode_18_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test2 = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME1, new MockEqualsChronology()).equals(new DateTime(TEST_TIME1, ISO_DEFAULT)));
    }

    public void testCompareTo_1_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        assertEquals(0, test1.compareTo(test1a));
    }

    public void testCompareTo_2_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_3_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test1.compareTo(test1));
    }

    public void testCompareTo_4_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test1a.compareTo(test1a));
    }

    public void testCompareTo_5_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        assertEquals(-1, test1.compareTo(test2));
    }

    public void testCompareTo_6_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(+1, test2.compareTo(test1));
    }

    public void testCompareTo_7_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(-1, test1.compareTo(test3));
    }

    public void testCompareTo_8_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(+1, test3.compareTo(test1));
    }

    public void testCompareTo_9_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test3.compareTo(test2));
    }

    public void testCompareTo_10_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(+1, test2.compareTo(new MockInstant()));
    }

    public void testCompareTo_11_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(0, test1.compareTo(new MockInstant()));
    }

    public void testIsEqual_long_1_oe() {
        assertEquals(false, new DateTime(TEST_TIME1).isEqual(TEST_TIME2));
    }

    public void testIsEqual_long_2_oe() {
        // removed other assertion
        assertEquals(true, new DateTime(TEST_TIME1).isEqual(TEST_TIME1));
    }

    public void testIsEqual_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME2).isEqual(TEST_TIME1));
    }

    public void testIsEqualNow_1_oe() {
        assertEquals(false, new DateTime(TEST_TIME_NOW - 1).isEqualNow());
    }

    public void testIsEqualNow_2_oe() {
        // removed other assertion
        assertEquals(true, new DateTime(TEST_TIME_NOW).isEqualNow());
    }

    public void testIsEqualNow_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME_NOW + 1).isEqualNow());
    }

    public void testIsEqual_RI_1_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_RI_2_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_RI_3_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.isEqual(test1));
    }

    public void testIsEqual_RI_4_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1a.isEqual(test1a));
    }

    public void testIsEqual_RI_5_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        assertEquals(false, test1.isEqual(test2));
    }

    public void testIsEqual_RI_6_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(false, test2.isEqual(test1));
    }

    public void testIsEqual_RI_7_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(false, test1.isEqual(test3));
    }

    public void testIsEqual_RI_8_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(false, test3.isEqual(test1));
    }

    public void testIsEqual_RI_9_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test3.isEqual(test2));
    }

    public void testIsEqual_RI_10_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test2.isEqual(new MockInstant()));
    }

    public void testIsEqual_RI_11_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test1.isEqual(new MockInstant()));
    }

    public void testIsEqual_RI_12_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, new DateTime(TEST_TIME_NOW + 1).isEqual(null));
    }

    public void testIsEqual_RI_13_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, new DateTime(TEST_TIME_NOW).isEqual(null));
    }

    public void testIsEqual_RI_14_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME_NOW - 1).isEqual(null));
    }

    public void testIsBefore_long_1_oe() {
        assertEquals(true, new DateTime(TEST_TIME1).isBefore(TEST_TIME2));
    }

    public void testIsBefore_long_2_oe() {
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME1).isBefore(TEST_TIME1));
    }

    public void testIsBefore_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME2).isBefore(TEST_TIME1));
    }

    public void testIsBeforeNow_1_oe() {
        assertEquals(true, new DateTime(TEST_TIME_NOW - 1).isBeforeNow());
    }

    public void testIsBeforeNow_2_oe() {
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME_NOW).isBeforeNow());
    }

    public void testIsBeforeNow_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME_NOW + 1).isBeforeNow());
    }

    public void testIsBefore_RI_1_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        assertEquals(false, test1.isBefore(test1a));
    }

    public void testIsBefore_RI_2_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(false, test1a.isBefore(test1));
    }

    public void testIsBefore_RI_3_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isBefore(test1));
    }

    public void testIsBefore_RI_4_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1a.isBefore(test1a));
    }

    public void testIsBefore_RI_5_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        assertEquals(true, test1.isBefore(test2));
    }

    public void testIsBefore_RI_6_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(false, test2.isBefore(test1));
    }

    public void testIsBefore_RI_7_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(true, test1.isBefore(test3));
    }

    public void testIsBefore_RI_8_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(false, test3.isBefore(test1));
    }

    public void testIsBefore_RI_9_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.isBefore(test2));
    }

    public void testIsBefore_RI_10_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test2.isBefore(new MockInstant()));
    }

    public void testIsBefore_RI_11_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test1.isBefore(new MockInstant()));
    }

    public void testIsBefore_RI_12_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, new DateTime(TEST_TIME_NOW + 1).isBefore(null));
    }

    public void testIsBefore_RI_13_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME_NOW).isBefore(null));
    }

    public void testIsBefore_RI_14_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, new DateTime(TEST_TIME_NOW - 1).isBefore(null));
    }

    public void testIsAfter_long_1_oe() {
        assertEquals(false, new DateTime(TEST_TIME1).isAfter(TEST_TIME2));
    }

    public void testIsAfter_long_2_oe() {
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME1).isAfter(TEST_TIME1));
    }

    public void testIsAfter_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, new DateTime(TEST_TIME2).isAfter(TEST_TIME1));
    }

    public void testIsAfterNow_1_oe() {
        assertEquals(false, new DateTime(TEST_TIME_NOW - 1).isAfterNow());
    }

    public void testIsAfterNow_2_oe() {
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME_NOW).isAfterNow());
    }

    public void testIsAfterNow_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, new DateTime(TEST_TIME_NOW + 1).isAfterNow());
    }

    public void testIsAfter_RI_1_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        assertEquals(false, test1.isAfter(test1a));
    }

    public void testIsAfter_RI_2_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        assertEquals(false, test1a.isAfter(test1));
    }

    public void testIsAfter_RI_3_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isAfter(test1));
    }

    public void testIsAfter_RI_4_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1a.isAfter(test1a));
    }

    public void testIsAfter_RI_5_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        assertEquals(false, test1.isAfter(test2));
    }

    public void testIsAfter_RI_6_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.isAfter(test1));
    }

    public void testIsAfter_RI_7_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        assertEquals(false, test1.isAfter(test3));
    }

    public void testIsAfter_RI_8_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(true, test3.isAfter(test1));
    }

    public void testIsAfter_RI_9_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.isAfter(test2));
    }

    public void testIsAfter_RI_10_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(true, test2.isAfter(new MockInstant()));
    }

    public void testIsAfter_RI_11_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test1.isAfter(new MockInstant()));
    }

    public void testIsAfter_RI_12_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(true, new DateTime(TEST_TIME_NOW + 1).isAfter(null));
    }

    public void testIsAfter_RI_13_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME_NOW).isAfter(null));
    }

    public void testIsAfter_RI_14_oe() {
        DateTime test1 = new DateTime(TEST_TIME1);
        DateTime test1a = new DateTime(TEST_TIME1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime test2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        DateTime test3 = new DateTime(TEST_TIME2, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, new DateTime(TEST_TIME_NOW - 1).isAfter(null));
    }

    public void testSerialization_1_oe() throws Exception {
        DateTime test = new DateTime(TEST_TIME_NOW);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DateTime result = (DateTime) ois.readObject();
        ois.close();
        
        assertEquals(test, result);
    }

    public void testToString_1_oe() {
        DateTime test = new DateTime(TEST_TIME_NOW);
        assertEquals("2002-06-09T01:00:00.000+01:00", test.toString());
    }

    public void testToString_2_oe() {
        DateTime test = new DateTime(TEST_TIME_NOW);
        // removed other assertion
        
        test = new DateTime(TEST_TIME_NOW, PARIS);
        assertEquals("2002-06-09T02:00:00.000+02:00", test.toString());
    }

    public void testToString_String_1_oe() {
        DateTime test = new DateTime(TEST_TIME_NOW);
        assertEquals("2002 01", test.toString("yyyy HH"));
    }

    public void testToString_String_2_oe() {
        DateTime test = new DateTime(TEST_TIME_NOW);
        // removed other assertion
        assertEquals("2002-06-09T01:00:00.000+01:00", test.toString((String) null));
    }

    public void testToString_String_Locale_1_oe() {
        DateTime test = new DateTime(TEST_TIME_NOW);
        assertEquals("Sun 9/6", test.toString("EEE d/M", Locale.ENGLISH));
    }

    public void testToString_String_Locale_2_oe() {
        DateTime test = new DateTime(TEST_TIME_NOW);
        // removed other assertion
        assertEquals("dim. 9/6", test.toString("EEE d/M", Locale.FRENCH));
    }

    public void testToString_String_Locale_3_oe() {
        DateTime test = new DateTime(TEST_TIME_NOW);
        // removed other assertion
        // removed other assertion
        assertEquals("2002-06-09T01:00:00.000+01:00", test.toString(null, Locale.ENGLISH));
    }

    public void testToString_String_Locale_4_oe() {
        DateTime test = new DateTime(TEST_TIME_NOW);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Sun 9/6", test.toString("EEE d/M", null));
    }

    public void testToString_String_Locale_5_oe() {
        DateTime test = new DateTime(TEST_TIME_NOW);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2002-06-09T01:00:00.000+01:00", test.toString(null, null));
    }

    public void testToString_DTFormatter_1_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW);
        assertEquals("2002 00", test.toString(DateTimeFormat.forPattern("yyyy HH")));
    }

    public void testToString_DTFormatter_2_oe() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW);
        // removed other assertion
        assertEquals("2002-06-09T00:00:00.000+01:00", test.toString((DateTimeFormatter) null));
    }

    public void testToInstant_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        Instant result = test.toInstant();
        assertEquals(TEST_TIME1, result.getMillis());
    }

    public void testToDateTime_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime();
        assertSame(test, result);
    }

    public void testToDateTimeISO_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        assertSame(test, result);
    }

    public void testToDateTimeISO_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        assertSame(DateTime.class, result.getClass());
    }

    public void testToDateTimeISO_3_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        assertSame(ISOChronology.class, result.getChronology().getClass());
    }

    public void testToDateTimeISO_4_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToDateTimeISO_5_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISO_PARIS, result.getChronology());
    }

    public void testToDateTimeISO_6_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test, result);
    }

    public void testToDateTimeISO_7_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        result = test.toDateTimeISO();
        assertSame(DateTime.class, result.getClass());
    }

    public void testToDateTimeISO_8_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        result = test.toDateTimeISO();
        // removed other assertion
        assertSame(ISOChronology.class, result.getChronology().getClass());
    }

    public void testToDateTimeISO_9_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToDateTimeISO_10_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testToDateTimeISO_11_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test, result);
    }

    public void testToDateTimeISO_12_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, new MockNullZoneChronology());
        result = test.toDateTimeISO();
        assertSame(DateTime.class, result.getClass());
    }

    public void testToDateTimeISO_13_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, new MockNullZoneChronology());
        result = test.toDateTimeISO();
        // removed other assertion
        assertSame(ISOChronology.class, result.getChronology().getClass());
    }

    public void testToDateTimeISO_14_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, new MockNullZoneChronology());
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToDateTimeISO_15_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, new MockNullZoneChronology());
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testToDateTimeISO_16_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTimeISO();
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, ISO_PARIS);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, new MockNullZoneChronology());
        result = test.toDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test, result);
    }

    public void testToDateTime_DateTimeZone_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime(LONDON);
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toDateTime(PARIS);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToDateTime_DateTimeZone_3_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime(LONDON);
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toDateTime(PARIS);
        // removed other assertion
        assertEquals(PARIS, result.getZone());
    }

    public void testToDateTime_DateTimeZone_4_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime(LONDON);
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToDateTime_DateTimeZone_5_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime(LONDON);
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toDateTime((DateTimeZone) null);
        // removed other assertion
        assertEquals(LONDON, result.getZone());
    }

    public void testToDateTime_Chronology_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime(ISO_DEFAULT);
        assertSame(test, result);
    }

    public void testToDateTime_Chronology_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime(ISO_DEFAULT);
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toDateTime(GREGORIAN_PARIS);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToDateTime_Chronology_3_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime(ISO_DEFAULT);
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toDateTime(GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(GREGORIAN_PARIS, result.getChronology());
    }

    public void testToDateTime_Chronology_4_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime(ISO_DEFAULT);
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toDateTime(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.toDateTime((Chronology) null);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToDateTime_Chronology_5_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime(ISO_DEFAULT);
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toDateTime(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.toDateTime((Chronology) null);
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testToDateTime_Chronology_6_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.toDateTime(ISO_DEFAULT);
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toDateTime(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.toDateTime((Chronology) null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toDateTime((Chronology) null);
        assertSame(test, result);
    }

    public void testToMutableDateTime_1_oe() {
        DateTime test = new DateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToMutableDateTime_2_oe() {
        DateTime test = new DateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        // removed other assertion
        assertEquals(ISO_PARIS, result.getChronology());
    }

    public void testToMutableDateTimeISO_1_oe() {
        DateTime test = new DateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertSame(MutableDateTime.class, result.getClass());
    }

    public void testToMutableDateTimeISO_2_oe() {
        DateTime test = new DateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        // removed other assertion
        assertSame(ISOChronology.class, result.getChronology().getClass());
    }

    public void testToMutableDateTimeISO_3_oe() {
        DateTime test = new DateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        // removed other assertion
        // removed other assertion
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToMutableDateTimeISO_4_oe() {
        DateTime test = new DateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISO_PARIS, result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone_3_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_4_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        assertEquals(ISO_PARIS, result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone_5_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_6_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testToMutableDateTime_DateTimeZone_7_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToMutableDateTime_DateTimeZone_8_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testToMutableDateTime_Chronology_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToMutableDateTime_Chronology_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testToMutableDateTime_Chronology_3_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(GREGORIAN_PARIS);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToMutableDateTime_Chronology_4_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(GREGORIAN_PARIS, result.getChronology());
    }

    public void testToMutableDateTime_Chronology_5_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToMutableDateTime_Chronology_6_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testToMutableDateTime_Chronology_7_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testToMutableDateTime_Chronology_8_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISO_DEFAULT);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testToDate_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        Date result = test.toDate();
        assertEquals(test.getMillis(), result.getTime());
    }

    public void testToCalendar_Locale_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        assertEquals(test.getMillis(), result.getTime().getTime());
    }

    public void testToCalendar_Locale_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Europe/London"), result.getTimeZone());
    }

    public void testToCalendar_Locale_3_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);
        assertEquals(test.getMillis(), result.getTime().getTime());
    }

    public void testToCalendar_Locale_4_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);
        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Europe/Paris"), result.getTimeZone());
    }

    public void testToCalendar_Locale_5_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(Locale.UK);
        assertEquals(test.getMillis(), result.getTime().getTime());
    }

    public void testToCalendar_Locale_6_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        Calendar result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(null);
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toCalendar(Locale.UK);
        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Europe/Paris"), result.getTimeZone());
    }

    public void testToGregorianCalendar_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();
        assertEquals(test.getMillis(), result.getTime().getTime());
    }

    public void testToGregorianCalendar_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();
        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Europe/London"), result.getTimeZone());
    }

    public void testToGregorianCalendar_3_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toGregorianCalendar();
        assertEquals(test.getMillis(), result.getTime().getTime());
    }

    public void testToGregorianCalendar_4_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        GregorianCalendar result = test.toGregorianCalendar();
        // removed other assertion
        // removed other assertion

        test = new DateTime(TEST_TIME1, PARIS);
        result = test.toGregorianCalendar();
        // removed other assertion
        assertEquals(TimeZone.getTimeZone("Europe/Paris"), result.getTimeZone());
    }

    public void testToDateMidnight_1_oe() {
        DateTime base = new DateTime(TEST_TIME1, COPTIC_DEFAULT);
        DateMidnight test = base.toDateMidnight();
        assertEquals(new DateMidnight(base, COPTIC_DEFAULT), test);
    }

    public void testToYearMonthDay_1_oe() {
        DateTime base = new DateTime(TEST_TIME1, COPTIC_DEFAULT);
        YearMonthDay test = base.toYearMonthDay();
        assertEquals(new YearMonthDay(TEST_TIME1, COPTIC_DEFAULT), test);
    }

    public void testToTimeOfDay_1_oe() {
        DateTime base = new DateTime(TEST_TIME1, COPTIC_DEFAULT);
        TimeOfDay test = base.toTimeOfDay();
        assertEquals(new TimeOfDay(TEST_TIME1, COPTIC_DEFAULT), test);
    }

    public void testToLocalDateTime_1_oe() {
        DateTime base = new DateTime(TEST_TIME1, COPTIC_DEFAULT);
        LocalDateTime test = base.toLocalDateTime();
        assertEquals(new LocalDateTime(TEST_TIME1, COPTIC_DEFAULT), test);
    }

    public void testToLocalDate_1_oe() {
        DateTime base = new DateTime(TEST_TIME1, COPTIC_DEFAULT);
        LocalDate test = base.toLocalDate();
        assertEquals(new LocalDate(TEST_TIME1, COPTIC_DEFAULT), test);
    }

    public void testToLocalTime_1_oe() {
        DateTime base = new DateTime(TEST_TIME1, COPTIC_DEFAULT);
        LocalTime test = base.toLocalTime();
        assertEquals(new LocalTime(TEST_TIME1, COPTIC_DEFAULT), test);
    }

    public void testWithMillis_long_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withMillis(TEST_TIME2);
        assertEquals(TEST_TIME2, result.getMillis());
    }

    public void testWithMillis_long_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withMillis(TEST_TIME2);
        // removed other assertion
        assertEquals(test.getChronology(), result.getChronology());
    }

    public void testWithMillis_long_3_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withMillis(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withMillis(TEST_TIME2);
        assertEquals(TEST_TIME2, result.getMillis());
    }

    public void testWithMillis_long_4_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withMillis(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withMillis(TEST_TIME2);
        // removed other assertion
        assertEquals(test.getChronology(), result.getChronology());
    }

    public void testWithMillis_long_5_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withMillis(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withMillis(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withMillis(TEST_TIME1);
        assertSame(test, result);
    }

    public void testWithChronology_Chronology_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withChronology(GREGORIAN_PARIS);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testWithChronology_Chronology_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withChronology(GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(GREGORIAN_PARIS, result.getChronology());
    }

    public void testWithChronology_Chronology_3_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withChronology(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withChronology(null);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testWithChronology_Chronology_4_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withChronology(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withChronology(null);
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testWithChronology_Chronology_5_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withChronology(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withChronology(null);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withChronology(null);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testWithChronology_Chronology_6_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withChronology(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withChronology(null);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withChronology(null);
        // removed other assertion
        assertEquals(ISO_DEFAULT, result.getChronology());
    }

    public void testWithChronology_Chronology_7_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withChronology(GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withChronology(null);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withChronology(null);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withChronology(ISO_DEFAULT);
        assertSame(test, result);
    }

    public void testWithZone_DateTimeZone_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withZone(PARIS);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testWithZone_DateTimeZone_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withZone(PARIS);
        // removed other assertion
        assertEquals(ISO_PARIS, result.getChronology());
    }

    public void testWithZone_DateTimeZone_3_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withZone(PARIS);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withZone(null);
        assertEquals(test.getMillis(), result.getMillis());
    }

    public void testWithZone_DateTimeZone_4_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withZone(PARIS);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withZone(null);
        // removed other assertion
        assertEquals(GREGORIAN_DEFAULT, result.getChronology());
    }

    public void testWithZone_DateTimeZone_5_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withZone(PARIS);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withZone(null);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withZone(null);
        assertSame(test, result);
    }

    public void testWithZoneRetainFields_DateTimeZone_1_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withZoneRetainFields(PARIS);
        assertEquals(test.getMillis() - DateTimeConstants.MILLIS_PER_HOUR, result.getMillis());
    }

    public void testWithZoneRetainFields_DateTimeZone_2_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withZoneRetainFields(PARIS);
        // removed other assertion
        assertEquals(ISO_PARIS, result.getChronology());
    }

    public void testWithZoneRetainFields_DateTimeZone_5_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withZoneRetainFields(PARIS);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withZoneRetainFields(LONDON);
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withZoneRetainFields(null);
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withZoneRetainFields(null);
        assertEquals(test.getMillis() + DateTimeConstants.MILLIS_PER_HOUR, result.getMillis());
    }

    public void testWithZoneRetainFields_DateTimeZone_6_oe() {
        DateTime test = new DateTime(TEST_TIME1);
        DateTime result = test.withZoneRetainFields(PARIS);
        // removed other assertion
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withZoneRetainFields(LONDON);
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withZoneRetainFields(null);
        // removed other assertion
        
        test = new DateTime(TEST_TIME1, GREGORIAN_PARIS);
        result = test.withZoneRetainFields(null);
        // removed other assertion
        assertEquals(GREGORIAN_DEFAULT, result.getChronology());
    }

    public void testWithDate_int_int_int_1_oe() {
        DateTime test = new DateTime(2002, 4, 5, 1, 2, 3, 4, ISO_UTC);
        DateTime result = test.withDate(2003, 5, 6);
        DateTime expected = new DateTime(2003, 5, 6, 1, 2, 3, 4, ISO_UTC);
        assertEquals(expected, result);
    }

    public void testWithDate_int_int_int_toDST1_1_oe() {
        // 2010-03-28T02:55 is DST time, need to change to 03:55
        DateTime test = new DateTime(2015, 1, 10, 2, 55, 0, 0, ISO_PARIS);
        DateTime result = test.withDate(2010, 3, 28);
        DateTime expected = new DateTime(2010, 3, 28, 3, 55, 0, 0, ISO_PARIS);
        assertEquals(expected, result);
    }

    public void testWithDate_int_int_int_toDST2_1_oe() {
        // 2010-03-28T02:55 is DST time, need to change to 03:55
        DateTime test = new DateTime(2015, 1, 28, 2, 55, 0, 0, ISO_PARIS);
        DateTime result = test.withDate(2010, 3, 28);
        DateTime expected = new DateTime(2010, 3, 28, 3, 55, 0, 0, ISO_PARIS);
        assertEquals(expected, result);
    }

    public void testWithDate_int_int_int_affectedByDST_1_oe() {
        // 2010-03-28T02:55 is DST time, need to avoid time being changed to 03:55
        DateTime test = new DateTime(2015, 1, 28, 2, 55, 0, 0, ISO_PARIS);
        DateTime result = test.withDate(2010, 3, 10);
        DateTime expected = new DateTime(2010, 3, 10, 2, 55, 0, 0, ISO_PARIS);
        assertEquals(expected, result);
    }

    public void testWithDate_LocalDate_1_oe() {
        DateTime test = new DateTime(2002, 4, 5, 1, 2, 3, 4, ISO_UTC);
        DateTime result = test.withDate(new LocalDate(2003, 5, 6));
        DateTime expected = new DateTime(2003, 5, 6, 1, 2, 3, 4, ISO_UTC);
        assertEquals(expected, result);
    }

    public void testWithTime_int_int_int_int_1_oe() {
        DateTime test = new DateTime(TEST_TIME1 - 12345L, BUDDHIST_UTC);
        DateTime result = test.withTime(12, 24, 0, 0);
        assertEquals(TEST_TIME1, result.getMillis());
    }

    public void testWithTime_int_int_int_int_2_oe() {
        DateTime test = new DateTime(TEST_TIME1 - 12345L, BUDDHIST_UTC);
        DateTime result = test.withTime(12, 24, 0, 0);
        // removed other assertion
        assertEquals(BUDDHIST_UTC, result.getChronology());
    }

    public void testWithTime_int_int_int_int_toDST_1_oe() {
        // 2010-03-28T02:55 is DST time, need to change to 03:55
        DateTime test = new DateTime(2010, 3, 28, 0, 0, 0, 0, ISO_PARIS);
        DateTime result = test.withTime(2, 55, 0, 0);
        DateTime expected = new DateTime(2010, 3, 28, 3, 55, 0, 0, ISO_PARIS);
        assertEquals(expected, result);
    }

    public void testWithTime_LocalTime_1_oe() {
        DateTime test = new DateTime(TEST_TIME1 - 12345L, BUDDHIST_UTC);
        DateTime result = test.withTime(new LocalTime(12, 24, 0, 0));
        assertEquals(TEST_TIME1, result.getMillis());
    }

    public void testWithTime_LocalTime_2_oe() {
        DateTime test = new DateTime(TEST_TIME1 - 12345L, BUDDHIST_UTC);
        DateTime result = test.withTime(new LocalTime(12, 24, 0, 0));
        // removed other assertion
        assertEquals(BUDDHIST_UTC, result.getChronology());
    }

    public void testWithTimeAtStartOfDay_1_oe() {
        DateTime test = new DateTime(2018, 10, 28, 0, 0, DateTimeZone.forID("Atlantic/Azores"));
        DateTime result = test.withTimeAtStartOfDay();
        assertEquals(test, result);
    }

    public void testWithFields_RPartial_1_oe() {
        DateTime test = new DateTime(2004, 5, 6, 7, 8, 9, 0);
        DateTime result = test.withFields(new YearMonthDay(2003, 4, 5));
        DateTime expected = new DateTime(2003, 4, 5, 7, 8, 9, 0);
        assertEquals(expected, result);
    }

    public void testWithFields_RPartial_2_oe() {
        DateTime test = new DateTime(2004, 5, 6, 7, 8, 9, 0);
        DateTime result = test.withFields(new YearMonthDay(2003, 4, 5));
        DateTime expected = new DateTime(2003, 4, 5, 7, 8, 9, 0);
        // removed other assertion
        
        test = new DateTime(TEST_TIME1);
        result = test.withFields(null);
        assertSame(test, result);
    }

    public void testWithField1_1_oe() {
        DateTime test = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(new DateTime(2004, 6, 9, 0, 0, 0, 0), test);
    }

    public void testWithField1_2_oe() {
        DateTime test = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime result = test.withField(DateTimeFieldType.year(), 2006);
        
        // removed other assertion
        assertEquals(new DateTime(2006, 6, 9, 0, 0, 0, 0), result);
    }

    public void testWithFieldAdded1_1_oe() {
        DateTime test = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(new DateTime(2004, 6, 9, 0, 0, 0, 0), test);
    }

    public void testWithFieldAdded1_2_oe() {
        DateTime test = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        // removed other assertion
        assertEquals(new DateTime(2010, 6, 9, 0, 0, 0, 0), result);
    }

    public void testWithFieldAdded4_1_oe() {
        DateTime test = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertSame(test, result);
    }

    public void testWithDurationAdded_long_int_1_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.withDurationAdded(123456789L, 1);
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testWithDurationAdded_long_int_2_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.withDurationAdded(123456789L, 1);
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, 0);
        assertSame(test, result);
    }

    public void testWithDurationAdded_long_int_3_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.withDurationAdded(123456789L, 1);
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, 0);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, 2);
        expected = new DateTime(TEST_TIME1 + (2L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testWithDurationAdded_long_int_4_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.withDurationAdded(123456789L, 1);
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, 0);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, 2);
        expected = new DateTime(TEST_TIME1 + (2L * 123456789L), BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withDurationAdded(123456789L, -3);
        expected = new DateTime(TEST_TIME1 - (3L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testWithDurationAdded_RD_int_1_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.withDurationAdded(new Duration(123456789L), 1);
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testWithDurationAdded_RD_int_2_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.withDurationAdded(new Duration(123456789L), 1);
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withDurationAdded(null, 1);
        assertSame(test, result);
    }

    public void testWithDurationAdded_RD_int_3_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.withDurationAdded(new Duration(123456789L), 1);
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withDurationAdded(null, 1);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), 0);
        assertSame(test, result);
    }

    public void testWithDurationAdded_RD_int_4_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.withDurationAdded(new Duration(123456789L), 1);
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withDurationAdded(null, 1);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), 0);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), 2);
        expected = new DateTime(TEST_TIME1 + (2L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testWithDurationAdded_RD_int_5_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.withDurationAdded(new Duration(123456789L), 1);
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withDurationAdded(null, 1);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), 0);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), 2);
        expected = new DateTime(TEST_TIME1 + (2L * 123456789L), BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withDurationAdded(new Duration(123456789L), -3);
        expected = new DateTime(TEST_TIME1 - (3L * 123456789L), BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testWithDurationAdded_RP_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateTime expected = new DateTime(2003, 7, 28, 6, 8, 10, 12, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testWithDurationAdded_RP_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateTime expected = new DateTime(2003, 7, 28, 6, 8, 10, 12, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withPeriodAdded(null, 1);
        assertSame(test, result);
    }

    public void testWithDurationAdded_RP_int_3_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateTime expected = new DateTime(2003, 7, 28, 6, 8, 10, 12, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withPeriodAdded(null, 1);
        // removed other assertion
        
        result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 0);
        assertSame(test, result);
    }

    public void testWithDurationAdded_RP_int_4_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateTime expected = new DateTime(2003, 7, 28, 6, 8, 10, 12, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withPeriodAdded(null, 1);
        // removed other assertion
        
        result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 0);
        // removed other assertion
        
        result = test.withPeriodAdded(new Period(1, 2, 0, 4, 5, 6, 7, 8), 3);
        expected = new DateTime(2005, 11, 15, 16, 20, 24, 28, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testWithDurationAdded_RP_int_5_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 1);
        DateTime expected = new DateTime(2003, 7, 28, 6, 8, 10, 12, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withPeriodAdded(null, 1);
        // removed other assertion
        
        result = test.withPeriodAdded(new Period(1, 2, 3, 4, 5, 6, 7, 8), 0);
        // removed other assertion
        
        result = test.withPeriodAdded(new Period(1, 2, 0, 4, 5, 6, 7, 8), 3);
        expected = new DateTime(2005, 11, 15, 16, 20, 24, 28, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.withPeriodAdded(new Period(1, 2, 0, 1, 1, 2, 3, 4), -1);
        expected = new DateTime(2001, 3, 2, 0, 0, 0, 0, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlus_long_1_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.plus(123456789L);
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlus_RD_1_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.plus(new Duration(123456789L));
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlus_RD_2_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.plus(new Duration(123456789L));
        DateTime expected = new DateTime(TEST_TIME1 + 123456789L, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.plus((ReadableDuration) null);
        assertSame(test, result);
    }

    public void testPlus_RP_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        DateTime expected = new DateTime(2003, 7, 28, 6, 8, 10, 12, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlus_RP_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        DateTime expected = new DateTime(2003, 7, 28, 6, 8, 10, 12, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.plus((ReadablePeriod) null);
        assertSame(test, result);
    }

    public void testPlusYears_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusYears(1);
        DateTime expected = new DateTime(2003, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlusYears_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusYears(1);
        DateTime expected = new DateTime(2003, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.plusYears(0);
        assertSame(test, result);
    }

    public void testPlusMonths_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusMonths(1);
        DateTime expected = new DateTime(2002, 6, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlusMonths_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusMonths(1);
        DateTime expected = new DateTime(2002, 6, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.plusMonths(0);
        assertSame(test, result);
    }

    public void testPlusMonths_intMax_1_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        assertEquals(new DateTime(178958986, 7, 20, 1, 2, 3, 4, ISO_UTC), test.plusMonths(Integer.MAX_VALUE - 2));
    }

    public void testPlusMonths_intMax_2_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        assertEquals(new DateTime(178958986, 8, 20, 1, 2, 3, 4, ISO_UTC), test.plusMonths(Integer.MAX_VALUE - 1));
    }

    public void testPlusMonths_intMax_3_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(178958986, 9, 20, 1, 2, 3, 4, ISO_UTC), test.plusMonths(Integer.MAX_VALUE));
    }

    public void testPlusMonths_intMax_4_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(new DateTime(178958986, 7, 20, 1, 2, 3, 4, ISO_UTC), test.monthOfYear().addToCopy(Integer.MAX_VALUE - 2));
    }

    public void testPlusMonths_intMax_5_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(new DateTime(178958986, 8, 20, 1, 2, 3, 4, ISO_UTC), test.monthOfYear().addToCopy(Integer.MAX_VALUE - 1));
    }

    public void testPlusMonths_intMax_6_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(178958986, 9, 20, 1, 2, 3, 4, ISO_UTC), test.monthOfYear().addToCopy(Integer.MAX_VALUE));
    }

    public void testPlusMonths_intMin_1_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        assertEquals(new DateTime(-178954955, 8, 20, 1, 2, 3, 4, ISO_UTC), test.plusMonths(Integer.MIN_VALUE + 2));
    }

    public void testPlusMonths_intMin_2_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        assertEquals(new DateTime(-178954955, 7, 20, 1, 2, 3, 4, ISO_UTC), test.plusMonths(Integer.MIN_VALUE + 1));
    }

    public void testPlusMonths_intMin_3_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(-178954955, 6, 20, 1, 2, 3, 4, ISO_UTC), test.plusMonths(Integer.MIN_VALUE));
    }

    public void testPlusMonths_intMin_4_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(new DateTime(-178954955, 8, 20, 1, 2, 3, 4, ISO_UTC), test.monthOfYear().addToCopy(Integer.MIN_VALUE + 2));
    }

    public void testPlusMonths_intMin_5_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(new DateTime(-178954955, 7, 20, 1, 2, 3, 4, ISO_UTC), test.monthOfYear().addToCopy(Integer.MIN_VALUE + 1));
    }

    public void testPlusMonths_intMin_6_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(-178954955, 6, 20, 1, 2, 3, 4, ISO_UTC), test.monthOfYear().addToCopy(Integer.MIN_VALUE));
    }

    public void testPlusWeeks_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusWeeks(1);
        DateTime expected = new DateTime(2002, 5, 10, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlusWeeks_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusWeeks(1);
        DateTime expected = new DateTime(2002, 5, 10, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.plusWeeks(0);
        assertSame(test, result);
    }

    public void testPlusDays_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusDays(1);
        DateTime expected = new DateTime(2002, 5, 4, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlusDays_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusDays(1);
        DateTime expected = new DateTime(2002, 5, 4, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.plusDays(0);
        assertSame(test, result);
    }

    public void testPlusHours_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusHours(1);
        DateTime expected = new DateTime(2002, 5, 3, 2, 2, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlusHours_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusHours(1);
        DateTime expected = new DateTime(2002, 5, 3, 2, 2, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.plusHours(0);
        assertSame(test, result);
    }

    public void testPlusMinutes_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusMinutes(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 3, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlusMinutes_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusMinutes(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 3, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.plusMinutes(0);
        assertSame(test, result);
    }

    public void testPlusSeconds_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusSeconds(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 2, 4, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlusSeconds_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusSeconds(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 2, 4, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.plusSeconds(0);
        assertSame(test, result);
    }

    public void testPlusMillis_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusMillis(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 2, 3, 5, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testPlusMillis_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.plusMillis(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 2, 3, 5, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.plusMillis(0);
        assertSame(test, result);
    }

    public void testMinus_long_1_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.minus(123456789L);
        DateTime expected = new DateTime(TEST_TIME1 - 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinus_RD_1_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.minus(new Duration(123456789L));
        DateTime expected = new DateTime(TEST_TIME1 - 123456789L, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinus_RD_2_oe() {
        DateTime test = new DateTime(TEST_TIME1, BUDDHIST_DEFAULT);
        DateTime result = test.minus(new Duration(123456789L));
        DateTime expected = new DateTime(TEST_TIME1 - 123456789L, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.minus((ReadableDuration) null);
        assertSame(test, result);
    }

    public void testMinus_RP_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        DateTime expected = new DateTime(2001, 3, 26, 0, 1, 2, 3, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinus_RP_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        DateTime expected = new DateTime(2001, 3, 26, 0, 1, 2, 3, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.minus((ReadablePeriod) null);
        assertSame(test, result);
    }

    public void testMinusYears_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusYears(1);
        DateTime expected = new DateTime(2001, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinusYears_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusYears(1);
        DateTime expected = new DateTime(2001, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.minusYears(0);
        assertSame(test, result);
    }

    public void testMinusMonths_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusMonths(1);
        DateTime expected = new DateTime(2002, 4, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinusMonths_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusMonths(1);
        DateTime expected = new DateTime(2002, 4, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.minusMonths(0);
        assertSame(test, result);
    }

    public void testMinusMonths_intMax_1_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        assertEquals(new DateTime(-178954955, 9, 20, 1, 2, 3, 4, ISO_UTC), test.minusMonths(Integer.MAX_VALUE - 2));
    }

    public void testMinusMonths_intMax_2_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        assertEquals(new DateTime(-178954955, 8, 20, 1, 2, 3, 4, ISO_UTC), test.minusMonths(Integer.MAX_VALUE - 1));
    }

    public void testMinusMonths_intMax_3_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(-178954955, 7, 20, 1, 2, 3, 4, ISO_UTC), test.minusMonths(Integer.MAX_VALUE));
    }

    public void testMinusMonths_intMin_1_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        assertEquals(new DateTime(178958986, 8, 20, 1, 2, 3, 4, ISO_UTC), test.minusMonths(Integer.MIN_VALUE + 2));
    }

    public void testMinusMonths_intMin_2_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        assertEquals(new DateTime(178958986, 9, 20, 1, 2, 3, 4, ISO_UTC), test.minusMonths(Integer.MIN_VALUE + 1));
    }

    public void testMinusMonths_intMin_3_oe() {
        DateTime test = new DateTime(2016, 2, 20, 1, 2, 3, 4, ISO_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(178958986, 10, 20, 1, 2, 3, 4, ISO_UTC), test.minusMonths(Integer.MIN_VALUE));
    }

    public void testMinusWeeks_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusWeeks(1);
        DateTime expected = new DateTime(2002, 4, 26, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinusWeeks_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusWeeks(1);
        DateTime expected = new DateTime(2002, 4, 26, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.minusWeeks(0);
        assertSame(test, result);
    }

    public void testMinusDays_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusDays(1);
        DateTime expected = new DateTime(2002, 5, 2, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinusDays_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusDays(1);
        DateTime expected = new DateTime(2002, 5, 2, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.minusDays(0);
        assertSame(test, result);
    }

    public void testMinusHours_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusHours(1);
        DateTime expected = new DateTime(2002, 5, 3, 0, 2, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinusHours_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusHours(1);
        DateTime expected = new DateTime(2002, 5, 3, 0, 2, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.minusHours(0);
        assertSame(test, result);
    }

    public void testMinusMinutes_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusMinutes(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 1, 3, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinusMinutes_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusMinutes(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 1, 3, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.minusMinutes(0);
        assertSame(test, result);
    }

    public void testMinusSeconds_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusSeconds(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 2, 2, 4, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinusSeconds_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusSeconds(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 2, 2, 4, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.minusSeconds(0);
        assertSame(test, result);
    }

    public void testMinusMillis_int_1_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusMillis(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 2, 3, 3, BUDDHIST_DEFAULT);
        assertEquals(expected, result);
    }

    public void testMinusMillis_int_2_oe() {
        DateTime test = new DateTime(2002, 5, 3, 1, 2, 3, 4, BUDDHIST_DEFAULT);
        DateTime result = test.minusMillis(1);
        DateTime expected = new DateTime(2002, 5, 3, 1, 2, 3, 3, BUDDHIST_DEFAULT);
        // removed other assertion
        
        result = test.minusMillis(0);
        assertSame(test, result);
    }

    public void testProperty_1_oe() {
        DateTime test = new DateTime();
        assertEquals(test.year(), test.property(DateTimeFieldType.year()));
    }

    public void testProperty_2_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        assertEquals(test.dayOfWeek(), test.property(DateTimeFieldType.dayOfWeek()));
    }

    public void testProperty_3_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        assertEquals(test.secondOfMinute(), test.property(DateTimeFieldType.secondOfMinute()));
    }

    public void testProperty_4_oe() {
        DateTime test = new DateTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.millisOfSecond(), test.property(DateTimeFieldType.millisOfSecond()));
    }

}
