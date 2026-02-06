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
 * This class is a Junit unit test for Seconds.
 *
 * @author Stephen Colebourne
 */
public class TestSeconds_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestSeconds_OE25Dev_OE25Dev.class);
    }

    public TestSeconds_OE25Dev(String name) {
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
        assertEquals(0,Seconds.ZERO.getSeconds());
    }

    public void testConstants_2_oe() {
        // removed other assertion
        assertEquals(1,Seconds.ONE.getSeconds());
    }

    public void testConstants_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2,Seconds.TWO.getSeconds());
    }

    public void testConstants_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,Seconds.THREE.getSeconds());
    }

    public void testConstants_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MAX_VALUE,Seconds.MAX_VALUE.getSeconds());
    }

    public void testConstants_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE,Seconds.MIN_VALUE.getSeconds());
    }

    public void testFactory_seconds_int_1_oe() {
        assertSame(Seconds.ZERO,Seconds.seconds(0));
    }

    public void testFactory_seconds_int_2_oe() {
        // removed other assertion
        assertSame(Seconds.ONE,Seconds.seconds(1));
    }

    public void testFactory_seconds_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertSame(Seconds.TWO,Seconds.seconds(2));
    }

    public void testFactory_seconds_int_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Seconds.THREE,Seconds.seconds(3));
    }

    public void testFactory_seconds_int_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Seconds.MAX_VALUE,Seconds.seconds(Integer.MAX_VALUE));
    }

    public void testFactory_seconds_int_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Seconds.MIN_VALUE,Seconds.seconds(Integer.MIN_VALUE));
    }

    public void testFactory_seconds_int_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1,Seconds.seconds(-1).getSeconds());
    }

    public void testFactory_seconds_int_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4,Seconds.seconds(4).getSeconds());
    }

    public void testFactory_secondsBetween_RInstant_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 3, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 0, 6, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 0, 9, 0, PARIS);
        
        assertEquals(3,Seconds.secondsBetween(start,end1).getSeconds());
    }

    public void testFactory_secondsBetween_RInstant_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 3, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 0, 6, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 0, 9, 0, PARIS);
        
        // removed other assertion
        assertEquals(0,Seconds.secondsBetween(start,start).getSeconds());
    }

    public void testFactory_secondsBetween_RInstant_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 3, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 0, 6, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 0, 9, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Seconds.secondsBetween(end1,end1).getSeconds());
    }

    public void testFactory_secondsBetween_RInstant_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 3, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 0, 6, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 0, 9, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Seconds.secondsBetween(end1,start).getSeconds());
    }

    public void testFactory_secondsBetween_RInstant_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 3, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 0, 6, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 0, 9, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Seconds.secondsBetween(start,end2).getSeconds());
    }

    public void testFactory_secondsBetween_RPartial_1_oe() {
        LocalTime start = new LocalTime(12, 0, 3);
        LocalTime end1 = new LocalTime(12, 0, 6);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(12, 0, 9);
        
        assertEquals(3,Seconds.secondsBetween(start,end1).getSeconds());
    }

    public void testFactory_secondsBetween_RPartial_2_oe() {
        LocalTime start = new LocalTime(12, 0, 3);
        LocalTime end1 = new LocalTime(12, 0, 6);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(12, 0, 9);
        
        // removed other assertion
        assertEquals(0,Seconds.secondsBetween(start,start).getSeconds());
    }

    public void testFactory_secondsBetween_RPartial_3_oe() {
        LocalTime start = new LocalTime(12, 0, 3);
        LocalTime end1 = new LocalTime(12, 0, 6);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(12, 0, 9);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Seconds.secondsBetween(end1,end1).getSeconds());
    }

    public void testFactory_secondsBetween_RPartial_4_oe() {
        LocalTime start = new LocalTime(12, 0, 3);
        LocalTime end1 = new LocalTime(12, 0, 6);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(12, 0, 9);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Seconds.secondsBetween(end1,start).getSeconds());
    }

    public void testFactory_secondsBetween_RPartial_5_oe() {
        LocalTime start = new LocalTime(12, 0, 3);
        LocalTime end1 = new LocalTime(12, 0, 6);
        @SuppressWarnings("deprecation")
        TimeOfDay end2 = new TimeOfDay(12, 0, 9);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Seconds.secondsBetween(start,end2).getSeconds());
    }

    public void testFactory_secondsIn_RInterval_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 3, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 0, 6, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 0, 9, 0, PARIS);
        
        assertEquals(0,Seconds.secondsIn((ReadableInterval)null).getSeconds());
    }

    public void testFactory_secondsIn_RInterval_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 3, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 0, 6, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 0, 9, 0, PARIS);
        
        // removed other assertion
        assertEquals(3,Seconds.secondsIn(new Interval(start,end1)).getSeconds());
    }

    public void testFactory_secondsIn_RInterval_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 3, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 0, 6, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 0, 9, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Seconds.secondsIn(new Interval(start,start)).getSeconds());
    }

    public void testFactory_secondsIn_RInterval_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 3, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 0, 6, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 0, 9, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,Seconds.secondsIn(new Interval(end1,end1)).getSeconds());
    }

    public void testFactory_secondsIn_RInterval_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 3, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 9, 12, 0, 6, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 9, 12, 0, 9, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Seconds.secondsIn(new Interval(start,end2)).getSeconds());
    }

    public void testFactory_standardSecondsIn_RPeriod_1_oe() {
        assertEquals(0,Seconds.standardSecondsIn((ReadablePeriod)null).getSeconds());
    }

    public void testFactory_standardSecondsIn_RPeriod_2_oe() {
        // removed other assertion
        assertEquals(0,Seconds.standardSecondsIn(Period.ZERO).getSeconds());
    }

    public void testFactory_standardSecondsIn_RPeriod_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1,Seconds.standardSecondsIn(new Period(0,0,0,0,0,0,1,0)).getSeconds());
    }

    public void testFactory_standardSecondsIn_RPeriod_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(123,Seconds.standardSecondsIn(Period.seconds(123)).getSeconds());
    }

    public void testFactory_standardSecondsIn_RPeriod_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-987,Seconds.standardSecondsIn(Period.seconds(-987)).getSeconds());
    }

    public void testFactory_standardSecondsIn_RPeriod_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2 * 24 * 60 * 60,Seconds.standardSecondsIn(Period.days(2)).getSeconds());
    }

    public void testFactory_parseSeconds_String_1_oe() {
        assertEquals(0,Seconds.parseSeconds((String)null).getSeconds());
    }

    public void testFactory_parseSeconds_String_2_oe() {
        // removed other assertion
        assertEquals(0,Seconds.parseSeconds("PT0S").getSeconds());
    }

    public void testFactory_parseSeconds_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1,Seconds.parseSeconds("PT1S").getSeconds());
    }

    public void testFactory_parseSeconds_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Seconds.parseSeconds("PT-3S").getSeconds());
    }

    public void testFactory_parseSeconds_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,Seconds.parseSeconds("P0Y0M0DT2S").getSeconds());
    }

    public void testFactory_parseSeconds_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,Seconds.parseSeconds("PT0H2S").getSeconds());
    }

    public void testGetMethods_1_oe() {
        Seconds test = Seconds.seconds(20);
        assertEquals(20,test.getSeconds());
    }

    public void testGetFieldType_1_oe() {
        Seconds test = Seconds.seconds(20);
        assertEquals(DurationFieldType.seconds(),test.getFieldType());
    }

    public void testGetPeriodType_1_oe() {
        Seconds test = Seconds.seconds(20);
        assertEquals(PeriodType.seconds(),test.getPeriodType());
    }

    public void testIsGreaterThan_1_oe() {
        assertEquals(true,Seconds.THREE.isGreaterThan(Seconds.TWO));
    }

    public void testIsGreaterThan_2_oe() {
        // removed other assertion
        assertEquals(false,Seconds.THREE.isGreaterThan(Seconds.THREE));
    }

    public void testIsGreaterThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false,Seconds.TWO.isGreaterThan(Seconds.THREE));
    }

    public void testIsGreaterThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Seconds.ONE.isGreaterThan(null));
    }

    public void testIsGreaterThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,Seconds.seconds(-1).isGreaterThan(null));
    }

    public void testIsLessThan_1_oe() {
        assertEquals(false,Seconds.THREE.isLessThan(Seconds.TWO));
    }

    public void testIsLessThan_2_oe() {
        // removed other assertion
        assertEquals(false,Seconds.THREE.isLessThan(Seconds.THREE));
    }

    public void testIsLessThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true,Seconds.TWO.isLessThan(Seconds.THREE));
    }

    public void testIsLessThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,Seconds.ONE.isLessThan(null));
    }

    public void testIsLessThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Seconds.seconds(-1).isLessThan(null));
    }

    public void testToString_1_oe() {
        Seconds test = Seconds.seconds(20);
        assertEquals("PT20S",test.toString());
    }

    public void testToString_2_oe() {
        Seconds test = Seconds.seconds(20);
        // removed other assertion
        
        test = Seconds.seconds(-20);
        assertEquals("PT-20S",test.toString());
    }

    public void testSerialization_1_oe() throws Exception {
        Seconds test = Seconds.THREE;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Seconds result = (Seconds) ois.readObject();
        ois.close();
        
        assertSame(test,result);
    }

    public void testToStandardWeeks_1_oe() {
        Seconds test = Seconds.seconds(60 * 60 * 24 * 7 * 2);
        Weeks expected = Weeks.weeks(2);
        assertEquals(expected,test.toStandardWeeks());
    }

    public void testToStandardDays_1_oe() {
        Seconds test = Seconds.seconds(60 * 60 * 24 * 2);
        Days expected = Days.days(2);
        assertEquals(expected,test.toStandardDays());
    }

    public void testToStandardHours_1_oe() {
        Seconds test = Seconds.seconds(60 * 60 * 2);
        Hours expected = Hours.hours(2);
        assertEquals(expected,test.toStandardHours());
    }

    public void testToStandardMinutes_1_oe() {
        Seconds test = Seconds.seconds(60 * 2);
        Minutes expected = Minutes.minutes(2);
        assertEquals(expected,test.toStandardMinutes());
    }

    public void testToStandardDuration_1_oe() {
        Seconds test = Seconds.seconds(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_SECOND);
        assertEquals(expected,test.toStandardDuration());
    }

    public void testToStandardDuration_2_oe() {
        Seconds test = Seconds.seconds(20);
        Duration expected = new Duration(20L * DateTimeConstants.MILLIS_PER_SECOND);
        // removed other assertion
        
        expected = new Duration(((long) Integer.MAX_VALUE) * DateTimeConstants.MILLIS_PER_SECOND);
        assertEquals(expected,Seconds.MAX_VALUE.toStandardDuration());
    }

    public void testPlus_int_1_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds result = test2.plus(3);
        assertEquals(2,test2.getSeconds());
    }

    public void testPlus_int_2_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds result = test2.plus(3);
        // removed other assertion
        assertEquals(5,result.getSeconds());
    }

    public void testPlus_int_3_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds result = test2.plus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Seconds.ONE.plus(0).getSeconds());
    }

    public void testPlus_Seconds_1_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds test3 = Seconds.seconds(3);
        Seconds result = test2.plus(test3);
        assertEquals(2,test2.getSeconds());
    }

    public void testPlus_Seconds_2_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds test3 = Seconds.seconds(3);
        Seconds result = test2.plus(test3);
        // removed other assertion
        assertEquals(3,test3.getSeconds());
    }

    public void testPlus_Seconds_3_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds test3 = Seconds.seconds(3);
        Seconds result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(5,result.getSeconds());
    }

    public void testPlus_Seconds_4_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds test3 = Seconds.seconds(3);
        Seconds result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Seconds.ONE.plus(Seconds.ZERO).getSeconds());
    }

    public void testPlus_Seconds_5_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds test3 = Seconds.seconds(3);
        Seconds result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1,Seconds.ONE.plus((Seconds)null).getSeconds());
    }

    public void testMinus_int_1_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds result = test2.minus(3);
        assertEquals(2,test2.getSeconds());
    }

    public void testMinus_int_2_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds result = test2.minus(3);
        // removed other assertion
        assertEquals(-1,result.getSeconds());
    }

    public void testMinus_int_3_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds result = test2.minus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Seconds.ONE.minus(0).getSeconds());
    }

    public void testMinus_Seconds_1_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds test3 = Seconds.seconds(3);
        Seconds result = test2.minus(test3);
        assertEquals(2,test2.getSeconds());
    }

    public void testMinus_Seconds_2_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds test3 = Seconds.seconds(3);
        Seconds result = test2.minus(test3);
        // removed other assertion
        assertEquals(3,test3.getSeconds());
    }

    public void testMinus_Seconds_3_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds test3 = Seconds.seconds(3);
        Seconds result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(-1,result.getSeconds());
    }

    public void testMinus_Seconds_4_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds test3 = Seconds.seconds(3);
        Seconds result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Seconds.ONE.minus(Seconds.ZERO).getSeconds());
    }

    public void testMinus_Seconds_5_oe() {
        Seconds test2 = Seconds.seconds(2);
        Seconds test3 = Seconds.seconds(3);
        Seconds result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1,Seconds.ONE.minus((Seconds)null).getSeconds());
    }

    public void testMultipliedBy_int_1_oe() {
        Seconds test = Seconds.seconds(2);
        assertEquals(6,test.multipliedBy(3).getSeconds());
    }

    public void testMultipliedBy_int_2_oe() {
        Seconds test = Seconds.seconds(2);
        // removed other assertion
        assertEquals(2,test.getSeconds());
    }

    public void testMultipliedBy_int_3_oe() {
        Seconds test = Seconds.seconds(2);
        // removed other assertion
        // removed other assertion
        assertEquals(-6,test.multipliedBy(-3).getSeconds());
    }

    public void testMultipliedBy_int_4_oe() {
        Seconds test = Seconds.seconds(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test,test.multipliedBy(1));
    }

    public void testDividedBy_int_1_oe() {
        Seconds test = Seconds.seconds(12);
        assertEquals(6,test.dividedBy(2).getSeconds());
    }

    public void testDividedBy_int_2_oe() {
        Seconds test = Seconds.seconds(12);
        // removed other assertion
        assertEquals(12,test.getSeconds());
    }

    public void testDividedBy_int_3_oe() {
        Seconds test = Seconds.seconds(12);
        // removed other assertion
        // removed other assertion
        assertEquals(4,test.dividedBy(3).getSeconds());
    }

    public void testDividedBy_int_4_oe() {
        Seconds test = Seconds.seconds(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,test.dividedBy(4).getSeconds());
    }

    public void testDividedBy_int_5_oe() {
        Seconds test = Seconds.seconds(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.dividedBy(5).getSeconds());
    }

    public void testDividedBy_int_6_oe() {
        Seconds test = Seconds.seconds(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.dividedBy(6).getSeconds());
    }

    public void testDividedBy_int_7_oe() {
        Seconds test = Seconds.seconds(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test,test.dividedBy(1));
    }

    public void testNegated_1_oe() {
        Seconds test = Seconds.seconds(12);
        assertEquals(-12,test.negated().getSeconds());
    }

    public void testNegated_2_oe() {
        Seconds test = Seconds.seconds(12);
        // removed other assertion
        assertEquals(12,test.getSeconds());
    }

    public void testAddToLocalDate_1_oe() {
        Seconds test = Seconds.seconds(26);
        LocalDateTime date = new LocalDateTime(2006, 6, 1, 0, 0, 0, 0);
        LocalDateTime expected = new LocalDateTime(2006, 6, 1, 0, 0, 26, 0);
        assertEquals(expected,date.plus(test));
    }

}
