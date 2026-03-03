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

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.convert.StringConvert;
import org.joda.time.chrono.ISOChronology;

/**
 * Test string conversion.
 *
 * @author Stephen Colebourne
 */
public class TestStringConvert_OE25Dev extends TestCase {

    private static final DateTimeZone ZONE = DateTimeZone.forID("+02:00");

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestStringConvert_OE25Dev.class);
    }

    public TestStringConvert_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

//    public void testInterval() {
//        DateTime a = new DateTime(2010, 6, 30, 2, 30, 50, 678, ISOChronology.getInstance(ZONE));
//        DateTime b = new DateTime(2011, 9, 10, 4, 20, 40, 234, ISOChronology.getInstance(ZONE));
//        Interval test = new Interval(a, b);
//        String str = StringConvert.INSTANCE.convertToString(test);
//        assertEquals("2010-06-30T02:30:50.678+02:00/2011-09-10T04:20:40.234+02:00",str);
//        assertEquals(test,StringConvert.INSTANCE.convertFromString(Interval.class,str));
//    }

    public void testDateMidnight_1_oe() {
        DateMidnight test = new DateMidnight(2010, 6, 30, ISOChronology.getInstance(ZONE));
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("2010-06-30T00:00:00.000+02:00",str);
    }

    public void testDateMidnight_2_oe() {
        DateMidnight test = new DateMidnight(2010, 6, 30, ISOChronology.getInstance(ZONE));
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(DateMidnight.class,str));
    }

    public void testDateTime_1_oe() {
        DateTime test = new DateTime(2010, 6, 30, 2, 30, 50, 678, ISOChronology.getInstance(ZONE));
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("2010-06-30T02:30:50.678+02:00",str);
    }

    public void testDateTime_2_oe() {
        DateTime test = new DateTime(2010, 6, 30, 2, 30, 50, 678, ISOChronology.getInstance(ZONE));
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(DateTime.class,str));
    }

    public void testMutableDateTime_1_oe() {
        MutableDateTime test = new MutableDateTime(2010, 6, 30, 2, 30, 50, 678, ISOChronology.getInstance(ZONE));
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("2010-06-30T02:30:50.678+02:00",str);
    }

    public void testMutableDateTime_2_oe() {
        MutableDateTime test = new MutableDateTime(2010, 6, 30, 2, 30, 50, 678, ISOChronology.getInstance(ZONE));
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(MutableDateTime.class,str));
    }

    public void testLocalDateTime_1_oe() {
        LocalDateTime test = new LocalDateTime(2010, 6, 30, 2, 30);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("2010-06-30T02:30:00.000",str);
    }

    public void testLocalDateTime_2_oe() {
        LocalDateTime test = new LocalDateTime(2010, 6, 30, 2, 30);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(LocalDateTime.class,str));
    }

    public void testLocalDate_1_oe() {
        LocalDate test = new LocalDate(2010, 6, 30);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("2010-06-30",str);
    }

    public void testLocalDate_2_oe() {
        LocalDate test = new LocalDate(2010, 6, 30);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(LocalDate.class,str));
    }

    public void testLocalTime_1_oe() {
        LocalTime test = new LocalTime(2, 30, 50, 678);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("02:30:50.678",str);
    }

    public void testLocalTime_2_oe() {
        LocalTime test = new LocalTime(2, 30, 50, 678);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(LocalTime.class,str));
    }

    public void testYearMonth_1_oe() {
        YearMonth test = new YearMonth(2010, 6);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("2010-06",str);
    }

    public void testYearMonth_2_oe() {
        YearMonth test = new YearMonth(2010, 6);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(YearMonth.class,str));
    }

    public void testMonthDay_1_oe() {
        MonthDay test = new MonthDay(6, 30);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("--06-30",str);
    }

    public void testMonthDay_2_oe() {
        MonthDay test = new MonthDay(6, 30);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(MonthDay.class,str));
    }

    public void testMonthDay_leapDay_1_oe() {
        MonthDay test = new MonthDay(2, 29);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("--02-29",str);
    }

    public void testMonthDay_leapDay_2_oe() {
        MonthDay test = new MonthDay(2, 29);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(MonthDay.class,str));
    }

    public void testTimeZone_1_oe() {
        DateTimeZone test = DateTimeZone.forID("Europe/Paris");
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("Europe/Paris",str);
    }

    public void testTimeZone_2_oe() {
        DateTimeZone test = DateTimeZone.forID("Europe/Paris");
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(DateTimeZone.class,str));
    }

    public void testDuration_1_oe() {
        Duration test = new Duration(12345678L);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("PT12345.678S",str);
    }

    public void testDuration_2_oe() {
        Duration test = new Duration(12345678L);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(Duration.class,str));
    }

    public void testPeriod_1_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("P1Y2M3W4DT5H6M7.008S",str);
    }

    public void testPeriod_2_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(Period.class,str));
    }

    public void testMutablePeriod_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("P1Y2M3W4DT5H6M7.008S",str);
    }

    public void testMutablePeriod_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(MutablePeriod.class,str));
    }

    public void testYears_1_oe() {
        Years test = Years.years(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("P5Y",str);
    }

    public void testYears_2_oe() {
        Years test = Years.years(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(Years.class,str));
    }

    public void testMonths_1_oe() {
        Months test = Months.months(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("P5M",str);
    }

    public void testMonths_2_oe() {
        Months test = Months.months(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(Months.class,str));
    }

    public void testWeeks_1_oe() {
        Weeks test = Weeks.weeks(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("P5W",str);
    }

    public void testWeeks_2_oe() {
        Weeks test = Weeks.weeks(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(Weeks.class,str));
    }

    public void testDays_1_oe() {
        Days test = Days.days(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("P5D",str);
    }

    public void testDays_2_oe() {
        Days test = Days.days(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(Days.class,str));
    }

    public void testHours_1_oe() {
        Hours test = Hours.hours(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("PT5H",str);
    }

    public void testHours_2_oe() {
        Hours test = Hours.hours(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(Hours.class,str));
    }

    public void testMinutes_1_oe() {
        Minutes test = Minutes.minutes(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("PT5M",str);
    }

    public void testMinutes_2_oe() {
        Minutes test = Minutes.minutes(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(Minutes.class,str));
    }

    public void testSeconds_1_oe() {
        Seconds test = Seconds.seconds(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals("PT5S",str);
    }

    public void testSeconds_2_oe() {
        Seconds test = Seconds.seconds(5);
        String str = StringConvert.INSTANCE.convertToString(test);
        assertEquals(test,StringConvert.INSTANCE.convertFromString(Seconds.class,str));
    }

}
