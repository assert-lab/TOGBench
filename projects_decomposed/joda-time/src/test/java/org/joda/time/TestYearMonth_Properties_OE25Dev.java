/*
 *  Copyright 2001-2010 Stephen Colebourne
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
 * This class is a Junit unit test for YearMonth.
 *
 * @author Stephen Colebourne
 */
public class TestYearMonth_Properties_OE25Dev extends TestCase {

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

    private Locale systemDefaultLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestYearMonth_Properties_OE25Dev_OE25Dev.class);
    }

    public TestYearMonth_Properties_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(DateTimeZone.UTC);
        systemDefaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        zone = null;
        Locale.setDefault(systemDefaultLocale);
        systemDefaultLocale = null;
    }

    //-----------------------------------------------------------------------

    public void testPropertyAddYear() {
        YearMonth test = new YearMonth(1972, 6);
        YearMonth copy = test.year().addToCopy(9);
        check(test, 1972, 6);
        check(copy, 1981, 6);
        
        copy = test.year().addToCopy(0);
        check(copy, 1972, 6);
        
        copy = test.year().addToCopy(292277023 - 1972);
        check(copy, 292277023, 6);
        
        try {
            test.year().addToCopy(292278993 - 1972 + 1);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 1972, 6);
        
        copy = test.year().addToCopy(-1972);
        check(copy, 0, 6);
        
        copy = test.year().addToCopy(-1973);
        check(copy, -1, 6);
        
        try {
            test.year().addToCopy(-292275054 - 1972 - 1);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 1972, 6);
    }

    public void testPropertyAddWrapFieldYear() {
        YearMonth test = new YearMonth(1972, 6);
        YearMonth copy = test.year().addWrapFieldToCopy(9);
        check(test, 1972, 6);
        check(copy, 1981, 6);
        
        copy = test.year().addWrapFieldToCopy(0);
        check(copy, 1972, 6);
        
        copy = test.year().addWrapFieldToCopy(292278993 - 1972 + 1);
        check(copy, -292275054, 6);
        
        copy = test.year().addWrapFieldToCopy(-292275054 - 1972 - 1);
        check(copy, 292278993, 6);
    }

    public void testPropertySetYear() {
        YearMonth test = new YearMonth(1972, 6);
        YearMonth copy = test.year().setCopy(12);
        check(test, 1972, 6);
        check(copy, 12, 6);
    }

    public void testPropertySetTextYear() {
        YearMonth test = new YearMonth(1972, 6);
        YearMonth copy = test.year().setCopy("12");
        check(test, 1972, 6);
        check(copy, 12, 6);
    }

    //-----------------------------------------------------------------------

    public void testPropertyAddMonth() {
        YearMonth test = new YearMonth(1972, 6);
        YearMonth copy = test.monthOfYear().addToCopy(6);
        check(test, 1972, 6);
        check(copy, 1972, 12);
        
        copy = test.monthOfYear().addToCopy(7);
        check(copy, 1973, 1);
        
        copy = test.monthOfYear().addToCopy(-5);
        check(copy, 1972, 1);
        
        copy = test.monthOfYear().addToCopy(-6);
        check(copy, 1971, 12);
    }

    public void testPropertyAddWrapFieldMonth() {
        YearMonth test = new YearMonth(1972, 6);
        YearMonth copy = test.monthOfYear().addWrapFieldToCopy(4);
        check(test, 1972, 6);
        check(copy, 1972, 10);
        
        copy = test.monthOfYear().addWrapFieldToCopy(8);
        check(copy, 1972, 2);
        
        copy = test.monthOfYear().addWrapFieldToCopy(-8);
        check(copy, 1972, 10);
    }

    public void testPropertySetMonth() {
        YearMonth test = new YearMonth(1972, 6);
        YearMonth copy = test.monthOfYear().setCopy(12);
        check(test, 1972, 6);
        check(copy, 1972, 12);
        
        try {
            test.monthOfYear().setCopy(13);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.monthOfYear().setCopy(0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testPropertySetTextMonth() {
        YearMonth test = new YearMonth(1972, 6);
        YearMonth copy = test.monthOfYear().setCopy("12");
        check(test, 1972, 6);
        check(copy, 1972, 12);
        
        copy = test.monthOfYear().setCopy("December");
        check(test, 1972, 6);
        check(copy, 1972, 12);
        
        copy = test.monthOfYear().setCopy("Dec");
        check(test, 1972, 6);
        check(copy, 1972, 12);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    private void check(YearMonth test, int year, int month) {
        assertEquals(year, test.getYear());
        assertEquals(month, test.getMonthOfYear());
    }

    public void testPropertyGetYear_1_oe() {
        YearMonth test = new YearMonth(1972, 6);
        assertSame(test.getChronology().year(), test.year().getField());
    }

    public void testPropertyGetYear_2_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        assertEquals("year", test.year().getName());
    }

    public void testPropertyGetYear_3_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[year]", test.year().toString());
    }

    public void testPropertyGetYear_4_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.year().getReadablePartial());
    }

    public void testPropertyGetYear_5_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.year().getYearMonth());
    }

    public void testPropertyGetYear_6_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1972, test.year().get());
    }

    public void testPropertyGetYear_7_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1972", test.year().getAsString());
    }

    public void testPropertyGetYear_8_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1972", test.year().getAsText());
    }

    public void testPropertyGetYear_9_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1972", test.year().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetYear_10_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1972", test.year().getAsShortText());
    }

    public void testPropertyGetYear_11_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        assertEquals("1972", test.year().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetYear_12_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        assertEquals(test.getChronology().years(), test.year().getDurationField());
    }

    public void testPropertyGetYear_13_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        assertEquals(null, test.year().getRangeDurationField());
    }

    public void testPropertyGetYear_14_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        assertEquals(9, test.year().getMaximumTextLength(null));
    }

    public void testPropertyGetYear_15_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        assertEquals(9, test.year().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMaxMinValuesYear_1_oe() {
        YearMonth test = new YearMonth(1972, 6);
        assertEquals(-292275054, test.year().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesYear_2_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        assertEquals(-292275054, test.year().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesYear_3_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(292278993, test.year().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesYear_4_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(292278993, test.year().getMaximumValueOverall());
    }

    public void testPropertyCompareToYear_1_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
        assertEquals(true, test1.year().compareTo(test2) < 0);
    }

    public void testPropertyCompareToYear_2_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.year().compareTo(test1) > 0);
    }

    public void testPropertyCompareToYear_3_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.year().compareTo(test1) == 0);
    }

    public void testPropertyCompareToYear_5_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.year().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true, test1.year().compareTo(dt2) < 0);
    }

    public void testPropertyCompareToYear_6_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.year().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.year().compareTo(dt1) > 0);
    }

    public void testPropertyCompareToYear_7_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.year().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.year().compareTo(dt1) == 0);
    }

    public void testPropertyGetMonth_1_oe() {
        YearMonth test = new YearMonth(1972, 6);
        assertSame(test.getChronology().monthOfYear(), test.monthOfYear().getField());
    }

    public void testPropertyGetMonth_2_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        assertEquals("monthOfYear", test.monthOfYear().getName());
    }

    public void testPropertyGetMonth_3_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[monthOfYear]", test.monthOfYear().toString());
    }

    public void testPropertyGetMonth_4_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.monthOfYear().getReadablePartial());
    }

    public void testPropertyGetMonth_5_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.monthOfYear().getYearMonth());
    }

    public void testPropertyGetMonth_6_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.monthOfYear().get());
    }

    public void testPropertyGetMonth_7_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("6", test.monthOfYear().getAsString());
    }

    public void testPropertyGetMonth_8_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("June", test.monthOfYear().getAsText());
    }

    public void testPropertyGetMonth_9_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("juin", test.monthOfYear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMonth_10_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Jun", test.monthOfYear().getAsShortText());
    }

    public void testPropertyGetMonth_11_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        assertEquals("juin", test.monthOfYear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMonth_12_oe() {
        YearMonth test = new YearMonth(1972, 6);
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

    public void testPropertyGetMonth_13_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        assertEquals(test.getChronology().years(), test.monthOfYear().getRangeDurationField());
    }

    public void testPropertyGetMonth_14_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        assertEquals(9, test.monthOfYear().getMaximumTextLength(null));
    }

    public void testPropertyGetMonth_15_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        assertEquals(3, test.monthOfYear().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMonth_16_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        test = new YearMonth(1972, 7);
        assertEquals("juillet", test.monthOfYear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMonth_17_oe() {
        YearMonth test = new YearMonth(1972, 6);
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
        test = new YearMonth(1972, 7);
        // removed other assertion
        assertEquals("juil.", test.monthOfYear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMaxMinValuesMonth_1_oe() {
        YearMonth test = new YearMonth(1972, 6);
        assertEquals(1, test.monthOfYear().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesMonth_2_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        assertEquals(1, test.monthOfYear().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesMonth_3_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(12, test.monthOfYear().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesMonth_4_oe() {
        YearMonth test = new YearMonth(1972, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12, test.monthOfYear().getMaximumValueOverall());
    }

    public void testPropertyCompareToMonth_1_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
        assertEquals(true, test1.monthOfYear().compareTo(test2) < 0);
    }

    public void testPropertyCompareToMonth_2_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.monthOfYear().compareTo(test1) > 0);
    }

    public void testPropertyCompareToMonth_3_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().compareTo(test1) == 0);
    }

    public void testPropertyCompareToMonth_5_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
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

    public void testPropertyCompareToMonth_6_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
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

    public void testPropertyCompareToMonth_7_oe() {
        YearMonth test1 = new YearMonth(TEST_TIME1);
        YearMonth test2 = new YearMonth(TEST_TIME2);
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

    public void testPropertyEquals_1_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
        assertEquals(true, test1.monthOfYear().equals(test1.monthOfYear()));
    }

    public void testPropertyEquals_2_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(false, test1.monthOfYear().equals(test1.year()));
    }

    public void testPropertyEquals_3_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.monthOfYear().equals(test2.monthOfYear()));
    }

    public void testPropertyEquals_4_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.monthOfYear().equals(test2.year()));
    }

    public void testPropertyEquals_5_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test1.year().equals(test1.monthOfYear()));
    }

    public void testPropertyEquals_6_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test1.year().equals(test1.year()));
    }

    public void testPropertyEquals_7_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.year().equals(test2.monthOfYear()));
    }

    public void testPropertyEquals_8_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.year().equals(test2.year()));
    }

    public void testPropertyEquals_9_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test1.monthOfYear().equals(null));
    }

    public void testPropertyEquals_10_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test1.monthOfYear().equals("any"));
    }

    public void testPropertyEquals_11_oe() {
        YearMonth test1 = new YearMonth(11, 11);
        YearMonth test2 = new YearMonth(11, 12);
        YearMonth test3 = new YearMonth(11, 11, CopticChronology.getInstanceUTC());
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
        assertEquals(false, test1.monthOfYear().equals(test3.monthOfYear()));
    }

    public void testPropertyHashCode_1_oe() {
        YearMonth test1 = new YearMonth(2005, 11);
        YearMonth test2 = new YearMonth(2005, 12);
        assertEquals(true, test1.monthOfYear().hashCode() == test1.monthOfYear().hashCode());
    }

    public void testPropertyHashCode_2_oe() {
        YearMonth test1 = new YearMonth(2005, 11);
        YearMonth test2 = new YearMonth(2005, 12);
        // removed other assertion
        assertEquals(false, test1.monthOfYear().hashCode() == test2.monthOfYear().hashCode());
    }

    public void testPropertyHashCode_3_oe() {
        YearMonth test1 = new YearMonth(2005, 11);
        YearMonth test2 = new YearMonth(2005, 12);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.year().hashCode() == test1.year().hashCode());
    }

    public void testPropertyHashCode_4_oe() {
        YearMonth test1 = new YearMonth(2005, 11);
        YearMonth test2 = new YearMonth(2005, 12);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.year().hashCode() == test2.year().hashCode());
    }

    public void testPropertyEqualsHashCodeLenient_1_oe() {
        YearMonth test1 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true, test1.monthOfYear().equals(test2.monthOfYear()));
    }

    public void testPropertyEqualsHashCodeLenient_2_oe() {
        YearMonth test1 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        assertEquals(true, test2.monthOfYear().equals(test1.monthOfYear()));
    }

    public void testPropertyEqualsHashCodeLenient_3_oe() {
        YearMonth test1 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().equals(test1.monthOfYear()));
    }

    public void testPropertyEqualsHashCodeLenient_4_oe() {
        YearMonth test1 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.monthOfYear().equals(test2.monthOfYear()));
    }

    public void testPropertyEqualsHashCodeLenient_5_oe() {
        YearMonth test1 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().hashCode() == test2.monthOfYear().hashCode());
    }

    public void testPropertyEqualsHashCodeLenient_6_oe() {
        YearMonth test1 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().hashCode() == test1.monthOfYear().hashCode());
    }

    public void testPropertyEqualsHashCodeLenient_7_oe() {
        YearMonth test1 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, LenientChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.monthOfYear().hashCode() == test2.monthOfYear().hashCode());
    }

    public void testPropertyEqualsHashCodeStrict_1_oe() {
        YearMonth test1 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true, test1.monthOfYear().equals(test2.monthOfYear()));
    }

    public void testPropertyEqualsHashCodeStrict_2_oe() {
        YearMonth test1 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        assertEquals(true, test2.monthOfYear().equals(test1.monthOfYear()));
    }

    public void testPropertyEqualsHashCodeStrict_3_oe() {
        YearMonth test1 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().equals(test1.monthOfYear()));
    }

    public void testPropertyEqualsHashCodeStrict_4_oe() {
        YearMonth test1 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.monthOfYear().equals(test2.monthOfYear()));
    }

    public void testPropertyEqualsHashCodeStrict_5_oe() {
        YearMonth test1 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().hashCode() == test2.monthOfYear().hashCode());
    }

    public void testPropertyEqualsHashCodeStrict_6_oe() {
        YearMonth test1 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.monthOfYear().hashCode() == test1.monthOfYear().hashCode());
    }

    public void testPropertyEqualsHashCodeStrict_7_oe() {
        YearMonth test1 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        YearMonth test2 = new YearMonth(1970, 6, StrictChronology.getInstance(COPTIC_PARIS));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.monthOfYear().hashCode() == test2.monthOfYear().hashCode());
    }

}
