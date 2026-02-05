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
 * This class is a Junit unit test for Minutes.
 *
 * @author Stephen Colebourne
 */
public class TestMinutes_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMinutes_OE25Dev.class);
    }

    public TestMinutes_OE25Dev(String name) {
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
        assertEquals(0,Minutes.ZERO.getMinutes());
    }

public void testConstants_2_oe() {
        // removed other assertion
        assertEquals(1,Minutes.ONE.getMinutes());
    }

public void testConstants_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2,Minutes.TWO.getMinutes());
    }

public void testConstants_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,Minutes.THREE.getMinutes());
    }

public void testConstants_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MAX_VALUE,Minutes.MAX_VALUE.getMinutes());
    }

public void testConstants_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE,Minutes.MIN_VALUE.getMinutes());
    }

public void testFactory_minutes_int_1_oe() {
        assertSame(Minutes.ZERO,Minutes.minutes(0));
    }

public void testFactory_minutes_int_2_oe() {
        // removed other assertion
        assertSame(Minutes.ONE,Minutes.minutes(1));
    }

public void testFactory_minutes_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertSame(Minutes.TWO,Minutes.minutes(2));
    }

public void testFactory_minutes_int_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Minutes.THREE,Minutes.minutes(3));
    }

public void testFactory_minutes_int_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Minutes.MAX_VALUE,Minutes.minutes(Integer.MAX_VALUE));
    }

public void testFactory_minutes_int_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Minutes.MIN_VALUE,Minutes.minutes(Integer.MIN_VALUE));
    }

public void testFactory_minutes_int_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1,Minutes.minutes(-1).getMinutes());
    }

public void testFactory_minutes_int_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4,Minutes.minutes(4).getMinutes());
    }

public void testFactory_minutesBetween_RInstant_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 3, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 6, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 9, 0, 0, PARIS);
        
        assertEquals(3,Minutes.minutesBetween(start,end1).getMinutes());
    }

public void testFactory_minutesBetween_RInstant_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 3, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 6, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 9, 0, 0, PARIS);
        
        // removed other assertion
        assertEquals(0,Minutes.minutesBetween(start,start).getMinutes());
    }

public void testFactory_minutesBetween_RInstant_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 3, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 6, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 9, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Minutes.minutesBetween(end1,end1).getMinutes());
    }

public void testFactory_minutesBetween_RInstant_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 3, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 6, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 9, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Minutes.minutesBetween(end1,start).getMinutes());
    }

public void testFactory_minutesBetween_RInstant_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 3, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 6, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 9, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Minutes.minutesBetween(start,end2).getMinutes());
    }

public void testFactory_minutesBetween_RPartial_1_oe() {
        LocalTime start = new LocalTime(12, 3);
        LocalTime end1 = new LocalTime(12, 6);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(12, 9);
        
        assertEquals(3,Minutes.minutesBetween(start,end1).getMinutes());
    }

public void testFactory_minutesBetween_RPartial_2_oe() {
        LocalTime start = new LocalTime(12, 3);
        LocalTime end1 = new LocalTime(12, 6);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(12, 9);
        
        // removed other assertion
        assertEquals(0,Minutes.minutesBetween(start,start).getMinutes());
    }

public void testFactory_minutesBetween_RPartial_3_oe() {
        LocalTime start = new LocalTime(12, 3);
        LocalTime end1 = new LocalTime(12, 6);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(12, 9);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Minutes.minutesBetween(end1,end1).getMinutes());
    }

public void testFactory_minutesBetween_RPartial_4_oe() {
        LocalTime start = new LocalTime(12, 3);
        LocalTime end1 = new LocalTime(12, 6);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(12, 9);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Minutes.minutesBetween(end1,start).getMinutes());
    }

public void testFactory_minutesBetween_RPartial_5_oe() {
        LocalTime start = new LocalTime(12, 3);
        LocalTime end1 = new LocalTime(12, 6);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(12, 9);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Minutes.minutesBetween(start,end2).getMinutes());
    }

public void testFactory_minutesIn_RInterval_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 3, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 6, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 9, 0, 0, PARIS);
        
        assertEquals(0,Minutes.minutesIn((ReadableInterval)null).getMinutes());
    }

public void testFactory_minutesIn_RInterval_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 3, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 6, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 9, 0, 0, PARIS);
        
        // removed other assertion
        assertEquals(3,Minutes.minutesIn(new Interval(start,end1)).getMinutes());
    }

public void testFactory_minutesIn_RInterval_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 3, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 6, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 9, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Minutes.minutesIn(new Interval(start,start)).getMinutes());
    }

public void testFactory_minutesIn_RInterval_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 3, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 6, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 9, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,Minutes.minutesIn(new Interval(end1,end1)).getMinutes());
    }

public void testFactory_minutesIn_RInterval_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 3, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 6, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 9, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Minutes.minutesIn(new Interval(start,end2)).getMinutes());
    }

public void testFactory_standardMinutesIn_RPeriod_1_oe() {
        assertEquals(0,Minutes.standardMinutesIn((ReadablePeriod)null).getMinutes());
    }

public void testFactory_standardMinutesIn_RPeriod_2_oe() {
        // removed other assertion
        assertEquals(0,Minutes.standardMinutesIn(Period.ZERO).getMinutes());
    }

public void testFactory_standardMinutesIn_RPeriod_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1,Minutes.standardMinutesIn(new Period(0,0,0,0,0,1,0,0)).getMinutes());
    }

public void testFactory_standardMinutesIn_RPeriod_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(123,Minutes.standardMinutesIn(Period.minutes(123)).getMinutes());
    }

public void testFactory_standardMinutesIn_RPeriod_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-987,Minutes.standardMinutesIn(Period.minutes(-987)).getMinutes());
    }

public void testFactory_standardMinutesIn_RPeriod_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,Minutes.standardMinutesIn(Period.seconds(119)).getMinutes());
    }

public void testFactory_standardMinutesIn_RPeriod_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,Minutes.standardMinutesIn(Period.seconds(120)).getMinutes());
    }

public void testFactory_standardMinutesIn_RPeriod_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,Minutes.standardMinutesIn(Period.seconds(121)).getMinutes());
    }

public void testFactory_standardMinutesIn_RPeriod_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(120,Minutes.standardMinutesIn(Period.hours(2)).getMinutes());
    }

public void testFactory_parseMinutes_String_1_oe() {
        assertEquals(0,Minutes.parseMinutes((String)null).getMinutes());
    }

public void testFactory_parseMinutes_String_2_oe() {
        // removed other assertion
        assertEquals(0,Minutes.parseMinutes("PT0M").getMinutes());
    }

public void testFactory_parseMinutes_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1,Minutes.parseMinutes("PT1M").getMinutes());
    }

public void testFactory_parseMinutes_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Minutes.parseMinutes("PT-3M").getMinutes());
    }

public void testFactory_parseMinutes_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,Minutes.parseMinutes("P0Y0M0DT2M").getMinutes());
    }

public void testFactory_parseMinutes_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,Minutes.parseMinutes("PT0H2M").getMinutes());
    }

public void testGetMethods_1_oe() {
        Minutes test = Minutes.minutes(20);
        assertEquals(20,test.getMinutes());
    }

public void testGetFieldType_1_oe() {
        Minutes test = Minutes.minutes(20);
        assertEquals(DurationFieldType.minutes(),test.getFieldType());
    }

public void testGetPeriodType_1_oe() {
        Minutes test = Minutes.minutes(20);
        assertEquals(PeriodType.minutes(),test.getPeriodType());
    }

public void testIsGreaterThan_1_oe() {
        assertEquals(true,Minutes.THREE.isGreaterThan(Minutes.TWO));
    }

public void testIsGreaterThan_2_oe() {
        // removed other assertion
        assertEquals(false,Minutes.THREE.isGreaterThan(Minutes.THREE));
    }

public void testIsGreaterThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false,Minutes.TWO.isGreaterThan(Minutes.THREE));
    }

public void testIsGreaterThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Minutes.ONE.isGreaterThan(null));
    }

public void testIsGreaterThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,Minutes.minutes(-1).isGreaterThan(null));
    }

public void testIsLessThan_1_oe() {
        assertEquals(false,Minutes.THREE.isLessThan(Minutes.TWO));
    }

public void testIsLessThan_2_oe() {
        // removed other assertion
        assertEquals(false,Minutes.THREE.isLessThan(Minutes.THREE));
    }

public void testIsLessThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true,Minutes.TWO.isLessThan(Minutes.THREE));
    }

public void testIsLessThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,Minutes.ONE.isLessThan(null));
    }

public void testIsLessThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Minutes.minutes(-1).isLessThan(null));
    }

public void testToString_1_oe() {
        Minutes test = Minutes.minutes(20);
        assertEquals("PT20M",test.toString());
    }

public void testToString_2_oe() {
        Minutes test = Minutes.minutes(20);
        // removed other assertion
        
        test = Minutes.minutes(-20);
        assertEquals("PT-20M",test.toString());
    }

public void testSerialization_1_oe() throws Exception {
        Minutes test = Minutes.THREE;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Minutes result = (Minutes) ois.readObject();
        ois.close();
        
        assertSame(test,result);
    }

public void testToStandardWeeks_1_oe() {
        Minutes test = Minutes.minutes(60 * 24 * 7 * 2);
        Weeks expected = Weeks.weeks(2);
        assertEquals(expected,test.toStandardWeeks());
    }

public void testToStandardDays_1_oe() {
        Minutes test = Minutes.minutes(60 * 24 * 2);
        Days expected = Days.days(2);
        assertEquals(expected,test.toStandardDays());
    }

public void testToStandardHours_1_oe() {
        Minutes test = Minutes.minutes(3 * 60);
        Hours expected = Hours.hours(3);
        assertEquals(expected,test.toStandardHours());
    }

public void testToStandardSeconds_1_oe() {
        Minutes test = Minutes.minutes(3);
        Seconds expected = Seconds.seconds(3 * 60);
        assertEquals(expected,test.toStandardSeconds());
    }

public void testToStandardDuration_1_oe() {
        Minutes test = Minutes.minutes(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_MINUTE);
        assertEquals(expected,test.toStandardDuration());
    }

public void testToStandardDuration_2_oe() {
        Minutes test = Minutes.minutes(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_MINUTE);
        // removed other assertion
        
        expected = new Duration(((long) Integer.MAX_VALUE) * DateTimeConstants.MILLIS_PER_MINUTE);
        assertEquals(expected,Minutes.MAX_VALUE.toStandardDuration());
    }

public void testPlus_int_1_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes result = test2.plus(3);
        assertEquals(2,test2.getMinutes());
    }

public void testPlus_int_2_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes result = test2.plus(3);
        // removed other assertion
        assertEquals(5,result.getMinutes());
    }

public void testPlus_int_3_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes result = test2.plus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Minutes.ONE.plus(0).getMinutes());
    }

public void testPlus_Minutes_1_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes test3 = Minutes.minutes(3);
        Minutes result = test2.plus(test3);
        assertEquals(2,test2.getMinutes());
    }

public void testPlus_Minutes_2_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes test3 = Minutes.minutes(3);
        Minutes result = test2.plus(test3);
        // removed other assertion
        assertEquals(3,test3.getMinutes());
    }

public void testPlus_Minutes_3_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes test3 = Minutes.minutes(3);
        Minutes result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(5,result.getMinutes());
    }

public void testPlus_Minutes_4_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes test3 = Minutes.minutes(3);
        Minutes result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Minutes.ONE.plus(Minutes.ZERO).getMinutes());
    }

public void testPlus_Minutes_5_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes test3 = Minutes.minutes(3);
        Minutes result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1,Minutes.ONE.plus((Minutes)null).getMinutes());
    }

public void testMinus_int_1_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes result = test2.minus(3);
        assertEquals(2,test2.getMinutes());
    }

public void testMinus_int_2_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes result = test2.minus(3);
        // removed other assertion
        assertEquals(-1,result.getMinutes());
    }

public void testMinus_int_3_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes result = test2.minus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Minutes.ONE.minus(0).getMinutes());
    }

public void testMinus_Minutes_1_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes test3 = Minutes.minutes(3);
        Minutes result = test2.minus(test3);
        assertEquals(2,test2.getMinutes());
    }

public void testMinus_Minutes_2_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes test3 = Minutes.minutes(3);
        Minutes result = test2.minus(test3);
        // removed other assertion
        assertEquals(3,test3.getMinutes());
    }

public void testMinus_Minutes_3_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes test3 = Minutes.minutes(3);
        Minutes result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(-1,result.getMinutes());
    }

public void testMinus_Minutes_4_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes test3 = Minutes.minutes(3);
        Minutes result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Minutes.ONE.minus(Minutes.ZERO).getMinutes());
    }

public void testMinus_Minutes_5_oe() {
        Minutes test2 = Minutes.minutes(2);
        Minutes test3 = Minutes.minutes(3);
        Minutes result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1,Minutes.ONE.minus((Minutes)null).getMinutes());
    }

public void testMultipliedBy_int_1_oe() {
        Minutes test = Minutes.minutes(2);
        assertEquals(6,test.multipliedBy(3).getMinutes());
    }

public void testMultipliedBy_int_2_oe() {
        Minutes test = Minutes.minutes(2);
        // removed other assertion
        assertEquals(2,test.getMinutes());
    }

public void testMultipliedBy_int_3_oe() {
        Minutes test = Minutes.minutes(2);
        // removed other assertion
        // removed other assertion
        assertEquals(-6,test.multipliedBy(-3).getMinutes());
    }

public void testMultipliedBy_int_4_oe() {
        Minutes test = Minutes.minutes(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test,test.multipliedBy(1));
    }

public void testDividedBy_int_1_oe() {
        Minutes test = Minutes.minutes(12);
        assertEquals(6,test.dividedBy(2).getMinutes());
    }

public void testDividedBy_int_2_oe() {
        Minutes test = Minutes.minutes(12);
        // removed other assertion
        assertEquals(12,test.getMinutes());
    }

public void testDividedBy_int_3_oe() {
        Minutes test = Minutes.minutes(12);
        // removed other assertion
        // removed other assertion
        assertEquals(4,test.dividedBy(3).getMinutes());
    }

public void testDividedBy_int_4_oe() {
        Minutes test = Minutes.minutes(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,test.dividedBy(4).getMinutes());
    }

public void testDividedBy_int_5_oe() {
        Minutes test = Minutes.minutes(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.dividedBy(5).getMinutes());
    }

public void testDividedBy_int_6_oe() {
        Minutes test = Minutes.minutes(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.dividedBy(6).getMinutes());
    }

public void testDividedBy_int_7_oe() {
        Minutes test = Minutes.minutes(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test,test.dividedBy(1));
    }

public void testNegated_1_oe() {
        Minutes test = Minutes.minutes(12);
        assertEquals(-12,test.negated().getMinutes());
    }

public void testNegated_2_oe() {
        Minutes test = Minutes.minutes(12);
        // removed other assertion
        assertEquals(12,test.getMinutes());
    }

public void testAddToLocalDate_1_oe() {
        Minutes test = Minutes.minutes(26);
        LocalDateTime date = new LocalDateTime(2006, 6, 1, 0, 0, 0, 0);
        LocalDateTime expected = new LocalDateTime(2006, 6, 1, 0, 26, 0, 0);
        assertEquals(expected,date.plus(test));
    }

}
