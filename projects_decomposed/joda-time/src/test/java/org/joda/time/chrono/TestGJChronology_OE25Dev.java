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
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Instant;
import org.joda.time.Period;
import org.joda.time.TimeOfDay;
import org.joda.time.YearMonthDay;

/**
 * This class is a Junit unit test for GJChronology.
 *
 * @author Stephen Colebourne
 */
@SuppressWarnings("deprecation")
public class TestGJChronology_OE25Dev extends TestCase {

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
        return new TestSuite(TestGJChronology_OE25Dev.class);
    }

    public TestGJChronology_OE25Dev(String name) {
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
        assertEquals(DateTimeZone.UTC,GJChronology.getInstanceUTC().getZone());
        assertSame(GJChronology.class,GJChronology.getInstanceUTC().getClass());
    }

    public void testFactory() {
        assertEquals(LONDON,GJChronology.getInstance().getZone());
        assertSame(GJChronology.class,GJChronology.getInstance().getClass());
    }

    public void testFactory_Zone() {
        assertEquals(TOKYO,GJChronology.getInstance(TOKYO).getZone());
        assertEquals(PARIS,GJChronology.getInstance(PARIS).getZone());
        assertEquals(LONDON,GJChronology.getInstance(null).getZone());
        assertSame(GJChronology.class,GJChronology.getInstance(TOKYO).getClass());
    }

    public void testFactory_Zone_long_int() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, 0L, 2);
        assertEquals(TOKYO,chrono.getZone());
        assertEquals(new Instant(0L),chrono.getGregorianCutover());
        assertEquals(2,chrono.getMinimumDaysInFirstWeek());
        assertSame(GJChronology.class,GJChronology.getInstance(TOKYO,0L,2).getClass());
        
        try {
            GJChronology.getInstance(TOKYO, 0L, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            GJChronology.getInstance(TOKYO, 0L, 8);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testFactory_Zone_RI() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L));
        assertEquals(TOKYO,chrono.getZone());
        assertEquals(new Instant(0L),chrono.getGregorianCutover());
        assertSame(GJChronology.class,GJChronology.getInstance(TOKYO,new Instant(0L)).getClass());
        
        DateTime cutover = new DateTime(1582, 10, 15, 0, 0, 0, 0, DateTimeZone.UTC);
        chrono = GJChronology.getInstance(TOKYO, null);
        assertEquals(TOKYO,chrono.getZone());
        assertEquals(cutover.toInstant(),chrono.getGregorianCutover());
    }

    public void testFactory_Zone_RI_int() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L), 2);
        assertEquals(TOKYO,chrono.getZone());
        assertEquals(new Instant(0L),chrono.getGregorianCutover());
        assertEquals(2,chrono.getMinimumDaysInFirstWeek());
        assertSame(GJChronology.class,GJChronology.getInstance(TOKYO,new Instant(0L),2).getClass());
        
        DateTime cutover = new DateTime(1582, 10, 15, 0, 0, 0, 0, DateTimeZone.UTC);
        chrono = GJChronology.getInstance(TOKYO, null, 2);
        assertEquals(TOKYO,chrono.getZone());
        assertEquals(cutover.toInstant(),chrono.getGregorianCutover());
        assertEquals(2,chrono.getMinimumDaysInFirstWeek());
        
        try {
            GJChronology.getInstance(TOKYO, new Instant(0L), 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            GJChronology.getInstance(TOKYO, new Instant(0L), 8);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testEquality() {
        assertSame(GJChronology.getInstance(TOKYO),GJChronology.getInstance(TOKYO));
        assertSame(GJChronology.getInstance(LONDON),GJChronology.getInstance(LONDON));
        assertSame(GJChronology.getInstance(PARIS),GJChronology.getInstance(PARIS));
        assertSame(GJChronology.getInstanceUTC(),GJChronology.getInstanceUTC());
        assertSame(GJChronology.getInstance(),GJChronology.getInstance(LONDON));
    }

    public void testWithUTC() {
        assertSame(GJChronology.getInstanceUTC(),GJChronology.getInstance(LONDON).withUTC());
        assertSame(GJChronology.getInstanceUTC(),GJChronology.getInstance(TOKYO).withUTC());
        assertSame(GJChronology.getInstanceUTC(),GJChronology.getInstanceUTC().withUTC());
        assertSame(GJChronology.getInstanceUTC(),GJChronology.getInstance().withUTC());
    }

    public void testWithZone() {
        assertSame(GJChronology.getInstance(TOKYO),GJChronology.getInstance(TOKYO).withZone(TOKYO));
        assertSame(GJChronology.getInstance(LONDON),GJChronology.getInstance(TOKYO).withZone(LONDON));
        assertSame(GJChronology.getInstance(PARIS),GJChronology.getInstance(TOKYO).withZone(PARIS));
        assertSame(GJChronology.getInstance(LONDON),GJChronology.getInstance(TOKYO).withZone(null));
        assertSame(GJChronology.getInstance(PARIS),GJChronology.getInstance().withZone(PARIS));
        assertSame(GJChronology.getInstance(PARIS),GJChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString() {
        assertEquals("GJChronology[Europe/London]",GJChronology.getInstance(LONDON).toString());
        assertEquals("GJChronology[Asia/Tokyo]",GJChronology.getInstance(TOKYO).toString());
        assertEquals("GJChronology[Europe/London]",GJChronology.getInstance().toString());
        assertEquals("GJChronology[UTC]",GJChronology.getInstanceUTC().toString());
        assertEquals("GJChronology[UTC,cutover=1970-01-01]",GJChronology.getInstance(DateTimeZone.UTC,0L,4).toString());
        assertEquals("GJChronology[UTC,cutover=1970-01-01T00:00:00.001Z,mdfw=2]",GJChronology.getInstance(DateTimeZone.UTC,1L,2).toString());
    }

    //-----------------------------------------------------------------------
    public void testDurationFields() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("eras",gj.eras().getName());
        assertEquals("centuries",gj.centuries().getName());
        assertEquals("years",gj.years().getName());
        assertEquals("weekyears",gj.weekyears().getName());
        assertEquals("months",gj.months().getName());
        assertEquals("weeks",gj.weeks().getName());
        assertEquals("halfdays",gj.halfdays().getName());
        assertEquals("days",gj.days().getName());
        assertEquals("hours",gj.hours().getName());
        assertEquals("minutes",gj.minutes().getName());
        assertEquals("seconds",gj.seconds().getName());
        assertEquals("millis",gj.millis().getName());
        
        assertEquals(false,gj.eras().isSupported());
        assertEquals(true,gj.centuries().isSupported());
        assertEquals(true,gj.years().isSupported());
        assertEquals(true,gj.weekyears().isSupported());
        assertEquals(true,gj.months().isSupported());
        assertEquals(true,gj.weeks().isSupported());
        assertEquals(true,gj.days().isSupported());
        assertEquals(true,gj.halfdays().isSupported());
        assertEquals(true,gj.hours().isSupported());
        assertEquals(true,gj.minutes().isSupported());
        assertEquals(true,gj.seconds().isSupported());
        assertEquals(true,gj.millis().isSupported());
        
        assertEquals(false,gj.centuries().isPrecise());
        assertEquals(false,gj.years().isPrecise());
        assertEquals(false,gj.weekyears().isPrecise());
        assertEquals(false,gj.months().isPrecise());
        assertEquals(false,gj.weeks().isPrecise());
        assertEquals(false,gj.days().isPrecise());
        assertEquals(false,gj.halfdays().isPrecise());
        assertEquals(true,gj.hours().isPrecise());
        assertEquals(true,gj.minutes().isPrecise());
        assertEquals(true,gj.seconds().isPrecise());
        assertEquals(true,gj.millis().isPrecise());
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(false,gjUTC.centuries().isPrecise());
        assertEquals(false,gjUTC.years().isPrecise());
        assertEquals(false,gjUTC.weekyears().isPrecise());
        assertEquals(false,gjUTC.months().isPrecise());
        assertEquals(true,gjUTC.weeks().isPrecise());
        assertEquals(true,gjUTC.days().isPrecise());
        assertEquals(true,gjUTC.halfdays().isPrecise());
        assertEquals(true,gjUTC.hours().isPrecise());
        assertEquals(true,gjUTC.minutes().isPrecise());
        assertEquals(true,gjUTC.seconds().isPrecise());
        assertEquals(true,gjUTC.millis().isPrecise());
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(false,gjGMT.centuries().isPrecise());
        assertEquals(false,gjGMT.years().isPrecise());
        assertEquals(false,gjGMT.weekyears().isPrecise());
        assertEquals(false,gjGMT.months().isPrecise());
        assertEquals(true,gjGMT.weeks().isPrecise());
        assertEquals(true,gjGMT.days().isPrecise());
        assertEquals(true,gjGMT.halfdays().isPrecise());
        assertEquals(true,gjGMT.hours().isPrecise());
        assertEquals(true,gjGMT.minutes().isPrecise());
        assertEquals(true,gjGMT.seconds().isPrecise());
        assertEquals(true,gjGMT.millis().isPrecise());
    }

    public void testDateFields() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("era",gj.era().getName());
        assertEquals("centuryOfEra",gj.centuryOfEra().getName());
        assertEquals("yearOfCentury",gj.yearOfCentury().getName());
        assertEquals("yearOfEra",gj.yearOfEra().getName());
        assertEquals("year",gj.year().getName());
        assertEquals("monthOfYear",gj.monthOfYear().getName());
        assertEquals("weekyearOfCentury",gj.weekyearOfCentury().getName());
        assertEquals("weekyear",gj.weekyear().getName());
        assertEquals("weekOfWeekyear",gj.weekOfWeekyear().getName());
        assertEquals("dayOfYear",gj.dayOfYear().getName());
        assertEquals("dayOfMonth",gj.dayOfMonth().getName());
        assertEquals("dayOfWeek",gj.dayOfWeek().getName());
        
        assertEquals(true,gj.era().isSupported());
        assertEquals(true,gj.centuryOfEra().isSupported());
        assertEquals(true,gj.yearOfCentury().isSupported());
        assertEquals(true,gj.yearOfEra().isSupported());
        assertEquals(true,gj.year().isSupported());
        assertEquals(true,gj.monthOfYear().isSupported());
        assertEquals(true,gj.weekyearOfCentury().isSupported());
        assertEquals(true,gj.weekyear().isSupported());
        assertEquals(true,gj.weekOfWeekyear().isSupported());
        assertEquals(true,gj.dayOfYear().isSupported());
        assertEquals(true,gj.dayOfMonth().isSupported());
        assertEquals(true,gj.dayOfWeek().isSupported());
        
        assertEquals(gj.eras(),gj.era().getDurationField());
        assertEquals(gj.centuries(),gj.centuryOfEra().getDurationField());
        assertEquals(gj.years(),gj.yearOfCentury().getDurationField());
        assertEquals(gj.years(),gj.yearOfEra().getDurationField());
        assertEquals(gj.years(),gj.year().getDurationField());
        assertEquals(gj.months(),gj.monthOfYear().getDurationField());
        assertEquals(gj.weekyears(),gj.weekyearOfCentury().getDurationField());
        assertEquals(gj.weekyears(),gj.weekyear().getDurationField());
        assertEquals(gj.weeks(),gj.weekOfWeekyear().getDurationField());
        assertEquals(gj.days(),gj.dayOfYear().getDurationField());
        assertEquals(gj.days(),gj.dayOfMonth().getDurationField());
        assertEquals(gj.days(),gj.dayOfWeek().getDurationField());
        
        assertEquals(null,gj.era().getRangeDurationField());
        assertEquals(gj.eras(),gj.centuryOfEra().getRangeDurationField());
        assertEquals(gj.centuries(),gj.yearOfCentury().getRangeDurationField());
        assertEquals(gj.eras(),gj.yearOfEra().getRangeDurationField());
        assertEquals(null,gj.year().getRangeDurationField());
        assertEquals(gj.years(),gj.monthOfYear().getRangeDurationField());
        assertEquals(gj.centuries(),gj.weekyearOfCentury().getRangeDurationField());
        assertEquals(null,gj.weekyear().getRangeDurationField());
        assertEquals(gj.weekyears(),gj.weekOfWeekyear().getRangeDurationField());
        assertEquals(gj.years(),gj.dayOfYear().getRangeDurationField());
        assertEquals(gj.months(),gj.dayOfMonth().getRangeDurationField());
        assertEquals(gj.weeks(),gj.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("halfdayOfDay",gj.halfdayOfDay().getName());
        assertEquals("clockhourOfHalfday",gj.clockhourOfHalfday().getName());
        assertEquals("hourOfHalfday",gj.hourOfHalfday().getName());
        assertEquals("clockhourOfDay",gj.clockhourOfDay().getName());
        assertEquals("hourOfDay",gj.hourOfDay().getName());
        assertEquals("minuteOfDay",gj.minuteOfDay().getName());
        assertEquals("minuteOfHour",gj.minuteOfHour().getName());
        assertEquals("secondOfDay",gj.secondOfDay().getName());
        assertEquals("secondOfMinute",gj.secondOfMinute().getName());
        assertEquals("millisOfDay",gj.millisOfDay().getName());
        assertEquals("millisOfSecond",gj.millisOfSecond().getName());
        
        assertEquals(true,gj.halfdayOfDay().isSupported());
        assertEquals(true,gj.clockhourOfHalfday().isSupported());
        assertEquals(true,gj.hourOfHalfday().isSupported());
        assertEquals(true,gj.clockhourOfDay().isSupported());
        assertEquals(true,gj.hourOfDay().isSupported());
        assertEquals(true,gj.minuteOfDay().isSupported());
        assertEquals(true,gj.minuteOfHour().isSupported());
        assertEquals(true,gj.secondOfDay().isSupported());
        assertEquals(true,gj.secondOfMinute().isSupported());
        assertEquals(true,gj.millisOfDay().isSupported());
        assertEquals(true,gj.millisOfSecond().isSupported());
    }

    public void testIllegalDates() {
        try {
            new DateTime(1582, 10, 5, 0, 0, 0, 0, GJChronology.getInstance(DateTimeZone.UTC));
            fail("Constructed illegal date");
        } catch (IllegalArgumentException e) { /* good */ }

        try {
            new DateTime(1582, 10, 14, 0, 0, 0, 0, GJChronology.getInstance(DateTimeZone.UTC));
            fail("Constructed illegal date");
        } catch (IllegalArgumentException e) { /* good */ }
    }

    public void testParseEquivalence() {
        testParse("1581-01-01T01:23:45.678", 1581, 1, 1, 1, 23, 45, 678);
        testParse("1581-06-30", 1581, 6, 30, 0, 0, 0, 0);
        testParse("1582-01-01T01:23:45.678", 1582, 1, 1, 1, 23, 45, 678);
        testParse("1582-06-30T01:23:45.678", 1582, 6, 30, 1, 23, 45, 678);
        testParse("1582-10-04", 1582, 10, 4, 0, 0, 0, 0);
        testParse("1582-10-15", 1582, 10, 15, 0, 0, 0, 0);
        testParse("1582-12-31", 1582, 12, 31, 0, 0, 0, 0);
        testParse("1583-12-31", 1583, 12, 31, 0, 0, 0, 0);
    }

    private void testParse(String str,
                           int year, int month, int day,
                           int hour, int minute, int second, int millis) {
        assertEquals(new DateTime(str,GJChronology.getInstance(DateTimeZone.UTC)),new DateTime(year,month,day,hour,minute,second,millis,GJChronology.getInstance(DateTimeZone.UTC)));
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

        // Leap years...
        testAdd("1580-01-01", DurationFieldType.years(), 4, "1584-01-01");
        testAdd("1580-02-29", DurationFieldType.years(), 4, "1584-02-29");
        testAdd("1580-10-01", DurationFieldType.years(), 4, "1584-10-01");
        testAdd("1580-10-10", DurationFieldType.years(), 4, "1584-10-10");
        testAdd("1580-10-15", DurationFieldType.years(), 4, "1584-10-15");
        testAdd("1580-12-31", DurationFieldType.years(), 4, "1584-12-31");
    }

    public void testCutoverAddWeekyears() {
        testAdd("1582-W01-1", DurationFieldType.weekyears(), 1, "1583-W01-1");
        testAdd("1582-W39-1", DurationFieldType.weekyears(), 1, "1583-W39-1");
        testAdd("1583-W45-1", DurationFieldType.weekyears(), 1, "1584-W45-1");

        // This test fails, but I'm not sure if its worth fixing. The date
        // falls after the cutover, but in the cutover year. The add operation
        // is performed completely within the gregorian calendar, with no
        // crossing of the cutover. As a result, no special correction is
        // applied. Since the full gregorian year of 1582 has a different week
        // numbers than the full julian year of 1582, the week number is off by
        // one after the addition.
        //
        //testAdd("1582-W42-1", DurationFieldType.weekyears(), 1, "1583-W42-1");

        // Leap years...
        testAdd("1580-W01-1", DurationFieldType.weekyears(), 4, "1584-W01-1");
        testAdd("1580-W30-7", DurationFieldType.weekyears(), 4, "1584-W30-7");
        testAdd("1580-W50-7", DurationFieldType.weekyears(), 4, "1584-W50-7");
    }

    public void testCutoverAddMonths() {
        testAdd("1582-01-01", DurationFieldType.months(), 1, "1582-02-01");
        testAdd("1582-01-01", DurationFieldType.months(), 6, "1582-07-01");
        testAdd("1582-01-01", DurationFieldType.months(), 12, "1583-01-01");
        testAdd("1582-11-15", DurationFieldType.months(), 1, "1582-12-15");

        testAdd("1582-09-04", DurationFieldType.months(), 2, "1582-11-04");
        testAdd("1582-09-05", DurationFieldType.months(), 2, "1582-11-05");
        testAdd("1582-09-10", DurationFieldType.months(), 2, "1582-11-10");
        testAdd("1582-09-15", DurationFieldType.months(), 2, "1582-11-15");


        // Leap years...
        testAdd("1580-01-01", DurationFieldType.months(), 48, "1584-01-01");
        testAdd("1580-02-29", DurationFieldType.months(), 48, "1584-02-29");
        testAdd("1580-10-01", DurationFieldType.months(), 48, "1584-10-01");
        testAdd("1580-10-10", DurationFieldType.months(), 48, "1584-10-10");
        testAdd("1580-10-15", DurationFieldType.months(), 48, "1584-10-15");
        testAdd("1580-12-31", DurationFieldType.months(), 48, "1584-12-31");
    }

    public void testCutoverAddWeeks() {
        testAdd("1582-01-01", DurationFieldType.weeks(), 1, "1582-01-08");
        testAdd("1583-01-01", DurationFieldType.weeks(), 1, "1583-01-08");

        // Weeks are precise, and so cutover is not ignored.
        testAdd("1582-10-01", DurationFieldType.weeks(), 2, "1582-10-25");
        testAdd("1582-W01-1", DurationFieldType.weeks(), 51, "1583-W01-1");
    }

    public void testCutoverAddDays() {
        testAdd("1582-10-03", DurationFieldType.days(), 1, "1582-10-04");
        testAdd("1582-10-04", DurationFieldType.days(), 1, "1582-10-15");
        testAdd("1582-10-15", DurationFieldType.days(), 1, "1582-10-16");

        testAdd("1582-09-30", DurationFieldType.days(), 10, "1582-10-20");
        testAdd("1582-10-04", DurationFieldType.days(), 10, "1582-10-24");
        testAdd("1582-10-15", DurationFieldType.days(), 10, "1582-10-25");
    }

    public void testYearEndAddDays() {
        testAdd("1582-11-05", DurationFieldType.days(), 28, "1582-12-03");
        testAdd("1582-12-05", DurationFieldType.days(), 28, "1583-01-02");
        
        testAdd("2005-11-05", DurationFieldType.days(), 28, "2005-12-03");
        testAdd("2005-12-05", DurationFieldType.days(), 28, "2006-01-02");
    }

    public void testSubtractDays() {
        // This is a test for a bug in version 1.0. The dayOfMonth range
        // duration field did not match the monthOfYear duration field. This
        // caused an exception to be thrown when subtracting days.
        DateTime dt = new DateTime
            (1112306400000L, GJChronology.getInstance(DateTimeZone.forID("Europe/Berlin")));
        YearMonthDay ymd = dt.toYearMonthDay();
        while (ymd.toDateTimeAtMidnight().getDayOfWeek() != DateTimeConstants.MONDAY) { 
            ymd = ymd.minus(Period.days(1));
        }
    }

    private void testAdd(String start, DurationFieldType type, int amt, String end) {
        DateTime dtStart = new DateTime(start, GJChronology.getInstance(DateTimeZone.UTC));
        DateTime dtEnd = new DateTime(end, GJChronology.getInstance(DateTimeZone.UTC));
        assertEquals(dtEnd,dtStart.withFieldAdded(type,amt));
        assertEquals(dtStart,dtEnd.withFieldAdded(type,-amt));

        DurationField field = type.getField(GJChronology.getInstance(DateTimeZone.UTC));
        int diff = field.getDifference(dtEnd.getMillis(), dtStart.getMillis());
        assertEquals(amt,diff);
        
        if (type == DurationFieldType.years() ||
            type == DurationFieldType.months() ||
            type == DurationFieldType.days()) {
            YearMonthDay ymdStart = new YearMonthDay(start, GJChronology.getInstance(DateTimeZone.UTC));
            YearMonthDay ymdEnd = new YearMonthDay(end, GJChronology.getInstance(DateTimeZone.UTC));
            assertEquals(ymdEnd,ymdStart.withFieldAdded(type,amt));
            assertEquals(ymdStart,ymdEnd.withFieldAdded(type,-amt));
        }
    }

    public void testTimeOfDayAdd() {
        TimeOfDay start = new TimeOfDay(12, 30, GJChronology.getInstance());
        TimeOfDay end = new TimeOfDay(10, 30, GJChronology.getInstance());
        assertEquals(end,start.plusHours(22));
        assertEquals(start,end.minusHours(22));
        assertEquals(end,start.plusMinutes(22 * 60));
        assertEquals(start,end.minusMinutes(22 * 60));
    }

    public void testMaximumValue() {
        DateMidnight dt = new DateMidnight(1570, 1, 1, GJChronology.getInstance());
        while (dt.getYear() < 1590) {
            dt = dt.plusDays(1);
            YearMonthDay ymd = dt.toYearMonthDay();
            assertEquals(dt.year().getMaximumValue(),ymd.year().getMaximumValue());
            assertEquals(dt.monthOfYear().getMaximumValue(),ymd.monthOfYear().getMaximumValue());
            assertEquals(dt.dayOfMonth().getMaximumValue(),ymd.dayOfMonth().getMaximumValue());
        }
    }

    public void testPartialGetAsText() {
        GJChronology chrono = GJChronology.getInstance(TOKYO);
        assertEquals("January",new YearMonthDay("2005-01-01",chrono).monthOfYear().getAsText());
        assertEquals("Jan",new YearMonthDay("2005-01-01",chrono).monthOfYear().getAsShortText());
    }

    public void testLeapYearRulesConstruction() {
        // 1500 not leap in Gregorian, but is leap in Julian
        DateMidnight dt = new DateMidnight(1500, 2, 29, GJChronology.getInstanceUTC());
        assertEquals(dt.getYear(),1500);
        assertEquals(dt.getMonthOfYear(),2);
        assertEquals(dt.getDayOfMonth(),29);
    }

    public void testLeapYearRulesConstructionInvalid() {
        // 1500 not leap in Gregorian, but is leap in Julian
        try {
            new DateMidnight(1500, 2, 30, GJChronology.getInstanceUTC());
            fail();
        } catch (IllegalFieldValueException ex) {
            // good
        }
    }

    public void testLeap_28feb() {
        Chronology chrono = GJChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(false,dt.dayOfMonth().isLeap());
        assertEquals(false,dt.dayOfYear().isLeap());
    }

    public void testLeap_29feb() {
        Chronology chrono = GJChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
        assertEquals(true,dt.monthOfYear().isLeap());
        assertEquals(true,dt.dayOfMonth().isLeap());
        assertEquals(true,dt.dayOfYear().isLeap());
    }

    public void testFactoryUTC_1_oe() {
        Object a = DateTimeZone.UTC;
        assertEquals(a, GJChronology.getInstanceUTC().getZone());
    }

    public void testFactoryUTC_2_oe() {
        Object a = GJChronology.class;
        assertSame(a, GJChronology.getInstanceUTC().getClass());
    }

    public void testFactory_1_oe() {
        Object a = LONDON;
        assertEquals(a, GJChronology.getInstance().getZone());
    }

    public void testFactory_2_oe() {
        Object a = GJChronology.class;
        assertSame(a, GJChronology.getInstance().getClass());
    }

    public void testFactory_Zone_1_oe() {
        Object a = TOKYO;
        assertEquals(a, GJChronology.getInstance(TOKYO).getZone());
    }

    public void testFactory_Zone_2_oe() {
        Object a = PARIS;
        assertEquals(a, GJChronology.getInstance(PARIS).getZone());
    }

    public void testFactory_Zone_3_oe() {
        Object a = LONDON;
        assertEquals(a, GJChronology.getInstance(null).getZone());
    }

    public void testFactory_Zone_4_oe() {
        Object a = GJChronology.class;
        assertSame(a, GJChronology.getInstance(TOKYO).getClass());
    }

    public void testFactory_Zone_long_int_1_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, 0L, 2);
        assertEquals(TOKYO,chrono.getZone());
    }

    public void testFactory_Zone_long_int_2_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, 0L, 2);
        assertEquals(new Instant(0L),chrono.getGregorianCutover());
    }

    public void testFactory_Zone_long_int_3_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, 0L, 2);
        assertEquals(2,chrono.getMinimumDaysInFirstWeek());
    }

    public void testFactory_Zone_long_int_4_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, 0L, 2);
        assertSame(GJChronology.class,GJChronology.getInstance(TOKYO,0L,2).getClass());
    }

    public void testFactory_Zone_RI_1_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L));
        assertEquals(TOKYO,chrono.getZone());
    }

    public void testFactory_Zone_RI_2_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L));
        assertEquals(new Instant(0L),chrono.getGregorianCutover());
    }

    public void testFactory_Zone_RI_3_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L));
        assertSame(GJChronology.class,GJChronology.getInstance(TOKYO,new Instant(0L)).getClass());
    }

    public void testFactory_Zone_RI_4_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L));
        
        DateTime cutover = new DateTime(1582, 10, 15, 0, 0, 0, 0, DateTimeZone.UTC);
        chrono = GJChronology.getInstance(TOKYO, null);
        assertEquals(TOKYO,chrono.getZone());
    }

    public void testFactory_Zone_RI_5_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L));
        
        DateTime cutover = new DateTime(1582, 10, 15, 0, 0, 0, 0, DateTimeZone.UTC);
        chrono = GJChronology.getInstance(TOKYO, null);
        assertEquals(cutover.toInstant(),chrono.getGregorianCutover());
    }

    public void testFactory_Zone_RI_int_1_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L), 2);
        assertEquals(TOKYO,chrono.getZone());
    }

    public void testFactory_Zone_RI_int_2_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L), 2);
        assertEquals(new Instant(0L),chrono.getGregorianCutover());
    }

    public void testFactory_Zone_RI_int_3_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L), 2);
        assertEquals(2,chrono.getMinimumDaysInFirstWeek());
    }

    public void testFactory_Zone_RI_int_4_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L), 2);
        assertSame(GJChronology.class,GJChronology.getInstance(TOKYO,new Instant(0L),2).getClass());
    }

    public void testFactory_Zone_RI_int_5_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L), 2);
        
        DateTime cutover = new DateTime(1582, 10, 15, 0, 0, 0, 0, DateTimeZone.UTC);
        chrono = GJChronology.getInstance(TOKYO, null, 2);
        assertEquals(TOKYO,chrono.getZone());
    }

    public void testFactory_Zone_RI_int_6_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L), 2);
        
        DateTime cutover = new DateTime(1582, 10, 15, 0, 0, 0, 0, DateTimeZone.UTC);
        chrono = GJChronology.getInstance(TOKYO, null, 2);
        assertEquals(cutover.toInstant(),chrono.getGregorianCutover());
    }

    public void testFactory_Zone_RI_int_7_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L), 2);
        
        DateTime cutover = new DateTime(1582, 10, 15, 0, 0, 0, 0, DateTimeZone.UTC);
        chrono = GJChronology.getInstance(TOKYO, null, 2);
        assertEquals(2,chrono.getMinimumDaysInFirstWeek());
    }

    public void testEquality_1_oe() {
        Object a = GJChronology.getInstance(TOKYO);
        assertSame(a, GJChronology.getInstance(TOKYO));
    }

    public void testEquality_2_oe() {
        Object a = GJChronology.getInstance(LONDON);
        assertSame(a, GJChronology.getInstance(LONDON));
    }

    public void testEquality_3_oe() {
        Object a = GJChronology.getInstance(PARIS);
        assertSame(a, GJChronology.getInstance(PARIS));
    }

    public void testEquality_4_oe() {
        Object a = GJChronology.getInstanceUTC();
        assertSame(a, GJChronology.getInstanceUTC());
    }

    public void testEquality_5_oe() {
        Object a = GJChronology.getInstance();
        assertSame(a, GJChronology.getInstance(LONDON));
    }

    public void testWithUTC_1_oe() {
        Object a = GJChronology.getInstanceUTC();
        assertSame(a, GJChronology.getInstance(LONDON).withUTC());
    }

    public void testWithUTC_2_oe() {
        Object a = GJChronology.getInstanceUTC();
        assertSame(a, GJChronology.getInstance(TOKYO).withUTC());
    }

    public void testWithUTC_3_oe() {
        Object a = GJChronology.getInstanceUTC();
        assertSame(a, GJChronology.getInstanceUTC().withUTC());
    }

    public void testWithUTC_4_oe() {
        Object a = GJChronology.getInstanceUTC();
        assertSame(a, GJChronology.getInstance().withUTC());
    }

    public void testWithZone_1_oe() {
        Object a = GJChronology.getInstance(TOKYO);
        assertSame(a, GJChronology.getInstance(TOKYO).withZone(TOKYO));
    }

    public void testWithZone_2_oe() {
        Object a = GJChronology.getInstance(LONDON);
        assertSame(a, GJChronology.getInstance(TOKYO).withZone(LONDON));
    }

    public void testWithZone_3_oe() {
        Object a = GJChronology.getInstance(PARIS);
        assertSame(a, GJChronology.getInstance(TOKYO).withZone(PARIS));
    }

    public void testWithZone_4_oe() {
        Object a = GJChronology.getInstance(LONDON);
        assertSame(a, GJChronology.getInstance(TOKYO).withZone(null));
    }

    public void testWithZone_5_oe() {
        Object a = GJChronology.getInstance(PARIS);
        assertSame(a, GJChronology.getInstance().withZone(PARIS));
    }

    public void testWithZone_6_oe() {
        Object a = GJChronology.getInstance(PARIS);
        assertSame(a, GJChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString_1_oe() {
        Object a = GJChronology.getInstance(LONDON).toString();
        assertEquals("GJChronology[Europe/London]", a);
    }

    public void testToString_2_oe() {
        Object a = GJChronology.getInstance(TOKYO).toString();
        assertEquals("GJChronology[Asia/Tokyo]", a);
    }

    public void testToString_3_oe() {
        Object a = GJChronology.getInstance().toString();
        assertEquals("GJChronology[Europe/London]", a);
    }

    public void testToString_4_oe() {
        Object a = GJChronology.getInstanceUTC().toString();
        assertEquals("GJChronology[UTC]", a);
    }

    public void testToString_5_oe() {
        Object a = GJChronology.getInstance(DateTimeZone.UTC,0L,4).toString();
        assertEquals("GJChronology[UTC,cutover=1970-01-01]", a);
    }

    public void testToString_6_oe() {
        Object a = GJChronology.getInstance(DateTimeZone.UTC,1L,2).toString();
        assertEquals("GJChronology[UTC,cutover=1970-01-01T00:00:00.001Z,mdfw=2]", a);
    }

    public void testDurationFields_1_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("eras",gj.eras().getName());
    }

    public void testDurationFields_2_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("centuries",gj.centuries().getName());
    }

    public void testDurationFields_3_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("years",gj.years().getName());
    }

    public void testDurationFields_4_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("weekyears",gj.weekyears().getName());
    }

    public void testDurationFields_5_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("months",gj.months().getName());
    }

    public void testDurationFields_6_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("weeks",gj.weeks().getName());
    }

    public void testDurationFields_7_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("halfdays",gj.halfdays().getName());
    }

    public void testDurationFields_8_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("days",gj.days().getName());
    }

    public void testDurationFields_9_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("hours",gj.hours().getName());
    }

    public void testDurationFields_10_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("minutes",gj.minutes().getName());
    }

    public void testDurationFields_11_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("seconds",gj.seconds().getName());
    }

    public void testDurationFields_12_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("millis",gj.millis().getName());
    }

    public void testDurationFields_13_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(false,gj.eras().isSupported());
    }

    public void testDurationFields_14_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.centuries().isSupported());
    }

    public void testDurationFields_15_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.years().isSupported());
    }

    public void testDurationFields_16_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.weekyears().isSupported());
    }

    public void testDurationFields_17_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.months().isSupported());
    }

    public void testDurationFields_18_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.weeks().isSupported());
    }

    public void testDurationFields_19_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.days().isSupported());
    }

    public void testDurationFields_20_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.halfdays().isSupported());
    }

    public void testDurationFields_21_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.hours().isSupported());
    }

    public void testDurationFields_22_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.minutes().isSupported());
    }

    public void testDurationFields_23_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.seconds().isSupported());
    }

    public void testDurationFields_24_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.millis().isSupported());
    }

    public void testDurationFields_25_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(false,gj.centuries().isPrecise());
    }

    public void testDurationFields_26_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(false,gj.years().isPrecise());
    }

    public void testDurationFields_27_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(false,gj.weekyears().isPrecise());
    }

    public void testDurationFields_28_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(false,gj.months().isPrecise());
    }

    public void testDurationFields_29_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(false,gj.weeks().isPrecise());
    }

    public void testDurationFields_30_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(false,gj.days().isPrecise());
    }

    public void testDurationFields_31_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(false,gj.halfdays().isPrecise());
    }

    public void testDurationFields_32_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(true,gj.hours().isPrecise());
    }

    public void testDurationFields_33_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(true,gj.minutes().isPrecise());
    }

    public void testDurationFields_34_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(true,gj.seconds().isPrecise());
    }

    public void testDurationFields_35_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(true,gj.millis().isPrecise());
    }

    public void testDurationFields_36_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(false,gjUTC.centuries().isPrecise());
    }

    public void testDurationFields_37_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(false,gjUTC.years().isPrecise());
    }

    public void testDurationFields_38_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(false,gjUTC.weekyears().isPrecise());
    }

    public void testDurationFields_39_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(false,gjUTC.months().isPrecise());
    }

    public void testDurationFields_40_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(true,gjUTC.weeks().isPrecise());
    }

    public void testDurationFields_41_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(true,gjUTC.days().isPrecise());
    }

    public void testDurationFields_42_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(true,gjUTC.halfdays().isPrecise());
    }

    public void testDurationFields_43_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(true,gjUTC.hours().isPrecise());
    }

    public void testDurationFields_44_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(true,gjUTC.minutes().isPrecise());
    }

    public void testDurationFields_45_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(true,gjUTC.seconds().isPrecise());
    }

    public void testDurationFields_46_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        assertEquals(true,gjUTC.millis().isPrecise());
    }

    public void testDurationFields_47_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(false,gjGMT.centuries().isPrecise());
    }

    public void testDurationFields_48_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(false,gjGMT.years().isPrecise());
    }

    public void testDurationFields_49_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(false,gjGMT.weekyears().isPrecise());
    }

    public void testDurationFields_50_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(false,gjGMT.months().isPrecise());
    }

    public void testDurationFields_51_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(true,gjGMT.weeks().isPrecise());
    }

    public void testDurationFields_52_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(true,gjGMT.days().isPrecise());
    }

    public void testDurationFields_53_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(true,gjGMT.halfdays().isPrecise());
    }

    public void testDurationFields_54_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(true,gjGMT.hours().isPrecise());
    }

    public void testDurationFields_55_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(true,gjGMT.minutes().isPrecise());
    }

    public void testDurationFields_56_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(true,gjGMT.seconds().isPrecise());
    }

    public void testDurationFields_57_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        final GJChronology gjUTC = GJChronology.getInstanceUTC();
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final GJChronology gjGMT = GJChronology.getInstance(gmt);
        assertEquals(true,gjGMT.millis().isPrecise());
    }

    public void testDateFields_1_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("era",gj.era().getName());
    }

    public void testDateFields_2_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("centuryOfEra",gj.centuryOfEra().getName());
    }

    public void testDateFields_3_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("yearOfCentury",gj.yearOfCentury().getName());
    }

    public void testDateFields_4_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("yearOfEra",gj.yearOfEra().getName());
    }

    public void testDateFields_5_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("year",gj.year().getName());
    }

    public void testDateFields_6_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("monthOfYear",gj.monthOfYear().getName());
    }

    public void testDateFields_7_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("weekyearOfCentury",gj.weekyearOfCentury().getName());
    }

    public void testDateFields_8_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("weekyear",gj.weekyear().getName());
    }

    public void testDateFields_9_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("weekOfWeekyear",gj.weekOfWeekyear().getName());
    }

    public void testDateFields_10_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("dayOfYear",gj.dayOfYear().getName());
    }

    public void testDateFields_11_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("dayOfMonth",gj.dayOfMonth().getName());
    }

    public void testDateFields_12_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("dayOfWeek",gj.dayOfWeek().getName());
    }

    public void testDateFields_13_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.era().isSupported());
    }

    public void testDateFields_14_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.centuryOfEra().isSupported());
    }

    public void testDateFields_15_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.yearOfCentury().isSupported());
    }

    public void testDateFields_16_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.yearOfEra().isSupported());
    }

    public void testDateFields_17_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.year().isSupported());
    }

    public void testDateFields_18_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.monthOfYear().isSupported());
    }

    public void testDateFields_19_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.weekyearOfCentury().isSupported());
    }

    public void testDateFields_20_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.weekyear().isSupported());
    }

    public void testDateFields_21_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.weekOfWeekyear().isSupported());
    }

    public void testDateFields_22_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.dayOfYear().isSupported());
    }

    public void testDateFields_23_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.dayOfMonth().isSupported());
    }

    public void testDateFields_24_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.dayOfWeek().isSupported());
    }

    public void testDateFields_25_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.eras(),gj.era().getDurationField());
    }

    public void testDateFields_26_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.centuries(),gj.centuryOfEra().getDurationField());
    }

    public void testDateFields_27_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.years(),gj.yearOfCentury().getDurationField());
    }

    public void testDateFields_28_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.years(),gj.yearOfEra().getDurationField());
    }

    public void testDateFields_29_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.years(),gj.year().getDurationField());
    }

    public void testDateFields_30_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.months(),gj.monthOfYear().getDurationField());
    }

    public void testDateFields_31_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.weekyears(),gj.weekyearOfCentury().getDurationField());
    }

    public void testDateFields_32_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.weekyears(),gj.weekyear().getDurationField());
    }

    public void testDateFields_33_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.weeks(),gj.weekOfWeekyear().getDurationField());
    }

    public void testDateFields_34_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.days(),gj.dayOfYear().getDurationField());
    }

    public void testDateFields_35_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.days(),gj.dayOfMonth().getDurationField());
    }

    public void testDateFields_36_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        assertEquals(gj.days(),gj.dayOfWeek().getDurationField());
    }

    public void testDateFields_37_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(null,gj.era().getRangeDurationField());
    }

    public void testDateFields_38_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(gj.eras(),gj.centuryOfEra().getRangeDurationField());
    }

    public void testDateFields_39_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(gj.centuries(),gj.yearOfCentury().getRangeDurationField());
    }

    public void testDateFields_40_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(gj.eras(),gj.yearOfEra().getRangeDurationField());
    }

    public void testDateFields_41_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(null,gj.year().getRangeDurationField());
    }

    public void testDateFields_42_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(gj.years(),gj.monthOfYear().getRangeDurationField());
    }

    public void testDateFields_43_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(gj.centuries(),gj.weekyearOfCentury().getRangeDurationField());
    }

    public void testDateFields_44_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(null,gj.weekyear().getRangeDurationField());
    }

    public void testDateFields_45_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(gj.weekyears(),gj.weekOfWeekyear().getRangeDurationField());
    }

    public void testDateFields_46_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(gj.years(),gj.dayOfYear().getRangeDurationField());
    }

    public void testDateFields_47_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(gj.months(),gj.dayOfMonth().getRangeDurationField());
    }

    public void testDateFields_48_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        
        
        assertEquals(gj.weeks(),gj.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields_1_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("halfdayOfDay",gj.halfdayOfDay().getName());
    }

    public void testTimeFields_2_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("clockhourOfHalfday",gj.clockhourOfHalfday().getName());
    }

    public void testTimeFields_3_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("hourOfHalfday",gj.hourOfHalfday().getName());
    }

    public void testTimeFields_4_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("clockhourOfDay",gj.clockhourOfDay().getName());
    }

    public void testTimeFields_5_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("hourOfDay",gj.hourOfDay().getName());
    }

    public void testTimeFields_6_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("minuteOfDay",gj.minuteOfDay().getName());
    }

    public void testTimeFields_7_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("minuteOfHour",gj.minuteOfHour().getName());
    }

    public void testTimeFields_8_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("secondOfDay",gj.secondOfDay().getName());
    }

    public void testTimeFields_9_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("secondOfMinute",gj.secondOfMinute().getName());
    }

    public void testTimeFields_10_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("millisOfDay",gj.millisOfDay().getName());
    }

    public void testTimeFields_11_oe() {
        final GJChronology gj = GJChronology.getInstance();
        assertEquals("millisOfSecond",gj.millisOfSecond().getName());
    }

    public void testTimeFields_12_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.halfdayOfDay().isSupported());
    }

    public void testTimeFields_13_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.clockhourOfHalfday().isSupported());
    }

    public void testTimeFields_14_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.hourOfHalfday().isSupported());
    }

    public void testTimeFields_15_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.clockhourOfDay().isSupported());
    }

    public void testTimeFields_16_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.hourOfDay().isSupported());
    }

    public void testTimeFields_17_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.minuteOfDay().isSupported());
    }

    public void testTimeFields_18_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.minuteOfHour().isSupported());
    }

    public void testTimeFields_19_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.secondOfDay().isSupported());
    }

    public void testTimeFields_20_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.secondOfMinute().isSupported());
    }

    public void testTimeFields_21_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.millisOfDay().isSupported());
    }

    public void testTimeFields_22_oe() {
        final GJChronology gj = GJChronology.getInstance();
        
        assertEquals(true,gj.millisOfSecond().isSupported());
    }

    public void testTimeOfDayAdd_1_oe() {
        TimeOfDay start = new TimeOfDay(12, 30, GJChronology.getInstance());
        TimeOfDay end = new TimeOfDay(10, 30, GJChronology.getInstance());
        assertEquals(end,start.plusHours(22));
    }

    public void testTimeOfDayAdd_2_oe() {
        TimeOfDay start = new TimeOfDay(12, 30, GJChronology.getInstance());
        TimeOfDay end = new TimeOfDay(10, 30, GJChronology.getInstance());
        assertEquals(start,end.minusHours(22));
    }

    public void testTimeOfDayAdd_3_oe() {
        TimeOfDay start = new TimeOfDay(12, 30, GJChronology.getInstance());
        TimeOfDay end = new TimeOfDay(10, 30, GJChronology.getInstance());
        assertEquals(end,start.plusMinutes(22 * 60));
    }

    public void testTimeOfDayAdd_4_oe() {
        TimeOfDay start = new TimeOfDay(12, 30, GJChronology.getInstance());
        TimeOfDay end = new TimeOfDay(10, 30, GJChronology.getInstance());
        assertEquals(start,end.minusMinutes(22 * 60));
    }

    public void testMaximumValue_1_oe() {
        DateMidnight dt = new DateMidnight(1570, 1, 1, GJChronology.getInstance());
        while (dt.getYear() < 1590) {
            dt = dt.plusDays(1);
            YearMonthDay ymd = dt.toYearMonthDay();
            assertEquals(dt.year().getMaximumValue(),ymd.year().getMaximumValue());
    }
    }

    public void testMaximumValue_2_oe() {
        DateMidnight dt = new DateMidnight(1570, 1, 1, GJChronology.getInstance());
        while (dt.getYear() < 1590) {
            dt = dt.plusDays(1);
            YearMonthDay ymd = dt.toYearMonthDay();
            assertEquals(dt.monthOfYear().getMaximumValue(),ymd.monthOfYear().getMaximumValue());
    }
    }

    public void testMaximumValue_3_oe() {
        DateMidnight dt = new DateMidnight(1570, 1, 1, GJChronology.getInstance());
        while (dt.getYear() < 1590) {
            dt = dt.plusDays(1);
            YearMonthDay ymd = dt.toYearMonthDay();
            assertEquals(dt.dayOfMonth().getMaximumValue(),ymd.dayOfMonth().getMaximumValue());
    }
    }

    public void testPartialGetAsText_1_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO);
        assertEquals("January",new YearMonthDay("2005-01-01",chrono).monthOfYear().getAsText());
    }

    public void testPartialGetAsText_2_oe() {
        GJChronology chrono = GJChronology.getInstance(TOKYO);
        assertEquals("Jan",new YearMonthDay("2005-01-01",chrono).monthOfYear().getAsShortText());
    }

    public void testLeapYearRulesConstruction_1_oe() {
        DateMidnight dt = new DateMidnight(1500, 2, 29, GJChronology.getInstanceUTC());
        assertEquals(dt.getYear(),1500);
    }

    public void testLeapYearRulesConstruction_2_oe() {
        DateMidnight dt = new DateMidnight(1500, 2, 29, GJChronology.getInstanceUTC());
        assertEquals(dt.getMonthOfYear(),2);
    }

    public void testLeapYearRulesConstruction_3_oe() {
        DateMidnight dt = new DateMidnight(1500, 2, 29, GJChronology.getInstanceUTC());
        assertEquals(dt.getDayOfMonth(),29);
    }

    public void testLeap_28feb_1_oe() {
        Chronology chrono = GJChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
    }

    public void testLeap_28feb_2_oe() {
        Chronology chrono = GJChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(true,dt.monthOfYear().isLeap());
    }

    public void testLeap_28feb_3_oe() {
        Chronology chrono = GJChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(false,dt.dayOfMonth().isLeap());
    }

    public void testLeap_28feb_4_oe() {
        Chronology chrono = GJChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 28, 0, 0, chrono);
        assertEquals(false,dt.dayOfYear().isLeap());
    }

    public void testLeap_29feb_1_oe() {
        Chronology chrono = GJChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.year().isLeap());
    }

    public void testLeap_29feb_2_oe() {
        Chronology chrono = GJChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.monthOfYear().isLeap());
    }

    public void testLeap_29feb_3_oe() {
        Chronology chrono = GJChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.dayOfMonth().isLeap());
    }

    public void testLeap_29feb_4_oe() {
        Chronology chrono = GJChronology.getInstance();
        DateTime dt = new DateTime(2012, 2, 29, 0, 0, chrono);
        assertEquals(true,dt.dayOfYear().isLeap());
    }

public void testFactory_Zone_long_int_oe_101_oe() {
        try {
            GJChronology.getInstance(TOKYO, 0L, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testFactory_Zone_long_int_oe_102_oe() {
        try {
            GJChronology.getInstance(TOKYO, 0L, 8);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testFactory_Zone_RI_int_oe_101_oe() {
        try {
            GJChronology.getInstance(TOKYO, new Instant(0L), 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testFactory_Zone_RI_int_oe_102_oe() {
        try {
            GJChronology.getInstance(TOKYO, new Instant(0L), 8);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testIllegalDates_oe_101_oe() {
        try {
            new DateTime(1582, 10, 5, 0, 0, 0, 0, GJChronology.getInstance(DateTimeZone.UTC));
            fail("Constructed illegal date");
        } catch (IllegalArgumentException e) { /* good */ }
    }

public void testIllegalDates_oe_102_oe() {
        try {
            new DateTime(1582, 10, 14, 0, 0, 0, 0, GJChronology.getInstance(DateTimeZone.UTC));
            fail("Constructed illegal date");
        } catch (IllegalArgumentException e) { /* good */ }
    }

public void testLeapYearRulesConstructionInvalid_oe_101_oe() {
        try {
            new DateMidnight(1500, 2, 30, GJChronology.getInstanceUTC());
            fail();
        } catch (IllegalFieldValueException ex) {
        }
    }

}
