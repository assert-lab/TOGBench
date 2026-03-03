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
package org.apache.commons.validator.routines;

import junit.framework.TestCase;

import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Test Case for TimeValidator.
 * 
 * @version $Revision$
 */
public class TimeValidatorTest_OE25Dev extends TestCase {

    protected static final TimeZone GMT = TimeZone.getTimeZone("GMT"); // 0 offset
    protected static final TimeZone EST = TimeZone.getTimeZone("EST"); // - 5 hours

    protected TimeValidator validator;

    protected String[] patternValid = new String[] {
                       "23-59-59" 
                      ,"00-00-00"
                      ,"00-00-01"
                      ,"0-0-0" 
                      ,"1-12-1"
                      ,"10-49-18"
                      ,"16-23-46"};
    protected Date[] patternExpect = new Date[] {
                       createDate(null, 235959, 0)
                      ,createDate(null, 0, 0)
                      ,createDate(null, 1, 0)
                      ,createDate(null, 0, 0)
                      ,createDate(null, 11201, 0)
                      ,createDate(null, 104918, 0)
                      ,createDate(null, 162346, 0)};
    protected String[] localeValid = new String[] {
                      "23:59" 
                     ,"00:00"
                     ,"00:01"
                     ,"0:0" 
                     ,"1:12"
                     ,"10:49"
                     ,"16:23"};
    protected Date[] localeExpect = new Date[] {
                      createDate(null, 235900, 0)
                     ,createDate(null, 0, 0)
                     ,createDate(null, 100, 0)
                     ,createDate(null, 0, 0)
                     ,createDate(null, 11200, 0)
                     ,createDate(null, 104900, 0)
                     ,createDate(null, 162300, 0)};
    protected String[] patternInvalid = new String[] {
                         "24-00-00"  // midnight
                        ,"24-00-01"  // past midnight
                        ,"25-02-03"  // invalid hour 
                        ,"10-61-31"  // invalid minute
                        ,"10-01-61"  // invalid second
                        ,"05:02-29"  // invalid sep 
                        ,"0X-01:01"  // invalid sep
                        ,"05-0X-01"  // invalid char
                        ,"10-01-0X"  // invalid char
                        ,"01:01:05"  // invalid pattern
                        ,"10-10"     // invalid pattern
                        ,"10--10"    // invalid pattern
                        ,"10-10-"};  // invalid pattern
    protected String[] localeInvalid = new String[] {
                         "24:00"  // midnight
                        ,"24:00"  // past midnight
                        ,"25:02"  // invalid hour 
                        ,"10:61"  // invalid minute
                        ,"05-02"  // invalid sep 
                        ,"0X:01"  // invalid sep
                        ,"05:0X"  // invalid char
                        ,"01-01"  // invalid pattern
                        ,"10:"     // invalid pattern
                        ,"10::1"    // invalid pattern
                        ,"10:1:"};  // invalid pattern

    private Locale origDefault;
    private TimeZone defaultZone;

    /**
     * Constructor
     * @param name test name
     */
    public TimeValidatorTest_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        validator = new TimeValidator();
        defaultZone = TimeZone.getDefault();
        origDefault = Locale.getDefault();
    }

    /**
     * Tear down
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        validator = null;
        Locale.setDefault(origDefault);
        TimeZone.setDefault(defaultZone);
    }

    /**
     * Test Valid Dates with "pattern" validation
     */

    /**
     * Test Invalid Dates with "pattern" validation
     */

    /**
     * Test Valid Dates with "locale" validation
     */

    /**
     * Test Invalid Dates with "locale" validation
     */

    /**
     * Test time zone methods.
     */

    /**
     * Test Invalid Dates with "locale" validation
     */

    /**
     * Test compare date methods
     */

    /**
     * Create a calendar instance for a specified time zone, date and time.
     * 
     * @param zone The time zone
     * @param time the time in HH:mm:ss format
     * @param millisecond the milliseconds
     * @return the new Calendar instance.
     */
    protected static Calendar createTime(TimeZone zone, int time, int millisecond) {
        Calendar calendar = zone == null ? Calendar.getInstance()
                                         : Calendar.getInstance(zone);
        int hour = ((time / 10000) * 10000);
        int min  = ((time / 100) * 100) - hour;
        int sec  = time - (hour + min);
        calendar.set(Calendar.YEAR,  1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE,  1);
        calendar.set(Calendar.HOUR_OF_DAY,  (hour / 10000));
        calendar.set(Calendar.MINUTE, (min / 100));
        calendar.set(Calendar.SECOND,  sec);
        calendar.set(Calendar.MILLISECOND,  millisecond);
        return calendar;
    }

    /**
     * Create a date instance for a specified time zone, date and time.
     * 
     * @param zone The time zone
     * @param time the time in HH:mm:ss format
     * @param millisecond the milliseconds
     * @return the new Date instance.
     */
    protected static Date createDate(TimeZone zone, int time, int millisecond) {
        Calendar calendar = createTime(zone, time, millisecond);
        return calendar.getTime();
    }

    public void testPatternValid_1_oe() {
        for (int i = 0; i < patternValid.length; i++) {
            String text = i + " value=[" +patternValid[i]+"] failed ";
            Calendar calendar = validator.validate(patternValid[i], "HH-mm-ss");
            assertNotNull("validateObj() " + text,  calendar);
    }
    }

    public void testPatternValid_2_oe() {
        for (int i = 0; i < patternValid.length; i++) {
            String text = i + " value=[" +patternValid[i]+"] failed ";
            Calendar calendar = validator.validate(patternValid[i], "HH-mm-ss");
            Date date = calendar.getTime();
            assertTrue("isValid() " + text,  validator.isValid(patternValid[i], "HH-mm-ss"));
    }
    }

    public void testPatternValid_3_oe() {
        for (int i = 0; i < patternValid.length; i++) {
            String text = i + " value=[" +patternValid[i]+"] failed ";
            Calendar calendar = validator.validate(patternValid[i], "HH-mm-ss");
            Date date = calendar.getTime();
            assertEquals("compare " + text, patternExpect[i], date);
    }
    }

    public void testPatternInvalid_1_oe() {
        for (int i = 0; i < patternInvalid.length; i++) {
            String text = i + " value=[" +patternInvalid[i]+"] passed ";
            Object date = validator.validate(patternInvalid[i], "HH-mm-ss");
            assertNull("validate() " + text + date,  date);
    }
    }

    public void testPatternInvalid_2_oe() {
        for (int i = 0; i < patternInvalid.length; i++) {
            String text = i + " value=[" +patternInvalid[i]+"] passed ";
            Object date = validator.validate(patternInvalid[i], "HH-mm-ss");
            assertFalse("isValid() " + text,  validator.isValid(patternInvalid[i], "HH-mm-ss"));
    }
    }

    public void testLocaleValid_1_oe() {
        for (int i = 0; i < localeValid.length; i++) {
            String text = i + " value=[" +localeValid[i]+"] failed ";
            Calendar calendar = validator.validate(localeValid[i], Locale.UK);
            assertNotNull("validate() " + text,  calendar);
    }
    }

    public void testLocaleValid_2_oe() {
        for (int i = 0; i < localeValid.length; i++) {
            String text = i + " value=[" +localeValid[i]+"] failed ";
            Calendar calendar = validator.validate(localeValid[i], Locale.UK);
            Date date = calendar.getTime();
            assertTrue("isValid() " + text,  validator.isValid(localeValid[i], Locale.UK));
    }
    }

    public void testLocaleValid_3_oe() {
        for (int i = 0; i < localeValid.length; i++) {
            String text = i + " value=[" +localeValid[i]+"] failed ";
            Calendar calendar = validator.validate(localeValid[i], Locale.UK);
            Date date = calendar.getTime();
            assertEquals("compare " + text, localeExpect[i], date);
    }
    }

    public void testLocaleInvalid_1_oe() {
        for (int i = 0; i < localeInvalid.length; i++) {
            String text = i + " value=[" +localeInvalid[i]+"] passed ";
            Object date = validator.validate(localeInvalid[i], Locale.US);
            assertNull("validate() " + text + date,  date);
    }
    }

    public void testLocaleInvalid_2_oe() {
        for (int i = 0; i < localeInvalid.length; i++) {
            String text = i + " value=[" +localeInvalid[i]+"] passed ";
            Object date = validator.validate(localeInvalid[i], Locale.US);
            assertFalse("isValid() " + text,  validator.isValid(localeInvalid[i], Locale.UK));
    }
    }

    public void testTimeZone_1_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        assertNotNull("default result", result);
    }

    public void testTimeZone_2_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        assertEquals("default zone",  GMT, result.getTimeZone());
    }

    public void testTimeZone_3_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        assertEquals("default hour",   18, result.get(Calendar.HOUR_OF_DAY));
    }

    public void testTimeZone_4_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        assertEquals("default minute", 01, result.get(Calendar.MINUTE));
    }

    public void testTimeZone_5_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        assertNotNull("zone result", result);
    }

    public void testTimeZone_6_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        assertEquals("zone zone",  EST, result.getTimeZone());
    }

    public void testTimeZone_7_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        assertEquals("zone hour",   16, result.get(Calendar.HOUR_OF_DAY));
    }

    public void testTimeZone_8_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        assertEquals("zone minute", 49, result.get(Calendar.MINUTE));
    }

    public void testTimeZone_9_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        assertNotNull("pattern result", result);
    }

    public void testTimeZone_10_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        assertEquals("pattern zone",  EST, result.getTimeZone());
    }

    public void testTimeZone_11_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        assertEquals("pattern hour",   14, result.get(Calendar.HOUR_OF_DAY));
    }

    public void testTimeZone_12_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        assertEquals("pattern minute", 34, result.get(Calendar.MINUTE));
    }

    public void testTimeZone_13_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        assertNotNull("locale result", result);
    }

    public void testTimeZone_14_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        assertEquals("locale zone",  EST, result.getTimeZone());
    }

    public void testTimeZone_15_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        assertEquals("locale hour",   19, result.get(Calendar.HOUR_OF_DAY));
    }

    public void testTimeZone_16_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        assertEquals("locale minute", 18, result.get(Calendar.MINUTE));
    }

    public void testTimeZone_17_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        assertNotNull("pattern result", result);
    }

    public void testTimeZone_18_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        assertEquals("pattern zone",  EST, result.getTimeZone());
    }

    public void testTimeZone_19_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        assertEquals("pattern day",  2005, result.get(Calendar.YEAR));
    }

    public void testTimeZone_20_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        assertEquals("pattern day",11,result.get(Calendar.MONTH));// months are 0-11 assertEquals("pattern day",31,result.get(Calendar.DATE));
    }

    public void testTimeZone_21_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        assertEquals("pattern hour",   21, result.get(Calendar.HOUR_OF_DAY));
    }

    public void testTimeZone_22_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        assertEquals("pattern minute", 05, result.get(Calendar.MINUTE));
    }

    public void testTimeZone_23_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN);
        assertNotNull("pattern result", result);
    }

    public void testTimeZone_24_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN);
        assertEquals("pattern zone",  GMT, result.getTimeZone());
    }

    public void testTimeZone_25_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN);
        assertEquals("pattern day",  2005, result.get(Calendar.YEAR));
    }

    public void testTimeZone_26_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN);
        assertEquals("pattern day",11,result.get(Calendar.MONTH));// months are 0-11 assertEquals("pattern day",31,result.get(Calendar.DATE));
    }

    public void testTimeZone_27_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN);
        assertEquals("pattern hour",   21, result.get(Calendar.HOUR_OF_DAY));
    }

    public void testTimeZone_28_oe() {
        Locale.setDefault(Locale.UK);
        TimeZone.setDefault(GMT);

        Calendar result = null;

        result = validator.validate("18:01");
        result = null;

        result = validator.validate("16:49", EST);
        result = null;

        result = validator.validate("14-34", "HH-mm", EST);
        result = null;

        result = validator.validate("7:18 PM", Locale.US, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN, EST);
        result = null;

        result = validator.validate("31/Dez/05 21-05", "dd/MMM/yy HH-mm", Locale.GERMAN);
        assertEquals("pattern minute", 05, result.get(Calendar.MINUTE));
    }

    public void testFormat_1_oe() {
        Locale.setDefault(Locale.UK);

        Object test = TimeValidator.getInstance().validate("16:49:23", "HH:mm:ss");
        assertNotNull("Test Date ", test);
    }

    public void testFormat_2_oe() {
        Locale.setDefault(Locale.UK);

        Object test = TimeValidator.getInstance().validate("16:49:23", "HH:mm:ss");
        assertEquals("Format pattern", "16-49-23", validator.format(test, "HH-mm-ss"));
    }

    public void testFormat_3_oe() {
        Locale.setDefault(Locale.UK);

        Object test = TimeValidator.getInstance().validate("16:49:23", "HH:mm:ss");
        assertEquals("Format locale",  "4:49 PM",  validator.format(test, Locale.US));
    }

    public void testFormat_4_oe() {
        Locale.setDefault(Locale.UK);

        Object test = TimeValidator.getInstance().validate("16:49:23", "HH:mm:ss");
        assertEquals("Format default", "16:49",  validator.format(test));
    }

    public void testCompare_1_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour

        assertEquals("mili LT", -1, validator.compareTime(value, milliGreater)); // > milli;
    }

    public void testCompare_2_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour

        assertEquals("mili EQ", 0,  validator.compareTime(value, value));        // same time;
    }

    public void testCompare_3_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour

        assertEquals("mili GT", 1,  validator.compareTime(value, milliLess));    // < milli;
    }

    public void testCompare_4_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour


        assertEquals("secs LT", -1, validator.compareSeconds(value, secGreater));   // +1 sec;
    }

    public void testCompare_5_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour


        assertEquals("secs =1", 0,  validator.compareSeconds(value, milliGreater)); // > milli;
    }

    public void testCompare_6_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour


        assertEquals("secs =2", 0,  validator.compareSeconds(value, value));        // same time;
    }

    public void testCompare_7_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour


        assertEquals("secs =3", 0,  validator.compareSeconds(value, milliLess));    // < milli;
    }

    public void testCompare_8_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour


        assertEquals("secs GT", 1,  validator.compareSeconds(value, secLess));      // -1 sec;
    }

    public void testCompare_9_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour



        assertEquals("mins LT", -1, validator.compareMinutes(value, minGreater));   // +1 min;
    }

    public void testCompare_10_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour



        assertEquals("mins =1", 0,  validator.compareMinutes(value, secGreater));   // +1 sec;
    }

    public void testCompare_11_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour



        assertEquals("mins =2", 0,  validator.compareMinutes(value, value));        // same time;
    }

    public void testCompare_12_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour



        assertEquals("mins =3", 0,  validator.compareMinutes(value, secLess));      // -1 sec;
    }

    public void testCompare_13_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour



        assertEquals("mins GT", 1,  validator.compareMinutes(value, minLess));      // -1 min;
    }

    public void testCompare_14_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour




        assertEquals("hour LT", -1, validator.compareHours(value, hourGreater));   // +1 hour;
    }

    public void testCompare_15_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour




        assertEquals("hour =1", 0,  validator.compareHours(value, minGreater));   // +1 min;
    }

    public void testCompare_16_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour




        assertEquals("hour =2", 0,  validator.compareHours(value, value));        // same time;
    }

    public void testCompare_17_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour




        assertEquals("hour =3", 0,  validator.compareHours(value, minLess));      // -1 min;
    }

    public void testCompare_18_oe() {
        int testTime = 154523;
        int min = 100;
        int hour = 10000;

        Calendar milliGreater = createTime(GMT, testTime, 500); // > milli sec
        Calendar value        = createTime(GMT, testTime, 400); // test value
        Calendar milliLess    = createTime(GMT, testTime, 300); // < milli sec

        Calendar secGreater   = createTime(GMT, testTime + 1, 100);   // +1 sec
        Calendar secLess      = createTime(GMT, testTime - 1, 100);   // -1 sec

        Calendar minGreater   = createTime(GMT, testTime + min, 100);   // +1 min
        Calendar minLess      = createTime(GMT, testTime - min, 100);   // -1 min

        Calendar hourGreater  = createTime(GMT, testTime + hour, 100);   // +1 hour
        Calendar hourLess     = createTime(GMT, testTime - hour, 100);   // -1 hour




        assertEquals("hour GT", 1,  validator.compareHours(value, hourLess));      // -1 hour;
    }

}
