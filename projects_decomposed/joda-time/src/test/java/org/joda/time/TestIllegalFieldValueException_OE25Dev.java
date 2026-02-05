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

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.SkipDateTimeField;

/**
 * Tests IllegalFieldValueException by triggering it from other methods.
 *
 * @author Brian S O'Neill
 */
public class TestIllegalFieldValueException_OE25Dev extends TestCase {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestIllegalFieldValueException_OE25Dev.class);
    }

    public TestIllegalFieldValueException_OE25Dev(String name) {
        super(name);
    }

    // Test extra constructors not currently called by anything

    public void testVerifyValueBounds_2_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.monthOfYear(),e.getDateTimeFieldType());
    }
    }

    public void testVerifyValueBounds_3_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testVerifyValueBounds_4_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("monthOfYear",e.getFieldName());
    }
    }

    public void testVerifyValueBounds_5_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(-5),e.getIllegalNumberValue());
    }
    }

    public void testVerifyValueBounds_6_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testVerifyValueBounds_7_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("-5",e.getIllegalValueAsString());
    }
    }

    public void testVerifyValueBounds_8_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(1),e.getLowerBound());
    }
    }

    public void testVerifyValueBounds_9_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(31),e.getUpperBound());
    }
    }

    public void testVerifyValueBounds_11_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.hourOfDay(),e.getDateTimeFieldType());
    }
    }

    public void testVerifyValueBounds_12_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testVerifyValueBounds_13_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("hourOfDay",e.getFieldName());
    }
    }

    public void testVerifyValueBounds_14_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(27),e.getIllegalNumberValue());
    }
    }

    public void testVerifyValueBounds_15_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testVerifyValueBounds_16_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("27",e.getIllegalValueAsString());
    }
    }

    public void testVerifyValueBounds_17_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(0),e.getLowerBound());
    }
    }

    public void testVerifyValueBounds_18_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(23),e.getUpperBound());
    }
    }

    public void testVerifyValueBounds_20_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds("foo", 1, 2, 3);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(null,e.getDateTimeFieldType());
    }
    }

    public void testVerifyValueBounds_21_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds("foo", 1, 2, 3);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testVerifyValueBounds_22_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds("foo", 1, 2, 3);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("foo",e.getFieldName());
    }
    }

    public void testVerifyValueBounds_23_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds("foo", 1, 2, 3);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(1),e.getIllegalNumberValue());
    }
    }

    public void testVerifyValueBounds_24_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds("foo", 1, 2, 3);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testVerifyValueBounds_25_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds("foo", 1, 2, 3);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("1",e.getIllegalValueAsString());
    }
    }

    public void testVerifyValueBounds_26_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds("foo", 1, 2, 3);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(2),e.getLowerBound());
    }
    }

    public void testVerifyValueBounds_27_oe() {
        try {
            FieldUtils.verifyValueBounds(ISOChronology.getInstance().monthOfYear(), -5, 1, 31);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), 27, 0, 23);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            FieldUtils.verifyValueBounds("foo", 1, 2, 3);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(3),e.getUpperBound());
    }
    }

    public void testSkipDateTimeField_2_oe() {
        DateTimeField field = new SkipDateTimeField
            (ISOChronology.getInstanceUTC(), ISOChronology.getInstanceUTC().year(), 1970);
        try {
            field.set(0, 1970);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.year(),e.getDateTimeFieldType());
    }
    }

    public void testSkipDateTimeField_3_oe() {
        DateTimeField field = new SkipDateTimeField
            (ISOChronology.getInstanceUTC(), ISOChronology.getInstanceUTC().year(), 1970);
        try {
            field.set(0, 1970);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testSkipDateTimeField_4_oe() {
        DateTimeField field = new SkipDateTimeField
            (ISOChronology.getInstanceUTC(), ISOChronology.getInstanceUTC().year(), 1970);
        try {
            field.set(0, 1970);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("year",e.getFieldName());
    }
    }

    public void testSkipDateTimeField_5_oe() {
        DateTimeField field = new SkipDateTimeField
            (ISOChronology.getInstanceUTC(), ISOChronology.getInstanceUTC().year(), 1970);
        try {
            field.set(0, 1970);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(1970),e.getIllegalNumberValue());
    }
    }

    public void testSkipDateTimeField_6_oe() {
        DateTimeField field = new SkipDateTimeField
            (ISOChronology.getInstanceUTC(), ISOChronology.getInstanceUTC().year(), 1970);
        try {
            field.set(0, 1970);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testSkipDateTimeField_7_oe() {
        DateTimeField field = new SkipDateTimeField
            (ISOChronology.getInstanceUTC(), ISOChronology.getInstanceUTC().year(), 1970);
        try {
            field.set(0, 1970);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("1970",e.getIllegalValueAsString());
    }
    }

    public void testSkipDateTimeField_8_oe() {
        DateTimeField field = new SkipDateTimeField
            (ISOChronology.getInstanceUTC(), ISOChronology.getInstanceUTC().year(), 1970);
        try {
            field.set(0, 1970);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testSkipDateTimeField_9_oe() {
        DateTimeField field = new SkipDateTimeField
            (ISOChronology.getInstanceUTC(), ISOChronology.getInstanceUTC().year(), 1970);
        try {
            field.set(0, 1970);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testSetText_2_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.year(),e.getDateTimeFieldType());
    }
    }

    public void testSetText_3_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testSetText_4_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("year",e.getFieldName());
    }
    }

    public void testSetText_5_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalNumberValue());
    }
    }

    public void testSetText_6_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testSetText_7_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("null",e.getIllegalValueAsString());
    }
    }

    public void testSetText_8_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testSetText_9_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testSetText_11_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.year(),e.getDateTimeFieldType());
    }
    }

    public void testSetText_12_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testSetText_13_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("year",e.getFieldName());
    }
    }

    public void testSetText_14_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalNumberValue());
    }
    }

    public void testSetText_15_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("nineteen seventy",e.getIllegalStringValue());
    }
    }

    public void testSetText_16_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("nineteen seventy",e.getIllegalValueAsString());
    }
    }

    public void testSetText_17_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testSetText_18_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testSetText_20_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.era(),e.getDateTimeFieldType());
    }
    }

    public void testSetText_21_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testSetText_22_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("era",e.getFieldName());
    }
    }

    public void testSetText_23_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalNumberValue());
    }
    }

    public void testSetText_24_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("long ago",e.getIllegalStringValue());
    }
    }

    public void testSetText_25_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("long ago",e.getIllegalValueAsString());
    }
    }

    public void testSetText_26_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testSetText_27_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testSetText_29_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.monthOfYear(),e.getDateTimeFieldType());
    }
    }

    public void testSetText_30_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testSetText_31_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("monthOfYear",e.getFieldName());
    }
    }

    public void testSetText_32_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalNumberValue());
    }
    }

    public void testSetText_33_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("spring",e.getIllegalStringValue());
    }
    }

    public void testSetText_34_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("spring",e.getIllegalValueAsString());
    }
    }

    public void testSetText_35_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testSetText_36_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testSetText_38_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.dayOfWeek(),e.getDateTimeFieldType());
    }
    }

    public void testSetText_39_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testSetText_40_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("dayOfWeek",e.getFieldName());
    }
    }

    public void testSetText_41_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalNumberValue());
    }
    }

    public void testSetText_42_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("yesterday",e.getIllegalStringValue());
    }
    }

    public void testSetText_43_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("yesterday",e.getIllegalValueAsString());
    }
    }

    public void testSetText_44_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testSetText_45_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testSetText_47_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().halfdayOfDay().set(0, "morning", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.halfdayOfDay(),e.getDateTimeFieldType());
    }
    }

    public void testSetText_48_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().halfdayOfDay().set(0, "morning", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testSetText_49_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().halfdayOfDay().set(0, "morning", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("halfdayOfDay",e.getFieldName());
    }
    }

    public void testSetText_50_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().halfdayOfDay().set(0, "morning", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalNumberValue());
    }
    }

    public void testSetText_51_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().halfdayOfDay().set(0, "morning", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("morning",e.getIllegalStringValue());
    }
    }

    public void testSetText_52_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().halfdayOfDay().set(0, "morning", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("morning",e.getIllegalValueAsString());
    }
    }

    public void testSetText_53_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().halfdayOfDay().set(0, "morning", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testSetText_54_oe() {
        try {
            ISOChronology.getInstanceUTC().year().set(0, null, java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().year().set(0, "nineteen seventy", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().era().set(0, "long ago", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().monthOfYear().set(0, "spring", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().dayOfWeek().set(0, "yesterday", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            ISOChronology.getInstanceUTC().halfdayOfDay().set(0, "morning", java.util.Locale.US);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testZoneTransition_2_oe() {
        DateTime dt = new DateTime
            (2005, 4, 3, 1, 0, 0, 0, DateTimeZone.forID("America/Los_Angeles"));
        try {
            dt.hourOfDay().setCopy(2);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.hourOfDay(),e.getDateTimeFieldType());
    }
    }

    public void testZoneTransition_3_oe() {
        DateTime dt = new DateTime
            (2005, 4, 3, 1, 0, 0, 0, DateTimeZone.forID("America/Los_Angeles"));
        try {
            dt.hourOfDay().setCopy(2);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testZoneTransition_4_oe() {
        DateTime dt = new DateTime
            (2005, 4, 3, 1, 0, 0, 0, DateTimeZone.forID("America/Los_Angeles"));
        try {
            dt.hourOfDay().setCopy(2);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("hourOfDay",e.getFieldName());
    }
    }

    public void testZoneTransition_5_oe() {
        DateTime dt = new DateTime
            (2005, 4, 3, 1, 0, 0, 0, DateTimeZone.forID("America/Los_Angeles"));
        try {
            dt.hourOfDay().setCopy(2);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(2),e.getIllegalNumberValue());
    }
    }

    public void testZoneTransition_6_oe() {
        DateTime dt = new DateTime
            (2005, 4, 3, 1, 0, 0, 0, DateTimeZone.forID("America/Los_Angeles"));
        try {
            dt.hourOfDay().setCopy(2);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testZoneTransition_7_oe() {
        DateTime dt = new DateTime
            (2005, 4, 3, 1, 0, 0, 0, DateTimeZone.forID("America/Los_Angeles"));
        try {
            dt.hourOfDay().setCopy(2);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("2",e.getIllegalValueAsString());
    }
    }

    public void testZoneTransition_8_oe() {
        DateTime dt = new DateTime
            (2005, 4, 3, 1, 0, 0, 0, DateTimeZone.forID("America/Los_Angeles"));
        try {
            dt.hourOfDay().setCopy(2);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testZoneTransition_9_oe() {
        DateTime dt = new DateTime
            (2005, 4, 3, 1, 0, 0, 0, DateTimeZone.forID("America/Los_Angeles"));
        try {
            dt.hourOfDay().setCopy(2);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testJulianYearZero_2_oe() {
        DateTime dt = new DateTime(JulianChronology.getInstanceUTC());
        try {
            dt.year().setCopy(0);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.year(),e.getDateTimeFieldType());
    }
    }

    public void testJulianYearZero_3_oe() {
        DateTime dt = new DateTime(JulianChronology.getInstanceUTC());
        try {
            dt.year().setCopy(0);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testJulianYearZero_4_oe() {
        DateTime dt = new DateTime(JulianChronology.getInstanceUTC());
        try {
            dt.year().setCopy(0);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("year",e.getFieldName());
    }
    }

    public void testJulianYearZero_5_oe() {
        DateTime dt = new DateTime(JulianChronology.getInstanceUTC());
        try {
            dt.year().setCopy(0);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(0),e.getIllegalNumberValue());
    }
    }

    public void testJulianYearZero_6_oe() {
        DateTime dt = new DateTime(JulianChronology.getInstanceUTC());
        try {
            dt.year().setCopy(0);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testJulianYearZero_7_oe() {
        DateTime dt = new DateTime(JulianChronology.getInstanceUTC());
        try {
            dt.year().setCopy(0);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("0",e.getIllegalValueAsString());
    }
    }

    public void testJulianYearZero_8_oe() {
        DateTime dt = new DateTime(JulianChronology.getInstanceUTC());
        try {
            dt.year().setCopy(0);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testJulianYearZero_9_oe() {
        DateTime dt = new DateTime(JulianChronology.getInstanceUTC());
        try {
            dt.year().setCopy(0);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testGJCutover_2_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.dayOfMonth(),e.getDateTimeFieldType());
    }
    }

    public void testGJCutover_3_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testGJCutover_4_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("dayOfMonth",e.getFieldName());
    }
    }

    public void testGJCutover_5_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(5),e.getIllegalNumberValue());
    }
    }

    public void testGJCutover_6_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testGJCutover_7_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("5",e.getIllegalValueAsString());
    }
    }

    public void testGJCutover_8_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testGJCutover_9_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testGJCutover_11_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        dt = new DateTime("1582-10-15", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(14);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.dayOfMonth(),e.getDateTimeFieldType());
    }
    }

    public void testGJCutover_12_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        dt = new DateTime("1582-10-15", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(14);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testGJCutover_13_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        dt = new DateTime("1582-10-15", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(14);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("dayOfMonth",e.getFieldName());
    }
    }

    public void testGJCutover_14_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        dt = new DateTime("1582-10-15", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(14);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(14),e.getIllegalNumberValue());
    }
    }

    public void testGJCutover_15_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        dt = new DateTime("1582-10-15", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(14);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testGJCutover_16_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        dt = new DateTime("1582-10-15", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(14);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("14",e.getIllegalValueAsString());
    }
    }

    public void testGJCutover_17_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        dt = new DateTime("1582-10-15", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(14);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testGJCutover_18_oe() {
        DateTime dt = new DateTime("1582-10-04", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(5);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        dt = new DateTime("1582-10-15", GJChronology.getInstanceUTC());
        try {
            dt.dayOfMonth().setCopy(14);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testReadablePartialValidate_2_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.monthOfYear(),e.getDateTimeFieldType());
    }
    }

    public void testReadablePartialValidate_3_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testReadablePartialValidate_4_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("monthOfYear",e.getFieldName());
    }
    }

    public void testReadablePartialValidate_5_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(-5),e.getIllegalNumberValue());
    }
    }

    public void testReadablePartialValidate_6_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testReadablePartialValidate_7_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("-5",e.getIllegalValueAsString());
    }
    }

    public void testReadablePartialValidate_8_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(1),e.getLowerBound());
    }
    }

    public void testReadablePartialValidate_9_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getUpperBound());
    }
    }

    public void testReadablePartialValidate_11_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.monthOfYear(),e.getDateTimeFieldType());
    }
    }

    public void testReadablePartialValidate_12_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testReadablePartialValidate_13_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("monthOfYear",e.getFieldName());
    }
    }

    public void testReadablePartialValidate_14_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(500),e.getIllegalNumberValue());
    }
    }

    public void testReadablePartialValidate_15_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testReadablePartialValidate_16_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("500",e.getIllegalValueAsString());
    }
    }

    public void testReadablePartialValidate_17_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testReadablePartialValidate_18_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(12),e.getUpperBound());
    }
    }

    public void testReadablePartialValidate_20_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 2, 30);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            assertEquals(DateTimeFieldType.dayOfMonth(),e.getDateTimeFieldType());
    }
    }

    public void testReadablePartialValidate_21_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 2, 30);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            assertEquals(null,e.getDurationFieldType());
    }
    }

    public void testReadablePartialValidate_22_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 2, 30);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            assertEquals("dayOfMonth",e.getFieldName());
    }
    }

    public void testReadablePartialValidate_23_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 2, 30);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(30),e.getIllegalNumberValue());
    }
    }

    public void testReadablePartialValidate_24_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 2, 30);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getIllegalStringValue());
    }
    }

    public void testReadablePartialValidate_25_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 2, 30);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("30",e.getIllegalValueAsString());
    }
    }

    public void testReadablePartialValidate_26_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 2, 30);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(null,e.getLowerBound());
    }
    }

    public void testReadablePartialValidate_27_oe() {
        try {
            new YearMonthDay(1970, -5, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 500, 1);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
            new YearMonthDay(1970, 2, 30);
            // removed other assertion
        } catch (IllegalFieldValueException e) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(new Integer(28),e.getUpperBound());
    }
    }

    public void testOtherConstructors_1_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        assertEquals(null,e.getDateTimeFieldType());
    }

    public void testOtherConstructors_2_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        assertEquals(DurationFieldType.days(),e.getDurationFieldType());
    }

    public void testOtherConstructors_3_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        assertEquals("days",e.getFieldName());
    }

    public void testOtherConstructors_4_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new Integer(1),e.getIllegalNumberValue());
    }

    public void testOtherConstructors_5_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,e.getIllegalStringValue());
    }

    public void testOtherConstructors_6_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1",e.getIllegalValueAsString());
    }

    public void testOtherConstructors_7_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new Integer(2),e.getLowerBound());
    }

    public void testOtherConstructors_8_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new Integer(3),e.getUpperBound());
    }

    public void testOtherConstructors_9_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        assertEquals(null,e.getDateTimeFieldType());
    }

    public void testOtherConstructors_10_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        assertEquals(DurationFieldType.months(),e.getDurationFieldType());
    }

    public void testOtherConstructors_11_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        assertEquals("months",e.getFieldName());
    }

    public void testOtherConstructors_12_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,e.getIllegalNumberValue());
    }

    public void testOtherConstructors_13_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("five",e.getIllegalStringValue());
    }

    public void testOtherConstructors_14_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("five",e.getIllegalValueAsString());
    }

    public void testOtherConstructors_15_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,e.getLowerBound());
    }

    public void testOtherConstructors_16_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,e.getUpperBound());
    }

    public void testOtherConstructors_17_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException("months", "five");
        assertEquals(null,e.getDateTimeFieldType());
    }

    public void testOtherConstructors_18_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException("months", "five");
        // removed other assertion
        assertEquals(null,e.getDurationFieldType());
    }

    public void testOtherConstructors_19_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException("months", "five");
        // removed other assertion
        // removed other assertion
        assertEquals("months",e.getFieldName());
    }

    public void testOtherConstructors_20_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException("months", "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,e.getIllegalNumberValue());
    }

    public void testOtherConstructors_21_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException("months", "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("five",e.getIllegalStringValue());
    }

    public void testOtherConstructors_22_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException("months", "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("five",e.getIllegalValueAsString());
    }

    public void testOtherConstructors_23_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException("months", "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,e.getLowerBound());
    }

    public void testOtherConstructors_24_oe() {
        IllegalFieldValueException e = new IllegalFieldValueException
            (DurationFieldType.days(), new Integer(1), new Integer(2), new Integer(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException(DurationFieldType.months(), "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        e = new IllegalFieldValueException("months", "five");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,e.getUpperBound());
    }

}
