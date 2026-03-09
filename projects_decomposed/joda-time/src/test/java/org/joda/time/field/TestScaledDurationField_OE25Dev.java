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
package org.joda.time.field;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.chrono.ISOChronology;

/**
 * This class is a Junit unit test for PreciseDurationField.
 *
 * @author Stephen Colebourne
 */
public class TestScaledDurationField_OE25Dev extends TestCase {
    
    private static final long LONG_INTEGER_MAX = Integer.MAX_VALUE;
    private static final int INTEGER_MAX = Integer.MAX_VALUE;
    private static final long LONG_MAX = Long.MAX_VALUE;
    
    private ScaledDurationField iField;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestScaledDurationField_OE25Dev.class);
    }

    public TestScaledDurationField_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DurationField base = MillisDurationField.INSTANCE;
        iField = new ScaledDurationField(base, DurationFieldType.minutes(), 90);
    }

    @Override
    protected void tearDown() throws Exception {
        iField = null;
    }

    //-----------------------------------------------------------------------
    public void test_constructor() {
        try {
            new ScaledDurationField(null, DurationFieldType.minutes(), 10);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new ScaledDurationField(MillisDurationField.INSTANCE, null, 10);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void test_getScalar() {
        assertEquals(90,iField.getScalar());
    }

    //-----------------------------------------------------------------------
    public void test_getType() {
        assertEquals(DurationFieldType.minutes(),iField.getType());
    }

    public void test_getName() {
        assertEquals("minutes",iField.getName());
    }
    
    public void test_isSupported() {
        assertEquals(true,iField.isSupported());
    }

    public void test_isPrecise() {
        assertEquals(true,iField.isPrecise());
    }

    public void test_getUnitMillis() {
        assertEquals(90,iField.getUnitMillis());
    }

    public void test_toString() {
        assertEquals("DurationField[minutes]",iField.toString());
    }

    //-----------------------------------------------------------------------
    public void test_getValue_long() {
        assertEquals(0,iField.getValue(0L));
        assertEquals(12345678 / 90,iField.getValue(12345678L));
        assertEquals(-1234 / 90,iField.getValue(-1234L));
        assertEquals(INTEGER_MAX / 90,iField.getValue(LONG_INTEGER_MAX));
        try {
            iField.getValue(LONG_INTEGER_MAX + 1L);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void test_getValueAsLong_long() {
        assertEquals(0L,iField.getValueAsLong(0L));
        assertEquals(12345678L / 90,iField.getValueAsLong(12345678L));
        assertEquals(-1234 / 90L,iField.getValueAsLong(-1234L));
        assertEquals(LONG_INTEGER_MAX + 1L,iField.getValueAsLong(LONG_INTEGER_MAX * 90L + 90L));
    }

    public void test_getValue_long_long() {
        assertEquals(0,iField.getValue(0L,567L));
        assertEquals(12345678 / 90,iField.getValue(12345678L,567L));
        assertEquals(-1234 / 90,iField.getValue(-1234L,567L));
        assertEquals(INTEGER_MAX / 90,iField.getValue(LONG_INTEGER_MAX,567L));
        try {
            iField.getValue(LONG_INTEGER_MAX + 1L, 567L);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void test_getValueAsLong_long_long() {
        assertEquals(0L,iField.getValueAsLong(0L,567L));
        assertEquals(12345678 / 90L,iField.getValueAsLong(12345678L,567L));
        assertEquals(-1234 / 90L,iField.getValueAsLong(-1234L,567L));
        assertEquals(LONG_INTEGER_MAX + 1L,iField.getValueAsLong(LONG_INTEGER_MAX * 90L + 90L,567L));
    }

    //-----------------------------------------------------------------------
    public void test_getMillis_int() {
        assertEquals(0,iField.getMillis(0));
        assertEquals(1234L * 90L,iField.getMillis(1234));
        assertEquals(-1234L * 90L,iField.getMillis(-1234));
        assertEquals(LONG_INTEGER_MAX * 90L,iField.getMillis(INTEGER_MAX));
    }

    public void test_getMillis_long() {
        assertEquals(0L,iField.getMillis(0L));
        assertEquals(1234L * 90L,iField.getMillis(1234L));
        assertEquals(-1234L * 90L,iField.getMillis(-1234L));
        try {
            iField.getMillis(LONG_MAX);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void test_getMillis_int_long() {
        assertEquals(0L,iField.getMillis(0,567L));
        assertEquals(1234L * 90L,iField.getMillis(1234,567L));
        assertEquals(-1234L * 90L,iField.getMillis(-1234,567L));
        assertEquals(LONG_INTEGER_MAX * 90L,iField.getMillis(INTEGER_MAX,567L));
    }

    public void test_getMillis_long_long() {
        assertEquals(0L,iField.getMillis(0L,567L));
        assertEquals(1234L * 90L,iField.getMillis(1234L,567L));
        assertEquals(-1234L * 90L,iField.getMillis(-1234L,567L));
        try {
            iField.getMillis(LONG_MAX, 567L);
            fail();
        } catch (ArithmeticException ex) {}
    }

    //-----------------------------------------------------------------------
    public void test_add_long_int() {
        assertEquals(567L,iField.add(567L,0));
        assertEquals(567L + 1234L * 90L,iField.add(567L,1234));
        assertEquals(567L - 1234L * 90L,iField.add(567L,-1234));
        try {
            iField.add(LONG_MAX, 1);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void test_add_long_long() {
        assertEquals(567L,iField.add(567L,0L));
        assertEquals(567L + 1234L * 90L,iField.add(567L,1234L));
        assertEquals(567L - 1234L * 90L,iField.add(567L,-1234L));
        try {
            iField.add(LONG_MAX, 1L);
            fail();
        } catch (ArithmeticException ex) {}
        try {
            iField.add(1L, LONG_MAX);
            fail();
        } catch (ArithmeticException ex) {}
    }

    //-----------------------------------------------------------------------
    public void test_getDifference_long_int() {
        assertEquals(0,iField.getDifference(1L,0L));
        assertEquals(567,iField.getDifference(567L * 90L,0L));
        assertEquals(567 - 1234,iField.getDifference(567L * 90L,1234L * 90L));
        assertEquals(567 + 1234,iField.getDifference(567L * 90L,-1234L * 90L));
        try {
            iField.getDifference(LONG_MAX, -1L);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void test_getDifferenceAsLong_long_long() {
        assertEquals(0L,iField.getDifferenceAsLong(1L,0L));
        assertEquals(567L,iField.getDifferenceAsLong(567L * 90L,0L));
        assertEquals(567L - 1234L,iField.getDifferenceAsLong(567L * 90L,1234L * 90L));
        assertEquals(567L + 1234L,iField.getDifferenceAsLong(567L * 90L,-1234L * 90L));
        try {
            iField.getDifferenceAsLong(LONG_MAX, -1L);
            fail();
        } catch (ArithmeticException ex) {}
    }

    //-----------------------------------------------------------------------
    public void test_equals() {
        assertEquals(true,iField.equals(iField));
        assertEquals(false,iField.equals(ISOChronology.getInstance().minutes()));
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        assertEquals(false,iField.equals(dummy));
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        assertEquals(true,iField.equals(dummy));
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.millis(), 90);
        assertEquals(false,iField.equals(dummy));
        assertEquals(false,iField.equals(""));
        assertEquals(false,iField.equals(null));
    }

    public void test_hashCode() {
        assertEquals(iField.hashCode(),iField.hashCode());
        assertEquals(false,iField.hashCode()== ISOChronology.getInstance().minutes().hashCode());
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        assertEquals(false,iField.hashCode()== dummy.hashCode());
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        assertEquals(true,iField.hashCode()== dummy.hashCode());
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.millis(), 90);
        assertEquals(false,iField.hashCode()== dummy.hashCode());
    }

    //-----------------------------------------------------------------------
    public void test_compareTo() {
        assertEquals(0,iField.compareTo(iField));
        assertEquals(-1,iField.compareTo(ISOChronology.getInstance().minutes()));
        DurationField dummy = new PreciseDurationField(DurationFieldType.minutes(), 0);
        assertEquals(1,iField.compareTo(dummy));
//        try {
//            iField.compareTo("");
//            fail();
//        } catch (ClassCastException ex) {}
        try {
            iField.compareTo(null);
            fail();
        } catch (NullPointerException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testSerialization() throws Exception {
        DurationField test = iField;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DurationField result = (DurationField) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

    public void test_getScalar_1_oe() {
        int a = 90;
        assertEquals(a, iField.getScalar());
    }

    public void test_getType_1_oe() {
        Object a = DurationFieldType.minutes();
        assertEquals(a, iField.getType());
    }

    public void test_getName_1_oe() {
        Object a = iField.getName();
        assertEquals("minutes", a);
    }

    public void test_isSupported_1_oe() {
        boolean a = true;
        assertEquals(a, iField.isSupported());
    }

    public void test_isPrecise_1_oe() {
        boolean a = true;
        assertEquals(a, iField.isPrecise());
    }

    public void test_getUnitMillis_1_oe() {
        int a = 90;
        assertEquals(a, iField.getUnitMillis());
    }

    public void test_toString_1_oe() {
        Object a = iField.toString();
        assertEquals("DurationField[minutes]", a);
    }

    public void test_getValue_long_1_oe() {
        int a = 0;
        assertEquals(a, iField.getValue(0L));
    }

    public void test_getValue_long_2_oe() {
        Object a = 12345678 / 90;
        assertEquals(a, iField.getValue(12345678L));
    }

    public void test_getValue_long_3_oe() {
        Object a = -1234 / 90;
        assertEquals(a, iField.getValue(-1234L));
    }

    public void test_getValue_long_4_oe() {
        Object a = INTEGER_MAX / 90;
        assertEquals(a, iField.getValue(LONG_INTEGER_MAX));
    }

    public void test_getValueAsLong_long_1_oe() {
        long a = 0L;
        assertEquals(a, iField.getValueAsLong(0L));
    }

    public void test_getValueAsLong_long_2_oe() {
        Object a = 12345678L / 90;
        assertEquals(a, iField.getValueAsLong(12345678L));
    }

    public void test_getValueAsLong_long_3_oe() {
        Object a = -1234 / 90L;
        assertEquals(a, iField.getValueAsLong(-1234L));
    }

    public void test_getValueAsLong_long_4_oe() {
        Object a = LONG_INTEGER_MAX + 1L;
        assertEquals(a, iField.getValueAsLong(LONG_INTEGER_MAX * 90L + 90L));
    }

    public void test_getValue_long_long_1_oe() {
        int a = 0;
        assertEquals(a, iField.getValue(0L,567L));
    }

    public void test_getValue_long_long_2_oe() {
        Object a = 12345678 / 90;
        assertEquals(a, iField.getValue(12345678L,567L));
    }

    public void test_getValue_long_long_3_oe() {
        Object a = -1234 / 90;
        assertEquals(a, iField.getValue(-1234L,567L));
    }

    public void test_getValue_long_long_4_oe() {
        Object a = INTEGER_MAX / 90;
        assertEquals(a, iField.getValue(LONG_INTEGER_MAX,567L));
    }

    public void test_getValueAsLong_long_long_1_oe() {
        long a = 0L;
        assertEquals(a, iField.getValueAsLong(0L,567L));
    }

    public void test_getValueAsLong_long_long_2_oe() {
        Object a = 12345678 / 90L;
        assertEquals(a, iField.getValueAsLong(12345678L,567L));
    }

    public void test_getValueAsLong_long_long_3_oe() {
        Object a = -1234 / 90L;
        assertEquals(a, iField.getValueAsLong(-1234L,567L));
    }

    public void test_getValueAsLong_long_long_4_oe() {
        Object a = LONG_INTEGER_MAX + 1L;
        assertEquals(a, iField.getValueAsLong(LONG_INTEGER_MAX * 90L + 90L,567L));
    }

    public void test_getMillis_int_1_oe() {
        int a = 0;
        assertEquals(a, iField.getMillis(0));
    }

    public void test_getMillis_int_2_oe() {
        Object a = 1234L * 90L;
        assertEquals(a, iField.getMillis(1234));
    }

    public void test_getMillis_int_3_oe() {
        Object a = -1234L * 90L;
        assertEquals(a, iField.getMillis(-1234));
    }

    public void test_getMillis_int_4_oe() {
        Object a = LONG_INTEGER_MAX * 90L;
        assertEquals(a, iField.getMillis(INTEGER_MAX));
    }

    public void test_getMillis_long_1_oe() {
        long a = 0L;
        assertEquals(a, iField.getMillis(0L));
    }

    public void test_getMillis_long_2_oe() {
        Object a = 1234L * 90L;
        assertEquals(a, iField.getMillis(1234L));
    }

    public void test_getMillis_long_3_oe() {
        Object a = -1234L * 90L;
        assertEquals(a, iField.getMillis(-1234L));
    }

    public void test_getMillis_int_long_1_oe() {
        long a = 0L;
        assertEquals(a, iField.getMillis(0,567L));
    }

    public void test_getMillis_int_long_2_oe() {
        Object a = 1234L * 90L;
        assertEquals(a, iField.getMillis(1234,567L));
    }

    public void test_getMillis_int_long_3_oe() {
        Object a = -1234L * 90L;
        assertEquals(a, iField.getMillis(-1234,567L));
    }

    public void test_getMillis_int_long_4_oe() {
        Object a = LONG_INTEGER_MAX * 90L;
        assertEquals(a, iField.getMillis(INTEGER_MAX,567L));
    }

    public void test_getMillis_long_long_1_oe() {
        long a = 0L;
        assertEquals(a, iField.getMillis(0L,567L));
    }

    public void test_getMillis_long_long_2_oe() {
        Object a = 1234L * 90L;
        assertEquals(a, iField.getMillis(1234L,567L));
    }

    public void test_getMillis_long_long_3_oe() {
        Object a = -1234L * 90L;
        assertEquals(a, iField.getMillis(-1234L,567L));
    }

    public void test_add_long_int_1_oe() {
        long a = 567L;
        assertEquals(a, iField.add(567L,0));
    }

    public void test_add_long_int_3_oe() {
        Object a = 567L - 1234L * 90L;
        assertEquals(a, iField.add(567L,-1234));
    }

    public void test_add_long_long_1_oe() {
        long a = 567L;
        assertEquals(a, iField.add(567L,0L));
    }

    public void test_add_long_long_3_oe() {
        Object a = 567L - 1234L * 90L;
        assertEquals(a, iField.add(567L,-1234L));
    }

    public void test_getDifference_long_int_1_oe() {
        int a = 0;
        assertEquals(a, iField.getDifference(1L,0L));
    }

    public void test_getDifference_long_int_2_oe() {
        int a = 567;
        assertEquals(a, iField.getDifference(567L * 90L,0L));
    }

    public void test_getDifference_long_int_3_oe() {
        Object a = 567 - 1234;
        assertEquals(a, iField.getDifference(567L * 90L,1234L * 90L));
    }

    public void test_getDifference_long_int_4_oe() {
        Object a = 567 + 1234;
        assertEquals(a, iField.getDifference(567L * 90L,-1234L * 90L));
    }

    public void test_getDifferenceAsLong_long_long_1_oe() {
        long a = 0L;
        assertEquals(a, iField.getDifferenceAsLong(1L,0L));
    }

    public void test_getDifferenceAsLong_long_long_3_oe() {
        Object a = 567L - 1234L;
        assertEquals(a, iField.getDifferenceAsLong(567L * 90L,1234L * 90L));
    }

    public void test_getDifferenceAsLong_long_long_4_oe() {
        Object a = 567L + 1234L;
        assertEquals(a, iField.getDifferenceAsLong(567L * 90L,-1234L * 90L));
    }

    public void test_equals_1_oe() {
        boolean a = true;
        assertEquals(a, iField.equals(iField));
    }

    public void test_equals_2_oe() {
        boolean a = false;
        assertEquals(a, iField.equals(ISOChronology.getInstance().minutes()));
    }

    public void test_equals_3_oe() {
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        assertEquals(false,iField.equals(dummy));
    }

    public void test_equals_4_oe() {
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        assertEquals(true,iField.equals(dummy));
    }

    public void test_equals_5_oe() {
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.millis(), 90);
        assertEquals(false,iField.equals(dummy));
    }

    public void test_equals_6_oe() {
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.millis(), 90);
        assertEquals(false,iField.equals(""));
    }

    public void test_equals_7_oe() {
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.millis(), 90);
        assertEquals(false,iField.equals(null));
    }

    public void test_hashCode_1_oe() {
        Object a = iField.hashCode();
        assertEquals(a, iField.hashCode());
    }

    public void test_hashCode_2_oe() {
        boolean a = false;
        assertEquals(a, iField.hashCode()== ISOChronology.getInstance().minutes().hashCode());
    }

    public void test_hashCode_3_oe() {
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        assertEquals(false,iField.hashCode()== dummy.hashCode());
    }

    public void test_hashCode_4_oe() {
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        assertEquals(true,iField.hashCode()== dummy.hashCode());
    }

    public void test_hashCode_5_oe() {
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.millis(), 90);
        assertEquals(false,iField.hashCode()== dummy.hashCode());
    }

    public void test_compareTo_1_oe() {
        int a = 0;
        assertEquals(a, iField.compareTo(iField));
    }

    public void test_compareTo_2_oe() {
        int a = -1;
        assertEquals(a, iField.compareTo(ISOChronology.getInstance().minutes()));
    }

    public void test_compareTo_3_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.minutes(), 0);
        assertEquals(1,iField.compareTo(dummy));
    }

    public void testSerialization_1_oe() throws Exception {
        DurationField test = iField;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DurationField result = (DurationField) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

    public void test_add_long_int_2_oe() {
        Object a = 567L + 1234L * 90L;
        assertEquals(a, iField.add(567L,1234));
    }

    public void test_add_long_long_2_oe() {
        Object a = 567L + 1234L * 90L;
        assertEquals(a, iField.add(567L,1234L));
    }

    public void test_getDifferenceAsLong_long_long_2_oe() {
        long a = 567L;
        assertEquals(a, iField.getDifferenceAsLong(567L * 90L,0L));
    }

public void test_constructor_oe_101_oe() {
        try {
            new ScaledDurationField(null, DurationFieldType.minutes(), 10);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void test_constructor_oe_102_oe() {
        try {
            new ScaledDurationField(MillisDurationField.INSTANCE, null, 10);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void test_constructor_oe_103_oe() {
        try {
            new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void test_constructor_oe_104_oe() {
        try {
            new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void test_getValue_long_oe_101_oe() {
        try {
            iField.getValue(LONG_INTEGER_MAX + 1L);
            fail();
        } catch (ArithmeticException ex) {}
    }

public void test_getValue_long_long_oe_101_oe() {
        try {
            iField.getValue(LONG_INTEGER_MAX + 1L, 567L);
            fail();
        } catch (ArithmeticException ex) {}
    }

public void test_getMillis_long_oe_101_oe() {
        try {
            iField.getMillis(LONG_MAX);
            fail();
        } catch (ArithmeticException ex) {}
    }

public void test_getMillis_long_long_oe_101_oe() {
        try {
            iField.getMillis(LONG_MAX, 567L);
            fail();
        } catch (ArithmeticException ex) {}
    }

public void test_add_long_int_oe_101_oe() {
        try {
            iField.add(LONG_MAX, 1);
            fail();
        } catch (ArithmeticException ex) {}
    }

public void test_add_long_long_oe_101_oe() {
        try {
            iField.add(LONG_MAX, 1L);
            fail();
        } catch (ArithmeticException ex) {}
    }

public void test_add_long_long_oe_102_oe() {
        try {
            iField.add(1L, LONG_MAX);
            fail();
        } catch (ArithmeticException ex) {}
    }

public void test_getDifference_long_int_oe_101_oe() {
        try {
            iField.getDifference(LONG_MAX, -1L);
            fail();
        } catch (ArithmeticException ex) {}
    }

public void test_getDifferenceAsLong_long_long_oe_101_oe() {
        try {
            iField.getDifferenceAsLong(LONG_MAX, -1L);
            fail();
        } catch (ArithmeticException ex) {}
    }

public void test_compareTo_oe_102_oe() {
        try {
            iField.compareTo(null);
            fail();
        } catch (NullPointerException ex) {}
    }

}
