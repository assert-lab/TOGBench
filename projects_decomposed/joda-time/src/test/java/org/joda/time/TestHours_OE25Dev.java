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
    public void testConstants() {
        assertEquals(0,Hours.ZERO.getHours());
        assertEquals(1,Hours.ONE.getHours());
        assertEquals(2,Hours.TWO.getHours());
        assertEquals(3,Hours.THREE.getHours());
        assertEquals(4,Hours.FOUR.getHours());
        assertEquals(5,Hours.FIVE.getHours());
        assertEquals(6,Hours.SIX.getHours());
        assertEquals(7,Hours.SEVEN.getHours());
        assertEquals(8,Hours.EIGHT.getHours());
        assertEquals(Integer.MAX_VALUE,Hours.MAX_VALUE.getHours());
        assertEquals(Integer.MIN_VALUE,Hours.MIN_VALUE.getHours());
    }

    //-----------------------------------------------------------------------
    public void testFactory_hours_int() {
        assertSame(Hours.ZERO,Hours.hours(0));
        assertSame(Hours.ONE,Hours.hours(1));
        assertSame(Hours.TWO,Hours.hours(2));
        assertSame(Hours.THREE,Hours.hours(3));
        assertSame(Hours.FOUR,Hours.hours(4));
        assertSame(Hours.FIVE,Hours.hours(5));
        assertSame(Hours.SIX,Hours.hours(6));
        assertSame(Hours.SEVEN,Hours.hours(7));
        assertSame(Hours.EIGHT,Hours.hours(8));
        assertSame(Hours.MAX_VALUE,Hours.hours(Integer.MAX_VALUE));
        assertSame(Hours.MIN_VALUE,Hours.hours(Integer.MIN_VALUE));
        assertEquals(-1,Hours.hours(-1).getHours());
        assertEquals(9,Hours.hours(9).getHours());
    }

    //-----------------------------------------------------------------------
    public void testFactory_hoursBetween_RInstant() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertEquals(3,Hours.hoursBetween(start,end1).getHours());
        assertEquals(0,Hours.hoursBetween(start,start).getHours());
        assertEquals(0,Hours.hoursBetween(end1,end1).getHours());
        assertEquals(-3,Hours.hoursBetween(end1,start).getHours());
        assertEquals(6,Hours.hoursBetween(start,end2).getHours());
    }

    public void testFactory_hoursBetween_RPartial() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        assertEquals(3,Hours.hoursBetween(start,end1).getHours());
        assertEquals(0,Hours.hoursBetween(start,start).getHours());
        assertEquals(0,Hours.hoursBetween(end1,end1).getHours());
        assertEquals(-3,Hours.hoursBetween(end1,start).getHours());
        assertEquals(6,Hours.hoursBetween(start,end2).getHours());
    }

    public void testFactory_hoursIn_RInterval() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertEquals(0,Hours.hoursIn((ReadableInterval)null).getHours());
        assertEquals(3,Hours.hoursIn(new Interval(start,end1)).getHours());
        assertEquals(0,Hours.hoursIn(new Interval(start,start)).getHours());
        assertEquals(0,Hours.hoursIn(new Interval(end1,end1)).getHours());
        assertEquals(6,Hours.hoursIn(new Interval(start,end2)).getHours());
    }

    public void testFactory_standardHoursIn_RPeriod() {
        assertEquals(0,Hours.standardHoursIn((ReadablePeriod)null).getHours());
        assertEquals(0,Hours.standardHoursIn(Period.ZERO).getHours());
        assertEquals(1,Hours.standardHoursIn(new Period(0,0,0,0,1,0,0,0)).getHours());
        assertEquals(123,Hours.standardHoursIn(Period.hours(123)).getHours());
        assertEquals(-987,Hours.standardHoursIn(Period.hours(-987)).getHours());
        assertEquals(1,Hours.standardHoursIn(Period.minutes(119)).getHours());
        assertEquals(2,Hours.standardHoursIn(Period.minutes(120)).getHours());
        assertEquals(2,Hours.standardHoursIn(Period.minutes(121)).getHours());
        assertEquals(48,Hours.standardHoursIn(Period.days(2)).getHours());
        try {
            Hours.standardHoursIn(Period.months(1));
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    public void testFactory_parseHours_String() {
        assertEquals(0,Hours.parseHours((String)null).getHours());
        assertEquals(0,Hours.parseHours("PT0H").getHours());
        assertEquals(1,Hours.parseHours("PT1H").getHours());
        assertEquals(-3,Hours.parseHours("PT-3H").getHours());
        assertEquals(2,Hours.parseHours("P0Y0M0DT2H").getHours());
        assertEquals(2,Hours.parseHours("PT2H0M").getHours());
        try {
            Hours.parseHours("P1Y1D");
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            Hours.parseHours("P1DT1H");
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void testGetMethods() {
        Hours test = Hours.hours(20);
        assertEquals(20,test.getHours());
    }

    public void testGetFieldType() {
        Hours test = Hours.hours(20);
        assertEquals(DurationFieldType.hours(),test.getFieldType());
    }

    public void testGetPeriodType() {
        Hours test = Hours.hours(20);
        assertEquals(PeriodType.hours(),test.getPeriodType());
    }

    //-----------------------------------------------------------------------
    public void testIsGreaterThan() {
        assertEquals(true,Hours.THREE.isGreaterThan(Hours.TWO));
        assertEquals(false,Hours.THREE.isGreaterThan(Hours.THREE));
        assertEquals(false,Hours.TWO.isGreaterThan(Hours.THREE));
        assertEquals(true,Hours.ONE.isGreaterThan(null));
        assertEquals(false,Hours.hours(-1).isGreaterThan(null));
    }

    public void testIsLessThan() {
        assertEquals(false,Hours.THREE.isLessThan(Hours.TWO));
        assertEquals(false,Hours.THREE.isLessThan(Hours.THREE));
        assertEquals(true,Hours.TWO.isLessThan(Hours.THREE));
        assertEquals(false,Hours.ONE.isLessThan(null));
        assertEquals(true,Hours.hours(-1).isLessThan(null));
    }

    //-----------------------------------------------------------------------
    public void testToString() {
        Hours test = Hours.hours(20);
        assertEquals("PT20H",test.toString());
        
        test = Hours.hours(-20);
        assertEquals("PT-20H",test.toString());
    }

    //-----------------------------------------------------------------------
    public void testSerialization() throws Exception {
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
        
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testToStandardWeeks() {
        Hours test = Hours.hours(24 * 7 * 2);
        Weeks expected = Weeks.weeks(2);
        assertEquals(expected,test.toStandardWeeks());
    }

    public void testToStandardDays() {
        Hours test = Hours.hours(24 * 2);
        Days expected = Days.days(2);
        assertEquals(expected,test.toStandardDays());
    }

    public void testToStandardMinutes() {
        Hours test = Hours.hours(3);
        Minutes expected = Minutes.minutes(3 * 60);
        assertEquals(expected,test.toStandardMinutes());
        
        try {
            Hours.MAX_VALUE.toStandardMinutes();
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testToStandardSeconds() {
        Hours test = Hours.hours(3);
        Seconds expected = Seconds.seconds(3 * 60 * 60);
        assertEquals(expected,test.toStandardSeconds());
        
        try {
            Hours.MAX_VALUE.toStandardSeconds();
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testToStandardDuration() {
        Hours test = Hours.hours(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_HOUR);
        assertEquals(expected,test.toStandardDuration());
        
        expected = new Duration(((long) Integer.MAX_VALUE) * DateTimeConstants.MILLIS_PER_HOUR);
        assertEquals(expected,Hours.MAX_VALUE.toStandardDuration());
    }

    //-----------------------------------------------------------------------
    public void testPlus_int() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.plus(3);
        assertEquals(2,test2.getHours());
        assertEquals(5,result.getHours());
        
        assertEquals(1,Hours.ONE.plus(0).getHours());
        
        try {
            Hours.MAX_VALUE.plus(1);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testPlus_Hours() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.plus(test3);
        assertEquals(2,test2.getHours());
        assertEquals(3,test3.getHours());
        assertEquals(5,result.getHours());
        
        assertEquals(1,Hours.ONE.plus(Hours.ZERO).getHours());
        assertEquals(1,Hours.ONE.plus((Hours)null).getHours());
        
        try {
            Hours.MAX_VALUE.plus(Hours.ONE);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testMinus_int() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.minus(3);
        assertEquals(2,test2.getHours());
        assertEquals(-1,result.getHours());
        
        assertEquals(1,Hours.ONE.minus(0).getHours());
        
        try {
            Hours.MIN_VALUE.minus(1);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testMinus_Hours() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.minus(test3);
        assertEquals(2,test2.getHours());
        assertEquals(3,test3.getHours());
        assertEquals(-1,result.getHours());
        
        assertEquals(1,Hours.ONE.minus(Hours.ZERO).getHours());
        assertEquals(1,Hours.ONE.minus((Hours)null).getHours());
        
        try {
            Hours.MIN_VALUE.minus(Hours.ONE);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testMultipliedBy_int() {
        Hours test = Hours.hours(2);
        assertEquals(6,test.multipliedBy(3).getHours());
        assertEquals(2,test.getHours());
        assertEquals(-6,test.multipliedBy(-3).getHours());
        assertSame(test,test.multipliedBy(1));
        
        Hours halfMax = Hours.hours(Integer.MAX_VALUE / 2 + 1);
        try {
            halfMax.multipliedBy(2);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testDividedBy_int() {
        Hours test = Hours.hours(12);
        assertEquals(6,test.dividedBy(2).getHours());
        assertEquals(12,test.getHours());
        assertEquals(4,test.dividedBy(3).getHours());
        assertEquals(3,test.dividedBy(4).getHours());
        assertEquals(2,test.dividedBy(5).getHours());
        assertEquals(2,test.dividedBy(6).getHours());
        assertSame(test,test.dividedBy(1));
        
        try {
            Hours.ONE.dividedBy(0);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testNegated() {
        Hours test = Hours.hours(12);
        assertEquals(-12,test.negated().getHours());
        assertEquals(12,test.getHours());
        
        try {
            Hours.MIN_VALUE.negated();
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void testAddToLocalDate() {
        Hours test = Hours.hours(26);
        LocalDateTime date = new LocalDateTime(2006, 6, 1, 0, 0, 0, 0);
        LocalDateTime expected = new LocalDateTime(2006, 6, 2, 2, 0, 0, 0);
        assertEquals(expected,date.plus(test));
    }

    public void testConstants_1_oe() {
        int a = 0;
// incorrect assertion         assertEquals(0, a.getHours());
    }

    public void testConstants_8_oe() {
        int a = 7;
// incorrect assertion         assertEquals(0, this.getHours());
    }

    public void testFactory_hours_int_1_oe() {
        Object a = Hours.ZERO;
        assertNotNull(Hours.hours(0));
    }

    public void testFactory_hours_int_2_oe() {
        Object a = Hours.ONE;
        assertNotNull(Hours.hours(1));
    }

    public void testFactory_hours_int_3_oe() {
        Object a = Hours.TWO;
        assertNotNull(Hours.hours(2));
    }

    public void testFactory_hours_int_4_oe() {
        Object a = Hours.THREE;
        assertNotNull(Hours.hours(3));
    }

    public void testFactory_hours_int_5_oe() {
        Object a = Hours.FOUR;
        assertNotNull(Hours.hours(4));
    }

    public void testFactory_hours_int_6_oe() {
        Object a = Hours.FIVE;
        assertNotNull(Hours.hours(5));
    }

    public void testFactory_hours_int_7_oe() {
        Object a = Hours.SIX;
        assertNotNull(Hours.hours(6));
    }

    public void testFactory_hours_int_8_oe() {
        Object a = Hours.SEVEN;
        assertNotNull(Hours.hours(7));
    }

    public void testFactory_hours_int_9_oe() {
        Object a = Hours.EIGHT;
        assertNotNull(Hours.hours(8));
    }

    public void testFactory_hours_int_10_oe() {
        Object a = Hours.MAX_VALUE;
        assertNotNull(Hours.hours(0));
    }

    public void testFactory_hours_int_11_oe() {
        Object a = Hours.MIN_VALUE;
        assertNotNull(Hours.hours(0));
    }

    public void testFactory_hours_int_12_oe() {
        int a = -1;
        assertNotNull(Hours.hours(0));
    }

    public void testFactory_hours_int_13_oe() {
        int a = 9;
        assertNotNull(Hours.hours(9));
    }

    public void testFactory_hoursBetween_RInstant_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursBetween_RInstant_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursBetween_RInstant_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursBetween_RInstant_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursBetween_RInstant_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursBetween_RPartial_1_oe() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursBetween_RPartial_2_oe() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursBetween_RPartial_3_oe() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursBetween_RPartial_4_oe() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursBetween_RPartial_5_oe() {
        LocalTime start = new LocalTime(12, 0);
        LocalTime end1 = new LocalTime(15, 0);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(18, 0);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursIn_RInterval_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursIn_RInterval_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursIn_RInterval_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursIn_RInterval_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_hoursIn_RInterval_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 15, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 18, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testGetMethods_1_oe() {
        Hours test = Hours.hours(20);
        assertEquals(20, test.getHours());
    }

    public void testGetFieldType_1_oe() {
        Hours test = Hours.hours(20);
        assertNotNull(test);
    }

    public void testGetPeriodType_1_oe() {
        Hours test = Hours.hours(20);
        assertNotNull(test);
    }

    public void testIsGreaterThan_5_oe() {
        boolean a = false;
        assertEquals(false, a);
    }

    public void testIsLessThan_5_oe() {
        boolean a = true;
        assertEquals(true, a);
    }

    public void testToString_1_oe() {
        Hours test = Hours.hours(20);
        assertNotNull(test);
    }

    public void testToString_2_oe() {
        Hours test = Hours.hours(20);
        
        test = Hours.hours(-20);
        assertNotNull(test);
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
        
        assertNotNull(result);
    }

    public void testToStandardDays_1_oe() {
        Hours test = Hours.hours(24 * 2);
        Days expected = Days.days(2);
        assertEquals(expected, test.toStandardDays());
    }

    public void testToStandardMinutes_1_oe() {
        Hours test = Hours.hours(3);
        Minutes expected = Minutes.minutes(3 * 60);
        assertEquals(Minutes.minutes(180), test.toStandardMinutes());
    }

    public void testPlus_int_1_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.plus(3);
        assertEquals(2, test2.getHours());
    }

    public void testPlus_int_2_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.plus(3);
        assertEquals(2, test2.getHours());
    }

    public void testPlus_int_3_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.plus(3);
        
        assertNotNull(result);
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
        assertEquals(2, test2.getHours());
    }

    public void testPlus_Hours_3_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.plus(test3);
        assertEquals(2, test2.getHours());
    }

    public void testPlus_Hours_4_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.plus(test3);
        
        assertNotNull(result);
    }

    public void testPlus_Hours_5_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.plus(test3);
        
// incorrect assertion         assertEquals(5, result.getValue());
    }

    public void testMinus_int_1_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.minus(3);
        assertEquals(2, test2.getHours());
    }

    public void testMinus_int_2_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.minus(3);
        assertEquals(2, test2.getHours());
    }

    public void testMinus_int_3_oe() {
        Hours test2 = Hours.hours(2);
        Hours result = test2.minus(3);
        
        assertNotNull(result);
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
        assertEquals(2, test2.getHours());
    }

    public void testMinus_Hours_3_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.minus(test3);
        assertEquals(2, test2.getHours());
    }

    public void testMinus_Hours_4_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.minus(test3);
        
// incorrect assertion         assertEquals(false, result.isNegative());
    }

    public void testMinus_Hours_5_oe() {
        Hours test2 = Hours.hours(2);
        Hours test3 = Hours.hours(3);
        Hours result = test2.minus(test3);
        
// incorrect assertion         assertEquals(false, result.isNegative());
    }

    public void testMultipliedBy_int_1_oe() {
        Hours test = Hours.hours(2);
        assertNotNull(test);
    }

    public void testMultipliedBy_int_2_oe() {
        Hours test = Hours.hours(2);
        assertEquals(2, test.getHours());
    }

    public void testMultipliedBy_int_3_oe() {
        Hours test = Hours.hours(2);
        assertNotNull(test);
    }

    public void testMultipliedBy_int_4_oe() {
        Hours test = Hours.hours(2);
        assertNotNull(test);
    }

    public void testDividedBy_int_1_oe() {
        Hours test = Hours.hours(12);
        assertEquals(Hours.hours(12), test.dividedBy(1));
    }

    public void testDividedBy_int_2_oe() {
        Hours test = Hours.hours(12);
        assertEquals(12, test.getHours());
    }

    public void testDividedBy_int_3_oe() {
        Hours test = Hours.hours(12);
        assertEquals(Hours.hours(12), test.dividedBy(1));
    }

    public void testDividedBy_int_4_oe() {
        Hours test = Hours.hours(12);
        assertNotNull(test);
    }

    public void testDividedBy_int_5_oe() {
        Hours test = Hours.hours(12);
        assertEquals(Hours.hours(12), test.dividedBy(1));
    }

    public void testDividedBy_int_6_oe() {
        Hours test = Hours.hours(12);
        assertEquals(Hours.hours(12), test.dividedBy(1));
    }

    public void testDividedBy_int_7_oe() {
        Hours test = Hours.hours(12);
        assertNotNull(test);
    }

    public void testNegated_1_oe() {
        Hours test = Hours.hours(12);
        assertEquals(Hours.hours(-12), test.negated());
    }

    public void testNegated_2_oe() {
        Hours test = Hours.hours(12);
        assertEquals(12, test.getHours());
    }

    public void testAddToLocalDate_1_oe() {
        Hours test = Hours.hours(26);
        LocalDateTime date = new LocalDateTime(2006, 6, 1, 0, 0, 0, 0);
        LocalDateTime expected = new LocalDateTime(2006, 6, 2, 2, 0, 0, 0);
        assertNotNull(expected);
    }

}
