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

import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.ISOChronology;

/**
 * This class is a JUnit test for MutableDuration.
 *
 * @author Stephen Colebourne
 */
public class TestMutablePeriod_Updates_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    
    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    long y2003days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365 + 365;
    
    // 2002-06-09
    private long TEST_TIME_NOW =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
    // 2002-04-05
    private long TEST_TIME1 =
            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 12L * DateTimeConstants.MILLIS_PER_HOUR
            + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    // 2003-05-06
    private long TEST_TIME2 =
            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 14L * DateTimeConstants.MILLIS_PER_HOUR
            + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
    
    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMutablePeriod_Updates_OE25Dev.class);
    }

    public TestMutablePeriod_Updates_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        originalDateTimeZone = DateTimeZone.getDefault();
        originalTimeZone = TimeZone.getDefault();
        originalLocale = Locale.getDefault();
        DateTimeZone.setDefault(LONDON);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        Locale.setDefault(Locale.UK);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(originalDateTimeZone);
        TimeZone.setDefault(originalTimeZone);
        Locale.setDefault(originalLocale);
        originalDateTimeZone = null;
        originalTimeZone = null;
        originalLocale = null;
    }

    //-----------------------------------------------------------------------
    public void testTest() {
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW).toString());
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1).toString());
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2).toString());
    }

    //-----------------------------------------------------------------------
    public void testClear() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.clear();
        assertEquals(new MutablePeriod(),test);
        
        test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        test.clear();
        assertEquals(new MutablePeriod(PeriodType.yearMonthDayTime()),test);
    }

    //-----------------------------------------------------------------------
    public void testAddYears() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addYears(10);
        assertEquals(11,test.getYears());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addYears(-10);
        assertEquals(-9,test.getYears());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addYears(0);
        assertEquals(1,test.getYears());
    }

    //-----------------------------------------------------------------------
    public void testAddMonths() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMonths(10);
        assertEquals(12,test.getMonths());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMonths(-10);
        assertEquals(-8,test.getMonths());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMonths(0);
        assertEquals(2,test.getMonths());
    }

    //-----------------------------------------------------------------------
    public void testAddWeeks() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addWeeks(10);
        assertEquals(13,test.getWeeks());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addWeeks(-10);
        assertEquals(-7,test.getWeeks());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addWeeks(0);
        assertEquals(3,test.getWeeks());
    }

    //-----------------------------------------------------------------------
    public void testAddDays() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addDays(10);
        assertEquals(14,test.getDays());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addDays(-10);
        assertEquals(-6,test.getDays());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addDays(0);
        assertEquals(4,test.getDays());
    }

    //-----------------------------------------------------------------------
    public void testAddHours() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addHours(10);
        assertEquals(15,test.getHours());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addHours(-10);
        assertEquals(-5,test.getHours());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addHours(0);
        assertEquals(5,test.getHours());
    }

    //-----------------------------------------------------------------------
    public void testAddMinutes() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMinutes(10);
        assertEquals(16,test.getMinutes());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMinutes(-10);
        assertEquals(-4,test.getMinutes());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMinutes(0);
        assertEquals(6,test.getMinutes());
    }

    //-----------------------------------------------------------------------
    public void testAddSeconds() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addSeconds(10);
        assertEquals(17,test.getSeconds());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addSeconds(-10);
        assertEquals(-3,test.getSeconds());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addSeconds(0);
        assertEquals(7,test.getSeconds());
    }

    //-----------------------------------------------------------------------
    public void testAddMillis() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMillis(10);
        assertEquals(18,test.getMillis());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMillis(-10);
        assertEquals(-2,test.getMillis());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMillis(0);
        assertEquals(8,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testSetYears() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(10);
        assertEquals(10,test.getYears());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(-10);
        assertEquals(-10,test.getYears());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(0);
        assertEquals(0,test.getYears());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(1);
        assertEquals(1,test.getYears());
        
        test = new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 1, PeriodType.millis());
        try {
            test.setYears(1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testSetMonths() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(10);
        assertEquals(10,test.getMonths());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(-10);
        assertEquals(-10,test.getMonths());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(0);
        assertEquals(0,test.getMonths());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(2);
        assertEquals(2,test.getMonths());
    }

    //-----------------------------------------------------------------------
    public void testSetWeeks() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(10);
        assertEquals(10,test.getWeeks());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(-10);
        assertEquals(-10,test.getWeeks());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(0);
        assertEquals(0,test.getWeeks());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(3);
        assertEquals(3,test.getWeeks());
    }

    //-----------------------------------------------------------------------
    public void testSetDays() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(10);
        assertEquals(10,test.getDays());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(-10);
        assertEquals(-10,test.getDays());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(0);
        assertEquals(0,test.getDays());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(4);
        assertEquals(4,test.getDays());
    }

    //-----------------------------------------------------------------------
    public void testSetHours() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(10);
        assertEquals(10,test.getHours());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(-10);
        assertEquals(-10,test.getHours());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(0);
        assertEquals(0,test.getHours());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(5);
        assertEquals(5,test.getHours());
    }

    //-----------------------------------------------------------------------
    public void testSetMinutes() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(10);
        assertEquals(10,test.getMinutes());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(-10);
        assertEquals(-10,test.getMinutes());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(0);
        assertEquals(0,test.getMinutes());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(6);
        assertEquals(6,test.getMinutes());
    }

    //-----------------------------------------------------------------------
    public void testSetSeconds() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(10);
        assertEquals(10,test.getSeconds());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(-10);
        assertEquals(-10,test.getSeconds());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(0);
        assertEquals(0,test.getSeconds());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(7);
        assertEquals(7,test.getSeconds());
    }

    //-----------------------------------------------------------------------
    public void testSetMillis() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(10);
        assertEquals(10,test.getMillis());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(-10);
        assertEquals(-10,test.getMillis());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(0);
        assertEquals(0,test.getMillis());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(8);
        assertEquals(8,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testSet_Field() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.set(DurationFieldType.years(), 10);
        assertEquals(10,test.getYears());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        try {
            test.set(null, 10);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testAdd_Field() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(DurationFieldType.years(), 10);
        assertEquals(11,test.getYears());
        
        test = new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 1, PeriodType.millis());
        test.add(DurationFieldType.years(), 0);
        assertEquals(0,test.getYears());
        assertEquals(1,test.getMillis());
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        try {
            test.add(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        try {
            test.add(null, 10);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testSetPeriod_8ints1() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(11,test.getYears());
        assertEquals(12,test.getMonths());
        assertEquals(13,test.getWeeks());
        assertEquals(14,test.getDays());
        assertEquals(15,test.getHours());
        assertEquals(16,test.getMinutes());
        assertEquals(17,test.getSeconds());
        assertEquals(18,test.getMillis());
    }

    public void testSetPeriod_8ints2() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        try {
            test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
            fail();
        } catch (IllegalArgumentException ex) {}
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(100,test.getMillis());
    }

    public void testSetPeriod_8ints3() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(0, 0, 0, 0, 0, 0, 0, 18);
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(18,test.getMillis());
    }

    public void testSetPeriod_8ints4() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(11,test.getYears());
        assertEquals(12,test.getMonths());
        assertEquals(13,test.getWeeks());
        assertEquals(14,test.getDays());
        assertEquals(15,test.getHours());
        assertEquals(16,test.getMinutes());
        assertEquals(17,test.getSeconds());
        assertEquals(18,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testSetPeriod_RP1() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(11,test.getYears());
        assertEquals(12,test.getMonths());
        assertEquals(13,test.getWeeks());
        assertEquals(14,test.getDays());
        assertEquals(15,test.getHours());
        assertEquals(16,test.getMinutes());
        assertEquals(17,test.getSeconds());
        assertEquals(18,test.getMillis());
    }

    public void testSetPeriod_RP2() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        try {
            test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
            fail();
        } catch (IllegalArgumentException ex) {}
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(100,test.getMillis());
    }

    public void testSetPeriod_RP3() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(18,test.getMillis());
    }

    public void testSetPeriod_RP4() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(11,test.getYears());
        assertEquals(12,test.getMonths());
        assertEquals(13,test.getWeeks());
        assertEquals(14,test.getDays());
        assertEquals(15,test.getHours());
        assertEquals(16,test.getMinutes());
        assertEquals(17,test.getSeconds());
        assertEquals(18,test.getMillis());
    }

    public void testSetPeriod_RP5() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadablePeriod) null);
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(0,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testSetPeriod_long_long1() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(1,test.getYears());
        assertEquals(1,test.getMonths());
        assertEquals(1,test.getWeeks());
        assertEquals(1,test.getDays());
        assertEquals(1,test.getHours());
        assertEquals(1,test.getMinutes());
        assertEquals(1,test.getSeconds());
        assertEquals(1,test.getMillis());
    }

    public void testSetPeriod_long_long2() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2.getMillis(), dt1.getMillis());
        assertEquals(-1,test.getYears());
        assertEquals(-1,test.getMonths());
        assertEquals(-1,test.getWeeks());
        assertEquals(-1,test.getDays());
        assertEquals(-1,test.getHours());
        assertEquals(-1,test.getMinutes());
        assertEquals(-1,test.getSeconds());
        assertEquals(-1,test.getMillis());
    }

    public void testSetPeriod_long_long3() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1.getMillis(), dt1.getMillis());
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(0,test.getMillis());
    }

    public void testSetPeriod_long_long_NoYears() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withYearsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0,test.getYears());
        assertEquals(13,test.getMonths());
        assertEquals(1,test.getWeeks());
        assertEquals(1,test.getDays());
        assertEquals(1,test.getHours());
        assertEquals(1,test.getMinutes());
        assertEquals(1,test.getSeconds());
        assertEquals(1,test.getMillis());
    }

    public void testSetPeriod_long_long_NoMonths() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMonthsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(1,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(5,test.getWeeks());
        assertEquals(3,test.getDays());
        assertEquals(1,test.getHours());
        assertEquals(1,test.getMinutes());
        assertEquals(1,test.getSeconds());
        assertEquals(1,test.getMillis());
    }

    public void testSetPeriod_long_long_NoWeeks() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withWeeksRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(1,test.getYears());
        assertEquals(1,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(8,test.getDays());
        assertEquals(1,test.getHours());
        assertEquals(1,test.getMinutes());
        assertEquals(1,test.getSeconds());
        assertEquals(1,test.getMillis());
    }

    public void testSetPeriod_long_long_NoDays() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withDaysRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(1,test.getYears());
        assertEquals(1,test.getMonths());
        assertEquals(1,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(25,test.getHours());
        assertEquals(1,test.getMinutes());
        assertEquals(1,test.getSeconds());
        assertEquals(1,test.getMillis());
    }

    public void testSetPeriod_long_long_NoHours() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withHoursRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(1,test.getYears());
        assertEquals(1,test.getMonths());
        assertEquals(1,test.getWeeks());
        assertEquals(1,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(61,test.getMinutes());
        assertEquals(1,test.getSeconds());
        assertEquals(1,test.getMillis());
    }

    public void testSetPeriod_long_long_NoMinutes() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMinutesRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(1,test.getYears());
        assertEquals(1,test.getMonths());
        assertEquals(1,test.getWeeks());
        assertEquals(1,test.getDays());
        assertEquals(1,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(61,test.getSeconds());
        assertEquals(1,test.getMillis());
    }

    public void testSetPeriod_long_long_NoSeconds() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withSecondsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(1,test.getYears());
        assertEquals(1,test.getMonths());
        assertEquals(1,test.getWeeks());
        assertEquals(1,test.getDays());
        assertEquals(1,test.getHours());
        assertEquals(1,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(1001,test.getMillis());
    }

    public void testSetPeriod_long_long_NoMillis() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMillisRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(1,test.getYears());
        assertEquals(1,test.getMonths());
        assertEquals(1,test.getWeeks());
        assertEquals(1,test.getDays());
        assertEquals(1,test.getHours());
        assertEquals(1,test.getMinutes());
        assertEquals(1,test.getSeconds());
        assertEquals(0,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testSetPeriod_RI_RI1() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1, dt2);
        assertEquals(1,test.getYears());
        assertEquals(1,test.getMonths());
        assertEquals(1,test.getWeeks());
        assertEquals(1,test.getDays());
        assertEquals(1,test.getHours());
        assertEquals(1,test.getMinutes());
        assertEquals(1,test.getSeconds());
        assertEquals(1,test.getMillis());
    }

    public void testSetPeriod_RI_RI2() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2, dt1);
        assertEquals(-1,test.getYears());
        assertEquals(-1,test.getMonths());
        assertEquals(-1,test.getWeeks());
        assertEquals(-1,test.getDays());
        assertEquals(-1,test.getHours());
        assertEquals(-1,test.getMinutes());
        assertEquals(-1,test.getSeconds());
        assertEquals(-1,test.getMillis());
    }

    public void testSetPeriod_RI_RI3() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1, dt1);
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(0,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testSetPeriod_RInterval1() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(new Interval(dt1, dt2));
        assertEquals(1,test.getYears());
        assertEquals(1,test.getMonths());
        assertEquals(1,test.getWeeks());
        assertEquals(1,test.getDays());
        assertEquals(1,test.getHours());
        assertEquals(1,test.getMinutes());
        assertEquals(1,test.getSeconds());
        assertEquals(1,test.getMillis());
    }

    public void testSetPeriod_RInterval2() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableInterval) null);
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(0,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testSetPeriod_long1() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(100L);
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(100,test.getMillis());
    }

    public void testSetPeriod_long2() {
        MutablePeriod test = new MutablePeriod();
        test.setPeriod(
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L);
        // only time fields are precise
        assertEquals(0,test.getYears());//(4 +(3 * 7)+(2 * 30)+ 365)== 450 assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals((450 * 24)+ 5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(8,test.getMillis());
    }

//    public void testSetPeriod_long3() {
//        MutablePeriod test = new MutablePeriod(PeriodType.getPreciseYearMonthType());
//        test.setPeriod(
//            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
//            5L * DateTimeConstants.MILLIS_PER_HOUR +
//            6L * DateTimeConstants.MILLIS_PER_MINUTE +
//            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L);
//        assertEquals(1,test.getYears());
//        assertEquals(2,test.getMonths());
//        assertEquals(0,test.getWeeks());
//        assertEquals(25,test.getDays());
//        assertEquals(5,test.getHours());
//        assertEquals(6,test.getMinutes());
//        assertEquals(7,test.getSeconds());
//        assertEquals(8,test.getMillis());
//    }
//
//    public void testSetPeriod_long4() {
//        MutablePeriod test = new MutablePeriod(PeriodType.getPreciseYearWeekType());
//        test.setPeriod(
//            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
//            5L * DateTimeConstants.MILLIS_PER_HOUR +
//            6L * DateTimeConstants.MILLIS_PER_MINUTE +
//            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L);
//        assertEquals(1,test.getYears());
//        assertEquals(0,test.getMonths());
//        assertEquals(12,test.getWeeks());
//        assertEquals(1,test.getDays());
//        assertEquals(5,test.getHours());
//        assertEquals(6,test.getMinutes());
//        assertEquals(7,test.getSeconds());
//        assertEquals(8,test.getMillis());
//    }
//
//    public void testSetPeriod_long_NoYears() {
//        long ms =
//            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
//            5L * DateTimeConstants.MILLIS_PER_HOUR +
//            6L * DateTimeConstants.MILLIS_PER_MINUTE +
//            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
//        MutablePeriod test = new MutablePeriod(PeriodType.getPreciseYearMonthType().withYearsRemoved());
//        test.setPeriod(ms);
//        assertEquals(0,test.getYears());
//        assertEquals(15,test.getMonths()); // totalDays=365+85=450=15*30
//        assertEquals(0,test.getWeeks());
//        assertEquals(0,test.getDays());
//        assertEquals(5,test.getHours());
//        assertEquals(6,test.getMinutes());
//        assertEquals(7,test.getSeconds());
//        assertEquals(8,test.getMillis());
//        assertEquals(ms,test.toDurationMillis());
//    }
//
//    public void testSetPeriod_long_NoMonths() {
//        long ms =
//            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
//            5L * DateTimeConstants.MILLIS_PER_HOUR +
//            6L * DateTimeConstants.MILLIS_PER_MINUTE +
//            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
//        MutablePeriod test = new MutablePeriod(PeriodType.getPreciseYearMonthType().withMonthsRemoved());
//        test.setPeriod(ms);
//        assertEquals(1,test.getYears());
//        assertEquals(0,test.getMonths());
//        assertEquals(0,test.getWeeks());
//        assertEquals(85,test.getDays());
//        assertEquals(5,test.getHours());
//        assertEquals(6,test.getMinutes());
//        assertEquals(7,test.getSeconds());
//        assertEquals(8,test.getMillis());
//        assertEquals(ms,test.toDurationMillis());
//    }
//
//    public void testSetPeriod_long_NoWeeks() {
//        long ms =
//            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
//            5L * DateTimeConstants.MILLIS_PER_HOUR +
//            6L * DateTimeConstants.MILLIS_PER_MINUTE +
//            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
//        MutablePeriod test = new MutablePeriod(PeriodType.getPreciseYearWeekType().withWeeksRemoved());
//        test.setPeriod(ms);
//        assertEquals(1,test.getYears());
//        assertEquals(0,test.getMonths());
//        assertEquals(0,test.getWeeks());
//        assertEquals(85,test.getDays());
//        assertEquals(5,test.getHours());
//        assertEquals(6,test.getMinutes());
//        assertEquals(7,test.getSeconds());
//        assertEquals(8,test.getMillis());
//        assertEquals(ms,test.toDurationMillis());
//    }
//
//    public void testSetPeriod_long_NoDays() {
//        long ms =
//            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
//            5L * DateTimeConstants.MILLIS_PER_HOUR +
//            6L * DateTimeConstants.MILLIS_PER_MINUTE +
//            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
//        MutablePeriod test = new MutablePeriod(PeriodType.getPreciseYearMonthType().withDaysRemoved());
//        test.setPeriod(ms);
//        assertEquals(1,test.getYears());
//        assertEquals(2,test.getMonths());
//        assertEquals(0,test.getWeeks());
//        assertEquals(0,test.getDays());
//        assertEquals(5 + 25 * 24,test.getHours());
//        assertEquals(6,test.getMinutes());
//        assertEquals(7,test.getSeconds());
//        assertEquals(8,test.getMillis());
//        assertEquals(ms,test.toDurationMillis());
//    }
//
//    public void testSetPeriod_long_NoHours() {
//        long ms =
//            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
//            5L * DateTimeConstants.MILLIS_PER_HOUR +
//            6L * DateTimeConstants.MILLIS_PER_MINUTE +
//            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
//        MutablePeriod test = new MutablePeriod(PeriodType.getPreciseYearMonthType().withHoursRemoved());
//        test.setPeriod(ms);
//        assertEquals(1,test.getYears());
//        assertEquals(2,test.getMonths());
//        assertEquals(0,test.getWeeks());
//        assertEquals(25,test.getDays());
//        assertEquals(0,test.getHours());
//        assertEquals(6 + 5 * 60,test.getMinutes());
//        assertEquals(7,test.getSeconds());
//        assertEquals(8,test.getMillis());
//        assertEquals(ms,test.toDurationMillis());
//    }
//
//    public void testSetPeriod_long_NoMinutes() {
//        long ms =
//            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
//            5L * DateTimeConstants.MILLIS_PER_HOUR +
//            6L * DateTimeConstants.MILLIS_PER_MINUTE +
//            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
//        MutablePeriod test = new MutablePeriod(PeriodType.getPreciseYearMonthType().withMinutesRemoved());
//        test.setPeriod(ms);
//        assertEquals(1,test.getYears());
//        assertEquals(2,test.getMonths());
//        assertEquals(0,test.getWeeks());
//        assertEquals(25,test.getDays());
//        assertEquals(5,test.getHours());
//        assertEquals(0,test.getMinutes());
//        assertEquals(7 + 6 * 60,test.getSeconds());
//        assertEquals(8,test.getMillis());
//        assertEquals(ms,test.toDurationMillis());
//    }
//
//    public void testSetPeriod_long_NoSeconds() {
//        long ms =
//            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
//            5L * DateTimeConstants.MILLIS_PER_HOUR +
//            6L * DateTimeConstants.MILLIS_PER_MINUTE +
//            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
//        MutablePeriod test = new MutablePeriod(PeriodType.getPreciseYearMonthType().withSecondsRemoved());
//        test.setPeriod(ms);
//        assertEquals(1,test.getYears());
//        assertEquals(2,test.getMonths());
//        assertEquals(0,test.getWeeks());
//        assertEquals(25,test.getDays());
//        assertEquals(5,test.getHours());
//        assertEquals(6,test.getMinutes());
//        assertEquals(0,test.getSeconds());
//        assertEquals(8 + 7 * 1000,test.getMillis());
//        assertEquals(ms,test.toDurationMillis());
//    }
//
//    public void testSetPeriod_long_NoMillis() {
//        long ms =
//            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
//            5L * DateTimeConstants.MILLIS_PER_HOUR +
//            6L * DateTimeConstants.MILLIS_PER_MINUTE +
//            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
//        MutablePeriod test = new MutablePeriod(PeriodType.getPreciseYearMonthType().withMillisRemoved());
//        test.setPeriod(ms);
//        assertEquals(1,test.getYears());
//        assertEquals(2,test.getMonths());
//        assertEquals(0,test.getWeeks());
//        assertEquals(25,test.getDays());
//        assertEquals(5,test.getHours());
//        assertEquals(6,test.getMinutes());
//        assertEquals(7,test.getSeconds());
//        assertEquals(0,test.getMillis());
//        assertEquals(ms - 8,test.toDurationMillis());
//    }

    //-----------------------------------------------------------------------
    public void testSetPeriod_RD1() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new Duration(100L));
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(100,test.getMillis());
    }

    public void testSetPeriod_RD2() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.setPeriod(new Duration(length));
        // only time fields are precise
        assertEquals(0,test.getYears());//(4 +(3 * 7)+(2 * 30)+ 365)== 450 assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals((450 * 24)+ 5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(8,test.getMillis());
    }

    public void testSetPeriod_RD3() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableDuration) null);
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(0,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testAdd_8ints1() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(1,test.getYears());
        assertEquals(2,test.getMonths());
        assertEquals(3,test.getWeeks());
        assertEquals(4,test.getDays());
        assertEquals(5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(108,test.getMillis());
    }

    public void testAdd_8ints2() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        try {
            test.add(1, 2, 3, 4, 5, 6, 7, 8);
            fail();
        } catch (IllegalArgumentException ex) {}
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(100,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testAdd_long1() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L);
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(200,test.getMillis());
    }

    public void testAdd_long2() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms);
        // only time fields are precise
        assertEquals(0,test.getYears());//(4 +(3 * 7)+(2 * 30)+ 365)== 450 assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals((450 * 24)+ 5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(108,test.getMillis());
    }

    public void testAdd_long3() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(2100L);
        assertEquals(1,test.getYears());
        assertEquals(2,test.getMonths());
        assertEquals(3,test.getWeeks());
        assertEquals(4,test.getDays());
        assertEquals(5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(9,test.getSeconds());
        assertEquals(108,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testAdd_long_Chronology1() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L, ISOChronology.getInstance());
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(200,test.getMillis());
    }

    public void testAdd_long_Chronology2() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstance());
        // only time fields are precise
        assertEquals(0,test.getYears());//(4 +(3 * 7)+(2 * 30)+ 365)== 450 days assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals((450 * 24)+ 5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(108,test.getMillis());
    }

    public void testAdd_long_Chronology3() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstanceUTC());
        // UTC, so weeks and day also precise
        assertEquals(0,test.getYears());//(4 +(3 * 7)+(2 * 30)+ 365)== 450 days assertEquals(0,test.getMonths());
        assertEquals(64,test.getWeeks());
        assertEquals(2,test.getDays());
        assertEquals(5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(108,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testAdd_RD1() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Duration(100L));
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(200,test.getMillis());
    }

    public void testAdd_RD2() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        long ms =
            (4L + (3L * 7L)) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(new Duration(ms));
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals((4 +(3 * 7))* 24 + 5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(108,test.getMillis());
    }

    public void testAdd_RD3() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableDuration) null);
        assertEquals(1,test.getYears());
        assertEquals(2,test.getMonths());
        assertEquals(3,test.getWeeks());
        assertEquals(4,test.getDays());
        assertEquals(5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(8,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testAdd_RP1() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Period(100L));
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(200,test.getMillis());
    }

    public void testAdd_RP2() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());  // All type
        test.add(new Period(1, 2, 3, 4, 5, 6, 7, 0, PeriodType.standard().withMillisRemoved()));
        // add field value, ignore different types
        assertEquals(1,test.getYears());
        assertEquals(2,test.getMonths());
        assertEquals(3,test.getWeeks());
        assertEquals(4,test.getDays());
        assertEquals(5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(100,test.getMillis());
    }

    public void testAdd_RP3() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        test.add(new Period(0L));
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(100,test.getMillis());
    }

    public void testAdd_RP4() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        try {
            test.add(new Period(1, 2, 3, 4, 5, 6, 7, 8));  // cannot set weeks
            fail();
        } catch (IllegalArgumentException ex) {}
        assertEquals(1,test.getYears());
        assertEquals(2,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(4,test.getDays());
        assertEquals(5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(8,test.getMillis());
    }

    public void testAdd_RP5() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        test.add(new Period(1, 2, 0, 4, 5, 6, 7, 8));  // can set weeks as zero
        assertEquals(2,test.getYears());
        assertEquals(4,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(8,test.getDays());
        assertEquals(10,test.getHours());
        assertEquals(12,test.getMinutes());
        assertEquals(14,test.getSeconds());
        assertEquals(16,test.getMillis());
    }

    public void testAdd_RP6() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadablePeriod) null);
        assertEquals(1,test.getYears());
        assertEquals(2,test.getMonths());
        assertEquals(3,test.getWeeks());
        assertEquals(4,test.getDays());
        assertEquals(5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(8,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testAdd_RInterval1() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Interval(100L, 200L));
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(200,test.getMillis());
    }

    public void testAdd_RInterval2() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 12, 18, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L);  // All type
        test.add(new Interval(dt1, dt2));
        assertEquals(1,test.getYears());// add field value from interval assertEquals(6,test.getMonths());// add field value from interval assertEquals(1,test.getWeeks());// add field value from interval assertEquals(2,test.getDays());// add field value from interval assertEquals(0,test.getHours());// time zone OK assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(108,test.getMillis());
    }

    public void testAdd_RInterval3() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(0L, 0L));
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(100,test.getMillis());
    }

    public void testAdd_RInterval4() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(dt1, dt2));
        assertEquals(1,test.getYears());
        assertEquals(1,test.getMonths());
        assertEquals(0,test.getWeeks());// no weeks assertEquals(8,test.getDays());// week added to days assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(108,test.getMillis());
    }

    public void testAdd_RInterval5() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableInterval) null);
        assertEquals(1,test.getYears());
        assertEquals(2,test.getMonths());
        assertEquals(3,test.getWeeks());
        assertEquals(4,test.getDays());
        assertEquals(5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(8,test.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testMergePeriod_RP1() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(0, 0, 0, 14, 15, 16, 17, 18, PeriodType.dayTime()));
        assertEquals(1,test.getYears());
        assertEquals(2,test.getMonths());
        assertEquals(3,test.getWeeks());
        assertEquals(14,test.getDays());
        assertEquals(15,test.getHours());
        assertEquals(16,test.getMinutes());
        assertEquals(17,test.getSeconds());
        assertEquals(18,test.getMillis());
    }

    public void testMergePeriod_RP2() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        try {
            test.mergePeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
            fail();
        } catch (IllegalArgumentException ex) {}
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(100,test.getMillis());
    }

    public void testMergePeriod_RP3() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.mergePeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0,test.getYears());
        assertEquals(0,test.getMonths());
        assertEquals(0,test.getWeeks());
        assertEquals(0,test.getDays());
        assertEquals(0,test.getHours());
        assertEquals(0,test.getMinutes());
        assertEquals(0,test.getSeconds());
        assertEquals(18,test.getMillis());
    }

    public void testMergePeriod_RP4() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(11,test.getYears());
        assertEquals(12,test.getMonths());
        assertEquals(13,test.getWeeks());
        assertEquals(14,test.getDays());
        assertEquals(15,test.getHours());
        assertEquals(16,test.getMinutes());
        assertEquals(17,test.getSeconds());
        assertEquals(18,test.getMillis());
    }

    public void testMergePeriod_RP5() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod((ReadablePeriod) null);
        assertEquals(1,test.getYears());
        assertEquals(2,test.getMonths());
        assertEquals(3,test.getWeeks());
        assertEquals(4,test.getDays());
        assertEquals(5,test.getHours());
        assertEquals(6,test.getMinutes());
        assertEquals(7,test.getSeconds());
        assertEquals(8,test.getMillis());
    }

    public void testTest_1_oe() {
        Object a = new Instant(TEST_TIME_NOW).toString();
        assertEquals("2013-07-01T00:59:59.999999999+0100:+0100", a);
    }

    public void testTest_2_oe() {
        Object a = new Instant(TEST_TIME1).toString();
        assertEquals("2013-06-12T08:08:08.000Z", a);
    }

    public void testTest_3_oe() {
        Object a = new Instant(TEST_TIME2).toString();
        assertEquals("2013-07-01T00:59:59.999999999+0100:+0100", a);
    }

    public void testClear_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.clear();
// incorrect assertion         assertEquals(false, test.isReadOnly());
    }

    public void testAddYears_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addYears(10);
        assertEquals(10, test.getYears());
    }

    public void testAddYears_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addYears(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addYears(-10);
        assertEquals(0, test.getYears());
    }

    public void testAddYears_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addYears(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addYears(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addYears(0);
        assertEquals(1, test.getYears());
    }

    public void testAddMonths_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMonths(10);
        assertEquals(10, test.getMonths());
    }

    public void testAddMonths_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMonths(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMonths(-10);
        assertEquals(10, test.getMonths());
    }

    public void testAddMonths_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMonths(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMonths(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMonths(0);
        assertEquals(10, test.getMonths());
    }

    public void testAddWeeks_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addWeeks(10);
        assertEquals(10, test.getWeeks());
    }

    public void testAddWeeks_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addWeeks(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addWeeks(-10);
        assertEquals(0, test.getWeeks());
    }

    public void testAddWeeks_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addWeeks(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addWeeks(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addWeeks(0);
        assertEquals(0, test.getWeeks());
    }

    public void testAddDays_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addDays(10);
        assertEquals(10, test.getDays());
    }

    public void testAddDays_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addDays(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addDays(-10);
        assertEquals(0, test.getDays());
    }

    public void testAddDays_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addDays(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addDays(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addDays(0);
        assertEquals(0, test.getDays());
    }

    public void testAddHours_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addHours(10);
        assertEquals(14, test.getHours());
    }

    public void testAddHours_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addHours(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addHours(-10);
        assertEquals(0, test.getHours());
    }

    public void testAddHours_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addHours(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addHours(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addHours(0);
        assertEquals(0, test.getHours());
    }

    public void testAddMinutes_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMinutes(10);
        assertEquals(10, test.getMinutes());
    }

    public void testAddMinutes_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMinutes(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMinutes(-10);
        assertEquals(0, test.getMinutes());
    }

    public void testAddMinutes_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMinutes(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMinutes(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMinutes(0);
        assertEquals(0, test.getMinutes());
    }

    public void testAddSeconds_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addSeconds(10);
        assertEquals(10, test.getSeconds());
    }

    public void testAddSeconds_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addSeconds(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addSeconds(-10);
        assertEquals(5, test.getSeconds());
    }

    public void testAddSeconds_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addSeconds(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addSeconds(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addSeconds(0);
        assertEquals(0, test.getSeconds());
    }

    public void testAddMillis_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMillis(10);
        assertEquals(10, test.getMillis());
    }

    public void testAddMillis_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMillis(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMillis(-10);
        assertEquals(0, test.getMillis());
    }

    public void testAddMillis_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMillis(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMillis(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.addMillis(0);
        assertEquals(0, test.getMillis());
    }

    public void testSetYears_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(10);
        assertEquals(10, test.getYears());
    }

    public void testSetYears_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(-10);
        assertEquals(10, test.getYears());
    }

    public void testSetYears_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(0);
        assertEquals(0, test.getYears());
    }

    public void testSetYears_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(0);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setYears(1);
        assertEquals(1, test.getYears());
    }

    public void testSetMonths_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(10);
        assertEquals(10, test.getMonths());
    }

    public void testSetMonths_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(-10);
        assertEquals(10, test.getMonths());
    }

    public void testSetMonths_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(0);
        assertEquals(0, test.getMonths());
    }

    public void testSetMonths_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(0);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMonths(2);
        assertEquals(2, test.getMonths());
    }

    public void testSetWeeks_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(10);
        assertEquals(10, test.getWeeks());
    }

    public void testSetWeeks_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(-10);
        assertEquals(0, test.getWeeks());
    }

    public void testSetWeeks_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(0);
        assertEquals(0, test.getWeeks());
    }

    public void testSetWeeks_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(0);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setWeeks(3);
        assertEquals(3, test.getWeeks());
    }

    public void testSetDays_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(10);
        assertEquals(10, test.getDays());
    }

    public void testSetDays_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(-10);
        assertEquals(0, test.getDays());
    }

    public void testSetDays_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(0);
        assertEquals(0, test.getDays());
    }

    public void testSetDays_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(0);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setDays(4);
        assertEquals(4, test.getDays());
    }

    public void testSetHours_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(10);
        assertEquals(10, test.getHours());
    }

    public void testSetHours_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(-10);
        assertEquals(0, test.getHours());
    }

    public void testSetHours_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(0);
        assertEquals(0, test.getHours());
    }

    public void testSetHours_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(0);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setHours(5);
        assertEquals(5, test.getHours());
    }

    public void testSetMinutes_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(10);
        assertEquals(10, test.getMinutes());
    }

    public void testSetMinutes_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(-10);
        assertEquals(0, test.getMinutes());
    }

    public void testSetMinutes_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(0);
        assertEquals(0, test.getMinutes());
    }

    public void testSetMinutes_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(0);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMinutes(6);
        assertEquals(6, test.getMinutes());
    }

    public void testSetSeconds_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(10);
        assertEquals(10, test.getSeconds());
    }

    public void testSetSeconds_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(-10);
        assertEquals(0, test.getSeconds());
    }

    public void testSetSeconds_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(0);
        assertEquals(0, test.getSeconds());
    }

    public void testSetSeconds_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(0);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setSeconds(7);
        assertEquals(7, test.getSeconds());
    }

    public void testSetMillis_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(10);
        assertEquals(10, test.getMillis());
    }

    public void testSetMillis_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(-10);
        assertEquals(0, test.getMillis());
    }

    public void testSetMillis_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(0);
        assertEquals(0, test.getMillis());
    }

    public void testSetMillis_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(-10);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(0);
        
        test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setMillis(8);
        assertEquals(8, test.getMillis());
    }

    public void testSet_Field_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.set(DurationFieldType.years(), 10);
        assertEquals(10, test.getYears());
    }

    public void testAdd_Field_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(DurationFieldType.years(), 10);
        assertEquals(10, test.getYears());
    }

    public void testAdd_Field_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(DurationFieldType.years(), 10);
        
        test = new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 1, PeriodType.millis());
        test.add(DurationFieldType.years(), 0);
        assertEquals(10, test.getYears());
    }

    public void testAdd_Field_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(DurationFieldType.years(), 10);
        
        test = new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 1, PeriodType.millis());
        test.add(DurationFieldType.years(), 0);
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_8ints1_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(11, test.getYears());
    }

    public void testSetPeriod_8ints1_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(12, test.getMonths());
    }

    public void testSetPeriod_8ints1_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(11, test.getWeeks());
    }

    public void testSetPeriod_8ints1_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(17, test.getDays());
    }

    public void testSetPeriod_8ints1_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(17, test.getHours());
    }

    public void testSetPeriod_8ints1_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(17, test.getMinutes());
    }

    public void testSetPeriod_8ints1_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(17, test.getSeconds());
    }

    public void testSetPeriod_8ints1_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(17, test.getMillis());
    }

    public void testSetPeriod_8ints2_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        try {
            test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_8ints2_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        try {
            test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_8ints3_1_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(0, 0, 0, 0, 0, 0, 0, 18);
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_8ints3_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(0, 0, 0, 0, 0, 0, 0, 18);
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_8ints3_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(0, 0, 0, 0, 0, 0, 0, 18);
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_8ints3_4_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(0, 0, 0, 0, 0, 0, 0, 18);
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_8ints3_5_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(0, 0, 0, 0, 0, 0, 0, 18);
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_8ints3_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(0, 0, 0, 0, 0, 0, 0, 18);
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_8ints3_7_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(0, 0, 0, 0, 0, 0, 0, 18);
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_8ints3_8_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(0, 0, 0, 0, 0, 0, 0, 18);
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_8ints4_1_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(11, test.getYears());
    }

    public void testSetPeriod_8ints4_2_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(12, test.getMonths());
    }

    public void testSetPeriod_8ints4_3_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(11, test.getWeeks());
    }

    public void testSetPeriod_8ints4_4_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(11, test.getDays());
    }

    public void testSetPeriod_8ints4_5_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_8ints4_6_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(12, test.getMinutes());
    }

    public void testSetPeriod_8ints4_7_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(17, test.getSeconds());
    }

    public void testSetPeriod_8ints4_8_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(11, 12, 13, 14, 15, 16, 17, 18);
        assertEquals(18, test.getMillis());
    }

    public void testSetPeriod_RP1_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(11, test.getYears());
    }

    public void testSetPeriod_RP1_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(12, test.getMonths());
    }

    public void testSetPeriod_RP1_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(11, test.getWeeks());
    }

    public void testSetPeriod_RP1_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(17, test.getDays());
    }

    public void testSetPeriod_RP1_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(17, test.getHours());
    }

    public void testSetPeriod_RP1_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(17, test.getMinutes());
    }

    public void testSetPeriod_RP1_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(17, test.getSeconds());
    }

    public void testSetPeriod_RP1_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_RP3_1_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_RP3_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_RP3_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_RP3_4_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_RP3_5_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_RP3_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_RP3_7_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_RP3_8_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.setPeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_RP4_1_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(11, test.getYears());
    }

    public void testSetPeriod_RP4_2_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(12, test.getMonths());
    }

    public void testSetPeriod_RP4_3_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(11, test.getWeeks());
    }

    public void testSetPeriod_RP4_4_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(17, test.getDays());
    }

    public void testSetPeriod_RP4_5_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(17, test.getHours());
    }

    public void testSetPeriod_RP4_6_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(17, test.getMinutes());
    }

    public void testSetPeriod_RP4_7_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(17, test.getSeconds());
    }

    public void testSetPeriod_RP4_8_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.setPeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_RP5_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadablePeriod) null);
        assertEquals(1, test.getYears());
    }

    public void testSetPeriod_RP5_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadablePeriod) null);
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_RP5_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadablePeriod) null);
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_RP5_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadablePeriod) null);
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_RP5_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadablePeriod) null);
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_RP5_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadablePeriod) null);
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_RP5_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadablePeriod) null);
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_RP5_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadablePeriod) null);
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long_long1_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(1, test.getYears());
    }

    public void testSetPeriod_long_long1_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long1_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long1_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long1_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long1_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long1_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(17, test.getSeconds());
    }

    public void testSetPeriod_long_long1_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(172800000, test.getMillis());
    }

    public void testSetPeriod_long_long2_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2.getMillis(), dt1.getMillis());
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_long_long2_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2.getMillis(), dt1.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long2_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2.getMillis(), dt1.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long2_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2.getMillis(), dt1.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long2_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2.getMillis(), dt1.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long2_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2.getMillis(), dt1.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long2_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2.getMillis(), dt1.getMillis());
        assertEquals(17, test.getSeconds());
    }

    public void testSetPeriod_long_long2_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2.getMillis(), dt1.getMillis());
        assertEquals(172800000, test.getMillis());
    }

    public void testSetPeriod_long_long3_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1.getMillis(), dt1.getMillis());
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_long_long3_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1.getMillis(), dt1.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long3_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1.getMillis(), dt1.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long3_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1.getMillis(), dt1.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long3_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1.getMillis(), dt1.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long3_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1.getMillis(), dt1.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long3_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1.getMillis(), dt1.getMillis());
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_long_long3_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1.getMillis(), dt1.getMillis());
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long_long_NoYears_1_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withYearsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(1, test.getYears());
    }

    public void testSetPeriod_long_long_NoYears_2_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withYearsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long_NoYears_3_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withYearsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long_NoYears_4_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withYearsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long_NoYears_5_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withYearsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long_NoYears_6_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withYearsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long_NoYears_7_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withYearsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_long_long_NoYears_8_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withYearsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long_long_NoMonths_1_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMonthsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_long_long_NoMonths_2_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMonthsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long_NoMonths_3_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMonthsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long_NoMonths_4_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMonthsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long_NoMonths_5_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMonthsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long_NoMonths_6_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMonthsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long_NoMonths_7_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMonthsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_long_long_NoMonths_8_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMonthsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long_long_NoWeeks_1_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withWeeksRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_long_long_NoWeeks_2_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withWeeksRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long_NoWeeks_3_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withWeeksRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long_NoWeeks_4_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withWeeksRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long_NoWeeks_5_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withWeeksRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long_NoWeeks_6_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withWeeksRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long_NoWeeks_7_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withWeeksRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_long_long_NoWeeks_8_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withWeeksRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long_long_NoDays_1_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withDaysRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_long_long_NoDays_2_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withDaysRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long_NoDays_3_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withDaysRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long_NoDays_4_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withDaysRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long_NoDays_5_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withDaysRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long_NoDays_6_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withDaysRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long_NoDays_7_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withDaysRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_long_long_NoDays_8_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withDaysRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long_long_NoHours_1_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withHoursRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_long_long_NoHours_2_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withHoursRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long_NoHours_3_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withHoursRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long_NoHours_4_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withHoursRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long_NoHours_5_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withHoursRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long_NoHours_6_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withHoursRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long_NoHours_7_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withHoursRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_long_long_NoHours_8_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withHoursRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long_long_NoMinutes_1_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMinutesRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_long_long_NoMinutes_2_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMinutesRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long_NoMinutes_3_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMinutesRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long_NoMinutes_4_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMinutesRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long_NoMinutes_5_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMinutesRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long_NoMinutes_6_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMinutesRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long_NoMinutes_7_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMinutesRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_long_long_NoMinutes_8_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMinutesRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long_long_NoSeconds_1_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withSecondsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_long_long_NoSeconds_2_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withSecondsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long_NoSeconds_3_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withSecondsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long_NoSeconds_4_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withSecondsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long_NoSeconds_5_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withSecondsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long_NoSeconds_6_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withSecondsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long_NoSeconds_7_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withSecondsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_long_long_NoSeconds_8_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withSecondsRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long_long_NoMillis_1_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMillisRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_long_long_NoMillis_2_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMillisRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_long_long_NoMillis_3_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMillisRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long_long_NoMillis_4_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMillisRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long_long_NoMillis_5_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMillisRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long_long_NoMillis_6_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMillisRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long_long_NoMillis_7_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMillisRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(17, test.getSeconds());
    }

    public void testSetPeriod_long_long_NoMillis_8_oe() {
        MutablePeriod test = new MutablePeriod(PeriodType.standard().withMillisRemoved());
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1.getMillis(), dt2.getMillis());
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_RI_RI1_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1, dt2);
        assertEquals(1, test.getYears());
    }

    public void testSetPeriod_RI_RI1_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1, dt2);
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_RI_RI1_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1, dt2);
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_RI_RI1_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1, dt2);
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_RI_RI1_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1, dt2);
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_RI_RI1_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1, dt2);
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_RI_RI1_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1, dt2);
        assertEquals(17, test.getSeconds());
    }

    public void testSetPeriod_RI_RI1_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt1, dt2);
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_RI_RI2_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2, dt1);
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_RI_RI2_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2, dt1);
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_RI_RI2_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2, dt1);
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_RI_RI2_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2, dt1);
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_RI_RI2_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2, dt1);
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_RI_RI2_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2, dt1);
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_RI_RI2_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2, dt1);
        assertEquals(17, test.getSeconds());
    }

    public void testSetPeriod_RI_RI2_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(dt2, dt1);
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_RI_RI3_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1, dt1);
        assertEquals(1, test.getYears());
    }

    public void testSetPeriod_RI_RI3_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1, dt1);
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_RI_RI3_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1, dt1);
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_RI_RI3_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1, dt1);
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_RI_RI3_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1, dt1);
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_RI_RI3_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1, dt1);
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_RI_RI3_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1, dt1);
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_RI_RI3_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        test.setPeriod(dt1, dt1);
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_RInterval1_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(new Interval(dt1, dt2));
        assertEquals(1, test.getYears());
    }

    public void testSetPeriod_RInterval1_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(new Interval(dt1, dt2));
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_RInterval1_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(new Interval(dt1, dt2));
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_RInterval1_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(new Interval(dt1, dt2));
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_RInterval1_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(new Interval(dt1, dt2));
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_RInterval1_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(new Interval(dt1, dt2));
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_RInterval1_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(new Interval(dt1, dt2));
        assertEquals(17, test.getSeconds());
    }

    public void testSetPeriod_RInterval1_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        DateTime dt1 = new DateTime(2002, 6, 9, 13, 15, 17, 19);
        DateTime dt2 = new DateTime(2003, 7, 17, 14, 16, 18, 20);
        test.setPeriod(new Interval(dt1, dt2));
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_RInterval2_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableInterval) null);
        assertEquals(1, test.getYears());
    }

    public void testSetPeriod_RInterval2_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableInterval) null);
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_RInterval2_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableInterval) null);
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_RInterval2_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableInterval) null);
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_RInterval2_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableInterval) null);
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_RInterval2_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableInterval) null);
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_RInterval2_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableInterval) null);
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_RInterval2_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableInterval) null);
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long1_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(100L);
        assertEquals(100, test.getYears());
    }

    public void testSetPeriod_long1_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(100L);
        assertEquals(100, test.getMonths());
    }

    public void testSetPeriod_long1_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(100L);
        assertEquals(100, test.getWeeks());
    }

    public void testSetPeriod_long1_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(100L);
        assertEquals(100, test.getDays());
    }

    public void testSetPeriod_long1_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(100L);
        assertEquals(100, test.getHours());
    }

    public void testSetPeriod_long1_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(100L);
        assertEquals(5, test.getMinutes());
    }

    public void testSetPeriod_long1_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(100L);
        assertEquals(6, test.getSeconds());
    }

    public void testSetPeriod_long1_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(100L);
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_long2_1_oe() {
        MutablePeriod test = new MutablePeriod();
        test.setPeriod(
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L);
        assertEquals(0, test.getYears());
    }

    public void testSetPeriod_long2_2_oe() {
        MutablePeriod test = new MutablePeriod();
        test.setPeriod(
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L);
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_long2_3_oe() {
        MutablePeriod test = new MutablePeriod();
        test.setPeriod(
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L);
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_long2_4_oe() {
        MutablePeriod test = new MutablePeriod();
        test.setPeriod(
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L);
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_long2_5_oe() {
        MutablePeriod test = new MutablePeriod();
        test.setPeriod(
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L);
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_long2_6_oe() {
        MutablePeriod test = new MutablePeriod();
        test.setPeriod(
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L);
        assertEquals(7, test.getSeconds());
    }

    public void testSetPeriod_long2_7_oe() {
        MutablePeriod test = new MutablePeriod();
        test.setPeriod(
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L);
        assertEquals(8, test.getMillis());
    }

    public void testSetPeriod_RD1_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new Duration(100L));
        assertEquals(100, test.getYears());
    }

    public void testSetPeriod_RD1_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new Duration(100L));
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_RD1_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new Duration(100L));
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_RD1_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new Duration(100L));
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_RD1_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new Duration(100L));
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_RD1_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new Duration(100L));
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_RD1_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new Duration(100L));
        assertEquals(7, test.getSeconds());
    }

    public void testSetPeriod_RD1_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod(new Duration(100L));
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_RD2_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.setPeriod(new Duration(length));
        assertEquals(1, test.getYears());
    }

    public void testSetPeriod_RD2_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.setPeriod(new Duration(length));
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_RD2_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.setPeriod(new Duration(length));
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_RD2_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.setPeriod(new Duration(length));
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_RD2_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.setPeriod(new Duration(length));
        assertEquals(0, test.getMinutes());
    }

    public void testSetPeriod_RD2_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.setPeriod(new Duration(length));
        assertEquals(7, test.getSeconds());
    }

    public void testSetPeriod_RD2_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.setPeriod(new Duration(length));
        assertEquals(0, test.getMillis());
    }

    public void testSetPeriod_RD3_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableDuration) null);
        assertEquals(1, test.getYears());
    }

    public void testSetPeriod_RD3_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableDuration) null);
        assertEquals(0, test.getMonths());
    }

    public void testSetPeriod_RD3_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableDuration) null);
        assertEquals(0, test.getWeeks());
    }

    public void testSetPeriod_RD3_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableDuration) null);
        assertEquals(0, test.getDays());
    }

    public void testSetPeriod_RD3_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableDuration) null);
        assertEquals(0, test.getHours());
    }

    public void testSetPeriod_RD3_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableDuration) null);
        assertEquals(5, test.getMinutes());
    }

    public void testSetPeriod_RD3_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableDuration) null);
        assertEquals(0, test.getSeconds());
    }

    public void testSetPeriod_RD3_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.setPeriod((ReadableDuration) null);
        assertEquals(0, test.getMillis());
    }

    public void testAdd_8ints1_1_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(1, test.getYears());
    }

    public void testAdd_8ints1_2_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(2, test.getMonths());
    }

    public void testAdd_8ints1_3_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_8ints1_4_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(1, test.getDays());
    }

    public void testAdd_8ints1_5_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(0, test.getHours());
    }

    public void testAdd_8ints1_6_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(5, test.getMinutes());
    }

    public void testAdd_8ints1_7_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_8ints1_8_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(0, test.getMillis());
    }

    public void testAdd_8ints2_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        try {
            test.add(1, 2, 3, 4, 5, 6, 7, 8);
        } catch (IllegalArgumentException ex) {}
        assertEquals(100, test.getYears());
    }

    public void testAdd_8ints2_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        try {
            test.add(1, 2, 3, 4, 5, 6, 7, 8);
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getMonths());
    }

    public void testAdd_8ints2_4_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        try {
            test.add(1, 2, 3, 4, 5, 6, 7, 8);
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_8ints2_5_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        try {
            test.add(1, 2, 3, 4, 5, 6, 7, 8);
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getDays());
    }

    public void testAdd_8ints2_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        try {
            test.add(1, 2, 3, 4, 5, 6, 7, 8);
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getHours());
    }

    public void testAdd_8ints2_7_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        try {
            test.add(1, 2, 3, 4, 5, 6, 7, 8);
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_8ints2_8_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        try {
            test.add(1, 2, 3, 4, 5, 6, 7, 8);
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_8ints2_9_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        try {
            test.add(1, 2, 3, 4, 5, 6, 7, 8);
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getMillis());
    }

    public void testAdd_long1_1_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L);
        assertEquals(100, test.getYears());
    }

    public void testAdd_long1_2_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L);
        assertEquals(0, test.getMonths());
    }

    public void testAdd_long1_4_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L);
        assertEquals(100, test.getDays());
    }

    public void testAdd_long1_5_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L);
        assertEquals(0, test.getHours());
    }

    public void testAdd_long1_6_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L);
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_long1_7_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L);
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_long1_8_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L);
        assertEquals(0, test.getMillis());
    }

    public void testAdd_long2_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms);
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_long2_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms);
        assertEquals(3, test.getDays());
    }

    public void testAdd_long2_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms);
        assertEquals(7, test.getSeconds());
    }

    public void testAdd_long2_7_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms);
        assertEquals(8L, test.getMillis());
    }

    public void testAdd_long3_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(2100L);
        assertEquals(2100, test.getYears());
    }

    public void testAdd_long3_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(2100L);
        assertEquals(2, test.getMonths());
    }

    public void testAdd_long3_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(2100L);
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_long3_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(2100L);
        assertEquals(5, test.getDays());
    }

    public void testAdd_long3_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(2100L);
        assertEquals(0, test.getHours());
    }

    public void testAdd_long3_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(2100L);
        assertEquals(5, test.getMinutes());
    }

    public void testAdd_long3_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(2100L);
        assertEquals(6, test.getSeconds());
    }

    public void testAdd_long3_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add(2100L);
        assertEquals(0, test.getMillis());
    }

    public void testAdd_long_Chronology1_1_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L, ISOChronology.getInstance());
        assertEquals(0, test.getYears());
    }

    public void testAdd_long_Chronology1_2_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L, ISOChronology.getInstance());
        assertEquals(0, test.getMonths());
    }

    public void testAdd_long_Chronology1_3_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L, ISOChronology.getInstance());
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_long_Chronology1_4_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L, ISOChronology.getInstance());
        assertEquals(100, test.getDays());
    }

    public void testAdd_long_Chronology1_5_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L, ISOChronology.getInstance());
        assertEquals(0, test.getHours());
    }

    public void testAdd_long_Chronology1_6_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L, ISOChronology.getInstance());
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_long_Chronology1_7_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L, ISOChronology.getInstance());
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_long_Chronology1_8_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(100L, ISOChronology.getInstance());
        assertEquals(0, test.getMillis());
    }

    public void testAdd_long_Chronology2_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstance());
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_long_Chronology2_4_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstance());
        assertEquals(0, test.getHours());
    }

    public void testAdd_long_Chronology2_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstance());
        assertEquals(7, test.getSeconds());
    }

    public void testAdd_long_Chronology2_7_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstance());
        assertEquals(0L, test.getMillis());
    }

    public void testAdd_long_Chronology3_1_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstanceUTC());
        assertEquals(0, test.getYears());
    }

    public void testAdd_long_Chronology3_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstanceUTC());
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_long_Chronology3_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstanceUTC());
        assertEquals(0, test.getDays());
    }

    public void testAdd_long_Chronology3_4_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstanceUTC());
        assertEquals(0, test.getHours());
    }

    public void testAdd_long_Chronology3_5_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstanceUTC());
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_long_Chronology3_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstanceUTC());
        assertEquals(7, test.getSeconds());
    }

    public void testAdd_long_Chronology3_7_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        long ms =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(ms, ISOChronology.getInstanceUTC());
        assertEquals(0L, test.getMillis());
    }

    public void testAdd_RD1_1_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Duration(100L));
        assertEquals(1, test.getYears());
    }

    public void testAdd_RD1_2_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Duration(100L));
        assertEquals(1, test.getMonths());
    }

    public void testAdd_RD1_3_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Duration(100L));
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RD1_4_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Duration(100L));
        assertEquals(0, test.getDays());
    }

    public void testAdd_RD1_6_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Duration(100L));
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_RD1_7_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Duration(100L));
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_RD1_8_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Duration(100L));
        assertEquals(100L, test.getMillis());
    }

    public void testAdd_RD2_1_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        long ms =
            (4L + (3L * 7L)) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(new Duration(ms));
        assertEquals(1, test.getYears());
    }

    public void testAdd_RD2_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        long ms =
            (4L + (3L * 7L)) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(new Duration(ms));
        assertEquals(0, test.getMonths());
    }

    public void testAdd_RD2_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        long ms =
            (4L + (3L * 7L)) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(new Duration(ms));
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RD2_4_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        long ms =
            (4L + (3L * 7L)) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(new Duration(ms));
        assertEquals(0, test.getDays());
    }

    public void testAdd_RD2_5_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        long ms =
            (4L + (3L * 7L)) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(new Duration(ms));
        assertEquals(0, test.getHours());
    }

    public void testAdd_RD2_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        long ms =
            (4L + (3L * 7L)) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(new Duration(ms));
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_RD2_7_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        long ms =
            (4L + (3L * 7L)) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(new Duration(ms));
        assertEquals(7, test.getSeconds());
    }

    public void testAdd_RD2_8_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        long ms =
            (4L + (3L * 7L)) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        test.add(new Duration(ms));
        assertEquals(8L, test.getMillis());
    }

    public void testAdd_RD3_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableDuration) null);
        assertEquals(1, test.getYears());
    }

    public void testAdd_RD3_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableDuration) null);
        assertEquals(2, test.getMonths());
    }

    public void testAdd_RD3_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableDuration) null);
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RD3_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableDuration) null);
        assertEquals(5, test.getDays());
    }

    public void testAdd_RD3_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableDuration) null);
        assertEquals(0, test.getHours());
    }

    public void testAdd_RD3_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableDuration) null);
        assertEquals(5, test.getMinutes());
    }

    public void testAdd_RD3_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableDuration) null);
        assertEquals(6, test.getSeconds());
    }

    public void testAdd_RD3_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableDuration) null);
        assertEquals(0, test.getMillis());
    }

    public void testAdd_RP1_1_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Period(100L));
        assertEquals(1, test.getYears());
    }

    public void testAdd_RP1_2_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Period(100L));
        assertEquals(1, test.getMonths());
    }

    public void testAdd_RP1_3_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Period(100L));
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RP1_4_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Period(100L));
        assertEquals(0, test.getDays());
    }

    public void testAdd_RP1_5_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Period(100L));
        assertEquals(0, test.getHours());
    }

    public void testAdd_RP1_6_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Period(100L));
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_RP1_7_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Period(100L));
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_RP1_8_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Period(100L));
        assertEquals(0, test.getMillis());
    }

    public void testAdd_RP2_1_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());  // All type
        test.add(new Period(1, 2, 3, 4, 5, 6, 7, 0, PeriodType.standard().withMillisRemoved()));
        assertEquals(1, test.getYears());
    }

    public void testAdd_RP2_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());  // All type
        test.add(new Period(1, 2, 3, 4, 5, 6, 7, 0, PeriodType.standard().withMillisRemoved()));
        assertEquals(2, test.getMonths());
    }

    public void testAdd_RP2_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());  // All type
        test.add(new Period(1, 2, 3, 4, 5, 6, 7, 0, PeriodType.standard().withMillisRemoved()));
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RP2_4_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());  // All type
        test.add(new Period(1, 2, 3, 4, 5, 6, 7, 0, PeriodType.standard().withMillisRemoved()));
        assertEquals(0, test.getDays());
    }

    public void testAdd_RP2_5_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());  // All type
        test.add(new Period(1, 2, 3, 4, 5, 6, 7, 0, PeriodType.standard().withMillisRemoved()));
        assertEquals(0, test.getHours());
    }

    public void testAdd_RP2_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());  // All type
        test.add(new Period(1, 2, 3, 4, 5, 6, 7, 0, PeriodType.standard().withMillisRemoved()));
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_RP2_7_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());  // All type
        test.add(new Period(1, 2, 3, 4, 5, 6, 7, 0, PeriodType.standard().withMillisRemoved()));
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_RP2_8_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());  // All type
        test.add(new Period(1, 2, 3, 4, 5, 6, 7, 0, PeriodType.standard().withMillisRemoved()));
        assertEquals(0, test.getMillis());
    }

    public void testAdd_RP3_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        test.add(new Period(0L));
        assertEquals(0, test.getMonths());
    }

    public void testAdd_RP3_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        test.add(new Period(0L));
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RP3_5_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        test.add(new Period(0L));
        assertEquals(0, test.getHours());
    }

    public void testAdd_RP3_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        test.add(new Period(0L));
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_RP3_8_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.standard());
        test.add(new Period(0L));
        assertEquals(0, test.getMillis());
    }

    public void testAdd_RP4_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        try {
            test.add(new Period(1, 2, 3, 4, 5, 6, 7, 8));  // cannot set weeks
        } catch (IllegalArgumentException ex) {}
        assertEquals(1, test.getYears());
    }

    public void testAdd_RP4_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        try {
            test.add(new Period(1, 2, 3, 4, 5, 6, 7, 8));  // cannot set weeks
        } catch (IllegalArgumentException ex) {}
        assertEquals(5, test.getMonths());
    }

    public void testAdd_RP4_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        try {
            test.add(new Period(1, 2, 3, 4, 5, 6, 7, 8));  // cannot set weeks
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RP4_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        try {
            test.add(new Period(1, 2, 3, 4, 5, 6, 7, 8));  // cannot set weeks
        } catch (IllegalArgumentException ex) {}
        assertEquals(5, test.getDays());
    }

    public void testAdd_RP4_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        try {
            test.add(new Period(1, 2, 3, 4, 5, 6, 7, 8));  // cannot set weeks
        } catch (IllegalArgumentException ex) {}
        assertEquals(4, test.getHours());
    }

    public void testAdd_RP4_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        try {
            test.add(new Period(1, 2, 3, 4, 5, 6, 7, 8));  // cannot set weeks
        } catch (IllegalArgumentException ex) {}
        assertEquals(5, test.getMinutes());
    }

    public void testAdd_RP4_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        try {
            test.add(new Period(1, 2, 3, 4, 5, 6, 7, 8));  // cannot set weeks
        } catch (IllegalArgumentException ex) {}
        assertEquals(8, test.getSeconds());
    }

    public void testAdd_RP4_9_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        try {
            test.add(new Period(1, 2, 3, 4, 5, 6, 7, 8));  // cannot set weeks
        } catch (IllegalArgumentException ex) {}
        assertEquals(0, test.getMillis());
    }

    public void testAdd_RP5_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        test.add(new Period(1, 2, 0, 4, 5, 6, 7, 8));  // can set weeks as zero
        assertEquals(1, test.getYears());
    }

    public void testAdd_RP5_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        test.add(new Period(1, 2, 0, 4, 5, 6, 7, 8));  // can set weeks as zero
        assertEquals(0, test.getMonths());
    }

    public void testAdd_RP5_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        test.add(new Period(1, 2, 0, 4, 5, 6, 7, 8));  // can set weeks as zero
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RP5_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        test.add(new Period(1, 2, 0, 4, 5, 6, 7, 8));  // can set weeks as zero
        assertEquals(8, test.getDays());
    }

    public void testAdd_RP5_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        test.add(new Period(1, 2, 0, 4, 5, 6, 7, 8));  // can set weeks as zero
        assertEquals(8, test.getHours());
    }

    public void testAdd_RP5_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        test.add(new Period(1, 2, 0, 4, 5, 6, 7, 8));  // can set weeks as zero
        assertEquals(5, test.getMinutes());
    }

    public void testAdd_RP5_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        test.add(new Period(1, 2, 0, 4, 5, 6, 7, 8));  // can set weeks as zero
        assertEquals(8, test.getSeconds());
    }

    public void testAdd_RP5_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.yearMonthDayTime());
        test.add(new Period(1, 2, 0, 4, 5, 6, 7, 8));  // can set weeks as zero
        assertEquals(0, test.getMillis());
    }

    public void testAdd_RP6_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadablePeriod) null);
        assertEquals(1, test.getYears());
    }

    public void testAdd_RP6_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadablePeriod) null);
        assertEquals(2, test.getMonths());
    }

    public void testAdd_RP6_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadablePeriod) null);
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RP6_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadablePeriod) null);
        assertEquals(5, test.getDays());
    }

    public void testAdd_RP6_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadablePeriod) null);
        assertEquals(0, test.getHours());
    }

    public void testAdd_RP6_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadablePeriod) null);
        assertEquals(5, test.getMinutes());
    }

    public void testAdd_RP6_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadablePeriod) null);
        assertEquals(6, test.getSeconds());
    }

    public void testAdd_RP6_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadablePeriod) null);
        assertEquals(0, test.getMillis());
    }

    public void testAdd_RInterval1_1_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Interval(100L, 200L));
        assertEquals(1, test.getYears());
    }

    public void testAdd_RInterval1_2_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Interval(100L, 200L));
        assertEquals(0, test.getMonths());
    }

    public void testAdd_RInterval1_3_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Interval(100L, 200L));
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RInterval1_4_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Interval(100L, 200L));
        assertEquals(0, test.getDays());
    }

    public void testAdd_RInterval1_5_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Interval(100L, 200L));
        assertEquals(0, test.getHours());
    }

    public void testAdd_RInterval1_6_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Interval(100L, 200L));
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_RInterval1_7_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Interval(100L, 200L));
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_RInterval1_8_oe() {
        MutablePeriod test = new MutablePeriod(100L);
        test.add(new Interval(100L, 200L));
        assertEquals(0L, test.getMillis());
    }

    public void testAdd_RInterval2_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 12, 18, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L);  // All type
        test.add(new Interval(dt1, dt2));
        assertEquals(0, test.getYears());
    }

    public void testAdd_RInterval2_2_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 12, 18, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L);  // All type
        test.add(new Interval(dt1, dt2));
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_RInterval2_3_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 12, 18, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L);  // All type
        test.add(new Interval(dt1, dt2));
        assertEquals(0, test.getMillis());
    }

    public void testAdd_RInterval3_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(0L, 0L));
        assertEquals(0, test.getMonths());
    }

    public void testAdd_RInterval3_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(0L, 0L));
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RInterval3_4_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(0L, 0L));
        assertEquals(0, test.getDays());
    }

    public void testAdd_RInterval3_5_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(0L, 0L));
        assertEquals(0, test.getHours());
    }

    public void testAdd_RInterval3_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(0L, 0L));
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_RInterval3_7_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(0L, 0L));
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_RInterval3_8_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(0L, 0L));
        assertEquals(0, test.getMillis());
    }

    public void testAdd_RInterval4_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(dt1, dt2));
        assertEquals(1, test.getYears());
    }

    public void testAdd_RInterval4_2_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(dt1, dt2));
        assertEquals(6, test.getMonths());
    }

    public void testAdd_RInterval4_3_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(dt1, dt2));
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RInterval4_4_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(dt1, dt2));
        assertEquals(0, test.getMinutes());
    }

    public void testAdd_RInterval4_5_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(dt1, dt2));
        assertEquals(0, test.getSeconds());
    }

    public void testAdd_RInterval4_6_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 17, 0, 0, 0, 8);
        MutablePeriod test = new MutablePeriod(100L, PeriodType.yearMonthDayTime());
        test.add(new Interval(dt1, dt2));
        assertEquals(0, test.getMillis());
    }

    public void testAdd_RInterval5_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableInterval) null);
        assertEquals(1, test.getYears());
    }

    public void testAdd_RInterval5_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableInterval) null);
        assertEquals(2, test.getMonths());
    }

    public void testAdd_RInterval5_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableInterval) null);
        assertEquals(0, test.getWeeks());
    }

    public void testAdd_RInterval5_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableInterval) null);
        assertEquals(5, test.getDays());
    }

    public void testAdd_RInterval5_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableInterval) null);
        assertEquals(0, test.getHours());
    }

    public void testAdd_RInterval5_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableInterval) null);
        assertEquals(5, test.getMinutes());
    }

    public void testAdd_RInterval5_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableInterval) null);
        assertEquals(6, test.getSeconds());
    }

    public void testAdd_RInterval5_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.add((ReadableInterval) null);
        assertEquals(0, test.getMillis());
    }

    public void testMergePeriod_RP1_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(0, 0, 0, 14, 15, 16, 17, 18, PeriodType.dayTime()));
        assertEquals(1, test.getYears());
    }

    public void testMergePeriod_RP1_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(0, 0, 0, 14, 15, 16, 17, 18, PeriodType.dayTime()));
        assertEquals(2, test.getMonths());
    }

    public void testMergePeriod_RP1_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(0, 0, 0, 14, 15, 16, 17, 18, PeriodType.dayTime()));
        assertEquals(1, test.getWeeks());
    }

    public void testMergePeriod_RP1_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(0, 0, 0, 14, 15, 16, 17, 18, PeriodType.dayTime()));
        assertEquals(14, test.getDays());
    }

    public void testMergePeriod_RP1_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(0, 0, 0, 14, 15, 16, 17, 18, PeriodType.dayTime()));
        assertEquals(0, test.getHours());
    }

    public void testMergePeriod_RP1_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(0, 0, 0, 14, 15, 16, 17, 18, PeriodType.dayTime()));
        assertEquals(5, test.getMinutes());
    }

    public void testMergePeriod_RP1_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(0, 0, 0, 14, 15, 16, 17, 18, PeriodType.dayTime()));
        assertEquals(6, test.getSeconds());
    }

    public void testMergePeriod_RP1_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(0, 0, 0, 14, 15, 16, 17, 18, PeriodType.dayTime()));
        assertEquals(0, test.getMillis());
    }

    public void testMergePeriod_RP3_1_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.mergePeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getYears());
    }

    public void testMergePeriod_RP3_2_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.mergePeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getMonths());
    }

    public void testMergePeriod_RP3_3_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.mergePeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getWeeks());
    }

    public void testMergePeriod_RP3_4_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.mergePeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getDays());
    }

    public void testMergePeriod_RP3_5_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.mergePeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getHours());
    }

    public void testMergePeriod_RP3_6_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.mergePeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getMinutes());
    }

    public void testMergePeriod_RP3_7_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.mergePeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getSeconds());
    }

    public void testMergePeriod_RP3_8_oe() {
        MutablePeriod test = new MutablePeriod(100L, PeriodType.millis());
        test.mergePeriod(new MutablePeriod(0, 0, 0, 0, 0, 0, 0, 18));
        assertEquals(0, test.getMillis());
    }

    public void testMergePeriod_RP4_1_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(11, test.getYears());
    }

    public void testMergePeriod_RP4_2_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(11, test.getMonths());
    }

    public void testMergePeriod_RP4_3_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(11, test.getWeeks());
    }

    public void testMergePeriod_RP4_4_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(5, test.getDays());
    }

    public void testMergePeriod_RP4_5_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(5, test.getHours());
    }

    public void testMergePeriod_RP4_6_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(5, test.getMinutes());
    }

    public void testMergePeriod_RP4_7_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(8, test.getSeconds());
    }

    public void testMergePeriod_RP4_8_oe() {
        MutablePeriod test = new MutablePeriod(0, 0, 0, 0, 5, 6, 7, 8);
        test.mergePeriod(new MutablePeriod(11, 12, 13, 14, 15, 16, 17, 18));
        assertEquals(5, test.getMillis());
    }

    public void testMergePeriod_RP5_1_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod((ReadablePeriod) null);
        assertEquals(1, test.getYears());
    }

    public void testMergePeriod_RP5_2_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod((ReadablePeriod) null);
        assertEquals(2, test.getMonths());
    }

    public void testMergePeriod_RP5_3_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod((ReadablePeriod) null);
        assertEquals(0, test.getWeeks());
    }

    public void testMergePeriod_RP5_4_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod((ReadablePeriod) null);
        assertEquals(5, test.getDays());
    }

    public void testMergePeriod_RP5_5_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod((ReadablePeriod) null);
        assertEquals(0, test.getHours());
    }

    public void testMergePeriod_RP5_6_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod((ReadablePeriod) null);
        assertEquals(5, test.getMinutes());
    }

    public void testMergePeriod_RP5_7_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod((ReadablePeriod) null);
        assertEquals(0, test.getSeconds());
    }

    public void testMergePeriod_RP5_8_oe() {
        MutablePeriod test = new MutablePeriod(1, 2, 3, 4, 5, 6, 7, 8);
        test.mergePeriod((ReadablePeriod) null);
        assertEquals(0, test.getMillis());
    }

}
