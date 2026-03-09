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
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.YearMonthDay;

/**
 * This class is a Junit unit test for GregorianChronology.
 *
 * @author Stephen Colebourne
 */
@SuppressWarnings("deprecation")
public class TestGregorianChronology_OE25Dev extends TestCase {

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
        return new TestSuite(TestGregorianChronology_OE25Dev.class);
    }

    public TestGregorianChronology_OE25Dev(String name) {
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
        assertEquals(DateTimeZone.UTC,GregorianChronology.getInstanceUTC().getZone());
        assertSame(GregorianChronology.class,GregorianChronology.getInstanceUTC().getClass());
    }

    public void testFactory() {
        assertEquals(LONDON,GregorianChronology.getInstance().getZone());
        assertSame(GregorianChronology.class,GregorianChronology.getInstance().getClass());
    }

    public void testFactory_Zone() {
        assertEquals(TOKYO,GregorianChronology.getInstance(TOKYO).getZone());
        assertEquals(PARIS,GregorianChronology.getInstance(PARIS).getZone());
        assertEquals(LONDON,GregorianChronology.getInstance(null).getZone());
        assertSame(GregorianChronology.class,GregorianChronology.getInstance(TOKYO).getClass());
    }

    public void testFactory_Zone_int() {
        GregorianChronology chrono = GregorianChronology.getInstance(TOKYO, 2);
        assertEquals(TOKYO,chrono.getZone());
        assertEquals(2,chrono.getMinimumDaysInFirstWeek());
        
        try {
            GregorianChronology.getInstance(TOKYO, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            GregorianChronology.getInstance(TOKYO, 8);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testEquality() {
        assertSame(GregorianChronology.getInstance(TOKYO),GregorianChronology.getInstance(TOKYO));
        assertSame(GregorianChronology.getInstance(LONDON),GregorianChronology.getInstance(LONDON));
        assertSame(GregorianChronology.getInstance(PARIS),GregorianChronology.getInstance(PARIS));
        assertSame(GregorianChronology.getInstanceUTC(),GregorianChronology.getInstanceUTC());
        assertSame(GregorianChronology.getInstance(),GregorianChronology.getInstance(LONDON));
    }

    public void testWithUTC() {
        assertSame(GregorianChronology.getInstanceUTC(),GregorianChronology.getInstance(LONDON).withUTC());
        assertSame(GregorianChronology.getInstanceUTC(),GregorianChronology.getInstance(TOKYO).withUTC());
        assertSame(GregorianChronology.getInstanceUTC(),GregorianChronology.getInstanceUTC().withUTC());
        assertSame(GregorianChronology.getInstanceUTC(),GregorianChronology.getInstance().withUTC());
    }

    public void testWithZone() {
        assertSame(GregorianChronology.getInstance(TOKYO),GregorianChronology.getInstance(TOKYO).withZone(TOKYO));
        assertSame(GregorianChronology.getInstance(LONDON),GregorianChronology.getInstance(TOKYO).withZone(LONDON));
        assertSame(GregorianChronology.getInstance(PARIS),GregorianChronology.getInstance(TOKYO).withZone(PARIS));
        assertSame(GregorianChronology.getInstance(LONDON),GregorianChronology.getInstance(TOKYO).withZone(null));
        assertSame(GregorianChronology.getInstance(PARIS),GregorianChronology.getInstance().withZone(PARIS));
        assertSame(GregorianChronology.getInstance(PARIS),GregorianChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString() {
        assertEquals("GregorianChronology[Europe/London]",GregorianChronology.getInstance(LONDON).toString());
        assertEquals("GregorianChronology[Asia/Tokyo]",GregorianChronology.getInstance(TOKYO).toString());
        assertEquals("GregorianChronology[Europe/London]",GregorianChronology.getInstance().toString());
        assertEquals("GregorianChronology[UTC]",GregorianChronology.getInstanceUTC().toString());
        assertEquals("GregorianChronology[UTC,mdfw=2]",GregorianChronology.getInstance(DateTimeZone.UTC,2).toString());
    }

    //-----------------------------------------------------------------------
    public void testDurationFields() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("eras",greg.eras().getName());
        assertEquals("centuries",greg.centuries().getName());
        assertEquals("years",greg.years().getName());
        assertEquals("weekyears",greg.weekyears().getName());
        assertEquals("months",greg.months().getName());
        assertEquals("weeks",greg.weeks().getName());
        assertEquals("days",greg.days().getName());
        assertEquals("halfdays",greg.halfdays().getName());
        assertEquals("hours",greg.hours().getName());
        assertEquals("minutes",greg.minutes().getName());
        assertEquals("seconds",greg.seconds().getName());
        assertEquals("millis",greg.millis().getName());
        
        assertEquals(false,greg.eras().isSupported());
        assertEquals(true,greg.centuries().isSupported());
        assertEquals(true,greg.years().isSupported());
        assertEquals(true,greg.weekyears().isSupported());
        assertEquals(true,greg.months().isSupported());
        assertEquals(true,greg.weeks().isSupported());
        assertEquals(true,greg.days().isSupported());
        assertEquals(true,greg.halfdays().isSupported());
        assertEquals(true,greg.hours().isSupported());
        assertEquals(true,greg.minutes().isSupported());
        assertEquals(true,greg.seconds().isSupported());
        assertEquals(true,greg.millis().isSupported());
        
        assertEquals(false,greg.centuries().isPrecise());
        assertEquals(false,greg.years().isPrecise());
        assertEquals(false,greg.weekyears().isPrecise());
        assertEquals(false,greg.months().isPrecise());
        assertEquals(false,greg.weeks().isPrecise());
        assertEquals(false,greg.days().isPrecise());
        assertEquals(false,greg.halfdays().isPrecise());
        assertEquals(true,greg.hours().isPrecise());
        assertEquals(true,greg.minutes().isPrecise());
        assertEquals(true,greg.seconds().isPrecise());
        assertEquals(true,greg.millis().isPrecise());
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(false,gregUTC.centuries().isPrecise());
        assertEquals(false,gregUTC.years().isPrecise());
        assertEquals(false,gregUTC.weekyears().isPrecise());
        assertEquals(false,gregUTC.months().isPrecise());
        assertEquals(true,gregUTC.weeks().isPrecise());
        assertEquals(true,gregUTC.days().isPrecise());
        assertEquals(true,gregUTC.halfdays().isPrecise());
        assertEquals(true,gregUTC.hours().isPrecise());
        assertEquals(true,gregUTC.minutes().isPrecise());
        assertEquals(true,gregUTC.seconds().isPrecise());
        assertEquals(true,gregUTC.millis().isPrecise());
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(false,gregGMT.centuries().isPrecise());
        assertEquals(false,gregGMT.years().isPrecise());
        assertEquals(false,gregGMT.weekyears().isPrecise());
        assertEquals(false,gregGMT.months().isPrecise());
        assertEquals(true,gregGMT.weeks().isPrecise());
        assertEquals(true,gregGMT.days().isPrecise());
        assertEquals(true,gregGMT.halfdays().isPrecise());
        assertEquals(true,gregGMT.hours().isPrecise());
        assertEquals(true,gregGMT.minutes().isPrecise());
        assertEquals(true,gregGMT.seconds().isPrecise());
        assertEquals(true,gregGMT.millis().isPrecise());
    }

    public void testDateFields() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("era",greg.era().getName());
        assertEquals("centuryOfEra",greg.centuryOfEra().getName());
        assertEquals("yearOfCentury",greg.yearOfCentury().getName());
        assertEquals("yearOfEra",greg.yearOfEra().getName());
        assertEquals("year",greg.year().getName());
        assertEquals("monthOfYear",greg.monthOfYear().getName());
        assertEquals("weekyearOfCentury",greg.weekyearOfCentury().getName());
        assertEquals("weekyear",greg.weekyear().getName());
        assertEquals("weekOfWeekyear",greg.weekOfWeekyear().getName());
        assertEquals("dayOfYear",greg.dayOfYear().getName());
        assertEquals("dayOfMonth",greg.dayOfMonth().getName());
        assertEquals("dayOfWeek",greg.dayOfWeek().getName());
        
        assertEquals(true,greg.era().isSupported());
        assertEquals(true,greg.centuryOfEra().isSupported());
        assertEquals(true,greg.yearOfCentury().isSupported());
        assertEquals(true,greg.yearOfEra().isSupported());
        assertEquals(true,greg.year().isSupported());
        assertEquals(true,greg.monthOfYear().isSupported());
        assertEquals(true,greg.weekyearOfCentury().isSupported());
        assertEquals(true,greg.weekyear().isSupported());
        assertEquals(true,greg.weekOfWeekyear().isSupported());
        assertEquals(true,greg.dayOfYear().isSupported());
        assertEquals(true,greg.dayOfMonth().isSupported());
        assertEquals(true,greg.dayOfWeek().isSupported());
        
        assertEquals(greg.eras(),greg.era().getDurationField());
        assertEquals(greg.centuries(),greg.centuryOfEra().getDurationField());
        assertEquals(greg.years(),greg.yearOfCentury().getDurationField());
        assertEquals(greg.years(),greg.yearOfEra().getDurationField());
        assertEquals(greg.years(),greg.year().getDurationField());
        assertEquals(greg.months(),greg.monthOfYear().getDurationField());
        assertEquals(greg.weekyears(),greg.weekyearOfCentury().getDurationField());
        assertEquals(greg.weekyears(),greg.weekyear().getDurationField());
        assertEquals(greg.weeks(),greg.weekOfWeekyear().getDurationField());
        assertEquals(greg.days(),greg.dayOfYear().getDurationField());
        assertEquals(greg.days(),greg.dayOfMonth().getDurationField());
        assertEquals(greg.days(),greg.dayOfWeek().getDurationField());
        
        assertEquals(null,greg.era().getRangeDurationField());
        assertEquals(greg.eras(),greg.centuryOfEra().getRangeDurationField());
        assertEquals(greg.centuries(),greg.yearOfCentury().getRangeDurationField());
        assertEquals(greg.eras(),greg.yearOfEra().getRangeDurationField());
        assertEquals(null,greg.year().getRangeDurationField());
        assertEquals(greg.years(),greg.monthOfYear().getRangeDurationField());
        assertEquals(greg.centuries(),greg.weekyearOfCentury().getRangeDurationField());
        assertEquals(null,greg.weekyear().getRangeDurationField());
        assertEquals(greg.weekyears(),greg.weekOfWeekyear().getRangeDurationField());
        assertEquals(greg.years(),greg.dayOfYear().getRangeDurationField());
        assertEquals(greg.months(),greg.dayOfMonth().getRangeDurationField());
        assertEquals(greg.weeks(),greg.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("halfdayOfDay",greg.halfdayOfDay().getName());
        assertEquals("clockhourOfHalfday",greg.clockhourOfHalfday().getName());
        assertEquals("hourOfHalfday",greg.hourOfHalfday().getName());
        assertEquals("clockhourOfDay",greg.clockhourOfDay().getName());
        assertEquals("hourOfDay",greg.hourOfDay().getName());
        assertEquals("minuteOfDay",greg.minuteOfDay().getName());
        assertEquals("minuteOfHour",greg.minuteOfHour().getName());
        assertEquals("secondOfDay",greg.secondOfDay().getName());
        assertEquals("secondOfMinute",greg.secondOfMinute().getName());
        assertEquals("millisOfDay",greg.millisOfDay().getName());
        assertEquals("millisOfSecond",greg.millisOfSecond().getName());
        
        assertEquals(true,greg.halfdayOfDay().isSupported());
        assertEquals(true,greg.clockhourOfHalfday().isSupported());
        assertEquals(true,greg.hourOfHalfday().isSupported());
        assertEquals(true,greg.clockhourOfDay().isSupported());
        assertEquals(true,greg.hourOfDay().isSupported());
        assertEquals(true,greg.minuteOfDay().isSupported());
        assertEquals(true,greg.minuteOfHour().isSupported());
        assertEquals(true,greg.secondOfDay().isSupported());
        assertEquals(true,greg.secondOfMinute().isSupported());
        assertEquals(true,greg.millisOfDay().isSupported());
        assertEquals(true,greg.millisOfSecond().isSupported());
    }

    public void testMaximumValue() {
        YearMonthDay ymd1 = new YearMonthDay(1999, DateTimeConstants.FEBRUARY, 1);
        DateMidnight dm1 = new DateMidnight(1999, DateTimeConstants.FEBRUARY, 1);
        Chronology chrono = GregorianChronology.getInstance();
        assertEquals(28,chrono.dayOfMonth().getMaximumValue(ymd1));
        assertEquals(28,chrono.dayOfMonth().getMaximumValue(dm1.getMillis()));
    }

    public void testLeap_28feb() {
        Chronology chrono = GregorianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(false,dt.dayOfMonth().isLeap());
        assertEquals(false,dt.dayOfYear().isLeap());
    }

    public void testLeap_29feb() {
        Chronology chrono = GregorianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(true,dt.dayOfMonth().isLeap());
        assertEquals(true,dt.dayOfYear().isLeap());
    }

    public void testFactoryUTC_1_oe() {
        Object a = DateTimeZone.UTC;
        assertEquals(a, GregorianChronology.getInstanceUTC().getZone());
    }

    public void testFactoryUTC_2_oe() {
        Object a = GregorianChronology.class;
        assertSame(a, GregorianChronology.getInstanceUTC().getClass());
    }

    public void testFactory_1_oe() {
        Object a = LONDON;
        assertEquals(a, GregorianChronology.getInstance().getZone());
    }

    public void testFactory_2_oe() {
        Object a = GregorianChronology.class;
        assertSame(a, GregorianChronology.getInstance().getClass());
    }

    public void testFactory_Zone_1_oe() {
        Object a = TOKYO;
        assertEquals(a, GregorianChronology.getInstance(TOKYO).getZone());
    }

    public void testFactory_Zone_2_oe() {
        Object a = PARIS;
        assertEquals(a, GregorianChronology.getInstance(PARIS).getZone());
    }

    public void testFactory_Zone_3_oe() {
        Object a = LONDON;
        assertEquals(a, GregorianChronology.getInstance(null).getZone());
    }

    public void testFactory_Zone_4_oe() {
        Object a = GregorianChronology.class;
        assertSame(a, GregorianChronology.getInstance(TOKYO).getClass());
    }

    public void testFactory_Zone_int_1_oe() {
        GregorianChronology chrono = GregorianChronology.getInstance(TOKYO, 2);
        assertEquals(TOKYO,chrono.getZone());
    }

    public void testFactory_Zone_int_2_oe() {
        GregorianChronology chrono = GregorianChronology.getInstance(TOKYO, 2);
        assertEquals(2,chrono.getMinimumDaysInFirstWeek());
    }

    public void testEquality_1_oe() {
        Object a = GregorianChronology.getInstance(TOKYO);
        assertSame(a, GregorianChronology.getInstance(TOKYO));
    }

    public void testEquality_2_oe() {
        Object a = GregorianChronology.getInstance(LONDON);
        assertSame(a, GregorianChronology.getInstance(LONDON));
    }

    public void testEquality_3_oe() {
        Object a = GregorianChronology.getInstance(PARIS);
        assertSame(a, GregorianChronology.getInstance(PARIS));
    }

    public void testEquality_4_oe() {
        Object a = GregorianChronology.getInstanceUTC();
        assertSame(a, GregorianChronology.getInstanceUTC());
    }

    public void testEquality_5_oe() {
        Object a = GregorianChronology.getInstance();
        assertSame(a, GregorianChronology.getInstance(LONDON));
    }

    public void testWithUTC_1_oe() {
        Object a = GregorianChronology.getInstanceUTC();
        assertSame(a, GregorianChronology.getInstance(LONDON).withUTC());
    }

    public void testWithUTC_2_oe() {
        Object a = GregorianChronology.getInstanceUTC();
        assertSame(a, GregorianChronology.getInstance(TOKYO).withUTC());
    }

    public void testWithUTC_3_oe() {
        Object a = GregorianChronology.getInstanceUTC();
        assertSame(a, GregorianChronology.getInstanceUTC().withUTC());
    }

    public void testWithUTC_4_oe() {
        Object a = GregorianChronology.getInstanceUTC();
        assertSame(a, GregorianChronology.getInstance().withUTC());
    }

    public void testWithZone_1_oe() {
        Object a = GregorianChronology.getInstance(TOKYO);
        assertSame(a, GregorianChronology.getInstance(TOKYO).withZone(TOKYO));
    }

    public void testWithZone_2_oe() {
        Object a = GregorianChronology.getInstance(LONDON);
        assertSame(a, GregorianChronology.getInstance(TOKYO).withZone(LONDON));
    }

    public void testWithZone_3_oe() {
        Object a = GregorianChronology.getInstance(PARIS);
        assertSame(a, GregorianChronology.getInstance(TOKYO).withZone(PARIS));
    }

    public void testWithZone_4_oe() {
        Object a = GregorianChronology.getInstance(LONDON);
        assertSame(a, GregorianChronology.getInstance(TOKYO).withZone(null));
    }

    public void testWithZone_5_oe() {
        Object a = GregorianChronology.getInstance(PARIS);
        assertSame(a, GregorianChronology.getInstance().withZone(PARIS));
    }

    public void testWithZone_6_oe() {
        Object a = GregorianChronology.getInstance(PARIS);
        assertSame(a, GregorianChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString_1_oe() {
        Object a = GregorianChronology.getInstance(LONDON).toString();
        assertEquals("GregorianChronology[Europe/London]", a);
    }

    public void testToString_2_oe() {
        Object a = GregorianChronology.getInstance(TOKYO).toString();
        assertEquals("GregorianChronology[Asia/Tokyo]", a);
    }

    public void testToString_3_oe() {
        Object a = GregorianChronology.getInstance().toString();
        assertEquals("GregorianChronology[Europe/London]", a);
    }

    public void testToString_4_oe() {
        Object a = GregorianChronology.getInstanceUTC().toString();
        assertEquals("GregorianChronology[UTC]", a);
    }

    public void testToString_5_oe() {
        Object a = GregorianChronology.getInstance(DateTimeZone.UTC,2).toString();
        assertEquals("GregorianChronology[UTC,mdfw=2]", a);
    }

    public void testDurationFields_1_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("eras",greg.eras().getName());
    }

    public void testDurationFields_2_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("centuries",greg.centuries().getName());
    }

    public void testDurationFields_3_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("years",greg.years().getName());
    }

    public void testDurationFields_4_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("weekyears",greg.weekyears().getName());
    }

    public void testDurationFields_5_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("months",greg.months().getName());
    }

    public void testDurationFields_6_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("weeks",greg.weeks().getName());
    }

    public void testDurationFields_7_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("days",greg.days().getName());
    }

    public void testDurationFields_8_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("halfdays",greg.halfdays().getName());
    }

    public void testDurationFields_9_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("hours",greg.hours().getName());
    }

    public void testDurationFields_10_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("minutes",greg.minutes().getName());
    }

    public void testDurationFields_11_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("seconds",greg.seconds().getName());
    }

    public void testDurationFields_12_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("millis",greg.millis().getName());
    }

    public void testDurationFields_13_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(false,greg.eras().isSupported());
    }

    public void testDurationFields_14_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.centuries().isSupported());
    }

    public void testDurationFields_15_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.years().isSupported());
    }

    public void testDurationFields_16_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.weekyears().isSupported());
    }

    public void testDurationFields_17_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.months().isSupported());
    }

    public void testDurationFields_18_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.weeks().isSupported());
    }

    public void testDurationFields_19_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.days().isSupported());
    }

    public void testDurationFields_20_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.halfdays().isSupported());
    }

    public void testDurationFields_21_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.hours().isSupported());
    }

    public void testDurationFields_22_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.minutes().isSupported());
    }

    public void testDurationFields_23_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.seconds().isSupported());
    }

    public void testDurationFields_24_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.millis().isSupported());
    }

    public void testDurationFields_25_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(false,greg.centuries().isPrecise());
    }

    public void testDurationFields_26_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(false,greg.years().isPrecise());
    }

    public void testDurationFields_27_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(false,greg.weekyears().isPrecise());
    }

    public void testDurationFields_28_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(false,greg.months().isPrecise());
    }

    public void testDurationFields_29_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(false,greg.weeks().isPrecise());
    }

    public void testDurationFields_30_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(false,greg.days().isPrecise());
    }

    public void testDurationFields_31_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(false,greg.halfdays().isPrecise());
    }

    public void testDurationFields_32_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(true,greg.hours().isPrecise());
    }

    public void testDurationFields_33_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(true,greg.minutes().isPrecise());
    }

    public void testDurationFields_34_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(true,greg.seconds().isPrecise());
    }

    public void testDurationFields_35_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(true,greg.millis().isPrecise());
    }

    public void testDurationFields_36_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(false,gregUTC.centuries().isPrecise());
    }

    public void testDurationFields_37_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(false,gregUTC.years().isPrecise());
    }

    public void testDurationFields_38_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(false,gregUTC.weekyears().isPrecise());
    }

    public void testDurationFields_39_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(false,gregUTC.months().isPrecise());
    }

    public void testDurationFields_40_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(true,gregUTC.weeks().isPrecise());
    }

    public void testDurationFields_41_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(true,gregUTC.days().isPrecise());
    }

    public void testDurationFields_42_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(true,gregUTC.halfdays().isPrecise());
    }

    public void testDurationFields_43_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(true,gregUTC.hours().isPrecise());
    }

    public void testDurationFields_44_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(true,gregUTC.minutes().isPrecise());
    }

    public void testDurationFields_45_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(true,gregUTC.seconds().isPrecise());
    }

    public void testDurationFields_46_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        assertEquals(true,gregUTC.millis().isPrecise());
    }

    public void testDurationFields_47_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(false,gregGMT.centuries().isPrecise());
    }

    public void testDurationFields_48_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(false,gregGMT.years().isPrecise());
    }

    public void testDurationFields_49_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(false,gregGMT.weekyears().isPrecise());
    }

    public void testDurationFields_50_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(false,gregGMT.months().isPrecise());
    }

    public void testDurationFields_51_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(true,gregGMT.weeks().isPrecise());
    }

    public void testDurationFields_52_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(true,gregGMT.days().isPrecise());
    }

    public void testDurationFields_53_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(true,gregGMT.halfdays().isPrecise());
    }

    public void testDurationFields_54_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(true,gregGMT.hours().isPrecise());
    }

    public void testDurationFields_55_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(true,gregGMT.minutes().isPrecise());
    }

    public void testDurationFields_56_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(true,gregGMT.seconds().isPrecise());
    }

    public void testDurationFields_57_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        final GregorianChronology gregUTC = GregorianChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GregorianChronology gregGMT = GregorianChronology.getInstance(gmt);
        assertEquals(true,gregGMT.millis().isPrecise());
    }

    public void testDateFields_1_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("era",greg.era().getName());
    }

    public void testDateFields_2_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("centuryOfEra",greg.centuryOfEra().getName());
    }

    public void testDateFields_3_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("yearOfCentury",greg.yearOfCentury().getName());
    }

    public void testDateFields_4_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("yearOfEra",greg.yearOfEra().getName());
    }

    public void testDateFields_5_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("year",greg.year().getName());
    }

    public void testDateFields_6_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("monthOfYear",greg.monthOfYear().getName());
    }

    public void testDateFields_7_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("weekyearOfCentury",greg.weekyearOfCentury().getName());
    }

    public void testDateFields_8_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("weekyear",greg.weekyear().getName());
    }

    public void testDateFields_9_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("weekOfWeekyear",greg.weekOfWeekyear().getName());
    }

    public void testDateFields_10_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("dayOfYear",greg.dayOfYear().getName());
    }

    public void testDateFields_11_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("dayOfMonth",greg.dayOfMonth().getName());
    }

    public void testDateFields_12_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("dayOfWeek",greg.dayOfWeek().getName());
    }

    public void testDateFields_13_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.era().isSupported());
    }

    public void testDateFields_14_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.centuryOfEra().isSupported());
    }

    public void testDateFields_15_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.yearOfCentury().isSupported());
    }

    public void testDateFields_16_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.yearOfEra().isSupported());
    }

    public void testDateFields_17_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.year().isSupported());
    }

    public void testDateFields_18_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.monthOfYear().isSupported());
    }

    public void testDateFields_19_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.weekyearOfCentury().isSupported());
    }

    public void testDateFields_20_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.weekyear().isSupported());
    }

    public void testDateFields_21_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.weekOfWeekyear().isSupported());
    }

    public void testDateFields_22_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.dayOfYear().isSupported());
    }

    public void testDateFields_23_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.dayOfMonth().isSupported());
    }

    public void testDateFields_24_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.dayOfWeek().isSupported());
    }

    public void testDateFields_25_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.eras(),greg.era().getDurationField());
    }

    public void testDateFields_26_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.centuries(),greg.centuryOfEra().getDurationField());
    }

    public void testDateFields_27_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.years(),greg.yearOfCentury().getDurationField());
    }

    public void testDateFields_28_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.years(),greg.yearOfEra().getDurationField());
    }

    public void testDateFields_29_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.years(),greg.year().getDurationField());
    }

    public void testDateFields_30_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.months(),greg.monthOfYear().getDurationField());
    }

    public void testDateFields_31_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.weekyears(),greg.weekyearOfCentury().getDurationField());
    }

    public void testDateFields_32_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.weekyears(),greg.weekyear().getDurationField());
    }

    public void testDateFields_33_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.weeks(),greg.weekOfWeekyear().getDurationField());
    }

    public void testDateFields_34_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.days(),greg.dayOfYear().getDurationField());
    }

    public void testDateFields_35_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.days(),greg.dayOfMonth().getDurationField());
    }

    public void testDateFields_36_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        assertEquals(greg.days(),greg.dayOfWeek().getDurationField());
    }

    public void testDateFields_37_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(null,greg.era().getRangeDurationField());
    }

    public void testDateFields_38_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(greg.eras(),greg.centuryOfEra().getRangeDurationField());
    }

    public void testDateFields_39_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(greg.centuries(),greg.yearOfCentury().getRangeDurationField());
    }

    public void testDateFields_40_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(greg.eras(),greg.yearOfEra().getRangeDurationField());
    }

    public void testDateFields_41_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(null,greg.year().getRangeDurationField());
    }

    public void testDateFields_42_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(greg.years(),greg.monthOfYear().getRangeDurationField());
    }

    public void testDateFields_43_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(greg.centuries(),greg.weekyearOfCentury().getRangeDurationField());
    }

    public void testDateFields_44_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(null,greg.weekyear().getRangeDurationField());
    }

    public void testDateFields_45_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(greg.weekyears(),greg.weekOfWeekyear().getRangeDurationField());
    }

    public void testDateFields_46_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(greg.years(),greg.dayOfYear().getRangeDurationField());
    }

    public void testDateFields_47_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(greg.months(),greg.dayOfMonth().getRangeDurationField());
    }

    public void testDateFields_48_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        
        
        assertEquals(greg.weeks(),greg.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields_1_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("halfdayOfDay",greg.halfdayOfDay().getName());
    }

    public void testTimeFields_2_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("clockhourOfHalfday",greg.clockhourOfHalfday().getName());
    }

    public void testTimeFields_3_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("hourOfHalfday",greg.hourOfHalfday().getName());
    }

    public void testTimeFields_4_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("clockhourOfDay",greg.clockhourOfDay().getName());
    }

    public void testTimeFields_5_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("hourOfDay",greg.hourOfDay().getName());
    }

    public void testTimeFields_6_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("minuteOfDay",greg.minuteOfDay().getName());
    }

    public void testTimeFields_7_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("minuteOfHour",greg.minuteOfHour().getName());
    }

    public void testTimeFields_8_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("secondOfDay",greg.secondOfDay().getName());
    }

    public void testTimeFields_9_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("secondOfMinute",greg.secondOfMinute().getName());
    }

    public void testTimeFields_10_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("millisOfDay",greg.millisOfDay().getName());
    }

    public void testTimeFields_11_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        assertEquals("millisOfSecond",greg.millisOfSecond().getName());
    }

    public void testTimeFields_12_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.halfdayOfDay().isSupported());
    }

    public void testTimeFields_13_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.clockhourOfHalfday().isSupported());
    }

    public void testTimeFields_14_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.hourOfHalfday().isSupported());
    }

    public void testTimeFields_15_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.clockhourOfDay().isSupported());
    }

    public void testTimeFields_16_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.hourOfDay().isSupported());
    }

    public void testTimeFields_17_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.minuteOfDay().isSupported());
    }

    public void testTimeFields_18_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.minuteOfHour().isSupported());
    }

    public void testTimeFields_19_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.secondOfDay().isSupported());
    }

    public void testTimeFields_20_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.secondOfMinute().isSupported());
    }

    public void testTimeFields_21_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.millisOfDay().isSupported());
    }

    public void testTimeFields_22_oe() {
        final GregorianChronology greg = GregorianChronology.getInstance();
        
        assertEquals(true,greg.millisOfSecond().isSupported());
    }

    public void testMaximumValue_1_oe() {
        YearMonthDay ymd1 = new YearMonthDay(1999, DateTimeConstants.FEBRUARY, 1);
        DateMidnight dm1 = new DateMidnight(1999, DateTimeConstants.FEBRUARY, 1);
        Chronology chrono = GregorianChronology.getInstance();
        assertEquals(28,chrono.dayOfMonth().getMaximumValue(ymd1));
    }

    public void testMaximumValue_2_oe() {
        YearMonthDay ymd1 = new YearMonthDay(1999, DateTimeConstants.FEBRUARY, 1);
        DateMidnight dm1 = new DateMidnight(1999, DateTimeConstants.FEBRUARY, 1);
        Chronology chrono = GregorianChronology.getInstance();
        assertEquals(28,chrono.dayOfMonth().getMaximumValue(dm1.getMillis()));
    }

    public void testLeap_28feb_1_oe() {
        Chronology chrono = GregorianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
    }

    public void testLeap_28feb_2_oe() {
        Chronology chrono = GregorianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(true,dt.monthOfYear().isLeap());
    }

    public void testLeap_28feb_3_oe() {
        Chronology chrono = GregorianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(false,dt.dayOfMonth().isLeap());
    }

    public void testLeap_28feb_4_oe() {
        Chronology chrono = GregorianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(false,dt.dayOfYear().isLeap());
    }

    public void testLeap_29feb_1_oe() {
        Chronology chrono = GregorianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
    }

    public void testLeap_29feb_2_oe() {
        Chronology chrono = GregorianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.monthOfYear().isLeap());
    }

    public void testLeap_29feb_3_oe() {
        Chronology chrono = GregorianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.dayOfMonth().isLeap());
    }

    public void testLeap_29feb_4_oe() {
        Chronology chrono = GregorianChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.dayOfYear().isLeap());
    }

public void testFactory_Zone_int_oe_101_oe() {
        try {
            GregorianChronology.getInstance(TOKYO, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testFactory_Zone_int_oe_102_oe() {
        try {
            GregorianChronology.getInstance(TOKYO, 8);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

}
