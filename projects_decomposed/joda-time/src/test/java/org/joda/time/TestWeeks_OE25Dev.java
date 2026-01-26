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
 * This class is a Junit unit test for Weeks.
 *
 * @author Stephen Colebourne
 */
public class TestWeeks_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestWeeks_OE25Dev_OE25Dev.class);
    }

    public TestWeeks_OE25Dev(String name) {
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

    public void testConstants_1_oe() {
        assertEquals(0, Weeks.ZERO.getWeeks());
    }

    public void testConstants_2_oe() {
        // removed other assertion
        assertEquals(1, Weeks.ONE.getWeeks());
    }

    public void testConstants_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2, Weeks.TWO.getWeeks());
    }

    public void testConstants_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, Weeks.THREE.getWeeks());
    }

    public void testConstants_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, Weeks.MAX_VALUE.getWeeks());
    }

    public void testConstants_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, Weeks.MIN_VALUE.getWeeks());
    }

    public void testFactory_weeks_int_1_oe() {
        assertSame(Weeks.ZERO, Weeks.weeks(0));
    }

    public void testFactory_weeks_int_2_oe() {
        // removed other assertion
        assertSame(Weeks.ONE, Weeks.weeks(1));
    }

    public void testFactory_weeks_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertSame(Weeks.TWO, Weeks.weeks(2));
    }

    public void testFactory_weeks_int_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Weeks.THREE, Weeks.weeks(3));
    }

    public void testFactory_weeks_int_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Weeks.MAX_VALUE, Weeks.weeks(Integer.MAX_VALUE));
    }

    public void testFactory_weeks_int_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Weeks.MIN_VALUE, Weeks.weeks(Integer.MIN_VALUE));
    }

    public void testFactory_weeks_int_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, Weeks.weeks(-1).getWeeks());
    }

    public void testFactory_weeks_int_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, Weeks.weeks(4).getWeeks());
    }

    public void testFactory_weeksBetween_RInstant_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 30, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 7, 21, 12, 0, 0, 0, PARIS);
        
        assertEquals(3, Weeks.weeksBetween(start, end1).getWeeks());
    }

    public void testFactory_weeksBetween_RInstant_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 30, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 7, 21, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        assertEquals(0, Weeks.weeksBetween(start, start).getWeeks());
    }

    public void testFactory_weeksBetween_RInstant_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 30, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 7, 21, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0, Weeks.weeksBetween(end1, end1).getWeeks());
    }

    public void testFactory_weeksBetween_RInstant_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 30, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 7, 21, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3, Weeks.weeksBetween(end1, start).getWeeks());
    }

    public void testFactory_weeksBetween_RInstant_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 30, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 7, 21, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, Weeks.weeksBetween(start, end2).getWeeks());
    }

    public void testFactory_weeksBetween_RPartial_1_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 30);
        YearMonthDay end2 = new YearMonthDay(2006, 7, 21);
        
        assertEquals(3, Weeks.weeksBetween(start, end1).getWeeks());
    }

    public void testFactory_weeksBetween_RPartial_2_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 30);
        YearMonthDay end2 = new YearMonthDay(2006, 7, 21);
        
        // removed other assertion
        assertEquals(0, Weeks.weeksBetween(start, start).getWeeks());
    }

    public void testFactory_weeksBetween_RPartial_3_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 30);
        YearMonthDay end2 = new YearMonthDay(2006, 7, 21);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0, Weeks.weeksBetween(end1, end1).getWeeks());
    }

    public void testFactory_weeksBetween_RPartial_4_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 30);
        YearMonthDay end2 = new YearMonthDay(2006, 7, 21);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3, Weeks.weeksBetween(end1, start).getWeeks());
    }

    public void testFactory_weeksBetween_RPartial_5_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 30);
        YearMonthDay end2 = new YearMonthDay(2006, 7, 21);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, Weeks.weeksBetween(start, end2).getWeeks());
    }

    public void testFactory_weeksIn_RInterval_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 30, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 7, 21, 12, 0, 0, 0, PARIS);
        
        assertEquals(0, Weeks.weeksIn((ReadableInterval) null).getWeeks());
    }

    public void testFactory_weeksIn_RInterval_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 30, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 7, 21, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        assertEquals(3, Weeks.weeksIn(new Interval(start, end1)).getWeeks());
    }

    public void testFactory_weeksIn_RInterval_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 30, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 7, 21, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0, Weeks.weeksIn(new Interval(start, start)).getWeeks());
    }

    public void testFactory_weeksIn_RInterval_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 30, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 7, 21, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, Weeks.weeksIn(new Interval(end1, end1)).getWeeks());
    }

    public void testFactory_weeksIn_RInterval_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 30, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 7, 21, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, Weeks.weeksIn(new Interval(start, end2)).getWeeks());
    }

    public void testFactory_standardWeeksIn_RPeriod_1_oe() {
        assertEquals(0, Weeks.standardWeeksIn((ReadablePeriod) null).getWeeks());
    }

    public void testFactory_standardWeeksIn_RPeriod_2_oe() {
        // removed other assertion
        assertEquals(0, Weeks.standardWeeksIn(Period.ZERO).getWeeks());
    }

    public void testFactory_standardWeeksIn_RPeriod_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1, Weeks.standardWeeksIn(new Period(0, 0, 1, 0, 0, 0, 0, 0)).getWeeks());
    }

    public void testFactory_standardWeeksIn_RPeriod_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(123, Weeks.standardWeeksIn(Period.weeks(123)).getWeeks());
    }

    public void testFactory_standardWeeksIn_RPeriod_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-987, Weeks.standardWeeksIn(Period.weeks(-987)).getWeeks());
    }

    public void testFactory_standardWeeksIn_RPeriod_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, Weeks.standardWeeksIn(Period.days(13)).getWeeks());
    }

    public void testFactory_standardWeeksIn_RPeriod_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, Weeks.standardWeeksIn(Period.days(14)).getWeeks());
    }

    public void testFactory_standardWeeksIn_RPeriod_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, Weeks.standardWeeksIn(Period.days(15)).getWeeks());
    }

    public void testFactory_parseWeeks_String_1_oe() {
        assertEquals(0, Weeks.parseWeeks((String) null).getWeeks());
    }

    public void testFactory_parseWeeks_String_2_oe() {
        // removed other assertion
        assertEquals(0, Weeks.parseWeeks("P0W").getWeeks());
    }

    public void testFactory_parseWeeks_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1, Weeks.parseWeeks("P1W").getWeeks());
    }

    public void testFactory_parseWeeks_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3, Weeks.parseWeeks("P-3W").getWeeks());
    }

    public void testFactory_parseWeeks_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, Weeks.parseWeeks("P0Y0M2W").getWeeks());
    }

    public void testFactory_parseWeeks_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, Weeks.parseWeeks("P2WT0H0M").getWeeks());
    }

    public void testGetMethods_1_oe() {
        Weeks test = Weeks.weeks(20);
        assertEquals(20, test.getWeeks());
    }

    public void testGetFieldType_1_oe() {
        Weeks test = Weeks.weeks(20);
        assertEquals(DurationFieldType.weeks(), test.getFieldType());
    }

    public void testGetPeriodType_1_oe() {
        Weeks test = Weeks.weeks(20);
        assertEquals(PeriodType.weeks(), test.getPeriodType());
    }

    public void testIsGreaterThan_1_oe() {
        assertEquals(true, Weeks.THREE.isGreaterThan(Weeks.TWO));
    }

    public void testIsGreaterThan_2_oe() {
        // removed other assertion
        assertEquals(false, Weeks.THREE.isGreaterThan(Weeks.THREE));
    }

    public void testIsGreaterThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, Weeks.TWO.isGreaterThan(Weeks.THREE));
    }

    public void testIsGreaterThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, Weeks.ONE.isGreaterThan(null));
    }

    public void testIsGreaterThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, Weeks.weeks(-1).isGreaterThan(null));
    }

    public void testIsLessThan_1_oe() {
        assertEquals(false, Weeks.THREE.isLessThan(Weeks.TWO));
    }

    public void testIsLessThan_2_oe() {
        // removed other assertion
        assertEquals(false, Weeks.THREE.isLessThan(Weeks.THREE));
    }

    public void testIsLessThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, Weeks.TWO.isLessThan(Weeks.THREE));
    }

    public void testIsLessThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, Weeks.ONE.isLessThan(null));
    }

    public void testIsLessThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, Weeks.weeks(-1).isLessThan(null));
    }

    public void testToString_1_oe() {
        Weeks test = Weeks.weeks(20);
        assertEquals("P20W", test.toString());
    }

    public void testToString_2_oe() {
        Weeks test = Weeks.weeks(20);
        // removed other assertion
        
        test = Weeks.weeks(-20);
        assertEquals("P-20W", test.toString());
    }

    public void testSerialization_1_oe() throws Exception {
        Weeks test = Weeks.THREE;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Weeks result = (Weeks) ois.readObject();
        ois.close();
        
        assertSame(test, result);
    }

    public void testToStandardDays_1_oe() {
        Weeks test = Weeks.weeks(2);
        Days expected = Days.days(14);
        assertEquals(expected, test.toStandardDays());
    }

    public void testToStandardHours_1_oe() {
        Weeks test = Weeks.weeks(2);
        Hours expected = Hours.hours(2 * 7 * 24);
        assertEquals(expected, test.toStandardHours());
    }

    public void testToStandardMinutes_1_oe() {
        Weeks test = Weeks.weeks(2);
        Minutes expected = Minutes.minutes(2 * 7 * 24 * 60);
        assertEquals(expected, test.toStandardMinutes());
    }

    public void testToStandardSeconds_1_oe() {
        Weeks test = Weeks.weeks(2);
        Seconds expected = Seconds.seconds(2 * 7 * 24 * 60 * 60);
        assertEquals(expected, test.toStandardSeconds());
    }

    public void testToStandardDuration_1_oe() {
        Weeks test = Weeks.weeks(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_WEEK);
        assertEquals(expected, test.toStandardDuration());
    }

    public void testToStandardDuration_2_oe() {
        Weeks test = Weeks.weeks(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_WEEK);
        // removed other assertion
        
        expected = new Duration(((long) Integer.MAX_VALUE) * DateTimeConstants.MILLIS_PER_WEEK);
        assertEquals(expected, Weeks.MAX_VALUE.toStandardDuration());
    }

    public void testPlus_int_1_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks result = test2.plus(3);
        assertEquals(2, test2.getWeeks());
    }

    public void testPlus_int_2_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks result = test2.plus(3);
        // removed other assertion
        assertEquals(5, result.getWeeks());
    }

    public void testPlus_int_3_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks result = test2.plus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1, Weeks.ONE.plus(0).getWeeks());
    }

    public void testPlus_Weeks_1_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks test3 = Weeks.weeks(3);
        Weeks result = test2.plus(test3);
        assertEquals(2, test2.getWeeks());
    }

    public void testPlus_Weeks_2_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks test3 = Weeks.weeks(3);
        Weeks result = test2.plus(test3);
        // removed other assertion
        assertEquals(3, test3.getWeeks());
    }

    public void testPlus_Weeks_3_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks test3 = Weeks.weeks(3);
        Weeks result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(5, result.getWeeks());
    }

    public void testPlus_Weeks_4_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks test3 = Weeks.weeks(3);
        Weeks result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1, Weeks.ONE.plus(Weeks.ZERO).getWeeks());
    }

    public void testPlus_Weeks_5_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks test3 = Weeks.weeks(3);
        Weeks result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1, Weeks.ONE.plus((Weeks) null).getWeeks());
    }

    public void testMinus_int_1_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks result = test2.minus(3);
        assertEquals(2, test2.getWeeks());
    }

    public void testMinus_int_2_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks result = test2.minus(3);
        // removed other assertion
        assertEquals(-1, result.getWeeks());
    }

    public void testMinus_int_3_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks result = test2.minus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1, Weeks.ONE.minus(0).getWeeks());
    }

    public void testMinus_Weeks_1_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks test3 = Weeks.weeks(3);
        Weeks result = test2.minus(test3);
        assertEquals(2, test2.getWeeks());
    }

    public void testMinus_Weeks_2_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks test3 = Weeks.weeks(3);
        Weeks result = test2.minus(test3);
        // removed other assertion
        assertEquals(3, test3.getWeeks());
    }

    public void testMinus_Weeks_3_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks test3 = Weeks.weeks(3);
        Weeks result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(-1, result.getWeeks());
    }

    public void testMinus_Weeks_4_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks test3 = Weeks.weeks(3);
        Weeks result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1, Weeks.ONE.minus(Weeks.ZERO).getWeeks());
    }

    public void testMinus_Weeks_5_oe() {
        Weeks test2 = Weeks.weeks(2);
        Weeks test3 = Weeks.weeks(3);
        Weeks result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1, Weeks.ONE.minus((Weeks) null).getWeeks());
    }

    public void testMultipliedBy_int_1_oe() {
        Weeks test = Weeks.weeks(2);
        assertEquals(6, test.multipliedBy(3).getWeeks());
    }

    public void testMultipliedBy_int_2_oe() {
        Weeks test = Weeks.weeks(2);
        // removed other assertion
        assertEquals(2, test.getWeeks());
    }

    public void testMultipliedBy_int_3_oe() {
        Weeks test = Weeks.weeks(2);
        // removed other assertion
        // removed other assertion
        assertEquals(-6, test.multipliedBy(-3).getWeeks());
    }

    public void testMultipliedBy_int_4_oe() {
        Weeks test = Weeks.weeks(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.multipliedBy(1));
    }

    public void testDividedBy_int_1_oe() {
        Weeks test = Weeks.weeks(12);
        assertEquals(6, test.dividedBy(2).getWeeks());
    }

    public void testDividedBy_int_2_oe() {
        Weeks test = Weeks.weeks(12);
        // removed other assertion
        assertEquals(12, test.getWeeks());
    }

    public void testDividedBy_int_3_oe() {
        Weeks test = Weeks.weeks(12);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.dividedBy(3).getWeeks());
    }

    public void testDividedBy_int_4_oe() {
        Weeks test = Weeks.weeks(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.dividedBy(4).getWeeks());
    }

    public void testDividedBy_int_5_oe() {
        Weeks test = Weeks.weeks(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.dividedBy(5).getWeeks());
    }

    public void testDividedBy_int_6_oe() {
        Weeks test = Weeks.weeks(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.dividedBy(6).getWeeks());
    }

    public void testDividedBy_int_7_oe() {
        Weeks test = Weeks.weeks(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.dividedBy(1));
    }

    public void testNegated_1_oe() {
        Weeks test = Weeks.weeks(12);
        assertEquals(-12, test.negated().getWeeks());
    }

    public void testNegated_2_oe() {
        Weeks test = Weeks.weeks(12);
        // removed other assertion
        assertEquals(12, test.getWeeks());
    }

    public void testAddToLocalDate_1_oe() {
        Weeks test = Weeks.weeks(3);
        LocalDate date = new LocalDate(2006, 6, 1);
        LocalDate expected = new LocalDate(2006, 6, 22);
        assertEquals(expected, date.plus(test));
    }

}
