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
import java.util.Locale;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for LocalTime.
 *
 * @author Stephen Colebourne
 */
public class TestLocalTime_Basics_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
    private static final Chronology COPTIC_LONDON = CopticChronology.getInstance(LONDON);
    private static final Chronology COPTIC_TOKYO = CopticChronology.getInstance(TOKYO);
    private static final Chronology COPTIC_UTC = CopticChronology.getInstanceUTC();
    private static final Chronology BUDDHIST_LONDON = BuddhistChronology.getInstance(LONDON);

    private long TEST_TIME_NOW =
            10L * DateTimeConstants.MILLIS_PER_HOUR
            + 20L * DateTimeConstants.MILLIS_PER_MINUTE
            + 30L * DateTimeConstants.MILLIS_PER_SECOND
            + 40L;

//    private long TEST_TIME1 =
//        1L * DateTimeConstants.MILLIS_PER_HOUR
//        + 2L * DateTimeConstants.MILLIS_PER_MINUTE
//        + 3L * DateTimeConstants.MILLIS_PER_SECOND
//        + 4L;

    private long TEST_TIME2 =
        1L * DateTimeConstants.MILLIS_PER_DAY
        + 5L * DateTimeConstants.MILLIS_PER_HOUR
        + 6L * DateTimeConstants.MILLIS_PER_MINUTE
        + 7L * DateTimeConstants.MILLIS_PER_SECOND
        + 8L;

    private DateTimeZone zone = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestLocalTime_Basics_OE25Dev_OE25Dev.class);
    }

    public TestLocalTime_Basics_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(LONDON);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        zone = null;
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
                COPTIC_UTC.hourOfDay(),
                COPTIC_UTC.minuteOfHour(),
                COPTIC_UTC.secondOfMinute(),
                COPTIC_UTC.millisOfSecond(),
            };
        }
        @Override
        public int[] getValues() {
            return new int[] {10, 20, 30, 40};
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testWithField_DateTimeFieldType_int_1() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withField(DateTimeFieldType.hourOfDay(), 15);
        
        assertEquals(new LocalTime(10,20,30,40),test);
        assertEquals(new LocalTime(15,20,30,40),result);
    }

    public void testWithField_DateTimeFieldType_int_2() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        try {
            test.withField(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithField_DateTimeFieldType_int_3() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        try {
            test.withField(DateTimeFieldType.dayOfMonth(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithField_DateTimeFieldType_int_4() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withField(DateTimeFieldType.hourOfDay(), 10);
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testWithFieldAdded_DurationFieldType_int_1() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withFieldAdded(DurationFieldType.hours(), 6);
        
        assertEquals(new LocalTime(10,20,30,40),test);
        assertEquals(new LocalTime(16,20,30,40),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_2() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        try {
            test.withFieldAdded(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_DurationFieldType_int_3() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        try {
            test.withFieldAdded(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_DurationFieldType_int_4() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withFieldAdded(DurationFieldType.hours(), 0);
        assertSame(test,result);
    }

    public void testWithFieldAdded_DurationFieldType_int_5() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        try {
            test.withFieldAdded(DurationFieldType.days(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_DurationFieldType_int_6() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withFieldAdded(DurationFieldType.hours(), 16);
        
        assertEquals(new LocalTime(10,20,30,40),test);
        assertEquals(new LocalTime(2,20,30,40),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_7() {
        LocalTime test = new LocalTime(23, 59, 59, 999);
        LocalTime result = test.withFieldAdded(DurationFieldType.millis(), 1);
        assertEquals(new LocalTime(0,0,0,0),result);
        
        test = new LocalTime(23, 59, 59, 999);
        result = test.withFieldAdded(DurationFieldType.seconds(), 1);
        assertEquals(new LocalTime(0,0,0,999),result);
        
        test = new LocalTime(23, 59, 59, 999);
        result = test.withFieldAdded(DurationFieldType.minutes(), 1);
        assertEquals(new LocalTime(0,0,59,999),result);
        
        test = new LocalTime(23, 59, 59, 999);
        result = test.withFieldAdded(DurationFieldType.hours(), 1);
        assertEquals(new LocalTime(0,59,59,999),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_8() {
        LocalTime test = new LocalTime(0, 0, 0, 0);
        LocalTime result = test.withFieldAdded(DurationFieldType.millis(), -1);
        assertEquals(new LocalTime(23,59,59,999),result);
        
        test = new LocalTime(0, 0, 0, 0);
        result = test.withFieldAdded(DurationFieldType.seconds(), -1);
        assertEquals(new LocalTime(23,59,59,0),result);
        
        test = new LocalTime(0, 0, 0, 0);
        result = test.withFieldAdded(DurationFieldType.minutes(), -1);
        assertEquals(new LocalTime(23,59,0,0),result);
        
        test = new LocalTime(0, 0, 0, 0);
        result = test.withFieldAdded(DurationFieldType.hours(), -1);
        assertEquals(new LocalTime(23,0,0,0),result);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testWithers() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        check(test.withHourOfDay(6), 6, 20, 30, 40);
        check(test.withMinuteOfHour(6), 10, 6, 30, 40);
        check(test.withSecondOfMinute(6), 10, 20, 6, 40);
        check(test.withMillisOfSecond(6), 10, 20, 30, 6);
        check(test.withMillisOfDay(61234), 0, 1, 1, 234);
        try {
            test.withHourOfDay(-1);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.withHourOfDay(24);
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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    private void check(LocalTime test, int hour, int min, int sec, int milli) {
        assertEquals(hour,test.getHourOfDay());
        assertEquals(min,test.getMinuteOfHour());
        assertEquals(sec,test.getSecondOfMinute());
        assertEquals(milli,test.getMillisOfSecond());
    }

    public void testGet_DateTimeFieldType_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(10,test.get(DateTimeFieldType.hourOfDay()));
    }

    public void testGet_DateTimeFieldType_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals(20,test.get(DateTimeFieldType.minuteOfHour()));
    }

    public void testGet_DateTimeFieldType_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(30,test.get(DateTimeFieldType.secondOfMinute()));
    }

    public void testGet_DateTimeFieldType_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(40,test.get(DateTimeFieldType.millisOfSecond()));
    }

    public void testGet_DateTimeFieldType_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_NOW / 60000,test.get(DateTimeFieldType.minuteOfDay()));
    }

    public void testGet_DateTimeFieldType_6_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_NOW / 1000,test.get(DateTimeFieldType.secondOfDay()));
    }

    public void testGet_DateTimeFieldType_7_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.get(DateTimeFieldType.millisOfDay()));
    }

    public void testGet_DateTimeFieldType_8_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10,test.get(DateTimeFieldType.hourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_9_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.AM,test.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testGet_DateTimeFieldType_10_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        assertEquals(0,test.get(DateTimeFieldType.hourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_11_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        assertEquals(12,test.get(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_12_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        // removed other assertion
        assertEquals(12,test.get(DateTimeFieldType.clockhourOfDay()));
    }

    public void testGet_DateTimeFieldType_13_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.PM,test.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testGet_DateTimeFieldType_14_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(14, 30);
        assertEquals(2,test.get(DateTimeFieldType.hourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_15_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(14, 30);
        // removed other assertion
        assertEquals(2,test.get(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_16_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(14, 30);
        // removed other assertion
        // removed other assertion
        assertEquals(14,test.get(DateTimeFieldType.clockhourOfDay()));
    }

    public void testGet_DateTimeFieldType_17_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(14, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.PM,test.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testGet_DateTimeFieldType_18_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(14, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(0, 30);
        assertEquals(0,test.get(DateTimeFieldType.hourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_19_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(14, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(0, 30);
        // removed other assertion
        assertEquals(12,test.get(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testGet_DateTimeFieldType_20_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(14, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(0, 30);
        // removed other assertion
        // removed other assertion
        assertEquals(24,test.get(DateTimeFieldType.clockhourOfDay()));
    }

    public void testGet_DateTimeFieldType_21_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(12, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(14, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new LocalTime(0, 30);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.AM,test.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testSize_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(4,test.size());
    }

    public void testGetFieldType_int_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertSame(DateTimeFieldType.hourOfDay(),test.getFieldType(0));
    }

    public void testGetFieldType_int_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertSame(DateTimeFieldType.minuteOfHour(),test.getFieldType(1));
    }

    public void testGetFieldType_int_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.secondOfMinute(),test.getFieldType(2));
    }

    public void testGetFieldType_int_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.millisOfSecond(),test.getFieldType(3));
    }

    public void testGetFieldTypes_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        DateTimeFieldType[] fields = test.getFieldTypes();
        assertSame(DateTimeFieldType.hourOfDay(),fields[0]);
    }

    public void testGetFieldTypes_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        assertSame(DateTimeFieldType.minuteOfHour(),fields[1]);
    }

    public void testGetFieldTypes_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.secondOfMinute(),fields[2]);
    }

    public void testGetFieldTypes_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.millisOfSecond(),fields[3]);
    }

    public void testGetFieldTypes_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFieldTypes(),test.getFieldTypes());
    }

    public void testGetField_int_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        assertSame(COPTIC_UTC.hourOfDay(),test.getField(0));
    }

    public void testGetField_int_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        // removed other assertion
        assertSame(COPTIC_UTC.minuteOfHour(),test.getField(1));
    }

    public void testGetField_int_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.secondOfMinute(),test.getField(2));
    }

    public void testGetField_int_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.millisOfSecond(),test.getField(3));
    }

    public void testGetFields_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        DateTimeField[] fields = test.getFields();
        assertSame(COPTIC_UTC.hourOfDay(),fields[0]);
    }

    public void testGetFields_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        assertSame(COPTIC_UTC.minuteOfHour(),fields[1]);
    }

    public void testGetFields_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.secondOfMinute(),fields[2]);
    }

    public void testGetFields_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.millisOfSecond(),fields[3]);
    }

    public void testGetFields_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFields(),test.getFields());
    }

    public void testGetValue_int_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        assertEquals(10,test.getValue(0));
    }

    public void testGetValue_int_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        assertEquals(20,test.getValue(1));
    }

    public void testGetValue_int_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(30,test.getValue(2));
    }

    public void testGetValue_int_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(40,test.getValue(3));
    }

    public void testGetValues_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        int[] values = test.getValues();
        assertEquals(10,values[0]);
    }

    public void testGetValues_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        int[] values = test.getValues();
        // removed other assertion
        assertEquals(20,values[1]);
    }

    public void testGetValues_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        assertEquals(30,values[2]);
    }

    public void testGetValues_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(40,values[3]);
    }

    public void testGetValues_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_UTC);
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getValues(),test.getValues());
    }

    public void testIsSupported_DateTimeFieldType_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(true,test.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.minuteOfHour()));
    }

    public void testIsSupported_DateTimeFieldType_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.secondOfMinute()));
    }

    public void testIsSupported_DateTimeFieldType_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.millisOfSecond()));
    }

    public void testIsSupported_DateTimeFieldType_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.minuteOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_6_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.secondOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_7_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.millisOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_8_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(true,test.isSupported(DateTimeFieldType.hourOfHalfday()));
    }

    public void testIsSupported_DateTimeFieldType_9_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.halfdayOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_10_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.clockhourOfHalfday()));
    }

    public void testIsSupported_DateTimeFieldType_11_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.clockhourOfDay()));
    }

    public void testIsSupported_DateTimeFieldType_12_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test.isSupported(DateTimeFieldType.dayOfMonth()));
    }

    public void testIsSupported_DateTimeFieldType_13_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
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

    public void testIsSupported_DateTimeFieldType_14_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        DateTimeFieldType d = new DateTimeFieldType("hours") {
            private static final long serialVersionUID = 1L;
            @Override
            public DurationFieldType getDurationType() {
                return DurationFieldType.hours();
            }
            @Override
            public DurationFieldType getRangeDurationType() {
                return null;
            }
            @Override
            public DateTimeField getField(Chronology chronology) {
                return chronology.hourOfDay();
            }
        };
        assertEquals(false,test.isSupported(d));
    }

    public void testIsSupported_DateTimeFieldType_15_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        DateTimeFieldType d = new DateTimeFieldType("hours") {
            private static final long serialVersionUID = 1L;
            @Override
            public DurationFieldType getDurationType() {
                return DurationFieldType.hours();
            }
            @Override
            public DurationFieldType getRangeDurationType() {
                return null;
            }
            @Override
            public DateTimeField getField(Chronology chronology) {
                return chronology.hourOfDay();
            }
        };
        // removed other assertion
        
        d = new DateTimeFieldType("hourOfYear") {
            private static final long serialVersionUID = 1L;
            @Override
            public DurationFieldType getDurationType() {
                return DurationFieldType.hours();
            }
            @Override
            public DurationFieldType getRangeDurationType() {
                return DurationFieldType.years();
            }
            @Override
            public DateTimeField getField(Chronology chronology) {
                return chronology.hourOfDay();
            }
        };
        assertEquals(false,test.isSupported(d));
    }

    public void testIsSupported_DurationFieldType_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(true,test.isSupported(DurationFieldType.hours()));
    }

    public void testIsSupported_DurationFieldType_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals(true,test.isSupported(DurationFieldType.minutes()));
    }

    public void testIsSupported_DurationFieldType_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DurationFieldType.seconds()));
    }

    public void testIsSupported_DurationFieldType_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DurationFieldType.millis()));
    }

    public void testIsSupported_DurationFieldType_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DurationFieldType.halfdays()));
    }

    public void testIsSupported_DurationFieldType_6_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test.isSupported(DurationFieldType.days()));
    }

    public void testIsSupported_DurationFieldType_7_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false,test.isSupported((DurationFieldType)null));
    }

    public void testEqualsHashCode_1_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        assertEquals(true,test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        assertEquals(true,test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        assertEquals(false,test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        // removed other assertion
        assertEquals(false,test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.hashCode()== test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test2.hashCode()== test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test1.equals("Hello"));
    }

    public void testEqualsHashCode_15_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,test1.equals(new TimeOfDay(10,20,30,40,COPTIC_UTC)));
    }

    public void testEqualsHashCode_16_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== new TimeOfDay(10,20,30,40,COPTIC_UTC).hashCode());
    }

    public void testEqualsHashCode_17_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.equals(new MockInstant()));
    }

    public void testEqualsHashCode_18_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        LocalTime test2 = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(15, 20, 30, 40);
        // removed other assertion
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

    public void testCompareTo_1_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        assertEquals(0,test1.compareTo(test1a));
    }

    public void testCompareTo_2_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals(0,test1a.compareTo(test1));
    }

    public void testCompareTo_3_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(0,test1.compareTo(test1));
    }

    public void testCompareTo_4_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test1a.compareTo(test1a));
    }

    public void testCompareTo_5_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        assertEquals(-1,test1.compareTo(test2));
    }

    public void testCompareTo_6_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        assertEquals(+1,test2.compareTo(test1));
    }

    public void testCompareTo_7_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        assertEquals(-1,test1.compareTo(test3));
    }

    public void testCompareTo_8_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(+1,test3.compareTo(test1));
    }

    public void testCompareTo_9_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(0,test3.compareTo(test2));
    }

    public void testCompareTo_10_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.hourOfDay(),
            DateTimeFieldType.minuteOfHour(),
            DateTimeFieldType.secondOfMinute(),
            DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 30, 40};
        Partial p = new Partial(types, values);
        assertEquals(0,test1.compareTo(p));
    }

    public void testCompareTo_11_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.hourOfDay(),
            DateTimeFieldType.minuteOfHour(),
            DateTimeFieldType.secondOfMinute(),
            DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 30, 40};
        Partial p = new Partial(types, values);
        // removed other assertion
        assertEquals(0,test1.compareTo(new TimeOfDay(10,20,30,40)));
    }

    public void testIsEqual_LocalTime_1_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        assertEquals(true,test1.isEqual(test1a));
    }

    public void testIsEqual_LocalTime_2_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals(true,test1a.isEqual(test1));
    }

    public void testIsEqual_LocalTime_3_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.isEqual(test1));
    }

    public void testIsEqual_LocalTime_4_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1a.isEqual(test1a));
    }

    public void testIsEqual_LocalTime_5_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        assertEquals(false,test1.isEqual(test2));
    }

    public void testIsEqual_LocalTime_6_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        assertEquals(false,test2.isEqual(test1));
    }

    public void testIsEqual_LocalTime_7_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        assertEquals(false,test1.isEqual(test3));
    }

    public void testIsEqual_LocalTime_8_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(false,test3.isEqual(test1));
    }

    public void testIsEqual_LocalTime_9_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(true,test3.isEqual(test2));
    }

    public void testIsBefore_LocalTime_1_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        assertEquals(false,test1.isBefore(test1a));
    }

    public void testIsBefore_LocalTime_2_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals(false,test1a.isBefore(test1));
    }

    public void testIsBefore_LocalTime_3_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.isBefore(test1));
    }

    public void testIsBefore_LocalTime_4_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1a.isBefore(test1a));
    }

    public void testIsBefore_LocalTime_5_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        assertEquals(true,test1.isBefore(test2));
    }

    public void testIsBefore_LocalTime_6_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        assertEquals(false,test2.isBefore(test1));
    }

    public void testIsBefore_LocalTime_7_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        assertEquals(true,test1.isBefore(test3));
    }

    public void testIsBefore_LocalTime_8_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(false,test3.isBefore(test1));
    }

    public void testIsBefore_LocalTime_9_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.isBefore(test2));
    }

    public void testIsAfter_LocalTime_1_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        assertEquals(false,test1.isAfter(test1a));
    }

    public void testIsAfter_LocalTime_2_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals(false,test1a.isAfter(test1));
    }

    public void testIsAfter_LocalTime_3_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.isAfter(test1));
    }

    public void testIsAfter_LocalTime_4_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1a.isAfter(test1a));
    }

    public void testIsAfter_LocalTime_5_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        assertEquals(false,test1.isAfter(test2));
    }

    public void testIsAfter_LocalTime_6_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        assertEquals(true,test2.isAfter(test1));
    }

    public void testIsAfter_LocalTime_7_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        assertEquals(false,test1.isAfter(test3));
    }

    public void testIsAfter_LocalTime_8_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(true,test3.isAfter(test1));
    }

    public void testIsAfter_LocalTime_9_oe() {
        LocalTime test1 = new LocalTime(10, 20, 30, 40);
        LocalTime test1a = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        LocalTime test2 = new LocalTime(10, 20, 35, 40);
        // removed other assertion
        // removed other assertion
        
        LocalTime test3 = new LocalTime(10, 20, 35, 40, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.isAfter(test2));
    }

    public void testWithField_DateTimeFieldType_int_1_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withField(DateTimeFieldType.hourOfDay(), 15);
        
        assertEquals(new LocalTime(10,20,30,40),test);
    }

    public void testWithField_DateTimeFieldType_int_1_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withField(DateTimeFieldType.hourOfDay(), 15);
        
        // removed other assertion
        assertEquals(new LocalTime(15,20,30,40),result);
    }

    public void testWithField_DateTimeFieldType_int_4_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withField(DateTimeFieldType.hourOfDay(), 10);
        assertSame(test,result);
    }

    public void testWithFieldAdded_DurationFieldType_int_1_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withFieldAdded(DurationFieldType.hours(), 6);
        
        assertEquals(new LocalTime(10,20,30,40),test);
    }

    public void testWithFieldAdded_DurationFieldType_int_1_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withFieldAdded(DurationFieldType.hours(), 6);
        
        // removed other assertion
        assertEquals(new LocalTime(16,20,30,40),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_4_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withFieldAdded(DurationFieldType.hours(), 0);
        assertSame(test,result);
    }

    public void testWithFieldAdded_DurationFieldType_int_6_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withFieldAdded(DurationFieldType.hours(), 16);
        
        assertEquals(new LocalTime(10,20,30,40),test);
    }

    public void testWithFieldAdded_DurationFieldType_int_6_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime result = test.withFieldAdded(DurationFieldType.hours(), 16);
        
        // removed other assertion
        assertEquals(new LocalTime(2,20,30,40),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_7_1_oe() {
        LocalTime test = new LocalTime(23, 59, 59, 999);
        LocalTime result = test.withFieldAdded(DurationFieldType.millis(), 1);
        assertEquals(new LocalTime(0,0,0,0),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_7_2_oe() {
        LocalTime test = new LocalTime(23, 59, 59, 999);
        LocalTime result = test.withFieldAdded(DurationFieldType.millis(), 1);
        // removed other assertion
        
        test = new LocalTime(23, 59, 59, 999);
        result = test.withFieldAdded(DurationFieldType.seconds(), 1);
        assertEquals(new LocalTime(0,0,0,999),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_7_3_oe() {
        LocalTime test = new LocalTime(23, 59, 59, 999);
        LocalTime result = test.withFieldAdded(DurationFieldType.millis(), 1);
        // removed other assertion
        
        test = new LocalTime(23, 59, 59, 999);
        result = test.withFieldAdded(DurationFieldType.seconds(), 1);
        // removed other assertion
        
        test = new LocalTime(23, 59, 59, 999);
        result = test.withFieldAdded(DurationFieldType.minutes(), 1);
        assertEquals(new LocalTime(0,0,59,999),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_7_4_oe() {
        LocalTime test = new LocalTime(23, 59, 59, 999);
        LocalTime result = test.withFieldAdded(DurationFieldType.millis(), 1);
        // removed other assertion
        
        test = new LocalTime(23, 59, 59, 999);
        result = test.withFieldAdded(DurationFieldType.seconds(), 1);
        // removed other assertion
        
        test = new LocalTime(23, 59, 59, 999);
        result = test.withFieldAdded(DurationFieldType.minutes(), 1);
        // removed other assertion
        
        test = new LocalTime(23, 59, 59, 999);
        result = test.withFieldAdded(DurationFieldType.hours(), 1);
        assertEquals(new LocalTime(0,59,59,999),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_8_1_oe() {
        LocalTime test = new LocalTime(0, 0, 0, 0);
        LocalTime result = test.withFieldAdded(DurationFieldType.millis(), -1);
        assertEquals(new LocalTime(23,59,59,999),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_8_2_oe() {
        LocalTime test = new LocalTime(0, 0, 0, 0);
        LocalTime result = test.withFieldAdded(DurationFieldType.millis(), -1);
        // removed other assertion
        
        test = new LocalTime(0, 0, 0, 0);
        result = test.withFieldAdded(DurationFieldType.seconds(), -1);
        assertEquals(new LocalTime(23,59,59,0),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_8_3_oe() {
        LocalTime test = new LocalTime(0, 0, 0, 0);
        LocalTime result = test.withFieldAdded(DurationFieldType.millis(), -1);
        // removed other assertion
        
        test = new LocalTime(0, 0, 0, 0);
        result = test.withFieldAdded(DurationFieldType.seconds(), -1);
        // removed other assertion
        
        test = new LocalTime(0, 0, 0, 0);
        result = test.withFieldAdded(DurationFieldType.minutes(), -1);
        assertEquals(new LocalTime(23,59,0,0),result);
    }

    public void testWithFieldAdded_DurationFieldType_int_8_4_oe() {
        LocalTime test = new LocalTime(0, 0, 0, 0);
        LocalTime result = test.withFieldAdded(DurationFieldType.millis(), -1);
        // removed other assertion
        
        test = new LocalTime(0, 0, 0, 0);
        result = test.withFieldAdded(DurationFieldType.seconds(), -1);
        // removed other assertion
        
        test = new LocalTime(0, 0, 0, 0);
        result = test.withFieldAdded(DurationFieldType.minutes(), -1);
        // removed other assertion
        
        test = new LocalTime(0, 0, 0, 0);
        result = test.withFieldAdded(DurationFieldType.hours(), -1);
        assertEquals(new LocalTime(23,0,0,0),result);
    }

    public void testPlus_RP_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, BUDDHIST_LONDON);
        LocalTime result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        LocalTime expected = new LocalTime(15, 26, 37, 48, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

    public void testPlus_RP_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, BUDDHIST_LONDON);
        LocalTime result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        LocalTime expected = new LocalTime(15, 26, 37, 48, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plus((ReadablePeriod) null);
        assertSame(test,result);
    }

    public void testPlusHours_int_1_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.plusHours(1);
        LocalTime expected = new LocalTime(2, 2, 3, 4, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

    public void testPlusHours_int_2_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.plusHours(1);
        LocalTime expected = new LocalTime(2, 2, 3, 4, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusHours(0);
        assertSame(test,result);
    }

    public void testPlusMinutes_int_1_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.plusMinutes(1);
        LocalTime expected = new LocalTime(1, 3, 3, 4, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

    public void testPlusMinutes_int_2_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.plusMinutes(1);
        LocalTime expected = new LocalTime(1, 3, 3, 4, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusMinutes(0);
        assertSame(test,result);
    }

    public void testPlusSeconds_int_1_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.plusSeconds(1);
        LocalTime expected = new LocalTime(1, 2, 4, 4, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

    public void testPlusSeconds_int_2_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.plusSeconds(1);
        LocalTime expected = new LocalTime(1, 2, 4, 4, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusSeconds(0);
        assertSame(test,result);
    }

    public void testPlusMillis_int_1_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.plusMillis(1);
        LocalTime expected = new LocalTime(1, 2, 3, 5, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

    public void testPlusMillis_int_2_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.plusMillis(1);
        LocalTime expected = new LocalTime(1, 2, 3, 5, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.plusMillis(0);
        assertSame(test,result);
    }

    public void testMinus_RP_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, BUDDHIST_LONDON);
        LocalTime result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        LocalTime expected = new LocalTime(9, 19, 29, 39, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

    public void testMinus_RP_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40, BUDDHIST_LONDON);
        LocalTime result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        LocalTime expected = new LocalTime(9, 19, 29, 39, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minus((ReadablePeriod) null);
        assertSame(test,result);
    }

    public void testMinusHours_int_1_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.minusHours(1);
        LocalTime expected = new LocalTime(0, 2, 3, 4, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

    public void testMinusHours_int_2_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.minusHours(1);
        LocalTime expected = new LocalTime(0, 2, 3, 4, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusHours(0);
        assertSame(test,result);
    }

    public void testMinusMinutes_int_1_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.minusMinutes(1);
        LocalTime expected = new LocalTime(1, 1, 3, 4, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

    public void testMinusMinutes_int_2_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.minusMinutes(1);
        LocalTime expected = new LocalTime(1, 1, 3, 4, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusMinutes(0);
        assertSame(test,result);
    }

    public void testMinusSeconds_int_1_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.minusSeconds(1);
        LocalTime expected = new LocalTime(1, 2, 2, 4, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

    public void testMinusSeconds_int_2_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.minusSeconds(1);
        LocalTime expected = new LocalTime(1, 2, 2, 4, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusSeconds(0);
        assertSame(test,result);
    }

    public void testMinusMillis_int_1_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.minusMillis(1);
        LocalTime expected = new LocalTime(1, 2, 3, 3, BUDDHIST_LONDON);
        assertEquals(expected,result);
    }

    public void testMinusMillis_int_2_oe() {
        LocalTime test = new LocalTime(1, 2, 3, 4, BUDDHIST_LONDON);
        LocalTime result = test.minusMillis(1);
        LocalTime expected = new LocalTime(1, 2, 3, 3, BUDDHIST_LONDON);
        // removed other assertion
        
        result = test.minusMillis(0);
        assertSame(test,result);
    }

    public void testGetters_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(10,test.getHourOfDay());
    }

    public void testGetters_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals(20,test.getMinuteOfHour());
    }

    public void testGetters_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(30,test.getSecondOfMinute());
    }

    public void testGetters_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(40,test.getMillisOfSecond());
    }

    public void testGetters_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_NOW,test.getMillisOfDay());
    }

    public void testToDateTimeTodayDefaultZone_1_oe() {
        LocalTime base = new LocalTime(10, 20, 30, 40, COPTIC_PARIS); // PARIS irrelevant
        DateTime dt = new DateTime(2004, 6, 9, 6, 7, 8, 9);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        DateTime test = base.toDateTimeToday();
        check(base, 10, 20, 30, 40);
        DateTime expected = new DateTime(dt.getMillis(), COPTIC_LONDON);
        expected = expected.hourOfDay().setCopy(10);
        expected = expected.minuteOfHour().setCopy(20);
        expected = expected.secondOfMinute().setCopy(30);
        expected = expected.millisOfSecond().setCopy(40);
        assertEquals(expected,test);
    }

    public void testToDateTimeToday_Zone_1_oe() {
        LocalTime base = new LocalTime(10, 20, 30, 40, COPTIC_PARIS); // PARIS irrelevant
        DateTime dt = new DateTime(2004, 6, 9, 6, 7, 8, 9);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        DateTime test = base.toDateTimeToday(TOKYO);
        check(base, 10, 20, 30, 40);
        DateTime expected = new DateTime(dt.getMillis(), COPTIC_TOKYO);
        expected = expected.hourOfDay().setCopy(10);
        expected = expected.minuteOfHour().setCopy(20);
        expected = expected.secondOfMinute().setCopy(30);
        expected = expected.millisOfSecond().setCopy(40);
        assertEquals(expected,test);
    }

    public void testToDateTimeToday_nullZone_1_oe() {
        LocalTime base = new LocalTime(10, 20, 30, 40, COPTIC_PARIS); // PARIS irrelevant
        DateTime dt = new DateTime(2004, 6, 9, 6, 7, 8, 9);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        DateTime test = base.toDateTimeToday((DateTimeZone) null);
        check(base, 10, 20, 30, 40);
        DateTime expected = new DateTime(dt.getMillis(), COPTIC_LONDON);
        expected = expected.hourOfDay().setCopy(10);
        expected = expected.minuteOfHour().setCopy(20);
        expected = expected.secondOfMinute().setCopy(30);
        expected = expected.millisOfSecond().setCopy(40);
        assertEquals(expected,test);
    }

    public void testToDateTime_RI_1_oe() {
        LocalTime base = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        DateTime dt = new DateTime(0L); // LONDON zone
        assertEquals("1970-01-01T01:00:00.000+01:00",dt.toString());
    }

    public void testToDateTime_RI_2_oe() {
        LocalTime base = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        DateTime dt = new DateTime(0L); // LONDON zone
        // removed other assertion
        
        DateTime test = base.toDateTime(dt);
        check(base, 10, 20, 30, 40);
        assertEquals("1970-01-01T01:00:00.000+01:00",dt.toString());
    }

    public void testToDateTime_RI_3_oe() {
        LocalTime base = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        DateTime dt = new DateTime(0L); // LONDON zone
        // removed other assertion
        
        DateTime test = base.toDateTime(dt);
        check(base, 10, 20, 30, 40);
        // removed other assertion
        assertEquals("1970-01-01T10:20:30.040+01:00",test.toString());
    }

    public void testToDateTime_nullRI_1_oe() {
        LocalTime base = new LocalTime(1, 2, 3, 4);
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2);
        
        DateTime test = base.toDateTime((ReadableInstant) null);
        check(base, 1, 2, 3, 4);
        assertEquals("1970-01-02T01:02:03.004+01:00",test.toString());
    }

    public void testProperty_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(test.hourOfDay(),test.property(DateTimeFieldType.hourOfDay()));
    }

    public void testProperty_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals(test.minuteOfHour(),test.property(DateTimeFieldType.minuteOfHour()));
    }

    public void testProperty_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(test.secondOfMinute(),test.property(DateTimeFieldType.secondOfMinute()));
    }

    public void testProperty_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.millisOfSecond(),test.property(DateTimeFieldType.millisOfSecond()));
    }

    public void testProperty_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.millisOfDay(),test.property(DateTimeFieldType.millisOfDay()));
    }

    public void testProperty_6_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(test,test.property(DateTimeFieldType.minuteOfDay()).getLocalTime());
    }

    public void testProperty_7_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(test,test.property(DateTimeFieldType.secondOfDay()).getLocalTime());
    }

    public void testProperty_8_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(test,test.property(DateTimeFieldType.millisOfDay()).getLocalTime());
    }

    public void testProperty_9_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test,test.property(DateTimeFieldType.hourOfHalfday()).getLocalTime());
    }

    public void testProperty_10_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test,test.property(DateTimeFieldType.halfdayOfDay()).getLocalTime());
    }

    public void testProperty_11_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test,test.property(DateTimeFieldType.clockhourOfHalfday()).getLocalTime());
    }

    public void testProperty_12_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test,test.property(DateTimeFieldType.clockhourOfDay()).getLocalTime());
    }

    public void testSerialization_1_oe() throws Exception {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalTime result = (LocalTime) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

    public void testSerialization_2_oe() throws Exception {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalTime result = (LocalTime) ois.readObject();
        ois.close();
        
        // removed other assertion
        assertTrue(Arrays.equals(test.getValues(),result.getValues()));
    }

    public void testSerialization_3_oe() throws Exception {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalTime result = (LocalTime) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(test.getFields(),result.getFields()));
    }

    public void testSerialization_4_oe() throws Exception {
        LocalTime test = new LocalTime(10, 20, 30, 40, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LocalTime result = (LocalTime) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology(),result.getChronology());
    }

    public void testToString_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("10:20:30.040",test.toString());
    }

    public void testToString_String_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("\ufffd\ufffd\ufffd\ufffd 10",test.toString("yyyy HH"));
    }

    public void testToString_String_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals("10:20:30.040",test.toString((String)null));
    }

    public void testToString_String_Locale_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("10 20",test.toString("H m",Locale.ENGLISH));
    }

    public void testToString_String_Locale_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals("10:20:30.040",test.toString(null,Locale.ENGLISH));
    }

    public void testToString_String_Locale_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals("10 20",test.toString("H m",null));
    }

    public void testToString_String_Locale_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("10:20:30.040",test.toString(null,null));
    }

    public void testToString_DTFormatter_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("\ufffd\ufffd\ufffd\ufffd 10",test.toString(DateTimeFormat.forPattern("yyyy HH")));
    }

    public void testToString_DTFormatter_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        // removed other assertion
        assertEquals("10:20:30.040",test.toString((DateTimeFormatter)null));
    }

}
