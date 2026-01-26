/*
 *  Copyright 2001-2009 Stephen Colebourne
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
import java.math.RoundingMode;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.base.AbstractDuration;
import org.joda.time.base.BaseDuration;
import org.joda.time.chrono.ISOChronology;

/**
 * This class is a Junit unit test for Duration.
 *
 * @author Stephen Colebourne
 */
public class TestDuration_Basics_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

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
        return new TestSuite(TestDuration_Basics_OE25Dev_OE25Dev.class);
    }

    public TestDuration_Basics_OE25Dev(String name) {
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

    //-----------------------------------------------------------------------
    
    class MockDuration extends AbstractDuration {
        private final long iValue;
        public MockDuration(long value) {
            super();
            iValue = value;
        }
        public long getMillis() {
            return iValue;
        }
    }
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testToStandardDays_overflow() {
        Duration test = new Duration((((long) Integer.MAX_VALUE) + 1) * 24L * 60L * 60000L);
        try {
            test.toStandardDays();
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    public void testToStandardHours_overflow() {
        Duration test = new Duration(((long) Integer.MAX_VALUE) * 3600000L + 3600000L);
        try {
            test.toStandardHours();
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    public void testToStandardMinutes_overflow() {
        Duration test = new Duration(((long) Integer.MAX_VALUE) * 60000L + 60000L);
        try {
            test.toStandardMinutes();
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    public void testToStandardSeconds_overflow() {
        Duration test = new Duration(((long) Integer.MAX_VALUE) * 1000L + 1000L);
        try {
            test.toStandardSeconds();
            fail();
        } catch (ArithmeticException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void testToPeriod() {
        DateTimeZone zone = DateTimeZone.getDefault();
        try {
            DateTimeZone.setDefault(DateTimeZone.forID("Europe/Paris"));
            long length =
                (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
                5L * DateTimeConstants.MILLIS_PER_HOUR +
                6L * DateTimeConstants.MILLIS_PER_MINUTE +
                7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
            Duration dur = new Duration(length);
            Period test = dur.toPeriod();
            assertEquals(0, test.getYears());  // (4 + (3 * 7) + (2 * 30) + 365) == 450
            assertEquals(0, test.getMonths());
            assertEquals(0, test.getWeeks());
            assertEquals(0, test.getDays());
            assertEquals((450 * 24) + 5, test.getHours());
            assertEquals(6, test.getMinutes());
            assertEquals(7, test.getSeconds());
            assertEquals(8, test.getMillis());
        } finally {
            DateTimeZone.setDefault(zone);
        }
    }

    public void testToPeriod_fixedZone() throws Throwable {
        DateTimeZone zone = DateTimeZone.getDefault();
        try {
            DateTimeZone.setDefault(DateTimeZone.forOffsetHours(2));
            long length =
                (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
                5L * DateTimeConstants.MILLIS_PER_HOUR +
                6L * DateTimeConstants.MILLIS_PER_MINUTE +
                7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
            Duration dur = new Duration(length);
            Period test = dur.toPeriod();
            assertEquals(0, test.getYears());  // (4 + (3 * 7) + (2 * 30) + 365) == 450
            assertEquals(0, test.getMonths());
            assertEquals(0, test.getWeeks());
            assertEquals(0, test.getDays());
            assertEquals((450 * 24) + 5, test.getHours());
            assertEquals(6, test.getMinutes());
            assertEquals(7, test.getSeconds());
            assertEquals(8, test.getMillis());
        } finally {
            DateTimeZone.setDefault(zone);
        }
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

    public void testNegated_long3() {
        Duration test = new Duration(Long.MIN_VALUE);
        try {
            test.negated();
            fail();
        } catch(ArithmeticException e) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    static class MockMutableDuration extends BaseDuration {
        public MockMutableDuration(long duration) {
            super(duration);
        }
        @Override
        public void setMillis(long duration) {
            super.setMillis(duration);
        }
    }

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

    public void testGetMillis_1_oe() {
        Duration test = new Duration(0L);
        assertEquals(0, test.getMillis());
    }

    public void testGetMillis_2_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        
        test = new Duration(1234567890L);
        assertEquals(1234567890L, test.getMillis());
    }

    public void testEqualsHashCode_1_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test3 = new Duration(321L);
        assertEquals(false, test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test3 = new Duration(321L);
        // removed other assertion
        assertEquals(false, test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test3 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test3 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test3 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test3 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test2.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test3 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test1.equals("Hello"));
    }

    public void testEqualsHashCode_15_oe() {
        Duration test1 = new Duration(123L);
        Duration test2 = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test3 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test1.equals(new MockDuration(123L)));
    }

    public void testCompareTo_1_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        assertEquals(0, test1.compareTo(test1a));
    }

    public void testCompareTo_2_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        assertEquals(0, test1a.compareTo(test1));
    }

    public void testCompareTo_3_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        assertEquals(0, test1.compareTo(test1));
    }

    public void testCompareTo_4_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, test1a.compareTo(test1a));
    }

    public void testCompareTo_5_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        assertEquals(-1, test1.compareTo(test2));
    }

    public void testCompareTo_6_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        assertEquals(+1, test2.compareTo(test1));
    }

    public void testCompareTo_7_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        assertEquals(+1, test2.compareTo(new MockDuration(123L)));
    }

    public void testCompareTo_8_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(0, test1.compareTo(new MockDuration(123L)));
    }

    public void testIsEqual_1_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        assertEquals(true, test1.isEqual(test1a));
    }

    public void testIsEqual_2_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        assertEquals(true, test1a.isEqual(test1));
    }

    public void testIsEqual_3_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.isEqual(test1));
    }

    public void testIsEqual_4_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1a.isEqual(test1a));
    }

    public void testIsEqual_5_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        assertEquals(false, test1.isEqual(test2));
    }

    public void testIsEqual_6_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        assertEquals(false, test2.isEqual(test1));
    }

    public void testIsEqual_7_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test2.isEqual(new MockDuration(123L)));
    }

    public void testIsEqual_8_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test1.isEqual(new MockDuration(123L)));
    }

    public void testIsEqual_9_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isEqual(null));
    }

    public void testIsEqual_10_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, new Duration(0L).isEqual(null));
    }

    public void testIsBefore_1_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        assertEquals(false, test1.isShorterThan(test1a));
    }

    public void testIsBefore_2_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        assertEquals(false, test1a.isShorterThan(test1));
    }

    public void testIsBefore_3_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isShorterThan(test1));
    }

    public void testIsBefore_4_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1a.isShorterThan(test1a));
    }

    public void testIsBefore_5_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        assertEquals(true, test1.isShorterThan(test2));
    }

    public void testIsBefore_6_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        assertEquals(false, test2.isShorterThan(test1));
    }

    public void testIsBefore_7_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test2.isShorterThan(new MockDuration(123L)));
    }

    public void testIsBefore_8_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test1.isShorterThan(new MockDuration(123L)));
    }

    public void testIsBefore_9_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isShorterThan(null));
    }

    public void testIsBefore_10_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, new Duration(0L).isShorterThan(null));
    }

    public void testIsAfter_1_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        assertEquals(false, test1.isLongerThan(test1a));
    }

    public void testIsAfter_2_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        assertEquals(false, test1a.isLongerThan(test1));
    }

    public void testIsAfter_3_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.isLongerThan(test1));
    }

    public void testIsAfter_4_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1a.isLongerThan(test1a));
    }

    public void testIsAfter_5_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        assertEquals(false, test1.isLongerThan(test2));
    }

    public void testIsAfter_6_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        assertEquals(true, test2.isLongerThan(test1));
    }

    public void testIsAfter_7_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        assertEquals(true, test2.isLongerThan(new MockDuration(123L)));
    }

    public void testIsAfter_8_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test1.isLongerThan(new MockDuration(123L)));
    }

    public void testIsAfter_9_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.isLongerThan(null));
    }

    public void testIsAfter_10_oe() {
        Duration test1 = new Duration(123L);
        Duration test1a = new Duration(123L);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        Duration test2 = new Duration(321L);
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, new Duration(0L).isLongerThan(null));
    }

    public void testSerialization_1_oe() throws Exception {
        Duration test = new Duration(123L);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Duration result = (Duration) ois.readObject();
        ois.close();
        
        assertEquals(test, result);
    }

    public void testGetStandardSeconds_1_oe() {
        Duration test = new Duration(0L);
        assertEquals(0, test.getStandardSeconds());
    }

    public void testGetStandardSeconds_2_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        assertEquals(0, test.getStandardSeconds());
    }

    public void testGetStandardSeconds_3_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        assertEquals(0, test.getStandardSeconds());
    }

    public void testGetStandardSeconds_4_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        assertEquals(1, test.getStandardSeconds());
    }

    public void testGetStandardSeconds_5_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        assertEquals(1, test.getStandardSeconds());
    }

    public void testGetStandardSeconds_6_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        // removed other assertion
        test = new Duration(1999L);
        assertEquals(1, test.getStandardSeconds());
    }

    public void testGetStandardSeconds_7_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        // removed other assertion
        test = new Duration(1999L);
        // removed other assertion
        test = new Duration(2000L);
        assertEquals(2, test.getStandardSeconds());
    }

    public void testGetStandardSeconds_8_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        // removed other assertion
        test = new Duration(1999L);
        // removed other assertion
        test = new Duration(2000L);
        // removed other assertion
        test = new Duration(-1L);
        assertEquals(0, test.getStandardSeconds());
    }

    public void testGetStandardSeconds_9_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        // removed other assertion
        test = new Duration(1999L);
        // removed other assertion
        test = new Duration(2000L);
        // removed other assertion
        test = new Duration(-1L);
        // removed other assertion
        test = new Duration(-999L);
        assertEquals(0, test.getStandardSeconds());
    }

    public void testGetStandardSeconds_10_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        // removed other assertion
        test = new Duration(1999L);
        // removed other assertion
        test = new Duration(2000L);
        // removed other assertion
        test = new Duration(-1L);
        // removed other assertion
        test = new Duration(-999L);
        // removed other assertion
        test = new Duration(-1000L);
        assertEquals(-1, test.getStandardSeconds());
    }

    public void testToString_1_oe() {
        long length = (365L + 2L * 30L + 3L * 7L + 4L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 845L;
        Duration test = new Duration(length);
        assertEquals("PT" + (length / 1000) + "." + (length % 1000) + "S", test.toString());
    }

    public void testToString_2_oe() {
        long length = (365L + 2L * 30L + 3L * 7L + 4L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 845L;
        Duration test = new Duration(length);
        // removed other assertion
        
        assertEquals("PT0S", new Duration(0L).toString());
    }

    public void testToString_3_oe() {
        long length = (365L + 2L * 30L + 3L * 7L + 4L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 845L;
        Duration test = new Duration(length);
        // removed other assertion
        
        // removed other assertion
        assertEquals("PT10S", new Duration(10000L).toString());
    }

    public void testToString_4_oe() {
        long length = (365L + 2L * 30L + 3L * 7L + 4L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 845L;
        Duration test = new Duration(length);
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals("PT1S", new Duration(1000L).toString());
    }

    public void testToString_5_oe() {
        long length = (365L + 2L * 30L + 3L * 7L + 4L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 845L;
        Duration test = new Duration(length);
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PT12.345S", new Duration(12345L).toString());
    }

    public void testToString_6_oe() {
        long length = (365L + 2L * 30L + 3L * 7L + 4L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 845L;
        Duration test = new Duration(length);
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PT-12.345S", new Duration(-12345L).toString());
    }

    public void testToString_7_oe() {
        long length = (365L + 2L * 30L + 3L * 7L + 4L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 845L;
        Duration test = new Duration(length);
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PT-1.123S", new Duration(-1123L).toString());
    }

    public void testToString_8_oe() {
        long length = (365L + 2L * 30L + 3L * 7L + 4L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 845L;
        Duration test = new Duration(length);
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PT-0.123S", new Duration(-123L).toString());
    }

    public void testToString_9_oe() {
        long length = (365L + 2L * 30L + 3L * 7L + 4L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 845L;
        Duration test = new Duration(length);
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PT-0.012S", new Duration(-12L).toString());
    }

    public void testToString_10_oe() {
        long length = (365L + 2L * 30L + 3L * 7L + 4L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 845L;
        Duration test = new Duration(length);
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PT-0.001S", new Duration(-1L).toString());
    }

    public void testToDuration1_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.toDuration();
        assertSame(test, result);
    }

    public void testToDuration2_1_oe() {
        MockDuration test = new MockDuration(123L);
        Duration result = test.toDuration();
        assertNotSame(test, result);
    }

    public void testToDuration2_2_oe() {
        MockDuration test = new MockDuration(123L);
        Duration result = test.toDuration();
        // removed other assertion
        assertEquals(test, result);
    }

    public void testToStandardDays_1_oe() {
        Duration test = new Duration(0L);
        assertEquals(Days.days(0), test.toStandardDays());
    }

    public void testToStandardDays_2_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        assertEquals(Days.days(0), test.toStandardDays());
    }

    public void testToStandardDays_3_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L - 1);
        assertEquals(Days.days(0), test.toStandardDays());
    }

    public void testToStandardDays_4_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L);
        assertEquals(Days.days(1), test.toStandardDays());
    }

    public void testToStandardDays_5_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L + 1);
        assertEquals(Days.days(1), test.toStandardDays());
    }

    public void testToStandardDays_6_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L + 1);
        // removed other assertion
        test = new Duration(2 * 24 * 60 * 60000L - 1);
        assertEquals(Days.days(1), test.toStandardDays());
    }

    public void testToStandardDays_7_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L + 1);
        // removed other assertion
        test = new Duration(2 * 24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(2 * 24 * 60 * 60000L);
        assertEquals(Days.days(2), test.toStandardDays());
    }

    public void testToStandardDays_8_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L + 1);
        // removed other assertion
        test = new Duration(2 * 24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(2 * 24 * 60 * 60000L);
        // removed other assertion
        test = new Duration(-1L);
        assertEquals(Days.days(0), test.toStandardDays());
    }

    public void testToStandardDays_9_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L + 1);
        // removed other assertion
        test = new Duration(2 * 24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(2 * 24 * 60 * 60000L);
        // removed other assertion
        test = new Duration(-1L);
        // removed other assertion
        test = new Duration(-24 * 60 * 60000L + 1);
        assertEquals(Days.days(0), test.toStandardDays());
    }

    public void testToStandardDays_10_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L);
        // removed other assertion
        test = new Duration(24 * 60 * 60000L + 1);
        // removed other assertion
        test = new Duration(2 * 24 * 60 * 60000L - 1);
        // removed other assertion
        test = new Duration(2 * 24 * 60 * 60000L);
        // removed other assertion
        test = new Duration(-1L);
        // removed other assertion
        test = new Duration(-24 * 60 * 60000L + 1);
        // removed other assertion
        test = new Duration(-24 * 60 * 60000L);
        assertEquals(Days.days(-1), test.toStandardDays());
    }

    public void testToStandardHours_1_oe() {
        Duration test = new Duration(0L);
        assertEquals(Hours.hours(0), test.toStandardHours());
    }

    public void testToStandardHours_2_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        assertEquals(Hours.hours(0), test.toStandardHours());
    }

    public void testToStandardHours_3_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(3600000L - 1);
        assertEquals(Hours.hours(0), test.toStandardHours());
    }

    public void testToStandardHours_4_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(3600000L - 1);
        // removed other assertion
        test = new Duration(3600000L);
        assertEquals(Hours.hours(1), test.toStandardHours());
    }

    public void testToStandardHours_5_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(3600000L - 1);
        // removed other assertion
        test = new Duration(3600000L);
        // removed other assertion
        test = new Duration(3600000L + 1);
        assertEquals(Hours.hours(1), test.toStandardHours());
    }

    public void testToStandardHours_6_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(3600000L - 1);
        // removed other assertion
        test = new Duration(3600000L);
        // removed other assertion
        test = new Duration(3600000L + 1);
        // removed other assertion
        test = new Duration(2 * 3600000L - 1);
        assertEquals(Hours.hours(1), test.toStandardHours());
    }

    public void testToStandardHours_7_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(3600000L - 1);
        // removed other assertion
        test = new Duration(3600000L);
        // removed other assertion
        test = new Duration(3600000L + 1);
        // removed other assertion
        test = new Duration(2 * 3600000L - 1);
        // removed other assertion
        test = new Duration(2 * 3600000L);
        assertEquals(Hours.hours(2), test.toStandardHours());
    }

    public void testToStandardHours_8_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(3600000L - 1);
        // removed other assertion
        test = new Duration(3600000L);
        // removed other assertion
        test = new Duration(3600000L + 1);
        // removed other assertion
        test = new Duration(2 * 3600000L - 1);
        // removed other assertion
        test = new Duration(2 * 3600000L);
        // removed other assertion
        test = new Duration(-1L);
        assertEquals(Hours.hours(0), test.toStandardHours());
    }

    public void testToStandardHours_9_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(3600000L - 1);
        // removed other assertion
        test = new Duration(3600000L);
        // removed other assertion
        test = new Duration(3600000L + 1);
        // removed other assertion
        test = new Duration(2 * 3600000L - 1);
        // removed other assertion
        test = new Duration(2 * 3600000L);
        // removed other assertion
        test = new Duration(-1L);
        // removed other assertion
        test = new Duration(-3600000L + 1);
        assertEquals(Hours.hours(0), test.toStandardHours());
    }

    public void testToStandardHours_10_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(3600000L - 1);
        // removed other assertion
        test = new Duration(3600000L);
        // removed other assertion
        test = new Duration(3600000L + 1);
        // removed other assertion
        test = new Duration(2 * 3600000L - 1);
        // removed other assertion
        test = new Duration(2 * 3600000L);
        // removed other assertion
        test = new Duration(-1L);
        // removed other assertion
        test = new Duration(-3600000L + 1);
        // removed other assertion
        test = new Duration(-3600000L);
        assertEquals(Hours.hours(-1), test.toStandardHours());
    }

    public void testToStandardMinutes_1_oe() {
        Duration test = new Duration(0L);
        assertEquals(Minutes.minutes(0), test.toStandardMinutes());
    }

    public void testToStandardMinutes_2_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        assertEquals(Minutes.minutes(0), test.toStandardMinutes());
    }

    public void testToStandardMinutes_3_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(60000L - 1);
        assertEquals(Minutes.minutes(0), test.toStandardMinutes());
    }

    public void testToStandardMinutes_4_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(60000L - 1);
        // removed other assertion
        test = new Duration(60000L);
        assertEquals(Minutes.minutes(1), test.toStandardMinutes());
    }

    public void testToStandardMinutes_5_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(60000L - 1);
        // removed other assertion
        test = new Duration(60000L);
        // removed other assertion
        test = new Duration(60000L + 1);
        assertEquals(Minutes.minutes(1), test.toStandardMinutes());
    }

    public void testToStandardMinutes_6_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(60000L - 1);
        // removed other assertion
        test = new Duration(60000L);
        // removed other assertion
        test = new Duration(60000L + 1);
        // removed other assertion
        test = new Duration(2 * 60000L - 1);
        assertEquals(Minutes.minutes(1), test.toStandardMinutes());
    }

    public void testToStandardMinutes_7_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(60000L - 1);
        // removed other assertion
        test = new Duration(60000L);
        // removed other assertion
        test = new Duration(60000L + 1);
        // removed other assertion
        test = new Duration(2 * 60000L - 1);
        // removed other assertion
        test = new Duration(2 * 60000L);
        assertEquals(Minutes.minutes(2), test.toStandardMinutes());
    }

    public void testToStandardMinutes_8_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(60000L - 1);
        // removed other assertion
        test = new Duration(60000L);
        // removed other assertion
        test = new Duration(60000L + 1);
        // removed other assertion
        test = new Duration(2 * 60000L - 1);
        // removed other assertion
        test = new Duration(2 * 60000L);
        // removed other assertion
        test = new Duration(-1L);
        assertEquals(Minutes.minutes(0), test.toStandardMinutes());
    }

    public void testToStandardMinutes_9_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(60000L - 1);
        // removed other assertion
        test = new Duration(60000L);
        // removed other assertion
        test = new Duration(60000L + 1);
        // removed other assertion
        test = new Duration(2 * 60000L - 1);
        // removed other assertion
        test = new Duration(2 * 60000L);
        // removed other assertion
        test = new Duration(-1L);
        // removed other assertion
        test = new Duration(-60000L + 1);
        assertEquals(Minutes.minutes(0), test.toStandardMinutes());
    }

    public void testToStandardMinutes_10_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(60000L - 1);
        // removed other assertion
        test = new Duration(60000L);
        // removed other assertion
        test = new Duration(60000L + 1);
        // removed other assertion
        test = new Duration(2 * 60000L - 1);
        // removed other assertion
        test = new Duration(2 * 60000L);
        // removed other assertion
        test = new Duration(-1L);
        // removed other assertion
        test = new Duration(-60000L + 1);
        // removed other assertion
        test = new Duration(-60000L);
        assertEquals(Minutes.minutes(-1), test.toStandardMinutes());
    }

    public void testToStandardSeconds_1_oe() {
        Duration test = new Duration(0L);
        assertEquals(Seconds.seconds(0), test.toStandardSeconds());
    }

    public void testToStandardSeconds_2_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        assertEquals(Seconds.seconds(0), test.toStandardSeconds());
    }

    public void testToStandardSeconds_3_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        assertEquals(Seconds.seconds(0), test.toStandardSeconds());
    }

    public void testToStandardSeconds_4_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        assertEquals(Seconds.seconds(1), test.toStandardSeconds());
    }

    public void testToStandardSeconds_5_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        assertEquals(Seconds.seconds(1), test.toStandardSeconds());
    }

    public void testToStandardSeconds_6_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        // removed other assertion
        test = new Duration(1999L);
        assertEquals(Seconds.seconds(1), test.toStandardSeconds());
    }

    public void testToStandardSeconds_7_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        // removed other assertion
        test = new Duration(1999L);
        // removed other assertion
        test = new Duration(2000L);
        assertEquals(Seconds.seconds(2), test.toStandardSeconds());
    }

    public void testToStandardSeconds_8_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        // removed other assertion
        test = new Duration(1999L);
        // removed other assertion
        test = new Duration(2000L);
        // removed other assertion
        test = new Duration(-1L);
        assertEquals(Seconds.seconds(0), test.toStandardSeconds());
    }

    public void testToStandardSeconds_9_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        // removed other assertion
        test = new Duration(1999L);
        // removed other assertion
        test = new Duration(2000L);
        // removed other assertion
        test = new Duration(-1L);
        // removed other assertion
        test = new Duration(-999L);
        assertEquals(Seconds.seconds(0), test.toStandardSeconds());
    }

    public void testToStandardSeconds_10_oe() {
        Duration test = new Duration(0L);
        // removed other assertion
        test = new Duration(1L);
        // removed other assertion
        test = new Duration(999L);
        // removed other assertion
        test = new Duration(1000L);
        // removed other assertion
        test = new Duration(1001L);
        // removed other assertion
        test = new Duration(1999L);
        // removed other assertion
        test = new Duration(2000L);
        // removed other assertion
        test = new Duration(-1L);
        // removed other assertion
        test = new Duration(-999L);
        // removed other assertion
        test = new Duration(-1000L);
        assertEquals(Seconds.seconds(-1), test.toStandardSeconds());
    }

    public void testToPeriod_PeriodType_1_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        Period result = test.toPeriod(PeriodType.standard().withMillisRemoved());
        assertEquals(new Period(test, PeriodType.standard().withMillisRemoved()), result);
    }

    public void testToPeriod_PeriodType_2_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        Period result = test.toPeriod(PeriodType.standard().withMillisRemoved());
        // removed other assertion
        assertEquals(new Period(test.getMillis(), PeriodType.standard().withMillisRemoved()), result);
    }

    public void testToPeriod_Chronology_1_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        Period result = test.toPeriod(ISOChronology.getInstanceUTC());
        assertEquals(new Period(test, ISOChronology.getInstanceUTC()), result);
    }

    public void testToPeriod_Chronology_2_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        Period result = test.toPeriod(ISOChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(new Period(test.getMillis(), ISOChronology.getInstanceUTC()), result);
    }

    public void testToPeriod_PeriodType_Chronology_1_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        Period result = test.toPeriod(PeriodType.standard().withMillisRemoved(), ISOChronology.getInstanceUTC());
        assertEquals(new Period(test, PeriodType.standard().withMillisRemoved(), ISOChronology.getInstanceUTC()), result);
    }

    public void testToPeriod_PeriodType_Chronology_2_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        Period result = test.toPeriod(PeriodType.standard().withMillisRemoved(), ISOChronology.getInstanceUTC());
        // removed other assertion
        assertEquals(new Period(test.getMillis(), PeriodType.standard().withMillisRemoved(), ISOChronology.getInstanceUTC()), result);
    }

    public void testToPeriodFrom_1_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Period result = test.toPeriodFrom(dt);
        assertEquals(new Period(dt, test), result);
    }

    public void testToPeriodFrom_PeriodType_1_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Period result = test.toPeriodFrom(dt, PeriodType.standard().withMillisRemoved());
        assertEquals(new Period(dt, test, PeriodType.standard().withMillisRemoved()), result);
    }

    public void testToPeriodTo_1_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Period result = test.toPeriodTo(dt);
        assertEquals(new Period(test, dt), result);
    }

    public void testToPeriodTo_PeriodType_1_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Period result = test.toPeriodTo(dt, PeriodType.standard().withMillisRemoved());
        assertEquals(new Period(test, dt, PeriodType.standard().withMillisRemoved()), result);
    }

    public void testToIntervalFrom_1_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Interval result = test.toIntervalFrom(dt);
        assertEquals(new Interval(dt, test), result);
    }

    public void testToIntervalTo_1_oe() {
        long length =
            (4L + (3L * 7L) + (2L * 30L) + 365L) * DateTimeConstants.MILLIS_PER_DAY +
            5L * DateTimeConstants.MILLIS_PER_HOUR +
            6L * DateTimeConstants.MILLIS_PER_MINUTE +
            7L * DateTimeConstants.MILLIS_PER_SECOND + 8L;
        Duration test = new Duration(length);
        DateTime dt = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Interval result = test.toIntervalTo(dt);
        assertEquals(new Interval(test, dt), result);
    }

    public void testWithMillis1_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withMillis(123L);
        assertSame(test, result);
    }

    public void testWithMillis2_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withMillis(1234567890L);
        assertEquals(1234567890L, result.getMillis());
    }

    public void testWithDurationAdded_long_int1_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(8000L, 1);
        assertEquals(8123L, result.getMillis());
    }

    public void testWithDurationAdded_long_int2_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(8000L, 2);
        assertEquals(16123L, result.getMillis());
    }

    public void testWithDurationAdded_long_int3_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(8000L, -1);
        assertEquals((123L - 8000L), result.getMillis());
    }

    public void testWithDurationAdded_long_int4_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(0L, 1);
        assertSame(test, result);
    }

    public void testWithDurationAdded_long_int5_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(8000L, 0);
        assertSame(test, result);
    }

    public void testPlus_long1_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.plus(8000L);
        assertEquals(8123L, result.getMillis());
    }

    public void testPlus_long2_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.plus(0L);
        assertSame(test, result);
    }

    public void testMinus_long1_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.minus(8000L);
        assertEquals(123L - 8000L, result.getMillis());
    }

    public void testMinus_long2_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.minus(0L);
        assertSame(test, result);
    }

    public void testMultipliedBy_long1_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.multipliedBy(2L);
        assertEquals(246L, result.getMillis());
    }

    public void testMultipliedBy_long2_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.multipliedBy(1L);
        assertSame(test, result);
    }

    public void testDividedBy_long1_1_oe() {
        Duration test = new Duration(246L);
        Duration result = test.dividedBy(2L);
        assertEquals(123L, result.getMillis());
    }

    public void testDividedBy_long2_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.dividedBy(1L);
        assertSame(test, result);
    }

    public void testDividedByRoundingMode_long1_1_oe() {
        Duration test = new Duration(246L);
        Duration result = test.dividedBy(2L, RoundingMode.UNNECESSARY);
        assertEquals(123L, result.getMillis());
    }

    public void testDividedByRoundingMode_long2_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.dividedBy(2L, RoundingMode.FLOOR);
        assertEquals(61L, result.getMillis());
    }

    public void testDividedByRoundingMode_long3_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.dividedBy(7L, RoundingMode.CEILING);
        assertEquals(18L, result.getMillis());
    }

    public void testDividedByRoundingMode_long4_1_oe() {
        Duration test = new Duration(33L);
        Duration result = test.dividedBy(1L, RoundingMode.FLOOR);
        assertSame(test, result);
    }

    public void testNegated_long1_1_oe() {
        Duration test = new Duration(246L);
        Duration result = test.negated();
        assertEquals(-246L, result.getMillis());
    }

    public void testNegated_long2_1_oe() {
        Duration test = new Duration(-246L);
        Duration result = test.negated();
        assertEquals(246L, result.getMillis());
    }

    public void testAbs_1_oe() {
        assertEquals(246L, new Duration(246L).abs().getMillis());
    }

    public void testAbs_2_oe() {
        // removed other assertion
        assertEquals(0L, new Duration(0L).abs().getMillis());
    }

    public void testAbs_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(246L, new Duration(-246L).abs().getMillis());
    }

    public void testWithDurationAdded_RD_int1_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(new Duration(8000L), 1);
        assertEquals(8123L, result.getMillis());
    }

    public void testWithDurationAdded_RD_int2_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(new Duration(8000L), 2);
        assertEquals(16123L, result.getMillis());
    }

    public void testWithDurationAdded_RD_int3_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(new Duration(8000L), -1);
        assertEquals((123L - 8000L), result.getMillis());
    }

    public void testWithDurationAdded_RD_int4_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(new Duration(0L), 1);
        assertSame(test, result);
    }

    public void testWithDurationAdded_RD_int5_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(new Duration(8000L), 0);
        assertSame(test, result);
    }

    public void testWithDurationAdded_RD_int6_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.withDurationAdded(null, 0);
        assertSame(test, result);
    }

    public void testPlus_RD1_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.plus(new Duration(8000L));
        assertEquals(8123L, result.getMillis());
    }

    public void testPlus_RD2_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.plus(new Duration(0L));
        assertSame(test, result);
    }

    public void testPlus_RD3_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.plus(null);
        assertSame(test, result);
    }

    public void testMinus_RD1_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.minus(new Duration(8000L));
        assertEquals(123L - 8000L, result.getMillis());
    }

    public void testMinus_RD2_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.minus(new Duration(0L));
        assertSame(test, result);
    }

    public void testMinus_RD3_1_oe() {
        Duration test = new Duration(123L);
        Duration result = test.minus(null);
        assertSame(test, result);
    }

    public void testMutableDuration_1_oe() {
        // no MutableDuration, so...
        MockMutableDuration test = new MockMutableDuration(123L);
        assertEquals(123L, test.getMillis());
    }

    public void testMutableDuration_2_oe() {
        // no MutableDuration, so...
        MockMutableDuration test = new MockMutableDuration(123L);
        // removed other assertion
        
        test.setMillis(2345L);
        assertEquals(2345L, test.getMillis());
    }

}
