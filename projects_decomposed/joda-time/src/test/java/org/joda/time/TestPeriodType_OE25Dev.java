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
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This class is a JUnit test for PeriodType.
 *
 * @author Stephen Colebourne
 */
public class TestPeriodType_OE25Dev extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
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
        return new TestSuite(TestPeriodType_OE25Dev.class);
    }

    public TestPeriodType_OE25Dev(String name) {
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
    private void assertEqualsAfterSerialization(PeriodType type) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(type);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        PeriodType result = (PeriodType) ois.readObject();
        ois.close();
        
        assertEquals(type,result);
    }

    private void assertSameAfterSerialization(PeriodType type) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(type);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        PeriodType result = (PeriodType) ois.readObject();
        ois.close();
        
        assertEquals(type,result);
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

    public void testForFields5() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.centuries(),
            DurationFieldType.months(),
        };
        try {
            PeriodType.forFields(types);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            PeriodType.forFields(types);  // repeated for test coverage of cache
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    public void testForFields6() throws Exception {
        DurationFieldType[] types = null;
        try {
            PeriodType.forFields(types);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        
        types = new DurationFieldType[0];
        try {
            PeriodType.forFields(types);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        
        types = new DurationFieldType[] {
            null,
            DurationFieldType.months(),
        };
        try {
            PeriodType.forFields(types);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
        
        types = new DurationFieldType[] {
            DurationFieldType.months(),
            null,
        };
        try {
            PeriodType.forFields(types);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    // ensure hash key distribution

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
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW).toString());
    }

public void testTest_2_oe() {
        // removed other assertion
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1).toString());
    }

public void testTest_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2).toString());
    }

public void testStandard_1_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(8,type.size());
    }

public void testStandard_2_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testStandard_3_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

public void testStandard_4_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

public void testStandard_5_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

public void testStandard_6_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
    }

public void testStandard_7_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(5));
    }

public void testStandard_8_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(6));
    }

public void testStandard_9_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(7));
    }

public void testStandard_10_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Standard",type.getName());
    }

public void testStandard_11_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[Standard]",type.toString());
    }

public void testStandard_12_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testStandard_13_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.standard());
    }

public void testStandard_14_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testStandard_15_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testStandard_16_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.standard().hashCode());
    }

public void testStandard_17_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testYearMonthDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(7,type.size());
    }

public void testYearMonthDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testYearMonthDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

public void testYearMonthDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

public void testYearMonthDayTime_5_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

public void testYearMonthDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

public void testYearMonthDayTime_7_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

public void testYearMonthDayTime_8_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

public void testYearMonthDayTime_9_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YearMonthDayTime",type.getName());
    }

public void testYearMonthDayTime_10_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[YearMonthDayTime]",type.toString());
    }

public void testYearMonthDayTime_11_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testYearMonthDayTime_12_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.yearMonthDayTime());
    }

public void testYearMonthDayTime_13_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testYearMonthDayTime_14_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testYearMonthDayTime_15_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.yearMonthDayTime().hashCode());
    }

public void testYearMonthDayTime_16_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testYearMonthDay_1_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(3,type.size());
    }

public void testYearMonthDay_2_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testYearMonthDay_3_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

public void testYearMonthDay_4_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

public void testYearMonthDay_5_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YearMonthDay",type.getName());
    }

public void testYearMonthDay_6_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[YearMonthDay]",type.toString());
    }

public void testYearMonthDay_7_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testYearMonthDay_8_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.yearMonthDay());
    }

public void testYearMonthDay_9_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testYearMonthDay_10_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testYearMonthDay_11_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.yearMonthDay().hashCode());
    }

public void testYearMonthDay_12_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testYearWeekDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(7,type.size());
    }

public void testYearWeekDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testYearWeekDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
    }

public void testYearWeekDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

public void testYearWeekDayTime_5_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

public void testYearWeekDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

public void testYearWeekDayTime_7_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

public void testYearWeekDayTime_8_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

public void testYearWeekDayTime_9_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YearWeekDayTime",type.getName());
    }

public void testYearWeekDayTime_10_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[YearWeekDayTime]",type.toString());
    }

public void testYearWeekDayTime_11_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testYearWeekDayTime_12_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.yearWeekDayTime());
    }

public void testYearWeekDayTime_13_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testYearWeekDayTime_14_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testYearWeekDayTime_15_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.yearWeekDayTime().hashCode());
    }

public void testYearWeekDayTime_16_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testYearWeekDay_1_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(3,type.size());
    }

public void testYearWeekDay_2_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testYearWeekDay_3_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
    }

public void testYearWeekDay_4_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

public void testYearWeekDay_5_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YearWeekDay",type.getName());
    }

public void testYearWeekDay_6_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[YearWeekDay]",type.toString());
    }

public void testYearWeekDay_7_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testYearWeekDay_8_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.yearWeekDay());
    }

public void testYearWeekDay_9_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testYearWeekDay_10_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testYearWeekDay_11_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.yearWeekDay().hashCode());
    }

public void testYearWeekDay_12_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testYearDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(6,type.size());
    }

public void testYearDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testYearDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(1));
    }

public void testYearDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(2));
    }

public void testYearDayTime_5_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(3));
    }

public void testYearDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(4));
    }

public void testYearDayTime_7_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(5));
    }

public void testYearDayTime_8_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YearDayTime",type.getName());
    }

public void testYearDayTime_9_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[YearDayTime]",type.toString());
    }

public void testYearDayTime_10_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testYearDayTime_11_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.yearDayTime());
    }

public void testYearDayTime_12_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testYearDayTime_13_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testYearDayTime_14_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.yearDayTime().hashCode());
    }

public void testYearDayTime_15_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testYearDay_1_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(2,type.size());
    }

public void testYearDay_2_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testYearDay_3_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(1));
    }

public void testYearDay_4_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YearDay",type.getName());
    }

public void testYearDay_5_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[YearDay]",type.toString());
    }

public void testYearDay_6_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testYearDay_7_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.yearDay());
    }

public void testYearDay_8_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testYearDay_9_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testYearDay_10_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.yearDay().hashCode());
    }

public void testYearDay_11_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(5,type.size());
    }

public void testDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(0));
    }

public void testDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(1));
    }

public void testDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(2));
    }

public void testDayTime_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(3));
    }

public void testDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(4));
    }

public void testDayTime_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DayTime",type.getName());
    }

public void testDayTime_8_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[DayTime]",type.toString());
    }

public void testDayTime_9_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testDayTime_10_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.dayTime());
    }

public void testDayTime_11_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testDayTime_12_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testDayTime_13_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.dayTime().hashCode());
    }

public void testDayTime_14_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testTime_1_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(4,type.size());
    }

public void testTime_2_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(0));
    }

public void testTime_3_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(1));
    }

public void testTime_4_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(2));
    }

public void testTime_5_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(3));
    }

public void testTime_6_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Time",type.getName());
    }

public void testTime_7_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[Time]",type.toString());
    }

public void testTime_8_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testTime_9_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.time());
    }

public void testTime_10_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testTime_11_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testTime_12_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.time().hashCode());
    }

public void testTime_13_oe() throws Exception {
        PeriodType type = PeriodType.time();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testYears_1_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(1,type.size());
    }

public void testYears_2_oe() throws Exception {
        PeriodType type = PeriodType.years();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testYears_3_oe() throws Exception {
        PeriodType type = PeriodType.years();
        // removed other assertion
        // removed other assertion
        assertEquals("Years",type.getName());
    }

public void testYears_4_oe() throws Exception {
        PeriodType type = PeriodType.years();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[Years]",type.toString());
    }

public void testYears_5_oe() throws Exception {
        PeriodType type = PeriodType.years();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testYears_6_oe() throws Exception {
        PeriodType type = PeriodType.years();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.years());
    }

public void testYears_7_oe() throws Exception {
        PeriodType type = PeriodType.years();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.standard()));
    }

public void testYears_8_oe() throws Exception {
        PeriodType type = PeriodType.years();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testYears_9_oe() throws Exception {
        PeriodType type = PeriodType.years();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.years().hashCode());
    }

public void testYears_10_oe() throws Exception {
        PeriodType type = PeriodType.years();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

public void testMonths_1_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(1,type.size());
    }

public void testMonths_2_oe() throws Exception {
        PeriodType type = PeriodType.months();
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(0));
    }

public void testMonths_3_oe() throws Exception {
        PeriodType type = PeriodType.months();
        // removed other assertion
        // removed other assertion
        assertEquals("Months",type.getName());
    }

public void testMonths_4_oe() throws Exception {
        PeriodType type = PeriodType.months();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[Months]",type.toString());
    }

public void testMonths_5_oe() throws Exception {
        PeriodType type = PeriodType.months();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMonths_6_oe() throws Exception {
        PeriodType type = PeriodType.months();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.months());
    }

public void testMonths_7_oe() throws Exception {
        PeriodType type = PeriodType.months();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.standard()));
    }

public void testMonths_8_oe() throws Exception {
        PeriodType type = PeriodType.months();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMonths_9_oe() throws Exception {
        PeriodType type = PeriodType.months();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.months().hashCode());
    }

public void testMonths_10_oe() throws Exception {
        PeriodType type = PeriodType.months();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

public void testWeeks_1_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(1,type.size());
    }

public void testWeeks_2_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(0));
    }

public void testWeeks_3_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        // removed other assertion
        // removed other assertion
        assertEquals("Weeks",type.getName());
    }

public void testWeeks_4_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[Weeks]",type.toString());
    }

public void testWeeks_5_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testWeeks_6_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.weeks());
    }

public void testWeeks_7_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.standard()));
    }

public void testWeeks_8_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testWeeks_9_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.weeks().hashCode());
    }

public void testWeeks_10_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

public void testDays_1_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(1,type.size());
    }

public void testDays_2_oe() throws Exception {
        PeriodType type = PeriodType.days();
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(0));
    }

public void testDays_3_oe() throws Exception {
        PeriodType type = PeriodType.days();
        // removed other assertion
        // removed other assertion
        assertEquals("Days",type.getName());
    }

public void testDays_4_oe() throws Exception {
        PeriodType type = PeriodType.days();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[Days]",type.toString());
    }

public void testDays_5_oe() throws Exception {
        PeriodType type = PeriodType.days();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testDays_6_oe() throws Exception {
        PeriodType type = PeriodType.days();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.days());
    }

public void testDays_7_oe() throws Exception {
        PeriodType type = PeriodType.days();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.standard()));
    }

public void testDays_8_oe() throws Exception {
        PeriodType type = PeriodType.days();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testDays_9_oe() throws Exception {
        PeriodType type = PeriodType.days();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.days().hashCode());
    }

public void testDays_10_oe() throws Exception {
        PeriodType type = PeriodType.days();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

public void testHours_1_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(1,type.size());
    }

public void testHours_2_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(0));
    }

public void testHours_3_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        // removed other assertion
        // removed other assertion
        assertEquals("Hours",type.getName());
    }

public void testHours_4_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[Hours]",type.toString());
    }

public void testHours_5_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testHours_6_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.hours());
    }

public void testHours_7_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.standard()));
    }

public void testHours_8_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testHours_9_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.hours().hashCode());
    }

public void testHours_10_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

public void testMinutes_1_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals(1,type.size());
    }

public void testMinutes_2_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(0));
    }

public void testMinutes_3_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        // removed other assertion
        // removed other assertion
        assertEquals("Minutes",type.getName());
    }

public void testMinutes_4_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[Minutes]",type.toString());
    }

public void testMinutes_5_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMinutes_6_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.minutes());
    }

public void testMinutes_7_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.standard()));
    }

public void testMinutes_8_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMinutes_9_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.minutes().hashCode());
    }

public void testMinutes_10_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

public void testSeconds_1_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(1,type.size());
    }

public void testSeconds_2_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(0));
    }

public void testSeconds_3_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        // removed other assertion
        // removed other assertion
        assertEquals("Seconds",type.getName());
    }

public void testSeconds_4_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[Seconds]",type.toString());
    }

public void testSeconds_5_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testSeconds_6_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.seconds());
    }

public void testSeconds_7_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.standard()));
    }

public void testSeconds_8_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testSeconds_9_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.seconds().hashCode());
    }

public void testSeconds_10_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

public void testMillis_1_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals(1,type.size());
    }

public void testMillis_2_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(0));
    }

public void testMillis_3_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        // removed other assertion
        // removed other assertion
        assertEquals("Millis",type.getName());
    }

public void testMillis_4_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[Millis]",type.toString());
    }

public void testMillis_5_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMillis_6_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.millis());
    }

public void testMillis_7_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.standard()));
    }

public void testMillis_8_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMillis_9_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testMillis_10_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

public void testForFields1_1_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        assertSame(PeriodType.years(),type);
    }

public void testForFields1_2_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        assertSame(PeriodType.months(),type);
    }

public void testForFields1_3_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        assertSame(PeriodType.weeks(),type);
    }

public void testForFields1_4_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        assertSame(PeriodType.days(),type);
    }

public void testForFields1_5_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.hours(),
        });
        assertSame(PeriodType.hours(),type);
    }

public void testForFields1_6_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.hours(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.minutes(),
        });
        assertSame(PeriodType.minutes(),type);
    }

public void testForFields1_7_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.hours(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.minutes(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.seconds(),
        });
        assertSame(PeriodType.seconds(),type);
    }

public void testForFields1_8_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.hours(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.minutes(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.seconds(),
        });
        // removed other assertion
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.millis(),
        });
        assertSame(PeriodType.millis(),type);
    }

public void testForFields2_1_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(2,type.size());
    }

public void testForFields2_2_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testForFields2_3_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(1));
    }

public void testForFields2_4_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoMonthsNoWeeksNoDaysNoMinutesNoSecondsNoMillis",type.getName());
    }

public void testForFields2_5_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoMonthsNoWeeksNoDaysNoMinutesNoSecondsNoMillis]",type.toString());
    }

public void testForFields2_6_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testForFields2_7_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.forFields(types));
    }

public void testForFields2_8_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testForFields2_9_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testForFields2_10_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.forFields(types).hashCode());
    }

public void testForFields2_11_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testForFields3_1_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(2,type.size());
    }

public void testForFields3_2_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(0));
    }

public void testForFields3_3_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
    }

public void testForFields3_4_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoYearsNoDaysNoHoursNoMinutesNoSecondsNoMillis",type.getName());
    }

public void testForFields3_5_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoYearsNoDaysNoHoursNoMinutesNoSecondsNoMillis]",type.toString());
    }

public void testForFields3_6_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testForFields3_7_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type == PeriodType.forFields(types));
    }

public void testForFields3_8_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testForFields3_9_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testForFields3_10_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.forFields(types).hashCode());
    }

public void testForFields3_11_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testForFields4_1_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.weeks(),
            DurationFieldType.days(),  // adding this makes this test unique, so cache is not pre-populated
            DurationFieldType.months(),
        };
        DurationFieldType[] types2 = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.days(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        PeriodType type2 = PeriodType.forFields(types2);
        assertEquals(true,type == type2);
    }

public void testForFields7_1_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.weeks(),
            DurationFieldType.months(),
        };
        DurationFieldType[] types2 = new DurationFieldType[] {
            DurationFieldType.seconds(),
        };
        PeriodType type = PeriodType.forFields(types);
        PeriodType type2 = PeriodType.forFields(types2);
        assertEquals(false,type == type2);
    }

public void testForFields7_2_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.weeks(),
            DurationFieldType.months(),
        };
        DurationFieldType[] types2 = new DurationFieldType[] {
            DurationFieldType.seconds(),
        };
        PeriodType type = PeriodType.forFields(types);
        PeriodType type2 = PeriodType.forFields(types2);
        // removed other assertion
        assertEquals(false,type.equals(type2));
    }

public void testForFields7_3_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.weeks(),
            DurationFieldType.months(),
        };
        DurationFieldType[] types2 = new DurationFieldType[] {
            DurationFieldType.seconds(),
        };
        PeriodType type = PeriodType.forFields(types);
        PeriodType type2 = PeriodType.forFields(types2);
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== type2.hashCode());
    }

public void testMaskYears_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(7,type.size());
    }

public void testMaskYears_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(0));
    }

public void testMaskYears_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
    }

public void testMaskYears_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

public void testMaskYears_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

public void testMaskYears_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

public void testMaskYears_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

public void testMaskYears_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

public void testMaskYears_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMaskYears_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(PeriodType.standard().withYearsRemoved()));
    }

public void testMaskYears_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testMaskYears_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMaskYears_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.standard().withYearsRemoved().hashCode());
    }

public void testMaskYears_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testMaskYears_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoYears",type.getName());
    }

public void testMaskYears_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoYears]",type.toString());
    }

public void testMaskMonths_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(7,type.size());
    }

public void testMaskMonths_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testMaskMonths_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
    }

public void testMaskMonths_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

public void testMaskMonths_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

public void testMaskMonths_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

public void testMaskMonths_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

public void testMaskMonths_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

public void testMaskMonths_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMaskMonths_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(PeriodType.standard().withMonthsRemoved()));
    }

public void testMaskMonths_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testMaskMonths_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMaskMonths_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.standard().withMonthsRemoved().hashCode());
    }

public void testMaskMonths_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testMaskMonths_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoMonths",type.getName());
    }

public void testMaskMonths_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoMonths]",type.toString());
    }

public void testMaskWeeks_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(7,type.size());
    }

public void testMaskWeeks_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testMaskWeeks_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

public void testMaskWeeks_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

public void testMaskWeeks_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

public void testMaskWeeks_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

public void testMaskWeeks_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

public void testMaskWeeks_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

public void testMaskWeeks_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMaskWeeks_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(PeriodType.standard().withWeeksRemoved()));
    }

public void testMaskWeeks_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testMaskWeeks_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMaskWeeks_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.standard().withWeeksRemoved().hashCode());
    }

public void testMaskWeeks_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testMaskWeeks_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoWeeks",type.getName());
    }

public void testMaskWeeks_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoWeeks]",type.toString());
    }

public void testMaskDays_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(7,type.size());
    }

public void testMaskDays_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testMaskDays_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

public void testMaskDays_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

public void testMaskDays_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

public void testMaskDays_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

public void testMaskDays_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

public void testMaskDays_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

public void testMaskDays_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMaskDays_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(PeriodType.standard().withDaysRemoved()));
    }

public void testMaskDays_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testMaskDays_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMaskDays_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.standard().withDaysRemoved().hashCode());
    }

public void testMaskDays_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testMaskDays_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoDays",type.getName());
    }

public void testMaskDays_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoDays]",type.toString());
    }

public void testMaskHours_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(7,type.size());
    }

public void testMaskHours_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testMaskHours_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

public void testMaskHours_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

public void testMaskHours_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

public void testMaskHours_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

public void testMaskHours_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

public void testMaskHours_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

public void testMaskHours_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMaskHours_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(PeriodType.standard().withHoursRemoved()));
    }

public void testMaskHours_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testMaskHours_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMaskHours_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.standard().withHoursRemoved().hashCode());
    }

public void testMaskHours_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testMaskHours_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoHours",type.getName());
    }

public void testMaskHours_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoHours]",type.toString());
    }

public void testMaskMinutes_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(7,type.size());
    }

public void testMaskMinutes_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testMaskMinutes_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

public void testMaskMinutes_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

public void testMaskMinutes_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

public void testMaskMinutes_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
    }

public void testMaskMinutes_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

public void testMaskMinutes_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

public void testMaskMinutes_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMaskMinutes_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(PeriodType.standard().withMinutesRemoved()));
    }

public void testMaskMinutes_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testMaskMinutes_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMaskMinutes_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.standard().withMinutesRemoved().hashCode());
    }

public void testMaskMinutes_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testMaskMinutes_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoMinutes",type.getName());
    }

public void testMaskMinutes_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoMinutes]",type.toString());
    }

public void testMaskSeconds_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(7,type.size());
    }

public void testMaskSeconds_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testMaskSeconds_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

public void testMaskSeconds_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

public void testMaskSeconds_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

public void testMaskSeconds_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
    }

public void testMaskSeconds_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(5));
    }

public void testMaskSeconds_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

public void testMaskSeconds_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMaskSeconds_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(PeriodType.standard().withSecondsRemoved()));
    }

public void testMaskSeconds_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testMaskSeconds_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMaskSeconds_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.standard().withSecondsRemoved().hashCode());
    }

public void testMaskSeconds_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testMaskSeconds_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoSeconds",type.getName());
    }

public void testMaskSeconds_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoSeconds]",type.toString());
    }

public void testMaskMillis_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(7,type.size());
    }

public void testMaskMillis_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testMaskMillis_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

public void testMaskMillis_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

public void testMaskMillis_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

public void testMaskMillis_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
    }

public void testMaskMillis_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.minutes(),type.getFieldType(5));
    }

public void testMaskMillis_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.seconds(),type.getFieldType(6));
    }

public void testMaskMillis_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMaskMillis_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(PeriodType.standard().withMillisRemoved()));
    }

public void testMaskMillis_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testMaskMillis_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMaskMillis_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.standard().withMillisRemoved().hashCode());
    }

public void testMaskMillis_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testMaskMillis_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoMillis",type.getName());
    }

public void testMaskMillis_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoMillis]",type.toString());
    }

public void testMaskHoursMinutesSeconds_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(5,type.size());
    }

public void testMaskHoursMinutesSeconds_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

public void testMaskHoursMinutesSeconds_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

public void testMaskHoursMinutesSeconds_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

public void testMaskHoursMinutesSeconds_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

public void testMaskHoursMinutesSeconds_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DurationFieldType.millis(),type.getFieldType(4));
    }

public void testMaskHoursMinutesSeconds_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(type));
    }

public void testMaskHoursMinutesSeconds_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.equals(PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved()));
    }

public void testMaskHoursMinutesSeconds_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(PeriodType.millis()));
    }

public void testMaskHoursMinutesSeconds_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== type.hashCode());
    }

public void testMaskHoursMinutesSeconds_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.hashCode()== PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved().hashCode());
    }

public void testMaskHoursMinutesSeconds_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

public void testMaskHoursMinutesSeconds_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("StandardNoHoursNoMinutesNoSeconds",type.getName());
    }

public void testMaskHoursMinutesSeconds_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("PeriodType[StandardNoHoursNoMinutesNoSeconds]",type.toString());
    }

public void testMaskTwice1_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice1_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice1_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice1_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice1_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withHoursRemoved();
        type2 = type.withHoursRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice1_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withHoursRemoved();
        type2 = type.withHoursRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMinutesRemoved();
        type2 = type.withMinutesRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice1_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withHoursRemoved();
        type2 = type.withHoursRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMinutesRemoved();
        type2 = type.withMinutesRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withSecondsRemoved();
        type2 = type.withSecondsRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice1_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withHoursRemoved();
        type2 = type.withHoursRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMinutesRemoved();
        type2 = type.withMinutesRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withSecondsRemoved();
        type2 = type.withSecondsRemoved();
        // removed other assertion
        
        type = PeriodType.standard().withMillisRemoved();
        type2 = type.withMillisRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice2_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice2_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice2_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice2_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        // removed other assertion
        
        type = PeriodType.millis();
        type2 = type.withDaysRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice2_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        // removed other assertion
        
        type = PeriodType.millis();
        type2 = type.withDaysRemoved();
        // removed other assertion
        
        type = PeriodType.millis();
        type2 = type.withHoursRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice2_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        // removed other assertion
        
        type = PeriodType.millis();
        type2 = type.withDaysRemoved();
        // removed other assertion
        
        type = PeriodType.millis();
        type2 = type.withHoursRemoved();
        // removed other assertion
        
        type = PeriodType.millis();
        type2 = type.withMinutesRemoved();
        assertEquals(true,type == type2);
    }

public void testMaskTwice2_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        // removed other assertion
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        // removed other assertion
        
        type = PeriodType.millis();
        type2 = type.withDaysRemoved();
        // removed other assertion
        
        type = PeriodType.millis();
        type2 = type.withHoursRemoved();
        // removed other assertion
        
        type = PeriodType.millis();
        type2 = type.withMinutesRemoved();
        // removed other assertion
        
        type = PeriodType.millis();
        type2 = type.withSecondsRemoved();
        assertEquals(true,type == type2);
    }

public void testEquals_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(true,type.equals(type));
    }

public void testEquals_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        assertEquals(true,type.equals(PeriodType.dayTime().withMillisRemoved()));
    }

public void testEquals_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(null));
    }

public void testEquals_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.equals(""));
    }

public void testHashCode_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(type.hashCode(),type.hashCode());
    }

public void testIsSupported_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(false,type.isSupported(DurationFieldType.years()));
    }

public void testIsSupported_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        assertEquals(false,type.isSupported(DurationFieldType.months()));
    }

public void testIsSupported_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.isSupported(DurationFieldType.weeks()));
    }

public void testIsSupported_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.isSupported(DurationFieldType.days()));
    }

public void testIsSupported_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.isSupported(DurationFieldType.hours()));
    }

public void testIsSupported_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.isSupported(DurationFieldType.minutes()));
    }

public void testIsSupported_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,type.isSupported(DurationFieldType.seconds()));
    }

public void testIsSupported_8_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,type.isSupported(DurationFieldType.millis()));
    }

public void testIndexOf_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(-1,type.indexOf(DurationFieldType.years()));
    }

public void testIndexOf_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        assertEquals(-1,type.indexOf(DurationFieldType.months()));
    }

public void testIndexOf_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        assertEquals(-1,type.indexOf(DurationFieldType.weeks()));
    }

public void testIndexOf_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,type.indexOf(DurationFieldType.days()));
    }

public void testIndexOf_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,type.indexOf(DurationFieldType.hours()));
    }

public void testIndexOf_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,type.indexOf(DurationFieldType.minutes()));
    }

public void testIndexOf_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,type.indexOf(DurationFieldType.seconds()));
    }

public void testIndexOf_8_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1,type.indexOf(DurationFieldType.millis()));
    }

}
