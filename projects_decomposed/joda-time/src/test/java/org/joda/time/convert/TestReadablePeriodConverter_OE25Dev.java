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
import org.joda.time.DateTimeZone;
import org.joda.time.PeriodType;
import org.joda.time.MutablePeriod;
import org.joda.time.ReadablePeriod;
import org.joda.time.Period;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * This class is a Junit unit test for ReadablePeriodConverter.
 *
 * @author Stephen Colebourne
 */
public class TestReadablePeriodConverter_OE25Dev extends TestCase {

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
        return new TestSuite(TestReadablePeriodConverter_OE25Dev_OE25Dev.class);
    }

    public TestReadablePeriodConverter_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        JULIAN = JulianChronology.getInstance();
        ISO = ISOChronology.getInstance();
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testSingleton_1_oe() throws Exception {
        Class cls = ReadablePeriodConverter.class;
        assertEquals(false,Modifier.isPublic(cls.getModifiers()));
    }

    public void testSingleton_2_oe() throws Exception {
        Class cls = ReadablePeriodConverter.class;
        // removed other assertion
        assertEquals(false,Modifier.isProtected(cls.getModifiers()));
    }

    public void testSingleton_3_oe() throws Exception {
        Class cls = ReadablePeriodConverter.class;
        // removed other assertion
        // removed other assertion
        assertEquals(false,Modifier.isPrivate(cls.getModifiers()));
    }

    public void testSingleton_4_oe() throws Exception {
        Class cls = ReadablePeriodConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1,cls.getDeclaredConstructors().length);
    }

    public void testSingleton_5_oe() throws Exception {
        Class cls = ReadablePeriodConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        assertEquals(true,Modifier.isProtected(con.getModifiers()));
    }

    public void testSingleton_6_oe() throws Exception {
        Class cls = ReadablePeriodConverter.class;
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
        Class cls = ReadablePeriodConverter.class;
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
        Class cls = ReadablePeriodConverter.class;
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
        assertEquals(ReadablePeriod.class,ReadablePeriodConverter.INSTANCE.getSupportedType());
    }

    public void testGetPeriodType_Object_1_oe() throws Exception {
        assertEquals(PeriodType.standard(),ReadablePeriodConverter.INSTANCE.getPeriodType(new Period(123L,PeriodType.standard())));
    }

    public void testGetPeriodType_Object_2_oe() throws Exception {
        // removed other assertion
        assertEquals(PeriodType.yearMonthDayTime(),ReadablePeriodConverter.INSTANCE.getPeriodType(new Period(123L,PeriodType.yearMonthDayTime())));
    }

    public void testSetInto_Object_1_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadablePeriodConverter.INSTANCE.setInto(m, new Period(0, 0, 0, 3, 0, 4, 0, 5), null);
        assertEquals(0,m.getYears());
    }

    public void testSetInto_Object_2_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadablePeriodConverter.INSTANCE.setInto(m, new Period(0, 0, 0, 3, 0, 4, 0, 5), null);
        // removed other assertion
        assertEquals(0,m.getMonths());
    }

    public void testSetInto_Object_3_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadablePeriodConverter.INSTANCE.setInto(m, new Period(0, 0, 0, 3, 0, 4, 0, 5), null);
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getWeeks());
    }

    public void testSetInto_Object_4_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadablePeriodConverter.INSTANCE.setInto(m, new Period(0, 0, 0, 3, 0, 4, 0, 5), null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,m.getDays());
    }

    public void testSetInto_Object_5_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadablePeriodConverter.INSTANCE.setInto(m, new Period(0, 0, 0, 3, 0, 4, 0, 5), null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getHours());
    }

    public void testSetInto_Object_6_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadablePeriodConverter.INSTANCE.setInto(m, new Period(0, 0, 0, 3, 0, 4, 0, 5), null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4,m.getMinutes());
    }

    public void testSetInto_Object_7_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadablePeriodConverter.INSTANCE.setInto(m, new Period(0, 0, 0, 3, 0, 4, 0, 5), null);
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
        ReadablePeriodConverter.INSTANCE.setInto(m, new Period(0, 0, 0, 3, 0, 4, 0, 5), null);
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
        assertEquals("Converter[org.joda.time.ReadablePeriod]",ReadablePeriodConverter.INSTANCE.toString());
    }

}
