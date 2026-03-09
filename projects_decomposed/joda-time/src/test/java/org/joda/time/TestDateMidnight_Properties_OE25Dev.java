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

import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.LenientChronology;
import org.joda.time.chrono.StrictChronology;

/**
 * This class is a Junit unit test for DateTime.
 *
 * @author Stephen Colebourne
 * @author Mike Schrag
 */
@SuppressWarnings("deprecation")
public class TestDateMidnight_Properties_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);

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
        return new TestSuite(TestDateMidnight_Properties_OE25Dev.class);
    }

    public TestDateMidnight_Properties_OE25Dev(String name) {
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
    public void testTest() {
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW).toString());
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1).toString());
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2).toString());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetEra() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().era(),test.era().getField());
        assertEquals("era",test.era().getName());
        assertEquals("Property[era]",test.era().toString());
        assertSame(test,test.era().getDateMidnight());
        assertEquals(1,test.era().get());
        assertEquals("AD",test.era().getAsText());
        assertEquals("ap. J.-C.",test.era().getAsText(Locale.FRENCH));
        assertEquals("AD",test.era().getAsShortText());
        assertEquals("ap. J.-C.",test.era().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().eras(),test.era().getDurationField());
        assertEquals(null,test.era().getRangeDurationField());
        assertEquals(2,test.era().getMaximumTextLength(null));
        assertEquals(9,test.era().getMaximumTextLength(Locale.FRENCH));
        assertEquals(2,test.era().getMaximumShortTextLength(null));
        assertEquals(9,test.era().getMaximumShortTextLength(Locale.FRENCH));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetYearOfEra() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().yearOfEra(),test.yearOfEra().getField());
        assertEquals("yearOfEra",test.yearOfEra().getName());
        assertEquals("Property[yearOfEra]",test.yearOfEra().toString());
        assertSame(test,test.yearOfEra().getDateMidnight());
        assertEquals(2004,test.yearOfEra().get());
        assertEquals("2004",test.yearOfEra().getAsText());
        assertEquals("2004",test.yearOfEra().getAsText(Locale.FRENCH));
        assertEquals("2004",test.yearOfEra().getAsShortText());
        assertEquals("2004",test.yearOfEra().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().years(),test.yearOfEra().getDurationField());
        assertEquals(test.getChronology().eras(),test.yearOfEra().getRangeDurationField());
        assertEquals(9,test.yearOfEra().getMaximumTextLength(null));
        assertEquals(9,test.yearOfEra().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetCenturyOfEra() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().centuryOfEra(),test.centuryOfEra().getField());
        assertEquals("centuryOfEra",test.centuryOfEra().getName());
        assertEquals("Property[centuryOfEra]",test.centuryOfEra().toString());
        assertSame(test,test.centuryOfEra().getDateMidnight());
        assertEquals(20,test.centuryOfEra().get());
        assertEquals("20",test.centuryOfEra().getAsText());
        assertEquals("20",test.centuryOfEra().getAsText(Locale.FRENCH));
        assertEquals("20",test.centuryOfEra().getAsShortText());
        assertEquals("20",test.centuryOfEra().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().centuries(),test.centuryOfEra().getDurationField());
        assertEquals(test.getChronology().eras(),test.centuryOfEra().getRangeDurationField());
        assertEquals(7,test.centuryOfEra().getMaximumTextLength(null));
        assertEquals(7,test.centuryOfEra().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetYearOfCentury() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().yearOfCentury(),test.yearOfCentury().getField());
        assertEquals("yearOfCentury",test.yearOfCentury().getName());
        assertEquals("Property[yearOfCentury]",test.yearOfCentury().toString());
        assertSame(test,test.yearOfCentury().getDateMidnight());
        assertEquals(4,test.yearOfCentury().get());
        assertEquals("4",test.yearOfCentury().getAsText());
        assertEquals("4",test.yearOfCentury().getAsText(Locale.FRENCH));
        assertEquals("4",test.yearOfCentury().getAsShortText());
        assertEquals("4",test.yearOfCentury().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().years(),test.yearOfCentury().getDurationField());
        assertEquals(test.getChronology().centuries(),test.yearOfCentury().getRangeDurationField());
        assertEquals(2,test.yearOfCentury().getMaximumTextLength(null));
        assertEquals(2,test.yearOfCentury().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetWeekyear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().weekyear(),test.weekyear().getField());
        assertEquals("weekyear",test.weekyear().getName());
        assertEquals("Property[weekyear]",test.weekyear().toString());
        assertSame(test,test.weekyear().getDateMidnight());
        assertEquals(2004,test.weekyear().get());
        assertEquals("2004",test.weekyear().getAsText());
        assertEquals("2004",test.weekyear().getAsText(Locale.FRENCH));
        assertEquals("2004",test.weekyear().getAsShortText());
        assertEquals("2004",test.weekyear().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().weekyears(),test.weekyear().getDurationField());
        assertEquals(null,test.weekyear().getRangeDurationField());
        assertEquals(9,test.weekyear().getMaximumTextLength(null));
        assertEquals(9,test.weekyear().getMaximumShortTextLength(null));
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetYear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().year(),test.year().getField());
        assertEquals("year",test.year().getName());
        assertEquals("Property[year]",test.year().toString());
        assertSame(test,test.year().getDateMidnight());
        assertEquals(2004,test.year().get());
        assertEquals("2004",test.year().getAsText());
        assertEquals("2004",test.year().getAsText(Locale.FRENCH));
        assertEquals("2004",test.year().getAsShortText());
        assertEquals("2004",test.year().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().years(),test.year().getDurationField());
        assertEquals(null,test.year().getRangeDurationField());
        assertEquals(9,test.year().getMaximumTextLength(null));
        assertEquals(9,test.year().getMaximumShortTextLength(null));
        assertEquals(-292275054,test.year().getMinimumValue());
        assertEquals(-292275054,test.year().getMinimumValueOverall());
        assertEquals(292278993,test.year().getMaximumValue());
        assertEquals(292278993,test.year().getMaximumValueOverall());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().monthOfYear(),test.monthOfYear().getField());
        assertEquals("monthOfYear",test.monthOfYear().getName());
        assertEquals("Property[monthOfYear]",test.monthOfYear().toString());
        assertSame(test,test.monthOfYear().getDateMidnight());
        assertEquals(6,test.monthOfYear().get());
        assertEquals("6",test.monthOfYear().getAsString());
        assertEquals("June",test.monthOfYear().getAsText());
        assertEquals("juin",test.monthOfYear().getAsText(Locale.FRENCH));
        assertEquals("Jun",test.monthOfYear().getAsShortText());
        assertEquals("juin",test.monthOfYear().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().months(),test.monthOfYear().getDurationField());
        assertEquals(test.getChronology().years(),test.monthOfYear().getRangeDurationField());
        assertEquals(9,test.monthOfYear().getMaximumTextLength(null));
        assertEquals(3,test.monthOfYear().getMaximumShortTextLength(null));
        test = new DateMidnight(2004, 7, 9);
        assertEquals("juillet",test.monthOfYear().getAsText(Locale.FRENCH));
        assertEquals("juil.",test.monthOfYear().getAsShortText(Locale.FRENCH));
        assertEquals(1,test.monthOfYear().getMinimumValue());
        assertEquals(1,test.monthOfYear().getMinimumValueOverall());
        assertEquals(12,test.monthOfYear().getMaximumValue());
        assertEquals(12,test.monthOfYear().getMaximumValueOverall());
        assertEquals(1,test.monthOfYear().getMinimumValue());
        assertEquals(1,test.monthOfYear().getMinimumValueOverall());
        assertEquals(12,test.monthOfYear().getMaximumValue());
        assertEquals(12,test.monthOfYear().getMaximumValueOverall());
    }

    public void testPropertySetMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy(8);
        assertEquals(2004,copy.getYear());
        assertEquals(8,copy.getMonthOfYear());
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertySetTextMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy("8");
        assertEquals(2004,copy.getYear());
        assertEquals(8,copy.getMonthOfYear());
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertySetTextLocaleMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy("mars", Locale.FRENCH);
        assertEquals(2004,copy.getYear());
        assertEquals(3,copy.getMonthOfYear());
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertyAddMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addToCopy(8);
        assertEquals(2005,copy.getYear());
        assertEquals(2,copy.getMonthOfYear());
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertyAddLongMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addToCopy(8L);
        assertEquals(2005,copy.getYear());
        assertEquals(2,copy.getMonthOfYear());
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertyAddWrapFieldMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addWrapFieldToCopy(8);
        assertEquals(2004,copy.getYear());
        assertEquals(2,copy.getMonthOfYear());
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertyGetDifferenceMonthOfYear() {
        DateMidnight test1 = new DateMidnight(2004, 6, 9);
        DateMidnight test2 = new DateMidnight(2004, 8, 9);
        assertEquals(-2,test1.monthOfYear().getDifference(test2));
        assertEquals(2,test2.monthOfYear().getDifference(test1));
        assertEquals(-2L,test1.monthOfYear().getDifferenceAsLong(test2));
        assertEquals(2L,test2.monthOfYear().getDifferenceAsLong(test1));
    }

    public void testPropertyRoundFloorMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundFloorCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundCeilingMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundCeilingCopy();
        assertEquals("2004-07-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfFloorMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfFloorCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
        
        test = new DateMidnight(2004, 6, 17);
        copy = test.monthOfYear().roundHalfFloorCopy();
        assertEquals("2004-07-01T00:00:00.000+01:00",copy.toString());
        
        test = new DateMidnight(2004, 6, 15);
        copy = test.monthOfYear().roundHalfFloorCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfCeilingMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfCeilingCopy();
        assertEquals("2004-07-01T00:00:00.000+01:00",copy.toString());
        
        test = new DateMidnight(2004, 6, 17);
        copy = test.monthOfYear().roundHalfCeilingCopy();
        assertEquals("2004-07-01T00:00:00.000+01:00",copy.toString());
        
        test = new DateMidnight(2004, 6, 15);
        copy = test.monthOfYear().roundHalfCeilingCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfEvenMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfEvenCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
        
        test = new DateMidnight(2004, 9, 16);
        copy = test.monthOfYear().roundHalfEvenCopy();
        assertEquals("2004-10-01T00:00:00.000+01:00",copy.toString());
        
        test = new DateMidnight(2004, 6, 17);
        copy = test.monthOfYear().roundHalfEvenCopy();
        assertEquals("2004-07-01T00:00:00.000+01:00",copy.toString());
        
        test = new DateMidnight(2004, 6, 15);
        copy = test.monthOfYear().roundHalfEvenCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRemainderMonthOfYear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals((9L - 1L)* DateTimeConstants.MILLIS_PER_DAY,test.monthOfYear().remainder());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetDayOfMonth() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().dayOfMonth(),test.dayOfMonth().getField());
        assertEquals("dayOfMonth",test.dayOfMonth().getName());
        assertEquals("Property[dayOfMonth]",test.dayOfMonth().toString());
        assertSame(test,test.dayOfMonth().getDateMidnight());
        assertEquals(9,test.dayOfMonth().get());
        assertEquals("9",test.dayOfMonth().getAsText());
        assertEquals("9",test.dayOfMonth().getAsText(Locale.FRENCH));
        assertEquals("9",test.dayOfMonth().getAsShortText());
        assertEquals("9",test.dayOfMonth().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().days(),test.dayOfMonth().getDurationField());
        assertEquals(test.getChronology().months(),test.dayOfMonth().getRangeDurationField());
        assertEquals(2,test.dayOfMonth().getMaximumTextLength(null));
        assertEquals(2,test.dayOfMonth().getMaximumShortTextLength(null));
        assertEquals(1,test.dayOfMonth().getMinimumValue());
        assertEquals(1,test.dayOfMonth().getMinimumValueOverall());
        assertEquals(30,test.dayOfMonth().getMaximumValue());
        assertEquals(31,test.dayOfMonth().getMaximumValueOverall());
        assertEquals(false,test.dayOfMonth().isLeap());
        assertEquals(0,test.dayOfMonth().getLeapAmount());
        assertEquals(null,test.dayOfMonth().getLeapDurationField());
    }

    public void testPropertyWithMaximumValueDayOfMonth() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.dayOfMonth().withMaximumValue();
        assertEquals("2004-06-09T00:00:00.000+01:00",test.toString());
        assertEquals("2004-06-30T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyWithMinimumValueDayOfMonth() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.dayOfMonth().withMinimumValue();
        assertEquals("2004-06-09T00:00:00.000+01:00",test.toString());
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetDayOfYear() {
        // 31+29+31+30+31+9 = 161
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().dayOfYear(),test.dayOfYear().getField());
        assertEquals("dayOfYear",test.dayOfYear().getName());
        assertEquals("Property[dayOfYear]",test.dayOfYear().toString());
        assertSame(test,test.dayOfYear().getDateMidnight());
        assertEquals(161,test.dayOfYear().get());
        assertEquals("161",test.dayOfYear().getAsText());
        assertEquals("161",test.dayOfYear().getAsText(Locale.FRENCH));
        assertEquals("161",test.dayOfYear().getAsShortText());
        assertEquals("161",test.dayOfYear().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().days(),test.dayOfYear().getDurationField());
        assertEquals(test.getChronology().years(),test.dayOfYear().getRangeDurationField());
        assertEquals(3,test.dayOfYear().getMaximumTextLength(null));
        assertEquals(3,test.dayOfYear().getMaximumShortTextLength(null));
        assertEquals(false,test.dayOfYear().isLeap());
        assertEquals(0,test.dayOfYear().getLeapAmount());
        assertEquals(null,test.dayOfYear().getLeapDurationField());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetWeekOfWeekyear() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().weekOfWeekyear(),test.weekOfWeekyear().getField());
        assertEquals("weekOfWeekyear",test.weekOfWeekyear().getName());
        assertEquals("Property[weekOfWeekyear]",test.weekOfWeekyear().toString());
        assertSame(test,test.weekOfWeekyear().getDateMidnight());
        assertEquals(24,test.weekOfWeekyear().get());
        assertEquals("24",test.weekOfWeekyear().getAsText());
        assertEquals("24",test.weekOfWeekyear().getAsText(Locale.FRENCH));
        assertEquals("24",test.weekOfWeekyear().getAsShortText());
        assertEquals("24",test.weekOfWeekyear().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().weeks(),test.weekOfWeekyear().getDurationField());
        assertEquals(test.getChronology().weekyears(),test.weekOfWeekyear().getRangeDurationField());
        assertEquals(2,test.weekOfWeekyear().getMaximumTextLength(null));
        assertEquals(2,test.weekOfWeekyear().getMaximumShortTextLength(null));
        assertEquals(false,test.weekOfWeekyear().isLeap());
        assertEquals(0,test.weekOfWeekyear().getLeapAmount());
        assertEquals(null,test.weekOfWeekyear().getLeapDurationField());
    }

    //-----------------------------------------------------------------------
    public void testPropertyGetDayOfWeek() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().dayOfWeek(),test.dayOfWeek().getField());
        assertEquals("dayOfWeek",test.dayOfWeek().getName());
        assertEquals("Property[dayOfWeek]",test.dayOfWeek().toString());
        assertSame(test,test.dayOfWeek().getDateMidnight());
        assertEquals(3,test.dayOfWeek().get());
        assertEquals("3",test.dayOfWeek().getAsString());
        assertEquals("Wednesday",test.dayOfWeek().getAsText());
        assertEquals("mercredi",test.dayOfWeek().getAsText(Locale.FRENCH));
        assertEquals("Wed",test.dayOfWeek().getAsShortText());
        assertEquals("mer.",test.dayOfWeek().getAsShortText(Locale.FRENCH));
        assertEquals(test.getChronology().days(),test.dayOfWeek().getDurationField());
        assertEquals(test.getChronology().weeks(),test.dayOfWeek().getRangeDurationField());
        assertEquals(9,test.dayOfWeek().getMaximumTextLength(null));
        assertEquals(8,test.dayOfWeek().getMaximumTextLength(Locale.FRENCH));
        assertEquals(3,test.dayOfWeek().getMaximumShortTextLength(null));
        assertEquals(4,test.dayOfWeek().getMaximumShortTextLength(Locale.FRENCH));
        assertEquals(1,test.dayOfWeek().getMinimumValue());
        assertEquals(1,test.dayOfWeek().getMinimumValueOverall());
        assertEquals(7,test.dayOfWeek().getMaximumValue());
        assertEquals(7,test.dayOfWeek().getMaximumValueOverall());
        assertEquals(false,test.dayOfWeek().isLeap());
        assertEquals(0,test.dayOfWeek().getLeapAmount());
        assertEquals(null,test.dayOfWeek().getLeapDurationField());
    }

    //-----------------------------------------------------------------------
    public void testPropertyToIntervalYearOfEra() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.yearOfEra().toInterval();
      assertEquals(new DateMidnight(2004,1,1),testInterval.getStart());
      assertEquals(new DateMidnight(2005,1,1),testInterval.getEnd());
    }

    public void testPropertyToIntervalYearOfCentury() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.yearOfCentury().toInterval();
      assertEquals(new DateMidnight(2004,1,1),testInterval.getStart());
      assertEquals(new DateMidnight(2005,1,1),testInterval.getEnd());
    }

    public void testPropertyToIntervalYear() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.year().toInterval();
      assertEquals(new DateMidnight(2004,1,1),testInterval.getStart());
      assertEquals(new DateMidnight(2005,1,1),testInterval.getEnd());
    }

    public void testPropertyToIntervalMonthOfYear() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.monthOfYear().toInterval();
      assertEquals(new DateMidnight(2004,6,1),testInterval.getStart());
      assertEquals(new DateMidnight(2004,7,1),testInterval.getEnd());
    }

    public void testPropertyToIntervalDayOfMonth() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.dayOfMonth().toInterval();
      assertEquals(new DateMidnight(2004,6,9),testInterval.getStart());
      assertEquals(new DateMidnight(2004,6,10),testInterval.getEnd());

      DateMidnight febTest = new DateMidnight(2004, 2, 29);
      Interval febTestInterval = febTest.dayOfMonth().toInterval();
      assertEquals(new DateMidnight(2004,2,29),febTestInterval.getStart());
      assertEquals(new DateMidnight(2004,3,1),febTestInterval.getEnd());
    }

    public void testPropertyEqualsHashCodeLenient() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.dayOfMonth().equals(test2.dayOfMonth()));
        assertEquals(true,test2.dayOfMonth().equals(test1.dayOfMonth()));
        assertEquals(true,test1.dayOfMonth().equals(test1.dayOfMonth()));
        assertEquals(true,test2.dayOfMonth().equals(test2.dayOfMonth()));
        assertEquals(true,test1.dayOfMonth().hashCode()== test2.dayOfMonth().hashCode());
        assertEquals(true,test1.dayOfMonth().hashCode()== test1.dayOfMonth().hashCode());
        assertEquals(true,test2.dayOfMonth().hashCode()== test2.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeStrict() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.dayOfMonth().equals(test2.dayOfMonth()));
        assertEquals(true,test2.dayOfMonth().equals(test1.dayOfMonth()));
        assertEquals(true,test1.dayOfMonth().equals(test1.dayOfMonth()));
        assertEquals(true,test2.dayOfMonth().equals(test2.dayOfMonth()));
        assertEquals(true,test1.dayOfMonth().hashCode()== test2.dayOfMonth().hashCode());
        assertEquals(true,test1.dayOfMonth().hashCode()== test1.dayOfMonth().hashCode());
        assertEquals(true,test2.dayOfMonth().hashCode()== test2.dayOfMonth().hashCode());
    }

    public void testTest_1_oe() {
        Object a = new Instant(TEST_TIME_NOW).toString();
        assertEquals("2002-06-09T00:00:00.000Z", a);
    }

    public void testTest_2_oe() {
        Object a = new Instant(TEST_TIME1).toString();
        assertEquals("2002-04-05T12:24:00.000Z", a);
    }

    public void testTest_3_oe() {
        Object a = new Instant(TEST_TIME2).toString();
        assertEquals("2003-05-06T14:28:00.000Z", a);
    }

    public void testPropertyGetEra_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().era(),test.era().getField());
    }

    public void testPropertyGetEra_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("era",test.era().getName());
    }

    public void testPropertyGetEra_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[era]",test.era().toString());
    }

    public void testPropertyGetEra_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.era().getDateMidnight());
    }

    public void testPropertyGetEra_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(1,test.era().get());
    }

    public void testPropertyGetEra_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("AD",test.era().getAsText());
    }

    public void testPropertyGetEra_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("ap. J.-C.",test.era().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetEra_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("AD",test.era().getAsShortText());
    }

    public void testPropertyGetEra_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("ap. J.-C.",test.era().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetEra_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().eras(),test.era().getDurationField());
    }

    public void testPropertyGetEra_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(null,test.era().getRangeDurationField());
    }

    public void testPropertyGetEra_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2,test.era().getMaximumTextLength(null));
    }

    public void testPropertyGetEra_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.era().getMaximumTextLength(Locale.FRENCH));
    }

    public void testPropertyGetEra_14_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2,test.era().getMaximumShortTextLength(null));
    }

    public void testPropertyGetEra_15_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.era().getMaximumShortTextLength(Locale.FRENCH));
    }

    public void testPropertyGetYearOfEra_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().yearOfEra(),test.yearOfEra().getField());
    }

    public void testPropertyGetYearOfEra_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("yearOfEra",test.yearOfEra().getName());
    }

    public void testPropertyGetYearOfEra_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[yearOfEra]",test.yearOfEra().toString());
    }

    public void testPropertyGetYearOfEra_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.yearOfEra().getDateMidnight());
    }

    public void testPropertyGetYearOfEra_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2004,test.yearOfEra().get());
    }

    public void testPropertyGetYearOfEra_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.yearOfEra().getAsText());
    }

    public void testPropertyGetYearOfEra_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.yearOfEra().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetYearOfEra_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.yearOfEra().getAsShortText());
    }

    public void testPropertyGetYearOfEra_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.yearOfEra().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetYearOfEra_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().years(),test.yearOfEra().getDurationField());
    }

    public void testPropertyGetYearOfEra_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().eras(),test.yearOfEra().getRangeDurationField());
    }

    public void testPropertyGetYearOfEra_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.yearOfEra().getMaximumTextLength(null));
    }

    public void testPropertyGetYearOfEra_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.yearOfEra().getMaximumShortTextLength(null));
    }

    public void testPropertyGetCenturyOfEra_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().centuryOfEra(),test.centuryOfEra().getField());
    }

    public void testPropertyGetCenturyOfEra_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("centuryOfEra",test.centuryOfEra().getName());
    }

    public void testPropertyGetCenturyOfEra_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[centuryOfEra]",test.centuryOfEra().toString());
    }

    public void testPropertyGetCenturyOfEra_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.centuryOfEra().getDateMidnight());
    }

    public void testPropertyGetCenturyOfEra_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(20,test.centuryOfEra().get());
    }

    public void testPropertyGetCenturyOfEra_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("20",test.centuryOfEra().getAsText());
    }

    public void testPropertyGetCenturyOfEra_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("20",test.centuryOfEra().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetCenturyOfEra_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("20",test.centuryOfEra().getAsShortText());
    }

    public void testPropertyGetCenturyOfEra_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("20",test.centuryOfEra().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetCenturyOfEra_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().centuries(),test.centuryOfEra().getDurationField());
    }

    public void testPropertyGetCenturyOfEra_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().eras(),test.centuryOfEra().getRangeDurationField());
    }

    public void testPropertyGetCenturyOfEra_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(7,test.centuryOfEra().getMaximumTextLength(null));
    }

    public void testPropertyGetCenturyOfEra_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(7,test.centuryOfEra().getMaximumShortTextLength(null));
    }

    public void testPropertyGetYearOfCentury_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().yearOfCentury(),test.yearOfCentury().getField());
    }

    public void testPropertyGetYearOfCentury_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("yearOfCentury",test.yearOfCentury().getName());
    }

    public void testPropertyGetYearOfCentury_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[yearOfCentury]",test.yearOfCentury().toString());
    }

    public void testPropertyGetYearOfCentury_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.yearOfCentury().getDateMidnight());
    }

    public void testPropertyGetYearOfCentury_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(4,test.yearOfCentury().get());
    }

    public void testPropertyGetYearOfCentury_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("4",test.yearOfCentury().getAsText());
    }

    public void testPropertyGetYearOfCentury_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("4",test.yearOfCentury().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetYearOfCentury_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("4",test.yearOfCentury().getAsShortText());
    }

    public void testPropertyGetYearOfCentury_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("4",test.yearOfCentury().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetYearOfCentury_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().years(),test.yearOfCentury().getDurationField());
    }

    public void testPropertyGetYearOfCentury_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().centuries(),test.yearOfCentury().getRangeDurationField());
    }

    public void testPropertyGetYearOfCentury_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2,test.yearOfCentury().getMaximumTextLength(null));
    }

    public void testPropertyGetYearOfCentury_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2,test.yearOfCentury().getMaximumShortTextLength(null));
    }

    public void testPropertyGetWeekyear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().weekyear(),test.weekyear().getField());
    }

    public void testPropertyGetWeekyear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("weekyear",test.weekyear().getName());
    }

    public void testPropertyGetWeekyear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[weekyear]",test.weekyear().toString());
    }

    public void testPropertyGetWeekyear_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.weekyear().getDateMidnight());
    }

    public void testPropertyGetWeekyear_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2004,test.weekyear().get());
    }

    public void testPropertyGetWeekyear_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.weekyear().getAsText());
    }

    public void testPropertyGetWeekyear_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.weekyear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetWeekyear_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.weekyear().getAsShortText());
    }

    public void testPropertyGetWeekyear_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.weekyear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetWeekyear_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().weekyears(),test.weekyear().getDurationField());
    }

    public void testPropertyGetWeekyear_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(null,test.weekyear().getRangeDurationField());
    }

    public void testPropertyGetWeekyear_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.weekyear().getMaximumTextLength(null));
    }

    public void testPropertyGetWeekyear_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.weekyear().getMaximumShortTextLength(null));
    }

    public void testPropertyGetYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().year(),test.year().getField());
    }

    public void testPropertyGetYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("year",test.year().getName());
    }

    public void testPropertyGetYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[year]",test.year().toString());
    }

    public void testPropertyGetYear_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.year().getDateMidnight());
    }

    public void testPropertyGetYear_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2004,test.year().get());
    }

    public void testPropertyGetYear_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.year().getAsText());
    }

    public void testPropertyGetYear_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.year().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetYear_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.year().getAsShortText());
    }

    public void testPropertyGetYear_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("2004",test.year().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetYear_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().years(),test.year().getDurationField());
    }

    public void testPropertyGetYear_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(null,test.year().getRangeDurationField());
    }

    public void testPropertyGetYear_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.year().getMaximumTextLength(null));
    }

    public void testPropertyGetYear_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.year().getMaximumShortTextLength(null));
    }

    public void testPropertyGetYear_14_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(-292275054,test.year().getMinimumValue());
    }

    public void testPropertyGetYear_15_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(-292275054,test.year().getMinimumValueOverall());
    }

    public void testPropertyGetYear_16_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(292278993,test.year().getMaximumValue());
    }

    public void testPropertyGetYear_17_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(292278993,test.year().getMaximumValueOverall());
    }

    public void testPropertyGetMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().monthOfYear(),test.monthOfYear().getField());
    }

    public void testPropertyGetMonthOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("monthOfYear",test.monthOfYear().getName());
    }

    public void testPropertyGetMonthOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[monthOfYear]",test.monthOfYear().toString());
    }

    public void testPropertyGetMonthOfYear_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.monthOfYear().getDateMidnight());
    }

    public void testPropertyGetMonthOfYear_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(6,test.monthOfYear().get());
    }

    public void testPropertyGetMonthOfYear_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("6",test.monthOfYear().getAsString());
    }

    public void testPropertyGetMonthOfYear_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("June",test.monthOfYear().getAsText());
    }

    public void testPropertyGetMonthOfYear_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("juin",test.monthOfYear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMonthOfYear_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Jun",test.monthOfYear().getAsShortText());
    }

    public void testPropertyGetMonthOfYear_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("juin",test.monthOfYear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMonthOfYear_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().months(),test.monthOfYear().getDurationField());
    }

    public void testPropertyGetMonthOfYear_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().years(),test.monthOfYear().getRangeDurationField());
    }

    public void testPropertyGetMonthOfYear_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.monthOfYear().getMaximumTextLength(null));
    }

    public void testPropertyGetMonthOfYear_15_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        test = new DateMidnight(2004, 7, 9);
        assertEquals("juillet",test.monthOfYear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetMonthOfYear_16_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        test = new DateMidnight(2004, 7, 9);
        assertEquals("juil.",test.monthOfYear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetMonthOfYear_17_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        test = new DateMidnight(2004, 7, 9);
        assertEquals(1,test.monthOfYear().getMinimumValue());
    }

    public void testPropertyGetMonthOfYear_18_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        test = new DateMidnight(2004, 7, 9);
        assertEquals(1,test.monthOfYear().getMinimumValueOverall());
    }

    public void testPropertyGetMonthOfYear_19_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        test = new DateMidnight(2004, 7, 9);
        assertEquals(12,test.monthOfYear().getMaximumValue());
    }

    public void testPropertyGetMonthOfYear_20_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        test = new DateMidnight(2004, 7, 9);
        assertEquals(12,test.monthOfYear().getMaximumValueOverall());
    }

    public void testPropertyGetMonthOfYear_21_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        test = new DateMidnight(2004, 7, 9);
        assertEquals(1,test.monthOfYear().getMinimumValue());
    }

    public void testPropertyGetMonthOfYear_22_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        test = new DateMidnight(2004, 7, 9);
        assertEquals(1,test.monthOfYear().getMinimumValueOverall());
    }

    public void testPropertyGetMonthOfYear_23_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        test = new DateMidnight(2004, 7, 9);
        assertEquals(12,test.monthOfYear().getMaximumValue());
    }

    public void testPropertyGetMonthOfYear_24_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        test = new DateMidnight(2004, 7, 9);
        assertEquals(12,test.monthOfYear().getMaximumValueOverall());
    }

    public void testPropertySetMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy(8);
        assertEquals(2004,copy.getYear());
    }

    public void testPropertySetMonthOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy(8);
        assertEquals(8,copy.getMonthOfYear());
    }

    public void testPropertySetMonthOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy(8);
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertySetTextMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy("8");
        assertEquals(2004,copy.getYear());
    }

    public void testPropertySetTextMonthOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy("8");
        assertEquals(8,copy.getMonthOfYear());
    }

    public void testPropertySetTextMonthOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy("8");
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertySetTextLocaleMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy("mars", Locale.FRENCH);
        assertEquals(2004,copy.getYear());
    }

    public void testPropertySetTextLocaleMonthOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy("mars", Locale.FRENCH);
        assertEquals(3,copy.getMonthOfYear());
    }

    public void testPropertySetTextLocaleMonthOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().setCopy("mars", Locale.FRENCH);
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertyAddMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addToCopy(8);
        assertEquals(2005,copy.getYear());
    }

    public void testPropertyAddMonthOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addToCopy(8);
        assertEquals(2,copy.getMonthOfYear());
    }

    public void testPropertyAddMonthOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addToCopy(8);
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertyAddLongMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addToCopy(8L);
        assertEquals(2005,copy.getYear());
    }

    public void testPropertyAddLongMonthOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addToCopy(8L);
        assertEquals(2,copy.getMonthOfYear());
    }

    public void testPropertyAddLongMonthOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addToCopy(8L);
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertyAddWrapFieldMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addWrapFieldToCopy(8);
        assertEquals(2004,copy.getYear());
    }

    public void testPropertyAddWrapFieldMonthOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addWrapFieldToCopy(8);
        assertEquals(2,copy.getMonthOfYear());
    }

    public void testPropertyAddWrapFieldMonthOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.monthOfYear().addWrapFieldToCopy(8);
        assertEquals(9,copy.getDayOfMonth());
    }

    public void testPropertyGetDifferenceMonthOfYear_1_oe() {
        DateMidnight test1 = new DateMidnight(2004, 6, 9);
        DateMidnight test2 = new DateMidnight(2004, 8, 9);
        assertEquals(-2,test1.monthOfYear().getDifference(test2));
    }

    public void testPropertyGetDifferenceMonthOfYear_2_oe() {
        DateMidnight test1 = new DateMidnight(2004, 6, 9);
        DateMidnight test2 = new DateMidnight(2004, 8, 9);
        assertEquals(2,test2.monthOfYear().getDifference(test1));
    }

    public void testPropertyGetDifferenceMonthOfYear_3_oe() {
        DateMidnight test1 = new DateMidnight(2004, 6, 9);
        DateMidnight test2 = new DateMidnight(2004, 8, 9);
        assertEquals(-2L,test1.monthOfYear().getDifferenceAsLong(test2));
    }

    public void testPropertyGetDifferenceMonthOfYear_4_oe() {
        DateMidnight test1 = new DateMidnight(2004, 6, 9);
        DateMidnight test2 = new DateMidnight(2004, 8, 9);
        assertEquals(2L,test2.monthOfYear().getDifferenceAsLong(test1));
    }

    public void testPropertyRoundFloorMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundFloorCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundCeilingMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundCeilingCopy();
        assertEquals("2004-07-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfFloorMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfFloorCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfFloorMonthOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfFloorCopy();
        
        test = new DateMidnight(2004, 6, 17);
        copy = test.monthOfYear().roundHalfFloorCopy();
        assertEquals("2004-07-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfFloorMonthOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfFloorCopy();
        
        test = new DateMidnight(2004, 6, 17);
        copy = test.monthOfYear().roundHalfFloorCopy();
        
        test = new DateMidnight(2004, 6, 15);
        copy = test.monthOfYear().roundHalfFloorCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfCeilingMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfCeilingCopy();
        assertEquals("2004-07-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfCeilingMonthOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfCeilingCopy();
        
        test = new DateMidnight(2004, 6, 17);
        copy = test.monthOfYear().roundHalfCeilingCopy();
        assertEquals("2004-07-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfCeilingMonthOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfCeilingCopy();
        
        test = new DateMidnight(2004, 6, 17);
        copy = test.monthOfYear().roundHalfCeilingCopy();
        
        test = new DateMidnight(2004, 6, 15);
        copy = test.monthOfYear().roundHalfCeilingCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfEvenMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfEvenCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfEvenMonthOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfEvenCopy();
        
        test = new DateMidnight(2004, 9, 16);
        copy = test.monthOfYear().roundHalfEvenCopy();
        assertEquals("2004-10-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfEvenMonthOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfEvenCopy();
        
        test = new DateMidnight(2004, 9, 16);
        copy = test.monthOfYear().roundHalfEvenCopy();
        
        test = new DateMidnight(2004, 6, 17);
        copy = test.monthOfYear().roundHalfEvenCopy();
        assertEquals("2004-07-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRoundHalfEvenMonthOfYear_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 16);
        DateMidnight copy = test.monthOfYear().roundHalfEvenCopy();
        
        test = new DateMidnight(2004, 9, 16);
        copy = test.monthOfYear().roundHalfEvenCopy();
        
        test = new DateMidnight(2004, 6, 17);
        copy = test.monthOfYear().roundHalfEvenCopy();
        
        test = new DateMidnight(2004, 6, 15);
        copy = test.monthOfYear().roundHalfEvenCopy();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyRemainderMonthOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals((9L - 1L)* DateTimeConstants.MILLIS_PER_DAY,test.monthOfYear().remainder());
    }

    public void testPropertyGetDayOfMonth_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().dayOfMonth(),test.dayOfMonth().getField());
    }

    public void testPropertyGetDayOfMonth_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("dayOfMonth",test.dayOfMonth().getName());
    }

    public void testPropertyGetDayOfMonth_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[dayOfMonth]",test.dayOfMonth().toString());
    }

    public void testPropertyGetDayOfMonth_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.dayOfMonth().getDateMidnight());
    }

    public void testPropertyGetDayOfMonth_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.dayOfMonth().get());
    }

    public void testPropertyGetDayOfMonth_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("9",test.dayOfMonth().getAsText());
    }

    public void testPropertyGetDayOfMonth_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("9",test.dayOfMonth().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfMonth_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("9",test.dayOfMonth().getAsShortText());
    }

    public void testPropertyGetDayOfMonth_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("9",test.dayOfMonth().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfMonth_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().days(),test.dayOfMonth().getDurationField());
    }

    public void testPropertyGetDayOfMonth_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().months(),test.dayOfMonth().getRangeDurationField());
    }

    public void testPropertyGetDayOfMonth_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2,test.dayOfMonth().getMaximumTextLength(null));
    }

    public void testPropertyGetDayOfMonth_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2,test.dayOfMonth().getMaximumShortTextLength(null));
    }

    public void testPropertyGetDayOfMonth_14_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(1,test.dayOfMonth().getMinimumValue());
    }

    public void testPropertyGetDayOfMonth_15_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(1,test.dayOfMonth().getMinimumValueOverall());
    }

    public void testPropertyGetDayOfMonth_16_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(30,test.dayOfMonth().getMaximumValue());
    }

    public void testPropertyGetDayOfMonth_17_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(31,test.dayOfMonth().getMaximumValueOverall());
    }

    public void testPropertyGetDayOfMonth_18_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(false,test.dayOfMonth().isLeap());
    }

    public void testPropertyGetDayOfMonth_19_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(0,test.dayOfMonth().getLeapAmount());
    }

    public void testPropertyGetDayOfMonth_20_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(null,test.dayOfMonth().getLeapDurationField());
    }

    public void testPropertyWithMaximumValueDayOfMonth_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.dayOfMonth().withMaximumValue();
        assertEquals("2004-06-09T00:00:00.000+01:00",test.toString());
    }

    public void testPropertyWithMaximumValueDayOfMonth_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.dayOfMonth().withMaximumValue();
        assertEquals("2004-06-30T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyWithMinimumValueDayOfMonth_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.dayOfMonth().withMinimumValue();
        assertEquals("2004-06-09T00:00:00.000+01:00",test.toString());
    }

    public void testPropertyWithMinimumValueDayOfMonth_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        DateMidnight copy = test.dayOfMonth().withMinimumValue();
        assertEquals("2004-06-01T00:00:00.000+01:00",copy.toString());
    }

    public void testPropertyGetDayOfYear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().dayOfYear(),test.dayOfYear().getField());
    }

    public void testPropertyGetDayOfYear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("dayOfYear",test.dayOfYear().getName());
    }

    public void testPropertyGetDayOfYear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[dayOfYear]",test.dayOfYear().toString());
    }

    public void testPropertyGetDayOfYear_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.dayOfYear().getDateMidnight());
    }

    public void testPropertyGetDayOfYear_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(161,test.dayOfYear().get());
    }

    public void testPropertyGetDayOfYear_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("161",test.dayOfYear().getAsText());
    }

    public void testPropertyGetDayOfYear_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("161",test.dayOfYear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfYear_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("161",test.dayOfYear().getAsShortText());
    }

    public void testPropertyGetDayOfYear_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("161",test.dayOfYear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfYear_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().days(),test.dayOfYear().getDurationField());
    }

    public void testPropertyGetDayOfYear_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().years(),test.dayOfYear().getRangeDurationField());
    }

    public void testPropertyGetDayOfYear_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(3,test.dayOfYear().getMaximumTextLength(null));
    }

    public void testPropertyGetDayOfYear_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(3,test.dayOfYear().getMaximumShortTextLength(null));
    }

    public void testPropertyGetDayOfYear_14_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(false,test.dayOfYear().isLeap());
    }

    public void testPropertyGetDayOfYear_15_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(0,test.dayOfYear().getLeapAmount());
    }

    public void testPropertyGetDayOfYear_16_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(null,test.dayOfYear().getLeapDurationField());
    }

    public void testPropertyGetWeekOfWeekyear_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().weekOfWeekyear(),test.weekOfWeekyear().getField());
    }

    public void testPropertyGetWeekOfWeekyear_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("weekOfWeekyear",test.weekOfWeekyear().getName());
    }

    public void testPropertyGetWeekOfWeekyear_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[weekOfWeekyear]",test.weekOfWeekyear().toString());
    }

    public void testPropertyGetWeekOfWeekyear_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.weekOfWeekyear().getDateMidnight());
    }

    public void testPropertyGetWeekOfWeekyear_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(24,test.weekOfWeekyear().get());
    }

    public void testPropertyGetWeekOfWeekyear_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("24",test.weekOfWeekyear().getAsText());
    }

    public void testPropertyGetWeekOfWeekyear_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("24",test.weekOfWeekyear().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetWeekOfWeekyear_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("24",test.weekOfWeekyear().getAsShortText());
    }

    public void testPropertyGetWeekOfWeekyear_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("24",test.weekOfWeekyear().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetWeekOfWeekyear_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().weeks(),test.weekOfWeekyear().getDurationField());
    }

    public void testPropertyGetWeekOfWeekyear_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().weekyears(),test.weekOfWeekyear().getRangeDurationField());
    }

    public void testPropertyGetWeekOfWeekyear_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2,test.weekOfWeekyear().getMaximumTextLength(null));
    }

    public void testPropertyGetWeekOfWeekyear_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(2,test.weekOfWeekyear().getMaximumShortTextLength(null));
    }

    public void testPropertyGetWeekOfWeekyear_14_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(false,test.weekOfWeekyear().isLeap());
    }

    public void testPropertyGetWeekOfWeekyear_15_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(0,test.weekOfWeekyear().getLeapAmount());
    }

    public void testPropertyGetWeekOfWeekyear_16_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(null,test.weekOfWeekyear().getLeapDurationField());
    }

    public void testPropertyGetDayOfWeek_1_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test.getChronology().dayOfWeek(),test.dayOfWeek().getField());
    }

    public void testPropertyGetDayOfWeek_2_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("dayOfWeek",test.dayOfWeek().getName());
    }

    public void testPropertyGetDayOfWeek_3_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Property[dayOfWeek]",test.dayOfWeek().toString());
    }

    public void testPropertyGetDayOfWeek_4_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertSame(test,test.dayOfWeek().getDateMidnight());
    }

    public void testPropertyGetDayOfWeek_5_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(3,test.dayOfWeek().get());
    }

    public void testPropertyGetDayOfWeek_6_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("3",test.dayOfWeek().getAsString());
    }

    public void testPropertyGetDayOfWeek_7_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Wednesday",test.dayOfWeek().getAsText());
    }

    public void testPropertyGetDayOfWeek_8_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("mercredi",test.dayOfWeek().getAsText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfWeek_9_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("Wed",test.dayOfWeek().getAsShortText());
    }

    public void testPropertyGetDayOfWeek_10_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals("mer.",test.dayOfWeek().getAsShortText(Locale.FRENCH));
    }

    public void testPropertyGetDayOfWeek_11_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().days(),test.dayOfWeek().getDurationField());
    }

    public void testPropertyGetDayOfWeek_12_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(test.getChronology().weeks(),test.dayOfWeek().getRangeDurationField());
    }

    public void testPropertyGetDayOfWeek_13_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(9,test.dayOfWeek().getMaximumTextLength(null));
    }

    public void testPropertyGetDayOfWeek_14_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(8,test.dayOfWeek().getMaximumTextLength(Locale.FRENCH));
    }

    public void testPropertyGetDayOfWeek_15_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(3,test.dayOfWeek().getMaximumShortTextLength(null));
    }

    public void testPropertyGetDayOfWeek_16_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(4,test.dayOfWeek().getMaximumShortTextLength(Locale.FRENCH));
    }

    public void testPropertyGetDayOfWeek_17_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(1,test.dayOfWeek().getMinimumValue());
    }

    public void testPropertyGetDayOfWeek_18_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(1,test.dayOfWeek().getMinimumValueOverall());
    }

    public void testPropertyGetDayOfWeek_19_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(7,test.dayOfWeek().getMaximumValue());
    }

    public void testPropertyGetDayOfWeek_20_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(7,test.dayOfWeek().getMaximumValueOverall());
    }

    public void testPropertyGetDayOfWeek_21_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(false,test.dayOfWeek().isLeap());
    }

    public void testPropertyGetDayOfWeek_22_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(0,test.dayOfWeek().getLeapAmount());
    }

    public void testPropertyGetDayOfWeek_23_oe() {
        DateMidnight test = new DateMidnight(2004, 6, 9);
        assertEquals(null,test.dayOfWeek().getLeapDurationField());
    }

    public void testPropertyToIntervalYearOfEra_1_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.yearOfEra().toInterval();
      assertEquals(new DateMidnight(2004,1,1),testInterval.getStart());
    }

    public void testPropertyToIntervalYearOfEra_2_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.yearOfEra().toInterval();
      assertEquals(new DateMidnight(2005,1,1),testInterval.getEnd());
    }

    public void testPropertyToIntervalYearOfCentury_1_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.yearOfCentury().toInterval();
      assertEquals(new DateMidnight(2004,1,1),testInterval.getStart());
    }

    public void testPropertyToIntervalYearOfCentury_2_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.yearOfCentury().toInterval();
      assertEquals(new DateMidnight(2005,1,1),testInterval.getEnd());
    }

    public void testPropertyToIntervalYear_1_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.year().toInterval();
      assertEquals(new DateMidnight(2004,1,1),testInterval.getStart());
    }

    public void testPropertyToIntervalYear_2_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.year().toInterval();
      assertEquals(new DateMidnight(2005,1,1),testInterval.getEnd());
    }

    public void testPropertyToIntervalMonthOfYear_1_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.monthOfYear().toInterval();
      assertEquals(new DateMidnight(2004,6,1),testInterval.getStart());
    }

    public void testPropertyToIntervalMonthOfYear_2_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.monthOfYear().toInterval();
      assertEquals(new DateMidnight(2004,7,1),testInterval.getEnd());
    }

    public void testPropertyToIntervalDayOfMonth_1_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.dayOfMonth().toInterval();
      assertEquals(new DateMidnight(2004,6,9),testInterval.getStart());
    }

    public void testPropertyToIntervalDayOfMonth_2_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.dayOfMonth().toInterval();
      assertEquals(new DateMidnight(2004,6,10),testInterval.getEnd());
    }

    public void testPropertyToIntervalDayOfMonth_3_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.dayOfMonth().toInterval();

      DateMidnight febTest = new DateMidnight(2004, 2, 29);
      Interval febTestInterval = febTest.dayOfMonth().toInterval();
      assertEquals(new DateMidnight(2004,2,29),febTestInterval.getStart());
    }

    public void testPropertyToIntervalDayOfMonth_4_oe() {
      DateMidnight test = new DateMidnight(2004, 6, 9);
      Interval testInterval = test.dayOfMonth().toInterval();

      DateMidnight febTest = new DateMidnight(2004, 2, 29);
      Interval febTestInterval = febTest.dayOfMonth().toInterval();
      assertEquals(new DateMidnight(2004,3,1),febTestInterval.getEnd());
    }

    public void testPropertyEqualsHashCodeLenient_1_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.dayOfMonth().equals(test2.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeLenient_2_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test2.dayOfMonth().equals(test1.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeLenient_3_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.dayOfMonth().equals(test1.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeLenient_4_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test2.dayOfMonth().equals(test2.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeLenient_5_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.dayOfMonth().hashCode()== test2.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeLenient_6_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.dayOfMonth().hashCode()== test1.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeLenient_7_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, LenientChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test2.dayOfMonth().hashCode()== test2.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeStrict_1_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.dayOfMonth().equals(test2.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeStrict_2_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test2.dayOfMonth().equals(test1.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeStrict_3_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.dayOfMonth().equals(test1.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeStrict_4_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test2.dayOfMonth().equals(test2.dayOfMonth()));
    }

    public void testPropertyEqualsHashCodeStrict_5_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.dayOfMonth().hashCode()== test2.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeStrict_6_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test1.dayOfMonth().hashCode()== test1.dayOfMonth().hashCode());
    }

    public void testPropertyEqualsHashCodeStrict_7_oe() {
        DateMidnight test1 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        DateMidnight test2 = new DateMidnight(1970, 6, 9, StrictChronology.getInstance(COPTIC_PARIS));
        assertEquals(true,test2.dayOfMonth().hashCode()== test2.dayOfMonth().hashCode());
    }

}
