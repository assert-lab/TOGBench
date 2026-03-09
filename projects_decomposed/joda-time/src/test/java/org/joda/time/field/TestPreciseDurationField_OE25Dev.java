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
public class TestPreciseDurationField_OE25Dev extends TestCase {
    
    private static final long LONG_INTEGER_MAX = Integer.MAX_VALUE;
    private static final int INTEGER_MAX = Integer.MAX_VALUE;
    private static final long LONG_MAX = Long.MAX_VALUE;
    
    private PreciseDurationField iField;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestPreciseDurationField_OE25Dev.class);
    }

    public TestPreciseDurationField_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        iField = new PreciseDurationField(DurationFieldType.seconds(), 1000);
    }

    @Override
    protected void tearDown() throws Exception {
        iField = null;
    }

    //-----------------------------------------------------------------------
    public void test_constructor() {
        try {
            new PreciseDurationField(null, 10);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void test_getType() {
        assertEquals(DurationFieldType.seconds(),iField.getType());
    }

    public void test_getName() {
        assertEquals("seconds",iField.getName());
    }
    
    public void test_isSupported() {
        assertEquals(true,iField.isSupported());
    }

    public void test_isPrecise() {
        assertEquals(true,iField.isPrecise());
    }

    public void test_getUnitMillis() {
        assertEquals(1000,iField.getUnitMillis());
    }

    public void test_toString() {
        assertEquals("DurationField[seconds]",iField.toString());
    }

    //-----------------------------------------------------------------------
    public void test_getValue_long() {
        assertEquals(0,iField.getValue(0L));
        assertEquals(12345,iField.getValue(12345678L));
        assertEquals(-1,iField.getValue(-1234L));
        assertEquals(INTEGER_MAX,iField.getValue(LONG_INTEGER_MAX * 1000L + 999L));
        try {
            iField.getValue(LONG_INTEGER_MAX * 1000L + 1000L);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void test_getValueAsLong_long() {
        assertEquals(0L,iField.getValueAsLong(0L));
        assertEquals(12345L,iField.getValueAsLong(12345678L));
        assertEquals(-1L,iField.getValueAsLong(-1234L));
        assertEquals(LONG_INTEGER_MAX + 1L,iField.getValueAsLong(LONG_INTEGER_MAX * 1000L + 1000L));
    }

    public void test_getValue_long_long() {
        assertEquals(0,iField.getValue(0L,567L));
        assertEquals(12345,iField.getValue(12345678L,567L));
        assertEquals(-1,iField.getValue(-1234L,567L));
        assertEquals(INTEGER_MAX,iField.getValue(LONG_INTEGER_MAX * 1000L + 999L,567L));
        try {
            iField.getValue(LONG_INTEGER_MAX * 1000L + 1000L, 567L);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void test_getValueAsLong_long_long() {
        assertEquals(0L,iField.getValueAsLong(0L,567L));
        assertEquals(12345L,iField.getValueAsLong(12345678L,567L));
        assertEquals(-1L,iField.getValueAsLong(-1234L,567L));
        assertEquals(LONG_INTEGER_MAX + 1L,iField.getValueAsLong(LONG_INTEGER_MAX * 1000L + 1000L,567L));
    }

    //-----------------------------------------------------------------------
    public void test_getMillis_int() {
        assertEquals(0,iField.getMillis(0));
        assertEquals(1234000L,iField.getMillis(1234));
        assertEquals(-1234000L,iField.getMillis(-1234));
        assertEquals(LONG_INTEGER_MAX * 1000L,iField.getMillis(INTEGER_MAX));
    }

    public void test_getMillis_long() {
        assertEquals(0L,iField.getMillis(0L));
        assertEquals(1234000L,iField.getMillis(1234L));
        assertEquals(-1234000L,iField.getMillis(-1234L));
        try {
            iField.getMillis(LONG_MAX);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void test_getMillis_int_long() {
        assertEquals(0L,iField.getMillis(0,567L));
        assertEquals(1234000L,iField.getMillis(1234,567L));
        assertEquals(-1234000L,iField.getMillis(-1234,567L));
        assertEquals(LONG_INTEGER_MAX * 1000L,iField.getMillis(INTEGER_MAX,567L));
    }

    public void test_getMillis_long_long() {
        assertEquals(0L,iField.getMillis(0L,567L));
        assertEquals(1234000L,iField.getMillis(1234L,567L));
        assertEquals(-1234000L,iField.getMillis(-1234L,567L));
        try {
            iField.getMillis(LONG_MAX, 567L);
            fail();
        } catch (ArithmeticException ex) {}
    }

    //-----------------------------------------------------------------------
    public void test_add_long_int() {
        assertEquals(567L,iField.add(567L,0));
        assertEquals(567L + 1234000L,iField.add(567L,1234));
        assertEquals(567L - 1234000L,iField.add(567L,-1234));
        try {
            iField.add(LONG_MAX, 1);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void test_add_long_long() {
        assertEquals(567L,iField.add(567L,0L));
        assertEquals(567L + 1234000L,iField.add(567L,1234L));
        assertEquals(567L - 1234000L,iField.add(567L,-1234L));
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
        assertEquals(567,iField.getDifference(567000L,0L));
        assertEquals(567 - 1234,iField.getDifference(567000L,1234000L));
        assertEquals(567 + 1234,iField.getDifference(567000L,-1234000L));
        try {
            iField.getDifference(LONG_MAX, -1L);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void test_getDifferenceAsLong_long_long() {
        assertEquals(0L,iField.getDifferenceAsLong(1L,0L));
        assertEquals(567L,iField.getDifferenceAsLong(567000L,0L));
        assertEquals(567L - 1234L,iField.getDifferenceAsLong(567000L,1234000L));
        assertEquals(567L + 1234L,iField.getDifferenceAsLong(567000L,-1234000L));
        try {
            iField.getDifferenceAsLong(LONG_MAX, -1L);
            fail();
        } catch (ArithmeticException ex) {}
    }

    //-----------------------------------------------------------------------
    public void test_equals() {
        assertEquals(true,iField.equals(iField));
        assertEquals(false,iField.equals(ISOChronology.getInstance().minutes()));
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        assertEquals(false,iField.equals(dummy));
        dummy = new PreciseDurationField(DurationFieldType.seconds(), 1000);
        assertEquals(true,iField.equals(dummy));
        dummy = new PreciseDurationField(DurationFieldType.millis(), 1000);
        assertEquals(false,iField.equals(dummy));
        assertEquals(false,iField.equals(""));
        assertEquals(false,iField.equals(null));
    }

    public void test_hashCode() {
        assertEquals(true,iField.hashCode()== iField.hashCode());
        assertEquals(false,iField.hashCode()== ISOChronology.getInstance().minutes().hashCode());
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        assertEquals(false,iField.hashCode()== dummy.hashCode());
        dummy = new PreciseDurationField(DurationFieldType.seconds(), 1000);
        assertEquals(true,iField.hashCode()== dummy.hashCode());
        dummy = new PreciseDurationField(DurationFieldType.millis(), 1000);
        assertEquals(false,iField.hashCode()== dummy.hashCode());
    }

    //-----------------------------------------------------------------------
    public void test_compareTo() {
        assertEquals(0,iField.compareTo(iField));
        assertEquals(-1,iField.compareTo(ISOChronology.getInstance().minutes()));
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
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

    public void test_toString_1_oe() {
        Object a = iField.toString();
        assertNotNull(a);
    }

    public void test_getValue_long_1_oe() {
        int a = 0;
        assertEquals(0, a);
    }

    public void test_getValue_long_2_oe() {
        int a = 12345;
        assertEquals(12, 12345);
    }

    public void test_getValue_long_3_oe() {
        int a = -1;
        assertEquals(0, -1);
    }

    public void test_getValue_long_4_oe() {
        Object a = INTEGER_MAX;
// incorrect assertion         assertEquals(0, INTEGER_MAX.getValue());
    }

    public void test_getValueAsLong_long_1_oe() {
        long a = 0L;
        assertEquals(0L, a);
    }

    public void test_getValueAsLong_long_2_oe() {
        long a = 12345L;
        assertEquals(12345L, 12345L);
    }

    public void test_getValueAsLong_long_4_oe() {
        Object a = LONG_INTEGER_MAX + 1L;
// incorrect assertion         assertEquals(1000L * 60L * 60L * 24L * 365L * 67L + 1000L * 60L * 60L * 24L * 365L + 1000L * 60L * 60L * 24L + 1000L * 60L * 6;
    }

    public void test_getValue_long_long_1_oe() {
        int a = 0;
        assertEquals(0, a);
    }

    public void test_getValue_long_long_2_oe() {
        int a = 12345;
        assertEquals(0, 12345);
    }

    public void test_getValue_long_long_3_oe() {
        int a = -1;
        assertEquals(0, -1);
    }

    public void test_getValue_long_long_4_oe() {
        Object a = INTEGER_MAX;
// incorrect assertion         assertEquals(0, INTEGER_MAX.getValue());
    }

    public void test_getValueAsLong_long_long_1_oe() {
        long a = 0L;
        assertEquals(0L, a);
    }

    public void test_getValueAsLong_long_long_4_oe() {
        Object a = LONG_INTEGER_MAX + 1L;
        assertEquals(0L, a);
    }

    public void test_getMillis_int_2_oe() {
        long a = 1234000L;
// incorrect assertion         assertEquals(1234000L, this.getMillis(1234, 1234000L));
    }

    public void test_getMillis_int_3_oe() {
        long a = -1234000L;
        assertEquals((-1234000L * 86400000L), -1234000L);
    }

    public void test_getMillis_int_4_oe() {
        Object a = LONG_INTEGER_MAX * 1000L;
        assertEquals(9223372036854775L, a);
    }

    public void test_getMillis_long_1_oe() {
        long a = 0L;
        assertEquals(0L, a);
    }

    public void test_getMillis_long_3_oe() {
        long a = -1234000L;
        assertEquals((-1234000L * 86400000L), -1234000L);
    }

    public void test_getMillis_int_long_1_oe() {
        long a = 0L;
        assertEquals(0L, a);
    }

    public void test_getMillis_int_long_2_oe() {
        long a = 1234000L;
// incorrect assertion         assertEquals(1234000L, this.getMillis(1234, 1234000L));
    }

    public void test_getMillis_int_long_3_oe() {
        long a = -1234000L;
        assertEquals((-1234000L * 86400000L), -1234000L);
    }

    public void test_getMillis_int_long_4_oe() {
        Object a = LONG_INTEGER_MAX * 1000L;
        assertEquals(9223372036854775L, a);
    }

    public void test_getMillis_long_long_1_oe() {
        long a = 0L;
// incorrect assertion         assertEquals(0L, instant);
    }

    public void test_getMillis_long_long_3_oe() {
        long a = -1234000L;
        assertEquals((-1234000L * 86400000L), -1234000L);
    }

    public void test_add_long_int_3_oe() {
        Object a = 567L - 1234000L;
        assertEquals(567L - 1234000L, a);
    }

    public void test_add_long_long_3_oe() {
        Object a = 567L - 1234000L;
        assertEquals(567L - 1234000L, a);
    }

    public void test_equals_1_oe() {
        boolean a = true;
        assertEquals(true, a);
    }

    public void test_equals_2_oe() {
        boolean a = false;
        assertEquals(false, a);
    }

    public void test_equals_3_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        assertNotNull(DurationFieldType.seconds());
    }

    public void test_equals_4_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        dummy = new PreciseDurationField(DurationFieldType.seconds(), 1000);
        assertNotNull(DurationFieldType.seconds());
    }

    public void test_equals_5_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        dummy = new PreciseDurationField(DurationFieldType.seconds(), 1000);
        dummy = new PreciseDurationField(DurationFieldType.millis(), 1000);
        assertEquals("millis", DurationFieldType.millis().getName());
    }

    public void test_equals_6_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        dummy = new PreciseDurationField(DurationFieldType.seconds(), 1000);
        dummy = new PreciseDurationField(DurationFieldType.millis(), 1000);
        assertEquals("millis", DurationFieldType.millis().getName());
    }

    public void test_equals_7_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        dummy = new PreciseDurationField(DurationFieldType.seconds(), 1000);
        dummy = new PreciseDurationField(DurationFieldType.millis(), 1000);
        assertEquals("millis", DurationFieldType.millis().getName());
    }

    public void test_hashCode_1_oe() {
        boolean a = true;
        assertEquals(true, a);
    }

    public void test_hashCode_2_oe() {
        boolean a = false;
        assertEquals(false, a);
    }

    public void test_hashCode_3_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        assertNotNull(DurationFieldType.seconds());
    }

    public void test_hashCode_4_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        dummy = new PreciseDurationField(DurationFieldType.seconds(), 1000);
        assertEquals(false, dummy.isPrecise());
    }

    public void test_hashCode_5_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        dummy = new PreciseDurationField(DurationFieldType.seconds(), 1000);
        dummy = new PreciseDurationField(DurationFieldType.millis(), 1000);
        assertEquals("millis", DurationFieldType.millis().getName());
    }

    public void test_compareTo_3_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        assertEquals(0, dummy.compareTo(dummy));
    }

    public void test_add_long_long_2_oe() {
        Object a = 567L + 1234000L;
        assertEquals(1234000L, a);
    }

}
