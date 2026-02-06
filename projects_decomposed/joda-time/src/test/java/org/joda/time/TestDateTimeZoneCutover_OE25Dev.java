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

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.GregorianChronology;
import org.joda.time.tz.DateTimeZoneBuilder;

/**
 * This class is a JUnit test for DateTimeZone.
 *
 * @author Stephen Colebourne
 */
public class TestDateTimeZoneCutover_OE25Dev extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateTimeZoneCutover_OE25Dev.class);
    }

    public TestDateTimeZoneCutover_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    //-----------------------------------------------------------------------
    //------------------------ Bug [1710316] --------------------------------
    //-----------------------------------------------------------------------
    // The behaviour of getOffsetFromLocal is defined in its javadoc
    // However, this definition doesn't work for all DateTimeField operations
    
    /** Mock zone simulating Asia/Gaza cutover at midnight 2007-04-01 */
    private static long CUTOVER_GAZA = 1175378400000L;
    private static int OFFSET_GAZA = 7200000;  // +02:00
    private static final DateTimeZone MOCK_GAZA = new MockZone(CUTOVER_GAZA, OFFSET_GAZA, 3600);

    //-----------------------------------------------------------------------

    public void test_getOffsetFromLocal_Gaza() {
        doTest_getOffsetFromLocal_Gaza(-1, 23, 0, "2007-03-31T23:00:00.000+02:00");
        doTest_getOffsetFromLocal_Gaza(-1, 23, 30, "2007-03-31T23:30:00.000+02:00");
        doTest_getOffsetFromLocal_Gaza(0, 0, 0, "2007-04-01T01:00:00.000+03:00");
        doTest_getOffsetFromLocal_Gaza(0, 0, 30, "2007-04-01T01:30:00.000+03:00");
        doTest_getOffsetFromLocal_Gaza(0, 1, 0, "2007-04-01T01:00:00.000+03:00");
        doTest_getOffsetFromLocal_Gaza(0, 1, 30, "2007-04-01T01:30:00.000+03:00");
        doTest_getOffsetFromLocal_Gaza(0, 2, 0, "2007-04-01T02:00:00.000+03:00");
        doTest_getOffsetFromLocal_Gaza(0, 3, 0, "2007-04-01T03:00:00.000+03:00");
        doTest_getOffsetFromLocal_Gaza(0, 4, 0, "2007-04-01T04:00:00.000+03:00");
        doTest_getOffsetFromLocal_Gaza(0, 5, 0, "2007-04-01T05:00:00.000+03:00");
        doTest_getOffsetFromLocal_Gaza(0, 6, 0, "2007-04-01T06:00:00.000+03:00");
    }

    private void doTest_getOffsetFromLocal_Gaza(int days, int hour, int min, String expected) {
        DateTime dt = new DateTime(2007, 4, 1, hour, min, 0, 0, DateTimeZone.UTC).plusDays(days);
        int offset = MOCK_GAZA.getOffsetFromLocal(dt.getMillis());
        DateTime res = new DateTime(dt.getMillis() - offset, MOCK_GAZA);
        assertEquals(res.toString(),expected,res.toString());
    }

    public void test_DateTime_newValid_Gaza() {
        new DateTime(2007, 3, 31, 19, 0, 0, 0, MOCK_GAZA);
        new DateTime(2007, 3, 31, 20, 0, 0, 0, MOCK_GAZA);
        new DateTime(2007, 3, 31, 21, 0, 0, 0, MOCK_GAZA);
        new DateTime(2007, 3, 31, 22, 0, 0, 0, MOCK_GAZA);
        new DateTime(2007, 3, 31, 23, 0, 0, 0, MOCK_GAZA);
        new DateTime(2007, 4, 1, 1, 0, 0, 0, MOCK_GAZA);
        new DateTime(2007, 4, 1, 2, 0, 0, 0, MOCK_GAZA);
        new DateTime(2007, 4, 1, 3, 0, 0, 0, MOCK_GAZA);
    }

    //-----------------------------------------------------------------------
    //------------------------ Bug [1710316] --------------------------------
    //-----------------------------------------------------------------------
    /** Mock zone simulating America/Grand_Turk cutover at midnight 2007-04-01 */
    private static long CUTOVER_TURK = 1175403600000L;
    private static int OFFSET_TURK = -18000000;  // -05:00
    private static final DateTimeZone MOCK_TURK = new MockZone(CUTOVER_TURK, OFFSET_TURK, 3600);

    //-----------------------------------------------------------------------

    public void test_getOffsetFromLocal_Turk() {
        doTest_getOffsetFromLocal_Turk(-1, 23, 0, "2007-03-31T23:00:00.000-05:00", -5);
        doTest_getOffsetFromLocal_Turk(-1, 23, 30, "2007-03-31T23:30:00.000-05:00", -5);
        doTest_getOffsetFromLocal_Turk(0, 0, 0, "2007-04-01T01:00:00.000-04:00", -5);
        doTest_getOffsetFromLocal_Turk(0, 0, 30, "2007-04-01T01:30:00.000-04:00", -5);
        doTest_getOffsetFromLocal_Turk(0, 1, 0, "2007-04-01T01:00:00.000-04:00", -4);
        doTest_getOffsetFromLocal_Turk(0, 1, 30, "2007-04-01T01:30:00.000-04:00", -4);
        doTest_getOffsetFromLocal_Turk(0, 2, 0, "2007-04-01T02:00:00.000-04:00", -4);
        doTest_getOffsetFromLocal_Turk(0, 3, 0, "2007-04-01T03:00:00.000-04:00", -4);
        doTest_getOffsetFromLocal_Turk(0, 4, 0, "2007-04-01T04:00:00.000-04:00", -4);
        doTest_getOffsetFromLocal_Turk(0, 5, 0, "2007-04-01T05:00:00.000-04:00", -4);
        doTest_getOffsetFromLocal_Turk(0, 6, 0, "2007-04-01T06:00:00.000-04:00", -4);
    }

    private void doTest_getOffsetFromLocal_Turk(int days, int hour, int min, String expected, int expOffset) {
        DateTime dt = new DateTime(2007, 4, 1, hour, min, 0, 0, DateTimeZone.UTC).plusDays(days);
        int offset = MOCK_TURK.getOffsetFromLocal(dt.getMillis());
        assertEquals(expOffset * 3600000L,offset);
        DateTime res = new DateTime(dt.getMillis() - offset, MOCK_TURK);
        assertEquals(res.toString(),expected,res.toString());
    }

    public void test_DateTime_newValid_Turk() {
        new DateTime(2007, 3, 31, 23, 0, 0, 0, MOCK_TURK);
        new DateTime(2007, 4, 1, 1, 0, 0, 0, MOCK_TURK);
        new DateTime(2007, 4, 1, 2, 0, 0, 0, MOCK_TURK);
        new DateTime(2007, 4, 1, 3, 0, 0, 0, MOCK_TURK);
        new DateTime(2007, 4, 1, 4, 0, 0, 0, MOCK_TURK);
        new DateTime(2007, 4, 1, 5, 0, 0, 0, MOCK_TURK);
        new DateTime(2007, 4, 1, 6, 0, 0, 0, MOCK_TURK);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    /** America/New_York cutover from 01:59 to 03:00 on 2007-03-11 */
    private static long CUTOVER_NEW_YORK_SPRING = 1173596400000L;  // 2007-03-11T03:00:00.000-04:00
    private static final DateTimeZone ZONE_NEW_YORK = DateTimeZone.forID("America/New_York");
//  DateTime x = new DateTime(2007, 1, 1, 0, 0, 0, 0, ZONE_NEW_YORK);
//  System.out.println(ZONE_NEW_YORK.nextTransition(x.getMillis()));
//  DateTime y = new DateTime(ZONE_NEW_YORK.nextTransition(x.getMillis()), ZONE_NEW_YORK);
//  System.out.println(y);

    //-----------------------------------------------------------------------

    public void test_getOffsetFromLocal_NewYork_Spring() {
        doTest_getOffsetFromLocal(3, 11, 1, 0, "2007-03-11T01:00:00.000-05:00", -5, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(3, 11, 1,30, "2007-03-11T01:30:00.000-05:00", -5, ZONE_NEW_YORK);
        
        doTest_getOffsetFromLocal(3, 11, 2, 0, "2007-03-11T03:00:00.000-04:00", -5, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(3, 11, 2,30, "2007-03-11T03:30:00.000-04:00", -5, ZONE_NEW_YORK);
        
        doTest_getOffsetFromLocal(3, 11, 3, 0, "2007-03-11T03:00:00.000-04:00", -4, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(3, 11, 3,30, "2007-03-11T03:30:00.000-04:00", -4, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(3, 11, 4, 0, "2007-03-11T04:00:00.000-04:00", -4, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(3, 11, 5, 0, "2007-03-11T05:00:00.000-04:00", -4, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(3, 11, 6, 0, "2007-03-11T06:00:00.000-04:00", -4, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(3, 11, 7, 0, "2007-03-11T07:00:00.000-04:00", -4, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(3, 11, 8, 0, "2007-03-11T08:00:00.000-04:00", -4, ZONE_NEW_YORK);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    /** America/New_York cutover from 01:59 to 01:00 on 2007-11-04 */
    private static long CUTOVER_NEW_YORK_AUTUMN = 1194156000000L;  // 2007-11-04T01:00:00.000-05:00

    //-----------------------------------------------------------------------

    public void test_getOffsetFromLocal_NewYork_Autumn() {
        doTest_getOffsetFromLocal(11, 4, 0, 0, "2007-11-04T00:00:00.000-04:00", -4, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(11, 4, 0,30, "2007-11-04T00:30:00.000-04:00", -4, ZONE_NEW_YORK);
        
        doTest_getOffsetFromLocal(11, 4, 1, 0, "2007-11-04T01:00:00.000-04:00", -4, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(11, 4, 1,30, "2007-11-04T01:30:00.000-04:00", -4, ZONE_NEW_YORK);
        
        doTest_getOffsetFromLocal(11, 4, 2, 0, "2007-11-04T02:00:00.000-05:00", -5, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(11, 4, 2,30, "2007-11-04T02:30:00.000-05:00", -5, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(11, 4, 3, 0, "2007-11-04T03:00:00.000-05:00", -5, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(11, 4, 3,30, "2007-11-04T03:30:00.000-05:00", -5, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(11, 4, 4, 0, "2007-11-04T04:00:00.000-05:00", -5, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(11, 4, 5, 0, "2007-11-04T05:00:00.000-05:00", -5, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(11, 4, 6, 0, "2007-11-04T06:00:00.000-05:00", -5, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(11, 4, 7, 0, "2007-11-04T07:00:00.000-05:00", -5, ZONE_NEW_YORK);
        doTest_getOffsetFromLocal(11, 4, 8, 0, "2007-11-04T08:00:00.000-05:00", -5, ZONE_NEW_YORK);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    /** Europe/Moscow cutover from 01:59 to 03:00 on 2007-03-25 */
    private static long CUTOVER_MOSCOW_SPRING = 1174777200000L;  // 2007-03-25T03:00:00.000+04:00
    private static final DateTimeZone ZONE_MOSCOW = DateTimeZone.forID("Europe/Moscow");

    //-----------------------------------------------------------------------

    public void test_getOffsetFromLocal_Moscow_Spring() {
        doTest_getOffsetFromLocal(3, 25, 1, 0, "2007-03-25T01:00:00.000+03:00", 3, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(3, 25, 1,30, "2007-03-25T01:30:00.000+03:00", 3, ZONE_MOSCOW);
        
        doTest_getOffsetFromLocal(3, 25, 2, 0, "2007-03-25T03:00:00.000+04:00", 3, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(3, 25, 2,30, "2007-03-25T03:30:00.000+04:00", 3, ZONE_MOSCOW);
        
        doTest_getOffsetFromLocal(3, 25, 3, 0, "2007-03-25T03:00:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(3, 25, 3,30, "2007-03-25T03:30:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(3, 25, 4, 0, "2007-03-25T04:00:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(3, 25, 5, 0, "2007-03-25T05:00:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(3, 25, 6, 0, "2007-03-25T06:00:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(3, 25, 7, 0, "2007-03-25T07:00:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(3, 25, 8, 0, "2007-03-25T08:00:00.000+04:00", 4, ZONE_MOSCOW);
    }

    //-----------------------------------------------------------------------
    /** America/New_York cutover from 02:59 to 02:00 on 2007-10-28 */
    private static long CUTOVER_MOSCOW_AUTUMN = 1193526000000L;  // 2007-10-28T02:00:00.000+03:00

    //-----------------------------------------------------------------------

    public void test_getOffsetFromLocal_Moscow_Autumn() {
        doTest_getOffsetFromLocal(10, 28, 0, 0, "2007-10-28T00:00:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 0,30, "2007-10-28T00:30:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 1, 0, "2007-10-28T01:00:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 1,30, "2007-10-28T01:30:00.000+04:00", 4, ZONE_MOSCOW);
        
        doTest_getOffsetFromLocal(10, 28, 2, 0, "2007-10-28T02:00:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 2,30, "2007-10-28T02:30:00.000+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 2,30,59,999, "2007-10-28T02:30:59.999+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 2,59,59,998, "2007-10-28T02:59:59.998+04:00", 4, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 2,59,59,999, "2007-10-28T02:59:59.999+04:00", 4, ZONE_MOSCOW);
        
        doTest_getOffsetFromLocal(10, 28, 3, 0, "2007-10-28T03:00:00.000+03:00", 3, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 3,30, "2007-10-28T03:30:00.000+03:00", 3, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 4, 0, "2007-10-28T04:00:00.000+03:00", 3, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 5, 0, "2007-10-28T05:00:00.000+03:00", 3, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 6, 0, "2007-10-28T06:00:00.000+03:00", 3, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 7, 0, "2007-10-28T07:00:00.000+03:00", 3, ZONE_MOSCOW);
        doTest_getOffsetFromLocal(10, 28, 8, 0, "2007-10-28T08:00:00.000+03:00", 3, ZONE_MOSCOW);
    }

    public void test_getOffsetFromLocal_Moscow_Autumn_overlap_mins() {
        for (int min = 0; min < 60; min++) {
            if (min < 10) {
                doTest_getOffsetFromLocal(10, 28, 2, min, "2007-10-28T02:0" + min + ":00.000+04:00", 4, ZONE_MOSCOW);
            } else {
                doTest_getOffsetFromLocal(10, 28, 2, min, "2007-10-28T02:" + min + ":00.000+04:00", 4, ZONE_MOSCOW);
            }
        }
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    /** America/Guatemala cutover from 23:59 to 23:00 on 2006-09-30 */
    private static long CUTOVER_GUATEMALA_AUTUMN = 1159678800000L; // 2006-09-30T23:00:00.000-06:00
    private static final DateTimeZone ZONE_GUATEMALA = DateTimeZone.forID("America/Guatemala");

    //-----------------------------------------------------------------------

    public void test_getOffsetFromLocal_Guatemata_Autumn() {
        doTest_getOffsetFromLocal( 2006, 9,30,23, 0,
                                  "2006-09-30T23:00:00.000-05:00", -5, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006, 9,30,23,30,
                                  "2006-09-30T23:30:00.000-05:00", -5, ZONE_GUATEMALA);
        
        doTest_getOffsetFromLocal( 2006, 9,30,23, 0,
                                  "2006-09-30T23:00:00.000-05:00", -5, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006, 9,30,23,30,
                                  "2006-09-30T23:30:00.000-05:00", -5, ZONE_GUATEMALA);
        
        doTest_getOffsetFromLocal( 2006,10, 1, 0, 0,
                                  "2006-10-01T00:00:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 0,30,
                                  "2006-10-01T00:30:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 1, 0,
                                  "2006-10-01T01:00:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 1,30,
                                  "2006-10-01T01:30:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 2, 0,
                                  "2006-10-01T02:00:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 2,30,
                                  "2006-10-01T02:30:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 3, 0,
                                  "2006-10-01T03:00:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 3,30,
                                  "2006-10-01T03:30:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 4, 0,
                                  "2006-10-01T04:00:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 4,30,
                                  "2006-10-01T04:30:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 5, 0,
                                  "2006-10-01T05:00:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 5,30,
                                  "2006-10-01T05:30:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 6, 0,
                                  "2006-10-01T06:00:00.000-06:00", -6, ZONE_GUATEMALA);
        doTest_getOffsetFromLocal( 2006,10, 1, 6,30,
                                  "2006-10-01T06:30:00.000-06:00", -6, ZONE_GUATEMALA);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    /** America/Rio_Branco gap cutover from 2008-06-23T23:59-05:00 to 2008-06-24T01:00-04:00 */
    private static long CUTOVER_RIO_BRANCO_AUTUMN = 1214283600000L; // 2008-06-24T01:00:00.000-04:00
    private static final DateTimeZone ZONE_RIO_BRANCO = DateTimeZone.forID("America/Rio_Branco");

    //-----------------------------------------------------------------------

    public void test_getOffsetFromLocal_RioBranco_Spring() {
        doTest_getOffsetFromLocal(2008, 6, 23, 23, 0, "2008-06-23T23:00:00.000-05:00", -5, ZONE_RIO_BRANCO);
        doTest_getOffsetFromLocal(2008, 6, 23, 23, 30, "2008-06-23T23:30:00.000-05:00", -5, ZONE_RIO_BRANCO);
        
        doTest_getOffsetFromLocal(2008, 6, 24, 0, 0, "2008-06-24T01:00:00.000-04:00", -5, ZONE_RIO_BRANCO);
        doTest_getOffsetFromLocal(2008, 6, 24, 0, 30, "2008-06-24T01:30:00.000-04:00", -5, ZONE_RIO_BRANCO);
        
        doTest_getOffsetFromLocal(2008, 6, 24, 1, 0, "2008-06-24T01:00:00.000-04:00", -4, ZONE_RIO_BRANCO);
        doTest_getOffsetFromLocal(2008, 6, 24, 1, 30, "2008-06-24T01:30:00.000-04:00", -4, ZONE_RIO_BRANCO);
        doTest_getOffsetFromLocal(2008, 6, 24, 2, 0, "2008-06-24T02:00:00.000-04:00", -4, ZONE_RIO_BRANCO);
        doTest_getOffsetFromLocal(2008, 6, 24, 2, 30, "2008-06-24T02:30:00.000-04:00", -4, ZONE_RIO_BRANCO);
        doTest_getOffsetFromLocal(2008, 6, 24, 5, 0, "2008-06-24T05:00:00.000-04:00", -4, ZONE_RIO_BRANCO);
        doTest_getOffsetFromLocal(2008, 6, 24, 5, 30, "2008-06-24T05:30:00.000-04:00", -4, ZONE_RIO_BRANCO);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

//    public void test_toDateMidnight_SaoPaolo() {
//        // RFE: 1684259
//        DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
//        LocalDate baseDate = new LocalDate(2006, 11, 5);
//        DateMidnight dm = baseDate.toDateMidnight(zone);
//        assertEquals("2006-11-05T00:00:00.000-03:00",dm.toString());
//        DateTime dt = baseDate.toDateTimeAtMidnight(zone);
//        assertEquals("2006-11-05T00:00:00.000-03:00",dt.toString());
//    }

    //-----------------------------------------------------------------------
    private static final DateTimeZone ZONE_PARIS = DateTimeZone.forID("Europe/Paris");

    //-------------------------------------------------------------------------

    // ensure Summer time picked
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    private void doTest_getOffsetFromLocal(int month, int day, int hour, int min, String expected, int expOffset, DateTimeZone zone) {
        doTest_getOffsetFromLocal(2007, month, day, hour, min, 0, 0, expected, expOffset, zone);
    }

    private void doTest_getOffsetFromLocal(int month, int day, int hour, int min, int sec, int milli, String expected, int expOffset, DateTimeZone zone) {
        doTest_getOffsetFromLocal(2007, month, day, hour, min, sec, milli, expected, expOffset, zone);
    }

    private void doTest_getOffsetFromLocal(int year, int month, int day, int hour, int min, String expected, int expOffset, DateTimeZone zone) {
        doTest_getOffsetFromLocal(year, month, day, hour, min, 0, 0, expected, expOffset, zone);
    }

    private void doTest_getOffsetFromLocal(int year, int month, int day, int hour, int min, int sec, int milli, String expected, int expOffset, DateTimeZone zone) {
        DateTime dt = new DateTime(year, month, day, hour, min, sec, milli, DateTimeZone.UTC);
        int offset = zone.getOffsetFromLocal(dt.getMillis());
        assertEquals(expOffset * 3600000L,offset);
        DateTime res = new DateTime(dt.getMillis() - offset, zone);
        assertEquals(res.toString(),expected,res.toString());
    }

    public void test_MockGazaIsCorrect_1_oe() {
        DateTime pre = new DateTime(CUTOVER_GAZA - 1L, MOCK_GAZA);
        assertEquals("2007-03-31T23:59:59.999+02:00",pre.toString());
    }

    public void test_MockGazaIsCorrect_2_oe() {
        DateTime pre = new DateTime(CUTOVER_GAZA - 1L, MOCK_GAZA);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_GAZA, MOCK_GAZA);
        assertEquals("2007-04-01T01:00:00.000+03:00",at.toString());
    }

    public void test_MockGazaIsCorrect_3_oe() {
        DateTime pre = new DateTime(CUTOVER_GAZA - 1L, MOCK_GAZA);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_GAZA, MOCK_GAZA);
        // removed other assertion
        DateTime post = new DateTime(CUTOVER_GAZA + 1L, MOCK_GAZA);
        assertEquals("2007-04-01T01:00:00.001+03:00",post.toString());
    }

    public void test_DateTime_roundFloor_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_GAZA);
        assertEquals("2007-04-01T08:00:00.000+03:00",dt.toString());
    }

    public void test_DateTime_roundFloor_Gaza_2_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundFloorCopy();
        assertEquals("2007-04-01T01:00:00.000+03:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 20, 0, 0, 0, MOCK_GAZA);
        assertEquals("2007-03-31T20:00:00.000+02:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_Gaza_2_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 20, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundCeilingCopy();
        assertEquals("2007-04-01T01:00:00.000+03:00",rounded.toString());
    }

    public void test_DateTime_setHourZero_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_GAZA);
        assertEquals("2007-04-01T08:00:00.000+03:00",dt.toString());
    }

    public void test_DateTime_withHourZero_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_GAZA);
        assertEquals("2007-04-01T08:00:00.000+03:00",dt.toString());
    }

    public void test_DateTime_withDay_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 0, 0, 0, 0, MOCK_GAZA);
        assertEquals("2007-04-02T00:00:00.000+03:00",dt.toString());
    }

    public void test_DateTime_withDay_Gaza_2_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 0, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        DateTime res = dt.withDayOfMonth(1);
        assertEquals("2007-04-01T01:00:00.000+03:00",res.toString());
    }

    public void test_DateTime_minusHour_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_GAZA);
        assertEquals("2007-04-01T08:00:00.000+03:00",dt.toString());
    }

    public void test_DateTime_minusHour_Gaza_2_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime minus7 = dt.minusHours(7);
        assertEquals("2007-04-01T01:00:00.000+03:00",minus7.toString());
    }

    public void test_DateTime_minusHour_Gaza_3_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime minus7 = dt.minusHours(7);
        // removed other assertion
        DateTime minus8 = dt.minusHours(8);
        assertEquals("2007-03-31T23:00:00.000+02:00",minus8.toString());
    }

    public void test_DateTime_minusHour_Gaza_4_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime minus7 = dt.minusHours(7);
        // removed other assertion
        DateTime minus8 = dt.minusHours(8);
        // removed other assertion
        DateTime minus9 = dt.minusHours(9);
        assertEquals("2007-03-31T22:00:00.000+02:00",minus9.toString());
    }

    public void test_DateTime_plusHour_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 16, 0, 0, 0, MOCK_GAZA);
        assertEquals("2007-03-31T16:00:00.000+02:00",dt.toString());
    }

    public void test_DateTime_plusHour_Gaza_2_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 16, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime plus7 = dt.plusHours(7);
        assertEquals("2007-03-31T23:00:00.000+02:00",plus7.toString());
    }

    public void test_DateTime_plusHour_Gaza_3_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 16, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime plus7 = dt.plusHours(7);
        // removed other assertion
        DateTime plus8 = dt.plusHours(8);
        assertEquals("2007-04-01T01:00:00.000+03:00",plus8.toString());
    }

    public void test_DateTime_plusHour_Gaza_4_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 16, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime plus7 = dt.plusHours(7);
        // removed other assertion
        DateTime plus8 = dt.plusHours(8);
        // removed other assertion
        DateTime plus9 = dt.plusHours(9);
        assertEquals("2007-04-01T02:00:00.000+03:00",plus9.toString());
    }

    public void test_DateTime_minusDay_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 0, 0, 0, 0, MOCK_GAZA);
        assertEquals("2007-04-02T00:00:00.000+03:00",dt.toString());
    }

    public void test_DateTime_minusDay_Gaza_2_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 0, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime minus1 = dt.minusDays(1);
        assertEquals("2007-04-01T01:00:00.000+03:00",minus1.toString());
    }

    public void test_DateTime_minusDay_Gaza_3_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 0, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime minus1 = dt.minusDays(1);
        // removed other assertion
        DateTime minus2 = dt.minusDays(2);
        assertEquals("2007-03-31T00:00:00.000+02:00",minus2.toString());
    }

    public void test_DateTime_plusDay_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 0, 0, 0, MOCK_GAZA);
        assertEquals("2007-03-31T00:00:00.000+02:00",dt.toString());
    }

    public void test_DateTime_plusDay_Gaza_2_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime plus1 = dt.plusDays(1);
        assertEquals("2007-04-01T01:00:00.000+03:00",plus1.toString());
    }

    public void test_DateTime_plusDay_Gaza_3_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime plus1 = dt.plusDays(1);
        // removed other assertion
        DateTime plus2 = dt.plusDays(2);
        assertEquals("2007-04-02T00:00:00.000+03:00",plus2.toString());
    }

    public void test_DateTime_plusDayMidGap_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 30, 0, 0, MOCK_GAZA);
        assertEquals("2007-03-31T00:30:00.000+02:00",dt.toString());
    }

    public void test_DateTime_plusDayMidGap_Gaza_2_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 30, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime plus1 = dt.plusDays(1);
        assertEquals("2007-04-01T01:30:00.000+03:00",plus1.toString());
    }

    public void test_DateTime_plusDayMidGap_Gaza_3_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 30, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime plus1 = dt.plusDays(1);
        // removed other assertion
        DateTime plus2 = dt.plusDays(2);
        assertEquals("2007-04-02T00:30:00.000+03:00",plus2.toString());
    }

    public void test_DateTime_addWrapFieldDay_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 4, 30, 0, 0, 0, 0, MOCK_GAZA);
        assertEquals("2007-04-30T00:00:00.000+03:00",dt.toString());
    }

    public void test_DateTime_addWrapFieldDay_Gaza_2_oe() {
        DateTime dt = new DateTime(2007, 4, 30, 0, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime plus1 = dt.dayOfMonth().addWrapFieldToCopy(1);
        assertEquals("2007-04-01T01:00:00.000+03:00",plus1.toString());
    }

    public void test_DateTime_addWrapFieldDay_Gaza_3_oe() {
        DateTime dt = new DateTime(2007, 4, 30, 0, 0, 0, 0, MOCK_GAZA);
        // removed other assertion
        
        DateTime plus1 = dt.dayOfMonth().addWrapFieldToCopy(1);
        // removed other assertion
        DateTime plus2 = dt.dayOfMonth().addWrapFieldToCopy(2);
        assertEquals("2007-04-02T00:00:00.000+03:00",plus2.toString());
    }

    public void test_DateTime_withZoneRetainFields_Gaza_1_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 0, 0, 0, 0, DateTimeZone.UTC);
        assertEquals("2007-04-01T00:00:00.000Z",dt.toString());
    }

    public void test_DateTime_withZoneRetainFields_Gaza_2_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 0, 0, 0, 0, DateTimeZone.UTC);
        // removed other assertion
        
        DateTime res = dt.withZoneRetainFields(MOCK_GAZA);
        assertEquals("2007-04-01T01:00:00.000+03:00",res.toString());
    }

    public void test_MutableDateTime_withZoneRetainFields_Gaza_1_oe() {
        MutableDateTime dt = new MutableDateTime(2007, 4, 1, 0, 0, 0, 0, DateTimeZone.UTC);
        assertEquals("2007-04-01T00:00:00.000Z",dt.toString());
    }

    public void test_MutableDateTime_withZoneRetainFields_Gaza_2_oe() {
        MutableDateTime dt = new MutableDateTime(2007, 4, 1, 0, 0, 0, 0, DateTimeZone.UTC);
        // removed other assertion
        
        dt.setZoneRetainFields(MOCK_GAZA);
        assertEquals("2007-04-01T01:00:00.000+03:00",dt.toString());
    }

    public void test_LocalDate_new_Gaza_1_oe() {
        LocalDate date1 = new LocalDate(CUTOVER_GAZA, MOCK_GAZA);
        assertEquals("2007-04-01",date1.toString());
    }

    public void test_LocalDate_new_Gaza_2_oe() {
        LocalDate date1 = new LocalDate(CUTOVER_GAZA, MOCK_GAZA);
        // removed other assertion
        
        LocalDate date2 = new LocalDate(CUTOVER_GAZA - 1, MOCK_GAZA);
        assertEquals("2007-03-31",date2.toString());
    }

    public void test_LocalDate_toDateMidnight_Gaza_2_oe() {
        LocalDate date = new LocalDate(2007, 4, 1);
        try {
            date.toDateMidnight(MOCK_GAZA);
            // removed other assertion
        } catch (IllegalInstantException ex) {
            assertEquals(true,ex.getMessage().startsWith("Illegal instant due to time zone offset transition"));
    }
    }

    public void test_DateTime_new_Gaza_2_oe() {
        try {
            new DateTime(2007, 4, 1, 0, 0, 0, 0, MOCK_GAZA);
            // removed other assertion
        } catch (IllegalInstantException ex) {
            assertEquals(true,ex.getMessage().indexOf("Illegal instant due to time zone offset transition")>= 0);
    }
    }

    public void test_DateTime_parse_Gaza_2_oe() {
        try {
            new DateTime("2007-04-01T00:00", MOCK_GAZA);
            // removed other assertion
        } catch (IllegalInstantException ex) {
            assertEquals(true,ex.getMessage().indexOf("Illegal instant due to time zone offset transition")>= 0);
    }
    }

    public void test_MockTurkIsCorrect_1_oe() {
        DateTime pre = new DateTime(CUTOVER_TURK - 1L, MOCK_TURK);
        assertEquals("2007-03-31T23:59:59.999-05:00",pre.toString());
    }

    public void test_MockTurkIsCorrect_2_oe() {
        DateTime pre = new DateTime(CUTOVER_TURK - 1L, MOCK_TURK);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_TURK, MOCK_TURK);
        assertEquals("2007-04-01T01:00:00.000-04:00",at.toString());
    }

    public void test_MockTurkIsCorrect_3_oe() {
        DateTime pre = new DateTime(CUTOVER_TURK - 1L, MOCK_TURK);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_TURK, MOCK_TURK);
        // removed other assertion
        DateTime post = new DateTime(CUTOVER_TURK + 1L, MOCK_TURK);
        assertEquals("2007-04-01T01:00:00.001-04:00",post.toString());
    }

    public void test_DateTime_roundFloor_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-04-01T08:00:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundFloor_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundFloorCopy();
        assertEquals("2007-04-01T01:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundFloorNotDST_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 8, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-04-02T08:00:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundFloorNotDST_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 8, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundFloorCopy();
        assertEquals("2007-04-02T00:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 20, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-03-31T20:00:00.000-05:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 20, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundCeilingCopy();
        assertEquals("2007-04-01T01:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_setHourZero_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-04-01T08:00:00.000-04:00",dt.toString());
    }

    public void test_DateTime_withHourZero_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-04-01T08:00:00.000-04:00",dt.toString());
    }

    public void test_DateTime_withDay_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 0, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-04-02T00:00:00.000-04:00",dt.toString());
    }

    public void test_DateTime_withDay_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 0, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        DateTime res = dt.withDayOfMonth(1);
        assertEquals("2007-04-01T01:00:00.000-04:00",res.toString());
    }

    public void test_DateTime_minusHour_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-04-01T08:00:00.000-04:00",dt.toString());
    }

    public void test_DateTime_minusHour_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime minus7 = dt.minusHours(7);
        assertEquals("2007-04-01T01:00:00.000-04:00",minus7.toString());
    }

    public void test_DateTime_minusHour_Turk_3_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime minus7 = dt.minusHours(7);
        // removed other assertion
        DateTime minus8 = dt.minusHours(8);
        assertEquals("2007-03-31T23:00:00.000-05:00",minus8.toString());
    }

    public void test_DateTime_minusHour_Turk_4_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 8, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime minus7 = dt.minusHours(7);
        // removed other assertion
        DateTime minus8 = dt.minusHours(8);
        // removed other assertion
        DateTime minus9 = dt.minusHours(9);
        assertEquals("2007-03-31T22:00:00.000-05:00",minus9.toString());
    }

    public void test_DateTime_plusHour_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 16, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-03-31T16:00:00.000-05:00",dt.toString());
    }

    public void test_DateTime_plusHour_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 16, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime plus7 = dt.plusHours(7);
        assertEquals("2007-03-31T23:00:00.000-05:00",plus7.toString());
    }

    public void test_DateTime_plusHour_Turk_3_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 16, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime plus7 = dt.plusHours(7);
        // removed other assertion
        DateTime plus8 = dt.plusHours(8);
        assertEquals("2007-04-01T01:00:00.000-04:00",plus8.toString());
    }

    public void test_DateTime_plusHour_Turk_4_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 16, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime plus7 = dt.plusHours(7);
        // removed other assertion
        DateTime plus8 = dt.plusHours(8);
        // removed other assertion
        DateTime plus9 = dt.plusHours(9);
        assertEquals("2007-04-01T02:00:00.000-04:00",plus9.toString());
    }

    public void test_DateTime_minusDay_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 0, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-04-02T00:00:00.000-04:00",dt.toString());
    }

    public void test_DateTime_minusDay_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 0, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime minus1 = dt.minusDays(1);
        assertEquals("2007-04-01T01:00:00.000-04:00",minus1.toString());
    }

    public void test_DateTime_minusDay_Turk_3_oe() {
        DateTime dt = new DateTime(2007, 4, 2, 0, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime minus1 = dt.minusDays(1);
        // removed other assertion
        DateTime minus2 = dt.minusDays(2);
        assertEquals("2007-03-31T00:00:00.000-05:00",minus2.toString());
    }

    public void test_DateTime_plusDay_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-03-31T00:00:00.000-05:00",dt.toString());
    }

    public void test_DateTime_plusDay_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime plus1 = dt.plusDays(1);
        assertEquals("2007-04-01T01:00:00.000-04:00",plus1.toString());
    }

    public void test_DateTime_plusDay_Turk_3_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime plus1 = dt.plusDays(1);
        // removed other assertion
        DateTime plus2 = dt.plusDays(2);
        assertEquals("2007-04-02T00:00:00.000-04:00",plus2.toString());
    }

    public void test_DateTime_plusDayMidGap_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 30, 0, 0, MOCK_TURK);
        assertEquals("2007-03-31T00:30:00.000-05:00",dt.toString());
    }

    public void test_DateTime_plusDayMidGap_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 30, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime plus1 = dt.plusDays(1);
        assertEquals("2007-04-01T01:30:00.000-04:00",plus1.toString());
    }

    public void test_DateTime_plusDayMidGap_Turk_3_oe() {
        DateTime dt = new DateTime(2007, 3, 31, 0, 30, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime plus1 = dt.plusDays(1);
        // removed other assertion
        DateTime plus2 = dt.plusDays(2);
        assertEquals("2007-04-02T00:30:00.000-04:00",plus2.toString());
    }

    public void test_DateTime_addWrapFieldDay_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 4, 30, 0, 0, 0, 0, MOCK_TURK);
        assertEquals("2007-04-30T00:00:00.000-04:00",dt.toString());
    }

    public void test_DateTime_addWrapFieldDay_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 4, 30, 0, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime plus1 = dt.dayOfMonth().addWrapFieldToCopy(1);
        assertEquals("2007-04-01T01:00:00.000-04:00",plus1.toString());
    }

    public void test_DateTime_addWrapFieldDay_Turk_3_oe() {
        DateTime dt = new DateTime(2007, 4, 30, 0, 0, 0, 0, MOCK_TURK);
        // removed other assertion
        
        DateTime plus1 = dt.dayOfMonth().addWrapFieldToCopy(1);
        // removed other assertion
        DateTime plus2 = dt.dayOfMonth().addWrapFieldToCopy(2);
        assertEquals("2007-04-02T00:00:00.000-04:00",plus2.toString());
    }

    public void test_DateTime_withZoneRetainFields_Turk_1_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 0, 0, 0, 0, DateTimeZone.UTC);
        assertEquals("2007-04-01T00:00:00.000Z",dt.toString());
    }

    public void test_DateTime_withZoneRetainFields_Turk_2_oe() {
        DateTime dt = new DateTime(2007, 4, 1, 0, 0, 0, 0, DateTimeZone.UTC);
        // removed other assertion
        
        DateTime res = dt.withZoneRetainFields(MOCK_TURK);
        assertEquals("2007-04-01T01:00:00.000-04:00",res.toString());
    }

    public void test_MutableDateTime_setZoneRetainFields_Turk_1_oe() {
        MutableDateTime dt = new MutableDateTime(2007, 4, 1, 0, 0, 0, 0, DateTimeZone.UTC);
        assertEquals("2007-04-01T00:00:00.000Z",dt.toString());
    }

    public void test_MutableDateTime_setZoneRetainFields_Turk_2_oe() {
        MutableDateTime dt = new MutableDateTime(2007, 4, 1, 0, 0, 0, 0, DateTimeZone.UTC);
        // removed other assertion
        
        dt.setZoneRetainFields(MOCK_TURK);
        assertEquals("2007-04-01T01:00:00.000-04:00",dt.toString());
    }

    public void test_LocalDate_new_Turk_1_oe() {
        LocalDate date1 = new LocalDate(CUTOVER_TURK, MOCK_TURK);
        assertEquals("2007-04-01",date1.toString());
    }

    public void test_LocalDate_new_Turk_2_oe() {
        LocalDate date1 = new LocalDate(CUTOVER_TURK, MOCK_TURK);
        // removed other assertion
        
        LocalDate date2 = new LocalDate(CUTOVER_TURK - 1, MOCK_TURK);
        assertEquals("2007-03-31",date2.toString());
    }

    public void test_LocalDate_toDateMidnight_Turk_2_oe() {
        LocalDate date = new LocalDate(2007, 4, 1);
        try {
            date.toDateMidnight(MOCK_TURK);
            // removed other assertion
        } catch (IllegalInstantException ex) {
            assertEquals(true,ex.getMessage().startsWith("Illegal instant due to time zone offset transition"));
    }
    }

    public void test_DateTime_new_Turk_2_oe() {
        try {
            new DateTime(2007, 4, 1, 0, 0, 0, 0, MOCK_TURK);
            // removed other assertion
        } catch (IllegalInstantException ex) {
            assertEquals(true,ex.getMessage().indexOf("Illegal instant due to time zone offset transition")>= 0);
    }
    }

    public void test_DateTime_parse_Turk_2_oe() {
        try {
            new DateTime("2007-04-01T00:00", MOCK_TURK);
            // removed other assertion
        } catch (IllegalInstantException ex) {
            assertEquals(true,ex.getMessage().indexOf("Illegal instant due to time zone offset transition")>= 0);
    }
    }

    public void test_NewYorkIsCorrect_Spring_1_oe() {
        DateTime pre = new DateTime(CUTOVER_NEW_YORK_SPRING - 1L, ZONE_NEW_YORK);
        assertEquals("2007-03-11T01:59:59.999-05:00",pre.toString());
    }

    public void test_NewYorkIsCorrect_Spring_2_oe() {
        DateTime pre = new DateTime(CUTOVER_NEW_YORK_SPRING - 1L, ZONE_NEW_YORK);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_NEW_YORK_SPRING, ZONE_NEW_YORK);
        assertEquals("2007-03-11T03:00:00.000-04:00",at.toString());
    }

    public void test_NewYorkIsCorrect_Spring_3_oe() {
        DateTime pre = new DateTime(CUTOVER_NEW_YORK_SPRING - 1L, ZONE_NEW_YORK);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_NEW_YORK_SPRING, ZONE_NEW_YORK);
        // removed other assertion
        DateTime post = new DateTime(CUTOVER_NEW_YORK_SPRING + 1L, ZONE_NEW_YORK);
        assertEquals("2007-03-11T03:00:00.001-04:00",post.toString());
    }

    public void test_DateTime_setHourAcross_NewYork_Spring_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 0, 0, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T00:00:00.000-05:00",dt.toString());
    }

    public void test_DateTime_setHourAcross_NewYork_Spring_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 0, 0, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime res = dt.hourOfDay().setCopy(4);
        assertEquals("2007-03-11T04:00:00.000-04:00",res.toString());
    }

    public void test_DateTime_setHourForward_NewYork_Spring_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 0, 0, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T00:00:00.000-05:00",dt.toString());
    }

    public void test_DateTime_setHourBack_NewYork_Spring_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 8, 0, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T08:00:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundFloor_day_NewYork_Spring_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T01:30:00.000-05:00",dt.toString());
    }

    public void test_DateTime_roundFloor_day_NewYork_Spring_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundFloorCopy();
        assertEquals("2007-03-11T00:00:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_day_NewYork_Spring_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T03:30:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundFloor_day_NewYork_Spring_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundFloorCopy();
        assertEquals("2007-03-11T00:00:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_hour_NewYork_Spring_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T01:30:00.000-05:00",dt.toString());
    }

    public void test_DateTime_roundFloor_hour_NewYork_Spring_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.hourOfDay().roundFloorCopy();
        assertEquals("2007-03-11T01:00:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_hour_NewYork_Spring_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T03:30:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundFloor_hour_NewYork_Spring_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.hourOfDay().roundFloorCopy();
        assertEquals("2007-03-11T03:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_minute_NewYork_Spring_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 40, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T01:30:40.000-05:00",dt.toString());
    }

    public void test_DateTime_roundFloor_minute_NewYork_Spring_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 40, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.minuteOfHour().roundFloorCopy();
        assertEquals("2007-03-11T01:30:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_minute_NewYork_Spring_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 40, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T03:30:40.000-04:00",dt.toString());
    }

    public void test_DateTime_roundFloor_minute_NewYork_Spring_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 40, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.minuteOfHour().roundFloorCopy();
        assertEquals("2007-03-11T03:30:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_day_NewYork_Spring_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T01:30:00.000-05:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_day_NewYork_Spring_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundCeilingCopy();
        assertEquals("2007-03-12T00:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_day_NewYork_Spring_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T03:30:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_day_NewYork_Spring_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundCeilingCopy();
        assertEquals("2007-03-12T00:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_hour_NewYork_Spring_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T01:30:00.000-05:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_hour_NewYork_Spring_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.hourOfDay().roundCeilingCopy();
        assertEquals("2007-03-11T03:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_hour_NewYork_Spring_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T03:30:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_hour_NewYork_Spring_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.hourOfDay().roundCeilingCopy();
        assertEquals("2007-03-11T04:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_minute_NewYork_Spring_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 40, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T01:30:40.000-05:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_minute_NewYork_Spring_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 1, 30, 40, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.minuteOfHour().roundCeilingCopy();
        assertEquals("2007-03-11T01:31:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_minute_NewYork_Spring_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 40, 0, ZONE_NEW_YORK);
        assertEquals("2007-03-11T03:30:40.000-04:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_minute_NewYork_Spring_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 3, 11, 3, 30, 40, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.minuteOfHour().roundCeilingCopy();
        assertEquals("2007-03-11T03:31:00.000-04:00",rounded.toString());
    }

    public void test_NewYorkIsCorrect_Autumn_1_oe() {
        DateTime pre = new DateTime(CUTOVER_NEW_YORK_AUTUMN - 1L, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:59:59.999-04:00",pre.toString());
    }

    public void test_NewYorkIsCorrect_Autumn_2_oe() {
        DateTime pre = new DateTime(CUTOVER_NEW_YORK_AUTUMN - 1L, ZONE_NEW_YORK);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_NEW_YORK_AUTUMN, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:00:00.000-05:00",at.toString());
    }

    public void test_NewYorkIsCorrect_Autumn_3_oe() {
        DateTime pre = new DateTime(CUTOVER_NEW_YORK_AUTUMN - 1L, ZONE_NEW_YORK);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_NEW_YORK_AUTUMN, ZONE_NEW_YORK);
        // removed other assertion
        DateTime post = new DateTime(CUTOVER_NEW_YORK_AUTUMN + 1L, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:00:00.001-05:00",post.toString());
    }

    public void test_DateTime_constructor_NewYork_Autumn_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:00.000-04:00",dt.toString());
    }

    public void test_DateTime_plusHour_NewYork_Autumn_1_oe() {
        DateTime dt = new DateTime(2007, 11, 3, 18, 0, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-11-03T18:00:00.000-04:00",dt.toString());
    }

    public void test_DateTime_plusHour_NewYork_Autumn_2_oe() {
        DateTime dt = new DateTime(2007, 11, 3, 18, 0, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        
        DateTime plus6 = dt.plusHours(6);
        assertEquals("2007-11-04T00:00:00.000-04:00",plus6.toString());
    }

    public void test_DateTime_plusHour_NewYork_Autumn_3_oe() {
        DateTime dt = new DateTime(2007, 11, 3, 18, 0, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        
        DateTime plus6 = dt.plusHours(6);
        // removed other assertion
        DateTime plus7 = dt.plusHours(7);
        assertEquals("2007-11-04T01:00:00.000-04:00",plus7.toString());
    }

    public void test_DateTime_plusHour_NewYork_Autumn_4_oe() {
        DateTime dt = new DateTime(2007, 11, 3, 18, 0, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        
        DateTime plus6 = dt.plusHours(6);
        // removed other assertion
        DateTime plus7 = dt.plusHours(7);
        // removed other assertion
        DateTime plus8 = dt.plusHours(8);
        assertEquals("2007-11-04T01:00:00.000-05:00",plus8.toString());
    }

    public void test_DateTime_plusHour_NewYork_Autumn_5_oe() {
        DateTime dt = new DateTime(2007, 11, 3, 18, 0, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        
        DateTime plus6 = dt.plusHours(6);
        // removed other assertion
        DateTime plus7 = dt.plusHours(7);
        // removed other assertion
        DateTime plus8 = dt.plusHours(8);
        // removed other assertion
        DateTime plus9 = dt.plusHours(9);
        assertEquals("2007-11-04T02:00:00.000-05:00",plus9.toString());
    }

    public void test_DateTime_minusHour_NewYork_Autumn_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 8, 0, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-11-04T08:00:00.000-05:00",dt.toString());
    }

    public void test_DateTime_minusHour_NewYork_Autumn_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 8, 0, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        
        DateTime minus6 = dt.minusHours(6);
        assertEquals("2007-11-04T02:00:00.000-05:00",minus6.toString());
    }

    public void test_DateTime_minusHour_NewYork_Autumn_3_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 8, 0, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        
        DateTime minus6 = dt.minusHours(6);
        // removed other assertion
        DateTime minus7 = dt.minusHours(7);
        assertEquals("2007-11-04T01:00:00.000-05:00",minus7.toString());
    }

    public void test_DateTime_minusHour_NewYork_Autumn_4_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 8, 0, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        
        DateTime minus6 = dt.minusHours(6);
        // removed other assertion
        DateTime minus7 = dt.minusHours(7);
        // removed other assertion
        DateTime minus8 = dt.minusHours(8);
        assertEquals("2007-11-04T01:00:00.000-04:00",minus8.toString());
    }

    public void test_DateTime_minusHour_NewYork_Autumn_5_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 8, 0, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        
        DateTime minus6 = dt.minusHours(6);
        // removed other assertion
        DateTime minus7 = dt.minusHours(7);
        // removed other assertion
        DateTime minus8 = dt.minusHours(8);
        // removed other assertion
        DateTime minus9 = dt.minusHours(9);
        assertEquals("2007-11-04T00:00:00.000-04:00",minus9.toString());
    }

    public void test_DateTime_roundFloor_day_NewYork_Autumn_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundFloor_day_NewYork_Autumn_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundFloorCopy();
        assertEquals("2007-11-04T00:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_day_NewYork_Autumn_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK).plusHours(1);
        assertEquals("2007-11-04T01:30:00.000-05:00",dt.toString());
    }

    public void test_DateTime_roundFloor_day_NewYork_Autumn_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK).plusHours(1);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundFloorCopy();
        assertEquals("2007-11-04T00:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_hourOfDay_NewYork_Autumn_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundFloor_hourOfDay_NewYork_Autumn_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.hourOfDay().roundFloorCopy();
        assertEquals("2007-11-04T01:00:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_hourOfDay_NewYork_Autumn_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK).plusHours(1);
        assertEquals("2007-11-04T01:30:00.000-05:00",dt.toString());
    }

    public void test_DateTime_roundFloor_hourOfDay_NewYork_Autumn_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK).plusHours(1);
        // removed other assertion
        DateTime rounded = dt.hourOfDay().roundFloorCopy();
        assertEquals("2007-11-04T01:00:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_minuteOfHour_NewYork_Autumn_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 0, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:40.000-04:00",dt.toString());
    }

    public void test_DateTime_roundFloor_minuteOfHour_NewYork_Autumn_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.minuteOfHour().roundFloorCopy();
        assertEquals("2007-11-04T01:30:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_minuteOfHour_NewYork_Autumn_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 0, ZONE_NEW_YORK).plusHours(1);
        assertEquals("2007-11-04T01:30:40.000-05:00",dt.toString());
    }

    public void test_DateTime_roundFloor_minuteOfHour_NewYork_Autumn_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 0, ZONE_NEW_YORK).plusHours(1);
        // removed other assertion
        DateTime rounded = dt.minuteOfHour().roundFloorCopy();
        assertEquals("2007-11-04T01:30:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_secondOfMinute_NewYork_Autumn_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 500, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:40.500-04:00",dt.toString());
    }

    public void test_DateTime_roundFloor_secondOfMinute_NewYork_Autumn_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 500, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.secondOfMinute().roundFloorCopy();
        assertEquals("2007-11-04T01:30:40.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundFloor_secondOfMinute_NewYork_Autumn_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 500, ZONE_NEW_YORK).plusHours(1);
        assertEquals("2007-11-04T01:30:40.500-05:00",dt.toString());
    }

    public void test_DateTime_roundFloor_secondOfMinute_NewYork_Autumn_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 500, ZONE_NEW_YORK).plusHours(1);
        // removed other assertion
        DateTime rounded = dt.secondOfMinute().roundFloorCopy();
        assertEquals("2007-11-04T01:30:40.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_day_NewYork_Autumn_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_day_NewYork_Autumn_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundCeilingCopy();
        assertEquals("2007-11-05T00:00:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_day_NewYork_Autumn_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK).plusHours(1);
        assertEquals("2007-11-04T01:30:00.000-05:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_day_NewYork_Autumn_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK).plusHours(1);
        // removed other assertion
        DateTime rounded = dt.dayOfMonth().roundCeilingCopy();
        assertEquals("2007-11-05T00:00:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_hourOfDay_NewYork_Autumn_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:00.000-04:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_hourOfDay_NewYork_Autumn_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.hourOfDay().roundCeilingCopy();
        assertEquals("2007-11-04T01:00:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_hourOfDay_NewYork_Autumn_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK).plusHours(1);
        assertEquals("2007-11-04T01:30:00.000-05:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_hourOfDay_NewYork_Autumn_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 0, 0, ZONE_NEW_YORK).plusHours(1);
        // removed other assertion
        DateTime rounded = dt.hourOfDay().roundCeilingCopy();
        assertEquals("2007-11-04T02:00:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_minuteOfHour_NewYork_Autumn_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 0, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:40.000-04:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_minuteOfHour_NewYork_Autumn_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 0, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.minuteOfHour().roundCeilingCopy();
        assertEquals("2007-11-04T01:31:00.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_minuteOfHour_NewYork_Autumn_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 0, ZONE_NEW_YORK).plusHours(1);
        assertEquals("2007-11-04T01:30:40.000-05:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_minuteOfHour_NewYork_Autumn_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 0, ZONE_NEW_YORK).plusHours(1);
        // removed other assertion
        DateTime rounded = dt.minuteOfHour().roundCeilingCopy();
        assertEquals("2007-11-04T01:31:00.000-05:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_secondOfMinute_NewYork_Autumn_preCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 500, ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:40.500-04:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_secondOfMinute_NewYork_Autumn_preCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 500, ZONE_NEW_YORK);
        // removed other assertion
        DateTime rounded = dt.secondOfMinute().roundCeilingCopy();
        assertEquals("2007-11-04T01:30:41.000-04:00",rounded.toString());
    }

    public void test_DateTime_roundCeiling_secondOfMinute_NewYork_Autumn_postCutover_1_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 500, ZONE_NEW_YORK).plusHours(1);
        assertEquals("2007-11-04T01:30:40.500-05:00",dt.toString());
    }

    public void test_DateTime_roundCeiling_secondOfMinute_NewYork_Autumn_postCutover_2_oe() {
        DateTime dt = new DateTime(2007, 11, 4, 1, 30, 40, 500, ZONE_NEW_YORK).plusHours(1);
        // removed other assertion
        DateTime rounded = dt.secondOfMinute().roundCeilingCopy();
        assertEquals("2007-11-04T01:30:41.000-05:00",rounded.toString());
    }

    public void test_MoscowIsCorrect_Spring_1_oe() {
//      DateTime x = new DateTime(2007, 7, 1, 0, 0, 0, 0, ZONE_MOSCOW);
//      System.out.println(ZONE_MOSCOW.nextTransition(x.getMillis()));
//      DateTime y = new DateTime(ZONE_MOSCOW.nextTransition(x.getMillis()), ZONE_MOSCOW);
//      System.out.println(y);
        DateTime pre = new DateTime(CUTOVER_MOSCOW_SPRING - 1L, ZONE_MOSCOW);
        assertEquals("2007-03-25T01:59:59.999+03:00",pre.toString());
    }

    public void test_MoscowIsCorrect_Spring_2_oe() {
//      DateTime x = new DateTime(2007, 7, 1, 0, 0, 0, 0, ZONE_MOSCOW);
//      System.out.println(ZONE_MOSCOW.nextTransition(x.getMillis()));
//      DateTime y = new DateTime(ZONE_MOSCOW.nextTransition(x.getMillis()), ZONE_MOSCOW);
//      System.out.println(y);
        DateTime pre = new DateTime(CUTOVER_MOSCOW_SPRING - 1L, ZONE_MOSCOW);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_MOSCOW_SPRING, ZONE_MOSCOW);
        assertEquals("2007-03-25T03:00:00.000+04:00",at.toString());
    }

    public void test_MoscowIsCorrect_Spring_3_oe() {
//      DateTime x = new DateTime(2007, 7, 1, 0, 0, 0, 0, ZONE_MOSCOW);
//      System.out.println(ZONE_MOSCOW.nextTransition(x.getMillis()));
//      DateTime y = new DateTime(ZONE_MOSCOW.nextTransition(x.getMillis()), ZONE_MOSCOW);
//      System.out.println(y);
        DateTime pre = new DateTime(CUTOVER_MOSCOW_SPRING - 1L, ZONE_MOSCOW);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_MOSCOW_SPRING, ZONE_MOSCOW);
        // removed other assertion
        DateTime post = new DateTime(CUTOVER_MOSCOW_SPRING + 1L, ZONE_MOSCOW);
        assertEquals("2007-03-25T03:00:00.001+04:00",post.toString());
    }

    public void test_DateTime_setHourAcross_Moscow_Spring_1_oe() {
        DateTime dt = new DateTime(2007, 3, 25, 0, 0, 0, 0, ZONE_MOSCOW);
        assertEquals("2007-03-25T00:00:00.000+03:00",dt.toString());
    }

    public void test_DateTime_setHourAcross_Moscow_Spring_2_oe() {
        DateTime dt = new DateTime(2007, 3, 25, 0, 0, 0, 0, ZONE_MOSCOW);
        // removed other assertion
        DateTime res = dt.hourOfDay().setCopy(4);
        assertEquals("2007-03-25T04:00:00.000+04:00",res.toString());
    }

    public void test_DateTime_setHourForward_Moscow_Spring_1_oe() {
        DateTime dt = new DateTime(2007, 3, 25, 0, 0, 0, 0, ZONE_MOSCOW);
        assertEquals("2007-03-25T00:00:00.000+03:00",dt.toString());
    }

    public void test_DateTime_setHourBack_Moscow_Spring_1_oe() {
        DateTime dt = new DateTime(2007, 3, 25, 8, 0, 0, 0, ZONE_MOSCOW);
        assertEquals("2007-03-25T08:00:00.000+04:00",dt.toString());
    }

    public void test_MoscowIsCorrect_Autumn_1_oe() {
        DateTime pre = new DateTime(CUTOVER_MOSCOW_AUTUMN - 1L, ZONE_MOSCOW);
        assertEquals("2007-10-28T02:59:59.999+04:00",pre.toString());
    }

    public void test_MoscowIsCorrect_Autumn_2_oe() {
        DateTime pre = new DateTime(CUTOVER_MOSCOW_AUTUMN - 1L, ZONE_MOSCOW);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_MOSCOW_AUTUMN, ZONE_MOSCOW);
        assertEquals("2007-10-28T02:00:00.000+03:00",at.toString());
    }

    public void test_MoscowIsCorrect_Autumn_3_oe() {
        DateTime pre = new DateTime(CUTOVER_MOSCOW_AUTUMN - 1L, ZONE_MOSCOW);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_MOSCOW_AUTUMN, ZONE_MOSCOW);
        // removed other assertion
        DateTime post = new DateTime(CUTOVER_MOSCOW_AUTUMN + 1L, ZONE_MOSCOW);
        assertEquals("2007-10-28T02:00:00.001+03:00",post.toString());
    }

    public void test_DateTime_constructor_Moscow_Autumn_1_oe() {
        DateTime dt = new DateTime(2007, 10, 28, 2, 30, ZONE_MOSCOW);
        assertEquals("2007-10-28T02:30:00.000+04:00",dt.toString());
    }

    public void test_DateTime_plusHour_Moscow_Autumn_1_oe() {
        DateTime dt = new DateTime(2007, 10, 27, 19, 0, 0, 0, ZONE_MOSCOW);
        assertEquals("2007-10-27T19:00:00.000+04:00",dt.toString());
    }

    public void test_DateTime_plusHour_Moscow_Autumn_2_oe() {
        DateTime dt = new DateTime(2007, 10, 27, 19, 0, 0, 0, ZONE_MOSCOW);
        // removed other assertion
        
        DateTime plus6 = dt.plusHours(6);
        assertEquals("2007-10-28T01:00:00.000+04:00",plus6.toString());
    }

    public void test_DateTime_plusHour_Moscow_Autumn_3_oe() {
        DateTime dt = new DateTime(2007, 10, 27, 19, 0, 0, 0, ZONE_MOSCOW);
        // removed other assertion
        
        DateTime plus6 = dt.plusHours(6);
        // removed other assertion
        DateTime plus7 = dt.plusHours(7);
        assertEquals("2007-10-28T02:00:00.000+04:00",plus7.toString());
    }

    public void test_DateTime_plusHour_Moscow_Autumn_4_oe() {
        DateTime dt = new DateTime(2007, 10, 27, 19, 0, 0, 0, ZONE_MOSCOW);
        // removed other assertion
        
        DateTime plus6 = dt.plusHours(6);
        // removed other assertion
        DateTime plus7 = dt.plusHours(7);
        // removed other assertion
        DateTime plus8 = dt.plusHours(8);
        assertEquals("2007-10-28T02:00:00.000+03:00",plus8.toString());
    }

    public void test_DateTime_plusHour_Moscow_Autumn_5_oe() {
        DateTime dt = new DateTime(2007, 10, 27, 19, 0, 0, 0, ZONE_MOSCOW);
        // removed other assertion
        
        DateTime plus6 = dt.plusHours(6);
        // removed other assertion
        DateTime plus7 = dt.plusHours(7);
        // removed other assertion
        DateTime plus8 = dt.plusHours(8);
        // removed other assertion
        DateTime plus9 = dt.plusHours(9);
        assertEquals("2007-10-28T03:00:00.000+03:00",plus9.toString());
    }

    public void test_DateTime_minusHour_Moscow_Autumn_1_oe() {
        DateTime dt = new DateTime(2007, 10, 28, 9, 0, 0, 0, ZONE_MOSCOW);
        assertEquals("2007-10-28T09:00:00.000+03:00",dt.toString());
    }

    public void test_DateTime_minusHour_Moscow_Autumn_2_oe() {
        DateTime dt = new DateTime(2007, 10, 28, 9, 0, 0, 0, ZONE_MOSCOW);
        // removed other assertion
        
        DateTime minus6 = dt.minusHours(6);
        assertEquals("2007-10-28T03:00:00.000+03:00",minus6.toString());
    }

    public void test_DateTime_minusHour_Moscow_Autumn_3_oe() {
        DateTime dt = new DateTime(2007, 10, 28, 9, 0, 0, 0, ZONE_MOSCOW);
        // removed other assertion
        
        DateTime minus6 = dt.minusHours(6);
        // removed other assertion
        DateTime minus7 = dt.minusHours(7);
        assertEquals("2007-10-28T02:00:00.000+03:00",minus7.toString());
    }

    public void test_DateTime_minusHour_Moscow_Autumn_4_oe() {
        DateTime dt = new DateTime(2007, 10, 28, 9, 0, 0, 0, ZONE_MOSCOW);
        // removed other assertion
        
        DateTime minus6 = dt.minusHours(6);
        // removed other assertion
        DateTime minus7 = dt.minusHours(7);
        // removed other assertion
        DateTime minus8 = dt.minusHours(8);
        assertEquals("2007-10-28T02:00:00.000+04:00",minus8.toString());
    }

    public void test_DateTime_minusHour_Moscow_Autumn_5_oe() {
        DateTime dt = new DateTime(2007, 10, 28, 9, 0, 0, 0, ZONE_MOSCOW);
        // removed other assertion
        
        DateTime minus6 = dt.minusHours(6);
        // removed other assertion
        DateTime minus7 = dt.minusHours(7);
        // removed other assertion
        DateTime minus8 = dt.minusHours(8);
        // removed other assertion
        DateTime minus9 = dt.minusHours(9);
        assertEquals("2007-10-28T01:00:00.000+04:00",minus9.toString());
    }

    public void test_GuatemataIsCorrect_Autumn_1_oe() {
        DateTime pre = new DateTime(CUTOVER_GUATEMALA_AUTUMN - 1L, ZONE_GUATEMALA);
        assertEquals("2006-09-30T23:59:59.999-05:00",pre.toString());
    }

    public void test_GuatemataIsCorrect_Autumn_2_oe() {
        DateTime pre = new DateTime(CUTOVER_GUATEMALA_AUTUMN - 1L, ZONE_GUATEMALA);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_GUATEMALA_AUTUMN, ZONE_GUATEMALA);
        assertEquals("2006-09-30T23:00:00.000-06:00",at.toString());
    }

    public void test_GuatemataIsCorrect_Autumn_3_oe() {
        DateTime pre = new DateTime(CUTOVER_GUATEMALA_AUTUMN - 1L, ZONE_GUATEMALA);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_GUATEMALA_AUTUMN, ZONE_GUATEMALA);
        // removed other assertion
        DateTime post = new DateTime(CUTOVER_GUATEMALA_AUTUMN + 1L, ZONE_GUATEMALA);
        assertEquals("2006-09-30T23:00:00.001-06:00",post.toString());
    }

    public void test_DateTime_plusHour_Guatemata_Autumn_1_oe() {
        DateTime dt = new DateTime(2006, 9, 30, 20, 0, 0, 0, ZONE_GUATEMALA);
        assertEquals("2006-09-30T20:00:00.000-05:00",dt.toString());
    }

    public void test_DateTime_plusHour_Guatemata_Autumn_2_oe() {
        DateTime dt = new DateTime(2006, 9, 30, 20, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime plus1 = dt.plusHours(1);
        assertEquals("2006-09-30T21:00:00.000-05:00",plus1.toString());
    }

    public void test_DateTime_plusHour_Guatemata_Autumn_3_oe() {
        DateTime dt = new DateTime(2006, 9, 30, 20, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime plus1 = dt.plusHours(1);
        // removed other assertion
        DateTime plus2 = dt.plusHours(2);
        assertEquals("2006-09-30T22:00:00.000-05:00",plus2.toString());
    }

    public void test_DateTime_plusHour_Guatemata_Autumn_4_oe() {
        DateTime dt = new DateTime(2006, 9, 30, 20, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime plus1 = dt.plusHours(1);
        // removed other assertion
        DateTime plus2 = dt.plusHours(2);
        // removed other assertion
        DateTime plus3 = dt.plusHours(3);
        assertEquals("2006-09-30T23:00:00.000-05:00",plus3.toString());
    }

    public void test_DateTime_plusHour_Guatemata_Autumn_5_oe() {
        DateTime dt = new DateTime(2006, 9, 30, 20, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime plus1 = dt.plusHours(1);
        // removed other assertion
        DateTime plus2 = dt.plusHours(2);
        // removed other assertion
        DateTime plus3 = dt.plusHours(3);
        // removed other assertion
        DateTime plus4 = dt.plusHours(4);
        assertEquals("2006-09-30T23:00:00.000-06:00",plus4.toString());
    }

    public void test_DateTime_plusHour_Guatemata_Autumn_6_oe() {
        DateTime dt = new DateTime(2006, 9, 30, 20, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime plus1 = dt.plusHours(1);
        // removed other assertion
        DateTime plus2 = dt.plusHours(2);
        // removed other assertion
        DateTime plus3 = dt.plusHours(3);
        // removed other assertion
        DateTime plus4 = dt.plusHours(4);
        // removed other assertion
        DateTime plus5 = dt.plusHours(5);
        assertEquals("2006-10-01T00:00:00.000-06:00",plus5.toString());
    }

    public void test_DateTime_plusHour_Guatemata_Autumn_7_oe() {
        DateTime dt = new DateTime(2006, 9, 30, 20, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime plus1 = dt.plusHours(1);
        // removed other assertion
        DateTime plus2 = dt.plusHours(2);
        // removed other assertion
        DateTime plus3 = dt.plusHours(3);
        // removed other assertion
        DateTime plus4 = dt.plusHours(4);
        // removed other assertion
        DateTime plus5 = dt.plusHours(5);
        // removed other assertion
        DateTime plus6 = dt.plusHours(6);
        assertEquals("2006-10-01T01:00:00.000-06:00",plus6.toString());
    }

    public void test_DateTime_plusHour_Guatemata_Autumn_8_oe() {
        DateTime dt = new DateTime(2006, 9, 30, 20, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime plus1 = dt.plusHours(1);
        // removed other assertion
        DateTime plus2 = dt.plusHours(2);
        // removed other assertion
        DateTime plus3 = dt.plusHours(3);
        // removed other assertion
        DateTime plus4 = dt.plusHours(4);
        // removed other assertion
        DateTime plus5 = dt.plusHours(5);
        // removed other assertion
        DateTime plus6 = dt.plusHours(6);
        // removed other assertion
        DateTime plus7 = dt.plusHours(7);
        assertEquals("2006-10-01T02:00:00.000-06:00",plus7.toString());
    }

    public void test_DateTime_minusHour_Guatemata_Autumn_1_oe() {
        DateTime dt = new DateTime(2006, 10, 1, 2, 0, 0, 0, ZONE_GUATEMALA);
        assertEquals("2006-10-01T02:00:00.000-06:00",dt.toString());
    }

    public void test_DateTime_minusHour_Guatemata_Autumn_2_oe() {
        DateTime dt = new DateTime(2006, 10, 1, 2, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime minus1 = dt.minusHours(1);
        assertEquals("2006-10-01T01:00:00.000-06:00",minus1.toString());
    }

    public void test_DateTime_minusHour_Guatemata_Autumn_3_oe() {
        DateTime dt = new DateTime(2006, 10, 1, 2, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime minus1 = dt.minusHours(1);
        // removed other assertion
        DateTime minus2 = dt.minusHours(2);
        assertEquals("2006-10-01T00:00:00.000-06:00",minus2.toString());
    }

    public void test_DateTime_minusHour_Guatemata_Autumn_4_oe() {
        DateTime dt = new DateTime(2006, 10, 1, 2, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime minus1 = dt.minusHours(1);
        // removed other assertion
        DateTime minus2 = dt.minusHours(2);
        // removed other assertion
        DateTime minus3 = dt.minusHours(3);
        assertEquals("2006-09-30T23:00:00.000-06:00",minus3.toString());
    }

    public void test_DateTime_minusHour_Guatemata_Autumn_5_oe() {
        DateTime dt = new DateTime(2006, 10, 1, 2, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime minus1 = dt.minusHours(1);
        // removed other assertion
        DateTime minus2 = dt.minusHours(2);
        // removed other assertion
        DateTime minus3 = dt.minusHours(3);
        // removed other assertion
        DateTime minus4 = dt.minusHours(4);
        assertEquals("2006-09-30T23:00:00.000-05:00",minus4.toString());
    }

    public void test_DateTime_minusHour_Guatemata_Autumn_6_oe() {
        DateTime dt = new DateTime(2006, 10, 1, 2, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime minus1 = dt.minusHours(1);
        // removed other assertion
        DateTime minus2 = dt.minusHours(2);
        // removed other assertion
        DateTime minus3 = dt.minusHours(3);
        // removed other assertion
        DateTime minus4 = dt.minusHours(4);
        // removed other assertion
        DateTime minus5 = dt.minusHours(5);
        assertEquals("2006-09-30T22:00:00.000-05:00",minus5.toString());
    }

    public void test_DateTime_minusHour_Guatemata_Autumn_7_oe() {
        DateTime dt = new DateTime(2006, 10, 1, 2, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime minus1 = dt.minusHours(1);
        // removed other assertion
        DateTime minus2 = dt.minusHours(2);
        // removed other assertion
        DateTime minus3 = dt.minusHours(3);
        // removed other assertion
        DateTime minus4 = dt.minusHours(4);
        // removed other assertion
        DateTime minus5 = dt.minusHours(5);
        // removed other assertion
        DateTime minus6 = dt.minusHours(6);
        assertEquals("2006-09-30T21:00:00.000-05:00",minus6.toString());
    }

    public void test_DateTime_minusHour_Guatemata_Autumn_8_oe() {
        DateTime dt = new DateTime(2006, 10, 1, 2, 0, 0, 0, ZONE_GUATEMALA);
        // removed other assertion
        
        DateTime minus1 = dt.minusHours(1);
        // removed other assertion
        DateTime minus2 = dt.minusHours(2);
        // removed other assertion
        DateTime minus3 = dt.minusHours(3);
        // removed other assertion
        DateTime minus4 = dt.minusHours(4);
        // removed other assertion
        DateTime minus5 = dt.minusHours(5);
        // removed other assertion
        DateTime minus6 = dt.minusHours(6);
        // removed other assertion
        DateTime minus7 = dt.minusHours(7);
        assertEquals("2006-09-30T20:00:00.000-05:00",minus7.toString());
    }

    public void test_RioBrancoIsCorrect_Spring_1_oe() {
        DateTime pre = new DateTime(CUTOVER_RIO_BRANCO_AUTUMN - 1L, ZONE_RIO_BRANCO);
        assertEquals("2008-06-23T23:59:59.999-05:00",pre.toString());
    }

    public void test_RioBrancoIsCorrect_Spring_2_oe() {
        DateTime pre = new DateTime(CUTOVER_RIO_BRANCO_AUTUMN - 1L, ZONE_RIO_BRANCO);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_RIO_BRANCO_AUTUMN, ZONE_RIO_BRANCO);
        assertEquals("2008-06-24T01:00:00.000-04:00",at.toString());
    }

    public void test_RioBrancoIsCorrect_Spring_3_oe() {
        DateTime pre = new DateTime(CUTOVER_RIO_BRANCO_AUTUMN - 1L, ZONE_RIO_BRANCO);
        // removed other assertion
        DateTime at = new DateTime(CUTOVER_RIO_BRANCO_AUTUMN, ZONE_RIO_BRANCO);
        // removed other assertion
        DateTime post = new DateTime(CUTOVER_RIO_BRANCO_AUTUMN + 1L, ZONE_RIO_BRANCO);
        assertEquals("2008-06-24T01:00:00.001-04:00",post.toString());
    }

    public void test_DateTime_JustAfterLastEverOverlap_1_oe() {
        // based on America/Argentina/Catamarca in file 2009s
        DateTimeZone zone = new DateTimeZoneBuilder()
            .setStandardOffset(-3 * DateTimeConstants.MILLIS_PER_HOUR)
            .addRecurringSavings("SUMMER", 1 * DateTimeConstants.MILLIS_PER_HOUR, 2000, 2008,
                                    'w', 4, 10, 0, true, 23 * DateTimeConstants.MILLIS_PER_HOUR)
            .addRecurringSavings("WINTER", 0, 2000, 2008,
                                    'w', 8, 10, 0, true, 0 * DateTimeConstants.MILLIS_PER_HOUR)
            .toDateTimeZone("Zone", false);
        
        LocalDate date = new LocalDate(2008, 8, 10);
        assertEquals("2008-08-10",date.toString());
    }

    public void test_DateTime_JustAfterLastEverOverlap_2_oe() {
        // based on America/Argentina/Catamarca in file 2009s
        DateTimeZone zone = new DateTimeZoneBuilder()
            .setStandardOffset(-3 * DateTimeConstants.MILLIS_PER_HOUR)
            .addRecurringSavings("SUMMER", 1 * DateTimeConstants.MILLIS_PER_HOUR, 2000, 2008,
                                    'w', 4, 10, 0, true, 23 * DateTimeConstants.MILLIS_PER_HOUR)
            .addRecurringSavings("WINTER", 0, 2000, 2008,
                                    'w', 8, 10, 0, true, 0 * DateTimeConstants.MILLIS_PER_HOUR)
            .toDateTimeZone("Zone", false);
        
        LocalDate date = new LocalDate(2008, 8, 10);
        // removed other assertion
        
        DateTime dt = date.toDateTimeAtStartOfDay(zone);
        assertEquals("2008-08-10T00:00:00.000-03:00",dt.toString());
    }

    public void testWithMinuteOfHourInDstChange_mockZone_1_oe() {
        DateTime cutover = new DateTime(2010, 10, 31, 1, 15, DateTimeZone.forOffsetHoursMinutes(0, 30));
        assertEquals("2010-10-31T01:15:00.000+00:30",cutover.toString());
    }

    public void testWithMinuteOfHourInDstChange_mockZone_2_oe() {
        DateTime cutover = new DateTime(2010, 10, 31, 1, 15, DateTimeZone.forOffsetHoursMinutes(0, 30));
        // removed other assertion
        DateTimeZone halfHourZone = new MockZone(cutover.getMillis(), 3600000, -1800);
        DateTime pre = new DateTime(2010, 10, 31, 1, 0, halfHourZone);
        assertEquals("2010-10-31T01:00:00.000+01:00",pre.toString());
    }

    public void testWithMinuteOfHourInDstChange_mockZone_3_oe() {
        DateTime cutover = new DateTime(2010, 10, 31, 1, 15, DateTimeZone.forOffsetHoursMinutes(0, 30));
        // removed other assertion
        DateTimeZone halfHourZone = new MockZone(cutover.getMillis(), 3600000, -1800);
        DateTime pre = new DateTime(2010, 10, 31, 1, 0, halfHourZone);
        // removed other assertion
        DateTime post = new DateTime(2010, 10, 31, 1, 59, halfHourZone);
        assertEquals("2010-10-31T01:59:00.000+00:30",post.toString());
    }

    public void testWithMinuteOfHourInDstChange_mockZone_4_oe() {
        DateTime cutover = new DateTime(2010, 10, 31, 1, 15, DateTimeZone.forOffsetHoursMinutes(0, 30));
        // removed other assertion
        DateTimeZone halfHourZone = new MockZone(cutover.getMillis(), 3600000, -1800);
        DateTime pre = new DateTime(2010, 10, 31, 1, 0, halfHourZone);
        // removed other assertion
        DateTime post = new DateTime(2010, 10, 31, 1, 59, halfHourZone);
        // removed other assertion
        
        DateTime testPre1 = pre.withMinuteOfHour(30);
        assertEquals("2010-10-31T01:30:00.000+01:00",testPre1.toString());// retain offset;
    }

    public void testWithMinuteOfHourInDstChange_mockZone_5_oe() {
        DateTime cutover = new DateTime(2010, 10, 31, 1, 15, DateTimeZone.forOffsetHoursMinutes(0, 30));
        // removed other assertion
        DateTimeZone halfHourZone = new MockZone(cutover.getMillis(), 3600000, -1800);
        DateTime pre = new DateTime(2010, 10, 31, 1, 0, halfHourZone);
        // removed other assertion
        DateTime post = new DateTime(2010, 10, 31, 1, 59, halfHourZone);
        // removed other assertion
        
        DateTime testPre1 = pre.withMinuteOfHour(30);
        // removed other assertion
        DateTime testPre2 = pre.withMinuteOfHour(50);
        assertEquals("2010-10-31T01:50:00.000+00:30",testPre2.toString());
    }

    public void testWithMinuteOfHourInDstChange_mockZone_6_oe() {
        DateTime cutover = new DateTime(2010, 10, 31, 1, 15, DateTimeZone.forOffsetHoursMinutes(0, 30));
        // removed other assertion
        DateTimeZone halfHourZone = new MockZone(cutover.getMillis(), 3600000, -1800);
        DateTime pre = new DateTime(2010, 10, 31, 1, 0, halfHourZone);
        // removed other assertion
        DateTime post = new DateTime(2010, 10, 31, 1, 59, halfHourZone);
        // removed other assertion
        
        DateTime testPre1 = pre.withMinuteOfHour(30);
        // removed other assertion
        DateTime testPre2 = pre.withMinuteOfHour(50);
        // removed other assertion
        
        DateTime testPost1 = post.withMinuteOfHour(30);
        assertEquals("2010-10-31T01:30:00.000+00:30",testPost1.toString());// retain offset;
    }

    public void testWithMinuteOfHourInDstChange_mockZone_7_oe() {
        DateTime cutover = new DateTime(2010, 10, 31, 1, 15, DateTimeZone.forOffsetHoursMinutes(0, 30));
        // removed other assertion
        DateTimeZone halfHourZone = new MockZone(cutover.getMillis(), 3600000, -1800);
        DateTime pre = new DateTime(2010, 10, 31, 1, 0, halfHourZone);
        // removed other assertion
        DateTime post = new DateTime(2010, 10, 31, 1, 59, halfHourZone);
        // removed other assertion
        
        DateTime testPre1 = pre.withMinuteOfHour(30);
        // removed other assertion
        DateTime testPre2 = pre.withMinuteOfHour(50);
        // removed other assertion
        
        DateTime testPost1 = post.withMinuteOfHour(30);
        // removed other assertion
        DateTime testPost2 = post.withMinuteOfHour(10);
        assertEquals("2010-10-31T01:10:00.000+01:00",testPost2.toString());
    }

    public void testWithHourOfDayInDstChange_1_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        assertEquals("2010-10-31T02:30:10.123+02:00",dateTime.toString());
    }

    public void testWithHourOfDayInDstChange_2_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        // removed other assertion
        DateTime test = dateTime.withHourOfDay(2);
        assertEquals("2010-10-31T02:30:10.123+02:00",test.toString());
    }

    public void testWithMinuteOfHourInDstChange_1_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        assertEquals("2010-10-31T02:30:10.123+02:00",dateTime.toString());
    }

    public void testWithMinuteOfHourInDstChange_2_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        // removed other assertion
        DateTime test = dateTime.withMinuteOfHour(0);
        assertEquals("2010-10-31T02:00:10.123+02:00",test.toString());
    }

    public void testWithSecondOfMinuteInDstChange_1_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        assertEquals("2010-10-31T02:30:10.123+02:00",dateTime.toString());
    }

    public void testWithSecondOfMinuteInDstChange_2_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        // removed other assertion
        DateTime test = dateTime.withSecondOfMinute(0);
        assertEquals("2010-10-31T02:30:00.123+02:00",test.toString());
    }

    public void testWithMillisOfSecondInDstChange_Paris_summer_1_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        assertEquals("2010-10-31T02:30:10.123+02:00",dateTime.toString());
    }

    public void testWithMillisOfSecondInDstChange_Paris_summer_2_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        // removed other assertion
        DateTime test = dateTime.withMillisOfSecond(0);
        assertEquals("2010-10-31T02:30:10.000+02:00",test.toString());
    }

    public void testWithMillisOfSecondInDstChange_Paris_winter_1_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+01:00", ZONE_PARIS);
        assertEquals("2010-10-31T02:30:10.123+01:00",dateTime.toString());
    }

    public void testWithMillisOfSecondInDstChange_Paris_winter_2_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+01:00", ZONE_PARIS);
        // removed other assertion
        DateTime test = dateTime.withMillisOfSecond(0);
        assertEquals("2010-10-31T02:30:10.000+01:00",test.toString());
    }

    public void testWithMillisOfSecondInDstChange_NewYork_summer_1_oe() {
        DateTime dateTime = new DateTime("2007-11-04T01:30:00.123-04:00", ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:00.123-04:00",dateTime.toString());
    }

    public void testWithMillisOfSecondInDstChange_NewYork_summer_2_oe() {
        DateTime dateTime = new DateTime("2007-11-04T01:30:00.123-04:00", ZONE_NEW_YORK);
        // removed other assertion
        DateTime test = dateTime.withMillisOfSecond(0);
        assertEquals("2007-11-04T01:30:00.000-04:00",test.toString());
    }

    public void testWithMillisOfSecondInDstChange_NewYork_winter_1_oe() {
        DateTime dateTime = new DateTime("2007-11-04T01:30:00.123-05:00", ZONE_NEW_YORK);
        assertEquals("2007-11-04T01:30:00.123-05:00",dateTime.toString());
    }

    public void testWithMillisOfSecondInDstChange_NewYork_winter_2_oe() {
        DateTime dateTime = new DateTime("2007-11-04T01:30:00.123-05:00", ZONE_NEW_YORK);
        // removed other assertion
        DateTime test = dateTime.withMillisOfSecond(0);
        assertEquals("2007-11-04T01:30:00.000-05:00",test.toString());
    }

    public void testPlusMinutesInDstChange_1_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        assertEquals("2010-10-31T02:30:10.123+02:00",dateTime.toString());
    }

    public void testPlusMinutesInDstChange_2_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        // removed other assertion
        DateTime test = dateTime.plusMinutes(1);
        assertEquals("2010-10-31T02:31:10.123+02:00",test.toString());
    }

    public void testPlusSecondsInDstChange_1_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        assertEquals("2010-10-31T02:30:10.123+02:00",dateTime.toString());
    }

    public void testPlusSecondsInDstChange_2_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        // removed other assertion
        DateTime test = dateTime.plusSeconds(1);
        assertEquals("2010-10-31T02:30:11.123+02:00",test.toString());
    }

    public void testPlusMillisInDstChange_1_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        assertEquals("2010-10-31T02:30:10.123+02:00",dateTime.toString());
    }

    public void testPlusMillisInDstChange_2_oe() {
        DateTime dateTime = new DateTime("2010-10-31T02:30:10.123+02:00", ZONE_PARIS);
        // removed other assertion
        DateTime test = dateTime.plusMillis(1);
        assertEquals("2010-10-31T02:30:10.124+02:00",test.toString());
    }

    public void testBug2182444_usCentral_1_oe() {
        Chronology chronUSCentral = GregorianChronology.getInstance(DateTimeZone.forID("US/Central"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime usCentralStandardInUTC = new DateTime(2008, 11, 2, 7, 0, 0, 0, chronUTC);
        DateTime usCentralDaylightInUTC = new DateTime(2008, 11, 2, 6, 0, 0, 0, chronUTC);
        assertTrue("Should be standard time",chronUSCentral.getZone().isStandardOffset(usCentralStandardInUTC.getMillis()));
    }

    public void testBug2182444_usCentral_2_oe() {
        Chronology chronUSCentral = GregorianChronology.getInstance(DateTimeZone.forID("US/Central"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime usCentralStandardInUTC = new DateTime(2008, 11, 2, 7, 0, 0, 0, chronUTC);
        DateTime usCentralDaylightInUTC = new DateTime(2008, 11, 2, 6, 0, 0, 0, chronUTC);
        // removed other assertion
        assertFalse("Should be daylight time",chronUSCentral.getZone().isStandardOffset(usCentralDaylightInUTC.getMillis()));
    }

    public void testBug2182444_usCentral_3_oe() {
        Chronology chronUSCentral = GregorianChronology.getInstance(DateTimeZone.forID("US/Central"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime usCentralStandardInUTC = new DateTime(2008, 11, 2, 7, 0, 0, 0, chronUTC);
        DateTime usCentralDaylightInUTC = new DateTime(2008, 11, 2, 6, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime usCentralStandardInUSCentral = usCentralStandardInUTC.toDateTime(chronUSCentral);
        DateTime usCentralDaylightInUSCentral = usCentralDaylightInUTC.toDateTime(chronUSCentral);
        assertEquals(1,usCentralStandardInUSCentral.getHourOfDay());
    }

    public void testBug2182444_usCentral_4_oe() {
        Chronology chronUSCentral = GregorianChronology.getInstance(DateTimeZone.forID("US/Central"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime usCentralStandardInUTC = new DateTime(2008, 11, 2, 7, 0, 0, 0, chronUTC);
        DateTime usCentralDaylightInUTC = new DateTime(2008, 11, 2, 6, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime usCentralStandardInUSCentral = usCentralStandardInUTC.toDateTime(chronUSCentral);
        DateTime usCentralDaylightInUSCentral = usCentralDaylightInUTC.toDateTime(chronUSCentral);
        // removed other assertion
        assertEquals(usCentralStandardInUSCentral.getHourOfDay(),usCentralDaylightInUSCentral.getHourOfDay());
    }

    public void testBug2182444_usCentral_5_oe() {
        Chronology chronUSCentral = GregorianChronology.getInstance(DateTimeZone.forID("US/Central"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime usCentralStandardInUTC = new DateTime(2008, 11, 2, 7, 0, 0, 0, chronUTC);
        DateTime usCentralDaylightInUTC = new DateTime(2008, 11, 2, 6, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime usCentralStandardInUSCentral = usCentralStandardInUTC.toDateTime(chronUSCentral);
        DateTime usCentralDaylightInUSCentral = usCentralDaylightInUTC.toDateTime(chronUSCentral);
        // removed other assertion
        // removed other assertion
        assertTrue(usCentralStandardInUSCentral.getMillis()!= usCentralDaylightInUSCentral.getMillis());
    }

    public void testBug2182444_usCentral_6_oe() {
        Chronology chronUSCentral = GregorianChronology.getInstance(DateTimeZone.forID("US/Central"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime usCentralStandardInUTC = new DateTime(2008, 11, 2, 7, 0, 0, 0, chronUTC);
        DateTime usCentralDaylightInUTC = new DateTime(2008, 11, 2, 6, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime usCentralStandardInUSCentral = usCentralStandardInUTC.toDateTime(chronUSCentral);
        DateTime usCentralDaylightInUSCentral = usCentralDaylightInUTC.toDateTime(chronUSCentral);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(usCentralStandardInUSCentral,usCentralStandardInUSCentral.withHourOfDay(1));
    }

    public void testBug2182444_usCentral_7_oe() {
        Chronology chronUSCentral = GregorianChronology.getInstance(DateTimeZone.forID("US/Central"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime usCentralStandardInUTC = new DateTime(2008, 11, 2, 7, 0, 0, 0, chronUTC);
        DateTime usCentralDaylightInUTC = new DateTime(2008, 11, 2, 6, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime usCentralStandardInUSCentral = usCentralStandardInUTC.toDateTime(chronUSCentral);
        DateTime usCentralDaylightInUSCentral = usCentralDaylightInUTC.toDateTime(chronUSCentral);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(usCentralStandardInUSCentral.getMillis()+ 3,usCentralStandardInUSCentral.withMillisOfSecond(3).getMillis());
    }

    public void testBug2182444_usCentral_8_oe() {
        Chronology chronUSCentral = GregorianChronology.getInstance(DateTimeZone.forID("US/Central"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime usCentralStandardInUTC = new DateTime(2008, 11, 2, 7, 0, 0, 0, chronUTC);
        DateTime usCentralDaylightInUTC = new DateTime(2008, 11, 2, 6, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime usCentralStandardInUSCentral = usCentralStandardInUTC.toDateTime(chronUSCentral);
        DateTime usCentralDaylightInUSCentral = usCentralDaylightInUTC.toDateTime(chronUSCentral);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(usCentralDaylightInUSCentral,usCentralDaylightInUSCentral.withHourOfDay(1));
    }

    public void testBug2182444_usCentral_9_oe() {
        Chronology chronUSCentral = GregorianChronology.getInstance(DateTimeZone.forID("US/Central"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime usCentralStandardInUTC = new DateTime(2008, 11, 2, 7, 0, 0, 0, chronUTC);
        DateTime usCentralDaylightInUTC = new DateTime(2008, 11, 2, 6, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime usCentralStandardInUSCentral = usCentralStandardInUTC.toDateTime(chronUSCentral);
        DateTime usCentralDaylightInUSCentral = usCentralDaylightInUTC.toDateTime(chronUSCentral);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(usCentralDaylightInUSCentral.getMillis()+ 3,usCentralDaylightInUSCentral.withMillisOfSecond(3).getMillis());
    }

    public void testBug2182444_ausNSW_1_oe() {
        Chronology chronAusNSW = GregorianChronology.getInstance(DateTimeZone.forID("Australia/NSW"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime australiaNSWStandardInUTC = new DateTime(2008, 4, 5, 16, 0, 0, 0, chronUTC);
        DateTime australiaNSWDaylightInUTC = new DateTime(2008, 4, 5, 15, 0, 0, 0, chronUTC);
        assertTrue("Should be standard time",chronAusNSW.getZone().isStandardOffset(australiaNSWStandardInUTC.getMillis()));
    }

    public void testBug2182444_ausNSW_2_oe() {
        Chronology chronAusNSW = GregorianChronology.getInstance(DateTimeZone.forID("Australia/NSW"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime australiaNSWStandardInUTC = new DateTime(2008, 4, 5, 16, 0, 0, 0, chronUTC);
        DateTime australiaNSWDaylightInUTC = new DateTime(2008, 4, 5, 15, 0, 0, 0, chronUTC);
        // removed other assertion
        assertFalse("Should be daylight time",chronAusNSW.getZone().isStandardOffset(australiaNSWDaylightInUTC.getMillis()));
    }

    public void testBug2182444_ausNSW_3_oe() {
        Chronology chronAusNSW = GregorianChronology.getInstance(DateTimeZone.forID("Australia/NSW"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime australiaNSWStandardInUTC = new DateTime(2008, 4, 5, 16, 0, 0, 0, chronUTC);
        DateTime australiaNSWDaylightInUTC = new DateTime(2008, 4, 5, 15, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime australiaNSWStandardInAustraliaNSW = australiaNSWStandardInUTC.toDateTime(chronAusNSW);
        DateTime australiaNSWDaylightInAusraliaNSW = australiaNSWDaylightInUTC.toDateTime(chronAusNSW);
        assertEquals(2,australiaNSWStandardInAustraliaNSW.getHourOfDay());
    }

    public void testBug2182444_ausNSW_4_oe() {
        Chronology chronAusNSW = GregorianChronology.getInstance(DateTimeZone.forID("Australia/NSW"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime australiaNSWStandardInUTC = new DateTime(2008, 4, 5, 16, 0, 0, 0, chronUTC);
        DateTime australiaNSWDaylightInUTC = new DateTime(2008, 4, 5, 15, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime australiaNSWStandardInAustraliaNSW = australiaNSWStandardInUTC.toDateTime(chronAusNSW);
        DateTime australiaNSWDaylightInAusraliaNSW = australiaNSWDaylightInUTC.toDateTime(chronAusNSW);
        // removed other assertion
        assertEquals(australiaNSWStandardInAustraliaNSW.getHourOfDay(),australiaNSWDaylightInAusraliaNSW.getHourOfDay());
    }

    public void testBug2182444_ausNSW_5_oe() {
        Chronology chronAusNSW = GregorianChronology.getInstance(DateTimeZone.forID("Australia/NSW"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime australiaNSWStandardInUTC = new DateTime(2008, 4, 5, 16, 0, 0, 0, chronUTC);
        DateTime australiaNSWDaylightInUTC = new DateTime(2008, 4, 5, 15, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime australiaNSWStandardInAustraliaNSW = australiaNSWStandardInUTC.toDateTime(chronAusNSW);
        DateTime australiaNSWDaylightInAusraliaNSW = australiaNSWDaylightInUTC.toDateTime(chronAusNSW);
        // removed other assertion
        // removed other assertion
        assertTrue(australiaNSWStandardInAustraliaNSW.getMillis()!= australiaNSWDaylightInAusraliaNSW.getMillis());
    }

    public void testBug2182444_ausNSW_6_oe() {
        Chronology chronAusNSW = GregorianChronology.getInstance(DateTimeZone.forID("Australia/NSW"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime australiaNSWStandardInUTC = new DateTime(2008, 4, 5, 16, 0, 0, 0, chronUTC);
        DateTime australiaNSWDaylightInUTC = new DateTime(2008, 4, 5, 15, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime australiaNSWStandardInAustraliaNSW = australiaNSWStandardInUTC.toDateTime(chronAusNSW);
        DateTime australiaNSWDaylightInAusraliaNSW = australiaNSWDaylightInUTC.toDateTime(chronAusNSW);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(australiaNSWStandardInAustraliaNSW,australiaNSWStandardInAustraliaNSW.withHourOfDay(2));
    }

    public void testBug2182444_ausNSW_7_oe() {
        Chronology chronAusNSW = GregorianChronology.getInstance(DateTimeZone.forID("Australia/NSW"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime australiaNSWStandardInUTC = new DateTime(2008, 4, 5, 16, 0, 0, 0, chronUTC);
        DateTime australiaNSWDaylightInUTC = new DateTime(2008, 4, 5, 15, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime australiaNSWStandardInAustraliaNSW = australiaNSWStandardInUTC.toDateTime(chronAusNSW);
        DateTime australiaNSWDaylightInAusraliaNSW = australiaNSWDaylightInUTC.toDateTime(chronAusNSW);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(australiaNSWStandardInAustraliaNSW.getMillis()+ 3,australiaNSWStandardInAustraliaNSW.withMillisOfSecond(3).getMillis());
    }

    public void testBug2182444_ausNSW_8_oe() {
        Chronology chronAusNSW = GregorianChronology.getInstance(DateTimeZone.forID("Australia/NSW"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime australiaNSWStandardInUTC = new DateTime(2008, 4, 5, 16, 0, 0, 0, chronUTC);
        DateTime australiaNSWDaylightInUTC = new DateTime(2008, 4, 5, 15, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime australiaNSWStandardInAustraliaNSW = australiaNSWStandardInUTC.toDateTime(chronAusNSW);
        DateTime australiaNSWDaylightInAusraliaNSW = australiaNSWDaylightInUTC.toDateTime(chronAusNSW);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(australiaNSWDaylightInAusraliaNSW,australiaNSWDaylightInAusraliaNSW.withHourOfDay(2));
    }

    public void testBug2182444_ausNSW_9_oe() {
        Chronology chronAusNSW = GregorianChronology.getInstance(DateTimeZone.forID("Australia/NSW"));
        Chronology chronUTC = GregorianChronology.getInstance(DateTimeZone.UTC);
        DateTime australiaNSWStandardInUTC = new DateTime(2008, 4, 5, 16, 0, 0, 0, chronUTC);
        DateTime australiaNSWDaylightInUTC = new DateTime(2008, 4, 5, 15, 0, 0, 0, chronUTC);
        // removed other assertion
        // removed other assertion
        
        DateTime australiaNSWStandardInAustraliaNSW = australiaNSWStandardInUTC.toDateTime(chronAusNSW);
        DateTime australiaNSWDaylightInAusraliaNSW = australiaNSWDaylightInUTC.toDateTime(chronAusNSW);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(australiaNSWDaylightInAusraliaNSW.getMillis()+ 3,australiaNSWDaylightInAusraliaNSW.withMillisOfSecond(3).getMillis());
    }

    public void testPeriod_1_oe() {
        DateTime a = new DateTime("2010-10-31T02:00:00.000+02:00", ZONE_PARIS);
        DateTime b = new DateTime("2010-10-31T02:01:00.000+02:00", ZONE_PARIS);
        Period period = new Period(a, b, PeriodType.standard());
        assertEquals("PT1M",period.toString());
    }

    public void testForum4013394_retainOffsetWhenRetainFields_sameOffsetsDifferentZones_1_oe() {
        final DateTimeZone fromDTZ = DateTimeZone.forID("Europe/London");
        final DateTimeZone toDTZ = DateTimeZone.forID("Europe/Lisbon");
        DateTime baseBefore = new DateTime(2007, 10, 28, 1, 15, fromDTZ).minusHours(1);
        DateTime baseAfter = new DateTime(2007, 10, 28, 1, 15, fromDTZ);
        DateTime testBefore = baseBefore.withZoneRetainFields(toDTZ);
        DateTime testAfter = baseAfter.withZoneRetainFields(toDTZ);
        // toString ignores time-zone but includes offset
        assertEquals(baseBefore.toString(),testBefore.toString());
    }

    public void testForum4013394_retainOffsetWhenRetainFields_sameOffsetsDifferentZones_2_oe() {
        final DateTimeZone fromDTZ = DateTimeZone.forID("Europe/London");
        final DateTimeZone toDTZ = DateTimeZone.forID("Europe/Lisbon");
        DateTime baseBefore = new DateTime(2007, 10, 28, 1, 15, fromDTZ).minusHours(1);
        DateTime baseAfter = new DateTime(2007, 10, 28, 1, 15, fromDTZ);
        DateTime testBefore = baseBefore.withZoneRetainFields(toDTZ);
        DateTime testAfter = baseAfter.withZoneRetainFields(toDTZ);
        // toString ignores time-zone but includes offset
        // removed other assertion
        assertEquals(baseAfter.toString(),testAfter.toString());
    }

    public void testBug3192457_adjustOffset_1_oe() {
        final DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        DateTime base = new DateTime(2007, 10, 28, 3, 15, zone);
        DateTime baseBefore = base.minusHours(2);
        DateTime baseAfter = base.minusHours(1);
        
        assertSame(base,base.withEarlierOffsetAtOverlap());
    }

    public void testBug3192457_adjustOffset_2_oe() {
        final DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        DateTime base = new DateTime(2007, 10, 28, 3, 15, zone);
        DateTime baseBefore = base.minusHours(2);
        DateTime baseAfter = base.minusHours(1);
        
        // removed other assertion
        assertSame(base,base.withLaterOffsetAtOverlap());
    }

    public void testBug3192457_adjustOffset_3_oe() {
        final DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        DateTime base = new DateTime(2007, 10, 28, 3, 15, zone);
        DateTime baseBefore = base.minusHours(2);
        DateTime baseAfter = base.minusHours(1);
        
        // removed other assertion
        // removed other assertion
        
        assertSame(baseBefore,baseBefore.withEarlierOffsetAtOverlap());
    }

    public void testBug3192457_adjustOffset_4_oe() {
        final DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        DateTime base = new DateTime(2007, 10, 28, 3, 15, zone);
        DateTime baseBefore = base.minusHours(2);
        DateTime baseAfter = base.minusHours(1);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(baseAfter,baseBefore.withLaterOffsetAtOverlap());
    }

    public void testBug3192457_adjustOffset_5_oe() {
        final DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        DateTime base = new DateTime(2007, 10, 28, 3, 15, zone);
        DateTime baseBefore = base.minusHours(2);
        DateTime baseAfter = base.minusHours(1);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertSame(baseAfter,baseAfter.withLaterOffsetAtOverlap());
    }

    public void testBug3192457_adjustOffset_6_oe() {
        final DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        DateTime base = new DateTime(2007, 10, 28, 3, 15, zone);
        DateTime baseBefore = base.minusHours(2);
        DateTime baseAfter = base.minusHours(1);
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(baseBefore,baseAfter.withEarlierOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_1_oe() {
        final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
        DateTime base = new DateTime(2012, 2, 25, 22, 15, zone);
        DateTime baseBefore = base.plusHours(1);  // 23:15 (first)
        DateTime baseAfter = base.plusHours(2);  // 23:15 (second)
        
        assertSame(base,base.withEarlierOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_2_oe() {
        final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
        DateTime base = new DateTime(2012, 2, 25, 22, 15, zone);
        DateTime baseBefore = base.plusHours(1);  // 23:15 (first)
        DateTime baseAfter = base.plusHours(2);  // 23:15 (second)
        
        // removed other assertion
        assertSame(base,base.withLaterOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_3_oe() {
        final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
        DateTime base = new DateTime(2012, 2, 25, 22, 15, zone);
        DateTime baseBefore = base.plusHours(1);  // 23:15 (first)
        DateTime baseAfter = base.plusHours(2);  // 23:15 (second)
        
        // removed other assertion
        // removed other assertion
        
        assertSame(baseBefore,baseBefore.withEarlierOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_4_oe() {
        final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
        DateTime base = new DateTime(2012, 2, 25, 22, 15, zone);
        DateTime baseBefore = base.plusHours(1);  // 23:15 (first)
        DateTime baseAfter = base.plusHours(2);  // 23:15 (second)
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(baseAfter,baseBefore.withLaterOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_5_oe() {
        final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
        DateTime base = new DateTime(2012, 2, 25, 22, 15, zone);
        DateTime baseBefore = base.plusHours(1);  // 23:15 (first)
        DateTime baseAfter = base.plusHours(2);  // 23:15 (second)
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertSame(baseAfter,baseAfter.withLaterOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_6_oe() {
        final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
        DateTime base = new DateTime(2012, 2, 25, 22, 15, zone);
        DateTime baseBefore = base.plusHours(1);  // 23:15 (first)
        DateTime baseAfter = base.plusHours(2);  // 23:15 (second)
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(baseBefore,baseAfter.withEarlierOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_springGap_1_oe() {
      final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
      DateTime base = new DateTime(2011, 10, 15, 22, 15, zone);
      DateTime baseBefore = base.plusHours(1);  // 23:15
      DateTime baseAfter = base.plusHours(2);  // 01:15
      
      assertSame(base,base.withEarlierOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_springGap_2_oe() {
      final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
      DateTime base = new DateTime(2011, 10, 15, 22, 15, zone);
      DateTime baseBefore = base.plusHours(1);  // 23:15
      DateTime baseAfter = base.plusHours(2);  // 01:15
      
      // removed other assertion
      assertSame(base,base.withLaterOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_springGap_3_oe() {
      final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
      DateTime base = new DateTime(2011, 10, 15, 22, 15, zone);
      DateTime baseBefore = base.plusHours(1);  // 23:15
      DateTime baseAfter = base.plusHours(2);  // 01:15
      
      // removed other assertion
      // removed other assertion
      
      assertSame(baseBefore,baseBefore.withEarlierOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_springGap_4_oe() {
      final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
      DateTime base = new DateTime(2011, 10, 15, 22, 15, zone);
      DateTime baseBefore = base.plusHours(1);  // 23:15
      DateTime baseAfter = base.plusHours(2);  // 01:15
      
      // removed other assertion
      // removed other assertion
      
      // removed other assertion
      assertEquals(baseBefore,baseBefore.withLaterOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_springGap_5_oe() {
      final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
      DateTime base = new DateTime(2011, 10, 15, 22, 15, zone);
      DateTime baseBefore = base.plusHours(1);  // 23:15
      DateTime baseAfter = base.plusHours(2);  // 01:15
      
      // removed other assertion
      // removed other assertion
      
      // removed other assertion
      // removed other assertion
      
      assertSame(baseAfter,baseAfter.withLaterOffsetAtOverlap());
    }

    public void testBug3476684_adjustOffset_springGap_6_oe() {
      final DateTimeZone zone = DateTimeZone.forID("America/Sao_Paulo");
      DateTime base = new DateTime(2011, 10, 15, 22, 15, zone);
      DateTime baseBefore = base.plusHours(1);  // 23:15
      DateTime baseAfter = base.plusHours(2);  // 01:15
      
      // removed other assertion
      // removed other assertion
      
      // removed other assertion
      // removed other assertion
      
      // removed other assertion
      assertEquals(baseAfter,baseAfter.withEarlierOffsetAtOverlap());
    }

    public void testDateTimeCreation_athens_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Athens");
        DateTime base = new DateTime(2011, 10, 30, 3, 15, zone);
        assertEquals("2011-10-30T03:15:00.000+03:00",base.toString());
    }

    public void testDateTimeCreation_athens_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Athens");
        DateTime base = new DateTime(2011, 10, 30, 3, 15, zone);
        // removed other assertion
        assertEquals("2011-10-30T03:15:00.000+02:00",base.plusHours(1).toString());
    }

    public void testDateTimeCreation_paris_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        DateTime base = new DateTime(2011, 10, 30, 2, 15, zone);
        assertEquals("2011-10-30T02:15:00.000+02:00",base.toString());
    }

    public void testDateTimeCreation_paris_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/Paris");
        DateTime base = new DateTime(2011, 10, 30, 2, 15, zone);
        // removed other assertion
        assertEquals("2011-10-30T02:15:00.000+01:00",base.plusHours(1).toString());
    }

    public void testDateTimeCreation_london_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        DateTime base = new DateTime(2011, 10, 30, 1, 15, zone);
        assertEquals("2011-10-30T01:15:00.000+01:00",base.toString());
    }

    public void testDateTimeCreation_london_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("Europe/London");
        DateTime base = new DateTime(2011, 10, 30, 1, 15, zone);
        // removed other assertion
        assertEquals("2011-10-30T01:15:00.000Z",base.plusHours(1).toString());
    }

    public void testDateTimeCreation_newYork_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        DateTime base = new DateTime(2010, 11, 7, 1, 15, zone);
        assertEquals("2010-11-07T01:15:00.000-04:00",base.toString());
    }

    public void testDateTimeCreation_newYork_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        DateTime base = new DateTime(2010, 11, 7, 1, 15, zone);
        // removed other assertion
        assertEquals("2010-11-07T01:15:00.000-05:00",base.plusHours(1).toString());
    }

    public void testDateTimeCreation_losAngeles_1_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Los_Angeles");
        DateTime base = new DateTime(2010, 11, 7, 1, 15, zone);
        assertEquals("2010-11-07T01:15:00.000-07:00",base.toString());
    }

    public void testDateTimeCreation_losAngeles_2_oe() {
        DateTimeZone zone = DateTimeZone.forID("America/Los_Angeles");
        DateTime base = new DateTime(2010, 11, 7, 1, 15, zone);
        // removed other assertion
        assertEquals("2010-11-07T01:15:00.000-08:00",base.plusHours(1).toString());
    }

}
