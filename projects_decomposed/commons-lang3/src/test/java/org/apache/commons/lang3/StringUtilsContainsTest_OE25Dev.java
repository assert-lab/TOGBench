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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.DefaultLocale;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Contains methods
 */
public class StringUtilsContainsTest_OE25Dev  {
    /**
     * Supplementary character U+20000
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    private static final String CharU20000 = "\uD840\uDC00";
    /**
     * Supplementary character U+20001
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    private static final String CharU20001 = "\uD840\uDC01";
    /**
     * Incomplete supplementary character U+20000, high surrogate only.
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    private static final String CharUSuppCharHigh = "\uDC00";

    /**
     * Incomplete supplementary character U+20000, low surrogate only.
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    private static final String CharUSuppCharLow = "\uD840";

    @Test
    public void testContains_Char() {
        assertFalse(StringUtils.contains(null, ' '));
        assertFalse(StringUtils.contains("", ' '));
        assertFalse(StringUtils.contains("", null));
        assertFalse(StringUtils.contains(null, null));
        assertTrue(StringUtils.contains("abc", 'a'));
        assertTrue(StringUtils.contains("abc", 'b'));
        assertTrue(StringUtils.contains("abc", 'c'));
        assertFalse(StringUtils.contains("abc", 'z'));
    }

    @Test
    public void testContains_String() {
        assertFalse(StringUtils.contains(null, null));
        assertFalse(StringUtils.contains(null, ""));
        assertFalse(StringUtils.contains(null, "a"));
        assertFalse(StringUtils.contains("", null));
        assertTrue(StringUtils.contains("", ""));
        assertFalse(StringUtils.contains("", "a"));
        assertTrue(StringUtils.contains("abc", "a"));
        assertTrue(StringUtils.contains("abc", "b"));
        assertTrue(StringUtils.contains("abc", "c"));
        assertTrue(StringUtils.contains("abc", "abc"));
        assertFalse(StringUtils.contains("abc", "z"));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContains_StringWithBadSupplementaryChars() {
        // Test edge case: 1/2 of a (broken) supplementary char
        assertFalse(StringUtils.contains(CharUSuppCharHigh, CharU20001));
        assertFalse(StringUtils.contains(CharUSuppCharLow, CharU20001));
        assertFalse(StringUtils.contains(CharU20001, CharUSuppCharHigh));
        assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
        assertTrue(StringUtils.contains(CharU20001, CharUSuppCharLow));
        assertTrue(StringUtils.contains(CharU20001 + CharUSuppCharLow + "a", "a"));
        assertTrue(StringUtils.contains(CharU20001 + CharUSuppCharHigh + "a", "a"));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContains_StringWithSupplementaryChars() {
        assertTrue(StringUtils.contains(CharU20000 + CharU20001, CharU20000));
        assertTrue(StringUtils.contains(CharU20000 + CharU20001, CharU20001));
        assertTrue(StringUtils.contains(CharU20000, CharU20000));
        assertFalse(StringUtils.contains(CharU20000, CharU20001));
    }

    @Test
    public void testContainsAny_StringCharArray() {
        assertFalse(StringUtils.containsAny(null, (char[]) null));
        assertFalse(StringUtils.containsAny(null, new char[0]));
        assertFalse(StringUtils.containsAny(null, 'a', 'b'));

        assertFalse(StringUtils.containsAny("", (char[]) null));
        assertFalse(StringUtils.containsAny("", new char[0]));
        assertFalse(StringUtils.containsAny("", 'a', 'b'));

        assertFalse(StringUtils.containsAny("zzabyycdxx", (char[]) null));
        assertFalse(StringUtils.containsAny("zzabyycdxx", new char[0]));
        assertTrue(StringUtils.containsAny("zzabyycdxx", 'z', 'a'));
        assertTrue(StringUtils.containsAny("zzabyycdxx", 'b', 'y'));
        assertTrue(StringUtils.containsAny("zzabyycdxx", 'z', 'y'));
        assertFalse(StringUtils.containsAny("ab", 'z'));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsAny_StringCharArrayWithBadSupplementaryChars() {
        // Test edge case: 1/2 of a (broken) supplementary char
        assertFalse(StringUtils.containsAny(CharUSuppCharHigh, CharU20001.toCharArray()));
        assertFalse(StringUtils.containsAny("abc" + CharUSuppCharHigh + "xyz", CharU20001.toCharArray()));
        assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
        assertFalse(StringUtils.containsAny(CharUSuppCharLow, CharU20001.toCharArray()));
        assertFalse(StringUtils.containsAny(CharU20001, CharUSuppCharHigh.toCharArray()));
        assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
        assertTrue(StringUtils.containsAny(CharU20001, CharUSuppCharLow.toCharArray()));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars() {
        assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20000.toCharArray()));
        assertTrue(StringUtils.containsAny("a" + CharU20000 + CharU20001, "a".toCharArray()));
        assertTrue(StringUtils.containsAny(CharU20000 + "a" + CharU20001, "a".toCharArray()));
        assertTrue(StringUtils.containsAny(CharU20000 + CharU20001 + "a", "a".toCharArray()));
        assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20001.toCharArray()));
        assertTrue(StringUtils.containsAny(CharU20000, CharU20000.toCharArray()));
        // Sanity check:
        assertEquals(-1, CharU20000.indexOf(CharU20001));
        assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
        assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
        // Test:
        assertFalse(StringUtils.containsAny(CharU20000, CharU20001.toCharArray()));
        assertFalse(StringUtils.containsAny(CharU20001, CharU20000.toCharArray()));
    }

    @Test
    public void testContainsAny_StringString() {
        assertFalse(StringUtils.containsAny(null, (String) null));
        assertFalse(StringUtils.containsAny(null, ""));
        assertFalse(StringUtils.containsAny(null, "ab"));

        assertFalse(StringUtils.containsAny("", (String) null));
        assertFalse(StringUtils.containsAny("", ""));
        assertFalse(StringUtils.containsAny("", "ab"));

        assertFalse(StringUtils.containsAny("zzabyycdxx", (String) null));
        assertFalse(StringUtils.containsAny("zzabyycdxx", ""));
        assertTrue(StringUtils.containsAny("zzabyycdxx", "za"));
        assertTrue(StringUtils.containsAny("zzabyycdxx", "by"));
        assertTrue(StringUtils.containsAny("zzabyycdxx", "zy"));
        assertFalse(StringUtils.containsAny("ab", "z"));
    }

    @Test
    public void testContainsAny_StringStringArray() {
        assertFalse(StringUtils.containsAny(null, (String[]) null));
        assertFalse(StringUtils.containsAny(null, new String[0]));
        assertFalse(StringUtils.containsAny(null, new String[] { "hello" }));
        assertFalse(StringUtils.containsAny("", (String[]) null));
        assertFalse(StringUtils.containsAny("", new String[0]));
        assertFalse(StringUtils.containsAny("", new String[] { "hello" }));
        assertFalse(StringUtils.containsAny("hello, goodbye", (String[]) null));
        assertFalse(StringUtils.containsAny("hello, goodbye", new String[0]));
        assertTrue(StringUtils.containsAny("hello, goodbye", new String[]{"hello", "goodbye"}));
        assertTrue(StringUtils.containsAny("hello, goodbye", new String[]{"hello", "Goodbye"}));
        assertFalse(StringUtils.containsAny("hello, goodbye", new String[]{"Hello", "Goodbye"}));
        assertFalse(StringUtils.containsAny("hello, goodbye", new String[]{"Hello", null}));
        assertFalse(StringUtils.containsAny("hello, null", new String[] { "Hello", null }));
        // Javadoc examples:
        assertTrue(StringUtils.containsAny("abcd", "ab", null));
        assertTrue(StringUtils.containsAny("abcd", "ab", "cd"));
        assertTrue(StringUtils.containsAny("abc", "d", "abc"));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray() {
        assertFalse(StringUtils.containsAnyIgnoreCase(null, (String[]) null));
        assertFalse(StringUtils.containsAnyIgnoreCase(null, new String[0]));
        assertFalse(StringUtils.containsAnyIgnoreCase(null, new String[] { "hello" }));
        assertFalse(StringUtils.containsAnyIgnoreCase("", (String[]) null));
        assertFalse(StringUtils.containsAnyIgnoreCase("", new String[0]));
        assertFalse(StringUtils.containsAnyIgnoreCase("", new String[] { "hello" }));
        assertFalse(StringUtils.containsAnyIgnoreCase("hello, goodbye", (String[]) null));
        assertFalse(StringUtils.containsAnyIgnoreCase("hello, goodbye", new String[0]));
        assertTrue(StringUtils.containsAnyIgnoreCase("hello, goodbye", new String[]{"hello", "goodbye"}));
        assertTrue(StringUtils.containsAnyIgnoreCase("hello, goodbye", new String[]{"hello", "Goodbye"}));
        assertTrue(StringUtils.containsAnyIgnoreCase("hello, goodbye", new String[]{"Hello", "Goodbye"}));
        assertTrue(StringUtils.containsAnyIgnoreCase("hello, goodbye", new String[]{"Hello", null}));
        assertTrue(StringUtils.containsAnyIgnoreCase("hello, null", new String[] { "Hello", null }));
        // Javadoc examples:
        assertTrue(StringUtils.containsAnyIgnoreCase("abcd", "ab", null));
        assertTrue(StringUtils.containsAnyIgnoreCase("abcd", "ab", "cd"));
        assertTrue(StringUtils.containsAnyIgnoreCase("abc", "d", "abc"));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsAny_StringWithBadSupplementaryChars() {
        // Test edge case: 1/2 of a (broken) supplementary char
        assertFalse(StringUtils.containsAny(CharUSuppCharHigh, CharU20001));
        assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
        assertFalse(StringUtils.containsAny(CharUSuppCharLow, CharU20001));
        assertFalse(StringUtils.containsAny(CharU20001, CharUSuppCharHigh));
        assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
        assertTrue(StringUtils.containsAny(CharU20001, CharUSuppCharLow));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsAny_StringWithSupplementaryChars() {
        assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20000));
        assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20001));
        assertTrue(StringUtils.containsAny(CharU20000, CharU20000));
        // Sanity check:
        assertEquals(-1, CharU20000.indexOf(CharU20001));
        assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
        assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
        // Test:
        assertFalse(StringUtils.containsAny(CharU20000, CharU20001));
        assertFalse(StringUtils.containsAny(CharU20001, CharU20000));
    }

    @DefaultLocale(language = "de", country = "DE")
    @Test
    public void testContainsIgnoreCase_LocaleIndependence() {
        final Locale[] locales = { Locale.ENGLISH, new Locale("tr"), Locale.getDefault() };

        final String[][] tdata = { { "i", "I" }, { "I", "i" }, { "\u03C2", "\u03C3" }, { "\u03A3", "\u03C2" },
            { "\u03A3", "\u03C3" }, };

        final String[][] fdata = { { "\u00DF", "SS" }, };

        for (final Locale testLocale : locales) {
            Locale.setDefault(testLocale);
            for (int j = 0; j < tdata.length; j++) {
                assertTrue(StringUtils.containsIgnoreCase(tdata[j][0],tdata[j][1]),Locale.getDefault()+ ": " + j + " " + tdata[j][0] + " " + tdata[j][1]);
            }
            for (int j = 0; j < fdata.length; j++) {
                assertFalse(StringUtils.containsIgnoreCase(fdata[j][0],fdata[j][1]),Locale.getDefault()+ ": " + j + " " + fdata[j][0] + " " + fdata[j][1]);
            }
        }
    }

    @Test
    public void testContainsIgnoreCase_StringString() {
        assertFalse(StringUtils.containsIgnoreCase(null, null));

        // Null tests
        assertFalse(StringUtils.containsIgnoreCase(null, ""));
        assertFalse(StringUtils.containsIgnoreCase(null, "a"));
        assertFalse(StringUtils.containsIgnoreCase(null, "abc"));

        assertFalse(StringUtils.containsIgnoreCase("", null));
        assertFalse(StringUtils.containsIgnoreCase("a", null));
        assertFalse(StringUtils.containsIgnoreCase("abc", null));

        // Match len = 0
        assertTrue(StringUtils.containsIgnoreCase("", ""));
        assertTrue(StringUtils.containsIgnoreCase("a", ""));
        assertTrue(StringUtils.containsIgnoreCase("abc", ""));

        // Match len = 1
        assertFalse(StringUtils.containsIgnoreCase("", "a"));
        assertTrue(StringUtils.containsIgnoreCase("a", "a"));
        assertTrue(StringUtils.containsIgnoreCase("abc", "a"));
        assertFalse(StringUtils.containsIgnoreCase("", "A"));
        assertTrue(StringUtils.containsIgnoreCase("a", "A"));
        assertTrue(StringUtils.containsIgnoreCase("abc", "A"));

        // Match len > 1
        assertFalse(StringUtils.containsIgnoreCase("", "abc"));
        assertFalse(StringUtils.containsIgnoreCase("a", "abc"));
        assertTrue(StringUtils.containsIgnoreCase("xabcz", "abc"));
        assertFalse(StringUtils.containsIgnoreCase("", "ABC"));
        assertFalse(StringUtils.containsIgnoreCase("a", "ABC"));
        assertTrue(StringUtils.containsIgnoreCase("xabcz", "ABC"));
    }

    @Test
    public void testContainsNone_CharArray() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone(null, (char[]) null));
        assertTrue(StringUtils.containsNone("", (char[]) null));
        assertTrue(StringUtils.containsNone(null, emptyChars));
        assertTrue(StringUtils.containsNone(str1, emptyChars));
        assertTrue(StringUtils.containsNone("", emptyChars));
        assertTrue(StringUtils.containsNone("", chars1));
        assertTrue(StringUtils.containsNone(str1, chars1));
        assertTrue(StringUtils.containsNone(str1, chars2));
        assertTrue(StringUtils.containsNone(str1, chars3));
        assertFalse(StringUtils.containsNone(str2, chars1));
        assertTrue(StringUtils.containsNone(str2, chars2));
        assertTrue(StringUtils.containsNone(str2, chars3));
        assertFalse(StringUtils.containsNone(str3, chars1));
        assertFalse(StringUtils.containsNone(str3, chars2));
        assertTrue(StringUtils.containsNone(str3, chars3));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsNone_CharArrayWithBadSupplementaryChars() {
        // Test edge case: 1/2 of a (broken) supplementary char
        assertTrue(StringUtils.containsNone(CharUSuppCharHigh, CharU20001.toCharArray()));
        assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
        assertTrue(StringUtils.containsNone(CharUSuppCharLow, CharU20001.toCharArray()));
        assertEquals(-1, CharU20001.indexOf(CharUSuppCharHigh));
        assertTrue(StringUtils.containsNone(CharU20001, CharUSuppCharHigh.toCharArray()));
        assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
        assertFalse(StringUtils.containsNone(CharU20001, CharUSuppCharLow.toCharArray()));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsNone_CharArrayWithSupplementaryChars() {
        assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20000.toCharArray()));
        assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20001.toCharArray()));
        assertFalse(StringUtils.containsNone(CharU20000, CharU20000.toCharArray()));
        // Sanity check:
        assertEquals(-1, CharU20000.indexOf(CharU20001));
        assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
        assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
        // Test:
        assertTrue(StringUtils.containsNone(CharU20000, CharU20001.toCharArray()));
        assertTrue(StringUtils.containsNone(CharU20001, CharU20000.toCharArray()));
    }

    @Test
    public void testContainsNone_String() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone(null, (String) null));
        assertTrue(StringUtils.containsNone("", (String) null));
        assertTrue(StringUtils.containsNone(null, ""));
        assertTrue(StringUtils.containsNone(str1, ""));
        assertTrue(StringUtils.containsNone("", ""));
        assertTrue(StringUtils.containsNone("", chars1));
        assertTrue(StringUtils.containsNone(str1, chars1));
        assertTrue(StringUtils.containsNone(str1, chars2));
        assertTrue(StringUtils.containsNone(str1, chars3));
        assertFalse(StringUtils.containsNone(str2, chars1));
        assertTrue(StringUtils.containsNone(str2, chars2));
        assertTrue(StringUtils.containsNone(str2, chars3));
        assertFalse(StringUtils.containsNone(str3, chars1));
        assertFalse(StringUtils.containsNone(str3, chars2));
        assertTrue(StringUtils.containsNone(str3, chars3));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsNone_StringWithBadSupplementaryChars() {
        // Test edge case: 1/2 of a (broken) supplementary char
        assertTrue(StringUtils.containsNone(CharUSuppCharHigh, CharU20001));
        assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
        assertTrue(StringUtils.containsNone(CharUSuppCharLow, CharU20001));
        assertEquals(-1, CharU20001.indexOf(CharUSuppCharHigh));
        assertTrue(StringUtils.containsNone(CharU20001, CharUSuppCharHigh));
        assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
        assertFalse(StringUtils.containsNone(CharU20001, CharUSuppCharLow));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsNone_StringWithSupplementaryChars() {
        assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20000));
        assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20001));
        assertFalse(StringUtils.containsNone(CharU20000, CharU20000));
        // Sanity check:
        assertEquals(-1, CharU20000.indexOf(CharU20001));
        assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
        assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
        // Test:
        assertTrue(StringUtils.containsNone(CharU20000, CharU20001));
        assertTrue(StringUtils.containsNone(CharU20001, CharU20000));
    }

    @Test
    public void testContainsOnly_CharArray() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsOnly(null, (char[]) null));
        assertFalse(StringUtils.containsOnly("", (char[]) null));
        assertFalse(StringUtils.containsOnly(null, emptyChars));
        assertFalse(StringUtils.containsOnly(str1, emptyChars));
        assertTrue(StringUtils.containsOnly("", emptyChars));
        assertTrue(StringUtils.containsOnly("", chars1));
        assertFalse(StringUtils.containsOnly(str1, chars1));
        assertTrue(StringUtils.containsOnly(str1, chars2));
        assertTrue(StringUtils.containsOnly(str1, chars3));
        assertTrue(StringUtils.containsOnly(str2, chars1));
        assertFalse(StringUtils.containsOnly(str2, chars2));
        assertTrue(StringUtils.containsOnly(str2, chars3));
        assertFalse(StringUtils.containsOnly(str3, chars1));
        assertFalse(StringUtils.containsOnly(str3, chars2));
        assertTrue(StringUtils.containsOnly(str3, chars3));
    }

    @Test
    public void testContainsOnly_String() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertFalse(StringUtils.containsOnly(null, (String) null));
        assertFalse(StringUtils.containsOnly("", (String) null));
        assertFalse(StringUtils.containsOnly(null, ""));
        assertFalse(StringUtils.containsOnly(str1, ""));
        assertTrue(StringUtils.containsOnly("", ""));
        assertTrue(StringUtils.containsOnly("", chars1));
        assertFalse(StringUtils.containsOnly(str1, chars1));
        assertTrue(StringUtils.containsOnly(str1, chars2));
        assertTrue(StringUtils.containsOnly(str1, chars3));
        assertTrue(StringUtils.containsOnly(str2, chars1));
        assertFalse(StringUtils.containsOnly(str2, chars2));
        assertTrue(StringUtils.containsOnly(str2, chars3));
        assertFalse(StringUtils.containsOnly(str3, chars1));
        assertFalse(StringUtils.containsOnly(str3, chars2));
        assertTrue(StringUtils.containsOnly(str3, chars3));
    }

    @Test
    public void testContainsWhitespace() {
        assertFalse( StringUtils.containsWhitespace("") );
        assertTrue( StringUtils.containsWhitespace(" ") );
        assertFalse( StringUtils.containsWhitespace("a") );
        assertTrue( StringUtils.containsWhitespace("a ") );
        assertTrue( StringUtils.containsWhitespace(" a") );
        assertTrue( StringUtils.containsWhitespace("a\t") );
        assertTrue( StringUtils.containsWhitespace("\n") );
    }

    @Test
    public void testContains_Char_1_oe() {
        assertFalse(StringUtils.contains(null, ' '));
    }

    @Test
    public void testContains_Char_2_oe() {
        assertFalse(StringUtils.contains("", ' '));
    }

    @Test
    public void testContains_Char_3_oe() {
        assertFalse(StringUtils.contains("", null));
    }

    @Test
    public void testContains_Char_4_oe() {
        assertFalse(StringUtils.contains(null, null));
    }

    @Test
    public void testContains_Char_5_oe() {
        assertTrue(StringUtils.contains("abc", 'a'));
    }

    @Test
    public void testContains_Char_6_oe() {
        assertTrue(StringUtils.contains("abc", 'b'));
    }

    @Test
    public void testContains_Char_7_oe() {
        assertTrue(StringUtils.contains("abc", 'c'));
    }

    @Test
    public void testContains_Char_8_oe() {
        assertFalse(StringUtils.contains("abc", 'z'));
    }

    @Test
    public void testContains_String_1_oe() {
        assertFalse(StringUtils.contains(null, null));
    }

    @Test
    public void testContains_String_2_oe() {
        assertFalse(StringUtils.contains(null, ""));
    }

    @Test
    public void testContains_String_3_oe() {
        assertFalse(StringUtils.contains(null, "a"));
    }

    @Test
    public void testContains_String_4_oe() {
        assertFalse(StringUtils.contains("", null));
    }

    @Test
    public void testContains_String_5_oe() {
        assertTrue(StringUtils.contains("", ""));
    }

    @Test
    public void testContains_String_6_oe() {
        assertFalse(StringUtils.contains("", "a"));
    }

    @Test
    public void testContains_String_7_oe() {
        assertTrue(StringUtils.contains("abc", "a"));
    }

    @Test
    public void testContains_String_8_oe() {
        assertTrue(StringUtils.contains("abc", "b"));
    }

    @Test
    public void testContains_String_9_oe() {
        assertTrue(StringUtils.contains("abc", "c"));
    }

    @Test
    public void testContains_String_10_oe() {
        assertTrue(StringUtils.contains("abc", "abc"));
    }

    @Test
    public void testContains_String_11_oe() {
        assertFalse(StringUtils.contains("abc", "z"));
    }

    @Test
    public void testContains_StringWithBadSupplementaryChars_1_oe() {
        assertFalse(StringUtils.contains(CharUSuppCharHigh, CharU20001));
    }

    @Test
    public void testContains_StringWithBadSupplementaryChars_2_oe() {
        assertFalse(StringUtils.contains(CharUSuppCharLow, CharU20001));
    }

    @Test
    public void testContains_StringWithBadSupplementaryChars_3_oe() {
        assertFalse(StringUtils.contains(CharU20001, CharUSuppCharHigh));
    }

    @Test
    public void testContains_StringWithBadSupplementaryChars_4_oe() {
        assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
    }

    @Test
    public void testContains_StringWithBadSupplementaryChars_5_oe() {
        assertTrue(StringUtils.contains(CharU20001, CharUSuppCharLow));
    }

    @Test
    public void testContains_StringWithBadSupplementaryChars_6_oe() {
        assertTrue(StringUtils.contains(CharU20001 + CharUSuppCharLow + "a", "a"));
    }

    @Test
    public void testContains_StringWithBadSupplementaryChars_7_oe() {
        assertTrue(StringUtils.contains(CharU20001 + CharUSuppCharHigh + "a", "a"));
    }

    @Test
    public void testContains_StringWithSupplementaryChars_1_oe() {
        assertTrue(StringUtils.contains(CharU20000 + CharU20001, CharU20000));
    }

    @Test
    public void testContains_StringWithSupplementaryChars_2_oe() {
        assertTrue(StringUtils.contains(CharU20000 + CharU20001, CharU20001));
    }

    @Test
    public void testContains_StringWithSupplementaryChars_3_oe() {
        assertTrue(StringUtils.contains(CharU20000, CharU20000));
    }

    @Test
    public void testContains_StringWithSupplementaryChars_4_oe() {
        assertFalse(StringUtils.contains(CharU20000, CharU20001));
    }

    @Test
    public void testContainsAny_StringCharArray_1_oe() {
        assertFalse(StringUtils.containsAny(null, (char[]) null));
    }

    @Test
    public void testContainsAny_StringCharArray_2_oe() {
        assertFalse(StringUtils.containsAny(null, new char[0]));
    }

    @Test
    public void testContainsAny_StringCharArray_3_oe() {
        assertFalse(StringUtils.containsAny(null, 'a', 'b'));
    }

    @Test
    public void testContainsAny_StringCharArray_4_oe() {

        assertFalse(StringUtils.containsAny("", (char[]) null));
    }

    @Test
    public void testContainsAny_StringCharArray_5_oe() {

        assertFalse(StringUtils.containsAny("", new char[0]));
    }

    @Test
    public void testContainsAny_StringCharArray_6_oe() {

        assertFalse(StringUtils.containsAny("", 'a', 'b'));
    }

    @Test
    public void testContainsAny_StringCharArray_7_oe() {


        assertFalse(StringUtils.containsAny("zzabyycdxx", (char[]) null));
    }

    @Test
    public void testContainsAny_StringCharArray_8_oe() {


        assertFalse(StringUtils.containsAny("zzabyycdxx", new char[0]));
    }

    @Test
    public void testContainsAny_StringCharArray_9_oe() {


        assertTrue(StringUtils.containsAny("zzabyycdxx", 'z', 'a'));
    }

    @Test
    public void testContainsAny_StringCharArray_10_oe() {


        assertTrue(StringUtils.containsAny("zzabyycdxx", 'b', 'y'));
    }

    @Test
    public void testContainsAny_StringCharArray_11_oe() {


        assertTrue(StringUtils.containsAny("zzabyycdxx", 'z', 'y'));
    }

    @Test
    public void testContainsAny_StringCharArray_12_oe() {


        assertFalse(StringUtils.containsAny("ab", 'z'));
    }

    @Test
    public void testContainsAny_StringCharArrayWithBadSupplementaryChars_1_oe() {
        assertFalse(StringUtils.containsAny(CharUSuppCharHigh, CharU20001.toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithBadSupplementaryChars_2_oe() {
        assertFalse(StringUtils.containsAny("abc" + CharUSuppCharHigh + "xyz", CharU20001.toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithBadSupplementaryChars_3_oe() {
        assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
    }

    @Test
    public void testContainsAny_StringCharArrayWithBadSupplementaryChars_4_oe() {
        assertFalse(StringUtils.containsAny(CharUSuppCharLow, CharU20001.toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithBadSupplementaryChars_5_oe() {
        assertFalse(StringUtils.containsAny(CharU20001, CharUSuppCharHigh.toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithBadSupplementaryChars_6_oe() {
        assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
    }

    @Test
    public void testContainsAny_StringCharArrayWithBadSupplementaryChars_7_oe() {
        assertTrue(StringUtils.containsAny(CharU20001, CharUSuppCharLow.toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_1_oe() {
        assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20000.toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_2_oe() {
        assertTrue(StringUtils.containsAny("a" + CharU20000 + CharU20001, "a".toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_3_oe() {
        assertTrue(StringUtils.containsAny(CharU20000 + "a" + CharU20001, "a".toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_4_oe() {
        assertTrue(StringUtils.containsAny(CharU20000 + CharU20001 + "a", "a".toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_5_oe() {
        assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20001.toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_6_oe() {
        assertTrue(StringUtils.containsAny(CharU20000, CharU20000.toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_7_oe() {
        assertEquals(-1, CharU20000.indexOf(CharU20001));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_8_oe() {
        assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_9_oe() {
        assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_10_oe() {
        assertFalse(StringUtils.containsAny(CharU20000, CharU20001.toCharArray()));
    }

    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars_11_oe() {
        assertFalse(StringUtils.containsAny(CharU20001, CharU20000.toCharArray()));
    }

    @Test
    public void testContainsAny_StringString_1_oe() {
        assertFalse(StringUtils.containsAny(null, (String) null));
    }

    @Test
    public void testContainsAny_StringString_2_oe() {
        assertFalse(StringUtils.containsAny(null, ""));
    }

    @Test
    public void testContainsAny_StringString_3_oe() {
        assertFalse(StringUtils.containsAny(null, "ab"));
    }

    @Test
    public void testContainsAny_StringString_4_oe() {

        assertFalse(StringUtils.containsAny("", (String) null));
    }

    @Test
    public void testContainsAny_StringString_5_oe() {

        assertFalse(StringUtils.containsAny("", ""));
    }

    @Test
    public void testContainsAny_StringString_6_oe() {

        assertFalse(StringUtils.containsAny("", "ab"));
    }

    @Test
    public void testContainsAny_StringString_7_oe() {


        assertFalse(StringUtils.containsAny("zzabyycdxx", (String) null));
    }

    @Test
    public void testContainsAny_StringString_8_oe() {


        assertFalse(StringUtils.containsAny("zzabyycdxx", ""));
    }

    @Test
    public void testContainsAny_StringString_9_oe() {


        assertTrue(StringUtils.containsAny("zzabyycdxx", "za"));
    }

    @Test
    public void testContainsAny_StringString_10_oe() {


        assertTrue(StringUtils.containsAny("zzabyycdxx", "by"));
    }

    @Test
    public void testContainsAny_StringString_11_oe() {


        assertTrue(StringUtils.containsAny("zzabyycdxx", "zy"));
    }

    @Test
    public void testContainsAny_StringString_12_oe() {


        assertFalse(StringUtils.containsAny("ab", "z"));
    }

    @Test
    public void testContainsAny_StringStringArray_1_oe() {
        assertFalse(StringUtils.containsAny(null, (String[]) null));
    }

    @Test
    public void testContainsAny_StringStringArray_2_oe() {
        assertFalse(StringUtils.containsAny(null, new String[0]));
    }

    @Test
    public void testContainsAny_StringStringArray_3_oe() {
        assertFalse(StringUtils.containsAny(null, new String[] { "hello" }));
    }

    @Test
    public void testContainsAny_StringStringArray_4_oe() {
        assertFalse(StringUtils.containsAny("", (String[]) null));
    }

    @Test
    public void testContainsAny_StringStringArray_5_oe() {
        assertFalse(StringUtils.containsAny("", new String[0]));
    }

    @Test
    public void testContainsAny_StringStringArray_6_oe() {
        assertFalse(StringUtils.containsAny("", new String[] { "hello" }));
    }

    @Test
    public void testContainsAny_StringStringArray_7_oe() {
        assertFalse(StringUtils.containsAny("hello, goodbye", (String[]) null));
    }

    @Test
    public void testContainsAny_StringStringArray_8_oe() {
        assertFalse(StringUtils.containsAny("hello, goodbye", new String[0]));
    }

    @Test
    public void testContainsAny_StringStringArray_9_oe() {
        assertTrue(StringUtils.containsAny("hello, goodbye", new String[]{"hello", "goodbye"}));
    }

    @Test
    public void testContainsAny_StringStringArray_10_oe() {
        assertTrue(StringUtils.containsAny("hello, goodbye", new String[]{"hello", "Goodbye"}));
    }

    @Test
    public void testContainsAny_StringStringArray_11_oe() {
        assertFalse(StringUtils.containsAny("hello, goodbye", new String[]{"Hello", "Goodbye"}));
    }

    @Test
    public void testContainsAny_StringStringArray_12_oe() {
        assertFalse(StringUtils.containsAny("hello, goodbye", new String[]{"Hello", null}));
    }

    @Test
    public void testContainsAny_StringStringArray_13_oe() {
        assertFalse(StringUtils.containsAny("hello, null", new String[] { "Hello", null }));
    }

    @Test
    public void testContainsAny_StringStringArray_14_oe() {
        assertTrue(StringUtils.containsAny("abcd", "ab", null));
    }

    @Test
    public void testContainsAny_StringStringArray_15_oe() {
        assertTrue(StringUtils.containsAny("abcd", "ab", "cd"));
    }

    @Test
    public void testContainsAny_StringStringArray_16_oe() {
        assertTrue(StringUtils.containsAny("abc", "d", "abc"));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_1_oe() {
        assertFalse(StringUtils.containsAnyIgnoreCase(null, (String[]) null));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_2_oe() {
        assertFalse(StringUtils.containsAnyIgnoreCase(null, new String[0]));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_3_oe() {
        assertFalse(StringUtils.containsAnyIgnoreCase(null, new String[] { "hello" }));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_4_oe() {
        assertFalse(StringUtils.containsAnyIgnoreCase("", (String[]) null));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_5_oe() {
        assertFalse(StringUtils.containsAnyIgnoreCase("", new String[0]));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_6_oe() {
        assertFalse(StringUtils.containsAnyIgnoreCase("", new String[] { "hello" }));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_7_oe() {
        assertFalse(StringUtils.containsAnyIgnoreCase("hello, goodbye", (String[]) null));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_8_oe() {
        assertFalse(StringUtils.containsAnyIgnoreCase("hello, goodbye", new String[0]));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_9_oe() {
        assertTrue(StringUtils.containsAnyIgnoreCase("hello, goodbye", new String[]{"hello", "goodbye"}));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_10_oe() {
        assertTrue(StringUtils.containsAnyIgnoreCase("hello, goodbye", new String[]{"hello", "Goodbye"}));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_11_oe() {
        assertTrue(StringUtils.containsAnyIgnoreCase("hello, goodbye", new String[]{"Hello", "Goodbye"}));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_12_oe() {
        assertTrue(StringUtils.containsAnyIgnoreCase("hello, goodbye", new String[]{"Hello", null}));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_13_oe() {
        assertTrue(StringUtils.containsAnyIgnoreCase("hello, null", new String[] { "Hello", null }));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_14_oe() {
        assertTrue(StringUtils.containsAnyIgnoreCase("abcd", "ab", null));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_15_oe() {
        assertTrue(StringUtils.containsAnyIgnoreCase("abcd", "ab", "cd"));
    }

    @Test
    public void testContainsAnyIgnoreCase_StringStringArray_16_oe() {
        assertTrue(StringUtils.containsAnyIgnoreCase("abc", "d", "abc"));
    }

    @Test
    public void testContainsAny_StringWithBadSupplementaryChars_1_oe() {
        assertFalse(StringUtils.containsAny(CharUSuppCharHigh, CharU20001));
    }

    @Test
    public void testContainsAny_StringWithBadSupplementaryChars_2_oe() {
        assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
    }

    @Test
    public void testContainsAny_StringWithBadSupplementaryChars_3_oe() {
        assertFalse(StringUtils.containsAny(CharUSuppCharLow, CharU20001));
    }

    @Test
    public void testContainsAny_StringWithBadSupplementaryChars_4_oe() {
        assertFalse(StringUtils.containsAny(CharU20001, CharUSuppCharHigh));
    }

    @Test
    public void testContainsAny_StringWithBadSupplementaryChars_5_oe() {
        assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
    }

    @Test
    public void testContainsAny_StringWithBadSupplementaryChars_6_oe() {
        assertTrue(StringUtils.containsAny(CharU20001, CharUSuppCharLow));
    }

    @Test
    public void testContainsAny_StringWithSupplementaryChars_1_oe() {
        assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20000));
    }

    @Test
    public void testContainsAny_StringWithSupplementaryChars_2_oe() {
        assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20001));
    }

    @Test
    public void testContainsAny_StringWithSupplementaryChars_3_oe() {
        assertTrue(StringUtils.containsAny(CharU20000, CharU20000));
    }

    @Test
    public void testContainsAny_StringWithSupplementaryChars_4_oe() {
        assertEquals(-1, CharU20000.indexOf(CharU20001));
    }

    @Test
    public void testContainsAny_StringWithSupplementaryChars_5_oe() {
        assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
    }

    @Test
    public void testContainsAny_StringWithSupplementaryChars_6_oe() {
        assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
    }

    @Test
    public void testContainsAny_StringWithSupplementaryChars_7_oe() {
        assertFalse(StringUtils.containsAny(CharU20000, CharU20001));
    }

    @Test
    public void testContainsAny_StringWithSupplementaryChars_8_oe() {
        assertFalse(StringUtils.containsAny(CharU20001, CharU20000));
    }

    @Test
    public void testContainsIgnoreCase_LocaleIndependence_1_oe() {
        final Locale[] locales = { Locale.ENGLISH, new Locale("tr"), Locale.getDefault() };

        final String[][] tdata = { { "i", "I" }, { "I", "i" }, { "\u03C2", "\u03C3" }, { "\u03A3", "\u03C2" },
            { "\u03A3", "\u03C3" }, };

        final String[][] fdata = { { "\u00DF", "SS" }, };

        for (final Locale testLocale : locales) {
            Locale.setDefault(testLocale);
            for (int j = 0; j < tdata.length; j++) {
                assertTrue(StringUtils.containsIgnoreCase(tdata[j][0],tdata[j][1]),Locale.getDefault()+ ": " + j + " " + tdata[j][0] + " " + tdata[j][1]);
    }
    }
    }

    @Test
    public void testContainsIgnoreCase_LocaleIndependence_2_oe() {
        final Locale[] locales = { Locale.ENGLISH, new Locale("tr"), Locale.getDefault() };

        final String[][] tdata = { { "i", "I" }, { "I", "i" }, { "\u03C2", "\u03C3" }, { "\u03A3", "\u03C2" },
            { "\u03A3", "\u03C3" }, };

        final String[][] fdata = { { "\u00DF", "SS" }, };

        for (final Locale testLocale : locales) {
            Locale.setDefault(testLocale);
            for (int j = 0; j < tdata.length; j++) {
            }
            for (int j = 0; j < fdata.length; j++) {
                assertFalse(StringUtils.containsIgnoreCase(fdata[j][0],fdata[j][1]),Locale.getDefault()+ ": " + j + " " + fdata[j][0] + " " + fdata[j][1]);
    }
    }
    }

    @Test
    public void testContainsIgnoreCase_StringString_1_oe() {
        assertFalse(StringUtils.containsIgnoreCase(null, null));
    }

    @Test
    public void testContainsIgnoreCase_StringString_2_oe() {

        assertFalse(StringUtils.containsIgnoreCase(null, ""));
    }

    @Test
    public void testContainsIgnoreCase_StringString_3_oe() {

        assertFalse(StringUtils.containsIgnoreCase(null, "a"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_4_oe() {

        assertFalse(StringUtils.containsIgnoreCase(null, "abc"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_5_oe() {


        assertFalse(StringUtils.containsIgnoreCase("", null));
    }

    @Test
    public void testContainsIgnoreCase_StringString_6_oe() {


        assertFalse(StringUtils.containsIgnoreCase("a", null));
    }

    @Test
    public void testContainsIgnoreCase_StringString_7_oe() {


        assertFalse(StringUtils.containsIgnoreCase("abc", null));
    }

    @Test
    public void testContainsIgnoreCase_StringString_8_oe() {



        assertTrue(StringUtils.containsIgnoreCase("", ""));
    }

    @Test
    public void testContainsIgnoreCase_StringString_9_oe() {



        assertTrue(StringUtils.containsIgnoreCase("a", ""));
    }

    @Test
    public void testContainsIgnoreCase_StringString_10_oe() {



        assertTrue(StringUtils.containsIgnoreCase("abc", ""));
    }

    @Test
    public void testContainsIgnoreCase_StringString_11_oe() {




        assertFalse(StringUtils.containsIgnoreCase("", "a"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_12_oe() {




        assertTrue(StringUtils.containsIgnoreCase("a", "a"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_13_oe() {




        assertTrue(StringUtils.containsIgnoreCase("abc", "a"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_14_oe() {




        assertFalse(StringUtils.containsIgnoreCase("", "A"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_15_oe() {




        assertTrue(StringUtils.containsIgnoreCase("a", "A"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_16_oe() {




        assertTrue(StringUtils.containsIgnoreCase("abc", "A"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_17_oe() {





        assertFalse(StringUtils.containsIgnoreCase("", "abc"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_18_oe() {





        assertFalse(StringUtils.containsIgnoreCase("a", "abc"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_19_oe() {





        assertTrue(StringUtils.containsIgnoreCase("xabcz", "abc"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_20_oe() {





        assertFalse(StringUtils.containsIgnoreCase("", "ABC"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_21_oe() {





        assertFalse(StringUtils.containsIgnoreCase("a", "ABC"));
    }

    @Test
    public void testContainsIgnoreCase_StringString_22_oe() {





        assertTrue(StringUtils.containsIgnoreCase("xabcz", "ABC"));
    }

    @Test
    public void testContainsNone_CharArray_1_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone(null, (char[]) null));
    }

    @Test
    public void testContainsNone_CharArray_2_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone("", (char[]) null));
    }

    @Test
    public void testContainsNone_CharArray_3_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone(null, emptyChars));
    }

    @Test
    public void testContainsNone_CharArray_4_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone(str1, emptyChars));
    }

    @Test
    public void testContainsNone_CharArray_5_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone("", emptyChars));
    }

    @Test
    public void testContainsNone_CharArray_6_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone("", chars1));
    }

    @Test
    public void testContainsNone_CharArray_7_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone(str1, chars1));
    }

    @Test
    public void testContainsNone_CharArray_8_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone(str1, chars2));
    }

    @Test
    public void testContainsNone_CharArray_9_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone(str1, chars3));
    }

    @Test
    public void testContainsNone_CharArray_10_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsNone(str2, chars1));
    }

    @Test
    public void testContainsNone_CharArray_11_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone(str2, chars2));
    }

    @Test
    public void testContainsNone_CharArray_12_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone(str2, chars3));
    }

    @Test
    public void testContainsNone_CharArray_13_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsNone(str3, chars1));
    }

    @Test
    public void testContainsNone_CharArray_14_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsNone(str3, chars2));
    }

    @Test
    public void testContainsNone_CharArray_15_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsNone(str3, chars3));
    }

    @Test
    public void testContainsNone_CharArrayWithBadSupplementaryChars_1_oe() {
        assertTrue(StringUtils.containsNone(CharUSuppCharHigh, CharU20001.toCharArray()));
    }

    @Test
    public void testContainsNone_CharArrayWithBadSupplementaryChars_2_oe() {
        assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
    }

    @Test
    public void testContainsNone_CharArrayWithBadSupplementaryChars_3_oe() {
        assertTrue(StringUtils.containsNone(CharUSuppCharLow, CharU20001.toCharArray()));
    }

    @Test
    public void testContainsNone_CharArrayWithBadSupplementaryChars_4_oe() {
        assertEquals(-1, CharU20001.indexOf(CharUSuppCharHigh));
    }

    @Test
    public void testContainsNone_CharArrayWithBadSupplementaryChars_5_oe() {
        assertTrue(StringUtils.containsNone(CharU20001, CharUSuppCharHigh.toCharArray()));
    }

    @Test
    public void testContainsNone_CharArrayWithBadSupplementaryChars_6_oe() {
        assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
    }

    @Test
    public void testContainsNone_CharArrayWithBadSupplementaryChars_7_oe() {
        assertFalse(StringUtils.containsNone(CharU20001, CharUSuppCharLow.toCharArray()));
    }

    @Test
    public void testContainsNone_CharArrayWithSupplementaryChars_1_oe() {
        assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20000.toCharArray()));
    }

    @Test
    public void testContainsNone_CharArrayWithSupplementaryChars_2_oe() {
        assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20001.toCharArray()));
    }

    @Test
    public void testContainsNone_CharArrayWithSupplementaryChars_3_oe() {
        assertFalse(StringUtils.containsNone(CharU20000, CharU20000.toCharArray()));
    }

    @Test
    public void testContainsNone_CharArrayWithSupplementaryChars_4_oe() {
        assertEquals(-1, CharU20000.indexOf(CharU20001));
    }

    @Test
    public void testContainsNone_CharArrayWithSupplementaryChars_5_oe() {
        assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
    }

    @Test
    public void testContainsNone_CharArrayWithSupplementaryChars_6_oe() {
        assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
    }

    @Test
    public void testContainsNone_CharArrayWithSupplementaryChars_7_oe() {
        assertTrue(StringUtils.containsNone(CharU20000, CharU20001.toCharArray()));
    }

    @Test
    public void testContainsNone_CharArrayWithSupplementaryChars_8_oe() {
        assertTrue(StringUtils.containsNone(CharU20001, CharU20000.toCharArray()));
    }

    @Test
    public void testContainsNone_String_1_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone(null, (String) null));
    }

    @Test
    public void testContainsNone_String_2_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone("", (String) null));
    }

    @Test
    public void testContainsNone_String_3_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone(null, ""));
    }

    @Test
    public void testContainsNone_String_4_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone(str1, ""));
    }

    @Test
    public void testContainsNone_String_5_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone("", ""));
    }

    @Test
    public void testContainsNone_String_6_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone("", chars1));
    }

    @Test
    public void testContainsNone_String_7_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone(str1, chars1));
    }

    @Test
    public void testContainsNone_String_8_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone(str1, chars2));
    }

    @Test
    public void testContainsNone_String_9_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone(str1, chars3));
    }

    @Test
    public void testContainsNone_String_10_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertFalse(StringUtils.containsNone(str2, chars1));
    }

    @Test
    public void testContainsNone_String_11_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone(str2, chars2));
    }

    @Test
    public void testContainsNone_String_12_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone(str2, chars3));
    }

    @Test
    public void testContainsNone_String_13_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertFalse(StringUtils.containsNone(str3, chars1));
    }

    @Test
    public void testContainsNone_String_14_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertFalse(StringUtils.containsNone(str3, chars2));
    }

    @Test
    public void testContainsNone_String_15_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        assertTrue(StringUtils.containsNone(str3, chars3));
    }

    @Test
    public void testContainsNone_StringWithBadSupplementaryChars_1_oe() {
        assertTrue(StringUtils.containsNone(CharUSuppCharHigh, CharU20001));
    }

    @Test
    public void testContainsNone_StringWithBadSupplementaryChars_2_oe() {
        assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
    }

    @Test
    public void testContainsNone_StringWithBadSupplementaryChars_3_oe() {
        assertTrue(StringUtils.containsNone(CharUSuppCharLow, CharU20001));
    }

    @Test
    public void testContainsNone_StringWithBadSupplementaryChars_4_oe() {
        assertEquals(-1, CharU20001.indexOf(CharUSuppCharHigh));
    }

    @Test
    public void testContainsNone_StringWithBadSupplementaryChars_5_oe() {
        assertTrue(StringUtils.containsNone(CharU20001, CharUSuppCharHigh));
    }

    @Test
    public void testContainsNone_StringWithBadSupplementaryChars_6_oe() {
        assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
    }

    @Test
    public void testContainsNone_StringWithBadSupplementaryChars_7_oe() {
        assertFalse(StringUtils.containsNone(CharU20001, CharUSuppCharLow));
    }

    @Test
    public void testContainsNone_StringWithSupplementaryChars_1_oe() {
        assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20000));
    }

    @Test
    public void testContainsNone_StringWithSupplementaryChars_2_oe() {
        assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20001));
    }

    @Test
    public void testContainsNone_StringWithSupplementaryChars_3_oe() {
        assertFalse(StringUtils.containsNone(CharU20000, CharU20000));
    }

    @Test
    public void testContainsNone_StringWithSupplementaryChars_4_oe() {
        assertEquals(-1, CharU20000.indexOf(CharU20001));
    }

    @Test
    public void testContainsNone_StringWithSupplementaryChars_5_oe() {
        assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
    }

    @Test
    public void testContainsNone_StringWithSupplementaryChars_6_oe() {
        assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
    }

    @Test
    public void testContainsNone_StringWithSupplementaryChars_7_oe() {
        assertTrue(StringUtils.containsNone(CharU20000, CharU20001));
    }

    @Test
    public void testContainsNone_StringWithSupplementaryChars_8_oe() {
        assertTrue(StringUtils.containsNone(CharU20001, CharU20000));
    }

    @Test
    public void testContainsOnly_CharArray_1_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsOnly(null, (char[]) null));
    }

    @Test
    public void testContainsOnly_CharArray_2_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsOnly("", (char[]) null));
    }

    @Test
    public void testContainsOnly_CharArray_3_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsOnly(null, emptyChars));
    }

    @Test
    public void testContainsOnly_CharArray_4_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsOnly(str1, emptyChars));
    }

    @Test
    public void testContainsOnly_CharArray_5_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsOnly("", emptyChars));
    }

    @Test
    public void testContainsOnly_CharArray_6_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsOnly("", chars1));
    }

    @Test
    public void testContainsOnly_CharArray_7_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsOnly(str1, chars1));
    }

    @Test
    public void testContainsOnly_CharArray_8_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsOnly(str1, chars2));
    }

    @Test
    public void testContainsOnly_CharArray_9_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsOnly(str1, chars3));
    }

    @Test
    public void testContainsOnly_CharArray_10_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsOnly(str2, chars1));
    }

    @Test
    public void testContainsOnly_CharArray_11_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsOnly(str2, chars2));
    }

    @Test
    public void testContainsOnly_CharArray_12_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsOnly(str2, chars3));
    }

    @Test
    public void testContainsOnly_CharArray_13_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsOnly(str3, chars1));
    }

    @Test
    public void testContainsOnly_CharArray_14_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertFalse(StringUtils.containsOnly(str3, chars2));
    }

    @Test
    public void testContainsOnly_CharArray_15_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        assertTrue(StringUtils.containsOnly(str3, chars3));
    }

    @Test
    public void testContainsOnly_String_1_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertFalse(StringUtils.containsOnly(null, (String) null));
    }

    @Test
    public void testContainsOnly_String_2_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertFalse(StringUtils.containsOnly("", (String) null));
    }

    @Test
    public void testContainsOnly_String_3_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertFalse(StringUtils.containsOnly(null, ""));
    }

    @Test
    public void testContainsOnly_String_4_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertFalse(StringUtils.containsOnly(str1, ""));
    }

    @Test
    public void testContainsOnly_String_5_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertTrue(StringUtils.containsOnly("", ""));
    }

    @Test
    public void testContainsOnly_String_6_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertTrue(StringUtils.containsOnly("", chars1));
    }

    @Test
    public void testContainsOnly_String_7_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertFalse(StringUtils.containsOnly(str1, chars1));
    }

    @Test
    public void testContainsOnly_String_8_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertTrue(StringUtils.containsOnly(str1, chars2));
    }

    @Test
    public void testContainsOnly_String_9_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertTrue(StringUtils.containsOnly(str1, chars3));
    }

    @Test
    public void testContainsOnly_String_10_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertTrue(StringUtils.containsOnly(str2, chars1));
    }

    @Test
    public void testContainsOnly_String_11_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertFalse(StringUtils.containsOnly(str2, chars2));
    }

    @Test
    public void testContainsOnly_String_12_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertTrue(StringUtils.containsOnly(str2, chars3));
    }

    @Test
    public void testContainsOnly_String_13_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertFalse(StringUtils.containsOnly(str3, chars1));
    }

    @Test
    public void testContainsOnly_String_14_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertFalse(StringUtils.containsOnly(str3, chars2));
    }

    @Test
    public void testContainsOnly_String_15_oe() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        assertTrue(StringUtils.containsOnly(str3, chars3));
    }

    @Test
    public void testContainsWhitespace_1_oe() {
        assertFalse( StringUtils.containsWhitespace("") );
    }

    @Test
    public void testContainsWhitespace_2_oe() {
        assertTrue( StringUtils.containsWhitespace(" ") );
    }

    @Test
    public void testContainsWhitespace_3_oe() {
        assertFalse( StringUtils.containsWhitespace("a") );
    }

    @Test
    public void testContainsWhitespace_4_oe() {
        assertTrue( StringUtils.containsWhitespace("a ") );
    }

    @Test
    public void testContainsWhitespace_5_oe() {
        assertTrue( StringUtils.containsWhitespace(" a") );
    }

    @Test
    public void testContainsWhitespace_6_oe() {
        assertTrue( StringUtils.containsWhitespace("a\t") );
    }

    @Test
    public void testContainsWhitespace_7_oe() {
        assertTrue( StringUtils.containsWhitespace("\n") );
    }

}
