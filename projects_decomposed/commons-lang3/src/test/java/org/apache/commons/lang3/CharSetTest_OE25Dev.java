/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.CharSet}.
 */
public class CharSetTest_OE25Dev  {

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @Test
    public void testClass_1_oe() {
        assertTrue(Modifier.isPublic(CharSet.class.getModifiers()));
    }

    @Test
    public void testClass_2_oe() {
        // removed other assertion
        assertFalse(Modifier.isFinal(CharSet.class.getModifiers()));
    }

    @Test
    public void testGetInstance_1_oe() {
        assertSame(CharSet.EMPTY, CharSet.getInstance( (String) null));
    }

    @Test
    public void testGetInstance_2_oe() {
        // removed other assertion
        assertSame(CharSet.EMPTY, CharSet.getInstance(""));
    }

    @Test
    public void testGetInstance_3_oe() {
        // removed other assertion
        // removed other assertion
        assertSame(CharSet.ASCII_ALPHA, CharSet.getInstance("a-zA-Z"));
    }

    @Test
    public void testGetInstance_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(CharSet.ASCII_ALPHA, CharSet.getInstance("A-Za-z"));
    }

    @Test
    public void testGetInstance_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(CharSet.ASCII_ALPHA_LOWER, CharSet.getInstance("a-z"));
    }

    @Test
    public void testGetInstance_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(CharSet.ASCII_ALPHA_UPPER, CharSet.getInstance("A-Z"));
    }

    @Test
    public void testGetInstance_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(CharSet.ASCII_NUMERIC, CharSet.getInstance("0-9"));
    }

    @Test
    public void testGetInstance_Stringarray_1_oe() {
        assertNull(CharSet.getInstance((String[]) null));
    }

    @Test
    public void testGetInstance_Stringarray_2_oe() {
        // removed other assertion
        assertEquals("[]", CharSet.getInstance(new String[0]).toString());
    }

    @Test
    public void testGetInstance_Stringarray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("[]", CharSet.getInstance(new String[] {null}).toString());
    }

    @Test
    public void testGetInstance_Stringarray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[a-e]", CharSet.getInstance(new String[] {"a-e"}).toString());
    }

    @Test
    public void testConstructor_String_simple_1_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        assertEquals("[]", set.toString());
    }

    @Test
    public void testConstructor_String_simple_2_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        assertEquals(0, array.length);
    }

    @Test
    public void testConstructor_String_simple_3_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        assertEquals("[]", set.toString());
    }

    @Test
    public void testConstructor_String_simple_4_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        assertEquals(0, array.length);
    }

    @Test
    public void testConstructor_String_simple_5_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        assertEquals("[a]", set.toString());
    }

    @Test
    public void testConstructor_String_simple_6_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_simple_7_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertEquals("a", array[0].toString());
    }

    @Test
    public void testConstructor_String_simple_8_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a");
        array = set.getCharRanges();
        assertEquals("[^a]", set.toString());
    }

    @Test
    public void testConstructor_String_simple_9_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a");
        array = set.getCharRanges();
        // removed other assertion
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_simple_10_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertEquals("^a", array[0].toString());
    }

    @Test
    public void testConstructor_String_simple_11_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-e");
        array = set.getCharRanges();
        assertEquals("[a-e]", set.toString());
    }

    @Test
    public void testConstructor_String_simple_12_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-e");
        array = set.getCharRanges();
        // removed other assertion
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_simple_13_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-e");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertEquals("a-e", array[0].toString());
    }

    @Test
    public void testConstructor_String_simple_14_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-e");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-e");
        array = set.getCharRanges();
        assertEquals("[^a-e]", set.toString());
    }

    @Test
    public void testConstructor_String_simple_15_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-e");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-e");
        array = set.getCharRanges();
        // removed other assertion
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_simple_16_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-e");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-e");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertEquals("^a-e", array[0].toString());
    }

    @Test
    public void testConstructor_String_combo_1_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        assertEquals(3, array.length);
    }

    @Test
    public void testConstructor_String_combo_2_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
    }

    @Test
    public void testConstructor_String_combo_3_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('b')));
    }

    @Test
    public void testConstructor_String_combo_4_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('c')));
    }

    @Test
    public void testConstructor_String_combo_5_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        assertEquals(2, array.length);
    }

    @Test
    public void testConstructor_String_combo_6_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('a', 'c')));
    }

    @Test
    public void testConstructor_String_combo_7_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('e', 'f')));
    }

    @Test
    public void testConstructor_String_combo_8_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        assertEquals(2, array.length);
    }

    @Test
    public void testConstructor_String_combo_9_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
    }

    @Test
    public void testConstructor_String_combo_10_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('e', 'f')));
    }

    @Test
    public void testConstructor_String_combo_11_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("e-fa");
        array = set.getCharRanges();
        assertEquals(2, array.length);
    }

    @Test
    public void testConstructor_String_combo_12_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("e-fa");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
    }

    @Test
    public void testConstructor_String_combo_13_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("e-fa");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('e', 'f')));
    }

    @Test
    public void testConstructor_String_combo_14_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("e-fa");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-fm-pz");
        array = set.getCharRanges();
        assertEquals(4, array.length);
    }

    @Test
    public void testConstructor_String_combo_15_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("e-fa");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-fm-pz");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
    }

    @Test
    public void testConstructor_String_combo_16_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("e-fa");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-fm-pz");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('e', 'f')));
    }

    @Test
    public void testConstructor_String_combo_17_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("e-fa");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-fm-pz");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('m', 'p')));
    }

    @Test
    public void testConstructor_String_combo_18_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("e-fa");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("ae-fm-pz");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('z')));
    }

    @Test
    public void testConstructor_String_comboNegated_1_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        assertEquals(3, array.length);
    }

    @Test
    public void testConstructor_String_comboNegated_2_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isNot('a')));
    }

    @Test
    public void testConstructor_String_comboNegated_3_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('b')));
    }

    @Test
    public void testConstructor_String_comboNegated_4_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('c')));
    }

    @Test
    public void testConstructor_String_comboNegated_5_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        assertEquals(3, array.length);
    }

    @Test
    public void testConstructor_String_comboNegated_6_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('b')));
    }

    @Test
    public void testConstructor_String_comboNegated_7_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isNot('a')));
    }

    @Test
    public void testConstructor_String_comboNegated_8_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('c')));
    }

    @Test
    public void testConstructor_String_comboNegated_9_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        assertEquals(4, array.length);
    }

    @Test
    public void testConstructor_String_comboNegated_10_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('d')));
    }

    @Test
    public void testConstructor_String_comboNegated_11_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('b')));
    }

    @Test
    public void testConstructor_String_comboNegated_12_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isNot('a')));
    }

    @Test
    public void testConstructor_String_comboNegated_13_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('c')));
    }

    @Test
    public void testConstructor_String_comboNegated_14_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^b^a");
        array = set.getCharRanges();
        assertEquals(2, array.length);
    }

    @Test
    public void testConstructor_String_comboNegated_15_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^b^a");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isNot('b')));
    }

    @Test
    public void testConstructor_String_comboNegated_16_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^b^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isNot('a')));
    }

    @Test
    public void testConstructor_String_comboNegated_17_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^b^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^a-c^z");
        array = set.getCharRanges();
        assertEquals(3, array.length);
    }

    @Test
    public void testConstructor_String_comboNegated_18_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^b^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^a-c^z");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isNotIn('a', 'c')));
    }

    @Test
    public void testConstructor_String_comboNegated_19_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^b^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^a-c^z");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isNot('z')));
    }

    @Test
    public void testConstructor_String_comboNegated_20_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^b^a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b^a-c^z");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('b')));
    }

    @Test
    public void testConstructor_String_oddDash_1_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddDash_2_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
    }

    @Test
    public void testConstructor_String_oddDash_3_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddDash_4_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
    }

    @Test
    public void testConstructor_String_oddDash_5_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddDash_6_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
    }

    @Test
    public void testConstructor_String_oddDash_7_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddDash_8_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
    }

    @Test
    public void testConstructor_String_oddDash_9_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        assertEquals(2, array.length);
    }

    @Test
    public void testConstructor_String_oddDash_10_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
    }

    @Test
    public void testConstructor_String_oddDash_11_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
    }

    @Test
    public void testConstructor_String_oddDash_12_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-");
        array = set.getCharRanges();
        assertEquals(2, array.length);
    }

    @Test
    public void testConstructor_String_oddDash_13_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
    }

    @Test
    public void testConstructor_String_oddDash_14_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
    }

    @Test
    public void testConstructor_String_oddDash_15_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a--");
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddDash_16_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a--");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('a', '-')));
    }

    @Test
    public void testConstructor_String_oddDash_17_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--a");
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddDash_18_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a-");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("a--");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("--a");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('-', 'a')));
    }

    @Test
    public void testConstructor_String_oddNegate_1_oe() {
        CharSet set;
        CharRange[] array;
        set = CharSet.getInstance("^");
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddNegate_2_oe() {
        CharSet set;
        CharRange[] array;
        set = CharSet.getInstance("^");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array,CharRange.is('^')));// "^" set = CharSet.getInstance("^^");
    }

    @Test
    public void testConstructor_String_oddNegate_3_oe() {
        CharSet set;
        CharRange[] array;
        set = CharSet.getInstance("^");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddNegate_7_oe() {
        CharSet set;
        CharRange[] array;
        set = CharSet.getInstance("^");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddNegate_13_oe() {
        CharSet set;
        CharRange[] array;
        set = CharSet.getInstance("^");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddNegate_15_oe() {
        CharSet set;
        CharRange[] array;
        set = CharSet.getInstance("^");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        array = set.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testConstructor_String_oddCombinations_1_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        assertTrue(ArrayUtils.contains(array,CharRange.isIn('a','^')));// "a-^" assertTrue(ArrayUtils.contains(array,CharRange.is('c')));// "c" assertFalse(set.contains('b'));
    }

    @Test
    public void testConstructor_String_oddCombinations_2_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(set.contains('^'));
    }

    @Test
    public void testConstructor_String_oddCombinations_3_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(set.contains('_'));// between ^ and a assertTrue(set.contains('c'));
    }

    @Test
    public void testConstructor_String_oddCombinations_4_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        assertTrue(ArrayUtils.contains(array,CharRange.isNotIn('a','^')));// "^a-^" assertTrue(ArrayUtils.contains(array,CharRange.is('c')));// "c" assertTrue(set.contains('b'));
    }

    @Test
    public void testConstructor_String_oddCombinations_5_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        // removed other assertion
        assertFalse(set.contains('^'));
    }

    @Test
    public void testConstructor_String_oddCombinations_6_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertFalse(set.contains('_'));// between ^ and a set = CharSet.getInstance("a- ^-- ");//contains everything array = set.getCharRanges();
    }

    @Test
    public void testConstructor_String_oddCombinations_10_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(set.contains('*'));
    }

    @Test
    public void testConstructor_String_oddCombinations_11_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(set.contains('A'));
    }

    @Test
    public void testConstructor_String_oddCombinations_12_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^-b");
        array = set.getCharRanges();
        assertTrue(ArrayUtils.contains(array,CharRange.isIn('^','b')));// "^-b" assertTrue(set.contains('b'));
    }

    @Test
    public void testConstructor_String_oddCombinations_13_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^-b");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(set.contains('_'));// between ^ and a assertFalse(set.contains('A'));
    }

    @Test
    public void testConstructor_String_oddCombinations_14_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^-b");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(set.contains('^'));
    }

    @Test
    public void testConstructor_String_oddCombinations_15_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^-b");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b-^");
        array = set.getCharRanges();
        assertTrue(ArrayUtils.contains(array,CharRange.isIn('^','b')));// "b-^" assertTrue(set.contains('b'));
    }

    @Test
    public void testConstructor_String_oddCombinations_16_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^-b");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b-^");
        array = set.getCharRanges();
        // removed other assertion
        assertTrue(set.contains('^'));
    }

    @Test
    public void testConstructor_String_oddCombinations_17_oe() {
        CharSet set;
        CharRange[] array;

        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("^-b");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set = CharSet.getInstance("b-^");
        array = set.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(set.contains('a'));// between ^ and b assertFalse(set.contains('c'));
    }

    @Test
    public void testEquals_Object_1_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        assertNotEquals(null, abc);
    }

    @Test
    public void testEquals_Object_2_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        assertEquals(abc, abc);
    }

    @Test
    public void testEquals_Object_3_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        assertEquals(abc, abc2);
    }

    @Test
    public void testEquals_Object_4_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNotEquals(abc, atoc);
    }

    @Test
    public void testEquals_Object_5_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(abc, notatoc);
    }

    @Test
    public void testEquals_Object_6_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNotEquals(atoc, abc);
    }

    @Test
    public void testEquals_Object_7_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(atoc, atoc);
    }

    @Test
    public void testEquals_Object_8_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(atoc, atoc2);
    }

    @Test
    public void testEquals_Object_9_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(atoc, notatoc);
    }

    @Test
    public void testEquals_Object_10_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNotEquals(notatoc, abc);
    }

    @Test
    public void testEquals_Object_11_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertNotEquals(notatoc, atoc);
    }

    @Test
    public void testEquals_Object_12_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(notatoc, notatoc);
    }

    @Test
    public void testEquals_Object_13_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(notatoc, notatoc2);
    }

    @Test
    public void testHashCode_1_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        assertEquals(abc.hashCode(), abc.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion
        assertEquals(abc.hashCode(), abc2.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion
        // removed other assertion
        assertEquals(atoc.hashCode(), atoc.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(atoc.hashCode(), atoc2.hashCode());
    }

    @Test
    public void testHashCode_5_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(notatoc.hashCode(), notatoc.hashCode());
    }

    @Test
    public void testHashCode_6_oe() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(notatoc.hashCode(), notatoc2.hashCode());
    }

    @Test
    public void testContains_Char_1_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        assertFalse(btod.contains('a'));
    }

    @Test
    public void testContains_Char_2_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        assertTrue(btod.contains('b'));
    }

    @Test
    public void testContains_Char_3_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        assertTrue(btod.contains('c'));
    }

    @Test
    public void testContains_Char_4_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(btod.contains('d'));
    }

    @Test
    public void testContains_Char_5_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(btod.contains('e'));
    }

    @Test
    public void testContains_Char_6_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(bcd.contains('a'));
    }

    @Test
    public void testContains_Char_7_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(bcd.contains('b'));
    }

    @Test
    public void testContains_Char_8_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(bcd.contains('c'));
    }

    @Test
    public void testContains_Char_9_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(bcd.contains('d'));
    }

    @Test
    public void testContains_Char_10_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(bcd.contains('e'));
    }

    @Test
    public void testContains_Char_11_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(bd.contains('a'));
    }

    @Test
    public void testContains_Char_12_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(bd.contains('b'));
    }

    @Test
    public void testContains_Char_13_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(bd.contains('c'));
    }

    @Test
    public void testContains_Char_14_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(bd.contains('d'));
    }

    @Test
    public void testContains_Char_15_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(bd.contains('e'));
    }

    @Test
    public void testContains_Char_16_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(notbtod.contains('a'));
    }

    @Test
    public void testContains_Char_17_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(notbtod.contains('b'));
    }

    @Test
    public void testContains_Char_18_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(notbtod.contains('c'));
    }

    @Test
    public void testContains_Char_19_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(notbtod.contains('d'));
    }

    @Test
    public void testContains_Char_20_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(notbtod.contains('e'));
    }

    @Test
    public void testContains_Char_21_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(dtob.contains('a'));
    }

    @Test
    public void testContains_Char_22_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(dtob.contains('b'));
    }

    @Test
    public void testContains_Char_23_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(dtob.contains('c'));
    }

    @Test
    public void testContains_Char_24_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(dtob.contains('d'));
    }

    @Test
    public void testContains_Char_25_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(dtob.contains('e'));
    }

    @Test
    public void testContains_Char_26_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final CharRange[] array = dtob.getCharRanges();
        assertEquals("[b-d]", dtob.toString());
    }

    @Test
    public void testContains_Char_27_oe() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final CharRange[] array = dtob.getCharRanges();
        // removed other assertion
        assertEquals(1, array.length);
    }

    @Test
    public void testSerialization_1_oe() {
        CharSet set = CharSet.getInstance("a");
        assertEquals(set, SerializationUtils.clone(set));
    }

    @Test
    public void testSerialization_2_oe() {
        CharSet set = CharSet.getInstance("a");
        // removed other assertion
        set = CharSet.getInstance("a-e");
        assertEquals(set, SerializationUtils.clone(set));
    }

    @Test
    public void testSerialization_3_oe() {
        CharSet set = CharSet.getInstance("a");
        // removed other assertion
        set = CharSet.getInstance("a-e");
        // removed other assertion
        set = CharSet.getInstance("be-f^a-z");
        assertEquals(set, SerializationUtils.clone(set));
    }

    @Test
    public void testStatics_1_oe() {
        CharRange[] array;

        array = CharSet.EMPTY.getCharRanges();
        assertEquals(0, array.length);
    }

    @Test
    public void testStatics_2_oe() {
        CharRange[] array;

        array = CharSet.EMPTY.getCharRanges();
        // removed other assertion

        array = CharSet.ASCII_ALPHA.getCharRanges();
        assertEquals(2, array.length);
    }

    @Test
    public void testStatics_3_oe() {
        CharRange[] array;

        array = CharSet.EMPTY.getCharRanges();
        // removed other assertion

        array = CharSet.ASCII_ALPHA.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('a', 'z')));
    }

    @Test
    public void testStatics_4_oe() {
        CharRange[] array;

        array = CharSet.EMPTY.getCharRanges();
        // removed other assertion

        array = CharSet.ASCII_ALPHA.getCharRanges();
        // removed other assertion
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('A', 'Z')));
    }

    @Test
    public void testStatics_5_oe() {
        CharRange[] array;

        array = CharSet.EMPTY.getCharRanges();
        // removed other assertion

        array = CharSet.ASCII_ALPHA.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_ALPHA_LOWER.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testStatics_6_oe() {
        CharRange[] array;

        array = CharSet.EMPTY.getCharRanges();
        // removed other assertion

        array = CharSet.ASCII_ALPHA.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_ALPHA_LOWER.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('a', 'z')));
    }

    @Test
    public void testStatics_7_oe() {
        CharRange[] array;

        array = CharSet.EMPTY.getCharRanges();
        // removed other assertion

        array = CharSet.ASCII_ALPHA.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_ALPHA_LOWER.getCharRanges();
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_ALPHA_UPPER.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testStatics_8_oe() {
        CharRange[] array;

        array = CharSet.EMPTY.getCharRanges();
        // removed other assertion

        array = CharSet.ASCII_ALPHA.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_ALPHA_LOWER.getCharRanges();
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_ALPHA_UPPER.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('A', 'Z')));
    }

    @Test
    public void testStatics_9_oe() {
        CharRange[] array;

        array = CharSet.EMPTY.getCharRanges();
        // removed other assertion

        array = CharSet.ASCII_ALPHA.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_ALPHA_LOWER.getCharRanges();
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_ALPHA_UPPER.getCharRanges();
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_NUMERIC.getCharRanges();
        assertEquals(1, array.length);
    }

    @Test
    public void testStatics_10_oe() {
        CharRange[] array;

        array = CharSet.EMPTY.getCharRanges();
        // removed other assertion

        array = CharSet.ASCII_ALPHA.getCharRanges();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_ALPHA_LOWER.getCharRanges();
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_ALPHA_UPPER.getCharRanges();
        // removed other assertion
        // removed other assertion

        array = CharSet.ASCII_NUMERIC.getCharRanges();
        // removed other assertion
        assertTrue(ArrayUtils.contains(array, CharRange.isIn('0', '9')));
    }

    @Test
    public void testJavadocExamples_1_oe() {
        assertFalse(CharSet.getInstance("^a-c").contains('a'));
    }

    @Test
    public void testJavadocExamples_2_oe() {
        // removed other assertion
        assertTrue(CharSet.getInstance("^a-c").contains('d'));
    }

    @Test
    public void testJavadocExamples_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(CharSet.getInstance("^^a-c").contains('a'));
    }

    @Test
    public void testJavadocExamples_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CharSet.getInstance("^^a-c").contains('^'));
    }

    @Test
    public void testJavadocExamples_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CharSet.getInstance("^a-cd-f").contains('d'));
    }

    @Test
    public void testJavadocExamples_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CharSet.getInstance("a-c^").contains('^'));
    }

    @Test
    public void testJavadocExamples_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CharSet.getInstance("^", "a-c").contains('^'));
    }

}
