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
public class TestIslamicChronology extends TestCase {

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
        return new TestSuite(TestIslamicChronology.class);
    }

    public TestIslamicChronology(String name) {
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
        assertEquals(DateTimeZone.UTC, IslamicChronology.getInstanceUTC().getZone());
        assertEquals(IslamicChronology.class, IslamicChronology.getInstanceUTC().getClass());
    }

    public void testFactory() {
        assertEquals(LONDON, IslamicChronology.getInstance().getZone());
        assertEquals(IslamicChronology.class, IslamicChronology.getInstance().getClass());
    }

    public void testFactory_Zone() {
        assertEquals(TOKYO, IslamicChronology.getInstance(TOKYO).getZone());
        assertEquals(PARIS, IslamicChronology.getInstance(PARIS).getZone());
        assertEquals(LONDON, IslamicChronology.getInstance(null).getZone());
        assertEquals(IslamicChronology.class, IslamicChronology.getInstance(TOKYO).getClass());
    }

    //-----------------------------------------------------------------------
    public void testEquality() {
        assertEquals(IslamicChronology.getInstance(TOKYO), IslamicChronology.getInstance(TOKYO));
        assertEquals(IslamicChronology.getInstance(LONDON), IslamicChronology.getInstance(LONDON));
        assertEquals(IslamicChronology.getInstance(PARIS), IslamicChronology.getInstance(PARIS));
        assertEquals(IslamicChronology.getInstanceUTC(), IslamicChronology.getInstanceUTC());
        assertEquals(IslamicChronology.getInstance(), IslamicChronology.getInstance(LONDON));
    }

    public void testWithUTC() {
        assertEquals(IslamicChronology.getInstanceUTC(), IslamicChronology.getInstance(LONDON).withUTC());
        assertEquals(IslamicChronology.getInstanceUTC(), IslamicChronology.getInstance(TOKYO).withUTC());
        assertEquals(IslamicChronology.getInstanceUTC(), IslamicChronology.getInstanceUTC().withUTC());
        assertEquals(IslamicChronology.getInstanceUTC(), IslamicChronology.getInstance().withUTC());
    }

    public void testWithZone() {
        assertEquals(IslamicChronology.getInstance(TOKYO), IslamicChronology.getInstance(TOKYO).withZone(TOKYO));
        assertEquals(IslamicChronology.getInstance(LONDON), IslamicChronology.getInstance(TOKYO).withZone(LONDON));
        assertEquals(IslamicChronology.getInstance(PARIS), IslamicChronology.getInstance(TOKYO).withZone(PARIS));
        assertEquals(IslamicChronology.getInstance(LONDON), IslamicChronology.getInstance(TOKYO).withZone(null));
        assertEquals(IslamicChronology.getInstance(PARIS), IslamicChronology.getInstance().withZone(PARIS));
        assertEquals(IslamicChronology.getInstance(PARIS), IslamicChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString() {
        assertEquals("IslamicChronology[Europe/London]", IslamicChronology.getInstance(LONDON).toString());
        assertEquals("IslamicChronology[Asia/Tokyo]", IslamicChronology.getInstance(TOKYO).toString());
        assertEquals("IslamicChronology[Europe/London]", IslamicChronology.getInstance().toString());
        assertEquals("IslamicChronology[UTC]", IslamicChronology.getInstanceUTC().toString());
    }

    //-----------------------------------------------------------------------
    public void testDurationFields() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("eras", islamic.eras().getName());
        assertEquals("centuries", islamic.centuries().getName());
        assertEquals("years", islamic.years().getName());
        assertEquals("weekyears", islamic.weekyears().getName());
        assertEquals("months", islamic.months().getName());
        assertEquals("weeks", islamic.weeks().getName());
        assertEquals("days", islamic.days().getName());
        assertEquals("halfdays", islamic.halfdays().getName());
        assertEquals("hours", islamic.hours().getName());
        assertEquals("minutes", islamic.minutes().getName());
        assertEquals("seconds", islamic.seconds().getName());
        assertEquals("millis", islamic.millis().getName());
        
        assertEquals(false, islamic.eras().isSupported());
        assertEquals(true, islamic.centuries().isSupported());
        assertEquals(true, islamic.years().isSupported());
        assertEquals(true, islamic.weekyears().isSupported());
        assertEquals(true, islamic.months().isSupported());
        assertEquals(true, islamic.weeks().isSupported());
        assertEquals(true, islamic.days().isSupported());
        assertEquals(true, islamic.halfdays().isSupported());
        assertEquals(true, islamic.hours().isSupported());
        assertEquals(true, islamic.minutes().isSupported());
        assertEquals(true, islamic.seconds().isSupported());
        assertEquals(true, islamic.millis().isSupported());
        
        assertEquals(false, islamic.centuries().isPrecise());
        assertEquals(false, islamic.years().isPrecise());
        assertEquals(false, islamic.weekyears().isPrecise());
        assertEquals(false, islamic.months().isPrecise());
        assertEquals(false, islamic.weeks().isPrecise());
        assertEquals(false, islamic.days().isPrecise());
        assertEquals(false, islamic.halfdays().isPrecise());
        assertEquals(true, islamic.hours().isPrecise());
        assertEquals(true, islamic.minutes().isPrecise());
        assertEquals(true, islamic.seconds().isPrecise());
        assertEquals(true, islamic.millis().isPrecise());
        
        final IslamicChronology islamicUTC = IslamicChronology.getInstanceUTC();
        assertEquals(false, islamicUTC.centuries().isPrecise());
        assertEquals(false, islamicUTC.years().isPrecise());
        assertEquals(false, islamicUTC.weekyears().isPrecise());
        assertEquals(false, islamicUTC.months().isPrecise());
        assertEquals(true, islamicUTC.weeks().isPrecise());
        assertEquals(true, islamicUTC.days().isPrecise());
        assertEquals(true, islamicUTC.halfdays().isPrecise());
        assertEquals(true, islamicUTC.hours().isPrecise());
        assertEquals(true, islamicUTC.minutes().isPrecise());
        assertEquals(true, islamicUTC.seconds().isPrecise());
        assertEquals(true, islamicUTC.millis().isPrecise());
        
        final DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        final IslamicChronology islamicGMT = IslamicChronology.getInstance(gmt);
        assertEquals(false, islamicGMT.centuries().isPrecise());
        assertEquals(false, islamicGMT.years().isPrecise());
        assertEquals(false, islamicGMT.weekyears().isPrecise());
        assertEquals(false, islamicGMT.months().isPrecise());
        assertEquals(true, islamicGMT.weeks().isPrecise());
        assertEquals(true, islamicGMT.days().isPrecise());
        assertEquals(true, islamicGMT.halfdays().isPrecise());
        assertEquals(true, islamicGMT.hours().isPrecise());
        assertEquals(true, islamicGMT.minutes().isPrecise());
        assertEquals(true, islamicGMT.seconds().isPrecise());
        assertEquals(true, islamicGMT.millis().isPrecise());
    }

    public void testDateFields() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("era", islamic.era().getName());
        assertEquals("centuryOfEra", islamic.centuryOfEra().getName());
        assertEquals("yearOfCentury", islamic.yearOfCentury().getName());
        assertEquals("yearOfEra", islamic.yearOfEra().getName());
        assertEquals("year", islamic.year().getName());
        assertEquals("monthOfYear", islamic.monthOfYear().getName());
        assertEquals("weekyearOfCentury", islamic.weekyearOfCentury().getName());
        assertEquals("weekyear", islamic.weekyear().getName());
        assertEquals("weekOfWeekyear", islamic.weekOfWeekyear().getName());
        assertEquals("dayOfYear", islamic.dayOfYear().getName());
        assertEquals("dayOfMonth", islamic.dayOfMonth().getName());
        assertEquals("dayOfWeek", islamic.dayOfWeek().getName());
        
        assertEquals(true, islamic.era().isSupported());
        assertEquals(true, islamic.centuryOfEra().isSupported());
        assertEquals(true, islamic.yearOfCentury().isSupported());
        assertEquals(true, islamic.yearOfEra().isSupported());
        assertEquals(true, islamic.year().isSupported());
        assertEquals(true, islamic.monthOfYear().isSupported());
        assertEquals(true, islamic.weekyearOfCentury().isSupported());
        assertEquals(true, islamic.weekyear().isSupported());
        assertEquals(true, islamic.weekOfWeekyear().isSupported());
        assertEquals(true, islamic.dayOfYear().isSupported());
        assertEquals(true, islamic.dayOfMonth().isSupported());
        assertEquals(true, islamic.dayOfWeek().isSupported());
        
        assertEquals(islamic.eras(), islamic.era().getDurationField());
        assertEquals(islamic.centuries(), islamic.centuryOfEra().getDurationField());
        assertEquals(islamic.years(), islamic.yearOfCentury().getDurationField());
        assertEquals(islamic.years(), islamic.yearOfEra().getDurationField());
        assertEquals(islamic.years(), islamic.year().getDurationField());
        assertEquals(islamic.months(), islamic.monthOfYear().getDurationField());
        assertEquals(islamic.weekyears(), islamic.weekyearOfCentury().getDurationField());
        assertEquals(islamic.weekyears(), islamic.weekyear().getDurationField());
        assertEquals(islamic.weeks(), islamic.weekOfWeekyear().getDurationField());
        assertEquals(islamic.days(), islamic.dayOfYear().getDurationField());
        assertEquals(islamic.days(), islamic.dayOfMonth().getDurationField());
        assertEquals(islamic.days(), islamic.dayOfWeek().getDurationField());
        
        assertEquals(null, islamic.era().getRangeDurationField());
        assertEquals(islamic.eras(), islamic.centuryOfEra().getRangeDurationField());
        assertEquals(islamic.centuries(), islamic.yearOfCentury().getRangeDurationField());
        assertEquals(islamic.eras(), islamic.yearOfEra().getRangeDurationField());
        assertEquals(null, islamic.year().getRangeDurationField());
        assertEquals(islamic.years(), islamic.monthOfYear().getRangeDurationField());
        assertEquals(islamic.centuries(), islamic.weekyearOfCentury().getRangeDurationField());
        assertEquals(null, islamic.weekyear().getRangeDurationField());
        assertEquals(islamic.weekyears(), islamic.weekOfWeekyear().getRangeDurationField());
        assertEquals(islamic.years(), islamic.dayOfYear().getRangeDurationField());
        assertEquals(islamic.months(), islamic.dayOfMonth().getRangeDurationField());
        assertEquals(islamic.weeks(), islamic.dayOfWeek().getRangeDurationField());
    }

    public void testTimeFields() {
        final IslamicChronology islamic = IslamicChronology.getInstance();
        assertEquals("halfdayOfDay", islamic.halfdayOfDay().getName());
        assertEquals("clockhourOfHalfday", islamic.clockhourOfHalfday().getName());
        assertEquals("hourOfHalfday", islamic.hourOfHalfday().getName());
        assertEquals("clockhourOfDay", islamic.clockhourOfDay().getName());
        assertEquals("hourOfDay", islamic.hourOfDay().getName());
        assertEquals("minuteOfDay", islamic.minuteOfDay().getName());
        assertEquals("minuteOfHour", islamic.minuteOfHour().getName());
        assertEquals("secondOfDay", islamic.secondOfDay().getName());
        assertEquals("secondOfMinute", islamic.secondOfMinute().getName());
        assertEquals("millisOfDay", islamic.millisOfDay().getName());
        assertEquals("millisOfSecond", islamic.millisOfSecond().getName());
        
        assertEquals(true, islamic.halfdayOfDay().isSupported());
        assertEquals(true, islamic.clockhourOfHalfday().isSupported());
        assertEquals(true, islamic.hourOfHalfday().isSupported());
        assertEquals(true, islamic.clockhourOfDay().isSupported());
        assertEquals(true, islamic.hourOfDay().isSupported());
        assertEquals(true, islamic.minuteOfDay().isSupported());
        assertEquals(true, islamic.minuteOfHour().isSupported());
        assertEquals(true, islamic.secondOfDay().isSupported());
        assertEquals(true, islamic.secondOfMinute().isSupported());
        assertEquals(true, islamic.millisOfDay().isSupported());
        assertEquals(true, islamic.millisOfSecond().isSupported());
    }

    //-----------------------------------------------------------------------
    public void testEpoch() {
        DateTime epoch = new DateTime(1, 1, 1, 0, 0, 0, 0, ISLAMIC_UTC);
        DateTime expectedEpoch = new DateTime(622, 7, 16, 0, 0, 0, 0, JULIAN_UTC);
        assertEquals(expectedEpoch.getMillis(), epoch.getMillis());
    }

    public void testEra() {
        assertEquals(1, IslamicChronology.AH);
        try {
            new DateTime(-1, 13, 5, 0, 0, 0, 0, ISLAMIC_UTC);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testFieldConstructor() {
        DateTime date = new DateTime(1364, 12, 6, 0, 0, 0, 0, ISLAMIC_UTC);
        DateTime expectedDate = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        assertEquals(expectedDate.getMillis(), date.getMillis());
    }

    public void testSampleDate1() {
        DateTime dt = new DateTime(1945, 11, 12, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH, dt.getEra());
        assertEquals(14, dt.getCenturyOfEra());  // TODO confirm
        assertEquals(64, dt.getYearOfCentury());
        assertEquals(1364, dt.getYearOfEra());
        
        assertEquals(1364, dt.getYear());
        Property fld = dt.year();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(DurationFieldType.days(), fld.getLeapDurationField().getType());
        assertEquals(new DateTime(1365, 12, 6, 0, 0, 0, 0, ISLAMIC_UTC), fld.addToCopy(1));
        
        assertEquals(12, dt.getMonthOfYear());
        fld = dt.monthOfYear();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(DurationFieldType.days(), fld.getLeapDurationField().getType());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(12, fld.getMaximumValue());
        assertEquals(12, fld.getMaximumValueOverall());
        assertEquals(new DateTime(1365, 1, 6, 0, 0, 0, 0, ISLAMIC_UTC), fld.addToCopy(1));
        assertEquals(new DateTime(1364, 1, 6, 0, 0, 0, 0, ISLAMIC_UTC), fld.addWrapFieldToCopy(1));
        
        assertEquals(6, dt.getDayOfMonth());
        fld = dt.dayOfMonth();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(null, fld.getLeapDurationField());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(29, fld.getMaximumValue());
        assertEquals(30, fld.getMaximumValueOverall());
        assertEquals(new DateTime(1364, 12, 7, 0, 0, 0, 0, ISLAMIC_UTC), fld.addToCopy(1));
        
        assertEquals(DateTimeConstants.MONDAY, dt.getDayOfWeek());
        fld = dt.dayOfWeek();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(null, fld.getLeapDurationField());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(7, fld.getMaximumValue());
        assertEquals(7, fld.getMaximumValueOverall());
        assertEquals(new DateTime(1364, 12, 7, 0, 0, 0, 0, ISLAMIC_UTC), fld.addToCopy(1));
        
        assertEquals(6 * 30 + 5 * 29 + 6, dt.getDayOfYear());
        fld = dt.dayOfYear();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(null, fld.getLeapDurationField());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(354, fld.getMaximumValue());
        assertEquals(355, fld.getMaximumValueOverall());
        assertEquals(new DateTime(1364, 12, 7, 0, 0, 0, 0, ISLAMIC_UTC), fld.addToCopy(1));
        
        assertEquals(0, dt.getHourOfDay());
        assertEquals(0, dt.getMinuteOfHour());
        assertEquals(0, dt.getSecondOfMinute());
        assertEquals(0, dt.getMillisOfSecond());
    }

    public void testSampleDate2() {
        DateTime dt = new DateTime(2005, 11, 26, 0, 0, 0, 0, ISO_UTC);
        dt = dt.withChronology(ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH, dt.getEra());
        assertEquals(15, dt.getCenturyOfEra());  // TODO confirm
        assertEquals(26, dt.getYearOfCentury());
        assertEquals(1426, dt.getYearOfEra());
        
        assertEquals(1426, dt.getYear());
        Property fld = dt.year();
        assertEquals(true, fld.isLeap());
        assertEquals(1, fld.getLeapAmount());
        assertEquals(DurationFieldType.days(), fld.getLeapDurationField().getType());
        
        assertEquals(10, dt.getMonthOfYear());
        fld = dt.monthOfYear();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(DurationFieldType.days(), fld.getLeapDurationField().getType());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(12, fld.getMaximumValue());
        assertEquals(12, fld.getMaximumValueOverall());
        
        assertEquals(24, dt.getDayOfMonth());
        fld = dt.dayOfMonth();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(null, fld.getLeapDurationField());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(29, fld.getMaximumValue());
        assertEquals(30, fld.getMaximumValueOverall());
        
        assertEquals(DateTimeConstants.SATURDAY, dt.getDayOfWeek());
        fld = dt.dayOfWeek();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(null, fld.getLeapDurationField());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(7, fld.getMaximumValue());
        assertEquals(7, fld.getMaximumValueOverall());
        
        assertEquals(5 * 30 + 4 * 29 + 24, dt.getDayOfYear());
        fld = dt.dayOfYear();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(null, fld.getLeapDurationField());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(355, fld.getMaximumValue());
        assertEquals(355, fld.getMaximumValueOverall());
        
        assertEquals(0, dt.getHourOfDay());
        assertEquals(0, dt.getMinuteOfHour());
        assertEquals(0, dt.getSecondOfMinute());
        assertEquals(0, dt.getMillisOfSecond());
    }

    public void testSampleDate3() {
        DateTime dt = new DateTime(1426, 12, 24, 0, 0, 0, 0, ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH, dt.getEra());
        
        assertEquals(1426, dt.getYear());
        Property fld = dt.year();
        assertEquals(true, fld.isLeap());
        assertEquals(1, fld.getLeapAmount());
        assertEquals(DurationFieldType.days(), fld.getLeapDurationField().getType());
        
        assertEquals(12, dt.getMonthOfYear());
        fld = dt.monthOfYear();
        assertEquals(true, fld.isLeap());
        assertEquals(1, fld.getLeapAmount());
        assertEquals(DurationFieldType.days(), fld.getLeapDurationField().getType());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(12, fld.getMaximumValue());
        assertEquals(12, fld.getMaximumValueOverall());
        
        assertEquals(24, dt.getDayOfMonth());
        fld = dt.dayOfMonth();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(null, fld.getLeapDurationField());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(30, fld.getMaximumValue());
        assertEquals(30, fld.getMaximumValueOverall());
        
        assertEquals(DateTimeConstants.TUESDAY, dt.getDayOfWeek());
        fld = dt.dayOfWeek();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(null, fld.getLeapDurationField());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(7, fld.getMaximumValue());
        assertEquals(7, fld.getMaximumValueOverall());
        
        assertEquals(6 * 30 + 5 * 29 + 24, dt.getDayOfYear());
        fld = dt.dayOfYear();
        assertEquals(false, fld.isLeap());
        assertEquals(0, fld.getLeapAmount());
        assertEquals(null, fld.getLeapDurationField());
        assertEquals(1, fld.getMinimumValue());
        assertEquals(1, fld.getMinimumValueOverall());
        assertEquals(355, fld.getMaximumValue());
        assertEquals(355, fld.getMaximumValueOverall());
        
        assertEquals(0, dt.getHourOfDay());
        assertEquals(0, dt.getMinuteOfHour());
        assertEquals(0, dt.getSecondOfMinute());
        assertEquals(0, dt.getMillisOfSecond());
    }

    public void testSampleDateWithZone() {
        DateTime dt = new DateTime(2005, 11, 26, 12, 0, 0, 0, PARIS).withChronology(ISLAMIC_UTC);
        assertEquals(IslamicChronology.AH, dt.getEra());
        assertEquals(1426, dt.getYear());
        assertEquals(10, dt.getMonthOfYear());
        assertEquals(24, dt.getDayOfMonth());
        assertEquals(11, dt.getHourOfDay());  // PARIS is UTC+1 in summer (12-1=11)
        assertEquals(0, dt.getMinuteOfHour());
        assertEquals(0, dt.getSecondOfMinute());
        assertEquals(0, dt.getMillisOfSecond());
    }

    public void test15BasedLeapYear() {
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(1));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(2));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(3));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(4));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(5));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(6));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(7));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(8));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(9));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(10));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(11));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(12));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(13));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(14));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(15));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(16));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(17));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(18));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(19));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(20));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(21));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(22));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(23));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(24));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(25));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(26));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(27));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(28));
        assertEquals(true, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(29));
        assertEquals(false, IslamicChronology.LEAP_YEAR_15_BASED.isLeapYear(30));
    }

    public void test16BasedLeapYear() {
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(1));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(2));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(3));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(4));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(5));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(6));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(7));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(8));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(9));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(10));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(11));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(12));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(13));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(14));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(15));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(16));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(17));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(18));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(19));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(20));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(21));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(22));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(23));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(24));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(25));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(26));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(27));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(28));
        assertEquals(true, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(29));
        assertEquals(false, IslamicChronology.LEAP_YEAR_16_BASED.isLeapYear(30));
    }

    public void testIndianBasedLeapYear() {
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(1));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(2));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(3));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(4));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(5));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(6));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(7));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(8));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(9));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(10));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(11));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(12));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(13));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(14));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(15));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(16));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(17));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(18));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(19));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(20));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(21));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(22));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(23));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(24));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(25));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(26));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(27));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(28));
        assertEquals(true, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(29));
        assertEquals(false, IslamicChronology.LEAP_YEAR_INDIAN.isLeapYear(30));
    }

    public void testHabashAlHasibBasedLeapYear() {
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(1));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(2));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(3));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(4));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(5));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(6));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(7));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(8));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(9));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(10));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(11));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(12));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(13));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(14));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(15));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(16));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(17));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(18));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(19));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(20));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(21));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(22));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(23));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(24));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(25));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(26));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(27));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(28));
        assertEquals(false, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(29));
        assertEquals(true, IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB.isLeapYear(30));
    }

}
