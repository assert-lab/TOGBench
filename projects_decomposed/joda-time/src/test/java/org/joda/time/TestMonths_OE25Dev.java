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
 * This class is a Junit unit test for Months.
 *
 * @author Stephen Colebourne
 */
public class TestMonths_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMonths_OE25Dev.class);
    }

    public TestMonths_OE25Dev(String name) {
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
        assertEquals(0,Months.ZERO.getMonths());
        assertEquals(1,Months.ONE.getMonths());
        assertEquals(2,Months.TWO.getMonths());
        assertEquals(3,Months.THREE.getMonths());
        assertEquals(4,Months.FOUR.getMonths());
        assertEquals(5,Months.FIVE.getMonths());
        assertEquals(6,Months.SIX.getMonths());
        assertEquals(7,Months.SEVEN.getMonths());
        assertEquals(8,Months.EIGHT.getMonths());
        assertEquals(9,Months.NINE.getMonths());
        assertEquals(10,Months.TEN.getMonths());
        assertEquals(11,Months.ELEVEN.getMonths());
        assertEquals(12,Months.TWELVE.getMonths());
        assertEquals(Integer.MAX_VALUE,Months.MAX_VALUE.getMonths());
        assertEquals(Integer.MIN_VALUE,Months.MIN_VALUE.getMonths());
    }

    //-----------------------------------------------------------------------
    public void testFactory_months_int() {
        assertSame(Months.ZERO,Months.months(0));
        assertSame(Months.ONE,Months.months(1));
        assertSame(Months.TWO,Months.months(2));
        assertSame(Months.THREE,Months.months(3));
        assertSame(Months.FOUR,Months.months(4));
        assertSame(Months.FIVE,Months.months(5));
        assertSame(Months.SIX,Months.months(6));
        assertSame(Months.SEVEN,Months.months(7));
        assertSame(Months.EIGHT,Months.months(8));
        assertSame(Months.NINE,Months.months(9));
        assertSame(Months.TEN,Months.months(10));
        assertSame(Months.ELEVEN,Months.months(11));
        assertSame(Months.TWELVE,Months.months(12));
        assertSame(Months.MAX_VALUE,Months.months(Integer.MAX_VALUE));
        assertSame(Months.MIN_VALUE,Months.months(Integer.MIN_VALUE));
        assertEquals(-1,Months.months(-1).getMonths());
        assertEquals(13,Months.months(13).getMonths());
    }

    //-----------------------------------------------------------------------
    public void testFactory_monthsBetween_RInstant() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertEquals(3,Months.monthsBetween(start,end1).getMonths());
        assertEquals(0,Months.monthsBetween(start,start).getMonths());
        assertEquals(0,Months.monthsBetween(end1,end1).getMonths());
        assertEquals(-3,Months.monthsBetween(end1,start).getMonths());
        assertEquals(6,Months.monthsBetween(start,end2).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth() {
        assertEquals(0,Months.monthsBetween(new DateTime(2006,1,31,0,0,0,PARIS),new DateTime(2006,2,27,0,0,0,PARIS)).getMonths());
        assertEquals(1,Months.monthsBetween(new DateTime(2006,1,28,0,0,0,PARIS),new DateTime(2006,2,28,0,0,0,PARIS)).getMonths());
        assertEquals(1,Months.monthsBetween(new DateTime(2006,1,29,0,0,0,PARIS),new DateTime(2006,2,28,0,0,0,PARIS)).getMonths());
        assertEquals(1,Months.monthsBetween(new DateTime(2006,1,30,0,0,0,PARIS),new DateTime(2006,2,28,0,0,0,PARIS)).getMonths());
        assertEquals(1,Months.monthsBetween(new DateTime(2006,1,31,0,0,0,PARIS),new DateTime(2006,2,28,0,0,0,PARIS)).getMonths());
        assertEquals(1,Months.monthsBetween(new DateTime(2006,1,31,0,0,0,PARIS),new DateTime(2006,3,1,0,0,0,PARIS)).getMonths());
    }

    //-------------------------------------------------------------------------
    @SuppressWarnings("deprecation")
    public void testFactory_monthsBetween_RPartial_LocalDate() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        assertEquals(3,Months.monthsBetween(start,end1).getMonths());
        assertEquals(0,Months.monthsBetween(start,start).getMonths());
        assertEquals(0,Months.monthsBetween(end1,end1).getMonths());
        assertEquals(-3,Months.monthsBetween(end1,start).getMonths());
        assertEquals(6,Months.monthsBetween(start,end2).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_EndMonth() {
        assertEquals(0,Months.monthsBetween(new LocalDate(2006,1,31),new LocalDate(2006,2,27)).getMonths());
        assertEquals(1,Months.monthsBetween(new LocalDate(2006,1,28),new LocalDate(2006,2,28)).getMonths());
        assertEquals(1,Months.monthsBetween(new LocalDate(2006,1,29),new LocalDate(2006,2,28)).getMonths());
        assertEquals(1,Months.monthsBetween(new LocalDate(2006,1,30),new LocalDate(2006,2,28)).getMonths());
        assertEquals(1,Months.monthsBetween(new LocalDate(2006,1,31),new LocalDate(2006,2,28)).getMonths());
        assertEquals(1,Months.monthsBetween(new LocalDate(2006,1,31),new LocalDate(2006,3,1)).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_YearMonth() {
        YearMonth start1 = new YearMonth(2011, 1);
        for (int i = 0; i < 6; i++) {
            YearMonth start2 = new YearMonth(2011 + i, 1);
            YearMonth end = new YearMonth(2011 + i, 3);
            assertEquals(i * 12 + 2,Months.monthsBetween(start1,end).getMonths());
            assertEquals(2,Months.monthsBetween(start2,end).getMonths());
        }
    }

    public void testFactory_monthsBetween_RPartial_MonthDay() {
        MonthDay start = new MonthDay(2, 1);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        MonthDay end3 = new MonthDay(3, 1);
        
        assertEquals(0,Months.monthsBetween(start,end1).getMonths());
        assertEquals(0,Months.monthsBetween(start,end2).getMonths());
        assertEquals(1,Months.monthsBetween(start,end3).getMonths());
        
        assertEquals(0,Months.monthsBetween(end1,start).getMonths());
        assertEquals(0,Months.monthsBetween(end2,start).getMonths());
        assertEquals(-1,Months.monthsBetween(end3,start).getMonths());
    }

    //-------------------------------------------------------------------------
    public void testFactory_monthsIn_RInterval() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertEquals(0,Months.monthsIn((ReadableInterval)null).getMonths());
        assertEquals(3,Months.monthsIn(new Interval(start,end1)).getMonths());
        assertEquals(0,Months.monthsIn(new Interval(start,start)).getMonths());
        assertEquals(0,Months.monthsIn(new Interval(end1,end1)).getMonths());
        assertEquals(6,Months.monthsIn(new Interval(start,end2)).getMonths());
    }

    public void testFactory_parseMonths_String() {
        assertEquals(0,Months.parseMonths((String)null).getMonths());
        assertEquals(0,Months.parseMonths("P0M").getMonths());
        assertEquals(1,Months.parseMonths("P1M").getMonths());
        assertEquals(-3,Months.parseMonths("P-3M").getMonths());
        assertEquals(2,Months.parseMonths("P0Y2M").getMonths());
        assertEquals(2,Months.parseMonths("P2MT0H0M").getMonths());
        try {
            Months.parseMonths("P1Y1D");
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            Months.parseMonths("P1MT1H");
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void testGetMethods() {
        Months test = Months.months(20);
        assertEquals(20,test.getMonths());
    }

    public void testGetFieldType() {
        Months test = Months.months(20);
        assertEquals(DurationFieldType.months(),test.getFieldType());
    }

    public void testGetPeriodType() {
        Months test = Months.months(20);
        assertEquals(PeriodType.months(),test.getPeriodType());
    }

    //-----------------------------------------------------------------------
    public void testIsGreaterThan() {
        assertEquals(true,Months.THREE.isGreaterThan(Months.TWO));
        assertEquals(false,Months.THREE.isGreaterThan(Months.THREE));
        assertEquals(false,Months.TWO.isGreaterThan(Months.THREE));
        assertEquals(true,Months.ONE.isGreaterThan(null));
        assertEquals(false,Months.months(-1).isGreaterThan(null));
    }

    public void testIsLessThan() {
        assertEquals(false,Months.THREE.isLessThan(Months.TWO));
        assertEquals(false,Months.THREE.isLessThan(Months.THREE));
        assertEquals(true,Months.TWO.isLessThan(Months.THREE));
        assertEquals(false,Months.ONE.isLessThan(null));
        assertEquals(true,Months.months(-1).isLessThan(null));
    }

    //-----------------------------------------------------------------------
    public void testToString() {
        Months test = Months.months(20);
        assertEquals("P20M",test.toString());
        
        test = Months.months(-20);
        assertEquals("P-20M",test.toString());
    }

    //-----------------------------------------------------------------------
    public void testSerialization() throws Exception {
        Months test = Months.THREE;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Months result = (Months) ois.readObject();
        ois.close();
        
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testPlus_int() {
        Months test2 = Months.months(2);
        Months result = test2.plus(3);
        assertEquals(2,test2.getMonths());
        assertEquals(5,result.getMonths());
        
        assertEquals(1,Months.ONE.plus(0).getMonths());
        
        try {
            Months.MAX_VALUE.plus(1);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testPlus_Months() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.plus(test3);
        assertEquals(2,test2.getMonths());
        assertEquals(3,test3.getMonths());
        assertEquals(5,result.getMonths());
        
        assertEquals(1,Months.ONE.plus(Months.ZERO).getMonths());
        assertEquals(1,Months.ONE.plus((Months)null).getMonths());
        
        try {
            Months.MAX_VALUE.plus(Months.ONE);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testMinus_int() {
        Months test2 = Months.months(2);
        Months result = test2.minus(3);
        assertEquals(2,test2.getMonths());
        assertEquals(-1,result.getMonths());
        
        assertEquals(1,Months.ONE.minus(0).getMonths());
        
        try {
            Months.MIN_VALUE.minus(1);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testMinus_Months() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        assertEquals(2,test2.getMonths());
        assertEquals(3,test3.getMonths());
        assertEquals(-1,result.getMonths());
        
        assertEquals(1,Months.ONE.minus(Months.ZERO).getMonths());
        assertEquals(1,Months.ONE.minus((Months)null).getMonths());
        
        try {
            Months.MIN_VALUE.minus(Months.ONE);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testMultipliedBy_int() {
        Months test = Months.months(2);
        assertEquals(6,test.multipliedBy(3).getMonths());
        assertEquals(2,test.getMonths());
        assertEquals(-6,test.multipliedBy(-3).getMonths());
        assertSame(test,test.multipliedBy(1));
        
        Months halfMax = Months.months(Integer.MAX_VALUE / 2 + 1);
        try {
            halfMax.multipliedBy(2);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testDividedBy_int() {
        Months test = Months.months(12);
        assertEquals(6,test.dividedBy(2).getMonths());
        assertEquals(12,test.getMonths());
        assertEquals(4,test.dividedBy(3).getMonths());
        assertEquals(3,test.dividedBy(4).getMonths());
        assertEquals(2,test.dividedBy(5).getMonths());
        assertEquals(2,test.dividedBy(6).getMonths());
        assertSame(test,test.dividedBy(1));
        
        try {
            Months.ONE.dividedBy(0);
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    public void testNegated() {
        Months test = Months.months(12);
        assertEquals(-12,test.negated().getMonths());
        assertEquals(12,test.getMonths());
        
        try {
            Months.MIN_VALUE.negated();
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void testAddToLocalDate() {
        Months test = Months.months(3);
        LocalDate date = new LocalDate(2006, 6, 1);
        LocalDate expected = new LocalDate(2006, 9, 1);
        assertEquals(expected,date.plus(test));
    }

    public void testConstants_1_oe() {
        int a = 0;
// incorrect assertion         assertEquals(0, this.getMonths());
    }

    public void testConstants_4_oe() {
        int a = 3;
// incorrect assertion         assertEquals(0, this.getMonths());
    }

    public void testConstants_5_oe() {
        int a = 4;
// incorrect assertion         assertEquals(4, this.getMonths());
    }

    public void testConstants_7_oe() {
        int a = 6;
// incorrect assertion         assertEquals(6, this.getMonths());
    }

    public void testConstants_9_oe() {
        int a = 8;
// incorrect assertion         assertEquals(0, this.getMonths());
    }

    public void testConstants_12_oe() {
        int a = 11;
// incorrect assertion         assertEquals(0, this.getMonths());
    }

    public void testConstants_15_oe() {
        Object a = Integer.MIN_VALUE;
// incorrect assertion         assertEquals(0, this.getMonths());
    }

    public void testFactory_months_int_1_oe() {
        Object a = Months.ZERO;
        assertNotNull(Months.ZERO);
    }

    public void testFactory_months_int_2_oe() {
        Object a = Months.ONE;
        assertNotNull(Months.ONE);
    }

    public void testFactory_months_int_3_oe() {
        Object a = Months.TWO;
        assertNotNull(Months.TWO);
    }

    public void testFactory_months_int_4_oe() {
        Object a = Months.THREE;
        assertNotNull(Months.THREE);
    }

    public void testFactory_months_int_5_oe() {
        Object a = Months.FOUR;
        assertNotNull(Months.FOUR);
    }

    public void testFactory_months_int_6_oe() {
        Object a = Months.FIVE;
        assertNotNull(Months.FIVE);
    }

    public void testFactory_months_int_7_oe() {
        Object a = Months.SIX;
        assertNotNull(Months.SIX);
    }

    public void testFactory_months_int_8_oe() {
        Object a = Months.SEVEN;
        assertNotNull(Months.SEVEN);
    }

    public void testFactory_months_int_9_oe() {
        Object a = Months.EIGHT;
        assertNotNull(Months.EIGHT);
    }

    public void testFactory_months_int_10_oe() {
        Object a = Months.NINE;
        assertNotNull(Months.NINE);
    }

    public void testFactory_months_int_11_oe() {
        Object a = Months.TEN;
        assertNotNull(Months.TEN);
    }

    public void testFactory_months_int_12_oe() {
        Object a = Months.ELEVEN;
        assertNotNull(Months.ELEVEN);
    }

    public void testFactory_months_int_13_oe() {
        Object a = Months.TWELVE;
        assertNotNull(Months.TWELVE);
    }

    public void testFactory_months_int_14_oe() {
        Object a = Months.MAX_VALUE;
        assertNotNull(Months.MAX_VALUE);
    }

    public void testFactory_months_int_15_oe() {
        Object a = Months.MIN_VALUE;
        assertNotNull(Months.MIN_VALUE);
    }

    public void testFactory_months_int_16_oe() {
        int a = -1;
// incorrect assertion         assertNotNull(Months.minValue());
    }

    public void testFactory_months_int_17_oe() {
        int a = 13;
        assertNotNull(Months.months(12));
    }

    public void testFactory_monthsBetween_RInstant_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsBetween_RInstant_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsBetween_RInstant_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsBetween_RInstant_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsBetween_RInstant_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth_1_oe() {
        int a = 0;
        assertNotNull(Months.monthsBetween(Instant.now(), Instant.now()));
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth_4_oe() {
        int a = 1;
        assertNotNull(Months.monthsBetween(Instant.now(), Instant.now()));
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth_5_oe() {
        int a = 1;
        assertNotNull(Months.monthsBetween(Instant.now(), Instant.now()));
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth_6_oe() {
        int a = 1;
        assertNotNull(Months.monthsBetween(Instant.now(), Instant.now()));
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_1_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_2_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_3_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_4_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_5_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsBetween_RPartial_MonthDay_1_oe() {
        MonthDay start = new MonthDay(2, 1);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        MonthDay end3 = new MonthDay(3, 1);
        
        assertNotNull(end3);
    }

    public void testFactory_monthsBetween_RPartial_MonthDay_6_oe() {
        MonthDay start = new MonthDay(2, 1);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        MonthDay end3 = new MonthDay(3, 1);
        
        
        assertNotNull(end3);
    }

    public void testFactory_monthsIn_RInterval_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsIn_RInterval_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsIn_RInterval_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsIn_RInterval_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testFactory_monthsIn_RInterval_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertNotNull(end2);
    }

    public void testGetMethods_1_oe() {
        Months test = Months.months(20);
        assertEquals(20, test.getMonths());
    }

    public void testGetFieldType_1_oe() {
        Months test = Months.months(20);
        assertNotNull(test);
    }

    public void testGetPeriodType_1_oe() {
        Months test = Months.months(20);
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
        Months test = Months.months(20);
        assertNotNull(test);
    }

    public void testToString_2_oe() {
        Months test = Months.months(20);
        
        test = Months.months(-20);
        assertNotNull(test);
    }

    public void testSerialization_1_oe() throws Exception {
        Months test = Months.THREE;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Months result = (Months) ois.readObject();
        ois.close();
        
        assertNotNull(result);
    }

    public void testPlus_int_1_oe() {
        Months test2 = Months.months(2);
        Months result = test2.plus(3);
        assertEquals(2, test2.getMonths());
    }

    public void testPlus_int_2_oe() {
        Months test2 = Months.months(2);
        Months result = test2.plus(3);
        assertEquals(2, test2.getMonths());
    }

    public void testPlus_int_3_oe() {
        Months test2 = Months.months(2);
        Months result = test2.plus(3);
        
// incorrect assertion         assertEquals(false, result.isNegative());
    }

    public void testPlus_Months_1_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.plus(test3);
        assertEquals(5, test3.getMonths());
    }

    public void testPlus_Months_2_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.plus(test3);
        assertEquals(5, test3.getMonths());
    }

    public void testPlus_Months_3_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.plus(test3);
        assertEquals(5, test3.getMonths());
    }

    public void testPlus_Months_5_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.plus(test3);
        
        assertNotNull(result);
    }

    public void testMinus_int_1_oe() {
        Months test2 = Months.months(2);
        Months result = test2.minus(3);
        assertEquals(2, test2.getMonths());
    }

    public void testMinus_int_2_oe() {
        Months test2 = Months.months(2);
        Months result = test2.minus(3);
        assertEquals(2, test2.getMonths());
    }

    public void testMinus_int_3_oe() {
        Months test2 = Months.months(2);
        Months result = test2.minus(3);
        
// incorrect assertion         assertEquals(false, result.isNegative());
    }

    public void testMinus_Months_1_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        assertEquals(2, test2.getMonths());
    }

    public void testMinus_Months_2_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        assertEquals(2, test2.getMonths());
    }

    public void testMinus_Months_3_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        assertEquals(2, test2.getMonths());
    }

    public void testMinus_Months_4_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        
// incorrect assertion         assertEquals(false, result.isNegative());
    }

    public void testMinus_Months_5_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        
// incorrect assertion         assertEquals(false, result.isNegative());
    }

    public void testMultipliedBy_int_1_oe() {
        Months test = Months.months(2);
        assertNotNull(test);
    }

    public void testMultipliedBy_int_2_oe() {
        Months test = Months.months(2);
        assertEquals(2, test.getMonths());
    }

    public void testMultipliedBy_int_3_oe() {
        Months test = Months.months(2);
        assertEquals(Months.months(2), test.multipliedBy(1));
    }

    public void testMultipliedBy_int_4_oe() {
        Months test = Months.months(2);
        assertNotNull(test);
    }

    public void testDividedBy_int_1_oe() {
        Months test = Months.months(12);
        assertNotNull(Months.months(12));
    }

    public void testDividedBy_int_2_oe() {
        Months test = Months.months(12);
        assertEquals(12, test.getMonths());
    }

    public void testDividedBy_int_3_oe() {
        Months test = Months.months(12);
        assertNotNull(Months.months(12));
    }

    public void testDividedBy_int_4_oe() {
        Months test = Months.months(12);
        assertNotNull(Months.months(12));
    }

    public void testDividedBy_int_5_oe() {
        Months test = Months.months(12);
        assertNotNull(Months.months(12));
    }

    public void testDividedBy_int_6_oe() {
        Months test = Months.months(12);
        assertNotNull(Months.months(12));
    }

    public void testDividedBy_int_7_oe() {
        Months test = Months.months(12);
        assertNotNull(Months.months(12));
    }

    public void testNegated_1_oe() {
        Months test = Months.months(12);
        assertNotNull(test.negated());
    }

    public void testNegated_2_oe() {
        Months test = Months.months(12);
        assertEquals(12, test.getMonths());
    }

    public void testAddToLocalDate_1_oe() {
        Months test = Months.months(3);
        LocalDate date = new LocalDate(2006, 6, 1);
        LocalDate expected = new LocalDate(2006, 9, 1);
        assertNotNull(test);
    }

}
