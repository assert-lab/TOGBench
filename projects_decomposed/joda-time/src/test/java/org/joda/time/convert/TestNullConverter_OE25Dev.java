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
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableInterval;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;
import org.joda.time.TimeOfDay;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * This class is a Junit unit test for NullConverter.
 *
 * @author Stephen Colebourne
 */
public class TestNullConverter_OE25Dev extends TestCase {

    private long TEST_TIME_NOW =
            20 * DateTimeConstants.MILLIS_PER_DAY
            + 10L * DateTimeConstants.MILLIS_PER_HOUR
            + 20L * DateTimeConstants.MILLIS_PER_MINUTE
            + 30L * DateTimeConstants.MILLIS_PER_SECOND
            + 40L;
            
    private static final DateTimeZone UTC = DateTimeZone.UTC;
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final Chronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    private static Chronology ISO;
    private static Chronology JULIAN;
    
    private DateTimeZone zone = null;
    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestNullConverter.class);
    }

    public TestNullConverter_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        originalDateTimeZone = DateTimeZone.getDefault();
        originalTimeZone = TimeZone.getDefault();
        originalLocale = Locale.getDefault();
        DateTimeZone.setDefault(DateTimeZone.forID("Europe/London"));
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        Locale.setDefault(Locale.UK);
        
        ISO = ISOChronology.getInstance();
        JULIAN = JulianChronology.getInstance();
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(originalDateTimeZone);
        TimeZone.setDefault(originalTimeZone);
        Locale.setDefault(originalLocale);
        originalDateTimeZone = null;
        originalTimeZone = null;
        originalLocale = null;
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testSingleton_1_oe() throws Exception {
        Class cls = NullConverter.class;
        assertEquals(false, Modifier.isPublic(cls.getModifiers()));
    }

    public void testSingleton_2_oe() throws Exception {
        Class cls = NullConverter.class;
        // removed other assertion
        assertEquals(false, Modifier.isProtected(cls.getModifiers()));
    }

    public void testSingleton_3_oe() throws Exception {
        Class cls = NullConverter.class;
        // removed other assertion
        // removed other assertion
        assertEquals(false, Modifier.isPrivate(cls.getModifiers()));
    }

    public void testSingleton_4_oe() throws Exception {
        Class cls = NullConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1, cls.getDeclaredConstructors().length);
    }

    public void testSingleton_5_oe() throws Exception {
        Class cls = NullConverter.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        assertEquals(true, Modifier.isProtected(con.getModifiers()));
    }

    public void testSingleton_6_oe() throws Exception {
        Class cls = NullConverter.class;
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
        Class cls = NullConverter.class;
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
        Class cls = NullConverter.class;
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
        assertEquals(null, NullConverter.INSTANCE.getSupportedType());
    }

    public void testGetInstantMillis_Object_Chronology_1_oe() throws Exception {
        assertEquals(TEST_TIME_NOW, NullConverter.INSTANCE.getInstantMillis(null, JULIAN));
    }

    public void testGetInstantMillis_Object_Chronology_2_oe() throws Exception {
        // removed other assertion
        assertEquals(TEST_TIME_NOW, NullConverter.INSTANCE.getInstantMillis(null, (Chronology) null));
    }

    public void testGetChronology_Object_Zone_1_oe() throws Exception {
        assertEquals(ISO_PARIS, NullConverter.INSTANCE.getChronology(null, PARIS));
    }

    public void testGetChronology_Object_Zone_2_oe() throws Exception {
        // removed other assertion
        assertEquals(ISO, NullConverter.INSTANCE.getChronology(null, (DateTimeZone) null));
    }

    public void testGetChronology_Object_Chronology_1_oe() throws Exception {
        assertEquals(JULIAN, NullConverter.INSTANCE.getChronology(null, JULIAN));
    }

    public void testGetChronology_Object_Chronology_2_oe() throws Exception {
        // removed other assertion
        assertEquals(ISO, NullConverter.INSTANCE.getChronology(null, (Chronology) null));
    }

    public void testGetPartialValues_1_oe() throws Exception {
        TimeOfDay tod = new TimeOfDay();
        int[] expected = new int[] {10 + 1, 20, 30, 40}; // now
        int[] actual = NullConverter.INSTANCE.getPartialValues(tod, null, ISOChronology.getInstance());
        assertEquals(true, Arrays.equals(expected, actual));
    }

    public void testGetDurationMillis_Object_1_oe() throws Exception {
        assertEquals(0L, NullConverter.INSTANCE.getDurationMillis(null));
    }

    public void testGetPeriodType_Object_1_oe() throws Exception {
        assertEquals(PeriodType.standard(), NullConverter.INSTANCE.getPeriodType(null));
    }

    public void testSetInto_Object_1_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.millis());
        NullConverter.INSTANCE.setInto(m, null, null);
        assertEquals(0L, m.getMillis());
    }

    public void testIsReadableInterval_Object_Chronology_1_oe() throws Exception {
        assertEquals(false, NullConverter.INSTANCE.isReadableInterval(null, null));
    }

    public void testSetInto_Object_Chronology1_1_oe() throws Exception {
        MutableInterval m = new MutableInterval(1000L, 2000L, GJChronology.getInstance());
        NullConverter.INSTANCE.setInto(m, null, null);
        assertEquals(TEST_TIME_NOW, m.getStartMillis());
    }

    public void testSetInto_Object_Chronology1_2_oe() throws Exception {
        MutableInterval m = new MutableInterval(1000L, 2000L, GJChronology.getInstance());
        NullConverter.INSTANCE.setInto(m, null, null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW, m.getEndMillis());
    }

    public void testSetInto_Object_Chronology1_3_oe() throws Exception {
        MutableInterval m = new MutableInterval(1000L, 2000L, GJChronology.getInstance());
        NullConverter.INSTANCE.setInto(m, null, null);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(), m.getChronology());
    }

    public void testSetInto_Object_Chronology2_1_oe() throws Exception {
        MutableInterval m = new MutableInterval(1000L, 2000L, GJChronology.getInstance());
        NullConverter.INSTANCE.setInto(m, null, CopticChronology.getInstance());
        assertEquals(TEST_TIME_NOW, m.getStartMillis());
    }

    public void testSetInto_Object_Chronology2_2_oe() throws Exception {
        MutableInterval m = new MutableInterval(1000L, 2000L, GJChronology.getInstance());
        NullConverter.INSTANCE.setInto(m, null, CopticChronology.getInstance());
        // removed other assertion
        assertEquals(TEST_TIME_NOW, m.getEndMillis());
    }

    public void testSetInto_Object_Chronology2_3_oe() throws Exception {
        MutableInterval m = new MutableInterval(1000L, 2000L, GJChronology.getInstance());
        NullConverter.INSTANCE.setInto(m, null, CopticChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(CopticChronology.getInstance(), m.getChronology());
    }

    public void testToString_1_oe() {
        assertEquals("Converter[null]", NullConverter.INSTANCE.toString());
    }

}
