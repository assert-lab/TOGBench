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
 * This class is a Junit unit test for YearMonth.
 *
 * @author Stephen Colebourne
 */
public class TestYearMonth_Basics_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
    private static final Chronology COPTIC_LONDON = CopticChronology.getInstance(LONDON);
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
        return new TestSuite(TestYearMonth_Basics_OE25Dev.class);
    }

    public TestYearMonth_Basics_OE25Dev(String name) {
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
    public void testGet() {
        YearMonth test = new YearMonth();
        assertEquals(1970,test.get(DateTimeFieldType.year()));
        assertEquals(6,test.get(DateTimeFieldType.monthOfYear()));
        try {
            test.get(null);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.get(DateTimeFieldType.dayOfMonth());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testSize() {
        YearMonth test = new YearMonth();
        assertEquals(2,test.size());
    }

    public void testGetFieldType() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        assertSame(DateTimeFieldType.year(),test.getFieldType(0));
        assertSame(DateTimeFieldType.monthOfYear(),test.getFieldType(1));
        try {
            test.getFieldType(-1);
        } catch (IndexOutOfBoundsException ex) {}
        try {
            test.getFieldType(2);
        } catch (IndexOutOfBoundsException ex) {}
    }

    public void testGetFieldTypes() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        assertEquals(2,fields.length);
        assertSame(DateTimeFieldType.year(),fields[0]);
        assertSame(DateTimeFieldType.monthOfYear(),fields[1]);
        assertNotSame(test.getFieldTypes(),test.getFieldTypes());
    }

    public void testGetField() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        assertSame(COPTIC_UTC.year(),test.getField(0));
        assertSame(COPTIC_UTC.monthOfYear(),test.getField(1));
        try {
            test.getField(-1);
        } catch (IndexOutOfBoundsException ex) {}
        try {
            test.getField(2);
        } catch (IndexOutOfBoundsException ex) {}
    }

    public void testGetFields() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        assertEquals(2,fields.length);
        assertSame(COPTIC_UTC.year(),fields[0]);
        assertSame(COPTIC_UTC.monthOfYear(),fields[1]);
        assertNotSame(test.getFields(),test.getFields());
    }

    public void testGetValue() {
        YearMonth test = new YearMonth();
        assertEquals(1970,test.getValue(0));
        assertEquals(6,test.getValue(1));
        try {
            test.getValue(-1);
        } catch (IndexOutOfBoundsException ex) {}
        try {
            test.getValue(2);
        } catch (IndexOutOfBoundsException ex) {}
    }

    public void testGetValues() {
        YearMonth test = new YearMonth();
        int[] values = test.getValues();
        assertEquals(2,values.length);
        assertEquals(1970,values[0]);
        assertEquals(6,values[1]);
        assertNotSame(test.getValues(),test.getValues());
    }

    public void testIsSupported() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        assertEquals(true,test.isSupported(DateTimeFieldType.year()));
        assertEquals(true,test.isSupported(DateTimeFieldType.monthOfYear()));
        assertEquals(false,test.isSupported(DateTimeFieldType.dayOfMonth()));
        assertEquals(false,test.isSupported(DateTimeFieldType.hourOfDay()));
    }

    public void testEqualsHashCode() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        assertEquals(true,test1.equals(test2));
        assertEquals(true,test2.equals(test1));
        assertEquals(true,test1.equals(test1));
        assertEquals(true,test2.equals(test2));
        assertEquals(true,test1.hashCode()== test2.hashCode());
        assertEquals(true,test1.hashCode()== test1.hashCode());
        assertEquals(true,test2.hashCode()== test2.hashCode());
        
        YearMonth test3 = new YearMonth(1971, 6);
        assertEquals(false,test1.equals(test3));
        assertEquals(false,test2.equals(test3));
        assertEquals(false,test3.equals(test1));
        assertEquals(false,test3.equals(test2));
        assertEquals(false,test1.hashCode()== test3.hashCode());
        assertEquals(false,test2.hashCode()== test3.hashCode());
        
        assertEquals(false,test1.equals("Hello"));
        assertEquals(true,test1.equals(new MockYM()));
        assertEquals(false,test1.equals(MockPartial.EMPTY_INSTANCE));
    }
    
    class MockYM extends MockPartial {
        @Override
        public Chronology getChronology() {
            return COPTIC_UTC;
        }
        @Override
        public DateTimeField[] getFields() {
            return new DateTimeField[] {
                COPTIC_UTC.year(),
                COPTIC_UTC.monthOfYear(),
            };
        }
        @Override
        public int[] getValues() {
            return new int[] {1970, 6};
        }
    }

    //-----------------------------------------------------------------------
    public void testCompareTo() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        assertEquals(0,test1.compareTo(test1a));
        assertEquals(0,test1a.compareTo(test1));
        assertEquals(0,test1.compareTo(test1));
        assertEquals(0,test1a.compareTo(test1a));
        
        YearMonth test2 = new YearMonth(2005, 7);
        assertEquals(-1,test1.compareTo(test2));
        assertEquals(+1,test2.compareTo(test1));
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
        assertEquals(-1,test1.compareTo(test3));
        assertEquals(+1,test3.compareTo(test1));
        assertEquals(0,test3.compareTo(test2));
        
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6};
        Partial p = new Partial(types, values);
        assertEquals(0,test1.compareTo(p));
        try {
            test1.compareTo(null);
            fail();
        } catch (NullPointerException ex) {}
        try {
            test1.compareTo(new LocalTime());
            fail();
        } catch (ClassCastException ex) {}
        Partial partial = new Partial()
            .with(DateTimeFieldType.centuryOfEra(), 1)
            .with(DateTimeFieldType.halfdayOfDay(), 0)
            .with(DateTimeFieldType.dayOfMonth(), 9);
        try {
            new YearMonth(1970, 6).compareTo(partial);
            fail();
        } catch (ClassCastException ex) {}
    }
    
    //-----------------------------------------------------------------------
    public void testIsEqual_YM() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        assertEquals(true,test1.isEqual(test1a));
        assertEquals(true,test1a.isEqual(test1));
        assertEquals(true,test1.isEqual(test1));
        assertEquals(true,test1a.isEqual(test1a));
        
        YearMonth test2 = new YearMonth(2005, 7);
        assertEquals(false,test1.isEqual(test2));
        assertEquals(false,test2.isEqual(test1));
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
        assertEquals(false,test1.isEqual(test3));
        assertEquals(false,test3.isEqual(test1));
        assertEquals(true,test3.isEqual(test2));
        
        try {
            new YearMonth(2005, 7).isEqual(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }
    
    //-----------------------------------------------------------------------
    public void testIsBefore_YM() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        assertEquals(false,test1.isBefore(test1a));
        assertEquals(false,test1a.isBefore(test1));
        assertEquals(false,test1.isBefore(test1));
        assertEquals(false,test1a.isBefore(test1a));
        
        YearMonth test2 = new YearMonth(2005, 7);
        assertEquals(true,test1.isBefore(test2));
        assertEquals(false,test2.isBefore(test1));
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
        assertEquals(true,test1.isBefore(test3));
        assertEquals(false,test3.isBefore(test1));
        assertEquals(false,test3.isBefore(test2));
        
        try {
            new YearMonth(2005, 7).isBefore(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }
    
    //-----------------------------------------------------------------------
    public void testIsAfter_YM() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        assertEquals(false,test1.isAfter(test1a));
        assertEquals(false,test1a.isAfter(test1));
        assertEquals(false,test1.isAfter(test1));
        assertEquals(false,test1a.isAfter(test1a));
        
        YearMonth test2 = new YearMonth(2005, 7);
        assertEquals(false,test1.isAfter(test2));
        assertEquals(true,test2.isAfter(test1));
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
        assertEquals(false,test1.isAfter(test3));
        assertEquals(true,test3.isAfter(test1));
        assertEquals(false,test3.isAfter(test2));
        
        try {
            new YearMonth(2005, 7).isAfter(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }
    
    //-----------------------------------------------------------------------
    public void testWithChronologyRetainFields_Chrono() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS);
        YearMonth test = base.withChronologyRetainFields(BUDDHIST_TOKYO);
        check(base, 2005, 6);
        assertEquals(COPTIC_UTC,base.getChronology());
        check(test, 2005, 6);
        assertEquals(BUDDHIST_UTC,test.getChronology());
    }

    public void testWithChronologyRetainFields_sameChrono() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS);
        YearMonth test = base.withChronologyRetainFields(COPTIC_TOKYO);
        assertSame(base,test);
    }

    public void testWithChronologyRetainFields_nullChrono() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS);
        YearMonth test = base.withChronologyRetainFields(null);
        check(base, 2005, 6);
        assertEquals(COPTIC_UTC,base.getChronology());
        check(test, 2005, 6);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testWithChronologyRetainFields_invalidInNewChrono() {
        YearMonth base = new YearMonth(2005, 13, COPTIC_UTC);
        try {
            base.withChronologyRetainFields(ISO_UTC);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void testWithField() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(new YearMonth(2004,6),test);
        assertEquals(new YearMonth(2006,6),result);
    }

    public void testWithField_nullField() {
        YearMonth test = new YearMonth(2004, 6);
        try {
            test.withField(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithField_unknownField() {
        YearMonth test = new YearMonth(2004, 6);
        try {
            test.withField(DateTimeFieldType.hourOfDay(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithField_same() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withField(DateTimeFieldType.year(), 2004);
        assertEquals(new YearMonth(2004,6),test);
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testWithFieldAdded() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(new YearMonth(2004,6),test);
        assertEquals(new YearMonth(2010,6),result);
    }

    public void testWithFieldAdded_nullField_zero() {
        YearMonth test = new YearMonth(2004, 6);
        try {
            test.withFieldAdded(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_nullField_nonZero() {
        YearMonth test = new YearMonth(2004, 6);
        try {
            test.withFieldAdded(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded_zero() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertSame(test,result);
    }

    public void testWithFieldAdded_unknownField() {
        YearMonth test = new YearMonth(2004, 6);
        try {
            test.withFieldAdded(DurationFieldType.hours(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testPlus_RP() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        YearMonth expected = new YearMonth(2003, 7, BuddhistChronology.getInstance());
        assertEquals(expected,result);
        
        result = test.plus((ReadablePeriod) null);
        assertSame(test,result);
    }

    public void testPlusYears_int() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.plusYears(1);
        YearMonth expected = new YearMonth(2003, 5, BuddhistChronology.getInstance());
        assertEquals(expected,result);
        
        result = test.plusYears(0);
        assertSame(test,result);
    }

    public void testPlusMonths_int() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.plusMonths(1);
        YearMonth expected = new YearMonth(2002, 6, BuddhistChronology.getInstance());
        assertEquals(expected,result);
        
        result = test.plusMonths(0);
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testMinus_RP() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        YearMonth expected = new YearMonth(2001, 4, BuddhistChronology.getInstance());
        assertEquals(expected,result);
        
        result = test.minus((ReadablePeriod) null);
        assertSame(test,result);
    }

    public void testMinusYears_int() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.minusYears(1);
        YearMonth expected = new YearMonth(2001, 5, BuddhistChronology.getInstance());
        assertEquals(expected,result);
        
        result = test.minusYears(0);
        assertSame(test,result);
    }

    public void testMinusMonths_int() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.minusMonths(1);
        YearMonth expected = new YearMonth(2002, 4, BuddhistChronology.getInstance());
        assertEquals(expected,result);
        
        result = test.minusMonths(0);
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testToLocalDate() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_UTC);
        LocalDate test = base.toLocalDate(9);
        assertEquals(new LocalDate(2005,6,9,COPTIC_UTC),test);
        try {
            base.toLocalDate(0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testToDateTime_RI() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS);
        DateTime dt = new DateTime(2002, 1, 3, 4, 5, 6, 7);
        
        DateTime test = base.toDateTime(dt);
        check(base, 2005, 6);
        DateTime expected = dt;
        expected = expected.year().setCopy(2005);
        expected = expected.monthOfYear().setCopy(6);
        assertEquals(expected,test);
    }

    public void testToDateTime_nullRI() {
        YearMonth base = new YearMonth(2005, 6);
        DateTime dt = new DateTime(2002, 1, 3, 4, 5, 6, 7);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        DateTime test = base.toDateTime((ReadableInstant) null);
        check(base, 2005, 6);
        DateTime expected = dt;
        expected = expected.year().setCopy(2005);
        expected = expected.monthOfYear().setCopy(6);
        assertEquals(expected,test);
    }

    //-----------------------------------------------------------------------
    public void testToInterval() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval();
        check(base, 2005, 6);
        DateTime start = new DateTime(2005, 6, 1, 0, 0, COPTIC_LONDON);
        DateTime end = new DateTime(2005, 7, 1, 0, 0, COPTIC_LONDON);
        Interval expected = new Interval(start, end);
        assertEquals(expected,test);
    }

    //-----------------------------------------------------------------------
    public void testToInterval_Zone() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval(TOKYO);
        check(base, 2005, 6);
        DateTime start = new DateTime(2005, 6, 1, 0, 0, COPTIC_TOKYO);
        DateTime end = new DateTime(2005, 7, 1, 0, 0, COPTIC_TOKYO);
        Interval expected = new Interval(start, end);
        assertEquals(expected,test);
    }

    public void testToInterval_nullZone() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval(null);
        check(base, 2005, 6);
        DateTime start = new DateTime(2005, 6, 1, 0, 0, COPTIC_LONDON);
        DateTime end = new DateTime(2005, 7, 1, 0, 0, COPTIC_LONDON);
        Interval expected = new Interval(start, end);
        assertEquals(expected,test);
    }

    //-----------------------------------------------------------------------
    public void testWithers() {
        YearMonth test = new YearMonth(1970, 6);
        check(test.withYear(2000), 2000, 6);
        check(test.withMonthOfYear(2), 1970, 2);
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
    public void testProperty() {
        YearMonth test = new YearMonth(2005, 6);
        assertEquals(test.year(),test.property(DateTimeFieldType.year()));
        assertEquals(test.monthOfYear(),test.property(DateTimeFieldType.monthOfYear()));
        try {
            test.property(DateTimeFieldType.millisOfDay());
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.property(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testSerialization() throws Exception {
        YearMonth test = new YearMonth(1972, 6, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        YearMonth result = (YearMonth) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
        assertTrue(Arrays.equals(test.getValues(),result.getValues()));
        assertTrue(Arrays.equals(test.getFields(),result.getFields()));
        assertEquals(test.getChronology(),result.getChronology());
    }

    //-----------------------------------------------------------------------
    public void testToString() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals("2002-06",test.toString());
    }

    //-----------------------------------------------------------------------
    public void testToString_String() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals("2002 \ufffd\ufffd",test.toString("yyyy HH"));
        assertEquals("2002-06",test.toString((String)null));
    }

    //-----------------------------------------------------------------------
    public void testToString_String_Locale() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals("\ufffd \ufffd/6",test.toString("EEE d/M",Locale.ENGLISH));
        assertEquals("\ufffd \ufffd/6",test.toString("EEE d/M",Locale.FRENCH));
        assertEquals("2002-06",test.toString(null,Locale.ENGLISH));
        assertEquals("\ufffd \ufffd/6",test.toString("EEE d/M",null));
        assertEquals("2002-06",test.toString(null,null));
    }

    //-----------------------------------------------------------------------
    public void testToString_DTFormatter() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals("2002 \ufffd\ufffd",test.toString(DateTimeFormat.forPattern("yyyy HH")));
        assertEquals("2002-06",test.toString((DateTimeFormatter)null));
    }

    //-----------------------------------------------------------------------
    private void check(YearMonth test, int year, int month) {
        assertEquals(year,test.getYear());
        assertEquals(month,test.getMonthOfYear());
    }

    public void testGet_1_oe() {
        YearMonth test = new YearMonth();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testGet_2_oe() {
        YearMonth test = new YearMonth();
// incorrect assertion         assertEquals(1, test.get());
    }

    public void testSize_1_oe() {
        YearMonth test = new YearMonth();
        assertEquals(2, test.size());
    }

    public void testGetFieldType_1_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
// incorrect assertion         assertEquals("year", YearMonth.year().getName());
    }

    public void testGetFieldType_2_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
// incorrect assertion         assertEquals("MonthOfYear", test.getFieldTypeName());
    }

    public void testGetFieldTypes_1_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        assertNotNull(fields);
    }

    public void testGetFieldTypes_2_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        assertEquals(DateTimeFieldType.YEAR, test.year());
    }

    public void testGetFieldTypes_3_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
// incorrect assertion         assertNotNull(YearMonth.monthOfYear());
    }

    public void testGetFieldTypes_4_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        DateTimeFieldType[] fields = test.getFieldTypes();
        assertNotNull(fields);
    }

    public void testGetField_1_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        assertEquals("year", test.year().getName());
    }

    public void testGetField_2_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        assertEquals("MonthOfYear", test.monthOfYear().getName());
    }

    public void testGetFields_1_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        assertNotNull(fields);
    }

    public void testGetFields_2_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        assertEquals("2013", test.year().getAsString());
    }

    public void testGetFields_3_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        assertEquals("MonthOfYear", test.monthOfYear().getName());
    }

    public void testGetFields_4_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
        DateTimeField[] fields = test.getFields();
        assertNotNull(fields);
    }

    public void testGetValue_1_oe() {
        YearMonth test = new YearMonth();
// incorrect assertion         assertEquals(1, test.getValue());
    }

    public void testGetValue_2_oe() {
        YearMonth test = new YearMonth();
// incorrect assertion         assertEquals(1, test.getValue());
    }

    public void testGetValues_1_oe() {
        YearMonth test = new YearMonth();
        int[] values = test.getValues();
// incorrect assertion         assertEquals(1, test.getMaximumLength());
    }

    public void testGetValues_2_oe() {
        YearMonth test = new YearMonth();
        int[] values = test.getValues();
        assertEquals("2013-07-01", test.toString());
    }

    public void testGetValues_3_oe() {
        YearMonth test = new YearMonth();
        int[] values = test.getValues();
        assertEquals("2013-07-01_00:00:00.000000-+0100:+0100", test.toString());
    }

    public void testGetValues_4_oe() {
        YearMonth test = new YearMonth();
        int[] values = test.getValues();
// incorrect assertion         assertEquals(1, test.getMaximumLength());
    }

    public void testIsSupported_1_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
// incorrect assertion         assertEquals(true, test.isSupported());
    }

    public void testIsSupported_2_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
// incorrect assertion         assertEquals(true, test.isSupported());
    }

    public void testIsSupported_3_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
// incorrect assertion         assertEquals(true, test.isSupported());
    }

    public void testIsSupported_4_oe() {
        YearMonth test = new YearMonth(COPTIC_PARIS);
// incorrect assertion         assertEquals(true, test.isSupported());
    }

    public void testEqualsHashCode_1_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_2_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_5_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_6_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_7_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_8_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        
        YearMonth test3 = new YearMonth(1971, 6);
        assertEquals(false, test1.equals(test2));
    }

    public void testEqualsHashCode_9_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        
        YearMonth test3 = new YearMonth(1971, 6);
        assertEquals(false, test1.equals(test2));
    }

    public void testEqualsHashCode_10_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        
        YearMonth test3 = new YearMonth(1971, 6);
        assertEquals(1970, test1.getYear());
    }

    public void testEqualsHashCode_11_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        
        YearMonth test3 = new YearMonth(1971, 6);
        assertEquals(false, test1.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        
        YearMonth test3 = new YearMonth(1971, 6);
        assertEquals(false, test1.equals(test2));
    }

    public void testEqualsHashCode_13_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        
        YearMonth test3 = new YearMonth(1971, 6);
        assertEquals(1970, test1.getYear());
    }

    public void testEqualsHashCode_14_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        
        YearMonth test3 = new YearMonth(1971, 6);
        
        assertEquals(false, test1.equals(test2));
    }

    public void testEqualsHashCode_15_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        
        YearMonth test3 = new YearMonth(1971, 6);
        
        assertEquals(false, test1.equals(test2));
    }

    public void testEqualsHashCode_16_oe() {
        YearMonth test1 = new YearMonth(1970, 6, COPTIC_PARIS);
        YearMonth test2 = new YearMonth(1970, 6, COPTIC_PARIS);
        
        YearMonth test3 = new YearMonth(1971, 6);
        
        assertEquals(false, test1.equals(test2));
    }

    public void testCompareTo_1_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_2_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_3_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_4_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_5_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_6_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_7_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_8_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_9_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testIsEqual_YM_2_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_YM_4_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_YM_5_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_YM_6_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_YM_7_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_YM_8_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_YM_9_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsBefore_YM_1_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
// incorrect assertion         assertEquals(false, test1.isBefore(1372709999999L));
    }

    public void testIsBefore_YM_2_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
// incorrect assertion         assertEquals(false, test1.isBefore(1372709999999L));
    }

    public void testIsBefore_YM_3_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
// incorrect assertion         assertEquals(false, test1.isBefore(1372709999999L));
    }

    public void testIsBefore_YM_4_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
// incorrect assertion         assertEquals(false, test1.isBefore(1372709999999L));
    }

    public void testIsBefore_YM_5_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
// incorrect assertion         assertEquals(false, test1.isBefore(test1a.getEndMillis()));
    }

    public void testIsBefore_YM_6_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
// incorrect assertion         assertEquals(true, test1.isBefore(test1a.getEndMillis()));
    }

    public void testIsBefore_YM_7_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
// incorrect assertion         assertEquals(false, test1.isBefore(test1a.getTimeInMillis()));
    }

    public void testIsBefore_YM_8_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
// incorrect assertion         assertEquals(false, test1.isBefore(0L));
    }

    public void testIsBefore_YM_9_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
// incorrect assertion         assertEquals(false, test1.isBefore(test1a.getTimeInMillis()));
    }

    public void testIsAfter_YM_1_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
// incorrect assertion         assertEquals(false, test1.isAfter(1372709999999L));
    }

    public void testIsAfter_YM_2_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
// incorrect assertion         assertEquals(false, test1.isAfter(1372709999999L));
    }

    public void testIsAfter_YM_3_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
// incorrect assertion         assertEquals(false, test1.isAfter(1372709999999L));
    }

    public void testIsAfter_YM_4_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
// incorrect assertion         assertEquals(false, test1.isAfter(1372709999999L));
    }

    public void testIsAfter_YM_5_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
// incorrect assertion         assertEquals(false, test1.isAfter(test1a.getStartMillis()));
    }

    public void testIsAfter_YM_6_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
// incorrect assertion         assertEquals(false, test1.isAfter(test1a.getStartMillis()));
    }

    public void testIsAfter_YM_7_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
// incorrect assertion         assertEquals(false, test1.isAfter(test1a.getStartMillis()));
    }

    public void testIsAfter_YM_8_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
// incorrect assertion         assertEquals(false, test1.isAfter(test1a.getStartMillis()));
    }

    public void testIsAfter_YM_9_oe() {
        YearMonth test1 = new YearMonth(2005, 6);
        YearMonth test1a = new YearMonth(2005, 6);
        
        YearMonth test2 = new YearMonth(2005, 7);
        
        YearMonth test3 = new YearMonth(2005, 7, GregorianChronology.getInstanceUTC());
// incorrect assertion         assertEquals(false, test1.isAfter(test1a.getStartMillis()));
    }

    public void testWithChronologyRetainFields_Chrono_1_oe() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS);
        YearMonth test = base.withChronologyRetainFields(BUDDHIST_TOKYO);
        check(base, 2005, 6);
        assertEquals(ISOChronology.getInstanceUTC(), test.getChronology());
    }

    public void testWithChronologyRetainFields_Chrono_2_oe() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS);
        YearMonth test = base.withChronologyRetainFields(BUDDHIST_TOKYO);
        check(base, 2005, 6);
        check(test, 2005, 6);
        assertEquals(ISOChronology.getInstanceUTC(), test.getChronology());
    }

    public void testWithChronologyRetainFields_sameChrono_1_oe() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS);
        YearMonth test = base.withChronologyRetainFields(COPTIC_TOKYO);
        assertEquals("2005-06-01/06:00:00.000+01:00[Europe/Paris]", base.toString());
    }

    public void testWithChronologyRetainFields_nullChrono_1_oe() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS);
        YearMonth test = base.withChronologyRetainFields(null);
        check(base, 2005, 6);
        assertEquals(ISOChronology.getInstanceUTC(), test.getChronology());
    }

    public void testWithChronologyRetainFields_nullChrono_2_oe() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS);
        YearMonth test = base.withChronologyRetainFields(null);
        check(base, 2005, 6);
        check(test, 2005, 6);
        assertEquals(ISOChronology.getInstanceUTC(), test.getChronology());
    }

    public void testWithField_1_oe() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(2006, result.getYear());
    }

    public void testWithField_2_oe() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withField(DateTimeFieldType.year(), 2006);
        
        assertEquals(2006, result.getYear());
    }

    public void testWithField_same_1_oe() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withField(DateTimeFieldType.year(), 2004);
        assertEquals(2004, test.getYear());
    }

    public void testWithField_same_2_oe() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withField(DateTimeFieldType.year(), 2004);
        assertEquals(2004, test.getYear());
    }

    public void testWithFieldAdded_1_oe() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(2010, result.getYear());
    }

    public void testWithFieldAdded_2_oe() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(2010, result.getYear());
    }

    public void testWithFieldAdded_zero_1_oe() {
        YearMonth test = new YearMonth(2004, 6);
        YearMonth result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertEquals(2004, test.getYear());
    }

    public void testPlus_RP_1_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        YearMonth expected = new YearMonth(2003, 7, BuddhistChronology.getInstance());
        assertNotSame(test, result);
    }

    public void testPlus_RP_2_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.plus(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        YearMonth expected = new YearMonth(2003, 7, BuddhistChronology.getInstance());
        
        result = test.plus((ReadablePeriod) null);
        assertNotNull(result);
    }

    public void testPlusYears_int_1_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.plusYears(1);
        YearMonth expected = new YearMonth(2003, 5, BuddhistChronology.getInstance());
        assertNotSame(test, result);
    }

    public void testPlusYears_int_2_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.plusYears(1);
        YearMonth expected = new YearMonth(2003, 5, BuddhistChronology.getInstance());
        
        result = test.plusYears(0);
        assertNotNull(result);
    }

    public void testPlusMonths_int_1_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.plusMonths(1);
        YearMonth expected = new YearMonth(2002, 6, BuddhistChronology.getInstance());
        assertNotNull(result);
    }

    public void testPlusMonths_int_2_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.plusMonths(1);
        YearMonth expected = new YearMonth(2002, 6, BuddhistChronology.getInstance());
        
        result = test.plusMonths(0);
        assertNotNull(result);
    }

    public void testMinus_RP_1_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        YearMonth expected = new YearMonth(2001, 4, BuddhistChronology.getInstance());
        assertNotSame(test, result);
    }

    public void testMinus_RP_2_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.minus(new Period(1, 1, 1, 1, 1, 1, 1, 1));
        YearMonth expected = new YearMonth(2001, 4, BuddhistChronology.getInstance());
        
        result = test.minus((ReadablePeriod) null);
        assertNotNull(result);
    }

    public void testMinusYears_int_1_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.minusYears(1);
        YearMonth expected = new YearMonth(2001, 5, BuddhistChronology.getInstance());
        assertNotSame(test, result);
    }

    public void testMinusYears_int_2_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.minusYears(1);
        YearMonth expected = new YearMonth(2001, 5, BuddhistChronology.getInstance());
        
        result = test.minusYears(0);
        assertNotNull(result);
    }

    public void testMinusMonths_int_1_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.minusMonths(1);
        YearMonth expected = new YearMonth(2002, 4, BuddhistChronology.getInstance());
        assertNotSame(test, result);
    }

    public void testMinusMonths_int_2_oe() {
        YearMonth test = new YearMonth(2002, 5, BuddhistChronology.getInstance());
        YearMonth result = test.minusMonths(1);
        YearMonth expected = new YearMonth(2002, 4, BuddhistChronology.getInstance());
        
        result = test.minusMonths(0);
        assertNotNull(result);
    }

    public void testToLocalDate_1_oe() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_UTC);
        LocalDate test = base.toLocalDate(9);
        assertEquals(9, test.getDayOfMonth());
    }

    public void testToDateTime_RI_1_oe() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS);
        DateTime dt = new DateTime(2002, 1, 3, 4, 5, 6, 7);
        
        DateTime test = base.toDateTime(dt);
        check(base, 2005, 6);
        DateTime expected = dt;
        expected = expected.year().setCopy(2005);
        expected = expected.monthOfYear().setCopy(6);
        assertEquals(2002, test.getYear());
    }

    public void testToDateTime_nullRI_1_oe() {
        YearMonth base = new YearMonth(2005, 6);
        DateTime dt = new DateTime(2002, 1, 3, 4, 5, 6, 7);
        DateTimeUtils.setCurrentMillisFixed(dt.getMillis());
        
        DateTime test = base.toDateTime((ReadableInstant) null);
        check(base, 2005, 6);
        DateTime expected = dt;
        expected = expected.year().setCopy(2005);
        expected = expected.monthOfYear().setCopy(6);
        assertEquals(2005, test.getYear());
    }

    public void testToInterval_1_oe() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval();
        check(base, 2005, 6);
        DateTime start = new DateTime(2005, 6, 1, 0, 0, COPTIC_LONDON);
        DateTime end = new DateTime(2005, 7, 1, 0, 0, COPTIC_LONDON);
        Interval expected = new Interval(start, end);
        assertNotNull(test);
    }

    public void testToInterval_Zone_1_oe() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval(TOKYO);
        check(base, 2005, 6);
        DateTime start = new DateTime(2005, 6, 1, 0, 0, COPTIC_TOKYO);
        DateTime end = new DateTime(2005, 7, 1, 0, 0, COPTIC_TOKYO);
        Interval expected = new Interval(start, end);
        assertEquals(expected, test);
    }

    public void testToInterval_nullZone_1_oe() {
        YearMonth base = new YearMonth(2005, 6, COPTIC_PARIS); // PARIS irrelevant
        Interval test = base.toInterval(null);
        check(base, 2005, 6);
        DateTime start = new DateTime(2005, 6, 1, 0, 0, COPTIC_LONDON);
        DateTime end = new DateTime(2005, 7, 1, 0, 0, COPTIC_LONDON);
        Interval expected = new Interval(start, end);
        assertNotNull(test);
    }

    public void testProperty_1_oe() {
        YearMonth test = new YearMonth(2005, 6);
// incorrect assertion         assertEquals("2005", test.year().getString());
    }

    public void testProperty_2_oe() {
        YearMonth test = new YearMonth(2005, 6);
        assertEquals("MonthOfYear", test.monthOfYear().getName());
    }

    public void testSerialization_1_oe() throws Exception {
        YearMonth test = new YearMonth(1972, 6, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        YearMonth result = (YearMonth) ois.readObject();
        ois.close();
        
        assertNotNull(result);
    }

    public void testSerialization_2_oe() throws Exception {
        YearMonth test = new YearMonth(1972, 6, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        YearMonth result = (YearMonth) ois.readObject();
        ois.close();
        
        assertNotNull(result);
    }

    public void testSerialization_3_oe() throws Exception {
        YearMonth test = new YearMonth(1972, 6, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        YearMonth result = (YearMonth) ois.readObject();
        ois.close();
        
        assertNotNull(result);
    }

    public void testSerialization_4_oe() throws Exception {
        YearMonth test = new YearMonth(1972, 6, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        YearMonth result = (YearMonth) ois.readObject();
        ois.close();
        
        assertEquals(ISOChronology.getInstanceUTC(), result.getChronology());
    }

    public void testToString_1_oe() {
        YearMonth test = new YearMonth(2002, 6);
        assertNotNull(test);
    }

    public void testToString_String_1_oe() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals(2002, test.getYear());
    }

    public void testToString_String_2_oe() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals(2002, test.getYear());
    }

    public void testToString_String_Locale_1_oe() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals(2002, test.getYear());
    }

    public void testToString_String_Locale_2_oe() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals(2002, test.getYear());
    }

    public void testToString_String_Locale_3_oe() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals(2002, test.getYear());
    }

    public void testToString_String_Locale_4_oe() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals(2002, test.getYear());
    }

    public void testToString_String_Locale_5_oe() {
        YearMonth test = new YearMonth(2002, 6);
        assertNotNull(test);
    }

    public void testToString_DTFormatter_1_oe() {
        YearMonth test = new YearMonth(2002, 6);
        assertNotNull(test);
    }

    public void testToString_DTFormatter_2_oe() {
        YearMonth test = new YearMonth(2002, 6);
        assertEquals(2002, test.getYear());
    }

}
