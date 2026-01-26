/*
 *  Copyright 2001-2005 Stephen Colebourne
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
package org.joda.time.field;

import java.util.Arrays;
import java.util.Locale;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.TimeOfDay;
import org.joda.time.chrono.ISOChronology;

/**
 * This class is a Junit unit test for PreciseDurationDateTimeField.
 *
 * @author Stephen Colebourne
 */
public class TestPreciseDurationDateTimeField_OE25Dev extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestPreciseDurationDateTimeField_OE25Dev_OE25Dev.class);
    }

    public TestPreciseDurationDateTimeField_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //------------------------------------------------------------------------
//    public abstract DurationField getDurationField();
//
//    public abstract DurationField getRangeDurationField();

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //------------------------------------------------------------------------

    //-----------------------------------------------------------------------
    static class MockPreciseDurationDateTimeField extends PreciseDurationDateTimeField {
        protected MockPreciseDurationDateTimeField() {
            super(DateTimeFieldType.secondOfMinute(),
                new MockCountingDurationField(DurationFieldType.seconds()));
        }
        protected MockPreciseDurationDateTimeField(DateTimeFieldType type, DurationField dur) {
            super(type, dur);
        }
        @Override
        public int get(long instant) {
            return (int) (instant / 60L);
        }
        @Override
        public DurationField getRangeDurationField() {
            return new MockCountingDurationField(DurationFieldType.minutes());
        }
        @Override
        public int getMaximumValue() {
            return 59;
        }
    }

    static class MockStandardBaseDateTimeField extends MockPreciseDurationDateTimeField {
        protected MockStandardBaseDateTimeField() {
            super();
        }
        @Override
        public DurationField getDurationField() {
            return ISOChronology.getInstanceUTC().seconds();
        }
        @Override
        public DurationField getRangeDurationField() {
            return ISOChronology.getInstanceUTC().minutes();
        }
    }

    //-----------------------------------------------------------------------
    static class MockCountingDurationField extends BaseDurationField {
        static int add_int = 0;
        static int add_long = 0;
        static int difference_long = 0;
        
        protected MockCountingDurationField(DurationFieldType type) {
            super(type);
        }
        @Override
        public boolean isPrecise() {
            return true;
        }
        @Override
        public long getUnitMillis() {
            return 60;
        }
        @Override
        public long getValueAsLong(long duration, long instant) {
            return 0;
        }
        @Override
        public long getMillis(int value, long instant) {
            return 0;
        }
        @Override
        public long getMillis(long value, long instant) {
            return 0;
        }
        @Override
        public long add(long instant, int value) {
            add_int++;
            return instant + (value * 60L);
        }
        @Override
        public long add(long instant, long value) {
            add_long++;
            return instant + (value * 60L);
        }
        @Override
        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            difference_long++;
            return 30;
        }
    }

    //-----------------------------------------------------------------------
    static class MockZeroDurationField extends BaseDurationField {
        protected MockZeroDurationField(DurationFieldType type) {
            super(type);
        }
        @Override
        public boolean isPrecise() {
            return true;
        }
        @Override
        public long getUnitMillis() {
            return 0;  // this is zero
        }
        @Override
        public long getValueAsLong(long duration, long instant) {
            return 0;
        }
        @Override
        public long getMillis(int value, long instant) {
            return 0;
        }
        @Override
        public long getMillis(long value, long instant) {
            return 0;
        }
        @Override
        public long add(long instant, int value) {
            return 0;
        }
        @Override
        public long add(long instant, long value) {
            return 0;
        }
        @Override
        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            return 0;
        }
    }

    //-----------------------------------------------------------------------
    static class MockImpreciseDurationField extends BaseDurationField {
        protected MockImpreciseDurationField(DurationFieldType type) {
            super(type);
        }
        @Override
        public boolean isPrecise() {
            return false;  // this is false
        }
        @Override
        public long getUnitMillis() {
            return 0;
        }
        @Override
        public long getValueAsLong(long duration, long instant) {
            return 0;
        }
        @Override
        public long getMillis(int value, long instant) {
            return 0;
        }
        @Override
        public long getMillis(long value, long instant) {
            return 0;
        }
        @Override
        public long add(long instant, int value) {
            return 0;
        }
        @Override
        public long add(long instant, long value) {
            return 0;
        }
        @Override
        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            return 0;
        }
    }

    public void test_constructor_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(DateTimeFieldType.secondOfMinute(), field.getType());
    }

    public void test_getType_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField(
            DateTimeFieldType.secondOfDay(), new MockCountingDurationField(DurationFieldType.minutes()));
        assertEquals(DateTimeFieldType.secondOfDay(), field.getType());
    }

    public void test_getName_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField(
            DateTimeFieldType.secondOfDay(), new MockCountingDurationField(DurationFieldType.minutes()));
        assertEquals("secondOfDay", field.getName());
    }

    public void test_toString_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField(
            DateTimeFieldType.secondOfDay(), new MockCountingDurationField(DurationFieldType.minutes()));
        assertEquals("DateTimeField[secondOfDay]", field.toString());
    }

    public void test_isSupported_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(true, field.isSupported());
    }

    public void test_isLenient_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(false, field.isLenient());
    }

    public void test_get_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0, field.get(0));
    }

    public void test_get_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(1, field.get(60));
    }

    public void test_get_3_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        assertEquals(2, field.get(123));
    }

    public void test_getAsText_long_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals("29", field.getAsText(60L * 29, Locale.ENGLISH));
    }

    public void test_getAsText_long_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals("29", field.getAsText(60L * 29, null));
    }

    public void test_getAsText_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals("29", field.getAsText(60L * 29));
    }

    public void test_getAsText_RP_int_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals("20", field.getAsText(new TimeOfDay(12, 30, 40, 50), 20, Locale.ENGLISH));
    }

    public void test_getAsText_RP_int_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals("20", field.getAsText(new TimeOfDay(12, 30, 40, 50), 20, null));
    }

    public void test_getAsText_RP_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals("40", field.getAsText(new TimeOfDay(12, 30, 40, 50), Locale.ENGLISH));
    }

    public void test_getAsText_RP_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals("40", field.getAsText(new TimeOfDay(12, 30, 40, 50), null));
    }

    public void test_getAsText_int_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals("80", field.getAsText(80, Locale.ENGLISH));
    }

    public void test_getAsText_int_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals("80", field.getAsText(80, null));
    }

    public void test_getAsShortText_long_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals("29", field.getAsShortText(60L * 29, Locale.ENGLISH));
    }

    public void test_getAsShortText_long_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals("29", field.getAsShortText(60L * 29, null));
    }

    public void test_getAsShortText_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals("29", field.getAsShortText(60L * 29));
    }

    public void test_getAsShortText_RP_int_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals("20", field.getAsShortText(new TimeOfDay(12, 30, 40, 50), 20, Locale.ENGLISH));
    }

    public void test_getAsShortText_RP_int_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals("20", field.getAsShortText(new TimeOfDay(12, 30, 40, 50), 20, null));
    }

    public void test_getAsShortText_RP_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals("40", field.getAsShortText(new TimeOfDay(12, 30, 40, 50), Locale.ENGLISH));
    }

    public void test_getAsShortText_RP_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals("40", field.getAsShortText(new TimeOfDay(12, 30, 40, 50), null));
    }

    public void test_getAsShortText_int_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals("80", field.getAsShortText(80, Locale.ENGLISH));
    }

    public void test_getAsShortText_int_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals("80", field.getAsShortText(80, null));
    }

    public void test_add_long_int_1_oe() {
        MockCountingDurationField.add_int = 0;
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(61, field.add(1L, 1));
    }

    public void test_add_long_int_2_oe() {
        MockCountingDurationField.add_int = 0;
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(1, MockCountingDurationField.add_int);
    }

    public void test_add_long_long_1_oe() {
        MockCountingDurationField.add_long = 0;
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(61, field.add(1L, 1L));
    }

    public void test_add_long_long_2_oe() {
        MockCountingDurationField.add_long = 0;
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(1, MockCountingDurationField.add_long);
    }

    public void test_add_RP_int_intarray_int_1_oe() {
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        BaseDateTimeField field = new MockStandardBaseDateTimeField();
        int[] result = field.add(new TimeOfDay(), 2, values, 0);
        assertEquals(true, Arrays.equals(expected, result));
    }

    public void test_add_RP_int_intarray_int_2_oe() {
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        BaseDateTimeField field = new MockStandardBaseDateTimeField();
        int[] result = field.add(new TimeOfDay(), 2, values, 0);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 31, 40};
        result = field.add(new TimeOfDay(), 2, values, 1);
        assertEquals(true, Arrays.equals(expected, result));
    }

    public void test_add_RP_int_intarray_int_3_oe() {
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        BaseDateTimeField field = new MockStandardBaseDateTimeField();
        int[] result = field.add(new TimeOfDay(), 2, values, 0);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 31, 40};
        result = field.add(new TimeOfDay(), 2, values, 1);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 21, 0, 40};
        result = field.add(new TimeOfDay(), 2, values, 30);
        assertEquals(true, Arrays.equals(expected, result));
    }

    public void test_add_RP_int_intarray_int_5_oe() {
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        BaseDateTimeField field = new MockStandardBaseDateTimeField();
        int[] result = field.add(new TimeOfDay(), 2, values, 0);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 31, 40};
        result = field.add(new TimeOfDay(), 2, values, 1);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 21, 0, 40};
        result = field.add(new TimeOfDay(), 2, values, 30);
        // removed other assertion
        
        values = new int[] {23, 59, 30, 40};
        try {
            field.add(new TimeOfDay(), 2, values, 30);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 29, 40};
        result = field.add(new TimeOfDay(), 2, values, -1);
        assertEquals(true, Arrays.equals(expected, result));
    }

    public void test_add_RP_int_intarray_int_6_oe() {
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        BaseDateTimeField field = new MockStandardBaseDateTimeField();
        int[] result = field.add(new TimeOfDay(), 2, values, 0);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 31, 40};
        result = field.add(new TimeOfDay(), 2, values, 1);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 21, 0, 40};
        result = field.add(new TimeOfDay(), 2, values, 30);
        // removed other assertion
        
        values = new int[] {23, 59, 30, 40};
        try {
            field.add(new TimeOfDay(), 2, values, 30);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 29, 40};
        result = field.add(new TimeOfDay(), 2, values, -1);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 19, 59, 40};
        result = field.add(new TimeOfDay(), 2, values, -31);
        assertEquals(true, Arrays.equals(expected, result));
    }

    public void test_addWrapField_long_int_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(29 * 60L, field.addWrapField(60L * 29, 0));
    }

    public void test_addWrapField_long_int_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(59 * 60L, field.addWrapField(60L * 29, 30));
    }

    public void test_addWrapField_long_int_3_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        assertEquals(0 * 60L, field.addWrapField(60L * 29, 31));
    }

    public void test_addWrapField_RP_int_intarray_int_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.addWrapField(new TimeOfDay(), 2, values, 0);
        assertEquals(true, Arrays.equals(result, expected));
    }

    public void test_addWrapField_RP_int_intarray_int_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.addWrapField(new TimeOfDay(), 2, values, 0);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 59, 40};
        result = field.addWrapField(new TimeOfDay(), 2, values, 29);
        assertEquals(true, Arrays.equals(result, expected));
    }

    public void test_addWrapField_RP_int_intarray_int_3_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.addWrapField(new TimeOfDay(), 2, values, 0);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 59, 40};
        result = field.addWrapField(new TimeOfDay(), 2, values, 29);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 0, 40};
        result = field.addWrapField(new TimeOfDay(), 2, values, 30);
        assertEquals(true, Arrays.equals(result, expected));
    }

    public void test_addWrapField_RP_int_intarray_int_4_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.addWrapField(new TimeOfDay(), 2, values, 0);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 59, 40};
        result = field.addWrapField(new TimeOfDay(), 2, values, 29);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 0, 40};
        result = field.addWrapField(new TimeOfDay(), 2, values, 30);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 1, 40};
        result = field.addWrapField(new TimeOfDay(), 2, values, 31);
        assertEquals(true, Arrays.equals(result, expected));
    }

    public void test_getDifference_long_long_1_oe() {
        MockCountingDurationField.difference_long = 0;
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(30, field.getDifference(0L, 0L));
    }

    public void test_getDifference_long_long_2_oe() {
        MockCountingDurationField.difference_long = 0;
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(1, MockCountingDurationField.difference_long);
    }

    public void test_getDifferenceAsLong_long_long_1_oe() {
        MockCountingDurationField.difference_long = 0;
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(30, field.getDifferenceAsLong(0L, 0L));
    }

    public void test_getDifferenceAsLong_long_long_2_oe() {
        MockCountingDurationField.difference_long = 0;
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(1, MockCountingDurationField.difference_long);
    }

    public void test_set_long_int_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0, field.set(120L, 0));
    }

    public void test_set_long_int_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(29 * 60, field.set(120L, 29));
    }

    public void test_set_RP_int_intarray_int_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.set(new TimeOfDay(), 2, values, 30);
        assertEquals(true, Arrays.equals(result, expected));
    }

    public void test_set_RP_int_intarray_int_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.set(new TimeOfDay(), 2, values, 30);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 29, 40};
        result = field.set(new TimeOfDay(), 2, values, 29);
        assertEquals(true, Arrays.equals(result, expected));
    }

    public void test_set_RP_int_intarray_int_4_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.set(new TimeOfDay(), 2, values, 30);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 29, 40};
        result = field.set(new TimeOfDay(), 2, values, 29);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 30, 40};
        try {
            field.set(new TimeOfDay(), 2, values, 60);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        assertEquals(true, Arrays.equals(values, expected));
    }

    public void test_set_RP_int_intarray_int_6_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.set(new TimeOfDay(), 2, values, 30);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 29, 40};
        result = field.set(new TimeOfDay(), 2, values, 29);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 30, 40};
        try {
            field.set(new TimeOfDay(), 2, values, 60);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 30, 40};
        try {
            field.set(new TimeOfDay(), 2, values, -1);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        assertEquals(true, Arrays.equals(values, expected));
    }

    public void test_set_long_String_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0, field.set(0L, "0", null));
    }

    public void test_set_long_String_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(29 * 60, field.set(0L, "29", Locale.ENGLISH));
    }

    public void test_set_long_String_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0, field.set(0L, "0"));
    }

    public void test_set_long_String_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(29 * 60, field.set(0L, "29"));
    }

    public void test_set_RP_int_intarray_String_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.set(new TimeOfDay(), 2, values, "30", null);
        assertEquals(true, Arrays.equals(result, expected));
    }

    public void test_set_RP_int_intarray_String_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.set(new TimeOfDay(), 2, values, "30", null);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 29, 40};
        result = field.set(new TimeOfDay(), 2, values, "29", Locale.ENGLISH);
        assertEquals(true, Arrays.equals(result, expected));
    }

    public void test_set_RP_int_intarray_String_Locale_4_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.set(new TimeOfDay(), 2, values, "30", null);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 29, 40};
        result = field.set(new TimeOfDay(), 2, values, "29", Locale.ENGLISH);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 30, 40};
        try {
            field.set(new TimeOfDay(), 2, values, "60", null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        assertEquals(true, Arrays.equals(values, expected));
    }

    public void test_set_RP_int_intarray_String_Locale_6_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        int[] values = new int[] {10, 20, 30, 40};
        int[] expected = new int[] {10, 20, 30, 40};
        int[] result = field.set(new TimeOfDay(), 2, values, "30", null);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 29, 40};
        result = field.set(new TimeOfDay(), 2, values, "29", Locale.ENGLISH);
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 30, 40};
        try {
            field.set(new TimeOfDay(), 2, values, "60", null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        // removed other assertion
        
        values = new int[] {10, 20, 30, 40};
        expected = new int[] {10, 20, 30, 40};
        try {
            field.set(new TimeOfDay(), 2, values, "-1", null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        assertEquals(true, Arrays.equals(values, expected));
    }

    public void test_convertText_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0, field.convertText("0", null));
    }

    public void test_convertText_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(29, field.convertText("29", null));
    }

    public void test_isLeap_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(false, field.isLeap(0L));
    }

    public void test_getLeapAmount_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0, field.getLeapAmount(0L));
    }

    public void test_getLeapDurationField_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(null, field.getLeapDurationField());
    }

    public void test_getMinimumValue_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0, field.getMinimumValue());
    }

    public void test_getMinimumValue_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0, field.getMinimumValue(0L));
    }

    public void test_getMinimumValue_RP_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0, field.getMinimumValue(new TimeOfDay()));
    }

    public void test_getMinimumValue_RP_intarray_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0, field.getMinimumValue(new TimeOfDay(), new int[4]));
    }

    public void test_getMaximumValue_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(59, field.getMaximumValue());
    }

    public void test_getMaximumValue_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(59, field.getMaximumValue(0L));
    }

    public void test_getMaximumValue_RP_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(59, field.getMaximumValue(new TimeOfDay()));
    }

    public void test_getMaximumValue_RP_intarray_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(59, field.getMaximumValue(new TimeOfDay(), new int[4]));
    }

    public void test_getMaximumTextLength_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(2, field.getMaximumTextLength(Locale.ENGLISH));
    }

    public void test_getMaximumTextLength_Locale_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion

        field = new MockPreciseDurationDateTimeField() {
            @Override
            public int getMaximumValue() {
                return 5;
            }
        };
        assertEquals(1, field.getMaximumTextLength(Locale.ENGLISH));
    }

    public void test_getMaximumTextLength_Locale_3_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion

        field = new MockPreciseDurationDateTimeField() {
            @Override
            public int getMaximumValue() {
                return 5;
            }
        };
        // removed other assertion
        
        field = new MockPreciseDurationDateTimeField() {
            @Override
            public int getMaximumValue() {
                return 555;
            }
        };
        assertEquals(3, field.getMaximumTextLength(Locale.ENGLISH));
    }

    public void test_getMaximumTextLength_Locale_4_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion

        field = new MockPreciseDurationDateTimeField() {
            @Override
            public int getMaximumValue() {
                return 5;
            }
        };
        // removed other assertion
        
        field = new MockPreciseDurationDateTimeField() {
            @Override
            public int getMaximumValue() {
                return 555;
            }
        };
        // removed other assertion
        
        field = new MockPreciseDurationDateTimeField() {
            @Override
            public int getMaximumValue() {
                return 5555;
            }
        };
        assertEquals(4, field.getMaximumTextLength(Locale.ENGLISH));
    }

    public void test_getMaximumTextLength_Locale_5_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion

        field = new MockPreciseDurationDateTimeField() {
            @Override
            public int getMaximumValue() {
                return 5;
            }
        };
        // removed other assertion
        
        field = new MockPreciseDurationDateTimeField() {
            @Override
            public int getMaximumValue() {
                return 555;
            }
        };
        // removed other assertion
        
        field = new MockPreciseDurationDateTimeField() {
            @Override
            public int getMaximumValue() {
                return 5555;
            }
        };
        // removed other assertion
        
        field = new MockPreciseDurationDateTimeField() {
            @Override
            public int getMaximumValue() {
                return -1;
            }
        };
        assertEquals(2, field.getMaximumTextLength(Locale.ENGLISH));
    }

    public void test_getMaximumShortTextLength_Locale_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(2, field.getMaximumShortTextLength(Locale.ENGLISH));
    }

    public void test_roundFloor_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(-120L, field.roundFloor(-61L));
    }

    public void test_roundFloor_long_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(-60L, field.roundFloor(-60L));
    }

    public void test_roundFloor_long_3_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        assertEquals(-60L, field.roundFloor(-59L));
    }

    public void test_roundFloor_long_4_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-60L, field.roundFloor(-1L));
    }

    public void test_roundFloor_long_5_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.roundFloor(0L));
    }

    public void test_roundFloor_long_6_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.roundFloor(1L));
    }

    public void test_roundFloor_long_7_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.roundFloor(29L));
    }

    public void test_roundFloor_long_8_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.roundFloor(30L));
    }

    public void test_roundFloor_long_9_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.roundFloor(31L));
    }

    public void test_roundFloor_long_10_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundFloor(60L));
    }

    public void test_roundCeiling_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(-60L, field.roundCeiling(-61L));
    }

    public void test_roundCeiling_long_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(-60L, field.roundCeiling(-60L));
    }

    public void test_roundCeiling_long_3_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.roundCeiling(-59L));
    }

    public void test_roundCeiling_long_4_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.roundCeiling(-1L));
    }

    public void test_roundCeiling_long_5_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.roundCeiling(0L));
    }

    public void test_roundCeiling_long_6_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundCeiling(1L));
    }

    public void test_roundCeiling_long_7_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundCeiling(29L));
    }

    public void test_roundCeiling_long_8_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundCeiling(30L));
    }

    public void test_roundCeiling_long_9_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundCeiling(31L));
    }

    public void test_roundCeiling_long_10_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundCeiling(60L));
    }

    public void test_roundHalfFloor_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0L, field.roundHalfFloor(0L));
    }

    public void test_roundHalfFloor_long_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(0L, field.roundHalfFloor(29L));
    }

    public void test_roundHalfFloor_long_3_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.roundHalfFloor(30L));
    }

    public void test_roundHalfFloor_long_4_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundHalfFloor(31L));
    }

    public void test_roundHalfFloor_long_5_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundHalfFloor(60L));
    }

    public void test_roundHalfCeiling_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0L, field.roundHalfCeiling(0L));
    }

    public void test_roundHalfCeiling_long_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(0L, field.roundHalfCeiling(29L));
    }

    public void test_roundHalfCeiling_long_3_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundHalfCeiling(30L));
    }

    public void test_roundHalfCeiling_long_4_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundHalfCeiling(31L));
    }

    public void test_roundHalfCeiling_long_5_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundHalfCeiling(60L));
    }

    public void test_roundHalfEven_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0L, field.roundHalfEven(0L));
    }

    public void test_roundHalfEven_long_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(0L, field.roundHalfEven(29L));
    }

    public void test_roundHalfEven_long_3_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.roundHalfEven(30L));
    }

    public void test_roundHalfEven_long_4_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundHalfEven(31L));
    }

    public void test_roundHalfEven_long_5_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundHalfEven(60L));
    }

    public void test_roundHalfEven_long_6_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, field.roundHalfEven(89L));
    }

    public void test_roundHalfEven_long_7_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(120L, field.roundHalfEven(90L));
    }

    public void test_roundHalfEven_long_8_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(120L, field.roundHalfEven(91L));
    }

    public void test_remainder_long_1_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        assertEquals(0L, field.remainder(0L));
    }

    public void test_remainder_long_2_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        assertEquals(29L, field.remainder(29L));
    }

    public void test_remainder_long_3_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        assertEquals(30L, field.remainder(30L));
    }

    public void test_remainder_long_4_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(31L, field.remainder(31L));
    }

    public void test_remainder_long_5_oe() {
        BaseDateTimeField field = new MockPreciseDurationDateTimeField();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, field.remainder(60L));
    }

}
