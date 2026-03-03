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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - IsX methods
 */
public class StringUtilsIsTest_OE25Dev  {

    @Test
    public void testIsAlpha_1_oe() {
        assertFalse(StringUtils.isAlpha(null));
    }

    @Test
    public void testIsAlpha_2_oe() {
        assertFalse(StringUtils.isAlpha(""));
    }

    @Test
    public void testIsAlpha_3_oe() {
        assertFalse(StringUtils.isAlpha(" "));
    }

    @Test
    public void testIsAlpha_4_oe() {
        assertTrue(StringUtils.isAlpha("a"));
    }

    @Test
    public void testIsAlpha_5_oe() {
        assertTrue(StringUtils.isAlpha("A"));
    }

    @Test
    public void testIsAlpha_6_oe() {
        assertTrue(StringUtils.isAlpha("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
    }

    @Test
    public void testIsAlpha_7_oe() {
        assertFalse(StringUtils.isAlpha("ham kso"));
    }

    @Test
    public void testIsAlpha_8_oe() {
        assertFalse(StringUtils.isAlpha("1"));
    }

    @Test
    public void testIsAlpha_9_oe() {
        assertFalse(StringUtils.isAlpha("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
    }

    @Test
    public void testIsAlpha_10_oe() {
        assertFalse(StringUtils.isAlpha("_"));
    }

    @Test
    public void testIsAlpha_11_oe() {
        assertFalse(StringUtils.isAlpha("hkHKHik*khbkuh"));
    }

    @Test
    public void testIsAlphanumeric_1_oe() {
        assertFalse(StringUtils.isAlphanumeric(null));
    }

    @Test
    public void testIsAlphanumeric_2_oe() {
        assertFalse(StringUtils.isAlphanumeric(""));
    }

    @Test
    public void testIsAlphanumeric_3_oe() {
        assertFalse(StringUtils.isAlphanumeric(" "));
    }

    @Test
    public void testIsAlphanumeric_4_oe() {
        assertTrue(StringUtils.isAlphanumeric("a"));
    }

    @Test
    public void testIsAlphanumeric_5_oe() {
        assertTrue(StringUtils.isAlphanumeric("A"));
    }

    @Test
    public void testIsAlphanumeric_6_oe() {
        assertTrue(StringUtils.isAlphanumeric("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
    }

    @Test
    public void testIsAlphanumeric_7_oe() {
        assertFalse(StringUtils.isAlphanumeric("ham kso"));
    }

    @Test
    public void testIsAlphanumeric_8_oe() {
        assertTrue(StringUtils.isAlphanumeric("1"));
    }

    @Test
    public void testIsAlphanumeric_9_oe() {
        assertTrue(StringUtils.isAlphanumeric("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
    }

    @Test
    public void testIsAlphanumeric_10_oe() {
        assertFalse(StringUtils.isAlphanumeric("_"));
    }

    @Test
    public void testIsAlphanumeric_11_oe() {
        assertFalse(StringUtils.isAlphanumeric("hkHKHik*khbkuh"));
    }

    @Test
    public void testIsAlphanumericSpace_1_oe() {
        assertFalse(StringUtils.isAlphanumericSpace(null));
    }

    @Test
    public void testIsAlphanumericSpace_2_oe() {
        assertTrue(StringUtils.isAlphanumericSpace(""));
    }

    @Test
    public void testIsAlphanumericSpace_3_oe() {
        assertTrue(StringUtils.isAlphanumericSpace(" "));
    }

    @Test
    public void testIsAlphanumericSpace_4_oe() {
        assertTrue(StringUtils.isAlphanumericSpace("a"));
    }

    @Test
    public void testIsAlphanumericSpace_5_oe() {
        assertTrue(StringUtils.isAlphanumericSpace("A"));
    }

    @Test
    public void testIsAlphanumericSpace_6_oe() {
        assertTrue(StringUtils.isAlphanumericSpace("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
    }

    @Test
    public void testIsAlphanumericSpace_7_oe() {
        assertTrue(StringUtils.isAlphanumericSpace("ham kso"));
    }

    @Test
    public void testIsAlphanumericSpace_8_oe() {
        assertTrue(StringUtils.isAlphanumericSpace("1"));
    }

    @Test
    public void testIsAlphanumericSpace_9_oe() {
        assertTrue(StringUtils.isAlphanumericSpace("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
    }

    @Test
    public void testIsAlphanumericSpace_10_oe() {
        assertFalse(StringUtils.isAlphanumericSpace("_"));
    }

    @Test
    public void testIsAlphanumericSpace_11_oe() {
        assertFalse(StringUtils.isAlphanumericSpace("hkHKHik*khbkuh"));
    }

    @Test
    public void testIsAlphaspace_1_oe() {
        assertFalse(StringUtils.isAlphaSpace(null));
    }

    @Test
    public void testIsAlphaspace_2_oe() {
        assertTrue(StringUtils.isAlphaSpace(""));
    }

    @Test
    public void testIsAlphaspace_3_oe() {
        assertTrue(StringUtils.isAlphaSpace(" "));
    }

    @Test
    public void testIsAlphaspace_4_oe() {
        assertTrue(StringUtils.isAlphaSpace("a"));
    }

    @Test
    public void testIsAlphaspace_5_oe() {
        assertTrue(StringUtils.isAlphaSpace("A"));
    }

    @Test
    public void testIsAlphaspace_6_oe() {
        assertTrue(StringUtils.isAlphaSpace("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
    }

    @Test
    public void testIsAlphaspace_7_oe() {
        assertTrue(StringUtils.isAlphaSpace("ham kso"));
    }

    @Test
    public void testIsAlphaspace_8_oe() {
        assertFalse(StringUtils.isAlphaSpace("1"));
    }

    @Test
    public void testIsAlphaspace_9_oe() {
        assertFalse(StringUtils.isAlphaSpace("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
    }

    @Test
    public void testIsAlphaspace_10_oe() {
        assertFalse(StringUtils.isAlphaSpace("_"));
    }

    @Test
    public void testIsAlphaspace_11_oe() {
        assertFalse(StringUtils.isAlphaSpace("hkHKHik*khbkuh"));
    }

    @Test
    public void testIsAsciiPrintable_String_1_oe() {
        assertFalse(StringUtils.isAsciiPrintable(null));
    }

    @Test
    public void testIsAsciiPrintable_String_2_oe() {
        assertTrue(StringUtils.isAsciiPrintable(""));
    }

    @Test
    public void testIsAsciiPrintable_String_3_oe() {
        assertTrue(StringUtils.isAsciiPrintable(" "));
    }

    @Test
    public void testIsAsciiPrintable_String_4_oe() {
        assertTrue(StringUtils.isAsciiPrintable("a"));
    }

    @Test
    public void testIsAsciiPrintable_String_5_oe() {
        assertTrue(StringUtils.isAsciiPrintable("A"));
    }

    @Test
    public void testIsAsciiPrintable_String_6_oe() {
        assertTrue(StringUtils.isAsciiPrintable("1"));
    }

    @Test
    public void testIsAsciiPrintable_String_7_oe() {
        assertTrue(StringUtils.isAsciiPrintable("Ceki"));
    }

    @Test
    public void testIsAsciiPrintable_String_8_oe() {
        assertTrue(StringUtils.isAsciiPrintable("!ab2c~"));
    }

    @Test
    public void testIsAsciiPrintable_String_9_oe() {
        assertTrue(StringUtils.isAsciiPrintable("1000"));
    }

    @Test
    public void testIsAsciiPrintable_String_10_oe() {
        assertTrue(StringUtils.isAsciiPrintable("10 00"));
    }

    @Test
    public void testIsAsciiPrintable_String_11_oe() {
        assertFalse(StringUtils.isAsciiPrintable("10\t00"));
    }

    @Test
    public void testIsAsciiPrintable_String_12_oe() {
        assertTrue(StringUtils.isAsciiPrintable("10.00"));
    }

    @Test
    public void testIsAsciiPrintable_String_13_oe() {
        assertTrue(StringUtils.isAsciiPrintable("10,00"));
    }

    @Test
    public void testIsAsciiPrintable_String_14_oe() {
        assertTrue(StringUtils.isAsciiPrintable("!ab-c~"));
    }

    @Test
    public void testIsAsciiPrintable_String_15_oe() {
        assertTrue(StringUtils.isAsciiPrintable("hkHK=Hik6i?UGH_KJgU7.tUJgKJ*GI87GI,kug"));
    }

    @Test
    public void testIsAsciiPrintable_String_16_oe() {
        assertTrue(StringUtils.isAsciiPrintable("\u0020"));
    }

    @Test
    public void testIsAsciiPrintable_String_17_oe() {
        assertTrue(StringUtils.isAsciiPrintable("\u0021"));
    }

    @Test
    public void testIsAsciiPrintable_String_18_oe() {
        assertTrue(StringUtils.isAsciiPrintable("\u007e"));
    }

    @Test
    public void testIsAsciiPrintable_String_19_oe() {
        assertFalse(StringUtils.isAsciiPrintable("\u007f"));
    }

    @Test
    public void testIsAsciiPrintable_String_20_oe() {
        assertTrue(StringUtils.isAsciiPrintable("G?lc?"));
    }

    @Test
    public void testIsAsciiPrintable_String_21_oe() {
        assertTrue(StringUtils.isAsciiPrintable("=?iso-8859-1?Q?G=FClc=FC?="));
    }

    @Test
    public void testIsAsciiPrintable_String_22_oe() {
        assertFalse(StringUtils.isAsciiPrintable("G\u00fclc\u00fc"));
    }

    @Test
    public void testIsNumeric_1_oe() {
        assertFalse(StringUtils.isNumeric(null));
    }

    @Test
    public void testIsNumeric_2_oe() {
        assertFalse(StringUtils.isNumeric(""));
    }

    @Test
    public void testIsNumeric_3_oe() {
        assertFalse(StringUtils.isNumeric(" "));
    }

    @Test
    public void testIsNumeric_4_oe() {
        assertFalse(StringUtils.isNumeric("a"));
    }

    @Test
    public void testIsNumeric_5_oe() {
        assertFalse(StringUtils.isNumeric("A"));
    }

    @Test
    public void testIsNumeric_6_oe() {
        assertFalse(StringUtils.isNumeric("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
    }

    @Test
    public void testIsNumeric_7_oe() {
        assertFalse(StringUtils.isNumeric("ham kso"));
    }

    @Test
    public void testIsNumeric_8_oe() {
        assertTrue(StringUtils.isNumeric("1"));
    }

    @Test
    public void testIsNumeric_9_oe() {
        assertTrue(StringUtils.isNumeric("1000"));
    }

    @Test
    public void testIsNumeric_10_oe() {
        assertTrue(StringUtils.isNumeric("\u0967\u0968\u0969"));
    }

    @Test
    public void testIsNumeric_11_oe() {
        assertFalse(StringUtils.isNumeric("\u0967\u0968 \u0969"));
    }

    @Test
    public void testIsNumeric_12_oe() {
        assertFalse(StringUtils.isNumeric("2.3"));
    }

    @Test
    public void testIsNumeric_13_oe() {
        assertFalse(StringUtils.isNumeric("10 00"));
    }

    @Test
    public void testIsNumeric_14_oe() {
        assertFalse(StringUtils.isNumeric("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
    }

    @Test
    public void testIsNumeric_15_oe() {
        assertFalse(StringUtils.isNumeric("_"));
    }

    @Test
    public void testIsNumeric_16_oe() {
        assertFalse(StringUtils.isNumeric("hkHKHik*khbkuh"));
    }

    @Test
    public void testIsNumeric_17_oe() {
        assertFalse(StringUtils.isNumeric("+123"));
    }

    @Test
    public void testIsNumeric_18_oe() {
        assertFalse(StringUtils.isNumeric("-123"));
    }

    @Test
    public void testIsNumericSpace_1_oe() {
        assertFalse(StringUtils.isNumericSpace(null));
    }

    @Test
    public void testIsNumericSpace_2_oe() {
        assertTrue(StringUtils.isNumericSpace(""));
    }

    @Test
    public void testIsNumericSpace_3_oe() {
        assertTrue(StringUtils.isNumericSpace(" "));
    }

    @Test
    public void testIsNumericSpace_4_oe() {
        assertFalse(StringUtils.isNumericSpace("a"));
    }

    @Test
    public void testIsNumericSpace_5_oe() {
        assertFalse(StringUtils.isNumericSpace("A"));
    }

    @Test
    public void testIsNumericSpace_6_oe() {
        assertFalse(StringUtils.isNumericSpace("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
    }

    @Test
    public void testIsNumericSpace_7_oe() {
        assertFalse(StringUtils.isNumericSpace("ham kso"));
    }

    @Test
    public void testIsNumericSpace_8_oe() {
        assertTrue(StringUtils.isNumericSpace("1"));
    }

    @Test
    public void testIsNumericSpace_9_oe() {
        assertTrue(StringUtils.isNumericSpace("1000"));
    }

    @Test
    public void testIsNumericSpace_10_oe() {
        assertFalse(StringUtils.isNumericSpace("2.3"));
    }

    @Test
    public void testIsNumericSpace_11_oe() {
        assertTrue(StringUtils.isNumericSpace("10 00"));
    }

    @Test
    public void testIsNumericSpace_12_oe() {
        assertTrue(StringUtils.isNumericSpace("\u0967\u0968\u0969"));
    }

    @Test
    public void testIsNumericSpace_13_oe() {
        assertTrue(StringUtils.isNumericSpace("\u0967\u0968 \u0969"));
    }

    @Test
    public void testIsNumericSpace_14_oe() {
        assertFalse(StringUtils.isNumericSpace("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
    }

    @Test
    public void testIsNumericSpace_15_oe() {
        assertFalse(StringUtils.isNumericSpace("_"));
    }

    @Test
    public void testIsNumericSpace_16_oe() {
        assertFalse(StringUtils.isNumericSpace("hkHKHik*khbkuh"));
    }

    @Test
    public void testIsWhitespace_1_oe() {
        assertFalse(StringUtils.isWhitespace(null));
    }

    @Test
    public void testIsWhitespace_2_oe() {
        assertTrue(StringUtils.isWhitespace(""));
    }

    @Test
    public void testIsWhitespace_3_oe() {
        assertTrue(StringUtils.isWhitespace(" "));
    }

    @Test
    public void testIsWhitespace_4_oe() {
        assertTrue(StringUtils.isWhitespace("\t \n \t"));
    }

    @Test
    public void testIsWhitespace_5_oe() {
        assertFalse(StringUtils.isWhitespace("\t aa\n \t"));
    }

    @Test
    public void testIsWhitespace_6_oe() {
        assertTrue(StringUtils.isWhitespace(" "));
    }

    @Test
    public void testIsWhitespace_7_oe() {
        assertFalse(StringUtils.isWhitespace(" a "));
    }

    @Test
    public void testIsWhitespace_8_oe() {
        assertFalse(StringUtils.isWhitespace("a  "));
    }

    @Test
    public void testIsWhitespace_9_oe() {
        assertFalse(StringUtils.isWhitespace("  a"));
    }

    @Test
    public void testIsWhitespace_10_oe() {
        assertFalse(StringUtils.isWhitespace("aba"));
    }

    @Test
    public void testIsWhitespace_11_oe() {
        assertTrue(StringUtils.isWhitespace(StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testIsWhitespace_12_oe() {
        assertFalse(StringUtils.isWhitespace(StringUtilsTest.NON_WHITESPACE));
    }

}
