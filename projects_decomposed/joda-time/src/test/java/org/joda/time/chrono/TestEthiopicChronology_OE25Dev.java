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
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.DateTime.Property;

/**
 * This class is a Junit unit test for EthiopicChronology.
 *
 * @author Stephen Colebourne
 */
public class TestEthiopicChronology_OE25Dev extends TestCase {

    private static final int MILLIS_PER_DAY = DateTimeConstants.MILLIS_PER_DAY;

    private static long SKIP = 1 * MILLIS_PER_DAY;

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final Chronology ETHIOPIC_UTC = EthiopicChronology.getInstanceUTC();
    private static final Chronology JULIAN_UTC = JulianChronology.getInstanceUTC();
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();

    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    // 2002-06-09
    private long TEST_TIME_NOW =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * MILLIS_PER_DAY;

    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        SKIP = 1 * MILLIS_PER_DAY;
        return new TestSuite(TestEthiopicChronology_OE25Dev.class);
    }

    public TestEthiopicChronology_OE25Dev(String name) {
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
        assertEquals(DateTimeZone.UTC,EthiopicChronology.getInstanceUTC().getZone());
        assertSame(EthiopicChronology.class,EthiopicChronology.getInstanceUTC().getClass());
    }

    public void testFactory() {
        assertEquals(LONDON,EthiopicChronology.getInstance().getZone());
        assertSame(EthiopicChronology.class,EthiopicChronology.getInstance().getClass());
    }

    public void testFactory_Zone() {
        assertEquals(TOKYO,EthiopicChronology.getInstance(TOKYO).getZone());
        assertEquals(PARIS,EthiopicChronology.getInstance(PARIS).getZone());
        assertEquals(LONDON,EthiopicChronology.getInstance(null).getZone());
        assertSame(EthiopicChronology.class,EthiopicChronology.getInstance(TOKYO).getClass());
    }

    //-----------------------------------------------------------------------
    public void testEquality() {
        assertSame(EthiopicChronology.getInstance(TOKYO),EthiopicChronology.getInstance(TOKYO));
        assertSame(EthiopicChronology.getInstance(LONDON),EthiopicChronology.getInstance(LONDON));
        assertSame(EthiopicChronology.getInstance(PARIS),EthiopicChronology.getInstance(PARIS));
        assertSame(EthiopicChronology.getInstanceUTC(),EthiopicChronology.getInstanceUTC());
        assertSame(EthiopicChronology.getInstance(),EthiopicChronology.getInstance(LONDON));
    }

    public void testWithUTC() {
        assertSame(EthiopicChronology.getInstanceUTC(),EthiopicChronology.getInstance(LONDON).withUTC());
        assertSame(EthiopicChronology.getInstanceUTC(),EthiopicChronology.getInstance(TOKYO).withUTC());
        assertSame(EthiopicChronology.getInstanceUTC(),EthiopicChronology.getInstanceUTC().withUTC());
        assertSame(EthiopicChronology.getInstanceUTC(),EthiopicChronology.getInstance().withUTC());
    }

    public void testWithZone() {
        assertSame(EthiopicChronology.getInstance(TOKYO),EthiopicChronology.getInstance(TOKYO).withZone(TOKYO));
        assertSame(EthiopicChronology.getInstance(LONDON),EthiopicChronology.getInstance(TOKYO).withZone(LONDON));
        assertSame(EthiopicChronology.getInstance(PARIS),EthiopicChronology.getInstance(TOKYO).withZone(PARIS));
        assertSame(EthiopicChronology.getInstance(LONDON),EthiopicChronology.getInstance(TOKYO).withZone(null));
        assertSame(EthiopicChronology.getInstance(PARIS),EthiopicChronology.getInstance().withZone(PARIS));
        assertSame(EthiopicChronology.getInstance(PARIS),EthiopicChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString() {
        assertEquals("EthiopicChronology[Europe/London]",EthiopicChronology.getInstance(LONDON).toString());
        assertEquals("EthiopicChronology[Asia/Tokyo]",EthiopicChronology.getInstance(TOKYO).toString());
        assertEquals("EthiopicChronology[Europe/London]",EthiopicChronology.getInstance().toString());
        assertEquals("EthiopicChronology[UTC]",EthiopicChronology.getInstanceUTC().toString());
    }

    //-----------------------------------------------------------------------
    public void testDurationFields() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("eras",ethiopic.eras().getName());
        assertEquals("centuries",ethiopic.centuries().getName());
        assertEquals("years",ethiopic.years().getName());
        assertEquals("weekyears",ethiopic.weekyears().getName());
        assertEquals("months",ethiopic.months().getName());
        assertEquals("weeks",ethiopic.weeks().getName());
        assertEquals("days",ethiopic.days().getName());
        assertEquals("halfdays",ethiopic.halfdays().getName());
        assertEquals("hours",ethiopic.hours().getName());
        assertEquals("minutes",ethiopic.minutes().getName());
        assertEquals("seconds",ethiopic.seconds().getName());
        assertEquals("millis",ethiopic.millis().getName());
        
        assertEquals(false,ethiopic.eras().isSupported());
        assertEquals(true,ethiopic.centuries().isSupported());
        assertEquals(true,ethiopic.years().isSupported());
        assertEquals(true,ethiopic.weekyears().isSupported());
        assertEquals(true,ethiopic.months().isSupported());
        assertEquals(true,ethiopic.weeks().isSupported());
        assertEquals(true,ethiopic.days().isSupported());
        assertEquals(true,ethiopic.halfdays().isSupported());
        assertEquals(true,ethiopic.hours().isSupported());
        assertEquals(true,ethiopic.minutes().isSupported());
        assertEquals(true,ethiopic.seconds().isSupported());
        assertEquals(true,ethiopic.millis().isSupported());
        
        assertEquals(false,ethiopic.centuries().isPrecise());
        assertEquals(false,ethiopic.years().isPrecise());
        assertEquals(false,ethiopic.weekyears().isPrecise());
        assertEquals(false,ethiopic.months().isPrecise());
        assertEquals(false,ethiopic.weeks().isPrecise());
        assertEquals(false,ethiopic.days().isPrecise());
        assertEquals(false,ethiopic.halfdays().isPrecise());
        assertEquals(true,ethiopic.hours().isPrecise());
        assertEquals(true,ethiopic.minutes().isPrecise());
        assertEquals(true,ethiopic.seconds().isPrecise());
        assertEquals(true,ethiopic.millis().isPrecise());
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(false,ethiopicUTC.centuries().isPrecise());
        assertEquals(false,ethiopicUTC.years().isPrecise());
        assertEquals(false,ethiopicUTC.weekyears().isPrecise());
        assertEquals(false,ethiopicUTC.months().isPrecise());
        assertEquals(true,ethiopicUTC.weeks().isPrecise());
        assertEquals(true,ethiopicUTC.days().isPrecise());
        assertEquals(true,ethiopicUTC.halfdays().isPrecise());
        assertEquals(true,ethiopicUTC.hours().isPrecise());
        assertEquals(true,ethiopicUTC.minutes().isPrecise());
        assertEquals(true,ethiopicUTC.seconds().isPrecise());
        assertEquals(true,ethiopicUTC.millis().isPrecise());
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(false,ethiopicGMT.centuries().isPrecise());
        assertEquals(false,ethiopicGMT.years().isPrecise());
        assertEquals(false,ethiopicGMT.weekyears().isPrecise());
        assertEquals(false,ethiopicGMT.months().isPrecise());
        assertEquals(true,ethiopicGMT.weeks().isPrecise());
        assertEquals(true,ethiopicGMT.days().isPrecise());
        assertEquals(true,ethiopicGMT.halfdays().isPrecise());
        assertEquals(true,ethiopicGMT.hours().isPrecise());
        assertEquals(true,ethiopicGMT.minutes().isPrecise());
        assertEquals(true,ethiopicGMT.seconds().isPrecise());
        assertEquals(true,ethiopicGMT.millis().isPrecise());
    }

    public void testDateFields() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("era",ethiopic.era().getName());
        assertEquals("centuryOfEra",ethiopic.centuryOfEra().getName());
        assertEquals("yearOfCentury",ethiopic.yearOfCentury().getName());
        assertEquals("yearOfEra",ethiopic.yearOfEra().getName());
        assertEquals("year",ethiopic.year().getName());
        assertEquals("monthOfYear",ethiopic.monthOfYear().getName());
        assertEquals("weekyearOfCentury",ethiopic.weekyearOfCentury().getName());
        assertEquals("weekyear",ethiopic.weekyear().getName());
        assertEquals("weekOfWeekyear",ethiopic.weekOfWeekyear().getName());
        assertEquals("dayOfYear",ethiopic.dayOfYear().getName());
        assertEquals("dayOfMonth",ethiopic.dayOfMonth().getName());
        assertEquals("dayOfWeek",ethiopic.dayOfWeek().getName());
        
        assertEquals(true,ethiopic.era().isSupported());
        assertEquals(true,ethiopic.centuryOfEra().isSupported());
        assertEquals(true,ethiopic.yearOfCentury().isSupported());
        assertEquals(true,ethiopic.yearOfEra().isSupported());
        assertEquals(true,ethiopic.year().isSupported());
        assertEquals(true,ethiopic.monthOfYear().isSupported());
        assertEquals(true,ethiopic.weekyearOfCentury().isSupported());
        assertEquals(true,ethiopic.weekyear().isSupported());
        assertEquals(true,ethiopic.weekOfWeekyear().isSupported());
        assertEquals(true,ethiopic.dayOfYear().isSupported());
        assertEquals(true,ethiopic.dayOfMonth().isSupported());
        assertEquals(true,ethiopic.dayOfWeek().isSupported());
        
        assertEquals(ethiopic.eras(),ethiopic.era().getDurationField());
        assertEquals(ethiopic.centuries(),ethiopic.centuryOfEra().getDurationField());
        assertEquals(ethiopic.years(),ethiopic.yearOfCentury().getDurationField());
        assertEquals(ethiopic.years(),ethiopic.yearOfEra().getDurationField());
        assertEquals(ethiopic.years(),ethiopic.year().getDurationField());
        assertEquals(ethiopic.months(),ethiopic.monthOfYear().getDurationField());
        assertEquals(ethiopic.weekyears(),ethiopic.weekyearOfCentury().getDurationField());
        assertEquals(ethiopic.weekyears(),ethiopic.weekyear().getDurationField());
        assertEquals(ethiopic.weeks(),ethiopic.weekOfWeekyear().getDurationField());
        assertEquals(ethiopic.days(),ethiopic.dayOfYear().getDurationField());
        assertEquals(ethiopic.days(),ethiopic.dayOfMonth().getDurationField());
        assertEquals(ethiopic.days(),ethiopic.dayOfWeek().getDurationField());
        
        assertEquals(null,ethiopic.era().getRangeDurationField());
        assertEquals(ethiopic.eras(),ethiopic.centuryOfEra().getRangeDurationField());
        assertEquals(ethiopic.centuries(),ethiopic.yearOfCentury().getRangeDurationField());
        assertEquals(ethiopic.eras(),ethiopic.yearOfEra().getRangeDurationField());
        assertEquals(null,ethiopic.year().getRangeDurationField());
        assertEquals(ethiopic.years(),ethiopic.monthOfYear().getRangeDurationField());
        assertEquals(ethiopic.centuries(),ethiopic.weekyearOfCentury().getRangeDurationField());
        assertEquals(null,ethiopic.weekyear().getRangeDurationField());
        assertEquals(ethiopic.weekyears(),ethiopic.weekOfWeekyear().getRangeDurationField());
        assertEquals(ethiopic.years(),ethiopic.dayOfYear().getRangeDurationField());
        assertEquals(ethiopic.months(),ethiopic.dayOfMonth().getRangeDurationField());
        assertEquals(ethiopic.weeks(),ethiopic.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("halfdayOfDay",ethiopic.halfdayOfDay().getName());
        assertEquals("clockhourOfHalfday",ethiopic.clockhourOfHalfday().getName());
        assertEquals("hourOfHalfday",ethiopic.hourOfHalfday().getName());
        assertEquals("clockhourOfDay",ethiopic.clockhourOfDay().getName());
        assertEquals("hourOfDay",ethiopic.hourOfDay().getName());
        assertEquals("minuteOfDay",ethiopic.minuteOfDay().getName());
        assertEquals("minuteOfHour",ethiopic.minuteOfHour().getName());
        assertEquals("secondOfDay",ethiopic.secondOfDay().getName());
        assertEquals("secondOfMinute",ethiopic.secondOfMinute().getName());
        assertEquals("millisOfDay",ethiopic.millisOfDay().getName());
        assertEquals("millisOfSecond",ethiopic.millisOfSecond().getName());
        
        assertEquals(true,ethiopic.halfdayOfDay().isSupported());
        assertEquals(true,ethiopic.clockhourOfHalfday().isSupported());
        assertEquals(true,ethiopic.hourOfHalfday().isSupported());
        assertEquals(true,ethiopic.clockhourOfDay().isSupported());
        assertEquals(true,ethiopic.hourOfDay().isSupported());
        assertEquals(true,ethiopic.minuteOfDay().isSupported());
        assertEquals(true,ethiopic.minuteOfHour().isSupported());
        assertEquals(true,ethiopic.secondOfDay().isSupported());
        assertEquals(true,ethiopic.secondOfMinute().isSupported());
        assertEquals(true,ethiopic.millisOfDay().isSupported());
        assertEquals(true,ethiopic.millisOfSecond().isSupported());
    }

    //-----------------------------------------------------------------------
    public void testEpoch() {
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, ETHIOPIC_UTC);
        assertEquals(new DateTime(8,8,29,0,0,0,0,JULIAN_UTC),epoch.withChronology(JULIAN_UTC));
    }

    public void testEra() {
        assertEquals(1,EthiopicChronology.EE);
        try {
            new DateTime(-1, 13, 5, 0, 0, 0, 0, ETHIOPIC_UTC);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    /**
     * Tests era, year, monthOfYear, dayOfMonth and dayOfWeek.
     */
    public void DISABLED_testCalendar() {
        if (TestAll.FAST) {
            return;
        }
        System.out.println("\nTestEthiopicChronology.testCalendar");
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, ETHIOPIC_UTC);
        long millis = epoch.getMillis();
        long end = new DateTime(3000, 1, 1, 0, 0, 0, 0, ISO_UTC).getMillis();
        DateTimeField dayOfWeek = ETHIOPIC_UTC.dayOfWeek();
        DateTimeField dayOfYear = ETHIOPIC_UTC.dayOfYear();
        DateTimeField dayOfMonth = ETHIOPIC_UTC.dayOfMonth();
        DateTimeField monthOfYear = ETHIOPIC_UTC.monthOfYear();
        DateTimeField year = ETHIOPIC_UTC.year();
        DateTimeField yearOfEra = ETHIOPIC_UTC.yearOfEra();
        DateTimeField era = ETHIOPIC_UTC.era();
        int expectedDOW = new DateTime(8, 8, 29, 0, 0, 0, 0, JULIAN_UTC).getDayOfWeek();
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
            int monthLen = dayOfMonth.getMaximumValue(millis);
            if (monthValue < 1 || monthValue > 13) {
                fail("Bad month: " + millis);
            }
            
            // test era
            assertEquals(1,era.get(millis));
            assertEquals("EE",era.getAsText(millis));
            assertEquals("EE",era.getAsShortText(millis));
            
            // test date
            assertEquals(expectedYear,yearValue);
            assertEquals(expectedYear,yearOfEraValue);
            assertEquals(expectedMonth,monthValue);
            assertEquals(expectedDay,dayValue);
            assertEquals(expectedDOW,dowValue);
            assertEquals(expectedDOY,doyValue);
            
            // test leap year
            assertEquals(yearValue % 4 == 3,year.isLeap(millis));
            
            // test month length
            if (monthValue == 13) {
                assertEquals(yearValue % 4 == 3,monthOfYear.isLeap(millis));
                if (yearValue % 4 == 3) {
                    assertEquals(6,monthLen);
                } else {
                    assertEquals(5,monthLen);
                }
            } else {
                assertEquals(30,monthLen);
            }
            
            // recalculate date
            expectedDOW = (((expectedDOW + 1) - 1) % 7) + 1;
            expectedDay++;
            expectedDOY++;
            if (expectedDay == 31 && expectedMonth < 13) {
                expectedDay = 1;
                expectedMonth++;
            } else if (expectedMonth == 13) {
                if (expectedYear % 4 == 3 && expectedDay == 7) {
                    expectedDay = 1;
                    expectedMonth = 1;
                    expectedYear++;
                    expectedDOY = 1;
                } else if (expectedYear % 4 != 3 && expectedDay == 6) {
                    expectedDay = 1;
                    expectedMonth = 1;
                    expectedYear++;
                    expectedDOY = 1;
                }
            }
            millis += SKIP;
        }
    }

    public void testSampleDate() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        assertEquals(EthiopicChronology.EE,dt.getEra());
        assertEquals(20,dt.getCenturyOfEra());// TODO confirm assertEquals(96,dt.getYearOfCentury());
        assertEquals(1996,dt.getYearOfEra());
        
        assertEquals(1996,dt.getYear());
        Property fld = dt.year();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
        assertEquals(new DateTime(1997,10,2,0,0,0,0,ETHIOPIC_UTC),fld.addToCopy(1));
        
        assertEquals(10,dt.getMonthOfYear());
        fld = dt.monthOfYear();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(13,fld.getMaximumValue());
        assertEquals(13,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1997,1,2,0,0,0,0,ETHIOPIC_UTC),fld.addToCopy(4));
        assertEquals(new DateTime(1996,1,2,0,0,0,0,ETHIOPIC_UTC),fld.addWrapFieldToCopy(4));
        
        assertEquals(2,dt.getDayOfMonth());
        fld = dt.dayOfMonth();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(30,fld.getMaximumValue());
        assertEquals(30,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1996,10,3,0,0,0,0,ETHIOPIC_UTC),fld.addToCopy(1));
        
        assertEquals(DateTimeConstants.WEDNESDAY,dt.getDayOfWeek());
        fld = dt.dayOfWeek();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(7,fld.getMaximumValue());
        assertEquals(7,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1996,10,3,0,0,0,0,ETHIOPIC_UTC),fld.addToCopy(1));
        
        assertEquals(9 * 30 + 2,dt.getDayOfYear());
        fld = dt.dayOfYear();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(365,fld.getMaximumValue());
        assertEquals(366,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1996,10,3,0,0,0,0,ETHIOPIC_UTC),fld.addToCopy(1));
        
        assertEquals(0,dt.getHourOfDay());
        assertEquals(0,dt.getMinuteOfHour());
        assertEquals(0,dt.getSecondOfMinute());
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testSampleDateWithZone() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(ETHIOPIC_UTC);
        assertEquals(EthiopicChronology.EE,dt.getEra());
        assertEquals(1996,dt.getYear());
        assertEquals(1996,dt.getYearOfEra());
        assertEquals(10,dt.getMonthOfYear());
        assertEquals(2,dt.getDayOfMonth());
        assertEquals(10,dt.getHourOfDay());// PARIS is UTC+2 in summer(12-2=10)assertEquals(0,dt.getMinuteOfHour());
        assertEquals(0,dt.getSecondOfMinute());
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testDurationYear() {
        // Leap 1999, NotLeap 1996,97,98
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        assertEquals(ETHIOPIC_UTC.years(),fld);
        assertEquals(1L * 365L * MILLIS_PER_DAY,fld.getMillis(1,dt96.getMillis()));
        assertEquals(2L * 365L * MILLIS_PER_DAY,fld.getMillis(2,dt96.getMillis()));
        assertEquals(3L * 365L * MILLIS_PER_DAY,fld.getMillis(3,dt96.getMillis()));
        assertEquals((4L * 365L + 1L)* MILLIS_PER_DAY,fld.getMillis(4,dt96.getMillis()));
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getMillis(1));
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 2,fld.getMillis(2));
        
        assertEquals(1L * 365L * MILLIS_PER_DAY,fld.getMillis(1L,dt96.getMillis()));
        assertEquals(2L * 365L * MILLIS_PER_DAY,fld.getMillis(2L,dt96.getMillis()));
        assertEquals(3L * 365L * MILLIS_PER_DAY,fld.getMillis(3L,dt96.getMillis()));
        assertEquals((4L * 365L + 1L)* MILLIS_PER_DAY,fld.getMillis(4L,dt96.getMillis()));
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getMillis(1L));
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 2,fld.getMillis(2L));
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getUnitMillis());
        
        assertEquals(0,fld.getValue(1L * 365L * MILLIS_PER_DAY - 1L,dt96.getMillis()));
        assertEquals(1,fld.getValue(1L * 365L * MILLIS_PER_DAY,dt96.getMillis()));
        assertEquals(1,fld.getValue(1L * 365L * MILLIS_PER_DAY + 1L,dt96.getMillis()));
        assertEquals(1,fld.getValue(2L * 365L * MILLIS_PER_DAY - 1L,dt96.getMillis()));
        assertEquals(2,fld.getValue(2L * 365L * MILLIS_PER_DAY,dt96.getMillis()));
        assertEquals(2,fld.getValue(2L * 365L * MILLIS_PER_DAY + 1L,dt96.getMillis()));
        assertEquals(2,fld.getValue(3L * 365L * MILLIS_PER_DAY - 1L,dt96.getMillis()));
        assertEquals(3,fld.getValue(3L * 365L * MILLIS_PER_DAY,dt96.getMillis()));
        assertEquals(3,fld.getValue(3L * 365L * MILLIS_PER_DAY + 1L,dt96.getMillis()));
        assertEquals(3,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY - 1L,dt96.getMillis()));
        assertEquals(4,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY,dt96.getMillis()));
        assertEquals(4,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY + 1L,dt96.getMillis()));
        
        assertEquals(dt97.getMillis(),fld.add(dt96.getMillis(),1));
        assertEquals(dt98.getMillis(),fld.add(dt96.getMillis(),2));
        assertEquals(dt99.getMillis(),fld.add(dt96.getMillis(),3));
        assertEquals(dt00.getMillis(),fld.add(dt96.getMillis(),4));
        
        assertEquals(dt97.getMillis(),fld.add(dt96.getMillis(),1L));
        assertEquals(dt98.getMillis(),fld.add(dt96.getMillis(),2L));
        assertEquals(dt99.getMillis(),fld.add(dt96.getMillis(),3L));
        assertEquals(dt00.getMillis(),fld.add(dt96.getMillis(),4L));
    }

    public void testDurationMonth() {
        // Leap 1999, NotLeap 1996,97,98
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals(ETHIOPIC_UTC.months(),fld);
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1,dt11.getMillis()));
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2,dt11.getMillis()));
        assertEquals((2L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(3,dt11.getMillis()));
        assertEquals((3L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(4,dt11.getMillis()));
        
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1));
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2));
        assertEquals(13L * 30L * MILLIS_PER_DAY,fld.getMillis(13));
        
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1L,dt11.getMillis()));
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2L,dt11.getMillis()));
        assertEquals((2L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(3L,dt11.getMillis()));
        assertEquals((3L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(4L,dt11.getMillis()));
        
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1L));
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2L));
        assertEquals(13L * 30L * MILLIS_PER_DAY,fld.getMillis(13L));
        
        assertEquals(0,fld.getValue(1L * 30L * MILLIS_PER_DAY - 1L,dt11.getMillis()));
        assertEquals(1,fld.getValue(1L * 30L * MILLIS_PER_DAY,dt11.getMillis()));
        assertEquals(1,fld.getValue(1L * 30L * MILLIS_PER_DAY + 1L,dt11.getMillis()));
        assertEquals(1,fld.getValue(2L * 30L * MILLIS_PER_DAY - 1L,dt11.getMillis()));
        assertEquals(2,fld.getValue(2L * 30L * MILLIS_PER_DAY,dt11.getMillis()));
        assertEquals(2,fld.getValue(2L * 30L * MILLIS_PER_DAY + 1L,dt11.getMillis()));
        assertEquals(2,fld.getValue((2L * 30L + 6L)* MILLIS_PER_DAY - 1L,dt11.getMillis()));
        assertEquals(3,fld.getValue((2L * 30L + 6L)* MILLIS_PER_DAY,dt11.getMillis()));
        assertEquals(3,fld.getValue((2L * 30L + 6L)* MILLIS_PER_DAY + 1L,dt11.getMillis()));
        assertEquals(3,fld.getValue((3L * 30L + 6L)* MILLIS_PER_DAY - 1L,dt11.getMillis()));
        assertEquals(4,fld.getValue((3L * 30L + 6L)* MILLIS_PER_DAY,dt11.getMillis()));
        assertEquals(4,fld.getValue((3L * 30L + 6L)* MILLIS_PER_DAY + 1L,dt11.getMillis()));
        
        assertEquals(dt12.getMillis(),fld.add(dt11.getMillis(),1));
        assertEquals(dt13.getMillis(),fld.add(dt11.getMillis(),2));
        assertEquals(dt01.getMillis(),fld.add(dt11.getMillis(),3));
        
        assertEquals(dt12.getMillis(),fld.add(dt11.getMillis(),1L));
        assertEquals(dt13.getMillis(),fld.add(dt11.getMillis(),2L));
        assertEquals(dt01.getMillis(),fld.add(dt11.getMillis(),3L));
    }

    public void testLeap_5_13() {
        Chronology chrono = EthiopicChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 5, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(false,dt.dayOfMonth().isLeap());
        assertEquals(false,dt.dayOfYear().isLeap());
    }

    public void testLeap_6_13() {
        Chronology chrono = EthiopicChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 6, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(true,dt.dayOfMonth().isLeap());
        assertEquals(true,dt.dayOfYear().isLeap());
    }

    public void testFactoryUTC_1_oe() {
        Object a = DateTimeZone.UTC;
        assertEquals(a, EthiopicChronology.getInstanceUTC().getZone());
    }

    public void testFactoryUTC_2_oe() {
        Object a = EthiopicChronology.class;
        assertSame(a, EthiopicChronology.getInstanceUTC().getClass());
    }

    public void testFactory_1_oe() {
        Object a = LONDON;
        assertEquals(a, EthiopicChronology.getInstance().getZone());
    }

    public void testFactory_2_oe() {
        Object a = EthiopicChronology.class;
        assertSame(a, EthiopicChronology.getInstance().getClass());
    }

    public void testFactory_Zone_1_oe() {
        Object a = TOKYO;
        assertEquals(a, EthiopicChronology.getInstance(TOKYO).getZone());
    }

    public void testFactory_Zone_2_oe() {
        Object a = PARIS;
        assertEquals(a, EthiopicChronology.getInstance(PARIS).getZone());
    }

    public void testFactory_Zone_3_oe() {
        Object a = LONDON;
        assertEquals(a, EthiopicChronology.getInstance(null).getZone());
    }

    public void testFactory_Zone_4_oe() {
        Object a = EthiopicChronology.class;
        assertSame(a, EthiopicChronology.getInstance(TOKYO).getClass());
    }

    public void testEquality_1_oe() {
        Object a = EthiopicChronology.getInstance(TOKYO);
        assertSame(a, EthiopicChronology.getInstance(TOKYO));
    }

    public void testEquality_2_oe() {
        Object a = EthiopicChronology.getInstance(LONDON);
        assertSame(a, EthiopicChronology.getInstance(LONDON));
    }

    public void testEquality_3_oe() {
        Object a = EthiopicChronology.getInstance(PARIS);
        assertSame(a, EthiopicChronology.getInstance(PARIS));
    }

    public void testEquality_4_oe() {
        Object a = EthiopicChronology.getInstanceUTC();
        assertSame(a, EthiopicChronology.getInstanceUTC());
    }

    public void testEquality_5_oe() {
        Object a = EthiopicChronology.getInstance();
        assertSame(a, EthiopicChronology.getInstance(LONDON));
    }

    public void testWithUTC_1_oe() {
        Object a = EthiopicChronology.getInstanceUTC();
        assertSame(a, EthiopicChronology.getInstance(LONDON).withUTC());
    }

    public void testWithUTC_2_oe() {
        Object a = EthiopicChronology.getInstanceUTC();
        assertSame(a, EthiopicChronology.getInstance(TOKYO).withUTC());
    }

    public void testWithUTC_3_oe() {
        Object a = EthiopicChronology.getInstanceUTC();
        assertSame(a, EthiopicChronology.getInstanceUTC().withUTC());
    }

    public void testWithUTC_4_oe() {
        Object a = EthiopicChronology.getInstanceUTC();
        assertSame(a, EthiopicChronology.getInstance().withUTC());
    }

    public void testWithZone_1_oe() {
        Object a = EthiopicChronology.getInstance(TOKYO);
        assertSame(a, EthiopicChronology.getInstance(TOKYO).withZone(TOKYO));
    }

    public void testWithZone_2_oe() {
        Object a = EthiopicChronology.getInstance(LONDON);
        assertSame(a, EthiopicChronology.getInstance(TOKYO).withZone(LONDON));
    }

    public void testWithZone_3_oe() {
        Object a = EthiopicChronology.getInstance(PARIS);
        assertSame(a, EthiopicChronology.getInstance(TOKYO).withZone(PARIS));
    }

    public void testWithZone_4_oe() {
        Object a = EthiopicChronology.getInstance(LONDON);
        assertSame(a, EthiopicChronology.getInstance(TOKYO).withZone(null));
    }

    public void testWithZone_5_oe() {
        Object a = EthiopicChronology.getInstance(PARIS);
        assertSame(a, EthiopicChronology.getInstance().withZone(PARIS));
    }

    public void testWithZone_6_oe() {
        Object a = EthiopicChronology.getInstance(PARIS);
        assertSame(a, EthiopicChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString_1_oe() {
        Object a = EthiopicChronology.getInstance(LONDON).toString();
        assertEquals("EthiopicChronology[Europe/London]", a);
    }

    public void testToString_2_oe() {
        Object a = EthiopicChronology.getInstance(TOKYO).toString();
        assertEquals("EthiopicChronology[Asia/Tokyo]", a);
    }

    public void testToString_3_oe() {
        Object a = EthiopicChronology.getInstance().toString();
        assertEquals("EthiopicChronology[Europe/London]", a);
    }

    public void testToString_4_oe() {
        Object a = EthiopicChronology.getInstanceUTC().toString();
        assertEquals("EthiopicChronology[UTC]", a);
    }

    public void testDurationFields_1_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("eras",ethiopic.eras().getName());
    }

    public void testDurationFields_2_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("centuries",ethiopic.centuries().getName());
    }

    public void testDurationFields_3_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("years",ethiopic.years().getName());
    }

    public void testDurationFields_4_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("weekyears",ethiopic.weekyears().getName());
    }

    public void testDurationFields_5_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("months",ethiopic.months().getName());
    }

    public void testDurationFields_6_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("weeks",ethiopic.weeks().getName());
    }

    public void testDurationFields_7_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("days",ethiopic.days().getName());
    }

    public void testDurationFields_8_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("halfdays",ethiopic.halfdays().getName());
    }

    public void testDurationFields_9_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("hours",ethiopic.hours().getName());
    }

    public void testDurationFields_10_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("minutes",ethiopic.minutes().getName());
    }

    public void testDurationFields_11_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("seconds",ethiopic.seconds().getName());
    }

    public void testDurationFields_12_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("millis",ethiopic.millis().getName());
    }

    public void testDurationFields_13_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(false,ethiopic.eras().isSupported());
    }

    public void testDurationFields_14_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.centuries().isSupported());
    }

    public void testDurationFields_15_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.years().isSupported());
    }

    public void testDurationFields_16_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.weekyears().isSupported());
    }

    public void testDurationFields_17_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.months().isSupported());
    }

    public void testDurationFields_18_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.weeks().isSupported());
    }

    public void testDurationFields_19_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.days().isSupported());
    }

    public void testDurationFields_20_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.halfdays().isSupported());
    }

    public void testDurationFields_21_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.hours().isSupported());
    }

    public void testDurationFields_22_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.minutes().isSupported());
    }

    public void testDurationFields_23_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.seconds().isSupported());
    }

    public void testDurationFields_24_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.millis().isSupported());
    }

    public void testDurationFields_25_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(false,ethiopic.centuries().isPrecise());
    }

    public void testDurationFields_26_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(false,ethiopic.years().isPrecise());
    }

    public void testDurationFields_27_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(false,ethiopic.weekyears().isPrecise());
    }

    public void testDurationFields_28_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(false,ethiopic.months().isPrecise());
    }

    public void testDurationFields_29_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(false,ethiopic.weeks().isPrecise());
    }

    public void testDurationFields_30_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(false,ethiopic.days().isPrecise());
    }

    public void testDurationFields_31_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(false,ethiopic.halfdays().isPrecise());
    }

    public void testDurationFields_32_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(true,ethiopic.hours().isPrecise());
    }

    public void testDurationFields_33_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(true,ethiopic.minutes().isPrecise());
    }

    public void testDurationFields_34_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(true,ethiopic.seconds().isPrecise());
    }

    public void testDurationFields_35_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(true,ethiopic.millis().isPrecise());
    }

    public void testDurationFields_36_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(false,ethiopicUTC.centuries().isPrecise());
    }

    public void testDurationFields_37_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(false,ethiopicUTC.years().isPrecise());
    }

    public void testDurationFields_38_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(false,ethiopicUTC.weekyears().isPrecise());
    }

    public void testDurationFields_39_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(false,ethiopicUTC.months().isPrecise());
    }

    public void testDurationFields_40_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(true,ethiopicUTC.weeks().isPrecise());
    }

    public void testDurationFields_41_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(true,ethiopicUTC.days().isPrecise());
    }

    public void testDurationFields_42_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(true,ethiopicUTC.halfdays().isPrecise());
    }

    public void testDurationFields_43_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(true,ethiopicUTC.hours().isPrecise());
    }

    public void testDurationFields_44_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(true,ethiopicUTC.minutes().isPrecise());
    }

    public void testDurationFields_45_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(true,ethiopicUTC.seconds().isPrecise());
    }

    public void testDurationFields_46_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        assertEquals(true,ethiopicUTC.millis().isPrecise());
    }

    public void testDurationFields_47_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(false,ethiopicGMT.centuries().isPrecise());
    }

    public void testDurationFields_48_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(false,ethiopicGMT.years().isPrecise());
    }

    public void testDurationFields_49_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(false,ethiopicGMT.weekyears().isPrecise());
    }

    public void testDurationFields_50_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(false,ethiopicGMT.months().isPrecise());
    }

    public void testDurationFields_51_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(true,ethiopicGMT.weeks().isPrecise());
    }

    public void testDurationFields_52_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(true,ethiopicGMT.days().isPrecise());
    }

    public void testDurationFields_53_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(true,ethiopicGMT.halfdays().isPrecise());
    }

    public void testDurationFields_54_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(true,ethiopicGMT.hours().isPrecise());
    }

    public void testDurationFields_55_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(true,ethiopicGMT.minutes().isPrecise());
    }

    public void testDurationFields_56_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(true,ethiopicGMT.seconds().isPrecise());
    }

    public void testDurationFields_57_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        final EthiopicChronology ethiopicUTC = EthiopicChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final EthiopicChronology ethiopicGMT = EthiopicChronology.getInstance(gmt);
        assertEquals(true,ethiopicGMT.millis().isPrecise());
    }

    public void testDateFields_1_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("era",ethiopic.era().getName());
    }

    public void testDateFields_2_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("centuryOfEra",ethiopic.centuryOfEra().getName());
    }

    public void testDateFields_3_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("yearOfCentury",ethiopic.yearOfCentury().getName());
    }

    public void testDateFields_4_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("yearOfEra",ethiopic.yearOfEra().getName());
    }

    public void testDateFields_5_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("year",ethiopic.year().getName());
    }

    public void testDateFields_6_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("monthOfYear",ethiopic.monthOfYear().getName());
    }

    public void testDateFields_7_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("weekyearOfCentury",ethiopic.weekyearOfCentury().getName());
    }

    public void testDateFields_8_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("weekyear",ethiopic.weekyear().getName());
    }

    public void testDateFields_9_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("weekOfWeekyear",ethiopic.weekOfWeekyear().getName());
    }

    public void testDateFields_10_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("dayOfYear",ethiopic.dayOfYear().getName());
    }

    public void testDateFields_11_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("dayOfMonth",ethiopic.dayOfMonth().getName());
    }

    public void testDateFields_12_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("dayOfWeek",ethiopic.dayOfWeek().getName());
    }

    public void testDateFields_13_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.era().isSupported());
    }

    public void testDateFields_14_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.centuryOfEra().isSupported());
    }

    public void testDateFields_15_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.yearOfCentury().isSupported());
    }

    public void testDateFields_16_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.yearOfEra().isSupported());
    }

    public void testDateFields_17_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.year().isSupported());
    }

    public void testDateFields_18_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.monthOfYear().isSupported());
    }

    public void testDateFields_19_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.weekyearOfCentury().isSupported());
    }

    public void testDateFields_20_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.weekyear().isSupported());
    }

    public void testDateFields_21_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.weekOfWeekyear().isSupported());
    }

    public void testDateFields_22_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.dayOfYear().isSupported());
    }

    public void testDateFields_23_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.dayOfMonth().isSupported());
    }

    public void testDateFields_24_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.dayOfWeek().isSupported());
    }

    public void testDateFields_25_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.eras(),ethiopic.era().getDurationField());
    }

    public void testDateFields_26_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.centuries(),ethiopic.centuryOfEra().getDurationField());
    }

    public void testDateFields_27_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.years(),ethiopic.yearOfCentury().getDurationField());
    }

    public void testDateFields_28_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.years(),ethiopic.yearOfEra().getDurationField());
    }

    public void testDateFields_29_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.years(),ethiopic.year().getDurationField());
    }

    public void testDateFields_30_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.months(),ethiopic.monthOfYear().getDurationField());
    }

    public void testDateFields_31_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.weekyears(),ethiopic.weekyearOfCentury().getDurationField());
    }

    public void testDateFields_32_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.weekyears(),ethiopic.weekyear().getDurationField());
    }

    public void testDateFields_33_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.weeks(),ethiopic.weekOfWeekyear().getDurationField());
    }

    public void testDateFields_34_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.days(),ethiopic.dayOfYear().getDurationField());
    }

    public void testDateFields_35_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.days(),ethiopic.dayOfMonth().getDurationField());
    }

    public void testDateFields_36_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        assertEquals(ethiopic.days(),ethiopic.dayOfWeek().getDurationField());
    }

    public void testDateFields_37_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(null,ethiopic.era().getRangeDurationField());
    }

    public void testDateFields_38_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(ethiopic.eras(),ethiopic.centuryOfEra().getRangeDurationField());
    }

    public void testDateFields_39_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(ethiopic.centuries(),ethiopic.yearOfCentury().getRangeDurationField());
    }

    public void testDateFields_40_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(ethiopic.eras(),ethiopic.yearOfEra().getRangeDurationField());
    }

    public void testDateFields_41_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(null,ethiopic.year().getRangeDurationField());
    }

    public void testDateFields_42_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(ethiopic.years(),ethiopic.monthOfYear().getRangeDurationField());
    }

    public void testDateFields_43_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(ethiopic.centuries(),ethiopic.weekyearOfCentury().getRangeDurationField());
    }

    public void testDateFields_44_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(null,ethiopic.weekyear().getRangeDurationField());
    }

    public void testDateFields_45_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(ethiopic.weekyears(),ethiopic.weekOfWeekyear().getRangeDurationField());
    }

    public void testDateFields_46_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(ethiopic.years(),ethiopic.dayOfYear().getRangeDurationField());
    }

    public void testDateFields_47_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(ethiopic.months(),ethiopic.dayOfMonth().getRangeDurationField());
    }

    public void testDateFields_48_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        
        
        assertEquals(ethiopic.weeks(),ethiopic.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields_1_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("halfdayOfDay",ethiopic.halfdayOfDay().getName());
    }

    public void testTimeFields_2_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("clockhourOfHalfday",ethiopic.clockhourOfHalfday().getName());
    }

    public void testTimeFields_3_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("hourOfHalfday",ethiopic.hourOfHalfday().getName());
    }

    public void testTimeFields_4_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("clockhourOfDay",ethiopic.clockhourOfDay().getName());
    }

    public void testTimeFields_5_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("hourOfDay",ethiopic.hourOfDay().getName());
    }

    public void testTimeFields_6_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("minuteOfDay",ethiopic.minuteOfDay().getName());
    }

    public void testTimeFields_7_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("minuteOfHour",ethiopic.minuteOfHour().getName());
    }

    public void testTimeFields_8_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("secondOfDay",ethiopic.secondOfDay().getName());
    }

    public void testTimeFields_9_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("secondOfMinute",ethiopic.secondOfMinute().getName());
    }

    public void testTimeFields_10_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("millisOfDay",ethiopic.millisOfDay().getName());
    }

    public void testTimeFields_11_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        assertEquals("millisOfSecond",ethiopic.millisOfSecond().getName());
    }

    public void testTimeFields_12_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.halfdayOfDay().isSupported());
    }

    public void testTimeFields_13_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.clockhourOfHalfday().isSupported());
    }

    public void testTimeFields_14_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.hourOfHalfday().isSupported());
    }

    public void testTimeFields_15_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.clockhourOfDay().isSupported());
    }

    public void testTimeFields_16_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.hourOfDay().isSupported());
    }

    public void testTimeFields_17_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.minuteOfDay().isSupported());
    }

    public void testTimeFields_18_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.minuteOfHour().isSupported());
    }

    public void testTimeFields_19_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.secondOfDay().isSupported());
    }

    public void testTimeFields_20_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.secondOfMinute().isSupported());
    }

    public void testTimeFields_21_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.millisOfDay().isSupported());
    }

    public void testTimeFields_22_oe() {
        final EthiopicChronology ethiopic = EthiopicChronology.getInstance();
        
        assertEquals(true,ethiopic.millisOfSecond().isSupported());
    }

    public void testEpoch_1_oe() {
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, ETHIOPIC_UTC);
        assertEquals(new DateTime(8,8,29,0,0,0,0,JULIAN_UTC),epoch.withChronology(JULIAN_UTC));
    }

    public void testEra_1_oe() {
        int a = 1;
        assertEquals(a, EthiopicChronology.EE);
    }

    public void testSampleDate_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        assertEquals(EthiopicChronology.EE,dt.getEra());
    }

    public void testSampleDate_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        assertEquals(20,dt.getCenturyOfEra());// TODO confirm assertEquals(96,dt.getYearOfCentury());
    }

    public void testSampleDate_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        assertEquals(1996,dt.getYearOfEra());
    }

    public void testSampleDate_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        assertEquals(1996,dt.getYear());
    }

    public void testSampleDate_5_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate_6_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate_7_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
    }

    public void testSampleDate_8_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        assertEquals(new DateTime(1997,10,2,0,0,0,0,ETHIOPIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate_9_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        assertEquals(10,dt.getMonthOfYear());
    }

    public void testSampleDate_10_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate_11_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate_12_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
    }

    public void testSampleDate_13_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate_14_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate_15_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(13,fld.getMaximumValue());
    }

    public void testSampleDate_16_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(13,fld.getMaximumValueOverall());
    }

    public void testSampleDate_17_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(new DateTime(1997,1,2,0,0,0,0,ETHIOPIC_UTC),fld.addToCopy(4));
    }

    public void testSampleDate_18_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(new DateTime(1996,1,2,0,0,0,0,ETHIOPIC_UTC),fld.addWrapFieldToCopy(4));
    }

    public void testSampleDate_19_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        assertEquals(2,dt.getDayOfMonth());
    }

    public void testSampleDate_20_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate_21_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate_22_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate_23_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate_24_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate_25_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(30,fld.getMaximumValue());
    }

    public void testSampleDate_26_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(30,fld.getMaximumValueOverall());
    }

    public void testSampleDate_27_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(new DateTime(1996,10,3,0,0,0,0,ETHIOPIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate_28_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        assertEquals(DateTimeConstants.WEDNESDAY,dt.getDayOfWeek());
    }

    public void testSampleDate_29_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate_30_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate_31_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate_32_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate_33_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate_34_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(7,fld.getMaximumValue());
    }

    public void testSampleDate_35_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(7,fld.getMaximumValueOverall());
    }

    public void testSampleDate_36_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(new DateTime(1996,10,3,0,0,0,0,ETHIOPIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate_37_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        assertEquals(9 * 30 + 2,dt.getDayOfYear());
    }

    public void testSampleDate_38_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate_39_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate_40_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate_41_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate_42_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate_43_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(365,fld.getMaximumValue());
    }

    public void testSampleDate_44_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(366,fld.getMaximumValueOverall());
    }

    public void testSampleDate_45_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(new DateTime(1996,10,3,0,0,0,0,ETHIOPIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate_46_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getHourOfDay());
    }

    public void testSampleDate_47_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getMinuteOfHour());
    }

    public void testSampleDate_48_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getSecondOfMinute());
    }

    public void testSampleDate_49_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(ETHIOPIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testSampleDateWithZone_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(ETHIOPIC_UTC);
        assertEquals(EthiopicChronology.EE,dt.getEra());
    }

    public void testSampleDateWithZone_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(ETHIOPIC_UTC);
        assertEquals(1996,dt.getYear());
    }

    public void testSampleDateWithZone_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(ETHIOPIC_UTC);
        assertEquals(1996,dt.getYearOfEra());
    }

    public void testSampleDateWithZone_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(ETHIOPIC_UTC);
        assertEquals(10,dt.getMonthOfYear());
    }

    public void testSampleDateWithZone_5_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(ETHIOPIC_UTC);
        assertEquals(2,dt.getDayOfMonth());
    }

    public void testSampleDateWithZone_6_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(ETHIOPIC_UTC);
        assertEquals(10,dt.getHourOfDay());// PARIS is UTC+2 in summer(12-2=10)assertEquals(0,dt.getMinuteOfHour());
    }

    public void testSampleDateWithZone_7_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(ETHIOPIC_UTC);
        assertEquals(0,dt.getSecondOfMinute());
    }

    public void testSampleDateWithZone_8_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(ETHIOPIC_UTC);
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testDurationYear_1_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        assertEquals(ETHIOPIC_UTC.years(),fld);
    }

    public void testDurationYear_2_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        assertEquals(1L * 365L * MILLIS_PER_DAY,fld.getMillis(1,dt96.getMillis()));
    }

    public void testDurationYear_3_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        assertEquals(2L * 365L * MILLIS_PER_DAY,fld.getMillis(2,dt96.getMillis()));
    }

    public void testDurationYear_4_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        assertEquals(3L * 365L * MILLIS_PER_DAY,fld.getMillis(3,dt96.getMillis()));
    }

    public void testDurationYear_5_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        assertEquals((4L * 365L + 1L)* MILLIS_PER_DAY,fld.getMillis(4,dt96.getMillis()));
    }

    public void testDurationYear_6_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getMillis(1));
    }

    public void testDurationYear_7_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 2,fld.getMillis(2));
    }

    public void testDurationYear_8_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        assertEquals(1L * 365L * MILLIS_PER_DAY,fld.getMillis(1L,dt96.getMillis()));
    }

    public void testDurationYear_9_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        assertEquals(2L * 365L * MILLIS_PER_DAY,fld.getMillis(2L,dt96.getMillis()));
    }

    public void testDurationYear_10_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        assertEquals(3L * 365L * MILLIS_PER_DAY,fld.getMillis(3L,dt96.getMillis()));
    }

    public void testDurationYear_11_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        assertEquals((4L * 365L + 1L)* MILLIS_PER_DAY,fld.getMillis(4L,dt96.getMillis()));
    }

    public void testDurationYear_12_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getMillis(1L));
    }

    public void testDurationYear_13_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 2,fld.getMillis(2L));
    }

    public void testDurationYear_14_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getUnitMillis());
    }

    public void testDurationYear_15_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(0,fld.getValue(1L * 365L * MILLIS_PER_DAY - 1L,dt96.getMillis()));
    }

    public void testDurationYear_16_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(1,fld.getValue(1L * 365L * MILLIS_PER_DAY,dt96.getMillis()));
    }

    public void testDurationYear_17_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(1,fld.getValue(1L * 365L * MILLIS_PER_DAY + 1L,dt96.getMillis()));
    }

    public void testDurationYear_18_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(1,fld.getValue(2L * 365L * MILLIS_PER_DAY - 1L,dt96.getMillis()));
    }

    public void testDurationYear_19_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(2,fld.getValue(2L * 365L * MILLIS_PER_DAY,dt96.getMillis()));
    }

    public void testDurationYear_20_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(2,fld.getValue(2L * 365L * MILLIS_PER_DAY + 1L,dt96.getMillis()));
    }

    public void testDurationYear_21_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(2,fld.getValue(3L * 365L * MILLIS_PER_DAY - 1L,dt96.getMillis()));
    }

    public void testDurationYear_22_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(3,fld.getValue(3L * 365L * MILLIS_PER_DAY,dt96.getMillis()));
    }

    public void testDurationYear_23_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(3,fld.getValue(3L * 365L * MILLIS_PER_DAY + 1L,dt96.getMillis()));
    }

    public void testDurationYear_24_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(3,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY - 1L,dt96.getMillis()));
    }

    public void testDurationYear_25_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(4,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY,dt96.getMillis()));
    }

    public void testDurationYear_26_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        assertEquals(4,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY + 1L,dt96.getMillis()));
    }

    public void testDurationYear_27_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        
        assertEquals(dt97.getMillis(),fld.add(dt96.getMillis(),1));
    }

    public void testDurationYear_28_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        
        assertEquals(dt98.getMillis(),fld.add(dt96.getMillis(),2));
    }

    public void testDurationYear_29_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        
        assertEquals(dt99.getMillis(),fld.add(dt96.getMillis(),3));
    }

    public void testDurationYear_30_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        
        assertEquals(dt00.getMillis(),fld.add(dt96.getMillis(),4));
    }

    public void testDurationYear_31_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        
        
        assertEquals(dt97.getMillis(),fld.add(dt96.getMillis(),1L));
    }

    public void testDurationYear_32_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        
        
        assertEquals(dt98.getMillis(),fld.add(dt96.getMillis(),2L));
    }

    public void testDurationYear_33_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        
        
        assertEquals(dt99.getMillis(),fld.add(dt96.getMillis(),3L));
    }

    public void testDurationYear_34_oe() {
        DateTime dt96 = new DateTime(1996, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt97 = new DateTime(1997, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt98 = new DateTime(1998, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt99 = new DateTime(1999, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt00 = new DateTime(2000, 10, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt96.year().getDurationField();
        
        
        
        
        
        
        
        assertEquals(dt00.getMillis(),fld.add(dt96.getMillis(),4L));
    }

    public void testDurationMonth_1_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals(ETHIOPIC_UTC.months(),fld);
    }

    public void testDurationMonth_2_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1,dt11.getMillis()));
    }

    public void testDurationMonth_3_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2,dt11.getMillis()));
    }

    public void testDurationMonth_4_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals((2L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(3,dt11.getMillis()));
    }

    public void testDurationMonth_5_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals((3L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(4,dt11.getMillis()));
    }

    public void testDurationMonth_6_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1));
    }

    public void testDurationMonth_7_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2));
    }

    public void testDurationMonth_8_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        assertEquals(13L * 30L * MILLIS_PER_DAY,fld.getMillis(13));
    }

    public void testDurationMonth_9_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1L,dt11.getMillis()));
    }

    public void testDurationMonth_10_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2L,dt11.getMillis()));
    }

    public void testDurationMonth_11_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        assertEquals((2L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(3L,dt11.getMillis()));
    }

    public void testDurationMonth_12_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        assertEquals((3L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(4L,dt11.getMillis()));
    }

    public void testDurationMonth_13_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1L));
    }

    public void testDurationMonth_14_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2L));
    }

    public void testDurationMonth_15_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        assertEquals(13L * 30L * MILLIS_PER_DAY,fld.getMillis(13L));
    }

    public void testDurationMonth_16_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(0,fld.getValue(1L * 30L * MILLIS_PER_DAY - 1L,dt11.getMillis()));
    }

    public void testDurationMonth_17_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(1,fld.getValue(1L * 30L * MILLIS_PER_DAY,dt11.getMillis()));
    }

    public void testDurationMonth_18_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(1,fld.getValue(1L * 30L * MILLIS_PER_DAY + 1L,dt11.getMillis()));
    }

    public void testDurationMonth_19_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(1,fld.getValue(2L * 30L * MILLIS_PER_DAY - 1L,dt11.getMillis()));
    }

    public void testDurationMonth_20_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(2,fld.getValue(2L * 30L * MILLIS_PER_DAY,dt11.getMillis()));
    }

    public void testDurationMonth_21_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(2,fld.getValue(2L * 30L * MILLIS_PER_DAY + 1L,dt11.getMillis()));
    }

    public void testDurationMonth_22_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(2,fld.getValue((2L * 30L + 6L)* MILLIS_PER_DAY - 1L,dt11.getMillis()));
    }

    public void testDurationMonth_23_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(3,fld.getValue((2L * 30L + 6L)* MILLIS_PER_DAY,dt11.getMillis()));
    }

    public void testDurationMonth_24_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(3,fld.getValue((2L * 30L + 6L)* MILLIS_PER_DAY + 1L,dt11.getMillis()));
    }

    public void testDurationMonth_25_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(3,fld.getValue((3L * 30L + 6L)* MILLIS_PER_DAY - 1L,dt11.getMillis()));
    }

    public void testDurationMonth_26_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(4,fld.getValue((3L * 30L + 6L)* MILLIS_PER_DAY,dt11.getMillis()));
    }

    public void testDurationMonth_27_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(4,fld.getValue((3L * 30L + 6L)* MILLIS_PER_DAY + 1L,dt11.getMillis()));
    }

    public void testDurationMonth_28_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        assertEquals(dt12.getMillis(),fld.add(dt11.getMillis(),1));
    }

    public void testDurationMonth_29_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        assertEquals(dt13.getMillis(),fld.add(dt11.getMillis(),2));
    }

    public void testDurationMonth_30_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        assertEquals(dt01.getMillis(),fld.add(dt11.getMillis(),3));
    }

    public void testDurationMonth_31_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        
        assertEquals(dt12.getMillis(),fld.add(dt11.getMillis(),1L));
    }

    public void testDurationMonth_32_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        
        assertEquals(dt13.getMillis(),fld.add(dt11.getMillis(),2L));
    }

    public void testDurationMonth_33_oe() {
        DateTime dt11 = new DateTime(1999, 11, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt12 = new DateTime(1999, 12, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt13 = new DateTime(1999, 13, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        DateTime dt01 = new DateTime(2000, 1, 2, 0, 0, 0, 0, ETHIOPIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        
        assertEquals(dt01.getMillis(),fld.add(dt11.getMillis(),3L));
    }

    public void testLeap_5_13_1_oe() {
        Chronology chrono = EthiopicChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 5, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
    }

    public void testLeap_5_13_2_oe() {
        Chronology chrono = EthiopicChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 5, 0, 0, chrono);
        assertEquals(true,dt.monthOfYear().isLeap());
    }

    public void testLeap_5_13_3_oe() {
        Chronology chrono = EthiopicChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 5, 0, 0, chrono);
        assertEquals(false,dt.dayOfMonth().isLeap());
    }

    public void testLeap_5_13_4_oe() {
        Chronology chrono = EthiopicChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 5, 0, 0, chrono);
        assertEquals(false,dt.dayOfYear().isLeap());
    }

    public void testLeap_6_13_1_oe() {
        Chronology chrono = EthiopicChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 6, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
    }

    public void testLeap_6_13_2_oe() {
        Chronology chrono = EthiopicChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 6, 0, 0, chrono);
        assertEquals(true,dt.monthOfYear().isLeap());
    }

    public void testLeap_6_13_3_oe() {
        Chronology chrono = EthiopicChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 6, 0, 0, chrono);
        assertEquals(true,dt.dayOfMonth().isLeap());
    }

    public void testLeap_6_13_4_oe() {
        Chronology chrono = EthiopicChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 6, 0, 0, chrono);
        assertEquals(true,dt.dayOfYear().isLeap());
    }

public void testEra_oe_101_oe() {
        try {
            new DateTime(-1, 13, 5, 0, 0, 0, 0, ETHIOPIC_UTC);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

}
