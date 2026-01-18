/*
 *  Copyright 2001-2015 Stephen Colebourne
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
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.IntervalConverter;

/**
 * This class is a JUnit test for Interval.
 *
 * @author Stephen Colebourne
 */
public class TestInterval_Constructors_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone OFFSET_04_00 = DateTimeZone.forOffsetHours(4);
    
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
            
//    // 2002-04-05
//    private long TEST_TIME1 =
//            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
//            + 12L * DateTimeConstants.MILLIS_PER_HOUR
//            + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
//        
//    // 2003-05-06
//    private long TEST_TIME2 =
//            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
//            + 14L * DateTimeConstants.MILLIS_PER_HOUR
//            + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
    
    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestInterval_Constructors_OE25Dev.class);
    }

    public TestInterval_Constructors_OE25Dev(String name) {
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

    public void testParseWithOffset_invalid() throws Throwable {
        try {
            Interval.parseWithOffset("2010-06-30T12:30");
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            Interval.parseWithOffset("P1D/P1D");
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    public void testConstructor_long_long3() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        DateTime dt2 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        try {
            new Interval(dt1.getMillis(), dt2.getMillis());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testConstructor_RI_RI7() throws Throwable {
        DateTime dt1 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        DateTime dt2 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        try {
            new Interval(dt1, dt2);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testConstructor_RI_RP8() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Period dur = new Period(0, 0, 0, 0, 0, 0, 0, -1);
        try {
            new Interval(dt, dur);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testConstructor_RP_RI8() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Period dur = new Period(0, 0, 0, 0, 0, 0, 0, -1);
        try {
            new Interval(dur, dt);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testConstructor_RI_RD5() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Duration dur = new Duration(-1);
        try {
            new Interval(dt, dur);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testConstructor_RD_RI5() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Duration dur = new Duration(-1);
        try {
            new Interval(dur, dt);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testConstructor_Object5() throws Throwable {
        IntervalConverter oldConv = ConverterManager.getInstance().getIntervalConverter("");
        IntervalConverter conv = new IntervalConverter() {
            public boolean isReadableInterval(Object object, Chronology chrono) {
                return false;
            }
            public void setInto(ReadWritableInterval interval, Object object, Chronology chrono) {
                interval.setChronology(chrono);
                interval.setInterval(1234L, 5678L);
            }
            public Class<?> getSupportedType() {
                return String.class;
            }
        };
        try {
            ConverterManager.getInstance().addIntervalConverter(conv);
            DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
            DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
            Interval test = new Interval(dt1.toString() + '/' + dt2.toString());
            assertEquals(1234L, test.getStartMillis());
            assertEquals(5678L, test.getEndMillis());
        } finally {
            ConverterManager.getInstance().addIntervalConverter(oldConv);
        }
    }

    public void testConstructor_Object6() throws Throwable {
        IntervalConverter oldConv = ConverterManager.getInstance().getIntervalConverter(new Interval(0L, 0L));
        IntervalConverter conv = new IntervalConverter() {
            public boolean isReadableInterval(Object object, Chronology chrono) {
                return false;
            }
            public void setInto(ReadWritableInterval interval, Object object, Chronology chrono) {
                interval.setChronology(chrono);
                interval.setInterval(1234L, 5678L);
            }
            public Class<?> getSupportedType() {
                return ReadableInterval.class;
            }
        };
        try {
            ConverterManager.getInstance().addIntervalConverter(conv);
            Interval base = new Interval(-1000L, 1000L);
            Interval test = new Interval(base);
            assertEquals(1234L, test.getStartMillis());
            assertEquals(5678L, test.getEndMillis());
        } finally {
            ConverterManager.getInstance().addIntervalConverter(oldConv);
        }
    }

    class MockInterval implements ReadableInterval {
        public Chronology getChronology() {
            return ISOChronology.getInstance();
        }
        public long getStartMillis() {
            return 1234L;
        }
        public DateTime getStart() {
            return new DateTime(1234L);
        }
        public long getEndMillis() {
            return 5678L;
        }
        public DateTime getEnd() {
            return new DateTime(5678L);
        }
        public long toDurationMillis() {
            return (5678L - 1234L);
        }
        public Duration toDuration() {
            return new Duration(5678L - 1234L);
        }
        public boolean contains(long millisInstant) {
            return false;
        }
        public boolean containsNow() {
            return false;
        }
        public boolean contains(ReadableInstant instant) {
            return false;
        }
        public boolean contains(ReadableInterval interval) {
            return false;
        }
        public boolean overlaps(ReadableInterval interval) {
            return false;
        }
        public boolean isBefore(ReadableInstant instant) {
            return false;
        }
        public boolean isBefore(ReadableInterval interval) {
            return false;
        }
        public boolean isAfter(ReadableInstant instant) {
            return false;
        }
        public boolean isAfter(ReadableInterval interval) {
            return false;
        }
        public Interval toInterval() {
            return null;
        }
        public MutableInterval toMutableInterval() {
            return null;
        }
        public Period toPeriod() {
            return null;
        }
        public Period toPeriod(PeriodType type) {
            return null;
        }
    }

    //-----------------------------------------------------------------------

    public void testParse_noOffsetInString_1_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 12, 30, ISOChronology.getInstance(PARIS));
        DateTime end = new DateTime(2010, 7, 1, 14, 30, ISOChronology.getInstance(PARIS));
        assertEquals(new Interval(start, end), Interval.parse("2010-06-30T12:30/2010-07-01T14:30"));
    }

    public void testParse_noOffsetInString_2_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 12, 30, ISOChronology.getInstance(PARIS));
        DateTime end = new DateTime(2010, 7, 1, 14, 30, ISOChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(new Interval(start, end), Interval.parse("2010-06-30T12:30/P1DT2H"));
    }

    public void testParse_noOffsetInString_3_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 12, 30, ISOChronology.getInstance(PARIS));
        DateTime end = new DateTime(2010, 7, 1, 14, 30, ISOChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(new Interval(start, end), Interval.parse("P1DT2H/2010-07-01T14:30"));
    }

    public void testParse_offsetInString_1_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 10, 30, ISOChronology.getInstance(PARIS));
        DateTime end = new DateTime(2010, 7, 1, 12, 30, ISOChronology.getInstance(PARIS));
        assertEquals(new Interval(start, end), Interval.parse("2010-06-30T12:30+04:00/2010-07-01T14:30+04:00"));
    }

    public void testParse_offsetInString_2_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 10, 30, ISOChronology.getInstance(PARIS));
        DateTime end = new DateTime(2010, 7, 1, 12, 30, ISOChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(new Interval(start, end), Interval.parse("2010-06-30T12:30+04:00/P1DT2H"));
    }

    public void testParse_offsetInString_3_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 10, 30, ISOChronology.getInstance(PARIS));
        DateTime end = new DateTime(2010, 7, 1, 12, 30, ISOChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(new Interval(start, end), Interval.parse("P1DT2H/2010-07-01T14:30+04:00"));
    }

    public void testParseWithOffset_noOffsetInString_1_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 12, 30, ISOChronology.getInstance(PARIS));
        DateTime end = new DateTime(2010, 7, 1, 14, 30, ISOChronology.getInstance(PARIS));
        assertEquals(new Interval(start, end), Interval.parseWithOffset("2010-06-30T12:30/2010-07-01T14:30"));
    }

    public void testParseWithOffset_noOffsetInString_2_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 12, 30, ISOChronology.getInstance(PARIS));
        DateTime end = new DateTime(2010, 7, 1, 14, 30, ISOChronology.getInstance(PARIS));
        // removed other assertion
        assertEquals(new Interval(start, end), Interval.parseWithOffset("2010-06-30T12:30/P1DT2H"));
    }

    public void testParseWithOffset_noOffsetInString_3_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 12, 30, ISOChronology.getInstance(PARIS));
        DateTime end = new DateTime(2010, 7, 1, 14, 30, ISOChronology.getInstance(PARIS));
        // removed other assertion
        // removed other assertion
        assertEquals(new Interval(start, end), Interval.parseWithOffset("P1DT2H/2010-07-01T14:30"));
    }

    public void testParseWithOffset_offsetInString_1_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 12, 30, ISOChronology.getInstance(OFFSET_04_00));
        DateTime end = new DateTime(2010, 7, 1, 14, 30, ISOChronology.getInstance(OFFSET_04_00));
        assertEquals(new Interval(start, end), Interval.parseWithOffset("2010-06-30T12:30+04:00/2010-07-01T14:30+04:00"));
    }

    public void testParseWithOffset_offsetInString_2_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 12, 30, ISOChronology.getInstance(OFFSET_04_00));
        DateTime end = new DateTime(2010, 7, 1, 14, 30, ISOChronology.getInstance(OFFSET_04_00));
        // removed other assertion
        assertEquals(new Interval(start, end), Interval.parseWithOffset("2010-06-30T12:30+04:00/p1DT2H"));
    }

    public void testParseWithOffset_offsetInString_3_oe() throws Throwable {
        DateTime start = new DateTime(2010, 6, 30, 12, 30, ISOChronology.getInstance(OFFSET_04_00));
        DateTime end = new DateTime(2010, 7, 1, 14, 30, ISOChronology.getInstance(OFFSET_04_00));
        // removed other assertion
        // removed other assertion
        assertEquals(new Interval(start, end), Interval.parseWithOffset("p1DT2H/2010-07-01T14:30+04:00"));
    }

    public void testConstructor_long_long1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis());
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_long_long1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_long_long1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis());
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_long_long2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Interval test = new Interval(dt1.getMillis(), dt1.getMillis());
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_long_long2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Interval test = new Interval(dt1.getMillis(), dt1.getMillis());
        // removed other assertion
        assertEquals(dt1.getMillis(), test.getEndMillis());
    }

    public void testConstructor_long_long2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Interval test = new Interval(dt1.getMillis(), dt1.getMillis());
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_long_long_minMax_1_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MAX_VALUE);
        assertEquals(Long.MIN_VALUE, test.getStartMillis());
    }

    public void testConstructor_long_long_minMax_2_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MAX_VALUE);
        // removed other assertion
        assertEquals(Long.MAX_VALUE, test.getEndMillis());
    }

    public void testConstructor_long_long_minMax_3_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(Long.MIN_VALUE), test.getStart());
    }

    public void testConstructor_long_long_minMax_4_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(Long.MAX_VALUE), test.getEnd());
    }

    public void testConstructor_long_long_minMax_5_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_long_long_minMax_6_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, test.toInterval());
    }

    public void testConstructor_long_long_minMax_7_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("-292275055-05-16T16:56:25.192+00:09:21/292278994-08-17T07:12:55.807Z", test.toString());
    }

    public void testConstructor_long_long_min_1_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MIN_VALUE + 9);
        assertEquals(Long.MIN_VALUE, test.getStartMillis());
    }

    public void testConstructor_long_long_min_2_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MIN_VALUE + 9);
        // removed other assertion
        assertEquals(Long.MIN_VALUE + 9, test.getEndMillis());
    }

    public void testConstructor_long_long_min_3_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MIN_VALUE + 9);
        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(Long.MIN_VALUE), test.getStart());
    }

    public void testConstructor_long_long_min_4_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MIN_VALUE + 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(Long.MIN_VALUE + 9), test.getEnd());
    }

    public void testConstructor_long_long_min_5_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MIN_VALUE + 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_long_long_min_6_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MIN_VALUE + 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, test.toInterval());
    }

    public void testConstructor_long_long_min_7_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MIN_VALUE + 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("-292275055-05-16T16:56:25.192+00:09:21/-292275055-05-16T16:56:25.201+00:09:21", test.toString());
    }

    public void testConstructor_long_long_min_8_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MIN_VALUE + 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.toDurationMillis());
    }

    public void testConstructor_long_long_min_9_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MIN_VALUE + 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new Duration(9), test.toDuration());
    }

    public void testConstructor_long_long_min_10_oe() throws Throwable {
        Interval test = new Interval(Long.MIN_VALUE, Long.MIN_VALUE + 9);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new Period(9), test.toPeriod());
    }

    public void testConstructor_long_long_max_1_oe() throws Throwable {
        Interval test = new Interval(Long.MAX_VALUE - 9, Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE - 9, test.getStartMillis());
    }

    public void testConstructor_long_long_max_2_oe() throws Throwable {
        Interval test = new Interval(Long.MAX_VALUE - 9, Long.MAX_VALUE);
        // removed other assertion
        assertEquals(Long.MAX_VALUE, test.getEndMillis());
    }

    public void testConstructor_long_long_max_3_oe() throws Throwable {
        Interval test = new Interval(Long.MAX_VALUE - 9, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(Long.MAX_VALUE - 9), test.getStart());
    }

    public void testConstructor_long_long_max_4_oe() throws Throwable {
        Interval test = new Interval(Long.MAX_VALUE - 9, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new DateTime(Long.MAX_VALUE), test.getEnd());
    }

    public void testConstructor_long_long_max_5_oe() throws Throwable {
        Interval test = new Interval(Long.MAX_VALUE - 9, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_long_long_max_6_oe() throws Throwable {
        Interval test = new Interval(Long.MAX_VALUE - 9, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(test, test.toInterval());
    }

    public void testConstructor_long_long_max_7_oe() throws Throwable {
        Interval test = new Interval(Long.MAX_VALUE - 9, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.798Z/292278994-08-17T07:12:55.807Z", test.toString());
    }

    public void testConstructor_long_long_max_8_oe() throws Throwable {
        Interval test = new Interval(Long.MAX_VALUE - 9, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, test.toDurationMillis());
    }

    public void testConstructor_long_long_max_9_oe() throws Throwable {
        Interval test = new Interval(Long.MAX_VALUE - 9, Long.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new Duration(9), test.toDuration());
    }

    public void testConstructor_long_long_Zone_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), LONDON);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_long_long_Zone_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), LONDON);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_long_long_Zone_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), LONDON);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(LONDON), test.getChronology());
    }

    public void testConstructor_long_long_nullZone_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), (DateTimeZone) null);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_long_long_nullZone_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), (DateTimeZone) null);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_long_long_nullZone_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), (DateTimeZone) null);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_long_long_Chronology_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), GJChronology.getInstance());
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_long_long_Chronology_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), GJChronology.getInstance());
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_long_long_Chronology_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(GJChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_long_long_nullChronology_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_long_long_nullChronology_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_long_long_nullChronology_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.getMillis(), dt2.getMillis(), (Chronology) null);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_RI_RI1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1, dt2);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RI1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1, dt2);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RI_RI2_1_oe() throws Throwable {
        Instant dt1 = new Instant(new DateTime(2004, 6, 9, 0, 0, 0, 0));
        Instant dt2 = new Instant(new DateTime(2005, 7, 10, 1, 1, 1, 1));
        Interval test = new Interval(dt1, dt2);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RI2_2_oe() throws Throwable {
        Instant dt1 = new Instant(new DateTime(2004, 6, 9, 0, 0, 0, 0));
        Instant dt2 = new Instant(new DateTime(2005, 7, 10, 1, 1, 1, 1));
        Interval test = new Interval(dt1, dt2);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RI_RI3_1_oe() throws Throwable {
        Interval test = new Interval((ReadableInstant) null, (ReadableInstant) null);
        assertEquals(TEST_TIME_NOW, test.getStartMillis());
    }

    public void testConstructor_RI_RI3_2_oe() throws Throwable {
        Interval test = new Interval((ReadableInstant) null, (ReadableInstant) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW, test.getEndMillis());
    }

    public void testConstructor_RI_RI4_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2000, 6, 9, 0, 0, 0, 0);
        Interval test = new Interval(dt1, (ReadableInstant) null);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RI4_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2000, 6, 9, 0, 0, 0, 0);
        Interval test = new Interval(dt1, (ReadableInstant) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW, test.getEndMillis());
    }

    public void testConstructor_RI_RI5_1_oe() throws Throwable {
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval((ReadableInstant) null, dt2);
        assertEquals(TEST_TIME_NOW, test.getStartMillis());
    }

    public void testConstructor_RI_RI5_2_oe() throws Throwable {
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval((ReadableInstant) null, dt2);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RI_RI6_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Interval test = new Interval(dt1, dt1);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RI6_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        Interval test = new Interval(dt1, dt1);
        // removed other assertion
        assertEquals(dt1.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RI_RI_chronoStart_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, GJChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1, dt2);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RI_chronoStart_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, GJChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1, dt2);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RI_RI_chronoStart_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, GJChronology.getInstance());
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(GJChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_RI_RI_chronoEnd_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, GJChronology.getInstance());
        Interval test = new Interval(dt1, dt2);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RI_chronoEnd_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, GJChronology.getInstance());
        Interval test = new Interval(dt1, dt2);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RI_RI_chronoEnd_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, GJChronology.getInstance());
        Interval test = new Interval(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_RI_RI_zones_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, LONDON);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, PARIS);
        Interval test = new Interval(dt1, dt2);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RI_zones_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, LONDON);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, PARIS);
        Interval test = new Interval(dt1, dt2);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RI_RI_zones_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0, LONDON);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1, PARIS);
        Interval test = new Interval(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(LONDON), test.getChronology());
    }

    public void testConstructor_RI_RI_instant_1_oe() throws Throwable {
        Instant dt1 = new Instant(12345678L);
        Instant dt2 = new Instant(22345678L);
        Interval test = new Interval(dt1, dt2);
        assertEquals(12345678L, test.getStartMillis());
    }

    public void testConstructor_RI_RI_instant_2_oe() throws Throwable {
        Instant dt1 = new Instant(12345678L);
        Instant dt2 = new Instant(22345678L);
        Interval test = new Interval(dt1, dt2);
        // removed other assertion
        assertEquals(22345678L, test.getEndMillis());
    }

    public void testConstructor_RI_RI_instant_3_oe() throws Throwable {
        Instant dt1 = new Instant(12345678L);
        Instant dt2 = new Instant(22345678L);
        Interval test = new Interval(dt1, dt2);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstanceUTC(), test.getChronology());
    }

    public void testConstructor_RI_RP1_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().months().add(result, 6);
        result = ISOChronology.getInstance().hours().add(result, 1);
        
        Interval test = new Interval(dt, dur);
        assertEquals(dt.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RP1_2_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().months().add(result, 6);
        result = ISOChronology.getInstance().hours().add(result, 1);
        
        Interval test = new Interval(dt, dur);
        // removed other assertion
        assertEquals(result, test.getEndMillis());
    }

    public void testConstructor_RI_RP2_1_oe() throws Throwable {
        Instant dt = new Instant(new DateTime(TEST_TIME_NOW));
        Period dur = new Period(0, 6, 0, 3, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstanceUTC().months().add(result, 6);
        result = ISOChronology.getInstanceUTC().days().add(result, 3);
        result = ISOChronology.getInstanceUTC().hours().add(result, 1);
        
        Interval test = new Interval(dt, dur);
        assertEquals(dt.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RP2_2_oe() throws Throwable {
        Instant dt = new Instant(new DateTime(TEST_TIME_NOW));
        Period dur = new Period(0, 6, 0, 3, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstanceUTC().months().add(result, 6);
        result = ISOChronology.getInstanceUTC().days().add(result, 3);
        result = ISOChronology.getInstanceUTC().hours().add(result, 1);
        
        Interval test = new Interval(dt, dur);
        // removed other assertion
        assertEquals(result, test.getEndMillis());
    }

    public void testConstructor_RI_RP3_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW, CopticChronology.getInstanceUTC());
        Period dur = new Period(0, 6, 0, 3, 1, 0, 0, 0, PeriodType.standard());
        long result = TEST_TIME_NOW;
        result = CopticChronology.getInstanceUTC().months().add(result, 6);
        result = CopticChronology.getInstanceUTC().days().add(result, 3);
        result = CopticChronology.getInstanceUTC().hours().add(result, 1);
        
        Interval test = new Interval(dt, dur);
        assertEquals(dt.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RP3_2_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW, CopticChronology.getInstanceUTC());
        Period dur = new Period(0, 6, 0, 3, 1, 0, 0, 0, PeriodType.standard());
        long result = TEST_TIME_NOW;
        result = CopticChronology.getInstanceUTC().months().add(result, 6);
        result = CopticChronology.getInstanceUTC().days().add(result, 3);
        result = CopticChronology.getInstanceUTC().hours().add(result, 1);
        
        Interval test = new Interval(dt, dur);
        // removed other assertion
        assertEquals(result, test.getEndMillis());
    }

    public void testConstructor_RI_RP4_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Period dur = new Period(1 * DateTimeConstants.MILLIS_PER_HOUR + 23L);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().hours().add(result, 1);
        result = ISOChronology.getInstance().millis().add(result, 23);
        
        Interval test = new Interval(dt, dur);
        assertEquals(dt.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RP4_2_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Period dur = new Period(1 * DateTimeConstants.MILLIS_PER_HOUR + 23L);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().hours().add(result, 1);
        result = ISOChronology.getInstance().millis().add(result, 23);
        
        Interval test = new Interval(dt, dur);
        // removed other assertion
        assertEquals(result, test.getEndMillis());
    }

    public void testConstructor_RI_RP5_1_oe() throws Throwable {
        Interval test = new Interval((ReadableInstant) null, (ReadablePeriod) null);
        assertEquals(TEST_TIME_NOW, test.getStartMillis());
    }

    public void testConstructor_RI_RP5_2_oe() throws Throwable {
        Interval test = new Interval((ReadableInstant) null, (ReadablePeriod) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW, test.getEndMillis());
    }

    public void testConstructor_RI_RP6_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Interval test = new Interval(dt, (ReadablePeriod) null);
        assertEquals(dt.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RP6_2_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Interval test = new Interval(dt, (ReadablePeriod) null);
        // removed other assertion
        assertEquals(dt.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RI_RP7_1_oe() throws Throwable {
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().monthOfYear().add(result, 6);
        result = ISOChronology.getInstance().hourOfDay().add(result, 1);
        
        Interval test = new Interval((ReadableInstant) null, dur);
        assertEquals(TEST_TIME_NOW, test.getStartMillis());
    }

    public void testConstructor_RI_RP7_2_oe() throws Throwable {
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().monthOfYear().add(result, 6);
        result = ISOChronology.getInstance().hourOfDay().add(result, 1);
        
        Interval test = new Interval((ReadableInstant) null, dur);
        // removed other assertion
        assertEquals(result, test.getEndMillis());
    }

    public void testConstructor_RP_RI1_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().months().add(result, -6);
        result = ISOChronology.getInstance().hours().add(result, -1);
        
        Interval test = new Interval(dur, dt);
        assertEquals(result, test.getStartMillis());
    }

    public void testConstructor_RP_RI1_2_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().months().add(result, -6);
        result = ISOChronology.getInstance().hours().add(result, -1);
        
        Interval test = new Interval(dur, dt);
        // removed other assertion
        assertEquals(dt.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RP_RI2_1_oe() throws Throwable {
        Instant dt = new Instant(new DateTime(TEST_TIME_NOW));
        Period dur = new Period(0, 6, 0, 3, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstanceUTC().months().add(result, -6);
        result = ISOChronology.getInstanceUTC().days().add(result, -3);
        result = ISOChronology.getInstanceUTC().hours().add(result, -1);
        
        Interval test = new Interval(dur, dt);
        assertEquals(result, test.getStartMillis());
    }

    public void testConstructor_RP_RI2_2_oe() throws Throwable {
        Instant dt = new Instant(new DateTime(TEST_TIME_NOW));
        Period dur = new Period(0, 6, 0, 3, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstanceUTC().months().add(result, -6);
        result = ISOChronology.getInstanceUTC().days().add(result, -3);
        result = ISOChronology.getInstanceUTC().hours().add(result, -1);
        
        Interval test = new Interval(dur, dt);
        // removed other assertion
        assertEquals(dt.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RP_RI3_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW, CopticChronology.getInstanceUTC());
        Period dur = new Period(0, 6, 0, 3, 1, 0, 0, 0, PeriodType.standard());
        long result = TEST_TIME_NOW;
        result = CopticChronology.getInstanceUTC().months().add(result, -6);
        result = CopticChronology.getInstanceUTC().days().add(result, -3);
        result = CopticChronology.getInstanceUTC().hours().add(result, -1);
        
        Interval test = new Interval(dur, dt);
        assertEquals(result, test.getStartMillis());
    }

    public void testConstructor_RP_RI3_2_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW, CopticChronology.getInstanceUTC());
        Period dur = new Period(0, 6, 0, 3, 1, 0, 0, 0, PeriodType.standard());
        long result = TEST_TIME_NOW;
        result = CopticChronology.getInstanceUTC().months().add(result, -6);
        result = CopticChronology.getInstanceUTC().days().add(result, -3);
        result = CopticChronology.getInstanceUTC().hours().add(result, -1);
        
        Interval test = new Interval(dur, dt);
        // removed other assertion
        assertEquals(dt.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RP_RI4_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Period dur = new Period(1 * DateTimeConstants.MILLIS_PER_HOUR + 23L);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().hours().add(result, -1);
        result = ISOChronology.getInstance().millis().add(result, -23);
        
        Interval test = new Interval(dur, dt);
        assertEquals(result, test.getStartMillis());
    }

    public void testConstructor_RP_RI4_2_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Period dur = new Period(1 * DateTimeConstants.MILLIS_PER_HOUR + 23L);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().hours().add(result, -1);
        result = ISOChronology.getInstance().millis().add(result, -23);
        
        Interval test = new Interval(dur, dt);
        // removed other assertion
        assertEquals(dt.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RP_RI5_1_oe() throws Throwable {
        Interval test = new Interval((ReadablePeriod) null, (ReadableInstant) null);
        assertEquals(TEST_TIME_NOW, test.getStartMillis());
    }

    public void testConstructor_RP_RI5_2_oe() throws Throwable {
        Interval test = new Interval((ReadablePeriod) null, (ReadableInstant) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW, test.getEndMillis());
    }

    public void testConstructor_RP_RI6_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Interval test = new Interval((ReadablePeriod) null, dt);
        assertEquals(dt.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RP_RI6_2_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Interval test = new Interval((ReadablePeriod) null, dt);
        // removed other assertion
        assertEquals(dt.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RP_RI7_1_oe() throws Throwable {
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().monthOfYear().add(result, -6);
        result = ISOChronology.getInstance().hourOfDay().add(result, -1);
        
        Interval test = new Interval(dur, (ReadableInstant) null);
        assertEquals(result, test.getStartMillis());
    }

    public void testConstructor_RP_RI7_2_oe() throws Throwable {
        Period dur = new Period(0, 6, 0, 0, 1, 0, 0, 0);
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().monthOfYear().add(result, -6);
        result = ISOChronology.getInstance().hourOfDay().add(result, -1);
        
        Interval test = new Interval(dur, (ReadableInstant) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW, test.getEndMillis());
    }

    public void testConstructor_RI_RD1_1_oe() throws Throwable {
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().months().add(result, 6);
        result = ISOChronology.getInstance().hours().add(result, 1);
        
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Duration dur = new Duration(result - TEST_TIME_NOW);
        
        Interval test = new Interval(dt, dur);
        assertEquals(dt.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RD1_2_oe() throws Throwable {
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().months().add(result, 6);
        result = ISOChronology.getInstance().hours().add(result, 1);
        
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Duration dur = new Duration(result - TEST_TIME_NOW);
        
        Interval test = new Interval(dt, dur);
        // removed other assertion
        assertEquals(result, test.getEndMillis());
    }

    public void testConstructor_RI_RD2_1_oe() throws Throwable {
        Interval test = new Interval((ReadableInstant) null, (ReadableDuration) null);
        assertEquals(TEST_TIME_NOW, test.getStartMillis());
    }

    public void testConstructor_RI_RD2_2_oe() throws Throwable {
        Interval test = new Interval((ReadableInstant) null, (ReadableDuration) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW, test.getEndMillis());
    }

    public void testConstructor_RI_RD3_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Interval test = new Interval(dt, (ReadableDuration) null);
        assertEquals(dt.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RI_RD3_2_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Interval test = new Interval(dt, (ReadableDuration) null);
        // removed other assertion
        assertEquals(dt.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RI_RD4_1_oe() throws Throwable {
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().monthOfYear().add(result, 6);
        result = ISOChronology.getInstance().hourOfDay().add(result, 1);
        
        Duration dur = new Duration(result - TEST_TIME_NOW);
        
        Interval test = new Interval((ReadableInstant) null, dur);
        assertEquals(TEST_TIME_NOW, test.getStartMillis());
    }

    public void testConstructor_RI_RD4_2_oe() throws Throwable {
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().monthOfYear().add(result, 6);
        result = ISOChronology.getInstance().hourOfDay().add(result, 1);
        
        Duration dur = new Duration(result - TEST_TIME_NOW);
        
        Interval test = new Interval((ReadableInstant) null, dur);
        // removed other assertion
        assertEquals(result, test.getEndMillis());
    }

    public void testConstructor_RD_RI1_1_oe() throws Throwable {
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().months().add(result, -6);
        result = ISOChronology.getInstance().hours().add(result, -1);
        
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Duration dur = new Duration(TEST_TIME_NOW - result);
        
        Interval test = new Interval(dur, dt);
        assertEquals(result, test.getStartMillis());
    }

    public void testConstructor_RD_RI1_2_oe() throws Throwable {
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().months().add(result, -6);
        result = ISOChronology.getInstance().hours().add(result, -1);
        
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Duration dur = new Duration(TEST_TIME_NOW - result);
        
        Interval test = new Interval(dur, dt);
        // removed other assertion
        assertEquals(dt.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RD_RI2_1_oe() throws Throwable {
        Interval test = new Interval((ReadableDuration) null, (ReadableInstant) null);
        assertEquals(TEST_TIME_NOW, test.getStartMillis());
    }

    public void testConstructor_RD_RI2_2_oe() throws Throwable {
        Interval test = new Interval((ReadableDuration) null, (ReadableInstant) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW, test.getEndMillis());
    }

    public void testConstructor_RD_RI3_1_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Interval test = new Interval((ReadableDuration) null, dt);
        assertEquals(dt.getMillis(), test.getStartMillis());
    }

    public void testConstructor_RD_RI3_2_oe() throws Throwable {
        DateTime dt = new DateTime(TEST_TIME_NOW);
        Interval test = new Interval((ReadableDuration) null, dt);
        // removed other assertion
        assertEquals(dt.getMillis(), test.getEndMillis());
    }

    public void testConstructor_RD_RI4_1_oe() throws Throwable {
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().monthOfYear().add(result, -6);
        result = ISOChronology.getInstance().hourOfDay().add(result, -1);
        
        Duration dur = new Duration(TEST_TIME_NOW - result);
        
        Interval test = new Interval(dur, (ReadableInstant) null);
        assertEquals(result, test.getStartMillis());
    }

    public void testConstructor_RD_RI4_2_oe() throws Throwable {
        long result = TEST_TIME_NOW;
        result = ISOChronology.getInstance().monthOfYear().add(result, -6);
        result = ISOChronology.getInstance().hourOfDay().add(result, -1);
        
        Duration dur = new Duration(TEST_TIME_NOW - result);
        
        Interval test = new Interval(dur, (ReadableInstant) null);
        // removed other assertion
        assertEquals(TEST_TIME_NOW, test.getEndMillis());
    }

    public void testConstructor_Object1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.toString() + '/' + dt2.toString());
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_Object1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval test = new Interval(dt1.toString() + '/' + dt2.toString());
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_Object2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval base = new Interval(dt1, dt2);
        
        Interval test = new Interval(base);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_Object2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval base = new Interval(dt1, dt2);
        
        Interval test = new Interval(base);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_Object3_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        MutableInterval base = new MutableInterval(dt1, dt2);
        
        Interval test = new Interval(base);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_Object3_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        MutableInterval base = new MutableInterval(dt1, dt2);
        
        Interval test = new Interval(base);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_Object4_1_oe() throws Throwable {
        MockInterval base = new MockInterval();
        Interval test = new Interval(base);
        assertEquals(base.getStartMillis(), test.getStartMillis());
    }

    public void testConstructor_Object4_2_oe() throws Throwable {
        MockInterval base = new MockInterval();
        Interval test = new Interval(base);
        // removed other assertion
        assertEquals(base.getEndMillis(), test.getEndMillis());
    }

    public void testConstructor_Object_Chronology1_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval base = new Interval(dt1, dt2);
        
        Interval test = new Interval(base, BuddhistChronology.getInstance());
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_Object_Chronology1_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval base = new Interval(dt1, dt2);
        
        Interval test = new Interval(base, BuddhistChronology.getInstance());
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_Object_Chronology1_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval base = new Interval(dt1, dt2);
        
        Interval test = new Interval(base, BuddhistChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(BuddhistChronology.getInstance(), test.getChronology());
    }

    public void testConstructor_Object_Chronology2_1_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval base = new Interval(dt1, dt2);
        
        Interval test = new Interval(base, null);
        assertEquals(dt1.getMillis(), test.getStartMillis());
    }

    public void testConstructor_Object_Chronology2_2_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval base = new Interval(dt1, dt2);
        
        Interval test = new Interval(base, null);
        // removed other assertion
        assertEquals(dt2.getMillis(), test.getEndMillis());
    }

    public void testConstructor_Object_Chronology2_3_oe() throws Throwable {
        DateTime dt1 = new DateTime(2004, 6, 9, 0, 0, 0, 0);
        DateTime dt2 = new DateTime(2005, 7, 10, 1, 1, 1, 1);
        Interval base = new Interval(dt1, dt2);
        
        Interval test = new Interval(base, null);
        // removed other assertion
        // removed other assertion
        assertEquals(ISOChronology.getInstance(), test.getChronology());
    }

}
