/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

public class FastDateParser_MoreOrLessTest_OE25Dev {

    private static final TimeZone NEW_YORK = TimeZone.getTimeZone("America/New_York");

    @Test
    public void testInputHasLessCharacters_1_oe() {
        final FastDateParser parser = new FastDateParser("MM/dd/yyy", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        assertNull(parser.parse("03/23", parsePosition));
    }

    @Test
    public void testInputHasMoreCharacters_1_oe() {
        final FastDateParser parser = new FastDateParser("MM/dd", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse("3/23/61", parsePosition);
        assertEquals(4, parsePosition.getIndex());
    }

    @Test
    public void testInputHasMoreCharacters_2_oe() {
        final FastDateParser parser = new FastDateParser("MM/dd", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse("3/23/61", parsePosition);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertEquals(2, calendar.get(Calendar.MONTH));
    }

    @Test
    public void testInputHasMoreCharacters_3_oe() {
        final FastDateParser parser = new FastDateParser("MM/dd", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse("3/23/61", parsePosition);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertEquals(23, calendar.get(Calendar.DATE));
    }

    @Test
    public void testInputHasPrecedingCharacters_1_oe() {
        final FastDateParser parser = new FastDateParser("MM/dd", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse("A 3/23/61", parsePosition);
        assertNull(date);
    }

    @Test
    public void testInputHasPrecedingCharacters_2_oe() {
        final FastDateParser parser = new FastDateParser("MM/dd", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse("A 3/23/61", parsePosition);
        assertEquals(0, parsePosition.getIndex());
    }

    @Test
    public void testInputHasPrecedingCharacters_3_oe() {
        final FastDateParser parser = new FastDateParser("MM/dd", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse("A 3/23/61", parsePosition);
        assertEquals(0, parsePosition.getErrorIndex());
    }

    @Test
    public void testInputHasWhitespace_1_oe() {
        final FastDateParser parser = new FastDateParser("M/d/y", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse(" 3/ 23/ 1961", parsePosition);
        assertEquals(12, parsePosition.getIndex());
    }

    @Test
    public void testInputHasWhitespace_2_oe() {
        final FastDateParser parser = new FastDateParser("M/d/y", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse(" 3/ 23/ 1961", parsePosition);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertEquals(1961, calendar.get(Calendar.YEAR));
    }

    @Test
    public void testInputHasWhitespace_3_oe() {
        final FastDateParser parser = new FastDateParser("M/d/y", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse(" 3/ 23/ 1961", parsePosition);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertEquals(2, calendar.get(Calendar.MONTH));
    }

    @Test
    public void testInputHasWhitespace_4_oe() {
        final FastDateParser parser = new FastDateParser("M/d/y", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse(" 3/ 23/ 1961", parsePosition);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertEquals(23, calendar.get(Calendar.DATE));
    }

    @Test
    public void testInputHasWrongCharacters_1_oe() {
        final FastDateParser parser = new FastDateParser("MM-dd-yyy", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        assertNull(parser.parse("03/23/1961", parsePosition));
    }

    @Test
    public void testInputHasWrongDay_1_oe() {
        final FastDateParser parser = new FastDateParser("EEEE, MM/dd/yyy", NEW_YORK, Locale.US);
        final String input = "Thursday, 03/23/61";
        final ParsePosition parsePosition = new ParsePosition(0);
        assertNotNull(parser.parse(input, parsePosition));
    }

    @Test
    public void testInputHasWrongDay_3_oe() {
        final FastDateParser parser = new FastDateParser("EEEE, MM/dd/yyy", NEW_YORK, Locale.US);
        final String input = "Thursday, 03/23/61";
        final ParsePosition parsePosition = new ParsePosition(0);

        parsePosition.setIndex(0);
        assertNull(parser.parse( "Thorsday, 03/23/61", parsePosition));
    }

    @Test
    public void testInputHasWrongTimeZone_1_oe() {
        final FastDateParser parser = new FastDateParser("mm:ss z", NEW_YORK, Locale.US);

        final String input = "11:23 Pacific Standard Time";
        final ParsePosition parsePosition = new ParsePosition(0);
        assertNotNull(parser.parse(input, parsePosition));
    }

    @Test
    public void testInputHasWrongTimeZone_3_oe() {
        final FastDateParser parser = new FastDateParser("mm:ss z", NEW_YORK, Locale.US);

        final String input = "11:23 Pacific Standard Time";
        final ParsePosition parsePosition = new ParsePosition(0);

        parsePosition.setIndex(0);
        assertNull(parser.parse( "11:23 Pacific Standard ", parsePosition));
    }

}
