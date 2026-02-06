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
    public void testIsEmpty_1_oe() {
        assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    public void testIsEmpty_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void testIsEmpty_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isEmpty(" "));
    }

    @Test
    public void testIsEmpty_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isEmpty("foo"));
    }

    @Test
    public void testIsEmpty_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isEmpty("  foo  "));
    }

    @Test
    public void testIsNotEmpty_1_oe() {
        assertFalse(StringUtils.isNotEmpty(null));
    }

    @Test
    public void testIsNotEmpty_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.isNotEmpty(""));
    }

    @Test
    public void testIsNotEmpty_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isNotEmpty(" "));
    }

    @Test
    public void testIsNotEmpty_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isNotEmpty("foo"));
    }

    @Test
    public void testIsNotEmpty_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isNotEmpty("  foo  "));
    }

    @Test
    public void testIsAnyEmpty_1_oe() {
        assertTrue(StringUtils.isAnyEmpty((String) null));
    }

    @Test
    public void testIsAnyEmpty_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.isAnyEmpty((String[]) null));
    }

    @Test
    public void testIsAnyEmpty_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAnyEmpty(null, "foo"));
    }

    @Test
    public void testIsAnyEmpty_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAnyEmpty("", "bar"));
    }

    @Test
    public void testIsAnyEmpty_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAnyEmpty("bob", ""));
    }

    @Test
    public void testIsAnyEmpty_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAnyEmpty("  bob  ", null));
    }

    @Test
    public void testIsAnyEmpty_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAnyEmpty(" ", "bar"));
    }

    @Test
    public void testIsAnyEmpty_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAnyEmpty("foo", "bar"));
    }

    @Test
    public void testIsNoneEmpty_1_oe() {
        assertFalse(StringUtils.isNoneEmpty((String) null));
    }

    @Test
    public void testIsNoneEmpty_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.isNoneEmpty((String[]) null));
    }

    @Test
    public void testIsNoneEmpty_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNoneEmpty(null, "foo"));
    }

    @Test
    public void testIsNoneEmpty_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNoneEmpty("", "bar"));
    }

    @Test
    public void testIsNoneEmpty_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNoneEmpty("bob", ""));
    }

    @Test
    public void testIsNoneEmpty_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNoneEmpty("  bob  ", null));
    }

    @Test
    public void testIsNoneEmpty_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isNoneEmpty(" ", "bar"));
    }

    @Test
    public void testIsNoneEmpty_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isNoneEmpty("foo", "bar"));
    }

    @Test
    public void testIsAllEmpty_1_oe() {
        assertTrue(StringUtils.isAllEmpty());
    }

    @Test
    public void testIsAllEmpty_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.isAllEmpty());
    }

    @Test
    public void testIsAllEmpty_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAllEmpty((String) null));
    }

    @Test
    public void testIsAllEmpty_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAllEmpty((String[]) null));
    }

    @Test
    public void testIsAllEmpty_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllEmpty(null, "foo"));
    }

    @Test
    public void testIsAllEmpty_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllEmpty("", "bar"));
    }

    @Test
    public void testIsAllEmpty_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllEmpty("bob", ""));
    }

    @Test
    public void testIsAllEmpty_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllEmpty("  bob  ", null));
    }

    @Test
    public void testIsAllEmpty_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllEmpty(" ", "bar"));
    }

    @Test
    public void testIsAllEmpty_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllEmpty("foo", "bar"));
    }

    @Test
    public void testIsAllEmpty_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAllEmpty("", null));
    }

    @Test
    public void testIsBlank_1_oe() {
        assertTrue(StringUtils.isBlank(null));
    }

    @Test
    public void testIsBlank_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.isBlank(""));
    }

    @Test
    public void testIsBlank_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isBlank(StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testIsBlank_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isBlank("foo"));
    }

    @Test
    public void testIsBlank_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isBlank("  foo  "));
    }

    @Test
    public void testIsNotBlank_1_oe() {
        assertFalse(StringUtils.isNotBlank(null));
    }

    @Test
    public void testIsNotBlank_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.isNotBlank(""));
    }

    @Test
    public void testIsNotBlank_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNotBlank(StringUtilsTest.WHITESPACE));
    }

    @Test
    public void testIsNotBlank_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isNotBlank("foo"));
    }

    @Test
    public void testIsNotBlank_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isNotBlank("  foo  "));
    }

    @Test
    public void testIsAnyBlank_1_oe() {
        assertTrue(StringUtils.isAnyBlank((String) null));
    }

    @Test
    public void testIsAnyBlank_2_oe() {
        // removed other assertion
        assertFalse(StringUtils.isAnyBlank((String[]) null));
    }

    @Test
    public void testIsAnyBlank_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAnyBlank(null, "foo"));
    }

    @Test
    public void testIsAnyBlank_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAnyBlank(null, null));
    }

    @Test
    public void testIsAnyBlank_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAnyBlank("", "bar"));
    }

    @Test
    public void testIsAnyBlank_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAnyBlank("bob", ""));
    }

    @Test
    public void testIsAnyBlank_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAnyBlank("  bob  ", null));
    }

    @Test
    public void testIsAnyBlank_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAnyBlank(" ", "bar"));
    }

    @Test
    public void testIsAnyBlank_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAnyBlank("foo", "bar"));
    }

    @Test
    public void testIsNoneBlank_1_oe() {
        assertFalse(StringUtils.isNoneBlank((String) null));
    }

    @Test
    public void testIsNoneBlank_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.isNoneBlank((String[]) null));
    }

    @Test
    public void testIsNoneBlank_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNoneBlank(null, "foo"));
    }

    @Test
    public void testIsNoneBlank_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNoneBlank(null, null));
    }

    @Test
    public void testIsNoneBlank_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNoneBlank("", "bar"));
    }

    @Test
    public void testIsNoneBlank_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNoneBlank("bob", ""));
    }

    @Test
    public void testIsNoneBlank_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNoneBlank("  bob  ", null));
    }

    @Test
    public void testIsNoneBlank_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isNoneBlank(" ", "bar"));
    }

    @Test
    public void testIsNoneBlank_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isNoneBlank("foo", "bar"));
    }

    @Test
    public void testIsAllBlank_1_oe() {
        assertTrue(StringUtils.isAllBlank((String) null));
    }

    @Test
    public void testIsAllBlank_2_oe() {
        // removed other assertion
        assertTrue(StringUtils.isAllBlank((String[]) null));
    }

    @Test
    public void testIsAllBlank_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAllBlank(null, null));
    }

    @Test
    public void testIsAllBlank_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtils.isAllBlank(null, " "));
    }

    @Test
    public void testIsAllBlank_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllBlank(null, "foo"));
    }

    @Test
    public void testIsAllBlank_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllBlank("", "bar"));
    }

    @Test
    public void testIsAllBlank_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllBlank("bob", ""));
    }

    @Test
    public void testIsAllBlank_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllBlank("  bob  ", null));
    }

    @Test
    public void testIsAllBlank_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllBlank(" ", "bar"));
    }

    @Test
    public void testIsAllBlank_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtils.isAllBlank("foo", "bar"));
    }

    @Test
    public void testFirstNonBlank_1_oe() {
        assertNull(StringUtils.firstNonBlank());
    }

    @Test
    public void testFirstNonBlank_2_oe() {
        // removed other assertion
        assertNull(StringUtils.firstNonBlank((String[]) null));
    }

    @Test
    public void testFirstNonBlank_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.firstNonBlank(null, null, null));
    }

    @Test
    public void testFirstNonBlank_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.firstNonBlank(null, "", " "));
    }

    @Test
    public void testFirstNonBlank_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.firstNonBlank(null, null, " "));
    }

    @Test
    public void testFirstNonBlank_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("zz", StringUtils.firstNonBlank(null, "zz"));
    }

    @Test
    public void testFirstNonBlank_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("abc", StringUtils.firstNonBlank("abc"));
    }

    @Test
    public void testFirstNonBlank_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("xyz", StringUtils.firstNonBlank(null, "xyz"));
    }

    @Test
    public void testFirstNonBlank_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("xyz", StringUtils.firstNonBlank(null, "xyz", "abc"));
    }

    @Test
    public void testFirstNonEmpty_1_oe() {
        assertNull(StringUtils.firstNonEmpty());
    }

    @Test
    public void testFirstNonEmpty_2_oe() {
        // removed other assertion
        assertNull(StringUtils.firstNonEmpty((String[]) null));
    }

    @Test
    public void testFirstNonEmpty_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.firstNonEmpty(null, null, null));
    }

    @Test
    public void testFirstNonEmpty_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" ", StringUtils.firstNonEmpty(null, "", " "));
    }

    @Test
    public void testFirstNonEmpty_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringUtils.firstNonEmpty(null, null, ""));
    }

    @Test
    public void testFirstNonEmpty_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("zz", StringUtils.firstNonEmpty(null, "zz"));
    }

    @Test
    public void testFirstNonEmpty_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("abc", StringUtils.firstNonEmpty("abc"));
    }

    @Test
    public void testFirstNonEmpty_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("xyz", StringUtils.firstNonEmpty(null, "xyz"));
    }

    @Test
    public void testFirstNonEmpty_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("xyz", StringUtils.firstNonEmpty(null, "xyz", "abc"));
    }

}
