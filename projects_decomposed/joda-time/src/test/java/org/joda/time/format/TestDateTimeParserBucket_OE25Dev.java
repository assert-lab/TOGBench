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

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.ISOChronology;

/**
 * Test.
 */
public class TestDateTimeParserBucket_OE25Dev extends TestCase {

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final Chronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();
    private static final Chronology BUDDHIST_PARIS = BuddhistChronology.getInstance(PARIS);
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final DateTimeZone ZONE_0400 = DateTimeZone.forOffsetHours(4);
    private static final Chronology ISO_0400 = ISOChronology.getInstance(ZONE_0400);
    private static final int MILLIS_PER_HOUR = 3600000;
    private static final int MILLIS_PER_MINUTE = 60000;
    private static final int OFFSET_0400 = 4 * MILLIS_PER_HOUR;
    private static final Locale LOCALE = Locale.CANADA;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateTimeParserBucket_OE25Dev_OE25Dev.class);
    }

    public TestDateTimeParserBucket_OE25Dev(String name) {
        super(name);
    }

    //-----------------------------------------------------------------------

    //-------------------------------------------------------------------------

    public void testConstructor_3arg_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE);
        assertEquals(BUDDHIST_UTC, test.getChronology());
    }

    public void testConstructor_3arg_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE);
        // removed other assertion
        assertEquals(LOCALE, test.getLocale());
    }

    public void testConstructor_3arg_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE);
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.getPivotYear());
    }

    public void testConstructor_3arg_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.getOffsetInteger());
    }

    public void testConstructor_3arg_5_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PARIS, test.getZone());
    }

    public void testConstructor_4arg_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010);
        assertEquals(BUDDHIST_UTC, test.getChronology());
    }

    public void testConstructor_4arg_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010);
        // removed other assertion
        assertEquals(LOCALE, test.getLocale());
    }

    public void testConstructor_4arg_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010);
        // removed other assertion
        // removed other assertion
        assertEquals((Integer) 2010, test.getPivotYear());
    }

    public void testConstructor_4arg_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.getOffsetInteger());
    }

    public void testConstructor_4arg_5_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PARIS, test.getZone());
    }

    public void testConstructor_5arg_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        assertEquals(BUDDHIST_UTC, test.getChronology());
    }

    public void testConstructor_5arg_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        // removed other assertion
        assertEquals(LOCALE, test.getLocale());
    }

    public void testConstructor_5arg_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        // removed other assertion
        // removed other assertion
        assertEquals((Integer) 2010, test.getPivotYear());
    }

    public void testConstructor_5arg_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.getOffsetInteger());
    }

    public void testConstructor_5arg_5_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PARIS, test.getZone());
    }

    public void testSetPivotYear_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        assertEquals((Integer) 2010, test.getPivotYear());
    }

    public void testSetPivotYear_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        // removed other assertion
        test.setPivotYear(null);
        assertEquals(null, test.getPivotYear());
    }

    public void testSetPivotYear_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        // removed other assertion
        test.setPivotYear(null);
        // removed other assertion
        test.setPivotYear(2030);
        assertEquals((Integer) 2030, test.getPivotYear());
    }

    public void testSetOffset_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        assertEquals(null, test.getOffsetInteger());
    }

    public void testSetOffset_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        // removed other assertion
        test.setOffset((Integer) 1000);
        assertEquals((Integer) 1000, test.getOffsetInteger());
    }

    public void testSetOffset_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        // removed other assertion
        test.setOffset((Integer) 1000);
        // removed other assertion
        test.setOffset(null);
        assertEquals(null, test.getOffsetInteger());
    }

    public void testSetZone_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        assertEquals(PARIS, test.getZone());
    }

    public void testSetZone_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2010, 2001);
        // removed other assertion
        test.setZone(LONDON);
        assertEquals(LONDON, test.getZone());
    }

    public void testCompute_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        assertEquals(100 - OFFSET_0400, test.computeMillis());
    }

    public void testCompute_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        assertEquals(100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testCompute_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // note that computeMillis(true) differs depending on whether fields are saved or not
        assertEquals(100 - OFFSET_0400, test.computeMillis(true));
    }

    public void testSaveCompute_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis());
    }

    public void testSaveCompute_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveCompute_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        // removed other assertion
        test.saveField(DateTimeFieldType.hourOfDay(), 5);
        assertEquals(5 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveCompute_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        // removed other assertion
        test.saveField(DateTimeFieldType.hourOfDay(), 5);
        // removed other assertion
        assertEquals(5 * MILLIS_PER_HOUR - OFFSET_0400, test.computeMillis(true));
    }

    public void testSaveCompute_5_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        // removed other assertion
        test.saveField(DateTimeFieldType.hourOfDay(), 5);
        // removed other assertion
        // removed other assertion
        assertEquals(5 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        assertEquals(2 * MILLIS_PER_HOUR + 6 * MILLIS_PER_MINUTE + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        assertEquals(true, test.restoreState(state));
    }

    public void testSaveRestoreState_6_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 7);
        assertEquals(2 * MILLIS_PER_HOUR + 7 * MILLIS_PER_MINUTE + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_7_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 7);
        // removed other assertion
        assertEquals(true, test.restoreState(state));
    }

    public void testSaveRestoreState_avoidSideEffects_1_oe() {
        // computeMillis() has side effects, so check works without it
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        Object state = test.saveState();
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        assertEquals(true, test.restoreState(state));
    }

    public void testSaveRestoreState_avoidSideEffects_2_oe() {
        // computeMillis() has side effects, so check works without it
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        Object state = test.saveState();
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 7);
        assertEquals(2 * MILLIS_PER_HOUR + 7 * MILLIS_PER_MINUTE + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_avoidSideEffects_3_oe() {
        // computeMillis() has side effects, so check works without it
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        Object state = test.saveState();
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 7);
        // removed other assertion
        assertEquals(true, test.restoreState(state));
    }

    public void testSaveRestoreState_offset_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_offset_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_offset_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        // removed other assertion
        test.setOffset((Integer) 0);
        assertEquals(2 * MILLIS_PER_HOUR + 100, test.computeMillis(false));
    }

    public void testSaveRestoreState_offset_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        // removed other assertion
        test.setOffset((Integer) 0);
        // removed other assertion
        assertEquals(true, test.restoreState(state));
    }

    public void testSaveRestoreState_zone_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_zone_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_zone_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        // removed other assertion
        test.setZone(DateTimeZone.UTC);
        assertEquals(2 * MILLIS_PER_HOUR + 100, test.computeMillis(false));
    }

    public void testSaveRestoreState_zone_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state = test.saveState();
        // removed other assertion
        test.setZone(DateTimeZone.UTC);
        // removed other assertion
        assertEquals(true, test.restoreState(state));
    }

    public void testSaveRestoreState_text_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), "2", Locale.ENGLISH);
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_text_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), "2", Locale.ENGLISH);
        // removed other assertion
        Object state = test.saveState();
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_text_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), "2", Locale.ENGLISH);
        // removed other assertion
        Object state = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), "6", Locale.ENGLISH);
        assertEquals(2 * MILLIS_PER_HOUR + 6 * MILLIS_PER_MINUTE + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_text_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), "2", Locale.ENGLISH);
        // removed other assertion
        Object state = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), "6", Locale.ENGLISH);
        // removed other assertion
        assertEquals(true, test.restoreState(state));
    }

    public void testSaveRestoreState_twoStates_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_twoStates_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_twoStates_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        assertEquals(2 * MILLIS_PER_HOUR + 6 * MILLIS_PER_MINUTE + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_twoStates_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        Object state2 = test.saveState();
        assertEquals(2 * MILLIS_PER_HOUR + 6 * MILLIS_PER_MINUTE + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_twoStates_5_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.secondOfMinute(), 8);
        assertEquals(2 * MILLIS_PER_HOUR + 6 * MILLIS_PER_MINUTE + 8000 + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_twoStates_6_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.secondOfMinute(), 8);
        // removed other assertion
        assertEquals(true, test.restoreState(state2));
    }

    public void testSaveRestoreState_twoStates_8_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.secondOfMinute(), 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.restoreState(state1));
    }

    public void testSaveRestoreState_twoStates_10_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.secondOfMinute(), 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.restoreState(state2));
    }

    public void testSaveRestoreState_twoStates_12_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.secondOfMinute(), 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.restoreState(state1));
    }

    public void testSaveRestoreState_sameStates_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_sameStates_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        Object state2 = test.saveState();
        assertEquals(2 * MILLIS_PER_HOUR + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_sameStates_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        assertEquals(2 * MILLIS_PER_HOUR + 6 * MILLIS_PER_MINUTE + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_sameStates_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        assertEquals(true, test.restoreState(state2));
    }

    public void testSaveRestoreState_sameStates_6_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 8);
        assertEquals(2 * MILLIS_PER_HOUR + 8 * MILLIS_PER_MINUTE + 100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testSaveRestoreState_sameStates_7_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 8);
        // removed other assertion
        assertEquals(true, test.restoreState(state1));
    }

    public void testSaveRestoreState_sameStates_9_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.restoreState(state2));
    }

    public void testSaveRestoreState_sameStates_11_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        Object state1 = test.saveState();
        Object state2 = test.saveState();
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        test.saveField(DateTimeFieldType.minuteOfHour(), 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.restoreState(state1));
    }

    public void testSaveRestoreState_badType_1_oe() {
        DateTimeParserBucket bucket1 = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        DateTimeParserBucket bucket2 = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2000, 2000);
        assertEquals(false, bucket1.restoreState(null));
    }

    public void testSaveRestoreState_badType_2_oe() {
        DateTimeParserBucket bucket1 = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        DateTimeParserBucket bucket2 = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2000, 2000);
        // removed other assertion
        assertEquals(false, bucket1.restoreState(""));
    }

    public void testSaveRestoreState_badType_3_oe() {
        DateTimeParserBucket bucket1 = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        DateTimeParserBucket bucket2 = new DateTimeParserBucket(100, BUDDHIST_PARIS, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        assertEquals(false, bucket2.restoreState(bucket1.saveState()));
    }

    public void testReset_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testReset_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        assertEquals(LOCALE, test.getLocale());
    }

    public void testReset_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        assertEquals((Integer) 2000, test.getPivotYear());
    }

    public void testReset_4_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.getOffsetInteger());
    }

    public void testReset_5_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ZONE_0400, test.getZone());
    }

    public void testReset_6_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.setOffset((Integer) 200);
        test.setZone(LONDON);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        assertEquals(2 * MILLIS_PER_HOUR + 100 - 200, test.computeMillis(false));
    }

    public void testReset_7_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.setOffset((Integer) 200);
        test.setZone(LONDON);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        assertEquals((Integer) 200, test.getOffsetInteger());
    }

    public void testReset_8_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.setOffset((Integer) 200);
        test.setZone(LONDON);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        // removed other assertion
        assertEquals(LONDON, test.getZone());
    }

    public void testReset_9_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.setOffset((Integer) 200);
        test.setZone(LONDON);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.reset();
        assertEquals(ISO_UTC, test.getChronology());
    }

    public void testReset_10_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.setOffset((Integer) 200);
        test.setZone(LONDON);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.reset();
        // removed other assertion
        assertEquals(LOCALE, test.getLocale());
    }

    public void testReset_11_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.setOffset((Integer) 200);
        test.setZone(LONDON);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.reset();
        // removed other assertion
        // removed other assertion
        assertEquals((Integer) 2000, test.getPivotYear());
    }

    public void testReset_12_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.setOffset((Integer) 200);
        test.setZone(LONDON);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.getOffsetInteger());
    }

    public void testReset_13_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.setOffset((Integer) 200);
        test.setZone(LONDON);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ZONE_0400, test.getZone());
    }

    public void testReset_14_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(100, ISO_0400, LOCALE, 2000, 2000);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.setOffset((Integer) 200);
        test.setZone(LONDON);
        test.saveField(DateTimeFieldType.hourOfDay(), 2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        test.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(100 - OFFSET_0400, test.computeMillis(false));
    }

    public void testParse_1_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(0, ISO_0400, LOCALE, 2000, 2000);
        DateTimeParser parser = new DateTimeParser() {
            public int parseInto(DateTimeParserBucket bucket, String text, int position) {
                bucket.saveField(DateTimeFieldType.hourOfDay(), 2);
                bucket.saveField(DateTimeFieldType.minuteOfHour(), 6);
                return position + 1;
            }
            public int estimateParsedLength() {
                return 1;
            }
        };
        long millis = test.parseMillis(parser, "A");
        assertEquals(2 * MILLIS_PER_HOUR + 6 * MILLIS_PER_MINUTE - OFFSET_0400, millis);
    }

    public void testParse_2_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(0, ISO_0400, LOCALE, 2000, 2000);
        DateTimeParser parser = new DateTimeParser() {
            public int parseInto(DateTimeParserBucket bucket, String text, int position) {
                bucket.saveField(DateTimeFieldType.hourOfDay(), 2);
                bucket.saveField(DateTimeFieldType.minuteOfHour(), 6);
                return position + 1;
            }
            public int estimateParsedLength() {
                return 1;
            }
        };
        long millis = test.parseMillis(parser, "A");
        // removed other assertion
        millis = test.parseMillis(parser, "B");
        assertEquals(2 * MILLIS_PER_HOUR + 6 * MILLIS_PER_MINUTE - OFFSET_0400, millis);
    }

    public void testParse_3_oe() {
        DateTimeParserBucket test = new DateTimeParserBucket(0, ISO_0400, LOCALE, 2000, 2000);
        DateTimeParser parser = new DateTimeParser() {
            public int parseInto(DateTimeParserBucket bucket, String text, int position) {
                bucket.saveField(DateTimeFieldType.hourOfDay(), 2);
                bucket.saveField(DateTimeFieldType.minuteOfHour(), 6);
                return position + 1;
            }
            public int estimateParsedLength() {
                return 1;
            }
        };
        long millis = test.parseMillis(parser, "A");
        // removed other assertion
        millis = test.parseMillis(parser, "B");
        // removed other assertion
        millis = test.parseMillis(parser, "C");
        assertEquals(2 * MILLIS_PER_HOUR + 6 * MILLIS_PER_MINUTE - OFFSET_0400, millis);
    }

}
