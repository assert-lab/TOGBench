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
        return new TestSuite(TestScaledDurationField_OE25Dev_OE25Dev.class);
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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void test_getScalar_1_oe() {
        assertEquals(90,iField.getScalar());
    }

    public void test_getType_1_oe() {
        assertEquals(DurationFieldType.minutes(),iField.getType());
    }

    public void test_getName_1_oe() {
        assertEquals("minutes",iField.getName());
    }

    public void test_isSupported_1_oe() {
        assertEquals(true,iField.isSupported());
    }

    public void test_isPrecise_1_oe() {
        assertEquals(true,iField.isPrecise());
    }

    public void test_getUnitMillis_1_oe() {
        assertEquals(90,iField.getUnitMillis());
    }

    public void test_toString_1_oe() {
        assertEquals("DurationField[minutes]",iField.toString());
    }

    public void test_getValue_long_1_oe() {
        assertEquals(0,iField.getValue(0L));
    }

    public void test_getValue_long_2_oe() {
        // removed other assertion
        assertEquals(12345678 / 90,iField.getValue(12345678L));
    }

    public void test_getValue_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1234 / 90,iField.getValue(-1234L));
    }

    public void test_getValue_long_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(INTEGER_MAX / 90,iField.getValue(LONG_INTEGER_MAX));
    }

    public void test_getValueAsLong_long_1_oe() {
        assertEquals(0L,iField.getValueAsLong(0L));
    }

    public void test_getValueAsLong_long_2_oe() {
        // removed other assertion
        assertEquals(12345678L / 90,iField.getValueAsLong(12345678L));
    }

    public void test_getValueAsLong_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1234 / 90L,iField.getValueAsLong(-1234L));
    }

    public void test_getValueAsLong_long_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(LONG_INTEGER_MAX + 1L,iField.getValueAsLong(LONG_INTEGER_MAX * 90L + 90L));
    }

    public void test_getValue_long_long_1_oe() {
        assertEquals(0,iField.getValue(0L,567L));
    }

    public void test_getValue_long_long_2_oe() {
        // removed other assertion
        assertEquals(12345678 / 90,iField.getValue(12345678L,567L));
    }

    public void test_getValue_long_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1234 / 90,iField.getValue(-1234L,567L));
    }

    public void test_getValue_long_long_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(INTEGER_MAX / 90,iField.getValue(LONG_INTEGER_MAX,567L));
    }

    public void test_getValueAsLong_long_long_1_oe() {
        assertEquals(0L,iField.getValueAsLong(0L,567L));
    }

    public void test_getValueAsLong_long_long_2_oe() {
        // removed other assertion
        assertEquals(12345678 / 90L,iField.getValueAsLong(12345678L,567L));
    }

    public void test_getValueAsLong_long_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1234 / 90L,iField.getValueAsLong(-1234L,567L));
    }

    public void test_getValueAsLong_long_long_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(LONG_INTEGER_MAX + 1L,iField.getValueAsLong(LONG_INTEGER_MAX * 90L + 90L,567L));
    }

    public void test_getMillis_int_1_oe() {
        assertEquals(0,iField.getMillis(0));
    }

    public void test_getMillis_int_2_oe() {
        // removed other assertion
        assertEquals(1234L * 90L,iField.getMillis(1234));
    }

    public void test_getMillis_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1234L * 90L,iField.getMillis(-1234));
    }

    public void test_getMillis_int_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(LONG_INTEGER_MAX * 90L,iField.getMillis(INTEGER_MAX));
    }

    public void test_getMillis_long_1_oe() {
        assertEquals(0L,iField.getMillis(0L));
    }

    public void test_getMillis_long_2_oe() {
        // removed other assertion
        assertEquals(1234L * 90L,iField.getMillis(1234L));
    }

    public void test_getMillis_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1234L * 90L,iField.getMillis(-1234L));
    }

    public void test_getMillis_int_long_1_oe() {
        assertEquals(0L,iField.getMillis(0,567L));
    }

    public void test_getMillis_int_long_2_oe() {
        // removed other assertion
        assertEquals(1234L * 90L,iField.getMillis(1234,567L));
    }

    public void test_getMillis_int_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1234L * 90L,iField.getMillis(-1234,567L));
    }

    public void test_getMillis_int_long_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(LONG_INTEGER_MAX * 90L,iField.getMillis(INTEGER_MAX,567L));
    }

    public void test_getMillis_long_long_1_oe() {
        assertEquals(0L,iField.getMillis(0L,567L));
    }

    public void test_getMillis_long_long_2_oe() {
        // removed other assertion
        assertEquals(1234L * 90L,iField.getMillis(1234L,567L));
    }

    public void test_getMillis_long_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1234L * 90L,iField.getMillis(-1234L,567L));
    }

    public void test_add_long_int_1_oe() {
        assertEquals(567L,iField.add(567L,0));
    }

    public void test_add_long_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(567L - 1234L * 90L,iField.add(567L,-1234));
    }

    public void test_add_long_long_1_oe() {
        assertEquals(567L,iField.add(567L,0L));
    }

    public void test_add_long_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(567L - 1234L * 90L,iField.add(567L,-1234L));
    }

    public void test_getDifference_long_int_1_oe() {
        assertEquals(0,iField.getDifference(1L,0L));
    }

    public void test_getDifference_long_int_2_oe() {
        // removed other assertion
        assertEquals(567,iField.getDifference(567L * 90L,0L));
    }

    public void test_getDifference_long_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(567 - 1234,iField.getDifference(567L * 90L,1234L * 90L));
    }

    public void test_getDifference_long_int_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(567 + 1234,iField.getDifference(567L * 90L,-1234L * 90L));
    }

    public void test_getDifferenceAsLong_long_long_1_oe() {
        assertEquals(0L,iField.getDifferenceAsLong(1L,0L));
    }

    public void test_getDifferenceAsLong_long_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(567L - 1234L,iField.getDifferenceAsLong(567L * 90L,1234L * 90L));
    }

    public void test_getDifferenceAsLong_long_long_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(567L + 1234L,iField.getDifferenceAsLong(567L * 90L,-1234L * 90L));
    }

    public void test_equals_1_oe() {
        assertEquals(true,iField.equals(iField));
    }

    public void test_equals_2_oe() {
        // removed other assertion
        assertEquals(false,iField.equals(ISOChronology.getInstance().minutes()));
    }

    public void test_equals_3_oe() {
        // removed other assertion
        // removed other assertion
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        assertEquals(false,iField.equals(dummy));
    }

    public void test_equals_4_oe() {
        // removed other assertion
        // removed other assertion
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        // removed other assertion
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        assertEquals(true,iField.equals(dummy));
    }

    public void test_equals_5_oe() {
        // removed other assertion
        // removed other assertion
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        // removed other assertion
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        // removed other assertion
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.millis(), 90);
        assertEquals(false,iField.equals(dummy));
    }

    public void test_equals_6_oe() {
        // removed other assertion
        // removed other assertion
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        // removed other assertion
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        // removed other assertion
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.millis(), 90);
        // removed other assertion
        assertEquals(false,iField.equals(""));
    }

    public void test_equals_7_oe() {
        // removed other assertion
        // removed other assertion
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        // removed other assertion
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        // removed other assertion
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.millis(), 90);
        // removed other assertion
        // removed other assertion
        assertEquals(false,iField.equals(null));
    }

    public void test_hashCode_1_oe() {
        assertEquals(iField.hashCode(),iField.hashCode());
    }

    public void test_hashCode_2_oe() {
        // removed other assertion
        assertEquals(false,iField.hashCode()== ISOChronology.getInstance().minutes().hashCode());
    }

    public void test_hashCode_3_oe() {
        // removed other assertion
        // removed other assertion
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        assertEquals(false,iField.hashCode()== dummy.hashCode());
    }

    public void test_hashCode_4_oe() {
        // removed other assertion
        // removed other assertion
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        // removed other assertion
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        assertEquals(true,iField.hashCode()== dummy.hashCode());
    }

    public void test_hashCode_5_oe() {
        // removed other assertion
        // removed other assertion
        DurationField dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 2);
        // removed other assertion
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.minutes(), 90);
        // removed other assertion
        dummy = new ScaledDurationField(MillisDurationField.INSTANCE, DurationFieldType.millis(), 90);
        assertEquals(false,iField.hashCode()== dummy.hashCode());
    }

    public void test_compareTo_1_oe() {
        assertEquals(0,iField.compareTo(iField));
    }

    public void test_compareTo_2_oe() {
        // removed other assertion
        assertEquals(-1,iField.compareTo(ISOChronology.getInstance().minutes()));
    }

    public void test_compareTo_3_oe() {
        // removed other assertion
        // removed other assertion
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

}
