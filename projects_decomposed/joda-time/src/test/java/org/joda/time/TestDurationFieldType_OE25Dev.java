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
package org.joda.time;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.CopticChronology;

/**
 * This class is a Junit unit test for DurationFieldType.
 *
 * @author Stephen Colebourne
 */
public class TestDurationFieldType_OE25Dev extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDurationFieldType_OE25Dev.class);
    }

    public TestDurationFieldType_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    //-----------------------------------------------------------------------

    public void test_other() throws Exception {
        assertEquals(1,DurationFieldType.class.getDeclaredClasses().length);
        Class cls = DurationFieldType.class.getDeclaredClasses()[0];
        assertEquals(1,cls.getDeclaredConstructors().length);
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {"other", new Byte((byte) 128)};
        DurationFieldType type = (DurationFieldType) con.newInstance(params);
        
        assertEquals("other",type.getName());
        try {
            type.getField(CopticChronology.getInstanceUTC());
            fail();
        } catch (InternalError ex) {}
        DurationFieldType result = doSerialization(type);
        assertEquals(type.getName(),result.getName());
        assertNotSame(type,result);
    }

    //-----------------------------------------------------------------------
    private void assertSerialization(DurationFieldType type) throws Exception {
        DurationFieldType result = doSerialization(type);
        assertSame(type,result);
    }

    private DurationFieldType doSerialization(DurationFieldType type) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(type);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DurationFieldType result = (DurationFieldType) ois.readObject();
        ois.close();
        return result;
    }

    public void test_eras_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.eras();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_centuries_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.centuries();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_years_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.years();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_months_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.months();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_weekyears_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.weekyears();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_weeks_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.weeks();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_days_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.days();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_halfdays_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.halfdays();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_hours_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.hours();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_minutes_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.minutes();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_seconds_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.seconds();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_millis_5_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DurationFieldType type = DurationFieldType.millis();
        DurationFieldType result = doSerialization(type);
                assertSame(type,result);
    }

}
