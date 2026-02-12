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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests {@link org.apache.commons.lang3.CharUtils}.
 */
public class CharUtilsTest_OE25Dev {

    private static final char CHAR_COPY = '\u00a9';
    private static final Character CHARACTER_A = Character.valueOf('A');
    private static final Character CHARACTER_B = Character.valueOf('B');

    @Test
    public void testCompare_1_oe() {
        assertTrue(CharUtils.compare('a', 'b') < 0);
    }

    @Test
    public void testCompare_2_oe() {
        // removed other assertion
        assertEquals(0, CharUtils.compare('c', 'c'));
    }

    @Test
    public void testCompare_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(CharUtils.compare('c', 'a') > 0);
    }

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new CharUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        // removed other assertion
        final Constructor<?>[] cons = CharUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        // removed other assertion
        final Constructor<?>[] cons = CharUtils.class.getDeclaredConstructors();
        // removed other assertion
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        // removed other assertion
        final Constructor<?>[] cons = CharUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        assertTrue(Modifier.isPublic(CharUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        // removed other assertion
        final Constructor<?>[] cons = CharUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Modifier.isFinal(CharUtils.class.getModifiers()));
    }

    @Test
    public void testIsAscii_char_1_oe() {
        assertTrue(CharUtils.isAscii('a'));
    }

    @Test
    public void testIsAscii_char_2_oe() {
        // removed other assertion
        assertTrue(CharUtils.isAscii('A'));
    }

    @Test
    public void testIsAscii_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(CharUtils.isAscii('3'));
    }

    @Test
    public void testIsAscii_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CharUtils.isAscii('-'));
    }

    @Test
    public void testIsAscii_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CharUtils.isAscii('\n'));
    }

    @Test
    public void testIsAscii_char_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAscii(CHAR_COPY));
    }

    @Test
    public void testIsAscii_char_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 255; i++) {
            assertEquals(i < 128, CharUtils.isAscii((char) i));
    }
    }

    @Test
    public void testIsAsciiAlpha_char_1_oe() {
        assertTrue(CharUtils.isAsciiAlpha('a'));
    }

    @Test
    public void testIsAsciiAlpha_char_2_oe() {
        // removed other assertion
        assertTrue(CharUtils.isAsciiAlpha('A'));
    }

    @Test
    public void testIsAsciiAlpha_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlpha('3'));
    }

    @Test
    public void testIsAsciiAlpha_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlpha('-'));
    }

    @Test
    public void testIsAsciiAlpha_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlpha('\n'));
    }

    @Test
    public void testIsAsciiAlpha_char_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlpha(CHAR_COPY));
    }

    @Test
    public void testIsAsciiAlpha_char_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if ((i >= 'A' && i <= 'Z') || (i >= 'a' && i <= 'z')) {
                assertTrue(CharUtils.isAsciiAlpha((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiAlpha_char_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if ((i >= 'A' && i <= 'Z') || (i >= 'a' && i <= 'z')) {
                // removed other assertion
            } else {
                assertFalse(CharUtils.isAsciiAlpha((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiAlphaLower_char_1_oe() {
        assertTrue(CharUtils.isAsciiAlphaLower('a'));
    }

    @Test
    public void testIsAsciiAlphaLower_char_2_oe() {
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphaLower('A'));
    }

    @Test
    public void testIsAsciiAlphaLower_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphaLower('3'));
    }

    @Test
    public void testIsAsciiAlphaLower_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphaLower('-'));
    }

    @Test
    public void testIsAsciiAlphaLower_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphaLower('\n'));
    }

    @Test
    public void testIsAsciiAlphaLower_char_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphaLower(CHAR_COPY));
    }

    @Test
    public void testIsAsciiAlphaLower_char_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if (i >= 'a' && i <= 'z') {
                assertTrue(CharUtils.isAsciiAlphaLower((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiAlphaLower_char_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if (i >= 'a' && i <= 'z') {
                // removed other assertion
            } else {
                assertFalse(CharUtils.isAsciiAlphaLower((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiAlphanumeric_char_1_oe() {
        assertTrue(CharUtils.isAsciiAlphanumeric('a'));
    }

    @Test
    public void testIsAsciiAlphanumeric_char_2_oe() {
        // removed other assertion
        assertTrue(CharUtils.isAsciiAlphanumeric('A'));
    }

    @Test
    public void testIsAsciiAlphanumeric_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(CharUtils.isAsciiAlphanumeric('3'));
    }

    @Test
    public void testIsAsciiAlphanumeric_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphanumeric('-'));
    }

    @Test
    public void testIsAsciiAlphanumeric_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphanumeric('\n'));
    }

    @Test
    public void testIsAsciiAlphanumeric_char_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphanumeric(CHAR_COPY));
    }

    @Test
    public void testIsAsciiAlphanumeric_char_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if ((i >= 'A' && i <= 'Z') || (i >= 'a' && i <= 'z') || (i >= '0' && i <= '9')) {
                assertTrue(CharUtils.isAsciiAlphanumeric((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiAlphanumeric_char_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if ((i >= 'A' && i <= 'Z') || (i >= 'a' && i <= 'z') || (i >= '0' && i <= '9')) {
                // removed other assertion
            } else {
                assertFalse(CharUtils.isAsciiAlphanumeric((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiAlphaUpper_char_1_oe() {
        assertFalse(CharUtils.isAsciiAlphaUpper('a'));
    }

    @Test
    public void testIsAsciiAlphaUpper_char_2_oe() {
        // removed other assertion
        assertTrue(CharUtils.isAsciiAlphaUpper('A'));
    }

    @Test
    public void testIsAsciiAlphaUpper_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphaUpper('3'));
    }

    @Test
    public void testIsAsciiAlphaUpper_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphaUpper('-'));
    }

    @Test
    public void testIsAsciiAlphaUpper_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphaUpper('\n'));
    }

    @Test
    public void testIsAsciiAlphaUpper_char_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiAlphaUpper(CHAR_COPY));
    }

    @Test
    public void testIsAsciiAlphaUpper_char_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if (i >= 'A' && i <= 'Z') {
                assertTrue(CharUtils.isAsciiAlphaUpper((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiAlphaUpper_char_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if (i >= 'A' && i <= 'Z') {
                // removed other assertion
            } else {
                assertFalse(CharUtils.isAsciiAlphaUpper((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiControl_char_1_oe() {
        assertFalse(CharUtils.isAsciiControl('a'));
    }

    @Test
    public void testIsAsciiControl_char_2_oe() {
        // removed other assertion
        assertFalse(CharUtils.isAsciiControl('A'));
    }

    @Test
    public void testIsAsciiControl_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiControl('3'));
    }

    @Test
    public void testIsAsciiControl_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiControl('-'));
    }

    @Test
    public void testIsAsciiControl_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CharUtils.isAsciiControl('\n'));
    }

    @Test
    public void testIsAsciiControl_char_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiControl(CHAR_COPY));
    }

    @Test
    public void testIsAsciiControl_char_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if (i < 32 || i == 127) {
                assertTrue(CharUtils.isAsciiControl((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiControl_char_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if (i < 32 || i == 127) {
                // removed other assertion
            } else {
                assertFalse(CharUtils.isAsciiControl((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiNumeric_char_1_oe() {
        assertFalse(CharUtils.isAsciiNumeric('a'));
    }

    @Test
    public void testIsAsciiNumeric_char_2_oe() {
        // removed other assertion
        assertFalse(CharUtils.isAsciiNumeric('A'));
    }

    @Test
    public void testIsAsciiNumeric_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(CharUtils.isAsciiNumeric('3'));
    }

    @Test
    public void testIsAsciiNumeric_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiNumeric('-'));
    }

    @Test
    public void testIsAsciiNumeric_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiNumeric('\n'));
    }

    @Test
    public void testIsAsciiNumeric_char_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiNumeric(CHAR_COPY));
    }

    @Test
    public void testIsAsciiNumeric_char_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if (i >= '0' && i <= '9') {
                assertTrue(CharUtils.isAsciiNumeric((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiNumeric_char_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if (i >= '0' && i <= '9') {
                // removed other assertion
            } else {
                assertFalse(CharUtils.isAsciiNumeric((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiPrintable_char_1_oe() {
        assertTrue(CharUtils.isAsciiPrintable('a'));
    }

    @Test
    public void testIsAsciiPrintable_char_2_oe() {
        // removed other assertion
        assertTrue(CharUtils.isAsciiPrintable('A'));
    }

    @Test
    public void testIsAsciiPrintable_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(CharUtils.isAsciiPrintable('3'));
    }

    @Test
    public void testIsAsciiPrintable_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CharUtils.isAsciiPrintable('-'));
    }

    @Test
    public void testIsAsciiPrintable_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiPrintable('\n'));
    }

    @Test
    public void testIsAsciiPrintable_char_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharUtils.isAsciiPrintable(CHAR_COPY));
    }

    @Test
    public void testIsAsciiPrintable_char_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if (i >= 32 && i <= 126) {
                assertTrue(CharUtils.isAsciiPrintable((char) i));
    }
    }
    }

    @Test
    public void testIsAsciiPrintable_char_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            if (i >= 32 && i <= 126) {
                // removed other assertion
            } else {
                assertFalse(CharUtils.isAsciiPrintable((char) i));
    }
    }
    }

    @Test
    public void testToChar_Character_1_oe() {
        assertEquals('A', CharUtils.toChar(CHARACTER_A));
    }

    @Test
    public void testToChar_Character_2_oe() {
        // removed other assertion
        assertEquals('B', CharUtils.toChar(CHARACTER_B));
    }

    @Test
    public void testToChar_Character_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    CharUtils.toChar((Character) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToChar_Character_char_1_oe() {
        assertEquals('A', CharUtils.toChar(CHARACTER_A, 'X'));
    }

    @Test
    public void testToChar_Character_char_2_oe() {
        // removed other assertion
        assertEquals('B', CharUtils.toChar(CHARACTER_B, 'X'));
    }

    @Test
    public void testToChar_Character_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals('X', CharUtils.toChar((Character) null, 'X'));
    }

    @Test
    public void testToChar_String_1_oe() {
        assertEquals('A', CharUtils.toChar("A"));
    }

    @Test
    public void testToChar_String_2_oe() {
        // removed other assertion
        assertEquals('B', CharUtils.toChar("BA"));
    }

    @Test
    public void testToChar_String_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    CharUtils.toChar((String) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToChar_String_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    CharUtils.toChar("");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testToChar_String_char_1_oe() {
        assertEquals('A', CharUtils.toChar("A", 'X'));
    }

    @Test
    public void testToChar_String_char_2_oe() {
        // removed other assertion
        assertEquals('B', CharUtils.toChar("BA", 'X'));
    }

    @Test
    public void testToChar_String_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals('X', CharUtils.toChar("", 'X'));
    }

    @Test
    public void testToChar_String_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('X', CharUtils.toChar((String) null, 'X'));
    }

    @Test
    public void testToCharacterObject_char_1_oe() {
        assertEquals(Character.valueOf('a'), CharUtils.toCharacterObject('a'));
    }

    @Test
    public void testToCharacterObject_char_2_oe() {
        // removed other assertion
        assertSame(CharUtils.toCharacterObject('a'), CharUtils.toCharacterObject('a'));
    }

    @Test
    public void testToCharacterObject_char_3_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            assertSame(ch, ch2);
    }
    }

    @Test
    public void testToCharacterObject_char_4_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            // removed other assertion
            assertEquals(i, ch.charValue());
    }
    }

    @Test
    public void testToCharacterObject_char_5_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            assertEquals(ch, ch2);
    }
    }

    @Test
    public void testToCharacterObject_char_6_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            // removed other assertion
            assertNotSame(ch, ch2);
    }
    }

    @Test
    public void testToCharacterObject_char_7_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            // removed other assertion
            // removed other assertion
            assertEquals(i, ch.charValue());
    }
    }

    @Test
    public void testToCharacterObject_char_8_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(i, ch2.charValue());
    }
    }

    @Test
    public void testToCharacterObject_char_9_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        assertSame(CharUtils.toCharacterObject("a"), CharUtils.toCharacterObject('a'));
    }

    @Test
    public void testToCharacterObject_String_1_oe() {
        assertNull(CharUtils.toCharacterObject(null));
    }

    @Test
    public void testToCharacterObject_String_2_oe() {
        // removed other assertion
        assertNull(CharUtils.toCharacterObject(""));
    }

    @Test
    public void testToCharacterObject_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Character.valueOf('a'), CharUtils.toCharacterObject("a"));
    }

    @Test
    public void testToCharacterObject_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Character.valueOf('a'), CharUtils.toCharacterObject("abc"));
    }

    @Test
    public void testToCharacterObject_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(CharUtils.toCharacterObject("a"), CharUtils.toCharacterObject("a"));
    }

    @Test
    public void testToIntValue_char_1_oe() {
        assertEquals(0, CharUtils.toIntValue('0'));
    }

    @Test
    public void testToIntValue_char_2_oe() {
        // removed other assertion
        assertEquals(1, CharUtils.toIntValue('1'));
    }

    @Test
    public void testToIntValue_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2, CharUtils.toIntValue('2'));
    }

    @Test
    public void testToIntValue_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, CharUtils.toIntValue('3'));
    }

    @Test
    public void testToIntValue_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, CharUtils.toIntValue('4'));
    }

    @Test
    public void testToIntValue_char_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, CharUtils.toIntValue('5'));
    }

    @Test
    public void testToIntValue_char_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, CharUtils.toIntValue('6'));
    }

    @Test
    public void testToIntValue_char_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, CharUtils.toIntValue('7'));
    }

    @Test
    public void testToIntValue_char_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, CharUtils.toIntValue('8'));
    }

    @Test
    public void testToIntValue_char_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, CharUtils.toIntValue('9'));
    }

    @Test
    public void testToIntValue_char_11_oe() throws Exception {
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
        try {
    CharUtils.toIntValue('a');
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testToIntValue_char_int_1_oe() {
        assertEquals(0, CharUtils.toIntValue('0', -1));
    }

    @Test
    public void testToIntValue_char_int_2_oe() {
        // removed other assertion
        assertEquals(3, CharUtils.toIntValue('3', -1));
    }

    @Test
    public void testToIntValue_char_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, CharUtils.toIntValue('a', -1));
    }

    @Test
    public void testToIntValue_Character_1_oe() {
        assertEquals(0, CharUtils.toIntValue(Character.valueOf('0')));
    }

    @Test
    public void testToIntValue_Character_2_oe() {
        // removed other assertion
        assertEquals(3, CharUtils.toIntValue(Character.valueOf('3')));
    }

    @Test
    public void testToIntValue_Character_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    CharUtils.toIntValue(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToIntValue_Character_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    CharUtils.toIntValue(CHARACTER_A);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testToIntValue_Character_int_1_oe() {
        assertEquals(0, CharUtils.toIntValue(Character.valueOf('0'), -1));
    }

    @Test
    public void testToIntValue_Character_int_2_oe() {
        // removed other assertion
        assertEquals(3, CharUtils.toIntValue(Character.valueOf('3'), -1));
    }

    @Test
    public void testToIntValue_Character_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, CharUtils.toIntValue(Character.valueOf('A'), -1));
    }

    @Test
    public void testToIntValue_Character_int_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, CharUtils.toIntValue(null, -1));
    }

    @Test
    public void testToString_char_1_oe() {
        assertEquals("a", CharUtils.toString('a'));
    }

    @Test
    public void testToString_char_2_oe() {
        // removed other assertion
        assertSame(CharUtils.toString('a'), CharUtils.toString('a'));
    }

    @Test
    public void testToString_char_3_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            assertSame(str, str2);
    }
    }

    @Test
    public void testToString_char_4_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            assertEquals(1, str.length());
    }
    }

    @Test
    public void testToString_char_5_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            assertEquals(i, str.charAt(0));
    }
    }

    @Test
    public void testToString_char_6_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            assertEquals(str, str2);
    }
    }

    @Test
    public void testToString_char_7_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            assertNotSame(str, str2);
    }
    }

    @Test
    public void testToString_char_8_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            assertEquals(1, str.length());
    }
    }

    @Test
    public void testToString_char_9_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(i, str.charAt(0));
    }
    }

    @Test
    public void testToString_char_10_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(1, str2.length());
    }
    }

    @Test
    public void testToString_char_11_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        for (int i = 128; i < 196; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(i, str2.charAt(0));
    }
    }

    @Test
    public void testToString_Character_1_oe() {
        assertNull(CharUtils.toString(null));
    }

    @Test
    public void testToString_Character_2_oe() {
        // removed other assertion
        assertEquals("A", CharUtils.toString(CHARACTER_A));
    }

    @Test
    public void testToString_Character_3_oe() {
        // removed other assertion
        // removed other assertion
        assertSame(CharUtils.toString(CHARACTER_A), CharUtils.toString(CHARACTER_A));
    }

    @Test
    public void testToUnicodeEscaped_char_1_oe() {
        assertEquals("\\u0041", CharUtils.unicodeEscaped('A'));
    }

    @Test
    public void testToUnicodeEscaped_char_2_oe() {
        // removed other assertion
        assertEquals("\\u004c", CharUtils.unicodeEscaped('L'));
    }

    @Test
    public void testToUnicodeEscaped_char_3_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            final String str = CharUtils.unicodeEscaped((char) i);
            assertEquals(6, str.length());
    }
    }

    @Test
    public void testToUnicodeEscaped_char_4_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            final String str = CharUtils.unicodeEscaped((char) i);
            // removed other assertion
            final int val = Integer.parseInt(str.substring(2), 16);
            assertEquals(i, val);
    }
    }

    @Test
    public void testToUnicodeEscaped_char_5_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            final String str = CharUtils.unicodeEscaped((char) i);
            // removed other assertion
            final int val = Integer.parseInt(str.substring(2), 16);
            // removed other assertion
        }
        assertEquals("\\u0999", CharUtils.unicodeEscaped((char) 0x999));
    }

    @Test
    public void testToUnicodeEscaped_char_6_oe() {
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 196; i++) {
            final String str = CharUtils.unicodeEscaped((char) i);
            // removed other assertion
            final int val = Integer.parseInt(str.substring(2), 16);
            // removed other assertion
        }
        // removed other assertion
        assertEquals("\\u1001", CharUtils.unicodeEscaped((char) 0x1001));
    }

    @Test
    public void testToUnicodeEscaped_Character_1_oe() {
        assertNull(CharUtils.unicodeEscaped(null));
    }

    @Test
    public void testToUnicodeEscaped_Character_2_oe() {
        // removed other assertion
        assertEquals("\\u0041", CharUtils.unicodeEscaped(CHARACTER_A));
    }

}
