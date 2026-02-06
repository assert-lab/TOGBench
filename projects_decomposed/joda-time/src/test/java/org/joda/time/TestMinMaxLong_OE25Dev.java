/*
 *  Copyright 2001-2015 Stephen Colebourne
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

import java.util.Locale;

import org.joda.time.chrono.ISOChronology;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This class is a Junit unit test for min/max long values.
 *
 * @author Stephen Colebourne
 */
public class TestMinMaxLong_OE25Dev extends TestCase {

    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final int ACTUAL_MAX_YEAR = 292278994;
    private static final int ACTUAL_MIN_YEAR = -292275055;

    private DateTimeZone zone = null;
    private Locale locale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMinMaxLong_OE25Dev_OE25Dev.class);
    }

    public TestMinMaxLong_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        zone = DateTimeZone.getDefault();
        locale = Locale.getDefault();
        DateTimeZone.setDefault(LONDON);
        java.util.TimeZone.setDefault(LONDON.toTimeZone());
        Locale.setDefault(Locale.UK);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeZone.setDefault(zone);
        java.util.TimeZone.setDefault(zone.toTimeZone());
        Locale.setDefault(locale);
        zone = null;
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testDateTime_max_1_oe() throws Throwable {
        // toString adjusts to UTC rather than overflow
        DateTime dt = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807, DateTimeZone.UTC);
        assertEquals(Long.MAX_VALUE,dt.getMillis());
    }

    public void testDateTime_max_2_oe() throws Throwable {
        // toString adjusts to UTC rather than overflow
        DateTime dt = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807, DateTimeZone.UTC);
        // removed other assertion
        assertEquals(ISOChronology.getInstanceUTC(),dt.getChronology());
    }

    public void testDateTime_max_3_oe() throws Throwable {
        // toString adjusts to UTC rather than overflow
        DateTime dt = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807, DateTimeZone.UTC);
        // removed other assertion
        // removed other assertion
        DateTime test = new DateTime(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE,test.getMillis());
    }

    public void testDateTime_max_4_oe() throws Throwable {
        // toString adjusts to UTC rather than overflow
        DateTime dt = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807, DateTimeZone.UTC);
        // removed other assertion
        // removed other assertion
        DateTime test = new DateTime(Long.MAX_VALUE);
        // removed other assertion
        assertEquals(ISOChronology.getInstanceUTC(),test.getChronology());
    }

    public void testDateTime_max_math_1_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        assertEquals("292278994-08-17T07:12:55.807Z",test.toString());
    }

    public void testDateTime_max_math_2_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        assertEquals(new DateTime(Long.MAX_VALUE - 807,DateTimeZone.UTC),test.minus(807));
    }

    public void testDateTime_max_math_3_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.000Z",test.minus(807).toString());
    }

    public void testDateTime_max_math_4_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MAX_VALUE - 1000,DateTimeZone.UTC),test.minusSeconds(1));
    }

    public void testDateTime_max_math_5_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("292278994-08-17T07:12:54.807Z",test.minusSeconds(1).toString());
    }

    public void testDateTime_max_math_6_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MAX_VALUE - 60000,DateTimeZone.UTC),test.minusMinutes(1));
    }

    public void testDateTime_max_math_7_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("292278994-08-17T07:11:55.807Z",test.minusMinutes(1).toString());
    }

    public void testDateTime_max_math_8_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MAX_VALUE - 3600000,DateTimeZone.UTC),test.minusHours(1));
    }

    public void testDateTime_max_math_9_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("292278994-08-17T06:12:55.807Z",test.minusHours(1).toString());
    }

    public void testDateTime_max_math_10_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MAX_VALUE - 3600000,DateTimeZone.UTC),test.minusHours(1));
    }

    public void testDateTime_max_math_11_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("292278994-08-17T06:12:55.808Z",test.minusHours(1).plusMillis(1).toString());
    }

    public void testDateTime_max_math_12_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MAX_VALUE -(3600000 - 60000),DateTimeZone.UTC),test.minusMinutes(59));
    }

    public void testDateTime_max_math_13_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("292278994-08-17T06:13:55.807Z",test.minusMinutes(59).toString());
    }

    public void testDateTime_max_math_14_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MAX_VALUE -(3600000 - 60000)+ 1,DateTimeZone.UTC),test.minusMinutes(59).plusMillis(1));
    }

    public void testDateTime_max_math_15_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("292278994-08-17T06:13:55.808Z",test.minusMinutes(59).plusMillis(1).toString());
    }

    public void testDateTime_max_math_16_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MAX_VALUE - 3600000,DateTimeZone.UTC),test.minusMinutes(61).plusMinutes(1));
    }

    public void testDateTime_max_math_17_oe() throws Throwable {
        DateTime test = new DateTime(Long.MAX_VALUE);  // always in UTC
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("292278994-08-17T06:12:55.807Z",test.minusHours(1).toString());
    }

    public void testDateTime_max_fields_1_oe() {
        assertEquals(ACTUAL_MAX_YEAR,ISOChronology.getInstanceUTC().year().getMaximumValue()+ 1);
    }

    public void testDateTime_max_fields_2_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        assertEquals("292278992-06-30T00:00:00.000+01:00",new DateTime(292278992,6,30,0,0).toString());
    }

    public void testDateTime_max_fields_3_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        assertEquals("292278992-12-31T00:00:00.000Z",new DateTime(292278992,12,31,0,0).toString());
    }

    public void testDateTime_max_fields_4_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        assertEquals("292278993-12-31T00:00:00.000Z",new DateTime(292278993,12,31,0,0).toString());
    }

    public void testDateTime_max_fields_5_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        assertEquals("292278994-01-01T00:00:00.000Z",a.toString());
    }

    public void testDateTime_max_fields_6_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        assertEquals("292278994-08-17T07:00:00.000+01:00",b.toString());
    }

    public void testDateTime_max_fields_7_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        assertEquals("292278994-08-17T07:12:55.000+01:00",c.toString());
    }

    public void testDateTime_max_fields_8_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        assertEquals(new DateTime(Long.MAX_VALUE - 1 - 3600000),d);
    }

    public void testDateTime_max_fields_9_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.806+01:00",d.toString());
    }

    public void testDateTime_max_fields_10_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        assertEquals(new DateTime(Long.MAX_VALUE),e);
    }

    public void testDateTime_max_fields_11_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.807Z",e.toString());
    }

    public void testDateTime_max_fields_12_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 808);
        assertEquals(new DateTime(Long.MAX_VALUE),f);
    }

    public void testDateTime_max_fields_13_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 808);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.807Z",f.toString());
    }

    public void testDateTime_max_fields_14_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 808);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime g = new DateTime(ACTUAL_MAX_YEAR, 12, 31, 23, 59, 59, 999);
        assertEquals(new DateTime(Long.MAX_VALUE),g);
    }

    public void testDateTime_max_fields_15_oe() {
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000+01:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 808);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime g = new DateTime(ACTUAL_MAX_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.807Z",g.toString());
    }

    public void testDateTime_max_fieldsUTC_1_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        assertEquals(ACTUAL_MAX_YEAR,ISOChronology.getInstanceUTC().year().getMaximumValue()+ 1);
    }

    public void testDateTime_max_fieldsUTC_2_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        assertEquals("292278992-06-30T00:00:00.000Z",new DateTime(292278992,6,30,0,0).toString());
    }

    public void testDateTime_max_fieldsUTC_3_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        assertEquals("292278992-12-31T00:00:00.000Z",new DateTime(292278992,12,31,0,0).toString());
    }

    public void testDateTime_max_fieldsUTC_4_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        assertEquals("292278993-06-30T00:00:00.000Z",new DateTime(292278993,6,30,0,0).toString());
    }

    public void testDateTime_max_fieldsUTC_5_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("292278993-12-31T00:00:00.000Z",new DateTime(292278993,12,31,0,0).toString());
    }

    public void testDateTime_max_fieldsUTC_6_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        assertEquals("292278994-01-01T00:00:00.000Z",a.toString());
    }

    public void testDateTime_max_fieldsUTC_7_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        assertEquals("292278994-08-17T07:00:00.000Z",b.toString());
    }

    public void testDateTime_max_fieldsUTC_8_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        assertEquals("292278994-08-17T07:12:55.000Z",c.toString());
    }

    public void testDateTime_max_fieldsUTC_9_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        assertEquals(new DateTime(Long.MAX_VALUE - 1),d);
    }

    public void testDateTime_max_fieldsUTC_10_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.806Z",d.toString());
    }

    public void testDateTime_max_fieldsUTC_11_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        assertEquals(new DateTime(Long.MAX_VALUE),e);
    }

    public void testDateTime_max_fieldsUTC_12_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.807Z",e.toString());
    }

    public void testDateTime_max_fieldsUTC_13_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 808);
        assertEquals(new DateTime(Long.MAX_VALUE),f);
    }

    public void testDateTime_max_fieldsUTC_14_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 808);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.807Z",f.toString());
    }

    public void testDateTime_max_fieldsUTC_15_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 808);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime g = new DateTime(ACTUAL_MAX_YEAR, 12, 31, 23, 59, 59, 999);
        assertEquals(new DateTime(Long.MAX_VALUE),g);
    }

    public void testDateTime_max_fieldsUTC_16_oe() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 808);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime g = new DateTime(ACTUAL_MAX_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.807Z",g.toString());
    }

    public void testDateTime_max_fieldsNewYork_1_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        assertEquals(ACTUAL_MAX_YEAR,ISOChronology.getInstanceUTC().year().getMaximumValue()+ 1);
    }

    public void testDateTime_max_fieldsNewYork_2_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        assertEquals("292278992-06-30T00:00:00.000-04:00",new DateTime(292278992,6,30,0,0).toString());
    }

    public void testDateTime_max_fieldsNewYork_3_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        assertEquals("292278992-12-31T00:00:00.000-05:00",new DateTime(292278992,12,31,0,0).toString());
    }

    public void testDateTime_max_fieldsNewYork_4_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        assertEquals("292278993-12-31T00:00:00.000-05:00",new DateTime(292278993,12,31,0,0).toString());
    }

    public void testDateTime_max_fieldsNewYork_5_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        assertEquals("292278994-01-01T00:00:00.000-05:00",a.toString());
    }

    public void testDateTime_max_fieldsNewYork_6_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 0, 0, 0);
        assertEquals("292278994-08-17T03:00:00.000-04:00",b.toString());
    }

    public void testDateTime_max_fieldsNewYork_7_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 0);
        assertEquals("292278994-08-17T03:12:55.000-04:00",c.toString());
    }

    public void testDateTime_max_fieldsNewYork_8_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 806);
        assertEquals(new DateTime(Long.MAX_VALUE - 1),d);
    }

    public void testDateTime_max_fieldsNewYork_9_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 806);
        // removed other assertion
        assertEquals("292278994-08-17T03:12:55.806-04:00",d.toString());
    }

    public void testDateTime_max_fieldsNewYork_10_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 807);
        assertEquals(new DateTime(Long.MAX_VALUE),e);
    }

    public void testDateTime_max_fieldsNewYork_11_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 807);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.807Z",e.toString());
    }

    public void testDateTime_max_fieldsNewYork_12_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        assertEquals(new DateTime(Long.MAX_VALUE),f);
    }

    public void testDateTime_max_fieldsNewYork_13_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.807Z",f.toString());
    }

    public void testDateTime_max_fieldsNewYork_14_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime g = new DateTime(ACTUAL_MAX_YEAR, 12, 31, 23, 59, 59, 999);
        assertEquals(new DateTime(Long.MAX_VALUE),g);
    }

    public void testDateTime_max_fieldsNewYork_15_oe() {
        DateTimeZone.setDefault(DateTimeZone.forID("America/New_York"));
        // removed other assertion
        // ensure time-zone correct in previous year
        // removed other assertion
        // removed other assertion
//        assertEquals("292278993-06-30T00:00:00.000-04:00",new DateTime(292278993,6,30,0,0).toString());
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MAX_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 806);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 3, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MAX_YEAR, 8, 17, 7, 12, 55, 807);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime g = new DateTime(ACTUAL_MAX_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.807Z",g.toString());
    }

    public void testDateTime_max_long_1_oe() {
        assertEquals("292278994-08-17T07:12:55.807+01:00",new DateTime(Long.MAX_VALUE - 3600000).toString());
    }

    public void testDateTime_max_long_2_oe() {
        // removed other assertion
        assertEquals("292278994-08-17T06:12:55.808Z",new DateTime(Long.MAX_VALUE - 3599999).toString());
    }

    public void testDateTime_max_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("292278994-08-17T07:11:55.807Z",new DateTime(Long.MAX_VALUE - 60000).toString());
    }

    public void testDateTime_max_long_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.000Z",new DateTime(Long.MAX_VALUE - 807).toString());
    }

    public void testDateTime_max_long_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.806Z",new DateTime(Long.MAX_VALUE - 1).toString());
    }

    public void testDateTime_max_long_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("292278994-08-17T07:12:55.807Z",new DateTime(Long.MAX_VALUE).toString());
    }

    public void testPrintParseMax_1_oe() {
        DateTime test1 = new DateTime(Long.MAX_VALUE);
        assertEquals(test1,DateTime.parse(test1.toString()));
    }

    public void testPrintParseMax_2_oe() {
        DateTime test1 = new DateTime(Long.MAX_VALUE);
        // removed other assertion
        DateTime test2 = new DateTime(Long.valueOf(Long.MAX_VALUE));
        assertEquals(test2,DateTime.parse(test2.toString()));
    }

    public void testPrintParseMax_3_oe() {
        DateTime test1 = new DateTime(Long.MAX_VALUE);
        // removed other assertion
        DateTime test2 = new DateTime(Long.valueOf(Long.MAX_VALUE));
        // removed other assertion
        assertEquals(test2,test1);
    }

    public void testDateTime_min_1_oe() throws Throwable {
        DateTime dt = new DateTime(-292275054, 1, 1, 0, 0);
        DateTime test = new DateTime(dt.getMillis());
        assertEquals(dt,test);
    }

    public void testDateTime_min_2_oe() throws Throwable {
        DateTime dt = new DateTime(-292275054, 1, 1, 0, 0);
        DateTime test = new DateTime(dt.getMillis());
        // removed other assertion
        assertEquals("-292275054-01-01T00:00:00.000-00:01:15",test.toString());
    }

    public void testDateTime_min_math_1_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        assertEquals("-292275055-05-16T16:47:04.192Z",test.toString());
    }

    public void testDateTime_min_math_2_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        // removed other assertion

        assertEquals(new DateTime(Long.MIN_VALUE + 808,DateTimeZone.UTC),test.plus(808));
    }

    public void testDateTime_min_math_3_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        // removed other assertion

        // removed other assertion
        assertEquals("-292275055-05-16T16:47:05.000Z",test.plus(808).toString());
    }

    public void testDateTime_min_math_4_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        // removed other assertion

        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MIN_VALUE + 808,DateTimeZone.UTC),test.plusMillis(808));
    }

    public void testDateTime_min_math_5_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        // removed other assertion

        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("-292275055-05-16T16:47:05.000Z",test.plusMillis(808).toString());
    }

    public void testDateTime_min_math_6_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        // removed other assertion

        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MIN_VALUE + 1000,DateTimeZone.UTC),test.plusSeconds(1));
    }

    public void testDateTime_min_math_7_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        // removed other assertion

        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("-292275055-05-16T16:47:05.192Z",test.plusSeconds(1).toString());
    }

    public void testDateTime_min_math_8_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        // removed other assertion

        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MIN_VALUE + 60000,DateTimeZone.UTC),test.plusMinutes(1));
    }

    public void testDateTime_min_math_9_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        // removed other assertion

        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("-292275055-05-16T16:48:04.192Z",test.plusMinutes(1).toString());
    }

    public void testDateTime_min_math_10_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        // removed other assertion

        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        assertEquals(new DateTime(Long.MIN_VALUE + 80000,DateTimeZone.UTC),test.plusSeconds(80));
    }

    public void testDateTime_min_math_11_oe() throws Throwable {
        DateTime test = new DateTime(Long.MIN_VALUE);  // always in UTC
        // removed other assertion

        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals("-292275055-05-16T16:48:24.192Z",test.plusSeconds(80).toString());
    }

    public void testDateTime_min_fields_1_oe() {
        assertEquals(ACTUAL_MIN_YEAR,ISOChronology.getInstanceUTC().year().getMinimumValue()- 1);
    }

    public void testDateTime_min_fields_2_oe() {
        // removed other assertion
        // ensure previous year
        assertEquals("-292275053-01-01T00:00:00.000-00:01:15",new DateTime(-292275053,1,1,0,0).toString());
    }

    public void testDateTime_min_fields_3_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        assertEquals("-292275054-01-01T00:00:00.000-00:01:15",new DateTime(-292275054,1,1,0,0).toString());
    }

    public void testDateTime_min_fields_4_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MIN_YEAR, 12, 31, 23, 59, 59, 999);
        assertEquals("-292275055-12-31T23:59:59.999-00:01:15",a.toString());
    }

    public void testDateTime_min_fields_5_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MIN_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MIN_YEAR, 5, 17, 0, 0, 0, 0);
        assertEquals("-292275055-05-17T00:00:00.000-00:01:15",b.toString());
    }

    public void testDateTime_min_fields_6_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MIN_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MIN_YEAR, 5, 17, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 17, 0, 0, 0);
        assertEquals("-292275055-05-16T17:00:00.000-00:01:15",c.toString());
    }

    public void testDateTime_min_fields_7_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MIN_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MIN_YEAR, 5, 17, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 17, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 193);
        assertEquals("-292275055-05-16T16:47:04.193-00:01:15",d.toString());
    }

    public void testDateTime_min_fields_8_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MIN_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MIN_YEAR, 5, 17, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 17, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 193);
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 192);
        assertEquals(new DateTime(Long.MIN_VALUE),e);
    }

    public void testDateTime_min_fields_9_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MIN_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MIN_YEAR, 5, 17, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 17, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 193);
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 192);
        // removed other assertion
        assertEquals("-292275055-05-16T16:47:04.192Z",e.toString());
    }

    public void testDateTime_min_fields_10_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MIN_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MIN_YEAR, 5, 17, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 17, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 193);
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 192);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 191);
        assertEquals(new DateTime(Long.MIN_VALUE),f);
    }

    public void testDateTime_min_fields_11_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MIN_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MIN_YEAR, 5, 17, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 17, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 193);
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 192);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 191);
        // removed other assertion
        assertEquals("-292275055-05-16T16:47:04.192Z",f.toString());
    }

    public void testDateTime_min_fields_12_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MIN_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MIN_YEAR, 5, 17, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 17, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 193);
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 192);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 191);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime g = new DateTime(ACTUAL_MIN_YEAR, 1, 1, 0, 0, 0, 0);
        assertEquals(new DateTime(Long.MIN_VALUE),g);
    }

    public void testDateTime_min_fields_13_oe() {
        // removed other assertion
        // ensure previous year
        // removed other assertion
        // removed other assertion
        // permitted
        DateTime a = new DateTime(ACTUAL_MIN_YEAR, 12, 31, 23, 59, 59, 999);
        // removed other assertion
        // permitted
        DateTime b = new DateTime(ACTUAL_MIN_YEAR, 5, 17, 0, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime c = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 17, 0, 0, 0);
        // removed other assertion
        // permitted
        DateTime d = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 193);
        // removed other assertion
        // clamp to max
        DateTime e = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 192);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime f = new DateTime(ACTUAL_MIN_YEAR, 5, 16, 16, 47, 4, 191);
        // removed other assertion
        // removed other assertion
        // clamp to max
        DateTime g = new DateTime(ACTUAL_MIN_YEAR, 1, 1, 0, 0, 0, 0);
        // removed other assertion
        assertEquals("-292275055-05-16T16:47:04.192Z",g.toString());
    }

    public void testDateTime_min_long_1_oe() {
        assertEquals("-292275055-05-16T16:47:04.192-00:01:15",new DateTime(Long.MIN_VALUE + 75000).toString());
    }

    public void testDateTime_min_long_2_oe() {
        // removed other assertion
        assertEquals("-292275055-05-16T16:48:19.191Z",new DateTime(Long.MIN_VALUE + 74999).toString());
    }

    public void testDateTime_min_long_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("-292275055-05-16T16:48:04.192Z",new DateTime(Long.MIN_VALUE + 60000).toString());
    }

    public void testDateTime_min_long_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("-292275055-05-16T16:47:05.192Z",new DateTime(Long.MIN_VALUE + 1000).toString());
    }

    public void testDateTime_min_long_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("-292275055-05-16T16:47:04.193Z",new DateTime(Long.MIN_VALUE + 1).toString());
    }

    public void testDateTime_min_long_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("-292275055-05-16T16:47:04.192Z",new DateTime(Long.MIN_VALUE).toString());
    }

    public void testPrintParseMin_1_oe() {
        DateTime test1 = new DateTime(Long.MIN_VALUE);
        assertEquals(test1,DateTime.parse(test1.toString()));
    }

    public void testPrintParseMin_2_oe() {
        DateTime test1 = new DateTime(Long.MIN_VALUE);
        // removed other assertion
        DateTime test2 = new DateTime(Long.valueOf(Long.MIN_VALUE));
        assertEquals(test2,DateTime.parse(test2.toString()));
    }

    public void testPrintParseMin_3_oe() {
        DateTime test1 = new DateTime(Long.MIN_VALUE);
        // removed other assertion
        DateTime test2 = new DateTime(Long.valueOf(Long.MIN_VALUE));
        // removed other assertion
        assertEquals(test2,test1);
    }

    public void testDateTime_aroundZero_1_oe() {
        DateTime base = new DateTime(1970, 1, 1, 1, 2, DateTimeZone.UTC);
        assertEquals(62 * 60000L,base.getMillis());
    }

    public void testDateTime_aroundZero_2_oe() {
        DateTime base = new DateTime(1970, 1, 1, 1, 2, DateTimeZone.UTC);
        // removed other assertion
        for (int i = -23; i <= 23; i++) {
            DateTime dt = new DateTime(1970, 1, 1, 1, 2, DateTimeZone.forOffsetHours(i));
            assertEquals(base.getMillis()- i * 3600000L,dt.getMillis());
    }
    }

}
