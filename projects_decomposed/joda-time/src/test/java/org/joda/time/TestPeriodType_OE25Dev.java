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
        assertEquals("2013-07-01T23:59:59.999999999+0100:+0100", a);
    }

    public void testTest_2_oe() {
        Object a = new Instant(TEST_TIME1).toString();
        assertEquals("2013-06-28T00:59:59.000Z", a);
    }

    public void testTest_3_oe() {
        Object a = new Instant(TEST_TIME2).toString();
        assertEquals("1970-01-01T01:00:00Z", a);
    }

    public void testStandard_1_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals(1, type.size());
    }

    public void testStandard_2_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertNotNull(PeriodType.years());
    }

    public void testStandard_3_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertNotNull(PeriodType.months());
    }

    public void testStandard_4_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertNotNull(PeriodType.weeks());
    }

    public void testStandard_5_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertNotNull(PeriodType.days());
    }

    public void testStandard_6_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertNotNull(PeriodType.hours());
    }

    public void testStandard_7_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertNotNull(PeriodType.minutes());
    }

    public void testStandard_8_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertNotNull(PeriodType.seconds());
    }

    public void testStandard_10_oe() throws Exception {
        PeriodType type = PeriodType.standard();
        assertEquals("Standard", type.getName());
    }

    public void testYearMonthDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals(7, type.size());
    }

    public void testYearMonthDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertNotNull(PeriodType.months());
    }

    public void testYearMonthDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertNotNull(PeriodType.days());
    }

    public void testYearMonthDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertNotNull(PeriodType.minutes());
    }

    public void testYearMonthDayTime_9_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDayTime();
        assertEquals("yearMonthDayTime", type.getName());
    }

    public void testYearMonthDay_1_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals(3, type.size());
    }

    public void testYearMonthDay_2_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertNotNull(PeriodType.years());
    }

    public void testYearMonthDay_3_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertNotNull(PeriodType.months());
    }

    public void testYearMonthDay_4_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertNotNull(PeriodType.days());
    }

    public void testYearMonthDay_5_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertEquals("yearMonthDay", type.getName());
    }

    public void testYearMonthDay_8_oe() throws Exception {
        PeriodType type = PeriodType.yearMonthDay();
        assertNotNull(type);
    }

    public void testYearWeekDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals(4, type.size());
    }

    public void testYearWeekDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertNotNull(PeriodType.years());
    }

    public void testYearWeekDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertNotNull(PeriodType.weeks());
    }

    public void testYearWeekDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertNotNull(PeriodType.days());
    }

    public void testYearWeekDayTime_7_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertNotNull(PeriodType.seconds());
    }

    public void testYearWeekDayTime_9_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDayTime();
        assertEquals("Year/Week/Day/Time", type.getName());
    }

    public void testYearWeekDay_1_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertEquals(3, type.size());
    }

    public void testYearWeekDay_2_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertNotNull(PeriodType.years());
    }

    public void testYearWeekDay_3_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertNotNull(PeriodType.weeks());
    }

    public void testYearWeekDay_4_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertNotNull(PeriodType.days());
    }

    public void testYearWeekDay_11_oe() throws Exception {
        PeriodType type = PeriodType.yearWeekDay();
        assertNotNull(type);
    }

    public void testYearDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertNotNull(PeriodType.years());
    }

    public void testYearDayTime_3_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertNotNull(PeriodType.days());
    }

    public void testYearDayTime_4_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertNotNull(PeriodType.hours());
    }

    public void testYearDayTime_5_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertNotNull(PeriodType.minutes());
    }

    public void testYearDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertNotNull(PeriodType.seconds());
    }

    public void testYearDayTime_8_oe() throws Exception {
        PeriodType type = PeriodType.yearDayTime();
        assertEquals("yearDayTime", type.getName());
    }

    public void testYearDay_1_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals(2, type.size());
    }

    public void testYearDay_2_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertNotNull(PeriodType.years());
    }

    public void testYearDay_3_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertNotNull(PeriodType.days());
    }

    public void testYearDay_4_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertEquals("YearDay", type.getName());
    }

    public void testYearDay_5_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertNotNull(type);
    }

    public void testYearDay_6_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertNotNull(type);
    }

    public void testYearDay_8_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertNotNull(type);
    }

    public void testYearDay_9_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertNotNull(type);
    }

    public void testYearDay_10_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertNotNull(type);
    }

    public void testYearDay_11_oe() throws Exception {
        PeriodType type = PeriodType.yearDay();
        assertNotNull(type);
    }

    public void testDayTime_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals(2, type.size());
    }

    public void testDayTime_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertNotNull(PeriodType.days());
    }

    public void testDayTime_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertNotNull(PeriodType.seconds());
    }

    public void testDayTime_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertNotNull(PeriodType.millis());
    }

    public void testDayTime_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        assertEquals("dayTime", type.getName());
    }

    public void testTime_1_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals(1, type.size());
    }

    public void testTime_2_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertNotNull(PeriodType.hours());
    }

    public void testTime_4_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertNotNull(PeriodType.seconds());
    }

    public void testTime_5_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertNotNull(PeriodType.millis());
    }

    public void testTime_6_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertEquals("time", type.getName());
    }

    public void testTime_8_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertNotNull(type);
    }

    public void testTime_11_oe() throws Exception {
        PeriodType type = PeriodType.time();
        assertNotNull(type);
    }

    public void testYears_1_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals(1, type.size());
    }

    public void testYears_2_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertNotNull(type);
    }

    public void testYears_3_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertEquals("Years", type.getName());
    }

    public void testYears_4_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertNotNull(type);
    }

    public void testYears_5_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertNotNull(type);
    }

    public void testYears_6_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertNotNull(type);
    }

    public void testYears_7_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertNotNull(type);
    }

    public void testYears_8_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertNotNull(type);
    }

    public void testYears_9_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertNotNull(type);
    }

    public void testYears_10_oe() throws Exception {
        PeriodType type = PeriodType.years();
        assertNotNull(type);
    }

    public void testMonths_1_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals(1, type.size());
    }

    public void testMonths_2_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertNotNull(type);
    }

    public void testMonths_3_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertEquals("months", type.getName());
    }

    public void testMonths_4_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertNotNull(type);
    }

    public void testMonths_5_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertNotNull(type);
    }

    public void testMonths_6_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertNotNull(type);
    }

    public void testMonths_7_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertNotNull(type);
    }

    public void testMonths_8_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertNotNull(type);
    }

    public void testMonths_9_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertNotNull(type);
    }

    public void testMonths_10_oe() throws Exception {
        PeriodType type = PeriodType.months();
        assertNotNull(type);
    }

    public void testWeeks_1_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertEquals(1, type.size());
    }

    public void testWeeks_2_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertNotNull(type);
    }

    public void testWeeks_4_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertNotNull(type);
    }

    public void testWeeks_5_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertNotNull(type);
    }

    public void testWeeks_6_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertNotNull(type);
    }

    public void testWeeks_7_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertNotNull(type);
    }

    public void testWeeks_8_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertNotNull(type);
    }

    public void testWeeks_9_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertNotNull(type);
    }

    public void testWeeks_10_oe() throws Exception {
        PeriodType type = PeriodType.weeks();
        assertNotNull(type);
    }

    public void testDays_1_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals(1, type.size());
    }

    public void testDays_2_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertNotNull(type);
    }

    public void testDays_3_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertEquals("days", type.getName());
    }

    public void testDays_5_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertNotNull(type);
    }

    public void testDays_6_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertNotNull(type);
    }

    public void testDays_7_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertNotNull(type);
    }

    public void testDays_10_oe() throws Exception {
        PeriodType type = PeriodType.days();
        assertNotNull(type);
    }

    public void testHours_1_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals(1, type.size());
    }

    public void testHours_2_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertNotNull(type);
    }

    public void testHours_3_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertEquals("Hour", type.getName());
    }

    public void testHours_4_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertNotNull(type);
    }

    public void testHours_5_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertNotNull(type);
    }

    public void testHours_6_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertNotNull(type);
    }

    public void testHours_8_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertNotNull(type);
    }

    public void testHours_9_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertNotNull(type);
    }

    public void testHours_10_oe() throws Exception {
        PeriodType type = PeriodType.hours();
        assertNotNull(type);
    }

    public void testMinutes_2_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertNotNull(type);
    }

    public void testMinutes_4_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertNotNull(type);
    }

    public void testMinutes_5_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertNotNull(type);
    }

    public void testMinutes_7_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertNotNull(type);
    }

    public void testMinutes_8_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertNotNull(type);
    }

    public void testMinutes_9_oe() throws Exception {
        PeriodType type = PeriodType.minutes();
        assertNotNull(type);
    }

    public void testSeconds_1_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertEquals(1, type.size());
    }

    public void testSeconds_2_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertNotNull(type);
    }

    public void testSeconds_4_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertNotNull(type);
    }

    public void testSeconds_5_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertNotNull(type);
    }

    public void testSeconds_6_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertNotNull(type);
    }

    public void testSeconds_9_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertNotNull(type);
    }

    public void testSeconds_10_oe() throws Exception {
        PeriodType type = PeriodType.seconds();
        assertNotNull(type);
    }

    public void testMillis_1_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals(1, type.size());
    }

    public void testMillis_2_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertNotNull(type);
    }

    public void testMillis_3_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertEquals("millis", type.getName());
    }

    public void testMillis_4_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertNotNull(type);
    }

    public void testMillis_5_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertNotNull(type);
    }

    public void testMillis_6_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertNotNull(type);
    }

    public void testMillis_8_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertNotNull(type);
    }

    public void testMillis_10_oe() throws Exception {
        PeriodType type = PeriodType.millis();
        assertNotNull(type);
    }

    public void testForFields1_1_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        assertNotNull(type);
    }

    public void testForFields1_2_oe() throws Exception {
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.years(),
        });
        type = PeriodType.forFields(new DurationFieldType[] {
            DurationFieldType.months(),
        });
        assertNotNull(type);
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
        assertEquals("Weeks", type.getName());
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
        assertEquals("Days", type.getName());
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
        assertEquals("Minutes", type.getName());
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
        assertEquals("Seconds", type.getName());
    }

    public void testForFields2_1_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(2, type.size());
    }

    public void testForFields2_2_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertNotNull(type);
    }

    public void testForFields2_4_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.years(),
            DurationFieldType.hours(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals("Years/Hours", type.getName());
    }

    public void testForFields3_1_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals(2, type.size());
    }

    public void testForFields3_3_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertNotNull(type);
    }

    public void testForFields3_4_oe() throws Exception {
        DurationFieldType[] types = new DurationFieldType[] {
            DurationFieldType.months(),
            DurationFieldType.weeks(),
        };
        PeriodType type = PeriodType.forFields(types);
        assertEquals("Months/Weeks", type.getName());
    }

    public void testMaskYears_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals(1, type.size());
    }

    public void testMaskYears_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertNotNull(PeriodType.months());
    }

    public void testMaskYears_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertNotNull(PeriodType.weeks());
    }

    public void testMaskYears_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals("Days", type.getName());
    }

    public void testMaskYears_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertNotNull(PeriodType.hours());
    }

    public void testMaskYears_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertNotNull(PeriodType.minutes());
    }

    public void testMaskYears_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertNotNull(PeriodType.seconds());
    }

    public void testMaskYears_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals("Millis", type.getName());
    }

    public void testMaskYears_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertNotNull(type);
    }

    public void testMaskYears_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertNotNull(type);
    }

    public void testMaskYears_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertNotNull(type);
    }

    public void testMaskYears_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertNotNull(type);
    }

    public void testMaskYears_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertEquals("Standard", type.getName());
    }

    public void testMaskYears_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        assertNotNull(type);
    }

    public void testMaskMonths_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals(1, type.size());
    }

    public void testMaskMonths_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertNotNull(PeriodType.years());
    }

    public void testMaskMonths_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertNotNull(PeriodType.weeks());
    }

    public void testMaskMonths_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("Days", type.getName());
    }

    public void testMaskMonths_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertNotNull(PeriodType.hours());
    }

    public void testMaskMonths_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertNotNull(PeriodType.minutes());
    }

    public void testMaskMonths_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertNotNull(PeriodType.seconds());
    }

    public void testMaskMonths_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("Millis", type.getName());
    }

    public void testMaskMonths_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskMonths_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskMonths_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskMonths_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskMonths_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskMonths_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskMonths_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("Standard", type.getName());
    }

    public void testMaskMonths_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMonthsRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskWeeks_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals(7, type.size());
    }

    public void testMaskWeeks_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("Years", type.getName());
    }

    public void testMaskWeeks_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertNotNull(PeriodType.months());
    }

    public void testMaskWeeks_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("Days", type.getName());
    }

    public void testMaskWeeks_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertNotNull(PeriodType.hours());
    }

    public void testMaskWeeks_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertNotNull(PeriodType.minutes());
    }

    public void testMaskWeeks_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertNotNull(PeriodType.seconds());
    }

    public void testMaskWeeks_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("Millis", type.getName());
    }

    public void testMaskWeeks_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskWeeks_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskWeeks_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskWeeks_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskWeeks_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskWeeks_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskWeeks_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("Standard", type.getName());
    }

    public void testMaskWeeks_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withWeeksRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskDays_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals(1, type.size());
    }

    public void testMaskDays_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals("Years", type.getName());
    }

    public void testMaskDays_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertNotNull(PeriodType.months());
    }

    public void testMaskDays_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertNotNull(PeriodType.weeks());
    }

    public void testMaskDays_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertNotNull(PeriodType.hours());
    }

    public void testMaskDays_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertNotNull(PeriodType.minutes());
    }

    public void testMaskDays_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertNotNull(PeriodType.seconds());
    }

    public void testMaskDays_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals("Millis", type.getName());
    }

    public void testMaskDays_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskDays_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertNotNull(type);
    }

    public void testMaskDays_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertNotNull(type);
    }

    public void testMaskDays_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertNotNull(type);
    }

    public void testMaskDays_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertNotNull(type);
    }

    public void testMaskDays_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskDays_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withDaysRemoved();
        assertEquals("Standard", type.getName());
    }

    public void testMaskHours_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals(1, type.size());
    }

    public void testMaskHours_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("Years", type.getName());
    }

    public void testMaskHours_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertNotNull(PeriodType.months());
    }

    public void testMaskHours_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertNotNull(PeriodType.weeks());
    }

    public void testMaskHours_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("Days", type.getName());
    }

    public void testMaskHours_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertNotNull(PeriodType.minutes());
    }

    public void testMaskHours_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("Seconds", type.getName());
    }

    public void testMaskHours_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("Millis", type.getName());
    }

    public void testMaskHours_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("PeriodType[any]", type.toString());
    }

    public void testMaskHours_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("PeriodType[any]", type.toString());
    }

    public void testMaskHours_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("PeriodType[any]", type.toString());
    }

    public void testMaskHours_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("PeriodType[any]", type.toString());
    }

    public void testMaskHours_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("PeriodType[any]", type.toString());
    }

    public void testMaskHours_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("PeriodType[any]", type.toString());
    }

    public void testMaskHours_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("Standard", type.getName());
    }

    public void testMaskHours_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved();
        assertEquals("PeriodType[any]", type.toString());
    }

    public void testMaskMinutes_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals(1, type.size());
    }

    public void testMaskMinutes_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("Years", type.getName());
    }

    public void testMaskMinutes_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertNotNull(PeriodType.months());
    }

    public void testMaskMinutes_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertNotNull(PeriodType.weeks());
    }

    public void testMaskMinutes_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("Days", type.getName());
    }

    public void testMaskMinutes_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertNotNull(PeriodType.hours());
    }

    public void testMaskMinutes_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("Seconds", type.getName());
    }

    public void testMaskMinutes_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("Millis", type.getName());
    }

    public void testMaskMinutes_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("PeriodType[year,month,day,hour,minute]", type.toString());
    }

    public void testMaskMinutes_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("PeriodType[year,month,day,hour,minute]", type.toString());
    }

    public void testMaskMinutes_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("PeriodType[year,month,day,hour,minute]", type.toString());
    }

    public void testMaskMinutes_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("PeriodType[year,month,day,hour,minute]", type.toString());
    }

    public void testMaskMinutes_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("PeriodType[year,month,day,hour,minute]", type.toString());
    }

    public void testMaskMinutes_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("PeriodType[year,month,day,hour,minute]", type.toString());
    }

    public void testMaskMinutes_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("Standard", type.getName());
    }

    public void testMaskMinutes_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMinutesRemoved();
        assertEquals("PeriodType[year,month,day,hour,minute]", type.toString());
    }

    public void testMaskSeconds_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals(7, type.size());
    }

    public void testMaskSeconds_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("Years", type.getName());
    }

    public void testMaskSeconds_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertNotNull(PeriodType.months());
    }

    public void testMaskSeconds_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertNotNull(PeriodType.weeks());
    }

    public void testMaskSeconds_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("Days", type.getName());
    }

    public void testMaskSeconds_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertNotNull(PeriodType.hours());
    }

    public void testMaskSeconds_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertNotNull(PeriodType.minutes());
    }

    public void testMaskSeconds_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("Millis", type.getName());
    }

    public void testMaskSeconds_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertNotNull(type);
    }

    public void testMaskSeconds_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("PeriodType[year,month,day,hour,minute]", type.toString());
    }

    public void testMaskSeconds_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("PeriodType[year,month,day]", type.toString());
    }

    public void testMaskSeconds_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("PeriodType[year,month,day]", type.toString());
    }

    public void testMaskSeconds_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("PeriodType[year,month,day]", type.toString());
    }

    public void testMaskSeconds_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("PeriodType[year,month,day,hour,minute]", type.toString());
    }

    public void testMaskSeconds_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertEquals("Standard", type.getName());
    }

    public void testMaskSeconds_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withSecondsRemoved();
        assertNotNull(type);
    }

    public void testMaskMillis_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals(1, type.size());
    }

    public void testMaskMillis_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals("Years", type.getName());
    }

    public void testMaskMillis_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertNotNull(PeriodType.months());
    }

    public void testMaskMillis_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertNotNull(PeriodType.weeks());
    }

    public void testMaskMillis_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals("Days", type.getName());
    }

    public void testMaskMillis_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertNotNull(PeriodType.hours());
    }

    public void testMaskMillis_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertNotNull(PeriodType.minutes());
    }

    public void testMaskMillis_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals("Seconds", type.getName());
    }

    public void testMaskMillis_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertNotNull(type);
    }

    public void testMaskMillis_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertNotNull(type);
    }

    public void testMaskMillis_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertNotNull(type);
    }

    public void testMaskMillis_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertNotNull(type);
    }

    public void testMaskMillis_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertNotNull(type);
    }

    public void testMaskMillis_15_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertEquals("Standard", type.getName());
    }

    public void testMaskMillis_16_oe() throws Exception {
        PeriodType type = PeriodType.standard().withMillisRemoved();
        assertNotNull(type);
    }

    public void testMaskHoursMinutesSeconds_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals(1, type.size());
    }

    public void testMaskHoursMinutesSeconds_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertNotNull(PeriodType.years());
    }

    public void testMaskHoursMinutesSeconds_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertNotNull(PeriodType.months());
    }

    public void testMaskHoursMinutesSeconds_4_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertNotNull(PeriodType.weeks());
    }

    public void testMaskHoursMinutesSeconds_5_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals("Days", type.getName());
    }

    public void testMaskHoursMinutesSeconds_6_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals("Millis", type.getName());
    }

    public void testMaskHoursMinutesSeconds_7_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertNotNull(type);
    }

    public void testMaskHoursMinutesSeconds_8_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertNotNull(type);
    }

    public void testMaskHoursMinutesSeconds_9_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertNotNull(type);
    }

    public void testMaskHoursMinutesSeconds_10_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals("PnYnM", type.toString());
    }

    public void testMaskHoursMinutesSeconds_11_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertNotNull(type);
    }

    public void testMaskHoursMinutesSeconds_12_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertNotNull(type);
    }

    public void testMaskHoursMinutesSeconds_13_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertEquals("Standard", type.getName());
    }

    public void testMaskHoursMinutesSeconds_14_oe() throws Exception {
        PeriodType type = PeriodType.standard().withHoursRemoved().withMinutesRemoved().withSecondsRemoved();
        assertNotNull(type);
    }

    public void testMaskTwice1_1_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        assertNotNull(type2);
    }

    public void testMaskTwice1_2_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        assertNotNull(type2);
    }

    public void testMaskTwice1_3_oe() throws Exception {
        PeriodType type = PeriodType.standard().withYearsRemoved();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.standard().withMonthsRemoved();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.standard().withWeeksRemoved();
        type2 = type.withWeeksRemoved();
        assertNotNull(type2);
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
        assertNotSame(type, type2);
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
        assertNotSame(type, type2);
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
        assertNotSame(type, type2);
    }

    public void testMaskTwice2_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        assertNotNull(type2);
    }

    public void testMaskTwice2_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime();
        PeriodType type2 = type.withYearsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withMonthsRemoved();
        
        type = PeriodType.dayTime();
        type2 = type.withWeeksRemoved();
        assertNotNull(type2);
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
        assertNotNull(type2);
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
        assertNotNull(type2);
    }

    public void testEquals_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertNotNull(type);
    }

    public void testEquals_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertNotNull(type);
    }

    public void testEquals_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertNotNull(type);
    }

    public void testEquals_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertNotNull(type);
    }

    public void testHashCode_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
        assertNotNull(type);
    }

    public void testIsSupported_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(true, type.isSupported());
    }

    public void testIsSupported_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(true, type.isSupported());
    }

    public void testIsSupported_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(true, type.isSupported());
    }

    public void testIsSupported_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(true, type.isSupported());
    }

    public void testIsSupported_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(true, type.isSupported());
    }

    public void testIsSupported_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(true, type.isSupported());
    }

    public void testIsSupported_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(true, type.isSupported());
    }

    public void testIsSupported_8_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(true, type.isSupported());
    }

    public void testIndexOf_1_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(-1, type.indexOf());
    }

    public void testIndexOf_2_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(-1, type.indexOf());
    }

    public void testIndexOf_3_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(-1, type.indexOf());
    }

    public void testIndexOf_4_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(-1, type.indexOf());
    }

    public void testIndexOf_5_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(-1, type.indexOf());
    }

    public void testIndexOf_6_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(-1, type.indexOf());
    }

    public void testIndexOf_7_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(-1, type.indexOf());
    }

    public void testIndexOf_8_oe() throws Exception {
        PeriodType type = PeriodType.dayTime().withMillisRemoved();
// incorrect assertion         assertEquals(-1, type.indexOf());
    }

}
