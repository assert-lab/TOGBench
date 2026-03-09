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

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableInterval;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;
import org.joda.time.TimeOfDay;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * This class is a Junit unit test for StringConverter.
 *
 * @author Stephen Colebourne
 */
public class TestStringConverter_OE25Dev extends TestCase {

    private static final DateTimeZone ONE_HOUR = DateTimeZone.forOffsetHours(1);
    private static final DateTimeZone SIX = DateTimeZone.forOffsetHours(6);
    private static final DateTimeZone SEVEN = DateTimeZone.forOffsetHours(7);
    private static final DateTimeZone EIGHT = DateTimeZone.forOffsetHours(8);
    private static final DateTimeZone UTC = DateTimeZone.UTC;
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final Chronology ISO_EIGHT = ISOChronology.getInstance(EIGHT);
    private static final Chronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    private static final Chronology ISO_LONDON = ISOChronology.getInstance(LONDON);
    private static Chronology ISO;
    private static Chronology JULIAN;
    
    private DateTimeZone zone = null;
    private Locale locale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestStringConverter_OE25Dev.class);
    }

    public TestStringConverter_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        zone = DateTimeZone.getDefault();
        locale = Locale.getDefault();
        DateTimeZone.setDefault(LONDON);
        Locale.setDefault(Locale.UK);
        
        JULIAN = JulianChronology.getInstance();
        ISO = ISOChronology.getInstance();
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeZone.setDefault(zone);
        Locale.setDefault(locale);
        zone = null;
    }

    //-----------------------------------------------------------------------
    public void testSingleton() throws Exception {
        Class cls = StringConverter.class;
        assertEquals(false,Modifier.isPublic(cls.getModifiers()));
        assertEquals(false,Modifier.isProtected(cls.getModifiers()));
        assertEquals(false,Modifier.isPrivate(cls.getModifiers()));
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1,cls.getDeclaredConstructors().length);
        assertEquals(true,Modifier.isProtected(con.getModifiers()));
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertEquals(false,Modifier.isPublic(fld.getModifiers()));
        assertEquals(false,Modifier.isProtected(fld.getModifiers()));
        assertEquals(false,Modifier.isPrivate(fld.getModifiers()));
    }

    //-----------------------------------------------------------------------
    public void testSupportedType() throws Exception {
        assertEquals(String.class,StringConverter.INSTANCE.getSupportedType());
    }

    //-----------------------------------------------------------------------
    public void testGetInstantMillis_Object() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24:48.501+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004T+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06T+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-161T+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-W24-3T+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 7, 0, 0, 0, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-W24T+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 9, 12, 24, 0, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24:48+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 9, 12, 30, 0, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12.5+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 9, 12, 24, 30, 0, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24.5+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 500, EIGHT);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24:48.5+08:00",ISO_EIGHT));
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24:48.501",ISO));
    }

    public void testGetInstantMillis_Object_Zone() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24:48.501+02:00",ISO_PARIS));
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24:48.501",ISO_PARIS));
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, LONDON);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24:48.501+01:00",ISO_LONDON));
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, LONDON);
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24:48.501",ISO_LONDON));
    }

    public void testGetInstantMillis_Object_Chronology() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, JulianChronology.getInstance(LONDON));
        assertEquals(dt.getMillis(),StringConverter.INSTANCE.getInstantMillis("2004-06-09T12:24:48.501+01:00",JULIAN));
    }

    public void testGetInstantMillisInvalid() {
        try {
            StringConverter.INSTANCE.getInstantMillis("", (Chronology) null);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            StringConverter.INSTANCE.getInstantMillis("X", (Chronology) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testGetChronology_Object_Zone() throws Exception {
        assertEquals(ISOChronology.getInstance(PARIS),StringConverter.INSTANCE.getChronology("2004-06-09T12:24:48.501+01:00",PARIS));
        assertEquals(ISOChronology.getInstance(PARIS),StringConverter.INSTANCE.getChronology("2004-06-09T12:24:48.501",PARIS));
        assertEquals(ISOChronology.getInstance(LONDON),StringConverter.INSTANCE.getChronology("2004-06-09T12:24:48.501+01:00",(DateTimeZone)null));
        assertEquals(ISOChronology.getInstance(LONDON),StringConverter.INSTANCE.getChronology("2004-06-09T12:24:48.501",(DateTimeZone)null));
    }

    public void testGetChronology_Object_Chronology() throws Exception {
        assertEquals(JulianChronology.getInstance(LONDON),StringConverter.INSTANCE.getChronology("2004-06-09T12:24:48.501+01:00",JULIAN));
        assertEquals(JulianChronology.getInstance(LONDON),StringConverter.INSTANCE.getChronology("2004-06-09T12:24:48.501",JULIAN));
        assertEquals(ISOChronology.getInstance(LONDON),StringConverter.INSTANCE.getChronology("2004-06-09T12:24:48.501+01:00",(Chronology)null));
        assertEquals(ISOChronology.getInstance(LONDON),StringConverter.INSTANCE.getChronology("2004-06-09T12:24:48.501",(Chronology)null));
    }

    //-----------------------------------------------------------------------
    public void testGetPartialValues() throws Exception {
        TimeOfDay tod = new TimeOfDay();
        int[] expected = new int[] {3, 4, 5, 6};
        int[] actual = StringConverter.INSTANCE.getPartialValues(tod, "T03:04:05.006", ISOChronology.getInstance());
        assertEquals(true,Arrays.equals(expected,actual));
    }

    //-----------------------------------------------------------------------
    public void testGetDateTime() throws Exception {
        DateTime base = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        DateTime test = new DateTime(base.toString(), PARIS);
        assertEquals(base,test);
    }

    public void testGetDateTime1() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+01:00");
        assertEquals(2004,test.getYear());
        assertEquals(6,test.getMonthOfYear());
        assertEquals(9,test.getDayOfMonth());
        assertEquals(12,test.getHourOfDay());
        assertEquals(24,test.getMinuteOfHour());
        assertEquals(48,test.getSecondOfMinute());
        assertEquals(501,test.getMillisOfSecond());
        assertEquals(LONDON,test.getZone());
    }

    public void testGetDateTime2() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501");
        assertEquals(2004,test.getYear());
        assertEquals(6,test.getMonthOfYear());
        assertEquals(9,test.getDayOfMonth());
        assertEquals(12,test.getHourOfDay());
        assertEquals(24,test.getMinuteOfHour());
        assertEquals(48,test.getSecondOfMinute());
        assertEquals(501,test.getMillisOfSecond());
        assertEquals(LONDON,test.getZone());
    }

    public void testGetDateTime3() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", PARIS);
        assertEquals(2004,test.getYear());
        assertEquals(6,test.getMonthOfYear());
        assertEquals(9,test.getDayOfMonth());
        assertEquals(12,test.getHourOfDay());
        assertEquals(24,test.getMinuteOfHour());
        assertEquals(48,test.getSecondOfMinute());
        assertEquals(501,test.getMillisOfSecond());
        assertEquals(PARIS,test.getZone());
    }

    public void testGetDateTime4() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", PARIS);
        assertEquals(2004,test.getYear());
        assertEquals(6,test.getMonthOfYear());
        assertEquals(9,test.getDayOfMonth());
        assertEquals(12,test.getHourOfDay());
        assertEquals(24,test.getMinuteOfHour());
        assertEquals(48,test.getSecondOfMinute());
        assertEquals(501,test.getMillisOfSecond());
        assertEquals(PARIS,test.getZone());
    }

    public void testGetDateTime5() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", JulianChronology.getInstance(PARIS));
        assertEquals(2004,test.getYear());
        assertEquals(6,test.getMonthOfYear());
        assertEquals(9,test.getDayOfMonth());
        assertEquals(12,test.getHourOfDay());
        assertEquals(24,test.getMinuteOfHour());
        assertEquals(48,test.getSecondOfMinute());
        assertEquals(501,test.getMillisOfSecond());
        assertEquals(PARIS,test.getZone());
    }

    public void testGetDateTime6() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", JulianChronology.getInstance(PARIS));
        assertEquals(2004,test.getYear());
        assertEquals(6,test.getMonthOfYear());
        assertEquals(9,test.getDayOfMonth());
        assertEquals(12,test.getHourOfDay());
        assertEquals(24,test.getMinuteOfHour());
        assertEquals(48,test.getSecondOfMinute());
        assertEquals(501,test.getMillisOfSecond());
        assertEquals(PARIS,test.getZone());
    }

    //-----------------------------------------------------------------------
    public void testGetDurationMillis_Object1() throws Exception {
        long millis = StringConverter.INSTANCE.getDurationMillis("PT12.345S");
        assertEquals(12345,millis);
        
        millis = StringConverter.INSTANCE.getDurationMillis("pt12.345s");
        assertEquals(12345,millis);
        
        millis = StringConverter.INSTANCE.getDurationMillis("pt12s");
        assertEquals(12000,millis);
        
        millis = StringConverter.INSTANCE.getDurationMillis("pt12.s");
        assertEquals(12000,millis);
        
        millis = StringConverter.INSTANCE.getDurationMillis("pt-12.32s");
        assertEquals(-12320,millis);
        
        millis = StringConverter.INSTANCE.getDurationMillis("pt-0.32s");
        assertEquals(-320,millis);

        millis = StringConverter.INSTANCE.getDurationMillis("pt-0.0s");
        assertEquals(0,millis);

        millis = StringConverter.INSTANCE.getDurationMillis("pt0.0s");
        assertEquals(0,millis);

        millis = StringConverter.INSTANCE.getDurationMillis("pt12.3456s");
        assertEquals(12345,millis);
    }

    public void testGetDurationMillis_Object2() throws Exception {
        try {
            StringConverter.INSTANCE.getDurationMillis("P2Y6M9DXYZ");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            StringConverter.INSTANCE.getDurationMillis("PTS");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            StringConverter.INSTANCE.getDurationMillis("XT0S");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            StringConverter.INSTANCE.getDurationMillis("PX0S");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            StringConverter.INSTANCE.getDurationMillis("PT0X");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            StringConverter.INSTANCE.getDurationMillis("PTXS");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            StringConverter.INSTANCE.getDurationMillis("PT0.0.0S");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            StringConverter.INSTANCE.getDurationMillis("PT0-00S");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            StringConverter.INSTANCE.getDurationMillis("PT-.001S");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testGetPeriodType_Object() throws Exception {
        assertEquals(PeriodType.standard(),StringConverter.INSTANCE.getPeriodType("P2Y6M9D"));
    }

    public void testSetIntoPeriod_Object1() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y6M9DT12H24M48S", null);
        assertEquals(2,m.getYears());
        assertEquals(6,m.getMonths());
        assertEquals(9,m.getDays());
        assertEquals(12,m.getHours());
        assertEquals(24,m.getMinutes());
        assertEquals(48,m.getSeconds());
        assertEquals(0,m.getMillis());
    }

    public void testSetIntoPeriod_Object2() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48S", null);
        assertEquals(2,m.getYears());
        assertEquals(4,m.getWeeks());
        assertEquals(3,m.getDays());
        assertEquals(12,m.getHours());
        assertEquals(24,m.getMinutes());
        assertEquals(48,m.getSeconds());
        assertEquals(0,m.getMillis());
    }        

    public void testSetIntoPeriod_Object3() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48.034S", null);
        assertEquals(2,m.getYears());
        assertEquals(4,m.getWeeks());
        assertEquals(3,m.getDays());
        assertEquals(12,m.getHours());
        assertEquals(24,m.getMinutes());
        assertEquals(48,m.getSeconds());
        assertEquals(34,m.getMillis());
    }        

    public void testSetIntoPeriod_Object4() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M.056S", null);
        assertEquals(2,m.getYears());
        assertEquals(4,m.getWeeks());
        assertEquals(3,m.getDays());
        assertEquals(12,m.getHours());
        assertEquals(24,m.getMinutes());
        assertEquals(0,m.getSeconds());
        assertEquals(56,m.getMillis());
    }        

    public void testSetIntoPeriod_Object5() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.S", null);
        assertEquals(2,m.getYears());
        assertEquals(4,m.getWeeks());
        assertEquals(3,m.getDays());
        assertEquals(12,m.getHours());
        assertEquals(24,m.getMinutes());
        assertEquals(56,m.getSeconds());
        assertEquals(0,m.getMillis());
    }        

    public void testSetIntoPeriod_Object6() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.1234567S", null);
        assertEquals(2,m.getYears());
        assertEquals(4,m.getWeeks());
        assertEquals(3,m.getDays());
        assertEquals(12,m.getHours());
        assertEquals(24,m.getMinutes());
        assertEquals(56,m.getSeconds());
        assertEquals(123,m.getMillis());
    }        

    public void testSetIntoPeriod_Object7() throws Exception {
        MutablePeriod m = new MutablePeriod(1, 0, 1, 1, 1, 1, 1, 1, PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3D", null);
        assertEquals(2,m.getYears());
        assertEquals(4,m.getWeeks());
        assertEquals(3,m.getDays());
        assertEquals(0,m.getHours());
        assertEquals(0,m.getMinutes());
        assertEquals(0,m.getSeconds());
        assertEquals(0,m.getMillis());
    }        

    public void testSetIntoPeriod_Object8() throws Exception {
        MutablePeriod m = new MutablePeriod();
        try {
            StringConverter.INSTANCE.setInto(m, "", null);
            fail();
        } catch (IllegalArgumentException ex) {}
        
        try {
            StringConverter.INSTANCE.setInto(m, "PXY", null);
            fail();
        } catch (IllegalArgumentException ex) {}
        
        try {
            StringConverter.INSTANCE.setInto(m, "PT0SXY", null);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48SX", null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testIsReadableInterval_Object_Chronology() throws Exception {
        assertEquals(false,StringConverter.INSTANCE.isReadableInterval("",null));
    }

    public void testSetIntoInterval_Object_Chronology1() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2004-06-09/P1Y2M", null);
        assertEquals(new DateTime(2004,6,9,0,0,0,0),m.getStart());
        assertEquals(new DateTime(2005,8,9,0,0,0,0),m.getEnd());
        assertEquals(ISOChronology.getInstance(),m.getChronology());
    }

    public void testSetIntoInterval_Object_Chronology2() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "P1Y2M/2004-06-09", null);
        assertEquals(new DateTime(2003,4,9,0,0,0,0),m.getStart());
        assertEquals(new DateTime(2004,6,9,0,0,0,0),m.getEnd());
        assertEquals(ISOChronology.getInstance(),m.getChronology());
    }

    public void testSetIntoInterval_Object_Chronology3() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09/2004-06-09", null);
        assertEquals(new DateTime(2003,8,9,0,0,0,0),m.getStart());
        assertEquals(new DateTime(2004,6,9,0,0,0,0),m.getEnd());
        assertEquals(ISOChronology.getInstance(),m.getChronology());
    }

    public void testSetIntoInterval_Object_Chronology4() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2004-06-09T+06:00/P1Y2M", null);
        assertEquals(new DateTime(2004,6,9,0,0,0,0,SIX).withChronology(null),m.getStart());
        assertEquals(new DateTime(2005,8,9,0,0,0,0,SIX).withChronology(null),m.getEnd());
        assertEquals(ISOChronology.getInstance(),m.getChronology());
    }

    public void testSetIntoInterval_Object_Chronology5() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "P1Y2M/2004-06-09T+06:00", null);
        assertEquals(new DateTime(2003,4,9,0,0,0,0,SIX).withChronology(null),m.getStart());
        assertEquals(new DateTime(2004,6,9,0,0,0,0,SIX).withChronology(null),m.getEnd());
        assertEquals(ISOChronology.getInstance(),m.getChronology());
    }

    public void testSetIntoInterval_Object_Chronology6() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09T+06:00/2004-06-09T+07:00", null);
        assertEquals(new DateTime(2003,8,9,0,0,0,0,SIX).withChronology(null),m.getStart());
        assertEquals(new DateTime(2004,6,9,0,0,0,0,SEVEN).withChronology(null),m.getEnd());
        assertEquals(ISOChronology.getInstance(),m.getChronology());
    }

    public void testSetIntoInterval_Object_Chronology7() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09/2004-06-09", BuddhistChronology.getInstance());
        assertEquals(new DateTime(2003,8,9,0,0,0,0,BuddhistChronology.getInstance()),m.getStart());
        assertEquals(new DateTime(2004,6,9,0,0,0,0,BuddhistChronology.getInstance()),m.getEnd());
        assertEquals(BuddhistChronology.getInstance(),m.getChronology());
    }

    public void testSetIntoInterval_Object_Chronology8() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09T+06:00/2004-06-09T+07:00", BuddhistChronology.getInstance(EIGHT));
        assertEquals(new DateTime(2003,8,9,0,0,0,0,BuddhistChronology.getInstance(SIX)).withZone(EIGHT),m.getStart());
        assertEquals(new DateTime(2004,6,9,0,0,0,0,BuddhistChronology.getInstance(SEVEN)).withZone(EIGHT),m.getEnd());
        assertEquals(BuddhistChronology.getInstance(EIGHT),m.getChronology());
    }

    public void testSetIntoIntervalEx_Object_Chronology1() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        try {
            StringConverter.INSTANCE.setInto(m, "", null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testSetIntoIntervalEx_Object_Chronology2() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        try {
            StringConverter.INSTANCE.setInto(m, "/", null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testSetIntoIntervalEx_Object_Chronology3() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        try {
            StringConverter.INSTANCE.setInto(m, "P1Y/", null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testSetIntoIntervalEx_Object_Chronology4() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        try {
            StringConverter.INSTANCE.setInto(m, "/P1Y", null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testSetIntoIntervalEx_Object_Chronology5() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        try {
            StringConverter.INSTANCE.setInto(m, "P1Y/P2Y", null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testToString() {
        assertEquals("Converter[java.lang.String]",StringConverter.INSTANCE.toString());
    }

    public void testSingleton_1_oe() throws Exception {
        Class cls = StringConverter.class;
        assertEquals(false, cls.isLocalClass());
    }

    public void testSingleton_2_oe() throws Exception {
        Class cls = StringConverter.class;
        assertEquals(false, cls.isLocalClass());
    }

    public void testSingleton_3_oe() throws Exception {
        Class cls = StringConverter.class;
        assertEquals(false, cls.isLocalClass());
    }

    public void testSingleton_4_oe() throws Exception {
        Class cls = StringConverter.class;
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1, con.getParameterTypes().length);
    }

    public void testSingleton_5_oe() throws Exception {
        Class cls = StringConverter.class;
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1, con.getParameterTypes().length);
    }

    public void testSingleton_6_oe() throws Exception {
        Class cls = StringConverter.class;
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertNotNull(fld);
    }

    public void testSingleton_7_oe() throws Exception {
        Class cls = StringConverter.class;
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertNotNull(fld);
    }

    public void testSingleton_8_oe() throws Exception {
        Class cls = StringConverter.class;
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertNotNull(fld);
    }

    public void testGetInstantMillis_Object_1_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        assertEquals(1286119648501L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_2_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_3_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_4_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_5_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_6_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_7_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 7, 0, 0, 0, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_8_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 7, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_9_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 7, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 0, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_10_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 7, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_11_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 7, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 30, 0, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_12_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 7, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 30, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 30, 0, EIGHT);
        assertEquals(1325376000000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_13_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 7, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 30, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 30, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 500, EIGHT);
        assertEquals(1372764400000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_14_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, EIGHT);
        
        dt = new DateTime(2004, 1, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 1, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 7, 0, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 30, 0, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 30, 0, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 500, EIGHT);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501);
        assertEquals(1372764400000L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_Zone_1_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        assertEquals(1286129648501L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_Zone_2_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        assertEquals(1286149648501L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_Zone_3_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, LONDON);
        assertEquals(1372709248501L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_Zone_4_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, LONDON);
        
        dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, LONDON);
        assertEquals(1372709248501L, dt.getMillis());
    }

    public void testGetInstantMillis_Object_Chronology_1_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 12, 24, 48, 501, JulianChronology.getInstance(LONDON));
        assertEquals(1262304448501L, dt.getMillis());
    }

    public void testGetChronology_Object_Zone_1_oe() throws Exception {
        Object a = ISOChronology.getInstance(PARIS);
// incorrect assertion         assertEquals(ISOChronology.getInstanceUTC(), a.getChronology());
    }

    public void testGetChronology_Object_Zone_2_oe() throws Exception {
        Object a = ISOChronology.getInstance(PARIS);
// incorrect assertion         assertEquals(ISOChronology.getInstanceUTC(), a.getChronology());
    }

    public void testGetChronology_Object_Zone_3_oe() throws Exception {
        Object a = ISOChronology.getInstance(LONDON);
// incorrect assertion         assertEquals(ISOChronology.getInstanceUTC(), a.getChronology());
    }

    public void testGetChronology_Object_Zone_4_oe() throws Exception {
        Object a = ISOChronology.getInstance(LONDON);
// incorrect assertion         assertEquals(ISOChronology.getInstanceUTC(), a.getChronology());
    }

    public void testGetChronology_Object_Chronology_1_oe() throws Exception {
        Object a = JulianChronology.getInstance(LONDON);
// incorrect assertion         assertEquals(ISOChronology.getInstanceUTC(), JulianChronology.getInstance(LONDON).getChronology());
    }

    public void testGetChronology_Object_Chronology_2_oe() throws Exception {
        Object a = JulianChronology.getInstance(LONDON);
// incorrect assertion         assertEquals(ISOChronology.getInstanceUTC(), a.getChronology());
    }

    public void testGetChronology_Object_Chronology_3_oe() throws Exception {
        Object a = ISOChronology.getInstance(LONDON);
// incorrect assertion         assertEquals(ISOChronology.getInstanceUTC(), a.getChronology());
    }

    public void testGetChronology_Object_Chronology_4_oe() throws Exception {
        Object a = ISOChronology.getInstance(LONDON);
// incorrect assertion         assertEquals(ISOChronology.getInstanceUTC(), a.getChronology());
    }

    public void testGetPartialValues_1_oe() throws Exception {
        TimeOfDay tod = new TimeOfDay();
        int[] expected = new int[] {3, 4, 5, 6};
        int[] actual = StringConverter.INSTANCE.getPartialValues(tod, "T03:04:05.006", ISOChronology.getInstance());
        assertNotNull(actual);
    }

    public void testGetDateTime_1_oe() throws Exception {
        DateTime base = new DateTime(2004, 6, 9, 12, 24, 48, 501, PARIS);
        DateTime test = new DateTime(base.toString(), PARIS);
        assertEquals("2004-06-09T12:24:48.501+0100", test.toString());
    }

    public void testGetDateTime1_2_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+01:00");
        assertEquals(6, test.getMonthOfYear());
    }

    public void testGetDateTime1_3_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+01:00");
        assertEquals(9, test.getDayOfMonth());
    }

    public void testGetDateTime1_4_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+01:00");
        assertEquals(12, test.getHourOfDay());
    }

    public void testGetDateTime1_5_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+01:00");
        assertEquals(24, test.getMinuteOfHour());
    }

    public void testGetDateTime1_6_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+01:00");
        assertEquals(24, test.getSecondOfMinute());
    }

    public void testGetDateTime1_7_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+01:00");
        assertEquals(501, test.getMillisOfSecond());
    }

    public void testGetDateTime2_1_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501");
        assertEquals(2004, test.getYear());
    }

    public void testGetDateTime2_2_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501");
        assertEquals(6, test.getMonthOfYear());
    }

    public void testGetDateTime2_3_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501");
        assertEquals(9, test.getDayOfMonth());
    }

    public void testGetDateTime2_4_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501");
        assertEquals(12, test.getHourOfDay());
    }

    public void testGetDateTime2_5_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501");
        assertEquals(24, test.getMinuteOfHour());
    }

    public void testGetDateTime2_6_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501");
        assertEquals(24, test.getSecondOfMinute());
    }

    public void testGetDateTime2_7_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501");
        assertEquals(501, test.getMillisOfSecond());
    }

    public void testGetDateTime3_1_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", PARIS);
        assertEquals(2004, test.getYear());
    }

    public void testGetDateTime3_2_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", PARIS);
        assertEquals(6, test.getMonthOfYear());
    }

    public void testGetDateTime3_3_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", PARIS);
        assertEquals(9, test.getDayOfMonth());
    }

    public void testGetDateTime3_4_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", PARIS);
        assertEquals(12, test.getHourOfDay());
    }

    public void testGetDateTime3_5_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", PARIS);
        assertEquals(24, test.getMinuteOfHour());
    }

    public void testGetDateTime3_6_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", PARIS);
        assertEquals(24, test.getSecondOfMinute());
    }

    public void testGetDateTime3_7_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", PARIS);
        assertEquals(501, test.getMillisOfSecond());
    }

    public void testGetDateTime3_8_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", PARIS);
        assertEquals("2004-06-09T12:24:48.501+02:00", test.toString());
    }

    public void testGetDateTime4_1_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", PARIS);
        assertEquals(2004, test.getYear());
    }

    public void testGetDateTime4_2_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", PARIS);
        assertEquals(6, test.getMonthOfYear());
    }

    public void testGetDateTime4_3_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", PARIS);
        assertEquals(9, test.getDayOfMonth());
    }

    public void testGetDateTime4_4_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", PARIS);
        assertEquals(12, test.getHourOfDay());
    }

    public void testGetDateTime4_5_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", PARIS);
        assertEquals(24, test.getMinuteOfHour());
    }

    public void testGetDateTime4_6_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", PARIS);
        assertEquals(24, test.getSecondOfMinute());
    }

    public void testGetDateTime4_7_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", PARIS);
        assertEquals(501, test.getMillisOfSecond());
    }

    public void testGetDateTime5_1_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", JulianChronology.getInstance(PARIS));
        assertEquals(2004, test.getYear());
    }

    public void testGetDateTime5_2_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", JulianChronology.getInstance(PARIS));
        assertEquals(6, test.getMonthOfYear());
    }

    public void testGetDateTime5_3_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", JulianChronology.getInstance(PARIS));
        assertEquals(9, test.getDayOfMonth());
    }

    public void testGetDateTime5_4_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", JulianChronology.getInstance(PARIS));
        assertEquals(12, test.getHourOfDay());
    }

    public void testGetDateTime5_5_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", JulianChronology.getInstance(PARIS));
        assertEquals(24, test.getMinuteOfHour());
    }

    public void testGetDateTime5_6_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", JulianChronology.getInstance(PARIS));
        assertEquals(24, test.getSecondOfMinute());
    }

    public void testGetDateTime5_7_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", JulianChronology.getInstance(PARIS));
        assertEquals(501, test.getMillisOfSecond());
    }

    public void testGetDateTime5_8_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501+02:00", JulianChronology.getInstance(PARIS));
        assertEquals("2004-06-09T12:24:48.501+02:00", test.toString());
    }

    public void testGetDateTime6_1_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", JulianChronology.getInstance(PARIS));
        assertEquals(2004, test.getYear());
    }

    public void testGetDateTime6_2_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", JulianChronology.getInstance(PARIS));
        assertEquals(6, test.getMonthOfYear());
    }

    public void testGetDateTime6_3_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", JulianChronology.getInstance(PARIS));
        assertEquals(9, test.getDayOfMonth());
    }

    public void testGetDateTime6_4_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", JulianChronology.getInstance(PARIS));
        assertEquals(12, test.getHourOfDay());
    }

    public void testGetDateTime6_5_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", JulianChronology.getInstance(PARIS));
        assertEquals(24, test.getMinuteOfHour());
    }

    public void testGetDateTime6_6_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", JulianChronology.getInstance(PARIS));
        assertEquals(24, test.getSecondOfMinute());
    }

    public void testGetDateTime6_7_oe() throws Exception {
        DateTime test = new DateTime("2004-06-09T12:24:48.501", JulianChronology.getInstance(PARIS));
        assertEquals(501, test.getMillisOfSecond());
    }

    public void testSetIntoPeriod_Object1_1_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y6M9DT12H24M48S", null);
        assertEquals(2, m.getYears());
    }

    public void testSetIntoPeriod_Object1_2_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y6M9DT12H24M48S", null);
        assertEquals(6, m.getMonths());
    }

    public void testSetIntoPeriod_Object1_3_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y6M9DT12H24M48S", null);
        assertEquals(9, m.getDays());
    }

    public void testSetIntoPeriod_Object1_4_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y6M9DT12H24M48S", null);
        assertEquals(12, m.getHours());
    }

    public void testSetIntoPeriod_Object1_5_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y6M9DT12H24M48S", null);
        assertEquals(24, m.getMinutes());
    }

    public void testSetIntoPeriod_Object1_6_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y6M9DT12H24M48S", null);
        assertEquals(48, m.getSeconds());
    }

    public void testSetIntoPeriod_Object1_7_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y6M9DT12H24M48S", null);
        assertEquals(48000, m.getMillis());
    }

    public void testSetIntoPeriod_Object2_1_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48S", null);
        assertEquals(2, m.getYears());
    }

    public void testSetIntoPeriod_Object2_2_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48S", null);
        assertEquals(4, m.getWeeks());
    }

    public void testSetIntoPeriod_Object2_3_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48S", null);
        assertEquals(0, m.getDays());
    }

    public void testSetIntoPeriod_Object2_4_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48S", null);
        assertEquals(12, m.getHours());
    }

    public void testSetIntoPeriod_Object2_5_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48S", null);
        assertEquals(24, m.getMinutes());
    }

    public void testSetIntoPeriod_Object2_6_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48S", null);
        assertEquals(48, m.getSeconds());
    }

    public void testSetIntoPeriod_Object2_7_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48S", null);
        assertEquals(48000, m.getMillis());
    }

    public void testSetIntoPeriod_Object3_1_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48.034S", null);
        assertEquals(2, m.getYears());
    }

    public void testSetIntoPeriod_Object3_2_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48.034S", null);
        assertEquals(4, m.getWeeks());
    }

    public void testSetIntoPeriod_Object3_4_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48.034S", null);
        assertEquals(12, m.getHours());
    }

    public void testSetIntoPeriod_Object3_5_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48.034S", null);
        assertEquals(24, m.getMinutes());
    }

    public void testSetIntoPeriod_Object3_6_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48.034S", null);
        assertEquals(48, m.getSeconds());
    }

    public void testSetIntoPeriod_Object3_7_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M48.034S", null);
        assertEquals(48034, m.getMillis());
    }

    public void testSetIntoPeriod_Object4_1_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M.056S", null);
        assertEquals(2, m.getYears());
    }

    public void testSetIntoPeriod_Object4_3_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M.056S", null);
        assertEquals(0, m.getDays());
    }

    public void testSetIntoPeriod_Object4_4_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M.056S", null);
        assertEquals(12, m.getHours());
    }

    public void testSetIntoPeriod_Object4_5_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M.056S", null);
        assertEquals(24, m.getMinutes());
    }

    public void testSetIntoPeriod_Object4_6_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M.056S", null);
        assertEquals(56, m.getSeconds());
    }

    public void testSetIntoPeriod_Object5_1_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.S", null);
        assertEquals(2, m.getYears());
    }

    public void testSetIntoPeriod_Object5_2_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.S", null);
        assertEquals(4, m.getWeeks());
    }

    public void testSetIntoPeriod_Object5_4_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.S", null);
        assertEquals(12, m.getHours());
    }

    public void testSetIntoPeriod_Object5_5_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.S", null);
        assertEquals(24, m.getMinutes());
    }

    public void testSetIntoPeriod_Object5_6_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.S", null);
        assertEquals(56, m.getSeconds());
    }

    public void testSetIntoPeriod_Object5_7_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.S", null);
        assertEquals(56, m.getMillis());
    }

    public void testSetIntoPeriod_Object6_1_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.1234567S", null);
        assertEquals(2, m.getYears());
    }

    public void testSetIntoPeriod_Object6_2_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.1234567S", null);
        assertEquals(4, m.getWeeks());
    }

    public void testSetIntoPeriod_Object6_3_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.1234567S", null);
        assertEquals(0, m.getDays());
    }

    public void testSetIntoPeriod_Object6_4_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3DT12H24M56.1234567S", null);
        assertEquals(12, m.getHours());
    }

    public void testSetIntoPeriod_Object7_1_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(1, 0, 1, 1, 1, 1, 1, 1, PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3D", null);
        assertEquals(2, m.getYears());
    }

    public void testSetIntoPeriod_Object7_2_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(1, 0, 1, 1, 1, 1, 1, 1, PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3D", null);
        assertEquals(4, m.getWeeks());
    }

    public void testSetIntoPeriod_Object7_3_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(1, 0, 1, 1, 1, 1, 1, 1, PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3D", null);
        assertEquals(1, m.getDays());
    }

    public void testSetIntoPeriod_Object7_4_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(1, 0, 1, 1, 1, 1, 1, 1, PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3D", null);
        assertEquals(1, m.getHours());
    }

    public void testSetIntoPeriod_Object7_5_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(1, 0, 1, 1, 1, 1, 1, 1, PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3D", null);
        assertEquals(1, m.getMinutes());
    }

    public void testSetIntoPeriod_Object7_6_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(1, 0, 1, 1, 1, 1, 1, 1, PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3D", null);
        assertEquals(1, m.getSeconds());
    }

    public void testSetIntoPeriod_Object7_7_oe() throws Exception {
        MutablePeriod m = new MutablePeriod(1, 0, 1, 1, 1, 1, 1, 1, PeriodType.yearWeekDayTime());
        StringConverter.INSTANCE.setInto(m, "P2Y4W3D", null);
        assertEquals(1, m.getMillis());
    }

    public void testIsReadableInterval_Object_Chronology_1_oe() throws Exception {
        boolean a = false;
        assertEquals(true, a);
    }

    public void testSetIntoInterval_Object_Chronology1_1_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2004-06-09/P1Y2M", null);
        assertEquals(2004, m.getStart().getYear());
    }

    public void testSetIntoInterval_Object_Chronology1_2_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2004-06-09/P1Y2M", null);
        assertEquals(2004, m.getEnd().getYear());
    }

    public void testSetIntoInterval_Object_Chronology2_1_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "P1Y2M/2004-06-09", null);
// incorrect assertion         assertEquals(new DateTime(-1000L, 1, 1, 0, 0, 0, 0), m.getStart());
    }

    public void testSetIntoInterval_Object_Chronology3_1_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09/2004-06-09", null);
        assertEquals(20030809000000000L, m.getStart().getMillis());
    }

    public void testSetIntoInterval_Object_Chronology3_2_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09/2004-06-09", null);
        assertEquals(200406100000000L, m.getEndMillis());
    }

    public void testSetIntoInterval_Object_Chronology3_3_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09/2004-06-09", null);
        assertEquals("2003-08-09/2004-06-09", m.toString());
    }

    public void testSetIntoInterval_Object_Chronology4_1_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2004-06-09T+06:00/P1Y2M", null);
        assertEquals(2004, m.getStart().getYear());
    }

    public void testSetIntoInterval_Object_Chronology4_2_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2004-06-09T+06:00/P1Y2M", null);
        assertEquals(2004, m.getEnd().getYear());
    }

    public void testSetIntoInterval_Object_Chronology4_3_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2004-06-09T+06:00/P1Y2M", null);
        assertEquals("2004-06-09T00:00:00.000000000+06:00/2004-07-09T00:00:00.000000000+06:00", m.toString());
    }

    public void testSetIntoInterval_Object_Chronology5_1_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "P1Y2M/2004-06-09T+06:00", null);
        assertEquals(1372706400000L, m.getStart().getMillis());
    }

    public void testSetIntoInterval_Object_Chronology5_2_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "P1Y2M/2004-06-09T+06:00", null);
        assertEquals(2004, m.getEnd().getYear());
    }

    public void testSetIntoInterval_Object_Chronology5_3_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "P1Y2M/2004-06-09T+06:00", null);
        assertEquals("2004-06-09T06:00:00.000000000+06:00/2004-06-09T06:00:00.000000000+06:00", m.toString());
    }

    public void testSetIntoInterval_Object_Chronology6_1_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09T+06:00/2004-06-09T+07:00", null);
        assertEquals(20030731060000000L, m.getStart().getMillis());
    }

    public void testSetIntoInterval_Object_Chronology6_2_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09T+06:00/2004-06-09T+07:00", null);
        assertEquals("2004-06-09T07:00:00.000+01:00", m.getEnd().toString());
    }

    public void testSetIntoInterval_Object_Chronology6_3_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09T+06:00/2004-06-09T+07:00", null);
        assertEquals("2003-08-09T06:00:00.000000000Z/2004-06-09T07:00:00.000000000Z", m.toString());
    }

    public void testSetIntoInterval_Object_Chronology7_1_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09/2004-06-09", BuddhistChronology.getInstance());
        assertEquals(new DateTime(20030809000000000L, BuddhistChronology.getInstance()), m.getStart());
    }

    public void testSetIntoInterval_Object_Chronology7_2_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09/2004-06-09", BuddhistChronology.getInstance());
        assertEquals(200406100000L, m.getEndMillis());
    }

    public void testSetIntoInterval_Object_Chronology8_1_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09T+06:00/2004-06-09T+07:00", BuddhistChronology.getInstance(EIGHT));
        assertEquals(20030731060000000L, m.getStart().getMillis());
    }

    public void testSetIntoInterval_Object_Chronology8_2_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09T+06:00/2004-06-09T+07:00", BuddhistChronology.getInstance(EIGHT));
        assertEquals(2004, m.getEnd().getYear());
    }

    public void testSetIntoInterval_Object_Chronology8_3_oe() throws Exception {
        MutableInterval m = new MutableInterval(-1000L, 1000L);
        StringConverter.INSTANCE.setInto(m, "2003-08-09T+06:00/2004-06-09T+07:00", BuddhistChronology.getInstance(EIGHT));
        assertEquals("2003-08-09T06:00:00.000000000+06:00/2004-06-09T07:00:00.000000000+07:00", m.toString());
    }

    public void testToString_1_oe() {
        Object a = StringConverter.INSTANCE.toString();
        assertEquals("java.lang.String", a.getClass().getName());
    }

}
