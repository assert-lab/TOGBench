/*
 *  Copyright 2001-2014 Stephen Colebourne
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
package org.joda.time.chrono;

import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;

/**
 * This class is a Junit unit test for JulianChronology.
 *
 * @author Stephen Colebourne
 */
public class TestJulianChronology_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");

    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    // 2002-06-09
    private long TEST_TIME_NOW =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;

    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestJulianChronology_OE25Dev.class);
    }

    public TestJulianChronology_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        originalDateTimeZone = DateTimeZone.getDefault();
        originalTimeZone = TimeZone.getDefault();
        originalLocale = Locale.getDefault();
        DateTimeZone.setDefault(LONDON);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        Locale.setDefault(Locale.UK);
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
    public void testFactoryUTC() {
        assertEquals(DateTimeZone.UTC,JulianChronology.getInstanceUTC().getZone());
        assertSame(JulianChronology.class,JulianChronology.getInstanceUTC().getClass());
    }

    public void testFactory() {
        assertEquals(LONDON,JulianChronology.getInstance().getZone());
        assertSame(JulianChronology.class,JulianChronology.getInstance().getClass());
    }

    public void testFactory_Zone() {
        assertEquals(TOKYO,JulianChronology.getInstance(TOKYO).getZone());
        assertEquals(PARIS,JulianChronology.getInstance(PARIS).getZone());
        assertEquals(LONDON,JulianChronology.getInstance(null).getZone());
        assertSame(JulianChronology.class,JulianChronology.getInstance(TOKYO).getClass());
    }

    public void testFactory_Zone_int() {
        JulianChronology chrono = JulianChronology.getInstance(TOKYO, 2);
        assertEquals(TOKYO,chrono.getZone());
        assertEquals(2,chrono.getMinimumDaysInFirstWeek());
        
        try {
            JulianChronology.getInstance(TOKYO, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            JulianChronology.getInstance(TOKYO, 8);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testEquality() {
        assertSame(JulianChronology.getInstance(TOKYO),JulianChronology.getInstance(TOKYO));
        assertSame(JulianChronology.getInstance(LONDON),JulianChronology.getInstance(LONDON));
        assertSame(JulianChronology.getInstance(PARIS),JulianChronology.getInstance(PARIS));
        assertSame(JulianChronology.getInstanceUTC(),JulianChronology.getInstanceUTC());
        assertSame(JulianChronology.getInstance(),JulianChronology.getInstance(LONDON));
    }

    public void testWithUTC() {
        assertSame(JulianChronology.getInstanceUTC(),JulianChronology.getInstance(LONDON).withUTC());
        assertSame(JulianChronology.getInstanceUTC(),JulianChronology.getInstance(TOKYO).withUTC());
        assertSame(JulianChronology.getInstanceUTC(),JulianChronology.getInstanceUTC().withUTC());
        assertSame(JulianChronology.getInstanceUTC(),JulianChronology.getInstance().withUTC());
    }

    public void testWithZone() {
        assertSame(JulianChronology.getInstance(TOKYO),JulianChronology.getInstance(TOKYO).withZone(TOKYO));
        assertSame(JulianChronology.getInstance(LONDON),JulianChronology.getInstance(TOKYO).withZone(LONDON));
        assertSame(JulianChronology.getInstance(PARIS),JulianChronology.getInstance(TOKYO).withZone(PARIS));
        assertSame(JulianChronology.getInstance(LONDON),JulianChronology.getInstance(TOKYO).withZone(null));
        assertSame(JulianChronology.getInstance(PARIS),JulianChronology.getInstance().withZone(PARIS));
        assertSame(JulianChronology.getInstance(PARIS),JulianChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString() {
        assertEquals("JulianChronology[Europe/London]",JulianChronology.getInstance(LONDON).toString());
        assertEquals("JulianChronology[Asia/Tokyo]",JulianChronology.getInstance(TOKYO).toString());
        assertEquals("JulianChronology[Europe/London]",JulianChronology.getInstance().toString());
        assertEquals("JulianChronology[UTC]",JulianChronology.getInstanceUTC().toString());
        assertEquals("JulianChronology[UTC,mdfw=2]",JulianChronology.getInstance(DateTimeZone.UTC,2).toString());
    }

    //-----------------------------------------------------------------------
    public void testDurationFields() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("eras",julian.eras().getName());
        assertEquals("centuries",julian.centuries().getName());
        assertEquals("years",julian.years().getName());
        assertEquals("weekyears",julian.weekyears().getName());
        assertEquals("months",julian.months().getName());
        assertEquals("weeks",julian.weeks().getName());
        assertEquals("days",julian.days().getName());
        assertEquals("halfdays",julian.halfdays().getName());
        assertEquals("hours",julian.hours().getName());
        assertEquals("minutes",julian.minutes().getName());
        assertEquals("seconds",julian.seconds().getName());
        assertEquals("millis",julian.millis().getName());
        
        assertEquals(false,julian.eras().isSupported());
        assertEquals(true,julian.centuries().isSupported());
        assertEquals(true,julian.years().isSupported());
        assertEquals(true,julian.weekyears().isSupported());
        assertEquals(true,julian.months().isSupported());
        assertEquals(true,julian.weeks().isSupported());
        assertEquals(true,julian.days().isSupported());
        assertEquals(true,julian.halfdays().isSupported());
        assertEquals(true,julian.hours().isSupported());
        assertEquals(true,julian.minutes().isSupported());
        assertEquals(true,julian.seconds().isSupported());
        assertEquals(true,julian.millis().isSupported());
        
        assertEquals(false,julian.centuries().isPrecise());
        assertEquals(false,julian.years().isPrecise());
        assertEquals(false,julian.weekyears().isPrecise());
        assertEquals(false,julian.months().isPrecise());
        assertEquals(false,julian.weeks().isPrecise());
        assertEquals(false,julian.days().isPrecise());
        assertEquals(false,julian.halfdays().isPrecise());
        assertEquals(true,julian.hours().isPrecise());
        assertEquals(true,julian.minutes().isPrecise());
        assertEquals(true,julian.seconds().isPrecise());
        assertEquals(true,julian.millis().isPrecise());
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        assertEquals(false,julianUTC.centuries().isPrecise());
        assertEquals(false,julianUTC.years().isPrecise());
        assertEquals(false,julianUTC.weekyears().isPrecise());
        assertEquals(false,julianUTC.months().isPrecise());
        assertEquals(true,julianUTC.weeks().isPrecise());
        assertEquals(true,julianUTC.days().isPrecise());
        assertEquals(true,julianUTC.halfdays().isPrecise());
        assertEquals(true,julianUTC.hours().isPrecise());
        assertEquals(true,julianUTC.minutes().isPrecise());
        assertEquals(true,julianUTC.seconds().isPrecise());
        assertEquals(true,julianUTC.millis().isPrecise());
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
        assertEquals(false,julianGMT.centuries().isPrecise());
        assertEquals(false,julianGMT.years().isPrecise());
        assertEquals(false,julianGMT.weekyears().isPrecise());
        assertEquals(false,julianGMT.months().isPrecise());
        assertEquals(true,julianGMT.weeks().isPrecise());
        assertEquals(true,julianGMT.days().isPrecise());
        assertEquals(true,julianGMT.halfdays().isPrecise());
        assertEquals(true,julianGMT.hours().isPrecise());
        assertEquals(true,julianGMT.minutes().isPrecise());
        assertEquals(true,julianGMT.seconds().isPrecise());
        assertEquals(true,julianGMT.millis().isPrecise());
    }

    public void testDateFields() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("era",julian.era().getName());
        assertEquals("centuryOfEra",julian.centuryOfEra().getName());
        assertEquals("yearOfCentury",julian.yearOfCentury().getName());
        assertEquals("yearOfEra",julian.yearOfEra().getName());
        assertEquals("year",julian.year().getName());
        assertEquals("monthOfYear",julian.monthOfYear().getName());
        assertEquals("weekyearOfCentury",julian.weekyearOfCentury().getName());
        assertEquals("weekyear",julian.weekyear().getName());
        assertEquals("weekOfWeekyear",julian.weekOfWeekyear().getName());
        assertEquals("dayOfYear",julian.dayOfYear().getName());
        assertEquals("dayOfMonth",julian.dayOfMonth().getName());
        assertEquals("dayOfWeek",julian.dayOfWeek().getName());
        
        assertEquals(true,julian.era().isSupported());
        assertEquals(true,julian.centuryOfEra().isSupported());
        assertEquals(true,julian.yearOfCentury().isSupported());
        assertEquals(true,julian.yearOfEra().isSupported());
        assertEquals(true,julian.year().isSupported());
        assertEquals(true,julian.monthOfYear().isSupported());
        assertEquals(true,julian.weekyearOfCentury().isSupported());
        assertEquals(true,julian.weekyear().isSupported());
        assertEquals(true,julian.weekOfWeekyear().isSupported());
        assertEquals(true,julian.dayOfYear().isSupported());
        assertEquals(true,julian.dayOfMonth().isSupported());
        assertEquals(true,julian.dayOfWeek().isSupported());
        
        assertEquals(julian.eras(),julian.era().getDurationField());
        assertEquals(julian.centuries(),julian.centuryOfEra().getDurationField());
        assertEquals(julian.years(),julian.yearOfCentury().getDurationField());
        assertEquals(julian.years(),julian.yearOfEra().getDurationField());
        assertEquals(julian.years(),julian.year().getDurationField());
        assertEquals(julian.months(),julian.monthOfYear().getDurationField());
        assertEquals(julian.weekyears(),julian.weekyearOfCentury().getDurationField());
        assertEquals(julian.weekyears(),julian.weekyear().getDurationField());
        assertEquals(julian.weeks(),julian.weekOfWeekyear().getDurationField());
        assertEquals(julian.days(),julian.dayOfYear().getDurationField());
        assertEquals(julian.days(),julian.dayOfMonth().getDurationField());
        assertEquals(julian.days(),julian.dayOfWeek().getDurationField());
        
        assertEquals(null,julian.era().getRangeDurationField());
        assertEquals(julian.eras(),julian.centuryOfEra().getRangeDurationField());
        assertEquals(julian.centuries(),julian.yearOfCentury().getRangeDurationField());
        assertEquals(julian.eras(),julian.yearOfEra().getRangeDurationField());
        assertEquals(null,julian.year().getRangeDurationField());
        assertEquals(julian.years(),julian.monthOfYear().getRangeDurationField());
        assertEquals(julian.centuries(),julian.weekyearOfCentury().getRangeDurationField());
        assertEquals(null,julian.weekyear().getRangeDurationField());
        assertEquals(julian.weekyears(),julian.weekOfWeekyear().getRangeDurationField());
        assertEquals(julian.years(),julian.dayOfYear().getRangeDurationField());
        assertEquals(julian.months(),julian.dayOfMonth().getRangeDurationField());
        assertEquals(julian.weeks(),julian.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("halfdayOfDay",julian.halfdayOfDay().getName());
        assertEquals("clockhourOfHalfday",julian.clockhourOfHalfday().getName());
        assertEquals("hourOfHalfday",julian.hourOfHalfday().getName());
        assertEquals("clockhourOfDay",julian.clockhourOfDay().getName());
        assertEquals("hourOfDay",julian.hourOfDay().getName());
        assertEquals("minuteOfDay",julian.minuteOfDay().getName());
        assertEquals("minuteOfHour",julian.minuteOfHour().getName());
        assertEquals("secondOfDay",julian.secondOfDay().getName());
        assertEquals("secondOfMinute",julian.secondOfMinute().getName());
        assertEquals("millisOfDay",julian.millisOfDay().getName());
        assertEquals("millisOfSecond",julian.millisOfSecond().getName());
        
        assertEquals(true,julian.halfdayOfDay().isSupported());
        assertEquals(true,julian.clockhourOfHalfday().isSupported());
        assertEquals(true,julian.hourOfHalfday().isSupported());
        assertEquals(true,julian.clockhourOfDay().isSupported());
        assertEquals(true,julian.hourOfDay().isSupported());
        assertEquals(true,julian.minuteOfDay().isSupported());
        assertEquals(true,julian.minuteOfHour().isSupported());
        assertEquals(true,julian.secondOfDay().isSupported());
        assertEquals(true,julian.secondOfMinute().isSupported());
        assertEquals(true,julian.millisOfDay().isSupported());
        assertEquals(true,julian.millisOfSecond().isSupported());
    }

    public void testLeap_28feb() {
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(false,dt.dayOfMonth().isLeap());
        assertEquals(false,dt.dayOfYear().isLeap());
    }

    public void testLeap_29feb() {
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(true,dt.dayOfMonth().isLeap());
        assertEquals(true,dt.dayOfYear().isLeap());
    }

    public void testFactoryUTC_1_oe() {
        Object a = DateTimeZone.UTC;
// incorrect assertion         assertNotNull(DateTimeZone.getInstanceUTC());
    }

    public void testFactoryUTC_2_oe() {
        Object a = JulianChronology.class;
        assertNotNull(JulianChronology.getInstanceUTC());
    }

    public void testFactory_1_oe() {
        Object a = LONDON;
        assertNotNull(JulianChronology.getInstance());
    }

    public void testFactory_2_oe() {
        Object a = JulianChronology.class;
        assertNotNull(JulianChronology.getInstance());
    }

    public void testFactory_Zone_1_oe() {
        Object a = TOKYO;
        assertNotNull(JulianChronology.getInstance());
    }

    public void testFactory_Zone_2_oe() {
        Object a = PARIS;
        assertNotNull(JulianChronology.getInstance());
    }

    public void testFactory_Zone_4_oe() {
        Object a = JulianChronology.class;
        assertNotNull(JulianChronology.getInstance());
    }

    public void testFactory_Zone_int_1_oe() {
        JulianChronology chrono = JulianChronology.getInstance(TOKYO, 2);
        assertEquals("Asia/Tokyo", chrono.getZone().getID());
    }

    public void testFactory_Zone_int_2_oe() {
        JulianChronology chrono = JulianChronology.getInstance(TOKYO, 2);
        assertEquals(4, chrono.getMinimumDaysInFirstWeek());
    }

    public void testEquality_1_oe() {
        Object a = JulianChronology.getInstance(TOKYO);
        assertNotNull(JulianChronology.getInstance());
    }

    public void testEquality_2_oe() {
        Object a = JulianChronology.getInstance(LONDON);
        assertNotNull(JulianChronology.getInstance());
    }

    public void testEquality_3_oe() {
        Object a = JulianChronology.getInstance(PARIS);
        assertNotNull(JulianChronology.getInstance());
    }

    public void testEquality_4_oe() {
        Object a = JulianChronology.getInstanceUTC();
        assertNotNull(JulianChronology.getInstanceUTC());
    }

    public void testEquality_5_oe() {
        Object a = JulianChronology.getInstance();
        assertNotNull(JulianChronology.getInstance());
    }

    public void testWithUTC_1_oe() {
        Object a = JulianChronology.getInstanceUTC();
        assertNotNull(JulianChronology.getInstance());
    }

    public void testWithUTC_2_oe() {
        Object a = JulianChronology.getInstanceUTC();
        assertNotNull(JulianChronology.getInstance());
    }

    public void testWithUTC_3_oe() {
        Object a = JulianChronology.getInstanceUTC();
        assertNotNull(JulianChronology.getInstanceUTC());
    }

    public void testWithUTC_4_oe() {
        Object a = JulianChronology.getInstanceUTC();
        assertNotNull(JulianChronology.getInstance());
    }

    public void testWithZone_1_oe() {
        Object a = JulianChronology.getInstance(TOKYO);
        assertNotNull(JulianChronology.getInstance());
    }

    public void testWithZone_2_oe() {
        Object a = JulianChronology.getInstance(LONDON);
        assertNotNull(JulianChronology.getInstance());
    }

    public void testWithZone_3_oe() {
        Object a = JulianChronology.getInstance(PARIS);
        assertNotNull(JulianChronology.getInstance());
    }

    public void testWithZone_4_oe() {
        Object a = JulianChronology.getInstance(LONDON);
        assertNotNull(JulianChronology.getInstance());
    }

    public void testWithZone_5_oe() {
        Object a = JulianChronology.getInstance(PARIS);
        assertNotNull(JulianChronology.getInstance());
    }

    public void testWithZone_6_oe() {
        Object a = JulianChronology.getInstance(PARIS);
        assertNotNull(JulianChronology.getInstanceUTC());
    }

    public void testToString_1_oe() {
        Object a = JulianChronology.getInstance(LONDON).toString();
        assertNotNull(a);
    }

    public void testToString_2_oe() {
        Object a = JulianChronology.getInstance(TOKYO).toString();
        assertNotNull(a);
    }

    public void testToString_3_oe() {
        Object a = JulianChronology.getInstance().toString();
        assertNotNull(JulianChronology.getInstance());
    }

    public void testToString_4_oe() {
        Object a = JulianChronology.getInstanceUTC().toString();
        assertNotNull(a);
    }

    public void testToString_5_oe() {
        Object a = JulianChronology.getInstance(DateTimeZone.UTC,2).toString();
        assertEquals("JulianChronology[UTC]", a.toString());
    }

    public void testDurationFields_1_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("eras", julian.eras().getName());
    }

    public void testDurationFields_2_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertEquals("centuries", JulianChronology.centuries().getName());
    }

    public void testDurationFields_3_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("Years", julian.years().getName());
    }

    public void testDurationFields_4_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("Weekyears", julian.weekyears().getName());
    }

    public void testDurationFields_5_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertNotNull(Months.months(0));
    }

    public void testDurationFields_6_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertEquals("weeks", DurationFieldType.weeks().getName());
    }

    public void testDurationFields_7_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("Days", julian.days().getName());
    }

    public void testDurationFields_8_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertEquals("Halfdays", JulianChronology.halfdays().getName());
    }

    public void testDurationFields_9_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("Hours", julian.hours().getName());
    }

    public void testDurationFields_10_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("Minutes", julian.minutes().getName());
    }

    public void testDurationFields_11_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertEquals("seconds", DurationFieldType.seconds().getName());
    }

    public void testDurationFields_12_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertEquals("millis", DurationFieldType.millis().getName());
    }

    public void testDurationFields_13_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("eras", julian.eras().getName());
    }

    public void testDurationFields_14_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
// incorrect assertion         assertEquals("centuries", JulianChronology.centuries().getName());
    }

    public void testDurationFields_15_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("Years", julian.years().getName());
    }

    public void testDurationFields_16_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("Weekyears", julian.weekyears().getName());
    }

    public void testDurationFields_17_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
// incorrect assertion         assertNotNull(Months.months(0));
    }

    public void testDurationFields_18_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("weeks", julian.weeks().getName());
    }

    public void testDurationFields_19_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("Days", julian.days().getName());
    }

    public void testDurationFields_20_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
// incorrect assertion         assertEquals("Halfdays", JulianChronology.halfdays().getName());
    }

    public void testDurationFields_21_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("Hours", julian.hours().getName());
    }

    public void testDurationFields_22_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("Minutes", julian.minutes().getName());
    }

    public void testDurationFields_23_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
// incorrect assertion         assertEquals("seconds", DurationFieldType.seconds().getName());
    }

    public void testDurationFields_24_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
// incorrect assertion         assertEquals("millis", DurationFieldType.millis().getName());
    }

    public void testDurationFields_25_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
// incorrect assertion         assertEquals("centuries", JulianChronology.centuries().getName());
    }

    public void testDurationFields_26_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Years", julian.years().getName());
    }

    public void testDurationFields_27_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Weekyears", julian.weekyears().getName());
    }

    public void testDurationFields_28_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
// incorrect assertion         assertNotNull(Months.months(0));
    }

    public void testDurationFields_29_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("weeks", julian.weeks().getName());
    }

    public void testDurationFields_30_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Days", julian.days().getName());
    }

    public void testDurationFields_31_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Halfdays", julian.halfdays().getName());
    }

    public void testDurationFields_32_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Hours", julian.hours().getName());
    }

    public void testDurationFields_33_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Minutes", julian.minutes().getName());
    }

    public void testDurationFields_34_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
// incorrect assertion         assertEquals("seconds", DurationFieldType.seconds().getName());
    }

    public void testDurationFields_35_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
// incorrect assertion         assertEquals("millis", DurationFieldType.millis().getName());
    }

    public void testDurationFields_36_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
// incorrect assertion         assertEquals("centuries", JulianChronology.centuries().getName());
    }

    public void testDurationFields_37_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        assertEquals("YEARS", julian.toString());
    }

    public void testDurationFields_38_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        assertEquals("Weekyears", julian.weekyears().getName());
    }

    public void testDurationFields_39_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        assertNotNull(julianUTC);
    }

    public void testDurationFields_40_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        assertEquals("weeks", julian.weeks().getName());
    }

    public void testDurationFields_41_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        assertEquals("Days", julian.days().getName());
    }

    public void testDurationFields_42_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
// incorrect assertion         assertEquals("Halfdays", JulianChronology.halfdays().getName());
    }

    public void testDurationFields_43_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        assertEquals("Hours", julian.hours().getName());
    }

    public void testDurationFields_44_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        assertEquals("Minutes", julian.minutes().getName());
    }

    public void testDurationFields_45_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
// incorrect assertion         assertEquals("seconds", DurationFieldType.seconds().getName());
    }

    public void testDurationFields_46_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
// incorrect assertion         assertEquals("millis", DurationFieldType.millis().getName());
    }

    public void testDurationFields_47_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
        assertEquals("centuries", julian.centuries().getName());
    }

    public void testDurationFields_48_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
        assertEquals("YEARS", julian.toString());
    }

    public void testDurationFields_49_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
        assertEquals("weekyears", julian.weekyears().getName());
    }

    public void testDurationFields_50_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
        assertNotNull(julianGMT);
    }

    public void testDurationFields_51_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
        assertEquals("weeks", julian.weeks().getName());
    }

    public void testDurationFields_52_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
        assertEquals("Days", julian.days().getName());
    }

    public void testDurationFields_53_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
// incorrect assertion         assertEquals("Halfdays", JulianChronology.halfdays().getName());
    }

    public void testDurationFields_54_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
        assertEquals("Hours", julian.hours().getName());
    }

    public void testDurationFields_55_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
        assertEquals("Minutes", julian.minutes().getName());
    }

    public void testDurationFields_56_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
        assertEquals("seconds", julian.seconds().getName());
    }

    public void testDurationFields_57_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        final JulianChronology julianUTC = JulianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final JulianChronology julianGMT = JulianChronology.getInstance(gmt);
// incorrect assertion         assertEquals("millis", DurationFieldType.millis().getName());
    }

    public void testDateFields_1_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("era", julian.era().getName());
    }

    public void testDateFields_2_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("centuryOfEra", julian.centuryOfEra().getName());
    }

    public void testDateFields_3_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("yearOfCentury", julian.yearOfCentury().getName());
    }

    public void testDateFields_4_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("yearOfEra", julian.yearOfEra().getName());
    }

    public void testDateFields_5_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("year", julian.year().getName());
    }

    public void testDateFields_6_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("MonthOfYear", julian.monthOfYear().getName());
    }

    public void testDateFields_7_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertEquals("weekyearOfCentury", JulianChronology.weekyearOfCentury().getName());
    }

    public void testDateFields_8_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertNotNull(julian.weekyear());
    }

    public void testDateFields_9_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertNotNull(julian.weekOfWeekyear());
    }

    public void testDateFields_10_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("DayOfYear", julian.dayOfYear().getName());
    }

    public void testDateFields_11_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertNotNull(julian.dayOfMonth());
    }

    public void testDateFields_12_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("Day of Week", julian.dayOfWeek().getName());
    }

    public void testDateFields_13_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("era", julian.era().getName());
    }

    public void testDateFields_14_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("centuryOfEra", julian.centuryOfEra().getName());
    }

    public void testDateFields_15_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("yearOfCentury", julian.yearOfCentury().getName());
    }

    public void testDateFields_16_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("yearOfEra", julian.yearOfEra().getName());
    }

    public void testDateFields_17_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("year", julian.year().getName());
    }

    public void testDateFields_18_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("MonthOfYear", julian.monthOfYear().getName());
    }

    public void testDateFields_19_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
// incorrect assertion         assertEquals("weekyearOfCentury", JulianChronology.weekyearOfCentury().getName());
    }

    public void testDateFields_20_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertNotNull(julian.weekyear());
    }

    public void testDateFields_21_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertNotNull(julian.weekOfWeekyear());
    }

    public void testDateFields_22_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("DayOfYear", julian.dayOfYear().getName());
    }

    public void testDateFields_23_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("Day of Month", julian.dayOfMonth().getName());
    }

    public void testDateFields_24_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("Day of Week", julian.dayOfWeek().getName());
    }

    public void testDateFields_25_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("eras", julian.eras().getName());
    }

    public void testDateFields_26_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
// incorrect assertion         assertEquals("centuries", JulianChronology.centuries().getName());
    }

    public void testDateFields_27_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Years", julian.years().getName());
    }

    public void testDateFields_28_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Years", julian.years().getName());
    }

    public void testDateFields_29_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Years", julian.years().getName());
    }

    public void testDateFields_30_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
// incorrect assertion         assertNotNull(Months.months(0));
    }

    public void testDateFields_31_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Weekyears", julian.weekyears().getName());
    }

    public void testDateFields_32_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Weekyears", julian.weekyears().getName());
    }

    public void testDateFields_33_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("weeks", julian.weeks().getName());
    }

    public void testDateFields_34_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Days", julian.days().getName());
    }

    public void testDateFields_35_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Days", julian.days().getName());
    }

    public void testDateFields_36_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        assertEquals("Days", julian.days().getName());
    }

    public void testDateFields_37_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        assertEquals("era", julian.era().getName());
    }

    public void testDateFields_38_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        assertEquals("eras", julian.eras().getName());
    }

    public void testDateFields_39_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
// incorrect assertion         assertEquals("centuries", JulianChronology.centuries().getName());
    }

    public void testDateFields_40_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        assertEquals("eras", julian.eras().getName());
    }

    public void testDateFields_41_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        assertEquals("year", julian.year().getName());
    }

    public void testDateFields_42_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        assertEquals("Years", julian.years().getName());
    }

    public void testDateFields_43_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
// incorrect assertion         assertEquals("centuries", JulianChronology.centuries().getName());
    }

    public void testDateFields_44_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        assertNotNull(julian.weekyear());
    }

    public void testDateFields_45_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        assertEquals("Weekyears", julian.weekyears().getName());
    }

    public void testDateFields_46_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        assertEquals("Years", julian.years().getName());
    }

    public void testDateFields_47_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
// incorrect assertion         assertNotNull(Months.months(0));
    }

    public void testDateFields_48_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        
        
        assertEquals("Weeks", julian.weeks().getName());
    }

    public void testTimeFields_1_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertEquals("halfdayOfDay", JulianChronology.halfdayOfDay().getName());
    }

    public void testTimeFields_2_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertEquals("clockhour_of_halfday", JulianChronology.clockhourOfHalfday().getName());
    }

    public void testTimeFields_3_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertEquals("hourOfHalfday", JulianChronology.hourOfHalfday().getName());
    }

    public void testTimeFields_4_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("clockhourOfDay", julian.clockhourOfDay().getName());
    }

    public void testTimeFields_5_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("HOUR_OF_DAY", julian.hourOfDay().getName());
    }

    public void testTimeFields_6_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("MinuteOfDay", julian.minuteOfDay().getName());
    }

    public void testTimeFields_7_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("MinuteOfHour", julian.minuteOfHour().getName());
    }

    public void testTimeFields_8_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
// incorrect assertion         assertEquals("secondOfDay", JulianChronology.secondOfDay().getName());
    }

    public void testTimeFields_9_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertNotNull(julian.secondOfMinute());
    }

    public void testTimeFields_10_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("millis of day", julian.millisOfDay().getName());
    }

    public void testTimeFields_11_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        assertEquals("Millis of Second", julian.millisOfSecond().getName());
    }

    public void testTimeFields_12_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
// incorrect assertion         assertEquals("halfdayOfDay", JulianChronology.halfdayOfDay().getName());
    }

    public void testTimeFields_13_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
// incorrect assertion         assertEquals("clockhour_of_halfday", JulianChronology.clockhourOfHalfday().getName());
    }

    public void testTimeFields_14_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
// incorrect assertion         assertEquals("hourOfHalfday", JulianChronology.hourOfHalfday().getName());
    }

    public void testTimeFields_15_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("clockhourOfDay", julian.clockhourOfDay().getName());
    }

    public void testTimeFields_16_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("Property[name=hourOfDay,type=int]", julian.hourOfDay().toString());
    }

    public void testTimeFields_17_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("MinuteOfDay", julian.minuteOfDay().getName());
    }

    public void testTimeFields_18_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("MinuteOfHour", julian.minuteOfHour().getName());
    }

    public void testTimeFields_19_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
// incorrect assertion         assertEquals("secondOfDay", JulianChronology.secondOfDay().getName());
    }

    public void testTimeFields_20_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertNotNull(julian.secondOfMinute());
    }

    public void testTimeFields_21_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("millis of day", julian.millisOfDay().getName());
    }

    public void testTimeFields_22_oe() {
        final JulianChronology julian = JulianChronology.getInstance();
        
        assertEquals("Millis of Second", julian.millisOfSecond().getName());
    }

    public void testLeap_28feb_1_oe() {
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(2012, dt.year().get());
    }

    public void testLeap_28feb_2_oe() {
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals("MonthOfYear", dt.monthOfYear().getName());
    }

    public void testLeap_28feb_3_oe() {
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(28, dt.dayOfMonth().get());
    }

    public void testLeap_28feb_4_oe() {
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(28, dt.dayOfYear().get());
    }

    public void testLeap_29feb_1_oe() {
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(2012, dt.year().get());
    }

    public void testLeap_29feb_2_oe() {
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals("MonthOfYear", dt.monthOfYear().getName());
    }

    public void testLeap_29feb_3_oe() {
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(29, dt.dayOfMonth().get());
    }

    public void testLeap_29feb_4_oe() {
        Chronology chrono = JulianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(29, dt.dayOfYear().get());
    }

}
