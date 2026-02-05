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
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a Junit unit test for YearMonthDay.
 *
 * @author Stephen Colebourne
 */
@SuppressWarnings("deprecation")
public class TestYearMonthDay_Basics_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
    private static final Chronology COPTIC_LONDON = CopticChronology.getInstance(LONDON);
    private static final Chronology COPTIC_TOKYO = CopticChronology.getInstance(TOKYO);
    private static final Chronology COPTIC_UTC = CopticChronology.getInstanceUTC();
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final Chronology BUDDHIST_TOKYO = BuddhistChronology.getInstance(TOKYO);
    private static final Chronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();
    
    private long TEST_TIME_NOW =
            (31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;

    private DateTimeZone zone = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestYearMonthDay_Basics_OE25Dev.class);
    }

    public TestYearMonthDay_Basics_OE25Dev(String name) {
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

    public void testWithChronologyRetainFields_invalidInNewChrono() {
        YearMonthDay base = new YearMonthDay(2005, 1, 31, ISO_UTC);
        try {
            base.withChronologyRetainFields(COPTIC_UTC);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    public void testWithField2() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        try {
            test.withField(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithField3() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        try {
            test.withField(DateTimeFieldType.hourOfDay(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testWithFieldAdded2() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        try {
            test.withFieldAdded(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded3() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        try {
            test.withFieldAdded(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded5() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        try {
            test.withFieldAdded(DurationFieldType.hours(), 6);
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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testWithers() {
        YearMonthDay test = new YearMonthDay(1970, 6, 9);
        check(test.withYear(2000), 2000, 6, 9);
        check(test.withMonthOfYear(2), 1970, 2, 9);
        check(test.withDayOfMonth(2), 1970, 6, 2);
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
    private void check(YearMonthDay test, int hour, int min, int sec) {
        assertEquals(hour,test.getYear());
        assertEquals(min,test.getMonthOfYear());
        assertEquals(sec,test.getDayOfMonth());
    }

public void testGet_1_oe() {
        YearMonthDay test = new YearMonthDay();
        assertEquals(1970,test.get(DateTimeFieldType.year()));
    }

public void testGet_2_oe() {
        YearMonthDay test = new YearMonthDay();
        // removed other assertion
        assertEquals(6,test.get(DateTimeFieldType.monthOfYear()));
    }

public void testGet_3_oe() {
        YearMonthDay test = new YearMonthDay();
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.get(DateTimeFieldType.dayOfMonth()));
    }

public void testSize_1_oe() {
        YearMonthDay test = new YearMonthDay();
        assertEquals(3,test.size());
    }

public void testGetFieldType_1_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        assertSame(DateTimeFieldType.year(),test.getFieldType(0));
    }

public void testGetFieldType_2_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        // removed other assertion
        assertSame(DateTimeFieldType.monthOfYear(),test.getFieldType(1));
    }

public void testGetFieldType_3_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.dayOfMonth(),test.getFieldType(2));
    }

public void testGetFieldTypes_1_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        assertSame(DateTimeFieldType.year(),fields[0]);
    }

public void testGetFieldTypes_2_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        assertSame(DateTimeFieldType.monthOfYear(),fields[1]);
    }

public void testGetFieldTypes_3_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        assertSame(DateTimeFieldType.dayOfMonth(),fields[2]);
    }

public void testGetFieldTypes_4_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFieldTypes(),test.getFieldTypes());
    }

public void testGetField_1_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        assertSame(COPTIC_UTC.year(),test.getField(0));
    }

public void testGetField_2_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        // removed other assertion
        assertSame(COPTIC_UTC.monthOfYear(),test.getField(1));
    }

public void testGetField_3_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.dayOfMonth(),test.getField(2));
    }

public void testGetFields_1_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        assertSame(COPTIC_UTC.year(),fields[0]);
    }

public void testGetFields_2_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        assertSame(COPTIC_UTC.monthOfYear(),fields[1]);
    }

public void testGetFields_3_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        assertSame(COPTIC_UTC.dayOfMonth(),fields[2]);
    }

public void testGetFields_4_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getFields(),test.getFields());
    }

public void testGetValue_1_oe() {
        YearMonthDay test = new YearMonthDay();
        assertEquals(1970,test.getValue(0));
    }

public void testGetValue_2_oe() {
        YearMonthDay test = new YearMonthDay();
        // removed other assertion
        assertEquals(6,test.getValue(1));
    }

public void testGetValue_3_oe() {
        YearMonthDay test = new YearMonthDay();
        // removed other assertion
        // removed other assertion
        assertEquals(9,test.getValue(2));
    }

public void testGetValues_1_oe() {
        YearMonthDay test = new YearMonthDay();
        int[] values = test.getValues();
        assertEquals(1970,values[0]);
    }

public void testGetValues_2_oe() {
        YearMonthDay test = new YearMonthDay();
        int[] values = test.getValues();
        // removed other assertion
        assertEquals(6,values[1]);
    }

public void testGetValues_3_oe() {
        YearMonthDay test = new YearMonthDay();
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        assertEquals(9,values[2]);
    }

public void testGetValues_4_oe() {
        YearMonthDay test = new YearMonthDay();
        int[] values = test.getValues();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(test.getValues(),test.getValues());
    }

public void testIsSupported_1_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        assertEquals(true,test.isSupported(DateTimeFieldType.year()));
    }

public void testIsSupported_2_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.monthOfYear()));
    }

public void testIsSupported_3_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.dayOfMonth()));
    }

public void testIsSupported_4_oe() {
        YearMonthDay test = new YearMonthDay(COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test.isSupported(DateTimeFieldType.hourOfDay()));
    }

public void testEqualsHashCode_1_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        assertEquals(true,test1.equals(test2));
    }

public void testEqualsHashCode_2_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        assertEquals(true,test2.equals(test1));
    }

public void testEqualsHashCode_3_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.equals(test1));
    }

public void testEqualsHashCode_4_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.equals(test2));
    }

public void testEqualsHashCode_5_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test2.hashCode());
    }

public void testEqualsHashCode_6_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.hashCode()== test1.hashCode());
    }

public void testEqualsHashCode_7_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

public void testEqualsHashCode_8_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(1971, 6, 9);
        assertEquals(false,test1.equals(test3));
    }

public void testEqualsHashCode_9_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(1971, 6, 9);
        // removed other assertion
        assertEquals(false,test2.equals(test3));
    }

public void testEqualsHashCode_10_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test1));
    }

public void testEqualsHashCode_11_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.equals(test2));
    }

public void testEqualsHashCode_12_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.hashCode()== test3.hashCode());
    }

public void testEqualsHashCode_13_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test2.hashCode()== test3.hashCode());
    }

public void testEqualsHashCode_14_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(1971, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,test1.equals("Hello"));
    }

public void testEqualsHashCode_15_oe() {
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(1971, 6, 9);
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
        YearMonthDay test1 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        YearMonthDay test2 = new YearMonthDay(1970, 6, 9, COPTIC_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(1971, 6, 9);
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
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        assertEquals(0,test1.compareTo(test1a));
    }

public void testCompareTo_2_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        assertEquals(0,test1a.compareTo(test1));
    }

public void testCompareTo_3_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        assertEquals(0,test1.compareTo(test1));
    }

public void testCompareTo_4_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,test1a.compareTo(test1a));
    }

public void testCompareTo_5_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        assertEquals(-1,test1.compareTo(test2));
    }

public void testCompareTo_6_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        assertEquals(+1,test2.compareTo(test1));
    }

public void testCompareTo_7_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        assertEquals(-1,test1.compareTo(test3));
    }

public void testCompareTo_8_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(+1,test3.compareTo(test1));
    }

public void testCompareTo_9_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(0,test3.compareTo(test2));
    }

public void testCompareTo_10_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
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

public void testIsEqual_YMD_1_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        assertEquals(true,test1.isEqual(test1a));
    }

public void testIsEqual_YMD_2_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        assertEquals(true,test1a.isEqual(test1));
    }

public void testIsEqual_YMD_3_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1.isEqual(test1));
    }

public void testIsEqual_YMD_4_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test1a.isEqual(test1a));
    }

public void testIsEqual_YMD_5_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        assertEquals(false,test1.isEqual(test2));
    }

public void testIsEqual_YMD_6_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        assertEquals(false,test2.isEqual(test1));
    }

public void testIsEqual_YMD_7_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        assertEquals(false,test1.isEqual(test3));
    }

public void testIsEqual_YMD_8_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(false,test3.isEqual(test1));
    }

public void testIsEqual_YMD_9_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(true,test3.isEqual(test2));
    }

public void testIsBefore_YMD_1_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        assertEquals(false,test1.isBefore(test1a));
    }

public void testIsBefore_YMD_2_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        assertEquals(false,test1a.isBefore(test1));
    }

public void testIsBefore_YMD_3_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.isBefore(test1));
    }

public void testIsBefore_YMD_4_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1a.isBefore(test1a));
    }

public void testIsBefore_YMD_5_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        assertEquals(true,test1.isBefore(test2));
    }

public void testIsBefore_YMD_6_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        assertEquals(false,test2.isBefore(test1));
    }

public void testIsBefore_YMD_7_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        assertEquals(true,test1.isBefore(test3));
    }

public void testIsBefore_YMD_8_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(false,test3.isBefore(test1));
    }

public void testIsBefore_YMD_9_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.isBefore(test2));
    }

public void testIsAfter_YMD_1_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        assertEquals(false,test1.isAfter(test1a));
    }

public void testIsAfter_YMD_2_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        assertEquals(false,test1a.isAfter(test1));
    }

public void testIsAfter_YMD_3_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1.isAfter(test1));
    }

public void testIsAfter_YMD_4_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,test1a.isAfter(test1a));
    }

public void testIsAfter_YMD_5_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        assertEquals(false,test1.isAfter(test2));
    }

public void testIsAfter_YMD_6_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        assertEquals(true,test2.isAfter(test1));
    }

public void testIsAfter_YMD_7_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        assertEquals(false,test1.isAfter(test3));
    }

public void testIsAfter_YMD_8_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(true,test3.isAfter(test1));
    }

public void testIsAfter_YMD_9_oe() {
        YearMonthDay test1 = new YearMonthDay(2005, 6, 2);
        YearMonthDay test1a = new YearMonthDay(2005, 6, 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test2 = new YearMonthDay(2005, 7, 2);
        // removed other assertion
        // removed other assertion
        
        YearMonthDay test3 = new YearMonthDay(2005, 7, 2, GregorianChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(false,test3.isAfter(test2));
    }

public void testWithChronologyRetainFields_Chrono_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        YearMonthDay test = base.withChronologyRetainFields(BUDDHIST_TOKYO);
        check(base, 2005, 6, 9);
        assertEquals(COPTIC_UTC,base.getChronology());
    }

public void testWithChronologyRetainFields_Chrono_2_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        YearMonthDay test = base.withChronologyRetainFields(BUDDHIST_TOKYO);
        check(base, 2005, 6, 9);
        // removed other assertion
        check(test, 2005, 6, 9);
        assertEquals(BUDDHIST_UTC,test.getChronology());
    }

public void testWithChronologyRetainFields_sameChrono_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        YearMonthDay test = base.withChronologyRetainFields(COPTIC_TOKYO);
        assertSame(base,test);
    }

public void testWithChronologyRetainFields_nullChrono_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        YearMonthDay test = base.withChronologyRetainFields(null);
        check(base, 2005, 6, 9);
        assertEquals(COPTIC_UTC,base.getChronology());
    }

public void testWithChronologyRetainFields_nullChrono_2_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        YearMonthDay test = base.withChronologyRetainFields(null);
        check(base, 2005, 6, 9);
        // removed other assertion
        check(test, 2005, 6, 9);
        assertEquals(ISO_UTC,test.getChronology());
    }

public void testWithField1_1_oe() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        YearMonthDay result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(new YearMonthDay(2004,6,9),test);
    }

public void testWithField1_2_oe() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        YearMonthDay result = test.withField(DateTimeFieldType.year(), 2006);
        
        // removed other assertion
        assertEquals(new YearMonthDay(2006,6,9),result);
    }

public void testWithField4_1_oe() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        YearMonthDay result = test.withField(DateTimeFieldType.year(), 2004);
        assertEquals(new YearMonthDay(2004,6,9),test);
    }

public void testWithField4_2_oe() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        YearMonthDay result = test.withField(DateTimeFieldType.year(), 2004);
        // removed other assertion
        assertSame(test,result);
    }

public void testWithFieldAdded1_1_oe() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        YearMonthDay result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(new YearMonthDay(2004,6,9),test);
    }

public void testWithFieldAdded1_2_oe() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        YearMonthDay result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        // removed other assertion
        assertEquals(new YearMonthDay(2010,6,9),result);
    }

public void testWithFieldAdded4_1_oe() {
        YearMonthDay test = new YearMonthDay(2004, 6, 9);
        YearMonthDay result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertSame(test,result);
    }

public void testPlus_RP_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        YearMonthDay expected = new YearMonthDay(2003, 7, 7, BuddhistChronology.getInstance());
        assertEquals(expected,result);
    }

public void testPlus_RP_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        YearMonthDay expected = new YearMonthDay(2003, 7, 7, BuddhistChronology.getInstance());
        // removed other assertion
        
        result = test.plus((ReadablePeriod) null);
        assertSame(test,result);
    }

public void testPlusYears_int_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.plusYears(1);
        YearMonthDay expected = new YearMonthDay(2003, 5, 3, BuddhistChronology.getInstance());
        assertEquals(expected,result);
    }

public void testPlusYears_int_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.plusYears(1);
        YearMonthDay expected = new YearMonthDay(2003, 5, 3, BuddhistChronology.getInstance());
        // removed other assertion
        
        result = test.plusYears(0);
        assertSame(test,result);
    }

public void testPlusMonths_int_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.plusMonths(1);
        YearMonthDay expected = new YearMonthDay(2002, 6, 3, BuddhistChronology.getInstance());
        assertEquals(expected,result);
    }

public void testPlusMonths_int_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.plusMonths(1);
        YearMonthDay expected = new YearMonthDay(2002, 6, 3, BuddhistChronology.getInstance());
        // removed other assertion
        
        result = test.plusMonths(0);
        assertSame(test,result);
    }

public void testPlusDays_int_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.plusDays(1);
        YearMonthDay expected = new YearMonthDay(2002, 5, 4, BuddhistChronology.getInstance());
        assertEquals(expected,result);
    }

public void testPlusDays_int_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.plusDays(1);
        YearMonthDay expected = new YearMonthDay(2002, 5, 4, BuddhistChronology.getInstance());
        // removed other assertion
        
        result = test.plusDays(0);
        assertSame(test,result);
    }

public void testMinus_RP_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        YearMonthDay expected = new YearMonthDay(2001, 4, 2, BuddhistChronology.getInstance());
        assertEquals(expected,result);
    }

public void testMinus_RP_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        YearMonthDay expected = new YearMonthDay(2001, 4, 2, BuddhistChronology.getInstance());
        // removed other assertion
        
        result = test.minus((ReadablePeriod) null);
        assertSame(test,result);
    }

public void testMinusYears_int_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.minusYears(1);
        YearMonthDay expected = new YearMonthDay(2001, 5, 3, BuddhistChronology.getInstance());
        assertEquals(expected,result);
    }

public void testMinusYears_int_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.minusYears(1);
        YearMonthDay expected = new YearMonthDay(2001, 5, 3, BuddhistChronology.getInstance());
        // removed other assertion
        
        result = test.minusYears(0);
        assertSame(test,result);
    }

public void testMinusMonths_int_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.minusMonths(1);
        YearMonthDay expected = new YearMonthDay(2002, 4, 3, BuddhistChronology.getInstance());
        assertEquals(expected,result);
    }

public void testMinusMonths_int_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.minusMonths(1);
        YearMonthDay expected = new YearMonthDay(2002, 4, 3, BuddhistChronology.getInstance());
        // removed other assertion
        
        result = test.minusMonths(0);
        assertSame(test,result);
    }

public void testMinusDays_int_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.minusDays(1);
        YearMonthDay expected = new YearMonthDay(2002, 5, 2, BuddhistChronology.getInstance());
        assertEquals(expected,result);
    }

public void testMinusDays_int_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 5, 3, BuddhistChronology.getInstance());
        YearMonthDay result = test.minusDays(1);
        YearMonthDay expected = new YearMonthDay(2002, 5, 2, BuddhistChronology.getInstance());
        // removed other assertion
        
        result = test.minusDays(0);
        assertSame(test,result);
    }

public void testToLocalDate_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_UTC);
        LocalDate test = base.toLocalDate();
        assertEquals(new LocalDate(2005,6,9,COPTIC_UTC),test);
    }

public void testToDateTimeAtMidnight_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        
        DateTime test = base.toDateTimeAtMidnight();
        check(base, 2005, 6, 9);
        assertEquals(new DateTime(2005,6,9,0,0,0,0,COPTIC_LONDON),test);
    }

public void testToDateTimeAtMidnight_Zone_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        
        DateTime test = base.toDateTimeAtMidnight(TOKYO);
        check(base, 2005, 6, 9);
        assertEquals(new DateTime(2005,6,9,0,0,0,0,COPTIC_TOKYO),test);
    }

public void testToDateTimeAtMidnight_nullZone_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        
        DateTime test = base.toDateTimeAtMidnight((DateTimeZone) null);
        check(base, 2005, 6, 9);
        assertEquals(new DateTime(2005,6,9,0,0,0,0,COPTIC_LONDON),test);
    }

public void testToDateTimeAtCurrentTime_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
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
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
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
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
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

public void testToDateTime_TOD_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        TimeOfDay tod = new TimeOfDay(12, 13, 14, 15, BUDDHIST_TOKYO);
        
        DateTime test = base.toDateTime(tod);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_LONDON);
        assertEquals(expected,test);
    }

public void testToDateTime_nullTOD_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        long now = new DateTime(2004, 5, 8, 12, 13, 14, 15, COPTIC_LONDON).getMillis();
        DateTimeUtils.setCurrentMillisFixed(now);
        
        DateTime test = base.toDateTime((TimeOfDay) null);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_LONDON);
        assertEquals(expected,test);
    }

public void testToDateTime_TOD_Zone_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        TimeOfDay tod = new TimeOfDay(12, 13, 14, 15, BUDDHIST_TOKYO);
        
        DateTime test = base.toDateTime(tod, TOKYO);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_TOKYO);
        assertEquals(expected,test);
    }

public void testToDateTime_TOD_nullZone_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        TimeOfDay tod = new TimeOfDay(12, 13, 14, 15, BUDDHIST_TOKYO);
        
        DateTime test = base.toDateTime(tod, null);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_LONDON);
        assertEquals(expected,test);
    }

public void testToDateTime_nullTOD_Zone_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        long now = new DateTime(2004, 5, 8, 12, 13, 14, 15, COPTIC_TOKYO).getMillis();
        DateTimeUtils.setCurrentMillisFixed(now);
        
        DateTime test = base.toDateTime((TimeOfDay) null, TOKYO);
        check(base, 2005, 6, 9);
        DateTime expected = new DateTime(2005, 6, 9, 12, 13, 14, 15, COPTIC_TOKYO);
        assertEquals(expected,test);
    }

public void testToDateMidnight_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        
        DateMidnight test = base.toDateMidnight();
        check(base, 2005, 6, 9);
        assertEquals(new DateMidnight(2005,6,9,COPTIC_LONDON),test);
    }

public void testToDateMidnight_Zone_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        
        DateMidnight test = base.toDateMidnight(TOKYO);
        check(base, 2005, 6, 9);
        assertEquals(new DateMidnight(2005,6,9,COPTIC_TOKYO),test);
    }

public void testToDateMidnight_nullZone_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
        
        DateMidnight test = base.toDateMidnight((DateTimeZone) null);
        check(base, 2005, 6, 9);
        assertEquals(new DateMidnight(2005,6,9,COPTIC_LONDON),test);
    }

public void testToDateTime_RI_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS);
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
        YearMonthDay base = new YearMonthDay(2005, 6, 9);
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
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval();
        check(base, 2005, 6, 9);
        DateTime start = base.toDateTime(TimeOfDay.MIDNIGHT);
        DateTime end = start.plus(Period.days(1));
        Interval expected = new Interval(start, end);
        assertEquals(expected,test);
    }

public void testToInterval_Zone_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval(TOKYO);
        check(base, 2005, 6, 9);
        DateTime start = base.toDateTime(TimeOfDay.MIDNIGHT, TOKYO);
        DateTime end = start.plus(Period.days(1));
        Interval expected = new Interval(start, end);
        assertEquals(expected,test);
    }

public void testToInterval_nullZone_1_oe() {
        YearMonthDay base = new YearMonthDay(2005, 6, 9, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval(null);
        check(base, 2005, 6, 9);
        DateTime start = base.toDateTime(TimeOfDay.MIDNIGHT, LONDON);
        DateTime end = start.plus(Period.days(1));
        Interval expected = new Interval(start, end);
        assertEquals(expected,test);
    }

public void testProperty_1_oe() {
        YearMonthDay test = new YearMonthDay(2005, 6, 9);
        assertEquals(test.year(),test.property(DateTimeFieldType.year()));
    }

public void testProperty_2_oe() {
        YearMonthDay test = new YearMonthDay(2005, 6, 9);
        // removed other assertion
        assertEquals(test.monthOfYear(),test.property(DateTimeFieldType.monthOfYear()));
    }

public void testProperty_3_oe() {
        YearMonthDay test = new YearMonthDay(2005, 6, 9);
        // removed other assertion
        // removed other assertion
        assertEquals(test.dayOfMonth(),test.property(DateTimeFieldType.dayOfMonth()));
    }

public void testSerialization_1_oe() throws Exception {
        YearMonthDay test = new YearMonthDay(1972, 6, 9, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        YearMonthDay result = (YearMonthDay) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

public void testSerialization_2_oe() throws Exception {
        YearMonthDay test = new YearMonthDay(1972, 6, 9, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        YearMonthDay result = (YearMonthDay) ois.readObject();
        ois.close();
        
        // removed other assertion
        assertTrue(Arrays.equals(test.getValues(),result.getValues()));
    }

public void testSerialization_3_oe() throws Exception {
        YearMonthDay test = new YearMonthDay(1972, 6, 9, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        YearMonthDay result = (YearMonthDay) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(test.getFields(),result.getFields()));
    }

public void testSerialization_4_oe() throws Exception {
        YearMonthDay test = new YearMonthDay(1972, 6, 9, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        YearMonthDay result = (YearMonthDay) ois.readObject();
        ois.close();
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology(),result.getChronology());
    }

public void testToString_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 6, 9);
        assertEquals("2002-06-09",test.toString());
    }

public void testToString_String_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 6, 9);
        assertEquals("2002 \ufffd\ufffd",test.toString("yyyy HH"));
    }

public void testToString_String_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 6, 9);
        // removed other assertion
        assertEquals("2002-06-09",test.toString((String)null));
    }

public void testToString_String_Locale_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 6, 9);
        assertEquals("\ufffd 9/6",test.toString("EEE d/M",Locale.ENGLISH));
    }

public void testToString_String_Locale_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 6, 9);
        // removed other assertion
        assertEquals("\ufffd 9/6",test.toString("EEE d/M",Locale.FRENCH));
    }

public void testToString_String_Locale_3_oe() {
        YearMonthDay test = new YearMonthDay(2002, 6, 9);
        // removed other assertion
        // removed other assertion
        assertEquals("2002-06-09",test.toString(null,Locale.ENGLISH));
    }

public void testToString_String_Locale_4_oe() {
        YearMonthDay test = new YearMonthDay(2002, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("\ufffd 9/6",test.toString("EEE d/M",null));
    }

public void testToString_String_Locale_5_oe() {
        YearMonthDay test = new YearMonthDay(2002, 6, 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2002-06-09",test.toString(null,null));
    }

public void testToString_DTFormatter_1_oe() {
        YearMonthDay test = new YearMonthDay(2002, 6, 9);
        assertEquals("2002 \ufffd\ufffd",test.toString(DateTimeFormat.forPattern("yyyy HH")));
    }

public void testToString_DTFormatter_2_oe() {
        YearMonthDay test = new YearMonthDay(2002, 6, 9);
        // removed other assertion
        assertEquals("2002-06-09",test.toString((DateTimeFormatter)null));
    }

}
