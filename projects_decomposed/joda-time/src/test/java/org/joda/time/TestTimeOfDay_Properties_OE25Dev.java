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

import java.util.Locale;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This class is a Junit unit test for TimeOfDay.
 *
 * @author Stephen Colebourne
 */
@SuppressWarnings("deprecation")
public class TestTimeOfDay_Properties_OE25Dev extends TestCase {

    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    
    private long TEST_TIME_NOW =
            10L * DateTimeConstants.MILLIS_PER_HOUR
            + 20L * DateTimeConstants.MILLIS_PER_MINUTE
            + 30L * DateTimeConstants.MILLIS_PER_SECOND
            + 40L;
            
    private long TEST_TIME1 =
        1L * DateTimeConstants.MILLIS_PER_HOUR
        + 2L * DateTimeConstants.MILLIS_PER_MINUTE
        + 3L * DateTimeConstants.MILLIS_PER_SECOND
        + 4L;
        
    private long TEST_TIME2 =
        1L * DateTimeConstants.MILLIS_PER_DAY
        + 5L * DateTimeConstants.MILLIS_PER_HOUR
        + 6L * DateTimeConstants.MILLIS_PER_MINUTE
        + 7L * DateTimeConstants.MILLIS_PER_SECOND
        + 8L;
        
    private DateTimeZone zone = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestTimeOfDay_Properties_OE25Dev_OE25Dev.class);
    }

    public TestTimeOfDay_Properties_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(LONDON);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        zone = null;
    }

    //-----------------------------------------------------------------------

    public void testPropertyAddHour() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.hourOfDay().addToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 19, 20, 30, 40);
        
        copy = test.hourOfDay().addToCopy(0);
        check(copy, 10, 20, 30, 40);
        
        copy = test.hourOfDay().addToCopy(13);
        check(copy, 23, 20, 30, 40);
        
        copy = test.hourOfDay().addToCopy(14);
        check(copy, 0, 20, 30, 40);
        
        copy = test.hourOfDay().addToCopy(-10);
        check(copy, 0, 20, 30, 40);
        
        copy = test.hourOfDay().addToCopy(-11);
        check(copy, 23, 20, 30, 40);
    }

    public void testPropertyAddNoWrapHour() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.hourOfDay().addNoWrapToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 19, 20, 30, 40);
        
        copy = test.hourOfDay().addNoWrapToCopy(0);
        check(copy, 10, 20, 30, 40);
        
        copy = test.hourOfDay().addNoWrapToCopy(13);
        check(copy, 23, 20, 30, 40);
        
        try {
            test.hourOfDay().addNoWrapToCopy(14);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
        
        copy = test.hourOfDay().addNoWrapToCopy(-10);
        check(copy, 0, 20, 30, 40);
        
        try {
            test.hourOfDay().addNoWrapToCopy(-11);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
    }

    public void testPropertyAddWrapFieldHour() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.hourOfDay().addWrapFieldToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 19, 20, 30, 40);
        
        copy = test.hourOfDay().addWrapFieldToCopy(0);
        check(copy, 10, 20, 30, 40);
        
        copy = test.hourOfDay().addWrapFieldToCopy(18);
        check(copy, 4, 20, 30, 40);
        
        copy = test.hourOfDay().addWrapFieldToCopy(-15);
        check(copy, 19, 20, 30, 40);
    }

    public void testPropertySetHour() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.hourOfDay().setCopy(12);
        check(test, 10, 20, 30, 40);
        check(copy, 12, 20, 30, 40);
        
        try {
            test.hourOfDay().setCopy(24);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.hourOfDay().setCopy(-1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testPropertySetTextHour() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.hourOfDay().setCopy("12");
        check(test, 10, 20, 30, 40);
        check(copy, 12, 20, 30, 40);
    }

    public void testPropertyWithMaximumValueHour() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.hourOfDay().withMaximumValue();
        check(test, 10, 20, 30, 40);
        check(copy, 23, 20, 30, 40);
    }

    public void testPropertyWithMinimumValueHour() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.hourOfDay().withMinimumValue();
        check(test, 10, 20, 30, 40);
        check(copy, 0, 20, 30, 40);
    }

    //-----------------------------------------------------------------------

    public void testPropertyAddMinute() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.minuteOfHour().addToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 29, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(39);
        check(copy, 10, 59, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(40);
        check(copy, 11, 0, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(1 * 60 + 45);
        check(copy, 12, 5, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(13 * 60 + 39);
        check(copy, 23, 59, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(13 * 60 + 40);
        check(copy, 0, 0, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(-9);
        check(copy, 10, 11, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(-19);
        check(copy, 10, 1, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(-20);
        check(copy, 10, 0, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(-21);
        check(copy, 9, 59, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(-(10 * 60 + 20));
        check(copy, 0, 0, 30, 40);
        
        copy = test.minuteOfHour().addToCopy(-(10 * 60 + 21));
        check(copy, 23, 59, 30, 40);
    }

    public void testPropertyAddNoWrapMinute() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.minuteOfHour().addNoWrapToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 29, 30, 40);
        
        copy = test.minuteOfHour().addNoWrapToCopy(39);
        check(copy, 10, 59, 30, 40);
        
        copy = test.minuteOfHour().addNoWrapToCopy(40);
        check(copy, 11, 0, 30, 40);
        
        copy = test.minuteOfHour().addNoWrapToCopy(1 * 60 + 45);
        check(copy, 12, 5, 30, 40);
        
        copy = test.minuteOfHour().addNoWrapToCopy(13 * 60 + 39);
        check(copy, 23, 59, 30, 40);
        
        try {
            test.minuteOfHour().addNoWrapToCopy(13 * 60 + 40);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
        
        copy = test.minuteOfHour().addNoWrapToCopy(-9);
        check(copy, 10, 11, 30, 40);
        
        copy = test.minuteOfHour().addNoWrapToCopy(-19);
        check(copy, 10, 1, 30, 40);
        
        copy = test.minuteOfHour().addNoWrapToCopy(-20);
        check(copy, 10, 0, 30, 40);
        
        copy = test.minuteOfHour().addNoWrapToCopy(-21);
        check(copy, 9, 59, 30, 40);
        
        copy = test.minuteOfHour().addNoWrapToCopy(-(10 * 60 + 20));
        check(copy, 0, 0, 30, 40);
        
        try {
            test.minuteOfHour().addNoWrapToCopy(-(10 * 60 + 21));
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
    }

    public void testPropertyAddWrapFieldMinute() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.minuteOfHour().addWrapFieldToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 29, 30, 40);
        
        copy = test.minuteOfHour().addWrapFieldToCopy(49);
        check(copy, 10, 9, 30, 40);
        
        copy = test.minuteOfHour().addWrapFieldToCopy(-47);
        check(copy, 10, 33, 30, 40);
    }

    public void testPropertySetMinute() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.minuteOfHour().setCopy(12);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 12, 30, 40);
        
        try {
            test.minuteOfHour().setCopy(60);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.minuteOfHour().setCopy(-1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testPropertySetTextMinute() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.minuteOfHour().setCopy("12");
        check(test, 10, 20, 30, 40);
        check(copy, 10, 12, 30, 40);
    }

    //-----------------------------------------------------------------------

    public void testPropertyAddSecond() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.secondOfMinute().addToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 39, 40);
        
        copy = test.secondOfMinute().addToCopy(29);
        check(copy, 10, 20, 59, 40);
        
        copy = test.secondOfMinute().addToCopy(30);
        check(copy, 10, 21, 0, 40);
        
        copy = test.secondOfMinute().addToCopy(39 * 60 + 29);
        check(copy, 10, 59, 59, 40);
        
        copy = test.secondOfMinute().addToCopy(39 * 60 + 30);
        check(copy, 11, 0, 0, 40);
        
        copy = test.secondOfMinute().addToCopy(13 * 60 * 60 + 39 * 60 + 30);
        check(copy, 0, 0, 0, 40);
        
        copy = test.secondOfMinute().addToCopy(-9);
        check(copy, 10, 20, 21, 40);
        
        copy = test.secondOfMinute().addToCopy(-30);
        check(copy, 10, 20, 0, 40);
        
        copy = test.secondOfMinute().addToCopy(-31);
        check(copy, 10, 19, 59, 40);
        
        copy = test.secondOfMinute().addToCopy(-(10 * 60 * 60 + 20 * 60 + 30));
        check(copy, 0, 0, 0, 40);
        
        copy = test.secondOfMinute().addToCopy(-(10 * 60 * 60 + 20 * 60 + 31));
        check(copy, 23, 59, 59, 40);
    }

    public void testPropertyAddNoWrapSecond() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.secondOfMinute().addNoWrapToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 39, 40);
        
        copy = test.secondOfMinute().addNoWrapToCopy(29);
        check(copy, 10, 20, 59, 40);
        
        copy = test.secondOfMinute().addNoWrapToCopy(30);
        check(copy, 10, 21, 0, 40);
        
        copy = test.secondOfMinute().addNoWrapToCopy(39 * 60 + 29);
        check(copy, 10, 59, 59, 40);
        
        copy = test.secondOfMinute().addNoWrapToCopy(39 * 60 + 30);
        check(copy, 11, 0, 0, 40);
        
        try {
            test.secondOfMinute().addNoWrapToCopy(13 * 60 * 60 + 39 * 60 + 30);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
        
        copy = test.secondOfMinute().addNoWrapToCopy(-9);
        check(copy, 10, 20, 21, 40);
        
        copy = test.secondOfMinute().addNoWrapToCopy(-30);
        check(copy, 10, 20, 0, 40);
        
        copy = test.secondOfMinute().addNoWrapToCopy(-31);
        check(copy, 10, 19, 59, 40);
        
        copy = test.secondOfMinute().addNoWrapToCopy(-(10 * 60 * 60 + 20 * 60 + 30));
        check(copy, 0, 0, 0, 40);
        
        try {
            test.secondOfMinute().addNoWrapToCopy(-(10 * 60 * 60 + 20 * 60 + 31));
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
    }

    public void testPropertyAddWrapFieldSecond() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.secondOfMinute().addWrapFieldToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 39, 40);
        
        copy = test.secondOfMinute().addWrapFieldToCopy(49);
        check(copy, 10, 20, 19, 40);
        
        copy = test.secondOfMinute().addWrapFieldToCopy(-47);
        check(copy, 10, 20, 43, 40);
    }

    public void testPropertySetSecond() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.secondOfMinute().setCopy(12);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 12, 40);
        
        try {
            test.secondOfMinute().setCopy(60);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.secondOfMinute().setCopy(-1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testPropertySetTextSecond() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.secondOfMinute().setCopy("12");
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 12, 40);
    }

    //-----------------------------------------------------------------------

    public void testPropertyAddMilli() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.millisOfSecond().addToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 30, 49);
        
        copy = test.millisOfSecond().addToCopy(959);
        check(copy, 10, 20, 30, 999);
        
        copy = test.millisOfSecond().addToCopy(960);
        check(copy, 10, 20, 31, 0);
        
        copy = test.millisOfSecond().addToCopy(13 * 60 * 60 * 1000 + 39 * 60 * 1000 + 29 * 1000 + 959);
        check(copy, 23, 59, 59, 999);
        
        copy = test.millisOfSecond().addToCopy(13 * 60 * 60 * 1000 + 39 * 60 * 1000 + 29 * 1000 + 960);
        check(copy, 0, 0, 0, 0);
        
        copy = test.millisOfSecond().addToCopy(-9);
        check(copy, 10, 20, 30, 31);
        
        copy = test.millisOfSecond().addToCopy(-40);
        check(copy, 10, 20, 30, 0);
        
        copy = test.millisOfSecond().addToCopy(-41);
        check(copy, 10, 20, 29, 999);
        
        copy = test.millisOfSecond().addToCopy(-(10 * 60 * 60 * 1000 + 20 * 60 * 1000 + 30 * 1000 + 40));
        check(copy, 0, 0, 0, 0);
        
        copy = test.millisOfSecond().addToCopy(-(10 * 60 * 60 * 1000 + 20 * 60 * 1000 + 30 * 1000 + 41));
        check(copy, 23, 59, 59, 999);
    }

    public void testPropertyAddNoWrapMilli() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.millisOfSecond().addNoWrapToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 30, 49);
        
        copy = test.millisOfSecond().addNoWrapToCopy(959);
        check(copy, 10, 20, 30, 999);
        
        copy = test.millisOfSecond().addNoWrapToCopy(960);
        check(copy, 10, 20, 31, 0);
        
        copy = test.millisOfSecond().addNoWrapToCopy(13 * 60 * 60 * 1000 + 39 * 60 * 1000 + 29 * 1000 + 959);
        check(copy, 23, 59, 59, 999);
        
        try {
            test.millisOfSecond().addNoWrapToCopy(13 * 60 * 60 * 1000 + 39 * 60 * 1000 + 29 * 1000 + 960);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
        
        copy = test.millisOfSecond().addNoWrapToCopy(-9);
        check(copy, 10, 20, 30, 31);
        
        copy = test.millisOfSecond().addNoWrapToCopy(-40);
        check(copy, 10, 20, 30, 0);
        
        copy = test.millisOfSecond().addNoWrapToCopy(-41);
        check(copy, 10, 20, 29, 999);
        
        copy = test.millisOfSecond().addNoWrapToCopy(-(10 * 60 * 60 * 1000 + 20 * 60 * 1000 + 30 * 1000 + 40));
        check(copy, 0, 0, 0, 0);
        
        try {
            test.millisOfSecond().addNoWrapToCopy(-(10 * 60 * 60 * 1000 + 20 * 60 * 1000 + 30 * 1000 + 41));
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
    }

    public void testPropertyAddWrapFieldMilli() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.millisOfSecond().addWrapFieldToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 30, 49);
        
        copy = test.millisOfSecond().addWrapFieldToCopy(995);
        check(copy, 10, 20, 30, 35);
        
        copy = test.millisOfSecond().addWrapFieldToCopy(-47);
        check(copy, 10, 20, 30, 993);
    }

    public void testPropertySetMilli() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.millisOfSecond().setCopy(12);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 30, 12);
        
        try {
            test.millisOfSecond().setCopy(1000);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.millisOfSecond().setCopy(-1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testPropertySetTextMilli() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        TimeOfDay copy = test.millisOfSecond().setCopy("12");
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 30, 12);
    }

    //-----------------------------------------------------------------------
    private void check(TimeOfDay test, int hour, int min, int sec, int milli) {
        assertEquals(hour, test.getHourOfDay());
        assertEquals(min, test.getMinuteOfHour());
        assertEquals(sec, test.getSecondOfMinute());
        assertEquals(milli, test.getMillisOfSecond());
    }

    public void testPropertyGetHour_1_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertSame(test.getChronology().hourOfDay(), test.hourOfDay().getField());
    }

    public void testPropertyGetHour_2_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        assertEquals("hourOfDay", test.hourOfDay().getName());
    }

    public void testPropertyGetHour_3_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[hourOfDay]", test.hourOfDay().toString());
    }

    public void testPropertyGetHour_4_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.hourOfDay().getReadablePartial());
    }

    public void testPropertyGetHour_5_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.hourOfDay().getTimeOfDay());
    }

    public void testPropertyGetHour_6_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, test.hourOfDay().get());
    }

    public void testPropertyGetHour_7_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("10", test.hourOfDay().getAsString());
    }

    public void testPropertyGetHour_8_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("10", test.hourOfDay().getAsText());
    }

    public void testPropertyGetHour_9_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("10", test.hourOfDay().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetHour_10_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("10", test.hourOfDay().getAsShortText());
    }

    public void testPropertyGetHour_11_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals("10", test.hourOfDay().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetHour_12_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(test.getChronology().hours(), test.hourOfDay().getDurationField());
    }

    public void testPropertyGetHour_13_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(test.getChronology().days(), test.hourOfDay().getRangeDurationField());
    }

    public void testPropertyGetHour_14_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(2, test.hourOfDay().getMaximumTextLength(null));
    }

    public void testPropertyGetHour_15_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(2, test.hourOfDay().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMaxMinValuesHour_1_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertEquals(0, test.hourOfDay().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesHour_2_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        assertEquals(0, test.hourOfDay().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesHour_3_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(23, test.hourOfDay().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesHour_4_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(23, test.hourOfDay().getMaximumValueOverall());
    }

    public void testPropertyCompareToHour_1_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        assertEquals(true, test1.hourOfDay().compareTo(test2) < 0);
    }

    public void testPropertyCompareToHour_2_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.hourOfDay().compareTo(test1) > 0);
    }

    public void testPropertyCompareToHour_3_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hourOfDay().compareTo(test1) == 0);
    }

    public void testPropertyCompareToHour_5_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.hourOfDay().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true, test1.hourOfDay().compareTo(dt2) < 0);
    }

    public void testPropertyCompareToHour_6_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.hourOfDay().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.hourOfDay().compareTo(dt1) > 0);
    }

    public void testPropertyCompareToHour_7_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.hourOfDay().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hourOfDay().compareTo(dt1) == 0);
    }

    public void testPropertyGetMinute_1_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertSame(test.getChronology().minuteOfHour(), test.minuteOfHour().getField());
    }

    public void testPropertyGetMinute_2_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        assertEquals("minuteOfHour", test.minuteOfHour().getName());
    }

    public void testPropertyGetMinute_3_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[minuteOfHour]", test.minuteOfHour().toString());
    }

    public void testPropertyGetMinute_4_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.minuteOfHour().getReadablePartial());
    }

    public void testPropertyGetMinute_5_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.minuteOfHour().getTimeOfDay());
    }

    public void testPropertyGetMinute_6_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(20, test.minuteOfHour().get());
    }

    public void testPropertyGetMinute_7_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("20", test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinute_8_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("20", test.minuteOfHour().getAsText());
    }

    public void testPropertyGetMinute_9_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("20", test.minuteOfHour().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMinute_10_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("20", test.minuteOfHour().getAsShortText());
    }

    public void testPropertyGetMinute_11_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals("20", test.minuteOfHour().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMinute_12_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(test.getChronology().minutes(), test.minuteOfHour().getDurationField());
    }

    public void testPropertyGetMinute_13_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(test.getChronology().hours(), test.minuteOfHour().getRangeDurationField());
    }

    public void testPropertyGetMinute_14_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(2, test.minuteOfHour().getMaximumTextLength(null));
    }

    public void testPropertyGetMinute_15_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(2, test.minuteOfHour().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMaxMinValuesMinute_1_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertEquals(0, test.minuteOfHour().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesMinute_2_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        assertEquals(0, test.minuteOfHour().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesMinute_3_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(59, test.minuteOfHour().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesMinute_4_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(59, test.minuteOfHour().getMaximumValueOverall());
    }

    public void testPropertyCompareToMinute_1_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        assertEquals(true, test1.minuteOfHour().compareTo(test2) < 0);
    }

    public void testPropertyCompareToMinute_2_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.minuteOfHour().compareTo(test1) > 0);
    }

    public void testPropertyCompareToMinute_3_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.minuteOfHour().compareTo(test1) == 0);
    }

    public void testPropertyCompareToMinute_5_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.minuteOfHour().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true, test1.minuteOfHour().compareTo(dt2) < 0);
    }

    public void testPropertyCompareToMinute_6_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.minuteOfHour().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.minuteOfHour().compareTo(dt1) > 0);
    }

    public void testPropertyCompareToMinute_7_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.minuteOfHour().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.minuteOfHour().compareTo(dt1) == 0);
    }

    public void testPropertyGetSecond_1_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertSame(test.getChronology().secondOfMinute(), test.secondOfMinute().getField());
    }

    public void testPropertyGetSecond_2_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        assertEquals("secondOfMinute", test.secondOfMinute().getName());
    }

    public void testPropertyGetSecond_3_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[secondOfMinute]", test.secondOfMinute().toString());
    }

    public void testPropertyGetSecond_4_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.secondOfMinute().getReadablePartial());
    }

    public void testPropertyGetSecond_5_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.secondOfMinute().getTimeOfDay());
    }

    public void testPropertyGetSecond_6_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(30, test.secondOfMinute().get());
    }

    public void testPropertyGetSecond_7_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("30", test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecond_8_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("30", test.secondOfMinute().getAsText());
    }

    public void testPropertyGetSecond_9_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("30", test.secondOfMinute().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetSecond_10_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("30", test.secondOfMinute().getAsShortText());
    }

    public void testPropertyGetSecond_11_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals("30", test.secondOfMinute().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetSecond_12_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(test.getChronology().seconds(), test.secondOfMinute().getDurationField());
    }

    public void testPropertyGetSecond_13_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(test.getChronology().minutes(), test.secondOfMinute().getRangeDurationField());
    }

    public void testPropertyGetSecond_14_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(2, test.secondOfMinute().getMaximumTextLength(null));
    }

    public void testPropertyGetSecond_15_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(2, test.secondOfMinute().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMaxMinValuesSecond_1_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertEquals(0, test.secondOfMinute().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesSecond_2_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        assertEquals(0, test.secondOfMinute().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesSecond_3_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(59, test.secondOfMinute().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesSecond_4_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(59, test.secondOfMinute().getMaximumValueOverall());
    }

    public void testPropertyCompareToSecond_1_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        assertEquals(true, test1.secondOfMinute().compareTo(test2) < 0);
    }

    public void testPropertyCompareToSecond_2_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.secondOfMinute().compareTo(test1) > 0);
    }

    public void testPropertyCompareToSecond_3_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.secondOfMinute().compareTo(test1) == 0);
    }

    public void testPropertyCompareToSecond_5_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.secondOfMinute().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true, test1.secondOfMinute().compareTo(dt2) < 0);
    }

    public void testPropertyCompareToSecond_6_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.secondOfMinute().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.secondOfMinute().compareTo(dt1) > 0);
    }

    public void testPropertyCompareToSecond_7_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.secondOfMinute().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.secondOfMinute().compareTo(dt1) == 0);
    }

    public void testPropertyGetMilli_1_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertSame(test.getChronology().millisOfSecond(), test.millisOfSecond().getField());
    }

    public void testPropertyGetMilli_2_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        assertEquals("millisOfSecond", test.millisOfSecond().getName());
    }

    public void testPropertyGetMilli_3_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[millisOfSecond]", test.millisOfSecond().toString());
    }

    public void testPropertyGetMilli_4_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.millisOfSecond().getReadablePartial());
    }

    public void testPropertyGetMilli_5_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.millisOfSecond().getTimeOfDay());
    }

    public void testPropertyGetMilli_6_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(40, test.millisOfSecond().get());
    }

    public void testPropertyGetMilli_7_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("40", test.millisOfSecond().getAsString());
    }

    public void testPropertyGetMilli_8_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("40", test.millisOfSecond().getAsText());
    }

    public void testPropertyGetMilli_9_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("40", test.millisOfSecond().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMilli_10_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("40", test.millisOfSecond().getAsShortText());
    }

    public void testPropertyGetMilli_11_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals("40", test.millisOfSecond().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMilli_12_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(test.getChronology().millis(), test.millisOfSecond().getDurationField());
    }

    public void testPropertyGetMilli_13_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(test.getChronology().seconds(), test.millisOfSecond().getRangeDurationField());
    }

    public void testPropertyGetMilli_14_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(3, test.millisOfSecond().getMaximumTextLength(null));
    }

    public void testPropertyGetMilli_15_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
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
        assertEquals(3, test.millisOfSecond().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMaxMinValuesMilli_1_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        assertEquals(0, test.millisOfSecond().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesMilli_2_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        assertEquals(0, test.millisOfSecond().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesMilli_3_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        assertEquals(999, test.millisOfSecond().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesMilli_4_oe() {
        TimeOfDay test = new TimeOfDay(10, 20, 30, 40);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(999, test.millisOfSecond().getMaximumValueOverall());
    }

    public void testPropertyCompareToMilli_1_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        assertEquals(true, test1.millisOfSecond().compareTo(test2) < 0);
    }

    public void testPropertyCompareToMilli_2_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.millisOfSecond().compareTo(test1) > 0);
    }

    public void testPropertyCompareToMilli_3_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.millisOfSecond().compareTo(test1) == 0);
    }

    public void testPropertyCompareToMilli_5_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.millisOfSecond().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true, test1.millisOfSecond().compareTo(dt2) < 0);
    }

    public void testPropertyCompareToMilli_6_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.millisOfSecond().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.millisOfSecond().compareTo(dt1) > 0);
    }

    public void testPropertyCompareToMilli_7_oe() {
        TimeOfDay test1 = new TimeOfDay(TEST_TIME1);
        TimeOfDay test2 = new TimeOfDay(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            test1.millisOfSecond().compareTo((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.millisOfSecond().compareTo(dt1) == 0);
    }

}
