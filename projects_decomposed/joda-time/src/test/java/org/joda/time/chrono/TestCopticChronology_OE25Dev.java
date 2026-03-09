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
import org.joda.time.DateTime.Property;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

/**
 * This class is a Junit unit test for CopticChronology.
 *
 * @author Stephen Colebourne
 */
public class TestCopticChronology_OE25Dev extends TestCase {

    private static final int MILLIS_PER_DAY = DateTimeConstants.MILLIS_PER_DAY;

    private static long SKIP = 1 * MILLIS_PER_DAY;

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final Chronology COPTIC_UTC = CopticChronology.getInstanceUTC();
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
        return new TestSuite(TestCopticChronology_OE25Dev.class);
    }

    public TestCopticChronology_OE25Dev(String name) {
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
        assertEquals(DateTimeZone.UTC,CopticChronology.getInstanceUTC().getZone());
        assertSame(CopticChronology.class,CopticChronology.getInstanceUTC().getClass());
    }

    public void testFactory() {
        assertEquals(LONDON,CopticChronology.getInstance().getZone());
        assertSame(CopticChronology.class,CopticChronology.getInstance().getClass());
    }

    public void testFactory_Zone() {
        assertEquals(TOKYO,CopticChronology.getInstance(TOKYO).getZone());
        assertEquals(PARIS,CopticChronology.getInstance(PARIS).getZone());
        assertEquals(LONDON,CopticChronology.getInstance(null).getZone());
        assertSame(CopticChronology.class,CopticChronology.getInstance(TOKYO).getClass());
    }

    //-----------------------------------------------------------------------
    public void testEquality() {
        assertSame(CopticChronology.getInstance(TOKYO),CopticChronology.getInstance(TOKYO));
        assertSame(CopticChronology.getInstance(LONDON),CopticChronology.getInstance(LONDON));
        assertSame(CopticChronology.getInstance(PARIS),CopticChronology.getInstance(PARIS));
        assertSame(CopticChronology.getInstanceUTC(),CopticChronology.getInstanceUTC());
        assertSame(CopticChronology.getInstance(),CopticChronology.getInstance(LONDON));
    }

    public void testWithUTC() {
        assertSame(CopticChronology.getInstanceUTC(),CopticChronology.getInstance(LONDON).withUTC());
        assertSame(CopticChronology.getInstanceUTC(),CopticChronology.getInstance(TOKYO).withUTC());
        assertSame(CopticChronology.getInstanceUTC(),CopticChronology.getInstanceUTC().withUTC());
        assertSame(CopticChronology.getInstanceUTC(),CopticChronology.getInstance().withUTC());
    }

    public void testWithZone() {
        assertSame(CopticChronology.getInstance(TOKYO),CopticChronology.getInstance(TOKYO).withZone(TOKYO));
        assertSame(CopticChronology.getInstance(LONDON),CopticChronology.getInstance(TOKYO).withZone(LONDON));
        assertSame(CopticChronology.getInstance(PARIS),CopticChronology.getInstance(TOKYO).withZone(PARIS));
        assertSame(CopticChronology.getInstance(LONDON),CopticChronology.getInstance(TOKYO).withZone(null));
        assertSame(CopticChronology.getInstance(PARIS),CopticChronology.getInstance().withZone(PARIS));
        assertSame(CopticChronology.getInstance(PARIS),CopticChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString() {
        assertEquals("CopticChronology[Europe/London]",CopticChronology.getInstance(LONDON).toString());
        assertEquals("CopticChronology[Asia/Tokyo]",CopticChronology.getInstance(TOKYO).toString());
        assertEquals("CopticChronology[Europe/London]",CopticChronology.getInstance().toString());
        assertEquals("CopticChronology[UTC]",CopticChronology.getInstanceUTC().toString());
    }

    //-----------------------------------------------------------------------
    public void testDurationFields() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("eras",coptic.eras().getName());
        assertEquals("centuries",coptic.centuries().getName());
        assertEquals("years",coptic.years().getName());
        assertEquals("weekyears",coptic.weekyears().getName());
        assertEquals("months",coptic.months().getName());
        assertEquals("weeks",coptic.weeks().getName());
        assertEquals("days",coptic.days().getName());
        assertEquals("halfdays",coptic.halfdays().getName());
        assertEquals("hours",coptic.hours().getName());
        assertEquals("minutes",coptic.minutes().getName());
        assertEquals("seconds",coptic.seconds().getName());
        assertEquals("millis",coptic.millis().getName());
        
        assertEquals(false,coptic.eras().isSupported());
        assertEquals(true,coptic.centuries().isSupported());
        assertEquals(true,coptic.years().isSupported());
        assertEquals(true,coptic.weekyears().isSupported());
        assertEquals(true,coptic.months().isSupported());
        assertEquals(true,coptic.weeks().isSupported());
        assertEquals(true,coptic.days().isSupported());
        assertEquals(true,coptic.halfdays().isSupported());
        assertEquals(true,coptic.hours().isSupported());
        assertEquals(true,coptic.minutes().isSupported());
        assertEquals(true,coptic.seconds().isSupported());
        assertEquals(true,coptic.millis().isSupported());
        
        assertEquals(false,coptic.centuries().isPrecise());
        assertEquals(false,coptic.years().isPrecise());
        assertEquals(false,coptic.weekyears().isPrecise());
        assertEquals(false,coptic.months().isPrecise());
        assertEquals(false,coptic.weeks().isPrecise());
        assertEquals(false,coptic.days().isPrecise());
        assertEquals(false,coptic.halfdays().isPrecise());
        assertEquals(true,coptic.hours().isPrecise());
        assertEquals(true,coptic.minutes().isPrecise());
        assertEquals(true,coptic.seconds().isPrecise());
        assertEquals(true,coptic.millis().isPrecise());
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(false,copticUTC.centuries().isPrecise());
        assertEquals(false,copticUTC.years().isPrecise());
        assertEquals(false,copticUTC.weekyears().isPrecise());
        assertEquals(false,copticUTC.months().isPrecise());
        assertEquals(true,copticUTC.weeks().isPrecise());
        assertEquals(true,copticUTC.days().isPrecise());
        assertEquals(true,copticUTC.halfdays().isPrecise());
        assertEquals(true,copticUTC.hours().isPrecise());
        assertEquals(true,copticUTC.minutes().isPrecise());
        assertEquals(true,copticUTC.seconds().isPrecise());
        assertEquals(true,copticUTC.millis().isPrecise());
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(false,copticGMT.centuries().isPrecise());
        assertEquals(false,copticGMT.years().isPrecise());
        assertEquals(false,copticGMT.weekyears().isPrecise());
        assertEquals(false,copticGMT.months().isPrecise());
        assertEquals(true,copticGMT.weeks().isPrecise());
        assertEquals(true,copticGMT.days().isPrecise());
        assertEquals(true,copticGMT.halfdays().isPrecise());
        assertEquals(true,copticGMT.hours().isPrecise());
        assertEquals(true,copticGMT.minutes().isPrecise());
        assertEquals(true,copticGMT.seconds().isPrecise());
        assertEquals(true,copticGMT.millis().isPrecise());
    }

    public void testDateFields() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("era",coptic.era().getName());
        assertEquals("centuryOfEra",coptic.centuryOfEra().getName());
        assertEquals("yearOfCentury",coptic.yearOfCentury().getName());
        assertEquals("yearOfEra",coptic.yearOfEra().getName());
        assertEquals("year",coptic.year().getName());
        assertEquals("monthOfYear",coptic.monthOfYear().getName());
        assertEquals("weekyearOfCentury",coptic.weekyearOfCentury().getName());
        assertEquals("weekyear",coptic.weekyear().getName());
        assertEquals("weekOfWeekyear",coptic.weekOfWeekyear().getName());
        assertEquals("dayOfYear",coptic.dayOfYear().getName());
        assertEquals("dayOfMonth",coptic.dayOfMonth().getName());
        assertEquals("dayOfWeek",coptic.dayOfWeek().getName());
        
        assertEquals(true,coptic.era().isSupported());
        assertEquals(true,coptic.centuryOfEra().isSupported());
        assertEquals(true,coptic.yearOfCentury().isSupported());
        assertEquals(true,coptic.yearOfEra().isSupported());
        assertEquals(true,coptic.year().isSupported());
        assertEquals(true,coptic.monthOfYear().isSupported());
        assertEquals(true,coptic.weekyearOfCentury().isSupported());
        assertEquals(true,coptic.weekyear().isSupported());
        assertEquals(true,coptic.weekOfWeekyear().isSupported());
        assertEquals(true,coptic.dayOfYear().isSupported());
        assertEquals(true,coptic.dayOfMonth().isSupported());
        assertEquals(true,coptic.dayOfWeek().isSupported());
        
        assertEquals(coptic.eras(),coptic.era().getDurationField());
        assertEquals(coptic.centuries(),coptic.centuryOfEra().getDurationField());
        assertEquals(coptic.years(),coptic.yearOfCentury().getDurationField());
        assertEquals(coptic.years(),coptic.yearOfEra().getDurationField());
        assertEquals(coptic.years(),coptic.year().getDurationField());
        assertEquals(coptic.months(),coptic.monthOfYear().getDurationField());
        assertEquals(coptic.weekyears(),coptic.weekyearOfCentury().getDurationField());
        assertEquals(coptic.weekyears(),coptic.weekyear().getDurationField());
        assertEquals(coptic.weeks(),coptic.weekOfWeekyear().getDurationField());
        assertEquals(coptic.days(),coptic.dayOfYear().getDurationField());
        assertEquals(coptic.days(),coptic.dayOfMonth().getDurationField());
        assertEquals(coptic.days(),coptic.dayOfWeek().getDurationField());
        
        assertEquals(null,coptic.era().getRangeDurationField());
        assertEquals(coptic.eras(),coptic.centuryOfEra().getRangeDurationField());
        assertEquals(coptic.centuries(),coptic.yearOfCentury().getRangeDurationField());
        assertEquals(coptic.eras(),coptic.yearOfEra().getRangeDurationField());
        assertEquals(null,coptic.year().getRangeDurationField());
        assertEquals(coptic.years(),coptic.monthOfYear().getRangeDurationField());
        assertEquals(coptic.centuries(),coptic.weekyearOfCentury().getRangeDurationField());
        assertEquals(null,coptic.weekyear().getRangeDurationField());
        assertEquals(coptic.weekyears(),coptic.weekOfWeekyear().getRangeDurationField());
        assertEquals(coptic.years(),coptic.dayOfYear().getRangeDurationField());
        assertEquals(coptic.months(),coptic.dayOfMonth().getRangeDurationField());
        assertEquals(coptic.weeks(),coptic.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("halfdayOfDay",coptic.halfdayOfDay().getName());
        assertEquals("clockhourOfHalfday",coptic.clockhourOfHalfday().getName());
        assertEquals("hourOfHalfday",coptic.hourOfHalfday().getName());
        assertEquals("clockhourOfDay",coptic.clockhourOfDay().getName());
        assertEquals("hourOfDay",coptic.hourOfDay().getName());
        assertEquals("minuteOfDay",coptic.minuteOfDay().getName());
        assertEquals("minuteOfHour",coptic.minuteOfHour().getName());
        assertEquals("secondOfDay",coptic.secondOfDay().getName());
        assertEquals("secondOfMinute",coptic.secondOfMinute().getName());
        assertEquals("millisOfDay",coptic.millisOfDay().getName());
        assertEquals("millisOfSecond",coptic.millisOfSecond().getName());
        
        assertEquals(true,coptic.halfdayOfDay().isSupported());
        assertEquals(true,coptic.clockhourOfHalfday().isSupported());
        assertEquals(true,coptic.hourOfHalfday().isSupported());
        assertEquals(true,coptic.clockhourOfDay().isSupported());
        assertEquals(true,coptic.hourOfDay().isSupported());
        assertEquals(true,coptic.minuteOfDay().isSupported());
        assertEquals(true,coptic.minuteOfHour().isSupported());
        assertEquals(true,coptic.secondOfDay().isSupported());
        assertEquals(true,coptic.secondOfMinute().isSupported());
        assertEquals(true,coptic.millisOfDay().isSupported());
        assertEquals(true,coptic.millisOfSecond().isSupported());
    }

    //-----------------------------------------------------------------------
    public void testEpoch() {
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, COPTIC_UTC);
        assertEquals(new DateTime(284,8,29,0,0,0,0,JULIAN_UTC),epoch.withChronology(JULIAN_UTC));
    }

    public void testEra() {
        assertEquals(1,CopticChronology.AM);
        try {
            new DateTime(-1, 13, 5, 0, 0, 0, 0, COPTIC_UTC);
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
        System.out.println("\nTestCopticChronology.testCalendar");
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, COPTIC_UTC);
        long millis = epoch.getMillis();
        long end = new DateTime(3000, 1, 1, 0, 0, 0, 0, ISO_UTC).getMillis();
        DateTimeField dayOfWeek = COPTIC_UTC.dayOfWeek();
        DateTimeField dayOfYear = COPTIC_UTC.dayOfYear();
        DateTimeField dayOfMonth = COPTIC_UTC.dayOfMonth();
        DateTimeField monthOfYear = COPTIC_UTC.monthOfYear();
        DateTimeField year = COPTIC_UTC.year();
        DateTimeField yearOfEra = COPTIC_UTC.yearOfEra();
        DateTimeField era = COPTIC_UTC.era();
        int expectedDOW = new DateTime(284, 8, 29, 0, 0, 0, 0, JULIAN_UTC).getDayOfWeek();
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
            assertEquals("AM",era.getAsText(millis));
            assertEquals("AM",era.getAsShortText(millis));
            
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
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        assertEquals(CopticChronology.AM,dt.getEra());
        assertEquals(18,dt.getCenturyOfEra());// TODO confirm assertEquals(20,dt.getYearOfCentury());
        assertEquals(1720,dt.getYearOfEra());
        
        assertEquals(1720,dt.getYear());
        Property fld = dt.year();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
        assertEquals(new DateTime(1721,10,2,0,0,0,0,COPTIC_UTC),fld.addToCopy(1));
        
        assertEquals(10,dt.getMonthOfYear());
        fld = dt.monthOfYear();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(13,fld.getMaximumValue());
        assertEquals(13,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1721,1,2,0,0,0,0,COPTIC_UTC),fld.addToCopy(4));
        assertEquals(new DateTime(1720,1,2,0,0,0,0,COPTIC_UTC),fld.addWrapFieldToCopy(4));
        
        assertEquals(2,dt.getDayOfMonth());
        fld = dt.dayOfMonth();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(30,fld.getMaximumValue());
        assertEquals(30,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1720,10,3,0,0,0,0,COPTIC_UTC),fld.addToCopy(1));
        
        assertEquals(DateTimeConstants.WEDNESDAY,dt.getDayOfWeek());
        fld = dt.dayOfWeek();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(7,fld.getMaximumValue());
        assertEquals(7,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1720,10,3,0,0,0,0,COPTIC_UTC),fld.addToCopy(1));
        
        assertEquals(9 * 30 + 2,dt.getDayOfYear());
        fld = dt.dayOfYear();
        assertEquals(false,fld.isLeap());
        assertEquals(0,fld.getLeapAmount());
        assertEquals(null,fld.getLeapDurationField());
        assertEquals(1,fld.getMinimumValue());
        assertEquals(1,fld.getMinimumValueOverall());
        assertEquals(365,fld.getMaximumValue());
        assertEquals(366,fld.getMaximumValueOverall());
        assertEquals(new DateTime(1720,10,3,0,0,0,0,COPTIC_UTC),fld.addToCopy(1));
        
        assertEquals(0,dt.getHourOfDay());
        assertEquals(0,dt.getMinuteOfHour());
        assertEquals(0,dt.getSecondOfMinute());
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testSampleDateWithZone() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(COPTIC_UTC);
        assertEquals(CopticChronology.AM,dt.getEra());
        assertEquals(1720,dt.getYear());
        assertEquals(1720,dt.getYearOfEra());
        assertEquals(10,dt.getMonthOfYear());
        assertEquals(2,dt.getDayOfMonth());
        assertEquals(10,dt.getHourOfDay());// PARIS is UTC+2 in summer(12-2=10)assertEquals(0,dt.getMinuteOfHour());
        assertEquals(0,dt.getSecondOfMinute());
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testDurationYear() {
        // Leap 1723
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        assertEquals(COPTIC_UTC.years(),fld);
        assertEquals(1L * 365L * MILLIS_PER_DAY,fld.getMillis(1,dt20.getMillis()));
        assertEquals(2L * 365L * MILLIS_PER_DAY,fld.getMillis(2,dt20.getMillis()));
        assertEquals(3L * 365L * MILLIS_PER_DAY,fld.getMillis(3,dt20.getMillis()));
        assertEquals((4L * 365L + 1L)* MILLIS_PER_DAY,fld.getMillis(4,dt20.getMillis()));
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getMillis(1));
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 2,fld.getMillis(2));
        
        assertEquals(1L * 365L * MILLIS_PER_DAY,fld.getMillis(1L,dt20.getMillis()));
        assertEquals(2L * 365L * MILLIS_PER_DAY,fld.getMillis(2L,dt20.getMillis()));
        assertEquals(3L * 365L * MILLIS_PER_DAY,fld.getMillis(3L,dt20.getMillis()));
        assertEquals((4L * 365L + 1L)* MILLIS_PER_DAY,fld.getMillis(4L,dt20.getMillis()));
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getMillis(1L));
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 2,fld.getMillis(2L));
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getUnitMillis());
        
        assertEquals(0,fld.getValue(1L * 365L * MILLIS_PER_DAY - 1L,dt20.getMillis()));
        assertEquals(1,fld.getValue(1L * 365L * MILLIS_PER_DAY,dt20.getMillis()));
        assertEquals(1,fld.getValue(1L * 365L * MILLIS_PER_DAY + 1L,dt20.getMillis()));
        assertEquals(1,fld.getValue(2L * 365L * MILLIS_PER_DAY - 1L,dt20.getMillis()));
        assertEquals(2,fld.getValue(2L * 365L * MILLIS_PER_DAY,dt20.getMillis()));
        assertEquals(2,fld.getValue(2L * 365L * MILLIS_PER_DAY + 1L,dt20.getMillis()));
        assertEquals(2,fld.getValue(3L * 365L * MILLIS_PER_DAY - 1L,dt20.getMillis()));
        assertEquals(3,fld.getValue(3L * 365L * MILLIS_PER_DAY,dt20.getMillis()));
        assertEquals(3,fld.getValue(3L * 365L * MILLIS_PER_DAY + 1L,dt20.getMillis()));
        assertEquals(3,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY - 1L,dt20.getMillis()));
        assertEquals(4,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY,dt20.getMillis()));
        assertEquals(4,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY + 1L,dt20.getMillis()));
        
        assertEquals(dt21.getMillis(),fld.add(dt20.getMillis(),1));
        assertEquals(dt22.getMillis(),fld.add(dt20.getMillis(),2));
        assertEquals(dt23.getMillis(),fld.add(dt20.getMillis(),3));
        assertEquals(dt24.getMillis(),fld.add(dt20.getMillis(),4));
        
        assertEquals(dt21.getMillis(),fld.add(dt20.getMillis(),1L));
        assertEquals(dt22.getMillis(),fld.add(dt20.getMillis(),2L));
        assertEquals(dt23.getMillis(),fld.add(dt20.getMillis(),3L));
        assertEquals(dt24.getMillis(),fld.add(dt20.getMillis(),4L));
    }

    public void testDurationMonth() {
        // Leap 1723
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals(COPTIC_UTC.months(),fld);
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
        Chronology chrono = CopticChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 5, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(false,dt.dayOfMonth().isLeap());
        assertEquals(false,dt.dayOfYear().isLeap());
    }

    public void testLeap_6_13() {
        Chronology chrono = CopticChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 6, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(true,dt.dayOfMonth().isLeap());
        assertEquals(true,dt.dayOfYear().isLeap());
    }

    public void testFactoryUTC_1_oe() {
        Object a = DateTimeZone.UTC;
        assertEquals(a, CopticChronology.getInstanceUTC().getZone());
    }

    public void testFactoryUTC_2_oe() {
        Object a = CopticChronology.class;
        assertSame(a, CopticChronology.getInstanceUTC().getClass());
    }

    public void testFactory_1_oe() {
        Object a = LONDON;
        assertEquals(a, CopticChronology.getInstance().getZone());
    }

    public void testFactory_2_oe() {
        Object a = CopticChronology.class;
        assertSame(a, CopticChronology.getInstance().getClass());
    }

    public void testFactory_Zone_1_oe() {
        Object a = TOKYO;
        assertEquals(a, CopticChronology.getInstance(TOKYO).getZone());
    }

    public void testFactory_Zone_2_oe() {
        Object a = PARIS;
        assertEquals(a, CopticChronology.getInstance(PARIS).getZone());
    }

    public void testFactory_Zone_3_oe() {
        Object a = LONDON;
        assertEquals(a, CopticChronology.getInstance(null).getZone());
    }

    public void testFactory_Zone_4_oe() {
        Object a = CopticChronology.class;
        assertSame(a, CopticChronology.getInstance(TOKYO).getClass());
    }

    public void testEquality_1_oe() {
        Object a = CopticChronology.getInstance(TOKYO);
        assertSame(a, CopticChronology.getInstance(TOKYO));
    }

    public void testEquality_2_oe() {
        Object a = CopticChronology.getInstance(LONDON);
        assertSame(a, CopticChronology.getInstance(LONDON));
    }

    public void testEquality_3_oe() {
        Object a = CopticChronology.getInstance(PARIS);
        assertSame(a, CopticChronology.getInstance(PARIS));
    }

    public void testEquality_4_oe() {
        Object a = CopticChronology.getInstanceUTC();
        assertSame(a, CopticChronology.getInstanceUTC());
    }

    public void testEquality_5_oe() {
        Object a = CopticChronology.getInstance();
        assertSame(a, CopticChronology.getInstance(LONDON));
    }

    public void testWithUTC_1_oe() {
        Object a = CopticChronology.getInstanceUTC();
        assertSame(a, CopticChronology.getInstance(LONDON).withUTC());
    }

    public void testWithUTC_2_oe() {
        Object a = CopticChronology.getInstanceUTC();
        assertSame(a, CopticChronology.getInstance(TOKYO).withUTC());
    }

    public void testWithUTC_3_oe() {
        Object a = CopticChronology.getInstanceUTC();
        assertSame(a, CopticChronology.getInstanceUTC().withUTC());
    }

    public void testWithUTC_4_oe() {
        Object a = CopticChronology.getInstanceUTC();
        assertSame(a, CopticChronology.getInstance().withUTC());
    }

    public void testWithZone_1_oe() {
        Object a = CopticChronology.getInstance(TOKYO);
        assertSame(a, CopticChronology.getInstance(TOKYO).withZone(TOKYO));
    }

    public void testWithZone_2_oe() {
        Object a = CopticChronology.getInstance(LONDON);
        assertSame(a, CopticChronology.getInstance(TOKYO).withZone(LONDON));
    }

    public void testWithZone_3_oe() {
        Object a = CopticChronology.getInstance(PARIS);
        assertSame(a, CopticChronology.getInstance(TOKYO).withZone(PARIS));
    }

    public void testWithZone_4_oe() {
        Object a = CopticChronology.getInstance(LONDON);
        assertSame(a, CopticChronology.getInstance(TOKYO).withZone(null));
    }

    public void testWithZone_5_oe() {
        Object a = CopticChronology.getInstance(PARIS);
        assertSame(a, CopticChronology.getInstance().withZone(PARIS));
    }

    public void testWithZone_6_oe() {
        Object a = CopticChronology.getInstance(PARIS);
        assertSame(a, CopticChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString_1_oe() {
        Object a = CopticChronology.getInstance(LONDON).toString();
        assertEquals("CopticChronology[Europe/London]", a);
    }

    public void testToString_2_oe() {
        Object a = CopticChronology.getInstance(TOKYO).toString();
        assertEquals("CopticChronology[Asia/Tokyo]", a);
    }

    public void testToString_3_oe() {
        Object a = CopticChronology.getInstance().toString();
        assertEquals("CopticChronology[Europe/London]", a);
    }

    public void testToString_4_oe() {
        Object a = CopticChronology.getInstanceUTC().toString();
        assertEquals("CopticChronology[UTC]", a);
    }

    public void testDurationFields_1_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("eras",coptic.eras().getName());
    }

    public void testDurationFields_2_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("centuries",coptic.centuries().getName());
    }

    public void testDurationFields_3_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("years",coptic.years().getName());
    }

    public void testDurationFields_4_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("weekyears",coptic.weekyears().getName());
    }

    public void testDurationFields_5_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("months",coptic.months().getName());
    }

    public void testDurationFields_6_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("weeks",coptic.weeks().getName());
    }

    public void testDurationFields_7_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("days",coptic.days().getName());
    }

    public void testDurationFields_8_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("halfdays",coptic.halfdays().getName());
    }

    public void testDurationFields_9_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("hours",coptic.hours().getName());
    }

    public void testDurationFields_10_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("minutes",coptic.minutes().getName());
    }

    public void testDurationFields_11_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("seconds",coptic.seconds().getName());
    }

    public void testDurationFields_12_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("millis",coptic.millis().getName());
    }

    public void testDurationFields_13_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(false,coptic.eras().isSupported());
    }

    public void testDurationFields_14_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.centuries().isSupported());
    }

    public void testDurationFields_15_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.years().isSupported());
    }

    public void testDurationFields_16_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.weekyears().isSupported());
    }

    public void testDurationFields_17_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.months().isSupported());
    }

    public void testDurationFields_18_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.weeks().isSupported());
    }

    public void testDurationFields_19_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.days().isSupported());
    }

    public void testDurationFields_20_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.halfdays().isSupported());
    }

    public void testDurationFields_21_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.hours().isSupported());
    }

    public void testDurationFields_22_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.minutes().isSupported());
    }

    public void testDurationFields_23_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.seconds().isSupported());
    }

    public void testDurationFields_24_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.millis().isSupported());
    }

    public void testDurationFields_25_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(false,coptic.centuries().isPrecise());
    }

    public void testDurationFields_26_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(false,coptic.years().isPrecise());
    }

    public void testDurationFields_27_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(false,coptic.weekyears().isPrecise());
    }

    public void testDurationFields_28_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(false,coptic.months().isPrecise());
    }

    public void testDurationFields_29_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(false,coptic.weeks().isPrecise());
    }

    public void testDurationFields_30_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(false,coptic.days().isPrecise());
    }

    public void testDurationFields_31_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(false,coptic.halfdays().isPrecise());
    }

    public void testDurationFields_32_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(true,coptic.hours().isPrecise());
    }

    public void testDurationFields_33_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(true,coptic.minutes().isPrecise());
    }

    public void testDurationFields_34_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(true,coptic.seconds().isPrecise());
    }

    public void testDurationFields_35_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(true,coptic.millis().isPrecise());
    }

    public void testDurationFields_36_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(false,copticUTC.centuries().isPrecise());
    }

    public void testDurationFields_37_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(false,copticUTC.years().isPrecise());
    }

    public void testDurationFields_38_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(false,copticUTC.weekyears().isPrecise());
    }

    public void testDurationFields_39_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(false,copticUTC.months().isPrecise());
    }

    public void testDurationFields_40_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(true,copticUTC.weeks().isPrecise());
    }

    public void testDurationFields_41_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(true,copticUTC.days().isPrecise());
    }

    public void testDurationFields_42_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(true,copticUTC.halfdays().isPrecise());
    }

    public void testDurationFields_43_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(true,copticUTC.hours().isPrecise());
    }

    public void testDurationFields_44_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(true,copticUTC.minutes().isPrecise());
    }

    public void testDurationFields_45_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(true,copticUTC.seconds().isPrecise());
    }

    public void testDurationFields_46_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        assertEquals(true,copticUTC.millis().isPrecise());
    }

    public void testDurationFields_47_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(false,copticGMT.centuries().isPrecise());
    }

    public void testDurationFields_48_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(false,copticGMT.years().isPrecise());
    }

    public void testDurationFields_49_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(false,copticGMT.weekyears().isPrecise());
    }

    public void testDurationFields_50_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(false,copticGMT.months().isPrecise());
    }

    public void testDurationFields_51_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(true,copticGMT.weeks().isPrecise());
    }

    public void testDurationFields_52_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(true,copticGMT.days().isPrecise());
    }

    public void testDurationFields_53_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(true,copticGMT.halfdays().isPrecise());
    }

    public void testDurationFields_54_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(true,copticGMT.hours().isPrecise());
    }

    public void testDurationFields_55_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(true,copticGMT.minutes().isPrecise());
    }

    public void testDurationFields_56_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(true,copticGMT.seconds().isPrecise());
    }

    public void testDurationFields_57_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        final CopticChronology copticUTC = CopticChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final CopticChronology copticGMT = CopticChronology.getInstance(gmt);
        assertEquals(true,copticGMT.millis().isPrecise());
    }

    public void testDateFields_1_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("era",coptic.era().getName());
    }

    public void testDateFields_2_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("centuryOfEra",coptic.centuryOfEra().getName());
    }

    public void testDateFields_3_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("yearOfCentury",coptic.yearOfCentury().getName());
    }

    public void testDateFields_4_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("yearOfEra",coptic.yearOfEra().getName());
    }

    public void testDateFields_5_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("year",coptic.year().getName());
    }

    public void testDateFields_6_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("monthOfYear",coptic.monthOfYear().getName());
    }

    public void testDateFields_7_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("weekyearOfCentury",coptic.weekyearOfCentury().getName());
    }

    public void testDateFields_8_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("weekyear",coptic.weekyear().getName());
    }

    public void testDateFields_9_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("weekOfWeekyear",coptic.weekOfWeekyear().getName());
    }

    public void testDateFields_10_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("dayOfYear",coptic.dayOfYear().getName());
    }

    public void testDateFields_11_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("dayOfMonth",coptic.dayOfMonth().getName());
    }

    public void testDateFields_12_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("dayOfWeek",coptic.dayOfWeek().getName());
    }

    public void testDateFields_13_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.era().isSupported());
    }

    public void testDateFields_14_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.centuryOfEra().isSupported());
    }

    public void testDateFields_15_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.yearOfCentury().isSupported());
    }

    public void testDateFields_16_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.yearOfEra().isSupported());
    }

    public void testDateFields_17_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.year().isSupported());
    }

    public void testDateFields_18_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.monthOfYear().isSupported());
    }

    public void testDateFields_19_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.weekyearOfCentury().isSupported());
    }

    public void testDateFields_20_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.weekyear().isSupported());
    }

    public void testDateFields_21_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.weekOfWeekyear().isSupported());
    }

    public void testDateFields_22_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.dayOfYear().isSupported());
    }

    public void testDateFields_23_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.dayOfMonth().isSupported());
    }

    public void testDateFields_24_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.dayOfWeek().isSupported());
    }

    public void testDateFields_25_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.eras(),coptic.era().getDurationField());
    }

    public void testDateFields_26_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.centuries(),coptic.centuryOfEra().getDurationField());
    }

    public void testDateFields_27_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.years(),coptic.yearOfCentury().getDurationField());
    }

    public void testDateFields_28_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.years(),coptic.yearOfEra().getDurationField());
    }

    public void testDateFields_29_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.years(),coptic.year().getDurationField());
    }

    public void testDateFields_30_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.months(),coptic.monthOfYear().getDurationField());
    }

    public void testDateFields_31_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.weekyears(),coptic.weekyearOfCentury().getDurationField());
    }

    public void testDateFields_32_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.weekyears(),coptic.weekyear().getDurationField());
    }

    public void testDateFields_33_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.weeks(),coptic.weekOfWeekyear().getDurationField());
    }

    public void testDateFields_34_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.days(),coptic.dayOfYear().getDurationField());
    }

    public void testDateFields_35_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.days(),coptic.dayOfMonth().getDurationField());
    }

    public void testDateFields_36_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        assertEquals(coptic.days(),coptic.dayOfWeek().getDurationField());
    }

    public void testDateFields_37_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(null,coptic.era().getRangeDurationField());
    }

    public void testDateFields_38_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(coptic.eras(),coptic.centuryOfEra().getRangeDurationField());
    }

    public void testDateFields_39_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(coptic.centuries(),coptic.yearOfCentury().getRangeDurationField());
    }

    public void testDateFields_40_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(coptic.eras(),coptic.yearOfEra().getRangeDurationField());
    }

    public void testDateFields_41_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(null,coptic.year().getRangeDurationField());
    }

    public void testDateFields_42_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(coptic.years(),coptic.monthOfYear().getRangeDurationField());
    }

    public void testDateFields_43_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(coptic.centuries(),coptic.weekyearOfCentury().getRangeDurationField());
    }

    public void testDateFields_44_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(null,coptic.weekyear().getRangeDurationField());
    }

    public void testDateFields_45_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(coptic.weekyears(),coptic.weekOfWeekyear().getRangeDurationField());
    }

    public void testDateFields_46_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(coptic.years(),coptic.dayOfYear().getRangeDurationField());
    }

    public void testDateFields_47_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(coptic.months(),coptic.dayOfMonth().getRangeDurationField());
    }

    public void testDateFields_48_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        
        
        assertEquals(coptic.weeks(),coptic.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields_1_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("halfdayOfDay",coptic.halfdayOfDay().getName());
    }

    public void testTimeFields_2_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("clockhourOfHalfday",coptic.clockhourOfHalfday().getName());
    }

    public void testTimeFields_3_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("hourOfHalfday",coptic.hourOfHalfday().getName());
    }

    public void testTimeFields_4_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("clockhourOfDay",coptic.clockhourOfDay().getName());
    }

    public void testTimeFields_5_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("hourOfDay",coptic.hourOfDay().getName());
    }

    public void testTimeFields_6_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("minuteOfDay",coptic.minuteOfDay().getName());
    }

    public void testTimeFields_7_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("minuteOfHour",coptic.minuteOfHour().getName());
    }

    public void testTimeFields_8_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("secondOfDay",coptic.secondOfDay().getName());
    }

    public void testTimeFields_9_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("secondOfMinute",coptic.secondOfMinute().getName());
    }

    public void testTimeFields_10_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("millisOfDay",coptic.millisOfDay().getName());
    }

    public void testTimeFields_11_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        assertEquals("millisOfSecond",coptic.millisOfSecond().getName());
    }

    public void testTimeFields_12_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.halfdayOfDay().isSupported());
    }

    public void testTimeFields_13_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.clockhourOfHalfday().isSupported());
    }

    public void testTimeFields_14_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.hourOfHalfday().isSupported());
    }

    public void testTimeFields_15_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.clockhourOfDay().isSupported());
    }

    public void testTimeFields_16_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.hourOfDay().isSupported());
    }

    public void testTimeFields_17_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.minuteOfDay().isSupported());
    }

    public void testTimeFields_18_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.minuteOfHour().isSupported());
    }

    public void testTimeFields_19_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.secondOfDay().isSupported());
    }

    public void testTimeFields_20_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.secondOfMinute().isSupported());
    }

    public void testTimeFields_21_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.millisOfDay().isSupported());
    }

    public void testTimeFields_22_oe() {
        final CopticChronology coptic = CopticChronology.getInstance();
        
        assertEquals(true,coptic.millisOfSecond().isSupported());
    }

    public void testEpoch_1_oe() {
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, COPTIC_UTC);
        assertEquals(new DateTime(284,8,29,0,0,0,0,JULIAN_UTC),epoch.withChronology(JULIAN_UTC));
    }

    public void testEra_1_oe() {
        int a = 1;
        assertEquals(a, CopticChronology.AM);
    }

    public void testSampleDate_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        assertEquals(CopticChronology.AM,dt.getEra());
    }

    public void testSampleDate_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        assertEquals(18,dt.getCenturyOfEra());// TODO confirm assertEquals(20,dt.getYearOfCentury());
    }

    public void testSampleDate_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        assertEquals(1720,dt.getYearOfEra());
    }

    public void testSampleDate_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        assertEquals(1720,dt.getYear());
    }

    public void testSampleDate_5_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate_6_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate_7_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
    }

    public void testSampleDate_8_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        assertEquals(new DateTime(1721,10,2,0,0,0,0,COPTIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate_9_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        assertEquals(10,dt.getMonthOfYear());
    }

    public void testSampleDate_10_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate_11_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate_12_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(DurationFieldType.days(),fld.getLeapDurationField().getType());
    }

    public void testSampleDate_13_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate_14_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate_15_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(13,fld.getMaximumValue());
    }

    public void testSampleDate_16_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(13,fld.getMaximumValueOverall());
    }

    public void testSampleDate_17_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(new DateTime(1721,1,2,0,0,0,0,COPTIC_UTC),fld.addToCopy(4));
    }

    public void testSampleDate_18_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        assertEquals(new DateTime(1720,1,2,0,0,0,0,COPTIC_UTC),fld.addWrapFieldToCopy(4));
    }

    public void testSampleDate_19_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        assertEquals(2,dt.getDayOfMonth());
    }

    public void testSampleDate_20_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate_21_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate_22_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate_23_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate_24_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate_25_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(30,fld.getMaximumValue());
    }

    public void testSampleDate_26_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(30,fld.getMaximumValueOverall());
    }

    public void testSampleDate_27_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        assertEquals(new DateTime(1720,10,3,0,0,0,0,COPTIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate_28_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        assertEquals(DateTimeConstants.WEDNESDAY,dt.getDayOfWeek());
    }

    public void testSampleDate_29_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate_30_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate_31_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate_32_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate_33_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate_34_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(7,fld.getMaximumValue());
    }

    public void testSampleDate_35_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(7,fld.getMaximumValueOverall());
    }

    public void testSampleDate_36_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        assertEquals(new DateTime(1720,10,3,0,0,0,0,COPTIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate_37_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        assertEquals(9 * 30 + 2,dt.getDayOfYear());
    }

    public void testSampleDate_38_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(false,fld.isLeap());
    }

    public void testSampleDate_39_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(0,fld.getLeapAmount());
    }

    public void testSampleDate_40_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(null,fld.getLeapDurationField());
    }

    public void testSampleDate_41_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(1,fld.getMinimumValue());
    }

    public void testSampleDate_42_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(1,fld.getMinimumValueOverall());
    }

    public void testSampleDate_43_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(365,fld.getMaximumValue());
    }

    public void testSampleDate_44_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(366,fld.getMaximumValueOverall());
    }

    public void testSampleDate_45_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        assertEquals(new DateTime(1720,10,3,0,0,0,0,COPTIC_UTC),fld.addToCopy(1));
    }

    public void testSampleDate_46_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getHourOfDay());
    }

    public void testSampleDate_47_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getMinuteOfHour());
    }

    public void testSampleDate_48_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getSecondOfMinute());
    }

    public void testSampleDate_49_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, ISO_UTC).withChronology(COPTIC_UTC);
        
        Property fld = dt.year();
        
        fld = dt.monthOfYear();
        
        fld = dt.dayOfMonth();
        
        fld = dt.dayOfWeek();
        
        fld = dt.dayOfYear();
        
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testSampleDateWithZone_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(COPTIC_UTC);
        assertEquals(CopticChronology.AM,dt.getEra());
    }

    public void testSampleDateWithZone_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(COPTIC_UTC);
        assertEquals(1720,dt.getYear());
    }

    public void testSampleDateWithZone_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(COPTIC_UTC);
        assertEquals(1720,dt.getYearOfEra());
    }

    public void testSampleDateWithZone_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(COPTIC_UTC);
        assertEquals(10,dt.getMonthOfYear());
    }

    public void testSampleDateWithZone_5_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(COPTIC_UTC);
        assertEquals(2,dt.getDayOfMonth());
    }

    public void testSampleDateWithZone_6_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(COPTIC_UTC);
        assertEquals(10,dt.getHourOfDay());// PARIS is UTC+2 in summer(12-2=10)assertEquals(0,dt.getMinuteOfHour());
    }

    public void testSampleDateWithZone_7_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(COPTIC_UTC);
        assertEquals(0,dt.getSecondOfMinute());
    }

    public void testSampleDateWithZone_8_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 12, 0, 0, 0, PARIS).withChronology(COPTIC_UTC);
        assertEquals(0,dt.getMillisOfSecond());
    }

    public void testDurationYear_1_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        assertEquals(COPTIC_UTC.years(),fld);
    }

    public void testDurationYear_2_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        assertEquals(1L * 365L * MILLIS_PER_DAY,fld.getMillis(1,dt20.getMillis()));
    }

    public void testDurationYear_3_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        assertEquals(2L * 365L * MILLIS_PER_DAY,fld.getMillis(2,dt20.getMillis()));
    }

    public void testDurationYear_4_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        assertEquals(3L * 365L * MILLIS_PER_DAY,fld.getMillis(3,dt20.getMillis()));
    }

    public void testDurationYear_5_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        assertEquals((4L * 365L + 1L)* MILLIS_PER_DAY,fld.getMillis(4,dt20.getMillis()));
    }

    public void testDurationYear_6_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getMillis(1));
    }

    public void testDurationYear_7_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 2,fld.getMillis(2));
    }

    public void testDurationYear_8_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        assertEquals(1L * 365L * MILLIS_PER_DAY,fld.getMillis(1L,dt20.getMillis()));
    }

    public void testDurationYear_9_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        assertEquals(2L * 365L * MILLIS_PER_DAY,fld.getMillis(2L,dt20.getMillis()));
    }

    public void testDurationYear_10_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        assertEquals(3L * 365L * MILLIS_PER_DAY,fld.getMillis(3L,dt20.getMillis()));
    }

    public void testDurationYear_11_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        assertEquals((4L * 365L + 1L)* MILLIS_PER_DAY,fld.getMillis(4L,dt20.getMillis()));
    }

    public void testDurationYear_12_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getMillis(1L));
    }

    public void testDurationYear_13_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 2,fld.getMillis(2L));
    }

    public void testDurationYear_14_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        assertEquals(((4L * 365L + 1L)* MILLIS_PER_DAY)/ 4,fld.getUnitMillis());
    }

    public void testDurationYear_15_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(0,fld.getValue(1L * 365L * MILLIS_PER_DAY - 1L,dt20.getMillis()));
    }

    public void testDurationYear_16_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(1,fld.getValue(1L * 365L * MILLIS_PER_DAY,dt20.getMillis()));
    }

    public void testDurationYear_17_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(1,fld.getValue(1L * 365L * MILLIS_PER_DAY + 1L,dt20.getMillis()));
    }

    public void testDurationYear_18_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(1,fld.getValue(2L * 365L * MILLIS_PER_DAY - 1L,dt20.getMillis()));
    }

    public void testDurationYear_19_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(2,fld.getValue(2L * 365L * MILLIS_PER_DAY,dt20.getMillis()));
    }

    public void testDurationYear_20_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(2,fld.getValue(2L * 365L * MILLIS_PER_DAY + 1L,dt20.getMillis()));
    }

    public void testDurationYear_21_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(2,fld.getValue(3L * 365L * MILLIS_PER_DAY - 1L,dt20.getMillis()));
    }

    public void testDurationYear_22_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(3,fld.getValue(3L * 365L * MILLIS_PER_DAY,dt20.getMillis()));
    }

    public void testDurationYear_23_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(3,fld.getValue(3L * 365L * MILLIS_PER_DAY + 1L,dt20.getMillis()));
    }

    public void testDurationYear_24_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(3,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY - 1L,dt20.getMillis()));
    }

    public void testDurationYear_25_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(4,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY,dt20.getMillis()));
    }

    public void testDurationYear_26_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        assertEquals(4,fld.getValue((4L * 365L + 1L)* MILLIS_PER_DAY + 1L,dt20.getMillis()));
    }

    public void testDurationYear_27_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        
        assertEquals(dt21.getMillis(),fld.add(dt20.getMillis(),1));
    }

    public void testDurationYear_28_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        
        assertEquals(dt22.getMillis(),fld.add(dt20.getMillis(),2));
    }

    public void testDurationYear_29_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        
        assertEquals(dt23.getMillis(),fld.add(dt20.getMillis(),3));
    }

    public void testDurationYear_30_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        
        assertEquals(dt24.getMillis(),fld.add(dt20.getMillis(),4));
    }

    public void testDurationYear_31_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        
        
        assertEquals(dt21.getMillis(),fld.add(dt20.getMillis(),1L));
    }

    public void testDurationYear_32_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        
        
        assertEquals(dt22.getMillis(),fld.add(dt20.getMillis(),2L));
    }

    public void testDurationYear_33_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        
        
        assertEquals(dt23.getMillis(),fld.add(dt20.getMillis(),3L));
    }

    public void testDurationYear_34_oe() {
        DateTime dt20 = new DateTime(1720, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt21 = new DateTime(1721, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt22 = new DateTime(1722, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt23 = new DateTime(1723, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt24 = new DateTime(1724, 10, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt20.year().getDurationField();
        
        
        
        
        
        
        
        assertEquals(dt24.getMillis(),fld.add(dt20.getMillis(),4L));
    }

    public void testDurationMonth_1_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals(COPTIC_UTC.months(),fld);
    }

    public void testDurationMonth_2_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1,dt11.getMillis()));
    }

    public void testDurationMonth_3_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2,dt11.getMillis()));
    }

    public void testDurationMonth_4_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals((2L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(3,dt11.getMillis()));
    }

    public void testDurationMonth_5_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        assertEquals((3L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(4,dt11.getMillis()));
    }

    public void testDurationMonth_6_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1));
    }

    public void testDurationMonth_7_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2));
    }

    public void testDurationMonth_8_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        assertEquals(13L * 30L * MILLIS_PER_DAY,fld.getMillis(13));
    }

    public void testDurationMonth_9_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1L,dt11.getMillis()));
    }

    public void testDurationMonth_10_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2L,dt11.getMillis()));
    }

    public void testDurationMonth_11_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        assertEquals((2L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(3L,dt11.getMillis()));
    }

    public void testDurationMonth_12_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        assertEquals((3L * 30L + 6L)* MILLIS_PER_DAY,fld.getMillis(4L,dt11.getMillis()));
    }

    public void testDurationMonth_13_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        assertEquals(1L * 30L * MILLIS_PER_DAY,fld.getMillis(1L));
    }

    public void testDurationMonth_14_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        assertEquals(2L * 30L * MILLIS_PER_DAY,fld.getMillis(2L));
    }

    public void testDurationMonth_15_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        assertEquals(13L * 30L * MILLIS_PER_DAY,fld.getMillis(13L));
    }

    public void testDurationMonth_16_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(0,fld.getValue(1L * 30L * MILLIS_PER_DAY - 1L,dt11.getMillis()));
    }

    public void testDurationMonth_17_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(1,fld.getValue(1L * 30L * MILLIS_PER_DAY,dt11.getMillis()));
    }

    public void testDurationMonth_18_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(1,fld.getValue(1L * 30L * MILLIS_PER_DAY + 1L,dt11.getMillis()));
    }

    public void testDurationMonth_19_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(1,fld.getValue(2L * 30L * MILLIS_PER_DAY - 1L,dt11.getMillis()));
    }

    public void testDurationMonth_20_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(2,fld.getValue(2L * 30L * MILLIS_PER_DAY,dt11.getMillis()));
    }

    public void testDurationMonth_21_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(2,fld.getValue(2L * 30L * MILLIS_PER_DAY + 1L,dt11.getMillis()));
    }

    public void testDurationMonth_22_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(2,fld.getValue((2L * 30L + 6L)* MILLIS_PER_DAY - 1L,dt11.getMillis()));
    }

    public void testDurationMonth_23_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(3,fld.getValue((2L * 30L + 6L)* MILLIS_PER_DAY,dt11.getMillis()));
    }

    public void testDurationMonth_24_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(3,fld.getValue((2L * 30L + 6L)* MILLIS_PER_DAY + 1L,dt11.getMillis()));
    }

    public void testDurationMonth_25_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(3,fld.getValue((3L * 30L + 6L)* MILLIS_PER_DAY - 1L,dt11.getMillis()));
    }

    public void testDurationMonth_26_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(4,fld.getValue((3L * 30L + 6L)* MILLIS_PER_DAY,dt11.getMillis()));
    }

    public void testDurationMonth_27_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        assertEquals(4,fld.getValue((3L * 30L + 6L)* MILLIS_PER_DAY + 1L,dt11.getMillis()));
    }

    public void testDurationMonth_28_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        assertEquals(dt12.getMillis(),fld.add(dt11.getMillis(),1));
    }

    public void testDurationMonth_29_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        assertEquals(dt13.getMillis(),fld.add(dt11.getMillis(),2));
    }

    public void testDurationMonth_30_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        assertEquals(dt01.getMillis(),fld.add(dt11.getMillis(),3));
    }

    public void testDurationMonth_31_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        
        assertEquals(dt12.getMillis(),fld.add(dt11.getMillis(),1L));
    }

    public void testDurationMonth_32_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        
        assertEquals(dt13.getMillis(),fld.add(dt11.getMillis(),2L));
    }

    public void testDurationMonth_33_oe() {
        DateTime dt11 = new DateTime(1723, 11, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt12 = new DateTime(1723, 12, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt13 = new DateTime(1723, 13, 2, 0, 0, 0, 0, COPTIC_UTC);
        DateTime dt01 = new DateTime(1724, 1, 2, 0, 0, 0, 0, COPTIC_UTC);
        
        DurationField fld = dt11.monthOfYear().getDurationField();
        
        
        
        
        
        
        assertEquals(dt01.getMillis(),fld.add(dt11.getMillis(),3L));
    }

    public void testLeap_5_13_1_oe() {
        Chronology chrono = CopticChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 5, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
    }

    public void testLeap_5_13_2_oe() {
        Chronology chrono = CopticChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 5, 0, 0, chrono);
        assertEquals(true,dt.monthOfYear().isLeap());
    }

    public void testLeap_5_13_3_oe() {
        Chronology chrono = CopticChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 5, 0, 0, chrono);
        assertEquals(false,dt.dayOfMonth().isLeap());
    }

    public void testLeap_5_13_4_oe() {
        Chronology chrono = CopticChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 5, 0, 0, chrono);
        assertEquals(false,dt.dayOfYear().isLeap());
    }

    public void testLeap_6_13_1_oe() {
        Chronology chrono = CopticChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 6, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
    }

    public void testLeap_6_13_2_oe() {
        Chronology chrono = CopticChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 6, 0, 0, chrono);
        assertEquals(true,dt.monthOfYear().isLeap());
    }

    public void testLeap_6_13_3_oe() {
        Chronology chrono = CopticChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 6, 0, 0, chrono);
        assertEquals(true,dt.dayOfMonth().isLeap());
    }

    public void testLeap_6_13_4_oe() {
        Chronology chrono = CopticChronology.getInstance();
        DateTime dt = new DateTime(3, 13, 6, 0, 0, chrono);
        assertEquals(true,dt.dayOfYear().isLeap());
    }

public void testEra_oe_101_oe() {
        try {
            new DateTime(-1, 13, 5, 0, 0, 0, 0, COPTIC_UTC);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

}
