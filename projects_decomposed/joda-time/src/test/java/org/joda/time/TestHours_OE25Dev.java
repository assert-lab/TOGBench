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
 * This class is a Junit unit test for Hours.
 *
 * @author Stephen Colebourne
 */
public class TestHours_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestHours_OE25Dev.class);
    }

    public TestHours_OE25Dev(String name) {
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
        assertEquals(0, Hours.ZERO.getHours());
    }

    public void testConstants_2_oe() {
        // removed other assertion
        assertEquals(1, Hours.ONE.getHours());
    }

    public void testConstants_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2, Hours.TWO.getHours());
    }

    public void testConstants_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, Hours.THREE.getHours());
    }

    public void testConstants_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, Hours.FOUR.getHours());
    }

    public void testConstants_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, Hours.FIVE.getHours());
    }

    public void testConstants_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, Hours.SIX.getHours());
    }

    public void testConstants_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, Hours.SEVEN.getHours());
    }

    public void testConstants_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, Hours.EIGHT.getHours());
    }

    public void testConstants_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, Hours.MAX_VALUE.getHours());
    }

    public void testConstants_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, Hours.MIN_VALUE.getHours());
    }

    public void testFactory_hours_int_1_oe() {
        assertEquals(Hours.ZERO, Hours.hours(0));
    }

    public void testFactory_hours_int_2_oe() {
        // removed other assertion
        assertEquals(Hours.ONE, Hours.hours(1));
    }

    public void testFactory_hours_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Hours.TWO, Hours.hours(2));
    }

    public void testFactory_hours_int_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Hours.THREE, Hours.hours(3));
    }

    public void testFactory_hours_int_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Hours.FOUR, Hours.hours(4));
    }

    public void testFactory_hours_int_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Hours.FIVE, Hours.hours(5));
    }

    public void testFactory_hours_int_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Hours.SIX, Hours.hours(6));
    }

    public void testFactory_hours_int_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Hours.SEVEN, Hours.hours(7));
    }

    public void testFactory_hours_int_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Hours.EIGHT, Hours.hours(8));
    }

    public void testFactory_hours_int_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Hours.MAX_VALUE, Hours.hours(Integer.MAX_VALUE));
    }

    public void testFactory_hours_int_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Hours.MIN_VALUE, Hours.hours(Integer.MIN_VALUE));
    }

    public void testFactory_hours_int_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, Hours.hours(-1).getHours());
    }

    public void testFactory_hours_int_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, Hours.hours(9).getHours());
    }

    public void testFactory_hoursBetween_RInstant_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertEquals(3, Hours.hoursBetween(start, end1).getHours());
    }

    public void testFactory_hoursBetween_RInstant_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        // removed other assertion
        assertEquals(0, Hours.hoursBetween(start, start).getHours());
    }

    public void testFactory_hoursBetween_RInstant_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0, Hours.hoursBetween(end1, end1).getHours());
    }

    public void testFactory_hoursBetween_RInstant_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3, Hours.hoursBetween(end1, start).getHours());
    }

    public void testFactory_hoursBetween_RInstant_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, Hours.hoursBetween(start, end2).getHours());
    }

    public void testFactory_hoursBetween_RPartial_1_oe() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        assertEquals(3, Hours.hoursBetween(start, end1).getHours());
    }

    public void testFactory_hoursBetween_RPartial_2_oe() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        // removed other assertion
        assertEquals(0, Hours.hoursBetween(start, start).getHours());
    }

    public void testFactory_hoursBetween_RPartial_3_oe() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0, Hours.hoursBetween(end1, end1).getHours());
    }

    public void testFactory_hoursBetween_RPartial_4_oe() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3, Hours.hoursBetween(end1, start).getHours());
    }

    public void testFactory_hoursBetween_RPartial_5_oe() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, Hours.hoursBetween(start, end2).getHours());
    }

    public void testFactory_hoursIn_RInterval_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertEquals(0, Hours.hoursIn((ReadableInterval) null).getHours());
    }

    public void testFactory_hoursIn_RInterval_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        // removed other assertion
        assertEquals(3, Hours.hoursIn(new Interval(start, end1)).getHours());
    }

    public void testFactory_hoursIn_RInterval_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0, Hours.hoursIn(new Interval(start, start)).getHours());
    }

    public void testFactory_hoursIn_RInterval_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, Hours.hoursIn(new Interval(end1, end1)).getHours());
    }

    public void testFactory_hoursIn_RInterval_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, Hours.hoursIn(new Interval(start, end2)).getHours());
    }

    public void testFactory_standardHoursIn_RPeriod_1_oe() {
        assertEquals(0, Hours.standardHoursIn((ReadablePeriod) null).getHours());
    }

    public void testFactory_standardHoursIn_RPeriod_2_oe() {
        // removed other assertion
        assertEquals(0, Hours.standardHoursIn(Period.ZERO).getHours());
    }

    public void testFactory_standardHoursIn_RPeriod_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1, Hours.standardHoursIn(new Period(0, 0, 0, 0, 1, 0, 0, 0)).getHours());
    }

    public void testFactory_standardHoursIn_RPeriod_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(123, Hours.standardHoursIn(Period.hours(123)).getHours());
    }

    public void testFactory_standardHoursIn_RPeriod_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-987, Hours.standardHoursIn(Period.hours(-987)).getHours());
    }

    public void testFactory_standardHoursIn_RPeriod_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, Hours.standardHoursIn(Period.minutes(119)).getHours());
    }

    public void testFactory_standardHoursIn_RPeriod_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, Hours.standardHoursIn(Period.minutes(120)).getHours());
    }

    public void testFactory_standardHoursIn_RPeriod_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, Hours.standardHoursIn(Period.minutes(121)).getHours());
    }

    public void testFactory_standardHoursIn_RPeriod_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(48, Hours.standardHoursIn(Period.days(2)).getHours());
    }

    public void testFactory_parseHours_String_1_oe() {
        assertEquals(0, Hours.parseHours((String) null).getHours());
    }

    public void testFactory_parseHours_String_2_oe() {
        // removed other assertion
        assertEquals(0, Hours.parseHours("PT0H").getHours());
    }

    public void testFactory_parseHours_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1, Hours.parseHours("PT1H").getHours());
    }

    public void testFactory_parseHours_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3, Hours.parseHours("PT-3H").getHours());
    }

    public void testFactory_parseHours_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, Hours.parseHours("P0Y0M0DT2H").getHours());
    }

    public void testFactory_parseHours_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, Hours.parseHours("PT2H0M").getHours());
    }

    public void testGetMethods_1_oe() {
        Hours test = Hours.hours(20);
        assertEquals(20, test.getHours());
    }

    public void testGetFieldType_1_oe() {
        Hours test = Hours.hours(20);
        assertEquals(DurationFieldType.hours(), test.getFieldType());
    }

    public void testGetPeriodType_1_oe() {
        Hours test = Hours.hours(20);
        assertEquals(PeriodType.hours(), test.getPeriodType());
    }

    public void testIsGreaterThan_1_oe() {
        assertEquals(true, Hours.THREE.isGreaterThan(Hours.TWO));
    }

    public void testIsGreaterThan_2_oe() {
        // removed other assertion
        assertEquals(false, Hours.THREE.isGreaterThan(Hours.THREE));
    }

    public void testIsGreaterThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false, Hours.TWO.isGreaterThan(Hours.THREE));
    }

    public void testIsGreaterThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, Hours.ONE.isGreaterThan(null));
    }

    public void testIsGreaterThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, Hours.hours(-1).isGreaterThan(null));
    }

    public void testIsLessThan_1_oe() {
        assertEquals(false, Hours.THREE.isLessThan(Hours.TWO));
    }

    public void testIsLessThan_2_oe() {
        // removed other assertion
        assertEquals(false, Hours.THREE.isLessThan(Hours.THREE));
    }

    public void testIsLessThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true, Hours.TWO.isLessThan(Hours.THREE));
    }

    public void testIsLessThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, Hours.ONE.isLessThan(null));
    }

    public void testIsLessThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, Hours.hours(-1).isLessThan(null));
    }

    public void testToString_1_oe() {
        Hours test = Hours.hours(20);
        assertEquals("PT20H", test.toString());
    }

    public void testToString_2_oe() {
        Hours test = Hours.hours(20);
        // removed other assertion
        
        test = Hours.hours(-20);
        assertEquals("PT-20H", test.toString());
    }

    public void testSerialization_1_oe() throws Exception {
        Hours test = Hours.SEVEN;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Hours result = (Hours) ois.readObject();
        ois.close();
        
        assertEquals(test, result);
    }

    public void testToStandardWeeks_1_oe() {
        Hours test = Hours.hours(24 * 7 * 2);
        Weeks expected = Weeks.weeks(2);
        assertEquals(expected, test.toStandardWeeks());
    }

    public void testToStandardDays_1_oe() {
        Hours test = Hours.hours(24 * 2);
        Days expected = Days.days(2);
        assertEquals(expected, test.toStandardDays());
    }

    public void testToStandardMinutes_1_oe() {
        Hours test = Hours.hours(3);
        Minutes expected = Minutes.minutes(3 * 60);
        assertEquals(expected, test.toStandardMinutes());
    }

    public void testToStandardSeconds_1_oe() {
        Hours test = Hours.hours(3);
        Seconds expected = Seconds.seconds(3 * 60 * 60);
        assertEquals(expected, test.toStandardSeconds());
    }

    public void testToStandardDuration_1_oe() {
        Hours test = Hours.hours(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_HOUR);
        assertEquals(expected, test.toStandardDuration());
    }

    public void testToStandardDuration_2_oe() {
        Hours test = Hours.hours(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_HOUR);
        // removed other assertion
        
        expected = new Duration(((long) Integer.MAX_VALUE) * DateTimeConstants.MILLIS_PER_HOUR);
        assertEquals(expected, Hours.MAX_VALUE.toStandardDuration());
    }

    public void testPlus_int_1_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.plus(3);
        assertEquals(2, test2.getHours());
    }

    public void testPlus_int_2_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.plus(3);
        // removed other assertion
        assertEquals(5, result.getHours());
    }

    public void testPlus_int_3_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.plus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1, Hours.ONE.plus(0).getHours());
    }

    public void testPlus_Hours_1_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.plus(test3);
        assertEquals(2, test2.getHours());
    }

    public void testPlus_Hours_2_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.plus(test3);
        // removed other assertion
        assertEquals(3, test3.getHours());
    }

    public void testPlus_Hours_3_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(5, result.getHours());
    }

    public void testPlus_Hours_4_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1, Hours.ONE.plus(Hours.ZERO).getHours());
    }

    public void testPlus_Hours_5_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1, Hours.ONE.plus((Hours) null).getHours());
    }

    public void testMinus_int_1_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.minus(3);
        assertEquals(2, test2.getHours());
    }

    public void testMinus_int_2_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.minus(3);
        // removed other assertion
        assertEquals(-1, result.getHours());
    }

    public void testMinus_int_3_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.minus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1, Hours.ONE.minus(0).getHours());
    }

    public void testMinus_Hours_1_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.minus(test3);
        assertEquals(2, test2.getHours());
    }

    public void testMinus_Hours_2_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.minus(test3);
        // removed other assertion
        assertEquals(3, test3.getHours());
    }

    public void testMinus_Hours_3_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(-1, result.getHours());
    }

    public void testMinus_Hours_4_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1, Hours.ONE.minus(Hours.ZERO).getHours());
    }

    public void testMinus_Hours_5_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1, Hours.ONE.minus((Hours) null).getHours());
    }

    public void testMultipliedBy_int_1_oe() {
        Hours test = Hours.hours(2);
        assertEquals(6, test.multipliedBy(3).getHours());
    }

    public void testMultipliedBy_int_2_oe() {
        Hours test = Hours.hours(2);
        // removed other assertion
        assertEquals(2, test.getHours());
    }

    public void testMultipliedBy_int_3_oe() {
        Hours test = Hours.hours(2);
        // removed other assertion
        // removed other assertion
        assertEquals(-6, test.multipliedBy(-3).getHours());
    }

    public void testMultipliedBy_int_4_oe() {
        Hours test = Hours.hours(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, test.multipliedBy(1));
    }

    public void testDividedBy_int_1_oe() {
        Hours test = Hours.hours(12);
        assertEquals(6, test.dividedBy(2).getHours());
    }

    public void testDividedBy_int_2_oe() {
        Hours test = Hours.hours(12);
        // removed other assertion
        assertEquals(12, test.getHours());
    }

    public void testDividedBy_int_3_oe() {
        Hours test = Hours.hours(12);
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.dividedBy(3).getHours());
    }

    public void testDividedBy_int_4_oe() {
        Hours test = Hours.hours(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.dividedBy(4).getHours());
    }

    public void testDividedBy_int_5_oe() {
        Hours test = Hours.hours(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.dividedBy(5).getHours());
    }

    public void testDividedBy_int_6_oe() {
        Hours test = Hours.hours(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.dividedBy(6).getHours());
    }

    public void testDividedBy_int_7_oe() {
        Hours test = Hours.hours(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, test.dividedBy(1));
    }

    public void testNegated_1_oe() {
        Hours test = Hours.hours(12);
        assertEquals(-12, test.negated().getHours());
    }

    public void testNegated_2_oe() {
        Hours test = Hours.hours(12);
        // removed other assertion
        assertEquals(12, test.getHours());
    }

    public void testAddToLocalDate_1_oe() {
        Hours test = Hours.hours(26);
        LocalDateTime date = new LocalDateTime(2006, 6, 1, 0, 0, 0, 0);
        LocalDateTime expected = new LocalDateTime(2006, 6, 2, 2, 0, 0, 0);
        assertEquals(expected, date.plus(test));
    }

}
