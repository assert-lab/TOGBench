/*
 *  Copyright 2001-2014 Stephen Colebourne
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

import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This class is a Junit unit test for PeriodFormatterBuilder.
 *
 * @author Stephen Colebourne
 */
public class TestPeriodFormatterBuilder_OE25Dev extends TestCase {
    
    private static final Period PERIOD = new Period(1, 2, 3, 4, 5, 6, 7, 8);
    private static final Period EMPTY_PERIOD = new Period(0, 0, 0, 0, 0, 0, 0, 0);
    private static final Period YEAR_DAY_PERIOD = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
    private static final Period EMPTY_YEAR_DAY_PERIOD = new Period(0, 0, 0, 0, 0, 0, 0, 0, PeriodType.yearDayTime());
    private static final Period TIME_PERIOD = new Period(0, 0, 0, 0, 5, 6, 7, 8);
    private static final Period DATE_PERIOD = new Period(1, 2, 3, 4, 0, 0, 0, 0);
    private static final String NULL_STRING = null;
    private static final String[] NULL_STRING_ARRAY = null;

    //private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    //private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");

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
    
    private PeriodFormatterBuilder builder;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestPeriodFormatterBuilder_OE25Dev.class);
    }

    public TestPeriodFormatterBuilder_OE25Dev(String name) {
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
        
        builder = new PeriodFormatterBuilder();
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
    public void testToFormatterPrinterParser() {
        builder.appendYears();
        assertNotNull(builder.toFormatter());
        assertNotNull(builder.toPrinter());
        assertNotNull(builder.toParser());
    }

    //-----------------------------------------------------------------------
    public void testFormatYears() {
        PeriodFormatter f = builder.appendYears().toFormatter();
        assertEquals("1",f.print(PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatMonths() {
        PeriodFormatter f = builder.appendMonths().toFormatter();
        assertEquals("2",f.print(PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatWeeks() {
        PeriodFormatter f = builder.appendWeeks().toFormatter();
        assertEquals("3",f.print(PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatDays() {
        PeriodFormatter f = builder.appendDays().toFormatter();
        assertEquals("4",f.print(PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatHours() {
        PeriodFormatter f = builder.appendHours().toFormatter();
        assertEquals("5",f.print(PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatMinutes() {
        PeriodFormatter f = builder.appendMinutes().toFormatter();
        assertEquals("6",f.print(PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatSeconds() {
        PeriodFormatter f = builder.appendSeconds().toFormatter();
        assertEquals("7",f.print(PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatSecondsWithMillis() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        assertEquals("7.000",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        assertEquals("7.001",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        assertEquals("7.999",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        assertEquals("8.000",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        assertEquals("8.001",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        assertEquals("6.999",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        assertEquals("-6.999",f.print(p));
        assertEquals(6,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
        assertEquals("-7.001",f.print(p));
        assertEquals(6,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0.000",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatSecondsWithOptionalMillis() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        assertEquals("7",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        assertEquals("7.001",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        assertEquals("7.999",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        assertEquals("8",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        assertEquals("8.001",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        assertEquals("6.999",f.print(p));
        assertEquals(5,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        assertEquals("-6.999",f.print(p));
        assertEquals(6,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
        assertEquals("-7.001",f.print(p));
        assertEquals(6,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
        
        p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatMillis() {
        PeriodFormatter f = builder.appendMillis().toFormatter();
        assertEquals("8",f.print(PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0",f.print(p));
        assertEquals(1,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatMillis3Digit() {
        PeriodFormatter f = builder.appendMillis3Digit().toFormatter();
        assertEquals("008",f.print(PERIOD));
        assertEquals(3,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("000",f.print(p));
        assertEquals(3,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    //-----------------------------------------------------------------------
    public void testFormatPrefixSimple1() {
        PeriodFormatter f = builder.appendPrefix("Years:").appendYears().toFormatter();
        assertEquals("Years:1",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Years:0",f.print(p));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatPrefixSimple2() {
        PeriodFormatter f = builder.appendPrefix("Hours:").appendHours().toFormatter();
        assertEquals("Hours:5",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Hours:0",f.print(p));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatPrefixSimple3() {
        try {
            builder.appendPrefix(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testFormatPrefixSimple4IgnoringPrefix() {
        PeriodFormatter f = builder.appendPrefix("m").appendMinutes()
                .appendSeparator(" ").appendPrefix("ms").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertEquals("ms1",oneMS);
        Period period = f.parsePeriod(oneMS);
        assertEquals(Period.millis(1),period);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(1),period);
    }

    public void testPluralAffixParseOrder() {
        PeriodFormatter f = builder.appendDays().appendSuffix("day", "days").toFormatter();
        String twoDays = Period.days(2).toString(f);
        Period period = f.parsePeriod(twoDays);
        assertEquals(Period.days(2),period);
        period = f.parsePeriod(twoDays.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.days(2),period);
    }

    public void testFormatPrefixPlural1() {
        PeriodFormatter f = builder.appendPrefix("Year:", "Years:").appendYears().toFormatter();
        assertEquals("Year:1",f.print(PERIOD));
        assertEquals(6,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Years:0",f.print(p));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatPrefixPlural2() {
        PeriodFormatter f = builder.appendPrefix("Hour:", "Hours:").appendHours().toFormatter();
        assertEquals("Hours:5",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Hours:0",f.print(p));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatPrefixPlural3() {
        try {
            builder.appendPrefix(null, "");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            builder.appendPrefix("", null);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            builder.appendPrefix(NULL_STRING, null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }
    
    public void testFormatPrefixPlural4IgnoringPrefix() {
        PeriodFormatter f = builder.appendPrefix("m", "ms").appendMinutes()
                .appendSeparator(" ").appendPrefix("mss", "msss").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertEquals("mss1",oneMS);
        Period period = f.parsePeriod(oneMS);
        assertEquals(Period.millis(1),period);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(1),period);
        
        String twoMS = Period.millis(2).toString(f);
        assertEquals("msss2",twoMS);
        Period period2 = f.parsePeriod(twoMS);
        assertEquals(Period.millis(2),period2);
        period = f.parsePeriod(twoMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(2),period2);
    }
    
    public void testRegExAffixParseOrder() {
        PeriodFormatter f = builder.appendDays()
                .appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"day", "days"}).toFormatter();
        String twoDays = Period.days(2).toString(f);
        Period period = f.parsePeriod(twoDays);
        assertEquals(Period.days(2),period);
        period = f.parsePeriod(twoDays.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.days(2),period);
    }

    public void testFormatPrefixRegEx1() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Year:", "Years:" }).appendYears()
                .toFormatter();
        assertEquals("Year:1",f.print(PERIOD));
        assertEquals(6,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));

        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Years:0",f.print(p));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatPrefixRegEx2() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Hour:", "Hours:" }).appendHours()
                .toFormatter();
        assertEquals("Hours:5",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));

        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Hours:0",f.print(p));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatPrefixRegEx3() {
        try {
            builder.appendPrefix(null, new String[0]);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        try {
            builder.appendPrefix(new String[0], null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        try {
            builder.appendPrefix(NULL_STRING_ARRAY, null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        try {
            builder.appendPrefix(new String[0], new String[0]);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        try {
            builder.appendPrefix(new String[1], new String[2]);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testFormatPrefixRegEx4IgnoringPrefix() {
        PeriodFormatter f = builder
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"}).appendMinutes()
                .appendSeparator(" ")
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"}).appendMillis()
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertEquals("mss1",oneMS);
        Period period = f.parsePeriod(oneMS);
        assertEquals(Period.millis(1),period);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(1),period);
        
        String twoMS = Period.millis(2).toString(f);
        assertEquals("msss2",twoMS);
        Period period2 = f.parsePeriod(twoMS);
        assertEquals(Period.millis(2),period2);
        period = f.parsePeriod(twoMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(2),period2);
    }

    //-----------------------------------------------------------------------
    public void testFormatPrefixComposite1() {
        PeriodFormatter f = builder.appendPrefix("d")
                .appendPrefix("a", "ay")
                .appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "y:", "s:" })
                .appendDays().toFormatter();
        String oneMS = Period.days(2).toString(f);
        assertEquals("days:2",oneMS);
        Period period = f.parsePeriod(oneMS);
        assertEquals(Period.days(2),period);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.days(2),period);
    }

    //-----------------------------------------------------------------------
    public void testFormatSuffixSimple1() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" years").toFormatter();
        assertEquals("1 years",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 years",f.print(p));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatSuffixSimple2() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hours").toFormatter();
        assertEquals("5 hours",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 hours",f.print(p));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatSuffixSimple3() {
        try {
            builder.appendSuffix(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testFormatSuffixSimple4() {
        try {
            builder.appendSuffix(" hours");
            fail();
        } catch (IllegalStateException ex) {}
    }
    
    public void testFormatPrefixSimple5IgnoringPrefix() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m")
                .appendSeparator(" ").appendMillis().appendSuffix("ms").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertEquals("1ms",oneMS);
        Period period = f.parsePeriod(oneMS);
        assertEquals(Period.millis(1),period);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(1),period);
    }

    public void testFormatPrefixSimple6IgnoringPrefix() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("M")
                .appendSeparator(" ").appendMillis().appendSuffix("ms").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertEquals("1ms",oneMS);
        Period period = f.parsePeriod(oneMS);
        assertEquals(Period.millis(1),period);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(1),period);
    }

    public void testFormatSuffixPlural1() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" year", " years").toFormatter();
        assertEquals("1 year",f.print(PERIOD));
        assertEquals(6,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 years",f.print(p));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatSuffixPlural2() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hour", " hours").toFormatter();
        assertEquals("5 hours",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 hours",f.print(p));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    public void testFormatSuffixPlural3() {
        try {
            builder.appendSuffix(null, "");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            builder.appendSuffix("", null);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            builder.appendSuffix(NULL_STRING, null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testFormatSuffixPlural4() {
        try {
            builder.appendSuffix(" hour", " hours");
            fail();
        } catch (IllegalStateException ex) {}
    }
    
    public void testFormatSuffixPlural4IgnoringPrefix() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m", "ms")
                .appendSeparator(" ").appendMillis().appendSuffix("mss", "msss").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertEquals("1mss",oneMS);
        Period period = f.parsePeriod(oneMS);
        assertEquals(Period.millis(1),period);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(1),period);
        
        String twoMS = Period.millis(2).toString(f);
        assertEquals("2msss",twoMS);
        Period period2 = f.parsePeriod(twoMS);
        assertEquals(Period.millis(2),period2);
        period = f.parsePeriod(twoMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(2),period);
    }

    public void testFormatSuffixRegEx1() {
        PeriodFormatter f = builder.appendYears().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " year", " years" })
                .toFormatter();
        assertEquals("1 year",f.print(PERIOD));
        assertEquals(6,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p2 = new Period(2, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("2 years",f.print(p2));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p2,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p2,Integer.MAX_VALUE,null));
        
        Period p0 = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 years",f.print(p0));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p0,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p0,Integer.MAX_VALUE,null));
    }

    public void testFormatSuffixRegEx2() {
        PeriodFormatter f = builder.appendHours().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " hour", " hours" }).toFormatter();
        assertEquals("5 hours",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));

        Period p2 = new Period(0, 0, 0, 0, 2, 0, 0, 0);
        assertEquals("2 hours",f.print(p2));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p2,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p2,Integer.MAX_VALUE,null));

        Period p0 = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 hours",f.print(p0));
        assertEquals(7,f.getPrinter().calculatePrintedLength(p0,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p0,Integer.MAX_VALUE,null));
    }

    public void testFormatSuffixRegEx3() {
        try {
            builder.appendSuffix(null, new String[0]);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        try {
            builder.appendSuffix(new String[0], null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        try {
            builder.appendSuffix(NULL_STRING_ARRAY, null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        try {
            builder.appendSuffix(new String[0], new String[0]);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        try {
            builder.appendSuffix(new String[1], new String[2]);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testFormatSuffixRegEx4() {
        try {
            builder.appendSuffix(new String[] { "^1$", "^.*$" }, new String[] { " hour", " hours" });
            fail();
        } catch (IllegalStateException ex) {
        }
    }

    public void testFormatSuffixRegEx5IgnoringAffix() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"})
                .appendSeparator(" ")
                .appendMillis().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"})
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertEquals("1mss",oneMS);
        Period period = f.parsePeriod(oneMS);
        assertEquals(Period.millis(1),period);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(1),period);
        
        String twoMS = Period.millis(2).toString(f);
        assertEquals("2msss",twoMS);
        Period period2 = f.parsePeriod(twoMS);
        assertEquals(Period.millis(2),period2);
        period = f.parsePeriod(twoMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(2),period);
    }
    //-----------------------------------------------------------------------
    public void testFormatSuffixComposite1() {
        PeriodFormatter f = builder.appendDays().appendSuffix("d")
                .appendSuffix("a", "ay")
                .appendSuffix(new String[] { "^1$", "^.*$" }, new String[] { "y", "s" })
                .toFormatter();
        String oneMS = Period.days(2).toString(f);
        assertEquals("2days",oneMS);
        Period period = f.parsePeriod(oneMS);
        assertEquals(Period.days(2),period);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.days(2),period);
    }

    public void testFormatSuffixComposite5IgnoringAffix() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix("m")
                .appendSeparator(" ")
                .appendMillis().appendSuffix("m").appendSuffix("s")
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertEquals("1ms",oneMS);
        Period period = f.parsePeriod(oneMS);
        assertEquals(Period.millis(1),period);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertEquals(Period.millis(1),period);
    }
    
    //-----------------------------------------------------------------------
    public void testFormatPrefixSuffix() {
        PeriodFormatter f = builder.appendPrefix("P").appendYears().appendSuffix("Y").toFormatter();
        assertEquals("P1Y",f.print(PERIOD));
        assertEquals(3,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("P0Y",f.print(p));
        assertEquals(3,f.getPrinter().calculatePrintedLength(p,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(p,Integer.MAX_VALUE,null));
    }

    //-----------------------------------------------------------------------
    public void testFormatSeparatorSimple() {
        PeriodFormatter f = builder.appendYears().appendSeparator("T").appendHours().toFormatter();
        assertEquals("1T5",f.print(PERIOD));
        assertEquals(3,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("5",f.print(TIME_PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(TIME_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(TIME_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("1",f.print(DATE_PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(DATE_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(DATE_PERIOD,Integer.MAX_VALUE,null));
    }

    public void testFormatSeparatorComplex() {
        PeriodFormatter f = builder
            .appendYears().appendSeparator(", ", " and ")
            .appendHours().appendSeparator(", ", " and ")
            .appendMinutes().appendSeparator(", ", " and ")
            .toFormatter();
        assertEquals("1,5 and 6",f.print(PERIOD));
        assertEquals(10,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(3,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("5 and 6",f.print(TIME_PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(TIME_PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(TIME_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("1",f.print(DATE_PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(DATE_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(DATE_PERIOD,Integer.MAX_VALUE,null));
    }

    public void testFormatSeparatorIfFieldsAfter() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsAfter("T").appendHours().toFormatter();
        assertEquals("1T5",f.print(PERIOD));
        assertEquals(3,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("T5",f.print(TIME_PERIOD));
        assertEquals(2,f.getPrinter().calculatePrintedLength(TIME_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(TIME_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("1",f.print(DATE_PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(DATE_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(DATE_PERIOD,Integer.MAX_VALUE,null));
    }

    public void testFormatSeparatorIfFieldsBefore() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsBefore("T").appendHours().toFormatter();
        assertEquals("1T5",f.print(PERIOD));
        assertEquals(3,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("5",f.print(TIME_PERIOD));
        assertEquals(1,f.getPrinter().calculatePrintedLength(TIME_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(TIME_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("1T",f.print(DATE_PERIOD));
        assertEquals(2,f.getPrinter().calculatePrintedLength(DATE_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(DATE_PERIOD,Integer.MAX_VALUE,null));
    }

    //-----------------------------------------------------------------------
    public void testFormatLiteral() {
        PeriodFormatter f = builder.appendLiteral("HELLO").toFormatter();
        assertEquals("HELLO",f.print(PERIOD));
        assertEquals(5,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(0,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
    }

    public void testFormatAppendFormatter() {
        PeriodFormatter base = builder.appendYears().appendLiteral("-").toFormatter();
        PeriodFormatter f = new PeriodFormatterBuilder().append(base).appendYears().toFormatter();
        assertEquals("1-1",f.print(PERIOD));
        assertEquals(3,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
    }

    public void testFormatMinDigits() {
        PeriodFormatter f = new PeriodFormatterBuilder().minimumPrintedDigits(4).appendYears().toFormatter();
        assertEquals("0001",f.print(PERIOD));
        assertEquals(4,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
    }

    //-----------------------------------------------------------------------
    public void testFormatPrintZeroDefault() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("1-2-3-4",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(4,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("---0",f.print(EMPTY_YEAR_DAY_PERIOD));
        assertEquals(4,f.getPrinter().calculatePrintedLength(EMPTY_YEAR_DAY_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(EMPTY_YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("1---4",f.print(YEAR_DAY_PERIOD));
        assertEquals(5,f.getPrinter().calculatePrintedLength(YEAR_DAY_PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("---0",f.print(EMPTY_PERIOD));
        assertEquals(4,f.getPrinter().calculatePrintedLength(EMPTY_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(EMPTY_PERIOD,Integer.MAX_VALUE,null));
        
        // test only last instance of same field is output
        f = new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendYears().toFormatter();
        assertEquals("-0",f.print(EMPTY_PERIOD));
        assertEquals(2,f.getPrinter().calculatePrintedLength(EMPTY_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(EMPTY_PERIOD,Integer.MAX_VALUE,null));
    }

    public void testFormatPrintZeroRarelyLast() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("1-2-3-4",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(4,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("---0",f.print(EMPTY_YEAR_DAY_PERIOD));
        assertEquals(4,f.getPrinter().calculatePrintedLength(EMPTY_YEAR_DAY_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(EMPTY_YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("1---4",f.print(YEAR_DAY_PERIOD));
        assertEquals(5,f.getPrinter().calculatePrintedLength(YEAR_DAY_PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("---0",f.print(EMPTY_PERIOD));
        assertEquals(4,f.getPrinter().calculatePrintedLength(EMPTY_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(EMPTY_PERIOD,Integer.MAX_VALUE,null));
    }

    public void testFormatPrintZeroRarelyFirst() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("1-2-3-4",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(4,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("0---",f.print(EMPTY_YEAR_DAY_PERIOD));
        assertEquals(4,f.getPrinter().calculatePrintedLength(EMPTY_YEAR_DAY_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(EMPTY_YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("1---4",f.print(YEAR_DAY_PERIOD));
        assertEquals(5,f.getPrinter().calculatePrintedLength(YEAR_DAY_PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("0---",f.print(EMPTY_PERIOD));
        assertEquals(4,f.getPrinter().calculatePrintedLength(EMPTY_PERIOD,null));
        assertEquals(1,f.getPrinter().countFieldsToPrint(EMPTY_PERIOD,Integer.MAX_VALUE,null));
    }

    public void testFormatPrintZeroRarelyFirstYears() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendYears().toFormatter();
        assertEquals("0",f.print(EMPTY_PERIOD));
    }

    public void testFormatPrintZeroRarelyFirstMonths() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendMonths().toFormatter();
        assertEquals("0",f.print(EMPTY_PERIOD));
    }

    public void testFormatPrintZeroRarelyFirstWeeks() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendWeeks().toFormatter();
        assertEquals("0",f.print(EMPTY_PERIOD));
    }

    public void testFormatPrintZeroRarelyFirstDays() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendDays().toFormatter();
        assertEquals("0",f.print(EMPTY_PERIOD));
    }

    public void testFormatPrintZeroRarelyFirstHours() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendHours().toFormatter();
        assertEquals("0",f.print(EMPTY_PERIOD));
    }

    public void testFormatPrintZeroRarelyFirstMinutes() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendMinutes().toFormatter();
        assertEquals("0",f.print(EMPTY_PERIOD));
    }

    public void testFormatPrintZeroRarelyFirstSeconds() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendSeconds().toFormatter();
        assertEquals("0",f.print(EMPTY_PERIOD));
    }

    public void testFormatPrintZeroIfSupported() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("1-2-3-4",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(4,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("0---0",f.print(EMPTY_YEAR_DAY_PERIOD));
        assertEquals(5,f.getPrinter().calculatePrintedLength(EMPTY_YEAR_DAY_PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(EMPTY_YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("1---4",f.print(YEAR_DAY_PERIOD));
        assertEquals(5,f.getPrinter().calculatePrintedLength(YEAR_DAY_PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("0-0-0-0",f.print(EMPTY_PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(EMPTY_PERIOD,null));
        assertEquals(4,f.getPrinter().countFieldsToPrint(EMPTY_PERIOD,Integer.MAX_VALUE,null));
    }

    public void testFormatPrintZeroAlways() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("1-2-3-4",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(4,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("0-0-0-0",f.print(EMPTY_YEAR_DAY_PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(EMPTY_YEAR_DAY_PERIOD,null));
        assertEquals(4,f.getPrinter().countFieldsToPrint(EMPTY_YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("1-0-0-4",f.print(YEAR_DAY_PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(YEAR_DAY_PERIOD,null));
        assertEquals(4,f.getPrinter().countFieldsToPrint(YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("0-0-0-0",f.print(EMPTY_PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(EMPTY_PERIOD,null));
        assertEquals(4,f.getPrinter().countFieldsToPrint(EMPTY_PERIOD,Integer.MAX_VALUE,null));
    }

    public void testFormatPrintZeroNever() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("1-2-3-4",f.print(PERIOD));
        assertEquals(7,f.getPrinter().calculatePrintedLength(PERIOD,null));
        assertEquals(4,f.getPrinter().countFieldsToPrint(PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("---",f.print(EMPTY_YEAR_DAY_PERIOD));
        assertEquals(3,f.getPrinter().calculatePrintedLength(EMPTY_YEAR_DAY_PERIOD,null));
        assertEquals(0,f.getPrinter().countFieldsToPrint(EMPTY_YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("1---4",f.print(YEAR_DAY_PERIOD));
        assertEquals(5,f.getPrinter().calculatePrintedLength(YEAR_DAY_PERIOD,null));
        assertEquals(2,f.getPrinter().countFieldsToPrint(YEAR_DAY_PERIOD,Integer.MAX_VALUE,null));
        
        assertEquals("---",f.print(EMPTY_PERIOD));
        assertEquals(3,f.getPrinter().calculatePrintedLength(EMPTY_PERIOD,null));
        assertEquals(0,f.getPrinter().countFieldsToPrint(EMPTY_PERIOD,Integer.MAX_VALUE,null));
    }

    //-----------------------------------------------------------------------
    public void testFormatAppend_PrinterParser_null_null() {
        try {
            new PeriodFormatterBuilder().append(null, null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testFormatAppend_PrinterParser_Printer_null() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).appendMonths();
        assertNotNull(bld.toPrinter());
        assertNull(bld.toParser());
        
        PeriodFormatter f = bld.toFormatter();
        assertEquals("1-2",f.print(PERIOD));
        try {
            f.parsePeriod("1-2");
            fail();
        } catch (UnsupportedOperationException ex) {}
    }

    public void testFormatAppend_PrinterParser_null_Parser() {
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(null, parser).appendMonths();
        assertNull(bld.toPrinter());
        assertNotNull(bld.toParser());
        
        PeriodFormatter f = bld.toFormatter();
        try {
            f.print(PERIOD);
            fail();
        } catch (UnsupportedOperationException ex) {}
        assertEquals(new Period(0,2,1,0,0,0,0,0),f.parsePeriod("1-2"));
    }

    public void testFormatAppend_PrinterParser_PrinterParser() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, parser).appendMonths();
        assertNotNull(bld.toPrinter());
        assertNotNull(bld.toParser());
        
        PeriodFormatter f = bld.toFormatter();
        assertEquals("1-2",f.print(PERIOD));
        assertEquals(new Period(0,2,1,0,0,0,0,0),f.parsePeriod("1-2"));
    }

    public void testFormatAppend_PrinterParser_Printer_null_null_Parser() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).append(null, parser);
        assertNull(bld.toPrinter());
        assertNull(bld.toParser());
        
        try {
            bld.toFormatter();
            fail();
        } catch (IllegalStateException ex) {}
    }

    public void testFormatAppend_PrinterParserThenClear() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).append(null, parser);
        assertNull(bld.toPrinter());
        assertNull(bld.toParser());
        bld.clear();
        bld.appendMonths();
        assertNotNull(bld.toPrinter());
        assertNotNull(bld.toParser());
    }

    public void testBug2495455() {
        PeriodFormatter pfmt1 = new PeriodFormatterBuilder()
            .appendLiteral("P")
            .appendYears()
            .appendSuffix("Y")
            .appendMonths()
            .appendSuffix("M")
            .appendWeeks()
            .appendSuffix("W")
            .appendDays()
            .appendSuffix("D")
            .appendSeparatorIfFieldsAfter("T")
            .appendHours()
            .appendSuffix("H")
            .appendMinutes()
            .appendSuffix("M")
            .appendSecondsWithOptionalMillis()
            .appendSuffix("S")
            .toFormatter();
        PeriodFormatter pfmt2 = new PeriodFormatterBuilder()
            .append(ISOPeriodFormat.standard())
            .toFormatter();
        pfmt1.parsePeriod("PT1003199059S");
        pfmt2.parsePeriod("PT1003199059S");
        pfmt2.parsePeriod("pt1003199059S");
    }

    public void testMonthsAndMinutesAreConsideredSeparateAndCaseIsNotIgnored() {
        PeriodFormatter formatter = builder
                .appendMonths().appendSuffix("M").appendSeparator(" ")
                .appendMinutes().appendSuffix("m").appendSeparator(" ")
                .toFormatter();

        String oneMonth = Period.months(1).toString(formatter);
        assertEquals("1M",oneMonth);
        Period period = formatter.parsePeriod(oneMonth);
        assertEquals(Period.months(1),period);
        String oneMinute = Period.minutes(1).toString(formatter);
        assertEquals("1m",oneMinute);
        period = formatter.parsePeriod(oneMinute);
        assertEquals(Period.minutes(1),period);
    }

    public void testAppendSeparatorIfFieldsBeforeThrowsIllegalStateExceptionAndAppendPrefixTakingString() {
        PeriodFormatterBuilder periodFormatterBuilder = new PeriodFormatterBuilder();
        periodFormatterBuilder.appendPrefix("=9Z/])WG");

        try {
            periodFormatterBuilder.appendSeparatorIfFieldsBefore("=9Z/])WG");
            fail("Expecting exception: IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals(PeriodFormatterBuilder.class.getName(),e.getStackTrace()[0].getClassName());
        }
    }

    public void testAppendSeparatorIfFieldsBeforeThrowsIllegalStateExceptionAndAppendSeparatorIfFieldsAfter() {
        PeriodFormatterBuilder periodFormatterBuilder = new PeriodFormatterBuilder();
        periodFormatterBuilder.appendSeparatorIfFieldsAfter("3xmZ\"*'Q={=");

        try {
            periodFormatterBuilder.appendSeparatorIfFieldsBefore("3xmZ\"*'Q={=");
            fail("Expecting exception: IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals(PeriodFormatterBuilder.class.getName(),e.getStackTrace()[0].getClassName());
        }
    }

    public void testAppendSeparatorTaking3ArgumentsWithEmptyStringAndNull() {
        PeriodFormatterBuilder periodFormatterBuilder = new PeriodFormatterBuilder();

        try {
            periodFormatterBuilder.appendSeparator("", null, null);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(PeriodFormatterBuilder.class.getName(),e.getStackTrace()[0].getClassName());
        }
    }

    public void testAppendSeparatorTaking3ArgumentsWithNullAndNonEmptyArray() {
        PeriodFormatterBuilder periodFormatterBuilder = new PeriodFormatterBuilder();
        String[] stringArray = new String[3];

        try {
            periodFormatterBuilder.appendSeparator(null, null, stringArray);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(PeriodFormatterBuilder.class.getName(),e.getStackTrace()[0].getClassName());
        }
    }

    public void testAppendSuffixTaking2StringArraysThrowsIllegalStateException() {
        PeriodFormatterBuilder periodFormatterBuilder = new PeriodFormatterBuilder();
        String[] stringArray = new String[1];
        stringArray[0] = "8io`#&*f6&";
        periodFormatterBuilder.appendSecondsWithMillis();
        periodFormatterBuilder.appendSeparator("8io`#&*f6&", "NW7");

        try {
            periodFormatterBuilder.appendSuffix(stringArray, stringArray);
            fail("Expecting exception: IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals(PeriodFormatterBuilder.class.getName(),e.getStackTrace()[0].getClassName());
        }
    }

    public void testAppendLiteralThrowsIllegalArgumentException() {
        PeriodFormatterBuilder periodFormatterBuilder = new PeriodFormatterBuilder();

        try {
            periodFormatterBuilder.appendLiteral(null);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(PeriodFormatterBuilder.class.getName(),e.getStackTrace()[0].getClassName());
        }
    }

    public void testAppendTakingPeriodFormatterThrowsIllegalArgumentException() {
        PeriodFormatterBuilder periodFormatterBuilder = new PeriodFormatterBuilder();

        try {
            periodFormatterBuilder.append(null);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(PeriodFormatterBuilder.class.getName(),e.getStackTrace()[0].getClassName());
        }
    }

    public void testToFormatterPrinterParser_3_oe() {
        builder.appendYears();
        assertNotNull(builder.toParser());
    }

    public void testFormatYears_1_oe() {
        PeriodFormatter f = builder.appendYears().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatYears_2_oe() {
        PeriodFormatter f = builder.appendYears().toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatYears_3_oe() {
        PeriodFormatter f = builder.appendYears().toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatYears_4_oe() {
        PeriodFormatter f = builder.appendYears().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0", f.print(p));
    }

    public void testFormatYears_5_oe() {
        PeriodFormatter f = builder.appendYears().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0 years 0 months 0 days 0 hours 0 minutes 0.000000000 seconds", f.getPrinter().print(p));
    }

    public void testFormatYears_6_oe() {
        PeriodFormatter f = builder.appendYears().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0 years 0 months 0 days 0 hours 0 minutes 0.000000000 seconds", f.getPrinter().print(p));
    }

    public void testFormatMonths_1_oe() {
        PeriodFormatter f = builder.appendMonths().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatMonths_2_oe() {
        PeriodFormatter f = builder.appendMonths().toFormatter();
// incorrect assertion         assertEquals("MM", f.getPrinter().getPattern());
    }

    public void testFormatMonths_3_oe() {
        PeriodFormatter f = builder.appendMonths().toFormatter();
// incorrect assertion         assertEquals("MM", f.getPrinter().getPattern());
    }

    public void testFormatMonths_4_oe() {
        PeriodFormatter f = builder.appendMonths().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0", f.print(p));
    }

    public void testFormatMonths_5_oe() {
        PeriodFormatter f = builder.appendMonths().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0", f.getPrinter().getPrefix());
    }

    public void testFormatMonths_6_oe() {
        PeriodFormatter f = builder.appendMonths().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0", f.getPrinter().getPrefix());
    }

    public void testFormatWeeks_1_oe() {
        PeriodFormatter f = builder.appendWeeks().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatWeeks_2_oe() {
        PeriodFormatter f = builder.appendWeeks().toFormatter();
// incorrect assertion         assertEquals("weeks", f.getPrinter().getPattern());
    }

    public void testFormatWeeks_3_oe() {
        PeriodFormatter f = builder.appendWeeks().toFormatter();
// incorrect assertion         assertEquals("MM/dd/yyyy HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatWeeks_4_oe() {
        PeriodFormatter f = builder.appendWeeks().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0w", f.print(p));
    }

    public void testFormatWeeks_5_oe() {
        PeriodFormatter f = builder.appendWeeks().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0w", f.getPrinter().getPattern());
    }

    public void testFormatWeeks_6_oe() {
        PeriodFormatter f = builder.appendWeeks().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0w", f.getPrinter().getPattern());
    }

    public void testFormatDays_1_oe() {
        PeriodFormatter f = builder.appendDays().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatDays_2_oe() {
        PeriodFormatter f = builder.appendDays().toFormatter();
// incorrect assertion         assertEquals("MMM d, yyyy", f.getPrinter().getPattern());
    }

    public void testFormatDays_3_oe() {
        PeriodFormatter f = builder.appendDays().toFormatter();
        assertEquals("java.time.format.PeriodPrinter", f.getPrinter().getClass().getName());
    }

    public void testFormatDays_4_oe() {
        PeriodFormatter f = builder.appendDays().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("", f.print(p));
    }

    public void testFormatDays_5_oe() {
        PeriodFormatter f = builder.appendDays().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0", f.getPrinter().getPrefix());
    }

    public void testFormatDays_6_oe() {
        PeriodFormatter f = builder.appendDays().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0", f.getPrinter().getPrefix());
    }

    public void testFormatHours_1_oe() {
        PeriodFormatter f = builder.appendHours().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatHours_2_oe() {
        PeriodFormatter f = builder.appendHours().toFormatter();
// incorrect assertion         assertEquals("HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatHours_3_oe() {
        PeriodFormatter f = builder.appendHours().toFormatter();
// incorrect assertion         assertEquals("HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatHours_4_oe() {
        PeriodFormatter f = builder.appendHours().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("00:00:00", f.print(p));
    }

    public void testFormatHours_5_oe() {
        PeriodFormatter f = builder.appendHours().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().getLocalizedString());
    }

    public void testFormatHours_6_oe() {
        PeriodFormatter f = builder.appendHours().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().getLocalizedString());
    }

    public void testFormatMinutes_1_oe() {
        PeriodFormatter f = builder.appendMinutes().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatMinutes_2_oe() {
        PeriodFormatter f = builder.appendMinutes().toFormatter();
// incorrect assertion         assertEquals("mm", f.getPrinter().getPattern());
    }

    public void testFormatMinutes_3_oe() {
        PeriodFormatter f = builder.appendMinutes().toFormatter();
// incorrect assertion         assertEquals("mm", f.getPrinter().getPattern());
    }

    public void testFormatMinutes_4_oe() {
        PeriodFormatter f = builder.appendMinutes().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("00:00", f.print(p));
    }

    public void testFormatMinutes_5_oe() {
        PeriodFormatter f = builder.appendMinutes().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().getLocalizedString());
    }

    public void testFormatMinutes_6_oe() {
        PeriodFormatter f = builder.appendMinutes().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().getLocalizedString());
    }

    public void testFormatSeconds_1_oe() {
        PeriodFormatter f = builder.appendSeconds().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatSeconds_2_oe() {
        PeriodFormatter f = builder.appendSeconds().toFormatter();
// incorrect assertion         assertEquals("HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeconds_3_oe() {
        PeriodFormatter f = builder.appendSeconds().toFormatter();
// incorrect assertion         assertEquals("HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeconds_4_oe() {
        PeriodFormatter f = builder.appendSeconds().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("00:00:00", f.print(p));
    }

    public void testFormatSeconds_5_oe() {
        PeriodFormatter f = builder.appendSeconds().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().getLocalizedString());
    }

    public void testFormatSeconds_6_oe() {
        PeriodFormatter f = builder.appendSeconds().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().getLocalizedString());
    }

    public void testFormatSecondsWithMillis_1_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        assertEquals("00:00:00.000", f.print(p));
    }

    public void testFormatSecondsWithMillis_2_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
// incorrect assertion         assertEquals("00:00:07", f.getPrinter().getLocalizedString());
    }

    public void testFormatSecondsWithMillis_3_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
// incorrect assertion         assertEquals("00:00:07", f.getPrinter().getLocalizedString());
    }

    public void testFormatSecondsWithMillis_4_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        assertEquals("00:00:00.001", f.print(p));
    }

    public void testFormatSecondsWithMillis_5_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_6_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_7_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        assertEquals("00:00:00.000", f.print(p));
    }

    public void testFormatSecondsWithMillis_8_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_9_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_10_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        assertEquals("00:00:00.000", f.print(p));
    }

    public void testFormatSecondsWithMillis_11_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_12_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
// incorrect assertion         assertEquals("00:00:00.0001000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_13_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        assertEquals("0:00:00.001001", f.print(p));
    }

    public void testFormatSecondsWithMillis_14_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
// incorrect assertion         assertEquals("00:00:00.001001", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_15_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_16_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        assertEquals("00:00:00.000", f.print(p));
    }

    public void testFormatSecondsWithMillis_17_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_18_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_19_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        assertEquals("-00:00:00.001", f.print(p));
    }

    public void testFormatSecondsWithMillis_20_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
// incorrect assertion         assertEquals("00:00:00.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_21_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
// incorrect assertion         assertEquals("00:00:00.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_22_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
        assertNotNull(f.print(p));
    }

    public void testFormatSecondsWithMillis_23_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
// incorrect assertion         assertEquals("HH:mm:ss.SSS", f.getPrinter().getPattern());
    }

    public void testFormatSecondsWithMillis_24_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
// incorrect assertion         assertEquals("00:00:00.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_25_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("-07:00:00.000", f.print(p));
    }

    public void testFormatSecondsWithMillis_26_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithMillis_27_oe() {
        PeriodFormatter f = builder.appendSecondsWithMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithOptionalMillis_1_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        assertEquals("7s", f.print(p));
    }

    public void testFormatSecondsWithOptionalMillis_2_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
// incorrect assertion         assertEquals("00:00:07", f.getPrinter().getLocalizedString());
    }

    public void testFormatSecondsWithOptionalMillis_3_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
// incorrect assertion         assertEquals("00:00:07", f.getPrinter().getLocalizedString());
    }

    public void testFormatSecondsWithOptionalMillis_4_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        assertEquals("7s", f.print(p));
    }

    public void testFormatSecondsWithOptionalMillis_5_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
// incorrect assertion         assertEquals("7s", f.getPrinter().getPattern());
    }

    public void testFormatSecondsWithOptionalMillis_6_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
// incorrect assertion         assertEquals("7s", f.getPrinter().getPattern());
    }

    public void testFormatSecondsWithOptionalMillis_7_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        assertEquals("00:00:07.000", f.print(p));
    }

    public void testFormatSecondsWithOptionalMillis_8_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithOptionalMillis_9_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithOptionalMillis_10_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        assertEquals("7s", f.print(p));
    }

    public void testFormatSecondsWithOptionalMillis_11_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithOptionalMillis_12_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().printPeriod(p));
    }

    public void testFormatSecondsWithOptionalMillis_13_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        assertEquals("7s", f.print(p));
    }

    public void testFormatSecondsWithOptionalMillis_14_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
// incorrect assertion         assertEquals("00:00:07.000", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithOptionalMillis_15_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
// incorrect assertion         assertEquals("00:00:07", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithOptionalMillis_16_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        assertEquals("7s", f.print(p));
    }

    public void testFormatSecondsWithOptionalMillis_17_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
// incorrect assertion         assertEquals("00:00:07", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithOptionalMillis_18_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
// incorrect assertion         assertEquals("00:00:07", f.getPrinter().print(p));
    }

    public void testFormatSecondsWithOptionalMillis_19_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        assertNotNull(f.print(p));
    }

    public void testFormatSecondsWithOptionalMillis_20_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
// incorrect assertion         assertEquals("0:0:0:0:0:0:7", f.getPrinter().getLocalizedString());
    }

    public void testFormatSecondsWithOptionalMillis_21_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
// incorrect assertion         assertEquals("0:0:0:0:0:0:7", f.getPrinter().getLocalizedString());
    }

    public void testFormatSecondsWithOptionalMillis_22_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
        assertEquals("-07:00", f.print(p));
    }

    public void testFormatSecondsWithOptionalMillis_23_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
// incorrect assertion         assertEquals("HH:mm:ss.SSS", f.getPrinter().getPattern());
    }

    public void testFormatSecondsWithOptionalMillis_24_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
// incorrect assertion         assertEquals("HH:mm:ss.SSS", f.getPrinter().getPattern());
    }

    public void testFormatSecondsWithOptionalMillis_25_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("-07:00", f.print(p));
    }

    public void testFormatSecondsWithOptionalMillis_26_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().printPeriod(p));
    }

    public void testFormatSecondsWithOptionalMillis_27_oe() {
        PeriodFormatter f = builder.appendSecondsWithOptionalMillis().toFormatter();
        Period p = new Period(0, 0, 0, 0, 0, 0, 7, 0);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 999);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1000);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, 1001);
        
        p = new Period(0, 0, 0, 0, 0, 0, 7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, 1);
        
        p = new Period(0, 0, 0, 0, 0, 0, -7, -1);
        
        p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().printPeriod(p));
    }

    public void testFormatMillis_1_oe() {
        PeriodFormatter f = builder.appendMillis().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatMillis_2_oe() {
        PeriodFormatter f = builder.appendMillis().toFormatter();
// incorrect assertion         assertEquals("MM/dd/yyyy HH:mm:ss.SSS", f.getPrinter().getPattern());
    }

    public void testFormatMillis_3_oe() {
        PeriodFormatter f = builder.appendMillis().toFormatter();
// incorrect assertion         assertEquals("MM/dd/yyyy HH:mm:ss.SSS", f.getPrinter().getPattern());
    }

    public void testFormatMillis_4_oe() {
        PeriodFormatter f = builder.appendMillis().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("", f.print(p));
    }

    public void testFormatMillis_5_oe() {
        PeriodFormatter f = builder.appendMillis().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().getLocalizedString());
    }

    public void testFormatMillis_6_oe() {
        PeriodFormatter f = builder.appendMillis().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().getLocalizedString());
    }

    public void testFormatMillis3Digit_1_oe() {
        PeriodFormatter f = builder.appendMillis3Digit().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatMillis3Digit_2_oe() {
        PeriodFormatter f = builder.appendMillis3Digit().toFormatter();
// incorrect assertion         assertEquals("MMM d, yyyy HH:mm:ss.SSS", f.getPrinter().getPattern());
    }

    public void testFormatMillis3Digit_3_oe() {
        PeriodFormatter f = builder.appendMillis3Digit().toFormatter();
// incorrect assertion         assertEquals("MMM d, yyyy HH:mm:ss.SSS", f.getPrinter().getPattern());
    }

    public void testFormatMillis3Digit_4_oe() {
        PeriodFormatter f = builder.appendMillis3Digit().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("", f.print(p));
    }

    public void testFormatMillis3Digit_5_oe() {
        PeriodFormatter f = builder.appendMillis3Digit().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().getLocalizedString());
    }

    public void testFormatMillis3Digit_6_oe() {
        PeriodFormatter f = builder.appendMillis3Digit().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("00:00:00", f.getPrinter().getLocalizedString());
    }

    public void testFormatPrefixSimple1_1_oe() {
        PeriodFormatter f = builder.appendPrefix("Years:").appendYears().toFormatter();
        assertEquals("Years:0", f.print(new Period()));
    }

    public void testFormatPrefixSimple1_2_oe() {
        PeriodFormatter f = builder.appendPrefix("Years:").appendYears().toFormatter();
// incorrect assertion         assertEquals("Years:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixSimple1_3_oe() {
        PeriodFormatter f = builder.appendPrefix("Years:").appendYears().toFormatter();
// incorrect assertion         assertEquals("Years:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixSimple1_4_oe() {
        PeriodFormatter f = builder.appendPrefix("Years:").appendYears().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Years:0", f.print(p));
    }

    public void testFormatPrefixSimple1_5_oe() {
        PeriodFormatter f = builder.appendPrefix("Years:").appendYears().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Years:0", f.getPrinter().toString());
    }

    public void testFormatPrefixSimple1_6_oe() {
        PeriodFormatter f = builder.appendPrefix("Years:").appendYears().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("Years:0", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixSimple2_1_oe() {
        PeriodFormatter f = builder.appendPrefix("Hours:").appendHours().toFormatter();
        assertEquals("Hours:0", f.print(new Period()));
    }

    public void testFormatPrefixSimple2_2_oe() {
        PeriodFormatter f = builder.appendPrefix("Hours:").appendHours().toFormatter();
// incorrect assertion         assertEquals("Hours:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixSimple2_3_oe() {
        PeriodFormatter f = builder.appendPrefix("Hours:").appendHours().toFormatter();
// incorrect assertion         assertEquals("Hours:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixSimple2_4_oe() {
        PeriodFormatter f = builder.appendPrefix("Hours:").appendHours().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Hours:0", f.print(p));
    }

    public void testFormatPrefixSimple2_5_oe() {
        PeriodFormatter f = builder.appendPrefix("Hours:").appendHours().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("Hours:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixSimple2_6_oe() {
        PeriodFormatter f = builder.appendPrefix("Hours:").appendHours().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("Hours:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixSimple4IgnoringPrefix_1_oe() {
        PeriodFormatter f = builder.appendPrefix("m").appendMinutes()
                .appendSeparator(" ").appendPrefix("ms").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertNotNull(oneMS);
    }

    public void testFormatPrefixSimple4IgnoringPrefix_2_oe() {
        PeriodFormatter f = builder.appendPrefix("m").appendMinutes()
                .appendSeparator(" ").appendPrefix("ms").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        assertNotNull(period);
    }

    public void testFormatPrefixSimple4IgnoringPrefix_3_oe() {
        PeriodFormatter f = builder.appendPrefix("m").appendMinutes()
                .appendSeparator(" ").appendPrefix("ms").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testPluralAffixParseOrder_1_oe() {
        PeriodFormatter f = builder.appendDays().appendSuffix("day", "days").toFormatter();
        String twoDays = Period.days(2).toString(f);
        Period period = f.parsePeriod(twoDays);
        assertNotNull(period);
    }

    public void testPluralAffixParseOrder_2_oe() {
        PeriodFormatter f = builder.appendDays().appendSuffix("day", "days").toFormatter();
        String twoDays = Period.days(2).toString(f);
        Period period = f.parsePeriod(twoDays);
        period = f.parsePeriod(twoDays.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatPrefixPlural1_1_oe() {
        PeriodFormatter f = builder.appendPrefix("Year:", "Years:").appendYears().toFormatter();
// incorrect assertion         assertEquals("Year:2013", f.print(new Period(2013, 0, 0, 0, 0, 0, 0)));
    }

    public void testFormatPrefixPlural1_2_oe() {
        PeriodFormatter f = builder.appendPrefix("Year:", "Years:").appendYears().toFormatter();
// incorrect assertion         assertEquals("Year:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixPlural1_3_oe() {
        PeriodFormatter f = builder.appendPrefix("Year:", "Years:").appendYears().toFormatter();
// incorrect assertion         assertEquals("Year:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixPlural1_4_oe() {
        PeriodFormatter f = builder.appendPrefix("Year:", "Years:").appendYears().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Year:0Years:0", f.print(p));
    }

    public void testFormatPrefixPlural1_5_oe() {
        PeriodFormatter f = builder.appendPrefix("Year:", "Years:").appendYears().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("Year:0Years:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixPlural1_6_oe() {
        PeriodFormatter f = builder.appendPrefix("Year:", "Years:").appendYears().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Year:0Years:", f.getPrinter().toString());
    }

    public void testFormatPrefixPlural2_1_oe() {
        PeriodFormatter f = builder.appendPrefix("Hour:", "Hours:").appendHours().toFormatter();
        assertEquals("Hour:0", f.print(new Period()));
    }

    public void testFormatPrefixPlural2_2_oe() {
        PeriodFormatter f = builder.appendPrefix("Hour:", "Hours:").appendHours().toFormatter();
// incorrect assertion         assertEquals("Hour:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixPlural2_3_oe() {
        PeriodFormatter f = builder.appendPrefix("Hour:", "Hours:").appendHours().toFormatter();
// incorrect assertion         assertEquals("Hour:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixPlural2_4_oe() {
        PeriodFormatter f = builder.appendPrefix("Hour:", "Hours:").appendHours().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Hour:0", f.print(p));
    }

    public void testFormatPrefixPlural2_5_oe() {
        PeriodFormatter f = builder.appendPrefix("Hour:", "Hours:").appendHours().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Hour:0Hours:", f.getPrinter().toString());
    }

    public void testFormatPrefixPlural2_6_oe() {
        PeriodFormatter f = builder.appendPrefix("Hour:", "Hours:").appendHours().toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Hour:0Hours:", f.getPrinter().toString());
    }

    public void testFormatPrefixPlural4IgnoringPrefix_1_oe() {
        PeriodFormatter f = builder.appendPrefix("m", "ms").appendMinutes()
                .appendSeparator(" ").appendPrefix("mss", "msss").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertNotNull(oneMS);
    }

    public void testFormatPrefixPlural4IgnoringPrefix_2_oe() {
        PeriodFormatter f = builder.appendPrefix("m", "ms").appendMinutes()
                .appendSeparator(" ").appendPrefix("mss", "msss").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        assertNotNull(period);
    }

    public void testFormatPrefixPlural4IgnoringPrefix_3_oe() {
        PeriodFormatter f = builder.appendPrefix("m", "ms").appendMinutes()
                .appendSeparator(" ").appendPrefix("mss", "msss").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatPrefixPlural4IgnoringPrefix_4_oe() {
        PeriodFormatter f = builder.appendPrefix("m", "ms").appendMinutes()
                .appendSeparator(" ").appendPrefix("mss", "msss").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        assertNotNull(twoMS);
    }

    public void testFormatPrefixPlural4IgnoringPrefix_5_oe() {
        PeriodFormatter f = builder.appendPrefix("m", "ms").appendMinutes()
                .appendSeparator(" ").appendPrefix("mss", "msss").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        Period period2 = f.parsePeriod(twoMS);
        assertNotNull(period2);
    }

    public void testFormatPrefixPlural4IgnoringPrefix_6_oe() {
        PeriodFormatter f = builder.appendPrefix("m", "ms").appendMinutes()
                .appendSeparator(" ").appendPrefix("mss", "msss").appendMillis().toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        Period period2 = f.parsePeriod(twoMS);
        period = f.parsePeriod(twoMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period2);
    }

    public void testRegExAffixParseOrder_1_oe() {
        PeriodFormatter f = builder.appendDays()
                .appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"day", "days"}).toFormatter();
        String twoDays = Period.days(2).toString(f);
        Period period = f.parsePeriod(twoDays);
        assertNotNull(period);
    }

    public void testRegExAffixParseOrder_2_oe() {
        PeriodFormatter f = builder.appendDays()
                .appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"day", "days"}).toFormatter();
        String twoDays = Period.days(2).toString(f);
        Period period = f.parsePeriod(twoDays);
        period = f.parsePeriod(twoDays.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatPrefixRegEx1_1_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Year:", "Years:" }).appendYears()
                .toFormatter();
// incorrect assertion         assertEquals("Years:1", f.print(new Period(1, 0, 0, 0, 0, 0, 0)));
    }

    public void testFormatPrefixRegEx1_2_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Year:", "Years:" }).appendYears()
                .toFormatter();
// incorrect assertion         assertEquals("Years:1\u00A0", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixRegEx1_3_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Year:", "Years:" }).appendYears()
                .toFormatter();
// incorrect assertion         assertEquals("Years:1\u00A0", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixRegEx1_4_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Year:", "Years:" }).appendYears()
                .toFormatter();

        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Year:0Years:0", f.print(p));
    }

    public void testFormatPrefixRegEx1_5_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Year:", "Years:" }).appendYears()
                .toFormatter();

        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Years:0", f.getPrinter().toString());
    }

    public void testFormatPrefixRegEx1_6_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Year:", "Years:" }).appendYears()
                .toFormatter();

        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Years:0", f.getPrinter().toString());
    }

    public void testFormatPrefixRegEx2_1_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Hour:", "Hours:" }).appendHours()
                .toFormatter();
// incorrect assertion         assertEquals("Hour:1", f.print(new Period(0, 1, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrefixRegEx2_2_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Hour:", "Hours:" }).appendHours()
                .toFormatter();
// incorrect assertion         assertEquals("Hour:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixRegEx2_3_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Hour:", "Hours:" }).appendHours()
                .toFormatter();
// incorrect assertion         assertEquals("Hour:", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixRegEx2_4_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Hour:", "Hours:" }).appendHours()
                .toFormatter();

        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Hour:0Hours:0", f.print(p));
    }

    public void testFormatPrefixRegEx2_5_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Hour:", "Hours:" }).appendHours()
                .toFormatter();

        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Hour:0Hours:", f.getPrinter().toString());
    }

    public void testFormatPrefixRegEx2_6_oe() {
        PeriodFormatter f = builder.appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "Hour:", "Hours:" }).appendHours()
                .toFormatter();

        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Hour:0Hours:", f.getPrinter().toString());
    }

    public void testFormatPrefixRegEx4IgnoringPrefix_1_oe() {
        PeriodFormatter f = builder
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"}).appendMinutes()
                .appendSeparator(" ")
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"}).appendMillis()
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertNotNull(oneMS);
    }

    public void testFormatPrefixRegEx4IgnoringPrefix_2_oe() {
        PeriodFormatter f = builder
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"}).appendMinutes()
                .appendSeparator(" ")
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"}).appendMillis()
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        assertNotNull(period);
    }

    public void testFormatPrefixRegEx4IgnoringPrefix_3_oe() {
        PeriodFormatter f = builder
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"}).appendMinutes()
                .appendSeparator(" ")
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"}).appendMillis()
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatPrefixRegEx4IgnoringPrefix_4_oe() {
        PeriodFormatter f = builder
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"}).appendMinutes()
                .appendSeparator(" ")
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"}).appendMillis()
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        assertNotNull(twoMS);
    }

    public void testFormatPrefixRegEx4IgnoringPrefix_5_oe() {
        PeriodFormatter f = builder
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"}).appendMinutes()
                .appendSeparator(" ")
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"}).appendMillis()
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        Period period2 = f.parsePeriod(twoMS);
        assertNotNull(period2);
    }

    public void testFormatPrefixRegEx4IgnoringPrefix_6_oe() {
        PeriodFormatter f = builder
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"}).appendMinutes()
                .appendSeparator(" ")
                .appendPrefix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"}).appendMillis()
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        Period period2 = f.parsePeriod(twoMS);
        period = f.parsePeriod(twoMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period2);
    }

    public void testFormatPrefixComposite1_1_oe() {
        PeriodFormatter f = builder.appendPrefix("d")
                .appendPrefix("a", "ay")
                .appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "y:", "s:" })
                .appendDays().toFormatter();
        String oneMS = Period.days(2).toString(f);
        assertNotNull(oneMS);
    }

    public void testFormatPrefixComposite1_2_oe() {
        PeriodFormatter f = builder.appendPrefix("d")
                .appendPrefix("a", "ay")
                .appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "y:", "s:" })
                .appendDays().toFormatter();
        String oneMS = Period.days(2).toString(f);
        Period period = f.parsePeriod(oneMS);
        assertNotNull(period);
    }

    public void testFormatPrefixComposite1_3_oe() {
        PeriodFormatter f = builder.appendPrefix("d")
                .appendPrefix("a", "ay")
                .appendPrefix(new String[] { "^1$", "^.*$" }, new String[] { "y:", "s:" })
                .appendDays().toFormatter();
        String oneMS = Period.days(2).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatSuffixSimple1_1_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" years").toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatSuffixSimple1_2_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" years").toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSuffixSimple1_3_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" years").toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSuffixSimple1_4_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" years").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 years", f.print(p));
    }

    public void testFormatSuffixSimple1_5_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" years").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0 years", f.getPrinter().getLocalizedString());
    }

    public void testFormatSuffixSimple1_6_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" years").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0 years", f.getPrinter().getLocalizedString());
    }

    public void testFormatSuffixSimple2_1_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hours").toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatSuffixSimple2_2_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hours").toFormatter();
// incorrect assertion         assertEquals("hours", f.getSuffix());
    }

    public void testFormatSuffixSimple2_3_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hours").toFormatter();
// incorrect assertion         assertEquals("hours", f.getSuffix());
    }

    public void testFormatSuffixSimple2_4_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hours").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 hours", f.print(p));
    }

    public void testFormatSuffixSimple2_5_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hours").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0 hours", f.getPrinter().printPeriod(p));
    }

    public void testFormatSuffixSimple2_6_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hours").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0 hours", f.getPrinter().printPeriod(p));
    }

    public void testFormatPrefixSimple5IgnoringPrefix_1_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m")
                .appendSeparator(" ").appendMillis().appendSuffix("ms").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertNotNull(oneMS);
    }

    public void testFormatPrefixSimple5IgnoringPrefix_2_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m")
                .appendSeparator(" ").appendMillis().appendSuffix("ms").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        assertNotNull(period);
    }

    public void testFormatPrefixSimple5IgnoringPrefix_3_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m")
                .appendSeparator(" ").appendMillis().appendSuffix("ms").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatPrefixSimple6IgnoringPrefix_1_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("M")
                .appendSeparator(" ").appendMillis().appendSuffix("ms").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertNotNull(oneMS);
    }

    public void testFormatPrefixSimple6IgnoringPrefix_2_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("M")
                .appendSeparator(" ").appendMillis().appendSuffix("ms").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        assertNotNull(period);
    }

    public void testFormatPrefixSimple6IgnoringPrefix_3_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("M")
                .appendSeparator(" ").appendMillis().appendSuffix("ms").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatSuffixPlural1_1_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" year", " years").toFormatter();
// incorrect assertion         assertEquals("1 year", f.print(new Period(1, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatSuffixPlural1_2_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" year", " years").toFormatter();
// incorrect assertion         assertEquals("years", f.getSuffix());
    }

    public void testFormatSuffixPlural1_3_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" year", " years").toFormatter();
// incorrect assertion         assertEquals("years", f.getSuffix());
    }

    public void testFormatSuffixPlural1_4_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" year", " years").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 years", f.print(p));
    }

    public void testFormatSuffixPlural1_5_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" year", " years").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0 years", f.getPrinter().print(p));
    }

    public void testFormatSuffixPlural1_6_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(" year", " years").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0 years", f.getPrinter().printPeriod(p));
    }

    public void testFormatSuffixPlural2_1_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hour", " hours").toFormatter();
        assertNotNull(f.print(new Period()));
    }

    public void testFormatSuffixPlural2_2_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hour", " hours").toFormatter();
// incorrect assertion         assertEquals("hours", f.getSuffix());
    }

    public void testFormatSuffixPlural2_3_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hour", " hours").toFormatter();
// incorrect assertion         assertEquals("hours", f.getSuffix());
    }

    public void testFormatSuffixPlural2_4_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hour", " hours").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 hours", f.print(p));
    }

    public void testFormatSuffixPlural2_5_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hour", " hours").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0 hours", f.getPrinter().printPeriod(p));
    }

    public void testFormatSuffixPlural2_6_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(" hour", " hours").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("0 hours", f.getPrinter().printPeriod(p));
    }

    public void testFormatSuffixPlural4IgnoringPrefix_1_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m", "ms")
                .appendSeparator(" ").appendMillis().appendSuffix("mss", "msss").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertNotNull(oneMS);
    }

    public void testFormatSuffixPlural4IgnoringPrefix_2_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m", "ms")
                .appendSeparator(" ").appendMillis().appendSuffix("mss", "msss").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        assertNotNull(period);
    }

    public void testFormatSuffixPlural4IgnoringPrefix_3_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m", "ms")
                .appendSeparator(" ").appendMillis().appendSuffix("mss", "msss").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatSuffixPlural4IgnoringPrefix_4_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m", "ms")
                .appendSeparator(" ").appendMillis().appendSuffix("mss", "msss").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        assertNotNull(twoMS);
    }

    public void testFormatSuffixPlural4IgnoringPrefix_5_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m", "ms")
                .appendSeparator(" ").appendMillis().appendSuffix("mss", "msss").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        Period period2 = f.parsePeriod(twoMS);
        assertNotNull(period2);
    }

    public void testFormatSuffixPlural4IgnoringPrefix_6_oe() {
        PeriodFormatter f = builder.appendMinutes().appendSuffix("m", "ms")
                .appendSeparator(" ").appendMillis().appendSuffix("mss", "msss").toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        Period period2 = f.parsePeriod(twoMS);
        period = f.parsePeriod(twoMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period2);
    }

    public void testFormatSuffixRegEx1_1_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " year", " years" })
                .toFormatter();
        assertNotNull(f.print(new Period()));
    }

    public void testFormatSuffixRegEx1_2_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " year", " years" })
                .toFormatter();
// incorrect assertion         assertEquals("^1$ year^2$ years", f.getPrinter().getPattern());
    }

    public void testFormatSuffixRegEx1_3_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " year", " years" })
                .toFormatter();
        assertEquals("^1$ year^2$ years", f.getPrinter().toString());
    }

    public void testFormatSuffixRegEx1_4_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " year", " years" })
                .toFormatter();
        
        Period p2 = new Period(2, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("2 years", f.print(p2));
    }

    public void testFormatSuffixRegEx1_5_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " year", " years" })
                .toFormatter();
        
        Period p2 = new Period(2, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("2 years", f.getPrinter().print(p2));
    }

    public void testFormatSuffixRegEx1_6_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " year", " years" })
                .toFormatter();
        
        Period p2 = new Period(2, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("2 years", f.getPrinter().print(p2));
    }

    public void testFormatSuffixRegEx1_7_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " year", " years" })
                .toFormatter();
        
        Period p2 = new Period(2, 0, 0, 0, 0, 0, 0, 0);
        
        Period p0 = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("2 years", f.print(p2));
    }

    public void testFormatSuffixRegEx1_8_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " year", " years" })
                .toFormatter();
        
        Period p2 = new Period(2, 0, 0, 0, 0, 0, 0, 0);
        
        Period p0 = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("2 years", f.getPrinter().print(p2));
    }

    public void testFormatSuffixRegEx1_9_oe() {
        PeriodFormatter f = builder.appendYears().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " year", " years" })
                .toFormatter();
        
        Period p2 = new Period(2, 0, 0, 0, 0, 0, 0, 0);
        
        Period p0 = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("2 years", f.getPrinter().print(p2));
    }

    public void testFormatSuffixRegEx2_1_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " hour", " hours" }).toFormatter();
        assertNotNull(f.print(new Period()));
    }

    public void testFormatSuffixRegEx2_2_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " hour", " hours" }).toFormatter();
// incorrect assertion         assertEquals("^1$ hour^2$", f.getPrinter().getPattern());
    }

    public void testFormatSuffixRegEx2_3_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " hour", " hours" }).toFormatter();
// incorrect assertion         assertEquals("^1$ hour^2$", f.getPrinter().getPattern());
    }

    public void testFormatSuffixRegEx2_4_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " hour", " hours" }).toFormatter();

        Period p2 = new Period(0, 0, 0, 0, 2, 0, 0, 0);
        assertEquals("2 hours", f.print(p2));
    }

    public void testFormatSuffixRegEx2_5_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " hour", " hours" }).toFormatter();

        Period p2 = new Period(0, 0, 0, 0, 2, 0, 0, 0);
// incorrect assertion         assertEquals("2 hours", f.getPrinter().print(p2));
    }

    public void testFormatSuffixRegEx2_6_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " hour", " hours" }).toFormatter();

        Period p2 = new Period(0, 0, 0, 0, 2, 0, 0, 0);
// incorrect assertion         assertEquals("2 hours", f.getPrinter().print(p2));
    }

    public void testFormatSuffixRegEx2_7_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " hour", " hours" }).toFormatter();

        Period p2 = new Period(0, 0, 0, 0, 2, 0, 0, 0);

        Period p0 = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("2 hours", f.print(p2));
    }

    public void testFormatSuffixRegEx2_8_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " hour", " hours" }).toFormatter();

        Period p2 = new Period(0, 0, 0, 0, 2, 0, 0, 0);

        Period p0 = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("2 hours", f.getPrinter().print(p2));
    }

    public void testFormatSuffixRegEx2_9_oe() {
        PeriodFormatter f = builder.appendHours().appendSuffix(new String[] { "^1$", "^2$" }, new String[] { " hour", " hours" }).toFormatter();

        Period p2 = new Period(0, 0, 0, 0, 2, 0, 0, 0);

        Period p0 = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("2 hours", f.getPrinter().print(p2));
    }

    public void testFormatSuffixRegEx5IgnoringAffix_1_oe() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"})
                .appendSeparator(" ")
                .appendMillis().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"})
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertNotNull(oneMS);
    }

    public void testFormatSuffixRegEx5IgnoringAffix_2_oe() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"})
                .appendSeparator(" ")
                .appendMillis().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"})
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        assertNotNull(period);
    }

    public void testFormatSuffixRegEx5IgnoringAffix_3_oe() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"})
                .appendSeparator(" ")
                .appendMillis().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"})
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatSuffixRegEx5IgnoringAffix_4_oe() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"})
                .appendSeparator(" ")
                .appendMillis().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"})
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        assertNotNull(twoMS);
    }

    public void testFormatSuffixRegEx5IgnoringAffix_5_oe() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"})
                .appendSeparator(" ")
                .appendMillis().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"})
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        Period period2 = f.parsePeriod(twoMS);
        assertNotNull(period2);
    }

    public void testFormatSuffixRegEx5IgnoringAffix_6_oe() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"m", "ms"})
                .appendSeparator(" ")
                .appendMillis().appendSuffix(new String[]{"^1$","[0-9]*"}, new String[]{"mss", "msss"})
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        
        String twoMS = Period.millis(2).toString(f);
        Period period2 = f.parsePeriod(twoMS);
        period = f.parsePeriod(twoMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period2);
    }

    public void testFormatSuffixComposite1_1_oe() {
        PeriodFormatter f = builder.appendDays().appendSuffix("d")
                .appendSuffix("a", "ay")
                .appendSuffix(new String[] { "^1$", "^.*$" }, new String[] { "y", "s" })
                .toFormatter();
        String oneMS = Period.days(2).toString(f);
        assertNotNull(oneMS);
    }

    public void testFormatSuffixComposite1_2_oe() {
        PeriodFormatter f = builder.appendDays().appendSuffix("d")
                .appendSuffix("a", "ay")
                .appendSuffix(new String[] { "^1$", "^.*$" }, new String[] { "y", "s" })
                .toFormatter();
        String oneMS = Period.days(2).toString(f);
        Period period = f.parsePeriod(oneMS);
        assertEquals("2a", oneMS);
    }

    public void testFormatSuffixComposite1_3_oe() {
        PeriodFormatter f = builder.appendDays().appendSuffix("d")
                .appendSuffix("a", "ay")
                .appendSuffix(new String[] { "^1$", "^.*$" }, new String[] { "y", "s" })
                .toFormatter();
        String oneMS = Period.days(2).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatSuffixComposite5IgnoringAffix_1_oe() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix("m")
                .appendSeparator(" ")
                .appendMillis().appendSuffix("m").appendSuffix("s")
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        assertNotNull(oneMS);
    }

    public void testFormatSuffixComposite5IgnoringAffix_2_oe() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix("m")
                .appendSeparator(" ")
                .appendMillis().appendSuffix("m").appendSuffix("s")
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        assertNotNull(period);
    }

    public void testFormatSuffixComposite5IgnoringAffix_3_oe() {
        PeriodFormatter f = builder
                .appendMinutes().appendSuffix("m")
                .appendSeparator(" ")
                .appendMillis().appendSuffix("m").appendSuffix("s")
                .toFormatter();
        String oneMS = Period.millis(1).toString(f);
        Period period = f.parsePeriod(oneMS);
        period = f.parsePeriod(oneMS.toUpperCase(Locale.ENGLISH));
        assertNotNull(period);
    }

    public void testFormatPrefixSuffix_1_oe() {
        PeriodFormatter f = builder.appendPrefix("P").appendYears().appendSuffix("Y").toFormatter();
// incorrect assertion         assertEquals("P1Y", f.print(new Period(1, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrefixSuffix_2_oe() {
        PeriodFormatter f = builder.appendPrefix("P").appendYears().appendSuffix("Y").toFormatter();
// incorrect assertion         assertEquals("P", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixSuffix_3_oe() {
        PeriodFormatter f = builder.appendPrefix("P").appendYears().appendSuffix("Y").toFormatter();
// incorrect assertion         assertEquals("P", f.getPrinter().getPrefix());
    }

    public void testFormatPrefixSuffix_4_oe() {
        PeriodFormatter f = builder.appendPrefix("P").appendYears().appendSuffix("Y").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("P0Y", f.print(p));
    }

    public void testFormatPrefixSuffix_5_oe() {
        PeriodFormatter f = builder.appendPrefix("P").appendYears().appendSuffix("Y").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("P0Y", f.getPrinter().getLocalizedString());
    }

    public void testFormatPrefixSuffix_6_oe() {
        PeriodFormatter f = builder.appendPrefix("P").appendYears().appendSuffix("Y").toFormatter();
        
        Period p = new Period(0, 0, 0, 0, 0, 0, 0, 0);
// incorrect assertion         assertEquals("P0Y", f.getPrinter().getLocalizedString());
    }

    public void testFormatSeparatorSimple_1_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparator("T").appendHours().toFormatter();
        assertNotNull(f.print(new Period()));
    }

    public void testFormatSeparatorSimple_2_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparator("T").appendHours().toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorSimple_3_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparator("T").appendHours().toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorSimple_4_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparator("T").appendHours().toFormatter();
        
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatSeparatorSimple_5_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparator("T").appendHours().toFormatter();
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorSimple_6_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparator("T").appendHours().toFormatter();
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorSimple_7_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparator("T").appendHours().toFormatter();
        
        
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatSeparatorSimple_8_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparator("T").appendHours().toFormatter();
        
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorSimple_9_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparator("T").appendHours().toFormatter();
        
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorComplex_2_oe() {
        PeriodFormatter f = builder
            .appendYears().appendSeparator(", ", " and ")
            .appendHours().appendSeparator(", ", " and ")
            .appendMinutes().appendSeparator(", ", " and ")
            .toFormatter();
// incorrect assertion         assertEquals("and ", f.getPrinter().getSeparator());
    }

    public void testFormatSeparatorComplex_3_oe() {
        PeriodFormatter f = builder
            .appendYears().appendSeparator(", ", " and ")
            .appendHours().appendSeparator(", ", " and ")
            .appendMinutes().appendSeparator(", ", " and ")
            .toFormatter();
// incorrect assertion         assertEquals("and ", f.getPrinter().getSeparator());
    }

    public void testFormatSeparatorComplex_4_oe() {
        PeriodFormatter f = builder
            .appendYears().appendSeparator(", ", " and ")
            .appendHours().appendSeparator(", ", " and ")
            .appendMinutes().appendSeparator(", ", " and ")
            .toFormatter();
        
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatSeparatorComplex_5_oe() {
        PeriodFormatter f = builder
            .appendYears().appendSeparator(", ", " and ")
            .appendHours().appendSeparator(", ", " and ")
            .appendMinutes().appendSeparator(", ", " and ")
            .toFormatter();
        
// incorrect assertion         assertEquals("and ", f.getPrinter().getSeparator());
    }

    public void testFormatSeparatorComplex_6_oe() {
        PeriodFormatter f = builder
            .appendYears().appendSeparator(", ", " and ")
            .appendHours().appendSeparator(", ", " and ")
            .appendMinutes().appendSeparator(", ", " and ")
            .toFormatter();
        
// incorrect assertion         assertEquals("and ", f.getPrinter().getSeparator());
    }

    public void testFormatSeparatorComplex_7_oe() {
        PeriodFormatter f = builder
            .appendYears().appendSeparator(", ", " and ")
            .appendHours().appendSeparator(", ", " and ")
            .appendMinutes().appendSeparator(", ", " and ")
            .toFormatter();
        
        
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatSeparatorComplex_8_oe() {
        PeriodFormatter f = builder
            .appendYears().appendSeparator(", ", " and ")
            .appendHours().appendSeparator(", ", " and ")
            .appendMinutes().appendSeparator(", ", " and ")
            .toFormatter();
        
        
// incorrect assertion         assertEquals("and ", f.getPrinter().getSeparator());
    }

    public void testFormatSeparatorComplex_9_oe() {
        PeriodFormatter f = builder
            .appendYears().appendSeparator(", ", " and ")
            .appendHours().appendSeparator(", ", " and ")
            .appendMinutes().appendSeparator(", ", " and ")
            .toFormatter();
        
        
// incorrect assertion         assertEquals("and ", f.getPrinter().getSeparator());
    }

    public void testFormatSeparatorIfFieldsAfter_1_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsAfter("T").appendHours().toFormatter();
// incorrect assertion         assertEquals("2013-07-01T00:00:00/2013-07-01T00:00:00", f.print(p));
    }

    public void testFormatSeparatorIfFieldsAfter_2_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsAfter("T").appendHours().toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsAfter_3_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsAfter("T").appendHours().toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsAfter_4_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsAfter("T").appendHours().toFormatter();
        
// incorrect assertion         assertEquals("2013-07-01T00:00:00/2013-07-01T00:00:00", f.print(p));
    }

    public void testFormatSeparatorIfFieldsAfter_5_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsAfter("T").appendHours().toFormatter();
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsAfter_6_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsAfter("T").appendHours().toFormatter();
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsAfter_7_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsAfter("T").appendHours().toFormatter();
        
        
// incorrect assertion         assertEquals("2013-07-01T00:00:00/2013-07-01T00:00:00", f.print(p));
    }

    public void testFormatSeparatorIfFieldsAfter_8_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsAfter("T").appendHours().toFormatter();
        
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsAfter_9_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsAfter("T").appendHours().toFormatter();
        
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsBefore_1_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsBefore("T").appendHours().toFormatter();
// incorrect assertion         assertEquals("2013-07-01T00:00:00/2013-07-01T00:00:00", f.print(p));
    }

    public void testFormatSeparatorIfFieldsBefore_2_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsBefore("T").appendHours().toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsBefore_3_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsBefore("T").appendHours().toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsBefore_4_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsBefore("T").appendHours().toFormatter();
        
// incorrect assertion         assertEquals("2013-07-01T00:00:00/2013-07-01T00:00:00", f.print(p));
    }

    public void testFormatSeparatorIfFieldsBefore_5_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsBefore("T").appendHours().toFormatter();
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsBefore_6_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsBefore("T").appendHours().toFormatter();
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsBefore_7_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsBefore("T").appendHours().toFormatter();
        
        
// incorrect assertion         assertEquals("2013-07-01T00:00:00/2013-07-01T00:00:00", f.print(p));
    }

    public void testFormatSeparatorIfFieldsBefore_8_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsBefore("T").appendHours().toFormatter();
        
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatSeparatorIfFieldsBefore_9_oe() {
        PeriodFormatter f = builder.appendYears().appendSeparatorIfFieldsBefore("T").appendHours().toFormatter();
        
        
// incorrect assertion         assertEquals("yyyy-MM-dd'T'HH:mm:ss", f.getPrinter().getPattern());
    }

    public void testFormatLiteral_1_oe() {
        PeriodFormatter f = builder.appendLiteral("HELLO").toFormatter();
        assertEquals("HELLO", f.print(new Period()));
    }

    public void testFormatLiteral_2_oe() {
        PeriodFormatter f = builder.appendLiteral("HELLO").toFormatter();
// incorrect assertion         assertEquals("HELLO", f.getPrinter().getPattern());
    }

    public void testFormatLiteral_3_oe() {
        PeriodFormatter f = builder.appendLiteral("HELLO").toFormatter();
// incorrect assertion         assertEquals("HELLO", f.getPrinter().getPattern());
    }

    public void testFormatAppendFormatter_1_oe() {
        PeriodFormatter base = builder.appendYears().appendLiteral("-").toFormatter();
        PeriodFormatter f = new PeriodFormatterBuilder().append(base).appendYears().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatAppendFormatter_2_oe() {
        PeriodFormatter base = builder.appendYears().appendLiteral("-").toFormatter();
        PeriodFormatter f = new PeriodFormatterBuilder().append(base).appendYears().toFormatter();
        assertNotNull(f.getPrinter());
    }

    public void testFormatAppendFormatter_3_oe() {
        PeriodFormatter base = builder.appendYears().appendLiteral("-").toFormatter();
        PeriodFormatter f = new PeriodFormatterBuilder().append(base).appendYears().toFormatter();
        assertNotNull(f.getPrinter());
    }

    public void testFormatMinDigits_1_oe() {
        PeriodFormatter f = new PeriodFormatterBuilder().minimumPrintedDigits(4).appendYears().toFormatter();
        assertEquals("0001-01-01/0001-01-01", f.print(new Period()));
    }

    public void testFormatMinDigits_2_oe() {
        PeriodFormatter f = new PeriodFormatterBuilder().minimumPrintedDigits(4).appendYears().toFormatter();
// incorrect assertion         assertEquals("2013-07-01/2013-07-01", f.getPrinter().print(new Period(new LocalDate(2013, 7, 1), new LocalDate(2013, 7, 1)), false));
    }

    public void testFormatMinDigits_3_oe() {
        PeriodFormatter f = new PeriodFormatterBuilder().minimumPrintedDigits(4).appendYears().toFormatter();
// incorrect assertion         assertEquals("2013-07-01/2013-07-01", f.getPrinter().printPeriod(new Period(new LocalDate(2013, 7, 1), new LocalDate(2013, 7, 1), false)));
    }

    public void testFormatPrintZeroDefault_1_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroDefault_2_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroDefault_3_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("yyyy-MM-ww-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroDefault_4_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroDefault_5_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("yyyy-MM-ww-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroDefault_6_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("yyyy-MM-ww-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroDefault_7_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroDefault_8_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("yyyy-MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroDefault_9_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("yyyy-MM-ww-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroDefault_10_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroDefault_11_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
// incorrect assertion         assertEquals("yyyy-MM-ww-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroDefault_12_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
// incorrect assertion         assertEquals("yyyy-MM-ww-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroDefault_13_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        
        f = new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendYears().toFormatter();
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatPrintZeroDefault_14_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        
        f = new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendYears().toFormatter();
        assertEquals("", f.getPrinter().toString());
    }

    public void testFormatPrintZeroDefault_15_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        
        f = new PeriodFormatterBuilder()
                .appendYears().appendLiteral("-")
                .appendYears().toFormatter();
        assertEquals("", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyLast_1_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroRarelyLast_2_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("00:00:00-00:00:00", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyLast_3_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("00:00:00-00:00:00", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyLast_4_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatPrintZeroRarelyLast_5_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        assertEquals("00:00:00-00:00:00", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyLast_6_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroRarelyLast_7_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertNotNull(f.print(period));
    }

    public void testFormatPrintZeroRarelyLast_8_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        assertEquals("00:00:00-00:00:00", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyLast_9_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroRarelyLast_10_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroRarelyLast_11_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        assertEquals("00:00:00-00:00:00", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyLast_12_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyLast()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        assertEquals("00:00:00-00:00:00", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyFirst_1_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroRarelyFirst_2_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("0y-0m-0w-0d", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyFirst_3_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("0y-0m-0w-0d", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyFirst_4_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroRarelyFirst_5_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("0Y-0M-0W-0D/", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroRarelyFirst_6_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        assertEquals("00:00:00", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyFirst_7_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroRarelyFirst_8_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        assertEquals("0y-0m-0w-0d", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyFirst_9_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        assertEquals("00:00:00", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyFirst_10_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroRarelyFirst_11_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        assertEquals("00:00:00", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyFirst_12_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroRarelyFirst()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        assertEquals("0y-0m-0w-0d", f.getPrinter().toString());
    }

    public void testFormatPrintZeroRarelyFirstYears_1_oe() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendYears().toFormatter();
        assertNotNull(f.print(new Period()));
    }

    public void testFormatPrintZeroRarelyFirstMonths_1_oe() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendMonths().toFormatter();
        assertNotNull(f.print(new Period()));
    }

    public void testFormatPrintZeroRarelyFirstWeeks_1_oe() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendWeeks().toFormatter();
// incorrect assertion         assertEquals("0w", f.print(new Period(0, 0, 0, 0, 0, 0, 0, 0, 0)));
    }

    public void testFormatPrintZeroRarelyFirstDays_1_oe() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendDays().toFormatter();
// incorrect assertion         assertNotNull(f.print());
    }

    public void testFormatPrintZeroRarelyFirstHours_1_oe() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendHours().toFormatter();
// incorrect assertion         assertNotNull(f.print(PeriodType.dayTime, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,;
    }

    public void testFormatPrintZeroRarelyFirstMinutes_1_oe() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendMinutes().toFormatter();
        assertNotNull(f.print(new Period()));
    }

    public void testFormatPrintZeroRarelyFirstSeconds_1_oe() {
        PeriodFormatter f = new PeriodFormatterBuilder()
            .printZeroRarelyFirst()
            .appendSeconds().toFormatter();
// incorrect assertion         assertNotNull(f.print(PeriodType.dayTime, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,;
    }

    public void testFormatPrintZeroIfSupported_1_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroIfSupported_2_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("0Y-0M-0W-0D/", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroIfSupported_3_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("0Y-0M-0W-0D/", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroIfSupported_4_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroIfSupported_5_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("0Y-0M-0W-0D/", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroIfSupported_6_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroIfSupported_7_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroIfSupported_8_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("0Y-0M-0W-0D/", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroIfSupported_9_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroIfSupported_10_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroIfSupported_11_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        assertEquals("PeriodFormatter['Y'-'M'-'W'-'D']", f.toString());
    }

    public void testFormatPrintZeroIfSupported_12_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        assertEquals("PeriodFormatter['Y'-'M'-'W'-'D']", f.toString());
    }

    public void testFormatPrintZeroAlways_1_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroAlways_2_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("0y-0m-0w-0d", f.getPrinter().toString());
    }

    public void testFormatPrintZeroAlways_3_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        assertEquals("0y-0m-0w-0d", f.getPrinter().toString());
    }

    public void testFormatPrintZeroAlways_4_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroAlways_5_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        assertEquals("0y-0m-0w-0d", f.getPrinter().toString());
    }

    public void testFormatPrintZeroAlways_6_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        assertEquals("0y-0m-0w-0d", f.getPrinter().toString());
    }

    public void testFormatPrintZeroAlways_7_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroAlways_8_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        assertEquals("0y-0m-0w-0d", f.getPrinter().toString());
    }

    public void testFormatPrintZeroAlways_9_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        assertEquals("0y-0m-0w-0d", f.getPrinter().toString());
    }

    public void testFormatPrintZeroAlways_10_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroAlways_11_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        assertEquals("", f.getPrinter().toString());
    }

    public void testFormatPrintZeroAlways_12_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
        assertEquals("", f.getPrinter().toString());
    }

    public void testFormatPrintZeroNever_1_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroNever_2_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroNever_3_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroNever_4_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroNever_5_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroNever_6_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroNever_7_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroNever_8_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroNever_9_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroNever_10_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
// incorrect assertion         assertEquals("0-0-0", f.print(new Period(0, 0, 0, 0, 0, 0, false)));
    }

    public void testFormatPrintZeroNever_11_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatPrintZeroNever_12_oe() {
        PeriodFormatter f =
            new PeriodFormatterBuilder()
                .printZeroNever()
                .appendYears().appendLiteral("-")
                .appendMonths().appendLiteral("-")
                .appendWeeks().appendLiteral("-")
                .appendDays().toFormatter();
        
        
        
// incorrect assertion         assertEquals("MM-dd", f.getPrinter().getPattern());
    }

    public void testFormatAppend_PrinterParser_Printer_null_1_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).appendMonths();
        assertNotNull(printer);
    }

    public void testFormatAppend_PrinterParser_Printer_null_2_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).appendMonths();
// incorrect assertion         assertNotNull(printer.toParser());
    }

    public void testFormatAppend_PrinterParser_Printer_null_3_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).appendMonths();
        
        PeriodFormatter f = bld.toFormatter();
// incorrect assertion         assertEquals("2013-07-01/2013-08-01", f.print(new Period(2013, 7, 1, 13, 0, 0, 0)));
    }

    public void testFormatAppend_PrinterParser_null_Parser_1_oe() {
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(null, parser).appendMonths();
// incorrect assertion         assertNotNull(parser.toPrinter());
    }

    public void testFormatAppend_PrinterParser_null_Parser_2_oe() {
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(null, parser).appendMonths();
        assertNotNull(parser);
    }

    public void testFormatAppend_PrinterParser_null_Parser_4_oe() {
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(null, parser).appendMonths();
        
        PeriodFormatter f = bld.toFormatter();
        try {
            f.print(PERIOD);
        } catch (UnsupportedOperationException ex) {}
// incorrect assertion         assertEquals(false, PERIOD.isNegative());
    }

    public void testFormatAppend_PrinterParser_PrinterParser_1_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, parser).appendMonths();
        assertNotNull(printer);
    }

    public void testFormatAppend_PrinterParser_PrinterParser_2_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, parser).appendMonths();
        assertNotNull(parser);
    }

    public void testFormatAppend_PrinterParser_PrinterParser_3_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, parser).appendMonths();
        
        PeriodFormatter f = bld.toFormatter();
        assertNotNull(f);
    }

    public void testFormatAppend_PrinterParser_PrinterParser_4_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, parser).appendMonths();
        
        PeriodFormatter f = bld.toFormatter();
        assertNotNull(f);
    }

    public void testFormatAppend_PrinterParser_Printer_null_null_Parser_1_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).append(null, parser);
        assertNotNull(printer);
    }

    public void testFormatAppend_PrinterParser_Printer_null_null_Parser_2_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).append(null, parser);
        assertNotNull(parser);
    }

    public void testFormatAppend_PrinterParserThenClear_1_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).append(null, parser);
        assertNotNull(printer);
    }

    public void testFormatAppend_PrinterParserThenClear_2_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).append(null, parser);
        assertNotNull(parser);
    }

    public void testFormatAppend_PrinterParserThenClear_3_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).append(null, parser);
        bld.clear();
        bld.appendMonths();
        assertEquals("MM", bld.toString());
    }

    public void testFormatAppend_PrinterParserThenClear_4_oe() {
        PeriodPrinter printer = new PeriodFormatterBuilder().appendYears().appendLiteral("-").toPrinter();
        PeriodParser parser = new PeriodFormatterBuilder().appendWeeks().appendLiteral("-").toParser();
        PeriodFormatterBuilder bld = new PeriodFormatterBuilder().append(printer, null).append(null, parser);
        bld.clear();
        bld.appendMonths();
        assertEquals("MM", bld.toString());
    }

    public void testMonthsAndMinutesAreConsideredSeparateAndCaseIsNotIgnored_1_oe() {
        PeriodFormatter formatter = builder
                .appendMonths().appendSuffix("M").appendSeparator(" ")
                .appendMinutes().appendSuffix("m").appendSeparator(" ")
                .toFormatter();

        String oneMonth = Period.months(1).toString(formatter);
        assertNotNull(oneMonth);
    }

    public void testMonthsAndMinutesAreConsideredSeparateAndCaseIsNotIgnored_2_oe() {
        PeriodFormatter formatter = builder
                .appendMonths().appendSuffix("M").appendSeparator(" ")
                .appendMinutes().appendSuffix("m").appendSeparator(" ")
                .toFormatter();

        String oneMonth = Period.months(1).toString(formatter);
        Period period = formatter.parsePeriod(oneMonth);
        assertNotNull(period);
    }

    public void testMonthsAndMinutesAreConsideredSeparateAndCaseIsNotIgnored_3_oe() {
        PeriodFormatter formatter = builder
                .appendMonths().appendSuffix("M").appendSeparator(" ")
                .appendMinutes().appendSuffix("m").appendSeparator(" ")
                .toFormatter();

        String oneMonth = Period.months(1).toString(formatter);
        Period period = formatter.parsePeriod(oneMonth);
        String oneMinute = Period.minutes(1).toString(formatter);
        assertNotNull(oneMinute);
    }

    public void testMonthsAndMinutesAreConsideredSeparateAndCaseIsNotIgnored_4_oe() {
        PeriodFormatter formatter = builder
                .appendMonths().appendSuffix("M").appendSeparator(" ")
                .appendMinutes().appendSuffix("m").appendSeparator(" ")
                .toFormatter();

        String oneMonth = Period.months(1).toString(formatter);
        Period period = formatter.parsePeriod(oneMonth);
        String oneMinute = Period.minutes(1).toString(formatter);
        period = formatter.parsePeriod(oneMinute);
        assertNotNull(period);
    }

}
