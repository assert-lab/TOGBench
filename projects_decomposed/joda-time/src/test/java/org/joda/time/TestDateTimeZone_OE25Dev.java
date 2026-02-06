/*
 *  Copyright 2001-2014 Stephen Colebourne
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
import java.io.FilePermission;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Modifier;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;
import org.joda.time.tz.UTCProvider;
import org.joda.time.tz.ZoneInfoProvider;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This class is a JUnit test for DateTimeZone.
 *
 * @author Stephen Colebourne
 */
public class TestDateTimeZone_OE25Dev extends TestCase {
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
    private long TEST_TIME_SUMMER =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
    // 2002-01-09
    private long TEST_TIME_WINTER =
            (y2002days + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
//    // 2002-04-05 Fri
//    private long TEST_TIME1 =
//            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
//            + 12L * DateTimeConstants.MILLIS_PER_HOUR
//            + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
//        
//    // 2003-05-06 Tue
//    private long TEST_TIME2 =
//            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
//            + 14L * DateTimeConstants.MILLIS_PER_HOUR
//            + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
    
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
    
    private DateTimeZone zone;
    private Locale locale;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateTimeZone_OE25Dev_OE25Dev.class);
    }

    public TestDateTimeZone_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        locale = Locale.getDefault();
        zone = DateTimeZone.getDefault();
        Locale.setDefault(Locale.UK);
    }

    @Override
    protected void tearDown() throws Exception {
        Locale.setDefault(locale);
        DateTimeZone.setDefault(zone);
    }

    //-----------------------------------------------------------------------
            
    public void testDefaultSecurity() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            DateTimeZone.setDefault(PARIS);
            fail();
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testFromTimeZoneInvalid() throws Exception {
        TimeZone jdkZone = new TimeZone() {
            private static final long serialVersionUID = 1L;
            @Override
            public String getID() {
                return null;
            }
            @Override
            public int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds) {
                return 0;
            }
            @Override
            public void setRawOffset(int offsetMillis) {
            }
            @Override
            public int getRawOffset() {
                return 0;
            }
            @Override
            public boolean useDaylightTime() {
                return false;
            }
            @Override
            public boolean inDaylightTime(Date date) {
                return false;
            }
        };
        try {
            DateTimeZone.forTimeZone(jdkZone);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    
    public void testProviderSecurity() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            DateTimeZone.setProvider(new MockOKProvider());
            fail();
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
    }

    public void testZoneInfoProviderResourceLoading() {
        final Set<String> ids = new HashSet<String>(DateTimeZone.getAvailableIDs());
        ids.remove(DateTimeZone.getDefault().getID());
        final String id = ids.toArray(new String[ids.size()])[new Random().nextInt(ids.size())];
        try {
            Policy.setPolicy(new Policy() {
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
                    return !(permission instanceof FilePermission) && !permission.getName().contains(id);
                }
            });
            System.setSecurityManager(new SecurityManager());
            // will throw IllegalArgumentException if the resource can
            // not be loaded
            final DateTimeZone zone = DateTimeZone.forID(id);
            assertNotNull(zone);
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
    }

    static class MockNullIDSProvider implements Provider {
        public Set getAvailableIDs() {
            return null;
        }
        public DateTimeZone getZone(String id) {
            return null;
        }
    }
    static class MockEmptyIDSProvider implements Provider {
        public Set getAvailableIDs() {
            return new HashSet();
        }
        public DateTimeZone getZone(String id) {
            return null;
        }
    }
    static class MockNoUTCProvider implements Provider {
        public Set getAvailableIDs() {
            Set set = new HashSet();
            set.add("Europe/London");
            return set;
        }
        public DateTimeZone getZone(String id) {
            return null;
        }
    }
    static class MockBadUTCProvider implements Provider {
        public Set getAvailableIDs() {
            Set set = new HashSet();
            set.add("UTC");
            set.add("Europe/London");
            return set;
        }
        public DateTimeZone getZone(String id) {
            return null;
        }
    }
    static class MockOKProvider implements Provider {
        public Set getAvailableIDs() {
            Set set = new HashSet();
            set.add("UTC");
            set.add("Europe/London");
            return set;
        }
        public DateTimeZone getZone(String id) {
            return DateTimeZone.UTC;
        }
    }

    //-----------------------------------------------------------------------

    public void testNameProviderSecurity() {
        if (OLD_JDK) {
            return;
        }
        try {
            Policy.setPolicy(RESTRICT);
            System.setSecurityManager(new SecurityManager());
            DateTimeZone.setNameProvider(new MockOKButNullNameProvider());
            fail();
        } catch (SecurityException ex) {
            // ok
        } finally {
            System.setSecurityManager(null);
            Policy.setPolicy(ALLOW);
        }
    }

    static class MockOKButNullNameProvider implements NameProvider {
        public String getShortName(Locale locale, String id, String nameKey) {
            return null;
        }
        public String getName(Locale locale, String id, String nameKey) {
            return null;
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    static final boolean JDK6PLUS;
    static {
        boolean jdk6 = true;
        try {
            DateFormatSymbols.class.getMethod("getInstance", new Class[] { Locale.class });
        } catch (Exception ex) {
            jdk6 = false;
        }
        JDK6PLUS = jdk6;
    }

    static final boolean JDK9;
    static {
        boolean jdk9 = true;
        try {
            String str = System.getProperty("java.version");
            jdk9 = str.startsWith("9");
        } catch (Exception ex) {
            jdk9 = false;
        }
        JDK9 = jdk9;
    }

    static class MockDateTimeZone extends DateTimeZone {
        public MockDateTimeZone(String id) {
            super(id);
        }
        @Override
        public String getNameKey(long instant) {
            return null;  // null
        }
        @Override
        public int getOffset(long instant) {
            return 0;
        }
        @Override
        public int getStandardOffset(long instant) {
            return 0;
        }
        @Override
        public boolean isFixed() {
            return false;
        }
        @Override
        public long nextTransition(long instant) {
            return 0;
        }
        @Override
        public long previousTransition(long instant) {
            return 0;
        }
        @Override
        public boolean equals(Object object) {
            return false;
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

//    //-----------------------------------------------------------------------
//    public void testIsLocalDateTimeOverlap_Berlin() {
//        DateTimeZone zone = DateTimeZone.forID("Europe/Berlin");
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,10,28,1,0)));
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,10,28,1,59,59,99)));
//        assertEquals(true,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,10,28,2,0)));
//        assertEquals(true,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,10,28,2,30)));
//        assertEquals(true,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,10,28,2,59,59,99)));
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,10,28,3,0)));
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,10,28,4,0)));
//        
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,3,25,1,30)));  // before gap
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,3,25,2,30)));  // gap
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,3,25,3,30)));  // after gap
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,12,24,12,34)));
//    }
//
//    //-----------------------------------------------------------------------
//    public void testIsLocalDateTimeOverlap_NewYork() {
//        DateTimeZone zone = DateTimeZone.forID("America/New_York");
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,11,4,0,0)));
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,11,4,0,59,59,99)));
//        assertEquals(true,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,11,4,1,0)));
//        assertEquals(true,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,11,4,1,30)));
//        assertEquals(true,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,11,4,1,59,59,99)));
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,11,4,2,0)));
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,11,4,3,0)));
//        
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,3,11,1,30)));  // before gap
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,3,11,2,30)));  // gap
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,3,11,3,30)));  // after gap
//        assertEquals(false,zone.isLocalDateTimeOverlap(new LocalDateTime(2007,12,24,12,34)));
//    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    // rule with negative SAVE value
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    // rule of style "Fri <= 1"

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testDefault_1_oe() {
        assertNotNull(DateTimeZone.getDefault());
    }

    public void testDefault_2_oe() {
        // removed other assertion
        
        DateTimeZone.setDefault(PARIS);
        assertSame(PARIS,DateTimeZone.getDefault());
    }

    public void testForID_String_1_oe() {
        assertEquals(DateTimeZone.getDefault(),DateTimeZone.forID((String)null));
    }

    public void testForID_String_2_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        assertEquals("Europe/London",zone.getID());
    }

    public void testForID_String_3_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        
        zone = DateTimeZone.forID("UTC");
        assertSame(DateTimeZone.UTC,zone);
    }

    public void testForID_String_4_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        
        zone = DateTimeZone.forID("UTC");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00:00");
        assertSame(DateTimeZone.UTC,zone);
    }

    public void testForID_String_5_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        
        zone = DateTimeZone.forID("UTC");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00:00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00");
        assertSame(DateTimeZone.UTC,zone);
    }

    public void testForID_String_6_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        
        zone = DateTimeZone.forID("UTC");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00:00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+01:23");
        assertEquals("+01:23",zone.getID());
    }

    public void testForID_String_7_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        
        zone = DateTimeZone.forID("UTC");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00:00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+01:23");
        // removed other assertion
        assertEquals(DateTimeConstants.MILLIS_PER_HOUR +(23L * DateTimeConstants.MILLIS_PER_MINUTE),zone.getOffset(TEST_TIME_SUMMER));
    }

    public void testForID_String_8_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        
        zone = DateTimeZone.forID("UTC");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00:00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+01:23");
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forID("-02:00");
        assertEquals("-02:00",zone.getID());
    }

    public void testForID_String_9_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        
        zone = DateTimeZone.forID("UTC");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00:00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+01:23");
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forID("-02:00");
        // removed other assertion
        assertEquals((-2L * DateTimeConstants.MILLIS_PER_HOUR),zone.getOffset(TEST_TIME_SUMMER));
    }

    public void testForID_String_10_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        
        zone = DateTimeZone.forID("UTC");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00:00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+01:23");
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forID("-02:00");
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forID("-07:05:34.0");
        assertEquals("-07:05:34",zone.getID());
    }

    public void testForID_String_11_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        
        zone = DateTimeZone.forID("UTC");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00:00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+00");
        // removed other assertion
        
        zone = DateTimeZone.forID("+01:23");
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forID("-02:00");
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forID("-07:05:34.0");
        // removed other assertion
        assertEquals((-7L * DateTimeConstants.MILLIS_PER_HOUR)+(-5L * DateTimeConstants.MILLIS_PER_MINUTE)+(-34L * DateTimeConstants.MILLIS_PER_SECOND),zone.getOffset(TEST_TIME_SUMMER));
    }

    public void testForID_ensureTzdb_1_oe() {
      // if these tests fail, check https://github.com/JodaOrg/joda-time/issues/566 for more info
      assertEquals("Europe/Oslo",DateTimeZone.forID("Europe/Oslo").getID());
    }

    public void testForID_ensureTzdb_2_oe() {
      // if these tests fail, check https://github.com/JodaOrg/joda-time/issues/566 for more info
      // removed other assertion
      assertEquals("Europe/Stockholm",DateTimeZone.forID("Europe/Stockholm").getID());
    }

    public void testForID_ensureTzdb_3_oe() {
      // if these tests fail, check https://github.com/JodaOrg/joda-time/issues/566 for more info
      // removed other assertion
      // removed other assertion
      assertEquals("Europe/Amsterdam",DateTimeZone.forID("Europe/Amsterdam").getID());
    }

    public void testForID_String_old_1_oe() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("GMT", "UTC");
        map.put("WET", "WET");
        map.put("CET", "CET");
        map.put("MET", "CET");
        map.put("ECT", "CET");
        map.put("EET", "EET");
        map.put("MIT", "Pacific/Apia");
        map.put("HST", "Pacific/Honolulu");
        map.put("AST", "America/Anchorage");
        map.put("PST", "America/Los_Angeles");
        map.put("MST", "America/Denver");
        map.put("PNT", "America/Phoenix");
        map.put("CST", "America/Chicago");
        map.put("EST", "America/New_York");
        map.put("IET", "America/Indiana/Indianapolis");
        map.put("PRT", "America/Puerto_Rico");
        map.put("CNT", "America/St_Johns");
        map.put("AGT", "America/Argentina/Buenos_Aires");
        map.put("BET", "America/Sao_Paulo");
        map.put("ART", "Africa/Cairo");
        map.put("CAT", "Africa/Harare");
        map.put("EAT", "Africa/Addis_Ababa");
        map.put("NET", "Asia/Yerevan");
        map.put("PLT", "Asia/Karachi");
        map.put("IST", "Asia/Kolkata");
        map.put("BST", "Asia/Dhaka");
        map.put("VST", "Asia/Ho_Chi_Minh");
        map.put("CTT", "Asia/Shanghai");
        map.put("JST", "Asia/Tokyo");
        map.put("ACT", "Australia/Darwin");
        map.put("AET", "Australia/Sydney");
        map.put("SST", "Pacific/Guadalcanal");
        map.put("NST", "Pacific/Auckland");
        for (String key : map.keySet()) {
            String value = map.get(key);
            TimeZone juZone = TimeZone.getTimeZone(key);
            DateTimeZone zone = DateTimeZone.forTimeZone(juZone);
            assertEquals(DateTimeZone.forID(value),zone);
    }
    }

    public void testForOffsetHours_int_1_oe() {
        assertEquals(DateTimeZone.UTC,DateTimeZone.forOffsetHours(0));
    }

    public void testForOffsetHours_int_2_oe() {
        // removed other assertion
        assertEquals(DateTimeZone.forID("+03:00"),DateTimeZone.forOffsetHours(3));
    }

    public void testForOffsetHours_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeZone.forID("-02:00"),DateTimeZone.forOffsetHours(-2));
    }

    public void testForOffsetHoursMinutes_int_int_1_oe() {
        assertEquals(DateTimeZone.UTC,DateTimeZone.forOffsetHoursMinutes(0,0));
    }

    public void testForOffsetHoursMinutes_int_int_2_oe() {
        // removed other assertion
        assertEquals(DateTimeZone.forID("+23:59"),DateTimeZone.forOffsetHoursMinutes(23,59));
    }

    public void testForOffsetHoursMinutes_int_int_3_oe() {
        // removed other assertion
        // removed other assertion
        
        assertEquals(DateTimeZone.forID("+02:15"),DateTimeZone.forOffsetHoursMinutes(2,15));
    }

    public void testForOffsetHoursMinutes_int_int_4_oe() {
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(DateTimeZone.forID("+02:00"),DateTimeZone.forOffsetHoursMinutes(2,0));
    }

    public void testForOffsetMillis_int_1_oe() {
        assertSame(DateTimeZone.UTC,DateTimeZone.forOffsetMillis(0));
    }

    public void testForOffsetMillis_int_2_oe() {
        // removed other assertion
        assertEquals(DateTimeZone.forID("+23:59:59.999"),DateTimeZone.forOffsetMillis((24 * 60 * 60 * 1000)- 1));
    }

    public void testForOffsetMillis_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeZone.forID("+03:00"),DateTimeZone.forOffsetMillis(3 * 60 * 60 * 1000));
    }

    public void testForOffsetMillis_int_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeZone.forID("-02:00"),DateTimeZone.forOffsetMillis(-2 * 60 * 60 * 1000));
    }

    public void testForOffsetMillis_int_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeZone.forID("-23:59:59.999"),DateTimeZone.forOffsetMillis((-24 * 60 * 60 * 1000)+ 1));
    }

    public void testForOffsetMillis_int_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeZone.forID("+04:45:17.045"),DateTimeZone.forOffsetMillis(4 * 60 * 60 * 1000 + 45 * 60 * 1000 + 17 * 1000 + 45));
    }

    public void testForTimeZone_TimeZone_1_oe() {
        assertEquals(DateTimeZone.getDefault(),DateTimeZone.forTimeZone((TimeZone)null));
    }

    public void testForTimeZone_TimeZone_2_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        assertEquals("Europe/London",zone.getID());
    }

    public void testForTimeZone_TimeZone_3_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        assertSame(DateTimeZone.UTC,DateTimeZone.forTimeZone(TimeZone.getTimeZone("UTC")));
    }

    public void testForTimeZone_TimeZone_4_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        assertSame(DateTimeZone.UTC,zone);
    }

    public void testForTimeZone_TimeZone_5_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        assertSame(DateTimeZone.UTC,zone);
    }

    public void testForTimeZone_TimeZone_6_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        assertSame(DateTimeZone.UTC,zone);
    }

    public void testForTimeZone_TimeZone_7_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        assertSame(DateTimeZone.UTC,zone);
    }

    public void testForTimeZone_TimeZone_8_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+01:23"));
        assertEquals("+01:23",zone.getID());
    }

    public void testForTimeZone_TimeZone_9_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+01:23"));
        // removed other assertion
        assertEquals(DateTimeConstants.MILLIS_PER_HOUR +(23L * DateTimeConstants.MILLIS_PER_MINUTE),zone.getOffset(TEST_TIME_SUMMER));
    }

    public void testForTimeZone_TimeZone_10_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+01:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+1:23"));
        assertEquals("+01:23",zone.getID());
    }

    public void testForTimeZone_TimeZone_11_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+01:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+1:23"));
        // removed other assertion
        assertEquals(DateTimeConstants.MILLIS_PER_HOUR +(23L * DateTimeConstants.MILLIS_PER_MINUTE),zone.getOffset(TEST_TIME_SUMMER));
    }

    public void testForTimeZone_TimeZone_12_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+01:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+1:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-02:00"));
        assertEquals("-02:00",zone.getID());
    }

    public void testForTimeZone_TimeZone_13_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+01:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+1:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-02:00"));
        // removed other assertion
        assertEquals((-2L * DateTimeConstants.MILLIS_PER_HOUR),zone.getOffset(TEST_TIME_SUMMER));
    }

    public void testForTimeZone_TimeZone_14_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+01:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+1:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-02:00"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+2"));
        assertEquals("+02:00",zone.getID());
    }

    public void testForTimeZone_TimeZone_15_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+01:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+1:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-02:00"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+2"));
        // removed other assertion
        assertEquals((2L * DateTimeConstants.MILLIS_PER_HOUR),zone.getOffset(TEST_TIME_SUMMER));
    }

    public void testForTimeZone_TimeZone_16_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+01:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+1:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-02:00"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+2"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("EST"));
        assertEquals("America/New_York",zone.getID());
    }

    public void testForTimeZone_TimeZone_17_oe() {
        // removed other assertion
        
        DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/London"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+00"));
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+01:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+1:23"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-02:00"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+2"));
        // removed other assertion
        // removed other assertion
        
        zone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("EST"));
        // removed other assertion
        
        TimeZone tz = TimeZone.getTimeZone("GMT-08:00");
        tz.setID("GMT-\u0660\u0668:\u0660\u0660");
        zone = DateTimeZone.forTimeZone(tz);
        assertEquals("-08:00",zone.getID());
    }

    public void testTimeZoneConversion_1_oe() {
        TimeZone jdkTimeZone = TimeZone.getTimeZone("GMT-10");
        assertEquals("GMT-10:00",jdkTimeZone.getID());
    }

    public void testTimeZoneConversion_2_oe() {
        TimeZone jdkTimeZone = TimeZone.getTimeZone("GMT-10");
        // removed other assertion
        
        DateTimeZone jodaTimeZone = DateTimeZone.forTimeZone(jdkTimeZone);
        assertEquals("-10:00",jodaTimeZone.getID());
    }

    public void testTimeZoneConversion_3_oe() {
        TimeZone jdkTimeZone = TimeZone.getTimeZone("GMT-10");
        // removed other assertion
        
        DateTimeZone jodaTimeZone = DateTimeZone.forTimeZone(jdkTimeZone);
        // removed other assertion
        assertEquals(jdkTimeZone.getRawOffset(),jodaTimeZone.getOffset(0L));
    }

    public void testTimeZoneConversion_4_oe() {
        TimeZone jdkTimeZone = TimeZone.getTimeZone("GMT-10");
        // removed other assertion
        
        DateTimeZone jodaTimeZone = DateTimeZone.forTimeZone(jdkTimeZone);
        // removed other assertion
        // removed other assertion
        
        TimeZone convertedTimeZone = jodaTimeZone.toTimeZone();
        assertEquals("GMT-10:00",jdkTimeZone.getID());
    }

    public void testTimeZoneConversion_5_oe() {
        TimeZone jdkTimeZone = TimeZone.getTimeZone("GMT-10");
        // removed other assertion
        
        DateTimeZone jodaTimeZone = DateTimeZone.forTimeZone(jdkTimeZone);
        // removed other assertion
        // removed other assertion
        
        TimeZone convertedTimeZone = jodaTimeZone.toTimeZone();
        // removed other assertion
        
        assertEquals(jdkTimeZone.getID(),convertedTimeZone.getID());
    }

    public void testTimeZoneConversion_6_oe() {
        TimeZone jdkTimeZone = TimeZone.getTimeZone("GMT-10");
        // removed other assertion
        
        DateTimeZone jodaTimeZone = DateTimeZone.forTimeZone(jdkTimeZone);
        // removed other assertion
        // removed other assertion
        
        TimeZone convertedTimeZone = jodaTimeZone.toTimeZone();
        // removed other assertion
        
        // removed other assertion
        assertEquals(jdkTimeZone.getRawOffset(),convertedTimeZone.getRawOffset());
    }

    public void testGetAvailableIDs_1_oe() {
        assertTrue(DateTimeZone.getAvailableIDs().contains("UTC"));
    }

    public void testProvider_11_oe() {
        try {
            // removed other assertion
        
            Provider provider = DateTimeZone.getProvider();
            DateTimeZone.setProvider(null);
            // removed other assertion
        
            try {
                DateTimeZone.setProvider(new MockNullIDSProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockEmptyIDSProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockNoUTCProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockBadUTCProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
        
            Provider prov = new MockOKProvider();
            DateTimeZone.setProvider(prov);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            DateTimeZone.setProvider(null);
            assertEquals(ZoneInfoProvider.class,DateTimeZone.getProvider().getClass());
    }
    }

    public void testProvider_13_oe() {
        try {
            // removed other assertion
        
            Provider provider = DateTimeZone.getProvider();
            DateTimeZone.setProvider(null);
            // removed other assertion
        
            try {
                DateTimeZone.setProvider(new MockNullIDSProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockEmptyIDSProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockNoUTCProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockBadUTCProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
        
            Provider prov = new MockOKProvider();
            DateTimeZone.setProvider(prov);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            DateTimeZone.setProvider(null);
            // removed other assertion
        }
        
        try {
            System.setProperty("org.joda.time.DateTimeZone.Provider", "org.joda.time.tz.UTCProvider");
            DateTimeZone.setProvider(null);
            // removed other assertion
        } finally {
            System.getProperties().remove("org.joda.time.DateTimeZone.Provider");
            DateTimeZone.setProvider(null);
            assertEquals(ZoneInfoProvider.class,DateTimeZone.getProvider().getClass());
    }
    }

    public void testProvider_18_oe() {
        try {
            // removed other assertion
        
            Provider provider = DateTimeZone.getProvider();
            DateTimeZone.setProvider(null);
            // removed other assertion
        
            try {
                DateTimeZone.setProvider(new MockNullIDSProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockEmptyIDSProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockNoUTCProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockBadUTCProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
        
            Provider prov = new MockOKProvider();
            DateTimeZone.setProvider(prov);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            DateTimeZone.setProvider(null);
            // removed other assertion
        }
        
        try {
            System.setProperty("org.joda.time.DateTimeZone.Provider", "org.joda.time.tz.UTCProvider");
            DateTimeZone.setProvider(null);
            // removed other assertion
        } finally {
            System.getProperties().remove("org.joda.time.DateTimeZone.Provider");
            DateTimeZone.setProvider(null);
            // removed other assertion
        }
        
        try {
            System.setProperty("org.joda.time.DateTimeZone.Folder", "src/test/resources/tzdata");
            DateTimeZone.setProvider(null);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            
        } finally {
            System.getProperties().remove("org.joda.time.DateTimeZone.Folder");
            DateTimeZone.setProvider(null);
            assertEquals(ZoneInfoProvider.class,DateTimeZone.getProvider().getClass());
    }
    }

    public void testProvider_19_oe() {
        try {
            // removed other assertion
        
            Provider provider = DateTimeZone.getProvider();
            DateTimeZone.setProvider(null);
            // removed other assertion
        
            try {
                DateTimeZone.setProvider(new MockNullIDSProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockEmptyIDSProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockNoUTCProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
            try {
                DateTimeZone.setProvider(new MockBadUTCProvider());
                // removed other assertion
            } catch (IllegalArgumentException ex) {}
        
            Provider prov = new MockOKProvider();
            DateTimeZone.setProvider(prov);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } finally {
            DateTimeZone.setProvider(null);
            // removed other assertion
        }
        
        try {
            System.setProperty("org.joda.time.DateTimeZone.Provider", "org.joda.time.tz.UTCProvider");
            DateTimeZone.setProvider(null);
            // removed other assertion
        } finally {
            System.getProperties().remove("org.joda.time.DateTimeZone.Provider");
            DateTimeZone.setProvider(null);
            // removed other assertion
        }
        
        try {
            System.setProperty("org.joda.time.DateTimeZone.Folder", "src/test/resources/tzdata");
            DateTimeZone.setProvider(null);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            
        } finally {
            System.getProperties().remove("org.joda.time.DateTimeZone.Folder");
            DateTimeZone.setProvider(null);
            // removed other assertion
            assertEquals(true,DateTimeZone.getAvailableIDs().size()> 2);
    }
    }

    public void testProvider_badClassName_1_oe() {
        try {
            System.setProperty("org.joda.time.DateTimeZone.Provider", "xxx");
            DateTimeZone.setProvider(null);
            
        } catch (RuntimeException ex) {
            // expected
            assertEquals(ZoneInfoProvider.class,DateTimeZone.getProvider().getClass());
    }
    }

    public void testNameProvider_7_oe() {
        try {
            // removed other assertion
        
            NameProvider provider = DateTimeZone.getNameProvider();
            DateTimeZone.setNameProvider(null);
            // removed other assertion
        
            provider = new MockOKButNullNameProvider();
            DateTimeZone.setNameProvider(provider);
            // removed other assertion
            
            // removed other assertion
            // removed other assertion
        } finally {
            DateTimeZone.setNameProvider(null);
        }
        
        try {
            System.setProperty("org.joda.time.DateTimeZone.NameProvider", "org.joda.time.tz.DefaultNameProvider");
            DateTimeZone.setNameProvider(null);
            // removed other assertion
        } finally {
            System.getProperties().remove("org.joda.time.DateTimeZone.NameProvider");
            DateTimeZone.setNameProvider(null);
            assertEquals(DefaultNameProvider.class,DateTimeZone.getNameProvider().getClass());
    }
    }

    public void testNameProvider_badClassName_1_oe() {
        try {
            System.setProperty("org.joda.time.DateTimeZone.NameProvider", "xxx");
            DateTimeZone.setProvider(null);
            
        } catch (RuntimeException ex) {
            // expected
            assertEquals(DefaultNameProvider.class,DateTimeZone.getNameProvider().getClass());
    }
    }

    public void testConstructor_1_oe() {
        assertEquals(1,DateTimeZone.class.getDeclaredConstructors().length);
    }

    public void testConstructor_2_oe() {
        // removed other assertion
        assertTrue(Modifier.isProtected(DateTimeZone.class.getDeclaredConstructors()[0].getModifiers()));
    }

    public void testGetID_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        assertEquals("Europe/Paris",zone.getID());
    }

    public void testGetNameKey_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        assertEquals("BST",zone.getNameKey(TEST_TIME_SUMMER));
    }

    public void testGetNameKey_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        assertEquals("GMT",zone.getNameKey(TEST_TIME_WINTER));
    }

    public void testGetShortName_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        assertEquals("BST",zone.getShortName(TEST_TIME_SUMMER));
    }

    public void testGetShortName_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        assertEquals("GMT",zone.getShortName(TEST_TIME_WINTER));
    }

    public void testGetShortName_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        assertEquals("BST",zone.getShortName(TEST_TIME_SUMMER,Locale.ENGLISH));
    }

    public void testGetShortName_berlin_1_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        assertEquals("CET",berlin.getShortName(TEST_TIME_WINTER,Locale.ENGLISH));
    }

    public void testGetShortName_berlin_2_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        assertEquals("CEST",berlin.getShortName(TEST_TIME_SUMMER,Locale.ENGLISH));
    }

    public void testGetShortName_berlin_3_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        // removed other assertion
        if (JDK6PLUS) {
          assertEquals("MEZ",berlin.getShortName(TEST_TIME_WINTER,Locale.GERMAN));
    }
    }

    public void testGetShortName_berlin_4_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        // removed other assertion
        if (JDK6PLUS) {
          // removed other assertion
          assertEquals("MESZ",berlin.getShortName(TEST_TIME_SUMMER,Locale.GERMAN));
    }
    }

    public void testGetShortName_berlin_5_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        // removed other assertion
        if (JDK6PLUS) {
          // removed other assertion
          // removed other assertion
        } else {
          assertEquals("CET",berlin.getShortName(TEST_TIME_WINTER,Locale.GERMAN));
    }
    }

    public void testGetShortName_berlin_6_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        // removed other assertion
        if (JDK6PLUS) {
          // removed other assertion
          // removed other assertion
        } else {
          // removed other assertion
          assertEquals("CEST",berlin.getShortName(TEST_TIME_SUMMER,Locale.GERMAN));
    }
    }

    public void testGetShortNameProviderName_1_oe() {
        assertEquals(null,DateTimeZone.getNameProvider().getShortName(null,"Europe/London","BST"));
    }

    public void testGetShortNameProviderName_2_oe() {
        // removed other assertion
        assertEquals(null,DateTimeZone.getNameProvider().getShortName(Locale.ENGLISH,null,"BST"));
    }

    public void testGetShortNameProviderName_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(null,DateTimeZone.getNameProvider().getShortName(Locale.ENGLISH,"Europe/London",null));
    }

    public void testGetShortNameProviderName_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,DateTimeZone.getNameProvider().getShortName(null,null,null));
    }

    public void testGetShortNameNullKey_1_oe() {
        DateTimeZone zone = new MockDateTimeZone("Europe/London");
        assertEquals("Europe/London",zone.getShortName(TEST_TIME_SUMMER,Locale.ENGLISH));
    }

    public void testGetName_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        assertEquals("British Summer Time",zone.getName(TEST_TIME_SUMMER));
    }

    public void testGetName_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        assertEquals("Greenwich Mean Time",zone.getName(TEST_TIME_WINTER));
    }

    public void testGetName_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        assertEquals("British Summer Time",zone.getName(TEST_TIME_SUMMER,Locale.ENGLISH));
    }

    public void testGetName_berlin_english_1_oe() {
      DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
      if (JDK9) {
          assertEquals("Central European Standard Time",berlin.getName(TEST_TIME_WINTER,Locale.ENGLISH));
    }
    }

    public void testGetName_berlin_english_2_oe() {
      DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
      if (JDK9) {
          // removed other assertion
      } else {
          assertEquals("Central European Time",berlin.getName(TEST_TIME_WINTER,Locale.ENGLISH));
    }
    }

    public void testGetName_berlin_english_3_oe() {
      DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
      if (JDK9) {
          // removed other assertion
      } else {
          // removed other assertion
      }
      assertEquals("Central European Summer Time",berlin.getName(TEST_TIME_SUMMER,Locale.ENGLISH));
    }

    public void testGetName_berlin_german_1_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        if (JDK9) {
            assertEquals("Mitteleurop\u00e4ische Normalzeit",berlin.getName(TEST_TIME_WINTER,Locale.GERMAN));
    }
    }

    public void testGetName_berlin_german_2_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        if (JDK9) {
            // removed other assertion
            assertEquals("Mitteleurop\u00e4ische Sommerzeit",berlin.getName(TEST_TIME_SUMMER,Locale.GERMAN));
    }
    }

    public void testGetName_berlin_german_3_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        if (JDK9) {
            // removed other assertion
            // removed other assertion
        } else if (JDK6PLUS) {
            assertEquals("Mitteleurop\u00e4ische Zeit",berlin.getName(TEST_TIME_WINTER,Locale.GERMAN));
    }
    }

    public void testGetName_berlin_german_4_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        if (JDK9) {
            // removed other assertion
            // removed other assertion
        } else if (JDK6PLUS) {
            // removed other assertion
            assertEquals("Mitteleurop\u00e4ische Sommerzeit",berlin.getName(TEST_TIME_SUMMER,Locale.GERMAN));
    }
    }

    public void testGetName_berlin_german_5_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        if (JDK9) {
            // removed other assertion
            // removed other assertion
        } else if (JDK6PLUS) {
            // removed other assertion
            // removed other assertion
        } else {
            assertEquals("Zentraleurop\u00e4ische Zeit",berlin.getName(TEST_TIME_WINTER,Locale.GERMAN));
    }
    }

    public void testGetName_berlin_german_6_oe() {
        DateTimeZone berlin = DateTimeZone.forID("Europe/Berlin");
        if (JDK9) {
            // removed other assertion
            // removed other assertion
        } else if (JDK6PLUS) {
            // removed other assertion
            // removed other assertion
        } else {
            // removed other assertion
            assertEquals("Zentraleurop\u00e4ische Sommerzeit",berlin.getName(TEST_TIME_SUMMER,Locale.GERMAN));
    }
    }

    public void testGetNameProviderName_1_oe() {
        assertEquals(null,DateTimeZone.getNameProvider().getName(null,"Europe/London","BST"));
    }

    public void testGetNameProviderName_2_oe() {
        // removed other assertion
        assertEquals(null,DateTimeZone.getNameProvider().getName(Locale.ENGLISH,null,"BST"));
    }

    public void testGetNameProviderName_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(null,DateTimeZone.getNameProvider().getName(Locale.ENGLISH,"Europe/London",null));
    }

    public void testGetNameProviderName_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null,DateTimeZone.getNameProvider().getName(null,null,null));
    }

    public void testGetNameNullKey_1_oe() {
        DateTimeZone zone = new MockDateTimeZone("Europe/London");
        assertEquals("Europe/London",zone.getName(TEST_TIME_SUMMER,Locale.ENGLISH));
    }

    public void testGetOffset_long_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        assertEquals(2L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffset(TEST_TIME_SUMMER));
    }

    public void testGetOffset_long_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffset(TEST_TIME_WINTER));
    }

    public void testGetOffset_long_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getStandardOffset(TEST_TIME_SUMMER));
    }

    public void testGetOffset_long_4_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getStandardOffset(TEST_TIME_WINTER));
    }

    public void testGetOffset_long_5_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(2L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffsetFromLocal(TEST_TIME_SUMMER));
    }

    public void testGetOffset_long_6_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffsetFromLocal(TEST_TIME_WINTER));
    }

    public void testGetOffset_long_7_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,zone.isStandardOffset(TEST_TIME_SUMMER));
    }

    public void testGetOffset_long_8_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,zone.isStandardOffset(TEST_TIME_WINTER));
    }

    public void testGetOffset_RI_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        assertEquals(2L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffset(new Instant(TEST_TIME_SUMMER)));
    }

    public void testGetOffset_RI_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffset(new Instant(TEST_TIME_WINTER)));
    }

    public void testGetOffset_RI_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        
        assertEquals(zone.getOffset(DateTimeUtils.currentTimeMillis()),zone.getOffset(null));
    }

    public void testGetOffsetFixed_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffset(TEST_TIME_SUMMER));
    }

    public void testGetOffsetFixed_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffset(TEST_TIME_WINTER));
    }

    public void testGetOffsetFixed_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getStandardOffset(TEST_TIME_SUMMER));
    }

    public void testGetOffsetFixed_4_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getStandardOffset(TEST_TIME_WINTER));
    }

    public void testGetOffsetFixed_5_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffsetFromLocal(TEST_TIME_SUMMER));
    }

    public void testGetOffsetFixed_6_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffsetFromLocal(TEST_TIME_WINTER));
    }

    public void testGetOffsetFixed_7_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(true,zone.isStandardOffset(TEST_TIME_SUMMER));
    }

    public void testGetOffsetFixed_8_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(true,zone.isStandardOffset(TEST_TIME_WINTER));
    }

    public void testGetOffsetFixed_RI_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffset(new Instant(TEST_TIME_SUMMER)));
    }

    public void testGetOffsetFixed_RI_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        assertEquals(1L * DateTimeConstants.MILLIS_PER_HOUR,zone.getOffset(new Instant(TEST_TIME_WINTER)));
    }

    public void testGetOffsetFixed_RI_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        
        assertEquals(zone.getOffset(DateTimeUtils.currentTimeMillis()),zone.getOffset(null));
    }

    public void testGetMillisKeepLocal_1_oe() {
        long millisLondon = TEST_TIME_SUMMER;
        long millisParis = TEST_TIME_SUMMER - 1L * DateTimeConstants.MILLIS_PER_HOUR;
        
        assertEquals(millisLondon,LONDON.getMillisKeepLocal(LONDON,millisLondon));
    }

    public void testGetMillisKeepLocal_2_oe() {
        long millisLondon = TEST_TIME_SUMMER;
        long millisParis = TEST_TIME_SUMMER - 1L * DateTimeConstants.MILLIS_PER_HOUR;
        
        // removed other assertion
        assertEquals(millisParis,LONDON.getMillisKeepLocal(LONDON,millisParis));
    }

    public void testGetMillisKeepLocal_3_oe() {
        long millisLondon = TEST_TIME_SUMMER;
        long millisParis = TEST_TIME_SUMMER - 1L * DateTimeConstants.MILLIS_PER_HOUR;
        
        // removed other assertion
        // removed other assertion
        assertEquals(millisLondon,PARIS.getMillisKeepLocal(PARIS,millisLondon));
    }

    public void testGetMillisKeepLocal_4_oe() {
        long millisLondon = TEST_TIME_SUMMER;
        long millisParis = TEST_TIME_SUMMER - 1L * DateTimeConstants.MILLIS_PER_HOUR;
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(millisParis,PARIS.getMillisKeepLocal(PARIS,millisParis));
    }

    public void testGetMillisKeepLocal_5_oe() {
        long millisLondon = TEST_TIME_SUMMER;
        long millisParis = TEST_TIME_SUMMER - 1L * DateTimeConstants.MILLIS_PER_HOUR;
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(millisParis,LONDON.getMillisKeepLocal(PARIS,millisLondon));
    }

    public void testGetMillisKeepLocal_6_oe() {
        long millisLondon = TEST_TIME_SUMMER;
        long millisParis = TEST_TIME_SUMMER - 1L * DateTimeConstants.MILLIS_PER_HOUR;
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(millisLondon,PARIS.getMillisKeepLocal(LONDON,millisParis));
    }

    public void testIsFixed_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        assertEquals(false,zone.isFixed());
    }

    public void testIsFixed_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        assertEquals(true,DateTimeZone.UTC.isFixed());
    }

    public void testTransitionFixed_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        assertEquals(TEST_TIME_SUMMER,zone.nextTransition(TEST_TIME_SUMMER));
    }

    public void testTransitionFixed_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        assertEquals(TEST_TIME_WINTER,zone.nextTransition(TEST_TIME_WINTER));
    }

    public void testTransitionFixed_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_SUMMER,zone.previousTransition(TEST_TIME_SUMMER));
    }

    public void testTransitionFixed_4_oe() {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEST_TIME_WINTER,zone.previousTransition(TEST_TIME_WINTER));
    }

    public void testIsLocalDateTimeGap_Berlin_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Berlin");
        assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,25,1,0)));
    }

    public void testIsLocalDateTimeGap_Berlin_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,25,1,59,59,99)));
    }

    public void testIsLocalDateTimeGap_Berlin_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,25,2,0)));
    }

    public void testIsLocalDateTimeGap_Berlin_4_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,25,2,30)));
    }

    public void testIsLocalDateTimeGap_Berlin_5_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,25,2,59,59,99)));
    }

    public void testIsLocalDateTimeGap_Berlin_6_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,25,3,0)));
    }

    public void testIsLocalDateTimeGap_Berlin_7_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,25,4,0)));
    }

    public void testIsLocalDateTimeGap_Berlin_8_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Berlin");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,10,28,1,30)));// before overlap assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,10,28,2,30)));// overlap assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,10,28,3,30)));// after overlap assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,12,24,12,34)));
    }

    public void testIsLocalDateTimeGap_NewYork_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,11,1,0)));
    }

    public void testIsLocalDateTimeGap_NewYork_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        // removed other assertion
        assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,11,1,59,59,99)));
    }

    public void testIsLocalDateTimeGap_NewYork_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,11,2,0)));
    }

    public void testIsLocalDateTimeGap_NewYork_4_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,11,2,30)));
    }

    public void testIsLocalDateTimeGap_NewYork_5_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,11,2,59,59,99)));
    }

    public void testIsLocalDateTimeGap_NewYork_6_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,11,3,0)));
    }

    public void testIsLocalDateTimeGap_NewYork_7_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,3,11,4,0)));
    }

    public void testIsLocalDateTimeGap_NewYork_8_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,11,4,0,30)));// before overlap assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,11,4,1,30)));// overlap assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,11,4,2,30)));// after overlap assertEquals(false,zone.isLocalDateTimeGap(new LocalDateTime(2007,12,24,12,34)));
    }

    public void testToTimeZone_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        TimeZone tz = zone.toTimeZone();
        assertEquals("Europe/Paris",tz.getID());
    }

    public void testEqualsHashCode_1_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        assertEquals(true,zone1.equals(zone1));
    }

    public void testEqualsHashCode_2_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        assertEquals(true,zone1.equals(zone2));
    }

    public void testEqualsHashCode_3_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone2.equals(zone1));
    }

    public void testEqualsHashCode_4_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone2.equals(zone2));
    }

    public void testEqualsHashCode_5_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone1.hashCode()== zone2.hashCode());
    }

    public void testEqualsHashCode_6_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        assertEquals(true,zone3.equals(zone3));
    }

    public void testEqualsHashCode_7_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        assertEquals(false,zone1.equals(zone3));
    }

    public void testEqualsHashCode_8_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone2.equals(zone3));
    }

    public void testEqualsHashCode_9_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone3.equals(zone1));
    }

    public void testEqualsHashCode_10_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone3.equals(zone2));
    }

    public void testEqualsHashCode_11_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone1.hashCode()== zone3.hashCode());
    }

    public void testEqualsHashCode_12_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone3.hashCode()== zone3.hashCode());
    }

    public void testEqualsHashCode_13_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        assertEquals(true,zone4.equals(zone4));
    }

    public void testEqualsHashCode_14_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        assertEquals(false,zone1.equals(zone4));
    }

    public void testEqualsHashCode_15_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone2.equals(zone4));
    }

    public void testEqualsHashCode_16_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone3.equals(zone4));
    }

    public void testEqualsHashCode_17_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone4.equals(zone1));
    }

    public void testEqualsHashCode_18_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone4.equals(zone2));
    }

    public void testEqualsHashCode_19_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone4.equals(zone3));
    }

    public void testEqualsHashCode_20_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone1.hashCode()== zone4.hashCode());
    }

    public void testEqualsHashCode_21_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone4.hashCode()== zone4.hashCode());
    }

    public void testEqualsHashCode_22_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        assertEquals(true,zone5.equals(zone5));
    }

    public void testEqualsHashCode_23_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        // removed other assertion
        assertEquals(false,zone1.equals(zone5));
    }

    public void testEqualsHashCode_24_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone2.equals(zone5));
    }

    public void testEqualsHashCode_25_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone3.equals(zone5));
    }

    public void testEqualsHashCode_26_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone4.equals(zone5));
    }

    public void testEqualsHashCode_27_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone5.equals(zone1));
    }

    public void testEqualsHashCode_28_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone5.equals(zone2));
    }

    public void testEqualsHashCode_29_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone5.equals(zone3));
    }

    public void testEqualsHashCode_30_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone5.equals(zone4));
    }

    public void testEqualsHashCode_31_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone1.hashCode()== zone5.hashCode());
    }

    public void testEqualsHashCode_32_oe() {
        DateTimeZone zone1 = DateTimeZone.forID("Europe/Paris");
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone3 = DateTimeZone.forID("Europe/London");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone4 = DateTimeZone.forID("+01:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeZone zone5 = DateTimeZone.forID("+02:00");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone5.hashCode()== zone5.hashCode());
    }

    public void testToString_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        assertEquals("Europe/Paris",zone.toString());
    }

    public void testToString_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        // removed other assertion
        assertEquals("UTC",DateTimeZone.UTC.toString());
    }

    public void testDublin_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Dublin");
        DateTime winter = new DateTime(2018, 1, 1, 0, 0, 0, 0, zone);
        assertEquals(0,zone.getStandardOffset(winter.getMillis()));
    }

    public void testDublin_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Dublin");
        DateTime winter = new DateTime(2018, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        assertEquals(0,zone.getOffset(winter.getMillis()));
    }

    public void testDublin_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Dublin");
        DateTime winter = new DateTime(2018, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone.isStandardOffset(winter.getMillis()));
    }

    public void testDublin_4_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Dublin");
        DateTime winter = new DateTime(2018, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Greenwich Mean Time",zone.getName(winter.getMillis()));
    }

    public void testDublin_5_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Dublin");
        DateTime winter = new DateTime(2018, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("GMT",zone.getNameKey(winter.getMillis()));
    }

    public void testDublin_6_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Dublin");
        DateTime winter = new DateTime(2018, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime summer = winter.plusMonths(6);
        assertEquals(0,zone.getStandardOffset(summer.getMillis()));
    }

    public void testDublin_7_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Dublin");
        DateTime winter = new DateTime(2018, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime summer = winter.plusMonths(6);
        // removed other assertion
        assertEquals(3600000,zone.getOffset(summer.getMillis()));
    }

    public void testDublin_8_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Dublin");
        DateTime winter = new DateTime(2018, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime summer = winter.plusMonths(6);
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone.isStandardOffset(summer.getMillis()));
    }

    public void testDublin_9_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Dublin");
        DateTime winter = new DateTime(2018, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime summer = winter.plusMonths(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone.getName(summer.getMillis()).startsWith("Irish "));
    }

    public void testDublin_10_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Dublin");
        DateTime winter = new DateTime(2018, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime summer = winter.plusMonths(6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("IST",zone.getNameKey(summer.getMillis()));
    }

    public void testWindhoek_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Africa/Windhoek");
        DateTime dtDec1990 = new DateTime(1990, 12, 1, 0, 0, 0, 0, zone);
        assertEquals(3600000,zone.getStandardOffset(dtDec1990.getMillis()));
    }

    public void testWindhoek_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Africa/Windhoek");
        DateTime dtDec1990 = new DateTime(1990, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        assertEquals(7200000,zone.getOffset(dtDec1990.getMillis()));
    }

    public void testWindhoek_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("Africa/Windhoek");
        DateTime dtDec1990 = new DateTime(1990, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone.isStandardOffset(dtDec1990.getMillis()));
    }

    public void testWindhoek_4_oe() {
        DateTimeZone zone = DateTimeZone.forID("Africa/Windhoek");
        DateTime dtDec1990 = new DateTime(1990, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime dtDec1994 = new DateTime(1994, 12, 1, 0, 0, 0, 0, zone);
        assertEquals(3600000,zone.getStandardOffset(dtDec1994.getMillis()));
    }

    public void testWindhoek_5_oe() {
        DateTimeZone zone = DateTimeZone.forID("Africa/Windhoek");
        DateTime dtDec1990 = new DateTime(1990, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime dtDec1994 = new DateTime(1994, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        assertEquals(7200000,zone.getOffset(dtDec1994.getMillis()));
    }

    public void testWindhoek_6_oe() {
        DateTimeZone zone = DateTimeZone.forID("Africa/Windhoek");
        DateTime dtDec1990 = new DateTime(1990, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime dtDec1994 = new DateTime(1994, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        assertEquals(false,zone.isStandardOffset(dtDec1994.getMillis()));
    }

    public void testWindhoek_7_oe() {
        DateTimeZone zone = DateTimeZone.forID("Africa/Windhoek");
        DateTime dtDec1990 = new DateTime(1990, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime dtDec1994 = new DateTime(1994, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime dtJun1995 = new DateTime(1995, 6, 1, 0, 0, 0, 0, zone);
        assertEquals(3600000,zone.getStandardOffset(dtJun1995.getMillis()));
    }

    public void testWindhoek_8_oe() {
        DateTimeZone zone = DateTimeZone.forID("Africa/Windhoek");
        DateTime dtDec1990 = new DateTime(1990, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime dtDec1994 = new DateTime(1994, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime dtJun1995 = new DateTime(1995, 6, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        assertEquals(3600000,zone.getOffset(dtJun1995.getMillis()));
    }

    public void testWindhoek_9_oe() {
        DateTimeZone zone = DateTimeZone.forID("Africa/Windhoek");
        DateTime dtDec1990 = new DateTime(1990, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime dtDec1994 = new DateTime(1994, 12, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTime dtJun1995 = new DateTime(1995, 6, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion
        assertEquals(true,zone.isStandardOffset(dtJun1995.getMillis()));
    }

    public void testToronto_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        assertEquals(-5 * 3600000,zone.getStandardOffset(start.getMillis()));
    }

    public void testToronto_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        assertEquals(-5 * 3600000,zone.getOffset(start.getMillis()));
    }

    public void testToronto_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        assertEquals(-4 * 3600000,zone.getOffset(summer1927.getMillis()));
    }

    public void testToronto_4_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1927,5,1),summer1927.toLocalDate());
    }

    public void testToronto_5_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,summer1927.getDayOfWeek());
    }

    public void testToronto_6_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        assertEquals(-5 * 3600000,zone.getOffset(winter1927.getMillis()));
    }

    public void testToronto_7_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1927,9,25),winter1927.toLocalDate());
    }

    public void testToronto_8_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,winter1927.getDayOfWeek());
    }

    public void testToronto_9_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        assertEquals(-4 * 3600000,zone.getOffset(summer1928.getMillis()));
    }

    public void testToronto_10_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1928,4,29),summer1928.toLocalDate());
    }

    public void testToronto_11_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,summer1928.getDayOfWeek());
    }

    public void testToronto_12_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        assertEquals(-5 * 3600000,zone.getOffset(winter1928.getMillis()));
    }

    public void testToronto_13_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1928,9,30),winter1928.toLocalDate());
    }

    public void testToronto_14_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,winter1928.getDayOfWeek());
    }

    public void testToronto_15_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        assertEquals(-4 * 3600000,zone.getOffset(summer1929.getMillis()));
    }

    public void testToronto_16_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1929,4,28),summer1929.toLocalDate());
    }

    public void testToronto_17_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,summer1929.getDayOfWeek());
    }

    public void testToronto_18_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        assertEquals(-5 * 3600000,zone.getOffset(winter1929.getMillis()));
    }

    public void testToronto_19_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1929,9,29),winter1929.toLocalDate());
    }

    public void testToronto_20_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,winter1929.getDayOfWeek());
    }

    public void testToronto_21_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        assertEquals(-4 * 3600000,zone.getOffset(summer1930.getMillis()));
    }

    public void testToronto_22_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1930,4,27),summer1930.toLocalDate());
    }

    public void testToronto_23_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,summer1930.getDayOfWeek());
    }

    public void testToronto_24_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        assertEquals(-5 * 3600000,zone.getOffset(winter1930.getMillis()));
    }

    public void testToronto_25_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1930,9,28),winter1930.toLocalDate());
    }

    public void testToronto_26_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,winter1930.getDayOfWeek());
    }

    public void testToronto_27_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        assertEquals(-4 * 3600000,zone.getOffset(summer1931.getMillis()));
    }

    public void testToronto_28_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1931,4,26),summer1931.toLocalDate());
    }

    public void testToronto_29_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,summer1931.getDayOfWeek());
    }

    public void testToronto_30_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        assertEquals(-5 * 3600000,zone.getOffset(winter1931.getMillis()));
    }

    public void testToronto_31_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1931,9,27),winter1931.toLocalDate());
    }

    public void testToronto_32_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,winter1931.getDayOfWeek());
    }

    public void testToronto_33_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        assertEquals(-4 * 3600000,zone.getOffset(summer1932.getMillis()));
    }

    public void testToronto_34_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1932,5,1),summer1932.toLocalDate());
    }

    public void testToronto_35_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,summer1932.getDayOfWeek());
    }

    public void testToronto_36_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1932 = new DateTime(zone.nextTransition(summer1932.getMillis()), zone);
        assertEquals(-5 * 3600000,zone.getOffset(winter1932.getMillis()));
    }

    public void testToronto_37_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1932 = new DateTime(zone.nextTransition(summer1932.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1932,9,25),winter1932.toLocalDate());
    }

    public void testToronto_38_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1932 = new DateTime(zone.nextTransition(summer1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,winter1932.getDayOfWeek());
    }

    public void testToronto_39_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1932 = new DateTime(zone.nextTransition(summer1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1933 = new DateTime(zone.nextTransition(winter1932.getMillis()), zone);
        assertEquals(-4 * 3600000,zone.getOffset(summer1933.getMillis()));
    }

    public void testToronto_40_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1932 = new DateTime(zone.nextTransition(summer1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1933 = new DateTime(zone.nextTransition(winter1932.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1933,4,30),summer1933.toLocalDate());
    }

    public void testToronto_41_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1932 = new DateTime(zone.nextTransition(summer1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1933 = new DateTime(zone.nextTransition(winter1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,summer1933.getDayOfWeek());
    }

    public void testToronto_42_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1932 = new DateTime(zone.nextTransition(summer1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1933 = new DateTime(zone.nextTransition(winter1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1933 = new DateTime(zone.nextTransition(summer1933.getMillis()), zone);
        assertEquals(-5 * 3600000,zone.getOffset(winter1933.getMillis()));
    }

    public void testToronto_43_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1932 = new DateTime(zone.nextTransition(summer1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1933 = new DateTime(zone.nextTransition(winter1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1933 = new DateTime(zone.nextTransition(summer1933.getMillis()), zone);
        // removed other assertion
        assertEquals(new LocalDate(1933,10,1),winter1933.toLocalDate());
    }

    public void testToronto_44_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Toronto");
        DateTime start = new DateTime(1927, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion
        // removed other assertion

        DateTime summer1927 = new DateTime(zone.nextTransition(start.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1927 = new DateTime(zone.nextTransition(summer1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1928 = new DateTime(zone.nextTransition(winter1927.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1928 = new DateTime(zone.nextTransition(summer1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1929 = new DateTime(zone.nextTransition(winter1928.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1929 = new DateTime(zone.nextTransition(summer1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1930 = new DateTime(zone.nextTransition(winter1929.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1930 = new DateTime(zone.nextTransition(summer1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1931 = new DateTime(zone.nextTransition(winter1930.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1931 = new DateTime(zone.nextTransition(summer1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1932 = new DateTime(zone.nextTransition(winter1931.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1932 = new DateTime(zone.nextTransition(summer1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime summer1933 = new DateTime(zone.nextTransition(winter1932.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        DateTime winter1933 = new DateTime(zone.nextTransition(summer1933.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(DateTimeConstants.SUNDAY,winter1933.getDayOfWeek());
    }

    public void testJerusalem_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Asia/Jerusalem");
        DateTime winter = new DateTime(2006, 1, 1, 0, 0, 0, 0, zone);
        assertEquals(true,zone.isStandardOffset(winter.getMillis()));
    }

    public void testJerusalem_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Asia/Jerusalem");
        DateTime winter = new DateTime(2006, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion

        DateTime cutover = new DateTime(zone.nextTransition(winter.getMillis()), zone);
        assertEquals(false,zone.isStandardOffset(cutover.getMillis()));
    }

    public void testJerusalem_3_oe() {
        DateTimeZone zone = DateTimeZone.forID("Asia/Jerusalem");
        DateTime winter = new DateTime(2006, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion

        DateTime cutover = new DateTime(zone.nextTransition(winter.getMillis()), zone);
        // removed other assertion
        assertEquals(5,cutover.getDayOfWeek());
    }

    public void testJerusalem_4_oe() {
        DateTimeZone zone = DateTimeZone.forID("Asia/Jerusalem");
        DateTime winter = new DateTime(2006, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion

        DateTime cutover = new DateTime(zone.nextTransition(winter.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        assertEquals(31,cutover.getDayOfMonth());
    }

    public void testJerusalem_5_oe() {
        DateTimeZone zone = DateTimeZone.forID("Asia/Jerusalem");
        DateTime winter = new DateTime(2006, 1, 1, 0, 0, 0, 0, zone);
        // removed other assertion

        DateTime cutover = new DateTime(zone.nextTransition(winter.getMillis()), zone);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3,cutover.getMonthOfYear());
    }

    public void testSerialization1_1_oe() throws Exception {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(zone);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DateTimeZone result = (DateTimeZone) ois.readObject();
        ois.close();
        
        assertSame(zone,result);
    }

    public void testSerialization2_1_oe() throws Exception {
        DateTimeZone zone = DateTimeZone.forID("+01:00");
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(zone);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DateTimeZone result = (DateTimeZone) ois.readObject();
        ois.close();
        
        assertEquals(zone,result);
    }

    public void testCommentParse_1_oe() throws Exception {
        // A bug in ZoneInfoCompiler's handling of comments broke Europe/Athens
        // after 1980. This test is included to make sure it doesn't break again.

        DateTimeZone zone = DateTimeZone.forID("Europe/Athens");
        DateTime dt = new DateTime(2005, 5, 5, 20, 10, 15, 0, zone);
        assertEquals(1115313015000L,dt.getMillis());
    }

    public void testPatchedNameKeysLondon_1_oe() throws Exception {
        // the tz database does not have unique name keys [1716305]
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        
        DateTime now = new DateTime(2007, 1, 1, 0, 0, 0, 0);
        String str1 = zone.getName(now.getMillis());
        String str2 = zone.getName(now.plusMonths(6).getMillis());
        assertEquals(false,str1.equals(str2));
    }

    public void testPatchedNameKeysSydney_1_oe() throws Exception {
        // the tz database does not have unique name keys [1716305]
        DateTimeZone zone = DateTimeZone.forID("Australia/Sydney");
        
        DateTime now = new DateTime(2007, 1, 1, 0, 0, 0, 0);
        String str1 = zone.getName(now.getMillis());
        String str2 = zone.getName(now.plusMonths(6).getMillis());
        assertEquals(false,str1.equals(str2));
    }

    public void testPatchedNameKeysSydneyHistoric_1_oe() throws Exception {
        // the tz database does not have unique name keys [1716305]
        DateTimeZone zone = DateTimeZone.forID("Australia/Sydney");
        
        DateTime now = new DateTime(1996, 1, 1, 0, 0, 0, 0);
        String str1 = zone.getName(now.getMillis());
        String str2 = zone.getName(now.plusMonths(6).getMillis());
        assertEquals(false,str1.equals(str2));
    }

    public void testPatchedNameKeysGazaHistoric_1_oe() throws Exception {
        // the tz database does not have unique name keys [1716305]
        DateTimeZone zone = DateTimeZone.forID("Africa/Johannesburg");
        
        DateTime now = new DateTime(1943, 1, 1, 0, 0, 0, 0);
        String str1 = zone.getName(now.getMillis());
        String str2 = zone.getName(now.plusMonths(6).getMillis());
        assertEquals(false,str1.equals(str2));
    }

}
