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
        return new TestSuite(TestDateTimeConstants_OE25Dev_OE25Dev.class);
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

    public void testHalfdaysOfDay_1_oe() {
        assertEquals(0, DateTimeConstants.AM);
    }

    public void testHalfdaysOfDay_2_oe() {
        // removed other assertion
        assertEquals(1, DateTimeConstants.PM);
    }

    public void testDaysOfWeek_1_oe() {
        assertEquals(1, DateTimeConstants.MONDAY);
    }

    public void testDaysOfWeek_2_oe() {
        // removed other assertion
        assertEquals(2, DateTimeConstants.TUESDAY);
    }

    public void testDaysOfWeek_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(3, DateTimeConstants.WEDNESDAY);
    }

    public void testDaysOfWeek_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, DateTimeConstants.THURSDAY);
    }

    public void testDaysOfWeek_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, DateTimeConstants.FRIDAY);
    }

    public void testDaysOfWeek_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, DateTimeConstants.SATURDAY);
    }

    public void testDaysOfWeek_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, DateTimeConstants.SUNDAY);
    }

    public void testMonthsOfYear_1_oe() {
        assertEquals(1, DateTimeConstants.JANUARY);
    }

    public void testMonthsOfYear_2_oe() {
        // removed other assertion
        assertEquals(2, DateTimeConstants.FEBRUARY);
    }

    public void testMonthsOfYear_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(3, DateTimeConstants.MARCH);
    }

    public void testMonthsOfYear_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, DateTimeConstants.APRIL);
    }

    public void testMonthsOfYear_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, DateTimeConstants.MAY);
    }

    public void testMonthsOfYear_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, DateTimeConstants.JUNE);
    }

    public void testMonthsOfYear_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, DateTimeConstants.JULY);
    }

    public void testMonthsOfYear_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, DateTimeConstants.AUGUST);
    }

    public void testMonthsOfYear_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, DateTimeConstants.SEPTEMBER);
    }

    public void testMonthsOfYear_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, DateTimeConstants.OCTOBER);
    }

    public void testMonthsOfYear_11_oe() {
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
        assertEquals(11, DateTimeConstants.NOVEMBER);
    }

    public void testMonthsOfYear_12_oe() {
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
        assertEquals(12, DateTimeConstants.DECEMBER);
    }

    public void testEras_1_oe() {
        assertEquals(0, DateTimeConstants.BC);
    }

    public void testEras_2_oe() {
        // removed other assertion
        assertEquals(0, DateTimeConstants.BCE);
    }

    public void testEras_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1, DateTimeConstants.AD);
    }

    public void testEras_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, DateTimeConstants.CE);
    }

    public void testMaths_1_oe() {
        assertEquals(1000, DateTimeConstants.MILLIS_PER_SECOND);
    }

    public void testMaths_2_oe() {
        // removed other assertion
        assertEquals(60 * 1000, DateTimeConstants.MILLIS_PER_MINUTE);
    }

    public void testMaths_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(60 * 60 * 1000, DateTimeConstants.MILLIS_PER_HOUR);
    }

    public void testMaths_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(24 * 60 * 60 * 1000, DateTimeConstants.MILLIS_PER_DAY);
    }

    public void testMaths_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7 * 24 * 60 * 60 * 1000, DateTimeConstants.MILLIS_PER_WEEK);
    }

    public void testMaths_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(60, DateTimeConstants.SECONDS_PER_MINUTE);
    }

    public void testMaths_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(60 * 60, DateTimeConstants.SECONDS_PER_HOUR);
    }

    public void testMaths_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(24 * 60 * 60, DateTimeConstants.SECONDS_PER_DAY);
    }

    public void testMaths_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7 * 24 * 60 * 60, DateTimeConstants.SECONDS_PER_WEEK);
    }

    public void testMaths_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(60, DateTimeConstants.MINUTES_PER_HOUR);
    }

    public void testMaths_11_oe() {
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
        assertEquals(24 * 60, DateTimeConstants.MINUTES_PER_DAY);
    }

    public void testMaths_12_oe() {
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
        assertEquals(7 * 24 * 60, DateTimeConstants.MINUTES_PER_WEEK);
    }

    public void testMaths_13_oe() {
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
        
        assertEquals(24, DateTimeConstants.HOURS_PER_DAY);
    }

    public void testMaths_14_oe() {
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
        assertEquals(7 * 24, DateTimeConstants.HOURS_PER_WEEK);
    }

    public void testMaths_15_oe() {
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
        
        assertEquals(7, DateTimeConstants.DAYS_PER_WEEK);
    }

}
