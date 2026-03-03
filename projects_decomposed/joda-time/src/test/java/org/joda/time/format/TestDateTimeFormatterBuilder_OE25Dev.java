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
package org.joda.time.format;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

/**
 * This class is a Junit unit test for DateTimeFormatterBuilder.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 */
public class TestDateTimeFormatterBuilder_OE25Dev extends TestCase {

    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
    private static final DateTimeZone NEW_YORK = DateTimeZone.forID("America/New_York");
    private static final DateTimeZone LOS_ANGELES = DateTimeZone.forID("America/Los_Angeles");
    private static final DateTimeZone OFFSET_0200 = DateTimeZone.forID("+02:00");
    private static final DateTimeZone OFFSET_023012 = DateTimeZone.forID("+02:30:12");

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateTimeFormatterBuilder_OE25Dev.class);
    }

    public TestDateTimeFormatterBuilder_OE25Dev(String name) {
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

    public void test_append_nullPrinter() {
        try {
            DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
            bld2.append((DateTimePrinter) null);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    public void test_append_nullParser() {
        try {
            DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
            bld2.append((DateTimeParser) null);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    public void test_append_Printer_nullParser() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimePrinter p = bld.toPrinter();
        
        try {
            DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
            bld2.append(p, (DateTimeParser) null);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    public void test_append_nullPrinter_Parser() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimeParser p = bld.toParser();
        
        try {
            DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
            bld2.append((DateTimePrinter) null, p);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    public void test_appendOptional_nullParser() {
        try {
            DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
            bld2.appendOptional((DateTimeParser) null);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void test_appendTimeZoneOffset_invalidText() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendTimeZoneOffset("Z", true, 1, 1);
        DateTimeFormatter f = bld.toFormatter();
        try {
            f.parseDateTime("Nonsense");
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    public void test_appendTimeZoneOffset_zeroMinInvalid() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        try {
            bld.appendTimeZoneOffset("Z", true, 0, 2);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    private static void assertPrint(String expected, DateTimeFormatter f, DateTime dt) {
        assertEquals(expected,f.print(dt));
        StringWriter out = new StringWriter();
        try {
            f.printTo(out, dt);
        } catch (IOException ex) {
            AssertionFailedError failure = new AssertionFailedError();
            failure.initCause(ex);
            throw failure;
        }
        assertEquals(expected,out.toString());
    }

    public void test_toFormatter_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        try {
            bld.toFormatter();
        } catch (UnsupportedOperationException ex) {}
        bld.appendLiteral('X');
        assertNotNull(bld.toFormatter());
    }

    public void test_toPrinter_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        try {
            bld.toPrinter();
        } catch (UnsupportedOperationException ex) {}
        bld.appendLiteral('X');
        assertNotNull(bld.toPrinter());
    }

    public void test_toParser_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        try {
            bld.toParser();
        } catch (UnsupportedOperationException ex) {}
        bld.appendLiteral('X');
        assertNotNull(bld.toParser());
    }

    public void test_canBuildFormatter_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        assertEquals(false,bld.canBuildFormatter());
    }

    public void test_canBuildFormatter_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('X');
        assertEquals(true,bld.canBuildFormatter());
    }

    public void test_canBuildPrinter_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        assertEquals(false,bld.canBuildPrinter());
    }

    public void test_canBuildPrinter_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('X');
        assertEquals(true,bld.canBuildPrinter());
    }

    public void test_canBuildParser_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        assertEquals(false,bld.canBuildParser());
    }

    public void test_canBuildParser_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('X');
        assertEquals(true,bld.canBuildParser());
    }

    public void test_append_Formatter_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimeFormatter f = bld.toFormatter();
        
        DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
        bld2.appendLiteral('X');
        bld2.append(f);
        bld2.appendLiteral('Z');
        assertEquals("XYZ",bld2.toFormatter().print(0L));
    }

    public void test_append_Printer_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimePrinter p = bld.toPrinter();
        
        DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
        bld2.appendLiteral('X');
        bld2.append(p);
        bld2.appendLiteral('Z');
        DateTimeFormatter f = bld2.toFormatter();
        assertEquals(true,f.isPrinter());
    }

    public void test_append_Printer_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimePrinter p = bld.toPrinter();
        
        DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
        bld2.appendLiteral('X');
        bld2.append(p);
        bld2.appendLiteral('Z');
        DateTimeFormatter f = bld2.toFormatter();
        assertEquals(false,f.isParser());
    }

    public void test_append_Printer_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimePrinter p = bld.toPrinter();
        
        DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
        bld2.appendLiteral('X');
        bld2.append(p);
        bld2.appendLiteral('Z');
        DateTimeFormatter f = bld2.toFormatter();
        assertEquals("XYZ",f.print(0L));
    }

    public void test_append_Parser_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimeParser p = bld.toParser();
        
        DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
        bld2.appendLiteral('X');
        bld2.append(p);
        bld2.appendLiteral('Z');
        DateTimeFormatter f = bld2.toFormatter();
        assertEquals(false,f.isPrinter());
    }

    public void test_append_Parser_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimeParser p = bld.toParser();
        
        DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
        bld2.appendLiteral('X');
        bld2.append(p);
        bld2.appendLiteral('Z');
        DateTimeFormatter f = bld2.toFormatter();
        assertEquals(true,f.isParser());
    }

    public void test_append_Parser_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimeParser p = bld.toParser();
        
        DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
        bld2.appendLiteral('X');
        bld2.append(p);
        bld2.appendLiteral('Z');
        DateTimeFormatter f = bld2.toFormatter();
        assertEquals(0,f.withZoneUTC().parseMillis("XYZ"));
    }

    public void test_appendOptional_Parser_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimeParser p = bld.toParser();
        
        DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
        bld2.appendLiteral('X');
        bld2.appendOptional(p);
        bld2.appendLiteral('Z');
        DateTimeFormatter f = bld2.toFormatter();
        assertEquals(false,f.isPrinter());
    }

    public void test_appendOptional_Parser_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimeParser p = bld.toParser();
        
        DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
        bld2.appendLiteral('X');
        bld2.appendOptional(p);
        bld2.appendLiteral('Z');
        DateTimeFormatter f = bld2.toFormatter();
        assertEquals(true,f.isParser());
    }

    public void test_appendOptional_Parser_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendLiteral('Y');
        DateTimeParser p = bld.toParser();
        
        DateTimeFormatterBuilder bld2 = new DateTimeFormatterBuilder();
        bld2.appendLiteral('X');
        bld2.appendOptional(p);
        bld2.appendLiteral('Z');
        DateTimeFormatter f = bld2.toFormatter();
        assertEquals(0,f.withZoneUTC().parseMillis("XYZ"));
    }

    public void test_appendFixedDecimal_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("2007",f.print(new DateTime("2007-01-01")));
    }

    public void test_appendFixedDecimal_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("0123",f.print(new DateTime("123-01-01")));
    }

    public void test_appendFixedDecimal_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("0001",f.print(new DateTime("1-2-3")));
    }

    public void test_appendFixedDecimal_4_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("99999",f.print(new DateTime("99999-2-3")));
    }

    public void test_appendFixedDecimal_5_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("-0099",f.print(new DateTime("-99-2-3")));
    }

    public void test_appendFixedDecimal_6_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("0000",f.print(new DateTime("0-2-3")));
    }

    public void test_appendFixedDecimal_7_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();


        assertEquals(2001,f.parseDateTime("2001").getYear());
    }

    public void test_appendFixedDecimal_11_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();


        try {
            f.parseDateTime("-2001");
        } catch (IllegalArgumentException e) {
        }
        try {
            f.parseDateTime("200");
        } catch (IllegalArgumentException e) {
        }
        try {
            f.parseDateTime("20016");
        } catch (IllegalArgumentException e) {
        }

        bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2);
        bld.appendLiteral(':');
        bld.appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2);
        bld.appendLiteral(':');
        bld.appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2);
        f = bld.toFormatter();

        assertEquals("01:02:34",f.print(new DateTime("T1:2:34")));
    }

    public void test_appendFixedDecimal_12_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();


        try {
            f.parseDateTime("-2001");
        } catch (IllegalArgumentException e) {
        }
        try {
            f.parseDateTime("200");
        } catch (IllegalArgumentException e) {
        }
        try {
            f.parseDateTime("20016");
        } catch (IllegalArgumentException e) {
        }

        bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2);
        bld.appendLiteral(':');
        bld.appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2);
        bld.appendLiteral(':');
        bld.appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2);
        f = bld.toFormatter();


        DateTime dt = f.parseDateTime("01:02:34");
        assertEquals(1,dt.getHourOfDay());
    }

    public void test_appendFixedDecimal_13_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();


        try {
            f.parseDateTime("-2001");
        } catch (IllegalArgumentException e) {
        }
        try {
            f.parseDateTime("200");
        } catch (IllegalArgumentException e) {
        }
        try {
            f.parseDateTime("20016");
        } catch (IllegalArgumentException e) {
        }

        bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2);
        bld.appendLiteral(':');
        bld.appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2);
        bld.appendLiteral(':');
        bld.appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2);
        f = bld.toFormatter();


        DateTime dt = f.parseDateTime("01:02:34");
        assertEquals(2,dt.getMinuteOfHour());
    }

    public void test_appendFixedDecimal_14_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();


        try {
            f.parseDateTime("-2001");
        } catch (IllegalArgumentException e) {
        }
        try {
            f.parseDateTime("200");
        } catch (IllegalArgumentException e) {
        }
        try {
            f.parseDateTime("20016");
        } catch (IllegalArgumentException e) {
        }

        bld = new DateTimeFormatterBuilder();
        bld.appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2);
        bld.appendLiteral(':');
        bld.appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2);
        bld.appendLiteral(':');
        bld.appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2);
        f = bld.toFormatter();


        DateTime dt = f.parseDateTime("01:02:34");
        assertEquals(34,dt.getSecondOfMinute());
    }

    public void test_appendFixedSignedDecimal_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedSignedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("2007",f.print(new DateTime("2007-01-01")));
    }

    public void test_appendFixedSignedDecimal_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedSignedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("0123",f.print(new DateTime("123-01-01")));
    }

    public void test_appendFixedSignedDecimal_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedSignedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("0001",f.print(new DateTime("1-2-3")));
    }

    public void test_appendFixedSignedDecimal_4_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedSignedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("99999",f.print(new DateTime("99999-2-3")));
    }

    public void test_appendFixedSignedDecimal_5_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedSignedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("-0099",f.print(new DateTime("-99-2-3")));
    }

    public void test_appendFixedSignedDecimal_6_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedSignedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();

        assertEquals("0000",f.print(new DateTime("0-2-3")));
    }

    public void test_appendFixedSignedDecimal_7_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedSignedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();


        assertEquals(2001,f.parseDateTime("2001").getYear());
    }

    public void test_appendFixedSignedDecimal_8_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedSignedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();


        assertEquals(-2001,f.parseDateTime("-2001").getYear());
    }

    public void test_appendFixedSignedDecimal_9_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendFixedSignedDecimal(DateTimeFieldType.year(), 4);
        DateTimeFormatter f = bld.toFormatter();


        assertEquals(2001,f.parseDateTime("+2001").getYear());
    }

    public void test_appendTimeZoneOffset_parse_1_oe() {
        for (int i = 1; i <= 4; i++) {
            for (int j = i; j <= 4; j++) {
                DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
                bld.appendTimeZoneOffset("Z", true, i, j);
                DateTimeFormatter f = bld.toFormatter();
                assertEquals(OFFSET_0200,f.withOffsetParsed().parseDateTime("+02").getZone());
    }
    }
    }

    public void test_appendTimeZoneOffset_parse_2_oe() {
        for (int i = 1; i <= 4; i++) {
            for (int j = i; j <= 4; j++) {
                DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
                bld.appendTimeZoneOffset("Z", true, i, j);
                DateTimeFormatter f = bld.toFormatter();
                assertEquals(OFFSET_0200,f.withOffsetParsed().parseDateTime("+02:00").getZone());
    }
    }
    }

    public void test_appendTimeZoneOffset_parse_3_oe() {
        for (int i = 1; i <= 4; i++) {
            for (int j = i; j <= 4; j++) {
                DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
                bld.appendTimeZoneOffset("Z", true, i, j);
                DateTimeFormatter f = bld.toFormatter();
                assertEquals(OFFSET_0200,f.withOffsetParsed().parseDateTime("+02:00:00").getZone());
    }
    }
    }

    public void test_appendTimeZoneOffset_parse_4_oe() {
        for (int i = 1; i <= 4; i++) {
            for (int j = i; j <= 4; j++) {
                DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
                bld.appendTimeZoneOffset("Z", true, i, j);
                DateTimeFormatter f = bld.toFormatter();
                assertEquals(OFFSET_0200,f.withOffsetParsed().parseDateTime("+02:00:00.000").getZone());
    }
    }
    }

    public void test_appendTimeZoneId_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        assertEquals("Asia/Tokyo",f.print(new DateTime(2007,3,4,0,0,0,TOKYO)));
    }

    public void test_appendTimeZoneId_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        bld.appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        assertEquals(TOKYO,f.parseDateTime("Asia/Tokyo").getZone());
    }

    public void test_printParseZoneTokyo_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals("2007-03-04 12:30 Asia/Tokyo",f.print(dt));
    }

    public void test_printParseZoneTokyo_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 Asia/Tokyo"));
    }

    public void test_printParseZoneParis_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, PARIS);
        assertEquals("2007-03-04 12:30 Europe/Paris",f.print(dt));
    }

    public void test_printParseZoneParis_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, PARIS);
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 Europe/Paris"));
    }

    public void test_printParseZoneParis_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, PARIS);
        assertEquals(dt,f.withOffsetParsed().parseDateTime("2007-03-04 12:30 Europe/Paris"));
    }

    public void test_printParseZoneDawson_1_oe() {  // clashes with shorter Dawson
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("America/Dawson"));
        assertEquals("2007-03-04 12:30 America/Dawson",f.print(dt));
    }

    public void test_printParseZoneDawson_2_oe() {  // clashes with shorter Dawson
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("America/Dawson"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 America/Dawson"));
    }

    public void test_printParseZoneDawson_suffix_1_oe() {  // clashes with shorter Dawson
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId().appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("America/Dawson"));
        assertEquals("2007-03-04 12:30 America/Dawson]",f.print(dt));
    }

    public void test_printParseZoneDawson_suffix_2_oe() {  // clashes with shorter Dawson
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId().appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("America/Dawson"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 America/Dawson]"));
    }

    public void test_printParseZoneDawsonCreek_1_oe() {  // clashes with shorter Dawson
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("America/Dawson_Creek"));
        assertEquals("2007-03-04 12:30 America/Dawson_Creek",f.print(dt));
    }

    public void test_printParseZoneDawsonCreek_2_oe() {  // clashes with shorter Dawson
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("America/Dawson_Creek"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 America/Dawson_Creek"));
    }

    public void test_printParseZoneDawsonCreek_suffix_1_oe() {  // clashes with shorter Dawson
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId().appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("America/Dawson_Creek"));
        assertEquals("2007-03-04 12:30 America/Dawson_Creek]",f.print(dt));
    }

    public void test_printParseZoneDawsonCreek_suffix_2_oe() {  // clashes with shorter Dawson
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId().appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("America/Dawson_Creek"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 America/Dawson_Creek]"));
    }

    public void test_printParseZoneEtcGMT_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ");
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT"));
        assertEquals("2007-03-04 12:30 Etc/GMT",f.print(dt));
    }

    public void test_printParseZoneEtcGMT_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ");
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 Etc/GMT"));
    }

    public void test_printParseZoneEtcGMT_suffix_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ").appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT"));
        assertEquals("2007-03-04 12:30 Etc/GMT]",f.print(dt));
    }

    public void test_printParseZoneEtcGMT_suffix_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ").appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 Etc/GMT]"));
    }

    public void test_printParseZoneGMT_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ");
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("GMT"));
        assertEquals("2007-03-04 12:30 Etc/GMT",f.print(dt));
    }

    public void test_printParseZoneGMT_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ");
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("GMT"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 GMT"));
    }

    public void test_printParseZoneGMT_suffix_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ").appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("GMT"));
        assertEquals("2007-03-04 12:30 Etc/GMT]",f.print(dt));
    }

    public void test_printParseZoneGMT_suffix_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ").appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("GMT"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 GMT]"));
    }

    public void test_printParseZoneEtcGMT1_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ");
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT+1"));
        assertEquals("2007-03-04 12:30 Etc/GMT+1",f.print(dt));
    }

    public void test_printParseZoneEtcGMT1_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ");
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT+1"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 Etc/GMT+1"));
    }

    public void test_printParseZoneEtcGMT1_suffix_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ").appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT+1"));
        assertEquals("2007-03-04 12:30 Etc/GMT+1]",f.print(dt));
    }

    public void test_printParseZoneEtcGMT1_suffix_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ").appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT+1"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 Etc/GMT+1]"));
    }

    public void test_printParseZoneEtcGMT10_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ");
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT+10"));
        assertEquals("2007-03-04 12:30 Etc/GMT+10",f.print(dt));
    }

    public void test_printParseZoneEtcGMT10_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ");
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT+10"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 Etc/GMT+10"));
    }

    public void test_printParseZoneEtcGMT10_suffix_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ").appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT+10"));
        assertEquals("2007-03-04 12:30 Etc/GMT+10]",f.print(dt));
    }

    public void test_printParseZoneEtcGMT10_suffix_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ").appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("Etc/GMT+10"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 Etc/GMT+10]"));
    }

    public void test_printParseZoneMET_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ");
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("MET"));
        assertEquals("2007-03-04 12:30 MET",f.print(dt));
    }

    public void test_printParseZoneMET_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ");
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("MET"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 MET"));
    }

    public void test_printParseZoneMET_suffix_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ").appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("MET"));
        assertEquals("2007-03-04 12:30 MET]",f.print(dt));
    }

    public void test_printParseZoneMET_suffix_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ZZZ").appendLiteral(']');
        DateTimeFormatter f = bld.toFormatter();

        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("MET"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 MET]"));
    }

    public void test_printParseZoneBahiaBanderas_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("America/Bahia_Banderas"));
        assertEquals("2007-03-04 12:30 America/Bahia_Banderas",f.print(dt));
    }

    public void test_printParseZoneBahiaBanderas_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forID("America/Bahia_Banderas"));
        assertEquals(dt,f.parseDateTime("2007-03-04 12:30 America/Bahia_Banderas"));
    }

    public void test_printParseOffset_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals("2007-03-04 12:30 +09:00",f.print(dt));
    }

    public void test_printParseOffset_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals(dt.withZone(DateTimeZone.getDefault()),f.parseDateTime("2007-03-04 12:30 +09:00"));
    }

    public void test_printParseOffset_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals(dt,f.withZone(TOKYO).parseDateTime("2007-03-04 12:30 +09:00"));
    }

    public void test_printParseOffset_4_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals(dt.withZone(DateTimeZone.forOffsetHours(9)),f.withOffsetParsed().parseDateTime("2007-03-04 12:30 +09:00"));
    }

    public void test_printParseOffsetAndZone_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals("2007-03-04 12:30 +09:00 Asia/Tokyo",f.print(dt));
    }

    public void test_printParseOffsetAndZone_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals(dt,f.withZone(TOKYO).parseDateTime("2007-03-04 12:30 +09:00 Asia/Tokyo"));
    }

    public void test_printParseOffsetAndZone_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals(dt.withZone(PARIS),f.withZone(PARIS).parseDateTime("2007-03-04 12:30 +09:00 Asia/Tokyo"));
    }

    public void test_printParseOffsetAndZone_4_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals(dt.withZone(DateTimeZone.forOffsetHours(9)),f.withOffsetParsed().parseDateTime("2007-03-04 12:30 +09:00 Asia/Tokyo"));
    }

    public void test_parseWrongOffset_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime expected = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forOffsetHours(7));
        assertEquals(expected.withZone(TOKYO),f.withZone(TOKYO).parseDateTime("2007-03-04 12:30 +07:00"));
    }

    public void test_parseWrongOffset_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime expected = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forOffsetHours(7));
        assertEquals(expected,f.withOffsetParsed().parseDateTime("2007-03-04 12:30 +07:00"));
    }

    public void test_parseWrongOffset_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime expected = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forOffsetHours(7));
        assertEquals(expected.withZone(DateTimeZone.getDefault()),f.parseDateTime("2007-03-04 12:30 +07:00"));
    }

    public void test_parseWrongOffsetAndZone_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime expected = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forOffsetHours(7));
        assertEquals(expected.withZone(TOKYO),f.parseDateTime("2007-03-04 12:30 +07:00 Asia/Tokyo"));
    }

    public void test_parseWrongOffsetAndZone_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime expected = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forOffsetHours(7));
        assertEquals(expected.withZone(TOKYO),f.withZone(TOKYO).parseDateTime("2007-03-04 12:30 +07:00 Asia/Tokyo"));
    }

    public void test_parseWrongOffsetAndZone_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime expected = new DateTime(2007, 3, 4, 12, 30, 0, DateTimeZone.forOffsetHours(7));
        assertEquals(expected,f.withOffsetParsed().parseDateTime("2007-03-04 12:30 +07:00 Asia/Tokyo"));
    }

    public void test_localPrintParseZoneTokyo_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals("2007-03-04 12:30 Asia/Tokyo",f.print(dt));
    }

    public void test_localPrintParseZoneTokyo_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        
        LocalDateTime expected = new LocalDateTime(2007, 3, 4, 12, 30);
        assertEquals(expected,f.parseLocalDateTime("2007-03-04 12:30 Asia/Tokyo"));
    }

    public void test_localPrintParseOffset_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals("2007-03-04 12:30 +09:00",f.print(dt));
    }

    public void test_localPrintParseOffset_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        
        LocalDateTime expected = new LocalDateTime(2007, 3, 4, 12, 30);
        assertEquals(expected,f.parseLocalDateTime("2007-03-04 12:30 +09:00"));
    }

    public void test_localPrintParseOffset_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        
        LocalDateTime expected = new LocalDateTime(2007, 3, 4, 12, 30);
        assertEquals(expected,f.withZone(TOKYO).parseLocalDateTime("2007-03-04 12:30 +09:00"));
    }

    public void test_localPrintParseOffset_4_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2);
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        
        LocalDateTime expected = new LocalDateTime(2007, 3, 4, 12, 30);
        assertEquals(expected,f.withOffsetParsed().parseLocalDateTime("2007-03-04 12:30 +09:00"));
    }

    public void test_localPrintParseOffsetAndZone_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        assertEquals("2007-03-04 12:30 +09:00 Asia/Tokyo",f.print(dt));
    }

    public void test_localPrintParseOffsetAndZone_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        
        LocalDateTime expected = new LocalDateTime(2007, 3, 4, 12, 30);
        assertEquals(expected,f.withZone(TOKYO).parseLocalDateTime("2007-03-04 12:30 +09:00 Asia/Tokyo"));
    }

    public void test_localPrintParseOffsetAndZone_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        DateTime dt = new DateTime(2007, 3, 4, 12, 30, 0, TOKYO);
        
        LocalDateTime expected = new LocalDateTime(2007, 3, 4, 12, 30);
        assertEquals(expected,f.withZone(PARIS).parseLocalDateTime("2007-03-04 12:30 +09:00 Asia/Tokyo"));
    }

    public void test_localParseWrongOffsetAndZone_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        LocalDateTime expected = new LocalDateTime(2007, 3, 4, 12, 30);
        assertEquals(expected,f.parseLocalDateTime("2007-03-04 12:30 +07:00 Asia/Tokyo"));
    }

    public void test_localParseWrongOffsetAndZone_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        LocalDateTime expected = new LocalDateTime(2007, 3, 4, 12, 30);
        assertEquals(expected,f.withZone(TOKYO).parseLocalDateTime("2007-03-04 12:30 +07:00 Asia/Tokyo"));
    }

    public void test_localParseWrongOffsetAndZone_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneOffset("Z", true, 2, 2).appendLiteral(' ').appendTimeZoneId();
        DateTimeFormatter f = bld.toFormatter();
        
        LocalDateTime expected = new LocalDateTime(2007, 3, 4, 12, 30);
        assertEquals(expected,f.withOffsetParsed().parseLocalDateTime("2007-03-04 12:30 +07:00 Asia/Tokyo"));
    }

    public void test_printParseShortName_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName();
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        assertEquals(true,f.isPrinter());
    }

    public void test_printParseShortName_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName();
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        assertEquals(false,f.isParser());
    }

    public void test_printParseShortName_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName();
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        assertEquals("2011-01-04 12:30 GMT",f.print(dt1));
    }

    public void test_printParseShortName_4_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName();
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, LONDON);
        assertEquals("2011-07-04 12:30 BST",f.print(dt2));
    }

    public void test_printParseShortNameWithLookup_1_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("GMT", LONDON);
        lookup.put("BST", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        assertEquals(true,f.isPrinter());
    }

    public void test_printParseShortNameWithLookup_2_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("GMT", LONDON);
        lookup.put("BST", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        assertEquals(true,f.isParser());
    }

    public void test_printParseShortNameWithLookup_3_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("GMT", LONDON);
        lookup.put("BST", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        assertEquals("2011-01-04 12:30 GMT",f.print(dt1));
    }

    public void test_printParseShortNameWithLookup_4_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("GMT", LONDON);
        lookup.put("BST", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, LONDON);
        assertEquals("2011-07-04 12:30 BST",f.print(dt2));
    }

    public void test_printParseShortNameWithLookup_5_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("GMT", LONDON);
        lookup.put("BST", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, LONDON);
        
        assertEquals(dt1,f.parseDateTime("2011-01-04 12:30 GMT"));
    }

    public void test_printParseShortNameWithLookup_6_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("GMT", LONDON);
        lookup.put("BST", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, LONDON);
        
        assertEquals(dt2,f.parseDateTime("2011-07-04 12:30 BST"));
    }

    public void test_printParseShortNameWithAutoLookup_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        assertEquals(true,f.isPrinter());
    }

    public void test_printParseShortNameWithAutoLookup_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        assertEquals(true,f.isParser());
    }

    public void test_printParseShortNameWithAutoLookup_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        assertEquals("2011-01-04 12:30 EST",f.print(dt1));
    }

    public void test_printParseShortNameWithAutoLookup_4_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, NEW_YORK);
        assertEquals("2011-07-04 12:30 EDT",f.print(dt2));
    }

    public void test_printParseShortNameWithAutoLookup_5_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, NEW_YORK);
        DateTime dt3 = new DateTime(2011, 1, 4, 12, 30, 0, LOS_ANGELES);
        assertEquals("2011-01-04 12:30 PST",f.print(dt3));
    }

    public void test_printParseShortNameWithAutoLookup_6_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, NEW_YORK);
        DateTime dt3 = new DateTime(2011, 1, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt4 = new DateTime(2011, 7, 4, 12, 30, 0, LOS_ANGELES);
        assertEquals("2011-07-04 12:30 PDT",f.print(dt4));
    }

    public void test_printParseShortNameWithAutoLookup_7_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, NEW_YORK);
        DateTime dt3 = new DateTime(2011, 1, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt4 = new DateTime(2011, 7, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt5 = new DateTime(2011, 7, 4, 12, 30, 0, DateTimeZone.UTC);
        assertEquals("2011-07-04 12:30 UTC",f.print(dt5));
    }

    public void test_printParseShortNameWithAutoLookup_8_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, NEW_YORK);
        DateTime dt3 = new DateTime(2011, 1, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt4 = new DateTime(2011, 7, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt5 = new DateTime(2011, 7, 4, 12, 30, 0, DateTimeZone.UTC);
        
        assertEquals(dt1.getZone()+ " " + f.parseDateTime("2011-01-04 12:30 EST").getZone(),dt1,f.parseDateTime("2011-01-04 12:30 EST"));
    }

    public void test_printParseShortNameWithAutoLookup_9_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, NEW_YORK);
        DateTime dt3 = new DateTime(2011, 1, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt4 = new DateTime(2011, 7, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt5 = new DateTime(2011, 7, 4, 12, 30, 0, DateTimeZone.UTC);
        
        assertEquals(dt2,f.parseDateTime("2011-07-04 12:30 EDT"));
    }

    public void test_printParseShortNameWithAutoLookup_10_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, NEW_YORK);
        DateTime dt3 = new DateTime(2011, 1, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt4 = new DateTime(2011, 7, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt5 = new DateTime(2011, 7, 4, 12, 30, 0, DateTimeZone.UTC);
        
        assertEquals(dt3,f.parseDateTime("2011-01-04 12:30 PST"));
    }

    public void test_printParseShortNameWithAutoLookup_11_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, NEW_YORK);
        DateTime dt3 = new DateTime(2011, 1, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt4 = new DateTime(2011, 7, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt5 = new DateTime(2011, 7, 4, 12, 30, 0, DateTimeZone.UTC);
        
        assertEquals(dt4,f.parseDateTime("2011-07-04 12:30 PDT"));
    }

    public void test_printParseShortNameWithAutoLookup_12_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, NEW_YORK);
        DateTime dt3 = new DateTime(2011, 1, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt4 = new DateTime(2011, 7, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt5 = new DateTime(2011, 7, 4, 12, 30, 0, DateTimeZone.UTC);
        
        assertEquals(dt5,f.parseDateTime("2011-07-04 12:30 UT"));
    }

    public void test_printParseShortNameWithAutoLookup_13_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneShortName(null);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, NEW_YORK);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, NEW_YORK);
        DateTime dt3 = new DateTime(2011, 1, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt4 = new DateTime(2011, 7, 4, 12, 30, 0, LOS_ANGELES);
        DateTime dt5 = new DateTime(2011, 7, 4, 12, 30, 0, DateTimeZone.UTC);
        
        assertEquals(dt5,f.parseDateTime("2011-07-04 12:30 UTC"));
    }

    public void test_printParseLongName_1_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneName();
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        assertEquals(true,f.isPrinter());
    }

    public void test_printParseLongName_2_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneName();
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        assertEquals(false,f.isParser());
    }

    public void test_printParseLongName_3_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneName();
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        assertEquals("2011-01-04 12:30 Greenwich Mean Time",f.print(dt1));
    }

    public void test_printParseLongName_4_oe() {
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneName();
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, LONDON);
        assertEquals("2011-07-04 12:30 British Summer Time",f.print(dt2));
    }

    public void test_printParseLongNameWithLookup_1_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("Greenwich Mean Time", LONDON);
        lookup.put("British Summer Time", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        assertEquals(true,f.isPrinter());
    }

    public void test_printParseLongNameWithLookup_2_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("Greenwich Mean Time", LONDON);
        lookup.put("British Summer Time", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        assertEquals(true,f.isParser());
    }

    public void test_printParseLongNameWithLookup_3_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("Greenwich Mean Time", LONDON);
        lookup.put("British Summer Time", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        assertEquals("2011-01-04 12:30 Greenwich Mean Time",f.print(dt1));
    }

    public void test_printParseLongNameWithLookup_4_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("Greenwich Mean Time", LONDON);
        lookup.put("British Summer Time", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, LONDON);
        assertEquals("2011-07-04 12:30 British Summer Time",f.print(dt2));
    }

    public void test_printParseLongNameWithLookup_5_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("Greenwich Mean Time", LONDON);
        lookup.put("British Summer Time", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, LONDON);
        
        assertEquals(dt1,f.parseDateTime("2011-01-04 12:30 Greenwich Mean Time"));
    }

    public void test_printParseLongNameWithLookup_6_oe() {
        Map<String, DateTimeZone> lookup = new LinkedHashMap<String, DateTimeZone>();
        lookup.put("Greenwich Mean Time", LONDON);
        lookup.put("British Summer Time", LONDON);
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm ").appendTimeZoneName(lookup);
        DateTimeFormatter f = bld.toFormatter().withLocale(Locale.ENGLISH);
        
        DateTime dt1 = new DateTime(2011, 1, 4, 12, 30, 0, LONDON);
        DateTime dt2 = new DateTime(2011, 7, 4, 12, 30, 0, LONDON);
        
        assertEquals(dt2,f.parseDateTime("2011-07-04 12:30 British Summer Time"));
    }

    public void test_appendTimeZoneOffset_print_min1max1_1_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 1).toFormatter();
                final String expected0 = "+02";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min1max1_1_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 1).toFormatter();
                final String expected0 = "+02";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min1max1_2_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 1).toFormatter();
                final String expected0 = "+02";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min1max1_2_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 1).toFormatter();
                final String expected0 = "+02";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min1max2_1_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 2).toFormatter();
                final String expected0 = "+02";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min1max2_1_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 2).toFormatter();
                final String expected0 = "+02";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min1max2_2_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 2).toFormatter();
                final String expected0 = "+02:30";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min1max2_2_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 2).toFormatter();
                final String expected0 = "+02:30";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min1max3_1_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 3).toFormatter();
                final String expected0 = "+02";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min1max3_1_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 3).toFormatter();
                final String expected0 = "+02";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min1max3_2_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 3).toFormatter();
                final String expected0 = "+02:30:12";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min1max3_2_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 1, 3).toFormatter();
                final String expected0 = "+02:30:12";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min2max2_1_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 2).toFormatter();
                final String expected0 = "+02:00";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min2max2_1_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 2).toFormatter();
                final String expected0 = "+02:00";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min2max2_2_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 2).toFormatter();
                final String expected0 = "+02:30";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min2max2_2_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 2).toFormatter();
                final String expected0 = "+02:30";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min2max3_1_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 3).toFormatter();
                final String expected0 = "+02:00";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min2max3_1_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 3).toFormatter();
                final String expected0 = "+02:00";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min2max3_2_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 3).toFormatter();
                final String expected0 = "+02:30:12";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min2max3_2_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 3).toFormatter();
                final String expected0 = "+02:30:12";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min3max3_1_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 3, 3).toFormatter();
                final String expected0 = "+02:00:00";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min3max3_1_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 3, 3).toFormatter();
                final String expected0 = "+02:00:00";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_0200);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

    public void test_appendTimeZoneOffset_print_min3max3_2_oe_1_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 3, 3).toFormatter();
                final String expected0 = "+02:30:12";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
        assertEquals(expected0,f0.print(dt0));
    }

    public void test_appendTimeZoneOffset_print_min3max3_2_oe_2_oe() throws IOException {
        DateTimeFormatter f = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 3, 3).toFormatter();
                final String expected0 = "+02:30:12";
        final DateTimeFormatter f0 = f;
        final DateTime dt0 = new DateTime(2007,3,4,0,0,0,OFFSET_023012);
                StringWriter out0 = new StringWriter();
                try {
                    f0.printTo(out0, dt0);
                } catch (IOException ex0) {
                    AssertionFailedError failure0 = new AssertionFailedError();
                    failure0.initCause(ex0);
                    throw failure0;
                }
                assertEquals(expected0,out0.toString());
    }

}
