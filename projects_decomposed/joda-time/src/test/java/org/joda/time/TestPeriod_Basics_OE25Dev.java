/*
 *  Copyright 2001-2005 Stephen Colebourne
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
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.base.BasePeriod;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * This class is a Junit unit test for Duration.
 *
 * @author Stephen Colebourne
 */
public class TestPeriod_Basics_OE25Dev extends TestCase {
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
        return new TestSuite(TestPeriod_Basics_OE25Dev.class);
    }

    public TestPeriod_Basics_OE25Dev(String name) {
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
    
    class MockPeriod extends BasePeriod {
        private static final long serialVersionUID = 1L;
        public MockPeriod(long value) {
            super(value, null, null);
        }
    }

    //-----------------------------------------------------------------------

//    //-----------------------------------------------------------------------
//    public void testAddTo1() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, 1);
//        expected = ISOChronology.getInstance().months().add(expected, 2);
//        expected = ISOChronology.getInstance().weeks().add(expected, 3);
//        expected = ISOChronology.getInstance().days().add(expected, 4);
//        expected = ISOChronology.getInstance().hours().add(expected, 5);
//        expected = ISOChronology.getInstance().minutes().add(expected, 6);
//        expected = ISOChronology.getInstance().seconds().add(expected, 7);
//        expected = ISOChronology.getInstance().millis().add(expected, 8);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
//        long added = test.addTo(TEST_TIME_NOW, 1);
//        assertEquals(expected,added);
//    }
//    
//    public void testAddTo2() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, -2);
//        expected = ISOChronology.getInstance().months().add(expected, -4);
//        expected = ISOChronology.getInstance().weeks().add(expected, -6);
//        expected = ISOChronology.getInstance().days().add(expected, -8);
//        expected = ISOChronology.getInstance().hours().add(expected, -10);
//        expected = ISOChronology.getInstance().minutes().add(expected, -12);
//        expected = ISOChronology.getInstance().seconds().add(expected, -14);
//        expected = ISOChronology.getInstance().millis().add(expected, -16);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
//        long added = test.addTo(TEST_TIME_NOW, -2);
//        assertEquals(expected,added);
//    }
//    
//    public void testAddTo3() {
//        long expected = TEST_TIME_NOW;
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
//        long added = test.addTo(TEST_TIME_NOW, 0);
//        assertEquals(expected,added);
//    }
//    
//    public void testAddTo4() {
//        long expected = TEST_TIME_NOW + 100L;
//        Period test = new Period(100L);
//        long added = test.addTo(TEST_TIME_NOW, 1);
//        assertEquals(expected,added);
//    }
//    
//    //-----------------------------------------------------------------------
//    public void testAddToWithChronology1() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, 1);
//        expected = ISOChronology.getInstance().months().add(expected, 2);
//        expected = ISOChronology.getInstance().weeks().add(expected, 3);
//        expected = ISOChronology.getInstance().days().add(expected, 4);
//        expected = ISOChronology.getInstance().hours().add(expected, 5);
//        expected = ISOChronology.getInstance().minutes().add(expected, 6);
//        expected = ISOChronology.getInstance().seconds().add(expected, 7);
//        expected = ISOChronology.getInstance().millis().add(expected, 8);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
//        long added = test.addTo(TEST_TIME_NOW, 1, ISOChronology.getInstance());
//        assertEquals(expected,added);
//    }
//    
//    public void testAddToWithChronology2() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstanceUTC().years().add(expected, -2);
//        expected = ISOChronology.getInstanceUTC().months().add(expected, -4);
//        expected = ISOChronology.getInstanceUTC().weeks().add(expected, -6);
//        expected = ISOChronology.getInstanceUTC().days().add(expected, -8);
//        expected = ISOChronology.getInstanceUTC().hours().add(expected, -10);
//        expected = ISOChronology.getInstanceUTC().minutes().add(expected, -12);
//        expected = ISOChronology.getInstanceUTC().seconds().add(expected, -14);
//        expected = ISOChronology.getInstanceUTC().millis().add(expected, -16);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        long added = test.addTo(TEST_TIME_NOW, -2, ISOChronology.getInstanceUTC());  // local specified so use it
//        assertEquals(expected,added);
//    }
//    
//    public void testAddToWithChronology3() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, -2);
//        expected = ISOChronology.getInstance().months().add(expected, -4);
//        expected = ISOChronology.getInstance().weeks().add(expected, -6);
//        expected = ISOChronology.getInstance().days().add(expected, -8);
//        expected = ISOChronology.getInstance().hours().add(expected, -10);
//        expected = ISOChronology.getInstance().minutes().add(expected, -12);
//        expected = ISOChronology.getInstance().seconds().add(expected, -14);
//        expected = ISOChronology.getInstance().millis().add(expected, -16);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        long added = test.addTo(TEST_TIME_NOW, -2, null);  // no chrono specified so use default
//        assertEquals(expected,added);
//    }
//    
//    //-----------------------------------------------------------------------
//    public void testAddToRI1() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, 1);
//        expected = ISOChronology.getInstance().months().add(expected, 2);
//        expected = ISOChronology.getInstance().weeks().add(expected, 3);
//        expected = ISOChronology.getInstance().days().add(expected, 4);
//        expected = ISOChronology.getInstance().hours().add(expected, 5);
//        expected = ISOChronology.getInstance().minutes().add(expected, 6);
//        expected = ISOChronology.getInstance().seconds().add(expected, 7);
//        expected = ISOChronology.getInstance().millis().add(expected, 8);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
//        DateTime added = test.addTo(new Instant(), 1);  // Instant has no time zone, use default
//        assertEquals(expected,added.getMillis());
//        assertEquals(ISOChronology.getInstance(),added.getChronology());
//    }
//    
//    public void testAddToRI2() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, -2);
//        expected = ISOChronology.getInstance().months().add(expected, -4);
//        expected = ISOChronology.getInstance().weeks().add(expected, -6);
//        expected = ISOChronology.getInstance().days().add(expected, -8);
//        expected = ISOChronology.getInstance().hours().add(expected, -10);
//        expected = ISOChronology.getInstance().minutes().add(expected, -12);
//        expected = ISOChronology.getInstance().seconds().add(expected, -14);
//        expected = ISOChronology.getInstance().millis().add(expected, -16);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        DateTime added = test.addTo(new Instant(), -2);  // Instant has no time zone, use default
//        assertEquals(expected,added.getMillis());
//        assertEquals(ISOChronology.getInstance(),added.getChronology());
//    }
//    
//    public void testAddToRI3() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstanceUTC().years().add(expected, -2);
//        expected = ISOChronology.getInstanceUTC().months().add(expected, -4);
//        expected = ISOChronology.getInstanceUTC().weeks().add(expected, -6);
//        expected = ISOChronology.getInstanceUTC().days().add(expected, -8);
//        expected = ISOChronology.getInstanceUTC().hours().add(expected, -10);
//        expected = ISOChronology.getInstanceUTC().minutes().add(expected, -12);
//        expected = ISOChronology.getInstanceUTC().seconds().add(expected, -14);
//        expected = ISOChronology.getInstanceUTC().millis().add(expected, -16);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        DateTime added = test.addTo(new DateTime(ISOChronology.getInstanceUTC()), -2);  // DateTime has UTC time zone
//        assertEquals(expected,added.getMillis());
//        assertEquals(ISOChronology.getInstanceUTC(),added.getChronology());
//    }
//    
//    public void testAddToRI4() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance(PARIS).years().add(expected, -2);
//        expected = ISOChronology.getInstance(PARIS).months().add(expected, -4);
//        expected = ISOChronology.getInstance(PARIS).weeks().add(expected, -6);
//        expected = ISOChronology.getInstance(PARIS).days().add(expected, -8);
//        expected = ISOChronology.getInstance(PARIS).hours().add(expected, -10);
//        expected = ISOChronology.getInstance(PARIS).minutes().add(expected, -12);
//        expected = ISOChronology.getInstance(PARIS).seconds().add(expected, -14);
//        expected = ISOChronology.getInstance(PARIS).millis().add(expected, -16);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        DateTime added = test.addTo(new DateTime(PARIS), -2);  // DateTime has PARIS time zone
//        assertEquals(expected,added.getMillis());
//        assertEquals(ISOChronology.getInstance(PARIS),added.getChronology());
//    }
//    
//    public void testAddToRI5() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, -2);
//        expected = ISOChronology.getInstance().months().add(expected, -4);
//        expected = ISOChronology.getInstance().weeks().add(expected, -6);
//        expected = ISOChronology.getInstance().days().add(expected, -8);
//        expected = ISOChronology.getInstance().hours().add(expected, -10);
//        expected = ISOChronology.getInstance().minutes().add(expected, -12);
//        expected = ISOChronology.getInstance().seconds().add(expected, -14);
//        expected = ISOChronology.getInstance().millis().add(expected, -16);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        DateTime added = test.addTo(null, -2);  // null has no time zone, use default
//        assertEquals(expected,added.getMillis());
//        assertEquals(ISOChronology.getInstance(),added.getChronology());
//    }
//    
//    //-----------------------------------------------------------------------
//    public void testAddIntoRWI1() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, 1);
//        expected = ISOChronology.getInstance().months().add(expected, 2);
//        expected = ISOChronology.getInstance().weeks().add(expected, 3);
//        expected = ISOChronology.getInstance().days().add(expected, 4);
//        expected = ISOChronology.getInstance().hours().add(expected, 5);
//        expected = ISOChronology.getInstance().minutes().add(expected, 6);
//        expected = ISOChronology.getInstance().seconds().add(expected, 7);
//        expected = ISOChronology.getInstance().millis().add(expected, 8);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
//        MutableDateTime mdt = new MutableDateTime();
//        test.addInto(mdt, 1);
//        assertEquals(expected,mdt.getMillis());
//    }
//    
//    public void testAddIntoRWI2() {
//        long expected = TEST_TIME_NOW;
//        expected = ISOChronology.getInstance().years().add(expected, -2);
//        expected = ISOChronology.getInstance().months().add(expected, -4);
//        expected = ISOChronology.getInstance().weeks().add(expected, -6);
//        expected = ISOChronology.getInstance().days().add(expected, -8);
//        expected = ISOChronology.getInstance().hours().add(expected, -10);
//        expected = ISOChronology.getInstance().minutes().add(expected, -12);
//        expected = ISOChronology.getInstance().seconds().add(expected, -14);
//        expected = ISOChronology.getInstance().millis().add(expected, -16);
//        
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
//        MutableDateTime mdt = new MutableDateTime();
//        test.addInto(mdt, -2);  // MutableDateTime has a chronology, use it
//        assertEquals(expected,mdt.getMillis());
//    }
//    
//    public void testAddIntoRWI3() {
//        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
//        try {
//            test.addInto(null, 1);
//            fail();
//        } catch (IllegalArgumentException ex) {}
//    }
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
//    public void testToDurationMillisFrom() {
//        Period test = new Period(123L);
//        assertEquals(123L,test.toDurationMillisFrom(0L,null));
//    }

    //-----------------------------------------------------------------------

    public void testWithPeriodType3() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8, PeriodType.standard());
        try {
            test.withPeriodType(PeriodType.dayTime());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testWithField2() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        try {
            test.withField(null, 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithField3() {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.time());
        try {
            test.withField(DurationFieldType.years(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testWithFieldAdded2() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        try {
            test.withFieldAdded(null, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testWithFieldAdded3() {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.time());
        try {
            test.withFieldAdded(DurationFieldType.years(), 6);
            fail();
        } catch (IllegalArgumentException ex) {}
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

    public void testNormalizedStandard_yearMonth2() {
        Period test = new Period(Integer.MAX_VALUE, 15, 0, 0, 0, 0, 0, 0);
        try {
            test.normalizedStandard();
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void testNormalizedStandard_weekDay2() {
        Period test = new Period(0, 0, Integer.MAX_VALUE, 7, 0, 0, 0, 0);
        try {
            test.normalizedStandard();
            fail();
        } catch (ArithmeticException ex) {}
    }

    //-----------------------------------------------------------------------

    public void testNormalizedStandard_periodType_yearMonth2() {
        Period test = new Period(Integer.MAX_VALUE, 15, 0, 0, 0, 0, 0, 0);
        try {
            test.normalizedStandard((PeriodType) null);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void testNormalizedStandard_periodType_yearMonth3() {
        Period test = new Period(1, 15, 3, 4, 0, 0, 0, 0);
        try {
            test.normalizedStandard(PeriodType.dayTime());
            fail();
        } catch (UnsupportedOperationException ex) {}
    }

    public void testNormalizedStandard_periodType_weekDay2() {
        Period test = new Period(0, 0, Integer.MAX_VALUE, 7, 0, 0, 0, 0);
        try {
            test.normalizedStandard((PeriodType) null);
            fail();
        } catch (ArithmeticException ex) {}
    }

    public void testNormalizedStandard_periodType_years() {
        Period test = new Period(1, 15, 0, 0, 0, 0, 0, 0);
        try {
            test.normalizedStandard(PeriodType.years());
            fail();
        } catch (UnsupportedOperationException ex) {
            // expected
        }
    }

    public void testTest_1_oe() {
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW).toString());
    }

    public void testTest_2_oe() {
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1).toString());
    }

    public void testTest_3_oe() {
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2).toString());
    }

    public void testGetPeriodType_1_oe() {
        Period test = new Period(0L);
        assertEquals(PeriodType.standard(),test.getPeriodType());
    }

    public void testGetMethods_1_oe() {
        Period test = new Period(0L);
        assertEquals(0,test.getYears());
    }

    public void testGetMethods_2_oe() {
        Period test = new Period(0L);
        assertEquals(0,test.getMonths());
    }

    public void testGetMethods_3_oe() {
        Period test = new Period(0L);
        assertEquals(0,test.getWeeks());
    }

    public void testGetMethods_4_oe() {
        Period test = new Period(0L);
        assertEquals(0,test.getDays());
    }

    public void testGetMethods_5_oe() {
        Period test = new Period(0L);
        assertEquals(0,test.getHours());
    }

    public void testGetMethods_6_oe() {
        Period test = new Period(0L);
        assertEquals(0,test.getMinutes());
    }

    public void testGetMethods_7_oe() {
        Period test = new Period(0L);
        assertEquals(0,test.getSeconds());
    }

    public void testGetMethods_8_oe() {
        Period test = new Period(0L);
        assertEquals(0,test.getMillis());
    }

    public void testValueIndexMethods_1_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(6,test.size());
    }

    public void testValueIndexMethods_2_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(1,test.getValue(0));
    }

    public void testValueIndexMethods_3_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(4,test.getValue(1));
    }

    public void testValueIndexMethods_4_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(5,test.getValue(2));
    }

    public void testValueIndexMethods_5_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(6,test.getValue(3));
    }

    public void testValueIndexMethods_6_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(7,test.getValue(4));
    }

    public void testValueIndexMethods_7_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(8,test.getValue(5));
    }

    public void testValueIndexMethods_8_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(true, Arrays.equals(new int[] {1, 4, 5, 6, 7, 8}, test.getValues()));
    }

    public void testTypeIndexMethods_1_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(6,test.size());
    }

    public void testTypeIndexMethods_2_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(DurationFieldType.years(),test.getFieldType(0));
    }

    public void testTypeIndexMethods_3_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(DurationFieldType.days(),test.getFieldType(1));
    }

    public void testTypeIndexMethods_4_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(DurationFieldType.hours(),test.getFieldType(2));
    }

    public void testTypeIndexMethods_5_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(DurationFieldType.minutes(),test.getFieldType(3));
    }

    public void testTypeIndexMethods_6_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(DurationFieldType.seconds(),test.getFieldType(4));
    }

    public void testTypeIndexMethods_7_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(DurationFieldType.millis(),test.getFieldType(5));
    }

    public void testTypeIndexMethods_8_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(true, Arrays.equals(new DurationFieldType[] { DurationFieldType.years(), DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis()}, test.getFieldTypes()));
    }

    public void testIsSupported_1_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(true,test.isSupported(DurationFieldType.years()));
    }

    public void testIsSupported_2_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(false,test.isSupported(DurationFieldType.months()));
    }

    public void testIsSupported_3_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(false,test.isSupported(DurationFieldType.weeks()));
    }

    public void testIsSupported_4_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(true,test.isSupported(DurationFieldType.days()));
    }

    public void testIsSupported_5_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(true,test.isSupported(DurationFieldType.hours()));
    }

    public void testIsSupported_6_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(true,test.isSupported(DurationFieldType.minutes()));
    }

    public void testIsSupported_7_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(true,test.isSupported(DurationFieldType.seconds()));
    }

    public void testIsSupported_8_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(true,test.isSupported(DurationFieldType.millis()));
    }

    public void testIndexOf_1_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(0,test.indexOf(DurationFieldType.years()));
    }

    public void testIndexOf_2_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(-1,test.indexOf(DurationFieldType.months()));
    }

    public void testIndexOf_3_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(-1,test.indexOf(DurationFieldType.weeks()));
    }

    public void testIndexOf_4_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(1,test.indexOf(DurationFieldType.days()));
    }

    public void testIndexOf_5_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(2,test.indexOf(DurationFieldType.hours()));
    }

    public void testIndexOf_6_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(3,test.indexOf(DurationFieldType.minutes()));
    }

    public void testIndexOf_7_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(4,test.indexOf(DurationFieldType.seconds()));
    }

    public void testIndexOf_8_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(5,test.indexOf(DurationFieldType.millis()));
    }

    public void testGet_1_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(1,test.get(DurationFieldType.years()));
    }

    public void testGet_2_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(0,test.get(DurationFieldType.months()));
    }

    public void testGet_3_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(0,test.get(DurationFieldType.weeks()));
    }

    public void testGet_4_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(4,test.get(DurationFieldType.days()));
    }

    public void testGet_5_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(5,test.get(DurationFieldType.hours()));
    }

    public void testGet_6_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(6,test.get(DurationFieldType.minutes()));
    }

    public void testGet_7_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(7,test.get(DurationFieldType.seconds()));
    }

    public void testGet_8_oe() {
        Period test = new Period(1, 0, 0, 4, 5, 6, 7, 8, PeriodType.yearDayTime());
        assertEquals(8,test.get(DurationFieldType.millis()));
    }

    public void testEqualsHashCode_1_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        assertEquals(true,test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        assertEquals(true,test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        assertEquals(true,test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        assertEquals(true,test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        assertEquals(true,test1.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        assertEquals(true,test1.hashCode()== test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        assertEquals(true,test2.hashCode()== test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        
        Period test3 = new Period(321L);
        assertEquals(false,test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        
        Period test3 = new Period(321L);
        assertEquals(false,test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        
        Period test3 = new Period(321L);
        assertEquals(false,test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        
        Period test3 = new Period(321L);
        assertEquals(false,test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        
        Period test3 = new Period(321L);
        assertEquals(false,test1.hashCode()== test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        
        Period test3 = new Period(321L);
        assertEquals(false,test2.hashCode()== test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        
        Period test3 = new Period(321L);
        
        assertEquals(false,test1.equals("Hello"));
    }

    public void testEqualsHashCode_15_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        
        Period test3 = new Period(321L);
        
        assertEquals(true,test1.equals(new MockPeriod(123L)));
    }

    public void testEqualsHashCode_16_oe() {
        Period test1 = new Period(123L);
        Period test2 = new Period(123L);
        
        Period test3 = new Period(321L);
        
        assertEquals(false,test1.equals(new Period(123L,PeriodType.dayTime())));
    }

    public void testSerialization_1_oe() throws Exception {
        Period test = new Period(123L);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Period result = (Period) ois.readObject();
        ois.close();
        
        assertEquals(test,result);
    }

    public void testToString_1_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals("P1Y2M3W4DT5H6M7.008S",test.toString());
    }

    public void testToString_2_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("PT0S",test.toString());
    }

    public void testToString_3_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        
        test = new Period(12345L);
        assertEquals("PT12.345S",test.toString());
    }

    public void testToString_PeriodFormatter_2_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals("0 milliseconds",test.toString(PeriodFormat.getDefault()));
    }

    public void testToString_nullPeriodFormatter_1_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals("P1Y2M3W4DT5H6M7.008S",test.toString((PeriodFormatter)null));
    }

    public void testToPeriod_1_oe() {
        Period test = new Period(123L);
        Period result = test.toPeriod();
        assertSame(test,result);
    }

    public void testToMutablePeriod_1_oe() {
        Period test = new Period(123L);
        MutablePeriod result = test.toMutablePeriod();
        assertEquals(test,result);
    }

    public void testToDurationFrom_1_oe() {
        Period test = new Period(123L);
        assertEquals(new Duration(123L),test.toDurationFrom(new Instant(0L)));
    }

    public void testToDurationTo_1_oe() {
        Period test = new Period(123L);
        assertEquals(new Duration(123L),test.toDurationTo(new Instant(123L)));
    }

    public void testWithPeriodType1_1_oe() {
        Period test = new Period(123L);
        Period result = test.withPeriodType(PeriodType.standard());
        assertSame(test,result);
    }

    public void testWithPeriodType2_1_oe() {
        Period test = new Period(3123L);
        Period result = test.withPeriodType(PeriodType.dayTime());
        assertEquals(3,result.getSeconds());
    }

    public void testWithPeriodType2_2_oe() {
        Period test = new Period(3123L);
        Period result = test.withPeriodType(PeriodType.dayTime());
        assertEquals(123,result.getMillis());
    }

    public void testWithPeriodType2_3_oe() {
        Period test = new Period(3123L);
        Period result = test.withPeriodType(PeriodType.dayTime());
        assertEquals(PeriodType.dayTime(),result.getPeriodType());
    }

    public void testWithPeriodType4_1_oe() {
        Period test = new Period(3123L);
        Period result = test.withPeriodType(null);
        assertEquals(3,result.getSeconds());
    }

    public void testWithPeriodType4_2_oe() {
        Period test = new Period(3123L);
        Period result = test.withPeriodType(null);
        assertEquals(123,result.getMillis());
    }

    public void testWithPeriodType4_3_oe() {
        Period test = new Period(3123L);
        Period result = test.withPeriodType(null);
        assertEquals(PeriodType.standard(),result.getPeriodType());
    }

    public void testWithPeriodType5_1_oe() {
        Period test = new Period(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.standard());
        Period result = test.withPeriodType(PeriodType.yearMonthDayTime());
        assertEquals(PeriodType.yearMonthDayTime(),result.getPeriodType());
    }

    public void testWithPeriodType5_2_oe() {
        Period test = new Period(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.standard());
        Period result = test.withPeriodType(PeriodType.yearMonthDayTime());
        assertEquals(1,result.getYears());
    }

    public void testWithPeriodType5_3_oe() {
        Period test = new Period(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.standard());
        Period result = test.withPeriodType(PeriodType.yearMonthDayTime());
        assertEquals(2,result.getMonths());
    }

    public void testWithPeriodType5_4_oe() {
        Period test = new Period(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.standard());
        Period result = test.withPeriodType(PeriodType.yearMonthDayTime());
        assertEquals(0,result.getWeeks());
    }

    public void testWithPeriodType5_5_oe() {
        Period test = new Period(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.standard());
        Period result = test.withPeriodType(PeriodType.yearMonthDayTime());
        assertEquals(4,result.getDays());
    }

    public void testWithPeriodType5_6_oe() {
        Period test = new Period(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.standard());
        Period result = test.withPeriodType(PeriodType.yearMonthDayTime());
        assertEquals(5,result.getHours());
    }

    public void testWithPeriodType5_7_oe() {
        Period test = new Period(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.standard());
        Period result = test.withPeriodType(PeriodType.yearMonthDayTime());
        assertEquals(6,result.getMinutes());
    }

    public void testWithPeriodType5_8_oe() {
        Period test = new Period(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.standard());
        Period result = test.withPeriodType(PeriodType.yearMonthDayTime());
        assertEquals(7,result.getSeconds());
    }

    public void testWithPeriodType5_9_oe() {
        Period test = new Period(1, 2, 0, 4, 5, 6, 7, 8, PeriodType.standard());
        Period result = test.withPeriodType(PeriodType.yearMonthDayTime());
        assertEquals(8,result.getMillis());
    }

    public void testWithFields1_1_oe() {
        Period test1 = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period test2 = new Period(0, 0, 0, 0, 0, 0, 0, 9, PeriodType.millis());
        Period result = test1.withFields(test2);
        
        assertEquals(new Period(1,2,3,4,5,6,7,8),test1);
    }

    public void testWithFields1_2_oe() {
        Period test1 = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period test2 = new Period(0, 0, 0, 0, 0, 0, 0, 9, PeriodType.millis());
        Period result = test1.withFields(test2);
        
        assertEquals(new Period(0,0,0,0,0,0,0,9,PeriodType.millis()),test2);
    }

    public void testWithFields1_3_oe() {
        Period test1 = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period test2 = new Period(0, 0, 0, 0, 0, 0, 0, 9, PeriodType.millis());
        Period result = test1.withFields(test2);
        
        assertEquals(new Period(1,2,3,4,5,6,7,9),result);
    }

    public void testWithFields2_1_oe() {
        Period test1 = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period test2 = null;
        Period result = test1.withFields(test2);
        
        assertEquals(new Period(1,2,3,4,5,6,7,8),test1);
    }

    public void testWithFields2_2_oe() {
        Period test1 = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period test2 = null;
        Period result = test1.withFields(test2);
        
        assertSame(test1,result);
    }

    public void testWithFields3_2_oe() {
        Period test1 = new Period(0, 0, 0, 0, 0, 0, 0, 9, PeriodType.millis());
        Period test2 = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        try {
            test1.withFields(test2);
        } catch (IllegalArgumentException ex) {}
        assertEquals(new Period(0,0,0,0,0,0,0,9,PeriodType.millis()),test1);
    }

    public void testWithFields3_3_oe() {
        Period test1 = new Period(0, 0, 0, 0, 0, 0, 0, 9, PeriodType.millis());
        Period test2 = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        try {
            test1.withFields(test2);
        } catch (IllegalArgumentException ex) {}
        assertEquals(new Period(1,2,3,4,5,6,7,8),test2);
    }

    public void testWithField1_1_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period result = test.withField(DurationFieldType.years(), 6);
        
        assertEquals(new Period(1,2,3,4,5,6,7,8),test);
    }

    public void testWithField1_2_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period result = test.withField(DurationFieldType.years(), 6);
        
        assertEquals(new Period(6,2,3,4,5,6,7,8),result);
    }

    public void testWithField4_1_oe() {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.time());
        Period result = test.withField(DurationFieldType.years(), 0);
        assertEquals(test,result);
    }

    public void testWithFieldAdded1_1_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(new Period(1,2,3,4,5,6,7,8),test);
    }

    public void testWithFieldAdded1_2_oe() {
        Period test = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period result = test.withFieldAdded(DurationFieldType.years(), 6);
        
        assertEquals(new Period(7,2,3,4,5,6,7,8),result);
    }

    public void testWithFieldAdded4_1_oe() {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8, PeriodType.time());
        Period result = test.withFieldAdded(DurationFieldType.years(), 0);
        assertEquals(test,result);
    }

    public void testPeriodStatics_1_oe() {
        Period test;
        test = Period.years(1);
        assertEquals(test,new Period(1,0,0,0,0,0,0,0,PeriodType.standard()));
    }

    public void testPeriodStatics_2_oe() {
        Period test;
        test = Period.years(1);
        test = Period.months(1);
        assertEquals(test,new Period(0,1,0,0,0,0,0,0,PeriodType.standard()));
    }

    public void testPeriodStatics_3_oe() {
        Period test;
        test = Period.years(1);
        test = Period.months(1);
        test = Period.weeks(1);
        assertEquals(test,new Period(0,0,1,0,0,0,0,0,PeriodType.standard()));
    }

    public void testPeriodStatics_4_oe() {
        Period test;
        test = Period.years(1);
        test = Period.months(1);
        test = Period.weeks(1);
        test = Period.days(1);
        assertEquals(test,new Period(0,0,0,1,0,0,0,0,PeriodType.standard()));
    }

    public void testPeriodStatics_5_oe() {
        Period test;
        test = Period.years(1);
        test = Period.months(1);
        test = Period.weeks(1);
        test = Period.days(1);
        test = Period.hours(1);
        assertEquals(test,new Period(0,0,0,0,1,0,0,0,PeriodType.standard()));
    }

    public void testPeriodStatics_6_oe() {
        Period test;
        test = Period.years(1);
        test = Period.months(1);
        test = Period.weeks(1);
        test = Period.days(1);
        test = Period.hours(1);
        test = Period.minutes(1);
        assertEquals(test,new Period(0,0,0,0,0,1,0,0,PeriodType.standard()));
    }

    public void testPeriodStatics_7_oe() {
        Period test;
        test = Period.years(1);
        test = Period.months(1);
        test = Period.weeks(1);
        test = Period.days(1);
        test = Period.hours(1);
        test = Period.minutes(1);
        test = Period.seconds(1);
        assertEquals(test,new Period(0,0,0,0,0,0,1,0,PeriodType.standard()));
    }

    public void testPeriodStatics_8_oe() {
        Period test;
        test = Period.years(1);
        test = Period.months(1);
        test = Period.weeks(1);
        test = Period.days(1);
        test = Period.hours(1);
        test = Period.minutes(1);
        test = Period.seconds(1);
        test = Period.millis(1);
        assertEquals(test,new Period(0,0,0,0,0,0,0,1,PeriodType.standard()));
    }

    public void testWith_1_oe() {
        Period test;
        test = Period.years(5).withYears(1);
        assertEquals(test,new Period(1,0,0,0,0,0,0,0,PeriodType.standard()));
    }

    public void testWith_2_oe() {
        Period test;
        test = Period.years(5).withYears(1);
        test = Period.months(5).withMonths(1);
        assertEquals(test,new Period(0,1,0,0,0,0,0,0,PeriodType.standard()));
    }

    public void testWith_3_oe() {
        Period test;
        test = Period.years(5).withYears(1);
        test = Period.months(5).withMonths(1);
        test = Period.weeks(5).withWeeks(1);
        assertEquals(test,new Period(0,0,1,0,0,0,0,0,PeriodType.standard()));
    }

    public void testWith_4_oe() {
        Period test;
        test = Period.years(5).withYears(1);
        test = Period.months(5).withMonths(1);
        test = Period.weeks(5).withWeeks(1);
        test = Period.days(5).withDays(1);
        assertEquals(test,new Period(0,0,0,1,0,0,0,0,PeriodType.standard()));
    }

    public void testWith_5_oe() {
        Period test;
        test = Period.years(5).withYears(1);
        test = Period.months(5).withMonths(1);
        test = Period.weeks(5).withWeeks(1);
        test = Period.days(5).withDays(1);
        test = Period.hours(5).withHours(1);
        assertEquals(test,new Period(0,0,0,0,1,0,0,0,PeriodType.standard()));
    }

    public void testWith_6_oe() {
        Period test;
        test = Period.years(5).withYears(1);
        test = Period.months(5).withMonths(1);
        test = Period.weeks(5).withWeeks(1);
        test = Period.days(5).withDays(1);
        test = Period.hours(5).withHours(1);
        test = Period.minutes(5).withMinutes(1);
        assertEquals(test,new Period(0,0,0,0,0,1,0,0,PeriodType.standard()));
    }

    public void testWith_7_oe() {
        Period test;
        test = Period.years(5).withYears(1);
        test = Period.months(5).withMonths(1);
        test = Period.weeks(5).withWeeks(1);
        test = Period.days(5).withDays(1);
        test = Period.hours(5).withHours(1);
        test = Period.minutes(5).withMinutes(1);
        test = Period.seconds(5).withSeconds(1);
        assertEquals(test,new Period(0,0,0,0,0,0,1,0,PeriodType.standard()));
    }

    public void testWith_8_oe() {
        Period test;
        test = Period.years(5).withYears(1);
        test = Period.months(5).withMonths(1);
        test = Period.weeks(5).withWeeks(1);
        test = Period.days(5).withDays(1);
        test = Period.hours(5).withHours(1);
        test = Period.minutes(5).withMinutes(1);
        test = Period.seconds(5).withSeconds(1);
        test = Period.millis(5).withMillis(1);
        assertEquals(test,new Period(0,0,0,0,0,0,0,1,PeriodType.standard()));
    }

    public void testPlus_1_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        assertSame(base,test);
    }

    public void testPlus_2_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        assertEquals(11,test.getYears());
    }

    public void testPlus_3_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        assertEquals(2,test.getMonths());
    }

    public void testPlus_4_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        assertEquals(3,test.getWeeks());
    }

    public void testPlus_5_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        assertEquals(4,test.getDays());
    }

    public void testPlus_6_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        assertEquals(5,test.getHours());
    }

    public void testPlus_7_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        assertEquals(6,test.getMinutes());
    }

    public void testPlus_8_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        assertEquals(7,test.getSeconds());
    }

    public void testPlus_9_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        assertEquals(8,test.getMillis());
    }

    public void testPlus_10_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        assertEquals(11,test.getYears());
    }

    public void testPlus_11_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        assertEquals(2,test.getMonths());
    }

    public void testPlus_12_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        assertEquals(3,test.getWeeks());
    }

    public void testPlus_13_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        assertEquals(4,test.getDays());
    }

    public void testPlus_14_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        assertEquals(5,test.getHours());
    }

    public void testPlus_15_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        assertEquals(6,test.getMinutes());
    }

    public void testPlus_16_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        assertEquals(7,test.getSeconds());
    }

    public void testPlus_17_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        assertEquals(8,test.getMillis());
    }

    public void testPlus_18_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        assertEquals(1,test.getYears());
    }

    public void testPlus_19_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        assertEquals(2,test.getMonths());
    }

    public void testPlus_20_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        assertEquals(3,test.getWeeks());
    }

    public void testPlus_21_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        assertEquals(14,test.getDays());
    }

    public void testPlus_22_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        assertEquals(5,test.getHours());
    }

    public void testPlus_23_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        assertEquals(6,test.getMinutes());
    }

    public void testPlus_24_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        assertEquals(7,test.getSeconds());
    }

    public void testPlus_25_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        assertEquals(8,test.getMillis());
    }

    public void testPlus_26_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        assertEquals(0,test.getYears());
    }

    public void testPlus_27_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        assertEquals(0,test.getMonths());
    }

    public void testPlus_28_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        assertEquals(0,test.getWeeks());
    }

    public void testPlus_29_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        assertEquals(10,test.getDays());
    }

    public void testPlus_30_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        assertEquals(0,test.getHours());
    }

    public void testPlus_31_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        assertEquals(0,test.getMinutes());
    }

    public void testPlus_32_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        assertEquals(0,test.getSeconds());
    }

    public void testPlus_33_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        assertEquals(0,test.getMillis());
    }

    public void testPlus_34_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        
        test = baseDaysOnly.plus(baseDaysOnly);
        assertEquals(0,test.getYears());
    }

    public void testPlus_35_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        
        test = baseDaysOnly.plus(baseDaysOnly);
        assertEquals(0,test.getMonths());
    }

    public void testPlus_36_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        
        test = baseDaysOnly.plus(baseDaysOnly);
        assertEquals(0,test.getWeeks());
    }

    public void testPlus_37_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        
        test = baseDaysOnly.plus(baseDaysOnly);
        assertEquals(20,test.getDays());
    }

    public void testPlus_38_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        
        test = baseDaysOnly.plus(baseDaysOnly);
        assertEquals(0,test.getHours());
    }

    public void testPlus_39_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        
        test = baseDaysOnly.plus(baseDaysOnly);
        assertEquals(0,test.getMinutes());
    }

    public void testPlus_40_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        
        test = baseDaysOnly.plus(baseDaysOnly);
        assertEquals(0,test.getSeconds());
    }

    public void testPlus_41_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.plus((ReadablePeriod) null);
        
        test = base.plus(Period.years(10));
        
        test = base.plus(Years.years(10));
        
        test = base.plus(Period.days(10));
        
        test = baseDaysOnly.plus(Period.years(0));
        
        test = baseDaysOnly.plus(baseDaysOnly);
        assertEquals(0,test.getMillis());
    }

    public void testMinus_1_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        assertSame(base,test);
    }

    public void testMinus_2_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        assertEquals(-9,test.getYears());
    }

    public void testMinus_3_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        assertEquals(2,test.getMonths());
    }

    public void testMinus_4_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        assertEquals(3,test.getWeeks());
    }

    public void testMinus_5_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        assertEquals(4,test.getDays());
    }

    public void testMinus_6_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        assertEquals(5,test.getHours());
    }

    public void testMinus_7_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        assertEquals(6,test.getMinutes());
    }

    public void testMinus_8_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        assertEquals(7,test.getSeconds());
    }

    public void testMinus_9_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        assertEquals(8,test.getMillis());
    }

    public void testMinus_10_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        assertEquals(-9,test.getYears());
    }

    public void testMinus_11_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        assertEquals(2,test.getMonths());
    }

    public void testMinus_12_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        assertEquals(3,test.getWeeks());
    }

    public void testMinus_13_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        assertEquals(4,test.getDays());
    }

    public void testMinus_14_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        assertEquals(5,test.getHours());
    }

    public void testMinus_15_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        assertEquals(6,test.getMinutes());
    }

    public void testMinus_16_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        assertEquals(7,test.getSeconds());
    }

    public void testMinus_17_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        assertEquals(8,test.getMillis());
    }

    public void testMinus_18_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        assertEquals(1,test.getYears());
    }

    public void testMinus_19_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        assertEquals(2,test.getMonths());
    }

    public void testMinus_20_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        assertEquals(3,test.getWeeks());
    }

    public void testMinus_21_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        assertEquals(-6,test.getDays());
    }

    public void testMinus_22_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        assertEquals(5,test.getHours());
    }

    public void testMinus_23_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        assertEquals(6,test.getMinutes());
    }

    public void testMinus_24_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        assertEquals(7,test.getSeconds());
    }

    public void testMinus_25_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        assertEquals(8,test.getMillis());
    }

    public void testMinus_26_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        assertEquals(0,test.getYears());
    }

    public void testMinus_27_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        assertEquals(0,test.getMonths());
    }

    public void testMinus_28_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        assertEquals(0,test.getWeeks());
    }

    public void testMinus_29_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        assertEquals(10,test.getDays());
    }

    public void testMinus_30_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        assertEquals(0,test.getHours());
    }

    public void testMinus_31_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        assertEquals(0,test.getMinutes());
    }

    public void testMinus_32_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        assertEquals(0,test.getSeconds());
    }

    public void testMinus_33_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        assertEquals(0,test.getMillis());
    }

    public void testMinus_34_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        
        test = baseDaysOnly.minus(baseDaysOnly);
        assertEquals(0,test.getYears());
    }

    public void testMinus_35_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        
        test = baseDaysOnly.minus(baseDaysOnly);
        assertEquals(0,test.getMonths());
    }

    public void testMinus_36_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        
        test = baseDaysOnly.minus(baseDaysOnly);
        assertEquals(0,test.getWeeks());
    }

    public void testMinus_37_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        
        test = baseDaysOnly.minus(baseDaysOnly);
        assertEquals(0,test.getDays());
    }

    public void testMinus_38_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        
        test = baseDaysOnly.minus(baseDaysOnly);
        assertEquals(0,test.getHours());
    }

    public void testMinus_39_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        
        test = baseDaysOnly.minus(baseDaysOnly);
        assertEquals(0,test.getMinutes());
    }

    public void testMinus_40_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        
        test = baseDaysOnly.minus(baseDaysOnly);
        assertEquals(0,test.getSeconds());
    }

    public void testMinus_41_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        Period baseDaysOnly = new Period(0, 0, 0, 10, 0, 0, 0, 0, PeriodType.days());
        
        Period test = base.minus((ReadablePeriod) null);
        
        test = base.minus(Period.years(10));
        
        test = base.minus(Years.years(10));
        
        test = base.minus(Period.days(10));
        
        test = baseDaysOnly.minus(Period.years(0));
        
        test = baseDaysOnly.minus(baseDaysOnly);
        assertEquals(0,test.getMillis());
    }

    public void testPlusFields_1_oe() {
        Period test;
        test = Period.years(1).plusYears(1);
        assertEquals(new Period(2,0,0,0,0,0,0,0,PeriodType.standard()),test);
    }

    public void testPlusFields_2_oe() {
        Period test;
        test = Period.years(1).plusYears(1);
        test = Period.months(1).plusMonths(1);
        assertEquals(new Period(0,2,0,0,0,0,0,0,PeriodType.standard()),test);
    }

    public void testPlusFields_3_oe() {
        Period test;
        test = Period.years(1).plusYears(1);
        test = Period.months(1).plusMonths(1);
        test = Period.weeks(1).plusWeeks(1);
        assertEquals(new Period(0,0,2,0,0,0,0,0,PeriodType.standard()),test);
    }

    public void testPlusFields_4_oe() {
        Period test;
        test = Period.years(1).plusYears(1);
        test = Period.months(1).plusMonths(1);
        test = Period.weeks(1).plusWeeks(1);
        test = Period.days(1).plusDays(1);
        assertEquals(new Period(0,0,0,2,0,0,0,0,PeriodType.standard()),test);
    }

    public void testPlusFields_5_oe() {
        Period test;
        test = Period.years(1).plusYears(1);
        test = Period.months(1).plusMonths(1);
        test = Period.weeks(1).plusWeeks(1);
        test = Period.days(1).plusDays(1);
        test = Period.hours(1).plusHours(1);
        assertEquals(new Period(0,0,0,0,2,0,0,0,PeriodType.standard()),test);
    }

    public void testPlusFields_6_oe() {
        Period test;
        test = Period.years(1).plusYears(1);
        test = Period.months(1).plusMonths(1);
        test = Period.weeks(1).plusWeeks(1);
        test = Period.days(1).plusDays(1);
        test = Period.hours(1).plusHours(1);
        test = Period.minutes(1).plusMinutes(1);
        assertEquals(new Period(0,0,0,0,0,2,0,0,PeriodType.standard()),test);
    }

    public void testPlusFields_7_oe() {
        Period test;
        test = Period.years(1).plusYears(1);
        test = Period.months(1).plusMonths(1);
        test = Period.weeks(1).plusWeeks(1);
        test = Period.days(1).plusDays(1);
        test = Period.hours(1).plusHours(1);
        test = Period.minutes(1).plusMinutes(1);
        test = Period.seconds(1).plusSeconds(1);
        assertEquals(new Period(0,0,0,0,0,0,2,0,PeriodType.standard()),test);
    }

    public void testPlusFields_8_oe() {
        Period test;
        test = Period.years(1).plusYears(1);
        test = Period.months(1).plusMonths(1);
        test = Period.weeks(1).plusWeeks(1);
        test = Period.days(1).plusDays(1);
        test = Period.hours(1).plusHours(1);
        test = Period.minutes(1).plusMinutes(1);
        test = Period.seconds(1).plusSeconds(1);
        test = Period.millis(1).plusMillis(1);
        assertEquals(new Period(0,0,0,0,0,0,0,2,PeriodType.standard()),test);
    }

    public void testPlusFieldsZero_1_oe() {
        Period test, result;
        test = Period.years(1);
        result = test.plusYears(0);
        assertSame(test,result);
    }

    public void testPlusFieldsZero_2_oe() {
        Period test, result;
        test = Period.years(1);
        result = test.plusYears(0);
        test = Period.months(1);
        result = test.plusMonths(0);
        assertSame(test,result);
    }

    public void testPlusFieldsZero_3_oe() {
        Period test, result;
        test = Period.years(1);
        result = test.plusYears(0);
        test = Period.months(1);
        result = test.plusMonths(0);
        test = Period.weeks(1);
        result = test.plusWeeks(0);
        assertSame(test,result);
    }

    public void testPlusFieldsZero_4_oe() {
        Period test, result;
        test = Period.years(1);
        result = test.plusYears(0);
        test = Period.months(1);
        result = test.plusMonths(0);
        test = Period.weeks(1);
        result = test.plusWeeks(0);
        test = Period.days(1);
        result = test.plusDays(0);
        assertSame(test,result);
    }

    public void testPlusFieldsZero_5_oe() {
        Period test, result;
        test = Period.years(1);
        result = test.plusYears(0);
        test = Period.months(1);
        result = test.plusMonths(0);
        test = Period.weeks(1);
        result = test.plusWeeks(0);
        test = Period.days(1);
        result = test.plusDays(0);
        test = Period.hours(1);
        result = test.plusHours(0);
        assertSame(test,result);
    }

    public void testPlusFieldsZero_6_oe() {
        Period test, result;
        test = Period.years(1);
        result = test.plusYears(0);
        test = Period.months(1);
        result = test.plusMonths(0);
        test = Period.weeks(1);
        result = test.plusWeeks(0);
        test = Period.days(1);
        result = test.plusDays(0);
        test = Period.hours(1);
        result = test.plusHours(0);
        test = Period.minutes(1);
        result = test.plusMinutes(0);
        assertSame(test,result);
    }

    public void testPlusFieldsZero_7_oe() {
        Period test, result;
        test = Period.years(1);
        result = test.plusYears(0);
        test = Period.months(1);
        result = test.plusMonths(0);
        test = Period.weeks(1);
        result = test.plusWeeks(0);
        test = Period.days(1);
        result = test.plusDays(0);
        test = Period.hours(1);
        result = test.plusHours(0);
        test = Period.minutes(1);
        result = test.plusMinutes(0);
        test = Period.seconds(1);
        result = test.plusSeconds(0);
        assertSame(test,result);
    }

    public void testPlusFieldsZero_8_oe() {
        Period test, result;
        test = Period.years(1);
        result = test.plusYears(0);
        test = Period.months(1);
        result = test.plusMonths(0);
        test = Period.weeks(1);
        result = test.plusWeeks(0);
        test = Period.days(1);
        result = test.plusDays(0);
        test = Period.hours(1);
        result = test.plusHours(0);
        test = Period.minutes(1);
        result = test.plusMinutes(0);
        test = Period.seconds(1);
        result = test.plusSeconds(0);
        test = Period.millis(1);
        result = test.plusMillis(0);
        assertSame(test,result);
    }

    public void testMinusFields_1_oe() {
        Period test;
        test = Period.years(3).minusYears(1);
        assertEquals(new Period(2,0,0,0,0,0,0,0,PeriodType.standard()),test);
    }

    public void testMinusFields_2_oe() {
        Period test;
        test = Period.years(3).minusYears(1);
        test = Period.months(3).minusMonths(1);
        assertEquals(new Period(0,2,0,0,0,0,0,0,PeriodType.standard()),test);
    }

    public void testMinusFields_3_oe() {
        Period test;
        test = Period.years(3).minusYears(1);
        test = Period.months(3).minusMonths(1);
        test = Period.weeks(3).minusWeeks(1);
        assertEquals(new Period(0,0,2,0,0,0,0,0,PeriodType.standard()),test);
    }

    public void testMinusFields_4_oe() {
        Period test;
        test = Period.years(3).minusYears(1);
        test = Period.months(3).minusMonths(1);
        test = Period.weeks(3).minusWeeks(1);
        test = Period.days(3).minusDays(1);
        assertEquals(new Period(0,0,0,2,0,0,0,0,PeriodType.standard()),test);
    }

    public void testMinusFields_5_oe() {
        Period test;
        test = Period.years(3).minusYears(1);
        test = Period.months(3).minusMonths(1);
        test = Period.weeks(3).minusWeeks(1);
        test = Period.days(3).minusDays(1);
        test = Period.hours(3).minusHours(1);
        assertEquals(new Period(0,0,0,0,2,0,0,0,PeriodType.standard()),test);
    }

    public void testMinusFields_6_oe() {
        Period test;
        test = Period.years(3).minusYears(1);
        test = Period.months(3).minusMonths(1);
        test = Period.weeks(3).minusWeeks(1);
        test = Period.days(3).minusDays(1);
        test = Period.hours(3).minusHours(1);
        test = Period.minutes(3).minusMinutes(1);
        assertEquals(new Period(0,0,0,0,0,2,0,0,PeriodType.standard()),test);
    }

    public void testMinusFields_7_oe() {
        Period test;
        test = Period.years(3).minusYears(1);
        test = Period.months(3).minusMonths(1);
        test = Period.weeks(3).minusWeeks(1);
        test = Period.days(3).minusDays(1);
        test = Period.hours(3).minusHours(1);
        test = Period.minutes(3).minusMinutes(1);
        test = Period.seconds(3).minusSeconds(1);
        assertEquals(new Period(0,0,0,0,0,0,2,0,PeriodType.standard()),test);
    }

    public void testMinusFields_8_oe() {
        Period test;
        test = Period.years(3).minusYears(1);
        test = Period.months(3).minusMonths(1);
        test = Period.weeks(3).minusWeeks(1);
        test = Period.days(3).minusDays(1);
        test = Period.hours(3).minusHours(1);
        test = Period.minutes(3).minusMinutes(1);
        test = Period.seconds(3).minusSeconds(1);
        test = Period.millis(3).minusMillis(1);
        assertEquals(new Period(0,0,0,0,0,0,0,2,PeriodType.standard()),test);
    }

    public void testMultipliedBy_1_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        assertSame(base,test);
    }

    public void testMultipliedBy_2_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        assertEquals(Period.ZERO,test);
    }

    public void testMultipliedBy_3_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        assertEquals(2,test.getYears());
    }

    public void testMultipliedBy_4_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        assertEquals(4,test.getMonths());
    }

    public void testMultipliedBy_5_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        assertEquals(6,test.getWeeks());
    }

    public void testMultipliedBy_6_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        assertEquals(8,test.getDays());
    }

    public void testMultipliedBy_7_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        assertEquals(10,test.getHours());
    }

    public void testMultipliedBy_8_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        assertEquals(12,test.getMinutes());
    }

    public void testMultipliedBy_9_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        assertEquals(14,test.getSeconds());
    }

    public void testMultipliedBy_10_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        assertEquals(16,test.getMillis());
    }

    public void testMultipliedBy_11_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        assertEquals(3,test.getYears());
    }

    public void testMultipliedBy_12_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        assertEquals(6,test.getMonths());
    }

    public void testMultipliedBy_13_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        assertEquals(9,test.getWeeks());
    }

    public void testMultipliedBy_14_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        assertEquals(12,test.getDays());
    }

    public void testMultipliedBy_15_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        assertEquals(15,test.getHours());
    }

    public void testMultipliedBy_16_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        assertEquals(18,test.getMinutes());
    }

    public void testMultipliedBy_17_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        assertEquals(21,test.getSeconds());
    }

    public void testMultipliedBy_18_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        assertEquals(24,test.getMillis());
    }

    public void testMultipliedBy_19_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        
        test = base.multipliedBy(-4);
        assertEquals(-4,test.getYears());
    }

    public void testMultipliedBy_20_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        
        test = base.multipliedBy(-4);
        assertEquals(-8,test.getMonths());
    }

    public void testMultipliedBy_21_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        
        test = base.multipliedBy(-4);
        assertEquals(-12,test.getWeeks());
    }

    public void testMultipliedBy_22_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        
        test = base.multipliedBy(-4);
        assertEquals(-16,test.getDays());
    }

    public void testMultipliedBy_23_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        
        test = base.multipliedBy(-4);
        assertEquals(-20,test.getHours());
    }

    public void testMultipliedBy_24_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        
        test = base.multipliedBy(-4);
        assertEquals(-24,test.getMinutes());
    }

    public void testMultipliedBy_25_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        
        test = base.multipliedBy(-4);
        assertEquals(-28,test.getSeconds());
    }

    public void testMultipliedBy_26_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = base.multipliedBy(1);
        
        test = base.multipliedBy(0);
        
        test = base.multipliedBy(2);
        
        test = base.multipliedBy(3);
        
        test = base.multipliedBy(-4);
        assertEquals(-32,test.getMillis());
    }

    public void testNegated_1_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = Period.ZERO.negated();
        assertEquals(Period.ZERO,test);
    }

    public void testNegated_2_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = Period.ZERO.negated();
        
        test = base.negated();
        assertEquals(-1,test.getYears());
    }

    public void testNegated_3_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = Period.ZERO.negated();
        
        test = base.negated();
        assertEquals(-2,test.getMonths());
    }

    public void testNegated_4_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = Period.ZERO.negated();
        
        test = base.negated();
        assertEquals(-3,test.getWeeks());
    }

    public void testNegated_5_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = Period.ZERO.negated();
        
        test = base.negated();
        assertEquals(-4,test.getDays());
    }

    public void testNegated_6_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = Period.ZERO.negated();
        
        test = base.negated();
        assertEquals(-5,test.getHours());
    }

    public void testNegated_7_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = Period.ZERO.negated();
        
        test = base.negated();
        assertEquals(-6,test.getMinutes());
    }

    public void testNegated_8_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = Period.ZERO.negated();
        
        test = base.negated();
        assertEquals(-7,test.getSeconds());
    }

    public void testNegated_9_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = Period.ZERO.negated();
        
        test = base.negated();
        assertEquals(-8,test.getMillis());
    }

    public void testNegated_10_oe() {
        Period base = new Period(1, 2, 3, 4, 5, 6, 7, 8);
        
        Period test = Period.ZERO.negated();
        
        test = base.negated();
        
        test = Period.days(Integer.MAX_VALUE).negated();
        assertEquals(-Integer.MAX_VALUE,test.getDays());
    }

    public void testToStandardWeeks_1_oe() {
        Period test = new Period(0, 0, 3, 4, 5, 6, 7, 8);
        assertEquals(3,test.toStandardWeeks().getWeeks());
    }

    public void testToStandardWeeks_2_oe() {
        Period test = new Period(0, 0, 3, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 3, 7, 0, 0, 0, 0);
        assertEquals(4,test.toStandardWeeks().getWeeks());
    }

    public void testToStandardWeeks_3_oe() {
        Period test = new Period(0, 0, 3, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 3, 7, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, 6, 23, 59, 59, 1000);
        assertEquals(1,test.toStandardWeeks().getWeeks());
    }

    public void testToStandardWeeks_4_oe() {
        Period test = new Period(0, 0, 3, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 3, 7, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, 6, 23, 59, 59, 1000);
        
        test = new Period(0, 0, Integer.MAX_VALUE, 0, 0, 0, 0, 0);
        assertEquals(Integer.MAX_VALUE,test.toStandardWeeks().getWeeks());
    }

    public void testToStandardWeeks_5_oe() {
        Period test = new Period(0, 0, 3, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 3, 7, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, 6, 23, 59, 59, 1000);
        
        test = new Period(0, 0, Integer.MAX_VALUE, 0, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        long intMax = Integer.MAX_VALUE;
        BigInteger expected = BigInteger.valueOf(intMax);
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_SECOND));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_MINUTE));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_HOUR));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_DAY));
        expected = expected.divide(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_WEEK));
        assertTrue(expected.compareTo(BigInteger.valueOf(Long.MAX_VALUE))< 0);
    }

    public void testToStandardWeeks_6_oe() {
        Period test = new Period(0, 0, 3, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 3, 7, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, 6, 23, 59, 59, 1000);
        
        test = new Period(0, 0, Integer.MAX_VALUE, 0, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        long intMax = Integer.MAX_VALUE;
        BigInteger expected = BigInteger.valueOf(intMax);
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_SECOND));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_MINUTE));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_HOUR));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_DAY));
        expected = expected.divide(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_WEEK));
        assertEquals(expected.longValue(),test.toStandardWeeks().getWeeks());
    }

    public void testToStandardWeeks_years_3_oe() {
        Period test = Period.years(1);
        try {
            test.toStandardWeeks();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(-1);
        try {
            test.toStandardWeeks();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(0);
        assertEquals(0,test.toStandardWeeks().getWeeks());
    }

    public void testToStandardWeeks_months_3_oe() {
        Period test = Period.months(1);
        try {
            test.toStandardWeeks();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(-1);
        try {
            test.toStandardWeeks();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(0);
        assertEquals(0,test.toStandardWeeks().getWeeks());
    }

    public void testToStandardDays_1_oe() {
        Period test = new Period(0, 0, 0, 4, 5, 6, 7, 8);
        assertEquals(4,test.toStandardDays().getDays());
    }

    public void testToStandardDays_2_oe() {
        Period test = new Period(0, 0, 0, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 1, 4, 0, 0, 0, 0);
        assertEquals(11,test.toStandardDays().getDays());
    }

    public void testToStandardDays_3_oe() {
        Period test = new Period(0, 0, 0, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 1, 4, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, 23, 59, 59, 1000);
        assertEquals(1,test.toStandardDays().getDays());
    }

    public void testToStandardDays_4_oe() {
        Period test = new Period(0, 0, 0, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 1, 4, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, 23, 59, 59, 1000);
        
        test = new Period(0, 0, 0, Integer.MAX_VALUE, 0, 0, 0, 0);
        assertEquals(Integer.MAX_VALUE,test.toStandardDays().getDays());
    }

    public void testToStandardDays_5_oe() {
        Period test = new Period(0, 0, 0, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 1, 4, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, 23, 59, 59, 1000);
        
        test = new Period(0, 0, 0, Integer.MAX_VALUE, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        long intMax = Integer.MAX_VALUE;
        BigInteger expected = BigInteger.valueOf(intMax);
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_SECOND));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_MINUTE));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_HOUR));
        expected = expected.divide(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_DAY));
        assertTrue(expected.compareTo(BigInteger.valueOf(Long.MAX_VALUE))< 0);
    }

    public void testToStandardDays_6_oe() {
        Period test = new Period(0, 0, 0, 4, 5, 6, 7, 8);
        
        test = new Period(0, 0, 1, 4, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, 23, 59, 59, 1000);
        
        test = new Period(0, 0, 0, Integer.MAX_VALUE, 0, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        long intMax = Integer.MAX_VALUE;
        BigInteger expected = BigInteger.valueOf(intMax);
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_SECOND));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_MINUTE));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_HOUR));
        expected = expected.divide(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_DAY));
        assertEquals(expected.longValue(),test.toStandardDays().getDays());
    }

    public void testToStandardDays_years_3_oe() {
        Period test = Period.years(1);
        try {
            test.toStandardDays();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(-1);
        try {
            test.toStandardDays();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(0);
        assertEquals(0,test.toStandardDays().getDays());
    }

    public void testToStandardDays_months_3_oe() {
        Period test = Period.months(1);
        try {
            test.toStandardDays();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(-1);
        try {
            test.toStandardDays();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(0);
        assertEquals(0,test.toStandardDays().getDays());
    }

    public void testToStandardHours_1_oe() {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8);
        assertEquals(5,test.toStandardHours().getHours());
    }

    public void testToStandardHours_2_oe() {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8);
        
        test = new Period(0, 0, 0, 1, 5, 0, 0, 0);
        assertEquals(29,test.toStandardHours().getHours());
    }

    public void testToStandardHours_3_oe() {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8);
        
        test = new Period(0, 0, 0, 1, 5, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, 59, 59, 1000);
        assertEquals(1,test.toStandardHours().getHours());
    }

    public void testToStandardHours_4_oe() {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8);
        
        test = new Period(0, 0, 0, 1, 5, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, 59, 59, 1000);
        
        test = new Period(0, 0, 0, 0, Integer.MAX_VALUE, 0, 0, 0);
        assertEquals(Integer.MAX_VALUE,test.toStandardHours().getHours());
    }

    public void testToStandardHours_5_oe() {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8);
        
        test = new Period(0, 0, 0, 1, 5, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, 59, 59, 1000);
        
        test = new Period(0, 0, 0, 0, Integer.MAX_VALUE, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        long intMax = Integer.MAX_VALUE;
        BigInteger expected = BigInteger.valueOf(intMax);
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_SECOND));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_MINUTE));
        expected = expected.divide(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_HOUR));
        assertTrue(expected.compareTo(BigInteger.valueOf(Long.MAX_VALUE))< 0);
    }

    public void testToStandardHours_6_oe() {
        Period test = new Period(0, 0, 0, 0, 5, 6, 7, 8);
        
        test = new Period(0, 0, 0, 1, 5, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, 59, 59, 1000);
        
        test = new Period(0, 0, 0, 0, Integer.MAX_VALUE, 0, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        long intMax = Integer.MAX_VALUE;
        BigInteger expected = BigInteger.valueOf(intMax);
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_SECOND));
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_MINUTE));
        expected = expected.divide(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_HOUR));
        assertEquals(expected.longValue(),test.toStandardHours().getHours());
    }

    public void testToStandardHours_years_3_oe() {
        Period test = Period.years(1);
        try {
            test.toStandardHours();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(-1);
        try {
            test.toStandardHours();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(0);
        assertEquals(0,test.toStandardHours().getHours());
    }

    public void testToStandardHours_months_3_oe() {
        Period test = Period.months(1);
        try {
            test.toStandardHours();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(-1);
        try {
            test.toStandardHours();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(0);
        assertEquals(0,test.toStandardHours().getHours());
    }

    public void testToStandardMinutes_1_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 6, 7, 8);
        assertEquals(6,test.toStandardMinutes().getMinutes());
    }

    public void testToStandardMinutes_2_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 6, 7, 8);
        
        test = new Period(0, 0, 0, 0, 1, 6, 0, 0);
        assertEquals(66,test.toStandardMinutes().getMinutes());
    }

    public void testToStandardMinutes_3_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 6, 7, 8);
        
        test = new Period(0, 0, 0, 0, 1, 6, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, 0, 59, 1000);
        assertEquals(1,test.toStandardMinutes().getMinutes());
    }

    public void testToStandardMinutes_4_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 6, 7, 8);
        
        test = new Period(0, 0, 0, 0, 1, 6, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, 0, 59, 1000);
        
        test = new Period(0, 0, 0, 0, 0, Integer.MAX_VALUE, 0, 0);
        assertEquals(Integer.MAX_VALUE,test.toStandardMinutes().getMinutes());
    }

    public void testToStandardMinutes_5_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 6, 7, 8);
        
        test = new Period(0, 0, 0, 0, 1, 6, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, 0, 59, 1000);
        
        test = new Period(0, 0, 0, 0, 0, Integer.MAX_VALUE, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        long intMax = Integer.MAX_VALUE;
        BigInteger expected = BigInteger.valueOf(intMax);
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_SECOND));
        expected = expected.divide(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_MINUTE));
        assertTrue(expected.compareTo(BigInteger.valueOf(Long.MAX_VALUE))< 0);
    }

    public void testToStandardMinutes_6_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 6, 7, 8);
        
        test = new Period(0, 0, 0, 0, 1, 6, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, 0, 59, 1000);
        
        test = new Period(0, 0, 0, 0, 0, Integer.MAX_VALUE, 0, 0);
        
        test = new Period(0, 0, 0, 0, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        long intMax = Integer.MAX_VALUE;
        BigInteger expected = BigInteger.valueOf(intMax);
        expected = expected.add(BigInteger.valueOf(intMax * DateTimeConstants.MILLIS_PER_SECOND));
        expected = expected.divide(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_MINUTE));
        assertEquals(expected.longValue(),test.toStandardMinutes().getMinutes());
    }

    public void testToStandardMinutes_years_3_oe() {
        Period test = Period.years(1);
        try {
            test.toStandardMinutes();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(-1);
        try {
            test.toStandardMinutes();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(0);
        assertEquals(0,test.toStandardMinutes().getMinutes());
    }

    public void testToStandardMinutes_months_3_oe() {
        Period test = Period.months(1);
        try {
            test.toStandardMinutes();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(-1);
        try {
            test.toStandardMinutes();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(0);
        assertEquals(0,test.toStandardMinutes().getMinutes());
    }

    public void testToStandardSeconds_1_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 0, 7, 8);
        assertEquals(7,test.toStandardSeconds().getSeconds());
    }

    public void testToStandardSeconds_2_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 0, 7, 8);
        
        test = new Period(0, 0, 0, 0, 0, 1, 3, 0);
        assertEquals(63,test.toStandardSeconds().getSeconds());
    }

    public void testToStandardSeconds_3_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 0, 7, 8);
        
        test = new Period(0, 0, 0, 0, 0, 1, 3, 0);
        
        test = new Period(0, 0, 0, 0, 0, 0, 0, 1000);
        assertEquals(1,test.toStandardSeconds().getSeconds());
    }

    public void testToStandardSeconds_4_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 0, 7, 8);
        
        test = new Period(0, 0, 0, 0, 0, 1, 3, 0);
        
        test = new Period(0, 0, 0, 0, 0, 0, 0, 1000);
        
        test = new Period(0, 0, 0, 0, 0, 0, Integer.MAX_VALUE, 0);
        assertEquals(Integer.MAX_VALUE,test.toStandardSeconds().getSeconds());
    }

    public void testToStandardSeconds_5_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 0, 7, 8);
        
        test = new Period(0, 0, 0, 0, 0, 1, 3, 0);
        
        test = new Period(0, 0, 0, 0, 0, 0, 0, 1000);
        
        test = new Period(0, 0, 0, 0, 0, 0, Integer.MAX_VALUE, 0);
        
        test = new Period(0, 0, 0, 0, 0, 0, 20, Integer.MAX_VALUE);
        long expected = 20;
        expected += ((long) Integer.MAX_VALUE) / DateTimeConstants.MILLIS_PER_SECOND;
        assertEquals(expected,test.toStandardSeconds().getSeconds());
    }

    public void testToStandardSeconds_years_3_oe() {
        Period test = Period.years(1);
        try {
            test.toStandardSeconds();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(-1);
        try {
            test.toStandardSeconds();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(0);
        assertEquals(0,test.toStandardSeconds().getSeconds());
    }

    public void testToStandardSeconds_months_3_oe() {
        Period test = Period.months(1);
        try {
            test.toStandardSeconds();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(-1);
        try {
            test.toStandardSeconds();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(0);
        assertEquals(0,test.toStandardSeconds().getSeconds());
    }

    public void testToStandardDuration_1_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 0, 0, 8);
        assertEquals(8,test.toStandardDuration().getMillis());
    }

    public void testToStandardDuration_2_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 0, 0, 8);
        
        test = new Period(0, 0, 0, 0, 0, 0, 1, 20);
        assertEquals(1020,test.toStandardDuration().getMillis());
    }

    public void testToStandardDuration_3_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 0, 0, 8);
        
        test = new Period(0, 0, 0, 0, 0, 0, 1, 20);
        
        test = new Period(0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE,test.toStandardDuration().getMillis());
    }

    public void testToStandardDuration_4_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 0, 0, 8);
        
        test = new Period(0, 0, 0, 0, 0, 0, 1, 20);
        
        test = new Period(0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE);
        
        test = new Period(0, 0, 0, 0, 0, 10, 20, Integer.MAX_VALUE);
        long expected = Integer.MAX_VALUE;
        expected += 10L * ((long) DateTimeConstants.MILLIS_PER_MINUTE);
        expected += 20L * ((long) DateTimeConstants.MILLIS_PER_SECOND);
        assertEquals(expected,test.toStandardDuration().getMillis());
    }

    public void testToStandardDuration_5_oe() {
        Period test = new Period(0, 0, 0, 0, 0, 0, 0, 8);
        
        test = new Period(0, 0, 0, 0, 0, 0, 1, 20);
        
        test = new Period(0, 0, 0, 0, 0, 0, 0, Integer.MAX_VALUE);
        
        test = new Period(0, 0, 0, 0, 0, 10, 20, Integer.MAX_VALUE);
        long expected = Integer.MAX_VALUE;
        expected += 10L * ((long) DateTimeConstants.MILLIS_PER_MINUTE);
        expected += 20L * ((long) DateTimeConstants.MILLIS_PER_SECOND);
        
        BigInteger intMax = BigInteger.valueOf(Integer.MAX_VALUE);
        BigInteger exp = intMax;
        exp = exp.add(intMax.multiply(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_SECOND)));
        exp = exp.add(intMax.multiply(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_MINUTE)));
        exp = exp.add(intMax.multiply(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_HOUR)));
        exp = exp.add(intMax.multiply(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_DAY)));
        exp = exp.add(intMax.multiply(BigInteger.valueOf(DateTimeConstants.MILLIS_PER_WEEK)));
        assertTrue(exp.compareTo(BigInteger.valueOf(Long.MAX_VALUE))< 0);
    }

    public void testToStandardDuration_years_3_oe() {
        Period test = Period.years(1);
        try {
            test.toStandardDuration();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(-1);
        try {
            test.toStandardDuration();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.years(0);
        assertEquals(0,test.toStandardDuration().getMillis());
    }

    public void testToStandardDuration_months_3_oe() {
        Period test = Period.months(1);
        try {
            test.toStandardDuration();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(-1);
        try {
            test.toStandardDuration();
        } catch (UnsupportedOperationException ex) {}
        
        test = Period.months(0);
        assertEquals(0,test.toStandardDuration().getMillis());
    }

    public void testNormalizedStandard_yearMonth1_1_oe() {
        Period test = new Period(1, 15, 0, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(1,15,0,0,0,0,0,0),test);
    }

    public void testNormalizedStandard_yearMonth1_2_oe() {
        Period test = new Period(1, 15, 0, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(2,3,0,0,0,0,0,0),result);
    }

    public void testNormalizedStandard_weekDay1_1_oe() {
        Period test = new Period(0, 0, 1, 12, 0, 0, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(0,0,1,12,0,0,0,0),test);
    }

    public void testNormalizedStandard_weekDay1_2_oe() {
        Period test = new Period(0, 0, 1, 12, 0, 0, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(0,0,2,5,0,0,0,0),result);
    }

    public void testNormalizedStandard_yearMonthWeekDay_1_oe() {
        Period test = new Period(1, 15, 1, 12, 0, 0, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(1,15,1,12,0,0,0,0),test);
    }

    public void testNormalizedStandard_yearMonthWeekDay_2_oe() {
        Period test = new Period(1, 15, 1, 12, 0, 0, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(2,3,2,5,0,0,0,0),result);
    }

    public void testNormalizedStandard_yearMonthDay_1_oe() {
        Period test = new Period(1, 15, 0, 36, 0, 0, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(1,15,0,36,0,0,0,0),test);
    }

    public void testNormalizedStandard_yearMonthDay_2_oe() {
        Period test = new Period(1, 15, 0, 36, 0, 0, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(2,3,5,1,0,0,0,0),result);
    }

    public void testNormalizedStandard_negative_1_oe() {
        Period test = new Period(0, 0, 0, 0, 2, -10, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(0,0,0,0,2,-10,0,0),test);
    }

    public void testNormalizedStandard_negative_2_oe() {
        Period test = new Period(0, 0, 0, 0, 2, -10, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(0,0,0,0,1,50,0,0),result);
    }

    public void testNormalizedStandard_fullNegative_1_oe() {
        Period test = new Period(0, 0, 0, 0, 1, -70, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(0,0,0,0,1,-70,0,0),test);
    }

    public void testNormalizedStandard_fullNegative_2_oe() {
        Period test = new Period(0, 0, 0, 0, 1, -70, 0, 0);
        Period result = test.normalizedStandard();
        assertEquals(new Period(0,0,0,0,0,-10,0,0),result);
    }

    public void testNormalizedStandard_periodType_yearMonth1_1_oe() {
        Period test = new Period(1, 15, 0, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard((PeriodType) null);
        assertEquals(new Period(1,15,0,0,0,0,0,0),test);
    }

    public void testNormalizedStandard_periodType_yearMonth1_2_oe() {
        Period test = new Period(1, 15, 0, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard((PeriodType) null);
        assertEquals(new Period(2,3,0,0,0,0,0,0),result);
    }

    public void testNormalizedStandard_periodType_weekDay1_1_oe() {
        Period test = new Period(0, 0, 1, 12, 0, 0, 0, 0);
        Period result = test.normalizedStandard((PeriodType) null);
        assertEquals(new Period(0,0,1,12,0,0,0,0),test);
    }

    public void testNormalizedStandard_periodType_weekDay1_2_oe() {
        Period test = new Period(0, 0, 1, 12, 0, 0, 0, 0);
        Period result = test.normalizedStandard((PeriodType) null);
        assertEquals(new Period(0,0,2,5,0,0,0,0),result);
    }

    public void testNormalizedStandard_periodType_weekDay3_1_oe() {
        Period test = new Period(0, 0, 1, 12, 0, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.dayTime());
        assertEquals(new Period(0,0,1,12,0,0,0,0),test);
    }

    public void testNormalizedStandard_periodType_weekDay3_2_oe() {
        Period test = new Period(0, 0, 1, 12, 0, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.dayTime());
        assertEquals(new Period(0,0,0,19,0,0,0,0,PeriodType.dayTime()),result);
    }

    public void testNormalizedStandard_periodType_yearMonthWeekDay_1_oe() {
        Period test = new Period(1, 15, 1, 12, 0, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.yearMonthDayTime());
        assertEquals(new Period(1,15,1,12,0,0,0,0),test);
    }

    public void testNormalizedStandard_periodType_yearMonthWeekDay_2_oe() {
        Period test = new Period(1, 15, 1, 12, 0, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.yearMonthDayTime());
        assertEquals(new Period(2,3,0,19,0,0,0,0,PeriodType.yearMonthDayTime()),result);
    }

    public void testNormalizedStandard_periodType_yearMonthDay_1_oe() {
        Period test = new Period(1, 15, 0, 36, 27, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.yearMonthDayTime());
        assertEquals(new Period(1,15,0,36,27,0,0,0),test);
    }

    public void testNormalizedStandard_periodType_yearMonthDay_2_oe() {
        Period test = new Period(1, 15, 0, 36, 27, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.yearMonthDayTime());
        assertEquals(new Period(2,3,0,37,3,0,0,0,PeriodType.yearMonthDayTime()),result);
    }

    public void testNormalizedStandard_periodType_months1_1_oe() {
        Period test = new Period(1, 15, 0, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.months());
        assertEquals(new Period(1,15,0,0,0,0,0,0),test);
    }

    public void testNormalizedStandard_periodType_months1_2_oe() {
        Period test = new Period(1, 15, 0, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.months());
        assertEquals(new Period(0,27,0,0,0,0,0,0,PeriodType.months()),result);
    }

    public void testNormalizedStandard_periodType_months2_1_oe() {
        Period test = new Period(-2, 15, 0, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.months());
        assertEquals(new Period(-2,15,0,0,0,0,0,0),test);
    }

    public void testNormalizedStandard_periodType_months2_2_oe() {
        Period test = new Period(-2, 15, 0, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.months());
        assertEquals(new Period(0,-9,0,0,0,0,0,0,PeriodType.months()),result);
    }

    public void testNormalizedStandard_periodType_months3_1_oe() {
        Period test = new Period(0, 4, 0, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.months());
        assertEquals(new Period(0,4,0,0,0,0,0,0),test);
    }

    public void testNormalizedStandard_periodType_months3_2_oe() {
        Period test = new Period(0, 4, 0, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard(PeriodType.months());
        assertEquals(new Period(0,4,0,0,0,0,0,0,PeriodType.months()),result);
    }

    public void testNormalizedStandard_periodType_monthsWeeks_1_oe() {
        PeriodType type = PeriodType.forFields(new DurationFieldType[]{
                        DurationFieldType.months(),
                        DurationFieldType.weeks(),
                        DurationFieldType.days()});
        Period test = new Period(2, 4, 6, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard(type);
        assertEquals(new Period(2,4,6,0,0,0,0,0),test);
    }

    public void testNormalizedStandard_periodType_monthsWeeks_2_oe() {
        PeriodType type = PeriodType.forFields(new DurationFieldType[]{
                        DurationFieldType.months(),
                        DurationFieldType.weeks(),
                        DurationFieldType.days()});
        Period test = new Period(2, 4, 6, 0, 0, 0, 0, 0);
        Period result = test.normalizedStandard(type);
        assertEquals(new Period(0,28,6,0,0,0,0,0,type),result);
    }

}
