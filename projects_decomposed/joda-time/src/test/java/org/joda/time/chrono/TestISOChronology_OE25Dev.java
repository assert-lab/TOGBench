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
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Partial;
import org.joda.time.TimeOfDay;
import org.joda.time.YearMonthDay;

/**
 * This class is a Junit unit test for ISOChronology.
 *
 * @author Stephen Colebourne
 */
@SuppressWarnings("deprecation")
public class TestISOChronology_OE25Dev extends TestCase {

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
        return new TestSuite(TestISOChronology_OE25Dev.class);
    }

    public TestISOChronology_OE25Dev(String name) {
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
        assertEquals(DateTimeZone.UTC,ISOChronology.getInstanceUTC().getZone());
        assertSame(ISOChronology.class,ISOChronology.getInstanceUTC().getClass());
    }

    public void testFactory() {
        assertEquals(LONDON,ISOChronology.getInstance().getZone());
        assertSame(ISOChronology.class,ISOChronology.getInstance().getClass());
    }

    public void testFactory_Zone() {
        assertEquals(TOKYO,ISOChronology.getInstance(TOKYO).getZone());
        assertEquals(PARIS,ISOChronology.getInstance(PARIS).getZone());
        assertEquals(LONDON,ISOChronology.getInstance(null).getZone());
        assertSame(ISOChronology.class,ISOChronology.getInstance(TOKYO).getClass());
    }

    //-----------------------------------------------------------------------
    public void testEquality() {
        assertSame(ISOChronology.getInstance(TOKYO),ISOChronology.getInstance(TOKYO));
        assertSame(ISOChronology.getInstance(LONDON),ISOChronology.getInstance(LONDON));
        assertSame(ISOChronology.getInstance(PARIS),ISOChronology.getInstance(PARIS));
        assertSame(ISOChronology.getInstanceUTC(),ISOChronology.getInstanceUTC());
        assertSame(ISOChronology.getInstance(),ISOChronology.getInstance(LONDON));
    }

    public void testWithUTC() {
        assertSame(ISOChronology.getInstanceUTC(),ISOChronology.getInstance(LONDON).withUTC());
        assertSame(ISOChronology.getInstanceUTC(),ISOChronology.getInstance(TOKYO).withUTC());
        assertSame(ISOChronology.getInstanceUTC(),ISOChronology.getInstanceUTC().withUTC());
        assertSame(ISOChronology.getInstanceUTC(),ISOChronology.getInstance().withUTC());
    }

    public void testWithZone() {
        assertSame(ISOChronology.getInstance(TOKYO),ISOChronology.getInstance(TOKYO).withZone(TOKYO));
        assertSame(ISOChronology.getInstance(LONDON),ISOChronology.getInstance(TOKYO).withZone(LONDON));
        assertSame(ISOChronology.getInstance(PARIS),ISOChronology.getInstance(TOKYO).withZone(PARIS));
        assertSame(ISOChronology.getInstance(LONDON),ISOChronology.getInstance(TOKYO).withZone(null));
        assertSame(ISOChronology.getInstance(PARIS),ISOChronology.getInstance().withZone(PARIS));
        assertSame(ISOChronology.getInstance(PARIS),ISOChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString() {
        assertEquals("ISOChronology[Europe/London]",ISOChronology.getInstance(LONDON).toString());
        assertEquals("ISOChronology[Asia/Tokyo]",ISOChronology.getInstance(TOKYO).toString());
        assertEquals("ISOChronology[Europe/London]",ISOChronology.getInstance().toString());
        assertEquals("ISOChronology[UTC]",ISOChronology.getInstanceUTC().toString());
    }

    //-----------------------------------------------------------------------
    public void testDurationFields() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("eras",iso.eras().getName());
        assertEquals("centuries",iso.centuries().getName());
        assertEquals("years",iso.years().getName());
        assertEquals("weekyears",iso.weekyears().getName());
        assertEquals("months",iso.months().getName());
        assertEquals("weeks",iso.weeks().getName());
        assertEquals("days",iso.days().getName());
        assertEquals("halfdays",iso.halfdays().getName());
        assertEquals("hours",iso.hours().getName());
        assertEquals("minutes",iso.minutes().getName());
        assertEquals("seconds",iso.seconds().getName());
        assertEquals("millis",iso.millis().getName());
        
        assertEquals(false,iso.eras().isSupported());
        assertEquals(true,iso.centuries().isSupported());
        assertEquals(true,iso.years().isSupported());
        assertEquals(true,iso.weekyears().isSupported());
        assertEquals(true,iso.months().isSupported());
        assertEquals(true,iso.weeks().isSupported());
        assertEquals(true,iso.days().isSupported());
        assertEquals(true,iso.halfdays().isSupported());
        assertEquals(true,iso.hours().isSupported());
        assertEquals(true,iso.minutes().isSupported());
        assertEquals(true,iso.seconds().isSupported());
        assertEquals(true,iso.millis().isSupported());
        
        assertEquals(false,iso.centuries().isPrecise());
        assertEquals(false,iso.years().isPrecise());
        assertEquals(false,iso.weekyears().isPrecise());
        assertEquals(false,iso.months().isPrecise());
        assertEquals(false,iso.weeks().isPrecise());
        assertEquals(false,iso.days().isPrecise());
        assertEquals(false,iso.halfdays().isPrecise());
        assertEquals(true,iso.hours().isPrecise());
        assertEquals(true,iso.minutes().isPrecise());
        assertEquals(true,iso.seconds().isPrecise());
        assertEquals(true,iso.millis().isPrecise());
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals(false,isoUTC.centuries().isPrecise());
        assertEquals(false,isoUTC.years().isPrecise());
        assertEquals(false,isoUTC.weekyears().isPrecise());
        assertEquals(false,isoUTC.months().isPrecise());
        assertEquals(true,isoUTC.weeks().isPrecise());
        assertEquals(true,isoUTC.days().isPrecise());
        assertEquals(true,isoUTC.halfdays().isPrecise());
        assertEquals(true,isoUTC.hours().isPrecise());
        assertEquals(true,isoUTC.minutes().isPrecise());
        assertEquals(true,isoUTC.seconds().isPrecise());
        assertEquals(true,isoUTC.millis().isPrecise());
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals(false,isoGMT.centuries().isPrecise());
        assertEquals(false,isoGMT.years().isPrecise());
        assertEquals(false,isoGMT.weekyears().isPrecise());
        assertEquals(false,isoGMT.months().isPrecise());
        assertEquals(true,isoGMT.weeks().isPrecise());
        assertEquals(true,isoGMT.days().isPrecise());
        assertEquals(true,isoGMT.halfdays().isPrecise());
        assertEquals(true,isoGMT.hours().isPrecise());
        assertEquals(true,isoGMT.minutes().isPrecise());
        assertEquals(true,isoGMT.seconds().isPrecise());
        assertEquals(true,isoGMT.millis().isPrecise());
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals(false,isoOffset1.centuries().isPrecise());
        assertEquals(false,isoOffset1.years().isPrecise());
        assertEquals(false,isoOffset1.weekyears().isPrecise());
        assertEquals(false,isoOffset1.months().isPrecise());
        assertEquals(true,isoOffset1.weeks().isPrecise());
        assertEquals(true,isoOffset1.days().isPrecise());
        assertEquals(true,isoOffset1.halfdays().isPrecise());
        assertEquals(true,isoOffset1.hours().isPrecise());
        assertEquals(true,isoOffset1.minutes().isPrecise());
        assertEquals(true,isoOffset1.seconds().isPrecise());
        assertEquals(true,isoOffset1.millis().isPrecise());
    }

    public void testDateFields() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("era",iso.era().getName());
        assertEquals("centuryOfEra",iso.centuryOfEra().getName());
        assertEquals("yearOfCentury",iso.yearOfCentury().getName());
        assertEquals("yearOfEra",iso.yearOfEra().getName());
        assertEquals("year",iso.year().getName());
        assertEquals("monthOfYear",iso.monthOfYear().getName());
        assertEquals("weekyearOfCentury",iso.weekyearOfCentury().getName());
        assertEquals("weekyear",iso.weekyear().getName());
        assertEquals("weekOfWeekyear",iso.weekOfWeekyear().getName());
        assertEquals("dayOfYear",iso.dayOfYear().getName());
        assertEquals("dayOfMonth",iso.dayOfMonth().getName());
        assertEquals("dayOfWeek",iso.dayOfWeek().getName());
        
        assertEquals(true,iso.era().isSupported());
        assertEquals(true,iso.centuryOfEra().isSupported());
        assertEquals(true,iso.yearOfCentury().isSupported());
        assertEquals(true,iso.yearOfEra().isSupported());
        assertEquals(true,iso.year().isSupported());
        assertEquals(true,iso.monthOfYear().isSupported());
        assertEquals(true,iso.weekyearOfCentury().isSupported());
        assertEquals(true,iso.weekyear().isSupported());
        assertEquals(true,iso.weekOfWeekyear().isSupported());
        assertEquals(true,iso.dayOfYear().isSupported());
        assertEquals(true,iso.dayOfMonth().isSupported());
        assertEquals(true,iso.dayOfWeek().isSupported());
        
        assertEquals(iso.eras(),iso.era().getDurationField());
        assertEquals(iso.centuries(),iso.centuryOfEra().getDurationField());
        assertEquals(iso.years(),iso.yearOfCentury().getDurationField());
        assertEquals(iso.years(),iso.yearOfEra().getDurationField());
        assertEquals(iso.years(),iso.year().getDurationField());
        assertEquals(iso.months(),iso.monthOfYear().getDurationField());
        assertEquals(iso.weekyears(),iso.weekyearOfCentury().getDurationField());
        assertEquals(iso.weekyears(),iso.weekyear().getDurationField());
        assertEquals(iso.weeks(),iso.weekOfWeekyear().getDurationField());
        assertEquals(iso.days(),iso.dayOfYear().getDurationField());
        assertEquals(iso.days(),iso.dayOfMonth().getDurationField());
        assertEquals(iso.days(),iso.dayOfWeek().getDurationField());
        
        assertEquals(null,iso.era().getRangeDurationField());
        assertEquals(iso.eras(),iso.centuryOfEra().getRangeDurationField());
        assertEquals(iso.centuries(),iso.yearOfCentury().getRangeDurationField());
        assertEquals(iso.eras(),iso.yearOfEra().getRangeDurationField());
        assertEquals(null,iso.year().getRangeDurationField());
        assertEquals(iso.years(),iso.monthOfYear().getRangeDurationField());
        assertEquals(iso.centuries(),iso.weekyearOfCentury().getRangeDurationField());
        assertEquals(null,iso.weekyear().getRangeDurationField());
        assertEquals(iso.weekyears(),iso.weekOfWeekyear().getRangeDurationField());
        assertEquals(iso.years(),iso.dayOfYear().getRangeDurationField());
        assertEquals(iso.months(),iso.dayOfMonth().getRangeDurationField());
        assertEquals(iso.weeks(),iso.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("halfdayOfDay",iso.halfdayOfDay().getName());
        assertEquals("clockhourOfHalfday",iso.clockhourOfHalfday().getName());
        assertEquals("hourOfHalfday",iso.hourOfHalfday().getName());
        assertEquals("clockhourOfDay",iso.clockhourOfDay().getName());
        assertEquals("hourOfDay",iso.hourOfDay().getName());
        assertEquals("minuteOfDay",iso.minuteOfDay().getName());
        assertEquals("minuteOfHour",iso.minuteOfHour().getName());
        assertEquals("secondOfDay",iso.secondOfDay().getName());
        assertEquals("secondOfMinute",iso.secondOfMinute().getName());
        assertEquals("millisOfDay",iso.millisOfDay().getName());
        assertEquals("millisOfSecond",iso.millisOfSecond().getName());
        
        assertEquals(true,iso.halfdayOfDay().isSupported());
        assertEquals(true,iso.clockhourOfHalfday().isSupported());
        assertEquals(true,iso.hourOfHalfday().isSupported());
        assertEquals(true,iso.clockhourOfDay().isSupported());
        assertEquals(true,iso.hourOfDay().isSupported());
        assertEquals(true,iso.minuteOfDay().isSupported());
        assertEquals(true,iso.minuteOfHour().isSupported());
        assertEquals(true,iso.secondOfDay().isSupported());
        assertEquals(true,iso.secondOfMinute().isSupported());
        assertEquals(true,iso.millisOfDay().isSupported());
        assertEquals(true,iso.millisOfSecond().isSupported());
    }

    public void testMaxYear() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int maxYear = chrono.year().getMaximumValue();

        DateTime start = new DateTime(maxYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(maxYear, 12, 31, 23, 59, 59, 999, chrono);
        assertTrue(start.getMillis()> 0);
        assertTrue(end.getMillis()> start.getMillis());
        assertEquals(maxYear,start.getYear());
        assertEquals(maxYear,end.getYear());
        long delta = end.getMillis() - start.getMillis();
        long expectedDelta = 
            (start.year().isLeap() ? 366L : 365L) * DateTimeConstants.MILLIS_PER_DAY - 1;
        assertEquals(expectedDelta,delta);

        assertEquals(start,new DateTime(maxYear + "-01-01T00:00:00.000Z",chrono));
        assertEquals(end,new DateTime(maxYear + "-12-31T23:59:59.999Z",chrono));

        try {
            start.plusYears(1);
            fail();
        } catch (IllegalFieldValueException e) {
        }

        try {
            end.plusYears(1);
            fail();
        } catch (IllegalFieldValueException e) {
        }

        assertEquals(maxYear + 1,chrono.year().get(Long.MAX_VALUE));
    }

    public void testMinYear() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int minYear = chrono.year().getMinimumValue();

        DateTime start = new DateTime(minYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(minYear, 12, 31, 23, 59, 59, 999, chrono);
        assertTrue(start.getMillis()< 0);
        assertTrue(end.getMillis()> start.getMillis());
        assertEquals(minYear,start.getYear());
        assertEquals(minYear,end.getYear());
        long delta = end.getMillis() - start.getMillis();
        long expectedDelta = 
            (start.year().isLeap() ? 366L : 365L) * DateTimeConstants.MILLIS_PER_DAY - 1;
        assertEquals(expectedDelta,delta);

        assertEquals(start,new DateTime(minYear + "-01-01T00:00:00.000Z",chrono));
        assertEquals(end,new DateTime(minYear + "-12-31T23:59:59.999Z",chrono));

        try {
            start.minusYears(1);
            fail();
        } catch (IllegalFieldValueException e) {
        }

        try {
            end.minusYears(1);
            fail();
        } catch (IllegalFieldValueException e) {
        }

        assertEquals(minYear - 1,chrono.year().get(Long.MIN_VALUE));
    }

    public void testCutoverAddYears() {
        testAdd("1582-01-01", DurationFieldType.years(), 1, "1583-01-01");
        testAdd("1582-02-15", DurationFieldType.years(), 1, "1583-02-15");
        testAdd("1582-02-28", DurationFieldType.years(), 1, "1583-02-28");
        testAdd("1582-03-01", DurationFieldType.years(), 1, "1583-03-01");
        testAdd("1582-09-30", DurationFieldType.years(), 1, "1583-09-30");
        testAdd("1582-10-01", DurationFieldType.years(), 1, "1583-10-01");
        testAdd("1582-10-04", DurationFieldType.years(), 1, "1583-10-04");
        testAdd("1582-10-15", DurationFieldType.years(), 1, "1583-10-15");
        testAdd("1582-10-16", DurationFieldType.years(), 1, "1583-10-16");
        testAdd("1580-01-01", DurationFieldType.years(), 4, "1584-01-01");
        testAdd("1580-02-29", DurationFieldType.years(), 4, "1584-02-29");
        testAdd("1580-10-01", DurationFieldType.years(), 4, "1584-10-01");
        testAdd("1580-10-10", DurationFieldType.years(), 4, "1584-10-10");
        testAdd("1580-10-15", DurationFieldType.years(), 4, "1584-10-15");
        testAdd("1580-12-31", DurationFieldType.years(), 4, "1584-12-31");
    }

    public void testAddMonths() {
        testAdd("1582-01-01", DurationFieldType.months(), 1, "1582-02-01");
        testAdd("1582-01-01", DurationFieldType.months(), 6, "1582-07-01");
        testAdd("1582-01-01", DurationFieldType.months(), 12, "1583-01-01");
        testAdd("1582-11-15", DurationFieldType.months(), 1, "1582-12-15");
        testAdd("1582-09-04", DurationFieldType.months(), 2, "1582-11-04");
        testAdd("1582-09-05", DurationFieldType.months(), 2, "1582-11-05");
        testAdd("1582-09-10", DurationFieldType.months(), 2, "1582-11-10");
        testAdd("1582-09-15", DurationFieldType.months(), 2, "1582-11-15");
        testAdd("1580-01-01", DurationFieldType.months(), 48, "1584-01-01");
        testAdd("1580-02-29", DurationFieldType.months(), 48, "1584-02-29");
        testAdd("1580-10-01", DurationFieldType.months(), 48, "1584-10-01");
        testAdd("1580-10-10", DurationFieldType.months(), 48, "1584-10-10");
        testAdd("1580-10-15", DurationFieldType.months(), 48, "1584-10-15");
        testAdd("1580-12-31", DurationFieldType.months(), 48, "1584-12-31");
    }

    private void testAdd(String start, DurationFieldType type, int amt, String end) {
        DateTime dtStart = new DateTime(start, ISOChronology.getInstanceUTC());
        DateTime dtEnd = new DateTime(end, ISOChronology.getInstanceUTC());
        assertEquals(dtEnd,dtStart.withFieldAdded(type,amt));
        assertEquals(dtStart,dtEnd.withFieldAdded(type,-amt));

        DurationField field = type.getField(ISOChronology.getInstanceUTC());
        int diff = field.getDifference(dtEnd.getMillis(), dtStart.getMillis());
        assertEquals(amt,diff);
        
        if (type == DurationFieldType.years() ||
            type == DurationFieldType.months() ||
            type == DurationFieldType.days()) {
            YearMonthDay ymdStart = new YearMonthDay(start, ISOChronology.getInstanceUTC());
            YearMonthDay ymdEnd = new YearMonthDay(end, ISOChronology.getInstanceUTC());
            assertEquals(ymdEnd,ymdStart.withFieldAdded(type,amt));
            assertEquals(ymdStart,ymdEnd.withFieldAdded(type,-amt));
        }
    }

    public void testTimeOfDayAdd() {
        TimeOfDay start = new TimeOfDay(12, 30);
        TimeOfDay end = new TimeOfDay(10, 30);
        assertEquals(end,start.plusHours(22));
        assertEquals(start,end.minusHours(22));
        assertEquals(end,start.plusMinutes(22 * 60));
        assertEquals(start,end.minusMinutes(22 * 60));
    }

    public void testPartialDayOfYearAdd() {
        Partial start = new Partial().with(DateTimeFieldType.year(), 2000).with(DateTimeFieldType.dayOfYear(), 366);
        Partial end = new Partial().with(DateTimeFieldType.year(), 2004).with(DateTimeFieldType.dayOfYear(), 366);
        assertEquals(end,start.withFieldAdded(DurationFieldType.days(),365 + 365 + 365 + 366));
        assertEquals(start,end.withFieldAdded(DurationFieldType.days(),-(365 + 365 + 365 + 366)));
    }

    public void testMaximumValue() {
        DateMidnight dt = new DateMidnight(1570, 1, 1);
        while (dt.getYear() < 1590) {
            dt = dt.plusDays(1);
            YearMonthDay ymd = dt.toYearMonthDay();
            assertEquals(dt.year().getMaximumValue(),ymd.year().getMaximumValue());
            assertEquals(dt.monthOfYear().getMaximumValue(),ymd.monthOfYear().getMaximumValue());
            assertEquals(dt.dayOfMonth().getMaximumValue(),ymd.dayOfMonth().getMaximumValue());
        }
    }

    public void testLeap_28feb() {
        Chronology chrono = ISOChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(false,dt.dayOfMonth().isLeap());
        assertEquals(false,dt.dayOfYear().isLeap());
    }

    public void testLeap_29feb() {
        Chronology chrono = ISOChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(true,dt.dayOfMonth().isLeap());
        assertEquals(true,dt.dayOfYear().isLeap());
    }

    public void testFactoryUTC_1_oe() {
        Object a = DateTimeZone.UTC;
        assertNotNull(DateTimeZone.UTC);
    }

    public void testFactoryUTC_2_oe() {
        Object a = ISOChronology.class;
// incorrect assertion         assertNotNull(Chronology.getInstanceUTC());
    }

    public void testFactory_2_oe() {
        Object a = ISOChronology.class;
        assertNotNull(ISOChronology.getInstance());
    }

    public void testFactory_Zone_2_oe() {
        Object a = PARIS;
// incorrect assertion         assertNotNull(Chronology.getInstance());
    }

    public void testFactory_Zone_3_oe() {
        Object a = LONDON;
// incorrect assertion         assertNotNull(getInstance());
    }

    public void testFactory_Zone_4_oe() {
        Object a = ISOChronology.class;
        assertNotNull(ISOChronology.getInstance());
    }

    public void testEquality_1_oe() {
        Object a = ISOChronology.getInstance(TOKYO);
        assertNotNull(a);
    }

    public void testEquality_2_oe() {
        Object a = ISOChronology.getInstance(LONDON);
        assertNotNull(a);
    }

    public void testEquality_3_oe() {
        Object a = ISOChronology.getInstance(PARIS);
        assertNotNull(a);
    }

    public void testEquality_4_oe() {
        Object a = ISOChronology.getInstanceUTC();
        assertNotNull(a);
    }

    public void testEquality_5_oe() {
        Object a = ISOChronology.getInstance();
        assertNotNull(a);
    }

    public void testWithUTC_1_oe() {
        Object a = ISOChronology.getInstanceUTC();
        assertNotNull(a);
    }

    public void testWithUTC_2_oe() {
        Object a = ISOChronology.getInstanceUTC();
        assertNotNull(a);
    }

    public void testWithUTC_3_oe() {
        Object a = ISOChronology.getInstanceUTC();
        assertNotNull(a);
    }

    public void testWithUTC_4_oe() {
        Object a = ISOChronology.getInstanceUTC();
        assertNotNull(a);
    }

    public void testWithZone_1_oe() {
        Object a = ISOChronology.getInstance(TOKYO);
        assertNotNull(a);
    }

    public void testWithZone_2_oe() {
        Object a = ISOChronology.getInstance(LONDON);
        assertNotNull(a);
    }

    public void testWithZone_3_oe() {
        Object a = ISOChronology.getInstance(PARIS);
        assertNotNull(a);
    }

    public void testWithZone_4_oe() {
        Object a = ISOChronology.getInstance(LONDON);
        assertNotNull(a);
    }

    public void testWithZone_5_oe() {
        Object a = ISOChronology.getInstance(PARIS);
        assertNotNull(a);
    }

    public void testWithZone_6_oe() {
        Object a = ISOChronology.getInstance(PARIS);
        assertNotNull(ISOChronology.getInstanceUTC());
    }

    public void testToString_1_oe() {
        Object a = ISOChronology.getInstance(LONDON).toString();
        assertNotNull(a);
    }

    public void testToString_2_oe() {
        Object a = ISOChronology.getInstance(TOKYO).toString();
        assertNotNull(a);
    }

    public void testToString_3_oe() {
        Object a = ISOChronology.getInstance().toString();
        assertNotNull(a);
    }

    public void testToString_4_oe() {
        Object a = ISOChronology.getInstanceUTC().toString();
        assertNotNull(a);
    }

    public void testDurationFields_1_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("eras", iso.eras().getName());
    }

    public void testDurationFields_2_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("centuries", iso.centuries().getName());
    }

    public void testDurationFields_3_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("Years", iso.years().getName());
    }

    public void testDurationFields_4_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("Weekyears", iso.weekyears().getName());
    }

    public void testDurationFields_5_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertNotNull(iso.months());
    }

    public void testDurationFields_6_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("weeks", iso.weeks().getName());
    }

    public void testDurationFields_7_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("Days", iso.days().getName());
    }

    public void testDurationFields_8_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("Halfdays", iso.halfdays().getName());
    }

    public void testDurationFields_9_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("Hours", iso.hours().getName());
    }

    public void testDurationFields_10_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("minutes", iso.minutes().getName());
    }

    public void testDurationFields_11_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("seconds", iso.seconds().getName());
    }

    public void testDurationFields_12_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("millis", iso.millis().getName());
    }

    public void testDurationFields_13_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("eras", iso.eras().getName());
    }

    public void testDurationFields_14_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("centuries", iso.centuries().getName());
    }

    public void testDurationFields_15_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("Years", iso.years().getName());
    }

    public void testDurationFields_16_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("Weekyears", iso.weekyears().getName());
    }

    public void testDurationFields_17_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
// incorrect assertion         assertNotNull(Months.months(0));
    }

    public void testDurationFields_18_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("weeks", iso.weeks().getName());
    }

    public void testDurationFields_19_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("Days", iso.days().getName());
    }

    public void testDurationFields_20_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("Halfdays", iso.halfdays().getName());
    }

    public void testDurationFields_21_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("Hours", iso.hours().getName());
    }

    public void testDurationFields_22_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("minutes", iso.minutes().getName());
    }

    public void testDurationFields_23_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("seconds", iso.seconds().getName());
    }

    public void testDurationFields_24_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("millis", iso.millis().getName());
    }

    public void testDurationFields_25_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("centuries", iso.centuries().getName());
    }

    public void testDurationFields_26_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Years", iso.years().getName());
    }

    public void testDurationFields_27_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Weekyears", iso.weekyears().getName());
    }

    public void testDurationFields_28_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
// incorrect assertion         assertNotNull(Months.months(0));
    }

    public void testDurationFields_29_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("weeks", iso.weeks().getName());
    }

    public void testDurationFields_30_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Days", iso.days().getName());
    }

    public void testDurationFields_31_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Halfdays", iso.halfdays().getName());
    }

    public void testDurationFields_32_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Hours", iso.hours().getName());
    }

    public void testDurationFields_33_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("minutes", iso.minutes().getName());
    }

    public void testDurationFields_34_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("seconds", iso.seconds().getName());
    }

    public void testDurationFields_35_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("millis", iso.millis().getName());
    }

    public void testDurationFields_36_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals("centuries", iso.centuries().getName());
    }

    public void testDurationFields_37_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals("YEARS", iso.years().getName());
    }

    public void testDurationFields_38_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals("weekyears", iso.weekyears().getName());
    }

    public void testDurationFields_39_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertNotNull(isoUTC);
    }

    public void testDurationFields_40_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals("weeks", iso.weeks().getName());
    }

    public void testDurationFields_41_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals("Days", iso.days().getName());
    }

    public void testDurationFields_42_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals("Halfdays", iso.halfdays().getName());
    }

    public void testDurationFields_43_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals("Hours", iso.hours().getName());
    }

    public void testDurationFields_44_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals("Minutes", iso.minutes().getName());
    }

    public void testDurationFields_45_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals("seconds", iso.seconds().getName());
    }

    public void testDurationFields_46_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        assertEquals("millis", iso.millis().getName());
    }

    public void testDurationFields_47_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals("centuries", iso.centuries().getName());
    }

    public void testDurationFields_48_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals("YEARS", iso.years().getName());
    }

    public void testDurationFields_49_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals("weekyears", iso.weekyears().getName());
    }

    public void testDurationFields_50_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertNotNull(isoGMT);
    }

    public void testDurationFields_51_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals("weeks", iso.weeks().getName());
    }

    public void testDurationFields_52_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals("Days", iso.days().getName());
    }

    public void testDurationFields_53_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals("Halfdays", iso.halfdays().getName());
    }

    public void testDurationFields_54_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals("Hours", iso.hours().getName());
    }

    public void testDurationFields_55_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals("Minutes", isoGMT.minutes().getName());
    }

    public void testDurationFields_56_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals("seconds", iso.seconds().getName());
    }

    public void testDurationFields_57_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        assertEquals("millis", iso.millis().getName());
    }

    public void testDurationFields_58_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals("centuries", iso.centuries().getName());
    }

    public void testDurationFields_59_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals("YEARS", iso.years().getName());
    }

    public void testDurationFields_60_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals("weekyears", iso.weekyears().getName());
    }

    public void testDurationFields_61_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertNotNull(isoOffset1);
    }

    public void testDurationFields_62_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals("weeks", iso.weeks().getName());
    }

    public void testDurationFields_63_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals("Days", iso.days().getName());
    }

    public void testDurationFields_64_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals("Halfdays", iso.halfdays().getName());
    }

    public void testDurationFields_65_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals("Hours", iso.hours().getName());
    }

    public void testDurationFields_66_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals("Minutes", iso.minutes().getName());
    }

    public void testDurationFields_67_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals("seconds", iso.seconds().getName());
    }

    public void testDurationFields_68_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        final ISOChronology isoUTC = ISOChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final ISOChronology isoGMT = ISOChronology.getInstance(gmt);
        
        final DateTimeZone offset = DateTimeZone.forOffsetHours(1);
        final ISOChronology isoOffset1 = ISOChronology.getInstance(offset);
        assertEquals("millis", iso.millis().getName());
    }

    public void testDateFields_1_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("era", iso.era().getName());
    }

    public void testDateFields_2_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("centuryOfEra", iso.centuryOfEra().getName());
    }

    public void testDateFields_3_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("yearOfCentury", iso.yearOfCentury().getName());
    }

    public void testDateFields_4_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("yearOfEra", iso.yearOfEra().getName());
    }

    public void testDateFields_5_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("year", iso.year().getName());
    }

    public void testDateFields_6_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("MonthOfYear", iso.monthOfYear().getName());
    }

    public void testDateFields_7_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
// incorrect assertion         assertEquals("weekyearOfCentury", ISOChronology.weekyearOfCentury().getName());
    }

    public void testDateFields_8_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("weekyear", iso.weekyear().getName());
    }

    public void testDateFields_9_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("weekOfWeekyear", iso.weekOfWeekyear().getName());
    }

    public void testDateFields_10_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("dayOfYear", iso.dayOfYear().getName());
    }

    public void testDateFields_11_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("dayOfMonth", iso.dayOfMonth().getName());
    }

    public void testDateFields_12_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("dayOfWeek", iso.dayOfWeek().getName());
    }

    public void testDateFields_13_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("era", iso.era().getName());
    }

    public void testDateFields_14_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("centuryOfEra", iso.centuryOfEra().getName());
    }

    public void testDateFields_15_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("yearOfCentury", iso.yearOfCentury().getName());
    }

    public void testDateFields_16_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("yearOfEra", iso.yearOfEra().getName());
    }

    public void testDateFields_17_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("year", iso.year().getName());
    }

    public void testDateFields_18_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("MonthOfYear", iso.monthOfYear().getName());
    }

    public void testDateFields_19_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
// incorrect assertion         assertEquals("weekyearOfCentury", ISOChronology.weekyearOfCentury().getName());
    }

    public void testDateFields_20_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("weekyear", iso.weekyear().getName());
    }

    public void testDateFields_21_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("weekOfWeekyear", iso.weekOfWeekyear().getName());
    }

    public void testDateFields_22_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("dayOfYear", iso.dayOfYear().getName());
    }

    public void testDateFields_23_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("dayOfMonth", iso.dayOfMonth().getName());
    }

    public void testDateFields_24_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("dayOfWeek", iso.dayOfWeek().getName());
    }

    public void testDateFields_25_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("eras", iso.eras().getName());
    }

    public void testDateFields_26_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("centuries", iso.centuries().getName());
    }

    public void testDateFields_27_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Years", iso.years().getName());
    }

    public void testDateFields_28_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Years", iso.years().getName());
    }

    public void testDateFields_29_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Years", iso.years().getName());
    }

    public void testDateFields_30_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
// incorrect assertion         assertNotNull(Months.months(0));
    }

    public void testDateFields_31_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Weekyears", iso.weekyears().getName());
    }

    public void testDateFields_32_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Weekyears", iso.weekyears().getName());
    }

    public void testDateFields_33_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("weeks", iso.weeks().getName());
    }

    public void testDateFields_34_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Days", iso.days().getName());
    }

    public void testDateFields_35_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Days", iso.days().getName());
    }

    public void testDateFields_36_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        assertEquals("Days", iso.days().getName());
    }

    public void testDateFields_37_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("era", iso.era().getName());
    }

    public void testDateFields_38_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("eras", iso.eras().getName());
    }

    public void testDateFields_39_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("centuries", iso.centuries().getName());
    }

    public void testDateFields_40_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("eras", iso.eras().getName());
    }

    public void testDateFields_41_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("year", iso.year().getName());
    }

    public void testDateFields_42_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("Years", iso.years().getName());
    }

    public void testDateFields_43_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("centuries", iso.centuries().getName());
    }

    public void testDateFields_44_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("weekyear", iso.weekyear().getName());
    }

    public void testDateFields_45_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("Weekyears", iso.weekyears().getName());
    }

    public void testDateFields_46_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("Years", iso.years().getName());
    }

    public void testDateFields_47_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
// incorrect assertion         assertNotNull(Months.months(0));
    }

    public void testDateFields_48_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        
        
        assertEquals("weeks", iso.weeks().getName());
    }

    public void testTimeFields_1_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("halfdayOfDay", iso.halfdayOfDay().getName());
    }

    public void testTimeFields_2_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("clockhour_of_halfday", iso.clockhourOfHalfday().getName());
    }

    public void testTimeFields_3_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("hourOfHalfday", iso.hourOfHalfday().getName());
    }

    public void testTimeFields_4_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("clockhourOfDay", iso.clockhourOfDay().getName());
    }

    public void testTimeFields_5_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("hourOfDay", iso.hourOfDay().getName());
    }

    public void testTimeFields_6_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("minuteOfDay", iso.minuteOfDay().getName());
    }

    public void testTimeFields_7_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("MinuteOfHour", iso.minuteOfHour().getName());
    }

    public void testTimeFields_8_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("secondOfDay", iso.secondOfDay().getName());
    }

    public void testTimeFields_9_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("secondOfMinute", iso.secondOfMinute().getName());
    }

    public void testTimeFields_10_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("millis of day", iso.millisOfDay().getName());
    }

    public void testTimeFields_11_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        assertEquals("millisOfSecond", iso.millisOfSecond().getName());
    }

    public void testTimeFields_12_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("HalfdayOfDay", iso.halfdayOfDay().getName());
    }

    public void testTimeFields_13_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("clockhour_of_halfday", iso.clockhourOfHalfday().getName());
    }

    public void testTimeFields_14_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("hourOfHalfday", iso.hourOfHalfday().getName());
    }

    public void testTimeFields_15_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("clockhourOfDay", iso.clockhourOfDay().getName());
    }

    public void testTimeFields_16_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("hourOfDay", iso.hourOfDay().getName());
    }

    public void testTimeFields_17_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("MinuteOfDay", iso.minuteOfDay().getName());
    }

    public void testTimeFields_18_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("MinuteOfHour", iso.minuteOfHour().getName());
    }

    public void testTimeFields_19_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("secondOfDay", iso.secondOfDay().getName());
    }

    public void testTimeFields_20_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("secondOfMinute", iso.secondOfMinute().getName());
    }

    public void testTimeFields_21_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("millis of day", iso.millisOfDay().getName());
    }

    public void testTimeFields_22_oe() {
        final ISOChronology iso = ISOChronology.getInstance();
        
        assertEquals("millisOfSecond", iso.millisOfSecond().getName());
    }

    public void testMaxYear_1_oe() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int maxYear = chrono.year().getMaximumValue();

        DateTime start = new DateTime(maxYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(maxYear, 12, 31, 23, 59, 59, 999, chrono);
        assertEquals(9223372036854775807L, end.getMillis());
    }

    public void testMaxYear_2_oe() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int maxYear = chrono.year().getMaximumValue();

        DateTime start = new DateTime(maxYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(maxYear, 12, 31, 23, 59, 59, 999, chrono);
        assertEquals(9223372036854775807L, end.getMillis());
    }

    public void testMaxYear_3_oe() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int maxYear = chrono.year().getMaximumValue();

        DateTime start = new DateTime(maxYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(maxYear, 12, 31, 23, 59, 59, 999, chrono);
        assertEquals(2038, end.getYear());
    }

    public void testMaxYear_4_oe() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int maxYear = chrono.year().getMaximumValue();

        DateTime start = new DateTime(maxYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(maxYear, 12, 31, 23, 59, 59, 999, chrono);
        assertEquals(2038, end.getYear());
    }

    public void testMaxYear_10_oe() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int maxYear = chrono.year().getMaximumValue();

        DateTime start = new DateTime(maxYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(maxYear, 12, 31, 23, 59, 59, 999, chrono);
        long delta = end.getMillis() - start.getMillis();
        long expectedDelta = 
            (start.year().isLeap() ? 366L : 365L) * DateTimeConstants.MILLIS_PER_DAY - 1;


        try {
            start.plusYears(1);
        } catch (IllegalFieldValueException e) {
        }

        try {
            end.plusYears(1);
        } catch (IllegalFieldValueException e) {
        }

        assertEquals(1372706400000L, end.getMillis());
    }

    public void testMinYear_1_oe() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int minYear = chrono.year().getMinimumValue();

        DateTime start = new DateTime(minYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(minYear, 12, 31, 23, 59, 59, 999, chrono);
        assertEquals(0L, start.getMillis());
    }

    public void testMinYear_2_oe() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int minYear = chrono.year().getMinimumValue();

        DateTime start = new DateTime(minYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(minYear, 12, 31, 23, 59, 59, 999, chrono);
        assertEquals(0L, start.getMillis());
    }

    public void testMinYear_3_oe() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int minYear = chrono.year().getMinimumValue();

        DateTime start = new DateTime(minYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(minYear, 12, 31, 23, 59, 59, 999, chrono);
        assertEquals(2001, end.getYear());
    }

    public void testMinYear_4_oe() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int minYear = chrono.year().getMinimumValue();

        DateTime start = new DateTime(minYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(minYear, 12, 31, 23, 59, 59, 999, chrono);
        assertEquals(2001, end.getYear());
    }

    public void testMinYear_6_oe() {
        final ISOChronology chrono = ISOChronology.getInstanceUTC();
        final int minYear = chrono.year().getMinimumValue();

        DateTime start = new DateTime(minYear, 1, 1, 0, 0, 0, 0, chrono);
        DateTime end = new DateTime(minYear, 12, 31, 23, 59, 59, 999, chrono);
        long delta = end.getMillis() - start.getMillis();
        long expectedDelta = 
            (start.year().isLeap() ? 366L : 365L) * DateTimeConstants.MILLIS_PER_DAY - 1;

// incorrect assertion         assertEquals(false, end.isLeap());
    }

    public void testTimeOfDayAdd_3_oe() {
        TimeOfDay start = new TimeOfDay(12, 30);
        TimeOfDay end = new TimeOfDay(10, 30);
        assertEquals(10, end.getHourOfDay());
    }

    public void testPartialDayOfYearAdd_2_oe() {
        Partial start = new Partial().with(DateTimeFieldType.year(), 2000).with(DateTimeFieldType.dayOfYear(), 366);
        Partial end = new Partial().with(DateTimeFieldType.year(), 2004).with(DateTimeFieldType.dayOfYear(), 366);
        assertNotNull(end);
    }

    public void testLeap_28feb_1_oe() {
        Chronology chrono = ISOChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(2012, dt.year().get());
    }

    public void testLeap_28feb_2_oe() {
        Chronology chrono = ISOChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals("MonthOfYear", dt.monthOfYear().getName());
    }

    public void testLeap_28feb_3_oe() {
        Chronology chrono = ISOChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(28, dt.dayOfMonth().get());
    }

    public void testLeap_28feb_4_oe() {
        Chronology chrono = ISOChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals("28", dt.dayOfYear().getAsString());
    }

    public void testLeap_29feb_1_oe() {
        Chronology chrono = ISOChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(2012, dt.year().get());
    }

    public void testLeap_29feb_2_oe() {
        Chronology chrono = ISOChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals("MonthOfYear", dt.monthOfYear().getName());
    }

    public void testLeap_29feb_3_oe() {
        Chronology chrono = ISOChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(29, dt.dayOfMonth().get());
    }

    public void testLeap_29feb_4_oe() {
        Chronology chrono = ISOChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(29, dt.dayOfYear().get());
    }

}
