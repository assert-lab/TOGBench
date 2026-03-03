/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DateUtilsFragmentTest_OE25Dev {

    private static final int months = 7;   // second final prime before 12
    private static final int days = 23;    // second final prime before 31 (and valid)
    private static final int hours = 19;   // second final prime before 24
    private static final int minutes = 53; // second final prime before 60
    private static final int seconds = 47; // third final prime before 60
    private static final int millis = 991; // second final prime before 1000

    private Date aDate;
    private Calendar aCalendar;


    @BeforeEach
    public void setUp() {
        aCalendar = Calendar.getInstance();
        aCalendar.set(2005, months, days, hours, minutes, seconds);
        aCalendar.set(Calendar.MILLISECOND, millis);
        aDate = aCalendar.getTime();
    }

    //Calendar.SECOND as useful fragment

    //Calendar.MINUTE as useful fragment

    //Calendar.DATE and Calendar.DAY_OF_YEAR as useful fragment

    //Calendar.HOUR_OF_DAY as useful fragment

    //Calendar.MONTH as useful fragment

    //Calendar.YEAR as useful fragment

    @Test
    public void testDateFragmentInLargerUnitWithCalendar_1_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.DATE));
    }

    @Test
    public void testDateFragmentInLargerUnitWithDate_1_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.DATE));
    }

    @Test
    public void testDayOfYearFragmentInLargerUnitWithCalendar_1_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.DAY_OF_YEAR));
    }

    @Test
    public void testDayOfYearFragmentInLargerUnitWithDate_1_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.DAY_OF_YEAR));
    }

    @Test
    public void testDaysOfMonthWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInDays(aCalendar, Calendar.MONTH);
        assertEquals(days, testResult);
    }

    @Test
    public void testDaysOfMonthWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInDays(aDate, Calendar.MONTH);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        assertEquals(cal.get(Calendar.DAY_OF_MONTH), testResult);
    }

    @Test
    public void testDaysOfYearWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInDays(aCalendar, Calendar.YEAR);
        assertEquals(aCalendar.get(Calendar.DAY_OF_YEAR), testResult);
    }

    @Test
    public void testDaysOfYearWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInDays(aDate, Calendar.YEAR);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        assertEquals(cal.get(Calendar.DAY_OF_YEAR), testResult);
    }

    @Test
    public void testHourOfDayFragmentInLargerUnitWithCalendar_1_oe() {
        assertEquals(0, DateUtils.getFragmentInHours(aCalendar, Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testHourOfDayFragmentInLargerUnitWithCalendar_2_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testHourOfDayFragmentInLargerUnitWithDate_1_oe() {
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testHourOfDayFragmentInLargerUnitWithDate_2_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testHoursOfDayWithCalendar_1_oe() {
        long testResult = DateUtils.getFragmentInHours(aCalendar, Calendar.DATE);
        final long expectedValue = hours;
        assertEquals(expectedValue, testResult);
    }

    @Test
    public void testHoursOfDayWithCalendar_2_oe() {
        long testResult = DateUtils.getFragmentInHours(aCalendar, Calendar.DATE);
        final long expectedValue = hours;
        testResult = DateUtils.getFragmentInHours(aCalendar, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testResult);
    }

    @Test
    public void testHoursOfDayWithDate_1_oe() {
        long testResult = DateUtils.getFragmentInHours(aDate, Calendar.DATE);
        final long expectedValue = hours;
        assertEquals(expectedValue, testResult);
    }

    @Test
    public void testHoursOfDayWithDate_2_oe() {
        long testResult = DateUtils.getFragmentInHours(aDate, Calendar.DATE);
        final long expectedValue = hours;
        testResult = DateUtils.getFragmentInHours(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testResult);
    }

    @Test
    public void testHoursOfMonthWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInHours(aCalendar, Calendar.MONTH);
        assertEquals(hours +(((days - 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_HOUR,testResult);
    }

    @Test
    public void testHoursOfMonthWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInHours(aDate, Calendar.MONTH);
        assertEquals(hours +(((days - 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_HOUR,testResult);
    }

    @Test
    public void testHoursOfYearWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInHours(aCalendar, Calendar.YEAR);
        assertEquals(hours +(((aCalendar.get(Calendar.DAY_OF_YEAR)- 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_HOUR,testResult);
    }

    @Test
    public void testHoursOfYearWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInHours(aDate, Calendar.YEAR);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        assertEquals(hours +(((cal.get(Calendar.DAY_OF_YEAR)- 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_HOUR,testResult);
    }

    @Test
    public void testInvalidFragmentWithCalendar_1_oe() throws Exception {
        try {
    DateUtils.getFragmentInMilliseconds(aCalendar, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithCalendar_2_oe() throws Exception {
        try {
    DateUtils.getFragmentInSeconds(aCalendar, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithCalendar_3_oe() throws Exception {
        try {
    DateUtils.getFragmentInMinutes(aCalendar, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithCalendar_4_oe() throws Exception {
        try {
    DateUtils.getFragmentInHours(aCalendar, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithCalendar_5_oe() throws Exception {
        try {
    DateUtils.getFragmentInDays(aCalendar, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithDate_1_oe() throws Exception {
        try {
    DateUtils.getFragmentInMilliseconds(aDate, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithDate_2_oe() throws Exception {
        try {
    DateUtils.getFragmentInSeconds(aDate, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithDate_3_oe() throws Exception {
        try {
    DateUtils.getFragmentInMinutes(aDate, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithDate_4_oe() throws Exception {
        try {
    DateUtils.getFragmentInHours(aDate, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithDate_5_oe() throws Exception {
        try {
    DateUtils.getFragmentInDays(aDate, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMillisecondFragmentInLargerUnitWithCalendar_1_oe() {
        assertEquals(0, DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.MILLISECOND));
    }

    @Test
    public void testMillisecondFragmentInLargerUnitWithCalendar_2_oe() {
        assertEquals(0, DateUtils.getFragmentInSeconds(aCalendar, Calendar.MILLISECOND));
    }

    @Test
    public void testMillisecondFragmentInLargerUnitWithCalendar_3_oe() {
        assertEquals(0, DateUtils.getFragmentInMinutes(aCalendar, Calendar.MILLISECOND));
    }

    @Test
    public void testMillisecondFragmentInLargerUnitWithCalendar_4_oe() {
        assertEquals(0, DateUtils.getFragmentInHours(aCalendar, Calendar.MILLISECOND));
    }

    @Test
    public void testMillisecondFragmentInLargerUnitWithCalendar_5_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.MILLISECOND));
    }

    @Test
    public void testMillisecondFragmentInLargerUnitWithDate_1_oe() {
        assertEquals(0, DateUtils.getFragmentInMilliseconds(aDate, Calendar.MILLISECOND));
    }

    @Test
    public void testMillisecondFragmentInLargerUnitWithDate_2_oe() {
        assertEquals(0, DateUtils.getFragmentInSeconds(aDate, Calendar.MILLISECOND));
    }

    @Test
    public void testMillisecondFragmentInLargerUnitWithDate_3_oe() {
        assertEquals(0, DateUtils.getFragmentInMinutes(aDate, Calendar.MILLISECOND));
    }

    @Test
    public void testMillisecondFragmentInLargerUnitWithDate_4_oe() {
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.MILLISECOND));
    }

    @Test
    public void testMillisecondFragmentInLargerUnitWithDate_5_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.MILLISECOND));
    }

    @Test
    public void testMillisecondsOfDayWithCalendar_1_oe() {
        long testresult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.DATE);
        final long expectedValue = millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR);
        assertEquals(expectedValue, testresult);
    }

    @Test
    public void testMillisecondsOfDayWithCalendar_2_oe() {
        long testresult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.DATE);
        final long expectedValue = millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR);
        testresult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testresult);
    }

    @Test
    public void testMillisecondsOfDayWithDate_1_oe() {
        long testresult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.DATE);
        final long expectedValue = millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR);
        assertEquals(expectedValue, testresult);
    }

    @Test
    public void testMillisecondsOfDayWithDate_2_oe() {
        long testresult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.DATE);
        final long expectedValue = millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR);
        testresult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testresult);
    }

    @Test
    public void testMillisecondsOfHourWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.HOUR_OF_DAY);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE), testResult);
    }

    @Test
    public void testMillisecondsOfHourWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.HOUR_OF_DAY);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE), testResult);
    }

    @Test
    public void testMillisecondsOfMinuteWithCalender_1_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.MINUTE);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND), testResult);
    }

    @Test
    public void testMillisecondsOfMinuteWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.MINUTE);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND), testResult);
    }

    @Test
    public void testMillisecondsOfMonthWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.MONTH);
        assertEquals(millis +(seconds * DateUtils.MILLIS_PER_SECOND)+(minutes * DateUtils.MILLIS_PER_MINUTE)+(hours * DateUtils.MILLIS_PER_HOUR)+((days - 1)* DateUtils.MILLIS_PER_DAY),testResult);
    }

    @Test
    public void testMillisecondsOfMonthWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.MONTH);
        assertEquals(millis +(seconds * DateUtils.MILLIS_PER_SECOND)+(minutes * DateUtils.MILLIS_PER_MINUTE)+(hours * DateUtils.MILLIS_PER_HOUR)+((days - 1)* DateUtils.MILLIS_PER_DAY),testResult);
    }

    @Test
    public void testMillisecondsOfSecondWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.SECOND);
        assertEquals(millis, testResult);
    }

    @Test
    public void testMillisecondsOfSecondWithCalendar_2_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.SECOND);
        assertEquals(aCalendar.get(Calendar.MILLISECOND), testResult);
    }

    @Test
    public void testMillisecondsOfSecondWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.SECOND);
        assertEquals(millis, testResult);
    }

    @Test
    public void testMillisecondsOfYearWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.YEAR);
        assertEquals(millis +(seconds * DateUtils.MILLIS_PER_SECOND)+(minutes * DateUtils.MILLIS_PER_MINUTE)+(hours * DateUtils.MILLIS_PER_HOUR)+((aCalendar.get(Calendar.DAY_OF_YEAR)- 1)* DateUtils.MILLIS_PER_DAY),testResult);
    }

    @Test
    public void testMillisecondsOfYearWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.YEAR);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        assertEquals(millis +(seconds * DateUtils.MILLIS_PER_SECOND)+(minutes * DateUtils.MILLIS_PER_MINUTE)+(hours * DateUtils.MILLIS_PER_HOUR)+((cal.get(Calendar.DAY_OF_YEAR)- 1)* DateUtils.MILLIS_PER_DAY),testResult);
    }

    @Test
    public void testMinuteFragmentInLargerUnitWithCalendar_1_oe() {
        assertEquals(0, DateUtils.getFragmentInMinutes(aCalendar, Calendar.MINUTE));
    }

    @Test
    public void testMinuteFragmentInLargerUnitWithCalendar_2_oe() {
        assertEquals(0, DateUtils.getFragmentInHours(aCalendar, Calendar.MINUTE));
    }

    @Test
    public void testMinuteFragmentInLargerUnitWithCalendar_3_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.MINUTE));
    }

    @Test
    public void testMinuteFragmentInLargerUnitWithDate_1_oe() {
        assertEquals(0, DateUtils.getFragmentInMinutes(aDate, Calendar.MINUTE));
    }

    @Test
    public void testMinuteFragmentInLargerUnitWithDate_2_oe() {
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.MINUTE));
    }

    @Test
    public void testMinuteFragmentInLargerUnitWithDate_3_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.MINUTE));
    }

    @Test
    public void testMinutesOfDayWithCalendar_1_oe() {
        long testResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.DATE);
        final long expectedValue = minutes + ((hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_MINUTE;
        assertEquals(expectedValue, testResult);
    }

    @Test
    public void testMinutesOfDayWithCalendar_2_oe() {
        long testResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.DATE);
        final long expectedValue = minutes + ((hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_MINUTE;
        testResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testResult);
    }

    @Test
    public void testMinutesOfDayWithDate_1_oe() {
        long testResult = DateUtils.getFragmentInMinutes(aDate, Calendar.DATE);
        final long expectedValue = minutes + ((hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_MINUTE;
        assertEquals(expectedValue, testResult);
    }

    @Test
    public void testMinutesOfDayWithDate_2_oe() {
        long testResult = DateUtils.getFragmentInMinutes(aDate, Calendar.DATE);
        final long expectedValue = minutes + ((hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_MINUTE;
        testResult = DateUtils.getFragmentInMinutes(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testResult);
    }

    @Test
    public void testMinutesOfHourWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.HOUR_OF_DAY);
        assertEquals(minutes, testResult);
    }

    @Test
    public void testMinutesOfHourWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInMinutes(aDate, Calendar.HOUR_OF_DAY);
        assertEquals(minutes, testResult);
    }

    @Test
    public void testMinutesOfMonthWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.MONTH);
        assertEquals(minutes +((hours * DateUtils.MILLIS_PER_HOUR)+((days - 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_MINUTE,testResult);
    }

    @Test
    public void testMinutesOfMonthWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInMinutes(aDate, Calendar.MONTH);
        assertEquals(minutes +((hours * DateUtils.MILLIS_PER_HOUR)+((days - 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_MINUTE,testResult);
    }

    @Test
    public void testMinutesOfYearWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.YEAR);
        assertEquals(minutes +((hours * DateUtils.MILLIS_PER_HOUR)+((aCalendar.get(Calendar.DAY_OF_YEAR)- 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_MINUTE,testResult);
    }

    @Test
    public void testMinutesOfYearWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInMinutes(aDate, Calendar.YEAR);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        assertEquals(minutes +((hours * DateUtils.MILLIS_PER_HOUR)+((cal.get(Calendar.DAY_OF_YEAR)- 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_MINUTE,testResult);
    }

    @Test
    public void testMinutesOfYearWithWrongOffsetBugWithCalendar_1_oe() {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_YEAR, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        final long testResult = DateUtils.getFragmentInMinutes(c, Calendar.YEAR);
        assertEquals( 0, testResult);
    }

    @Test
    public void testNullCalendar_1_oe() throws Exception {
        try {
    DateUtils.getFragmentInMilliseconds((Calendar) null, Calendar.MILLISECOND);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullCalendar_2_oe() throws Exception {

        try {
    DateUtils.getFragmentInSeconds((Calendar) null, Calendar.MILLISECOND);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullCalendar_3_oe() throws Exception {


        try {
    DateUtils.getFragmentInMinutes((Calendar) null, Calendar.MILLISECOND);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullCalendar_4_oe() throws Exception {



        try {
    DateUtils.getFragmentInHours((Calendar) null, Calendar.MILLISECOND);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullCalendar_5_oe() throws Exception {




        try {
    DateUtils.getFragmentInDays((Calendar) null, Calendar.MILLISECOND);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullDate_1_oe() throws Exception {
        try {
    DateUtils.getFragmentInMilliseconds((Date) null, Calendar.MILLISECOND);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testNullDate_2_oe() throws Exception {

        try {
    DateUtils.getFragmentInSeconds((Date) null, Calendar.MILLISECOND);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testNullDate_3_oe() throws Exception {


        try {
    DateUtils.getFragmentInMinutes((Date) null, Calendar.MILLISECOND);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testNullDate_4_oe() throws Exception {



        try {
    DateUtils.getFragmentInHours((Date) null, Calendar.MILLISECOND);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testNullDate_5_oe() throws Exception {




        try {
    DateUtils.getFragmentInDays((Date) null, Calendar.MILLISECOND);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSecondFragmentInLargerUnitWithCalendar_1_oe() {
        assertEquals(0, DateUtils.getFragmentInSeconds(aCalendar, Calendar.SECOND));
    }

    @Test
    public void testSecondFragmentInLargerUnitWithCalendar_2_oe() {
        assertEquals(0, DateUtils.getFragmentInMinutes(aCalendar, Calendar.SECOND));
    }

    @Test
    public void testSecondFragmentInLargerUnitWithCalendar_3_oe() {
        assertEquals(0, DateUtils.getFragmentInHours(aCalendar, Calendar.SECOND));
    }

    @Test
    public void testSecondFragmentInLargerUnitWithCalendar_4_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.SECOND));
    }

    @Test
    public void testSecondFragmentInLargerUnitWithDate_1_oe() {
        assertEquals(0, DateUtils.getFragmentInSeconds(aDate, Calendar.SECOND));
    }

    @Test
    public void testSecondFragmentInLargerUnitWithDate_2_oe() {
        assertEquals(0, DateUtils.getFragmentInMinutes(aDate, Calendar.SECOND));
    }

    @Test
    public void testSecondFragmentInLargerUnitWithDate_3_oe() {
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.SECOND));
    }

    @Test
    public void testSecondFragmentInLargerUnitWithDate_4_oe() {
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.SECOND));
    }

    @Test
    public void testSecondsOfDayWithCalendar_1_oe() {
        long testresult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.DATE);
        final long expectedValue = seconds + ((minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_SECOND;
        assertEquals(expectedValue, testresult);
    }

    @Test
    public void testSecondsOfDayWithCalendar_2_oe() {
        long testresult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.DATE);
        final long expectedValue = seconds + ((minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_SECOND;
        testresult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testresult);
    }

    @Test
    public void testSecondsOfDayWithDate_1_oe() {
        long testresult = DateUtils.getFragmentInSeconds(aDate, Calendar.DATE);
        final long expectedValue = seconds + ((minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_SECOND;
        assertEquals(expectedValue, testresult);
    }

    @Test
    public void testSecondsOfDayWithDate_2_oe() {
        long testresult = DateUtils.getFragmentInSeconds(aDate, Calendar.DATE);
        final long expectedValue = seconds + ((minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_SECOND;
        testresult = DateUtils.getFragmentInSeconds(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testresult);
    }

    @Test
    public void testSecondsofHourWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.HOUR_OF_DAY);
        assertEquals(seconds +(minutes * DateUtils.MILLIS_PER_MINUTE / DateUtils.MILLIS_PER_SECOND),testResult);
    }

    @Test
    public void testSecondsofHourWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInSeconds(aDate, Calendar.HOUR_OF_DAY);
        assertEquals(seconds +(minutes * DateUtils.MILLIS_PER_MINUTE / DateUtils.MILLIS_PER_SECOND),testResult);
    }

    @Test
    public void testSecondsofMinuteWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.MINUTE);
        assertEquals(seconds, testResult);
    }

    @Test
    public void testSecondsofMinuteWithCalendar_2_oe() {
        final long testResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.MINUTE);
        assertEquals(aCalendar.get(Calendar.SECOND), testResult);
    }

    @Test
    public void testSecondsofMinuteWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInSeconds(aDate, Calendar.MINUTE);
        assertEquals(seconds, testResult);
    }

    @Test
    public void testSecondsOfMonthWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.MONTH);
        assertEquals(seconds +((minutes * DateUtils.MILLIS_PER_MINUTE)+(hours * DateUtils.MILLIS_PER_HOUR)+((days - 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_SECOND,testResult);
    }

    @Test
    public void testSecondsOfMonthWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInSeconds(aDate, Calendar.MONTH);
        assertEquals(seconds +((minutes * DateUtils.MILLIS_PER_MINUTE)+(hours * DateUtils.MILLIS_PER_HOUR)+((days - 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_SECOND,testResult);
    }

    @Test
    public void testSecondsOfYearWithCalendar_1_oe() {
        final long testResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.YEAR);
        assertEquals(seconds +((minutes * DateUtils.MILLIS_PER_MINUTE)+(hours * DateUtils.MILLIS_PER_HOUR)+((aCalendar.get(Calendar.DAY_OF_YEAR)- 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_SECOND,testResult);
    }

    @Test
    public void testSecondsOfYearWithDate_1_oe() {
        final long testResult = DateUtils.getFragmentInSeconds(aDate, Calendar.YEAR);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        assertEquals(seconds +((minutes * DateUtils.MILLIS_PER_MINUTE)+(hours * DateUtils.MILLIS_PER_HOUR)+((cal.get(Calendar.DAY_OF_YEAR)- 1)* DateUtils.MILLIS_PER_DAY))/ DateUtils.MILLIS_PER_SECOND,testResult);
    }

}
