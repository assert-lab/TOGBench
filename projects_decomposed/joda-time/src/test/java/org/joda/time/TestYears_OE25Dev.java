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
 * This class is a Junit unit test for Years.
 *
 * @author Stephen Colebourne
 */
public class TestYears_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestYears_OE25Dev_OE25Dev.class);
    }

    public TestYears_OE25Dev(String name) {
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

    public void testConstants_1_oe() {
        assertEquals(0,Years.ZERO.getYears());
    }

    public void testConstants_2_oe() {
        // removed other assertion
        assertEquals(1,Years.ONE.getYears());
    }

    public void testConstants_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2,Years.TWO.getYears());
    }

    public void testConstants_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,Years.THREE.getYears());
    }

    public void testConstants_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MAX_VALUE,Years.MAX_VALUE.getYears());
    }

    public void testConstants_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE,Years.MIN_VALUE.getYears());
    }

    public void testFactory_years_int_1_oe() {
        assertSame(Years.ZERO,Years.years(0));
    }

    public void testFactory_years_int_2_oe() {
        // removed other assertion
        assertSame(Years.ONE,Years.years(1));
    }

    public void testFactory_years_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertSame(Years.TWO,Years.years(2));
    }

    public void testFactory_years_int_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Years.THREE,Years.years(3));
    }

    public void testFactory_years_int_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Years.MAX_VALUE,Years.years(Integer.MAX_VALUE));
    }

    public void testFactory_years_int_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(Years.MIN_VALUE,Years.years(Integer.MIN_VALUE));
    }

    public void testFactory_years_int_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1,Years.years(-1).getYears());
    }

    public void testFactory_years_int_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4,Years.years(4).getYears());
    }

    public void testFactory_yearsBetween_RInstant_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2009, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2012, 6, 9, 12, 0, 0, 0, PARIS);
        
        assertEquals(3,Years.yearsBetween(start,end1).getYears());
    }

    public void testFactory_yearsBetween_RInstant_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2009, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2012, 6, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        assertEquals(0,Years.yearsBetween(start,start).getYears());
    }

    public void testFactory_yearsBetween_RInstant_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2009, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2012, 6, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Years.yearsBetween(end1,end1).getYears());
    }

    public void testFactory_yearsBetween_RInstant_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2009, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2012, 6, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Years.yearsBetween(end1,start).getYears());
    }

    public void testFactory_yearsBetween_RInstant_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2009, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2012, 6, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Years.yearsBetween(start,end2).getYears());
    }

    public void testFactory_yearsBetween_RPartial_1_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2009, 6, 9);
        YearMonthDay end2 = new YearMonthDay(2012, 6, 9);
        
        assertEquals(3,Years.yearsBetween(start,end1).getYears());
    }

    public void testFactory_yearsBetween_RPartial_2_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2009, 6, 9);
        YearMonthDay end2 = new YearMonthDay(2012, 6, 9);
        
        // removed other assertion
        assertEquals(0,Years.yearsBetween(start,start).getYears());
    }

    public void testFactory_yearsBetween_RPartial_3_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2009, 6, 9);
        YearMonthDay end2 = new YearMonthDay(2012, 6, 9);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Years.yearsBetween(end1,end1).getYears());
    }

    public void testFactory_yearsBetween_RPartial_4_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2009, 6, 9);
        YearMonthDay end2 = new YearMonthDay(2012, 6, 9);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Years.yearsBetween(end1,start).getYears());
    }

    public void testFactory_yearsBetween_RPartial_5_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2009, 6, 9);
        YearMonthDay end2 = new YearMonthDay(2012, 6, 9);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Years.yearsBetween(start,end2).getYears());
    }

    public void testFactory_yearsIn_RInterval_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2009, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2012, 6, 9, 12, 0, 0, 0, PARIS);
        
        assertEquals(0,Years.yearsIn((ReadableInterval)null).getYears());
    }

    public void testFactory_yearsIn_RInterval_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2009, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2012, 6, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        assertEquals(3,Years.yearsIn(new Interval(start,end1)).getYears());
    }

    public void testFactory_yearsIn_RInterval_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2009, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2012, 6, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        assertEquals(0,Years.yearsIn(new Interval(start,start)).getYears());
    }

    public void testFactory_yearsIn_RInterval_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2009, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2012, 6, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,Years.yearsIn(new Interval(end1,end1)).getYears());
    }

    public void testFactory_yearsIn_RInterval_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2009, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2012, 6, 9, 12, 0, 0, 0, PARIS);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,Years.yearsIn(new Interval(start,end2)).getYears());
    }

    public void testFactory_parseYears_String_1_oe() {
        assertEquals(0,Years.parseYears((String)null).getYears());
    }

    public void testFactory_parseYears_String_2_oe() {
        // removed other assertion
        assertEquals(0,Years.parseYears("P0Y").getYears());
    }

    public void testFactory_parseYears_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1,Years.parseYears("P1Y").getYears());
    }

    public void testFactory_parseYears_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-3,Years.parseYears("P-3Y").getYears());
    }

    public void testFactory_parseYears_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,Years.parseYears("P2Y0M").getYears());
    }

    public void testFactory_parseYears_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,Years.parseYears("P2YT0H0M").getYears());
    }

    public void testGetMethods_1_oe() {
        Years test = Years.years(20);
        assertEquals(20,test.getYears());
    }

    public void testGetFieldType_1_oe() {
        Years test = Years.years(20);
        assertEquals(DurationFieldType.years(),test.getFieldType());
    }

    public void testGetPeriodType_1_oe() {
        Years test = Years.years(20);
        assertEquals(PeriodType.years(),test.getPeriodType());
    }

    public void testIsGreaterThan_1_oe() {
        assertEquals(true,Years.THREE.isGreaterThan(Years.TWO));
    }

    public void testIsGreaterThan_2_oe() {
        // removed other assertion
        assertEquals(false,Years.THREE.isGreaterThan(Years.THREE));
    }

    public void testIsGreaterThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(false,Years.TWO.isGreaterThan(Years.THREE));
    }

    public void testIsGreaterThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Years.ONE.isGreaterThan(null));
    }

    public void testIsGreaterThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,Years.years(-1).isGreaterThan(null));
    }

    public void testIsLessThan_1_oe() {
        assertEquals(false,Years.THREE.isLessThan(Years.TWO));
    }

    public void testIsLessThan_2_oe() {
        // removed other assertion
        assertEquals(false,Years.THREE.isLessThan(Years.THREE));
    }

    public void testIsLessThan_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(true,Years.TWO.isLessThan(Years.THREE));
    }

    public void testIsLessThan_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,Years.ONE.isLessThan(null));
    }

    public void testIsLessThan_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Years.years(-1).isLessThan(null));
    }

    public void testToString_1_oe() {
        Years test = Years.years(20);
        assertEquals("P20Y",test.toString());
    }

    public void testToString_2_oe() {
        Years test = Years.years(20);
        // removed other assertion
        
        test = Years.years(-20);
        assertEquals("P-20Y",test.toString());
    }

    public void testSerialization_1_oe() throws Exception {
        Years test = Years.THREE;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Years result = (Years) ois.readObject();
        ois.close();
        
        assertSame(test,result);
    }

    public void testPlus_int_1_oe() {
        Years test2 = Years.years(2);
        Years result = test2.plus(3);
        assertEquals(2,test2.getYears());
    }

    public void testPlus_int_2_oe() {
        Years test2 = Years.years(2);
        Years result = test2.plus(3);
        // removed other assertion
        assertEquals(5,result.getYears());
    }

    public void testPlus_int_3_oe() {
        Years test2 = Years.years(2);
        Years result = test2.plus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Years.ONE.plus(0).getYears());
    }

    public void testPlus_Years_1_oe() {
        Years test2 = Years.years(2);
        Years test3 = Years.years(3);
        Years result = test2.plus(test3);
        assertEquals(2,test2.getYears());
    }

    public void testPlus_Years_2_oe() {
        Years test2 = Years.years(2);
        Years test3 = Years.years(3);
        Years result = test2.plus(test3);
        // removed other assertion
        assertEquals(3,test3.getYears());
    }

    public void testPlus_Years_3_oe() {
        Years test2 = Years.years(2);
        Years test3 = Years.years(3);
        Years result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(5,result.getYears());
    }

    public void testPlus_Years_4_oe() {
        Years test2 = Years.years(2);
        Years test3 = Years.years(3);
        Years result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Years.ONE.plus(Years.ZERO).getYears());
    }

    public void testPlus_Years_5_oe() {
        Years test2 = Years.years(2);
        Years test3 = Years.years(3);
        Years result = test2.plus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1,Years.ONE.plus((Years)null).getYears());
    }

    public void testMinus_int_1_oe() {
        Years test2 = Years.years(2);
        Years result = test2.minus(3);
        assertEquals(2,test2.getYears());
    }

    public void testMinus_int_2_oe() {
        Years test2 = Years.years(2);
        Years result = test2.minus(3);
        // removed other assertion
        assertEquals(-1,result.getYears());
    }

    public void testMinus_int_3_oe() {
        Years test2 = Years.years(2);
        Years result = test2.minus(3);
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Years.ONE.minus(0).getYears());
    }

    public void testMinus_Years_1_oe() {
        Years test2 = Years.years(2);
        Years test3 = Years.years(3);
        Years result = test2.minus(test3);
        assertEquals(2,test2.getYears());
    }

    public void testMinus_Years_2_oe() {
        Years test2 = Years.years(2);
        Years test3 = Years.years(3);
        Years result = test2.minus(test3);
        // removed other assertion
        assertEquals(3,test3.getYears());
    }

    public void testMinus_Years_3_oe() {
        Years test2 = Years.years(2);
        Years test3 = Years.years(3);
        Years result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        assertEquals(-1,result.getYears());
    }

    public void testMinus_Years_4_oe() {
        Years test2 = Years.years(2);
        Years test3 = Years.years(3);
        Years result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,Years.ONE.minus(Years.ZERO).getYears());
    }

    public void testMinus_Years_5_oe() {
        Years test2 = Years.years(2);
        Years test3 = Years.years(3);
        Years result = test2.minus(test3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1,Years.ONE.minus((Years)null).getYears());
    }

    public void testMultipliedBy_int_1_oe() {
        Years test = Years.years(2);
        assertEquals(6,test.multipliedBy(3).getYears());
    }

    public void testMultipliedBy_int_2_oe() {
        Years test = Years.years(2);
        // removed other assertion
        assertEquals(2,test.getYears());
    }

    public void testMultipliedBy_int_3_oe() {
        Years test = Years.years(2);
        // removed other assertion
        // removed other assertion
        assertEquals(-6,test.multipliedBy(-3).getYears());
    }

    public void testMultipliedBy_int_4_oe() {
        Years test = Years.years(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test,test.multipliedBy(1));
    }

    public void testDividedBy_int_1_oe() {
        Years test = Years.years(12);
        assertEquals(6,test.dividedBy(2).getYears());
    }

    public void testDividedBy_int_2_oe() {
        Years test = Years.years(12);
        // removed other assertion
        assertEquals(12,test.getYears());
    }

    public void testDividedBy_int_3_oe() {
        Years test = Years.years(12);
        // removed other assertion
        // removed other assertion
        assertEquals(4,test.dividedBy(3).getYears());
    }

    public void testDividedBy_int_4_oe() {
        Years test = Years.years(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,test.dividedBy(4).getYears());
    }

    public void testDividedBy_int_5_oe() {
        Years test = Years.years(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.dividedBy(5).getYears());
    }

    public void testDividedBy_int_6_oe() {
        Years test = Years.years(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,test.dividedBy(6).getYears());
    }

    public void testDividedBy_int_7_oe() {
        Years test = Years.years(12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test,test.dividedBy(1));
    }

    public void testNegated_1_oe() {
        Years test = Years.years(12);
        assertEquals(-12,test.negated().getYears());
    }

    public void testNegated_2_oe() {
        Years test = Years.years(12);
        // removed other assertion
        assertEquals(12,test.getYears());
    }

    public void testAddToLocalDate_1_oe() {
        Years test = Years.years(3);
        LocalDate date = new LocalDate(2006, 6, 1);
        LocalDate expected = new LocalDate(2009, 6, 1);
        assertEquals(expected,date.plus(test));
    }

}
