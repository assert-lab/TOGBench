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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Substring methods
 */
public class StringUtilsSubstringTest_OE25Dev  {
    private static final String FOO = "foo";
    private static final String BAR = "bar";
    private static final String BAZ = "baz";
    private static final String FOOBAR = "foobar";
    private static final String SENTENCE = "foo bar baz";

    //-----------------------------------------------------------------------

    @Test
    public void testSubstring_StringInt() {
        assertNull(StringUtils.substring(null, 0));
        assertEquals("", StringUtils.substring("", 0));
        assertEquals("", StringUtils.substring("", 2));

        assertEquals("", StringUtils.substring(SENTENCE, 80));
        assertEquals(BAZ, StringUtils.substring(SENTENCE, 8));
        assertEquals(BAZ, StringUtils.substring(SENTENCE, -3));
        assertEquals(SENTENCE, StringUtils.substring(SENTENCE, 0));
        assertEquals("abc", StringUtils.substring("abc", -4));
        assertEquals("abc", StringUtils.substring("abc", -3));
        assertEquals("bc", StringUtils.substring("abc", -2));
        assertEquals("c", StringUtils.substring("abc", -1));
        assertEquals("abc", StringUtils.substring("abc", 0));
        assertEquals("bc", StringUtils.substring("abc", 1));
        assertEquals("c", StringUtils.substring("abc", 2));
        assertEquals("", StringUtils.substring("abc", 3));
        assertEquals("", StringUtils.substring("abc", 4));
    }

    @Test
    public void testSubstring_StringIntInt() {
        assertNull(StringUtils.substring(null, 0, 0));
        assertNull(StringUtils.substring(null, 1, 2));
        assertEquals("", StringUtils.substring("", 0, 0));
        assertEquals("", StringUtils.substring("", 1, 2));
        assertEquals("", StringUtils.substring("", -2, -1));

        assertEquals("", StringUtils.substring(SENTENCE, 8, 6));
        assertEquals(FOO, StringUtils.substring(SENTENCE, 0, 3));
        assertEquals("o", StringUtils.substring(SENTENCE, -9, 3));
        assertEquals(FOO, StringUtils.substring(SENTENCE, 0, -8));
        assertEquals("o", StringUtils.substring(SENTENCE, -9, -8));
        assertEquals(SENTENCE, StringUtils.substring(SENTENCE, 0, 80));
        assertEquals("", StringUtils.substring(SENTENCE, 2, 2));
        assertEquals("b", StringUtils.substring("abc", -2, -1));
    }

    @Test
    public void testLeft_String() {
        assertSame(null, StringUtils.left(null, -1));
        assertSame(null, StringUtils.left(null, 0));
        assertSame(null, StringUtils.left(null, 2));

        assertEquals("", StringUtils.left("", -1));
        assertEquals("", StringUtils.left("", 0));
        assertEquals("", StringUtils.left("", 2));

        assertEquals("", StringUtils.left(FOOBAR, -1));
        assertEquals("", StringUtils.left(FOOBAR, 0));
        assertEquals(FOO, StringUtils.left(FOOBAR, 3));
        assertSame(FOOBAR, StringUtils.left(FOOBAR, 80));
    }

    @Test
    public void testRight_String() {
        assertSame(null, StringUtils.right(null, -1));
        assertSame(null, StringUtils.right(null, 0));
        assertSame(null, StringUtils.right(null, 2));

        assertEquals("", StringUtils.right("", -1));
        assertEquals("", StringUtils.right("", 0));
        assertEquals("", StringUtils.right("", 2));

        assertEquals("", StringUtils.right(FOOBAR, -1));
        assertEquals("", StringUtils.right(FOOBAR, 0));
        assertEquals(BAR, StringUtils.right(FOOBAR, 3));
        assertSame(FOOBAR, StringUtils.right(FOOBAR, 80));
    }

    @Test
    public void testMid_String() {
        assertSame(null, StringUtils.mid(null, -1, 0));
        assertSame(null, StringUtils.mid(null, 0, -1));
        assertSame(null, StringUtils.mid(null, 3, 0));
        assertSame(null, StringUtils.mid(null, 3, 2));

        assertEquals("", StringUtils.mid("", 0, -1));
        assertEquals("", StringUtils.mid("", 0, 0));
        assertEquals("", StringUtils.mid("", 0, 2));

        assertEquals("", StringUtils.mid(FOOBAR, 3, -1));
        assertEquals("", StringUtils.mid(FOOBAR, 3, 0));
        assertEquals("b", StringUtils.mid(FOOBAR, 3, 1));
        assertEquals(FOO, StringUtils.mid(FOOBAR, 0, 3));
        assertEquals(BAR, StringUtils.mid(FOOBAR, 3, 3));
        assertEquals(FOOBAR, StringUtils.mid(FOOBAR, 0, 80));
        assertEquals(BAR, StringUtils.mid(FOOBAR, 3, 80));
        assertEquals("", StringUtils.mid(FOOBAR, 9, 3));
        assertEquals(FOO, StringUtils.mid(FOOBAR, -1, 3));
    }

    @Test
    public void testSubstringBefore_StringInt() {
        assertEquals("foo", StringUtils.substringBefore("fooXXbarXXbaz", 'X'));

        assertNull(StringUtils.substringBefore(null, 0));
        assertNull(StringUtils.substringBefore(null, 'X'));
        assertEquals("", StringUtils.substringBefore("", 0));
        assertEquals("", StringUtils.substringBefore("", 'X'));

        assertEquals("foo", StringUtils.substringBefore("foo", 0));
        assertEquals("foo", StringUtils.substringBefore("foo", 'b'));
        assertEquals("f", StringUtils.substringBefore("foot", 'o'));
        assertEquals("", StringUtils.substringBefore("abc", 'a'));
        assertEquals("a", StringUtils.substringBefore("abcba", 'b'));
        assertEquals("ab", StringUtils.substringBefore("abc", 'c'));
        assertEquals("abc", StringUtils.substringBefore("abc", 0));
    }

    @Test
    public void testSubstringBefore_StringString() {
        assertEquals("foo", StringUtils.substringBefore("fooXXbarXXbaz", "XX"));

        assertNull(StringUtils.substringBefore(null, null));
        assertNull(StringUtils.substringBefore(null, ""));
        assertNull(StringUtils.substringBefore(null, "XX"));
        assertEquals("", StringUtils.substringBefore("", null));
        assertEquals("", StringUtils.substringBefore("", ""));
        assertEquals("", StringUtils.substringBefore("", "XX"));

        assertEquals("foo", StringUtils.substringBefore("foo", null));
        assertEquals("foo", StringUtils.substringBefore("foo", "b"));
        assertEquals("f", StringUtils.substringBefore("foot", "o"));
        assertEquals("", StringUtils.substringBefore("abc", "a"));
        assertEquals("a", StringUtils.substringBefore("abcba", "b"));
        assertEquals("ab", StringUtils.substringBefore("abc", "c"));
        assertEquals("", StringUtils.substringBefore("abc", ""));
        assertEquals("abc", StringUtils.substringBefore("abc", "X"));
    }

    @Test
    public void testSubstringAfter_StringString() {
        assertEquals("barXXbaz", StringUtils.substringAfter("fooXXbarXXbaz", "XX"));

        assertNull(StringUtils.substringAfter(null, null));
        assertNull(StringUtils.substringAfter(null, ""));
        assertNull(StringUtils.substringAfter(null, "XX"));
        assertEquals("", StringUtils.substringAfter("", null));
        assertEquals("", StringUtils.substringAfter("", ""));
        assertEquals("", StringUtils.substringAfter("", "XX"));

        assertEquals("", StringUtils.substringAfter("foo", null));
        assertEquals("ot", StringUtils.substringAfter("foot", "o"));
        assertEquals("bc", StringUtils.substringAfter("abc", "a"));
        assertEquals("cba", StringUtils.substringAfter("abcba", "b"));
        assertEquals("", StringUtils.substringAfter("abc", "c"));
        assertEquals("abc", StringUtils.substringAfter("abc", ""));
        assertEquals("", StringUtils.substringAfter("abc", "d"));
    }

    @Test
    public void testSubstringAfter_StringInt() {
        assertNull(StringUtils.substringAfter(null, 0));
        assertNull(StringUtils.substringAfter(null, 'X'));
        assertEquals("", StringUtils.substringAfter("", 0));
        assertEquals("", StringUtils.substringAfter("", 'X'));

        assertEquals("", StringUtils.substringAfter("foo", 0));
        assertEquals("ot", StringUtils.substringAfter("foot", 'o'));
        assertEquals("bc", StringUtils.substringAfter("abc", 'a'));
        assertEquals("cba", StringUtils.substringAfter("abcba", 'b'));
        assertEquals("", StringUtils.substringAfter("abc", 'c'));
        assertEquals("", StringUtils.substringAfter("abc", 'd'));
    }

    @Test
    public void testSubstringBeforeLast_StringString() {
        assertEquals("fooXXbar", StringUtils.substringBeforeLast("fooXXbarXXbaz", "XX"));

        assertNull(StringUtils.substringBeforeLast(null, null));
        assertNull(StringUtils.substringBeforeLast(null, ""));
        assertNull(StringUtils.substringBeforeLast(null, "XX"));
        assertEquals("", StringUtils.substringBeforeLast("", null));
        assertEquals("", StringUtils.substringBeforeLast("", ""));
        assertEquals("", StringUtils.substringBeforeLast("", "XX"));

        assertEquals("foo", StringUtils.substringBeforeLast("foo", null));
        assertEquals("foo", StringUtils.substringBeforeLast("foo", "b"));
        assertEquals("fo", StringUtils.substringBeforeLast("foo", "o"));
        assertEquals("abc\r\n", StringUtils.substringBeforeLast("abc\r\n", "d"));
        assertEquals("abc", StringUtils.substringBeforeLast("abcdabc", "d"));
        assertEquals("abcdabc", StringUtils.substringBeforeLast("abcdabcd", "d"));
        assertEquals("a", StringUtils.substringBeforeLast("abc", "b"));
        assertEquals("abc ", StringUtils.substringBeforeLast("abc \n", "\n"));
        assertEquals("a", StringUtils.substringBeforeLast("a", null));
        assertEquals("a", StringUtils.substringBeforeLast("a", ""));
        assertEquals("", StringUtils.substringBeforeLast("a", "a"));
    }

    @Test
    public void testSubstringAfterLast_StringString() {
        assertEquals("baz", StringUtils.substringAfterLast("fooXXbarXXbaz", "XX"));

        assertNull(StringUtils.substringAfterLast(null, null));
        assertNull(StringUtils.substringAfterLast(null, ""));
        assertNull(StringUtils.substringAfterLast(null, "XX"));
        assertEquals("", StringUtils.substringAfterLast("", null));
        assertEquals("", StringUtils.substringAfterLast("", ""));
        assertEquals("", StringUtils.substringAfterLast("", "a"));

        assertEquals("", StringUtils.substringAfterLast("foo", null));
        assertEquals("", StringUtils.substringAfterLast("foo", "b"));
        assertEquals("t", StringUtils.substringAfterLast("foot", "o"));
        assertEquals("bc", StringUtils.substringAfterLast("abc", "a"));
        assertEquals("a", StringUtils.substringAfterLast("abcba", "b"));
        assertEquals("", StringUtils.substringAfterLast("abc", "c"));
        assertEquals("", StringUtils.substringAfterLast("", "d"));
        assertEquals("", StringUtils.substringAfterLast("abc", ""));
    }

    @Test
    public void testSubstringAfterLast_StringInt() {
        assertNull(StringUtils.substringAfterLast(null, 0));
        assertNull(StringUtils.substringAfterLast(null, 'X'));
        assertEquals("", StringUtils.substringAfterLast("", 0));
        assertEquals("", StringUtils.substringAfterLast("", 'a'));

        assertEquals("", StringUtils.substringAfterLast("foo", 0));
        assertEquals("", StringUtils.substringAfterLast("foo", 'b'));
        assertEquals("t", StringUtils.substringAfterLast("foot", 'o'));
        assertEquals("bc", StringUtils.substringAfterLast("abc", 'a'));
        assertEquals("a", StringUtils.substringAfterLast("abcba", 'b'));
        assertEquals("", StringUtils.substringAfterLast("abc", 'c'));
        assertEquals("", StringUtils.substringAfterLast("", 'd'));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testSubstringBetween_StringString() {
        assertNull(StringUtils.substringBetween(null, "tag"));
        assertEquals("", StringUtils.substringBetween("", ""));
        assertNull(StringUtils.substringBetween("", "abc"));
        assertEquals("", StringUtils.substringBetween("    ", " "));
        assertNull(StringUtils.substringBetween("abc", null));
        assertEquals("", StringUtils.substringBetween("abc", ""));
        assertNull(StringUtils.substringBetween("abc", "a"));
        assertEquals("bc", StringUtils.substringBetween("abca", "a"));
        assertEquals("bc", StringUtils.substringBetween("abcabca", "a"));
        assertEquals("bar", StringUtils.substringBetween("\nbar\n", "\n"));
    }

    @Test
    public void testSubstringBetween_StringStringString() {
        assertNull(StringUtils.substringBetween(null, "", ""));
        assertNull(StringUtils.substringBetween("", null, ""));
        assertNull(StringUtils.substringBetween("", "", null));
        assertEquals("", StringUtils.substringBetween("", "", ""));
        assertEquals("", StringUtils.substringBetween("foo", "", ""));
        assertNull(StringUtils.substringBetween("foo", "", "]"));
        assertNull(StringUtils.substringBetween("foo", "[", "]"));
        assertEquals("", StringUtils.substringBetween("    ", " ", "  "));
        assertEquals("bar", StringUtils.substringBetween("<foo>bar</foo>", "<foo>", "</foo>") );
    }

   /**
     * Tests the substringsBetween method that returns an String Array of substrings.
     */
    @Test
    public void testSubstringsBetween_StringStringString() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");
        assertEquals(3, results.length);
        assertEquals("one", results[0]);
        assertEquals("two", results[1]);
        assertEquals("three", results[2]);

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");
        assertEquals(2, results.length);
        assertEquals("one", results[0]);
        assertEquals("two", results[1]);

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");
        assertEquals(2, results.length);
        assertEquals("one", results[0]);
        assertEquals("two", results[1]);

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");
        assertEquals(1, results.length);
        assertEquals("one", results[0]);

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");
        assertEquals(1, results.length);
        assertEquals("three", results[0]);

        // 'ab hello ba' will match, but 'ab non ba' won't
        // this is because the 'a' is shared between the two and can't be matched twice
        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");
        assertEquals(1, results.length);
        assertEquals("hello", results[0]);

        results = StringUtils.substringsBetween("one, two, three", "[", "]");
        assertNull(results);

        results = StringUtils.substringsBetween("[one, two, three", "[", "]");
        assertNull(results);

        results = StringUtils.substringsBetween("one, two, three]", "[", "]");
        assertNull(results);

        results = StringUtils.substringsBetween("[one], [two], [three]", "[", null);
        assertNull(results);

        results = StringUtils.substringsBetween("[one], [two], [three]", null, "]");
        assertNull(results);

        results = StringUtils.substringsBetween("[one], [two], [three]", "", "");
        assertNull(results);

        results = StringUtils.substringsBetween(null, "[", "]");
        assertNull(results);

        results = StringUtils.substringsBetween("", "[", "]");
        assertEquals(0, results.length);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testCountMatches_String() {
        assertEquals(0, StringUtils.countMatches(null, null));
        assertEquals(0, StringUtils.countMatches("blah", null));
        assertEquals(0, StringUtils.countMatches(null, "DD"));

        assertEquals(0, StringUtils.countMatches("x", ""));
        assertEquals(0, StringUtils.countMatches("", ""));

        assertEquals(3,StringUtils.countMatches("one long someone sentence of one","one"));
        assertEquals(0,StringUtils.countMatches("one long someone sentence of one","two"));
        assertEquals(4,StringUtils.countMatches("oooooooooooo","ooo"));
        assertEquals(0, StringUtils.countMatches(null, "?"));
        assertEquals(0, StringUtils.countMatches("", "?"));
        assertEquals(0, StringUtils.countMatches("abba", null));
        assertEquals(0, StringUtils.countMatches("abba", ""));
        assertEquals(2, StringUtils.countMatches("abba", "a"));
        assertEquals(1, StringUtils.countMatches("abba", "ab"));
        assertEquals(0, StringUtils.countMatches("abba", "xxx"));
        assertEquals(1, StringUtils.countMatches("ababa", "aba"));
    }

    @Test
    public void testCountMatches_char() {
        assertEquals(0, StringUtils.countMatches(null, 'D'));
        assertEquals(5, StringUtils.countMatches("one long someone sentence of one", ' '));
        assertEquals(6, StringUtils.countMatches("one long someone sentence of one", 'o'));
        assertEquals(4, StringUtils.countMatches("oooooooooooo", "ooo"));
    }

    @Test
    public void testSubstring_StringInt_1_oe() {
        assertNull(StringUtils.substring(null, 0));
    }

    @Test
    public void testSubstring_StringInt_2_oe() {
        assertEquals("", StringUtils.substring("", 0));
    }

    @Test
    public void testSubstring_StringInt_3_oe() {
        assertEquals("", StringUtils.substring("", 2));
    }

    @Test
    public void testSubstring_StringInt_4_oe() {

        assertEquals("", StringUtils.substring(SENTENCE, 80));
    }

    @Test
    public void testSubstring_StringInt_5_oe() {

        assertEquals(BAZ, StringUtils.substring(SENTENCE, 8));
    }

    @Test
    public void testSubstring_StringInt_6_oe() {

        assertEquals(BAZ, StringUtils.substring(SENTENCE, -3));
    }

    @Test
    public void testSubstring_StringInt_7_oe() {

        assertEquals(SENTENCE, StringUtils.substring(SENTENCE, 0));
    }

    @Test
    public void testSubstring_StringInt_8_oe() {

        assertEquals("abc", StringUtils.substring("abc", -4));
    }

    @Test
    public void testSubstring_StringInt_9_oe() {

        assertEquals("abc", StringUtils.substring("abc", -3));
    }

    @Test
    public void testSubstring_StringInt_10_oe() {

        assertEquals("bc", StringUtils.substring("abc", -2));
    }

    @Test
    public void testSubstring_StringInt_11_oe() {

        assertEquals("c", StringUtils.substring("abc", -1));
    }

    @Test
    public void testSubstring_StringInt_12_oe() {

        assertEquals("abc", StringUtils.substring("abc", 0));
    }

    @Test
    public void testSubstring_StringInt_13_oe() {

        assertEquals("bc", StringUtils.substring("abc", 1));
    }

    @Test
    public void testSubstring_StringInt_14_oe() {

        assertEquals("c", StringUtils.substring("abc", 2));
    }

    @Test
    public void testSubstring_StringInt_15_oe() {

        assertEquals("", StringUtils.substring("abc", 3));
    }

    @Test
    public void testSubstring_StringInt_16_oe() {

        assertEquals("", StringUtils.substring("abc", 4));
    }

    @Test
    public void testSubstring_StringIntInt_1_oe() {
        assertNull(StringUtils.substring(null, 0, 0));
    }

    @Test
    public void testSubstring_StringIntInt_2_oe() {
        assertNull(StringUtils.substring(null, 1, 2));
    }

    @Test
    public void testSubstring_StringIntInt_3_oe() {
        assertEquals("", StringUtils.substring("", 0, 0));
    }

    @Test
    public void testSubstring_StringIntInt_4_oe() {
        assertEquals("", StringUtils.substring("", 1, 2));
    }

    @Test
    public void testSubstring_StringIntInt_5_oe() {
        assertEquals("", StringUtils.substring("", -2, -1));
    }

    @Test
    public void testSubstring_StringIntInt_6_oe() {

        assertEquals("", StringUtils.substring(SENTENCE, 8, 6));
    }

    @Test
    public void testSubstring_StringIntInt_7_oe() {

        assertEquals(FOO, StringUtils.substring(SENTENCE, 0, 3));
    }

    @Test
    public void testSubstring_StringIntInt_8_oe() {

        assertEquals("o", StringUtils.substring(SENTENCE, -9, 3));
    }

    @Test
    public void testSubstring_StringIntInt_9_oe() {

        assertEquals(FOO, StringUtils.substring(SENTENCE, 0, -8));
    }

    @Test
    public void testSubstring_StringIntInt_10_oe() {

        assertEquals("o", StringUtils.substring(SENTENCE, -9, -8));
    }

    @Test
    public void testSubstring_StringIntInt_11_oe() {

        assertEquals(SENTENCE, StringUtils.substring(SENTENCE, 0, 80));
    }

    @Test
    public void testSubstring_StringIntInt_12_oe() {

        assertEquals("", StringUtils.substring(SENTENCE, 2, 2));
    }

    @Test
    public void testSubstring_StringIntInt_13_oe() {

        assertEquals("b", StringUtils.substring("abc", -2, -1));
    }

    @Test
    public void testLeft_String_1_oe() {
        assertSame(null, StringUtils.left(null, -1));
    }

    @Test
    public void testLeft_String_2_oe() {
        assertSame(null, StringUtils.left(null, 0));
    }

    @Test
    public void testLeft_String_3_oe() {
        assertSame(null, StringUtils.left(null, 2));
    }

    @Test
    public void testLeft_String_4_oe() {

        assertEquals("", StringUtils.left("", -1));
    }

    @Test
    public void testLeft_String_5_oe() {

        assertEquals("", StringUtils.left("", 0));
    }

    @Test
    public void testLeft_String_6_oe() {

        assertEquals("", StringUtils.left("", 2));
    }

    @Test
    public void testLeft_String_7_oe() {


        assertEquals("", StringUtils.left(FOOBAR, -1));
    }

    @Test
    public void testLeft_String_8_oe() {


        assertEquals("", StringUtils.left(FOOBAR, 0));
    }

    @Test
    public void testLeft_String_9_oe() {


        assertEquals(FOO, StringUtils.left(FOOBAR, 3));
    }

    @Test
    public void testLeft_String_10_oe() {


        assertSame(FOOBAR, StringUtils.left(FOOBAR, 80));
    }

    @Test
    public void testRight_String_1_oe() {
        assertSame(null, StringUtils.right(null, -1));
    }

    @Test
    public void testRight_String_2_oe() {
        assertSame(null, StringUtils.right(null, 0));
    }

    @Test
    public void testRight_String_3_oe() {
        assertSame(null, StringUtils.right(null, 2));
    }

    @Test
    public void testRight_String_4_oe() {

        assertEquals("", StringUtils.right("", -1));
    }

    @Test
    public void testRight_String_5_oe() {

        assertEquals("", StringUtils.right("", 0));
    }

    @Test
    public void testRight_String_6_oe() {

        assertEquals("", StringUtils.right("", 2));
    }

    @Test
    public void testRight_String_7_oe() {


        assertEquals("", StringUtils.right(FOOBAR, -1));
    }

    @Test
    public void testRight_String_8_oe() {


        assertEquals("", StringUtils.right(FOOBAR, 0));
    }

    @Test
    public void testRight_String_9_oe() {


        assertEquals(BAR, StringUtils.right(FOOBAR, 3));
    }

    @Test
    public void testRight_String_10_oe() {


        assertSame(FOOBAR, StringUtils.right(FOOBAR, 80));
    }

    @Test
    public void testMid_String_1_oe() {
        assertSame(null, StringUtils.mid(null, -1, 0));
    }

    @Test
    public void testMid_String_2_oe() {
        assertSame(null, StringUtils.mid(null, 0, -1));
    }

    @Test
    public void testMid_String_3_oe() {
        assertSame(null, StringUtils.mid(null, 3, 0));
    }

    @Test
    public void testMid_String_4_oe() {
        assertSame(null, StringUtils.mid(null, 3, 2));
    }

    @Test
    public void testMid_String_5_oe() {

        assertEquals("", StringUtils.mid("", 0, -1));
    }

    @Test
    public void testMid_String_6_oe() {

        assertEquals("", StringUtils.mid("", 0, 0));
    }

    @Test
    public void testMid_String_7_oe() {

        assertEquals("", StringUtils.mid("", 0, 2));
    }

    @Test
    public void testMid_String_8_oe() {


        assertEquals("", StringUtils.mid(FOOBAR, 3, -1));
    }

    @Test
    public void testMid_String_9_oe() {


        assertEquals("", StringUtils.mid(FOOBAR, 3, 0));
    }

    @Test
    public void testMid_String_10_oe() {


        assertEquals("b", StringUtils.mid(FOOBAR, 3, 1));
    }

    @Test
    public void testMid_String_11_oe() {


        assertEquals(FOO, StringUtils.mid(FOOBAR, 0, 3));
    }

    @Test
    public void testMid_String_12_oe() {


        assertEquals(BAR, StringUtils.mid(FOOBAR, 3, 3));
    }

    @Test
    public void testMid_String_13_oe() {


        assertEquals(FOOBAR, StringUtils.mid(FOOBAR, 0, 80));
    }

    @Test
    public void testMid_String_14_oe() {


        assertEquals(BAR, StringUtils.mid(FOOBAR, 3, 80));
    }

    @Test
    public void testMid_String_15_oe() {


        assertEquals("", StringUtils.mid(FOOBAR, 9, 3));
    }

    @Test
    public void testMid_String_16_oe() {


        assertEquals(FOO, StringUtils.mid(FOOBAR, -1, 3));
    }

    @Test
    public void testSubstringBefore_StringInt_1_oe() {
        assertEquals("foo", StringUtils.substringBefore("fooXXbarXXbaz", 'X'));
    }

    @Test
    public void testSubstringBefore_StringInt_2_oe() {

        assertNull(StringUtils.substringBefore(null, 0));
    }

    @Test
    public void testSubstringBefore_StringInt_3_oe() {

        assertNull(StringUtils.substringBefore(null, 'X'));
    }

    @Test
    public void testSubstringBefore_StringInt_4_oe() {

        assertEquals("", StringUtils.substringBefore("", 0));
    }

    @Test
    public void testSubstringBefore_StringInt_5_oe() {

        assertEquals("", StringUtils.substringBefore("", 'X'));
    }

    @Test
    public void testSubstringBefore_StringInt_6_oe() {


        assertEquals("foo", StringUtils.substringBefore("foo", 0));
    }

    @Test
    public void testSubstringBefore_StringInt_7_oe() {


        assertEquals("foo", StringUtils.substringBefore("foo", 'b'));
    }

    @Test
    public void testSubstringBefore_StringInt_8_oe() {


        assertEquals("f", StringUtils.substringBefore("foot", 'o'));
    }

    @Test
    public void testSubstringBefore_StringInt_9_oe() {


        assertEquals("", StringUtils.substringBefore("abc", 'a'));
    }

    @Test
    public void testSubstringBefore_StringInt_10_oe() {


        assertEquals("a", StringUtils.substringBefore("abcba", 'b'));
    }

    @Test
    public void testSubstringBefore_StringInt_11_oe() {


        assertEquals("ab", StringUtils.substringBefore("abc", 'c'));
    }

    @Test
    public void testSubstringBefore_StringInt_12_oe() {


        assertEquals("abc", StringUtils.substringBefore("abc", 0));
    }

    @Test
    public void testSubstringBefore_StringString_1_oe() {
        assertEquals("foo", StringUtils.substringBefore("fooXXbarXXbaz", "XX"));
    }

    @Test
    public void testSubstringBefore_StringString_2_oe() {

        assertNull(StringUtils.substringBefore(null, null));
    }

    @Test
    public void testSubstringBefore_StringString_3_oe() {

        assertNull(StringUtils.substringBefore(null, ""));
    }

    @Test
    public void testSubstringBefore_StringString_4_oe() {

        assertNull(StringUtils.substringBefore(null, "XX"));
    }

    @Test
    public void testSubstringBefore_StringString_5_oe() {

        assertEquals("", StringUtils.substringBefore("", null));
    }

    @Test
    public void testSubstringBefore_StringString_6_oe() {

        assertEquals("", StringUtils.substringBefore("", ""));
    }

    @Test
    public void testSubstringBefore_StringString_7_oe() {

        assertEquals("", StringUtils.substringBefore("", "XX"));
    }

    @Test
    public void testSubstringBefore_StringString_8_oe() {


        assertEquals("foo", StringUtils.substringBefore("foo", null));
    }

    @Test
    public void testSubstringBefore_StringString_9_oe() {


        assertEquals("foo", StringUtils.substringBefore("foo", "b"));
    }

    @Test
    public void testSubstringBefore_StringString_10_oe() {


        assertEquals("f", StringUtils.substringBefore("foot", "o"));
    }

    @Test
    public void testSubstringBefore_StringString_11_oe() {


        assertEquals("", StringUtils.substringBefore("abc", "a"));
    }

    @Test
    public void testSubstringBefore_StringString_12_oe() {


        assertEquals("a", StringUtils.substringBefore("abcba", "b"));
    }

    @Test
    public void testSubstringBefore_StringString_13_oe() {


        assertEquals("ab", StringUtils.substringBefore("abc", "c"));
    }

    @Test
    public void testSubstringBefore_StringString_14_oe() {


        assertEquals("", StringUtils.substringBefore("abc", ""));
    }

    @Test
    public void testSubstringBefore_StringString_15_oe() {


        assertEquals("abc", StringUtils.substringBefore("abc", "X"));
    }

    @Test
    public void testSubstringAfter_StringString_1_oe() {
        assertEquals("barXXbaz", StringUtils.substringAfter("fooXXbarXXbaz", "XX"));
    }

    @Test
    public void testSubstringAfter_StringString_2_oe() {

        assertNull(StringUtils.substringAfter(null, null));
    }

    @Test
    public void testSubstringAfter_StringString_3_oe() {

        assertNull(StringUtils.substringAfter(null, ""));
    }

    @Test
    public void testSubstringAfter_StringString_4_oe() {

        assertNull(StringUtils.substringAfter(null, "XX"));
    }

    @Test
    public void testSubstringAfter_StringString_5_oe() {

        assertEquals("", StringUtils.substringAfter("", null));
    }

    @Test
    public void testSubstringAfter_StringString_6_oe() {

        assertEquals("", StringUtils.substringAfter("", ""));
    }

    @Test
    public void testSubstringAfter_StringString_7_oe() {

        assertEquals("", StringUtils.substringAfter("", "XX"));
    }

    @Test
    public void testSubstringAfter_StringString_8_oe() {


        assertEquals("", StringUtils.substringAfter("foo", null));
    }

    @Test
    public void testSubstringAfter_StringString_9_oe() {


        assertEquals("ot", StringUtils.substringAfter("foot", "o"));
    }

    @Test
    public void testSubstringAfter_StringString_10_oe() {


        assertEquals("bc", StringUtils.substringAfter("abc", "a"));
    }

    @Test
    public void testSubstringAfter_StringString_11_oe() {


        assertEquals("cba", StringUtils.substringAfter("abcba", "b"));
    }

    @Test
    public void testSubstringAfter_StringString_12_oe() {


        assertEquals("", StringUtils.substringAfter("abc", "c"));
    }

    @Test
    public void testSubstringAfter_StringString_13_oe() {


        assertEquals("abc", StringUtils.substringAfter("abc", ""));
    }

    @Test
    public void testSubstringAfter_StringString_14_oe() {


        assertEquals("", StringUtils.substringAfter("abc", "d"));
    }

    @Test
    public void testSubstringAfter_StringInt_1_oe() {
        assertNull(StringUtils.substringAfter(null, 0));
    }

    @Test
    public void testSubstringAfter_StringInt_2_oe() {
        assertNull(StringUtils.substringAfter(null, 'X'));
    }

    @Test
    public void testSubstringAfter_StringInt_3_oe() {
        assertEquals("", StringUtils.substringAfter("", 0));
    }

    @Test
    public void testSubstringAfter_StringInt_4_oe() {
        assertEquals("", StringUtils.substringAfter("", 'X'));
    }

    @Test
    public void testSubstringAfter_StringInt_5_oe() {

        assertEquals("", StringUtils.substringAfter("foo", 0));
    }

    @Test
    public void testSubstringAfter_StringInt_6_oe() {

        assertEquals("ot", StringUtils.substringAfter("foot", 'o'));
    }

    @Test
    public void testSubstringAfter_StringInt_7_oe() {

        assertEquals("bc", StringUtils.substringAfter("abc", 'a'));
    }

    @Test
    public void testSubstringAfter_StringInt_8_oe() {

        assertEquals("cba", StringUtils.substringAfter("abcba", 'b'));
    }

    @Test
    public void testSubstringAfter_StringInt_9_oe() {

        assertEquals("", StringUtils.substringAfter("abc", 'c'));
    }

    @Test
    public void testSubstringAfter_StringInt_10_oe() {

        assertEquals("", StringUtils.substringAfter("abc", 'd'));
    }

    @Test
    public void testSubstringBeforeLast_StringString_1_oe() {
        assertEquals("fooXXbar", StringUtils.substringBeforeLast("fooXXbarXXbaz", "XX"));
    }

    @Test
    public void testSubstringBeforeLast_StringString_2_oe() {

        assertNull(StringUtils.substringBeforeLast(null, null));
    }

    @Test
    public void testSubstringBeforeLast_StringString_3_oe() {

        assertNull(StringUtils.substringBeforeLast(null, ""));
    }

    @Test
    public void testSubstringBeforeLast_StringString_4_oe() {

        assertNull(StringUtils.substringBeforeLast(null, "XX"));
    }

    @Test
    public void testSubstringBeforeLast_StringString_5_oe() {

        assertEquals("", StringUtils.substringBeforeLast("", null));
    }

    @Test
    public void testSubstringBeforeLast_StringString_6_oe() {

        assertEquals("", StringUtils.substringBeforeLast("", ""));
    }

    @Test
    public void testSubstringBeforeLast_StringString_7_oe() {

        assertEquals("", StringUtils.substringBeforeLast("", "XX"));
    }

    @Test
    public void testSubstringBeforeLast_StringString_8_oe() {


        assertEquals("foo", StringUtils.substringBeforeLast("foo", null));
    }

    @Test
    public void testSubstringBeforeLast_StringString_9_oe() {


        assertEquals("foo", StringUtils.substringBeforeLast("foo", "b"));
    }

    @Test
    public void testSubstringBeforeLast_StringString_10_oe() {


        assertEquals("fo", StringUtils.substringBeforeLast("foo", "o"));
    }

    @Test
    public void testSubstringBeforeLast_StringString_11_oe() {


        assertEquals("abc\r\n", StringUtils.substringBeforeLast("abc\r\n", "d"));
    }

    @Test
    public void testSubstringBeforeLast_StringString_12_oe() {


        assertEquals("abc", StringUtils.substringBeforeLast("abcdabc", "d"));
    }

    @Test
    public void testSubstringBeforeLast_StringString_13_oe() {


        assertEquals("abcdabc", StringUtils.substringBeforeLast("abcdabcd", "d"));
    }

    @Test
    public void testSubstringBeforeLast_StringString_14_oe() {


        assertEquals("a", StringUtils.substringBeforeLast("abc", "b"));
    }

    @Test
    public void testSubstringBeforeLast_StringString_15_oe() {


        assertEquals("abc ", StringUtils.substringBeforeLast("abc \n", "\n"));
    }

    @Test
    public void testSubstringBeforeLast_StringString_16_oe() {


        assertEquals("a", StringUtils.substringBeforeLast("a", null));
    }

    @Test
    public void testSubstringBeforeLast_StringString_17_oe() {


        assertEquals("a", StringUtils.substringBeforeLast("a", ""));
    }

    @Test
    public void testSubstringBeforeLast_StringString_18_oe() {


        assertEquals("", StringUtils.substringBeforeLast("a", "a"));
    }

    @Test
    public void testSubstringAfterLast_StringString_1_oe() {
        assertEquals("baz", StringUtils.substringAfterLast("fooXXbarXXbaz", "XX"));
    }

    @Test
    public void testSubstringAfterLast_StringString_2_oe() {

        assertNull(StringUtils.substringAfterLast(null, null));
    }

    @Test
    public void testSubstringAfterLast_StringString_3_oe() {

        assertNull(StringUtils.substringAfterLast(null, ""));
    }

    @Test
    public void testSubstringAfterLast_StringString_4_oe() {

        assertNull(StringUtils.substringAfterLast(null, "XX"));
    }

    @Test
    public void testSubstringAfterLast_StringString_5_oe() {

        assertEquals("", StringUtils.substringAfterLast("", null));
    }

    @Test
    public void testSubstringAfterLast_StringString_6_oe() {

        assertEquals("", StringUtils.substringAfterLast("", ""));
    }

    @Test
    public void testSubstringAfterLast_StringString_7_oe() {

        assertEquals("", StringUtils.substringAfterLast("", "a"));
    }

    @Test
    public void testSubstringAfterLast_StringString_8_oe() {


        assertEquals("", StringUtils.substringAfterLast("foo", null));
    }

    @Test
    public void testSubstringAfterLast_StringString_9_oe() {


        assertEquals("", StringUtils.substringAfterLast("foo", "b"));
    }

    @Test
    public void testSubstringAfterLast_StringString_10_oe() {


        assertEquals("t", StringUtils.substringAfterLast("foot", "o"));
    }

    @Test
    public void testSubstringAfterLast_StringString_11_oe() {


        assertEquals("bc", StringUtils.substringAfterLast("abc", "a"));
    }

    @Test
    public void testSubstringAfterLast_StringString_12_oe() {


        assertEquals("a", StringUtils.substringAfterLast("abcba", "b"));
    }

    @Test
    public void testSubstringAfterLast_StringString_13_oe() {


        assertEquals("", StringUtils.substringAfterLast("abc", "c"));
    }

    @Test
    public void testSubstringAfterLast_StringString_14_oe() {


        assertEquals("", StringUtils.substringAfterLast("", "d"));
    }

    @Test
    public void testSubstringAfterLast_StringString_15_oe() {


        assertEquals("", StringUtils.substringAfterLast("abc", ""));
    }

    @Test
    public void testSubstringAfterLast_StringInt_1_oe() {
        assertNull(StringUtils.substringAfterLast(null, 0));
    }

    @Test
    public void testSubstringAfterLast_StringInt_2_oe() {
        assertNull(StringUtils.substringAfterLast(null, 'X'));
    }

    @Test
    public void testSubstringAfterLast_StringInt_3_oe() {
        assertEquals("", StringUtils.substringAfterLast("", 0));
    }

    @Test
    public void testSubstringAfterLast_StringInt_4_oe() {
        assertEquals("", StringUtils.substringAfterLast("", 'a'));
    }

    @Test
    public void testSubstringAfterLast_StringInt_5_oe() {

        assertEquals("", StringUtils.substringAfterLast("foo", 0));
    }

    @Test
    public void testSubstringAfterLast_StringInt_6_oe() {

        assertEquals("", StringUtils.substringAfterLast("foo", 'b'));
    }

    @Test
    public void testSubstringAfterLast_StringInt_7_oe() {

        assertEquals("t", StringUtils.substringAfterLast("foot", 'o'));
    }

    @Test
    public void testSubstringAfterLast_StringInt_8_oe() {

        assertEquals("bc", StringUtils.substringAfterLast("abc", 'a'));
    }

    @Test
    public void testSubstringAfterLast_StringInt_9_oe() {

        assertEquals("a", StringUtils.substringAfterLast("abcba", 'b'));
    }

    @Test
    public void testSubstringAfterLast_StringInt_10_oe() {

        assertEquals("", StringUtils.substringAfterLast("abc", 'c'));
    }

    @Test
    public void testSubstringAfterLast_StringInt_11_oe() {

        assertEquals("", StringUtils.substringAfterLast("", 'd'));
    }

    @Test
    public void testSubstringBetween_StringString_1_oe() {
        assertNull(StringUtils.substringBetween(null, "tag"));
    }

    @Test
    public void testSubstringBetween_StringString_2_oe() {
        assertEquals("", StringUtils.substringBetween("", ""));
    }

    @Test
    public void testSubstringBetween_StringString_3_oe() {
        assertNull(StringUtils.substringBetween("", "abc"));
    }

    @Test
    public void testSubstringBetween_StringString_4_oe() {
        assertEquals("", StringUtils.substringBetween("    ", " "));
    }

    @Test
    public void testSubstringBetween_StringString_5_oe() {
        assertNull(StringUtils.substringBetween("abc", null));
    }

    @Test
    public void testSubstringBetween_StringString_6_oe() {
        assertEquals("", StringUtils.substringBetween("abc", ""));
    }

    @Test
    public void testSubstringBetween_StringString_7_oe() {
        assertNull(StringUtils.substringBetween("abc", "a"));
    }

    @Test
    public void testSubstringBetween_StringString_8_oe() {
        assertEquals("bc", StringUtils.substringBetween("abca", "a"));
    }

    @Test
    public void testSubstringBetween_StringString_9_oe() {
        assertEquals("bc", StringUtils.substringBetween("abcabca", "a"));
    }

    @Test
    public void testSubstringBetween_StringString_10_oe() {
        assertEquals("bar", StringUtils.substringBetween("\nbar\n", "\n"));
    }

    @Test
    public void testSubstringBetween_StringStringString_1_oe() {
        assertNull(StringUtils.substringBetween(null, "", ""));
    }

    @Test
    public void testSubstringBetween_StringStringString_2_oe() {
        assertNull(StringUtils.substringBetween("", null, ""));
    }

    @Test
    public void testSubstringBetween_StringStringString_3_oe() {
        assertNull(StringUtils.substringBetween("", "", null));
    }

    @Test
    public void testSubstringBetween_StringStringString_4_oe() {
        assertEquals("", StringUtils.substringBetween("", "", ""));
    }

    @Test
    public void testSubstringBetween_StringStringString_5_oe() {
        assertEquals("", StringUtils.substringBetween("foo", "", ""));
    }

    @Test
    public void testSubstringBetween_StringStringString_6_oe() {
        assertNull(StringUtils.substringBetween("foo", "", "]"));
    }

    @Test
    public void testSubstringBetween_StringStringString_7_oe() {
        assertNull(StringUtils.substringBetween("foo", "[", "]"));
    }

    @Test
    public void testSubstringBetween_StringStringString_8_oe() {
        assertEquals("", StringUtils.substringBetween("    ", " ", "  "));
    }

    @Test
    public void testSubstringBetween_StringStringString_9_oe() {
        assertEquals("bar", StringUtils.substringBetween("<foo>bar</foo>", "<foo>", "</foo>") );
    }

    @Test
    public void testSubstringsBetween_StringStringString_1_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");
        assertEquals(3, results.length);
    }

    @Test
    public void testSubstringsBetween_StringStringString_2_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");
        assertEquals("one", results[0]);
    }

    @Test
    public void testSubstringsBetween_StringStringString_3_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");
        assertEquals("two", results[1]);
    }

    @Test
    public void testSubstringsBetween_StringStringString_4_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");
        assertEquals("three", results[2]);
    }

    @Test
    public void testSubstringsBetween_StringStringString_5_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");
        assertEquals(2, results.length);
    }

    @Test
    public void testSubstringsBetween_StringStringString_6_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");
        assertEquals("one", results[0]);
    }

    @Test
    public void testSubstringsBetween_StringStringString_7_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");
        assertEquals("two", results[1]);
    }

    @Test
    public void testSubstringsBetween_StringStringString_8_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");
        assertEquals(2, results.length);
    }

    @Test
    public void testSubstringsBetween_StringStringString_9_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");
        assertEquals("one", results[0]);
    }

    @Test
    public void testSubstringsBetween_StringStringString_10_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");
        assertEquals("two", results[1]);
    }

    @Test
    public void testSubstringsBetween_StringStringString_11_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");
        assertEquals(1, results.length);
    }

    @Test
    public void testSubstringsBetween_StringStringString_12_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");
        assertEquals("one", results[0]);
    }

    @Test
    public void testSubstringsBetween_StringStringString_13_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");
        assertEquals(1, results.length);
    }

    @Test
    public void testSubstringsBetween_StringStringString_14_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");
        assertEquals("three", results[0]);
    }

    @Test
    public void testSubstringsBetween_StringStringString_15_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");

        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");
        assertEquals(1, results.length);
    }

    @Test
    public void testSubstringsBetween_StringStringString_16_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");

        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");
        assertEquals("hello", results[0]);
    }

    @Test
    public void testSubstringsBetween_StringStringString_17_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");

        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");

        results = StringUtils.substringsBetween("one, two, three", "[", "]");
        assertNull(results);
    }

    @Test
    public void testSubstringsBetween_StringStringString_18_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");

        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");

        results = StringUtils.substringsBetween("one, two, three", "[", "]");

        results = StringUtils.substringsBetween("[one, two, three", "[", "]");
        assertNull(results);
    }

    @Test
    public void testSubstringsBetween_StringStringString_19_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");

        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");

        results = StringUtils.substringsBetween("one, two, three", "[", "]");

        results = StringUtils.substringsBetween("[one, two, three", "[", "]");

        results = StringUtils.substringsBetween("one, two, three]", "[", "]");
        assertNull(results);
    }

    @Test
    public void testSubstringsBetween_StringStringString_20_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");

        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");

        results = StringUtils.substringsBetween("one, two, three", "[", "]");

        results = StringUtils.substringsBetween("[one, two, three", "[", "]");

        results = StringUtils.substringsBetween("one, two, three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], [three]", "[", null);
        assertNull(results);
    }

    @Test
    public void testSubstringsBetween_StringStringString_21_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");

        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");

        results = StringUtils.substringsBetween("one, two, three", "[", "]");

        results = StringUtils.substringsBetween("[one, two, three", "[", "]");

        results = StringUtils.substringsBetween("one, two, three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], [three]", "[", null);

        results = StringUtils.substringsBetween("[one], [two], [three]", null, "]");
        assertNull(results);
    }

    @Test
    public void testSubstringsBetween_StringStringString_22_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");

        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");

        results = StringUtils.substringsBetween("one, two, three", "[", "]");

        results = StringUtils.substringsBetween("[one, two, three", "[", "]");

        results = StringUtils.substringsBetween("one, two, three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], [three]", "[", null);

        results = StringUtils.substringsBetween("[one], [two], [three]", null, "]");

        results = StringUtils.substringsBetween("[one], [two], [three]", "", "");
        assertNull(results);
    }

    @Test
    public void testSubstringsBetween_StringStringString_23_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");

        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");

        results = StringUtils.substringsBetween("one, two, three", "[", "]");

        results = StringUtils.substringsBetween("[one, two, three", "[", "]");

        results = StringUtils.substringsBetween("one, two, three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], [three]", "[", null);

        results = StringUtils.substringsBetween("[one], [two], [three]", null, "]");

        results = StringUtils.substringsBetween("[one], [two], [three]", "", "");

        results = StringUtils.substringsBetween(null, "[", "]");
        assertNull(results);
    }

    @Test
    public void testSubstringsBetween_StringStringString_24_oe() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");

        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");

        results = StringUtils.substringsBetween("one, two, three", "[", "]");

        results = StringUtils.substringsBetween("[one, two, three", "[", "]");

        results = StringUtils.substringsBetween("one, two, three]", "[", "]");

        results = StringUtils.substringsBetween("[one], [two], [three]", "[", null);

        results = StringUtils.substringsBetween("[one], [two], [three]", null, "]");

        results = StringUtils.substringsBetween("[one], [two], [three]", "", "");

        results = StringUtils.substringsBetween(null, "[", "]");

        results = StringUtils.substringsBetween("", "[", "]");
        assertEquals(0, results.length);
    }

    @Test
    public void testCountMatches_String_1_oe() {
        assertEquals(0, StringUtils.countMatches(null, null));
    }

    @Test
    public void testCountMatches_String_2_oe() {
        assertEquals(0, StringUtils.countMatches("blah", null));
    }

    @Test
    public void testCountMatches_String_3_oe() {
        assertEquals(0, StringUtils.countMatches(null, "DD"));
    }

    @Test
    public void testCountMatches_String_4_oe() {

        assertEquals(0, StringUtils.countMatches("x", ""));
    }

    @Test
    public void testCountMatches_String_5_oe() {

        assertEquals(0, StringUtils.countMatches("", ""));
    }

    @Test
    public void testCountMatches_String_6_oe() {


        assertEquals(3,StringUtils.countMatches("one long someone sentence of one","one"));
    }

    @Test
    public void testCountMatches_String_7_oe() {


        assertEquals(0,StringUtils.countMatches("one long someone sentence of one","two"));
    }

    @Test
    public void testCountMatches_String_8_oe() {


        assertEquals(4,StringUtils.countMatches("oooooooooooo","ooo"));
    }

    @Test
    public void testCountMatches_String_9_oe() {


        assertEquals(0, StringUtils.countMatches(null, "?"));
    }

    @Test
    public void testCountMatches_String_10_oe() {


        assertEquals(0, StringUtils.countMatches("", "?"));
    }

    @Test
    public void testCountMatches_String_11_oe() {


        assertEquals(0, StringUtils.countMatches("abba", null));
    }

    @Test
    public void testCountMatches_String_12_oe() {


        assertEquals(0, StringUtils.countMatches("abba", ""));
    }

    @Test
    public void testCountMatches_String_13_oe() {


        assertEquals(2, StringUtils.countMatches("abba", "a"));
    }

    @Test
    public void testCountMatches_String_14_oe() {


        assertEquals(1, StringUtils.countMatches("abba", "ab"));
    }

    @Test
    public void testCountMatches_String_15_oe() {


        assertEquals(0, StringUtils.countMatches("abba", "xxx"));
    }

    @Test
    public void testCountMatches_String_16_oe() {


        assertEquals(1, StringUtils.countMatches("ababa", "aba"));
    }

    @Test
    public void testCountMatches_char_1_oe() {
        assertEquals(0, StringUtils.countMatches(null, 'D'));
    }

    @Test
    public void testCountMatches_char_2_oe() {
        assertEquals(5, StringUtils.countMatches("one long someone sentence of one", ' '));
    }

    @Test
    public void testCountMatches_char_3_oe() {
        assertEquals(6, StringUtils.countMatches("one long someone sentence of one", 'o'));
    }

    @Test
    public void testCountMatches_char_4_oe() {
        assertEquals(4, StringUtils.countMatches("oooooooooooo", "ooo"));
    }

}
