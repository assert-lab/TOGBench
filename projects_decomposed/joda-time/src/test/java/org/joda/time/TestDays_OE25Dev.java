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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This class is a Junit unit test for Days.
 *
 * @author Stephen Colebourne
 */
public class TestDays_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDays_OE25Dev.class);
    }

    public TestDays_OE25Dev(String name) {
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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testConstants_1_oe() {
        assertEquals(0,Days.ZERO.getDays());
    }

    public void testConstants_2_oe() {
        assertEquals(1,Days.ONE.getDays());
    }

    public void testConstants_3_oe() {
        assertEquals(2,Days.TWO.getDays());
    }

    public void testConstants_4_oe() {
        assertEquals(3,Days.THREE.getDays());
    }

    public void testConstants_5_oe() {
        assertEquals(4,Days.FOUR.getDays());
    }

    public void testConstants_6_oe() {
        assertEquals(5,Days.FIVE.getDays());
    }

    public void testConstants_7_oe() {
        assertEquals(6,Days.SIX.getDays());
    }

    public void testConstants_8_oe() {
        assertEquals(7,Days.SEVEN.getDays());
    }

    public void testConstants_9_oe() {
        assertEquals(Integer.MAX_VALUE,Days.MAX_VALUE.getDays());
    }

    public void testConstants_10_oe() {
        assertEquals(Integer.MIN_VALUE,Days.MIN_VALUE.getDays());
    }

    public void testFactory_days_int_1_oe() {
        assertSame(Days.ZERO,Days.days(0));
    }

    public void testFactory_days_int_2_oe() {
        assertSame(Days.ONE,Days.days(1));
    }

    public void testFactory_days_int_3_oe() {
        assertSame(Days.TWO,Days.days(2));
    }

    public void testFactory_days_int_4_oe() {
        assertSame(Days.THREE,Days.days(3));
    }

    public void testFactory_days_int_5_oe() {
        assertSame(Days.FOUR,Days.days(4));
    }

    public void testFactory_days_int_6_oe() {
        assertSame(Days.FIVE,Days.days(5));
    }

    public void testFactory_days_int_7_oe() {
        assertSame(Days.SIX,Days.days(6));
    }

    public void testFactory_days_int_8_oe() {
        assertSame(Days.SEVEN,Days.days(7));
    }

    public void testFactory_days_int_9_oe() {
        assertSame(Days.MAX_VALUE,Days.days(Integer.MAX_VALUE));
    }

    public void testFactory_days_int_10_oe() {
        assertSame(Days.MIN_VALUE,Days.days(Integer.MIN_VALUE));
    }

    public void testFactory_days_int_11_oe() {
        assertEquals(-1,Days.days(-1).getDays());
    }

    public void testFactory_days_int_12_oe() {
        assertEquals(8,Days.days(8).getDays());
    }

    public void testFactory_daysBetween_RInstant_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(3,Days.daysBetween(start,end1).getDays());
    }

    public void testFactory_daysBetween_RInstant_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(0,Days.daysBetween(start,start).getDays());
    }

    public void testFactory_daysBetween_RInstant_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(0,Days.daysBetween(end1,end1).getDays());
    }

    public void testFactory_daysBetween_RInstant_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(-3,Days.daysBetween(end1,start).getDays());
    }

    public void testFactory_daysBetween_RInstant_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(6,Days.daysBetween(start,end2).getDays());
    }

    public void testFactory_daysBetween_RPartial_LocalDate_1_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        assertEquals(3,Days.daysBetween(start,end1).getDays());
    }

    public void testFactory_daysBetween_RPartial_LocalDate_2_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        assertEquals(0,Days.daysBetween(start,start).getDays());
    }

    public void testFactory_daysBetween_RPartial_LocalDate_3_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        assertEquals(0,Days.daysBetween(end1,end1).getDays());
    }

    public void testFactory_daysBetween_RPartial_LocalDate_4_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        assertEquals(-3,Days.daysBetween(end1,start).getDays());
    }

    public void testFactory_daysBetween_RPartial_LocalDate_5_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        assertEquals(6,Days.daysBetween(start,end2).getDays());
    }

    public void testFactory_daysBetween_RPartial_YearMonth_1_oe() {
        YearMonth start1 = new YearMonth(2011, 1);
        YearMonth start2 = new YearMonth(2012, 1);
        YearMonth end1 = new YearMonth(2011, 3);
        YearMonth end2 = new YearMonth(2012, 3);
        
        assertEquals(59,Days.daysBetween(start1,end1).getDays());
    }

    public void testFactory_daysBetween_RPartial_YearMonth_2_oe() {
        YearMonth start1 = new YearMonth(2011, 1);
        YearMonth start2 = new YearMonth(2012, 1);
        YearMonth end1 = new YearMonth(2011, 3);
        YearMonth end2 = new YearMonth(2012, 3);
        
        assertEquals(60,Days.daysBetween(start2,end2).getDays());
    }

    public void testFactory_daysBetween_RPartial_YearMonth_3_oe() {
        YearMonth start1 = new YearMonth(2011, 1);
        YearMonth start2 = new YearMonth(2012, 1);
        YearMonth end1 = new YearMonth(2011, 3);
        YearMonth end2 = new YearMonth(2012, 3);
        
        
        assertEquals(-59,Days.daysBetween(end1,start1).getDays());
    }

    public void testFactory_daysBetween_RPartial_YearMonth_4_oe() {
        YearMonth start1 = new YearMonth(2011, 1);
        YearMonth start2 = new YearMonth(2012, 1);
        YearMonth end1 = new YearMonth(2011, 3);
        YearMonth end2 = new YearMonth(2012, 3);
        
        
        assertEquals(-60,Days.daysBetween(end2,start2).getDays());
    }

    public void testFactory_daysBetween_RPartial_MonthDay_1_oe() {
        MonthDay start1 = new MonthDay(2, 1);
        MonthDay start2 = new MonthDay(2, 28);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        
        assertEquals(27,Days.daysBetween(start1,end1).getDays());
    }

    public void testFactory_daysBetween_RPartial_MonthDay_2_oe() {
        MonthDay start1 = new MonthDay(2, 1);
        MonthDay start2 = new MonthDay(2, 28);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        
        assertEquals(28,Days.daysBetween(start1,end2).getDays());
    }

    public void testFactory_daysBetween_RPartial_MonthDay_3_oe() {
        MonthDay start1 = new MonthDay(2, 1);
        MonthDay start2 = new MonthDay(2, 28);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        
        assertEquals(0,Days.daysBetween(start2,end1).getDays());
    }

    public void testFactory_daysBetween_RPartial_MonthDay_4_oe() {
        MonthDay start1 = new MonthDay(2, 1);
        MonthDay start2 = new MonthDay(2, 28);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        
        assertEquals(1,Days.daysBetween(start2,end2).getDays());
    }

    public void testFactory_daysBetween_RPartial_MonthDay_5_oe() {
        MonthDay start1 = new MonthDay(2, 1);
        MonthDay start2 = new MonthDay(2, 28);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        
        
        assertEquals(-27,Days.daysBetween(end1,start1).getDays());
    }

    public void testFactory_daysBetween_RPartial_MonthDay_6_oe() {
        MonthDay start1 = new MonthDay(2, 1);
        MonthDay start2 = new MonthDay(2, 28);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        
        
        assertEquals(-28,Days.daysBetween(end2,start1).getDays());
    }

    public void testFactory_daysBetween_RPartial_MonthDay_7_oe() {
        MonthDay start1 = new MonthDay(2, 1);
        MonthDay start2 = new MonthDay(2, 28);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        
        
        assertEquals(0,Days.daysBetween(end1,start2).getDays());
    }

    public void testFactory_daysBetween_RPartial_MonthDay_8_oe() {
        MonthDay start1 = new MonthDay(2, 1);
        MonthDay start2 = new MonthDay(2, 28);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        
        
        assertEquals(-1,Days.daysBetween(end2,start2).getDays());
    }

    public void testFactory_daysIn_RInterval_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(0,Days.daysIn((ReadableInterval)null).getDays());
    }

    public void testFactory_daysIn_RInterval_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(3,Days.daysIn(new Interval(start,end1)).getDays());
    }

    public void testFactory_daysIn_RInterval_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(0,Days.daysIn(new Interval(start,start)).getDays());
    }

    public void testFactory_daysIn_RInterval_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(0,Days.daysIn(new Interval(end1,end1)).getDays());
    }

    public void testFactory_daysIn_RInterval_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(6,Days.daysIn(new Interval(start,end2)).getDays());
    }

    public void testFactory_standardDaysIn_RPeriod_1_oe() {
        assertEquals(0,Days.standardDaysIn((ReadablePeriod)null).getDays());
    }

    public void testFactory_standardDaysIn_RPeriod_2_oe() {
        assertEquals(0,Days.standardDaysIn(Period.ZERO).getDays());
    }

    public void testFactory_standardDaysIn_RPeriod_3_oe() {
        assertEquals(1,Days.standardDaysIn(new Period(0,0,0,1,0,0,0,0)).getDays());
    }

    public void testFactory_standardDaysIn_RPeriod_4_oe() {
        assertEquals(123,Days.standardDaysIn(Period.days(123)).getDays());
    }

    public void testFactory_standardDaysIn_RPeriod_5_oe() {
        assertEquals(-987,Days.standardDaysIn(Period.days(-987)).getDays());
    }

    public void testFactory_standardDaysIn_RPeriod_6_oe() {
        assertEquals(1,Days.standardDaysIn(Period.hours(47)).getDays());
    }

    public void testFactory_standardDaysIn_RPeriod_7_oe() {
        assertEquals(2,Days.standardDaysIn(Period.hours(48)).getDays());
    }

    public void testFactory_standardDaysIn_RPeriod_8_oe() {
        assertEquals(2,Days.standardDaysIn(Period.hours(49)).getDays());
    }

    public void testFactory_standardDaysIn_RPeriod_9_oe() {
        assertEquals(14,Days.standardDaysIn(Period.weeks(2)).getDays());
    }

    public void testFactory_parseDays_String_1_oe() {
        assertEquals(0,Days.parseDays((String)null).getDays());
    }

    public void testFactory_parseDays_String_2_oe() {
        assertEquals(0,Days.parseDays("P0D").getDays());
    }

    public void testFactory_parseDays_String_3_oe() {
        assertEquals(1,Days.parseDays("P1D").getDays());
    }

    public void testFactory_parseDays_String_4_oe() {
        assertEquals(-3,Days.parseDays("P-3D").getDays());
    }

    public void testFactory_parseDays_String_5_oe() {
        assertEquals(2,Days.parseDays("P0Y0M2D").getDays());
    }

    public void testFactory_parseDays_String_6_oe() {
        assertEquals(2,Days.parseDays("P2DT0H0M").getDays());
    }

    public void testGetMethods_1_oe() {
        Days test = Days.days(20);
        assertEquals(20,test.getDays());
    }

    public void testGetFieldType_1_oe() {
        Days test = Days.days(20);
        assertEquals(DurationFieldType.days(),test.getFieldType());
    }

    public void testGetPeriodType_1_oe() {
        Days test = Days.days(20);
        assertEquals(PeriodType.days(),test.getPeriodType());
    }

    public void testIsGreaterThan_1_oe() {
        assertEquals(true,Days.THREE.isGreaterThan(Days.TWO));
    }

    public void testIsGreaterThan_2_oe() {
        assertEquals(false,Days.THREE.isGreaterThan(Days.THREE));
    }

    public void testIsGreaterThan_3_oe() {
        assertEquals(false,Days.TWO.isGreaterThan(Days.THREE));
    }

    public void testIsGreaterThan_4_oe() {
        assertEquals(true,Days.ONE.isGreaterThan(null));
    }

    public void testIsGreaterThan_5_oe() {
        assertEquals(false,Days.days(-1).isGreaterThan(null));
    }

    public void testIsLessThan_1_oe() {
        assertEquals(false,Days.THREE.isLessThan(Days.TWO));
    }

    public void testIsLessThan_2_oe() {
        assertEquals(false,Days.THREE.isLessThan(Days.THREE));
    }

    public void testIsLessThan_3_oe() {
        assertEquals(true,Days.TWO.isLessThan(Days.THREE));
    }

    public void testIsLessThan_4_oe() {
        assertEquals(false,Days.ONE.isLessThan(null));
    }

    public void testIsLessThan_5_oe() {
        assertEquals(true,Days.days(-1).isLessThan(null));
    }

    public void testToString_1_oe() {
        Days test = Days.days(20);
        assertEquals("P20D",test.toString());
    }

    public void testToString_2_oe() {
        Days test = Days.days(20);
        
        test = Days.days(-20);
        assertEquals("P-20D",test.toString());
    }

    public void testSerialization_1_oe() throws Exception {
        Days test = Days.SEVEN;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Days result = (Days) ois.readObject();
        ois.close();
        
        assertSame(test,result);
    }

    public void testToStandardWeeks_1_oe() {
        Days test = Days.days(14);
        Weeks expected = Weeks.weeks(2);
        assertEquals(expected,test.toStandardWeeks());
    }

    public void testToStandardHours_1_oe() {
        Days test = Days.days(2);
        Hours expected = Hours.hours(2 * 24);
        assertEquals(expected,test.toStandardHours());
    }

    public void testToStandardMinutes_1_oe() {
        Days test = Days.days(2);
        Minutes expected = Minutes.minutes(2 * 24 * 60);
        assertEquals(expected,test.toStandardMinutes());
    }

    public void testToStandardSeconds_1_oe() {
        Days test = Days.days(2);
        Seconds expected = Seconds.seconds(2 * 24 * 60 * 60);
        assertEquals(expected,test.toStandardSeconds());
    }

    public void testToStandardDuration_1_oe() {
        Days test = Days.days(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_DAY);
        assertEquals(expected,test.toStandardDuration());
    }

    public void testToStandardDuration_2_oe() {
        Days test = Days.days(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_DAY);
        
        expected = new Duration(((long) Integer.MAX_VALUE) * DateTimeConstants.MILLIS_PER_DAY);
        assertEquals(expected,Days.MAX_VALUE.toStandardDuration());
    }

    public void testPlus_int_1_oe() {
        Days test2 = Days.days(2);
        Days result = test2.plus(3);
        assertEquals(2,test2.getDays());
    }

    public void testPlus_int_2_oe() {
        Days test2 = Days.days(2);
        Days result = test2.plus(3);
        assertEquals(5,result.getDays());
    }

    public void testPlus_int_3_oe() {
        Days test2 = Days.days(2);
        Days result = test2.plus(3);
        
        assertEquals(1,Days.ONE.plus(0).getDays());
    }

    public void testPlus_Days_1_oe() {
        Days test2 = Days.days(2);
        Days test3 = Days.days(3);
        Days result = test2.plus(test3);
        assertEquals(2,test2.getDays());
    }

    public void testPlus_Days_2_oe() {
        Days test2 = Days.days(2);
        Days test3 = Days.days(3);
        Days result = test2.plus(test3);
        assertEquals(3,test3.getDays());
    }

    public void testPlus_Days_3_oe() {
        Days test2 = Days.days(2);
        Days test3 = Days.days(3);
        Days result = test2.plus(test3);
        assertEquals(5,result.getDays());
    }

    public void testPlus_Days_4_oe() {
        Days test2 = Days.days(2);
        Days test3 = Days.days(3);
        Days result = test2.plus(test3);
        
        assertEquals(1,Days.ONE.plus(Days.ZERO).getDays());
    }

    public void testPlus_Days_5_oe() {
        Days test2 = Days.days(2);
        Days test3 = Days.days(3);
        Days result = test2.plus(test3);
        
        assertEquals(1,Days.ONE.plus((Days)null).getDays());
    }

    public void testMinus_int_1_oe() {
        Days test2 = Days.days(2);
        Days result = test2.minus(3);
        assertEquals(2,test2.getDays());
    }

    public void testMinus_int_2_oe() {
        Days test2 = Days.days(2);
        Days result = test2.minus(3);
        assertEquals(-1,result.getDays());
    }

    public void testMinus_int_3_oe() {
        Days test2 = Days.days(2);
        Days result = test2.minus(3);
        
        assertEquals(1,Days.ONE.minus(0).getDays());
    }

    public void testMinus_Days_1_oe() {
        Days test2 = Days.days(2);
        Days test3 = Days.days(3);
        Days result = test2.minus(test3);
        assertEquals(2,test2.getDays());
    }

    public void testMinus_Days_2_oe() {
        Days test2 = Days.days(2);
        Days test3 = Days.days(3);
        Days result = test2.minus(test3);
        assertEquals(3,test3.getDays());
    }

    public void testMinus_Days_3_oe() {
        Days test2 = Days.days(2);
        Days test3 = Days.days(3);
        Days result = test2.minus(test3);
        assertEquals(-1,result.getDays());
    }

    public void testMinus_Days_4_oe() {
        Days test2 = Days.days(2);
        Days test3 = Days.days(3);
        Days result = test2.minus(test3);
        
        assertEquals(1,Days.ONE.minus(Days.ZERO).getDays());
    }

    public void testMinus_Days_5_oe() {
        Days test2 = Days.days(2);
        Days test3 = Days.days(3);
        Days result = test2.minus(test3);
        
        assertEquals(1,Days.ONE.minus((Days)null).getDays());
    }

    public void testMultipliedBy_int_1_oe() {
        Days test = Days.days(2);
        assertEquals(6,test.multipliedBy(3).getDays());
    }

    public void testMultipliedBy_int_2_oe() {
        Days test = Days.days(2);
        assertEquals(2,test.getDays());
    }

    public void testMultipliedBy_int_3_oe() {
        Days test = Days.days(2);
        assertEquals(-6,test.multipliedBy(-3).getDays());
    }

    public void testMultipliedBy_int_4_oe() {
        Days test = Days.days(2);
        assertSame(test,test.multipliedBy(1));
    }

    public void testDividedBy_int_1_oe() {
        Days test = Days.days(12);
        assertEquals(6,test.dividedBy(2).getDays());
    }

    public void testDividedBy_int_2_oe() {
        Days test = Days.days(12);
        assertEquals(12,test.getDays());
    }

    public void testDividedBy_int_3_oe() {
        Days test = Days.days(12);
        assertEquals(4,test.dividedBy(3).getDays());
    }

    public void testDividedBy_int_4_oe() {
        Days test = Days.days(12);
        assertEquals(3,test.dividedBy(4).getDays());
    }

    public void testDividedBy_int_5_oe() {
        Days test = Days.days(12);
        assertEquals(2,test.dividedBy(5).getDays());
    }

    public void testDividedBy_int_6_oe() {
        Days test = Days.days(12);
        assertEquals(2,test.dividedBy(6).getDays());
    }

    public void testDividedBy_int_7_oe() {
        Days test = Days.days(12);
        assertSame(test,test.dividedBy(1));
    }

    public void testNegated_1_oe() {
        Days test = Days.days(12);
        assertEquals(-12,test.negated().getDays());
    }

    public void testNegated_2_oe() {
        Days test = Days.days(12);
        assertEquals(12,test.getDays());
    }

    public void testAddToLocalDate_1_oe() {
        Days test = Days.days(20);
        LocalDate date = new LocalDate(2006, 6, 1);
        LocalDate expected = new LocalDate(2006, 6, 21);
        assertEquals(expected,date.plus(test));
    }

}
