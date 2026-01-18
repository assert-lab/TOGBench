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
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for Partial.
 *
 * @author Stephen Colebourne
 */
public class TestPartial_Basics_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
    private static final Chronology COPTIC_TOKYO = CopticChronology.getInstance(TOKYO);
    private static final Chronology COPTIC_UTC = CopticChronology.getInstanceUTC();
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final Chronology BUDDHIST_LONDON = BuddhistChronology.getInstance(LONDON);
    private static final Chronology BUDDHIST_TOKYO = BuddhistChronology.getInstance(TOKYO);
    private static final Chronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();
    
    private long TEST_TIME_NOW =
            10L * DateTimeConstants.MILLIS_PER_HOUR
            + 20L * DateTimeConstants.MILLIS_PER_MINUTE
            + 30L * DateTimeConstants.MILLIS_PER_SECOND
            + 40L;
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
        return new TestSuite(TestPartial_Basics_OE25Dev.class);
    }

    public TestPartial_Basics_OE25Dev(String name) {
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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testWith1() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.hourOfDay(), 15);
        check(test, 10, 20);
        check(result, 15, 20);
    }

    public void testWith2() {
        Partial test = createHourMinPartial();
        try {
            test.with(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20);
    }

    public void testWith3() {
        Partial test = createHourMinPartial();
        try {
            test.with(DateTimeFieldType.clockhourOfDay(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20);
    }

    //-----------------------------------------------------------------------
    public void testWithout1() {
        Partial test = createHourMinPartial();
        Partial result = test.without(DateTimeFieldType.year());
        check(test, 10, 20);
        check(result, 10, 20);
    }

    public void testWithout2() {
        Partial test = createHourMinPartial();
        Partial result = test.without((DateTimeFieldType) null);
        check(test, 10, 20);
        check(result, 10, 20);
    }

    //-----------------------------------------------------------------------
    public void testWithField1() {
        Partial test = createHourMinPartial();
        Partial result = test.withField(DateTimeFieldType.hourOfDay(), 15);
        check(test, 10, 20);
        check(result, 15, 20);
    }

    public void testWithField2() {
        Partial test = createHourMinPartial();
        try {
            test.withField(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20);
    }

    public void testWithField3() {
        Partial test = createHourMinPartial();
        try {
            test.withField(DateTimeFieldType.dayOfMonth(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20);
    }

    //-----------------------------------------------------------------------

    public void testWithFieldAdded2() {
        Partial test = createHourMinPartial();
        try {
            test.withFieldAdded(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20);
    }

    public void testWithFieldAdded3() {
        Partial test = createHourMinPartial();
        try {
            test.withFieldAdded(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20);
    }

    public void testWithFieldAdded5() {
        Partial test = createHourMinPartial();
        try {
            test.withFieldAdded(DurationFieldType.days(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20);
    }

    public void testWithFieldAdded6() {
        Partial test = createHourMinPartial();
        try {
            test.withFieldAdded(DurationFieldType.hours(), 16);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        check(test, 10, 20);
    }

    public void testWithFieldAdded7() {
        Partial test = createHourMinPartial(23, 59, ISO_UTC);
        try {
            test.withFieldAdded(DurationFieldType.minutes(), 1);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        check(test, 23, 59);
        
        test = createHourMinPartial(23, 59, ISO_UTC);
        try {
            test.withFieldAdded(DurationFieldType.hours(), 1);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        check(test, 23, 59);
    }

    public void testWithFieldAdded8() {
        Partial test = createHourMinPartial(0, 0, ISO_UTC);
        try {
            test.withFieldAdded(DurationFieldType.minutes(), -1);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        check(test, 0, 0);
        
        test = createHourMinPartial(0, 0, ISO_UTC);
        try {
            test.withFieldAdded(DurationFieldType.hours(), -1);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        check(test, 0, 0);
    }

    //-----------------------------------------------------------------------

    public void testWithFieldAddWrapped2() {
        Partial test = createHourMinPartial();
        try {
            test.withFieldAddWrapped(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20);
    }

    public void testWithFieldAddWrapped3() {
        Partial test = createHourMinPartial();
        try {
            test.withFieldAddWrapped(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20);
    }

    public void testWithFieldAddWrapped5() {
        Partial test = createHourMinPartial();
        try {
            test.withFieldAddWrapped(DurationFieldType.days(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20);
    }

    public void testWithFieldAddWrapped7() {
        Partial test = createHourMinPartial(23, 59, ISO_UTC);
        Partial result = test.withFieldAddWrapped(DurationFieldType.minutes(), 1);
        check(test, 23, 59);
        check(result, 0, 0);
        
        test = createHourMinPartial(23, 59, ISO_UTC);
        result = test.withFieldAddWrapped(DurationFieldType.hours(), 1);
        check(test, 23, 59);
        check(result, 0, 59);
    }

    public void testWithFieldAddWrapped8() {
        Partial test = createHourMinPartial(0, 0, ISO_UTC);
        Partial result = test.withFieldAddWrapped(DurationFieldType.minutes(), -1);
        check(test, 0, 0);
        check(result, 23, 59);
        
        test = createHourMinPartial(0, 0, ISO_UTC);
        result = test.withFieldAddWrapped(DurationFieldType.hours(), -1);
        check(test, 0, 0);
        check(result, 23, 0);
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
    private Partial createHourMinPartial() {
        return createHourMinPartial(ISO_UTC);
    }

    private Partial createHourMinPartial(Chronology chrono) {
        return createHourMinPartial(10, 20, chrono);
    }

    private Partial createHourMinPartial2(Chronology chrono) {
        return createHourMinPartial(15, 20, chrono);
    }

    private Partial createHourMinPartial(int hour, int min, Chronology chrono) {
        return new Partial(
            new DateTimeFieldType[] {DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour()},
            new int[] {hour, min},
            chrono);
    }

    private Partial createTODPartial(Chronology chrono) {
        return new Partial(
            new DateTimeFieldType[] {
                    DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(),
                    DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()},
            new int[] {10, 20, 30, 40},
            chrono);
    }

    private void check(Partial test, int hour, int min) {
        assertEquals(test.toString(), hour, test.get(DateTimeFieldType.hourOfDay()));
        assertEquals(test.toString(), min, test.get(DateTimeFieldType.minuteOfHour()));
    }

    public void testGet_1_oe() {
        Partial test = createHourMinPartial();
        assertEquals(10, test.get(DateTimeFieldType.hourOfDay()));
    }

    public void testGet_2_oe() {
        Partial test = createHourMinPartial();
        // removed other assertion
        assertEquals(20, test.get(DateTimeFieldType.minuteOfHour()));
    }

    public void testSize_1_oe() {
        Partial test = createHourMinPartial();
        assertEquals(2, test.size());
    }

    public void testGetFieldType_1_oe() {
        Partial test = createHourMinPartial();
        assertSame(DateTimeFieldType.hourOfDay(), test.getFieldType(0));
    }

    public void testGetFieldType_2_oe() {
        Partial test = createHourMinPartial();
        // removed other assertion
        assertSame(DateTimeFieldType.minuteOfHour(), test.getFieldType(1));
    }

    public void testGetFieldTypes_1_oe() {
        Partial test = createHourMinPartial();
        DateTimeFieldType[] fields = test.getFieldTypes();
        assertEquals(2, fields.length);
    }

    public void testGetFieldTypes_2_oe() {
        Partial test = createHourMinPartial();
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        assertSame(DateTimeFieldType.hourOfDay(), fields[0]);
    }

    public void testGetFieldTypes_3_oe() {
        Partial test = createHourMinPartial();
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.minuteOfHour(), fields[1]);
    }

    public void testGetFieldTypes_4_oe() {
        Partial test = createHourMinPartial();
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFieldTypes(), test.getFieldTypes());
    }

    public void testGetField_1_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        assertSame(CopticChronology.getInstanceUTC().hourOfDay(), test.getField(0));
    }

    public void testGetField_2_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        assertSame(CopticChronology.getInstanceUTC().minuteOfHour(), test.getField(1));
    }

    public void testGetFields_1_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        assertEquals(2, fields.length);
    }

    public void testGetFields_2_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        assertSame(CopticChronology.getInstanceUTC().hourOfDay(), fields[0]);
    }

    public void testGetFields_3_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        assertSame(CopticChronology.getInstanceUTC().minuteOfHour(), fields[1]);
    }

    public void testGetFields_4_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFields(), test.getFields());
    }

    public void testGetValue_1_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        assertEquals(10, test.getValue(0));
    }

    public void testGetValue_2_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        assertEquals(20, test.getValue(1));
    }

    public void testGetValues_1_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        int[] values = test.getValues();
        assertEquals(2, values.length);
    }

    public void testGetValues_2_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        int[] values = test.getValues();
        // removed other assertion
        assertEquals(10, values[0]);
    }

    public void testGetValues_3_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        assertEquals(20, values[1]);
    }

    public void testGetValues_4_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getValues(), test.getValues());
    }

    public void testIsSupported_1_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        assertEquals(true, test.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testIsSupported_2_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.minuteOfHour()));
    }

    public void testIsSupported_3_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isSupported(DateTimeFieldType.secondOfMinute()));
    }

    public void testIsSupported_4_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isSupported(DateTimeFieldType.millisOfSecond()));
    }

    public void testIsSupported_5_oe() {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isSupported(DateTimeFieldType.dayOfMonth()));
    }

    public void testEqualsHashCode_1_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_PARIS);
        assertEquals(false, test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_PARIS);
        // removed other assertion
        assertEquals(false, test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test2.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test1.equals("Hello"));
    }

    public void testEqualsHashCode_15_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test1.equals(MockPartial.EMPTY_INSTANCE));
    }

    public void testEqualsHashCode_16_oe() {
        Partial test1 = createHourMinPartial(COPTIC_PARIS);
        Partial test2 = createHourMinPartial(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(new TimeOfDay(10, 20, 30, 40), createTODPartial(ISO_UTC));
    }

    public void testCompareTo_1_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        assertEquals(0, test1.compareTo(test1a));
    }

    public void testCompareTo_2_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_3_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        assertEquals(0, test1.compareTo(test1));
    }

    public void testCompareTo_4_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test1a.compareTo(test1a));
    }

    public void testCompareTo_5_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        assertEquals(-1, test1.compareTo(test2));
    }

    public void testCompareTo_6_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        assertEquals(+1, test2.compareTo(test1));
    }

    public void testCompareTo_7_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        assertEquals(-1, test1.compareTo(test3));
    }

    public void testCompareTo_8_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        // removed other assertion
        assertEquals(+1, test3.compareTo(test1));
    }

    public void testCompareTo_9_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test3.compareTo(test2));
    }

    public void testCompareTo_10_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(0, new TimeOfDay(10, 20, 30, 40).compareTo(createTODPartial(ISO_UTC)));
    }

    public void testIsEqual_TOD_1_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_TOD_2_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_TOD_3_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.isEqual(test1));
    }

    public void testIsEqual_TOD_4_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1a.isEqual(test1a));
    }

    public void testIsEqual_TOD_5_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        assertEquals(false, test1.isEqual(test2));
    }

    public void testIsEqual_TOD_6_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        assertEquals(false, test2.isEqual(test1));
    }

    public void testIsEqual_TOD_7_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        assertEquals(false, test1.isEqual(test3));
    }

    public void testIsEqual_TOD_8_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        // removed other assertion
        assertEquals(false, test3.isEqual(test1));
    }

    public void testIsEqual_TOD_9_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test3.isEqual(test2));
    }

    public void testIsBefore_TOD_1_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        assertEquals(false, test1.isBefore(test1a));
    }

    public void testIsBefore_TOD_2_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        assertEquals(false, test1a.isBefore(test1));
    }

    public void testIsBefore_TOD_3_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isBefore(test1));
    }

    public void testIsBefore_TOD_4_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1a.isBefore(test1a));
    }

    public void testIsBefore_TOD_5_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        assertEquals(true, test1.isBefore(test2));
    }

    public void testIsBefore_TOD_6_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        assertEquals(false, test2.isBefore(test1));
    }

    public void testIsBefore_TOD_7_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        assertEquals(true, test1.isBefore(test3));
    }

    public void testIsBefore_TOD_8_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        // removed other assertion
        assertEquals(false, test3.isBefore(test1));
    }

    public void testIsBefore_TOD_9_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.isBefore(test2));
    }

    public void testIsAfter_TOD_1_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        assertEquals(false, test1.isAfter(test1a));
    }

    public void testIsAfter_TOD_2_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        assertEquals(false, test1a.isAfter(test1));
    }

    public void testIsAfter_TOD_3_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isAfter(test1));
    }

    public void testIsAfter_TOD_4_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1a.isAfter(test1a));
    }

    public void testIsAfter_TOD_5_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        assertEquals(false, test1.isAfter(test2));
    }

    public void testIsAfter_TOD_6_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        assertEquals(true, test2.isAfter(test1));
    }

    public void testIsAfter_TOD_7_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        assertEquals(false, test1.isAfter(test3));
    }

    public void testIsAfter_TOD_8_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        // removed other assertion
        assertEquals(true, test3.isAfter(test1));
    }

    public void testIsAfter_TOD_9_oe() {
        Partial test1 = createHourMinPartial();
        Partial test1a = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Partial test2 = createHourMinPartial2(ISO_UTC);
        // removed other assertion
        // removed other assertion
        
        Partial test3 = createHourMinPartial2(COPTIC_UTC);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.isAfter(test2));
    }

    public void testWithChronologyRetainFields_Chrono_1_oe() {
        Partial base = createHourMinPartial(COPTIC_PARIS);
        Partial test = base.withChronologyRetainFields(BUDDHIST_TOKYO);
        check(base, 10, 20);
        assertEquals(COPTIC_UTC, base.getChronology());
    }

    public void testWithChronologyRetainFields_Chrono_2_oe() {
        Partial base = createHourMinPartial(COPTIC_PARIS);
        Partial test = base.withChronologyRetainFields(BUDDHIST_TOKYO);
        check(base, 10, 20);
        // removed other assertion
        check(test, 10, 20);
        assertEquals(BUDDHIST_UTC, test.getChronology());
    }

    public void testWithChronologyRetainFields_sameChrono_1_oe() {
        Partial base = createHourMinPartial(COPTIC_PARIS);
        Partial test = base.withChronologyRetainFields(COPTIC_TOKYO);
        assertSame(base, test);
    }

    public void testWithChronologyRetainFields_nullChrono_1_oe() {
        Partial base = createHourMinPartial(COPTIC_PARIS);
        Partial test = base.withChronologyRetainFields(null);
        check(base, 10, 20);
        assertEquals(COPTIC_UTC, base.getChronology());
    }

    public void testWithChronologyRetainFields_nullChrono_2_oe() {
        Partial base = createHourMinPartial(COPTIC_PARIS);
        Partial test = base.withChronologyRetainFields(null);
        check(base, 10, 20);
        // removed other assertion
        check(test, 10, 20);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testWith3a_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.secondOfMinute(), 15);
        check(test, 10, 20);
        assertEquals(3, result.size());
    }

    public void testWith3a_2_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.secondOfMinute(), 15);
        check(test, 10, 20);
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testWith3a_3_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.secondOfMinute(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.minuteOfHour()));
    }

    public void testWith3a_4_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.secondOfMinute(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.secondOfMinute()));
    }

    public void testWith3a_5_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.secondOfMinute(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.hourOfDay(), result.getFieldType(0));
    }

    public void testWith3a_6_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.secondOfMinute(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.minuteOfHour(), result.getFieldType(1));
    }

    public void testWith3a_7_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.secondOfMinute(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.secondOfMinute(), result.getFieldType(2));
    }

    public void testWith3a_8_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.secondOfMinute(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, result.get(DateTimeFieldType.hourOfDay()));
    }

    public void testWith3a_9_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.secondOfMinute(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(20, result.get(DateTimeFieldType.minuteOfHour()));
    }

    public void testWith3a_10_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.secondOfMinute(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(15, result.get(DateTimeFieldType.secondOfMinute()));
    }

    public void testWith3b_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.minuteOfDay(), 15);
        check(test, 10, 20);
        assertEquals(3, result.size());
    }

    public void testWith3b_2_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.minuteOfDay(), 15);
        check(test, 10, 20);
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testWith3b_3_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.minuteOfDay(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.minuteOfDay()));
    }

    public void testWith3b_4_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.minuteOfDay(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.minuteOfHour()));
    }

    public void testWith3b_5_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.minuteOfDay(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.hourOfDay(), result.getFieldType(0));
    }

    public void testWith3b_6_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.minuteOfDay(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.minuteOfDay(), result.getFieldType(1));
    }

    public void testWith3b_7_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.minuteOfDay(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.minuteOfHour(), result.getFieldType(2));
    }

    public void testWith3b_8_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.minuteOfDay(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, result.get(DateTimeFieldType.hourOfDay()));
    }

    public void testWith3b_9_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.minuteOfDay(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(20, result.get(DateTimeFieldType.minuteOfHour()));
    }

    public void testWith3b_10_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.minuteOfDay(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(15, result.get(DateTimeFieldType.minuteOfDay()));
    }

    public void testWith3c_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.dayOfMonth(), 15);
        check(test, 10, 20);
        assertEquals(3, result.size());
    }

    public void testWith3c_2_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.dayOfMonth(), 15);
        check(test, 10, 20);
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.dayOfMonth()));
    }

    public void testWith3c_3_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.dayOfMonth(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testWith3c_4_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.dayOfMonth(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.minuteOfHour()));
    }

    public void testWith3c_5_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.dayOfMonth(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.dayOfMonth(), result.getFieldType(0));
    }

    public void testWith3c_6_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.dayOfMonth(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.hourOfDay(), result.getFieldType(1));
    }

    public void testWith3c_7_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.dayOfMonth(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.minuteOfHour(), result.getFieldType(2));
    }

    public void testWith3c_8_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.dayOfMonth(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, result.get(DateTimeFieldType.hourOfDay()));
    }

    public void testWith3c_9_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.dayOfMonth(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(20, result.get(DateTimeFieldType.minuteOfHour()));
    }

    public void testWith3c_10_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.dayOfMonth(), 15);
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(15, result.get(DateTimeFieldType.dayOfMonth()));
    }

    public void testWith3d_1_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        Partial result = test.with(DateTimeFieldType.monthOfYear(), 6);
        assertEquals(2, result.size());
    }

    public void testWith3d_2_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        Partial result = test.with(DateTimeFieldType.monthOfYear(), 6);
        // removed other assertion
        assertEquals(2005, result.get(DateTimeFieldType.year()));
    }

    public void testWith3d_3_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        Partial result = test.with(DateTimeFieldType.monthOfYear(), 6);
        // removed other assertion
        // removed other assertion
        assertEquals(6, result.get(DateTimeFieldType.monthOfYear()));
    }

    public void testWith3e_1_oe() {
        Partial test = new Partial(DateTimeFieldType.era(), 1);
        Partial result = test.with(DateTimeFieldType.halfdayOfDay(), 0);
        assertEquals(2, result.size());
    }

    public void testWith3e_2_oe() {
        Partial test = new Partial(DateTimeFieldType.era(), 1);
        Partial result = test.with(DateTimeFieldType.halfdayOfDay(), 0);
        // removed other assertion
        assertEquals(1, result.get(DateTimeFieldType.era()));
    }

    public void testWith3e_3_oe() {
        Partial test = new Partial(DateTimeFieldType.era(), 1);
        Partial result = test.with(DateTimeFieldType.halfdayOfDay(), 0);
        // removed other assertion
        // removed other assertion
        assertEquals(0, result.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testWith3e_4_oe() {
        Partial test = new Partial(DateTimeFieldType.era(), 1);
        Partial result = test.with(DateTimeFieldType.halfdayOfDay(), 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, result.indexOf(DateTimeFieldType.era()));
    }

    public void testWith3e_5_oe() {
        Partial test = new Partial(DateTimeFieldType.era(), 1);
        Partial result = test.with(DateTimeFieldType.halfdayOfDay(), 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, result.indexOf(DateTimeFieldType.halfdayOfDay()));
    }

    public void testWith3f_1_oe() {
        Partial test = new Partial(DateTimeFieldType.halfdayOfDay(), 0);
        Partial result = test.with(DateTimeFieldType.era(), 1);
        assertEquals(2, result.size());
    }

    public void testWith3f_2_oe() {
        Partial test = new Partial(DateTimeFieldType.halfdayOfDay(), 0);
        Partial result = test.with(DateTimeFieldType.era(), 1);
        // removed other assertion
        assertEquals(1, result.get(DateTimeFieldType.era()));
    }

    public void testWith3f_3_oe() {
        Partial test = new Partial(DateTimeFieldType.halfdayOfDay(), 0);
        Partial result = test.with(DateTimeFieldType.era(), 1);
        // removed other assertion
        // removed other assertion
        assertEquals(0, result.get(DateTimeFieldType.halfdayOfDay()));
    }

    public void testWith3f_4_oe() {
        Partial test = new Partial(DateTimeFieldType.halfdayOfDay(), 0);
        Partial result = test.with(DateTimeFieldType.era(), 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, result.indexOf(DateTimeFieldType.era()));
    }

    public void testWith3f_5_oe() {
        Partial test = new Partial(DateTimeFieldType.halfdayOfDay(), 0);
        Partial result = test.with(DateTimeFieldType.era(), 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, result.indexOf(DateTimeFieldType.halfdayOfDay()));
    }

    public void testWith4_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.with(DateTimeFieldType.hourOfDay(), 10);
        assertSame(test, result);
    }

    public void testWith_baseHasNoRange_1_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 1);
        Partial result = test.with(DateTimeFieldType.hourOfDay(), 10);
        assertEquals(2, result.size());
    }

    public void testWith_baseHasNoRange_2_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 1);
        Partial result = test.with(DateTimeFieldType.hourOfDay(), 10);
        // removed other assertion
        assertEquals(0, result.indexOf(DateTimeFieldType.year()));
    }

    public void testWith_baseHasNoRange_3_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 1);
        Partial result = test.with(DateTimeFieldType.hourOfDay(), 10);
        // removed other assertion
        // removed other assertion
        assertEquals(1, result.indexOf(DateTimeFieldType.hourOfDay()));
    }

    public void testWith_argHasNoRange_1_oe() {
        Partial test = new Partial(DateTimeFieldType.hourOfDay(), 1);
        Partial result = test.with(DateTimeFieldType.year(), 10);
        assertEquals(2, result.size());
    }

    public void testWith_argHasNoRange_2_oe() {
        Partial test = new Partial(DateTimeFieldType.hourOfDay(), 1);
        Partial result = test.with(DateTimeFieldType.year(), 10);
        // removed other assertion
        assertEquals(0, result.indexOf(DateTimeFieldType.year()));
    }

    public void testWith_argHasNoRange_3_oe() {
        Partial test = new Partial(DateTimeFieldType.hourOfDay(), 1);
        Partial result = test.with(DateTimeFieldType.year(), 10);
        // removed other assertion
        // removed other assertion
        assertEquals(1, result.indexOf(DateTimeFieldType.hourOfDay()));
    }

    public void testWith_baseAndArgHaveNoRange_1_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 1);
        Partial result = test.with(DateTimeFieldType.era(), 1);
        assertEquals(2, result.size());
    }

    public void testWith_baseAndArgHaveNoRange_2_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 1);
        Partial result = test.with(DateTimeFieldType.era(), 1);
        // removed other assertion
        assertEquals(0, result.indexOf(DateTimeFieldType.era()));
    }

    public void testWith_baseAndArgHaveNoRange_3_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 1);
        Partial result = test.with(DateTimeFieldType.era(), 1);
        // removed other assertion
        // removed other assertion
        assertEquals(1, result.indexOf(DateTimeFieldType.year()));
    }

    public void testWithout3_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.without(DateTimeFieldType.hourOfDay());
        check(test, 10, 20);
        assertEquals(1, result.size());
    }

    public void testWithout3_2_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.without(DateTimeFieldType.hourOfDay());
        check(test, 10, 20);
        // removed other assertion
        assertEquals(false, result.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testWithout3_3_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.without(DateTimeFieldType.hourOfDay());
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.minuteOfHour()));
    }

    public void testWithout3_4_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.without(DateTimeFieldType.hourOfDay());
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.minuteOfHour(), result.getFieldType(0));
    }

    public void testWithout4_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.without(DateTimeFieldType.minuteOfHour());
        check(test, 10, 20);
        assertEquals(1, result.size());
    }

    public void testWithout4_2_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.without(DateTimeFieldType.minuteOfHour());
        check(test, 10, 20);
        // removed other assertion
        assertEquals(true, result.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testWithout4_3_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.without(DateTimeFieldType.minuteOfHour());
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        assertEquals(false, result.isSupported(DateTimeFieldType.minuteOfHour()));
    }

    public void testWithout4_4_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.without(DateTimeFieldType.minuteOfHour());
        check(test, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeFieldType.hourOfDay(), result.getFieldType(0));
    }

    public void testWithout5_1_oe() {
        Partial test = new Partial(DateTimeFieldType.hourOfDay(), 12);
        Partial result = test.without(DateTimeFieldType.hourOfDay());
        assertEquals(0, result.size());
    }

    public void testWithout5_2_oe() {
        Partial test = new Partial(DateTimeFieldType.hourOfDay(), 12);
        Partial result = test.without(DateTimeFieldType.hourOfDay());
        // removed other assertion
        assertEquals(false, result.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testWithField4_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.withField(DateTimeFieldType.hourOfDay(), 10);
        assertSame(test, result);
    }

    public void testWithFieldAdded1_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.withFieldAdded(DurationFieldType.hours(), 6);
        
        assertEquals(createHourMinPartial(), test);
    }

    public void testWithFieldAdded4_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.withFieldAdded(DurationFieldType.hours(), 0);
        assertSame(test, result);
    }

    public void testWithFieldAddWrapped1_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.withFieldAddWrapped(DurationFieldType.hours(), 6);
        
        assertEquals(createHourMinPartial(), test);
    }

    public void testWithFieldAddWrapped4_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.withFieldAddWrapped(DurationFieldType.hours(), 0);
        assertSame(test, result);
    }

    public void testWithFieldAddWrapped6_1_oe() {
        Partial test = createHourMinPartial();
        Partial result = test.withFieldAddWrapped(DurationFieldType.hours(), 16);
        
        assertEquals(createHourMinPartial(), test);
    }

    public void testPlus_RP_1_oe() {
        Partial test = createHourMinPartial(BUDDHIST_LONDON);
        Partial result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        check(test, 10, 20);
        check(result, 15, 26);
        
        result = test.plus((ReadablePeriod) null);
        assertSame(test, result);
    }

    public void testMinus_RP_1_oe() {
        Partial test = createHourMinPartial(BUDDHIST_LONDON);
        Partial result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        check(test, 10, 20);
        check(result, 9, 19);
        
        result = test.minus((ReadablePeriod) null);
        assertSame(test, result);
    }

    public void testToDateTime_RI_1_oe() {
        Partial base = createHourMinPartial(COPTIC_PARIS);
        DateTime dt = new DateTime(0L); // LONDON zone
        assertEquals("1970-01-01T01:00:00.000+01:00", dt.toString());
    }

    public void testToDateTime_RI_2_oe() {
        Partial base = createHourMinPartial(COPTIC_PARIS);
        DateTime dt = new DateTime(0L); // LONDON zone
        // removed other assertion
        
        DateTime test = base.toDateTime(dt);
        check(base, 10, 20);
        assertEquals("1970-01-01T01:00:00.000+01:00", dt.toString());
    }

    public void testToDateTime_RI_3_oe() {
        Partial base = createHourMinPartial(COPTIC_PARIS);
        DateTime dt = new DateTime(0L); // LONDON zone
        // removed other assertion
        
        DateTime test = base.toDateTime(dt);
        check(base, 10, 20);
        // removed other assertion
        assertEquals("1970-01-01T10:20:00.000+01:00", test.toString());
    }

    public void testToDateTime_nullRI_1_oe() {
        Partial base = createHourMinPartial(1, 2, ISO_UTC);
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2);
        
        DateTime test = base.toDateTime((ReadableInstant) null);
        check(base, 1, 2);
        assertEquals("1970-01-02T01:02:07.008+01:00", test.toString());
    }

    public void testProperty_1_oe() {
        Partial test = createHourMinPartial();
        assertNotNull(test.property(DateTimeFieldType.hourOfDay()));
    }

    public void testProperty_2_oe() {
        Partial test = createHourMinPartial();
        // removed other assertion
        assertNotNull(test.property(DateTimeFieldType.minuteOfHour()));
    }

    public void testSerialization_1_oe() throws Exception {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Partial result = (Partial) ois.readObject();
        ois.close();
        
        assertEquals(test, result);
    }

    public void testSerialization_2_oe() throws Exception {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Partial result = (Partial) ois.readObject();
        ois.close();
        
        // removed other assertion
        assertTrue(Arrays.equals(test.getValues(), result.getValues()));
    }

    public void testSerialization_3_oe() throws Exception {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Partial result = (Partial) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(test.getFields(), result.getFields()));
    }

    public void testSerialization_4_oe() throws Exception {
        Partial test = createHourMinPartial(COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Partial result = (Partial) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology(), result.getChronology());
    }

    public void testGetFormatter1_1_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        assertEquals("2005", test.getFormatter().print(test));
    }

    public void testGetFormatter1_2_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.monthOfYear(), 6);
        assertEquals("2005-06", test.getFormatter().print(test));
    }

    public void testGetFormatter1_3_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.monthOfYear(), 6);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.dayOfMonth(), 25);
        assertEquals("2005-06-25", test.getFormatter().print(test));
    }

    public void testGetFormatter1_4_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.monthOfYear(), 6);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.dayOfMonth(), 25);
        // removed other assertion
        
        test = test.without(DateTimeFieldType.monthOfYear());
        assertEquals("2005--25", test.getFormatter().print(test));
    }

    public void testGetFormatter2_1_oe() {
        Partial test = new Partial();
        assertEquals(null, test.getFormatter());
    }

    public void testGetFormatter2_2_oe() {
        Partial test = new Partial();
        // removed other assertion
        
        test = test.with(DateTimeFieldType.era(), 1);
        assertEquals(null, test.getFormatter());
    }

    public void testGetFormatter2_3_oe() {
        Partial test = new Partial();
        // removed other assertion
        
        test = test.with(DateTimeFieldType.era(), 1);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.halfdayOfDay(), 0);
        assertEquals(null, test.getFormatter());
    }

    public void testGetFormatter3_1_oe() {
        Partial test = new Partial(DateTimeFieldType.dayOfWeek(), 5);
        assertEquals("-W-5", test.getFormatter().print(test));
    }

    public void testGetFormatter3_2_oe() {
        Partial test = new Partial(DateTimeFieldType.dayOfWeek(), 5);
        // removed other assertion
        
        // contrast with testToString5
        test = test.with(DateTimeFieldType.dayOfMonth(), 13);
        assertEquals("---13", test.getFormatter().print(test));
    }

    public void testToString1_1_oe() {
        Partial test = createHourMinPartial();
        assertEquals("10:20", test.toString());
    }

    public void testToString2_1_oe() {
        Partial test = new Partial();
        assertEquals("[]", test.toString());
    }

    public void testToString3_1_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        assertEquals("2005", test.toString());
    }

    public void testToString3_2_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.monthOfYear(), 6);
        assertEquals("2005-06", test.toString());
    }

    public void testToString3_3_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.monthOfYear(), 6);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.dayOfMonth(), 25);
        assertEquals("2005-06-25", test.toString());
    }

    public void testToString3_4_oe() {
        Partial test = new Partial(DateTimeFieldType.year(), 2005);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.monthOfYear(), 6);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.dayOfMonth(), 25);
        // removed other assertion
        
        test = test.without(DateTimeFieldType.monthOfYear());
        assertEquals("2005--25", test.toString());
    }

    public void testToString4_1_oe() {
        Partial test = new Partial(DateTimeFieldType.dayOfWeek(), 5);
        assertEquals("-W-5", test.toString());
    }

    public void testToString4_2_oe() {
        Partial test = new Partial(DateTimeFieldType.dayOfWeek(), 5);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.dayOfMonth(), 13);
        assertEquals("[dayOfMonth=13, dayOfWeek=5]", test.toString());
    }

    public void testToString5_1_oe() {
        Partial test = new Partial(DateTimeFieldType.era(), 1);
        assertEquals("[era=1]", test.toString());
    }

    public void testToString5_2_oe() {
        Partial test = new Partial(DateTimeFieldType.era(), 1);
        // removed other assertion
        
        test = test.with(DateTimeFieldType.halfdayOfDay(), 0);
        assertEquals("[era=1, halfdayOfDay=0]", test.toString());
    }

    public void testToString_String_1_oe() {
        Partial test = createHourMinPartial();
        assertEquals("\ufffd\ufffd\ufffd\ufffd 10", test.toString("yyyy HH"));
    }

    public void testToString_String_2_oe() {
        Partial test = createHourMinPartial();
        // removed other assertion
        assertEquals("10:20", test.toString((String) null));
    }

    public void testToString_String_Locale_1_oe() {
        Partial test = createHourMinPartial();
        assertEquals("10 20", test.toString("H m", Locale.ENGLISH));
    }

    public void testToString_String_Locale_2_oe() {
        Partial test = createHourMinPartial();
        // removed other assertion
        assertEquals("10:20", test.toString(null, Locale.ENGLISH));
    }

    public void testToString_String_Locale_3_oe() {
        Partial test = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        assertEquals("10 20", test.toString("H m", null));
    }

    public void testToString_String_Locale_4_oe() {
        Partial test = createHourMinPartial();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("10:20", test.toString(null, null));
    }

    public void testToString_DTFormatter_1_oe() {
        Partial test = createHourMinPartial();
        assertEquals("\ufffd\ufffd\ufffd\ufffd 10", test.toString(DateTimeFormat.forPattern("yyyy HH")));
    }

    public void testToString_DTFormatter_2_oe() {
        Partial test = createHourMinPartial();
        // removed other assertion
        assertEquals("10:20", test.toString((DateTimeFormatter) null));
    }

}
