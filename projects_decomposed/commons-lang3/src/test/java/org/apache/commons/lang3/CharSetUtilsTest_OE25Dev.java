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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.CharSetUtils}.
 */
public class CharSetUtilsTest_OE25Dev  {

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

@Test
    public void testConstructor_1_oe() {
        assertNotNull(new CharSetUtils());
    }

@Test
    public void testConstructor_2_oe() {
        // removed other assertion
        final Constructor<?>[] cons = CharSetUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

@Test
    public void testConstructor_3_oe() {
        // removed other assertion
        final Constructor<?>[] cons = CharSetUtils.class.getDeclaredConstructors();
        // removed other assertion
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

@Test
    public void testConstructor_4_oe() {
        // removed other assertion
        final Constructor<?>[] cons = CharSetUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        assertTrue(Modifier.isPublic(CharSetUtils.class.getModifiers()));
    }

@Test
    public void testConstructor_5_oe() {
        // removed other assertion
        final Constructor<?>[] cons = CharSetUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Modifier.isFinal(CharSetUtils.class.getModifiers()));
    }

@Test
    public void testSqueeze_StringString_1_oe() {
        assertNull(CharSetUtils.squeeze(null, (String) null));
    }

@Test
    public void testSqueeze_StringString_2_oe() {
        // removed other assertion
        assertNull(CharSetUtils.squeeze(null, ""));
    }

@Test
    public void testSqueeze_StringString_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals("", CharSetUtils.squeeze("", (String) null));
    }

@Test
    public void testSqueeze_StringString_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", CharSetUtils.squeeze("", ""));
    }

@Test
    public void testSqueeze_StringString_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.squeeze("", "a-e"));
    }

@Test
    public void testSqueeze_StringString_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("hello", CharSetUtils.squeeze("hello", (String) null));
    }

@Test
    public void testSqueeze_StringString_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("hello", CharSetUtils.squeeze("hello", ""));
    }

@Test
    public void testSqueeze_StringString_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.squeeze("hello", "a-e"));
    }

@Test
    public void testSqueeze_StringString_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("helo", CharSetUtils.squeeze("hello", "l-p"));
    }

@Test
    public void testSqueeze_StringString_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("heloo", CharSetUtils.squeeze("helloo", "l"));
    }

@Test
    public void testSqueeze_StringString_11_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.squeeze("helloo", "^l"));
    }

@Test
    public void testSqueeze_StringStringarray_1_oe() {
        assertNull(CharSetUtils.squeeze(null, (String[]) null));
    }

@Test
    public void testSqueeze_StringStringarray_2_oe() {
        // removed other assertion
        assertNull(CharSetUtils.squeeze(null));
    }

@Test
    public void testSqueeze_StringStringarray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(CharSetUtils.squeeze(null, null));
    }

@Test
    public void testSqueeze_StringStringarray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(CharSetUtils.squeeze(null, "el"));
    }

@Test
    public void testSqueeze_StringStringarray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("", CharSetUtils.squeeze("", (String[]) null));
    }

@Test
    public void testSqueeze_StringStringarray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", CharSetUtils.squeeze(""));
    }

@Test
    public void testSqueeze_StringStringarray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.squeeze("", null));
    }

@Test
    public void testSqueeze_StringStringarray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.squeeze("", "a-e"));
    }

@Test
    public void testSqueeze_StringStringarray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("hello", CharSetUtils.squeeze("hello", (String[]) null));
    }

@Test
    public void testSqueeze_StringStringarray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("hello", CharSetUtils.squeeze("hello"));
    }

@Test
    public void testSqueeze_StringStringarray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.squeeze("hello", null));
    }

@Test
    public void testSqueeze_StringStringarray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.squeeze("hello", "a-e"));
    }

@Test
    public void testSqueeze_StringStringarray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("helo", CharSetUtils.squeeze("hello", "el"));
    }

@Test
    public void testSqueeze_StringStringarray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("hello", CharSetUtils.squeeze("hello", "e"));
    }

@Test
    public void testSqueeze_StringStringarray_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("fofof", CharSetUtils.squeeze("fooffooff", "of"));
    }

@Test
    public void testSqueeze_StringStringarray_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fof", CharSetUtils.squeeze("fooooff", "fo"));
    }

@Test
    public void testContainsAny_StringString_1_oe() {
        assertFalse(CharSetUtils.containsAny(null, (String) null));
    }

@Test
    public void testContainsAny_StringString_2_oe() {
        // removed other assertion
        assertFalse(CharSetUtils.containsAny(null, ""));
    }

@Test
    public void testContainsAny_StringString_3_oe() {
        // removed other assertion
        // removed other assertion

        assertFalse(CharSetUtils.containsAny("", (String) null));
    }

@Test
    public void testContainsAny_StringString_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(CharSetUtils.containsAny("", ""));
    }

@Test
    public void testContainsAny_StringString_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(CharSetUtils.containsAny("", "a-e"));
    }

@Test
    public void testContainsAny_StringString_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(CharSetUtils.containsAny("hello", (String) null));
    }

@Test
    public void testContainsAny_StringString_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(CharSetUtils.containsAny("hello", ""));
    }

@Test
    public void testContainsAny_StringString_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(CharSetUtils.containsAny("hello", "a-e"));
    }

@Test
    public void testContainsAny_StringString_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CharSetUtils.containsAny("hello", "l-p"));
    }

@Test
    public void testContainsAny_StringStringarray_1_oe() {
        assertFalse(CharSetUtils.containsAny(null, (String[]) null));
    }

@Test
    public void testContainsAny_StringStringarray_2_oe() {
        // removed other assertion
        assertFalse(CharSetUtils.containsAny(null));
    }

@Test
    public void testContainsAny_StringStringarray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(CharSetUtils.containsAny(null, null));
    }

@Test
    public void testContainsAny_StringStringarray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharSetUtils.containsAny(null, "a-e"));
    }

@Test
    public void testContainsAny_StringStringarray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(CharSetUtils.containsAny("", (String[]) null));
    }

@Test
    public void testContainsAny_StringStringarray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(CharSetUtils.containsAny(""));
    }

@Test
    public void testContainsAny_StringStringarray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(CharSetUtils.containsAny("", null));
    }

@Test
    public void testContainsAny_StringStringarray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharSetUtils.containsAny("", "a-e"));
    }

@Test
    public void testContainsAny_StringStringarray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(CharSetUtils.containsAny("hello", (String[]) null));
    }

@Test
    public void testContainsAny_StringStringarray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(CharSetUtils.containsAny("hello"));
    }

@Test
    public void testContainsAny_StringStringarray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(CharSetUtils.containsAny("hello", null));
    }

@Test
    public void testContainsAny_StringStringarray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CharSetUtils.containsAny("hello", "a-e"));
    }

@Test
    public void testContainsAny_StringStringarray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(CharSetUtils.containsAny("hello", "el"));
    }

@Test
    public void testContainsAny_StringStringarray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(CharSetUtils.containsAny("hello", "x"));
    }

@Test
    public void testContainsAny_StringStringarray_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(CharSetUtils.containsAny("hello", "e-i"));
    }

@Test
    public void testContainsAny_StringStringarray_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CharSetUtils.containsAny("hello", "a-z"));
    }

@Test
    public void testContainsAny_StringStringarray_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharSetUtils.containsAny("hello", ""));
    }

@Test
    public void testCount_StringString_1_oe() {
        assertEquals(0, CharSetUtils.count(null, (String) null));
    }

@Test
    public void testCount_StringString_2_oe() {
        // removed other assertion
        assertEquals(0, CharSetUtils.count(null, ""));
    }

@Test
    public void testCount_StringString_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals(0, CharSetUtils.count("", (String) null));
    }

@Test
    public void testCount_StringString_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, CharSetUtils.count("", ""));
    }

@Test
    public void testCount_StringString_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0, CharSetUtils.count("", "a-e"));
    }

@Test
    public void testCount_StringString_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, CharSetUtils.count("hello", (String) null));
    }

@Test
    public void testCount_StringString_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, CharSetUtils.count("hello", ""));
    }

@Test
    public void testCount_StringString_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(1, CharSetUtils.count("hello", "a-e"));
    }

@Test
    public void testCount_StringString_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, CharSetUtils.count("hello", "l-p"));
    }

@Test
    public void testCount_StringStringarray_1_oe() {
        assertEquals(0, CharSetUtils.count(null, (String[]) null));
    }

@Test
    public void testCount_StringStringarray_2_oe() {
        // removed other assertion
        assertEquals(0, CharSetUtils.count(null));
    }

@Test
    public void testCount_StringStringarray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, CharSetUtils.count(null, null));
    }

@Test
    public void testCount_StringStringarray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, CharSetUtils.count(null, "a-e"));
    }

@Test
    public void testCount_StringStringarray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, CharSetUtils.count("", (String[]) null));
    }

@Test
    public void testCount_StringStringarray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, CharSetUtils.count(""));
    }

@Test
    public void testCount_StringStringarray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0, CharSetUtils.count("", null));
    }

@Test
    public void testCount_StringStringarray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, CharSetUtils.count("", "a-e"));
    }

@Test
    public void testCount_StringStringarray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, CharSetUtils.count("hello", (String[]) null));
    }

@Test
    public void testCount_StringStringarray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, CharSetUtils.count("hello"));
    }

@Test
    public void testCount_StringStringarray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0, CharSetUtils.count("hello", null));
    }

@Test
    public void testCount_StringStringarray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, CharSetUtils.count("hello", "a-e"));
    }

@Test
    public void testCount_StringStringarray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(3, CharSetUtils.count("hello", "el"));
    }

@Test
    public void testCount_StringStringarray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, CharSetUtils.count("hello", "x"));
    }

@Test
    public void testCount_StringStringarray_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(2, CharSetUtils.count("hello", "e-i"));
    }

@Test
    public void testCount_StringStringarray_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, CharSetUtils.count("hello", "a-z"));
    }

@Test
    public void testCount_StringStringarray_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, CharSetUtils.count("hello", ""));
    }

@Test
    public void testKeep_StringString_1_oe() {
        assertNull(CharSetUtils.keep(null, (String) null));
    }

@Test
    public void testKeep_StringString_2_oe() {
        // removed other assertion
        assertNull(CharSetUtils.keep(null, ""));
    }

@Test
    public void testKeep_StringString_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals("", CharSetUtils.keep("", (String) null));
    }

@Test
    public void testKeep_StringString_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", CharSetUtils.keep("", ""));
    }

@Test
    public void testKeep_StringString_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.keep("", "a-e"));
    }

@Test
    public void testKeep_StringString_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("", CharSetUtils.keep("hello", (String) null));
    }

@Test
    public void testKeep_StringString_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", CharSetUtils.keep("hello", ""));
    }

@Test
    public void testKeep_StringString_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.keep("hello", "xyz"));
    }

@Test
    public void testKeep_StringString_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.keep("hello", "a-z"));
    }

@Test
    public void testKeep_StringString_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.keep("hello", "oleh"));
    }

@Test
    public void testKeep_StringString_11_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ell", CharSetUtils.keep("hello", "el"));
    }

@Test
    public void testKeep_StringStringarray_1_oe() {
        assertNull(CharSetUtils.keep(null, (String[]) null));
    }

@Test
    public void testKeep_StringStringarray_2_oe() {
        // removed other assertion
        assertNull(CharSetUtils.keep(null));
    }

@Test
    public void testKeep_StringStringarray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(CharSetUtils.keep(null, null));
    }

@Test
    public void testKeep_StringStringarray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(CharSetUtils.keep(null, "a-e"));
    }

@Test
    public void testKeep_StringStringarray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("", CharSetUtils.keep("", (String[]) null));
    }

@Test
    public void testKeep_StringStringarray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", CharSetUtils.keep(""));
    }

@Test
    public void testKeep_StringStringarray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.keep("", null));
    }

@Test
    public void testKeep_StringStringarray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.keep("", "a-e"));
    }

@Test
    public void testKeep_StringStringarray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("", CharSetUtils.keep("hello", (String[]) null));
    }

@Test
    public void testKeep_StringStringarray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", CharSetUtils.keep("hello"));
    }

@Test
    public void testKeep_StringStringarray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.keep("hello", null));
    }

@Test
    public void testKeep_StringStringarray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("e", CharSetUtils.keep("hello", "a-e"));
    }

@Test
    public void testKeep_StringStringarray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("e", CharSetUtils.keep("hello", "a-e"));
    }

@Test
    public void testKeep_StringStringarray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("ell", CharSetUtils.keep("hello", "el"));
    }

@Test
    public void testKeep_StringStringarray_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.keep("hello", "elho"));
    }

@Test
    public void testKeep_StringStringarray_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.keep("hello", "a-z"));
    }

@Test
    public void testKeep_StringStringarray_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("----", CharSetUtils.keep("----", "-"));
    }

@Test
    public void testKeep_StringStringarray_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ll", CharSetUtils.keep("hello", "l"));
    }

@Test
    public void testDelete_StringString_1_oe() {
        assertNull(CharSetUtils.delete(null, (String) null));
    }

@Test
    public void testDelete_StringString_2_oe() {
        // removed other assertion
        assertNull(CharSetUtils.delete(null, ""));
    }

@Test
    public void testDelete_StringString_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals("", CharSetUtils.delete("", (String) null));
    }

@Test
    public void testDelete_StringString_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", CharSetUtils.delete("", ""));
    }

@Test
    public void testDelete_StringString_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.delete("", "a-e"));
    }

@Test
    public void testDelete_StringString_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("hello", CharSetUtils.delete("hello", (String) null));
    }

@Test
    public void testDelete_StringString_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("hello", CharSetUtils.delete("hello", ""));
    }

@Test
    public void testDelete_StringString_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("hllo", CharSetUtils.delete("hello", "a-e"));
    }

@Test
    public void testDelete_StringString_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("he", CharSetUtils.delete("hello", "l-p"));
    }

@Test
    public void testDelete_StringString_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.delete("hello", "z"));
    }

@Test
    public void testDelete_StringStringarray_1_oe() {
        assertNull(CharSetUtils.delete(null, (String[]) null));
    }

@Test
    public void testDelete_StringStringarray_2_oe() {
        // removed other assertion
        assertNull(CharSetUtils.delete(null));
    }

@Test
    public void testDelete_StringStringarray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(CharSetUtils.delete(null, null));
    }

@Test
    public void testDelete_StringStringarray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(CharSetUtils.delete(null, "el"));
    }

@Test
    public void testDelete_StringStringarray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("", CharSetUtils.delete("", (String[]) null));
    }

@Test
    public void testDelete_StringStringarray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", CharSetUtils.delete(""));
    }

@Test
    public void testDelete_StringStringarray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.delete("", null));
    }

@Test
    public void testDelete_StringStringarray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.delete("", "a-e"));
    }

@Test
    public void testDelete_StringStringarray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("hello", CharSetUtils.delete("hello", (String[]) null));
    }

@Test
    public void testDelete_StringStringarray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("hello", CharSetUtils.delete("hello"));
    }

@Test
    public void testDelete_StringStringarray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.delete("hello", null));
    }

@Test
    public void testDelete_StringStringarray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.delete("hello", "xyz"));
    }

@Test
    public void testDelete_StringStringarray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("ho", CharSetUtils.delete("hello", "el"));
    }

@Test
    public void testDelete_StringStringarray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", CharSetUtils.delete("hello", "elho"));
    }

@Test
    public void testDelete_StringStringarray_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.delete("hello", ""));
    }

@Test
    public void testDelete_StringStringarray_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hello", CharSetUtils.delete("hello", ""));
    }

@Test
    public void testDelete_StringStringarray_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.delete("hello", "a-z"));
    }

@Test
    public void testDelete_StringStringarray_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", CharSetUtils.delete("----", "-"));
    }

@Test
    public void testDelete_StringStringarray_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("heo", CharSetUtils.delete("hello", "l"));
    }

}
