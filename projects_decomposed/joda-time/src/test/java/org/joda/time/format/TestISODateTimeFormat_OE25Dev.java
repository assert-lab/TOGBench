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
package org.joda.time.format;

import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Partial;

/**
 * This class is a Junit unit test for ISODateTimeFormat.
 *
 * @author Stephen Colebourne
 */
public class TestISODateTimeFormat_OE25Dev extends TestCase {

    private static final DateTimeZone UTC = DateTimeZone.UTC;
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");

    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    // 2002-06-09
    private long TEST_TIME_NOW =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;

    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestISODateTimeFormat_OE25Dev.class);
    }

    public TestISODateTimeFormat_OE25Dev(String name) {
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
    public void testSubclassableConstructor() {
        ISODateTimeFormat f = new ISODateTimeFormat() {
            // test constructor is protected
        };
        assertNotNull(f);
    }

    //-----------------------------------------------------------------------
    public void testFormat_date() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09",ISODateTimeFormat.date().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09",ISODateTimeFormat.date().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09",ISODateTimeFormat.date().print(dt));
    }

    public void testFormat_date_partial() {
        Partial dt = new Partial(
                new DateTimeFieldType[] {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth()},
                new int[] {2004, 6, 9});
        assertEquals("2004-06-09",ISODateTimeFormat.date().print(dt));
    }

    public void testFormat_time() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("10:20:30.040Z",ISODateTimeFormat.time().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("11:20:30.040+01:00",ISODateTimeFormat.time().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("12:20:30.040+02:00",ISODateTimeFormat.time().print(dt));
    }

    public void testFormat_time_partial() {
        Partial dt = new Partial(
                new DateTimeFieldType[] {DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(),
                        DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()},
                new int[] {10, 20, 30, 40});
        assertEquals("10:20:30.040",ISODateTimeFormat.time().print(dt));
    }

    public void testFormat_timeNoMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("10:20:30Z",ISODateTimeFormat.timeNoMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("11:20:30+01:00",ISODateTimeFormat.timeNoMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("12:20:30+02:00",ISODateTimeFormat.timeNoMillis().print(dt));
    }

    public void testFormat_timeNoMillis_partial() {
        Partial dt = new Partial(
                new DateTimeFieldType[] {DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(),
                        DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()},
                new int[] {10, 20, 30, 40});
        assertEquals("10:20:30",ISODateTimeFormat.timeNoMillis().print(dt));
    }

    public void testFormat_tTime() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("T10:20:30.040Z",ISODateTimeFormat.tTime().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("T11:20:30.040+01:00",ISODateTimeFormat.tTime().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("T12:20:30.040+02:00",ISODateTimeFormat.tTime().print(dt));
    }

    public void testFormat_tTimeNoMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("T10:20:30Z",ISODateTimeFormat.tTimeNoMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("T11:20:30+01:00",ISODateTimeFormat.tTimeNoMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("T12:20:30+02:00",ISODateTimeFormat.tTimeNoMillis().print(dt));
    }

    public void testFormat_dateTime() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09T10:20:30.040Z",ISODateTimeFormat.dateTime().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T11:20:30.040+01:00",ISODateTimeFormat.dateTime().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T12:20:30.040+02:00",ISODateTimeFormat.dateTime().print(dt));
        
//        dt = dt.withZone(LONDON);
//        assertEquals("2004-06-09T11:20:30.040+01:00",ISODateTimeFormat.getInstance(PARIS).dateTime().print(dt));
//        
//        dt = dt.withZone(LONDON);
//        assertEquals("2004-06-09T12:20:30.040+02:00",ISODateTimeFormat.dateTime().print(dt.getMillis(),PARIS));
//        
//        dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, CopticChronology.getInstance());
//        assertEquals("2288-02-19T10:20:30.040Z",ISODateTimeFormat.dateTime().print(dt));
//        
//        dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, CopticChronology.getInstance());
//        assertEquals("2004-06-09T10:20:30.040Z",ISODateTimeFormat.getInstance(CopticChronology.getInstance()).dateTime().print(dt));
    }

    public void testFormat_dateTimeNoMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09T10:20:30Z",ISODateTimeFormat.dateTimeNoMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T11:20:30+01:00",ISODateTimeFormat.dateTimeNoMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T12:20:30+02:00",ISODateTimeFormat.dateTimeNoMillis().print(dt));
    }

    public void testFormat_ordinalDate() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-161",ISODateTimeFormat.ordinalDate().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-161",ISODateTimeFormat.ordinalDate().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-161",ISODateTimeFormat.ordinalDate().print(dt));
    }

    public void testFormat_ordinalDateTime() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-161T10:20:30.040Z",ISODateTimeFormat.ordinalDateTime().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-161T11:20:30.040+01:00",ISODateTimeFormat.ordinalDateTime().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-161T12:20:30.040+02:00",ISODateTimeFormat.ordinalDateTime().print(dt));
    }

    public void testFormat_ordinalDateTimeNoMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-161T10:20:30Z",ISODateTimeFormat.ordinalDateTimeNoMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-161T11:20:30+01:00",ISODateTimeFormat.ordinalDateTimeNoMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-161T12:20:30+02:00",ISODateTimeFormat.ordinalDateTimeNoMillis().print(dt));
    }

    public void testFormat_weekDate() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-W24-3",ISODateTimeFormat.weekDate().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-W24-3",ISODateTimeFormat.weekDate().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-W24-3",ISODateTimeFormat.weekDate().print(dt));
    }

    public void testFormat_weekDateTime() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-W24-3T10:20:30.040Z",ISODateTimeFormat.weekDateTime().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-W24-3T11:20:30.040+01:00",ISODateTimeFormat.weekDateTime().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-W24-3T12:20:30.040+02:00",ISODateTimeFormat.weekDateTime().print(dt));
    }

    public void testFormat_weekDateTimeNoMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-W24-3T10:20:30Z",ISODateTimeFormat.weekDateTimeNoMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-W24-3T11:20:30+01:00",ISODateTimeFormat.weekDateTimeNoMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-W24-3T12:20:30+02:00",ISODateTimeFormat.weekDateTimeNoMillis().print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_basicDate() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("20040609",ISODateTimeFormat.basicDate().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("20040609",ISODateTimeFormat.basicDate().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("20040609",ISODateTimeFormat.basicDate().print(dt));
    }

    public void testFormat_basicTime() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("102030.040Z",ISODateTimeFormat.basicTime().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("112030.040+0100",ISODateTimeFormat.basicTime().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("122030.040+0200",ISODateTimeFormat.basicTime().print(dt));
    }

    public void testFormat_basicTimeNoMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("102030Z",ISODateTimeFormat.basicTimeNoMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("112030+0100",ISODateTimeFormat.basicTimeNoMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("122030+0200",ISODateTimeFormat.basicTimeNoMillis().print(dt));
    }

    public void testFormat_basicTTime() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("T102030.040Z",ISODateTimeFormat.basicTTime().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("T112030.040+0100",ISODateTimeFormat.basicTTime().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("T122030.040+0200",ISODateTimeFormat.basicTTime().print(dt));
    }

    public void testFormat_basicTTimeNoMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("T102030Z",ISODateTimeFormat.basicTTimeNoMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("T112030+0100",ISODateTimeFormat.basicTTimeNoMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("T122030+0200",ISODateTimeFormat.basicTTimeNoMillis().print(dt));
    }

    public void testFormat_basicDateTime() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("20040609T102030.040Z",ISODateTimeFormat.basicDateTime().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("20040609T112030.040+0100",ISODateTimeFormat.basicDateTime().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("20040609T122030.040+0200",ISODateTimeFormat.basicDateTime().print(dt));
    }

    public void testFormat_basicDateTimeNoMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("20040609T102030Z",ISODateTimeFormat.basicDateTimeNoMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("20040609T112030+0100",ISODateTimeFormat.basicDateTimeNoMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("20040609T122030+0200",ISODateTimeFormat.basicDateTimeNoMillis().print(dt));
    }

    public void testFormat_basicOrdinalDate() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004161",ISODateTimeFormat.basicOrdinalDate().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004161",ISODateTimeFormat.basicOrdinalDate().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004161",ISODateTimeFormat.basicOrdinalDate().print(dt));
    }

    public void testFormat_basicOrdinalDateTime() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004161T102030.040Z",ISODateTimeFormat.basicOrdinalDateTime().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004161T112030.040+0100",ISODateTimeFormat.basicOrdinalDateTime().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004161T122030.040+0200",ISODateTimeFormat.basicOrdinalDateTime().print(dt));
    }

    public void testFormat_basicOrdinalDateTimeNoMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004161T102030Z",ISODateTimeFormat.basicOrdinalDateTimeNoMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004161T112030+0100",ISODateTimeFormat.basicOrdinalDateTimeNoMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004161T122030+0200",ISODateTimeFormat.basicOrdinalDateTimeNoMillis().print(dt));
    }

    public void testFormat_basicWeekDate() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004W243",ISODateTimeFormat.basicWeekDate().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004W243",ISODateTimeFormat.basicWeekDate().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004W243",ISODateTimeFormat.basicWeekDate().print(dt));
    }

    public void testFormat_basicWeekDateTime() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004W243T102030.040Z",ISODateTimeFormat.basicWeekDateTime().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004W243T112030.040+0100",ISODateTimeFormat.basicWeekDateTime().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004W243T122030.040+0200",ISODateTimeFormat.basicWeekDateTime().print(dt));
    }

    public void testFormat_basicWeekDateTimeNoMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004W243T102030Z",ISODateTimeFormat.basicWeekDateTimeNoMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004W243T112030+0100",ISODateTimeFormat.basicWeekDateTimeNoMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004W243T122030+0200",ISODateTimeFormat.basicWeekDateTimeNoMillis().print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_year() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004",ISODateTimeFormat.year().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004",ISODateTimeFormat.year().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004",ISODateTimeFormat.year().print(dt));
    }

    public void testFormat_yearMonth() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06",ISODateTimeFormat.yearMonth().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06",ISODateTimeFormat.yearMonth().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06",ISODateTimeFormat.yearMonth().print(dt));
    }

    public void testFormat_yearMonthDay() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09",ISODateTimeFormat.yearMonthDay().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09",ISODateTimeFormat.yearMonthDay().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09",ISODateTimeFormat.yearMonthDay().print(dt));
    }

    public void testFormat_weekyear() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004",ISODateTimeFormat.weekyear().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004",ISODateTimeFormat.weekyear().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004",ISODateTimeFormat.weekyear().print(dt));
    }

    public void testFormat_weekyearWeek() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-W24",ISODateTimeFormat.weekyearWeek().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-W24",ISODateTimeFormat.weekyearWeek().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-W24",ISODateTimeFormat.weekyearWeek().print(dt));
    }

    public void testFormat_weekyearWeekDay() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-W24-3",ISODateTimeFormat.weekyearWeekDay().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-W24-3",ISODateTimeFormat.weekyearWeekDay().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-W24-3",ISODateTimeFormat.weekyearWeekDay().print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_hour() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("10",ISODateTimeFormat.hour().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("11",ISODateTimeFormat.hour().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("12",ISODateTimeFormat.hour().print(dt));
    }

    public void testFormat_hourMinute() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("10:20",ISODateTimeFormat.hourMinute().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("11:20",ISODateTimeFormat.hourMinute().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("12:20",ISODateTimeFormat.hourMinute().print(dt));
    }

    public void testFormat_hourMinuteSecond() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("10:20:30",ISODateTimeFormat.hourMinuteSecond().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("11:20:30",ISODateTimeFormat.hourMinuteSecond().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("12:20:30",ISODateTimeFormat.hourMinuteSecond().print(dt));
    }

    public void testFormat_hourMinuteSecondMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("10:20:30.040",ISODateTimeFormat.hourMinuteSecondMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("11:20:30.040",ISODateTimeFormat.hourMinuteSecondMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("12:20:30.040",ISODateTimeFormat.hourMinuteSecondMillis().print(dt));
    }

    public void testFormat_hourMinuteSecondFraction() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("10:20:30.040",ISODateTimeFormat.hourMinuteSecondFraction().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("11:20:30.040",ISODateTimeFormat.hourMinuteSecondFraction().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("12:20:30.040",ISODateTimeFormat.hourMinuteSecondFraction().print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_dateHour() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09T10",ISODateTimeFormat.dateHour().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T11",ISODateTimeFormat.dateHour().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T12",ISODateTimeFormat.dateHour().print(dt));
    }

    public void testFormat_dateHourMinute() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09T10:20",ISODateTimeFormat.dateHourMinute().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T11:20",ISODateTimeFormat.dateHourMinute().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T12:20",ISODateTimeFormat.dateHourMinute().print(dt));
    }

    public void testFormat_dateHourMinuteSecond() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09T10:20:30",ISODateTimeFormat.dateHourMinuteSecond().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T11:20:30",ISODateTimeFormat.dateHourMinuteSecond().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T12:20:30",ISODateTimeFormat.dateHourMinuteSecond().print(dt));
    }

    public void testFormat_dateHourMinuteSecondMillis() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09T10:20:30.040",ISODateTimeFormat.dateHourMinuteSecondMillis().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T11:20:30.040",ISODateTimeFormat.dateHourMinuteSecondMillis().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T12:20:30.040",ISODateTimeFormat.dateHourMinuteSecondMillis().print(dt));
    }

    public void testFormat_dateHourMinuteSecondFraction() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09T10:20:30.040",ISODateTimeFormat.dateHourMinuteSecondFraction().print(dt));
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T11:20:30.040",ISODateTimeFormat.dateHourMinuteSecondFraction().print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T12:20:30.040",ISODateTimeFormat.dateHourMinuteSecondFraction().print(dt));
    }

    public void testSubclassableConstructor_1_oe() {
        ISODateTimeFormat f = new ISODateTimeFormat() {
        };
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", f.getPattern());
    }

    public void testFormat_date_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTime.date());
    }

    public void testFormat_date_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09", dt.toString());
    }

    public void testFormat_date_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09", dt.toString());
    }

    public void testFormat_date_partial_1_oe() {
        Partial dt = new Partial(
                new DateTimeFieldType[] {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth()},
                new int[] {2004, 6, 9});
        assertEquals("2004-06-09", dt.toString());
    }

    public void testFormat_timeNoMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.timeNoMillis());
    }

    public void testFormat_timeNoMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertEquals("HH:mm:ssZZ", DateTimeFormatter.timeNoMillis().toString());
    }

    public void testFormat_timeNoMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertEquals("HH:mm:ssZZ", DateTimeFormatter.timeNoMillis().toString());
    }

    public void testFormat_timeNoMillis_partial_1_oe() {
        Partial dt = new Partial(
                new DateTimeFieldType[] {DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(),
                        DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()},
                new int[] {10, 20, 30, 40});
// incorrect assertion         assertNotNull(Partial.timeNoMillis());
    }

    public void testFormat_tTime_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.tTime());
    }

    public void testFormat_tTime_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_tTime_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_tTimeNoMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.tTimeNoMillis());
    }

    public void testFormat_tTimeNoMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertNotNull(DateTimeFormatter.tTimeNoMillis());
    }

    public void testFormat_tTimeNoMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30+01:00[Europe/Paris]", dt.toString());
    }

    public void testFormat_dateTime_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTime.dateTime());
    }

    public void testFormat_dateTime_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_dateTime_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_dateTimeNoMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.dateTimeNoMillis());
    }

    public void testFormat_dateTimeNoMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertNotNull(DateTimeFormatter.dateTimeNoMillis());
    }

    public void testFormat_dateTimeNoMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T11:20:30Z", dt.toString());
    }

    public void testFormat_ordinalDate_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.ordinalDate());
    }

    public void testFormat_ordinalDate_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertNotNull(DateTimeFormatter.ordinalDate());
    }

    public void testFormat_ordinalDate_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertNotNull(DateTimeFormatter.ordinalDate());
    }

    public void testFormat_ordinalDateTime_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTime.ordinalDateTime());
    }

    public void testFormat_ordinalDateTime_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-069T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_ordinalDateTime_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-069T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_ordinalDateTimeNoMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.ordinalDateTimeNoMillis());
    }

    public void testFormat_ordinalDateTimeNoMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertNotNull(DateTimeFormatter.ordinalDateTimeNoMillis());
    }

    public void testFormat_ordinalDateTimeNoMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertNotNull(DateTimeFormatter.ordinalDateTimeNoMillis());
    }

    public void testFormat_weekDate_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(weekDate());
    }

    public void testFormat_weekDate_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertEquals("2004-W29-6", dt.weekDate().toString());
    }

    public void testFormat_weekDate_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertEquals("2004-W29-6", dt.weekDate().toString());
    }

    public void testFormat_weekDateTime_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(weekDateTime());
    }

    public void testFormat_weekDateTime_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertNotNull(DateTime.weekDateTime());
    }

    public void testFormat_weekDateTime_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-W29-6T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_weekDateTimeNoMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.weekDateTimeNoMillis());
    }

    public void testFormat_weekDateTimeNoMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-W29-6T10:20:30Z", dt.toString());
    }

    public void testFormat_weekDateTimeNoMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-W29-6T10:20:30Z", dt.toString());
    }

    public void testFormat_basicDate_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicDate());
    }

    public void testFormat_basicDate_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.040000400Z", dt.toString());
    }

    public void testFormat_basicDate_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.040000400Z", dt.toString());
    }

    public void testFormat_basicTime_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicTime());
    }

    public void testFormat_basicTime_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

    public void testFormat_basicTime_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

    public void testFormat_basicTimeNoMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicTimeNoMillis());
    }

    public void testFormat_basicTimeNoMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

    public void testFormat_basicTimeNoMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_basicTTime_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicTTime());
    }

    public void testFormat_basicTTime_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_basicTTime_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_basicTTimeNoMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicTTimeNoMillis());
    }

    public void testFormat_basicTTimeNoMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30Z", dt.toString());
    }

    public void testFormat_basicTTimeNoMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30Z", dt.toString());
    }

    public void testFormat_basicDateTime_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTime.basicDateTime());
    }

    public void testFormat_basicDateTime_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("20040609T102030.400Z", dt.toString());
    }

    public void testFormat_basicDateTime_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

    public void testFormat_basicDateTimeNoMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicDateTimeNoMillis());
    }

    public void testFormat_basicDateTimeNoMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("20040609T102030Z", dt.toString());
    }

    public void testFormat_basicDateTimeNoMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("20040609T102030Z", dt.toString());
    }

    public void testFormat_basicOrdinalDate_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicOrdinalDate());
    }

    public void testFormat_basicOrdinalDate_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicOrdinalDate());
    }

    public void testFormat_basicOrdinalDate_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004069", dt.toString());
    }

    public void testFormat_basicOrdinalDateTime_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicOrdinalDateTime());
    }

    public void testFormat_basicOrdinalDateTime_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004069'T'102030.400+0100", dt.toString());
    }

    public void testFormat_basicOrdinalDateTime_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-069T102030.400+0100", dt.toString());
    }

    public void testFormat_basicOrdinalDateTimeNoMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicOrdinalDateTimeNoMillis());
    }

    public void testFormat_basicOrdinalDateTimeNoMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004069T102030Z", dt.toString());
    }

    public void testFormat_basicOrdinalDateTimeNoMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004069T102030Z", dt.toString());
    }

    public void testFormat_basicWeekDate_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicWeekDate());
    }

    public void testFormat_basicWeekDate_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.040000400Z", dt.toString());
    }

    public void testFormat_basicWeekDate_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09", dt.toString());
    }

    public void testFormat_basicWeekDateTime_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicWeekDateTime());
    }

    public void testFormat_basicWeekDateTime_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

    public void testFormat_basicWeekDateTime_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.040+0100", dt.toString());
    }

    public void testFormat_basicWeekDateTimeNoMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.basicWeekDateTimeNoMillis());
    }

    public void testFormat_basicWeekDateTimeNoMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30Z", dt.toString());
    }

    public void testFormat_basicWeekDateTimeNoMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30Z", dt.toString());
    }

    public void testFormat_year_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertEquals("yyyy", DateTimeFormatter.year().toString());
    }

    public void testFormat_year_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertEquals("yyyy", DateTimeFormatter.year().toString());
    }

    public void testFormat_year_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertEquals("yyyy", DateTimeFormatter.year().toString());
    }

    public void testFormat_yearMonth_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.yearMonth());
    }

    public void testFormat_yearMonth_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertEquals("2004-06", dt.yearMonth().toString());
    }

    public void testFormat_yearMonth_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertEquals("2004-06", dt.yearMonth().toString());
    }

    public void testFormat_yearMonthDay_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.yearMonthDay());
    }

    public void testFormat_yearMonthDay_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertEquals("2004-06-09", dt.yearMonthDay().print(dt));
    }

    public void testFormat_yearMonthDay_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09", dt.toString());
    }

    public void testFormat_weekyear_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004", dt.weekyear());
    }

    public void testFormat_weekyear_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004", dt.weekyear().toString());
    }

    public void testFormat_weekyear_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004", dt.weekyear().toString());
    }

    public void testFormat_weekyearWeek_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertEquals("G", DateTimeFormatter.weekyearWeek().getPattern());
    }

    public void testFormat_weekyearWeek_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertNotNull(weekyearWeek());
    }

    public void testFormat_weekyearWeek_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-W29", dt.toString());
    }

    public void testFormat_weekyearWeekDay_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(weekyearWeekDay());
    }

    public void testFormat_weekyearWeekDay_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertEquals("2004-W29-6", dt.weekyearWeekDay().toString());
    }

    public void testFormat_weekyearWeekDay_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertEquals("2004-W29-6", dt.weekyearWeekDay().toString());
    }

    public void testFormat_hour_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertEquals("HH", DateTimeFormatter.hour().getPattern());
    }

    public void testFormat_hour_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertEquals("HH", DateTimeFormatter.hour().toString());
    }

    public void testFormat_hour_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertEquals("HH", DateTimeFormatter.hour().toString());
    }

    public void testFormat_hourMinute_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.hourMinute());
    }

    public void testFormat_hourMinute_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertEquals("10:20", dt.hourMinute().toString());
    }

    public void testFormat_hourMinute_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertEquals("10:20", dt.hourMinute().toString());
    }

    public void testFormat_hourMinuteSecond_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.hourMinuteSecond());
    }

    public void testFormat_hourMinuteSecond_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

    public void testFormat_hourMinuteSecond_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.400+01:00[Europe/Paris]", dt.toString());
    }

    public void testFormat_hourMinuteSecondMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.hourMinuteSecondMillis());
    }

    public void testFormat_hourMinuteSecondMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

    public void testFormat_hourMinuteSecondMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.400+01:00[Europe/Paris]", dt.toString());
    }

    public void testFormat_hourMinuteSecondFraction_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.hourMinuteSecondFraction());
    }

    public void testFormat_hourMinuteSecondFraction_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

    public void testFormat_hourMinuteSecondFraction_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertEquals("HH:mm:ss.SSS", DateTimeFormatter.hourMinuteSecondFraction().toString());
    }

    public void testFormat_dateHour_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09T10", dt.toString());
    }

    public void testFormat_dateHour_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
// incorrect assertion         assertEquals("2004-06-09T10", DateTime.dateHour().toString());
    }

    public void testFormat_dateHour_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
// incorrect assertion         assertEquals("2004-06-09T10", DateTime.dateHour().toString());
    }

    public void testFormat_dateHourMinute_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09T10:20", dt.toString());
    }

    public void testFormat_dateHourMinute_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20", dt.toString());
    }

    public void testFormat_dateHourMinute_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20", dt.toString());
    }

    public void testFormat_dateHourMinuteSecond_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("2004-06-09T10:20:30", dt.toString());
    }

    public void testFormat_dateHourMinuteSecond_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30", dt.toString());
    }

    public void testFormat_dateHourMinuteSecond_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30", dt.toString());
    }

    public void testFormat_dateHourMinuteSecondMillis_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.dateHourMinuteSecondMillis());
    }

    public void testFormat_dateHourMinuteSecondMillis_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

    public void testFormat_dateHourMinuteSecondMillis_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

    public void testFormat_dateHourMinuteSecondFraction_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
// incorrect assertion         assertNotNull(DateTimeFormatter.dateHourMinuteSecondFraction());
    }

    public void testFormat_dateHourMinuteSecondFraction_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        assertEquals("2004-06-09T10:20:30.400", dt.toString());
    }

    public void testFormat_dateHourMinuteSecondFraction_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(LONDON);
        
        dt = dt.withZone(PARIS);
        assertEquals("2004-06-09T10:20:30.400+0100", dt.toString());
    }

}
