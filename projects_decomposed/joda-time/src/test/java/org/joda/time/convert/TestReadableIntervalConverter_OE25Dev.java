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
import org.joda.time.Interval;
import org.joda.time.MutableInterval;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInterval;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * This class is a JUnit test for ReadableIntervalConverter.
 *
 * @author Stephen Colebourne
 */
public class TestReadableIntervalConverter_OE25Dev extends TestCase {

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
        return new TestSuite(TestReadableIntervalConverter_OE25Dev.class);
    }

    public TestReadableIntervalConverter_OE25Dev(String name) {
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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testSingleton_1_oe() throws Exception {
        Class cls = ReadableIntervalConverter.class;
        assertEquals(false,Modifier.isPublic(cls.getModifiers()));
    }

    public void testSingleton_2_oe() throws Exception {
        Class cls = ReadableIntervalConverter.class;
        // removed other assertion
        assertEquals(false,Modifier.isProtected(cls.getModifiers()));
    }

    public void testSingleton_3_oe() throws Exception {
        Class cls = ReadableIntervalConverter.class;
        // removed other assertion
        // removed other assertion
        assertEquals(false,Modifier.isPrivate(cls.getModifiers()));
    }

    public void testSingleton_4_oe() throws Exception {
        Class cls = ReadableIntervalConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1,cls.getDeclaredConstructors().length);
    }

    public void testSingleton_5_oe() throws Exception {
        Class cls = ReadableIntervalConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        assertEquals(true,Modifier.isProtected(con.getModifiers()));
    }

    public void testSingleton_6_oe() throws Exception {
        Class cls = ReadableIntervalConverter.class;
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
        Class cls = ReadableIntervalConverter.class;
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
        Class cls = ReadableIntervalConverter.class;
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
        assertEquals(ReadableInterval.class,ReadableIntervalConverter.INSTANCE.getSupportedType());
    }

    public void testGetDurationMillis_Object_1_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        assertEquals(123L,ReadableIntervalConverter.INSTANCE.getDurationMillis(i));
    }

    public void testGetPeriodType_Object_1_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        assertEquals(PeriodType.standard(),ReadableIntervalConverter.INSTANCE.getPeriodType(i));
    }

    public void testSetIntoPeriod_Object1_1_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        assertEquals(0,m.getYears());
    }

    public void testSetIntoPeriod_Object1_2_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        assertEquals(0,m.getMonths());
    }

    public void testSetIntoPeriod_Object1_3_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getWeeks());
    }

    public void testSetIntoPeriod_Object1_4_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getDays());
    }

    public void testSetIntoPeriod_Object1_5_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getHours());
    }

    public void testSetIntoPeriod_Object1_6_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getMinutes());
    }

    public void testSetIntoPeriod_Object1_7_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getSeconds());
    }

    public void testSetIntoPeriod_Object1_8_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(123,m.getMillis());
    }

    public void testSetIntoPeriod_Object2_1_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, CopticChronology.getInstance());
        assertEquals(0,m.getYears());
    }

    public void testSetIntoPeriod_Object2_2_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, CopticChronology.getInstance());
        // removed other assertion
        assertEquals(0,m.getMonths());
    }

    public void testSetIntoPeriod_Object2_3_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getWeeks());
    }

    public void testSetIntoPeriod_Object2_4_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getDays());
    }

    public void testSetIntoPeriod_Object2_5_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getHours());
    }

    public void testSetIntoPeriod_Object2_6_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getMinutes());
    }

    public void testSetIntoPeriod_Object2_7_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,m.getSeconds());
    }

    public void testSetIntoPeriod_Object2_8_oe() throws Exception {
        Interval i = new Interval(100L, 223L);
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(123,m.getMillis());
    }

    public void testIsReadableInterval_Object_Chronology_1_oe() throws Exception {
        Interval i = new Interval(1234L, 5678L);
        assertEquals(true,ReadableIntervalConverter.INSTANCE.isReadableInterval(i,null));
    }

    public void testSetIntoInterval_Object1_1_oe() throws Exception {
        Interval i = new Interval(0L, 123L, CopticChronology.getInstance());
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        assertEquals(0L,m.getStartMillis());
    }

    public void testSetIntoInterval_Object1_2_oe() throws Exception {
        Interval i = new Interval(0L, 123L, CopticChronology.getInstance());
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        assertEquals(123L,m.getEndMillis());
    }

    public void testSetIntoInterval_Object1_3_oe() throws Exception {
        Interval i = new Interval(0L, 123L, CopticChronology.getInstance());
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstance(),m.getChronology());
    }

    public void testSetIntoInterval_Object2_1_oe() throws Exception {
        Interval i = new Interval(0L, 123L, CopticChronology.getInstance());
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, GJChronology.getInstance());
        assertEquals(0L,m.getStartMillis());
    }

    public void testSetIntoInterval_Object2_2_oe() throws Exception {
        Interval i = new Interval(0L, 123L, CopticChronology.getInstance());
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, GJChronology.getInstance());
        // removed other assertion
        assertEquals(123L,m.getEndMillis());
    }

    public void testSetIntoInterval_Object2_3_oe() throws Exception {
        Interval i = new Interval(0L, 123L, CopticChronology.getInstance());
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(GJChronology.getInstance(),m.getChronology());
    }

    public void testSetIntoInterval_Object3_1_oe() throws Exception {
        MutableInterval i = new MutableInterval(0L, 123L) {
            @Override
            public Chronology getChronology() {
                return null; // bad
            }
        };
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, GJChronology.getInstance());
        assertEquals(0L,m.getStartMillis());
    }

    public void testSetIntoInterval_Object3_2_oe() throws Exception {
        MutableInterval i = new MutableInterval(0L, 123L) {
            @Override
            public Chronology getChronology() {
                return null; // bad
            }
        };
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, GJChronology.getInstance());
        // removed other assertion
        assertEquals(123L,m.getEndMillis());
    }

    public void testSetIntoInterval_Object3_3_oe() throws Exception {
        MutableInterval i = new MutableInterval(0L, 123L) {
            @Override
            public Chronology getChronology() {
                return null; // bad
            }
        };
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(GJChronology.getInstance(),m.getChronology());
    }

    public void testSetIntoInterval_Object4_1_oe() throws Exception {
        MutableInterval i = new MutableInterval(0L, 123L) {
            @Override
            public Chronology getChronology() {
                return null; // bad
            }
        };
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        assertEquals(0L,m.getStartMillis());
    }

    public void testSetIntoInterval_Object4_2_oe() throws Exception {
        MutableInterval i = new MutableInterval(0L, 123L) {
            @Override
            public Chronology getChronology() {
                return null; // bad
            }
        };
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        assertEquals(123L,m.getEndMillis());
    }

    public void testSetIntoInterval_Object4_3_oe() throws Exception {
        MutableInterval i = new MutableInterval(0L, 123L) {
            @Override
            public Chronology getChronology() {
                return null; // bad
            }
        };
        MutableInterval m = new MutableInterval(-1000L, 1000L, BuddhistChronology.getInstance());
        ReadableIntervalConverter.INSTANCE.setInto(m, i, null);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(),m.getChronology());
    }

    public void testToString_1_oe() {
        assertEquals("Converter[org.joda.time.ReadableInterval]",ReadableIntervalConverter.INSTANCE.toString());
    }

}
