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
import java.util.Arrays;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;
import org.joda.time.TimeOfDay;
import org.joda.time.YearMonthDay;
import org.joda.time.base.BasePartial;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * This class is a Junit unit test for ReadablePartialConverter.
 *
 * @author Stephen Colebourne
 */
public class TestReadablePartialConverter_OE25Dev extends TestCase {

    private static final DateTimeZone UTC = DateTimeZone.UTC;
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final Chronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    private static Chronology JULIAN;
    private static Chronology ISO;
    private static Chronology BUDDHIST;
    
    private DateTimeZone zone = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestReadablePartialConverter_OE25Dev.class);
    }

    public TestReadablePartialConverter_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        JULIAN = JulianChronology.getInstance();
        ISO = ISOChronology.getInstance();
        BUDDHIST = BuddhistChronology.getInstance();
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    static class MockTOD extends BasePartial {
        @Override
        protected DateTimeField getField(int index, Chronology chrono) {
            switch (index) {
                case 0:
                return chrono.hourOfDay();
                case 1:
                return chrono.minuteOfHour();
                case 2:
                return chrono.year();
                case 3:
                return chrono.era();
            }
            return null;
        }
        public int size() {
            return 4;
        }
    }

    //-----------------------------------------------------------------------

    public void testSingleton_1_oe() throws Exception {
        Class cls = ReadablePartialConverter.class;
        assertEquals(false,Modifier.isPublic(cls.getModifiers()));
    }

    public void testSingleton_2_oe() throws Exception {
        Class cls = ReadablePartialConverter.class;
        assertEquals(false,Modifier.isProtected(cls.getModifiers()));
    }

    public void testSingleton_3_oe() throws Exception {
        Class cls = ReadablePartialConverter.class;
        assertEquals(false,Modifier.isPrivate(cls.getModifiers()));
    }

    public void testSingleton_4_oe() throws Exception {
        Class cls = ReadablePartialConverter.class;
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1,cls.getDeclaredConstructors().length);
    }

    public void testSingleton_5_oe() throws Exception {
        Class cls = ReadablePartialConverter.class;
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(true,Modifier.isProtected(con.getModifiers()));
    }

    public void testSingleton_6_oe() throws Exception {
        Class cls = ReadablePartialConverter.class;
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertEquals(false,Modifier.isPublic(fld.getModifiers()));
    }

    public void testSingleton_7_oe() throws Exception {
        Class cls = ReadablePartialConverter.class;
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertEquals(false,Modifier.isProtected(fld.getModifiers()));
    }

    public void testSingleton_8_oe() throws Exception {
        Class cls = ReadablePartialConverter.class;
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertEquals(false,Modifier.isPrivate(fld.getModifiers()));
    }

    public void testSupportedType_1_oe() throws Exception {
        assertEquals(ReadablePartial.class,ReadablePartialConverter.INSTANCE.getSupportedType());
    }

    public void testGetChronology_Object_Zone_1_oe() throws Exception {
        assertEquals(ISO_PARIS,ReadablePartialConverter.INSTANCE.getChronology(new TimeOfDay(123L),PARIS));
    }

    public void testGetChronology_Object_Zone_2_oe() throws Exception {
        assertEquals(ISO,ReadablePartialConverter.INSTANCE.getChronology(new TimeOfDay(123L),DateTimeZone.getDefault()));
    }

    public void testGetChronology_Object_Zone_3_oe() throws Exception {
        assertEquals(ISO,ReadablePartialConverter.INSTANCE.getChronology(new TimeOfDay(123L),(DateTimeZone)null));
    }

    public void testGetChronology_Object_Chronology_1_oe() throws Exception {
        assertEquals(JULIAN,ReadablePartialConverter.INSTANCE.getChronology(new TimeOfDay(123L,BUDDHIST),JULIAN));
    }

    public void testGetChronology_Object_Chronology_2_oe() throws Exception {
        assertEquals(JULIAN,ReadablePartialConverter.INSTANCE.getChronology(new TimeOfDay(123L),JULIAN));
    }

    public void testGetChronology_Object_Chronology_3_oe() throws Exception {
        assertEquals(BUDDHIST.withUTC(),ReadablePartialConverter.INSTANCE.getChronology(new TimeOfDay(123L,BUDDHIST),(Chronology)null));
    }

    public void testGetPartialValues_1_oe() throws Exception {
        TimeOfDay tod = new TimeOfDay();
        int[] expected = new int[] {1, 2, 3, 4};
        int[] actual = ReadablePartialConverter.INSTANCE.getPartialValues(tod, new TimeOfDay(1, 2, 3, 4), ISOChronology.getInstance(PARIS));
        assertEquals(true,Arrays.equals(expected,actual));
    }

    public void testToString_1_oe() {
        assertEquals("Converter[org.joda.time.ReadablePartial]",ReadablePartialConverter.INSTANCE.toString());
    }

}
