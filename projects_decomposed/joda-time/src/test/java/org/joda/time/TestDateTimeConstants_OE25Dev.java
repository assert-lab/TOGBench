/*
 *  Copyright 2001-2005 Stephen Colebourne
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

/**
 * Test case.
 *
 * @author Stephen Colebourne
 */
public class TestDateTimeConstants_OE25Dev extends TestCase {

    /**
     * The main method for this test program.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    /**
     * TestSuite is a junit required method.
     */
    public static TestSuite suite() {
        return new TestSuite(TestDateTimeConstants_OE25Dev.class);
    }

    /**
     * TestDateTimeComparator constructor.
     * @param name
     */
    public TestDateTimeConstants_OE25Dev(String name) {
        super(name);
    }

    //-----------------------------------------------------------------------
    public void testConstructor() {
        DateTimeConstants c = new DateTimeConstants() {
        };
        c.toString();
    }

    public void testHalfdaysOfDay() {
        assertEquals(0,DateTimeConstants.AM);
        assertEquals(1,DateTimeConstants.PM);
    }

    public void testDaysOfWeek() {
        assertEquals(1,DateTimeConstants.MONDAY);
        assertEquals(2,DateTimeConstants.TUESDAY);
        assertEquals(3,DateTimeConstants.WEDNESDAY);
        assertEquals(4,DateTimeConstants.THURSDAY);
        assertEquals(5,DateTimeConstants.FRIDAY);
        assertEquals(6,DateTimeConstants.SATURDAY);
        assertEquals(7,DateTimeConstants.SUNDAY);
    }

    public void testMonthsOfYear() {
        assertEquals(1,DateTimeConstants.JANUARY);
        assertEquals(2,DateTimeConstants.FEBRUARY);
        assertEquals(3,DateTimeConstants.MARCH);
        assertEquals(4,DateTimeConstants.APRIL);
        assertEquals(5,DateTimeConstants.MAY);
        assertEquals(6,DateTimeConstants.JUNE);
        assertEquals(7,DateTimeConstants.JULY);
        assertEquals(8,DateTimeConstants.AUGUST);
        assertEquals(9,DateTimeConstants.SEPTEMBER);
        assertEquals(10,DateTimeConstants.OCTOBER);
        assertEquals(11,DateTimeConstants.NOVEMBER);
        assertEquals(12,DateTimeConstants.DECEMBER);
    }

    public void testEras() {
        assertEquals(0,DateTimeConstants.BC);
        assertEquals(0,DateTimeConstants.BCE);
        assertEquals(1,DateTimeConstants.AD);
        assertEquals(1,DateTimeConstants.CE);
    }

    public void testMaths() {
        assertEquals(1000,DateTimeConstants.MILLIS_PER_SECOND);
        assertEquals(60 * 1000,DateTimeConstants.MILLIS_PER_MINUTE);
        assertEquals(60 * 60 * 1000,DateTimeConstants.MILLIS_PER_HOUR);
        assertEquals(24 * 60 * 60 * 1000,DateTimeConstants.MILLIS_PER_DAY);
        assertEquals(7 * 24 * 60 * 60 * 1000,DateTimeConstants.MILLIS_PER_WEEK);
        
        assertEquals(60,DateTimeConstants.SECONDS_PER_MINUTE);
        assertEquals(60 * 60,DateTimeConstants.SECONDS_PER_HOUR);
        assertEquals(24 * 60 * 60,DateTimeConstants.SECONDS_PER_DAY);
        assertEquals(7 * 24 * 60 * 60,DateTimeConstants.SECONDS_PER_WEEK);
        
        assertEquals(60,DateTimeConstants.MINUTES_PER_HOUR);
        assertEquals(24 * 60,DateTimeConstants.MINUTES_PER_DAY);
        assertEquals(7 * 24 * 60,DateTimeConstants.MINUTES_PER_WEEK);
        
        assertEquals(24,DateTimeConstants.HOURS_PER_DAY);
        assertEquals(7 * 24,DateTimeConstants.HOURS_PER_WEEK);
        
        assertEquals(7,DateTimeConstants.DAYS_PER_WEEK);
    }

    public void testHalfdaysOfDay_1_oe() {
        int a = 0;
        assertEquals(a, DateTimeConstants.AM);
    }

    public void testHalfdaysOfDay_2_oe() {
        int a = 1;
        assertEquals(a, DateTimeConstants.PM);
    }

    public void testDaysOfWeek_1_oe() {
        int a = 1;
        assertEquals(a, DateTimeConstants.MONDAY);
    }

    public void testDaysOfWeek_2_oe() {
        int a = 2;
        assertEquals(a, DateTimeConstants.TUESDAY);
    }

    public void testDaysOfWeek_3_oe() {
        int a = 3;
        assertEquals(a, DateTimeConstants.WEDNESDAY);
    }

    public void testDaysOfWeek_4_oe() {
        int a = 4;
        assertEquals(a, DateTimeConstants.THURSDAY);
    }

    public void testDaysOfWeek_5_oe() {
        int a = 5;
        assertEquals(a, DateTimeConstants.FRIDAY);
    }

    public void testDaysOfWeek_6_oe() {
        int a = 6;
        assertEquals(a, DateTimeConstants.SATURDAY);
    }

    public void testDaysOfWeek_7_oe() {
        int a = 7;
        assertEquals(a, DateTimeConstants.SUNDAY);
    }

    public void testMonthsOfYear_1_oe() {
        int a = 1;
        assertEquals(a, DateTimeConstants.JANUARY);
    }

    public void testMonthsOfYear_2_oe() {
        int a = 2;
        assertEquals(a, DateTimeConstants.FEBRUARY);
    }

    public void testMonthsOfYear_3_oe() {
        int a = 3;
        assertEquals(a, DateTimeConstants.MARCH);
    }

    public void testMonthsOfYear_4_oe() {
        int a = 4;
        assertEquals(a, DateTimeConstants.APRIL);
    }

    public void testMonthsOfYear_5_oe() {
        int a = 5;
        assertEquals(a, DateTimeConstants.MAY);
    }

    public void testMonthsOfYear_6_oe() {
        int a = 6;
        assertEquals(a, DateTimeConstants.JUNE);
    }

    public void testMonthsOfYear_7_oe() {
        int a = 7;
        assertEquals(a, DateTimeConstants.JULY);
    }

    public void testMonthsOfYear_8_oe() {
        int a = 8;
        assertEquals(a, DateTimeConstants.AUGUST);
    }

    public void testMonthsOfYear_9_oe() {
        int a = 9;
        assertEquals(a, DateTimeConstants.SEPTEMBER);
    }

    public void testMonthsOfYear_10_oe() {
        int a = 10;
        assertEquals(a, DateTimeConstants.OCTOBER);
    }

    public void testMonthsOfYear_11_oe() {
        int a = 11;
        assertEquals(a, DateTimeConstants.NOVEMBER);
    }

    public void testMonthsOfYear_12_oe() {
        int a = 12;
        assertEquals(a, DateTimeConstants.DECEMBER);
    }

    public void testEras_1_oe() {
        int a = 0;
        assertEquals(a, DateTimeConstants.BC);
    }

    public void testEras_2_oe() {
        int a = 0;
        assertEquals(a, DateTimeConstants.BCE);
    }

    public void testEras_3_oe() {
        int a = 1;
        assertEquals(a, DateTimeConstants.AD);
    }

    public void testEras_4_oe() {
        int a = 1;
        assertEquals(a, DateTimeConstants.CE);
    }

    public void testMaths_1_oe() {
        int a = 1000;
        assertEquals(a, DateTimeConstants.MILLIS_PER_SECOND);
    }

    public void testMaths_2_oe() {
        Object a = 60 * 1000;
        assertEquals(a, DateTimeConstants.MILLIS_PER_MINUTE);
    }

    public void testMaths_3_oe() {
        Object a = 60 * 60 * 1000;
        assertEquals(a, DateTimeConstants.MILLIS_PER_HOUR);
    }

    public void testMaths_4_oe() {
        Object a = 24 * 60 * 60 * 1000;
        assertEquals(a, DateTimeConstants.MILLIS_PER_DAY);
    }

    public void testMaths_5_oe() {
        Object a = 7 * 24 * 60 * 60 * 1000;
        assertEquals(a, DateTimeConstants.MILLIS_PER_WEEK);
    }

    public void testMaths_6_oe() {
        
        int a = 60;
        assertEquals(a, DateTimeConstants.SECONDS_PER_MINUTE);
    }

    public void testMaths_7_oe() {
        
        Object a = 60 * 60;
        assertEquals(a, DateTimeConstants.SECONDS_PER_HOUR);
    }

    public void testMaths_8_oe() {
        
        Object a = 24 * 60 * 60;
        assertEquals(a, DateTimeConstants.SECONDS_PER_DAY);
    }

    public void testMaths_9_oe() {
        
        Object a = 7 * 24 * 60 * 60;
        assertEquals(a, DateTimeConstants.SECONDS_PER_WEEK);
    }

    public void testMaths_10_oe() {
        
        
        int a = 60;
        assertEquals(a, DateTimeConstants.MINUTES_PER_HOUR);
    }

    public void testMaths_11_oe() {
        
        
        Object a = 24 * 60;
        assertEquals(a, DateTimeConstants.MINUTES_PER_DAY);
    }

    public void testMaths_12_oe() {
        
        
        Object a = 7 * 24 * 60;
        assertEquals(a, DateTimeConstants.MINUTES_PER_WEEK);
    }

    public void testMaths_13_oe() {
        
        
        
        int a = 24;
        assertEquals(a, DateTimeConstants.HOURS_PER_DAY);
    }

    public void testMaths_14_oe() {
        
        
        
        Object a = 7 * 24;
        assertEquals(a, DateTimeConstants.HOURS_PER_WEEK);
    }

    public void testMaths_15_oe() {
        
        
        
        
        int a = 7;
        assertEquals(a, DateTimeConstants.DAYS_PER_WEEK);
    }

}
