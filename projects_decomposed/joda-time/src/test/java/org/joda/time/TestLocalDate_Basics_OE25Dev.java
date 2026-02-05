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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.LenientChronology;
import org.joda.time.chrono.StrictChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for LocalDate.
 *
 * @author Stephen Colebourne
 */
public class TestLocalDate_Basics_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final DateTimeZone NEW_YORK = DateTimeZone.forID("America/New_York");
//    private static final int OFFSET = 1;
    private static final GJChronology GJ_UTC = GJChronology.getInstanceUTC();
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
    private static final Chronology COPTIC_LONDON = CopticChronology.getInstance(LONDON);
    private static final Chronology COPTIC_TOKYO = CopticChronology.getInstance(TOKYO);
    private static final Chronology COPTIC_UTC = CopticChronology.getInstanceUTC();
//    private static final Chronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    private static final Chronology ISO_LONDON = ISOChronology.getInstance(LONDON);
    private static final Chronology ISO_NEW_YORK = ISOChronology.getInstance(NEW_YORK);
//    private static final Chronology ISO_TOKYO = ISOChronology.getInstance(TOKYO);
//    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final Chronology BUDDHIST_PARIS = BuddhistChronology.getInstance(PARIS);
    private static final Chronology BUDDHIST_LONDON = BuddhistChronology.getInstance(LONDON);
    private static final Chronology BUDDHIST_TOKYO = BuddhistChronology.getInstance(TOKYO);
//    private static final Chronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();

    /** Mock zone simulating Asia/Gaza cutover at midnight 2007-04-01 */
    private static long CUTOVER_GAZA = 1175378400000L;
    private static int OFFSET_GAZA = 7200000;  // +02:00
    private static final DateTimeZone MOCK_GAZA = new MockZone(CUTOVER_GAZA, OFFSET_GAZA, 3600);

    private long TEST_TIME_NOW =
            (31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
//    private long TEST_TIME1 =
//        (31L + 28L + 31L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
//        + 12L * DateTimeConstants.MILLIS_PER_HOUR
//        + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
//        
//    private long TEST_TIME2 =
//        (365L + 31L + 28L + 31L + 30L + 7L -1L) * DateTimeConstants.MILLIS_PER_DAY
//        + 14L * DateTimeConstants.MILLIS_PER_HOUR
//        + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    private DateTimeZone zone = null;

    private Locale systemDefaultLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestLocalDate_Basics_OE25Dev.class);
    }

    public TestLocalDate_Basics_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(LONDON);
        systemDefaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        zone = null;
        Locale.setDefault(systemDefaultLocale);
        systemDefaultLocale = null;
    }

    //-----------------------------------------------------------------------

    class MockInstant extends MockPartial {
        @Override
        public Chronology getChronology() {
            return COPTIC_UTC;
        }
        @Override
        public DateTimeField[] getFields() {
            return new DateTimeField[] {
                COPTIC_UTC.year(),
                COPTIC_UTC.monthOfYear(),
                COPTIC_UTC.dayOfMonth(),
            };
        }
        @Override
        public int[] getValues() {
            return new int[] {1970, 6, 9};
        }
    }

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testWithField_DateTimeFieldType_int_1() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(new LocalDate(2004,6,9),test);
        assertEquals(new LocalDate(2006,6,9),result);
    }

    public void testWithField_DateTimeFieldType_int_2() {
        LocalDate test = new LocalDate(2004, 6, 9);
        try {
            test.withField(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithField_DateTimeFieldType_int_3() {
        LocalDate test = new LocalDate(2004, 6, 9);
        try {
            test.withField(DateTimeFieldType.hourOfDay(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithField_DateTimeFieldType_int_4() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withField(DateTimeFieldType.year(), 2004);
        assertEquals(new LocalDate(2004,6,9),test);
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testWithFieldAdded_DurationFieldType_int_1() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(new LocalDate(2004,6,9),test);
        assertEquals(new LocalDate(2010,6,9),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_2() {
        LocalDate test = new LocalDate(2004, 6, 9);
        try {
            test.withFieldAdded(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_DurationFieldType_int_3() {
        LocalDate test = new LocalDate(2004, 6, 9);
        try {
            test.withFieldAdded(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_DurationFieldType_int_4() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertSame(test,result);
    }

    public void testWithFieldAdded_DurationFieldType_int_5() {
        LocalDate test = new LocalDate(2004, 6, 9);
        try {
            test.withFieldAdded(DurationFieldType.hours(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testWithers() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
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
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testToLocalDateTime_nullLocalTime() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        
        try {
            base.toLocalDateTime((LocalTime) null);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    public void testToLocalDateTime_wrongChronologyLocalTime() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        LocalTime tod = new LocalTime(12, 13, 14, 15, BUDDHIST_PARIS); // PARIS irrelevant
        
        try {
            base.toLocalDateTime(tod);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testToDateTime_LocalTime_Zone_dstGap() {
        LocalDate base = new LocalDate(2014, 3, 30, ISO_LONDON);
        LocalTime tod = new LocalTime(1, 30, 0, 0, ISO_LONDON);
        try {
            base.toDateTime(tod, LONDON);
            fail();
        } catch (IllegalInstantException ex) {}
    }

    public void testToDateTime_wrongChronoLocalTime_Zone() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        LocalTime tod = new LocalTime(12, 13, 14, 15, BUDDHIST_TOKYO);
        
        try {
            base.toDateTime(tod, LONDON);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testToDate_springDST() {
        LocalDate base = new LocalDate(2007, 4, 2);
        
        SimpleTimeZone testZone = new SimpleTimeZone(3600000, "NoMidnight",
                Calendar.APRIL, 2, 0, 0, Calendar.OCTOBER, 2, 0, 3600000);
        TimeZone currentZone = TimeZone.getDefault();
        try {
            TimeZone.setDefault(testZone);
            Date test = base.toDate();
            check(base, 2007, 4, 2);
            assertEquals("Mon Apr 02 01:00:00 GMT+02:00 2007",test.toString());
        } finally {
            TimeZone.setDefault(currentZone);
        }
    }

    public void testToDate_springDST_2Hour40Savings() {
        LocalDate base = new LocalDate(2007, 4, 2);
        
        SimpleTimeZone testZone = new SimpleTimeZone(3600000, "NoMidnight",
                Calendar.APRIL, 2, 0, 0, Calendar.OCTOBER, 2, 0, 3600000, (3600000 / 6) * 16);
        TimeZone currentZone = TimeZone.getDefault();
        try {
            TimeZone.setDefault(testZone);
            Date test = base.toDate();
            check(base, 2007, 4, 2);
            assertEquals("Mon Apr 02 02:40:00 GMT+03:40 2007",test.toString());
        } finally {
            TimeZone.setDefault(currentZone);
        }
    }

    public void testToDate_autumnDST() {
        LocalDate base = new LocalDate(2007, 10, 2);
        
        SimpleTimeZone testZone = new SimpleTimeZone(3600000, "NoMidnight",
                Calendar.APRIL, 2, 0, 0, Calendar.OCTOBER, 2, 0, 3600000);
        TimeZone currentZone = TimeZone.getDefault();
        try {
            TimeZone.setDefault(testZone);
            Date test = base.toDate();
            check(base, 2007, 10, 2);
            assertEquals("Tue Oct 02 00:00:00 GMT+02:00 2007",test.toString());
        } finally {
            TimeZone.setDefault(currentZone);
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    private void check(LocalDate test, int hour, int min, int sec) {
        assertEquals(hour,test.getYear());
        assertEquals(min,test.getMonthOfYear());
        assertEquals(sec,test.getDayOfMonth());
    }

public void testGet_DateTimeFieldType_1_oe() {
        LocalDate test = new LocalDate();
        assertEquals(1970,test.get(DateTimeFieldType.year()));
    }

public void testGet_DateTimeFieldType_2_oe() {
        LocalDate test = new LocalDate();
        // removed other assertion
        assertEquals(6,test.get(DateTimeFieldType.monthOfYear()));
    }

public void testGet_DateTimeFieldType_3_oe() {
        LocalDate test = new LocalDate();
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.get(DateTimeFieldType.dayOfMonth()));
    }

public void testGet_DateTimeFieldType_4_oe() {
        LocalDate test = new LocalDate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.get(DateTimeFieldType.dayOfWeek()));
    }

public void testGet_DateTimeFieldType_5_oe() {
        LocalDate test = new LocalDate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(160,test.get(DateTimeFieldType.dayOfYear()));
    }

public void testGet_DateTimeFieldType_6_oe() {
        LocalDate test = new LocalDate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(24,test.get(DateTimeFieldType.weekOfWeekyear()));
    }

public void testGet_DateTimeFieldType_7_oe() {
        LocalDate test = new LocalDate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1970,test.get(DateTimeFieldType.weekyear()));
    }

public void testSize_1_oe() {
        LocalDate test = new LocalDate();
        assertEquals(3,test.size());
    }

public void testGetFieldType_int_1_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        assertSame(DateTimeFieldType.year(),test.getFieldType(0));
    }

public void testGetFieldType_int_2_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        assertSame(DateTimeFieldType.monthOfYear(),test.getFieldType(1));
    }

public void testGetFieldType_int_3_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.dayOfMonth(),test.getFieldType(2));
    }

public void testGetFieldTypes_1_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        assertSame(DateTimeFieldType.year(),fields[0]);
    }

public void testGetFieldTypes_2_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        assertSame(DateTimeFieldType.monthOfYear(),fields[1]);
    }

public void testGetFieldTypes_3_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.dayOfMonth(),fields[2]);
    }

public void testGetFieldTypes_4_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFieldTypes(),test.getFieldTypes());
    }

public void testGetField_int_1_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        assertSame(COPTIC_UTC.year(),test.getField(0));
    }

public void testGetField_int_2_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        assertSame(COPTIC_UTC.monthOfYear(),test.getField(1));
    }

public void testGetField_int_3_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.dayOfMonth(),test.getField(2));
    }

public void testGetFields_1_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        assertSame(COPTIC_UTC.year(),fields[0]);
    }

public void testGetFields_2_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        assertSame(COPTIC_UTC.monthOfYear(),fields[1]);
    }

public void testGetFields_3_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.dayOfMonth(),fields[2]);
    }

public void testGetFields_4_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFields(),test.getFields());
    }

public void testGetValue_int_1_oe() {
        LocalDate test = new LocalDate();
        assertEquals(1970,test.getValue(0));
    }

public void testGetValue_int_2_oe() {
        LocalDate test = new LocalDate();
        // removed other assertion
        assertEquals(6,test.getValue(1));
    }

public void testGetValue_int_3_oe() {
        LocalDate test = new LocalDate();
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getValue(2));
    }

public void testGetValues_1_oe() {
        LocalDate test = new LocalDate();
        int[] values = test.getValues();
        assertEquals(1970,values[0]);
    }

public void testGetValues_2_oe() {
        LocalDate test = new LocalDate();
        int[] values = test.getValues();
        // removed other assertion
        assertEquals(6,values[1]);
    }

public void testGetValues_3_oe() {
        LocalDate test = new LocalDate();
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        assertEquals(9,values[2]);
    }

public void testGetValues_4_oe() {
        LocalDate test = new LocalDate();
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getValues(),test.getValues());
    }

public void testIsSupported_DateTimeFieldType_1_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        assertEquals(true,test.isSupported(DateTimeFieldType.year()));
    }

public void testIsSupported_DateTimeFieldType_2_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.monthOfYear()));
    }

public void testIsSupported_DateTimeFieldType_3_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.dayOfMonth()));
    }

public void testIsSupported_DateTimeFieldType_4_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.dayOfWeek()));
    }

public void testIsSupported_DateTimeFieldType_5_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.dayOfYear()));
    }

public void testIsSupported_DateTimeFieldType_6_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.weekOfWeekyear()));
    }

public void testIsSupported_DateTimeFieldType_7_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.weekyear()));
    }

public void testIsSupported_DateTimeFieldType_8_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.yearOfCentury()));
    }

public void testIsSupported_DateTimeFieldType_9_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.yearOfEra()));
    }

public void testIsSupported_DateTimeFieldType_10_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.centuryOfEra()));
    }

public void testIsSupported_DateTimeFieldType_11_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.weekyearOfCentury()));
    }

public void testIsSupported_DateTimeFieldType_12_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.era()));
    }

public void testIsSupported_DateTimeFieldType_13_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test.isSupported(DateTimeFieldType.hourOfDay()));
    }

public void testIsSupported_DateTimeFieldType_14_oe() {
        LocalDate test = new LocalDate(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test.isSupported((DateTimeFieldType)null));
    }

public void testIsSupported_DurationFieldType_1_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        assertEquals(false,test.isSupported(DurationFieldType.eras()));
    }

public void testIsSupported_DurationFieldType_2_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        assertEquals(true,test.isSupported(DurationFieldType.centuries()));
    }

public void testIsSupported_DurationFieldType_3_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DurationFieldType.years()));
    }

public void testIsSupported_DurationFieldType_4_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DurationFieldType.months()));
    }

public void testIsSupported_DurationFieldType_5_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DurationFieldType.weekyears()));
    }

public void testIsSupported_DurationFieldType_6_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DurationFieldType.weeks()));
    }

public void testIsSupported_DurationFieldType_7_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DurationFieldType.days()));
    }

public void testIsSupported_DurationFieldType_8_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test.isSupported(DurationFieldType.hours()));
    }

public void testIsSupported_DurationFieldType_9_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false,test.isSupported((DurationFieldType)null));
    }

public void testEqualsHashCode_1_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        assertEquals(true,test1.equals(test2));
    }

public void testEqualsHashCode_2_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        assertEquals(true,test2.equals(test1));
    }

public void testEqualsHashCode_3_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.equals(test1));
    }

public void testEqualsHashCode_4_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.equals(test2));
    }

public void testEqualsHashCode_5_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test2.hashCode());
    }

public void testEqualsHashCode_6_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test1.hashCode());
    }

public void testEqualsHashCode_7_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

public void testEqualsHashCode_8_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
        assertEquals(false,test1.equals(test3));
    }

public void testEqualsHashCode_9_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
        // removed other assertion
        assertEquals(false,test2.equals(test3));
    }

public void testEqualsHashCode_10_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test1));
    }

public void testEqualsHashCode_11_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test2));
    }

public void testEqualsHashCode_12_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.hashCode()== test3.hashCode());
    }

public void testEqualsHashCode_13_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test2.hashCode()== test3.hashCode());
    }

public void testEqualsHashCode_14_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test1.equals("Hello"));
    }

public void testEqualsHashCode_15_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
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
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.equals(new YearMonthDay(1970,6,9,COPTIC_PARIS)));
    }

public void testEqualsHashCode_17_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== new YearMonthDay(1970,6,9,COPTIC_PARIS).hashCode());
    }

public void testEqualsHashCode_18_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        LocalDate test2 = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.equals(MockPartial.EMPTY_INSTANCE));
    }

public void testEqualsHashCodeLenient_1_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.equals(test2));
    }

public void testEqualsHashCodeLenient_2_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        assertEquals(true,test2.equals(test1));
    }

public void testEqualsHashCodeLenient_3_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.equals(test1));
    }

public void testEqualsHashCodeLenient_4_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.equals(test2));
    }

public void testEqualsHashCodeLenient_5_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test2.hashCode());
    }

public void testEqualsHashCodeLenient_6_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test1.hashCode());
    }

public void testEqualsHashCodeLenient_7_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

public void testEqualsHashCodeStrict_1_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.equals(test2));
    }

public void testEqualsHashCodeStrict_2_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        assertEquals(true,test2.equals(test1));
    }

public void testEqualsHashCodeStrict_3_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.equals(test1));
    }

public void testEqualsHashCodeStrict_4_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.equals(test2));
    }

public void testEqualsHashCodeStrict_5_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test2.hashCode());
    }

public void testEqualsHashCodeStrict_6_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test1.hashCode());
    }

public void testEqualsHashCodeStrict_7_oe() {
        LocalDate test1 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        LocalDate test2 = new LocalDate(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

public void testEqualsHashCodeAPI_1_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, COPTIC_PARIS);
        int expected = 157;
        expected = 23 * expected + 1970;
        expected = 23 * expected + COPTIC_UTC.year().getType().hashCode();
        expected = 23 * expected + 6;
        expected = 23 * expected + COPTIC_UTC.monthOfYear().getType().hashCode();
        expected = 23 * expected + 9;
        expected = 23 * expected + COPTIC_UTC.dayOfMonth().getType().hashCode();
        expected += COPTIC_UTC.hashCode();
        assertEquals(expected,test.hashCode());
    }

public void testCompareTo_1_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        assertEquals(0,test1.compareTo(test1a));
    }

public void testCompareTo_2_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        assertEquals(0,test1a.compareTo(test1));
    }

public void testCompareTo_3_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        assertEquals(0,test1.compareTo(test1));
    }

public void testCompareTo_4_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test1a.compareTo(test1a));
    }

public void testCompareTo_5_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        assertEquals(-1,test1.compareTo(test2));
    }

public void testCompareTo_6_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        assertEquals(+1,test2.compareTo(test1));
    }

public void testCompareTo_7_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        assertEquals(-1,test1.compareTo(test3));
    }

public void testCompareTo_8_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(+1,test3.compareTo(test1));
    }

public void testCompareTo_9_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(0,test3.compareTo(test2));
    }

public void testCompareTo_10_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 2};
        Partial p = new Partial(types, values);
        assertEquals(0,test1.compareTo(p));
    }

public void testCompareTo_11_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 2};
        Partial p = new Partial(types, values);
        // removed other assertion
        assertEquals(0,test1.compareTo(new YearMonthDay(2005,6,2)));
    }

public void testIsEqual_LocalDate_1_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        assertEquals(true,test1.isEqual(test1a));
    }

public void testIsEqual_LocalDate_2_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        assertEquals(true,test1a.isEqual(test1));
    }

public void testIsEqual_LocalDate_3_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.isEqual(test1));
    }

public void testIsEqual_LocalDate_4_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1a.isEqual(test1a));
    }

public void testIsEqual_LocalDate_5_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        assertEquals(false,test1.isEqual(test2));
    }

public void testIsEqual_LocalDate_6_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        assertEquals(false,test2.isEqual(test1));
    }

public void testIsEqual_LocalDate_7_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        assertEquals(false,test1.isEqual(test3));
    }

public void testIsEqual_LocalDate_8_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(false,test3.isEqual(test1));
    }

public void testIsEqual_LocalDate_9_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(true,test3.isEqual(test2));
    }

public void testIsBefore_LocalDate_1_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        assertEquals(false,test1.isBefore(test1a));
    }

public void testIsBefore_LocalDate_2_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        assertEquals(false,test1a.isBefore(test1));
    }

public void testIsBefore_LocalDate_3_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.isBefore(test1));
    }

public void testIsBefore_LocalDate_4_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1a.isBefore(test1a));
    }

public void testIsBefore_LocalDate_5_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        assertEquals(true,test1.isBefore(test2));
    }

public void testIsBefore_LocalDate_6_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        assertEquals(false,test2.isBefore(test1));
    }

public void testIsBefore_LocalDate_7_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        assertEquals(true,test1.isBefore(test3));
    }

public void testIsBefore_LocalDate_8_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(false,test3.isBefore(test1));
    }

public void testIsBefore_LocalDate_9_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.isBefore(test2));
    }

public void testIsAfter_LocalDate_1_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        assertEquals(false,test1.isAfter(test1a));
    }

public void testIsAfter_LocalDate_2_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        assertEquals(false,test1a.isAfter(test1));
    }

public void testIsAfter_LocalDate_3_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.isAfter(test1));
    }

public void testIsAfter_LocalDate_4_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1a.isAfter(test1a));
    }

public void testIsAfter_LocalDate_5_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        assertEquals(false,test1.isAfter(test2));
    }

public void testIsAfter_LocalDate_6_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        assertEquals(true,test2.isAfter(test1));
    }

public void testIsAfter_LocalDate_7_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        assertEquals(false,test1.isAfter(test3));
    }

public void testIsAfter_LocalDate_8_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(true,test3.isAfter(test1));
    }

public void testIsAfter_LocalDate_9_oe() {
        LocalDate test1 = new LocalDate(2005, 6, 2);
        LocalDate test1a = new LocalDate(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalDate test2 = new LocalDate(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        LocalDate test3 = new LocalDate(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.isAfter(test2));
    }

public void testWithField_DateTimeFieldType_int_1_1_oe() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(new LocalDate(2004,6,9),test);
    }

public void testWithField_DateTimeFieldType_int_1_2_oe() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withField(DateTimeFieldType.year(), 2006);
        
        // removed other assertion
        assertEquals(new LocalDate(2006,6,9),result);
    }

public void testWithField_DateTimeFieldType_int_4_1_oe() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withField(DateTimeFieldType.year(), 2004);
        assertEquals(new LocalDate(2004,6,9),test);
    }

public void testWithField_DateTimeFieldType_int_4_2_oe() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withField(DateTimeFieldType.year(), 2004);
        // removed other assertion
        assertSame(test,result);
    }

public void testWithFieldAdded_DurationFieldType_int_1_1_oe() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(new LocalDate(2004,6,9),test);
    }

public void testWithFieldAdded_DurationFieldType_int_1_2_oe() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        // removed other assertion
        assertEquals(new LocalDate(2010,6,9),result);
    }

public void testWithFieldAdded_DurationFieldType_int_4_1_oe() {
        LocalDate test = new LocalDate(2004, 6, 9);
        LocalDate result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertSame(test,result);
    }

public void testPlus_RP_1_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.plus(new Period(1, 2, 3, 4, 29, 6, 7, 8));
        LocalDate expected = new LocalDate(2003, 7, 28, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

public void testPlus_RP_2_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.plus(new Period(1, 2, 3, 4, 29, 6, 7, 8));
        LocalDate expected = new LocalDate(2003, 7, 28, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plus((ReadablePeriod) null);
        assertSame(test,result);
    }

public void testPlusYears_int_1_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.plusYears(1);
        LocalDate expected = new LocalDate(2003, 5, 3, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

public void testPlusYears_int_2_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.plusYears(1);
        LocalDate expected = new LocalDate(2003, 5, 3, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusYears(0);
        assertSame(test,result);
    }

public void testPlusMonths_int_1_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.plusMonths(1);
        LocalDate expected = new LocalDate(2002, 6, 3, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

public void testPlusMonths_int_2_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.plusMonths(1);
        LocalDate expected = new LocalDate(2002, 6, 3, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusMonths(0);
        assertSame(test,result);
    }

public void testPlusWeeks_int_1_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.plusWeeks(1);
        LocalDate expected = new LocalDate(2002, 5, 10, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

public void testPlusWeeks_int_2_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.plusWeeks(1);
        LocalDate expected = new LocalDate(2002, 5, 10, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusWeeks(0);
        assertSame(test,result);
    }

public void testPlusDays_int_1_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.plusDays(1);
        LocalDate expected = new LocalDate(2002, 5, 4, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

public void testPlusDays_int_2_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.plusDays(1);
        LocalDate expected = new LocalDate(2002, 5, 4, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusDays(0);
        assertSame(test,result);
    }

public void testMinus_RP_1_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        
        // TODO breaks because it subtracts millis now, and thus goes
        // into the previous day
        
        LocalDate expected = new LocalDate(2001, 3, 26, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

public void testMinus_RP_2_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        
        // TODO breaks because it subtracts millis now, and thus goes
        // into the previous day
        
        LocalDate expected = new LocalDate(2001, 3, 26, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minus((ReadablePeriod) null);
        assertSame(test,result);
    }

public void testMinusYears_int_1_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.minusYears(1);
        LocalDate expected = new LocalDate(2001, 5, 3, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

public void testMinusYears_int_2_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.minusYears(1);
        LocalDate expected = new LocalDate(2001, 5, 3, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusYears(0);
        assertSame(test,result);
    }

public void testMinusMonths_int_1_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.minusMonths(1);
        LocalDate expected = new LocalDate(2002, 4, 3, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

public void testMinusMonths_int_2_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.minusMonths(1);
        LocalDate expected = new LocalDate(2002, 4, 3, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusMonths(0);
        assertSame(test,result);
    }

public void testMinusWeeks_int_1_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.minusWeeks(1);
        LocalDate expected = new LocalDate(2002, 4, 26, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

public void testMinusWeeks_int_2_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.minusWeeks(1);
        LocalDate expected = new LocalDate(2002, 4, 26, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusWeeks(0);
        assertSame(test,result);
    }

public void testMinusDays_int_1_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.minusDays(1);
        LocalDate expected = new LocalDate(2002, 5, 2, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

public void testMinusDays_int_2_oe() {
        LocalDate test = new LocalDate(2002, 5, 3, BUDDHIST_LONDON);
        LocalDate result = test.minusDays(1);
        LocalDate expected = new LocalDate(2002, 5, 2, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusDays(0);
        assertSame(test,result);
    }

public void testGetters_1_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        assertEquals(1970,test.getYear());
    }

public void testGetters_2_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        // removed other assertion
        assertEquals(6,test.getMonthOfYear());
    }

public void testGetters_3_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getDayOfMonth());
    }

public void testGetters_4_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(160,test.getDayOfYear());
    }

public void testGetters_5_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.getDayOfWeek());
    }

public void testGetters_6_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(24,test.getWeekOfWeekyear());
    }

public void testGetters_7_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1970,test.getWeekyear());
    }

public void testGetters_8_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(70,test.getYearOfCentury());
    }

public void testGetters_9_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(20,test.getCenturyOfEra());
    }

public void testGetters_10_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1970,test.getYearOfEra());
    }

public void testGetters_11_oe() {
        LocalDate test = new LocalDate(1970, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.AD,test.getEra());
    }

public void testToDateTimeAtStartOfDay_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS);
        
        DateTime test = base.toDateTimeAtStartOfDay();
        check(base, 2005, 6, 9);
        assertEquals(new DateTime(2005,6,9,0,0,0,0,COPTIC_LONDON),test);
    }

public void testToDateTimeAtStartOfDay_avoidDST_1_oe() {
        LocalDate base = new LocalDate(2007, 4, 1);
        
        DateTimeZone.setDefault(MOCK_GAZA);
        DateTime test = base.toDateTimeAtStartOfDay();
        check(base, 2007, 4, 1);
        assertEquals(new DateTime(2007,4,1,1,0,0,0,MOCK_GAZA),test);
    }

public void testToDateTimeAtStartOfDay_handleMidnightDST_1_oe() {
        LocalDate test = new LocalDate(2018, 10, 28);
        DateTime result = test.toDateTimeAtStartOfDay(DateTimeZone.forID("Atlantic/Azores"));
        DateTime expected = new DateTime(2018, 10, 28, 0, 0, DateTimeZone.forID("Atlantic/Azores"));
        assertEquals(expected,result);
    }

public void testToDateTimeAtStartOfDay_Zone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS);
        
        DateTime test = base.toDateTimeAtStartOfDay(TOKYO);
        check(base, 2005, 6, 9);
        assertEquals(new DateTime(2005,6,9,0,0,0,0,COPTIC_TOKYO),test);
    }

public void testToDateTimeAtStartOfDay_Zone_avoidDST_1_oe() {
        LocalDate base = new LocalDate(2007, 4, 1);
        
        DateTime test = base.toDateTimeAtStartOfDay(MOCK_GAZA);
        check(base, 2007, 4, 1);
        assertEquals(new DateTime(2007,4,1,1,0,0,0,MOCK_GAZA),test);
    }

public void testToDateTimeAtStartOfDay_nullZone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS);
        
        DateTime test = base.toDateTimeAtStartOfDay((DateTimeZone) null);
        check(base, 2005, 6, 9);
        assertEquals(new DateTime(2005,6,9,0,0,0,0,COPTIC_LONDON),test);
    }

public void testToDateTimeAtMidnight_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS);
        
        DateTime test = base.toDateTimeAtMidnight();
        check(base, 2005, 6, 9);
        assertEquals(new DateTime(2005,6,9,0,0,0,0,COPTIC_LONDON),test);
    }

public void testToDateTimeAtMidnight_Zone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS);
        
        DateTime test = base.toDateTimeAtMidnight(TOKYO);
        check(base, 2005, 6, 9);
        assertEquals(new DateTime(2005,6,9,0,0,0,0,COPTIC_TOKYO),test);
    }

public void testToDateTimeAtMidnight_nullZone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS);
        
        DateTime test = base.toDateTimeAtMidnight((DateTimeZone) null);
        check(base, 2005, 6, 9);
        assertEquals(new DateTime(2005,6,9,0,0,0,0,COPTIC_LONDON),test);
    }

public void testToDateTimeAtCurrentTime_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        DateTime dt = new DateTime(2004, 6, 9, 6, 7, 8, 9);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        DateTime test = base.toDateTimeAtCurrentTime();
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(dt.getMillis(), COPTIC_LONDON);
        expected = expected.year().setCopy(2005);
        expected = expected.monthOfYear().setCopy(6);
        expected = expected.dayOfMonth().setCopy(9);
        assertEquals(expected,test);
    }

public void testToDateTimeAtCurrentTime_Zone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        DateTime dt = new DateTime(2004, 6, 9, 6, 7, 8, 9);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        DateTime test = base.toDateTimeAtCurrentTime(TOKYO);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(dt.getMillis(), COPTIC_TOKYO);
        expected = expected.year().setCopy(2005);
        expected = expected.monthOfYear().setCopy(6);
        expected = expected.dayOfMonth().setCopy(9);
        assertEquals(expected,test);
    }

public void testToDateTimeAtCurrentTime_nullZone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        DateTime dt = new DateTime(2004, 6, 9, 6, 7, 8, 9);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        DateTime test = base.toDateTimeAtCurrentTime((DateTimeZone) null);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(dt.getMillis(), COPTIC_LONDON);
        expected = expected.year().setCopy(2005);
        expected = expected.monthOfYear().setCopy(6);
        expected = expected.dayOfMonth().setCopy(9);
        assertEquals(expected,test);
    }

public void testToLocalDateTime_LocalTime_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        LocalTime tod = new LocalTime(12, 13, 14, 15, COPTIC_TOKYO);
        
        LocalDateTime test = base.toLocalDateTime(tod);
        check(base, 2005, 6, 9);
        LocalDateTime expected = new LocalDateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_UTC);
        assertEquals(expected,test);
    }

public void testToDateTime_LocalTime_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        LocalTime tod = new LocalTime(12, 13, 14, 15, COPTIC_TOKYO);
        
        DateTime test = base.toDateTime(tod);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_LONDON);
        assertEquals(expected,test);
    }

public void testToDateTime_nullLocalTime_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        long now = new DateTime(2004, 5, 8, 12, 13, 14, 15, COPTIC_LONDON).getMillis();
        DateTimeUtils.setCurrentMillisFixed(now);
        
        DateTime test = base.toDateTime((LocalTime) null);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_LONDON);
        assertEquals(expected,test);
    }

public void testToDateTime_LocalTime_Zone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        LocalTime tod = new LocalTime(12, 13, 14, 15, COPTIC_TOKYO);
        
        DateTime test = base.toDateTime(tod, TOKYO);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_TOKYO);
        assertEquals(expected,test);
    }

public void testToDateTime_LocalTime_nullZone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        LocalTime tod = new LocalTime(12, 13, 14, 15, COPTIC_TOKYO);
        
        DateTime test = base.toDateTime(tod, null);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_LONDON);
        assertEquals(expected,test);
    }

public void testToDateTime_nullLocalTime_Zone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        long now = new DateTime(2004, 5, 8, 12, 13, 14, 15, COPTIC_TOKYO).getMillis();
        DateTimeUtils.setCurrentMillisFixed(now);
        
        DateTime test = base.toDateTime((LocalTime) null, TOKYO);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_TOKYO);
        assertEquals(expected,test);
    }

public void testToDateTime_LocalTime_Zone_dstOverlap_1_oe() {
        LocalDate base = new LocalDate(2014, 10, 26, ISO_LONDON);
        LocalTime tod = new LocalTime(1, 30, 0, 0, ISO_LONDON);
        DateTime test = base.toDateTime(tod, LONDON);
        DateTime expected = new DateTime(2014, 10, 26, 1, 30, ISO_LONDON).withEarlierOffsetAtOverlap();
        assertEquals(expected,test);
    }

public void testToDateTime_LocalTime_Zone_dstOverlap_NewYork_1_oe() {
        LocalDate base = new LocalDate(2007, 11, 4, ISO_NEW_YORK);
        LocalTime tod = new LocalTime(1, 30, 0, 0, ISO_NEW_YORK);
        DateTime test = base.toDateTime(tod, NEW_YORK);
        DateTime expected = new DateTime(2007, 11, 4, 1, 30, ISO_NEW_YORK).withEarlierOffsetAtOverlap();
        assertEquals(expected,test);
    }

public void testToDateMidnight_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS);
        
        DateMidnight test = base.toDateMidnight();
        check(base, 2005, 6, 9);
        assertEquals(new DateMidnight(2005,6,9,COPTIC_LONDON),test);
    }

public void testToDateMidnight_Zone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS);
        
        DateMidnight test = base.toDateMidnight(TOKYO);
        check(base, 2005, 6, 9);
        assertEquals(new DateMidnight(2005,6,9,COPTIC_TOKYO),test);
    }

public void testToDateMidnight_nullZone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS);
        
        DateMidnight test = base.toDateMidnight((DateTimeZone) null);
        check(base, 2005, 6, 9);
        assertEquals(new DateMidnight(2005,6,9,COPTIC_LONDON),test);
    }

public void testToDateTime_RI_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS);
        DateTime dt = new DateTime(2002, 1, 3, 4, 5, 6, 7);
        
        DateTime test = base.toDateTime(dt);
        check(base, 2005, 6, 9);
        DateTime expected = dt;
        expected = expected.year().setCopy(2005);
        expected = expected.monthOfYear().setCopy(6);
        expected = expected.dayOfMonth().setCopy(9);
        assertEquals(expected,test);
    }

public void testToDateTime_nullRI_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9);
        DateTime dt = new DateTime(2002, 1, 3, 4, 5, 6, 7);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        DateTime test = base.toDateTime((ReadableInstant) null);
        check(base, 2005, 6, 9);
        DateTime expected = dt;
        expected = expected.year().setCopy(2005);
        expected = expected.monthOfYear().setCopy(6);
        expected = expected.dayOfMonth().setCopy(9);
        assertEquals(expected,test);
    }

public void testToInterval_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval();
        check(base, 2005, 6, 9);
        DateTime start = base.toDateTimeAtStartOfDay();
        DateTime end = start.plus(Period.days(1));
        Interval expected = new Interval(start, end);
        assertEquals(expected,test);
    }

public void testToInterval_Zone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval(TOKYO);
        check(base, 2005, 6, 9);
        DateTime start = base.toDateTimeAtStartOfDay(TOKYO);
        DateTime end = start.plus(Period.days(1));
        Interval expected = new Interval(start, end);
        assertEquals(expected,test);
    }

public void testToInterval_Zone_noMidnight_1_oe() {
        LocalDate base = new LocalDate(2006, 4, 1, ISO_LONDON);  // LONDON irrelevant
        DateTimeZone gaza = DateTimeZone.forID("Asia/Gaza");
        Interval test = base.toInterval(gaza);
        check(base, 2006, 4, 1);
        DateTime start = new DateTime(2006, 4, 1, 1, 0, 0, 0, gaza);
        DateTime end = new DateTime(2006, 4, 2, 0, 0, 0, 0, gaza);
        Interval expected = new Interval(start, end);
        assertEquals(expected,test);
    }

public void testToInterval_nullZone_1_oe() {
        LocalDate base = new LocalDate(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval(null);
        check(base, 2005, 6, 9);
        DateTime start = base.toDateTimeAtStartOfDay(LONDON);
        DateTime end = start.plus(Period.days(1));
        Interval expected = new Interval(start, end);
        assertEquals(expected,test);
    }

public void testToDate_summer_1_oe() {
        LocalDate base = new LocalDate(2005, 7, 9, COPTIC_PARIS);
        
        Date test = base.toDate();
        check(base, 2005, 7, 9);
        
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.clear();
        gcal.set(Calendar.YEAR, 2005);
        gcal.set(Calendar.MONTH, Calendar.JULY);
        gcal.set(Calendar.DAY_OF_MONTH, 9);
        assertEquals(gcal.getTime(),test);
    }

public void testToDate_winter_1_oe() {
        LocalDate base = new LocalDate(2005, 1, 9, COPTIC_PARIS);
        
        Date test = base.toDate();
        check(base, 2005, 1, 9);
        
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.clear();
        gcal.set(Calendar.YEAR, 2005);
        gcal.set(Calendar.MONTH, Calendar.JANUARY);
        gcal.set(Calendar.DAY_OF_MONTH, 9);
        assertEquals(gcal.getTime(),test);
    }

public void testProperty_1_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        assertEquals(test.year(),test.property(DateTimeFieldType.year()));
    }

public void testProperty_2_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        // removed other assertion
        assertEquals(test.monthOfYear(),test.property(DateTimeFieldType.monthOfYear()));
    }

public void testProperty_3_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(test.dayOfMonth(),test.property(DateTimeFieldType.dayOfMonth()));
    }

public void testProperty_4_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.dayOfWeek(),test.property(DateTimeFieldType.dayOfWeek()));
    }

public void testProperty_5_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.dayOfYear(),test.property(DateTimeFieldType.dayOfYear()));
    }

public void testProperty_6_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.weekOfWeekyear(),test.property(DateTimeFieldType.weekOfWeekyear()));
    }

public void testProperty_7_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.weekyear(),test.property(DateTimeFieldType.weekyear()));
    }

public void testProperty_8_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.yearOfCentury(),test.property(DateTimeFieldType.yearOfCentury()));
    }

public void testProperty_9_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.yearOfEra(),test.property(DateTimeFieldType.yearOfEra()));
    }

public void testProperty_10_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.centuryOfEra(),test.property(DateTimeFieldType.centuryOfEra()));
    }

public void testProperty_11_oe() {
        LocalDate test = new LocalDate(2005, 6, 9, GJ_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.era(),test.property(DateTimeFieldType.era()));
    }

public void testSerialization_1_oe() throws Exception {
        LocalDate test = new LocalDate(1972, 6, 9, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalDate result = (LocalDate) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

public void testSerialization_2_oe() throws Exception {
        LocalDate test = new LocalDate(1972, 6, 9, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalDate result = (LocalDate) ois.readObject();
        ois.close();
        
        // removed other assertion
        assertTrue(Arrays.equals(test.getValues(),result.getValues()));
    }

public void testSerialization_3_oe() throws Exception {
        LocalDate test = new LocalDate(1972, 6, 9, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalDate result = (LocalDate) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(test.getFields(),result.getFields()));
    }

public void testSerialization_4_oe() throws Exception {
        LocalDate test = new LocalDate(1972, 6, 9, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalDate result = (LocalDate) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology(),result.getChronology());
    }

public void testToString_1_oe() {
        LocalDate test = new LocalDate(2002, 6, 9);
        assertEquals("2002-06-09",test.toString());
    }

public void testToString_String_1_oe() {
        LocalDate test = new LocalDate(2002, 6, 9);
        assertEquals("2002 \ufffd\ufffd",test.toString("yyyy HH"));
    }

public void testToString_String_2_oe() {
        LocalDate test = new LocalDate(2002, 6, 9);
        // removed other assertion
        assertEquals("2002-06-09",test.toString((String)null));
    }

public void testToString_String_Locale_1_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        assertEquals("Tue 9/6",test.toString("EEE d/M",Locale.ENGLISH));
    }

public void testToString_String_Locale_2_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        assertEquals("mar. 9/6",test.toString("EEE d/M",Locale.FRENCH));
    }

public void testToString_String_Locale_3_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        assertEquals("1970-06-09",test.toString(null,Locale.ENGLISH));
    }

public void testToString_String_Locale_4_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Tue 9/6",test.toString("EEE d/M",null));
    }

public void testToString_String_Locale_5_oe() {
        LocalDate test = new LocalDate(1970, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1970-06-09",test.toString(null,null));
    }

public void testToString_DTFormatter_1_oe() {
        LocalDate test = new LocalDate(2002, 6, 9);
        assertEquals("2002 \ufffd\ufffd",test.toString(DateTimeFormat.forPattern("yyyy HH")));
    }

public void testToString_DTFormatter_2_oe() {
        LocalDate test = new LocalDate(2002, 6, 9);
        // removed other assertion
        assertEquals("2002-06-09",test.toString((DateTimeFormatter)null));
    }

}
