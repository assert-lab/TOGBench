/*
 *  Copyright 2001-2006 Stephen Colebourne
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
package org.joda.time.convert;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Interval;
import org.joda.time.JodaTimePermission;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadableInterval;
import org.joda.time.TimeOfDay;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class is a JUnit test for ConverterManager.
 *
 * @author Stephen Colebourne
 */
public class TestConverterManager_OE25Dev extends TestCase {
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
        return new TestSuite(TestConverterManager_OE25Dev_OE25Dev.class);
    }

    public TestConverterManager_OE25Dev(String name) {
        super(name);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    private static final int PARTIAL_SIZE = 7;

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    private static int DURATION_SIZE = 5;

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    private static int PERIOD_SIZE = 5;

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    private static int INTERVAL_SIZE = 3;

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testSingleton_1_oe() throws Exception {
        Class cls = ConverterManager.class;
        assertEquals(true,Modifier.isPublic(cls.getModifiers()));
    }

    public void testSingleton_2_oe() throws Exception {
        Class cls = ConverterManager.class;
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1,cls.getDeclaredConstructors().length);
    }

    public void testSingleton_3_oe() throws Exception {
        Class cls = ConverterManager.class;
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        assertEquals(true,Modifier.isProtected(con.getModifiers()));
    }

    public void testSingleton_4_oe() throws Exception {
        Class cls = ConverterManager.class;
        // removed other assertion
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        // removed other assertion
        // removed other assertion
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertEquals(true,Modifier.isPrivate(fld.getModifiers()));
    }

    public void testGetInstantConverter_1_oe() {
        InstantConverter c = ConverterManager.getInstance().getInstantConverter(new Long(0L));
        assertEquals(Long.class,c.getSupportedType());
    }

    public void testGetInstantConverter_2_oe() {
        InstantConverter c = ConverterManager.getInstance().getInstantConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(new DateTime());
        assertEquals(ReadableInstant.class,c.getSupportedType());
    }

    public void testGetInstantConverter_3_oe() {
        InstantConverter c = ConverterManager.getInstance().getInstantConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(new DateTime());
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter("");
        assertEquals(String.class,c.getSupportedType());
    }

    public void testGetInstantConverter_4_oe() {
        InstantConverter c = ConverterManager.getInstance().getInstantConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(new DateTime());
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter("");
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(new Date());
        assertEquals(Date.class,c.getSupportedType());
    }

    public void testGetInstantConverter_5_oe() {
        InstantConverter c = ConverterManager.getInstance().getInstantConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(new DateTime());
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter("");
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(new Date());
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(new GregorianCalendar());
        assertEquals(Calendar.class,c.getSupportedType());
    }

    public void testGetInstantConverter_6_oe() {
        InstantConverter c = ConverterManager.getInstance().getInstantConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(new DateTime());
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter("");
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(new Date());
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(new GregorianCalendar());
        // removed other assertion
        
        c = ConverterManager.getInstance().getInstantConverter(null);
        assertEquals(null,c.getSupportedType());
    }

    public void testGetInstantConverterRemovedNull_2_oe() {
        try {
            ConverterManager.getInstance().removeInstantConverter(NullConverter.INSTANCE);
            try {
                ConverterManager.getInstance().getInstantConverter(null);
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
        } finally {
            ConverterManager.getInstance().addInstantConverter(NullConverter.INSTANCE);
        }
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testGetInstantConverterOKMultipleMatches_2_oe() {
        InstantConverter c = new InstantConverter() {
            public long getInstantMillis(Object object, Chronology chrono) {return 0;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return ReadableDateTime.class;}
        };
        try {
            ConverterManager.getInstance().addInstantConverter(c);
            InstantConverter ok = ConverterManager.getInstance().getInstantConverter(new DateTime());
            // ReadableDateTime and ReadableInstant both match, but RI discarded as less specific
            // removed other assertion
        } finally {
            ConverterManager.getInstance().removeInstantConverter(c);
        }
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testGetInstantConverterBadMultipleMatches_2_oe() {
        InstantConverter c = new InstantConverter() {
            public long getInstantMillis(Object object, Chronology chrono) {return 0;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return Serializable.class;}
        };
        try {
            ConverterManager.getInstance().addInstantConverter(c);
            try {
                ConverterManager.getInstance().getInstantConverter(new DateTime());
                // removed other assertion
            } catch (IllegalStateException ex) {
                // Serializable and ReadableInstant both match, so cannot pick
            }
        } finally {
            ConverterManager.getInstance().removeInstantConverter(c);
        }
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testGetInstantConverters_1_oe() {
        InstantConverter[] array = ConverterManager.getInstance().getInstantConverters();
        assertEquals(6,array.length);
    }

    public void testAddInstantConverter1_4_oe() {
        InstantConverter c = new InstantConverter() {
            public long getInstantMillis(Object object, Chronology chrono) {return 0;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return Boolean.class;}
        };
        try {
            InstantConverter removed = ConverterManager.getInstance().addInstantConverter(c);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().removeInstantConverter(c);
        }
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testAddInstantConverter2_4_oe() {
        InstantConverter c = new InstantConverter() {
            public long getInstantMillis(Object object, Chronology chrono) {return 0;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return String.class;}
        };
        try {
            InstantConverter removed = ConverterManager.getInstance().addInstantConverter(c);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().addInstantConverter(StringConverter.INSTANCE);
        }
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testAddInstantConverter3_1_oe() {
        InstantConverter removed = ConverterManager.getInstance().addInstantConverter(StringConverter.INSTANCE);
        assertEquals(null,removed);
    }

    public void testAddInstantConverter3_2_oe() {
        InstantConverter removed = ConverterManager.getInstance().addInstantConverter(StringConverter.INSTANCE);
        // removed other assertion
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testAddInstantConverter4_1_oe() {
        InstantConverter removed = ConverterManager.getInstance().addInstantConverter(null);
        assertEquals(null,removed);
    }

    public void testAddInstantConverter4_2_oe() {
        InstantConverter removed = ConverterManager.getInstance().addInstantConverter(null);
        // removed other assertion
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testAddInstantConverterSecurity_2_oe() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            ConverterManager.getInstance().addInstantConverter(StringConverter.INSTANCE);
            // removed other assertion
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testRemoveInstantConverter1_3_oe() {
        try {
            InstantConverter removed = ConverterManager.getInstance().removeInstantConverter(StringConverter.INSTANCE);
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().addInstantConverter(StringConverter.INSTANCE);
        }
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testRemoveInstantConverter2_1_oe() {
        InstantConverter c = new InstantConverter() {
            public long getInstantMillis(Object object, Chronology chrono) {return 0;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return Boolean.class;}
        };
        InstantConverter removed = ConverterManager.getInstance().removeInstantConverter(c);
        assertEquals(null,removed);
    }

    public void testRemoveInstantConverter2_2_oe() {
        InstantConverter c = new InstantConverter() {
            public long getInstantMillis(Object object, Chronology chrono) {return 0;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return Boolean.class;}
        };
        InstantConverter removed = ConverterManager.getInstance().removeInstantConverter(c);
        // removed other assertion
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testRemoveInstantConverter3_1_oe() {
        InstantConverter removed = ConverterManager.getInstance().removeInstantConverter(null);
        assertEquals(null,removed);
    }

    public void testRemoveInstantConverter3_2_oe() {
        InstantConverter removed = ConverterManager.getInstance().removeInstantConverter(null);
        // removed other assertion
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testRemoveInstantConverterSecurity_2_oe() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            ConverterManager.getInstance().removeInstantConverter(StringConverter.INSTANCE);
            // removed other assertion
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
        assertEquals(6,ConverterManager.getInstance().getInstantConverters().length);
    }

    public void testGetPartialConverter_1_oe() {
        PartialConverter c = ConverterManager.getInstance().getPartialConverter(new Long(0L));
        assertEquals(Long.class,c.getSupportedType());
    }

    public void testGetPartialConverter_2_oe() {
        PartialConverter c = ConverterManager.getInstance().getPartialConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new TimeOfDay());
        assertEquals(ReadablePartial.class,c.getSupportedType());
    }

    public void testGetPartialConverter_3_oe() {
        PartialConverter c = ConverterManager.getInstance().getPartialConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new TimeOfDay());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new DateTime());
        assertEquals(ReadableInstant.class,c.getSupportedType());
    }

    public void testGetPartialConverter_4_oe() {
        PartialConverter c = ConverterManager.getInstance().getPartialConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new TimeOfDay());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new DateTime());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter("");
        assertEquals(String.class,c.getSupportedType());
    }

    public void testGetPartialConverter_5_oe() {
        PartialConverter c = ConverterManager.getInstance().getPartialConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new TimeOfDay());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new DateTime());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter("");
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new Date());
        assertEquals(Date.class,c.getSupportedType());
    }

    public void testGetPartialConverter_6_oe() {
        PartialConverter c = ConverterManager.getInstance().getPartialConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new TimeOfDay());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new DateTime());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter("");
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new Date());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new GregorianCalendar());
        assertEquals(Calendar.class,c.getSupportedType());
    }

    public void testGetPartialConverter_7_oe() {
        PartialConverter c = ConverterManager.getInstance().getPartialConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new TimeOfDay());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new DateTime());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter("");
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new Date());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(new GregorianCalendar());
        // removed other assertion
        
        c = ConverterManager.getInstance().getPartialConverter(null);
        assertEquals(null,c.getSupportedType());
    }

    public void testGetPartialConverterRemovedNull_2_oe() {
        try {
            ConverterManager.getInstance().removePartialConverter(NullConverter.INSTANCE);
            try {
                ConverterManager.getInstance().getPartialConverter(null);
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
        } finally {
            ConverterManager.getInstance().addPartialConverter(NullConverter.INSTANCE);
        }
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testGetPartialConverterOKMultipleMatches_2_oe() {
        PartialConverter c = new PartialConverter() {
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono) {return null;}
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono, DateTimeFormatter parser) {return null;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return ReadableDateTime.class;}
        };
        try {
            ConverterManager.getInstance().addPartialConverter(c);
            PartialConverter ok = ConverterManager.getInstance().getPartialConverter(new DateTime());
            // ReadableDateTime and ReadablePartial both match, but RI discarded as less specific
            // removed other assertion
        } finally {
            ConverterManager.getInstance().removePartialConverter(c);
        }
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testGetPartialConverterBadMultipleMatches_2_oe() {
        PartialConverter c = new PartialConverter() {
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono) {return null;}
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono, DateTimeFormatter parser) {return null;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return Serializable.class;}
        };
        try {
            ConverterManager.getInstance().addPartialConverter(c);
            try {
                ConverterManager.getInstance().getPartialConverter(new DateTime());
                // removed other assertion
            } catch (IllegalStateException ex) {
                // Serializable and ReadablePartial both match, so cannot pick
            }
        } finally {
            ConverterManager.getInstance().removePartialConverter(c);
        }
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testGetPartialConverters_1_oe() {
        PartialConverter[] array = ConverterManager.getInstance().getPartialConverters();
        assertEquals(PARTIAL_SIZE,array.length);
    }

    public void testAddPartialConverter1_4_oe() {
        PartialConverter c = new PartialConverter() {
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono) {return null;}
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono, DateTimeFormatter parser) {return null;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return Boolean.class;}
        };
        try {
            PartialConverter removed = ConverterManager.getInstance().addPartialConverter(c);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().removePartialConverter(c);
        }
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testAddPartialConverter2_4_oe() {
        PartialConverter c = new PartialConverter() {
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono) {return null;}
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono, DateTimeFormatter parser) {return null;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return String.class;}
        };
        try {
            PartialConverter removed = ConverterManager.getInstance().addPartialConverter(c);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().addPartialConverter(StringConverter.INSTANCE);
        }
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testAddPartialConverter3_1_oe() {
        PartialConverter removed = ConverterManager.getInstance().addPartialConverter(StringConverter.INSTANCE);
        assertEquals(null,removed);
    }

    public void testAddPartialConverter3_2_oe() {
        PartialConverter removed = ConverterManager.getInstance().addPartialConverter(StringConverter.INSTANCE);
        // removed other assertion
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testAddPartialConverter4_1_oe() {
        PartialConverter removed = ConverterManager.getInstance().addPartialConverter(null);
        assertEquals(null,removed);
    }

    public void testAddPartialConverter4_2_oe() {
        PartialConverter removed = ConverterManager.getInstance().addPartialConverter(null);
        // removed other assertion
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testAddPartialConverterSecurity_2_oe() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            ConverterManager.getInstance().addPartialConverter(StringConverter.INSTANCE);
            // removed other assertion
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testRemovePartialConverter1_3_oe() {
        try {
            PartialConverter removed = ConverterManager.getInstance().removePartialConverter(StringConverter.INSTANCE);
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().addPartialConverter(StringConverter.INSTANCE);
        }
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testRemovePartialConverter2_1_oe() {
        PartialConverter c = new PartialConverter() {
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono) {return null;}
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono, DateTimeFormatter parser) {return null;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return Boolean.class;}
        };
        PartialConverter removed = ConverterManager.getInstance().removePartialConverter(c);
        assertEquals(null,removed);
    }

    public void testRemovePartialConverter2_2_oe() {
        PartialConverter c = new PartialConverter() {
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono) {return null;}
            public int[] getPartialValues(ReadablePartial partial, Object object, Chronology chrono, DateTimeFormatter parser) {return null;}
            public Chronology getChronology(Object object, DateTimeZone zone) {return null;}
            public Chronology getChronology(Object object, Chronology chrono) {return null;}
            public Class getSupportedType() {return Boolean.class;}
        };
        PartialConverter removed = ConverterManager.getInstance().removePartialConverter(c);
        // removed other assertion
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testRemovePartialConverter3_1_oe() {
        PartialConverter removed = ConverterManager.getInstance().removePartialConverter(null);
        assertEquals(null,removed);
    }

    public void testRemovePartialConverter3_2_oe() {
        PartialConverter removed = ConverterManager.getInstance().removePartialConverter(null);
        // removed other assertion
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testRemovePartialConverterSecurity_2_oe() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            ConverterManager.getInstance().removeInstantConverter(StringConverter.INSTANCE);
            // removed other assertion
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
        assertEquals(PARTIAL_SIZE,ConverterManager.getInstance().getPartialConverters().length);
    }

    public void testGetDurationConverter_1_oe() {
        DurationConverter c = ConverterManager.getInstance().getDurationConverter(new Long(0L));
        assertEquals(Long.class,c.getSupportedType());
    }

    public void testGetDurationConverter_2_oe() {
        DurationConverter c = ConverterManager.getInstance().getDurationConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getDurationConverter(new Duration(123L));
        assertEquals(ReadableDuration.class,c.getSupportedType());
    }

    public void testGetDurationConverter_3_oe() {
        DurationConverter c = ConverterManager.getInstance().getDurationConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getDurationConverter(new Duration(123L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getDurationConverter(new Interval(0L, 1000L));
        assertEquals(ReadableInterval.class,c.getSupportedType());
    }

    public void testGetDurationConverter_4_oe() {
        DurationConverter c = ConverterManager.getInstance().getDurationConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getDurationConverter(new Duration(123L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getDurationConverter(new Interval(0L, 1000L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getDurationConverter("");
        assertEquals(String.class,c.getSupportedType());
    }

    public void testGetDurationConverter_5_oe() {
        DurationConverter c = ConverterManager.getInstance().getDurationConverter(new Long(0L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getDurationConverter(new Duration(123L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getDurationConverter(new Interval(0L, 1000L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getDurationConverter("");
        // removed other assertion
        
        c = ConverterManager.getInstance().getDurationConverter(null);
        assertEquals(null,c.getSupportedType());
    }

    public void testGetDurationConverterRemovedNull_2_oe() {
        try {
            ConverterManager.getInstance().removeDurationConverter(NullConverter.INSTANCE);
            try {
                ConverterManager.getInstance().getDurationConverter(null);
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
        } finally {
            ConverterManager.getInstance().addDurationConverter(NullConverter.INSTANCE);
        }
        assertEquals(DURATION_SIZE,ConverterManager.getInstance().getDurationConverters().length);
    }

    public void testGetDurationConverters_1_oe() {
        DurationConverter[] array = ConverterManager.getInstance().getDurationConverters();
        assertEquals(DURATION_SIZE,array.length);
    }

    public void testAddDurationConverter1_4_oe() {
        DurationConverter c = new DurationConverter() {
            public long getDurationMillis(Object object) {return 0;}
            public Class getSupportedType() {return Boolean.class;}
        };
        try {
            DurationConverter removed = ConverterManager.getInstance().addDurationConverter(c);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().removeDurationConverter(c);
        }
        assertEquals(DURATION_SIZE,ConverterManager.getInstance().getDurationConverters().length);
    }

    public void testAddDurationConverter2_4_oe() {
        DurationConverter c = new DurationConverter() {
            public long getDurationMillis(Object object) {return 0;}
            public Class getSupportedType() {return String.class;}
        };
        try {
            DurationConverter removed = ConverterManager.getInstance().addDurationConverter(c);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().addDurationConverter(StringConverter.INSTANCE);
        }
        assertEquals(DURATION_SIZE,ConverterManager.getInstance().getDurationConverters().length);
    }

    public void testAddDurationConverter3_1_oe() {
        DurationConverter removed = ConverterManager.getInstance().addDurationConverter(null);
        assertEquals(null,removed);
    }

    public void testAddDurationConverter3_2_oe() {
        DurationConverter removed = ConverterManager.getInstance().addDurationConverter(null);
        // removed other assertion
        assertEquals(DURATION_SIZE,ConverterManager.getInstance().getDurationConverters().length);
    }

    public void testAddDurationConverterSecurity_2_oe() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            ConverterManager.getInstance().addDurationConverter(StringConverter.INSTANCE);
            // removed other assertion
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
        assertEquals(DURATION_SIZE,ConverterManager.getInstance().getDurationConverters().length);
    }

    public void testRemoveDurationConverter1_3_oe() {
        try {
            DurationConverter removed = ConverterManager.getInstance().removeDurationConverter(StringConverter.INSTANCE);
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().addDurationConverter(StringConverter.INSTANCE);
        }
        assertEquals(DURATION_SIZE,ConverterManager.getInstance().getDurationConverters().length);
    }

    public void testRemoveDurationConverter2_1_oe() {
        DurationConverter c = new DurationConverter() {
            public long getDurationMillis(Object object) {return 0;}
            public Class getSupportedType() {return Boolean.class;}
        };
        DurationConverter removed = ConverterManager.getInstance().removeDurationConverter(c);
        assertEquals(null,removed);
    }

    public void testRemoveDurationConverter2_2_oe() {
        DurationConverter c = new DurationConverter() {
            public long getDurationMillis(Object object) {return 0;}
            public Class getSupportedType() {return Boolean.class;}
        };
        DurationConverter removed = ConverterManager.getInstance().removeDurationConverter(c);
        // removed other assertion
        assertEquals(DURATION_SIZE,ConverterManager.getInstance().getDurationConverters().length);
    }

    public void testRemoveDurationConverter3_1_oe() {
        DurationConverter removed = ConverterManager.getInstance().removeDurationConverter(null);
        assertEquals(null,removed);
    }

    public void testRemoveDurationConverter3_2_oe() {
        DurationConverter removed = ConverterManager.getInstance().removeDurationConverter(null);
        // removed other assertion
        assertEquals(DURATION_SIZE,ConverterManager.getInstance().getDurationConverters().length);
    }

    public void testRemoveDurationConverterSecurity_2_oe() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            ConverterManager.getInstance().removeDurationConverter(StringConverter.INSTANCE);
            // removed other assertion
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
        assertEquals(DURATION_SIZE,ConverterManager.getInstance().getDurationConverters().length);
    }

    public void testGetPeriodConverter_1_oe() {
        PeriodConverter c = ConverterManager.getInstance().getPeriodConverter(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(ReadablePeriod.class,c.getSupportedType());
    }

    public void testGetPeriodConverter_2_oe() {
        PeriodConverter c = ConverterManager.getInstance().getPeriodConverter(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPeriodConverter(new Duration(123L));
        assertEquals(ReadableDuration.class,c.getSupportedType());
    }

    public void testGetPeriodConverter_3_oe() {
        PeriodConverter c = ConverterManager.getInstance().getPeriodConverter(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPeriodConverter(new Duration(123L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPeriodConverter(new Interval(0L, 1000L));
        assertEquals(ReadableInterval.class,c.getSupportedType());
    }

    public void testGetPeriodConverter_4_oe() {
        PeriodConverter c = ConverterManager.getInstance().getPeriodConverter(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPeriodConverter(new Duration(123L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPeriodConverter(new Interval(0L, 1000L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPeriodConverter("");
        assertEquals(String.class,c.getSupportedType());
    }

    public void testGetPeriodConverter_5_oe() {
        PeriodConverter c = ConverterManager.getInstance().getPeriodConverter(new Period(1, 2, 3, 4, 5, 6, 7, 8));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPeriodConverter(new Duration(123L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPeriodConverter(new Interval(0L, 1000L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getPeriodConverter("");
        // removed other assertion
        
        c = ConverterManager.getInstance().getPeriodConverter(null);
        assertEquals(null,c.getSupportedType());
    }

    public void testGetPeriodConverterRemovedNull_2_oe() {
        try {
            ConverterManager.getInstance().removePeriodConverter(NullConverter.INSTANCE);
            try {
                ConverterManager.getInstance().getPeriodConverter(null);
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
        } finally {
            ConverterManager.getInstance().addPeriodConverter(NullConverter.INSTANCE);
        }
        assertEquals(PERIOD_SIZE,ConverterManager.getInstance().getPeriodConverters().length);
    }

    public void testGetPeriodConverters_1_oe() {
        PeriodConverter[] array = ConverterManager.getInstance().getPeriodConverters();
        assertEquals(PERIOD_SIZE,array.length);
    }

    public void testAddPeriodConverter1_4_oe() {
        PeriodConverter c = new PeriodConverter() {
            public void setInto(ReadWritablePeriod duration, Object object, Chronology c) {}
            public PeriodType getPeriodType(Object object) {return null;}
            public Class getSupportedType() {return Boolean.class;}
        };
        try {
            PeriodConverter removed = ConverterManager.getInstance().addPeriodConverter(c);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().removePeriodConverter(c);
        }
        assertEquals(PERIOD_SIZE,ConverterManager.getInstance().getPeriodConverters().length);
    }

    public void testAddPeriodConverter2_4_oe() {
        PeriodConverter c = new PeriodConverter() {
            public void setInto(ReadWritablePeriod duration, Object object, Chronology c) {}
            public PeriodType getPeriodType(Object object) {return null;}
            public Class getSupportedType() {return String.class;}
        };
        try {
            PeriodConverter removed = ConverterManager.getInstance().addPeriodConverter(c);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().addPeriodConverter(StringConverter.INSTANCE);
        }
        assertEquals(PERIOD_SIZE,ConverterManager.getInstance().getPeriodConverters().length);
    }

    public void testAddPeriodConverter3_1_oe() {
        PeriodConverter removed = ConverterManager.getInstance().addPeriodConverter(null);
        assertEquals(null,removed);
    }

    public void testAddPeriodConverter3_2_oe() {
        PeriodConverter removed = ConverterManager.getInstance().addPeriodConverter(null);
        // removed other assertion
        assertEquals(PERIOD_SIZE,ConverterManager.getInstance().getPeriodConverters().length);
    }

    public void testAddPeriodConverterSecurity_2_oe() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            ConverterManager.getInstance().addPeriodConverter(StringConverter.INSTANCE);
            // removed other assertion
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
        assertEquals(PERIOD_SIZE,ConverterManager.getInstance().getPeriodConverters().length);
    }

    public void testRemovePeriodConverter1_3_oe() {
        try {
            PeriodConverter removed = ConverterManager.getInstance().removePeriodConverter(StringConverter.INSTANCE);
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().addPeriodConverter(StringConverter.INSTANCE);
        }
        assertEquals(PERIOD_SIZE,ConverterManager.getInstance().getPeriodConverters().length);
    }

    public void testRemovePeriodConverter2_1_oe() {
        PeriodConverter c = new PeriodConverter() {
            public void setInto(ReadWritablePeriod duration, Object object, Chronology c) {}
            public PeriodType getPeriodType(Object object) {return null;}
            public Class getSupportedType() {return Boolean.class;}
        };
        PeriodConverter removed = ConverterManager.getInstance().removePeriodConverter(c);
        assertEquals(null,removed);
    }

    public void testRemovePeriodConverter2_2_oe() {
        PeriodConverter c = new PeriodConverter() {
            public void setInto(ReadWritablePeriod duration, Object object, Chronology c) {}
            public PeriodType getPeriodType(Object object) {return null;}
            public Class getSupportedType() {return Boolean.class;}
        };
        PeriodConverter removed = ConverterManager.getInstance().removePeriodConverter(c);
        // removed other assertion
        assertEquals(PERIOD_SIZE,ConverterManager.getInstance().getPeriodConverters().length);
    }

    public void testRemovePeriodConverter3_1_oe() {
        PeriodConverter removed = ConverterManager.getInstance().removePeriodConverter(null);
        assertEquals(null,removed);
    }

    public void testRemovePeriodConverter3_2_oe() {
        PeriodConverter removed = ConverterManager.getInstance().removePeriodConverter(null);
        // removed other assertion
        assertEquals(PERIOD_SIZE,ConverterManager.getInstance().getPeriodConverters().length);
    }

    public void testRemovePeriodConverterSecurity_2_oe() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            ConverterManager.getInstance().removePeriodConverter(StringConverter.INSTANCE);
            // removed other assertion
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
        assertEquals(PERIOD_SIZE,ConverterManager.getInstance().getPeriodConverters().length);
    }

    public void testGetIntervalConverter_1_oe() {
        IntervalConverter c = ConverterManager.getInstance().getIntervalConverter(new Interval(0L, 1000L));
        assertEquals(ReadableInterval.class,c.getSupportedType());
    }

    public void testGetIntervalConverter_2_oe() {
        IntervalConverter c = ConverterManager.getInstance().getIntervalConverter(new Interval(0L, 1000L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getIntervalConverter("");
        assertEquals(String.class,c.getSupportedType());
    }

    public void testGetIntervalConverter_3_oe() {
        IntervalConverter c = ConverterManager.getInstance().getIntervalConverter(new Interval(0L, 1000L));
        // removed other assertion
        
        c = ConverterManager.getInstance().getIntervalConverter("");
        // removed other assertion
        
        c = ConverterManager.getInstance().getIntervalConverter(null);
        assertEquals(null,c.getSupportedType());
    }

    public void testGetIntervalConverterRemovedNull_2_oe() {
        try {
            ConverterManager.getInstance().removeIntervalConverter(NullConverter.INSTANCE);
            try {
                ConverterManager.getInstance().getIntervalConverter(null);
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
        } finally {
            ConverterManager.getInstance().addIntervalConverter(NullConverter.INSTANCE);
        }
        assertEquals(INTERVAL_SIZE,ConverterManager.getInstance().getIntervalConverters().length);
    }

    public void testGetIntervalConverters_1_oe() {
        IntervalConverter[] array = ConverterManager.getInstance().getIntervalConverters();
        assertEquals(INTERVAL_SIZE,array.length);
    }

    public void testAddIntervalConverter1_4_oe() {
        IntervalConverter c = new IntervalConverter() {
            public boolean isReadableInterval(Object object, Chronology chrono) {return false;}
            public void setInto(ReadWritableInterval interval, Object object, Chronology chrono) {}
            public Class getSupportedType() {return Boolean.class;}
        };
        try {
            IntervalConverter removed = ConverterManager.getInstance().addIntervalConverter(c);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().removeIntervalConverter(c);
        }
        assertEquals(INTERVAL_SIZE,ConverterManager.getInstance().getIntervalConverters().length);
    }

    public void testAddIntervalConverter2_4_oe() {
        IntervalConverter c = new IntervalConverter() {
            public boolean isReadableInterval(Object object, Chronology chrono) {return false;}
            public void setInto(ReadWritableInterval interval, Object object, Chronology chrono) {}
            public Class getSupportedType() {return String.class;}
        };
        try {
            IntervalConverter removed = ConverterManager.getInstance().addIntervalConverter(c);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().addIntervalConverter(StringConverter.INSTANCE);
        }
        assertEquals(INTERVAL_SIZE,ConverterManager.getInstance().getIntervalConverters().length);
    }

    public void testAddIntervalConverter3_1_oe() {
        IntervalConverter removed = ConverterManager.getInstance().addIntervalConverter(null);
        assertEquals(null,removed);
    }

    public void testAddIntervalConverter3_2_oe() {
        IntervalConverter removed = ConverterManager.getInstance().addIntervalConverter(null);
        // removed other assertion
        assertEquals(INTERVAL_SIZE,ConverterManager.getInstance().getIntervalConverters().length);
    }

    public void testAddIntervalConverterSecurity_2_oe() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            ConverterManager.getInstance().addIntervalConverter(StringConverter.INSTANCE);
            // removed other assertion
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
        assertEquals(INTERVAL_SIZE,ConverterManager.getInstance().getIntervalConverters().length);
    }

    public void testRemoveIntervalConverter1_3_oe() {
        try {
            IntervalConverter removed = ConverterManager.getInstance().removeIntervalConverter(StringConverter.INSTANCE);
            // removed other assertion
            // removed other assertion
        } finally {
            ConverterManager.getInstance().addIntervalConverter(StringConverter.INSTANCE);
        }
        assertEquals(INTERVAL_SIZE,ConverterManager.getInstance().getIntervalConverters().length);
    }

    public void testRemoveIntervalConverter2_1_oe() {
        IntervalConverter c = new IntervalConverter() {
            public boolean isReadableInterval(Object object, Chronology chrono) {return false;}
            public void setInto(ReadWritableInterval interval, Object object, Chronology chrono) {}
            public Class getSupportedType() {return Boolean.class;}
        };
        IntervalConverter removed = ConverterManager.getInstance().removeIntervalConverter(c);
        assertEquals(null,removed);
    }

    public void testRemoveIntervalConverter2_2_oe() {
        IntervalConverter c = new IntervalConverter() {
            public boolean isReadableInterval(Object object, Chronology chrono) {return false;}
            public void setInto(ReadWritableInterval interval, Object object, Chronology chrono) {}
            public Class getSupportedType() {return Boolean.class;}
        };
        IntervalConverter removed = ConverterManager.getInstance().removeIntervalConverter(c);
        // removed other assertion
        assertEquals(INTERVAL_SIZE,ConverterManager.getInstance().getIntervalConverters().length);
    }

    public void testRemoveIntervalConverter3_1_oe() {
        IntervalConverter removed = ConverterManager.getInstance().removeIntervalConverter(null);
        assertEquals(null,removed);
    }

    public void testRemoveIntervalConverter3_2_oe() {
        IntervalConverter removed = ConverterManager.getInstance().removeIntervalConverter(null);
        // removed other assertion
        assertEquals(INTERVAL_SIZE,ConverterManager.getInstance().getIntervalConverters().length);
    }

    public void testRemoveIntervalConverterSecurity_2_oe() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            ConverterManager.getInstance().removeIntervalConverter(StringConverter.INSTANCE);
            // removed other assertion
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
        assertEquals(INTERVAL_SIZE,ConverterManager.getInstance().getIntervalConverters().length);
    }

    public void testToString_1_oe() {
        assertEquals("ConverterManager[6 instant,7 partial,5 duration,5 period,3 interval]",ConverterManager.getInstance().toString());
    }

}
