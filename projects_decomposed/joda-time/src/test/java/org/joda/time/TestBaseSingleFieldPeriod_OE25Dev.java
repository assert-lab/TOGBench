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

import org.joda.time.base.BaseSingleFieldPeriod;

/**
 * This class is a Junit unit test for BaseSingleFieldPeriod.
 *
 * @author Stephen Colebourne
 */
public class TestBaseSingleFieldPeriod_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestBaseSingleFieldPeriod_OE25Dev.class);
    }

    public TestBaseSingleFieldPeriod_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    //-----------------------------------------------------------------------
    public void testFactory_between_RInstant() {
        // test using Days
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(3,Single.between(start,end1,DurationFieldType.days()));
        assertEquals(0,Single.between(start,start,DurationFieldType.days()));
        assertEquals(0,Single.between(end1,end1,DurationFieldType.days()));
        assertEquals(-3,Single.between(end1,start,DurationFieldType.days()));
        assertEquals(6,Single.between(start,end2,DurationFieldType.days()));
        try {
            Single.between(start, (ReadableInstant) null, DurationFieldType.days());
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            Single.between((ReadableInstant) null, end1, DurationFieldType.days());
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            Single.between((ReadableInstant) null, (ReadableInstant) null, DurationFieldType.days());
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    @SuppressWarnings("deprecation")
    public void testFactory_between_RPartial() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        Single zero = new Single(0);
        assertEquals(3,Single.between(start,end1,zero));
        assertEquals(0,Single.between(start,start,zero));
        assertEquals(0,Single.between(end1,end1,zero));
        assertEquals(-3,Single.between(end1,start,zero));
        assertEquals(6,Single.between(start,end2,zero));
        try {
            Single.between(start, (ReadablePartial) null, zero);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            Single.between((ReadablePartial) null, end1, zero);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            Single.between((ReadablePartial) null, (ReadablePartial) null, zero);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            Single.between(start, new LocalTime(), zero);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            Single.between(new Partial(DateTimeFieldType.dayOfWeek(), 2), new Partial(DateTimeFieldType.dayOfMonth(), 3), zero);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        Partial p = new Partial(
                new DateTimeFieldType[] {DateTimeFieldType.year(), DateTimeFieldType.hourOfDay()},
                new int[] {1, 2});
        try {
            Single.between(p, p, zero);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    public void testFactory_standardPeriodIn_RPeriod() {
        assertEquals(0,Single.standardPeriodIn((ReadablePeriod)null,DateTimeConstants.MILLIS_PER_DAY));
        assertEquals(0,Single.standardPeriodIn(Period.ZERO,DateTimeConstants.MILLIS_PER_DAY));
        assertEquals(1,Single.standardPeriodIn(new Period(0,0,0,1,0,0,0,0),DateTimeConstants.MILLIS_PER_DAY));
        assertEquals(123,Single.standardPeriodIn(Period.days(123),DateTimeConstants.MILLIS_PER_DAY));
        assertEquals(-987,Single.standardPeriodIn(Period.days(-987),DateTimeConstants.MILLIS_PER_DAY));
        assertEquals(1,Single.standardPeriodIn(Period.hours(47),DateTimeConstants.MILLIS_PER_DAY));
        assertEquals(2,Single.standardPeriodIn(Period.hours(48),DateTimeConstants.MILLIS_PER_DAY));
        assertEquals(2,Single.standardPeriodIn(Period.hours(49),DateTimeConstants.MILLIS_PER_DAY));
        assertEquals(14,Single.standardPeriodIn(Period.weeks(2),DateTimeConstants.MILLIS_PER_DAY));
        try {
            Single.standardPeriodIn(Period.months(1), DateTimeConstants.MILLIS_PER_DAY);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void testValueIndexMethods() {
        Single test = new Single(20);
        assertEquals(1,test.size());
        assertEquals(20,test.getValue(0));
        try {
            test.getValue(1);
            fail();
        } catch (IndexOutOfBoundsException ex) {
            // expected
        }
    }

    public void testFieldTypeIndexMethods() {
        Single test = new Single(20);
        assertEquals(1,test.size());
        assertEquals(DurationFieldType.days(),test.getFieldType(0));
        try {
            test.getFieldType(1);
            fail();
        } catch (IndexOutOfBoundsException ex) {
            // expected
        }
    }

    public void testIsSupported() {
        Single test = new Single(20);
        assertEquals(false,test.isSupported(DurationFieldType.years()));
        assertEquals(false,test.isSupported(DurationFieldType.months()));
        assertEquals(false,test.isSupported(DurationFieldType.weeks()));
        assertEquals(true,test.isSupported(DurationFieldType.days()));
        assertEquals(false,test.isSupported(DurationFieldType.hours()));
        assertEquals(false,test.isSupported(DurationFieldType.minutes()));
        assertEquals(false,test.isSupported(DurationFieldType.seconds()));
        assertEquals(false,test.isSupported(DurationFieldType.millis()));
    }        

    public void testGet() {
        Single test = new Single(20);
        assertEquals(0,test.get(DurationFieldType.years()));
        assertEquals(0,test.get(DurationFieldType.months()));
        assertEquals(0,test.get(DurationFieldType.weeks()));
        assertEquals(20,test.get(DurationFieldType.days()));
        assertEquals(0,test.get(DurationFieldType.hours()));
        assertEquals(0,test.get(DurationFieldType.minutes()));
        assertEquals(0,test.get(DurationFieldType.seconds()));
        assertEquals(0,test.get(DurationFieldType.millis()));
    }

    //-----------------------------------------------------------------------
    public void testEqualsHashCode() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        assertEquals(true,testA.equals(testB));
        assertEquals(true,testB.equals(testA));
        assertEquals(true,testA.equals(testA));
        assertEquals(true,testB.equals(testB));
        assertEquals(true,testA.hashCode()== testB.hashCode());
        assertEquals(true,testA.hashCode()== testA.hashCode());
        assertEquals(true,testB.hashCode()== testB.hashCode());
        
        Single testC = new Single(30);
        assertEquals(false,testA.equals(testC));
        assertEquals(false,testB.equals(testC));
        assertEquals(false,testC.equals(testA));
        assertEquals(false,testC.equals(testB));
        assertEquals(false,testA.hashCode()== testC.hashCode());
        assertEquals(false,testB.hashCode()== testC.hashCode());
        
        assertEquals(true,testA.equals(Days.days(20)));
        assertEquals(true,testA.equals(new Period(0,0,0,20,0,0,0,0,PeriodType.days())));
        assertEquals(false,testA.equals(Period.days(2)));
        assertEquals(false,testA.equals("Hello"));
        assertEquals(false,testA.equals(Hours.hours(2)));
        assertEquals(false,testA.equals(null));
    }

    public void testCompareTo() {
        Single test1 = new Single(21);
        Single test2 = new Single(22);
        Single test3 = new Single(23);
        assertEquals(true,test1.compareTo(test1)== 0);
        assertEquals(true,test1.compareTo(test2)< 0);
        assertEquals(true,test1.compareTo(test3)< 0);
        assertEquals(true,test2.compareTo(test1)> 0);
        assertEquals(true,test2.compareTo(test2)== 0);
        assertEquals(true,test2.compareTo(test3)< 0);
        assertEquals(true,test3.compareTo(test1)> 0);
        assertEquals(true,test3.compareTo(test2)> 0);
        assertEquals(true,test3.compareTo(test3)== 0);
        
//        try {
//            test1.compareTo("Hello");
//            fail();
//        } catch (ClassCastException ex) {
//            // expected
//        }
//        try {
//            test1.compareTo(new Period(0, 0, 0, 21, 0, 0, 0, 0, PeriodType.days()));
//            fail();
//        } catch (ClassCastException ex) {
//            // expected
//        }
        try {
            test1.compareTo(null);
            fail();
        } catch (NullPointerException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void testToPeriod() {
        Single test = new Single(20);
        Period expected = Period.days(20);
        assertEquals(expected,test.toPeriod());
    }

    public void testToMutablePeriod() {
        Single test = new Single(20);
        MutablePeriod expected = new MutablePeriod(0, 0, 0, 20, 0, 0, 0, 0);
        assertEquals(expected,test.toMutablePeriod());
    }

//    public void testToDurationFrom() {
//        Period test = new Period(123L);
//        assertEquals(new Duration(123L),test.toDurationFrom(new Instant(0L)));
//    }
//
//    public void testToDurationTo() {
//        Period test = new Period(123L);
//        assertEquals(new Duration(123L),test.toDurationTo(new Instant(123L)));
//    }
//

    //-----------------------------------------------------------------------
    public void testGetSetValue() {
        Single test = new Single(20);
        assertEquals(20,test.getValue());
        test.setValue(10);
        assertEquals(10,test.getValue());
    }

    //-----------------------------------------------------------------------
    /** Test class. */
    static class Single extends BaseSingleFieldPeriod {

        public Single(int period) {
            super(period);
        }
        
        public static int between(ReadableInstant start, ReadableInstant end, DurationFieldType field) {
            return BaseSingleFieldPeriod.between(start, end, field);
        }
        
        public static int between(ReadablePartial start, ReadablePartial end, ReadablePeriod zeroInstance) {
            return BaseSingleFieldPeriod.between(start, end, zeroInstance);
        }
        
        public static int standardPeriodIn(ReadablePeriod period, long millisPerUnit) {
            return BaseSingleFieldPeriod.standardPeriodIn(period, millisPerUnit);
        }
        
        @Override
        public DurationFieldType getFieldType() {
            return DurationFieldType.days();
        }

        @Override
        public PeriodType getPeriodType() {
            return PeriodType.days();
        }
        
        @Override
        public int getValue() {
            return super.getValue();
        }
        
        @Override
        public void setValue(int value) {
            super.setValue(value);
        }
    }

    public void testFactory_between_RInstant_1_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(3,Single.between(start,end1,DurationFieldType.days()));
    }

    public void testFactory_between_RInstant_2_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(0,Single.between(start,start,DurationFieldType.days()));
    }

    public void testFactory_between_RInstant_3_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(0,Single.between(end1,end1,DurationFieldType.days()));
    }

    public void testFactory_between_RInstant_4_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(-3,Single.between(end1,start,DurationFieldType.days()));
    }

    public void testFactory_between_RInstant_5_oe() {
        DateTime start = new DateTime(2006, 6, 9, 12, 0, 0, 0, PARIS);
        DateTime end1 = new DateTime(2006, 6, 12, 12, 0, 0, 0, PARIS);
        DateTime end2 = new DateTime(2006, 6, 15, 18, 0, 0, 0, PARIS);
        
        assertEquals(6,Single.between(start,end2,DurationFieldType.days()));
    }

    public void testFactory_between_RPartial_1_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        Single zero = new Single(0);
        assertEquals(3,Single.between(start,end1,zero));
    }

    public void testFactory_between_RPartial_2_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        Single zero = new Single(0);
        assertEquals(0,Single.between(start,start,zero));
    }

    public void testFactory_between_RPartial_3_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        Single zero = new Single(0);
        assertEquals(0,Single.between(end1,end1,zero));
    }

    public void testFactory_between_RPartial_4_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        Single zero = new Single(0);
        assertEquals(-3,Single.between(end1,start,zero));
    }

    public void testFactory_between_RPartial_5_oe() {
        LocalDate start = new LocalDate(2006, 6, 9);
        LocalDate end1 = new LocalDate(2006, 6, 12);
        YearMonthDay end2 = new YearMonthDay(2006, 6, 15);
        
        Single zero = new Single(0);
        assertEquals(6,Single.between(start,end2,zero));
    }

    public void testFactory_standardPeriodIn_RPeriod_1_oe() {
        int a = 0;
        assertEquals(a, Single.standardPeriodIn((ReadablePeriod)null,DateTimeConstants.MILLIS_PER_DAY));
    }

    public void testFactory_standardPeriodIn_RPeriod_2_oe() {
        int a = 0;
        assertEquals(a, Single.standardPeriodIn(Period.ZERO,DateTimeConstants.MILLIS_PER_DAY));
    }

    public void testFactory_standardPeriodIn_RPeriod_3_oe() {
        int a = 1;
        assertEquals(a, Single.standardPeriodIn(new Period(0,0,0,1,0,0,0,0),DateTimeConstants.MILLIS_PER_DAY));
    }

    public void testFactory_standardPeriodIn_RPeriod_4_oe() {
        int a = 123;
        assertEquals(a, Single.standardPeriodIn(Period.days(123),DateTimeConstants.MILLIS_PER_DAY));
    }

    public void testFactory_standardPeriodIn_RPeriod_5_oe() {
        int a = -987;
        assertEquals(a, Single.standardPeriodIn(Period.days(-987),DateTimeConstants.MILLIS_PER_DAY));
    }

    public void testFactory_standardPeriodIn_RPeriod_6_oe() {
        int a = 1;
        assertEquals(a, Single.standardPeriodIn(Period.hours(47),DateTimeConstants.MILLIS_PER_DAY));
    }

    public void testFactory_standardPeriodIn_RPeriod_7_oe() {
        int a = 2;
        assertEquals(a, Single.standardPeriodIn(Period.hours(48),DateTimeConstants.MILLIS_PER_DAY));
    }

    public void testFactory_standardPeriodIn_RPeriod_8_oe() {
        int a = 2;
        assertEquals(a, Single.standardPeriodIn(Period.hours(49),DateTimeConstants.MILLIS_PER_DAY));
    }

    public void testFactory_standardPeriodIn_RPeriod_9_oe() {
        int a = 14;
        assertEquals(a, Single.standardPeriodIn(Period.weeks(2),DateTimeConstants.MILLIS_PER_DAY));
    }

    public void testValueIndexMethods_1_oe() {
        Single test = new Single(20);
        assertEquals(1,test.size());
    }

    public void testValueIndexMethods_2_oe() {
        Single test = new Single(20);
        assertEquals(20,test.getValue(0));
    }

    public void testFieldTypeIndexMethods_1_oe() {
        Single test = new Single(20);
        assertEquals(1,test.size());
    }

    public void testFieldTypeIndexMethods_2_oe() {
        Single test = new Single(20);
        assertEquals(DurationFieldType.days(),test.getFieldType(0));
    }

    public void testIsSupported_1_oe() {
        Single test = new Single(20);
        assertEquals(false,test.isSupported(DurationFieldType.years()));
    }

    public void testIsSupported_2_oe() {
        Single test = new Single(20);
        assertEquals(false,test.isSupported(DurationFieldType.months()));
    }

    public void testIsSupported_3_oe() {
        Single test = new Single(20);
        assertEquals(false,test.isSupported(DurationFieldType.weeks()));
    }

    public void testIsSupported_4_oe() {
        Single test = new Single(20);
        assertEquals(true,test.isSupported(DurationFieldType.days()));
    }

    public void testIsSupported_5_oe() {
        Single test = new Single(20);
        assertEquals(false,test.isSupported(DurationFieldType.hours()));
    }

    public void testIsSupported_6_oe() {
        Single test = new Single(20);
        assertEquals(false,test.isSupported(DurationFieldType.minutes()));
    }

    public void testIsSupported_7_oe() {
        Single test = new Single(20);
        assertEquals(false,test.isSupported(DurationFieldType.seconds()));
    }

    public void testIsSupported_8_oe() {
        Single test = new Single(20);
        assertEquals(false,test.isSupported(DurationFieldType.millis()));
    }

    public void testGet_1_oe() {
        Single test = new Single(20);
        assertEquals(0,test.get(DurationFieldType.years()));
    }

    public void testGet_2_oe() {
        Single test = new Single(20);
        assertEquals(0,test.get(DurationFieldType.months()));
    }

    public void testGet_3_oe() {
        Single test = new Single(20);
        assertEquals(0,test.get(DurationFieldType.weeks()));
    }

    public void testGet_4_oe() {
        Single test = new Single(20);
        assertEquals(20,test.get(DurationFieldType.days()));
    }

    public void testGet_5_oe() {
        Single test = new Single(20);
        assertEquals(0,test.get(DurationFieldType.hours()));
    }

    public void testGet_6_oe() {
        Single test = new Single(20);
        assertEquals(0,test.get(DurationFieldType.minutes()));
    }

    public void testGet_7_oe() {
        Single test = new Single(20);
        assertEquals(0,test.get(DurationFieldType.seconds()));
    }

    public void testGet_8_oe() {
        Single test = new Single(20);
        assertEquals(0,test.get(DurationFieldType.millis()));
    }

    public void testEqualsHashCode_1_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        assertEquals(true,testA.equals(testB));
    }

    public void testEqualsHashCode_2_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        assertEquals(true,testB.equals(testA));
    }

    public void testEqualsHashCode_3_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        assertEquals(true,testA.equals(testA));
    }

    public void testEqualsHashCode_4_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        assertEquals(true,testB.equals(testB));
    }

    public void testEqualsHashCode_5_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        assertEquals(true,testA.hashCode()== testB.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        assertEquals(true,testA.hashCode()== testA.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        assertEquals(true,testB.hashCode()== testB.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        assertEquals(false,testA.equals(testC));
    }

    public void testEqualsHashCode_9_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        assertEquals(false,testB.equals(testC));
    }

    public void testEqualsHashCode_10_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        assertEquals(false,testC.equals(testA));
    }

    public void testEqualsHashCode_11_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        assertEquals(false,testC.equals(testB));
    }

    public void testEqualsHashCode_12_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        assertEquals(false,testA.hashCode()== testC.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        assertEquals(false,testB.hashCode()== testC.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        
        assertEquals(true,testA.equals(Days.days(20)));
    }

    public void testEqualsHashCode_15_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        
        assertEquals(true,testA.equals(new Period(0,0,0,20,0,0,0,0,PeriodType.days())));
    }

    public void testEqualsHashCode_16_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        
        assertEquals(false,testA.equals(Period.days(2)));
    }

    public void testEqualsHashCode_17_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        
        assertEquals(false,testA.equals("Hello"));
    }

    public void testEqualsHashCode_18_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        
        assertEquals(false,testA.equals(Hours.hours(2)));
    }

    public void testEqualsHashCode_19_oe() {
        Single testA = new Single(20);
        Single testB = new Single(20);
        
        Single testC = new Single(30);
        
        assertEquals(false,testA.equals(null));
    }

    public void testCompareTo_1_oe() {
        Single test1 = new Single(21);
        Single test2 = new Single(22);
        Single test3 = new Single(23);
        assertEquals(true,test1.compareTo(test1)== 0);
    }

    public void testCompareTo_2_oe() {
        Single test1 = new Single(21);
        Single test2 = new Single(22);
        Single test3 = new Single(23);
        assertEquals(true,test1.compareTo(test2)< 0);
    }

    public void testCompareTo_3_oe() {
        Single test1 = new Single(21);
        Single test2 = new Single(22);
        Single test3 = new Single(23);
        assertEquals(true,test1.compareTo(test3)< 0);
    }

    public void testCompareTo_4_oe() {
        Single test1 = new Single(21);
        Single test2 = new Single(22);
        Single test3 = new Single(23);
        assertEquals(true,test2.compareTo(test1)> 0);
    }

    public void testCompareTo_5_oe() {
        Single test1 = new Single(21);
        Single test2 = new Single(22);
        Single test3 = new Single(23);
        assertEquals(true,test2.compareTo(test2)== 0);
    }

    public void testCompareTo_6_oe() {
        Single test1 = new Single(21);
        Single test2 = new Single(22);
        Single test3 = new Single(23);
        assertEquals(true,test2.compareTo(test3)< 0);
    }

    public void testCompareTo_7_oe() {
        Single test1 = new Single(21);
        Single test2 = new Single(22);
        Single test3 = new Single(23);
        assertEquals(true,test3.compareTo(test1)> 0);
    }

    public void testCompareTo_8_oe() {
        Single test1 = new Single(21);
        Single test2 = new Single(22);
        Single test3 = new Single(23);
        assertEquals(true,test3.compareTo(test2)> 0);
    }

    public void testCompareTo_9_oe() {
        Single test1 = new Single(21);
        Single test2 = new Single(22);
        Single test3 = new Single(23);
        assertEquals(true,test3.compareTo(test3)== 0);
    }

    public void testToPeriod_1_oe() {
        Single test = new Single(20);
        Period expected = Period.days(20);
        assertEquals(expected,test.toPeriod());
    }

    public void testToMutablePeriod_1_oe() {
        Single test = new Single(20);
        MutablePeriod expected = new MutablePeriod(0, 0, 0, 20, 0, 0, 0, 0);
        assertEquals(expected,test.toMutablePeriod());
    }

    public void testGetSetValue_1_oe() {
        Single test = new Single(20);
        assertEquals(20,test.getValue());
    }

    public void testGetSetValue_2_oe() {
        Single test = new Single(20);
        test.setValue(10);
        assertEquals(10,test.getValue());
    }

public void testFactory_between_RInstant_oe_103_oe() {
        try {
            Single.between((ReadableInstant) null, (ReadableInstant) null, DurationFieldType.days());
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

public void testFactory_standardPeriodIn_RPeriod_oe_101_oe() {
        try {
            Single.standardPeriodIn(Period.months(1), DateTimeConstants.MILLIS_PER_DAY);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

}
