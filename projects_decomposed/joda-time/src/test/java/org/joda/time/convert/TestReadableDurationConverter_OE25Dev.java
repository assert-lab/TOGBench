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
package org.joda.time.convert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.PeriodType;
import org.joda.time.MutablePeriod;
import org.joda.time.ReadableDuration;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * This class is a Junit unit test for ReadableDurationConverter.
 *
 * @author Stephen Colebourne
 */
public class TestReadableDurationConverter_OE25Dev extends TestCase {

    private static final DateTimeZone UTC = DateTimeZone.UTC;
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final Chronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    private static Chronology JULIAN;
    private static Chronology ISO;
    
    private DateTimeZone zone = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestReadableDurationConverter_OE25Dev_OE25Dev.class);
    }

    public TestReadableDurationConverter_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        JULIAN = JulianChronology.getInstance();
        ISO = ISOChronology.getInstance();
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(PARIS);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        DateTimeZone.setDefault(zone);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testSingleton_1_oe() throws Exception {
        Class cls = ReadableDurationConverter.class;
        assertEquals(false,Modifier.isPublic(cls.getModifiers()));
    }

    public void testSingleton_2_oe() throws Exception {
        Class cls = ReadableDurationConverter.class;
        // removed other assertion
        assertEquals(false,Modifier.isProtected(cls.getModifiers()));
    }

    public void testSingleton_3_oe() throws Exception {
        Class cls = ReadableDurationConverter.class;
        // removed other assertion
        // removed other assertion
        assertEquals(false,Modifier.isPrivate(cls.getModifiers()));
    }

    public void testSingleton_4_oe() throws Exception {
        Class cls = ReadableDurationConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1,cls.getDeclaredConstructors().length);
    }

    public void testSingleton_5_oe() throws Exception {
        Class cls = ReadableDurationConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        assertEquals(true,Modifier.isProtected(con.getModifiers()));
    }

    public void testSingleton_6_oe() throws Exception {
        Class cls = ReadableDurationConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        // removed other assertion
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertEquals(false,Modifier.isPublic(fld.getModifiers()));
    }

    public void testSingleton_7_oe() throws Exception {
        Class cls = ReadableDurationConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        // removed other assertion
        
        Field fld = cls.getDeclaredField("INSTANCE");
        // removed other assertion
        assertEquals(false,Modifier.isProtected(fld.getModifiers()));
    }

    public void testSingleton_8_oe() throws Exception {
        Class cls = ReadableDurationConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        // removed other assertion
        
        Field fld = cls.getDeclaredField("INSTANCE");
        // removed other assertion
        // removed other assertion
        assertEquals(false,Modifier.isPrivate(fld.getModifiers()));
    }

    public void testSupportedType_1_oe() throws Exception {
        assertEquals(ReadableDuration.class,ReadableDurationConverter.INSTANCE.getSupportedType());
    }

    public void testGetDurationMillis_Object_1_oe() throws Exception {
        assertEquals(123L,ReadableDurationConverter.INSTANCE.getDurationMillis(new Duration(123L)));
    }

    public void testGetPeriodType_Object_1_oe() throws Exception {
        assertEquals(PeriodType.standard(),ReadableDurationConverter.INSTANCE.getPeriodType(new Duration(123L)));
    }

    public void testSetInto_Object_1_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadableDurationConverter.INSTANCE.setInto(m, new Duration(
            3L * DateTimeConstants.MILLIS_PER_DAY +
            4L * DateTimeConstants.MILLIS_PER_MINUTE + 5L
        ), null);
        assertEquals(0,m.getYears());
    }

    public void testSetInto_Object_2_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadableDurationConverter.INSTANCE.setInto(m, new Duration(
            3L * DateTimeConstants.MILLIS_PER_DAY +
            4L * DateTimeConstants.MILLIS_PER_MINUTE + 5L
        ), null);
        // removed other assertion
        assertEquals(0,m.getMonths());
    }

    public void testSetInto_Object_3_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadableDurationConverter.INSTANCE.setInto(m, new Duration(
            3L * DateTimeConstants.MILLIS_PER_DAY +
            4L * DateTimeConstants.MILLIS_PER_MINUTE + 5L
        ), null);
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getWeeks());
    }

    public void testSetInto_Object_4_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadableDurationConverter.INSTANCE.setInto(m, new Duration(
            3L * DateTimeConstants.MILLIS_PER_DAY +
            4L * DateTimeConstants.MILLIS_PER_MINUTE + 5L
        ), null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getDays());
    }

    public void testSetInto_Object_5_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadableDurationConverter.INSTANCE.setInto(m, new Duration(
            3L * DateTimeConstants.MILLIS_PER_DAY +
            4L * DateTimeConstants.MILLIS_PER_MINUTE + 5L
        ), null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3 * 24,m.getHours());
    }

    public void testSetInto_Object_6_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadableDurationConverter.INSTANCE.setInto(m, new Duration(
            3L * DateTimeConstants.MILLIS_PER_DAY +
            4L * DateTimeConstants.MILLIS_PER_MINUTE + 5L
        ), null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4,m.getMinutes());
    }

    public void testSetInto_Object_7_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadableDurationConverter.INSTANCE.setInto(m, new Duration(
            3L * DateTimeConstants.MILLIS_PER_DAY +
            4L * DateTimeConstants.MILLIS_PER_MINUTE + 5L
        ), null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getSeconds());
    }

    public void testSetInto_Object_8_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadableDurationConverter.INSTANCE.setInto(m, new Duration(
            3L * DateTimeConstants.MILLIS_PER_DAY +
            4L * DateTimeConstants.MILLIS_PER_MINUTE + 5L
        ), null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5,m.getMillis());
    }

    public void testToString_1_oe() {
        assertEquals("Converter[org.joda.time.ReadableDuration]",ReadableDurationConverter.INSTANCE.toString());
    }

}
