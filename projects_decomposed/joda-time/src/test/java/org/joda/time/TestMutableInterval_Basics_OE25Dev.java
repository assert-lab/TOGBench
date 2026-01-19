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
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;

/**
 * This class is a Junit unit test for Instant.
 *
 * @author Stephen Colebourne
 */
public class TestMutableInterval_Basics_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
    
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
        return new TestSuite(TestMutableInterval_Basics_OE25Dev.class);
    }

    public TestMutableInterval_Basics_OE25Dev(String name) {
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

    public void testGetMillis_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(TEST_TIME1, test.getStartMillis());
    }

    public void testGetMillis_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        assertEquals(TEST_TIME1, test.getStart().getMillis());
    }

    public void testGetMillis_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME2, test.getEndMillis());
    }

    public void testGetMillis_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME2, test.getEnd().getMillis());
    }

    public void testGetMillis_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME2 - TEST_TIME1, test.toDurationMillis());
    }

    public void testGetMillis_6_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME2 - TEST_TIME1, test.toDuration().getMillis());
    }

    public void testGetDuration1_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(TEST_TIME2 - TEST_TIME1, test.toDurationMillis());
    }

    public void testGetDuration1_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        assertEquals(TEST_TIME2 - TEST_TIME1, test.toDuration().getMillis());
    }

    public void testGetDuration2_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME1);
        assertEquals(Duration.ZERO, test.toDuration());
    }

    public void testEqualsHashCode_1_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test1.equals(test2));
    }

    public void testEqualsHashCode_2_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.equals(test1));
    }

    public void testEqualsHashCode_3_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.equals(test1));
    }

    public void testEqualsHashCode_4_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.equals(test2));
    }

    public void testEqualsHashCode_5_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test1.hashCode());
    }

    public void testEqualsHashCode_7_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.hashCode() == test2.hashCode());
    }

    public void testEqualsHashCode_8_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        assertEquals(false, test1.equals(test3));
    }

    public void testEqualsHashCode_9_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        assertEquals(false, test2.equals(test3));
    }

    public void testEqualsHashCode_10_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test1));
    }

    public void testEqualsHashCode_11_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test2));
    }

    public void testEqualsHashCode_12_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test2.hashCode() == test3.hashCode());
    }

    public void testEqualsHashCode_14_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        assertEquals(true, test4.equals(test4));
    }

    public void testEqualsHashCode_15_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        assertEquals(false, test1.equals(test4));
    }

    public void testEqualsHashCode_16_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(false, test2.equals(test4));
    }

    public void testEqualsHashCode_17_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test4.equals(test1));
    }

    public void testEqualsHashCode_18_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test4.equals(test2));
    }

    public void testEqualsHashCode_19_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test1.hashCode() == test4.hashCode());
    }

    public void testEqualsHashCode_20_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test2.hashCode() == test4.hashCode());
    }

    public void testEqualsHashCode_21_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test1.equals(test5));
    }

    public void testEqualsHashCode_22_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        assertEquals(true, test2.equals(test5));
    }

    public void testEqualsHashCode_23_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.equals(test5));
    }

    public void testEqualsHashCode_24_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test5.equals(test1));
    }

    public void testEqualsHashCode_25_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test5.equals(test2));
    }

    public void testEqualsHashCode_26_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test5.equals(test3));
    }

    public void testEqualsHashCode_27_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test1.hashCode() == test5.hashCode());
    }

    public void testEqualsHashCode_28_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test2.hashCode() == test5.hashCode());
    }

    public void testEqualsHashCode_29_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test3.hashCode() == test5.hashCode());
    }

    public void testEqualsHashCode_30_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test1.equals("Hello"));
    }

    public void testEqualsHashCode_31_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
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
        assertEquals(true, test1.equals(new MockInterval()));
    }

    public void testEqualsHashCode_32_oe() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
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
        assertEquals(false, test1.equals(new DateTime(TEST_TIME1)));
    }

    public void testContains_long_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test.contains(TEST_TIME1));
    }

    public void testContains_long_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        assertEquals(false, test.contains(TEST_TIME1 - 1));
    }

    public void testContains_long_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.contains(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2));
    }

    public void testContains_long_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.contains(TEST_TIME2));
    }

    public void testContains_long_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.contains(TEST_TIME2 - 1));
    }

    public void testContainsNow_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1);
        assertEquals(true, test.containsNow());
    }

    public void testContainsNow_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 - 1);
        assertEquals(false, test.containsNow());
    }

    public void testContainsNow_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 - 1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2);
        assertEquals(true, test.containsNow());
    }

    public void testContainsNow_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 - 1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2);
        assertEquals(false, test.containsNow());
    }

    public void testContainsNow_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 - 1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2 - 1);
        assertEquals(true, test.containsNow());
    }

    public void testContains_RI_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test.contains(new Instant(TEST_TIME1)));
    }

    public void testContains_RI_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        assertEquals(false, test.contains(new Instant(TEST_TIME1 - 1)));
    }

    public void testContains_RI_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.contains(new Instant(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2)));
    }

    public void testContains_RI_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.contains(new Instant(TEST_TIME2)));
    }

    public void testContains_RI_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.contains(new Instant(TEST_TIME2 - 1)));
    }

    public void testContains_RI_6_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.contains((ReadableInstant) null));
    }

    public void testContains_RInterval_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertEquals(true, test.contains(new Interval(TEST_TIME1, TEST_TIME1)));
    }

    public void testContains_RInterval_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 1, TEST_TIME1)));
    }

    public void testContains_RInterval_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(true, test.contains(new Interval(TEST_TIME1, TEST_TIME1 + 1)));
    }

    public void testContains_RInterval_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 1, TEST_TIME1 + 1)));
    }

    public void testContains_RInterval_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.contains(new Interval(TEST_TIME1 + 1, TEST_TIME1 + 1)));
    }

    public void testContains_RInterval_6_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(true, test.contains(new Interval(TEST_TIME1, TEST_TIME2)));
    }

    public void testContains_RInterval_7_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 1, TEST_TIME2)));
    }

    public void testContains_RInterval_8_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.contains(new Interval(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2, TEST_TIME2)));
    }

    public void testContains_RInterval_9_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.contains(new Interval(TEST_TIME2, TEST_TIME2)));
    }

    public void testContains_RInterval_10_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.contains(new Interval(TEST_TIME2 - 1, TEST_TIME2)));
    }

    public void testContains_RInterval_11_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        
        assertEquals(true, test.contains(new Interval(TEST_TIME1, TEST_TIME2 - 1)));
    }

    public void testContains_RInterval_12_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 1, TEST_TIME2 - 1)));
    }

    public void testContains_RInterval_13_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(true, test.contains(new Interval(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2, TEST_TIME2 - 1)));
    }

    public void testContains_RInterval_14_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(true, test.contains(new Interval(TEST_TIME2 - 1, TEST_TIME2 - 1)));
    }

    public void testContains_RInterval_15_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(true, test.contains(new Interval(TEST_TIME2 - 2, TEST_TIME2 - 1)));
    }

    public void testContains_RInterval_16_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        
        assertEquals(false, test.contains(new Interval(TEST_TIME1, TEST_TIME2 + 1)));
    }

    public void testContains_RInterval_17_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 1, TEST_TIME2 + 1)));
    }

    public void testContains_RInterval_18_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(false, test.contains(new Interval(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2, TEST_TIME2 + 1)));
    }

    public void testContains_RInterval_19_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(false, test.contains(new Interval(TEST_TIME2, TEST_TIME2 + 1)));
    }

    public void testContains_RInterval_20_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(false, test.contains(new Interval(TEST_TIME2 - 1, TEST_TIME2 + 1)));
    }

    public void testContains_RInterval_21_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 2, TEST_TIME1 - 1)));
    }

    public void testContains_RInterval_22_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        // removed other assertion
        
        assertEquals(true, test.contains((ReadableInterval) null));
    }

    public void testOverlaps_RInterval_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertEquals(false, test.overlaps(new Interval(TEST_TIME1, TEST_TIME1)));
    }

    public void testOverlaps_RInterval_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        assertEquals(false, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME1)));
    }

    public void testOverlaps_RInterval_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1, TEST_TIME1 + 1)));
    }

    public void testOverlaps_RInterval_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME1 + 1)));
    }

    public void testOverlaps_RInterval_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 + 1, TEST_TIME1 + 1)));
    }

    public void testOverlaps_RInterval_6_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1, TEST_TIME2)));
    }

    public void testOverlaps_RInterval_7_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME2)));
    }

    public void testOverlaps_RInterval_8_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2, TEST_TIME2)));
    }

    public void testOverlaps_RInterval_9_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.overlaps(new Interval(TEST_TIME2, TEST_TIME2)));
    }

    public void testOverlaps_RInterval_10_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.overlaps(new Interval(TEST_TIME2 - 1, TEST_TIME2)));
    }

    public void testOverlaps_RInterval_11_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1, TEST_TIME2 + 1)));
    }

    public void testOverlaps_RInterval_12_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME2 + 1)));
    }

    public void testOverlaps_RInterval_13_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2, TEST_TIME2 + 1)));
    }

    public void testOverlaps_RInterval_14_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(false, test.overlaps(new Interval(TEST_TIME2, TEST_TIME2 + 1)));
    }

    public void testOverlaps_RInterval_15_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(true, test.overlaps(new Interval(TEST_TIME2 - 1, TEST_TIME2 + 1)));
    }

    public void testOverlaps_RInterval_16_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        
        assertEquals(false, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME1 - 1)));
    }

    public void testOverlaps_RInterval_17_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(false, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME1)));
    }

    public void testOverlaps_RInterval_18_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME1 + 1)));
    }

    public void testOverlaps_RInterval_19_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        
        assertEquals(true, test.overlaps((ReadableInterval) null));
    }

    public void testOverlaps_RInterval_20_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        
        MutableInterval empty = new MutableInterval(TEST_TIME1, TEST_TIME1);
        assertEquals(false, empty.overlaps(empty));
    }

    public void testOverlaps_RInterval_21_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        
        MutableInterval empty = new MutableInterval(TEST_TIME1, TEST_TIME1);
        // removed other assertion
        assertEquals(false, empty.overlaps(test));
    }

    public void testOverlaps_RInterval_22_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
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
        
        MutableInterval empty = new MutableInterval(TEST_TIME1, TEST_TIME1);
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.overlaps(empty));
    }

    public void testIsBefore_long_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertEquals(false, test.isBefore(TEST_TIME1 - 1));
    }

    public void testIsBefore_long_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        assertEquals(false, test.isBefore(TEST_TIME1));
    }

    public void testIsBefore_long_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isBefore(TEST_TIME1 + 1));
    }

    public void testIsBefore_long_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isBefore(TEST_TIME2 - 1));
    }

    public void testIsBefore_long_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test.isBefore(TEST_TIME2));
    }

    public void testIsBefore_long_6_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isBefore(TEST_TIME2 + 1));
    }

    public void testIsBeforeNow_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2 - 1);
        assertEquals(false, test.isBeforeNow());
    }

    public void testIsBeforeNow_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2 - 1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2);
        assertEquals(true, test.isBeforeNow());
    }

    public void testIsBeforeNow_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2 - 1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2 + 1);
        assertEquals(true, test.isBeforeNow());
    }

    public void testIsBefore_RI_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertEquals(false, test.isBefore(new Instant(TEST_TIME1 - 1)));
    }

    public void testIsBefore_RI_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        assertEquals(false, test.isBefore(new Instant(TEST_TIME1)));
    }

    public void testIsBefore_RI_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isBefore(new Instant(TEST_TIME1 + 1)));
    }

    public void testIsBefore_RI_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isBefore(new Instant(TEST_TIME2 - 1)));
    }

    public void testIsBefore_RI_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test.isBefore(new Instant(TEST_TIME2)));
    }

    public void testIsBefore_RI_6_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isBefore(new Instant(TEST_TIME2 + 1)));
    }

    public void testIsBefore_RI_7_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isBefore((ReadableInstant) null));
    }

    public void testIsBefore_RInterval_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertEquals(false, test.isBefore(new Interval(Long.MIN_VALUE, TEST_TIME1 - 1)));
    }

    public void testIsBefore_RInterval_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        assertEquals(false, test.isBefore(new Interval(Long.MIN_VALUE, TEST_TIME1)));
    }

    public void testIsBefore_RInterval_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isBefore(new Interval(Long.MIN_VALUE, TEST_TIME1 + 1)));
    }

    public void testIsBefore_RInterval_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isBefore(new Interval(TEST_TIME2 - 1, Long.MAX_VALUE)));
    }

    public void testIsBefore_RInterval_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true, test.isBefore(new Interval(TEST_TIME2, Long.MAX_VALUE)));
    }

    public void testIsBefore_RInterval_6_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.isBefore(new Interval(TEST_TIME2 + 1, Long.MAX_VALUE)));
    }

    public void testIsBefore_RInterval_7_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isBefore((ReadableInterval) null));
    }

    public void testIsAfter_long_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertEquals(true, test.isAfter(TEST_TIME1 - 1));
    }

    public void testIsAfter_long_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        assertEquals(false, test.isAfter(TEST_TIME1));
    }

    public void testIsAfter_long_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isAfter(TEST_TIME1 + 1));
    }

    public void testIsAfter_long_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isAfter(TEST_TIME2 - 1));
    }

    public void testIsAfter_long_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test.isAfter(TEST_TIME2));
    }

    public void testIsAfter_long_6_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isAfter(TEST_TIME2 + 1));
    }

    public void testIsAfterNow_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 - 1);
        assertEquals(true, test.isAfterNow());
    }

    public void testIsAfterNow_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 - 1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1);
        assertEquals(false, test.isAfterNow());
    }

    public void testIsAfterNow_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 - 1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1);
        // removed other assertion
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 + 1);
        assertEquals(false, test.isAfterNow());
    }

    public void testIsAfter_RI_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertEquals(true, test.isAfter(new Instant(TEST_TIME1 - 1)));
    }

    public void testIsAfter_RI_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        assertEquals(false, test.isAfter(new Instant(TEST_TIME1)));
    }

    public void testIsAfter_RI_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isAfter(new Instant(TEST_TIME1 + 1)));
    }

    public void testIsAfter_RI_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isAfter(new Instant(TEST_TIME2 - 1)));
    }

    public void testIsAfter_RI_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test.isAfter(new Instant(TEST_TIME2)));
    }

    public void testIsAfter_RI_6_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isAfter(new Instant(TEST_TIME2 + 1)));
    }

    public void testIsAfter_RI_7_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isAfter((ReadableInstant) null));
    }

    public void testIsAfter_RInterval_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertEquals(true, test.isAfter(new Interval(Long.MIN_VALUE, TEST_TIME1 - 1)));
    }

    public void testIsAfter_RInterval_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        assertEquals(true, test.isAfter(new Interval(Long.MIN_VALUE, TEST_TIME1)));
    }

    public void testIsAfter_RInterval_3_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isAfter(new Interval(Long.MIN_VALUE, TEST_TIME1 + 1)));
    }

    public void testIsAfter_RInterval_4_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isAfter(new Interval(TEST_TIME2 - 1, Long.MAX_VALUE)));
    }

    public void testIsAfter_RInterval_5_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(false, test.isAfter(new Interval(TEST_TIME2, Long.MAX_VALUE)));
    }

    public void testIsAfter_RInterval_6_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(false, test.isAfter(new Interval(TEST_TIME2 + 1, Long.MAX_VALUE)));
    }

    public void testIsAfter_RInterval_7_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false, test.isAfter((ReadableInterval) null));
    }

    public void testToInterval1_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval result = test.toInterval();
        assertEquals(test, result);
    }

    public void testToMutableInterval1_1_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        MutableInterval result = test.toMutableInterval();
        assertEquals(test, result);
    }

    public void testToMutableInterval1_2_oe() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        MutableInterval result = test.toMutableInterval();
        // removed other assertion
        assertNotSame(test, result);
    }

    public void testToPeriod_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, COPTIC_PARIS);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, COPTIC_PARIS);
        MutableInterval base = new MutableInterval(dt1, dt2);
        
        Period test = base.toPeriod();
        Period expected = new Period(dt1, dt2, PeriodType.standard());
        assertEquals(expected, test);
    }

    public void testToPeriod_PeriodType1_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, COPTIC_PARIS);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, COPTIC_PARIS);
        MutableInterval base = new MutableInterval(dt1, dt2);
        
        Period test = base.toPeriod(null);
        Period expected = new Period(dt1, dt2, PeriodType.standard());
        assertEquals(expected, test);
    }

    public void testToPeriod_PeriodType2_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18);
        MutableInterval base = new MutableInterval(dt1, dt2);
        
        Period test = base.toPeriod(PeriodType.yearWeekDayTime());
        Period expected = new Period(dt1, dt2, PeriodType.yearWeekDayTime());
        assertEquals(expected, test);
    }

    public void testSerialization_1_oe() throws Exception {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        MutableInterval result = (MutableInterval) ois.readObject();
        ois.close();
        
        assertEquals(test, result);
    }

    public void testToString_1_oe() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, DateTimeZone.UTC);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, DateTimeZone.UTC);
        MutableInterval test = new MutableInterval(dt1, dt2);
        assertEquals("2004-06-09T07:08:09.010Z/2005-08-13T12:14:16.018Z", test.toString());
    }

    public void testCopy_1_oe() {
        MutableInterval test = new MutableInterval(123L, 456L, COPTIC_PARIS);
        MutableInterval cloned = test.copy();
        assertEquals(test, cloned);
    }

    public void testCopy_2_oe() {
        MutableInterval test = new MutableInterval(123L, 456L, COPTIC_PARIS);
        MutableInterval cloned = test.copy();
        // removed other assertion
        assertNotSame(test, cloned);
    }

    public void testClone_1_oe() {
        MutableInterval test = new MutableInterval(123L, 456L, COPTIC_PARIS);
        MutableInterval cloned = (MutableInterval) test.clone();
        assertEquals(test, cloned);
    }

    public void testClone_2_oe() {
        MutableInterval test = new MutableInterval(123L, 456L, COPTIC_PARIS);
        MutableInterval cloned = (MutableInterval) test.clone();
        // removed other assertion
        assertNotSame(test, cloned);
    }

}
