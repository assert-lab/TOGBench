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
    public void testTest() {
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW).toString());
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1).toString());
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2).toString());
    }

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
    public void testForFields1() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        assertSame(PeriodType.years(),type);
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        assertSame(PeriodType.months(),type);
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        assertSame(PeriodType.weeks(),type);
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        assertSame(PeriodType.days(),type);
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.hours(),
        });
        assertSame(PeriodType.hours(),type);
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.minutes(),
        });
        assertSame(PeriodType.minutes(),type);
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.seconds(),
        });
        assertSame(PeriodType.seconds(),type);
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.millis(),
        });
        assertSame(PeriodType.millis(),type);
    }

    public void testForFields4() throws Exception {
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
    public void testForFields7() throws Exception {
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
        assertEquals(false,type.equals(type2));
        assertEquals(false,type.hashCode()== type2.hashCode());
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
    public void testMaskTwice1() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.standard().withHoursRemoved();
        type2 = type.withHoursRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.standard().withMinutesRemoved();
        type2 = type.withMinutesRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.standard().withSecondsRemoved();
        type2 = type.withSecondsRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.standard().withMillisRemoved();
        type2 = type.withMillisRemoved();
        assertEquals(true,type == type2);
    }

    //-----------------------------------------------------------------------
    public void testMaskTwice2() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.millis();
        type2 = type.withDaysRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.millis();
        type2 = type.withHoursRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.millis();
        type2 = type.withMinutesRemoved();
        assertEquals(true,type == type2);
        
        type = PeriodType.millis();
        type2 = type.withSecondsRemoved();
        assertEquals(true,type == type2);
    }

    //-----------------------------------------------------------------------
    public void testEquals() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(true,type.equals(type));
        assertEquals(true,type.equals(PeriodType.dayTime().withMillisRemoved()));
        assertEquals(false,type.equals(null));
        assertEquals(false,type.equals(""));
    }

    public void testHashCode() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(type.hashCode(),type.hashCode());
    }

    //-----------------------------------------------------------------------
    public void testIsSupported() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(false,type.isSupported(DurationFieldType.years()));
        assertEquals(false,type.isSupported(DurationFieldType.months()));
        assertEquals(false,type.isSupported(DurationFieldType.weeks()));
        assertEquals(true,type.isSupported(DurationFieldType.days()));
        assertEquals(true,type.isSupported(DurationFieldType.hours()));
        assertEquals(true,type.isSupported(DurationFieldType.minutes()));
        assertEquals(true,type.isSupported(DurationFieldType.seconds()));
        assertEquals(false,type.isSupported(DurationFieldType.millis()));
    }

    //-----------------------------------------------------------------------
    public void testIndexOf() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(-1,type.indexOf(DurationFieldType.years()));
        assertEquals(-1,type.indexOf(DurationFieldType.months()));
        assertEquals(-1,type.indexOf(DurationFieldType.weeks()));
        assertEquals(0,type.indexOf(DurationFieldType.days()));
        assertEquals(1,type.indexOf(DurationFieldType.hours()));
        assertEquals(2,type.indexOf(DurationFieldType.minutes()));
        assertEquals(3,type.indexOf(DurationFieldType.seconds()));
        assertEquals(-1,type.indexOf(DurationFieldType.millis()));
    }

    public void testStandard_18_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testYearMonthDayTime_17_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testYearMonthDay_13_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testYearWeekDayTime_17_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testYearWeekDay_13_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testYearDayTime_16_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testYearDay_12_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testDayTime_15_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testTime_14_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testYears_11_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMonths_11_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testWeeks_11_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testDays_11_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testHours_11_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMinutes_11_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testSeconds_11_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMillis_11_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testForFields2_12_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testForFields3_12_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMaskYears_17_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMaskMonths_17_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMaskWeeks_17_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMaskDays_17_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMaskHours_17_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMaskMinutes_17_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMaskSeconds_17_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMaskMillis_17_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

    public void testMaskHoursMinutesSeconds_15_oe_1_oe() throws Exception {
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
        // removed other assertion
                final PeriodType type1 = type;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(type1);
                oos.close();
                byte[] bytes = baos.toByteArray();
        
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                PeriodType result = (PeriodType) ois.readObject();
                ois.close();
        
                assertEquals(type1,result);
    }

}
