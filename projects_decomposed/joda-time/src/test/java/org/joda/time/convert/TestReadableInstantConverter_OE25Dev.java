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
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.TimeOfDay;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * This class is a Junit unit test for ReadableInstantConverter.
 *
 * @author Stephen Colebourne
 */
public class TestReadableInstantConverter_OE25Dev extends TestCase {

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
        return new TestSuite(TestReadableInstantConverter_OE25Dev.class);
    }

    public TestReadableInstantConverter_OE25Dev(String name) {
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
        Class cls = ReadableInstantConverter.class;
        assertEquals(false,Modifier.isPublic(cls.getModifiers()));
    }

    public void testSingleton_2_oe() throws Exception {
        Class cls = ReadableInstantConverter.class;
        // removed other assertion
        assertEquals(false,Modifier.isProtected(cls.getModifiers()));
    }

    public void testSingleton_3_oe() throws Exception {
        Class cls = ReadableInstantConverter.class;
        // removed other assertion
        // removed other assertion
        assertEquals(false,Modifier.isPrivate(cls.getModifiers()));
    }

    public void testSingleton_4_oe() throws Exception {
        Class cls = ReadableInstantConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1,cls.getDeclaredConstructors().length);
    }

    public void testSingleton_5_oe() throws Exception {
        Class cls = ReadableInstantConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        assertEquals(true,Modifier.isProtected(con.getModifiers()));
    }

    public void testSingleton_6_oe() throws Exception {
        Class cls = ReadableInstantConverter.class;
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
        Class cls = ReadableInstantConverter.class;
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
        Class cls = ReadableInstantConverter.class;
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
        assertEquals(ReadableInstant.class,ReadableInstantConverter.INSTANCE.getSupportedType());
    }

    public void testGetInstantMillis_Object_Chronology_1_oe() throws Exception {
        assertEquals(123L,ReadableInstantConverter.INSTANCE.getInstantMillis(new Instant(123L),JULIAN));
    }

    public void testGetInstantMillis_Object_Chronology_2_oe() throws Exception {
        // removed other assertion
        assertEquals(123L,ReadableInstantConverter.INSTANCE.getInstantMillis(new DateTime(123L),JULIAN));
    }

    public void testGetInstantMillis_Object_Chronology_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(123L,ReadableInstantConverter.INSTANCE.getInstantMillis(new Instant(123L),(Chronology)null));
    }

    public void testGetInstantMillis_Object_Chronology_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(123L,ReadableInstantConverter.INSTANCE.getInstantMillis(new DateTime(123L),(Chronology)null));
    }

    public void testGetChronology_Object_Zone_1_oe() throws Exception {
        assertEquals(ISO_PARIS,ReadableInstantConverter.INSTANCE.getChronology(new Instant(123L),PARIS));
    }

    public void testGetChronology_Object_Zone_2_oe() throws Exception {
        // removed other assertion
        assertEquals(ISO_PARIS,ReadableInstantConverter.INSTANCE.getChronology(new DateTime(123L),PARIS));
    }

    public void testGetChronology_Object_Zone_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(ISO,ReadableInstantConverter.INSTANCE.getChronology(new Instant(123L),DateTimeZone.getDefault()));
    }

    public void testGetChronology_Object_Zone_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISO,ReadableInstantConverter.INSTANCE.getChronology(new DateTime(123L),DateTimeZone.getDefault()));
    }

    public void testGetChronology_Object_Zone_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISO,ReadableInstantConverter.INSTANCE.getChronology(new Instant(123L),(DateTimeZone)null));
    }

    public void testGetChronology_Object_Zone_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISO,ReadableInstantConverter.INSTANCE.getChronology(new DateTime(123L),(DateTimeZone)null));
    }

    public void testGetChronology_Object_Zone_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(ISO_PARIS,ReadableInstantConverter.INSTANCE.getChronology(new DateTime(123L,new MockBadChronology()),PARIS));
    }

    public void testGetChronology_Object_Zone_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        
        MutableDateTime mdt = new MutableDateTime() {
            @Override
            public Chronology getChronology() {
                return null; // bad
            }
        };
        assertEquals(ISO_PARIS,ReadableInstantConverter.INSTANCE.getChronology(mdt,PARIS));
    }

    public void testGetChronology_Object_nullChronology_1_oe() throws Exception {
        assertEquals(ISO.withUTC(),ReadableInstantConverter.INSTANCE.getChronology(new Instant(123L),(Chronology)null));
    }

    public void testGetChronology_Object_nullChronology_2_oe() throws Exception {
        // removed other assertion
        assertEquals(ISO,ReadableInstantConverter.INSTANCE.getChronology(new DateTime(123L),(Chronology)null));
    }

    public void testGetChronology_Object_nullChronology_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        
        MutableDateTime mdt = new MutableDateTime() {
            @Override
            public Chronology getChronology() {
                return null; // bad
            }
        };
        assertEquals(ISO,ReadableInstantConverter.INSTANCE.getChronology(mdt,(Chronology)null));
    }

    public void testGetChronology_Object_Chronology_1_oe() throws Exception {
        assertEquals(JULIAN,ReadableInstantConverter.INSTANCE.getChronology(new Instant(123L),JULIAN));
    }

    public void testGetChronology_Object_Chronology_2_oe() throws Exception {
        // removed other assertion
        assertEquals(JULIAN,ReadableInstantConverter.INSTANCE.getChronology(new DateTime(123L),JULIAN));
    }

    public void testGetPartialValues_1_oe() throws Exception {
        TimeOfDay tod = new TimeOfDay();
        int[] expected = ISOChronology.getInstance().get(tod, 12345678L);
        int[] actual = ReadableInstantConverter.INSTANCE.getPartialValues(tod, new Instant(12345678L), ISOChronology.getInstance());
        assertEquals(true,Arrays.equals(expected,actual));
    }

    public void testToString_1_oe() {
        assertEquals("Converter[org.joda.time.ReadableInstant]",ReadableInstantConverter.INSTANCE.toString());
    }

}
