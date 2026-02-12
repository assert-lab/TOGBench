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

    @Disabled
    @Test
    public void testLang1593() {
        final int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        final String expected = StringUtils.join(arr, '-');
        final String actual = StringUtils.join(arr, "-");
        assertEquals(expected, actual);
    }

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
    public void testAbbreviate_StringInt_13_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String raspberry = "raspberry peach";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    StringUtils.abbreviate("abc", 3);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAbbreviate_StringIntInt_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    StringUtils.abbreviate("abcdefghij", 0, 3);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAbbreviate_StringIntInt_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    StringUtils.abbreviate("abcdefghij", 5, 6);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAbbreviate_StringStringInt_15_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String raspberry = "raspberry peach";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    StringUtils.abbreviate("abcdefghij", "...", 3);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAbbreviate_StringStringIntInt_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    StringUtils.abbreviate("abcdefghij", "::", 0, 2);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAbbreviate_StringStringIntInt_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    StringUtils.abbreviate("abcdefghij", "!!!", 5, 6);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: StringUtils.abbreviate expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFuzzyDistance_NullNullNull_1_oe() throws Exception {
        try {
    StringUtils.getFuzzyDistance(null, null, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFuzzyDistance_NullStringLocale_1_oe() throws Exception {
        try {
    StringUtils.getFuzzyDistance(null, "clear", Locale.ENGLISH);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFuzzyDistance_StringNullLoclae_1_oe() throws Exception {
        try {
    StringUtils.getFuzzyDistance(" ", null, Locale.ENGLISH);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFuzzyDistance_StringStringNull_1_oe() throws Exception {
        try {
    StringUtils.getFuzzyDistance(" ", "clear", null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetJaroWinklerDistance_NullNull_1_oe() throws Exception {
        try {
    StringUtils.getJaroWinklerDistance(null, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetJaroWinklerDistance_NullString_1_oe() throws Exception {
        try {
    StringUtils.getJaroWinklerDistance(null, "clear");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetJaroWinklerDistance_StringNull_1_oe() throws Exception {
        try {
    StringUtils.getJaroWinklerDistance(" ", null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetLevenshteinDistance_NullString_1_oe() throws Exception {
        try {
    StringUtils.getLevenshteinDistance("a", null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetLevenshteinDistance_NullStringInt_1_oe() throws Exception {
        try {
    StringUtils.getLevenshteinDistance(null, "a", 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetLevenshteinDistance_StringNull_1_oe() throws Exception {
        try {
    StringUtils.getLevenshteinDistance(null, "a");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetLevenshteinDistance_StringNullInt_1_oe() throws Exception {
        try {
    StringUtils.getLevenshteinDistance("a", null, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetLevenshteinDistance_StringStringNegativeInt_1_oe() throws Exception {
        try {
    StringUtils.getLevenshteinDistance("a", "a", -1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testJoinWithThrowsException_1_oe() throws Exception {
        try {
    StringUtils.joinWith(",", (Object[]) null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testRemoveAll_StringString_10_oe() throws Exception {
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
    StringUtils.removeAll("any", "{badRegexSyntax}");
    org.junit.jupiter.api.Assertions.fail("PatternSyntaxException: StringUtils.removeAll expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testRemoveFirst_StringString_11_oe() throws Exception {
        // removed other assertion
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
    StringUtils.removeFirst("any", "{badRegexSyntax}");
    org.junit.jupiter.api.Assertions.fail("PatternSyntaxException: StringUtils.removeFirst expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testReplace_StringStringArrayStringArray_15_oe() throws Exception {
        //JAVADOC TESTS START
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //JAVADOC TESTS END

        // removed other assertion
        // removed other assertion

        // Test null safety inside arrays - LANG-552
        // removed other assertion
        // removed other assertion

        try {
    StringUtils.replaceEach("abba", new String[]{"a"}, new String[]{"b", "a"});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: StringUtils.replaceEach(String, String[], String[]) expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReplace_StringStringArrayStringArrayBoolean_12_oe() throws Exception {
        //JAVADOC TESTS START
        // removed other assertion
        // removed other assertion
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
    StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"});
    org.junit.jupiter.api.Assertions.fail("IllegalStateException: Should be a circular reference");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testReplaceAll_StringStringString_14_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
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
    StringUtils.replaceAll("any", "{badRegexSyntax}", "");
    org.junit.jupiter.api.Assertions.fail("PatternSyntaxException: StringUtils.replaceAll expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testReplaceFirst_StringStringString_14_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
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
    StringUtils.replaceFirst("any", "{badRegexSyntax}", "");
    org.junit.jupiter.api.Assertions.fail("PatternSyntaxException: StringUtils.replaceFirst expecting PatternSyntaxException");
} catch (PatternSyntaxException e) {
}
    }

    @Test
    public void testTruncate_StringInt_2_oe() throws Exception {
        // removed other assertion
        try {
    StringUtils.truncate(null, -1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    StringUtils.truncate(null, -10);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    StringUtils.truncate(null, Integer.MIN_VALUE);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_10_oe() throws Exception {
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
    StringUtils.truncate("abcdefghij", -1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_11_oe() throws Exception {
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", -100);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringInt_12_oe() throws Exception {
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", Integer.MIN_VALUE);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_2_oe() throws Exception {
        // removed other assertion
        try {
    StringUtils.truncate(null, -1, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    StringUtils.truncate(null, -10, -4);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    StringUtils.truncate(null, Integer.MIN_VALUE, Integer.MIN_VALUE);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_11_oe() throws Exception {
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", 0, -1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_12_oe() throws Exception {
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", 0, -10);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_13_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", 0, -100);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_14_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", 1, -100);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_15_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", 0, Integer.MIN_VALUE);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: maxWith cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_16_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", -1, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_17_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", -10, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_18_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", -100, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_19_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", Integer.MIN_VALUE, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_20_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", -1, -1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_21_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", -10, -10);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_22_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", -100, -100);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testTruncate_StringIntInt_23_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
    StringUtils.truncate("abcdefghij", Integer.MIN_VALUE, Integer.MIN_VALUE);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: offset cannot be negative");
} catch (IllegalArgumentException e) {
}
    }

}
