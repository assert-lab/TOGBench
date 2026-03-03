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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for methods of {@link org.apache.commons.lang3.StringUtils}
 * which been moved to their own test classes.
 */
@SuppressWarnings("deprecation") // deliberate use of deprecated code
public class StringUtilsTest_OE25Dev {

    static final String WHITESPACE;
    static final String NON_WHITESPACE;
    static final String HARD_SPACE;
    static final String TRIMMABLE;
    static final String NON_TRIMMABLE;

    static {
        final StringBuilder ws = new StringBuilder();
        final StringBuilder nws = new StringBuilder();
        final String hs = String.valueOf(((char) 160));
        final StringBuilder tr = new StringBuilder();
        final StringBuilder ntr = new StringBuilder();
        for (int i = 0; i < Character.MAX_VALUE; i++) {
            if (Character.isWhitespace((char) i)) {
                ws.append(String.valueOf((char) i));
                if (i > 32) {
                    ntr.append(String.valueOf((char) i));
                }
            } else if (i < 40) {
                nws.append(String.valueOf((char) i));
            }
        }
        for (int i = 0; i <= 32; i++) {
            tr.append(String.valueOf((char) i));
        }
        WHITESPACE = ws.toString();
        NON_WHITESPACE = nws.toString();
        HARD_SPACE = hs;
        TRIMMABLE = tr.toString();
        NON_TRIMMABLE = ntr.toString();
    }

    private static final String[] ARRAY_LIST = {"foo", "bar", "baz"};
    private static final String[] EMPTY_ARRAY_LIST = {};
    private static final String[] NULL_ARRAY_LIST = {null};
    private static final Object[] NULL_TO_STRING_LIST = {
            new Object() {
                @Override
                public String toString() {
                    return null;
                }
            }
    };
    private static final String[] MIXED_ARRAY_LIST = {null, "", "foo"};
    private static final Object[] MIXED_TYPE_LIST = {"foo", Long.valueOf(2L)};
    private static final long[] LONG_PRIM_LIST = {1, 2};
    private static final int[] INT_PRIM_LIST = {1, 2};
    private static final byte[] BYTE_PRIM_LIST = {1, 2};
    private static final short[] SHORT_PRIM_LIST = {1, 2};
    private static final char[] CHAR_PRIM_LIST = {'1', '2'};
    private static final float[] FLOAT_PRIM_LIST = {1, 2};
    private static final double[] DOUBLE_PRIM_LIST = {1, 2};
    private static final List<String> MIXED_STRING_LIST = Arrays.asList(null, "", "foo");
    private static final List<Object> MIXED_TYPE_OBJECT_LIST = Arrays.<Object>asList("foo", Long.valueOf(2L));
    private static final List<String> STRING_LIST = Arrays.asList("foo", "bar", "baz");
    private static final List<String> EMPTY_STRING_LIST = Collections.emptyList();
    private static final List<String> NULL_STRING_LIST = Collections.singletonList(null);

    private static final String SEPARATOR = ",";
    private static final char SEPARATOR_CHAR = ';';
    private static final char COMMA_SEPARATOR_CHAR = ',';

    private static final String TEXT_LIST = "foo,bar,baz";
    private static final String TEXT_LIST_CHAR = "foo;bar;baz";
    private static final String TEXT_LIST_NOSEP = "foobarbaz";

    private static final String FOO_UNCAP = "foo";
    private static final String FOO_CAP = "Foo";

    private static final String SENTENCE_UNCAP = "foo bar baz";
    private static final String SENTENCE_CAP = "Foo Bar Baz";

    private static final boolean[] EMPTY = {};
    private static final boolean[] ARRAY_FALSE_FALSE = {false, false};
    private static final boolean[] ARRAY_FALSE_TRUE = {false, true};
    private static final boolean[] ARRAY_FALSE_TRUE_FALSE = {false, true, false};

    private void assertAbbreviateWithAbbrevMarkerAndOffset(final String expected, final String abbrevMarker, final int offset, final int maxWidth) {
        final String abcdefghijklmno = "abcdefghijklmno";
        final String message = "abbreviate(String,String,int,int) failed";
        final String actual = StringUtils.abbreviate(abcdefghijklmno, abbrevMarker, offset, maxWidth);
        if (offset >= 0 && offset < abcdefghijklmno.length()) {
            assertTrue(actual.indexOf((char)('a' + offset))!= -1,message + " -- should contain offset character");
        }
        assertTrue(actual.length()<= maxWidth,message + " -- should not be greater than maxWidth");
        assertEquals(expected, actual, message);
    }

    private void assertAbbreviateWithOffset(final String expected, final int offset, final int maxWidth) {
        final String abcdefghijklmno = "abcdefghijklmno";
        final String message = "abbreviate(String,int,int) failed";
        final String actual = StringUtils.abbreviate(abcdefghijklmno, offset, maxWidth);
        if (offset >= 0 && offset < abcdefghijklmno.length()) {
            assertTrue(actual.indexOf((char)('a' + offset))!= -1,message + " -- should contain offset character");
        }
        assertTrue(actual.length()<= maxWidth,message + " -- should not be greater than maxWidth");
        assertEquals(expected, actual, message);
    }

    private void innerTestSplit(final char separator, final String sepStr, final char noMatch) {
        final String msg = "Failed on separator hex(" + Integer.toHexString(separator) +
                "), noMatch hex(" + Integer.toHexString(noMatch) + "), sepStr(" + sepStr + ")";

        final String str = "a" + separator + "b" + separator + separator + noMatch + "c";
        String[] res;
        // (str, sepStr)
        res = StringUtils.split(str, sepStr);
        assertEquals(3, res.length, msg);
        assertEquals("a", res[0]);
        assertEquals("b", res[1]);
        assertEquals(noMatch + "c", res[2]);

        final String str2 = separator + "a" + separator;
        res = StringUtils.split(str2, sepStr);
        assertEquals(1, res.length, msg);
        assertEquals("a", res[0], msg);

        res = StringUtils.split(str, sepStr, -1);
        assertEquals(3, res.length, msg);
        assertEquals("a", res[0], msg);
        assertEquals("b", res[1], msg);
        assertEquals(noMatch + "c", res[2], msg);

        res = StringUtils.split(str, sepStr, 0);
        assertEquals(3, res.length, msg);
        assertEquals("a", res[0], msg);
        assertEquals("b", res[1], msg);
        assertEquals(noMatch + "c", res[2], msg);

        res = StringUtils.split(str, sepStr, 1);
        assertEquals(1, res.length, msg);
        assertEquals(str, res[0], msg);

        res = StringUtils.split(str, sepStr, 2);
        assertEquals(2, res.length, msg);
        assertEquals("a", res[0], msg);
        assertEquals(str.substring(2), res[1], msg);
    }

    private void innerTestSplitPreserveAllTokens(final char separator, final String sepStr, final char noMatch) {
        final String msg = "Failed on separator hex(" + Integer.toHexString(separator) +
                "), noMatch hex(" + Integer.toHexString(noMatch) + "), sepStr(" + sepStr + ")";

        final String str = "a" + separator + "b" + separator + separator + noMatch + "c";
        String[] res;
        // (str, sepStr)
        res = StringUtils.splitPreserveAllTokens(str, sepStr);
        assertEquals(4, res.length, msg);
        assertEquals("a", res[0], msg);
        assertEquals("b", res[1], msg);
        assertEquals("", res[2], msg);
        assertEquals(noMatch + "c", res[3], msg);

        final String str2 = separator + "a" + separator;
        res = StringUtils.splitPreserveAllTokens(str2, sepStr);
        assertEquals(3, res.length, msg);
        assertEquals("", res[0], msg);
        assertEquals("a", res[1], msg);
        assertEquals("", res[2], msg);

        res = StringUtils.splitPreserveAllTokens(str, sepStr, -1);
        assertEquals(4, res.length, msg);
        assertEquals("a", res[0], msg);
        assertEquals("b", res[1], msg);
        assertEquals("", res[2], msg);
        assertEquals(noMatch + "c", res[3], msg);

        res = StringUtils.splitPreserveAllTokens(str, sepStr, 0);
        assertEquals(4, res.length, msg);
        assertEquals("a", res[0], msg);
        assertEquals("b", res[1], msg);
        assertEquals("", res[2], msg);
        assertEquals(noMatch + "c", res[3], msg);

        res = StringUtils.splitPreserveAllTokens(str, sepStr, 1);
        assertEquals(1, res.length, msg);
        assertEquals(str, res[0], msg);

        res = StringUtils.splitPreserveAllTokens(str, sepStr, 2);
        assertEquals(2, res.length, msg);
        assertEquals("a", res[0], msg);
        assertEquals(str.substring(2), res[1], msg);
    }

    //-----------------------------------------------------------------------
    //Fixed LANG-1463

    /**
     * Tests {@code appendIfMissing}.
     */

    /**
     * Tests {@code appendIfMissingIgnoreCase}.
     */

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    /**
     * A sanity check for {@link StringUtils#EMPTY}.
     */

    /**
     * Tests LANG-858.
     */

    //-----------------------------------------------------------------------

    /**
     * Test for {@link StringUtils#isAllLowerCase(CharSequence)}.
     */

    /**
     * Test for {@link StringUtils#isAllUpperCase(CharSequence)}.
     */

    /**
     * Test for {@link StringUtils#isMixedCase(CharSequence)}.
     */

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    /**
     * Tests {@code prependIfMissing}.
     */

    /**
     * Tests {@code prependIfMissingIgnoreCase}.
     */

    /**
     * Test method for 'StringUtils.replaceEach(String, String[], String[])'
     */

    /**
     * Test method for 'StringUtils.replaceEachRepeatedly(String, String[], String[])'
     */

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    // Methods on StringUtils that are immutable in spirit (i.e. calculate the length)
    // should take a CharSequence parameter. Methods that are mutable in spirit (i.e. capitalize)
    // should take a String or String[] parameter and return String or String[].
    // This test enforces that this is done.

    /**
     * Tests {@link StringUtils#toEncodedString(byte[], Charset)}
     *
     * @see StringUtils#toEncodedString(byte[], Charset)
     */

    /**
     * Tests {@link StringUtils#toString(byte[], String)}
     *
     * @throws java.io.UnsupportedEncodingException because the method under test max throw it
     * @see StringUtils#toString(byte[], String)
     */

    //-----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    @Test
    public void testAbbreviateMarkerWithEmptyString_1_oe() {
        final String greaterThanMaxTest = "much too long text";
        assertEquals("much too long", StringUtils.abbreviate(greaterThanMaxTest, "", 13));
    }

    @Test
    public void testAbbreviate_StringInt_1_oe() {
        assertNull(StringUtils.abbreviate(null, 10));
    }

    @Test
    public void testAbbreviate_StringInt_2_oe() {
        assertEquals("", StringUtils.abbreviate("", 10));
    }

    @Test
    public void testAbbreviate_StringInt_3_oe() {
        assertEquals("short", StringUtils.abbreviate("short", 10));
    }

    @Test
    public void testAbbreviate_StringInt_4_oe() {
        assertEquals("Now is ...", StringUtils.abbreviate("Now is the time for all good men to come to the aid of their party.", 10));
    }

    @Test
    public void testAbbreviate_StringInt_5_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("raspberry p...", StringUtils.abbreviate(raspberry, 14));
    }

    @Test
    public void testAbbreviate_StringInt_6_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("raspberry peach", StringUtils.abbreviate("raspberry peach", 15));
    }

    @Test
    public void testAbbreviate_StringInt_7_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("raspberry peach", StringUtils.abbreviate("raspberry peach", 16));
    }

    @Test
    public void testAbbreviate_StringInt_8_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("abc...", StringUtils.abbreviate("abcdefg", 6));
    }

    @Test
    public void testAbbreviate_StringInt_9_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("abcdefg", StringUtils.abbreviate("abcdefg", 7));
    }

    @Test
    public void testAbbreviate_StringInt_10_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("abcdefg", StringUtils.abbreviate("abcdefg", 8));
    }

    @Test
    public void testAbbreviate_StringInt_11_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("a...", StringUtils.abbreviate("abcdefg", 4));
    }

    @Test
    public void testAbbreviate_StringInt_12_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("", StringUtils.abbreviate("", 4));
    }

    @Test
    public void testAbbreviate_StringInt_13_oe() throws Exception {

        final String raspberry = "raspberry peach";

        try {
    StringUtils.abbreviate("abc", 3);
    fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAbbreviate_StringIntInt_1_oe() {
        assertNull(StringUtils.abbreviate(null, 10, 12));
    }

    @Test
    public void testAbbreviate_StringIntInt_2_oe() {
        assertEquals("", StringUtils.abbreviate("", 0, 10));
    }

    @Test
    public void testAbbreviate_StringIntInt_3_oe() {
        assertEquals("", StringUtils.abbreviate("", 2, 10));
    }

    @Test
    public void testAbbreviate_StringIntInt_4_oe() throws Exception {

        try {
    StringUtils.abbreviate("abcdefghij", 0, 3);
    fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAbbreviate_StringIntInt_5_oe() throws Exception {

        try {
    StringUtils.abbreviate("abcdefghij", 5, 6);
    fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAbbreviate_StringIntInt_6_oe() {


        final String raspberry = "raspberry peach";
        assertEquals("raspberry peach", StringUtils.abbreviate(raspberry, 11, 15));
    }

    @Test
    public void testAbbreviate_StringIntInt_7_oe() {


        final String raspberry = "raspberry peach";

        assertNull(StringUtils.abbreviate(null, 7, 14));
    }

    @Test
    public void testAbbreviate_StringStringInt_1_oe() {
        assertNull(StringUtils.abbreviate(null, null, 10));
    }

    @Test
    public void testAbbreviate_StringStringInt_2_oe() {
        assertNull(StringUtils.abbreviate(null, "...", 10));
    }

    @Test
    public void testAbbreviate_StringStringInt_3_oe() {
        assertEquals("paranaguacu", StringUtils.abbreviate("paranaguacu", null, 10));
    }

    @Test
    public void testAbbreviate_StringStringInt_4_oe() {
        assertEquals("", StringUtils.abbreviate("", "...", 2));
    }

    @Test
    public void testAbbreviate_StringStringInt_5_oe() {
        assertEquals("wai**", StringUtils.abbreviate("waiheke", "**", 5));
    }

    @Test
    public void testAbbreviate_StringStringInt_6_oe() {
        assertEquals("And af,,,,", StringUtils.abbreviate("And after a long time, he finally met his son.", ",,,,", 10));
    }

    @Test
    public void testAbbreviate_StringStringInt_7_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("raspberry pe..", StringUtils.abbreviate(raspberry, "..", 14));
    }

    @Test
    public void testAbbreviate_StringStringInt_8_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("raspberry peach", StringUtils.abbreviate("raspberry peach", "---*---", 15));
    }

    @Test
    public void testAbbreviate_StringStringInt_9_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("raspberry peach", StringUtils.abbreviate("raspberry peach", ".", 16));
    }

    @Test
    public void testAbbreviate_StringStringInt_10_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("abc()(", StringUtils.abbreviate("abcdefg", "()(", 6));
    }

    @Test
    public void testAbbreviate_StringStringInt_11_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("abcdefg", StringUtils.abbreviate("abcdefg", ";", 7));
    }

    @Test
    public void testAbbreviate_StringStringInt_12_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("abcdefg", StringUtils.abbreviate("abcdefg", "_-", 8));
    }

    @Test
    public void testAbbreviate_StringStringInt_13_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("abc.", StringUtils.abbreviate("abcdefg", ".", 4));
    }

    @Test
    public void testAbbreviate_StringStringInt_14_oe() {

        final String raspberry = "raspberry peach";
        assertEquals("", StringUtils.abbreviate("", 4));
    }

    @Test
    public void testAbbreviate_StringStringInt_15_oe() throws Exception {

        final String raspberry = "raspberry peach";

        try {
    StringUtils.abbreviate("abcdefghij", "...", 3);
    fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAbbreviate_StringStringIntInt_1_oe() {
        assertNull(StringUtils.abbreviate(null, null, 10, 12));
    }

    @Test
    public void testAbbreviate_StringStringIntInt_2_oe() {
        assertNull(StringUtils.abbreviate(null, "...", 10, 12));
    }

    @Test
    public void testAbbreviate_StringStringIntInt_3_oe() {
        assertEquals("", StringUtils.abbreviate("", null, 0, 10));
    }

    @Test
    public void testAbbreviate_StringStringIntInt_4_oe() {
        assertEquals("", StringUtils.abbreviate("", "...", 2, 10));
    }

    @Test
    public void testAbbreviate_StringStringIntInt_6_oe() throws Exception {

        try {
    StringUtils.abbreviate("abcdefghij", "!!!", 5, 6);
    fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAbbreviate_StringStringIntInt_7_oe() {


        final String raspberry = "raspberry peach";
        assertEquals("raspberry peach", StringUtils.abbreviate(raspberry, "--", 12, 15));
    }

    @Test
    public void testAbbreviate_StringStringIntInt_8_oe() {


        final String raspberry = "raspberry peach";

        assertNull(StringUtils.abbreviate(null, ";", 7, 14));
    }

    @Test
    public void testAbbreviateMiddle_1_oe() {
        assertNull(StringUtils.abbreviateMiddle(null, null, 0));
    }

    @Test
    public void testAbbreviateMiddle_2_oe() {
        assertEquals("abc", StringUtils.abbreviateMiddle("abc", null, 0));
    }

    @Test
    public void testAbbreviateMiddle_3_oe() {
        assertEquals("abc", StringUtils.abbreviateMiddle("abc", ".", 0));
    }

    @Test
    public void testAbbreviateMiddle_4_oe() {
        assertEquals("abc", StringUtils.abbreviateMiddle("abc", ".", 3));
    }

    @Test
    public void testAbbreviateMiddle_5_oe() {
        assertEquals("ab.f", StringUtils.abbreviateMiddle("abcdef", ".", 4));
    }

    @Test
    public void testAbbreviateMiddle_6_oe() {

        assertEquals("A very long text with un...f the text is complete.",StringUtils.abbreviateMiddle("A very long text with unimportant stuff in the middle but interesting start and " + "end to see if the text is complete.","...",50));
    }

    @Test
    public void testAbbreviateMiddle_7_oe() {


        final String longText = "Start text" + StringUtils.repeat("x", 10000) + "Close text";
        assertEquals("Start text->Close text",StringUtils.abbreviateMiddle(longText,"->",22));
    }

    @Test
    public void testAbbreviateMiddle_8_oe() {


        final String longText = "Start text" + StringUtils.repeat("x", 10000) + "Close text";

        assertEquals("abc", StringUtils.abbreviateMiddle("abc", ".", -1));
    }

    @Test
    public void testAbbreviateMiddle_9_oe() {


        final String longText = "Start text" + StringUtils.repeat("x", 10000) + "Close text";


        assertEquals("abc", StringUtils.abbreviateMiddle("abc", ".", 1));
    }

    @Test
    public void testAbbreviateMiddle_10_oe() {


        final String longText = "Start text" + StringUtils.repeat("x", 10000) + "Close text";


        assertEquals("abc", StringUtils.abbreviateMiddle("abc", ".", 2));
    }

    @Test
    public void testAbbreviateMiddle_11_oe() {


        final String longText = "Start text" + StringUtils.repeat("x", 10000) + "Close text";



        assertEquals("a", StringUtils.abbreviateMiddle("a", ".", 1));
    }

    @Test
    public void testAbbreviateMiddle_12_oe() {


        final String longText = "Start text" + StringUtils.repeat("x", 10000) + "Close text";




        assertEquals("a.d", StringUtils.abbreviateMiddle("abcd", ".", 3));
    }

    @Test
    public void testAbbreviateMiddle_13_oe() {


        final String longText = "Start text" + StringUtils.repeat("x", 10000) + "Close text";





        assertEquals("a..f", StringUtils.abbreviateMiddle("abcdef", "..", 4));
    }

    @Test
    public void testAbbreviateMiddle_14_oe() {


        final String longText = "Start text" + StringUtils.repeat("x", 10000) + "Close text";





        assertEquals("ab.ef", StringUtils.abbreviateMiddle("abcdef", ".", 5));
    }

    @Test
    public void testAppendIfMissing_1_oe() {
        assertNull(StringUtils.appendIfMissing(null, null), "appendIfMissing(null,null)");
    }

    @Test
    public void testAppendIfMissing_2_oe() {
        assertEquals("abc", StringUtils.appendIfMissing("abc", null), "appendIfMissing(abc,null)");
    }

    @Test
    public void testAppendIfMissing_3_oe() {
        assertEquals("xyz", StringUtils.appendIfMissing("", "xyz"), "appendIfMissing(\"\",xyz)");
    }

    @Test
    public void testAppendIfMissing_4_oe() {
        assertEquals("abcxyz", StringUtils.appendIfMissing("abc", "xyz"), "appendIfMissing(abc,xyz)");
    }

    @Test
    public void testAppendIfMissing_5_oe() {
        assertEquals("abcxyz", StringUtils.appendIfMissing("abcxyz", "xyz"), "appendIfMissing(abcxyz,xyz)");
    }

    @Test
    public void testAppendIfMissing_6_oe() {
        assertEquals("aXYZxyz", StringUtils.appendIfMissing("aXYZ", "xyz"), "appendIfMissing(aXYZ,xyz)");
    }

    @Test
    public void testAppendIfMissing_7_oe() {

        assertNull(StringUtils.appendIfMissing(null, null, (CharSequence[]) null), "appendIfMissing(null,null,null)");
    }

    @Test
    public void testAppendIfMissing_8_oe() {

        assertEquals("abc", StringUtils.appendIfMissing("abc", null, (CharSequence[]) null), "appendIfMissing(abc,null,null)");
    }

    @Test
    public void testAppendIfMissing_9_oe() {

        assertEquals("xyz", StringUtils.appendIfMissing("", "xyz", (CharSequence[]) null), "appendIfMissing(\"\",xyz,null))");
    }

    @Test
    public void testAppendIfMissing_10_oe() {

        assertEquals("abcxyz", StringUtils.appendIfMissing("abc", "xyz", null), "appendIfMissing(abc,xyz,{null})");
    }

    @Test
    public void testAppendIfMissing_11_oe() {

        assertEquals("abc", StringUtils.appendIfMissing("abc", "xyz", ""), "appendIfMissing(abc,xyz,\"\")");
    }

    @Test
    public void testAppendIfMissing_12_oe() {

        assertEquals("abcxyz", StringUtils.appendIfMissing("abc", "xyz", "mno"), "appendIfMissing(abc,xyz,mno)");
    }

    @Test
    public void testAppendIfMissing_13_oe() {

        assertEquals("abcxyz", StringUtils.appendIfMissing("abcxyz", "xyz", "mno"), "appendIfMissing(abcxyz,xyz,mno)");
    }

    @Test
    public void testAppendIfMissing_14_oe() {

        assertEquals("abcmno", StringUtils.appendIfMissing("abcmno", "xyz", "mno"), "appendIfMissing(abcmno,xyz,mno)");
    }

    @Test
    public void testAppendIfMissing_15_oe() {

        assertEquals("abcXYZxyz", StringUtils.appendIfMissing("abcXYZ", "xyz", "mno"), "appendIfMissing(abcXYZ,xyz,mno)");
    }

    @Test
    public void testAppendIfMissing_16_oe() {

        assertEquals("abcMNOxyz", StringUtils.appendIfMissing("abcMNO", "xyz", "mno"), "appendIfMissing(abcMNO,xyz,mno)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_1_oe() {
        assertNull(StringUtils.appendIfMissingIgnoreCase(null, null), "appendIfMissingIgnoreCase(null,null)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_2_oe() {
        assertEquals("abc", StringUtils.appendIfMissingIgnoreCase("abc", null), "appendIfMissingIgnoreCase(abc,null)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_3_oe() {
        assertEquals("xyz", StringUtils.appendIfMissingIgnoreCase("", "xyz"), "appendIfMissingIgnoreCase(\"\",xyz)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_4_oe() {
        assertEquals("abcxyz", StringUtils.appendIfMissingIgnoreCase("abc", "xyz"), "appendIfMissingIgnoreCase(abc,xyz)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_5_oe() {
        assertEquals("abcxyz", StringUtils.appendIfMissingIgnoreCase("abcxyz", "xyz"), "appendIfMissingIgnoreCase(abcxyz,xyz)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_6_oe() {
        assertEquals("abcXYZ", StringUtils.appendIfMissingIgnoreCase("abcXYZ", "xyz"), "appendIfMissingIgnoreCase(abcXYZ,xyz)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_7_oe() {

        assertNull(StringUtils.appendIfMissingIgnoreCase(null, null, (CharSequence[]) null), "appendIfMissingIgnoreCase(null,null,null)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_8_oe() {

        assertEquals("abc", StringUtils.appendIfMissingIgnoreCase("abc", null, (CharSequence[]) null), "appendIfMissingIgnoreCase(abc,null,null)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_9_oe() {

        assertEquals("xyz", StringUtils.appendIfMissingIgnoreCase("", "xyz", (CharSequence[]) null), "appendIfMissingIgnoreCase(\"\",xyz,null)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_10_oe() {

        assertEquals("abcxyz", StringUtils.appendIfMissingIgnoreCase("abc", "xyz", null), "appendIfMissingIgnoreCase(abc,xyz,{null})");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_11_oe() {

        assertEquals("abc", StringUtils.appendIfMissingIgnoreCase("abc", "xyz", ""), "appendIfMissingIgnoreCase(abc,xyz,\"\")");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_12_oe() {

        assertEquals("abcxyz", StringUtils.appendIfMissingIgnoreCase("abc", "xyz", "mno"), "appendIfMissingIgnoreCase(abc,xyz,mno)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_13_oe() {

        assertEquals("abcxyz", StringUtils.appendIfMissingIgnoreCase("abcxyz", "xyz", "mno"), "appendIfMissingIgnoreCase(abcxyz,xyz,mno)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_14_oe() {

        assertEquals("abcmno", StringUtils.appendIfMissingIgnoreCase("abcmno", "xyz", "mno"), "appendIfMissingIgnoreCase(abcmno,xyz,mno)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_15_oe() {

        assertEquals("abcXYZ", StringUtils.appendIfMissingIgnoreCase("abcXYZ", "xyz", "mno"), "appendIfMissingIgnoreCase(abcXYZ,xyz,mno)");
    }

    @Test
    public void testAppendIfMissingIgnoreCase_16_oe() {

        assertEquals("abcMNO", StringUtils.appendIfMissingIgnoreCase("abcMNO", "xyz", "mno"), "appendIfMissingIgnoreCase(abcMNO,xyz,mno)");
    }

    @Test
    public void testCapitalize_1_oe() {
        assertNull(StringUtils.capitalize(null));
    }

    @Test
    public void testCapitalize_2_oe() {

        assertEquals("", StringUtils.capitalize(""), "capitalize(empty-string) failed");
    }

    @Test
    public void testCapitalize_3_oe() {

        assertEquals("X", StringUtils.capitalize("x"), "capitalize(single-char-string) failed");
    }

    @Test
    public void testCapitalize_4_oe() {

        assertEquals(FOO_CAP, StringUtils.capitalize(FOO_CAP), "capitalize(String) failed");
    }

    @Test
    public void testCapitalize_5_oe() {

        assertEquals(FOO_CAP, StringUtils.capitalize(FOO_UNCAP), "capitalize(string) failed");
    }

    @Test
    public void testCapitalize_6_oe() {


        assertEquals("\u01C8", StringUtils.capitalize("\u01C9"), "capitalize(String) is not using TitleCase");
    }

    @Test
    public void testCapitalize_7_oe() {



        assertNull(StringUtils.capitalize(null));
    }

    @Test
    public void testCapitalize_8_oe() {



        assertEquals("", StringUtils.capitalize(""));
    }

    @Test
    public void testCapitalize_9_oe() {



        assertEquals("Cat", StringUtils.capitalize("cat"));
    }

    @Test
    public void testCapitalize_10_oe() {



        assertEquals("CAt", StringUtils.capitalize("cAt"));
    }

    @Test
    public void testCapitalize_11_oe() {



        assertEquals("'cat'", StringUtils.capitalize("'cat'"));
    }

    @Test
    public void testCenter_StringInt_1_oe() {
        assertNull(StringUtils.center(null, -1));
    }

    @Test
    public void testCenter_StringInt_2_oe() {
        assertNull(StringUtils.center(null, 4));
    }

    @Test
    public void testCenter_StringInt_3_oe() {
        assertEquals("    ", StringUtils.center("", 4));
    }

    @Test
    public void testCenter_StringInt_4_oe() {
        assertEquals("ab", StringUtils.center("ab", 0));
    }

    @Test
    public void testCenter_StringInt_5_oe() {
        assertEquals("ab", StringUtils.center("ab", -1));
    }

    @Test
    public void testCenter_StringInt_6_oe() {
        assertEquals("ab", StringUtils.center("ab", 1));
    }

    @Test
    public void testCenter_StringInt_7_oe() {
        assertEquals("    ", StringUtils.center("", 4));
    }

    @Test
    public void testCenter_StringInt_8_oe() {
        assertEquals(" ab ", StringUtils.center("ab", 4));
    }

    @Test
    public void testCenter_StringInt_9_oe() {
        assertEquals("abcd", StringUtils.center("abcd", 2));
    }

    @Test
    public void testCenter_StringInt_10_oe() {
        assertEquals(" a  ", StringUtils.center("a", 4));
    }

    @Test
    public void testCenter_StringInt_11_oe() {
        assertEquals("  a  ", StringUtils.center("a", 5));
    }

    @Test
    public void testCenter_StringIntChar_1_oe() {
        assertNull(StringUtils.center(null, -1, ' '));
    }

    @Test
    public void testCenter_StringIntChar_2_oe() {
        assertNull(StringUtils.center(null, 4, ' '));
    }

    @Test
    public void testCenter_StringIntChar_3_oe() {
        assertEquals("    ", StringUtils.center("", 4, ' '));
    }

    @Test
    public void testCenter_StringIntChar_4_oe() {
        assertEquals("ab", StringUtils.center("ab", 0, ' '));
    }

    @Test
    public void testCenter_StringIntChar_5_oe() {
        assertEquals("ab", StringUtils.center("ab", -1, ' '));
    }

    @Test
    public void testCenter_StringIntChar_6_oe() {
        assertEquals("ab", StringUtils.center("ab", 1, ' '));
    }

    @Test
    public void testCenter_StringIntChar_7_oe() {
        assertEquals("    ", StringUtils.center("", 4, ' '));
    }

    @Test
    public void testCenter_StringIntChar_8_oe() {
        assertEquals(" ab ", StringUtils.center("ab", 4, ' '));
    }

    @Test
    public void testCenter_StringIntChar_9_oe() {
        assertEquals("abcd", StringUtils.center("abcd", 2, ' '));
    }

    @Test
    public void testCenter_StringIntChar_10_oe() {
        assertEquals(" a  ", StringUtils.center("a", 4, ' '));
    }

    @Test
    public void testCenter_StringIntChar_11_oe() {
        assertEquals("  a  ", StringUtils.center("a", 5, ' '));
    }

    @Test
    public void testCenter_StringIntChar_12_oe() {
        assertEquals("xxaxx", StringUtils.center("a", 5, 'x'));
    }

    @Test
    public void testCenter_StringIntString_1_oe() {
        assertNull(StringUtils.center(null, 4, null));
    }

    @Test
    public void testCenter_StringIntString_2_oe() {
        assertNull(StringUtils.center(null, -1, " "));
    }

    @Test
    public void testCenter_StringIntString_3_oe() {
        assertNull(StringUtils.center(null, 4, " "));
    }

    @Test
    public void testCenter_StringIntString_4_oe() {
        assertEquals("    ", StringUtils.center("", 4, " "));
    }

    @Test
    public void testCenter_StringIntString_5_oe() {
        assertEquals("ab", StringUtils.center("ab", 0, " "));
    }

    @Test
    public void testCenter_StringIntString_6_oe() {
        assertEquals("ab", StringUtils.center("ab", -1, " "));
    }

    @Test
    public void testCenter_StringIntString_7_oe() {
        assertEquals("ab", StringUtils.center("ab", 1, " "));
    }

    @Test
    public void testCenter_StringIntString_8_oe() {
        assertEquals("    ", StringUtils.center("", 4, " "));
    }

    @Test
    public void testCenter_StringIntString_9_oe() {
        assertEquals(" ab ", StringUtils.center("ab", 4, " "));
    }

    @Test
    public void testCenter_StringIntString_10_oe() {
        assertEquals("abcd", StringUtils.center("abcd", 2, " "));
    }

    @Test
    public void testCenter_StringIntString_11_oe() {
        assertEquals(" a  ", StringUtils.center("a", 4, " "));
    }

    @Test
    public void testCenter_StringIntString_12_oe() {
        assertEquals("yayz", StringUtils.center("a", 4, "yz"));
    }

    @Test
    public void testCenter_StringIntString_13_oe() {
        assertEquals("yzyayzy", StringUtils.center("a", 7, "yz"));
    }

    @Test
    public void testCenter_StringIntString_14_oe() {
        assertEquals("  abc  ", StringUtils.center("abc", 7, null));
    }

    @Test
    public void testCenter_StringIntString_15_oe() {
        assertEquals("  abc  ", StringUtils.center("abc", 7, ""));
    }

    @Test
    public void testChomp_1_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
            assertEquals(expectedResult, StringUtils.chomp(original), "chomp(String) failed");
    }
    }

    @Test
    public void testChomp_2_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals("foo", StringUtils.chomp("foobar", "bar"), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_3_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals("foobar", StringUtils.chomp("foobar", "baz"), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_4_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals("foo", StringUtils.chomp("foo", "foooo"), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_5_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals("foobar", StringUtils.chomp("foobar", ""), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_6_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals("foobar", StringUtils.chomp("foobar", null), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_7_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals("", StringUtils.chomp("", "foo"), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_8_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals("", StringUtils.chomp("", null), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_9_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals("", StringUtils.chomp("", ""), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_10_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertNull(StringUtils.chomp(null, "foo"), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_11_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertNull(StringUtils.chomp(null, null), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_12_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertNull(StringUtils.chomp(null, ""), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_13_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals("", StringUtils.chomp("foo", "foo"), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_14_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals(" ", StringUtils.chomp(" foo", "foo"), "chomp(String, String) failed");
    }

    @Test
    public void testChomp_15_oe() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
        }

        assertEquals("foo ", StringUtils.chomp("foo ", "foo"), "chomp(String, String) failed");
    }

    @Test
    public void testChop_1_oe() {

        final String[][] chopCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {"foo", "fo"},
                {"foo\nfoo", "foo\nfo"},
                {"\n", ""},
                {"\r", ""},
                {"\r\n", ""},
                {null, null},
                {"", ""},
                {"a", ""},
        };
        for (final String[] chopCase : chopCases) {
            final String original = chopCase[0];
            final String expectedResult = chopCase[1];
            assertEquals(expectedResult, StringUtils.chop(original), "chop(String) failed");
    }
    }

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new StringUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        final Constructor<?>[] cons = StringUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        final Constructor<?>[] cons = StringUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        final Constructor<?>[] cons = StringUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(StringUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        final Constructor<?>[] cons = StringUtils.class.getDeclaredConstructors();
        assertFalse(Modifier.isFinal(StringUtils.class.getModifiers()));
    }

    @Test
    public void testDefault_String_1_oe() {
        assertEquals("", StringUtils.defaultString(null));
    }

    @Test
    public void testDefault_String_2_oe() {
        assertEquals("", StringUtils.defaultString(""));
    }

    @Test
    public void testDefault_String_3_oe() {
        assertEquals("abc", StringUtils.defaultString("abc"));
    }

    @Test
    public void testDefault_StringString_1_oe() {
        assertEquals("NULL", StringUtils.defaultString(null, "NULL"));
    }

    @Test
    public void testDefault_StringString_2_oe() {
        assertEquals("", StringUtils.defaultString("", "NULL"));
    }

    @Test
    public void testDefault_StringString_3_oe() {
        assertEquals("abc", StringUtils.defaultString("abc", "NULL"));
    }

    @Test
    public void testDefaultIfBlank_CharBuffers_1_oe() {
        assertEquals("NULL", StringUtils.defaultIfBlank(CharBuffer.wrap(""), CharBuffer.wrap("NULL")).toString());
    }

    @Test
    public void testDefaultIfBlank_CharBuffers_2_oe() {
        assertEquals("NULL", StringUtils.defaultIfBlank(CharBuffer.wrap(" "), CharBuffer.wrap("NULL")).toString());
    }

    @Test
    public void testDefaultIfBlank_CharBuffers_3_oe() {
        assertEquals("abc", StringUtils.defaultIfBlank(CharBuffer.wrap("abc"), CharBuffer.wrap("NULL")).toString());
    }

    @Test
    public void testDefaultIfBlank_CharBuffers_4_oe() {
        assertNull(StringUtils.defaultIfBlank(CharBuffer.wrap(""), (CharBuffer) null));
    }

    @Test
    public void testDefaultIfBlank_CharBuffers_5_oe() {
        final CharBuffer s = StringUtils.defaultIfBlank(CharBuffer.wrap("abc"), CharBuffer.wrap("NULL"));
        assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfBlank_StringBuffers_1_oe() {
        assertEquals("NULL", StringUtils.defaultIfBlank(new StringBuffer(""), new StringBuffer("NULL")).toString());
    }

    @Test
    public void testDefaultIfBlank_StringBuffers_2_oe() {
        assertEquals("NULL", StringUtils.defaultIfBlank(new StringBuffer(" "), new StringBuffer("NULL")).toString());
    }

    @Test
    public void testDefaultIfBlank_StringBuffers_3_oe() {
        assertEquals("abc", StringUtils.defaultIfBlank(new StringBuffer("abc"), new StringBuffer("NULL")).toString());
    }

    @Test
    public void testDefaultIfBlank_StringBuffers_4_oe() {
        assertNull(StringUtils.defaultIfBlank(new StringBuffer(""), (StringBuffer) null));
    }

    @Test
    public void testDefaultIfBlank_StringBuffers_5_oe() {
        final StringBuffer s = StringUtils.defaultIfBlank(new StringBuffer("abc"), new StringBuffer("NULL"));
        assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfBlank_StringBuilders_1_oe() {
        assertEquals("NULL", StringUtils.defaultIfBlank(new StringBuilder(""), new StringBuilder("NULL")).toString());
    }

    @Test
    public void testDefaultIfBlank_StringBuilders_2_oe() {
        assertEquals("NULL", StringUtils.defaultIfBlank(new StringBuilder(" "), new StringBuilder("NULL")).toString());
    }

    @Test
    public void testDefaultIfBlank_StringBuilders_3_oe() {
        assertEquals("abc", StringUtils.defaultIfBlank(new StringBuilder("abc"), new StringBuilder("NULL")).toString());
    }

    @Test
    public void testDefaultIfBlank_StringBuilders_4_oe() {
        assertNull(StringUtils.defaultIfBlank(new StringBuilder(""), (StringBuilder) null));
    }

    @Test
    public void testDefaultIfBlank_StringBuilders_5_oe() {
        final StringBuilder s = StringUtils.defaultIfBlank(new StringBuilder("abc"), new StringBuilder("NULL"));
        assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfBlank_StringString_1_oe() {
        assertEquals("NULL", StringUtils.defaultIfBlank(null, "NULL"));
    }

    @Test
    public void testDefaultIfBlank_StringString_2_oe() {
        assertEquals("NULL", StringUtils.defaultIfBlank("", "NULL"));
    }

    @Test
    public void testDefaultIfBlank_StringString_3_oe() {
        assertEquals("NULL", StringUtils.defaultIfBlank(" ", "NULL"));
    }

    @Test
    public void testDefaultIfBlank_StringString_4_oe() {
        assertEquals("abc", StringUtils.defaultIfBlank("abc", "NULL"));
    }

    @Test
    public void testDefaultIfBlank_StringString_5_oe() {
        assertNull(StringUtils.defaultIfBlank("", (String) null));
    }

    @Test
    public void testDefaultIfBlank_StringString_6_oe() {
        final String s = StringUtils.defaultIfBlank("abc", "NULL");
        assertEquals("abc", s);
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_1_oe() {
        assertEquals("NULL", StringUtils.getIfBlank(null, () -> "NULL"));
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_2_oe() {
        assertEquals("NULL", StringUtils.getIfBlank("",  () -> "NULL"));
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_3_oe() {
        assertEquals("NULL", StringUtils.getIfBlank(" ", () -> "NULL"));
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_4_oe() {
        assertEquals("abc", StringUtils.getIfBlank("abc", () -> "NULL"));
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_5_oe() {
        assertNull(StringUtils.getIfBlank("", () -> null));
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_6_oe() {
        assertNull(StringUtils.defaultIfBlank("", (String) null));
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_7_oe() {
        final String s = StringUtils.getIfBlank("abc", () -> "NULL");
        assertEquals("abc", s);
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_8_oe() {
        final String s = StringUtils.getIfBlank("abc", () -> "NULL");
        final MutableInt numberOfCalls = new MutableInt(0);
        final Supplier<String> countingDefaultSupplier = () -> {
            numberOfCalls.increment();
            return "NULL";
        };
        StringUtils.getIfBlank("abc", countingDefaultSupplier);
        assertEquals(0, numberOfCalls.getValue());
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_9_oe() {
        final String s = StringUtils.getIfBlank("abc", () -> "NULL");
        final MutableInt numberOfCalls = new MutableInt(0);
        final Supplier<String> countingDefaultSupplier = () -> {
            numberOfCalls.increment();
            return "NULL";
        };
        StringUtils.getIfBlank("abc", countingDefaultSupplier);
        StringUtils.getIfBlank("", countingDefaultSupplier);
        assertEquals(1, numberOfCalls.getValue());
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_10_oe() {
        final String s = StringUtils.getIfBlank("abc", () -> "NULL");
        final MutableInt numberOfCalls = new MutableInt(0);
        final Supplier<String> countingDefaultSupplier = () -> {
            numberOfCalls.increment();
            return "NULL";
        };
        StringUtils.getIfBlank("abc", countingDefaultSupplier);
        StringUtils.getIfBlank("", countingDefaultSupplier);
        StringUtils.getIfBlank(" ", countingDefaultSupplier);
        assertEquals(2, numberOfCalls.getValue());
    }

    @Test
    public void testGetIfBlank_StringStringSupplier_11_oe() {
        final String s = StringUtils.getIfBlank("abc", () -> "NULL");
        final MutableInt numberOfCalls = new MutableInt(0);
        final Supplier<String> countingDefaultSupplier = () -> {
            numberOfCalls.increment();
            return "NULL";
        };
        StringUtils.getIfBlank("abc", countingDefaultSupplier);
        StringUtils.getIfBlank("", countingDefaultSupplier);
        StringUtils.getIfBlank(" ", countingDefaultSupplier);
        StringUtils.getIfBlank(null, countingDefaultSupplier);
        assertEquals(3, numberOfCalls.getValue());
    }

    @Test
    public void testDefaultIfEmpty_CharBuffers_1_oe() {
        assertEquals("NULL", StringUtils.defaultIfEmpty(CharBuffer.wrap(""), CharBuffer.wrap("NULL")).toString());
    }

    @Test
    public void testDefaultIfEmpty_CharBuffers_2_oe() {
        assertEquals("abc", StringUtils.defaultIfEmpty(CharBuffer.wrap("abc"), CharBuffer.wrap("NULL")).toString());
    }

    @Test
    public void testDefaultIfEmpty_CharBuffers_3_oe() {
        assertNull(StringUtils.defaultIfEmpty(CharBuffer.wrap(""), (CharBuffer) null));
    }

    @Test
    public void testDefaultIfEmpty_CharBuffers_4_oe() {
        final CharBuffer s = StringUtils.defaultIfEmpty(CharBuffer.wrap("abc"), CharBuffer.wrap("NULL"));
        assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfEmpty_StringBuffers_1_oe() {
        assertEquals("NULL", StringUtils.defaultIfEmpty(new StringBuffer(""), new StringBuffer("NULL")).toString());
    }

    @Test
    public void testDefaultIfEmpty_StringBuffers_2_oe() {
        assertEquals("abc", StringUtils.defaultIfEmpty(new StringBuffer("abc"), new StringBuffer("NULL")).toString());
    }

    @Test
    public void testDefaultIfEmpty_StringBuffers_3_oe() {
        assertNull(StringUtils.defaultIfEmpty(new StringBuffer(""), (StringBuffer) null));
    }

    @Test
    public void testDefaultIfEmpty_StringBuffers_4_oe() {
        final StringBuffer s = StringUtils.defaultIfEmpty(new StringBuffer("abc"), new StringBuffer("NULL"));
        assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfEmpty_StringBuilders_1_oe() {
        assertEquals("NULL", StringUtils.defaultIfEmpty(new StringBuilder(""), new StringBuilder("NULL")).toString());
    }

    @Test
    public void testDefaultIfEmpty_StringBuilders_2_oe() {
        assertEquals("abc", StringUtils.defaultIfEmpty(new StringBuilder("abc"), new StringBuilder("NULL")).toString());
    }

    @Test
    public void testDefaultIfEmpty_StringBuilders_3_oe() {
        assertNull(StringUtils.defaultIfEmpty(new StringBuilder(""), (StringBuilder) null));
    }

    @Test
    public void testDefaultIfEmpty_StringBuilders_4_oe() {
        final StringBuilder s = StringUtils.defaultIfEmpty(new StringBuilder("abc"), new StringBuilder("NULL"));
        assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfEmpty_StringString_1_oe() {
        assertEquals("NULL", StringUtils.defaultIfEmpty(null, "NULL"));
    }

    @Test
    public void testDefaultIfEmpty_StringString_2_oe() {
        assertEquals("NULL", StringUtils.defaultIfEmpty("", "NULL"));
    }

    @Test
    public void testDefaultIfEmpty_StringString_3_oe() {
        assertEquals("abc", StringUtils.defaultIfEmpty("abc", "NULL"));
    }

    @Test
    public void testDefaultIfEmpty_StringString_4_oe() {
        assertNull(StringUtils.getIfEmpty("", null));
    }

    @Test
    public void testDefaultIfEmpty_StringString_5_oe() {
        final String s = StringUtils.defaultIfEmpty("abc", "NULL");
        assertEquals("abc", s);
    }

    @Test
    public void testGetIfEmpty_StringStringSupplier_1_oe() {
        assertEquals("NULL", StringUtils.getIfEmpty((String) null, () -> "NULL"));
    }

    @Test
    public void testGetIfEmpty_StringStringSupplier_2_oe() {
        assertEquals("NULL", StringUtils.getIfEmpty("", () -> "NULL"));
    }

    @Test
    public void testGetIfEmpty_StringStringSupplier_3_oe() {
        assertEquals("abc", StringUtils.getIfEmpty("abc", () -> "NULL"));
    }

    @Test
    public void testGetIfEmpty_StringStringSupplier_4_oe() {
        assertNull(StringUtils.getIfEmpty("", () -> null));
    }

    @Test
    public void testGetIfEmpty_StringStringSupplier_5_oe() {
        assertNull(StringUtils.defaultIfEmpty("", (String) null));
    }

    @Test
    public void testGetIfEmpty_StringStringSupplier_6_oe() {
        final String s = StringUtils.getIfEmpty("abc", () -> "NULL");
        assertEquals("abc", s);
    }

    @Test
    public void testGetIfEmpty_StringStringSupplier_7_oe() {
        final String s = StringUtils.getIfEmpty("abc", () -> "NULL");
        final MutableInt numberOfCalls = new MutableInt(0);
        final Supplier<String> countingDefaultSupplier = () -> {
            numberOfCalls.increment();
            return "NULL";
        };
        StringUtils.getIfEmpty("abc", countingDefaultSupplier);
        assertEquals(0, numberOfCalls.getValue());
    }

    @Test
    public void testGetIfEmpty_StringStringSupplier_8_oe() {
        final String s = StringUtils.getIfEmpty("abc", () -> "NULL");
        final MutableInt numberOfCalls = new MutableInt(0);
        final Supplier<String> countingDefaultSupplier = () -> {
            numberOfCalls.increment();
            return "NULL";
        };
        StringUtils.getIfEmpty("abc", countingDefaultSupplier);
        StringUtils.getIfEmpty("", countingDefaultSupplier);
        assertEquals(1, numberOfCalls.getValue());
    }

    @Test
    public void testGetIfEmpty_StringStringSupplier_9_oe() {
        final String s = StringUtils.getIfEmpty("abc", () -> "NULL");
        final MutableInt numberOfCalls = new MutableInt(0);
        final Supplier<String> countingDefaultSupplier = () -> {
            numberOfCalls.increment();
            return "NULL";
        };
        StringUtils.getIfEmpty("abc", countingDefaultSupplier);
        StringUtils.getIfEmpty("", countingDefaultSupplier);
        StringUtils.getIfEmpty(null, countingDefaultSupplier);
        assertEquals(2, numberOfCalls.getValue());
    }

    @Test
    public void testDeleteWhitespace_String_1_oe() {
        assertNull(StringUtils.deleteWhitespace(null));
    }

    @Test
    public void testDeleteWhitespace_String_2_oe() {
        assertEquals("", StringUtils.deleteWhitespace(""));
    }

    @Test
    public void testDeleteWhitespace_String_3_oe() {
        assertEquals("", StringUtils.deleteWhitespace("  \u000C  \t\t\u001F\n\n \u000B  "));
    }

    @Test
    public void testDeleteWhitespace_String_4_oe() {
        assertEquals("", StringUtils.deleteWhitespace(StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testDeleteWhitespace_String_5_oe() {
        assertEquals(StringUtilsTest.NON_WHITESPACE, StringUtils.deleteWhitespace(StringUtilsTest.NON_WHITESPACE));
    }

    @Test
    public void testDeleteWhitespace_String_6_oe() {
        assertEquals("\u00A0\u202F", StringUtils.deleteWhitespace("  \u00A0  \t\t\n\n \u202F  "));
    }

    @Test
    public void testDeleteWhitespace_String_7_oe() {
        assertEquals("\u00A0\u202F", StringUtils.deleteWhitespace("\u00A0\u202F"));
    }

    @Test
    public void testDeleteWhitespace_String_8_oe() {
        assertEquals("test", StringUtils.deleteWhitespace("\u000Bt  \t\n\u0009e\rs\n\n   \tt"));
    }

    @Test
    public void testDifference_StringString_1_oe() {
        assertNull(StringUtils.difference(null, null));
    }

    @Test
    public void testDifference_StringString_2_oe() {
        assertEquals("", StringUtils.difference("", ""));
    }

    @Test
    public void testDifference_StringString_3_oe() {
        assertEquals("abc", StringUtils.difference("", "abc"));
    }

    @Test
    public void testDifference_StringString_4_oe() {
        assertEquals("", StringUtils.difference("abc", ""));
    }

    @Test
    public void testDifference_StringString_5_oe() {
        assertEquals("i am a robot", StringUtils.difference(null, "i am a robot"));
    }

    @Test
    public void testDifference_StringString_6_oe() {
        assertEquals("i am a machine", StringUtils.difference("i am a machine", null));
    }

    @Test
    public void testDifference_StringString_7_oe() {
        assertEquals("robot", StringUtils.difference("i am a machine", "i am a robot"));
    }

    @Test
    public void testDifference_StringString_8_oe() {
        assertEquals("", StringUtils.difference("abc", "abc"));
    }

    @Test
    public void testDifference_StringString_9_oe() {
        assertEquals("you are a robot", StringUtils.difference("i am a robot", "you are a robot"));
    }

    @Test
    public void testDifferenceAt_StringArray_1_oe() {
        assertEquals(-1, StringUtils.indexOfDifference((String[]) null));
    }

    @Test
    public void testDifferenceAt_StringArray_2_oe() {
        assertEquals(-1, StringUtils.indexOfDifference());
    }

    @Test
    public void testDifferenceAt_StringArray_3_oe() {
        assertEquals(-1, StringUtils.indexOfDifference("abc"));
    }

    @Test
    public void testDifferenceAt_StringArray_4_oe() {
        assertEquals(-1, StringUtils.indexOfDifference(null, null));
    }

    @Test
    public void testDifferenceAt_StringArray_5_oe() {
        assertEquals(-1, StringUtils.indexOfDifference("", ""));
    }

    @Test
    public void testDifferenceAt_StringArray_6_oe() {
        assertEquals(0, StringUtils.indexOfDifference("", null));
    }

    @Test
    public void testDifferenceAt_StringArray_7_oe() {
        assertEquals(0, StringUtils.indexOfDifference("abc", null, null));
    }

    @Test
    public void testDifferenceAt_StringArray_8_oe() {
        assertEquals(0, StringUtils.indexOfDifference(null, null, "abc"));
    }

    @Test
    public void testDifferenceAt_StringArray_9_oe() {
        assertEquals(0, StringUtils.indexOfDifference("", "abc"));
    }

    @Test
    public void testDifferenceAt_StringArray_10_oe() {
        assertEquals(0, StringUtils.indexOfDifference("abc", ""));
    }

    @Test
    public void testDifferenceAt_StringArray_11_oe() {
        assertEquals(-1, StringUtils.indexOfDifference("abc", "abc"));
    }

    @Test
    public void testDifferenceAt_StringArray_12_oe() {
        assertEquals(1, StringUtils.indexOfDifference("abc", "a"));
    }

    @Test
    public void testDifferenceAt_StringArray_13_oe() {
        assertEquals(2, StringUtils.indexOfDifference("ab", "abxyz"));
    }

    @Test
    public void testDifferenceAt_StringArray_14_oe() {
        assertEquals(2, StringUtils.indexOfDifference("abcde", "abxyz"));
    }

    @Test
    public void testDifferenceAt_StringArray_15_oe() {
        assertEquals(0, StringUtils.indexOfDifference("abcde", "xyz"));
    }

    @Test
    public void testDifferenceAt_StringArray_16_oe() {
        assertEquals(0, StringUtils.indexOfDifference("xyz", "abcde"));
    }

    @Test
    public void testDifferenceAt_StringArray_17_oe() {
        assertEquals(7, StringUtils.indexOfDifference("i am a machine", "i am a robot"));
    }

    @Test
    public void testDifferenceAt_StringString_1_oe() {
        assertEquals(-1, StringUtils.indexOfDifference(null, null));
    }

    @Test
    public void testDifferenceAt_StringString_2_oe() {
        assertEquals(0, StringUtils.indexOfDifference(null, "i am a robot"));
    }

    @Test
    public void testDifferenceAt_StringString_3_oe() {
        assertEquals(-1, StringUtils.indexOfDifference("", ""));
    }

    @Test
    public void testDifferenceAt_StringString_4_oe() {
        assertEquals(0, StringUtils.indexOfDifference("", "abc"));
    }

    @Test
    public void testDifferenceAt_StringString_5_oe() {
        assertEquals(0, StringUtils.indexOfDifference("abc", ""));
    }

    @Test
    public void testDifferenceAt_StringString_6_oe() {
        assertEquals(0, StringUtils.indexOfDifference("i am a machine", null));
    }

    @Test
    public void testDifferenceAt_StringString_7_oe() {
        assertEquals(7, StringUtils.indexOfDifference("i am a machine", "i am a robot"));
    }

    @Test
    public void testDifferenceAt_StringString_8_oe() {
        assertEquals(-1, StringUtils.indexOfDifference("foo", "foo"));
    }

    @Test
    public void testDifferenceAt_StringString_9_oe() {
        assertEquals(0, StringUtils.indexOfDifference("i am a robot", "you are a robot"));
    }

    @Test
    public void testEMPTY_1_oe() {
        assertNotNull(StringUtils.EMPTY);
    }

    @Test
    public void testEMPTY_2_oe() {
        assertEquals("", StringUtils.EMPTY);
    }

    @Test
    public void testEMPTY_3_oe() {
        assertEquals(0, StringUtils.EMPTY.length());
    }

    @Test
    public void testEscapeSurrogatePairs_1_oe() {
        assertEquals("\uD83D\uDE30", StringEscapeUtils.escapeCsv("\uD83D\uDE30"));
    }

    @Test
    public void testEscapeSurrogatePairs_2_oe() {
        assertEquals("\uD800\uDC00", StringEscapeUtils.escapeCsv("\uD800\uDC00"));
    }

    @Test
    public void testEscapeSurrogatePairs_3_oe() {
        assertEquals("\uD834\uDD1E", StringEscapeUtils.escapeCsv("\uD834\uDD1E"));
    }

    @Test
    public void testEscapeSurrogatePairs_4_oe() {
        assertEquals("\uDBFF\uDFFD", StringEscapeUtils.escapeCsv("\uDBFF\uDFFD"));
    }

    @Test
    public void testEscapeSurrogatePairs_5_oe() {
        assertEquals("\uDBFF\uDFFD", StringEscapeUtils.escapeHtml3("\uDBFF\uDFFD"));
    }

    @Test
    public void testEscapeSurrogatePairs_6_oe() {
        assertEquals("\uDBFF\uDFFD", StringEscapeUtils.escapeHtml4("\uDBFF\uDFFD"));
    }

    @Test
    public void testEscapeSurrogatePairs_7_oe() {
        assertEquals("\uDBFF\uDFFD", StringEscapeUtils.escapeXml("\uDBFF\uDFFD"));
    }

    @Test
    public void testEscapeSurrogatePairsLang858_1_oe() {
        assertEquals("\\uDBFF\\uDFFD", StringEscapeUtils.escapeJava("\uDBFF\uDFFD"));       //fail LANG-858;
    }

    @Test
    public void testEscapeSurrogatePairsLang858_2_oe() {
        assertEquals("\\uDBFF\\uDFFD", StringEscapeUtils.escapeEcmaScript("\uDBFF\uDFFD")); //fail LANG-858;
    }

    @Test
    public void testGetBytes_Charset_1_oe() {
        assertEquals(ArrayUtils.EMPTY_BYTE_ARRAY, StringUtils.getBytes(null, (Charset) null));
    }

    @Test
    public void testGetBytes_Charset_2_oe() {
        assertArrayEquals(StringUtils.EMPTY.getBytes(), StringUtils.getBytes(StringUtils.EMPTY, (Charset) null));
    }

    @Test
    public void testGetBytes_Charset_3_oe() {
        assertArrayEquals(StringUtils.EMPTY.getBytes(StandardCharsets.US_ASCII),StringUtils.getBytes(StringUtils.EMPTY,StandardCharsets.US_ASCII));
    }

    @Test
    public void testGetBytes_String_1_oe() throws UnsupportedEncodingException {
        assertEquals(ArrayUtils.EMPTY_BYTE_ARRAY, StringUtils.getBytes(null, (String) null));
    }

    @Test
    public void testGetBytes_String_2_oe() throws UnsupportedEncodingException {
        assertArrayEquals(StringUtils.EMPTY.getBytes(), StringUtils.getBytes(StringUtils.EMPTY, (String) null));
    }

    @Test
    public void testGetBytes_String_3_oe() throws UnsupportedEncodingException {
        assertArrayEquals(StringUtils.EMPTY.getBytes(StandardCharsets.US_ASCII.name()),StringUtils.getBytes(StringUtils.EMPTY,StandardCharsets.US_ASCII.name()));
    }

    @Test
    public void testGetCommonPrefix_StringArray_1_oe() {
        assertEquals("", StringUtils.getCommonPrefix((String[]) null));
    }

    @Test
    public void testGetCommonPrefix_StringArray_2_oe() {
        assertEquals("", StringUtils.getCommonPrefix());
    }

    @Test
    public void testGetCommonPrefix_StringArray_3_oe() {
        assertEquals("abc", StringUtils.getCommonPrefix("abc"));
    }

    @Test
    public void testGetCommonPrefix_StringArray_4_oe() {
        assertEquals("", StringUtils.getCommonPrefix(null, null));
    }

    @Test
    public void testGetCommonPrefix_StringArray_5_oe() {
        assertEquals("", StringUtils.getCommonPrefix("", ""));
    }

    @Test
    public void testGetCommonPrefix_StringArray_6_oe() {
        assertEquals("", StringUtils.getCommonPrefix("", null));
    }

    @Test
    public void testGetCommonPrefix_StringArray_7_oe() {
        assertEquals("", StringUtils.getCommonPrefix("abc", null, null));
    }

    @Test
    public void testGetCommonPrefix_StringArray_8_oe() {
        assertEquals("", StringUtils.getCommonPrefix(null, null, "abc"));
    }

    @Test
    public void testGetCommonPrefix_StringArray_9_oe() {
        assertEquals("", StringUtils.getCommonPrefix("", "abc"));
    }

    @Test
    public void testGetCommonPrefix_StringArray_10_oe() {
        assertEquals("", StringUtils.getCommonPrefix("abc", ""));
    }

    @Test
    public void testGetCommonPrefix_StringArray_11_oe() {
        assertEquals("abc", StringUtils.getCommonPrefix("abc", "abc"));
    }

    @Test
    public void testGetCommonPrefix_StringArray_12_oe() {
        assertEquals("a", StringUtils.getCommonPrefix("abc", "a"));
    }

    @Test
    public void testGetCommonPrefix_StringArray_13_oe() {
        assertEquals("ab", StringUtils.getCommonPrefix("ab", "abxyz"));
    }

    @Test
    public void testGetCommonPrefix_StringArray_14_oe() {
        assertEquals("ab", StringUtils.getCommonPrefix("abcde", "abxyz"));
    }

    @Test
    public void testGetCommonPrefix_StringArray_15_oe() {
        assertEquals("", StringUtils.getCommonPrefix("abcde", "xyz"));
    }

    @Test
    public void testGetCommonPrefix_StringArray_16_oe() {
        assertEquals("", StringUtils.getCommonPrefix("xyz", "abcde"));
    }

    @Test
    public void testGetCommonPrefix_StringArray_17_oe() {
        assertEquals("i am a ", StringUtils.getCommonPrefix("i am a machine", "i am a robot"));
    }

    @Test
    public void testGetDigits_1_oe() {
        assertNull(StringUtils.getDigits(null));
    }

    @Test
    public void testGetDigits_2_oe() {
        assertEquals("", StringUtils.getDigits(""));
    }

    @Test
    public void testGetDigits_3_oe() {
        assertEquals("", StringUtils.getDigits("abc"));
    }

    @Test
    public void testGetDigits_4_oe() {
        assertEquals("1000", StringUtils.getDigits("1000$"));
    }

    @Test
    public void testGetDigits_5_oe() {
        assertEquals("12345", StringUtils.getDigits("123password45"));
    }

    @Test
    public void testGetDigits_6_oe() {
        assertEquals("5417543010", StringUtils.getDigits("(541) 754-3010"));
    }

    @Test
    public void testGetDigits_7_oe() {
        assertEquals("\u0967\u0968\u0969", StringUtils.getDigits("\u0967\u0968\u0969"));
    }

    @Test
    public void testGetFuzzyDistance_1_oe() {
        assertEquals(0, StringUtils.getFuzzyDistance("", "", Locale.ENGLISH));
    }

    @Test
    public void testGetFuzzyDistance_2_oe() {
        assertEquals(0, StringUtils.getFuzzyDistance("Workshop", "b", Locale.ENGLISH));
    }

    @Test
    public void testGetFuzzyDistance_3_oe() {
        assertEquals(1, StringUtils.getFuzzyDistance("Room", "o", Locale.ENGLISH));
    }

    @Test
    public void testGetFuzzyDistance_4_oe() {
        assertEquals(1, StringUtils.getFuzzyDistance("Workshop", "w", Locale.ENGLISH));
    }

    @Test
    public void testGetFuzzyDistance_5_oe() {
        assertEquals(2, StringUtils.getFuzzyDistance("Workshop", "ws", Locale.ENGLISH));
    }

    @Test
    public void testGetFuzzyDistance_6_oe() {
        assertEquals(4, StringUtils.getFuzzyDistance("Workshop", "wo", Locale.ENGLISH));
    }

    @Test
    public void testGetFuzzyDistance_7_oe() {
        assertEquals(3, StringUtils.getFuzzyDistance("Apache Software Foundation", "asf", Locale.ENGLISH));
    }

    @Test
    public void testGetFuzzyDistance_NullNullNull_1_oe() throws Exception {
        try {
    StringUtils.getFuzzyDistance(null, null, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFuzzyDistance_NullStringLocale_1_oe() throws Exception {
        try {
    StringUtils.getFuzzyDistance(null, "clear", Locale.ENGLISH);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFuzzyDistance_StringNullLoclae_1_oe() throws Exception {
        try {
    StringUtils.getFuzzyDistance(" ", null, Locale.ENGLISH);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFuzzyDistance_StringStringNull_1_oe() throws Exception {
        try {
    StringUtils.getFuzzyDistance(" ", "clear", null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetJaroWinklerDistance_NullNull_1_oe() throws Exception {
        try {
    StringUtils.getJaroWinklerDistance(null, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetJaroWinklerDistance_NullString_1_oe() throws Exception {
        try {
    StringUtils.getJaroWinklerDistance(null, "clear");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetJaroWinklerDistance_StringNull_1_oe() throws Exception {
        try {
    StringUtils.getJaroWinklerDistance(" ", null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetJaroWinklerDistance_StringString_1_oe() {
        assertEquals(0.93d, StringUtils.getJaroWinklerDistance("frog", "fog"));
    }

    @Test
    public void testGetJaroWinklerDistance_StringString_2_oe() {
        assertEquals(0.0d, StringUtils.getJaroWinklerDistance("fly", "ant"));
    }

    @Test
    public void testGetJaroWinklerDistance_StringString_3_oe() {
        assertEquals(0.44d, StringUtils.getJaroWinklerDistance("elephant", "hippo"));
    }

    @Test
    public void testGetJaroWinklerDistance_StringString_4_oe() {
        assertEquals(0.84d, StringUtils.getJaroWinklerDistance("dwayne", "duane"));
    }

    @Test
    public void testGetJaroWinklerDistance_StringString_5_oe() {
        assertEquals(0.93d, StringUtils.getJaroWinklerDistance("ABC Corporation", "ABC Corp"));
    }

    @Test
    public void testGetJaroWinklerDistance_StringString_6_oe() {
        assertEquals(0.95d, StringUtils.getJaroWinklerDistance("D N H Enterprises Inc", "D & H Enterprises, Inc."));
    }

    @Test
    public void testGetJaroWinklerDistance_StringString_7_oe() {
        assertEquals(0.92d, StringUtils.getJaroWinklerDistance("My Gym Children's Fitness Center", "My Gym. Childrens Fitness"));
    }

    @Test
    public void testGetJaroWinklerDistance_StringString_8_oe() {
        assertEquals(0.88d, StringUtils.getJaroWinklerDistance("PENNSYLVANIA", "PENNCISYLVNIA"));
    }

    @Test
    public void testGetJaroWinklerDistance_StringString_9_oe() {
        assertEquals(0.63d, StringUtils.getJaroWinklerDistance("Haus Ingeborg", "Ingeborg Esser"));
    }

    @Test
    public void testGetLevenshteinDistance_NullString_1_oe() throws Exception {
        try {
    StringUtils.getLevenshteinDistance("a", null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetLevenshteinDistance_NullStringInt_1_oe() throws Exception {
        try {
    StringUtils.getLevenshteinDistance(null, "a", 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetLevenshteinDistance_StringNull_1_oe() throws Exception {
        try {
    StringUtils.getLevenshteinDistance(null, "a");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetLevenshteinDistance_StringNullInt_1_oe() throws Exception {
        try {
    StringUtils.getLevenshteinDistance("a", null, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetLevenshteinDistance_StringString_1_oe() {
        assertEquals(0, StringUtils.getLevenshteinDistance("", ""));
    }

    @Test
    public void testGetLevenshteinDistance_StringString_2_oe() {
        assertEquals(1, StringUtils.getLevenshteinDistance("", "a"));
    }

    @Test
    public void testGetLevenshteinDistance_StringString_3_oe() {
        assertEquals(7, StringUtils.getLevenshteinDistance("aaapppp", ""));
    }

    @Test
    public void testGetLevenshteinDistance_StringString_4_oe() {
        assertEquals(1, StringUtils.getLevenshteinDistance("frog", "fog"));
    }

    @Test
    public void testGetLevenshteinDistance_StringString_5_oe() {
        assertEquals(3, StringUtils.getLevenshteinDistance("fly", "ant"));
    }

    @Test
    public void testGetLevenshteinDistance_StringString_6_oe() {
        assertEquals(7, StringUtils.getLevenshteinDistance("elephant", "hippo"));
    }

    @Test
    public void testGetLevenshteinDistance_StringString_7_oe() {
        assertEquals(7, StringUtils.getLevenshteinDistance("hippo", "elephant"));
    }

    @Test
    public void testGetLevenshteinDistance_StringString_8_oe() {
        assertEquals(8, StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz"));
    }

    @Test
    public void testGetLevenshteinDistance_StringString_9_oe() {
        assertEquals(8, StringUtils.getLevenshteinDistance("zzzzzzzz", "hippo"));
    }

    @Test
    public void testGetLevenshteinDistance_StringString_10_oe() {
        assertEquals(1, StringUtils.getLevenshteinDistance("hello", "hallo"));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_1_oe() {
        assertEquals(0, StringUtils.getLevenshteinDistance("", "", 0));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_2_oe() {
        assertEquals(7, StringUtils.getLevenshteinDistance("aaapppp", "", 8));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_3_oe() {
        assertEquals(7, StringUtils.getLevenshteinDistance("aaapppp", "", 7));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_4_oe() {
        assertEquals(-1, StringUtils.getLevenshteinDistance("aaapppp", "", 6));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_5_oe() {

        assertEquals(-1, StringUtils.getLevenshteinDistance("b", "a", 0));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_6_oe() {

        assertEquals(-1, StringUtils.getLevenshteinDistance("a", "b", 0));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_7_oe() {


        assertEquals(0, StringUtils.getLevenshteinDistance("aa", "aa", 0));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_8_oe() {


        assertEquals(0, StringUtils.getLevenshteinDistance("aa", "aa", 2));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_9_oe() {



        assertEquals(-1, StringUtils.getLevenshteinDistance("aaa", "bbb", 2));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_10_oe() {



        assertEquals(3, StringUtils.getLevenshteinDistance("aaa", "bbb", 3));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_11_oe() {




        assertEquals(6, StringUtils.getLevenshteinDistance("aaaaaa", "b", 10));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_12_oe() {





        assertEquals(7, StringUtils.getLevenshteinDistance("aaapppp", "b", 8));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_13_oe() {





        assertEquals(3, StringUtils.getLevenshteinDistance("a", "bbb", 4));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_14_oe() {






        assertEquals(7, StringUtils.getLevenshteinDistance("aaapppp", "b", 7));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_15_oe() {






        assertEquals(3, StringUtils.getLevenshteinDistance("a", "bbb", 3));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_16_oe() {







        assertEquals(-1, StringUtils.getLevenshteinDistance("a", "bbb", 2));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_17_oe() {







        assertEquals(-1, StringUtils.getLevenshteinDistance("bbb", "a", 2));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_18_oe() {







        assertEquals(-1, StringUtils.getLevenshteinDistance("aaapppp", "b", 6));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_19_oe() {








        assertEquals(-1, StringUtils.getLevenshteinDistance("a", "bbb", 1));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_20_oe() {








        assertEquals(-1, StringUtils.getLevenshteinDistance("bbb", "a", 1));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_21_oe() {









        assertEquals(-1, StringUtils.getLevenshteinDistance("12345", "1234567", 1));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_22_oe() {









        assertEquals(-1, StringUtils.getLevenshteinDistance("1234567", "12345", 1));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_23_oe() {










        assertEquals(1, StringUtils.getLevenshteinDistance("frog", "fog", 1));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_24_oe() {










        assertEquals(3, StringUtils.getLevenshteinDistance("fly", "ant", 3));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_25_oe() {










        assertEquals(7, StringUtils.getLevenshteinDistance("elephant", "hippo", 7));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_26_oe() {










        assertEquals(-1, StringUtils.getLevenshteinDistance("elephant", "hippo", 6));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_27_oe() {










        assertEquals(7, StringUtils.getLevenshteinDistance("hippo", "elephant", 7));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_28_oe() {










        assertEquals(-1, StringUtils.getLevenshteinDistance("hippo", "elephant", 6));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_29_oe() {










        assertEquals(8, StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz", 8));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_30_oe() {










        assertEquals(8, StringUtils.getLevenshteinDistance("zzzzzzzz", "hippo", 8));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_31_oe() {










        assertEquals(1, StringUtils.getLevenshteinDistance("hello", "hallo", 1));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_32_oe() {











        assertEquals(1, StringUtils.getLevenshteinDistance("frog", "fog", Integer.MAX_VALUE));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_33_oe() {











        assertEquals(3, StringUtils.getLevenshteinDistance("fly", "ant", Integer.MAX_VALUE));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_34_oe() {











        assertEquals(7, StringUtils.getLevenshteinDistance("elephant", "hippo", Integer.MAX_VALUE));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_35_oe() {











        assertEquals(7, StringUtils.getLevenshteinDistance("hippo", "elephant", Integer.MAX_VALUE));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_36_oe() {











        assertEquals(8, StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz", Integer.MAX_VALUE));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_37_oe() {











        assertEquals(8, StringUtils.getLevenshteinDistance("zzzzzzzz", "hippo", Integer.MAX_VALUE));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt_38_oe() {











        assertEquals(1, StringUtils.getLevenshteinDistance("hello", "hallo", Integer.MAX_VALUE));
    }

    @Test
    public void testGetLevenshteinDistance_StringStringNegativeInt_1_oe() throws Exception {
        try {
    StringUtils.getLevenshteinDistance("a", "a", -1);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testIsAllLowerCase_1_oe() {
        assertFalse(StringUtils.isAllLowerCase(null));
    }

    @Test
    public void testIsAllLowerCase_2_oe() {
        assertFalse(StringUtils.isAllLowerCase(StringUtils.EMPTY));
    }

    @Test
    public void testIsAllLowerCase_3_oe() {
        assertFalse(StringUtils.isAllLowerCase("  "));
    }

    @Test
    public void testIsAllLowerCase_4_oe() {
        assertTrue(StringUtils.isAllLowerCase("abc"));
    }

    @Test
    public void testIsAllLowerCase_5_oe() {
        assertFalse(StringUtils.isAllLowerCase("abc "));
    }

    @Test
    public void testIsAllLowerCase_6_oe() {
        assertFalse(StringUtils.isAllLowerCase("abc\n"));
    }

    @Test
    public void testIsAllLowerCase_7_oe() {
        assertFalse(StringUtils.isAllLowerCase("abC"));
    }

    @Test
    public void testIsAllLowerCase_8_oe() {
        assertFalse(StringUtils.isAllLowerCase("ab c"));
    }

    @Test
    public void testIsAllLowerCase_9_oe() {
        assertFalse(StringUtils.isAllLowerCase("ab1c"));
    }

    @Test
    public void testIsAllLowerCase_10_oe() {
        assertFalse(StringUtils.isAllLowerCase("ab/c"));
    }

    @Test
    public void testIsAllUpperCase_1_oe() {
        assertFalse(StringUtils.isAllUpperCase(null));
    }

    @Test
    public void testIsAllUpperCase_2_oe() {
        assertFalse(StringUtils.isAllUpperCase(StringUtils.EMPTY));
    }

    @Test
    public void testIsAllUpperCase_3_oe() {
        assertFalse(StringUtils.isAllUpperCase("  "));
    }

    @Test
    public void testIsAllUpperCase_4_oe() {
        assertTrue(StringUtils.isAllUpperCase("ABC"));
    }

    @Test
    public void testIsAllUpperCase_5_oe() {
        assertFalse(StringUtils.isAllUpperCase("ABC "));
    }

    @Test
    public void testIsAllUpperCase_6_oe() {
        assertFalse(StringUtils.isAllUpperCase("ABC\n"));
    }

    @Test
    public void testIsAllUpperCase_7_oe() {
        assertFalse(StringUtils.isAllUpperCase("aBC"));
    }

    @Test
    public void testIsAllUpperCase_8_oe() {
        assertFalse(StringUtils.isAllUpperCase("A C"));
    }

    @Test
    public void testIsAllUpperCase_9_oe() {
        assertFalse(StringUtils.isAllUpperCase("A1C"));
    }

    @Test
    public void testIsAllUpperCase_10_oe() {
        assertFalse(StringUtils.isAllUpperCase("A/C"));
    }

    @Test
    public void testIsMixedCase_1_oe() {
        assertFalse(StringUtils.isMixedCase(null));
    }

    @Test
    public void testIsMixedCase_2_oe() {
        assertFalse(StringUtils.isMixedCase(StringUtils.EMPTY));
    }

    @Test
    public void testIsMixedCase_3_oe() {
        assertFalse(StringUtils.isMixedCase(" "));
    }

    @Test
    public void testIsMixedCase_4_oe() {
        assertFalse(StringUtils.isMixedCase("A"));
    }

    @Test
    public void testIsMixedCase_5_oe() {
        assertFalse(StringUtils.isMixedCase("a"));
    }

    @Test
    public void testIsMixedCase_6_oe() {
        assertFalse(StringUtils.isMixedCase("/"));
    }

    @Test
    public void testIsMixedCase_7_oe() {
        assertFalse(StringUtils.isMixedCase("A/"));
    }

    @Test
    public void testIsMixedCase_8_oe() {
        assertFalse(StringUtils.isMixedCase("/b"));
    }

    @Test
    public void testIsMixedCase_9_oe() {
        assertFalse(StringUtils.isMixedCase("abc"));
    }

    @Test
    public void testIsMixedCase_10_oe() {
        assertFalse(StringUtils.isMixedCase("ABC"));
    }

    @Test
    public void testIsMixedCase_11_oe() {
        assertTrue(StringUtils.isMixedCase("aBc"));
    }

    @Test
    public void testIsMixedCase_12_oe() {
        assertTrue(StringUtils.isMixedCase("aBc "));
    }

    @Test
    public void testIsMixedCase_13_oe() {
        assertTrue(StringUtils.isMixedCase("A c"));
    }

    @Test
    public void testIsMixedCase_14_oe() {
        assertTrue(StringUtils.isMixedCase("aBc\n"));
    }

    @Test
    public void testIsMixedCase_15_oe() {
        assertTrue(StringUtils.isMixedCase("A1c"));
    }

    @Test
    public void testIsMixedCase_16_oe() {
        assertTrue(StringUtils.isMixedCase("a/C"));
    }

    @Test
    public void testJoin_ArrayCharSeparator_1_oe() {
        assertNull(StringUtils.join((Object[]) null, ','));
    }

    @Test
    public void testJoin_ArrayCharSeparator_2_oe() {
        assertEquals(TEXT_LIST_CHAR, StringUtils.join(ARRAY_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayCharSeparator_3_oe() {
        assertEquals("", StringUtils.join(EMPTY_ARRAY_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayCharSeparator_4_oe() {
        assertEquals(";;foo", StringUtils.join(MIXED_ARRAY_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayCharSeparator_5_oe() {
        assertEquals("foo;2", StringUtils.join(MIXED_TYPE_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayCharSeparator_6_oe() {

        assertNull(StringUtils.join((Object[]) null, ',', 0, 1));
    }

    @Test
    public void testJoin_ArrayCharSeparator_7_oe() {

        assertEquals("/", StringUtils.join(MIXED_ARRAY_LIST, '/', 0, MIXED_ARRAY_LIST.length - 1));
    }

    @Test
    public void testJoin_ArrayCharSeparator_8_oe() {

        assertEquals("foo", StringUtils.join(MIXED_TYPE_LIST, '/', 0, 1));
    }

    @Test
    public void testJoin_ArrayCharSeparator_9_oe() {

        assertEquals("null", StringUtils.join(NULL_TO_STRING_LIST, '/', 0, 1));
    }

    @Test
    public void testJoin_ArrayCharSeparator_10_oe() {

        assertEquals("foo/2", StringUtils.join(MIXED_TYPE_LIST, '/', 0, 2));
    }

    @Test
    public void testJoin_ArrayCharSeparator_11_oe() {

        assertEquals("2", StringUtils.join(MIXED_TYPE_LIST, '/', 1, 2));
    }

    @Test
    public void testJoin_ArrayCharSeparator_12_oe() {

        assertEquals("", StringUtils.join(MIXED_TYPE_LIST, '/', 2, 1));
    }

    @Test
    public void testJoin_ArrayOfBytes_1_oe() {
        assertNull(StringUtils.join((byte[]) null, ','));
    }

    @Test
    public void testJoin_ArrayOfBytes_2_oe() {
        assertEquals("1;2", StringUtils.join(BYTE_PRIM_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfBytes_3_oe() {
        assertEquals("2", StringUtils.join(BYTE_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
    }

    @Test
    public void testJoin_ArrayOfBytes_4_oe() {
        assertNull(StringUtils.join((byte[]) null, SEPARATOR_CHAR, 0, 1));
    }

    @Test
    public void testJoin_ArrayOfBytes_5_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(BYTE_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
    }

    @Test
    public void testJoin_ArrayOfBytes_6_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(BYTE_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfBooleans_1_oe() {
        assertNull(StringUtils.join((boolean[]) null, COMMA_SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfBooleans_2_oe() {
        assertEquals("false;false", StringUtils.join(ARRAY_FALSE_FALSE, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfBooleans_3_oe() {
        assertEquals("", StringUtils.join(EMPTY, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfBooleans_4_oe() {
        assertEquals("false,true,false", StringUtils.join(ARRAY_FALSE_TRUE_FALSE, COMMA_SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfBooleans_5_oe() {
        assertEquals("true", StringUtils.join(ARRAY_FALSE_TRUE, SEPARATOR_CHAR, 1, 2));
    }

    @Test
    public void testJoin_ArrayOfBooleans_6_oe() {
        assertNull(StringUtils.join((boolean[]) null, SEPARATOR_CHAR, 0, 1));
    }

    @Test
    public void testJoin_ArrayOfBooleans_7_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(ARRAY_FALSE_FALSE, SEPARATOR_CHAR, 0, 0));
    }

    @Test
    public void testJoin_ArrayOfBooleans_8_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(ARRAY_FALSE_TRUE_FALSE, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfChars_1_oe() {
        assertNull(StringUtils.join((char[]) null, ','));
    }

    @Test
    public void testJoin_ArrayOfChars_2_oe() {
        assertEquals("1;2", StringUtils.join(CHAR_PRIM_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfChars_3_oe() {
        assertEquals("2", StringUtils.join(CHAR_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
    }

    @Test
    public void testJoin_ArrayOfChars_4_oe() {
        assertNull(StringUtils.join((char[]) null, SEPARATOR_CHAR, 0, 1));
    }

    @Test
    public void testJoin_ArrayOfChars_5_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(CHAR_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
    }

    @Test
    public void testJoin_ArrayOfChars_6_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(CHAR_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfDoubles_1_oe() {
        assertNull(StringUtils.join((double[]) null, ','));
    }

    @Test
    public void testJoin_ArrayOfDoubles_2_oe() {
        assertEquals("1.0;2.0", StringUtils.join(DOUBLE_PRIM_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfDoubles_3_oe() {
        assertEquals("2.0", StringUtils.join(DOUBLE_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
    }

    @Test
    public void testJoin_ArrayOfDoubles_4_oe() {
        assertNull(StringUtils.join((double[]) null, SEPARATOR_CHAR, 0, 1));
    }

    @Test
    public void testJoin_ArrayOfDoubles_5_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(DOUBLE_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
    }

    @Test
    public void testJoin_ArrayOfDoubles_6_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(DOUBLE_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfFloats_1_oe() {
        assertNull(StringUtils.join((float[]) null, ','));
    }

    @Test
    public void testJoin_ArrayOfFloats_2_oe() {
        assertEquals("1.0;2.0", StringUtils.join(FLOAT_PRIM_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfFloats_3_oe() {
        assertEquals("2.0", StringUtils.join(FLOAT_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
    }

    @Test
    public void testJoin_ArrayOfFloats_4_oe() {
        assertNull(StringUtils.join((float[]) null, SEPARATOR_CHAR, 0, 1));
    }

    @Test
    public void testJoin_ArrayOfFloats_5_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(FLOAT_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
    }

    @Test
    public void testJoin_ArrayOfFloats_6_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(FLOAT_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfInts_1_oe() {
        assertNull(StringUtils.join((int[]) null, ','));
    }

    @Test
    public void testJoin_ArrayOfInts_2_oe() {
        assertEquals("1;2", StringUtils.join(INT_PRIM_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfInts_3_oe() {
        assertEquals("2", StringUtils.join(INT_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
    }

    @Test
    public void testJoin_ArrayOfInts_4_oe() {
        assertNull(StringUtils.join((int[]) null, SEPARATOR_CHAR, 0, 1));
    }

    @Test
    public void testJoin_ArrayOfInts_5_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(INT_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
    }

    @Test
    public void testJoin_ArrayOfInts_6_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(INT_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfLongs_1_oe() {
        assertNull(StringUtils.join((long[]) null, ','));
    }

    @Test
    public void testJoin_ArrayOfLongs_2_oe() {
        assertEquals("1;2", StringUtils.join(LONG_PRIM_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfLongs_3_oe() {
        assertEquals("2", StringUtils.join(LONG_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
    }

    @Test
    public void testJoin_ArrayOfLongs_4_oe() {
        assertNull(StringUtils.join((long[]) null, SEPARATOR_CHAR, 0, 1));
    }

    @Test
    public void testJoin_ArrayOfLongs_5_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(LONG_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
    }

    @Test
    public void testJoin_ArrayOfLongs_6_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(LONG_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfShorts_1_oe() {
        assertNull(StringUtils.join((short[]) null, ','));
    }

    @Test
    public void testJoin_ArrayOfShorts_2_oe() {
        assertEquals("1;2", StringUtils.join(SHORT_PRIM_LIST, SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_ArrayOfShorts_3_oe() {
        assertEquals("2", StringUtils.join(SHORT_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
    }

    @Test
    public void testJoin_ArrayOfShorts_4_oe() {
        assertNull(StringUtils.join((short[]) null, SEPARATOR_CHAR, 0, 1));
    }

    @Test
    public void testJoin_ArrayOfShorts_5_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(SHORT_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
    }

    @Test
    public void testJoin_ArrayOfShorts_6_oe() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(SHORT_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayString_1_oe() {
        assertNull(StringUtils.join((Object[]) null, null));
    }

    @Test
    public void testJoin_ArrayString_2_oe() {
        assertEquals(TEXT_LIST_NOSEP, StringUtils.join(ARRAY_LIST, null));
    }

    @Test
    public void testJoin_ArrayString_3_oe() {
        assertEquals(TEXT_LIST_NOSEP, StringUtils.join(ARRAY_LIST, ""));
    }

    @Test
    public void testJoin_ArrayString_4_oe() {

        assertEquals("", StringUtils.join(NULL_ARRAY_LIST, null));
    }

    @Test
    public void testJoin_ArrayString_5_oe() {


        assertEquals("", StringUtils.join(EMPTY_ARRAY_LIST, null));
    }

    @Test
    public void testJoin_ArrayString_6_oe() {


        assertEquals("", StringUtils.join(EMPTY_ARRAY_LIST, ""));
    }

    @Test
    public void testJoin_ArrayString_7_oe() {


        assertEquals("", StringUtils.join(EMPTY_ARRAY_LIST, SEPARATOR));
    }

    @Test
    public void testJoin_ArrayString_8_oe() {



        assertEquals(TEXT_LIST, StringUtils.join(ARRAY_LIST, SEPARATOR));
    }

    @Test
    public void testJoin_ArrayString_9_oe() {



        assertEquals(",,foo", StringUtils.join(MIXED_ARRAY_LIST, SEPARATOR));
    }

    @Test
    public void testJoin_ArrayString_10_oe() {



        assertEquals("foo,2", StringUtils.join(MIXED_TYPE_LIST, SEPARATOR));
    }

    @Test
    public void testJoin_ArrayString_11_oe() {




        assertEquals("/", StringUtils.join(MIXED_ARRAY_LIST, "/", 0, MIXED_ARRAY_LIST.length - 1));
    }

    @Test
    public void testJoin_ArrayString_12_oe() {




        assertEquals("", StringUtils.join(MIXED_ARRAY_LIST, "", 0, MIXED_ARRAY_LIST.length - 1));
    }

    @Test
    public void testJoin_ArrayString_13_oe() {




        assertEquals("foo", StringUtils.join(MIXED_TYPE_LIST, "/", 0, 1));
    }

    @Test
    public void testJoin_ArrayString_14_oe() {




        assertEquals("foo/2", StringUtils.join(MIXED_TYPE_LIST, "/", 0, 2));
    }

    @Test
    public void testJoin_ArrayString_15_oe() {




        assertEquals("2", StringUtils.join(MIXED_TYPE_LIST, "/", 1, 2));
    }

    @Test
    public void testJoin_ArrayString_16_oe() {




        assertEquals("", StringUtils.join(MIXED_TYPE_LIST, "/", 2, 1));
    }

    @Test
    public void testJoin_IterableChar_1_oe() {
        assertNull(StringUtils.join((Iterable<?>) null, ','));
    }

    @Test
    public void testJoin_IterableChar_2_oe() {
        assertEquals(TEXT_LIST_CHAR, StringUtils.join(Arrays.asList(ARRAY_LIST), SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_IterableChar_3_oe() {
        assertEquals("", StringUtils.join(Arrays.asList(NULL_ARRAY_LIST), SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_IterableChar_4_oe() {
        assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST), SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_IterableChar_5_oe() {
        assertEquals("foo", StringUtils.join(Collections.singleton("foo"), 'x'));
    }

    @Test
    public void testJoin_IterableString_1_oe() {
        assertNull(StringUtils.join((Iterable<?>) null, null));
    }

    @Test
    public void testJoin_IterableString_2_oe() {
        assertEquals(TEXT_LIST_NOSEP, StringUtils.join(Arrays.asList(ARRAY_LIST), null));
    }

    @Test
    public void testJoin_IterableString_3_oe() {
        assertEquals(TEXT_LIST_NOSEP, StringUtils.join(Arrays.asList(ARRAY_LIST), ""));
    }

    @Test
    public void testJoin_IterableString_4_oe() {
        assertEquals("foo", StringUtils.join(Collections.singleton("foo"), "x"));
    }

    @Test
    public void testJoin_IterableString_5_oe() {
        assertEquals("foo", StringUtils.join(Collections.singleton("foo"), null));
    }

    @Test
    public void testJoin_IterableString_6_oe() {

        assertEquals("", StringUtils.join(Arrays.asList(NULL_ARRAY_LIST), null));
    }

    @Test
    public void testJoin_IterableString_7_oe() {


        assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST), null));
    }

    @Test
    public void testJoin_IterableString_8_oe() {


        assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST), ""));
    }

    @Test
    public void testJoin_IterableString_9_oe() {


        assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST), SEPARATOR));
    }

    @Test
    public void testJoin_IterableString_10_oe() {



        assertEquals(TEXT_LIST, StringUtils.join(Arrays.asList(ARRAY_LIST), SEPARATOR));
    }

    @Test
    public void testJoin_IteratorChar_1_oe() {
        assertNull(StringUtils.join((Iterator<?>) null, ','));
    }

    @Test
    public void testJoin_IteratorChar_2_oe() {
        assertEquals(TEXT_LIST_CHAR, StringUtils.join(Arrays.asList(ARRAY_LIST).iterator(), SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_IteratorChar_3_oe() {
        assertEquals("", StringUtils.join(Arrays.asList(NULL_ARRAY_LIST).iterator(), SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_IteratorChar_4_oe() {
        assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST).iterator(), SEPARATOR_CHAR));
    }

    @Test
    public void testJoin_IteratorChar_5_oe() {
        assertEquals("foo", StringUtils.join(Collections.singleton("foo").iterator(), 'x'));
    }

    @Test
    public void testJoin_IteratorString_1_oe() {
        assertNull(StringUtils.join((Iterator<?>) null, null));
    }

    @Test
    public void testJoin_IteratorString_2_oe() {
        assertEquals(TEXT_LIST_NOSEP, StringUtils.join(Arrays.asList(ARRAY_LIST).iterator(), null));
    }

    @Test
    public void testJoin_IteratorString_3_oe() {
        assertEquals(TEXT_LIST_NOSEP, StringUtils.join(Arrays.asList(ARRAY_LIST).iterator(), ""));
    }

    @Test
    public void testJoin_IteratorString_4_oe() {
        assertEquals("foo", StringUtils.join(Collections.singleton("foo").iterator(), "x"));
    }

    @Test
    public void testJoin_IteratorString_5_oe() {
        assertEquals("foo", StringUtils.join(Collections.singleton("foo").iterator(), null));
    }

    @Test
    public void testJoin_IteratorString_6_oe() {

        assertEquals("", StringUtils.join(Arrays.asList(NULL_ARRAY_LIST).iterator(), null));
    }

    @Test
    public void testJoin_IteratorString_7_oe() {


        assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST).iterator(), null));
    }

    @Test
    public void testJoin_IteratorString_8_oe() {


        assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST).iterator(), ""));
    }

    @Test
    public void testJoin_IteratorString_9_oe() {


        assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST).iterator(), SEPARATOR));
    }

    @Test
    public void testJoin_IteratorString_10_oe() {



        assertEquals(TEXT_LIST, StringUtils.join(Arrays.asList(ARRAY_LIST).iterator(), SEPARATOR));
    }

    @Test
    public void testJoin_IteratorString_11_oe() {




        assertNull(StringUtils.join(Arrays.asList(NULL_TO_STRING_LIST).iterator(), SEPARATOR));
    }

    @Test
    public void testJoin_List_1_oe() {
        assertNull(StringUtils.join((List<String>) null, null));
    }

    @Test
    public void testJoin_List_2_oe() {
        assertEquals(TEXT_LIST_NOSEP, StringUtils.join(STRING_LIST, null));
    }

    @Test
    public void testJoin_List_3_oe() {
        assertEquals(TEXT_LIST_NOSEP, StringUtils.join(STRING_LIST, ""));
    }

    @Test
    public void testJoin_List_4_oe() {

        assertEquals("", StringUtils.join(NULL_STRING_LIST, null));
    }

    @Test
    public void testJoin_List_5_oe() {


        assertEquals("", StringUtils.join(EMPTY_STRING_LIST, null));
    }

    @Test
    public void testJoin_List_6_oe() {


        assertEquals("", StringUtils.join(EMPTY_STRING_LIST, ""));
    }

    @Test
    public void testJoin_List_7_oe() {


        assertEquals("", StringUtils.join(EMPTY_STRING_LIST, SEPARATOR));
    }

    @Test
    public void testJoin_List_8_oe() {



        assertEquals(TEXT_LIST, StringUtils.join(STRING_LIST, SEPARATOR));
    }

    @Test
    public void testJoin_List_9_oe() {



        assertEquals(",,foo", StringUtils.join(MIXED_STRING_LIST, SEPARATOR));
    }

    @Test
    public void testJoin_List_10_oe() {



        assertEquals("foo,2", StringUtils.join(MIXED_TYPE_OBJECT_LIST, SEPARATOR));
    }

    @Test
    public void testJoin_List_11_oe() {




        assertEquals("/", StringUtils.join(MIXED_STRING_LIST, "/", 0, MIXED_STRING_LIST.size() - 1));
    }

    @Test
    public void testJoin_List_12_oe() {




        assertEquals("", StringUtils.join(MIXED_STRING_LIST, "", 0, MIXED_STRING_LIST.size()- 1));
    }

    @Test
    public void testJoin_List_13_oe() {




        assertEquals("foo", StringUtils.join(MIXED_TYPE_OBJECT_LIST, "/", 0, 1));
    }

    @Test
    public void testJoin_List_14_oe() {




        assertEquals("foo/2", StringUtils.join(MIXED_TYPE_OBJECT_LIST, "/", 0, 2));
    }

    @Test
    public void testJoin_List_15_oe() {




        assertEquals("2", StringUtils.join(MIXED_TYPE_OBJECT_LIST, "/", 1, 2));
    }

    @Test
    public void testJoin_List_16_oe() {




        assertEquals("", StringUtils.join(MIXED_TYPE_OBJECT_LIST, "/", 2, 1));
    }

    @Test
    public void testJoin_List_17_oe() {




        assertNull(null, StringUtils.join((List<?>) null, "/", 0, 1));
    }

    @Test
    public void testJoin_List_18_oe() {





        assertEquals("/", StringUtils.join(MIXED_STRING_LIST, '/', 0, MIXED_STRING_LIST.size() - 1));
    }

    @Test
    public void testJoin_List_19_oe() {





        assertEquals("foo", StringUtils.join(MIXED_TYPE_OBJECT_LIST, '/', 0, 1));
    }

    @Test
    public void testJoin_List_20_oe() {





        assertEquals("foo/2", StringUtils.join(MIXED_TYPE_OBJECT_LIST, '/', 0, 2));
    }

    @Test
    public void testJoin_List_21_oe() {





        assertEquals("2", StringUtils.join(MIXED_TYPE_OBJECT_LIST, '/', 1, 2));
    }

    @Test
    public void testJoin_List_22_oe() {





        assertEquals("", StringUtils.join(MIXED_TYPE_OBJECT_LIST, '/', 2, 1));
    }

    @Test
    public void testJoin_List_23_oe() {





        assertNull(null, StringUtils.join((List<?>) null, '/', 0, 1));
    }

    @Test
    public void testJoin_Objectarray_1_oe() {
        assertNull(StringUtils.join((Object[]) null)); // equivalent explicit cast;
    }

    @Test
    public void testJoin_Objectarray_2_oe() {
        assertEquals("", StringUtils.join()); // empty array;
    }

    @Test
    public void testJoin_Objectarray_3_oe() {
        assertEquals("", StringUtils.join((Object) null)); // => new Object[]{null};
    }

    @Test
    public void testJoin_Objectarray_4_oe() {

        assertEquals("", StringUtils.join(EMPTY_ARRAY_LIST));
    }

    @Test
    public void testJoin_Objectarray_5_oe() {

        assertEquals("", StringUtils.join(NULL_ARRAY_LIST));
    }

    @Test
    public void testJoin_Objectarray_6_oe() {

        assertEquals("null", StringUtils.join(NULL_TO_STRING_LIST));
    }

    @Test
    public void testJoin_Objectarray_7_oe() {

        assertEquals("abc", StringUtils.join("a", "b", "c"));
    }

    @Test
    public void testJoin_Objectarray_8_oe() {

        assertEquals("a", StringUtils.join(null, "a", ""));
    }

    @Test
    public void testJoin_Objectarray_9_oe() {

        assertEquals("foo", StringUtils.join(MIXED_ARRAY_LIST));
    }

    @Test
    public void testJoin_Objectarray_10_oe() {

        assertEquals("foo2", StringUtils.join(MIXED_TYPE_LIST));
    }

    @Test
    public void testJoin_Objects_1_oe() {
        assertEquals("abc", StringUtils.join("a", "b", "c"));
    }

    @Test
    public void testJoin_Objects_2_oe() {
        assertEquals("a", StringUtils.join(null, "", "a"));
    }

    @Test
    public void testJoin_Objects_3_oe() {
        assertNull(StringUtils.join((Object[]) null));
    }

    @Test
    public void testJoinWith_1_oe() {
        assertEquals("",StringUtils.joinWith(","));// empty array assertEquals("",StringUtils.joinWith(",",(Object[])NULL_ARRAY_LIST));
    }

    @Test
    public void testJoinWith_2_oe() {
        assertEquals("null",StringUtils.joinWith(",",NULL_TO_STRING_LIST));//toString method prints 'null' assertEquals("a,b,c",StringUtils.joinWith(",","a","b","c"));
    }

    @Test
    public void testJoinWith_3_oe() {
        assertEquals(",a,", StringUtils.joinWith(",", null, "a", ""));
    }

    @Test
    public void testJoinWith_4_oe() {
        assertEquals(",a,", StringUtils.joinWith(",", "", "a", ""));
    }

    @Test
    public void testJoinWith_5_oe() {

        assertEquals("ab", StringUtils.joinWith(null, "a", "b"));
    }

    @Test
    public void testJoinWithThrowsException_1_oe() throws Exception {
        try {
    StringUtils.joinWith(",", (Object[]) null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testLang623_1_oe() {
        assertEquals("t", StringUtils.replaceChars("\u00DE", '\u00DE', 't'));
    }

    @Test
    public void testLang623_2_oe() {
        assertEquals("t", StringUtils.replaceChars("\u00FE", '\u00FE', 't'));
    }

    @Test
    public void testLANG666_1_oe() {
        assertEquals("12", StringUtils.stripEnd("120.00", ".0"));
    }

    @Test
    public void testLANG666_2_oe() {
        assertEquals("121", StringUtils.stripEnd("121.00", ".0"));
    }

    @Test
    public void testLeftPad_StringInt_1_oe() {
        assertNull(StringUtils.leftPad(null, 5));
    }

    @Test
    public void testLeftPad_StringInt_2_oe() {
        assertEquals("     ", StringUtils.leftPad("", 5));
    }

    @Test
    public void testLeftPad_StringInt_3_oe() {
        assertEquals("  abc", StringUtils.leftPad("abc", 5));
    }

    @Test
    public void testLeftPad_StringInt_4_oe() {
        assertEquals("abc", StringUtils.leftPad("abc", 2));
    }

    @Test
    public void testLeftPad_StringIntChar_1_oe() {
        assertNull(StringUtils.leftPad(null, 5, ' '));
    }

    @Test
    public void testLeftPad_StringIntChar_2_oe() {
        assertEquals("     ", StringUtils.leftPad("", 5, ' '));
    }

    @Test
    public void testLeftPad_StringIntChar_3_oe() {
        assertEquals("  abc", StringUtils.leftPad("abc", 5, ' '));
    }

    @Test
    public void testLeftPad_StringIntChar_4_oe() {
        assertEquals("xxabc", StringUtils.leftPad("abc", 5, 'x'));
    }

    @Test
    public void testLeftPad_StringIntChar_5_oe() {
        assertEquals("\uffff\uffffabc", StringUtils.leftPad("abc", 5, '\uffff'));
    }

    @Test
    public void testLeftPad_StringIntChar_6_oe() {
        assertEquals("abc", StringUtils.leftPad("abc", 2, ' '));
    }

    @Test
    public void testLeftPad_StringIntChar_7_oe() {
        final String str = StringUtils.leftPad("aaa", 10000, 'a');  // bigger than pad length
        assertEquals(10000, str.length());
    }

    @Test
    public void testLeftPad_StringIntChar_8_oe() {
        final String str = StringUtils.leftPad("aaa", 10000, 'a');  // bigger than pad length
        assertTrue(StringUtils.containsOnly(str, 'a'));
    }

    @Test
    public void testLeftPad_StringIntString_1_oe() {
        assertNull(StringUtils.leftPad(null, 5, "-+"));
    }

    @Test
    public void testLeftPad_StringIntString_2_oe() {
        assertNull(StringUtils.leftPad(null, 5, null));
    }

    @Test
    public void testLeftPad_StringIntString_3_oe() {
        assertEquals("     ", StringUtils.leftPad("", 5, " "));
    }

    @Test
    public void testLeftPad_StringIntString_4_oe() {
        assertEquals("-+-+abc", StringUtils.leftPad("abc", 7, "-+"));
    }

    @Test
    public void testLeftPad_StringIntString_5_oe() {
        assertEquals("-+~abc", StringUtils.leftPad("abc", 6, "-+~"));
    }

    @Test
    public void testLeftPad_StringIntString_6_oe() {
        assertEquals("-+abc", StringUtils.leftPad("abc", 5, "-+~"));
    }

    @Test
    public void testLeftPad_StringIntString_7_oe() {
        assertEquals("abc", StringUtils.leftPad("abc", 2, " "));
    }

    @Test
    public void testLeftPad_StringIntString_8_oe() {
        assertEquals("abc", StringUtils.leftPad("abc", -1, " "));
    }

    @Test
    public void testLeftPad_StringIntString_9_oe() {
        assertEquals("  abc", StringUtils.leftPad("abc", 5, null));
    }

    @Test
    public void testLeftPad_StringIntString_10_oe() {
        assertEquals("  abc", StringUtils.leftPad("abc", 5, ""));
    }

    @Test
    public void testLength_CharBuffer_1_oe() {
        assertEquals(0, StringUtils.length(CharBuffer.wrap("")));
    }

    @Test
    public void testLength_CharBuffer_2_oe() {
        assertEquals(1, StringUtils.length(CharBuffer.wrap("A")));
    }

    @Test
    public void testLength_CharBuffer_3_oe() {
        assertEquals(1, StringUtils.length(CharBuffer.wrap(" ")));
    }

    @Test
    public void testLength_CharBuffer_4_oe() {
        assertEquals(8, StringUtils.length(CharBuffer.wrap("ABCDEFGH")));
    }

    @Test
    public void testLengthString_1_oe() {
        assertEquals(0, StringUtils.length(null));
    }

    @Test
    public void testLengthString_2_oe() {
        assertEquals(0, StringUtils.length(""));
    }

    @Test
    public void testLengthString_3_oe() {
        assertEquals(0, StringUtils.length(StringUtils.EMPTY));
    }

    @Test
    public void testLengthString_4_oe() {
        assertEquals(1, StringUtils.length("A"));
    }

    @Test
    public void testLengthString_5_oe() {
        assertEquals(1, StringUtils.length(" "));
    }

    @Test
    public void testLengthString_6_oe() {
        assertEquals(8, StringUtils.length("ABCDEFGH"));
    }

    @Test
    public void testLengthStringBuffer_1_oe() {
        assertEquals(0, StringUtils.length(new StringBuffer("")));
    }

    @Test
    public void testLengthStringBuffer_2_oe() {
        assertEquals(0, StringUtils.length(new StringBuffer(StringUtils.EMPTY)));
    }

    @Test
    public void testLengthStringBuffer_3_oe() {
        assertEquals(1, StringUtils.length(new StringBuffer("A")));
    }

    @Test
    public void testLengthStringBuffer_4_oe() {
        assertEquals(1, StringUtils.length(new StringBuffer(" ")));
    }

    @Test
    public void testLengthStringBuffer_5_oe() {
        assertEquals(8, StringUtils.length(new StringBuffer("ABCDEFGH")));
    }

    @Test
    public void testLengthStringBuilder_1_oe() {
        assertEquals(0, StringUtils.length(new StringBuilder("")));
    }

    @Test
    public void testLengthStringBuilder_2_oe() {
        assertEquals(0, StringUtils.length(new StringBuilder(StringUtils.EMPTY)));
    }

    @Test
    public void testLengthStringBuilder_3_oe() {
        assertEquals(1, StringUtils.length(new StringBuilder("A")));
    }

    @Test
    public void testLengthStringBuilder_4_oe() {
        assertEquals(1, StringUtils.length(new StringBuilder(" ")));
    }

    @Test
    public void testLengthStringBuilder_5_oe() {
        assertEquals(8, StringUtils.length(new StringBuilder("ABCDEFGH")));
    }

    @Test
    public void testLowerCase_1_oe() {
        assertNull(StringUtils.lowerCase(null));
    }

    @Test
    public void testLowerCase_2_oe() {
        assertNull(StringUtils.lowerCase(null, Locale.ENGLISH));
    }

    @Test
    public void testLowerCase_3_oe() {
        assertEquals("foo test thing", StringUtils.lowerCase("fOo test THING"), "lowerCase(String) failed");
    }

    @Test
    public void testLowerCase_4_oe() {
        assertEquals("", StringUtils.lowerCase(""), "lowerCase(empty-string) failed");
    }

    @Test
    public void testLowerCase_5_oe() {
        assertEquals("foo test thing",StringUtils.lowerCase("fOo test THING",Locale.ENGLISH),"lowerCase(String,Locale)failed");
    }

    @Test
    public void testLowerCase_6_oe() {
        assertEquals("", StringUtils.lowerCase("", Locale.ENGLISH), "lowerCase(empty-string, Locale) failed");
    }

    @Test
    public void testNormalizeSpace_1_oe() {
        assertFalse(Character.isWhitespace('\u00A0'));
    }

    @Test
    public void testNormalizeSpace_2_oe() {
        assertNull(StringUtils.normalizeSpace(null));
    }

    @Test
    public void testNormalizeSpace_3_oe() {
        assertEquals("", StringUtils.normalizeSpace(""));
    }

    @Test
    public void testNormalizeSpace_4_oe() {
        assertEquals("", StringUtils.normalizeSpace(" "));
    }

    @Test
    public void testNormalizeSpace_5_oe() {
        assertEquals("", StringUtils.normalizeSpace("\t"));
    }

    @Test
    public void testNormalizeSpace_6_oe() {
        assertEquals("", StringUtils.normalizeSpace("\n"));
    }

    @Test
    public void testNormalizeSpace_7_oe() {
        assertEquals("", StringUtils.normalizeSpace("\u0009"));
    }

    @Test
    public void testNormalizeSpace_8_oe() {
        assertEquals("", StringUtils.normalizeSpace("\u000B"));
    }

    @Test
    public void testNormalizeSpace_9_oe() {
        assertEquals("", StringUtils.normalizeSpace("\u000C"));
    }

    @Test
    public void testNormalizeSpace_10_oe() {
        assertEquals("", StringUtils.normalizeSpace("\u001C"));
    }

    @Test
    public void testNormalizeSpace_11_oe() {
        assertEquals("", StringUtils.normalizeSpace("\u001D"));
    }

    @Test
    public void testNormalizeSpace_12_oe() {
        assertEquals("", StringUtils.normalizeSpace("\u001E"));
    }

    @Test
    public void testNormalizeSpace_13_oe() {
        assertEquals("", StringUtils.normalizeSpace("\u001F"));
    }

    @Test
    public void testNormalizeSpace_14_oe() {
        assertEquals("", StringUtils.normalizeSpace("\f"));
    }

    @Test
    public void testNormalizeSpace_15_oe() {
        assertEquals("", StringUtils.normalizeSpace("\r"));
    }

    @Test
    public void testNormalizeSpace_16_oe() {
        assertEquals("a", StringUtils.normalizeSpace("  a  "));
    }

    @Test
    public void testNormalizeSpace_17_oe() {
        assertEquals("a b c", StringUtils.normalizeSpace("  a  b   c  "));
    }

    @Test
    public void testNormalizeSpace_18_oe() {
        assertEquals("a b c", StringUtils.normalizeSpace("a\t\f\r  b\u000B   c\n"));
    }

    @Test
    public void testNormalizeSpace_19_oe() {
        assertEquals("a   b c", StringUtils.normalizeSpace("a\t\f\r  " + HARD_SPACE + HARD_SPACE + "b\u000B   c\n"));
    }

    @Test
    public void testNormalizeSpace_20_oe() {
        assertEquals("b", StringUtils.normalizeSpace("\u0000b"));
    }

    @Test
    public void testNormalizeSpace_21_oe() {
        assertEquals("b", StringUtils.normalizeSpace("b\u0000"));
    }

    @Test
    public void testOverlay_StringStringIntInt_1_oe() {
        assertNull(StringUtils.overlay(null, null, 2, 4));
    }

    @Test
    public void testOverlay_StringStringIntInt_2_oe() {
        assertNull(StringUtils.overlay(null, null, -2, -4));
    }

    @Test
    public void testOverlay_StringStringIntInt_3_oe() {

        assertEquals("", StringUtils.overlay("", null, 0, 0));
    }

    @Test
    public void testOverlay_StringStringIntInt_4_oe() {

        assertEquals("", StringUtils.overlay("", "", 0, 0));
    }

    @Test
    public void testOverlay_StringStringIntInt_5_oe() {

        assertEquals("zzzz", StringUtils.overlay("", "zzzz", 0, 0));
    }

    @Test
    public void testOverlay_StringStringIntInt_6_oe() {

        assertEquals("zzzz", StringUtils.overlay("", "zzzz", 2, 4));
    }

    @Test
    public void testOverlay_StringStringIntInt_7_oe() {

        assertEquals("zzzz", StringUtils.overlay("", "zzzz", -2, -4));
    }

    @Test
    public void testOverlay_StringStringIntInt_8_oe() {


        assertEquals("abef", StringUtils.overlay("abcdef", null, 2, 4));
    }

    @Test
    public void testOverlay_StringStringIntInt_9_oe() {


        assertEquals("abef", StringUtils.overlay("abcdef", null, 4, 2));
    }

    @Test
    public void testOverlay_StringStringIntInt_10_oe() {


        assertEquals("abef", StringUtils.overlay("abcdef", "", 2, 4));
    }

    @Test
    public void testOverlay_StringStringIntInt_11_oe() {


        assertEquals("abef", StringUtils.overlay("abcdef", "", 4, 2));
    }

    @Test
    public void testOverlay_StringStringIntInt_12_oe() {


        assertEquals("abzzzzef", StringUtils.overlay("abcdef", "zzzz", 2, 4));
    }

    @Test
    public void testOverlay_StringStringIntInt_13_oe() {


        assertEquals("abzzzzef", StringUtils.overlay("abcdef", "zzzz", 4, 2));
    }

    @Test
    public void testOverlay_StringStringIntInt_14_oe() {



        assertEquals("zzzzef", StringUtils.overlay("abcdef", "zzzz", -1, 4));
    }

    @Test
    public void testOverlay_StringStringIntInt_15_oe() {



        assertEquals("zzzzef", StringUtils.overlay("abcdef", "zzzz", 4, -1));
    }

    @Test
    public void testOverlay_StringStringIntInt_16_oe() {



        assertEquals("zzzzabcdef", StringUtils.overlay("abcdef", "zzzz", -2, -1));
    }

    @Test
    public void testOverlay_StringStringIntInt_17_oe() {



        assertEquals("zzzzabcdef", StringUtils.overlay("abcdef", "zzzz", -1, -2));
    }

    @Test
    public void testOverlay_StringStringIntInt_18_oe() {



        assertEquals("abcdzzzz", StringUtils.overlay("abcdef", "zzzz", 4, 10));
    }

    @Test
    public void testOverlay_StringStringIntInt_19_oe() {



        assertEquals("abcdzzzz", StringUtils.overlay("abcdef", "zzzz", 10, 4));
    }

    @Test
    public void testOverlay_StringStringIntInt_20_oe() {



        assertEquals("abcdefzzzz", StringUtils.overlay("abcdef", "zzzz", 8, 10));
    }

    @Test
    public void testOverlay_StringStringIntInt_21_oe() {



        assertEquals("abcdefzzzz", StringUtils.overlay("abcdef", "zzzz", 10, 8));
    }

    @Test
    public void testPrependIfMissing_1_oe() {
        assertNull(StringUtils.prependIfMissing(null, null), "prependIfMissing(null,null)");
    }

    @Test
    public void testPrependIfMissing_2_oe() {
        assertEquals("abc", StringUtils.prependIfMissing("abc", null), "prependIfMissing(abc,null)");
    }

    @Test
    public void testPrependIfMissing_3_oe() {
        assertEquals("xyz", StringUtils.prependIfMissing("", "xyz"), "prependIfMissing(\"\",xyz)");
    }

    @Test
    public void testPrependIfMissing_4_oe() {
        assertEquals("xyzabc", StringUtils.prependIfMissing("abc", "xyz"), "prependIfMissing(abc,xyz)");
    }

    @Test
    public void testPrependIfMissing_5_oe() {
        assertEquals("xyzabc", StringUtils.prependIfMissing("xyzabc", "xyz"), "prependIfMissing(xyzabc,xyz)");
    }

    @Test
    public void testPrependIfMissing_6_oe() {
        assertEquals("xyzXYZabc", StringUtils.prependIfMissing("XYZabc", "xyz"), "prependIfMissing(XYZabc,xyz)");
    }

    @Test
    public void testPrependIfMissing_7_oe() {

        assertNull(StringUtils.prependIfMissing(null, null, (CharSequence[]) null), "prependIfMissing(null,null null)");
    }

    @Test
    public void testPrependIfMissing_8_oe() {

        assertEquals("abc", StringUtils.prependIfMissing("abc", null, (CharSequence[]) null), "prependIfMissing(abc,null,null)");
    }

    @Test
    public void testPrependIfMissing_9_oe() {

        assertEquals("xyz", StringUtils.prependIfMissing("", "xyz", (CharSequence[]) null), "prependIfMissing(\"\",xyz,null)");
    }

    @Test
    public void testPrependIfMissing_10_oe() {

        assertEquals("xyzabc", StringUtils.prependIfMissing("abc", "xyz", null), "prependIfMissing(abc,xyz,{null})");
    }

    @Test
    public void testPrependIfMissing_11_oe() {

        assertEquals("abc", StringUtils.prependIfMissing("abc", "xyz", ""), "prependIfMissing(abc,xyz,\"\")");
    }

    @Test
    public void testPrependIfMissing_12_oe() {

        assertEquals("xyzabc", StringUtils.prependIfMissing("abc", "xyz", "mno"), "prependIfMissing(abc,xyz,mno)");
    }

    @Test
    public void testPrependIfMissing_13_oe() {

        assertEquals("xyzabc", StringUtils.prependIfMissing("xyzabc", "xyz", "mno"), "prependIfMissing(xyzabc,xyz,mno)");
    }

    @Test
    public void testPrependIfMissing_14_oe() {

        assertEquals("mnoabc", StringUtils.prependIfMissing("mnoabc", "xyz", "mno"), "prependIfMissing(mnoabc,xyz,mno)");
    }

    @Test
    public void testPrependIfMissing_15_oe() {

        assertEquals("xyzXYZabc", StringUtils.prependIfMissing("XYZabc", "xyz", "mno"), "prependIfMissing(XYZabc,xyz,mno)");
    }

    @Test
    public void testPrependIfMissing_16_oe() {

        assertEquals("xyzMNOabc", StringUtils.prependIfMissing("MNOabc", "xyz", "mno"), "prependIfMissing(MNOabc,xyz,mno)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_1_oe() {
        assertNull(StringUtils.prependIfMissingIgnoreCase(null, null), "prependIfMissingIgnoreCase(null,null)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_2_oe() {
        assertEquals("abc", StringUtils.prependIfMissingIgnoreCase("abc", null), "prependIfMissingIgnoreCase(abc,null)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_3_oe() {
        assertEquals("xyz", StringUtils.prependIfMissingIgnoreCase("", "xyz"), "prependIfMissingIgnoreCase(\"\",xyz)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_4_oe() {
        assertEquals("xyzabc", StringUtils.prependIfMissingIgnoreCase("abc", "xyz"), "prependIfMissingIgnoreCase(abc,xyz)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_5_oe() {
        assertEquals("xyzabc", StringUtils.prependIfMissingIgnoreCase("xyzabc", "xyz"), "prependIfMissingIgnoreCase(xyzabc,xyz)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_6_oe() {
        assertEquals("XYZabc", StringUtils.prependIfMissingIgnoreCase("XYZabc", "xyz"), "prependIfMissingIgnoreCase(XYZabc,xyz)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_7_oe() {

        assertNull(StringUtils.prependIfMissingIgnoreCase(null, null, (CharSequence[]) null), "prependIfMissingIgnoreCase(null,null null)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_8_oe() {

        assertEquals("abc", StringUtils.prependIfMissingIgnoreCase("abc", null, (CharSequence[]) null), "prependIfMissingIgnoreCase(abc,null,null)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_9_oe() {

        assertEquals("xyz", StringUtils.prependIfMissingIgnoreCase("", "xyz", (CharSequence[]) null), "prependIfMissingIgnoreCase(\"\",xyz,null)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_10_oe() {

        assertEquals("xyzabc", StringUtils.prependIfMissingIgnoreCase("abc", "xyz", null), "prependIfMissingIgnoreCase(abc,xyz,{null})");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_11_oe() {

        assertEquals("abc", StringUtils.prependIfMissingIgnoreCase("abc", "xyz", ""), "prependIfMissingIgnoreCase(abc,xyz,\"\")");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_12_oe() {

        assertEquals("xyzabc", StringUtils.prependIfMissingIgnoreCase("abc", "xyz", "mno"), "prependIfMissingIgnoreCase(abc,xyz,mno)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_13_oe() {

        assertEquals("xyzabc", StringUtils.prependIfMissingIgnoreCase("xyzabc", "xyz", "mno"), "prependIfMissingIgnoreCase(xyzabc,xyz,mno)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_14_oe() {

        assertEquals("mnoabc", StringUtils.prependIfMissingIgnoreCase("mnoabc", "xyz", "mno"), "prependIfMissingIgnoreCase(mnoabc,xyz,mno)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_15_oe() {

        assertEquals("XYZabc", StringUtils.prependIfMissingIgnoreCase("XYZabc", "xyz", "mno"), "prependIfMissingIgnoreCase(XYZabc,xyz,mno)");
    }

    @Test
    public void testPrependIfMissingIgnoreCase_16_oe() {

        assertEquals("MNOabc", StringUtils.prependIfMissingIgnoreCase("MNOabc", "xyz", "mno"), "prependIfMissingIgnoreCase(MNOabc,xyz,mno)");
    }

    @Test
    public void testReCapitalize_1_oe() {
        assertEquals(SENTENCE_UNCAP,StringUtils.uncapitalize(StringUtils.capitalize(SENTENCE_UNCAP)),"uncapitalize(capitalize(String))failed");
    }

    @Test
    public void testReCapitalize_2_oe() {
        assertEquals(SENTENCE_CAP,StringUtils.capitalize(StringUtils.uncapitalize(SENTENCE_CAP)),"capitalize(uncapitalize(String))failed");
    }

    @Test
    public void testReCapitalize_3_oe() {

        assertEquals(FOO_UNCAP,StringUtils.uncapitalize(StringUtils.capitalize(FOO_UNCAP)),"uncapitalize(capitalize(String))failed");
    }

    @Test
    public void testReCapitalize_4_oe() {

        assertEquals(FOO_CAP,StringUtils.capitalize(StringUtils.uncapitalize(FOO_CAP)),"capitalize(uncapitalize(String))failed");
    }

    @Test
    public void testRemove_char_1_oe() {
        assertNull(StringUtils.remove(null, 'a'));
    }

    @Test
    public void testRemove_char_2_oe() {
        assertNull(StringUtils.remove(null, 'a'));
    }

    @Test
    public void testRemove_char_3_oe() {
        assertNull(StringUtils.remove(null, 'a'));
    }

    @Test
    public void testRemove_char_4_oe() {

        assertEquals("", StringUtils.remove("", 'a'));
    }

    @Test
    public void testRemove_char_5_oe() {

        assertEquals("", StringUtils.remove("", 'a'));
    }

    @Test
    public void testRemove_char_6_oe() {

        assertEquals("", StringUtils.remove("", 'a'));
    }

    @Test
    public void testRemove_char_7_oe() {


        assertEquals("qeed", StringUtils.remove("queued", 'u'));
    }

    @Test
    public void testRemove_char_8_oe() {



        assertEquals("queued", StringUtils.remove("queued", 'z'));
    }

    @Test
    public void testRemove_String_1_oe() {
        assertNull(StringUtils.remove(null, null));
    }

    @Test
    public void testRemove_String_2_oe() {
        assertNull(StringUtils.remove(null, ""));
    }

    @Test
    public void testRemove_String_3_oe() {
        assertNull(StringUtils.remove(null, "a"));
    }

    @Test
    public void testRemove_String_4_oe() {

        assertEquals("", StringUtils.remove("", null));
    }

    @Test
    public void testRemove_String_5_oe() {

        assertEquals("", StringUtils.remove("", ""));
    }

    @Test
    public void testRemove_String_6_oe() {

        assertEquals("", StringUtils.remove("", "a"));
    }

    @Test
    public void testRemove_String_7_oe() {


        assertNull(StringUtils.remove(null, null));
    }

    @Test
    public void testRemove_String_8_oe() {


        assertEquals("", StringUtils.remove("", null));
    }

    @Test
    public void testRemove_String_9_oe() {


        assertEquals("a", StringUtils.remove("a", null));
    }

    @Test
    public void testRemove_String_10_oe() {



        assertNull(StringUtils.remove(null, ""));
    }

    @Test
    public void testRemove_String_11_oe() {



        assertEquals("", StringUtils.remove("", ""));
    }

    @Test
    public void testRemove_String_12_oe() {



        assertEquals("a", StringUtils.remove("a", ""));
    }

    @Test
    public void testRemove_String_13_oe() {




        assertEquals("qd", StringUtils.remove("queued", "ue"));
    }

    @Test
    public void testRemove_String_14_oe() {





        assertEquals("queued", StringUtils.remove("queued", "zz"));
    }

    @Test
    public void testRemoveAll_StringString_1_oe() {
        assertNull(StringUtils.removeAll(null, ""));
    }

    @Test
    public void testRemoveAll_StringString_2_oe() {
        assertEquals("any", StringUtils.removeAll("any", null));
    }

    @Test
    public void testRemoveAll_StringString_3_oe() {

        assertEquals("any", StringUtils.removeAll("any", ""));
    }

    @Test
    public void testRemoveAll_StringString_4_oe() {

        assertEquals("", StringUtils.removeAll("any", ".*"));
    }

    @Test
    public void testRemoveAll_StringString_5_oe() {

        assertEquals("", StringUtils.removeAll("any", ".+"));
    }

    @Test
    public void testRemoveAll_StringString_6_oe() {

        assertEquals("", StringUtils.removeAll("any", ".?"));
    }

    @Test
    public void testRemoveAll_StringString_7_oe() {


        assertEquals("A\nB", StringUtils.removeAll("A<__>\n<__>B", "<.*>"));
    }

    @Test
    public void testRemoveAll_StringString_8_oe() {


        assertEquals("AB", StringUtils.removeAll("A<__>\n<__>B", "(?s)<.*>"));
    }

    @Test
    public void testRemoveAll_StringString_9_oe() {


        assertEquals("ABC123", StringUtils.removeAll("ABCabc123abc", "[a-z]"));
    }

    @Test
    public void testRemoveAll_StringString_10_oe() throws Exception {



        try {
    StringUtils.removeAll("any", "{badRegexSyntax}");
    fail("PatternSyntaxException: StringUtils.removeAll expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testRemoveEnd_1_oe() {
        assertNull(StringUtils.removeEnd(null, null));
    }

    @Test
    public void testRemoveEnd_2_oe() {
        assertNull(StringUtils.removeEnd(null, ""));
    }

    @Test
    public void testRemoveEnd_3_oe() {
        assertNull(StringUtils.removeEnd(null, "a"));
    }

    @Test
    public void testRemoveEnd_4_oe() {

        assertEquals(StringUtils.removeEnd("", null), "");
    }

    @Test
    public void testRemoveEnd_5_oe() {

        assertEquals(StringUtils.removeEnd("", ""), "");
    }

    @Test
    public void testRemoveEnd_6_oe() {

        assertEquals(StringUtils.removeEnd("", "a"), "");
    }

    @Test
    public void testRemoveEnd_7_oe() {


        assertEquals(StringUtils.removeEnd("www.domain.com.", ".com"), "www.domain.com.");
    }

    @Test
    public void testRemoveEnd_8_oe() {


        assertEquals(StringUtils.removeEnd("www.domain.com", ".com"), "www.domain");
    }

    @Test
    public void testRemoveEnd_9_oe() {


        assertEquals(StringUtils.removeEnd("www.domain", ".com"), "www.domain");
    }

    @Test
    public void testRemoveEnd_10_oe() {


        assertEquals(StringUtils.removeEnd("domain.com", ""), "domain.com");
    }

    @Test
    public void testRemoveEnd_11_oe() {


        assertEquals(StringUtils.removeEnd("domain.com", null), "domain.com");
    }

    @Test
    public void testRemoveEndIgnoreCase_1_oe() {
        assertNull(StringUtils.removeEndIgnoreCase(null, null), "removeEndIgnoreCase(null, null)");
    }

    @Test
    public void testRemoveEndIgnoreCase_2_oe() {
        assertNull(StringUtils.removeEndIgnoreCase(null, ""), "removeEndIgnoreCase(null, \"\")");
    }

    @Test
    public void testRemoveEndIgnoreCase_3_oe() {
        assertNull(StringUtils.removeEndIgnoreCase(null, "a"), "removeEndIgnoreCase(null, \"a\")");
    }

    @Test
    public void testRemoveEndIgnoreCase_4_oe() {

        assertEquals(StringUtils.removeEndIgnoreCase("", null), "", "removeEndIgnoreCase(\"\", null)");
    }

    @Test
    public void testRemoveEndIgnoreCase_5_oe() {

        assertEquals(StringUtils.removeEndIgnoreCase("", ""), "", "removeEndIgnoreCase(\"\", \"\")");
    }

    @Test
    public void testRemoveEndIgnoreCase_6_oe() {

        assertEquals(StringUtils.removeEndIgnoreCase("", "a"), "", "removeEndIgnoreCase(\"\", \"a\")");
    }

    @Test
    public void testRemoveEndIgnoreCase_7_oe() {


        assertEquals(StringUtils.removeEndIgnoreCase("www.domain.com.", ".com"), "www.domain.com.", "removeEndIgnoreCase(\"www.domain.com.\", \".com\")");
    }

    @Test
    public void testRemoveEndIgnoreCase_8_oe() {


        assertEquals(StringUtils.removeEndIgnoreCase("www.domain.com", ".com"), "www.domain", "removeEndIgnoreCase(\"www.domain.com\", \".com\")");
    }

    @Test
    public void testRemoveEndIgnoreCase_9_oe() {


        assertEquals(StringUtils.removeEndIgnoreCase("www.domain", ".com"), "www.domain", "removeEndIgnoreCase(\"www.domain\", \".com\")");
    }

    @Test
    public void testRemoveEndIgnoreCase_10_oe() {


        assertEquals(StringUtils.removeEndIgnoreCase("domain.com", ""), "domain.com", "removeEndIgnoreCase(\"domain.com\", \"\")");
    }

    @Test
    public void testRemoveEndIgnoreCase_11_oe() {


        assertEquals(StringUtils.removeEndIgnoreCase("domain.com", null), "domain.com", "removeEndIgnoreCase(\"domain.com\", null)");
    }

    @Test
    public void testRemoveEndIgnoreCase_12_oe() {



        assertEquals(StringUtils.removeEndIgnoreCase("www.domain.com", ".COM"), "www.domain", "removeEndIgnoreCase(\"www.domain.com\", \".COM\")");
    }

    @Test
    public void testRemoveEndIgnoreCase_13_oe() {



        assertEquals(StringUtils.removeEndIgnoreCase("www.domain.COM", ".com"), "www.domain", "removeEndIgnoreCase(\"www.domain.COM\", \".com\")");
    }

    @Test
    public void testRemoveFirst_StringString_1_oe() {
        assertNull(StringUtils.removeFirst(null, ""));
    }

    @Test
    public void testRemoveFirst_StringString_2_oe() {
        assertEquals("any", StringUtils.removeFirst("any", null));
    }

    @Test
    public void testRemoveFirst_StringString_3_oe() {

        assertEquals("any", StringUtils.removeFirst("any", ""));
    }

    @Test
    public void testRemoveFirst_StringString_4_oe() {

        assertEquals("", StringUtils.removeFirst("any", ".*"));
    }

    @Test
    public void testRemoveFirst_StringString_5_oe() {

        assertEquals("", StringUtils.removeFirst("any", ".+"));
    }

    @Test
    public void testRemoveFirst_StringString_6_oe() {

        assertEquals("bc", StringUtils.removeFirst("abc", ".?"));
    }

    @Test
    public void testRemoveFirst_StringString_7_oe() {


        assertEquals("A\n<__>B", StringUtils.removeFirst("A<__>\n<__>B", "<.*>"));
    }

    @Test
    public void testRemoveFirst_StringString_8_oe() {


        assertEquals("AB", StringUtils.removeFirst("A<__>\n<__>B", "(?s)<.*>"));
    }

    @Test
    public void testRemoveFirst_StringString_9_oe() {


        assertEquals("ABCbc123", StringUtils.removeFirst("ABCabc123", "[a-z]"));
    }

    @Test
    public void testRemoveFirst_StringString_10_oe() {


        assertEquals("ABC123abc", StringUtils.removeFirst("ABCabc123abc", "[a-z]+"));
    }

    @Test
    public void testRemoveFirst_StringString_11_oe() throws Exception {



        try {
    StringUtils.removeFirst("any", "{badRegexSyntax}");
    fail("PatternSyntaxException: StringUtils.removeFirst expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testRemoveIgnoreCase_String_1_oe() {
        assertNull(StringUtils.removeIgnoreCase(null, null));
    }

    @Test
    public void testRemoveIgnoreCase_String_2_oe() {
        assertNull(StringUtils.removeIgnoreCase(null, ""));
    }

    @Test
    public void testRemoveIgnoreCase_String_3_oe() {
        assertNull(StringUtils.removeIgnoreCase(null, "a"));
    }

    @Test
    public void testRemoveIgnoreCase_String_4_oe() {

        assertEquals("", StringUtils.removeIgnoreCase("", null));
    }

    @Test
    public void testRemoveIgnoreCase_String_5_oe() {

        assertEquals("", StringUtils.removeIgnoreCase("", ""));
    }

    @Test
    public void testRemoveIgnoreCase_String_6_oe() {

        assertEquals("", StringUtils.removeIgnoreCase("", "a"));
    }

    @Test
    public void testRemoveIgnoreCase_String_7_oe() {


        assertNull(StringUtils.removeIgnoreCase(null, null));
    }

    @Test
    public void testRemoveIgnoreCase_String_8_oe() {


        assertEquals("", StringUtils.removeIgnoreCase("", null));
    }

    @Test
    public void testRemoveIgnoreCase_String_9_oe() {


        assertEquals("a", StringUtils.removeIgnoreCase("a", null));
    }

    @Test
    public void testRemoveIgnoreCase_String_10_oe() {



        assertNull(StringUtils.removeIgnoreCase(null, ""));
    }

    @Test
    public void testRemoveIgnoreCase_String_11_oe() {



        assertEquals("", StringUtils.removeIgnoreCase("", ""));
    }

    @Test
    public void testRemoveIgnoreCase_String_12_oe() {



        assertEquals("a", StringUtils.removeIgnoreCase("a", ""));
    }

    @Test
    public void testRemoveIgnoreCase_String_13_oe() {




        assertEquals("qd", StringUtils.removeIgnoreCase("queued", "ue"));
    }

    @Test
    public void testRemoveIgnoreCase_String_14_oe() {





        assertEquals("queued", StringUtils.removeIgnoreCase("queued", "zz"));
    }

    @Test
    public void testRemoveIgnoreCase_String_15_oe() {






        assertEquals("qd", StringUtils.removeIgnoreCase("quEUed", "UE"));
    }

    @Test
    public void testRemoveIgnoreCase_String_16_oe() {







        assertEquals("queued", StringUtils.removeIgnoreCase("queued", "zZ"));
    }

    @Test
    public void testRemoveIgnoreCase_String_17_oe() {








        assertEquals("\u0130", StringUtils.removeIgnoreCase("\u0130x", "x"));
    }

    @Test
    public void testRemovePattern_StringString_1_oe() {
        assertNull(StringUtils.removePattern(null, ""));
    }

    @Test
    public void testRemovePattern_StringString_2_oe() {
        assertEquals("any", StringUtils.removePattern("any", null));
    }

    @Test
    public void testRemovePattern_StringString_3_oe() {

        assertEquals("", StringUtils.removePattern("", ""));
    }

    @Test
    public void testRemovePattern_StringString_4_oe() {

        assertEquals("", StringUtils.removePattern("", ".*"));
    }

    @Test
    public void testRemovePattern_StringString_5_oe() {

        assertEquals("", StringUtils.removePattern("", ".+"));
    }

    @Test
    public void testRemovePattern_StringString_6_oe() {


        assertEquals("AB", StringUtils.removePattern("A<__>\n<__>B", "<.*>"));
    }

    @Test
    public void testRemovePattern_StringString_7_oe() {


        assertEquals("AB", StringUtils.removePattern("A<__>\\n<__>B", "<.*>"));
    }

    @Test
    public void testRemovePattern_StringString_8_oe() {


        assertEquals("", StringUtils.removePattern("<A>x\\ny</A>", "<A>.*</A>"));
    }

    @Test
    public void testRemovePattern_StringString_9_oe() {


        assertEquals("", StringUtils.removePattern("<A>\nxy\n</A>", "<A>.*</A>"));
    }

    @Test
    public void testRemovePattern_StringString_10_oe() {



        assertEquals("ABC123", StringUtils.removePattern("ABCabc123", "[a-z]"));
    }

    @Test
    public void testRemoveStart_1_oe() {
        assertNull(StringUtils.removeStart(null, null));
    }

    @Test
    public void testRemoveStart_2_oe() {
        assertNull(StringUtils.removeStart(null, ""));
    }

    @Test
    public void testRemoveStart_3_oe() {
        assertNull(StringUtils.removeStart(null, "a"));
    }

    @Test
    public void testRemoveStart_4_oe() {

        assertEquals(StringUtils.removeStart("", null), "");
    }

    @Test
    public void testRemoveStart_5_oe() {

        assertEquals(StringUtils.removeStart("", ""), "");
    }

    @Test
    public void testRemoveStart_6_oe() {

        assertEquals(StringUtils.removeStart("", "a"), "");
    }

    @Test
    public void testRemoveStart_7_oe() {


        assertEquals(StringUtils.removeStart("www.domain.com", "www."), "domain.com");
    }

    @Test
    public void testRemoveStart_8_oe() {


        assertEquals(StringUtils.removeStart("domain.com", "www."), "domain.com");
    }

    @Test
    public void testRemoveStart_9_oe() {


        assertEquals(StringUtils.removeStart("domain.com", ""), "domain.com");
    }

    @Test
    public void testRemoveStart_10_oe() {


        assertEquals(StringUtils.removeStart("domain.com", null), "domain.com");
    }

    @Test
    public void testRemoveStartIgnoreCase_1_oe() {
        assertNull(StringUtils.removeStartIgnoreCase(null, null), "removeStartIgnoreCase(null, null)");
    }

    @Test
    public void testRemoveStartIgnoreCase_2_oe() {
        assertNull(StringUtils.removeStartIgnoreCase(null, ""), "removeStartIgnoreCase(null, \"\")");
    }

    @Test
    public void testRemoveStartIgnoreCase_3_oe() {
        assertNull(StringUtils.removeStartIgnoreCase(null, "a"), "removeStartIgnoreCase(null, \"a\")");
    }

    @Test
    public void testRemoveStartIgnoreCase_4_oe() {

        assertEquals(StringUtils.removeStartIgnoreCase("", null), "", "removeStartIgnoreCase(\"\", null)");
    }

    @Test
    public void testRemoveStartIgnoreCase_5_oe() {

        assertEquals(StringUtils.removeStartIgnoreCase("", ""), "", "removeStartIgnoreCase(\"\", \"\")");
    }

    @Test
    public void testRemoveStartIgnoreCase_6_oe() {

        assertEquals(StringUtils.removeStartIgnoreCase("", "a"), "", "removeStartIgnoreCase(\"\", \"a\")");
    }

    @Test
    public void testRemoveStartIgnoreCase_7_oe() {


        assertEquals(StringUtils.removeStartIgnoreCase("www.domain.com", "www."), "domain.com", "removeStartIgnoreCase(\"www.domain.com\", \"www.\")");
    }

    @Test
    public void testRemoveStartIgnoreCase_8_oe() {


        assertEquals(StringUtils.removeStartIgnoreCase("domain.com", "www."), "domain.com", "removeStartIgnoreCase(\"domain.com\", \"www.\")");
    }

    @Test
    public void testRemoveStartIgnoreCase_9_oe() {


        assertEquals(StringUtils.removeStartIgnoreCase("domain.com", ""), "domain.com", "removeStartIgnoreCase(\"domain.com\", \"\")");
    }

    @Test
    public void testRemoveStartIgnoreCase_10_oe() {


        assertEquals(StringUtils.removeStartIgnoreCase("domain.com", null), "domain.com", "removeStartIgnoreCase(\"domain.com\", null)");
    }

    @Test
    public void testRemoveStartIgnoreCase_11_oe() {



        assertEquals(StringUtils.removeStartIgnoreCase("www.domain.com", "WWW."), "domain.com", "removeStartIgnoreCase(\"www.domain.com\", \"WWW.\")");
    }

    @Test
    public void testRepeat_CharInt_1_oe() {
        assertEquals("zzz", StringUtils.repeat('z', 3));
    }

    @Test
    public void testRepeat_CharInt_2_oe() {
        assertEquals("", StringUtils.repeat('z', 0));
    }

    @Test
    public void testRepeat_CharInt_3_oe() {
        assertEquals("", StringUtils.repeat('z', -2));
    }

    @Test
    public void testRepeat_StringInt_1_oe() {
        assertNull(StringUtils.repeat(null, 2));
    }

    @Test
    public void testRepeat_StringInt_2_oe() {
        assertEquals("", StringUtils.repeat("ab", 0));
    }

    @Test
    public void testRepeat_StringInt_3_oe() {
        assertEquals("", StringUtils.repeat("", 3));
    }

    @Test
    public void testRepeat_StringInt_4_oe() {
        assertEquals("aaa", StringUtils.repeat("a", 3));
    }

    @Test
    public void testRepeat_StringInt_5_oe() {
        assertEquals("", StringUtils.repeat("a", -2));
    }

    @Test
    public void testRepeat_StringInt_6_oe() {
        assertEquals("ababab", StringUtils.repeat("ab", 3));
    }

    @Test
    public void testRepeat_StringInt_7_oe() {
        assertEquals("abcabcabc", StringUtils.repeat("abc", 3));
    }

    @Test
    public void testRepeat_StringInt_8_oe() {
        final String str = StringUtils.repeat("a", 10000);  // bigger than pad limit
        assertEquals(10000, str.length());
    }

    @Test
    public void testRepeat_StringInt_9_oe() {
        final String str = StringUtils.repeat("a", 10000);  // bigger than pad limit
        assertTrue(StringUtils.containsOnly(str, 'a'));
    }

    @Test
    public void testRepeat_StringStringInt_1_oe() {
        assertNull(StringUtils.repeat(null, null, 2));
    }

    @Test
    public void testRepeat_StringStringInt_2_oe() {
        assertNull(StringUtils.repeat(null, "x", 2));
    }

    @Test
    public void testRepeat_StringStringInt_3_oe() {
        assertEquals("", StringUtils.repeat("", null, 2));
    }

    @Test
    public void testRepeat_StringStringInt_4_oe() {

        assertEquals("", StringUtils.repeat("ab", "", 0));
    }

    @Test
    public void testRepeat_StringStringInt_5_oe() {

        assertEquals("", StringUtils.repeat("", "", 2));
    }

    @Test
    public void testRepeat_StringStringInt_6_oe() {


        assertEquals("xx", StringUtils.repeat("", "x", 3));
    }

    @Test
    public void testRepeat_StringStringInt_7_oe() {



        assertEquals("?, ?, ?", StringUtils.repeat("?", ", ", 3));
    }

    @Test
    public void testReplace_StringStringArrayStringArray_1_oe() {
        assertNull(StringUtils.replaceEach(null, new String[]{"a"}, new String[]{"b"}));
    }

    @Test
    public void testReplace_StringStringArrayStringArray_2_oe() {
        assertEquals(StringUtils.replaceEach("", new String[]{"a"}, new String[]{"b"}), "");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_3_oe() {
        assertEquals(StringUtils.replaceEach("aba", null, null), "aba");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_4_oe() {
        assertEquals(StringUtils.replaceEach("aba", new String[0], null), "aba");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_5_oe() {
        assertEquals(StringUtils.replaceEach("aba", null, new String[0]), "aba");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_6_oe() {
        assertEquals(StringUtils.replaceEach("aba", new String[]{"a"}, null), "aba");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_7_oe() {

        assertEquals(StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""}), "b");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_8_oe() {

        assertEquals(StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"}), "aba");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_9_oe() {

        assertEquals(StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}), "wcte");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_10_oe() {

        assertEquals(StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}), "dcte");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_11_oe() {


        assertEquals("bcc", StringUtils.replaceEach("abc", new String[]{"a", "b"}, new String[]{"b", "c"}));
    }

    @Test
    public void testReplace_StringStringArrayStringArray_12_oe() {


        assertEquals("q651.506bera", StringUtils.replaceEach("d216.102oren", new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9"}, new String[]{"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "5", "6", "7", "8", "9", "1", "2", "3", "4"}));
    }

    @Test
    public void testReplace_StringStringArrayStringArray_13_oe() {



        assertEquals(StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{null}), "aba");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_14_oe() {



        assertEquals(StringUtils.replaceEach("aba", new String[]{"a", "b"}, new String[]{"c", null}), "cbc");
    }

    @Test
    public void testReplace_StringStringArrayStringArray_15_oe() throws Exception {




        try {
    StringUtils.replaceEach("abba", new String[]{"a"}, new String[]{"b", "a"});
    fail("IllegalArgumentException: StringUtils.replaceEach(String, String[], String[]) expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_1_oe() {
        assertNull(StringUtils.replaceEachRepeatedly(null, new String[]{"a"}, new String[]{"b"}));
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_2_oe() {
        assertEquals("", StringUtils.replaceEachRepeatedly("", new String[]{"a"}, new String[]{"b"}));
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_3_oe() {
        assertEquals("aba", StringUtils.replaceEachRepeatedly("aba", null, null));
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_4_oe() {
        assertEquals("aba", StringUtils.replaceEachRepeatedly("aba", new String[0], null));
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_5_oe() {
        assertEquals("aba", StringUtils.replaceEachRepeatedly("aba", null, new String[0]));
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_6_oe() {
        assertEquals("aba", StringUtils.replaceEachRepeatedly("aba", new String[0], null));
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_7_oe() {

        assertEquals("b", StringUtils.replaceEachRepeatedly("aba", new String[]{"a"}, new String[]{""}));
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_8_oe() {

        assertEquals("aba", StringUtils.replaceEachRepeatedly("aba", new String[]{null}, new String[]{"a"}));
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_9_oe() {

        assertEquals("wcte", StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}));
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_10_oe() {

        assertEquals("tcte", StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}));
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_11_oe() {

        assertEquals("blaan", StringUtils.replaceEachRepeatedly("blllaan", new String[]{"llaan"}, new String[]{"laan"}) );
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_12_oe() throws Exception {


        try {
    StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"});
    fail("IllegalStateException: Should be a circular reference");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testReplace_StringStringString_1_oe() {
        assertNull(StringUtils.replace(null, null, null));
    }

    @Test
    public void testReplace_StringStringString_2_oe() {
        assertNull(StringUtils.replace(null, null, "any"));
    }

    @Test
    public void testReplace_StringStringString_3_oe() {
        assertNull(StringUtils.replace(null, "any", null));
    }

    @Test
    public void testReplace_StringStringString_4_oe() {
        assertNull(StringUtils.replace(null, "any", "any"));
    }

    @Test
    public void testReplace_StringStringString_5_oe() {

        assertEquals("", StringUtils.replace("", null, null));
    }

    @Test
    public void testReplace_StringStringString_6_oe() {

        assertEquals("", StringUtils.replace("", null, "any"));
    }

    @Test
    public void testReplace_StringStringString_7_oe() {

        assertEquals("", StringUtils.replace("", "any", null));
    }

    @Test
    public void testReplace_StringStringString_8_oe() {

        assertEquals("", StringUtils.replace("", "any", "any"));
    }

    @Test
    public void testReplace_StringStringString_9_oe() {


        assertEquals("FOO", StringUtils.replace("FOO", "", "any"));
    }

    @Test
    public void testReplace_StringStringString_10_oe() {


        assertEquals("FOO", StringUtils.replace("FOO", null, "any"));
    }

    @Test
    public void testReplace_StringStringString_11_oe() {


        assertEquals("FOO", StringUtils.replace("FOO", "F", null));
    }

    @Test
    public void testReplace_StringStringString_12_oe() {


        assertEquals("FOO", StringUtils.replace("FOO", null, null));
    }

    @Test
    public void testReplace_StringStringString_13_oe() {



        assertEquals("", StringUtils.replace("foofoofoo", "foo", ""));
    }

    @Test
    public void testReplace_StringStringString_14_oe() {



        assertEquals("barbarbar", StringUtils.replace("foofoofoo", "foo", "bar"));
    }

    @Test
    public void testReplace_StringStringString_15_oe() {



        assertEquals("farfarfar", StringUtils.replace("foofoofoo", "oo", "ar"));
    }

    @Test
    public void testReplace_StringStringStringInt_1_oe() {
        assertNull(StringUtils.replace(null, null, null, 2));
    }

    @Test
    public void testReplace_StringStringStringInt_2_oe() {
        assertNull(StringUtils.replace(null, null, "any", 2));
    }

    @Test
    public void testReplace_StringStringStringInt_3_oe() {
        assertNull(StringUtils.replace(null, "any", null, 2));
    }

    @Test
    public void testReplace_StringStringStringInt_4_oe() {
        assertNull(StringUtils.replace(null, "any", "any", 2));
    }

    @Test
    public void testReplace_StringStringStringInt_5_oe() {

        assertEquals("", StringUtils.replace("", null, null, 2));
    }

    @Test
    public void testReplace_StringStringStringInt_6_oe() {

        assertEquals("", StringUtils.replace("", null, "any", 2));
    }

    @Test
    public void testReplace_StringStringStringInt_7_oe() {

        assertEquals("", StringUtils.replace("", "any", null, 2));
    }

    @Test
    public void testReplace_StringStringStringInt_8_oe() {

        assertEquals("", StringUtils.replace("", "any", "any", 2));
    }

    @Test
    public void testReplace_StringStringStringInt_9_oe() {


        final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});
        assertSame(str, StringUtils.replace(str, "x", "", -1));
    }

    @Test
    public void testReplace_StringStringStringInt_10_oe() {


        final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});

        assertEquals("f", StringUtils.replace("oofoo", "o", "", -1));
    }

    @Test
    public void testReplace_StringStringStringInt_11_oe() {


        final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});

        assertEquals("oofoo", StringUtils.replace("oofoo", "o", "", 0));
    }

    @Test
    public void testReplace_StringStringStringInt_12_oe() {


        final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});

        assertEquals("ofoo", StringUtils.replace("oofoo", "o", "", 1));
    }

    @Test
    public void testReplace_StringStringStringInt_13_oe() {


        final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});

        assertEquals("foo", StringUtils.replace("oofoo", "o", "", 2));
    }

    @Test
    public void testReplace_StringStringStringInt_14_oe() {


        final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});

        assertEquals("fo", StringUtils.replace("oofoo", "o", "", 3));
    }

    @Test
    public void testReplace_StringStringStringInt_15_oe() {


        final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});

        assertEquals("f", StringUtils.replace("oofoo", "o", "", 4));
    }

    @Test
    public void testReplace_StringStringStringInt_16_oe() {


        final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});


        assertEquals("f", StringUtils.replace("oofoo", "o", "", -5));
    }

    @Test
    public void testReplace_StringStringStringInt_17_oe() {


        final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});


        assertEquals("f", StringUtils.replace("oofoo", "o", "", 1000));
    }

    @Test
    public void testReplaceAll_StringStringString_1_oe() {
        assertNull(StringUtils.replaceAll(null, "", ""));
    }

    @Test
    public void testReplaceAll_StringStringString_2_oe() {

        assertEquals("any", StringUtils.replaceAll("any", null, ""));
    }

    @Test
    public void testReplaceAll_StringStringString_3_oe() {

        assertEquals("any", StringUtils.replaceAll("any", "", null));
    }

    @Test
    public void testReplaceAll_StringStringString_4_oe() {


        assertEquals("zzz", StringUtils.replaceAll("", "", "zzz"));
    }

    @Test
    public void testReplaceAll_StringStringString_5_oe() {


        assertEquals("zzz", StringUtils.replaceAll("", ".*", "zzz"));
    }

    @Test
    public void testReplaceAll_StringStringString_6_oe() {


        assertEquals("", StringUtils.replaceAll("", ".+", "zzz"));
    }

    @Test
    public void testReplaceAll_StringStringString_7_oe() {


        assertEquals("ZZaZZbZZcZZ", StringUtils.replaceAll("abc", "", "ZZ"));
    }

    @Test
    public void testReplaceAll_StringStringString_8_oe() {



        assertEquals("z\nz", StringUtils.replaceAll("<__>\n<__>", "<.*>", "z"));
    }

    @Test
    public void testReplaceAll_StringStringString_9_oe() {



        assertEquals("z", StringUtils.replaceAll("<__>\n<__>", "(?s)<.*>", "z"));
    }

    @Test
    public void testReplaceAll_StringStringString_10_oe() {




        assertEquals("ABC___123", StringUtils.replaceAll("ABCabc123", "[a-z]", "_"));
    }

    @Test
    public void testReplaceAll_StringStringString_11_oe() {




        assertEquals("ABC_123", StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "_"));
    }

    @Test
    public void testReplaceAll_StringStringString_12_oe() {




        assertEquals("ABC123", StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", ""));
    }

    @Test
    public void testReplaceAll_StringStringString_14_oe() throws Exception {





        try {
    StringUtils.replaceAll("any", "{badRegexSyntax}", "");
    fail("PatternSyntaxException: StringUtils.replaceAll expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testReplaceChars_StringCharChar_1_oe() {
        assertNull(StringUtils.replaceChars(null, 'b', 'z'));
    }

    @Test
    public void testReplaceChars_StringCharChar_2_oe() {
        assertEquals("", StringUtils.replaceChars("", 'b', 'z'));
    }

    @Test
    public void testReplaceChars_StringCharChar_3_oe() {
        assertEquals("azcza", StringUtils.replaceChars("abcba", 'b', 'z'));
    }

    @Test
    public void testReplaceChars_StringCharChar_4_oe() {
        assertEquals("abcba", StringUtils.replaceChars("abcba", 'x', 'z'));
    }

    @Test
    public void testReplaceChars_StringStringString_1_oe() {
        assertNull(StringUtils.replaceChars(null, null, null));
    }

    @Test
    public void testReplaceChars_StringStringString_2_oe() {
        assertNull(StringUtils.replaceChars(null, "", null));
    }

    @Test
    public void testReplaceChars_StringStringString_3_oe() {
        assertNull(StringUtils.replaceChars(null, "a", null));
    }

    @Test
    public void testReplaceChars_StringStringString_4_oe() {
        assertNull(StringUtils.replaceChars(null, null, ""));
    }

    @Test
    public void testReplaceChars_StringStringString_5_oe() {
        assertNull(StringUtils.replaceChars(null, null, "x"));
    }

    @Test
    public void testReplaceChars_StringStringString_6_oe() {

        assertEquals("", StringUtils.replaceChars("", null, null));
    }

    @Test
    public void testReplaceChars_StringStringString_7_oe() {

        assertEquals("", StringUtils.replaceChars("", "", null));
    }

    @Test
    public void testReplaceChars_StringStringString_8_oe() {

        assertEquals("", StringUtils.replaceChars("", "a", null));
    }

    @Test
    public void testReplaceChars_StringStringString_9_oe() {

        assertEquals("", StringUtils.replaceChars("", null, ""));
    }

    @Test
    public void testReplaceChars_StringStringString_10_oe() {

        assertEquals("", StringUtils.replaceChars("", null, "x"));
    }

    @Test
    public void testReplaceChars_StringStringString_11_oe() {


        assertEquals("abc", StringUtils.replaceChars("abc", null, null));
    }

    @Test
    public void testReplaceChars_StringStringString_12_oe() {


        assertEquals("abc", StringUtils.replaceChars("abc", null, ""));
    }

    @Test
    public void testReplaceChars_StringStringString_13_oe() {


        assertEquals("abc", StringUtils.replaceChars("abc", null, "x"));
    }

    @Test
    public void testReplaceChars_StringStringString_14_oe() {



        assertEquals("abc", StringUtils.replaceChars("abc", "", null));
    }

    @Test
    public void testReplaceChars_StringStringString_15_oe() {



        assertEquals("abc", StringUtils.replaceChars("abc", "", ""));
    }

    @Test
    public void testReplaceChars_StringStringString_16_oe() {



        assertEquals("abc", StringUtils.replaceChars("abc", "", "x"));
    }

    @Test
    public void testReplaceChars_StringStringString_17_oe() {




        assertEquals("ac", StringUtils.replaceChars("abc", "b", null));
    }

    @Test
    public void testReplaceChars_StringStringString_18_oe() {




        assertEquals("ac", StringUtils.replaceChars("abc", "b", ""));
    }

    @Test
    public void testReplaceChars_StringStringString_19_oe() {




        assertEquals("axc", StringUtils.replaceChars("abc", "b", "x"));
    }

    @Test
    public void testReplaceChars_StringStringString_20_oe() {





        assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yz"));
    }

    @Test
    public void testReplaceChars_StringStringString_21_oe() {





        assertEquals("ayya", StringUtils.replaceChars("abcba", "bc", "y"));
    }

    @Test
    public void testReplaceChars_StringStringString_22_oe() {





        assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yzx"));
    }

    @Test
    public void testReplaceChars_StringStringString_23_oe() {






        assertEquals("abcba", StringUtils.replaceChars("abcba", "z", "w"));
    }

    @Test
    public void testReplaceChars_StringStringString_24_oe() {






        assertSame("abcba", StringUtils.replaceChars("abcba", "z", "w"));
    }

    @Test
    public void testReplaceChars_StringStringString_25_oe() {







        assertEquals("jelly", StringUtils.replaceChars("hello", "ho", "jy"));
    }

    @Test
    public void testReplaceChars_StringStringString_26_oe() {







        assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yz"));
    }

    @Test
    public void testReplaceChars_StringStringString_27_oe() {







        assertEquals("ayya", StringUtils.replaceChars("abcba", "bc", "y"));
    }

    @Test
    public void testReplaceChars_StringStringString_28_oe() {







        assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yzx"));
    }

    @Test
    public void testReplaceChars_StringStringString_29_oe() {








        assertEquals("bcc", StringUtils.replaceChars("abc", "ab", "bc"));
    }

    @Test
    public void testReplaceChars_StringStringString_30_oe() {








        assertEquals("q651.506bera",StringUtils.replaceChars("d216.102oren","abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789","nopqrstuvwxyzabcdefghijklmNOPQRSTUVWXYZABCDEFGHIJKLM567891234"));
    }

    @Test
    public void testReplaceFirst_StringStringString_1_oe() {
        assertNull(StringUtils.replaceFirst(null, "", ""));
    }

    @Test
    public void testReplaceFirst_StringStringString_2_oe() {

        assertEquals("any", StringUtils.replaceFirst("any", null, ""));
    }

    @Test
    public void testReplaceFirst_StringStringString_3_oe() {

        assertEquals("any", StringUtils.replaceFirst("any", "", null));
    }

    @Test
    public void testReplaceFirst_StringStringString_4_oe() {


        assertEquals("zzz", StringUtils.replaceFirst("", "", "zzz"));
    }

    @Test
    public void testReplaceFirst_StringStringString_5_oe() {


        assertEquals("zzz", StringUtils.replaceFirst("", ".*", "zzz"));
    }

    @Test
    public void testReplaceFirst_StringStringString_6_oe() {


        assertEquals("", StringUtils.replaceFirst("", ".+", "zzz"));
    }

    @Test
    public void testReplaceFirst_StringStringString_7_oe() {


        assertEquals("ZZabc", StringUtils.replaceFirst("abc", "", "ZZ"));
    }

    @Test
    public void testReplaceFirst_StringStringString_8_oe() {



        assertEquals("z\n<__>", StringUtils.replaceFirst("<__>\n<__>", "<.*>", "z"));
    }

    @Test
    public void testReplaceFirst_StringStringString_9_oe() {



        assertEquals("z", StringUtils.replaceFirst("<__>\n<__>", "(?s)<.*>", "z"));
    }

    @Test
    public void testReplaceFirst_StringStringString_10_oe() {




        assertEquals("ABC_bc123", StringUtils.replaceFirst("ABCabc123", "[a-z]", "_"));
    }

    @Test
    public void testReplaceFirst_StringStringString_11_oe() {




        assertEquals("ABC_123abc", StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "_"));
    }

    @Test
    public void testReplaceFirst_StringStringString_12_oe() {




        assertEquals("ABC123abc", StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", ""));
    }

    @Test
    public void testReplaceFirst_StringStringString_14_oe() throws Exception {





        try {
    StringUtils.replaceFirst("any", "{badRegexSyntax}", "");
    fail("PatternSyntaxException: StringUtils.replaceFirst expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_1_oe() {
        assertNull(StringUtils.replaceIgnoreCase(null, null, null));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_2_oe() {
        assertNull(StringUtils.replaceIgnoreCase(null, null, "any"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_3_oe() {
        assertNull(StringUtils.replaceIgnoreCase(null, "any", null));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_4_oe() {
        assertNull(StringUtils.replaceIgnoreCase(null, "any", "any"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_5_oe() {

        assertEquals("", StringUtils.replaceIgnoreCase("", null, null));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_6_oe() {

        assertEquals("", StringUtils.replaceIgnoreCase("", null, "any"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_7_oe() {

        assertEquals("", StringUtils.replaceIgnoreCase("", "any", null));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_8_oe() {

        assertEquals("", StringUtils.replaceIgnoreCase("", "any", "any"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_9_oe() {


        assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", "", "any"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_10_oe() {


        assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", null, "any"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_11_oe() {


        assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", "F", null));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_12_oe() {


        assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", null, null));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_13_oe() {



        assertEquals("", StringUtils.replaceIgnoreCase("foofoofoo", "foo", ""));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_14_oe() {



        assertEquals("barbarbar", StringUtils.replaceIgnoreCase("foofoofoo", "foo", "bar"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_15_oe() {



        assertEquals("farfarfar", StringUtils.replaceIgnoreCase("foofoofoo", "oo", "ar"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_16_oe() {




        assertEquals("", StringUtils.replaceIgnoreCase("foofoofoo", "FOO", ""));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_17_oe() {




        assertEquals("barbarbar", StringUtils.replaceIgnoreCase("fooFOOfoo", "foo", "bar"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString_18_oe() {




        assertEquals("farfarfar", StringUtils.replaceIgnoreCase("foofOOfoo", "OO", "ar"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_1_oe() {
        assertNull(StringUtils.replaceIgnoreCase(null, null, null, 2));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_2_oe() {
        assertNull(StringUtils.replaceIgnoreCase(null, null, "any", 2));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_3_oe() {
        assertNull(StringUtils.replaceIgnoreCase(null, "any", null, 2));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_4_oe() {
        assertNull(StringUtils.replaceIgnoreCase(null, "any", "any", 2));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_5_oe() {

        assertEquals("", StringUtils.replaceIgnoreCase("", null, null, 2));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_6_oe() {

        assertEquals("", StringUtils.replaceIgnoreCase("", null, "any", 2));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_7_oe() {

        assertEquals("", StringUtils.replaceIgnoreCase("", "any", null, 2));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_8_oe() {

        assertEquals("", StringUtils.replaceIgnoreCase("", "any", "any", 2));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_9_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });
        assertSame(str, StringUtils.replaceIgnoreCase(str, "x", "", -1));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_10_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });

        assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", -1));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_11_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });

        assertEquals("oofoo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 0));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_12_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });

        assertEquals("ofoo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 1));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_13_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });

        assertEquals("foo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 2));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_14_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });

        assertEquals("fo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 3));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_15_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });

        assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", 4));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_16_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });


        assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", -5));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_17_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });


        assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", 1000));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_18_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });



        assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", -1));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_19_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });



        assertEquals("oofoo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 0));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_20_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });



        assertEquals("ofoo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 1));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_21_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });



        assertEquals("foo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 2));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_22_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });



        assertEquals("fo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 3));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_23_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });



        assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", 4));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_24_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });




        assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", -5));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt_25_oe() {


        final String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });




        assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", 1000));
    }

    @Test
    public void testReplaceOnce_StringStringString_1_oe() {
        assertNull(StringUtils.replaceOnce(null, null, null));
    }

    @Test
    public void testReplaceOnce_StringStringString_2_oe() {
        assertNull(StringUtils.replaceOnce(null, null, "any"));
    }

    @Test
    public void testReplaceOnce_StringStringString_3_oe() {
        assertNull(StringUtils.replaceOnce(null, "any", null));
    }

    @Test
    public void testReplaceOnce_StringStringString_4_oe() {
        assertNull(StringUtils.replaceOnce(null, "any", "any"));
    }

    @Test
    public void testReplaceOnce_StringStringString_5_oe() {

        assertEquals("", StringUtils.replaceOnce("", null, null));
    }

    @Test
    public void testReplaceOnce_StringStringString_6_oe() {

        assertEquals("", StringUtils.replaceOnce("", null, "any"));
    }

    @Test
    public void testReplaceOnce_StringStringString_7_oe() {

        assertEquals("", StringUtils.replaceOnce("", "any", null));
    }

    @Test
    public void testReplaceOnce_StringStringString_8_oe() {

        assertEquals("", StringUtils.replaceOnce("", "any", "any"));
    }

    @Test
    public void testReplaceOnce_StringStringString_9_oe() {


        assertEquals("FOO", StringUtils.replaceOnce("FOO", "", "any"));
    }

    @Test
    public void testReplaceOnce_StringStringString_10_oe() {


        assertEquals("FOO", StringUtils.replaceOnce("FOO", null, "any"));
    }

    @Test
    public void testReplaceOnce_StringStringString_11_oe() {


        assertEquals("FOO", StringUtils.replaceOnce("FOO", "F", null));
    }

    @Test
    public void testReplaceOnce_StringStringString_12_oe() {


        assertEquals("FOO", StringUtils.replaceOnce("FOO", null, null));
    }

    @Test
    public void testReplaceOnce_StringStringString_13_oe() {



        assertEquals("foofoo", StringUtils.replaceOnce("foofoofoo", "foo", ""));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_1_oe() {
        assertNull(StringUtils.replaceOnceIgnoreCase(null, null, null));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_2_oe() {
        assertNull(StringUtils.replaceOnceIgnoreCase(null, null, "any"));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_3_oe() {
        assertNull(StringUtils.replaceOnceIgnoreCase(null, "any", null));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_4_oe() {
        assertNull(StringUtils.replaceOnceIgnoreCase(null, "any", "any"));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_5_oe() {

        assertEquals("", StringUtils.replaceOnceIgnoreCase("", null, null));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_6_oe() {

        assertEquals("", StringUtils.replaceOnceIgnoreCase("", null, "any"));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_7_oe() {

        assertEquals("", StringUtils.replaceOnceIgnoreCase("", "any", null));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_8_oe() {

        assertEquals("", StringUtils.replaceOnceIgnoreCase("", "any", "any"));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_9_oe() {


        assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", "", "any"));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_10_oe() {


        assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", null, "any"));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_11_oe() {


        assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", "F", null));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_12_oe() {


        assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", null, null));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_13_oe() {



        assertEquals("foofoo", StringUtils.replaceOnceIgnoreCase("foofoofoo", "foo", ""));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString_14_oe() {




        assertEquals("Foofoo", StringUtils.replaceOnceIgnoreCase("FoOFoofoo", "foo", ""));
    }

    @Test
    public void testReplacePattern_StringStringString_1_oe() {
        assertNull(StringUtils.replacePattern(null, "", ""));
    }

    @Test
    public void testReplacePattern_StringStringString_2_oe() {
        assertEquals("any", StringUtils.replacePattern("any", null, ""));
    }

    @Test
    public void testReplacePattern_StringStringString_3_oe() {
        assertEquals("any", StringUtils.replacePattern("any", "", null));
    }

    @Test
    public void testReplacePattern_StringStringString_4_oe() {

        assertEquals("zzz", StringUtils.replacePattern("", "", "zzz"));
    }

    @Test
    public void testReplacePattern_StringStringString_5_oe() {

        assertEquals("zzz", StringUtils.replacePattern("", ".*", "zzz"));
    }

    @Test
    public void testReplacePattern_StringStringString_6_oe() {

        assertEquals("", StringUtils.replacePattern("", ".+", "zzz"));
    }

    @Test
    public void testReplacePattern_StringStringString_7_oe() {


        assertEquals("z", StringUtils.replacePattern("<__>\n<__>", "<.*>", "z"));
    }

    @Test
    public void testReplacePattern_StringStringString_8_oe() {


        assertEquals("z", StringUtils.replacePattern("<__>\\n<__>", "<.*>", "z"));
    }

    @Test
    public void testReplacePattern_StringStringString_9_oe() {


        assertEquals("X", StringUtils.replacePattern("<A>\nxy\n</A>", "<A>.*</A>", "X"));
    }

    @Test
    public void testReplacePattern_StringStringString_10_oe() {



        assertEquals("ABC___123", StringUtils.replacePattern("ABCabc123", "[a-z]", "_"));
    }

    @Test
    public void testReplacePattern_StringStringString_11_oe() {



        assertEquals("ABC_123", StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "_"));
    }

    @Test
    public void testReplacePattern_StringStringString_12_oe() {



        assertEquals("ABC123", StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", ""));
    }

    @Test
    public void testReverse_String_1_oe() {
        assertNull(StringUtils.reverse(null));
    }

    @Test
    public void testReverse_String_2_oe() {
        assertEquals("", StringUtils.reverse(""));
    }

    @Test
    public void testReverse_String_3_oe() {
        assertEquals("sdrawkcab", StringUtils.reverse("backwards"));
    }

    @Test
    public void testReverseDelimited_StringChar_1_oe() {
        assertNull(StringUtils.reverseDelimited(null, '.'));
    }

    @Test
    public void testReverseDelimited_StringChar_2_oe() {
        assertEquals("", StringUtils.reverseDelimited("", '.'));
    }

    @Test
    public void testReverseDelimited_StringChar_3_oe() {
        assertEquals("c.b.a", StringUtils.reverseDelimited("a.b.c", '.'));
    }

    @Test
    public void testReverseDelimited_StringChar_4_oe() {
        assertEquals("a b c", StringUtils.reverseDelimited("a b c", '.'));
    }

    @Test
    public void testReverseDelimited_StringChar_5_oe() {
        assertEquals("", StringUtils.reverseDelimited("", '.'));
    }

    @Test
    public void testRightPad_StringInt_1_oe() {
        assertNull(StringUtils.rightPad(null, 5));
    }

    @Test
    public void testRightPad_StringInt_2_oe() {
        assertEquals("     ", StringUtils.rightPad("", 5));
    }

    @Test
    public void testRightPad_StringInt_3_oe() {
        assertEquals("abc  ", StringUtils.rightPad("abc", 5));
    }

    @Test
    public void testRightPad_StringInt_4_oe() {
        assertEquals("abc", StringUtils.rightPad("abc", 2));
    }

    @Test
    public void testRightPad_StringInt_5_oe() {
        assertEquals("abc", StringUtils.rightPad("abc", -1));
    }

    @Test
    public void testRightPad_StringIntChar_1_oe() {
        assertNull(StringUtils.rightPad(null, 5, ' '));
    }

    @Test
    public void testRightPad_StringIntChar_2_oe() {
        assertEquals("     ", StringUtils.rightPad("", 5, ' '));
    }

    @Test
    public void testRightPad_StringIntChar_3_oe() {
        assertEquals("abc  ", StringUtils.rightPad("abc", 5, ' '));
    }

    @Test
    public void testRightPad_StringIntChar_4_oe() {
        assertEquals("abc", StringUtils.rightPad("abc", 2, ' '));
    }

    @Test
    public void testRightPad_StringIntChar_5_oe() {
        assertEquals("abc", StringUtils.rightPad("abc", -1, ' '));
    }

    @Test
    public void testRightPad_StringIntChar_6_oe() {
        assertEquals("abcxx", StringUtils.rightPad("abc", 5, 'x'));
    }

    @Test
    public void testRightPad_StringIntChar_7_oe() {
        final String str = StringUtils.rightPad("aaa", 10000, 'a');  // bigger than pad length
        assertEquals(10000, str.length());
    }

    @Test
    public void testRightPad_StringIntChar_8_oe() {
        final String str = StringUtils.rightPad("aaa", 10000, 'a');  // bigger than pad length
        assertTrue(StringUtils.containsOnly(str, 'a'));
    }

    @Test
    public void testRightPad_StringIntString_1_oe() {
        assertNull(StringUtils.rightPad(null, 5, "-+"));
    }

    @Test
    public void testRightPad_StringIntString_2_oe() {
        assertEquals("     ", StringUtils.rightPad("", 5, " "));
    }

    @Test
    public void testRightPad_StringIntString_3_oe() {
        assertNull(StringUtils.rightPad(null, 8, null));
    }

    @Test
    public void testRightPad_StringIntString_4_oe() {
        assertEquals("abc-+-+", StringUtils.rightPad("abc", 7, "-+"));
    }

    @Test
    public void testRightPad_StringIntString_5_oe() {
        assertEquals("abc-+~", StringUtils.rightPad("abc", 6, "-+~"));
    }

    @Test
    public void testRightPad_StringIntString_6_oe() {
        assertEquals("abc-+", StringUtils.rightPad("abc", 5, "-+~"));
    }

    @Test
    public void testRightPad_StringIntString_7_oe() {
        assertEquals("abc", StringUtils.rightPad("abc", 2, " "));
    }

    @Test
    public void testRightPad_StringIntString_8_oe() {
        assertEquals("abc", StringUtils.rightPad("abc", -1, " "));
    }

    @Test
    public void testRightPad_StringIntString_9_oe() {
        assertEquals("abc  ", StringUtils.rightPad("abc", 5, null));
    }

    @Test
    public void testRightPad_StringIntString_10_oe() {
        assertEquals("abc  ", StringUtils.rightPad("abc", 5, ""));
    }

    @Test
    public void testRotate_StringInt_1_oe() {
        assertNull(StringUtils.rotate(null, 1));
    }

    @Test
    public void testRotate_StringInt_2_oe() {
        assertEquals("", StringUtils.rotate("", 1));
    }

    @Test
    public void testRotate_StringInt_3_oe() {
        assertEquals("abcdefg", StringUtils.rotate("abcdefg", 0));
    }

    @Test
    public void testRotate_StringInt_4_oe() {
        assertEquals("fgabcde", StringUtils.rotate("abcdefg", 2));
    }

    @Test
    public void testRotate_StringInt_5_oe() {
        assertEquals("cdefgab", StringUtils.rotate("abcdefg", -2));
    }

    @Test
    public void testRotate_StringInt_6_oe() {
        assertEquals("abcdefg", StringUtils.rotate("abcdefg", 7));
    }

    @Test
    public void testRotate_StringInt_7_oe() {
        assertEquals("abcdefg", StringUtils.rotate("abcdefg", -7));
    }

    @Test
    public void testRotate_StringInt_8_oe() {
        assertEquals("fgabcde", StringUtils.rotate("abcdefg", 9));
    }

    @Test
    public void testRotate_StringInt_9_oe() {
        assertEquals("cdefgab", StringUtils.rotate("abcdefg", -9));
    }

    @Test
    public void testRotate_StringInt_10_oe() {
        assertEquals("efgabcd", StringUtils.rotate("abcdefg", 17));
    }

    @Test
    public void testRotate_StringInt_11_oe() {
        assertEquals("defgabc", StringUtils.rotate("abcdefg", -17));
    }

    @Test
    public void testSplit_String_1_oe() {
        assertNull(StringUtils.split(null));
    }

    @Test
    public void testSplit_String_2_oe() {
        assertEquals(0, StringUtils.split("").length);
    }

    @Test
    public void testSplit_String_3_oe() {

        String str = "a b  .c";
        String[] res = StringUtils.split(str);
        assertEquals(3, res.length);
    }

    @Test
    public void testSplit_String_4_oe() {

        String str = "a b  .c";
        String[] res = StringUtils.split(str);
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplit_String_5_oe() {

        String str = "a b  .c";
        String[] res = StringUtils.split(str);
        assertEquals("b", res[1]);
    }

    @Test
    public void testSplit_String_6_oe() {

        String str = "a b  .c";
        String[] res = StringUtils.split(str);
        assertEquals(".c", res[2]);
    }

    @Test
    public void testSplit_String_7_oe() {

        String str = "a b  .c";
        String[] res = StringUtils.split(str);

        str = " a ";
        res = StringUtils.split(str);
        assertEquals(1, res.length);
    }

    @Test
    public void testSplit_String_8_oe() {

        String str = "a b  .c";
        String[] res = StringUtils.split(str);

        str = " a ";
        res = StringUtils.split(str);
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplit_String_9_oe() {

        String str = "a b  .c";
        String[] res = StringUtils.split(str);

        str = " a ";
        res = StringUtils.split(str);

        str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
        res = StringUtils.split(str);
        assertEquals(2, res.length);
    }

    @Test
    public void testSplit_String_10_oe() {

        String str = "a b  .c";
        String[] res = StringUtils.split(str);

        str = " a ";
        res = StringUtils.split(str);

        str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
        res = StringUtils.split(str);
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplit_String_11_oe() {

        String str = "a b  .c";
        String[] res = StringUtils.split(str);

        str = " a ";
        res = StringUtils.split(str);

        str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
        res = StringUtils.split(str);
        assertEquals("b" + NON_WHITESPACE + "c", res[1]);
    }

    @Test
    public void testSplit_StringChar_1_oe() {
        assertNull(StringUtils.split(null, '.'));
    }

    @Test
    public void testSplit_StringChar_2_oe() {
        assertEquals(0, StringUtils.split("", '.').length);
    }

    @Test
    public void testSplit_StringChar_3_oe() {

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');
        assertEquals(3, res.length);
    }

    @Test
    public void testSplit_StringChar_4_oe() {

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplit_StringChar_5_oe() {

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');
        assertEquals("b", res[1]);
    }

    @Test
    public void testSplit_StringChar_6_oe() {

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');
        assertEquals(" c", res[2]);
    }

    @Test
    public void testSplit_StringChar_7_oe() {

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');

        str = ".a.";
        res = StringUtils.split(str, '.');
        assertEquals(1, res.length);
    }

    @Test
    public void testSplit_StringChar_8_oe() {

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');

        str = ".a.";
        res = StringUtils.split(str, '.');
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplit_StringChar_9_oe() {

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');

        str = ".a.";
        res = StringUtils.split(str, '.');

        str = "a b c";
        res = StringUtils.split(str, ' ');
        assertEquals(3, res.length);
    }

    @Test
    public void testSplit_StringChar_10_oe() {

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');

        str = ".a.";
        res = StringUtils.split(str, '.');

        str = "a b c";
        res = StringUtils.split(str, ' ');
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplit_StringChar_11_oe() {

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');

        str = ".a.";
        res = StringUtils.split(str, '.');

        str = "a b c";
        res = StringUtils.split(str, ' ');
        assertEquals("b", res[1]);
    }

    @Test
    public void testSplit_StringChar_12_oe() {

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');

        str = ".a.";
        res = StringUtils.split(str, '.');

        str = "a b c";
        res = StringUtils.split(str, ' ');
        assertEquals("c", res[2]);
    }

    @Test
    public void testSplit_StringString_StringStringInt_1_oe() {
        assertNull(StringUtils.split(null, "."));
    }

    @Test
    public void testSplit_StringString_StringStringInt_2_oe() {
        assertNull(StringUtils.split(null, ".", 3));
    }

    @Test
    public void testSplit_StringString_StringStringInt_3_oe() {

        assertEquals(0, StringUtils.split("", ".").length);
    }

    @Test
    public void testSplit_StringString_StringStringInt_4_oe() {

        assertEquals(0, StringUtils.split("", ".", 3).length);
    }

    @Test
    public void testSplit_StringString_StringStringInt_5_oe() {


        innerTestSplit('.', ".", ' ');
        innerTestSplit('.', ".", ',');
        innerTestSplit('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplit(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplit(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        String[] results;
        final String[] expectedResults = {"ab", "de fg"};
        results = StringUtils.split("ab   de fg", null, 2);
        assertEquals(expectedResults.length, results.length);
    }

    @Test
    public void testSplit_StringString_StringStringInt_6_oe() {


        innerTestSplit('.', ".", ' ');
        innerTestSplit('.', ".", ',');
        innerTestSplit('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplit(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplit(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        String[] results;
        final String[] expectedResults = {"ab", "de fg"};
        results = StringUtils.split("ab   de fg", null, 2);
        for (int i = 0; i < expectedResults.length; i++) {
            assertEquals(expectedResults[i], results[i]);
    }
    }

    @Test
    public void testSplit_StringString_StringStringInt_7_oe() {


        innerTestSplit('.', ".", ' ');
        innerTestSplit('.', ".", ',');
        innerTestSplit('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplit(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplit(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        String[] results;
        final String[] expectedResults = {"ab", "de fg"};
        results = StringUtils.split("ab   de fg", null, 2);
        for (int i = 0; i < expectedResults.length; i++) {
        }

        final String[] expectedResults2 = {"ab", "cd:ef"};
        results = StringUtils.split("ab:cd:ef", ":", 2);
        assertEquals(expectedResults2.length, results.length);
    }

    @Test
    public void testSplit_StringString_StringStringInt_8_oe() {


        innerTestSplit('.', ".", ' ');
        innerTestSplit('.', ".", ',');
        innerTestSplit('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplit(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplit(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        String[] results;
        final String[] expectedResults = {"ab", "de fg"};
        results = StringUtils.split("ab   de fg", null, 2);
        for (int i = 0; i < expectedResults.length; i++) {
        }

        final String[] expectedResults2 = {"ab", "cd:ef"};
        results = StringUtils.split("ab:cd:ef", ":", 2);
        for (int i = 0; i < expectedResults2.length; i++) {
            assertEquals(expectedResults2[i], results[i]);
    }
    }

    @Test
    public void testSplitByCharacterType_1_oe() {
        assertNull(StringUtils.splitByCharacterType(null));
    }

    @Test
    public void testSplitByCharacterType_2_oe() {
        assertEquals(0, StringUtils.splitByCharacterType("").length);
    }

    @Test
    public void testSplitByCharacterType_3_oe() {

        assertTrue(Objects.deepEquals(new String[]{"ab", " ", "de", " ", "fg"}, StringUtils.splitByCharacterType("ab de fg")));
    }

    @Test
    public void testSplitByCharacterType_4_oe() {


        assertTrue(Objects.deepEquals(new String[]{"ab", "   ", "de", " ", "fg"}, StringUtils.splitByCharacterType("ab   de fg")));
    }

    @Test
    public void testSplitByCharacterType_5_oe() {



        assertTrue(Objects.deepEquals(new String[]{"ab", ":", "cd", ":", "ef"}, StringUtils.splitByCharacterType("ab:cd:ef")));
    }

    @Test
    public void testSplitByCharacterType_6_oe() {




        assertTrue(Objects.deepEquals(new String[]{"number", "5"}, StringUtils.splitByCharacterType("number5")));
    }

    @Test
    public void testSplitByCharacterType_7_oe() {





        assertTrue(Objects.deepEquals(new String[]{"foo", "B", "ar"}, StringUtils.splitByCharacterType("fooBar")));
    }

    @Test
    public void testSplitByCharacterType_8_oe() {






        assertTrue(Objects.deepEquals(new String[]{"foo", "200", "B", "ar"}, StringUtils.splitByCharacterType("foo200Bar")));
    }

    @Test
    public void testSplitByCharacterType_9_oe() {







        assertTrue(Objects.deepEquals(new String[]{"ASFR", "ules"}, StringUtils.splitByCharacterType("ASFRules")));
    }

    @Test
    public void testSplitByCharacterTypeCamelCase_1_oe() {
        assertNull(StringUtils.splitByCharacterTypeCamelCase(null));
    }

    @Test
    public void testSplitByCharacterTypeCamelCase_2_oe() {
        assertEquals(0, StringUtils.splitByCharacterTypeCamelCase("").length);
    }

    @Test
    public void testSplitByCharacterTypeCamelCase_3_oe() {

        assertTrue(Objects.deepEquals(new String[]{"ab", " ", "de", " ", "fg"}, StringUtils.splitByCharacterTypeCamelCase("ab de fg")));
    }

    @Test
    public void testSplitByCharacterTypeCamelCase_4_oe() {


        assertTrue(Objects.deepEquals(new String[]{"ab", "   ", "de", " ", "fg"}, StringUtils.splitByCharacterTypeCamelCase("ab   de fg")));
    }

    @Test
    public void testSplitByCharacterTypeCamelCase_5_oe() {



        assertTrue(Objects.deepEquals(new String[]{"ab", ":", "cd", ":", "ef"}, StringUtils.splitByCharacterTypeCamelCase("ab:cd:ef")));
    }

    @Test
    public void testSplitByCharacterTypeCamelCase_6_oe() {




        assertTrue(Objects.deepEquals(new String[]{"number", "5"}, StringUtils.splitByCharacterTypeCamelCase("number5")));
    }

    @Test
    public void testSplitByCharacterTypeCamelCase_7_oe() {





        assertTrue(Objects.deepEquals(new String[]{"foo", "Bar"}, StringUtils.splitByCharacterTypeCamelCase("fooBar")));
    }

    @Test
    public void testSplitByCharacterTypeCamelCase_8_oe() {






        assertTrue(Objects.deepEquals(new String[]{"foo", "200", "Bar"}, StringUtils.splitByCharacterTypeCamelCase("foo200Bar")));
    }

    @Test
    public void testSplitByCharacterTypeCamelCase_9_oe() {







        assertTrue(Objects.deepEquals(new String[]{"ASF", "Rules"}, StringUtils.splitByCharacterTypeCamelCase("ASFRules")));
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringString_1_oe() {
        assertArrayEquals(null, StringUtils.splitByWholeSeparatorPreserveAllTokens(null, "."));
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringString_2_oe() {

        assertEquals(0, StringUtils.splitByWholeSeparatorPreserveAllTokens("", ".").length);
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringString_3_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null);
        assertEquals(expected.length, actual.length);
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringString_4_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null);
        for (int i = 0; i < actual.length; i += 1) {
            assertEquals(expected[i], actual[i]);
    }
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringString_5_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":");
        assertEquals(expected.length, actual.length);
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringString_6_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":");
        for (int i = 0; i < actual.length; i += 1) {
            assertEquals(expected[i], actual[i]);
    }
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringString_7_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":");
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "2", ":3", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, "::");
        assertEquals(expected.length, actual.length);
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringString_8_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":");
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "2", ":3", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, "::");
        for (int i = 0; i < actual.length; i += 1) {
            assertEquals(expected[i], actual[i]);
    }
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt_1_oe() {
        assertArrayEquals(null, StringUtils.splitByWholeSeparatorPreserveAllTokens(null, ".", -1));
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt_2_oe() {

        assertEquals(0, StringUtils.splitByWholeSeparatorPreserveAllTokens("", ".", -1).length);
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt_3_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null, -1);
        assertEquals(expected.length, actual.length);
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt_4_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null, -1);
        for (int i = 0; i < actual.length; i += 1) {
            assertEquals(expected[i], actual[i]);
    }
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt_5_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null, -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":", -1);
        assertEquals(expected.length, actual.length);
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt_6_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null, -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":", -1);
        for (int i = 0; i < actual.length; i += 1) {
            assertEquals(expected[i], actual[i]);
    }
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt_7_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null, -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":", -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "2", ":3", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, "::", -1);
        assertEquals(expected.length, actual.length);
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt_8_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null, -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":", -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "2", ":3", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, "::", -1);
        for (int i = 0; i < actual.length; i += 1) {
            assertEquals(expected[i], actual[i]);
    }
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt_9_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null, -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":", -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "2", ":3", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, "::", -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2::3:4";
        expected = new String[]{"1", "", "2", ":3:4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":", 4);
        assertEquals(expected.length, actual.length);
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt_10_oe() {


        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null, -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":", -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2:::3::::4";
        expected = new String[]{"1", "2", ":3", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, "::", -1);
        for (int i = 0; i < actual.length; i += 1) {
        }

        input = "1::2::3:4";
        expected = new String[]{"1", "", "2", ":3:4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":", 4);
        for (int i = 0; i < actual.length; i += 1) {
            assertEquals(expected[i], actual[i]);
    }
    }

    @Test
    public void testSplitByWholeString_StringStringBoolean_1_oe() {
        assertArrayEquals(null, StringUtils.splitByWholeSeparator(null, "."));
    }

    @Test
    public void testSplitByWholeString_StringStringBoolean_2_oe() {

        assertEquals(0, StringUtils.splitByWholeSeparator("", ".").length);
    }

    @Test
    public void testSplitByWholeString_StringStringBoolean_3_oe() {


        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de", "fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null);
        assertEquals(splitOnNullExpectedResults.length, splitOnNullResults.length);
    }

    @Test
    public void testSplitByWholeString_StringStringBoolean_4_oe() {


        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de", "fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null);
        for (int i = 0; i < splitOnNullExpectedResults.length; i += 1) {
            assertEquals(splitOnNullExpectedResults[i], splitOnNullResults[i]);
    }
    }

    @Test
    public void testSplitByWholeString_StringStringBoolean_5_oe() {


        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de", "fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null);
        for (int i = 0; i < splitOnNullExpectedResults.length; i += 1) {
        }

        final String stringToSplitOnCharactersAndString = "abstemiouslyaeiouyabstemiously";

        final String[] splitOnStringExpectedResults = {"abstemiously", "abstemiously"};
        final String[] splitOnStringResults = StringUtils.splitByWholeSeparator(stringToSplitOnCharactersAndString, "aeiouy");
        assertEquals(splitOnStringExpectedResults.length, splitOnStringResults.length);
    }

    @Test
    public void testSplitByWholeString_StringStringBoolean_6_oe() {


        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de", "fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null);
        for (int i = 0; i < splitOnNullExpectedResults.length; i += 1) {
        }

        final String stringToSplitOnCharactersAndString = "abstemiouslyaeiouyabstemiously";

        final String[] splitOnStringExpectedResults = {"abstemiously", "abstemiously"};
        final String[] splitOnStringResults = StringUtils.splitByWholeSeparator(stringToSplitOnCharactersAndString, "aeiouy");
        for (int i = 0; i < splitOnStringExpectedResults.length; i += 1) {
            assertEquals(splitOnStringExpectedResults[i], splitOnStringResults[i]);
    }
    }

    @Test
    public void testSplitByWholeString_StringStringBoolean_7_oe() {


        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de", "fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null);
        for (int i = 0; i < splitOnNullExpectedResults.length; i += 1) {
        }

        final String stringToSplitOnCharactersAndString = "abstemiouslyaeiouyabstemiously";

        final String[] splitOnStringExpectedResults = {"abstemiously", "abstemiously"};
        final String[] splitOnStringResults = StringUtils.splitByWholeSeparator(stringToSplitOnCharactersAndString, "aeiouy");
        for (int i = 0; i < splitOnStringExpectedResults.length; i += 1) {
        }

        final String[] splitWithMultipleSeparatorExpectedResults = {"ab", "cd", "ef"};
        final String[] splitWithMultipleSeparator = StringUtils.splitByWholeSeparator("ab:cd::ef", ":");
        assertEquals(splitWithMultipleSeparatorExpectedResults.length, splitWithMultipleSeparator.length);
    }

    @Test
    public void testSplitByWholeString_StringStringBoolean_8_oe() {


        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de", "fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null);
        for (int i = 0; i < splitOnNullExpectedResults.length; i += 1) {
        }

        final String stringToSplitOnCharactersAndString = "abstemiouslyaeiouyabstemiously";

        final String[] splitOnStringExpectedResults = {"abstemiously", "abstemiously"};
        final String[] splitOnStringResults = StringUtils.splitByWholeSeparator(stringToSplitOnCharactersAndString, "aeiouy");
        for (int i = 0; i < splitOnStringExpectedResults.length; i += 1) {
        }

        final String[] splitWithMultipleSeparatorExpectedResults = {"ab", "cd", "ef"};
        final String[] splitWithMultipleSeparator = StringUtils.splitByWholeSeparator("ab:cd::ef", ":");
        for (int i = 0; i < splitWithMultipleSeparatorExpectedResults.length; i++) {
            assertEquals(splitWithMultipleSeparatorExpectedResults[i], splitWithMultipleSeparator[i]);
    }
    }

    @Test
    public void testSplitByWholeString_StringStringBooleanInt_1_oe() {
        assertArrayEquals(null, StringUtils.splitByWholeSeparator(null, ".", 3));
    }

    @Test
    public void testSplitByWholeString_StringStringBooleanInt_2_oe() {

        assertEquals(0, StringUtils.splitByWholeSeparator("", ".", 3).length);
    }

    @Test
    public void testSplitByWholeString_StringStringBooleanInt_3_oe() {


        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null, 2);
        assertEquals(splitOnNullExpectedResults.length, splitOnNullResults.length);
    }

    @Test
    public void testSplitByWholeString_StringStringBooleanInt_4_oe() {


        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null, 2);
        for (int i = 0; i < splitOnNullExpectedResults.length; i += 1) {
            assertEquals(splitOnNullExpectedResults[i], splitOnNullResults[i]);
    }
    }

    @Test
    public void testSplitByWholeString_StringStringBooleanInt_5_oe() {


        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null, 2);
        for (int i = 0; i < splitOnNullExpectedResults.length; i += 1) {
        }

        final String stringToSplitOnCharactersAndString = "abstemiouslyaeiouyabstemiouslyaeiouyabstemiously";

        final String[] splitOnStringExpectedResults = {"abstemiously", "abstemiouslyaeiouyabstemiously"};
        final String[] splitOnStringResults = StringUtils.splitByWholeSeparator(stringToSplitOnCharactersAndString, "aeiouy", 2);
        assertEquals(splitOnStringExpectedResults.length, splitOnStringResults.length);
    }

    @Test
    public void testSplitByWholeString_StringStringBooleanInt_6_oe() {


        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null, 2);
        for (int i = 0; i < splitOnNullExpectedResults.length; i += 1) {
        }

        final String stringToSplitOnCharactersAndString = "abstemiouslyaeiouyabstemiouslyaeiouyabstemiously";

        final String[] splitOnStringExpectedResults = {"abstemiously", "abstemiouslyaeiouyabstemiously"};
        final String[] splitOnStringResults = StringUtils.splitByWholeSeparator(stringToSplitOnCharactersAndString, "aeiouy", 2);
        for (int i = 0; i < splitOnStringExpectedResults.length; i++) {
            assertEquals(splitOnStringExpectedResults[i], splitOnStringResults[i]);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_String_1_oe() {
        assertNull(StringUtils.splitPreserveAllTokens(null));
    }

    @Test
    public void testSplitPreserveAllTokens_String_2_oe() {
        assertEquals(0, StringUtils.splitPreserveAllTokens("").length);
    }

    @Test
    public void testSplitPreserveAllTokens_String_3_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(2, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_String_4_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("abc", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_5_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("def", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_6_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(3, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_String_7_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("abc", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_8_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_9_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("def", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_10_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(3, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_String_11_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_12_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("abc", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_13_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_14_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(3, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_String_15_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_16_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("b", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_17_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(".c", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_18_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(4, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_String_19_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_20_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("a", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_21_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("b", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_22_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(".c", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_23_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(5, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_String_24_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_25_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_26_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("b", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_27_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_28_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(".c", res[4]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_29_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(4, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_String_30_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_31_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("a", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_32_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_33_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_34_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  b";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(4, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_String_35_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  b";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_36_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  b";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("a", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_37_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  b";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_38_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  b";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("b", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_39_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  b";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals(WHITESPACE.length() + 1, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_String_40_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  b";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
        res = StringUtils.splitPreserveAllTokens(str);
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_String_41_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  b";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
        res = StringUtils.splitPreserveAllTokens(str);
        for (int i = 1; i < WHITESPACE.length() - 1; i++) {
            assertEquals("", res[i]);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_String_42_oe() {

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);

        str = " a  b";
        res = StringUtils.splitPreserveAllTokens(str);

        str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
        res = StringUtils.splitPreserveAllTokens(str);
        for (int i = 1; i < WHITESPACE.length() - 1; i++) {
        }
        assertEquals("b" + NON_WHITESPACE + "c", res[WHITESPACE.length()]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_1_oe() {
        assertNull(StringUtils.splitPreserveAllTokens(null, '.'));
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_2_oe() {
        assertEquals(0, StringUtils.splitPreserveAllTokens("", '.').length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_3_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals(3, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_4_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_5_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("b", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_6_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals(" c", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_7_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals(4, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_8_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_9_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("b", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_10_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_11_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals(" c", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_12_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals(3, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_13_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_14_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("a", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_15_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_16_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals(4, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_17_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_18_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("a", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_19_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_20_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_21_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals(4, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_22_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_23_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_24_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("a", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_25_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_26_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals(3, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_27_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_28_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_29_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        assertEquals("a", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_30_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals(3, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_31_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_32_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("b", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_33_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("c", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_34_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals(5, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_35_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_36_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_37_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("b", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_38_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_39_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("c", res[4]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_40_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals(4, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_41_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_42_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("a", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_43_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("b", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_44_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("c", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_45_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals(5, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_46_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_47_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_48_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("a", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_49_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("b", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_50_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("c", res[4]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_51_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals(4, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_52_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_53_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("b", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_54_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("c", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_55_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_56_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c  ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals(5, res.length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_57_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c  ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("a", res[0]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_58_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c  ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("b", res[1]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_59_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c  ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("c", res[2]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_60_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c  ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_61_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c  ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        assertEquals("", res[3]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_62_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c  ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        {
            String[] results;
            final String[] expectedResults = {"a", "", "b", "c"};
            results = StringUtils.splitPreserveAllTokens("a..b.c", '.');
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar_63_oe() {

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        str = "a b c  ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');

        {
            String[] results;
            final String[] expectedResults = {"a", "", "b", "c"};
            results = StringUtils.splitPreserveAllTokens("a..b.c", '.');
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_1_oe() {
        assertNull(StringUtils.splitPreserveAllTokens(null, "."));
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_2_oe() {
        assertNull(StringUtils.splitPreserveAllTokens(null, ".", 3));
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_3_oe() {

        assertEquals(0, StringUtils.splitPreserveAllTokens("", ".").length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_4_oe() {

        assertEquals(0, StringUtils.splitPreserveAllTokens("", ".", 3).length);
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_5_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_6_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_7_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_8_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_9_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_10_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_11_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_12_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_13_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_14_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_15_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_16_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_17_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_18_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_19_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 3);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_20_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 3);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_21_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 4);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_22_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 4);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_23_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"", "ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens(":ab:::cd:ef", ":", 5);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_24_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"", "ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens(":ab:::cd:ef", ":", 5);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_25_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"", "ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens(":ab:::cd:ef", ":", 5);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"", "", "ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens("::ab:::cd:ef", ":", 6);
            assertEquals(expectedResults.length, results.length);
    }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt_26_oe() {


        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 3);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 4);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"", "ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens(":ab:::cd:ef", ":", 5);
            for (int i = 0; i < expectedResults.length; i++) {
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"", "", "ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens("::ab:::cd:ef", ":", 6);
            for (int i = 0; i < expectedResults.length; i++) {
                assertEquals(expectedResults[i], results[i]);
    }
    }
    }

    @Test
    public void testStringUtilsCharSequenceContract_1_oe() {
        final Class<StringUtils> c = StringUtils.class;
        final String[] excludeMethods = {
            "public static int org.apache.commons.lang3.StringUtils.compare(java.lang.String,java.lang.String)",
            "public static int org.apache.commons.lang3.StringUtils.compare(java.lang.String,java.lang.String,boolean)",
            "public static int org.apache.commons.lang3.StringUtils.compareIgnoreCase(java.lang.String,java.lang.String)",
            "public static int org.apache.commons.lang3.StringUtils.compareIgnoreCase(java.lang.String,java.lang.String,boolean)",
            "public static byte[] org.apache.commons.lang3.StringUtils.getBytes(java.lang.String,java.nio.charset.Charset)",
            "public static byte[] org.apache.commons.lang3.StringUtils.getBytes(java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException"
        };
        final Method[] methods = c.getMethods();

        for (final Method m : methods) {
            final String methodStr = m.toString();
            if (m.getReturnType() == String.class || m.getReturnType() == String[].class) {
                final Class<?>[] params = m.getParameterTypes();
                if (params.length > 0 && (params[0] == CharSequence.class || params[0] == CharSequence[].class)) {
                    assertTrue(!ArrayUtils.contains(excludeMethods,methodStr),"The method \"" + methodStr + "\" appears to be mutable in spirit and therefore must not accept a CharSequence");
    }
    }
    }
    }

    @Test
    public void testStringUtilsCharSequenceContract_2_oe() {
        final Class<StringUtils> c = StringUtils.class;
        final String[] excludeMethods = {
            "public static int org.apache.commons.lang3.StringUtils.compare(java.lang.String,java.lang.String)",
            "public static int org.apache.commons.lang3.StringUtils.compare(java.lang.String,java.lang.String,boolean)",
            "public static int org.apache.commons.lang3.StringUtils.compareIgnoreCase(java.lang.String,java.lang.String)",
            "public static int org.apache.commons.lang3.StringUtils.compareIgnoreCase(java.lang.String,java.lang.String,boolean)",
            "public static byte[] org.apache.commons.lang3.StringUtils.getBytes(java.lang.String,java.nio.charset.Charset)",
            "public static byte[] org.apache.commons.lang3.StringUtils.getBytes(java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException"
        };
        final Method[] methods = c.getMethods();

        for (final Method m : methods) {
            final String methodStr = m.toString();
            if (m.getReturnType() == String.class || m.getReturnType() == String[].class) {
                final Class<?>[] params = m.getParameterTypes();
                if (params.length > 0 && (params[0] == CharSequence.class || params[0] == CharSequence[].class)) {
                }
            } else {
                final Class<?>[] params = m.getParameterTypes();
                if (params.length > 0 && (params[0] == String.class || params[0] == String[].class)) {
                    assertTrue(ArrayUtils.contains(excludeMethods,methodStr),"The method \"" + methodStr + "\" appears to be immutable in spirit and therefore must not accept a String");
    }
    }
    }
    }

    @Test
    public void testSwapCase_String_1_oe() {
        assertNull(StringUtils.swapCase(null));
    }

    @Test
    public void testSwapCase_String_2_oe() {
        assertEquals("", StringUtils.swapCase(""));
    }

    @Test
    public void testSwapCase_String_3_oe() {
        assertEquals("  ", StringUtils.swapCase("  "));
    }

    @Test
    public void testSwapCase_String_4_oe() {

        assertEquals("i", WordUtils.swapCase("I"));
    }

    @Test
    public void testSwapCase_String_5_oe() {

        assertEquals("I", WordUtils.swapCase("i"));
    }

    @Test
    public void testSwapCase_String_6_oe() {

        assertEquals("I AM HERE 123", StringUtils.swapCase("i am here 123"));
    }

    @Test
    public void testSwapCase_String_7_oe() {

        assertEquals("i aM hERE 123", StringUtils.swapCase("I Am Here 123"));
    }

    @Test
    public void testSwapCase_String_8_oe() {

        assertEquals("I AM here 123", StringUtils.swapCase("i am HERE 123"));
    }

    @Test
    public void testSwapCase_String_9_oe() {

        assertEquals("i am here 123", StringUtils.swapCase("I AM HERE 123"));
    }

    @Test
    public void testSwapCase_String_10_oe() {


        final String test = "This String contains a TitleCase character: \u01C8";
        final String expect = "tHIS sTRING CONTAINS A tITLEcASE CHARACTER: \u01C9";
        assertEquals(expect, WordUtils.swapCase(test));
    }

    @Test
    public void testSwapCase_String_11_oe() {


        final String test = "This String contains a TitleCase character: \u01C8";
        final String expect = "tHIS sTRING CONTAINS A tITLEcASE CHARACTER: \u01C9";
        assertEquals(expect, StringUtils.swapCase(test));
    }

    @Test
    public void testToCodePoints_1_oe() {
        final int orphanedHighSurrogate = 0xD801;
        final int orphanedLowSurrogate = 0xDC00;
        final int supplementary = 0x2070E;

        final int[] codePoints = {'a', orphanedHighSurrogate, 'b', 'c', supplementary,
                'd', orphanedLowSurrogate, 'e'};
        final String s = new String(codePoints, 0, codePoints.length);
        assertArrayEquals(codePoints, StringUtils.toCodePoints(s));
    }

    @Test
    public void testToCodePoints_2_oe() {
        final int orphanedHighSurrogate = 0xD801;
        final int orphanedLowSurrogate = 0xDC00;
        final int supplementary = 0x2070E;

        final int[] codePoints = {'a', orphanedHighSurrogate, 'b', 'c', supplementary,
                'd', orphanedLowSurrogate, 'e'};
        final String s = new String(codePoints, 0, codePoints.length);

        assertNull(StringUtils.toCodePoints(null));
    }

    @Test
    public void testToCodePoints_3_oe() {
        final int orphanedHighSurrogate = 0xD801;
        final int orphanedLowSurrogate = 0xDC00;
        final int supplementary = 0x2070E;

        final int[] codePoints = {'a', orphanedHighSurrogate, 'b', 'c', supplementary,
                'd', orphanedLowSurrogate, 'e'};
        final String s = new String(codePoints, 0, codePoints.length);

        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, StringUtils.toCodePoints(""));
    }

    @Test
    public void testToEncodedString_1_oe() {
        final String expectedString = "The quick brown fox jumps over the lazy dog.";
        String encoding = SystemUtils.FILE_ENCODING;
        byte[] expectedBytes = expectedString.getBytes(Charset.defaultCharset());
        assertArrayEquals(expectedBytes, expectedString.getBytes());
    }

    @Test
    public void testToEncodedString_2_oe() {
        final String expectedString = "The quick brown fox jumps over the lazy dog.";
        String encoding = SystemUtils.FILE_ENCODING;
        byte[] expectedBytes = expectedString.getBytes(Charset.defaultCharset());
        assertEquals(expectedString, StringUtils.toEncodedString(expectedBytes, Charset.defaultCharset()));
    }

    @Test
    public void testToEncodedString_3_oe() {
        final String expectedString = "The quick brown fox jumps over the lazy dog.";
        String encoding = SystemUtils.FILE_ENCODING;
        byte[] expectedBytes = expectedString.getBytes(Charset.defaultCharset());
        assertEquals(expectedString, StringUtils.toEncodedString(expectedBytes, Charset.forName(encoding)));
    }

    @Test
    public void testToEncodedString_4_oe() {
        final String expectedString = "The quick brown fox jumps over the lazy dog.";
        String encoding = SystemUtils.FILE_ENCODING;
        byte[] expectedBytes = expectedString.getBytes(Charset.defaultCharset());
        encoding = "UTF-16";
        expectedBytes = expectedString.getBytes(Charset.forName(encoding));
        assertEquals(expectedString, StringUtils.toEncodedString(expectedBytes, Charset.forName(encoding)));
    }

    @Test
    public void testToString_1_oe() throws UnsupportedEncodingException {
        final String expectedString = "The quick brown fox jumps over the lazy dog.";
        byte[] expectedBytes = expectedString.getBytes(Charset.defaultCharset());
        assertArrayEquals(expectedBytes, expectedString.getBytes());
    }

    @Test
    public void testToString_2_oe() throws UnsupportedEncodingException {
        final String expectedString = "The quick brown fox jumps over the lazy dog.";
        byte[] expectedBytes = expectedString.getBytes(Charset.defaultCharset());
        assertEquals(expectedString, StringUtils.toString(expectedBytes, null));
    }

    @Test
    public void testToString_3_oe() throws UnsupportedEncodingException {
        final String expectedString = "The quick brown fox jumps over the lazy dog.";
        byte[] expectedBytes = expectedString.getBytes(Charset.defaultCharset());
        assertEquals(expectedString, StringUtils.toString(expectedBytes, SystemUtils.FILE_ENCODING));
    }

    @Test
    public void testToString_4_oe() throws UnsupportedEncodingException {
        final String expectedString = "The quick brown fox jumps over the lazy dog.";
        byte[] expectedBytes = expectedString.getBytes(Charset.defaultCharset());
        final String encoding = "UTF-16";
        expectedBytes = expectedString.getBytes(Charset.forName(encoding));
        assertEquals(expectedString, StringUtils.toString(expectedBytes, encoding));
    }

    @Test
    public void testTruncate_StringInt_1_oe() {
        assertNull(StringUtils.truncate(null, 12));
    }

    @Test
    public void testTruncate_StringInt_2_oe() throws Exception {
        try {
    StringUtils.truncate(null, -1);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_3_oe() throws Exception {
        try {
    StringUtils.truncate(null, -10);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_4_oe() throws Exception {
        try {
    StringUtils.truncate(null, Integer.MIN_VALUE);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_5_oe() {
        assertEquals("", StringUtils.truncate("", 10));
    }

    @Test
    public void testTruncate_StringInt_6_oe() {
        assertEquals("", StringUtils.truncate("", 10));
    }

    @Test
    public void testTruncate_StringInt_7_oe() {
        assertEquals("abc", StringUtils.truncate("abcdefghij", 3));
    }

    @Test
    public void testTruncate_StringInt_8_oe() {
        assertEquals("abcdef", StringUtils.truncate("abcdefghij", 6));
    }

    @Test
    public void testTruncate_StringInt_9_oe() {
        assertEquals("", StringUtils.truncate("abcdefghij", 0));
    }

    @Test
    public void testTruncate_StringInt_10_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", -1);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_11_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", -100);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_12_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", Integer.MIN_VALUE);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_13_oe() {
        assertEquals("abcdefghij", StringUtils.truncate("abcdefghijklmno", 10));
    }

    @Test
    public void testTruncate_StringInt_14_oe() {
        assertEquals("abcdefghijklmno", StringUtils.truncate("abcdefghijklmno", Integer.MAX_VALUE));
    }

    @Test
    public void testTruncate_StringInt_15_oe() {
        assertEquals("abcde", StringUtils.truncate("abcdefghijklmno", 5));
    }

    @Test
    public void testTruncate_StringInt_16_oe() {
        assertEquals("abc", StringUtils.truncate("abcdefghijklmno", 3));
    }

    @Test
    public void testTruncate_StringIntInt_1_oe() {
        assertNull(StringUtils.truncate(null, 0, 12));
    }

    @Test
    public void testTruncate_StringIntInt_2_oe() throws Exception {
        try {
    StringUtils.truncate(null, -1, 0);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_3_oe() throws Exception {
        try {
    StringUtils.truncate(null, -10, -4);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_4_oe() throws Exception {
        try {
    StringUtils.truncate(null, Integer.MIN_VALUE, Integer.MIN_VALUE);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_5_oe() {
        assertNull(StringUtils.truncate(null, 10, 12));
    }

    @Test
    public void testTruncate_StringIntInt_6_oe() {
        assertEquals("", StringUtils.truncate("", 0, 10));
    }

    @Test
    public void testTruncate_StringIntInt_7_oe() {
        assertEquals("", StringUtils.truncate("", 2, 10));
    }

    @Test
    public void testTruncate_StringIntInt_8_oe() {
        assertEquals("abc", StringUtils.truncate("abcdefghij", 0, 3));
    }

    @Test
    public void testTruncate_StringIntInt_9_oe() {
        assertEquals("fghij", StringUtils.truncate("abcdefghij", 5, 6));
    }

    @Test
    public void testTruncate_StringIntInt_10_oe() {
        assertEquals("", StringUtils.truncate("abcdefghij", 0, 0));
    }

    @Test
    public void testTruncate_StringIntInt_11_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", 0, -1);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_12_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", 0, -10);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_13_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", 0, -100);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_14_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", 1, -100);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_15_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", 0, Integer.MIN_VALUE);
    fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_16_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", -1, 0);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_17_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", -10, 0);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_18_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", -100, 1);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_19_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", Integer.MIN_VALUE, 0);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_20_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", -1, -1);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_21_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", -10, -10);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_22_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", -100, -100);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_23_oe() throws Exception {
        try {
    StringUtils.truncate("abcdefghij", Integer.MIN_VALUE, Integer.MIN_VALUE);
    fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_24_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("peach", StringUtils.truncate(raspberry, 10, 15));
    }

    @Test
    public void testTruncate_StringIntInt_25_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("abcdefghij", StringUtils.truncate("abcdefghijklmno", 0, 10));
    }

    @Test
    public void testTruncate_StringIntInt_26_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("abcdefghijklmno", StringUtils.truncate("abcdefghijklmno", 0, Integer.MAX_VALUE));
    }

    @Test
    public void testTruncate_StringIntInt_27_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("bcdefghijk", StringUtils.truncate("abcdefghijklmno", 1, 10));
    }

    @Test
    public void testTruncate_StringIntInt_28_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("cdefghijkl", StringUtils.truncate("abcdefghijklmno", 2, 10));
    }

    @Test
    public void testTruncate_StringIntInt_29_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("defghijklm", StringUtils.truncate("abcdefghijklmno", 3, 10));
    }

    @Test
    public void testTruncate_StringIntInt_30_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("efghijklmn", StringUtils.truncate("abcdefghijklmno", 4, 10));
    }

    @Test
    public void testTruncate_StringIntInt_31_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("fghijklmno", StringUtils.truncate("abcdefghijklmno", 5, 10));
    }

    @Test
    public void testTruncate_StringIntInt_32_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("fghij", StringUtils.truncate("abcdefghijklmno", 5, 5));
    }

    @Test
    public void testTruncate_StringIntInt_33_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("fgh", StringUtils.truncate("abcdefghijklmno", 5, 3));
    }

    @Test
    public void testTruncate_StringIntInt_34_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("klm", StringUtils.truncate("abcdefghijklmno", 10, 3));
    }

    @Test
    public void testTruncate_StringIntInt_35_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("klmno", StringUtils.truncate("abcdefghijklmno", 10, Integer.MAX_VALUE));
    }

    @Test
    public void testTruncate_StringIntInt_36_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("n", StringUtils.truncate("abcdefghijklmno", 13, 1));
    }

    @Test
    public void testTruncate_StringIntInt_37_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("no", StringUtils.truncate("abcdefghijklmno", 13, Integer.MAX_VALUE));
    }

    @Test
    public void testTruncate_StringIntInt_38_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("o", StringUtils.truncate("abcdefghijklmno", 14, 1));
    }

    @Test
    public void testTruncate_StringIntInt_39_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("o", StringUtils.truncate("abcdefghijklmno", 14, Integer.MAX_VALUE));
    }

    @Test
    public void testTruncate_StringIntInt_40_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("", StringUtils.truncate("abcdefghijklmno", 15, 1));
    }

    @Test
    public void testTruncate_StringIntInt_41_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("", StringUtils.truncate("abcdefghijklmno", 15, Integer.MAX_VALUE));
    }

    @Test
    public void testTruncate_StringIntInt_42_oe() {
        final String raspberry = "raspberry peach";
        assertEquals("", StringUtils.truncate("abcdefghijklmno", Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testUnCapitalize_1_oe() {
        assertNull(StringUtils.uncapitalize(null));
    }

    @Test
    public void testUnCapitalize_2_oe() {

        assertEquals(FOO_UNCAP, StringUtils.uncapitalize(FOO_CAP), "uncapitalize(String) failed");
    }

    @Test
    public void testUnCapitalize_3_oe() {

        assertEquals(FOO_UNCAP, StringUtils.uncapitalize(FOO_UNCAP), "uncapitalize(string) failed");
    }

    @Test
    public void testUnCapitalize_4_oe() {

        assertEquals("", StringUtils.uncapitalize(""), "uncapitalize(empty-string) failed");
    }

    @Test
    public void testUnCapitalize_5_oe() {

        assertEquals("x", StringUtils.uncapitalize("X"), "uncapitalize(single-char-string) failed");
    }

    @Test
    public void testUnCapitalize_6_oe() {


        assertEquals("cat", StringUtils.uncapitalize("cat"));
    }

    @Test
    public void testUnCapitalize_7_oe() {


        assertEquals("cat", StringUtils.uncapitalize("Cat"));
    }

    @Test
    public void testUnCapitalize_8_oe() {


        assertEquals("cAT", StringUtils.uncapitalize("CAT"));
    }

    @Test
    public void testUnescapeSurrogatePairs_1_oe() {
        assertEquals("\uD83D\uDE30", StringEscapeUtils.unescapeCsv("\uD83D\uDE30"));
    }

    @Test
    public void testUnescapeSurrogatePairs_2_oe() {
        assertEquals("\uD800\uDC00", StringEscapeUtils.unescapeCsv("\uD800\uDC00"));
    }

    @Test
    public void testUnescapeSurrogatePairs_3_oe() {
        assertEquals("\uD834\uDD1E", StringEscapeUtils.unescapeCsv("\uD834\uDD1E"));
    }

    @Test
    public void testUnescapeSurrogatePairs_4_oe() {
        assertEquals("\uDBFF\uDFFD", StringEscapeUtils.unescapeCsv("\uDBFF\uDFFD"));
    }

    @Test
    public void testUnescapeSurrogatePairs_5_oe() {
        assertEquals("\uDBFF\uDFFD", StringEscapeUtils.unescapeHtml3("\uDBFF\uDFFD"));
    }

    @Test
    public void testUnescapeSurrogatePairs_6_oe() {
        assertEquals("\uDBFF\uDFFD", StringEscapeUtils.unescapeHtml4("\uDBFF\uDFFD"));
    }

    @Test
    public void testUnwrap_StringChar_1_oe() {
        assertNull(StringUtils.unwrap(null, null));
    }

    @Test
    public void testUnwrap_StringChar_2_oe() {
        assertNull(StringUtils.unwrap(null, CharUtils.NUL));
    }

    @Test
    public void testUnwrap_StringChar_3_oe() {
        assertNull(StringUtils.unwrap(null, '1'));
    }

    @Test
    public void testUnwrap_StringChar_4_oe() {

        assertEquals("abc", StringUtils.unwrap("abc", null));
    }

    @Test
    public void testUnwrap_StringChar_5_oe() {

        assertEquals("a", StringUtils.unwrap("a", "a"));
    }

    @Test
    public void testUnwrap_StringChar_6_oe() {

        assertEquals("", StringUtils.unwrap("aa", "a"));
    }

    @Test
    public void testUnwrap_StringChar_7_oe() {

        assertEquals("abc", StringUtils.unwrap("\'abc\'", '\''));
    }

    @Test
    public void testUnwrap_StringChar_8_oe() {

        assertEquals("abc", StringUtils.unwrap("AabcA", 'A'));
    }

    @Test
    public void testUnwrap_StringChar_9_oe() {

        assertEquals("AabcA", StringUtils.unwrap("AAabcAA", 'A'));
    }

    @Test
    public void testUnwrap_StringChar_10_oe() {

        assertEquals("abc", StringUtils.unwrap("abc", 'b'));
    }

    @Test
    public void testUnwrap_StringChar_11_oe() {

        assertEquals("#A", StringUtils.unwrap("#A", '#'));
    }

    @Test
    public void testUnwrap_StringChar_12_oe() {

        assertEquals("A#", StringUtils.unwrap("A#", '#'));
    }

    @Test
    public void testUnwrap_StringChar_13_oe() {

        assertEquals("ABA", StringUtils.unwrap("AABAA", 'A'));
    }

    @Test
    public void testUnwrap_StringString_1_oe() {
        assertNull(StringUtils.unwrap(null, null));
    }

    @Test
    public void testUnwrap_StringString_2_oe() {
        assertNull(StringUtils.unwrap(null, ""));
    }

    @Test
    public void testUnwrap_StringString_3_oe() {
        assertNull(StringUtils.unwrap(null, "1"));
    }

    @Test
    public void testUnwrap_StringString_4_oe() {

        assertEquals("abc", StringUtils.unwrap("abc", null));
    }

    @Test
    public void testUnwrap_StringString_5_oe() {

        assertEquals("abc", StringUtils.unwrap("abc", ""));
    }

    @Test
    public void testUnwrap_StringString_6_oe() {

        assertEquals("a", StringUtils.unwrap("a", "a"));
    }

    @Test
    public void testUnwrap_StringString_7_oe() {

        assertEquals("ababa", StringUtils.unwrap("ababa", "aba"));
    }

    @Test
    public void testUnwrap_StringString_8_oe() {

        assertEquals("", StringUtils.unwrap("aa", "a"));
    }

    @Test
    public void testUnwrap_StringString_9_oe() {

        assertEquals("abc", StringUtils.unwrap("\'abc\'", "\'"));
    }

    @Test
    public void testUnwrap_StringString_10_oe() {

        assertEquals("abc", StringUtils.unwrap("\"abc\"", "\""));
    }

    @Test
    public void testUnwrap_StringString_11_oe() {

        assertEquals("abc\"xyz", StringUtils.unwrap("\"abc\"xyz\"", "\""));
    }

    @Test
    public void testUnwrap_StringString_12_oe() {

        assertEquals("abc\"xyz\"", StringUtils.unwrap("\"abc\"xyz\"\"", "\""));
    }

    @Test
    public void testUnwrap_StringString_13_oe() {

        assertEquals("abc\'xyz\'", StringUtils.unwrap("\"abc\'xyz\'\"", "\""));
    }

    @Test
    public void testUnwrap_StringString_14_oe() {

        assertEquals("\"abc\'xyz\'\"", StringUtils.unwrap("AA\"abc\'xyz\'\"AA", "AA"));
    }

    @Test
    public void testUnwrap_StringString_15_oe() {

        assertEquals("\"abc\'xyz\'\"", StringUtils.unwrap("123\"abc\'xyz\'\"123", "123"));
    }

    @Test
    public void testUnwrap_StringString_16_oe() {

        assertEquals("AA\"abc\'xyz\'\"", StringUtils.unwrap("AA\"abc\'xyz\'\"", "AA"));
    }

    @Test
    public void testUnwrap_StringString_17_oe() {

        assertEquals("AA\"abc\'xyz\'\"AA", StringUtils.unwrap("AAA\"abc\'xyz\'\"AAA", "A"));
    }

    @Test
    public void testUnwrap_StringString_18_oe() {

        assertEquals("\"abc\'xyz\'\"AA", StringUtils.unwrap("\"abc\'xyz\'\"AA", "AA"));
    }

    @Test
    public void testUpperCase_1_oe() {
        assertNull(StringUtils.upperCase(null));
    }

    @Test
    public void testUpperCase_2_oe() {
        assertNull(StringUtils.upperCase(null, Locale.ENGLISH));
    }

    @Test
    public void testUpperCase_3_oe() {
        assertEquals("FOO TEST THING", StringUtils.upperCase("fOo test THING"), "upperCase(String) failed");
    }

    @Test
    public void testUpperCase_4_oe() {
        assertEquals("", StringUtils.upperCase(""), "upperCase(empty-string) failed");
    }

    @Test
    public void testUpperCase_5_oe() {
        assertEquals("FOO TEST THING",StringUtils.upperCase("fOo test THING",Locale.ENGLISH),"upperCase(String,Locale)failed");
    }

    @Test
    public void testUpperCase_6_oe() {
        assertEquals("",StringUtils.upperCase("",Locale.ENGLISH),"upperCase(empty-string,Locale)failed");
    }

    @Test
    public void testWrap_StringChar_1_oe() {
        assertNull(StringUtils.wrap(null, CharUtils.NUL));
    }

    @Test
    public void testWrap_StringChar_2_oe() {
        assertNull(StringUtils.wrap(null, '1'));
    }

    @Test
    public void testWrap_StringChar_3_oe() {

        assertEquals("", StringUtils.wrap("", CharUtils.NUL));
    }

    @Test
    public void testWrap_StringChar_4_oe() {

        assertEquals("xabx", StringUtils.wrap("ab", 'x'));
    }

    @Test
    public void testWrap_StringChar_5_oe() {

        assertEquals("\"ab\"", StringUtils.wrap("ab", '\"'));
    }

    @Test
    public void testWrap_StringChar_6_oe() {

        assertEquals("\"\"ab\"\"", StringUtils.wrap("\"ab\"", '\"'));
    }

    @Test
    public void testWrap_StringChar_7_oe() {

        assertEquals("'ab'", StringUtils.wrap("ab", '\''));
    }

    @Test
    public void testWrap_StringChar_8_oe() {

        assertEquals("''abcd''", StringUtils.wrap("'abcd'", '\''));
    }

    @Test
    public void testWrap_StringChar_9_oe() {

        assertEquals("'\"abcd\"'", StringUtils.wrap("\"abcd\"", '\''));
    }

    @Test
    public void testWrap_StringChar_10_oe() {

        assertEquals("\"'abcd'\"", StringUtils.wrap("'abcd'", '\"'));
    }

    @Test
    public void testWrap_StringString_1_oe() {
        assertNull(StringUtils.wrap(null, null));
    }

    @Test
    public void testWrap_StringString_2_oe() {
        assertNull(StringUtils.wrap(null, ""));
    }

    @Test
    public void testWrap_StringString_3_oe() {
        assertNull(StringUtils.wrap(null, "1"));
    }

    @Test
    public void testWrap_StringString_4_oe() {

        assertNull(StringUtils.wrap(null, null));
    }

    @Test
    public void testWrap_StringString_5_oe() {

        assertEquals("", StringUtils.wrap("", ""));
    }

    @Test
    public void testWrap_StringString_6_oe() {

        assertEquals("ab", StringUtils.wrap("ab", null));
    }

    @Test
    public void testWrap_StringString_7_oe() {

        assertEquals("xabx", StringUtils.wrap("ab", "x"));
    }

    @Test
    public void testWrap_StringString_8_oe() {

        assertEquals("\"ab\"", StringUtils.wrap("ab", "\""));
    }

    @Test
    public void testWrap_StringString_9_oe() {

        assertEquals("\"\"ab\"\"", StringUtils.wrap("\"ab\"", "\""));
    }

    @Test
    public void testWrap_StringString_10_oe() {

        assertEquals("'ab'", StringUtils.wrap("ab", "'"));
    }

    @Test
    public void testWrap_StringString_11_oe() {

        assertEquals("''abcd''", StringUtils.wrap("'abcd'", "'"));
    }

    @Test
    public void testWrap_StringString_12_oe() {

        assertEquals("'\"abcd\"'", StringUtils.wrap("\"abcd\"", "'"));
    }

    @Test
    public void testWrap_StringString_13_oe() {

        assertEquals("\"'abcd'\"", StringUtils.wrap("'abcd'", "\""));
    }

    @Test
    public void testWrapIfMissing_StringChar_1_oe() {
        assertNull(StringUtils.wrapIfMissing(null, CharUtils.NUL));
    }

    @Test
    public void testWrapIfMissing_StringChar_2_oe() {
        assertNull(StringUtils.wrapIfMissing(null, '1'));
    }

    @Test
    public void testWrapIfMissing_StringChar_3_oe() {

        assertEquals("", StringUtils.wrapIfMissing("", CharUtils.NUL));
    }

    @Test
    public void testWrapIfMissing_StringChar_4_oe() {

        assertEquals("xabx", StringUtils.wrapIfMissing("ab", 'x'));
    }

    @Test
    public void testWrapIfMissing_StringChar_5_oe() {

        assertEquals("\"ab\"", StringUtils.wrapIfMissing("ab", '\"'));
    }

    @Test
    public void testWrapIfMissing_StringChar_6_oe() {

        assertEquals("\"ab\"", StringUtils.wrapIfMissing("\"ab\"", '\"'));
    }

    @Test
    public void testWrapIfMissing_StringChar_7_oe() {

        assertEquals("'ab'", StringUtils.wrapIfMissing("ab", '\''));
    }

    @Test
    public void testWrapIfMissing_StringChar_8_oe() {

        assertEquals("'abcd'", StringUtils.wrapIfMissing("'abcd'", '\''));
    }

    @Test
    public void testWrapIfMissing_StringChar_9_oe() {

        assertEquals("'\"abcd\"'", StringUtils.wrapIfMissing("\"abcd\"", '\''));
    }

    @Test
    public void testWrapIfMissing_StringChar_10_oe() {

        assertEquals("\"'abcd'\"", StringUtils.wrapIfMissing("'abcd'", '\"'));
    }

    @Test
    public void testWrapIfMissing_StringChar_11_oe() {

        assertEquals("/x/", StringUtils.wrapIfMissing("x", '/'));
    }

    @Test
    public void testWrapIfMissing_StringChar_12_oe() {

        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z", '/'));
    }

    @Test
    public void testWrapIfMissing_StringChar_13_oe() {

        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("/x/y/z", '/'));
    }

    @Test
    public void testWrapIfMissing_StringChar_14_oe() {

        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z/", '/'));
    }

    @Test
    public void testWrapIfMissing_StringChar_15_oe() {


        assertSame("/", StringUtils.wrapIfMissing("/", '/'));
    }

    @Test
    public void testWrapIfMissing_StringChar_16_oe() {


        assertSame("/x/", StringUtils.wrapIfMissing("/x/", '/'));
    }

    @Test
    public void testWrapIfMissing_StringString_1_oe() {
        assertNull(StringUtils.wrapIfMissing(null, "\0"));
    }

    @Test
    public void testWrapIfMissing_StringString_2_oe() {
        assertNull(StringUtils.wrapIfMissing(null, "1"));
    }

    @Test
    public void testWrapIfMissing_StringString_3_oe() {

        assertEquals("", StringUtils.wrapIfMissing("", "\0"));
    }

    @Test
    public void testWrapIfMissing_StringString_4_oe() {

        assertEquals("xabx", StringUtils.wrapIfMissing("ab", "x"));
    }

    @Test
    public void testWrapIfMissing_StringString_5_oe() {

        assertEquals("\"ab\"", StringUtils.wrapIfMissing("ab", "\""));
    }

    @Test
    public void testWrapIfMissing_StringString_6_oe() {

        assertEquals("\"ab\"", StringUtils.wrapIfMissing("\"ab\"", "\""));
    }

    @Test
    public void testWrapIfMissing_StringString_7_oe() {

        assertEquals("'ab'", StringUtils.wrapIfMissing("ab", "\'"));
    }

    @Test
    public void testWrapIfMissing_StringString_8_oe() {

        assertEquals("'abcd'", StringUtils.wrapIfMissing("'abcd'", "\'"));
    }

    @Test
    public void testWrapIfMissing_StringString_9_oe() {

        assertEquals("'\"abcd\"'", StringUtils.wrapIfMissing("\"abcd\"", "\'"));
    }

    @Test
    public void testWrapIfMissing_StringString_10_oe() {

        assertEquals("\"'abcd'\"", StringUtils.wrapIfMissing("'abcd'", "\""));
    }

    @Test
    public void testWrapIfMissing_StringString_11_oe() {

        assertEquals("/x/", StringUtils.wrapIfMissing("x", "/"));
    }

    @Test
    public void testWrapIfMissing_StringString_12_oe() {

        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z", "/"));
    }

    @Test
    public void testWrapIfMissing_StringString_13_oe() {

        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("/x/y/z", "/"));
    }

    @Test
    public void testWrapIfMissing_StringString_14_oe() {

        assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z/", "/"));
    }

    @Test
    public void testWrapIfMissing_StringString_15_oe() {

        assertEquals("/", StringUtils.wrapIfMissing("/", "/"));
    }

    @Test
    public void testWrapIfMissing_StringString_16_oe() {

        assertEquals("ab/ab", StringUtils.wrapIfMissing("/", "ab"));
    }

    @Test
    public void testWrapIfMissing_StringString_17_oe() {


        assertSame("ab/ab", StringUtils.wrapIfMissing("ab/ab", "ab"));
    }

    @Test
    public void testWrapIfMissing_StringString_18_oe() {


        assertSame("//x//", StringUtils.wrapIfMissing("//x//", "//"));
    }

    @Test
    public void testToRootLowerCase_1_oe() {
        assertEquals(null, StringUtils.toRootLowerCase(null));
    }

    @Test
    public void testToRootLowerCase_2_oe() {
        assertEquals("a", StringUtils.toRootLowerCase("A"));
    }

    @Test
    public void testToRootLowerCase_3_oe() {
        assertEquals("a", StringUtils.toRootLowerCase("a"));
    }

    @Test
    public void testToRootLowerCase_4_oe() {
        final Locale TURKISH = Locale.forLanguageTag("tr");
        assertNotEquals("title", "TITLE".toLowerCase(TURKISH));
    }

    @Test
    public void testToRootLowerCase_5_oe() {
        final Locale TURKISH = Locale.forLanguageTag("tr");
        assertEquals("title", "TITLE".toLowerCase(Locale.ROOT));
    }

    @Test
    public void testToRootLowerCase_6_oe() {
        final Locale TURKISH = Locale.forLanguageTag("tr");
        assertEquals("title", StringUtils.toRootLowerCase("TITLE"));
    }

    @Test
    public void testToRootUpperCase_1_oe() {
        assertEquals(null, StringUtils.toRootUpperCase(null));
    }

    @Test
    public void testToRootUpperCase_2_oe() {
        assertEquals("A", StringUtils.toRootUpperCase("a"));
    }

    @Test
    public void testToRootUpperCase_3_oe() {
        assertEquals("A", StringUtils.toRootUpperCase("A"));
    }

    @Test
    public void testToRootUpperCase_4_oe() {
        final Locale TURKISH = Locale.forLanguageTag("tr");
        assertNotEquals("TITLE", "title".toUpperCase(TURKISH));
    }

    @Test
    public void testToRootUpperCase_5_oe() {
        final Locale TURKISH = Locale.forLanguageTag("tr");
        assertEquals("TITLE", "title".toUpperCase(Locale.ROOT));
    }

    @Test
    public void testToRootUpperCase_6_oe() {
        final Locale TURKISH = Locale.forLanguageTag("tr");
        assertEquals("TITLE", StringUtils.toRootUpperCase("title"));
    }

    @Test
    public void testGeorgianSample_1_oe() {
        final char[] arrayI = new char[]{
                (char) 0x0131,
                (char) 0x03F4
        };
        final char[] arrayJ = new char[]{
                (char) 0x0130,
                (char) 0x03D1
        };
        for (final char i : arrayI) {
            for (final char j : arrayJ) {
                final String si = String.valueOf(i);
                final String sj = String.valueOf(j);
                final boolean res1 = si.equalsIgnoreCase(sj);
                final CharSequence ci = new StringBuilder(si);
                final CharSequence cj = new StringBuilder(sj);
                boolean res2 = StringUtils.startsWithIgnoreCase(ci, cj);
                assertEquals(res1, res2, "si : " + si + " sj : " + sj);
    }
    }
    }

    @Test
    public void testGeorgianSample_2_oe() {
        final char[] arrayI = new char[]{
                (char) 0x0131,
                (char) 0x03F4
        };
        final char[] arrayJ = new char[]{
                (char) 0x0130,
                (char) 0x03D1
        };
        for (final char i : arrayI) {
            for (final char j : arrayJ) {
                final String si = String.valueOf(i);
                final String sj = String.valueOf(j);
                final boolean res1 = si.equalsIgnoreCase(sj);
                final CharSequence ci = new StringBuilder(si);
                final CharSequence cj = new StringBuilder(sj);
                boolean res2 = StringUtils.startsWithIgnoreCase(ci, cj);
                res2 = StringUtils.endsWithIgnoreCase(ci, cj);
                assertEquals(res1, res2, "si : " + si + " sj : " + sj);
    }
    }
    }

    @Test
    public void testGeorgianSample_3_oe() {
        final char[] arrayI = new char[]{
                (char) 0x0131,
                (char) 0x03F4
        };
        final char[] arrayJ = new char[]{
                (char) 0x0130,
                (char) 0x03D1
        };
        for (final char i : arrayI) {
            for (final char j : arrayJ) {
                final String si = String.valueOf(i);
                final String sj = String.valueOf(j);
                final boolean res1 = si.equalsIgnoreCase(sj);
                final CharSequence ci = new StringBuilder(si);
                final CharSequence cj = new StringBuilder(sj);
                boolean res2 = StringUtils.startsWithIgnoreCase(ci, cj);
                res2 = StringUtils.endsWithIgnoreCase(ci, cj);
                res2 = StringUtils.compareIgnoreCase(ci.toString(), cj.toString()) == 0;
                assertEquals(res1, res2, "si : " + si + " sj : " + sj);
    }
    }
    }

    @Test
    public void testGeorgianSample_4_oe() {
        final char[] arrayI = new char[]{
                (char) 0x0131,
                (char) 0x03F4
        };
        final char[] arrayJ = new char[]{
                (char) 0x0130,
                (char) 0x03D1
        };
        for (final char i : arrayI) {
            for (final char j : arrayJ) {
                final String si = String.valueOf(i);
                final String sj = String.valueOf(j);
                final boolean res1 = si.equalsIgnoreCase(sj);
                final CharSequence ci = new StringBuilder(si);
                final CharSequence cj = new StringBuilder(sj);
                boolean res2 = StringUtils.startsWithIgnoreCase(ci, cj);
                res2 = StringUtils.endsWithIgnoreCase(ci, cj);
                res2 = StringUtils.compareIgnoreCase(ci.toString(), cj.toString()) == 0;
                res2 = StringUtils.indexOfIgnoreCase(ci.toString(), cj.toString()) == 0;
                assertEquals(res1, res2, "si : " + si + " sj : " + sj);
    }
    }
    }

    @Test
    public void testGeorgianSample_5_oe() {
        final char[] arrayI = new char[]{
                (char) 0x0131,
                (char) 0x03F4
        };
        final char[] arrayJ = new char[]{
                (char) 0x0130,
                (char) 0x03D1
        };
        for (final char i : arrayI) {
            for (final char j : arrayJ) {
                final String si = String.valueOf(i);
                final String sj = String.valueOf(j);
                final boolean res1 = si.equalsIgnoreCase(sj);
                final CharSequence ci = new StringBuilder(si);
                final CharSequence cj = new StringBuilder(sj);
                boolean res2 = StringUtils.startsWithIgnoreCase(ci, cj);
                res2 = StringUtils.endsWithIgnoreCase(ci, cj);
                res2 = StringUtils.compareIgnoreCase(ci.toString(), cj.toString()) == 0;
                res2 = StringUtils.indexOfIgnoreCase(ci.toString(), cj.toString()) == 0;
                res2 = StringUtils.lastIndexOfIgnoreCase(ci.toString(), cj.toString()) == 0;
                assertEquals(res1, res2, "si : " + si + " sj : " + sj);
    }
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_8_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = -1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_8_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = -1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_8_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = -1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringIntInt_9_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 0;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_9_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 0;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_9_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 0;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringIntInt_10_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_10_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_10_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringIntInt_11_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 2;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_11_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 2;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_11_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 2;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringIntInt_12_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 3;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_12_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 3;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_12_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 3;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringIntInt_13_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 4;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_13_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 4;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_13_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefg...";
        final int offset0 = 4;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringIntInt_14_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...fghi...";
        final int offset0 = 5;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_14_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...fghi...";
        final int offset0 = 5;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_14_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...fghi...";
        final int offset0 = 5;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringIntInt_15_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ghij...";
        final int offset0 = 6;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_15_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ghij...";
        final int offset0 = 6;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_15_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ghij...";
        final int offset0 = 6;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringIntInt_16_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...hijk...";
        final int offset0 = 7;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_16_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...hijk...";
        final int offset0 = 7;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_16_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...hijk...";
        final int offset0 = 7;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringIntInt_17_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 8;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_17_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 8;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_18_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 9;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_18_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 9;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_19_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 10;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_19_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 10;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_20_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 10;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_20_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 10;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_21_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 11;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_21_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 11;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_22_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 12;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_22_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 12;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_23_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 13;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_23_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 13;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_24_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 14;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_24_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 14;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_25_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 15;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_26_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 16;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_26_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = 16;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringIntInt_27_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = Integer.MAX_VALUE;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringIntInt_27_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final int offset0 = Integer.MAX_VALUE;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_9_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefgh;;";
        final String abbrevMarker0 = ";;";
        final int offset0 = -1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_9_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefgh;;";
        final String abbrevMarker0 = ";;";
        final int offset0 = -1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_9_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefgh;;";
        final String abbrevMarker0 = ";;";
        final int offset0 = -1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringStringIntInt_10_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefghi.";
        final String abbrevMarker0 = ".";
        final int offset0 = 0;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_10_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefghi.";
        final String abbrevMarker0 = ".";
        final int offset0 = 0;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_10_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefghi.";
        final String abbrevMarker0 = ".";
        final int offset0 = 0;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringStringIntInt_11_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefgh++";
        final String abbrevMarker0 = "++";
        final int offset0 = 1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_11_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefgh++";
        final String abbrevMarker0 = "++";
        final int offset0 = 1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_11_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefgh++";
        final String abbrevMarker0 = "++";
        final int offset0 = 1;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringStringIntInt_12_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefghi*";
        final String abbrevMarker0 = "*";
        final int offset0 = 2;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_12_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefghi*";
        final String abbrevMarker0 = "*";
        final int offset0 = 2;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_12_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdefghi*";
        final String abbrevMarker0 = "*";
        final int offset0 = 2;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringStringIntInt_14_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdef____";
        final String abbrevMarker0 = "____";
        final int offset0 = 5;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_14_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdef____";
        final String abbrevMarker0 = "____";
        final int offset0 = 5;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_14_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "abcdef____";
        final String abbrevMarker0 = "____";
        final int offset0 = 5;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringStringIntInt_15_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "==fghijk==";
        final String abbrevMarker0 = "==";
        final int offset0 = 5;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_15_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "==fghijk==";
        final String abbrevMarker0 = "==";
        final int offset0 = 5;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_15_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "==fghijk==";
        final String abbrevMarker0 = "==";
        final int offset0 = 5;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringStringIntInt_16_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "___ghij___";
        final String abbrevMarker0 = "___";
        final int offset0 = 6;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_16_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "___ghij___";
        final String abbrevMarker0 = "___";
        final int offset0 = 6;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_16_oe_3_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "___ghij___";
        final String abbrevMarker0 = "___";
        final int offset0 = 6;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertEquals(expected0, actual0, message0);
    }

    @Test
    public void testAbbreviate_StringStringIntInt_17_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "/ghijklmno";
        final String abbrevMarker0 = "/";
        final int offset0 = 7;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_17_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "/ghijklmno";
        final String abbrevMarker0 = "/";
        final int offset0 = 7;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_18_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "/ghijklmno";
        final String abbrevMarker0 = "/";
        final int offset0 = 8;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_18_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "/ghijklmno";
        final String abbrevMarker0 = "/";
        final int offset0 = 8;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_19_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "/ghijklmno";
        final String abbrevMarker0 = "/";
        final int offset0 = 9;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_19_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "/ghijklmno";
        final String abbrevMarker0 = "/";
        final int offset0 = 9;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_20_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "///ijklmno";
        final String abbrevMarker0 = "///";
        final int offset0 = 10;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_20_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "///ijklmno";
        final String abbrevMarker0 = "///";
        final int offset0 = 10;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_21_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "//hijklmno";
        final String abbrevMarker0 = "//";
        final int offset0 = 10;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_21_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "//hijklmno";
        final String abbrevMarker0 = "//";
        final int offset0 = 10;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_22_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "//hijklmno";
        final String abbrevMarker0 = "//";
        final int offset0 = 11;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_22_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "//hijklmno";
        final String abbrevMarker0 = "//";
        final int offset0 = 11;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_23_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final String abbrevMarker0 = "...";
        final int offset0 = 12;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_23_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "...ijklmno";
        final String abbrevMarker0 = "...";
        final int offset0 = 12;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_24_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "/ghijklmno";
        final String abbrevMarker0 = "/";
        final int offset0 = 13;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_24_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "/ghijklmno";
        final String abbrevMarker0 = "/";
        final int offset0 = 13;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_25_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "/ghijklmno";
        final String abbrevMarker0 = "/";
        final int offset0 = 14;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_25_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "/ghijklmno";
        final String abbrevMarker0 = "/";
        final int offset0 = 14;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_26_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "999ijklmno";
        final String abbrevMarker0 = "999";
        final int offset0 = 15;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_27_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "_ghijklmno";
        final String abbrevMarker0 = "_";
        final int offset0 = 16;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_27_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "_ghijklmno";
        final String abbrevMarker0 = "_";
        final int offset0 = 16;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

    @Test
    public void testAbbreviate_StringStringIntInt_28_oe_1_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "+ghijklmno";
        final String abbrevMarker0 = "+";
        final int offset0 = Integer.MAX_VALUE;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                    assertTrue(actual0.indexOf((char)('a' + offset0))!= -1,message0 + " -- should contain offset0 character");
    }
    }

    @Test
    public void testAbbreviate_StringStringIntInt_28_oe_2_oe() {


        final String raspberry = "raspberry peach";

                final String expected0 = "+ghijklmno";
        final String abbrevMarker0 = "+";
        final int offset0 = Integer.MAX_VALUE;
        final int maxWidth0 = 10;
        final String abcdefghijklmno0 = "abcdefghijklmno0";
                final String message0 = "abbreviate(String,String,int,int) failed";
                final String actual0 = StringUtils.abbreviate(abcdefghijklmno0, abbrevMarker0, offset0, maxWidth0);
                if (offset0 >= 0 && offset0 < abcdefghijklmno0.length()) {
                }
                assertTrue(actual0.length()<= maxWidth0,message0 + " -- should not be greater than maxWidth0");
    }

@Test
    public void testAbbreviate_StringStringIntInt_5_oe() {

        assertThrows( IllegalArgumentException.class, () -> StringUtils.abbreviate("abcdefghij", "::", 0, 2), "StringUtils.abbreviate expecting IllegalArgumentException");
    }

@Test
    public void testAbbreviate_StringStringIntInt_13_oe() {


        final String raspberry = "raspberry peach";

        assertAbbreviateWithAbbrevMarkerAndOffset("abcdef{{{{", "{{{{", 4, 10);
    }

@Disabled
    @Test
    public void testLang1593_1_oe() {
        final int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        final String expected = StringUtils.join(arr, '-');
        final String actual = StringUtils.join(arr, "-");
        assertEquals(expected, actual);
    }

}
