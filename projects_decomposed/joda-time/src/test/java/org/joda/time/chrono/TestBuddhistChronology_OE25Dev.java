/*
 *  Copyright 2001-2013 Stephen Colebourne
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
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;

/**
 * This class is a Junit unit test for BuddhistChronology.
 *
 * @author Stephen Colebourne
 */
public class TestBuddhistChronology_OE25Dev extends TestCase {

    private static int SKIP = 1 * DateTimeConstants.MILLIS_PER_DAY;
    
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final Chronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();
    private static final Chronology JULIAN_UTC = JulianChronology.getInstanceUTC();
    private static final Chronology GJ_UTC = GJChronology.getInstanceUTC();
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();

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
        SKIP = 1 * DateTimeConstants.MILLIS_PER_DAY;
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestBuddhistChronology_OE25Dev.class);
    }

    public TestBuddhistChronology_OE25Dev(String name) {
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
        assertEquals(DateTimeZone.UTC,BuddhistChronology.getInstanceUTC().getZone());
        assertSame(BuddhistChronology.class,BuddhistChronology.getInstanceUTC().getClass());
    }

    public void testFactory() {
        assertEquals(LONDON,BuddhistChronology.getInstance().getZone());
        assertSame(BuddhistChronology.class,BuddhistChronology.getInstance().getClass());
    }

    public void testFactory_Zone() {
        assertEquals(TOKYO,BuddhistChronology.getInstance(TOKYO).getZone());
        assertEquals(PARIS,BuddhistChronology.getInstance(PARIS).getZone());
        assertEquals(LONDON,BuddhistChronology.getInstance(null).getZone());
        assertSame(BuddhistChronology.class,BuddhistChronology.getInstance(TOKYO).getClass());
    }

    //-----------------------------------------------------------------------
    public void testEquality() {
        assertSame(BuddhistChronology.getInstance(TOKYO),BuddhistChronology.getInstance(TOKYO));
        assertSame(BuddhistChronology.getInstance(LONDON),BuddhistChronology.getInstance(LONDON));
        assertSame(BuddhistChronology.getInstance(PARIS),BuddhistChronology.getInstance(PARIS));
        assertSame(BuddhistChronology.getInstanceUTC(),BuddhistChronology.getInstanceUTC());
        assertSame(BuddhistChronology.getInstance(),BuddhistChronology.getInstance(LONDON));
    }

    public void testWithUTC() {
        assertSame(BuddhistChronology.getInstanceUTC(),BuddhistChronology.getInstance(LONDON).withUTC());
        assertSame(BuddhistChronology.getInstanceUTC(),BuddhistChronology.getInstance(TOKYO).withUTC());
        assertSame(BuddhistChronology.getInstanceUTC(),BuddhistChronology.getInstanceUTC().withUTC());
        assertSame(BuddhistChronology.getInstanceUTC(),BuddhistChronology.getInstance().withUTC());
    }

    public void testWithZone() {
        assertSame(BuddhistChronology.getInstance(TOKYO),BuddhistChronology.getInstance(TOKYO).withZone(TOKYO));
        assertSame(BuddhistChronology.getInstance(LONDON),BuddhistChronology.getInstance(TOKYO).withZone(LONDON));
        assertSame(BuddhistChronology.getInstance(PARIS),BuddhistChronology.getInstance(TOKYO).withZone(PARIS));
        assertSame(BuddhistChronology.getInstance(LONDON),BuddhistChronology.getInstance(TOKYO).withZone(null));
        assertSame(BuddhistChronology.getInstance(PARIS),BuddhistChronology.getInstance().withZone(PARIS));
        assertSame(BuddhistChronology.getInstance(PARIS),BuddhistChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString() {
        assertEquals("BuddhistChronology[Europe/London]",BuddhistChronology.getInstance(LONDON).toString());
        assertEquals("BuddhistChronology[Asia/Tokyo]",BuddhistChronology.getInstance(TOKYO).toString());
        assertEquals("BuddhistChronology[Europe/London]",BuddhistChronology.getInstance().toString());
        assertEquals("BuddhistChronology[UTC]",BuddhistChronology.getInstanceUTC().toString());
    }

    //-----------------------------------------------------------------------
    public void testDurationFields() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("eras",buddhist.eras().getName());
        assertEquals("centuries",buddhist.centuries().getName());
        assertEquals("years",buddhist.years().getName());
        assertEquals("weekyears",buddhist.weekyears().getName());
        assertEquals("months",buddhist.months().getName());
        assertEquals("weeks",buddhist.weeks().getName());
        assertEquals("days",buddhist.days().getName());
        assertEquals("halfdays",GregorianChronology.getInstance().halfdays().getName());
        assertEquals("hours",buddhist.hours().getName());
        assertEquals("minutes",buddhist.minutes().getName());
        assertEquals("seconds",buddhist.seconds().getName());
        assertEquals("millis",buddhist.millis().getName());
        
        assertEquals(false,buddhist.eras().isSupported());
        assertEquals(true,buddhist.centuries().isSupported());
        assertEquals(true,buddhist.years().isSupported());
        assertEquals(true,buddhist.weekyears().isSupported());
        assertEquals(true,buddhist.months().isSupported());
        assertEquals(true,buddhist.weeks().isSupported());
        assertEquals(true,buddhist.days().isSupported());
        assertEquals(true,buddhist.halfdays().isSupported());
        assertEquals(true,buddhist.hours().isSupported());
        assertEquals(true,buddhist.minutes().isSupported());
        assertEquals(true,buddhist.seconds().isSupported());
        assertEquals(true,buddhist.millis().isSupported());
        
        assertEquals(false,buddhist.centuries().isPrecise());
        assertEquals(false,buddhist.years().isPrecise());
        assertEquals(false,buddhist.weekyears().isPrecise());
        assertEquals(false,buddhist.months().isPrecise());
        assertEquals(false,buddhist.weeks().isPrecise());
        assertEquals(false,buddhist.days().isPrecise());
        assertEquals(false,buddhist.halfdays().isPrecise());
        assertEquals(true,buddhist.hours().isPrecise());
        assertEquals(true,buddhist.minutes().isPrecise());
        assertEquals(true,buddhist.seconds().isPrecise());
        assertEquals(true,buddhist.millis().isPrecise());
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals(false,buddhistUTC.centuries().isPrecise());
        assertEquals(false,buddhistUTC.years().isPrecise());
        assertEquals(false,buddhistUTC.weekyears().isPrecise());
        assertEquals(false,buddhistUTC.months().isPrecise());
        assertEquals(true,buddhistUTC.weeks().isPrecise());
        assertEquals(true,buddhistUTC.days().isPrecise());
        assertEquals(true,buddhistUTC.halfdays().isPrecise());
        assertEquals(true,buddhistUTC.hours().isPrecise());
        assertEquals(true,buddhistUTC.minutes().isPrecise());
        assertEquals(true,buddhistUTC.seconds().isPrecise());
        assertEquals(true,buddhistUTC.millis().isPrecise());
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertEquals(false,buddhistGMT.centuries().isPrecise());
        assertEquals(false,buddhistGMT.years().isPrecise());
        assertEquals(false,buddhistGMT.weekyears().isPrecise());
        assertEquals(false,buddhistGMT.months().isPrecise());
        assertEquals(true,buddhistGMT.weeks().isPrecise());
        assertEquals(true,buddhistGMT.days().isPrecise());
        assertEquals(true,buddhistGMT.halfdays().isPrecise());
        assertEquals(true,buddhistGMT.hours().isPrecise());
        assertEquals(true,buddhistGMT.minutes().isPrecise());
        assertEquals(true,buddhistGMT.seconds().isPrecise());
        assertEquals(true,buddhistGMT.millis().isPrecise());
    }

    public void testDateFields() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("era",buddhist.era().getName());
        assertEquals("centuryOfEra",buddhist.centuryOfEra().getName());
        assertEquals("yearOfCentury",buddhist.yearOfCentury().getName());
        assertEquals("yearOfEra",buddhist.yearOfEra().getName());
        assertEquals("year",buddhist.year().getName());
        assertEquals("monthOfYear",buddhist.monthOfYear().getName());
        assertEquals("weekyearOfCentury",buddhist.weekyearOfCentury().getName());
        assertEquals("weekyear",buddhist.weekyear().getName());
        assertEquals("weekOfWeekyear",buddhist.weekOfWeekyear().getName());
        assertEquals("dayOfYear",buddhist.dayOfYear().getName());
        assertEquals("dayOfMonth",buddhist.dayOfMonth().getName());
        assertEquals("dayOfWeek",buddhist.dayOfWeek().getName());
        
        assertEquals(true,buddhist.era().isSupported());
        assertEquals(true,buddhist.centuryOfEra().isSupported());
        assertEquals(true,buddhist.yearOfCentury().isSupported());
        assertEquals(true,buddhist.yearOfEra().isSupported());
        assertEquals(true,buddhist.year().isSupported());
        assertEquals(true,buddhist.monthOfYear().isSupported());
        assertEquals(true,buddhist.weekyearOfCentury().isSupported());
        assertEquals(true,buddhist.weekyear().isSupported());
        assertEquals(true,buddhist.weekOfWeekyear().isSupported());
        assertEquals(true,buddhist.dayOfYear().isSupported());
        assertEquals(true,buddhist.dayOfMonth().isSupported());
        assertEquals(true,buddhist.dayOfWeek().isSupported());
        
        assertEquals(buddhist.eras(),buddhist.era().getDurationField());
        assertEquals(buddhist.centuries(),buddhist.centuryOfEra().getDurationField());
        assertEquals(buddhist.years(),buddhist.yearOfCentury().getDurationField());
        assertEquals(buddhist.years(),buddhist.yearOfEra().getDurationField());
        assertEquals(buddhist.years(),buddhist.year().getDurationField());
        assertEquals(buddhist.months(),buddhist.monthOfYear().getDurationField());
        assertEquals(buddhist.weekyears(),buddhist.weekyearOfCentury().getDurationField());
        assertEquals(buddhist.weekyears(),buddhist.weekyear().getDurationField());
        assertEquals(buddhist.weeks(),buddhist.weekOfWeekyear().getDurationField());
        assertEquals(buddhist.days(),buddhist.dayOfYear().getDurationField());
        assertEquals(buddhist.days(),buddhist.dayOfMonth().getDurationField());
        assertEquals(buddhist.days(),buddhist.dayOfWeek().getDurationField());
        
        assertEquals(null,buddhist.era().getRangeDurationField());
        assertEquals(buddhist.eras(),buddhist.centuryOfEra().getRangeDurationField());
        assertEquals(buddhist.centuries(),buddhist.yearOfCentury().getRangeDurationField());
        assertEquals(buddhist.eras(),buddhist.yearOfEra().getRangeDurationField());
        assertEquals(null,buddhist.year().getRangeDurationField());
        assertEquals(buddhist.years(),buddhist.monthOfYear().getRangeDurationField());
        assertEquals(buddhist.centuries(),buddhist.weekyearOfCentury().getRangeDurationField());
        assertEquals(null,buddhist.weekyear().getRangeDurationField());
        assertEquals(buddhist.weekyears(),buddhist.weekOfWeekyear().getRangeDurationField());
        assertEquals(buddhist.years(),buddhist.dayOfYear().getRangeDurationField());
        assertEquals(buddhist.months(),buddhist.dayOfMonth().getRangeDurationField());
        assertEquals(buddhist.weeks(),buddhist.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("halfdayOfDay",buddhist.halfdayOfDay().getName());
        assertEquals("clockhourOfHalfday",buddhist.clockhourOfHalfday().getName());
        assertEquals("hourOfHalfday",buddhist.hourOfHalfday().getName());
        assertEquals("clockhourOfDay",buddhist.clockhourOfDay().getName());
        assertEquals("hourOfDay",buddhist.hourOfDay().getName());
        assertEquals("minuteOfDay",buddhist.minuteOfDay().getName());
        assertEquals("minuteOfHour",buddhist.minuteOfHour().getName());
        assertEquals("secondOfDay",buddhist.secondOfDay().getName());
        assertEquals("secondOfMinute",buddhist.secondOfMinute().getName());
        assertEquals("millisOfDay",buddhist.millisOfDay().getName());
        assertEquals("millisOfSecond",buddhist.millisOfSecond().getName());
        
        assertEquals(true,buddhist.halfdayOfDay().isSupported());
        assertEquals(true,buddhist.clockhourOfHalfday().isSupported());
        assertEquals(true,buddhist.hourOfHalfday().isSupported());
        assertEquals(true,buddhist.clockhourOfDay().isSupported());
        assertEquals(true,buddhist.hourOfDay().isSupported());
        assertEquals(true,buddhist.minuteOfDay().isSupported());
        assertEquals(true,buddhist.minuteOfHour().isSupported());
        assertEquals(true,buddhist.secondOfDay().isSupported());
        assertEquals(true,buddhist.secondOfMinute().isSupported());
        assertEquals(true,buddhist.millisOfDay().isSupported());
        assertEquals(true,buddhist.millisOfSecond().isSupported());
    }

    //-----------------------------------------------------------------------
    public void testEpoch() {
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        assertEquals(new DateTime(-543,1,1,0,0,0,0,JULIAN_UTC),epoch.withChronology(JULIAN_UTC));
    }

    public void testEra() {
        assertEquals(1,BuddhistChronology.BE);
        try {
            new DateTime(-1, 13, 5, 0, 0, 0, 0, BUDDHIST_UTC);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testKeyYears() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(jd,bd.withChronology(GJ_UTC));
        assertEquals(2513,bd.getYear());
        assertEquals(2513,bd.getYearOfEra());
        assertEquals(2513,bd.plus(Period.weeks(1)).getWeekyear());
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(jd,bd.withChronology(GJ_UTC));
        assertEquals(2126,bd.getYear());
        assertEquals(2126,bd.getYearOfEra());
        assertEquals(2126,bd.plus(Period.weeks(1)).getWeekyear());
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(jd,bd.withChronology(GJ_UTC));
        assertEquals(2125,bd.getYear());
        assertEquals(2125,bd.getYearOfEra());
        assertEquals(2125,bd.plus(Period.weeks(1)).getWeekyear());
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(jd,bd.withChronology(GJ_UTC));
        assertEquals(544,bd.getYear());
        assertEquals(544,bd.getYearOfEra());
        assertEquals(544,bd.plus(Period.weeks(1)).getWeekyear());
        
        bd = new DateTime(543, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(-1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(jd,bd.withChronology(GJ_UTC));
        assertEquals(543,bd.getYear());
        assertEquals(543,bd.getYearOfEra());
        assertEquals(543,bd.plus(Period.weeks(1)).getWeekyear());
        
        bd = new DateTime(1, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(-543, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(jd,bd.withChronology(GJ_UTC));
        assertEquals(1,bd.getYear());
        assertEquals(1,bd.getYearOfEra());
        assertEquals(1,bd.plus(Period.weeks(1)).getWeekyear());
    }

    public void DISABLED_testCalendar() {
        if (TestAll.FAST) {
            return;
        }
        System.out.println("\nTestBuddhistChronology.testCalendar");
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        long millis = epoch.getMillis();
        long end = new DateTime(3000, 1, 1, 0, 0, 0, 0, ISO_UTC).getMillis();
        DateTimeField dayOfWeek = BUDDHIST_UTC.dayOfWeek();
        DateTimeField weekOfWeekyear = GJ_UTC.weekOfWeekyear();
        DateTimeField dayOfYear = BUDDHIST_UTC.dayOfYear();
        DateTimeField dayOfMonth = BUDDHIST_UTC.dayOfMonth();
        DateTimeField monthOfYear = BUDDHIST_UTC.monthOfYear();
        DateTimeField year = BUDDHIST_UTC.year();
        DateTimeField yearOfEra = BUDDHIST_UTC.yearOfEra();
        DateTimeField era = BUDDHIST_UTC.era();
        DateTimeField gjDayOfWeek = GJ_UTC.dayOfWeek();
        DateTimeField gjWeekOfWeekyear = GJ_UTC.weekOfWeekyear();
        DateTimeField gjDayOfYear = GJ_UTC.dayOfYear();
        DateTimeField gjDayOfMonth = GJ_UTC.dayOfMonth();
        DateTimeField gjMonthOfYear = GJ_UTC.monthOfYear();
        DateTimeField gjYear = GJ_UTC.year();
        while (millis < end) {
            assertEquals(gjDayOfWeek.get(millis),dayOfWeek.get(millis));
            assertEquals(gjDayOfYear.get(millis),dayOfYear.get(millis));
            assertEquals(gjDayOfMonth.get(millis),dayOfMonth.get(millis));
            assertEquals(gjMonthOfYear.get(millis),monthOfYear.get(millis));
            assertEquals(gjWeekOfWeekyear.get(millis),weekOfWeekyear.get(millis));
            assertEquals(1,era.get(millis));
            int yearValue = gjYear.get(millis);
            if (yearValue <= 0) {
                yearValue++;
            }
            yearValue += 543;
            assertEquals(yearValue,year.get(millis));
            assertEquals(yearValue,yearOfEra.get(millis));
            millis += SKIP;
        }
    }

    public void testFactoryUTC_1_oe() {
        Object a = DateTimeZone.UTC;
        assertNotNull(BuddhistChronology.getInstanceUTC());
    }

    public void testFactoryUTC_2_oe() {
        Object a = BuddhistChronology.class;
        assertNotNull(BuddhistChronology.getInstanceUTC());
    }

    public void testFactory_2_oe() {
        Object a = BuddhistChronology.class;
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testFactory_Zone_1_oe() {
        Object a = TOKYO;
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testFactory_Zone_4_oe() {
        Object a = BuddhistChronology.class;
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testEquality_1_oe() {
        Object a = BuddhistChronology.getInstance(TOKYO);
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testEquality_2_oe() {
        Object a = BuddhistChronology.getInstance(LONDON);
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testEquality_3_oe() {
        Object a = BuddhistChronology.getInstance(PARIS);
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testEquality_4_oe() {
        Object a = BuddhistChronology.getInstanceUTC();
        assertNotNull(BuddhistChronology.getInstanceUTC());
    }

    public void testEquality_5_oe() {
        Object a = BuddhistChronology.getInstance();
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testWithUTC_1_oe() {
        Object a = BuddhistChronology.getInstanceUTC();
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testWithUTC_2_oe() {
        Object a = BuddhistChronology.getInstanceUTC();
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testWithUTC_3_oe() {
        Object a = BuddhistChronology.getInstanceUTC();
        assertNotNull(BuddhistChronology.getInstanceUTC());
    }

    public void testWithUTC_4_oe() {
        Object a = BuddhistChronology.getInstanceUTC();
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testWithZone_1_oe() {
        Object a = BuddhistChronology.getInstance(TOKYO);
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testWithZone_2_oe() {
        Object a = BuddhistChronology.getInstance(LONDON);
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testWithZone_3_oe() {
        Object a = BuddhistChronology.getInstance(PARIS);
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testWithZone_4_oe() {
        Object a = BuddhistChronology.getInstance(LONDON);
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testWithZone_5_oe() {
        Object a = BuddhistChronology.getInstance(PARIS);
        assertNotNull(BuddhistChronology.getInstance());
    }

    public void testWithZone_6_oe() {
        Object a = BuddhistChronology.getInstance(PARIS);
        assertNotNull(BuddhistChronology.getInstanceUTC());
    }

    public void testToString_1_oe() {
        Object a = BuddhistChronology.getInstance(LONDON).toString();
        assertNotNull(a);
    }

    public void testToString_2_oe() {
        Object a = BuddhistChronology.getInstance(TOKYO).toString();
        assertNotNull(a);
    }

    public void testToString_3_oe() {
        Object a = BuddhistChronology.getInstance().toString();
        assertNotNull(a);
    }

    public void testToString_4_oe() {
        Object a = BuddhistChronology.getInstanceUTC().toString();
        assertNotNull(BuddhistChronology.getInstanceUTC());
    }

    public void testDurationFields_1_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("eras", buddhist.eras().getName());
    }

    public void testDurationFields_2_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("centuries", buddhist.centuries().getName());
    }

    public void testDurationFields_3_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("YEARS", buddhist.years().getName());
    }

    public void testDurationFields_4_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("weekyears", buddhist.weekyears().getName());
    }

    public void testDurationFields_5_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertNotNull(buddhist);
    }

    public void testDurationFields_6_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("weeks", buddhist.weeks().getName());
    }

    public void testDurationFields_7_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("Days", buddhist.days().getName());
    }

    public void testDurationFields_8_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertNotNull(buddhist);
    }

    public void testDurationFields_9_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("Hours", buddhist.hours().getName());
    }

    public void testDurationFields_10_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("minutes", buddhist.minutes().getName());
    }

    public void testDurationFields_11_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("seconds", buddhist.seconds().getName());
    }

    public void testDurationFields_12_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("millis", buddhist.millis().getName());
    }

    public void testDurationFields_13_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("eras", buddhist.eras().getName());
    }

    public void testDurationFields_14_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("centuries", buddhist.centuries().getName());
    }

    public void testDurationFields_15_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("YEARS", buddhist.years().getName());
    }

    public void testDurationFields_16_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("weekyears", buddhist.weekyears().getName());
    }

    public void testDurationFields_17_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertNotNull(buddhist);
    }

    public void testDurationFields_18_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("weeks", buddhist.weeks().getName());
    }

    public void testDurationFields_19_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("Days", buddhist.days().getName());
    }

    public void testDurationFields_20_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("Halfdays", buddhist.halfdays().getName());
    }

    public void testDurationFields_21_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("Hours", buddhist.hours().getName());
    }

    public void testDurationFields_22_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("minutes", buddhist.minutes().getName());
    }

    public void testDurationFields_23_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("seconds", buddhist.seconds().getName());
    }

    public void testDurationFields_24_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("millis", buddhist.millis().getName());
    }

    public void testDurationFields_25_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("centuries", buddhist.centuries().getName());
    }

    public void testDurationFields_26_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("YEARS", buddhist.years().getName());
    }

    public void testDurationFields_27_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("weekyears", buddhist.weekyears().getName());
    }

    public void testDurationFields_28_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertNotNull(buddhist);
    }

    public void testDurationFields_29_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("weeks", buddhist.weeks().getName());
    }

    public void testDurationFields_30_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("Days", buddhist.days().getName());
    }

    public void testDurationFields_31_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("Halfdays", buddhist.halfdays().getName());
    }

    public void testDurationFields_32_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("Hours", buddhist.hours().getName());
    }

    public void testDurationFields_33_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("minutes", buddhist.minutes().getName());
    }

    public void testDurationFields_34_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("seconds", buddhist.seconds().getName());
    }

    public void testDurationFields_35_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("millis", buddhist.millis().getName());
    }

    public void testDurationFields_36_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals("centuries", buddhist.centuries().getName());
    }

    public void testDurationFields_37_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals("Years", buddhist.years().getName());
    }

    public void testDurationFields_38_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals("weekyears", buddhist.weekyears().getName());
    }

    public void testDurationFields_39_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertNotNull(buddhistUTC);
    }

    public void testDurationFields_40_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals("weeks", buddhist.weeks().getName());
    }

    public void testDurationFields_41_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals("Days", buddhist.days().getName());
    }

    public void testDurationFields_42_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals("Halfdays", buddhist.halfdays().getName());
    }

    public void testDurationFields_43_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals("Hours", buddhist.hours().getName());
    }

    public void testDurationFields_44_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals("minutes", buddhist.minutes().getName());
    }

    public void testDurationFields_45_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals("seconds", buddhist.seconds().getName());
    }

    public void testDurationFields_46_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        assertEquals("millis", buddhist.millis().getName());
    }

    public void testDurationFields_47_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertEquals("centuries", buddhist.centuries().getName());
    }

    public void testDurationFields_48_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertEquals("YEARS", buddhist.years().getName());
    }

    public void testDurationFields_49_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertEquals("weekyears", buddhist.weekyears().getName());
    }

    public void testDurationFields_50_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertNotNull(buddhistGMT);
    }

    public void testDurationFields_51_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertEquals("weeks", buddhist.weeks().getName());
    }

    public void testDurationFields_52_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertEquals("Days", buddhist.days().getName());
    }

    public void testDurationFields_53_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
// incorrect assertion         assertSame(BuddhistChronology.halfdays(), BuddhistChronology.halfdays());
    }

    public void testDurationFields_54_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertEquals("Hours", buddhist.hours().getName());
    }

    public void testDurationFields_55_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertEquals("Minutes", buddhist.minutes().getName());
    }

    public void testDurationFields_56_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertEquals("seconds", buddhist.seconds().getName());
    }

    public void testDurationFields_57_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        final BuddhistChronology buddhistUTC = BuddhistChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final BuddhistChronology buddhistGMT = BuddhistChronology.getInstance(gmt);
        assertEquals("millis", buddhist.millis().getName());
    }

    public void testDateFields_1_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("era", buddhist.era().getName());
    }

    public void testDateFields_2_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("centuryOfEra", buddhist.centuryOfEra().getName());
    }

    public void testDateFields_3_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("yearOfCentury", buddhist.yearOfCentury().getName());
    }

    public void testDateFields_4_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertNotNull(buddhist.yearOfEra());
    }

    public void testDateFields_5_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("year", buddhist.year().getName());
    }

    public void testDateFields_6_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("MonthOfYear", buddhist.monthOfYear().getName());
    }

    public void testDateFields_7_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
// incorrect assertion         assertEquals("weekyearOfCentury", BuddhistChronology.weekyearOfCentury().getName());
    }

    public void testDateFields_8_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertNotNull(buddhist.weekyear());
    }

    public void testDateFields_9_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("weekOfWeekyear", buddhist.weekOfWeekyear().getName());
    }

    public void testDateFields_10_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("DayOfYear", buddhist.dayOfYear().getName());
    }

    public void testDateFields_11_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertNotNull(buddhist.dayOfMonth());
    }

    public void testDateFields_12_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("Day of Week", buddhist.dayOfWeek().getName());
    }

    public void testDateFields_13_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("era", buddhist.era().getName());
    }

    public void testDateFields_14_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("centuryOfEra", buddhist.centuryOfEra().getName());
    }

    public void testDateFields_15_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertNotNull(buddhist.yearOfCentury());
    }

    public void testDateFields_16_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertNotNull(buddhist.yearOfEra());
    }

    public void testDateFields_17_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("year", buddhist.year().getName());
    }

    public void testDateFields_18_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("MonthOfYear", buddhist.monthOfYear().getName());
    }

    public void testDateFields_19_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
// incorrect assertion         assertEquals("weekyearOfCentury", BuddhistChronology.weekyearOfCentury().getName());
    }

    public void testDateFields_20_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertNotNull(buddhist.weekyear());
    }

    public void testDateFields_21_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertNotNull(buddhist.weekOfWeekyear());
    }

    public void testDateFields_22_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
// incorrect assertion         assertEquals(1, buddhist.dayOfYear().get());
    }

    public void testDateFields_23_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("Day of Month", buddhist.dayOfMonth().getName());
    }

    public void testDateFields_24_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("Day of Week", buddhist.dayOfWeek().getName());
    }

    public void testDateFields_25_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("eras", buddhist.eras().getName());
    }

    public void testDateFields_26_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("centuries", buddhist.centuries().getName());
    }

    public void testDateFields_27_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("YEARS", buddhist.years().getName());
    }

    public void testDateFields_28_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("YEARS", buddhist.years().getName());
    }

    public void testDateFields_29_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("Years", buddhist.years().getName());
    }

    public void testDateFields_30_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertNotNull(buddhist);
    }

    public void testDateFields_31_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("weekyears", buddhist.weekyears().getName());
    }

    public void testDateFields_32_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("weekyears", buddhist.weekyears().getName());
    }

    public void testDateFields_33_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("weeks", buddhist.weeks().getName());
    }

    public void testDateFields_34_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("Days", buddhist.days().getName());
    }

    public void testDateFields_35_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("Days", buddhist.days().getName());
    }

    public void testDateFields_36_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        assertEquals("Days", buddhist.days().getName());
    }

    public void testDateFields_37_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertEquals("era", buddhist.era().getName());
    }

    public void testDateFields_38_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertEquals("eras", buddhist.eras().getName());
    }

    public void testDateFields_39_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertEquals("centuries", buddhist.centuries().getName());
    }

    public void testDateFields_40_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertEquals("eras", buddhist.eras().getName());
    }

    public void testDateFields_41_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertEquals("year", buddhist.year().getName());
    }

    public void testDateFields_42_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertEquals("Years", buddhist.years().getName());
    }

    public void testDateFields_43_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertEquals("centuries", buddhist.centuries().getName());
    }

    public void testDateFields_44_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertNotNull(buddhist.weekyear());
    }

    public void testDateFields_45_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertEquals("weekyears", buddhist.weekyears().getName());
    }

    public void testDateFields_46_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertEquals("YEARS", buddhist.years().getName());
    }

    public void testDateFields_47_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertNotNull(buddhist);
    }

    public void testDateFields_48_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        
        
        assertEquals("weeks", buddhist.weeks().getName());
    }

    public void testTimeFields_1_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("halfdayOfDay", buddhist.halfdayOfDay().getName());
    }

    public void testTimeFields_2_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("clockhour_of_halfday", buddhist.clockhourOfHalfday().getName());
    }

    public void testTimeFields_3_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("hourOfHalfday", buddhist.hourOfHalfday().getName());
    }

    public void testTimeFields_4_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("clockhourOfDay", buddhist.clockhourOfDay().getName());
    }

    public void testTimeFields_5_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("Property[name=hourOfDay,type=int]", buddhist.hourOfDay().toString());
    }

    public void testTimeFields_6_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("MinuteOfDay", buddhist.minuteOfDay().getName());
    }

    public void testTimeFields_7_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("MinuteOfHour", buddhist.minuteOfHour().getName());
    }

    public void testTimeFields_8_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("secondOfDay", buddhist.secondOfDay().getName());
    }

    public void testTimeFields_9_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("secondOfMinute", buddhist.secondOfMinute().getName());
    }

    public void testTimeFields_10_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("millis of day", buddhist.millisOfDay().getName());
    }

    public void testTimeFields_11_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        assertEquals("millis of second", buddhist.millisOfSecond().getName());
    }

    public void testTimeFields_12_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("halfdayOfDay", buddhist.halfdayOfDay().getName());
    }

    public void testTimeFields_13_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("clockhour_of_halfday", buddhist.clockhourOfHalfday().getName());
    }

    public void testTimeFields_14_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("hourOfHalfday", buddhist.hourOfHalfday().getName());
    }

    public void testTimeFields_15_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("clockhourOfDay", buddhist.clockhourOfDay().getName());
    }

    public void testTimeFields_16_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("Property[name=hourOfDay,type=int]", buddhist.hourOfDay().toString());
    }

    public void testTimeFields_17_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("MinuteOfDay", buddhist.minuteOfDay().getName());
    }

    public void testTimeFields_18_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("MinuteOfHour", buddhist.minuteOfHour().getName());
    }

    public void testTimeFields_19_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("secondOfDay", buddhist.secondOfDay().getName());
    }

    public void testTimeFields_20_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertNotNull(buddhist.secondOfMinute());
    }

    public void testTimeFields_21_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("millis of day", buddhist.millisOfDay().getName());
    }

    public void testTimeFields_22_oe() {
        final BuddhistChronology buddhist = BuddhistChronology.getInstance();
        
        assertEquals("millisOfSecond", buddhist.millisOfSecond().getName());
    }

    public void testEpoch_1_oe() {
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
// incorrect assertion         assertNotNull(epoch.withChronology());
    }

    public void testEra_1_oe() {
        int a = 1;
        assertEquals(1, a);
    }

    public void testKeyYears_1_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertNotNull(bd);
    }

    public void testKeyYears_2_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(2513, bd.getYear());
    }

    public void testKeyYears_3_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(1, bd.getYearOfEra());
    }

    public void testKeyYears_4_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertNotNull(bd);
    }

    public void testKeyYears_5_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertNotSame(bd, jd);
    }

    public void testKeyYears_6_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(2126, bd.getYear());
    }

    public void testKeyYears_7_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(2126, bd.getYearOfEra());
    }

    public void testKeyYears_8_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertNotNull(bd);
    }

    public void testKeyYears_9_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertNotSame(bd, jd);
    }

    public void testKeyYears_10_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(2125, bd.getYear());
    }

    public void testKeyYears_11_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(1, bd.getYearOfEra());
    }

    public void testKeyYears_12_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertNotNull(bd);
    }

    public void testKeyYears_13_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertNotSame(bd, jd);
    }

    public void testKeyYears_14_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(544, bd.getYear());
    }

    public void testKeyYears_15_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(1, bd.getYearOfEra());
    }

    public void testKeyYears_16_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertNotNull(bd);
    }

    public void testKeyYears_17_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(543, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(-1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertNotSame(bd, jd);
    }

    public void testKeyYears_18_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(543, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(-1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(543, bd.getYear());
    }

    public void testKeyYears_19_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(543, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(-1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(1, bd.getYearOfEra());
    }

    public void testKeyYears_20_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(543, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(-1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertNotNull(bd);
    }

    public void testKeyYears_22_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(543, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(-1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(1, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(-543, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(1, bd.getYear());
    }

    public void testKeyYears_23_oe() {
        DateTime bd = new DateTime(2513, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        DateTime jd = new DateTime(1970, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2126, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1583, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(2125, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1582, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(544, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(543, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(-1, 1, 1, 0, 0, 0, 0, GJ_UTC);
        
        bd = new DateTime(1, 1, 1, 0, 0, 0, 0, BUDDHIST_UTC);
        jd = new DateTime(-543, 1, 1, 0, 0, 0, 0, GJ_UTC);
        assertEquals(1, bd.getYearOfEra());
    }

}
