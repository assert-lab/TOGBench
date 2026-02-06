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
        return new TestSuite(TestMonths_OE25Dev_OE25Dev.class);
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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testConstants_1_oe() {
        assertEquals(0,Months.ZERO.getMonths());
    }

    public void testConstants_2_oe() {
        // removed other assertion
        assertEquals(1,Months.ONE.getMonths());
    }

    public void testConstants_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2,Months.TWO.getMonths());
    }

    public void testConstants_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,Months.THREE.getMonths());
    }

    public void testConstants_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4,Months.FOUR.getMonths());
    }

    public void testConstants_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5,Months.FIVE.getMonths());
    }

    public void testConstants_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Months.SIX.getMonths());
    }

    public void testConstants_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7,Months.SEVEN.getMonths());
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
        assertEquals(8,Months.EIGHT.getMonths());
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
        assertEquals(9,Months.NINE.getMonths());
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
        assertEquals(10,Months.TEN.getMonths());
    }

    public void testConstants_12_oe() {
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
        assertEquals(11,Months.ELEVEN.getMonths());
    }

    public void testConstants_13_oe() {
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
        assertEquals(12,Months.TWELVE.getMonths());
    }

    public void testConstants_14_oe() {
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
        // removed other assertion
        assertEquals(Integer.MAX_VALUE,Months.MAX_VALUE.getMonths());
    }

    public void testConstants_15_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE,Months.MIN_VALUE.getMonths());
    }

    public void testFactory_months_int_1_oe() {
        assertSame(Months.ZERO,Months.months(0));
    }

    public void testFactory_months_int_2_oe() {
        // removed other assertion
        assertSame(Months.ONE,Months.months(1));
    }

    public void testFactory_months_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertSame(Months.TWO,Months.months(2));
    }

    public void testFactory_months_int_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Months.THREE,Months.months(3));
    }

    public void testFactory_months_int_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Months.FOUR,Months.months(4));
    }

    public void testFactory_months_int_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Months.FIVE,Months.months(5));
    }

    public void testFactory_months_int_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Months.SIX,Months.months(6));
    }

    public void testFactory_months_int_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Months.SEVEN,Months.months(7));
    }

    public void testFactory_months_int_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Months.EIGHT,Months.months(8));
    }

    public void testFactory_months_int_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Months.NINE,Months.months(9));
    }

    public void testFactory_months_int_11_oe() {
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
        assertSame(Months.TEN,Months.months(10));
    }

    public void testFactory_months_int_12_oe() {
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
        assertSame(Months.ELEVEN,Months.months(11));
    }

    public void testFactory_months_int_13_oe() {
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
        assertSame(Months.TWELVE,Months.months(12));
    }

    public void testFactory_months_int_14_oe() {
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
        // removed other assertion
        assertSame(Months.MAX_VALUE,Months.months(Integer.MAX_VALUE));
    }

    public void testFactory_months_int_15_oe() {
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
        // removed other assertion
        // removed other assertion
        assertSame(Months.MIN_VALUE,Months.months(Integer.MIN_VALUE));
    }

    public void testFactory_months_int_16_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1,Months.months(-1).getMonths());
    }

    public void testFactory_months_int_17_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(13,Months.months(13).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertEquals(3,Months.monthsBetween(start,end1).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        assertEquals(0,Months.monthsBetween(start,start).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Months.monthsBetween(end1,end1).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Months.monthsBetween(end1,start).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Months.monthsBetween(start,end2).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth_1_oe() {
        assertEquals(0,Months.monthsBetween(new DateTime(2006,1,31,0,0,0,PARIS),new DateTime(2006,2,27,0,0,0,PARIS)).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth_2_oe() {
        // removed other assertion
        assertEquals(1,Months.monthsBetween(new DateTime(2006,1,28,0,0,0,PARIS),new DateTime(2006,2,28,0,0,0,PARIS)).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1,Months.monthsBetween(new DateTime(2006,1,29,0,0,0,PARIS),new DateTime(2006,2,28,0,0,0,PARIS)).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,Months.monthsBetween(new DateTime(2006,1,30,0,0,0,PARIS),new DateTime(2006,2,28,0,0,0,PARIS)).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,Months.monthsBetween(new DateTime(2006,1,31,0,0,0,PARIS),new DateTime(2006,2,28,0,0,0,PARIS)).getMonths());
    }

    public void testFactory_monthsBetween_RInstant_LocalDate_EndMonth_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,Months.monthsBetween(new DateTime(2006,1,31,0,0,0,PARIS),new DateTime(2006,3,1,0,0,0,PARIS)).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_1_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        assertEquals(3,Months.monthsBetween(start,end1).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_2_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        // removed other assertion
        assertEquals(0,Months.monthsBetween(start,start).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_3_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Months.monthsBetween(end1,end1).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_4_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Months.monthsBetween(end1,start).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_5_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 9, 9);
        YearMonthDay end2 = new YearMonthDay(2006, 12, 9);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Months.monthsBetween(start,end2).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_EndMonth_1_oe() {
        assertEquals(0,Months.monthsBetween(new LocalDate(2006,1,31),new LocalDate(2006,2,27)).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_EndMonth_2_oe() {
        // removed other assertion
        assertEquals(1,Months.monthsBetween(new LocalDate(2006,1,28),new LocalDate(2006,2,28)).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_EndMonth_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1,Months.monthsBetween(new LocalDate(2006,1,29),new LocalDate(2006,2,28)).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_EndMonth_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,Months.monthsBetween(new LocalDate(2006,1,30),new LocalDate(2006,2,28)).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_EndMonth_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,Months.monthsBetween(new LocalDate(2006,1,31),new LocalDate(2006,2,28)).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_LocalDate_EndMonth_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,Months.monthsBetween(new LocalDate(2006,1,31),new LocalDate(2006,3,1)).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_YearMonth_1_oe() {
        YearMonth start1 = new YearMonth(2011, 1);
        for (int i = 0; i < 6; i++) {
            YearMonth start2 = new YearMonth(2011 + i, 1);
            YearMonth end = new YearMonth(2011 + i, 3);
            assertEquals(i * 12 + 2,Months.monthsBetween(start1,end).getMonths());
    }
    }

    public void testFactory_monthsBetween_RPartial_YearMonth_2_oe() {
        YearMonth start1 = new YearMonth(2011, 1);
        for (int i = 0; i < 6; i++) {
            YearMonth start2 = new YearMonth(2011 + i, 1);
            YearMonth end = new YearMonth(2011 + i, 3);
            // removed other assertion
            assertEquals(2,Months.monthsBetween(start2,end).getMonths());
    }
    }

    public void testFactory_monthsBetween_RPartial_MonthDay_1_oe() {
        MonthDay start = new MonthDay(2, 1);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        MonthDay end3 = new MonthDay(3, 1);
        
        assertEquals(0,Months.monthsBetween(start,end1).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_MonthDay_2_oe() {
        MonthDay start = new MonthDay(2, 1);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        MonthDay end3 = new MonthDay(3, 1);
        
        // removed other assertion
        assertEquals(0,Months.monthsBetween(start,end2).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_MonthDay_3_oe() {
        MonthDay start = new MonthDay(2, 1);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        MonthDay end3 = new MonthDay(3, 1);
        
        // removed other assertion
        // removed other assertion
        assertEquals(1,Months.monthsBetween(start,end3).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_MonthDay_4_oe() {
        MonthDay start = new MonthDay(2, 1);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        MonthDay end3 = new MonthDay(3, 1);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(0,Months.monthsBetween(end1,start).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_MonthDay_5_oe() {
        MonthDay start = new MonthDay(2, 1);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        MonthDay end3 = new MonthDay(3, 1);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(0,Months.monthsBetween(end2,start).getMonths());
    }

    public void testFactory_monthsBetween_RPartial_MonthDay_6_oe() {
        MonthDay start = new MonthDay(2, 1);
        MonthDay end1 = new MonthDay(2, 28);
        MonthDay end2 = new MonthDay(2, 29);
        MonthDay end3 = new MonthDay(3, 1);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(-1,Months.monthsBetween(end3,start).getMonths());
    }

    public void testFactory_monthsIn_RInterval_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        assertEquals(0,Months.monthsIn((ReadableInterval)null).getMonths());
    }

    public void testFactory_monthsIn_RInterval_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        assertEquals(3,Months.monthsIn(new Interval(start,end1)).getMonths());
    }

    public void testFactory_monthsIn_RInterval_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Months.monthsIn(new Interval(start,start)).getMonths());
    }

    public void testFactory_monthsIn_RInterval_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,Months.monthsIn(new Interval(end1,end1)).getMonths());
    }

    public void testFactory_monthsIn_RInterval_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 9, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 12, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Months.monthsIn(new Interval(start,end2)).getMonths());
    }

    public void testFactory_parseMonths_String_1_oe() {
        assertEquals(0,Months.parseMonths((String)null).getMonths());
    }

    public void testFactory_parseMonths_String_2_oe() {
        // removed other assertion
        assertEquals(0,Months.parseMonths("P0M").getMonths());
    }

    public void testFactory_parseMonths_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1,Months.parseMonths("P1M").getMonths());
    }

    public void testFactory_parseMonths_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Months.parseMonths("P-3M").getMonths());
    }

    public void testFactory_parseMonths_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,Months.parseMonths("P0Y2M").getMonths());
    }

    public void testFactory_parseMonths_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,Months.parseMonths("P2MT0H0M").getMonths());
    }

    public void testGetMethods_1_oe() {
        Months test = Months.months(20);
        assertEquals(20,test.getMonths());
    }

    public void testGetFieldType_1_oe() {
        Months test = Months.months(20);
        assertEquals(DurationFieldType.months(),test.getFieldType());
    }

    public void testGetPeriodType_1_oe() {
        Months test = Months.months(20);
        assertEquals(PeriodType.months(),test.getPeriodType());
    }

    public void testIsGreaterThan_1_oe() {
        assertEquals(true,Months.THREE.isGreaterThan(Months.TWO));
    }

    public void testIsGreaterThan_2_oe() {
        // removed other assertion
        assertEquals(false,Months.THREE.isGreaterThan(Months.THREE));
    }

    public void testIsGreaterThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false,Months.TWO.isGreaterThan(Months.THREE));
    }

    public void testIsGreaterThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Months.ONE.isGreaterThan(null));
    }

    public void testIsGreaterThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,Months.months(-1).isGreaterThan(null));
    }

    public void testIsLessThan_1_oe() {
        assertEquals(false,Months.THREE.isLessThan(Months.TWO));
    }

    public void testIsLessThan_2_oe() {
        // removed other assertion
        assertEquals(false,Months.THREE.isLessThan(Months.THREE));
    }

    public void testIsLessThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true,Months.TWO.isLessThan(Months.THREE));
    }

    public void testIsLessThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,Months.ONE.isLessThan(null));
    }

    public void testIsLessThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Months.months(-1).isLessThan(null));
    }

    public void testToString_1_oe() {
        Months test = Months.months(20);
        assertEquals("P20M",test.toString());
    }

    public void testToString_2_oe() {
        Months test = Months.months(20);
        // removed other assertion
        
        test = Months.months(-20);
        assertEquals("P-20M",test.toString());
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
        
        assertSame(test,result);
    }

    public void testPlus_int_1_oe() {
        Months test2 = Months.months(2);
        Months result = test2.plus(3);
        assertEquals(2,test2.getMonths());
    }

    public void testPlus_int_2_oe() {
        Months test2 = Months.months(2);
        Months result = test2.plus(3);
        // removed other assertion
        assertEquals(5,result.getMonths());
    }

    public void testPlus_int_3_oe() {
        Months test2 = Months.months(2);
        Months result = test2.plus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Months.ONE.plus(0).getMonths());
    }

    public void testPlus_Months_1_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.plus(test3);
        assertEquals(2,test2.getMonths());
    }

    public void testPlus_Months_2_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.plus(test3);
        // removed other assertion
        assertEquals(3,test3.getMonths());
    }

    public void testPlus_Months_3_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(5,result.getMonths());
    }

    public void testPlus_Months_4_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Months.ONE.plus(Months.ZERO).getMonths());
    }

    public void testPlus_Months_5_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1,Months.ONE.plus((Months)null).getMonths());
    }

    public void testMinus_int_1_oe() {
        Months test2 = Months.months(2);
        Months result = test2.minus(3);
        assertEquals(2,test2.getMonths());
    }

    public void testMinus_int_2_oe() {
        Months test2 = Months.months(2);
        Months result = test2.minus(3);
        // removed other assertion
        assertEquals(-1,result.getMonths());
    }

    public void testMinus_int_3_oe() {
        Months test2 = Months.months(2);
        Months result = test2.minus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Months.ONE.minus(0).getMonths());
    }

    public void testMinus_Months_1_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        assertEquals(2,test2.getMonths());
    }

    public void testMinus_Months_2_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        // removed other assertion
        assertEquals(3,test3.getMonths());
    }

    public void testMinus_Months_3_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(-1,result.getMonths());
    }

    public void testMinus_Months_4_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Months.ONE.minus(Months.ZERO).getMonths());
    }

    public void testMinus_Months_5_oe() {
        Months test2 = Months.months(2);
        Months test3 = Months.months(3);
        Months result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1,Months.ONE.minus((Months)null).getMonths());
    }

    public void testMultipliedBy_int_1_oe() {
        Months test = Months.months(2);
        assertEquals(6,test.multipliedBy(3).getMonths());
    }

    public void testMultipliedBy_int_2_oe() {
        Months test = Months.months(2);
        // removed other assertion
        assertEquals(2,test.getMonths());
    }

    public void testMultipliedBy_int_3_oe() {
        Months test = Months.months(2);
        // removed other assertion
        // removed other assertion
        assertEquals(-6,test.multipliedBy(-3).getMonths());
    }

    public void testMultipliedBy_int_4_oe() {
        Months test = Months.months(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test,test.multipliedBy(1));
    }

    public void testDividedBy_int_1_oe() {
        Months test = Months.months(12);
        assertEquals(6,test.dividedBy(2).getMonths());
    }

    public void testDividedBy_int_2_oe() {
        Months test = Months.months(12);
        // removed other assertion
        assertEquals(12,test.getMonths());
    }

    public void testDividedBy_int_3_oe() {
        Months test = Months.months(12);
        // removed other assertion
        // removed other assertion
        assertEquals(4,test.dividedBy(3).getMonths());
    }

    public void testDividedBy_int_4_oe() {
        Months test = Months.months(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,test.dividedBy(4).getMonths());
    }

    public void testDividedBy_int_5_oe() {
        Months test = Months.months(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.dividedBy(5).getMonths());
    }

    public void testDividedBy_int_6_oe() {
        Months test = Months.months(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.dividedBy(6).getMonths());
    }

    public void testDividedBy_int_7_oe() {
        Months test = Months.months(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test,test.dividedBy(1));
    }

    public void testNegated_1_oe() {
        Months test = Months.months(12);
        assertEquals(-12,test.negated().getMonths());
    }

    public void testNegated_2_oe() {
        Months test = Months.months(12);
        // removed other assertion
        assertEquals(12,test.getMonths());
    }

    public void testAddToLocalDate_1_oe() {
        Months test = Months.months(3);
        LocalDate date = new LocalDate(2006, 6, 1);
        LocalDate expected = new LocalDate(2006, 9, 1);
        assertEquals(expected,date.plus(test));
    }

}
