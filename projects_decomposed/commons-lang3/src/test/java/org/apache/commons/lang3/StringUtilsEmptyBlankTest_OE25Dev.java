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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Empty/Blank methods
 */
public class StringUtilsEmptyBlankTest_OE25Dev  {

    @Test
    public void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty(" "));
        assertFalse(StringUtils.isEmpty("foo"));
        assertFalse(StringUtils.isEmpty("  foo  "));
    }

    @Test
    public void testIsNotEmpty() {
        assertFalse(StringUtils.isNotEmpty(null));
        assertFalse(StringUtils.isNotEmpty(""));
        assertTrue(StringUtils.isNotEmpty(" "));
        assertTrue(StringUtils.isNotEmpty("foo"));
        assertTrue(StringUtils.isNotEmpty("  foo  "));
    }

    @Test
    public void testIsAnyEmpty() {
        assertTrue(StringUtils.isAnyEmpty((String) null));
        assertFalse(StringUtils.isAnyEmpty((String[]) null));
        assertTrue(StringUtils.isAnyEmpty(null, "foo"));
        assertTrue(StringUtils.isAnyEmpty("", "bar"));
        assertTrue(StringUtils.isAnyEmpty("bob", ""));
        assertTrue(StringUtils.isAnyEmpty("  bob  ", null));
        assertFalse(StringUtils.isAnyEmpty(" ", "bar"));
        assertFalse(StringUtils.isAnyEmpty("foo", "bar"));
    }

    @Test
    public void testIsNoneEmpty() {
        assertFalse(StringUtils.isNoneEmpty((String) null));
        assertTrue(StringUtils.isNoneEmpty((String[]) null));
        assertFalse(StringUtils.isNoneEmpty(null, "foo"));
        assertFalse(StringUtils.isNoneEmpty("", "bar"));
        assertFalse(StringUtils.isNoneEmpty("bob", ""));
        assertFalse(StringUtils.isNoneEmpty("  bob  ", null));
        assertTrue(StringUtils.isNoneEmpty(" ", "bar"));
        assertTrue(StringUtils.isNoneEmpty("foo", "bar"));
    }

    @Test
    public void testIsAllEmpty() {
        assertTrue(StringUtils.isAllEmpty());
        assertTrue(StringUtils.isAllEmpty());
        assertTrue(StringUtils.isAllEmpty((String) null));
        assertTrue(StringUtils.isAllEmpty((String[]) null));
        assertFalse(StringUtils.isAllEmpty(null, "foo"));
        assertFalse(StringUtils.isAllEmpty("", "bar"));
        assertFalse(StringUtils.isAllEmpty("bob", ""));
        assertFalse(StringUtils.isAllEmpty("  bob  ", null));
        assertFalse(StringUtils.isAllEmpty(" ", "bar"));
        assertFalse(StringUtils.isAllEmpty("foo", "bar"));
        assertTrue(StringUtils.isAllEmpty("", null));
    }

    @Test
    public void testIsBlank() {
        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank(StringUtilsTest.WHITESPACE));
        assertFalse(StringUtils.isBlank("foo"));
        assertFalse(StringUtils.isBlank("  foo  "));
    }

    @Test
    public void testIsNotBlank() {
        assertFalse(StringUtils.isNotBlank(null));
        assertFalse(StringUtils.isNotBlank(""));
        assertFalse(StringUtils.isNotBlank(StringUtilsTest.WHITESPACE));
        assertTrue(StringUtils.isNotBlank("foo"));
        assertTrue(StringUtils.isNotBlank("  foo  "));
    }

    @Test
    public void testIsAnyBlank() {
        assertTrue(StringUtils.isAnyBlank((String) null));
        assertFalse(StringUtils.isAnyBlank((String[]) null));
        assertTrue(StringUtils.isAnyBlank(null, "foo"));
        assertTrue(StringUtils.isAnyBlank(null, null));
        assertTrue(StringUtils.isAnyBlank("", "bar"));
        assertTrue(StringUtils.isAnyBlank("bob", ""));
        assertTrue(StringUtils.isAnyBlank("  bob  ", null));
        assertTrue(StringUtils.isAnyBlank(" ", "bar"));
        assertFalse(StringUtils.isAnyBlank("foo", "bar"));
    }

    @Test
    public void testIsNoneBlank() {
        assertFalse(StringUtils.isNoneBlank((String) null));
        assertTrue(StringUtils.isNoneBlank((String[]) null));
        assertFalse(StringUtils.isNoneBlank(null, "foo"));
        assertFalse(StringUtils.isNoneBlank(null, null));
        assertFalse(StringUtils.isNoneBlank("", "bar"));
        assertFalse(StringUtils.isNoneBlank("bob", ""));
        assertFalse(StringUtils.isNoneBlank("  bob  ", null));
        assertFalse(StringUtils.isNoneBlank(" ", "bar"));
        assertTrue(StringUtils.isNoneBlank("foo", "bar"));
    }

    @Test
    public void testIsAllBlank() {
        assertTrue(StringUtils.isAllBlank((String) null));
        assertTrue(StringUtils.isAllBlank((String[]) null));
        assertTrue(StringUtils.isAllBlank(null, null));
        assertTrue(StringUtils.isAllBlank(null, " "));
        assertFalse(StringUtils.isAllBlank(null, "foo"));
        assertFalse(StringUtils.isAllBlank("", "bar"));
        assertFalse(StringUtils.isAllBlank("bob", ""));
        assertFalse(StringUtils.isAllBlank("  bob  ", null));
        assertFalse(StringUtils.isAllBlank(" ", "bar"));
        assertFalse(StringUtils.isAllBlank("foo", "bar"));
    }

    @Test
    public void testFirstNonBlank() {
        assertNull(StringUtils.firstNonBlank());
        assertNull(StringUtils.firstNonBlank((String[]) null));
        assertNull(StringUtils.firstNonBlank(null, null, null));
        assertNull(StringUtils.firstNonBlank(null, "", " "));
        assertNull(StringUtils.firstNonBlank(null, null, " "));
        assertEquals("zz", StringUtils.firstNonBlank(null, "zz"));
        assertEquals("abc", StringUtils.firstNonBlank("abc"));
        assertEquals("xyz", StringUtils.firstNonBlank(null, "xyz"));
        assertEquals("xyz", StringUtils.firstNonBlank(null, "xyz", "abc"));
    }

    @Test
    public void testFirstNonEmpty() {
        assertNull(StringUtils.firstNonEmpty());
        assertNull(StringUtils.firstNonEmpty((String[]) null));
        assertNull(StringUtils.firstNonEmpty(null, null, null));
        assertEquals(" ", StringUtils.firstNonEmpty(null, "", " "));
        assertNull(StringUtils.firstNonEmpty(null, null, ""));
        assertEquals("zz", StringUtils.firstNonEmpty(null, "zz"));
        assertEquals("abc", StringUtils.firstNonEmpty("abc"));
        assertEquals("xyz", StringUtils.firstNonEmpty(null, "xyz"));
        assertEquals("xyz", StringUtils.firstNonEmpty(null, "xyz", "abc"));
    }

    @Test
    public void testIsEmpty_1_oe() {
        assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    public void testIsEmpty_2_oe() {
        assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void testIsEmpty_3_oe() {
        assertFalse(StringUtils.isEmpty(" "));
    }

    @Test
    public void testIsEmpty_4_oe() {
        assertFalse(StringUtils.isEmpty("foo"));
    }

    @Test
    public void testIsEmpty_5_oe() {
        assertFalse(StringUtils.isEmpty("  foo  "));
    }

    @Test
    public void testIsNotEmpty_1_oe() {
        assertFalse(StringUtils.isNotEmpty(null));
    }

    @Test
    public void testIsNotEmpty_2_oe() {
        assertFalse(StringUtils.isNotEmpty(""));
    }

    @Test
    public void testIsNotEmpty_3_oe() {
        assertTrue(StringUtils.isNotEmpty(" "));
    }

    @Test
    public void testIsNotEmpty_4_oe() {
        assertTrue(StringUtils.isNotEmpty("foo"));
    }

    @Test
    public void testIsNotEmpty_5_oe() {
        assertTrue(StringUtils.isNotEmpty("  foo  "));
    }

    @Test
    public void testIsAnyEmpty_1_oe() {
        assertTrue(StringUtils.isAnyEmpty((String) null));
    }

    @Test
    public void testIsAnyEmpty_2_oe() {
        assertFalse(StringUtils.isAnyEmpty((String[]) null));
    }

    @Test
    public void testIsAnyEmpty_3_oe() {
        assertTrue(StringUtils.isAnyEmpty(null, "foo"));
    }

    @Test
    public void testIsAnyEmpty_4_oe() {
        assertTrue(StringUtils.isAnyEmpty("", "bar"));
    }

    @Test
    public void testIsAnyEmpty_5_oe() {
        assertTrue(StringUtils.isAnyEmpty("bob", ""));
    }

    @Test
    public void testIsAnyEmpty_6_oe() {
        assertTrue(StringUtils.isAnyEmpty("  bob  ", null));
    }

    @Test
    public void testIsAnyEmpty_7_oe() {
        assertFalse(StringUtils.isAnyEmpty(" ", "bar"));
    }

    @Test
    public void testIsAnyEmpty_8_oe() {
        assertFalse(StringUtils.isAnyEmpty("foo", "bar"));
    }

    @Test
    public void testIsNoneEmpty_1_oe() {
        assertFalse(StringUtils.isNoneEmpty((String) null));
    }

    @Test
    public void testIsNoneEmpty_2_oe() {
        assertTrue(StringUtils.isNoneEmpty((String[]) null));
    }

    @Test
    public void testIsNoneEmpty_3_oe() {
        assertFalse(StringUtils.isNoneEmpty(null, "foo"));
    }

    @Test
    public void testIsNoneEmpty_4_oe() {
        assertFalse(StringUtils.isNoneEmpty("", "bar"));
    }

    @Test
    public void testIsNoneEmpty_5_oe() {
        assertFalse(StringUtils.isNoneEmpty("bob", ""));
    }

    @Test
    public void testIsNoneEmpty_6_oe() {
        assertFalse(StringUtils.isNoneEmpty("  bob  ", null));
    }

    @Test
    public void testIsNoneEmpty_7_oe() {
        assertTrue(StringUtils.isNoneEmpty(" ", "bar"));
    }

    @Test
    public void testIsNoneEmpty_8_oe() {
        assertTrue(StringUtils.isNoneEmpty("foo", "bar"));
    }

    @Test
    public void testIsAllEmpty_1_oe() {
        assertTrue(StringUtils.isAllEmpty());
    }

    @Test
    public void testIsAllEmpty_2_oe() {
        assertTrue(StringUtils.isAllEmpty());
    }

    @Test
    public void testIsAllEmpty_3_oe() {
        assertTrue(StringUtils.isAllEmpty((String) null));
    }

    @Test
    public void testIsAllEmpty_4_oe() {
        assertTrue(StringUtils.isAllEmpty((String[]) null));
    }

    @Test
    public void testIsAllEmpty_5_oe() {
        assertFalse(StringUtils.isAllEmpty(null, "foo"));
    }

    @Test
    public void testIsAllEmpty_6_oe() {
        assertFalse(StringUtils.isAllEmpty("", "bar"));
    }

    @Test
    public void testIsAllEmpty_7_oe() {
        assertFalse(StringUtils.isAllEmpty("bob", ""));
    }

    @Test
    public void testIsAllEmpty_8_oe() {
        assertFalse(StringUtils.isAllEmpty("  bob  ", null));
    }

    @Test
    public void testIsAllEmpty_9_oe() {
        assertFalse(StringUtils.isAllEmpty(" ", "bar"));
    }

    @Test
    public void testIsAllEmpty_10_oe() {
        assertFalse(StringUtils.isAllEmpty("foo", "bar"));
    }

    @Test
    public void testIsAllEmpty_11_oe() {
        assertTrue(StringUtils.isAllEmpty("", null));
    }

    @Test
    public void testIsBlank_1_oe() {
        assertTrue(StringUtils.isBlank(null));
    }

    @Test
    public void testIsBlank_2_oe() {
        assertTrue(StringUtils.isBlank(""));
    }

    @Test
    public void testIsBlank_3_oe() {
        assertTrue(StringUtils.isBlank(StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testIsBlank_4_oe() {
        assertFalse(StringUtils.isBlank("foo"));
    }

    @Test
    public void testIsBlank_5_oe() {
        assertFalse(StringUtils.isBlank("  foo  "));
    }

    @Test
    public void testIsNotBlank_1_oe() {
        assertFalse(StringUtils.isNotBlank(null));
    }

    @Test
    public void testIsNotBlank_2_oe() {
        assertFalse(StringUtils.isNotBlank(""));
    }

    @Test
    public void testIsNotBlank_3_oe() {
        assertFalse(StringUtils.isNotBlank(StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testIsNotBlank_4_oe() {
        assertTrue(StringUtils.isNotBlank("foo"));
    }

    @Test
    public void testIsNotBlank_5_oe() {
        assertTrue(StringUtils.isNotBlank("  foo  "));
    }

    @Test
    public void testIsAnyBlank_1_oe() {
        assertTrue(StringUtils.isAnyBlank((String) null));
    }

    @Test
    public void testIsAnyBlank_2_oe() {
        assertFalse(StringUtils.isAnyBlank((String[]) null));
    }

    @Test
    public void testIsAnyBlank_3_oe() {
        assertTrue(StringUtils.isAnyBlank(null, "foo"));
    }

    @Test
    public void testIsAnyBlank_4_oe() {
        assertTrue(StringUtils.isAnyBlank(null, null));
    }

    @Test
    public void testIsAnyBlank_5_oe() {
        assertTrue(StringUtils.isAnyBlank("", "bar"));
    }

    @Test
    public void testIsAnyBlank_6_oe() {
        assertTrue(StringUtils.isAnyBlank("bob", ""));
    }

    @Test
    public void testIsAnyBlank_7_oe() {
        assertTrue(StringUtils.isAnyBlank("  bob  ", null));
    }

    @Test
    public void testIsAnyBlank_8_oe() {
        assertTrue(StringUtils.isAnyBlank(" ", "bar"));
    }

    @Test
    public void testIsAnyBlank_9_oe() {
        assertFalse(StringUtils.isAnyBlank("foo", "bar"));
    }

    @Test
    public void testIsNoneBlank_1_oe() {
        assertFalse(StringUtils.isNoneBlank((String) null));
    }

    @Test
    public void testIsNoneBlank_2_oe() {
        assertTrue(StringUtils.isNoneBlank((String[]) null));
    }

    @Test
    public void testIsNoneBlank_3_oe() {
        assertFalse(StringUtils.isNoneBlank(null, "foo"));
    }

    @Test
    public void testIsNoneBlank_4_oe() {
        assertFalse(StringUtils.isNoneBlank(null, null));
    }

    @Test
    public void testIsNoneBlank_5_oe() {
        assertFalse(StringUtils.isNoneBlank("", "bar"));
    }

    @Test
    public void testIsNoneBlank_6_oe() {
        assertFalse(StringUtils.isNoneBlank("bob", ""));
    }

    @Test
    public void testIsNoneBlank_7_oe() {
        assertFalse(StringUtils.isNoneBlank("  bob  ", null));
    }

    @Test
    public void testIsNoneBlank_8_oe() {
        assertFalse(StringUtils.isNoneBlank(" ", "bar"));
    }

    @Test
    public void testIsNoneBlank_9_oe() {
        assertTrue(StringUtils.isNoneBlank("foo", "bar"));
    }

    @Test
    public void testIsAllBlank_1_oe() {
        assertTrue(StringUtils.isAllBlank((String) null));
    }

    @Test
    public void testIsAllBlank_2_oe() {
        assertTrue(StringUtils.isAllBlank((String[]) null));
    }

    @Test
    public void testIsAllBlank_3_oe() {
        assertTrue(StringUtils.isAllBlank(null, null));
    }

    @Test
    public void testIsAllBlank_4_oe() {
        assertTrue(StringUtils.isAllBlank(null, " "));
    }

    @Test
    public void testIsAllBlank_5_oe() {
        assertFalse(StringUtils.isAllBlank(null, "foo"));
    }

    @Test
    public void testIsAllBlank_6_oe() {
        assertFalse(StringUtils.isAllBlank("", "bar"));
    }

    @Test
    public void testIsAllBlank_7_oe() {
        assertFalse(StringUtils.isAllBlank("bob", ""));
    }

    @Test
    public void testIsAllBlank_8_oe() {
        assertFalse(StringUtils.isAllBlank("  bob  ", null));
    }

    @Test
    public void testIsAllBlank_9_oe() {
        assertFalse(StringUtils.isAllBlank(" ", "bar"));
    }

    @Test
    public void testIsAllBlank_10_oe() {
        assertFalse(StringUtils.isAllBlank("foo", "bar"));
    }

    @Test
    public void testFirstNonBlank_1_oe() {
        assertNull(StringUtils.firstNonBlank());
    }

    @Test
    public void testFirstNonBlank_2_oe() {
        assertNull(StringUtils.firstNonBlank((String[]) null));
    }

    @Test
    public void testFirstNonBlank_3_oe() {
        assertNull(StringUtils.firstNonBlank(null, null, null));
    }

    @Test
    public void testFirstNonBlank_4_oe() {
        assertNull(StringUtils.firstNonBlank(null, "", " "));
    }

    @Test
    public void testFirstNonBlank_5_oe() {
        assertNull(StringUtils.firstNonBlank(null, null, " "));
    }

    @Test
    public void testFirstNonBlank_6_oe() {
        assertEquals("zz", StringUtils.firstNonBlank(null, "zz"));
    }

    @Test
    public void testFirstNonBlank_7_oe() {
        assertEquals("abc", StringUtils.firstNonBlank("abc"));
    }

    @Test
    public void testFirstNonBlank_8_oe() {
        assertEquals("xyz", StringUtils.firstNonBlank(null, "xyz"));
    }

    @Test
    public void testFirstNonBlank_9_oe() {
        assertEquals("xyz", StringUtils.firstNonBlank(null, "xyz", "abc"));
    }

    @Test
    public void testFirstNonEmpty_1_oe() {
        assertNull(StringUtils.firstNonEmpty());
    }

    @Test
    public void testFirstNonEmpty_2_oe() {
        assertNull(StringUtils.firstNonEmpty((String[]) null));
    }

    @Test
    public void testFirstNonEmpty_3_oe() {
        assertNull(StringUtils.firstNonEmpty(null, null, null));
    }

    @Test
    public void testFirstNonEmpty_4_oe() {
        assertEquals(" ", StringUtils.firstNonEmpty(null, "", " "));
    }

    @Test
    public void testFirstNonEmpty_5_oe() {
        assertNull(StringUtils.firstNonEmpty(null, null, ""));
    }

    @Test
    public void testFirstNonEmpty_6_oe() {
        assertEquals("zz", StringUtils.firstNonEmpty(null, "zz"));
    }

    @Test
    public void testFirstNonEmpty_7_oe() {
        assertEquals("abc", StringUtils.firstNonEmpty("abc"));
    }

    @Test
    public void testFirstNonEmpty_8_oe() {
        assertEquals("xyz", StringUtils.firstNonEmpty(null, "xyz"));
    }

    @Test
    public void testFirstNonEmpty_9_oe() {
        assertEquals("xyz", StringUtils.firstNonEmpty(null, "xyz", "abc"));
    }

}
