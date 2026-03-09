/*
 *  Copyright 2001-2011 Stephen Colebourne
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.base.AbstractInterval;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.LenientChronology;

/**
 * This class is a Junit unit test for Instant.
 *
 * @author Stephen Colebourne
 */
public class TestInterval_Basics_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone MOSCOW = DateTimeZone.forID("Europe/Moscow");
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
    private Interval interval37;
    private Interval interval33;

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
        return new TestSuite(TestInterval_Basics_OE25Dev.class);
    }

    public TestInterval_Basics_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        originalDateTimeZone = DateTimeZone.getDefault();
        originalTimeZone = TimeZone.getDefault();
        originalLocale = Locale.getDefault();
        DateTimeZone.setDefault(PARIS);
        TimeZone.setDefault(PARIS.toTimeZone());
        Locale.setDefault(Locale.FRANCE);
        interval37 = new Interval(3, 7);
        interval33 = new Interval(3, 3);
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
    public void testGetMillis() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(TEST_TIME1,test.getStartMillis());
        assertEquals(TEST_TIME1,test.getStart().getMillis());
        assertEquals(TEST_TIME2,test.getEndMillis());
        assertEquals(TEST_TIME2,test.getEnd().getMillis());
        assertEquals(TEST_TIME2 - TEST_TIME1,test.toDurationMillis());
        assertEquals(TEST_TIME2 - TEST_TIME1,test.toDuration().getMillis());
    }

    public void testGetDuration1() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(TEST_TIME2 - TEST_TIME1,test.toDurationMillis());
        assertEquals(TEST_TIME2 - TEST_TIME1,test.toDuration().getMillis());
    }

    public void testGetDuration2() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME1);
        assertSame(Duration.ZERO,test.toDuration());
    }

    public void testGetDuration3() {
        Interval test = new Interval(Long.MIN_VALUE, -2);
        assertEquals(-2L - Long.MIN_VALUE,test.toDurationMillis());
    }

    public void testEqualsHashCode() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(true,test1.equals(test2));
        assertEquals(true,test2.equals(test1));
        assertEquals(true,test1.equals(test1));
        assertEquals(true,test2.equals(test2));
        assertEquals(true,test1.hashCode()== test2.hashCode());
        assertEquals(true,test1.hashCode()== test1.hashCode());
        assertEquals(true,test2.hashCode()== test2.hashCode());
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        assertEquals(false,test1.equals(test3));
        assertEquals(false,test2.equals(test3));
        assertEquals(false,test3.equals(test1));
        assertEquals(false,test3.equals(test2));
        assertEquals(false,test1.hashCode()== test3.hashCode());
        assertEquals(false,test2.hashCode()== test3.hashCode());
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        assertEquals(true,test4.equals(test4));
        assertEquals(false,test1.equals(test4));
        assertEquals(false,test2.equals(test4));
        assertEquals(false,test4.equals(test1));
        assertEquals(false,test4.equals(test2));
        assertEquals(false,test1.hashCode()== test4.hashCode());
        assertEquals(false,test2.hashCode()== test4.hashCode());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(true,test1.equals(test5));
        assertEquals(true,test2.equals(test5));
        assertEquals(false,test3.equals(test5));
        assertEquals(true,test5.equals(test1));
        assertEquals(true,test5.equals(test2));
        assertEquals(false,test5.equals(test3));
        assertEquals(true,test1.hashCode()== test5.hashCode());
        assertEquals(true,test2.hashCode()== test5.hashCode());
        assertEquals(false,test3.hashCode()== test5.hashCode());
        
        assertEquals(false,test1.equals("Hello"));
        assertEquals(true,test1.equals(new MockInterval()));
        assertEquals(false,test1.equals(new DateTime(TEST_TIME1)));
    }

    class MockInterval extends AbstractInterval {
        public MockInterval() {
            super();
        }
        public Chronology getChronology() {
            return ISOChronology.getInstance();
        }
        public long getStartMillis() {
            return TEST_TIME1;
        }
        public long getEndMillis() {
            return TEST_TIME2;
        }
    }

    public void testEqualsHashCodeLenient() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertEquals(true,test1.equals(test2));
        assertEquals(true,test2.equals(test1));
        assertEquals(true,test1.equals(test1));
        assertEquals(true,test2.equals(test2));
        assertEquals(true,test1.hashCode()== test2.hashCode());
        assertEquals(true,test1.hashCode()== test1.hashCode());
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCodeStrict() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertEquals(true,test1.equals(test2));
        assertEquals(true,test2.equals(test1));
        assertEquals(true,test1.equals(test1));
        assertEquals(true,test2.equals(test2));
        assertEquals(true,test1.hashCode()== test2.hashCode());
        assertEquals(true,test1.hashCode()== test1.hashCode());
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

    //-----------------------------------------------------------------------
    public void test_useCase_ContainsOverlapAbutGap() {
        // this is a simple test to ensure that the use case of these methods is OK
        // when comparing any two intervals they can be in one and only one of these states
        // (a) have a gap between them, (b) abut or (c) overlap
        // contains is a subset of overlap
        Interval test1020 = new Interval(10, 20);
        
        // [4,8) [10,20) - gap
        Interval interval = new Interval(4, 8);
        assertNotNull(test1020.gap(interval));
        assertEquals(false,test1020.abuts(interval));
        assertEquals(false,test1020.overlaps(interval));
        assertEquals(false,test1020.contains(interval));
        assertNotNull(interval.gap(test1020));
        assertEquals(false,interval.abuts(test1020));
        assertEquals(false,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
        
        // [6,10) [10,20) - abuts
        interval = new Interval(6, 10);
        assertNull(test1020.gap(interval));
        assertEquals(true,test1020.abuts(interval));
        assertEquals(false,test1020.overlaps(interval));
        assertEquals(false,test1020.contains(interval));
        assertNull(interval.gap(test1020));
        assertEquals(true,interval.abuts(test1020));
        assertEquals(false,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
        
        // [8,12) [10,20) - overlaps
        interval = new Interval(8, 12);
        assertNull(test1020.gap(interval));
        assertEquals(false,test1020.abuts(interval));
        assertEquals(true,test1020.overlaps(interval));
        assertEquals(false,test1020.contains(interval));
        assertNull(interval.gap(test1020));
        assertEquals(false,interval.abuts(test1020));
        assertEquals(true,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
        
        // [10,14) [10,20) - overlaps and contains-one-way
        interval = new Interval(10, 14);
        assertNull(test1020.gap(interval));
        assertEquals(false,test1020.abuts(interval));
        assertEquals(true,test1020.overlaps(interval));
        assertEquals(true,test1020.contains(interval));
        assertNull(interval.gap(test1020));
        assertEquals(false,interval.abuts(test1020));
        assertEquals(true,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
        
        // [10,20) [10,20) - overlaps and contains-both-ways
        assertNull(test1020.gap(interval));
        assertEquals(false,test1020.abuts(test1020));
        assertEquals(true,test1020.overlaps(test1020));
        assertEquals(true,test1020.contains(test1020));
        
        // [10,20) [16,20) - overlaps and contains-one-way
        interval = new Interval(16, 20);
        assertNull(test1020.gap(interval));
        assertEquals(false,test1020.abuts(interval));
        assertEquals(true,test1020.overlaps(interval));
        assertEquals(true,test1020.contains(interval));
        assertNull(interval.gap(test1020));
        assertEquals(false,interval.abuts(test1020));
        assertEquals(true,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
        
        // [10,20) [18,22) - overlaps
        interval = new Interval(18, 22);
        assertNull(test1020.gap(interval));
        assertEquals(false,test1020.abuts(interval));
        assertEquals(true,test1020.overlaps(interval));
        assertEquals(false,test1020.contains(interval));
        assertNull(interval.gap(test1020));
        assertEquals(false,interval.abuts(test1020));
        assertEquals(true,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
        
        // [10,20) [20,24) - abuts
        interval = new Interval(20, 24);
        assertNull(test1020.gap(interval));
        assertEquals(true,test1020.abuts(interval));
        assertEquals(false,test1020.overlaps(interval));
        assertEquals(false,test1020.contains(interval));
        assertNull(interval.gap(test1020));
        assertEquals(true,interval.abuts(test1020));
        assertEquals(false,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
        
        // [10,20) [22,26) - gap
        interval = new Interval(22, 26);
        assertNotNull(test1020.gap(interval));
        assertEquals(false,test1020.abuts(interval));
        assertEquals(false,test1020.overlaps(interval));
        assertEquals(false,test1020.contains(interval));
        assertNotNull(interval.gap(test1020));
        assertEquals(false,interval.abuts(test1020));
        assertEquals(false,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
    }

    //-----------------------------------------------------------------------
    public void test_useCase_ContainsOverlapAbutGap_zeroDuration() {
        // this is a simple test to ensure that the use case of these methods
        // is OK when considering a zero duration inerval
        // when comparing any two intervals they can be in one and only one of these states
        // (a) have a gap between them, (b) abut or (c) overlap
        // contains is a subset of overlap
        Interval test1020 = new Interval(10, 20);
        
        // [8,8) [10,20) - gap
        Interval interval = new Interval(8, 8);
        assertNotNull(test1020.gap(interval));
        assertEquals(false,test1020.abuts(interval));
        assertEquals(false,test1020.overlaps(interval));
        assertEquals(false,test1020.contains(interval));
        assertNotNull(interval.gap(test1020));
        assertEquals(false,interval.abuts(test1020));
        assertEquals(false,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
        
        // [10,10) [10,20) - abuts and contains-one-way
        interval = new Interval(10, 10);
        assertNull(test1020.gap(interval));
        assertEquals(true,test1020.abuts(interval));
        assertEquals(false,test1020.overlaps(interval));// abuts,so can't overlap assertEquals(true,test1020.contains(interval));// normal contains zero-duration assertNull(interval.gap(test1020));
        assertEquals(true,interval.abuts(test1020));
        assertEquals(false,interval.overlaps(test1020));// abuts,so can't overlap assertEquals(false,interval.contains(test1020));// zero-duration does not contain normal interval = new Interval(12,12);
        assertNull(test1020.gap(interval));
        assertEquals(false,test1020.abuts(interval));
        assertEquals(true,test1020.overlaps(interval));
        assertEquals(true,test1020.contains(interval));// normal contains zero-duration assertNull(interval.gap(test1020));
        assertEquals(false,interval.abuts(test1020));
        assertEquals(true,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));// zero-duration does not contain normal interval = new Interval(20,20);
        assertNull(test1020.gap(interval));
        assertEquals(true,test1020.abuts(interval));
        assertEquals(false,test1020.overlaps(interval));
        assertEquals(false,test1020.contains(interval));
        assertNull(interval.gap(test1020));
        assertEquals(true,interval.abuts(test1020));
        assertEquals(false,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
        
        // [10,20) [22,22) - gap
        interval = new Interval(22, 22);
        assertNotNull(test1020.gap(interval));
        assertEquals(false,test1020.abuts(interval));
        assertEquals(false,test1020.overlaps(interval));
        assertEquals(false,test1020.contains(interval));
        assertNotNull(interval.gap(test1020));
        assertEquals(false,interval.abuts(test1020));
        assertEquals(false,interval.overlaps(test1020));
        assertEquals(false,interval.contains(test1020));
    }

    //-----------------------------------------------------------------------
    public void test_useCase_ContainsOverlapAbutGap_bothZeroDuration() {
        // this is a simple test to ensure that the use case of these methods
        // is OK when considering two zero duration inervals
        // this is the simplest case, as the two intervals either have a gap or not
        // if not, then they are equal and abut
        Interval test0808 = new Interval(8, 8);
        Interval test1010 = new Interval(10, 10);
        
        // [8,8) [10,10) - gap
        assertNotNull(test1010.gap(test0808));
        assertEquals(false,test1010.abuts(test0808));
        assertEquals(false,test1010.overlaps(test0808));
        assertEquals(false,test1010.contains(test0808));
        assertNotNull(test0808.gap(test1010));
        assertEquals(false,test0808.abuts(test1010));
        assertEquals(false,test0808.overlaps(test1010));
        assertEquals(false,test0808.contains(test1010));
        
        // [10,10) [10,10) - abuts
        assertNull(test1010.gap(test1010));
        assertEquals(true,test1010.abuts(test1010));
        assertEquals(false,test1010.overlaps(test1010));
        assertEquals(false,test1010.contains(test1010));
    }

    //-----------------------------------------------------------------------
    public void testContains_long() {
        assertEquals(false,interval37.contains(2));// value before assertEquals(true,interval37.contains(3));
        assertEquals(true,interval37.contains(4));
        assertEquals(true,interval37.contains(5));
        assertEquals(true,interval37.contains(6));
        assertEquals(false,interval37.contains(7));  // value after
        assertEquals(false,interval37.contains(8));  // value after
    }

    public void testContains_long_zeroDuration() {
        assertEquals(false,interval33.contains(2));  // value before
        assertEquals(false,interval33.contains(3));  // zero length duration contains nothing
        assertEquals(false,interval33.contains(4));  // value after
    }

    //-----------------------------------------------------------------------
    public void testContainsNow() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(false,interval37.containsNow());// value before DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(true,interval37.containsNow());
        DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(true,interval37.containsNow());
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(true,interval37.containsNow());
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(false,interval37.containsNow());// value after DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(false,interval37.containsNow());// value after DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(false,interval33.containsNow());// value before DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(false,interval33.containsNow());// zero length duration contains nothing DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(false,interval33.containsNow());  // value after
    }

    //-----------------------------------------------------------------------
    public void testContains_RI() {
        assertEquals(false,interval37.contains(new Instant(2)));// value before assertEquals(true,interval37.contains(new Instant(3)));
        assertEquals(true,interval37.contains(new Instant(4)));
        assertEquals(true,interval37.contains(new Instant(5)));
        assertEquals(true,interval37.contains(new Instant(6)));
        assertEquals(false,interval37.contains(new Instant(7)));  // value after
        assertEquals(false,interval37.contains(new Instant(8)));  // value after
    }

    public void testContains_RI_null() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(false,interval37.contains((ReadableInstant)null));// value before DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(true,interval37.contains((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(true,interval37.contains((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(true,interval37.contains((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(false,interval37.contains((ReadableInstant)null));// value after DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(false,interval37.contains((ReadableInstant)null));  // value after
    }

    public void testContains_RI_zeroDuration() {
        assertEquals(false,interval33.contains(new Instant(2)));  // value before
        assertEquals(false,interval33.contains(new Instant(3)));  // zero length duration contains nothing
        assertEquals(false,interval33.contains(new Instant(4)));  // value after
    }

    //-----------------------------------------------------------------------
    public void testContains_RInterval() {
        assertEquals(false,interval37.contains(new Interval(1,2)));// gap before assertEquals(false,interval37.contains(new Interval(2,2)));// gap before assertEquals(false,interval37.contains(new Interval(2,3)));// abuts before assertEquals(true,interval37.contains(new Interval(3,3)));
        
        assertEquals(false,interval37.contains(new Interval(2,4)));// starts before assertEquals(true,interval37.contains(new Interval(3,4)));
        assertEquals(true,interval37.contains(new Interval(4,4)));
        
        assertEquals(false,interval37.contains(new Interval(2,6)));// starts before assertEquals(true,interval37.contains(new Interval(3,6)));
        assertEquals(true,interval37.contains(new Interval(4,6)));
        assertEquals(true,interval37.contains(new Interval(5,6)));
        assertEquals(true,interval37.contains(new Interval(6,6)));
        
        assertEquals(false,interval37.contains(new Interval(2,7)));// starts before assertEquals(true,interval37.contains(new Interval(3,7)));
        assertEquals(true,interval37.contains(new Interval(4,7)));
        assertEquals(true,interval37.contains(new Interval(5,7)));
        assertEquals(true,interval37.contains(new Interval(6,7)));
        assertEquals(false,interval37.contains(new Interval(7,7)));  // abuts after
        
        assertEquals(false,interval37.contains(new Interval(2,8)));  // ends after
        assertEquals(false,interval37.contains(new Interval(3,8)));  // ends after
        assertEquals(false,interval37.contains(new Interval(4,8)));  // ends after
        assertEquals(false,interval37.contains(new Interval(5,8)));  // ends after
        assertEquals(false,interval37.contains(new Interval(6,8)));  // ends after
        assertEquals(false,interval37.contains(new Interval(7,8)));  // abuts after
        assertEquals(false,interval37.contains(new Interval(8,8)));  // gap after
        
        assertEquals(false,interval37.contains(new Interval(8,9)));  // gap after
        assertEquals(false,interval37.contains(new Interval(9,9)));  // gap after
    }

    public void testContains_RInterval_null() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(false,interval37.contains((ReadableInterval)null));// gap before DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(true,interval37.contains((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(true,interval37.contains((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(true,interval37.contains((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(false,interval37.contains((ReadableInterval)null));// abuts after DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(false,interval37.contains((ReadableInterval)null));  // gap after
    }

    public void testContains_RInterval_zeroDuration() {
        assertEquals(false,interval33.contains(interval33));// zero length duration contains nothing assertEquals(false,interval33.contains(interval37));// zero-duration cannot contain anything assertEquals(true,interval37.contains(interval33));
        assertEquals(false,interval33.contains(new Interval(1,2)));// zero-duration cannot contain anything assertEquals(false,interval33.contains(new Interval(8,9)));// zero-duration cannot contain anything assertEquals(false,interval33.contains(new Interval(1,9)));// zero-duration cannot contain anything DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(false,interval33.contains((ReadableInterval)null));// gap before DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(false,interval33.contains((ReadableInterval)null));// zero length duration contains nothing DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(false,interval33.contains((ReadableInterval)null));  // gap after
    }

    //-----------------------------------------------------------------------
    public void testOverlaps_RInterval() {
        assertEquals(false,interval37.overlaps(new Interval(1,2)));// gap before assertEquals(false,interval37.overlaps(new Interval(2,2)));// gap before assertEquals(false,interval37.overlaps(new Interval(2,3)));// abuts before assertEquals(false,interval37.overlaps(new Interval(3,3)));// abuts before assertEquals(true,interval37.overlaps(new Interval(2,4)));
        assertEquals(true,interval37.overlaps(new Interval(3,4)));
        assertEquals(true,interval37.overlaps(new Interval(4,4)));
        
        assertEquals(true,interval37.overlaps(new Interval(2,6)));
        assertEquals(true,interval37.overlaps(new Interval(3,6)));
        assertEquals(true,interval37.overlaps(new Interval(4,6)));
        assertEquals(true,interval37.overlaps(new Interval(5,6)));
        assertEquals(true,interval37.overlaps(new Interval(6,6)));
        
        assertEquals(true,interval37.overlaps(new Interval(2,7)));
        assertEquals(true,interval37.overlaps(new Interval(3,7)));
        assertEquals(true,interval37.overlaps(new Interval(4,7)));
        assertEquals(true,interval37.overlaps(new Interval(5,7)));
        assertEquals(true,interval37.overlaps(new Interval(6,7)));
        assertEquals(false,interval37.overlaps(new Interval(7,7)));// abuts after assertEquals(true,interval37.overlaps(new Interval(2,8)));
        assertEquals(true,interval37.overlaps(new Interval(3,8)));
        assertEquals(true,interval37.overlaps(new Interval(4,8)));
        assertEquals(true,interval37.overlaps(new Interval(5,8)));
        assertEquals(true,interval37.overlaps(new Interval(6,8)));
        assertEquals(false,interval37.overlaps(new Interval(7,8)));  // abuts after
        assertEquals(false,interval37.overlaps(new Interval(8,8)));  // gap after
        
        assertEquals(false,interval37.overlaps(new Interval(8,9)));  // gap after
        assertEquals(false,interval37.overlaps(new Interval(9,9)));  // gap after
    }

    public void testOverlaps_RInterval_null() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(false,interval37.overlaps((ReadableInterval)null));// gap before DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(false,interval37.overlaps((ReadableInterval)null));// abuts before DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(true,interval37.overlaps((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(true,interval37.overlaps((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(false,interval37.overlaps((ReadableInterval)null));// abuts after DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(false,interval37.overlaps((ReadableInterval)null));// gap after DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(false,interval33.overlaps((ReadableInterval)null));  // abuts before and after
    }

    public void testOverlaps_RInterval_zeroDuration() {
        assertEquals(false,interval33.overlaps(interval33));// abuts before and after assertEquals(false,interval33.overlaps(interval37));// abuts before assertEquals(false,interval37.overlaps(interval33));// abuts before assertEquals(false,interval33.overlaps(new Interval(1,2)));
        assertEquals(false,interval33.overlaps(new Interval(8,9)));
        assertEquals(true,interval33.overlaps(new Interval(1,9)));
    }

    //-----------------------------------------------------------------------
    public void testOverlap_RInterval() {
        assertEquals(null,interval37.overlap(new Interval(1,2)));// gap before assertEquals(null,interval37.overlap(new Interval(2,2)));// gap before assertEquals(null,interval37.overlap(new Interval(2,3)));// abuts before assertEquals(null,interval37.overlap(new Interval(3,3)));// abuts before assertEquals(new Interval(3,4),interval37.overlap(new Interval(2,4)));// truncated start assertEquals(new Interval(3,4),interval37.overlap(new Interval(3,4)));
        assertEquals(new Interval(4,4),interval37.overlap(new Interval(4,4)));
        
        assertEquals(new Interval(3,7),interval37.overlap(new Interval(2,7)));// truncated start assertEquals(new Interval(3,7),interval37.overlap(new Interval(3,7)));
        assertEquals(new Interval(4,7),interval37.overlap(new Interval(4,7)));
        assertEquals(new Interval(5,7),interval37.overlap(new Interval(5,7)));
        assertEquals(new Interval(6,7),interval37.overlap(new Interval(6,7)));
        assertEquals(null,interval37.overlap(new Interval(7,7)));  // abuts after
        
        assertEquals(new Interval(3,7),interval37.overlap(new Interval(2,8)));  // truncated start and end
        assertEquals(new Interval(3,7),interval37.overlap(new Interval(3,8)));  // truncated end
        assertEquals(new Interval(4,7),interval37.overlap(new Interval(4,8)));  // truncated end
        assertEquals(new Interval(5,7),interval37.overlap(new Interval(5,8)));  // truncated end
        assertEquals(new Interval(6,7),interval37.overlap(new Interval(6,8)));  // truncated end
        assertEquals(null,interval37.overlap(new Interval(7,8)));  // abuts after
        assertEquals(null,interval37.overlap(new Interval(8,8)));  // gap after
    }

    public void testOverlap_RInterval_null() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(null,interval37.overlap((ReadableInterval)null));// gap before DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(null,interval37.overlap((ReadableInterval)null));// abuts before DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(new Interval(4,4),interval37.overlap((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(new Interval(6,6),interval37.overlap((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(null,interval37.overlap((ReadableInterval)null));// abuts after DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(null,interval37.overlap((ReadableInterval)null));// gap after DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(null,interval33.overlap((ReadableInterval)null));  // abuts before and after
    }

    public void testOverlap_RInterval_zone() {
        Interval testA = new Interval(new DateTime(3, LONDON), new DateTime(7, LONDON));
        assertEquals(ISOChronology.getInstance(LONDON),testA.getChronology());
        
        Interval testB = new Interval(new DateTime(4, MOSCOW), new DateTime(8, MOSCOW));
        assertEquals(ISOChronology.getInstance(MOSCOW),testB.getChronology());
        
        Interval resultAB = testA.overlap(testB);
        assertEquals(ISOChronology.getInstance(LONDON),resultAB.getChronology());
        
        Interval resultBA = testB.overlap(testA);
        assertEquals(ISOChronology.getInstance(MOSCOW),resultBA.getChronology());
    }

    public void testOverlap_RInterval_zoneUTC() {
        Interval testA = new Interval(new Instant(3), new Instant(7));
        assertEquals(ISOChronology.getInstanceUTC(),testA.getChronology());
        
        Interval testB = new Interval(new Instant(4), new Instant(8));
        assertEquals(ISOChronology.getInstanceUTC(),testB.getChronology());
        
        Interval result = testA.overlap(testB);
        assertEquals(ISOChronology.getInstanceUTC(),result.getChronology());
    }

    //-----------------------------------------------------------------------
    public void testGap_RInterval() {
        assertEquals(new Interval(1,3),interval37.gap(new Interval(0,1)));
        assertEquals(new Interval(1,3),interval37.gap(new Interval(1,1)));
        
        assertEquals(null,interval37.gap(new Interval(2,3)));// abuts before assertEquals(null,interval37.gap(new Interval(3,3)));// abuts before assertEquals(null,interval37.gap(new Interval(4,6)));// overlaps assertEquals(null,interval37.gap(new Interval(3,7)));// overlaps assertEquals(null,interval37.gap(new Interval(6,7)));// overlaps assertEquals(null,interval37.gap(new Interval(7,7)));// abuts after assertEquals(null,interval37.gap(new Interval(6,8)));// overlaps assertEquals(null,interval37.gap(new Interval(7,8)));// abuts after assertEquals(new Interval(7,8),interval37.gap(new Interval(8,8)));
        
        assertEquals(null,interval37.gap(new Interval(6,9)));// overlaps assertEquals(null,interval37.gap(new Interval(7,9)));// abuts after assertEquals(new Interval(7,8),interval37.gap(new Interval(8,9)));
        assertEquals(new Interval(7,9),interval37.gap(new Interval(9,9)));
    }

    public void testGap_RInterval_null() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(new Interval(2,3),interval37.gap((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(null,interval37.gap((ReadableInterval)null));// abuts before DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(null,interval37.gap((ReadableInterval)null));// overlaps DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(null,interval37.gap((ReadableInterval)null));// overlaps DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(null,interval37.gap((ReadableInterval)null));// abuts after DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(new Interval(7,8),interval37.gap((ReadableInterval)null));
    }

    public void testGap_RInterval_zone() {
        Interval testA = new Interval(new DateTime(3, LONDON), new DateTime(7, LONDON));
        assertEquals(ISOChronology.getInstance(LONDON),testA.getChronology());
        
        Interval testB = new Interval(new DateTime(1, MOSCOW), new DateTime(2, MOSCOW));
        assertEquals(ISOChronology.getInstance(MOSCOW),testB.getChronology());
        
        Interval resultAB = testA.gap(testB);
        assertEquals(ISOChronology.getInstance(LONDON),resultAB.getChronology());
        
        Interval resultBA = testB.gap(testA);
        assertEquals(ISOChronology.getInstance(MOSCOW),resultBA.getChronology());
    }

    public void testGap_RInterval_zoneUTC() {
        Interval testA = new Interval(new Instant(3), new Instant(7));
        assertEquals(ISOChronology.getInstanceUTC(),testA.getChronology());
        
        Interval testB = new Interval(new Instant(1), new Instant(2));
        assertEquals(ISOChronology.getInstanceUTC(),testB.getChronology());
        
        Interval result = testA.gap(testB);
        assertEquals(ISOChronology.getInstanceUTC(),result.getChronology());
    }

    //-----------------------------------------------------------------------
    public void testAbuts_RInterval() {
        assertEquals(false,interval37.abuts(new Interval(1,2)));// gap before assertEquals(false,interval37.abuts(new Interval(2,2)));// gap before assertEquals(true,interval37.abuts(new Interval(2,3)));
        assertEquals(true,interval37.abuts(new Interval(3,3)));
        
        assertEquals(false,interval37.abuts(new Interval(2,4)));// overlaps assertEquals(false,interval37.abuts(new Interval(3,4)));// overlaps assertEquals(false,interval37.abuts(new Interval(4,4)));// overlaps assertEquals(false,interval37.abuts(new Interval(2,6)));// overlaps assertEquals(false,interval37.abuts(new Interval(3,6)));// overlaps assertEquals(false,interval37.abuts(new Interval(4,6)));// overlaps assertEquals(false,interval37.abuts(new Interval(5,6)));// overlaps assertEquals(false,interval37.abuts(new Interval(6,6)));// overlaps assertEquals(false,interval37.abuts(new Interval(2,7)));// overlaps assertEquals(false,interval37.abuts(new Interval(3,7)));// overlaps assertEquals(false,interval37.abuts(new Interval(4,7)));// overlaps assertEquals(false,interval37.abuts(new Interval(5,7)));// overlaps assertEquals(false,interval37.abuts(new Interval(6,7)));// overlaps assertEquals(true,interval37.abuts(new Interval(7,7)));
        
        assertEquals(false,interval37.abuts(new Interval(2,8)));// overlaps assertEquals(false,interval37.abuts(new Interval(3,8)));// overlaps assertEquals(false,interval37.abuts(new Interval(4,8)));// overlaps assertEquals(false,interval37.abuts(new Interval(5,8)));// overlaps assertEquals(false,interval37.abuts(new Interval(6,8)));// overlaps assertEquals(true,interval37.abuts(new Interval(7,8)));
        assertEquals(false,interval37.abuts(new Interval(8,8)));  // gap after
        
        assertEquals(false,interval37.abuts(new Interval(8,9)));  // gap after
        assertEquals(false,interval37.abuts(new Interval(9,9)));  // gap after
    }

    public void testAbuts_RInterval_null() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(false,interval37.abuts((ReadableInterval)null));// gap before DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(true,interval37.abuts((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(false,interval37.abuts((ReadableInterval)null));// overlaps DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(false,interval37.abuts((ReadableInterval)null));// overlaps DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(true,interval37.abuts((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(false,interval37.abuts((ReadableInterval)null));  // gap after
    }

    //-----------------------------------------------------------------------
    public void testIsEqual_RI() {
        assertEquals(false,interval37.isEqual(interval33));
        assertEquals(true,interval37.isEqual(interval37));
    }

    //-----------------------------------------------------------------------
    public void testIsBefore_long() {
        assertEquals(false,interval37.isBefore(2));
        assertEquals(false,interval37.isBefore(3));
        assertEquals(false,interval37.isBefore(4));
        assertEquals(false,interval37.isBefore(5));
        assertEquals(false,interval37.isBefore(6));
        assertEquals(true,interval37.isBefore(7));
        assertEquals(true,interval37.isBefore(8));
    }

    public void testIsBeforeNow() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(false,interval37.isBeforeNow());
        DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(false,interval37.isBeforeNow());
        DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(false,interval37.isBeforeNow());
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(false,interval37.isBeforeNow());
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(true,interval37.isBeforeNow());
        DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(true,interval37.isBeforeNow());
    }

    public void testIsBefore_RI() {
        assertEquals(false,interval37.isBefore(new Instant(2)));
        assertEquals(false,interval37.isBefore(new Instant(3)));
        assertEquals(false,interval37.isBefore(new Instant(4)));
        assertEquals(false,interval37.isBefore(new Instant(5)));
        assertEquals(false,interval37.isBefore(new Instant(6)));
        assertEquals(true,interval37.isBefore(new Instant(7)));
        assertEquals(true,interval37.isBefore(new Instant(8)));
    }

    public void testIsBefore_RI_null() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(false,interval37.isBefore((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(false,interval37.isBefore((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(false,interval37.isBefore((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(false,interval37.isBefore((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(true,interval37.isBefore((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(true,interval37.isBefore((ReadableInstant)null));
    }

    public void testIsBefore_RInterval() {
        assertEquals(false,interval37.isBefore(new Interval(Long.MIN_VALUE,2)));
        assertEquals(false,interval37.isBefore(new Interval(Long.MIN_VALUE,3)));
        assertEquals(false,interval37.isBefore(new Interval(Long.MIN_VALUE,4)));
        
        assertEquals(false,interval37.isBefore(new Interval(6,Long.MAX_VALUE)));
        assertEquals(true,interval37.isBefore(new Interval(7,Long.MAX_VALUE)));
        assertEquals(true,interval37.isBefore(new Interval(8,Long.MAX_VALUE)));
    }

    public void testIsBefore_RInterval_null() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(false,interval37.isBefore((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(false,interval37.isBefore((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(false,interval37.isBefore((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(false,interval37.isBefore((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(true,interval37.isBefore((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(true,interval37.isBefore((ReadableInterval)null));
    }

    //-----------------------------------------------------------------------
    public void testIsAfter_long() {
        assertEquals(true,interval37.isAfter(2));
        assertEquals(false,interval37.isAfter(3));
        assertEquals(false,interval37.isAfter(4));
        assertEquals(false,interval37.isAfter(5));
        assertEquals(false,interval37.isAfter(6));
        assertEquals(false,interval37.isAfter(7));
        assertEquals(false,interval37.isAfter(8));
    }

    public void testIsAfterNow() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(true,interval37.isAfterNow());
        DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(false,interval37.isAfterNow());
        DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(false,interval37.isAfterNow());
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(false,interval37.isAfterNow());
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(false,interval37.isAfterNow());
        DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(false,interval37.isAfterNow());
    }

    public void testIsAfter_RI() {
        assertEquals(true,interval37.isAfter(new Instant(2)));
        assertEquals(false,interval37.isAfter(new Instant(3)));
        assertEquals(false,interval37.isAfter(new Instant(4)));
        assertEquals(false,interval37.isAfter(new Instant(5)));
        assertEquals(false,interval37.isAfter(new Instant(6)));
        assertEquals(false,interval37.isAfter(new Instant(7)));
        assertEquals(false,interval37.isAfter(new Instant(8)));
    }

    public void testIsAfter_RI_null() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(true,interval37.isAfter((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(false,interval37.isAfter((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(false,interval37.isAfter((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(false,interval37.isAfter((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(false,interval37.isAfter((ReadableInstant)null));
        DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(false,interval37.isAfter((ReadableInstant)null));
    }

    public void testIsAfter_RInterval() {
        assertEquals(true,interval37.isAfter(new Interval(Long.MIN_VALUE,2)));
        assertEquals(true,interval37.isAfter(new Interval(Long.MIN_VALUE,3)));
        assertEquals(false,interval37.isAfter(new Interval(Long.MIN_VALUE,4)));
        
        assertEquals(false,interval37.isAfter(new Interval(6,Long.MAX_VALUE)));
        assertEquals(false,interval37.isAfter(new Interval(7,Long.MAX_VALUE)));
        assertEquals(false,interval37.isAfter(new Interval(8,Long.MAX_VALUE)));
    }

    public void testIsAfter_RInterval_null() {
        DateTimeUtils.setCurrentMillisFixed(2);
        assertEquals(true,interval37.isAfter((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(3);
        assertEquals(true,interval37.isAfter((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(4);
        assertEquals(false,interval37.isAfter((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(6);
        assertEquals(false,interval37.isAfter((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(7);
        assertEquals(false,interval37.isAfter((ReadableInterval)null));
        DateTimeUtils.setCurrentMillisFixed(8);
        assertEquals(false,interval37.isAfter((ReadableInterval)null));
    }

    //-----------------------------------------------------------------------
    public void testToInterval1() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval result = test.toInterval();
        assertSame(test,result);
    }

    //-----------------------------------------------------------------------
    public void testToMutableInterval1() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        MutableInterval result = test.toMutableInterval();
        assertEquals(test,result);
    }

    //-----------------------------------------------------------------------
    public void testToPeriod() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, COPTIC_PARIS);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, COPTIC_PARIS);
        Interval base = new Interval(dt1, dt2);
        
        Period test = base.toPeriod();
        Period expected = new Period(dt1, dt2, PeriodType.standard());
        assertEquals(expected,test);
    }

    //-----------------------------------------------------------------------
    public void testToPeriod_PeriodType1() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, COPTIC_PARIS);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, COPTIC_PARIS);
        Interval base = new Interval(dt1, dt2);
        
        Period test = base.toPeriod(null);
        Period expected = new Period(dt1, dt2, PeriodType.standard());
        assertEquals(expected,test);
    }

    public void testToPeriod_PeriodType2() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18);
        Interval base = new Interval(dt1, dt2);
        
        Period test = base.toPeriod(PeriodType.yearWeekDayTime());
        Period expected = new Period(dt1, dt2, PeriodType.yearWeekDayTime());
        assertEquals(expected,test);
    }

    //-----------------------------------------------------------------------
    public void testSerialization() throws Exception {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Interval result = (Interval) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

    //-----------------------------------------------------------------------
    public void testToString() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, DateTimeZone.UTC);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, DateTimeZone.UTC);
        Interval test = new Interval(dt1, dt2);
        assertEquals("2004-06-09T07:08:09.010Z/2005-08-13T12:14:16.018Z",test.toString());
    }

    public void testToString_reparse() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, DateTimeZone.getDefault());
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, DateTimeZone.getDefault());
        Interval test = new Interval(dt1, dt2);
        assertEquals(test,new Interval(test.toString()));
    }

    //-----------------------------------------------------------------------
    public void testWithChronology1() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withChronology(BuddhistChronology.getInstance());
        assertEquals(new Interval(TEST_TIME1,TEST_TIME2,BuddhistChronology.getInstance()),test);
    }

    public void testWithChronology2() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withChronology(null);
        assertEquals(new Interval(TEST_TIME1,TEST_TIME2,ISOChronology.getInstance()),test);
    }

    public void testWithChronology3() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withChronology(COPTIC_PARIS);
        assertSame(base,test);
    }

    //-----------------------------------------------------------------------
    public void testWithStartMillis_long1() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withStartMillis(TEST_TIME1 - 1);
        assertEquals(new Interval(TEST_TIME1 - 1,TEST_TIME2,COPTIC_PARIS),test);
    }

    public void testWithStartMillis_long2() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        try {
            test.withStartMillis(TEST_TIME2 + 1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithStartMillis_long3() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withStartMillis(TEST_TIME1);
        assertSame(base,test);
    }

    //-----------------------------------------------------------------------
    public void testWithStartInstant_RI1() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withStart(new Instant(TEST_TIME1 - 1));
        assertEquals(new Interval(TEST_TIME1 - 1,TEST_TIME2,COPTIC_PARIS),test);
    }

    public void testWithStartInstant_RI2() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        try {
            test.withStart(new Instant(TEST_TIME2 + 1));
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithStartInstant_RI3() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withStart(null);
        assertEquals(new Interval(TEST_TIME_NOW,TEST_TIME2,COPTIC_PARIS),test);
    }

    //-----------------------------------------------------------------------
    public void testWithEndMillis_long1() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withEndMillis(TEST_TIME2 - 1);
        assertEquals(new Interval(TEST_TIME1,TEST_TIME2 - 1,COPTIC_PARIS),test);
    }

    public void testWithEndMillis_long2() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        try {
            test.withEndMillis(TEST_TIME1 - 1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithEndMillis_long3() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withEndMillis(TEST_TIME2);
        assertSame(base,test);
    }

    //-----------------------------------------------------------------------
    public void testWithEndInstant_RI1() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withEnd(new Instant(TEST_TIME2 - 1));
        assertEquals(new Interval(TEST_TIME1,TEST_TIME2 - 1,COPTIC_PARIS),test);
    }

    public void testWithEndInstant_RI2() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        try {
            test.withEnd(new Instant(TEST_TIME1 - 1));
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithEndInstant_RI3() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withEnd(null);
        assertEquals(new Interval(TEST_TIME1,TEST_TIME_NOW,COPTIC_PARIS),test);
    }

    //-----------------------------------------------------------------------
    public void testWithDurationAfterStart1() throws Throwable {
        Duration dur = new Duration(TEST_TIME2 - TEST_TIME_NOW);
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME_NOW, COPTIC_PARIS);
        Interval test = base.withDurationAfterStart(dur);
        
        assertEquals(new Interval(TEST_TIME_NOW,TEST_TIME2,COPTIC_PARIS),test);
    }

    public void testWithDurationAfterStart2() throws Throwable {
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withDurationAfterStart(null);
        
        assertEquals(new Interval(TEST_TIME_NOW,TEST_TIME_NOW,COPTIC_PARIS),test);
    }

    public void testWithDurationAfterStart3() throws Throwable {
        Duration dur = new Duration(-1);
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME_NOW);
        try {
            base.withDurationAfterStart(dur);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithDurationAfterStart4() throws Throwable {
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withDurationAfterStart(base.toDuration());
        
        assertSame(base,test);
    }

    //-----------------------------------------------------------------------
    public void testWithDurationBeforeEnd1() throws Throwable {
        Duration dur = new Duration(TEST_TIME_NOW - TEST_TIME1);
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME_NOW, COPTIC_PARIS);
        Interval test = base.withDurationBeforeEnd(dur);
        
        assertEquals(new Interval(TEST_TIME1,TEST_TIME_NOW,COPTIC_PARIS),test);
    }

    public void testWithDurationBeforeEnd2() throws Throwable {
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withDurationBeforeEnd(null);
        
        assertEquals(new Interval(TEST_TIME2,TEST_TIME2,COPTIC_PARIS),test);
    }

    public void testWithDurationBeforeEnd3() throws Throwable {
        Duration dur = new Duration(-1);
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME_NOW);
        try {
            base.withDurationBeforeEnd(dur);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithDurationBeforeEnd4() throws Throwable {
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withDurationBeforeEnd(base.toDuration());
        
        assertSame(base,test);
    }

    //-----------------------------------------------------------------------
    public void testWithPeriodAfterStart1() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW, COPTIC_PARIS);
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        
        Interval base = new Interval(dt, dt);
        Interval test = base.withPeriodAfterStart(dur);
        assertEquals(new Interval(dt,dur),test);
    }

    public void testWithPeriodAfterStart2() throws Throwable {
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withPeriodAfterStart(null);
        
        assertEquals(new Interval(TEST_TIME_NOW,TEST_TIME_NOW,COPTIC_PARIS),test);
    }

    public void testWithPeriodAfterStart3() throws Throwable {
        Period per = new Period(0, 0, 0, 0, 0, 0, 0, -1);
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME_NOW);
        try {
            base.withPeriodAfterStart(per);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testWithPeriodBeforeEnd1() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW, COPTIC_PARIS);
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        
        Interval base = new Interval(dt, dt);
        Interval test = base.withPeriodBeforeEnd(dur);
        assertEquals(new Interval(dur,dt),test);
    }

    public void testWithPeriodBeforeEnd2() throws Throwable {
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withPeriodBeforeEnd(null);
        
        assertEquals(new Interval(TEST_TIME2,TEST_TIME2,COPTIC_PARIS),test);
    }

    public void testWithPeriodBeforeEnd3() throws Throwable {
        Period per = new Period(0, 0, 0, 0, 0, 0, 0, -1);
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME_NOW);
        try {
            base.withPeriodBeforeEnd(per);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testTest_1_oe() {
        Object a = new Instant(TEST_TIME_NOW).toString();
        assertEquals("2013-07-01T00:59:59.999999999+0100:+0100", a);
    }

    public void testTest_2_oe() {
        Object a = new Instant(TEST_TIME1).toString();
        assertEquals("1970-01-01T01:00:00Z", a);
    }

    public void testTest_3_oe() {
        Object a = new Instant(TEST_TIME2).toString();
        assertEquals("2013-06-06T06:06:06.000Z", a);
    }

    public void testGetMillis_1_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(1372766400000L, test.getStartMillis());
    }

    public void testGetMillis_2_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals("2013-06-04_23:59:59.999+0100", test.getStart().toString());
    }

    public void testGetMillis_3_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(1372766400000L, test.getEndMillis());
    }

    public void testGetMillis_4_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(2013, test.getEnd().getYear());
    }

    public void testGetMillis_5_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(3600000L, test.toDurationMillis());
    }

    public void testGetMillis_6_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        assertSame(test, test.toDuration());
    }

    public void testGetDuration1_1_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(3600000L, test.toDurationMillis());
    }

    public void testGetDuration1_2_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2);
        assertSame(test, test.toDuration());
    }

    public void testGetDuration2_1_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME1);
        assertSame(test, test.toDuration());
    }

    public void testEqualsHashCode_1_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_2_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_5_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_6_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_7_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_8_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_9_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_10_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_13_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_14_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        assertNotNull(test4);
    }

    public void testEqualsHashCode_15_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        assertNotNull(test4);
    }

    public void testEqualsHashCode_16_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        assertNotNull(test4);
    }

    public void testEqualsHashCode_17_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        assertNotNull(test4);
    }

    public void testEqualsHashCode_18_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        assertNotNull(test4);
    }

    public void testEqualsHashCode_19_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        assertNotNull(test4);
    }

    public void testEqualsHashCode_20_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        assertNotNull(test4);
    }

    public void testEqualsHashCode_21_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertNotNull(test4);
    }

    public void testEqualsHashCode_22_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertNotNull(test4);
    }

    public void testEqualsHashCode_23_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertNotNull(test4);
    }

    public void testEqualsHashCode_24_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertNotNull(test4);
    }

    public void testEqualsHashCode_25_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertNotNull(test4);
    }

    public void testEqualsHashCode_26_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertNotNull(test4);
    }

    public void testEqualsHashCode_27_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertNotNull(test4);
    }

    public void testEqualsHashCode_28_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertNotNull(test4);
    }

    public void testEqualsHashCode_29_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertNotNull(test4);
    }

    public void testEqualsHashCode_30_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertNotNull(test4);
    }

    public void testEqualsHashCode_31_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertNotNull(test4);
    }

    public void testEqualsHashCode_32_oe() {
        Interval test1 = new Interval(TEST_TIME1, TEST_TIME2);
        Interval test2 = new Interval(TEST_TIME1, TEST_TIME2);
        
        Interval test3 = new Interval(TEST_TIME_NOW, TEST_TIME2);
        
        Interval test4 = new Interval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertNotNull(test4);
    }

    public void testEqualsHashCodeLenient_1_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeLenient_2_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeLenient_3_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeLenient_4_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeLenient_5_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeLenient_6_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeLenient_7_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeStrict_1_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeStrict_2_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeStrict_3_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeStrict_4_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeStrict_5_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeStrict_6_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void testEqualsHashCodeStrict_7_oe() {
        Interval test1 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        Interval test2 = new Interval(
                new DateTime(TEST_TIME1, LenientChronology.getInstance(COPTIC_PARIS)),
                new DateTime(TEST_TIME2, LenientChronology.getInstance(COPTIC_PARIS)));
        assertNotNull(test2);
    }

    public void test_useCase_ContainsOverlapAbutGap_12_oe() {
        Interval test1020 = new Interval(10, 20);
        
        Interval interval = new Interval(4, 8);
        
        interval = new Interval(6, 10);
        assertEquals(true, test1020.contains(10));
    }

    public void test_useCase_ContainsOverlapAbutGap_16_oe() {
        Interval test1020 = new Interval(10, 20);
        
        Interval interval = new Interval(4, 8);
        
        interval = new Interval(6, 10);
        assertEquals(true, test1020.contains(10));
    }

    public void test_useCase_ContainsOverlapAbutGap_20_oe() {
        Interval test1020 = new Interval(10, 20);
        
        Interval interval = new Interval(4, 8);
        
        interval = new Interval(6, 10);
        
        interval = new Interval(8, 12);
        assertEquals(true, test1020.contains(10));
    }

    public void test_useCase_ContainsOverlapAbutGap_52_oe() {
        Interval test1020 = new Interval(10, 20);
        
        Interval interval = new Interval(4, 8);
        
        interval = new Interval(6, 10);
        
        interval = new Interval(8, 12);
        
        interval = new Interval(10, 14);
        
        
        interval = new Interval(16, 20);
        
        interval = new Interval(18, 22);
        assertEquals(false, interval.contains(1372766400000L));
    }

    public void test_useCase_ContainsOverlapAbutGap_zeroDuration_17_oe() {
        Interval test1020 = new Interval(10, 20);
        
        Interval interval = new Interval(8, 8);
        
        interval = new Interval(10, 10);
        assertEquals(true, test1020.contains(10));
    }

    public void test_useCase_ContainsOverlapAbutGap_zeroDuration_20_oe() {
        Interval test1020 = new Interval(10, 20);
        
        Interval interval = new Interval(8, 8);
        
        interval = new Interval(10, 10);
        assertEquals(true, test1020.contains(10));
    }

    public void test_useCase_ContainsOverlapAbutGap_zeroDuration_28_oe() {
        Interval test1020 = new Interval(10, 20);
        
        Interval interval = new Interval(8, 8);
        
        interval = new Interval(10, 10);
        assertEquals(true, test1020.contains(10));
    }

    public void testContains_long_1_oe() {
// incorrect assertion         assertEquals(false, ct.contains(1372709999999L));
    }

    public void testContains_long_5_oe() {
// incorrect assertion         assertEquals(false, c11370.contains(1372766400000L));
    }

    public void testContains_long_6_oe() {
// incorrect assertion         assertEquals(false, c16682.contains(1372709999999L));
    }

    public void testContains_long_zeroDuration_2_oe() {
// incorrect assertion         assertEquals(false, c12502.contains(1372709999999L));
    }

    public void testContains_long_zeroDuration_3_oe() {
// incorrect assertion         assertEquals(false, c13275.contains(137270L));
    }

    public void testContainsNow_6_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
// incorrect assertion         assertEquals(true, DateTimeUtils.containsNow());
    }

    public void testContainsNow_7_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
// incorrect assertion         assertEquals(true, DateTimeUtils.containsNow());
    }

    public void testContainsNow_9_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
// incorrect assertion         assertEquals(true, DateTimeUtils.containsNow());
    }

    public void testContains_RI_1_oe() {
// incorrect assertion         assertEquals(false, ct.contains(1372766400000L));
    }

    public void testContains_RI_3_oe() {
        boolean a = true;
        assertEquals(true, a);
    }

    public void testContains_RI_5_oe() {
// incorrect assertion         assertEquals(false, c14810.contains(1372719999999L));
    }

    public void testContains_RI_6_oe() {
// incorrect assertion         assertEquals(true, c1.contains(1372706400000L));
    }

    public void testContains_RI_null_4_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
// incorrect assertion         assertEquals(false, DateTimeUtils.contains());
    }

    public void testContains_RI_null_5_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
// incorrect assertion         assertEquals(false, DateTimeUtils.contains());
    }

    public void testContains_RI_null_6_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
// incorrect assertion         assertEquals(false, DateTimeUtils.contains());
    }

    public void testContains_RI_zeroDuration_1_oe() {
// incorrect assertion         assertEquals(false, c13048.contains(1372719999999L));
    }

    public void testContains_RI_zeroDuration_2_oe() {
// incorrect assertion         assertEquals(true, c11152.contains(1372728000000L));
    }

    public void testContains_RI_zeroDuration_3_oe() {
// incorrect assertion         assertEquals(false, c13107.contains(1372719999999L));
    }

    public void testContains_RInterval_1_oe() {
// incorrect assertion         assertEquals(false, c11859.contains(1372719999999L));
    }

    public void testContains_RInterval_2_oe() {
        
// incorrect assertion         assertEquals(false, ct.contains(1372766400000L));
    }

    public void testContains_RInterval_4_oe() {
        
        
// incorrect assertion         assertEquals(false, c15358.contains(1372719999999L));
    }

    public void testContains_RInterval_6_oe() {
        
        
        boolean a = true;
        assertEquals(true, a);
    }

    public void testContains_RInterval_8_oe() {
        
        
        
// incorrect assertion         assertEquals(false, c1582.contains(1372766400000L));
    }

    public void testContains_RInterval_9_oe() {
        
        
        
        boolean a = true;
        assertEquals(true, a);
    }

    public void testContains_RInterval_11_oe() {
        
        
        
        boolean a = true;
        assertEquals(true, a);
    }

    public void testContains_RInterval_12_oe() {
        
        
        
// incorrect assertion         assertEquals(false, c12190.contains(1372719999999L));
    }

    public void testContains_RInterval_13_oe() {
        
        
        
        
// incorrect assertion         assertEquals(false, ct.contains(1372709999999L));
    }

    public void testContains_RInterval_14_oe() {
        
        
        
        
// incorrect assertion         assertEquals(false, c15185.contains(1372719999999L));
    }

    public void testContains_RInterval_16_oe() {
        
        
        
        
// incorrect assertion         assertEquals(true, c14965.contains(1372728000000L));
    }

    public void testContains_RInterval_17_oe() {
        
        
        
        
// incorrect assertion         assertEquals(false, ct.contains(1372766400000L));
    }

    public void testContains_RInterval_18_oe() {
        
        
        
        
// incorrect assertion         assertEquals(false, ct.contains(1372766400000L));
    }

    public void testContains_RInterval_19_oe() {
        
        
        
        
// incorrect assertion         assertEquals(false, ct.contains(1372709999999L));
    }

    public void testContains_RInterval_20_oe() {
        
        
        
        
        
// incorrect assertion         assertEquals(false, ct.contains(1372709999999L));
    }

    public void testContains_RInterval_21_oe() {
        
        
        
        
        
// incorrect assertion         assertEquals(false, ct.contains(1372766400000L));
    }

    public void testContains_RInterval_null_4_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
// incorrect assertion         assertEquals(false, DateTimeUtils.contains());
    }

    public void testContains_RInterval_null_5_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
// incorrect assertion         assertEquals(false, DateTimeUtils.contains());
    }

    public void testContains_RInterval_null_6_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
// incorrect assertion         assertEquals(false, DateTimeUtils.contains());
    }

    public void testContains_RInterval_zeroDuration_1_oe() {
// incorrect assertion         assertEquals(true, c14772.contains(1372728000000L));
    }

    public void testContains_RInterval_zeroDuration_2_oe() {
// incorrect assertion         assertEquals(false, ct.contains(1372766400000L));
    }

    public void testContains_RInterval_zeroDuration_3_oe() {
// incorrect assertion         assertEquals(false, c15254.contains(1372706400000L));
    }

    public void testContains_RInterval_zeroDuration_4_oe() {
// incorrect assertion         assertEquals(false, ct.contains(1372766400000L));
    }

    public void testContains_RInterval_zeroDuration_5_oe() {
// incorrect assertion         assertEquals(false, ct.contains(1372766400000L));
    }

    public void testOverlap_RInterval_zone_1_oe() {
        Interval testA = new Interval(new DateTime(3, LONDON), new DateTime(7, LONDON));
        assertEquals("Interval[2013-07-01T01:00:00.000+01:00/2013-07-01T01:00:00.000+01:00]", testA.toString());
    }

    public void testOverlap_RInterval_zone_2_oe() {
        Interval testA = new Interval(new DateTime(3, LONDON), new DateTime(7, LONDON));
        
        Interval testB = new Interval(new DateTime(4, MOSCOW), new DateTime(8, MOSCOW));
        assertNotNull(testB);
    }

    public void testOverlap_RInterval_zone_3_oe() {
        Interval testA = new Interval(new DateTime(3, LONDON), new DateTime(7, LONDON));
        
        Interval testB = new Interval(new DateTime(4, MOSCOW), new DateTime(8, MOSCOW));
        
        Interval resultAB = testA.overlap(testB);
        assertNotNull(resultAB);
    }

    public void testOverlap_RInterval_zone_4_oe() {
        Interval testA = new Interval(new DateTime(3, LONDON), new DateTime(7, LONDON));
        
        Interval testB = new Interval(new DateTime(4, MOSCOW), new DateTime(8, MOSCOW));
        
        Interval resultAB = testA.overlap(testB);
        
        Interval resultBA = testB.overlap(testA);
        assertNotNull(resultBA);
    }

    public void testOverlap_RInterval_zoneUTC_1_oe() {
        Interval testA = new Interval(new Instant(3), new Instant(7));
        assertEquals("Interval[2013-07-01T00:00:00.000000000Z/2013-07-01T00:00:00.000000000Z]", testA.toString());
    }

    public void testOverlap_RInterval_zoneUTC_2_oe() {
        Interval testA = new Interval(new Instant(3), new Instant(7));
        
        Interval testB = new Interval(new Instant(4), new Instant(8));
        assertEquals("Interval[2013-07-01T00:00:00.000000000Z/2013-07-01T00:00:00.000000000Z]", testA.toString());
    }

    public void testOverlap_RInterval_zoneUTC_3_oe() {
        Interval testA = new Interval(new Instant(3), new Instant(7));
        
        Interval testB = new Interval(new Instant(4), new Instant(8));
        
        Interval result = testA.overlap(testB);
// incorrect assertion         assertEquals("Interval[2013-07-01T00:00:00.000000000Z/2013-07-01T00:00:00.000000000Z]/Interval[2013-07-01T00:00:00.000000000;
    }

    public void testGap_RInterval_zone_1_oe() {
        Interval testA = new Interval(new DateTime(3, LONDON), new DateTime(7, LONDON));
// incorrect assertion         assertNotNull(Interval.getInstance());
    }

    public void testGap_RInterval_zone_2_oe() {
        Interval testA = new Interval(new DateTime(3, LONDON), new DateTime(7, LONDON));
        
        Interval testB = new Interval(new DateTime(1, MOSCOW), new DateTime(2, MOSCOW));
        assertNotNull(testB);
    }

    public void testGap_RInterval_zone_3_oe() {
        Interval testA = new Interval(new DateTime(3, LONDON), new DateTime(7, LONDON));
        
        Interval testB = new Interval(new DateTime(1, MOSCOW), new DateTime(2, MOSCOW));
        
        Interval resultAB = testA.gap(testB);
        assertEquals("Interval[2013-07-01T00:00:00.000+0100,2013-07-01T00:00:00.000+0100]/2013-07-01T00:00:00.000+0100", resultAB.toString());
    }

    public void testGap_RInterval_zone_4_oe() {
        Interval testA = new Interval(new DateTime(3, LONDON), new DateTime(7, LONDON));
        
        Interval testB = new Interval(new DateTime(1, MOSCOW), new DateTime(2, MOSCOW));
        
        Interval resultAB = testA.gap(testB);
        
        Interval resultBA = testB.gap(testA);
        assertNotNull(resultBA);
    }

    public void testGap_RInterval_zoneUTC_1_oe() {
        Interval testA = new Interval(new Instant(3), new Instant(7));
// incorrect assertion         assertNotNull(Interval.getInstanceUTC());
    }

    public void testGap_RInterval_zoneUTC_2_oe() {
        Interval testA = new Interval(new Instant(3), new Instant(7));
        
        Interval testB = new Interval(new Instant(1), new Instant(2));
// incorrect assertion         assertNotNull(Chronology.getInstanceUTC());
    }

    public void testGap_RInterval_zoneUTC_3_oe() {
        Interval testA = new Interval(new Instant(3), new Instant(7));
        
        Interval testB = new Interval(new Instant(1), new Instant(2));
        
        Interval result = testA.gap(testB);
// incorrect assertion         assertEquals("Interval[2013-07-01T00:00:00.000000000Z/2013-07-01T00:00:00.000000000Z]/2013-07-01T00:00:00.000000000Z",;
    }

    public void testIsBeforeNow_4_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
// incorrect assertion         assertEquals(false, DateTimeUtils.isBeforeNow());
    }

    public void testIsBeforeNow_5_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
// incorrect assertion         assertEquals(false, DateTimeUtils.isBeforeNow());
    }

    public void testIsBeforeNow_6_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
        DateTimeUtils.setCurrentMillisFixed(8);
// incorrect assertion         assertEquals(false, DateTimeUtils.isBeforeNow());
    }

    public void testIsBefore_RI_null_3_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
// incorrect assertion         assertEquals(false, DateTimeUtils.isBefore());
    }

    public void testIsBefore_RInterval_null_2_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
// incorrect assertion         assertEquals(false, DateTimeUtils.isBefore());
    }

    public void testIsAfterNow_3_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfterNow());
    }

    public void testIsAfterNow_4_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfterNow());
    }

    public void testIsAfterNow_5_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfterNow());
    }

    public void testIsAfter_RI_6_oe() {
        boolean a = false;
        assertEquals(false, a);
    }

    public void testIsAfter_RI_null_2_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfter());
    }

    public void testIsAfter_RI_null_3_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfter());
    }

    public void testIsAfter_RI_null_4_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfter());
    }

    public void testIsAfter_RI_null_5_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfter());
    }

    public void testIsAfter_RI_null_6_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
        DateTimeUtils.setCurrentMillisFixed(8);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfter());
    }

    public void testIsAfter_RInterval_null_3_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfter());
    }

    public void testIsAfter_RInterval_null_4_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfter());
    }

    public void testIsAfter_RInterval_null_6_oe() {
        DateTimeUtils.setCurrentMillisFixed(2);
        DateTimeUtils.setCurrentMillisFixed(3);
        DateTimeUtils.setCurrentMillisFixed(4);
        DateTimeUtils.setCurrentMillisFixed(6);
        DateTimeUtils.setCurrentMillisFixed(7);
        DateTimeUtils.setCurrentMillisFixed(8);
// incorrect assertion         assertEquals(false, DateTimeUtils.isAfter());
    }

    public void testToInterval1_1_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval result = test.toInterval();
        assertNotNull(result);
    }

    public void testToMutableInterval1_1_oe() {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        MutableInterval result = test.toMutableInterval();
        assertNotNull(result);
    }

    public void testToPeriod_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, COPTIC_PARIS);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, COPTIC_PARIS);
        Interval base = new Interval(dt1, dt2);
        
        Period test = base.toPeriod();
        Period expected = new Period(dt1, dt2, PeriodType.standard());
        assertNotNull(test);
    }

    public void testToPeriod_PeriodType1_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, COPTIC_PARIS);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, COPTIC_PARIS);
        Interval base = new Interval(dt1, dt2);
        
        Period test = base.toPeriod(null);
        Period expected = new Period(dt1, dt2, PeriodType.standard());
        assertNotNull(test);
    }

    public void testToPeriod_PeriodType2_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18);
        Interval base = new Interval(dt1, dt2);
        
        Period test = base.toPeriod(PeriodType.yearWeekDayTime());
        Period expected = new Period(dt1, dt2, PeriodType.yearWeekDayTime());
        assertNotNull(test);
    }

    public void testSerialization_1_oe() throws Exception {
        Interval test = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Interval result = (Interval) ois.readObject();
        ois.close();
        
        assertNotNull(result);
    }

    public void testToString_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, DateTimeZone.UTC);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, DateTimeZone.UTC);
        Interval test = new Interval(dt1, dt2);
        assertEquals("2004-06-09T07:08:09.010000000Z/2005-08-13T12:14:16.018000000Z", test.toString());
    }

    public void testToString_reparse_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, DateTimeZone.getDefault());
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, DateTimeZone.getDefault());
        Interval test = new Interval(dt1, dt2);
        assertNotNull(test);
    }

    public void testWithChronology1_1_oe() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withChronology(BuddhistChronology.getInstance());
        assertNotNull(test);
    }

    public void testWithChronology2_1_oe() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withChronology(null);
// incorrect assertion         assertEquals("Interval[2013-06-03T00:00:00.000000000,2013-06-04T00:00:00.000000000[Europe/Paris])/2013-06-04T00:00:00.00000000;
    }

    public void testWithChronology3_1_oe() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withChronology(COPTIC_PARIS);
        assertNotSame(base, test);
    }

    public void testWithStartMillis_long1_1_oe() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withStartMillis(TEST_TIME1 - 1);
        assertNotNull(test);
    }

    public void testWithStartMillis_long3_1_oe() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withStartMillis(TEST_TIME1);
        assertNotNull(test);
    }

    public void testWithStartInstant_RI1_1_oe() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withStart(new Instant(TEST_TIME1 - 1));
        assertEquals(1372706400000L, test.getEndMillis());
    }

    public void testWithEndMillis_long1_1_oe() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withEndMillis(TEST_TIME2 - 1);
        assertNotNull(test);
    }

    public void testWithEndMillis_long3_1_oe() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withEndMillis(TEST_TIME2);
        assertNotNull(test);
    }

    public void testWithEndInstant_RI1_1_oe() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withEnd(new Instant(TEST_TIME2 - 1));
        assertEquals("2013-06-06_2013-06-06T23:59:59.999Z/2013-06-06T23:59:59.998Z", test.toString());
    }

    public void testWithEndInstant_RI3_1_oe() {
        Interval base = new Interval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withEnd(null);
        assertNotNull(test);
    }

    public void testWithDurationAfterStart1_1_oe() throws Throwable {
        Duration dur = new Duration(TEST_TIME2 - TEST_TIME_NOW);
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME_NOW, COPTIC_PARIS);
        Interval test = base.withDurationAfterStart(dur);
        
        assertNotNull(test);
    }

    public void testWithDurationAfterStart4_1_oe() throws Throwable {
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withDurationAfterStart(base.toDuration());
        
// incorrect assertion         assertEquals(false, test.isNegative());
    }

    public void testWithDurationBeforeEnd1_1_oe() throws Throwable {
        Duration dur = new Duration(TEST_TIME_NOW - TEST_TIME1);
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME_NOW, COPTIC_PARIS);
        Interval test = base.withDurationBeforeEnd(dur);
        
        assertNotNull(test);
    }

    public void testWithDurationBeforeEnd4_1_oe() throws Throwable {
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withDurationBeforeEnd(base.toDuration());
        
// incorrect assertion         assertEquals(0, test.getMinutes());
    }

    public void testWithPeriodAfterStart1_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW, COPTIC_PARIS);
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        
        Interval base = new Interval(dt, dt);
        Interval test = base.withPeriodAfterStart(dur);
        assertNotNull(test);
    }

    public void testWithPeriodAfterStart2_1_oe() throws Throwable {
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withPeriodAfterStart(null);
        
        assertNotNull(test);
    }

    public void testWithPeriodBeforeEnd1_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW, COPTIC_PARIS);
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        
        Interval base = new Interval(dt, dt);
        Interval test = base.withPeriodBeforeEnd(dur);
        assertNotNull(test);
    }

    public void testWithPeriodBeforeEnd2_1_oe() throws Throwable {
        Interval base = new Interval(TEST_TIME_NOW, TEST_TIME2, COPTIC_PARIS);
        Interval test = base.withPeriodBeforeEnd(null);
        
        assertNotNull(test);
    }

}
