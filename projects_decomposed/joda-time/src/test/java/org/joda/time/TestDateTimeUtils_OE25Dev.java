/*
 *  Copyright 2001-2013 Stephen Colebourne
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

import java.lang.reflect.Modifier;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.ProtectionDomain;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DateTimeUtils.MillisProvider;
import org.joda.time.base.AbstractInstant;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * This class is a Junit unit test for DateTimeUtils.
 *
 * @author Stephen Colebourne
 */
public class TestDateTimeUtils_OE25Dev extends TestCase {

    private static final GJChronology GJ = GJChronology.getInstance();
    private static final boolean OLD_JDK;
    static {
        String str = System.getProperty("java.version");
        boolean old = true;
        if (str.length() > 3 &&
            str.charAt(0) == '1' &&
            str.charAt(1) == '.' &&
            (str.charAt(2) == '4' || str.charAt(2) == '5' || str.charAt(2) == '6')) {
            old = false;
        }
        OLD_JDK = old;
    }

    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    
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
        
    private static final Policy RESTRICT;
    private static final Policy ALLOW;
    static {
        // don't call Policy.getPolicy()
        RESTRICT = new Policy() {
            @Override
            public PermissionCollection getPermissions(CodeSource codesource) {
                Permissions p = new Permissions();
                p.add(new AllPermission());  // enable everything
                return p;
            }
            @Override
            public void refresh() {
            }
            @Override
            public boolean implies(ProtectionDomain domain, Permission permission) {
                if (permission instanceof JodaTimePermission) {
                    return false;
                }
                return true;
//                return super.implies(domain, permission);
            }
        };
        ALLOW = new Policy() {
            @Override
            public PermissionCollection getPermissions(CodeSource codesource) {
                Permissions p = new Permissions();
                p.add(new AllPermission());  // enable everything
                return p;
            }
            @Override
            public void refresh() {
            }
        };
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateTimeUtils_OE25Dev.class);
    }

    public TestDateTimeUtils_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testSystemMillisSecurity() {
        if (OLD_JDK) {
            return;
        }
        try {
            try {
                Policy.setPolicy(RESTRICT);
                System.setSecurityManager(new SecurityManager());
                DateTimeUtils.setCurrentMillisSystem();
                fail();
            } catch (SecurityException ex) {
                // ok
            } finally {
                System.setSecurityManager(null);
                Policy.setPolicy(ALLOW);
            }
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testFixedMillisSecurity() {
        if (OLD_JDK) {
            return;
        }
        try {
            try {
                Policy.setPolicy(RESTRICT);
                System.setSecurityManager(new SecurityManager());
                DateTimeUtils.setCurrentMillisFixed(0L);
                fail();
            } catch (SecurityException ex) {
                // ok
            } finally {
                System.setSecurityManager(null);
                Policy.setPolicy(ALLOW);
            }
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testOffsetMillisSecurity() {
        if (OLD_JDK) {
            return;
        }
        try {
            try {
                Policy.setPolicy(RESTRICT);
                System.setSecurityManager(new SecurityManager());
                DateTimeUtils.setCurrentMillisOffset(-24 * 60 *  60 * 1000);
                fail();
            } catch (SecurityException ex) {
                // ok
            } finally {
                System.setSecurityManager(null);
                Policy.setPolicy(ALLOW);
            }
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
        }
    }

    //-----------------------------------------------------------------------
    public void testMillisProvider() {
        try {
            DateTimeUtils.setCurrentMillisProvider(new MillisProvider() {
                public long getMillis() {
                    return 1L;
                }
            });
            assertEquals(1L,DateTimeUtils.currentTimeMillis());
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
        }
    }

    public void testMillisProvider_null() {
        try {
            DateTimeUtils.setCurrentMillisProvider(null);
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void testMillisProviderSecurity() {
        if (OLD_JDK) {
            return;
        }
        try {
            try {
                Policy.setPolicy(RESTRICT);
                System.setSecurityManager(new SecurityManager());
                DateTimeUtils.setCurrentMillisProvider(new MillisProvider() {
                    public long getMillis() {
                        return 0L;
                    }
                });
                fail();
            } catch (SecurityException ex) {
                // ok
            } finally {
                System.setSecurityManager(null);
                Policy.setPolicy(ALLOW);
            }
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
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

    public void testTest_1_oe() {
        assertEquals("2002-06-09T00:00:00.000Z",new Instant(TEST_TIME_NOW).toString());
    }

    public void testTest_2_oe() {
        assertEquals("2002-04-05T12:24:00.000Z",new Instant(TEST_TIME1).toString());
    }

    public void testTest_3_oe() {
        assertEquals("2003-05-06T14:28:00.000Z",new Instant(TEST_TIME2).toString());
    }

    public void testClass_1_oe() {
        Class<?> cls = DateTimeUtils.class;
        assertEquals(true,Modifier.isPublic(cls.getModifiers()));
    }

    public void testClass_2_oe() {
        Class<?> cls = DateTimeUtils.class;
        assertEquals(false,Modifier.isFinal(cls.getModifiers()));
    }

    public void testClass_3_oe() {
        Class<?> cls = DateTimeUtils.class;
        
        assertEquals(1,cls.getDeclaredConstructors().length);
    }

    public void testClass_4_oe() {
        Class<?> cls = DateTimeUtils.class;
        
        assertEquals(true,Modifier.isProtected(cls.getDeclaredConstructors()[0].getModifiers()));
    }

    public void testSystemMillis_1_oe() {
        long nowSystem = System.currentTimeMillis();
        long now = DateTimeUtils.currentTimeMillis();
        assertTrue((now >= nowSystem));
    }

    public void testSystemMillis_2_oe() {
        long nowSystem = System.currentTimeMillis();
        long now = DateTimeUtils.currentTimeMillis();
        assertTrue((now - nowSystem)< 10000L);
    }

    public void testFixedMillis_4_oe() {
        try {
            DateTimeUtils.setCurrentMillisFixed(0L);
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
        }
        long nowSystem = System.currentTimeMillis();
        long now = DateTimeUtils.currentTimeMillis();
        assertTrue((now >= nowSystem));
    }

    public void testFixedMillis_5_oe() {
        try {
            DateTimeUtils.setCurrentMillisFixed(0L);
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
        }
        long nowSystem = System.currentTimeMillis();
        long now = DateTimeUtils.currentTimeMillis();
        assertTrue((now - nowSystem)< 10000L);
    }

    public void testOffsetMillis_4_oe() {
        try {
            DateTimeUtils.setCurrentMillisOffset(-24 * 60 *  60 * 1000);
            long nowSystem = System.currentTimeMillis();
            long now = DateTimeUtils.currentTimeMillis();
            long nowAdjustDay = now + (24 * 60 *  60 * 1000);
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
        }
        long nowSystem = System.currentTimeMillis();
        long now = DateTimeUtils.currentTimeMillis();
        assertTrue((now >= nowSystem));
    }

    public void testOffsetMillis_5_oe() {
        try {
            DateTimeUtils.setCurrentMillisOffset(-24 * 60 *  60 * 1000);
            long nowSystem = System.currentTimeMillis();
            long now = DateTimeUtils.currentTimeMillis();
            long nowAdjustDay = now + (24 * 60 *  60 * 1000);
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
        }
        long nowSystem = System.currentTimeMillis();
        long now = DateTimeUtils.currentTimeMillis();
        assertTrue((now - nowSystem)< 10000L);
    }

    public void testOffsetMillisToZero_1_oe() {
        long now1 = 0L;
        try {
            DateTimeUtils.setCurrentMillisOffset(0);
            now1 = DateTimeUtils.currentTimeMillis();
        } finally {
            DateTimeUtils.setCurrentMillisSystem();
        }
        long now2 = DateTimeUtils.currentTimeMillis();
        assertEquals(Math.abs(now1 - now2)< 100,true);
    }

    public void testGetInstantMillis_RI_1_oe() {
        Instant i = new Instant(123L);
        assertEquals(123L,DateTimeUtils.getInstantMillis(i));
    }

    public void testGetInstantChronology_RI_1_oe() {
        DateTime dt = new DateTime(123L, BuddhistChronology.getInstance());
        assertEquals(BuddhistChronology.getInstance(),DateTimeUtils.getInstantChronology(dt));
    }

    public void testGetInstantChronology_RI_2_oe() {
        DateTime dt = new DateTime(123L, BuddhistChronology.getInstance());
        
        Instant i = new Instant(123L);
        assertEquals(ISOChronology.getInstanceUTC(),DateTimeUtils.getInstantChronology(i));
    }

    public void testGetInstantChronology_RI_3_oe() {
        DateTime dt = new DateTime(123L, BuddhistChronology.getInstance());
        
        Instant i = new Instant(123L);
        
        AbstractInstant ai = new AbstractInstant() {
            public long getMillis() {
                return 0L;
            }
            public Chronology getChronology() {
                return null; // testing for this
            }
        };
        assertEquals(ISOChronology.getInstance(),DateTimeUtils.getInstantChronology(ai));
    }

    public void testGetInstantChronology_RI_4_oe() {
        DateTime dt = new DateTime(123L, BuddhistChronology.getInstance());
        
        Instant i = new Instant(123L);
        
        AbstractInstant ai = new AbstractInstant() {
            public long getMillis() {
                return 0L;
            }
            public Chronology getChronology() {
                return null; // testing for this
            }
        };
        
        assertEquals(ISOChronology.getInstance(),DateTimeUtils.getInstantChronology(null));
    }

    public void testGetIntervalChronology_RInterval_1_oe() {
        Interval dt = new Interval(123L, 456L, BuddhistChronology.getInstance());
        assertEquals(BuddhistChronology.getInstance(),DateTimeUtils.getIntervalChronology(dt));
    }

    public void testGetIntervalChronology_RInterval_2_oe() {
        Interval dt = new Interval(123L, 456L, BuddhistChronology.getInstance());
        
        assertEquals(ISOChronology.getInstance(),DateTimeUtils.getIntervalChronology(null));
    }

    public void testGetIntervalChronology_RInterval_3_oe() {
        Interval dt = new Interval(123L, 456L, BuddhistChronology.getInstance());
        
        
        MutableInterval ai = new MutableInterval() {
            private static final long serialVersionUID = 1L;

            @Override
            public Chronology getChronology() {
                return null; // testing for this
            }
        };
        assertEquals(ISOChronology.getInstance(),DateTimeUtils.getIntervalChronology(ai));
    }

    public void testGetIntervalChronology_RI_RI_1_oe() {
        DateTime dt1 = new DateTime(123L, BuddhistChronology.getInstance());
        DateTime dt2 = new DateTime(123L, CopticChronology.getInstance());
        assertEquals(BuddhistChronology.getInstance(),DateTimeUtils.getIntervalChronology(dt1,dt2));
    }

    public void testGetIntervalChronology_RI_RI_2_oe() {
        DateTime dt1 = new DateTime(123L, BuddhistChronology.getInstance());
        DateTime dt2 = new DateTime(123L, CopticChronology.getInstance());
        assertEquals(BuddhistChronology.getInstance(),DateTimeUtils.getIntervalChronology(dt1,null));
    }

    public void testGetIntervalChronology_RI_RI_3_oe() {
        DateTime dt1 = new DateTime(123L, BuddhistChronology.getInstance());
        DateTime dt2 = new DateTime(123L, CopticChronology.getInstance());
        assertEquals(CopticChronology.getInstance(),DateTimeUtils.getIntervalChronology(null,dt2));
    }

    public void testGetIntervalChronology_RI_RI_4_oe() {
        DateTime dt1 = new DateTime(123L, BuddhistChronology.getInstance());
        DateTime dt2 = new DateTime(123L, CopticChronology.getInstance());
        assertEquals(ISOChronology.getInstance(),DateTimeUtils.getIntervalChronology(null,null));
    }

    public void testGetReadableInterval_ReadableInterval_1_oe() {
        ReadableInterval input = new Interval(0, 100L);
        assertEquals(input,DateTimeUtils.getReadableInterval(input));
    }

    public void testGetChronology_Chronology_1_oe() {
        assertEquals(BuddhistChronology.getInstance(),DateTimeUtils.getChronology(BuddhistChronology.getInstance()));
    }

    public void testGetChronology_Chronology_2_oe() {
        assertEquals(ISOChronology.getInstance(),DateTimeUtils.getChronology(null));
    }

    public void testGetZone_Zone_1_oe() {
        assertEquals(PARIS,DateTimeUtils.getZone(PARIS));
    }

    public void testGetZone_Zone_2_oe() {
        assertEquals(DateTimeZone.getDefault(),DateTimeUtils.getZone(null));
    }

    public void testGetPeriodType_PeriodType_1_oe() {
        assertEquals(PeriodType.dayTime(),DateTimeUtils.getPeriodType(PeriodType.dayTime()));
    }

    public void testGetPeriodType_PeriodType_2_oe() {
        assertEquals(PeriodType.standard(),DateTimeUtils.getPeriodType(null));
    }

    public void testGetDurationMillis_RI_1_oe() {
        Duration dur = new Duration(123L);
        assertEquals(123L,DateTimeUtils.getDurationMillis(dur));
    }

    public void testGetDurationMillis_RI_2_oe() {
        Duration dur = new Duration(123L);
        assertEquals(0L,DateTimeUtils.getDurationMillis(null));
    }

    public void testIsContiguous_RP_1_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9);
        assertEquals(true,DateTimeUtils.isContiguous(ymd));
    }

    public void testIsContiguous_RP_2_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0);
        assertEquals(true,DateTimeUtils.isContiguous(tod));
    }

    public void testIsContiguous_RP_3_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0);
        Partial year = new Partial(DateTimeFieldType.year(), 2005);
        assertEquals(true,DateTimeUtils.isContiguous(year));
    }

    public void testIsContiguous_RP_4_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0);
        Partial year = new Partial(DateTimeFieldType.year(), 2005);
        Partial hourOfDay = new Partial(DateTimeFieldType.hourOfDay(), 12);
        assertEquals(true,DateTimeUtils.isContiguous(hourOfDay));
    }

    public void testIsContiguous_RP_5_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0);
        Partial year = new Partial(DateTimeFieldType.year(), 2005);
        Partial hourOfDay = new Partial(DateTimeFieldType.hourOfDay(), 12);
        Partial yearHour = year.with(DateTimeFieldType.hourOfDay(), 12);
        assertEquals(false,DateTimeUtils.isContiguous(yearHour));
    }

    public void testIsContiguous_RP_6_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0);
        Partial year = new Partial(DateTimeFieldType.year(), 2005);
        Partial hourOfDay = new Partial(DateTimeFieldType.hourOfDay(), 12);
        Partial yearHour = year.with(DateTimeFieldType.hourOfDay(), 12);
        Partial ymdd = new Partial(ymd).with(DateTimeFieldType.dayOfWeek(), 2);
        assertEquals(false,DateTimeUtils.isContiguous(ymdd));
    }

    public void testIsContiguous_RP_7_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0);
        Partial year = new Partial(DateTimeFieldType.year(), 2005);
        Partial hourOfDay = new Partial(DateTimeFieldType.hourOfDay(), 12);
        Partial yearHour = year.with(DateTimeFieldType.hourOfDay(), 12);
        Partial ymdd = new Partial(ymd).with(DateTimeFieldType.dayOfWeek(), 2);
        Partial dd = new Partial(DateTimeFieldType.dayOfMonth(), 13).with(DateTimeFieldType.dayOfWeek(), 5);
        assertEquals(false,DateTimeUtils.isContiguous(dd));
    }

    public void testIsContiguous_RP_GJChronology_1_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9, GJ);
        assertEquals(true,DateTimeUtils.isContiguous(ymd));
    }

    public void testIsContiguous_RP_GJChronology_2_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9, GJ);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0, GJ);
        assertEquals(true,DateTimeUtils.isContiguous(tod));
    }

    public void testIsContiguous_RP_GJChronology_3_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9, GJ);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0, GJ);
        Partial year = new Partial(DateTimeFieldType.year(), 2005, GJ);
        assertEquals(true,DateTimeUtils.isContiguous(year));
    }

    public void testIsContiguous_RP_GJChronology_4_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9, GJ);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0, GJ);
        Partial year = new Partial(DateTimeFieldType.year(), 2005, GJ);
        Partial hourOfDay = new Partial(DateTimeFieldType.hourOfDay(), 12, GJ);
        assertEquals(true,DateTimeUtils.isContiguous(hourOfDay));
    }

    public void testIsContiguous_RP_GJChronology_5_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9, GJ);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0, GJ);
        Partial year = new Partial(DateTimeFieldType.year(), 2005, GJ);
        Partial hourOfDay = new Partial(DateTimeFieldType.hourOfDay(), 12, GJ);
        Partial yearHour = year.with(DateTimeFieldType.hourOfDay(), 12);
        assertEquals(false,DateTimeUtils.isContiguous(yearHour));
    }

    public void testIsContiguous_RP_GJChronology_6_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9, GJ);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0, GJ);
        Partial year = new Partial(DateTimeFieldType.year(), 2005, GJ);
        Partial hourOfDay = new Partial(DateTimeFieldType.hourOfDay(), 12, GJ);
        Partial yearHour = year.with(DateTimeFieldType.hourOfDay(), 12);
        Partial ymdd = new Partial(ymd).with(DateTimeFieldType.dayOfWeek(), 2);
        assertEquals(false,DateTimeUtils.isContiguous(ymdd));
    }

    public void testIsContiguous_RP_GJChronology_7_oe() {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 9, GJ);
        TimeOfDay tod = new TimeOfDay(12, 20, 30, 0, GJ);
        Partial year = new Partial(DateTimeFieldType.year(), 2005, GJ);
        Partial hourOfDay = new Partial(DateTimeFieldType.hourOfDay(), 12, GJ);
        Partial yearHour = year.with(DateTimeFieldType.hourOfDay(), 12);
        Partial ymdd = new Partial(ymd).with(DateTimeFieldType.dayOfWeek(), 2);
        Partial dd = new Partial(DateTimeFieldType.dayOfMonth(), 13).with(DateTimeFieldType.dayOfWeek(), 5);
        assertEquals(false,DateTimeUtils.isContiguous(dd));
    }

    public void test_julianDay_1_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        assertEquals(2440587.5d,DateTimeUtils.toJulianDay(base.getMillis()),0.0001d);
    }

    public void test_julianDay_2_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        assertEquals(2440588,DateTimeUtils.toJulianDayNumber(base.getMillis()));
    }

    public void test_julianDay_3_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        assertEquals(base.getMillis(),DateTimeUtils.fromJulianDay(2440587.5d));
    }

    public void test_julianDay_4_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        assertEquals(2440587.75d,DateTimeUtils.toJulianDay(base.getMillis()),0.0001d);
    }

    public void test_julianDay_5_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        assertEquals(2440588,DateTimeUtils.toJulianDayNumber(base.getMillis()));
    }

    public void test_julianDay_6_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        assertEquals(base.getMillis(),DateTimeUtils.fromJulianDay(2440587.75d));
    }

    public void test_julianDay_7_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        assertEquals(2440588d,DateTimeUtils.toJulianDay(base.getMillis()),0.0001d);
    }

    public void test_julianDay_8_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        assertEquals(2440588,DateTimeUtils.toJulianDayNumber(base.getMillis()));
    }

    public void test_julianDay_9_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        assertEquals(base.getMillis(),DateTimeUtils.fromJulianDay(2440588d));
    }

    public void test_julianDay_10_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        assertEquals(2440588.25d,DateTimeUtils.toJulianDay(base.getMillis()),0.0001d);
    }

    public void test_julianDay_11_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        assertEquals(2440588,DateTimeUtils.toJulianDayNumber(base.getMillis()));
    }

    public void test_julianDay_12_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        assertEquals(base.getMillis(),DateTimeUtils.fromJulianDay(2440588.25d));
    }

    public void test_julianDay_13_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        assertEquals(2440588.5d,DateTimeUtils.toJulianDay(base.getMillis()),0.0001d);
    }

    public void test_julianDay_14_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        assertEquals(2440589,DateTimeUtils.toJulianDayNumber(base.getMillis()));
    }

    public void test_julianDay_15_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        assertEquals(base.getMillis(),DateTimeUtils.fromJulianDay(2440588.5d));
    }

    public void test_julianDay_16_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = new DateTime(2012, 8, 31, 23, 50, DateTimeZone.UTC);
        assertEquals(2456171.4930555555,DateTimeUtils.toJulianDay(base.getMillis()),0.0001d);
    }

    public void test_julianDay_17_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = new DateTime(2012, 8, 31, 23, 50, DateTimeZone.UTC);
        assertEquals(2456171,DateTimeUtils.toJulianDayNumber(base.getMillis()));
    }

    public void test_julianDay_18_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = new DateTime(2012, 8, 31, 23, 50, DateTimeZone.UTC);
        
        base = new DateTime(-4713, 1, 1, 12, 0, JulianChronology.getInstanceUTC());
        assertEquals(0d,DateTimeUtils.toJulianDay(base.getMillis()),0.0001d);
    }

    public void test_julianDay_19_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = new DateTime(2012, 8, 31, 23, 50, DateTimeZone.UTC);
        
        base = new DateTime(-4713, 1, 1, 12, 0, JulianChronology.getInstanceUTC());
        assertEquals(0,DateTimeUtils.toJulianDayNumber(base.getMillis()));
    }

    public void test_julianDay_20_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = new DateTime(2012, 8, 31, 23, 50, DateTimeZone.UTC);
        
        base = new DateTime(-4713, 1, 1, 12, 0, JulianChronology.getInstanceUTC());
        assertEquals(base.getMillis(),DateTimeUtils.fromJulianDay(0d));
    }

    public void test_julianDay_21_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = new DateTime(2012, 8, 31, 23, 50, DateTimeZone.UTC);
        
        base = new DateTime(-4713, 1, 1, 12, 0, JulianChronology.getInstanceUTC());
        
        base = new DateTime(-4713, 1, 1, 0, 0, JulianChronology.getInstanceUTC());
        assertEquals(-0.5d,DateTimeUtils.toJulianDay(base.getMillis()),0.0001d);
    }

    public void test_julianDay_22_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = new DateTime(2012, 8, 31, 23, 50, DateTimeZone.UTC);
        
        base = new DateTime(-4713, 1, 1, 12, 0, JulianChronology.getInstanceUTC());
        
        base = new DateTime(-4713, 1, 1, 0, 0, JulianChronology.getInstanceUTC());
        assertEquals(0,DateTimeUtils.toJulianDayNumber(base.getMillis()));
    }

    public void test_julianDay_23_oe() {
        DateTime base = new DateTime(1970, 1, 1, 0, 0, DateTimeZone.UTC);
        
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = base.plusHours(6);
        
        base = new DateTime(2012, 8, 31, 23, 50, DateTimeZone.UTC);
        
        base = new DateTime(-4713, 1, 1, 12, 0, JulianChronology.getInstanceUTC());
        
        base = new DateTime(-4713, 1, 1, 0, 0, JulianChronology.getInstanceUTC());
        assertEquals(base.getMillis(),DateTimeUtils.fromJulianDay(-0.5d));
    }

}
