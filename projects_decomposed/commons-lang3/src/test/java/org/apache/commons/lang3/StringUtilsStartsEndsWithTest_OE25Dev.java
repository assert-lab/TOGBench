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
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - StartsWith/EndsWith methods
 */
public class StringUtilsStartsEndsWithTest_OE25Dev {
    private static final String foo    = "foo";
    private static final String bar    = "bar";
    private static final String foobar = "foobar";
    private static final String FOO    = "FOO";
    private static final String BAR    = "BAR";
    private static final String FOOBAR = "FOOBAR";

    //-----------------------------------------------------------------------

    /**
     * Test StringUtils.startsWith()
     */

    /**
     * Test StringUtils.testStartsWithIgnoreCase()
     */


    /**
     * Test StringUtils.endsWith()
     */

    /**
     * Test StringUtils.endsWithIgnoreCase()
     */


@Test
    public void testStartsWith_1_oe() {
        assertTrue(StringUtils.startsWith(null, null), "startsWith(null, null)");
    }

@Test
    public void testStartsWith_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.startsWith(FOOBAR, null), "startsWith(FOOBAR, null)");
    }

@Test
    public void testStartsWith_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWith(null, FOO), "startsWith(null, FOO)");
    }

@Test
    public void testStartsWith_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.startsWith(FOOBAR, ""), "startsWith(FOOBAR, \"\")");
    }

@Test
    public void testStartsWith_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(StringUtils.startsWith(foobar, foo), "startsWith(foobar, foo)");
    }

@Test
    public void testStartsWith_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(StringUtils.startsWith(FOOBAR, FOO), "startsWith(FOOBAR, FOO)");
    }

@Test
    public void testStartsWith_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWith(foobar, FOO), "startsWith(foobar, FOO)");
    }

@Test
    public void testStartsWith_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWith(FOOBAR, foo), "startsWith(FOOBAR, foo)");
    }

@Test
    public void testStartsWith_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(StringUtils.startsWith(foo, foobar), "startsWith(foo, foobar)");
    }

@Test
    public void testStartsWith_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtils.startsWith(bar, foobar), "startsWith(foo, foobar)");
    }

@Test
    public void testStartsWith_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertFalse(StringUtils.startsWith(foobar, bar), "startsWith(foobar, bar)");
    }

@Test
    public void testStartsWith_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtils.startsWith(FOOBAR, BAR), "startsWith(FOOBAR, BAR)");
    }

@Test
    public void testStartsWith_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWith(foobar, BAR), "startsWith(foobar, BAR)");
    }

@Test
    public void testStartsWith_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWith(FOOBAR, bar), "startsWith(FOOBAR, bar)");
    }

@Test
    public void testStartsWithIgnoreCase_1_oe() {
        assertTrue(StringUtils.startsWithIgnoreCase(null, null), "startsWithIgnoreCase(null, null)");
    }

@Test
    public void testStartsWithIgnoreCase_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.startsWithIgnoreCase(FOOBAR, null), "startsWithIgnoreCase(FOOBAR, null)");
    }

@Test
    public void testStartsWithIgnoreCase_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWithIgnoreCase(null, FOO), "startsWithIgnoreCase(null, FOO)");
    }

@Test
    public void testStartsWithIgnoreCase_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.startsWithIgnoreCase(FOOBAR, ""), "startsWithIgnoreCase(FOOBAR, \"\")");
    }

@Test
    public void testStartsWithIgnoreCase_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(StringUtils.startsWithIgnoreCase(foobar, foo), "startsWithIgnoreCase(foobar, foo)");
    }

@Test
    public void testStartsWithIgnoreCase_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(StringUtils.startsWithIgnoreCase(FOOBAR, FOO), "startsWithIgnoreCase(FOOBAR, FOO)");
    }

@Test
    public void testStartsWithIgnoreCase_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.startsWithIgnoreCase(foobar, FOO), "startsWithIgnoreCase(foobar, FOO)");
    }

@Test
    public void testStartsWithIgnoreCase_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.startsWithIgnoreCase(FOOBAR, foo), "startsWithIgnoreCase(FOOBAR, foo)");
    }

@Test
    public void testStartsWithIgnoreCase_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(StringUtils.startsWithIgnoreCase(foo, foobar), "startsWithIgnoreCase(foo, foobar)");
    }

@Test
    public void testStartsWithIgnoreCase_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtils.startsWithIgnoreCase(bar, foobar), "startsWithIgnoreCase(foo, foobar)");
    }

@Test
    public void testStartsWithIgnoreCase_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertFalse(StringUtils.startsWithIgnoreCase(foobar, bar), "startsWithIgnoreCase(foobar, bar)");
    }

@Test
    public void testStartsWithIgnoreCase_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtils.startsWithIgnoreCase(FOOBAR, BAR), "startsWithIgnoreCase(FOOBAR, BAR)");
    }

@Test
    public void testStartsWithIgnoreCase_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWithIgnoreCase(foobar, BAR), "startsWithIgnoreCase(foobar, BAR)");
    }

@Test
    public void testStartsWithIgnoreCase_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWithIgnoreCase(FOOBAR, bar), "startsWithIgnoreCase(FOOBAR, bar)");
    }

@Test
    public void testStartsWithAny_1_oe() {
        assertFalse(StringUtils.startsWithAny(null, (String[]) null));
    }

@Test
    public void testStartsWithAny_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.startsWithAny(null, "abc"));
    }

@Test
    public void testStartsWithAny_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWithAny("abcxyz", (String[]) null));
    }

@Test
    public void testStartsWithAny_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWithAny("abcxyz"));
    }

@Test
    public void testStartsWithAny_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.startsWithAny("abcxyz", "abc"));
    }

@Test
    public void testStartsWithAny_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.startsWithAny("abcxyz", null, "xyz", "abc"));
    }

@Test
    public void testStartsWithAny_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWithAny("abcxyz", null, "xyz", "abcd"));
    }

@Test
    public void testStartsWithAny_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.startsWithAny("abcxyz", ""));
    }

@Test
    public void testStartsWithAny_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWithAny("abcxyz", null, "xyz", "ABCX"));
    }

@Test
    public void testStartsWithAny_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.startsWithAny("ABCXYZ", null, "xyz", "abc"));
    }

@Test
    public void testStartsWithAny_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(StringUtils.startsWithAny("abcxyz", new StringBuilder("xyz"), new StringBuffer("abc")), "StringUtils.startsWithAny(abcxyz, StringBuilder(xyz), StringBuffer(abc))");
    }

@Test
    public void testStartsWithAny_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(StringUtils.startsWithAny(new StringBuffer("abcxyz"), new StringBuilder("xyz"), new StringBuffer("abc")), "StringUtils.startsWithAny(StringBuffer(abcxyz), StringBuilder(xyz), StringBuffer(abc))");
    }

@Test
    public void testEndsWith_1_oe() {
        assertTrue(StringUtils.endsWith(null, null), "endsWith(null, null)");
    }

@Test
    public void testEndsWith_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.endsWith(FOOBAR, null), "endsWith(FOOBAR, null)");
    }

@Test
    public void testEndsWith_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWith(null, FOO), "endsWith(null, FOO)");
    }

@Test
    public void testEndsWith_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.endsWith(FOOBAR, ""), "endsWith(FOOBAR, \"\")");
    }

@Test
    public void testEndsWith_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(StringUtils.endsWith(foobar, foo), "endsWith(foobar, foo)");
    }

@Test
    public void testEndsWith_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtils.endsWith(FOOBAR, FOO), "endsWith(FOOBAR, FOO)");
    }

@Test
    public void testEndsWith_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWith(foobar, FOO), "endsWith(foobar, FOO)");
    }

@Test
    public void testEndsWith_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWith(FOOBAR, foo), "endsWith(FOOBAR, foo)");
    }

@Test
    public void testEndsWith_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(StringUtils.endsWith(foo, foobar), "endsWith(foo, foobar)");
    }

@Test
    public void testEndsWith_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtils.endsWith(bar, foobar), "endsWith(foo, foobar)");
    }

@Test
    public void testEndsWith_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(StringUtils.endsWith(foobar, bar), "endsWith(foobar, bar)");
    }

@Test
    public void testEndsWith_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(StringUtils.endsWith(FOOBAR, BAR), "endsWith(FOOBAR, BAR)");
    }

@Test
    public void testEndsWith_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWith(foobar, BAR), "endsWith(foobar, BAR)");
    }

@Test
    public void testEndsWith_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWith(FOOBAR, bar), "endsWith(FOOBAR, bar)");
    }

@Test
    public void testEndsWith_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "alpha, beta, gamma, delta".endsWith("delta")
        assertTrue(StringUtils.endsWith("\u03B1\u03B2\u03B3\u03B4","\u03B4"),"endsWith(\u03B1\u03B2\u03B3\u03B4,\u03B4)");
    }

@Test
    public void testEndsWith_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "alpha, beta, gamma, delta".endsWith("delta")
        // removed other assertion
        // "alpha, beta, gamma, delta".endsWith("gamma, DELTA")
        assertFalse(StringUtils.endsWith("\u03B1\u03B2\u03B3\u03B4","\u03B3\u0394"),"endsWith(\u03B1\u03B2\u03B3\u03B4,\u03B3\u0394)");
    }

@Test
    public void testEndsWithIgnoreCase_1_oe() {
        assertTrue(StringUtils.endsWithIgnoreCase(null, null), "endsWithIgnoreCase(null, null)");
    }

@Test
    public void testEndsWithIgnoreCase_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.endsWithIgnoreCase(FOOBAR, null), "endsWithIgnoreCase(FOOBAR, null)");
    }

@Test
    public void testEndsWithIgnoreCase_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWithIgnoreCase(null, FOO), "endsWithIgnoreCase(null, FOO)");
    }

@Test
    public void testEndsWithIgnoreCase_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.endsWithIgnoreCase(FOOBAR, ""), "endsWithIgnoreCase(FOOBAR, \"\")");
    }

@Test
    public void testEndsWithIgnoreCase_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(StringUtils.endsWithIgnoreCase(foobar, foo), "endsWithIgnoreCase(foobar, foo)");
    }

@Test
    public void testEndsWithIgnoreCase_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtils.endsWithIgnoreCase(FOOBAR, FOO), "endsWithIgnoreCase(FOOBAR, FOO)");
    }

@Test
    public void testEndsWithIgnoreCase_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWithIgnoreCase(foobar, FOO), "endsWithIgnoreCase(foobar, FOO)");
    }

@Test
    public void testEndsWithIgnoreCase_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWithIgnoreCase(FOOBAR, foo), "endsWithIgnoreCase(FOOBAR, foo)");
    }

@Test
    public void testEndsWithIgnoreCase_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(StringUtils.endsWithIgnoreCase(foo, foobar), "endsWithIgnoreCase(foo, foobar)");
    }

@Test
    public void testEndsWithIgnoreCase_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtils.endsWithIgnoreCase(bar, foobar), "endsWithIgnoreCase(foo, foobar)");
    }

@Test
    public void testEndsWithIgnoreCase_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(StringUtils.endsWithIgnoreCase(foobar, bar), "endsWithIgnoreCase(foobar, bar)");
    }

@Test
    public void testEndsWithIgnoreCase_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(StringUtils.endsWithIgnoreCase(FOOBAR, BAR), "endsWithIgnoreCase(FOOBAR, BAR)");
    }

@Test
    public void testEndsWithIgnoreCase_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.endsWithIgnoreCase(foobar, BAR), "endsWithIgnoreCase(foobar, BAR)");
    }

@Test
    public void testEndsWithIgnoreCase_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.endsWithIgnoreCase(FOOBAR, bar), "endsWithIgnoreCase(FOOBAR, bar)");
    }

@Test
    public void testEndsWithIgnoreCase_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // javadoc
        assertTrue(StringUtils.endsWithIgnoreCase("abcdef", "def"));
    }

@Test
    public void testEndsWithIgnoreCase_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // javadoc
        // removed other assertion
        assertTrue(StringUtils.endsWithIgnoreCase("ABCDEF", "def"));
    }

@Test
    public void testEndsWithIgnoreCase_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // javadoc
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWithIgnoreCase("ABCDEF", "cde"));
    }

@Test
    public void testEndsWithIgnoreCase_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // javadoc
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "alpha, beta, gamma, delta".endsWith("DELTA")
        assertTrue(StringUtils.endsWithIgnoreCase("\u03B1\u03B2\u03B3\u03B4","\u0394"),"endsWith(\u03B1\u03B2\u03B3\u03B4,\u0394)");
    }

@Test
    public void testEndsWithIgnoreCase_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // javadoc
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // "alpha, beta, gamma, delta".endsWith("DELTA")
        // removed other assertion
        // "alpha, beta, gamma, delta".endsWith("GAMMA")
        assertFalse(StringUtils.endsWithIgnoreCase("\u03B1\u03B2\u03B3\u03B4","\u0393"),"endsWith(\u03B1\u03B2\u03B3\u03B4,\u0393)");
    }

@Test
    public void testEndsWithAny_1_oe() {
        assertFalse(StringUtils.endsWithAny(null, (String) null), "StringUtils.endsWithAny(null, null)");
    }

@Test
    public void testEndsWithAny_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.endsWithAny(null, "abc"), "StringUtils.endsWithAny(null, new String[] {abc})");
    }

@Test
    public void testEndsWithAny_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWithAny("abcxyz", (String) null), "StringUtils.endsWithAny(abcxyz, null)");
    }

@Test
    public void testEndsWithAny_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.endsWithAny("abcxyz", ""), "StringUtils.endsWithAny(abcxyz, new String[] {\"\"})");
    }

@Test
    public void testEndsWithAny_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.endsWithAny("abcxyz", "xyz"), "StringUtils.endsWithAny(abcxyz, new String[] {xyz})");
    }

@Test
    public void testEndsWithAny_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.endsWithAny("abcxyz", null, "xyz", "abc"), "StringUtils.endsWithAny(abcxyz, new String[] {null, xyz, abc})");
    }

@Test
    public void testEndsWithAny_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWithAny("defg", null, "xyz", "abc"), "StringUtils.endsWithAny(defg, new String[] {null, xyz, abc})");
    }

@Test
    public void testEndsWithAny_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.endsWithAny("abcXYZ", "def", "XYZ"));
    }

@Test
    public void testEndsWithAny_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.endsWithAny("abcXYZ", "def", "xyz"));
    }

@Test
    public void testEndsWithAny_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.endsWithAny("abcXYZ", "def", "YZ"));
    }

@Test
    public void testEndsWithAny_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /*
         * Type null of the last argument to method endsWithAny(CharSequence, CharSequence...)
         * doesn't exactly match the vararg parameter type.
         * Cast to CharSequence[] to confirm the non-varargs invocation,
         * or pass individual arguments of type CharSequence for a varargs invocation.
         *
         * assertFalse(StringUtils.endsWithAny("abcXYZ", null)); // replace with specific types to avoid warning
         */
        assertFalse(StringUtils.endsWithAny("abcXYZ", (CharSequence) null));
    }

@Test
    public void testEndsWithAny_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /*
         * Type null of the last argument to method endsWithAny(CharSequence, CharSequence...)
         * doesn't exactly match the vararg parameter type.
         * Cast to CharSequence[] to confirm the non-varargs invocation,
         * or pass individual arguments of type CharSequence for a varargs invocation.
         *
         * assertFalse(StringUtils.endsWithAny("abcXYZ", null)); // replace with specific types to avoid warning
         */
        // removed other assertion
        assertFalse(StringUtils.endsWithAny("abcXYZ", (CharSequence[]) null));
    }

@Test
    public void testEndsWithAny_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /*
         * Type null of the last argument to method endsWithAny(CharSequence, CharSequence...)
         * doesn't exactly match the vararg parameter type.
         * Cast to CharSequence[] to confirm the non-varargs invocation,
         * or pass individual arguments of type CharSequence for a varargs invocation.
         *
         * assertFalse(StringUtils.endsWithAny("abcXYZ", null)); // replace with specific types to avoid warning
         */
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.endsWithAny("abcXYZ", ""));
    }

@Test
    public void testEndsWithAny_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /*
         * Type null of the last argument to method endsWithAny(CharSequence, CharSequence...)
         * doesn't exactly match the vararg parameter type.
         * Cast to CharSequence[] to confirm the non-varargs invocation,
         * or pass individual arguments of type CharSequence for a varargs invocation.
         *
         * assertFalse(StringUtils.endsWithAny("abcXYZ", null)); // replace with specific types to avoid warning
         */
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(StringUtils.endsWithAny("abcxyz", new StringBuilder("abc"), new StringBuffer("xyz")), "StringUtils.endsWithAny(abcxyz, StringBuilder(abc), StringBuffer(xyz))");
    }

@Test
    public void testEndsWithAny_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /*
         * Type null of the last argument to method endsWithAny(CharSequence, CharSequence...)
         * doesn't exactly match the vararg parameter type.
         * Cast to CharSequence[] to confirm the non-varargs invocation,
         * or pass individual arguments of type CharSequence for a varargs invocation.
         *
         * assertFalse(StringUtils.endsWithAny("abcXYZ", null)); // replace with specific types to avoid warning
         */
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(StringUtils.endsWithAny(new StringBuffer("abcxyz"), new StringBuilder("abc"), new StringBuffer("xyz")), "StringUtils.endsWithAny(StringBuffer(abcxyz), StringBuilder(abc), StringBuffer(xyz))");
    }

}
