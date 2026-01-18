/*
 *  Copyright 2001-2013 Stephen Colebourne
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
 * This class is a Junit unit test for DateTime.
 *
 * @author Stephen Colebourne
 * @author Mike Schrag
 */
public class TestMutableDateTime_Properties_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    //private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
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
            
    // 2002-04-05 Fri
    private long TEST_TIME1 =
            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 12L * DateTimeConstants.MILLIS_PER_HOUR
            + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    // 2003-05-06 Tue
    private long TEST_TIME2 =
            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 14L * DateTimeConstants.MILLIS_PER_HOUR
            + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    private DateTimeZone zone = null;
    private Locale locale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMutableDateTime_Properties.class);
    }

    public TestMutableDateTime_Properties_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        locale = Locale.getDefault();
        DateTimeZone.setDefault(LONDON);
        Locale.setDefault(Locale.UK);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        Locale.setDefault(locale);
        zone = null;
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testTest_1_oe() {
        assertEquals("2002-06-09T00:00:00.000Z", new Instant(TEST_TIME_NOW).toString());
    }

    public void testTest_2_oe() {
        // removed other assertion
        assertEquals("2002-04-05T12:24:00.000Z", new Instant(TEST_TIME1).toString());
    }

    public void testTest_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("2003-05-06T14:28:00.000Z", new Instant(TEST_TIME2).toString());
    }

    public void testPropertyGetEra_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().era(), test.era().getField());
    }

    public void testPropertyGetEra_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("era", test.era().getName());
    }

    public void testPropertyGetEra_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[era]", test.era().toString());
    }

    public void testPropertyGetEra_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(test, test.era().getMutableDateTime());
    }

    public void testPropertyGetEra_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.era().get());
    }

    public void testPropertyGetEra_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("AD", test.era().getAsText());
    }

    public void testPropertyGetEra_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ap. J.-C.", test.era().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetEra_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("AD", test.era().getAsShortText());
    }

    public void testPropertyGetEra_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ap. J.-C.", test.era().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetEra_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().eras(), test.era().getDurationField());
    }

    public void testPropertyGetEra_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.era().getRangeDurationField());
    }

    public void testPropertyGetEra_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.era().getMaximumTextLength(null));
    }

    public void testPropertyGetEra_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.era().getMaximumTextLength(Locale.FRENCH));
    }

    public void testPropertyGetEra_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.era().getMaximumShortTextLength(null));
    }

    public void testPropertyGetEra_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.era().getMaximumShortTextLength(Locale.FRENCH));
    }

    public void testPropertyGetYearOfEra_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().yearOfEra(), test.yearOfEra().getField());
    }

    public void testPropertyGetYearOfEra_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("yearOfEra", test.yearOfEra().getName());
    }

    public void testPropertyGetYearOfEra_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[yearOfEra]", test.yearOfEra().toString());
    }

    public void testPropertyGetYearOfEra_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2004, test.yearOfEra().get());
    }

    public void testPropertyGetYearOfEra_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.yearOfEra().getAsText());
    }

    public void testPropertyGetYearOfEra_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.yearOfEra().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetYearOfEra_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.yearOfEra().getAsShortText());
    }

    public void testPropertyGetYearOfEra_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.yearOfEra().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetYearOfEra_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().years(), test.yearOfEra().getDurationField());
    }

    public void testPropertyGetYearOfEra_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().eras(), test.yearOfEra().getRangeDurationField());
    }

    public void testPropertyGetYearOfEra_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.yearOfEra().getMaximumTextLength(null));
    }

    public void testPropertyGetYearOfEra_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.yearOfEra().getMaximumShortTextLength(null));
    }

    public void testPropertyGetCenturyOfEra_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().centuryOfEra(), test.centuryOfEra().getField());
    }

    public void testPropertyGetCenturyOfEra_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("centuryOfEra", test.centuryOfEra().getName());
    }

    public void testPropertyGetCenturyOfEra_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[centuryOfEra]", test.centuryOfEra().toString());
    }

    public void testPropertyGetCenturyOfEra_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(20, test.centuryOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("20", test.centuryOfEra().getAsText());
    }

    public void testPropertyGetCenturyOfEra_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("20", test.centuryOfEra().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetCenturyOfEra_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("20", test.centuryOfEra().getAsShortText());
    }

    public void testPropertyGetCenturyOfEra_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("20", test.centuryOfEra().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetCenturyOfEra_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().centuries(), test.centuryOfEra().getDurationField());
    }

    public void testPropertyGetCenturyOfEra_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().eras(), test.centuryOfEra().getRangeDurationField());
    }

    public void testPropertyGetCenturyOfEra_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.centuryOfEra().getMaximumTextLength(null));
    }

    public void testPropertyGetCenturyOfEra_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.centuryOfEra().getMaximumShortTextLength(null));
    }

    public void testPropertyGetYearOfCentury_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().yearOfCentury(), test.yearOfCentury().getField());
    }

    public void testPropertyGetYearOfCentury_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("yearOfCentury", test.yearOfCentury().getName());
    }

    public void testPropertyGetYearOfCentury_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[yearOfCentury]", test.yearOfCentury().toString());
    }

    public void testPropertyGetYearOfCentury_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.yearOfCentury().get());
    }

    public void testPropertyGetYearOfCentury_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", test.yearOfCentury().getAsText());
    }

    public void testPropertyGetYearOfCentury_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", test.yearOfCentury().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetYearOfCentury_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", test.yearOfCentury().getAsShortText());
    }

    public void testPropertyGetYearOfCentury_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", test.yearOfCentury().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetYearOfCentury_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().years(), test.yearOfCentury().getDurationField());
    }

    public void testPropertyGetYearOfCentury_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().centuries(), test.yearOfCentury().getRangeDurationField());
    }

    public void testPropertyGetYearOfCentury_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.yearOfCentury().getMaximumTextLength(null));
    }

    public void testPropertyGetYearOfCentury_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.yearOfCentury().getMaximumShortTextLength(null));
    }

    public void testPropertyGetWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().weekyear(), test.weekyear().getField());
    }

    public void testPropertyGetWeekyear_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("weekyear", test.weekyear().getName());
    }

    public void testPropertyGetWeekyear_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[weekyear]", test.weekyear().toString());
    }

    public void testPropertyGetWeekyear_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2004, test.weekyear().get());
    }

    public void testPropertyGetWeekyear_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.weekyear().getAsText());
    }

    public void testPropertyGetWeekyear_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.weekyear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetWeekyear_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.weekyear().getAsShortText());
    }

    public void testPropertyGetWeekyear_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.weekyear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetWeekyear_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().weekyears(), test.weekyear().getDurationField());
    }

    public void testPropertyGetWeekyear_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.weekyear().getRangeDurationField());
    }

    public void testPropertyGetWeekyear_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.weekyear().getMaximumTextLength(null));
    }

    public void testPropertyGetWeekyear_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.weekyear().getMaximumShortTextLength(null));
    }

    public void testPropertyGetYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().year(), test.year().getField());
    }

    public void testPropertyGetYear_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("year", test.year().getName());
    }

    public void testPropertyGetYear_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[year]", test.year().toString());
    }

    public void testPropertyGetYear_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2004, test.year().get());
    }

    public void testPropertyGetYear_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.year().getAsText());
    }

    public void testPropertyGetYear_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.year().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetYear_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.year().getAsShortText());
    }

    public void testPropertyGetYear_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2004", test.year().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetYear_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().years(), test.year().getDurationField());
    }

    public void testPropertyGetYear_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.year().getRangeDurationField());
    }

    public void testPropertyGetYear_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.year().getMaximumTextLength(null));
    }

    public void testPropertyGetYear_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.year().getMaximumShortTextLength(null));
    }

    public void testPropertyGetYear_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-292275054, test.year().getMinimumValue());
    }

    public void testPropertyGetYear_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-292275054, test.year().getMinimumValueOverall());
    }

    public void testPropertyGetYear_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(292278993, test.year().getMaximumValue());
    }

    public void testPropertyGetYear_16_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(292278993, test.year().getMaximumValueOverall());
    }

    public void testPropertyAddYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().add(9);
        assertEquals("2013-06-09T00:00:00.000+01:00", test.toString());
    }

    public void testPropertyAddWrapFieldYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().addWrapField(9);
        assertEquals("2013-06-09T00:00:00.000+01:00", test.toString());
    }

    public void testPropertySetYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().set(1960);
        assertEquals("1960-06-09T00:00:00.000+01:00", test.toString());
    }

    public void testPropertySetTextYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.year().set("1960");
        assertEquals("1960-06-09T00:00:00.000+01:00", test.toString());
    }

    public void testPropertyGetMonthOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().monthOfYear(), test.monthOfYear().getField());
    }

    public void testPropertyGetMonthOfYear_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("monthOfYear", test.monthOfYear().getName());
    }

    public void testPropertyGetMonthOfYear_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[monthOfYear]", test.monthOfYear().toString());
    }

    public void testPropertyGetMonthOfYear_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("June", test.monthOfYear().getAsText());
    }

    public void testPropertyGetMonthOfYear_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("juin", test.monthOfYear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMonthOfYear_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Jun", test.monthOfYear().getAsShortText());
    }

    public void testPropertyGetMonthOfYear_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("juin", test.monthOfYear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMonthOfYear_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().months(), test.monthOfYear().getDurationField());
    }

    public void testPropertyGetMonthOfYear_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().years(), test.monthOfYear().getRangeDurationField());
    }

    public void testPropertyGetMonthOfYear_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.monthOfYear().getMaximumTextLength(null));
    }

    public void testPropertyGetMonthOfYear_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.monthOfYear().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMonthOfYear_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        assertEquals("juillet", test.monthOfYear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMonthOfYear_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("juil.", test.monthOfYear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMonthOfYear_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.monthOfYear().getMinimumValue());
    }

    public void testPropertyGetMonthOfYear_16_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.monthOfYear().getMinimumValueOverall());
    }

    public void testPropertyGetMonthOfYear_17_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12, test.monthOfYear().getMaximumValue());
    }

    public void testPropertyGetMonthOfYear_18_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12, test.monthOfYear().getMaximumValueOverall());
    }

    public void testPropertyGetMonthOfYear_19_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.monthOfYear().getMinimumValue());
    }

    public void testPropertyGetMonthOfYear_20_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.monthOfYear().getMinimumValueOverall());
    }

    public void testPropertyGetMonthOfYear_21_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12, test.monthOfYear().getMaximumValue());
    }

    public void testPropertyGetMonthOfYear_22_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test = new MutableDateTime(2004, 7, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12, test.monthOfYear().getMaximumValueOverall());
    }

    public void testPropertyAddMonthOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().add(6);
        assertEquals("2004-12-09T00:00:00.000Z", test.toString());
    }

    public void testPropertyAddWrapFieldMonthOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().addWrapField(8);
        assertEquals("2004-02-09T00:00:00.000Z", test.toString());
    }

    public void testPropertySetMonthOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set(12);
        assertEquals("2004-12-09T00:00:00.000Z", test.toString());
    }

    public void testPropertySetTextMonthOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("12");
        assertEquals("2004-12-09T00:00:00.000Z", test.toString());
    }

    public void testPropertySetTextMonthOfYear_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("12");
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("December");
        assertEquals("2004-12-09T00:00:00.000Z", test.toString());
    }

    public void testPropertySetTextMonthOfYear_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("12");
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("December");
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.monthOfYear().set("Dec");
        assertEquals("2004-12-09T00:00:00.000Z", test.toString());
    }

    public void testPropertyGetDayOfMonth_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().dayOfMonth(), test.dayOfMonth().getField());
    }

    public void testPropertyGetDayOfMonth_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("dayOfMonth", test.dayOfMonth().getName());
    }

    public void testPropertyGetDayOfMonth_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[dayOfMonth]", test.dayOfMonth().toString());
    }

    public void testPropertyGetDayOfMonth_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("9", test.dayOfMonth().getAsText());
    }

    public void testPropertyGetDayOfMonth_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("9", test.dayOfMonth().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfMonth_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("9", test.dayOfMonth().getAsShortText());
    }

    public void testPropertyGetDayOfMonth_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("9", test.dayOfMonth().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfMonth_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().days(), test.dayOfMonth().getDurationField());
    }

    public void testPropertyGetDayOfMonth_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().months(), test.dayOfMonth().getRangeDurationField());
    }

    public void testPropertyGetDayOfMonth_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.dayOfMonth().getMaximumTextLength(null));
    }

    public void testPropertyGetDayOfMonth_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.dayOfMonth().getMaximumShortTextLength(null));
    }

    public void testPropertyGetDayOfMonth_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.dayOfMonth().getMinimumValue());
    }

    public void testPropertyGetDayOfMonth_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.dayOfMonth().getMinimumValueOverall());
    }

    public void testPropertyGetDayOfMonth_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(30, test.dayOfMonth().getMaximumValue());
    }

    public void testPropertyGetDayOfMonth_16_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(31, test.dayOfMonth().getMaximumValueOverall());
    }

    public void testPropertyGetDayOfMonth_17_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.dayOfMonth().isLeap());
    }

    public void testPropertyGetDayOfMonth_18_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.dayOfMonth().getLeapAmount());
    }

    public void testPropertyGetDayOfMonth_19_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.dayOfMonth().getLeapDurationField());
    }

    public void testPropertyAddDayOfMonth_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().add(9);
        assertEquals("2004-06-18T00:00:00.000+01:00", test.toString());
    }

    public void testPropertyAddWrapFieldDayOfMonth_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().addWrapField(22);
        assertEquals("2004-06-01T00:00:00.000+01:00", test.toString());
    }

    public void testPropertySetDayOfMonth_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().set(12);
        assertEquals("2004-06-12T00:00:00.000+01:00", test.toString());
    }

    public void testPropertySetTextDayOfMonth_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfMonth().set("12");
        assertEquals("2004-06-12T00:00:00.000+01:00", test.toString());
    }

    public void testPropertyGetDayOfYear_1_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().dayOfYear(), test.dayOfYear().getField());
    }

    public void testPropertyGetDayOfYear_2_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("dayOfYear", test.dayOfYear().getName());
    }

    public void testPropertyGetDayOfYear_3_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[dayOfYear]", test.dayOfYear().toString());
    }

    public void testPropertyGetDayOfYear_4_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(161, test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_5_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("161", test.dayOfYear().getAsText());
    }

    public void testPropertyGetDayOfYear_6_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("161", test.dayOfYear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfYear_7_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("161", test.dayOfYear().getAsShortText());
    }

    public void testPropertyGetDayOfYear_8_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("161", test.dayOfYear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfYear_9_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().days(), test.dayOfYear().getDurationField());
    }

    public void testPropertyGetDayOfYear_10_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().years(), test.dayOfYear().getRangeDurationField());
    }

    public void testPropertyGetDayOfYear_11_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.dayOfYear().getMaximumTextLength(null));
    }

    public void testPropertyGetDayOfYear_12_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.dayOfYear().getMaximumShortTextLength(null));
    }

    public void testPropertyGetDayOfYear_13_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.dayOfYear().isLeap());
    }

    public void testPropertyGetDayOfYear_14_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.dayOfYear().getLeapAmount());
    }

    public void testPropertyGetDayOfYear_15_oe() {
        // 31+29+31+30+31+9 = 161
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.dayOfYear().getLeapDurationField());
    }

    public void testPropertyAddDayOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().add(9);
        assertEquals("2004-06-18T00:00:00.000+01:00", test.toString());
    }

    public void testPropertyAddWrapFieldDayOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().addWrapField(206);
        assertEquals("2004-01-01T00:00:00.000Z", test.toString());
    }

    public void testPropertySetDayOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().set(12);
        assertEquals("2004-01-12T00:00:00.000Z", test.toString());
    }

    public void testPropertySetTextDayOfYear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfYear().set("12");
        assertEquals("2004-01-12T00:00:00.000Z", test.toString());
    }

    public void testPropertyGetWeekOfWeekyear_1_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().weekOfWeekyear(), test.weekOfWeekyear().getField());
    }

    public void testPropertyGetWeekOfWeekyear_2_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("weekOfWeekyear", test.weekOfWeekyear().getName());
    }

    public void testPropertyGetWeekOfWeekyear_3_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[weekOfWeekyear]", test.weekOfWeekyear().toString());
    }

    public void testPropertyGetWeekOfWeekyear_4_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(24, test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_5_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("24", test.weekOfWeekyear().getAsText());
    }

    public void testPropertyGetWeekOfWeekyear_6_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("24", test.weekOfWeekyear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetWeekOfWeekyear_7_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("24", test.weekOfWeekyear().getAsShortText());
    }

    public void testPropertyGetWeekOfWeekyear_8_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("24", test.weekOfWeekyear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetWeekOfWeekyear_9_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().weeks(), test.weekOfWeekyear().getDurationField());
    }

    public void testPropertyGetWeekOfWeekyear_10_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().weekyears(), test.weekOfWeekyear().getRangeDurationField());
    }

    public void testPropertyGetWeekOfWeekyear_11_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.weekOfWeekyear().getMaximumTextLength(null));
    }

    public void testPropertyGetWeekOfWeekyear_12_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.weekOfWeekyear().getMaximumShortTextLength(null));
    }

    public void testPropertyGetWeekOfWeekyear_13_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.weekOfWeekyear().isLeap());
    }

    public void testPropertyGetWeekOfWeekyear_14_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.weekOfWeekyear().getLeapAmount());
    }

    public void testPropertyGetWeekOfWeekyear_15_oe() {
        // 2002-01-01 = Thu
        // 2002-12-31 = Thu (+364 days)
        // 2003-12-30 = Thu (+364 days)
        // 2004-01-03 = Mon             W1
        // 2004-01-31 = Mon (+28 days)  W5
        // 2004-02-28 = Mon (+28 days)  W9
        // 2004-03-27 = Mon (+28 days)  W13
        // 2004-04-24 = Mon (+28 days)  W17
        // 2004-05-23 = Mon (+28 days)  W21
        // 2004-06-05 = Mon (+14 days)  W23
        // 2004-06-09 = Fri
        // 2004-12-25 = Mon             W52
        // 2005-01-01 = Mon             W1
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.weekOfWeekyear().getLeapDurationField());
    }

    public void testPropertyAddWeekOfWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().add(1);
        assertEquals("2004-06-14T00:00:00.000+01:00", test.toString());
    }

    public void testPropertyAddWrapFieldWeekOfWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().addWrapField(30);
        assertEquals("2003-12-29T00:00:00.000Z", test.toString());
    }

    public void testPropertySetWeekOfWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().set(4);
        assertEquals("2004-01-19T00:00:00.000Z", test.toString());
    }

    public void testPropertySetTextWeekOfWeekyear_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 7, 0, 0, 0, 0);
        test.weekOfWeekyear().set("4");
        assertEquals("2004-01-19T00:00:00.000Z", test.toString());
    }

    public void testPropertyGetDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        assertSame(test.getChronology().dayOfWeek(), test.dayOfWeek().getField());
    }

    public void testPropertyGetDayOfWeek_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("dayOfWeek", test.dayOfWeek().getName());
    }

    public void testPropertyGetDayOfWeek_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[dayOfWeek]", test.dayOfWeek().toString());
    }

    public void testPropertyGetDayOfWeek_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.dayOfWeek().get());
    }

    public void testPropertyGetDayOfWeek_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wednesday", test.dayOfWeek().getAsText());
    }

    public void testPropertyGetDayOfWeek_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("mercredi", test.dayOfWeek().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfWeek_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wed", test.dayOfWeek().getAsShortText());
    }

    public void testPropertyGetDayOfWeek_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("mer.", test.dayOfWeek().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfWeek_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().days(), test.dayOfWeek().getDurationField());
    }

    public void testPropertyGetDayOfWeek_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().weeks(), test.dayOfWeek().getRangeDurationField());
    }

    public void testPropertyGetDayOfWeek_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.dayOfWeek().getMaximumTextLength(null));
    }

    public void testPropertyGetDayOfWeek_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.dayOfWeek().getMaximumTextLength(Locale.FRENCH));
    }

    public void testPropertyGetDayOfWeek_13_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.dayOfWeek().getMaximumShortTextLength(null));
    }

    public void testPropertyGetDayOfWeek_14_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.dayOfWeek().getMaximumShortTextLength(Locale.FRENCH));
    }

    public void testPropertyGetDayOfWeek_15_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.dayOfWeek().getMinimumValue());
    }

    public void testPropertyGetDayOfWeek_16_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.dayOfWeek().getMinimumValueOverall());
    }

    public void testPropertyGetDayOfWeek_17_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.dayOfWeek().getMaximumValue());
    }

    public void testPropertyGetDayOfWeek_18_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, test.dayOfWeek().getMaximumValueOverall());
    }

    public void testPropertyGetDayOfWeek_19_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.dayOfWeek().isLeap());
    }

    public void testPropertyGetDayOfWeek_20_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test.dayOfWeek().getLeapAmount());
    }

    public void testPropertyGetDayOfWeek_21_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.dayOfWeek().getLeapDurationField());
    }

    public void testPropertyAddDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().add(1);
        assertEquals("2004-06-10T00:00:00.000+01:00", test.toString());
    }

    public void testPropertyAddLongDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().add(1L);
        assertEquals("2004-06-10T00:00:00.000+01:00", test.toString());
    }

    public void testPropertyAddWrapFieldDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);  // Wed
        test.dayOfWeek().addWrapField(5);
        assertEquals("2004-06-07T00:00:00.000+01:00", test.toString());
    }

    public void testPropertySetDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set(4);
        assertEquals("2004-06-10T00:00:00.000+01:00", test.toString());
    }

    public void testPropertySetTextDayOfWeek_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("4");
        assertEquals("2004-06-10T00:00:00.000+01:00", test.toString());
    }

    public void testPropertySetTextDayOfWeek_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("4");
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Mon");
        assertEquals("2004-06-07T00:00:00.000+01:00", test.toString());
    }

    public void testPropertySetTextDayOfWeek_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("4");
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Mon");
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Tuesday");
        assertEquals("2004-06-08T00:00:00.000+01:00", test.toString());
    }

    public void testPropertySetTextDayOfWeek_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("4");
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Mon");
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("Tuesday");
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 0, 0, 0, 0);
        test.dayOfWeek().set("lundi", Locale.FRENCH);
        assertEquals("2004-06-07T00:00:00.000+01:00", test.toString());
    }

    public void testPropertyGetHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().hourOfDay(), test.hourOfDay().getField());
    }

    public void testPropertyGetHourOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        assertEquals("hourOfDay", test.hourOfDay().getName());
    }

    public void testPropertyGetHourOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[hourOfDay]", test.hourOfDay().toString());
    }

    public void testPropertyGetHourOfDay_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(13, test.hourOfDay().get());
    }

    public void testPropertyGetHourOfDay_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("13", test.hourOfDay().getAsText());
    }

    public void testPropertyGetHourOfDay_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("13", test.hourOfDay().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetHourOfDay_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("13", test.hourOfDay().getAsShortText());
    }

    public void testPropertyGetHourOfDay_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("13", test.hourOfDay().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetHourOfDay_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().hours(), test.hourOfDay().getDurationField());
    }

    public void testPropertyGetHourOfDay_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().days(), test.hourOfDay().getRangeDurationField());
    }

    public void testPropertyGetHourOfDay_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.hourOfDay().getMaximumTextLength(null));
    }

    public void testPropertyGetHourOfDay_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.hourOfDay().getMaximumShortTextLength(null));
    }

    public void testPropertyRoundFloorHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundFloor();
        assertEquals("2004-06-09T13:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundCeilingHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundCeiling();
        assertEquals("2004-06-09T14:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundHalfFloorHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfFloor();
        assertEquals("2004-06-09T13:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundHalfFloorHourOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfFloor();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfFloor();
        assertEquals("2004-06-09T14:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundHalfFloorHourOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfFloor();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfFloor();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 13, 29, 59, 999);
        test.hourOfDay().roundHalfFloor();
        assertEquals("2004-06-09T13:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundHalfCeilingHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfCeiling();
        assertEquals("2004-06-09T14:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundHalfCeilingHourOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfCeiling();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfCeiling();
        assertEquals("2004-06-09T14:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundHalfCeilingHourOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfCeiling();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfCeiling();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 13, 29, 59, 999);
        test.hourOfDay().roundHalfCeiling();
        assertEquals("2004-06-09T13:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundHalfEvenHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        assertEquals("2004-06-09T14:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundHalfEvenHourOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 14, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        assertEquals("2004-06-09T14:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundHalfEvenHourOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 14, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfEven();
        assertEquals("2004-06-09T14:00:00.000+01:00", test.toString());
    }

    public void testPropertyRoundHalfEvenHourOfDay_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 14, 30, 0, 0);
        test.hourOfDay().roundHalfEven();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 1);
        test.hourOfDay().roundHalfEven();
        // removed other assertion
        
        test = new MutableDateTime(2004, 6, 9, 13, 29, 59, 999);
        test.hourOfDay().roundHalfEven();
        assertEquals("2004-06-09T13:00:00.000+01:00", test.toString());
    }

    public void testPropertyRemainderHourOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 30, 0, 0);
        assertEquals(30L * DateTimeConstants.MILLIS_PER_MINUTE, test.hourOfDay().remainder());
    }

    public void testPropertyGetMinuteOfHour_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().minuteOfHour(), test.minuteOfHour().getField());
    }

    public void testPropertyGetMinuteOfHour_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        assertEquals("minuteOfHour", test.minuteOfHour().getName());
    }

    public void testPropertyGetMinuteOfHour_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[minuteOfHour]", test.minuteOfHour().toString());
    }

    public void testPropertyGetMinuteOfHour_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(23, test.minuteOfHour().get());
    }

    public void testPropertyGetMinuteOfHour_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("23", test.minuteOfHour().getAsText());
    }

    public void testPropertyGetMinuteOfHour_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("23", test.minuteOfHour().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMinuteOfHour_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("23", test.minuteOfHour().getAsShortText());
    }

    public void testPropertyGetMinuteOfHour_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("23", test.minuteOfHour().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMinuteOfHour_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().minutes(), test.minuteOfHour().getDurationField());
    }

    public void testPropertyGetMinuteOfHour_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().hours(), test.minuteOfHour().getRangeDurationField());
    }

    public void testPropertyGetMinuteOfHour_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.minuteOfHour().getMaximumTextLength(null));
    }

    public void testPropertyGetMinuteOfHour_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.minuteOfHour().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMinuteOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().minuteOfDay(), test.minuteOfDay().getField());
    }

    public void testPropertyGetMinuteOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        assertEquals("minuteOfDay", test.minuteOfDay().getName());
    }

    public void testPropertyGetMinuteOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[minuteOfDay]", test.minuteOfDay().toString());
    }

    public void testPropertyGetMinuteOfDay_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(803, test.minuteOfDay().get());
    }

    public void testPropertyGetMinuteOfDay_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("803", test.minuteOfDay().getAsText());
    }

    public void testPropertyGetMinuteOfDay_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("803", test.minuteOfDay().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMinuteOfDay_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("803", test.minuteOfDay().getAsShortText());
    }

    public void testPropertyGetMinuteOfDay_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("803", test.minuteOfDay().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMinuteOfDay_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().minutes(), test.minuteOfDay().getDurationField());
    }

    public void testPropertyGetMinuteOfDay_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().days(), test.minuteOfDay().getRangeDurationField());
    }

    public void testPropertyGetMinuteOfDay_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.minuteOfDay().getMaximumTextLength(null));
    }

    public void testPropertyGetMinuteOfDay_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, test.minuteOfDay().getMaximumShortTextLength(null));
    }

    public void testPropertyGetSecondOfMinute_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().secondOfMinute(), test.secondOfMinute().getField());
    }

    public void testPropertyGetSecondOfMinute_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        assertEquals("secondOfMinute", test.secondOfMinute().getName());
    }

    public void testPropertyGetSecondOfMinute_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[secondOfMinute]", test.secondOfMinute().toString());
    }

    public void testPropertyGetSecondOfMinute_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(43, test.secondOfMinute().get());
    }

    public void testPropertyGetSecondOfMinute_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("43", test.secondOfMinute().getAsText());
    }

    public void testPropertyGetSecondOfMinute_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("43", test.secondOfMinute().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetSecondOfMinute_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("43", test.secondOfMinute().getAsShortText());
    }

    public void testPropertyGetSecondOfMinute_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("43", test.secondOfMinute().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetSecondOfMinute_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().seconds(), test.secondOfMinute().getDurationField());
    }

    public void testPropertyGetSecondOfMinute_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().minutes(), test.secondOfMinute().getRangeDurationField());
    }

    public void testPropertyGetSecondOfMinute_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.secondOfMinute().getMaximumTextLength(null));
    }

    public void testPropertyGetSecondOfMinute_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.secondOfMinute().getMaximumShortTextLength(null));
    }

    public void testPropertyGetSecondOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().secondOfDay(), test.secondOfDay().getField());
    }

    public void testPropertyGetSecondOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        assertEquals("secondOfDay", test.secondOfDay().getName());
    }

    public void testPropertyGetSecondOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[secondOfDay]", test.secondOfDay().toString());
    }

    public void testPropertyGetSecondOfDay_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(48223, test.secondOfDay().get());
    }

    public void testPropertyGetSecondOfDay_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("48223", test.secondOfDay().getAsText());
    }

    public void testPropertyGetSecondOfDay_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("48223", test.secondOfDay().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetSecondOfDay_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("48223", test.secondOfDay().getAsShortText());
    }

    public void testPropertyGetSecondOfDay_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("48223", test.secondOfDay().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetSecondOfDay_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().seconds(), test.secondOfDay().getDurationField());
    }

    public void testPropertyGetSecondOfDay_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().days(), test.secondOfDay().getRangeDurationField());
    }

    public void testPropertyGetSecondOfDay_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.secondOfDay().getMaximumTextLength(null));
    }

    public void testPropertyGetSecondOfDay_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, test.secondOfDay().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMillisOfSecond_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().millisOfSecond(), test.millisOfSecond().getField());
    }

    public void testPropertyGetMillisOfSecond_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        assertEquals("millisOfSecond", test.millisOfSecond().getName());
    }

    public void testPropertyGetMillisOfSecond_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[millisOfSecond]", test.millisOfSecond().toString());
    }

    public void testPropertyGetMillisOfSecond_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(53, test.millisOfSecond().get());
    }

    public void testPropertyGetMillisOfSecond_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("53", test.millisOfSecond().getAsText());
    }

    public void testPropertyGetMillisOfSecond_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("53", test.millisOfSecond().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMillisOfSecond_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("53", test.millisOfSecond().getAsShortText());
    }

    public void testPropertyGetMillisOfSecond_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("53", test.millisOfSecond().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMillisOfSecond_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().millis(), test.millisOfSecond().getDurationField());
    }

    public void testPropertyGetMillisOfSecond_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().seconds(), test.millisOfSecond().getRangeDurationField());
    }

    public void testPropertyGetMillisOfSecond_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.millisOfSecond().getMaximumTextLength(null));
    }

    public void testPropertyGetMillisOfSecond_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.millisOfSecond().getMaximumShortTextLength(null));
    }

    public void testPropertyGetMillisOfDay_1_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        assertSame(test.getChronology().millisOfDay(), test.millisOfDay().getField());
    }

    public void testPropertyGetMillisOfDay_2_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        assertEquals("millisOfDay", test.millisOfDay().getName());
    }

    public void testPropertyGetMillisOfDay_3_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        assertEquals("Property[millisOfDay]", test.millisOfDay().toString());
    }

    public void testPropertyGetMillisOfDay_4_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(48223053, test.millisOfDay().get());
    }

    public void testPropertyGetMillisOfDay_5_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("48223053", test.millisOfDay().getAsText());
    }

    public void testPropertyGetMillisOfDay_6_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("48223053", test.millisOfDay().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMillisOfDay_7_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("48223053", test.millisOfDay().getAsShortText());
    }

    public void testPropertyGetMillisOfDay_8_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("48223053", test.millisOfDay().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMillisOfDay_9_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().millis(), test.millisOfDay().getDurationField());
    }

    public void testPropertyGetMillisOfDay_10_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test.getChronology().days(), test.millisOfDay().getRangeDurationField());
    }

    public void testPropertyGetMillisOfDay_11_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.millisOfDay().getMaximumTextLength(null));
    }

    public void testPropertyGetMillisOfDay_12_oe() {
        MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, test.millisOfDay().getMaximumShortTextLength(null));
    }

    public void testPropertyToIntervalYearOfEra_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfEra().toInterval();
      assertEquals(new MutableDateTime(2004, 1, 1, 0, 0, 0, 0), testInterval.getStart());
    }

    public void testPropertyToIntervalYearOfEra_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfEra().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2005, 1, 1, 0, 0, 0, 0), testInterval.getEnd());
    }

    public void testPropertyToIntervalYearOfEra_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfEra().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 53), test);
    }

    public void testPropertyToIntervalYearOfCentury_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfCentury().toInterval();
      assertEquals(new MutableDateTime(2004, 1, 1, 0, 0, 0, 0), testInterval.getStart());
    }

    public void testPropertyToIntervalYearOfCentury_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfCentury().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2005, 1, 1, 0, 0, 0, 0), testInterval.getEnd());
    }

    public void testPropertyToIntervalYearOfCentury_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.yearOfCentury().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 53), test);
    }

    public void testPropertyToIntervalYear_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.year().toInterval();
      assertEquals(new MutableDateTime(2004, 1, 1, 0, 0, 0, 0), testInterval.getStart());
    }

    public void testPropertyToIntervalYear_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.year().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2005, 1, 1, 0, 0, 0, 0), testInterval.getEnd());
    }

    public void testPropertyToIntervalYear_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.year().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 53), test);
    }

    public void testPropertyToIntervalMonthOfYear_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.monthOfYear().toInterval();
      assertEquals(new MutableDateTime(2004, 6, 1, 0, 0, 0, 0), testInterval.getStart());
    }

    public void testPropertyToIntervalMonthOfYear_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.monthOfYear().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 7, 1, 0, 0, 0, 0), testInterval.getEnd());
    }

    public void testPropertyToIntervalMonthOfYear_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.monthOfYear().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 53), test);
    }

    public void testPropertyToIntervalDayOfMonth_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();
      assertEquals(new MutableDateTime(2004, 6, 9, 0, 0, 0, 0), testInterval.getStart());
    }

    public void testPropertyToIntervalDayOfMonth_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 10, 0, 0, 0, 0), testInterval.getEnd());
    }

    public void testPropertyToIntervalDayOfMonth_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 53), test);
    }

    public void testPropertyToIntervalDayOfMonth_4_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();
      // removed other assertion
      // removed other assertion
      // removed other assertion

      MutableDateTime febTest = new MutableDateTime(2004, 2, 29, 13, 23, 43, 53);
      Interval febTestInterval = febTest.dayOfMonth().toInterval();
      assertEquals(new MutableDateTime(2004, 2, 29, 0, 0, 0, 0), febTestInterval.getStart());
    }

    public void testPropertyToIntervalDayOfMonth_5_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();
      // removed other assertion
      // removed other assertion
      // removed other assertion

      MutableDateTime febTest = new MutableDateTime(2004, 2, 29, 13, 23, 43, 53);
      Interval febTestInterval = febTest.dayOfMonth().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 3, 1, 0, 0, 0, 0), febTestInterval.getEnd());
    }

    public void testPropertyToIntervalDayOfMonth_6_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.dayOfMonth().toInterval();
      // removed other assertion
      // removed other assertion
      // removed other assertion

      MutableDateTime febTest = new MutableDateTime(2004, 2, 29, 13, 23, 43, 53);
      Interval febTestInterval = febTest.dayOfMonth().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 2, 29, 13, 23, 43, 53), febTest);
    }

    public void testPropertyToIntervalHourOfDay_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 0, 0, 0), testInterval.getStart());
    }

    public void testPropertyToIntervalHourOfDay_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 14, 0, 0, 0), testInterval.getEnd());
    }

    public void testPropertyToIntervalHourOfDay_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 53), test);
    }

    public void testPropertyToIntervalHourOfDay_4_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();
      // removed other assertion
      // removed other assertion
      // removed other assertion

      MutableDateTime midnightTest = new MutableDateTime(2004, 6, 9, 23, 23, 43, 53);
      Interval midnightTestInterval = midnightTest.hourOfDay().toInterval();
      assertEquals(new MutableDateTime(2004, 6, 9, 23, 0, 0, 0), midnightTestInterval.getStart());
    }

    public void testPropertyToIntervalHourOfDay_5_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();
      // removed other assertion
      // removed other assertion
      // removed other assertion

      MutableDateTime midnightTest = new MutableDateTime(2004, 6, 9, 23, 23, 43, 53);
      Interval midnightTestInterval = midnightTest.hourOfDay().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 10, 0, 0, 0, 0), midnightTestInterval.getEnd());
    }

    public void testPropertyToIntervalHourOfDay_6_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.hourOfDay().toInterval();
      // removed other assertion
      // removed other assertion
      // removed other assertion

      MutableDateTime midnightTest = new MutableDateTime(2004, 6, 9, 23, 23, 43, 53);
      Interval midnightTestInterval = midnightTest.hourOfDay().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 23, 23, 43, 53), midnightTest);
    }

    public void testPropertyToIntervalMinuteOfHour_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.minuteOfHour().toInterval();
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 0, 0), testInterval.getStart());
    }

    public void testPropertyToIntervalMinuteOfHour_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.minuteOfHour().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 24, 0, 0), testInterval.getEnd());
    }

    public void testPropertyToIntervalMinuteOfHour_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.minuteOfHour().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 53), test);
    }

    public void testPropertyToIntervalSecondOfMinute_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.secondOfMinute().toInterval();
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 0), testInterval.getStart());
    }

    public void testPropertyToIntervalSecondOfMinute_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.secondOfMinute().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 44, 0), testInterval.getEnd());
    }

    public void testPropertyToIntervalSecondOfMinute_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.secondOfMinute().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 53), test);
    }

    public void testPropertyToIntervalMillisOfSecond_1_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.millisOfSecond().toInterval();
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 53), testInterval.getStart());
    }

    public void testPropertyToIntervalMillisOfSecond_2_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.millisOfSecond().toInterval();
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 54), testInterval.getEnd());
    }

    public void testPropertyToIntervalMillisOfSecond_3_oe() {
      MutableDateTime test = new MutableDateTime(2004, 6, 9, 13, 23, 43, 53);
      Interval testInterval = test.millisOfSecond().toInterval();
      // removed other assertion
      // removed other assertion
      assertEquals(new MutableDateTime(2004, 6, 9, 13, 23, 43, 53), test);
    }

}
