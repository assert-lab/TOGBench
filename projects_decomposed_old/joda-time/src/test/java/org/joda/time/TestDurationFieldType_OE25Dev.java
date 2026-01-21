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

    //-----------------------------------------------------------------------
    private void assertSerialization(DurationFieldType type) throws Exception {
        DurationFieldType result = doSerialization(type);
        assertEquals(type, result);
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

    public void test_eras_1_oe() throws Exception {
        assertEquals(DurationFieldType.eras(), DurationFieldType.eras());
    }

    public void test_eras_2_oe() throws Exception {
        // removed other assertion
        assertEquals("eras", DurationFieldType.eras().getName());
    }

    public void test_eras_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().eras(), DurationFieldType.eras().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_eras_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().eras().isSupported(), DurationFieldType.eras().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_centuries_1_oe() throws Exception {
        assertEquals(DurationFieldType.centuries(), DurationFieldType.centuries());
    }

    public void test_centuries_2_oe() throws Exception {
        // removed other assertion
        assertEquals("centuries", DurationFieldType.centuries().getName());
    }

    public void test_centuries_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().centuries(), DurationFieldType.centuries().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_centuries_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().centuries().isSupported(), DurationFieldType.centuries().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_years_1_oe() throws Exception {
        assertEquals(DurationFieldType.years(), DurationFieldType.years());
    }

    public void test_years_2_oe() throws Exception {
        // removed other assertion
        assertEquals("years", DurationFieldType.years().getName());
    }

    public void test_years_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().years(), DurationFieldType.years().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_years_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().years().isSupported(), DurationFieldType.years().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_months_1_oe() throws Exception {
        assertEquals(DurationFieldType.months(), DurationFieldType.months());
    }

    public void test_months_2_oe() throws Exception {
        // removed other assertion
        assertEquals("months", DurationFieldType.months().getName());
    }

    public void test_months_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().months(), DurationFieldType.months().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_months_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().months().isSupported(), DurationFieldType.months().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_weekyears_1_oe() throws Exception {
        assertEquals(DurationFieldType.weekyears(), DurationFieldType.weekyears());
    }

    public void test_weekyears_2_oe() throws Exception {
        // removed other assertion
        assertEquals("weekyears", DurationFieldType.weekyears().getName());
    }

    public void test_weekyears_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().weekyears(), DurationFieldType.weekyears().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_weekyears_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().weekyears().isSupported(), DurationFieldType.weekyears().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_weeks_1_oe() throws Exception {
        assertEquals(DurationFieldType.weeks(), DurationFieldType.weeks());
    }

    public void test_weeks_2_oe() throws Exception {
        // removed other assertion
        assertEquals("weeks", DurationFieldType.weeks().getName());
    }

    public void test_weeks_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().weeks(), DurationFieldType.weeks().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_weeks_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().weeks().isSupported(), DurationFieldType.weeks().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_days_1_oe() throws Exception {
        assertEquals(DurationFieldType.days(), DurationFieldType.days());
    }

    public void test_days_2_oe() throws Exception {
        // removed other assertion
        assertEquals("days", DurationFieldType.days().getName());
    }

    public void test_days_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().days(), DurationFieldType.days().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_days_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().days().isSupported(), DurationFieldType.days().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_halfdays_1_oe() throws Exception {
        assertEquals(DurationFieldType.halfdays(), DurationFieldType.halfdays());
    }

    public void test_halfdays_2_oe() throws Exception {
        // removed other assertion
        assertEquals("halfdays", DurationFieldType.halfdays().getName());
    }

    public void test_halfdays_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().halfdays(), DurationFieldType.halfdays().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_halfdays_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().halfdays().isSupported(), DurationFieldType.halfdays().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_hours_1_oe() throws Exception {
        assertEquals(DurationFieldType.hours(), DurationFieldType.hours());
    }

    public void test_hours_2_oe() throws Exception {
        // removed other assertion
        assertEquals("hours", DurationFieldType.hours().getName());
    }

    public void test_hours_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().hours(), DurationFieldType.hours().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_hours_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().hours().isSupported(), DurationFieldType.hours().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_minutes_1_oe() throws Exception {
        assertEquals(DurationFieldType.minutes(), DurationFieldType.minutes());
    }

    public void test_minutes_2_oe() throws Exception {
        // removed other assertion
        assertEquals("minutes", DurationFieldType.minutes().getName());
    }

    public void test_minutes_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().minutes(), DurationFieldType.minutes().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_minutes_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().minutes().isSupported(), DurationFieldType.minutes().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_seconds_1_oe() throws Exception {
        assertEquals(DurationFieldType.seconds(), DurationFieldType.seconds());
    }

    public void test_seconds_2_oe() throws Exception {
        // removed other assertion
        assertEquals("seconds", DurationFieldType.seconds().getName());
    }

    public void test_seconds_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().seconds(), DurationFieldType.seconds().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_seconds_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().seconds().isSupported(), DurationFieldType.seconds().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_millis_1_oe() throws Exception {
        assertEquals(DurationFieldType.millis(), DurationFieldType.millis());
    }

    public void test_millis_2_oe() throws Exception {
        // removed other assertion
        assertEquals("millis", DurationFieldType.millis().getName());
    }

    public void test_millis_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().millis(), DurationFieldType.millis().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_millis_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().millis().isSupported(), DurationFieldType.millis().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_other_1_oe() throws Exception {
        assertEquals(1, DurationFieldType.class.getDeclaredClasses().length);
    }

    public void test_other_2_oe() throws Exception {
        // removed other assertion
        Class cls = DurationFieldType.class.getDeclaredClasses()[0];
        assertEquals(1, cls.getDeclaredConstructors().length);
    }

    public void test_other_3_oe() throws Exception {
        // removed other assertion
        Class cls = DurationFieldType.class.getDeclaredClasses()[0];
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {"other", new Byte((byte) 128)};
        DurationFieldType type = (DurationFieldType) con.newInstance(params);
        
        assertEquals("other", type.getName());
    }

    public void test_other_5_oe() throws Exception {
        // removed other assertion
        Class cls = DurationFieldType.class.getDeclaredClasses()[0];
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {"other", new Byte((byte) 128)};
        DurationFieldType type = (DurationFieldType) con.newInstance(params);
        
        // removed other assertion
        try {
            type.getField(CopticChronology.getInstanceUTC());
            // removed other assertion
        } catch (InternalError ex) {}
        DurationFieldType result = doSerialization(type);
        assertEquals(type.getName(), result.getName());
    }

    public void test_other_6_oe() throws Exception {
        // removed other assertion
        Class cls = DurationFieldType.class.getDeclaredClasses()[0];
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {"other", new Byte((byte) 128)};
        DurationFieldType type = (DurationFieldType) con.newInstance(params);
        
        // removed other assertion
        try {
            type.getField(CopticChronology.getInstanceUTC());
            // removed other assertion
        } catch (InternalError ex) {}
        DurationFieldType result = doSerialization(type);
        // removed other assertion
        assertNotSame(type, result);
    }

}
