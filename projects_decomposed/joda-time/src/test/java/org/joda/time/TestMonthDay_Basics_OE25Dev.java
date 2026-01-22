/*
 *  Copyright 2001-2010 Stephen Colebourne
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
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for MonthDay. Based on {@link TestYearMonth_Basics} 
 */
public class TestMonthDay_Basics_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
//    private static final Chronology COPTIC_LONDON = CopticChronology.getInstance(LONDON);
    private static final Chronology COPTIC_TOKYO = CopticChronology.getInstance(TOKYO);
    private static final Chronology COPTIC_UTC = CopticChronology.getInstanceUTC();
//    private static final Chronology ISO_PARIS = ISOChronology.getInstance(PARIS);
//    private static final Chronology ISO_LONDON = ISOChronology.getInstance(LONDON);
//    private static final Chronology ISO_TOKYO = ISOChronology.getInstance(TOKYO);
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
//    private static final Chronology BUDDHIST_PARIS = BuddhistChronology.getInstance(PARIS);
//    private static final Chronology BUDDHIST_LONDON = BuddhistChronology.getInstance(LONDON);
    private static final Chronology BUDDHIST_TOKYO = BuddhistChronology.getInstance(TOKYO);
    private static final Chronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();
    
    private long TEST_TIME_NOW =
            (31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;

    private DateTimeZone zone = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMonthDay_Basics_OE25Dev_OE25Dev.class);
    }

    public TestMonthDay_Basics_OE25Dev(String name) {
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
    
    class MockMD extends MockPartial {
        
        @Override
        public Chronology getChronology() {
            return COPTIC_UTC;
        }
        
        @Override
        public DateTimeField[] getFields() {
            return new DateTimeField[] {
                COPTIC_UTC.monthOfYear(),
                COPTIC_UTC.dayOfMonth()
            };
        }
        
        @Override
        public int[] getValues() {
            return new int[] {10, 6};
        }
    }

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testWithField_nullField() {
        MonthDay test = new MonthDay(9, 6);
        try {
            test.withField(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithField_unknownField() {
        MonthDay test = new MonthDay(9, 6);
        try {
            test.withField(DateTimeFieldType.hourOfDay(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testWithFieldAdded_nullField_zero() {
        MonthDay test = new MonthDay(9, 6);
        try {
            test.withFieldAdded(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_nullField_nonZero() {
        MonthDay test = new MonthDay(9, 6);
        try {
            test.withFieldAdded(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_unknownField() {
        MonthDay test = new MonthDay(9, 6);
        try {
            test.withFieldAdded(DurationFieldType.hours(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-------------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-------------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testWithers() {
        MonthDay test = new MonthDay(10, 6);
        check(test.withMonthOfYear(5), 5, 6);
        check(test.withDayOfMonth(2), 10, 2);
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
    private void check(MonthDay test, int month, int day) {
        assertEquals(month, test.getMonthOfYear());
        assertEquals(day, test.getDayOfMonth());
    }

    public void testGet_1_oe() {
        MonthDay test = new MonthDay();
        assertEquals(6, test.get(DateTimeFieldType.monthOfYear()));
    }

    public void testGet_2_oe() {
        MonthDay test = new MonthDay();
        // removed other assertion
        assertEquals(9, test.get(DateTimeFieldType.dayOfMonth()));
    }

    public void testSize_1_oe() {
        MonthDay test = new MonthDay();
        assertEquals(2, test.size());
    }

    public void testGetFieldType_1_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        assertSame(DateTimeFieldType.monthOfYear(), test.getFieldType(0));
    }

    public void testGetFieldType_2_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        // removed other assertion
        assertSame(DateTimeFieldType.dayOfMonth(), test.getFieldType(1));
    }

    public void testGetFieldTypes_1_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        assertEquals(2, fields.length);
    }

    public void testGetFieldTypes_2_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        assertSame(DateTimeFieldType.monthOfYear(), fields[0]);
    }

    public void testGetFieldTypes_3_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.dayOfMonth(), fields[1]);
    }

    public void testGetFieldTypes_4_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFieldTypes(), test.getFieldTypes());
    }

    public void testGetField_1_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        assertSame(COPTIC_UTC.monthOfYear(), test.getField(0));
    }

    public void testGetField_2_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        // removed other assertion
        assertSame(COPTIC_UTC.dayOfMonth(), test.getField(1));
    }

    public void testGetFields_1_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        assertEquals(2, fields.length);
    }

    public void testGetFields_2_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        assertSame(COPTIC_UTC.monthOfYear(), fields[0]);
    }

    public void testGetFields_3_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.dayOfMonth(), fields[1]);
    }

    public void testGetFields_4_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFields(), test.getFields());
    }

    public void testGetValue_1_oe() {
        MonthDay test = new MonthDay();
        assertEquals(6, test.getValue(0));
    }

    public void testGetValue_2_oe() {
        MonthDay test = new MonthDay();
        // removed other assertion
        assertEquals(9, test.getValue(1));
    }

    public void testGetValues_1_oe() {
        MonthDay test = new MonthDay();
        int[] values = test.getValues();
        assertEquals(2, values.length);
    }

    public void testGetValues_2_oe() {
        MonthDay test = new MonthDay();
        int[] values = test.getValues();
        // removed other assertion
        assertEquals(6, values[0]);
    }

    public void testGetValues_3_oe() {
        MonthDay test = new MonthDay();
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        assertEquals(9, values[1]);
    }

    public void testGetValues_4_oe() {
        MonthDay test = new MonthDay();
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getValues(), test.getValues());
    }

    public void testIsSupported_1_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        assertEquals(false, test.isSupported(DateTimeFieldType.year()));
    }

    public void testIsSupported_2_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.monthOfYear()));
    }

    public void testIsSupported_3_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isSupported(DateTimeFieldType.dayOfMonth()));
    }

    public void testIsSupported_4_oe() {
        MonthDay test = new MonthDay(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testEqualsHashCode_1_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(10, 6);
        assertEquals(false, test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(10, 6);
        // removed other assertion
        assertEquals(false, test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(10, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(10, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(10, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(10, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test2.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(10, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test1.equals("Hello"));
    }

    public void testEqualsHashCode_15_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(10, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test1.equals(new MockMD()));
    }

    public void testEqualsHashCode_16_oe() {
        MonthDay test1 = new MonthDay(10, 6, COPTIC_PARIS);
        MonthDay test2 = new MonthDay(10, 6, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(10, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.equals(MockPartial.EMPTY_INSTANCE));
    }

    public void testCompareTo_1_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        assertEquals(0, test1.compareTo(test1a));
    }

    public void testCompareTo_2_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_3_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test1.compareTo(test1));
    }

    public void testCompareTo_4_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test1a.compareTo(test1a));
    }

    public void testCompareTo_5_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        assertEquals(-1, test1.compareTo(test2));
    }

    public void testCompareTo_6_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        assertEquals(+1, test2.compareTo(test1));
    }

    public void testCompareTo_7_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        assertEquals(-1, test1.compareTo(test3));
    }

    public void testCompareTo_8_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(+1, test3.compareTo(test1));
    }

    public void testCompareTo_9_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(0, test3.compareTo(test2));
    }

    public void testCompareTo_10_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth()
        };
        int[] values = new int[] {6, 6};
        Partial p = new Partial(types, values);
        assertEquals(0, test1.compareTo(p));
    }

    public void testIsEqual_MD_1_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_MD_2_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_MD_3_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.isEqual(test1));
    }

    public void testIsEqual_MD_4_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1a.isEqual(test1a));
    }

    public void testIsEqual_MD_5_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        assertEquals(false, test1.isEqual(test2));
    }

    public void testIsEqual_MD_6_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        assertEquals(false, test2.isEqual(test1));
    }

    public void testIsEqual_MD_7_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        assertEquals(false, test1.isEqual(test3));
    }

    public void testIsEqual_MD_8_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(false, test3.isEqual(test1));
    }

    public void testIsEqual_MD_9_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(true, test3.isEqual(test2));
    }

    public void testIsBefore_MD_1_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        assertEquals(false, test1.isBefore(test1a));
    }

    public void testIsBefore_MD_2_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        assertEquals(false, test1a.isBefore(test1));
    }

    public void testIsBefore_MD_3_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isBefore(test1));
    }

    public void testIsBefore_MD_4_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1a.isBefore(test1a));
    }

    public void testIsBefore_MD_5_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        assertEquals(true, test1.isBefore(test2));
    }

    public void testIsBefore_MD_6_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        assertEquals(false, test2.isBefore(test1));
    }

    public void testIsBefore_MD_7_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        assertEquals(true, test1.isBefore(test3));
    }

    public void testIsBefore_MD_8_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(false, test3.isBefore(test1));
    }

    public void testIsBefore_MD_9_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.isBefore(test2));
    }

    public void testIsAfter_MD_1_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        assertEquals(false, test1.isAfter(test1a));
    }

    public void testIsAfter_MD_2_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        assertEquals(false, test1a.isAfter(test1));
    }

    public void testIsAfter_MD_3_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isAfter(test1));
    }

    public void testIsAfter_MD_4_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1a.isAfter(test1a));
    }

    public void testIsAfter_MD_5_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        assertEquals(false, test1.isAfter(test2));
    }

    public void testIsAfter_MD_6_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        assertEquals(true, test2.isAfter(test1));
    }

    public void testIsAfter_MD_7_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        assertEquals(false, test1.isAfter(test3));
    }

    public void testIsAfter_MD_8_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(true, test3.isAfter(test1));
    }

    public void testIsAfter_MD_9_oe() {
        MonthDay test1 = new MonthDay(6, 6);
        MonthDay test1a = new MonthDay(6, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MonthDay test2 = new MonthDay(6, 7);
        // removed other assertion
        // removed other assertion
        
        MonthDay test3 = new MonthDay(6, 7, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.isAfter(test2));
    }

    public void testWithChronologyRetainFields_Chrono_1_oe() {
        MonthDay base = new MonthDay(6, 6, COPTIC_PARIS);
        MonthDay test = base.withChronologyRetainFields(BUDDHIST_TOKYO);
        check(base, 6, 6);
        assertEquals(COPTIC_UTC, base.getChronology());
    }

    public void testWithChronologyRetainFields_Chrono_2_oe() {
        MonthDay base = new MonthDay(6, 6, COPTIC_PARIS);
        MonthDay test = base.withChronologyRetainFields(BUDDHIST_TOKYO);
        check(base, 6, 6);
        // removed other assertion
        check(test, 6, 6);
        assertEquals(BUDDHIST_UTC, test.getChronology());
    }

    public void testWithChronologyRetainFields_sameChrono_1_oe() {
        MonthDay base = new MonthDay(6, 6, COPTIC_PARIS);
        MonthDay test = base.withChronologyRetainFields(COPTIC_TOKYO);
        assertSame(base, test);
    }

    public void testWithChronologyRetainFields_nullChrono_1_oe() {
        MonthDay base = new MonthDay(6, 6, COPTIC_PARIS);
        MonthDay test = base.withChronologyRetainFields(null);
        check(base, 6, 6);
        assertEquals(COPTIC_UTC, base.getChronology());
    }

    public void testWithChronologyRetainFields_nullChrono_2_oe() {
        MonthDay base = new MonthDay(6, 6, COPTIC_PARIS);
        MonthDay test = base.withChronologyRetainFields(null);
        check(base, 6, 6);
        // removed other assertion
        check(test, 6, 6);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testWithField_1_oe() {
        MonthDay test = new MonthDay(9, 6);
        MonthDay result = test.withField(DateTimeFieldType.monthOfYear(), 10);
        
        assertEquals(new MonthDay(9, 6), test);
    }

    public void testWithField_2_oe() {
        MonthDay test = new MonthDay(9, 6);
        MonthDay result = test.withField(DateTimeFieldType.monthOfYear(), 10);
        
        // removed other assertion
        assertEquals(new MonthDay(10, 6), result);
    }

    public void testWithField_same_1_oe() {
        MonthDay test = new MonthDay(9, 6);
        MonthDay result = test.withField(DateTimeFieldType.monthOfYear(), 9);
        assertEquals(new MonthDay(9, 6), test);
    }

    public void testWithField_same_2_oe() {
        MonthDay test = new MonthDay(9, 6);
        MonthDay result = test.withField(DateTimeFieldType.monthOfYear(), 9);
        // removed other assertion
        assertSame(test, result);
    }

    public void testWithFieldAdded_1_oe() {
        MonthDay test = new MonthDay(9, 6);
        MonthDay result = test.withFieldAdded(DurationFieldType.months(), 1);
        
        assertEquals(new MonthDay(9, 6), test);
    }

    public void testWithFieldAdded_2_oe() {
        MonthDay test = new MonthDay(9, 6);
        MonthDay result = test.withFieldAdded(DurationFieldType.months(), 1);
        
        // removed other assertion
        assertEquals(new MonthDay(10, 6), result);
    }

    public void testWithFieldAdded_zero_1_oe() {
        MonthDay test = new MonthDay(9, 6);
        MonthDay result = test.withFieldAdded(DurationFieldType.months(), 0);
        assertSame(test, result);
    }

    public void testPlus_RP_1_oe() {
        MonthDay test = new MonthDay(6, 5, BuddhistChronology.getInstance());
        MonthDay result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        MonthDay expected = new MonthDay(8, 9, BuddhistChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testPlus_RP_2_oe() {
        MonthDay test = new MonthDay(6, 5, BuddhistChronology.getInstance());
        MonthDay result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        MonthDay expected = new MonthDay(8, 9, BuddhistChronology.getInstance());
        // removed other assertion
        
        result = test.plus((ReadablePeriod) null);
        assertSame(test, result);
    }

    public void testPlusMonths_int_1_oe() {
        MonthDay test = new MonthDay(6, 5, BuddhistChronology.getInstance());
        MonthDay result = test.plusMonths(1);
        MonthDay expected = new MonthDay(7, 5, BuddhistChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testPlusMonths_int_fromLeap_1_oe() {
        MonthDay test = new MonthDay(2, 29, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusMonths(1);
        MonthDay expected = new MonthDay(3, 29, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testPlusMonths_int_negativeFromLeap_1_oe() {
        MonthDay test = new MonthDay(2, 29, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusMonths(-1);
        MonthDay expected = new MonthDay(1, 29, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testPlusMonths_int_endOfMonthAdjust_1_oe() {
        MonthDay test = new MonthDay(3, 31, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusMonths(1);
        MonthDay expected = new MonthDay(4, 30, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testPlusMonths_int_negativeEndOfMonthAdjust_1_oe() {
        MonthDay test = new MonthDay(3, 31, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusMonths(-1);
        MonthDay expected = new MonthDay(2, 29, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testPlusMonths_int_same_1_oe() {
        MonthDay test = new MonthDay(6, 5, ISO_UTC);
        MonthDay result = test.plusMonths(0);
        assertSame(test, result);
    }

    public void testPlusMonths_int_wrap_1_oe() {
        MonthDay test = new MonthDay(6, 5, ISO_UTC);
        MonthDay result = test.plusMonths(10);
        MonthDay expected = new MonthDay(4, 5, ISO_UTC);
        assertEquals(expected, result);
    }

    public void testPlusMonths_int_adjust_1_oe() {
        MonthDay test = new MonthDay(7, 31, ISO_UTC);
        MonthDay result = test.plusMonths(2);
        MonthDay expected = new MonthDay(9, 30, ISO_UTC);
        assertEquals(expected, result);
    }

    public void testPlusDays_int_1_oe() {
        MonthDay test = new MonthDay(5, 10, BuddhistChronology.getInstance());
        MonthDay result = test.plusDays(1);
        MonthDay expected = new MonthDay(5, 11, BuddhistChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testPlusDays_int_wrapMonth_1_oe() {
        MonthDay test = new MonthDay(11, 1, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusDays(31);
        MonthDay expected = new MonthDay(12, 2, ISOChronology.getInstanceUTC());
        assertEquals(expected, result);
    }

    public void testPlusDays_int_wrapMonthTwice_1_oe() {
        MonthDay test = new MonthDay(10, 31, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusDays(32);
        MonthDay expected = new MonthDay(12, 2, ISOChronology.getInstanceUTC());
        assertEquals(expected, result);
    }

    public void testPlusDays_int_wrapMonthIntoNextYear_1_oe() {
        MonthDay test = new MonthDay(12, 1, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusDays(31);
        MonthDay expected = new MonthDay(1, 1, ISOChronology.getInstanceUTC());
        assertEquals(expected, result);
    }

    public void testPlusDays_int_wrapMonthTwiceIntoNextYear_1_oe() {
        MonthDay test = new MonthDay(11, 30, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusDays(32);
        MonthDay expected = new MonthDay(1, 1, ISOChronology.getInstanceUTC());
        assertEquals(expected, result);
    }

    public void testPlusDays_int_wrap50_1_oe() {
        MonthDay test = new MonthDay(5, 15, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusDays(50);
        MonthDay expected = new MonthDay(7, 4, ISOChronology.getInstanceUTC());
        assertEquals(expected, result);
    }

    public void testPlusDays_int_toLeap_1_oe() {
        MonthDay test = new MonthDay(2, 28, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusDays(1);
        MonthDay expected = new MonthDay(2, 29, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testPlusDays_int_fromLeap_1_oe() {
        MonthDay test = new MonthDay(2, 29, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusDays(1);
        MonthDay expected = new MonthDay(3, 1, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testPlusDays_int_negativeFromLeap_1_oe() {
        MonthDay test = new MonthDay(2, 29, ISOChronology.getInstanceUTC());
        MonthDay result = test.plusDays(-1);
        MonthDay expected = new MonthDay(2, 28, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testPlusDays_same_1_oe() {
        MonthDay test = new MonthDay(5, 10, BuddhistChronology.getInstance());
        MonthDay result = test.plusDays(0);
        assertSame(test, result);
    }

    public void testMinus_RP_1_oe() {
        MonthDay test = new MonthDay(6, 5, BuddhistChronology.getInstance());
        MonthDay result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        MonthDay expected = new MonthDay(5, 4, BuddhistChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testMinus_RP_2_oe() {
        MonthDay test = new MonthDay(6, 5, BuddhistChronology.getInstance());
        MonthDay result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        MonthDay expected = new MonthDay(5, 4, BuddhistChronology.getInstance());
        // removed other assertion
        
        result = test.minus((ReadablePeriod) null);
        assertSame(test, result);
    }

    public void testMinusMonths_int_1_oe() {
        MonthDay test = new MonthDay(6, 5, BuddhistChronology.getInstance());
        MonthDay result = test.minusMonths(1);
        MonthDay expected = new MonthDay(5, 5, BuddhistChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testMinusMonths_int_fromLeap_1_oe() {
        MonthDay test = new MonthDay(2, 29, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusMonths(1);
        MonthDay expected = new MonthDay(1, 29, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testMinusMonths_int_negativeFromLeap_1_oe() {
        MonthDay test = new MonthDay(2, 29, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusMonths(-1);
        MonthDay expected = new MonthDay(3, 29, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testMinusMonths_int_endOfMonthAdjust_1_oe() {
        MonthDay test = new MonthDay(3, 31, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusMonths(1);
        MonthDay expected = new MonthDay(2, 29, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testMinusMonths_int_negativeEndOfMonthAdjust_1_oe() {
        MonthDay test = new MonthDay(3, 31, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusMonths(-1);
        MonthDay expected = new MonthDay(4, 30, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testMinusMonths_int_same_1_oe() {
        MonthDay test = new MonthDay(6, 5, ISO_UTC);
        MonthDay result = test.minusMonths(0);
        assertSame(test, result);
    }

    public void testMinusMonths_int_wrap_1_oe() {
        MonthDay test = new MonthDay(6, 5, ISO_UTC);
        MonthDay result = test.minusMonths(10);
        MonthDay expected = new MonthDay(8, 5, ISO_UTC);
        assertEquals(expected, result);
    }

    public void testMinusMonths_int_adjust_1_oe() {
        MonthDay test = new MonthDay(7, 31, ISO_UTC);
        MonthDay result = test.minusMonths(3);
        MonthDay expected = new MonthDay(4, 30, ISO_UTC);
        assertEquals(expected, result);
    }

    public void testMinusDays_int_1_oe() {
        MonthDay test = new MonthDay(5, 11, BuddhistChronology.getInstance());
        MonthDay result = test.minusDays(1);
        MonthDay expected = new MonthDay(5, 10, BuddhistChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testMinusDays_int_wrapMonth_1_oe() {
        MonthDay test = new MonthDay(12, 1, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusDays(30);
        MonthDay expected = new MonthDay(11, 1, ISOChronology.getInstanceUTC());
        assertEquals(expected, result);
    }

    public void testMinusDays_int_wrapMonthTwice_1_oe() {
        MonthDay test = new MonthDay(12, 1, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusDays(31);
        MonthDay expected = new MonthDay(10, 31, ISOChronology.getInstanceUTC());
        assertEquals(expected, result);
    }

    public void testMinusDays_int_wrapMonthIntoLastYear_1_oe() {
        MonthDay test = new MonthDay(1, 1, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusDays(31);
        MonthDay expected = new MonthDay(12, 1, ISOChronology.getInstanceUTC());
        assertEquals(expected, result);
    }

    public void testMinusDays_int_wrapMonthTwiceIntoLastYear_1_oe() {
        MonthDay test = new MonthDay(1, 1, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusDays(32);
        MonthDay expected = new MonthDay(11, 30, ISOChronology.getInstanceUTC());
        assertEquals(expected, result);
    }

    public void testMinusDays_int_toLeap_1_oe() {
        MonthDay test = new MonthDay(3, 1, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusDays(1);
        MonthDay expected = new MonthDay(2, 29, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testMinusDays_int_fromLeap_1_oe() {
        MonthDay test = new MonthDay(2, 29, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusDays(1);
        MonthDay expected = new MonthDay(2, 28, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testMinusDays_int_negativeFromLeap_1_oe() {
        MonthDay test = new MonthDay(2, 29, ISOChronology.getInstanceUTC());
        MonthDay result = test.minusDays(-1);
        MonthDay expected = new MonthDay(3, 1, ISOChronology.getInstance());
        assertEquals(expected, result);
    }

    public void testMinusDays_same_1_oe() {
        MonthDay test = new MonthDay(5, 11, BuddhistChronology.getInstance());
        MonthDay result = test.minusDays(0);
        assertSame(test, result);
    }

    public void testToLocalDate_1_oe() {
        MonthDay base = new MonthDay(6, 6, COPTIC_UTC);
        LocalDate test = base.toLocalDate(2009);
        assertEquals(new LocalDate(2009, 6, 6, COPTIC_UTC), test);
    }

    public void testToDateTime_RI_1_oe() {
        MonthDay base = new MonthDay(6, 6, COPTIC_PARIS);
        DateTime dt = new DateTime(2002, 1, 3, 4, 5, 6, 7);
        
        DateTime test = base.toDateTime(dt);
        check(base, 6, 6);
        DateTime expected = dt;
        expected = expected.monthOfYear().setCopy(6);
        expected = expected.dayOfMonth().setCopy(6);
        assertEquals(expected, test);
    }

    public void testToDateTime_nullRI_1_oe() {
        MonthDay base = new MonthDay(6, 6);
        DateTime dt = new DateTime(2002, 1, 3, 4, 5, 6, 7);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        DateTime test = base.toDateTime((ReadableInstant) null);
        check(base, 6, 6);
        DateTime expected = dt;
        expected = expected.monthOfYear().setCopy(6);
        expected = expected.dayOfMonth().setCopy(6);
        assertEquals(expected, test);
    }

    public void testProperty_1_oe() {
        MonthDay test = new MonthDay(6, 6);
        assertEquals(test.monthOfYear(), test.property(DateTimeFieldType.monthOfYear()));
    }

    public void testProperty_2_oe() {
        MonthDay test = new MonthDay(6, 6);
        // removed other assertion
        assertEquals(test.dayOfMonth(), test.property(DateTimeFieldType.dayOfMonth()));
    }

    public void testSerialization_1_oe() throws Exception {
        MonthDay test = new MonthDay(5, 6, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        MonthDay result = (MonthDay) ois.readObject();
        ois.close();
        
        assertEquals(test, result);
    }

    public void testSerialization_2_oe() throws Exception {
        MonthDay test = new MonthDay(5, 6, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        MonthDay result = (MonthDay) ois.readObject();
        ois.close();
        
        // removed other assertion
        assertTrue(Arrays.equals(test.getValues(), result.getValues()));
    }

    public void testSerialization_3_oe() throws Exception {
        MonthDay test = new MonthDay(5, 6, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        MonthDay result = (MonthDay) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(test.getFields(), result.getFields()));
    }

    public void testSerialization_4_oe() throws Exception {
        MonthDay test = new MonthDay(5, 6, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        MonthDay result = (MonthDay) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology(), result.getChronology());
    }

    public void testToString_1_oe() {
        MonthDay test = new MonthDay(5, 6);
        assertEquals("--05-06", test.toString());
    }

    public void testToString_String_1_oe() {
        MonthDay test = new MonthDay(5, 6);
        assertEquals("05 \ufffd\ufffd", test.toString("MM HH"));
    }

    public void testToString_String_2_oe() {
        MonthDay test = new MonthDay(5, 6);
        // removed other assertion
        assertEquals("--05-06", test.toString((String) null));
    }

    public void testToString_String_Locale_1_oe() {
        MonthDay test = new MonthDay(5, 6);
        assertEquals("\ufffd 6/5", test.toString("EEE d/M", Locale.ENGLISH));
    }

    public void testToString_String_Locale_2_oe() {
        MonthDay test = new MonthDay(5, 6);
        // removed other assertion
        assertEquals("\ufffd 6/5", test.toString("EEE d/M", Locale.FRENCH));
    }

    public void testToString_String_Locale_3_oe() {
        MonthDay test = new MonthDay(5, 6);
        // removed other assertion
        // removed other assertion
        assertEquals("--05-06", test.toString(null, Locale.ENGLISH));
    }

    public void testToString_String_Locale_4_oe() {
        MonthDay test = new MonthDay(5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("\ufffd 6/5", test.toString("EEE d/M", null));
    }

    public void testToString_String_Locale_5_oe() {
        MonthDay test = new MonthDay(5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("--05-06", test.toString(null, null));
    }

    public void testToString_DTFormatter_1_oe() {
        MonthDay test = new MonthDay(5, 6);
        assertEquals("05 \ufffd\ufffd", test.toString(DateTimeFormat.forPattern("MM HH")));
    }

    public void testToString_DTFormatter_2_oe() {
        MonthDay test = new MonthDay(5, 6);
        // removed other assertion
        assertEquals("--05-06", test.toString((DateTimeFormatter) null));
    }

}
