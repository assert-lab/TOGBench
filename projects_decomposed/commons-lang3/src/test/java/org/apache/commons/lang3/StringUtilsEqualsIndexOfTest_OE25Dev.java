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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.CharBuffer;
import java.util.Locale;

import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Equals/IndexOf methods
 */
public class StringUtilsEqualsIndexOfTest_OE25Dev  {
    private static final String BAR = "bar";
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

    private static final String FOO = "foo";

    private static final String FOOBAR = "foobar";

    private static final String[] FOOBAR_SUB_ARRAY = new String[] {"ob", "ba"};

    // The purpose of this class is to test StringUtils#equals(CharSequence, CharSequence)
    // with a CharSequence implementation whose equals(Object) override requires that the
    // other object be an instance of CustomCharSequence, even though, as char sequences,
    // `seq` may equal the other object.
    private static class CustomCharSequence implements CharSequence {
        private final CharSequence seq;

        CustomCharSequence(final CharSequence seq) {
            this.seq = seq;
        }

        @Override
        public char charAt(final int index) {
            return seq.charAt(index);
        }

        @Override
        public int length() {
            return seq.length();
        }

        @Override
        public CharSequence subSequence(final int start, final int end) {
            return new CustomCharSequence(seq.subSequence(start, end));
        }

        @Override
        public boolean equals(final Object obj) {
            if (!(obj instanceof CustomCharSequence)) {
                return false;
            }
            final CustomCharSequence other = (CustomCharSequence) obj;
            return seq.equals(other.seq);
        }

        @Override
        public int hashCode() {
            return seq.hashCode();
        }

        @Override
        public String toString() {
            return seq.toString();
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */

    @Test
    // Non-overlapping test
    public void testLANG1241_1() {
        //                                          0  3  6
        assertEquals(0, StringUtils.ordinalIndexOf("abaabaab", "ab", 1));
        assertEquals(3, StringUtils.ordinalIndexOf("abaabaab", "ab", 2));
        assertEquals(6, StringUtils.ordinalIndexOf("abaabaab", "ab", 3));
    }

    @Test
    // Overlapping matching test
    public void testLANG1241_2() {
        //                                          0 2 4
        assertEquals(0, StringUtils.ordinalIndexOf("abababa", "aba", 1));
        assertEquals(2, StringUtils.ordinalIndexOf("abababa", "aba", 2));
        assertEquals(4, StringUtils.ordinalIndexOf("abababa", "aba", 3));
        assertEquals(0, StringUtils.ordinalIndexOf("abababab", "abab", 1));
        assertEquals(2, StringUtils.ordinalIndexOf("abababab", "abab", 2));
        assertEquals(4, StringUtils.ordinalIndexOf("abababab", "abab", 3));
    }

@Test
    public void testCustomCharSequence_1_oe() {
        assertThat(new CustomCharSequence(FOO), IsNot.<CharSequence>not(FOO));
    }

@Test
    public void testCustomCharSequence_2_oe() {
        // removed other assertion
        assertThat(FOO, IsNot.<CharSequence>not(new CustomCharSequence(FOO)));
    }

@Test
    public void testCustomCharSequence_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(new CustomCharSequence(FOO), new CustomCharSequence(FOO));
    }

@Test
    public void testEquals_1_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        assertTrue(StringUtils.equals(null, null));
    }

@Test
    public void testEquals_2_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        // removed other assertion
        assertTrue(StringUtils.equals(fooCs, fooCs));
    }

@Test
    public void testEquals_3_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equals(fooCs, new StringBuilder(FOO)));
    }

@Test
    public void testEquals_4_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equals(fooCs, new String(new char[] { 'f', 'o', 'o' })));
    }

@Test
    public void testEquals_5_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equals(fooCs, new CustomCharSequence(FOO)));
    }

@Test
    public void testEquals_6_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equals(new CustomCharSequence(FOO), fooCs));
    }

@Test
    public void testEquals_7_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equals(fooCs, new String(new char[] { 'f', 'O', 'O' })));
    }

@Test
    public void testEquals_8_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equals(fooCs, barCs));
    }

@Test
    public void testEquals_9_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equals(fooCs, null));
    }

@Test
    public void testEquals_10_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equals(null, fooCs));
    }

@Test
    public void testEquals_11_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
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
        assertFalse(StringUtils.equals(fooCs, foobarCs));
    }

@Test
    public void testEquals_12_oe() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
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
        assertFalse(StringUtils.equals(foobarCs, fooCs));
    }

@Test
    public void testEqualsOnStrings_1_oe() {
        assertTrue(StringUtils.equals(null, null));
    }

@Test
    public void testEqualsOnStrings_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.equals(FOO, FOO));
    }

@Test
    public void testEqualsOnStrings_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equals(FOO, new String(new char[] { 'f', 'o', 'o' })));
    }

@Test
    public void testEqualsOnStrings_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equals(FOO, new String(new char[] { 'f', 'O', 'O' })));
    }

@Test
    public void testEqualsOnStrings_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equals(FOO, BAR));
    }

@Test
    public void testEqualsOnStrings_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equals(FOO, null));
    }

@Test
    public void testEqualsOnStrings_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equals(null, FOO));
    }

@Test
    public void testEqualsOnStrings_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equals(FOO, FOOBAR));
    }

@Test
    public void testEqualsOnStrings_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equals(FOOBAR, FOO));
    }

@Test
    public void testEqualsIgnoreCase_1_oe() {
        assertTrue(StringUtils.equalsIgnoreCase(null, null));
    }

@Test
    public void testEqualsIgnoreCase_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.equalsIgnoreCase(FOO, FOO));
    }

@Test
    public void testEqualsIgnoreCase_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equalsIgnoreCase(FOO, new String(new char[] { 'f', 'o', 'o' })));
    }

@Test
    public void testEqualsIgnoreCase_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equalsIgnoreCase(FOO, new String(new char[] { 'f', 'O', 'O' })));
    }

@Test
    public void testEqualsIgnoreCase_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsIgnoreCase(FOO, BAR));
    }

@Test
    public void testEqualsIgnoreCase_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsIgnoreCase(FOO, null));
    }

@Test
    public void testEqualsIgnoreCase_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsIgnoreCase(null, FOO));
    }

@Test
    public void testEqualsIgnoreCase_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equalsIgnoreCase("", ""));
    }

@Test
    public void testEqualsIgnoreCase_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsIgnoreCase("abcd", "abcd "));
    }

@Test
    public void testEqualsAny_1_oe() {
        assertFalse(StringUtils.equalsAny(FOO));
    }

@Test
    public void testEqualsAny_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.equalsAny(FOO, new String[]{}));
    }

@Test
    public void testEqualsAny_3_oe() {
        // removed other assertion
        // removed other assertion

        assertTrue(StringUtils.equalsAny(FOO, FOO));
    }

@Test
    public void testEqualsAny_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(StringUtils.equalsAny(FOO, BAR, new String(new char[] { 'f', 'o', 'o' })));
    }

@Test
    public void testEqualsAny_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAny(FOO, BAR, new String(new char[] { 'f', 'O', 'O' })));
    }

@Test
    public void testEqualsAny_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAny(FOO, BAR));
    }

@Test
    public void testEqualsAny_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAny(FOO, BAR, null));
    }

@Test
    public void testEqualsAny_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAny(null, FOO));
    }

@Test
    public void testEqualsAny_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAny(FOO, FOOBAR));
    }

@Test
    public void testEqualsAny_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAny(FOOBAR, FOO));
    }

@Test
    public void testEqualsAny_11_oe() {
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

        assertTrue(StringUtils.equalsAny(null, null, null));
    }

@Test
    public void testEqualsAny_12_oe() {
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
        assertFalse(StringUtils.equalsAny(null, FOO, BAR, FOOBAR));
    }

@Test
    public void testEqualsAny_13_oe() {
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
        assertFalse(StringUtils.equalsAny(FOO, null, BAR));
    }

@Test
    public void testEqualsAny_14_oe() {
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
        assertTrue(StringUtils.equalsAny(FOO, BAR, null, "", FOO, BAR));
    }

@Test
    public void testEqualsAny_15_oe() {
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
        assertFalse(StringUtils.equalsAny(FOO, FOO.toUpperCase(Locale.ROOT)));
    }

@Test
    public void testEqualsAny_16_oe() {
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

        assertFalse(StringUtils.equalsAny(null, (CharSequence[]) null));
    }

@Test
    public void testEqualsAny_17_oe() {
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
        assertTrue(StringUtils.equalsAny(FOO, new CustomCharSequence("foo")));
    }

@Test
    public void testEqualsAny_18_oe() {
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
        // removed other assertion
        assertTrue(StringUtils.equalsAny(FOO, new StringBuilder("foo")));
    }

@Test
    public void testEqualsAny_19_oe() {
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
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAny(FOO, new CustomCharSequence("fOo")));
    }

@Test
    public void testEqualsAny_20_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAny(FOO, new StringBuilder("fOo")));
    }

@Test
    public void testEqualsAnyIgnoreCase_1_oe() {
        assertFalse(StringUtils.equalsAnyIgnoreCase(FOO));
    }

@Test
    public void testEqualsAnyIgnoreCase_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.equalsAnyIgnoreCase(FOO, new String[]{}));
    }

@Test
    public void testEqualsAnyIgnoreCase_3_oe() {
        // removed other assertion
        // removed other assertion

        assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, FOO));
    }

@Test
    public void testEqualsAnyIgnoreCase_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, FOO.toUpperCase(Locale.ROOT)));
    }

@Test
    public void testEqualsAnyIgnoreCase_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, FOO, new String(new char[]{'f', 'o', 'o'})));
    }

@Test
    public void testEqualsAnyIgnoreCase_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, BAR, new String(new char[]{'f', 'O', 'O'})));
    }

@Test
    public void testEqualsAnyIgnoreCase_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAnyIgnoreCase(FOO, BAR));
    }

@Test
    public void testEqualsAnyIgnoreCase_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAnyIgnoreCase(FOO, BAR, null));
    }

@Test
    public void testEqualsAnyIgnoreCase_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAnyIgnoreCase(null, FOO));
    }

@Test
    public void testEqualsAnyIgnoreCase_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.equalsAnyIgnoreCase(FOO, FOOBAR));
    }

@Test
    public void testEqualsAnyIgnoreCase_11_oe() {
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
        assertFalse(StringUtils.equalsAnyIgnoreCase(FOOBAR, FOO));
    }

@Test
    public void testEqualsAnyIgnoreCase_12_oe() {
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

        assertTrue(StringUtils.equalsAnyIgnoreCase(null, null, null));
    }

@Test
    public void testEqualsAnyIgnoreCase_13_oe() {
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
        assertFalse(StringUtils.equalsAnyIgnoreCase(null, FOO, BAR, FOOBAR));
    }

@Test
    public void testEqualsAnyIgnoreCase_14_oe() {
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
        assertFalse(StringUtils.equalsAnyIgnoreCase(FOO, null, BAR));
    }

@Test
    public void testEqualsAnyIgnoreCase_15_oe() {
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
        assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, BAR, null, "", FOO.toUpperCase(Locale.ROOT), BAR));
    }

@Test
    public void testEqualsAnyIgnoreCase_16_oe() {
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
        assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, FOO.toUpperCase(Locale.ROOT)));
    }

@Test
    public void testEqualsAnyIgnoreCase_17_oe() {
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

        assertFalse(StringUtils.equalsAnyIgnoreCase(null, (CharSequence[]) null));
    }

@Test
    public void testEqualsAnyIgnoreCase_18_oe() {
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

        // removed other assertion
        assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, new CustomCharSequence("fOo")));
    }

@Test
    public void testEqualsAnyIgnoreCase_19_oe() {
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

        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, new StringBuilder("fOo")));
    }

@Test
    public void testCompare_StringString_1_oe() {
        assertEquals(0, StringUtils.compare(null, null));
    }

@Test
    public void testCompare_StringString_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.compare(null, "a") < 0);
    }

@Test
    public void testCompare_StringString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("a", null) > 0);
    }

@Test
    public void testCompare_StringString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.compare("abc", "abc"));
    }

@Test
    public void testCompare_StringString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("a", "b") < 0);
    }

@Test
    public void testCompare_StringString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("b", "a") > 0);
    }

@Test
    public void testCompare_StringString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("a", "B") > 0);
    }

@Test
    public void testCompare_StringString_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("abc", "abd") < 0);
    }

@Test
    public void testCompare_StringString_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("ab", "abc") < 0);
    }

@Test
    public void testCompare_StringString_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("ab", "ab ") < 0);
    }

@Test
    public void testCompare_StringString_11_oe() {
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
        assertTrue(StringUtils.compare("abc", "ab ") > 0);
    }

@Test
    public void testCompare_StringStringBoolean_1_oe() {
        assertEquals(0, StringUtils.compare(null, null, false));
    }

@Test
    public void testCompare_StringStringBoolean_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.compare(null, "a", true) < 0);
    }

@Test
    public void testCompare_StringStringBoolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare(null, "a", false) > 0);
    }

@Test
    public void testCompare_StringStringBoolean_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("a", null, true) > 0);
    }

@Test
    public void testCompare_StringStringBoolean_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("a", null, false) < 0);
    }

@Test
    public void testCompare_StringStringBoolean_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.compare("abc", "abc", false));
    }

@Test
    public void testCompare_StringStringBoolean_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("a", "b", false) < 0);
    }

@Test
    public void testCompare_StringStringBoolean_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("b", "a", false) > 0);
    }

@Test
    public void testCompare_StringStringBoolean_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("a", "B", false) > 0);
    }

@Test
    public void testCompare_StringStringBoolean_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compare("abc", "abd", false) < 0);
    }

@Test
    public void testCompare_StringStringBoolean_11_oe() {
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
        assertTrue(StringUtils.compare("ab", "abc", false) < 0);
    }

@Test
    public void testCompare_StringStringBoolean_12_oe() {
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
        assertTrue(StringUtils.compare("ab", "ab ", false) < 0);
    }

@Test
    public void testCompare_StringStringBoolean_13_oe() {
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
        assertTrue(StringUtils.compare("abc", "ab ", false) > 0);
    }

@Test
    public void testCompareIgnoreCase_StringString_1_oe() {
        assertEquals(0, StringUtils.compareIgnoreCase(null, null));
    }

@Test
    public void testCompareIgnoreCase_StringString_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase(null, "a") < 0);
    }

@Test
    public void testCompareIgnoreCase_StringString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("a", null) > 0);
    }

@Test
    public void testCompareIgnoreCase_StringString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.compareIgnoreCase("abc", "abc"));
    }

@Test
    public void testCompareIgnoreCase_StringString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.compareIgnoreCase("abc", "ABC"));
    }

@Test
    public void testCompareIgnoreCase_StringString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("a", "b") < 0);
    }

@Test
    public void testCompareIgnoreCase_StringString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("b", "a") > 0);
    }

@Test
    public void testCompareIgnoreCase_StringString_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("a", "B") < 0);
    }

@Test
    public void testCompareIgnoreCase_StringString_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("A", "b") < 0);
    }

@Test
    public void testCompareIgnoreCase_StringString_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("abc", "ABD") < 0);
    }

@Test
    public void testCompareIgnoreCase_StringString_11_oe() {
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
        assertTrue(StringUtils.compareIgnoreCase("ab", "ABC") < 0);
    }

@Test
    public void testCompareIgnoreCase_StringString_12_oe() {
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
        assertTrue(StringUtils.compareIgnoreCase("ab", "AB ") < 0);
    }

@Test
    public void testCompareIgnoreCase_StringString_13_oe() {
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
        assertTrue(StringUtils.compareIgnoreCase("abc", "AB ") > 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_1_oe() {
        assertEquals(0, StringUtils.compareIgnoreCase(null, null, false));
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase(null, "a", true) < 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase(null, "a", false) > 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("a", null, true) > 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("a", null, false) < 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.compareIgnoreCase("abc", "abc", false));
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.compareIgnoreCase("abc", "ABC", false));
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("a", "b", false) < 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("b", "a", false) > 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.compareIgnoreCase("a", "B", false) < 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_11_oe() {
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
        assertTrue(StringUtils.compareIgnoreCase("A", "b", false) < 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_12_oe() {
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
        assertTrue(StringUtils.compareIgnoreCase("abc", "ABD", false) < 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_13_oe() {
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
        assertTrue(StringUtils.compareIgnoreCase("ab", "ABC", false) < 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_14_oe() {
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
        assertTrue(StringUtils.compareIgnoreCase("ab", "AB ", false) < 0);
    }

@Test
    public void testCompareIgnoreCase_StringStringBoolean_15_oe() {
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
        assertTrue(StringUtils.compareIgnoreCase("abc", "AB ", false) > 0);
    }

@Test
    public void testIndexOf_char_1_oe() {
        assertEquals(-1, StringUtils.indexOf(null, ' '));
    }

@Test
    public void testIndexOf_char_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf("", ' '));
    }

@Test
    public void testIndexOf_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOf("aabaabaa", 'a'));
    }

@Test
    public void testIndexOf_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.indexOf("aabaabaa", 'b'));
    }

@Test
    public void testIndexOf_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(2, StringUtils.indexOf(new StringBuilder("aabaabaa"), 'b'));
    }

@Test
    public void testIndexOf_char_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(StringUtils.INDEX_NOT_FOUND, StringUtils.indexOf(new StringBuilder("aabaabaa"), -1738));
    }

@Test
    public void testIndexOf_charInt_1_oe() {
        assertEquals(-1, StringUtils.indexOf(null, ' ', 0));
    }

@Test
    public void testIndexOf_charInt_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf(null, ' ', -1));
    }

@Test
    public void testIndexOf_charInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf("", ' ', 0));
    }

@Test
    public void testIndexOf_charInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf("", ' ', -1));
    }

@Test
    public void testIndexOf_charInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOf("aabaabaa", 'a', 0));
    }

@Test
    public void testIndexOf_charInt_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.indexOf("aabaabaa", 'b', 0));
    }

@Test
    public void testIndexOf_charInt_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, StringUtils.indexOf("aabaabaa", 'b', 3));
    }

@Test
    public void testIndexOf_charInt_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf("aabaabaa", 'b', 9));
    }

@Test
    public void testIndexOf_charInt_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.indexOf("aabaabaa", 'b', -1));
    }

@Test
    public void testIndexOf_charInt_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(5, StringUtils.indexOf(new StringBuilder("aabaabaa"), 'b', 3));
    }

@Test
    public void testIndexOf_charInt_11_oe() {
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

        //LANG-1300 tests go here
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        assertEquals(0, StringUtils.indexOf(builder, CODE_POINT, 0));
    }

@Test
    public void testIndexOf_charInt_12_oe() {
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

        //LANG-1300 tests go here
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        assertEquals(0, StringUtils.indexOf(builder.toString(), CODE_POINT, 0));
    }

@Test
    public void testIndexOf_charInt_13_oe() {
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

        //LANG-1300 tests go here
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        assertEquals(2, StringUtils.indexOf(builder, CODE_POINT, 1));
    }

@Test
    public void testIndexOf_charInt_14_oe() {
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

        //LANG-1300 tests go here
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        assertEquals(2, StringUtils.indexOf(builder.toString(), CODE_POINT, 1));
    }

@Test
    public void testIndexOf_charInt_15_oe() {
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

        //LANG-1300 tests go here
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        assertEquals(-1, StringUtils.indexOf(builder, CODE_POINT, 0));
    }

@Test
    public void testIndexOf_charInt_16_oe() {
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

        //LANG-1300 tests go here
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf(builder.toString(), CODE_POINT, 0));
    }

@Test
    public void testIndexOf_charInt_17_oe() {
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

        //LANG-1300 tests go here
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        assertEquals(1, StringUtils.indexOf(builder, CODE_POINT, 0));
    }

@Test
    public void testIndexOf_charInt_18_oe() {
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

        //LANG-1300 tests go here
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        assertEquals(1, StringUtils.indexOf(builder.toString(), CODE_POINT, 0));
    }

@Test
    public void testIndexOf_charInt_19_oe() {
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

        //LANG-1300 tests go here
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf(builder, CODE_POINT, 2));
    }

@Test
    public void testIndexOf_charInt_20_oe() {
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

        //LANG-1300 tests go here
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        // removed other assertion
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf(builder.toString(), CODE_POINT, 2));
    }

@Test
    public void testIndexOf_String_1_oe() {
        assertEquals(-1, StringUtils.indexOf(null, null));
    }

@Test
    public void testIndexOf_String_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf("", null));
    }

@Test
    public void testIndexOf_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOf("", ""));
    }

@Test
    public void testIndexOf_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOf("aabaabaa", "a"));
    }

@Test
    public void testIndexOf_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.indexOf("aabaabaa", "b"));
    }

@Test
    public void testIndexOf_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, StringUtils.indexOf("aabaabaa", "ab"));
    }

@Test
    public void testIndexOf_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOf("aabaabaa", ""));
    }

@Test
    public void testIndexOf_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(2, StringUtils.indexOf(new StringBuilder("aabaabaa"), "b"));
    }

@Test
    public void testIndexOf_StringInt_1_oe() {
        assertEquals(-1, StringUtils.indexOf(null, null, 0));
    }

@Test
    public void testIndexOf_StringInt_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf(null, null, -1));
    }

@Test
    public void testIndexOf_StringInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf(null, "", 0));
    }

@Test
    public void testIndexOf_StringInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf(null, "", -1));
    }

@Test
    public void testIndexOf_StringInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf("", null, 0));
    }

@Test
    public void testIndexOf_StringInt_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf("", null, -1));
    }

@Test
    public void testIndexOf_StringInt_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOf("", "", 0));
    }

@Test
    public void testIndexOf_StringInt_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOf("", "", -1));
    }

@Test
    public void testIndexOf_StringInt_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOf("", "", 9));
    }

@Test
    public void testIndexOf_StringInt_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOf("abc", "", 0));
    }

@Test
    public void testIndexOf_StringInt_11_oe() {
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
        assertEquals(0, StringUtils.indexOf("abc", "", -1));
    }

@Test
    public void testIndexOf_StringInt_12_oe() {
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
        assertEquals(3, StringUtils.indexOf("abc", "", 9));
    }

@Test
    public void testIndexOf_StringInt_13_oe() {
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
        assertEquals(3, StringUtils.indexOf("abc", "", 3));
    }

@Test
    public void testIndexOf_StringInt_14_oe() {
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
        assertEquals(0, StringUtils.indexOf("aabaabaa", "a", 0));
    }

@Test
    public void testIndexOf_StringInt_15_oe() {
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
        assertEquals(2, StringUtils.indexOf("aabaabaa", "b", 0));
    }

@Test
    public void testIndexOf_StringInt_16_oe() {
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
        assertEquals(1, StringUtils.indexOf("aabaabaa", "ab", 0));
    }

@Test
    public void testIndexOf_StringInt_17_oe() {
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
        assertEquals(5, StringUtils.indexOf("aabaabaa", "b", 3));
    }

@Test
    public void testIndexOf_StringInt_18_oe() {
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
        // removed other assertion
        assertEquals(-1, StringUtils.indexOf("aabaabaa", "b", 9));
    }

@Test
    public void testIndexOf_StringInt_19_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.indexOf("aabaabaa", "b", -1));
    }

@Test
    public void testIndexOf_StringInt_20_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.indexOf("aabaabaa", "", 2));
    }

@Test
    public void testIndexOf_StringInt_21_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that startIndex works correctly, i.e. cannot match before startIndex
        assertEquals(7, StringUtils.indexOf("12345678", "8", 5));
    }

@Test
    public void testIndexOf_StringInt_22_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that startIndex works correctly, i.e. cannot match before startIndex
        // removed other assertion
        assertEquals(7, StringUtils.indexOf("12345678", "8", 6));
    }

@Test
    public void testIndexOf_StringInt_23_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that startIndex works correctly, i.e. cannot match before startIndex
        // removed other assertion
        // removed other assertion
        assertEquals(7,StringUtils.indexOf("12345678","8",7));// 7 is last index assertEquals(-1,StringUtils.indexOf("12345678","8",8));
    }

@Test
    public void testIndexOf_StringInt_24_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that startIndex works correctly, i.e. cannot match before startIndex
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(5, StringUtils.indexOf(new StringBuilder("aabaabaa"), "b", 3));
    }

@Test
    public void testIndexOfAny_StringCharArray_1_oe() {
        assertEquals(-1, StringUtils.indexOfAny(null, (char[]) null));
    }

@Test
    public void testIndexOfAny_StringCharArray_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(null, new char[0]));
    }

@Test
    public void testIndexOfAny_StringCharArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(null, 'a', 'b'));
    }

@Test
    public void testIndexOfAny_StringCharArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.indexOfAny("", (char[]) null));
    }

@Test
    public void testIndexOfAny_StringCharArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny("", new char[0]));
    }

@Test
    public void testIndexOfAny_StringCharArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny("", 'a', 'b'));
    }

@Test
    public void testIndexOfAny_StringCharArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.indexOfAny("zzabyycdxx", (char[]) null));
    }

@Test
    public void testIndexOfAny_StringCharArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny("zzabyycdxx", new char[0]));
    }

@Test
    public void testIndexOfAny_StringCharArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAny("zzabyycdxx", 'z', 'a'));
    }

@Test
    public void testIndexOfAny_StringCharArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, StringUtils.indexOfAny("zzabyycdxx", 'b', 'y'));
    }

@Test
    public void testIndexOfAny_StringCharArray_11_oe() {
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
        assertEquals(-1, StringUtils.indexOfAny("ab", 'z'));
    }

@Test
    public void testIndexOfAny_StringCharArrayWithSupplementaryChars_1_oe() {
        assertEquals(0, StringUtils.indexOfAny(CharU20000 + CharU20001, CharU20000.toCharArray()));
    }

@Test
    public void testIndexOfAny_StringCharArrayWithSupplementaryChars_2_oe() {
        // removed other assertion
        assertEquals(2, StringUtils.indexOfAny(CharU20000 + CharU20001, CharU20001.toCharArray()));
    }

@Test
    public void testIndexOfAny_StringCharArrayWithSupplementaryChars_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAny(CharU20000, CharU20000.toCharArray()));
    }

@Test
    public void testIndexOfAny_StringCharArrayWithSupplementaryChars_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(CharU20000, CharU20001.toCharArray()));
    }

@Test
    public void testIndexOfAny_StringString_1_oe() {
        assertEquals(-1, StringUtils.indexOfAny(null, (String) null));
    }

@Test
    public void testIndexOfAny_StringString_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(null, ""));
    }

@Test
    public void testIndexOfAny_StringString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(null, "ab"));
    }

@Test
    public void testIndexOfAny_StringString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.indexOfAny("", (String) null));
    }

@Test
    public void testIndexOfAny_StringString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny("", ""));
    }

@Test
    public void testIndexOfAny_StringString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny("", "ab"));
    }

@Test
    public void testIndexOfAny_StringString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.indexOfAny("zzabyycdxx", (String) null));
    }

@Test
    public void testIndexOfAny_StringString_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny("zzabyycdxx", ""));
    }

@Test
    public void testIndexOfAny_StringString_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAny("zzabyycdxx", "za"));
    }

@Test
    public void testIndexOfAny_StringString_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, StringUtils.indexOfAny("zzabyycdxx", "by"));
    }

@Test
    public void testIndexOfAny_StringString_11_oe() {
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
        assertEquals(-1, StringUtils.indexOfAny("ab", "z"));
    }

@Test
    public void testIndexOfAny_StringStringArray_1_oe() {
        assertEquals(-1, StringUtils.indexOfAny(null, (String[]) null));
    }

@Test
    public void testIndexOfAny_StringStringArray_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(null, FOOBAR_SUB_ARRAY));
    }

@Test
    public void testIndexOfAny_StringStringArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(FOOBAR, (String[]) null));
    }

@Test
    public void testIndexOfAny_StringStringArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.indexOfAny(FOOBAR, FOOBAR_SUB_ARRAY));
    }

@Test
    public void testIndexOfAny_StringStringArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(FOOBAR, new String[0]));
    }

@Test
    public void testIndexOfAny_StringStringArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(null, new String[0]));
    }

@Test
    public void testIndexOfAny_StringStringArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny("", new String[0]));
    }

@Test
    public void testIndexOfAny_StringStringArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(FOOBAR, new String[] {"llll"}));
    }

@Test
    public void testIndexOfAny_StringStringArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAny(FOOBAR, new String[] {""}));
    }

@Test
    public void testIndexOfAny_StringStringArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAny("", new String[] {""}));
    }

@Test
    public void testIndexOfAny_StringStringArray_11_oe() {
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
        assertEquals(-1, StringUtils.indexOfAny("", new String[] {"a"}));
    }

@Test
    public void testIndexOfAny_StringStringArray_12_oe() {
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
        assertEquals(-1, StringUtils.indexOfAny("", new String[] {null}));
    }

@Test
    public void testIndexOfAny_StringStringArray_13_oe() {
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
        assertEquals(-1, StringUtils.indexOfAny(FOOBAR, new String[] {null}));
    }

@Test
    public void testIndexOfAny_StringStringArray_14_oe() {
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
        assertEquals(-1, StringUtils.indexOfAny(null, new String[] {null}));
    }

@Test
    public void testIndexOfAny_StringStringWithSupplementaryChars_1_oe() {
        assertEquals(0, StringUtils.indexOfAny(CharU20000 + CharU20001, CharU20000));
    }

@Test
    public void testIndexOfAny_StringStringWithSupplementaryChars_2_oe() {
        // removed other assertion
        assertEquals(2, StringUtils.indexOfAny(CharU20000 + CharU20001, CharU20001));
    }

@Test
    public void testIndexOfAny_StringStringWithSupplementaryChars_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAny(CharU20000, CharU20000));
    }

@Test
    public void testIndexOfAny_StringStringWithSupplementaryChars_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAny(CharU20000, CharU20001));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_1_oe() {
        assertEquals(-1, StringUtils.indexOfAnyBut(null, (char[]) null));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut(null));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut(null, 'a', 'b'));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.indexOfAnyBut("", (char[]) null));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut(""));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut("", 'a', 'b'));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.indexOfAnyBut("zzabyycdxx", (char[]) null));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut("zzabyycdxx"));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(3, StringUtils.indexOfAnyBut("zzabyycdxx", 'z', 'a'));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAnyBut("zzabyycdxx", 'b', 'y'));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_11_oe() {
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
        assertEquals(-1, StringUtils.indexOfAnyBut("aba", 'a', 'b'));
    }

@Test
    public void testIndexOfAnyBut_StringCharArray_12_oe() {
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
        assertEquals(0, StringUtils.indexOfAnyBut("aba", 'z'));
    }

@Test
    public void testIndexOfAnyBut_StringCharArrayWithSupplementaryChars_1_oe() {
        assertEquals(2, StringUtils.indexOfAnyBut(CharU20000 + CharU20001, CharU20000.toCharArray()));
    }

@Test
    public void testIndexOfAnyBut_StringCharArrayWithSupplementaryChars_2_oe() {
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAnyBut(CharU20000 + CharU20001, CharU20001.toCharArray()));
    }

@Test
    public void testIndexOfAnyBut_StringCharArrayWithSupplementaryChars_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut(CharU20000, CharU20000.toCharArray()));
    }

@Test
    public void testIndexOfAnyBut_StringCharArrayWithSupplementaryChars_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAnyBut(CharU20000, CharU20001.toCharArray()));
    }

@Test
    public void testIndexOfAnyBut_StringString_1_oe() {
        assertEquals(-1, StringUtils.indexOfAnyBut(null, (String) null));
    }

@Test
    public void testIndexOfAnyBut_StringString_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut(null, ""));
    }

@Test
    public void testIndexOfAnyBut_StringString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut(null, "ab"));
    }

@Test
    public void testIndexOfAnyBut_StringString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.indexOfAnyBut("", (String) null));
    }

@Test
    public void testIndexOfAnyBut_StringString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut("", ""));
    }

@Test
    public void testIndexOfAnyBut_StringString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut("", "ab"));
    }

@Test
    public void testIndexOfAnyBut_StringString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.indexOfAnyBut("zzabyycdxx", (String) null));
    }

@Test
    public void testIndexOfAnyBut_StringString_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut("zzabyycdxx", ""));
    }

@Test
    public void testIndexOfAnyBut_StringString_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(3, StringUtils.indexOfAnyBut("zzabyycdxx", "za"));
    }

@Test
    public void testIndexOfAnyBut_StringString_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAnyBut("zzabyycdxx", "by"));
    }

@Test
    public void testIndexOfAnyBut_StringString_11_oe() {
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
        assertEquals(0, StringUtils.indexOfAnyBut("ab", "z"));
    }

@Test
    public void testIndexOfAnyBut_StringStringWithSupplementaryChars_1_oe() {
        assertEquals(2, StringUtils.indexOfAnyBut(CharU20000 + CharU20001, CharU20000));
    }

@Test
    public void testIndexOfAnyBut_StringStringWithSupplementaryChars_2_oe() {
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAnyBut(CharU20000 + CharU20001, CharU20001));
    }

@Test
    public void testIndexOfAnyBut_StringStringWithSupplementaryChars_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfAnyBut(CharU20000, CharU20000));
    }

@Test
    public void testIndexOfAnyBut_StringStringWithSupplementaryChars_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfAnyBut(CharU20000, CharU20001));
    }

@Test
    public void testIndexOfIgnoreCase_String_1_oe() {
        assertEquals(-1, StringUtils.indexOfIgnoreCase(null, null));
    }

@Test
    public void testIndexOfIgnoreCase_String_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfIgnoreCase(null, ""));
    }

@Test
    public void testIndexOfIgnoreCase_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfIgnoreCase("", null));
    }

@Test
    public void testIndexOfIgnoreCase_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfIgnoreCase("", ""));
    }

@Test
    public void testIndexOfIgnoreCase_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfIgnoreCase("aabaabaa", "a"));
    }

@Test
    public void testIndexOfIgnoreCase_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.indexOfIgnoreCase("aabaabaa", "A"));
    }

@Test
    public void testIndexOfIgnoreCase_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.indexOfIgnoreCase("aabaabaa", "b"));
    }

@Test
    public void testIndexOfIgnoreCase_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.indexOfIgnoreCase("aabaabaa", "B"));
    }

@Test
    public void testIndexOfIgnoreCase_String_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, StringUtils.indexOfIgnoreCase("aabaabaa", "ab"));
    }

@Test
    public void testIndexOfIgnoreCase_String_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB"));
    }

@Test
    public void testIndexOfIgnoreCase_String_11_oe() {
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
        assertEquals(0, StringUtils.indexOfIgnoreCase("aabaabaa", ""));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_1_oe() {
        assertEquals(1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", -1));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_2_oe() {
        // removed other assertion
        assertEquals(1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 0));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 1));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 2));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 3));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 4));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 5));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 6));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 7));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 8));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_11_oe() {
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
        assertEquals(1, StringUtils.indexOfIgnoreCase("aab", "AB", 1));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_12_oe() {
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
        assertEquals(5, StringUtils.indexOfIgnoreCase("aabaabaa", "", 5));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_13_oe() {
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
        assertEquals(-1, StringUtils.indexOfIgnoreCase("ab", "AAB", 0));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_14_oe() {
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
        assertEquals(-1, StringUtils.indexOfIgnoreCase("aab", "AAB", 1));
    }

@Test
    public void testIndexOfIgnoreCase_StringInt_15_oe() {
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
        assertEquals(-1, StringUtils.indexOfIgnoreCase("abc", "", 9));
    }

@Test
    public void testLastIndexOf_char_1_oe() {
        assertEquals(-1, StringUtils.lastIndexOf(null, ' '));
    }

@Test
    public void testLastIndexOf_char_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("", ' '));
    }

@Test
    public void testLastIndexOf_char_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(7, StringUtils.lastIndexOf("aabaabaa", 'a'));
    }

@Test
    public void testLastIndexOf_char_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, StringUtils.lastIndexOf("aabaabaa", 'b'));
    }

@Test
    public void testLastIndexOf_char_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(5, StringUtils.lastIndexOf(new StringBuilder("aabaabaa"), 'b'));
    }

@Test
    public void testLastIndexOf_charInt_1_oe() {
        assertEquals(-1, StringUtils.lastIndexOf(null, ' ', 0));
    }

@Test
    public void testLastIndexOf_charInt_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf(null, ' ', -1));
    }

@Test
    public void testLastIndexOf_charInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("", ' ', 0));
    }

@Test
    public void testLastIndexOf_charInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("", ' ', -1));
    }

@Test
    public void testLastIndexOf_charInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, StringUtils.lastIndexOf("aabaabaa", 'a', 8));
    }

@Test
    public void testLastIndexOf_charInt_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, StringUtils.lastIndexOf("aabaabaa", 'b', 8));
    }

@Test
    public void testLastIndexOf_charInt_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.lastIndexOf("aabaabaa", 'b', 3));
    }

@Test
    public void testLastIndexOf_charInt_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, StringUtils.lastIndexOf("aabaabaa", 'b', 9));
    }

@Test
    public void testLastIndexOf_charInt_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("aabaabaa", 'b', -1));
    }

@Test
    public void testLastIndexOf_charInt_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOf("aabaabaa", 'a', 0));
    }

@Test
    public void testLastIndexOf_charInt_11_oe() {
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

        assertEquals(2, StringUtils.lastIndexOf(new StringBuilder("aabaabaa"), 'b', 2));
    }

@Test
    public void testLastIndexOf_charInt_12_oe() {
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

        //LANG-1300 addition test
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        assertEquals(0, StringUtils.lastIndexOf(builder, CODE_POINT, 0));
    }

@Test
    public void testLastIndexOf_charInt_13_oe() {
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

        //LANG-1300 addition test
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        assertEquals(0, StringUtils.lastIndexOf(builder, CODE_POINT, 0));
    }

@Test
    public void testLastIndexOf_charInt_14_oe() {
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

        //LANG-1300 addition test
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOf(builder, CODE_POINT, 1));
    }

@Test
    public void testLastIndexOf_charInt_15_oe() {
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

        //LANG-1300 addition test
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.lastIndexOf(builder, CODE_POINT, 2));
    }

@Test
    public void testLastIndexOf_charInt_16_oe() {
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

        //LANG-1300 addition test
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        builder.append("aaaaa");
        assertEquals(2, StringUtils.lastIndexOf(builder, CODE_POINT, 4));
    }

@Test
    public void testLastIndexOf_charInt_17_oe() {
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

        //LANG-1300 addition test
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        builder.append("aaaaa");
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        assertEquals(-1, StringUtils.lastIndexOf(builder, CODE_POINT, 0));
    }

@Test
    public void testLastIndexOf_charInt_18_oe() {
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

        //LANG-1300 addition test
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        builder.append("aaaaa");
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        assertEquals(-1, StringUtils.lastIndexOf(builder, CODE_POINT, 0));
    }

@Test
    public void testLastIndexOf_charInt_19_oe() {
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

        //LANG-1300 addition test
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        builder.append("aaaaa");
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        assertEquals(1, StringUtils.lastIndexOf(builder, CODE_POINT, 1 ));
    }

@Test
    public void testLastIndexOf_charInt_20_oe() {
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

        //LANG-1300 addition test
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        builder.append("aaaaa");
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf(builder.toString(), CODE_POINT, 0));
    }

@Test
    public void testLastIndexOf_charInt_21_oe() {
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

        //LANG-1300 addition test
        final int CODE_POINT = 0x2070E;
        StringBuilder builder = new StringBuilder();
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        builder.append("aaaaa");
        // removed other assertion
        // inner branch on the supplementary character block
        final char[] tmp = { (char) 55361 };
        builder = new StringBuilder();
        builder.append(tmp);
        // removed other assertion
        builder.appendCodePoint(CODE_POINT);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, StringUtils.lastIndexOf(builder.toString(), CODE_POINT, 1));
    }

@Test
    public void testLastIndexOf_String_1_oe() {
        assertEquals(-1, StringUtils.lastIndexOf(null, null));
    }

@Test
    public void testLastIndexOf_String_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("", null));
    }

@Test
    public void testLastIndexOf_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("", "a"));
    }

@Test
    public void testLastIndexOf_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOf("", ""));
    }

@Test
    public void testLastIndexOf_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, StringUtils.lastIndexOf("aabaabaa", ""));
    }

@Test
    public void testLastIndexOf_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, StringUtils.lastIndexOf("aabaabaa", "a"));
    }

@Test
    public void testLastIndexOf_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, StringUtils.lastIndexOf("aabaabaa", "b"));
    }

@Test
    public void testLastIndexOf_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, StringUtils.lastIndexOf("aabaabaa", "ab"));
    }

@Test
    public void testLastIndexOf_String_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(4, StringUtils.lastIndexOf(new StringBuilder("aabaabaa"), "ab"));
    }

@Test
    public void testLastIndexOf_StringInt_1_oe() {
        assertEquals(-1, StringUtils.lastIndexOf(null, null, 0));
    }

@Test
    public void testLastIndexOf_StringInt_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf(null, null, -1));
    }

@Test
    public void testLastIndexOf_StringInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf(null, "", 0));
    }

@Test
    public void testLastIndexOf_StringInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf(null, "", -1));
    }

@Test
    public void testLastIndexOf_StringInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("", null, 0));
    }

@Test
    public void testLastIndexOf_StringInt_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("", null, -1));
    }

@Test
    public void testLastIndexOf_StringInt_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOf("", "", 0));
    }

@Test
    public void testLastIndexOf_StringInt_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("", "", -1));
    }

@Test
    public void testLastIndexOf_StringInt_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOf("", "", 9));
    }

@Test
    public void testLastIndexOf_StringInt_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOf("abc", "", 0));
    }

@Test
    public void testLastIndexOf_StringInt_11_oe() {
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
        assertEquals(-1, StringUtils.lastIndexOf("abc", "", -1));
    }

@Test
    public void testLastIndexOf_StringInt_12_oe() {
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
        assertEquals(3, StringUtils.lastIndexOf("abc", "", 9));
    }

@Test
    public void testLastIndexOf_StringInt_13_oe() {
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
        assertEquals(7, StringUtils.lastIndexOf("aabaabaa", "a", 8));
    }

@Test
    public void testLastIndexOf_StringInt_14_oe() {
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
        assertEquals(5, StringUtils.lastIndexOf("aabaabaa", "b", 8));
    }

@Test
    public void testLastIndexOf_StringInt_15_oe() {
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
        assertEquals(4, StringUtils.lastIndexOf("aabaabaa", "ab", 8));
    }

@Test
    public void testLastIndexOf_StringInt_16_oe() {
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
        assertEquals(2, StringUtils.lastIndexOf("aabaabaa", "b", 3));
    }

@Test
    public void testLastIndexOf_StringInt_17_oe() {
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
        assertEquals(5, StringUtils.lastIndexOf("aabaabaa", "b", 9));
    }

@Test
    public void testLastIndexOf_StringInt_18_oe() {
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
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("aabaabaa", "b", -1));
    }

@Test
    public void testLastIndexOf_StringInt_19_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("aabaabaa", "b", 0));
    }

@Test
    public void testLastIndexOf_StringInt_20_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOf("aabaabaa", "a", 0));
    }

@Test
    public void testLastIndexOf_StringInt_21_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOf("aabaabaa", "a", -1));
    }

@Test
    public void testLastIndexOf_StringInt_22_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that fromIndex works correctly, i.e. cannot match after fromIndex
        assertEquals(7, StringUtils.lastIndexOf("12345678", "8", 9));
    }

@Test
    public void testLastIndexOf_StringInt_23_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that fromIndex works correctly, i.e. cannot match after fromIndex
        // removed other assertion
        assertEquals(7, StringUtils.lastIndexOf("12345678", "8", 8));
    }

@Test
    public void testLastIndexOf_StringInt_24_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that fromIndex works correctly, i.e. cannot match after fromIndex
        // removed other assertion
        // removed other assertion
        assertEquals(7,StringUtils.lastIndexOf("12345678","8",7));// 7 is last index assertEquals(-1,StringUtils.lastIndexOf("12345678","8",6));
    }

@Test
    public void testLastIndexOf_StringInt_25_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that fromIndex works correctly, i.e. cannot match after fromIndex
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.lastIndexOf("aabaabaa", "b", 1));
    }

@Test
    public void testLastIndexOf_StringInt_26_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that fromIndex works correctly, i.e. cannot match after fromIndex
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(2, StringUtils.lastIndexOf("aabaabaa", "b", 2));
    }

@Test
    public void testLastIndexOf_StringInt_27_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that fromIndex works correctly, i.e. cannot match after fromIndex
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.lastIndexOf("aabaabaa", "ba", 2));
    }

@Test
    public void testLastIndexOf_StringInt_28_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that fromIndex works correctly, i.e. cannot match after fromIndex
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.lastIndexOf("aabaabaa", "ba", 3));
    }

@Test
    public void testLastIndexOf_StringInt_29_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test that fromIndex works correctly, i.e. cannot match after fromIndex
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(2, StringUtils.lastIndexOf(new StringBuilder("aabaabaa"), "b", 3));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_1_oe() {
        assertEquals(-1,StringUtils.lastIndexOfAny(null,(CharSequence)null));// test both types of ... assertEquals(-1,StringUtils.lastIndexOfAny(null,(CharSequence[])null));// ... varargs invocation assertEquals(-1,StringUtils.lastIndexOfAny(null));// Missing varag assertEquals(-1,StringUtils.lastIndexOfAny(null,FOOBAR_SUB_ARRAY));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_2_oe() {
        // removed other assertion
        assertEquals(-1,StringUtils.lastIndexOfAny(FOOBAR,(CharSequence)null));// test both types of ... assertEquals(-1,StringUtils.lastIndexOfAny(FOOBAR,(CharSequence[])null));// ... varargs invocation assertEquals(-1,StringUtils.lastIndexOfAny(FOOBAR));// Missing vararg assertEquals(3,StringUtils.lastIndexOfAny(FOOBAR,FOOBAR_SUB_ARRAY));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfAny(FOOBAR, new String[0]));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfAny(null, new String[0]));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfAny("", new String[0]));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfAny(FOOBAR, new String[] {"llll"}));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, StringUtils.lastIndexOfAny(FOOBAR, new String[] {""}));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOfAny("", new String[] {""}));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfAny("", new String[] {"a"}));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfAny("", new String[] {null}));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_11_oe() {
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
        assertEquals(-1, StringUtils.lastIndexOfAny(FOOBAR, new String[] {null}));
    }

@Test
    public void testLastIndexOfAny_StringStringArray_12_oe() {
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
        assertEquals(-1, StringUtils.lastIndexOfAny(null, new String[] {null}));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_1_oe() {
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, null));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("", null));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, ""));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("", "a"));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOfIgnoreCase("", ""));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, StringUtils.lastIndexOfIgnoreCase("aabaabaa", ""));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "a"));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A"));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "b"));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B"));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_11_oe() {
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
        assertEquals(4, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "ab"));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_12_oe() {
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
        assertEquals(4, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB"));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_13_oe() {
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
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("ab", "AAB"));
    }

@Test
    public void testLastIndexOfIgnoreCase_String_14_oe() {
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
        assertEquals(0, StringUtils.lastIndexOfIgnoreCase("aab", "AAB"));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_1_oe() {
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, null, 0));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, null, -1));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, "", 0));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, "", -1));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("", null, 0));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("", null, -1));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOfIgnoreCase("", "", 0));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("", "", -1));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOfIgnoreCase("", "", 9));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOfIgnoreCase("abc", "", 0));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_11_oe() {
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
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("abc", "", -1));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_12_oe() {
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
        assertEquals(3, StringUtils.lastIndexOfIgnoreCase("abc", "", 9));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_13_oe() {
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
        assertEquals(7, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 8));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_14_oe() {
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
        assertEquals(5, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 8));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_15_oe() {
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
        assertEquals(4, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB", 8));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_16_oe() {
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
        assertEquals(2, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 3));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_17_oe() {
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
        assertEquals(5, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 9));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_18_oe() {
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
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", -1));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_19_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 0));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_20_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 0));
    }

@Test
    public void testLastIndexOfIgnoreCase_StringInt_21_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, StringUtils.lastIndexOfIgnoreCase("aab", "AB", 1));
    }

@Test
    public void testLastOrdinalIndexOf_1_oe() {
        assertEquals(-1, StringUtils.lastOrdinalIndexOf(null, "*", 42) );
    }

@Test
    public void testLastOrdinalIndexOf_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.lastOrdinalIndexOf("*", null, 42) );
    }

@Test
    public void testLastOrdinalIndexOf_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.lastOrdinalIndexOf("", "", 42) );
    }

@Test
    public void testLastOrdinalIndexOf_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 1) );
    }

@Test
    public void testLastOrdinalIndexOf_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 2) );
    }

@Test
    public void testLastOrdinalIndexOf_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 1) );
    }

@Test
    public void testLastOrdinalIndexOf_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 2) );
    }

@Test
    public void testLastOrdinalIndexOf_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 1) );
    }

@Test
    public void testLastOrdinalIndexOf_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 2) );
    }

@Test
    public void testLastOrdinalIndexOf_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, StringUtils.lastOrdinalIndexOf("aabaabaa", "", 1) );
    }

@Test
    public void testLastOrdinalIndexOf_11_oe() {
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
        assertEquals(8, StringUtils.lastOrdinalIndexOf("aabaabaa", "", 2) );
    }

@Test
    public void testOrdinalIndexOf_1_oe() {
        assertEquals(-1, StringUtils.ordinalIndexOf(null, null, Integer.MIN_VALUE));
    }

@Test
    public void testOrdinalIndexOf_2_oe() {
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("", null, Integer.MIN_VALUE));
    }

@Test
    public void testOrdinalIndexOf_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("", "", Integer.MIN_VALUE));
    }

@Test
    public void testOrdinalIndexOf_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "a", Integer.MIN_VALUE));
    }

@Test
    public void testOrdinalIndexOf_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "b", Integer.MIN_VALUE));
    }

@Test
    public void testOrdinalIndexOf_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "ab", Integer.MIN_VALUE));
    }

@Test
    public void testOrdinalIndexOf_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "", Integer.MIN_VALUE));
    }

@Test
    public void testOrdinalIndexOf_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.ordinalIndexOf(null, null, -1));
    }

@Test
    public void testOrdinalIndexOf_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("", null, -1));
    }

@Test
    public void testOrdinalIndexOf_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("", "", -1));
    }

@Test
    public void testOrdinalIndexOf_11_oe() {
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
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "a", -1));
    }

@Test
    public void testOrdinalIndexOf_12_oe() {
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
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "b", -1));
    }

@Test
    public void testOrdinalIndexOf_13_oe() {
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
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "ab", -1));
    }

@Test
    public void testOrdinalIndexOf_14_oe() {
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
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "", -1));
    }

@Test
    public void testOrdinalIndexOf_15_oe() {
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

        assertEquals(-1, StringUtils.ordinalIndexOf(null, null, 0));
    }

@Test
    public void testOrdinalIndexOf_16_oe() {
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
        assertEquals(-1, StringUtils.ordinalIndexOf("", null, 0));
    }

@Test
    public void testOrdinalIndexOf_17_oe() {
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
        assertEquals(-1, StringUtils.ordinalIndexOf("", "", 0));
    }

@Test
    public void testOrdinalIndexOf_18_oe() {
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
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "a", 0));
    }

@Test
    public void testOrdinalIndexOf_19_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "b", 0));
    }

@Test
    public void testOrdinalIndexOf_20_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "ab", 0));
    }

@Test
    public void testOrdinalIndexOf_21_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "", 0));
    }

@Test
    public void testOrdinalIndexOf_22_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.ordinalIndexOf(null, null, 1));
    }

@Test
    public void testOrdinalIndexOf_23_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("", null, 1));
    }

@Test
    public void testOrdinalIndexOf_24_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.ordinalIndexOf("", "", 1));
    }

@Test
    public void testOrdinalIndexOf_25_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.ordinalIndexOf("aabaabaa", "a", 1));
    }

@Test
    public void testOrdinalIndexOf_26_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.ordinalIndexOf("aabaabaa", "b", 1));
    }

@Test
    public void testOrdinalIndexOf_27_oe() {
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
        assertEquals(1, StringUtils.ordinalIndexOf("aabaabaa", "ab", 1));
    }

@Test
    public void testOrdinalIndexOf_28_oe() {
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
        assertEquals(0, StringUtils.ordinalIndexOf("aabaabaa", "", 1));
    }

@Test
    public void testOrdinalIndexOf_29_oe() {
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

        assertEquals(-1, StringUtils.ordinalIndexOf(null, null, 2));
    }

@Test
    public void testOrdinalIndexOf_30_oe() {
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
        assertEquals(-1, StringUtils.ordinalIndexOf("", null, 2));
    }

@Test
    public void testOrdinalIndexOf_31_oe() {
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
        assertEquals(0, StringUtils.ordinalIndexOf("", "", 2));
    }

@Test
    public void testOrdinalIndexOf_32_oe() {
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
        assertEquals(1, StringUtils.ordinalIndexOf("aabaabaa", "a", 2));
    }

@Test
    public void testOrdinalIndexOf_33_oe() {
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
        assertEquals(5, StringUtils.ordinalIndexOf("aabaabaa", "b", 2));
    }

@Test
    public void testOrdinalIndexOf_34_oe() {
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
        // removed other assertion
        assertEquals(4, StringUtils.ordinalIndexOf("aabaabaa", "ab", 2));
    }

@Test
    public void testOrdinalIndexOf_35_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.ordinalIndexOf("aabaabaa", "", 2));
    }

@Test
    public void testOrdinalIndexOf_36_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, StringUtils.ordinalIndexOf(null, null, Integer.MAX_VALUE));
    }

@Test
    public void testOrdinalIndexOf_37_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("", null, Integer.MAX_VALUE));
    }

@Test
    public void testOrdinalIndexOf_38_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.ordinalIndexOf("", "", Integer.MAX_VALUE));
    }

@Test
    public void testOrdinalIndexOf_39_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "a", Integer.MAX_VALUE));
    }

@Test
    public void testOrdinalIndexOf_40_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "b", Integer.MAX_VALUE));
    }

@Test
    public void testOrdinalIndexOf_41_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "ab", Integer.MAX_VALUE));
    }

@Test
    public void testOrdinalIndexOf_42_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.ordinalIndexOf("aabaabaa", "", Integer.MAX_VALUE));
    }

@Test
    public void testOrdinalIndexOf_43_oe() {
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

        assertEquals(-1, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 0));
    }

@Test
    public void testOrdinalIndexOf_44_oe() {
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
        assertEquals(0, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 1));
    }

@Test
    public void testOrdinalIndexOf_45_oe() {
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
        assertEquals(1, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 2));
    }

@Test
    public void testOrdinalIndexOf_46_oe() {
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
        assertEquals(2, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 3));
    }

@Test
    public void testOrdinalIndexOf_47_oe() {
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
        assertEquals(3, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 4));
    }

@Test
    public void testOrdinalIndexOf_48_oe() {
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
        assertEquals(4, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 5));
    }

@Test
    public void testOrdinalIndexOf_49_oe() {
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
        assertEquals(5, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 6));
    }

@Test
    public void testOrdinalIndexOf_50_oe() {
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
        // removed other assertion
        assertEquals(6, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 7));
    }

@Test
    public void testOrdinalIndexOf_51_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(7, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 8));
    }

@Test
    public void testOrdinalIndexOf_52_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 9));
    }

@Test
    public void testOrdinalIndexOf_53_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 10));
    }

@Test
    public void testOrdinalIndexOf_54_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
        assertEquals(0, StringUtils.ordinalIndexOf("aaaaaa", "aa", 1));
    }

@Test
    public void testOrdinalIndexOf_55_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
        // removed other assertion
        assertEquals(1, StringUtils.ordinalIndexOf("aaaaaa", "aa", 2));
    }

@Test
    public void testOrdinalIndexOf_56_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.ordinalIndexOf("aaaaaa", "aa", 3));
    }

@Test
    public void testOrdinalIndexOf_57_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, StringUtils.ordinalIndexOf("aaaaaa", "aa", 4));
    }

@Test
    public void testOrdinalIndexOf_58_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, StringUtils.ordinalIndexOf("aaaaaa", "aa", 5));
    }

@Test
    public void testOrdinalIndexOf_59_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("aaaaaa", "aa", 6));
    }

@Test
    public void testOrdinalIndexOf_60_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, StringUtils.ordinalIndexOf("ababab", "aba", 1));
    }

@Test
    public void testOrdinalIndexOf_61_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(2, StringUtils.ordinalIndexOf("ababab", "aba", 2));
    }

@Test
    public void testOrdinalIndexOf_62_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(-1, StringUtils.ordinalIndexOf("ababab", "aba", 3));
    }

@Test
    public void testOrdinalIndexOf_63_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, StringUtils.ordinalIndexOf("abababab", "abab", 1));
    }

@Test
    public void testOrdinalIndexOf_64_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
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
        assertEquals(2, StringUtils.ordinalIndexOf("abababab", "abab", 2));
    }

@Test
    public void testOrdinalIndexOf_65_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
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
        assertEquals(4, StringUtils.ordinalIndexOf("abababab", "abab", 3));
    }

@Test
    public void testOrdinalIndexOf_66_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match at each possible position
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
        assertEquals(-1, StringUtils.ordinalIndexOf("abababab", "abab", 4));
    }

@Test
    public void testLANG1193_1_oe() {
        assertEquals(0, StringUtils.ordinalIndexOf("abc", "ab", 1));
    }

@Test
    // Non-overlapping test
    public void testLANG1241_1_1_oe() {
        //                                          0  3  6
        assertEquals(0, StringUtils.ordinalIndexOf("abaabaab", "ab", 1));
    }

@Test
    // Non-overlapping test
    public void testLANG1241_1_2_oe() {
        //                                          0  3  6
        // removed other assertion
        assertEquals(3, StringUtils.ordinalIndexOf("abaabaab", "ab", 2));
    }

@Test
    // Non-overlapping test
    public void testLANG1241_1_3_oe() {
        //                                          0  3  6
        // removed other assertion
        // removed other assertion
        assertEquals(6, StringUtils.ordinalIndexOf("abaabaab", "ab", 3));
    }

@Test
    // Overlapping matching test
    public void testLANG1241_2_1_oe() {
        //                                          0 2 4
        assertEquals(0, StringUtils.ordinalIndexOf("abababa", "aba", 1));
    }

@Test
    // Overlapping matching test
    public void testLANG1241_2_2_oe() {
        //                                          0 2 4
        // removed other assertion
        assertEquals(2, StringUtils.ordinalIndexOf("abababa", "aba", 2));
    }

@Test
    // Overlapping matching test
    public void testLANG1241_2_3_oe() {
        //                                          0 2 4
        // removed other assertion
        // removed other assertion
        assertEquals(4, StringUtils.ordinalIndexOf("abababa", "aba", 3));
    }

@Test
    // Overlapping matching test
    public void testLANG1241_2_4_oe() {
        //                                          0 2 4
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, StringUtils.ordinalIndexOf("abababab", "abab", 1));
    }

@Test
    // Overlapping matching test
    public void testLANG1241_2_5_oe() {
        //                                          0 2 4
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, StringUtils.ordinalIndexOf("abababab", "abab", 2));
    }

@Test
    // Overlapping matching test
    public void testLANG1241_2_6_oe() {
        //                                          0 2 4
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, StringUtils.ordinalIndexOf("abababab", "abab", 3));
    }

}
