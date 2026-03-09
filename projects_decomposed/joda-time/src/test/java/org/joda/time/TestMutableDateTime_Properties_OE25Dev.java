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
package org.joda.time;

import java.util.Locale;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This class is a Junit unit test for DateTime.
 *
 * @author Stephen Colebourne
 * @author Mike Schrag
 */
public class TestMutableDateTime_Properties_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    //private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    
    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    long y2003days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365 + 365;
    
    // 2002-06-09
    private long TEST_TIME_NOW =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
    // 2002-04-05 Fri
    private long TEST_TIME1 =
            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 12L * DateTimeConstants.MILLIS_PER_HOUR
            + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    // 2003-05-06 Tue
    private long TEST_TIME2 =
            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 14L * DateTimeConstants.MILLIS_PER_HOUR
            + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    private DateTimeZone zone = null;
    private Locale locale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMutableDateTime_Properties_OE25Dev.class);
    }

    public TestMutableDateTime_Properties_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        locale = Locale.getDefault();
        DateTimeZone.setDefault(LONDON);
        Locale.setDefault(Locale.UK);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        Locale.setDefault(locale);
        zone = null;
    }

    //-----------------------------------------------------------------------
    public void testTest() {
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW).toString());
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1).toString());
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2).toString());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetEra() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().era(),test.era().getField());
        assertEquals("era",test.era().getName());
        assertEquals("Property[era]",test.era().toString());
        assertSame(test,test.era().getMutableDateTime());
        assertEquals(1,test.era().get());
        assertEquals("AD",test.era().getAsText());
        assertEquals("ap. J.-C.",test.era().getAsText(Locale.FRENCH));
        assertEquals("AD",test.era().getAsShortText());
        assertEquals("ap. J.-C.",test.era().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().eras(),test.era().getDurationField());
        assertEquals(null,test.era().getRangeDurationField());
        assertEquals(2,test.era().getMaximumTextLength(null));
        assertEquals(9,test.era().getMaximumTextLength(Locale.FRENCH));
        assertEquals(2,test.era().getMaximumShortTextLength(null));
        assertEquals(9,test.era().getMaximumShortTextLength(Locale.FRENCH));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetYearOfEra() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().yearOfEra(),test.yearOfEra().getField());
        assertEquals("yearOfEra",test.yearOfEra().getName());
        assertEquals("Property[yearOfEra]",test.yearOfEra().toString());
        assertEquals(2004,test.yearOfEra().get());
        assertEquals("2004",test.yearOfEra().getAsText());
        assertEquals("2004",test.yearOfEra().getAsText(Locale.FRENCH));
        assertEquals("2004",test.yearOfEra().getAsShortText());
        assertEquals("2004",test.yearOfEra().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().years(),test.yearOfEra().getDurationField());
        assertEquals(test.getChronology().eras(),test.yearOfEra().getRangeDurationField());
        assertEquals(9,test.yearOfEra().getMaximumTextLength(null));
        assertEquals(9,test.yearOfEra().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetCenturyOfEra() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().centuryOfEra(),test.centuryOfEra().getField());
        assertEquals("centuryOfEra",test.centuryOfEra().getName());
        assertEquals("Property[centuryOfEra]",test.centuryOfEra().toString());
        assertEquals(20,test.centuryOfEra().get());
        assertEquals("20",test.centuryOfEra().getAsText());
        assertEquals("20",test.centuryOfEra().getAsText(Locale.FRENCH));
        assertEquals("20",test.centuryOfEra().getAsShortText());
        assertEquals("20",test.centuryOfEra().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().centuries(),test.centuryOfEra().getDurationField());
        assertEquals(test.getChronology().eras(),test.centuryOfEra().getRangeDurationField());
        assertEquals(7,test.centuryOfEra().getMaximumTextLength(null));
        assertEquals(7,test.centuryOfEra().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetYearOfCentury() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().yearOfCentury(),test.yearOfCentury().getField());
        assertEquals("yearOfCentury",test.yearOfCentury().getName());
        assertEquals("Property[yearOfCentury]",test.yearOfCentury().toString());
        assertEquals(4,test.yearOfCentury().get());
        assertEquals("4",test.yearOfCentury().getAsText());
        assertEquals("4",test.yearOfCentury().getAsText(Locale.FRENCH));
        assertEquals("4",test.yearOfCentury().getAsShortText());
        assertEquals("4",test.yearOfCentury().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().years(),test.yearOfCentury().getDurationField());
        assertEquals(test.getChronology().centuries(),test.yearOfCentury().getRangeDurationField());
        assertEquals(2,test.yearOfCentury().getMaximumTextLength(null));
        assertEquals(2,test.yearOfCentury().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetWeekyear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().weekyear(),test.weekyear().getField());
        assertEquals("weekyear",test.weekyear().getName());
        assertEquals("Property[weekyear]",test.weekyear().toString());
        assertEquals(2004,test.weekyear().get());
        assertEquals("2004",test.weekyear().getAsText());
        assertEquals("2004",test.weekyear().getAsText(Locale.FRENCH));
        assertEquals("2004",test.weekyear().getAsShortText());
        assertEquals("2004",test.weekyear().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().weekyears(),test.weekyear().getDurationField());
        assertEquals(null,test.weekyear().getRangeDurationField());
        assertEquals(9,test.weekyear().getMaximumTextLength(null));
        assertEquals(9,test.weekyear().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().year(),test.year().getField());
        assertEquals("year",test.year().getName());
        assertEquals("Property[year]",test.year().toString());
        assertEquals(2004,test.year().get());
        assertEquals("2004",test.year().getAsText());
        assertEquals("2004",test.year().getAsText(Locale.FRENCH));
        assertEquals("2004",test.year().getAsShortText());
        assertEquals("2004",test.year().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().years(),test.year().getDurationField());
        assertEquals(null,test.year().getRangeDurationField());
        assertEquals(9,test.year().getMaximumTextLength(null));
        assertEquals(9,test.year().getMaximumShortTextLength(null));
        assertEquals(-292275054,test.year().getMinimumValue());
        assertEquals(-292275054,test.year().getMinimumValueOverall());
        assertEquals(292278993,test.year().getMaximumValue());
        assertEquals(292278993,test.year().getMaximumValueOverall());
    }

    public void testPropertyAddYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().add(9);
        assertEquals("2013-06-09T00:00:00.000+01:00",test.toString());
    }

    public void testPropertyAddWrapFieldYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().addWrapField(9);
        assertEquals("2013-06-09T00:00:00.000+01:00",test.toString());
    }

    public void testPropertySetYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().set(1960);
        assertEquals("1960-06-09T00:00:00.000+01:00",test.toString());
    }

    public void testPropertySetTextYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().set("1960");
        assertEquals("1960-06-09T00:00:00.000+01:00",test.toString());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetMonthOfYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().monthOfYear(),test.monthOfYear().getField());
        assertEquals("monthOfYear",test.monthOfYear().getName());
        assertEquals("Property[monthOfYear]",test.monthOfYear().toString());
        assertEquals(6,test.monthOfYear().get());
        assertEquals("June",test.monthOfYear().getAsText());
        assertEquals("juin",test.monthOfYear().getAsText(Locale.FRENCH));
        assertEquals("Jun",test.monthOfYear().getAsShortText());
        assertEquals("juin",test.monthOfYear().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().months(),test.monthOfYear().getDurationField());
        assertEquals(test.getChronology().years(),test.monthOfYear().getRangeDurationField());
        assertEquals(9,test.monthOfYear().getMaximumTextLength(null));
        assertEquals(3,test.monthOfYear().getMaximumShortTextLength(null));
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals("juillet",test.monthOfYear().getAsText(Locale.FRENCH));
        assertEquals("juil.",test.monthOfYear().getAsShortText(Locale.FRENCH));
        assertEquals(1,test.monthOfYear().getMinimumValue());
        assertEquals(1,test.monthOfYear().getMinimumValueOverall());
        assertEquals(12,test.monthOfYear().getMaximumValue());
        assertEquals(12,test.monthOfYear().getMaximumValueOverall());
        assertEquals(1,test.monthOfYear().getMinimumValue());
        assertEquals(1,test.monthOfYear().getMinimumValueOverall());
        assertEquals(12,test.monthOfYear().getMaximumValue());
        assertEquals(12,test.monthOfYear().getMaximumValueOverall());
    }

    public void testPropertyAddMonthOfYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().add(6);
        assertEquals("2004-12-09T00:00:00.000Z",test.toString());
    }

    public void testPropertyAddWrapFieldMonthOfYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().addWrapField(8);
        assertEquals("2004-02-09T00:00:00.000Z",test.toString());
    }

    public void testPropertySetMonthOfYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set(12);
        assertEquals("2004-12-09T00:00:00.000Z",test.toString());
    }

    public void testPropertySetTextMonthOfYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("12");
        assertEquals("2004-12-09T00:00:00.000Z",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("December");
        assertEquals("2004-12-09T00:00:00.000Z",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("Dec");
        assertEquals("2004-12-09T00:00:00.000Z",test.toString());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetDayOfMonth() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().dayOfMonth(),test.dayOfMonth().getField());
        assertEquals("dayOfMonth",test.dayOfMonth().getName());
        assertEquals("Property[dayOfMonth]",test.dayOfMonth().toString());
        assertEquals(9,test.dayOfMonth().get());
        assertEquals("9",test.dayOfMonth().getAsText());
        assertEquals("9",test.dayOfMonth().getAsText(Locale.FRENCH));
        assertEquals("9",test.dayOfMonth().getAsShortText());
        assertEquals("9",test.dayOfMonth().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().days(),test.dayOfMonth().getDurationField());
        assertEquals(test.getChronology().months(),test.dayOfMonth().getRangeDurationField());
        assertEquals(2,test.dayOfMonth().getMaximumTextLength(null));
        assertEquals(2,test.dayOfMonth().getMaximumShortTextLength(null));
        assertEquals(1,test.dayOfMonth().getMinimumValue());
        assertEquals(1,test.dayOfMonth().getMinimumValueOverall());
        assertEquals(30,test.dayOfMonth().getMaximumValue());
        assertEquals(31,test.dayOfMonth().getMaximumValueOverall());
        assertEquals(false,test.dayOfMonth().isLeap());
        assertEquals(0,test.dayOfMonth().getLeapAmount());
        assertEquals(null,test.dayOfMonth().getLeapDurationField());
    }

    public void testPropertyAddDayOfMonth() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().add(9);
        assertEquals("2004-06-18T00:00:00.000+01:00",test.toString());
    }

    public void testPropertyAddWrapFieldDayOfMonth() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().addWrapField(22);
        assertEquals("2004-06-01T00:00:00.000+01:00",test.toString());
    }

    public void testPropertySetDayOfMonth() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().set(12);
        assertEquals("2004-06-12T00:00:00.000+01:00",test.toString());
    }

    public void testPropertySetTextDayOfMonth() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().set("12");
        assertEquals("2004-06-12T00:00:00.000+01:00",test.toString());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetDayOfYear() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().dayOfYear(),test.dayOfYear().getField());
        assertEquals("dayOfYear",test.dayOfYear().getName());
        assertEquals("Property[dayOfYear]",test.dayOfYear().toString());
        assertEquals(161,test.dayOfYear().get());
        assertEquals("161",test.dayOfYear().getAsText());
        assertEquals("161",test.dayOfYear().getAsText(Locale.FRENCH));
        assertEquals("161",test.dayOfYear().getAsShortText());
        assertEquals("161",test.dayOfYear().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().days(),test.dayOfYear().getDurationField());
        assertEquals(test.getChronology().years(),test.dayOfYear().getRangeDurationField());
        assertEquals(3,test.dayOfYear().getMaximumTextLength(null));
        assertEquals(3,test.dayOfYear().getMaximumShortTextLength(null));
        assertEquals(false,test.dayOfYear().isLeap());
        assertEquals(0,test.dayOfYear().getLeapAmount());
        assertEquals(null,test.dayOfYear().getLeapDurationField());
    }

    public void testPropertyAddDayOfYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().add(9);
        assertEquals("2004-06-18T00:00:00.000+01:00",test.toString());
    }

    public void testPropertyAddWrapFieldDayOfYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().addWrapField(206);
        assertEquals("2004-01-01T00:00:00.000Z",test.toString());
    }

    public void testPropertySetDayOfYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().set(12);
        assertEquals("2004-01-12T00:00:00.000Z",test.toString());
    }

    public void testPropertySetTextDayOfYear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().set("12");
        assertEquals("2004-01-12T00:00:00.000Z",test.toString());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetWeekOfWeekyear() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().weekOfWeekyear(),test.weekOfWeekyear().getField());
        assertEquals("weekOfWeekyear",test.weekOfWeekyear().getName());
        assertEquals("Property[weekOfWeekyear]",test.weekOfWeekyear().toString());
        assertEquals(24,test.weekOfWeekyear().get());
        assertEquals("24",test.weekOfWeekyear().getAsText());
        assertEquals("24",test.weekOfWeekyear().getAsText(Locale.FRENCH));
        assertEquals("24",test.weekOfWeekyear().getAsShortText());
        assertEquals("24",test.weekOfWeekyear().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().weeks(),test.weekOfWeekyear().getDurationField());
        assertEquals(test.getChronology().weekyears(),test.weekOfWeekyear().getRangeDurationField());
        assertEquals(2,test.weekOfWeekyear().getMaximumTextLength(null));
        assertEquals(2,test.weekOfWeekyear().getMaximumShortTextLength(null));
        assertEquals(false,test.weekOfWeekyear().isLeap());
        assertEquals(0,test.weekOfWeekyear().getLeapAmount());
        assertEquals(null,test.weekOfWeekyear().getLeapDurationField());
    }

    public void testPropertyAddWeekOfWeekyear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().add(1);
        assertEquals("2004-06-14T00:00:00.000+01:00",test.toString());
    }

    public void testPropertyAddWrapFieldWeekOfWeekyear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().addWrapField(30);
        assertEquals("2003-12-29T00:00:00.000Z",test.toString());
    }

    public void testPropertySetWeekOfWeekyear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().set(4);
        assertEquals("2004-01-19T00:00:00.000Z",test.toString());
    }

    public void testPropertySetTextWeekOfWeekyear() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().set("4");
        assertEquals("2004-01-19T00:00:00.000Z",test.toString());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetDayOfWeek() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().dayOfWeek(),test.dayOfWeek().getField());
        assertEquals("dayOfWeek",test.dayOfWeek().getName());
        assertEquals("Property[dayOfWeek]",test.dayOfWeek().toString());
        assertEquals(3,test.dayOfWeek().get());
        assertEquals("Wednesday",test.dayOfWeek().getAsText());
        assertEquals("mercredi",test.dayOfWeek().getAsText(Locale.FRENCH));
        assertEquals("Wed",test.dayOfWeek().getAsShortText());
        assertEquals("mer.",test.dayOfWeek().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().days(),test.dayOfWeek().getDurationField());
        assertEquals(test.getChronology().weeks(),test.dayOfWeek().getRangeDurationField());
        assertEquals(9,test.dayOfWeek().getMaximumTextLength(null));
        assertEquals(8,test.dayOfWeek().getMaximumTextLength(Locale.FRENCH));
        assertEquals(3,test.dayOfWeek().getMaximumShortTextLength(null));
        assertEquals(4,test.dayOfWeek().getMaximumShortTextLength(Locale.FRENCH));
        assertEquals(1,test.dayOfWeek().getMinimumValue());
        assertEquals(1,test.dayOfWeek().getMinimumValueOverall());
        assertEquals(7,test.dayOfWeek().getMaximumValue());
        assertEquals(7,test.dayOfWeek().getMaximumValueOverall());
        assertEquals(false,test.dayOfWeek().isLeap());
        assertEquals(0,test.dayOfWeek().getLeapAmount());
        assertEquals(null,test.dayOfWeek().getLeapDurationField());
    }

    public void testPropertyAddDayOfWeek() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().add(1);
        assertEquals("2004-06-10T00:00:00.000+01:00",test.toString());
    }

    public void testPropertyAddLongDayOfWeek() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().add(1L);
        assertEquals("2004-06-10T00:00:00.000+01:00",test.toString());
    }

    public void testPropertyAddWrapFieldDayOfWeek() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);  // Wed
        test.dayOfWeek().addWrapField(5);
        assertEquals("2004-06-07T00:00:00.000+01:00",test.toString());
    }

    public void testPropertySetDayOfWeek() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set(4);
        assertEquals("2004-06-10T00:00:00.000+01:00",test.toString());
    }

    public void testPropertySetTextDayOfWeek() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("4");
        assertEquals("2004-06-10T00:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Mon");
        assertEquals("2004-06-07T00:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Tuesday");
        assertEquals("2004-06-08T00:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("lundi", Locale.FRENCH);
        assertEquals("2004-06-07T00:00:00.000+01:00",test.toString());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetHourOfDay() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().hourOfDay(),test.hourOfDay().getField());
        assertEquals("hourOfDay",test.hourOfDay().getName());
        assertEquals("Property[hourOfDay]",test.hourOfDay().toString());
        assertEquals(13,test.hourOfDay().get());
        assertEquals("13",test.hourOfDay().getAsText());
        assertEquals("13",test.hourOfDay().getAsText(Locale.FRENCH));
        assertEquals("13",test.hourOfDay().getAsShortText());
        assertEquals("13",test.hourOfDay().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().hours(),test.hourOfDay().getDurationField());
        assertEquals(test.getChronology().days(),test.hourOfDay().getRangeDurationField());
        assertEquals(2,test.hourOfDay().getMaximumTextLength(null));
        assertEquals(2,test.hourOfDay().getMaximumShortTextLength(null));
    }

    public void testPropertyRoundFloorHourOfDay() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundFloor();
        assertEquals("2004-06-09T13:00:00.000+01:00",test.toString());
    }

    public void testPropertyRoundCeilingHourOfDay() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundCeiling();
        assertEquals("2004-06-09T14:00:00.000+01:00",test.toString());
    }

    public void testPropertyRoundHalfFloorHourOfDay() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfFloor();
        assertEquals("2004-06-09T13:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfFloor();
        assertEquals("2004-06-09T14:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 13, 29, 59, 999);
        test.hourOfDay().roundHalfFloor();
        assertEquals("2004-06-09T13:00:00.000+01:00",test.toString());
    }

    public void testPropertyRoundHalfCeilingHourOfDay() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfCeiling();
        assertEquals("2004-06-09T14:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfCeiling();
        assertEquals("2004-06-09T14:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 13, 29, 59, 999);
        test.hourOfDay().roundHalfCeiling();
        assertEquals("2004-06-09T13:00:00.000+01:00",test.toString());
    }

    public void testPropertyRoundHalfEvenHourOfDay() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        assertEquals("2004-06-09T14:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 14, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        assertEquals("2004-06-09T14:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfEven();
        assertEquals("2004-06-09T14:00:00.000+01:00",test.toString());
        
        test = new MutableDateTime(2004, 6, 9, 13, 29, 59, 999);
        test.hourOfDay().roundHalfEven();
        assertEquals("2004-06-09T13:00:00.000+01:00",test.toString());
    }

    public void testPropertyRemainderHourOfDay() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        assertEquals(30L * DateTimeConstants.MILLIS_PER_MINUTE,test.hourOfDay().remainder());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetMinuteOfHour() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().minuteOfHour(),test.minuteOfHour().getField());
        assertEquals("minuteOfHour",test.minuteOfHour().getName());
        assertEquals("Property[minuteOfHour]",test.minuteOfHour().toString());
        assertEquals(23,test.minuteOfHour().get());
        assertEquals("23",test.minuteOfHour().getAsText());
        assertEquals("23",test.minuteOfHour().getAsText(Locale.FRENCH));
        assertEquals("23",test.minuteOfHour().getAsShortText());
        assertEquals("23",test.minuteOfHour().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().minutes(),test.minuteOfHour().getDurationField());
        assertEquals(test.getChronology().hours(),test.minuteOfHour().getRangeDurationField());
        assertEquals(2,test.minuteOfHour().getMaximumTextLength(null));
        assertEquals(2,test.minuteOfHour().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetMinuteOfDay() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().minuteOfDay(),test.minuteOfDay().getField());
        assertEquals("minuteOfDay",test.minuteOfDay().getName());
        assertEquals("Property[minuteOfDay]",test.minuteOfDay().toString());
        assertEquals(803,test.minuteOfDay().get());
        assertEquals("803",test.minuteOfDay().getAsText());
        assertEquals("803",test.minuteOfDay().getAsText(Locale.FRENCH));
        assertEquals("803",test.minuteOfDay().getAsShortText());
        assertEquals("803",test.minuteOfDay().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().minutes(),test.minuteOfDay().getDurationField());
        assertEquals(test.getChronology().days(),test.minuteOfDay().getRangeDurationField());
        assertEquals(4,test.minuteOfDay().getMaximumTextLength(null));
        assertEquals(4,test.minuteOfDay().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetSecondOfMinute() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().secondOfMinute(),test.secondOfMinute().getField());
        assertEquals("secondOfMinute",test.secondOfMinute().getName());
        assertEquals("Property[secondOfMinute]",test.secondOfMinute().toString());
        assertEquals(43,test.secondOfMinute().get());
        assertEquals("43",test.secondOfMinute().getAsText());
        assertEquals("43",test.secondOfMinute().getAsText(Locale.FRENCH));
        assertEquals("43",test.secondOfMinute().getAsShortText());
        assertEquals("43",test.secondOfMinute().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().seconds(),test.secondOfMinute().getDurationField());
        assertEquals(test.getChronology().minutes(),test.secondOfMinute().getRangeDurationField());
        assertEquals(2,test.secondOfMinute().getMaximumTextLength(null));
        assertEquals(2,test.secondOfMinute().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetSecondOfDay() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().secondOfDay(),test.secondOfDay().getField());
        assertEquals("secondOfDay",test.secondOfDay().getName());
        assertEquals("Property[secondOfDay]",test.secondOfDay().toString());
        assertEquals(48223,test.secondOfDay().get());
        assertEquals("48223",test.secondOfDay().getAsText());
        assertEquals("48223",test.secondOfDay().getAsText(Locale.FRENCH));
        assertEquals("48223",test.secondOfDay().getAsShortText());
        assertEquals("48223",test.secondOfDay().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().seconds(),test.secondOfDay().getDurationField());
        assertEquals(test.getChronology().days(),test.secondOfDay().getRangeDurationField());
        assertEquals(5,test.secondOfDay().getMaximumTextLength(null));
        assertEquals(5,test.secondOfDay().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetMillisOfSecond() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().millisOfSecond(),test.millisOfSecond().getField());
        assertEquals("millisOfSecond",test.millisOfSecond().getName());
        assertEquals("Property[millisOfSecond]",test.millisOfSecond().toString());
        assertEquals(53,test.millisOfSecond().get());
        assertEquals("53",test.millisOfSecond().getAsText());
        assertEquals("53",test.millisOfSecond().getAsText(Locale.FRENCH));
        assertEquals("53",test.millisOfSecond().getAsShortText());
        assertEquals("53",test.millisOfSecond().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().millis(),test.millisOfSecond().getDurationField());
        assertEquals(test.getChronology().seconds(),test.millisOfSecond().getRangeDurationField());
        assertEquals(3,test.millisOfSecond().getMaximumTextLength(null));
        assertEquals(3,test.millisOfSecond().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetMillisOfDay() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().millisOfDay(),test.millisOfDay().getField());
        assertEquals("millisOfDay",test.millisOfDay().getName());
        assertEquals("Property[millisOfDay]",test.millisOfDay().toString());
        assertEquals(48223053,test.millisOfDay().get());
        assertEquals("48223053",test.millisOfDay().getAsText());
        assertEquals("48223053",test.millisOfDay().getAsText(Locale.FRENCH));
        assertEquals("48223053",test.millisOfDay().getAsShortText());
        assertEquals("48223053",test.millisOfDay().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().millis(),test.millisOfDay().getDurationField());
        assertEquals(test.getChronology().days(),test.millisOfDay().getRangeDurationField());
        assertEquals(8,test.millisOfDay().getMaximumTextLength(null));
        assertEquals(8,test.millisOfDay().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyToIntervalYearOfEra() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfEra().toInterval();
      assertEquals(new MutableDateTime(2004,1,1,0,0,0,0),testInterval.getStart());
      assertEquals(new MutableDateTime(2005,1,1,0,0,0,0),testInterval.getEnd());
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,53),test);
    }

    public void testPropertyToIntervalYearOfCentury() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfCentury().toInterval();
      assertEquals(new MutableDateTime(2004,1,1,0,0,0,0),testInterval.getStart());
      assertEquals(new MutableDateTime(2005,1,1,0,0,0,0),testInterval.getEnd());
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,53),test);
    }

    public void testPropertyToIntervalYear() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.year().toInterval();
      assertEquals(new MutableDateTime(2004,1,1,0,0,0,0),testInterval.getStart());
      assertEquals(new MutableDateTime(2005,1,1,0,0,0,0),testInterval.getEnd());
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,53),test);
    }

    public void testPropertyToIntervalMonthOfYear() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.monthOfYear().toInterval();
      assertEquals(new MutableDateTime(2004,6,1,0,0,0,0),testInterval.getStart());
      assertEquals(new MutableDateTime(2004,7,1,0,0,0,0),testInterval.getEnd());
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,53),test);
    }

    public void testPropertyToIntervalDayOfMonth() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();
      assertEquals(new MutableDateTime(2004,6,9,0,0,0,0),testInterval.getStart());
      assertEquals(new MutableDateTime(2004,6,10,0,0,0,0),testInterval.getEnd());
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,53),test);

      MutableDateTime febTest = new MutableDateTime(2004, 2, 29, 13, 23, 43, 53);
      Interval febTestInterval = febTest.dayOfMonth().toInterval();
      assertEquals(new MutableDateTime(2004,2,29,0,0,0,0),febTestInterval.getStart());
      assertEquals(new MutableDateTime(2004,3,1,0,0,0,0),febTestInterval.getEnd());
      assertEquals(new MutableDateTime(2004,2,29,13,23,43,53),febTest);
    }

    public void testPropertyToIntervalHourOfDay() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();
      assertEquals(new MutableDateTime(2004,6,9,13,0,0,0),testInterval.getStart());
      assertEquals(new MutableDateTime(2004,6,9,14,0,0,0),testInterval.getEnd());
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,53),test);

      MutableDateTime midnightTest = new MutableDateTime(2004, 6, 9, 23, 23, 43, 53);
      Interval midnightTestInterval = midnightTest.hourOfDay().toInterval();
      assertEquals(new MutableDateTime(2004,6,9,23,0,0,0),midnightTestInterval.getStart());
      assertEquals(new MutableDateTime(2004,6,10,0,0,0,0),midnightTestInterval.getEnd());
      assertEquals(new MutableDateTime(2004,6,9,23,23,43,53),midnightTest);
    }

    public void testPropertyToIntervalMinuteOfHour() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.minuteOfHour().toInterval();
      assertEquals(new MutableDateTime(2004,6,9,13,23,0,0),testInterval.getStart());
      assertEquals(new MutableDateTime(2004,6,9,13,24,0,0),testInterval.getEnd());
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,53),test);
    }

    public void testPropertyToIntervalSecondOfMinute() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.secondOfMinute().toInterval();
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,0),testInterval.getStart());
      assertEquals(new MutableDateTime(2004,6,9,13,23,44,0),testInterval.getEnd());
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,53),test);
    }

    public void testPropertyToIntervalMillisOfSecond() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.millisOfSecond().toInterval();
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,53),testInterval.getStart());
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,54),testInterval.getEnd());
      assertEquals(new MutableDateTime(2004,6,9,13,23,43,53),test);
    }

    public void testTest_1_oe() {
        Object a = new Instant(TEST_TIME_NOW).toString();
        assertNotNull(a);
    }

    public void testTest_2_oe() {
        Object a = new Instant(TEST_TIME1).toString();
        assertEquals("1970-01-01T01:00:00Z", a);
    }

    public void testTest_3_oe() {
        Object a = new Instant(TEST_TIME2).toString();
        assertEquals("1970-01-01T01:00:00Z", a);
    }

    public void testPropertyGetEra_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetEra_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetEra_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetYearOfEra_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetYearOfEra_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfEra().get());
    }

    public void testPropertyGetYearOfEra_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfEra().get());
    }

    public void testPropertyGetYearOfEra_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004", test.yearOfEra().toString());
    }

    public void testPropertyGetYearOfEra_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfEra().get());
    }

    public void testPropertyGetYearOfEra_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfEra().get());
    }

    public void testPropertyGetYearOfEra_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfEra().get());
    }

    public void testPropertyGetYearOfEra_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfEra().get());
    }

    public void testPropertyGetYearOfEra_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetYearOfEra_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetYearOfEra_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004", test.yearOfEra().toString());
    }

    public void testPropertyGetYearOfEra_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetCenturyOfEra_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.centuryOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.centuryOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.centuryOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.centuryOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.centuryOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.centuryOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.centuryOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetCenturyOfEra_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetCenturyOfEra_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.centuryOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.centuryOfEra().get());
    }

    public void testPropertyGetYearOfCentury_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetYearOfCentury_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.getYearOfCentury());
    }

    public void testPropertyGetYearOfCentury_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.getYearOfCentury());
    }

    public void testPropertyGetYearOfCentury_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfCentury().get());
    }

    public void testPropertyGetYearOfCentury_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfCentury().get());
    }

    public void testPropertyGetYearOfCentury_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfCentury().get());
    }

    public void testPropertyGetYearOfCentury_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfCentury().get());
    }

    public void testPropertyGetYearOfCentury_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfCentury().get());
    }

    public void testPropertyGetYearOfCentury_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetYearOfCentury_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetYearOfCentury_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfCentury().get());
    }

    public void testPropertyGetYearOfCentury_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.yearOfCentury().get());
    }

    public void testPropertyGetWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetWeekyear_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetWeekyear_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetWeekyear_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetWeekyear_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetWeekyear_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetWeekyear_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetWeekyear_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetWeekyear_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetWeekyear_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetWeekyear_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetWeekyear_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetYear_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetYear_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_16_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(2004, test.year().get());
    }

    public void testPropertyAddYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().add(9);
        assertEquals(2004, test.getYear());
    }

    public void testPropertyAddWrapFieldYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().addWrapField(9);
        assertEquals(2004, test.getYear());
    }

    public void testPropertySetYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().set(1960);
        assertEquals(1372719200000L, test.getMillis());
    }

    public void testPropertySetTextYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().set("1960");
        assertEquals(2004, test.getYear());
    }

    public void testPropertyGetMonthOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetMonthOfYear_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetMonthOfYear_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetMonthOfYear_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(6, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetMonthOfYear_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetMonthOfYear_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetMonthOfYear_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetMonthOfYear_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetMonthOfYear_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetMonthOfYear_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(6, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals(7, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals(7, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals(7, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_16_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals(7, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_17_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals(7, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_18_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals(7, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_19_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals(7, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_20_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals(7, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_21_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals(7, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_22_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals(7, test.monthOfYear().get());
    }

    public void testPropertyAddMonthOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().add(6);
        assertEquals(7, test.getMonthOfYear());
    }

    public void testPropertyAddWrapFieldMonthOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().addWrapField(8);
        assertEquals(1372716800000L, test.getMillis());
    }

    public void testPropertySetMonthOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set(12);
        assertEquals(12, test.getMonthOfYear());
    }

    public void testPropertySetTextMonthOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("12");
        assertEquals(12, test.getMonthOfYear());
    }

    public void testPropertySetTextMonthOfYear_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("12");
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("December");
        assertEquals(12, test.getMonthOfYear());
    }

    public void testPropertySetTextMonthOfYear_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("12");
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("December");
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("Dec");
        assertEquals(12, test.getMonthOfYear());
    }

    public void testPropertyGetDayOfMonth_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetDayOfMonth_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetDayOfMonth_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetDayOfMonth_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_16_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_17_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_18_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_19_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyAddDayOfMonth_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().add(9);
        assertEquals(2004, test.getYear());
    }

    public void testPropertyAddWrapFieldDayOfMonth_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().addWrapField(22);
        assertEquals(2004, test.getYear());
    }

    public void testPropertySetDayOfMonth_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().set(12);
        assertEquals(12, test.getDayOfMonth());
    }

    public void testPropertySetTextDayOfMonth_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().set("12");
        assertEquals(12, test.getDayOfMonth());
    }

    public void testPropertyGetDayOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetDayOfYear_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetDayOfYear_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetDayOfYear_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(0, test.dayOfYear().get());
    }

    public void testPropertyAddDayOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().add(9);
        assertEquals(1372716800000L, test.getMillis());
    }

    public void testPropertyAddWrapFieldDayOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().addWrapField(206);
        assertEquals(2004, test.getYear());
    }

    public void testPropertySetDayOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().set(12);
        assertEquals(12, test.getDayOfYear());
    }

    public void testPropertySetTextDayOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().set("12");
        assertEquals(12, test.getDayOfYear());
    }

    public void testPropertyGetWeekOfWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetWeekOfWeekyear_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetWeekOfWeekyear_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetWeekOfWeekyear_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals(1, test.weekOfWeekyear().get());
    }

    public void testPropertyAddWeekOfWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().add(1);
        assertEquals(2004, test.getYear());
    }

    public void testPropertyAddWrapFieldWeekOfWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().addWrapField(30);
        assertEquals(2004, test.getYear());
    }

    public void testPropertySetWeekOfWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().set(4);
        assertEquals(2004, test.getYear());
    }

    public void testPropertySetTextWeekOfWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().set("4");
        assertEquals(2004, test.getYear());
    }

    public void testPropertyGetDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetDayOfWeek_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyGetDayOfWeek_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyGetDayOfWeek_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyGetDayOfWeek_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetDayOfWeek_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyGetDayOfWeek_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyGetDayOfWeek_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyGetDayOfWeek_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetDayOfWeek_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000Z", test.toString());
    }

    public void testPropertyGetDayOfWeek_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyGetDayOfWeek_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetDayOfWeek_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetDayOfWeek_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyGetDayOfWeek_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyGetDayOfWeek_16_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetDayOfWeek_17_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetDayOfWeek_18_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyGetDayOfWeek_19_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetDayOfWeek_20_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000", test.toString());
    }

    public void testPropertyGetDayOfWeek_21_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertEquals("2004-06-09T00:00:00.000000000-+0100:+0100", test.toString());
    }

    public void testPropertyAddDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().add(1);
        assertEquals(1372716800000L, test.getMillis());
    }

    public void testPropertyAddLongDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().add(1L);
        assertEquals(1372716800000L, test.getMillis());
    }

    public void testPropertyAddWrapFieldDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);  // Wed
        test.dayOfWeek().addWrapField(5);
        assertEquals(2004, test.getYear());
    }

    public void testPropertySetDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set(4);
        assertEquals(1372719200000L, test.getMillis());
    }

    public void testPropertySetTextDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("4");
        assertEquals(1372719200000L, test.getMillis());
    }

    public void testPropertySetTextDayOfWeek_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("4");
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Mon");
        assertEquals(1372719200000L, test.getMillis());
    }

    public void testPropertySetTextDayOfWeek_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("4");
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Mon");
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Tuesday");
        assertEquals(1372719200000L, test.getMillis());
    }

    public void testPropertySetTextDayOfWeek_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("4");
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Mon");
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Tuesday");
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("lundi", Locale.FRENCH);
        assertEquals(1372719200000L, test.getMillis());
    }

    public void testPropertyGetHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetHourOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyGetHourOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyGetHourOfDay_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyGetHourOfDay_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyGetHourOfDay_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyGetHourOfDay_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyGetHourOfDay_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyGetHourOfDay_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetHourOfDay_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetHourOfDay_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyGetHourOfDay_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyRoundFloorHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundFloor();
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyRoundCeilingHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundCeiling();
        assertEquals(2004, test.getYear());
    }

    public void testPropertyRoundHalfFloorHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfFloor();
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyRoundHalfFloorHourOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfFloor();
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfFloor();
        assertEquals(13, test.getHourOfDay());
    }

    public void testPropertyRoundHalfFloorHourOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfFloor();
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfFloor();
        
        test = new MutableDateTime(2004, 6, 9, 13, 29, 59, 999);
        test.hourOfDay().roundHalfFloor();
        assertEquals(2004, test.getYear());
    }

    public void testPropertyRoundHalfCeilingHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfCeiling();
        assertEquals(13, test.getHourOfDay());
    }

    public void testPropertyRoundHalfCeilingHourOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfCeiling();
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfCeiling();
        assertEquals(2004, test.getYear());
    }

    public void testPropertyRoundHalfCeilingHourOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfCeiling();
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfCeiling();
        
        test = new MutableDateTime(2004, 6, 9, 13, 29, 59, 999);
        test.hourOfDay().roundHalfCeiling();
        assertEquals(2004, test.getYear());
    }

    public void testPropertyRoundHalfEvenHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyRoundHalfEvenHourOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        
        test = new MutableDateTime(2004, 6, 9, 14, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        assertEquals(2004, test.getYear());
    }

    public void testPropertyRoundHalfEvenHourOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        
        test = new MutableDateTime(2004, 6, 9, 14, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfEven();
        assertEquals(2004, test.getYear());
    }

    public void testPropertyRoundHalfEvenHourOfDay_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        
        test = new MutableDateTime(2004, 6, 9, 14, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfEven();
        
        test = new MutableDateTime(2004, 6, 9, 13, 29, 59, 999);
        test.hourOfDay().roundHalfEven();
        assertEquals(2004, test.getYear());
    }

    public void testPropertyRemainderHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyGetMinuteOfHour_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetMinuteOfHour_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinuteOfHour_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinuteOfHour_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinuteOfHour_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinuteOfHour_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinuteOfHour_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinuteOfHour_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinuteOfHour_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetMinuteOfHour_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetMinuteOfHour_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinuteOfHour_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinuteOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+01:00:00.000000000", test.toString());
    }

    public void testPropertyGetMinuteOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMinuteOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMinuteOfDay_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMinuteOfDay_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMinuteOfDay_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMinuteOfDay_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMinuteOfDay_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMinuteOfDay_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetMinuteOfDay_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetMinuteOfDay_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMinuteOfDay_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetSecondOfMinute_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetSecondOfMinute_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecondOfMinute_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecondOfMinute_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecondOfMinute_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecondOfMinute_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecondOfMinute_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecondOfMinute_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecondOfMinute_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetSecondOfMinute_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetSecondOfMinute_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecondOfMinute_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("23", test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecondOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetSecondOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetSecondOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetSecondOfDay_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetSecondOfDay_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetSecondOfDay_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetSecondOfDay_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetSecondOfDay_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetSecondOfDay_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+01:00:00.000000000", test.toString());
    }

    public void testPropertyGetSecondOfDay_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetSecondOfDay_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetSecondOfDay_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMillisOfSecond_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetMillisOfSecond_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.530", test.toString());
    }

    public void testPropertyGetMillisOfSecond_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.530", test.toString());
    }

    public void testPropertyGetMillisOfSecond_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.530", test.toString());
    }

    public void testPropertyGetMillisOfSecond_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.530", test.toString());
    }

    public void testPropertyGetMillisOfSecond_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.530", test.toString());
    }

    public void testPropertyGetMillisOfSecond_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.530", test.toString());
    }

    public void testPropertyGetMillisOfSecond_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.530", test.toString());
    }

    public void testPropertyGetMillisOfSecond_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetMillisOfSecond_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetMillisOfSecond_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.530", test.toString());
    }

    public void testPropertyGetMillisOfSecond_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.530", test.toString());
    }

    public void testPropertyGetMillisOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+01:00:00.000000000", test.toString());
    }

    public void testPropertyGetMillisOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMillisOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMillisOfDay_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMillisOfDay_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMillisOfDay_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMillisOfDay_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMillisOfDay_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMillisOfDay_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetMillisOfDay_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430Z", test.toString());
    }

    public void testPropertyGetMillisOfDay_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyGetMillisOfDay_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertEquals("2004-06-09T13:23:43.000000430-+0100:+0100", test.toString());
    }

    public void testPropertyToIntervalYearOfEra_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfEra().toInterval();
      assertEquals("2004-01-01T00:00:00.000+0000", testInterval.getStart().toString());
    }

    public void testPropertyToIntervalYearOfEra_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfEra().toInterval();
      assertEquals(2004, testInterval.getEnd().getYear());
    }

    public void testPropertyToIntervalYearOfEra_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfEra().toInterval();
      assertNotNull(testInterval);
    }

    public void testPropertyToIntervalYearOfCentury_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfCentury().toInterval();
      assertEquals("2004-01-01T00:00:00.000+0000", testInterval.getStart().toString());
    }

    public void testPropertyToIntervalYearOfCentury_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfCentury().toInterval();
      assertEquals("2004-06-09T23:59:59.999+0100:+0100", testInterval.getEnd().toString());
    }

    public void testPropertyToIntervalYearOfCentury_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfCentury().toInterval();
      assertNotNull(testInterval);
    }

    public void testPropertyToIntervalYear_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.year().toInterval();
      assertEquals("2004-01-01T00:00:00.000+0100", testInterval.getStart().toString());
    }

    public void testPropertyToIntervalYear_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.year().toInterval();
      assertEquals("2004-07-01T00:00:00.000+0100", testInterval.getEnd().toString());
    }

    public void testPropertyToIntervalYear_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.year().toInterval();
      assertNotNull(testInterval);
    }

    public void testPropertyToIntervalMonthOfYear_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.monthOfYear().toInterval();
      assertEquals("2004-06-01T00:00:00.000+0100", testInterval.getStart().toString());
    }

    public void testPropertyToIntervalMonthOfYear_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.monthOfYear().toInterval();
      assertEquals("2004-07-01T00:00:00.000+01:00", testInterval.getEnd().toString());
    }

    public void testPropertyToIntervalMonthOfYear_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.monthOfYear().toInterval();
      assertNotNull(testInterval);
    }

    public void testPropertyToIntervalDayOfMonth_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();
      assertEquals("2004-06-09T00:00:00.000+0100", testInterval.getStart().toString());
    }

    public void testPropertyToIntervalDayOfMonth_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();
      assertEquals("2004-06-10_00:00:00.000+0100", testInterval.getEnd().toString());
    }

    public void testPropertyToIntervalDayOfMonth_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();
      assertNotNull(testInterval);
    }

    public void testPropertyToIntervalDayOfMonth_4_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();

      MutableDateTime febTest = new MutableDateTime(2004, 2, 29, 13, 23, 43, 53);
      Interval febTestInterval = febTest.dayOfMonth().toInterval();
// incorrect assertion       assertEquals("2004-02-29T00:00:00.000+0000", febTest.getStart().toString());
    }

    public void testPropertyToIntervalDayOfMonth_5_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();

      MutableDateTime febTest = new MutableDateTime(2004, 2, 29, 13, 23, 43, 53);
      Interval febTestInterval = febTest.dayOfMonth().toInterval();
      assertEquals("2004-03-01T00:00:00.000+0000", testInterval.getEnd().toString());
    }

    public void testPropertyToIntervalDayOfMonth_6_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();

      MutableDateTime febTest = new MutableDateTime(2004, 2, 29, 13, 23, 43, 53);
      Interval febTestInterval = febTest.dayOfMonth().toInterval();
      assertNotNull(febTestInterval);
    }

    public void testPropertyToIntervalHourOfDay_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();
      assertEquals(13, testInterval.getStart().getHourOfDay());
    }

    public void testPropertyToIntervalHourOfDay_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();
      assertEquals(2004, testInterval.getEnd().getYear());
    }

    public void testPropertyToIntervalHourOfDay_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();
      assertNotNull(testInterval);
    }

    public void testPropertyToIntervalHourOfDay_4_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();

      MutableDateTime midnightTest = new MutableDateTime(2004, 6, 9, 23, 23, 43, 53);
      Interval midnightTestInterval = midnightTest.hourOfDay().toInterval();
      assertEquals("2004-06-09T23:00:00.000+01:00", testInterval.getStart().toString());
    }

    public void testPropertyToIntervalHourOfDay_5_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();

      MutableDateTime midnightTest = new MutableDateTime(2004, 6, 9, 23, 23, 43, 53);
      Interval midnightTestInterval = midnightTest.hourOfDay().toInterval();
      assertEquals("2004-06-10T00:00:00.000+0100", testInterval.getEnd().toString());
    }

    public void testPropertyToIntervalHourOfDay_6_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();

      MutableDateTime midnightTest = new MutableDateTime(2004, 6, 9, 23, 23, 43, 53);
      Interval midnightTestInterval = midnightTest.hourOfDay().toInterval();
      assertNotNull(midnightTestInterval);
    }

    public void testPropertyToIntervalMinuteOfHour_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.minuteOfHour().toInterval();
      assertEquals("2004-06-09T13:23:00.000+0100:+0100", testInterval.getStart().toString());
    }

    public void testPropertyToIntervalMinuteOfHour_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.minuteOfHour().toInterval();
      assertEquals("2004-06-09T13:24:00.000+0100:+0100", testInterval.getEnd().toString());
    }

    public void testPropertyToIntervalMinuteOfHour_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.minuteOfHour().toInterval();
      assertNotNull(testInterval);
    }

    public void testPropertyToIntervalSecondOfMinute_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.secondOfMinute().toInterval();
      assertEquals("2004-06-09T13:23:43.530+0100:+0100", testInterval.getStart().toString());
    }

    public void testPropertyToIntervalSecondOfMinute_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.secondOfMinute().toInterval();
      assertEquals("2004-06-09T13:23:43.000+0100:+0100", testInterval.getEnd().toString());
    }

    public void testPropertyToIntervalSecondOfMinute_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.secondOfMinute().toInterval();
      assertNotNull(testInterval);
    }

    public void testPropertyToIntervalMillisOfSecond_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.millisOfSecond().toInterval();
      assertEquals("2004-06-09T13:23:43.000+0100:+0100", testInterval.getStart().toString());
    }

    public void testPropertyToIntervalMillisOfSecond_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.millisOfSecond().toInterval();
      assertEquals("2004-06-09T13:23:43.000+0100:+0100", testInterval.getEnd().toString());
    }

    public void testPropertyToIntervalMillisOfSecond_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.millisOfSecond().toInterval();
      assertNotNull(testInterval);
    }

}
