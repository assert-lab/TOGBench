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
    public void test_era() throws Exception {
        assertEquals(DateTimeFieldType.era(),DateTimeFieldType.era());
        assertEquals("era",DateTimeFieldType.era().getName());
        assertEquals(DurationFieldType.eras(),DateTimeFieldType.era().getDurationType());
        assertEquals(null,DateTimeFieldType.era().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().era(),DateTimeFieldType.era().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().era().isSupported(),DateTimeFieldType.era().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.era());
    }

    public void test_centuryOfEra() throws Exception {
        assertEquals(DateTimeFieldType.centuryOfEra(),DateTimeFieldType.centuryOfEra());
        assertEquals("centuryOfEra",DateTimeFieldType.centuryOfEra().getName());
        assertEquals(DurationFieldType.centuries(),DateTimeFieldType.centuryOfEra().getDurationType());
        assertEquals(DurationFieldType.eras(),DateTimeFieldType.centuryOfEra().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().centuryOfEra(),DateTimeFieldType.centuryOfEra().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().centuryOfEra().isSupported(),DateTimeFieldType.centuryOfEra().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.centuryOfEra());
    }

    public void test_yearOfCentury() throws Exception {
        assertEquals(DateTimeFieldType.yearOfCentury(),DateTimeFieldType.yearOfCentury());
        assertEquals("yearOfCentury",DateTimeFieldType.yearOfCentury().getName());
        assertEquals(DurationFieldType.years(),DateTimeFieldType.yearOfCentury().getDurationType());
        assertEquals(DurationFieldType.centuries(),DateTimeFieldType.yearOfCentury().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().yearOfCentury(),DateTimeFieldType.yearOfCentury().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().yearOfCentury().isSupported(),DateTimeFieldType.yearOfCentury().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.yearOfCentury());
    }

    public void test_yearOfEra() throws Exception {
        assertEquals(DateTimeFieldType.yearOfEra(),DateTimeFieldType.yearOfEra());
        assertEquals("yearOfEra",DateTimeFieldType.yearOfEra().getName());
        assertEquals(DurationFieldType.years(),DateTimeFieldType.yearOfEra().getDurationType());
        assertEquals(DurationFieldType.eras(),DateTimeFieldType.yearOfEra().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().yearOfEra(),DateTimeFieldType.yearOfEra().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().yearOfEra().isSupported(),DateTimeFieldType.yearOfEra().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.yearOfEra());
    }

    public void test_year() throws Exception {
        assertEquals(DateTimeFieldType.year(),DateTimeFieldType.year());
        assertEquals("year",DateTimeFieldType.year().getName());
        assertEquals(DurationFieldType.years(),DateTimeFieldType.year().getDurationType());
        assertEquals(null,DateTimeFieldType.year().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().year(),DateTimeFieldType.year().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().year().isSupported(),DateTimeFieldType.year().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.year());
    }

    public void test_monthOfYear() throws Exception {
        assertEquals(DateTimeFieldType.monthOfYear(),DateTimeFieldType.monthOfYear());
        assertEquals("monthOfYear",DateTimeFieldType.monthOfYear().getName());
        assertEquals(DurationFieldType.months(),DateTimeFieldType.monthOfYear().getDurationType());
        assertEquals(DurationFieldType.years(),DateTimeFieldType.monthOfYear().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().monthOfYear(),DateTimeFieldType.monthOfYear().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().monthOfYear().isSupported(),DateTimeFieldType.monthOfYear().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.monthOfYear());
    }

    public void test_weekyearOfCentury() throws Exception {
        assertEquals(DateTimeFieldType.weekyearOfCentury(),DateTimeFieldType.weekyearOfCentury());
        assertEquals("weekyearOfCentury",DateTimeFieldType.weekyearOfCentury().getName());
        assertEquals(DurationFieldType.weekyears(),DateTimeFieldType.weekyearOfCentury().getDurationType());
        assertEquals(DurationFieldType.centuries(),DateTimeFieldType.weekyearOfCentury().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().weekyearOfCentury(),DateTimeFieldType.weekyearOfCentury().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().weekyearOfCentury().isSupported(),DateTimeFieldType.weekyearOfCentury().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.weekyearOfCentury());
    }

    public void test_weekyear() throws Exception {
        assertEquals(DateTimeFieldType.weekyear(),DateTimeFieldType.weekyear());
        assertEquals("weekyear",DateTimeFieldType.weekyear().getName());
        assertEquals(DurationFieldType.weekyears(),DateTimeFieldType.weekyear().getDurationType());
        assertEquals(null,DateTimeFieldType.weekyear().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().weekyear(),DateTimeFieldType.weekyear().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().weekyear().isSupported(),DateTimeFieldType.weekyear().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.weekyear());
    }

    public void test_weekOfWeekyear() throws Exception {
        assertEquals(DateTimeFieldType.weekOfWeekyear(),DateTimeFieldType.weekOfWeekyear());
        assertEquals("weekOfWeekyear",DateTimeFieldType.weekOfWeekyear().getName());
        assertEquals(DurationFieldType.weeks(),DateTimeFieldType.weekOfWeekyear().getDurationType());
        assertEquals(DurationFieldType.weekyears(),DateTimeFieldType.weekOfWeekyear().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().weekOfWeekyear(),DateTimeFieldType.weekOfWeekyear().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().weekOfWeekyear().isSupported(),DateTimeFieldType.weekOfWeekyear().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.weekOfWeekyear());
    }

    public void test_dayOfYear() throws Exception {
        assertEquals(DateTimeFieldType.dayOfYear(),DateTimeFieldType.dayOfYear());
        assertEquals("dayOfYear",DateTimeFieldType.dayOfYear().getName());
        assertEquals(DurationFieldType.days(),DateTimeFieldType.dayOfYear().getDurationType());
        assertEquals(DurationFieldType.years(),DateTimeFieldType.dayOfYear().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().dayOfYear(),DateTimeFieldType.dayOfYear().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().dayOfYear().isSupported(),DateTimeFieldType.dayOfYear().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.dayOfYear());
    }

    public void test_dayOfMonth() throws Exception {
        assertEquals(DateTimeFieldType.dayOfMonth(),DateTimeFieldType.dayOfMonth());
        assertEquals("dayOfMonth",DateTimeFieldType.dayOfMonth().getName());
        assertEquals(DurationFieldType.days(),DateTimeFieldType.dayOfMonth().getDurationType());
        assertEquals(DurationFieldType.months(),DateTimeFieldType.dayOfMonth().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().dayOfMonth(),DateTimeFieldType.dayOfMonth().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().dayOfMonth().isSupported(),DateTimeFieldType.dayOfMonth().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.dayOfMonth());
    }

    public void test_dayOfWeek() throws Exception {
        assertEquals(DateTimeFieldType.dayOfWeek(),DateTimeFieldType.dayOfWeek());
        assertEquals("dayOfWeek",DateTimeFieldType.dayOfWeek().getName());
        assertEquals(DurationFieldType.days(),DateTimeFieldType.dayOfWeek().getDurationType());
        assertEquals(DurationFieldType.weeks(),DateTimeFieldType.dayOfWeek().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().dayOfWeek(),DateTimeFieldType.dayOfWeek().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().dayOfWeek().isSupported(),DateTimeFieldType.dayOfWeek().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.dayOfWeek());
    }

    public void test_halfdayOfDay() throws Exception {
        assertEquals(DateTimeFieldType.halfdayOfDay(),DateTimeFieldType.halfdayOfDay());
        assertEquals("halfdayOfDay",DateTimeFieldType.halfdayOfDay().getName());
        assertEquals(DurationFieldType.halfdays(),DateTimeFieldType.halfdayOfDay().getDurationType());
        assertEquals(DurationFieldType.days(),DateTimeFieldType.halfdayOfDay().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().halfdayOfDay(),DateTimeFieldType.halfdayOfDay().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().halfdayOfDay().isSupported(),DateTimeFieldType.halfdayOfDay().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.halfdayOfDay());
    }

    public void test_clockhourOfDay() throws Exception {
        assertEquals(DateTimeFieldType.clockhourOfDay(),DateTimeFieldType.clockhourOfDay());
        assertEquals("clockhourOfDay",DateTimeFieldType.clockhourOfDay().getName());
        assertEquals(DurationFieldType.hours(),DateTimeFieldType.clockhourOfDay().getDurationType());
        assertEquals(DurationFieldType.days(),DateTimeFieldType.clockhourOfDay().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().clockhourOfDay(),DateTimeFieldType.clockhourOfDay().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().clockhourOfDay().isSupported(),DateTimeFieldType.clockhourOfDay().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.clockhourOfDay());
    }

    public void test_clockhourOfHalfday() throws Exception {
        assertEquals(DateTimeFieldType.clockhourOfHalfday(),DateTimeFieldType.clockhourOfHalfday());
        assertEquals("clockhourOfHalfday",DateTimeFieldType.clockhourOfHalfday().getName());
        assertEquals(DurationFieldType.hours(),DateTimeFieldType.clockhourOfHalfday().getDurationType());
        assertEquals(DurationFieldType.halfdays(),DateTimeFieldType.clockhourOfHalfday().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().clockhourOfHalfday(),DateTimeFieldType.clockhourOfHalfday().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().clockhourOfHalfday().isSupported(),DateTimeFieldType.clockhourOfHalfday().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.clockhourOfHalfday());
    }

    public void test_hourOfHalfday() throws Exception {
        assertEquals(DateTimeFieldType.hourOfHalfday(),DateTimeFieldType.hourOfHalfday());
        assertEquals("hourOfHalfday",DateTimeFieldType.hourOfHalfday().getName());
        assertEquals(DurationFieldType.hours(),DateTimeFieldType.hourOfHalfday().getDurationType());
        assertEquals(DurationFieldType.halfdays(),DateTimeFieldType.hourOfHalfday().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().hourOfHalfday(),DateTimeFieldType.hourOfHalfday().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().hourOfHalfday().isSupported(),DateTimeFieldType.hourOfHalfday().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.hourOfHalfday());
    }

    public void test_hourOfDay() throws Exception {
        assertEquals(DateTimeFieldType.hourOfDay(),DateTimeFieldType.hourOfDay());
        assertEquals("hourOfDay",DateTimeFieldType.hourOfDay().getName());
        assertEquals(DurationFieldType.hours(),DateTimeFieldType.hourOfDay().getDurationType());
        assertEquals(DurationFieldType.days(),DateTimeFieldType.hourOfDay().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().hourOfDay(),DateTimeFieldType.hourOfDay().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().hourOfDay().isSupported(),DateTimeFieldType.hourOfDay().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.hourOfDay());
    }

    public void test_minuteOfDay() throws Exception {
        assertEquals(DateTimeFieldType.minuteOfDay(),DateTimeFieldType.minuteOfDay());
        assertEquals("minuteOfDay",DateTimeFieldType.minuteOfDay().getName());
        assertEquals(DurationFieldType.minutes(),DateTimeFieldType.minuteOfDay().getDurationType());
        assertEquals(DurationFieldType.days(),DateTimeFieldType.minuteOfDay().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().minuteOfDay(),DateTimeFieldType.minuteOfDay().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().minuteOfDay().isSupported(),DateTimeFieldType.minuteOfDay().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.minuteOfDay());
    }

    public void test_minuteOfHour() throws Exception {
        assertEquals(DateTimeFieldType.minuteOfHour(),DateTimeFieldType.minuteOfHour());
        assertEquals("minuteOfHour",DateTimeFieldType.minuteOfHour().getName());
        assertEquals(DurationFieldType.minutes(),DateTimeFieldType.minuteOfHour().getDurationType());
        assertEquals(DurationFieldType.hours(),DateTimeFieldType.minuteOfHour().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().minuteOfHour(),DateTimeFieldType.minuteOfHour().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().minuteOfHour().isSupported(),DateTimeFieldType.minuteOfHour().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.minuteOfHour());
    }

    public void test_secondOfDay() throws Exception {
        assertEquals(DateTimeFieldType.secondOfDay(),DateTimeFieldType.secondOfDay());
        assertEquals("secondOfDay",DateTimeFieldType.secondOfDay().getName());
        assertEquals(DurationFieldType.seconds(),DateTimeFieldType.secondOfDay().getDurationType());
        assertEquals(DurationFieldType.days(),DateTimeFieldType.secondOfDay().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().secondOfDay(),DateTimeFieldType.secondOfDay().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().secondOfDay().isSupported(),DateTimeFieldType.secondOfDay().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.secondOfDay());
    }

    public void test_secondOfMinute() throws Exception {
        assertEquals(DateTimeFieldType.secondOfMinute(),DateTimeFieldType.secondOfMinute());
        assertEquals("secondOfMinute",DateTimeFieldType.secondOfMinute().getName());
        assertEquals(DurationFieldType.seconds(),DateTimeFieldType.secondOfMinute().getDurationType());
        assertEquals(DurationFieldType.minutes(),DateTimeFieldType.secondOfMinute().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().secondOfMinute(),DateTimeFieldType.secondOfMinute().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().secondOfMinute().isSupported(),DateTimeFieldType.secondOfMinute().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.secondOfMinute());
    }

    public void test_millisOfDay() throws Exception {
        assertEquals(DateTimeFieldType.millisOfDay(),DateTimeFieldType.millisOfDay());
        assertEquals("millisOfDay",DateTimeFieldType.millisOfDay().getName());
        assertEquals(DurationFieldType.millis(),DateTimeFieldType.millisOfDay().getDurationType());
        assertEquals(DurationFieldType.days(),DateTimeFieldType.millisOfDay().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().millisOfDay(),DateTimeFieldType.millisOfDay().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().millisOfDay().isSupported(),DateTimeFieldType.millisOfDay().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.millisOfDay());
    }

    public void test_millisOfSecond() throws Exception {
        assertEquals(DateTimeFieldType.millisOfSecond(),DateTimeFieldType.millisOfSecond());
        assertEquals("millisOfSecond",DateTimeFieldType.millisOfSecond().getName());
        assertEquals(DurationFieldType.millis(),DateTimeFieldType.millisOfSecond().getDurationType());
        assertEquals(DurationFieldType.seconds(),DateTimeFieldType.millisOfSecond().getRangeDurationType());
        assertEquals(CopticChronology.getInstanceUTC().millisOfSecond(),DateTimeFieldType.millisOfSecond().getField(CopticChronology.getInstanceUTC()));
        assertEquals(CopticChronology.getInstanceUTC().millisOfSecond().isSupported(),DateTimeFieldType.millisOfSecond().isSupported(CopticChronology.getInstanceUTC()));
        assertSerialization(DateTimeFieldType.millisOfSecond());
    }

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

    public void test_era_1_oe() throws Exception {
        Object a = DateTimeFieldType.era();
        assertNotNull(a);
    }

    public void test_era_2_oe() throws Exception {
        Object a = DateTimeFieldType.era().getName();
        assertEquals("era", a);
    }

    public void test_era_3_oe() throws Exception {
        Object a = DurationFieldType.eras();
// incorrect assertion         assertEquals(1, DurationFieldType.eras().ordinal());
    }

    public void test_era_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().era();
        assertNotNull(a);
    }

    public void test_era_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().era().isSupported();
// incorrect assertion         assertEquals("ERA", CopticChronology.era().getName());
    }

    public void test_centuryOfEra_1_oe() throws Exception {
        Object a = DateTimeFieldType.centuryOfEra();
        assertNotNull(a);
    }

    public void test_centuryOfEra_2_oe() throws Exception {
        Object a = DateTimeFieldType.centuryOfEra().getName();
        assertEquals("centuryOfEra", a.toString());
    }

    public void test_centuryOfEra_3_oe() throws Exception {
        Object a = DurationFieldType.centuries();
// incorrect assertion         assertEquals("centuryOfEra", DurationFieldType.centuryOfEra().getName());
    }

    public void test_centuryOfEra_4_oe() throws Exception {
        Object a = DurationFieldType.eras();
// incorrect assertion         assertEquals("centuryOfEra", DurationFieldType.centuryOfEra().getName());
    }

    public void test_centuryOfEra_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().centuryOfEra();
// incorrect assertion         assertEquals("centuryOfEra", CopticChronology.centuryOfEra().getName());
    }

    public void test_centuryOfEra_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().centuryOfEra().isSupported();
// incorrect assertion         assertEquals("centuryOfEra", CopticChronology.centuryOfEra().getName());
    }

    public void test_yearOfCentury_1_oe() throws Exception {
        Object a = DateTimeFieldType.yearOfCentury();
        assertNotNull(DateTimeFieldType.yearOfCentury());
    }

    public void test_yearOfCentury_2_oe() throws Exception {
        Object a = DateTimeFieldType.yearOfCentury().getName();
        assertEquals("yearOfCentury", a);
    }

    public void test_yearOfCentury_3_oe() throws Exception {
        Object a = DurationFieldType.years();
// incorrect assertion         assertEquals("yearOfCentury", DurationFieldType.yearOfCentury().getName());
    }

    public void test_yearOfCentury_4_oe() throws Exception {
        Object a = DurationFieldType.centuries();
// incorrect assertion         assertEquals(DateTimeFieldType.YEAR_OF_CENTURY, DurationFieldType.yearOfCentury());
    }

    public void test_yearOfCentury_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().yearOfCentury();
// incorrect assertion         assertNotNull(CopticChronology.yearOfCentury());
    }

    public void test_yearOfCentury_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().yearOfCentury().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.yearOfCentury());
    }

    public void test_yearOfEra_1_oe() throws Exception {
        Object a = DateTimeFieldType.yearOfEra();
        assertNotNull(DateTimeFieldType.yearOfEra());
    }

    public void test_yearOfEra_2_oe() throws Exception {
        Object a = DateTimeFieldType.yearOfEra().getName();
        assertEquals("yearOfEra", a);
    }

    public void test_yearOfEra_3_oe() throws Exception {
        Object a = DurationFieldType.years();
// incorrect assertion         assertEquals(1, DurationFieldType.years().ordinal());
    }

    public void test_yearOfEra_4_oe() throws Exception {
        Object a = DurationFieldType.eras();
// incorrect assertion         assertEquals(DateTimeFieldType.YEAR_OF_ERA, DurationFieldType.yearOfEra());
    }

    public void test_yearOfEra_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().yearOfEra();
// incorrect assertion         assertEquals("YearOfEra", CopticChronology.yearOfEra().getName());
    }

    public void test_yearOfEra_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().yearOfEra().isSupported();
// incorrect assertion         assertEquals("YearOfEra", CopticChronology.yearOfEra().getName());
    }

    public void test_year_1_oe() throws Exception {
        Object a = DateTimeFieldType.year();
        assertNotNull(DateTimeFieldType.year());
    }

    public void test_year_2_oe() throws Exception {
        Object a = DateTimeFieldType.year().getName();
        assertEquals("year", a);
    }

    public void test_year_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().year();
// incorrect assertion         assertNotNull(CopticChronology.year());
    }

    public void test_year_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().year().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.year());
    }

    public void test_monthOfYear_1_oe() throws Exception {
        Object a = DateTimeFieldType.monthOfYear();
        assertNotNull(DateTimeFieldType.monthOfYear());
    }

    public void test_monthOfYear_2_oe() throws Exception {
        Object a = DateTimeFieldType.monthOfYear().getName();
        assertEquals("monthOfYear", a);
    }

    public void test_monthOfYear_3_oe() throws Exception {
        Object a = DurationFieldType.months();
// incorrect assertion         assertEquals("MonthOfYear", DurationFieldType.monthOfYear().getName());
    }

    public void test_monthOfYear_4_oe() throws Exception {
        Object a = DurationFieldType.years();
// incorrect assertion         assertEquals("MonthOfYear", DurationFieldType.monthOfYear().getName());
    }

    public void test_monthOfYear_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().monthOfYear();
// incorrect assertion         assertNotNull(CopticChronology.monthOfYear());
    }

    public void test_monthOfYear_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().monthOfYear().isSupported();
        assertEquals(false, a);
    }

    public void test_weekyearOfCentury_1_oe() throws Exception {
        Object a = DateTimeFieldType.weekyearOfCentury();
        assertNotNull(DateTimeFieldType.weekyearOfCentury());
    }

    public void test_weekyearOfCentury_2_oe() throws Exception {
        Object a = DateTimeFieldType.weekyearOfCentury().getName();
        assertEquals("weekyearOfCentury", a);
    }

    public void test_weekyearOfCentury_3_oe() throws Exception {
        Object a = DurationFieldType.weekyears();
// incorrect assertion         assertEquals("weekyearOfCentury", DurationFieldType.weekyearOfCentury().getName());
    }

    public void test_weekyearOfCentury_4_oe() throws Exception {
        Object a = DurationFieldType.centuries();
// incorrect assertion         assertEquals("WeekyearOfCentury", DurationFieldType.weekyearOfCentury().getName());
    }

    public void test_weekyearOfCentury_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().weekyearOfCentury();
// incorrect assertion         assertNotNull(CopticChronology.weekyearOfCentury());
    }

    public void test_weekyearOfCentury_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().weekyearOfCentury().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.weekyearOfCentury());
    }

    public void test_weekyear_2_oe() throws Exception {
        Object a = DateTimeFieldType.weekyear().getName();
        assertEquals("weekyear", a);
    }

    public void test_weekyear_3_oe() throws Exception {
        Object a = DurationFieldType.weekyears();
// incorrect assertion         assertEquals("weekyear", DurationFieldType.weekyear().getName());
    }

    public void test_weekyear_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().weekyear();
        assertEquals("weekyear", CopticChronology.getInstanceUTC().weekyear().getName());
    }

    public void test_weekyear_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().weekyear().isSupported();
        assertEquals(false, a);
    }

    public void test_weekOfWeekyear_1_oe() throws Exception {
        Object a = DateTimeFieldType.weekOfWeekyear();
        assertNotNull(DateTimeFieldType.weekOfWeekyear());
    }

    public void test_weekOfWeekyear_2_oe() throws Exception {
        Object a = DateTimeFieldType.weekOfWeekyear().getName();
        assertEquals("weekOfWeekyear", a);
    }

    public void test_weekOfWeekyear_4_oe() throws Exception {
        Object a = DurationFieldType.weekyears();
// incorrect assertion         assertEquals("weekOfWeekyear", DurationFieldType.weekOfWeekyear().getName());
    }

    public void test_weekOfWeekyear_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().weekOfWeekyear();
// incorrect assertion         assertNotNull(CopticChronology.weekOfWeekyear());
    }

    public void test_weekOfWeekyear_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().weekOfWeekyear().isSupported();
// incorrect assertion         assertEquals("WeekOfWeekyear", CopticChronology.weekOfWeekyear().getName());
    }

    public void test_dayOfYear_1_oe() throws Exception {
        Object a = DateTimeFieldType.dayOfYear();
// incorrect assertion         assertNotNull(dayOfYear());
    }

    public void test_dayOfYear_2_oe() throws Exception {
        Object a = DateTimeFieldType.dayOfYear().getName();
        assertEquals("dayOfYear", a);
    }

    public void test_dayOfYear_3_oe() throws Exception {
        Object a = DurationFieldType.days();
// incorrect assertion         assertEquals("DayOfYear", DurationFieldType.dayOfYear().getName());
    }

    public void test_dayOfYear_4_oe() throws Exception {
        Object a = DurationFieldType.years();
// incorrect assertion         assertEquals("DayOfYear", DurationFieldType.dayOfYear().getName());
    }

    public void test_dayOfYear_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().dayOfYear();
// incorrect assertion         assertNotNull(CopticChronology.dayOfYear());
    }

    public void test_dayOfYear_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().dayOfYear().isSupported();
        assertEquals(false, a);
    }

    public void test_dayOfMonth_1_oe() throws Exception {
        Object a = DateTimeFieldType.dayOfMonth();
        assertNotNull(DateTimeFieldType.dayOfMonth());
    }

    public void test_dayOfMonth_2_oe() throws Exception {
        Object a = DateTimeFieldType.dayOfMonth().getName();
        assertEquals("dayOfMonth", a);
    }

    public void test_dayOfMonth_3_oe() throws Exception {
        Object a = DurationFieldType.days();
// incorrect assertion         assertEquals("DayOfMonth", DurationFieldType.dayOfMonth().getName());
    }

    public void test_dayOfMonth_4_oe() throws Exception {
        Object a = DurationFieldType.months();
// incorrect assertion         assertEquals("DayOfMonth", DurationFieldType.dayOfMonth().getName());
    }

    public void test_dayOfMonth_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().dayOfMonth();
// incorrect assertion         assertNotNull(CopticChronology.dayOfMonth());
    }

    public void test_dayOfMonth_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().dayOfMonth().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.dayOfMonth());
    }

    public void test_dayOfWeek_1_oe() throws Exception {
        Object a = DateTimeFieldType.dayOfWeek();
        assertNotNull(DateTimeFieldType.dayOfWeek());
    }

    public void test_dayOfWeek_2_oe() throws Exception {
        Object a = DateTimeFieldType.dayOfWeek().getName();
        assertEquals("dayOfWeek", a);
    }

    public void test_dayOfWeek_3_oe() throws Exception {
        Object a = DurationFieldType.days();
// incorrect assertion         assertEquals(DAY_OF_WEEK_TYPE, DurationFieldType.dayOfWeek());
    }

    public void test_dayOfWeek_4_oe() throws Exception {
        Object a = DurationFieldType.weeks();
// incorrect assertion         assertEquals(DateTimeFieldType.DayOfWeek, DurationFieldType.dayOfWeek());
    }

    public void test_dayOfWeek_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().dayOfWeek();
// incorrect assertion         assertNotNull(CopticChronology.dayOfWeek());
    }

    public void test_dayOfWeek_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().dayOfWeek().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.dayOfWeek());
    }

    public void test_halfdayOfDay_1_oe() throws Exception {
        Object a = DateTimeFieldType.halfdayOfDay();
        assertNotNull(a);
    }

    public void test_halfdayOfDay_2_oe() throws Exception {
        Object a = DateTimeFieldType.halfdayOfDay().getName();
        assertEquals("halfdayOfDay", a);
    }

    public void test_halfdayOfDay_3_oe() throws Exception {
        Object a = DurationFieldType.halfdays();
// incorrect assertion         assertNotNull(DurationFieldType.halfdayOfDay());
    }

    public void test_halfdayOfDay_4_oe() throws Exception {
        Object a = DurationFieldType.days();
// incorrect assertion         assertEquals(DateTimeFieldType.HALFDAY_OF_DAY, DurationFieldType.halfdayOfDay());
    }

    public void test_halfdayOfDay_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().halfdayOfDay();
        assertNotNull(a);
    }

    public void test_halfdayOfDay_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().halfdayOfDay().isSupported();
        assertNotNull(a);
    }

    public void test_clockhourOfDay_1_oe() throws Exception {
        Object a = DateTimeFieldType.clockhourOfDay();
        assertNotNull(a);
    }

    public void test_clockhourOfDay_2_oe() throws Exception {
        Object a = DateTimeFieldType.clockhourOfDay().getName();
        assertEquals("clockhourOfDay", a);
    }

    public void test_clockhourOfDay_4_oe() throws Exception {
        Object a = DurationFieldType.days();
// incorrect assertion         assertEquals("clockhour_of_day", DurationFieldType.clockhourOfDay().getName());
    }

    public void test_clockhourOfDay_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().clockhourOfDay();
        assertEquals("clockhour_of_day", CopticChronology.getInstanceUTC().clockhourOfDay().getName());
    }

    public void test_clockhourOfDay_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().clockhourOfDay().isSupported();
        assertEquals(false, a);
    }

    public void test_clockhourOfHalfday_1_oe() throws Exception {
        Object a = DateTimeFieldType.clockhourOfHalfday();
        assertNotNull(DateTimeFieldType.clockhourOfHalfday());
    }

    public void test_clockhourOfHalfday_2_oe() throws Exception {
        Object a = DateTimeFieldType.clockhourOfHalfday().getName();
        assertEquals("clockhourOfHalfday", a.toString());
    }

    public void test_clockhourOfHalfday_3_oe() throws Exception {
        Object a = DurationFieldType.hours();
// incorrect assertion         assertEquals("clockhour_of_halfday", DurationFieldType.clockhourOfHalfday().getName());
    }

    public void test_clockhourOfHalfday_4_oe() throws Exception {
        Object a = DurationFieldType.halfdays();
// incorrect assertion         assertEquals(DateTimeFieldType.CLOCKHOUR_OF_HALFDAY, DurationFieldType.clockhourOfHalfday());
    }

    public void test_clockhourOfHalfday_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().clockhourOfHalfday();
        assertEquals("clockhour_of_halfday", CopticChronology.getInstanceUTC().clockhourOfHalfday().getName());
    }

    public void test_clockhourOfHalfday_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().clockhourOfHalfday().isSupported();
        assertEquals("clockhour_of_halfday", CopticChronology.getInstanceUTC().clockhourOfHalfday().getName());
    }

    public void test_hourOfHalfday_1_oe() throws Exception {
        Object a = DateTimeFieldType.hourOfHalfday();
        assertNotNull(DateTimeFieldType.hourOfHalfday());
    }

    public void test_hourOfHalfday_2_oe() throws Exception {
        Object a = DateTimeFieldType.hourOfHalfday().getName();
        assertEquals("hourOfHalfday", a.toString());
    }

    public void test_hourOfHalfday_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().hourOfHalfday();
// incorrect assertion         assertNotNull(CopticChronology.hourOfHalfday());
    }

    public void test_hourOfHalfday_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().hourOfHalfday().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.hourOfHalfday());
    }

    public void test_hourOfDay_1_oe() throws Exception {
        Object a = DateTimeFieldType.hourOfDay();
        assertNotNull(DateTimeFieldType.hourOfDay());
    }

    public void test_hourOfDay_2_oe() throws Exception {
        Object a = DateTimeFieldType.hourOfDay().getName();
        assertEquals("hourOfDay", a);
    }

    public void test_hourOfDay_3_oe() throws Exception {
        Object a = DurationFieldType.hours();
// incorrect assertion         assertNotNull(DurationFieldType.hourOfDay());
    }

    public void test_hourOfDay_4_oe() throws Exception {
        Object a = DurationFieldType.days();
// incorrect assertion         assertEquals(6, DurationFieldType.hourOfDay().ordinal());
    }

    public void test_hourOfDay_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().hourOfDay();
// incorrect assertion         assertNotNull(CopticChronology.hourOfDay());
    }

    public void test_hourOfDay_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().hourOfDay().isSupported();
// incorrect assertion         assertEquals("HourOfDay", CopticChronology.HOUR_OF_DAY_TYPE.getName());
    }

    public void test_minuteOfDay_1_oe() throws Exception {
        Object a = DateTimeFieldType.minuteOfDay();
        assertNotNull(DateTimeFieldType.minuteOfDay());
    }

    public void test_minuteOfDay_2_oe() throws Exception {
        Object a = DateTimeFieldType.minuteOfDay().getName();
        assertEquals("minuteOfDay", a);
    }

    public void test_minuteOfDay_3_oe() throws Exception {
        Object a = DurationFieldType.minutes();
// incorrect assertion         assertNotNull(DurationFieldType.minuteOfDay());
    }

    public void test_minuteOfDay_4_oe() throws Exception {
        Object a = DurationFieldType.days();
// incorrect assertion         assertEquals("MinuteOfDay", DurationFieldType.minuteOfDay().getName());
    }

    public void test_minuteOfDay_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().minuteOfDay();
// incorrect assertion         assertEquals("MinuteOfDay", CopticChronology.minuteOfDay().getName());
    }

    public void test_minuteOfDay_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().minuteOfDay().isSupported();
        assertEquals(false, a);
    }

    public void test_minuteOfHour_1_oe() throws Exception {
        Object a = DateTimeFieldType.minuteOfHour();
        assertNotNull(DateTimeFieldType.minuteOfHour());
    }

    public void test_minuteOfHour_2_oe() throws Exception {
        Object a = DateTimeFieldType.minuteOfHour().getName();
        assertEquals("minuteOfHour", a);
    }

    public void test_minuteOfHour_3_oe() throws Exception {
        Object a = DurationFieldType.minutes();
// incorrect assertion         assertNotNull(DurationFieldType.minuteOfHour());
    }

    public void test_minuteOfHour_4_oe() throws Exception {
        Object a = DurationFieldType.hours();
// incorrect assertion         assertEquals("MinuteOfHour", DurationFieldType.minuteOfHour().getName());
    }

    public void test_minuteOfHour_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().minuteOfHour();
// incorrect assertion         assertNotNull(CopticChronology.minuteOfHour());
    }

    public void test_minuteOfHour_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().minuteOfHour().isSupported();
// incorrect assertion         assertNotNull(CopticChronology.minuteOfHour());
    }

    public void test_secondOfDay_1_oe() throws Exception {
        Object a = DateTimeFieldType.secondOfDay();
        assertNotNull(DateTimeFieldType.secondOfDay());
    }

    public void test_secondOfDay_2_oe() throws Exception {
        Object a = DateTimeFieldType.secondOfDay().getName();
        assertEquals("secondOfDay", a.toString());
    }

    public void test_secondOfDay_3_oe() throws Exception {
        Object a = DurationFieldType.seconds();
// incorrect assertion         assertNotNull(DurationFieldType.secondOfDay());
    }

    public void test_secondOfDay_4_oe() throws Exception {
        Object a = DurationFieldType.days();
// incorrect assertion         assertEquals("secondOfDay", DurationFieldType.secondOfDay().getName());
    }

    public void test_secondOfDay_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().secondOfDay();
        assertNotNull(a);
    }

    public void test_secondOfDay_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().secondOfDay().isSupported();
        assertNotNull(a);
    }

    public void test_secondOfMinute_1_oe() throws Exception {
        Object a = DateTimeFieldType.secondOfMinute();
        assertNotNull(a);
    }

    public void test_secondOfMinute_2_oe() throws Exception {
        Object a = DateTimeFieldType.secondOfMinute().getName();
        assertEquals("secondOfMinute", a.toString());
    }

    public void test_secondOfMinute_3_oe() throws Exception {
        Object a = DurationFieldType.seconds();
// incorrect assertion         assertNotNull(DurationFieldType.secondOfMinute());
    }

    public void test_secondOfMinute_4_oe() throws Exception {
        Object a = DurationFieldType.minutes();
// incorrect assertion         assertNotNull(DurationFieldType.secondOfMinute());
    }

    public void test_secondOfMinute_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().secondOfMinute();
        assertNotNull(a);
    }

    public void test_secondOfMinute_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().secondOfMinute().isSupported();
        assertNotNull(CopticChronology.getInstanceUTC().secondOfMinute());
    }

    public void test_millisOfDay_1_oe() throws Exception {
        Object a = DateTimeFieldType.millisOfDay();
        assertNotNull(a);
    }

    public void test_millisOfDay_2_oe() throws Exception {
        Object a = DateTimeFieldType.millisOfDay().getName();
        assertEquals("millisOfDay", a.toString());
    }

    public void test_millisOfDay_3_oe() throws Exception {
        Object a = DurationFieldType.millis();
// incorrect assertion         assertNotNull(DurationFieldType.millisOfDay());
    }

    public void test_millisOfDay_4_oe() throws Exception {
        Object a = DurationFieldType.days();
// incorrect assertion         assertEquals("millisOfDay", DurationFieldType.millisOfDay().getName());
    }

    public void test_millisOfDay_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().millisOfDay();
// incorrect assertion         assertEquals("MillisOfDay", CopticChronology.millisOfDay().getName());
    }

    public void test_millisOfDay_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().millisOfDay().isSupported();
        assertNotNull(a);
    }

    public void test_millisOfSecond_1_oe() throws Exception {
        Object a = DateTimeFieldType.millisOfSecond();
        assertNotNull(DateTimeFieldType.millisOfSecond());
    }

    public void test_millisOfSecond_2_oe() throws Exception {
        Object a = DateTimeFieldType.millisOfSecond().getName();
        assertEquals("millisOfSecond", a);
    }

    public void test_millisOfSecond_3_oe() throws Exception {
        Object a = DurationFieldType.millis();
// incorrect assertion         assertEquals("millisOfSecond", DurationFieldType.millisOfSecond().getName());
    }

    public void test_millisOfSecond_4_oe() throws Exception {
        Object a = DurationFieldType.seconds();
// incorrect assertion         assertNotNull(DurationFieldType.millisOfSecond());
    }

    public void test_millisOfSecond_5_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().millisOfSecond();
// incorrect assertion         assertNotNull(CopticChronology.millisOfSecond());
    }

    public void test_millisOfSecond_6_oe() throws Exception {
        Object a = CopticChronology.getInstanceUTC().millisOfSecond().isSupported();
        assertNotNull(a);
    }

    public void test_other_1_oe() throws Exception {
        int a = 1;
        assertEquals(1, a);
    }

    public void test_other_2_oe() throws Exception {
        Class cls = DateTimeFieldType.class.getDeclaredClasses()[0];
        assertEquals(0, cls.getModifiers());
    }

    public void test_other_3_oe() throws Exception {
        Class cls = DateTimeFieldType.class.getDeclaredClasses()[0];
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {
            "other", new Byte((byte) 128), DurationFieldType.hours(), DurationFieldType.months()};
        con.setAccessible(true);  // for Apache Harmony JVM
        DateTimeFieldType type = (DateTimeFieldType) con.newInstance(params);
        
        assertEquals("other", type.getName());
    }

    public void test_other_7_oe() throws Exception {
        Class cls = DateTimeFieldType.class.getDeclaredClasses()[0];
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {
            "other", new Byte((byte) 128), DurationFieldType.hours(), DurationFieldType.months()};
        con.setAccessible(true);  // for Apache Harmony JVM
        DateTimeFieldType type = (DateTimeFieldType) con.newInstance(params);
        
        try {
            type.getField(CopticChronology.getInstanceUTC());
        } catch (InternalError ex) {}
        DateTimeFieldType result = doSerialization(type);
        assertEquals("other", type.getName());
    }

    public void test_centuryOfEra_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.centuryOfEra();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_yearOfCentury_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.yearOfCentury();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_yearOfEra_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.yearOfEra();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_year_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.year();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_monthOfYear_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.monthOfYear();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_weekyearOfCentury_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.weekyearOfCentury();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_weekOfWeekyear_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.weekOfWeekyear();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_dayOfYear_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.dayOfYear();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_dayOfMonth_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.dayOfMonth();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_dayOfWeek_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.dayOfWeek();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_halfdayOfDay_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.halfdayOfDay();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_clockhourOfDay_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.clockhourOfDay();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_clockhourOfHalfday_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.clockhourOfHalfday();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_hourOfHalfday_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.hourOfHalfday();
        DateTimeFieldType result0 = doSerialization(type0);
                assertSame(DateTimeFieldType.hourOfHalfday(), result0);
    }

    public void test_hourOfDay_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.hourOfDay();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_minuteOfDay_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.minuteOfDay();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_minuteOfHour_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.minuteOfHour();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_secondOfDay_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.secondOfDay();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_secondOfMinute_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.secondOfMinute();
        DateTimeFieldType result0 = doSerialization(type0);
                assertSame(type0, result0);
    }

    public void test_millisOfDay_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.millisOfDay();
        DateTimeFieldType result0 = doSerialization(type0);
                assertNotNull(result0);
    }

    public void test_millisOfSecond_7_oe_1_oe() throws Exception {
                final DateTimeFieldType type0 = DateTimeFieldType.millisOfSecond();
        DateTimeFieldType result0 = doSerialization(type0);
                assertSame(type0, result0);
    }

}
