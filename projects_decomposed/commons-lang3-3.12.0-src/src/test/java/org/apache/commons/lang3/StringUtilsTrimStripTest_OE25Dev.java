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
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Trim/Strip methods
 */
public class StringUtilsTrimStripTest_OE25Dev  {
    private static final String FOO = "foo";

    //-----------------------------------------------------------------------

    @Test
    public void testTrim_1_oe() {
        assertEquals(FOO, StringUtils.trim(FOO + "  "));
    }

    @Test
    public void testTrim_2_oe() {
        // removed other assertion
        assertEquals(FOO, StringUtils.trim(" " + FOO + "  "));
    }

    @Test
    public void testTrim_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(FOO, StringUtils.trim(" " + FOO));
    }

    @Test
    public void testTrim_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(FOO, StringUtils.trim(FOO + ""));
    }

    @Test
    public void testTrim_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.trim(" \t\r\n\b "));
    }

    @Test
    public void testTrim_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.trim(StringUtilsTest.TRIMMABLE));
    }

    @Test
    public void testTrim_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.NON_TRIMMABLE, StringUtils.trim(StringUtilsTest.NON_TRIMMABLE));
    }

    @Test
    public void testTrim_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.trim(""));
    }

    @Test
    public void testTrim_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.trim(null));
    }

    @Test
    public void testTrimToNull_1_oe() {
        assertEquals(FOO, StringUtils.trimToNull(FOO + "  "));
    }

    @Test
    public void testTrimToNull_2_oe() {
        // removed other assertion
        assertEquals(FOO, StringUtils.trimToNull(" " + FOO + "  "));
    }

    @Test
    public void testTrimToNull_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(FOO, StringUtils.trimToNull(" " + FOO));
    }

    @Test
    public void testTrimToNull_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(FOO, StringUtils.trimToNull(FOO + ""));
    }

    @Test
    public void testTrimToNull_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.trimToNull(" \t\r\n\b "));
    }

    @Test
    public void testTrimToNull_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.trimToNull(StringUtilsTest.TRIMMABLE));
    }

    @Test
    public void testTrimToNull_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.NON_TRIMMABLE, StringUtils.trimToNull(StringUtilsTest.NON_TRIMMABLE));
    }

    @Test
    public void testTrimToNull_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.trimToNull(""));
    }

    @Test
    public void testTrimToNull_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.trimToNull(null));
    }

    @Test
    public void testTrimToEmpty_1_oe() {
        assertEquals(FOO, StringUtils.trimToEmpty(FOO + "  "));
    }

    @Test
    public void testTrimToEmpty_2_oe() {
        // removed other assertion
        assertEquals(FOO, StringUtils.trimToEmpty(" " + FOO + "  "));
    }

    @Test
    public void testTrimToEmpty_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(FOO, StringUtils.trimToEmpty(" " + FOO));
    }

    @Test
    public void testTrimToEmpty_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(FOO, StringUtils.trimToEmpty(FOO + ""));
    }

    @Test
    public void testTrimToEmpty_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.trimToEmpty(" \t\r\n\b "));
    }

    @Test
    public void testTrimToEmpty_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.trimToEmpty(StringUtilsTest.TRIMMABLE));
    }

    @Test
    public void testTrimToEmpty_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.NON_TRIMMABLE, StringUtils.trimToEmpty(StringUtilsTest.NON_TRIMMABLE));
    }

    @Test
    public void testTrimToEmpty_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.trimToEmpty(""));
    }

    @Test
    public void testTrimToEmpty_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.trimToEmpty(null));
    }

    @Test
    public void testStrip_String_1_oe() {
        assertNull(StringUtils.strip(null));
    }

    @Test
    public void testStrip_String_2_oe() {
        // removed other assertion
        assertEquals("", StringUtils.strip(""));
    }

    @Test
    public void testStrip_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.strip("        "));
    }

    @Test
    public void testStrip_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("abc", StringUtils.strip("  abc  "));
    }

    @Test
    public void testStrip_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.NON_WHITESPACE, StringUtils.strip(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testStripToNull_String_1_oe() {
        assertNull(StringUtils.stripToNull(null));
    }

    @Test
    public void testStripToNull_String_2_oe() {
        // removed other assertion
        assertNull(StringUtils.stripToNull(""));
    }

    @Test
    public void testStripToNull_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.stripToNull("        "));
    }

    @Test
    public void testStripToNull_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.stripToNull(StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testStripToNull_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ab c", StringUtils.stripToNull("  ab c  "));
    }

    @Test
    public void testStripToNull_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.NON_WHITESPACE, StringUtils.stripToNull(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testStripToEmpty_String_1_oe() {
        assertEquals("", StringUtils.stripToEmpty(null));
    }

    @Test
    public void testStripToEmpty_String_2_oe() {
        // removed other assertion
        assertEquals("", StringUtils.stripToEmpty(""));
    }

    @Test
    public void testStripToEmpty_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.stripToEmpty("        "));
    }

    @Test
    public void testStripToEmpty_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.stripToEmpty(StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testStripToEmpty_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ab c", StringUtils.stripToEmpty("  ab c  "));
    }

    @Test
    public void testStripToEmpty_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.NON_WHITESPACE, StringUtils.stripToEmpty(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testStrip_StringString_1_oe() {
        // null strip
        assertNull(StringUtils.strip(null, null));
    }

    @Test
    public void testStrip_StringString_2_oe() {
        // null strip
        // removed other assertion
        assertEquals("", StringUtils.strip("", null));
    }

    @Test
    public void testStrip_StringString_3_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.strip("        ", null));
    }

    @Test
    public void testStrip_StringString_4_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("abc", StringUtils.strip("  abc  ", null));
    }

    @Test
    public void testStrip_StringString_5_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.NON_WHITESPACE, StringUtils.strip(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE, null));
    }

    @Test
    public void testStrip_StringString_6_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        assertNull(StringUtils.strip(null, ""));
    }

    @Test
    public void testStrip_StringString_7_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        assertEquals("", StringUtils.strip("", ""));
    }

    @Test
    public void testStrip_StringString_8_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        assertEquals("        ", StringUtils.strip("        ", ""));
    }

    @Test
    public void testStrip_StringString_9_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("  abc  ", StringUtils.strip("  abc  ", ""));
    }

    @Test
    public void testStrip_StringString_10_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.strip(StringUtilsTest.WHITESPACE, ""));
    }

    @Test
    public void testStrip_StringString_11_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " strip
        assertNull(StringUtils.strip(null, " "));
    }

    @Test
    public void testStrip_StringString_12_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " strip
        // removed other assertion
        assertEquals("", StringUtils.strip("", " "));
    }

    @Test
    public void testStrip_StringString_13_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " strip
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.strip("        ", " "));
    }

    @Test
    public void testStrip_StringString_14_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("abc", StringUtils.strip("  abc  ", " "));
    }

    @Test
    public void testStrip_StringString_15_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" strip
        assertNull(StringUtils.strip(null, "ab"));
    }

    @Test
    public void testStrip_StringString_16_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" strip
        // removed other assertion
        assertEquals("", StringUtils.strip("", "ab"));
    }

    @Test
    public void testStrip_StringString_17_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" strip
        // removed other assertion
        // removed other assertion
        assertEquals("        ", StringUtils.strip("        ", "ab"));
    }

    @Test
    public void testStrip_StringString_18_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("  abc  ", StringUtils.strip("  abc  ", "ab"));
    }

    @Test
    public void testStrip_StringString_19_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", StringUtils.strip("abcabab", "ab"));
    }

    @Test
    public void testStrip_StringString_20_oe() {
        // null strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" strip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.strip(StringUtilsTest.WHITESPACE, ""));
    }

    @Test
    public void testStripStart_StringString_1_oe() {
        // null stripStart
        assertNull(StringUtils.stripStart(null, null));
    }

    @Test
    public void testStripStart_StringString_2_oe() {
        // null stripStart
        // removed other assertion
        assertEquals("", StringUtils.stripStart("", null));
    }

    @Test
    public void testStripStart_StringString_3_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.stripStart("        ", null));
    }

    @Test
    public void testStripStart_StringString_4_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("abc  ", StringUtils.stripStart("  abc  ", null));
    }

    @Test
    public void testStripStart_StringString_5_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE, StringUtils.stripStart(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE, null));
    }

    @Test
    public void testStripStart_StringString_6_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        assertNull(StringUtils.stripStart(null, ""));
    }

    @Test
    public void testStripStart_StringString_7_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        assertEquals("", StringUtils.stripStart("", ""));
    }

    @Test
    public void testStripStart_StringString_8_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        assertEquals("        ", StringUtils.stripStart("        ", ""));
    }

    @Test
    public void testStripStart_StringString_9_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("  abc  ", StringUtils.stripStart("  abc  ", ""));
    }

    @Test
    public void testStripStart_StringString_10_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.stripStart(StringUtilsTest.WHITESPACE, ""));
    }

    @Test
    public void testStripStart_StringString_11_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripStart
        assertNull(StringUtils.stripStart(null, " "));
    }

    @Test
    public void testStripStart_StringString_12_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripStart
        // removed other assertion
        assertEquals("", StringUtils.stripStart("", " "));
    }

    @Test
    public void testStripStart_StringString_13_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripStart
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.stripStart("        ", " "));
    }

    @Test
    public void testStripStart_StringString_14_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("abc  ", StringUtils.stripStart("  abc  ", " "));
    }

    @Test
    public void testStripStart_StringString_15_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripStart
        assertNull(StringUtils.stripStart(null, "ab"));
    }

    @Test
    public void testStripStart_StringString_16_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripStart
        // removed other assertion
        assertEquals("", StringUtils.stripStart("", "ab"));
    }

    @Test
    public void testStripStart_StringString_17_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripStart
        // removed other assertion
        // removed other assertion
        assertEquals("        ", StringUtils.stripStart("        ", "ab"));
    }

    @Test
    public void testStripStart_StringString_18_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("  abc  ", StringUtils.stripStart("  abc  ", "ab"));
    }

    @Test
    public void testStripStart_StringString_19_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("cabab", StringUtils.stripStart("abcabab", "ab"));
    }

    @Test
    public void testStripStart_StringString_20_oe() {
        // null stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripStart
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.stripStart(StringUtilsTest.WHITESPACE, ""));
    }

    @Test
    public void testStripEnd_StringString_1_oe() {
        // null stripEnd
        assertNull(StringUtils.stripEnd(null, null));
    }

    @Test
    public void testStripEnd_StringString_2_oe() {
        // null stripEnd
        // removed other assertion
        assertEquals("", StringUtils.stripEnd("", null));
    }

    @Test
    public void testStripEnd_StringString_3_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.stripEnd("        ", null));
    }

    @Test
    public void testStripEnd_StringString_4_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("  abc", StringUtils.stripEnd("  abc  ", null));
    }

    @Test
    public void testStripEnd_StringString_5_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE, StringUtils.stripEnd(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE, null));
    }

    @Test
    public void testStripEnd_StringString_6_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        assertNull(StringUtils.stripEnd(null, ""));
    }

    @Test
    public void testStripEnd_StringString_7_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        assertEquals("", StringUtils.stripEnd("", ""));
    }

    @Test
    public void testStripEnd_StringString_8_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        assertEquals("        ", StringUtils.stripEnd("        ", ""));
    }

    @Test
    public void testStripEnd_StringString_9_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("  abc  ", StringUtils.stripEnd("  abc  ", ""));
    }

    @Test
    public void testStripEnd_StringString_10_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.stripEnd(StringUtilsTest.WHITESPACE, ""));
    }

    @Test
    public void testStripEnd_StringString_11_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripEnd
        assertNull(StringUtils.stripEnd(null, " "));
    }

    @Test
    public void testStripEnd_StringString_12_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripEnd
        // removed other assertion
        assertEquals("", StringUtils.stripEnd("", " "));
    }

    @Test
    public void testStripEnd_StringString_13_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripEnd
        // removed other assertion
        // removed other assertion
        assertEquals("", StringUtils.stripEnd("        ", " "));
    }

    @Test
    public void testStripEnd_StringString_14_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("  abc", StringUtils.stripEnd("  abc  ", " "));
    }

    @Test
    public void testStripEnd_StringString_15_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripEnd
        assertNull(StringUtils.stripEnd(null, "ab"));
    }

    @Test
    public void testStripEnd_StringString_16_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripEnd
        // removed other assertion
        assertEquals("", StringUtils.stripEnd("", "ab"));
    }

    @Test
    public void testStripEnd_StringString_17_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripEnd
        // removed other assertion
        // removed other assertion
        assertEquals("        ", StringUtils.stripEnd("        ", "ab"));
    }

    @Test
    public void testStripEnd_StringString_18_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("  abc  ", StringUtils.stripEnd("  abc  ", "ab"));
    }

    @Test
    public void testStripEnd_StringString_19_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("abc", StringUtils.stripEnd("abcabab", "ab"));
    }

    @Test
    public void testStripEnd_StringString_20_oe() {
        // null stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // " " stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "ab" stripEnd
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.stripEnd(StringUtilsTest.WHITESPACE, ""));
    }

    @Test
    public void testStripAll_1_oe() {
        // test stripAll method, merely an array version of the above strip
        final String[] empty = new String[0];
        final String[] fooSpace = new String[] { "  "+FOO+"  ", "  "+FOO, FOO+"  " };
        final String[] fooDots = new String[] { ".."+FOO+"..", ".."+FOO, FOO+".." };
        final String[] foo = new String[] { FOO, FOO, FOO };

        assertNull(StringUtils.stripAll((String[]) null));
    }

    @Test
    public void testStripAll_2_oe() {
        // test stripAll method, merely an array version of the above strip
        final String[] empty = new String[0];
        final String[] fooSpace = new String[] { "  "+FOO+"  ", "  "+FOO, FOO+"  " };
        final String[] fooDots = new String[] { ".."+FOO+"..", ".."+FOO, FOO+".." };
        final String[] foo = new String[] { FOO, FOO, FOO };

        // removed other assertion
        // Additional varargs tests
        assertArrayEquals(empty, StringUtils.stripAll()); // empty array;
    }

    @Test
    public void testStripAll_3_oe() {
        // test stripAll method, merely an array version of the above strip
        final String[] empty = new String[0];
        final String[] fooSpace = new String[] { "  "+FOO+"  ", "  "+FOO, FOO+"  " };
        final String[] fooDots = new String[] { ".."+FOO+"..", ".."+FOO, FOO+".." };
        final String[] foo = new String[] { FOO, FOO, FOO };

        // removed other assertion
        // Additional varargs tests
        // removed other assertion
        assertArrayEquals(new String[]{null}, StringUtils.stripAll((String) null)); // == new String[]{null};
    }

    @Test
    public void testStripAll_4_oe() {
        // test stripAll method, merely an array version of the above strip
        final String[] empty = new String[0];
        final String[] fooSpace = new String[] { "  "+FOO+"  ", "  "+FOO, FOO+"  " };
        final String[] fooDots = new String[] { ".."+FOO+"..", ".."+FOO, FOO+".." };
        final String[] foo = new String[] { FOO, FOO, FOO };

        // removed other assertion
        // Additional varargs tests
        // removed other assertion
        // removed other assertion

        assertArrayEquals(empty, StringUtils.stripAll(empty));
    }

    @Test
    public void testStripAll_5_oe() {
        // test stripAll method, merely an array version of the above strip
        final String[] empty = new String[0];
        final String[] fooSpace = new String[] { "  "+FOO+"  ", "  "+FOO, FOO+"  " };
        final String[] fooDots = new String[] { ".."+FOO+"..", ".."+FOO, FOO+".." };
        final String[] foo = new String[] { FOO, FOO, FOO };

        // removed other assertion
        // Additional varargs tests
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(foo, StringUtils.stripAll(fooSpace));
    }

    @Test
    public void testStripAll_6_oe() {
        // test stripAll method, merely an array version of the above strip
        final String[] empty = new String[0];
        final String[] fooSpace = new String[] { "  "+FOO+"  ", "  "+FOO, FOO+"  " };
        final String[] fooDots = new String[] { ".."+FOO+"..", ".."+FOO, FOO+".." };
        final String[] foo = new String[] { FOO, FOO, FOO };

        // removed other assertion
        // Additional varargs tests
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertNull(StringUtils.stripAll(null, null));
    }

    @Test
    public void testStripAll_7_oe() {
        // test stripAll method, merely an array version of the above strip
        final String[] empty = new String[0];
        final String[] fooSpace = new String[] { "  "+FOO+"  ", "  "+FOO, FOO+"  " };
        final String[] fooDots = new String[] { ".."+FOO+"..", ".."+FOO, FOO+".." };
        final String[] foo = new String[] { FOO, FOO, FOO };

        // removed other assertion
        // Additional varargs tests
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(foo, StringUtils.stripAll(fooSpace, null));
    }

    @Test
    public void testStripAll_8_oe() {
        // test stripAll method, merely an array version of the above strip
        final String[] empty = new String[0];
        final String[] fooSpace = new String[] { "  "+FOO+"  ", "  "+FOO, FOO+"  " };
        final String[] fooDots = new String[] { ".."+FOO+"..", ".."+FOO, FOO+".." };
        final String[] foo = new String[] { FOO, FOO, FOO };

        // removed other assertion
        // Additional varargs tests
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals(foo, StringUtils.stripAll(fooDots, "."));
    }

    @Test
    public void testStripAccents_1_oe() {
        final String cue = "\u00C7\u00FA\u00EA";
        assertEquals("Cue", StringUtils.stripAccents(cue), "Failed to strip accents from " + cue);
    }

    @Test
    public void testStripAccents_2_oe() {
        final String cue = "\u00C7\u00FA\u00EA";
        // removed other assertion

        final String lots = "\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00C7\u00C8\u00C9" +
                      "\u00CA\u00CB\u00CC\u00CD\u00CE\u00CF\u00D1\u00D2\u00D3" +
                      "\u00D4\u00D5\u00D6\u00D9\u00DA\u00DB\u00DC\u00DD";
        assertEquals("AAAAAACEEEEIIIINOOOOOUUUUY", StringUtils.stripAccents(lots), "Failed to strip accents from " + lots);
    }

    @Test
    public void testStripAccents_3_oe() {
        final String cue = "\u00C7\u00FA\u00EA";
        // removed other assertion

        final String lots = "\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00C7\u00C8\u00C9" +
                      "\u00CA\u00CB\u00CC\u00CD\u00CE\u00CF\u00D1\u00D2\u00D3" +
                      "\u00D4\u00D5\u00D6\u00D9\u00DA\u00DB\u00DC\u00DD";
        // removed other assertion

        assertNull(StringUtils.stripAccents(null), "Failed null safety");
    }

    @Test
    public void testStripAccents_4_oe() {
        final String cue = "\u00C7\u00FA\u00EA";
        // removed other assertion

        final String lots = "\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00C7\u00C8\u00C9" +
                      "\u00CA\u00CB\u00CC\u00CD\u00CE\u00CF\u00D1\u00D2\u00D3" +
                      "\u00D4\u00D5\u00D6\u00D9\u00DA\u00DB\u00DC\u00DD";
        // removed other assertion

        // removed other assertion
        assertEquals("", StringUtils.stripAccents(""), "Failed empty String");
    }

    @Test
    public void testStripAccents_5_oe() {
        final String cue = "\u00C7\u00FA\u00EA";
        // removed other assertion

        final String lots = "\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00C7\u00C8\u00C9" +
                      "\u00CA\u00CB\u00CC\u00CD\u00CE\u00CF\u00D1\u00D2\u00D3" +
                      "\u00D4\u00D5\u00D6\u00D9\u00DA\u00DB\u00DC\u00DD";
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("control", StringUtils.stripAccents("control"), "Failed to handle non-accented text");
    }

    @Test
    public void testStripAccents_6_oe() {
        final String cue = "\u00C7\u00FA\u00EA";
        // removed other assertion

        final String lots = "\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00C7\u00C8\u00C9" +
                      "\u00CA\u00CB\u00CC\u00CD\u00CE\u00CF\u00D1\u00D2\u00D3" +
                      "\u00D4\u00D5\u00D6\u00D9\u00DA\u00DB\u00DC\u00DD";
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("eclair", StringUtils.stripAccents("\u00E9clair"), "Failed to handle easy example");
    }

    @Test
    public void testStripAccents_7_oe() {
        final String cue = "\u00C7\u00FA\u00EA";
        // removed other assertion

        final String lots = "\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00C7\u00C8\u00C9" +
                      "\u00CA\u00CB\u00CC\u00CD\u00CE\u00CF\u00D1\u00D2\u00D3" +
                      "\u00D4\u00D5\u00D6\u00D9\u00DA\u00DB\u00DC\u00DD";
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ALOSZZCN aloszzcn", StringUtils.stripAccents("\u0104\u0141\u00D3\u015A\u017B\u0179\u0106\u0143 " + "\u0105\u0142\u00F3\u015B\u017C\u017A\u0107\u0144"));
    }

}
