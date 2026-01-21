/*
 *  Copyright 2001-2011 Stephen Colebourne
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

import java.util.Locale;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.LenientChronology;
import org.joda.time.chrono.StrictChronology;

/**
 * This class is a Junit unit test for MonthDay. Based on {@link TestYearMonth_Propeties} 
 */
public class TestMonthDay_Properties_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);

    private long TEST_TIME_NOW =
            (31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
    private long TEST_TIME1 =
        (31L + 28L + 31L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
        + 12L * DateTimeConstants.MILLIS_PER_HOUR
        + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    private long TEST_TIME2 =
        (365L + 31L + 28L + 31L + 30L + 7L -1L) * DateTimeConstants.MILLIS_PER_DAY
        + 14L * DateTimeConstants.MILLIS_PER_HOUR
        + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    private DateTimeZone zone = null;
    private Locale locale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMonthDay_Properties.class);
    }

    public TestMonthDay_Properties_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        locale = Locale.getDefault();
        Locale.setDefault(Locale.UK);
        DateTimeZone.setDefault(DateTimeZone.UTC);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        zone = null;
        Locale.setDefault(locale);
        locale = null;
    }

    //-----------------------------------------------------------------------

    public void testPropertyAddMonthOfYear() {
        MonthDay test = new MonthDay(3, 6);
        MonthDay copy = test.monthOfYear().addToCopy(9);
        check(test, 3, 6);
        check(copy, 12, 6);
        
        copy = test.monthOfYear().addToCopy(0);
        check(copy, 3, 6);

        check(test, 3, 6);
        
        copy = test.monthOfYear().addToCopy(-3);
        check(copy, 12, 6);
        check(test, 3, 6);
    }

    public void testPropertyAddWrapFieldMonthOfYear() {
        MonthDay test = new MonthDay(5, 6);
        MonthDay copy = test.monthOfYear().addWrapFieldToCopy(2);
        check(test, 5, 6);
        check(copy, 7, 6);
        
        copy = test.monthOfYear().addWrapFieldToCopy(2);
        check(copy, 7, 6);
        
        copy = test.monthOfYear().addWrapFieldToCopy(292278993 - 4 + 1);
        check(copy, 11, 6);
        
        copy = test.monthOfYear().addWrapFieldToCopy(-292275054 - 4 - 1);
        check(copy, 6, 6);
    }

    public void testPropertySetMonthOfYear() {
        MonthDay test = new MonthDay(10, 6);
        MonthDay copy = test.monthOfYear().setCopy(12);
        check(test, 10, 6);
        check(copy, 12, 6);
    }

    public void testPropertySetTextMonthOfYear() {
        MonthDay test = new MonthDay(10, 6);
        MonthDay copy = test.monthOfYear().setCopy("12");
        check(test, 10, 6);
        check(copy, 12, 6);
    }

    //-----------------------------------------------------------------------

    public void testPropertyAddDayOfMonth() {
        MonthDay test = new MonthDay(4, 6);
        MonthDay copy = test.dayOfMonth().addToCopy(6);
        check(test, 4, 6);
        check(copy, 4, 12);
        
        copy = test.dayOfMonth().addToCopy(7);
        check(copy, 4, 13);
        
        copy = test.dayOfMonth().addToCopy(-5);
        check(copy, 4, 1);
        
        copy = test.dayOfMonth().addToCopy(-6);
        check(copy, 3, 31);
    }

    public void testPropertyAddWrapFieldDayOfMonth() {
        MonthDay test = new MonthDay(4, 6);
        MonthDay copy = test.dayOfMonth().addWrapFieldToCopy(4);
        check(test, 4, 6);
        check(copy, 4, 10);
        
        copy = test.dayOfMonth().addWrapFieldToCopy(8);
        check(copy, 4, 14);
        
        copy = test.dayOfMonth().addWrapFieldToCopy(-8);
        check(copy, 4, 28);
    }

    public void testPropertySetDayOfMonth() {
        MonthDay test = new MonthDay(4, 6);
        MonthDay copy = test.dayOfMonth().setCopy(12);
        check(test, 4, 6);
        check(copy, 4, 12);
        
        try {
            test.dayOfMonth().setCopy(33);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.dayOfMonth().setCopy(0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testPropertySetTextDayOfMonth() {
        MonthDay test = new MonthDay(4, 6);
        MonthDay copy = test.dayOfMonth().setCopy("12");
        check(test, 4, 6);
        check(copy, 4, 12);
        
        copy = test.dayOfMonth().setCopy("2");
        check(test, 4, 6);
        check(copy, 4, 2);
        
        copy = test.dayOfMonth().setCopy("4");
        check(test, 4, 6);
        check(copy, 4, 4);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    private void check(MonthDay test, int monthOfYear, int dayOfMonth) {
        assertEquals(monthOfYear, test.getMonthOfYear());
        assertEquals(dayOfMonth, test.getDayOfMonth());
    }

    public void testPropertyGetMonthOfYear_1_oe() {
        MonthDay test = new MonthDay(9, 6);
        assertSame(test.getChronology().monthOfYear(), test.monthOfYear().getField());
    }

    public void testPropertyGetMonthOfYear_2_oe() {
        MonthDay test = new MonthDay(9, 6);
        // removed other assertion
        assertEquals("monthOfYear", test.monthOfYear().getName());
    }

    public void testPropertyGetMonthOfYear_3_oe() {
        MonthDay test = new MonthDay(9, 6);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[monthOfYear]", test.monthOfYear().toString());
    }

    public void testPropertyGetMonthOfYear_4_oe() {
        MonthDay test = new MonthDay(9, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.monthOfYear().getReadablePartial());
    }

    public void testPropertyGetMonthOfYear_5_oe() {
        MonthDay test = new MonthDay(9, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.monthOfYear().getMonthDay());
    }

    public void testPropertyGetMonthOfYear_6_oe() {
        MonthDay test = new MonthDay(9, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_7_oe() {
        MonthDay test = new MonthDay(9, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("9", test.monthOfYear().getAsString());
    }

    public void testPropertyGetMonthOfYear_8_oe() {
        MonthDay test = new MonthDay(9, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("September", test.monthOfYear().getAsText());
    }

    public void testPropertyGetMonthOfYear_9_oe() {
        MonthDay test = new MonthDay(9, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("septembre", test.monthOfYear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMonthOfYear_10_oe() {
        MonthDay test = new MonthDay(9, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Sep", test.monthOfYear().getAsShortText());
    }

    public void testPropertyGetMonthOfYear_11_oe() {
        MonthDay test = new MonthDay(9, 6);
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
        assertEquals("sept.", test.monthOfYear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMonthOfYear_12_oe() {
        MonthDay test = new MonthDay(9, 6);
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
        assertEquals(test.getChronology().months(), test.monthOfYear().getDurationField());
    }

    public void testPropertyGetMonthOfYear_13_oe() {
        MonthDay test = new MonthDay(9, 6);
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
        // assertEquals(test.getChronology().days(), test.dayOfMonth().getRangeDurationField());
        assertEquals(9, test.monthOfYear().getMaximumTextLength(null));
    }

    public void testPropertyGetMonthOfYear_14_oe() {
        MonthDay test = new MonthDay(9, 6);
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
        // assertEquals(test.getChronology().days(), test.dayOfMonth().getRangeDurationField());
        // removed other assertion
        assertEquals(3, test.monthOfYear().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMaxMinValuesMonthOfYear_1_oe() {
        MonthDay test = new MonthDay(10, 6);
        assertEquals(1, test.monthOfYear().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesMonthOfYear_2_oe() {
        MonthDay test = new MonthDay(10, 6);
        // removed other assertion
        assertEquals(1, test.monthOfYear().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesMonthOfYear_3_oe() {
        MonthDay test = new MonthDay(10, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(12, test.monthOfYear().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesMonthOfYear_4_oe() {
        MonthDay test = new MonthDay(10, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12, test.monthOfYear().getMaximumValueOverall());
    }

    public void testPropertyCompareToMonthOfYear_1_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        assertEquals(true, test1.monthOfYear().compareTo(test2) < 0);
    }

    public void testPropertyCompareToMonthOfYear_2_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.monthOfYear().compareTo(test1) > 0);
    }

    public void testPropertyCompareToMonthOfYear_3_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().compareTo(test1) == 0);
    }

    public void testPropertyCompareToMonthOfYear_5_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.monthOfYear().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true, test1.monthOfYear().compareTo(dt2) < 0);
    }

    public void testPropertyCompareToMonthOfYear_6_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.monthOfYear().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.monthOfYear().compareTo(dt1) > 0);
    }

    public void testPropertyCompareToMonthOfYear_7_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.monthOfYear().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().compareTo(dt1) == 0);
    }

    public void testPropertyGetDayOfMonth_1_oe() {
        MonthDay test = new MonthDay(4, 6);
        assertSame(test.getChronology().dayOfMonth(), test.dayOfMonth().getField());
    }

    public void testPropertyGetDayOfMonth_2_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        assertEquals("dayOfMonth", test.dayOfMonth().getName());
    }

    public void testPropertyGetDayOfMonth_3_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[dayOfMonth]", test.dayOfMonth().toString());
    }

    public void testPropertyGetDayOfMonth_4_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.dayOfMonth().getReadablePartial());
    }

    public void testPropertyGetDayOfMonth_5_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.dayOfMonth().getMonthDay());
    }

    public void testPropertyGetDayOfMonth_6_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_7_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("6", test.dayOfMonth().getAsString());
    }

    public void testPropertyGetDayOfMonth_8_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("6", test.dayOfMonth().getAsText());
    }

    public void testPropertyGetDayOfMonth_9_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("6", test.dayOfMonth().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfMonth_10_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("6", test.dayOfMonth().getAsShortText());
    }

    public void testPropertyGetDayOfMonth_11_oe() {
        MonthDay test = new MonthDay(4, 6);
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
        assertEquals("6", test.dayOfMonth().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfMonth_12_oe() {
        MonthDay test = new MonthDay(4, 6);
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
        assertEquals(test.getChronology().days(), test.dayOfMonth().getDurationField());
    }

    public void testPropertyGetDayOfMonth_13_oe() {
        MonthDay test = new MonthDay(4, 6);
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
        assertEquals(test.getChronology().months(), test.dayOfMonth().getRangeDurationField());
    }

    public void testPropertyGetDayOfMonth_14_oe() {
        MonthDay test = new MonthDay(4, 6);
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
        assertEquals(2, test.dayOfMonth().getMaximumTextLength(null));
    }

    public void testPropertyGetDayOfMonth_15_oe() {
        MonthDay test = new MonthDay(4, 6);
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
        assertEquals(2, test.dayOfMonth().getMaximumShortTextLength(null));
    }

    public void testPropertyGetDayOfMonth_16_oe() {
        MonthDay test = new MonthDay(4, 6);
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
        test = new MonthDay(4, 7);
        assertEquals("7", test.dayOfMonth().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfMonth_17_oe() {
        MonthDay test = new MonthDay(4, 6);
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
        test = new MonthDay(4, 7);
        // removed other assertion
        assertEquals("7", test.dayOfMonth().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMaxMinValuesDayOfMonth_1_oe() {
        MonthDay test = new MonthDay(4, 6);
        assertEquals(1, test.dayOfMonth().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesDayOfMonth_2_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        assertEquals(1, test.dayOfMonth().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesDayOfMonth_3_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(30, test.dayOfMonth().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesDayOfMonth_4_oe() {
        MonthDay test = new MonthDay(4, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(31, test.dayOfMonth().getMaximumValueOverall());
    }

    public void testPropertyCompareToDayOfMonth_1_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        assertEquals(true, test1.dayOfMonth().compareTo(test2) < 0);
    }

    public void testPropertyCompareToDayOfMonth_2_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.dayOfMonth().compareTo(test1) > 0);
    }

    public void testPropertyCompareToDayOfMonth_3_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.dayOfMonth().compareTo(test1) == 0);
    }

    public void testPropertyCompareToDayOfMonth_5_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.dayOfMonth().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true, test1.dayOfMonth().compareTo(dt2) < 0);
    }

    public void testPropertyCompareToDayOfMonth_6_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.dayOfMonth().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.dayOfMonth().compareTo(dt1) > 0);
    }

    public void testPropertyCompareToDayOfMonth_7_oe() {
        MonthDay test1 = new MonthDay(TEST_TIME1);
        MonthDay test2 = new MonthDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.dayOfMonth().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.dayOfMonth().compareTo(dt1) == 0);
    }

    public void testPropertyEquals_1_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
        assertEquals(true, test1.dayOfMonth().equals(test1.dayOfMonth()));
    }

    public void testPropertyEquals_2_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(false, test1.dayOfMonth().equals(test1.monthOfYear()));
    }

    public void testPropertyEquals_3_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.dayOfMonth().equals(test2.dayOfMonth()));
    }

    public void testPropertyEquals_4_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.dayOfMonth().equals(test2.monthOfYear()));
    }

    public void testPropertyEquals_5_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test1.monthOfYear().equals(test1.dayOfMonth()));
    }

    public void testPropertyEquals_6_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test1.monthOfYear().equals(test1.monthOfYear()));
    }

    public void testPropertyEquals_7_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.monthOfYear().equals(test2.dayOfMonth()));
    }

    public void testPropertyEquals_8_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().equals(test2.monthOfYear()));
    }

    public void testPropertyEquals_9_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test1.dayOfMonth().equals(null));
    }

    public void testPropertyEquals_10_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test1.dayOfMonth().equals("any"));
    }

    public void testPropertyEquals_11_oe() {
        MonthDay test1 = new MonthDay(11, 11);
        MonthDay test2 = new MonthDay(11, 12);
        MonthDay test3 = new MonthDay(11, 11, CopticChronology.getInstanceUTC());
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
        
        // chrono
        assertEquals(false, test1.dayOfMonth().equals(test3.dayOfMonth()));
    }

    public void testPropertyHashCode_1_oe() {
        MonthDay test1 = new MonthDay(5, 11);
        MonthDay test2 = new MonthDay(5, 12);
        assertEquals(true, test1.dayOfMonth().hashCode() == test1.dayOfMonth().hashCode());
    }

    public void testPropertyHashCode_2_oe() {
        MonthDay test1 = new MonthDay(5, 11);
        MonthDay test2 = new MonthDay(5, 12);
        // removed other assertion
        assertEquals(false, test1.dayOfMonth().hashCode() == test2.dayOfMonth().hashCode());
    }

    public void testPropertyHashCode_3_oe() {
        MonthDay test1 = new MonthDay(5, 11);
        MonthDay test2 = new MonthDay(5, 12);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().hashCode() == test1.monthOfYear().hashCode());
    }

    public void testPropertyHashCode_4_oe() {
        MonthDay test1 = new MonthDay(5, 11);
        MonthDay test2 = new MonthDay(5, 12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().hashCode() == test2.monthOfYear().hashCode());
    }

    public void testPropertyEqualsHashCodeLenient_1_oe() {
        MonthDay test1 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true, test1.dayOfMonth().equals(test2.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeLenient_2_oe() {
        MonthDay test1 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        assertEquals(true, test2.dayOfMonth().equals(test1.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeLenient_3_oe() {
        MonthDay test1 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.dayOfMonth().equals(test1.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeLenient_4_oe() {
        MonthDay test1 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.dayOfMonth().equals(test2.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeLenient_5_oe() {
        MonthDay test1 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.dayOfMonth().hashCode() == test2.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeLenient_6_oe() {
        MonthDay test1 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.dayOfMonth().hashCode() == test1.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeLenient_7_oe() {
        MonthDay test1 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.dayOfMonth().hashCode() == test2.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeStrict_1_oe() {
        MonthDay test1 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true, test1.dayOfMonth().equals(test2.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeStrict_2_oe() {
        MonthDay test1 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        assertEquals(true, test2.dayOfMonth().equals(test1.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeStrict_3_oe() {
        MonthDay test1 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.dayOfMonth().equals(test1.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeStrict_4_oe() {
        MonthDay test1 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.dayOfMonth().equals(test2.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeStrict_5_oe() {
        MonthDay test1 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.dayOfMonth().hashCode() == test2.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeStrict_6_oe() {
        MonthDay test1 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.dayOfMonth().hashCode() == test1.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeStrict_7_oe() {
        MonthDay test1 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        MonthDay test2 = new MonthDay(5, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.dayOfMonth().hashCode() == test2.dayOfMonth().hashCode());
    }

}
