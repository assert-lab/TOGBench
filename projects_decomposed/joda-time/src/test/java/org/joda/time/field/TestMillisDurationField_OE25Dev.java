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
 * This class is a Junit unit test for PeriodFormatterBuilder.
 *
 * @author Stephen Colebourne
 */
public class TestMillisDurationField_OE25Dev extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMillisDurationField_OE25Dev.class);
    }

    public TestMillisDurationField_OE25Dev(String name) {
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

    public void test_getType_1_oe() {
        assertEquals(DurationFieldType.millis(),MillisDurationField.INSTANCE.getType());
    }

    public void test_getName_1_oe() {
        assertEquals("millis",MillisDurationField.INSTANCE.getName());
    }

    public void test_isSupported_1_oe() {
        assertEquals(true,MillisDurationField.INSTANCE.isSupported());
    }

    public void test_isPrecise_1_oe() {
        assertEquals(true,MillisDurationField.INSTANCE.isPrecise());
    }

    public void test_getUnitMillis_1_oe() {
        assertEquals(1,MillisDurationField.INSTANCE.getUnitMillis());
    }

    public void test_toString_1_oe() {
        assertEquals("DurationField[millis]",MillisDurationField.INSTANCE.toString());
    }

    public void test_getValue_long_1_oe() {
        assertEquals(0,MillisDurationField.INSTANCE.getValue(0L));
    }

    public void test_getValue_long_2_oe() {
        assertEquals(1234,MillisDurationField.INSTANCE.getValue(1234L));
    }

    public void test_getValue_long_3_oe() {
        assertEquals(-1234,MillisDurationField.INSTANCE.getValue(-1234L));
    }

    public void test_getValueAsLong_long_1_oe() {
        assertEquals(0L,MillisDurationField.INSTANCE.getValueAsLong(0L));
    }

    public void test_getValueAsLong_long_2_oe() {
        assertEquals(1234L,MillisDurationField.INSTANCE.getValueAsLong(1234L));
    }

    public void test_getValueAsLong_long_3_oe() {
        assertEquals(-1234L,MillisDurationField.INSTANCE.getValueAsLong(-1234L));
    }

    public void test_getValueAsLong_long_4_oe() {
        assertEquals(((long)(Integer.MAX_VALUE))+ 1L,MillisDurationField.INSTANCE.getValueAsLong(((long)(Integer.MAX_VALUE))+ 1L));
    }

    public void test_getValue_long_long_1_oe() {
        assertEquals(0,MillisDurationField.INSTANCE.getValue(0L,567L));
    }

    public void test_getValue_long_long_2_oe() {
        assertEquals(1234,MillisDurationField.INSTANCE.getValue(1234L,567L));
    }

    public void test_getValue_long_long_3_oe() {
        assertEquals(-1234,MillisDurationField.INSTANCE.getValue(-1234L,567L));
    }

    public void test_getValueAsLong_long_long_1_oe() {
        assertEquals(0L,MillisDurationField.INSTANCE.getValueAsLong(0L,567L));
    }

    public void test_getValueAsLong_long_long_2_oe() {
        assertEquals(1234L,MillisDurationField.INSTANCE.getValueAsLong(1234L,567L));
    }

    public void test_getValueAsLong_long_long_3_oe() {
        assertEquals(-1234L,MillisDurationField.INSTANCE.getValueAsLong(-1234L,567L));
    }

    public void test_getValueAsLong_long_long_4_oe() {
        assertEquals(((long)(Integer.MAX_VALUE))+ 1L,MillisDurationField.INSTANCE.getValueAsLong(((long)(Integer.MAX_VALUE))+ 1L,567L));
    }

    public void test_getMillis_int_1_oe() {
        assertEquals(0,MillisDurationField.INSTANCE.getMillis(0));
    }

    public void test_getMillis_int_2_oe() {
        assertEquals(1234,MillisDurationField.INSTANCE.getMillis(1234));
    }

    public void test_getMillis_int_3_oe() {
        assertEquals(-1234,MillisDurationField.INSTANCE.getMillis(-1234));
    }

    public void test_getMillis_long_1_oe() {
        assertEquals(0L,MillisDurationField.INSTANCE.getMillis(0L));
    }

    public void test_getMillis_long_2_oe() {
        assertEquals(1234L,MillisDurationField.INSTANCE.getMillis(1234L));
    }

    public void test_getMillis_long_3_oe() {
        assertEquals(-1234L,MillisDurationField.INSTANCE.getMillis(-1234L));
    }

    public void test_getMillis_int_long_1_oe() {
        assertEquals(0,MillisDurationField.INSTANCE.getMillis(0,567L));
    }

    public void test_getMillis_int_long_2_oe() {
        assertEquals(1234,MillisDurationField.INSTANCE.getMillis(1234,567L));
    }

    public void test_getMillis_int_long_3_oe() {
        assertEquals(-1234,MillisDurationField.INSTANCE.getMillis(-1234,567L));
    }

    public void test_getMillis_long_long_1_oe() {
        assertEquals(0L,MillisDurationField.INSTANCE.getMillis(0L,567L));
    }

    public void test_getMillis_long_long_2_oe() {
        assertEquals(1234L,MillisDurationField.INSTANCE.getMillis(1234L,567L));
    }

    public void test_getMillis_long_long_3_oe() {
        assertEquals(-1234L,MillisDurationField.INSTANCE.getMillis(-1234L,567L));
    }

    public void test_add_long_int_1_oe() {
        assertEquals(567L,MillisDurationField.INSTANCE.add(567L,0));
    }

    public void test_add_long_int_3_oe() {
        assertEquals(567L - 1234L,MillisDurationField.INSTANCE.add(567L,-1234));
    }

    public void test_add_long_long_1_oe() {
        assertEquals(567L,MillisDurationField.INSTANCE.add(567L,0L));
    }

    public void test_add_long_long_3_oe() {
        assertEquals(567L - 1234L,MillisDurationField.INSTANCE.add(567L,-1234L));
    }

    public void test_getDifference_long_int_1_oe() {
        assertEquals(567,MillisDurationField.INSTANCE.getDifference(567L,0L));
    }

    public void test_getDifference_long_int_2_oe() {
        assertEquals(567 - 1234,MillisDurationField.INSTANCE.getDifference(567L,1234L));
    }

    public void test_getDifference_long_int_3_oe() {
        assertEquals(567 + 1234,MillisDurationField.INSTANCE.getDifference(567L,-1234L));
    }

    public void test_getDifferenceAsLong_long_long_1_oe() {
        assertEquals(567L,MillisDurationField.INSTANCE.getDifferenceAsLong(567L,0L));
    }

    public void test_getDifferenceAsLong_long_long_3_oe() {
        assertEquals(567L + 1234L,MillisDurationField.INSTANCE.getDifferenceAsLong(567L,-1234L));
    }

    public void test_compareTo_1_oe() {
        assertEquals(0,MillisDurationField.INSTANCE.compareTo(MillisDurationField.INSTANCE));
    }

    public void test_compareTo_2_oe() {
        assertEquals(-1,MillisDurationField.INSTANCE.compareTo(ISOChronology.getInstance().seconds()));
    }

    public void test_compareTo_3_oe() {
        DurationField dummy = new PreciseDurationField(DurationFieldType.seconds(), 0);
        assertEquals(1,MillisDurationField.INSTANCE.compareTo(dummy));
    }

    public void testSerialization_1_oe() throws Exception {
        DurationField test = MillisDurationField.INSTANCE;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DurationField result = (DurationField) ois.readObject();
        ois.close();
        
        assertSame(test,result);
    }

    public void test_add_long_int_2_oe() {
        assertEquals(567L + 1234L,MillisDurationField.INSTANCE.add(567L,1234));
    }

    public void test_add_long_long_2_oe() {
        assertEquals(567L + 1234L,MillisDurationField.INSTANCE.add(567L,1234L));
    }

    public void test_getDifferenceAsLong_long_long_2_oe() {
        assertEquals(567L - 1234L,MillisDurationField.INSTANCE.getDifferenceAsLong(567L,1234L));
    }

}
