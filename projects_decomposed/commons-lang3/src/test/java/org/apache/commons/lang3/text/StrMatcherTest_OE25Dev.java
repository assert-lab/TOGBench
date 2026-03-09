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

package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link org.apache.commons.lang3.text.StrMatcher}.
 */
@Deprecated
public class StrMatcherTest_OE25Dev  {

    private static final char[] BUFFER1 = "0,1\t2 3\n\r\f\u0000'\"".toCharArray();

    private static final char[] BUFFER2 = "abcdef".toCharArray();


    //-----------------------------------------------------------------------
    @Test
    public void testCommaMatcher() {
        final StrMatcher matcher = StrMatcher.commaMatcher();
        assertSame(matcher, StrMatcher.commaMatcher());
        assertEquals(0, matcher.isMatch(BUFFER1, 0));
        assertEquals(1, matcher.isMatch(BUFFER1, 1));
        assertEquals(0, matcher.isMatch(BUFFER1, 2));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testTabMatcher() {
        final StrMatcher matcher = StrMatcher.tabMatcher();
        assertSame(matcher, StrMatcher.tabMatcher());
        assertEquals(0, matcher.isMatch(BUFFER1, 2));
        assertEquals(1, matcher.isMatch(BUFFER1, 3));
        assertEquals(0, matcher.isMatch(BUFFER1, 4));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testSpaceMatcher() {
        final StrMatcher matcher = StrMatcher.spaceMatcher();
        assertSame(matcher, StrMatcher.spaceMatcher());
        assertEquals(0, matcher.isMatch(BUFFER1, 4));
        assertEquals(1, matcher.isMatch(BUFFER1, 5));
        assertEquals(0, matcher.isMatch(BUFFER1, 6));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testSplitMatcher() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertSame(matcher, StrMatcher.splitMatcher());
        assertEquals(0, matcher.isMatch(BUFFER1, 2));
        assertEquals(1, matcher.isMatch(BUFFER1, 3));
        assertEquals(0, matcher.isMatch(BUFFER1, 4));
        assertEquals(1, matcher.isMatch(BUFFER1, 5));
        assertEquals(0, matcher.isMatch(BUFFER1, 6));
        assertEquals(1, matcher.isMatch(BUFFER1, 7));
        assertEquals(1, matcher.isMatch(BUFFER1, 8));
        assertEquals(1, matcher.isMatch(BUFFER1, 9));
        assertEquals(0, matcher.isMatch(BUFFER1, 10));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testTrimMatcher() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertSame(matcher, StrMatcher.trimMatcher());
        assertEquals(0, matcher.isMatch(BUFFER1, 2));
        assertEquals(1, matcher.isMatch(BUFFER1, 3));
        assertEquals(0, matcher.isMatch(BUFFER1, 4));
        assertEquals(1, matcher.isMatch(BUFFER1, 5));
        assertEquals(0, matcher.isMatch(BUFFER1, 6));
        assertEquals(1, matcher.isMatch(BUFFER1, 7));
        assertEquals(1, matcher.isMatch(BUFFER1, 8));
        assertEquals(1, matcher.isMatch(BUFFER1, 9));
        assertEquals(1, matcher.isMatch(BUFFER1, 10));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testSingleQuoteMatcher() {
        final StrMatcher matcher = StrMatcher.singleQuoteMatcher();
        assertSame(matcher, StrMatcher.singleQuoteMatcher());
        assertEquals(0, matcher.isMatch(BUFFER1, 10));
        assertEquals(1, matcher.isMatch(BUFFER1, 11));
        assertEquals(0, matcher.isMatch(BUFFER1, 12));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testDoubleQuoteMatcher() {
        final StrMatcher matcher = StrMatcher.doubleQuoteMatcher();
        assertSame(matcher, StrMatcher.doubleQuoteMatcher());
        assertEquals(0, matcher.isMatch(BUFFER1, 11));
        assertEquals(1, matcher.isMatch(BUFFER1, 12));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testQuoteMatcher() {
        final StrMatcher matcher = StrMatcher.quoteMatcher();
        assertSame(matcher, StrMatcher.quoteMatcher());
        assertEquals(0, matcher.isMatch(BUFFER1, 10));
        assertEquals(1, matcher.isMatch(BUFFER1, 11));
        assertEquals(1, matcher.isMatch(BUFFER1, 12));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNoneMatcher() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertSame(matcher, StrMatcher.noneMatcher());
        assertEquals(0, matcher.isMatch(BUFFER1, 0));
        assertEquals(0, matcher.isMatch(BUFFER1, 1));
        assertEquals(0, matcher.isMatch(BUFFER1, 2));
        assertEquals(0, matcher.isMatch(BUFFER1, 3));
        assertEquals(0, matcher.isMatch(BUFFER1, 4));
        assertEquals(0, matcher.isMatch(BUFFER1, 5));
        assertEquals(0, matcher.isMatch(BUFFER1, 6));
        assertEquals(0, matcher.isMatch(BUFFER1, 7));
        assertEquals(0, matcher.isMatch(BUFFER1, 8));
        assertEquals(0, matcher.isMatch(BUFFER1, 9));
        assertEquals(0, matcher.isMatch(BUFFER1, 10));
        assertEquals(0, matcher.isMatch(BUFFER1, 11));
        assertEquals(0, matcher.isMatch(BUFFER1, 12));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testCharMatcher_char() {
        final StrMatcher matcher = StrMatcher.charMatcher('c');
        assertEquals(0, matcher.isMatch(BUFFER2, 0));
        assertEquals(0, matcher.isMatch(BUFFER2, 1));
        assertEquals(1, matcher.isMatch(BUFFER2, 2));
        assertEquals(0, matcher.isMatch(BUFFER2, 3));
        assertEquals(0, matcher.isMatch(BUFFER2, 4));
        assertEquals(0, matcher.isMatch(BUFFER2, 5));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testCharSetMatcher_String() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace");
        assertEquals(1, matcher.isMatch(BUFFER2, 0));
        assertEquals(0, matcher.isMatch(BUFFER2, 1));
        assertEquals(1, matcher.isMatch(BUFFER2, 2));
        assertEquals(0, matcher.isMatch(BUFFER2, 3));
        assertEquals(1, matcher.isMatch(BUFFER2, 4));
        assertEquals(0, matcher.isMatch(BUFFER2, 5));
        assertSame(StrMatcher.noneMatcher(), StrMatcher.charSetMatcher(""));
        assertSame(StrMatcher.noneMatcher(), StrMatcher.charSetMatcher((String) null));
        assertTrue(StrMatcher.charSetMatcher("a") instanceof StrMatcher.CharMatcher);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testCharSetMatcher_charArray() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace".toCharArray());
        assertEquals(1, matcher.isMatch(BUFFER2, 0));
        assertEquals(0, matcher.isMatch(BUFFER2, 1));
        assertEquals(1, matcher.isMatch(BUFFER2, 2));
        assertEquals(0, matcher.isMatch(BUFFER2, 3));
        assertEquals(1, matcher.isMatch(BUFFER2, 4));
        assertEquals(0, matcher.isMatch(BUFFER2, 5));
        assertSame(StrMatcher.noneMatcher(), StrMatcher.charSetMatcher());
        assertSame(StrMatcher.noneMatcher(), StrMatcher.charSetMatcher((char[]) null));
        assertTrue(StrMatcher.charSetMatcher("a".toCharArray()) instanceof StrMatcher.CharMatcher);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testStringMatcher_String() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(0, matcher.isMatch(BUFFER2, 0));
        assertEquals(2, matcher.isMatch(BUFFER2, 1));
        assertEquals(0, matcher.isMatch(BUFFER2, 2));
        assertEquals(0, matcher.isMatch(BUFFER2, 3));
        assertEquals(0, matcher.isMatch(BUFFER2, 4));
        assertEquals(0, matcher.isMatch(BUFFER2, 5));
        assertSame(StrMatcher.noneMatcher(), StrMatcher.stringMatcher(""));
        assertSame(StrMatcher.noneMatcher(), StrMatcher.stringMatcher(null));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testMatcherIndices() {
        // remember that the API contract is tight for the isMatch() method
        // all the onus is on the caller, so invalid inputs are not
        // the concern of StrMatcher, and are not bugs
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(2, matcher.isMatch(BUFFER2, 1, 1, BUFFER2.length));
        assertEquals(2, matcher.isMatch(BUFFER2, 1, 0, 3));
        assertEquals(0, matcher.isMatch(BUFFER2, 1, 0, 2));
    }

    @Test
    public void testCommaMatcher_1_oe() {
        final StrMatcher matcher = StrMatcher.commaMatcher();
        assertSame(matcher, StrMatcher.commaMatcher());
    }

    @Test
    public void testCommaMatcher_2_oe() {
        final StrMatcher matcher = StrMatcher.commaMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 0));
    }

    @Test
    public void testCommaMatcher_3_oe() {
        final StrMatcher matcher = StrMatcher.commaMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 1));
    }

    @Test
    public void testCommaMatcher_4_oe() {
        final StrMatcher matcher = StrMatcher.commaMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 2));
    }

    @Test
    public void testTabMatcher_1_oe() {
        final StrMatcher matcher = StrMatcher.tabMatcher();
        assertSame(matcher, StrMatcher.tabMatcher());
    }

    @Test
    public void testTabMatcher_2_oe() {
        final StrMatcher matcher = StrMatcher.tabMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 2));
    }

    @Test
    public void testTabMatcher_3_oe() {
        final StrMatcher matcher = StrMatcher.tabMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 3));
    }

    @Test
    public void testTabMatcher_4_oe() {
        final StrMatcher matcher = StrMatcher.tabMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 4));
    }

    @Test
    public void testSpaceMatcher_1_oe() {
        final StrMatcher matcher = StrMatcher.spaceMatcher();
        assertSame(matcher, StrMatcher.spaceMatcher());
    }

    @Test
    public void testSpaceMatcher_2_oe() {
        final StrMatcher matcher = StrMatcher.spaceMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 4));
    }

    @Test
    public void testSpaceMatcher_3_oe() {
        final StrMatcher matcher = StrMatcher.spaceMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 5));
    }

    @Test
    public void testSpaceMatcher_4_oe() {
        final StrMatcher matcher = StrMatcher.spaceMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 6));
    }

    @Test
    public void testSplitMatcher_1_oe() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertSame(matcher, StrMatcher.splitMatcher());
    }

    @Test
    public void testSplitMatcher_2_oe() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 2));
    }

    @Test
    public void testSplitMatcher_3_oe() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 3));
    }

    @Test
    public void testSplitMatcher_4_oe() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 4));
    }

    @Test
    public void testSplitMatcher_5_oe() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 5));
    }

    @Test
    public void testSplitMatcher_6_oe() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 6));
    }

    @Test
    public void testSplitMatcher_7_oe() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 7));
    }

    @Test
    public void testSplitMatcher_8_oe() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 8));
    }

    @Test
    public void testSplitMatcher_9_oe() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 9));
    }

    @Test
    public void testSplitMatcher_10_oe() {
        final StrMatcher matcher = StrMatcher.splitMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 10));
    }

    @Test
    public void testTrimMatcher_1_oe() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertSame(matcher, StrMatcher.trimMatcher());
    }

    @Test
    public void testTrimMatcher_2_oe() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 2));
    }

    @Test
    public void testTrimMatcher_3_oe() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 3));
    }

    @Test
    public void testTrimMatcher_4_oe() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 4));
    }

    @Test
    public void testTrimMatcher_5_oe() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 5));
    }

    @Test
    public void testTrimMatcher_6_oe() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 6));
    }

    @Test
    public void testTrimMatcher_7_oe() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 7));
    }

    @Test
    public void testTrimMatcher_8_oe() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 8));
    }

    @Test
    public void testTrimMatcher_9_oe() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 9));
    }

    @Test
    public void testTrimMatcher_10_oe() {
        final StrMatcher matcher = StrMatcher.trimMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 10));
    }

    @Test
    public void testSingleQuoteMatcher_1_oe() {
        final StrMatcher matcher = StrMatcher.singleQuoteMatcher();
        assertSame(matcher, StrMatcher.singleQuoteMatcher());
    }

    @Test
    public void testSingleQuoteMatcher_2_oe() {
        final StrMatcher matcher = StrMatcher.singleQuoteMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 10));
    }

    @Test
    public void testSingleQuoteMatcher_3_oe() {
        final StrMatcher matcher = StrMatcher.singleQuoteMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 11));
    }

    @Test
    public void testSingleQuoteMatcher_4_oe() {
        final StrMatcher matcher = StrMatcher.singleQuoteMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 12));
    }

    @Test
    public void testDoubleQuoteMatcher_1_oe() {
        final StrMatcher matcher = StrMatcher.doubleQuoteMatcher();
        assertSame(matcher, StrMatcher.doubleQuoteMatcher());
    }

    @Test
    public void testDoubleQuoteMatcher_2_oe() {
        final StrMatcher matcher = StrMatcher.doubleQuoteMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 11));
    }

    @Test
    public void testDoubleQuoteMatcher_3_oe() {
        final StrMatcher matcher = StrMatcher.doubleQuoteMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 12));
    }

    @Test
    public void testQuoteMatcher_1_oe() {
        final StrMatcher matcher = StrMatcher.quoteMatcher();
        assertSame(matcher, StrMatcher.quoteMatcher());
    }

    @Test
    public void testQuoteMatcher_2_oe() {
        final StrMatcher matcher = StrMatcher.quoteMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 10));
    }

    @Test
    public void testQuoteMatcher_3_oe() {
        final StrMatcher matcher = StrMatcher.quoteMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 11));
    }

    @Test
    public void testQuoteMatcher_4_oe() {
        final StrMatcher matcher = StrMatcher.quoteMatcher();
        assertEquals(1, matcher.isMatch(BUFFER1, 12));
    }

    @Test
    public void testNoneMatcher_1_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertSame(matcher, StrMatcher.noneMatcher());
    }

    @Test
    public void testNoneMatcher_2_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 0));
    }

    @Test
    public void testNoneMatcher_3_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 1));
    }

    @Test
    public void testNoneMatcher_4_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 2));
    }

    @Test
    public void testNoneMatcher_5_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 3));
    }

    @Test
    public void testNoneMatcher_6_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 4));
    }

    @Test
    public void testNoneMatcher_7_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 5));
    }

    @Test
    public void testNoneMatcher_8_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 6));
    }

    @Test
    public void testNoneMatcher_9_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 7));
    }

    @Test
    public void testNoneMatcher_10_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 8));
    }

    @Test
    public void testNoneMatcher_11_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 9));
    }

    @Test
    public void testNoneMatcher_12_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 10));
    }

    @Test
    public void testNoneMatcher_13_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 11));
    }

    @Test
    public void testNoneMatcher_14_oe() {
        final StrMatcher matcher = StrMatcher.noneMatcher();
        assertEquals(0, matcher.isMatch(BUFFER1, 12));
    }

    @Test
    public void testCharMatcher_char_1_oe() {
        final StrMatcher matcher = StrMatcher.charMatcher('c');
        assertEquals(0, matcher.isMatch(BUFFER2, 0));
    }

    @Test
    public void testCharMatcher_char_2_oe() {
        final StrMatcher matcher = StrMatcher.charMatcher('c');
        assertEquals(0, matcher.isMatch(BUFFER2, 1));
    }

    @Test
    public void testCharMatcher_char_3_oe() {
        final StrMatcher matcher = StrMatcher.charMatcher('c');
        assertEquals(1, matcher.isMatch(BUFFER2, 2));
    }

    @Test
    public void testCharMatcher_char_4_oe() {
        final StrMatcher matcher = StrMatcher.charMatcher('c');
        assertEquals(0, matcher.isMatch(BUFFER2, 3));
    }

    @Test
    public void testCharMatcher_char_5_oe() {
        final StrMatcher matcher = StrMatcher.charMatcher('c');
        assertEquals(0, matcher.isMatch(BUFFER2, 4));
    }

    @Test
    public void testCharMatcher_char_6_oe() {
        final StrMatcher matcher = StrMatcher.charMatcher('c');
        assertEquals(0, matcher.isMatch(BUFFER2, 5));
    }

    @Test
    public void testCharSetMatcher_String_1_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace");
        assertEquals(1, matcher.isMatch(BUFFER2, 0));
    }

    @Test
    public void testCharSetMatcher_String_2_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace");
        assertEquals(0, matcher.isMatch(BUFFER2, 1));
    }

    @Test
    public void testCharSetMatcher_String_3_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace");
        assertEquals(1, matcher.isMatch(BUFFER2, 2));
    }

    @Test
    public void testCharSetMatcher_String_4_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace");
        assertEquals(0, matcher.isMatch(BUFFER2, 3));
    }

    @Test
    public void testCharSetMatcher_String_5_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace");
        assertEquals(1, matcher.isMatch(BUFFER2, 4));
    }

    @Test
    public void testCharSetMatcher_String_6_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace");
        assertEquals(0, matcher.isMatch(BUFFER2, 5));
    }

    @Test
    public void testCharSetMatcher_String_7_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace");
        assertSame(StrMatcher.noneMatcher(), StrMatcher.charSetMatcher(""));
    }

    @Test
    public void testCharSetMatcher_String_8_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace");
        assertSame(StrMatcher.noneMatcher(), StrMatcher.charSetMatcher((String) null));
    }

    @Test
    public void testCharSetMatcher_String_9_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace");
        assertTrue(StrMatcher.charSetMatcher("a") instanceof StrMatcher.CharMatcher);
    }

    @Test
    public void testCharSetMatcher_charArray_1_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace".toCharArray());
        assertEquals(1, matcher.isMatch(BUFFER2, 0));
    }

    @Test
    public void testCharSetMatcher_charArray_2_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace".toCharArray());
        assertEquals(0, matcher.isMatch(BUFFER2, 1));
    }

    @Test
    public void testCharSetMatcher_charArray_3_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace".toCharArray());
        assertEquals(1, matcher.isMatch(BUFFER2, 2));
    }

    @Test
    public void testCharSetMatcher_charArray_4_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace".toCharArray());
        assertEquals(0, matcher.isMatch(BUFFER2, 3));
    }

    @Test
    public void testCharSetMatcher_charArray_5_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace".toCharArray());
        assertEquals(1, matcher.isMatch(BUFFER2, 4));
    }

    @Test
    public void testCharSetMatcher_charArray_6_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace".toCharArray());
        assertEquals(0, matcher.isMatch(BUFFER2, 5));
    }

    @Test
    public void testCharSetMatcher_charArray_7_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace".toCharArray());
        assertSame(StrMatcher.noneMatcher(), StrMatcher.charSetMatcher());
    }

    @Test
    public void testCharSetMatcher_charArray_8_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace".toCharArray());
        assertSame(StrMatcher.noneMatcher(), StrMatcher.charSetMatcher((char[]) null));
    }

    @Test
    public void testCharSetMatcher_charArray_9_oe() {
        final StrMatcher matcher = StrMatcher.charSetMatcher("ace".toCharArray());
        assertTrue(StrMatcher.charSetMatcher("a".toCharArray()) instanceof StrMatcher.CharMatcher);
    }

    @Test
    public void testStringMatcher_String_1_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(0, matcher.isMatch(BUFFER2, 0));
    }

    @Test
    public void testStringMatcher_String_2_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(2, matcher.isMatch(BUFFER2, 1));
    }

    @Test
    public void testStringMatcher_String_3_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(0, matcher.isMatch(BUFFER2, 2));
    }

    @Test
    public void testStringMatcher_String_4_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(0, matcher.isMatch(BUFFER2, 3));
    }

    @Test
    public void testStringMatcher_String_5_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(0, matcher.isMatch(BUFFER2, 4));
    }

    @Test
    public void testStringMatcher_String_6_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(0, matcher.isMatch(BUFFER2, 5));
    }

    @Test
    public void testStringMatcher_String_7_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertSame(StrMatcher.noneMatcher(), StrMatcher.stringMatcher(""));
    }

    @Test
    public void testStringMatcher_String_8_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertSame(StrMatcher.noneMatcher(), StrMatcher.stringMatcher(null));
    }

    @Test
    public void testMatcherIndices_1_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(2, matcher.isMatch(BUFFER2, 1, 1, BUFFER2.length));
    }

    @Test
    public void testMatcherIndices_2_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(2, matcher.isMatch(BUFFER2, 1, 0, 3));
    }

    @Test
    public void testMatcherIndices_3_oe() {
        final StrMatcher matcher = StrMatcher.stringMatcher("bc");
        assertEquals(0, matcher.isMatch(BUFFER2, 1, 0, 2));
    }

}
