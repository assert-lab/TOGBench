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

import java.text.Format;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Test Case for CalendarValidator.
 * 
 * @version $Revision$
 */
public class CalendarValidatorTest_OE25Dev extends AbstractCalendarValidatorTest {
    
    private static final int DATE_2005_11_23 = 20051123;
    private static final int TIME_12_03_45   = 120345;

    private CalendarValidator calValidator;

    /**
     * Constructor
     * @param name test name
     */
    public CalendarValidatorTest_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        calValidator = new CalendarValidator();
        validator         = calValidator;
    }

    /**
     * Test CalendarValidator validate Methods
     */

    /**
     * Test compare date methods
     */

    /**
     * Test Date/Time style Validator (there isn't an implementation for this)
     */

    /**
     * Test format methods
     */

    /**
     * Test adjustToTimeZone() method
     */
    
    public void testCalendarValidatorMethods_1_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();
        assertEquals("validate(A) default", expected, CalendarValidator.getInstance().validate(defaultVal).getTime());
    }

    public void testCalendarValidatorMethods_2_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();
        assertEquals("validate(A) locale ", expected, CalendarValidator.getInstance().validate(localeVal, locale).getTime());
    }

    public void testCalendarValidatorMethods_3_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();
        assertEquals("validate(A) pattern", expected, CalendarValidator.getInstance().validate(patternVal, pattern).getTime());
    }

    public void testCalendarValidatorMethods_4_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();
        assertEquals("validate(A) both",    expected, CalendarValidator.getInstance().validate(germanVal, germanPattern, Locale.GERMAN).getTime());
    }

    public void testCalendarValidatorMethods_5_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();

        assertTrue("isValid(A) default", CalendarValidator.getInstance().isValid(defaultVal));
    }

    public void testCalendarValidatorMethods_6_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();

        assertTrue("isValid(A) locale ", CalendarValidator.getInstance().isValid(localeVal, locale));
    }

    public void testCalendarValidatorMethods_7_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();

        assertTrue("isValid(A) pattern", CalendarValidator.getInstance().isValid(patternVal, pattern));
    }

    public void testCalendarValidatorMethods_8_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();

        assertTrue("isValid(A) both",    CalendarValidator.getInstance().isValid(germanVal, germanPattern, Locale.GERMAN));
    }

    public void testCalendarValidatorMethods_9_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();


        assertNull("validate(B) default", CalendarValidator.getInstance().validate(XXXX));
    }

    public void testCalendarValidatorMethods_10_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();


        assertNull("validate(B) locale ", CalendarValidator.getInstance().validate(XXXX, locale));
    }

    public void testCalendarValidatorMethods_11_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();


        assertNull("validate(B) pattern", CalendarValidator.getInstance().validate(XXXX, pattern));
    }

    public void testCalendarValidatorMethods_12_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();


        assertNull("validate(B) both",    CalendarValidator.getInstance().validate("31 Dec 2005", germanPattern, Locale.GERMAN));
    }

    public void testCalendarValidatorMethods_13_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();



        assertFalse("isValid(B) default", CalendarValidator.getInstance().isValid(XXXX));
    }

    public void testCalendarValidatorMethods_14_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();



        assertFalse("isValid(B) locale ", CalendarValidator.getInstance().isValid(XXXX, locale));
    }

    public void testCalendarValidatorMethods_15_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();



        assertFalse("isValid(B) pattern", CalendarValidator.getInstance().isValid(XXXX, pattern));
    }

    public void testCalendarValidatorMethods_16_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();



        assertFalse("isValid(B) both",    CalendarValidator.getInstance().isValid("31 Dec 2005", germanPattern, Locale.GERMAN));
    }

    public void testCalendarValidatorMethods_17_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();




        TimeZone zone = (TimeZone.getDefault().getRawOffset() == EET.getRawOffset() ? EST : EET); 
        Date expectedZone = createCalendar(zone, 20051231, 0).getTime();
        assertFalse("default/EET same ", expected.getTime() == expectedZone.getTime());
    }

    public void testCalendarValidatorMethods_18_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();




        TimeZone zone = (TimeZone.getDefault().getRawOffset() == EET.getRawOffset() ? EST : EET); 
        Date expectedZone = createCalendar(zone, 20051231, 0).getTime();

        assertEquals("validate(C) default", expectedZone, CalendarValidator.getInstance().validate(defaultVal, zone).getTime());
    }

    public void testCalendarValidatorMethods_19_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();




        TimeZone zone = (TimeZone.getDefault().getRawOffset() == EET.getRawOffset() ? EST : EET); 
        Date expectedZone = createCalendar(zone, 20051231, 0).getTime();

        assertEquals("validate(C) locale ", expectedZone, CalendarValidator.getInstance().validate(localeVal, locale, zone).getTime());
    }

    public void testCalendarValidatorMethods_20_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();




        TimeZone zone = (TimeZone.getDefault().getRawOffset() == EET.getRawOffset() ? EST : EET); 
        Date expectedZone = createCalendar(zone, 20051231, 0).getTime();

        assertEquals("validate(C) pattern", expectedZone, CalendarValidator.getInstance().validate(patternVal, pattern, zone).getTime());
    }

    public void testCalendarValidatorMethods_21_oe() {
        Locale.setDefault(Locale.US);
        Locale locale     = Locale.GERMAN;
        String pattern    = "yyyy-MM-dd";
        String patternVal = "2005-12-31";
        String germanVal     = "31 Dez 2005";
        String germanPattern = "dd MMM yyyy";
        String localeVal  = "31.12.2005";
        String defaultVal = "12/31/05";
        String XXXX    = "XXXX"; 
        Date expected = createCalendar(null, 20051231, 0).getTime();




        TimeZone zone = (TimeZone.getDefault().getRawOffset() == EET.getRawOffset() ? EST : EET); 
        Date expectedZone = createCalendar(zone, 20051231, 0).getTime();

        assertEquals("validate(C) both",    expectedZone, CalendarValidator.getInstance().validate(germanVal, germanPattern, Locale.GERMAN, zone).getTime());
    }

    public void testCompare_1_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year

        assertEquals("hour GT", 1, calValidator.compare(value, diffHour, Calendar.HOUR_OF_DAY));
    }

    public void testCompare_2_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year

        assertEquals("hour EQ", 0, calValidator.compare(value, diffMin,  Calendar.HOUR_OF_DAY));
    }

    public void testCompare_3_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year

        assertEquals("mins GT", 1, calValidator.compare(value, diffMin,  Calendar.MINUTE));
    }

    public void testCompare_4_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year

        assertEquals("mins EQ", 0, calValidator.compare(value, diffSec,  Calendar.MINUTE));
    }

    public void testCompare_5_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year

        assertEquals("secs GT", 1, calValidator.compare(value, diffSec,  Calendar.SECOND));
    }

    public void testCompare_6_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year


        assertEquals("date LT", -1, calValidator.compareDates(value, cal20050824)); // +1 day;
    }

    public void testCompare_7_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year


        assertEquals("date EQ", 0,  calValidator.compareDates(value, diffHour));    // same day, diff hour;
    }

    public void testCompare_8_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year


        assertEquals("date(B)", 0,  calValidator.compare(value, diffHour, Calendar.DAY_OF_YEAR));    // same day, diff hour;
    }

    public void testCompare_9_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year


        assertEquals("date GT", 1,  calValidator.compareDates(value, cal20050822)); // -1 day;
    }

    public void testCompare_10_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year



        assertEquals("week LT", -1, calValidator.compareWeeks(value, cal20050830)); // +1 week;
    }

    public void testCompare_11_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year



        assertEquals("week =1", 0,  calValidator.compareWeeks(value, cal20050824)); // +1 day;
    }

    public void testCompare_12_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year



        assertEquals("week =2", 0,  calValidator.compareWeeks(value, cal20050822)); // same week;
    }

    public void testCompare_13_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year



        assertEquals("week =3", 0,  calValidator.compare(value, cal20050822, Calendar.WEEK_OF_MONTH)); // same week;
    }

    public void testCompare_14_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year



        assertEquals("week =4", 0,  calValidator.compareWeeks(value, cal20050822)); // -1 day;
    }

    public void testCompare_15_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year



        assertEquals("week GT", 1,  calValidator.compareWeeks(value, cal20050816)); // -1 week;
    }

    public void testCompare_16_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year




        assertEquals("mnth LT", -1, calValidator.compareMonths(value, cal20050901)); // +1 month;
    }

    public void testCompare_17_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year




        assertEquals("mnth =1", 0,  calValidator.compareMonths(value, cal20050830)); // +1 week;
    }

    public void testCompare_18_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year




        assertEquals("mnth =2", 0,  calValidator.compareMonths(value, cal20050801)); // same month;
    }

    public void testCompare_19_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year




        assertEquals("mnth =3", 0,  calValidator.compareMonths(value, cal20050816)); // -1 week;
    }

    public void testCompare_20_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year




        assertEquals("mnth GT", 1,  calValidator.compareMonths(value, cal20050731)); // -1 month;
    }

    public void testCompare_21_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year





        assertEquals("qtrA <1", -1, calValidator.compareQuarters(value, cal20051101)); // +1 quarter (Feb);
    }

    public void testCompare_22_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year





        assertEquals("qtrA <2", -1, calValidator.compareQuarters(value, cal20051001)); // +1 quarter;
    }

    public void testCompare_23_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year





        assertEquals("qtrA =1", 0,  calValidator.compareQuarters(value, cal20050901)); // +1 month;
    }

    public void testCompare_24_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year





        assertEquals("qtrA =2", 0,  calValidator.compareQuarters(value, cal20050701)); // same quarter;
    }

    public void testCompare_25_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year





        assertEquals("qtrA =3", 0,  calValidator.compareQuarters(value, cal20050731)); // -1 month;
    }

    public void testCompare_26_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year





        assertEquals("qtrA GT", 1,  calValidator.compareQuarters(value, cal20050630)); // -1 quarter;
    }

    public void testCompare_27_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year






        assertEquals("qtrB LT", -1, calValidator.compareQuarters(value, cal20051101, 2)); // +1 quarter (Feb);
    }

    public void testCompare_28_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year






        assertEquals("qtrB =1", 0,  calValidator.compareQuarters(value, cal20051001, 2));  // same quarter;
    }

    public void testCompare_29_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year






        assertEquals("qtrB =2", 0,  calValidator.compareQuarters(value, cal20050901, 2)); // +1 month;
    }

    public void testCompare_30_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year






        assertEquals("qtrB =3", 1,  calValidator.compareQuarters(value, cal20050701, 2)); // same quarter;
    }

    public void testCompare_31_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year






        assertEquals("qtrB =4", 1,  calValidator.compareQuarters(value, cal20050731, 2)); // -1 month;
    }

    public void testCompare_32_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year






        assertEquals("qtrB GT", 1,  calValidator.compareQuarters(value, cal20050630, 2)); // -1 quarter;
    }

    public void testCompare_33_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year







        assertEquals("year LT", -1, calValidator.compareYears(value, cal20060101)); // +1 year;
    }

    public void testCompare_34_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year







        assertEquals("year EQ", 0,  calValidator.compareYears(value, cal20050101)); // same year;
    }

    public void testCompare_35_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year







        assertEquals("year GT", 1,  calValidator.compareYears(value, cal20041231)); // -1 year;
    }

    public void testCompare_37_oe() {
        int sameTime = 124522;
        int testDate = 20050823;
        Calendar diffHour    = createCalendar(GMT, testDate, 115922);    // same date, different time
        Calendar diffMin     = createCalendar(GMT, testDate, 124422);    // same date, different time
        Calendar diffSec     = createCalendar(GMT, testDate, 124521);    // same date, different time

        Calendar value       = createCalendar(GMT, testDate, sameTime);   // test value
        Calendar cal20050824 = createCalendar(GMT, 20050824, sameTime);   // +1 day
        Calendar cal20050822 = createCalendar(GMT, 20050822, sameTime);   // -1 day

        Calendar cal20050830 = createCalendar(GMT, 20050830, sameTime);   // +1 week
        Calendar cal20050816 = createCalendar(GMT, 20050816, sameTime);   // -1 week

        Calendar cal20050901 = createCalendar(GMT, 20050901, sameTime);   // +1 month
        Calendar cal20050801 = createCalendar(GMT, 20050801, sameTime);   // same month
        Calendar cal20050731 = createCalendar(GMT, 20050731, sameTime);   // -1 month

        Calendar cal20051101 = createCalendar(GMT, 20051101, sameTime);   // +1 quarter (Feb Start)
        Calendar cal20051001 = createCalendar(GMT, 20051001, sameTime);   // +1 quarter
        Calendar cal20050701 = createCalendar(GMT, 20050701, sameTime);   // same quarter
        Calendar cal20050630 = createCalendar(GMT, 20050630, sameTime);   // -1 quarter

        Calendar cal20060101 = createCalendar(GMT, 20060101, sameTime);   // +1 year
        Calendar cal20050101 = createCalendar(GMT, 20050101, sameTime);   // same year
        Calendar cal20041231 = createCalendar(GMT, 20041231, sameTime);   // -1 year








        try {
            calValidator.compare(value, value,  -1);
        } catch (IllegalArgumentException e) {
            assertEquals("check message", "Invalid field: -1", e.getMessage());
    }
    }

    public void testDateTimeStyle_1_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        AbstractCalendarValidator dateTimeValidator = 
            new AbstractCalendarValidator(true, DateFormat.SHORT, DateFormat.SHORT) {
                private static final long serialVersionUID = 1L;

            @Override
            protected Object processParsedValue(Object value, Format formatter) {
                return value;
            }
        };
        assertTrue("validate(A) default", dateTimeValidator.isValid("31/12/05 14:23"));
    }

    public void testDateTimeStyle_2_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        AbstractCalendarValidator dateTimeValidator = 
            new AbstractCalendarValidator(true, DateFormat.SHORT, DateFormat.SHORT) {
                private static final long serialVersionUID = 1L;

            @Override
            protected Object processParsedValue(Object value, Format formatter) {
                return value;
            }
        };
        assertTrue("validate(A) locale ", dateTimeValidator.isValid("12/31/05 2:23 PM", Locale.US));
    }

    public void testFormat_1_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);
        assertNull("null", calValidator.format(null));
    }

    public void testFormat_2_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);
        assertEquals("default",  "31/12/05",         calValidator.format(cal20050101));
    }

    public void testFormat_3_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);
        assertEquals("locale",   "12/31/05",         calValidator.format(cal20050101, Locale.US));
    }

    public void testFormat_4_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);
        assertEquals("patternA", "2005-12-31 01:15", calValidator.format(cal20050101, "yyyy-MM-dd HH:mm"));
    }

    public void testFormat_5_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);
        assertEquals("patternB", "2005-12-31 GMT",   calValidator.format(cal20050101, "yyyy-MM-dd z"));
    }

    public void testFormat_6_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);
        assertEquals("both",     "31 Dez 2005",      calValidator.format(cal20050101, "dd MMM yyyy", Locale.GERMAN));
    }

    public void testFormat_7_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);

        assertEquals("EST default",  "30/12/05",         calValidator.format(cal20050101, EST));
    }

    public void testFormat_8_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);

        assertEquals("EST locale",   "12/30/05",         calValidator.format(cal20050101, Locale.US, EST));
    }

    public void testFormat_9_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);

        assertEquals("EST patternA", "2005-12-30 20:15", calValidator.format(cal20050101, "yyyy-MM-dd HH:mm", EST));
    }

    public void testFormat_10_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);

        assertEquals("EST patternB", "2005-12-30 EST",   calValidator.format(cal20050101, "yyyy-MM-dd z", EST));
    }

    public void testFormat_11_oe() {
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        Calendar cal20050101 = createCalendar(GMT, 20051231, 11500);

        assertEquals("EST both",     "30 Dez 2005",      calValidator.format(cal20050101, "dd MMM yyyy", Locale.GERMAN, EST));
    }

    public void testAdjustToTimeZone_1_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();

        assertFalse("Check GMT != CET", dateGMT.getTime() == dateCET.getTime());
    }

    public void testAdjustToTimeZone_2_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();

        assertFalse("Check GMT != EST", dateGMT.getTime() == dateEST.getTime());
    }

    public void testAdjustToTimeZone_3_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();

        assertFalse("Check CET != EST", dateCET.getTime() == dateEST.getTime());
    }

    public void testAdjustToTimeZone_4_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        assertEquals("EST to GMT", dateGMT, calEST.getTime());
    }

    public void testAdjustToTimeZone_5_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        assertFalse("Check EST = GMT", dateEST == calEST.getTime());
    }

    public void testAdjustToTimeZone_6_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);
        assertEquals("back to EST", dateEST, calEST.getTime());
    }

    public void testAdjustToTimeZone_7_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);
        assertFalse("Check EST != GMT", dateGMT == calEST.getTime());
    }

    public void testAdjustToTimeZone_8_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        assertEquals("CET to GMT", dateGMT, calCET.getTime());
    }

    public void testAdjustToTimeZone_9_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        assertFalse("Check CET = GMT", dateCET == calCET.getTime());
    }

    public void testAdjustToTimeZone_10_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        CalendarValidator.adjustToTimeZone(calCET, EET);
        assertEquals("back to CET", dateCET, calCET.getTime());
    }

    public void testAdjustToTimeZone_11_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        CalendarValidator.adjustToTimeZone(calCET, EET);
        assertFalse("Check CET != GMT", dateGMT == calCET.getTime());
    }

    public void testAdjustToTimeZone_12_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        CalendarValidator.adjustToTimeZone(calCET, EET);

        Calendar calUTC = createCalendar(UTC, DATE_2005_11_23, TIME_12_03_45);
        assertTrue("SAME: UTC = GMT",  UTC.hasSameRules(GMT));
    }

    public void testAdjustToTimeZone_13_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        CalendarValidator.adjustToTimeZone(calCET, EET);

        Calendar calUTC = createCalendar(UTC, DATE_2005_11_23, TIME_12_03_45);
        assertEquals("SAME: Check time (A)", calUTC.getTime(), calGMT.getTime());
    }

    public void testAdjustToTimeZone_14_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        CalendarValidator.adjustToTimeZone(calCET, EET);

        Calendar calUTC = createCalendar(UTC, DATE_2005_11_23, TIME_12_03_45);
        assertFalse("SAME: Check GMT(A)", GMT.equals(calUTC.getTimeZone()));
    }

    public void testAdjustToTimeZone_15_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        CalendarValidator.adjustToTimeZone(calCET, EET);

        Calendar calUTC = createCalendar(UTC, DATE_2005_11_23, TIME_12_03_45);
        assertTrue("SAME: Check UTC(A)",  UTC.equals(calUTC.getTimeZone()));
    }

    public void testAdjustToTimeZone_16_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        CalendarValidator.adjustToTimeZone(calCET, EET);

        Calendar calUTC = createCalendar(UTC, DATE_2005_11_23, TIME_12_03_45);
        CalendarValidator.adjustToTimeZone(calUTC, GMT);
        assertEquals("SAME: Check time (B)", calUTC.getTime(), calGMT.getTime());
    }

    public void testAdjustToTimeZone_17_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        CalendarValidator.adjustToTimeZone(calCET, EET);

        Calendar calUTC = createCalendar(UTC, DATE_2005_11_23, TIME_12_03_45);
        CalendarValidator.adjustToTimeZone(calUTC, GMT);
        assertTrue("SAME: Check GMT(B)", GMT.equals(calUTC.getTimeZone()));
    }

    public void testAdjustToTimeZone_18_oe() {

        Calendar calEST = createCalendar(EST, DATE_2005_11_23, TIME_12_03_45);
        Date dateEST = calEST.getTime();

        Calendar calGMT = createCalendar(GMT, DATE_2005_11_23, TIME_12_03_45);
        Date dateGMT = calGMT.getTime();

        Calendar calCET = createCalendar(EET, DATE_2005_11_23, TIME_12_03_45);
        Date dateCET = calCET.getTime();


        CalendarValidator.adjustToTimeZone(calEST, GMT);
        CalendarValidator.adjustToTimeZone(calEST, EST);

        CalendarValidator.adjustToTimeZone(calCET, GMT);
        CalendarValidator.adjustToTimeZone(calCET, EET);

        Calendar calUTC = createCalendar(UTC, DATE_2005_11_23, TIME_12_03_45);
        CalendarValidator.adjustToTimeZone(calUTC, GMT);
        assertFalse("SAME: Check UTC(B)",  UTC.equals(calUTC.getTimeZone()));
    }

}
