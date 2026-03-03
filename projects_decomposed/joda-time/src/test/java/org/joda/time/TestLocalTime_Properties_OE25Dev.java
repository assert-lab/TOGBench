/*
 *  Copyright 2001-2006 Stephen Colebourne
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
public class TestLocalTime_Properties_OE25Dev extends TestCase {

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
        return new TestSuite(TestLocalTime_Properties_OE25Dev.class);
    }

    public TestLocalTime_Properties_OE25Dev(String name) {
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

    public void testPropertyRoundHour() {
        LocalTime test = new LocalTime(10, 20);
        check(test.hourOfDay().roundCeilingCopy(), 11, 0, 0, 0);
        check(test.hourOfDay().roundFloorCopy(), 10, 0, 0, 0);
        check(test.hourOfDay().roundHalfCeilingCopy(), 10, 0, 0, 0);
        check(test.hourOfDay().roundHalfFloorCopy(), 10, 0, 0, 0);
        check(test.hourOfDay().roundHalfEvenCopy(), 10, 0, 0, 0);
        
        test = new LocalTime(10, 40);
        check(test.hourOfDay().roundCeilingCopy(), 11, 0, 0, 0);
        check(test.hourOfDay().roundFloorCopy(), 10, 0, 0, 0);
        check(test.hourOfDay().roundHalfCeilingCopy(), 11, 0, 0, 0);
        check(test.hourOfDay().roundHalfFloorCopy(), 11, 0, 0, 0);
        check(test.hourOfDay().roundHalfEvenCopy(), 11, 0, 0, 0);
        
        test = new LocalTime(10, 30);
        check(test.hourOfDay().roundCeilingCopy(), 11, 0, 0, 0);
        check(test.hourOfDay().roundFloorCopy(), 10, 0, 0, 0);
        check(test.hourOfDay().roundHalfCeilingCopy(), 11, 0, 0, 0);
        check(test.hourOfDay().roundHalfFloorCopy(), 10, 0, 0, 0);
        check(test.hourOfDay().roundHalfEvenCopy(), 10, 0, 0, 0);
        
        test = new LocalTime(11, 30);
        check(test.hourOfDay().roundCeilingCopy(), 12, 0, 0, 0);
        check(test.hourOfDay().roundFloorCopy(), 11, 0, 0, 0);
        check(test.hourOfDay().roundHalfCeilingCopy(), 12, 0, 0, 0);
        check(test.hourOfDay().roundHalfFloorCopy(), 11, 0, 0, 0);
        check(test.hourOfDay().roundHalfEvenCopy(), 12, 0, 0, 0);
    }

    public void testPropertyWithMaxMinValueHour() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        check(test.hourOfDay().withMaximumValue(), 23, 20, 30, 40);
        check(test.hourOfDay().withMinimumValue(), 0, 20, 30, 40);
    }

    public void testPropertyPlusHour() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.hourOfDay().addCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 19, 20, 30, 40);
        
        copy = test.hourOfDay().addCopy(0);
        check(copy, 10, 20, 30, 40);
        
        copy = test.hourOfDay().addCopy(13);
        check(copy, 23, 20, 30, 40);
        
        copy = test.hourOfDay().addCopy(14);
        check(copy, 0, 20, 30, 40);
        
        copy = test.hourOfDay().addCopy(-10);
        check(copy, 0, 20, 30, 40);
        
        copy = test.hourOfDay().addCopy(-11);
        check(copy, 23, 20, 30, 40);
    }

    public void testPropertyPlusNoWrapHour() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.hourOfDay().addNoWrapToCopy(9);
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

    public void testPropertyPlusWrapFieldHour() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.hourOfDay().addWrapFieldToCopy(9);
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
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.hourOfDay().setCopy(12);
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
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.hourOfDay().setCopy("12");
        check(test, 10, 20, 30, 40);
        check(copy, 12, 20, 30, 40);
    }

    public void testPropertyWithMaximumValueHour() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.hourOfDay().withMaximumValue();
        check(test, 10, 20, 30, 40);
        check(copy, 23, 20, 30, 40);
    }

    public void testPropertyWithMinimumValueHour() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.hourOfDay().withMinimumValue();
        check(test, 10, 20, 30, 40);
        check(copy, 0, 20, 30, 40);
    }

    //-----------------------------------------------------------------------

    public void testPropertyWithMaxMinValueMinute() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        check(test.minuteOfHour().withMaximumValue(), 10, 59, 30, 40);
        check(test.minuteOfHour().withMinimumValue(), 10, 0, 30, 40);
    }

    public void testPropertyPlusMinute() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.minuteOfHour().addCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 29, 30, 40);
        
        copy = test.minuteOfHour().addCopy(39);
        check(copy, 10, 59, 30, 40);
        
        copy = test.minuteOfHour().addCopy(40);
        check(copy, 11, 0, 30, 40);
        
        copy = test.minuteOfHour().addCopy(1 * 60 + 45);
        check(copy, 12, 5, 30, 40);
        
        copy = test.minuteOfHour().addCopy(13 * 60 + 39);
        check(copy, 23, 59, 30, 40);
        
        copy = test.minuteOfHour().addCopy(13 * 60 + 40);
        check(copy, 0, 0, 30, 40);
        
        copy = test.minuteOfHour().addCopy(-9);
        check(copy, 10, 11, 30, 40);
        
        copy = test.minuteOfHour().addCopy(-19);
        check(copy, 10, 1, 30, 40);
        
        copy = test.minuteOfHour().addCopy(-20);
        check(copy, 10, 0, 30, 40);
        
        copy = test.minuteOfHour().addCopy(-21);
        check(copy, 9, 59, 30, 40);
        
        copy = test.minuteOfHour().addCopy(-(10 * 60 + 20));
        check(copy, 0, 0, 30, 40);
        
        copy = test.minuteOfHour().addCopy(-(10 * 60 + 21));
        check(copy, 23, 59, 30, 40);
    }

    public void testPropertyPlusNoWrapMinute() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.minuteOfHour().addNoWrapToCopy(9);
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

    public void testPropertyPlusWrapFieldMinute() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.minuteOfHour().addWrapFieldToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 29, 30, 40);
        
        copy = test.minuteOfHour().addWrapFieldToCopy(49);
        check(copy, 10, 9, 30, 40);
        
        copy = test.minuteOfHour().addWrapFieldToCopy(-47);
        check(copy, 10, 33, 30, 40);
    }

    public void testPropertySetMinute() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.minuteOfHour().setCopy(12);
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
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.minuteOfHour().setCopy("12");
        check(test, 10, 20, 30, 40);
        check(copy, 10, 12, 30, 40);
    }

    //-----------------------------------------------------------------------

    public void testPropertyWithMaxMinValueSecond() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        check(test.secondOfMinute().withMaximumValue(), 10, 20, 59, 40);
        check(test.secondOfMinute().withMinimumValue(), 10, 20, 0, 40);
    }

    public void testPropertyPlusSecond() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.secondOfMinute().addCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 39, 40);
        
        copy = test.secondOfMinute().addCopy(29);
        check(copy, 10, 20, 59, 40);
        
        copy = test.secondOfMinute().addCopy(30);
        check(copy, 10, 21, 0, 40);
        
        copy = test.secondOfMinute().addCopy(39 * 60 + 29);
        check(copy, 10, 59, 59, 40);
        
        copy = test.secondOfMinute().addCopy(39 * 60 + 30);
        check(copy, 11, 0, 0, 40);
        
        copy = test.secondOfMinute().addCopy(13 * 60 * 60 + 39 * 60 + 30);
        check(copy, 0, 0, 0, 40);
        
        copy = test.secondOfMinute().addCopy(-9);
        check(copy, 10, 20, 21, 40);
        
        copy = test.secondOfMinute().addCopy(-30);
        check(copy, 10, 20, 0, 40);
        
        copy = test.secondOfMinute().addCopy(-31);
        check(copy, 10, 19, 59, 40);
        
        copy = test.secondOfMinute().addCopy(-(10 * 60 * 60 + 20 * 60 + 30));
        check(copy, 0, 0, 0, 40);
        
        copy = test.secondOfMinute().addCopy(-(10 * 60 * 60 + 20 * 60 + 31));
        check(copy, 23, 59, 59, 40);
    }

    public void testPropertyPlusNoWrapSecond() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.secondOfMinute().addNoWrapToCopy(9);
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

    public void testPropertyPlusWrapFieldSecond() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.secondOfMinute().addWrapFieldToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 39, 40);
        
        copy = test.secondOfMinute().addWrapFieldToCopy(49);
        check(copy, 10, 20, 19, 40);
        
        copy = test.secondOfMinute().addWrapFieldToCopy(-47);
        check(copy, 10, 20, 43, 40);
    }

    public void testPropertySetSecond() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.secondOfMinute().setCopy(12);
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
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.secondOfMinute().setCopy("12");
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 12, 40);
    }

    //-----------------------------------------------------------------------

    public void testPropertyWithMaxMinValueMilli() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        check(test.millisOfSecond().withMaximumValue(), 10, 20, 30, 999);
        check(test.millisOfSecond().withMinimumValue(), 10, 20, 30, 0);
    }

    public void testPropertyPlusMilli() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.millisOfSecond().addCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 30, 49);
        
        copy = test.millisOfSecond().addCopy(959);
        check(copy, 10, 20, 30, 999);
        
        copy = test.millisOfSecond().addCopy(960);
        check(copy, 10, 20, 31, 0);
        
        copy = test.millisOfSecond().addCopy(13 * 60 * 60 * 1000 + 39 * 60 * 1000 + 29 * 1000 + 959);
        check(copy, 23, 59, 59, 999);
        
        copy = test.millisOfSecond().addCopy(13 * 60 * 60 * 1000 + 39 * 60 * 1000 + 29 * 1000 + 960);
        check(copy, 0, 0, 0, 0);
        
        copy = test.millisOfSecond().addCopy(-9);
        check(copy, 10, 20, 30, 31);
        
        copy = test.millisOfSecond().addCopy(-40);
        check(copy, 10, 20, 30, 0);
        
        copy = test.millisOfSecond().addCopy(-41);
        check(copy, 10, 20, 29, 999);
        
        copy = test.millisOfSecond().addCopy(-(10 * 60 * 60 * 1000 + 20 * 60 * 1000 + 30 * 1000 + 40));
        check(copy, 0, 0, 0, 0);
        
        copy = test.millisOfSecond().addCopy(-(10 * 60 * 60 * 1000 + 20 * 60 * 1000 + 30 * 1000 + 41));
        check(copy, 23, 59, 59, 999);
    }

    public void testPropertyPlusNoWrapMilli() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.millisOfSecond().addNoWrapToCopy(9);
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

    public void testPropertyPlusWrapFieldMilli() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.millisOfSecond().addWrapFieldToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 30, 49);
        
        copy = test.millisOfSecond().addWrapFieldToCopy(995);
        check(copy, 10, 20, 30, 35);
        
        copy = test.millisOfSecond().addWrapFieldToCopy(-47);
        check(copy, 10, 20, 30, 993);
    }

    public void testPropertySetMilli() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.millisOfSecond().setCopy(12);
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
        LocalTime test = new LocalTime(10, 20, 30, 40);
        LocalTime copy = test.millisOfSecond().setCopy("12");
        check(test, 10, 20, 30, 40);
        check(copy, 10, 20, 30, 12);
    }

    //-----------------------------------------------------------------------
    private void check(LocalTime test, int hour, int min, int sec, int milli) {
        assertEquals(hour,test.getHourOfDay());
        assertEquals(min,test.getMinuteOfHour());
        assertEquals(sec,test.getSecondOfMinute());
        assertEquals(milli,test.getMillisOfSecond());
    }

    public void testPropertyGetHour_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertSame(test.getChronology().hourOfDay(),test.hourOfDay().getField());
    }

    public void testPropertyGetHour_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("hourOfDay",test.hourOfDay().getName());
    }

    public void testPropertyGetHour_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("Property[hourOfDay]",test.hourOfDay().toString());
    }

    public void testPropertyGetHour_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertSame(test,test.hourOfDay().getLocalTime());
    }

    public void testPropertyGetHour_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(10,test.hourOfDay().get());
    }

    public void testPropertyGetHour_6_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("10",test.hourOfDay().getAsString());
    }

    public void testPropertyGetHour_7_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("10",test.hourOfDay().getAsText());
    }

    public void testPropertyGetHour_8_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("10",test.hourOfDay().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetHour_9_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("10",test.hourOfDay().getAsShortText());
    }

    public void testPropertyGetHour_10_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("10",test.hourOfDay().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetHour_11_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(test.getChronology().hours(),test.hourOfDay().getDurationField());
    }

    public void testPropertyGetHour_12_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(test.getChronology().days(),test.hourOfDay().getRangeDurationField());
    }

    public void testPropertyGetHour_13_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(2,test.hourOfDay().getMaximumTextLength(null));
    }

    public void testPropertyGetHour_14_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(2,test.hourOfDay().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMaxMinValuesHour_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(0,test.hourOfDay().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesHour_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(0,test.hourOfDay().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesHour_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(23,test.hourOfDay().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesHour_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(23,test.hourOfDay().getMaximumValueOverall());
    }

    public void testPropertyCompareToHour_1_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test1.hourOfDay().compareTo(test2)< 0);
    }

    public void testPropertyCompareToHour_2_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test2.hourOfDay().compareTo(test1)> 0);
    }

    public void testPropertyCompareToHour_3_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test1.hourOfDay().compareTo(test1)== 0);
    }

    public void testPropertyCompareToHour_5_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.hourOfDay().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test1.hourOfDay().compareTo(dt2)< 0);
    }

    public void testPropertyCompareToHour_6_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.hourOfDay().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test2.hourOfDay().compareTo(dt1)> 0);
    }

    public void testPropertyCompareToHour_7_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.hourOfDay().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test1.hourOfDay().compareTo(dt1)== 0);
    }

    public void testPropertyGetMinute_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertSame(test.getChronology().minuteOfHour(),test.minuteOfHour().getField());
    }

    public void testPropertyGetMinute_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("minuteOfHour",test.minuteOfHour().getName());
    }

    public void testPropertyGetMinute_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("Property[minuteOfHour]",test.minuteOfHour().toString());
    }

    public void testPropertyGetMinute_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertSame(test,test.minuteOfHour().getLocalTime());
    }

    public void testPropertyGetMinute_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(20,test.minuteOfHour().get());
    }

    public void testPropertyGetMinute_6_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("20",test.minuteOfHour().getAsString());
    }

    public void testPropertyGetMinute_7_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("20",test.minuteOfHour().getAsText());
    }

    public void testPropertyGetMinute_8_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("20",test.minuteOfHour().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMinute_9_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("20",test.minuteOfHour().getAsShortText());
    }

    public void testPropertyGetMinute_10_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("20",test.minuteOfHour().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMinute_11_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(test.getChronology().minutes(),test.minuteOfHour().getDurationField());
    }

    public void testPropertyGetMinute_12_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(test.getChronology().hours(),test.minuteOfHour().getRangeDurationField());
    }

    public void testPropertyGetMinute_13_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(2,test.minuteOfHour().getMaximumTextLength(null));
    }

    public void testPropertyGetMinute_14_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(2,test.minuteOfHour().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMaxMinValuesMinute_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(0,test.minuteOfHour().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesMinute_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(0,test.minuteOfHour().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesMinute_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(59,test.minuteOfHour().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesMinute_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(59,test.minuteOfHour().getMaximumValueOverall());
    }

    public void testPropertyCompareToMinute_1_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test1.minuteOfHour().compareTo(test2)< 0);
    }

    public void testPropertyCompareToMinute_2_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test2.minuteOfHour().compareTo(test1)> 0);
    }

    public void testPropertyCompareToMinute_3_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test1.minuteOfHour().compareTo(test1)== 0);
    }

    public void testPropertyCompareToMinute_5_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.minuteOfHour().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test1.minuteOfHour().compareTo(dt2)< 0);
    }

    public void testPropertyCompareToMinute_6_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.minuteOfHour().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test2.minuteOfHour().compareTo(dt1)> 0);
    }

    public void testPropertyCompareToMinute_7_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.minuteOfHour().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test1.minuteOfHour().compareTo(dt1)== 0);
    }

    public void testPropertyGetSecond_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertSame(test.getChronology().secondOfMinute(),test.secondOfMinute().getField());
    }

    public void testPropertyGetSecond_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("secondOfMinute",test.secondOfMinute().getName());
    }

    public void testPropertyGetSecond_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("Property[secondOfMinute]",test.secondOfMinute().toString());
    }

    public void testPropertyGetSecond_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertSame(test,test.secondOfMinute().getLocalTime());
    }

    public void testPropertyGetSecond_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(30,test.secondOfMinute().get());
    }

    public void testPropertyGetSecond_6_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("30",test.secondOfMinute().getAsString());
    }

    public void testPropertyGetSecond_7_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("30",test.secondOfMinute().getAsText());
    }

    public void testPropertyGetSecond_8_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("30",test.secondOfMinute().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetSecond_9_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("30",test.secondOfMinute().getAsShortText());
    }

    public void testPropertyGetSecond_10_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("30",test.secondOfMinute().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetSecond_11_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(test.getChronology().seconds(),test.secondOfMinute().getDurationField());
    }

    public void testPropertyGetSecond_12_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(test.getChronology().minutes(),test.secondOfMinute().getRangeDurationField());
    }

    public void testPropertyGetSecond_13_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(2,test.secondOfMinute().getMaximumTextLength(null));
    }

    public void testPropertyGetSecond_14_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(2,test.secondOfMinute().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMaxMinValuesSecond_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(0,test.secondOfMinute().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesSecond_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(0,test.secondOfMinute().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesSecond_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(59,test.secondOfMinute().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesSecond_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(59,test.secondOfMinute().getMaximumValueOverall());
    }

    public void testPropertyCompareToSecond_1_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test1.secondOfMinute().compareTo(test2)< 0);
    }

    public void testPropertyCompareToSecond_2_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test2.secondOfMinute().compareTo(test1)> 0);
    }

    public void testPropertyCompareToSecond_3_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test1.secondOfMinute().compareTo(test1)== 0);
    }

    public void testPropertyCompareToSecond_5_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.secondOfMinute().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test1.secondOfMinute().compareTo(dt2)< 0);
    }

    public void testPropertyCompareToSecond_6_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.secondOfMinute().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test2.secondOfMinute().compareTo(dt1)> 0);
    }

    public void testPropertyCompareToSecond_7_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.secondOfMinute().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test1.secondOfMinute().compareTo(dt1)== 0);
    }

    public void testPropertyGetMilli_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertSame(test.getChronology().millisOfSecond(),test.millisOfSecond().getField());
    }

    public void testPropertyGetMilli_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("millisOfSecond",test.millisOfSecond().getName());
    }

    public void testPropertyGetMilli_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("Property[millisOfSecond]",test.millisOfSecond().toString());
    }

    public void testPropertyGetMilli_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertSame(test,test.millisOfSecond().getLocalTime());
    }

    public void testPropertyGetMilli_5_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(40,test.millisOfSecond().get());
    }

    public void testPropertyGetMilli_6_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("40",test.millisOfSecond().getAsString());
    }

    public void testPropertyGetMilli_7_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("40",test.millisOfSecond().getAsText());
    }

    public void testPropertyGetMilli_8_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("40",test.millisOfSecond().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMilli_9_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("40",test.millisOfSecond().getAsShortText());
    }

    public void testPropertyGetMilli_10_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals("40",test.millisOfSecond().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMilli_11_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(test.getChronology().millis(),test.millisOfSecond().getDurationField());
    }

    public void testPropertyGetMilli_12_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(test.getChronology().seconds(),test.millisOfSecond().getRangeDurationField());
    }

    public void testPropertyGetMilli_13_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(3,test.millisOfSecond().getMaximumTextLength(null));
    }

    public void testPropertyGetMilli_14_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(3,test.millisOfSecond().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMaxMinValuesMilli_1_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(0,test.millisOfSecond().getMinimumValue());
    }

    public void testPropertyGetMaxMinValuesMilli_2_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(0,test.millisOfSecond().getMinimumValueOverall());
    }

    public void testPropertyGetMaxMinValuesMilli_3_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(999,test.millisOfSecond().getMaximumValue());
    }

    public void testPropertyGetMaxMinValuesMilli_4_oe() {
        LocalTime test = new LocalTime(10, 20, 30, 40);
        assertEquals(999,test.millisOfSecond().getMaximumValueOverall());
    }

    public void testPropertyCompareToMilli_1_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test1.millisOfSecond().compareTo(test2)< 0);
    }

    public void testPropertyCompareToMilli_2_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test2.millisOfSecond().compareTo(test1)> 0);
    }

    public void testPropertyCompareToMilli_3_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        assertEquals(true,test1.millisOfSecond().compareTo(test1)== 0);
    }

    public void testPropertyCompareToMilli_5_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.millisOfSecond().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test1.millisOfSecond().compareTo(dt2)< 0);
    }

    public void testPropertyCompareToMilli_6_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.millisOfSecond().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test2.millisOfSecond().compareTo(dt1)> 0);
    }

    public void testPropertyCompareToMilli_7_oe() {
        LocalTime test1 = new LocalTime(TEST_TIME1);
        LocalTime test2 = new LocalTime(TEST_TIME2);
        try {
            test1.millisOfSecond().compareTo((ReadablePartial) null);
        } catch (IllegalArgumentException ex) {}
        
        DateTime dt1 = new DateTime(TEST_TIME1);
        DateTime dt2 = new DateTime(TEST_TIME2);
        assertEquals(true,test1.millisOfSecond().compareTo(dt1)== 0);
    }

}
