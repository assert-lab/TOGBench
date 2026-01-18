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
import java.util.Date;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.TimeOfDay;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * This class is a Junit unit test for DateConverter.
 *
 * @author Stephen Colebourne
 */
public class TestDateConverter_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final Chronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    private static Chronology ISO;
    private static Chronology JULIAN;
    private static Chronology COPTIC;
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateConverter_OE25Dev.class);
    }

    public TestDateConverter_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        JULIAN = JulianChronology.getInstance();
        COPTIC = CopticChronology.getInstance();
        ISO = ISOChronology.getInstance();
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testSingleton_1_oe() throws Exception {
        Class cls = DateConverter.class;
        assertEquals(false, Modifier.isPublic(cls.getModifiers()));
    }

    public void testSingleton_2_oe() throws Exception {
        Class cls = DateConverter.class;
        // removed other assertion
        assertEquals(false, Modifier.isProtected(cls.getModifiers()));
    }

    public void testSingleton_3_oe() throws Exception {
        Class cls = DateConverter.class;
        // removed other assertion
        // removed other assertion
        assertEquals(false, Modifier.isPrivate(cls.getModifiers()));
    }

    public void testSingleton_4_oe() throws Exception {
        Class cls = DateConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1, cls.getDeclaredConstructors().length);
    }

    public void testSingleton_5_oe() throws Exception {
        Class cls = DateConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        assertEquals(true, Modifier.isProtected(con.getModifiers()));
    }

    public void testSingleton_6_oe() throws Exception {
        Class cls = DateConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        // removed other assertion
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertEquals(false, Modifier.isPublic(fld.getModifiers()));
    }

    public void testSingleton_7_oe() throws Exception {
        Class cls = DateConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        // removed other assertion
        
        Field fld = cls.getDeclaredField("INSTANCE");
        // removed other assertion
        assertEquals(false, Modifier.isProtected(fld.getModifiers()));
    }

    public void testSingleton_8_oe() throws Exception {
        Class cls = DateConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        // removed other assertion
        
        Field fld = cls.getDeclaredField("INSTANCE");
        // removed other assertion
        // removed other assertion
        assertEquals(false, Modifier.isPrivate(fld.getModifiers()));
    }

    public void testSupportedType_1_oe() throws Exception {
        assertEquals(Date.class, DateConverter.INSTANCE.getSupportedType());
    }

    public void testGetInstantMillis_Object_Chronology_1_oe() throws Exception {
        Date date = new Date(123L);
        long millis = DateConverter.INSTANCE.getInstantMillis(date, JULIAN);
        assertEquals(123L, millis);
    }

    public void testGetInstantMillis_Object_Chronology_2_oe() throws Exception {
        Date date = new Date(123L);
        long millis = DateConverter.INSTANCE.getInstantMillis(date, JULIAN);
        // removed other assertion
        assertEquals(123L, DateConverter.INSTANCE.getInstantMillis(date, (Chronology) null));
    }

    public void testGetChronology_Object_Zone_1_oe() throws Exception {
        assertEquals(ISO_PARIS, DateConverter.INSTANCE.getChronology(new Date(123L), PARIS));
    }

    public void testGetChronology_Object_Zone_2_oe() throws Exception {
        // removed other assertion
        assertEquals(ISO, DateConverter.INSTANCE.getChronology(new Date(123L), (DateTimeZone) null));
    }

    public void testGetChronology_Object_Chronology_1_oe() throws Exception {
        assertEquals(JULIAN, DateConverter.INSTANCE.getChronology(new Date(123L), JULIAN));
    }

    public void testGetChronology_Object_Chronology_2_oe() throws Exception {
        // removed other assertion
        assertEquals(ISO, DateConverter.INSTANCE.getChronology(new Date(123L), (Chronology) null));
    }

    public void testGetPartialValues_1_oe() throws Exception {
        TimeOfDay tod = new TimeOfDay();
        int[] expected = COPTIC.get(tod, 12345678L);
        int[] actual = DateConverter.INSTANCE.getPartialValues(tod, new Date(12345678L), COPTIC);
        assertEquals(true, Arrays.equals(expected, actual));
    }

    public void testToString_1_oe() {
        assertEquals("Converter[java.util.Date]", DateConverter.INSTANCE.toString());
    }

}
