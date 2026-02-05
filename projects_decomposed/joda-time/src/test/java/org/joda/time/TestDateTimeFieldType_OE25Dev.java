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
        assertEquals(DateTimeFieldType.era(),DateTimeFieldType.era());
    }

    public void test_era_2_oe() throws Exception {
        // removed other assertion
        assertEquals("era",DateTimeFieldType.era().getName());
    }

    public void test_era_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.eras(),DateTimeFieldType.era().getDurationType());
    }

    public void test_era_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,DateTimeFieldType.era().getRangeDurationType());
    }

    public void test_era_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().era(),DateTimeFieldType.era().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_era_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().era().isSupported(),DateTimeFieldType.era().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_centuryOfEra_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.centuryOfEra(),DateTimeFieldType.centuryOfEra());
    }

    public void test_centuryOfEra_2_oe() throws Exception {
        // removed other assertion
        assertEquals("centuryOfEra",DateTimeFieldType.centuryOfEra().getName());
    }

    public void test_centuryOfEra_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.centuries(),DateTimeFieldType.centuryOfEra().getDurationType());
    }

    public void test_centuryOfEra_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.eras(),DateTimeFieldType.centuryOfEra().getRangeDurationType());
    }

    public void test_centuryOfEra_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().centuryOfEra(),DateTimeFieldType.centuryOfEra().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_centuryOfEra_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().centuryOfEra().isSupported(),DateTimeFieldType.centuryOfEra().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_yearOfCentury_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.yearOfCentury(),DateTimeFieldType.yearOfCentury());
    }

    public void test_yearOfCentury_2_oe() throws Exception {
        // removed other assertion
        assertEquals("yearOfCentury",DateTimeFieldType.yearOfCentury().getName());
    }

    public void test_yearOfCentury_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.years(),DateTimeFieldType.yearOfCentury().getDurationType());
    }

    public void test_yearOfCentury_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.centuries(),DateTimeFieldType.yearOfCentury().getRangeDurationType());
    }

    public void test_yearOfCentury_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().yearOfCentury(),DateTimeFieldType.yearOfCentury().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_yearOfCentury_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().yearOfCentury().isSupported(),DateTimeFieldType.yearOfCentury().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_yearOfEra_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.yearOfEra(),DateTimeFieldType.yearOfEra());
    }

    public void test_yearOfEra_2_oe() throws Exception {
        // removed other assertion
        assertEquals("yearOfEra",DateTimeFieldType.yearOfEra().getName());
    }

    public void test_yearOfEra_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.years(),DateTimeFieldType.yearOfEra().getDurationType());
    }

    public void test_yearOfEra_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.eras(),DateTimeFieldType.yearOfEra().getRangeDurationType());
    }

    public void test_yearOfEra_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().yearOfEra(),DateTimeFieldType.yearOfEra().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_yearOfEra_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().yearOfEra().isSupported(),DateTimeFieldType.yearOfEra().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_year_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.year(),DateTimeFieldType.year());
    }

    public void test_year_2_oe() throws Exception {
        // removed other assertion
        assertEquals("year",DateTimeFieldType.year().getName());
    }

    public void test_year_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.years(),DateTimeFieldType.year().getDurationType());
    }

    public void test_year_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,DateTimeFieldType.year().getRangeDurationType());
    }

    public void test_year_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().year(),DateTimeFieldType.year().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_year_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().year().isSupported(),DateTimeFieldType.year().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_monthOfYear_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.monthOfYear(),DateTimeFieldType.monthOfYear());
    }

    public void test_monthOfYear_2_oe() throws Exception {
        // removed other assertion
        assertEquals("monthOfYear",DateTimeFieldType.monthOfYear().getName());
    }

    public void test_monthOfYear_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),DateTimeFieldType.monthOfYear().getDurationType());
    }

    public void test_monthOfYear_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.years(),DateTimeFieldType.monthOfYear().getRangeDurationType());
    }

    public void test_monthOfYear_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().monthOfYear(),DateTimeFieldType.monthOfYear().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_monthOfYear_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().monthOfYear().isSupported(),DateTimeFieldType.monthOfYear().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_weekyearOfCentury_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.weekyearOfCentury(),DateTimeFieldType.weekyearOfCentury());
    }

    public void test_weekyearOfCentury_2_oe() throws Exception {
        // removed other assertion
        assertEquals("weekyearOfCentury",DateTimeFieldType.weekyearOfCentury().getName());
    }

    public void test_weekyearOfCentury_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weekyears(),DateTimeFieldType.weekyearOfCentury().getDurationType());
    }

    public void test_weekyearOfCentury_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.centuries(),DateTimeFieldType.weekyearOfCentury().getRangeDurationType());
    }

    public void test_weekyearOfCentury_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().weekyearOfCentury(),DateTimeFieldType.weekyearOfCentury().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_weekyearOfCentury_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().weekyearOfCentury().isSupported(),DateTimeFieldType.weekyearOfCentury().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_weekyear_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.weekyear(),DateTimeFieldType.weekyear());
    }

    public void test_weekyear_2_oe() throws Exception {
        // removed other assertion
        assertEquals("weekyear",DateTimeFieldType.weekyear().getName());
    }

    public void test_weekyear_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weekyears(),DateTimeFieldType.weekyear().getDurationType());
    }

    public void test_weekyear_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,DateTimeFieldType.weekyear().getRangeDurationType());
    }

    public void test_weekyear_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().weekyear(),DateTimeFieldType.weekyear().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_weekyear_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().weekyear().isSupported(),DateTimeFieldType.weekyear().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_weekOfWeekyear_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.weekOfWeekyear(),DateTimeFieldType.weekOfWeekyear());
    }

    public void test_weekOfWeekyear_2_oe() throws Exception {
        // removed other assertion
        assertEquals("weekOfWeekyear",DateTimeFieldType.weekOfWeekyear().getName());
    }

    public void test_weekOfWeekyear_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),DateTimeFieldType.weekOfWeekyear().getDurationType());
    }

    public void test_weekOfWeekyear_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weekyears(),DateTimeFieldType.weekOfWeekyear().getRangeDurationType());
    }

    public void test_weekOfWeekyear_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().weekOfWeekyear(),DateTimeFieldType.weekOfWeekyear().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_weekOfWeekyear_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().weekOfWeekyear().isSupported(),DateTimeFieldType.weekOfWeekyear().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_dayOfYear_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.dayOfYear(),DateTimeFieldType.dayOfYear());
    }

    public void test_dayOfYear_2_oe() throws Exception {
        // removed other assertion
        assertEquals("dayOfYear",DateTimeFieldType.dayOfYear().getName());
    }

    public void test_dayOfYear_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),DateTimeFieldType.dayOfYear().getDurationType());
    }

    public void test_dayOfYear_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.years(),DateTimeFieldType.dayOfYear().getRangeDurationType());
    }

    public void test_dayOfYear_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().dayOfYear(),DateTimeFieldType.dayOfYear().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_dayOfYear_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().dayOfYear().isSupported(),DateTimeFieldType.dayOfYear().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_dayOfMonth_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.dayOfMonth(),DateTimeFieldType.dayOfMonth());
    }

    public void test_dayOfMonth_2_oe() throws Exception {
        // removed other assertion
        assertEquals("dayOfMonth",DateTimeFieldType.dayOfMonth().getName());
    }

    public void test_dayOfMonth_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),DateTimeFieldType.dayOfMonth().getDurationType());
    }

    public void test_dayOfMonth_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),DateTimeFieldType.dayOfMonth().getRangeDurationType());
    }

    public void test_dayOfMonth_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().dayOfMonth(),DateTimeFieldType.dayOfMonth().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_dayOfMonth_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().dayOfMonth().isSupported(),DateTimeFieldType.dayOfMonth().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_dayOfWeek_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.dayOfWeek(),DateTimeFieldType.dayOfWeek());
    }

    public void test_dayOfWeek_2_oe() throws Exception {
        // removed other assertion
        assertEquals("dayOfWeek",DateTimeFieldType.dayOfWeek().getName());
    }

    public void test_dayOfWeek_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),DateTimeFieldType.dayOfWeek().getDurationType());
    }

    public void test_dayOfWeek_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),DateTimeFieldType.dayOfWeek().getRangeDurationType());
    }

    public void test_dayOfWeek_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().dayOfWeek(),DateTimeFieldType.dayOfWeek().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_dayOfWeek_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().dayOfWeek().isSupported(),DateTimeFieldType.dayOfWeek().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_halfdayOfDay_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.halfdayOfDay(),DateTimeFieldType.halfdayOfDay());
    }

    public void test_halfdayOfDay_2_oe() throws Exception {
        // removed other assertion
        assertEquals("halfdayOfDay",DateTimeFieldType.halfdayOfDay().getName());
    }

    public void test_halfdayOfDay_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.halfdays(),DateTimeFieldType.halfdayOfDay().getDurationType());
    }

    public void test_halfdayOfDay_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),DateTimeFieldType.halfdayOfDay().getRangeDurationType());
    }

    public void test_halfdayOfDay_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().halfdayOfDay(),DateTimeFieldType.halfdayOfDay().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_halfdayOfDay_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().halfdayOfDay().isSupported(),DateTimeFieldType.halfdayOfDay().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_clockhourOfDay_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.clockhourOfDay(),DateTimeFieldType.clockhourOfDay());
    }

    public void test_clockhourOfDay_2_oe() throws Exception {
        // removed other assertion
        assertEquals("clockhourOfDay",DateTimeFieldType.clockhourOfDay().getName());
    }

    public void test_clockhourOfDay_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),DateTimeFieldType.clockhourOfDay().getDurationType());
    }

    public void test_clockhourOfDay_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),DateTimeFieldType.clockhourOfDay().getRangeDurationType());
    }

    public void test_clockhourOfDay_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().clockhourOfDay(),DateTimeFieldType.clockhourOfDay().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_clockhourOfDay_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().clockhourOfDay().isSupported(),DateTimeFieldType.clockhourOfDay().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_clockhourOfHalfday_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.clockhourOfHalfday(),DateTimeFieldType.clockhourOfHalfday());
    }

    public void test_clockhourOfHalfday_2_oe() throws Exception {
        // removed other assertion
        assertEquals("clockhourOfHalfday",DateTimeFieldType.clockhourOfHalfday().getName());
    }

    public void test_clockhourOfHalfday_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),DateTimeFieldType.clockhourOfHalfday().getDurationType());
    }

    public void test_clockhourOfHalfday_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.halfdays(),DateTimeFieldType.clockhourOfHalfday().getRangeDurationType());
    }

    public void test_clockhourOfHalfday_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().clockhourOfHalfday(),DateTimeFieldType.clockhourOfHalfday().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_clockhourOfHalfday_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().clockhourOfHalfday().isSupported(),DateTimeFieldType.clockhourOfHalfday().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_hourOfHalfday_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.hourOfHalfday(),DateTimeFieldType.hourOfHalfday());
    }

    public void test_hourOfHalfday_2_oe() throws Exception {
        // removed other assertion
        assertEquals("hourOfHalfday",DateTimeFieldType.hourOfHalfday().getName());
    }

    public void test_hourOfHalfday_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),DateTimeFieldType.hourOfHalfday().getDurationType());
    }

    public void test_hourOfHalfday_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.halfdays(),DateTimeFieldType.hourOfHalfday().getRangeDurationType());
    }

    public void test_hourOfHalfday_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().hourOfHalfday(),DateTimeFieldType.hourOfHalfday().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_hourOfHalfday_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().hourOfHalfday().isSupported(),DateTimeFieldType.hourOfHalfday().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_hourOfDay_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.hourOfDay(),DateTimeFieldType.hourOfDay());
    }

    public void test_hourOfDay_2_oe() throws Exception {
        // removed other assertion
        assertEquals("hourOfDay",DateTimeFieldType.hourOfDay().getName());
    }

    public void test_hourOfDay_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),DateTimeFieldType.hourOfDay().getDurationType());
    }

    public void test_hourOfDay_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),DateTimeFieldType.hourOfDay().getRangeDurationType());
    }

    public void test_hourOfDay_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().hourOfDay(),DateTimeFieldType.hourOfDay().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_hourOfDay_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().hourOfDay().isSupported(),DateTimeFieldType.hourOfDay().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_minuteOfDay_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.minuteOfDay(),DateTimeFieldType.minuteOfDay());
    }

    public void test_minuteOfDay_2_oe() throws Exception {
        // removed other assertion
        assertEquals("minuteOfDay",DateTimeFieldType.minuteOfDay().getName());
    }

    public void test_minuteOfDay_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),DateTimeFieldType.minuteOfDay().getDurationType());
    }

    public void test_minuteOfDay_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),DateTimeFieldType.minuteOfDay().getRangeDurationType());
    }

    public void test_minuteOfDay_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().minuteOfDay(),DateTimeFieldType.minuteOfDay().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_minuteOfDay_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().minuteOfDay().isSupported(),DateTimeFieldType.minuteOfDay().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_minuteOfHour_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.minuteOfHour(),DateTimeFieldType.minuteOfHour());
    }

    public void test_minuteOfHour_2_oe() throws Exception {
        // removed other assertion
        assertEquals("minuteOfHour",DateTimeFieldType.minuteOfHour().getName());
    }

    public void test_minuteOfHour_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),DateTimeFieldType.minuteOfHour().getDurationType());
    }

    public void test_minuteOfHour_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),DateTimeFieldType.minuteOfHour().getRangeDurationType());
    }

    public void test_minuteOfHour_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().minuteOfHour(),DateTimeFieldType.minuteOfHour().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_minuteOfHour_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().minuteOfHour().isSupported(),DateTimeFieldType.minuteOfHour().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_secondOfDay_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.secondOfDay(),DateTimeFieldType.secondOfDay());
    }

    public void test_secondOfDay_2_oe() throws Exception {
        // removed other assertion
        assertEquals("secondOfDay",DateTimeFieldType.secondOfDay().getName());
    }

    public void test_secondOfDay_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),DateTimeFieldType.secondOfDay().getDurationType());
    }

    public void test_secondOfDay_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),DateTimeFieldType.secondOfDay().getRangeDurationType());
    }

    public void test_secondOfDay_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().secondOfDay(),DateTimeFieldType.secondOfDay().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_secondOfDay_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().secondOfDay().isSupported(),DateTimeFieldType.secondOfDay().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_secondOfMinute_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.secondOfMinute(),DateTimeFieldType.secondOfMinute());
    }

    public void test_secondOfMinute_2_oe() throws Exception {
        // removed other assertion
        assertEquals("secondOfMinute",DateTimeFieldType.secondOfMinute().getName());
    }

    public void test_secondOfMinute_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),DateTimeFieldType.secondOfMinute().getDurationType());
    }

    public void test_secondOfMinute_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),DateTimeFieldType.secondOfMinute().getRangeDurationType());
    }

    public void test_secondOfMinute_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().secondOfMinute(),DateTimeFieldType.secondOfMinute().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_secondOfMinute_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().secondOfMinute().isSupported(),DateTimeFieldType.secondOfMinute().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_millisOfDay_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.millisOfDay(),DateTimeFieldType.millisOfDay());
    }

    public void test_millisOfDay_2_oe() throws Exception {
        // removed other assertion
        assertEquals("millisOfDay",DateTimeFieldType.millisOfDay().getName());
    }

    public void test_millisOfDay_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),DateTimeFieldType.millisOfDay().getDurationType());
    }

    public void test_millisOfDay_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),DateTimeFieldType.millisOfDay().getRangeDurationType());
    }

    public void test_millisOfDay_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().millisOfDay(),DateTimeFieldType.millisOfDay().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_millisOfDay_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().millisOfDay().isSupported(),DateTimeFieldType.millisOfDay().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_millisOfSecond_1_oe() throws Exception {
        assertEquals(DateTimeFieldType.millisOfSecond(),DateTimeFieldType.millisOfSecond());
    }

    public void test_millisOfSecond_2_oe() throws Exception {
        // removed other assertion
        assertEquals("millisOfSecond",DateTimeFieldType.millisOfSecond().getName());
    }

    public void test_millisOfSecond_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),DateTimeFieldType.millisOfSecond().getDurationType());
    }

    public void test_millisOfSecond_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),DateTimeFieldType.millisOfSecond().getRangeDurationType());
    }

    public void test_millisOfSecond_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().millisOfSecond(),DateTimeFieldType.millisOfSecond().getField(CopticChronology.getInstanceUTC()));
    }

    public void test_millisOfSecond_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstanceUTC().millisOfSecond().isSupported(),DateTimeFieldType.millisOfSecond().isSupported(CopticChronology.getInstanceUTC()));
    }

    public void test_other_1_oe() throws Exception {
        assertEquals(1,DateTimeFieldType.class.getDeclaredClasses().length);
    }

    public void test_other_2_oe() throws Exception {
        // removed other assertion
        Class cls = DateTimeFieldType.class.getDeclaredClasses()[0];
        assertEquals(1,cls.getDeclaredConstructors().length);
    }

    public void test_other_3_oe() throws Exception {
        // removed other assertion
        Class cls = DateTimeFieldType.class.getDeclaredClasses()[0];
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {
            "other", new Byte((byte) 128), DurationFieldType.hours(), DurationFieldType.months()};
        con.setAccessible(true);  // for Apache Harmony JVM
        DateTimeFieldType type = (DateTimeFieldType) con.newInstance(params);
        
        assertEquals("other",type.getName());
    }

    public void test_other_4_oe() throws Exception {
        // removed other assertion
        Class cls = DateTimeFieldType.class.getDeclaredClasses()[0];
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {
            "other", new Byte((byte) 128), DurationFieldType.hours(), DurationFieldType.months()};
        con.setAccessible(true);  // for Apache Harmony JVM
        DateTimeFieldType type = (DateTimeFieldType) con.newInstance(params);
        
        // removed other assertion
        assertSame(DurationFieldType.hours(),type.getDurationType());
    }

    public void test_other_5_oe() throws Exception {
        // removed other assertion
        Class cls = DateTimeFieldType.class.getDeclaredClasses()[0];
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {
            "other", new Byte((byte) 128), DurationFieldType.hours(), DurationFieldType.months()};
        con.setAccessible(true);  // for Apache Harmony JVM
        DateTimeFieldType type = (DateTimeFieldType) con.newInstance(params);
        
        // removed other assertion
        // removed other assertion
        assertSame(DurationFieldType.months(),type.getRangeDurationType());
    }

    public void test_other_7_oe() throws Exception {
        // removed other assertion
        Class cls = DateTimeFieldType.class.getDeclaredClasses()[0];
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {
            "other", new Byte((byte) 128), DurationFieldType.hours(), DurationFieldType.months()};
        con.setAccessible(true);  // for Apache Harmony JVM
        DateTimeFieldType type = (DateTimeFieldType) con.newInstance(params);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            type.getField(CopticChronology.getInstanceUTC());
            // removed other assertion
        } catch (InternalError ex) {}
        DateTimeFieldType result = doSerialization(type);
        assertEquals(type.getName(),result.getName());
    }

    public void test_other_8_oe() throws Exception {
        // removed other assertion
        Class cls = DateTimeFieldType.class.getDeclaredClasses()[0];
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        Object[] params = new Object[] {
            "other", new Byte((byte) 128), DurationFieldType.hours(), DurationFieldType.months()};
        con.setAccessible(true);  // for Apache Harmony JVM
        DateTimeFieldType type = (DateTimeFieldType) con.newInstance(params);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            type.getField(CopticChronology.getInstanceUTC());
            // removed other assertion
        } catch (InternalError ex) {}
        DateTimeFieldType result = doSerialization(type);
        // removed other assertion
        assertNotSame(type,result);
    }

}
