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
import org.joda.time.DurationFieldType;
import org.joda.time.DateTime.Property;

/**
 * This class is a Junit unit test for IslamicChronology.
 *
 * @author Stephen Colebourne
 */
public class TestIslamicChronology_OE25Dev extends TestCase {

    private static long SKIP = 1 * DateTimeConstants.MILLIS_PER_DAY;

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final Chronology ISLAMIC_UTC = IslamicChronology.getInstanceUTC();
    private static final Chronology JULIAN_UTC = JulianChronology.getInstanceUTC();
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
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        SKIP = 1 * DateTimeConstants.MILLIS_PER_DAY;
        return new TestSuite(TestIslamicChronology_OE25Dev.class);
    }

    public TestIslamicChronology_OE25Dev(String name) {
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
        assertEquals(DateTimeZone.UTC,IslamicChronology.getInstanceUTC().getZone());
        assertSame(IslamicChronology.class,IslamicChronology.getInstanceUTC().getClass());
    }

    public void testFactory() {
        assertEquals(LONDON,IslamicChronology.getInstance().getZone());
        assertSame(IslamicChronology.class,IslamicChronology.getInstance().getClass());
    }

    public void testFactory_Zone() {
        assertEquals(TOKYO,IslamicChronology.getInstance(TOKYO).getZone());
        assertEquals(PARIS,IslamicChronology.getInstance(PARIS).getZone());
        assertEquals(LONDON,IslamicChronology.getInstance(null).getZone());
        assertSame(IslamicChronology.class,IslamicChronology.getInstance(TOKYO).getClass());
    }

    //-----------------------------------------------------------------------
    public void testEquality() {
        assertSame(IslamicChronology.getInstance(TOKYO),IslamicChronology.getInstance(TOKYO));
        assertSame(IslamicChronology.getInstance(LONDON),IslamicChronology.getInstance(LONDON));
        assertSame(IslamicChronology.getInstance(PARIS),IslamicChronology.getInstance(PARIS));
        assertSame(IslamicChronology.getInstanceUTC(),IslamicChronology.getInstanceUTC());
        assertSame(IslamicChronology.getInstance(),IslamicChronology.getInstance(LONDON));
    }

    public void testWithUTC() {
        assertSame(IslamicChronology.getInstanceUTC(),IslamicChronology.getInstance(LONDON).withUTC());
        assertSame(IslamicChronology.getInstanceUTC(),IslamicChronology.getInstance(TOKYO).withUTC());
        assertSame(IslamicChronology.getInstanceUTC(),IslamicChronology.getInstanceUTC().withUTC());
        assertSame(IslamicChronology.getInstanceUTC(),IslamicChronology.getInstance().withUTC());
    }

    public void testWithZone() {
        assertSame(IslamicChronology.getInstance(TOKYO),IslamicChronology.getInstance(TOKYO).withZone(TOKYO));
        assertSame(IslamicChronology.getInstance(LONDON),IslamicChronology.getInstance(TOKYO).withZone(LONDON));
        assertSame(IslamicChronology.getInstance(PARIS),IslamicChronology.getInstance(TOKYO).withZone(PARIS));
        assertSame(IslamicChronology.getInstance(LONDON),IslamicChronology.getInstance(TOKYO).withZone(null));
        assertSame(IslamicChronology.getInstance(PARIS),IslamicChronology.getInstance().withZone(PARIS));
        assertSame(IslamicChronology.getInstance(PARIS),IslamicChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString() {
        assertEquals("IslamicChronology[Europe/London]",IslamicChronology.getInstance(LONDON).toString());
        assertEquals("IslamicChronology[Asia/Tokyo]",IslamicChronology.getInstance(TOKYO).toString());
        assertEquals("IslamicChronology[Europe/London]",IslamicChronology.getInstance().toString());
        assertEquals("IslamicChronology[UTC]",IslamicChronology.getInstanceUTC().toString());
    }

    //-----------------------------------------------------------------------
    public void testDurationFields() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("eras",islamic.eras().getName());
        assertEquals("centuries",islamic.centuries().getName());
        assertEquals("years",islamic.years().getName());
        assertEquals("weekyears",islamic.weekyears().getName());
        assertEquals("months",islamic.months().getName());
        assertEquals("weeks",islamic.weeks().getName());
        assertEquals("days",islamic.days().getName());
        assertEquals("halfdays",islamic.halfdays().getName());
        assertEquals("hours",islamic.hours().getName());
        assertEquals("minutes",islamic.minutes().getName());
        assertEquals("seconds",islamic.seconds().getName());
        assertEquals("millis",islamic.millis().getName());
        
        assertEquals(false,islamic.eras().isSupported());
        assertEquals(true,islamic.centuries().isSupported());
        assertEquals(true,islamic.years().isSupported());
        assertEquals(true,islamic.weekyears().isSupported());
        assertEquals(true,islamic.months().isSupported());
        assertEquals(true,islamic.weeks().isSupported());
        assertEquals(true,islamic.days().isSupported());
        assertEquals(true,islamic.halfdays().isSupported());
        assertEquals(true,islamic.hours().isSupported());
        assertEquals(true,islamic.minutes().isSupported());
        assertEquals(true,islamic.seconds().isSupported());
        assertEquals(true,islamic.millis().isSupported());
        
        assertEquals(false,islamic.centuries().isPrecise());
        assertEquals(false,islamic.years().isPrecise());
        assertEquals(false,islamic.weekyears().isPrecise());
        assertEquals(false,islamic.months().isPrecise());
        assertEquals(false,islamic.weeks().isPrecise());
        assertEquals(false,islamic.days().isPrecise());
        assertEquals(false,islamic.halfdays().isPrecise());
        assertEquals(true,islamic.hours().isPrecise());
        assertEquals(true,islamic.minutes().isPrecise());
        assertEquals(true,islamic.seconds().isPrecise());
        assertEquals(true,islamic.millis().isPrecise());
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(false,islamicUTC.centuries().isPrecise());
        assertEquals(false,islamicUTC.years().isPrecise());
        assertEquals(false,islamicUTC.weekyears().isPrecise());
        assertEquals(false,islamicUTC.months().isPrecise());
        assertEquals(true,islamicUTC.weeks().isPrecise());
        assertEquals(true,islamicUTC.days().isPrecise());
        assertEquals(true,islamicUTC.halfdays().isPrecise());
        assertEquals(true,islamicUTC.hours().isPrecise());
        assertEquals(true,islamicUTC.minutes().isPrecise());
        assertEquals(true,islamicUTC.seconds().isPrecise());
        assertEquals(true,islamicUTC.millis().isPrecise());
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(false,islamicGMT.centuries().isPrecise());
        assertEquals(false,islamicGMT.years().isPrecise());
        assertEquals(false,islamicGMT.weekyears().isPrecise());
        assertEquals(false,islamicGMT.months().isPrecise());
        assertEquals(true,islamicGMT.weeks().isPrecise());
        assertEquals(true,islamicGMT.days().isPrecise());
        assertEquals(true,islamicGMT.halfdays().isPrecise());
        assertEquals(true,islamicGMT.hours().isPrecise());
        assertEquals(true,islamicGMT.minutes().isPrecise());
        assertEquals(true,islamicGMT.seconds().isPrecise());
        assertEquals(true,islamicGMT.millis().isPrecise());
    }

    public void testDateFields() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("era",islamic.era().getName());
        assertEquals("centuryOfEra",islamic.centuryOfEra().getName());
        assertEquals("yearOfCentury",islamic.yearOfCentury().getName());
        assertEquals("yearOfEra",islamic.yearOfEra().getName());
        assertEquals("year",islamic.year().getName());
        assertEquals("monthOfYear",islamic.monthOfYear().getName());
        assertEquals("weekyearOfCentury",islamic.weekyearOfCentury().getName());
        assertEquals("weekyear",islamic.weekyear().getName());
        assertEquals("weekOfWeekyear",islamic.weekOfWeekyear().getName());
        assertEquals("dayOfYear",islamic.dayOfYear().getName());
        assertEquals("dayOfMonth",islamic.dayOfMonth().getName());
        assertEquals("dayOfWeek",islamic.dayOfWeek().getName());
        
        assertEquals(true,islamic.era().isSupported());
        assertEquals(true,islamic.centuryOfEra().isSupported());
        assertEquals(true,islamic.yearOfCentury().isSupported());
        assertEquals(true,islamic.yearOfEra().isSupported());
        assertEquals(true,islamic.year().isSupported());
        assertEquals(true,islamic.monthOfYear().isSupported());
        assertEquals(true,islamic.weekyearOfCentury().isSupported());
        assertEquals(true,islamic.weekyear().isSupported());
        assertEquals(true,islamic.weekOfWeekyear().isSupported());
        assertEquals(true,islamic.dayOfYear().isSupported());
        assertEquals(true,islamic.dayOfMonth().isSupported());
        assertEquals(true,islamic.dayOfWeek().isSupported());
        
        assertEquals(islamic.eras(),islamic.era().getDurationField());
        assertEquals(islamic.centuries(),islamic.centuryOfEra().getDurationField());
        assertEquals(islamic.years(),islamic.yearOfCentury().getDurationField());
        assertEquals(islamic.years(),islamic.yearOfEra().getDurationField());
        assertEquals(islamic.years(),islamic.year().getDurationField());
        assertEquals(islamic.months(),islamic.monthOfYear().getDurationField());
        assertEquals(islamic.weekyears(),islamic.weekyearOfCentury().getDurationField());
        assertEquals(islamic.weekyears(),islamic.weekyear().getDurationField());
        assertEquals(islamic.weeks(),islamic.weekOfWeekyear().getDurationField());
        assertEquals(islamic.days(),islamic.dayOfYear().getDurationField());
        assertEquals(islamic.days(),islamic.dayOfMonth().getDurationField());
        assertEquals(islamic.days(),islamic.dayOfWeek().getDurationField());
        
        assertEquals(null,islamic.era().getRangeDurationField());
        assertEquals(islamic.eras(),islamic.centuryOfEra().getRangeDurationField());
        assertEquals(islamic.centuries(),islamic.yearOfCentury().getRangeDurationField());
        assertEquals(islamic.eras(),islamic.yearOfEra().getRangeDurationField());
        assertEquals(null,islamic.year().getRangeDurationField());
        assertEquals(islamic.years(),islamic.monthOfYear().getRangeDurationField());
        assertEquals(islamic.centuries(),islamic.weekyearOfCentury().getRangeDurationField());
        assertEquals(null,islamic.weekyear().getRangeDurationField());
        assertEquals(islamic.weekyears(),islamic.weekOfWeekyear().getRangeDurationField());
        assertEquals(islamic.years(),islamic.dayOfYear().getRangeDurationField());
        assertEquals(islamic.months(),islamic.dayOfMonth().getRangeDurationField());
        assertEquals(islamic.weeks(),islamic.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("halfdayOfDay",islamic.halfdayOfDay().getName());
        assertEquals("clockhourOfHalfday",islamic.clockhourOfHalfday().getName());
        assertEquals("hourOfHalfday",islamic.hourOfHalfday().getName());
        assertEquals("clockhourOfDay",islamic.clockhourOfDay().getName());
        assertEquals("hourOfDay",islamic.hourOfDay().getName());
        assertEquals("minuteOfDay",islamic.minuteOfDay().getName());
        assertEquals("minuteOfHour",islamic.minuteOfHour().getName());
        assertEquals("secondOfDay",islamic.secondOfDay().getName());
        assertEquals("secondOfMinute",islamic.secondOfMinute().getName());
        assertEquals("millisOfDay",islamic.millisOfDay().getName());
        assertEquals("millisOfSecond",islamic.millisOfSecond().getName());
        
        assertEquals(true,islamic.halfdayOfDay().isSupported());
        assertEquals(true,islamic.clockhourOfHalfday().isSupported());
        assertEquals(true,islamic.hourOfHalfday().isSupported());
        assertEquals(true,islamic.clockhourOfDay().isSupported());
        assertEquals(true,islamic.hourOfDay().isSupported());
        assertEquals(true,islamic.minuteOfDay().isSupported());
        assertEquals(true,islamic.minuteOfHour().isSupported());
        assertEquals(true,islamic.secondOfDay().isSupported());
        assertEquals(true,islamic.secondOfMinute().isSupported());
        assertEquals(true,islamic.millisOfDay().isSupported());
        assertEquals(true,islamic.millisOfSecond().isSupported());
    }

    //-----------------------------------------------------------------------
    public void testEpoch() {
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, ISLAMIC_UTC);
        DateTime expectedEpoch = new DateTime(622, 7, 16, 0, 0, 0, 0, JULIAN_UTC);
        assertEquals(expectedEpoch.getMillis(),epoch.getMillis());
    }

    public void testEra() {
        assertEquals(1,IslamicChronology.AH);
        try {
            new DateTime(-1, 13, 5, 0, 0, 0, 0, ISLAMIC_UTC);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testFieldConstructor() {
        DateTime date = new DateTime(1364, 12, 6, 0, 0, 0, 0, ISLAMIC_UTC);
        DateTime expectedDate = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        assertEquals(expectedDate.getMillis(),date.getMillis());
    }

    //-----------------------------------------------------------------------
    /**
     * Tests era, year, monthOfYear, dayOfMonth and dayOfWeek.
     */
    public void DISABLED_testCalendar() {
        if (TestAll.FAST) {
            return;
        }
        System.out.println("\nTestIslamicChronology.testCalendar");
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, ISLAMIC_UTC);
        long millis = epoch.getMillis();
        long end = new DateTime(3000, 1, 1, 0, 0, 0, 0, ISO_UTC).getMillis();
        DateTimeField dayOfWeek = ISLAMIC_UTC.dayOfWeek();
        DateTimeField dayOfYear = ISLAMIC_UTC.dayOfYear();
        DateTimeField dayOfMonth = ISLAMIC_UTC.dayOfMonth();
        DateTimeField monthOfYear = ISLAMIC_UTC.monthOfYear();
        DateTimeField year = ISLAMIC_UTC.year();
        DateTimeField yearOfEra = ISLAMIC_UTC.yearOfEra();
        DateTimeField era = ISLAMIC_UTC.era();
        int expectedDOW = new DateTime(622, 7, 16, 0, 0, 0, 0, JULIAN_UTC).getDayOfWeek();
        int expectedDOY = 1;
        int expectedDay = 1;
        int expectedMonth = 1;
        int expectedYear = 1;
        while (millis < end) {
            int dowValue = dayOfWeek.get(millis);
            int doyValue = dayOfYear.get(millis);
            int dayValue = dayOfMonth.get(millis);
            int monthValue = monthOfYear.get(millis);
            int yearValue = year.get(millis);
            int yearOfEraValue = yearOfEra.get(millis);
            int dayOfYearLen = dayOfYear.getMaximumValue(millis);
            int monthLen = dayOfMonth.getMaximumValue(millis);
            if (monthValue < 1 || monthValue > 12) {
                fail("Bad month: " + millis);
            }
            
            // test era
            assertEquals(1,era.get(millis));
            assertEquals("AH",era.getAsText(millis));
            assertEquals("AH",era.getAsShortText(millis));
            
            // test date
            assertEquals(expectedDOY,doyValue);
            assertEquals(expectedMonth,monthValue);
            assertEquals(expectedDay,dayValue);
            assertEquals(expectedDOW,dowValue);
            assertEquals(expectedYear,yearValue);
            assertEquals(expectedYear,yearOfEraValue);
            
            // test leap year
            boolean leap = ((11 * yearValue + 14) % 30) < 11;
            assertEquals(leap,year.isLeap(millis));
            
            // test month length
            switch (monthValue) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 9:
                case 11:
                    assertEquals(30,monthLen);
                    break;
                case 2:
                case 4:
                case 6:
                case 8:
                case 10:
                    assertEquals(29,monthLen);
                    break;
                case 12:
                    assertEquals((leap ? 30 : 29),monthLen);
                    break;
            }
            
            // test year length
            assertEquals((leap ? 355 : 354),dayOfYearLen);
            
            // recalculate date
            expectedDOW = (((expectedDOW + 1) - 1) % 7) + 1;
            expectedDay++;
            expectedDOY++;
            if (expectedDay > monthLen) {
                expectedDay = 1;
                expectedMonth++;
                if (expectedMonth == 13) {
                    expectedMonth = 1;
                    expectedDOY = 1;
                    expectedYear++;
                }
            }
            millis += SKIP;
        }
    }

    public void testSampleDate1() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH,dt.getEra());
        assertEquals(14,dt.getCenturyOfEra());// TODO confirm assertEquals(64,dt.getYearOfCentury());
        assertEquals(1364,dt.getYearOfEra());
        
        assertEquals(1364,dt.getYear());
        Property fld = dt.year();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
        assertEquals(new DateTime(1365,12,6,0,0,0,0,ISLAMIC_UTC),fld.addToCopy(1));
        
        assertEquals(12,dt.getMonthOfYear());
        fld = dt.monthOfYear();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(12,fld.getMaximumValue());
        assertEquals(12,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1365,1,6,0,0,0,0,ISLAMIC_UTC),fld.addToCopy(1));
        assertEquals(new DateTime(1364,1,6,0,0,0,0,ISLAMIC_UTC),fld.addWrapFieldToCopy(1));
        
        assertEquals(6,dt.getDayOfMonth());
        fld = dt.dayOfMonth();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(29,fld.getMaximumValue());
        assertEquals(30,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1364,12,7,0,0,0,0,ISLAMIC_UTC),fld.addToCopy(1));
        
        assertEquals(DateTimeConstants.MONDAY,dt.getDayOfWeek());
        fld = dt.dayOfWeek();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(7,fld.getMaximumValue());
        assertEquals(7,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1364,12,7,0,0,0,0,ISLAMIC_UTC),fld.addToCopy(1));
        
        assertEquals(6 * 30 + 5 * 29 + 6,dt.getDayOfYear());
        fld = dt.dayOfYear();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(354,fld.getMaximumValue());
        assertEquals(355,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1364,12,7,0,0,0,0,ISLAMIC_UTC),fld.addToCopy(1));
        
        assertEquals(0,dt.getHourOfDay());
        assertEquals(0,dt.getMinuteOfHour());
        assertEquals(0,dt.getSecondOfMinute());
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testSampleDate2() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH,dt.getEra());
        assertEquals(15,dt.getCenturyOfEra());// TODO confirm assertEquals(26,dt.getYearOfCentury());
        assertEquals(1426,dt.getYearOfEra());
        
        assertEquals(1426,dt.getYear());
        Property fld = dt.year();
        assertEquals(true,fld.isLeap());
        assertEquals(1,fld.getLeapAmount());
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
        
        assertEquals(10,dt.getMonthOfYear());
        fld = dt.monthOfYear();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(12,fld.getMaximumValue());
        assertEquals(12,fld.getMaximumValueOverall());
        
        assertEquals(24,dt.getDayOfMonth());
        fld = dt.dayOfMonth();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(29,fld.getMaximumValue());
        assertEquals(30,fld.getMaximumValueOverall());
        
        assertEquals(DateTimeConstants.SATURDAY,dt.getDayOfWeek());
        fld = dt.dayOfWeek();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(7,fld.getMaximumValue());
        assertEquals(7,fld.getMaximumValueOverall());
        
        assertEquals(5 * 30 + 4 * 29 + 24,dt.getDayOfYear());
        fld = dt.dayOfYear();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(355,fld.getMaximumValue());
        assertEquals(355,fld.getMaximumValueOverall());
        
        assertEquals(0,dt.getHourOfDay());
        assertEquals(0,dt.getMinuteOfHour());
        assertEquals(0,dt.getSecondOfMinute());
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testSampleDate3() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH,dt.getEra());
        
        assertEquals(1426,dt.getYear());
        Property fld = dt.year();
        assertEquals(true,fld.isLeap());
        assertEquals(1,fld.getLeapAmount());
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
        
        assertEquals(12,dt.getMonthOfYear());
        fld = dt.monthOfYear();
        assertEquals(true,fld.isLeap());
        assertEquals(1,fld.getLeapAmount());
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(12,fld.getMaximumValue());
        assertEquals(12,fld.getMaximumValueOverall());
        
        assertEquals(24,dt.getDayOfMonth());
        fld = dt.dayOfMonth();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(30,fld.getMaximumValue());
        assertEquals(30,fld.getMaximumValueOverall());
        
        assertEquals(DateTimeConstants.TUESDAY,dt.getDayOfWeek());
        fld = dt.dayOfWeek();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(7,fld.getMaximumValue());
        assertEquals(7,fld.getMaximumValueOverall());
        
        assertEquals(6 * 30 + 5 * 29 + 24,dt.getDayOfYear());
        fld = dt.dayOfYear();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(355,fld.getMaximumValue());
        assertEquals(355,fld.getMaximumValueOverall());
        
        assertEquals(0,dt.getHourOfDay());
        assertEquals(0,dt.getMinuteOfHour());
        assertEquals(0,dt.getSecondOfMinute());
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testSampleDateWithZone() {
        DateTime dt = new DateTime(2005, 11, 26, 12, 0, 0, 0, PARIS).withChronology(ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH,dt.getEra());
        assertEquals(1426,dt.getYear());
        assertEquals(10,dt.getMonthOfYear());
        assertEquals(24,dt.getDayOfMonth());
        assertEquals(11,dt.getHourOfDay());// PARIS is UTC+1 in summer(12-1=11)assertEquals(0,dt.getMinuteOfHour());
        assertEquals(0,dt.getSecondOfMinute());
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void test15BasedLeapYear() {
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(1));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(2));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(3));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(4));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(5));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(6));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(7));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(8));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(9));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(10));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(11));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(12));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(13));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(14));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(15));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(16));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(17));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(18));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(19));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(20));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(21));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(22));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(23));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(24));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(25));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(26));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(27));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(28));
        assertEquals(true,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(29));
        assertEquals(false,IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(30));
    }

    public void test16BasedLeapYear() {
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(1));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(2));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(3));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(4));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(5));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(6));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(7));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(8));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(9));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(10));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(11));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(12));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(13));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(14));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(15));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(16));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(17));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(18));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(19));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(20));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(21));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(22));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(23));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(24));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(25));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(26));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(27));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(28));
        assertEquals(true,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(29));
        assertEquals(false,IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(30));
    }

    public void testIndianBasedLeapYear() {
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(1));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(2));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(3));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(4));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(5));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(6));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(7));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(8));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(9));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(10));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(11));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(12));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(13));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(14));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(15));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(16));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(17));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(18));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(19));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(20));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(21));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(22));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(23));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(24));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(25));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(26));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(27));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(28));
        assertEquals(true,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(29));
        assertEquals(false,IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(30));
    }

    public void testHabashAlHasibBasedLeapYear() {
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(1));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(2));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(3));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(4));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(5));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(6));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(7));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(8));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(9));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(10));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(11));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(12));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(13));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(14));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(15));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(16));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(17));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(18));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(19));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(20));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(21));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(22));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(23));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(24));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(25));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(26));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(27));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(28));
        assertEquals(false,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(29));
        assertEquals(true,IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(30));
    }

    public void testFactoryUTC_1_oe() {
        Object a = DateTimeZone.UTC;
        assertEquals(a, IslamicChronology.getInstanceUTC().getZone());
    }

    public void testFactoryUTC_2_oe() {
        Object a = IslamicChronology.class;
        assertSame(a, IslamicChronology.getInstanceUTC().getClass());
    }

    public void testFactory_1_oe() {
        Object a = LONDON;
        assertEquals(a, IslamicChronology.getInstance().getZone());
    }

    public void testFactory_2_oe() {
        Object a = IslamicChronology.class;
        assertSame(a, IslamicChronology.getInstance().getClass());
    }

    public void testFactory_Zone_1_oe() {
        Object a = TOKYO;
        assertEquals(a, IslamicChronology.getInstance(TOKYO).getZone());
    }

    public void testFactory_Zone_2_oe() {
        Object a = PARIS;
        assertEquals(a, IslamicChronology.getInstance(PARIS).getZone());
    }

    public void testFactory_Zone_3_oe() {
        Object a = LONDON;
        assertEquals(a, IslamicChronology.getInstance(null).getZone());
    }

    public void testFactory_Zone_4_oe() {
        Object a = IslamicChronology.class;
        assertSame(a, IslamicChronology.getInstance(TOKYO).getClass());
    }

    public void testEquality_1_oe() {
        Object a = IslamicChronology.getInstance(TOKYO);
        assertSame(a, IslamicChronology.getInstance(TOKYO));
    }

    public void testEquality_2_oe() {
        Object a = IslamicChronology.getInstance(LONDON);
        assertSame(a, IslamicChronology.getInstance(LONDON));
    }

    public void testEquality_3_oe() {
        Object a = IslamicChronology.getInstance(PARIS);
        assertSame(a, IslamicChronology.getInstance(PARIS));
    }

    public void testEquality_4_oe() {
        Object a = IslamicChronology.getInstanceUTC();
        assertSame(a, IslamicChronology.getInstanceUTC());
    }

    public void testEquality_5_oe() {
        Object a = IslamicChronology.getInstance();
        assertSame(a, IslamicChronology.getInstance(LONDON));
    }

    public void testWithUTC_1_oe() {
        Object a = IslamicChronology.getInstanceUTC();
        assertSame(a, IslamicChronology.getInstance(LONDON).withUTC());
    }

    public void testWithUTC_2_oe() {
        Object a = IslamicChronology.getInstanceUTC();
        assertSame(a, IslamicChronology.getInstance(TOKYO).withUTC());
    }

    public void testWithUTC_3_oe() {
        Object a = IslamicChronology.getInstanceUTC();
        assertSame(a, IslamicChronology.getInstanceUTC().withUTC());
    }

    public void testWithUTC_4_oe() {
        Object a = IslamicChronology.getInstanceUTC();
        assertSame(a, IslamicChronology.getInstance().withUTC());
    }

    public void testWithZone_1_oe() {
        Object a = IslamicChronology.getInstance(TOKYO);
        assertSame(a, IslamicChronology.getInstance(TOKYO).withZone(TOKYO));
    }

    public void testWithZone_2_oe() {
        Object a = IslamicChronology.getInstance(LONDON);
        assertSame(a, IslamicChronology.getInstance(TOKYO).withZone(LONDON));
    }

    public void testWithZone_3_oe() {
        Object a = IslamicChronology.getInstance(PARIS);
        assertSame(a, IslamicChronology.getInstance(TOKYO).withZone(PARIS));
    }

    public void testWithZone_4_oe() {
        Object a = IslamicChronology.getInstance(LONDON);
        assertSame(a, IslamicChronology.getInstance(TOKYO).withZone(null));
    }

    public void testWithZone_5_oe() {
        Object a = IslamicChronology.getInstance(PARIS);
        assertSame(a, IslamicChronology.getInstance().withZone(PARIS));
    }

    public void testWithZone_6_oe() {
        Object a = IslamicChronology.getInstance(PARIS);
        assertSame(a, IslamicChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString_1_oe() {
        Object a = IslamicChronology.getInstance(LONDON).toString();
        assertEquals("IslamicChronology[Europe/London]", a);
    }

    public void testToString_2_oe() {
        Object a = IslamicChronology.getInstance(TOKYO).toString();
        assertEquals("IslamicChronology[Asia/Tokyo]", a);
    }

    public void testToString_3_oe() {
        Object a = IslamicChronology.getInstance().toString();
        assertEquals("IslamicChronology[Europe/London]", a);
    }

    public void testToString_4_oe() {
        Object a = IslamicChronology.getInstanceUTC().toString();
        assertEquals("IslamicChronology[UTC]", a);
    }

    public void testDurationFields_1_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("eras",islamic.eras().getName());
    }

    public void testDurationFields_2_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("centuries",islamic.centuries().getName());
    }

    public void testDurationFields_3_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("years",islamic.years().getName());
    }

    public void testDurationFields_4_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("weekyears",islamic.weekyears().getName());
    }

    public void testDurationFields_5_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("months",islamic.months().getName());
    }

    public void testDurationFields_6_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("weeks",islamic.weeks().getName());
    }

    public void testDurationFields_7_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("days",islamic.days().getName());
    }

    public void testDurationFields_8_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("halfdays",islamic.halfdays().getName());
    }

    public void testDurationFields_9_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("hours",islamic.hours().getName());
    }

    public void testDurationFields_10_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("minutes",islamic.minutes().getName());
    }

    public void testDurationFields_11_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("seconds",islamic.seconds().getName());
    }

    public void testDurationFields_12_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("millis",islamic.millis().getName());
    }

    public void testDurationFields_13_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(false,islamic.eras().isSupported());
    }

    public void testDurationFields_14_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.centuries().isSupported());
    }

    public void testDurationFields_15_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.years().isSupported());
    }

    public void testDurationFields_16_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.weekyears().isSupported());
    }

    public void testDurationFields_17_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.months().isSupported());
    }

    public void testDurationFields_18_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.weeks().isSupported());
    }

    public void testDurationFields_19_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.days().isSupported());
    }

    public void testDurationFields_20_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.halfdays().isSupported());
    }

    public void testDurationFields_21_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.hours().isSupported());
    }

    public void testDurationFields_22_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.minutes().isSupported());
    }

    public void testDurationFields_23_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.seconds().isSupported());
    }

    public void testDurationFields_24_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.millis().isSupported());
    }

    public void testDurationFields_25_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(false,islamic.centuries().isPrecise());
    }

    public void testDurationFields_26_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(false,islamic.years().isPrecise());
    }

    public void testDurationFields_27_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(false,islamic.weekyears().isPrecise());
    }

    public void testDurationFields_28_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(false,islamic.months().isPrecise());
    }

    public void testDurationFields_29_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(false,islamic.weeks().isPrecise());
    }

    public void testDurationFields_30_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(false,islamic.days().isPrecise());
    }

    public void testDurationFields_31_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(false,islamic.halfdays().isPrecise());
    }

    public void testDurationFields_32_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(true,islamic.hours().isPrecise());
    }

    public void testDurationFields_33_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(true,islamic.minutes().isPrecise());
    }

    public void testDurationFields_34_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(true,islamic.seconds().isPrecise());
    }

    public void testDurationFields_35_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(true,islamic.millis().isPrecise());
    }

    public void testDurationFields_36_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(false,islamicUTC.centuries().isPrecise());
    }

    public void testDurationFields_37_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(false,islamicUTC.years().isPrecise());
    }

    public void testDurationFields_38_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(false,islamicUTC.weekyears().isPrecise());
    }

    public void testDurationFields_39_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(false,islamicUTC.months().isPrecise());
    }

    public void testDurationFields_40_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(true,islamicUTC.weeks().isPrecise());
    }

    public void testDurationFields_41_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(true,islamicUTC.days().isPrecise());
    }

    public void testDurationFields_42_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(true,islamicUTC.halfdays().isPrecise());
    }

    public void testDurationFields_43_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(true,islamicUTC.hours().isPrecise());
    }

    public void testDurationFields_44_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(true,islamicUTC.minutes().isPrecise());
    }

    public void testDurationFields_45_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(true,islamicUTC.seconds().isPrecise());
    }

    public void testDurationFields_46_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(true,islamicUTC.millis().isPrecise());
    }

    public void testDurationFields_47_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(false,islamicGMT.centuries().isPrecise());
    }

    public void testDurationFields_48_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(false,islamicGMT.years().isPrecise());
    }

    public void testDurationFields_49_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(false,islamicGMT.weekyears().isPrecise());
    }

    public void testDurationFields_50_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(false,islamicGMT.months().isPrecise());
    }

    public void testDurationFields_51_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(true,islamicGMT.weeks().isPrecise());
    }

    public void testDurationFields_52_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(true,islamicGMT.days().isPrecise());
    }

    public void testDurationFields_53_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(true,islamicGMT.halfdays().isPrecise());
    }

    public void testDurationFields_54_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(true,islamicGMT.hours().isPrecise());
    }

    public void testDurationFields_55_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(true,islamicGMT.minutes().isPrecise());
    }

    public void testDurationFields_56_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(true,islamicGMT.seconds().isPrecise());
    }

    public void testDurationFields_57_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(true,islamicGMT.millis().isPrecise());
    }

    public void testDateFields_1_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("era",islamic.era().getName());
    }

    public void testDateFields_2_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("centuryOfEra",islamic.centuryOfEra().getName());
    }

    public void testDateFields_3_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("yearOfCentury",islamic.yearOfCentury().getName());
    }

    public void testDateFields_4_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("yearOfEra",islamic.yearOfEra().getName());
    }

    public void testDateFields_5_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("year",islamic.year().getName());
    }

    public void testDateFields_6_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("monthOfYear",islamic.monthOfYear().getName());
    }

    public void testDateFields_7_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("weekyearOfCentury",islamic.weekyearOfCentury().getName());
    }

    public void testDateFields_8_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("weekyear",islamic.weekyear().getName());
    }

    public void testDateFields_9_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("weekOfWeekyear",islamic.weekOfWeekyear().getName());
    }

    public void testDateFields_10_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("dayOfYear",islamic.dayOfYear().getName());
    }

    public void testDateFields_11_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("dayOfMonth",islamic.dayOfMonth().getName());
    }

    public void testDateFields_12_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("dayOfWeek",islamic.dayOfWeek().getName());
    }

    public void testDateFields_13_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.era().isSupported());
    }

    public void testDateFields_14_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.centuryOfEra().isSupported());
    }

    public void testDateFields_15_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.yearOfCentury().isSupported());
    }

    public void testDateFields_16_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.yearOfEra().isSupported());
    }

    public void testDateFields_17_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.year().isSupported());
    }

    public void testDateFields_18_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.monthOfYear().isSupported());
    }

    public void testDateFields_19_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.weekyearOfCentury().isSupported());
    }

    public void testDateFields_20_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.weekyear().isSupported());
    }

    public void testDateFields_21_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.weekOfWeekyear().isSupported());
    }

    public void testDateFields_22_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.dayOfYear().isSupported());
    }

    public void testDateFields_23_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.dayOfMonth().isSupported());
    }

    public void testDateFields_24_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.dayOfWeek().isSupported());
    }

    public void testDateFields_25_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.eras(),islamic.era().getDurationField());
    }

    public void testDateFields_26_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.centuries(),islamic.centuryOfEra().getDurationField());
    }

    public void testDateFields_27_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.years(),islamic.yearOfCentury().getDurationField());
    }

    public void testDateFields_28_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.years(),islamic.yearOfEra().getDurationField());
    }

    public void testDateFields_29_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.years(),islamic.year().getDurationField());
    }

    public void testDateFields_30_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.months(),islamic.monthOfYear().getDurationField());
    }

    public void testDateFields_31_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.weekyears(),islamic.weekyearOfCentury().getDurationField());
    }

    public void testDateFields_32_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.weekyears(),islamic.weekyear().getDurationField());
    }

    public void testDateFields_33_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.weeks(),islamic.weekOfWeekyear().getDurationField());
    }

    public void testDateFields_34_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.days(),islamic.dayOfYear().getDurationField());
    }

    public void testDateFields_35_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.days(),islamic.dayOfMonth().getDurationField());
    }

    public void testDateFields_36_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        assertEquals(islamic.days(),islamic.dayOfWeek().getDurationField());
    }

    public void testDateFields_37_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(null,islamic.era().getRangeDurationField());
    }

    public void testDateFields_38_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(islamic.eras(),islamic.centuryOfEra().getRangeDurationField());
    }

    public void testDateFields_39_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(islamic.centuries(),islamic.yearOfCentury().getRangeDurationField());
    }

    public void testDateFields_40_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(islamic.eras(),islamic.yearOfEra().getRangeDurationField());
    }

    public void testDateFields_41_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(null,islamic.year().getRangeDurationField());
    }

    public void testDateFields_42_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(islamic.years(),islamic.monthOfYear().getRangeDurationField());
    }

    public void testDateFields_43_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(islamic.centuries(),islamic.weekyearOfCentury().getRangeDurationField());
    }

    public void testDateFields_44_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(null,islamic.weekyear().getRangeDurationField());
    }

    public void testDateFields_45_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(islamic.weekyears(),islamic.weekOfWeekyear().getRangeDurationField());
    }

    public void testDateFields_46_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(islamic.years(),islamic.dayOfYear().getRangeDurationField());
    }

    public void testDateFields_47_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(islamic.months(),islamic.dayOfMonth().getRangeDurationField());
    }

    public void testDateFields_48_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        
        
        assertEquals(islamic.weeks(),islamic.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields_1_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("halfdayOfDay",islamic.halfdayOfDay().getName());
    }

    public void testTimeFields_2_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("clockhourOfHalfday",islamic.clockhourOfHalfday().getName());
    }

    public void testTimeFields_3_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("hourOfHalfday",islamic.hourOfHalfday().getName());
    }

    public void testTimeFields_4_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("clockhourOfDay",islamic.clockhourOfDay().getName());
    }

    public void testTimeFields_5_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("hourOfDay",islamic.hourOfDay().getName());
    }

    public void testTimeFields_6_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("minuteOfDay",islamic.minuteOfDay().getName());
    }

    public void testTimeFields_7_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("minuteOfHour",islamic.minuteOfHour().getName());
    }

    public void testTimeFields_8_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("secondOfDay",islamic.secondOfDay().getName());
    }

    public void testTimeFields_9_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("secondOfMinute",islamic.secondOfMinute().getName());
    }

    public void testTimeFields_10_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("millisOfDay",islamic.millisOfDay().getName());
    }

    public void testTimeFields_11_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("millisOfSecond",islamic.millisOfSecond().getName());
    }

    public void testTimeFields_12_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.halfdayOfDay().isSupported());
    }

    public void testTimeFields_13_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.clockhourOfHalfday().isSupported());
    }

    public void testTimeFields_14_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.hourOfHalfday().isSupported());
    }

    public void testTimeFields_15_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.clockhourOfDay().isSupported());
    }

    public void testTimeFields_16_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.hourOfDay().isSupported());
    }

    public void testTimeFields_17_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.minuteOfDay().isSupported());
    }

    public void testTimeFields_18_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.minuteOfHour().isSupported());
    }

    public void testTimeFields_19_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.secondOfDay().isSupported());
    }

    public void testTimeFields_20_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.secondOfMinute().isSupported());
    }

    public void testTimeFields_21_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.millisOfDay().isSupported());
    }

    public void testTimeFields_22_oe() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        
        assertEquals(true,islamic.millisOfSecond().isSupported());
    }

    public void testEpoch_1_oe() {
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, ISLAMIC_UTC);
        DateTime expectedEpoch = new DateTime(622, 7, 16, 0, 0, 0, 0, JULIAN_UTC);
        assertEquals(expectedEpoch.getMillis(),epoch.getMillis());
    }

    public void testEra_1_oe() {
        int a = 1;
        assertEquals(a, IslamicChronology.AH);
    }

    public void testFieldConstructor_1_oe() {
        DateTime date = new DateTime(1364, 12, 6, 0, 0, 0, 0, ISLAMIC_UTC);
        DateTime expectedDate = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        assertEquals(expectedDate.getMillis(),date.getMillis());
    }

    public void testSampleDate1_1_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH,dt.getEra());
    }

    public void testSampleDate1_2_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        assertEquals(14,dt.getCenturyOfEra());// TODO confirm assertEquals(64,dt.getYearOfCentury());
    }

    public void testSampleDate1_3_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        assertEquals(1364,dt.getYearOfEra());
    }

    public void testSampleDate1_4_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        assertEquals(1364,dt.getYear());
    }

    public void testSampleDate1_5_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate1_6_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate1_7_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
    }

    public void testSampleDate1_8_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        assertEquals(new DateTime(1365,12,6,0,0,0,0,ISLAMIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate1_9_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        assertEquals(12,dt.getMonthOfYear());
    }

    public void testSampleDate1_10_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate1_11_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate1_12_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
    }

    public void testSampleDate1_13_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate1_14_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate1_15_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(12,fld.getMaximumValue());
    }

    public void testSampleDate1_16_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(12,fld.getMaximumValueOverall());
    }

    public void testSampleDate1_17_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(new DateTime(1365,1,6,0,0,0,0,ISLAMIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate1_18_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(new DateTime(1364,1,6,0,0,0,0,ISLAMIC_UTC),fld.addWrapFieldToCopy(1));
    }

    public void testSampleDate1_19_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        assertEquals(6,dt.getDayOfMonth());
    }

    public void testSampleDate1_20_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate1_21_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate1_22_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate1_23_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate1_24_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate1_25_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(29,fld.getMaximumValue());
    }

    public void testSampleDate1_26_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(30,fld.getMaximumValueOverall());
    }

    public void testSampleDate1_27_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(new DateTime(1364,12,7,0,0,0,0,ISLAMIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate1_28_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        assertEquals(DateTimeConstants.MONDAY,dt.getDayOfWeek());
    }

    public void testSampleDate1_29_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate1_30_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate1_31_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate1_32_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate1_33_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate1_34_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(7,fld.getMaximumValue());
    }

    public void testSampleDate1_35_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(7,fld.getMaximumValueOverall());
    }

    public void testSampleDate1_36_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(new DateTime(1364,12,7,0,0,0,0,ISLAMIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate1_37_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        assertEquals(6 * 30 + 5 * 29 + 6,dt.getDayOfYear());
    }

    public void testSampleDate1_38_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate1_39_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate1_40_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate1_41_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate1_42_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate1_43_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(354,fld.getMaximumValue());
    }

    public void testSampleDate1_44_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(355,fld.getMaximumValueOverall());
    }

    public void testSampleDate1_45_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(new DateTime(1364,12,7,0,0,0,0,ISLAMIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate1_46_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getHourOfDay());
    }

    public void testSampleDate1_47_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getMinuteOfHour());
    }

    public void testSampleDate1_48_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getSecondOfMinute());
    }

    public void testSampleDate1_49_oe() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testSampleDate2_1_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH,dt.getEra());
    }

    public void testSampleDate2_2_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        assertEquals(15,dt.getCenturyOfEra());// TODO confirm assertEquals(26,dt.getYearOfCentury());
    }

    public void testSampleDate2_3_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        assertEquals(1426,dt.getYearOfEra());
    }

    public void testSampleDate2_4_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        assertEquals(1426,dt.getYear());
    }

    public void testSampleDate2_5_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        assertEquals(true,fld.isLeap());
    }

    public void testSampleDate2_6_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        assertEquals(1,fld.getLeapAmount());
    }

    public void testSampleDate2_7_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
    }

    public void testSampleDate2_8_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        assertEquals(10,dt.getMonthOfYear());
    }

    public void testSampleDate2_9_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate2_10_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate2_11_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
    }

    public void testSampleDate2_12_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate2_13_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate2_14_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(12,fld.getMaximumValue());
    }

    public void testSampleDate2_15_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(12,fld.getMaximumValueOverall());
    }

    public void testSampleDate2_16_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        assertEquals(24,dt.getDayOfMonth());
    }

    public void testSampleDate2_17_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate2_18_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate2_19_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate2_20_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate2_21_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate2_22_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(29,fld.getMaximumValue());
    }

    public void testSampleDate2_23_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(30,fld.getMaximumValueOverall());
    }

    public void testSampleDate2_24_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        assertEquals(DateTimeConstants.SATURDAY,dt.getDayOfWeek());
    }

    public void testSampleDate2_25_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate2_26_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate2_27_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate2_28_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate2_29_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate2_30_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(7,fld.getMaximumValue());
    }

    public void testSampleDate2_31_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(7,fld.getMaximumValueOverall());
    }

    public void testSampleDate2_32_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        assertEquals(5 * 30 + 4 * 29 + 24,dt.getDayOfYear());
    }

    public void testSampleDate2_33_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate2_34_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate2_35_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate2_36_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate2_37_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate2_38_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(355,fld.getMaximumValue());
    }

    public void testSampleDate2_39_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(355,fld.getMaximumValueOverall());
    }

    public void testSampleDate2_40_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getHourOfDay());
    }

    public void testSampleDate2_41_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getMinuteOfHour());
    }

    public void testSampleDate2_42_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getSecondOfMinute());
    }

    public void testSampleDate2_43_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testSampleDate3_1_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH,dt.getEra());
    }

    public void testSampleDate3_2_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        assertEquals(1426,dt.getYear());
    }

    public void testSampleDate3_3_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        assertEquals(true,fld.isLeap());
    }

    public void testSampleDate3_4_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        assertEquals(1,fld.getLeapAmount());
    }

    public void testSampleDate3_5_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
    }

    public void testSampleDate3_6_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        assertEquals(12,dt.getMonthOfYear());
    }

    public void testSampleDate3_7_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(true,fld.isLeap());
    }

    public void testSampleDate3_8_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getLeapAmount());
    }

    public void testSampleDate3_9_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
    }

    public void testSampleDate3_10_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate3_11_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate3_12_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(12,fld.getMaximumValue());
    }

    public void testSampleDate3_13_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(12,fld.getMaximumValueOverall());
    }

    public void testSampleDate3_14_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        assertEquals(24,dt.getDayOfMonth());
    }

    public void testSampleDate3_15_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate3_16_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate3_17_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate3_18_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate3_19_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate3_20_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(30,fld.getMaximumValue());
    }

    public void testSampleDate3_21_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(30,fld.getMaximumValueOverall());
    }

    public void testSampleDate3_22_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        assertEquals(DateTimeConstants.TUESDAY,dt.getDayOfWeek());
    }

    public void testSampleDate3_23_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate3_24_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate3_25_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate3_26_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate3_27_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate3_28_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(7,fld.getMaximumValue());
    }

    public void testSampleDate3_29_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(7,fld.getMaximumValueOverall());
    }

    public void testSampleDate3_30_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        assertEquals(6 * 30 + 5 * 29 + 24,dt.getDayOfYear());
    }

    public void testSampleDate3_31_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate3_32_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate3_33_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate3_34_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate3_35_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate3_36_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(355,fld.getMaximumValue());
    }

    public void testSampleDate3_37_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(355,fld.getMaximumValueOverall());
    }

    public void testSampleDate3_38_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getHourOfDay());
    }

    public void testSampleDate3_39_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getMinuteOfHour());
    }

    public void testSampleDate3_40_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getSecondOfMinute());
    }

    public void testSampleDate3_41_oe() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testSampleDateWithZone_1_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 12, 0, 0, 0, PARIS).withChronology(ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH,dt.getEra());
    }

    public void testSampleDateWithZone_2_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 12, 0, 0, 0, PARIS).withChronology(ISLAMIC_UTC);
        assertEquals(1426,dt.getYear());
    }

    public void testSampleDateWithZone_3_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 12, 0, 0, 0, PARIS).withChronology(ISLAMIC_UTC);
        assertEquals(10,dt.getMonthOfYear());
    }

    public void testSampleDateWithZone_4_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 12, 0, 0, 0, PARIS).withChronology(ISLAMIC_UTC);
        assertEquals(24,dt.getDayOfMonth());
    }

    public void testSampleDateWithZone_5_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 12, 0, 0, 0, PARIS).withChronology(ISLAMIC_UTC);
        assertEquals(11,dt.getHourOfDay());// PARIS is UTC+1 in summer(12-1=11)assertEquals(0,dt.getMinuteOfHour());
    }

    public void testSampleDateWithZone_6_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 12, 0, 0, 0, PARIS).withChronology(ISLAMIC_UTC);
        assertEquals(0,dt.getSecondOfMinute());
    }

    public void testSampleDateWithZone_7_oe() {
        DateTime dt = new DateTime(2005, 11, 26, 12, 0, 0, 0, PARIS).withChronology(ISLAMIC_UTC);
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void test15BasedLeapYear_1_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(1));
    }

    public void test15BasedLeapYear_2_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(2));
    }

    public void test15BasedLeapYear_3_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(3));
    }

    public void test15BasedLeapYear_4_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(4));
    }

    public void test15BasedLeapYear_5_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(5));
    }

    public void test15BasedLeapYear_6_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(6));
    }

    public void test15BasedLeapYear_7_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(7));
    }

    public void test15BasedLeapYear_8_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(8));
    }

    public void test15BasedLeapYear_9_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(9));
    }

    public void test15BasedLeapYear_10_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(10));
    }

    public void test15BasedLeapYear_11_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(11));
    }

    public void test15BasedLeapYear_12_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(12));
    }

    public void test15BasedLeapYear_13_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(13));
    }

    public void test15BasedLeapYear_14_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(14));
    }

    public void test15BasedLeapYear_15_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(15));
    }

    public void test15BasedLeapYear_16_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(16));
    }

    public void test15BasedLeapYear_17_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(17));
    }

    public void test15BasedLeapYear_18_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(18));
    }

    public void test15BasedLeapYear_19_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(19));
    }

    public void test15BasedLeapYear_20_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(20));
    }

    public void test15BasedLeapYear_21_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(21));
    }

    public void test15BasedLeapYear_22_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(22));
    }

    public void test15BasedLeapYear_23_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(23));
    }

    public void test15BasedLeapYear_24_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(24));
    }

    public void test15BasedLeapYear_25_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(25));
    }

    public void test15BasedLeapYear_26_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(26));
    }

    public void test15BasedLeapYear_27_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(27));
    }

    public void test15BasedLeapYear_28_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(28));
    }

    public void test15BasedLeapYear_29_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(29));
    }

    public void test15BasedLeapYear_30_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(30));
    }

    public void test16BasedLeapYear_1_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(1));
    }

    public void test16BasedLeapYear_2_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(2));
    }

    public void test16BasedLeapYear_3_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(3));
    }

    public void test16BasedLeapYear_4_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(4));
    }

    public void test16BasedLeapYear_5_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(5));
    }

    public void test16BasedLeapYear_6_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(6));
    }

    public void test16BasedLeapYear_7_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(7));
    }

    public void test16BasedLeapYear_8_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(8));
    }

    public void test16BasedLeapYear_9_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(9));
    }

    public void test16BasedLeapYear_10_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(10));
    }

    public void test16BasedLeapYear_11_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(11));
    }

    public void test16BasedLeapYear_12_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(12));
    }

    public void test16BasedLeapYear_13_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(13));
    }

    public void test16BasedLeapYear_14_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(14));
    }

    public void test16BasedLeapYear_15_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(15));
    }

    public void test16BasedLeapYear_16_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(16));
    }

    public void test16BasedLeapYear_17_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(17));
    }

    public void test16BasedLeapYear_18_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(18));
    }

    public void test16BasedLeapYear_19_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(19));
    }

    public void test16BasedLeapYear_20_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(20));
    }

    public void test16BasedLeapYear_21_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(21));
    }

    public void test16BasedLeapYear_22_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(22));
    }

    public void test16BasedLeapYear_23_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(23));
    }

    public void test16BasedLeapYear_24_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(24));
    }

    public void test16BasedLeapYear_25_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(25));
    }

    public void test16BasedLeapYear_26_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(26));
    }

    public void test16BasedLeapYear_27_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(27));
    }

    public void test16BasedLeapYear_28_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(28));
    }

    public void test16BasedLeapYear_29_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(29));
    }

    public void test16BasedLeapYear_30_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(30));
    }

    public void testIndianBasedLeapYear_1_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(1));
    }

    public void testIndianBasedLeapYear_2_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(2));
    }

    public void testIndianBasedLeapYear_3_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(3));
    }

    public void testIndianBasedLeapYear_4_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(4));
    }

    public void testIndianBasedLeapYear_5_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(5));
    }

    public void testIndianBasedLeapYear_6_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(6));
    }

    public void testIndianBasedLeapYear_7_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(7));
    }

    public void testIndianBasedLeapYear_8_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(8));
    }

    public void testIndianBasedLeapYear_9_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(9));
    }

    public void testIndianBasedLeapYear_10_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(10));
    }

    public void testIndianBasedLeapYear_11_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(11));
    }

    public void testIndianBasedLeapYear_12_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(12));
    }

    public void testIndianBasedLeapYear_13_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(13));
    }

    public void testIndianBasedLeapYear_14_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(14));
    }

    public void testIndianBasedLeapYear_15_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(15));
    }

    public void testIndianBasedLeapYear_16_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(16));
    }

    public void testIndianBasedLeapYear_17_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(17));
    }

    public void testIndianBasedLeapYear_18_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(18));
    }

    public void testIndianBasedLeapYear_19_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(19));
    }

    public void testIndianBasedLeapYear_20_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(20));
    }

    public void testIndianBasedLeapYear_21_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(21));
    }

    public void testIndianBasedLeapYear_22_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(22));
    }

    public void testIndianBasedLeapYear_23_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(23));
    }

    public void testIndianBasedLeapYear_24_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(24));
    }

    public void testIndianBasedLeapYear_25_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(25));
    }

    public void testIndianBasedLeapYear_26_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(26));
    }

    public void testIndianBasedLeapYear_27_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(27));
    }

    public void testIndianBasedLeapYear_28_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(28));
    }

    public void testIndianBasedLeapYear_29_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(29));
    }

    public void testIndianBasedLeapYear_30_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(30));
    }

    public void testHabashAlHasibBasedLeapYear_1_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(1));
    }

    public void testHabashAlHasibBasedLeapYear_2_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(2));
    }

    public void testHabashAlHasibBasedLeapYear_3_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(3));
    }

    public void testHabashAlHasibBasedLeapYear_4_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(4));
    }

    public void testHabashAlHasibBasedLeapYear_5_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(5));
    }

    public void testHabashAlHasibBasedLeapYear_6_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(6));
    }

    public void testHabashAlHasibBasedLeapYear_7_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(7));
    }

    public void testHabashAlHasibBasedLeapYear_8_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(8));
    }

    public void testHabashAlHasibBasedLeapYear_9_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(9));
    }

    public void testHabashAlHasibBasedLeapYear_10_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(10));
    }

    public void testHabashAlHasibBasedLeapYear_11_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(11));
    }

    public void testHabashAlHasibBasedLeapYear_12_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(12));
    }

    public void testHabashAlHasibBasedLeapYear_13_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(13));
    }

    public void testHabashAlHasibBasedLeapYear_14_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(14));
    }

    public void testHabashAlHasibBasedLeapYear_15_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(15));
    }

    public void testHabashAlHasibBasedLeapYear_16_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(16));
    }

    public void testHabashAlHasibBasedLeapYear_17_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(17));
    }

    public void testHabashAlHasibBasedLeapYear_18_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(18));
    }

    public void testHabashAlHasibBasedLeapYear_19_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(19));
    }

    public void testHabashAlHasibBasedLeapYear_20_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(20));
    }

    public void testHabashAlHasibBasedLeapYear_21_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(21));
    }

    public void testHabashAlHasibBasedLeapYear_22_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(22));
    }

    public void testHabashAlHasibBasedLeapYear_23_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(23));
    }

    public void testHabashAlHasibBasedLeapYear_24_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(24));
    }

    public void testHabashAlHasibBasedLeapYear_25_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(25));
    }

    public void testHabashAlHasibBasedLeapYear_26_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(26));
    }

    public void testHabashAlHasibBasedLeapYear_27_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(27));
    }

    public void testHabashAlHasibBasedLeapYear_28_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(28));
    }

    public void testHabashAlHasibBasedLeapYear_29_oe() {
        boolean a = false;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(29));
    }

    public void testHabashAlHasibBasedLeapYear_30_oe() {
        boolean a = true;
        assertEquals(a, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(30));
    }

public void testEra_oe_101_oe() {
        try {
            new DateTime(-1, 13, 5, 0, 0, 0, 0, ISLAMIC_UTC);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

}
