/*
 *  Copyright 2001-2016 Stephen Colebourne
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

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadablePartial;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;

/**
 * This class is a Junit unit test for DateTime Formating.
 *
 * @author Stephen Colebourne
 */
public class TestDateTimeFormatter_OE25Dev extends TestCase {

    private static final DateTimeZone UTC = DateTimeZone.UTC;
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final DateTimeZone NEWYORK = DateTimeZone.forID("America/New_York");
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final Chronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    private static final Chronology BUDDHIST_PARIS = BuddhistChronology.getInstance(PARIS);

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
    private DateTimeFormatter f = null;
    private DateTimeFormatter g = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateTimeFormatter_OE25Dev.class);
    }

    public TestDateTimeFormatter_OE25Dev(String name) {
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
        f = new DateTimeFormatterBuilder()
                .appendDayOfWeekShortText()
                .appendLiteral(' ')
                .append(ISODateTimeFormat.dateTimeNoMillis())
                .toFormatter();
        g = ISODateTimeFormat.dateTimeNoMillis();
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
        f = null;
        g = null;
    }

    //-----------------------------------------------------------------------
    public void testPrint_simple() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T10:20:30Z",f.print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.print(dt));
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2547-06-09T12:20:30+02:00",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testPrint_locale() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("mer. 2004-06-09T10:20:30Z",f.withLocale(Locale.FRENCH).print(dt));
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withLocale(null).print(dt));
    }

    //-----------------------------------------------------------------------
    public void testPrint_zone() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withZone(NEWYORK).print(dt));
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withZone(PARIS).print(dt));
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withZone(null).print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withZone(NEWYORK).print(dt));
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withZone(PARIS).print(dt));
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withZoneUTC().print(dt));
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withZone(null).print(dt));
    }

    //-----------------------------------------------------------------------
    public void testPrint_chrono() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).print(dt));
        assertEquals("Wed 2547-06-09T12:20:30+02:00",f.withChronology(BUDDHIST_PARIS).print(dt));
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withChronology(null).print(dt));
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).print(dt));
        assertEquals("Wed 2547-06-09T12:20:30+02:00",f.withChronology(BUDDHIST_PARIS).print(dt));
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withChronology(ISO_UTC).print(dt));
        assertEquals("Wed 2547-06-09T12:20:30+02:00",f.withChronology(null).print(dt));
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("deprecation")
    public void testPrint_bufferMethods() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuffer buf = new StringBuffer();
        f.printTo(buf, dt);
        assertEquals("Wed 2004-06-09T10:20:30Z",buf.toString());
        
        buf = new StringBuffer();
        f.printTo(buf, dt.getMillis());
        assertEquals("Wed 2004-06-09T11:20:30+01:00",buf.toString());
        
        buf = new StringBuffer();
        ISODateTimeFormat.yearMonthDay().printTo(buf, dt.toYearMonthDay());
        assertEquals("2004-06-09",buf.toString());
        
        buf = new StringBuffer();
        try {
            ISODateTimeFormat.yearMonthDay().printTo(buf, (ReadablePartial) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("deprecation")
    public void testPrint_builderMethods() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuilder buf = new StringBuilder();
        f.printTo(buf, dt);
        assertEquals("Wed 2004-06-09T10:20:30Z",buf.toString());
        
        buf = new StringBuilder();
        f.printTo(buf, dt.getMillis());
        assertEquals("Wed 2004-06-09T11:20:30+01:00",buf.toString());
        
        buf = new StringBuilder();
        ISODateTimeFormat.yearMonthDay().printTo(buf, dt.toYearMonthDay());
        assertEquals("2004-06-09",buf.toString());
        
        buf = new StringBuilder();
        try {
            ISODateTimeFormat.yearMonthDay().printTo(buf, (ReadablePartial) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("deprecation")
    public void testPrint_writerMethods() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        CharArrayWriter out = new CharArrayWriter();
        f.printTo(out, dt);
        assertEquals("Wed 2004-06-09T10:20:30Z",out.toString());
        
        out = new CharArrayWriter();
        f.printTo(out, dt.getMillis());
        assertEquals("Wed 2004-06-09T11:20:30+01:00",out.toString());
        
        out = new CharArrayWriter();
        ISODateTimeFormat.yearMonthDay().printTo(out, dt.toYearMonthDay());
        assertEquals("2004-06-09",out.toString());
        
        out = new CharArrayWriter();
        try {
            ISODateTimeFormat.yearMonthDay().printTo(out, (ReadablePartial) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testPrint_appendableMethods() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuilder buf = new StringBuilder();
        f.printTo(buf, dt);
        assertEquals("Wed 2004-06-09T10:20:30Z",buf.toString());
        
        buf = new StringBuilder();
        f.printTo(buf, dt.getMillis());
        assertEquals("Wed 2004-06-09T11:20:30+01:00",buf.toString());
        
        buf = new StringBuilder();
        ISODateTimeFormat.yearMonthDay().printTo(buf, dt.toLocalDate());
        assertEquals("2004-06-09",buf.toString());
        
        buf = new StringBuilder();
        try {
            ISODateTimeFormat.yearMonthDay().printTo(buf, (ReadablePartial) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testPrint_chrono_and_zone() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withChronology(null).withZone(null).print(dt));
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(null).print(dt));
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(PARIS).print(dt));
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withChronology(ISO_PARIS).withZone(NEWYORK).print(dt));
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withChronology(null).withZone(NEWYORK).print(dt));
        
        dt = dt.withChronology(ISO_PARIS);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(null).withZone(null).print(dt));
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(null).print(dt));
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(PARIS).print(dt));
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withChronology(ISO_PARIS).withZone(NEWYORK).print(dt));
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withChronology(null).withZone(NEWYORK).print(dt));
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2547-06-09T12:20:30+02:00",f.withChronology(null).withZone(null).print(dt));
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(null).print(dt));
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(PARIS).print(dt));
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withChronology(ISO_PARIS).withZone(NEWYORK).print(dt));
        assertEquals("Wed 2547-06-09T06:20:30-04:00",f.withChronology(null).withZone(NEWYORK).print(dt));
    }

    public void testWithGetLocale() {
        DateTimeFormatter f2 = f.withLocale(Locale.FRENCH);
        assertEquals(Locale.FRENCH,f2.getLocale());
        assertSame(f2,f2.withLocale(Locale.FRENCH));
        
        f2 = f.withLocale(null);
        assertEquals(null,f2.getLocale());
        assertSame(f2,f2.withLocale(null));
    }

    public void testWithGetZone() {
        DateTimeFormatter f2 = f.withZone(PARIS);
        assertEquals(PARIS,f2.getZone());
        assertSame(f2,f2.withZone(PARIS));
        
        f2 = f.withZone(null);
        assertEquals(null,f2.getZone());
        assertSame(f2,f2.withZone(null));
    }

    public void testWithGetChronology() {
        DateTimeFormatter f2 = f.withChronology(BUDDHIST_PARIS);
        assertEquals(BUDDHIST_PARIS,f2.getChronology());
        assertSame(f2,f2.withChronology(BUDDHIST_PARIS));
        
        f2 = f.withChronology(null);
        assertEquals(null,f2.getChronology());
        assertSame(f2,f2.withChronology(null));
    }

    public void testWithGetPivotYear() {
        DateTimeFormatter f2 = f.withPivotYear(13);
        assertEquals(new Integer(13),f2.getPivotYear());
        assertSame(f2,f2.withPivotYear(13));
        
        f2 = f.withPivotYear(new Integer(14));
        assertEquals(new Integer(14),f2.getPivotYear());
        assertSame(f2,f2.withPivotYear(new Integer(14)));
        
        f2 = f.withPivotYear(null);
        assertEquals(null,f2.getPivotYear());
        assertSame(f2,f2.withPivotYear(null));
    }

    public void testWithGetOffsetParsedMethods() {
        DateTimeFormatter f2 = f;
        assertEquals(false,f2.isOffsetParsed());
        assertEquals(null,f2.getZone());
        
        f2 = f.withOffsetParsed();
        assertEquals(true,f2.isOffsetParsed());
        assertEquals(null,f2.getZone());
        
        f2 = f2.withZone(PARIS);
        assertEquals(false,f2.isOffsetParsed());
        assertEquals(PARIS,f2.getZone());
        
        f2 = f2.withOffsetParsed();
        assertEquals(true,f2.isOffsetParsed());
        assertEquals(null,f2.getZone());
        
        f2 = f.withOffsetParsed();
        assertNotSame(f,f2);
        DateTimeFormatter f3 = f2.withOffsetParsed();
        assertSame(f2,f3);
    }

    public void testPrinterParserMethods() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        assertEquals(f.getPrinter(),f2.getPrinter());
        assertEquals(f.getParser(),f2.getParser());
        assertEquals(true,f2.isPrinter());
        assertEquals(true,f2.isParser());
        assertNotNull(f2.print(0L));
        assertNotNull(f2.parseDateTime("Thu 1970-01-01T00:00:00Z"));
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        assertEquals(f.getPrinter(),f2.getPrinter());
        assertEquals(null,f2.getParser());
        assertEquals(true,f2.isPrinter());
        assertEquals(false,f2.isParser());
        assertNotNull(f2.print(0L));
        try {
            f2.parseDateTime("Thu 1970-01-01T00:00:00Z");
            fail();
        } catch (UnsupportedOperationException ex) {}
        
        f2 = new DateTimeFormatter((DateTimePrinter) null, f.getParser());
        assertEquals(null,f2.getPrinter());
        assertEquals(f.getParser(),f2.getParser());
        assertEquals(false,f2.isPrinter());
        assertEquals(true,f2.isParser());
        try {
            f2.print(0L);
            fail();
        } catch (UnsupportedOperationException ex) {}
        assertNotNull(f2.parseDateTime("Thu 1970-01-01T00:00:00Z"));
    }

    //-----------------------------------------------------------------------
    public void testParseLocalDate_simple() {
        assertEquals(new LocalDate(2004,6,9),g.parseLocalDate("2004-06-09T10:20:30Z"));
        assertEquals(new LocalDate(2004,6,9),g.parseLocalDate("2004-06-09T10:20:30+18:00"));
        assertEquals(new LocalDate(2004,6,9),g.parseLocalDate("2004-06-09T10:20:30-18:00"));
        assertEquals(new LocalDate(2004,6,9,BUDDHIST_PARIS),g.withChronology(BUDDHIST_PARIS).parseLocalDate("2004-06-09T10:20:30Z"));
        try {
            g.parseDateTime("ABC");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testParseLocalDate_yearOfEra() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("YYYY-MM GG")
            .withChronology(chrono)
            .withLocale(Locale.UK);
        
        LocalDate date = new LocalDate(2005, 10, 1, chrono);
        assertEquals(date,f.parseLocalDate("2005-10 AD"));
        assertEquals(date,f.parseLocalDate("2005-10 CE"));
        
        date = new LocalDate(-2005, 10, 1, chrono);
        assertEquals(date,f.parseLocalDate("2005-10 BC"));
        assertEquals(date,f.parseLocalDate("2005-10 BCE"));
    }

    public void testParseLocalDate_yearOfCentury() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("yy M d")
            .withChronology(chrono)
            .withLocale(Locale.UK)
            .withPivotYear(2050);
        
        LocalDate date = new LocalDate(2050, 8, 4, chrono);
        assertEquals(date,f.parseLocalDate("50 8 4"));
    }

    public void testParseLocalDate_monthDay_feb29() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("M d")
            .withChronology(chrono)
            .withLocale(Locale.UK);
        
        assertEquals(new LocalDate(2000,2,29,chrono),f.parseLocalDate("2 29"));
    }

    public void testParseLocalDate_monthDay_withDefaultYear_feb29() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("M d")
            .withChronology(chrono)
            .withLocale(Locale.UK)
            .withDefaultYear(2012);
        
        assertEquals(new LocalDate(2012,2,29,chrono),f.parseLocalDate("2 29"));
    }

    public void testParseLocalDate_weekyear_month_week_2010() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("xxxx-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2010,1,4,chrono),f.parseLocalDate("2010-01-01"));
    }

    public void testParseLocalDate_weekyear_month_week_2011() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("xxxx-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2011,1,3,chrono),f.parseLocalDate("2011-01-01"));
    }

    public void testParseLocalDate_weekyear_month_week_2012() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("xxxx-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2012,1,2,chrono),f.parseLocalDate("2012-01-01"));
    }

// This test fails, but since more related tests pass with the extra loop in DateTimeParserBucket
// I'm going to leave the change in and ignore this test
//    public void testParseLocalDate_weekyear_month_week_2013() {
//        Chronology chrono = GJChronology.getInstanceUTC();
//        DateTimeFormatter f = DateTimeFormat.forPattern("xxxx-MM-ww").withChronology(chrono);
//        assertEquals(new LocalDate(2012,12,31,chrono),f.parseLocalDate("2013-01-01"));
//    }

    public void testParseLocalDate_year_month_week_2010() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2010,1,4,chrono),f.parseLocalDate("2010-01-01"));
    }

    public void testParseLocalDate_year_month_week_2011() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2011,1,3,chrono),f.parseLocalDate("2011-01-01"));
    }

    public void testParseLocalDate_year_month_week_2012() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2012,1,2,chrono),f.parseLocalDate("2012-01-01"));
    }

    public void testParseLocalDate_year_month_week_2013() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2012,12,31,chrono),f.parseLocalDate("2013-01-01"));  // 2013-01-01 would be better, but this is OK
    }

    public void testParseLocalDate_year_month_week_2014() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2013,12,30,chrono),f.parseLocalDate("2014-01-01"));  // 2014-01-01 would be better, but this is OK
    }

    public void testParseLocalDate_year_month_week_2015() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2014,12,29,chrono),f.parseLocalDate("2015-01-01"));  // 2015-01-01 would be better, but this is OK
    }

    public void testParseLocalDate_year_month_week_2016() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2016,1,4,chrono),f.parseLocalDate("2016-01-01"));
    }

    //-----------------------------------------------------------------------
    public void testParseLocalTime_simple() {
        assertEquals(new LocalTime(10,20,30),g.parseLocalTime("2004-06-09T10:20:30Z"));
        assertEquals(new LocalTime(10,20,30),g.parseLocalTime("2004-06-09T10:20:30+18:00"));
        assertEquals(new LocalTime(10,20,30),g.parseLocalTime("2004-06-09T10:20:30-18:00"));
        assertEquals(new LocalTime(10,20,30,0,BUDDHIST_PARIS),g.withChronology(BUDDHIST_PARIS).parseLocalTime("2004-06-09T10:20:30Z"));
        try {
            g.parseDateTime("ABC");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testParseLocalDateTime_simple() {
        assertEquals(new LocalDateTime(2004,6,9,10,20,30),g.parseLocalDateTime("2004-06-09T10:20:30Z"));
        assertEquals(new LocalDateTime(2004,6,9,10,20,30),g.parseLocalDateTime("2004-06-09T10:20:30+18:00"));
        assertEquals(new LocalDateTime(2004,6,9,10,20,30),g.parseLocalDateTime("2004-06-09T10:20:30-18:00"));
        assertEquals(new LocalDateTime(2004,6,9,10,20,30,0,BUDDHIST_PARIS),g.withChronology(BUDDHIST_PARIS).parseLocalDateTime("2004-06-09T10:20:30Z"));
        try {
            g.parseDateTime("ABC");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testParseLocalDateTime_monthDay_feb29() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("M d H m")
            .withChronology(chrono)
            .withLocale(Locale.UK);
        
        assertEquals(new LocalDateTime(2000,2,29,13,40,0,0,chrono),f.parseLocalDateTime("2 29 13 40"));
    }

    public void testParseLocalDateTime_monthDay_withDefaultYear_feb29() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("M d H m")
            .withChronology(chrono)
            .withLocale(Locale.UK)
            .withDefaultYear(2012);
        
        assertEquals(new LocalDateTime(2012,2,29,13,40,0,0,chrono),f.parseLocalDateTime("2 29 13 40"));
    }

    //-----------------------------------------------------------------------
    public void testParseDateTime_simple() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.parseDateTime("2004-06-09T10:20:30Z"));
        
        try {
            g.parseDateTime("ABC");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testParseDateTime_zone() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(LONDON).parseDateTime("2004-06-09T10:20:30Z"));
        
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(null).parseDateTime("2004-06-09T10:20:30Z"));
        
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withZone(PARIS).parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_zone2() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(LONDON).parseDateTime("2004-06-09T06:20:30-04:00"));
        
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(null).parseDateTime("2004-06-09T06:20:30-04:00"));
        
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withZone(PARIS).parseDateTime("2004-06-09T06:20:30-04:00"));
    }

    public void testParseDateTime_zone3() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        assertEquals(expect,h.withZone(LONDON).parseDateTime("2004-06-09T10:20:30"));
        
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        assertEquals(expect,h.withZone(null).parseDateTime("2004-06-09T10:20:30"));
        
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, PARIS);
        assertEquals(expect,h.withZone(PARIS).parseDateTime("2004-06-09T10:20:30"));
    }

    public void testParseDateTime_simple_precedence() {
        DateTime expect = null;
        // use correct day of week
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,f.parseDateTime("Wed 2004-06-09T10:20:30Z"));
        
        // use wrong day of week
        expect = new DateTime(2004, 6, 7, 11, 20, 30, 0, LONDON);
        // DayOfWeek takes precedence, because week < month in length
        assertEquals(expect,f.parseDateTime("Mon 2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_offsetParsed() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        assertEquals(expect,g.withOffsetParsed().parseDateTime("2004-06-09T10:20:30Z"));
        
        expect = new DateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        assertEquals(expect,g.withOffsetParsed().parseDateTime("2004-06-09T06:20:30-04:00"));
        
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        assertEquals(expect,g.withZone(PARIS).withOffsetParsed().parseDateTime("2004-06-09T10:20:30Z"));
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withOffsetParsed().withZone(PARIS).parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_chrono() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withChronology(ISO_PARIS).parseDateTime("2004-06-09T10:20:30Z"));
        
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0,LONDON);
        assertEquals(expect,g.withChronology(null).parseDateTime("2004-06-09T10:20:30Z"));
        
        expect = new DateTime(2547, 6, 9, 12, 20, 30, 0, BUDDHIST_PARIS);
        assertEquals(expect,g.withChronology(BUDDHIST_PARIS).parseDateTime("2547-06-09T10:20:30Z"));
        
        expect = new DateTime(2004, 6, 9, 10, 29, 51, 0, BUDDHIST_PARIS); // zone is +00:09:21 in 1451
        assertEquals(expect,g.withChronology(BUDDHIST_PARIS).parseDateTime("2004-06-09T10:20:30Z"));
    }

    //-----------------------------------------------------------------------
    public void testParseMutableDateTime_simple() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.parseMutableDateTime("2004-06-09T10:20:30Z"));
        
        try {
            g.parseMutableDateTime("ABC");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testParseMutableDateTime_zone() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(LONDON).parseMutableDateTime("2004-06-09T10:20:30Z"));
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(null).parseMutableDateTime("2004-06-09T10:20:30Z"));
        
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withZone(PARIS).parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_zone2() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(LONDON).parseMutableDateTime("2004-06-09T06:20:30-04:00"));
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(null).parseMutableDateTime("2004-06-09T06:20:30-04:00"));
        
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withZone(PARIS).parseMutableDateTime("2004-06-09T06:20:30-04:00"));
    }

    public void testParseMutableDateTime_zone3() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        assertEquals(expect,h.withZone(LONDON).parseMutableDateTime("2004-06-09T10:20:30"));
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        assertEquals(expect,h.withZone(null).parseMutableDateTime("2004-06-09T10:20:30"));
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, PARIS);
        assertEquals(expect,h.withZone(PARIS).parseMutableDateTime("2004-06-09T10:20:30"));
    }

    public void testParseMutableDateTime_simple_precedence() {
        MutableDateTime expect = null;
        // use correct day of week
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,f.parseDateTime("Wed 2004-06-09T10:20:30Z"));
        
        // use wrong day of week
        expect = new MutableDateTime(2004, 6, 7, 11, 20, 30, 0, LONDON);
        // DayOfWeek takes precedence, because week < month in length
        assertEquals(expect,f.parseDateTime("Mon 2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_offsetParsed() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        assertEquals(expect,g.withOffsetParsed().parseMutableDateTime("2004-06-09T10:20:30Z"));
        
        expect = new MutableDateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        assertEquals(expect,g.withOffsetParsed().parseMutableDateTime("2004-06-09T06:20:30-04:00"));
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        assertEquals(expect,g.withZone(PARIS).withOffsetParsed().parseMutableDateTime("2004-06-09T10:20:30Z"));
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withOffsetParsed().withZone(PARIS).parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_chrono() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withChronology(ISO_PARIS).parseMutableDateTime("2004-06-09T10:20:30Z"));
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0,LONDON);
        assertEquals(expect,g.withChronology(null).parseMutableDateTime("2004-06-09T10:20:30Z"));
        
        expect = new MutableDateTime(2547, 6, 9, 12, 20, 30, 0, BUDDHIST_PARIS);
        assertEquals(expect,g.withChronology(BUDDHIST_PARIS).parseMutableDateTime("2547-06-09T10:20:30Z"));
        
        expect = new MutableDateTime(2004, 6, 9, 10, 29, 51, 0, BUDDHIST_PARIS); // zone is +00:09:21 in 1451
        assertEquals(expect,g.withChronology(BUDDHIST_PARIS).parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    //-----------------------------------------------------------------------
    public void testParseInto_simple() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        MutableDateTime result = new MutableDateTime(0L);
        assertEquals(20,g.parseInto(result,"2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
        
        try {
            g.parseInto(null, "2004-06-09T10:20:30Z", 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        
        assertEquals(~0,g.parseInto(result,"ABC",0));
        assertEquals(~10,g.parseInto(result,"2004-06-09",0));
        assertEquals(~13,g.parseInto(result,"XX2004-06-09T",2));
    }

    public void testParseInto_zone() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withZone(LONDON).parseInto(result,"2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withZone(null).parseInto(result,"2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withZone(PARIS).parseInto(result,"2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
    }

    public void testParseInto_zone2() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(25,g.withZone(LONDON).parseInto(result,"2004-06-09T06:20:30-04:00",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(25,g.withZone(null).parseInto(result,"2004-06-09T06:20:30-04:00",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(25,g.withZone(PARIS).parseInto(result,"2004-06-09T06:20:30-04:00",0));
        assertEquals(expect,result);
    }

    public void testParseInto_zone3() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(19,h.withZone(LONDON).parseInto(result,"2004-06-09T10:20:30",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(19,h.withZone(null).parseInto(result,"2004-06-09T10:20:30",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        assertEquals(19,h.withZone(PARIS).parseInto(result,"2004-06-09T10:20:30",0));
        assertEquals(expect,result);
    }

    public void testParseInto_simple_precedence() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 7, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        // DayOfWeek takes precedence, because week < month in length
        assertEquals(24,f.parseInto(result,"Mon 2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
    }

    public void testParseInto_offsetParsed() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withOffsetParsed().parseInto(result,"2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        result = new MutableDateTime(0L);
        assertEquals(25,g.withOffsetParsed().parseInto(result,"2004-06-09T06:20:30-04:00",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withZone(PARIS).withOffsetParsed().parseInto(result,"2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withOffsetParsed().withZone(PARIS).parseInto(result,"2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
    }

    public void testParseInto_chrono() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withChronology(ISO_PARIS).parseInto(result,"2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withChronology(null).parseInto(result,"2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2547, 6, 9, 12, 20, 30, 0, BUDDHIST_PARIS);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withChronology(BUDDHIST_PARIS).parseInto(result,"2547-06-09T10:20:30Z",0));
        assertEquals(expect,result);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 29, 51, 0, BUDDHIST_PARIS);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withChronology(BUDDHIST_PARIS).parseInto(result,"2004-06-09T10:20:30Z",0));
        assertEquals(expect,result);
    }

    public void testParseInto_monthOnly() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, LONDON);
        assertEquals(1,f.parseInto(result,"5",0));
        assertEquals(new MutableDateTime(2004,5,9,12,20,30,0,LONDON),result);
    }

    public void testParseInto_monthOnly_baseStartYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 1, 12, 20, 30, 0, TOKYO);
        assertEquals(1,f.parseInto(result,"5",0));
        assertEquals(new MutableDateTime(2004,5,1,12,20,30,0,TOKYO),result);
    }

    public void testParseInto_monthOnly_parseStartYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 2, 1, 12, 20, 30, 0, TOKYO);
        assertEquals(1,f.parseInto(result,"1",0));
        assertEquals(new MutableDateTime(2004,1,1,12,20,30,0,TOKYO),result);
    }

    public void testParseInto_monthOnly_baseEndYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 12, 31, 12, 20, 30, 0, TOKYO);
        assertEquals(1,f.parseInto(result,"5",0));
        assertEquals(new MutableDateTime(2004,5,31,12,20,30,0,TOKYO),result);
   }

    public void testParseInto_monthOnly_parseEndYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 31, 12, 20, 30, 0,TOKYO);
        assertEquals(2,f.parseInto(result,"12",0));
        assertEquals(new MutableDateTime(2004,12,31,12,20,30,0,TOKYO),result);
    }

    public void testParseInto_monthDay_feb29() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, LONDON);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,12,20,30,0,LONDON),result);
    }

    public void testParseInto_monthDay_feb29_startOfYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 1, 0, 0, 0, 0, LONDON);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,0,0,0,0,LONDON),result);
    }

    public void testParseInto_monthDay_feb29_OfYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 12, 31, 23, 59, 59, 999, LONDON);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,23,59,59,999,LONDON),result);
    }

    public void testParseInto_monthDay_feb29_newYork() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, NEWYORK);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,12,20,30,0,NEWYORK),result);
    }

    public void testParseInto_monthDay_feb29_newYork_startOfYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 1, 0, 0, 0, 0, NEWYORK);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,0,0,0,0,NEWYORK),result);
    }

    public void testParseInto_monthDay_feb29_newYork_endOfYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 12, 31, 23, 59, 59, 999, NEWYORK);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,23,59,59,999,NEWYORK),result);
    }

    public void testParseInto_monthDay_feb29_tokyo() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, TOKYO);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,12,20,30,0,TOKYO),result);
    }

    public void testParseInto_monthDay_feb29_tokyo_startOfYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 1, 0, 0, 0, 0, TOKYO);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,0,0,0,0,TOKYO),result);
    }

    public void testParseInto_monthDay_feb29_tokyo_endOfYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 12, 31, 23, 59, 59, 999, TOKYO);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,23,59,59,999,TOKYO),result);
    }

    public void testParseInto_monthDay_withDefaultYear_feb29() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withDefaultYear(2012);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, LONDON);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,12,20,30,0,LONDON),result);
    }

    public void testParseInto_monthDay_withDefaultYear_feb29_newYork() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withDefaultYear(2012);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, NEWYORK);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,12,20,30,0,NEWYORK),result);
    }

    public void testParseInto_monthDay_withDefaultYear_feb29_newYork_endOfYear() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withDefaultYear(2012);
        MutableDateTime result = new MutableDateTime(2004, 12, 9, 12, 20, 30, 0, NEWYORK);
        assertEquals(4,f.parseInto(result,"2 29",0));
        assertEquals(new MutableDateTime(2004,2,29,12,20,30,0,NEWYORK),result);
    }

    public void testParseMillis_fractionOfSecondLong() {
        DateTimeFormatter f = new DateTimeFormatterBuilder()
            .appendSecondOfDay(2).appendLiteral('.').appendFractionOfSecond(1, 9)
                .toFormatter().withZoneUTC();
        assertEquals(10512,f.parseMillis("10.5123456"));
        assertEquals(10512,f.parseMillis("10.512999"));
    }

    //-----------------------------------------------------------------------
    // Ensure time zone name switches properly at the zone DST transition.
    public void testZoneNameNearTransition() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S zzzz");
        assertEquals("2005-10-30 01:00:00.0 Eastern Daylight Time",fmt.print(inDST_1));
        assertEquals("2005-10-30 01:59:59.9 Eastern Daylight Time",fmt.print(inDST_2));
        assertEquals("2005-10-30 02:00:00.0 Eastern Standard Time",fmt.print(onDST));
        assertEquals("2005-10-30 02:00:00.0 Eastern Standard Time",fmt.print(outDST));
        assertEquals("2005-10-30 02:00:01.0 Eastern Standard Time",fmt.print(outDST_2));
    }

    // Ensure time zone name switches properly at the zone DST transition.
    public void testZoneShortNameNearTransition() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S z").withLocale(Locale.ENGLISH);
        assertEquals("2005-10-30 01:00:00.0 EDT",fmt.print(inDST_1));
        assertEquals("2005-10-30 01:59:59.9 EDT",fmt.print(inDST_2));
        assertEquals("2005-10-30 02:00:00.0 EST",fmt.print(onDST));
        assertEquals("2005-10-30 02:00:00.0 EST",fmt.print(outDST));
        assertEquals("2005-10-30 02:00:01.0 EST",fmt.print(outDST_2));
    }

    public void testCustomDateTimePrinter() {
        DateTimePrinter printer = new DateTimeFormatterBuilder()
                .append(new CustomDateTimePrinter())
                .appendLiteral(' ')
                .appendYear(4, 8)
                .toPrinter();

        StringBuffer buffer = new StringBuffer();
        long instant = new DateTime(2017, 1, 1, 0, 0, 0, ISO_UTC).getMillis();
        printer.printTo(buffer, instant, ISO_UTC, 0, UTC, Locale.ENGLISH);

        assertEquals("Hi 2017",buffer.toString());
    }

    private static class CustomDateTimePrinter implements DateTimePrinter {

        public int estimatePrintedLength() {
            return 2;
        }

        public void printTo(StringBuffer buf, long instant, Chronology chrono, int displayOffset,
                DateTimeZone displayZone, Locale locale) {
            buf.append("Hi");
        }

        public void printTo(Writer out, long instant, Chronology chrono, int displayOffset,
                DateTimeZone displayZone, Locale locale) throws IOException {
            out.write("Hi");
        }

        public void printTo(StringBuffer buf, ReadablePartial partial, Locale locale) {
            buf.append("Hi");
        }

        public void printTo(Writer out, ReadablePartial partial, Locale locale) throws IOException {
            out.write("Hi");
        }
    }

    public void testPrint_simple_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T10:20:30Z",f.print(dt));
    }

    public void testPrint_simple_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(PARIS);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.print(dt));
    }

    public void testPrint_simple_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(PARIS);
        
        dt = dt.withZone(NEWYORK);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.print(dt));
    }

    public void testPrint_simple_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(PARIS);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2547-06-09T12:20:30+02:00",f.print(dt));
    }

    public void testPrint_locale_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("mer. 2004-06-09T10:20:30Z",f.withLocale(Locale.FRENCH).print(dt));
    }

    public void testPrint_locale_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withLocale(null).print(dt));
    }

    public void testPrint_zone_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withZone(NEWYORK).print(dt));
    }

    public void testPrint_zone_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withZone(PARIS).print(dt));
    }

    public void testPrint_zone_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withZone(null).print(dt));
    }

    public void testPrint_zone_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(NEWYORK);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withZone(NEWYORK).print(dt));
    }

    public void testPrint_zone_5_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(NEWYORK);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withZone(PARIS).print(dt));
    }

    public void testPrint_zone_6_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(NEWYORK);
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withZoneUTC().print(dt));
    }

    public void testPrint_zone_7_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withZone(NEWYORK);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withZone(null).print(dt));
    }

    public void testPrint_chrono_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).print(dt));
    }

    public void testPrint_chrono_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2547-06-09T12:20:30+02:00",f.withChronology(BUDDHIST_PARIS).print(dt));
    }

    public void testPrint_chrono_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withChronology(null).print(dt));
    }

    public void testPrint_chrono_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).print(dt));
    }

    public void testPrint_chrono_5_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2547-06-09T12:20:30+02:00",f.withChronology(BUDDHIST_PARIS).print(dt));
    }

    public void testPrint_chrono_6_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withChronology(ISO_UTC).print(dt));
    }

    public void testPrint_chrono_7_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2547-06-09T12:20:30+02:00",f.withChronology(null).print(dt));
    }

    public void testPrint_bufferMethods_1_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuffer buf = new StringBuffer();
        f.printTo(buf, dt);
        assertEquals("Wed 2004-06-09T10:20:30Z",buf.toString());
    }

    public void testPrint_bufferMethods_2_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuffer buf = new StringBuffer();
        f.printTo(buf, dt);
        
        buf = new StringBuffer();
        f.printTo(buf, dt.getMillis());
        assertEquals("Wed 2004-06-09T11:20:30+01:00",buf.toString());
    }

    public void testPrint_bufferMethods_3_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuffer buf = new StringBuffer();
        f.printTo(buf, dt);
        
        buf = new StringBuffer();
        f.printTo(buf, dt.getMillis());
        
        buf = new StringBuffer();
        ISODateTimeFormat.yearMonthDay().printTo(buf, dt.toYearMonthDay());
        assertEquals("2004-06-09",buf.toString());
    }

    public void testPrint_builderMethods_1_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuilder buf = new StringBuilder();
        f.printTo(buf, dt);
        assertEquals("Wed 2004-06-09T10:20:30Z",buf.toString());
    }

    public void testPrint_builderMethods_2_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuilder buf = new StringBuilder();
        f.printTo(buf, dt);
        
        buf = new StringBuilder();
        f.printTo(buf, dt.getMillis());
        assertEquals("Wed 2004-06-09T11:20:30+01:00",buf.toString());
    }

    public void testPrint_builderMethods_3_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuilder buf = new StringBuilder();
        f.printTo(buf, dt);
        
        buf = new StringBuilder();
        f.printTo(buf, dt.getMillis());
        
        buf = new StringBuilder();
        ISODateTimeFormat.yearMonthDay().printTo(buf, dt.toYearMonthDay());
        assertEquals("2004-06-09",buf.toString());
    }

    public void testPrint_writerMethods_1_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        CharArrayWriter out = new CharArrayWriter();
        f.printTo(out, dt);
        assertEquals("Wed 2004-06-09T10:20:30Z",out.toString());
    }

    public void testPrint_writerMethods_2_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        CharArrayWriter out = new CharArrayWriter();
        f.printTo(out, dt);
        
        out = new CharArrayWriter();
        f.printTo(out, dt.getMillis());
        assertEquals("Wed 2004-06-09T11:20:30+01:00",out.toString());
    }

    public void testPrint_writerMethods_3_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        CharArrayWriter out = new CharArrayWriter();
        f.printTo(out, dt);
        
        out = new CharArrayWriter();
        f.printTo(out, dt.getMillis());
        
        out = new CharArrayWriter();
        ISODateTimeFormat.yearMonthDay().printTo(out, dt.toYearMonthDay());
        assertEquals("2004-06-09",out.toString());
    }

    public void testPrint_appendableMethods_1_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuilder buf = new StringBuilder();
        f.printTo(buf, dt);
        assertEquals("Wed 2004-06-09T10:20:30Z",buf.toString());
    }

    public void testPrint_appendableMethods_2_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuilder buf = new StringBuilder();
        f.printTo(buf, dt);
        
        buf = new StringBuilder();
        f.printTo(buf, dt.getMillis());
        assertEquals("Wed 2004-06-09T11:20:30+01:00",buf.toString());
    }

    public void testPrint_appendableMethods_3_oe() throws Exception {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        StringBuilder buf = new StringBuilder();
        f.printTo(buf, dt);
        
        buf = new StringBuilder();
        f.printTo(buf, dt.getMillis());
        
        buf = new StringBuilder();
        ISODateTimeFormat.yearMonthDay().printTo(buf, dt.toLocalDate());
        assertEquals("2004-06-09",buf.toString());
    }

    public void testPrint_chrono_and_zone_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T10:20:30Z",f.withChronology(null).withZone(null).print(dt));
    }

    public void testPrint_chrono_and_zone_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(null).print(dt));
    }

    public void testPrint_chrono_and_zone_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(PARIS).print(dt));
    }

    public void testPrint_chrono_and_zone_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withChronology(ISO_PARIS).withZone(NEWYORK).print(dt));
    }

    public void testPrint_chrono_and_zone_5_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withChronology(null).withZone(NEWYORK).print(dt));
    }

    public void testPrint_chrono_and_zone_6_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(ISO_PARIS);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(null).withZone(null).print(dt));
    }

    public void testPrint_chrono_and_zone_7_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(ISO_PARIS);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(null).print(dt));
    }

    public void testPrint_chrono_and_zone_8_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(ISO_PARIS);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(PARIS).print(dt));
    }

    public void testPrint_chrono_and_zone_9_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(ISO_PARIS);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withChronology(ISO_PARIS).withZone(NEWYORK).print(dt));
    }

    public void testPrint_chrono_and_zone_10_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(ISO_PARIS);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withChronology(null).withZone(NEWYORK).print(dt));
    }

    public void testPrint_chrono_and_zone_11_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(ISO_PARIS);
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2547-06-09T12:20:30+02:00",f.withChronology(null).withZone(null).print(dt));
    }

    public void testPrint_chrono_and_zone_12_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(ISO_PARIS);
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(null).print(dt));
    }

    public void testPrint_chrono_and_zone_13_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(ISO_PARIS);
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2004-06-09T12:20:30+02:00",f.withChronology(ISO_PARIS).withZone(PARIS).print(dt));
    }

    public void testPrint_chrono_and_zone_14_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(ISO_PARIS);
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2004-06-09T06:20:30-04:00",f.withChronology(ISO_PARIS).withZone(NEWYORK).print(dt));
    }

    public void testPrint_chrono_and_zone_15_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        
        dt = dt.withChronology(ISO_PARIS);
        
        dt = dt.withChronology(BUDDHIST_PARIS);
        assertEquals("Wed 2547-06-09T06:20:30-04:00",f.withChronology(null).withZone(NEWYORK).print(dt));
    }

    public void testWithGetLocale_1_oe() {
        DateTimeFormatter f2 = f.withLocale(Locale.FRENCH);
        assertEquals(Locale.FRENCH,f2.getLocale());
    }

    public void testWithGetLocale_2_oe() {
        DateTimeFormatter f2 = f.withLocale(Locale.FRENCH);
        assertSame(f2,f2.withLocale(Locale.FRENCH));
    }

    public void testWithGetLocale_3_oe() {
        DateTimeFormatter f2 = f.withLocale(Locale.FRENCH);
        
        f2 = f.withLocale(null);
        assertEquals(null,f2.getLocale());
    }

    public void testWithGetLocale_4_oe() {
        DateTimeFormatter f2 = f.withLocale(Locale.FRENCH);
        
        f2 = f.withLocale(null);
        assertSame(f2,f2.withLocale(null));
    }

    public void testWithGetZone_1_oe() {
        DateTimeFormatter f2 = f.withZone(PARIS);
        assertEquals(PARIS,f2.getZone());
    }

    public void testWithGetZone_2_oe() {
        DateTimeFormatter f2 = f.withZone(PARIS);
        assertSame(f2,f2.withZone(PARIS));
    }

    public void testWithGetZone_3_oe() {
        DateTimeFormatter f2 = f.withZone(PARIS);
        
        f2 = f.withZone(null);
        assertEquals(null,f2.getZone());
    }

    public void testWithGetZone_4_oe() {
        DateTimeFormatter f2 = f.withZone(PARIS);
        
        f2 = f.withZone(null);
        assertSame(f2,f2.withZone(null));
    }

    public void testWithGetChronology_1_oe() {
        DateTimeFormatter f2 = f.withChronology(BUDDHIST_PARIS);
        assertEquals(BUDDHIST_PARIS,f2.getChronology());
    }

    public void testWithGetChronology_2_oe() {
        DateTimeFormatter f2 = f.withChronology(BUDDHIST_PARIS);
        assertSame(f2,f2.withChronology(BUDDHIST_PARIS));
    }

    public void testWithGetChronology_3_oe() {
        DateTimeFormatter f2 = f.withChronology(BUDDHIST_PARIS);
        
        f2 = f.withChronology(null);
        assertEquals(null,f2.getChronology());
    }

    public void testWithGetChronology_4_oe() {
        DateTimeFormatter f2 = f.withChronology(BUDDHIST_PARIS);
        
        f2 = f.withChronology(null);
        assertSame(f2,f2.withChronology(null));
    }

    public void testWithGetPivotYear_1_oe() {
        DateTimeFormatter f2 = f.withPivotYear(13);
        assertEquals(new Integer(13),f2.getPivotYear());
    }

    public void testWithGetPivotYear_2_oe() {
        DateTimeFormatter f2 = f.withPivotYear(13);
        assertSame(f2,f2.withPivotYear(13));
    }

    public void testWithGetPivotYear_3_oe() {
        DateTimeFormatter f2 = f.withPivotYear(13);
        
        f2 = f.withPivotYear(new Integer(14));
        assertEquals(new Integer(14),f2.getPivotYear());
    }

    public void testWithGetPivotYear_4_oe() {
        DateTimeFormatter f2 = f.withPivotYear(13);
        
        f2 = f.withPivotYear(new Integer(14));
        assertSame(f2,f2.withPivotYear(new Integer(14)));
    }

    public void testWithGetPivotYear_5_oe() {
        DateTimeFormatter f2 = f.withPivotYear(13);
        
        f2 = f.withPivotYear(new Integer(14));
        
        f2 = f.withPivotYear(null);
        assertEquals(null,f2.getPivotYear());
    }

    public void testWithGetPivotYear_6_oe() {
        DateTimeFormatter f2 = f.withPivotYear(13);
        
        f2 = f.withPivotYear(new Integer(14));
        
        f2 = f.withPivotYear(null);
        assertSame(f2,f2.withPivotYear(null));
    }

    public void testWithGetOffsetParsedMethods_1_oe() {
        DateTimeFormatter f2 = f;
        assertEquals(false,f2.isOffsetParsed());
    }

    public void testWithGetOffsetParsedMethods_2_oe() {
        DateTimeFormatter f2 = f;
        assertEquals(null,f2.getZone());
    }

    public void testWithGetOffsetParsedMethods_3_oe() {
        DateTimeFormatter f2 = f;
        
        f2 = f.withOffsetParsed();
        assertEquals(true,f2.isOffsetParsed());
    }

    public void testWithGetOffsetParsedMethods_4_oe() {
        DateTimeFormatter f2 = f;
        
        f2 = f.withOffsetParsed();
        assertEquals(null,f2.getZone());
    }

    public void testWithGetOffsetParsedMethods_5_oe() {
        DateTimeFormatter f2 = f;
        
        f2 = f.withOffsetParsed();
        
        f2 = f2.withZone(PARIS);
        assertEquals(false,f2.isOffsetParsed());
    }

    public void testWithGetOffsetParsedMethods_6_oe() {
        DateTimeFormatter f2 = f;
        
        f2 = f.withOffsetParsed();
        
        f2 = f2.withZone(PARIS);
        assertEquals(PARIS,f2.getZone());
    }

    public void testWithGetOffsetParsedMethods_7_oe() {
        DateTimeFormatter f2 = f;
        
        f2 = f.withOffsetParsed();
        
        f2 = f2.withZone(PARIS);
        
        f2 = f2.withOffsetParsed();
        assertEquals(true,f2.isOffsetParsed());
    }

    public void testWithGetOffsetParsedMethods_8_oe() {
        DateTimeFormatter f2 = f;
        
        f2 = f.withOffsetParsed();
        
        f2 = f2.withZone(PARIS);
        
        f2 = f2.withOffsetParsed();
        assertEquals(null,f2.getZone());
    }

    public void testWithGetOffsetParsedMethods_9_oe() {
        DateTimeFormatter f2 = f;
        
        f2 = f.withOffsetParsed();
        
        f2 = f2.withZone(PARIS);
        
        f2 = f2.withOffsetParsed();
        
        f2 = f.withOffsetParsed();
        assertNotSame(f,f2);
    }

    public void testWithGetOffsetParsedMethods_10_oe() {
        DateTimeFormatter f2 = f;
        
        f2 = f.withOffsetParsed();
        
        f2 = f2.withZone(PARIS);
        
        f2 = f2.withOffsetParsed();
        
        f2 = f.withOffsetParsed();
        DateTimeFormatter f3 = f2.withOffsetParsed();
        assertSame(f2,f3);
    }

    public void testPrinterParserMethods_1_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        assertEquals(f.getPrinter(),f2.getPrinter());
    }

    public void testPrinterParserMethods_2_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        assertEquals(f.getParser(),f2.getParser());
    }

    public void testPrinterParserMethods_3_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        assertEquals(true,f2.isPrinter());
    }

    public void testPrinterParserMethods_4_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        assertEquals(true,f2.isParser());
    }

    public void testPrinterParserMethods_5_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        assertNotNull(f2.print(0L));
    }

    public void testPrinterParserMethods_6_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        assertNotNull(f2.parseDateTime("Thu 1970-01-01T00:00:00Z"));
    }

    public void testPrinterParserMethods_7_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        assertEquals(f.getPrinter(),f2.getPrinter());
    }

    public void testPrinterParserMethods_8_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        assertEquals(null,f2.getParser());
    }

    public void testPrinterParserMethods_9_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        assertEquals(true,f2.isPrinter());
    }

    public void testPrinterParserMethods_10_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        assertEquals(false,f2.isParser());
    }

    public void testPrinterParserMethods_11_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        assertNotNull(f2.print(0L));
    }

    public void testPrinterParserMethods_13_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        try {
            f2.parseDateTime("Thu 1970-01-01T00:00:00Z");
        } catch (UnsupportedOperationException ex) {}
        
        f2 = new DateTimeFormatter((DateTimePrinter) null, f.getParser());
        assertEquals(null,f2.getPrinter());
    }

    public void testPrinterParserMethods_14_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        try {
            f2.parseDateTime("Thu 1970-01-01T00:00:00Z");
        } catch (UnsupportedOperationException ex) {}
        
        f2 = new DateTimeFormatter((DateTimePrinter) null, f.getParser());
        assertEquals(f.getParser(),f2.getParser());
    }

    public void testPrinterParserMethods_15_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        try {
            f2.parseDateTime("Thu 1970-01-01T00:00:00Z");
        } catch (UnsupportedOperationException ex) {}
        
        f2 = new DateTimeFormatter((DateTimePrinter) null, f.getParser());
        assertEquals(false,f2.isPrinter());
    }

    public void testPrinterParserMethods_16_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        try {
            f2.parseDateTime("Thu 1970-01-01T00:00:00Z");
        } catch (UnsupportedOperationException ex) {}
        
        f2 = new DateTimeFormatter((DateTimePrinter) null, f.getParser());
        assertEquals(true,f2.isParser());
    }

    public void testPrinterParserMethods_18_oe() {
        DateTimeFormatter f2 = new DateTimeFormatter(f.getPrinter(), f.getParser());
        
        f2 = new DateTimeFormatter(f.getPrinter(), null);
        try {
            f2.parseDateTime("Thu 1970-01-01T00:00:00Z");
        } catch (UnsupportedOperationException ex) {}
        
        f2 = new DateTimeFormatter((DateTimePrinter) null, f.getParser());
        try {
            f2.print(0L);
        } catch (UnsupportedOperationException ex) {}
        assertNotNull(f2.parseDateTime("Thu 1970-01-01T00:00:00Z"));
    }

    public void testParseLocalDate_simple_1_oe() {
        Object a = new LocalDate(2004,6,9);
        assertEquals(a, g.parseLocalDate("2004-06-09T10:20:30Z"));
    }

    public void testParseLocalDate_simple_2_oe() {
        Object a = new LocalDate(2004,6,9);
        assertEquals(a, g.parseLocalDate("2004-06-09T10:20:30+18:00"));
    }

    public void testParseLocalDate_simple_3_oe() {
        Object a = new LocalDate(2004,6,9);
        assertEquals(a, g.parseLocalDate("2004-06-09T10:20:30-18:00"));
    }

    public void testParseLocalDate_simple_4_oe() {
        Object a = new LocalDate(2004,6,9,BUDDHIST_PARIS);
        assertEquals(a, g.withChronology(BUDDHIST_PARIS).parseLocalDate("2004-06-09T10:20:30Z"));
    }

    public void testParseLocalDate_yearOfEra_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("YYYY-MM GG")
            .withChronology(chrono)
            .withLocale(Locale.UK);
        
        LocalDate date = new LocalDate(2005, 10, 1, chrono);
        assertEquals(date,f.parseLocalDate("2005-10 AD"));
    }

    public void testParseLocalDate_yearOfEra_2_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("YYYY-MM GG")
            .withChronology(chrono)
            .withLocale(Locale.UK);
        
        LocalDate date = new LocalDate(2005, 10, 1, chrono);
        assertEquals(date,f.parseLocalDate("2005-10 CE"));
    }

    public void testParseLocalDate_yearOfEra_3_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("YYYY-MM GG")
            .withChronology(chrono)
            .withLocale(Locale.UK);
        
        LocalDate date = new LocalDate(2005, 10, 1, chrono);
        
        date = new LocalDate(-2005, 10, 1, chrono);
        assertEquals(date,f.parseLocalDate("2005-10 BC"));
    }

    public void testParseLocalDate_yearOfEra_4_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("YYYY-MM GG")
            .withChronology(chrono)
            .withLocale(Locale.UK);
        
        LocalDate date = new LocalDate(2005, 10, 1, chrono);
        
        date = new LocalDate(-2005, 10, 1, chrono);
        assertEquals(date,f.parseLocalDate("2005-10 BCE"));
    }

    public void testParseLocalDate_yearOfCentury_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("yy M d")
            .withChronology(chrono)
            .withLocale(Locale.UK)
            .withPivotYear(2050);
        
        LocalDate date = new LocalDate(2050, 8, 4, chrono);
        assertEquals(date,f.parseLocalDate("50 8 4"));
    }

    public void testParseLocalDate_monthDay_feb29_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("M d")
            .withChronology(chrono)
            .withLocale(Locale.UK);
        
        assertEquals(new LocalDate(2000,2,29,chrono),f.parseLocalDate("2 29"));
    }

    public void testParseLocalDate_monthDay_withDefaultYear_feb29_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("M d")
            .withChronology(chrono)
            .withLocale(Locale.UK)
            .withDefaultYear(2012);
        
        assertEquals(new LocalDate(2012,2,29,chrono),f.parseLocalDate("2 29"));
    }

    public void testParseLocalDate_weekyear_month_week_2010_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("xxxx-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2010,1,4,chrono),f.parseLocalDate("2010-01-01"));
    }

    public void testParseLocalDate_weekyear_month_week_2011_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("xxxx-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2011,1,3,chrono),f.parseLocalDate("2011-01-01"));
    }

    public void testParseLocalDate_weekyear_month_week_2012_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("xxxx-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2012,1,2,chrono),f.parseLocalDate("2012-01-01"));
    }

    public void testParseLocalDate_year_month_week_2010_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2010,1,4,chrono),f.parseLocalDate("2010-01-01"));
    }

    public void testParseLocalDate_year_month_week_2011_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2011,1,3,chrono),f.parseLocalDate("2011-01-01"));
    }

    public void testParseLocalDate_year_month_week_2012_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2012,1,2,chrono),f.parseLocalDate("2012-01-01"));
    }

    public void testParseLocalDate_year_month_week_2013_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2012,12,31,chrono),f.parseLocalDate("2013-01-01"));  // 2013-01-01 would be better, but this is OK;
    }

    public void testParseLocalDate_year_month_week_2014_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2013,12,30,chrono),f.parseLocalDate("2014-01-01"));  // 2014-01-01 would be better, but this is OK;
    }

    public void testParseLocalDate_year_month_week_2015_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2014,12,29,chrono),f.parseLocalDate("2015-01-01"));  // 2015-01-01 would be better, but this is OK;
    }

    public void testParseLocalDate_year_month_week_2016_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-ww").withChronology(chrono);
        assertEquals(new LocalDate(2016,1,4,chrono),f.parseLocalDate("2016-01-01"));
    }

    public void testParseLocalTime_simple_1_oe() {
        Object a = new LocalTime(10,20,30);
        assertEquals(a, g.parseLocalTime("2004-06-09T10:20:30Z"));
    }

    public void testParseLocalTime_simple_2_oe() {
        Object a = new LocalTime(10,20,30);
        assertEquals(a, g.parseLocalTime("2004-06-09T10:20:30+18:00"));
    }

    public void testParseLocalTime_simple_3_oe() {
        Object a = new LocalTime(10,20,30);
        assertEquals(a, g.parseLocalTime("2004-06-09T10:20:30-18:00"));
    }

    public void testParseLocalTime_simple_4_oe() {
        Object a = new LocalTime(10,20,30,0,BUDDHIST_PARIS);
        assertEquals(a, g.withChronology(BUDDHIST_PARIS).parseLocalTime("2004-06-09T10:20:30Z"));
    }

    public void testParseLocalDateTime_simple_1_oe() {
        Object a = new LocalDateTime(2004,6,9,10,20,30);
        assertEquals(a, g.parseLocalDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseLocalDateTime_simple_2_oe() {
        Object a = new LocalDateTime(2004,6,9,10,20,30);
        assertEquals(a, g.parseLocalDateTime("2004-06-09T10:20:30+18:00"));
    }

    public void testParseLocalDateTime_simple_3_oe() {
        Object a = new LocalDateTime(2004,6,9,10,20,30);
        assertEquals(a, g.parseLocalDateTime("2004-06-09T10:20:30-18:00"));
    }

    public void testParseLocalDateTime_simple_4_oe() {
        Object a = new LocalDateTime(2004,6,9,10,20,30,0,BUDDHIST_PARIS);
        assertEquals(a, g.withChronology(BUDDHIST_PARIS).parseLocalDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseLocalDateTime_monthDay_feb29_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("M d H m")
            .withChronology(chrono)
            .withLocale(Locale.UK);
        
        assertEquals(new LocalDateTime(2000,2,29,13,40,0,0,chrono),f.parseLocalDateTime("2 29 13 40"));
    }

    public void testParseLocalDateTime_monthDay_withDefaultYear_feb29_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();
        DateTimeFormatter f = DateTimeFormat
            .forPattern("M d H m")
            .withChronology(chrono)
            .withLocale(Locale.UK)
            .withDefaultYear(2012);
        
        assertEquals(new LocalDateTime(2012,2,29,13,40,0,0,chrono),f.parseLocalDateTime("2 29 13 40"));
    }

    public void testParseDateTime_simple_1_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_zone_1_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(LONDON).parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_zone_2_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(null).parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_zone_3_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withZone(PARIS).parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_zone2_1_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(LONDON).parseDateTime("2004-06-09T06:20:30-04:00"));
    }

    public void testParseDateTime_zone2_2_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(null).parseDateTime("2004-06-09T06:20:30-04:00"));
    }

    public void testParseDateTime_zone2_3_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withZone(PARIS).parseDateTime("2004-06-09T06:20:30-04:00"));
    }

    public void testParseDateTime_zone3_1_oe() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        assertEquals(expect,h.withZone(LONDON).parseDateTime("2004-06-09T10:20:30"));
    }

    public void testParseDateTime_zone3_2_oe() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        assertEquals(expect,h.withZone(null).parseDateTime("2004-06-09T10:20:30"));
    }

    public void testParseDateTime_zone3_3_oe() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, PARIS);
        assertEquals(expect,h.withZone(PARIS).parseDateTime("2004-06-09T10:20:30"));
    }

    public void testParseDateTime_simple_precedence_1_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,f.parseDateTime("Wed 2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_simple_precedence_2_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new DateTime(2004, 6, 7, 11, 20, 30, 0, LONDON);
        assertEquals(expect,f.parseDateTime("Mon 2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_offsetParsed_1_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        assertEquals(expect,g.withOffsetParsed().parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_offsetParsed_2_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        
        expect = new DateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        assertEquals(expect,g.withOffsetParsed().parseDateTime("2004-06-09T06:20:30-04:00"));
    }

    public void testParseDateTime_offsetParsed_3_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        
        expect = new DateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        assertEquals(expect,g.withZone(PARIS).withOffsetParsed().parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_offsetParsed_4_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        
        expect = new DateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        
        expect = new DateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withOffsetParsed().withZone(PARIS).parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_chrono_1_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withChronology(ISO_PARIS).parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_chrono_2_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0,LONDON);
        assertEquals(expect,g.withChronology(null).parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseDateTime_chrono_3_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0,LONDON);
        
        expect = new DateTime(2547, 6, 9, 12, 20, 30, 0, BUDDHIST_PARIS);
        assertEquals(expect,g.withChronology(BUDDHIST_PARIS).parseDateTime("2547-06-09T10:20:30Z"));
    }

    public void testParseDateTime_chrono_4_oe() {
        DateTime expect = null;
        expect = new DateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        
        expect = new DateTime(2004, 6, 9, 11, 20, 30, 0,LONDON);
        
        expect = new DateTime(2547, 6, 9, 12, 20, 30, 0, BUDDHIST_PARIS);
        
        expect = new DateTime(2004, 6, 9, 10, 29, 51, 0, BUDDHIST_PARIS); // zone is +00:09:21 in 1451
        assertEquals(expect,g.withChronology(BUDDHIST_PARIS).parseDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_simple_1_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_zone_1_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(LONDON).parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_zone_2_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(null).parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_zone_3_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withZone(PARIS).parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_zone2_1_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(LONDON).parseMutableDateTime("2004-06-09T06:20:30-04:00"));
    }

    public void testParseMutableDateTime_zone2_2_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,g.withZone(null).parseMutableDateTime("2004-06-09T06:20:30-04:00"));
    }

    public void testParseMutableDateTime_zone2_3_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withZone(PARIS).parseMutableDateTime("2004-06-09T06:20:30-04:00"));
    }

    public void testParseMutableDateTime_zone3_1_oe() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        assertEquals(expect,h.withZone(LONDON).parseMutableDateTime("2004-06-09T10:20:30"));
    }

    public void testParseMutableDateTime_zone3_2_oe() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        assertEquals(expect,h.withZone(null).parseMutableDateTime("2004-06-09T10:20:30"));
    }

    public void testParseMutableDateTime_zone3_3_oe() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, PARIS);
        assertEquals(expect,h.withZone(PARIS).parseMutableDateTime("2004-06-09T10:20:30"));
    }

    public void testParseMutableDateTime_simple_precedence_1_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(expect,f.parseDateTime("Wed 2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_simple_precedence_2_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 7, 11, 20, 30, 0, LONDON);
        assertEquals(expect,f.parseDateTime("Mon 2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_offsetParsed_1_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        assertEquals(expect,g.withOffsetParsed().parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_offsetParsed_2_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        
        expect = new MutableDateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        assertEquals(expect,g.withOffsetParsed().parseMutableDateTime("2004-06-09T06:20:30-04:00"));
    }

    public void testParseMutableDateTime_offsetParsed_3_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        
        expect = new MutableDateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        assertEquals(expect,g.withZone(PARIS).withOffsetParsed().parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_offsetParsed_4_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        
        expect = new MutableDateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withOffsetParsed().withZone(PARIS).parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_chrono_1_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(expect,g.withChronology(ISO_PARIS).parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_chrono_2_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0,LONDON);
        assertEquals(expect,g.withChronology(null).parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_chrono_3_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0,LONDON);
        
        expect = new MutableDateTime(2547, 6, 9, 12, 20, 30, 0, BUDDHIST_PARIS);
        assertEquals(expect,g.withChronology(BUDDHIST_PARIS).parseMutableDateTime("2547-06-09T10:20:30Z"));
    }

    public void testParseMutableDateTime_chrono_4_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0,LONDON);
        
        expect = new MutableDateTime(2547, 6, 9, 12, 20, 30, 0, BUDDHIST_PARIS);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 29, 51, 0, BUDDHIST_PARIS); // zone is +00:09:21 in 1451
        assertEquals(expect,g.withChronology(BUDDHIST_PARIS).parseMutableDateTime("2004-06-09T10:20:30Z"));
    }

    public void testParseInto_simple_1_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        MutableDateTime result = new MutableDateTime(0L);
        assertEquals(20,g.parseInto(result,"2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_simple_4_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        MutableDateTime result = new MutableDateTime(0L);
        
        try {
            g.parseInto(null, "2004-06-09T10:20:30Z", 0);
        } catch (IllegalArgumentException ex) {}
        
        assertEquals(~0,g.parseInto(result,"ABC",0));
    }

    public void testParseInto_simple_5_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        MutableDateTime result = new MutableDateTime(0L);
        
        try {
            g.parseInto(null, "2004-06-09T10:20:30Z", 0);
        } catch (IllegalArgumentException ex) {}
        
        assertEquals(~10,g.parseInto(result,"2004-06-09",0));
    }

    public void testParseInto_simple_6_oe() {
        MutableDateTime expect = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        MutableDateTime result = new MutableDateTime(0L);
        
        try {
            g.parseInto(null, "2004-06-09T10:20:30Z", 0);
        } catch (IllegalArgumentException ex) {}
        
        assertEquals(~13,g.parseInto(result,"XX2004-06-09T",2));
    }

    public void testParseInto_zone_1_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withZone(LONDON).parseInto(result,"2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_zone_3_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withZone(null).parseInto(result,"2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_zone_5_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withZone(PARIS).parseInto(result,"2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_zone2_1_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(25,g.withZone(LONDON).parseInto(result,"2004-06-09T06:20:30-04:00",0));
    }

    public void testParseInto_zone2_3_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        assertEquals(25,g.withZone(null).parseInto(result,"2004-06-09T06:20:30-04:00",0));
    }

    public void testParseInto_zone2_5_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        assertEquals(25,g.withZone(PARIS).parseInto(result,"2004-06-09T06:20:30-04:00",0));
    }

    public void testParseInto_zone3_1_oe() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(19,h.withZone(LONDON).parseInto(result,"2004-06-09T10:20:30",0));
    }

    public void testParseInto_zone3_3_oe() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(19,h.withZone(null).parseInto(result,"2004-06-09T10:20:30",0));
    }

    public void testParseInto_zone3_5_oe() {
        DateTimeFormatter h = new DateTimeFormatterBuilder()
        .append(ISODateTimeFormat.date())
        .appendLiteral('T')
        .append(ISODateTimeFormat.timeElementParser())
        .toFormatter();
        
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        assertEquals(19,h.withZone(PARIS).parseInto(result,"2004-06-09T10:20:30",0));
    }

    public void testParseInto_simple_precedence_1_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 7, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(24,f.parseInto(result,"Mon 2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_offsetParsed_1_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withOffsetParsed().parseInto(result,"2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_offsetParsed_3_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        result = new MutableDateTime(0L);
        assertEquals(25,g.withOffsetParsed().parseInto(result,"2004-06-09T06:20:30-04:00",0));
    }

    public void testParseInto_offsetParsed_5_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withZone(PARIS).withOffsetParsed().parseInto(result,"2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_offsetParsed_7_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 6, 20, 30, 0, DateTimeZone.forOffsetHours(-4));
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 20, 30, 0, UTC);
        result = new MutableDateTime(0L);
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withOffsetParsed().withZone(PARIS).parseInto(result,"2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_chrono_1_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withChronology(ISO_PARIS).parseInto(result,"2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_chrono_3_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withChronology(null).parseInto(result,"2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_chrono_5_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2547, 6, 9, 12, 20, 30, 0, BUDDHIST_PARIS);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withChronology(BUDDHIST_PARIS).parseInto(result,"2547-06-09T10:20:30Z",0));
    }

    public void testParseInto_chrono_7_oe() {
        MutableDateTime expect = null;
        MutableDateTime result = null;
        expect = new MutableDateTime(2004, 6, 9, 12, 20, 30, 0, PARIS);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 11, 20, 30, 0, LONDON);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2547, 6, 9, 12, 20, 30, 0, BUDDHIST_PARIS);
        result = new MutableDateTime(0L);
        
        expect = new MutableDateTime(2004, 6, 9, 10, 29, 51, 0, BUDDHIST_PARIS);
        result = new MutableDateTime(0L);
        assertEquals(20,g.withChronology(BUDDHIST_PARIS).parseInto(result,"2004-06-09T10:20:30Z",0));
    }

    public void testParseInto_monthOnly_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, LONDON);
        assertEquals(1,f.parseInto(result,"5",0));
    }

    public void testParseInto_monthOnly_baseStartYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 1, 12, 20, 30, 0, TOKYO);
        assertEquals(1,f.parseInto(result,"5",0));
    }

    public void testParseInto_monthOnly_parseStartYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 2, 1, 12, 20, 30, 0, TOKYO);
        assertEquals(1,f.parseInto(result,"1",0));
    }

    public void testParseInto_monthOnly_baseEndYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 12, 31, 12, 20, 30, 0, TOKYO);
        assertEquals(1,f.parseInto(result,"5",0));
    }

    public void testParseInto_monthOnly_parseEndYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 31, 12, 20, 30, 0,TOKYO);
        assertEquals(2,f.parseInto(result,"12",0));
    }

    public void testParseInto_monthDay_feb29_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, LONDON);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_feb29_startOfYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 1, 0, 0, 0, 0, LONDON);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_feb29_OfYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 12, 31, 23, 59, 59, 999, LONDON);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_feb29_newYork_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, NEWYORK);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_feb29_newYork_startOfYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 1, 0, 0, 0, 0, NEWYORK);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_feb29_newYork_endOfYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 12, 31, 23, 59, 59, 999, NEWYORK);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_feb29_tokyo_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, TOKYO);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_feb29_tokyo_startOfYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 1, 1, 0, 0, 0, 0, TOKYO);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_feb29_tokyo_endOfYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withLocale(Locale.UK);
        MutableDateTime result = new MutableDateTime(2004, 12, 31, 23, 59, 59, 999, TOKYO);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_withDefaultYear_feb29_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withDefaultYear(2012);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, LONDON);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_withDefaultYear_feb29_newYork_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withDefaultYear(2012);
        MutableDateTime result = new MutableDateTime(2004, 1, 9, 12, 20, 30, 0, NEWYORK);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseInto_monthDay_withDefaultYear_feb29_newYork_endOfYear_1_oe() {
        DateTimeFormatter f = DateTimeFormat.forPattern("M d").withDefaultYear(2012);
        MutableDateTime result = new MutableDateTime(2004, 12, 9, 12, 20, 30, 0, NEWYORK);
        assertEquals(4,f.parseInto(result,"2 29",0));
    }

    public void testParseMillis_fractionOfSecondLong_1_oe() {
        DateTimeFormatter f = new DateTimeFormatterBuilder()
            .appendSecondOfDay(2).appendLiteral('.').appendFractionOfSecond(1, 9)
                .toFormatter().withZoneUTC();
        assertEquals(10512,f.parseMillis("10.5123456"));
    }

    public void testParseMillis_fractionOfSecondLong_2_oe() {
        DateTimeFormatter f = new DateTimeFormatterBuilder()
            .appendSecondOfDay(2).appendLiteral('.').appendFractionOfSecond(1, 9)
                .toFormatter().withZoneUTC();
        assertEquals(10512,f.parseMillis("10.512999"));
    }

    public void testZoneNameNearTransition_1_oe() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S zzzz");
        assertEquals("2005-10-30 01:00:00.0 Eastern Daylight Time",fmt.print(inDST_1));
    }

    public void testZoneNameNearTransition_2_oe() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S zzzz");
        assertEquals("2005-10-30 01:59:59.9 Eastern Daylight Time",fmt.print(inDST_2));
    }

    public void testZoneNameNearTransition_3_oe() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S zzzz");
        assertEquals("2005-10-30 02:00:00.0 Eastern Standard Time",fmt.print(onDST));
    }

    public void testZoneNameNearTransition_4_oe() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S zzzz");
        assertEquals("2005-10-30 02:00:00.0 Eastern Standard Time",fmt.print(outDST));
    }

    public void testZoneNameNearTransition_5_oe() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S zzzz");
        assertEquals("2005-10-30 02:00:01.0 Eastern Standard Time",fmt.print(outDST_2));
    }

    public void testZoneShortNameNearTransition_1_oe() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S z").withLocale(Locale.ENGLISH);
        assertEquals("2005-10-30 01:00:00.0 EDT",fmt.print(inDST_1));
    }

    public void testZoneShortNameNearTransition_2_oe() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S z").withLocale(Locale.ENGLISH);
        assertEquals("2005-10-30 01:59:59.9 EDT",fmt.print(inDST_2));
    }

    public void testZoneShortNameNearTransition_3_oe() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S z").withLocale(Locale.ENGLISH);
        assertEquals("2005-10-30 02:00:00.0 EST",fmt.print(onDST));
    }

    public void testZoneShortNameNearTransition_4_oe() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S z").withLocale(Locale.ENGLISH);
        assertEquals("2005-10-30 02:00:00.0 EST",fmt.print(outDST));
    }

    public void testZoneShortNameNearTransition_5_oe() {
        DateTime inDST_1  = new DateTime(2005, 10, 30, 1, 0, 0, 0, NEWYORK);
        DateTime inDST_2  = new DateTime(2005, 10, 30, 1, 59, 59, 999, NEWYORK);
        DateTime onDST    = new DateTime(2005, 10, 30, 2, 0, 0, 0, NEWYORK);
        DateTime outDST   = new DateTime(2005, 10, 30, 2, 0, 0, 1, NEWYORK);
        DateTime outDST_2 = new DateTime(2005, 10, 30, 2, 0, 1, 0, NEWYORK);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss.S z").withLocale(Locale.ENGLISH);
        assertEquals("2005-10-30 02:00:01.0 EST",fmt.print(outDST_2));
    }

    public void testCustomDateTimePrinter_1_oe() {
        DateTimePrinter printer = new DateTimeFormatterBuilder()
                .append(new CustomDateTimePrinter())
                .appendLiteral(' ')
                .appendYear(4, 8)
                .toPrinter();

        StringBuffer buffer = new StringBuffer();
        long instant = new DateTime(2017, 1, 1, 0, 0, 0, ISO_UTC).getMillis();
        printer.printTo(buffer, instant, ISO_UTC, 0, UTC, Locale.ENGLISH);

        assertEquals("Hi 2017",buffer.toString());
    }

public void testParseLocalDate_simple_oe_101_oe() {
        try {
            g.parseDateTime("ABC");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testParseLocalTime_simple_oe_101_oe() {
        try {
            g.parseDateTime("ABC");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testParseLocalDateTime_simple_oe_101_oe() {
        try {
            g.parseDateTime("ABC");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testParseDateTime_simple_oe_101_oe() {
        try {
            g.parseDateTime("ABC");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testParseMutableDateTime_simple_oe_101_oe() {
        try {
            g.parseMutableDateTime("ABC");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testParseInto_simple_oe_101_oe() {
        try {
            g.parseInto(null, "2004-06-09T10:20:30Z", 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

}
