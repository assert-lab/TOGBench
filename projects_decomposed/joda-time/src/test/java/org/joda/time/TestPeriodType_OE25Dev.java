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
    public void testStandard() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(8,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(5));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(6));
        assertEquals(DurationFieldType.millis(),type.getFieldType(7));
        assertEquals("Standard",type.getName());
        assertEquals("PeriodType[Standard]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.standard());
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.standard().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testYearMonthDayTime() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(7,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
        assertEquals("YearMonthDayTime",type.getName());
        assertEquals("PeriodType[YearMonthDayTime]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.yearMonthDayTime());
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.yearMonthDayTime().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testYearMonthDay() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(3,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
        assertEquals("YearMonthDay",type.getName());
        assertEquals("PeriodType[YearMonthDay]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.yearMonthDay());
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.yearMonthDay().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testYearWeekDayTime() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(7,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
        assertEquals("YearWeekDayTime",type.getName());
        assertEquals("PeriodType[YearWeekDayTime]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.yearWeekDayTime());
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.yearWeekDayTime().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testYearWeekDay() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(3,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
        assertEquals("YearWeekDay",type.getName());
        assertEquals("PeriodType[YearWeekDay]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.yearWeekDay());
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.yearWeekDay().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testYearDayTime() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(6,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.days(),type.getFieldType(1));
        assertEquals(DurationFieldType.hours(),type.getFieldType(2));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(3));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(4));
        assertEquals(DurationFieldType.millis(),type.getFieldType(5));
        assertEquals("YearDayTime",type.getName());
        assertEquals("PeriodType[YearDayTime]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.yearDayTime());
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.yearDayTime().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testYearDay() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(2,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.days(),type.getFieldType(1));
        assertEquals("YearDay",type.getName());
        assertEquals("PeriodType[YearDay]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.yearDay());
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.yearDay().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testDayTime() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(5,type.size());
        assertEquals(DurationFieldType.days(),type.getFieldType(0));
        assertEquals(DurationFieldType.hours(),type.getFieldType(1));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(2));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(3));
        assertEquals(DurationFieldType.millis(),type.getFieldType(4));
        assertEquals("DayTime",type.getName());
        assertEquals("PeriodType[DayTime]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.dayTime());
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.dayTime().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testTime() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(4,type.size());
        assertEquals(DurationFieldType.hours(),type.getFieldType(0));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(1));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(2));
        assertEquals(DurationFieldType.millis(),type.getFieldType(3));
        assertEquals("Time",type.getName());
        assertEquals("PeriodType[Time]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.time());
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.time().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testYears() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(1,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals("Years",type.getName());
        assertEquals("PeriodType[Years]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.years());
        assertEquals(false,type.equals(PeriodType.standard()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.years().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMonths() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(1,type.size());
        assertEquals(DurationFieldType.months(),type.getFieldType(0));
        assertEquals("Months",type.getName());
        assertEquals("PeriodType[Months]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.months());
        assertEquals(false,type.equals(PeriodType.standard()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.months().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testWeeks() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(1,type.size());
        assertEquals(DurationFieldType.weeks(),type.getFieldType(0));
        assertEquals("Weeks",type.getName());
        assertEquals("PeriodType[Weeks]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.weeks());
        assertEquals(false,type.equals(PeriodType.standard()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.weeks().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testDays() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(1,type.size());
        assertEquals(DurationFieldType.days(),type.getFieldType(0));
        assertEquals("Days",type.getName());
        assertEquals("PeriodType[Days]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.days());
        assertEquals(false,type.equals(PeriodType.standard()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.days().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testHours() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(1,type.size());
        assertEquals(DurationFieldType.hours(),type.getFieldType(0));
        assertEquals("Hours",type.getName());
        assertEquals("PeriodType[Hours]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.hours());
        assertEquals(false,type.equals(PeriodType.standard()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.hours().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMinutes() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals(1,type.size());
        assertEquals(DurationFieldType.minutes(),type.getFieldType(0));
        assertEquals("Minutes",type.getName());
        assertEquals("PeriodType[Minutes]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.minutes());
        assertEquals(false,type.equals(PeriodType.standard()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.minutes().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testSeconds() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(1,type.size());
        assertEquals(DurationFieldType.seconds(),type.getFieldType(0));
        assertEquals("Seconds",type.getName());
        assertEquals("PeriodType[Seconds]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.seconds());
        assertEquals(false,type.equals(PeriodType.standard()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.seconds().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
        assertSameAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMillis() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals(1,type.size());
        assertEquals(DurationFieldType.millis(),type.getFieldType(0));
        assertEquals("Millis",type.getName());
        assertEquals("PeriodType[Millis]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.millis());
        assertEquals(false,type.equals(PeriodType.standard()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.millis().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
        assertSameAfterSerialization(type);
    }

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

    public void testForFields2() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(2,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.hours(),type.getFieldType(1));
        assertEquals("StandardNoMonthsNoWeeksNoDaysNoMinutesNoSecondsNoMillis",type.getName());
        assertEquals("PeriodType[StandardNoMonthsNoWeeksNoDaysNoMinutesNoSecondsNoMillis]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.forFields(types));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.forFields(types).hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
    }

    public void testForFields3() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(2,type.size());
        assertEquals(DurationFieldType.months(),type.getFieldType(0));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
        assertEquals("StandardNoYearsNoDaysNoHoursNoMinutesNoSecondsNoMillis",type.getName());
        assertEquals("PeriodType[StandardNoYearsNoDaysNoHoursNoMinutesNoSecondsNoMillis]",type.toString());
        assertEquals(true,type.equals(type));
        assertEquals(true,type == PeriodType.forFields(types));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.forFields(types).hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertSameAfterSerialization(type);
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
    public void testMaskYears() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(7,type.size());
        assertEquals(DurationFieldType.months(),type.getFieldType(0));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
        assertEquals(true,type.equals(type));
        assertEquals(true,type.equals(PeriodType.standard().withYearsRemoved()));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.standard().withYearsRemoved().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertEquals("StandardNoYears",type.getName());
        assertEquals("PeriodType[StandardNoYears]",type.toString());
        assertEqualsAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMaskMonths() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(7,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
        assertEquals(true,type.equals(type));
        assertEquals(true,type.equals(PeriodType.standard().withMonthsRemoved()));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.standard().withMonthsRemoved().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertEquals("StandardNoMonths",type.getName());
        assertEquals("PeriodType[StandardNoMonths]",type.toString());
        assertEqualsAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMaskWeeks() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(7,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
        assertEquals(true,type.equals(type));
        assertEquals(true,type.equals(PeriodType.standard().withWeeksRemoved()));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.standard().withWeeksRemoved().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertEquals("StandardNoWeeks",type.getName());
        assertEquals("PeriodType[StandardNoWeeks]",type.toString());
        assertEqualsAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMaskDays() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(7,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
        assertEquals(true,type.equals(type));
        assertEquals(true,type.equals(PeriodType.standard().withDaysRemoved()));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.standard().withDaysRemoved().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertEquals("StandardNoDays",type.getName());
        assertEquals("PeriodType[StandardNoDays]",type.toString());
        assertEqualsAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMaskHours() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(7,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
        assertEquals(true,type.equals(type));
        assertEquals(true,type.equals(PeriodType.standard().withHoursRemoved()));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.standard().withHoursRemoved().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertEquals("StandardNoHours",type.getName());
        assertEquals("PeriodType[StandardNoHours]",type.toString());
        assertEqualsAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMaskMinutes() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(7,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
        assertEquals(true,type.equals(type));
        assertEquals(true,type.equals(PeriodType.standard().withMinutesRemoved()));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.standard().withMinutesRemoved().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertEquals("StandardNoMinutes",type.getName());
        assertEquals("PeriodType[StandardNoMinutes]",type.toString());
        assertEqualsAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMaskSeconds() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(7,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(5));
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
        assertEquals(true,type.equals(type));
        assertEquals(true,type.equals(PeriodType.standard().withSecondsRemoved()));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.standard().withSecondsRemoved().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertEquals("StandardNoSeconds",type.getName());
        assertEquals("PeriodType[StandardNoSeconds]",type.toString());
        assertEqualsAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMaskMillis() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(7,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
        assertEquals(DurationFieldType.minutes(),type.getFieldType(5));
        assertEquals(DurationFieldType.seconds(),type.getFieldType(6));
        assertEquals(true,type.equals(type));
        assertEquals(true,type.equals(PeriodType.standard().withMillisRemoved()));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.standard().withMillisRemoved().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertEquals("StandardNoMillis",type.getName());
        assertEquals("PeriodType[StandardNoMillis]",type.toString());
        assertEqualsAfterSerialization(type);
    }

    //-----------------------------------------------------------------------
    public void testMaskHoursMinutesSeconds() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(5,type.size());
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
        assertEquals(DurationFieldType.millis(),type.getFieldType(4));
        assertEquals(true,type.equals(type));
        assertEquals(true,type.equals(PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved()));
        assertEquals(false,type.equals(PeriodType.millis()));
        assertEquals(true,type.hashCode()== type.hashCode());
        assertEquals(true,type.hashCode()== PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved().hashCode());
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
        assertEquals("StandardNoHoursNoMinutesNoSeconds",type.getName());
        assertEquals("PeriodType[StandardNoHoursNoMinutesNoSeconds]",type.toString());
        assertEqualsAfterSerialization(type);
    }

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

    public void testStandard_1_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(8,type.size());
    }

    public void testStandard_2_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testStandard_3_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

    public void testStandard_4_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

    public void testStandard_5_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

    public void testStandard_6_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
    }

    public void testStandard_7_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(5));
    }

    public void testStandard_8_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(6));
    }

    public void testStandard_9_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(DurationFieldType.millis(),type.getFieldType(7));
    }

    public void testStandard_10_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals("Standard",type.getName());
    }

    public void testStandard_11_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals("PeriodType[Standard]",type.toString());
    }

    public void testStandard_12_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(true,type.equals(type));
    }

    public void testStandard_13_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(true,type == PeriodType.standard());
    }

    public void testStandard_14_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testStandard_15_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testStandard_16_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(true,type.hashCode()== PeriodType.standard().hashCode());
    }

    public void testStandard_17_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testYearMonthDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(7,type.size());
    }

    public void testYearMonthDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testYearMonthDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

    public void testYearMonthDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

    public void testYearMonthDayTime_5_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

    public void testYearMonthDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

    public void testYearMonthDayTime_7_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

    public void testYearMonthDayTime_8_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

    public void testYearMonthDayTime_9_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals("YearMonthDayTime",type.getName());
    }

    public void testYearMonthDayTime_10_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals("PeriodType[YearMonthDayTime]",type.toString());
    }

    public void testYearMonthDayTime_11_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(true,type.equals(type));
    }

    public void testYearMonthDayTime_12_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(true,type == PeriodType.yearMonthDayTime());
    }

    public void testYearMonthDayTime_13_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testYearMonthDayTime_14_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testYearMonthDayTime_15_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(true,type.hashCode()== PeriodType.yearMonthDayTime().hashCode());
    }

    public void testYearMonthDayTime_16_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testYearMonthDay_1_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(3,type.size());
    }

    public void testYearMonthDay_2_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testYearMonthDay_3_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

    public void testYearMonthDay_4_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

    public void testYearMonthDay_5_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals("YearMonthDay",type.getName());
    }

    public void testYearMonthDay_6_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals("PeriodType[YearMonthDay]",type.toString());
    }

    public void testYearMonthDay_7_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(true,type.equals(type));
    }

    public void testYearMonthDay_8_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(true,type == PeriodType.yearMonthDay());
    }

    public void testYearMonthDay_9_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testYearMonthDay_10_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testYearMonthDay_11_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(true,type.hashCode()== PeriodType.yearMonthDay().hashCode());
    }

    public void testYearMonthDay_12_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testYearWeekDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(7,type.size());
    }

    public void testYearWeekDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testYearWeekDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
    }

    public void testYearWeekDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

    public void testYearWeekDayTime_5_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

    public void testYearWeekDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

    public void testYearWeekDayTime_7_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

    public void testYearWeekDayTime_8_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

    public void testYearWeekDayTime_9_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals("YearWeekDayTime",type.getName());
    }

    public void testYearWeekDayTime_10_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals("PeriodType[YearWeekDayTime]",type.toString());
    }

    public void testYearWeekDayTime_11_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(true,type.equals(type));
    }

    public void testYearWeekDayTime_12_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(true,type == PeriodType.yearWeekDayTime());
    }

    public void testYearWeekDayTime_13_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testYearWeekDayTime_14_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testYearWeekDayTime_15_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(true,type.hashCode()== PeriodType.yearWeekDayTime().hashCode());
    }

    public void testYearWeekDayTime_16_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testYearWeekDay_1_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(3,type.size());
    }

    public void testYearWeekDay_2_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testYearWeekDay_3_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
    }

    public void testYearWeekDay_4_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

    public void testYearWeekDay_5_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals("YearWeekDay",type.getName());
    }

    public void testYearWeekDay_6_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals("PeriodType[YearWeekDay]",type.toString());
    }

    public void testYearWeekDay_7_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(true,type.equals(type));
    }

    public void testYearWeekDay_8_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(true,type == PeriodType.yearWeekDay());
    }

    public void testYearWeekDay_9_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testYearWeekDay_10_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testYearWeekDay_11_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(true,type.hashCode()== PeriodType.yearWeekDay().hashCode());
    }

    public void testYearWeekDay_12_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testYearDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(6,type.size());
    }

    public void testYearDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testYearDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(DurationFieldType.days(),type.getFieldType(1));
    }

    public void testYearDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(DurationFieldType.hours(),type.getFieldType(2));
    }

    public void testYearDayTime_5_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(3));
    }

    public void testYearDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(4));
    }

    public void testYearDayTime_7_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(DurationFieldType.millis(),type.getFieldType(5));
    }

    public void testYearDayTime_8_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals("YearDayTime",type.getName());
    }

    public void testYearDayTime_9_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals("PeriodType[YearDayTime]",type.toString());
    }

    public void testYearDayTime_10_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(true,type.equals(type));
    }

    public void testYearDayTime_11_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(true,type == PeriodType.yearDayTime());
    }

    public void testYearDayTime_12_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testYearDayTime_13_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testYearDayTime_14_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(true,type.hashCode()== PeriodType.yearDayTime().hashCode());
    }

    public void testYearDayTime_15_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testYearDay_1_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(2,type.size());
    }

    public void testYearDay_2_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testYearDay_3_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(DurationFieldType.days(),type.getFieldType(1));
    }

    public void testYearDay_4_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals("YearDay",type.getName());
    }

    public void testYearDay_5_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals("PeriodType[YearDay]",type.toString());
    }

    public void testYearDay_6_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(true,type.equals(type));
    }

    public void testYearDay_7_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(true,type == PeriodType.yearDay());
    }

    public void testYearDay_8_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testYearDay_9_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testYearDay_10_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(true,type.hashCode()== PeriodType.yearDay().hashCode());
    }

    public void testYearDay_11_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(5,type.size());
    }

    public void testDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(DurationFieldType.days(),type.getFieldType(0));
    }

    public void testDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(DurationFieldType.hours(),type.getFieldType(1));
    }

    public void testDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(2));
    }

    public void testDayTime_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(3));
    }

    public void testDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(DurationFieldType.millis(),type.getFieldType(4));
    }

    public void testDayTime_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals("DayTime",type.getName());
    }

    public void testDayTime_8_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals("PeriodType[DayTime]",type.toString());
    }

    public void testDayTime_9_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(true,type.equals(type));
    }

    public void testDayTime_10_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(true,type == PeriodType.dayTime());
    }

    public void testDayTime_11_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testDayTime_12_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testDayTime_13_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(true,type.hashCode()== PeriodType.dayTime().hashCode());
    }

    public void testDayTime_14_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testTime_1_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(4,type.size());
    }

    public void testTime_2_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(DurationFieldType.hours(),type.getFieldType(0));
    }

    public void testTime_3_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(1));
    }

    public void testTime_4_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(2));
    }

    public void testTime_5_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(DurationFieldType.millis(),type.getFieldType(3));
    }

    public void testTime_6_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals("Time",type.getName());
    }

    public void testTime_7_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals("PeriodType[Time]",type.toString());
    }

    public void testTime_8_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(true,type.equals(type));
    }

    public void testTime_9_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(true,type == PeriodType.time());
    }

    public void testTime_10_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testTime_11_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testTime_12_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(true,type.hashCode()== PeriodType.time().hashCode());
    }

    public void testTime_13_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testYears_1_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(1,type.size());
    }

    public void testYears_2_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testYears_3_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals("Years",type.getName());
    }

    public void testYears_4_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals("PeriodType[Years]",type.toString());
    }

    public void testYears_5_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(true,type.equals(type));
    }

    public void testYears_6_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(true,type == PeriodType.years());
    }

    public void testYears_7_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(false,type.equals(PeriodType.standard()));
    }

    public void testYears_8_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testYears_9_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(true,type.hashCode()== PeriodType.years().hashCode());
    }

    public void testYears_10_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

    public void testMonths_1_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(1,type.size());
    }

    public void testMonths_2_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(DurationFieldType.months(),type.getFieldType(0));
    }

    public void testMonths_3_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals("Months",type.getName());
    }

    public void testMonths_4_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals("PeriodType[Months]",type.toString());
    }

    public void testMonths_5_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(true,type.equals(type));
    }

    public void testMonths_6_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(true,type == PeriodType.months());
    }

    public void testMonths_7_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(false,type.equals(PeriodType.standard()));
    }

    public void testMonths_8_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMonths_9_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(true,type.hashCode()== PeriodType.months().hashCode());
    }

    public void testMonths_10_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

    public void testWeeks_1_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(1,type.size());
    }

    public void testWeeks_2_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(0));
    }

    public void testWeeks_3_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals("Weeks",type.getName());
    }

    public void testWeeks_4_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals("PeriodType[Weeks]",type.toString());
    }

    public void testWeeks_5_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(true,type.equals(type));
    }

    public void testWeeks_6_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(true,type == PeriodType.weeks());
    }

    public void testWeeks_7_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(false,type.equals(PeriodType.standard()));
    }

    public void testWeeks_8_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testWeeks_9_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(true,type.hashCode()== PeriodType.weeks().hashCode());
    }

    public void testWeeks_10_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

    public void testDays_1_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(1,type.size());
    }

    public void testDays_2_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(DurationFieldType.days(),type.getFieldType(0));
    }

    public void testDays_3_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals("Days",type.getName());
    }

    public void testDays_4_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals("PeriodType[Days]",type.toString());
    }

    public void testDays_5_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(true,type.equals(type));
    }

    public void testDays_6_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(true,type == PeriodType.days());
    }

    public void testDays_7_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(false,type.equals(PeriodType.standard()));
    }

    public void testDays_8_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testDays_9_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(true,type.hashCode()== PeriodType.days().hashCode());
    }

    public void testDays_10_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

    public void testHours_1_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(1,type.size());
    }

    public void testHours_2_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(DurationFieldType.hours(),type.getFieldType(0));
    }

    public void testHours_3_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals("Hours",type.getName());
    }

    public void testHours_4_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals("PeriodType[Hours]",type.toString());
    }

    public void testHours_5_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(true,type.equals(type));
    }

    public void testHours_6_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(true,type == PeriodType.hours());
    }

    public void testHours_7_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(false,type.equals(PeriodType.standard()));
    }

    public void testHours_8_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testHours_9_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(true,type.hashCode()== PeriodType.hours().hashCode());
    }

    public void testHours_10_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

    public void testMinutes_1_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals(1,type.size());
    }

    public void testMinutes_2_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(0));
    }

    public void testMinutes_3_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals("Minutes",type.getName());
    }

    public void testMinutes_4_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals("PeriodType[Minutes]",type.toString());
    }

    public void testMinutes_5_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals(true,type.equals(type));
    }

    public void testMinutes_6_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals(true,type == PeriodType.minutes());
    }

    public void testMinutes_7_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals(false,type.equals(PeriodType.standard()));
    }

    public void testMinutes_8_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMinutes_9_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals(true,type.hashCode()== PeriodType.minutes().hashCode());
    }

    public void testMinutes_10_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

    public void testSeconds_1_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(1,type.size());
    }

    public void testSeconds_2_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(0));
    }

    public void testSeconds_3_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals("Seconds",type.getName());
    }

    public void testSeconds_4_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals("PeriodType[Seconds]",type.toString());
    }

    public void testSeconds_5_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(true,type.equals(type));
    }

    public void testSeconds_6_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(true,type == PeriodType.seconds());
    }

    public void testSeconds_7_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(false,type.equals(PeriodType.standard()));
    }

    public void testSeconds_8_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testSeconds_9_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(true,type.hashCode()== PeriodType.seconds().hashCode());
    }

    public void testSeconds_10_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(false,type.hashCode()== PeriodType.standard().hashCode());
    }

    public void testMillis_1_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals(1,type.size());
    }

    public void testMillis_2_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals(DurationFieldType.millis(),type.getFieldType(0));
    }

    public void testMillis_3_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals("Millis",type.getName());
    }

    public void testMillis_4_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals("PeriodType[Millis]",type.toString());
    }

    public void testMillis_5_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals(true,type.equals(type));
    }

    public void testMillis_6_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals(true,type == PeriodType.millis());
    }

    public void testMillis_7_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals(false,type.equals(PeriodType.standard()));
    }

    public void testMillis_8_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMillis_9_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals(true,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testMillis_10_oe() throws Exception {
        PeriodType type = PeriodType.millis();
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
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        assertSame(PeriodType.months(),type);
    }

    public void testForFields1_3_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        assertSame(PeriodType.weeks(),type);
    }

    public void testForFields1_4_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        assertSame(PeriodType.days(),type);
    }

    public void testForFields1_5_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.hours(),
        });
        assertSame(PeriodType.hours(),type);
    }

    public void testForFields1_6_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.hours(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.minutes(),
        });
        assertSame(PeriodType.minutes(),type);
    }

    public void testForFields1_7_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.hours(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.minutes(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.seconds(),
        });
        assertSame(PeriodType.seconds(),type);
    }

    public void testForFields1_8_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.weeks(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.days(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.hours(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.minutes(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.seconds(),
        });
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
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testForFields2_3_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(DurationFieldType.hours(),type.getFieldType(1));
    }

    public void testForFields2_4_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals("StandardNoMonthsNoWeeksNoDaysNoMinutesNoSecondsNoMillis",type.getName());
    }

    public void testForFields2_5_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals("PeriodType[StandardNoMonthsNoWeeksNoDaysNoMinutesNoSecondsNoMillis]",type.toString());
    }

    public void testForFields2_6_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(true,type.equals(type));
    }

    public void testForFields2_7_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(true,type == PeriodType.forFields(types));
    }

    public void testForFields2_8_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testForFields2_9_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testForFields2_10_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(true,type.hashCode()== PeriodType.forFields(types).hashCode());
    }

    public void testForFields2_11_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
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
        assertEquals(DurationFieldType.months(),type.getFieldType(0));
    }

    public void testForFields3_3_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
    }

    public void testForFields3_4_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals("StandardNoYearsNoDaysNoHoursNoMinutesNoSecondsNoMillis",type.getName());
    }

    public void testForFields3_5_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals("PeriodType[StandardNoYearsNoDaysNoHoursNoMinutesNoSecondsNoMillis]",type.toString());
    }

    public void testForFields3_6_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(true,type.equals(type));
    }

    public void testForFields3_7_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(true,type == PeriodType.forFields(types));
    }

    public void testForFields3_8_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testForFields3_9_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testForFields3_10_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(true,type.hashCode()== PeriodType.forFields(types).hashCode());
    }

    public void testForFields3_11_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
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
        assertEquals(false,type.hashCode()== type2.hashCode());
    }

    public void testMaskYears_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(7,type.size());
    }

    public void testMaskYears_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(DurationFieldType.months(),type.getFieldType(0));
    }

    public void testMaskYears_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
    }

    public void testMaskYears_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

    public void testMaskYears_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

    public void testMaskYears_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

    public void testMaskYears_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

    public void testMaskYears_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

    public void testMaskYears_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(true,type.equals(type));
    }

    public void testMaskYears_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(true,type.equals(PeriodType.standard().withYearsRemoved()));
    }

    public void testMaskYears_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testMaskYears_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMaskYears_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(true,type.hashCode()== PeriodType.standard().withYearsRemoved().hashCode());
    }

    public void testMaskYears_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testMaskYears_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals("StandardNoYears",type.getName());
    }

    public void testMaskYears_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals("PeriodType[StandardNoYears]",type.toString());
    }

    public void testMaskMonths_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(7,type.size());
    }

    public void testMaskMonths_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testMaskMonths_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(1));
    }

    public void testMaskMonths_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

    public void testMaskMonths_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

    public void testMaskMonths_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

    public void testMaskMonths_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

    public void testMaskMonths_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

    public void testMaskMonths_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(true,type.equals(type));
    }

    public void testMaskMonths_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(true,type.equals(PeriodType.standard().withMonthsRemoved()));
    }

    public void testMaskMonths_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testMaskMonths_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMaskMonths_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(true,type.hashCode()== PeriodType.standard().withMonthsRemoved().hashCode());
    }

    public void testMaskMonths_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testMaskMonths_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("StandardNoMonths",type.getName());
    }

    public void testMaskMonths_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("PeriodType[StandardNoMonths]",type.toString());
    }

    public void testMaskWeeks_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(7,type.size());
    }

    public void testMaskWeeks_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testMaskWeeks_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

    public void testMaskWeeks_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(DurationFieldType.days(),type.getFieldType(2));
    }

    public void testMaskWeeks_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

    public void testMaskWeeks_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

    public void testMaskWeeks_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

    public void testMaskWeeks_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

    public void testMaskWeeks_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(true,type.equals(type));
    }

    public void testMaskWeeks_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(true,type.equals(PeriodType.standard().withWeeksRemoved()));
    }

    public void testMaskWeeks_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testMaskWeeks_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMaskWeeks_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(true,type.hashCode()== PeriodType.standard().withWeeksRemoved().hashCode());
    }

    public void testMaskWeeks_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testMaskWeeks_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("StandardNoWeeks",type.getName());
    }

    public void testMaskWeeks_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("PeriodType[StandardNoWeeks]",type.toString());
    }

    public void testMaskDays_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(7,type.size());
    }

    public void testMaskDays_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testMaskDays_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

    public void testMaskDays_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

    public void testMaskDays_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(DurationFieldType.hours(),type.getFieldType(3));
    }

    public void testMaskDays_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

    public void testMaskDays_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

    public void testMaskDays_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

    public void testMaskDays_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(true,type.equals(type));
    }

    public void testMaskDays_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(true,type.equals(PeriodType.standard().withDaysRemoved()));
    }

    public void testMaskDays_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testMaskDays_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMaskDays_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(true,type.hashCode()== PeriodType.standard().withDaysRemoved().hashCode());
    }

    public void testMaskDays_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testMaskDays_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals("StandardNoDays",type.getName());
    }

    public void testMaskDays_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals("PeriodType[StandardNoDays]",type.toString());
    }

    public void testMaskHours_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(7,type.size());
    }

    public void testMaskHours_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testMaskHours_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

    public void testMaskHours_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

    public void testMaskHours_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

    public void testMaskHours_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(4));
    }

    public void testMaskHours_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

    public void testMaskHours_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

    public void testMaskHours_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(true,type.equals(type));
    }

    public void testMaskHours_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(true,type.equals(PeriodType.standard().withHoursRemoved()));
    }

    public void testMaskHours_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testMaskHours_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMaskHours_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(true,type.hashCode()== PeriodType.standard().withHoursRemoved().hashCode());
    }

    public void testMaskHours_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testMaskHours_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("StandardNoHours",type.getName());
    }

    public void testMaskHours_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("PeriodType[StandardNoHours]",type.toString());
    }

    public void testMaskMinutes_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(7,type.size());
    }

    public void testMaskMinutes_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testMaskMinutes_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

    public void testMaskMinutes_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

    public void testMaskMinutes_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

    public void testMaskMinutes_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
    }

    public void testMaskMinutes_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(5));
    }

    public void testMaskMinutes_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

    public void testMaskMinutes_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(true,type.equals(type));
    }

    public void testMaskMinutes_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(true,type.equals(PeriodType.standard().withMinutesRemoved()));
    }

    public void testMaskMinutes_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testMaskMinutes_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMaskMinutes_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(true,type.hashCode()== PeriodType.standard().withMinutesRemoved().hashCode());
    }

    public void testMaskMinutes_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testMaskMinutes_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("StandardNoMinutes",type.getName());
    }

    public void testMaskMinutes_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("PeriodType[StandardNoMinutes]",type.toString());
    }

    public void testMaskSeconds_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(7,type.size());
    }

    public void testMaskSeconds_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testMaskSeconds_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

    public void testMaskSeconds_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

    public void testMaskSeconds_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

    public void testMaskSeconds_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
    }

    public void testMaskSeconds_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(5));
    }

    public void testMaskSeconds_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(DurationFieldType.millis(),type.getFieldType(6));
    }

    public void testMaskSeconds_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(true,type.equals(type));
    }

    public void testMaskSeconds_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(true,type.equals(PeriodType.standard().withSecondsRemoved()));
    }

    public void testMaskSeconds_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testMaskSeconds_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMaskSeconds_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(true,type.hashCode()== PeriodType.standard().withSecondsRemoved().hashCode());
    }

    public void testMaskSeconds_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testMaskSeconds_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("StandardNoSeconds",type.getName());
    }

    public void testMaskSeconds_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("PeriodType[StandardNoSeconds]",type.toString());
    }

    public void testMaskMillis_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(7,type.size());
    }

    public void testMaskMillis_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testMaskMillis_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

    public void testMaskMillis_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

    public void testMaskMillis_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

    public void testMaskMillis_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(DurationFieldType.hours(),type.getFieldType(4));
    }

    public void testMaskMillis_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(DurationFieldType.minutes(),type.getFieldType(5));
    }

    public void testMaskMillis_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(DurationFieldType.seconds(),type.getFieldType(6));
    }

    public void testMaskMillis_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(true,type.equals(type));
    }

    public void testMaskMillis_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(true,type.equals(PeriodType.standard().withMillisRemoved()));
    }

    public void testMaskMillis_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testMaskMillis_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMaskMillis_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(true,type.hashCode()== PeriodType.standard().withMillisRemoved().hashCode());
    }

    public void testMaskMillis_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testMaskMillis_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals("StandardNoMillis",type.getName());
    }

    public void testMaskMillis_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals("PeriodType[StandardNoMillis]",type.toString());
    }

    public void testMaskHoursMinutesSeconds_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(5,type.size());
    }

    public void testMaskHoursMinutesSeconds_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(DurationFieldType.years(),type.getFieldType(0));
    }

    public void testMaskHoursMinutesSeconds_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(DurationFieldType.months(),type.getFieldType(1));
    }

    public void testMaskHoursMinutesSeconds_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(DurationFieldType.weeks(),type.getFieldType(2));
    }

    public void testMaskHoursMinutesSeconds_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(DurationFieldType.days(),type.getFieldType(3));
    }

    public void testMaskHoursMinutesSeconds_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(DurationFieldType.millis(),type.getFieldType(4));
    }

    public void testMaskHoursMinutesSeconds_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(true,type.equals(type));
    }

    public void testMaskHoursMinutesSeconds_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(true,type.equals(PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved()));
    }

    public void testMaskHoursMinutesSeconds_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(false,type.equals(PeriodType.millis()));
    }

    public void testMaskHoursMinutesSeconds_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(true,type.hashCode()== type.hashCode());
    }

    public void testMaskHoursMinutesSeconds_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(true,type.hashCode()== PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved().hashCode());
    }

    public void testMaskHoursMinutesSeconds_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(false,type.hashCode()== PeriodType.millis().hashCode());
    }

    public void testMaskHoursMinutesSeconds_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals("StandardNoHoursNoMinutesNoSeconds",type.getName());
    }

    public void testMaskHoursMinutesSeconds_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
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
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice1_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice1_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice1_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        
        type = PeriodType.standard().withHoursRemoved();
        type2 = type.withHoursRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice1_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        
        type = PeriodType.standard().withHoursRemoved();
        type2 = type.withHoursRemoved();
        
        type = PeriodType.standard().withMinutesRemoved();
        type2 = type.withMinutesRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice1_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        
        type = PeriodType.standard().withHoursRemoved();
        type2 = type.withHoursRemoved();
        
        type = PeriodType.standard().withMinutesRemoved();
        type2 = type.withMinutesRemoved();
        
        type = PeriodType.standard().withSecondsRemoved();
        type2 = type.withSecondsRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice1_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        
        type = PeriodType.standard().withDaysRemoved();
        type2 = type.withDaysRemoved();
        
        type = PeriodType.standard().withHoursRemoved();
        type2 = type.withHoursRemoved();
        
        type = PeriodType.standard().withMinutesRemoved();
        type2 = type.withMinutesRemoved();
        
        type = PeriodType.standard().withSecondsRemoved();
        type2 = type.withSecondsRemoved();
        
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
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice2_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice2_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        
        type = PeriodType.millis();
        type2 = type.withDaysRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice2_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        
        type = PeriodType.millis();
        type2 = type.withDaysRemoved();
        
        type = PeriodType.millis();
        type2 = type.withHoursRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice2_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        
        type = PeriodType.millis();
        type2 = type.withDaysRemoved();
        
        type = PeriodType.millis();
        type2 = type.withHoursRemoved();
        
        type = PeriodType.millis();
        type2 = type.withMinutesRemoved();
        assertEquals(true,type == type2);
    }

    public void testMaskTwice2_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        
        type = PeriodType.millis();
        type2 = type.withDaysRemoved();
        
        type = PeriodType.millis();
        type2 = type.withHoursRemoved();
        
        type = PeriodType.millis();
        type2 = type.withMinutesRemoved();
        
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
        assertEquals(true,type.equals(PeriodType.dayTime().withMillisRemoved()));
    }

    public void testEquals_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(false,type.equals(null));
    }

    public void testEquals_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
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
        assertEquals(false,type.isSupported(DurationFieldType.months()));
    }

    public void testIsSupported_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(false,type.isSupported(DurationFieldType.weeks()));
    }

    public void testIsSupported_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(true,type.isSupported(DurationFieldType.days()));
    }

    public void testIsSupported_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(true,type.isSupported(DurationFieldType.hours()));
    }

    public void testIsSupported_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(true,type.isSupported(DurationFieldType.minutes()));
    }

    public void testIsSupported_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(true,type.isSupported(DurationFieldType.seconds()));
    }

    public void testIsSupported_8_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(false,type.isSupported(DurationFieldType.millis()));
    }

    public void testIndexOf_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(-1,type.indexOf(DurationFieldType.years()));
    }

    public void testIndexOf_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(-1,type.indexOf(DurationFieldType.months()));
    }

    public void testIndexOf_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(-1,type.indexOf(DurationFieldType.weeks()));
    }

    public void testIndexOf_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(0,type.indexOf(DurationFieldType.days()));
    }

    public void testIndexOf_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(1,type.indexOf(DurationFieldType.hours()));
    }

    public void testIndexOf_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(2,type.indexOf(DurationFieldType.minutes()));
    }

    public void testIndexOf_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(3,type.indexOf(DurationFieldType.seconds()));
    }

    public void testIndexOf_8_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertEquals(-1,type.indexOf(DurationFieldType.millis()));
    }

    public void testStandard_18_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.standard();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testYearMonthDayTime_17_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testYearMonthDay_13_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testYearWeekDayTime_17_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testYearWeekDay_13_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testYearDayTime_16_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testYearDay_12_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testDayTime_15_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testTime_14_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.time();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testYears_11_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.years();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMonths_11_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.months();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testWeeks_11_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testDays_11_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.days();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testHours_11_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.hours();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMinutes_11_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testSeconds_11_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMillis_11_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.millis();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testForFields2_12_oe_1_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testForFields3_12_oe_1_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMaskYears_17_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMaskMonths_17_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMaskWeeks_17_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMaskDays_17_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMaskHours_17_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMaskMinutes_17_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMaskSeconds_17_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMaskMillis_17_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

    public void testMaskHoursMinutesSeconds_15_oe_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
                final PeriodType type0 = type;
        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();
                ObjectOutputStream oos0 = new ObjectOutputStream(baos0);
                oos0.writeObject(type0);
                oos0.close();
                byte[] bytes0 = baos0.toByteArray();
        
                ByteArrayInputStream bais0 = new ByteArrayInputStream(bytes0);
                ObjectInputStream ois0 = new ObjectInputStream(bais0);
                PeriodType result0 = (PeriodType) ois0.readObject();
                ois0.close();
        
                assertEquals(type0,result0);
    }

}
