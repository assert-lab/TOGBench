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
    public void test_eras() throws Exception {
        assertEquals(DurationFieldType.eras(),DurationFieldType.eras());
        assertEquals("eras",DurationFieldType.eras().getName());
        assertEquals(CopticChronology.getInstanceUTC().eras(),DurationFieldType.eras().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().eras().isSupported(),DurationFieldType.eras().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.eras());
    }

    public void test_centuries() throws Exception {
        assertEquals(DurationFieldType.centuries(),DurationFieldType.centuries());
        assertEquals("centuries",DurationFieldType.centuries().getName());
        assertEquals(CopticChronology.getInstanceUTC().centuries(),DurationFieldType.centuries().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().centuries().isSupported(),DurationFieldType.centuries().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.centuries());
    }

    public void test_years() throws Exception {
        assertEquals(DurationFieldType.years(),DurationFieldType.years());
        assertEquals("years",DurationFieldType.years().getName());
        assertEquals(CopticChronology.getInstanceUTC().years(),DurationFieldType.years().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().years().isSupported(),DurationFieldType.years().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.years());
    }

    public void test_months() throws Exception {
        assertEquals(DurationFieldType.months(),DurationFieldType.months());
        assertEquals("months",DurationFieldType.months().getName());
        assertEquals(CopticChronology.getInstanceUTC().months(),DurationFieldType.months().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().months().isSupported(),DurationFieldType.months().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.months());
    }

    public void test_weekyears() throws Exception {
        assertEquals(DurationFieldType.weekyears(),DurationFieldType.weekyears());
        assertEquals("weekyears",DurationFieldType.weekyears().getName());
        assertEquals(CopticChronology.getInstanceUTC().weekyears(),DurationFieldType.weekyears().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().weekyears().isSupported(),DurationFieldType.weekyears().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.weekyears());
    }

    public void test_weeks() throws Exception {
        assertEquals(DurationFieldType.weeks(),DurationFieldType.weeks());
        assertEquals("weeks",DurationFieldType.weeks().getName());
        assertEquals(CopticChronology.getInstanceUTC().weeks(),DurationFieldType.weeks().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().weeks().isSupported(),DurationFieldType.weeks().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.weeks());
    }

    public void test_days() throws Exception {
        assertEquals(DurationFieldType.days(),DurationFieldType.days());
        assertEquals("days",DurationFieldType.days().getName());
        assertEquals(CopticChronology.getInstanceUTC().days(),DurationFieldType.days().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().days().isSupported(),DurationFieldType.days().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.days());
    }

    public void test_halfdays() throws Exception {
        assertEquals(DurationFieldType.halfdays(),DurationFieldType.halfdays());
        assertEquals("halfdays",DurationFieldType.halfdays().getName());
        assertEquals(CopticChronology.getInstanceUTC().halfdays(),DurationFieldType.halfdays().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().halfdays().isSupported(),DurationFieldType.halfdays().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.halfdays());
    }

    public void test_hours() throws Exception {
        assertEquals(DurationFieldType.hours(),DurationFieldType.hours());
        assertEquals("hours",DurationFieldType.hours().getName());
        assertEquals(CopticChronology.getInstanceUTC().hours(),DurationFieldType.hours().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().hours().isSupported(),DurationFieldType.hours().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.hours());
    }

    public void test_minutes() throws Exception {
        assertEquals(DurationFieldType.minutes(),DurationFieldType.minutes());
        assertEquals("minutes",DurationFieldType.minutes().getName());
        assertEquals(CopticChronology.getInstanceUTC().minutes(),DurationFieldType.minutes().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().minutes().isSupported(),DurationFieldType.minutes().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.minutes());
    }

    public void test_seconds() throws Exception {
        assertEquals(DurationFieldType.seconds(),DurationFieldType.seconds());
        assertEquals("seconds",DurationFieldType.seconds().getName());
        assertEquals(CopticChronology.getInstanceUTC().seconds(),DurationFieldType.seconds().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().seconds().isSupported(),DurationFieldType.seconds().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.seconds());
    }

    public void test_millis() throws Exception {
        assertEquals(DurationFieldType.millis(),DurationFieldType.millis());
        assertEquals("millis",DurationFieldType.millis().getName());
        assertEquals(CopticChronology.getInstanceUTC().millis(),DurationFieldType.millis().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().millis().isSupported(),DurationFieldType.millis().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DurationFieldType.millis());
    }

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

    public void test_eras_1_oe() throws Exception {
        Object a = DurationFieldType.eras();
        assertNotNull(a);
    }

    public void test_eras_2_oe() throws Exception {
        Object a = DurationFieldType.eras().getName();
        assertEquals("Eras", a);
    }

    public void test_eras_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().eras();
        assertNotNull(CopticChronology.getInstanceUTC().eras());
    }

    public void test_eras_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().eras().isSupported();
// incorrect assertion         assertEquals("eras", CopticChronology.ERAS_TYPE.getName());
    }

    public void test_centuries_1_oe() throws Exception {
        Object a = DurationFieldType.centuries();
        assertNotNull(a);
    }

    public void test_centuries_2_oe() throws Exception {
        Object a = DurationFieldType.centuries().getName();
        assertEquals("centuries", a);
    }

    public void test_centuries_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().centuries();
        assertNotNull(CopticChronology.getInstanceUTC().centuries());
    }

    public void test_centuries_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().centuries().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.centuries());
    }

    public void test_years_1_oe() throws Exception {
        Object a = DurationFieldType.years();
        assertNotNull(a);
    }

    public void test_years_2_oe() throws Exception {
        Object a = DurationFieldType.years().getName();
        assertEquals("years", a);
    }

    public void test_years_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().years();
// incorrect assertion         assertNotNull(CopticChronology.years());
    }

    public void test_years_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().years().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.years());
    }

    public void test_months_1_oe() throws Exception {
        Object a = DurationFieldType.months();
        assertNotNull(a);
    }

    public void test_months_2_oe() throws Exception {
        Object a = DurationFieldType.months().getName();
        assertEquals("months", a);
    }

    public void test_months_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().months();
// incorrect assertion         assertNotNull(CopticChronology.months());
    }

    public void test_months_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().months().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.months());
    }

    public void test_weekyears_1_oe() throws Exception {
        Object a = DurationFieldType.weekyears();
        assertNotNull(a);
    }

    public void test_weekyears_2_oe() throws Exception {
        Object a = DurationFieldType.weekyears().getName();
        assertEquals("weekyears", a);
    }

    public void test_weekyears_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().weekyears();
// incorrect assertion         assertNotNull(CopticChronology.weekyears());
    }

    public void test_weekyears_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().weekyears().isSupported();
// incorrect assertion         assertEquals(false, a.isPrecise());
    }

    public void test_weeks_1_oe() throws Exception {
        Object a = DurationFieldType.weeks();
        assertNotNull(a);
    }

    public void test_weeks_2_oe() throws Exception {
        Object a = DurationFieldType.weeks().getName();
        assertEquals("weeks", a);
    }

    public void test_weeks_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().weeks();
        assertNotNull(CopticChronology.getInstanceUTC().weeks());
    }

    public void test_weeks_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().weeks().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.weeks());
    }

    public void test_days_1_oe() throws Exception {
        Object a = DurationFieldType.days();
        assertNotNull(a);
    }

    public void test_days_2_oe() throws Exception {
        Object a = DurationFieldType.days().getName();
        assertEquals("Days", a);
    }

    public void test_days_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().days();
// incorrect assertion         assertNotNull(CopticChronology.days());
    }

    public void test_days_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().days().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.days());
    }

    public void test_halfdays_1_oe() throws Exception {
        Object a = DurationFieldType.halfdays();
        assertNotNull(a);
    }

    public void test_halfdays_2_oe() throws Exception {
        Object a = DurationFieldType.halfdays().getName();
        assertEquals("halfdays", a);
    }

    public void test_halfdays_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().halfdays();
// incorrect assertion         assertEquals("Halfdays", a.getName());
    }

    public void test_halfdays_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().halfdays().isSupported();
// incorrect assertion         assertEquals("Halfdays", a.getName());
    }

    public void test_hours_1_oe() throws Exception {
        Object a = DurationFieldType.hours();
        assertNotNull(a);
    }

    public void test_hours_2_oe() throws Exception {
        Object a = DurationFieldType.hours().getName();
        assertEquals("hours", a);
    }

    public void test_hours_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().hours();
// incorrect assertion         assertNotNull(CopticChronology.hours());
    }

    public void test_hours_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().hours().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.hours());
    }

    public void test_minutes_1_oe() throws Exception {
        Object a = DurationFieldType.minutes();
        assertNotNull(a);
    }

    public void test_minutes_2_oe() throws Exception {
        Object a = DurationFieldType.minutes().getName();
        assertEquals("minutes", a);
    }

    public void test_minutes_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().minutes();
// incorrect assertion         assertNotNull(CopticChronology.minutes());
    }

    public void test_minutes_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().minutes().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.minutes());
    }

    public void test_seconds_1_oe() throws Exception {
        Object a = DurationFieldType.seconds();
        assertNotNull(a);
    }

    public void test_seconds_2_oe() throws Exception {
        Object a = DurationFieldType.seconds().getName();
        assertEquals("seconds", a);
    }

    public void test_seconds_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().seconds();
        assertNotNull(CopticChronology.getInstanceUTC().seconds());
    }

    public void test_seconds_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().seconds().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.seconds());
    }

    public void test_millis_1_oe() throws Exception {
        Object a = DurationFieldType.millis();
        assertNotNull(a);
    }

    public void test_millis_2_oe() throws Exception {
        Object a = DurationFieldType.millis().getName();
        assertEquals("millis", a);
    }

    public void test_millis_3_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().millis();
// incorrect assertion         assertEquals("millis", CopticChronology.millis().getName());
    }

    public void test_millis_4_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().millis().isSupported();
// incorrect assertion         assertEquals("millis", a.getName());
    }

    public void test_other_1_oe() throws Exception {
        int a = 1;
        assertEquals(1, a);
    }

    public void test_other_2_oe() throws Exception {
        Class cls = DurationFieldType.class.getDeclaredClasses()[0];
        assertEquals(false, cls.isLocalClass());
    }

    public void test_other_3_oe() throws Exception {
        Class cls = DurationFieldType.class.getDeclaredClasses()[0];
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {"other", new Byte((byte) 128)};
        DurationFieldType type = (DurationFieldType) con.newInstance(params);
        
        assertEquals("other", type.getName());
    }

    public void test_other_5_oe() throws Exception {
        Class cls = DurationFieldType.class.getDeclaredClasses()[0];
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {"other", new Byte((byte) 128)};
        DurationFieldType type = (DurationFieldType) con.newInstance(params);
        
        try {
            type.getField(CopticChronology.getInstanceUTC());
        } catch (InternalError ex) {}
        DurationFieldType result = doSerialization(type);
        assertEquals("other", result.getName());
    }

    public void test_other_6_oe() throws Exception {
        Class cls = DurationFieldType.class.getDeclaredClasses()[0];
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {"other", new Byte((byte) 128)};
        DurationFieldType type = (DurationFieldType) con.newInstance(params);
        
        try {
            type.getField(CopticChronology.getInstanceUTC());
        } catch (InternalError ex) {}
        DurationFieldType result = doSerialization(type);
        assertNotNull(result);
    }

    public void test_eras_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.eras();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_centuries_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.centuries();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_years_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.years();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_months_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.months();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_weeks_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.weeks();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_days_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.days();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_halfdays_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.halfdays();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_hours_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.hours();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_minutes_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.minutes();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_seconds_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.seconds();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_millis_5_oe_1_oe() throws Exception {
                final DurationFieldType type0 = DurationFieldType.millis();
        DurationFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

}
