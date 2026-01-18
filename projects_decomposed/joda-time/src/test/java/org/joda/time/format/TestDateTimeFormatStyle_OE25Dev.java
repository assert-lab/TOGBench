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

import java.text.DateFormat;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;

/**
 * This class is a Junit unit test for DateTimeFormat styles.
 *
 * @author Stephen Colebourne
 */
public class TestDateTimeFormatStyle_OE25Dev extends TestCase {

    private static final Locale UK = Locale.UK;
    private static final Locale US = Locale.US;
    private static final Locale FRANCE = Locale.FRANCE;
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
        return new TestSuite(TestDateTimeFormatStyle_OE25Dev.class);
    }

    public TestDateTimeFormatStyle_OE25Dev(String name) {
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
        Locale.setDefault(UK);
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
    public void testForStyle_stringLengths() {
        try {
            DateTimeFormat.forStyle(null);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateTimeFormat.forStyle("");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateTimeFormat.forStyle("S");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateTimeFormat.forStyle("SSS");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testForStyle_invalidStrings() {
        try {
            DateTimeFormat.forStyle("AA");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateTimeFormat.forStyle("--");
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            DateTimeFormat.forStyle("ss");
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testForStyle_shortDate_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("S-");
        assertSame(g, f);
    }

    public void testForStyle_shortDate_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("S-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.SHORT, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_shortDate_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("S-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.SHORT, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_shortDate_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("S-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.SHORT, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.SHORT, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_shortDate_5_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("S-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.SHORT, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.SHORT, FRANCE).format(dt.toDate());
        // removed other assertion
        
        DateTime date = new DateTime(
                DateFormat.getDateInstance(DateFormat.SHORT, FRANCE).parse(expect));
        assertEquals(date, f.withLocale(FRANCE).parseDateTime(expect));
    }

    public void testForStyle_shortTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-S");
        assertSame(g, f);
    }

    public void testForStyle_shortTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-S");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.SHORT, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_shortTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-S");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.SHORT, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_shortTime_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-S");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.SHORT, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.SHORT, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_shortTime_5_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-S");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.SHORT, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.SHORT, FRANCE).format(dt.toDate());
        // removed other assertion
        
        if (TimeZone.getDefault() instanceof SimpleTimeZone) {
            // skip test, as it needs historical time zone info
        } else {
            DateTime date = new DateTime(
                DateFormat.getTimeInstance(DateFormat.SHORT, FRANCE).parse(expect));
            assertEquals(date, f.withLocale(FRANCE).parseDateTime(expect));
    }
    }

    public void testForStyle_shortDateTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("SS");
        assertSame(g, f);
    }

    public void testForStyle_shortDateTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("SS");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_shortDateTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("SS");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_shortDateTime_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("SS");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_shortDateTime_5_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.shortDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("SS");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, FRANCE).format(dt.toDate());
        // removed other assertion
        
        DateTime date = new DateTime(
            DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, FRANCE).parse(expect));
        assertEquals(date, f.withLocale(FRANCE).parseDateTime(expect));
    }

    public void testForStyle_mediumDate_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("M-");
        assertSame(g, f);
    }

    public void testForStyle_mediumDate_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("M-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.MEDIUM, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_mediumDate_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("M-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.MEDIUM, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.MEDIUM, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_mediumDate_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("M-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.MEDIUM, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.MEDIUM, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.MEDIUM, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_mediumTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-M");
        assertSame(g, f);
    }

    public void testForStyle_mediumTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-M");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.MEDIUM, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_mediumTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-M");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.MEDIUM, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.MEDIUM, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_mediumTime_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-M");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.MEDIUM, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.MEDIUM, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.MEDIUM, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_mediumDateTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("MM");
        assertSame(g, f);
    }

    public void testForStyle_mediumDateTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("MM");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_mediumDateTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("MM");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_mediumDateTime_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.mediumDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("MM");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_longDate_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("L-");
        assertSame(g, f);
    }

    public void testForStyle_longDate_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("L-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.LONG, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_longDate_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("L-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.LONG, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.LONG, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_longDate_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("L-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.LONG, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.LONG, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.LONG, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_longTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-L");
        assertSame(g, f);
    }

    public void testForStyle_longTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-L");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.LONG, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_longTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-L");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.LONG, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.LONG, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_longTime_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-L");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.LONG, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.LONG, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.LONG, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_longDateTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("LL");
        assertSame(g, f);
    }

    public void testForStyle_longDateTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("LL");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_longDateTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("LL");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_longDateTime_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.longDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("LL");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_fullDate_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("F-");
        assertSame(g, f);
    }

    public void testForStyle_fullDate_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("F-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.FULL, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_fullDate_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("F-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.FULL, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.FULL, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_fullDate_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullDate();
        DateTimeFormatter g = DateTimeFormat.forStyle("F-");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateInstance(DateFormat.FULL, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.FULL, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateInstance(DateFormat.FULL, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_fullTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-F");
        assertSame(g, f);
    }

    public void testForStyle_fullTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-F");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.FULL, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_fullTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-F");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.FULL, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.FULL, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_fullTime_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("-F");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getTimeInstance(DateFormat.FULL, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.FULL, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getTimeInstance(DateFormat.FULL, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_fullDateTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("FF");
        assertSame(g, f);
    }

    public void testForStyle_fullDateTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("FF");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_fullDateTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("FF");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_fullDateTime_4_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.fullDateTime();
        DateTimeFormatter g = DateTimeFormat.forStyle("FF");
        // removed other assertion
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_shortMediumDateTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("SM");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_shortMediumDateTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("SM");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_shortMediumDateTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("SM");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_shortLongDateTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("SL");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_shortLongDateTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("SL");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_shortLongDateTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("SL");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_shortFullDateTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("SF");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_shortFullDateTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("SF");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_shortFullDateTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("SF");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_mediumShortDateTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("MS");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_mediumShortDateTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("MS");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_mediumShortDateTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("MS");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_mediumLongDateTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("ML");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_mediumLongDateTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("ML");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_mediumLongDateTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("ML");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void testForStyle_mediumFullDateTime_1_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("MF");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.FULL, UK).format(dt.toDate());
        assertEquals(expect, f.print(dt));
    }

    public void testForStyle_mediumFullDateTime_2_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("MF");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.FULL, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.FULL, US).format(dt.toDate());
        assertEquals(expect, f.withLocale(US).print(dt));
    }

    public void testForStyle_mediumFullDateTime_3_oe() throws Exception {
        DateTimeFormatter f = DateTimeFormat.forStyle("MF");
        DateTime dt = new DateTime(2004, 6, 9, 10, 20, 30, 0);
        String expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.FULL, UK).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.FULL, US).format(dt.toDate());
        // removed other assertion
        expect = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.FULL, FRANCE).format(dt.toDate());
        assertEquals(expect, f.withLocale(FRANCE).print(dt));
    }

    public void test_patternForStyle_1_oe() throws Exception {
        String format = DateTimeFormat.patternForStyle("MF", UK);
        assertNotNull(format);
    }

}
