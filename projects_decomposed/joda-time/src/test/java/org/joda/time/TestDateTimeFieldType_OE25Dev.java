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
 * This class is a Junit unit test for Chronology.
 *
 * @author Stephen Colebourne
 */
public class TestDateTimeFieldType_OE25Dev extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateTimeFieldType_OE25Dev.class);
    }

    public TestDateTimeFieldType_OE25Dev(String name) {
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
        assertEquals(1,DateTimeFieldType.class.getDeclaredClasses().length);
        Class cls = DateTimeFieldType.class.getDeclaredClasses()[0];
        assertEquals(1,cls.getDeclaredConstructors().length);
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {
            "other", new Byte((byte) 128), DurationFieldType.hours(), DurationFieldType.months()};
        con.setAccessible(true);  // for Apache Harmony JVM
        DateTimeFieldType type = (DateTimeFieldType) con.newInstance(params);
        
        assertEquals("other",type.getName());
        assertSame(DurationFieldType.hours(),type.getDurationType());
        assertSame(DurationFieldType.months(),type.getRangeDurationType());
        try {
            type.getField(CopticChronology.getInstanceUTC());
            fail();
        } catch (InternalError ex) {}
        DateTimeFieldType result = doSerialization(type);
        assertEquals(type.getName(),result.getName());
        assertNotSame(type,result);
    }

    //-----------------------------------------------------------------------
    private void assertSerialization(DateTimeFieldType type) throws Exception {
        DateTimeFieldType result = doSerialization(type);
        assertSame(type,result);
    }

    private DateTimeFieldType doSerialization(DateTimeFieldType type) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(type);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DateTimeFieldType result = (DateTimeFieldType) ois.readObject();
        ois.close();
        return result;
    }

    public void test_era_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.era();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_centuryOfEra_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.centuryOfEra();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_yearOfCentury_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.yearOfCentury();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_yearOfEra_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.yearOfEra();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_year_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.year();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_monthOfYear_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.monthOfYear();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_weekyearOfCentury_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.weekyearOfCentury();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_weekyear_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.weekyear();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_weekOfWeekyear_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.weekOfWeekyear();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_dayOfYear_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.dayOfYear();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_dayOfMonth_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.dayOfMonth();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_dayOfWeek_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.dayOfWeek();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_halfdayOfDay_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.halfdayOfDay();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_clockhourOfDay_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.clockhourOfDay();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_clockhourOfHalfday_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.clockhourOfHalfday();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_hourOfHalfday_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.hourOfHalfday();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_hourOfDay_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.hourOfDay();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_minuteOfDay_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.minuteOfDay();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_minuteOfHour_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.minuteOfHour();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_secondOfDay_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.secondOfDay();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_secondOfMinute_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.secondOfMinute();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_millisOfDay_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.millisOfDay();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

    public void test_millisOfSecond_7_oe_1_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final DateTimeFieldType type = DateTimeFieldType.millisOfSecond();
        DateTimeFieldType result = doSerialization(type);
                assertSame(type,result);
    }

}
