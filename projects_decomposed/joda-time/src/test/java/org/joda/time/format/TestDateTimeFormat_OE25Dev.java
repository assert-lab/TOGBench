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
package org.joda.time.format;

import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GJChronology;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.NameProvider;

/**
 * This class is a Junit unit test for DateTime Formating.
 *
 * @author Stephen Colebourne
 * @author Fredrik Borgh
 */
public class TestDateTimeFormat_OE25Dev extends TestCase {

    private static final DateTimeZone UTC = DateTimeZone.UTC;
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final DateTimeZone NEWYORK = DateTimeZone.forID("America/New_York");

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
        return new TestSuite(TestDateTimeFormat_OE25Dev.class);
    }

    public TestDateTimeFormat_OE25Dev(String name) {
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
        DateTimeFormat f = new DateTimeFormat() {
            // test constructor is protected
        };
        assertNotNull(f);
    }

    //-----------------------------------------------------------------------
    public void testFormat_era() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("G").withLocale(Locale.UK);
        assertEquals(dt.toString(),"AD",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"AD",f.print(dt));
        
        dt = dt.withZone(PARIS);
        assertEquals(dt.toString(),"AD",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_centuryOfEra() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("C").withLocale(Locale.UK);
        assertEquals(dt.toString(),"20",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"20",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"20",f.print(dt));
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"1",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_yearOfEra() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("Y").withLocale(Locale.UK);
        assertEquals(dt.toString(),"2004",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"2004",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"2004",f.print(dt));
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"124",f.print(dt));  // 124th year of BCE
    }        

    public void testFormat_yearOfEra_twoDigit() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("YY").withLocale(Locale.UK);
        assertEquals(dt.toString(),"04",f.print(dt));
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"23",f.print(dt));
        
        // current time set to 2002-06-09
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("04"));
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("22"));
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("21"));

        // Added tests to ensure single sign digit parse fails properly
        try {
            f.parseDateTime("-");
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
            fail();
        } catch (IllegalArgumentException ex) {}

        // Added tests for pivot year setting
        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("00"));

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("99"));

        // Added tests to ensure two digit parsing is lenient for DateTimeFormat
        f = DateTimeFormat.forPattern("YY").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");
    }

    public void testFormat_yearOfEraParse() {
        Chronology chrono = GJChronology.getInstanceUTC();

        DateTimeFormatter f = DateTimeFormat
            .forPattern("YYYY-MM GG")
            .withChronology(chrono)
            .withLocale(Locale.UK);

        DateTime dt = new DateTime(2005, 10, 1, 0, 0, 0, 0, chrono);
        assertEquals(dt,f.parseDateTime("2005-10 AD"));
        assertEquals(dt,f.parseDateTime("2005-10 CE"));

        dt = new DateTime(-2005, 10, 1, 0, 0, 0, 0, chrono);
        assertEquals(dt,f.parseDateTime("2005-10 BC"));
        assertEquals(dt,f.parseDateTime("2005-10 BCE"));
    }        

    //-----------------------------------------------------------------------
    public void testFormat_year() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("y").withLocale(Locale.UK);
        assertEquals(dt.toString(),"2004",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"2004",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"2004",f.print(dt));
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"-123",f.print(dt));

        // Added tests to ensure single sign digit parse fails properly
        try {
            f.parseDateTime("-");
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testFormat_year_twoDigit() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        assertEquals(dt.toString(),"04",f.print(dt));
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"23",f.print(dt));
        
        // current time set to 2002-06-09
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("04"));
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("22"));
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("21"));

        // Added tests to ensure single sign digit parse fails properly
        try {
            f.parseDateTime("-");
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
            fail();
        } catch (IllegalArgumentException ex) {}

        // Added tests for pivot year setting
        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("00"));

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("99"));

        // Added tests to ensure two digit parsing is strict by default for
        // DateTimeFormatterBuilder
        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
            fail();
        } catch (IllegalArgumentException ex) {}

        // Added tests to ensure two digit parsing is lenient for DateTimeFormat
        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        // Added tests for lenient two digit parsing
        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("04"));

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+04"));

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-04"));

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("4"));

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-4"));

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("004"));

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+004"));

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-004"));

        expect = new DateTime(3004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("3004"));

        expect = new DateTime(3004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+3004"));

        expect = new DateTime(-3004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-3004"));

        try {
            f.parseDateTime("-");
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testFormat_year_long() {
        DateTime dt = new DateTime(278004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy");
        assertEquals(dt.toString(),"278004",f.print(dt));
        
        // for coverage
        f = DateTimeFormat.forPattern("yyyyMMdd");
        assertEquals(dt.toString(),"2780040609",f.print(dt));
        
        // for coverage
        f = DateTimeFormat.forPattern("yyyyddMM");
        assertEquals(dt.toString(),"2780040906",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_weekyear() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("x").withLocale(Locale.UK);
        assertEquals(dt.toString(),"2004",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"2004",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"2004",f.print(dt));
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"-123",f.print(dt));
    }

    public void testFormat_weekyearOfEra_twoDigit() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        assertEquals(dt.toString(),"04",f.print(dt));
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"23",f.print(dt));
        
        // current time set to 2002-06-09
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("04"));
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("22"));
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("21"));

        // Added tests to ensure single sign digit parse fails properly
        try {
            f.parseDateTime("-");
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
            fail();
        } catch (IllegalArgumentException ex) {}

        // Added tests for pivot year setting
        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);
        assertEquals(expect,f.parseDateTime("00"));

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);
        assertEquals(expect,f.parseDateTime("99"));

        // Added tests to ensure two digit parsing is strict by default for
        // DateTimeFormatterBuilder
        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
            fail();
        } catch (IllegalArgumentException ex) {}

        // Added tests to ensure two digit parsing is lenient for DateTimeFormat
        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        // Added tests for lenient two digit parsing
        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("04"));

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+04"));

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-04"));

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("4"));

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-4"));

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("004"));

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+004"));

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-004"));

        expect = new DateTime(3004, 1, 2, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("3004"));

        expect = new DateTime(3004, 1, 2, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+3004"));

        expect = new DateTime(-3004, 1, 4, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-3004"));

        try {
            f.parseDateTime("-");
            fail();
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testFormat_weekOfWeekyear() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("w").withLocale(Locale.UK);
        assertEquals(dt.toString(),"24",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"24",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"24",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_dayOfWeek() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("e").withLocale(Locale.UK);
        assertEquals(dt.toString(),"3",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"3",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"3",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_dayOfWeekShortText() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("E").withLocale(Locale.UK);
        assertEquals(dt.toString(),"Wed",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"Wed",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"Wed",f.print(dt));
        
        f = f.withLocale(Locale.FRENCH);
        assertEquals(dt.toString(),"mer.",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_dayOfWeekText() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("EEEE").withLocale(Locale.UK);
        assertEquals(dt.toString(),"Wednesday",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"Wednesday",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"Wednesday",f.print(dt));
        
        f = f.withLocale(Locale.FRENCH);
        assertEquals(dt.toString(),"mercredi",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_dayOfYearText() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("D").withLocale(Locale.UK);
        assertEquals(dt.toString(),"161",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"161",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"161",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_monthOfYear() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        assertEquals(dt.toString(),"6",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"6",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"6",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_monthOfYearShortText() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("MMM").withLocale(Locale.UK);
        assertEquals(dt.toString(),"Jun",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"Jun",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"Jun",f.print(dt));
        
        f = f.withLocale(Locale.FRENCH);
        assertEquals(dt.toString(),"juin",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_monthOfYearText() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("MMMM").withLocale(Locale.UK);
        assertEquals(dt.toString(),"June",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"June",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"June",f.print(dt));
        
        f = f.withLocale(Locale.FRENCH);
        assertEquals(dt.toString(),"juin",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_dayOfMonth() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("d").withLocale(Locale.UK);
        assertEquals(dt.toString(),"9",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"9",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"9",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_halfdayOfDay() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("a").withLocale(Locale.UK);
        assertEquals(dt.toString(),"AM",f.print(dt).toUpperCase(Locale.ENGLISH));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"AM",f.print(dt).toUpperCase(Locale.ENGLISH));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"PM",f.print(dt).toUpperCase(Locale.ENGLISH));
    }

    //-----------------------------------------------------------------------
    public void testFormat_hourOfHalfday() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("K").withLocale(Locale.UK);
        assertEquals(dt.toString(),"10",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"6",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"7",f.print(dt));
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, UTC);
        assertEquals(dt.toString(),"0",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_clockhourOfHalfday() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("h").withLocale(Locale.UK);
        assertEquals(dt.toString(),"10",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"6",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"7",f.print(dt));
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, UTC);
        assertEquals(dt.toString(),"12",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_hourOfDay() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("H").withLocale(Locale.UK);
        assertEquals(dt.toString(),"10",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"6",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"19",f.print(dt));
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, UTC);
        assertEquals(dt.toString(),"0",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_clockhourOfDay() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("k").withLocale(Locale.UK);
        assertEquals(dt.toString(),"10",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"6",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"19",f.print(dt));
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, UTC);
        assertEquals(dt.toString(),"24",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_minute() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("m").withLocale(Locale.UK);
        assertEquals(dt.toString(),"20",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"20",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"20",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_second() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("s").withLocale(Locale.UK);
        assertEquals(dt.toString(),"30",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"30",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"30",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_fractionOfSecond() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("SSS").withLocale(Locale.UK);
        assertEquals(dt.toString(),"040",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"040",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"040",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_fractionOfSecondLong() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("SSSSSS").withLocale(Locale.UK);
        assertEquals(dt.toString(),"040000",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"040000",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"040000",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_zoneText() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("z").withLocale(Locale.ENGLISH);
        assertEquals(dt.toString(),"UTC",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"EDT",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"JST",f.print(dt));
    }

    public void testFormat_zoneLongText() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("zzzz").withLocale(Locale.ENGLISH);
        assertEquals(dt.toString(),"Coordinated Universal Time",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"Eastern Daylight Time",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"Japan Standard Time",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_zoneAmount() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("Z").withLocale(Locale.UK);
        assertEquals(dt.toString(),"+0000",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"-0400",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"+0900",f.print(dt));
    }

    public void testFormat_zoneAmountColon() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("ZZ").withLocale(Locale.UK);
        assertEquals(dt.toString(),"+00:00",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"-04:00",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"+09:00",f.print(dt));
    }

    public void testFormat_zoneAmountID() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("ZZZ").withLocale(Locale.UK);
        assertEquals(dt.toString(),"UTC",f.print(dt));
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"America/New_York",f.print(dt));
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"Asia/Tokyo",f.print(dt));
    }

    //-----------------------------------------------------------------------
    public void testFormat_other() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("'Hello' ''");
        assertEquals("Hello '",f.print(dt));
    }

    public void testFormat_invalid() {
        try {
            DateTimeFormat.forPattern(null);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateTimeFormat.forPattern("");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateTimeFormat.forPattern("A");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateTimeFormat.forPattern("dd/mm/AA");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testFormat_samples() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-dd HH.mm.ss");
        assertEquals("2004-06-09 10.20.30",f.print(dt));
    }

    public void testFormat_shortBasicParse() {
        // Tests special two digit parse to make sure it properly switches
        // between lenient and strict parsing.

        DateTime dt = new DateTime(2004, 3, 9, 0, 0, 0, 0);

        DateTimeFormatter f = DateTimeFormat.forPattern("yyMMdd");
        assertEquals(dt,f.parseDateTime("040309"));
        try {
            assertEquals(dt,f.parseDateTime("20040309"));
            fail();
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy/MM/dd");
        assertEquals(dt,f.parseDateTime("04/03/09"));
        assertEquals(dt,f.parseDateTime("2004/03/09"));
    }

    //-----------------------------------------------------------------------
    public void testParse_pivotYear() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yy").withPivotYear(2050).withZoneUTC();
        
        DateTime date = dateFormatter.parseDateTime("25.12.15");
        assertEquals(date.getYear(),2015);
        
        date = dateFormatter.parseDateTime("25.12.00");
        assertEquals(date.getYear(),2000);
        
        date = dateFormatter.parseDateTime("25.12.99");
        assertEquals(date.getYear(),2099);
    }

    public void testParse_pivotYear_ignored4DigitYear() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yyyy").withPivotYear(2050).withZoneUTC();
        
        DateTime date = dateFormatter.parseDateTime("25.12.15");
        assertEquals(date.getYear(),15);
        
        date = dateFormatter.parseDateTime("25.12.00");
        assertEquals(date.getYear(),0);
        
        date = dateFormatter.parseDateTime("25.12.99");
        assertEquals(date.getYear(),99);
    }

    //-----------------------------------------------------------------------
    public void testFormatParse_textMonthJanShort_UK() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 1, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals(str,"23 Jan 2007");
        DateTime date = dateFormatter.parseDateTime(str);
        check(date, 2007, 1, 23);
    }

    public void testFormatParse_textMonthJanShortLowerCase_UK() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.UK).withZoneUTC();
        DateTime date = dateFormatter.parseDateTime("23 jan 2007");
        check(date, 2007, 1, 23);
    }

    public void testFormatParse_textMonthJanShortUpperCase_UK() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.UK).withZoneUTC();
        DateTime date = dateFormatter.parseDateTime("23 JAN 2007");
        check(date, 2007, 1, 23);
    }

    public void testParse_textMonthJanLong_UK() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.UK).withZoneUTC();
        
        DateTime date = dateFormatter.parseDateTime("23 January 2007");
        check(date, 2007, 1, 23);
    }

    public void testFormatParse_textMonthJanLongLowerCase_UK() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.UK).withZoneUTC();
        DateTime date = dateFormatter.parseDateTime("23 january 2007");
        check(date, 2007, 1, 23);
    }

    public void testFormatParse_textMonthJanLongUpperCase_UK() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.UK).withZoneUTC();
        DateTime date = dateFormatter.parseDateTime("23 JANUARY 2007");
        check(date, 2007, 1, 23);
    }

    public void testFormatParse_textMonthJanShort_France() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 1, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("23 janv. 2007",str);
        DateTime date = dateFormatter.parseDateTime(str);
        check(date, 2007, 1, 23);
    }

    public void testFormatParse_textMonthJanLong_France() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        DateTime date = dateFormatter.parseDateTime("23 janvier 2007");
        check(date, 2007, 1, 23);
    }

    public void testFormatParse_textMonthApr_France() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 2, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("23 f\u00E9vr. 2007",str);// e acute 
        DateTime date = dateFormatter.parseDateTime(str);
        check(date, 2007, 2, 23);
    }

    public void testFormatParse_textMonthAtEnd_France() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM")
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("23 juin",str);
        DateTime date = dateFormatter.parseDateTime(str);
        check(date, 2000, 6, 23);
    }

    public void testFormatParse_textMonthAtEnd_France_withSpecifiedDefault() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM")
            .withLocale(Locale.FRANCE).withZoneUTC().withDefaultYear(1980);
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("23 juin",str);
        DateTime date = dateFormatter.parseDateTime(str);
        check(date, 1980, 6, 23);
    }

    public void testFormatParse_textMonthApr_Korean() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("EEEE, d MMMM yyyy HH:mm")
            .withLocale(Locale.KOREAN).withZoneUTC();
        
        String str = new DateTime(2007, 3, 8, 22, 0, 0, 0, UTC).toString(dateFormatter);
        DateTime date = dateFormatter.parseDateTime(str);
        assertEquals(new DateTime(2007,3,8,22,0,0,0,UTC),date);
    }

    //-----------------------------------------------------------------------
    public void testFormatParse_textHalfdayAM_UK() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendClockhourOfHalfday(2)
            .appendLiteral('-')
            .appendHalfdayOfDayText()
            .appendLiteral('-')
            .appendYear(4, 4)
            .toFormatter()
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 18, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$06-PM-2007",str.toUpperCase(Locale.ENGLISH));
        DateTime date = dateFormatter.parseDateTime(str);
        check(date, 2007, 1, 1);
    }

    public void testFormatParse_textHalfdayAM_France() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendClockhourOfHalfday(2)
            .appendLiteral('-')
            .appendHalfdayOfDayText()
            .appendLiteral('-')
            .appendYear(4, 4)
            .toFormatter()
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 18, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$06-PM-2007",str);
        DateTime date = dateFormatter.parseDateTime(str);
        check(date, 2007, 1, 1);
    }

    //-----------------------------------------------------------------------
    public void testFormatParse_textEraAD_UK() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendEraText()
            .appendYear(4, 4)
            .toFormatter()
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$AD2007",str);
        DateTime date = dateFormatter.parseDateTime(str);
        check(date, 2007, 1, 1);
    }

    public void testFormatParse_textEraAD_France() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendEraText()
            .appendYear(4, 4)
            .toFormatter()
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$ap. J.-C.2007",str);
        DateTime date = dateFormatter.parseDateTime(str);
        check(date, 2007, 1, 1);
    }

    public void testFormatParse_textEraBC_France() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendEraText()
            .appendYear(4, 4)
            .toFormatter()
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(-1, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertTrue(str.equals("$BC-0001")|| str.equals("$av. J.-C.-0001"));
        DateTime date = dateFormatter.parseDateTime(str);
        check(date, -1, 1, 1);
    }

    //-----------------------------------------------------------------------
    public void testFormatParse_textYear_UK() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendText(DateTimeFieldType.year())
            .toFormatter()
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$2007",str);
        try {
            dateFormatter.parseDateTime(str);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    public void testFormatParse_textYear_France() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendText(DateTimeFieldType.year())
            .toFormatter()
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$2007",str);
        try {
            dateFormatter.parseDateTime(str);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void testFormatParse_textAdjoiningHelloWorld_UK() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendDayOfMonth(2)
            .appendMonthOfYearShortText()
            .appendLiteral("HelloWorld")
            .toFormatter()
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$23JunHelloWorld",str);
        dateFormatter.parseDateTime(str);
    }

    public void testFormatParse_textAdjoiningMonthDOW_UK() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendDayOfMonth(2)
            .appendMonthOfYearShortText()
            .appendDayOfWeekShortText()
            .toFormatter()
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$23JunSat",str);
        dateFormatter.parseDateTime(str);
    }

    //-----------------------------------------------------------------------
    public void testFormatParse_zoneId_noColon() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm Z").withZoneUTC();
        String str = new DateTime(2007, 6, 23, 1, 2, 0, 0, UTC).toString(dateFormatter);
        assertEquals("01:02 +0000",str);
        DateTime parsed = dateFormatter.parseDateTime(str);
        assertEquals(1,parsed.getHourOfDay());
        assertEquals(2,parsed.getMinuteOfHour());
    }

    public void testFormatParse_zoneId_noColon_parseZ() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm Z").withZoneUTC();
        DateTime parsed = dateFormatter.parseDateTime("01:02 Z");
        assertEquals(1,parsed.getHourOfDay());
        assertEquals(2,parsed.getMinuteOfHour());
    }

    public void testFormatParse_zoneId_colon() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm ZZ").withZoneUTC();
        String str = new DateTime(2007, 6, 23, 1, 2, 0, 0, UTC).toString(dateFormatter);
        assertEquals("01:02 +00:00",str);
        DateTime parsed = dateFormatter.parseDateTime(str);
        assertEquals(1,parsed.getHourOfDay());
        assertEquals(2,parsed.getMinuteOfHour());
    }

    public void testFormatParse_zoneId_colon_parseZ() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm ZZ").withZoneUTC();
        DateTime parsed = dateFormatter.parseDateTime("01:02 Z");
        assertEquals(1,parsed.getHourOfDay());
        assertEquals(2,parsed.getMinuteOfHour());
    }

    //-----------------------------------------------------------------------
    private void check(DateTime test, int hour, int min, int sec) {
        assertEquals(hour,test.getYear());
        assertEquals(min,test.getMonthOfYear());
        assertEquals(sec,test.getDayOfMonth());
    }

    public void testSubclassableConstructor_1_oe() {
        DateTimeFormat f = new DateTimeFormat() {
        };
        assertNotNull(f);
    }

    public void testFormat_era_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("G").withLocale(Locale.UK);
        assertEquals(dt.toString(),"AD",f.print(dt));
    }

    public void testFormat_era_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("G").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"AD",f.print(dt));
    }

    public void testFormat_era_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("G").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(PARIS);
        assertEquals(dt.toString(),"AD",f.print(dt));
    }

    public void testFormat_centuryOfEra_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("C").withLocale(Locale.UK);
        assertEquals(dt.toString(),"20",f.print(dt));
    }

    public void testFormat_centuryOfEra_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("C").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"20",f.print(dt));
    }

    public void testFormat_centuryOfEra_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("C").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"20",f.print(dt));
    }

    public void testFormat_centuryOfEra_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("C").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"1",f.print(dt));
    }

    public void testFormat_yearOfEra_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("Y").withLocale(Locale.UK);
        assertEquals(dt.toString(),"2004",f.print(dt));
    }

    public void testFormat_yearOfEra_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("Y").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"2004",f.print(dt));
    }

    public void testFormat_yearOfEra_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("Y").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"2004",f.print(dt));
    }

    public void testFormat_yearOfEra_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("Y").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"124",f.print(dt));  // 124th year of BCE;
    }

    public void testFormat_yearOfEra_twoDigit_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("YY").withLocale(Locale.UK);
        assertEquals(dt.toString(),"04",f.print(dt));
    }

    public void testFormat_yearOfEra_twoDigit_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("YY").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"23",f.print(dt));
    }

    public void testFormat_yearOfEra_twoDigit_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("YY").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("04"));
    }

    public void testFormat_yearOfEra_twoDigit_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("YY").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("22"));
    }

    public void testFormat_yearOfEra_twoDigit_5_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("YY").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("21"));
    }

    public void testFormat_yearOfEra_twoDigit_8_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("YY").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("00"));
    }

    public void testFormat_yearOfEra_twoDigit_9_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("YY").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("99"));
    }

    public void testFormat_yearOfEraParse_1_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();

        DateTimeFormatter f = DateTimeFormat
            .forPattern("YYYY-MM GG")
            .withChronology(chrono)
            .withLocale(Locale.UK);

        DateTime dt = new DateTime(2005, 10, 1, 0, 0, 0, 0, chrono);
        assertEquals(dt,f.parseDateTime("2005-10 AD"));
    }

    public void testFormat_yearOfEraParse_2_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();

        DateTimeFormatter f = DateTimeFormat
            .forPattern("YYYY-MM GG")
            .withChronology(chrono)
            .withLocale(Locale.UK);

        DateTime dt = new DateTime(2005, 10, 1, 0, 0, 0, 0, chrono);
        assertEquals(dt,f.parseDateTime("2005-10 CE"));
    }

    public void testFormat_yearOfEraParse_3_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();

        DateTimeFormatter f = DateTimeFormat
            .forPattern("YYYY-MM GG")
            .withChronology(chrono)
            .withLocale(Locale.UK);

        DateTime dt = new DateTime(2005, 10, 1, 0, 0, 0, 0, chrono);

        dt = new DateTime(-2005, 10, 1, 0, 0, 0, 0, chrono);
        assertEquals(dt,f.parseDateTime("2005-10 BC"));
    }

    public void testFormat_yearOfEraParse_4_oe() {
        Chronology chrono = GJChronology.getInstanceUTC();

        DateTimeFormatter f = DateTimeFormat
            .forPattern("YYYY-MM GG")
            .withChronology(chrono)
            .withLocale(Locale.UK);

        DateTime dt = new DateTime(2005, 10, 1, 0, 0, 0, 0, chrono);

        dt = new DateTime(-2005, 10, 1, 0, 0, 0, 0, chrono);
        assertEquals(dt,f.parseDateTime("2005-10 BCE"));
    }

    public void testFormat_year_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("y").withLocale(Locale.UK);
        assertEquals(dt.toString(),"2004",f.print(dt));
    }

    public void testFormat_year_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("y").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"2004",f.print(dt));
    }

    public void testFormat_year_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("y").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"2004",f.print(dt));
    }

    public void testFormat_year_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("y").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"-123",f.print(dt));
    }

    public void testFormat_year_twoDigit_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        assertEquals(dt.toString(),"04",f.print(dt));
    }

    public void testFormat_year_twoDigit_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"23",f.print(dt));
    }

    public void testFormat_year_twoDigit_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("04"));
    }

    public void testFormat_year_twoDigit_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("22"));
    }

    public void testFormat_year_twoDigit_5_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("21"));
    }

    public void testFormat_year_twoDigit_8_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("00"));
    }

    public void testFormat_year_twoDigit_9_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("99"));
    }

    public void testFormat_year_twoDigit_14_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("04"));
    }

    public void testFormat_year_twoDigit_15_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+04"));
    }

    public void testFormat_year_twoDigit_16_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-04"));
    }

    public void testFormat_year_twoDigit_17_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("4"));
    }

    public void testFormat_year_twoDigit_18_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-4"));
    }

    public void testFormat_year_twoDigit_19_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("004"));
    }

    public void testFormat_year_twoDigit_20_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+004"));
    }

    public void testFormat_year_twoDigit_21_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-004"));
    }

    public void testFormat_year_twoDigit_22_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("3004"));
    }

    public void testFormat_year_twoDigit_23_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+3004"));
    }

    public void testFormat_year_twoDigit_24_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 1, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 1, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(2099, 1, 1, 0, 0, 0, 0, UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitYear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3004, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(-3004, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-3004"));
    }

    public void testFormat_year_long_1_oe() {
        DateTime dt = new DateTime(278004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy");
        assertEquals(dt.toString(),"278004",f.print(dt));
    }

    public void testFormat_year_long_2_oe() {
        DateTime dt = new DateTime(278004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy");
        
        f = DateTimeFormat.forPattern("yyyyMMdd");
        assertEquals(dt.toString(),"2780040609",f.print(dt));
    }

    public void testFormat_year_long_3_oe() {
        DateTime dt = new DateTime(278004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy");
        
        f = DateTimeFormat.forPattern("yyyyMMdd");
        
        f = DateTimeFormat.forPattern("yyyyddMM");
        assertEquals(dt.toString(),"2780040906",f.print(dt));
    }

    public void testFormat_weekyear_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("x").withLocale(Locale.UK);
        assertEquals(dt.toString(),"2004",f.print(dt));
    }

    public void testFormat_weekyear_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("x").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"2004",f.print(dt));
    }

    public void testFormat_weekyear_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("x").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"2004",f.print(dt));
    }

    public void testFormat_weekyear_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("x").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"-123",f.print(dt));
    }

    public void testFormat_weekyearOfEra_twoDigit_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        assertEquals(dt.toString(),"04",f.print(dt));
    }

    public void testFormat_weekyearOfEra_twoDigit_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        assertEquals(dt.toString(),"23",f.print(dt));
    }

    public void testFormat_weekyearOfEra_twoDigit_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("04"));
    }

    public void testFormat_weekyearOfEra_twoDigit_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("22"));
    }

    public void testFormat_weekyearOfEra_twoDigit_5_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("21"));
    }

    public void testFormat_weekyearOfEra_twoDigit_8_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);
        assertEquals(expect,f.parseDateTime("00"));
    }

    public void testFormat_weekyearOfEra_twoDigit_9_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);
        assertEquals(expect,f.parseDateTime("99"));
    }

    public void testFormat_weekyearOfEra_twoDigit_14_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("04"));
    }

    public void testFormat_weekyearOfEra_twoDigit_15_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+04"));
    }

    public void testFormat_weekyearOfEra_twoDigit_16_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-04"));
    }

    public void testFormat_weekyearOfEra_twoDigit_17_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("4"));
    }

    public void testFormat_weekyearOfEra_twoDigit_18_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-4"));
    }

    public void testFormat_weekyearOfEra_twoDigit_19_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("004"));
    }

    public void testFormat_weekyearOfEra_twoDigit_20_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+004"));
    }

    public void testFormat_weekyearOfEra_twoDigit_21_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-004"));
    }

    public void testFormat_weekyearOfEra_twoDigit_22_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3004, 1, 2, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("3004"));
    }

    public void testFormat_weekyearOfEra_twoDigit_23_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3004, 1, 2, 0, 0, 0, 0, UTC);

        expect = new DateTime(3004, 1, 2, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("+3004"));
    }

    public void testFormat_weekyearOfEra_twoDigit_24_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        
        dt = new DateTime(-123, 6, 9, 10, 20, 30, 40, UTC);
        
        f = f.withZoneUTC();
        DateTime expect = null;
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(1922, 1, 2, 0, 0, 0, 0, UTC);
        
        expect = new DateTime(2021, 1, 4, 0, 0, 0, 0, UTC);

        try {
            f.parseDateTime("-");
        } catch (IllegalArgumentException ex) {}

        try {
            f.parseDateTime("+");
        } catch (IllegalArgumentException ex) {}

        f = f.withPivotYear(new Integer(2050));
        expect = new DateTime(2000, 1, 3, 0, 0, 0, 0, DateTimeZone.UTC);

        expect = new DateTime(2098, 12, 29, 0, 0, 0, 0, DateTimeZone.UTC);

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000).toFormatter();
        f = f.withZoneUTC();
        try {
            f.parseDateTime("5");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("005");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("+50");
        } catch (IllegalArgumentException ex) {}
        try {
            f.parseDateTime("-50");
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("xx").withLocale(Locale.UK);
        f = f.withZoneUTC();
        f.parseDateTime("5");
        f.parseDateTime("005");
        f.parseDateTime("+50");
        f.parseDateTime("-50");

        f = new DateTimeFormatterBuilder().appendTwoDigitWeekyear(2000, true).toFormatter();
        f = f.withZoneUTC();
        expect = new DateTime(2003, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(3, 12, 29, 0, 0, 0, 0, UTC);

        expect = new DateTime(-4, 1, 1, 0, 0, 0, 0, UTC);

        expect = new DateTime(3004, 1, 2, 0, 0, 0, 0, UTC);

        expect = new DateTime(3004, 1, 2, 0, 0, 0, 0, UTC);

        expect = new DateTime(-3004, 1, 4, 0, 0, 0, 0, UTC);
        assertEquals(expect,f.parseDateTime("-3004"));
    }

    public void testFormat_weekOfWeekyear_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("w").withLocale(Locale.UK);
        assertEquals(dt.toString(),"24",f.print(dt));
    }

    public void testFormat_weekOfWeekyear_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("w").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"24",f.print(dt));
    }

    public void testFormat_weekOfWeekyear_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("w").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"24",f.print(dt));
    }

    public void testFormat_dayOfWeek_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("e").withLocale(Locale.UK);
        assertEquals(dt.toString(),"3",f.print(dt));
    }

    public void testFormat_dayOfWeek_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("e").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"3",f.print(dt));
    }

    public void testFormat_dayOfWeek_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("e").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"3",f.print(dt));
    }

    public void testFormat_dayOfWeekShortText_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("E").withLocale(Locale.UK);
        assertEquals(dt.toString(),"Wed",f.print(dt));
    }

    public void testFormat_dayOfWeekShortText_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("E").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"Wed",f.print(dt));
    }

    public void testFormat_dayOfWeekShortText_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("E").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"Wed",f.print(dt));
    }

    public void testFormat_dayOfWeekShortText_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("E").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        f = f.withLocale(Locale.FRENCH);
        assertEquals(dt.toString(),"mer.",f.print(dt));
    }

    public void testFormat_dayOfWeekText_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("EEEE").withLocale(Locale.UK);
        assertEquals(dt.toString(),"Wednesday",f.print(dt));
    }

    public void testFormat_dayOfWeekText_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("EEEE").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"Wednesday",f.print(dt));
    }

    public void testFormat_dayOfWeekText_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("EEEE").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"Wednesday",f.print(dt));
    }

    public void testFormat_dayOfWeekText_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("EEEE").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        f = f.withLocale(Locale.FRENCH);
        assertEquals(dt.toString(),"mercredi",f.print(dt));
    }

    public void testFormat_dayOfYearText_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("D").withLocale(Locale.UK);
        assertEquals(dt.toString(),"161",f.print(dt));
    }

    public void testFormat_dayOfYearText_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("D").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"161",f.print(dt));
    }

    public void testFormat_dayOfYearText_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("D").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"161",f.print(dt));
    }

    public void testFormat_monthOfYear_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        assertEquals(dt.toString(),"6",f.print(dt));
    }

    public void testFormat_monthOfYear_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"6",f.print(dt));
    }

    public void testFormat_monthOfYear_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("M").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"6",f.print(dt));
    }

    public void testFormat_monthOfYearShortText_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("MMM").withLocale(Locale.UK);
        assertEquals(dt.toString(),"Jun",f.print(dt));
    }

    public void testFormat_monthOfYearShortText_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("MMM").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"Jun",f.print(dt));
    }

    public void testFormat_monthOfYearShortText_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("MMM").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"Jun",f.print(dt));
    }

    public void testFormat_monthOfYearShortText_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("MMM").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        f = f.withLocale(Locale.FRENCH);
        assertEquals(dt.toString(),"juin",f.print(dt));
    }

    public void testFormat_monthOfYearText_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("MMMM").withLocale(Locale.UK);
        assertEquals(dt.toString(),"June",f.print(dt));
    }

    public void testFormat_monthOfYearText_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("MMMM").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"June",f.print(dt));
    }

    public void testFormat_monthOfYearText_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("MMMM").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"June",f.print(dt));
    }

    public void testFormat_monthOfYearText_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("MMMM").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        f = f.withLocale(Locale.FRENCH);
        assertEquals(dt.toString(),"juin",f.print(dt));
    }

    public void testFormat_dayOfMonth_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("d").withLocale(Locale.UK);
        assertEquals(dt.toString(),"9",f.print(dt));
    }

    public void testFormat_dayOfMonth_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("d").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"9",f.print(dt));
    }

    public void testFormat_dayOfMonth_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("d").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"9",f.print(dt));
    }

    public void testFormat_halfdayOfDay_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("a").withLocale(Locale.UK);
        assertEquals(dt.toString(),"AM",f.print(dt).toUpperCase(Locale.ENGLISH));
    }

    public void testFormat_halfdayOfDay_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("a").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"AM",f.print(dt).toUpperCase(Locale.ENGLISH));
    }

    public void testFormat_halfdayOfDay_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("a").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"PM",f.print(dt).toUpperCase(Locale.ENGLISH));
    }

    public void testFormat_hourOfHalfday_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("K").withLocale(Locale.UK);
        assertEquals(dt.toString(),"10",f.print(dt));
    }

    public void testFormat_hourOfHalfday_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("K").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"6",f.print(dt));
    }

    public void testFormat_hourOfHalfday_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("K").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"7",f.print(dt));
    }

    public void testFormat_hourOfHalfday_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("K").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, UTC);
        assertEquals(dt.toString(),"0",f.print(dt));
    }

    public void testFormat_clockhourOfHalfday_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("h").withLocale(Locale.UK);
        assertEquals(dt.toString(),"10",f.print(dt));
    }

    public void testFormat_clockhourOfHalfday_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("h").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"6",f.print(dt));
    }

    public void testFormat_clockhourOfHalfday_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("h").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"7",f.print(dt));
    }

    public void testFormat_clockhourOfHalfday_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("h").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, UTC);
        assertEquals(dt.toString(),"12",f.print(dt));
    }

    public void testFormat_hourOfDay_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("H").withLocale(Locale.UK);
        assertEquals(dt.toString(),"10",f.print(dt));
    }

    public void testFormat_hourOfDay_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("H").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"6",f.print(dt));
    }

    public void testFormat_hourOfDay_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("H").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"19",f.print(dt));
    }

    public void testFormat_hourOfDay_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("H").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, UTC);
        assertEquals(dt.toString(),"0",f.print(dt));
    }

    public void testFormat_clockhourOfDay_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("k").withLocale(Locale.UK);
        assertEquals(dt.toString(),"10",f.print(dt));
    }

    public void testFormat_clockhourOfDay_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("k").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"6",f.print(dt));
    }

    public void testFormat_clockhourOfDay_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("k").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"19",f.print(dt));
    }

    public void testFormat_clockhourOfDay_4_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("k").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        
        dt = new DateTime(2004, 6, 9, 0, 0, 0, 0, UTC);
        assertEquals(dt.toString(),"24",f.print(dt));
    }

    public void testFormat_minute_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("m").withLocale(Locale.UK);
        assertEquals(dt.toString(),"20",f.print(dt));
    }

    public void testFormat_minute_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("m").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"20",f.print(dt));
    }

    public void testFormat_minute_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("m").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"20",f.print(dt));
    }

    public void testFormat_second_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("s").withLocale(Locale.UK);
        assertEquals(dt.toString(),"30",f.print(dt));
    }

    public void testFormat_second_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("s").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"30",f.print(dt));
    }

    public void testFormat_second_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("s").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"30",f.print(dt));
    }

    public void testFormat_fractionOfSecond_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("SSS").withLocale(Locale.UK);
        assertEquals(dt.toString(),"040",f.print(dt));
    }

    public void testFormat_fractionOfSecond_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("SSS").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"040",f.print(dt));
    }

    public void testFormat_fractionOfSecond_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("SSS").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"040",f.print(dt));
    }

    public void testFormat_fractionOfSecondLong_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("SSSSSS").withLocale(Locale.UK);
        assertEquals(dt.toString(),"040000",f.print(dt));
    }

    public void testFormat_fractionOfSecondLong_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("SSSSSS").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"040000",f.print(dt));
    }

    public void testFormat_fractionOfSecondLong_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("SSSSSS").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"040000",f.print(dt));
    }

    public void testFormat_zoneText_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("z").withLocale(Locale.ENGLISH);
        assertEquals(dt.toString(),"UTC",f.print(dt));
    }

    public void testFormat_zoneText_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("z").withLocale(Locale.ENGLISH);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"EDT",f.print(dt));
    }

    public void testFormat_zoneText_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("z").withLocale(Locale.ENGLISH);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"JST",f.print(dt));
    }

    public void testFormat_zoneLongText_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("zzzz").withLocale(Locale.ENGLISH);
        assertEquals(dt.toString(),"Coordinated Universal Time",f.print(dt));
    }

    public void testFormat_zoneLongText_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("zzzz").withLocale(Locale.ENGLISH);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"Eastern Daylight Time",f.print(dt));
    }

    public void testFormat_zoneLongText_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("zzzz").withLocale(Locale.ENGLISH);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"Japan Standard Time",f.print(dt));
    }

    public void testFormat_zoneAmount_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("Z").withLocale(Locale.UK);
        assertEquals(dt.toString(),"+0000",f.print(dt));
    }

    public void testFormat_zoneAmount_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("Z").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"-0400",f.print(dt));
    }

    public void testFormat_zoneAmount_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("Z").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"+0900",f.print(dt));
    }

    public void testFormat_zoneAmountColon_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("ZZ").withLocale(Locale.UK);
        assertEquals(dt.toString(),"+00:00",f.print(dt));
    }

    public void testFormat_zoneAmountColon_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("ZZ").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"-04:00",f.print(dt));
    }

    public void testFormat_zoneAmountColon_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("ZZ").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"+09:00",f.print(dt));
    }

    public void testFormat_zoneAmountID_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("ZZZ").withLocale(Locale.UK);
        assertEquals(dt.toString(),"UTC",f.print(dt));
    }

    public void testFormat_zoneAmountID_2_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("ZZZ").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        assertEquals(dt.toString(),"America/New_York",f.print(dt));
    }

    public void testFormat_zoneAmountID_3_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("ZZZ").withLocale(Locale.UK);
        
        dt = dt.withZone(NEWYORK);
        
        dt = dt.withZone(TOKYO);
        assertEquals(dt.toString(),"Asia/Tokyo",f.print(dt));
    }

    public void testFormat_other_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("'Hello' ''");
        assertEquals("Hello '",f.print(dt));
    }

    public void testFormat_samples_1_oe() {
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 40, UTC);
        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-dd HH.mm.ss");
        assertEquals("2004-06-09 10.20.30",f.print(dt));
    }

    public void testFormat_shortBasicParse_1_oe() {

        DateTime dt = new DateTime(2004, 3, 9, 0, 0, 0, 0);

        DateTimeFormatter f = DateTimeFormat.forPattern("yyMMdd");
        assertEquals(dt,f.parseDateTime("040309"));
    }

    public void testFormat_shortBasicParse_4_oe() {

        DateTime dt = new DateTime(2004, 3, 9, 0, 0, 0, 0);

        DateTimeFormatter f = DateTimeFormat.forPattern("yyMMdd");
        try {
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy/MM/dd");
        assertEquals(dt,f.parseDateTime("04/03/09"));
    }

    public void testFormat_shortBasicParse_5_oe() {

        DateTime dt = new DateTime(2004, 3, 9, 0, 0, 0, 0);

        DateTimeFormatter f = DateTimeFormat.forPattern("yyMMdd");
        try {
        } catch (IllegalArgumentException ex) {}

        f = DateTimeFormat.forPattern("yy/MM/dd");
        assertEquals(dt,f.parseDateTime("2004/03/09"));
    }

    public void testParse_pivotYear_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yy").withPivotYear(2050).withZoneUTC();
        
        DateTime date = dateFormatter.parseDateTime("25.12.15");
        assertEquals(date.getYear(),2015);
    }

    public void testParse_pivotYear_2_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yy").withPivotYear(2050).withZoneUTC();
        
        DateTime date = dateFormatter.parseDateTime("25.12.15");
        
        date = dateFormatter.parseDateTime("25.12.00");
        assertEquals(date.getYear(),2000);
    }

    public void testParse_pivotYear_3_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yy").withPivotYear(2050).withZoneUTC();
        
        DateTime date = dateFormatter.parseDateTime("25.12.15");
        
        date = dateFormatter.parseDateTime("25.12.00");
        
        date = dateFormatter.parseDateTime("25.12.99");
        assertEquals(date.getYear(),2099);
    }

    public void testParse_pivotYear_ignored4DigitYear_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yyyy").withPivotYear(2050).withZoneUTC();
        
        DateTime date = dateFormatter.parseDateTime("25.12.15");
        assertEquals(date.getYear(),15);
    }

    public void testParse_pivotYear_ignored4DigitYear_2_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yyyy").withPivotYear(2050).withZoneUTC();
        
        DateTime date = dateFormatter.parseDateTime("25.12.15");
        
        date = dateFormatter.parseDateTime("25.12.00");
        assertEquals(date.getYear(),0);
    }

    public void testParse_pivotYear_ignored4DigitYear_3_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yyyy").withPivotYear(2050).withZoneUTC();
        
        DateTime date = dateFormatter.parseDateTime("25.12.15");
        
        date = dateFormatter.parseDateTime("25.12.00");
        
        date = dateFormatter.parseDateTime("25.12.99");
        assertEquals(date.getYear(),99);
    }

    public void testFormatParse_textMonthJanShort_UK_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 1, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals(str,"23 Jan 2007");
    }

    public void testFormatParse_textMonthJanShort_France_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 1, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("23 janv. 2007",str);
    }

    public void testFormatParse_textMonthApr_France_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 2, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("23 f\u00E9vr. 2007",str);// e acute;
    }

    public void testFormatParse_textMonthAtEnd_France_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM")
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("23 juin",str);
    }

    public void testFormatParse_textMonthAtEnd_France_withSpecifiedDefault_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd MMM")
            .withLocale(Locale.FRANCE).withZoneUTC().withDefaultYear(1980);
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("23 juin",str);
    }

    public void testFormatParse_textMonthApr_Korean_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("EEEE, d MMMM yyyy HH:mm")
            .withLocale(Locale.KOREAN).withZoneUTC();
        
        String str = new DateTime(2007, 3, 8, 22, 0, 0, 0, UTC).toString(dateFormatter);
        DateTime date = dateFormatter.parseDateTime(str);
        assertEquals(new DateTime(2007,3,8,22,0,0,0,UTC),date);
    }

    public void testFormatParse_textHalfdayAM_UK_1_oe() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendClockhourOfHalfday(2)
            .appendLiteral('-')
            .appendHalfdayOfDayText()
            .appendLiteral('-')
            .appendYear(4, 4)
            .toFormatter()
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 18, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$06-PM-2007",str.toUpperCase(Locale.ENGLISH));
    }

    public void testFormatParse_textHalfdayAM_France_1_oe() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendClockhourOfHalfday(2)
            .appendLiteral('-')
            .appendHalfdayOfDayText()
            .appendLiteral('-')
            .appendYear(4, 4)
            .toFormatter()
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 18, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$06-PM-2007",str);
    }

    public void testFormatParse_textEraAD_UK_1_oe() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendEraText()
            .appendYear(4, 4)
            .toFormatter()
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$AD2007",str);
    }

    public void testFormatParse_textEraAD_France_1_oe() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendEraText()
            .appendYear(4, 4)
            .toFormatter()
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$ap. J.-C.2007",str);
    }

    public void testFormatParse_textEraBC_France_1_oe() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendEraText()
            .appendYear(4, 4)
            .toFormatter()
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(-1, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertTrue(str.equals("$BC-0001")|| str.equals("$av. J.-C.-0001"));
    }

    public void testFormatParse_textYear_UK_1_oe() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendText(DateTimeFieldType.year())
            .toFormatter()
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$2007",str);
    }

    public void testFormatParse_textYear_France_1_oe() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendText(DateTimeFieldType.year())
            .toFormatter()
            .withLocale(Locale.FRANCE).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$2007",str);
    }

    public void testFormatParse_textAdjoiningHelloWorld_UK_1_oe() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendDayOfMonth(2)
            .appendMonthOfYearShortText()
            .appendLiteral("HelloWorld")
            .toFormatter()
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$23JunHelloWorld",str);
    }

    public void testFormatParse_textAdjoiningMonthDOW_UK_1_oe() {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendLiteral('$')
            .appendDayOfMonth(2)
            .appendMonthOfYearShortText()
            .appendDayOfWeekShortText()
            .toFormatter()
            .withLocale(Locale.UK).withZoneUTC();
        
        String str = new DateTime(2007, 6, 23, 0, 0, 0, 0, UTC).toString(dateFormatter);
        assertEquals("$23JunSat",str);
    }

    public void testFormatParse_zoneId_noColon_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm Z").withZoneUTC();
        String str = new DateTime(2007, 6, 23, 1, 2, 0, 0, UTC).toString(dateFormatter);
        assertEquals("01:02 +0000",str);
    }

    public void testFormatParse_zoneId_noColon_2_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm Z").withZoneUTC();
        String str = new DateTime(2007, 6, 23, 1, 2, 0, 0, UTC).toString(dateFormatter);
        DateTime parsed = dateFormatter.parseDateTime(str);
        assertEquals(1,parsed.getHourOfDay());
    }

    public void testFormatParse_zoneId_noColon_3_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm Z").withZoneUTC();
        String str = new DateTime(2007, 6, 23, 1, 2, 0, 0, UTC).toString(dateFormatter);
        DateTime parsed = dateFormatter.parseDateTime(str);
        assertEquals(2,parsed.getMinuteOfHour());
    }

    public void testFormatParse_zoneId_noColon_parseZ_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm Z").withZoneUTC();
        DateTime parsed = dateFormatter.parseDateTime("01:02 Z");
        assertEquals(1,parsed.getHourOfDay());
    }

    public void testFormatParse_zoneId_noColon_parseZ_2_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm Z").withZoneUTC();
        DateTime parsed = dateFormatter.parseDateTime("01:02 Z");
        assertEquals(2,parsed.getMinuteOfHour());
    }

    public void testFormatParse_zoneId_colon_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm ZZ").withZoneUTC();
        String str = new DateTime(2007, 6, 23, 1, 2, 0, 0, UTC).toString(dateFormatter);
        assertEquals("01:02 +00:00",str);
    }

    public void testFormatParse_zoneId_colon_2_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm ZZ").withZoneUTC();
        String str = new DateTime(2007, 6, 23, 1, 2, 0, 0, UTC).toString(dateFormatter);
        DateTime parsed = dateFormatter.parseDateTime(str);
        assertEquals(1,parsed.getHourOfDay());
    }

    public void testFormatParse_zoneId_colon_3_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm ZZ").withZoneUTC();
        String str = new DateTime(2007, 6, 23, 1, 2, 0, 0, UTC).toString(dateFormatter);
        DateTime parsed = dateFormatter.parseDateTime(str);
        assertEquals(2,parsed.getMinuteOfHour());
    }

    public void testFormatParse_zoneId_colon_parseZ_1_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm ZZ").withZoneUTC();
        DateTime parsed = dateFormatter.parseDateTime("01:02 Z");
        assertEquals(1,parsed.getHourOfDay());
    }

    public void testFormatParse_zoneId_colon_parseZ_2_oe() {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("HH:mm ZZ").withZoneUTC();
        DateTime parsed = dateFormatter.parseDateTime("01:02 Z");
        assertEquals(2,parsed.getMinuteOfHour());
    }

public void testFormat_invalid_oe_101_oe() {
        try {
            DateTimeFormat.forPattern(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testFormat_invalid_oe_102_oe() {
        try {
            DateTimeFormat.forPattern("");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testFormat_invalid_oe_103_oe() {
        try {
            DateTimeFormat.forPattern("A");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

public void testFormat_invalid_oe_104_oe() {
        try {
            DateTimeFormat.forPattern("dd/mm/AA");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

}
